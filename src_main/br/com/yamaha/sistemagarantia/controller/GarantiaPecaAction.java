/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......GarantiaPecaAction.java
 *   .: Criação.....15 de junho, 10:11
 *   .: Autor.......Edson Luiz Sumensari
 *                  Thiago Uriel M. Garcia
 *   .: Descrição...Action para sistema de peças de garantia.
 */
package br.com.yamaha.sistemagarantia.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.DynaValidatorActionForm;
import org.springframework.beans.BeansException;

import br.com.resource.infra.controller.InfraDispatchAction;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.ControllerException;
import br.com.resource.infra.security.exception.InvalidSessionException;
import br.com.resource.infra.security.helper.UserHelper;
import br.com.resource.infra.utils.DateUtils;
import br.com.resource.infra.utils.NumberUtils;
import br.com.yamaha.sistemagarantia.business.GarantiaArquivoBusiness;
import br.com.yamaha.sistemagarantia.business.GarantiaHeaderBusiness;
import br.com.yamaha.sistemagarantia.business.GarantiaLineBusiness;
import br.com.yamaha.sistemagarantia.business.ItemBusiness;
import br.com.yamaha.sistemagarantia.business.exception.FileException;
import br.com.yamaha.sistemagarantia.controller.helper.ControllerHelper;
import br.com.yamaha.sistemagarantia.controller.helper.FileHelper;
import br.com.yamaha.sistemagarantia.controller.helper.YmSessionHelper;
import br.com.yamaha.sistemagarantia.model.AlertGarantia;
import br.com.yamaha.sistemagarantia.model.Concessionaria;
import br.com.yamaha.sistemagarantia.model.DescricaoDefeito;
import br.com.yamaha.sistemagarantia.model.FatorGarantia;
import br.com.yamaha.sistemagarantia.model.GarantiaArquivo;
import br.com.yamaha.sistemagarantia.model.GarantiaArquivoMoto;
import br.com.yamaha.sistemagarantia.model.GarantiaArquivoNtc;
import br.com.yamaha.sistemagarantia.model.GarantiaHeader;
import br.com.yamaha.sistemagarantia.model.GarantiaLine;
import br.com.yamaha.sistemagarantia.model.Item;
import br.com.yamaha.sistemagarantia.model.Linha;
import br.com.yamaha.sistemagarantia.model.Lote;
import br.com.yamaha.sistemagarantia.model.ParametroSistema;
import br.com.yamaha.sistemagarantia.model.TabelaPreco;
import br.com.yamaha.sistemagarantia.model.Usuario;
import br.com.yamaha.sistemagarantia.model.id.GarantiaLineId;
import br.com.yamaha.sistemagarantia.model.id.TabelaPrecoId;
import br.com.yamaha.sistemagarantia.model.to.GarantiaTO;
import br.com.yamaha.sistemagarantia.model.to.PecaTO;
import br.com.yamaha.sistemagarantia.utils.FileUtils;

/** Action responsável por fornecer controle às funcionalidades
 *  do módulo de peças de garantias do sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class GarantiaPecaAction extends InfraDispatchAction {

	private static final long serialVersionUID = 1L;
	
    /** Tarefa: Formulário de inclusão de Peça de SG.
     * 
     *  @param mapping
     *      O objeto <code>ActionMapping</code> utilizado para
     *      selecionar esta instância.
     *  @param form
     *      Um objeto (opcional) do tipo <code>ActionForm</code>
     *      utilizado nesta requisição.
     *  @param request
     *      A requisição <code>HTTP</code> que estamos processando.
     *  @param response
     *      A resposta <code>HTTP</code> que estamos processando.
     * 
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward add( ActionMapping mapping
                            , ActionForm form
                            , HttpServletRequest request
                            , HttpServletResponse response) throws ControllerException {

    	try {
    		
    		Usuario usuario = (Usuario) UserHelper.getBoundedUser(request.getSession());
    		GarantiaHeaderBusiness garantiaHeaderBusiness = (GarantiaHeaderBusiness) springContext.getBean("garantiaHeaderBO");
    		
    		// Retornamos a garantia que está na sessão
    		GarantiaTO     garantiaTO = YmSessionHelper.getGarantiaTOFromSession(request);
    		GarantiaHeader garantia   = null;
    		Concessionaria concessionaria = usuario.getConcessionaria();
    		
    		// Verificamos se a garantia veio pelo TO ou por id
    		if ( garantiaTO != null ) {
    			
    			garantia = garantiaTO.getGarantia();
    			
    		} else {
    			
    			garantiaTO = new GarantiaTO();
    			String garantiaId = request.getAttribute("garantiaId").toString();
    			garantia = garantiaHeaderBusiness.get(Integer.valueOf(garantiaId));
    			garantiaTO.setGarantia(garantia);
    			YmSessionHelper.setGarantiaTOToSession(request, garantiaTO);
    			
    		}
    		
    		// Se  a concessionária possui aprovação automática e o status da garantia for 5, permitir alteração
    		//boolean hasAprovAut = Concessionaria.FLAG_APROVA_YES.equalsIgnoreCase(concessionaria.getFlagAprovacaoAutom()) && (new Long(5).equals(garantia.getStatusGarantia().getEntityId()));
    		
			String isEdit			 = request.getAttribute("isEdit").toString();
			boolean isZeroKm         = ((Boolean)request.getAttribute("isZeroKm")).booleanValue();
			//log.info("isZeroKm:" + isZeroKm);
			boolean consulta         = ((Boolean)request.getAttribute("consulta")).booleanValue();
			
			Lote   lote   = garantia.getLote();
						
			DynaActionForm pecaForm = (DynaActionForm) form;
			pecaForm.initialize(mapping);    	
			
			pecaForm.set( "counter", new Long(1) );
			
			if ( garantia.getEntityId() != null )
				pecaForm.set( "entityId", garantia.getEntityId() );
			
			pecaForm.set( "chassi", garantia.getModelo() );	
			pecaForm.set( "numeroOS", garantia.getNumeroOS() );
			pecaForm.set( "dataAberturaOS", DateUtils.applyMask(garantia.getDataAberturaOS()));
			pecaForm.set( "dataFechamentoOS", DateUtils.applyMask(garantia.getDataFechamentoOS()));
			
			pecaForm.set( "isEdit", isEdit);
			pecaForm.set( "loteId", lote.getEntityId());
			pecaForm.set( "linhaId", lote.getLinha().getEntityId());
			
			try {
				if(garantia.getEntityId() != null ) {
					GarantiaArquivoBusiness gArquivoBusiness = (GarantiaArquivoBusiness) springContext.getBean("garantiaArquivoBO");				
									
					pecaForm.set( "listFiles", gArquivoBusiness.listFile((Integer)garantia.getEntityId()));
				} else {
					pecaForm.set( "listFiles", null);
				}
				
			}catch(FileException flExp){
				pecaForm.set( "listFiles", null);
			}
			
			//log.info("------> Garantia - Status Lote ID: " +  lote.getStatusLote().getEntityId());
			//log.info("------> Garantia - IsEdit        : " +  isEdit);
			//log.info("------> Garantia - Status  ID    : " +  garantia.getStatusGarantia().getEntityId());
						
			List pecas = new ArrayList();
			
			if ( !garantia.isNew() )
				pecas = garantiaHeaderBusiness.listPecas(garantia);
			
			pecaForm.set( "filledLines", pecas );
			
			// Enviamos este parâmetro para deixar o box enviar checado e desabilitado
			if ( "SP".equalsIgnoreCase(concessionaria.getUf()) ||"RJ".equalsIgnoreCase(concessionaria.getUf()) )
				
				if ( !isZeroKm )
					request.setAttribute("isCheckEnviar"      , new Boolean(true) );
				else
					request.setAttribute("isCheckEnviar"      , new Boolean(false) );
			else
				request.setAttribute("isCheckEnviar"      , new Boolean(false) );	
				
			request.setAttribute("statusLoteId", lote.getStatusLote().getEntityId() );
			request.setAttribute("consulta"    , new Boolean(consulta) );
			request.setAttribute("isZeroKm"    , new Boolean(isZeroKm) );
			request.setAttribute("isEdit"      , new Boolean(isEdit) );
			
			return mapping.findForward("_form");
			
		} catch (BeansException bExp) {
			throw new ControllerException( bExp );
			
		} catch (NumberFormatException nfExp) {
			throw new ControllerException( nfExp );
			
		} catch (BusinessException bExp) {
			throw new ControllerException( bExp );
			
		} catch ( InvalidSessionException isExp ) {
    		
    		throw new ControllerException(isExp);
    		
		}   
			
    }
    
    /** Tarefa: Remove uma peça.
     * 
     *  @param mapping
     *      O objeto <code>ActionMapping</code> utilizado para
     *      selecionar esta instância.
     *  @param form
     *      Um objeto (opcional) do tipo <code>ActionForm</code>
     *      utilizado nesta requisição.
     *  @param request
     *      A requisição <code>HTTP</code> que estamos processando.
     *  @param response
     *      A resposta <code>HTTP</code> que estamos processando.
     * 
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */    
    public ActionForward removeLine( ActionMapping mapping
                             , ActionForm form
                             , HttpServletRequest request
                             , HttpServletResponse response) throws ControllerException {
    	
    	DynaActionForm pecaForm = (DynaActionForm) form;
		pecaForm.initialize(mapping);  
    	
    	ActionMessages 			successMessages = new ActionMessages();
		ActionMessages          errorMessages   = new ActionMessages();
    	
    	String garantiaId = request.getParameter("garantiaId");
    	String lineId 	  = request.getParameter("lineId");
    	//String isZeroKm	  = request.getParameter("isZeroKm");
    	boolean isZeroKm         = (new Boolean(request.getParameter("isZeroKm"))).booleanValue();
    	//log.info("GarantiaId" + garantiaId + " - lineId:" + lineId);
    	
    	try {
    		
    		Usuario usuario = (Usuario) UserHelper.getBoundedUser(request.getSession());
    		//ItemBusiness itemBusiness = (ItemBusiness) springContext.getBean("itemBO");
    		GarantiaLineBusiness garantiaLineBusiness = (GarantiaLineBusiness) springContext.getBean("garantiaLineBO");
    		GarantiaHeaderBusiness garantiaHeaderBusiness = (GarantiaHeaderBusiness) springContext.getBean("garantiaHeaderBO");
    		
    		// Retornamos a garantia que está na sessão
    		GarantiaTO     garantiaTO = YmSessionHelper.getGarantiaTOFromSession(request);
    		GarantiaHeader garantia   = null;
    		
    		// Verificamos se a garantia veio pelo TO ou por id
    		if ( garantiaTO != null ) {
    			
    			garantia = garantiaTO.getGarantia();
    			
    		} else {
    			
    			garantiaTO = new GarantiaTO();
    			garantia = garantiaHeaderBusiness.get(Integer.valueOf(garantiaId));
    			garantiaTO.setGarantia(garantia);
    			YmSessionHelper.setGarantiaTOToSession(request, garantiaTO);
    			
    		}    		
    		
    		//GarantiaHeader garantia = garantiaHeaderBusiness.get(Integer.valueOf(garantiaId));
    		Lote lote = garantia.getLote();
    		String isEdit = "true";
    		
    		GarantiaLineId garantiaLineId = new GarantiaLineId(Integer.valueOf(garantiaId), Long.valueOf(lineId));
			//GarantiaLine garantiaLine = garantiaLineBusiness.getGarantiaLine(garantiaLineId);
    		GarantiaLine garantiaLine = garantiaHeaderBusiness.getGarantiaLine(garantiaLineId, garantia.getOrganizationId());
			
			if ( garantiaLine == null ) {
				
				errorMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("garantia.peca.msg.error.notLocalized", lineId) );
	            super.addProblemMessages(request, errorMessages);	
				
			} else {				
				
				ControllerHelper.prepare(garantiaLine, (Long)usuario.getIdentifier());
				garantiaLineBusiness.remove(garantiaLine);
				
				successMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("garantia.peca.msg.success.removed") );
	            super.addSuccessMessages(request, successMessages);
				
			}
			
			pecaForm.set( "counter"			, new Long(1) );
			pecaForm.set( "entityId"		, Integer.valueOf(garantiaId) );
			pecaForm.set( "chassi"			, garantia.getModelo() );
			pecaForm.set( "isEdit"			, isEdit);
			pecaForm.set( "numeroOS"		, garantia.getNumeroOS() );
			pecaForm.set( "dataAberturaOS"  , garantia.getMaskedDataAberturaOS());
			pecaForm.set( "dataFechamentoOS", garantia.getMaskedDataFechamentoOS());
			pecaForm.set( "loteId"		    , lote.getEntityId());
			/*
			String descricao = garantiaLineBusiness.listDescricaoDefeito(garantia);
			
			if ( descricao != null ) {
				pecaForm.set( "descricaoDefeito",  descricao);
			} else			
				pecaForm.set( "descricaoDefeito", new String() );			
			*/
			//pecaForm.set( "options"         , itemBusiness.listByChassi( garantia.getModelo(),(Long)lote.getLinha().getEntityId() ) );
			//pecaForm.set( "filledLines", null );
			
			if ( (lote.getStatusLote().getEntityId().equals(new Long(4))) || "true".equals(isEdit) ) {
				
				pecaForm.set( "histDescricaoDefeito", garantiaLineBusiness.listDescricaoDefeito(garantia) );
				pecaForm.set( "histParecerTecnico"  , garantiaLineBusiness.listParecerTecnico(garantia) );
				
			}
			
			List pecas = garantiaHeaderBusiness.listPecas(garantia);
			
			pecaForm.set( "filledLines", pecas );
			
			// Enviamos este parâmetro para deixar o box enviar checado e desabilitado
			if ( "SP".equalsIgnoreCase(usuario.getConcessionaria().getUf()) ||"RJ".equalsIgnoreCase(usuario.getConcessionaria().getUf()) )
				
				if ( !isZeroKm )
					request.setAttribute("isCheckEnviar", new Boolean(true) );
				else
					request.setAttribute("isCheckEnviar", new Boolean(false) );
			else
				request.setAttribute("isCheckEnviar", new Boolean(false) );
			
			request.setAttribute("statusLoteId", lote.getStatusLote().getEntityId() );
			request.setAttribute("consulta"    , new Boolean(false) );
			request.setAttribute("isZeroKm"    , new Boolean(isZeroKm) );
			request.setAttribute("isEdit"      , new Boolean(isEdit) );
			
			return mapping.findForward("_form");
			
		} catch (BeansException bExp) {
			throw new ControllerException( bExp );
			
		} catch (NumberFormatException nfExp) {
			throw new ControllerException( nfExp );
			
		} catch (BusinessException bExp) {
			throw new ControllerException( bExp );
			
		}  catch ( InvalidSessionException isExp ) {
    		
    		throw new ControllerException(isExp);
    		
		}   
    }
	
    /** Tarefa: Salvar peças da garantia.
     * 
     *  @param mapping
     *      O objeto <code>ActionMapping</code> utilizado para
     *      selecionar esta instância.
     *  @param form
     *      Um objeto (opcional) do tipo <code>ActionForm</code>
     *      utilizado nesta requisição.
     *  @param request
     *      A requisição <code>HTTP</code> que estamos processando.
     *  @param response
     *      A resposta <code>HTTP</code> que estamos processando.
     * 
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */    
    public ActionForward save( ActionMapping mapping
                             , ActionForm form
                             , HttpServletRequest request
                             , HttpServletResponse response) throws ControllerException {
    	
    	try {
    		
    		boolean saved = false;
    		boolean error = false;
    		
			ItemBusiness             itemBusiness         = (ItemBusiness) springContext.getBean("itemBO");
			GarantiaHeaderBusiness   gHeaderBusiness      = (GarantiaHeaderBusiness) springContext.getBean("garantiaHeaderBO");
			GarantiaLineBusiness     gLineBusiness        = (GarantiaLineBusiness) springContext.getBean("garantiaLineBO");	
						
			DynaActionForm pecaForm        = (DynaActionForm) form;
			ActionMessages 			successMessages = new ActionMessages();
			ActionMessages          warningMessages = new ActionMessages();
			ActionMessages          errorMessages   = new ActionMessages();
			
			Long    counter        = (Long) pecaForm.get("counter");
			Integer garantiaId     = (Integer) pecaForm.get("entityId");
			String  modelo         = pecaForm.getString("chassi");
			Integer loteId  	   = (Integer) pecaForm.get("loteId");
			boolean isEdit         = pecaForm.getString("isEdit").equals("false") ? false : true; 
			boolean onlyService    = pecaForm.getString("onlyService").equals("false") ? false : true;
			
			Usuario usuario = (Usuario) UserHelper.getBoundedUser(request.getSession());
			
			List    pecas          = new ArrayList();
			
			//			 Retornamos a garantia que está na sessão
    		GarantiaTO     garantiaTO = YmSessionHelper.getGarantiaTOFromSession(request);
    		GarantiaHeader garantia   = null; 
    		
    		if ( garantiaTO != null ){
    			
    			garantia = garantiaTO.getGarantia();
    			
    		} else {
    			
    			garantia = gHeaderBusiness.get(garantiaId);
    			garantiaTO = new GarantiaTO();
    			garantiaTO.setGarantia(garantia);
    			YmSessionHelper.setGarantiaTOToSession(request, garantiaTO);
    			
    		}
			
			if ( garantia == null ) {
				
				warningMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("garantia.msg.error.notLocalized", garantiaId) );
	            super.addWarningMessages(request, warningMessages);
	            		            
	            //log.info( "Garantia não encontrada!" );
	            
	            return mapping.findForward("_form");
				
			}
			
			// Criamos um objeto para armazenar temporáriamente a peça causadora do problema			
			log.info( "COUNTER: " + counter );
			
			for ( long i = 1; i < counter.longValue(); i++ ) {

				//log.info( "LINHA: " + i );
				
				String itemId       = request.getParameter( "itemId_"   + i );
				String itemCode     = request.getParameter( "itemCode_" + i );
				String descItem     = request.getParameter( "descItem_" + i );
				String qtdPeca      = request.getParameter( "qtdPeca_"  + i );
				String enviarPeca   = request.getParameter( "enviar_"   + i );
				String cobrarPeca   = request.getParameter( "cobrar_"   + i );
				String faltantePeca = request.getParameter( "faltante_" + i );
				String causadoraPeca = request.getParameter( "causadora_" + i );
				
				boolean isPeca     = itemCode != null && itemCode.length() != 0;							
				boolean isQtde     = !"".equals(qtdPeca) ? Integer.parseInt(qtdPeca) > 0 : false;
				boolean isEnviar   = enviarPeca   != null;
				boolean isCobrar   = cobrarPeca   != null;
				boolean isFaltante = faltantePeca != null;
				Boolean isCausadora = causadoraPeca != null ? Boolean.TRUE: Boolean.FALSE;
				
				PecaTO peca = new PecaTO();
				
				// Só iniciamos o o processo se realmente existir uma peça para
				// esta posição do array. Isso garante que posições que foram 
				// removidas pelo usuário não sejam acessadas pelo programa.
				if ( isPeca && isQtde && (!itemId.equals("null")) ) {
					
					peca.setPecaId( Integer.parseInt(itemId) );	
					peca.setPecaCode(itemCode);
					peca.setDescricao( descItem );
					// Validamos a qunatidade fornecida
					if ( isQtde )
						peca.setQuantidade( Integer.parseInt(qtdPeca) );
					else
						peca.setQuantidade( 0 );
					
					peca.setEnviar( isEnviar ); // Se for nulo, não foi selecionado pelo usuário.
					peca.setCobrar( isCobrar ); // Se for nulo, não foi selecionado pelo usuário.
					peca.setFaltante( isFaltante ); // Se for nulo, não foi selecionado pelo usuário.
					peca.setCausadora( isCausadora ); // Se for a primeira peça, consideramos como sendo causadora.
					pecas.add( peca );
					
					//log.info("Peça:" + peca.getPecaId() + " - Código:" + itemCode);
					// Recupera o item pelo seu id e pelo Empresa 91 
					Item item = itemBusiness.getByOrg( new Long( peca.getPecaId() ), new Long(91) );
					
					//log.info("Achou o Item?" + (item != null));
					
					//log.info(" OrganizationId da peça:" + item.getOrganizationId() );
					
					// Quando for alteração, não é necessário verificar se já foi cobrada a peça
					if ( !isEdit ) {
						// Verificamos se a peça já foi cobrada.
						if ( gHeaderBusiness.wasCharged( peca.getPecaId(), modelo ) ) {
	
							AlertGarantia alerta = new AlertGarantia();
							//alerta.setCompositeEntityId(new Long(52), garantiaId);
							alerta.setAlertGarantiaSeveridade( AlertGarantia.SEVERIDADE_WARNING );
							alerta.setAlertGarantiaKey("garantia.msg.garantiarealizada");
							ControllerHelper.prepare(alerta, (Long) usuario.getEntityId() );
							
							warningMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("garantia.msg.garantiarealizada") );
							super.addWarningMessages(request, warningMessages);
							
							//log.info( "Esta linha já foi cobrada." );
	
						}
						
					}
					
					// ******************************************************************************* //
					// *   Validação da quantidade de peças solicitadas para o modelo do produto	 * //
					// ******************************************************************************* //
					int     qtdePecas = 0;
					
					if ( !Item.SEGMENTO_COMPLEMENTAR.equalsIgnoreCase(item.getSegmento()) ) {
						
						qtdePecas  = itemBusiness.getQtdePecaByModelo(garantia.getModelo(), item.getItemCode());
						
						if ( qtdePecas == 0 ) {
							
							//log.info("---> Quantidade de peças igual a Zero -  Enviando mensagem de peça não pertence ao modelo!");
							
							AlertGarantia alerta = new AlertGarantia();
							alerta.setAlertGarantiaSeveridade( AlertGarantia.SEVERIDADE_ERROR );
							alerta.setAlertGarantiaKey("garantia.msg.error.notPertenceModel");
							ControllerHelper.prepare(alerta, (Long) usuario.getEntityId() );
														
							errorMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("garantia.msg.error.notPertenceModel", ("Linha " + i)) );
							super.addProblemMessages(request, errorMessages);
							
							error = true;
							
							//log.info( "Peça não pertence ao modelo!" );
							
						// Se o produto for Nacional, validamos a quantidade de peças solicitadas com a existente no produto.
						} else {
							
							//log.info( "Quantidade de peças solicitadas:" + peca.getQuantidade() + " - Peças do Produto:" +  qtdePecas);
							if (  peca.getQuantidade() > qtdePecas ) {
								
								AlertGarantia alerta = new AlertGarantia();
								alerta.setAlertGarantiaSeveridade( AlertGarantia.SEVERIDADE_ERROR);
								alerta.setAlertGarantiaKey("garantia.msg.error.qtdePecas");
								ControllerHelper.prepare(alerta, (Long) usuario.getEntityId() );
								
								errorMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("garantia.msg.error.qtdePecas", ("Linha " + i )) );
								super.addProblemMessages(request, errorMessages);
								
								error = true;
								
								//log.info( "Quantidade de peças solicitadas maior que a existente no produto!" );
								
							}
						} 
					}
					//log.info( "Obtendo tabela de preços..." );
					
					TabelaPrecoId tabelaPrecoId = new TabelaPrecoId(new Long(91), new Long(peca.getPecaId()));
					TabelaPreco   tabelaPreco   = gLineBusiness.getTabelaPreco(tabelaPrecoId);
					
					//Recuperamos o Fator de Garantia pelo estado da concessionária
					FatorGarantia fatorGarantia = gLineBusiness.getFatorGarantia( usuario.getConcessionaria().getUf());
					
					
					//log.info( "Fator de Garantia:" + (fatorGarantia != null ? fatorGarantia.getEstado() : "NULL") );
					
					GarantiaLine garantiaLine = null;
					
					//log.info( "Garantia:" + garantia.getEntityId() + " - Linha:" + i );
					
					if ( isEdit ) {
						
						GarantiaLineId garantiaLineId = new GarantiaLineId(garantia.getEntityId(), new Long(i));
						//garantiaLine = gHeaderBusiness.getGarantiaLine(garantiaLineId);	
						
						garantiaLine = garantia.getGarantiaLine(garantiaLineId);
						
						//System.out.println("Recuperou? " + (garantiaLine != null) );
						
						if ( garantiaLine == null ) {
							
							garantiaLine = new GarantiaLine();
							garantiaLine.setCompositeEntityId( garantia.getEntityId(), new Long(i) );	
						}
						
					} else {
						
						garantiaLine = new GarantiaLine();
						garantiaLine.setCompositeEntityId( null, new Long(i) );
						
					}
					
					garantiaLine.setQuantidade( new Integer( peca.getQuantidade() ) );
					garantiaLine.setItem( item );
					garantiaLine.setEnviaPeca( peca.isEnviar() ? "S" : "N" );
					garantiaLine.setCobraPeca( peca.isCobrar() ? "S" : "N" );
					garantiaLine.setPecaFaltante( peca.isFaltante() ? "S" : "N" );
					garantiaLine.setPecaCausadora( peca.isCausadora() ? "S" : "N" );
					
					// Implementado por solicitação do Pedro em 30/06
					if ( tabelaPreco == null ) {
						
						warningMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("garantia.msg.tabelanaoencontrada") );
			            super.addWarningMessages(request, warningMessages);
			            		            
			            //log.info( "Tabela de preços não encontrada." );
			            
			            garantiaLine.setFatorGarantiaId( new Long(55) );
						
					} else {
						
						//log.info( "Tabela de preços encontrada." );
						
						if ( fatorGarantia == null ) {		
							
							//log.info( "Fator de Garantia não encontrado." );
							garantiaLine.setFatorGarantiaId( new Long(55) );
							garantiaLine.setValorPecaGarantia( tabelaPreco.getPrecoGarantia() * 1.33D );
							
						} else {
							
							garantiaLine.setFatorGarantiaId( (Long)fatorGarantia.getEntityId() );
							garantiaLine.setValorPecaGarantia( 
																NumberUtils.round( 
																					(tabelaPreco.getPrecoUnitario() * fatorGarantia.getFatorGarantia())
																					, 2
																				 ) 
															 );
							
						}
						
						garantiaLine.setValorPrecoFob(  tabelaPreco.getPrecoFOB() );
						garantiaLine.setValorCustoPadraoSP( tabelaPreco.getPrecoUnitario() );
						garantiaLine.setValorPrecoSugerido( tabelaPreco.getPrecoPublico() );
						
					}
					
					ControllerHelper.prepare( garantiaLine, (Long)usuario.getEntityId() );	
					garantiaLine.setCriadoPor((Long)usuario.getEntityId());
					garantiaLine.setDataCriacao(new Date());
										
					garantiaTO.addGarantiaLine(garantiaLine);
					
					//log.info( "Garantia incluída na lista!" );
					
					saved = true;
					
				} else {
										
					if ( itemId.equals("null") && !onlyService ) {
						
						//log.info(" Peça inválida!" );
						
						errorMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("garantia.msg.error.pecaNaoLocalizada") );
			            super.addProblemMessages(request, errorMessages);
			            
			            error = true;
			            
					} else if ( !isQtde && !onlyService) {
						// Quando for a primeira linha será erro, para as demais warning
						if ( i == 1 ) {
						
							errorMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("garantia.peca.msg.error.qtdeObrigatorio", String.valueOf(i)) );
							super.addProblemMessages(request, errorMessages);
							
							error = true;
							
						} else {
							
							warningMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("garantia.peca.msg.error.qtdeObrigatorio", String.valueOf(i)) );
				            super.addWarningMessages(request, warningMessages);
							
						}
						
					} /* else if ( !isEnviar && !isCobrar)  {
						
						errorMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("garantia.peca.msg.error.pecaCheckObrigatorio", String.valueOf(i)) );
						super.addProblemMessages(request, errorMessages);	
						
						error = true;
						
					}*/
					if ( i > 1 && !onlyService) {
						
						warningMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("garantia.msg.linhaNaoInserida", String.valueOf(i)) );
			            super.addWarningMessages(request, warningMessages);
			            
			            error = true;
						
					} else if ( !onlyService ){
					
						errorMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("garantia.msg.linhaNaoInserida", String.valueOf(i)) );
						super.addProblemMessages(request, errorMessages);
						
						error = true;
						
					}
					
					saved = true;
					
				}

			} // Fim do FOR
									
			// Se não existir erro, gravamos			
			if ( !error && saved ) {
				
				AlertGarantia saveAlert = gHeaderBusiness.saveAll(garantiaTO, isEdit);
				
				if ( saveAlert != null ) {
					
					if ( saveAlert.getAlertGarantiaKey().indexOf("error") != -1 ) {
						
						errorMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage(saveAlert.getAlertGarantiaKey()) );
						super.addProblemMessages(request, errorMessages);					
						saved = false;
					} else {
						//Salvamos os arquivos enviados
						FormFile uploadFile1 = (FormFile) pecaForm.get("uploadFile1");
						FormFile uploadFile2 = (FormFile) pecaForm.get("uploadFile2");
						FormFile uploadFile3 = (FormFile) pecaForm.get("uploadFile3");
						FormFile uploadFile4 = (FormFile) pecaForm.get("uploadFile4");
						FormFile uploadFile5 = (FormFile) pecaForm.get("uploadFile5");
						FormFile uploadFile6 = (FormFile) pecaForm.get("uploadFile6");
						FormFile uploadFile7 = (FormFile) pecaForm.get("uploadFile7");
						FormFile uploadFile8 = (FormFile) pecaForm.get("uploadFile8");
						
						GarantiaArquivoBusiness gArquivoBusiness = (GarantiaArquivoBusiness) springContext.getBean("garantiaArquivoBO");						
						ParametroSistema filesMaxSize = gArquivoBusiness.getByParameterSystem(ParametroSistema.FILE_MAX_SIZE_UPLOAD_GARANTIA_NAUTICA);
						
						int fileMaxSizeParam =  Integer.parseInt(filesMaxSize.getValorParametro());						
						AlertGarantia alert = null;
						
						if ( uploadFile1 != null && uploadFile1.getFileName() != null && uploadFile1.getFileSize() > 0 ) {
							
							alert  = this.saveFile(fileMaxSizeParam, usuario, uploadFile1, garantia);
							
							if ( alert.getAlertGarantiaKey().indexOf("warning") != -1 ) {					    				
								warningMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( alert.getAlertGarantiaKey(), alert.getParam()) );
		    					super.addWarningMessages(request, warningMessages);				    				
		    				} else if ( alert.getAlertGarantiaKey().indexOf("error") != -1 ) {					    				
								errorMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( alert.getAlertGarantiaKey(), alert.getParam()) );
		    					super.addProblemMessages(request, errorMessages);				    				
		    				}  	
						}
						
						if ( uploadFile2 != null && uploadFile2.getFileName() != null && uploadFile2.getFileSize() > 0 ) {
							
							alert  = this.saveFile(fileMaxSizeParam, usuario, uploadFile2, garantia);
							
							if ( alert.getAlertGarantiaKey().indexOf("warning") != -1 ) {					    				
								warningMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( alert.getAlertGarantiaKey(), alert.getParam()) );
		    					super.addWarningMessages(request, warningMessages);				    				
		    				} else if ( alert.getAlertGarantiaKey().indexOf("error") != -1 ) {					    				
								errorMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( alert.getAlertGarantiaKey(), alert.getParam()) );
		    					super.addProblemMessages(request, errorMessages);				    				
		    				}  		
						}
						
						if ( uploadFile3 != null && uploadFile3.getFileName() != null && uploadFile3.getFileSize() > 0 ) {
							 
							alert  = this.saveFile(fileMaxSizeParam, usuario, uploadFile3, garantia);
							
							if ( alert.getAlertGarantiaKey().indexOf("warning") != -1 ) {					    				
								warningMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( alert.getAlertGarantiaKey(), alert.getParam()) );
		    					super.addWarningMessages(request, warningMessages);				    				
		    				} else if ( alert.getAlertGarantiaKey().indexOf("error") != -1 ) {					    				
								errorMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( alert.getAlertGarantiaKey(), alert.getParam()) );
		    					super.addProblemMessages(request, errorMessages);				    				
		    				}  		
						}
						
						if ( uploadFile4 != null && uploadFile4.getFileName() != null && uploadFile4.getFileSize() > 0 ) {
							
							alert  = this.saveFile(fileMaxSizeParam, usuario, uploadFile4, garantia);
							
							if ( alert.getAlertGarantiaKey().indexOf("warning") != -1 ) {					    				
								warningMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( alert.getAlertGarantiaKey(), alert.getParam()) );
		    					super.addWarningMessages(request, warningMessages);				    				
		    				} else if ( alert.getAlertGarantiaKey().indexOf("error") != -1 ) {					    				
								errorMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( alert.getAlertGarantiaKey(), alert.getParam()) );
		    					super.addProblemMessages(request, errorMessages);				    				
		    				}  	
						}
						
						if ( uploadFile5 != null && uploadFile5.getFileName() != null && uploadFile5.getFileSize() > 0 ) {
							
							alert  = this.saveFile(fileMaxSizeParam, usuario, uploadFile5, garantia);
							
							if ( alert.getAlertGarantiaKey().indexOf("warning") != -1 ) {					    				
								warningMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( alert.getAlertGarantiaKey(), alert.getParam()) );
		    					super.addWarningMessages(request, warningMessages);				    				
		    				} else if ( alert.getAlertGarantiaKey().indexOf("error") != -1 ) {					    				
								errorMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( alert.getAlertGarantiaKey(), alert.getParam()) );
		    					super.addProblemMessages(request, errorMessages);				    				
		    				}  		
						}
						
						if ( uploadFile6 != null && uploadFile6.getFileName() != null && uploadFile6.getFileSize() > 0 ) {
							 
							alert  = this.saveFile(fileMaxSizeParam, usuario, uploadFile6, garantia);
							
							if ( alert.getAlertGarantiaKey().indexOf("warning") != -1 ) {					    				
								warningMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( alert.getAlertGarantiaKey(), alert.getParam()) );
		    					super.addWarningMessages(request, warningMessages);				    				
		    				} else if ( alert.getAlertGarantiaKey().indexOf("error") != -1 ) {					    				
								errorMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( alert.getAlertGarantiaKey(), alert.getParam()) );
		    					super.addProblemMessages(request, errorMessages);				    				
		    				}  		
						}
						
						if ( uploadFile7 != null && uploadFile7.getFileName() != null && uploadFile7.getFileSize() > 0 ) {
							
							alert  = this.saveFile(fileMaxSizeParam, usuario, uploadFile7, garantia);
							
							if ( alert.getAlertGarantiaKey().indexOf("warning") != -1 ) {					    				
								warningMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( alert.getAlertGarantiaKey(), alert.getParam()) );
		    					super.addWarningMessages(request, warningMessages);				    				
		    				} else if ( alert.getAlertGarantiaKey().indexOf("error") != -1 ) {					    				
								errorMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( alert.getAlertGarantiaKey(), alert.getParam()) );
		    					super.addProblemMessages(request, errorMessages);				    				
		    				}  	
						}
						
						if ( uploadFile8 != null && uploadFile8.getFileName() != null && uploadFile8.getFileSize() > 0 ) {
							
							alert  = this.saveFile(fileMaxSizeParam, usuario, uploadFile8, garantia);
							
							if ( alert.getAlertGarantiaKey().indexOf("warning") != -1 ) {					    				
								warningMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( alert.getAlertGarantiaKey(), alert.getParam()) );
		    					super.addWarningMessages(request, warningMessages);				    				
		    				} else if ( alert.getAlertGarantiaKey().indexOf("error") != -1 ) {					    				
								errorMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( alert.getAlertGarantiaKey(), alert.getParam()) );
		    					super.addProblemMessages(request, errorMessages);				    				
		    				}  		
						}
						
					}
					
				} else {
					// Removemos o TO da sessão do usuário
					YmSessionHelper.removeFromSession(request, YmSessionHelper.SESSION_GARANTIATO);
				}
				
				//log.info( "Peças salvas..." );
			} else {
				// Garantimos que o parâmetro save chegue com false
				saved = false;
				// Vamos zerar os objetos de peças incluídos na lista
				garantiaTO.setListGarantiaLine(new ArrayList());
				
			}
			
			//log.info( "Quantidade de Peças:" + pecas.size() );
			
			//log.info( "Modelo:" + modelo );
			
			List options = itemBusiness.listByChassi( modelo, (Long)garantia.getLote().getLinha().getEntityId() );
			
			pecaForm.set( "counter"    , new Long(1) );
			pecaForm.set( "entityId"   , garantia.getEntityId() );
			pecaForm.set( "chassi"     , modelo );
			pecaForm.set( "isEdit"     , isEdit ? "true" : "false" );
			pecaForm.set( "loteId"     , loteId);
			
			//log.info( "Options:" + options.size());
			
			pecaForm.set( "options"    , options );
			pecaForm.set( "filledLines", pecas );
			pecaForm.set( "numeroOS"   , garantia.getNumeroOS() );
			pecaForm.set( "dataAberturaOS"  , garantia.getMaskedDataAberturaOS());
			pecaForm.set( "dataFechamentoOS", garantia.getMaskedDataFechamentoOS());
			
			request.setAttribute("isEdit"       , new Boolean(isEdit) ); 
			request.setAttribute("statusLoteId" , garantia.getLote().getStatusLote().getEntityId() );
			request.setAttribute("consulta"     , new Boolean(false) );
			
			// Enviamos este parâmetro para deixar o box enviar checado e desabilitado
			if ( "SP".equalsIgnoreCase(usuario.getConcessionaria().getUf()) ||"RJ".equalsIgnoreCase(usuario.getConcessionaria().getUf()) )
				request.setAttribute("isCheckEnviar"      , new Boolean(true) );		
			else
				request.setAttribute("isCheckEnviar"      , new Boolean(false) );	
			
			// Aqui verificamos se é produto novo, tanto para moto como para naútica utilizamos
			// isZeroKm
			if ( garantia.getQuilometragem() != null ) {
				
				request.setAttribute("isZeroKm" 		, garantia.getQuilometragem().equals(new Long(0)) ? new Boolean(true):new Boolean(false));
			
			} else if ( garantia.getDiasUso() != null ) {
				
				request.setAttribute("isZeroKm" 		, garantia.getDiasUso().equals(new Long(0)) ? new Boolean(true):new Boolean(false));
				
			} else {
				
				request.setAttribute("isZeroKm", new Boolean(false));
				
			}				
			
			if ( saved ) {
				
				successMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("form.msg.saved") );
            	super.addSuccessMessages(request, successMessages);
            	
	            this.setJavaScriptExecuter(request, "redirectGar(" + loteId + ")");
            	
			}
						
			return mapping.findForward("_form");
			
    	} catch (BeansException bExp) {
    		YmSessionHelper.removeFromSession(request, YmSessionHelper.SESSION_GARANTIATO);
			throw new ControllerException( bExp );
			
		} catch (NumberFormatException nfExp) {
			YmSessionHelper.removeFromSession(request, YmSessionHelper.SESSION_GARANTIATO);
			throw new ControllerException( nfExp );
			
		} catch (BusinessException bExp) {
			YmSessionHelper.removeFromSession(request, YmSessionHelper.SESSION_GARANTIATO);
			throw new ControllerException( bExp );
			
		} catch (InvalidSessionException isExp) {
			YmSessionHelper.removeFromSession(request, YmSessionHelper.SESSION_GARANTIATO);
			throw new ControllerException( isExp );
			
		}

    }  
    
    /**
     * 
     * @param fileMaxSizeParam
     * @param usuario
     * @param formFile
     * @param garantiaId
     * @return
     */
    private AlertGarantia saveFile(int fileMaxSizeParam, Usuario usuario, FormFile formFile, GarantiaHeader garantia) {   	
    	
    	GarantiaArquivo garantiaArquivo = null;
    	AlertGarantia alert  = null;
    	try {
			//Upload do arquivo 
			if(formFile.getFileSize() <= fileMaxSizeParam) {	
				GarantiaArquivoBusiness gArquivoBusiness = (GarantiaArquivoBusiness) springContext.getBean("garantiaArquivoBO");
				garantiaArquivo = GarantiaArquivo.getInstance(formFile, (Long)usuario.getConcessionaria().getEntityId(), garantia);	
				if(garantiaArquivo != null) {				
					//ControllerHelper.prepare( garantiaArquivo, (Long)usuario.getEntityId() );
					alert  = gArquivoBusiness.saveFile(garantiaArquivo, usuario);				
				} else {
					alert = new AlertGarantia();
					alert.setAlertGarantiaKey("garantia.msg.warning.fileException");
					alert.setAlertGarantiaText("O upload do arquivo não foi realizado, arquivo inválido!");
					alert.setParam(formFile.getFileName());
				}		
			} else {
				alert = new AlertGarantia();
				alert.setAlertGarantiaKey("garantia.msg.warning.fileMaxSize");
	            alert.setAlertGarantiaText("Não foi possível gravar o arquivo! O tamanho do arquivo ultrapassou o limite!");
	            alert.setParam(formFile.getFileName());
			}		
			
    	}catch(FileNotFoundException fnf){
    		alert = new AlertGarantia();
			alert.setAlertGarantiaKey("garantia.msg.error.Permission");
			alert.setAlertGarantiaText("O upload do arquivo não foi realizado, arquivo não encontrado!");
			alert.setParam(formFile.getFileName());
    	}catch(IOException  ioEx){
    		alert = new AlertGarantia();
			alert.setAlertGarantiaKey("garantia.msg.error.Permission");
			alert.setAlertGarantiaText("O upload do arquivo não foi realizado, erro na gravação!");
			alert.setParam(formFile.getFileName());    	
    	}catch(FileException flExc) {
    		alert = new AlertGarantia();
			alert.setAlertGarantiaKey("garantia.msg.error.Permission");
			alert.setAlertGarantiaText("O upload do arquivo não foi realizado, arquivo inválido!");
			alert.setParam(formFile.getFileName());
    	}catch(BusinessException bsExc) {
    		alert = new AlertGarantia();
			alert.setAlertGarantiaKey("garantia.msg.error.Permission");
			alert.setAlertGarantiaText("O upload do arquivo não foi realizado, arquivo inválido!");
			alert.setParam(formFile.getFileName());
    	}
		return alert;
    }
    
    /** Tarefa: Retorna o fluxo de bytes para download do arquivo selecionado.
     * 
     *  @param mapping
     *      O objeto <code>ActionMapping</code> utilizado para
     *      selecionar esta instância.
     *  @param form
     *      Um objeto (opcional) do tipo <code>ActionForm</code>
     *      utilizado nesta requisição.
     *  @param request
     *      A requisição <code>HTTP</code> que estamos processando.
     *  @param response
     *      A resposta <code>HTTP</code> que estamos processando.
     *
     * 
     */ 
    public ActionForward download(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception {
    	    
		ActionMessages          errorMessages   = new ActionMessages();
		
    	Integer fileId = request.getParameter("fileId") != null ? Integer.valueOf(request.getParameter("fileId")):null;
    	
    	try {
    		
    		GarantiaArquivoBusiness gArquivoBusiness = (GarantiaArquivoBusiness) springContext.getBean("garantiaArquivoBO");
    			
    		GarantiaArquivo gArq = gArquivoBusiness.get(fileId);
    		
    		if(gArq != null) {
    			
    			byte[] bytes;
    			
    			if(Linha.TIPO_MOTOCICLETA.equals(gArq.getLinhaId())){
    				bytes = gArquivoBusiness.getContentMoto(gArq);    				
    			} else {
    				bytes = gArquivoBusiness.getContentNtc(gArq);
    			}

    			if(bytes.length > 0 ) {
    				
    				ServletOutputStream ouputStream = response.getOutputStream(); 

    				response.setContentType(gArq.getContentType());
    				response.setHeader("Content-Disposition", "attachment; filename=" + gArq.getFilename());

    				response.setContentLength( bytes.length); 

    				ouputStream.write( bytes, 0,  bytes.length);

    				ouputStream.flush();
    				ouputStream.close();

    			}
    			
    			/*
		        File file = FileUtils.getFileByName(gArq.getFilePath());
		        		        	
		        if ( file.exists() ) { 
		        		
		        		ServletOutputStream ouputStream = response.getOutputStream(); 
		        		
			        	response.setContentType(gArq.getContentType());
			        	response.setHeader("Content-Disposition", "attachment; filename=" + gArq.getFilename());
		    	
			        	byte[] bytes = FileHelper.getBytesFromFile(file);
			        	response.setContentLength( bytes.length); 
		    	
			        	ouputStream.write( bytes, 0,  bytes.length);
			        	
			        	ouputStream.flush();
			    		ouputStream.close();
			        	
		        } else {
		        	errorMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("documento.msg.error.fileNotFount") );
		        	super.addProblemMessages(request, errorMessages);

		        }
			*/
    		} else {

    			errorMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("documento.msg.error.fileNotFount") );
    			super.addProblemMessages(request, errorMessages);


    		}
    	    		
    	} catch (FileNotFoundException fex){
    	
    		errorMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("documento.msg.error.fileNotFount") );
            super.addProblemMessages(request, errorMessages);
                		
    	}
    
    	return null;
        
    }  
    
    /** Tarefa: Remove um arquivo.
     * 
     *  @param mapping
     *      O objeto <code>ActionMapping</code> utilizado para
     *      selecionar esta instância.
     *  @param form
     *      Um objeto (opcional) do tipo <code>ActionForm</code>
     *      utilizado nesta requisição.
     *  @param request
     *      A requisição <code>HTTP</code> que estamos processando.
     *  @param response
     *      A resposta <code>HTTP</code> que estamos processando.
     * 
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */    
    public ActionForward removeFile( ActionMapping mapping
                             , ActionForm form
                             , HttpServletRequest request
                             , HttpServletResponse response) throws ControllerException {
    	
    	DynaActionForm pecaForm = (DynaActionForm) form;
		pecaForm.initialize(mapping);  
    	
    	ActionMessages 			successMessages = new ActionMessages();
		ActionMessages          errorMessages   = new ActionMessages();
    	
    	String garantiaId = request.getParameter("entityId");
    	boolean isZeroKm         = (new Boolean(request.getParameter("isZeroKm"))).booleanValue();
    	
    	Integer fileId = request.getParameter("fileId") != null ? Integer.valueOf(request.getParameter("fileId")):null;
    	
    	try {
    		
    		Usuario usuario = (Usuario) UserHelper.getBoundedUser(request.getSession());
    		GarantiaLineBusiness garantiaLineBusiness = (GarantiaLineBusiness) springContext.getBean("garantiaLineBO");
    		GarantiaHeaderBusiness garantiaHeaderBusiness = (GarantiaHeaderBusiness) springContext.getBean("garantiaHeaderBO");
    		

    		GarantiaArquivoBusiness gArquivoBusiness = (GarantiaArquivoBusiness) springContext.getBean("garantiaArquivoBO");
    		
    		// Retornamos a garantia que está na sessão
    		GarantiaTO     garantiaTO = YmSessionHelper.getGarantiaTOFromSession(request);
    		GarantiaHeader garantia   = null;
    		
    		// Verificamos se a garantia veio pelo TO ou por id
    		if ( garantiaTO != null ) {
    			
    			garantia = garantiaTO.getGarantia();
    			
    		} else {
    			
    			garantiaTO = new GarantiaTO();
    			garantia = garantiaHeaderBusiness.get(Integer.valueOf(garantiaId));
    			garantiaTO.setGarantia(garantia);
    			YmSessionHelper.setGarantiaTOToSession(request, garantiaTO);
    			
    		}    		
    		
    		Lote lote = garantia.getLote();
    		String isEdit = "true";
    		
    		GarantiaArquivo gArq = gArquivoBusiness.get(fileId);
    		    		
			if( gArq == null ) {
				
				errorMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("garantia.file.msg.error.removed") );
	            super.addProblemMessages(request, errorMessages);	
				
			} else {			
				

	    		String fileName = gArq.getFilename();
	    		
	    		try{
	    			gArquivoBusiness.remove(gArq);
	    			
	    			successMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("garantia.file.msg.success.removed", fileName) );
		            super.addSuccessMessages(request, successMessages);
	    		    		
	    		}catch(BusinessException bexc) {
	    			errorMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("garantia.file.msg.error.removed") );
		            super.addProblemMessages(request, errorMessages);
	    		}			
				
			}
			
			pecaForm.set( "counter"			, new Long(1) );
			pecaForm.set( "entityId"		, garantia.getEntityId() );
			pecaForm.set( "chassi"			, garantia.getModelo() );
			pecaForm.set( "isEdit"			, isEdit);
			pecaForm.set( "numeroOS"		, garantia.getNumeroOS() );
			pecaForm.set( "dataAberturaOS"  , garantia.getMaskedDataAberturaOS());
			pecaForm.set( "dataFechamentoOS", garantia.getMaskedDataFechamentoOS());
			pecaForm.set( "loteId"		    , lote.getEntityId());
			
			
			if ( (lote.getStatusLote().getEntityId().equals(new Long(4))) || "true".equals(isEdit) ) {
				
				pecaForm.set( "histParecerTecnico"  , garantiaLineBusiness.listParecerTecnico(garantia) );
				
			}
			
			List pecas = garantiaHeaderBusiness.listPecas(garantia);
			
			pecaForm.set( "filledLines", pecas );
			
			try {	
				if(garantia.getEntityId() != null ) {
					gArquivoBusiness = (GarantiaArquivoBusiness) springContext.getBean("garantiaArquivoBO");				
									
					pecaForm.set( "listFiles", gArquivoBusiness.listFile((Integer)garantia.getEntityId()));
				} else {
					pecaForm.set( "listFiles", null);
				}				
			}catch(FileException flExp){
				pecaForm.set( "listFiles", null);
			}catch(Exception exc){
				pecaForm.set( "listFiles", null);
			}
			
			// Enviamos este parâmetro para deixar o box enviar checado e desabilitado
			if ( "SP".equalsIgnoreCase(usuario.getConcessionaria().getUf()) ||"RJ".equalsIgnoreCase(usuario.getConcessionaria().getUf()) )
				
				if ( !isZeroKm )
					request.setAttribute("isCheckEnviar", new Boolean(true) );
				else
					request.setAttribute("isCheckEnviar", new Boolean(false) );
			else
				request.setAttribute("isCheckEnviar", new Boolean(false) );
			
			request.setAttribute("statusLoteId", lote.getStatusLote().getEntityId() );
			request.setAttribute("consulta"    , new Boolean(false) );
			request.setAttribute("isZeroKm"    , new Boolean(isZeroKm) );
			request.setAttribute("isEdit"      , new Boolean(isEdit) );
			
			return mapping.findForward("_form");
			
		} catch (BeansException bExp) {
			throw new ControllerException( bExp );
			
		} catch (NumberFormatException nfExp) {
			throw new ControllerException( nfExp );
			
		} catch (BusinessException bExp) {
			throw new ControllerException( bExp );
			
		}  catch ( InvalidSessionException isExp ) {
    		
    		throw new ControllerException(isExp);
    		
		}   
    }
    
}
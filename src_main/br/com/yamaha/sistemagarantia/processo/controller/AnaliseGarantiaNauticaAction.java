/* Yamaha
 * Copyright (c) 2012 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......GarantiaAction.java
 *   .: Criação.....10 de dezembro, 20:40
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Action para o processo de análise de garantias.
 */
package br.com.yamaha.sistemagarantia.processo.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
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

import br.com.resource.infra.controller.InfraDispatchAction;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.BusinessRuleException;
import br.com.resource.infra.exception.ControllerException;
import br.com.resource.infra.security.exception.InvalidSessionException;
import br.com.resource.infra.security.helper.UserHelper;
import br.com.yamaha.sistemagarantia.business.CupomBusiness;
import br.com.yamaha.sistemagarantia.business.FaturamentoBusiness;
import br.com.yamaha.sistemagarantia.business.GarantiaArquivoBusiness;
import br.com.yamaha.sistemagarantia.business.GarantiaHeaderBusiness;
import br.com.yamaha.sistemagarantia.business.InfoMercadoBusiness;
import br.com.yamaha.sistemagarantia.business.RevisaoBusiness;
import br.com.yamaha.sistemagarantia.business.UsuarioBusiness;
import br.com.yamaha.sistemagarantia.business.exception.FileException;
import br.com.yamaha.sistemagarantia.controller.helper.ControllerHelper;
import br.com.yamaha.sistemagarantia.controller.helper.FileHelper;
import br.com.yamaha.sistemagarantia.model.AlertGarantia;
import br.com.yamaha.sistemagarantia.model.Cupom;
import br.com.yamaha.sistemagarantia.model.Faturamento;
import br.com.yamaha.sistemagarantia.model.GarantiaArquivo;
import br.com.yamaha.sistemagarantia.model.GarantiaArquivoMoto;
import br.com.yamaha.sistemagarantia.model.GarantiaArquivoNtc;
import br.com.yamaha.sistemagarantia.model.GarantiaHeader;
import br.com.yamaha.sistemagarantia.model.GarantiaLine;
import br.com.yamaha.sistemagarantia.model.Linha;
import br.com.yamaha.sistemagarantia.model.Revisao;
import br.com.yamaha.sistemagarantia.model.Servico;
import br.com.yamaha.sistemagarantia.model.ServicoGrupo;
import br.com.yamaha.sistemagarantia.model.Usuario;
import br.com.yamaha.sistemagarantia.model.id.GarantiaLineId;
import br.com.yamaha.sistemagarantia.processo.business.AnaliseGarantiaBusiness;
import br.com.yamaha.sistemagarantia.processo.model.AnaliseGarantiaVO;
import br.com.yamaha.sistemagarantia.utils.ConstantAnaliseGarantia;
import br.com.yamaha.sistemagarantia.utils.FileUtils;

/** Action responsável por fornecer controle às funcionalidades
 *  do módulo de garantias do sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class AnaliseGarantiaNauticaAction extends InfraDispatchAction {

	private static final long serialVersionUID = 1L;

	/** Tarefa: Processo para determinar um parecer da análise da SG para a Concessionária.
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
     *  @throws ControllerException
     *      Se houverem erros, estes serão lançados como uma exception
     *      deste tipo.
     */ 
    public ActionForward parecerSG( ActionMapping mapping
                             , ActionForm form
                             , HttpServletRequest request
                             , HttpServletResponse response) throws ControllerException {
    	   
    	DynaActionForm gAnaliseForm = (DynaActionForm) form; 
    	ActionMessages messagesProblem    = new ActionMessages();
    	ActionMessages messagesSuccess    = new ActionMessages();
    	
    	Boolean isSuccess = Boolean.TRUE;
    	
    	try { 
    		
    		Integer garantiaId 	= gAnaliseForm.get("entityId") 		!= null ? (Integer)gAnaliseForm.get("entityId") : null;
    		Integer loteId     	= gAnaliseForm.get("loteId")   		!= null ? (Integer)gAnaliseForm.get("loteId")   : null;
    		String  parecerCode	= request.getParameter("prcCode") 	!= null ? request.getParameter("prcCode") 		: null;
    		
    		if(garantiaId != null && loteId != null && parecerCode != null && !"".equals(parecerCode)) {
	    		
    			AnaliseGarantiaBusiness  analiseGarantiaBusiness  = (AnaliseGarantiaBusiness)  springContext.getBean("analiseGarantiaBO");
    			Usuario usuario = (Usuario) UserHelper.getBoundedUser(request.getSession());
    			
    			GarantiaHeader garantia	= (GarantiaHeader) analiseGarantiaBusiness.getGarantia(garantiaId);
    			
    			if(garantia != null) {
    				//Quando for aprovação, precisamos verificar se foram marcados ou desmarcados itens de peças para cobrar Fornecedor
    				if(AnaliseGarantiaVO.PARECER_APROVAR_SG.equals(parecerCode)) {

						String  targetsItens[] = request.getParameterValues("cobraPecaFornecedor");	
						if(targetsItens != null  && targetsItens.length > 0 )
							this.setCobraFornecedor(garantia, targetsItens);
					}		
    				
    				//Atualizamos o status
    				AlertGarantia alerta = analiseGarantiaBusiness.definirParecerSG(usuario, garantia, parecerCode);

    				if ( alerta != null ) {

    					if ( alerta.getAlertGarantiaKey().indexOf("error") != -1 ) {

    						messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage(alerta.getAlertGarantiaKey(),garantiaId) );
    						super.addProblemMessages(request, messagesProblem);	

    						isSuccess = Boolean.FALSE;

    					} else if ( alerta.getAlertGarantiaKey().indexOf("success") != -1 ) {

    						messagesSuccess.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage(alerta.getAlertGarantiaKey(), garantiaId) );
    						super.addSuccessMessages(request, messagesSuccess);

    					}
    				}

    				preparaForm(gAnaliseForm, garantiaId);   

    				this.setJavaScriptExecuter(request, "redirectList(" + isSuccess +")");

    				return mapping.findForward("_form");
    				
    			} else
    				throw new ControllerException("Garantia " +  garantiaId + " não localizada!");    	
    		
    		} else
    			throw new ControllerException("Parâmetros Inválidos!");
    		
        } catch (BusinessException bExp) {
            
            throw new ControllerException(bExp);
            
        } catch (InvalidSessionException e) {
        	throw new ControllerException("Usuário inválido!");
		}   
    	
    }
    
    /** Determinada se Cobra fornecedor ou não
     * 
     * @param GarantiaHeader garantia
     * @param String  targetsItens[] -  itens selecionados no form
     */
    private void setCobraFornecedor(GarantiaHeader garantia, String  targetsItens[]) {    	
    	
    	// Verificamos se foram selecioandos itens
		if ( targetsItens.length > 0 ) {
			//Pegamos os itens selecionados
			String line   = null;
			Long   lineId = null;
			GarantiaLineId gLineId = null;
			GarantiaLine   gLine   = null;
			List listGLine = new ArrayList(garantia.getListGarantiaLineNotDouble().size());
			
			for (int i = 0 ; i < targetsItens.length ; i++) {
				line = targetsItens[i].substring(targetsItens[i].indexOf('@') + 1 , targetsItens[i].length());								
				lineId = line != null && !"".equals(line) ? Long.valueOf(line) : null; 

				if(lineId != null){
					gLineId = new GarantiaLineId(garantia.getEntityId(), lineId);
					gLine = garantia.getGarantiaLine(gLineId);						
					listGLine.add(gLine);									
				}
			}
			
			//atualizamos as peças
			Iterator it = garantia.getListGarantiaLineNotDouble().iterator();
			Iterator itn= null;
			GarantiaLine   gLineI   = null;
			Boolean isCheck = Boolean.FALSE;
			while(it.hasNext()){								
				gLine = (GarantiaLine)it.next();
				isCheck = Boolean.FALSE;
				itn = listGLine.iterator();
				while(itn.hasNext()){					
					gLineI = (GarantiaLine)itn.next();
					if(gLineI.equals(gLine)) {
						isCheck = Boolean.TRUE;
					}
					
				}
				//Se estiver na lista marcamos como S
				if(isCheck.booleanValue()) {
					gLine.setCobraPecaFornecedor(GarantiaLine.COBRAR_FORNECEDOR_SIM);
				} else {
					gLine.setCobraPecaFornecedor(GarantiaLine.COBRAR_FORNECEDOR_NAO);
				}				
				
			}
			
		}
    	
    }
    
    /** Tarefa: Apresenta form de análise de garantia.
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
     *  @throws ControllerException
     *      Se houverem erros, estes serão lançados como uma exception
     *      deste tipo.
     */ 
    public ActionForward analisar( ActionMapping mapping
                             , ActionForm form
                             , HttpServletRequest request
                             , HttpServletResponse response) throws ControllerException {
    	
    	DynaActionForm gAnaliseForm = (DynaActionForm) form;

    	String garantiaIdStr = request.getParameter( "garantiaId" );
    	if(garantiaIdStr != null && !"".equals(garantiaIdStr)) {
    		Integer garantiaId  = Integer.valueOf(garantiaIdStr.trim());    

    		preparaForm(gAnaliseForm, garantiaId);

    	} else
    		throw new ControllerException("Garantia inválida!");
    	
    	return mapping.findForward("_form");
    }
    
    /** Prepara o formulário para retornar a apresentação
     * 
     * @param gAnaliseForm
     * @param garantiaId
     * @throws ControllerException
     */
    private void preparaForm(DynaActionForm gAnaliseForm, Integer garantiaId) throws ControllerException {
    	
    	try { 
    		
    		GarantiaHeaderBusiness garantiaHeaderBusiness = (GarantiaHeaderBusiness) springContext.getBean("garantiaHeaderBO");
    		GarantiaHeader garantia = garantiaHeaderBusiness.get(garantiaId);
    		
    		FaturamentoBusiness faturamentoBusiness = (FaturamentoBusiness) springContext.getBean("faturamentoBO");
    		Faturamento 		faturamento 		= faturamentoBusiness.getByChassi(garantia.getModelo());
    		AnaliseGarantiaBusiness  analiseGarantiaBusiness  = (AnaliseGarantiaBusiness)  springContext.getBean("analiseGarantiaBO");
    		RevisaoBusiness        revisaoBusiness        = (RevisaoBusiness)springContext.getBean("revisaoBO"); 
    		InfoMercadoBusiness    infoMercadoBusiness    = (InfoMercadoBusiness) springContext.getBean("infoMercadoBO");

    		CupomBusiness 		cupomBusiness 		= (CupomBusiness) springContext.getBean("cupomBO");

    		List cupons = cupomBusiness.listByChassiAsc(garantia.getModelo());
    		
    		//Recuperando o cupom da revisão de entrega
    		Iterator itCp = cupons.iterator();
    		Cupom cp = null;
    		String dtVenda = "";
    		while(itCp.hasNext()){
    			
    			cp = (Cupom)itCp.next();
    			if(new Long(1).equals(cp.getRevisao().getNumeroRevisao())) {
    				dtVenda = cp.getMaskedDataVenda();
    				break;
    			}
    			
    		}   
    		
    		// Recuparamos a revisão que irá servir de parâmetro para encontrar um cupom
    		Revisao revisao = revisaoBusiness.getByModel(ControllerHelper.getModeloByChassi(garantia.getModelo()), new Long(1));
    			    		
    		Cupom   cupom   = cupomBusiness.getCupomByChassiAndRevisao( garantia.getModelo(), revisao);
    		
    		if ( cupom != null ) {
    				    			
    			dtVenda =  cupom.getMaskedDataVenda();
    			
    		} else {
    			    			
    			faturamento = faturamentoBusiness.getByChassi(garantia.getModelo());    			
    			dtVenda = faturamento.getMaskedDataNotaFiscal();
    			
    		}
    		
    		gAnaliseForm.set( "entityId"		 , garantia.getEntityId());    		
    		gAnaliseForm.set( "loteId"			 ,  garantia.getLote().getEntityId());
    		gAnaliseForm.set( "chassi"			 ,  garantia.getModelo());
    		gAnaliseForm.set( "dtVenda"			 , dtVenda);
    		gAnaliseForm.set( "createdDate"		 ,  garantia.getMaskedDataCriacao());
    		gAnaliseForm.set( "numOS"			 , garantia.getNumeroOS());
    		gAnaliseForm.set( "dtAbert"			 , garantia.getMaskedDataAberturaOS());
    		gAnaliseForm.set( "dtFech"			 , garantia.getMaskedDataFechamentoOS());
    		gAnaliseForm.set( "condicaoProblema" , garantia.getCondicaoProblemaGarantia() != null ? garantia.getCondicaoProblemaGarantia().getCondicaoProblema():"");
    		gAnaliseForm.set( "causaProblema"    , garantia.getCausaProblemaGarantia() != null ? garantia.getCausaProblemaGarantia().getCausaProblema():"");
    		gAnaliseForm.set( "diagnostico"      , garantia.getDiagnosticoProblemaGarantia() != null ? garantia.getDiagnosticoProblemaGarantia().getDiagnosticoProblema():"");
    		gAnaliseForm.set( "solucao"          , garantia.getSolucaoProblemaGarantia() != null ? garantia.getSolucaoProblemaGarantia().getSolucaoProblema():"");
    		gAnaliseForm.set( "km"  			 , garantia.getQuilometragem());
    		gAnaliseForm.set( "horasUso"  		 , garantia.getHorasUso());
    		gAnaliseForm.set( "autorizacaoEspecial", garantia.getAutorizacaoEspecialSG() != null ? garantia.getAutorizacaoEspecialSG().getNumAutorizacao() : "");
    		gAnaliseForm.set( "dtFaturamento"	 , faturamento.getMaskedDataNotaFiscal());
    		gAnaliseForm.set( "nuNtFiscal"		 , faturamento.getNumeroNotaFiscal());
    		gAnaliseForm.set( "empresa"			 , faturamento.getEmpresa().getOrgCode());
    		gAnaliseForm.set( "dsConcessionaria" , faturamento.getConcessionaria().getRazaoSocial());
    		gAnaliseForm.set( "cnpjConc" 		 , faturamento.getConcessionaria().getMaskedCnpj());
    		gAnaliseForm.set( "dsCidade"		 , faturamento.getConcessionaria().getCidade());
    		gAnaliseForm.set( "dsUf"			 , faturamento.getConcessionaria().getUf());
    		gAnaliseForm.set( "idTipo"           , garantia.getLote().getLinha().getEntityId() );
    		gAnaliseForm.set( "nmRepresentante"  , garantia.getLote().getConcessionaria().getRepresentante()!= null ? garantia.getLote().getConcessionaria().getRepresentante().getNome():"");
    		gAnaliseForm.set( "preenchidoPor"    , garantia.getPreenchidoPor() );
    		gAnaliseForm.set( "tipoGasolinaId"   , garantia.getTipoGasolinaId() );
    		gAnaliseForm.set( "localUsoDoce"     , garantia.getLocalUsoDoce() );
    		gAnaliseForm.set( "localUsoSalg"     , garantia.getLocalUsoSalg() );
    		gAnaliseForm.set( "materHelice"      , garantia.getMaterHelice() );
    		gAnaliseForm.set( "tipoUso"          , garantia.getTipoUso() );
    		gAnaliseForm.set( "cidadeUso"        , garantia.getCidadeUso() );
    		gAnaliseForm.set( "marcaCasco"       , garantia.getMarcaCasco() );
    		gAnaliseForm.set( "modeloCasco"      , garantia.getModeloCasco() );
    		gAnaliseForm.set( "marcaHelice"      , garantia.getMarcaHelice() );
    		gAnaliseForm.set( "passoHelice"      , garantia.getPassoHelice() );
    		gAnaliseForm.set( "rotacaoMaxima"    , garantia.getRotacaoMaxima() );
			
    		gAnaliseForm.set("listTipoGasolina", infoMercadoBusiness.listTipoGasolina());
    		gAnaliseForm.set("listTipoProduto" , infoMercadoBusiness.listTipoProdutoInfo() );
    		gAnaliseForm.set("listTipoUso"     , infoMercadoBusiness.listTipoUso()); 
    		
    		gAnaliseForm.set( "listRevisao"		 , cupons);
    		gAnaliseForm.set( "listGarantias"	 , analiseGarantiaBusiness.listHistoricoGarantia((Integer)garantia.getEntityId(),garantia.getModelo()));
    				
    		gAnaliseForm.set( "parecerYm"		 , analiseGarantiaBusiness.listHistParecerTecnico((Integer)garantia.getEntityId()));
    		gAnaliseForm.set( "observacaoYm"	 , analiseGarantiaBusiness.listHistAnaliseObservacao((Integer)garantia.getEntityId()));
    		gAnaliseForm.set( "listPecas"		 , garantia.getListGarantiaLineNotDouble());
    		
    		// Recuperando os serviços da garantia
			 
			 //log.info("Serviços:"  + garantia.getGrupos().size());
			 
			 Iterator itS = garantia.getGrupos().iterator();
			 int numberField  = 0;
			 
			 while ( itS.hasNext() ) {
				 
				 numberField++;
				 
				 String nameField        = "servico_" + numberField;
				 String descServicoField = "descServico_" + numberField;
				 
				 ServicoGrupo servicoGrp = (ServicoGrupo)itS.next();
				 Servico      servico    =  servicoGrp.getServico(); 
				 if ( servico != null ) {
					 
					 if ( numberField < 4 ) {
						 
						 gAnaliseForm.set(nameField         , servico.getServicoCode());
						 gAnaliseForm.set(descServicoField  , servico.getDescricao());
						 
					 } else {
						 
						 log.info("A garantia n. " + garantia.getEntityId() + " possui mais de 3 serviços cadastrados!");
						 
					 }
					 
				 }
				 
			 }
			 
    		try {
				if(garantia.getEntityId() != null ) {
					GarantiaArquivoBusiness gArquivoBusiness = (GarantiaArquivoBusiness) springContext.getBean("garantiaArquivoBO");				
									
					gAnaliseForm.set( "listFiles", gArquivoBusiness.listFile((Integer)garantia.getEntityId()));					
					
				} else {
					gAnaliseForm.set( "listFiles", null);
				}
				
			}catch(FileException flExp){
				gAnaliseForm.set( "listFiles", null);
			}
    		
    	} catch (BusinessException bExp) {

    		throw new ControllerException("Usuário inválido!");

    	}   

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
    			/*
    			if(Linha.TIPO_MOTOCICLETA.equals(gArq.getLinhaId())){
    				GarantiaArquivoMoto  gAMoto = gArquivoBusiness.getArquivoMoto(gArq);
    				bytes = gAMoto.getContent().getBytes(1,gArq.getSize().intValue());
    				
    			} else {
    				GarantiaArquivoNtc gANtc = gArquivoBusiness.getArquivoNtc(gArq);
    				bytes = gANtc.getContent().getBytes(1, gArq.getSize().intValue());
    			}
    			*/
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
	
}
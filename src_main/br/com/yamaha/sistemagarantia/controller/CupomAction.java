/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......CupomAction.java
 *   .: Criação.....24 de abril, 12:17
 *   .: Autor.......Edson Luiz Sumensari
 *                  Thiago Uriel M. Garcia
 *   .: Descrição...Action para tratar Cupom.
 */
package br.com.yamaha.sistemagarantia.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.validator.DynaValidatorActionForm;
import org.springframework.beans.BeansException;

import br.com.resource.infra.controller.InfraDispatchAction;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.BusinessRuleException;
import br.com.resource.infra.exception.ControllerException;
import br.com.resource.infra.security.exception.InvalidSessionException;
import br.com.resource.infra.security.helper.UserHelper;
import br.com.resource.infra.utils.DateUtils;
import br.com.resource.infra.utils.NumberUtils;
import br.com.yamaha.sistemagarantia.business.CampaignBusiness;
import br.com.yamaha.sistemagarantia.business.ClienteBusiness;
import br.com.yamaha.sistemagarantia.business.CupomBusiness;
import br.com.yamaha.sistemagarantia.business.LinhaBusiness;
import br.com.yamaha.sistemagarantia.business.LoteBusiness;
import br.com.yamaha.sistemagarantia.business.RevisaoBusiness;
import br.com.yamaha.sistemagarantia.business.TipoUsoBarcoBusiness;
import br.com.yamaha.sistemagarantia.controller.helper.ControllerHelper;
import br.com.yamaha.sistemagarantia.controller.helper.YmSessionHelper;
import br.com.yamaha.sistemagarantia.model.AlertCupom;
import br.com.yamaha.sistemagarantia.model.Cliente;
import br.com.yamaha.sistemagarantia.model.Concessionaria;
import br.com.yamaha.sistemagarantia.model.Cupom;
import br.com.yamaha.sistemagarantia.model.Faturamento;
import br.com.yamaha.sistemagarantia.model.Linha;
import br.com.yamaha.sistemagarantia.model.Lote;
import br.com.yamaha.sistemagarantia.model.ParametroSistema;
import br.com.yamaha.sistemagarantia.model.Revisao;
import br.com.yamaha.sistemagarantia.model.StatusGarantia;
import br.com.yamaha.sistemagarantia.model.StatusLote;
import br.com.yamaha.sistemagarantia.model.TipoUsoBarco;
import br.com.yamaha.sistemagarantia.model.Usuario;
import br.com.yamaha.sistemagarantia.model.ValorServico;
import br.com.yamaha.sistemagarantia.model.to.CupomTO;

/** Classe que controla as tarefas relacionadas ao <b>Cupom</b>.
 * 
 *  @author Edson Luiz Sumensari
 *  @author Thiago Uriel M. Garcia
 */
public class CupomAction extends InfraDispatchAction {

    
	private static final long serialVersionUID = 1L;

	/** Tarefa: Apresenta o formulário de inclusão de Lote.
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
    	
    	DynaValidatorActionForm cupomForm = (DynaValidatorActionForm) form;
    	cupomForm.initialize(mapping);
    	
    	int loteId = request.getParameter("loteId") != null ? Integer.parseInt(request.getParameter("loteId"))
				  : ((Integer)request.getAttribute("loteId")).intValue();
    	
    	Lote lote = null;
    	List listUseType = null;
    	
    	try { 
    		
    		LoteBusiness  		  loteBusiness  		= (LoteBusiness)  		 springContext.getBean("loteBO");
    		TipoUsoBarcoBusiness  tipoUsobarcoBusiness  = (TipoUsoBarcoBusiness) springContext.getBean("tipoUsoBarcoBO");
    		
    		lote = (Lote) loteBusiness.get(new Integer(loteId));
    		listUseType = tipoUsobarcoBusiness.list();
    		
    		cupomForm.set("loteId"			, lote.getEntityId());
    		cupomForm.set("linhaId"			, lote.getLinha().getEntityId());
    		cupomForm.set("linhaDescricao"  , lote.getLinha().getDescricao());
    		cupomForm.set("listUseType"		, listUseType);
    		cupomForm.set("listRevisao"		, new ArrayList());
    		cupomForm.set("validate"		, new Boolean(false));
    		cupomForm.set("sysDate"		    , DateUtils.applyMask(new Date()));
    		
    		this.setJavaScriptExecuter(request, "setTitle();fieldFocus('chassi');" );
    		//this.setJavaScriptExecuter(request, "fieldFocus('chassi')" );
    		
    		return mapping.findForward("_form");
    		
    	} catch (BusinessException bExp) {
    		
            throw new ControllerException(bExp);
            
        }
        
    }       
    
    /** Tarefa: Apresenta o formulário par alteração da revisão.
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
    public ActionForward alter( ActionMapping mapping
                             , ActionForm form
                             , HttpServletRequest request
                             , HttpServletResponse response) throws ControllerException {
    	
    	DynaValidatorActionForm cupomForm = (DynaValidatorActionForm) form;
    	cupomForm.initialize(mapping);
    	
    	Long cupomId = request.getParameter("cupomId") != null 
    				   ?  Long.valueOf(request.getParameter("cupomId"))
				  	   : (Long)request.getAttribute("cupomId");
    	    	    	    	
    	try { 
    		
    		LoteBusiness  		  loteBusiness  		= (LoteBusiness)  		 springContext.getBean("loteBO"        );
    		TipoUsoBarcoBusiness  tipoUsobarcoBusiness  = (TipoUsoBarcoBusiness) springContext.getBean("tipoUsoBarcoBO");
    		CupomBusiness 		  cupomBusiness 		= (CupomBusiness)        springContext.getBean("cupomBO"       );
    		RevisaoBusiness		  revisaoBusiness	    = (RevisaoBusiness)	     springContext.getBean("revisaoBO"	   );
    		    		
    		Cupom   cupom       = cupomBusiness.get(cupomId);
    		
    		Lote    lote        = (Lote) loteBusiness.get(cupom.getLote().getEntityId());
    		List    listUseType = tipoUsobarcoBusiness.list();
    		//List    listRevisao = revisaoBusiness.listApproachedByChassi(ControllerHelper.getModeloByChassi(cupom.getChassi()));
    		List    listRevisao = revisaoBusiness.loadAlterRevisao(cupom);
    		
    		cupomForm.set("entityId"		, cupom.getEntityId()			 	     	 );
    		cupomForm.set("loteId"			, lote.getEntityId()			 	     	 );
    		cupomForm.set("linhaId"			, lote.getLinha().getEntityId()  	     	 );
    		cupomForm.set("linhaDescricao"  , lote.getLinha().getDescricao() 	     	 );
    		cupomForm.set("listUseType"		, listUseType					 	     	 );
    		cupomForm.set("listRevisao"		, new ArrayList()				 	     	 );
    		cupomForm.set("validate"		, new Boolean(false)			 	     	 );
    		cupomForm.set("sysDate"		    , DateUtils.applyMask(new Date())	     	 );
    		cupomForm.set("listRevisao"     , listRevisao                    	     	 ); 
    		cupomForm.set("numeroRevisao"   , cupom.getRevisao().getNumeroRevisao()  	 );     		
			cupomForm.set("chassi"          , cupom.getChassi()						 	 );
			cupomForm.set("cupomCode"		, cupom.getCupomCode()					 	 );
			cupomForm.set("dataVenda"		, DateUtils.applyMask(cupom.getDataVenda()));
			cupomForm.set("dataRevisao"		, DateUtils.applyMask(cupom.getDataRevisao()));
			cupomForm.set("dataEntrega"		, DateUtils.applyMask(cupom.getDataEntrega()));
			cupomForm.set("quilometragem"	, cupom.getQuilometragem()					 );
			cupomForm.set("diasUso"	        , cupom.getDiasUso()					     );
			cupomForm.set("horasUso"	    , cupom.getHorasUso()						 );					
			cupomForm.set("modeloBarco"     , cupom.getModeloBarco()					 );
			cupomForm.set("marcaBarco"		, cupom.getMarcaBarco()						 );
			cupomForm.set("alterState"	    , new Boolean(true)			 	     	 	 );
			
			if ( cupom.getCliente() != null ) {
				cupomForm.set("cpfCnpjCliente"  , cupom.getCliente().getCnpj().toString());
				cupomForm.set("clienteNome"     , cupom.getCliente().getNome() 			 );
				cupomForm.set("clienteId"       , cupom.getCliente().getEntityId()		 );
				
				this.setJavaScriptExecuter(request, "setAlterTitle();fieldFocus('cupomCode');formataCpfCnpj(" + Cliente.JURIDICA.equalsIgnoreCase(cupom.getCliente().getTipoCliente()) + " );" );
				
			} else {
				
				this.setJavaScriptExecuter(request, "setAlterTitle();fieldFocus('cupomCode');" );
			}
			
			if ( cupom.getTipoUsoBarco() != null )
				cupomForm.set("tipoUsoId"		, cupom.getTipoUsoBarco().getEntityId()  );	
			
    		
    		
    		return mapping.findForward("_form");
    		
    	} catch (BusinessException bExp) {
    		
            throw new ControllerException(bExp);
            
        }
        
    }           
    
    
    /** Tarefa: Listagem.
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
    public ActionForward list( ActionMapping mapping
                             , ActionForm form
                             , HttpServletRequest request
                             , HttpServletResponse response) throws ControllerException {
    	
    	try {    		
    		
    		Usuario usuario = (Usuario) UserHelper.getBoundedUser(request.getSession());
    		Concessionaria concessionaria = usuario.getConcessionaria();
    		
            Lote 		lote 	= null;
            
            DynaValidatorActionForm cupomForm = (DynaValidatorActionForm) form;
            cupomForm.initialize(mapping);
    		
            int loteId = request.getParameter("loteId") != null ? Integer.parseInt(request.getParameter("loteId"))
					  : ((Integer)request.getAttribute("loteId")).intValue();
              
    		LoteBusiness  loteBusiness  = (LoteBusiness)  springContext.getBean("loteBO");
    		
    		lote = (Lote)loteBusiness.get(new Integer(loteId));
    		
    		Integer linhasPrograma = loteBusiness.getLinhasPagina("SG_LT_REV");
    		
    		//lista = cupomBusiness.listByLote( lote );
    		//lista = cupomBusiness.listCuponsByLote( new Integer(loteId) );
    		
            cupomForm.set( "task"           , "list");
            cupomForm.set( "listResults"    , lote.getCupons() );
            
            // Quando a concessionária tiver status de aprovação automática e
            // O status do lote for "aguardando digitação de notas,
            // Devemos liberar algumas ações na tela como o botão incluir e
            // os links alterar e excluir
            boolean hasAprovAut = Concessionaria.FLAG_APROVA_YES.equals(concessionaria.getFlagAprovacaoAutom()) && lote.getStatusLote().getEntityId().equals(StatusLote.STATUS_AGUARDANDO_DIGITACAO_NOTA);
            
            // Flag para habilitar ou não o botão de incluir no form
    		boolean validInclude = lote.getValidInclude();
    		
    		// Flag para liberar a alteração na Garantia
    		boolean validAlter   = lote.getValidAlter();
    		    		
    		request.setAttribute("validInclude", new Boolean(validInclude));
    		request.setAttribute("validAlter"  , new Boolean(validAlter));
            request.setAttribute("lote"        , lote);
            request.setAttribute("hasAprovAut" , new Boolean(hasAprovAut) );
            request.setAttribute("qtdeLinhas"  , linhasPrograma.toString() );
            
            this.setJavaScriptExecuter(request, "setTitle()" );
            
    		return mapping.findForward("_list");
    		
    	} catch (BusinessException bExp) {
    		
            throw new ControllerException(bExp);
            
        } catch ( InvalidSessionException isExp ) {
    		
    		throw new ControllerException(isExp);
    		
		}   
    } 
    
    /** Tarefa: Grava as informações enviadas do formulário de Cliente.
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
    	
    	//System.out.println("0 Valor servico zerado");
    	
    	DynaValidatorActionForm cupomForm = (DynaValidatorActionForm) form;
    	ActionMessages 			messagesWarning    = new ActionMessages();
    	ActionMessages 			messagesSuccess    = new ActionMessages();
    	ActionMessages 			messagesProblem    = new ActionMessages();
    	
    	String  chassi  	   = cupomForm.getString("chassi");    	
    	//Long    linhaId 	   = (Long)cupomForm.get("linhaId");
    	Long    cupomCode 	   = (Long)cupomForm.get("cupomCode");
    	Long    numeroRevisao  = (Long)cupomForm.get("numeroRevisao");    	
    	Integer loteId 		   = cupomForm.get("loteId") != null ? (Integer)cupomForm.get("loteId") : null;
    	Long	tipoUsoId	   = cupomForm.get("tipoUsoId") != null ? (Long)cupomForm.get("tipoUsoId") : null;
    	boolean validado       = cupomForm.getString("validado").equals("false") ? false : true;;
    	
    	// Flag que verificação se o chassi informado foi faturado pela concessionária do usuário
    	// Se for True
    	boolean isConcessionaria = true;
    	    	
    	try {    		

    		Usuario usuario = (Usuario) UserHelper.getBoundedUser(request.getSession());
    		
    		/* Validações Críticas */    		    		
    		//FaturamentoBusiness  faturamentoBusiness  = (FaturamentoBusiness)  springContext.getBean("faturamentoBO" );
    		ClienteBusiness		 clienteBusiness	  = (ClienteBusiness)	   springContext.getBean("clienteBO"	 );
    		CupomBusiness		 cupomBusiness		  = (CupomBusiness)		   springContext.getBean("cupomBO"		 );
    		//LinhaBusiness		 linhaBusiness		  = (LinhaBusiness)		   springContext.getBean("linhaBO"		 );
    		RevisaoBusiness		 revisaoBusiness	  = (RevisaoBusiness)	   springContext.getBean("revisaoBO"	 );
    		LoteBusiness  		 loteBusiness  		  = (LoteBusiness)		   springContext.getBean("loteBO"		 );
    		//AlertCupomBusiness   alertCupomBusiness   = (AlertCupomBusiness)   springContext.getBean("alertCupomBO"	 );
    		TipoUsoBarcoBusiness tipoUsoBarcoBusiness = (TipoUsoBarcoBusiness) springContext.getBean("tipoUsoBarcoBO");
    		CampaignBusiness campaignBusiness 		  = (CampaignBusiness) 	   springContext.getBean("campaignBO");
    		
    		//Linha			linha 		 = linhaBusiness.get(linhaId);
    		Lote			lote		 = loteBusiness.get(loteId);
    		Linha			linha 		 = lote.getLinha();
    		Faturamento 	faturamento	 = cupomBusiness.getFaturamentoByLinha(chassi, linha);
    		List            listUseType  = tipoUsoBarcoBusiness.list();
    		Revisao			revisao		 = null;
    		
    		// Lista de alertas
    		List alertas = new ArrayList();
    		
    		// Setamos os dados do form para retorno de mensgagens
    		cupomForm.set("loteId"			, lote.getEntityId());
    		cupomForm.set("linhaId"			, lote.getLinha().getEntityId());
    		cupomForm.set("linhaDescricao"  , lote.getLinha().getDescricao());
    		cupomForm.set("listUseType"		, listUseType);
    		cupomForm.set("validate"		, new Boolean(false));
    		//cupomForm.set("dataRevisao"		, DateUtils.applyMask(new Date()));
    		
    		List    listRevisao = revisaoBusiness.listNewByModel(chassi);
    		
    		//log.info("Modelo:" + ControllerHelper.getModeloByChassi(chassi) + " - numeroRevisao:" + numeroRevisao );
    		revisao		= revisaoBusiness.getByModel(ControllerHelper.getModeloByChassi(chassi), numeroRevisao);
    		
    		if ( revisao != null ) {
    			if ( !listRevisao.contains(revisao) )
    				listRevisao.add(revisao);
    			
    			cupomForm.set("numeroRevisao", revisao.getNumeroRevisao());
    			cupomForm.set("listRevisao"  , listRevisao );  
    			
    		} else 
    			throw new BusinessRuleException("cupom.msg.revision");   
    		
    		// Verificamos se o chassi informado existe
    		//log.info("faturamento:" + (faturamento == null ? "true":faturamento.getEntityId()));
    		if ( faturamento == null ) {    			
    			
    			throw new BusinessRuleException("cupom.msg.error.chassi");    			
    			
    		}
    		
    		//log.info("Revisao:" + revisao.getEntityId());
    		TipoUsoBarco    tipoUsoBarco = null;
    		Cliente			cliente		 = null;
    		    		
    		Long    cpfCnpjCliente = ( cupomForm.getString("cpfCnpjCliente" ) != null ) && 
    								 ( !cupomForm.getString("cpfCnpjCliente").equals("") ) ? 
    								 clienteBusiness.unmaskCepCpfCnpj( cupomForm.getString("cpfCnpjCliente") ) : 
    								 null ;
    								 
    		Long   clienteId = cupomForm.get("clienteId") != null ? (Long)cupomForm.get("clienteId") : null;
    		
    		//log.info(" Cliente:" + clienteId);
    		
    		if ( clienteId != null && (numeroRevisao.longValue() == 1) ) {
    			
    			cliente		 = clienteBusiness.get(clienteId);
    			
    			if ( cliente == null && cpfCnpjCliente != null )
    				cliente		 = clienteBusiness.getByCpfCnpj(cpfCnpjCliente);
    		
    			if ( cliente != null ) {
    				//log.info(" ----->  Cliente :" + cliente.getTipoCliente());
    				
    				/*
    				 * Validacao dos campos: Email, Data de Nascimento e Sexo 
    				 * Demanda 00003
    				 * Data: 29/07/2012
    				 * Resource IT 
    				 */
    	    		
    	    		//Tratamento do email
    				if (cliente.getEmail() == null)
    					throw new BusinessRuleException("cupom.error.required.customer.email");
    				
    				// Tratamento da Data de Nascimento
    				if (cliente.getTipoCliente().equals(Cliente.FISICA)) {
    					
    					//Data de Nascimento
    					if (cliente.getDataNascimento() == null)
    						throw new BusinessRuleException("cupom.error.required.customer.dtNascimento");
    					
    					//Sexo
    					if (cliente.getSexo() == null)
    						throw new BusinessRuleException("cupom.error.required.customer.sexo");
    				}
    				
	    			cupomForm.set("cpfCnpjCliente", cpfCnpjCliente.toString() );
	    			cupomForm.set("clienteNome"   , cliente.getNome() );
	    			cupomForm.set("clienteId"     , cliente.getEntityId() );
	    			this.setJavaScriptExecuter(request, "formataCpfCnpj(" + Cliente.JURIDICA.equalsIgnoreCase(cliente.getTipoCliente()) + ")" );
	    			
    			} else {    				
    				
    				//log.info(" -----> ck1 - Cliente Não Encontrado! Direcionando para inclusão de Cliente.");
    				
    				includeClient(mapping, form, request, response);
    				
    				return mapping.findForward("_customerInclude");
    				
    			}
    			
    		}
    		
    		if ( tipoUsoId != null )
    			tipoUsoBarco = tipoUsoBarcoBusiness.get(tipoUsoId);
    		
	    	// Verificamos se o chassi faturado é da mesma concessionária do usuário
	    	if (!usuario.compareIdAtribute("concessionaria",faturamento.getConcessionaria().getEntityId())){
	    		
	    		isConcessionaria = false;
	    		messagesWarning.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("cupom.msg.warning.chassi",chassi.toUpperCase()));
	    		super.addWarningMessages(request, messagesWarning);
	    		
	    	}	   
	    	
	    	// 	Montamos o objeto cupom para outras validações 
	    	Cupom cupom = null;
	    	
	    	// Recuperamos o entityId do objeto	    	
	    	Long entityId = cupomForm.get("entityId") != null ? (Long)cupomForm.get("entityId") : null;
	    	
	    	//log.info(" EntityId:" + entityId);
	    	
	    	// Quando o entityId enviado pelo form for diferente de zero, temos uma alteração
	    	// Neste caso recuperamos a entidade
	    	if ( !entityId.equals(new Long(0)) ) {
	    		
	    		cupom = cupomBusiness.get(entityId);
	    		//log.info(" -----> Alteração do Cupom:" +  cupom.getCupomCode());
	    		
	    	} else {
	    		
	    		if ( StatusLote.STATUS_EM_DIGITACAO.equals(lote.getStatusLote().getEntityId()) ) {
		    		
		    		log.info(" -----> Novo Cupom!");
		    		cupom = new Cupom();
		    		
	    		} else {
	    				    			
	        		throw new BusinessRuleException("cupom.msg.error.statusInvalid"); 
	    			
	    		}
	    	}
	    	
	    	// Verificamos se o Cliente está cadastrado, apenas quando for revisão de zero Km 
	    	if ( numeroRevisao.equals(Revisao.REVISAO_ZERO) ) { 
	    		    		
	    		if ( cliente == null ) {
	    			
	    			//log.info(" -----> ck2 - Cliente Não Encontrado! Direcionando para inclusão de Cliente.");
	    			
	    			includeClient(mapping, form, request, response);
	    			
	    			return mapping.findForward("_customerInclude");
	    			
	    		}	
	    		
	    		// Setamos o número e a série da Nota Fiscal
	    		
	    		//log.info(" -----> Série da Nota Fiscal :"   + faturamento.getSerieNotaFiscal() );
	    		//log.info(" -----> Data da Nota Fiscal :"    + faturamento.getDataNotaFiscal()  );
	    		//log.info(" -----> Número da Nota Fiscal :"  + faturamento.getNumeroNotaFiscal());
	    		
	    		cupom.setSerieNotaFiscal(faturamento.getSerieNotaFiscal());
	    		cupom.setDataNotaFiscal(faturamento.getDataNotaFiscal());
	    		cupom.setNumeroNotaFiscal(faturamento.getNumeroNotaFiscal());
	    		
	    	} 
	    	
	    	// Para revisão acima da 3 (para moto) e 2 (para Náutica),
	    	// Não existe valor de serviços
	    	if ( numeroRevisao.equals(Revisao.REVISAO_ZERO) || numeroRevisao.equals(Revisao.REVISAO_HUM) 
	    		 || ( linha.getEntityId().equals(Linha.TIPO_MOTOCICLETA) && numeroRevisao.equals(Revisao.REVISAO_DOIS)) ) {	    		
	    		
	    		Long codigoRegiao       = null;
	    		double valorRevisao     = 0;
	    		double valorBonificacao = 0;
	    		
	    		if (linha.getEntityId().equals(Linha.TIPO_MOTOCICLETA) || linha.getEntityId().equals(Linha.TIPO_QUADRICICLO) ) {
	    			
	    			codigoRegiao = usuario.getConcessionaria().getRegiaMoto();
	    			
	    		} else if (linha.getEntityId().equals(Linha.TIPO_NAUTICA) || linha.getEntityId().equals(Linha.TIPO_FORCA ) ) {
	    			
	    			codigoRegiao = usuario.getConcessionaria().getRegiaNautica();
	    		}
	    		//log.info(" -----> CupomActon - save - Concessionária:" + usuario.getConcessionaria().getRazaoSocial() + " - Região" + usuario.getConcessionaria().getRegiaMoto());
	    		//log.info(" -----> CupomActon - save - valorServico MO:" + revisao.getDescricao() + " - modelo:" + ControllerHelper.getModeloByChassi(chassi) + " - Região:" + codigoRegiao);
	    		ValorServico valorServico = cupomBusiness.getValorServico(revisao, ControllerHelper.getModeloByChassi(chassi), codigoRegiao);
	    		//log.info(" -----> CupomActon - save - valorServico MO:" + valorServico.getValorMaoObra() + " - Sv:" + valorServico.getValorRevisao() + " - Pri:" + valorServico.getPriValorRevisao());
	    		try {
	    				    		
	    			valorRevisao = valorServico.getPriValorRevisao() + (valorServico.getPriValorRevisao() * valorServico.getPriPercBonificacao());
	    			
	    			//log.info(" -----> CupomActon - save - Bonificação revisão:" + revisao.getNumeroRevisao());
	    			//log.info(" -----> CupomActon - save - valor revisao:" + valorRevisao);
	    			
	    			//log.info(" -----> CupomActon - save - Flag Bonificação:" + usuario.getConcessionaria().getFlagBonificacao());
	    			// recuperamos o valor da bonificação, apenas se a concessionária possui a flag 'S'
	    			// e o id da revisão for 1 ou 2. 
	    			if ( usuario.getConcessionaria().getFlagBonificacao().equalsIgnoreCase(Concessionaria.FLAG_BONIFICACAO_YES) 
	    				 && ( revisao.getNumeroRevisao().equals(new Long(2)) || revisao.getNumeroRevisao().equals(new Long(3)) )
	    				 && linha.getEntityId().equals(Linha.TIPO_MOTOCICLETA) 
	    				) {
	    				
	    				ParametroSistema parametroBonif = cupomBusiness.getByParameterSystem(ParametroSistema.BONIFICACAO);
	    				
	    				//log.info(" -----> CupomActon - save - Bonificação valor:" + parametroBonif.getValorParametro());
    					double bonificacao = new Double(parametroBonif.getValorParametro()).doubleValue();
    					//log.info(" -----> CupomActon - save - Bonificação double:" + bonificacao);
	    				valorBonificacao = (valorServico.getPriValorRevisao() * bonificacao / 100);
	    				
	    			} 
	    			
	    			cupom.setValorRevisao(NumberUtils.round(valorRevisao,2));	    			
	    			cupom.setValorBonificacao(NumberUtils.round(valorBonificacao,2));	
	    			
	    			//log.info(" -----> CupomActon - save - valor revisao:" + cupom.getValorRevisao());
	    			
	    			//log.info(" -----> CupomActon - save - Bonificação valor final:" + cupom.getValorBonificacao());
	    			
	    		} catch ( NullPointerException npExp ) {
	    			
	    			messagesWarning.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("cupom.msg.warning.serviceValue",chassi.toUpperCase()) );
    	            super.addWarningMessages(request, messagesWarning); 
	    			
	    		}
	    		
	    		//INICIO - edson 02/07/2013
	    		if ( cupom.getValorRevisao() != 0 ) {
	    			cupom.setStatusMovId(StatusGarantia.STATUS_AGUARDANDO_DIGITACAO_NOTA);
	    			cupom.setStatusGarantia(StatusGarantia.getInstance(StatusGarantia.STATUS_AGUARDANDO_DIGITACAO_NOTA));
	    		} else { 
	    			cupom.setStatusMovId(StatusGarantia.STATUS_PERIODICA);
	    			cupom.setStatusGarantia(StatusGarantia.getInstance(StatusGarantia.STATUS_PERIODICA));
	    		}
	    		
	    	} else {
	    		
	    		// O status inicial do cupom é "PERIÓDICO"
	    		if ( entityId.equals(new Long(0)) )	 {
	    			cupom.setStatusMovId(StatusGarantia.STATUS_PERIODICA);
	    			cupom.setStatusGarantia(StatusGarantia.getInstance(StatusGarantia.STATUS_PERIODICA));
	    		}
	    		//FIM - edson 02/07/2013
	    	}
	    	
	    	// Tratamento de Datas Fornecidas
	    	Date dataRevisao = "".equals(cupomForm.getString("dataRevisao")) ? 
	    					   null :
	    					   DateUtils.format(cupomForm.getString("dataRevisao"),"dd/MM/yyyy");
	    	
	    	Date dataVenda   = "".equals(cupomForm.getString("dataVenda")) ? 
			   				   null :
					           DateUtils.format(cupomForm.getString("dataVenda"),"dd/MM/yyyy"); 
	    	
	    	Date dataEntrega = "".equals(cupomForm.getString("dataEntrega"))? 
			   				   null :
			   				   DateUtils.format(cupomForm.getString("dataEntrega"),"dd/MM/yyyy"); 	

	    	
	    	// Validação da Data de Revisão com a da de Faturamento
	    	// Se a data da venda foi informada, comparamos por ela
	    	// Caso contrário, verificamos pela data do faturamento. 
	    	if ( dataVenda != null ) {
	    		//Validando pela data da venda
	    		if ( DateUtils.compareDate(dataVenda, faturamento.getDataNotaFiscal() ) < 0 ) {
	    			
	    			throw new BusinessRuleException("cupom.msg.error.greaterDateSell");  
	    			
	    		}	    		
	    		
	    	} else {
	    		// Validando pela data do faturamento
	    		if ( DateUtils.compareDate(dataRevisao, faturamento.getDataNotaFiscal()) < 0 ) {
	    			
	    			throw new BusinessRuleException("cupom.msg.error.greaterDateSell");  
	    			
	    		}	
	    		
	    	}	    	
	    	
	    	boolean alterState = ((Boolean)cupomForm.get("alterState")).booleanValue(); 
	    		    	
	    	cupom.setCupomCode(cupomCode);
	    	cupom.setChassi(chassi);
	    	cupom.setCliente(cliente);
	    	cupom.setLote(lote);
	    	cupom.setRevisao(revisao);
	    	
	    	if( revisao.getNumeroRevisao().equals(Revisao.REVISAO_ZERO) ) {
	    		
	    		if ( linha.getEntityId().equals(Linha.TIPO_NAUTICA) ||  linha.getEntityId().equals(Linha.TIPO_FORCA) ) {	    			
	    			
	    			ParametroSistema parametro 		= cupomBusiness.getByParameterSystem(ParametroSistema.PRAZO_ENTREGA_NAUTICA_DIAS);
	        		
	    			int diasUteis = DateUtils.daysUtils(dataVenda, dataEntrega);
	        		
	    			//log.info("Prazo e dias úteis:" + parametro.getValorParametro() + " - " + diasUteis );
	    			
	    			if ( Integer.parseInt(parametro.getValorParametro()) < diasUteis ) {
	    				
	    				throw new BusinessRuleException("cupom.msg.error.greaterSend");  	    				
	    				
	    			} else {	    			
	    				cupom.setDataRevisao( dataEntrega );
	    				cupom.setDataVenda  ( dataVenda   );
	    				cupom.setDataEntrega( dataEntrega );
	    			}
	    			
	    		} else if ( linha.getEntityId().equals(Linha.TIPO_MOTOCICLETA) ||  linha.getEntityId().equals(Linha.TIPO_QUADRICICLO) ) {
	    			cupom.setDataRevisao( dataRevisao );
	    			cupom.setDataVenda  ( dataRevisao );
	    	    	cupom.setDataEntrega( dataRevisao );
	    		}
	    		
	    	} else {
	    		
	    		cupom.setDataRevisao(dataRevisao);	    		
	    	}
	    	
	    	cupom.setTipoUsoBarco(tipoUsoBarco);
	    	
	    	cupom.setDiasUso (cupomForm.get("diasUso") != null ? (Long)cupomForm.get("diasUso") : new Long(0));
	    	cupom.setHorasUso(cupomForm.get("horasUso")!= null ? (Long)cupomForm.get("horasUso"): new Long(0));
	    	cupom.setQuilometragem(cupomForm.get("quilometragem") != null ? (Long)cupomForm.get("quilometragem") : new Long(0) );
	    	
	    	cupom.setMarcaBarco (cupomForm.getString("marcaBarco"));
	    	cupom.setModeloBarco(cupomForm.getString("modeloBarco"));
	    	
	    	// **************************************************************** //
	    	// * Validações dos valores para retornar alertas ao usuário	  * //
	    	// * Salvar ou apenas Retornar mensagens de validação			  * //
	    	// **************************************************************** //
	    	
	    	//log.info(" -----> CupomActon - save - validar prazo de garantia");
	    	List alertCupom_hasWarranty = null;
	    	if ( !revisao.getNumeroRevisao().equals(Revisao.REVISAO_ZERO)) {
	    		
	    		alertCupom_hasWarranty = cupomBusiness.validatePrazo(cupom, faturamento);	 

	    		Iterator itPrazo = alertCupom_hasWarranty.iterator();
	 	    	while ( itPrazo.hasNext() ) {
	 	    		
	 	    		AlertCupom alertCupom_Prazo = (AlertCupom) itPrazo.next();
	 	    	
	 		    	// Verificamos se existe alerta e  se é erro
	 		    	if ( alertCupom_Prazo != null ) {
	 		    		
	 		    		ControllerHelper.prepare(alertCupom_Prazo, (Long)usuario.getEntityId());
	 		    		
	 		    		if ( alertCupom_Prazo.getAlertCupomKey().indexOf("error") != -1   ) {
	 			    		
	 		    			throw new BusinessRuleException(alertCupom_Prazo.getAlertCupomKey());
	 			    		
	 			    	} else {
	 			    		
			    			// sjoviano 04-03-2016 INI #01
			    			if ("garantia.msg.warning.prazo.garantia.dif".equals(alertCupom_Prazo.getAlertCupomKey())) {
			    				if (Integer.parseInt(revisao.getNumeroRevisao() + "") > 1) {
			    				  //ValorServico valorServico = cupom.getValorServico();
			    				  //System.out.println("sjoviano entrou 1 valorServico " + cupom.getValorServico().getValorMaoObra());
			    				  //valorServico.setValorMaoObra(new Double(0.0));
			    				  //cupom.setValorServico(valorServico);
			 			    		try {
			 			    			cupom.setValorRevisao(0);
			 			    			cupom.setStatusMovId(StatusGarantia.STATUS_PERIODICA);
			 			    		} catch (NullPointerException e) {
			 			    			//System.out.println("sjoviano entrou 1 valorTotalRevisao null");
			 			    		}			    					
			    				}
			    			}
			    			
			    			if ("garantia.msg.warning.prazo.garantia".equals(alertCupom_Prazo.getAlertCupomKey())) {
			    				//ValorServico valorServico = (ValorServico)cupom.getValorServico();
			    				//System.out.println("sjoviano entrou 1 valorServico " + cupom.getValorServico().getValorMaoObra());
			    				//valorServico.setValorMaoObra(new Double(0.0));
			    				//cupom.setValorServico(valorServico);
		 			    		try {
		 			    			cupom.setValorRevisao(0);
		 			    			cupom.setStatusMovId(StatusGarantia.STATUS_PERIODICA);
		 			    		} catch (NullPointerException e) {
		 			    			//System.out.println("sjoviano entrou 2 valorTotalRevisao null");
		 			    		}
			    			}
			    			// sjoviano 04-03-2016 FIM #01
  		    			    
	 			    		
	 			    		messagesWarning.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage(alertCupom_Prazo.getAlertCupomKey()) );
	 	    	            super.addWarningMessages(request, messagesWarning); 	 
	 			    		
	 			    	}
	 		    		
	 		    	}	
	 		    	
	 	    	}	    	
	    	}
	    	
	    	// Validação da Quilometragem para linhas Motocicleta e Quadriciclo 
	    	// Validação de intervalo de tempo para linhas Náutica e Produtos de força
	    	// Quando for revisão de entrega, não precisamos fazer a validação
	    	AlertCupom alertCupom_KmCondiz = null;
	    	
	    	// Só precisamos validar a KM se não for a revisão de entrega
	    	if ( !revisao.getNumeroRevisao().equals(Revisao.REVISAO_ZERO)) {
	    		alertCupom_KmCondiz = cupomBusiness.validateKmCondiz(cupom, linha, isConcessionaria);
	    		//log.info("Validação da Quilometragem para linhas Motocicleta e Quadriciclo! alertCupom_KmCondiz:" + (alertCupom_KmCondiz != null));
	    	}
	    	
	    	// Validação de revisões anteriores
	    	AlertCupom alertCupom_RevisaoAnterior = null;
	    	ParametroSistema parametroReviPrev = cupomBusiness.getByParameterSystem(ParametroSistema.VALIDAR_REVISAO_ANTERIOR);
	    	
	    	//log.info("Parâmetro para validação:" + parametroReviPrev.getValorParametro());
	    	
	    	// A validação será realizada se o parâmetro de sistema for igual a 'S'
	    	if ( parametroReviPrev != null ) { 
	    		if ( "S".equalsIgnoreCase(parametroReviPrev.getValorParametro()) )
	    			alertCupom_RevisaoAnterior = cupomBusiness.validateRevisaoAnterior(cupom, linha, isConcessionaria, alterState);
	    	} else {
	    		
	    		throw new BusinessRuleException("cupom.msg.error.pameterNotFount");
	    		
	    	}		
	    	//AlertCupom alertCupom_RevisaoAnterior = cupomBusiness.validateRevisaoAnterior(cupom, linha, isConcessionaria, alterState);

	    	//log.info("Validação de revisões anteriores! alertCupom_RevisaoAnterior:" + (alertCupom_RevisaoAnterior != null));
	    		    	
	    	// Validação Prazo de envio da revisão
	    	AlertCupom alertCupom_PrazoEnvio = cupomBusiness.validatePrazoEnvio(cupom);	
	    	//log.info("Validação Prazo de envio da revisão! alertCupom_PrazoEnvio:" + (alertCupom_PrazoEnvio != null));
	    	
	    	if ( !(alertCupom_KmCondiz == null && alertCupom_RevisaoAnterior == null && alertCupom_PrazoEnvio == null) ) {
	    			    		
	    		if ( alertCupom_KmCondiz != null ) {
	    			
	    			alertCupom_KmCondiz.setCriadoPor((Long)usuario.getIdentifier());
	    			alertCupom_KmCondiz.setDataCriacao(new Date());
	    			alertCupom_KmCondiz.setAtualizadoPor((Long)usuario.getIdentifier());
	    			alertCupom_KmCondiz.setDataAtualizacao(new Date());
	    			
	    			alertas.add(alertCupom_KmCondiz);
	    			//alertCupomBusiness.save(alertCupom_KmCondiz);
	    		
		    		if ( alertCupom_KmCondiz.getAlertCupomKey().indexOf("error") != -1 ) {
		    			
		    			throw new BusinessRuleException(alertCupom_KmCondiz.getAlertCupomKey());
		    			
		    		} else if ( alertCupom_KmCondiz.getAlertCupomKey().indexOf("warning") != -1 ) {
		    			
		    			messagesWarning.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage(alertCupom_KmCondiz.getAlertCupomKey()) );
	    	            super.addWarningMessages(request, messagesWarning);    			
		    			
		    		}
		    		
	    		} 
	    		
	    		if ( alertCupom_RevisaoAnterior != null ) {
	    			
	    			alertCupom_RevisaoAnterior.setCriadoPor((Long)usuario.getIdentifier());
	    			alertCupom_RevisaoAnterior.setDataCriacao(new Date());
	    			alertCupom_RevisaoAnterior.setAtualizadoPor((Long)usuario.getIdentifier());
	    			alertCupom_RevisaoAnterior.setDataAtualizacao(new Date());
	    			
	    			alertas.add(alertCupom_RevisaoAnterior);
	    			//alertCupomBusiness.save(alertCupom_RevisaoAnterior);
	    			
	    			if ( alertCupom_RevisaoAnterior.getAlertCupomKey().indexOf("error") != -1 ) {
		    			
		    			throw new BusinessRuleException(alertCupom_RevisaoAnterior.getAlertCupomKey());
		    			
		    		} else if ( alertCupom_RevisaoAnterior.getAlertCupomKey().indexOf("warning") != -1 ) {
		    			
		    			messagesWarning.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage(alertCupom_RevisaoAnterior.getAlertCupomKey()) );
	    	            super.addWarningMessages(request, messagesWarning);    			
		    			
		    		}	    			
	    			
	    		}
	    		
	    		if ( alertCupom_PrazoEnvio != null ) {
	    			
	    			alertCupom_PrazoEnvio.setCriadoPor((Long)usuario.getIdentifier());
	    			alertCupom_PrazoEnvio.setDataCriacao(new Date());
	    			alertCupom_PrazoEnvio.setAtualizadoPor((Long)usuario.getIdentifier());
	    			alertCupom_PrazoEnvio.setDataAtualizacao(new Date());
	    			
	    			alertas.add(alertCupom_PrazoEnvio);
	    			//alertCupomBusiness.save(alertCupom_PrazoEnvio);
	    			
	    			if ( alertCupom_PrazoEnvio.getAlertCupomKey().indexOf("error") != -1 ) {
		    			
		    			throw new BusinessRuleException(alertCupom_PrazoEnvio.getAlertCupomKey());
		    			
		    		} else if ( alertCupom_PrazoEnvio.getAlertCupomKey().indexOf("warning") != -1 ) {
		    			
		    			messagesWarning.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage(alertCupom_PrazoEnvio.getAlertCupomKey()) );
	    	            super.addWarningMessages(request, messagesWarning);    			
		    			
		    		}
	    			
	    		}
	    		
	    	}
    		
	    	//log.info("Validate:" + validado);
	    	
	    	// Verificamos se a validação foi confirmada e salvamos
	    	if ( validado ) {
	    		
	    		ControllerHelper.prepare(cupom, (Long)usuario.getIdentifier());
	    			    			    		
	    		cupomBusiness.save(cupom, alertas);
	    		    		
	    		messagesSuccess.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("cupom.msg.success.cupom", String.valueOf(cupom.getEntityId()))); // ist_edson 01/07/3013
	            super.addSuccessMessages(request, messagesSuccess);
	            
	            request.setAttribute("loteId", cupomForm.get("loteId"));
	            
	            return mapping.findForward("_listPostInsert");
	            //return this.list(mapping, form, request, response);
	        	
	    	} else {
	    		
	    		cupomForm.set( "validado", "true"  );
	    		
	    		String msg = campaignBusiness.checkCampaingByChassi(chassi, false);
	    		
	    		if("".equals(msg))
	    			this.setConfirmMessage(request, "Deseja continuar a operação?", "save()", "cancel()");
	    		else
	    			this.setConfirmMessage(request, msg, "save()", "cancel()");
	    		
	    		request.setAttribute("loteId", cupomForm.get("loteId"));
	    		
	    		return mapping.findForward("_form");
	    		
	    	}
	    		
            
    	} catch ( BusinessRuleException rExp) { 
    		
    		// Quando tentamos recuperar um objeto e ele retornar null, apresentamos esta mensagem
    		messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage(rExp.getMessage()) );
            super.addProblemMessages(request, messagesProblem);   
            
            return mapping.findForward("_form");
            
    	} catch ( BusinessException rExp) { 
            
            throw new ControllerException(rExp);
    		
    	} catch ( InvalidSessionException isExp ) {
    		
    		throw new ControllerException(isExp);
    		
		}   
    }
    
    /** Tarefa: Remove um cupom.
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
    public ActionForward delete( ActionMapping mapping
                               , ActionForm form
                               , HttpServletRequest request
                               , HttpServletResponse response) throws ControllerException {

    	try {
    		
			Long entityId = Long.valueOf( request.getParameter("cupomId") );
			
			CupomBusiness cupomBusiness	= (CupomBusiness) springContext.getBean("cupomBO");	
			Cupom         cupom         = cupomBusiness.get(entityId);
			cupomBusiness.remove( cupom );
			
			request.setAttribute("loteId", (Integer)cupom.getLote().getEntityId());
			
			return this.list( mapping, form, request, response);
			
		} catch (NumberFormatException nfExp) {
			throw new ControllerException( nfExp );
			
		} catch (BeansException bExp) {
			throw new ControllerException( bExp );
			
		} catch (BusinessException bExp) {
			throw new ControllerException( bExp );

		}

    }
    
    /** Tarefa: Coleta as informações do form de cupom e monta um TO dispachando
     *          ao cadastro de Cliente.
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
    public ActionForward includeClient( ActionMapping mapping
                             , ActionForm form
                             , HttpServletRequest request
                             , HttpServletResponse response) throws ControllerException {
    	
    	DynaValidatorActionForm cupomForm = (DynaValidatorActionForm) form;
    	
    	//log.info(" -----> Inclusão de Cliente");
    	
    	// Coletamos os itens do formulário
    	String  chassi  	   = cupomForm.getString( "chassi"        );    	
    	Long    linhaId 	   = (Long)cupomForm.get( "linhaId"       );
    	Long    cupomId 	   = (Long)cupomForm.get( "entityId"      );
    	Long    cupomCode 	   = (Long)cupomForm.get( "cupomCode"     );
    	Long    numeroRevisao  = (Long)cupomForm.get( "numeroRevisao" ); 
    	
    	Integer loteId 		   = cupomForm.get( "loteId"         ) != null ? (Integer)cupomForm.get( "loteId"         ) : null;
    	Long	tipoUsoId	   = cupomForm.get( "tipoUsoId"      ) != null ? (Long)cupomForm.get   ( "tipoUsoId"      ) : null;
    	
    	String  cpfCnpjCliente = ( cupomForm.getString("cpfCnpjCliente" ) != null ) && 
								 ( !cupomForm.getString("cpfCnpjCliente").equals("") ) ? 
								 cupomForm.getString("cpfCnpjCliente"): 
								 "" ;
    	
    	// Tratamento de Datas Fornecidas
    	Date dataRevisao = "".equals(cupomForm.getString( "dataRevisao" )) ? 
    					   null :
    					   DateUtils.format(cupomForm.getString( "dataRevisao" ),"dd/MM/yyyy");
    	    	
    	Date dataEntrega = "".equals(cupomForm.getString( "dataEntrega" ))? 
		   				   null :
		   				   DateUtils.format(cupomForm.getString( "dataEntrega" ),"dd/MM/yyyy"); 
    	
    	Long diasUso 	   = cupomForm.get( "diasUso"       ) != null ? (Long)cupomForm.get( "diasUso"       ) : new Long(0);
    	Long horasUso 	   = cupomForm.get( "horasUso"      ) != null ? (Long)cupomForm.get( "horasUso"      ) : new Long(0);
    	Long quilometragem = cupomForm.get( "quilometragem" ) != null ? (Long)cupomForm.get( "quilometragem" ) : new Long(0);
    	
    	String marcaBarco  = cupomForm.getString( "marcaBarco"  );
    	String modeloBarco = cupomForm.getString( "modeloBarco" );
    	
    	//log.info(" -----> Montando o objeto CupomTO");
    	
    	// Montagem do objeto cupomTO    	
    	CupomTO cupomTO = new CupomTO();
    	
    	cupomTO.setCupomId(cupomId);
    	cupomTO.setChassi(chassi);
    	cupomTO.setLinhaId(linhaId);
    	cupomTO.setTipoUsoId(tipoUsoId);
    	cupomTO.setCupomCode(cupomCode);
    	cupomTO.setNumeroRevisao(numeroRevisao);
    	cupomTO.setLoteId(loteId);
    	cupomTO.setCnpj(cpfCnpjCliente);
    	cupomTO.setDataRevisao(dataRevisao);
    	cupomTO.setDataEntrega(dataEntrega);
    	cupomTO.setDiasUso(diasUso);
    	cupomTO.setHorasUso(horasUso);
    	cupomTO.setQuilometragem(quilometragem);
    	cupomTO.setMarcaBarco(marcaBarco);
    	cupomTO.setModeloBarco(modeloBarco);   
    	
    	// Colocamos o objeto cupomTO na sessão    	
    	YmSessionHelper.setCupomTOToSession(request, cupomTO);
    	
    	//log.info(" -----> Direcionando para a Inclusão de Cliente");
    	
    	return mapping.findForward("_customerInclude");
    }
    
    /** Tarefa: Coleta as informações do form de cupom e monta um TO dispachando
     *          ao cadastro de Cliente.
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
     *  @author Resource IT - Demanda 0003
     *  
     */ 
    public ActionForward editByCnpjClient( ActionMapping mapping
                             , ActionForm form
                             , HttpServletRequest request
                             , HttpServletResponse response) throws ControllerException {
    	
    	DynaValidatorActionForm cupomForm = (DynaValidatorActionForm) form;
    	
    	//log.info(" -----> Alteracao de Cliente");
    	
    	// Coletamos os itens do formulário
    	String  chassi  	   = cupomForm.getString( "chassi"        );    	
    	Long    linhaId 	   = (Long)cupomForm.get( "linhaId"       );
    	Long    cupomId 	   = (Long)cupomForm.get( "entityId"      );
    	Long    cupomCode 	   = (Long)cupomForm.get( "cupomCode"     );
    	Long    numeroRevisao  = (Long)cupomForm.get( "numeroRevisao" ); 
    	
    	Integer loteId 		   = cupomForm.get( "loteId"         ) != null ? (Integer)cupomForm.get( "loteId"         ) : null;
    	Long	tipoUsoId	   = cupomForm.get( "tipoUsoId"      ) != null ? (Long)cupomForm.get   ( "tipoUsoId"      ) : null;
    	
    	String  cpfCnpjCliente = ( cupomForm.getString("cpfCnpjCliente" ) != null ) && 
								 ( !cupomForm.getString("cpfCnpjCliente").equals("") ) ? 
								 cupomForm.getString("cpfCnpjCliente"): 
								 "" ;
    	
    	// Tratamento de Datas Fornecidas
    	Date dataRevisao = "".equals(cupomForm.getString( "dataRevisao" )) ? 
    					   null :
    					   DateUtils.format(cupomForm.getString( "dataRevisao" ),"dd/MM/yyyy");
    	    	
    	Date dataEntrega = "".equals(cupomForm.getString( "dataEntrega" ))? 
		   				   null :
		   				   DateUtils.format(cupomForm.getString( "dataEntrega" ),"dd/MM/yyyy"); 
    	
    	Long diasUso 	   = cupomForm.get( "diasUso"       ) != null ? (Long)cupomForm.get( "diasUso"       ) : new Long(0);
    	Long horasUso 	   = cupomForm.get( "horasUso"      ) != null ? (Long)cupomForm.get( "horasUso"      ) : new Long(0);
    	Long quilometragem = cupomForm.get( "quilometragem" ) != null ? (Long)cupomForm.get( "quilometragem" ) : new Long(0);
    	
    	String marcaBarco  = cupomForm.getString( "marcaBarco"  );
    	String modeloBarco = cupomForm.getString( "modeloBarco" );
    	
    	//log.info(" -----> Montando o objeto CupomTO");
    	
    	// Montagem do objeto cupomTO    	
    	CupomTO cupomTO = new CupomTO();
    	
    	cupomTO.setCupomId(cupomId);
    	cupomTO.setChassi(chassi);
    	cupomTO.setLinhaId(linhaId);
    	cupomTO.setTipoUsoId(tipoUsoId);
    	cupomTO.setCupomCode(cupomCode);
    	cupomTO.setNumeroRevisao(numeroRevisao);
    	cupomTO.setLoteId(loteId);
    	cupomTO.setCnpj(cpfCnpjCliente);
    	cupomTO.setDataRevisao(dataRevisao);
    	cupomTO.setDataEntrega(dataEntrega);
    	cupomTO.setDiasUso(diasUso);
    	cupomTO.setHorasUso(horasUso);
    	cupomTO.setQuilometragem(quilometragem);
    	cupomTO.setMarcaBarco(marcaBarco);
    	cupomTO.setModeloBarco(modeloBarco);   
    	
    	// Colocamos o objeto cupomTO na sessão    	
    	YmSessionHelper.setCupomTOToSession(request, cupomTO);
    	
    	//log.info(" -----> Direcionando para a Inclusão de Cliente");
    	
    	return mapping.findForward("_customerEditByCnpj");
    }
    
    /** Tarefa: Recebe o retorno do cadstro de cliente e monta novamente
     *          o form para conclusão do cadastro do cupom.
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
    public ActionForward postIncludeClient( ActionMapping mapping
                             , ActionForm form
                             , HttpServletRequest request
                             , HttpServletResponse response) throws ControllerException {
    	
    	DynaValidatorActionForm cupomForm = (DynaValidatorActionForm) form;
    	
    	//log.info(" -----> postIncludeClient");
    	
    	try {
    		
	    	RevisaoBusiness       revisaoBusiness       = (RevisaoBusiness)      springContext.getBean("revisaoBO");
	    	TipoUsoBarcoBusiness  tipoUsobarcoBusiness  = (TipoUsoBarcoBusiness) springContext.getBean("tipoUsoBarcoBO");
	    	LinhaBusiness		  linhaBusiness		    = (LinhaBusiness)		 springContext.getBean("linhaBO"		 );
	    	
	    	CupomTO cupomTO = YmSessionHelper.getCupomTOFromSession(request);
	    	
	    	List  listRevisao = revisaoBusiness.listApproachedByChassi(ControllerHelper.getModeloByChassi(cupomTO.getChassi()));
	    	List  listUseType = tipoUsobarcoBusiness.list();
	    	Linha linha 	  = linhaBusiness.get(cupomTO.getLinhaId());
	    	
	    	cupomForm.set("chassi"        , cupomTO.getChassi()            );
	    	cupomForm.set("entityId"      , cupomTO.getCupomId()           );
	    	cupomForm.set("linhaId"       , cupomTO.getLinhaId()           );
	    	cupomForm.set("tipoUsoId"     , cupomTO.getTipoUsoId()         );
	    	cupomForm.set("cupomCode"     , cupomTO.getCupomCode() 		   );
	    	cupomForm.set("numeroRevisao" , cupomTO.getNumeroRevisao()     );
	    	cupomForm.set("loteId"        , cupomTO.getLoteId()            );
	    	cupomForm.set("cpfCnpjCliente", cupomTO.getCnpj()              );
	    	cupomForm.set("clienteNome"   , cupomTO.getClienteNome()       );
	    	cupomForm.set("dataRevisao"   , cupomTO.getMaskedDataRevisao() );
	    	cupomForm.set("dataEntrega"   , cupomTO.getMaskedDataEntrega() );
	    	cupomForm.set("diasUso"       , cupomTO.getDiasUso()           );
	    	cupomForm.set("horasUso"      , cupomTO.getHorasUso()          );
	    	cupomForm.set("quilometragem" , cupomTO.getQuilometragem()     );
	    	cupomForm.set("marcaBarco"    , cupomTO.getMarcaBarco()        );
	    	cupomForm.set("modeloBarco"   , cupomTO.getModeloBarco()       );
	    	cupomForm.set("listRevisao"   , listRevisao      			   );
	    	cupomForm.set("listUseType"	  , listUseType					   );
    		cupomForm.set("validate"	  , new Boolean(false)		       );
    		cupomForm.set("sysDate"		  , DateUtils.applyMask(new Date()));
    		cupomForm.set("linhaDescricao", linha.getDescricao()		   );
    		
    		YmSessionHelper.removeCupomTOToSession(request);
	    	
	    	this.setJavaScriptExecuter(request, "setTitle();fieldFocus('cpfCnpjCliente');" );
	    	
	    	return mapping.findForward("_form");
	    	
    	} catch (BusinessException bExp) {
    		
            throw new ControllerException(bExp);
            
        }
    	
    } 	
   

}
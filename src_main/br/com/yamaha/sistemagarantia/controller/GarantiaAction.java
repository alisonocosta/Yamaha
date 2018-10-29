/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......GarantiaAction.java
 *   .: Criação.....05 de junho, 16:15
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Action para sistema de garantias.
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

import br.com.resource.infra.controller.InfraDispatchAction;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.BusinessRuleException;
import br.com.resource.infra.exception.ControllerException;
import br.com.resource.infra.security.SystemUser;
import br.com.resource.infra.security.exception.InvalidSessionException;
import br.com.resource.infra.security.helper.UserHelper;
import br.com.resource.infra.utils.DateUtils;
import br.com.resource.infra.utils.NumberUtils;
import br.com.yamaha.sistemagarantia.business.AutEspecSGBusiness;
import br.com.yamaha.sistemagarantia.business.CampaignBusiness;
import br.com.yamaha.sistemagarantia.business.FaturamentoBusiness;
import br.com.yamaha.sistemagarantia.business.GarantiaHeaderBusiness;
import br.com.yamaha.sistemagarantia.business.GrupoServicoBusiness;
import br.com.yamaha.sistemagarantia.business.InfoMercadoBusiness;
import br.com.yamaha.sistemagarantia.business.LoteBusiness;
import br.com.yamaha.sistemagarantia.business.ModeloBusiness;
import br.com.yamaha.sistemagarantia.controller.helper.ControllerHelper;
import br.com.yamaha.sistemagarantia.controller.helper.YmSessionHelper;
import br.com.yamaha.sistemagarantia.model.AlertGarantia;
import br.com.yamaha.sistemagarantia.model.AutorizacaoEspecialSG;
import br.com.yamaha.sistemagarantia.model.Campaign;
import br.com.yamaha.sistemagarantia.model.CampaignBilling;
import br.com.yamaha.sistemagarantia.model.CampaignPiece;
import br.com.yamaha.sistemagarantia.model.CausaProblemaGarantia;
import br.com.yamaha.sistemagarantia.model.Concessionaria;
import br.com.yamaha.sistemagarantia.model.Condicao;
import br.com.yamaha.sistemagarantia.model.CondicaoProblemaGarantia;
import br.com.yamaha.sistemagarantia.model.DiagnosticoProblemaGarantia;
import br.com.yamaha.sistemagarantia.model.Faturamento;
import br.com.yamaha.sistemagarantia.model.GarantiaHeader;
import br.com.yamaha.sistemagarantia.model.GarantiaLine;
import br.com.yamaha.sistemagarantia.model.InfoMercado;
import br.com.yamaha.sistemagarantia.model.Linha;
import br.com.yamaha.sistemagarantia.model.Lote;
import br.com.yamaha.sistemagarantia.model.Modelo;
import br.com.yamaha.sistemagarantia.model.ParametroSistema;
import br.com.yamaha.sistemagarantia.model.Recall;
import br.com.yamaha.sistemagarantia.model.RecallServico;
import br.com.yamaha.sistemagarantia.model.Servico;
import br.com.yamaha.sistemagarantia.model.ServicoGrupo;
import br.com.yamaha.sistemagarantia.model.Sintoma;
import br.com.yamaha.sistemagarantia.model.SolucaoProblemaGarantia;
import br.com.yamaha.sistemagarantia.model.StatusGarantia;
import br.com.yamaha.sistemagarantia.model.StatusLote;
import br.com.yamaha.sistemagarantia.model.TempoPadrao;
import br.com.yamaha.sistemagarantia.model.Usuario;
import br.com.yamaha.sistemagarantia.model.ValorServico;
import br.com.yamaha.sistemagarantia.model.to.GarantiaTO;
import br.com.yamaha.sistemagarantia.processo.business.AnaliseGarantiaBusiness;

/** Action responsável por fornecer controle às funcionalidades
 *  do módulo de garantias do sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class GarantiaAction extends InfraDispatchAction {

	private static final long serialVersionUID = 1L;
	
	
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
            Lote 		lote 	        = null;
            
            DynaValidatorActionForm garantiaForm = (DynaValidatorActionForm) form;
            garantiaForm.initialize(mapping);
    		
            int loteId = request.getParameter("loteId") != null ? Integer.parseInt(request.getParameter("loteId"))
					  : ((Integer)request.getAttribute("loteId")).intValue();
               
    		LoteBusiness loteBusiness = (LoteBusiness)  springContext.getBean("loteBO");
    		GarantiaHeaderBusiness garantiaHeaderBusiness = (GarantiaHeaderBusiness) springContext.getBean("garantiaHeaderBO");
    		
    		lote = garantiaHeaderBusiness.getLote(new Integer(loteId));
    		
    		Integer linhasPrograma = loteBusiness.getLinhasPagina("SG_LT_REV");
    		
    		List listGarantias = garantiaHeaderBusiness.listByLote(lote);
    		    			
    		// Flag para habilitar ou não o botão de incluir no form
    		Boolean validInclude = Boolean.FALSE;
    			
    		if(lote.getValidInclude() && listGarantias != null && listGarantias.size() < 1)
    			validInclude= Boolean.TRUE;
    		
    		// Flag para liberar a alteração na Garantia
    		//boolean validAlter   = lote.getValidAlter();
    		boolean validAlter   = lote.getValidAlterNew();
    		
    		garantiaForm.set( "task"          , "list");    		
    		garantiaForm.set( "listGarantias" , listGarantias);
    		
            request.setAttribute("lote"		    , lote);
            request.setAttribute("validInclude" , validInclude);
            request.setAttribute("validAlter"   , new Boolean(validAlter));
            request.setAttribute("qtdeLinhas"	, linhasPrograma.toString() );
            
            YmSessionHelper.removeFromSession(request, YmSessionHelper.SESSION_GARANTIATO);
            
    		return mapping.findForward("_list");
    		
    	} catch (BusinessException bExp) {
    		
            throw new ControllerException(bExp);
            
        } 
    	
    }
	
    /** Tarefa: Formulário de inclusão de SG.
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
    	    	
    	ActionMessages 			messagesProblem    = new ActionMessages();
    	
    	int loteId = request.getParameter("loteId") != null ? Integer.parseInt(request.getParameter("loteId"))
    													  : ((Integer)request.getAttribute("loteId")).intValue();
    	
    	DynaValidatorActionForm garantiaForm = (DynaValidatorActionForm) form;

    	if ( loteId != 0 ) {

    		try {
    			
    			GarantiaHeaderBusiness garantiaHeaderBusiness = (GarantiaHeaderBusiness) springContext.getBean("garantiaHeaderBO");
				LoteBusiness           loteBusiness           = (LoteBusiness) springContext.getBean("loteBO");
				InfoMercadoBusiness    infoMercadoBusiness    = (InfoMercadoBusiness) springContext.getBean("infoMercadoBO");
				Lote                   lote                   = (Lote) loteBusiness.get(new Integer(loteId));					
				
				// Validamos a permissão do lote para inclusão de nova Garantia
				if ( lote.getValidInclude() ) {
					
					garantiaForm.set( "lote"  		  , lote.getEntityId()    									);				
					garantiaForm.set( "listSintomas"  , garantiaHeaderBusiness.listSintomas(  lote.getLinha() ) );
					garantiaForm.set( "listCondicoes" , garantiaHeaderBusiness.listCondicoes( lote.getLinha() ) );
					garantiaForm.set( "listRecall"    , new ArrayList() 										);	
					garantiaForm.set( "listCampaign"  , new ArrayList() 										);
					garantiaForm.set( "linhaId"  	  , lote.getLinha().getEntityId()							);	
					
					garantiaForm.set("listTipoGasolina", infoMercadoBusiness.listTipoGasolina());
					garantiaForm.set("listTipoProduto" , infoMercadoBusiness.listTipoProdutoInfo() );
					garantiaForm.set("listTipoUso"     , infoMercadoBusiness.listTipoUso()); 
					
					garantiaForm.set( "sysDate"		  , DateUtils.applyMask(new Date()));
					
					request.setAttribute("idTipo"       , lote.getLinha().getEntityId()      );
					request.setAttribute("statusLoteId" , lote.getStatusLote().getEntityId() );
					request.setAttribute("consulta"     , new Boolean(false)      		     );				
					
					this.setJavaScriptExecuter(request, "setTitle()" );
					
					return mapping.findForward("_form");
				
				} else {
					
					// Retornamos para o form com a mensagem				
					messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("garantia.msg.error.add",lote.getEntityId()) );
		            super.addProblemMessages(request, messagesProblem); 
		            
		            request.setAttribute("loteId", lote.getEntityId() );
		            
		            return this.list(mapping, form, request, response);		            
				}
			
    		} catch (BusinessException bExp) {
    			
    			throw new ControllerException( bExp );    			
    		} 
    		
    	} else {
    		
    		throw new ControllerException("Nenhum identificador de lote encontrado.");
    		
    	}			
        
    }
    
    /** Tarefa: Formulário de inclusão de SG, utilizado para incluir um novo lote e depois apresentar o formulário.
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
    public ActionForward addWithNewLote( ActionMapping mapping
                            , ActionForm form
                            , HttpServletRequest request
                            , HttpServletResponse response) throws ControllerException {
    	    	
    	ActionMessages 			messagesProblem    = new ActionMessages();
    	    	
    	int loteId = request.getParameter("loteId") != null ? Integer.parseInt(request.getParameter("loteId"))
    			: ((Integer)request.getAttribute("loteId")).intValue();

    	DynaValidatorActionForm garantiaForm = (DynaValidatorActionForm) form;

    	if ( loteId != 0 ) {

    		try {

    			GarantiaHeaderBusiness garantiaHeaderBusiness = (GarantiaHeaderBusiness) springContext.getBean("garantiaHeaderBO");
    			LoteBusiness           loteBusiness           = (LoteBusiness) springContext.getBean("loteBO");
    			InfoMercadoBusiness    infoMercadoBusiness    = (InfoMercadoBusiness) springContext.getBean("infoMercadoBO");
    			Lote                   lote                   = (Lote) loteBusiness.get(new Integer(loteId));					
    			SystemUser			   usuario			= (Usuario)UserHelper.getBoundedUser(request.getSession());	 
    			
    			//Criamos um novo lote com base no lote anterior
    			Lote   newLote  = new Lote();
    			newLote.setConcessionaria(lote.getConcessionaria());
    			newLote.setLinha(lote.getLinha());
    			newLote.setTipoLote(lote.getTipoLote());
    			StatusLote statusLote = new StatusLote();
    			statusLote.setEntityId(StatusLote.STATUS_EM_DIGITACAO);
    			newLote.setStatusLote(statusLote);
    			newLote.setDataLote(new Date());
    			ControllerHelper.prepare( newLote, (Long)usuario.getIdentifier() );
    		    	
	            // Salvamos o bean Lote
		    	loteBusiness.save(newLote);

				garantiaForm.set( "lote"  		  , newLote.getEntityId()    									);				
				garantiaForm.set( "listSintomas"  , garantiaHeaderBusiness.listSintomas(  newLote.getLinha() ) );
				garantiaForm.set( "listCondicoes" , garantiaHeaderBusiness.listCondicoes( newLote.getLinha() ) );
				garantiaForm.set( "listRecall"    , new ArrayList() 										);	
				garantiaForm.set( "listCampaign"  , new ArrayList() 										);
				garantiaForm.set( "linhaId"  	  , newLote.getLinha().getEntityId()							);	
				garantiaForm.set( "sysDate"		  , DateUtils.applyMask(new Date()));
				
				garantiaForm.set("listTipoGasolina", infoMercadoBusiness.listTipoGasolina());
				garantiaForm.set("listTipoProduto" , infoMercadoBusiness.listTipoProdutoInfo() );
				garantiaForm.set("listTipoUso"     , infoMercadoBusiness.listTipoUso()); 

				request.setAttribute("idTipo"       , newLote.getLinha().getEntityId()      );
				request.setAttribute("statusLoteId" , newLote.getStatusLote().getEntityId() );
				request.setAttribute("consulta"     , new Boolean(false)      		     );				

				this.setJavaScriptExecuter(request, "setTitle()" );

				return mapping.findForward("_form");
		

    		} catch (BusinessException bExp) {

    			throw new ControllerException( bExp );   
    			
    		} catch ( InvalidSessionException isExp) {
            	
            	throw new ControllerException(isExp);
            	
            }


    	} else {

    		throw new ControllerException("Nenhum identificador de lote encontrado.");

    	}	    			
        
    }
    
    /** Tarefa: Monta o Formulário de inclusão de SG para Recall.
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
    public ActionForward addRecall( ActionMapping mapping
                            , ActionForm form
                            , HttpServletRequest request
                            , HttpServletResponse response) throws ControllerException {
    	     	
    	DynaValidatorActionForm garantiaForm = (DynaValidatorActionForm) form;
    	
    	Integer loteId = garantiaForm.get("lote") != null ? (Integer)garantiaForm.get("lote"): new Integer(0);

    	//log.info("LoteId:" + loteId);
    	
    	if ( !loteId.equals(new Integer(0)) ) {

    		try {
    			
    			GarantiaHeaderBusiness garantiaHeaderBusiness = (GarantiaHeaderBusiness) springContext.getBean("garantiaHeaderBO");
				LoteBusiness           loteBusiness           = (LoteBusiness) springContext.getBean("loteBO");
				Lote                   lote                   = (Lote) loteBusiness.get(loteId);
				
				request.setAttribute("idTipo" , lote.getLinha().getEntityId() );
				request.setAttribute("consulta" , new Boolean(false)          );
				
				String chassi = garantiaForm.getString("chassi");
				
				Long recallId =  garantiaForm.get("recallId") != null ? (Long)garantiaForm.get("recallId") : new Long(0);
				
				Recall recall   =  garantiaHeaderBusiness.getRecall(recallId);
				
				//log.info("Recall:" + (recall != null));
				
				if ( recall != null) { 
					
					List listRecall = garantiaHeaderBusiness.listRecall(chassi);
				
					garantiaForm.set( "sintoma"            , recall.getSintoma().getEntityId() );
					garantiaForm.set( "sintomaDescRecall"  , recall.getSintoma().getDescricaoCode().toUpperCase() );
					
					garantiaForm.set( "condicao"           , recall.getCondicao().getEntityId() );
					garantiaForm.set( "condicaoDescRecall" , recall.getCondicao().getDescricaoCode().toUpperCase() );
					
					garantiaForm.set( "listServicos"       , recall.getServicos() );
					
					garantiaForm.set( "listRecall"    , listRecall );	
					garantiaForm.set( "listCampaign"  , new ArrayList()	);
					garantiaForm.set( "listSintomas"  , new ArrayList() );
					garantiaForm.set( "listCondicoes" , new ArrayList() );					
					garantiaForm.set( "isRecall"      , "true" );
					
					garantiaForm.set( "recallId"      , recallId );
					
					this.setJavaScriptExecuter(request, "setTitleRecall()" );
					
					return mapping.findForward("_form");
					
				} else {
					
					request.setAttribute("lote", lote.getEntityId());
					
					return this.add(mapping, form, request, response);
					
				}
			
    		} catch (BusinessException bExp) {
    			
    			throw new ControllerException( bExp );
    			
    		} 
    		
    	} else {
    		
    		throw new ControllerException("Nenhum identificador de lote encontrado.");
    		
    	}
			
        
    }
    
    /** Tarefa: Monta o Formulário de inclusão de SG para uma Campanha de Modificação Técnica.
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
    public ActionForward addCampaign( ActionMapping mapping
                            , ActionForm form
                            , HttpServletRequest request
                            , HttpServletResponse response) throws ControllerException {
    	     	
    	DynaValidatorActionForm garantiaForm = (DynaValidatorActionForm) form;
    	
    	Integer loteId = garantiaForm.get("lote") != null ? (Integer)garantiaForm.get("lote"): new Integer(0);
    	
    	if ( !loteId.equals(new Integer(0)) ) {

    		try {
    			
    			GarantiaHeaderBusiness garantiaHeaderBusiness = (GarantiaHeaderBusiness) springContext.getBean("garantiaHeaderBO");
    			CampaignBusiness 	   campaignBusiness 	  = (CampaignBusiness) springContext.getBean("campaignBO");
    			FaturamentoBusiness    faturamentoBusiness    = (FaturamentoBusiness) springContext.getBean("faturamentoBO");
				LoteBusiness           loteBusiness           = (LoteBusiness) springContext.getBean("loteBO");
				ModeloBusiness    	   modeloBusiness    	  = (ModeloBusiness) springContext.getBean("modeloBO");
				Lote                   lote                   = (Lote) loteBusiness.get(loteId);
				double vrLimiteSerTerc;
								
				String 	 chassi     = garantiaForm.getString("chassi");				
				Long 	 campaignId = garantiaForm.get("campaignId") != null ? (Long)garantiaForm.get("campaignId") : new Long(0);				
				//Campaign campaign 	= campaignBusiness.getById(campaignId);
				Campaign campaign 	= campaignBusiness.getCampaignById(campaignId);
				request.setAttribute("lote", lote.getEntityId());
				
				if ( campaign != null) { 
					
					Faturamento faturamento = faturamentoBusiness.getByChassi(chassi.toUpperCase());
		    		Modelo      modelo		= (Modelo) modeloBusiness.get(Long.valueOf(faturamento.getModelo()));
		    		ParametroSistema param  = garantiaHeaderBusiness.getByParameterSystem(ParametroSistema.KM_MAX_WARRANTY);
		    		
		    		ParametroSistema paramVter  = garantiaHeaderBusiness.getByParameterSystem(ParametroSistema.GARANTIA_NAUTICA_VALOR_SERVICOS_TERCEIROS);
		    		try {
		    			vrLimiteSerTerc = NumberUtils.converteStringParaDouble(paramVter.getValorParametro());
		    		} catch(NumberFormatException nFe){
		    			throw new BusinessException(nFe);
		    		}		    		
		    		
		    		Long   km    = loteBusiness.getCupomBO().getLastRevision(chassi);
		    		String kmMax = param.getValorParametro();
		    		
		    		if ( Modelo.VELOCIMETRO_NOT.equalsIgnoreCase(modelo.getVelocimetro()) ) {
		    			
		    			garantiaForm.set("hasKM", Boolean.FALSE);
		    			
		    		} else {
		    			
		    			garantiaForm.set("hasKM", Boolean.TRUE);
		    			
		    		}
					request.setAttribute("idTipo" 		  , lote.getLinha().getEntityId() );
					request.setAttribute("limitServTer"   , new Double(vrLimiteSerTerc) );
					request.setAttribute("statusLoteId"   , lote.getStatusLote().getEntityId() );
					request.setAttribute("kmMin" 		  , km );	
					request.setAttribute("kmMax" 		  , kmMax );
					
					garantiaForm.set( "chassi"       	  , chassi.toUpperCase() );
					garantiaForm.set( "campaignNum"       , campaign.getCodeCampaign() );
					garantiaForm.set( "subjectCampaign"   , campaign.getSubjectCampaign() );
					
					garantiaForm.set( "sintomaIdCampaign"    , campaign.getSintoma().getEntityId() );
					garantiaForm.set( "sintomaDescCampaign"  , campaign.getSintoma().getDescricaoCode().toUpperCase() );
					
					garantiaForm.set( "condicaoIdCampaign"   , campaign.getCondition().getEntityId() );
					garantiaForm.set( "condicaoDescCampaign" , campaign.getCondition().getDescricaoCode().toUpperCase() );
					
					garantiaForm.set( "listServicos"       , campaign.getServices() );
					garantiaForm.set( "listPieces"         , campaign.getPieces() );
					garantiaForm.set( "sysDate"		  	   , DateUtils.applyMask(new Date()));
					
					garantiaForm.set( "campaignId"         , campaignId );
					
					this.setJavaScriptExecuter(request, "setTitle()" );
					
					return mapping.findForward("_formCampaign");
					
				} else {
					
					return this.add(mapping, form, request, response);
					
				}
			
    		} catch (BusinessException bExp) {
    			
    			throw new ControllerException( bExp );
    			
    		} 
    		
    	} else {
    		
    		throw new ControllerException("Nenhum identificador de lote encontrado.");
    		
    	}  	    	
    	
    }
    
    /** Tarefa: Monta o Formulário de alteração de SG para uma Campanha de Modificação Técnica.
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
    public ActionForward alterCampaign( ActionMapping mapping
                            , ActionForm form
                            , HttpServletRequest request
                            , HttpServletResponse response) throws ControllerException {
    	     	
    	DynaValidatorActionForm garantiaForm 	= (DynaValidatorActionForm) form;
    	ActionMessages 			messagesProblem = new ActionMessages();
    	double vrLimiteSerTerc;
    	Integer garantiaId = request.getParameter("garantiaId") != null ? Integer.valueOf(request.getParameter("garantiaId")): new Integer(0);
    	
    	String readOnly = request.getParameter("readonly") != null ? request.getParameter("readonly"): "false";
    	    	
    	if ( !garantiaId.equals(new Integer(0)) ) {

    		try {
    			
    			UserHelper.getBoundedUser(request.getSession());
    			GarantiaHeaderBusiness garantiaHeaderBusiness = (GarantiaHeaderBusiness) springContext.getBean("garantiaHeaderBO");    			
    			FaturamentoBusiness    faturamentoBusiness    = (FaturamentoBusiness) springContext.getBean("faturamentoBO");				
				ModeloBusiness    	   modeloBusiness    	  = (ModeloBusiness) springContext.getBean("modeloBO");
				CampaignBusiness 	   campaignBusiness 	  = (CampaignBusiness) springContext.getBean("campaignBO");
				
				GarantiaHeader garantia = garantiaHeaderBusiness.get(garantiaId);
				
				Campaign campaign = campaignBusiness.getCampaignByGarantia(garantia);
				
				if ( campaign != null) { 
					
					Faturamento faturamento = faturamentoBusiness.getByChassi(garantia.getModelo().toUpperCase());
		    		Modelo      modelo		= (Modelo) modeloBusiness.get(Long.valueOf(faturamento.getModelo()));
		    		
		    		ParametroSistema paramVter  = garantiaHeaderBusiness.getByParameterSystem(ParametroSistema.GARANTIA_NAUTICA_VALOR_SERVICOS_TERCEIROS);
		    		try {
		    			vrLimiteSerTerc = NumberUtils.converteStringParaDouble(paramVter.getValorParametro());
		    		} catch(NumberFormatException nFe){
		    			throw new BusinessException(nFe);
		    		}	
		    		
		    		Long km = garantiaHeaderBusiness.getCupomBO().getLastRevision(garantia.getModelo());
		    		
		    		if ( Modelo.VELOCIMETRO_NOT.equalsIgnoreCase(modelo.getVelocimetro()) ) {
		    			
		    			garantiaForm.set("hasKM", Boolean.FALSE);
		    			
		    		} else {
		    			
		    			garantiaForm.set("hasKM", Boolean.TRUE);
		    			
		    		}		
		    		
					request.setAttribute("idTipo" 		  , garantia.getLote().getLinha().getEntityId() );
					request.setAttribute("limitServTer"   , new Double(vrLimiteSerTerc) );
					request.setAttribute("statusLoteId"   , garantia.getLote().getStatusLote().getEntityId() );
					request.setAttribute("readOnly"   	  , new Boolean(readOnly) );
					request.setAttribute("kmMin" 		  , km );
										
					garantiaForm.set( "entityId"       	  , garantia.getEntityId() );
					garantiaForm.set( "chassi"       	  , garantia.getModelo().toUpperCase() );
					garantiaForm.set( "lote"			  , garantia.getLote().getEntityId());
					garantiaForm.set( "linhaId"			  , garantia.getLote().getLinha().getEntityId());
					garantiaForm.set( "lote"			  , garantia.getLote().getEntityId());
					garantiaForm.set( "sysDate"		      , DateUtils.applyMask(new Date()));
					garantiaForm.set( "dtVenda"		      , faturamento.getMaskedDataNotaFiscal());
					garantiaForm.set( "numOS"			  , garantia.getNumeroOS());
					garantiaForm.set( "dtAbert"			  , garantia.getMaskedDataAberturaOS());
					garantiaForm.set( "dtFech"			  , garantia.getMaskedDataFechamentoOS());
					garantiaForm.set( "km"				  , garantia.getQuilometragem());
					garantiaForm.set( "horasUso"		  , garantia.getHorasUso());
					garantiaForm.set( "isEdit"		      , "true");
					if ( garantia.getValorServicoTerceiro() > 0 ) {
						
						garantiaForm.set( "hasServTer"     , "S");
						garantiaForm.set( "servTerUnFormat" , new Double(garantia.getValorServicoTerceiro()));
						garantiaForm.set( "servTer"			, NumberUtils.formatNumberCurrency(garantia.getValorServicoTerceiro()));						
						garantiaForm.set( "justif"          , garantia.getJustServicoTerceiro());
					}
					
					garantiaForm.set( "campaignNum"       , campaign.getCodeCampaign() );
					garantiaForm.set( "subjectCampaign"   , campaign.getSubjectCampaign() );
					
					garantiaForm.set( "sintomaIdCampaign"    , campaign.getSintoma().getEntityId() );
					garantiaForm.set( "sintomaDescCampaign"  , campaign.getSintoma().getDescricaoCode().toUpperCase() );
					
					garantiaForm.set( "condicaoIdCampaign"   , campaign.getCondition().getEntityId() );
					garantiaForm.set( "condicaoDescCampaign" , campaign.getCondition().getDescricaoCode().toUpperCase() );
					
					garantiaForm.set( "listServicos"         , campaign.getServices() );
					// Precisamos definir os campos enviar peças
					Iterator itSG = garantia.getListGarantiaLine().iterator();
					Iterator itCP = null;
					CampaignPiece cp = null;
					
					while ( itSG.hasNext() ) {
						
						GarantiaLine line = (GarantiaLine) itSG.next();
						itCP = campaign.getPieces().iterator();
						while ( itCP.hasNext() ) {
							
							cp = (CampaignPiece)itCP.next();
							if ( cp.getPiece().getEntityId().equals(line.getItem().getEntityId()) ) {
								
								cp.setSendPiece(line.getEnviaPeca());								
								
							}
							
						}						
						
					}
					garantiaForm.set( "listPieces"         , campaign.getPieces() );
					
					garantiaForm.set( "campaignId"         , campaign.getEntityId() );
					
					if ( "true".equalsIgnoreCase(readOnly))
						this.setJavaScriptExecuter(request, "setTitleAlter('Consulta de Garantia para Campanha')" );
					else
						this.setJavaScriptExecuter(request, "setTitleAlter('Alteração de Garantia para Campanha')" );					
					
					
				} else {
					
					// Retornamos para form com os dados e a mensagem 
	    			messagesProblem.add( ActionMessages.GLOBAL_MESSAGE
	    					             , new ActionMessage("garantia.msg.error.campaign.notLocalized") );
	    			super.addProblemMessages(request, messagesProblem);
					
				}
			
    		} catch (BusinessException bExp) {
    			
    			throw new ControllerException( bExp );
    			
    		} catch ( InvalidSessionException isExp ) {
        		
        		throw new ControllerException(isExp);
        		
    		} 
    		
    	} else {
    		
    		throw new ControllerException("Nenhum identificador da Garantia encontrado.");
    		
    	}  	    	
    	
    	return mapping.findForward("_formCampaign");
    	
    }
    
    /** Tarefa: Remoção Lógica da Solicitção de Garantia.
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
    public ActionForward delete( ActionMapping mapping
                             , ActionForm form
                             , HttpServletRequest request
                             , HttpServletResponse response) throws ControllerException {
    	
    	
    	DynaValidatorActionForm garantiaForm = (DynaValidatorActionForm) form;
        garantiaForm.initialize(mapping);        
        
    	try { 
    		
    		int garantiaId = Integer.parseInt(request.getParameter("garantiaId"));
    		int loteId = Integer.parseInt(request.getParameter("loteId"));
    		
    		//Lote 		lote 	        = null;
            //List		listaGarantias	= null;
    		Usuario usuario = (Usuario)UserHelper.getBoundedUser(request.getSession());
    		GarantiaHeaderBusiness garantiaHeaderBusiness = (GarantiaHeaderBusiness) springContext.getBean("garantiaHeaderBO");   
    		//LoteBusiness  		   loteBusiness           = (LoteBusiness)  springContext.getBean("loteBO");
    		    		
    		GarantiaHeader garantia = garantiaHeaderBusiness.get(new Integer(garantiaId));
    		/*
    		if ( garantia.getCampaign() != null ) {      			
    			
    			CampaignBilling campaignBilling = garantiaHeaderBusiness.getCampaignBillingByGarantia(garantia);
    			ControllerHelper.prepare(campaignBilling, (Long)usuario.getEntityId());
    			garantiaHeaderBusiness.removeEntity(campaignBilling);
    			
    		}
    		
    		// Removemos e entidade
    		ControllerHelper.prepare(garantia, (Long)usuario.getEntityId());
    		*/
    		garantiaHeaderBusiness.remove(garantia, usuario);
    		
    		this.setAlertMessage(request, "A Solicitação de Garantia Nº " + garantia.getEntityId() + " foi removida!");
    		/*
    		lote = (Lote)loteBusiness.get(new Integer(loteId));
    		
    		listaGarantias = garantiaHeaderBusiness.listByLote( lote );
    		
    		garantiaForm.set( "task"          , "list");
    		garantiaForm.set( "listGarantias" , listaGarantias );
    		*/
    		
            request.setAttribute("loteId"		    , new Integer(loteId));
            
            return this.list(mapping, form, request, response);
    		//return mapping.findForward("_list");
    		
    	} catch (BusinessException bExp) {
    		
            throw new ControllerException(bExp);
            
        } catch ( InvalidSessionException isExp ) {
    		
    		throw new ControllerException(isExp);
    		
		} 
	    
	}
    
    /** Tarefa: Valida as informações enviadas do formulário de Inclusão de Garantia para Campanha.
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
    public ActionForward includeCampaign( ActionMapping mapping
                             , ActionForm form
                             , HttpServletRequest request
                             , HttpServletResponse response) throws ControllerException {
    	
    	DynaValidatorActionForm garantiaForm = (DynaValidatorActionForm) form;
    	ActionMessages 			messagesWarning    = new ActionMessages();
    	ActionMessages 			messagesProblem    = new ActionMessages();
    	
    	Integer loteId 		= garantiaForm.get("lote") != null ? (Integer)garantiaForm.get("lote"): new Integer(0);
    	Integer garantiaId  = garantiaForm.get("entityId") != null ?(Integer)garantiaForm.get("entityId"): new Integer(0);
    	Long    campaignId  = (Long)garantiaForm.get("campaignId");
    	String  chassi      = garantiaForm.getString("chassi");
    	String  numeroOS    = garantiaForm.getString("numOS");
    	Long    km          = (Long)garantiaForm.get("km");
    	Long    horasUso    = (Long)garantiaForm.get("horasUso");
    	Date    dtAbert     = DateUtils.format( garantiaForm.getString("dtAbert"), "dd/MM/yyyy");
    	Date    dtFech      = DateUtils.format( garantiaForm.getString("dtFech"), "dd/MM/yyyy");
    	//String  hasServTer  = garantiaForm.getString("hasServTer");
    	double  servTer     = garantiaForm.get("servTerUnFormat") == null ? 0 : ((Double)garantiaForm.get("servTerUnFormat")).doubleValue();
    	String  justif      = garantiaForm.getString("justif");
    	boolean isEdit      = garantiaForm.getString("isEdit").equals("false") ? false : true; 
    	// Primeiro, pegamos nossos alvos e quebramos em
        // um array de Strings.
        String targets[] = garantiaForm.getStrings("deleteTargets");
    	
    	boolean isSaved     = true;
    	    	
    	try { 
    		
    		Usuario usuario = (Usuario)UserHelper.getBoundedUser(request.getSession());
    		GarantiaHeaderBusiness 	garantiaHeaderBusiness 	= (GarantiaHeaderBusiness) springContext.getBean("garantiaHeaderBO"		);
    		ModeloBusiness    	   	modeloBusiness    	  	= (ModeloBusiness) springContext.getBean("modeloBO");
    		FaturamentoBusiness 	faturamentoBusiness 	= (FaturamentoBusiness) 	 springContext.getBean("faturamentoBO" 			);
    		CampaignBusiness 	    campaignBusiness 	    = (CampaignBusiness) springContext.getBean("campaignBO");
    		
    		Campaign 			campaign			= new Campaign();
    		GarantiaHeader 		garantia			= new GarantiaHeader();
    		Faturamento 	    faturamentoModelo 	= faturamentoBusiness.getByChassi(chassi.toUpperCase());
    		Modelo      		modelo				= (Modelo) modeloBusiness.get(Long.valueOf(faturamentoModelo.getModelo()));
    		Lote				lote				= new Lote();
    		GarantiaTO 			garantiaTO 			= new GarantiaTO();
    		Long 				kmMin 				= garantiaHeaderBusiness.getCupomBO().getLastRevision(chassi);
    		
    		//Montando a entidade de garantia
    		if ( !garantiaId.equals(new Integer(0)) ) {
    			
    			garantia = garantiaHeaderBusiness.get(garantiaId);
    			campaign = campaignBusiness.getCampaignByGarantia(garantia);
    			lote     = garantia.getLote();
    			
    		} else {
    		    
    			garantia.setModelo(chassi.toUpperCase());
    		    garantia.setNumeroOS(numeroOS);
    		    
    		    //Montando a entidade da campanha 
    		    //campaign = campaignBusiness.getById(campaignId);
    		    campaign = campaignBusiness.getCampaignById(campaignId);
        		
        		//Montando a entidade do lote 
        		lote.setEntityId(loteId);
        		
    		}
    		
    		garantia.setQuilometragem(km);
    		garantia.setHorasUso(horasUso);
    		garantia.setDataAberturaOS(dtAbert);
    		garantia.setDataFechamentoOS(dtFech);
    		
    		garantia.setJustServicoTerceiro(justif);
    		garantia.setValorServicoTerceiro(servTer);
    		
    		garantia.setLote(lote);
    		
    		// Definimos a propriedade enviar peça 		
    		campaign.setSendPiece(targets); 
    		
    		// Montando o TO da campanha para enviar para as validações
    		garantiaTO.setCampaign(campaign);
    		garantiaTO.setGarantia(garantia);
    		
    		// Enviamos o TO da Campanha para as validações e recebemos TO da garantia montado com as mensagens
    		garantiaTO = garantiaHeaderBusiness.validationCampaign(garantiaTO,usuario, isEdit);
    		
    		// Vamos tratar os alertas
    		Iterator itAl = garantiaTO.getListAlertas().iterator();
    		
    		while ( itAl.hasNext() ) {
    			
    			AlertGarantia alert = (AlertGarantia)itAl.next();
    			
    			if ( alert.getAlertGarantiaKey().indexOf("error") != -1 ) {
		    		
    				//Quando o alerta for de erro não podemos salvar
		    		isSaved = false;
		    		// Retornamos para form com os dados e a mensagem 
	    			messagesProblem.add( ActionMessages.GLOBAL_MESSAGE
	    					             , new ActionMessage(alert.getAlertGarantiaText().toString()) );
	    			super.addProblemMessages(request, messagesProblem);	 
	    			
		    	} else if ( (alert.getAlertGarantiaKey().indexOf("warning") != -1) ) {
		    		
		    		// Retornamos para form com os dados e a mensagem 
	    			messagesWarning.add( ActionMessages.GLOBAL_MESSAGE
	    					             , new ActionMessage(alert.getAlertGarantiaKey(),alert.getAtributo()) );
	    			super.addWarningMessages(request, messagesWarning);	 
		    		
		    	}
    		}
    		
    		double vrLimiteSerTerc;
    		ParametroSistema paramVter  = garantiaHeaderBusiness.getByParameterSystem(ParametroSistema.GARANTIA_NAUTICA_VALOR_SERVICOS_TERCEIROS);
    		try {
    			vrLimiteSerTerc = NumberUtils.converteStringParaDouble(paramVter.getValorParametro());
    		} catch(NumberFormatException nFe){
    			throw new BusinessException(nFe);
    		}	
    		
    		if ( Modelo.VELOCIMETRO_NOT.equalsIgnoreCase(modelo.getVelocimetro()) ) {
    			
    			garantiaForm.set("hasKM", Boolean.FALSE);
    			
    		} else {
    			
    			garantiaForm.set("hasKM", Boolean.TRUE);
    			
    		}		
    		
			request.setAttribute("idTipo" 		  , garantia.getLote().getLinha().getEntityId() );
			request.setAttribute("limitServTer"   , new Double(vrLimiteSerTerc) );
			request.setAttribute("statusLoteId"   , garantia.getLote().getStatusLote().getEntityId() );
			request.setAttribute("kmMin" 		  , kmMin );
			
    		garantiaForm.set( "listServicos"       , garantiaTO.getCampaign().getServices() );
			garantiaForm.set( "listPieces"         , garantiaTO.getCampaign().getPieces() );
			garantiaForm.set( "isEdit"             , isEdit ? "true" : "false" );
			
    		String msg = new String();
    		
    		if ( !isSaved ) { 
    			
    			msg = "Não foi possível gravar a Garantia para a Camapanha!";
    			this.setAlertMessage(request, msg);
    			garantiaForm.set( "validated", "false"  );
    			// Removemos o TO da sessão do usuário
				YmSessionHelper.removeFromSession(request, YmSessionHelper.SESSION_GARANTIATO);
				
    		} else {
    			
    			if ( garantia.getCampaign() != null ) {  
    				msg = "Confirma a alteração da Solicitação de Garantia para a Campanha?";
    			} else {    			
    				msg = "Confirma a inclusão da Solicitação de Garantia para a Campanha?";
    			}
    			
		    	this.setConfirmMessage(request, msg, "save()", "cancel(" + lote.getEntityId() + ")");
		    	// Incluímos o objeto na sessão
				YmSessionHelper.setGarantiaTOToSession(request, garantiaTO);
    		}
    		
    		return mapping.findForward("_formCampaign");    		
    		
    	} catch (BusinessException bExp) {
    		
            throw new ControllerException(bExp);
            
        } catch ( InvalidSessionException isExp ) {
    		
    		throw new ControllerException(isExp);
    		
		} 
    }
    
    /** Tarefa: Grava as informações enviadas do formulário de Inclusão de Garantia para Campanha.
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
    public ActionForward saveCampaign( ActionMapping mapping
                             , ActionForm form
                             , HttpServletRequest request
                             , HttpServletResponse response) throws ControllerException {
    	
    	DynaValidatorActionForm garantiaForm = (DynaValidatorActionForm) form;
    	ActionMessages 			messagesSuccess    = new ActionMessages();
    	ActionMessages 			messagesProblem    = new ActionMessages();
    	
    	Integer loteId 		= garantiaForm.get("lote") != null ? (Integer)garantiaForm.get("lote"): new Integer(0);
    	boolean isEdit      = garantiaForm.getString("isEdit").equals("false") ? false : true; 
    	try {
    		
    		Usuario usuario = (Usuario)UserHelper.getBoundedUser(request.getSession());
    		GarantiaHeaderBusiness 	garantiaHeaderBusiness 	= (GarantiaHeaderBusiness) springContext.getBean("garantiaHeaderBO");    		
    		FaturamentoBusiness 	faturamentoBusiness 	= (FaturamentoBusiness) 	 springContext.getBean("faturamentoBO" 			);
    		ModeloBusiness    	   	modeloBusiness    	  	= (ModeloBusiness) springContext.getBean("modeloBO");
    		
    		GarantiaTO garantiaTO = YmSessionHelper.getGarantiaTOFromSession(request);
    		GarantiaHeader 		garantia 			= garantiaTO.getGarantia();
			Campaign      		campaign 			= garantiaTO.getCampaign();
			Faturamento 	    faturamentoModelo 	= faturamentoBusiness.getByChassi(garantia.getModelo().toUpperCase());
			Modelo      		modelo				= (Modelo) modeloBusiness.get(Long.valueOf(faturamentoModelo.getModelo()));
			Long 				kmMin 				= garantiaHeaderBusiness.getCupomBO().getLastRevision(garantia.getModelo());
			
    		AlertGarantia alert = garantiaHeaderBusiness.saveAll(garantiaTO, isEdit);
    		
    		if ( alert != null ) {
				
				if ( alert.getAlertGarantiaKey().indexOf("error") != -1 ) {
					
					messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage(alert.getAlertGarantiaText()) );
					super.addProblemMessages(request, messagesProblem);	
					
				} else {
					
					//log.info("CampaignId:" + campaign.getEntityId());
					CampaignBilling     campaignBilling     = new CampaignBilling();
					
					// Recuperamos o faturamento para a garantia apenas se ainda não existir o relacionamento
					Faturamento faturamento = faturamentoBusiness.getByChassiAndCampaign(garantia.getModelo().toUpperCase(), campaign);
					
					if ( faturamento != null ) {
					
						campaignBilling.setBilling(faturamento);
						campaignBilling.setCampaign(campaign);
						campaignBilling.setGarantia(garantia);
						ControllerHelper.prepare(campaignBilling, (Long)usuario.getEntityId());
						
						garantiaHeaderBusiness.saveEntity(campaignBilling);
						
					}
					
					messagesSuccess.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("form.msg.saved") );
	            	super.addSuccessMessages(request, messagesSuccess);	
	            	            	
	            	this.setJavaScriptExecuter(request, "redirectGar(" + loteId + ")");
					
	            	
				}
				
			} 
    		
			// Removemos o TO da sessão do usuário
			YmSessionHelper.removeFromSession(request, YmSessionHelper.SESSION_GARANTIATO);
			
			double vrLimiteSerTerc;
    		ParametroSistema paramVter  = garantiaHeaderBusiness.getByParameterSystem(ParametroSistema.GARANTIA_NAUTICA_VALOR_SERVICOS_TERCEIROS);
    		try {
    			vrLimiteSerTerc = NumberUtils.converteStringParaDouble(paramVter.getValorParametro());
    		} catch(NumberFormatException nFe){
    			throw new BusinessException(nFe);
    		}	
    		
			if ( Modelo.VELOCIMETRO_NOT.equalsIgnoreCase(modelo.getVelocimetro()) ) {
    			
    			garantiaForm.set("hasKM", Boolean.FALSE);
    			
    		} else {
    			
    			garantiaForm.set("hasKM", Boolean.TRUE);
    			
    		}		
    		
			request.setAttribute("idTipo" 		  , garantia.getLote().getLinha().getEntityId() );
			request.setAttribute("limitServTer"   , new Double(vrLimiteSerTerc));
			request.setAttribute("statusLoteId"   , garantia.getLote().getStatusLote().getEntityId() );
			request.setAttribute("kmMin" 		  , kmMin );
											
        	garantiaForm.set( "listServicos"       , campaign.getServices() );
			garantiaForm.set( "listPieces"         , campaign.getPieces() );
			garantiaForm.set( "isEdit"             , isEdit ? "true" : "false" );
			garantiaForm.set( "sysDate"		  	   , DateUtils.applyMask(new Date()));
			
    		return mapping.findForward("_formCampaign");    
    		
    	} catch (BusinessException bExp) {
    		
            throw new ControllerException(bExp);
            
        } catch ( InvalidSessionException isExp ) {
    		
    		throw new ControllerException(isExp);
    		
		} 
    	
    }
    
    /** Tarefa: Cancela a Inclusão de Garantia para Campanha.
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
    public ActionForward cancelIncludeCampaign( ActionMapping mapping
                             , ActionForm form
                             , HttpServletRequest request
                             , HttpServletResponse response) throws ControllerException {
    	
    	// Removemos o TO da sessão do usuário
		YmSessionHelper.removeFromSession(request, YmSessionHelper.SESSION_GARANTIATO);
    	    	
    	return this.list(mapping, form, request, response);
    	
    }
    
    /** Tarefa: Grava as informações enviadas do formulário de Inclusão de Garantia.
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
    	
    	DynaValidatorActionForm garantiaForm = (DynaValidatorActionForm) form;
    	ActionMessages 			messagesWarning    = new ActionMessages();
    	ActionMessages 			messagesProblem    = new ActionMessages();
    	
    	// recuperamos os valores do form
    	Integer garantiaId  = (Integer)garantiaForm.get("entityId");
    	Integer infoMercadoId = garantiaForm.get("infoMercadoId") != null ? (Integer)garantiaForm.get("infoMercadoId"):null;
    	Integer loteId      = (Integer)garantiaForm.get("lote");
    	String  chassi      = garantiaForm.getString("chassi");
    	String  numeroOS    = garantiaForm.getString("numOS");
    	Long    km          = (Long)garantiaForm.get("km");
    	Long    horasUso    = (Long)garantiaForm.get("horasUso");
    	Date    dtAbert     = DateUtils.format( garantiaForm.getString("dtAbert"), "dd/MM/yyyy");
    	Date    dtFech      = DateUtils.format( garantiaForm.getString("dtFech"), "dd/MM/yyyy");
    	Long 	sintomaId   = (Long)garantiaForm.get("sintoma");
    	Long    condicaoId  = (Long)garantiaForm.get("condicao");
    	String  hasServTer  = garantiaForm.getString("hasServTer");
    	double  servTer     = garantiaForm.get("servTerUnFormat") == null ? 0 : ((Double)garantiaForm.get("servTerUnFormat")).doubleValue();
    	String  justif      = garantiaForm.getString("justif");
    	String  autEsp      = garantiaForm.getString("autEsp"); 
    	String  cortesia    = garantiaForm.getString("cortesia"); 
    	boolean isRecall    = "S".equalsIgnoreCase(garantiaForm.getString("recall"));
    	Long    recallId    = isRecall ? (Long) garantiaForm.get("recallId") : null; 
    	boolean validated   = garantiaForm.getString("validated").equals("false") ? false : true; 
    	boolean isEdit      = garantiaForm.getString("isEdit").equals("false") ? false : true; 
    	
    	String preenchidoPor 	= garantiaForm.getString("preenchidoPor");
    	String condicaoProblema = garantiaForm.getString("condicaoProblema");
    	String causaProblema 	= garantiaForm.getString("causaProblema");
    	String diagnostico 		= garantiaForm.getString("diagnostico");
    	String solucao			= garantiaForm.getString("solucao");
    	
    	String tipoGasolina = null; 
    	long tipoGasolinaId = new Long( garantiaForm.getString("tipoGasolinaId") ).longValue();
		if ( tipoGasolinaId > 0 )			
			tipoGasolina =  garantiaForm.getString("tipoGasolinaId");
		
		String localUsoDoce = null; 
		if ( garantiaForm.getString("localUsoDoce") != null )
			localUsoDoce = garantiaForm.getString("localUsoDoce");
		
		String localUsoSalg = null;
		if ( garantiaForm.getString("localUsoSalg") != null )
			localUsoSalg = garantiaForm.getString("localUsoSalg");
		
		String materHelice = garantiaForm.getString("materHelice");
		
		String tipoUso = null;
		Long tipoUsoId = garantiaForm.getString("tipoUso") != null ? new Long( garantiaForm.getString("tipoUso")):new Long(0);
		if ( tipoUsoId.longValue() >  0 ) { 
			tipoUso =  garantiaForm.getString("tipoUso");
		}
		
		String cidadeUso   = garantiaForm.getString("cidadeUso");
		String marcaCasco  = garantiaForm.getString("marcaCasco");
		String modeloCasco = garantiaForm.getString("modeloCasco");
		String marcaHelice = garantiaForm.getString("marcaHelice");
		
		Long passoHelice = null;
		if ( garantiaForm.get("passoHelice") != null )
			passoHelice = (Long) garantiaForm.get("passoHelice");

		Long rotacaoMaxima = null;
		if ( garantiaForm.get("rotacaoMaxima") != null )
			rotacaoMaxima = (Long) garantiaForm.get("rotacaoMaxima");
    	
    	String alterState   = garantiaForm.get("alterState") != null ? garantiaForm.getString("alterState"):"false";
    	
    	//log.info("infoMercadoId:" + infoMercadoId);
    	
    	if ( isEdit ) {
    		garantiaForm.set("alterState", alterState);
    		garantiaForm.set("isEdit", "true");
    	}    		
    	
    	if ( sintomaId == null ) {
    		
    		sintomaId = (Long)garantiaForm.get("sintomaId");
    	}
    	
    	if ( condicaoId == null ) {
    		
    		condicaoId = (Long)garantiaForm.get("condicaoId");
    		
    	}
    	
    	AlertGarantia alertGarantia = null;
    	Recall        recall        = null;
    	List 		  listAlertas   = new ArrayList();
    	
    	// Flag que verificação se o chassi informado foi faturado pela concessionária do usuário
    	// Se for True
    	boolean isConcessionaria = true;
    	boolean isSaved = true;
    	
    	try {
    		
    		Usuario usuario = (Usuario) UserHelper.getBoundedUser(request.getSession());
    		
    		FaturamentoBusiness    faturamentoBusiness    = (FaturamentoBusiness) 	 springContext.getBean("faturamentoBO" 			);
    		GarantiaHeaderBusiness garantiaHeaderBusiness = (GarantiaHeaderBusiness) springContext.getBean("garantiaHeaderBO"		);
    		LoteBusiness           loteBusiness           = (LoteBusiness) 			 springContext.getBean("loteBO"			  		);
    		AutEspecSGBusiness     autEspecSGBusiness     = (AutEspecSGBusiness) 	 springContext.getBean("autorizacaoEspecialSGBO");
    		InfoMercadoBusiness    infoMercadoBusiness    = (InfoMercadoBusiness) 	 springContext.getBean("infoMercadoBO");
    		
			Lote                   lote                   = (Lote) loteBusiness.get(loteId);			
			
			//log.info(" Validação de Recall!" +  isRecall);
			// Se a solicitação vier como recall carregamos os valores correspondentes
			if ( isRecall ) { 
				
				recall   =  garantiaHeaderBusiness.getRecall(recallId);
								
				if ( recall != null) { 
					
					List listRecall = garantiaHeaderBusiness.listRecall(chassi);
					
					//log.info("Recalls:" + listRecall.size());
					
					garantiaForm.set( "sintomaIdRecall"    , recall.getSintoma().getEntityId() );
					garantiaForm.set( "sintomaDescRecall"  , recall.getSintoma().getDescricaoCode().toUpperCase() );
					
					garantiaForm.set( "condicaoIdRecall"   , recall.getCondicao().getEntityId() );
					garantiaForm.set( "condicaoDescRecall" , recall.getCondicao().getDescricaoCode().toUpperCase() );
					
					garantiaForm.set( "listServicos"       , recall.getServicos() );
					
					garantiaForm.set( "listRecall"    , listRecall );
					garantiaForm.set( "listCampaign"  , new ArrayList() );
					garantiaForm.set( "listSintomas"  , new ArrayList() );
					garantiaForm.set( "listCondicoes" , new ArrayList() );
					
					garantiaForm.set( "recallId"      , recallId );
					
					garantiaForm.set( "isRecall"      , "true" );
					
				} else {
				
					// 	Retornamos para form com os dados e a mensagem				
					messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("garantia.msg.error.recall") );
		            super.addProblemMessages(request, messagesProblem); 
		            isSaved = false;
				}
				
			} else {		
				
				//log.info("Sintoma:" + sintomaId  + " - Condição:" + condicaoId);
				
				garantiaForm.set( "sintoma"		  , sintomaId 											 );
				garantiaForm.set( "condicao"	  , condicaoId 											 );
				garantiaForm.set( "listSintomas"  , garantiaHeaderBusiness.listSintomas(lote.getLinha()) );
				garantiaForm.set( "listCondicoes" , garantiaHeaderBusiness.listCondicoes(lote.getLinha()));
				garantiaForm.set( "listServicos"  , garantiaHeaderBusiness.listServicos(lote.getLinha()) );
				garantiaForm.set( "listRecall"    , new ArrayList()  									 );
				garantiaForm.set( "listCampaign"  , new ArrayList()  									 );
				garantiaForm.set( "isRecall"      , "false" 											 );
				
				garantiaForm.set( "sintomaId"	  , sintomaId 											 );
				garantiaForm.set( "condicaoId"	  , condicaoId 											 );
				
				garantiaForm.set( "tipoGasolinaId"   , tipoGasolina );
				garantiaForm.set( "localUsoDoce"     , localUsoDoce );
				garantiaForm.set( "localUsoSalg"     , localUsoSalg );
				garantiaForm.set( "materHelice"      , materHelice );
				garantiaForm.set( "tipoUso"          , tipoUso );
				garantiaForm.set( "cidadeUso"        , cidadeUso );
				garantiaForm.set( "marcaCasco"       , marcaCasco );
				garantiaForm.set( "modeloCasco"      , modeloCasco );
				garantiaForm.set( "marcaHelice"      , marcaHelice );
				garantiaForm.set( "passoHelice"      , passoHelice );
				garantiaForm.set( "rotacaoMaxima"    , rotacaoMaxima );
				
				garantiaForm.set("listTipoGasolina", infoMercadoBusiness.listTipoGasolina());
				garantiaForm.set("listTipoProduto" , infoMercadoBusiness.listTipoProdutoInfo() );
				garantiaForm.set("listTipoUso"     , infoMercadoBusiness.listTipoUso()); 
			
			}						
			
			garantiaForm.set( "hasServTer", hasServTer );
			garantiaForm.set( "cortesia"  , cortesia   );
			
			request.setAttribute("idTipo"   , lote.getLinha().getEntityId() );
			request.setAttribute("consulta" , new Boolean(false)             );
			request.setAttribute("statusLoteId" , lote.getStatusLote().getEntityId());
						
	    	// **************************************************************** //
	    	// * INICIO - Validação do chassi informado       				  * //
	    	// *          Verificação de faturamento e concessionária    	  * //
	    	// **************************************************************** //
			Faturamento faturamento	 = faturamentoBusiness.getByChassi(chassi);
			
			if ( faturamento != null ) { 
				
				//log.info(" Validações de Faturamento!");
				
				// Se a linha do chassi informado é mesma do lote selecionado
				if ( !faturamento.getLinha().getEntityId().equals(lote.getLinha().getEntityId()) ) {
					
					// Retornamos para form com os dados e a mensagem 
	    			messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("garantia.msg.error.invalidLine", lote.getLinha().getDescricao()) );
		            super.addProblemMessages(request, messagesProblem);
		            
		            return mapping.findForward("_form");		            
				}
				
				// Verificamos se o chassi faturado é da mesma concessionária do usuário
		    	if (!usuario.compareIdAtribute("concessionaria",faturamento.getConcessionaria().getEntityId())){
		    		
		    		isConcessionaria = false;
		    		messagesWarning.add( ActionMessages.GLOBAL_MESSAGE
		    				             , new ActionMessage("garantia.msg.warning.chassi",chassi.toUpperCase()));
		    		super.addWarningMessages(request, messagesWarning);	 		    		
		    		
		    		//log.info("Concessionária:" + usuario.getConcessionaria().getRazaoSocial() + " - Estado da Concessionária:" + usuario.getConcessionaria().getEstado());
		    		//log.info("Estado do Faturamento:" + faturamento.getConcessionaria().getEstado());
		    		
		    		if ( !usuario.getConcessionaria().getUf().equalsIgnoreCase(faturamento.getConcessionaria().getUf()) ) {
		    			
		    			alertGarantia = new AlertGarantia();
		    			alertGarantia.setAlertGarantiaText("Chassi Faturado para outro Estado!");
		    			alertGarantia.setAlertGarantiaSeveridade(AlertGarantia.SEVERIDADE_WARNING);
		    			alertGarantia.setAlertGarantiaKey("garantia.msg.warning.estado.chassi");
		    			ControllerHelper.prepare(alertGarantia, (Long)usuario.getEntityId());
		    			
		    			listAlertas.add(alertGarantia);
		    		} 		    		
		    	}
		    	
		    	// **************************************************************** //
		    	// * Validação de restrição do chassi 							  * //
		    	// **************************************************************** //				
		    	if ( garantiaHeaderBusiness.isRestriction(chassi) ) {
					
					// Retornamos para form com os dados e a mensagem 
	    			messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("garantia.form.msg.error.notGarantia", chassi) );
		            super.addProblemMessages(request, messagesProblem);	 
		            
		            isSaved = false;		    		
		    	}
		    	
		    	InfoMercado infoMercado = null;
		    	// Validamos a informação de Mercado, se existir
		    	if( ( new Long(0)).equals(garantiaId) && Linha.TIPO_NAUTICA.equals(lote.getLinha().getEntityId())){
			    	
			    	if(infoMercadoId != null ) {
			    		infoMercado = infoMercadoBusiness.get(infoMercadoId);
			    		
			    		if(infoMercado == null) {			    			
			    			// Retornamos para form com os dados e a mensagem 
			    			messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("garantia.msg.error.infoMercado", infoMercadoId) );
				            super.addProblemMessages(request, messagesProblem);
				            
				            return mapping.findForward("_form");	
				            
			    		}else if(!infoMercado.getChassi().toUpperCase().equals(chassi.toUpperCase())) {
			    			log.info("Informação de Mercado:" + infoMercado.getEntityId());
			    			// Retornamos para form com os dados e a mensagem 
			    			messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("garantia.msg.error.imChassi", infoMercado.getEntityId()) );
				            super.addProblemMessages(request, messagesProblem);
				            
				            return mapping.findForward("_form");	
				            
			    		} else if(infoMercado.getHasGarantia()) {
			    			log.info("Informação de Mercado:" + infoMercado.getEntityId());
			    			// Retornamos para form com os dados e a mensagem 
			    			messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("garantia.msg.error.imExist", infoMercado.getEntityId()) );
				            super.addProblemMessages(request, messagesProblem);
				            
				            return mapping.findForward("_form");			    			
			    		}
			    	}	
		    	}
		    
		    	// Recuperamos as entidades necessárias para relacionar
		    	Sintoma sintoma   = null;
		    	Condicao condicao = null;
		    	
		    	if ( !isRecall ) { 
		    		
		    		sintoma   = (Sintoma)garantiaHeaderBusiness.get(Sintoma.class, sintomaId);
			    	condicao  = (Condicao)garantiaHeaderBusiness.get(Condicao.class, condicaoId);			    	
		    	
			    	// Validamos a recuperação do sintoma e da condição, são itens obrigatórios
			    	if ( sintoma == null || condicao == null ) {
			    		
				    	if ( sintoma == null ) {
							
							// Retornamos para form com os dados e a mensagem 
			    			messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("garantia.msg.error.sintoma") );
				            super.addProblemMessages(request, messagesProblem);	
							
						}
				    	
				    	if ( condicao == null ) {
								
							// Retornamos para form com os dados e a mensagem 
			    			messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("garantia.msg.error.condicao") );
				            super.addProblemMessages(request, messagesProblem);
							
						}
				    	
				    	return mapping.findForward("_form");
			    	}
			    	
		    	} else {
		    		
		    		sintoma  = recall.getSintoma();
		    		condicao = recall.getCondicao();
		    		
		    	}
		    	
		    	// Popular o objeto Garantia com as informações coletadas no form de garantia
		    	// Se for alteração, tentamos recuperar o objeto do banco
		    	GarantiaHeader garantia = null;
		    	
		    	if( isEdit ) {
		    		garantia = garantiaHeaderBusiness.get(garantiaId);
		    		//log.info("Removendo os serviços!");
		    		GrupoServicoBusiness grupoServicoBusiness = (GrupoServicoBusiness) springContext.getBean("grupoServicoBO");
		    		Iterator it = garantia.getGrupos().iterator();
		    		while ( it.hasNext() ) {
		    			
		    			ServicoGrupo gServ = (ServicoGrupo) it.next();
		    			
		    			grupoServicoBusiness.remove(gServ);
		    					    			
		    		}
		    		
		    		if(garantia.getCausaProblemaGarantia() != null)
		    			garantiaHeaderBusiness.makeTransient(garantia.getCausaProblemaGarantia());
		    		if(garantia.getCondicaoProblemaGarantia() != null)
		    			garantiaHeaderBusiness.makeTransient(garantia.getCondicaoProblemaGarantia());
		    		if(garantia.getDiagnosticoProblemaGarantia() != null)
		    			garantiaHeaderBusiness.makeTransient(garantia.getDiagnosticoProblemaGarantia());
		    		if(garantia.getSolucaoProblemaGarantia() != null)
		    			garantiaHeaderBusiness.makeTransient(garantia.getSolucaoProblemaGarantia());
		    	}
		    	
		    	if ( garantia == null )
		    		garantia = new GarantiaHeader();
		    	
		    	//log.info("Chassi:" + chassi + " Garantia é nula:" + (garantia != null));
		    	
		    	garantia.setModelo(          chassi   );		    		    	
		    	garantia.setDataAberturaOS(  dtAbert  );
		    	garantia.setDataFechamentoOS(dtFech	  );
		    	garantia.setNumeroOS(		 numeroOS );
		    	garantia.setSintoma(		 sintoma  );
		    	garantia.setCondicao(        condicao );
		    	garantia.setPreenchidoPor( preenchidoPor);
		    	
		    	garantia.setCondicaoProblemaGarantia(CondicaoProblemaGarantia.getInstance(condicaoProblema));		    	
		    	garantia.setCausaProblemaGarantia(CausaProblemaGarantia.getInstance(causaProblema));
		    	garantia.setDiagnosticoProblemaGarantia(DiagnosticoProblemaGarantia.getInstance(diagnostico));
		    	garantia.setSolucaoProblemaGarantia(SolucaoProblemaGarantia.getInstance(solucao));
		    	
		    	garantia.setTipoGasolinaId(tipoGasolina);
		    	garantia.setLocalUsoDoce(localUsoDoce);
		    	garantia.setLocalUsoSalg(localUsoSalg);
		    	garantia.setMaterHelice(materHelice);
		    	garantia.setTipoUsoId(tipoUsoId);
		    	garantia.setTipoUso(tipoUso);
		    	garantia.setCidadeUso(cidadeUso);
		    	garantia.setMarcaCasco(marcaCasco);
		    	garantia.setModeloCasco(modeloCasco);
		    	garantia.setMarcaHelice(marcaHelice);
		    	garantia.setMaterHelice(materHelice);
		    	garantia.setPassoHelice(passoHelice);
		    	garantia.setRotacaoMaxima(rotacaoMaxima);
		    	
		    	// Calculando o número de dias entre a revisão de entrega ou da venda com o dia da abertura da SG
		    	Long diasUso = garantiaHeaderBusiness.getDiasUso(garantia.getModelo(), faturamento.getDataNotaFiscal(), garantia.getDataAberturaOS());
		    	
		    	garantia.setDiasUso(diasUso);
		    	garantia.setHorasUso(horasUso);
		    	garantia.setQuilometragem(km);
		    	garantia.setLote(lote);
				
				// **************************************************************** //
				// *           Validação da existência de uma Garantia com as     * //
				// *           mesmas informações de:							  * //
				// *           chassi, data de abertura da OS, Número da OS, 	  * //
				// * 		   KM (para linha MOTOCICLETA) e dias de uso (para    * //
				// *           linha de Náutica)								  * //
				// * 	       Implementado em 13/09/2007, conforme solicitação   * //
				// *           do Dimas, esta validação não constava no MD-50 até * //
				// *           o momento desta implementação.     				  * //
		    	// *		   OBS: Apenas para novas garantias  				  * //
				// **************************************************************** //
				
				if ( garantiaHeaderBusiness.isEcxistsGarantia(garantia) && !isEdit ) {
					
					// Retornamos para form com os dados e a mensagem 
	    			messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("garantia.msg.error.isEcxists") );
		            super.addProblemMessages(request, messagesProblem);
		            
		            return mapping.findForward("_form");					
					
				}
				
		    	// **************************************************************** //
				// *				Validação da Cortesia						  * //
		    	// *				Autorização Especial						  * //		
				// **************************************************************** //				
				AutorizacaoEspecialSG  autorizacaoEspecialSG  = null;
				
				boolean isUtil = false;
				
				//log.info("GarantiaAction ----> " + new Date() + " - Cortesia:" + cortesia );
				
				if ( "S".equalsIgnoreCase(cortesia) ) {
					
					//log.info("GarantiaAction ----> " + new Date() + " - autor. Especial:" + autEsp);
					
					autorizacaoEspecialSG  = (AutorizacaoEspecialSG) autEspecSGBusiness.getByChassiAndNum(chassi, autEsp, usuario.getConcessionaria().getEntityId());
					
					//log.info("GarantiaAction ----> " + new Date() + " - Autor Espec. Is Null? " + (autorizacaoEspecialSG == null));
					
					if ( autorizacaoEspecialSG == null ) {	
						
						isSaved = false;
						
						// Retornamos para form com os dados e a mensagem 
		    			messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("garantia.msg.error.status.cortesia") );
			            super.addProblemMessages(request, messagesProblem);
			            
			            return mapping.findForward("_form");					
						
					} else if ( isEdit ){
						
						//log.info(" --- > Alteração de garantia com cortesia!");						
						
						if ( garantia.getAutorizacaoEspecialSG() != null ) {
							
							if ( autorizacaoEspecialSG.equals(garantia.getAutorizacaoEspecialSG()) ) {
								
								autorizacaoEspecialSG = null;
								
							} else {
								
								autorizacaoEspecialSG = garantia.getAutorizacaoEspecialSG();
								
							}
						}
						
					}
					
					isUtil = true;
					
					
				} else if ( isEdit ) {
					
					//log.info(" --- > Alteração de garantia sem cortesia!");
					
					if ( garantia.getAutorizacaoEspecialSG() != null ) {
						
						//log.info(" --- > Alteração de garantia sem cortesia, mas existia cortesia anteriormente!");
						
						autorizacaoEspecialSG = garantia.getAutorizacaoEspecialSG();						
						
					}					
					
				}
				
				if ( autorizacaoEspecialSG != null ) {
				
					//log.info("GarantiaAction ----> " + new Date() + " - Cortesia AUTMO3:" + autorizacaoEspecialSG.getAutMo3() + " - HasServTer:" + hasServTer);
					
					// Verificação de MO3 para cortesia						
					if ( "N".equalsIgnoreCase(autorizacaoEspecialSG.getAutMo3()) && "S".equalsIgnoreCase(hasServTer) ) {
						
						isSaved = false;
						
						// Retornamos para form com os dados e a mensagem 
		    			messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("garantia.msg.error.mo3.cortesia") );
			            super.addProblemMessages(request, messagesProblem);
			            
			            //log.info("GarantiaAction ----> " + new Date() + " - A Cortesia informada não autoriza Mão de Obra de Terceiro!");
			            
			            return mapping.findForward("_form");	
						
					}
				}
		    	
		    	// **************************************************************** //
		    	// * INICIO - Validação dos códigos de serviço   				  * //
				// **************************************************************** //	    	
		    	List servicos   = new ArrayList();
		    		    	
		    	for( int i = 1 ; i < 4 ; i++ ) {
		    		
		    		Servico servicoNew = null;
		    		String servicoCode = garantiaForm.getString("servico_" + i);
		    		
		    		//log.info(" Código do Serviço para validação " + i + ": " + servicoCode +  "  Linha do Produto:" + lote.getLinha().getEntityId());
		    		
		    		if ( servicoCode != null && !"".equals(servicoCode) ) {
		    			
		    			servicoNew = garantiaHeaderBusiness.getServiceByChassi(servicoCode, garantia.getModelo(), lote.getLinha());
		    		
			    		//log.info("Serviço Existente?" + (servicoNew != null));
			    		
			    		if ( servicoNew != null )
			    			
			    			servicos.add(servicoNew);
			    		
			    		else {
			    			
			    			// Retornamos para form com os dados e a mensagem 
			    			messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("garantia.form.msg.servico.notFound", servicoCode) );
				            super.addProblemMessages(request, messagesProblem);
				            
				            return mapping.findForward("_form");
			    			
			    		}
			    		
		    		}
		    		
		    	}
				
		    	if ( servicos == null || (servicos != null && servicos.isEmpty())) {
		    		
					// Retornamos para form com os dados e a mensagem				
		            messagesWarning.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("garantia.form.msg.servico.empty") );
		            super.addWarningMessages(request, messagesWarning);	 
		            isSaved = false;
		            
		    	}  else {
    				
    				// Quando a lista de serviços não estiver vazia,  devemos verificar
    				// se é uma alteração para retirar os serviços existentes
		    		
		    		if ( isEdit ) {
		    			
	    				List grupo = garantia.getGrupos();
	    				
	    				Iterator itG  = grupo.iterator();
	    				while ( itG.hasNext() ) {
	    					
	    					ServicoGrupo servicoGrupo = (ServicoGrupo) itG.next();
	    					servicoGrupo.setDataTermino(new Date());		    					
	    					
	    				}
	    				
		    		}
    				
    			}
		    	// **************************************************************** //
		    	// * FIM - Validação dos códigos de serviço 	    			  * //
				// **************************************************************** //
		    	
		    	// Validamos se foi digitado valor de terceiros
		    	if ( servTer != 0 ) {
				    	// Validar o valor de serviços de terceiros		    	
				    	ParametroSistema parametroSistema = garantiaHeaderBusiness.getByParameterSystem(ParametroSistema.VALOR_SERVICOS_TERCEIROS);
				    	if ( parametroSistema != null ) {
					    	
				    		if ( Double.parseDouble(parametroSistema.getValorParametro()) < servTer) {
					    		
					    		alertGarantia = new AlertGarantia();
				    			alertGarantia.setAlertGarantiaText("O valor de serviço de terceiros execede o limite!");
				    			alertGarantia.setAlertGarantiaSeveridade(AlertGarantia.SEVERIDADE_WARNING);
				    			alertGarantia.setAlertGarantiaKey("garantia.msg.warning.valor.servico.terceiro");
				    			ControllerHelper.prepare(alertGarantia, (Long)usuario.getEntityId());
				    			
				    			listAlertas.add(alertGarantia);
					    	}
				    		
				    	} else {
				    		
				    		// Retornamos para form com os dados e a mensagem 
			    			messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("garantia.form.msg.parametro.sistema.empty") );
				            super.addProblemMessages(request, messagesProblem);
				            
				            return mapping.findForward("_form");
				    		
				    	}
		    	}
		    	
		    	garantia.setValorServicoTerceiro(servTer);
		    	garantia.setJustServicoTerceiro(justif);
		    	
		    	// Valores temporários, pois os campos estão como obrigatórios e não estão especificados no MD 50		    	
		    	garantia.setGarantiaCode("-");
		    	garantia.setRecusaId(new Long(-1));
		    	garantia.setOrganizationId((Long)faturamento.getEmpresa().getEntityId());
		    	
		    	
		    	// **************************************************************** //
		    	// * Atribuição do status para a Garantia	 	    			  * //
				// **************************************************************** //
		    	StatusGarantia statusGarantia = new StatusGarantia();
		    	// Se for criação da Garantia
		    	// seu status deve ser "DIGITACAO"
		    	if ( !isEdit ) {
		    		
		    		statusGarantia.setEntityId(StatusGarantia.STATUS_DIGITACAO);
			    	garantia.setStatusGarantia(statusGarantia);	
			    	
		    	} 
		    	
		    	// Quando for Recall não precisamos realizar estas validações
		    	if ( !isRecall ) { 		    	
		    		
			    	// Validar produto em garantia
			    	List alertasPrazo = garantiaHeaderBusiness.validatePrazo(isConcessionaria, garantia, faturamento);		    	
			    	//AlertGarantia alertGarantia_Prazo = garantiaHeaderBusiness.validatePrazo(garantia, faturamento);
			    	
			    	Iterator itPrazo = alertasPrazo.iterator();
			    	while ( itPrazo.hasNext() ) {
			    		
			    		AlertGarantia alertGarantia_Prazo = (AlertGarantia) itPrazo.next();
			    	
				    	// Verificamos se existe alerta e  se é erro
				    	if ( alertGarantia_Prazo != null ) {
				    		
				    		ControllerHelper.prepare(alertGarantia_Prazo, (Long)usuario.getEntityId());
				    		
				    		if ( (alertGarantia_Prazo.getAlertGarantiaKey().indexOf("error") != -1) &&  autorizacaoEspecialSG == null  ) {
					    		
					    		isSaved = false;
					    		// Retornamos para form com os dados e a mensagem 
				    			messagesProblem.add( ActionMessages.GLOBAL_MESSAGE
				    					             , new ActionMessage(alertGarantia_Prazo.getAlertGarantiaKey()) );
				    			super.addProblemMessages(request, messagesProblem);
					    		
					    	} else {
					    		
					    		listAlertas.add(alertGarantia_Prazo);	
					    		
				    			messagesWarning.add( ActionMessages.GLOBAL_MESSAGE
				    					             , new ActionMessage(alertGarantia_Prazo.getAlertGarantiaKey()) );
				    			super.addWarningMessages(request, messagesWarning);	 
					    		
					    	}
				    		
				    	}	
				    	
			    	}
			    	
		    	} // fim da verificação de recall
			    	
		    	// Validamos o prazo apenas se não for edição
		    	if ( !isEdit ) { 
		    	
			    	// Validar prazo de envio da Garantia após fechamento da OS
		    		// O prazo é parametrizado e deve ser recuperado da table de parâmetros do sistema
			    	AlertGarantia alertGarantia_PrazoEnvioEnvio = garantiaHeaderBusiness.validatePrazoEnvio(garantia, usuario);
			    	// Verificamos se existe alerta e  se é warning ou error
			    	if ( alertGarantia_PrazoEnvioEnvio != null ) {
			    		
			    		if ( alertGarantia_PrazoEnvioEnvio.getAlertGarantiaKey().indexOf("warning") != -1 ) {
				    		
			    			ControllerHelper.prepare(alertGarantia_PrazoEnvioEnvio, (Long)usuario.getEntityId());
			    			
			    			listAlertas.add(alertGarantia_PrazoEnvioEnvio);
			    			
			    			messagesWarning.add( ActionMessages.GLOBAL_MESSAGE
			    					             , new ActionMessage(alertGarantia_PrazoEnvioEnvio.getAlertGarantiaKey()) );
			    			super.addWarningMessages(request, messagesWarning);	 
			    						    		
				    	} else if ( (alertGarantia_PrazoEnvioEnvio.getAlertGarantiaKey().indexOf("error") != -1) &&  autorizacaoEspecialSG == null) {
				    		
				    		isSaved = false;
				    		
				    		if(alertGarantia_PrazoEnvioEnvio.getAtributo() != null) {
					    		// Retornamos para form com os dados e a mensagem 
				    			messagesProblem.add( ActionMessages.GLOBAL_MESSAGE
				    					             , new ActionMessage(alertGarantia_PrazoEnvioEnvio.getAlertGarantiaKey(),alertGarantia_PrazoEnvioEnvio.getAtributo()) );
				    		} else {
				    			// Retornamos para form com os dados e a mensagem 
				    			messagesProblem.add( ActionMessages.GLOBAL_MESSAGE
				    					             , new ActionMessage(alertGarantia_PrazoEnvioEnvio.getAlertGarantiaKey(),alertGarantia_PrazoEnvioEnvio.getAtributo()) );
				    		}
			    			super.addProblemMessages(request, messagesProblem);
				    		
				    	} 		    		
			    		
			    	}
			    	
		    	}
		    	
		    	// Validar o valor de terceiros
		    	AlertGarantia alertGarantia_valorTerceiro = garantiaHeaderBusiness.validateValorTerceiro(garantia);
		    	// Verificamos se existe alerta e  se é warning
		    	if ( alertGarantia_valorTerceiro != null ) {
		    		
		    		if ( alertGarantia_valorTerceiro.getAlertGarantiaKey().indexOf("warning") != -1 ) {
		    			
		    			ControllerHelper.prepare(alertGarantia_valorTerceiro, (Long)usuario.getEntityId());
		    			
		    			listAlertas.add(alertGarantia_valorTerceiro);
		    			
		    			messagesWarning.add( ActionMessages.GLOBAL_MESSAGE
		    					             , new ActionMessage(alertGarantia_valorTerceiro.getAlertGarantiaKey()) );
		    			super.addWarningMessages(request, messagesWarning);	 
		    						    		
			    	}		    		
		    		
		    	}
				
		    	//log.info(" Validated:" + validated);
		    	
				// Verificamos se já foi confirmada a operação
				// Senão enviamos a confirmação com as possíveis mensagens
		    	if ( !validated ) {
		    		
					garantiaForm.set( "validated", "true"  );
					
					String msg = "";
					
					if ( isEdit )
						msg = "Confirma a alteração da Solicitação de Garantia?";
					else {
						CampaignBusiness campaignBusiness = (CampaignBusiness) springContext.getBean("campaignBO");
						msg = campaignBusiness.checkCampaingByChassi(chassi, true);
						if("".equals(msg))
							msg = "Confirma a inclusão da Solicitação de Garantia?";							
					}
		    		this.setConfirmMessage(request, msg, "save()", "cancel()");
		    		
		    	} else {
	    			
		    		//log.info(" Autorização Especial!");
	    			// Verificamos se existe autorização especial, apenas se existirem erros
		    		if ( !isSaved ) {		    			
		    			
		    			
			    		if ( autorizacaoEspecialSG != null ) {
			    			
			    			// Existindo autorização especial mudamos os status para salvar
			    			isSaved = true;
			    			
			    		} else {
			    			
			    			messagesProblem.add( ActionMessages.GLOBAL_MESSAGE
			    								 , new ActionMessage("garantia.form.msg.error.notSave") );
				            super.addProblemMessages(request, messagesProblem);	
				            
				            garantiaForm.set( "validated"  , "false" );
			    			
			    		}
			    	
		    		}
		    		
		    		//****************************************************************************************//
		    		//* INICIO - Rotina para gravar uma garantia com os serviços relacionados				 *//
		    		//****************************************************************************************//
		    		if ( isSaved ) {
		    			
		    			//log.info(" Salvando!");
		    			
		    			if ( autorizacaoEspecialSG != null ) {
		    				
		    				if ( isUtil ) {
		    					autorizacaoEspecialSG.setStatusUso(AutorizacaoEspecialSG.STATUS_USO_YES);
		    					garantia.setAutorizacaoEspecialSG(autorizacaoEspecialSG);
		    				} else {
		    					autorizacaoEspecialSG.setStatusUso(AutorizacaoEspecialSG.STATUS_USO_NOT);
		    					garantia.setAutorizacaoEspecialSG(null);
		    				}
		    				
			    			ControllerHelper.prepare(autorizacaoEspecialSG, (Long)usuario.getEntityId());
			    			//autEspecSGBusiness.save(autorizacaoEspecialSG);
			    			
		    			}					
		    			
				    	if ( isRecall && recallId != null ) {				    		
				    		garantia.setRecall(recall);
				    	}
		    			
		    			// Setamos os dados padrão da entitdade
			    		ControllerHelper.prepare(garantia, (Long)usuario.getEntityId());
		    			
		    			// Agora salvamos
		    			//garantiaHeaderBusiness.save(garantia, listAlertas);
		    			
		    			//log.info("Quantidade de Servicos: " + servicos.size());
		    			
		    			// Gravando os serviços executados na garantia
		    			if ( !servicos.isEmpty() && servicos.size() != 0 && servicos !=  null ) {
		    				
		    				Long codRegiao = null;
		    				Concessionaria concessionaria = (Concessionaria)usuario.getAttribute("concessionaria");
		    				
		    				// recuperamos o código da região para o cálculo do valor de serviço
		    				if ( lote.getLinha().getEntityId().equals(new Long(1)) || lote.getLinha().getEntityId().equals(new Long(4)) ) 
		    					codRegiao = concessionaria.getRegiaMoto();
		    				else if ( lote.getLinha().getEntityId().equals(new Long(2)) || lote.getLinha().getEntityId().equals(new Long(3)) ) 
		    					codRegiao = concessionaria.getRegiaNautica();	
		    				
		    				ParametroSistema parametroBonif = null;
		    				
		    				if ( "S".equalsIgnoreCase(concessionaria.getFlagBonificacao()) ) {
		    					
		    					parametroBonif = garantiaHeaderBusiness.getByParameterSystem(ParametroSistema.BONIFICACAO);
		    				}
		    				
		    				ServicoGrupo servicoGrupo = null;
		    				Iterator itS = servicos.iterator();
		    				
		    				while ( itS.hasNext() ) {
		    					
		    					Servico servico = (Servico)itS.next();
		    					
		    					//log.info("Servico: " + servico.getDescricao());
		    					
		    					// Salvamos também um ServicoGrupo
				    			servicoGrupo = new ServicoGrupo();	
				    			
				    			TempoPadrao  tempo = (TempoPadrao)garantiaHeaderBusiness.getTempoPadrao(servico, chassi.toUpperCase());
				    			
				    			if (tempo == null ) {
				    				
				    				messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("garantia.msg.error.businessRole","\n Não foi localizado o tempo padrão para o modelo :" + ControllerHelper.getModeloByChassi(chassi)) );
		    			            super.addProblemMessages(request, messagesProblem);
		    			            
		    			            return mapping.findForward("_form");
				    								    				
				    			}
				    			
				    			double 	     valorServicoTemp   = 0;
				    			double 		 bonificacao	    = 0;
		    					// Se não for inclusão de garantia de recall 
		    					if ( !isRecall && recallId == null && recall == null ) {
					    			
			    					ValorServico valorServico = (ValorServico)garantiaHeaderBusiness.getValorServico(codRegiao, chassi.toUpperCase());
			    								    					
			    					//log.info("valorServico:" + ( valorServico != null ) + " TempoPadrao:" + ( tempo != null ) );
			    					if ( valorServico == null ) {
			    						
			    						messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("garantia.msg.error.businessRole","Não foi localizado o valor de Serviço para o modelo :" + ControllerHelper.getModeloByChassi(chassi)) );
			    			            super.addProblemMessages(request, messagesProblem);
			    			            
			    			            return mapping.findForward("_form");		    						
			    						
			    					}
			    					
					    			// Valor do Servico é : tempo padrão multiplicado pelo valor de mão de obra de valor serviço
					    			// Se existir bonificação para a concessionária, acrescentamos o percentual.
					    			try {
					    				
					    				valorServicoTemp = tempo.getTempo() * valorServico.getPriValorMaoObra();
					    				
					    				if ( "S".equalsIgnoreCase(concessionaria.getFlagBonificacao()) ) {
					    					
					    					bonificacao = new Double(parametroBonif.getValorParametro()).doubleValue();
					    					//valorServicoTemp += (valorServico.getPriValorMaoObra() * bonificacao/100);
					    					valorServicoTemp += (valorServicoTemp * bonificacao/100);
					    					
					    				}
					    				
					    				//log.info("Valor Total:" + valorServicoTemp);
					    				
					    				servicoGrupo.setValorServico(NumberUtils.round(valorServicoTemp,2));
					    			
					    			} catch (NullPointerException npExp) {
					    				
					    				//log.info("Exception na recuperação do valor de Serviços!");
					    				
					    				servicoGrupo.setValorServico(0);
					    			}
					    		// Se for recall
		    					} else {
		    						
		    						// Aqui verificamos qual é  entidade corresponde em recallServico e pegamos o valor do servico
		    						Iterator srvIt = recall.getRecallServicos().iterator();
		    						
		    						while ( srvIt.hasNext() ) {
		    							
		    							RecallServico recallServico = (RecallServico)srvIt.next();
		    							
		    							if ( recallServico.getServico().getEntityId().equals(servico.getEntityId()) ) {
		    										    								
		    								//log.info("Valor Tempo:" + tempo.getTempo() + "  - Servico:" + recallServico.getValorServicoRecall());
		    								valorServicoTemp = tempo.getTempo() * recallServico.getValorServicoRecall();
						    				//log.info("Valor Total:" + valorServicoTemp);
			    							servicoGrupo.setValorServico( NumberUtils.round(valorServicoTemp,2));		    								
		    								
		    							}
		    							
		    						}
		    						
		    					}
				    			servicoGrupo.setServico(servico);
				    			ControllerHelper.prepare(servicoGrupo, (Long)usuario.getEntityId());
				    			garantia.addServicoGrupo(servicoGrupo);
				    			servicoGrupo.setGarantia(garantia);
				    			//garantiaHeaderBusiness.saveEntity(servicoGrupo);
				    			
		    				}    				
		    				
		    			}
		    			
		    			/*
		    			// Agora salvamos
		    			garantiaHeaderBusiness.save(garantia, listAlertas);
		    			
		    			// Esta rotina é um erro grave de estrutura do banco
		    			// Temos que pegar o id que foi gerado a partir da gravação da entidade e 
		    			// atualizar em outro campo dela mesmo, pfffff!  ahhh, sem contar a conversão ainda!!!!!!
		    			garantia.setGarantiaCode(garantia.getEntityId().toString());
		    			garantiaHeaderBusiness.saveEntity(garantia);
		    			*/
		    			
		    			GarantiaTO garantiaTO = new GarantiaTO();
		    			
		    			garantiaTO.setGarantia(garantia);
		    			garantiaTO.setAutorizacaoEspecialSG(autorizacaoEspecialSG);
		    			garantiaTO.setListAlertas(listAlertas);
		    			garantiaTO.setInfoMercado(infoMercado);
		    			
		    			YmSessionHelper.removeGarantiaTOToSession(request);
		    			YmSessionHelper.setGarantiaTOToSession(request, garantiaTO);
		    			
		    			//request.setAttribute("garantiaId"       , (Integer)garantia.getEntityId());
		    			//request.setAttribute("chassi"           , garantia.getModelo());
		    			//request.setAttribute("numeroOS"         , garantia.getNumeroOS());
		    			//request.setAttribute("dataAberturaOS"   , garantia.getMaskedDataAberturaOS());
		    			//request.setAttribute("dataFechamentoOS" , garantia.getMaskedDataFechamentoOS());
		    			
		    			// Aqui verificamos se é produto novo, tanto para moto como para naútica utilizamos
		    			// isZeroKm
		    			if ( garantia.getQuilometragem() != null ) {
		    				
		    				request.setAttribute("isZeroKm" 		, garantia.getQuilometragem().equals(new Long(0)) ? new Boolean(true):new Boolean(false));
		    			
		    			} else if ( garantia.getDiasUso() != null ) {
		    				
		    				request.setAttribute("isZeroKm" 		, garantia.getDiasUso().equals(new Long(0)) ? new Boolean(true):new Boolean(false));
		    				
		    			} else {
		    				
		    				request.setAttribute("isZeroKm", new Boolean(false));
		    				
		    			}
		    			
		    			//request.setAttribute("isZeroKm" 		, garantia.getQuilometragem().equals(new Long(0)) ? new Boolean(true):new Boolean(false));
		    			request.setAttribute("loteId"           , loteId);
		    			request.setAttribute("isEdit"           , isEdit == true ? "true" : "false");
		    			request.setAttribute("consulta" 		, new Boolean(false));
		    			
		    			if ( isRecall && recallId != null )
		    				request.setAttribute("recall"           , recall );
		    			
		    			//log.info(" Voltando!");
		    			
		    			if ( !isEdit )
		    				return mapping.findForward("_peca");
		    			else 
		    				return this.alterLine(mapping, form, request, response);
		    		}
		    		
		    	}
		    	
			}
			// **************************************************************** //
	    	// * FIM - Validação do chassi informado       				      * //
	    	// *       Verificação de faturamento e concessionária    	      * //
	    	// **************************************************************** //
					
			return mapping.findForward("_form");	  
			
    	} catch ( BusinessRuleException rExp) { 
    		
    		//log.info("Mensagem em BusinessRuleException:" + rExp.getMessage());
    		
    		// Quando tentamos recuperar um objeto e ele retornar null, apresentamos esta mensagem
    		messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage(rExp.getMessage()) );
            super.addProblemMessages(request, messagesProblem);
            
            return mapping.findForward("_form");	
			
	    } catch (BusinessException bExp) {
			
			throw new ControllerException( bExp );
			
	    } catch ( InvalidSessionException isExp ) {
    		
    		throw new ControllerException(isExp);
    		
		}  
	    
    }
    
    /** Tarefa: Direciona para a alteração de peça.
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
    public ActionForward alterLine( ActionMapping mapping
                             , ActionForm form
                             , HttpServletRequest request
                             , HttpServletResponse response) throws ControllerException {
    	
    	DynaValidatorActionForm garantiaForm = (DynaValidatorActionForm) form;
    	
    	Integer garantiaId = (Integer)garantiaForm.get("entityId");
    	String consulta = request.getParameter("view");
    	
    	try {
    		
    		GarantiaHeaderBusiness garantiaHeaderBusiness = (GarantiaHeaderBusiness) springContext.getBean("garantiaHeaderBO");
    		
    		GarantiaHeader garantia = garantiaHeaderBusiness.get(garantiaId);
    		
	    	request.setAttribute("garantiaId"       , (Integer)garantia.getEntityId());
			request.setAttribute("chassi"           , garantia.getModelo());
			request.setAttribute("numeroOS"         , garantia.getNumeroOS());
			request.setAttribute("dataAberturaOS"   , garantia.getMaskedDataAberturaOS());
			request.setAttribute("dataFechamentoOS" , garantia.getMaskedDataFechamentoOS());
			request.setAttribute("loteId"           , garantia.getLote().getEntityId());
			
			// Aqui verificamos se é produto novo, tanto para moto como para naútica utilizamos
			// isZeroKm
			if ( garantia.getQuilometragem() != null ) {
				
				request.setAttribute("isZeroKm" 		, garantia.getQuilometragem().equals(new Long(0)) ? new Boolean(true):new Boolean(false));
			
			} else if ( garantia.getDiasUso() != null ) {
				
				request.setAttribute("isZeroKm" 		, garantia.getDiasUso().equals(new Long(0)) ? new Boolean(true):new Boolean(false));
				
			} else {
				
				request.setAttribute("isZeroKm", new Boolean(false));
				
			}
			
			request.setAttribute("isEdit"           , "true");
			
			if ( !"".equals(consulta) && consulta != null ) {
				request.setAttribute("consulta" , new Boolean(true) );
				this.setJavaScriptExecuter(request, "viewPeca();");
			} else
				request.setAttribute("consulta" , new Boolean(false));
			
			if ( garantia.getRecall() != null )
				request.setAttribute("recall"           , garantia.getRecall() );
			
			return mapping.findForward("_peca");
    	
    	} catch (BusinessException bExp) {
			
			throw new ControllerException( bExp );
			
	    }
    	
    }
    
    
    /** Tarefa: Monta o form para alteração da Garantia.
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
    	
    	DynaValidatorActionForm garantiaForm = (DynaValidatorActionForm) form;
    	ActionMessages 			messagesProblem    = new ActionMessages();
    	
    	int garantiaId = Integer.parseInt(request.getParameter("garantiaId"));
    	
    	boolean readOnly = "true".equalsIgnoreCase(request.getParameter("readonly")) ? true : false;
    	//log.info("readOnly 1:" + readOnly);
    	 
		String  alterState  = "false";
    	
    	try {
	    	
    		GarantiaHeaderBusiness garantiaHeaderBusiness = (GarantiaHeaderBusiness) springContext.getBean("garantiaHeaderBO");
    		InfoMercadoBusiness    infoMercadoBusiness    = (InfoMercadoBusiness) springContext.getBean("infoMercadoBO");
    		AnaliseGarantiaBusiness  analiseGarantiaBusiness  = (AnaliseGarantiaBusiness)  springContext.getBean("analiseGarantiaBO");
    		GarantiaTO garantiaTO = YmSessionHelper.getGarantiaTOFromSession(request);
    		GarantiaHeader garantia = null;
    			
    		if (garantiaTO != null ) {
    			
    			garantia = garantiaTO.getGarantia();
    			
    		} else {
    		
    			garantia = garantiaHeaderBusiness.get(new Integer(garantiaId));
    			
    		}
			
			List listRecall = null;
			
			// Carregamos algumas entidades necessárias para retornar para o form
			request.setAttribute("idTipo"       , garantia.getLote().getLinha().getEntityId()     );
			request.setAttribute("statusLoteId" , garantia.getLote().getStatusLote().getEntityId());
			request.setAttribute("consulta"     , new Boolean(readOnly) 						  );
			
			garantiaForm.set( "lote"  		  , garantia.getLote().getEntityId() );
			garantiaForm.set( "linhaId"  	  , garantia.getLote().getLinha().getEntityId());
			
			// Verificamos o status do lote para peemitir ou não a alteração
			// Se o status do lote(status_lote_id) for igual a 1, permitir
			// Se o status do lote(status_lote_id) for igual a 2 ou 4, não permitir
			Long statusLote = (Long)garantia.getLote().getStatusLote().getEntityId();
			
			//log.info(" - O status do lote é:" + statusLote );
			if (!statusLote.equals(StatusLote.STATUS_EM_DIGITACAO) && !statusLote.equals(StatusLote.STATUS_MANUTENCAO)) {
				alterState = "true";
			}
			/* alterado - 11/08/2013  ist_edson
			if ( statusLote.equals(StatusLote.STATUS_EM_DIGITACAO)) {
				
				alterState = "false";
				
			// Se o status não igual a 1 ou 2 ou 4 ou 5 emitir alerta de erro	
			} else if ( statusLote.equals(StatusLote.STATUS_ANALISE) || statusLote.equals(StatusLote.STATUS_MANUTENCAO) || statusLote.equals(StatusLote.STATUS_AGUARDANDO_DIGITACAO_NOTA) ) {
				
				alterState = "true";
				
			} else {	
				
				messagesProblem.add( ActionMessages.GLOBAL_MESSAGE
						 , new ActionMessage("garantia.msg.error.status.edit") );
				super.addProblemMessages(request, messagesProblem);	
   
				garantiaForm.set( "validated"  , "false" );
				this.setAlertMessage(request   , "O Status do lote é inválido!");				
				
			}
			*/
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
						 
						 garantiaForm.set(nameField         , servico.getServicoCode());
						 garantiaForm.set(descServicoField  , servico.getDescricao());
						 
					 } else {
						 
						 log.info("A garantia n. " + garantia.getEntityId() + " possui mais de 3 serviços cadastrados!");
						 
					 }
					 
				 }
				 
			 }
			 
			 // Setamos os valores do form
			 garantiaForm.set( "entityId"  , garantia.getEntityId());
			 garantiaForm.set( "chassi"    , garantia.getModelo().toUpperCase());
			 garantiaForm.set( "km"        , garantia.getQuilometragem());
			 garantiaForm.set( "numOS"	   , garantia.getNumeroOS());
			 garantiaForm.set( "diasUso"   , garantia.getDiasUso());
			 garantiaForm.set( "horasUso"  , garantia.getHorasUso());
			 garantiaForm.set( "dtAbert"   , garantia.getMaskedDataAberturaOS());
			 garantiaForm.set( "dtFech"    , garantia.getMaskedDataFechamentoOS());
			 garantiaForm.set( "hasServTer", garantia.getValorServicoTerceiro() != 0 ? "S" : "N" );
			 garantiaForm.set( "servTer"   , NumberUtils.formatNumberCurrency(garantia.getValorServicoTerceiro()));
			 garantiaForm.set( "justif"    , garantia.getJustServicoTerceiro());
			 garantiaForm.set( "cortesia"  , garantia.getAutorizacaoEspecialSG() != null ? "S" : "N" );
			 garantiaForm.set( "autEsp"	   , garantia.getAutorizacaoEspecialSG() != null ? garantia.getAutorizacaoEspecialSG().getNumAutorizacao() : "" );
			 garantiaForm.set( "alterState", alterState);
			 garantiaForm.set( "sysDate"   , DateUtils.applyMask(new Date()));

			 garantiaForm.set( "preenchidoPor"    , garantia.getPreenchidoPor());
			 garantiaForm.set( "condicaoProblema" , garantia.getCondicaoProblemaGarantia() != null ? garantia.getCondicaoProblemaGarantia().getCondicaoProblema():"");
			 garantiaForm.set( "causaProblema"    , garantia.getCausaProblemaGarantia() != null ? garantia.getCausaProblemaGarantia().getCausaProblema():"");
			 garantiaForm.set( "diagnostico"      , garantia.getDiagnosticoProblemaGarantia() != null ? garantia.getDiagnosticoProblemaGarantia().getDiagnosticoProblema():"");
			 garantiaForm.set( "solucao"          , garantia.getSolucaoProblemaGarantia() != null ? garantia.getSolucaoProblemaGarantia().getSolucaoProblema():"");
			 
			 String parecerYm    = analiseGarantiaBusiness.listHistParecerTecnico((Integer)garantia.getEntityId());
			 String observacaoYm = analiseGarantiaBusiness.listHistAnaliseObservacao((Integer)garantia.getEntityId());
			 
			 if(parecerYm != null && !"".equals(parecerYm) || observacaoYm != null && !"".equals(observacaoYm)){
				 garantiaForm.set( "hasParecerYm"	, "true");
				 garantiaForm.set( "parecerYm"		, parecerYm);
			 	 garantiaForm.set( "observacaoYm"	, observacaoYm);
			 }
			 
			 if ( garantia.getEntityId() != null )
				 garantiaForm.set( "isEdit"	   , "true");
			 else
				 garantiaForm.set( "isEdit"	   , "false");
			 
			 // Quando for uma garantia de recall
			 if ( garantia.getRecall() != null ) {
				 
				listRecall =  garantiaHeaderBusiness.listRecall(garantia.getModelo());
				 
				//log.info("Recalls:" + listRecall.size());
					
				garantiaForm.set( "sintomaIdRecall"    , garantia.getRecall().getSintoma().getEntityId() );
				garantiaForm.set( "sintomaDescRecall"  , garantia.getRecall().getSintoma().getDescricaoCode().toUpperCase() );
				
				garantiaForm.set( "condicaoIdRecall"   , garantia.getRecall().getCondicao().getEntityId() );
				garantiaForm.set( "condicaoDescRecall" , garantia.getRecall().getCondicao().getDescricaoCode().toUpperCase() );
				
				garantiaForm.set( "listServicos"       , garantia.getRecall().getServicos() );
				
				garantiaForm.set( "listRecall"    , listRecall );
				garantiaForm.set( "listCampaign"  , new ArrayList() );	
				garantiaForm.set( "listSintomas"  , new ArrayList() );
				garantiaForm.set( "listCondicoes" , new ArrayList() );
				
				garantiaForm.set( "isRecall"      , "true" );
				 
				garantiaForm.set("recallId"  , garantia.getRecall().getEntityId());
			 	garantiaForm.set("recall"    , "S");
			 	 
			 } else {
				 
				garantiaForm.set( "recall"        , "N");
				garantiaForm.set( "hasInforMercado" , String.valueOf(garantia.getHasInfoMercado()));
				garantiaForm.set( "infoMercadoId" , garantia.getHasInfoMercado() ? garantia.getInfoMercado().getEntityId():null);
				garantiaForm.set( "sintoma"       , garantia.getSintoma() != null ? garantia.getSintoma().getEntityId()  : new Long(0) );
				garantiaForm.set( "condicao"      , garantia.getCondicao()!= null ? garantia.getCondicao().getEntityId() : new Long(0) );
				garantiaForm.set( "listSintomas"  , garantiaHeaderBusiness.listSintomas( garantia.getLote().getLinha() ) );
				garantiaForm.set( "listCondicoes" , garantiaHeaderBusiness.listCondicoes( garantia.getLote().getLinha() ) );
				garantiaForm.set( "listServicos"  , garantiaHeaderBusiness.listServicos( garantia.getLote().getLinha() ) );
				garantiaForm.set( "listRecall"    , new ArrayList() );	
				garantiaForm.set( "listCampaign"  , new ArrayList() );	
				garantiaForm.set( "sintomaId"     , garantia.getSintoma() != null ? garantia.getSintoma().getEntityId()  : new Long(0) );
				garantiaForm.set( "condicaoId"    , garantia.getCondicao()!= null ? garantia.getCondicao().getEntityId() : new Long(0) );
				
				garantiaForm.set( "tipoGasolinaId"   , garantia.getTipoGasolinaId() );
				garantiaForm.set( "localUsoDoce"     , garantia.getLocalUsoDoce() );
				garantiaForm.set( "localUsoSalg"     , garantia.getLocalUsoSalg() );
				garantiaForm.set( "materHelice"      , garantia.getMaterHelice() );
				garantiaForm.set( "tipoUso"          , garantia.getTipoUso() );
				garantiaForm.set( "cidadeUso"        , garantia.getCidadeUso() );
				garantiaForm.set( "marcaCasco"       , garantia.getMarcaCasco() );
				garantiaForm.set( "modeloCasco"      , garantia.getModeloCasco() );
				garantiaForm.set( "marcaHelice"      , garantia.getMarcaHelice() );
				garantiaForm.set( "passoHelice"      , garantia.getPassoHelice() );
				garantiaForm.set( "rotacaoMaxima"    , garantia.getRotacaoMaxima() );
				
				garantiaForm.set("listTipoGasolina", infoMercadoBusiness.listTipoGasolina());
				garantiaForm.set("listTipoProduto" , infoMercadoBusiness.listTipoProdutoInfo() );
				garantiaForm.set("listTipoUso"     , infoMercadoBusiness.listTipoUso()); 
			 }
			 
			 //log.info("readOnly 2:" + readOnly);
			 
			 if ( readOnly ) {
				 
				 this.setJavaScriptExecuter(request, "setTitleAlter('Consultar Solicitação de Garantia');getGarantiaValuesRetorno()" );
				 
			 } else {
				 
				 this.setJavaScriptExecuter(request, "setTitleAlter('Alterar Solicitação de Garantia');getGarantiaValuesRetorno()" );
				 
			 }	
			 
			 YmSessionHelper.removeGarantiaTOToSession(request);
				
			return mapping.findForward("_form");
	    	
    	
	    } catch ( BusinessRuleException rExp) { 
			
			// Quando tentamos recuperar um objeto e ele retornar null, apresentamos esta mensagem
			messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage(rExp.getMessage()) );
	        super.addProblemMessages(request, messagesProblem);
	        
	        return mapping.findForward("_form");
	        
		} catch (BusinessException bExp) {
			
			throw new ControllerException( bExp );
			
	    } 
    }
    
    /** Tarefa: Remove um Serviço relacionado com a Solicitação de Garantia.
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
    public ActionForward removeService( ActionMapping mapping
                             , ActionForm form
                             , HttpServletRequest request
                             , HttpServletResponse response) throws ControllerException {
    	
    	int garantiaId  = Integer.parseInt(request.getParameter("garantiaId"));
    	String servicoCode = request.getParameter("servicoCode");
    	
    	try {
	    	
    		GarantiaHeaderBusiness garantiaHeaderBusiness = (GarantiaHeaderBusiness) springContext.getBean("garantiaHeaderBO");
    		GarantiaHeader garantia = garantiaHeaderBusiness.get(new Integer(garantiaId));
    		
    		ServicoGrupo servicoGrupo = garantia.getServico(servicoCode);
    		
    		garantiaHeaderBusiness.removeEntity(servicoGrupo);
    		
    		this.setAlertMessage(request, "O serviço código " + servicoCode + " foi removido com sucesso!");
    		
    		return this.alter(mapping, form, request, response);
    		
    		
    	} catch (BusinessException bExp) {
			
			throw new ControllerException( bExp );
			
	    }
    	
    }
}
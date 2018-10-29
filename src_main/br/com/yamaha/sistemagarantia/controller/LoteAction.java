/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......LoteFormAction.java
 *   .: Criação.....24 de abril, 12:17
 *   .: Autor.......Edson Luiz Sumensari
 *                  Thiago Uriel M. Garcia
 *   .: Descrição...Action para o formulário de inclusão de Lote.
 */
package br.com.yamaha.sistemagarantia.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
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
import br.com.yamaha.sistemagarantia.business.ConcessionariaBusiness;
import br.com.yamaha.sistemagarantia.business.LinhaBusiness;
import br.com.yamaha.sistemagarantia.business.LoteBusiness;
import br.com.yamaha.sistemagarantia.business.StatusLoteBusiness;
import br.com.yamaha.sistemagarantia.business.TipoLoteBusiness;
import br.com.yamaha.sistemagarantia.controller.helper.ControllerHelper;
import br.com.yamaha.sistemagarantia.model.Concessionaria;
import br.com.yamaha.sistemagarantia.model.Linha;
import br.com.yamaha.sistemagarantia.model.Lote;
import br.com.yamaha.sistemagarantia.model.StatusLote;
import br.com.yamaha.sistemagarantia.model.TipoLote;
import br.com.yamaha.sistemagarantia.model.Usuario;
import br.com.yamaha.sistemagarantia.view.GarantiaVO;
import br.com.yamaha.sistemagarantia.view.LoteCompactVO;

/** Classe que controla as tarefas relacionadas ao <b>Lote</b>.
 * 
 *  @author Edson Luiz Sumensari
 *  @author Thiago Uriel M. Garcia
 */
public class LoteAction extends InfraDispatchAction {

  
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
    	
    	
    	// Verificar se o usuário identificado é concessionária e tem acesso ao programa "Sg_cd_lote"
    	// Apresentar mensagem de acesso negado caso o usuário não possua permissão para acessar o programa 
    	// Implementar   	
    	
    	
    	DynaValidatorActionForm loteForm = (DynaValidatorActionForm) form;
        loteForm.initialize(mapping);
        
        try {        	
        	
        	TipoLoteBusiness  tipoLoteBusiness = (TipoLoteBusiness) springContext.getBean("tipoLoteBO");
        	LinhaBusiness  	  linhaBusiness    = (LinhaBusiness) springContext.getBean("linhaBO");
        	
        	// Setando os dados iniciais do form
            loteForm.set("dataLote", 				DateUtils.applyMask(new Date(), "dd/MM/yyyy"));    
            loteForm.set("descriptionLoteTypeList", tipoLoteBusiness.list() );
            loteForm.set("descriptionLineList", 	linhaBusiness.list() );
        	
        	return mapping.findForward("_form");
        	
        } catch ( BusinessException bExp ) {
        	
        	throw new ControllerException();
        	
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
    		
            DynaValidatorActionForm loteForm = (DynaValidatorActionForm) form;
            loteForm.initialize(mapping);
    		
    		LoteBusiness           loteBusiness 		  = (LoteBusiness) springContext.getBean("loteBO"); 
    		StatusLoteBusiness     statusLoteBusiness 	  = (StatusLoteBusiness) springContext.getBean("statusLoteBO");
    		
    		Usuario usuario = (Usuario) UserHelper.getBoundedUser(request.getSession());
    		
    		log.debug("USuário:" + usuario.getUsuarioCode() + " - ID da Sessão:" + request.getSession().getId());
    		
    		//Concessionaria concessionaria = concessionariaBusiness.get(((Concessionaria)usuario.getAttribute("concessionaria")).getEntityId());
    		Concessionaria concessionaria = (Concessionaria)usuario.getConcessionaria();
    		    		
    		Integer linhasPrograma = loteBusiness.getLinhasPagina("Sg_lt_mst");
    		
    		List listStatus = new ArrayList();    		
    		listStatus.add(statusLoteBusiness.get(StatusLote.STATUS_EM_DIGITACAO));
    		listStatus.add(statusLoteBusiness.get(StatusLote.STATUS_MANUTENCAO));
    		
    		//List list = loteBusiness.listByStatus(listStatus, concessionaria);
    		
    		List list = loteBusiness.listLotes(listStatus, concessionaria);
    		
	    	List listStatusLote = statusLoteBusiness.list();
	    	
            loteForm.set( "task"           , "list");
            loteForm.set( "listResults"    , list );
            loteForm.set( "listStatusLote" , listStatusLote); 
            loteForm.set( "criterio"       , StatusLote.STATUS_EM_DIGITACAO );
            
            request.setAttribute("qtdeLinhas", linhasPrograma );
            /*
            if(!concessionaria.getFlagComunicado())  {
            
				if(loteBusiness.listGaranatiasInManut(concessionaria.getEntityId())) {
	
					StringBuffer strMsg = new StringBuffer();
	
					strMsg.append("Prezado concessionário existem itens de Garantia que estão em Manutenção e aguardando sua ação a mais de dois dias, favor verificar!'");
					
					this.setJavaScriptExecuter(request, "window.alert('" + strMsg.toString() + ")" );
				}  
				concessionaria.setFlagComunicado(true);// Marcamos como já comunicado				
            }
            */
    		return mapping.findForward("_list");
    		
    	} catch (BusinessException bExp) {
    		
            throw new ControllerException(bExp);
            
        } catch (InvalidSessionException isExp) {
        	
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
    	
    	DynaValidatorActionForm loteForm = (DynaValidatorActionForm) form;
    	
    	Long loteTypeId = (Long)loteForm.get("idTipoLote");
    	Long lineId		= (Long)loteForm.get("idLinha"); 
    	
    	try {
    		// Recuperamos as instâncias dos business necessários 
    		LoteBusiness 	   		loteBusiness 	  		= (LoteBusiness) 		  springContext.getBean("loteBO");
    		LinhaBusiness 	   		linhaBusiness 	  		= (LinhaBusiness) 		  springContext.getBean("linhaBO");
    		TipoLoteBusiness   		tipoLoteBusiness   		= (TipoLoteBusiness)	  springContext.getBean("tipoLoteBO");
    		StatusLoteBusiness 		statusLoteBusiness 		= (StatusLoteBusiness)	  springContext.getBean("statusLoteBO");
    		ConcessionariaBusiness 	concessionariaBusiness 	= (ConcessionariaBusiness)springContext.getBean("concessionariaBO");    		
    		    		
    		// Criamos as instâncias dos objetos
	    	Lote 			lote 			= new Lote();
	    	Linha 			linha 		  	= new Linha();
	    	TipoLote 		tipoLote 	  	= new TipoLote();
	    	StatusLote 		statusLote 		= statusLoteBusiness.get(StatusLote.STATUS_EM_DIGITACAO);     
	    	Concessionaria 	concessionaria 	= new Concessionaria();		
	    	//SystemUser		usuario			= (Usuario)UserHelper.getBoundedUser(request.getSession());	    	
	    	Usuario usuario = (Usuario) UserHelper.getBoundedUser(request.getSession());
	    	// Recuperamos os objetos relacionados ao Lote  
	    	linha  	 	 	= linhaBusiness.get(lineId);
            tipoLote 	 	= tipoLoteBusiness.get(loteTypeId);
            
            //concessionaria 	= concessionariaBusiness.get(((Concessionaria)usuario.getAttribute("concessionaria")).getEntityId());
            concessionaria = (Concessionaria)usuario.getConcessionaria();
	    	            
            // Setamos o bean Lote
	    	lote.setEntityId        ( loteForm.get("entityId") 		   );
	    	//lote.setDataLote		( loteForm.get("dataLote") 	   	   );
	    	
	    	lote.setDataLote		( new Date() 	   	   			   );
	    	
            lote.setLinha			( linha							   );
            lote.setTipoLote		( tipoLote						   );
            lote.setStatusLote		( statusLote					   );
            lote.setConcessionaria	( concessionaria				   );
            
            ControllerHelper.prepare( lote, (Long)usuario.getIdentifier() );
	    	
            // Salvamos o bean Lote
	    	loteBusiness.save(lote);
    		
	    	//Adicionamos uma mensagem se tudo ocorrer bem
	    	//actionMsgs.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("form.msg.saved") );

            loteForm.initialize(mapping);
            
            request.setAttribute("loteId", (Integer)lote.getEntityId());
            
            this.setJavaScriptExecuter(request, "setTitle()" );
            
            if ( lote.getTipoLote().getEntityId().equals(TipoLote.TIPO_REVISAO) ) {
            	
            	return mapping.findForward("_cupom");
            	
            } else if ( lote.getTipoLote().getEntityId().equals(TipoLote.TIPO_GARANTIA) ) {
            	
            	if(lote.getLinha().getEntityId().equals(Linha.TIPO_MOTOCICLETA)){            	
            		return mapping.findForward("_garantiaMoto");
            	} else {
            		return mapping.findForward("_garantiaNautica");
            	}
            	
            } 
            
            return this.add( mapping, form, request, response);
            
    	} catch (BusinessException bExp) {
    		
            throw new ControllerException(bExp);
            
        } catch ( InvalidSessionException isExp) {
        	
        	throw new ControllerException(isExp);
        	
        }

    }   
    
    /** Tarefa: Alterar o status de um Lote para Liberado.
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
    public ActionForward liberate( ActionMapping mapping
                             , ActionForm form
                             , HttpServletRequest request
                             , HttpServletResponse response) throws ControllerException {
    	
    	ActionMessages 			messagesSuccess    = new ActionMessages();
    	ActionMessages 			messagesProblem    = new ActionMessages();
    	
    	Integer entityId = request.getParameter("loteId") == null ? new Integer(0) :  Integer.valueOf(request.getParameter("loteId"));
    	try {
    		
	    	LoteBusiness loteBusiness = (LoteBusiness) springContext.getBean("loteBO");
	    	 	
	    	Lote lote = loteBusiness.get(entityId);
	    	
	    	if ( loteBusiness.alterStateLiberar(lote) ) {    		
	    	
		    	// 	Adicionamos uma mensagem se tudo ocorrer bem
	    		messagesSuccess.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("lote.msg.liberated", lote.getEntityId()) );
		    	super.addSuccessMessages(request, messagesSuccess);    
		    
	    	} else {
	    			    		
	    		messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("form.msg.notalter", "Lote " + lote.getEntityId()) );
		    	super.addProblemMessages(request, messagesProblem);    
	    		
	    	}
	    	
	    	return this.list( mapping, form, request, response);
    	
	    } catch (BusinessRuleException brExp) {
			
	    	messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("lote.msg.notLiberated", brExp.getMessage()) );
	    	super.addProblemMessages(request, messagesProblem); 
	    	
	    	return this.list( mapping, form, request, response);
	        
	    } catch (BusinessException bExp) {
			
	        throw new ControllerException();
	        
	    } 
    }
    
    /** Tarefa: Alterar o status de um Lote para Cancelado.
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
    public ActionForward cancel( ActionMapping mapping
                             , ActionForm form
                             , HttpServletRequest request
                             , HttpServletResponse response) throws ControllerException {
    	
    	ActionMessages 			actionMsgs 	= new ActionMessages();
    	
    	//Integer entityId = request.getParameter("entityId") == null ?  new Integer(0) :  Integer.valueOf(request.getParameter("entityId"));
    	Integer entityId = request.getParameter("loteId") == null ?  new Integer(0) :  Integer.valueOf(request.getParameter("loteId"));
    	
    	try {
    		
	    	LoteBusiness 	   		loteBusiness 	  		= (LoteBusiness) 		  springContext.getBean("loteBO");
	    	StatusLoteBusiness 		statusLoteBusiness 		= (StatusLoteBusiness)	  springContext.getBean("statusLoteBO");
	    	
	    	Lote 		lote 		= new Lote();	    	
	    	StatusLote 	statusLote 	= new StatusLote();
	    	
	    	lote 		= loteBusiness.get(entityId);	    	
	    	statusLote 	= statusLoteBusiness.get(StatusLote.STATUS_CANCELADO);
	    		    	
	    	// Setar o novo Status
	    	lote.setStatusLote(statusLote);
	    	
	    	if ( !lote.getIsMovimento() ) {
	    	
		    	if ( loteBusiness.alterState(lote) ) {    		
		    	
			    	// 	Adicionamos uma mensagem se tudo ocorrer bem
			    	actionMsgs.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("form.msg.alter", "Lote " + lote.getEntityId()) );
			    	super.addSuccessMessages(request, actionMsgs);    
			    
		    	} else {
		    			    		
			    	actionMsgs.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("form.msg.notalter", "Lote " + lote.getEntityId()) );
			    	super.addProblemMessages(request, actionMsgs);    
		    		
		    	}
		    	
	    	} else {
	    		
	    		actionMsgs.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("form.msg.notalter", "Lote " + lote.getEntityId()) );
		    	super.addProblemMessages(request, actionMsgs);	    		
	    		
	    	}
	    	
	    	return this.list( mapping, form, request, response);
    	
	    } catch (BusinessException bExp) {
			
	        throw new ControllerException();
	        
	    }
	    
    }
    	
    /** Tarefa: Pesquisa.
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
    public ActionForward search( ActionMapping mapping
                               , ActionForm form
                               , HttpServletRequest request
                               , HttpServletResponse response) throws ControllerException {
        
    	DynaValidatorActionForm loteForm = (DynaValidatorActionForm) form;
    	loteForm.initialize( mapping );
    	
    	try {
    		
	    	StatusLoteBusiness statusLoteBusiness = (StatusLoteBusiness) springContext.getBean("statusLoteBO");
	    	
	    	List listStatusLote = statusLoteBusiness.list();
	    	
	    	loteForm.set("listStatusLote", listStatusLote);
	    	
	        return mapping.findForward("_search");
        
    	} catch (BusinessException bExp) {
    		
            throw new ControllerException(bExp);
            
        } 
        
    }
    
    /** Tarefa: Pesquisa.
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
    public ActionForward listSearch( ActionMapping mapping
                               , ActionForm form
                               , HttpServletRequest request
                               , HttpServletResponse response) throws ControllerException {
        
    	DynaValidatorActionForm loteForm = (DynaValidatorActionForm) form;
    	
    	/*Long statusLoteId = request.getParameter("statusLoteId") != null && request.getParameter("statusLoteId") != "" 
    							?  Long.valueOf(request.getParameter("statusLoteId")) 
    							:  new Long(0);*/
    	
    	Long statusLoteId = null;
    	
    	String status = request.getParameter("status");
    	
    	Integer loteId = request.getParameter("loteId") != null ? Integer.valueOf(request.getParameter("loteId")) : new Integer(0);
    	
    	try {
    		
    		Usuario usuario = (Usuario) UserHelper.getBoundedUser(request.getSession());
    		
    		LoteBusiness loteBusiness = (LoteBusiness) springContext.getBean("loteBO");
    		StatusLoteBusiness statusLoteBusiness = (StatusLoteBusiness) springContext.getBean("statusLoteBO");
    		Concessionaria concessionaria = (Concessionaria)usuario.getConcessionaria();
    		
    		Integer linhasPrograma = loteBusiness.getLinhasPagina("Sg_lt_mst");
	    	
	    	List listStatusLote   = statusLoteBusiness.list();
	    	StatusLote statusLote = statusLoteBusiness.get(new Long(1));
    		List list = new ArrayList();   
    		List listStatus = new ArrayList();  
    		
    		//log.info(" -- O loteId é:" + loteId);
    		
    		if ( loteId.equals(new Integer(0) ) ) {
    		    		
	    		if ( !"".equals(status) ) {	    			
	    				    				    	
	    			// Recuperamos os Status selecionados
	    			StringTokenizer stStatus = new StringTokenizer(status);
	    			while ( stStatus.hasMoreTokens() ) {
	    				 
	    				statusLoteId = Long.valueOf(stStatus.nextToken(";"));
	    				statusLote = statusLoteBusiness.get(statusLoteId);
	    				listStatus.add(statusLote); 
	    				
	    				//log.info("Status:" + statusLote.getDescricaoReduzida());
	    			}
	    			
	    		} else {
	    			
	    			//log.info(" -- Recuperando os lotes com status padrão!");
	    			
	    			//statusLoteId = new Long(1);
	    			statusLote = statusLoteBusiness.get(new Long(1));
	    			listStatus.add(statusLote); 
	    			
	    		}
	    		
	    		list = loteBusiness.listLotes(listStatus, concessionaria);
	    		
	    		if ( list.isEmpty() || list == null ) 
	    			this.setAlertMessage(request, "Não foram localizados Lotes com status " + statusLote.getDescricaoReduzida() + "!");
	    		
    		} else {
    			
    			//log.info(" -- Recuperando o lote com id:" + loteId);
    			
    			LoteCompactVO lote = loteBusiness.getByIdList(loteId, concessionaria);
    			
    			if ( lote != null ) {  
    				
    				list.add(lote);
    				
    			} else {
    				
    				this.setAlertMessage(request, "Não foi localizado Lote com número " + loteId + "!");
    				
    			}
    		}
    		
    		loteForm.set( "task"          , "list");
            loteForm.set( "listResults"   , list );
            //loteForm.set( "criterio"      , statusLoteId );
            loteForm.set( "listStatusLote", listStatusLote);
            
            request.setAttribute("qtdeLinhas", linhasPrograma );
            
            return mapping.findForward("_list");
    		
    	} catch (BusinessException bExp) {
    		
            throw new ControllerException(bExp);
            
    	} catch ( InvalidSessionException isExp ) {
        	
        	throw new ControllerException(isExp);
        	
        } 
    	
    }
    
    /** Tarefa: Carrega as informações de um cliente para formulário de Cliente.
     * 			Recebe o id do Cliente como parâmetro
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
    public ActionForward edit( ActionMapping mapping
                             , ActionForm form
                             , HttpServletRequest request
                             , HttpServletResponse response) throws ControllerException {
    	try {
    	
	    	DynaValidatorActionForm loteForm = (DynaValidatorActionForm) form;
	    	loteForm.initialize( mapping );
	    	
	    	int entityId = Integer.parseInt(request.getParameter("numeroLote"));
	    	
	    	LoteBusiness     loteBusiness     = (LoteBusiness) springContext.getBean("loteBO");
        	TipoLoteBusiness tipoLoteBusiness = (TipoLoteBusiness) springContext.getBean("tipoLoteBO");
        	LinhaBusiness  	 linhaBusiness    = (LinhaBusiness) springContext.getBean("linhaBO");
        	
	    	SystemUser usuario = (Usuario) UserHelper.getBoundedUser(request.getSession());
	    	Lote       lote    = (Lote) loteBusiness.get( new Integer(entityId) );
	    	ControllerHelper.prepare( lote, (Long)usuario.getIdentifier() );

	    	BeanUtils.copyProperties(loteForm, lote);
        	
        	// Setando os dados iniciais do form
            loteForm.set("dataLote", 				DateUtils.applyMask(lote.getDataLote(), "dd/MM/yyyy") );    
            loteForm.set("descriptionLoteTypeList", tipoLoteBusiness.list() );
            loteForm.set("descriptionLineList", 	linhaBusiness.list() );
            loteForm.set("idLinha", 	            lote.getLinha().getEntityId() );
            loteForm.set("idTipoLote", 	            lote.getTipoLote().getEntityId() );
            loteForm.set("locked",                  "s");
	    	
            return mapping.findForward("_form");
	        
    	} catch (BusinessException bExp) {
    		
            throw new ControllerException(bExp);
            
        } catch (IllegalAccessException illegAccExp) {
        	
        	throw new ControllerException(illegAccExp);
        	
		} catch (InvocationTargetException invTarExp) {
			
			throw new ControllerException(invTarExp);
			
		} catch ( InvalidSessionException isExp ) {
        	
        	throw new ControllerException(isExp);
        	
        } 
    	
    }    
    
}
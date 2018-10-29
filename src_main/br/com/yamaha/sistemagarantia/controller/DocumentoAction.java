/* Resource Tecnologia
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......DocumentoAction.java
 *   .: Criação.....28 de Agosto, 10:35
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Controla requisições sobre File - Arquivos e uploads/downloads.
 */
package br.com.yamaha.sistemagarantia.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
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
import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.DynaValidatorActionForm;

import br.com.resource.infra.controller.InfraDispatchAction;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.ControllerException;
import br.com.resource.infra.security.exception.InvalidSessionException;
import br.com.resource.infra.security.helper.UserHelper;
import br.com.resource.infra.utils.DateUtils;
import br.com.yamaha.sistemagarantia.business.DocumentoBusiness;
import br.com.yamaha.sistemagarantia.business.UsuarioBusiness;
import br.com.yamaha.sistemagarantia.controller.helper.ControllerHelper;
import br.com.yamaha.sistemagarantia.controller.helper.FileHelper;
import br.com.yamaha.sistemagarantia.model.AlertGarantia;
import br.com.yamaha.sistemagarantia.model.Documento;
import br.com.yamaha.sistemagarantia.model.ParametroSistema;
import br.com.yamaha.sistemagarantia.model.Usuario;

/** Classe controla as tarefas relacionadas ao <b>File</b>.
 * 
 *  @author Edson Luiz Sumensari
 */
public class DocumentoAction extends InfraDispatchAction {

   
	private static final long serialVersionUID = 1L;
    
	
	/** Tarefa: Loga o usuário.
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
    public ActionForward logon( ActionMapping mapping
                             , ActionForm form
                             , HttpServletRequest request
                             , HttpServletResponse response) throws ControllerException {
    	
    	Long    usuario_id = request.getParameter("usuario_id") != null ? (new Long(request.getParameter("usuario_id"))):null;
    	    	
    	try {
    		
    		UsuarioBusiness  usuarioBusiness  = (UsuarioBusiness) springContext.getBean("usuarioBO");
    		UserHelper.unboundUserToSession(request.getSession());
                        
            if ( usuario_id == null ) {
        		
        		throw new BusinessException("O usuário não foi informado!");
        		
        	} else {
        		
        		Usuario usuario = usuarioBusiness.get(usuario_id);
        		
        		if ( usuario == null )
        			throw new BusinessException("O usuário é inválido!");
        		else
        			if ( !usuario.getNivelAcesso().getEntityId().equals(new Long(3)) && !usuario.getNivelAcesso().getEntityId().equals(new Long(4)) )
        				throw new BusinessException("O usuário não tem permissão!");
        			else
        				UserHelper.boundUserToSession(request.getSession(), usuario);
        		
        	}
    		
    	} catch (BusinessException bExp) {
            
            throw new ControllerException("Erro na autenticação do usuário - Exception:" + bExp);
            
        }
    	
    	return this.list(mapping, form, request, response);
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
        	
        	DocumentoBusiness documentoBusiness = (DocumentoBusiness) springContext.getBean("documentoBO");
        	UserHelper.getBoundedUser(request.getSession());
        	
        	// Parâmetro que define se devem ser apresentados apenas os itens expirados
        	boolean exp 	   = request.getParameter("exp") != null ? (new Boolean(request.getParameter("exp"))).booleanValue():false;
        	       
        	List list = null;
        	
        	if ( !exp ) {       	
        		list = documentoBusiness.list();        		
        	} else {
        		list = documentoBusiness.listExpired();
        	}
        	        	
            DynaActionForm documentoForm = (DynaValidatorActionForm) form;
            documentoForm.initialize( mapping );
            
            if ( list != null )
            	documentoForm.set( "listResults" , list );
            else
            	documentoForm.set( "listResults" , new ArrayList() );
            
            documentoForm.set( "entityId" , null); 
            documentoForm.set( "startDate", DateUtils.applyMask(new Date()));
            
            request.setAttribute( "sysDate" , DateUtils.applyMask(new Date()));
            request.setAttribute( "isEdit"  , new Boolean(false));
            request.setAttribute( "exp"     , new Boolean(exp));
            
            return mapping.findForward("_list");

        } catch (BusinessException bExp) {
            
            throw new ControllerException(bExp);
            
        } catch ( InvalidSessionException isExp ) {
    		
    		throw new ControllerException(isExp);
    		
		}  
        
    } 
    
    /** Tarefa: Remover entidade.
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
    public ActionForward remove( ActionMapping mapping
                               , ActionForm form
                               , HttpServletRequest request
                               , HttpServletResponse response) throws ControllerException {
        
    	
    	DynaActionForm actionForm = (DynaValidatorActionForm) form;        
        ActionMessages 			messagesSuccess    = new ActionMessages();
    	ActionMessages 			messagesProblem    = new ActionMessages();
    	ActionMessages 			messagesWarning    = new ActionMessages();
        
        try {
	        // Primeiro, pegamos nossos alvos e quebramos em
	        // um array de Strings.
	        String targets[] = actionForm.getStrings("deleteTargets");
	        
	        Documento documento = null;
	    	List list = new ArrayList();
	        DocumentoBusiness documentoBusiness = (DocumentoBusiness) springContext.getBean("documentoBO");
	        Usuario usuario = (Usuario)UserHelper.getBoundedUser(request.getSession());
	        //log.info("Itens selecionados:" + targets.length);
	        
	        for ( int i=0; i<targets.length; i++ ) {   
	        	
	        	//log.info("Valor recuperado do id:" + targets[i]);
	        	
        		documento = documentoBusiness.get(new Long(targets[i]));
        		
        		//log.info("File null? " + (file == null) );
        		
        		if ( documento != null ) {
        			
        			list.add(documento);
        			
        		}
        		
	        }
	        
	        if ( !list.isEmpty() ) {
	        	
	        	List alertas = documentoBusiness.removeList(list, usuario);
	        	
	        	Iterator itL = alertas.iterator();
	        	
	        	while ( itL.hasNext() ) {
	        		
	        		AlertGarantia alerta = (AlertGarantia) itL.next();
	        		if ( alerta.getAlertGarantiaKey().indexOf("error") != -1 ) {
						
			        	messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage(alerta.getAlertGarantiaKey(), alerta.getParam()) );
						super.addProblemMessages(request, messagesProblem);		
						
					} else if ( alerta.getAlertGarantiaKey().indexOf("success") != -1 ) {
						
						messagesSuccess.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage(alerta.getAlertGarantiaKey(), alerta.getParam()) );
		            	super.addSuccessMessages(request, messagesSuccess);
					}	        		
	        		
	        	}
	        	
	        } else {
	        	
	        	messagesWarning.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("estoque.msg.warning.notFound") );
	            super.addWarningMessages(request, messagesWarning);
	        	
	        }
	        
        } catch (BusinessException bExp) {
            
            throw new ControllerException(bExp);
    		
		} catch (InvalidSessionException e) {
			throw new ControllerException(e);
		}  
        
        return this.list(mapping, form, request, response);
        
    }
    
    /** Tarefa: Editar Documento.
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
    public ActionForward edit( ActionMapping mapping
                               , ActionForm form
                               , HttpServletRequest request
                               , HttpServletResponse response) throws ControllerException {
        
    	
    	DynaActionForm documentoForm = (DynaValidatorActionForm) form;
    	ActionMessages 			messagesProblem    = new ActionMessages();
        
        try {	
	        
	        Long  entityId = request.getParameter("entityId") != null ? new Long(request.getParameter("entityId")) : null;
	        	    	    	
	        DocumentoBusiness documentoBusiness = (DocumentoBusiness) springContext.getBean("documentoBO");
	        
	        Documento documento = documentoBusiness.get(entityId);
	        
	        if ( documento != null ) {
	        
		        documentoForm.set("entityId"   , documento.getEntityId());
		        documentoForm.set("description", documento.getDescription());
		        documentoForm.set("startDate"  , DateUtils.applyMask(documento.getDataInicio()));
		        documentoForm.set("endDate"    , DateUtils.applyMask(documento.getDataTermino()));
		        
		        request.setAttribute( "sysDate", DateUtils.applyMask(documento.getDataCriacao()));
		        request.setAttribute( "isEdit" , new Boolean(true));
		        	        
		        return mapping.findForward("_edit");
		        
	        } else  {
	        	
	        	messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("documento.msg.error.fileNotFount") );
				super.addProblemMessages(request, messagesProblem);
				
				return this.list(mapping, form, request, response);
	        		        	
	        }
	        
        } catch (BusinessException bExp) {
            
            throw new ControllerException(bExp);
            
        } 
        
    }
    
    /** Tarefa: Salvar as alterações no registro do Documento.
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
    public ActionForward saveEdit( ActionMapping mapping
                               , ActionForm form
                               , HttpServletRequest request
                               , HttpServletResponse response) throws ControllerException {
        
    	
    	DynaActionForm documentoForm 	  = (DynaValidatorActionForm) form;
    	ActionMessages messagesProblem    = new ActionMessages();
    	ActionMessages messagesSuccess    = new ActionMessages();
        
        try { 
	        
	        Long  entityId = documentoForm.get("entityId") != null ? (Long)documentoForm.get("entityId") : null;
	        		    	
	        DocumentoBusiness documentoBusiness = (DocumentoBusiness) springContext.getBean("documentoBO");
	        Usuario usuario = (Usuario)UserHelper.getBoundedUser(request.getSession());
	        
	        Documento documento = documentoBusiness.get(entityId);
	        
	        if ( documento != null ) {
	        	
	        	documento.setDescription(documentoForm.getString("description"));
	        	documento.setDataInicio(DateUtils.secureSet(documentoForm.getString("startDate")));
	        	documento.setDataTermino(DateUtils.secureSet(documentoForm.getString("endDate")));
	        	
	        	documentoBusiness.update(documento, usuario);
	        	
	        	messagesSuccess.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("documento.msg.success.update") );
            	super.addSuccessMessages(request, messagesSuccess);
		        
	        } else  {
	        	
	        	messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("documento.msg.error.fileNotFount") );
				super.addProblemMessages(request, messagesProblem);
	        		        	
	        }
	        
	        return mapping.findForward("_edit");
	        
        } catch (BusinessException bExp) {
            
            throw new ControllerException(bExp);
            
        } catch (InvalidSessionException e) {
			throw new ControllerException(e);
		} 
    }
    
    /** Tarefa: Grava as informações enviadas do formulário de Doumentos.
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
    	
    	DynaValidatorActionForm documentoForm = (DynaValidatorActionForm) form;
    	ActionMessages 			messagesSuccess    = new ActionMessages();
    	ActionMessages 			messagesProblem    = new ActionMessages();
    	
    	Long entityId = documentoForm.get("entityId").equals(new Long(0)) ? null:(Long)documentoForm.get("entityId");
    	
    	try {
    		
    		FormFile myFile = (FormFile) documentoForm.get("fileToUpload");
    		
    		DocumentoBusiness 	documentoBusiness 	= (DocumentoBusiness) springContext.getBean("documentoBO");
    		Usuario usuario = (Usuario)UserHelper.getBoundedUser(request.getSession());
    		ParametroSistema param = documentoBusiness.getByParameterSystem(ParametroSistema.FILE_MAX_SIZE_UPLOAD);
    		
    		if ( param != null ) {
    		
    			if ( Documento.isValidFile( myFile, Integer.parseInt(param.getValorParametro())) ) {
	    	
    				Documento documento = new Documento();
    				documento.setEntityId(entityId);
    				documento.setFormFile(myFile);
    				documento.setFilename(myFile.getFileName());
    				documento.setContentType(myFile.getContentType());
    				documento.setSize(new Integer(myFile.getFileSize()));
    				documento.setDescription(documentoForm.getString("description"));
    				documento.setUrl(FileHelper.createAbsolutePatch(documento.getFilename()));    				
            	
    				ControllerHelper.prepare( documento, (Long)usuario.getEntityId() );
    				
    				documento.setDataInicio(DateUtils.secureSet(documentoForm.get("startDate")));
    				
    				Date endDate = DateUtils.secureSet(documentoForm.get("endDate"));
    				
    				if ( endDate != null )
    					documento.setDataTermino(endDate);    					
    			
    				AlertGarantia alert  = documentoBusiness.save(documento,usuario);
    				
    				if ( alert.getAlertGarantiaKey().indexOf("error") != -1 ) {
    				
    					messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( alert.getAlertGarantiaKey(), alert.getParam()) );
    					super.addProblemMessages(request, messagesProblem);
    				
    				} else if ( alert.getAlertGarantiaKey().indexOf("success") != -1 ) {
    			
    					messagesSuccess.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage(alert.getAlertGarantiaKey()) );
    					super.addSuccessMessages(request, messagesSuccess);
    				}
    				
    			} else {
    				
    				 messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("documento.msg.error.invalidFile") );
    		         super.addProblemMessages(request, messagesProblem);    				
    				
    			}
    			
    		} else {
    			
	            messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("documento.msg.error.parameterInvalid") );
	            super.addProblemMessages(request, messagesProblem);
    			
    		}	 
            	
	    	return this.list(mapping, form, request, response);
         
    	} catch (BusinessException bExp) {
                
            throw new ControllerException(bExp);
                
    	} catch (InvalidSessionException e) {
    		 throw new ControllerException(e);
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
    public ActionForward listDownload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception {
    	
    	DynaValidatorActionForm documentoForm = (DynaValidatorActionForm) form;
    	
    	try {     		
    		
    		DocumentoBusiness documentoBusiness = (DocumentoBusiness) springContext.getBean("documentoBO");
        	UserHelper.getBoundedUser(request.getSession());
    		
    		List list = documentoBusiness.list();
    		
    		if ( list != null )
            	documentoForm.set( "listResults" , list );
            else
            	documentoForm.set( "listResults" , new ArrayList() );
    		
    		    		
    	} catch (BusinessException bExp) {
            
            throw new ControllerException(bExp);
                
    	} catch ( InvalidSessionException isExp ) {
        	
        	throw new ControllerException(isExp);
        	
        }     	
    	
    	return mapping.findForward("_download");
        	
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
    	
    	ActionMessages 			messagesProblem    = new ActionMessages();
    	
    	Long entityId = request.getParameter("entityId") != null ? Long.valueOf(request.getParameter("entityId")):null;
    	
    	String target = request.getParameter("target") != null ? request.getParameter("target"):null;
    	
    	try {
    		
    		DocumentoBusiness 	documentoBusiness 	= (DocumentoBusiness) springContext.getBean("documentoBO");
    		Usuario usuario = (Usuario)UserHelper.getBoundedUser(request.getSession());
    		
    		Documento documento = documentoBusiness.get(entityId);
	        
	        if ( documento != null ) {
    		
	        	File file = new File( FileHelper.getUploadFolder() + documento.getFilename() );
	        		        	
	        	if ( file.exists() ) { 
	        		
	        		ServletOutputStream ouputStream = response.getOutputStream(); 
	        		
		        	response.setContentType(documento.getContentType());
		        	response.setHeader("Content-Disposition", "attachment; filename=" + documento.getFilename() );
	    	
		        	byte[] bytes = FileHelper.getBytesFromFile(file);
		        	response.setContentLength( bytes.length); 
	    	
		        	ouputStream.write( bytes, 0,  bytes.length);
		        	
		        	ouputStream.flush();
		    		ouputStream.close();
		    		
		    		documentoBusiness.saveLog(documento, usuario);
		        	
	        	} else {
	        		messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("documento.msg.error.fileNotFount") );
		            super.addProblemMessages(request, messagesProblem);
	        		
	        	}
    		
	        } else {
	        	
	        	messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("documento.msg.error.fileNotFount") );
	            super.addProblemMessages(request, messagesProblem);
	            
	        	
	        }
    		
    	} catch (FileNotFoundException fex){
    	
    		messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("documento.msg.error.fileNotFount") );
            super.addProblemMessages(request, messagesProblem);
                		
    	}
    	 
    	if ( target != null )
    		if ( "view".equals(target) )
    			return this.list(mapping, form, request, response);
    
    	return this.listDownload(mapping, form, request, response);
        
    }  
        
}
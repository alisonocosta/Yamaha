/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......HomeAction.java
 *   .: Criação.....02 de abril, 12:26
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Classe responsável por tratar tarefas da home do sistema.
 */
package br.com.yamaha.sistemagarantia.controller;

import java.security.NoSuchAlgorithmException;

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
import br.com.resource.infra.exception.ControllerException;
import br.com.resource.infra.security.DigestGenerator;
import br.com.resource.infra.security.helper.UserHelper;
import br.com.yamaha.sistemagarantia.business.UsuarioBusiness;
import br.com.yamaha.sistemagarantia.controller.helper.YmSessionHelper;
import br.com.yamaha.sistemagarantia.model.Concessionaria;
import br.com.yamaha.sistemagarantia.model.ParametroSistema;
import br.com.yamaha.sistemagarantia.model.Usuario;

/** Action responsável por exibir a página principal do sistema.
 * 
 *  @author Thiago Uriel M. Garcia
 *  		Edson Luiz Sumensari
 */
public class HomeAction extends InfraDispatchAction {

    private static final long serialVersionUID = 1L;

    /** Exibe a tela inicial do sistema.
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
    public ActionForward show( ActionMapping mapping
                             	, ActionForm form
                             	, HttpServletRequest request
                             	, HttpServletResponse response) throws ControllerException {
        
    	DynaValidatorActionForm logonForm 		= (DynaValidatorActionForm) form;
    	ActionMessages 			messagesProblem = new ActionMessages();
    	
        String paramCode  = request.getParameter( "userCode" );
        String hashCode   = request.getParameter( "hKey" );
        
        UserHelper.unboundUserToSession(request.getSession());
        request.setAttribute("isNewPass", new Boolean(false));
		request.setAttribute("message"  , "Informe o nome de usuário e senha");
        
        if ( (paramCode != null && !"".equals(paramCode.trim())) && (hashCode != null && !"".equals(hashCode.trim()))  ) {
            
            try {                
            	
            	UsuarioBusiness  usuarioBusiness  = (UsuarioBusiness) springContext.getBean("usuarioBO");
                Usuario 		 usuario 		  = usuarioBusiness.getUserByHash(paramCode.toUpperCase(), hashCode);
        		
                if ( usuario != null ) {
                	
                	ParametroSistema parametroDisp  = usuarioBusiness.getSystemParameter( ParametroSistema.DISPONIBILIDADE );
                	YmSessionHelper.setInnerUserToSession(request, usuario);
                	
                	String hashDefault = (DigestGenerator.getDigest("GARANTIA", DigestGenerator.ALGORITHM_MD5)).toUpperCase();
                	
                	if ( !hashDefault.equals(usuario.getPassword().toUpperCase()) ) {
                		
                		logonForm.set("username", paramCode);
                		
	                	if ( Usuario.NIVEL_ACESSO_CONCESSIONARIA.equalsIgnoreCase(usuario.getNivelAcesso().getCodigo()) ){
	                		
	                		if ( ParametroSistema.DISPONIBILIDADE_YES.equalsIgnoreCase( parametroDisp.getValorParametro() )
	                			 && Concessionaria.FLAG_SISTEMA_YES.equals(usuario.getConcessionaria().getFlagSistema())
	                			) {
	                			
	                			log.info("USuário:" + usuario.getUsuarioCode() + " - ID da Sessão:" + request.getSession().getId());
	                			UserHelper.boundUserToSession(request.getSession(),usuario);
	    	            		
	    	            		return mapping.findForward( "_index" );
	    	            		
	                		} else {
	                    		
	                			messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("user.code.sysOFF") );
	                            super.addProblemMessages(request, messagesProblem);   
	                            
	                            return mapping.findForward( "_logon" );
	                            
	                    	}
	                		
	                	} else {
	                		
	                		if ( !ParametroSistema.DISPONIBILIDADE_NO.equalsIgnoreCase( parametroDisp.getValorParametro() ) ) {
	                			/*
	    	            		ParametroSistema parametroSistema = usuarioBusiness.getSystemParameter( ParametroSistema.HOME_PAGE_INTERNO );
	    	            		
	    	            		YmSessionHelper.setParametroSistemaToSession(request, parametroSistema);
	    	            		            		
	    	            		return mapping.findForward( "_inner" );
	    	            		*/
	                			return this.backInner(mapping, form, request, response);
	    	            		
	    	            	} else {
	    	            		
	    	            		messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("user.code.sysOFF") );
	    	                    super.addProblemMessages(request, messagesProblem);   
	    	                    
	    	                    return mapping.findForward( "_logon" );
	    	                    
	    	            	}
	                	}   
	                	
                	} else {
                		
                		request.setAttribute("isNewPass", new Boolean(true));
                		request.setAttribute("message"  , "Para realizar o primeiro acesso altere a senha!");
                		logonForm.set("username", paramCode);
                		return mapping.findForward( "_logon" );
                		
                	}
                	
                } else {
                	
                	messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("user.code.notFound") );
    	            super.addProblemMessages(request, messagesProblem);  
    	            
    	            return mapping.findForward( "_logon" );
    	            
                }
                
        	
        	} catch (BusinessException bExp) {
                
                throw new ControllerException("Erro na autenticação do usuário - Exception:" + bExp);
                
            } catch (NoSuchAlgorithmException nsaExp) {  
            	
            	 throw new ControllerException("Erro na autenticação do usuário - Exception:" + nsaExp );
            	 
            }
            
        } else {
        	
        	messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("user.code.notexist") );
            super.addProblemMessages(request, messagesProblem); 
        	
        	return mapping.findForward( "_logon" );
            
        }
        
    }
    
    /** Exibe a tela inicial do sistema.
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
    public ActionForward alterPassword( ActionMapping mapping
                             			, ActionForm form
                             			, HttpServletRequest request
                             			, HttpServletResponse response) throws ControllerException {
        
    	DynaValidatorActionForm logonForm 		= (DynaValidatorActionForm) form;
    	ActionMessages 			messagesProblem = new ActionMessages();
    
    	String username = logonForm.getString("username");
        String password = logonForm.getString("newPassword");
        
        if ( (username != null && !username.trim().equals("")) && (password != null && !password.trim().equals("")) ) {
            
            try {
                
            	UserHelper.unboundUserToSession(request.getSession());
            	
            	String hashDefault = (DigestGenerator.getDigest("GARANTIA", DigestGenerator.ALGORITHM_MD5)).toUpperCase();
            	String hashNew     = (DigestGenerator.getDigest(password  , DigestGenerator.ALGORITHM_MD5)).toUpperCase();
            	
                UsuarioBusiness  usuarioBusiness  = (UsuarioBusiness) springContext.getBean("usuarioBO");
                Usuario 		 usuario 		  = usuarioBusiness.getUserByHash(username.toUpperCase(), hashDefault);
                
                if ( usuario != null ) {
                	
                	usuario.setPassword(hashNew);
                	
                	usuarioBusiness.save(usuario);
                	
                	ParametroSistema parametroDisp  = usuarioBusiness.getSystemParameter( ParametroSistema.DISPONIBILIDADE );
                	YmSessionHelper.setInnerUserToSession(request, usuario);
                	
                	if ( Usuario.NIVEL_ACESSO_CONCESSIONARIA.equalsIgnoreCase(usuario.getNivelAcesso().getCodigo()) ){
                		
                		if ( ParametroSistema.DISPONIBILIDADE_YES.equalsIgnoreCase( parametroDisp.getValorParametro() ) 
                				&& Concessionaria.FLAG_SISTEMA_YES.equals(usuario.getConcessionaria().getFlagSistema())
            				) {
    	            		
                			UserHelper.boundUserToSession(request.getSession(),usuario);
    	            		
    	            		return mapping.findForward( "_index" );
    	            		
                		} else {
                    		
                			messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("user.code.sysOFF") );
                            super.addProblemMessages(request, messagesProblem);   
                            
                            return mapping.findForward( "_logon" );
                            
                    	}
                		
                	} else {
                		
                		if ( !ParametroSistema.DISPONIBILIDADE_NO.equalsIgnoreCase( parametroDisp.getValorParametro() ) ) {
                			/*
    	            		ParametroSistema parametroSistema = usuarioBusiness.getSystemParameter( ParametroSistema.HOME_PAGE_INTERNO );
    	            		
    	            		YmSessionHelper.setParametroSistemaToSession(request, parametroSistema);
    	            		            		
    	            		return mapping.findForward( "_inner" );
    	            		*/
                			return this.backInner(mapping, form, request, response);
    	            	} else {
    	            		
    	            		messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("user.code.sysOFF") );
    	                    super.addProblemMessages(request, messagesProblem);   
    	                    
    	                    return mapping.findForward( "_logon" );
    	                    
    	            	}
                	}   
                	
                } else {
                	
                	messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("user.code.notFound") );
    	            super.addProblemMessages(request, messagesProblem);  
    	            
    	            return mapping.findForward( "_logon" );                	
                	
                }                
        
            } catch (BusinessException bExp) {
                
                throw new ControllerException("Erro na autenticação do usuário - Exception:" + bExp);
                
            } catch (NoSuchAlgorithmException nsaExp) {  
            	
            	 throw new ControllerException("Erro na autenticação do usuário - Exception:" + nsaExp );            	 
            }
            
        } else {
        	
        	messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("user.code.notexist") );
            super.addProblemMessages(request, messagesProblem); 
        	
        	return mapping.findForward( "_logon" );
        	
        }
        
    }
                             
    /** Exibe a tela inicial do sistema para solicitções encaminhadas pelo sistema interno.
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
    public ActionForward showInner( ActionMapping mapping
                             , ActionForm form
                             , HttpServletRequest request
                             , HttpServletResponse response) throws ControllerException {
        
    	ActionMessages 			messagesProblem    = new ActionMessages();
    	
        String paramCode  = request.getParameter( "userCode" );
        String innerCode  = request.getParameter( "userInterno" );        

		request.setAttribute("isNewPass", new Boolean(false));
		request.setAttribute("message"  , "Informe o nome de usuário e senha");
		
		//log.info("showInner ---> Parâmetro paramCode:" + paramCode + " - innerCode:" + innerCode);
		
        if ( paramCode != null && !paramCode.trim().equals("") ) {
            
            try {
                
            	UserHelper.unboundUserToSession(request.getSession());
            	
                UsuarioBusiness  usuarioBusiness  = (UsuarioBusiness) springContext.getBean("usuarioBO");
                Usuario          user             = usuarioBusiness.getByCode( paramCode.toUpperCase() );              
                Usuario			 innerUser		  = null;
                
                if ( innerCode != null && !innerCode.trim().equals("") ) {
                	
                	innerUser = usuarioBusiness.getByCode( innerCode );
                	
                } 
                
                ParametroSistema parametroSistema = usuarioBusiness.getSystemParameter( ParametroSistema.HOME_PAGE_INTERNO );
                ParametroSistema parametroDisp    = usuarioBusiness.getSystemParameter( ParametroSistema.DISPONIBILIDADE );
                
                //log.info("showInner -- Usuario:" + (user != null) + " -- Sessão:" + request.getSession().getId() );
                
                UserHelper.boundUserToSession(request.getSession(),user);
                YmSessionHelper.setParametroSistemaToSession(request, parametroSistema);
                
                log.info("USuário:" + user.getUsuarioCode() + " - ID da Sessão:" + request.getSession().getId());
                
                if ( user != null ) {
	                // Quando existir o parâmetro de usuário interno, colocamos ele na sessão
	                if ( innerUser != null ) {
	                	
	                	if ( !ParametroSistema.DISPONIBILIDADE_NO.equalsIgnoreCase( parametroDisp.getValorParametro() ) )  {              	
	                		YmSessionHelper.setInnerUserToSession(request, innerUser);                	
	                	} else {
	                		messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("user.code.sysOFF") );
	                        super.addProblemMessages(request, messagesProblem);   
	                        
	                        return mapping.findForward( "_logon" );
	                	}
	                	
	                } else
	                	
	                	if ( ParametroSistema.DISPONIBILIDADE_YES.equalsIgnoreCase( parametroDisp.getValorParametro() ) ) {
	                		YmSessionHelper.setInnerUserToSession(request, user);
	                	} else {                		
	                		messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("user.code.sysOFF") );
	                        super.addProblemMessages(request, messagesProblem); 
	                        
	                        return mapping.findForward( "_logon" );
	                	}
	                
                } else {
                	messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("user.code.notFound") );
                    super.addProblemMessages(request, messagesProblem); 
                    
                    return mapping.findForward( "_logon" );                	
                }
                
            } catch (BusinessException bExp) {
                
                throw new ControllerException("Usuário inválido!");
                
            }           
            
        } else {
        	
        	messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("user.code.notexist") );
            super.addProblemMessages(request, messagesProblem); 
        	
        	return mapping.findForward( "_logon" );
            
        }
        
        //log.info("Vamos para a Home!");
        return mapping.findForward( "_index" );
        
    }
    
    
    /** Exibe a tela de logon de logon do Sistema.
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
    public ActionForward showLogon( ActionMapping mapping
                             , ActionForm form
                             , HttpServletRequest request
                             , HttpServletResponse response) throws ControllerException {
    	
    	request.setAttribute("isNewPass", new Boolean(false));
		request.setAttribute("message"  , "Informe o nome de usuário e senha");
    	
    	return mapping.findForward( "_logon" );
    	
    }
    
    /** Exibe a tela inicial do sistema após o logon do usuário.
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
    	
    	DynaValidatorActionForm logonForm = (DynaValidatorActionForm) form;
    	
    	ActionMessages 			messagesProblem    = new ActionMessages();
    	
    	request.setAttribute("isNewPass", new Boolean(false));
		request.setAttribute("message"  , "Informe o nome de usuário e senha");
    	
    	try {
    		
    		UsuarioBusiness  usuarioBusiness  = (UsuarioBusiness) springContext.getBean("usuarioBO");
    		String username = logonForm.getString("username");
            String password = logonForm.getString("password");
            
            //log.info("Usuário:" + username);
            //log.info("Senha:" + password);
            
            UserHelper.unboundUserToSession(request.getSession());
            
            Usuario usuario = usuarioBusiness.getUser(username.toUpperCase(), password);
            
            if ( usuario != null ) {
            	
            	ParametroSistema parametroDisp  = usuarioBusiness.getSystemParameter( ParametroSistema.DISPONIBILIDADE );
            	YmSessionHelper.setInnerUserToSession(request, usuario);
            	
            	log.info("USuário:" + usuario.getUsuarioCode() + " - ID da Sessão:" + request.getSession().getId());
            	
            	String hashDefault = (DigestGenerator.getDigest("GARANTIA", DigestGenerator.ALGORITHM_MD5)).toUpperCase();
            	
            	if ( !hashDefault.equals(usuario.getPassword().toUpperCase()) ) {
            	
	            	if ( Usuario.NIVEL_ACESSO_CONCESSIONARIA.equalsIgnoreCase(usuario.getNivelAcesso().getCodigo()) ){
	            		
	            		if ( ParametroSistema.DISPONIBILIDADE_YES.equalsIgnoreCase( parametroDisp.getValorParametro() ) 
	            				&& Concessionaria.FLAG_SISTEMA_YES.equals(usuario.getConcessionaria().getFlagSistema())
            				) {
		            		
	            			UserHelper.boundUserToSession(request.getSession(),usuario);
		            		
		            		return mapping.findForward( "_index" );
		            		
	            		} else {
	                		
	            			messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("user.code.sysOFF") );
	                        super.addProblemMessages(request, messagesProblem);   
	                        
	                        return mapping.findForward( "_logon" );
	                        
	                	}
	            		
	            	} else {
	            		
	            		if ( !ParametroSistema.DISPONIBILIDADE_NO.equalsIgnoreCase( parametroDisp.getValorParametro() ) ) {
	            			/*
		            		ParametroSistema parametroSistema = usuarioBusiness.getSystemParameter( ParametroSistema.HOME_PAGE_INTERNO );
		            		
		            		YmSessionHelper.setParametroSistemaToSession(request, parametroSistema);
		            		            		
		            		return mapping.findForward( "_inner" );
		            		*/
	            			return this.backInner(mapping, form, request, response);
		            	} else {
		            		
		            		messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("user.code.sysOFF") );
		                    super.addProblemMessages(request, messagesProblem);   
		                    
		                    return mapping.findForward( "_logon" );
		                    
		            	}
	            	}  
	            	
            	} else {
            		
            		request.setAttribute("isNewPass", new Boolean(true));
            		request.setAttribute("message"  , "Para realizar o primeiro acesso altere a senha!");
            		logonForm.set("username", username);
            		return mapping.findForward( "_logon" );
            		
            	}
            	
            } else {
            	
            	messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("user.code.notFound") );
	            super.addProblemMessages(request, messagesProblem);  
	            
	            return mapping.findForward( "_logon" );
	            
            }
            
    	
    	} catch (BusinessException bExp) {
            
            throw new ControllerException("Erro na autenticação do usuário - Exception:" + bExp);
            
        } catch (NoSuchAlgorithmException nsaExp) {  
        	
       	 throw new ControllerException("Erro na autenticação do usuário - Exception:" + nsaExp );            	 
       }
    	
    }
    
    
    /** Encerra a sessão do usuário e direciona para a página de logon.
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
    public ActionForward close( ActionMapping mapping
                             , ActionForm form
                             , HttpServletRequest request
                             , HttpServletResponse response) throws ControllerException {
    	
    	YmSessionHelper.removeAllToSession(request);
    	UserHelper.unboundUserToSession(request.getSession());    	
    	
    	request.setAttribute("isNewPass", new Boolean(false));
		request.setAttribute("message"  , "Informe o nome de usuário e senha");
    	
    	return mapping.findForward( "_logon" );
    	
    }
    
    /** Encerra a sessão do usuário e direciona para a página interna do sistema, apenas quando 
     *  existir um usuário interno na sessão.
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
    public ActionForward backInner(  ActionMapping mapping
		                             , ActionForm form
		                             , HttpServletRequest request
		                             , HttpServletResponse response) throws ControllerException {
    	
    	try {
    		
    		UsuarioBusiness  usuarioBusiness  = (UsuarioBusiness) springContext.getBean("usuarioBO");    		
    		ParametroSistema parametroSistema = usuarioBusiness.getSystemParameter( ParametroSistema.HOME_PAGE_INTERNO );
    		
    		Usuario user = (Usuario)YmSessionHelper.getInnerUserFromSession(request);
    		
    		String innerCode = new String();
    		String sessionId = new String();
    		if( user != null ) {
    			
    			innerCode = user.getUsuarioCode();    			
    			sessionId = request.getSession().getId();    			
    			usuarioBusiness.setSessionUserId(sessionId, innerCode);
    			
    		} else {
    			throw new ControllerException("Erro na autenticação do usuário!");
    		}
    		
    		//log.info("Usuário interno:"   + innerCode);
    		//log.info("Parâmetro Sistema:" + parametroSistema.getValorParametro());
    		
    		StringBuffer strAcesso = new StringBuffer();
    		strAcesso.append(parametroSistema.getValorParametro());
    		strAcesso.append(innerCode);
    		strAcesso.append("&p_sessionId=");
    		strAcesso.append(sessionId);
    		
    		//log.info("URL Para Direcionamento:" + strAcesso.toString());
    		
    		request.setAttribute("innerURL" ,strAcesso.toString());
    		
    		//request.setAttribute("innerCode", innerCode);
    		//request.setAttribute("innerURL" , parametroSistema.getValorParametro());
    		//request.setAttribute("sessionId" , sessionId);
    		
    		YmSessionHelper.removeAllToSession(request);
    		UserHelper.unboundUserToSession(request.getSession());
    		
    		return mapping.findForward( "_inner" );
    		
	     } catch (BusinessException bExp) {
	         
	         throw new ControllerException("Erro na autenticação do usuário - Exception:" + bExp);
	         
	     }
    	
    }
    
}
/* Yamaha 
 * Copyright (c) 2015 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......AuthenticationAjaxAction.java
 *   .: Cria��o.....15 de janeiro, 21:40
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Action de servi�o para autenticar o usu�rio logado para as p�ginas internas em PLSQL.
 */
package br.com.yamaha.sistemagarantia.controller.ajax;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import br.com.resource.infra.controller.InfraAjaxDefaultAction;
import br.com.resource.infra.exception.ControllerException;
import br.com.resource.infra.security.exception.InvalidSessionException;
import br.com.resource.infra.security.helper.UserHelper;
import br.com.yamaha.sistemagarantia.model.Usuario;

/** Action de servi�o para autenticar o usu�rio logado para as p�ginas internas em PLSQL.
 * 
 */
public class AuthenticationAjaxAction extends InfraAjaxDefaultAction {

    /** Verifica se o usu�rio est� logado.
     *  
     *  @param mapping
     *      O objeto <code>ActionMapping</code> utilizado para
     *      selecionar esta inst�ncia.
     *  @param form
     *      Um objeto (opcional) do tipo <code>ActionForm</code>
     *      utilizado nesta requisi��o.
     *  @param request
     *      A requisi��o <code>HTTP</code> que estamos processando.
     *  @param response
     *      A resposta <code>HTTP</code> que estamos processando.
     * 
     *  @return 
     *      Uma String com o conte�do XML (ou texto) a ser enviado
     *      de volta ao cliente.
     * 
     *  @throws ControllerException
     *      Se houverem erros, estes ser�o lan�ados como uma exception
     *      deste tipo.
     */ 
    public String getXmlContent( ActionMapping mapping
                               , ActionForm form
                               , HttpServletRequest request
                               , HttpServletResponse response) throws ControllerException {
    	try{
    		
    		String authenticUser = "N";
    		if ( request.getParameter("userCode") != null) {
        		
    			String userCode = URLDecoder.decode( request.getParameter("userCode"), "ISO-8859-1" );
    			HttpSession session = request.getSession();
    			System.out.println(" Sess�o Nova? " + session.isNew());
    			
    			if(!session.isNew()){
    				System.out.println(" Id da Sess�o:" + session.getId());

    				Usuario usuario = (Usuario) UserHelper.getBoundedUser(session);
    				if(usuario != null && usuario.getUsuarioCode().equals(userCode)){
    					authenticUser = "S";
    				}   			
    			} 
    		}    		
	    	return authenticUser;
    	
    	} catch ( InvalidSessionException isExp ) {

    		throw new ControllerException(isExp);

    	} catch (UnsupportedEncodingException e) {
    		
    		throw new ControllerException(e);
			
		}        
        
    }
    
}
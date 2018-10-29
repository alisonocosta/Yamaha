/* Yamaha 
 * Copyright (c) 2015 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......AuthenticationAjaxAction.java
 *   .: Criação.....15 de janeiro, 21:40
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Action de serviço para autenticar o usuário logado para as páginas internas em PLSQL.
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

/** Action de serviço para autenticar o usuário logado para as páginas internas em PLSQL.
 * 
 */
public class AuthenticationAjaxAction extends InfraAjaxDefaultAction {

    /** Verifica se o usuário está logado.
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
     *  @return 
     *      Uma String com o conteúdo XML (ou texto) a ser enviado
     *      de volta ao cliente.
     * 
     *  @throws ControllerException
     *      Se houverem erros, estes serão lançados como uma exception
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
    			System.out.println(" Sessão Nova? " + session.isNew());
    			
    			if(!session.isNew()){
    				System.out.println(" Id da Sessão:" + session.getId());

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
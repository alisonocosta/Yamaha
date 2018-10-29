/* IST
 * Copyright (c) 2014 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioRmpsAction.java
 *   .: Criação.....17 de setembro, 21:27
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Acesso ao relatório RMPS  APEX.
 */
package br.com.yamaha.sistemagarantia.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.resource.infra.controller.InfraDefaultAction;
import br.com.resource.infra.exception.ControllerException;
import br.com.resource.infra.security.exception.InvalidSessionException;
import br.com.resource.infra.security.helper.UserHelper;
import br.com.yamaha.sistemagarantia.model.Usuario;

/** Acesso ao relatório RMPS  APEX.
 * 
 *  @author Edson Luiz Sumensari
 */
public class RelatorioRmpsAction extends InfraDefaultAction {
   
	private static final long serialVersionUID = 1L;
	
	public ActionForward doTask(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ControllerException {

		try {
			Usuario user = (Usuario)UserHelper.getBoundedUser(request.getSession());
			Cookie cookie = new Cookie("user_garantia", user.getUsuarioCode());
			cookie.setMaxAge(1000 * 24 * 3600 * 30); //60dias
			response.addCookie(cookie);

		} catch(InvalidSessionException iExc) {
			throw new ControllerException(iExc);
		}

		return mapping.findForward( "_relRmps" );
	}
    
}
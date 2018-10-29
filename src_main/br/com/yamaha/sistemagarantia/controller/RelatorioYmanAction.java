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

/** Acesso ao relatório YMAN.
 * 
 *  @author Edson Luiz Sumensari
 */
public class RelatorioYmanAction extends InfraDefaultAction {
   
	private static final long serialVersionUID = 1L;
	
	public ActionForward doTask(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ControllerException {

		try {			
			Usuario user = (Usuario)UserHelper.getBoundedUser(request.getSession());
			request.setAttribute("flag1"      ,  "3" );
			request.setAttribute("flag2"      ,  "26395" );
			request.setAttribute("flag3"      ,  user.getUsuarioCode() );
			request.setAttribute("flag4"      ,  user.getUsuarioCode() );

		} catch(InvalidSessionException iExc) {
			throw new ControllerException(iExc);
		}

		return mapping.findForward( "_relYman" );
	}
    
}
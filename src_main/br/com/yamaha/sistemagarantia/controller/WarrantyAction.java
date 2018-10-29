/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......GuaranteeAction.java
 *   .: Criação.....07 de novembro, 16:32
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Controla requisições de entrada no sistema.
 */
package br.com.yamaha.sistemagarantia.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.resource.infra.controller.InfraDefaultAction;
import br.com.resource.infra.exception.ControllerException;
import br.com.resource.infra.security.helper.UserHelper;

/** Classe controla as tarefas relacionadas entrada no sistema.
 * 
 *  @author Edson Luiz Sumensari
 */
public class WarrantyAction extends InfraDefaultAction {
   
	private static final long serialVersionUID = 1L;
	
	public ActionForward doTask(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		
		//System.out.println("WarrantyAction --> Sessão:" + request.getSession().getId() );
		
		if ( !UserHelper.userIsBounded(request.getSession()) ) {
			
			//System.out.println("WarrantyAction:Usuario não localizado!");
			request.setAttribute("isNewPass", new Boolean(false));
			request.setAttribute("message"  , "Informe o nome de usuário e senha");
			return mapping.findForward( "_logon" );
			
		}
		
		return mapping.findForward( "_show" );
	}
    
}
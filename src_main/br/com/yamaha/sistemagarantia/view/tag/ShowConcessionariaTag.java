/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......ShowConcessionariaTag.java
 *   .: Cria��o.....24 de abril, 12:17
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Tag para exibir o nome da concession�ria do usu�rio.
 */
package br.com.yamaha.sistemagarantia.view.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import br.com.resource.infra.security.SystemUser;
import br.com.resource.infra.security.exception.InvalidSessionException;
import br.com.resource.infra.security.helper.UserHelper;
import br.com.yamaha.sistemagarantia.model.Concessionaria;
import br.com.yamaha.sistemagarantia.model.Usuario;

/** Esta Tag pega o nome da concession�ria 
 *  do usu�rio da sess�o e envia ao jsp
 *  
 * @author Edson Luiz Sumenari
 *
 */
public class ShowConcessionariaTag extends TagSupport {
	
	 /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;    
	
	public int doStartTag() throws JspException {
		
		HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest();
		
		JspWriter out = this.pageContext.getOut();
		
		try {
		
			SystemUser usuario = (Usuario)UserHelper.getBoundedUser(request.getSession());
			Concessionaria concessionaria = (Concessionaria)usuario.getAttribute("concessionaria");
			out.println(concessionaria.getRazaoSocial());
			
		} catch (InvalidSessionException isExp) {
			
			System.out.println(isExp.getMessage());
			
		} catch (IOException ioExp) {
            
            System.out.println(ioExp);
            
        }
		
		
		return SKIP_BODY;
	}

}

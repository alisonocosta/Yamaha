/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......AlertMessageTag.java
 *   .: Criação.....22 de maio, 8:54
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Tag para apresentação de alert box.
 */
package br.com.resource.infra.view.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/** Tag para apresentação de alert box */
public class AlertMessageTag extends TagSupport {
    
	/** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;  

	/** Construtor */
    public AlertMessageTag() {
    	
        super();
        
    }
    
    /** Recebemos os atributos para o alert e apresentamos o javascript com a mesnagem
     *  ao usuário
     */
    public int doStartTag() throws JspException {
        try {
            String alertMessage = (String)pageContext.getRequest().getAttribute("alertMessage");
            String fieldFocus   = (String)pageContext.getRequest().getAttribute("alertMessage.fieldFocus");
            
            if (alertMessage != null) {
                JspWriter out = pageContext.getOut();
                
	            out.println();
	            out.println("<script>");
	            out.println("    alert('" + alertMessage + "');");
	            
	            if (fieldFocus != null) {
		            out.println();
		            out.println("    " + fieldFocus + ".focus();");
	            }
	            
	            out.println("</script>");
	            out.println();
            }
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        
        return SKIP_BODY;
    }
}

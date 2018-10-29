/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......ConfirmMessageTag.java
 *   .: Cria��o.....
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descri��o...Tag para apresenta��o de confirm box.
 */
package br.com.resource.infra.view.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author Thiago Uriel M. Garcia
 */
public class ConfirmMessageTag extends TagSupport {
    
	/** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;  

	/** Construtor */
    public ConfirmMessageTag() {
        super();
    }
    
    /** Recebemos os atributos do request e apresenta��o a mensagem para consfirma��o do usu�rio*/
    public int doStartTag() throws JspException {
        try {
            String confirmMessage = (String)pageContext.getRequest().getAttribute("confirmMessage");
            String methodIfTrue   = (String)pageContext.getRequest().getAttribute("confirmMessage.methodIfTrue");
            String methodIfFalse  = (String)pageContext.getRequest().getAttribute("confirmMessage.methodIfFalse");
            
            if ((confirmMessage != null) && (methodIfTrue != null) && (methodIfFalse != null)) {
                JspWriter out = pageContext.getOut();
                
	            out.println();
	            out.println("<script>");
	            out.println(" if (window.confirm('" + confirmMessage + "')) { ");
	            out.println("     " + methodIfTrue + "; ");
	            out.println(" } else { ");
	            out.println("     " + methodIfFalse + "; ");
	            out.println(" } ");
	            out.println("</script>");
	            out.println();
            }
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        
        return SKIP_BODY;
    }
}

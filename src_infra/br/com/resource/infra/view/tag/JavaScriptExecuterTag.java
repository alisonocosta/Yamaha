/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......JavaScriptExecuterTag.java
 *   .: Criação.....22 de maio, 8:58
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Tag para execução de um método javascript.
 */
package br.com.resource.infra.view.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/** Tag para execução de um método javascript. */
public class JavaScriptExecuterTag extends TagSupport {
    
	/** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;  

	/** Construtor */
    public JavaScriptExecuterTag() {
        super();
    }
    
    /**Recebemos os atributos do request e executamos o método solicitado */
    public int doStartTag() throws JspException {
        try {
            String method = (String)pageContext.getRequest().getAttribute("javaScriptExecuter.method");
            
            if (method != null) {
                JspWriter out = pageContext.getOut();
                
	            out.println();
	            out.println("<script>");
	            out.println("    " + method + ";");
	            out.println("</script>");
	            out.println();
            }
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        
        return SKIP_BODY;
    }
}

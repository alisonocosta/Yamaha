/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......CheckLogonTag.java
 *   .: Cria��o.....08 de maio, 08:45
 *   .: Autor.......Thiago Uriel M. Garcia
 *   				Edson Luiz Sumensari
 *   .: Descri��o...Objeto helper para a sess�o de usu�rio.
 */
package br.com.resource.infra.view.tag;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import br.com.resource.infra.security.SystemUser;

/** Tag utilizada para determinar se um usu�rio
 *  est� autorizado a ver uma determinada p�gina.
 * 
 *  Esta valida��o � feita determinando se um
 *  objeto <code>SystemUser</code> est� armazenado na
 *  sess�o HTTP, sob a chave especificada como par�metro
 *  da tag, ou armazenada na classe de constantes.
 */
public class CheckLogonTag extends TagSupport {
  
	/** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;  
    
    /** P�gina para onde o usu�rio ser� direcionado.
     *  Tipicamente uma p�gina de login.
     */
    private String page = "sessionExpired.jsp";
    
    /** Nome do perfil a ser verificado. */
    private String roleName = null;
    
   	/** No caso, aqui colocaremos nossa
     *  l�gica, pois � aqui que ela dever� ser
     *  executada.
     * 
     *  @return  Indica��o dizendo que o processamento
     *           da p�gina dever� ser ignorado ou uma dizendo
     *           que dever� contionuar normalmente.
     * 
     *  @throws  Caso haja algum erro na execu��o do JSP, esta
     *           exception ser� lan�ada.
     */
    public int doStartTag() throws JspException {

    	SystemUser sysUser = null;
        
        //System.out.println("USER:" + userExists);
    	// Primeiro devemos checar se um usu�rio est�
        // presente na sess�o.
        // Esta valida��o checa 3 coisas:
        // 1. Se existe uma sess�o aberta;
        // 2. Se existe um objeto dentro da chave de usu�rio;
        // 3. Se este objeto � um usu�rio de sistema (SystemUser);
        // 4. Se este usu�rio tem as permiss�es necess�rias, caso
        //    esta valida��o tenha sido solicitada.
    	Enumeration e = pageContext.getAttributeNamesInScope(PageContext.SESSION_SCOPE);
    	
    	while (e.hasMoreElements()) {
    		
    	    if (e.nextElement().toString().equals("SystemUserCcs")) {
    	        
    	        try {
    	            
    	            sysUser = (SystemUser) pageContext.getAttribute("SystemUserCcs", PageContext.SESSION_SCOPE);
    	            
    	        } catch (NullPointerException npExp) {
    	            
    	            // N�o precisar�amos fazer nada. Mas s� para
    	            // mater a "legibilidade" do c�digo, vamos 
    	            // setar o usu�rio para "null" novamente.
    	        	sysUser = null;
    	            
    	        } catch (ClassCastException ccExp) {
    	            
    	            // Mesmo caso do bloco catch acima.
    	        	sysUser = null;
    	            
    	        } catch (IllegalStateException isExp) {
    	            
    	            // Isso indica que n�o existe nenhuma sess�o corrente
    	            // ou que esta foi marcada como inv�lida. Nesse caso,
    	            // nosso usu�rio � inv�lido.
    	        	sysUser = null;
    	            
    	        }
    	        
    	    }
    	    
    	}  	
    	
    	if ( sysUser != null ) { // 1. Usu�rio n�o � nulo.
    		
    		if ( this.roleName != null && !this.roleName.trim().equals("") ) { // 2. Foi especificado um perfil a ser testado.
    			
    			if ( !sysUser.hasRole( this.roleName ) ) { // 3. O usu�rio n�o tem o perfil.
    				
    				redirectUser(); // 4. Redirecionamos.
    				
    			}
    			
    		}
    		
    	} else {
    		
    		redirectUser(); 
    		
    	}
        	
    	
        return SKIP_BODY;
        
    }
    
    /** Redireciona o usu�rio.
     * 
     *  @throws JspException
     */
    private void redirectUser() throws JspException {
        
        try {
        
            pageContext.forward( this.page );

        } catch (ServletException sExp) {
            
            throw new JspException(sExp);
            
        } catch (IOException ioExp) {
            
            throw new JspException(ioExp);
            
        }        
        
    }
    
    /** M�todo setter para a propriedade "page".
     *  @param page   O novo valor a ser atribu�do para a propriedade.
     */
    public void setPage(String page) {
        this.page = page;
    }
    
    /** M�todo setter para a propriedade "roleName".
     *  @param roleName   O novo valor a ser atribu�do para a propriedade.
     */    
    public void setRoleName(String roleName) {
    	this.roleName = roleName;
    }
    
    /** Libera todos os recursos utilizados por
     *  esta tag.
     */
    public void release() {
                
        super.release();
        this.page     = null;
        this.roleName = null;
        
    }
    
}
/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......CheckLogonTag.java
 *   .: Criação.....08 de maio, 08:45
 *   .: Autor.......Thiago Uriel M. Garcia
 *   				Edson Luiz Sumensari
 *   .: Descrição...Objeto helper para a sessão de usuário.
 */
package br.com.resource.infra.view.tag;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import br.com.resource.infra.security.SystemUser;

/** Tag utilizada para determinar se um usuário
 *  está autorizado a ver uma determinada página.
 * 
 *  Esta validação é feita determinando se um
 *  objeto <code>SystemUser</code> está armazenado na
 *  sessão HTTP, sob a chave especificada como parâmetro
 *  da tag, ou armazenada na classe de constantes.
 */
public class CheckLogonTag extends TagSupport {
  
	/** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;  
    
    /** Página para onde o usuário será direcionado.
     *  Tipicamente uma página de login.
     */
    private String page = "sessionExpired.jsp";
    
    /** Nome do perfil a ser verificado. */
    private String roleName = null;
    
   	/** No caso, aqui colocaremos nossa
     *  lógica, pois é aqui que ela deverá ser
     *  executada.
     * 
     *  @return  Indicação dizendo que o processamento
     *           da página deverá ser ignorado ou uma dizendo
     *           que deverá contionuar normalmente.
     * 
     *  @throws  Caso haja algum erro na execução do JSP, esta
     *           exception será lançada.
     */
    public int doStartTag() throws JspException {

    	SystemUser sysUser = null;
        
        //System.out.println("USER:" + userExists);
    	// Primeiro devemos checar se um usuário está
        // presente na sessão.
        // Esta validação checa 3 coisas:
        // 1. Se existe uma sessão aberta;
        // 2. Se existe um objeto dentro da chave de usuário;
        // 3. Se este objeto é um usuário de sistema (SystemUser);
        // 4. Se este usuário tem as permissões necessárias, caso
        //    esta validação tenha sido solicitada.
    	Enumeration e = pageContext.getAttributeNamesInScope(PageContext.SESSION_SCOPE);
    	
    	while (e.hasMoreElements()) {
    		
    	    if (e.nextElement().toString().equals("SystemUserCcs")) {
    	        
    	        try {
    	            
    	            sysUser = (SystemUser) pageContext.getAttribute("SystemUserCcs", PageContext.SESSION_SCOPE);
    	            
    	        } catch (NullPointerException npExp) {
    	            
    	            // Não precisaríamos fazer nada. Mas só para
    	            // mater a "legibilidade" do código, vamos 
    	            // setar o usuário para "null" novamente.
    	        	sysUser = null;
    	            
    	        } catch (ClassCastException ccExp) {
    	            
    	            // Mesmo caso do bloco catch acima.
    	        	sysUser = null;
    	            
    	        } catch (IllegalStateException isExp) {
    	            
    	            // Isso indica que não existe nenhuma sessão corrente
    	            // ou que esta foi marcada como inválida. Nesse caso,
    	            // nosso usuário é inválido.
    	        	sysUser = null;
    	            
    	        }
    	        
    	    }
    	    
    	}  	
    	
    	if ( sysUser != null ) { // 1. Usuário não é nulo.
    		
    		if ( this.roleName != null && !this.roleName.trim().equals("") ) { // 2. Foi especificado um perfil a ser testado.
    			
    			if ( !sysUser.hasRole( this.roleName ) ) { // 3. O usuário não tem o perfil.
    				
    				redirectUser(); // 4. Redirecionamos.
    				
    			}
    			
    		}
    		
    	} else {
    		
    		redirectUser(); 
    		
    	}
        	
    	
        return SKIP_BODY;
        
    }
    
    /** Redireciona o usuário.
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
    
    /** Método setter para a propriedade "page".
     *  @param page   O novo valor a ser atribuído para a propriedade.
     */
    public void setPage(String page) {
        this.page = page;
    }
    
    /** Método setter para a propriedade "roleName".
     *  @param roleName   O novo valor a ser atribuído para a propriedade.
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
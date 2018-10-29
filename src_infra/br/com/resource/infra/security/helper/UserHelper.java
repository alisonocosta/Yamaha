/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......UserHelper.java
 *   .: Criação.....24 de abril, 12:17
 *   .: Autor.......Thiago Uriel M. Garcia
 *   				Edson Luiz Sumensari
 *   .: Descrição...Objeto helper para a sessão de usuário.
 */
package br.com.resource.infra.security.helper;
import java.util.Enumeration;

import javax.servlet.http.HttpSession;

import br.com.resource.infra.security.SystemUser;
import br.com.resource.infra.security.exception.InvalidSessionException;

/** Ajuda o controle em suas tarefas mais básicas 
 *  de sessão.
 */
public final class UserHelper {

    /** Atrela um <code>SystemUser</code> em uma sessão
     *  HTTP adequada, dentro
     *  das especificações necessárias. 
     * 
     *  @param session  HttpSession. Sessão HTTP do usuário a ser registrado.
     *  @param user     SystemUser. Usuário a ser registrado à sessão.
     */
    public static void boundUserToSession(HttpSession session, SystemUser user) {
        
    	// Por hora só precisamos colocar o usuário na sessão,
        // mas posteriormente poderão haver outras informações
        // a serem disponibilizadas desta forma...
		session.setAttribute("SystemUserCcs", user);
    }

    /** Remove o vínculo de um usuário com uma sessão HTTP,
     *  efetuando portanto um <b>Logoff</b>.
     * 
     *  É seguro utilizar este método sem ter a certeza de que
     *  o usuário esteja logado. Neste caso, o método não 
     *  fará nada.
     * 
     *  @param session   HttpSession. Sessão HTTP de onde o usuário deverá
     *                   ser removido.
     */
    public static void unboundUserToSession(HttpSession session) {
        session.removeAttribute("SystemUserCcs");
        session.invalidate();
                
    }
    
    /** Checa se um usuário está logado no aplicativo.
     * 
     *  @param session   HttpSession. Sessão na qual o usuário deverá
     *                   ser procurado.
     * 
     *  @return  <code>true</code> caso o usuário esteja logado ou
     *           <code>false</code> caso não esteja.
     */
    public static boolean userIsBounded(HttpSession session) {
        
        Enumeration e = session.getAttributeNames();
        
        String attName;
        while (e.hasMoreElements()) {
            
            attName = (String) e.nextElement();
            if (attName.equals("SystemUserCcs"))
                return true;
                
        }
        
        return false;
        
    }
    
    /** Retorna um usuário de sistema atrelado a uma sessão HTTP.
     * 
     *  @param session A sessão HTTP de onde o usuário deverá ser
     *         retirado.
     * 
     *  @return Um usuário do tipo <code>SystemUser</code> ou <code>null</code>
     *          em algum dos seguintes cenários:
     *          1. A sessão contém um objeto que não implementa a interface
     *             <code>SystemUsed</code>
     *          2. A sessão é nula ou inválida.
     *          3. Não existe nenhum objeto da chave do usuário.
     */
    public static SystemUser getBoundedUser(HttpSession session) throws InvalidSessionException{
        
        try {
            
            SystemUser user = (SystemUser) session.getAttribute("SystemUserCcs");
            if (user != null)
                return user;
            else
            	throw new InvalidSessionException("O usuário não está logado!");
                
        } catch (ClassCastException ccExp) {
            
        	throw new InvalidSessionException("O usuário não está logado!");
            
        } catch (NullPointerException npExp) {
            
        	throw new InvalidSessionException("O usuário não está logado!");
            
        }
            
    }
    
    /** Retorna o identificador do usuário logado. 
     * 
     *  @param session   Sessão HTTP a ser usada para obter o usuário.
     * 
     *  @return   Identificador do usuário.
     */
    public static Object getBoundedUserId(HttpSession session) throws InvalidSessionException{
        
        Object userId = null;
        
        try {
            
            SystemUser user = (SystemUser) session.getAttribute("SystemUserCcs");
            if (user != null)
                userId = user.getIdentifier();
            else
            	throw new InvalidSessionException("O usuário não está logado!");
            
        } catch (ClassCastException ccExp) {
            
        	throw new InvalidSessionException("O usuário não está logado!");
            
        } catch (NullPointerException npExp) {
            
        	throw new InvalidSessionException("O usuário não está logado!");
            
        }

        return userId;
        
    }
    
    /** Limpa a sessão da aplicação
     * 
     * @param session
     */
    public static void clear(HttpSession session) {
    	
        Enumeration attributeNames = session.getAttributeNames();

        while (attributeNames.hasMoreElements()) {
            String attributeName = (String)attributeNames.nextElement(); 
            session.removeAttribute(attributeName);          
        }
        
    }
    
}
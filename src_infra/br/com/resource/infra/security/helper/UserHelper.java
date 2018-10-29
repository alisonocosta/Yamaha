/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......UserHelper.java
 *   .: Cria��o.....24 de abril, 12:17
 *   .: Autor.......Thiago Uriel M. Garcia
 *   				Edson Luiz Sumensari
 *   .: Descri��o...Objeto helper para a sess�o de usu�rio.
 */
package br.com.resource.infra.security.helper;
import java.util.Enumeration;

import javax.servlet.http.HttpSession;

import br.com.resource.infra.security.SystemUser;
import br.com.resource.infra.security.exception.InvalidSessionException;

/** Ajuda o controle em suas tarefas mais b�sicas 
 *  de sess�o.
 */
public final class UserHelper {

    /** Atrela um <code>SystemUser</code> em uma sess�o
     *  HTTP adequada, dentro
     *  das especifica��es necess�rias. 
     * 
     *  @param session  HttpSession. Sess�o HTTP do usu�rio a ser registrado.
     *  @param user     SystemUser. Usu�rio a ser registrado � sess�o.
     */
    public static void boundUserToSession(HttpSession session, SystemUser user) {
        
    	// Por hora s� precisamos colocar o usu�rio na sess�o,
        // mas posteriormente poder�o haver outras informa��es
        // a serem disponibilizadas desta forma...
		session.setAttribute("SystemUserCcs", user);
    }

    /** Remove o v�nculo de um usu�rio com uma sess�o HTTP,
     *  efetuando portanto um <b>Logoff</b>.
     * 
     *  � seguro utilizar este m�todo sem ter a certeza de que
     *  o usu�rio esteja logado. Neste caso, o m�todo n�o 
     *  far� nada.
     * 
     *  @param session   HttpSession. Sess�o HTTP de onde o usu�rio dever�
     *                   ser removido.
     */
    public static void unboundUserToSession(HttpSession session) {
        session.removeAttribute("SystemUserCcs");
        session.invalidate();
                
    }
    
    /** Checa se um usu�rio est� logado no aplicativo.
     * 
     *  @param session   HttpSession. Sess�o na qual o usu�rio dever�
     *                   ser procurado.
     * 
     *  @return  <code>true</code> caso o usu�rio esteja logado ou
     *           <code>false</code> caso n�o esteja.
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
    
    /** Retorna um usu�rio de sistema atrelado a uma sess�o HTTP.
     * 
     *  @param session A sess�o HTTP de onde o usu�rio dever� ser
     *         retirado.
     * 
     *  @return Um usu�rio do tipo <code>SystemUser</code> ou <code>null</code>
     *          em algum dos seguintes cen�rios:
     *          1. A sess�o cont�m um objeto que n�o implementa a interface
     *             <code>SystemUsed</code>
     *          2. A sess�o � nula ou inv�lida.
     *          3. N�o existe nenhum objeto da chave do usu�rio.
     */
    public static SystemUser getBoundedUser(HttpSession session) throws InvalidSessionException{
        
        try {
            
            SystemUser user = (SystemUser) session.getAttribute("SystemUserCcs");
            if (user != null)
                return user;
            else
            	throw new InvalidSessionException("O usu�rio n�o est� logado!");
                
        } catch (ClassCastException ccExp) {
            
        	throw new InvalidSessionException("O usu�rio n�o est� logado!");
            
        } catch (NullPointerException npExp) {
            
        	throw new InvalidSessionException("O usu�rio n�o est� logado!");
            
        }
            
    }
    
    /** Retorna o identificador do usu�rio logado. 
     * 
     *  @param session   Sess�o HTTP a ser usada para obter o usu�rio.
     * 
     *  @return   Identificador do usu�rio.
     */
    public static Object getBoundedUserId(HttpSession session) throws InvalidSessionException{
        
        Object userId = null;
        
        try {
            
            SystemUser user = (SystemUser) session.getAttribute("SystemUserCcs");
            if (user != null)
                userId = user.getIdentifier();
            else
            	throw new InvalidSessionException("O usu�rio n�o est� logado!");
            
        } catch (ClassCastException ccExp) {
            
        	throw new InvalidSessionException("O usu�rio n�o est� logado!");
            
        } catch (NullPointerException npExp) {
            
        	throw new InvalidSessionException("O usu�rio n�o est� logado!");
            
        }

        return userId;
        
    }
    
    /** Limpa a sess�o da aplica��o
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
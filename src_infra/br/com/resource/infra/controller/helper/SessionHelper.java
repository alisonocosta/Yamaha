/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Infraestrutura para aplicativos Java
 *   .: Objeto......SessionHelper.java
 *   .: Cria��o.....20 de Julho, 11:07
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .:				Edson Luiz Sumensari
 *   .: Descri��o...Implementa��o b�sica para o tratamento de objetos da 
 *   .:				sess�o da aplica��o
 */
package br.com.resource.infra.controller.helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/** Implementa��o b�sica para o tratamento de objetos da 
 *  sess�o da aplica��o
 *  
 * @author Edson Luiz Sumensari
 *
 */
public class SessionHelper {
	
	/** Atrela um objeto � sess�o do usu�rio.
	 * 
	 *  @param request
	 *  	<code>HttpServletRequest<code> do qual a sess�o alvo
	 *  	dever� ser obtida.
	 *  @param identifier
	 *  	<code>String</code> representando o identificador pelo
	 *  	qual o objeto ser� conhecido dentro da sess�o.
	 *  @param boundedObject
	 *  	<code>Object</code> que ser� inserido na sess�o.
	 *  @param overwrite
	 *  	<code>boolean</code> indicando se este objeto dever�
	 *  	ou n�o ser sobrescrito caso j� exista.
	 */
	public static void setToSession( HttpServletRequest request
                              , String identifier
                              , Object boundedObject
                              , boolean overwrite) {


		// Primeiro devemos obter a sess�o corrente a partir 
		// da requisi��o HTTP recebida no m�todo.
		HttpSession session = request.getSession();
		
		// Agora verificamos se um objeto com este nome j� 
		// existe na sess�o. 
		if ( session.getAttribute( identifier ) == null ) {
			
			// Caso n�o exista, simplesmente iremos adicion�-lo,
			// usando como identificador aquele enviado ao m�todo.
			session.setAttribute( identifier ,  boundedObject );
			
		} else {
			
			// Do contr�rio (se existir), checamos se devemos
			// sobrescrev�-lo. Caso positivo, inserimos com a 
			// chave determinada.
			if ( overwrite ) 
				session.setAttribute( identifier ,  boundedObject );
			
		}

	}
	
	/** Atrela um objeto � sess�o do usu�rio. Se o objeto j� existir
	 *  na sess�o, ser� substituido.
	 * 
	 *  @param request
	 *  	<code>HttpServletRequest<code> do qual a sess�o alvo
	 *  	dever� ser obtida.
	 *  @param identifier
	 *  	<code>String</code> representando o identificador pelo
	 *  	qual o objeto ser� conhecido dentro da sess�o.
	 *  @param boundedObject
	 *  	<code>Object</code> que ser� inserido na sess�o.
	 */	
	public static void setToSession( HttpServletRequest request
			                  , String identifier
			                  , Object boundedObject) {
		
		setToSession( request, identifier, boundedObject, true );
		
	}
	
	/** Obt�m um objeto da sess�o de acordo com seu identificador.
	 * 
	 *  @param request
	 *  	<code>HttpServletRequest<code> do qual a sess�o alvo
	 *  	dever� ser obtida.
	 *  @param identifier
	 *  	<code>String</code> representando o identificador pelo
	 *  	qual o objeto ser� conhecido dentro da sess�o.
	 *  
	 *  @return
	 *  	O objeto relacionado ao identificador ou <code>null</code>
	 *  	caso nenhum objeto exista com o nome fornecido.
	 */
	public static Object getFromSession(HttpServletRequest request, String identifier) {
		
		return request.getSession().getAttribute( identifier );
		
	}
	
	/** Remove um objeto da sess�o do usu�rio. 
	 * 
	 *  @param request
	 *  	<code>HttpServletRequest<code> do qual a sess�o alvo
	 *  	dever� ser obtida.
	 *  @param identifier
	 *  	<code>String</code> representando o identificador pelo
	 *  	qual o objeto ser� conhecido dentro da sess�o.
	 */		
	public static void removeFromSession(HttpServletRequest request, String identifier) {
		
		// Primeiro devemos obter a sess�o corrente a partir 
		// da requisi��o HTTP recebida no m�todo.
		HttpSession session = request.getSession();
		
		// Agora removemos o objeto de acordo com seu identificador.
		session.removeAttribute( identifier );
		
	}
}

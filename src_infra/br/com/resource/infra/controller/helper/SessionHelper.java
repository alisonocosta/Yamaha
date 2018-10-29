/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Infraestrutura para aplicativos Java
 *   .: Objeto......SessionHelper.java
 *   .: Criação.....20 de Julho, 11:07
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .:				Edson Luiz Sumensari
 *   .: Descrição...Implementação básica para o tratamento de objetos da 
 *   .:				sessão da aplicação
 */
package br.com.resource.infra.controller.helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/** Implementação básica para o tratamento de objetos da 
 *  sessão da aplicação
 *  
 * @author Edson Luiz Sumensari
 *
 */
public class SessionHelper {
	
	/** Atrela um objeto à sessão do usuário.
	 * 
	 *  @param request
	 *  	<code>HttpServletRequest<code> do qual a sessão alvo
	 *  	deverá ser obtida.
	 *  @param identifier
	 *  	<code>String</code> representando o identificador pelo
	 *  	qual o objeto será conhecido dentro da sessão.
	 *  @param boundedObject
	 *  	<code>Object</code> que será inserido na sessão.
	 *  @param overwrite
	 *  	<code>boolean</code> indicando se este objeto deverá
	 *  	ou não ser sobrescrito caso já exista.
	 */
	public static void setToSession( HttpServletRequest request
                              , String identifier
                              , Object boundedObject
                              , boolean overwrite) {


		// Primeiro devemos obter a sessão corrente a partir 
		// da requisição HTTP recebida no método.
		HttpSession session = request.getSession();
		
		// Agora verificamos se um objeto com este nome já 
		// existe na sessão. 
		if ( session.getAttribute( identifier ) == null ) {
			
			// Caso não exista, simplesmente iremos adicioná-lo,
			// usando como identificador aquele enviado ao método.
			session.setAttribute( identifier ,  boundedObject );
			
		} else {
			
			// Do contrário (se existir), checamos se devemos
			// sobrescrevê-lo. Caso positivo, inserimos com a 
			// chave determinada.
			if ( overwrite ) 
				session.setAttribute( identifier ,  boundedObject );
			
		}

	}
	
	/** Atrela um objeto à sessão do usuário. Se o objeto já existir
	 *  na sessão, será substituido.
	 * 
	 *  @param request
	 *  	<code>HttpServletRequest<code> do qual a sessão alvo
	 *  	deverá ser obtida.
	 *  @param identifier
	 *  	<code>String</code> representando o identificador pelo
	 *  	qual o objeto será conhecido dentro da sessão.
	 *  @param boundedObject
	 *  	<code>Object</code> que será inserido na sessão.
	 */	
	public static void setToSession( HttpServletRequest request
			                  , String identifier
			                  , Object boundedObject) {
		
		setToSession( request, identifier, boundedObject, true );
		
	}
	
	/** Obtém um objeto da sessão de acordo com seu identificador.
	 * 
	 *  @param request
	 *  	<code>HttpServletRequest<code> do qual a sessão alvo
	 *  	deverá ser obtida.
	 *  @param identifier
	 *  	<code>String</code> representando o identificador pelo
	 *  	qual o objeto será conhecido dentro da sessão.
	 *  
	 *  @return
	 *  	O objeto relacionado ao identificador ou <code>null</code>
	 *  	caso nenhum objeto exista com o nome fornecido.
	 */
	public static Object getFromSession(HttpServletRequest request, String identifier) {
		
		return request.getSession().getAttribute( identifier );
		
	}
	
	/** Remove um objeto da sessão do usuário. 
	 * 
	 *  @param request
	 *  	<code>HttpServletRequest<code> do qual a sessão alvo
	 *  	deverá ser obtida.
	 *  @param identifier
	 *  	<code>String</code> representando o identificador pelo
	 *  	qual o objeto será conhecido dentro da sessão.
	 */		
	public static void removeFromSession(HttpServletRequest request, String identifier) {
		
		// Primeiro devemos obter a sessão corrente a partir 
		// da requisição HTTP recebida no método.
		HttpSession session = request.getSession();
		
		// Agora removemos o objeto de acordo com seu identificador.
		session.removeAttribute( identifier );
		
	}
}

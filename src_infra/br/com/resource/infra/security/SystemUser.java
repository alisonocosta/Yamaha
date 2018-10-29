/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......SystemUser.java
 *   .: Criação.....07 de maio, 15:35
 *   .: Autor.......Thiago Uriel M. Garcia
 *   				Edson Luiz Sumensari
 *   .: Descrição...Entidade "SystemUser".
 */
package br.com.resource.infra.security;


/** Entidade do sistema, representando
 *  um objeto de sessão na aplicação.
 *  
 *  @author Thiago Uriel M. Garcia
 */
public interface SystemUser {

	/** Método getter para o identificador do usuário
	 * 
	 * @return <code>Object</code>
	 */
	public Object getIdentifier();
	
	/** Método getter para o nome do usuário
	 * 
	 * @return <code>String</code>
	 */
	public String getUsername();
	
	/** Método getter para um atributo do usuário
	 * 
	 * @return <code>Object</code>
	 */
	public Object getAttribute(String attributeName);
	
	/** Método setter para um atributo do usuário
	 * 
	 * @param <code>String</code> nome do atributo
	 * @param <code>Object</code> atributo
	 */
	public void setAttribute(String attributeName, Object attribute);

	/** Determina se este usuário possui um determinado perfil.
	 * 
	 *  @param roleName
	 *  	Nome do perfil a ser verificado.
	 *  
	 *  @return
	 *  	Um valor booleano indicando se o usuário possui um
	 *  	determinado perfil.
	 */
	public boolean hasRole(Object roleName);
	
	/** Adiciona um perfil.
	 * 
	 *  @param roleName
	 *  	Perfil a ser adicionado.
	 *  
	 */
	public void addRole(Object roleName);
	
}
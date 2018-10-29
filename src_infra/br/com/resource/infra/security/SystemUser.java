/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......SystemUser.java
 *   .: Cria��o.....07 de maio, 15:35
 *   .: Autor.......Thiago Uriel M. Garcia
 *   				Edson Luiz Sumensari
 *   .: Descri��o...Entidade "SystemUser".
 */
package br.com.resource.infra.security;


/** Entidade do sistema, representando
 *  um objeto de sess�o na aplica��o.
 *  
 *  @author Thiago Uriel M. Garcia
 */
public interface SystemUser {

	/** M�todo getter para o identificador do usu�rio
	 * 
	 * @return <code>Object</code>
	 */
	public Object getIdentifier();
	
	/** M�todo getter para o nome do usu�rio
	 * 
	 * @return <code>String</code>
	 */
	public String getUsername();
	
	/** M�todo getter para um atributo do usu�rio
	 * 
	 * @return <code>Object</code>
	 */
	public Object getAttribute(String attributeName);
	
	/** M�todo setter para um atributo do usu�rio
	 * 
	 * @param <code>String</code> nome do atributo
	 * @param <code>Object</code> atributo
	 */
	public void setAttribute(String attributeName, Object attribute);

	/** Determina se este usu�rio possui um determinado perfil.
	 * 
	 *  @param roleName
	 *  	Nome do perfil a ser verificado.
	 *  
	 *  @return
	 *  	Um valor booleano indicando se o usu�rio possui um
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
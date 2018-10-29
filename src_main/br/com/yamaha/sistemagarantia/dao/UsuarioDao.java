/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......UsuarioDao.java
 *   .: Cria��o.....13 de junho, 12:14
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descri��o...Interface DAO para a entidade "Usuario".
 */
package br.com.yamaha.sistemagarantia.dao;

import java.io.Serializable;
import java.util.List;

import br.com.resource.infra.dao.Dao;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.model.Usuario;

/** Interface que adiciona m�todos de servi�o pertinentes ao
 *  usu�rio em um objeto DAO padr�o.
 *  
 *  @author Thiago Uriel M. Garcia
 */
public interface UsuarioDao extends Dao {
	
	/** Lista os perfis de um usu�rio, a partir de um n�vel de acesso.
	 * 
	 *  @param nivelAcessoId
	 *  @return
	 *  @throws DaoException
	 */
	public List listRoles(Serializable nivelAcessoId) throws DaoException;
	
	/** Busca um usu�rio pelo nome e pelo hash da senha.
     * 
     *  @param username
     *      <code>String</code> nome do usu�rio.
     *      
     *  @param hashPassword
     *  	<code>String</code> Hash da senha do usu�rio.
     *  
     *  @return
     *      <code>Usuario</code> Uma entidade do usu�rio.
     *  
     *  @throws DaoException
     *      Se houverem erros durante a execu��o estes ser�o lan�ados
     *      como uma Exception deste tipo.
     */
	public Usuario getUser(String username, String hashPassword) throws DaoException;
	
	/**
	 * Atualiza o valor da Session de um usu�rio logado
	 *  
	 * @param String sessionUserId
	 * @param String usuarioCode
	 * @throws DaoException
	 */
	public void setSessionUserId(String sessionUserId, String usuarioCode)throws DaoException;
	
}
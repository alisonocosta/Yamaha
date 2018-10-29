/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......UsuarioDao.java
 *   .: Criação.....13 de junho, 12:14
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Interface DAO para a entidade "Usuario".
 */
package br.com.yamaha.sistemagarantia.dao;

import java.io.Serializable;
import java.util.List;

import br.com.resource.infra.dao.Dao;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.model.Usuario;

/** Interface que adiciona métodos de serviço pertinentes ao
 *  usuário em um objeto DAO padrão.
 *  
 *  @author Thiago Uriel M. Garcia
 */
public interface UsuarioDao extends Dao {
	
	/** Lista os perfis de um usuário, a partir de um nível de acesso.
	 * 
	 *  @param nivelAcessoId
	 *  @return
	 *  @throws DaoException
	 */
	public List listRoles(Serializable nivelAcessoId) throws DaoException;
	
	/** Busca um usuário pelo nome e pelo hash da senha.
     * 
     *  @param username
     *      <code>String</code> nome do usuário.
     *      
     *  @param hashPassword
     *  	<code>String</code> Hash da senha do usuário.
     *  
     *  @return
     *      <code>Usuario</code> Uma entidade do usuário.
     *  
     *  @throws DaoException
     *      Se houverem erros durante a execução estes serão lançados
     *      como uma Exception deste tipo.
     */
	public Usuario getUser(String username, String hashPassword) throws DaoException;
	
	/**
	 * Atualiza o valor da Session de um usuário logado
	 *  
	 * @param String sessionUserId
	 * @param String usuarioCode
	 * @throws DaoException
	 */
	public void setSessionUserId(String sessionUserId, String usuarioCode)throws DaoException;
	
}
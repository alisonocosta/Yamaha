/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......UsuarioBusiness.java
 *   .: Cria��o.....07 de maio, 15:52
 *   .: Autor.......Edson Luiz Sumensari
 *                  Thiago Uriel M. Garcia
 *   .: Descri��o...Objeto de neg�cios para a entidade "Linha".
 */
package br.com.yamaha.sistemagarantia.business;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import br.com.resource.infra.business.BusinessObject;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.DaoException;
import br.com.resource.infra.security.DigestGenerator;
import br.com.yamaha.sistemagarantia.dao.UsuarioDao;
import br.com.yamaha.sistemagarantia.model.ParametroSistema;
import br.com.yamaha.sistemagarantia.model.Usuario;

/** Classe de neg�cios relacionada � entidade <b>Usuario</b>.
 * 
 *  @author Edson Luiz Sumensari
 *  @author Thiago Uriel M. Garcia
 */
public class UsuarioBusiness extends BusinessObject {

    /** Objeto DAO para este objeto de neg�cios. 
     */
    private UsuarioDao usuarioDao;

    /** Recupera uma linha pelo id.
     *
     *  @param entityId
     *      Refer�ncia da entidade na camada de persist�ncia.
     *  @return
     *      Um objeto <code>Usu�rio</code>.
     *      
     * @throws BusinessException
     *      Se houverem erros de execu��o ou nas camadas 
     *      abaixo dos servi�os, ser�o encapsulados neste
     *      tipo de <code>Exception</code>.
     */
    public Usuario get(Serializable entityId) throws BusinessException {

        try {

            return (Usuario)usuarioDao.get(Usuario.class, entityId);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    }
    
    /** Retorna um usu�rio a partir de seu c�digo da aplica��o.
     *  
     *  @param code
     *  	C�digo do usu�rio, utilizado pela aplica��o.
     *  
     *  @return
     *  	Um usu�rio relacionado ao c�digo fornecido.
     *  
     *  @throws BusinessException
     *      Se houverem erros de execu��o ou nas camadas 
     *      abaixo dos servi�os, ser�o encapsulados neste
     *      tipo de <code>Exception</code>.
     */
    public Usuario getByCode(String code) throws BusinessException {
    	
        try {

            Usuario usuario = (Usuario)usuarioDao.getByParameter(Usuario.class, "usuarioCode", code);
            List    roles   = usuarioDao.listRoles(new Long(1));
            
            if ( usuario != null ) {
            	
            	usuario.setRoles(roles);
            	return usuario;
            	
            }else
            	throw new BusinessException("Usu�rio Inv�lido!");
            
        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }    	
    	
    }

    /** Obt�m uma vari�vel do sistema.
     * 
     *  @param paramName
     *      Nome do par�metro a ser obtido.
     *      
     *  @return
     *      Um par�metro de sistema populado.
     *      
     *  @throws BusinessException
     *      Se houverem erros de execu��o ou nas camadas 
     *      abaixo dos servi�os, ser�o encapsulados neste
     *      tipo de <code>Exception</code>
     */
    public ParametroSistema getSystemParameter(String paramName) throws BusinessException {
        
        try {
            
            return (ParametroSistema)this.getUsuarioDao().getByParameter(ParametroSistema.class, "nomeParametro", paramName);
            
        } catch (DaoException dExp) {

            throw new BusinessException(dExp);
            
        }
        
    }
    
    /** Obt�m um usu�rio a partir de seu username
     *  e sua senha.
     * 
     *  Utilizado durante o login.
     * 
     *  @param username   Username a ser utilizado para a busca.
     *  @param password   Password a ser utilizado para a busca.
     *                    N�o � necess�rio criar o hash sobre este
     *                    password. Este m�todo se encarregar� desta
     *                    tarefa.
     * 
     *  @return   A entidade de usu�rio obtida ou nulo caso os
     *            dados n�o correspondam a nenhum registro existente.
     */
    public Usuario getUser(String username, String password) throws BusinessException{

        try {

            // Obt�m o digest do password.
	        String md5Password = DigestGenerator.getDigest(
										                	password, 
										                	DigestGenerator.ALGORITHM_MD5 
										                ); 
	        
	        
//	        System.out.println("Senha hash:" + md5Password.toUpperCase());
	        Usuario usuario = this.getUsuarioDao().getUser(username, md5Password.toUpperCase());
	        List    roles   = usuarioDao.listRoles(new Long(1));
            
            if ( usuario != null ) {
            	
            	usuario.setRoles(roles);
            	return usuario;
            	
            }else
            	return null;
	        
        } catch (DaoException dExp) {

            throw new BusinessException(dExp);
            
        } catch (NoSuchAlgorithmException nsaExp) {
        	
            throw new BusinessException(nsaExp);
            
        }
        
    }
    
    /** Obt�m um usu�rio a partir de seu username
     *  e seu hash.
     * 
     *  Utilizado durante o login.
     * 
     *  @param username   Username a ser utilizado para a busca.
     *  @param hashCode   Password a ser utilizado para a busca.
     *                    N�o � necess�rio criar o hash sobre este
     *                    password. 
     * 
     *  @return   A entidade de usu�rio obtida ou nulo caso os
     *            dados n�o correspondam a nenhum registro existente.
     */
    public Usuario getUserByHash(String username, String hashCode) throws BusinessException{

        try {
	        
	        Usuario usuario = this.getUsuarioDao().getUser(username, hashCode.toUpperCase());
	        List    roles   = usuarioDao.listRoles(new Long(1));
            
            if ( usuario != null ) {
            	
            	usuario.setRoles(roles);
            	return usuario;
            	
            }else
            	return null;
	        
        } catch (DaoException dExp) {

            throw new BusinessException(dExp);
            
        } 
        
    }
    
    /** Atualiza a entidade de usu�rio
     * 
     *  Utilizado durante o login.
     * 
     *  @param usuario   Uma entidade de Usuario.
     *  
     *  @throws BusinessException
     *      Se houverem erros de execu��o ou nas camadas 
     *      abaixo dos servi�os, ser�o encapsulados neste
     *      tipo de <code>Exception</code>
     */
    public void save(Usuario usuario) throws BusinessException{

        try {
	        
	        this.getUsuarioDao().makePersistent(usuario);
	        
        } catch (DaoException dExp) {

            throw new BusinessException(dExp);
            
        } 
        
    }
    
    /**
     * Atualiza o valor da Session de um usu�rio logado
     *  
     * @param String sessionUserId
     * @param String usuarioCode
     * @throws DaoException
     */
    public void setSessionUserId(String sessionUserId, String usuarioCode)throws BusinessException {

    	try {

    		this.getUsuarioDao().setSessionUserId(sessionUserId, usuarioCode);

    	} catch (DaoException dExp) {

    		throw new BusinessException(dExp);

    	} 
    }
    
	/**
	 * @return the usuarioDao
	 */
	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	/**
	 * @param usuarioDao the usuarioDao to set
	 */
	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}
    
}
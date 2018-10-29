/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......UsuarioBusiness.java
 *   .: Criação.....07 de maio, 15:52
 *   .: Autor.......Edson Luiz Sumensari
 *                  Thiago Uriel M. Garcia
 *   .: Descrição...Objeto de negócios para a entidade "Linha".
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

/** Classe de negócios relacionada à entidade <b>Usuario</b>.
 * 
 *  @author Edson Luiz Sumensari
 *  @author Thiago Uriel M. Garcia
 */
public class UsuarioBusiness extends BusinessObject {

    /** Objeto DAO para este objeto de negócios. 
     */
    private UsuarioDao usuarioDao;

    /** Recupera uma linha pelo id.
     *
     *  @param entityId
     *      Referência da entidade na camada de persistência.
     *  @return
     *      Um objeto <code>Usuário</code>.
     *      
     * @throws BusinessException
     *      Se houverem erros de execução ou nas camadas 
     *      abaixo dos serviços, serão encapsulados neste
     *      tipo de <code>Exception</code>.
     */
    public Usuario get(Serializable entityId) throws BusinessException {

        try {

            return (Usuario)usuarioDao.get(Usuario.class, entityId);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    }
    
    /** Retorna um usuário a partir de seu código da aplicação.
     *  
     *  @param code
     *  	Código do usuário, utilizado pela aplicação.
     *  
     *  @return
     *  	Um usuário relacionado ao código fornecido.
     *  
     *  @throws BusinessException
     *      Se houverem erros de execução ou nas camadas 
     *      abaixo dos serviços, serão encapsulados neste
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
            	throw new BusinessException("Usuário Inválido!");
            
        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }    	
    	
    }

    /** Obtém uma variável do sistema.
     * 
     *  @param paramName
     *      Nome do parâmetro a ser obtido.
     *      
     *  @return
     *      Um parâmetro de sistema populado.
     *      
     *  @throws BusinessException
     *      Se houverem erros de execução ou nas camadas 
     *      abaixo dos serviços, serão encapsulados neste
     *      tipo de <code>Exception</code>
     */
    public ParametroSistema getSystemParameter(String paramName) throws BusinessException {
        
        try {
            
            return (ParametroSistema)this.getUsuarioDao().getByParameter(ParametroSistema.class, "nomeParametro", paramName);
            
        } catch (DaoException dExp) {

            throw new BusinessException(dExp);
            
        }
        
    }
    
    /** Obtém um usuário a partir de seu username
     *  e sua senha.
     * 
     *  Utilizado durante o login.
     * 
     *  @param username   Username a ser utilizado para a busca.
     *  @param password   Password a ser utilizado para a busca.
     *                    Não é necessário criar o hash sobre este
     *                    password. Este método se encarregará desta
     *                    tarefa.
     * 
     *  @return   A entidade de usuário obtida ou nulo caso os
     *            dados não correspondam a nenhum registro existente.
     */
    public Usuario getUser(String username, String password) throws BusinessException{

        try {

            // Obtém o digest do password.
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
    
    /** Obtém um usuário a partir de seu username
     *  e seu hash.
     * 
     *  Utilizado durante o login.
     * 
     *  @param username   Username a ser utilizado para a busca.
     *  @param hashCode   Password a ser utilizado para a busca.
     *                    Não é necessário criar o hash sobre este
     *                    password. 
     * 
     *  @return   A entidade de usuário obtida ou nulo caso os
     *            dados não correspondam a nenhum registro existente.
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
    
    /** Atualiza a entidade de usuário
     * 
     *  Utilizado durante o login.
     * 
     *  @param usuario   Uma entidade de Usuario.
     *  
     *  @throws BusinessException
     *      Se houverem erros de execução ou nas camadas 
     *      abaixo dos serviços, serão encapsulados neste
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
     * Atualiza o valor da Session de um usuário logado
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
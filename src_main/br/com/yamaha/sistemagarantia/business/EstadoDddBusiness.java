/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......EstadoDddBusiness.java
 *   .: Cria��o.....26 de julho de 2009, 12:20
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Objeto de neg�cios para a entidade "EstadoDdd".
 */
package br.com.yamaha.sistemagarantia.business;

import java.io.Serializable;

import br.com.resource.infra.business.BusinessObject;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.dao.EstadoDddDao;
import br.com.yamaha.sistemagarantia.model.EstadoDdd;

/** Classe de neg�cios relacionada � entidade <b>EstadoDdd</b>.
 * 
 *  @author Edson Luiz Sumensari
 */
public class EstadoDddBusiness extends BusinessObject {

    /** Objeto DAO para <i>EstadoDdd</i> utilizado para este 
     *  objeto de neg�cios.
     */
    private EstadoDddDao estadoDddDao;    


    /** Recupera um ddd.
     *
     *  @param entityId
     *      Refer�ncia da entidade na camada de persist�ncia.
     *  @return
     *      Um objeto <code>EstadoDdd</code>.
     *      
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
     *      BusinessRuleException (subclasse de BusinessException) ser� 
     *      lan�ada.
     */
    public EstadoDdd get(Serializable entityId) throws BusinessException {

        try {

            return (EstadoDdd)estadoDddDao.get(EstadoDdd.class, entityId);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    }

    /** Obt�m um estadoddd a partir do ddd.
     * 
     *  @param ddd
     *      Par�metro de pesquisa a ser utilizado na busca pelo
     *      DDD na camada de persist�ncia.
     *  
     *  @return
     *      Um estadoDdd ou <code>null</code>
     *      caso nenhum DDD seja encontrado.
     *  
     *  @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
     *      BusinessRuleException (subclasse de BusinessException) ser� 
     *      lan�ada.
     */
    public EstadoDdd getByDdd(Long ddd) throws BusinessException {

        try {
            
            return estadoDddDao.getByDdd(ddd);
            
        } catch (DaoException dExp) {
            
            throw new BusinessException(dExp);
            
        }
        
    }
    
    /** Obt�m um ddd para valida��o.
     * 
     *  @param ddd
     *      <code>Long</code> representando ddd para um telefone.
     *      
     *  @param estadoId
     *      <code>Long</code> representando o id do estado.
     *  
     *  @return
     *      Um estadoDdd relacionado ao valor fornecido ou <code>null</code>
     *      caso nenhum ddd seja encontrado.
     *  
     *  @throws DaoException
     *      Se houverem erros durante a execu��o estes ser�o lan�ados
     *      como uma Exception deste tipo.
     */
    public EstadoDdd getByEstado(Long ddd, Long estadoId) throws BusinessException {

        try {
            
            return estadoDddDao.getByEstado(ddd, estadoId);
            
        } catch (DaoException dExp) {
            
            throw new BusinessException(dExp);
            
        }
        
    }
    
    /** M�todo getter para "estadoDddDao".
     *  @return
     *      <code>EstadoDddDao</code>. O valor atual de estadoDddDao.
     */
    public EstadoDddDao getEstadoDddDao() {
        return this.estadoDddDao;
    }

    /** M�todo setter para "estadoDddDao".
     *  @param clienteDao
     *      <code>EstadoDddDao</code> a ser designado para estadoDddDao.
     */
    public void setEstadoDddDao(EstadoDddDao estadoDddDao) {
        this.estadoDddDao = estadoDddDao;
    }

}
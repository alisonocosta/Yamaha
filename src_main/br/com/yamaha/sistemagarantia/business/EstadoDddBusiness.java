/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......EstadoDddBusiness.java
 *   .: Criação.....26 de julho de 2009, 12:20
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Objeto de negócios para a entidade "EstadoDdd".
 */
package br.com.yamaha.sistemagarantia.business;

import java.io.Serializable;

import br.com.resource.infra.business.BusinessObject;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.dao.EstadoDddDao;
import br.com.yamaha.sistemagarantia.model.EstadoDdd;

/** Classe de negócios relacionada à entidade <b>EstadoDdd</b>.
 * 
 *  @author Edson Luiz Sumensari
 */
public class EstadoDddBusiness extends BusinessObject {

    /** Objeto DAO para <i>EstadoDdd</i> utilizado para este 
     *  objeto de negócios.
     */
    private EstadoDddDao estadoDddDao;    


    /** Recupera um ddd.
     *
     *  @param entityId
     *      Referência da entidade na camada de persistência.
     *  @return
     *      Um objeto <code>EstadoDdd</code>.
     *      
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      será lançada. Caso hajam problemas de regras de negócio, uma
     *      BusinessRuleException (subclasse de BusinessException) será 
     *      lançada.
     */
    public EstadoDdd get(Serializable entityId) throws BusinessException {

        try {

            return (EstadoDdd)estadoDddDao.get(EstadoDdd.class, entityId);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    }

    /** Obtém um estadoddd a partir do ddd.
     * 
     *  @param ddd
     *      Parâmetro de pesquisa a ser utilizado na busca pelo
     *      DDD na camada de persistência.
     *  
     *  @return
     *      Um estadoDdd ou <code>null</code>
     *      caso nenhum DDD seja encontrado.
     *  
     *  @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      será lançada. Caso hajam problemas de regras de negócio, uma
     *      BusinessRuleException (subclasse de BusinessException) será 
     *      lançada.
     */
    public EstadoDdd getByDdd(Long ddd) throws BusinessException {

        try {
            
            return estadoDddDao.getByDdd(ddd);
            
        } catch (DaoException dExp) {
            
            throw new BusinessException(dExp);
            
        }
        
    }
    
    /** Obtém um ddd para validação.
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
     *      Se houverem erros durante a execução estes serão lançados
     *      como uma Exception deste tipo.
     */
    public EstadoDdd getByEstado(Long ddd, Long estadoId) throws BusinessException {

        try {
            
            return estadoDddDao.getByEstado(ddd, estadoId);
            
        } catch (DaoException dExp) {
            
            throw new BusinessException(dExp);
            
        }
        
    }
    
    /** Método getter para "estadoDddDao".
     *  @return
     *      <code>EstadoDddDao</code>. O valor atual de estadoDddDao.
     */
    public EstadoDddDao getEstadoDddDao() {
        return this.estadoDddDao;
    }

    /** Método setter para "estadoDddDao".
     *  @param clienteDao
     *      <code>EstadoDddDao</code> a ser designado para estadoDddDao.
     */
    public void setEstadoDddDao(EstadoDddDao estadoDddDao) {
        this.estadoDddDao = estadoDddDao;
    }

}
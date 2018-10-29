/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......TipoLoteBusiness.java
 *   .: Criação.....24 de abril, 13:11
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Objeto de negócios para a entidade "TipoLote".
 */
package br.com.yamaha.sistemagarantia.business;

import java.io.Serializable;
import java.util.List;

import br.com.resource.infra.business.BusinessObject;
import br.com.resource.infra.dao.Dao;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.model.TipoLote;

/** Classe de negócios relacionada à entidade <b>TipoLote</b>.
 * 
 *  @author Thiago Uriel M. Garcia
 */
public class TipoLoteBusiness extends BusinessObject {

    /** Objeto DAO genérico para utilizado para este objeto de negócios. 
     *  <p>
     *  A entidade "TipoLote" não possui tarefas específicas, portanto, 
     *  sua ponte com o banco de dados é feita utilizando um DAO padrão. 
     */
    private Dao genericDao;

    /** Recupera um tipo de lote pelo id.
     *
     *  @param entityId
     *      Referência da entidade na camada de persistência.
     *  @return
     *      Um objeto <code>Linha</code>.
     *      
     * @throws BusinessException
     *      Se houverem erros de execução ou nas camadas 
     *      abaixo dos serviços, serão encapsulados neste
     *      tipo de <code>Exception</code>.
     */
    public TipoLote get(Serializable entityId) throws BusinessException {

        try {

            return (TipoLote)genericDao.get(TipoLote.class, entityId);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    }
    
    
    /** Lista todos os lotes existentes no banco de dados.
    * 
    *  @return
    *      Um objeto <code>List</code> contendo todos os 
    *      clientes persistentes na aplicação.
    *      
    * @throws BusinessException
    *      Se houverem erros de execução ou nas camadas 
    *      abaixo dos serviços, serão encapsulados neste
    *      tipo de <code>Exception</code>.
    */
   public List list() throws BusinessException {

       try {

           return genericDao.listAll(TipoLote.class);

       } catch (DaoException dExp) {

           throw new BusinessException(dExp);

       }

   }

    /** Salva ou atualiza o estado de uma entidade.
     *  <p>
     *  Se a entidade recebida não existir na camada de persistência
     *  da aplicação, será criada. Do contrário seus valores serão
     *  atualizados de acordo com as informações alteradas.
     *  
     *  @param TipoLote
     *      <code>tipoLote</code> representando a entidade a ser
     *      criada/atualizada na camada de persistência.
     *      
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma BusinessException.
     */    
    public void save(TipoLote tipoLote) throws BusinessException {

        try {

            genericDao.makePersistent(tipoLote);

        } catch ( DaoException daoExp ) {

            throw new BusinessException( daoExp );

        }

    }    

    /** Método getter para "genericDao".
     *  @return
     *      <code>Dao</code>. O valor atual de genericDao.
     */
    public Dao getGenericDao() {
        return this.genericDao;
    }

    /** Método setter para "genericDao".
     *  @param entityId
     *      <code>Dao</code> a ser designado para genericDao.
     */
    public void setGenericDao(Dao genericDao) {
        this.genericDao = genericDao;
    }    

}
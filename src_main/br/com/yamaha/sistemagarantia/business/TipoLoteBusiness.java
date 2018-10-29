/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......TipoLoteBusiness.java
 *   .: Cria��o.....24 de abril, 13:11
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descri��o...Objeto de neg�cios para a entidade "TipoLote".
 */
package br.com.yamaha.sistemagarantia.business;

import java.io.Serializable;
import java.util.List;

import br.com.resource.infra.business.BusinessObject;
import br.com.resource.infra.dao.Dao;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.model.TipoLote;

/** Classe de neg�cios relacionada � entidade <b>TipoLote</b>.
 * 
 *  @author Thiago Uriel M. Garcia
 */
public class TipoLoteBusiness extends BusinessObject {

    /** Objeto DAO gen�rico para utilizado para este objeto de neg�cios. 
     *  <p>
     *  A entidade "TipoLote" n�o possui tarefas espec�ficas, portanto, 
     *  sua ponte com o banco de dados � feita utilizando um DAO padr�o. 
     */
    private Dao genericDao;

    /** Recupera um tipo de lote pelo id.
     *
     *  @param entityId
     *      Refer�ncia da entidade na camada de persist�ncia.
     *  @return
     *      Um objeto <code>Linha</code>.
     *      
     * @throws BusinessException
     *      Se houverem erros de execu��o ou nas camadas 
     *      abaixo dos servi�os, ser�o encapsulados neste
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
    *      clientes persistentes na aplica��o.
    *      
    * @throws BusinessException
    *      Se houverem erros de execu��o ou nas camadas 
    *      abaixo dos servi�os, ser�o encapsulados neste
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
     *  Se a entidade recebida n�o existir na camada de persist�ncia
     *  da aplica��o, ser� criada. Do contr�rio seus valores ser�o
     *  atualizados de acordo com as informa��es alteradas.
     *  
     *  @param TipoLote
     *      <code>tipoLote</code> representando a entidade a ser
     *      criada/atualizada na camada de persist�ncia.
     *      
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma BusinessException.
     */    
    public void save(TipoLote tipoLote) throws BusinessException {

        try {

            genericDao.makePersistent(tipoLote);

        } catch ( DaoException daoExp ) {

            throw new BusinessException( daoExp );

        }

    }    

    /** M�todo getter para "genericDao".
     *  @return
     *      <code>Dao</code>. O valor atual de genericDao.
     */
    public Dao getGenericDao() {
        return this.genericDao;
    }

    /** M�todo setter para "genericDao".
     *  @param entityId
     *      <code>Dao</code> a ser designado para genericDao.
     */
    public void setGenericDao(Dao genericDao) {
        this.genericDao = genericDao;
    }    

}
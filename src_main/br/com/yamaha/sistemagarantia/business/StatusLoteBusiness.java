/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......BusinessBusiness.java
 *   .: Criação.....24 de abril, 15:43
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Objeto de negócios para a entidade "StatusLote".
 */
package br.com.yamaha.sistemagarantia.business;

import java.io.Serializable;
import java.util.List;

import br.com.resource.infra.business.BusinessObject;
import br.com.resource.infra.dao.Dao;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.model.StatusLote;

/** Classe de negócios relacionada à entidade <b>Cliente</b>.
 * 
 *  @author Thiago Uriel M. Garcia
 */
public class StatusLoteBusiness extends BusinessObject {

    /** Objeto DAO genérico para utilizado para este objeto de negócios. 
     *  <p>
     *  A entidade "StatusLote" não possui tarefas específicas, portanto, 
     *  sua ponte com o banco de dados é feita utilizando um DAO padrão. 
     */
    private Dao genericDao;    

    /** Recupera um status de lote pelo id.
     *
     *  @param entityId
     *      Referência da entidade na camada de persistência.
     *  @return
     *      Um objeto <code>StatusLote</code>.
     *      
     * @throws BusinessException
     *      Se houverem erros de execução ou nas camadas 
     *      abaixo dos serviços, serão encapsulados neste
     *      tipo de <code>Exception</code>.
     */
    public StatusLote get(Serializable entityId) throws BusinessException {

        try {

            return (StatusLote)genericDao.get(StatusLote.class, entityId);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    }
    
    /** Lista todos os statusLotes existentes no banco de dados.
     * 
     *  @return
     *      Um objeto <code>List</code> contendo todos os 
     *      StatusLote existentes na aplicação.
     *      
     * @throws BusinessException
     *      Se houverem erros de execução ou nas camadas 
     *      abaixo dos serviços, serão encapsulados neste
     *      tipo de <code>Exception</code>.
     */
    public List list() throws BusinessException {

        try {

            return genericDao.listAll(StatusLote.class);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

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
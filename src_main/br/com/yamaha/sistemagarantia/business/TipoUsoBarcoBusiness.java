/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......TipoUsoBarcoBusiness.java
 *   .: Criação.....11 de maio, 11:23
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Objeto de negócios para a entidade "TipoUsoBarco".
 */
package br.com.yamaha.sistemagarantia.business;

import java.io.Serializable;
import java.util.List;

import br.com.resource.infra.business.BusinessObject;
import br.com.resource.infra.dao.Dao;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.model.TipoUsoBarco;

/** Classe de negócios relacionada à entidade <b>TipoUsoBarco</b>.
 * 
 *  @author Edson Luiz Sumensari
 */
public class TipoUsoBarcoBusiness extends BusinessObject {

    /** Objeto DAO genérico para utilizado para este objeto de negócios. 
     *  <p>
     *  A entidade "TipoUsoBarco" não possui tarefas específicas, portanto, 
     *  sua ponte com o banco de dados é feita utilizando um DAO padrão. 
     */
    private Dao genericDao;

    /** Recupera um tipo de Uso de Barco pelo id.
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
    public TipoUsoBarco get(Serializable entityId) throws BusinessException {

        try {

            return (TipoUsoBarco)genericDao.get(TipoUsoBarco.class, entityId);

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

           return genericDao.listAll(TipoUsoBarco.class);

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
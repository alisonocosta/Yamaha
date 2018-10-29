/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......GrupoServicoBusiness.java
 *   .: Cria��o.....01 de junho, 12:34
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Objeto de neg�cios para a entidade "GrupoServico".
 */
package br.com.yamaha.sistemagarantia.business;

import java.io.Serializable;

import br.com.resource.infra.business.BusinessObject;
import br.com.resource.infra.dao.Dao;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.DaoException;
import br.com.resource.infra.model.EntityObject;
import br.com.yamaha.sistemagarantia.model.ServicoGrupo;

/** Classe de neg�cios relacionada � entidade <b>GrupoServico</b>.
 * 
 *  @author Edson Luiz Sumensari
 */
public class GrupoServicoBusiness extends BusinessObject {

	
	/** Objeto DAO gen�rico para utilizado para este objeto de neg�cios. 
     *  <p>
     *  A entidade "GrupoServico" n�o possui tarefas espec�ficas, portanto, 
     *  sua ponte com o banco de dados � feita utilizando um DAO padr�o. 
     */
    private Dao genericDao;
    
    /** Recupera uma GrupoServico pelo id.
    *
    *  @param entityId
    *      Refer�ncia da entidade na camada de persist�ncia.
    *  @return
    *      Um objeto <code>GrupoServico</code>.
    *      
    * @throws BusinessException
    *      Se houverem erros de execu��o ou nas camadas 
    *      abaixo dos servi�os, ser�o encapsulados neste
    *      tipo de <code>Exception</code>.
    */
   public ServicoGrupo get(Serializable entityId) throws BusinessException {

       try {

           return (ServicoGrupo)genericDao.get(ServicoGrupo.class, entityId);

       } catch (DaoException dExp) {

           throw new BusinessException(dExp);

       }

   }
   
   /** Remove uma GrupoServico pelo id.
   *
   *  @param EntityObject
   *      entidade na camada de persist�ncia.
   *  
   *      
   * @throws BusinessException
   *      Se houverem erros de execu��o ou nas camadas 
   *      abaixo dos servi�os, ser�o encapsulados neste
   *      tipo de <code>Exception</code>.
   */
  public void remove(EntityObject entity) throws BusinessException {

      try {

          genericDao.remove(entity);

      } catch (DaoException dExp) {

          throw new BusinessException(dExp);

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

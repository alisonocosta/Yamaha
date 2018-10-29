/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......GrupoServicoBusiness.java
 *   .: Criação.....01 de junho, 12:34
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Objeto de negócios para a entidade "GrupoServico".
 */
package br.com.yamaha.sistemagarantia.business;

import java.io.Serializable;

import br.com.resource.infra.business.BusinessObject;
import br.com.resource.infra.dao.Dao;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.DaoException;
import br.com.resource.infra.model.EntityObject;
import br.com.yamaha.sistemagarantia.model.ServicoGrupo;

/** Classe de negócios relacionada à entidade <b>GrupoServico</b>.
 * 
 *  @author Edson Luiz Sumensari
 */
public class GrupoServicoBusiness extends BusinessObject {

	
	/** Objeto DAO genérico para utilizado para este objeto de negócios. 
     *  <p>
     *  A entidade "GrupoServico" não possui tarefas específicas, portanto, 
     *  sua ponte com o banco de dados é feita utilizando um DAO padrão. 
     */
    private Dao genericDao;
    
    /** Recupera uma GrupoServico pelo id.
    *
    *  @param entityId
    *      Referência da entidade na camada de persistência.
    *  @return
    *      Um objeto <code>GrupoServico</code>.
    *      
    * @throws BusinessException
    *      Se houverem erros de execução ou nas camadas 
    *      abaixo dos serviços, serão encapsulados neste
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
   *      entidade na camada de persistência.
   *  
   *      
   * @throws BusinessException
   *      Se houverem erros de execução ou nas camadas 
   *      abaixo dos serviços, serão encapsulados neste
   *      tipo de <code>Exception</code>.
   */
  public void remove(EntityObject entity) throws BusinessException {

      try {

          genericDao.remove(entity);

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

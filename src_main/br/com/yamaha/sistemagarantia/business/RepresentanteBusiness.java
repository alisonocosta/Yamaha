/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RepresentanteBusiness.java
 *   .: Cria��o.....16 de julho, 09:46
 *   .: Autor.......Gisele Panosso
 *   .: Descri��o...Objeto de neg�cios para a entidade "Representante".
 */
package br.com.yamaha.sistemagarantia.business;

import java.io.Serializable;
import java.util.List;

import br.com.resource.infra.business.BusinessObject;
import br.com.resource.infra.dao.Dao;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.model.Representante;

/** Classe de neg�cios relacionada � entidade <b>Representante</b>.
 * 
 *  @author Gisele Panosso
 */
public class RepresentanteBusiness extends BusinessObject {


	/** Objeto DAO gen�rico para utilizado para este objeto de neg�cios. 
	 *  <p>
	 *  A entidade "Representante" n�o possui tarefas espec�ficas, portanto, 
	 *  sua ponte com o banco de dados � feita utilizando um DAO padr�o. 
	 */
	private Dao genericDao;

	/** Recupera uma Representante pelo id.
	 *
	 *  @param entityId
	 *      Refer�ncia da entidade na camada de persist�ncia.
	 *  @return
	 *      Um objeto <code>Representante</code>.
	 *      
	 * @throws BusinessException
	 *      Se houverem erros de execu��o ou nas camadas 
	 *      abaixo dos servi�os, ser�o encapsulados neste
	 *      tipo de <code>Exception</code>.
	 */
	public Representante get(Serializable entityId) throws BusinessException {

		try {

			return (Representante)genericDao.get(Representante.class, entityId);

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}

	}

	/** Lista todas os Representantes existentes no banco de dados.
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      clientes persistentes na aplica��o.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
	 *      BusinessRuleException (subclasse de BusinessException) ser� 
	 *      lan�ada.
	 */
	public List list() throws BusinessException {

		try {

			return this.getGenericDao().orderedList(Representante.class, 1, "nome" );

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



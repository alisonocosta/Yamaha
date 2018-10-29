/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......ConcessionariaBusiness.java
 *   .: Cria��o.....04 de maio, 16:10
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Objeto de neg�cios para a entidade "Concessionaria".
 */
package br.com.yamaha.sistemagarantia.business;

import java.io.Serializable;
import java.util.List;

import br.com.resource.infra.business.BusinessObject;
import br.com.resource.infra.dao.Dao;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.model.Concessionaria;

/** Classe de neg�cios relacionada � entidade <b>Concessionaria</b>.
 * 
 *  @author Edson Luiz Sumensari
 */
public class ConcessionariaBusiness extends BusinessObject {


	/** Objeto DAO gen�rico para utilizado para este objeto de neg�cios. 
	 *  <p>
	 *  A entidade "Concessionaria" n�o possui tarefas espec�ficas, portanto, 
	 *  sua ponte com o banco de dados � feita utilizando um DAO padr�o. 
	 */
	private Dao genericDao;

	/** Recupera uma Concessionaria pelo id.
	 *
	 *  @param entityId
	 *      Refer�ncia da entidade na camada de persist�ncia.
	 *  @return
	 *      Um objeto <code>Concessionaria</code>.
	 *      
	 * @throws BusinessException
	 *      Se houverem erros de execu��o ou nas camadas 
	 *      abaixo dos servi�os, ser�o encapsulados neste
	 *      tipo de <code>Exception</code>.
	 */
	public Concessionaria get(Serializable entityId) throws BusinessException {

		try {

			return (Concessionaria)genericDao.get(Concessionaria.class, entityId);

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}

	}

	/** Lista todas as Concessionarias existentes no banco de dados.
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

			return this.getGenericDao().orderedList(Concessionaria.class, 1, "razaoSocial" );

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



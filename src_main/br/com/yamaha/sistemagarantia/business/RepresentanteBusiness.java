/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RepresentanteBusiness.java
 *   .: Criação.....16 de julho, 09:46
 *   .: Autor.......Gisele Panosso
 *   .: Descrição...Objeto de negócios para a entidade "Representante".
 */
package br.com.yamaha.sistemagarantia.business;

import java.io.Serializable;
import java.util.List;

import br.com.resource.infra.business.BusinessObject;
import br.com.resource.infra.dao.Dao;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.model.Representante;

/** Classe de negócios relacionada à entidade <b>Representante</b>.
 * 
 *  @author Gisele Panosso
 */
public class RepresentanteBusiness extends BusinessObject {


	/** Objeto DAO genérico para utilizado para este objeto de negócios. 
	 *  <p>
	 *  A entidade "Representante" não possui tarefas específicas, portanto, 
	 *  sua ponte com o banco de dados é feita utilizando um DAO padrão. 
	 */
	private Dao genericDao;

	/** Recupera uma Representante pelo id.
	 *
	 *  @param entityId
	 *      Referência da entidade na camada de persistência.
	 *  @return
	 *      Um objeto <code>Representante</code>.
	 *      
	 * @throws BusinessException
	 *      Se houverem erros de execução ou nas camadas 
	 *      abaixo dos serviços, serão encapsulados neste
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
	 *      clientes persistentes na aplicação.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      será lançada. Caso hajam problemas de regras de negócio, uma
	 *      BusinessRuleException (subclasse de BusinessException) será 
	 *      lançada.
	 */
	public List list() throws BusinessException {

		try {

			return this.getGenericDao().orderedList(Representante.class, 1, "nome" );

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



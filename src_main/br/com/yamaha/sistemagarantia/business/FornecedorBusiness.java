/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......FornecedorBusiness.java
 *   .: Criação.....04 de outubro de 2007
 *   .: Autor.......Gisele Panosso
 *   .: Descrição...Objeto de negócios para a entidade "FornecedorVO".
 */
package br.com.yamaha.sistemagarantia.business;

import java.util.List;

import br.com.resource.infra.business.BusinessObject;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.dao.FornecedorDao;

/** Classe de negócios relacionada à entidade <b>FornecedorVO</b>.
 * 
 *  @author Gisele Panosso
 */
public class FornecedorBusiness extends BusinessObject {

	/** Objeto DAO genérico para utilizado para este objeto de negócios. 
	 *  <p>
	 *  A entidade "FornecedorVO" não possui tarefas específicas, portanto, 
	 *  sua ponte com o banco de dados é feita utilizando um DAO padrão. 
	 */
	private FornecedorDao fornecedorDao;


	/** Lista todas os Fornecedores existentes no banco de dados.
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

			return fornecedorDao.listFornecedores();

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}
	}   

	/** Método getter para "fornecedorDao".
	 *  @return
	 *      <code>Dao</code>. O valor atual de fornecedorDao.
	 */
	public FornecedorDao getFornecedorDao() {
		return this.fornecedorDao;
	}

	/** Método setter para "fornecedorDao".
	 *  @param entityId
	 *      <code>Dao</code> a ser designado para fornecedorDao.
	 */
	public void setFornecedorDao(FornecedorDao fornecedorDao) {
		this.fornecedorDao = fornecedorDao;
	}  
}




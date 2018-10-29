/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......FornecedorBusiness.java
 *   .: Cria��o.....04 de outubro de 2007
 *   .: Autor.......Gisele Panosso
 *   .: Descri��o...Objeto de neg�cios para a entidade "FornecedorVO".
 */
package br.com.yamaha.sistemagarantia.business;

import java.util.List;

import br.com.resource.infra.business.BusinessObject;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.dao.FornecedorDao;

/** Classe de neg�cios relacionada � entidade <b>FornecedorVO</b>.
 * 
 *  @author Gisele Panosso
 */
public class FornecedorBusiness extends BusinessObject {

	/** Objeto DAO gen�rico para utilizado para este objeto de neg�cios. 
	 *  <p>
	 *  A entidade "FornecedorVO" n�o possui tarefas espec�ficas, portanto, 
	 *  sua ponte com o banco de dados � feita utilizando um DAO padr�o. 
	 */
	private FornecedorDao fornecedorDao;


	/** Lista todas os Fornecedores existentes no banco de dados.
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

			return fornecedorDao.listFornecedores();

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}
	}   

	/** M�todo getter para "fornecedorDao".
	 *  @return
	 *      <code>Dao</code>. O valor atual de fornecedorDao.
	 */
	public FornecedorDao getFornecedorDao() {
		return this.fornecedorDao;
	}

	/** M�todo setter para "fornecedorDao".
	 *  @param entityId
	 *      <code>Dao</code> a ser designado para fornecedorDao.
	 */
	public void setFornecedorDao(FornecedorDao fornecedorDao) {
		this.fornecedorDao = fornecedorDao;
	}  
}




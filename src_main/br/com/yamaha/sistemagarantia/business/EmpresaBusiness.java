/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......EmpresaBusiness.java
 *   .: Criação.....20 de agosto de 2007
 *   .: Autor.......Gisele Panosso
 *   .: Descrição...Objeto de negócios para a entidade "Empresa".
 */
package br.com.yamaha.sistemagarantia.business;

import java.io.Serializable;
import java.util.List;

import br.com.resource.infra.business.BusinessObject;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.dao.EmpresaDao;
import br.com.yamaha.sistemagarantia.model.Empresa;

/** Classe de negócios relacionada à entidade <b>Empresa</b>.
 * 
 *  @author Gisele Panosso
 */
public class EmpresaBusiness extends BusinessObject {

	/** Objeto DAO genérico para utilizado para este objeto de negócios. 
	 *  <p>
	 *  A entidade "Empresa" não possui tarefas específicas, portanto, 
	 *  sua ponte com o banco de dados é feita utilizando um DAO padrão. 
	 */
	private EmpresaDao empresaDao;

	/** Recupera uma Empresa pelo id.
	 *
	 *  @param entityId
	 *      Referência da entidade na camada de persistência.
	 *  @return
	 *      Um objeto <code>Empresa</code>.
	 *      
	 * @throws BusinessException
	 *      Se houverem erros de execução ou nas camadas 
	 *      abaixo dos serviços, serão encapsulados neste
	 *      tipo de <code>Exception</code>.
	 */
	public Empresa get(Serializable entityId) throws BusinessException {

		try {

			return (Empresa)empresaDao.get(Empresa.class, entityId);

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}

	}

	/** Lista todas as Empresas existentes no banco de dados.
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
			
			return empresaDao.listEmpresas();

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}
	}   


	/** Método getter para a propriedade empresaDao.
	 *
	 *  @return o valor atual de empresaDao.
	 */
	public EmpresaDao getEmpresaDao() {
		return empresaDao;
	}

	/** Obtém o valur atual de empresaDao.
	 * 
	 *  @param empresaDao 
	 *    O novo valor para empresaDao.
	 */
	public void setEmpresaDao(EmpresaDao empresaDao) {
		this.empresaDao = empresaDao;
	}   

}



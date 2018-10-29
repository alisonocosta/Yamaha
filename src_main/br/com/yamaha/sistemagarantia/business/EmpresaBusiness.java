/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......EmpresaBusiness.java
 *   .: Cria��o.....20 de agosto de 2007
 *   .: Autor.......Gisele Panosso
 *   .: Descri��o...Objeto de neg�cios para a entidade "Empresa".
 */
package br.com.yamaha.sistemagarantia.business;

import java.io.Serializable;
import java.util.List;

import br.com.resource.infra.business.BusinessObject;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.dao.EmpresaDao;
import br.com.yamaha.sistemagarantia.model.Empresa;

/** Classe de neg�cios relacionada � entidade <b>Empresa</b>.
 * 
 *  @author Gisele Panosso
 */
public class EmpresaBusiness extends BusinessObject {

	/** Objeto DAO gen�rico para utilizado para este objeto de neg�cios. 
	 *  <p>
	 *  A entidade "Empresa" n�o possui tarefas espec�ficas, portanto, 
	 *  sua ponte com o banco de dados � feita utilizando um DAO padr�o. 
	 */
	private EmpresaDao empresaDao;

	/** Recupera uma Empresa pelo id.
	 *
	 *  @param entityId
	 *      Refer�ncia da entidade na camada de persist�ncia.
	 *  @return
	 *      Um objeto <code>Empresa</code>.
	 *      
	 * @throws BusinessException
	 *      Se houverem erros de execu��o ou nas camadas 
	 *      abaixo dos servi�os, ser�o encapsulados neste
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
			
			return empresaDao.listEmpresas();

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}
	}   


	/** M�todo getter para a propriedade empresaDao.
	 *
	 *  @return o valor atual de empresaDao.
	 */
	public EmpresaDao getEmpresaDao() {
		return empresaDao;
	}

	/** Obt�m o valur atual de empresaDao.
	 * 
	 *  @param empresaDao 
	 *    O novo valor para empresaDao.
	 */
	public void setEmpresaDao(EmpresaDao empresaDao) {
		this.empresaDao = empresaDao;
	}   

}



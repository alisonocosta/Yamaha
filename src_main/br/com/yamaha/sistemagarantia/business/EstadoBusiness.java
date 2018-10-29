/* Resource Tecnologia
 * Copyright (c) 2009 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......EstadoDddBusiness.java
 *   .: Cria��o.....01 de Agosto de 2009 
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Objeto de neg�cios para a entidade "Estado".
 */
package br.com.yamaha.sistemagarantia.business;

import java.io.Serializable;
import java.util.List;

import br.com.resource.infra.business.BusinessObject;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.dao.EstadoDao;
import br.com.yamaha.sistemagarantia.model.Estado;

/** Classe de neg�cios relacionada � entidade <b>Estado</b>.
 * 
 *  @author Edson Luiz Sumensari
 */
public class EstadoBusiness extends BusinessObject {

	
    private EstadoDao estadoDao;
    
    /** Recupera uma lista de estados.
     *
     *  @param entityId
     *      Refer�ncia da entidade na camada de persist�ncia.
     *  @return
     *      Uma lista de objetos <code>Estado</code>.
     *      
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
     *      BusinessRuleException (subclasse de BusinessException) ser� 
     *      lan�ada.
     */
    public List listAll() throws BusinessException {

        try {

            return estadoDao.listAllOrderUf();

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    }

    /** Recupera uma Estado pelo id.
	 *
	 *  @param entityId
	 *      Refer�ncia da entidade na camada de persist�ncia.
	 *  @return
	 *      Um objeto <code>Estado</code>.
	 *      
	 * @throws BusinessException
	 *      Se houverem erros de execu��o ou nas camadas 
	 *      abaixo dos servi�os, ser�o encapsulados neste
	 *      tipo de <code>Exception</code>.
	 */
	public Estado get(Serializable entityId) throws BusinessException {

		try {

			return (Estado)estadoDao.get(Estado.class, entityId);

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}

	}
	
	 /** Recupera uma Estado pelo nome.
	 *
	 *  @param estado
	 *      String estado.
	 *  @return
	 *      Um objeto <code>Estado</code>.
	 *      
	 * @throws BusinessException
	 *      Se houverem erros de execu��o ou nas camadas 
	 *      abaixo dos servi�os, ser�o encapsulados neste
	 *      tipo de <code>Exception</code>.
	 */
	public Estado getByName(String estado) throws BusinessException {

		try {

			return (Estado)estadoDao.getByParameter(Estado.class, "uf", estado.toUpperCase());

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}

	}

	/** M�todo getter para a propriedade estadoDao
	 *
	 *  @return EstadoDao de estadoDao
	 */
	public EstadoDao getEstadoDao() {
		return estadoDao;
	}

	/** M�todo setter para a propriedade estadoDao
	 *
	 * @param estadoDao EstadoDao
	 */
	public void setEstadoDao(EstadoDao estadoDao) {
		this.estadoDao = estadoDao;
	}
	
}
/* Resource Tecnologia
 * Copyright (c) 2009 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......EstadoDddBusiness.java
 *   .: Criação.....01 de Agosto de 2009 
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Objeto de negócios para a entidade "Estado".
 */
package br.com.yamaha.sistemagarantia.business;

import java.io.Serializable;
import java.util.List;

import br.com.resource.infra.business.BusinessObject;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.dao.EstadoDao;
import br.com.yamaha.sistemagarantia.model.Estado;

/** Classe de negócios relacionada à entidade <b>Estado</b>.
 * 
 *  @author Edson Luiz Sumensari
 */
public class EstadoBusiness extends BusinessObject {

	
    private EstadoDao estadoDao;
    
    /** Recupera uma lista de estados.
     *
     *  @param entityId
     *      Referência da entidade na camada de persistência.
     *  @return
     *      Uma lista de objetos <code>Estado</code>.
     *      
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      será lançada. Caso hajam problemas de regras de negócio, uma
     *      BusinessRuleException (subclasse de BusinessException) será 
     *      lançada.
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
	 *      Referência da entidade na camada de persistência.
	 *  @return
	 *      Um objeto <code>Estado</code>.
	 *      
	 * @throws BusinessException
	 *      Se houverem erros de execução ou nas camadas 
	 *      abaixo dos serviços, serão encapsulados neste
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
	 *      Se houverem erros de execução ou nas camadas 
	 *      abaixo dos serviços, serão encapsulados neste
	 *      tipo de <code>Exception</code>.
	 */
	public Estado getByName(String estado) throws BusinessException {

		try {

			return (Estado)estadoDao.getByParameter(Estado.class, "uf", estado.toUpperCase());

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}

	}

	/** Método getter para a propriedade estadoDao
	 *
	 *  @return EstadoDao de estadoDao
	 */
	public EstadoDao getEstadoDao() {
		return estadoDao;
	}

	/** Método setter para a propriedade estadoDao
	 *
	 * @param estadoDao EstadoDao
	 */
	public void setEstadoDao(EstadoDao estadoDao) {
		this.estadoDao = estadoDao;
	}
	
}
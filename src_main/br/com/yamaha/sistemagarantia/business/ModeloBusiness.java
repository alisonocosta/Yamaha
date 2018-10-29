/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......ModeloBusiness.java
 *   .: Criação.....17 de setembro de 2007
 *   .: Autor.......Gisele Panosso
 *   .: Descrição...Objeto de negócios para a entidade "Modelo".
 */
package br.com.yamaha.sistemagarantia.business;

import java.io.Serializable;
import java.util.List;

import br.com.resource.infra.business.BusinessObject;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.dao.ModeloDao;
import br.com.yamaha.sistemagarantia.model.Modelo;

/** Classe de negócios relacionada à entidade <b>Modelo</b>.
 * 
 *  @author Gisele Panosso
 */
public class ModeloBusiness extends BusinessObject {

	/** Objeto DAO genérico para utilizado para este objeto de negócios. 
	 *  <p>
	 *  A entidade "ModeloVO" não possui tarefas específicas, portanto, 
	 *  sua ponte com o banco de dados é feita utilizando um DAO padrão. 
	 */
	private ModeloDao modeloDao;
	
	/** Recupera uma Modelo pelo id.
    *
    *  @param entityId
    *      Referência da entidade na camada de persistência.
    *  @return
    *      Um objeto <code>Modelo</code>.
    *      
    * @throws BusinessException
    *      Se houverem erros de execução ou nas camadas 
    *      abaixo dos serviços, serão encapsulados neste
    *      tipo de <code>Exception</code>.
    */
   public Modelo get(Serializable entityId) throws BusinessException {

       try {

           return (Modelo)modeloDao.get(Modelo.class, entityId);

       } catch (DaoException dExp) {

           throw new BusinessException(dExp);

       }

   }

	/** Lista todas as Modelos existentes no banco de dados.
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
			
			return modeloDao.listModelos();

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}
	}  
	
	/** Lista os Modelos para o relatório de Gráficos individuais. 
	 *
	 *  @return
	 *      Um objeto <code>List</code> contendo os modelos para o relatório.
	 *      
	 * @throws DaoException
	 */
	public List listGraphModelos () throws BusinessException {

		try {
			
			return modeloDao.listGraphModelos();

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}
	}  
	
	/** Lista os Modelos para o relatório de Gráficos individuais por linha. 
	 * 
	 * 	@param linhaId
	 * 		<code>Long</code> Id da linha do produto 
	 *
	 *  @return
	 *      Um objeto <code>List</code> contendo os modelos para o relatório.
	 *      
	 * @throws DaoException
	 */
	public List listGraphModelos (Long linhaId) throws BusinessException {

		try {
			
			return modeloDao.listModelosByLinha(linhaId);

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}
	}  


	/** Método getter para a propriedade ModeloDao.
	 *
	 *  @return o valor atual de ModeloDao.
	 */
	public ModeloDao getModeloDao() {
		return modeloDao;
	}

	/** Obtém o valur atual de ModeloDao.
	 * 
	 *  @param ModeloDao 
	 *    O novo valor para ModeloDao.
	 */
	public void setModeloDao(ModeloDao modeloDao) {
		this.modeloDao = modeloDao;
	}   

}



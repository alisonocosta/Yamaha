/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......ModeloBusiness.java
 *   .: Cria��o.....17 de setembro de 2007
 *   .: Autor.......Gisele Panosso
 *   .: Descri��o...Objeto de neg�cios para a entidade "Modelo".
 */
package br.com.yamaha.sistemagarantia.business;

import java.io.Serializable;
import java.util.List;

import br.com.resource.infra.business.BusinessObject;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.dao.ModeloDao;
import br.com.yamaha.sistemagarantia.model.Modelo;

/** Classe de neg�cios relacionada � entidade <b>Modelo</b>.
 * 
 *  @author Gisele Panosso
 */
public class ModeloBusiness extends BusinessObject {

	/** Objeto DAO gen�rico para utilizado para este objeto de neg�cios. 
	 *  <p>
	 *  A entidade "ModeloVO" n�o possui tarefas espec�ficas, portanto, 
	 *  sua ponte com o banco de dados � feita utilizando um DAO padr�o. 
	 */
	private ModeloDao modeloDao;
	
	/** Recupera uma Modelo pelo id.
    *
    *  @param entityId
    *      Refer�ncia da entidade na camada de persist�ncia.
    *  @return
    *      Um objeto <code>Modelo</code>.
    *      
    * @throws BusinessException
    *      Se houverem erros de execu��o ou nas camadas 
    *      abaixo dos servi�os, ser�o encapsulados neste
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
			
			return modeloDao.listModelos();

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}
	}  
	
	/** Lista os Modelos para o relat�rio de Gr�ficos individuais. 
	 *
	 *  @return
	 *      Um objeto <code>List</code> contendo os modelos para o relat�rio.
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
	
	/** Lista os Modelos para o relat�rio de Gr�ficos individuais por linha. 
	 * 
	 * 	@param linhaId
	 * 		<code>Long</code> Id da linha do produto 
	 *
	 *  @return
	 *      Um objeto <code>List</code> contendo os modelos para o relat�rio.
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


	/** M�todo getter para a propriedade ModeloDao.
	 *
	 *  @return o valor atual de ModeloDao.
	 */
	public ModeloDao getModeloDao() {
		return modeloDao;
	}

	/** Obt�m o valur atual de ModeloDao.
	 * 
	 *  @param ModeloDao 
	 *    O novo valor para ModeloDao.
	 */
	public void setModeloDao(ModeloDao modeloDao) {
		this.modeloDao = modeloDao;
	}   

}



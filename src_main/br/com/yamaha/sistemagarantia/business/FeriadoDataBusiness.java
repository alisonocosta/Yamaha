/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......FeriadoDataBusiness.java
 *   .: Cria��o.....25 de maio, 14:04
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Objeto de neg�cios para a entidade "FeriadoData".
 */
package br.com.yamaha.sistemagarantia.business;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import br.com.resource.infra.business.BusinessObject;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.DaoException;
import br.com.resource.infra.utils.DateUtils;
import br.com.yamaha.sistemagarantia.dao.FeriadoDataDao;
import br.com.yamaha.sistemagarantia.model.FeriadoData;

/** Classe de neg�cios relacionada � entidade <b>FeriadoData</b>.
 * 
 *  @author Edson Luiz Sumensari
 */
public class FeriadoDataBusiness extends BusinessObject {

    /** Objeto DAO gen�rico para utilizado para este objeto de neg�cios. 
     *  <p>
     *  A entidade "TipoUsoBarco" n�o possui tarefas espec�ficas, portanto, 
     *  sua ponte com o banco de dados � feita utilizando um DAO padr�o. 
     */
    private FeriadoDataDao feriadoDataDao;

    /** Recupera um tipo de Uso de Barco pelo id.
     *
     *  @param entityId
     *      Refer�ncia da entidade na camada de persist�ncia.
     *  @return
     *      Um objeto <code>FeriadoData</code>.
     *      
     * @throws BusinessException
     *      Se houverem erros de execu��o ou nas camadas 
     *      abaixo dos servi�os, ser�o encapsulados neste
     *      tipo de <code>Exception</code>.
     */
    public FeriadoData get(Serializable entityId) throws BusinessException {

        try {

            return (FeriadoData)feriadoDataDao.get(FeriadoData.class, entityId);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    }
    
    /** Obt�m uma listagem de registros limitada de acordo com o interval de data informado.
     *    
     * @param startDate
     *      <code>Date</code> Data inicial para filtro.
     *      
     * @param finalDate
     *      <code>Date</code> Data final do filtro.
     * 
     * @return
     *      Uma listagem de elementos, organizada e restrita aos 
     *      delimitadores fornecidos.
     * 
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
     */
    public List listDateBetween(Date startDate, Date finalDate) throws BusinessException {
    	
    	try {
    		return (List)feriadoDataDao.listDateBetween(startDate, finalDate);
    	
	    } catch (DaoException dExp) {
	
	        throw new BusinessException(dExp);
	
	    }
    	
    }
    
    /** Retorna o n�mero de dias �teis de um intervalo de data
     * 
     * @param startDate
     * 		<code>Date</code> Data inicial   
     *    
     * @param finalDate
     * 		<code>Date</code> Data Final
     * 
     * @return 
     * 		<code>int</code> N�mero de dias �teis do intervalo
     * 
     * @throws BusinessException
     */
    public int getDaysUtils(Date startDate, Date finalDate) throws BusinessException {
    	
    	List betweenDate = listDateBetween(startDate, finalDate);
    	
    	return DateUtils.daysUtils(startDate, finalDate, betweenDate);
    	
    }
    
    /** Retorna o n�mero de dias corridos de um intervalo de data
     * 
     * @param startDate
     * 		<code>Date</code> Data inicial   
     *    
     * @param finalDate
     * 		<code>Date</code> Data Final
     * 
     * @return 
     * 		<code>int</code> N�mero de dias �teis do intervalo
     * 
     * @throws BusinessException
     */
    public int getDaysCurrence(Date startDate, Date finalDate) throws BusinessException {
    	
    	return DateUtils.getDays(startDate, finalDate);
    	
    }
   
   
    /** M�todo getter para "feriadoDataDao".
     *  @return
     *      <code>FeriadoDataDao</code>. O valor atual de genericDao.
     */
    public FeriadoDataDao getFeriadoDataDao() {
        return this.feriadoDataDao;
    }

    /** M�todo setter para "feriadoDataDao".
     *  @param entityId
     *      <code>FeriadoDataDao</code> a ser designado para feriadoDataDao.
     */
    public void setFeriadoDataDao(FeriadoDataDao feriadoDataDao) {
        this.feriadoDataDao = feriadoDataDao;
    }    

}
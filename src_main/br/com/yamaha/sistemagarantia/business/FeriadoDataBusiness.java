/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......FeriadoDataBusiness.java
 *   .: Criação.....25 de maio, 14:04
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Objeto de negócios para a entidade "FeriadoData".
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

/** Classe de negócios relacionada à entidade <b>FeriadoData</b>.
 * 
 *  @author Edson Luiz Sumensari
 */
public class FeriadoDataBusiness extends BusinessObject {

    /** Objeto DAO genérico para utilizado para este objeto de negócios. 
     *  <p>
     *  A entidade "TipoUsoBarco" não possui tarefas específicas, portanto, 
     *  sua ponte com o banco de dados é feita utilizando um DAO padrão. 
     */
    private FeriadoDataDao feriadoDataDao;

    /** Recupera um tipo de Uso de Barco pelo id.
     *
     *  @param entityId
     *      Referência da entidade na camada de persistência.
     *  @return
     *      Um objeto <code>FeriadoData</code>.
     *      
     * @throws BusinessException
     *      Se houverem erros de execução ou nas camadas 
     *      abaixo dos serviços, serão encapsulados neste
     *      tipo de <code>Exception</code>.
     */
    public FeriadoData get(Serializable entityId) throws BusinessException {

        try {

            return (FeriadoData)feriadoDataDao.get(FeriadoData.class, entityId);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    }
    
    /** Obtém uma listagem de registros limitada de acordo com o interval de data informado.
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
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
     */
    public List listDateBetween(Date startDate, Date finalDate) throws BusinessException {
    	
    	try {
    		return (List)feriadoDataDao.listDateBetween(startDate, finalDate);
    	
	    } catch (DaoException dExp) {
	
	        throw new BusinessException(dExp);
	
	    }
    	
    }
    
    /** Retorna o número de dias úteis de um intervalo de data
     * 
     * @param startDate
     * 		<code>Date</code> Data inicial   
     *    
     * @param finalDate
     * 		<code>Date</code> Data Final
     * 
     * @return 
     * 		<code>int</code> Número de dias úteis do intervalo
     * 
     * @throws BusinessException
     */
    public int getDaysUtils(Date startDate, Date finalDate) throws BusinessException {
    	
    	List betweenDate = listDateBetween(startDate, finalDate);
    	
    	return DateUtils.daysUtils(startDate, finalDate, betweenDate);
    	
    }
    
    /** Retorna o número de dias corridos de um intervalo de data
     * 
     * @param startDate
     * 		<code>Date</code> Data inicial   
     *    
     * @param finalDate
     * 		<code>Date</code> Data Final
     * 
     * @return 
     * 		<code>int</code> Número de dias úteis do intervalo
     * 
     * @throws BusinessException
     */
    public int getDaysCurrence(Date startDate, Date finalDate) throws BusinessException {
    	
    	return DateUtils.getDays(startDate, finalDate);
    	
    }
   
   
    /** Método getter para "feriadoDataDao".
     *  @return
     *      <code>FeriadoDataDao</code>. O valor atual de genericDao.
     */
    public FeriadoDataDao getFeriadoDataDao() {
        return this.feriadoDataDao;
    }

    /** Método setter para "feriadoDataDao".
     *  @param entityId
     *      <code>FeriadoDataDao</code> a ser designado para feriadoDataDao.
     */
    public void setFeriadoDataDao(FeriadoDataDao feriadoDataDao) {
        this.feriadoDataDao = feriadoDataDao;
    }    

}
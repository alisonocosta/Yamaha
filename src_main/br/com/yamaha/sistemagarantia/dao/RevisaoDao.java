/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RevisaoDao.java
 *   .: Criação.....14 de maio, 16:02
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Interface DAO para a entidade "Revisao".
 */
package br.com.yamaha.sistemagarantia.dao;

import java.util.List;

import br.com.resource.infra.dao.Dao;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.model.Revisao;

/** Interface contendo os métodos especializados para
 *  as entidades <b>Revisao</b>.
 *  
 *  @author Edson Luiz Sumensari
 */
public interface RevisaoDao extends Dao {
	
	/** Obtém uma entidade da próxima revisão para o mode informado, revisão ainda não realizada.
     *    
     * @param chassi
     *      Chassi do produto.
     * 
     * 
     * @return
     *      Uma entidade de Revisao
     * 
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
     */
    public Revisao getNextByChassi( String chassi ) throws DaoException;

	/** Obtém uma listagem de registros aproxiamda limitada de acordo o parâmetro passado.
     *    
     * @param paramName
     *      Nome do parâmetro do filtro.
     *      
     * @param valuePart
     *      valor que servirá como parâmetro de filtro.
     * 
     * @return
     *      Uma listagem de elementos, organizada e restrita aos 
     *      delimitadores fornecidos.
     * 
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
     */
    public List listApproachedByChassi( String valuePart) throws DaoException;
    
    /** Obtém uma listagem de revisões ainda não realizadas para o chassi informado.
     *    
     * @param chassi
     *      Chassi do produto.
     * 
     * 
     * @return
     *      Uma listagem de elementos, organizada e restrita aos 
     *      delimitadores fornecidos.
     * 
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
     */
    public List listNewByModel( String chassi ) throws DaoException;    
    
    /** Obtém uma listagem de registros limitada de acordo o parâmetro passado.
     *    
     * @param paramName
     *      Nome do parâmetro do filtro.
     *      
     * @param modelo
     *      valor que servirá como parâmetro de filtro.
     * 
     * @return
     *      Uma listagem de elementos, organizada e restrita aos 
     *      delimitadores fornecidos.
     * 
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
     */
    public List listByModel( String modelo ) throws DaoException;
    
    
    /** Obtém uma entidade de Revisao de acordo com o modelo - 5 primeiras posições do chassi e
     *  o número da revisão desejada
     *    
     * @param String model
     *      modelo.
     *      
     * @param Long numberRevision
     *      valor que servirá como parâmetro de filtro da Revisão.
     * 
     * @return Revisao
     *      Uma entitydade de Revisao.
     * 
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
     */
    public Revisao getByModel(String model, Long numberRevision) throws DaoException;
    
}
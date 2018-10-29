/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RevisaoDao.java
 *   .: Cria��o.....14 de maio, 16:02
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Interface DAO para a entidade "Revisao".
 */
package br.com.yamaha.sistemagarantia.dao;

import java.util.List;

import br.com.resource.infra.dao.Dao;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.model.Revisao;

/** Interface contendo os m�todos especializados para
 *  as entidades <b>Revisao</b>.
 *  
 *  @author Edson Luiz Sumensari
 */
public interface RevisaoDao extends Dao {
	
	/** Obt�m uma entidade da pr�xima revis�o para o mode informado, revis�o ainda n�o realizada.
     *    
     * @param chassi
     *      Chassi do produto.
     * 
     * 
     * @return
     *      Uma entidade de Revisao
     * 
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
     */
    public Revisao getNextByChassi( String chassi ) throws DaoException;

	/** Obt�m uma listagem de registros aproxiamda limitada de acordo o par�metro passado.
     *    
     * @param paramName
     *      Nome do par�metro do filtro.
     *      
     * @param valuePart
     *      valor que servir� como par�metro de filtro.
     * 
     * @return
     *      Uma listagem de elementos, organizada e restrita aos 
     *      delimitadores fornecidos.
     * 
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
     */
    public List listApproachedByChassi( String valuePart) throws DaoException;
    
    /** Obt�m uma listagem de revis�es ainda n�o realizadas para o chassi informado.
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
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
     */
    public List listNewByModel( String chassi ) throws DaoException;    
    
    /** Obt�m uma listagem de registros limitada de acordo o par�metro passado.
     *    
     * @param paramName
     *      Nome do par�metro do filtro.
     *      
     * @param modelo
     *      valor que servir� como par�metro de filtro.
     * 
     * @return
     *      Uma listagem de elementos, organizada e restrita aos 
     *      delimitadores fornecidos.
     * 
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
     */
    public List listByModel( String modelo ) throws DaoException;
    
    
    /** Obt�m uma entidade de Revisao de acordo com o modelo - 5 primeiras posi��es do chassi e
     *  o n�mero da revis�o desejada
     *    
     * @param String model
     *      modelo.
     *      
     * @param Long numberRevision
     *      valor que servir� como par�metro de filtro da Revis�o.
     * 
     * @return Revisao
     *      Uma entitydade de Revisao.
     * 
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
     */
    public Revisao getByModel(String model, Long numberRevision) throws DaoException;
    
}
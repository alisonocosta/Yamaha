/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......FeriadoDataDao.java
 *   .: Criação.....25 de maio, 14:11
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Interface DAO para a entidade "FeriadoData".
 */
package br.com.yamaha.sistemagarantia.dao;

import java.util.Date;
import java.util.List;

import br.com.resource.infra.dao.Dao;
import br.com.resource.infra.exception.DaoException;

/** Interface contendo os métodos especializados para
 *  as entidades <b>FeriadoData</b>.
 *  
 *  @author Edson Luiz Sumensari
 */
public interface FeriadoDataDao extends Dao {

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
    public List listDateBetween(Date startDate, Date finalDate) throws DaoException;
   
}
/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......ModeloDao.java
 *   .: Cria��o.....18 de setembro de 2007
 *   .: Autor.......Gisele Panosso
 *   .: Descri��o...Objeto de neg�cios para a entidade "ModeloDao".
 */

package br.com.yamaha.sistemagarantia.dao;

import java.util.List;

import br.com.resource.infra.dao.Dao;
import br.com.resource.infra.exception.DaoException;

public interface ModeloDao extends Dao {
	
	
    /** Obt�m a lista de modelos.
     * 
     *  @return
     *      Listagem de todas as modelos, onde dataTermino is null.
     *  
     *  @throws DaoException
     *      Se houverem problemas na camada de acesso, esta exception
     *      ser� disparada pelo objeto.
     */
    public List listModelos() throws DaoException;
    
    /** Lista todos os modelos, onde dataTermino is null.
	 * 
	 * @return list
	 * 	<code>list</code> lista de modelos.
	 * 
	 * @throws DaoException
	 */	
	 public List listModelosByLinha(Long linhaId) throws DaoException;
    
    /** Lista os Modelos para o relat�rio de Gr�ficos individuais por linha. 
	 *
	 *  @return
	 *      Um objeto <code>List</code> contendo os modelos para o relat�rio.
	 *      
	 * @throws DaoException
	 */
	public List listGraphModelos () throws DaoException;


}

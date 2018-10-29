/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......EstadoDddDao.java
 *   .: Cria��o.....01 de Agosto de 2009, 18:59
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Interface DAO para a entidade "Estado".
 */
package br.com.yamaha.sistemagarantia.dao;

import java.util.List;

import br.com.resource.infra.dao.Dao;
import br.com.resource.infra.exception.DaoException;

/** Interface contendo os m�todos especializados para
 *  as entidades <b>Estado</b>.
 *  
 *  @author Edson Luiz Sumensari
 */
public interface EstadoDao extends Dao {

    /** Obt�m uma estados para valida��o.
     * 
     *  @param estados
     *      <code>List</code>
     *  
     *  @return
     *      Um ddd relacionado ao valor fornecido ou <code>null</code>
     *      caso nenhum ddd seja encontrado.
     *  
     *  @throws DaoException
     *      Se houverem erros durante a execu��o estes ser�o lan�ados
     *      como uma Exception deste tipo.
     */
    public List listAllOrderUf() throws DaoException; 
    
}
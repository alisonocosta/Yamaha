/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......EstadoDddDao.java
 *   .: Criação.....01 de Agosto de 2009, 18:59
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Interface DAO para a entidade "Estado".
 */
package br.com.yamaha.sistemagarantia.dao;

import java.util.List;

import br.com.resource.infra.dao.Dao;
import br.com.resource.infra.exception.DaoException;

/** Interface contendo os métodos especializados para
 *  as entidades <b>Estado</b>.
 *  
 *  @author Edson Luiz Sumensari
 */
public interface EstadoDao extends Dao {

    /** Obtém uma estados para validação.
     * 
     *  @param estados
     *      <code>List</code>
     *  
     *  @return
     *      Um ddd relacionado ao valor fornecido ou <code>null</code>
     *      caso nenhum ddd seja encontrado.
     *  
     *  @throws DaoException
     *      Se houverem erros durante a execução estes serão lançados
     *      como uma Exception deste tipo.
     */
    public List listAllOrderUf() throws DaoException; 
    
}
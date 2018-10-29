/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......FornecedorDao.java
 *   .: Criação.....04 de outubro de 2007
 *   .: Autor.......Gisele Panosso
 *   .: Descrição...Objeto de negócios para a entidade "FornecedorDao".
 */

package br.com.yamaha.sistemagarantia.dao;

import java.util.List;

import br.com.resource.infra.dao.Dao;
import br.com.resource.infra.exception.DaoException;

public interface FornecedorDao extends Dao {
	
	
    /** Obtém a lista de fornecedores.
     * 
     *  @return
     *      Listagem de todos os fornecedores, onde dataTermino is null.
     *  
     *  @throws DaoException
     *      Se houverem problemas na camada de acesso, esta exception
     *      será disparada pelo objeto.
     */
    public List listFornecedores() throws DaoException;


}

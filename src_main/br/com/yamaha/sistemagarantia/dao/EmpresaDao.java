/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......EmpresaDao.java
 *   .: Criação.....03 de setembro de 2007
 *   .: Autor.......Gisele Panosso
 *   .: Descrição...Objeto de negócios para a entidade "EmpresaDao".
 */

package br.com.yamaha.sistemagarantia.dao;

import java.util.List;

import br.com.resource.infra.dao.Dao;
import br.com.resource.infra.exception.DaoException;

public interface EmpresaDao extends Dao {
	
	
    /** Obtém a lista de empresas com critérios de filtro.
     * 
     *  @return
     *      Listagem de todas as empresas, onde o orgCode is not null e dataTermino is null.
     *  
     *  @throws DaoException
     *      Se houverem problemas na camada de acesso, esta exception
     *      será disparada pelo objeto.
     */
    public List listEmpresas() throws DaoException;


}

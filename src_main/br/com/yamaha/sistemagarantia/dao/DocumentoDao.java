/* Resource Tecnologia
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......DocumentoDao.java
 *   .: Criação.....29 de Agosto de 2008
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Objeto de negócios para a entidade "DocumentoDao".
 */

package br.com.yamaha.sistemagarantia.dao;

import java.util.List;

import br.com.resource.infra.dao.Dao;
import br.com.resource.infra.exception.DaoException;

public interface DocumentoDao extends Dao {
	
	/** Lista os arquivos expirados
	 * 
	 * @return list
	 * 	<code>list</code> lista de empresas.
	 * 
	 * @throws DaoException
	 */	
	 public List listExpired() throws DaoException;
	
}

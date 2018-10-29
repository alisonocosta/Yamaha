/* Resource Tecnologia
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......DocumentoHibernateDaoImpl.java
 *   .: Criação.....29 de Agosto de 2008
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...DAO com tarefas específicas para a entidade Documento.
 */
package br.com.yamaha.sistemagarantia.dao.hibernate;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import br.com.resource.infra.dao.hibernate.HibernateDaoImpl;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.dao.DocumentoDao;
import br.com.yamaha.sistemagarantia.model.Documento;

public class DocumentoHibernateDaoImpl extends HibernateDaoImpl implements DocumentoDao{
	 
	
	/** Lista os arquivos expirados
	 * 
	 * @return list
	 * 	<code>list</code> lista de empresas.
	 * 
	 * @throws DaoException
	 */	
	 public List listExpired() throws DaoException{
		 
		 String sql = "   SELECT  dc.* 					" +
				      "   FROM  ym_sg_documento dc 		" +
					  "   WHERE dc.end_date <= sysdate  " +
					  "	  AND   DC.end_date is not null " +
					  "	  ORDER BY DC.NOME_DOCUMENTO, DC.DESCRICAO asc";
		   
		   Session session = super.getSession();
		   SQLQuery query = session.createSQLQuery(sql);

		   query.addEntity(Documento.class);

		   List results = query.list();

		   session.close();

		   return results;
	 }
	
}

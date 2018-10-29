/* Yamaha
 * Copyright (c) 2012 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......GarantiaArquivoDaoImpl.java
 *   .: Criação.....04 de Outubro de 2012
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...DAO com tarefas específicas para a entidade Documento.
 */
package br.com.yamaha.sistemagarantia.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import br.com.resource.infra.dao.hibernate.HibernateDaoImpl;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.dao.GarantiaArquivoDao;
import br.com.yamaha.sistemagarantia.model.GarantiaArquivo;
import br.com.yamaha.sistemagarantia.model.GarantiaArquivoMoto;
import br.com.yamaha.sistemagarantia.model.GarantiaArquivoNtc;

public class GarantiaArquivoDaoImpl extends HibernateDaoImpl implements GarantiaArquivoDao{
	 
	
	/** Lista os arquivos expirados
	 * 
	 * @param Integer garantiaId
	 * 
	 * @return List
	 * 	<code>List</code> lista de Arquivos.
	 * 
	 * @throws DaoException
	 */	
	 public List listFile(Integer garantiaId) throws DaoException{ 
		 
		 String sql = "   SELECT  fl.*	" +
				      "   FROM  YM_SG_TAB_GARANTIA_ARQUIVO fl " +
					  "   WHERE fl.GARANTIA_ID = " + garantiaId.intValue();
		   
		   Session session = super.getSession();
		   SQLQuery query = session.createSQLQuery(sql);

		   query.addEntity(GarantiaArquivo.class);
 
		   List results = query.list();

		   session.close();    

		   return results;
	 }
	 
	 /** Recupera um arquivo de moto
	  * 
	  * @param Integer garantiaId
	  * 
	  * @return byte[]
	  * 	<code>GarantiaArquivoMoto</code> Arquivo de moto.
	  * 
	  * @throws DaoException
	  */	
	 public byte[] getFileMoto(GarantiaArquivo garantiaArquivo) throws DaoException{
		 try {
			 String sql = "   SELECT  mt.*					" +
					 "   FROM  YM_SG_TAB_GARANTIA_ARQ_MOTO mt 		" +
					 "   WHERE mt.GARANTIA_ARQUIVO_ID = " + garantiaArquivo.getEntityId();

			 Session session = super.getSession();
			 SQLQuery query = session.createSQLQuery(sql);

			 query.addEntity(GarantiaArquivoMoto.class);

			 List results = query.list();
			 GarantiaArquivoMoto arqMoto = null;
			 byte[] bytes = null;
			 if(results != null && results.size() > 0) {
				 arqMoto = (GarantiaArquivoMoto)results.get(0);

				 bytes = arqMoto.getContent().getBytes(1,garantiaArquivo.getSize().intValue());
				 				 
			 }
			 session.close();
			 
			 return bytes;
			 
		 } catch (SQLException sqlEx) {			 
			 throw new DaoException(sqlEx);
		 }
	 }
	 
	 /** Recupera um arquivo de náutica
	  * 
	  * @param GarantiaArquivo garantiaArquivo
	  * 
	  * @return byte[] 
	  * 	<code>byte[]</code> Arquivo de moto.
	  * 
	  * @throws DaoException
	  */	
	 public byte[] getFileNtc(GarantiaArquivo garantiaArquivo) throws DaoException{
		 try {
			 String sql = "   SELECT  mt.*					" +
					 "   FROM  YM_SG_TAB_GARANTIA_ARQ_NTC mt 		" +
					 "   WHERE mt.GARANTIA_ARQUIVO_ID = " + garantiaArquivo.getEntityId();

			 Session session = super.getSession();
			 SQLQuery query = session.createSQLQuery(sql);

			 query.addEntity(GarantiaArquivoNtc.class);

			 List results = query.list();
			 GarantiaArquivoNtc arqNtc = null;
			 byte[] bytes = null;
			 if(results != null && results.size() > 0) {
				 arqNtc = (GarantiaArquivoNtc)results.get(0);

				 bytes = arqNtc.getContent().getBytes(1,garantiaArquivo.getSize().intValue());
				 arqNtc.setContent(bytes);
				 
			 }
			 session.close();
			 
			 return bytes;
			 
		 } catch (SQLException sqlEx) {			 
			 throw new DaoException(sqlEx);
		 }
	 }
	
}

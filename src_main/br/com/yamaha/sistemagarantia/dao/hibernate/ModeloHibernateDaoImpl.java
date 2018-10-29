package br.com.yamaha.sistemagarantia.dao.hibernate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import br.com.resource.infra.dao.hibernate.HibernateDaoImpl;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.dao.ModeloDao;
import br.com.yamaha.sistemagarantia.view.ModeloVO;

public class ModeloHibernateDaoImpl extends HibernateDaoImpl implements ModeloDao{
	
	/** Lista todos os modelos, onde dataTermino is null.
	 * 
	 * @return list
	 * 	<code>list</code> lista de modelos.
	 * 
	 * @throws DaoException
	 */	
	 public List listModelos() throws DaoException{
		
 		String sql = "SELECT distinct    modelo"     +
 		             "     , nome_modelo nomeModelo" +
		             "  FROM ym_sg_modelo"           +
		             " WHERE end_date is null"       +
		             " ORDER BY modelo"              ;
 		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		List results     = new ArrayList();		
		List listModelos = query.list();
		
		Iterator modeloIt = listModelos.iterator(); 
		
		while ( modeloIt.hasNext() ) {
		
			ModeloVO modelos = new ModeloVO();
			Object[] row = (Object[]) modeloIt.next();
			
			modelos.setModelo((String)row[0]);
			modelos.setNomeModelo((String)row[1]);
			
			results.add(modelos);
		}		
		
		session.close();
		
		return results;			
	}
	
	/** Lista todos os modelos, onde dataTermino is null.
	 * 
	 * @return list
	 * 	<code>list</code> lista de modelos.
	 * 
	 * @throws DaoException
	 */	
	 public List listModelosByLinha(Long linhaId) throws DaoException{
		
		//System.out.println("listModelosByLinha -- > " + linhaId);
		 
		String sql =	" select inclui_grafico 			" +
						" from ym_sg_modelo 				" +
						" where inclui_grafico is not null	" +
						" and   linha_id = :p_linhaId		" +
						" group by inclui_grafico			";	
	 		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		query.setLong("p_linhaId", linhaId.longValue());
			
		List results = new ArrayList();		
		Iterator it = query.list().iterator();
		
		while ( it.hasNext() ) {
		
			ModeloVO modelo = new ModeloVO();
			
			//Object[] row = (Object[]) it.next();			
			modelo.setModelo((String)it.next());
			
			results.add(modelo);
		
		}		
		
		session.close();
		
		//System.out.println("Results:" + results.size());
		
		return results;		
	}
		
	/** Lista os Modelos para o relatório de Gráficos individuais por linha. 
	 *
	 *  @return
	 *      Um objeto <code>List</code> contendo os modelos para o relatório.
	 *      
	 * @throws DaoException
	 */
	public List listGraphModelos () throws DaoException {
		
		String sql = 	" select inclui_grafico from ym_sg_modelo" +
						" where inclui_grafico is not null" +
						" group by inclui_grafico";	

		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		List results = new ArrayList();		
		Iterator it = query.list().iterator();
		
		while ( it.hasNext() ) {
		
			ModeloVO modelo = new ModeloVO();
			
			//Object[] row = (Object[]) it.next();			
			modelo.setModelo((String)it.next());
			
			results.add(modelo);
		
		}		
		
		session.close();
		
		return results;			
		
	}		
	 
	 
}

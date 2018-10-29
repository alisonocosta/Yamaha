package br.com.yamaha.sistemagarantia.dao.hibernate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import br.com.resource.infra.dao.hibernate.HibernateDaoImpl;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.dao.FornecedorDao;
import br.com.yamaha.sistemagarantia.view.FornecedorVO;

public class FornecedorHibernateDaoImpl extends HibernateDaoImpl implements FornecedorDao{
	
	/** Lista todos os fornecedores, onde a dataTermino is null.
	 * 
	 * @return list
	 * 	<code>list</code> lista de fornecedores.
	 * 
	 * @throws DaoException
	 */	
	 public List listFornecedores() throws DaoException{
		
 		String sql = "SELECT distinct fornecedor razaoSocial" +
 		             "     , codigo_fornecedor   codigo" +
		             "  FROM ym_sg_item_fornecedor" +
		             " WHERE end_date is NULL" +
		             " ORDER BY razaoSocial" ;
 		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		List results      = new ArrayList();		
		Iterator fornecedorIt = query.list().iterator();
		
		while ( fornecedorIt.hasNext() ) {
		
			FornecedorVO fornecedores = new FornecedorVO();
			Object[] row = (Object[]) fornecedorIt.next();
			
			fornecedores.setRazaoSocial((String)row[0]);
			fornecedores.setCodigo((String)row[1]);
			
			results.add(fornecedores);
		}		
		
		session.close();
		
		return results;			
	}
	 
}

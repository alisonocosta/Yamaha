package br.com.yamaha.sistemagarantia.dao.hibernate;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import br.com.resource.infra.dao.hibernate.HibernateDaoImpl;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.dao.EmpresaDao;
import br.com.yamaha.sistemagarantia.model.Empresa;

public class EmpresaHibernateDaoImpl extends HibernateDaoImpl implements EmpresaDao{
	
	/** Lista todas as empresas, onde o orgCode is not null e dataTermino is null.
	 * 
	 * @return list
	 * 	<code>list</code> lista de empresas.
	 * 
	 * @throws DaoException
	 */	
	 public List listEmpresas() throws DaoException{
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Empresa.class);
		criteria.add(Expression.isNull("dataTermino"));
		criteria.add(Expression.isNotNull("orgCode"));
		criteria.addOrder(Order.asc("orgCode"));
	 
		return super.getHibernateTemplate().findByCriteria(criteria);
	 }

}

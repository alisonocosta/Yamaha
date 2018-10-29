/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......ClienteHibernateDaoImpl.java
 *   .: Criação.....03 de abril, 18:17
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...DAO com tarefas específicas para a entidade Cliente.
 */
package br.com.yamaha.sistemagarantia.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;

import br.com.resource.infra.dao.hibernate.HibernateDaoImpl;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.dao.ClienteDao;
import br.com.yamaha.sistemagarantia.model.Cep;
import br.com.yamaha.sistemagarantia.model.Cliente;
import br.com.yamaha.sistemagarantia.model.Concessionaria;

/** Objeto DAO com tarefas específicas para a entidade <b>Cliente</b>. 
 * 
 *  @author Thiago Uriel M. Garcia
 */
public class ClienteHibernateDaoImpl extends HibernateDaoImpl implements ClienteDao {

    /** Obtém um cliente a partir de seu CPF ou CNPJ.
     * 
     *  @param cpfCnpj
     *      <code>Long</code> representando o CPF ou CNPJ do cliente.
     *  
     *  @return
     *      Um cliente relacionado ao valor fornecido ou <code>null</code>
     *      caso nenhum cliente seja encontrado.
     *  
     *  @throws DaoException
     *      Se houverem erros durante a execução estes serão lançados
     *      como uma Exception deste tipo.
     */
    public Cliente getByCpfCnpj(Long cpfCnpj) throws DaoException {
        
        DetachedCriteria criteria = super.getDetachedCriteria(Cliente.class);
        criteria.add( Expression.eq("cnpj", cpfCnpj) );

        List results = super.getHibernateTemplate().findByCriteria(criteria);

        // Se houver um registro, retornamos sendo o cliente da pesquisa.
        // Se houver mais de um registro temos um problema de banco de dados.
        // Se não houver nenhum registro, retornamos nulo.
        if ( results.size() == 1 )
            return (Cliente) results.get(0);
            
        else if ( results.size() > 1 )
            throw new DaoException("Too many entities found. There should be only one.");
        
        else
            return null;
        
    }
    
    /** Lista de clientes utilizando como critério, parte do nome.
     * 
     *  @param name
     *      <code>String</code> contendo parte do nome a ser utilizado
     *      como critério de pesquisa.
     *  
     *  
     *  @return
     *      Uma lista de entidades <code>Cliente</code>, criada a
     *      partir do nome fornecido.
     *  
     *  @throws DaoException
     *      Se houverem erros durante a execução estes serão lançados
     *      como uma Exception deste tipo.
     */
    public List listApproachedByName(String name) throws DaoException {
    	
    	String sql =" SELECT cl.*  " +
			        "   FROM ym_sg_cliente cl " +
			        "   WHERE UPPER(cl.nome) like UPPER('%" + name + "%') " + 
			        "	  AND cl.start_date <= :data " +
			        "     AND ( (cl.end_date is null) OR (cl.end_date >= :data) )" +
			        "	ORDER BY cl.nome asc";

		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		query.addEntity(Cliente.class);
		query.setParameter("data", new Date());
		
		List results = query.list();
		
		session.close();
		
		return results;
        
    	
    }

    /** Pesquisa um usuário utilizando como critério, parte do nome.
     * 
     *  @param name
     *      <code>String</code> contendo parte do nome a ser utilizado
     *      como critério de pesquisa.
     *      
     *  @param concessionaria
     *      <code>Concessionaria</code> Entidade concessionaria do usuário.
     *  
     *  @return
     *      Uma lista de entidades <code>Cliente</code>, criada a
     *      partir do nome fornecido.
     *  
     *  @throws DaoException
     *      Se houverem erros durante a execução estes serão lançados
     *      como uma Exception deste tipo.
     */
    public List searchByName(String name, Concessionaria concessionaria) throws DaoException {
       
        String sql =" SELECT cl.*  " +
			        "   FROM ym_sg_cliente cl " +
			        "      , ym_sg_cupom   cp " +
			        "	   , ym_sg_revisao rv" +
			        "	   , ym_sg_lote    lt " +
			        "   WHERE cp.cliente_id = cl.cliente_id " +
			        "	  AND cp.revisao_id = rv.revisao_id " +
			        "	  AND rv.numero_revisao = 1  " +
			        "	  AND cp.lote_id = lt.lote_id " +
			        "	  AND lt.concessionaria_id = :concessionariaId " +
			        "	  AND UPPER(cl.nome) like '%" + name.toUpperCase() + "%' " + 
			        "     AND cp.start_date <= :data " +
			        "     AND ( (cp.end_date is null) OR (cp.end_date >= :data) )" +
			        "	  AND cl.start_date <= :data " +
			        "     AND ( (cl.end_date is null) OR (cl.end_date >= :data) )" +
			        "	  AND lt.start_date <= :data " +
			        "     AND ( (lt.end_date is null) OR (lt.end_date >= :data) )" +
			        "	 ORDER BY cl.nome asc";

		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		query.addEntity(Cliente.class);
		query.setParameter("concessionariaId", concessionaria.getEntityId());
		query.setParameter("data"			 , new Date());
		
		List results = query.list();
		
		session.close();
		
		return results;
        
    }   
    
    /** Pesquisa um cliente utilizando como critério, chassi e concessionária.
     * 
     *  @param cjassi
     *      <code>String</code> contendo o chassi.
     *      
     *  @param concessionaria
     *      <code>Concessionaria</code> Entidade concessionaria do usuário.
     *  
     *  @return
     *      Uma entidade <code>Cliente</code>.
     *  
     *  @throws DaoException
     *      Se houverem erros durante a execução estes serão lançados
     *      como uma Exception deste tipo.
     */
    public Cliente getByChassi(String chassi, Concessionaria concessionaria) throws DaoException {
    	
    	 String sql =	" SELECT cl.*  " +
				        "   FROM ym_sg_cliente cl " +
				        "      , ym_sg_cupom   cp " +
				        "      , ym_sg_lote    lt " +		
				        "   WHERE cl.cliente_id = cp.cliente_id " +
				        "     AND cp.lote_id    = lt.lote_id   " +
				        "     AND lt.concessionaria_id = :concessionariaId " +
				        "     AND cp.modelo = :chassi " + 
				        "     AND cp.start_date <= :data " +
				        "     AND ( (cp.end_date is null) OR (cp.end_date >= :data) )" +
				        "	  AND cl.start_date <= :data " +
				        "     AND ( (cl.end_date is null) OR (cl.end_date >= :data) )" +
				        "	  AND lt.start_date <= :data " +
				        "     AND ( (lt.end_date is null) OR (lt.end_date >= :data) )";

			Session session = super.getSession();
			SQLQuery query  = session.createSQLQuery(sql);
			
			query.addEntity(Cliente.class);			
			query.setParameter("concessionariaId", concessionaria.getEntityId());
			query.setParameter("chassi"          , chassi.toUpperCase());
			query.setParameter("data"			 , new Date());
			
			List results = query.list();
			
			session.close();
			
		   // Se houver um registro, retornamos.
	       // Se houver mais de um registro temos um problema de banco de dados.
	       // Se não houver nenhum registro, retornamos nulo.
	       if ( results.size() == 1 )
	           return (Cliente) results.get(0);
	           
	       else if ( results.size() > 1 )
	           throw new DaoException("Too many entities found. There should be only one.");
	       
	       else
	           return null;
    }
    
    /** Obtém uma listagem de CPF/CNPJ e nomes de cliente, aproxiamda limitada de acordo
     *  com a parte inicial do CPF/CNPJ passado.
     *     
     * @param cpfCnpjPart
     *      <code>String<code> parte do CPF/CNPJ para pesquisa aproximada.
     *      
     * @param concessionaria
     * 		<code>Concessionaria</code> Entidade concessionaria do usuário. 
     * 
     * @return
     *      Uma listagem de elementos, organizada e restrita aos 
     *      delimitadores fornecidos.
     * 
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
     */
    public List listApproachedByCpfCnpj(String cpfCnpjPart) throws DaoException {
    	
    	DetachedCriteria criteria = super.getDetachedCriteria(Cliente.class);
    	
    	Long cpfCnpj = new Long(cpfCnpjPart);
    	
        criteria.add( Expression.eq("cnpj", cpfCnpj) );

        return super.getHibernateTemplate().findByCriteria(criteria);
   }
    
    /** Obtém uma listagem de CPF/CNPJ e nomes de cliente, aproxiamda limitada de acordo
     *  com a parte inicial do CPF/CNPJ passado.
     *     
     * @param cpfCnpjPart
     *      <code>String<code> parte do CPF/CNPJ para pesquisa aproximada.
     *      
     * @param concessionaria
     * 		<code>Concessionaria</code> Entidade concessionaria do usuário. 
     * 
     * @return
     *      Uma listagem de elementos, organizada e restrita aos 
     *      delimitadores fornecidos.
     * 
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
     */
    public List listApproachedByCpfCnpjByConc(String cpfCnpjPart, Concessionaria concessionaria) throws DaoException {
    	
    	/*
    	DetachedCriteria criteria = super.getDetachedCriteria(Cliente.class);
        criteria.add( Expression.eq("cnpj", new Long(cpfCnpjPart)) );

        return super.getHibernateTemplate().findByCriteria(criteria);
        */
        String sql =" SELECT distinct cl.*  " +
			        "   FROM ym_sg_cliente cl " +
			        "      , ym_sg_cupom   cp " +
			        "	   , ym_sg_lote    lt " +
			        "   WHERE cp.cliente_id = cl.cliente_id " +
			        "	  AND cp.lote_id = lt.lote_id " +
			        "	  AND lt.concessionaria_id = :concessionariaId " +
			        "	  AND cl.cnpj = :cpfCnpjPart " + 
			        "     AND cp.start_date <= :data " +
			        "     AND ( (cp.end_date is null) OR (cp.end_date >= :data) )" +
			        "	  AND cl.start_date <= :data " +
			        "     AND ( (cl.end_date is null) OR (cl.end_date >= :data) )" +
			        "	  AND lt.start_date <= :data " +
			        "     AND ( (lt.end_date is null) OR (lt.end_date >= :data) )" +
			        "	 ORDER BY cl.nome asc";

		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		query.addEntity(Cliente.class);
		query.setParameter("cpfCnpjPart"	 , new Long(cpfCnpjPart));
		query.setParameter("concessionariaId", concessionaria.getEntityId());
		query.setParameter("data"			 , new Date());
		
		List results = query.list();
		
		session.close();
		
		return results;
    	
    }
    
    /** Obtém uma listagem de nomes de cliente, limitada de acordo
     *  com a  Chassi passado.
     *     
     * @param chassi
     *      <code>String<code> Chassi.
     *      
     * @param concessionaria
     * 		<code>Concessionaria</code> Entidade concessionaria do usuário. 
     * 
     * @return
     *      Uma listagem de elementos, organizada e restrita aos 
     *      delimitadores fornecidos.
     * 
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
     */
    public List listByChassiByConc(String chassi, Concessionaria concessionaria) throws DaoException {
    	
    	String sql =" SELECT distinct cl.*  " +
			        "   FROM ym_sg_cliente cl " +
			        "      , ym_sg_cupom   cp " +
			        "	   , ym_sg_lote    lt " +
			        "   WHERE cp.cliente_id = cl.cliente_id " +
			        "	  AND cp.lote_id = lt.lote_id " +
			        "	  AND lt.concessionaria_id = :concessionariaId " +
			        "	  AND cp.modelo like '" + chassi.toUpperCase() + "%' " + 
			        "     AND cp.start_date <= :data " +
			        "     AND ( (cp.end_date is null) OR (cp.end_date >= :data) )" +
			        "	  AND cl.start_date <= :data " +
			        "     AND ( (cl.end_date is null) OR (cl.end_date >= :data) )" +
			        "	  AND lt.start_date <= :data " +
			        "     AND ( (lt.end_date is null) OR (lt.end_date >= :data) )" +
			        "	ORDER BY cl.nome asc";

		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		query.addEntity(Cliente.class);
		query.setParameter("concessionariaId", concessionaria.getEntityId());
		query.setParameter("data"			 , new Date());
		
		List results = query.list();
		
		session.close();
		
		return results;   	
    	
    }
    
    /** Obtém uma listagem de clientes que possuem cupom em uma concessionária
     * 
     * @param concessionaria
     * 	<code>Conccessionaria</code> Entidade de Concessionária 
     * 
     * @return List
     * 	<code>List</code>  Uma lista de clientes
     * 
     * @throws DaoException
     */
    public List listByConcessionaria( Concessionaria concessionaria ) throws DaoException {
    	    	 
    	String sql =" SELECT distinct cl.*  " +
			        "   FROM ym_sg_cliente cl " +
			        "      , ym_sg_cupom   cp " +
			        "	   , ym_sg_lote    lt " +
			        "   WHERE cp.cliente_id = cl.cliente_id " +
			        "	  AND cp.lote_id = lt.lote_id " +
			        "	  AND lt.concessionaria_id = :concessionariaId " +
			        "     AND cp.start_date <= :data " +
			        "     AND ( (cp.end_date is null) OR (cp.end_date >= :data) )" +
			        "	  AND cl.start_date <= :data " +
			        "     AND ( (cl.end_date is null) OR (cl.end_date >= :data) )" +
			        "	  AND lt.start_date <= :data " +
			        "     AND ( (lt.end_date is null) OR (lt.end_date >= :data) )" +
			        "	 ORDER BY cl.nome asc";

    	Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		query.addEntity(Cliente.class);
		query.setParameter("concessionariaId", concessionaria.getEntityId());
		query.setParameter("data"			 , new Date());
		
		List results = query.list();
		
		session.close();
		
		return results;
    }
    
    
    /** Valida um CEP
     * 
     * 
     * @param cep
     * 	<code>Long</code> CEP informado
     * 
     * @return boolean
     * 	<code>true</code> se for válido.
     * 
     * @throws BusinessException
     */
    public boolean isValidCep( Long cep ) throws DaoException {
    	
    	DetachedCriteria criteria = super.getDetachedCriteria(Cep.class);
        criteria.add( Expression.eq("entityId", cep) );

        List results = super.getHibernateTemplate().findByCriteria(criteria);

        // Se houver um registro, retornamos true.
        // Se houver mais de um registro temos um problema de banco de dados.
        // Se não houver nenhum registro, retornamos false.
        if ( results.size() == 1 )
            return true;
            
        else if ( results.size() > 1 )
            throw new DaoException("Too many entities found. There should be only one.");
        
        else
            return false;
    	
    }

}
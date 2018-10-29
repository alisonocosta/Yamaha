/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Infraestrutura para aplicativos Java
 *   .: Objeto......HibernateDaoImpl.java
 *   .: Criação.....03 de abril, 17:24
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Implementação mínima de um objeto Dao, 
 *                  utilizando o framework Hibernate.
 */
package br.com.resource.infra.dao.hibernate;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import br.com.resource.infra.dao.Dao;
import br.com.resource.infra.exception.DaoException;
import br.com.resource.infra.model.EntityObject;

/** Implementa um objeto DAO genérico, utilizando Hibernate.
 *  <p>
 *  Um DAO genérico possui as funcionalidades básicas de acesso ao
 *  banco de dados: obtem um registro, lista registros, salva um registro
 *  e remove um registro.
 * 
 *  @author Thiago Uriel M. Garcia
 */
public class HibernateDaoImpl extends HibernateDaoSupport implements Dao {

	/** Fornece um critério de busca independente da sessão do Hibernate, 
	 *  contendo filtranges de data de início e data de término da entidade.
	 *  
	 *  @param baseClass
	 *  	Classe base a ser utilizada para o critério de busca.
	 *  
	 *  @return
	 *  	Um critério de busca não atrelado à sessão do Hibernate.
	 */
	protected DetachedCriteria getDetachedCriteria(Class baseClass) {
	
		DetachedCriteria criteria = DetachedCriteria.forClass(baseClass);
		
		criteria.add( Expression.le("dataInicio", new Date()) );
        criteria.add( Expression.or(Expression.isNull("dataTermino"),Expression.gt("dataTermino", new Date())) );
        
        return criteria;
		
	}
	
    /** Obtém uma entidade do sistema a partir de uma classe base e um identificador,
     *  utilizandao o framework <b>Hibernate</b>.
     *  
     *  @param baseClass
     *      <code>Class</code>. Tipo de classe a ser obtida pelo método.
     *  
     *  @param entityId
     *      <code>Serializable<code>. Identificador pelo qual o objeto
     *      deverá ser pesquisado na camada de persistência da aplicação.
     *  
     *  @return
     *      Um <code>EntityObject</code> populado com os dados da 
     *      entidade específica, solicitada.
     *      
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
     */     
    public EntityObject get(Class baseClass, Serializable entityId) throws DaoException {
       
    	final Class        finalBaseClass = baseClass;
    	final Serializable finalEntityId  = entityId;
    	
    	return (EntityObject) super.getHibernateTemplate().execute(
    		
    			new HibernateCallback() {
    				
    				public Object doInHibernate(Session session) throws HibernateException {
    					
    					session.enableFilter("effectiveDate").setParameter("asOfDate", new Date());
    					
    					Criteria criteria = session.createCriteria(finalBaseClass);
    					
    					criteria.add(Expression.eq("entityId", finalEntityId));
    			        
    			        List results = criteria.list();
    			        
    			        if ( results.isEmpty() ) {
    			        	
    			        	// Não existem registros. Retornamos null.
    			        	return null;
    			        	
    			        } else if ( results.size() == 1 ) {
    			        	
    			        	// A lista possui exatamente um registro. Retornamos
    			        	// o primeiro (e único) registro.
    			        	return results.get(0);
    			        	
    			        } else {
    			        	
    			        	// Se encontrarmos mais de um registro neste método, devemos
    			        	// lançar um erro, pois sua concepção sugere que apenas um 
    			        	// registro exista na base de dados.
    			        	throw new HibernateException("Too many rows found, when only one was expected (Entity Type: " + finalBaseClass + ", key: " + finalEntityId);
    			        	
    			        }
    				}   				
    				
    			}
    			
    	);
        
    }
    
    /** Obtém uma entidade do sistema a partir de uma classe base e um parâmetro,
     *  utilizandao o framework <b>Hibernate</b>.
     *  
     *  @param baseClass
     *      <code>Class</code>. Tipo de classe a ser obtida pelo método.
     *  
     *  @param parameterName
     *      <code>Serializable<code>. Nome do parâmetro(Coluna) pelo qual o objeto
     *      deverá ser pesquisado na camada de persistência da aplicação. Com ignoreCase
     *      
     *  @param parameterValue
     *  	<code>Object<code>. Valor do parâmetro
     *  
     *  @return
     *      Um <code>EntityObject</code> populado com os dados da 
     *      entidade específica, solicitada, ou caso nenhuma entidade
     *      seja encontrada, retornará <code>null</code>.
     *      
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
     */     
    public EntityObject getByParameter(Class baseClass, String parameterName, Serializable parameterValue) throws DaoException {
        
    	DetachedCriteria criteria = getDetachedCriteria(baseClass);
        criteria.add(Expression.eq(parameterName, parameterValue));
        
        List results = super.getHibernateTemplate().findByCriteria(criteria);        
        
        if ( results.isEmpty() ) {
        	
        	// Não existem registros. Retornamos null.
        	return null;
        	
        } else if ( results.size() == 1 ) {
        	
        	// A lista possui exatamente um registro. Retornamos
        	// o primeiro (e único) registro.
        	return (EntityObject) results.get(0);
        	
        } else {
        	
        	// Se encontrarmos mais de um registro neste método, devemos
        	// lançar um erro, pois sua concepção sugere que apenas um 
        	// registro exista na base de dados.
        	throw new DaoException("Too many rows found, when only one was expected.");
        	
        }
        	
    }

    /** Lista todos os objetos do tipo fornecido pelo atributo baseClass.
     * 
     *  @param baseClass
     *      Tipo de entidade a ser lida da camada de persistência
     *      e listada para a aplicação.
     * 
     *  @return
     *      Uma lista de objetos recebida da camada de persistência,
     *      a partir da classe-base fornecida.
     *      
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
     */    
    public List listAll(Class baseClass) throws DaoException {
        
    	final Class  finalBaseClass = baseClass;
        
        List results = super.getHibernateTemplate().executeFind(
            new HibernateCallback() {
                
                public Object doInHibernate(Session session) throws HibernateException, SQLException {
                    Criteria criteria = session.createCriteria(finalBaseClass);
                    criteria.add( Expression.le("dataInicio", new Date()) );
                    criteria.add( Expression.or(Expression.isNull("dataTermino"),Expression.ge("dataTermino", new Date())) );
                    
                    return criteria.list();
                    
                }
                
            }
        );

        return results;
        
    }
    
    /** Salva ou atualiza o estado de uma entidade.
     *  <p>
     *  Se a entidade recebida não existir na camada de persistência
     *  da aplicação, será criada. Do contrário seus valores serão
     *  atualizados de acordo com as informações alteradas.
     *  
     *  @param entity
     *      <code>EntityObject</code> representando a entidade a ser
     *      criada/atualizada na camada de persistência.
     *      
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
     */    
    public void makePersistent(EntityObject entity) throws DaoException {
    	
    	try {
    		
    		super.getHibernateTemplate().saveOrUpdate(entity);
    		//super.getHibernateTemplate().flush();
    		
    	} catch ( Exception exp ) {
    		
    		throw new DaoException(exp);
    		
    	}
 
    }

    /** Remove um objeto da camada de persistência da aplicação.
     * 
     *  @param entity
     *      <code>EntityObject</code> representando a entidade a ser
     *      removida na camada de persistência.
     *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
     */    
    public void makeTransient(EntityObject entity) throws DaoException {

        super.getHibernateTemplate().delete(entity);
        //super.getHibernateTemplate().flush();
            
    }

    /** Remove um objeto da camada de persistência da aplicação.
     * 
     *  @param baseClass
     *      <code>Class</code>. Tipo de classe a ser removida pelo método.
     *  
     *  @param entityId
     *      <code>Serializable<code>. Identificador pelo qual o objeto
     *      deverá ser referenciado na camada de persistência da aplicação.
     *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
     */    
    public void makeTransient(Class baseClass, Serializable entityId) throws DaoException {

        EntityObject entity = this.get(baseClass, entityId);
        
        if (entity != null)
            makeTransient(entity);
        else
            throw new DaoException("The object type " + baseClass.getName() + " (id=" + entityId.toString() + ") does not exist." );
 
    }    
    
    /** Remove um objeto da camada de persistência da aplicação.
     * 
     *  @param baseClass
     *      <code>Class</code>. Tipo de classe a ser removida pelo método.
     *  
     *  @param entity
     *      <code>EntityObject<code>
     *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
     */    
    public void remove(EntityObject entity) throws DaoException {
        
        if (entity != null) {
          
        	entity.setDataTermino(new Date());
        	makePersistent(entity);
        	
        } else
            throw new DaoException("The object type does not exist!" );
 
    }    
    
    /** Obtém o tamanho completo da listagem de todos os registros.
     * 
     *  @param baseClass
     *      Tipo de entidade a ser lida da camada de persistência
     *      e listada para a aplicação.
     * 
     *  @return 
     *      Um inteiro determinando quantos registros serão retornados.
     *      
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
     */
    public int getFullListSize(Class baseClass) throws DaoException {
        
        DetachedCriteria criteria = DetachedCriteria.forClass(baseClass);
        criteria.setProjection(Projections.projectionList().add( Projections.rowCount() ) );
        
        Integer size = (Integer) super.getHibernateTemplate().findByCriteria(criteria).get(0); 
        
        return size.intValue();
        
    }
    
    /** Obtém o tamanho  de uma listagem dos registros de acordo com uma parâmetro informado.
     * 
     *  @param baseClass
     *      Tipo de entidade a ser lida da camada de persistência
     *      e listada para a aplicação.
     *      
     *  @param paramName
     *      Nome do parâmetro para filtro.
     *   
     *  @param objectParam
     *      Objeto que servirá como parâmetro de filtro.
     * 
     *  @return 
     *      Um inteiro determinando quantos registros serão retornados.
     *      
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
     */
    public int getListSizeByParameter(Class baseClass, String parameterName, Object ObjectParameter) throws DaoException { 
    	
    	DetachedCriteria criteria = DetachedCriteria.forClass(baseClass);
        criteria.setProjection(Projections.projectionList().add( Projections.rowCount() ) );
        criteria.add(Expression.eq(parameterName, ObjectParameter));
        
        Integer size = (Integer) super.getHibernateTemplate().findByCriteria(criteria).get(0); 
        
        return size.intValue();
    	
    }
    
    /** Obtém uma listagem de registros limitada de acordo com os dados
     *  delimitadores fornecidos e organizada.
     * 
     *  @param baseClass
     *      Tipo de entidade a ser lida da camada de persistência
     *      e listada para a aplicação.
     * @param start
     *      Registro pelo qual a listagem deverá ser iniciada.
     * @param limit
     *      Limite de registros obtidos a partir do registro de início.
     * @param orderType
     *      Tipo de ordenação, sendo 1=ascendente, 2=descendente.
     * @param orderColumn
     *      Nome da coluna a ser utilizada para organizar a listagem.
     * 
     * @return
     *      Uma listagem de elementos, organizada e restrita aos 
     *      delimitadores fornecidos.
     * 
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
     */
    public List list(Class baseClass, int start, int limit, int orderType, String orderColumn) throws DaoException {

        final Class  finalBaseClass = baseClass;
        final int    finalStart     = start;
        final int    finalLimit     = limit;
        final String finalOrderCol  = orderColumn;
        final int    finalOrderType = orderType;
        
        List results = super.getHibernateTemplate().executeFind(
            new HibernateCallback() {
                
                public Object doInHibernate(Session session) throws HibernateException, SQLException {
                    
                    Criteria criteria = session.createCriteria(finalBaseClass);
                    criteria.add( Expression.le("dataInicio", new Date()) );
                    criteria.add( Expression.or(Expression.isNull("dataTermino"),Expression.ge("dataTermino", new Date())) );
                    criteria.setFirstResult(finalStart);
                    criteria.setMaxResults(finalLimit);
                    
                    if (finalOrderType == 1)
                        criteria.addOrder( Order.asc(finalOrderCol) );
                    else
                        criteria.addOrder( Order.desc(finalOrderCol) );
                    
                    return criteria.list();
                    
                }
                
            }
        );

        return results;
        
    }
    
    /** Obtém uma listagem de registros limitada de acordo com os dados
     *  delimitadores fornecidos e organizada.
     * 
     *  @param baseClass
     *      Tipo de entidade a ser lida da camada de persistência
     *      e listada para a aplicação.
     * @param paramName
     *      Nome do parâmetro do filtro.
     * @param objectParam
     *      Objeto que servirá como parâmetro de filtro.
     * @param start
     *      Registro pelo qual a listagem deverá ser iniciada.
     * @param limit
     *      Limite de registros obtidos a partir do registro de início.
     * @param orderType
     *      Tipo de ordenação, sendo 1=ascendente, 2=descendente.
     * @param orderColumn
     *      Nome da coluna a ser utilizada para organizar a listagem.
     * 
     * @return
     *      Uma listagem de elementos, organizada e restrita aos 
     *      delimitadores fornecidos.
     * 
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
     */
    public List listByParameter(Class baseClass, String parameterName, Object objectParameter, int start, int limit, int orderType, String orderColumn) throws DaoException {

    	final Class  finalBaseClass = baseClass;
    	//final Class  finalBaseClass = Lote.class;
        final int    finalStart     = start;
        final int    finalLimit     = limit;
        final String finalOrderCol  = orderColumn;
        final int    finalOrderType = orderType;
        final Object finalObject 	= objectParameter;
        final String finalParameterName	= parameterName;
        
        List results = super.getHibernateTemplate().executeFind(
            new HibernateCallback() {
                
                public Object doInHibernate(Session session) throws HibernateException, SQLException {
                    
                    Criteria criteria = session.createCriteria(finalBaseClass);
                    criteria.setFetchMode(finalParameterName, FetchMode.JOIN);    
                    criteria.add( Expression.le("dataInicio", new Date()) );
                    criteria.add( Expression.or(Expression.isNull("dataTermino"),Expression.ge("dataTermino", new Date())) );
                    criteria.add(Expression.eq( finalParameterName, finalObject ) );
                    criteria.setFirstResult(finalStart);
                    criteria.setMaxResults(finalLimit);
                    
                    if (finalOrderType == 1)
                        criteria.addOrder( Order.asc(finalOrderCol) );
                    else
                        criteria.addOrder( Order.desc(finalOrderCol) );  
                    
                    return criteria.list();
                    
                }
                
            }
        );

        return results;
        
    }
    
    /** Obtém uma listagem de registros limitada de acordo o parâmetro passado.
     * 
     *  @param baseClass
     *      Tipo de entidade a ser lida da camada de persistência
     *      e listada para a aplicação.
     * @param paramName
     *      Nome do parâmetro do filtro.
     * @param objectParam
     *      Objeto que servirá como parâmetro de filtro.
     * 
     * @return
     *      Uma listagem de elementos, organizada e restrita aos 
     *      delimitadores fornecidos.
     * 
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
     */
    public List listByParameter(Class baseClass, String parameterName, Object objectParameter) throws DaoException {
    	
    	final Class  finalBaseClass = baseClass;
        final Object finalObject 	= objectParameter;
        final String finalParameterName	= parameterName;
        
        List results = super.getHibernateTemplate().executeFind(
            new HibernateCallback() {
                
                public Object doInHibernate(Session session) throws HibernateException, SQLException {
                    
                    Criteria criteria = session.createCriteria(finalBaseClass);
                    criteria.setFetchMode(finalParameterName, FetchMode.JOIN);  
                    criteria.add( Expression.le("dataInicio", new Date()) );
                    criteria.add( Expression.or(Expression.isNull("dataTermino"),Expression.ge("dataTermino", new Date())) );
                    criteria.add(Expression.eq( finalParameterName, finalObject ) );
                    criteria.addOrder( Order.desc("entityId") ); 
                    
                    return criteria.list();
                    
                }
                
            }
        );

        return results;
    	
    }
    
    /** Obtém uma listagem de registros limitada de acordo o parâmetro passado.
     * 
     *  @param baseClass
     *      Tipo de entidade a ser lida da camada de persistência
     *      e listada para a aplicação.
     * @param paramName
     *      Nome do parâmetro do filtro.
     * @param objectParam
     *      Objeto que servirá como parâmetro de filtro.
     * 
     * @return
     *      Uma listagem de elementos, organizada e restrita aos 
     *      delimitadores fornecidos.
     * 
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
     */
    public List listByParameter(Class baseClass, String parameterName, Object objectParameter, String orderField , String order) throws DaoException {
    	
    	final Class  finalBaseClass 	= baseClass;
        final Object finalObject 		= objectParameter;
        final String finalParameterName	= parameterName;
        final String finalOrderField 	= orderField;
        final String finalOrder 		= order;
        
        List results = super.getHibernateTemplate().executeFind(
            new HibernateCallback() {
                
                public Object doInHibernate(Session session) throws HibernateException, SQLException {
                	
                	session.enableFilter("effectiveDate").setParameter("asOfDate", new Date());
                    
                    Criteria criteria = session.createCriteria(finalBaseClass);
                    criteria.setFetchMode(finalParameterName, FetchMode.JOIN); 
                    criteria.add( Expression.le("dataInicio", new Date()) );
                    criteria.add( Expression.or(Expression.isNull("dataTermino"),Expression.ge("dataTermino", new Date())) );
                    criteria.add(Expression.eq( finalParameterName, finalObject ) );
                    
                    if ( "ASC".equals(finalOrder.toUpperCase()) )
                    	criteria.addOrder( Order.asc(finalOrderField) ); 
                    else	
                    	criteria.addOrder( Order.desc(finalOrderField) ); 
                    
                    return criteria.list();
                    
                }
                
            }
        );

        return results;
    	
    }
    
    /** Obtém uma listagem de registros aproxiamda limitada de acordo o parâmetro passado.
     * 
     *  @param baseClass
     *      Tipo de entidade a ser lida da camada de persistência
     *      e listada para a aplicação.
     * @param parameterName
     *      Nome do parâmetro do filtro.
     *      
     * @param objectParameter
     *      Objeto que servirá como parâmetro de filtro.
     *      
     * @param fieldName
     * 		Nome do campo para busca aproximada
     * 
     * @param valuePart
     * 		Valor para busca aproximada
     * 
     * @return
     *      Uma listagem de elementos, organizada e restrita aos 
     *      delimitadores fornecidos.
     * 
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
     */
    public List listApproachedByParameter(Class baseClass, String parameterName, Object objectParameter, String fieldName, String valuePart) throws DaoException {
    	
    	final Class  finalBaseClass 	= baseClass;
        final Object finalObject 		= objectParameter;
        final String finalParameterName	= parameterName;
        final String finalFieldName		= fieldName;
        final String finalValuePart		= valuePart;
        List results = super.getHibernateTemplate().executeFind(
            new HibernateCallback() {
                
                public Object doInHibernate(Session session) throws HibernateException, SQLException {
                    
                    Criteria criteria = session.createCriteria(finalBaseClass);
                    criteria.setFetchMode(finalParameterName, FetchMode.JOIN); 
                    criteria.add( Expression.le("dataInicio", new Date()) );
                    criteria.add( Expression.or(Expression.isNull("dataTermino"),Expression.ge("dataTermino", new Date())) );
                    criteria.add(Expression.eq( finalParameterName, finalObject ) );
                    criteria.add(Expression.ilike(finalFieldName, finalValuePart + "%"));
                    criteria.addOrder( Order.asc(finalFieldName) ); 
                    
                    return criteria.list();
                    
                }
                
            }
        );

        return results;
    	
    }

    /** Lista todos os objetos do tipo fornecido pelo atributo baseClass.
     * 
     *  @param baseClass
     *      Tipo de entidade a ser lida da camada de persistência
     *      e listada para a aplicação.
     * @param orderType
     *      Tipo de ordenação, sendo 1=ascendente, 2=decrescente.
     * @param orderColumn
     *      Nome da coluna a ser utilizada para organizar a listagem. 
     * 
     *  @return
     *      Uma lista de objetos recebida da camada de persistência,
     *      a partir da classe-base fornecida.
     *      
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
     */    
	public List orderedList(Class baseClass, int orderType, String orderColumn) throws DaoException {
		
		DetachedCriteria criteria = getDetachedCriteria( baseClass );
        if (orderType == 1)
            criteria.addOrder( Order.asc(orderColumn) );
        else
            criteria.addOrder( Order.desc(orderColumn) );	
        
        return super.getHibernateTemplate().findByCriteria(criteria);
        
	}
    
}
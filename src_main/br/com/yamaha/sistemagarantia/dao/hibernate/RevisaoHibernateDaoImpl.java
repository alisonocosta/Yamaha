/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RevisaoHibernateDaoImpl.java
 *   .: Cria��o.....14 de maio, 16:06
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...DAO com tarefas espec�ficas para a entidade Revisao.
 */
package br.com.yamaha.sistemagarantia.dao.hibernate;

import java.util.Iterator;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import br.com.resource.infra.dao.hibernate.HibernateDaoImpl;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.controller.helper.ControllerHelper;
import br.com.yamaha.sistemagarantia.dao.RevisaoDao;
import br.com.yamaha.sistemagarantia.model.Cupom;
import br.com.yamaha.sistemagarantia.model.Revisao;

/** Objeto DAO com tarefas espec�ficas para a entidade <b>Revisao</b>. 
 * 
 *  @author Edson Luiz Sumensari
 */
public class RevisaoHibernateDaoImpl extends HibernateDaoImpl implements RevisaoDao {

	/** Obt�m uma listagem de registros aproxiamda limitada de acordo o par�metro passado.
     *    
     * @param paramName
     *      Nome do par�metro do filtro.
     *      
     * @param valuePart
     *      valor que servir� como par�metro de filtro.
     * 
     * @return
     *      Uma listagem de elementos, organizada e restrita aos 
     *      delimitadores fornecidos.
     * 
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
     */
    public List listApproachedByChassi( String valuePart) throws DaoException {
    	
    	DetachedCriteria criteria = super.getDetachedCriteria(Revisao.class);
        criteria.add( Expression.ilike("modelo", valuePart.toUpperCase()+"%") );
        criteria.addOrder( Order.asc("numeroRevisao") );

        return super.getHibernateTemplate().findByCriteria(criteria);
    	
    }
    
    /** Obt�m uma listagem de revis�es ainda n�o realizadas para o chassi informado.
     *    
     * @param chassi
     *      Chassi do produto.
     * 
     * 
     * @return
     *      Uma listagem de elementos, organizada e restrita aos 
     *      delimitadores fornecidos.
     * 
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
     */
    public List listNewByModel( String chassi ) throws DaoException {
    	    	
    	DetachedCriteria criteriaCp = super.getDetachedCriteria(Cupom.class);
    	criteriaCp.add( Expression.eq("chassi", chassi.toUpperCase()) );
    	criteriaCp.addOrder( Order.asc("revisao") );
    	
    	List cupons = super.getHibernateTemplate().findByCriteria(criteriaCp);   	
    	
    	DetachedCriteria criteria = super.getDetachedCriteria(Revisao.class);
        criteria.add( Expression.eq("modelo", ControllerHelper.getModeloByChassi(chassi)) );
        
        if ( cupons != null ) {
        	
	        Iterator itCups = cupons.iterator();
	        
	        while ( itCups.hasNext() ) {        	
	        	
	        	Cupom cupom = (Cupom)itCups.next();	        	
	        	criteria.add( Expression.ne("entityId", cupom.getRevisao().getEntityId()) );
	        	
	        }
        }        
        criteria.addOrder( Order.asc("numeroRevisao") );
        
        List revs = super.getHibernateTemplate().findByCriteria(criteria);
        
        return revs;
    	
    }
    
    /** Obt�m uma entidade da pr�xima revis�o para o mode informado, revis�o ainda n�o realizada.
     *    
     * @param chassi
     *      Chassi do produto.
     * 
     * 
     * @return
     *      Uma entidade de Revisao
     * 
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
     */
    public Revisao getNextByChassi( String chassi ) throws DaoException {
    	    	
    	List results = this.listNewByModel(chassi);
    	Revisao rev  = null;
    	
    	if(results != null && results.size() > 0) {    		
    		rev = (Revisao) results.get(0);    		
    	}
    	
    	return rev;
    }
    
    /** Obt�m uma listagem de registros limitada de acordo o par�metro passado.
     *    
     * @param paramName
     *      Nome do par�metro do filtro.
     *      
     * @param modelo
     *      valor que servir� como par�metro de filtro.
     * 
     * @return
     *      Uma listagem de elementos, organizada e restrita aos 
     *      delimitadores fornecidos.
     * 
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
     */
    public List listByModel( String modelo ) throws DaoException {
    	
    	DetachedCriteria criteria = super.getDetachedCriteria(Revisao.class);
        criteria.add( Expression.eq("modelo", modelo.toUpperCase()) );
        criteria.addOrder( Order.asc("numeroRevisao") );

        return super.getHibernateTemplate().findByCriteria(criteria);
    	
    }
    
    /** Obt�m uma entidade de Revisao de acordo com o modelo - 5 primeiras posi��es do chassi e
     *  o n�mero da revis�o desejada
     *    
     * @param String model
     *      modelo.
     *      
     * @param Long numberRevision
     *      valor que servir� como par�metro de filtro da Revis�o.
     * 
     * @return Revisao
     *      Uma entitydade de Revisao.
     * 
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
     */
    public Revisao getByModel(String model, Long numberRevision) throws DaoException {
    	    	
    	DetachedCriteria criteria = super.getDetachedCriteria(Revisao.class);
        criteria.add( Expression.eq("modelo"		, model.toUpperCase()) );
        criteria.add( Expression.eq("numeroRevisao" , numberRevision) );

        List results = super.getHibernateTemplate().findByCriteria(criteria);

        // Se houver um registro, retornamos sendo o Revisao da pesquisa.
        // Se houver mais de um registro temos um problema de banco de dados.
        // Se n�o houver nenhum registro, retornamos nulo.
        if ( results.size() == 1 )
            return (Revisao) results.get(0);
            
        else if ( results.size() > 1 )
            throw new DaoException("Too many entities found. There should be only one.");
        
        else
            return null;
    	
    }
    
}
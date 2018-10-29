/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......FeriadoDataHibernateDaoImpl.java
 *   .: Criação.....25 de maio, 14:17
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...DAO com tarefas específicas para a entidade FeriadoData.
 */
package br.com.yamaha.sistemagarantia.dao.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import br.com.resource.infra.dao.hibernate.HibernateDaoImpl;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.dao.FeriadoDataDao;
import br.com.yamaha.sistemagarantia.model.FeriadoData;

/** Objeto DAO com tarefas específicas para a entidade <b>FeriadoData</b>. 
 * 
 *  @author Edson Luiz Sumensari
 */
public class FeriadoDataHibernateDaoImpl extends HibernateDaoImpl implements FeriadoDataDao {

	/** Obtém uma listagem de registros limitada de acordo com o interval de data informado.
     *    
     * @param startDate
     *      <code>Date</code> Data inicial para filtro.
     *      
     * @param finalDate
     *      <code>Date</code> Data final do filtro.
     * 
     * @return
     *      Uma listagem de elementos, organizada e restrita aos 
     *      delimitadores fornecidos.
     * 
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
     */
    public List listDateBetween(Date startDate, Date finalDate) throws DaoException {
    	
    	
    	DetachedCriteria criteria = super.getDetachedCriteria(FeriadoData.class);
    	
        criteria.add( Expression.between("dataFeriado", startDate, finalDate));
        criteria.addOrder( Order.asc("dataFeriado") );
        
        List feriados = super.getHibernateTemplate().findByCriteria(criteria);
        List dates    = new ArrayList();
        Iterator it   = feriados.iterator();
        
        while ( it.hasNext() ) {
        	
        	FeriadoData feriado = (FeriadoData)it.next();
        	dates.add(feriado.getDataFeriado());
        	
        }
        
        if ( dates.isEmpty() ) {
        	
        	dates = null;
        }

        return dates;
    	
    }
    
}
/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......EstadoHibernateDaoImpl.java
 *   .: Cria��o.....01 de Agosto de 2009
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...DAO com tarefas espec�ficas para a entidade Estado.
 */
package br.com.yamaha.sistemagarantia.dao.hibernate;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;

import br.com.resource.infra.dao.hibernate.HibernateDaoImpl;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.dao.EstadoDao;
import br.com.yamaha.sistemagarantia.model.Estado;

/** Objeto DAO com tarefas espec�ficas para a entidade <b>Estado</b>. 
 * 
 *  @author Edson Luiz Sumensari
 */
public class EstadoHibernateDaoImpl extends HibernateDaoImpl implements EstadoDao {

	/** Obt�m uma estados para valida��o.
     * 
     *  @param estados
     *      <code>List</code>
     *  
     *  @return
     *      Um ddd relacionado ao valor fornecido ou <code>null</code>
     *      caso nenhum ddd seja encontrado.
     *  
     *  @throws DaoException
     *      Se houverem erros durante a execu��o estes ser�o lan�ados
     *      como uma Exception deste tipo.
     */
    public List listAllOrderUf() throws DaoException {
        
        DetachedCriteria criteria = super.getDetachedCriteria(Estado.class);
        criteria.addOrder(Order.asc("estado") );

        return  super.getHibernateTemplate().findByCriteria(criteria);

        
    }
}
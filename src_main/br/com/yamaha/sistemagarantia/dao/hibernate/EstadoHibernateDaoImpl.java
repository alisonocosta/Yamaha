/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......EstadoHibernateDaoImpl.java
 *   .: Criação.....01 de Agosto de 2009
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...DAO com tarefas específicas para a entidade Estado.
 */
package br.com.yamaha.sistemagarantia.dao.hibernate;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;

import br.com.resource.infra.dao.hibernate.HibernateDaoImpl;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.dao.EstadoDao;
import br.com.yamaha.sistemagarantia.model.Estado;

/** Objeto DAO com tarefas específicas para a entidade <b>Estado</b>. 
 * 
 *  @author Edson Luiz Sumensari
 */
public class EstadoHibernateDaoImpl extends HibernateDaoImpl implements EstadoDao {

	/** Obtém uma estados para validação.
     * 
     *  @param estados
     *      <code>List</code>
     *  
     *  @return
     *      Um ddd relacionado ao valor fornecido ou <code>null</code>
     *      caso nenhum ddd seja encontrado.
     *  
     *  @throws DaoException
     *      Se houverem erros durante a execução estes serão lançados
     *      como uma Exception deste tipo.
     */
    public List listAllOrderUf() throws DaoException {
        
        DetachedCriteria criteria = super.getDetachedCriteria(Estado.class);
        criteria.addOrder(Order.asc("estado") );

        return  super.getHibernateTemplate().findByCriteria(criteria);

        
    }
}
/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......EstadoDDDHibernateDaoImpl.java
 *   .: Criação.....26 de julho de 2009, 12:03
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...DAO com tarefas específicas para a entidade EstadoDDD.
 */
package br.com.yamaha.sistemagarantia.dao.hibernate;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;

import br.com.resource.infra.dao.hibernate.HibernateDaoImpl;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.dao.EstadoDddDao;
import br.com.yamaha.sistemagarantia.model.EstadoDdd;

/** Objeto DAO com tarefas específicas para a entidade <b>EstadoDDD</b>. 
 * 
 *  @author Edson Luiz Sumensari
 */
public class EstadoDddHibernateDaoImpl extends HibernateDaoImpl implements EstadoDddDao {

	/** Obtém um ddd para validação.
     * 
     *  @param ddd
     *      <code>Long</code> representando ddd para um telefone.
     *  
     *  @return
     *      Um estadoDdd relacionado ao valor fornecido ou <code>null</code>
     *      caso nenhum ddd seja encontrado.
     *  
     *  @throws DaoException
     *      Se houverem erros durante a execução estes serão lançados
     *      como uma Exception deste tipo.
     */
    public EstadoDdd getByDdd(Long ddd) throws DaoException {
        
        DetachedCriteria criteria = super.getDetachedCriteria(EstadoDdd.class);
        criteria.add( Expression.eq("ddd", ddd) );

        List results = super.getHibernateTemplate().findByCriteria(criteria);

        // Se houver um registro, retornamos sendo o DDD da pesquisa.
        // Se houver mais de um registro temos um problema de banco de dados.
        // Se não houver nenhum registro, retornamos nulo.
        if ( results.size() == 1 )
            return (EstadoDdd) results.get(0);
            
        else if ( results.size() > 1 )
            throw new DaoException("Too many entities found. There should be only one.");
        
        else
            return null;
        
    }
    
    /** Obtém um ddd para validação.
     * 
     *  @param ddd
     *      <code>Long</code> representando ddd para um telefone.
     *      
     *  @param estadoId
     *      <code>Long</code> representando o id do estado.
     *  
     *  @return
     *      Um estadoDdd relacionado ao valor fornecido ou <code>null</code>
     *      caso nenhum ddd seja encontrado.
     *  
     *  @throws DaoException
     *      Se houverem erros durante a execução estes serão lançados
     *      como uma Exception deste tipo.
     */
    public EstadoDdd getByEstado(Long ddd, Long estadoId) throws DaoException {
        
        DetachedCriteria criteria = super.getDetachedCriteria(EstadoDdd.class);
        criteria.add( Expression.eq("ddd", ddd) );
        criteria.add( Expression.eq("entityId", estadoId) );

        List results = super.getHibernateTemplate().findByCriteria(criteria);

        // Se houver um registro, retornamos sendo o DDD da pesquisa.
        // Se houver mais de um registro temos um problema de banco de dados.
        // Se não houver nenhum registro, retornamos nulo.
        if ( results.size() == 1 )
            return (EstadoDdd) results.get(0);
            
        else if ( results.size() > 1 )
            throw new DaoException("Too many entities found. There should be only one.");
        
        else
            return null;
        
    }
}
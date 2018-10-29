/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......AutEspecSGHibernateDaoImpl.java
 *   .: Criação.....15 de junho, 13:24
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...DAO com tarefas específicas para a entidade AutorizacaoEspecialSG.
 */
package br.com.yamaha.sistemagarantia.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;

import br.com.resource.infra.dao.hibernate.HibernateDaoImpl;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.dao.AutorizacaoEspecialSGDao;
import br.com.yamaha.sistemagarantia.model.AutorizacaoEspecialSG;

/** Objeto DAO com tarefas específicas para a entidade <b>AutorizacaoEspecialSG</b>. 
 * 
 *  @author Edson Luiz Sumensari
 */
public class AutEspecSGHibernateDaoImpl extends HibernateDaoImpl implements AutorizacaoEspecialSGDao {

	/** Pesquisa um AutorizacaoEspecialSG utilizando como critério, 
     *  datas e usuários de criação e  atualização iguais.
     * 
     *  @param chassi
     *      <code>String</code> contendo parte do nome a ser utilizado
     *      como critério de pesquisa.
     *
     *  @param numero
     *      <code>String</code> contendo o número da Aut. Esp. a ser utilizado
     *      como critério de pesquisa.
     *  
     *  @return
     *      Uma entidade <code>AutorizacaoEspecialSG</code>, criada a
     *      partir do chassi e número fornecidos.
     *  
     *  @throws DaoException
     *      Se houverem erros durante a execução estes serão lançados
     *      como uma Exception deste tipo.
     */
    public AutorizacaoEspecialSG getByChassiAndNum(String chassi, String numero, Serializable concessionaria_id) throws DaoException {
    	
    	DetachedCriteria criteria = super.getDetachedCriteria(AutorizacaoEspecialSG.class);
 	    criteria.add( Expression.eq("chassi"         	, chassi.toUpperCase()) );
 	    criteria.add( Expression.eq("numAutorizacao" 	, numero).ignoreCase() );
 	    criteria.add( Expression.eq("concessionariaId" 	, concessionaria_id) );
 	    
 	    criteria.add( Expression.eq("statusUso"      , "N") );

        List results = super.getHibernateTemplate().findByCriteria(criteria);
        
        // Se houver um registro, retornamos.
        // Se houver mais de um registro temos um problema de banco de dados.
        // Se não houver nenhum registro, retornamos nulo.
        if ( results.size() == 1 )
            return (AutorizacaoEspecialSG) results.get(0);
            
        else if ( results.size() > 1 )
            throw new DaoException("Too many entities found. There should be only one.");
        
        else
            return null;	
    	
    	
    }

}
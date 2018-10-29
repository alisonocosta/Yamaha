/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......AutEspecSGHibernateDaoImpl.java
 *   .: Cria��o.....15 de junho, 13:24
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...DAO com tarefas espec�ficas para a entidade AutorizacaoEspecialSG.
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

/** Objeto DAO com tarefas espec�ficas para a entidade <b>AutorizacaoEspecialSG</b>. 
 * 
 *  @author Edson Luiz Sumensari
 */
public class AutEspecSGHibernateDaoImpl extends HibernateDaoImpl implements AutorizacaoEspecialSGDao {

	/** Pesquisa um AutorizacaoEspecialSG utilizando como crit�rio, 
     *  datas e usu�rios de cria��o e  atualiza��o iguais.
     * 
     *  @param chassi
     *      <code>String</code> contendo parte do nome a ser utilizado
     *      como crit�rio de pesquisa.
     *
     *  @param numero
     *      <code>String</code> contendo o n�mero da Aut. Esp. a ser utilizado
     *      como crit�rio de pesquisa.
     *  
     *  @return
     *      Uma entidade <code>AutorizacaoEspecialSG</code>, criada a
     *      partir do chassi e n�mero fornecidos.
     *  
     *  @throws DaoException
     *      Se houverem erros durante a execu��o estes ser�o lan�ados
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
        // Se n�o houver nenhum registro, retornamos nulo.
        if ( results.size() == 1 )
            return (AutorizacaoEspecialSG) results.get(0);
            
        else if ( results.size() > 1 )
            throw new DaoException("Too many entities found. There should be only one.");
        
        else
            return null;	
    	
    	
    }

}
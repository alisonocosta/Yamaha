/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......CupomHibernateDaoImpl.java
 *   .: Criação.....31 de maio, 13:02
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...DAO com tarefas específicas para a entidade Cupom.
 */
package br.com.yamaha.sistemagarantia.dao.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import br.com.resource.infra.dao.hibernate.HibernateDaoImpl;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.dao.CupomDao;
import br.com.yamaha.sistemagarantia.model.Concessionaria;
import br.com.yamaha.sistemagarantia.model.Cupom;
import br.com.yamaha.sistemagarantia.model.Faturamento;
import br.com.yamaha.sistemagarantia.model.Linha;
import br.com.yamaha.sistemagarantia.model.Lote;
import br.com.yamaha.sistemagarantia.model.Revisao;
import br.com.yamaha.sistemagarantia.model.StatusGarantia;
import br.com.yamaha.sistemagarantia.model.StatusLote;
import br.com.yamaha.sistemagarantia.model.ValorServico;

/** Objeto DAO com tarefas específicas para a entidade <b>Cupom</b>. 
 * 
 *  @author Edson Luiz Sumensari
 */
public class CupomHibernateDaoImpl extends HibernateDaoImpl implements CupomDao {
	
	/** Obtém uma lista de cupons de um determinado lote.
	 * 
	 *  @param loteId
	 *  	<code>Serializable</code> id do lote
	 *  
	 *  @throws DaoException
	 *      Se houverem erros durante a execução estes serão lançados
	 *      como uma Exception deste tipo.
	 */
	public List listCuponsByLote( Serializable loteId ) throws DaoException {

		DetachedCriteria criteria = super.getDetachedCriteria(Cupom.class);
		criteria.createAlias("lote", "lote");
		criteria.createAlias("revisao", "revisao");
		criteria.add( Expression.eq("lote.entityId", loteId) );
		criteria.addOrder( Order.asc("chassi") );
		criteria.addOrder( Order.asc("revisao") );
		criteria.addOrder( Order.desc("dataRevisao") );

		List results = super.getHibernateTemplate().findByCriteria(criteria);

		//System.out.println("Tamanho Cupons:" + results.size());

		return results;	   

	}

	/** Obtém uma lista de cupons para relacionar com notas fiscais.
	 *  
	 *  @throws DaoException
	 *      Se houverem erros durante a execução estes serão lançados
	 *      como uma Exception deste tipo.
	 */
	public List listCupons( Concessionaria concessionaria, StatusLote statusLote ) throws DaoException {

		DetachedCriteria criteria = super.getDetachedCriteria(Cupom.class);
		criteria.createAlias("lote", "lote");
		criteria.add( Expression.eq("lote.statusLote.entityId"      , statusLote.getEntityId()) );
		criteria.add( Expression.eq("lote.tipoLote.entityId"        , new Long(1)) );
		criteria.add( Expression.eq("lote.concessionaria.entityId"  , concessionaria.getEntityId()) );
		// Status do cupom para lançamento de notas
		// Até 26/06/2007 era 6, o Pedro pediu para mudar para 2.
		//criteria.add( Expression.eq("this.statusMovId", new Long(6)) );
		criteria.add( Expression.eq("statusMovId"  , new Long(2)) );
		criteria.add( Expression.isEmpty("notas") );
		criteria.addOrder( Order.desc("entityId") );


		List results = super.getHibernateTemplate().findByCriteria(criteria);

		//System.out.println("Tamanho Cupons:" + results.size());

		return results;	   

	}
   
   /** Verifica se existem cupons com status sem notas lançadas
    * 
    * @param lote
    * 	<code>Lote</code> Lote para verificação
    * 
    * @return boolean
    * 	<code>boolean</code> true quando existir cupom
    * 
    * @throws DaoException
    */
   public boolean getCuponsByLote( Lote lote ) throws DaoException {
	   
	   boolean isValue = false;
	   
	   List statusMov = new ArrayList();
	   statusMov.add(StatusGarantia.STATUS_AGUARDANDO_DIGITACAO_NOTA);
	   statusMov.add(StatusGarantia.STATUS_NF_DIVERGENTE);
	   
	   DetachedCriteria criteria = super.getDetachedCriteria(Cupom.class);
	   criteria.add( Expression.eq("lote"            , lote ) );
	   criteria.add( Expression.isEmpty("notas") );
       criteria.add( Expression.in("this.statusMovId", statusMov) );

       List results = super.getHibernateTemplate().findByCriteria(criteria);

       // Se houver um registro, retornamos true.
       if ( results.size() > 0 )
    	   isValue = true;
       
       return isValue;
	   
   }
   
   /** Retorna uma entidade de cupom de acordo com um chassi e revisao
    * 
    * @param chassi
    * 	<code>String</code> Chassi informado
    * 
    * @param linha
    * 	<code>Linha</code> Linha do produto
    * 
    * @return boolean
    * 	<code>Faturamento</code> uma entidade de Faturamento ou null se não encontrar
    * 
    * @throws DaoException
    */
   public Cupom getCupomByChassiAndRevisao( String chassi, Revisao revisao ) throws DaoException {
	   
	   DetachedCriteria criteria = super.getDetachedCriteria(Cupom.class);
       criteria.add( Expression.eq("chassi"  , chassi.toUpperCase() ) );
       criteria.add( Expression.eq("revisao" , revisao )              );

       List results = super.getHibernateTemplate().findByCriteria(criteria);

       // Se houver um registro, retornamos.
       // Se houver mais de um registro temos um problema de banco de dados.
       // Se não houver nenhum registro, retornamos nulo.
       if ( results.size() == 1 )
           return (Cupom) results.get(0);
           
       else if ( results.size() > 1 )
           throw new DaoException("Too many entities found. There should be only one.");
       
       else
           return null;
	   
   }
   
   /** Retorna uma entidade de faturamento de acordo com um chassi e linha do produto
    * 
    * @param chassi
    * 	<code>String</code> Chassi informado
    * 
    * @param linha
    * 	<code>Linha</code> Linha do produto
    * 
    * @return boolean
    * 	<code>Faturamento</code> uma entidade de Faturamento ou null se não encontrar
    * 
    * @throws DaoException
    */
   public Faturamento getFaturamentoByLinha( String chassi, Linha linha ) throws DaoException {
	   
	   DetachedCriteria criteria = super.getDetachedCriteria(Faturamento.class);
       criteria.add( Expression.eq("chassi", chassi.toUpperCase() ) );
       criteria.add( Expression.eq("linha" , linha )                );
       //criteria.add( Expression.isNull("dataTermino"));
       criteria.add( Expression.isNotNull("modelo") );
       criteria.addOrder(Order.desc("dataCriacao"));  //edson 01/07/2013

       List results = super.getHibernateTemplate().findByCriteria(criteria);
       
       /*
       // Se houver um registro, retornamos.
       // Se houver mais de um registro temos um problema de banco de dados.
       // Se não houver nenhum registro, retornamos nulo.
       if ( results.size() == 1 )
           return (Faturamento) results.get(0);
           
       else if ( results.size() > 1 )
           throw new DaoException("Too many entities found. There should be only one.");
       */
       //Quando existir mais de um Faturamento retornamos o mais recente //edson 01/07/2013
       if ( results.size() > 0 )
           return (Faturamento) results.get(0);
       else
           return null;
   }
   
   /** Retorna uma lista de entidades de faturamento de acordo com um chassi e linha do produto
    * 
    * @param chassiPart
    * 	<code>String</code> Chassi informado ou parte do valor do chassi
    * 
    * @param concessionaria
    * 	<code>Concessionaria</code> Entidade de Concessionaria 
    * 
    * @param linhaId
    * 	<code>Long</code> entityId da Linha do produto
    * 
    * @return 
    * 	<code>List</code> uma lista de entidades de Faturamento
    * 
    * @throws DaoException
    */
   public List listApproachFaturamentoByLinha( String chassiPart, Concessionaria concessionaria, Long linhaId ) throws DaoException {

	   String sql = new String();
	   
	   if ( linhaId != null ) {
		   
		   sql = "   SELECT  ft.* " +
			     "   FROM  ym_sg_faturamento ft " +
				 "   where ft.linha_id          = :linhaId " +
				 "     AND ft.concessionaria_id = :concessionariaId " +
				 "	   AND ft.chassi like '" + chassiPart.toUpperCase() + "%' " +
				 "     AND ft.created_date = (SELECT MAX(CREATED_DATE) FROM YM_SG_FATURAMENTO WHERE chassi = ft.chassi) " + //edson 01/07/2013
				 " 	   AND ft.modelo is not null " + 
				 "     AND ft.start_date <= :data " +
				 "     AND ( (ft.end_date is null) OR (ft.end_date >= :data) )" +
				 "	 ORDER BY ft.chassi asc";
	   
	   } else {
		   
		   sql = "   SELECT  ft.* " +
		         "   FROM  ym_sg_faturamento ft " +
		         "   where ft.concessionaria_id = :concessionariaId " +
		         "	   AND ft.chassi like '" + chassiPart.toUpperCase() + "%' " + 
		         "     AND ft.created_date = (SELECT MAX(CREATED_DATE) FROM YM_SG_FATURAMENTO WHERE chassi = ft.chassi) " + //edson 01/07/2013
		         " 	   AND ft.modelo is not null " + 
		         "     AND ft.start_date <= :data " +
		         "     AND ( (ft.end_date is null) OR (ft.end_date >= :data) )" +
		         "	 ORDER BY ft.chassi asc";
		   
	   }
	   
	   Session session = super.getSession();
	   SQLQuery query = session.createSQLQuery(sql);

	   query.addEntity(Faturamento.class);
	   query.setParameter("linhaId"		 , linhaId);
	   query.setParameter("concessionariaId", concessionaria.getEntityId());
	   query.setParameter("data"			 , new Date());

	   List results = query.list();

	   session.close();

	   return results;
   }
   
   /** Recupera um objeto ValorServico para o tipo revisao, modelo e região
    * 
    * @param revisao
    * 	<code>Revisao</code> Entidade de revisão.
    * 
    * @param modelo
    * 	<code>String</code> Modelo do produto - 5 primeiras posições do chassi
    * 
    * @param regiao
    * 	<code>Long</code> regiao_moto ou regiao_nautica da concessionária
    * 
    * @return ValorServico
    * 	Retorna <code>null</code> quando existir valor conforme os parâmetros enviados
    * 
    * @throws DaoException
    *      Se houverem erros durante a execução estes serão lançados
    *      como uma Exception deste tipo.
    */
   public ValorServico getValorServico(Revisao revisao, String modelo, Long regiao) throws DaoException {
	   
	   DetachedCriteria criteria = super.getDetachedCriteria(ValorServico.class);
       criteria.add( Expression.eq("codigoRegiao", regiao ) );
       criteria.add( Expression.eq("modelo"      , modelo ) );
       criteria.add( Expression.eq("revisao"     , revisao ) );

       List results = super.getHibernateTemplate().findByCriteria(criteria);

       // Se houver um registro, retornamos sendo o Revisao da pesquisa.
       // Se houver mais de um registro temos um problema de banco de dados.
       // Se não houver nenhum registro, retornamos nulo.
       if ( results.size() == 1 )
           return (ValorServico) results.get(0);
           
       else if ( results.size() > 1 )
           throw new DaoException("Too many entities found. There should be only one.");
       
       else
           return null;
   }
   
   /** Obtém uma lista de chassi de uma concessionária e lote.
    * 
    * @param chassiPart
    *  <code>String</code> Chassi ou parte.
    *
    *  @param concessionaria
    *  	<code>Concessionaria</code> entidade concessionária
    *  
    *  @param loteId
    *  	<code>Long</code> Id do lote.
    *    
    *  @throws DaoException
    *      Se houverem erros durante a execução estes serão lançados
    *      como uma Exception deste tipo.
    */
   public List listApproachChassiByLote(String chassiPart, Concessionaria concessionaria, Long loteId ) throws DaoException {
	  
	   //System.out.println( " listApproachChassiByLote - chassi:" + chassiPart + " Lote: " + loteId + " Concessionaria:" + concessionaria.getEntityId());
	   
		String sql =   "   SELECT  ft.* " +
					   "   FROM  ym_sg_faturamento ft " +
					   "		,ym_sg_lote        lt " +
					   "   where ft.linha_id          = lt.linha_id " +
					   "     AND ft.concessionaria_id = lt.concessionaria_id " +
					   "     AND lt.lote_id           = :loteId " +
					   "     AND lt.concessionaria_id = :concessionariaId " +
					   "	 AND ft.chassi like '" + chassiPart.toUpperCase() + "%' " +
					   "     AND ft.created_date = (SELECT MAX(CREATED_DATE) FROM YM_SG_FATURAMENTO WHERE chassi = ft.chassi) " + //edson 01/07/2013
					   " 	 AND ft.modelo is not null " + 
					   "     AND ft.start_date <= :data " +
					   "     AND ( (ft.end_date is null) OR (ft.end_date >= :data) )" +
					   "	 AND lt.start_date <= :data " +
					   "     AND ( (lt.end_date is null) OR (lt.end_date >= :data) ) " +
					   "	 ORDER BY ft.chassi asc";
		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		query.addEntity(Faturamento.class);
		query.setParameter("loteId"			 , loteId);
		query.setParameter("concessionariaId", concessionaria.getEntityId());
		query.setParameter("data"			 , new Date());
		
		List results = query.list();
		
		session.close();
		
		return results;
	   
   }
   
   /** Implementação do método de listagem "listFaturamentoByLinhaAndChassi"
    *  definido na interface "CupomDao"
    */
   public List listFaturamentoByLinhaAndChassi(String chassiPart, Serializable linhaId) throws DaoException {
	   
	   //System.out.println("listFaturamentoByLinhaAndChassi - Chassi:" + chassiPart +  " - Linha:" + linhaId); 
	   
	   String sql = "   SELECT  ft.* " +
  	                "     FROM  ym_sg_faturamento ft " +
		            "    WHERE ft.linha_id          = :linhaId " +
		            "	   AND ft.chassi like '" + chassiPart.toUpperCase() + "%' " + 
		            "      AND ft.created_date = (SELECT MAX(CREATED_DATE) FROM YM_SG_FATURAMENTO WHERE chassi = ft.chassi) " + //edson 01/07/2013
		            " 	   AND ft.modelo is not null " + 
		            "      AND ft.start_date <= :data " +
		            "      AND ( (ft.end_date is null) OR (ft.end_date >= :data) )" +
		            "	 ORDER BY ft.chassi asc";

	   Session session = super.getSession();
	   SQLQuery query = session.createSQLQuery(sql);

	   query.addEntity(Faturamento.class);
	   query.setParameter("linhaId" , (Long)linhaId);
	   query.setParameter("data"	, new Date());

	   List results = query.list();

	   session.close();

	   //System.out.println("listFaturamentoByLinhaAndChassi - results:" + results.size()); 
	   
	   return results;
	   
   }
   
   /** Retorna a última kilometragem registrada 
    * 
    * @param chassi
    * @return Long
    * @throws DaoException
    */
   public Long getLastRevision(String chassi)throws DaoException {
	   
	   String sql = "   SELECT max(its.km)" +
	   				"	FROM (" +
	   				"    		SELECT  max(cp.quilometragem) km" +
	   				"    		FROM  ym_sg_cupom cp" +
	   				"    		WHERE cp.modelo like '" + chassi.toUpperCase() + "%'" +
	   				"    		AND cp.start_date <= sysdate" +
	   				"			AND ( (cp.end_date is null) OR (cp.end_date >= sysdate) )" +
	   				"			UNION ALL" +
	   				"			SELECT MAX(gh.quilometragem) km" +
	   				"			FROM ym_sg_garantia_header gh " +
	   				"			WHERE gh.modelo like '" + chassi.toUpperCase() + "%'" +
	   				"			AND gh.start_date <= sysdate" +
	   				"			AND ( (gh.end_date is null) OR (gh.end_date >= sysdate))" +
	   				"	)its";

	   Session session = super.getSession();
	   SQLQuery query = session.createSQLQuery(sql);

	   List results = query.list();
	   
       String km = "0";
	   if ( results.size() > 0 )
	   	if ( results.get(0)!= null  )
	   		km = results.get(0).toString();

	   session.close();
	   
	   return Long.valueOf(km);
	   
   }
   
   /** Retorna a última kilometragem registrada, com exceção da garantia enviada
    *  Para os casos de alteração da quilometragem da Garantia
    *  
    * @param chassi
    * @param garantiaId
    * @return Long
    * @throws DaoException
    */
   public Long getLastRevision(String chassi, Long garantiaId)throws DaoException {
	   
	   String sql = "   SELECT max(its.km)" +
	   				"	FROM (" +
	   				"    		SELECT  max(cp.quilometragem) km" +
	   				"    		FROM  ym_sg_cupom cp" +
	   				"    		WHERE cp.modelo like '" + chassi.toUpperCase() + "%'" +
	   				"    		AND cp.start_date <= sysdate" +
	   				"			AND ( (cp.end_date is null) OR (cp.end_date >= sysdate) )" +
	   				"			UNION ALL" +
	   				"			SELECT MAX(gh.quilometragem) km" +
	   				"			FROM ym_sg_garantia_header gh " +
	   				"			WHERE gh.modelo like '" + chassi.toUpperCase() + "%'" +
	   				"			AND gh.garantia_id != :garantiaId " +	
	   				"			AND gh.start_date <= sysdate" +
	   				"			AND ( (gh.end_date is null) OR (gh.end_date >= sysdate))" +
	   				"	)its";

	   Session session = super.getSession();
	   SQLQuery query = session.createSQLQuery(sql);
	   
	   query.setParameter("garantiaId" , garantiaId);
	   
	   List results = query.list();
	   
       String km = "0";
	   if ( results.size() > 0 )
	   	if ( results.get(0)!= null  )
	   		km = results.get(0).toString();

	   session.close();
	   
	   return Long.valueOf(km);
	   
   }
   
   /** Retorna um cupom de revisao de acordo com o número da revisão e para modelo informados
    * 
    * @param chassi
    * @param numeroRevisao
    * @return
    * @throws DaoException
    */
   public Cupom getCupomByRevisao(String chassi, Long numeroRevisao)throws DaoException {
	   
	   DetachedCriteria criteria = super.getDetachedCriteria(Cupom.class);
	   criteria.createAlias("revisao", "revisao");
	   criteria.add( Expression.eq("chassi"                , chassi.toUpperCase()));	   
       criteria.add( Expression.eq("revisao.numeroRevisao" , numeroRevisao));

       List results = super.getHibernateTemplate().findByCriteria(criteria);

       // Se houver um registro, retornamos sendo o Revisao da pesquisa.
       // Se houver mais de um registro temos um problema de banco de dados.
       // Se não houver nenhum registro, retornamos nulo.
       if ( results.size() == 1 )
           return (Cupom) results.get(0);
           
       else if ( results.size() > 1 )
           throw new DaoException("Too many entities found. There should be only one.");
       
       else
           return null;
	   
	   
   }
   
}
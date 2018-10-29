/* Resource Tecnologia
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......CampaignHibernateDaoImpl.java
 *   .: Criação.....21 de Julho de 2008
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...DAO com tarefas específicas para a entidade Campaign.
 */
package br.com.yamaha.sistemagarantia.dao.hibernate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.HibernateCallback;

import br.com.resource.infra.dao.hibernate.HibernateDaoImpl;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.controller.helper.ControllerHelper;
import br.com.yamaha.sistemagarantia.dao.CampaignDao;
import br.com.yamaha.sistemagarantia.model.Campaign;
import br.com.yamaha.sistemagarantia.model.CampaignPiece;
import br.com.yamaha.sistemagarantia.model.Condicao;
import br.com.yamaha.sistemagarantia.model.Faturamento;
import br.com.yamaha.sistemagarantia.model.Item;
import br.com.yamaha.sistemagarantia.model.Linha;
import br.com.yamaha.sistemagarantia.model.Servico;
import br.com.yamaha.sistemagarantia.model.Sintoma;

public class CampaignHibernateDaoImpl extends HibernateDaoImpl implements CampaignDao{
	 
	/** Lista as campanhas de um determinado produto pelo seu chassi, desde de que, 
	 *  o produto não tenha participado da campanha.
	 * 
	 * @param campaignId
	 * 		<code>Long</code> id da Campanha.	
	 * 
	 * @return campaign
	 * 	<code>Campaign</code> uma Entidade de Campaign devidamente preenchida com as peças relaciondas.
	 * 
	 * @throws DaoException
	 */	
	 public Campaign getById( Long campaignId ) throws DaoException {
		 
		 Campaign campaign = null;
		 
		 DetachedCriteria criteria = super.getDetachedCriteria(Campaign.class);
		 criteria.add( Expression.eq("entityId", campaignId    ) );
		 
		 List results = super.getHibernateTemplate().findByCriteria(criteria);

		// Se houver um registro, retornamos sendo o Revisao da pesquisa.
		// Se houver mais de um registro temos um problema de banco de dados.
		// Se não houver nenhum registro, retornamos nulo.
		if ( results.size() == 1 ) 
			campaign = (Campaign) results.get(0);

		else if ( results.size() > 1 )
			throw new DaoException("Too many entities found. There should be only one.");

		else
			return null;
		
		
		Iterator it = campaign.getPieces().iterator();
		
		while ( it.hasNext() ) {
			
			CampaignPiece cp = (CampaignPiece) it.next();
			
			criteria = super.getDetachedCriteria(Item.class);
			criteria.add( Expression.eq("entityId"     , cp.getPieceId()  ) );
			criteria.add( Expression.eq("organizationId", new Long(91)) );
			
			List resultsPiece = super.getHibernateTemplate().findByCriteria(criteria);
			
			if ( resultsPiece.size() ==  0 ) {
				criteria = super.getDetachedCriteria(Item.class);
				criteria.add( Expression.eq("entityId"     , cp.getPieceId()  ) );
				criteria.add( Expression.eq("organizationId"  , new Long(89)) );

				resultsPiece = super.getHibernateTemplate().findByCriteria(criteria);
				
			}
			// Se houver um registro, retornamos sendo o Revisao da pesquisa.
			// Se houver mais de um registro temos um problema de banco de dados.
			// Se não houver nenhum registro, retornamos nulo.
			if ( resultsPiece.size() == 1 ) 
				cp.setPiece((Item) resultsPiece.get(0));

			else if ( resultsPiece.size() > 1 )
				throw new DaoException("Too many entities found. There should be only one.");

			else
				throw new DaoException("Peça não localizada! Código:" + cp.getPieceId());
			
		}
		
		return campaign;
		 
	 }
	 
	 /** Recupera uma Campanha com base em seu ID
	  * 
	  *  @param entityId
	  *  	<code>Serializable</code> Id da Campanha.
	  *  
	  *  @return
	  *      <code>Campaign</code> Uma entidade de Campaign.
	  *  
	  *  @throws DaoException
	  *      Se houverem erros durante a execução estes serão lançados
	  *      como uma Exception deste tipo.
	  */
	 public Campaign getCampaignById(Serializable entityId) throws DaoException {

		 StringBuffer sbSql 		 = new StringBuffer();

		 sbSql.append(" SELECT ct.CAMPAIGN_ID ");
		 sbSql.append(" 		, ct.CODE_CAMPAIGN ");
		 sbSql.append(" 		, ct.MODEL_PRODUCT ");
		 sbSql.append(" 		, ct.CHASSI_SEQ_START ");
		 sbSql.append(" 		, ct.CHASSI_SEQ_END ");
		 sbSql.append(" 		, ct.VALIDITY_START_DATE ");
		 sbSql.append(" 		, ct.VALIDITY_END_DATE ");
		 sbSql.append(" 		, ct.SUBJECT_CAMPAIGN ");
		 sbSql.append(" 		, ct.SERVICE_VALUE ");
		 sbSql.append(" 		, st.SINTOMA_ID ");
		 sbSql.append(" 		, st.SINTOMA_CODE ");
		 sbSql.append(" 		, st.DESCRICAO as descSintoma ");		 
		 sbSql.append(" 		, cd.CONDICAO_ID ");
		 sbSql.append(" 		, cd.CONDICAO_CODE ");
		 sbSql.append(" 		, cd.DESCRICAO as descCondicao");
		 sbSql.append(" FROM YM_SG_CAMPAIGN_TEC_CHANGE ct, YM_SG_CONDICAO cd, YM_SG_SINTOMA st ");
		 sbSql.append(" WHERE CAMPAIGN_ID = :campaignId ");
		 sbSql.append(" AND ct.CONDITION_ID = cd.CONDICAO_ID ");
		 sbSql.append(" AND ct.SINTOMA_ID = st.SINTOMA_ID ");
		 sbSql.append(" AND ct.START_DATE <= sysdate AND (ct.END_DATE is null or ct.END_DATE > sysdate) ");

		 Session session = super.getSession();
		 SQLQuery query = session.createSQLQuery(sbSql.toString());

		 query.setParameter("campaignId" , entityId);

		 Campaign 		campaign = null;
		 Condicao       condicao = null;
		 Sintoma        sintoma  = null;
		 Servico  		servico  = null;
		 CampaignPiece 	peca 	 = null;
		 try {
			 List results = query.list();

			// Se houver um registro, retornamos sendo o Revisao da pesquisa.
			   // Se houver mais de um registro temos um problema de banco de dados.
			   // Se não houver nenhum registro, retornamos nulo.
			if ( results.size() == 1 ) {
				
				 campaign = new Campaign();
				 
				 Object[] row = (Object[]) results.get(0);
				 
				 campaign.setEntityId(Long.valueOf(row[0].toString()));
				 campaign.setCodeCampaign((String)row[1]);
				 campaign.setModelo((String)row[2]);
				 campaign.setChassiSeqStart((String)row[3]);
				 campaign.setChassiSeqFinal((String)row[4]);				 
				 campaign.setValidityStartDate(row[5] != null ? (Date)row[5] : null);
				 campaign.setValidityEndDate(row[6] != null ? (Date)row[6] : null);
				 campaign.setSubjectCampaign((String)row[7]);
				 campaign.setServiceValue(row[8] != null ? (Double.valueOf(row[8].toString())).doubleValue():0.0);
				 
				 sintoma = new Sintoma();
				 sintoma.setEntityId(Long.valueOf(row[9].toString()));
				 sintoma.setSintomaCode((String)row[10]);	
				 sintoma.setDescricao((String)row[11]);				 
				 campaign.setSintoma(sintoma);
				 
				 condicao = new Condicao();
				 condicao.setEntityId(Long.valueOf(row[12].toString()));
				 condicao.setCondicaoCode((String)row[13]);	
				 condicao.setDescricao((String)row[14]);
				 campaign.setCondition(condicao);
				 
				 //Recuperamos os Serviços para a Campanha
				 sbSql 		 = new StringBuffer();

				 sbSql.append(" SELECT sv.SERVICO_ID ");
				 sbSql.append(" 		, sv.SERVICO_CODE ");
				 sbSql.append(" 		, sv.DESCRICAO ");
				 sbSql.append(" 		, sv.LINHA_ID ");
				 sbSql.append(" FROM YM_SG_CAMPAIGN_SERVICE cs, YM_SG_SERVICO sv ");
				 sbSql.append(" WHERE cs.campaign_id = :campaignId ");
				 sbSql.append(" AND cs.service_id = sv.servico_id ");
				 sbSql.append(" AND cs.START_DATE <= sysdate AND (cs.END_DATE is null or cs.END_DATE > sysdate) ");
				 sbSql.append(" AND sv.START_DATE <= sysdate AND (sv.END_DATE is null or sv.END_DATE > sysdate) ");
				 
				 query = session.createSQLQuery(sbSql.toString());

				 query.setParameter("campaignId" , entityId);
				 Long  linhaId = null;
				 Linha linha = null;
				 
				 Iterator it = query.list().iterator();				 
				 List  services = new ArrayList();
				 
				 while ( it.hasNext() ) {
					 servico = new Servico();
					 Object[] rowSrv = (Object[]) it.next();
					 servico.setEntityId(Long.valueOf(rowSrv[0].toString()));
					 servico.setServicoCode((String)rowSrv[1]);
					 servico.setDescricao((String)rowSrv[2]);
					 linhaId = Long.valueOf(rowSrv[3].toString());
					 linha = new Linha();
					 linha.setEntityId(linhaId);
					 servico.setLinha(linha);
					 services.add(servico);
				 }
				 
				 if(!services.isEmpty())
					 campaign.setServices(services);
				 
				 
				//Recuperamos as Peças para a Campanha
				 sbSql 		 = new StringBuffer();

				 sbSql.append(" SELECT CAMPAIGN_PIECE_ID ");
				 sbSql.append(" 		, PIECE_ID ");
				 sbSql.append(" 		, QUANTITY_PIECE ");
				 sbSql.append(" 		, RECOVERED_PIECE ");
				 sbSql.append(" 		, SEND_PIECE ");
				 sbSql.append(" 		, CAUSING_PIECE ");
				 sbSql.append(" FROM YM_SG_CAMPAIGN_PIECE ");
				 sbSql.append(" WHERE campaign_id = :campaignId ");
				 sbSql.append(" AND START_DATE <= sysdate AND (END_DATE is null or END_DATE > sysdate) ");
				 
				 query = session.createSQLQuery(sbSql.toString());

				 query.setParameter("campaignId" , entityId);
				 
				 it = query.list().iterator();				 
				 List  pecas = new ArrayList();
				 
				 while ( it.hasNext() ) {
					 peca = new CampaignPiece();
					 Object[] rowPc = (Object[]) it.next();
					 peca.setEntityId(Long.valueOf(rowPc[0].toString()));
					 peca.setPieceId(Long.valueOf(rowPc[1].toString()));
					 peca.setQuantityPiece(Integer.valueOf(rowPc[2].toString()));
					 peca.setRecoveredPiece((String)rowPc[3]);
					 peca.setSendPiece((String)rowPc[4]);
					 peca.setCausingPiece((String)rowPc[5]);
					 peca.setPiece(getItemByPecaId(peca.getPieceId()));
					 pecas.add(peca);
				 }
				 
				 if(!pecas.isEmpty())
					 campaign.setPieces(pecas);
				 
			 } else if ( results.size() > 1 )
				   throw new DaoException("Too many entities found. There should be only one.");
			
		 } catch ( Exception exp ) {

			 throw new DaoException(exp);

		 }  finally {
			 session.close();
		 }

		 return campaign;
	 }
	 
	 /**
	  * Recupera um Item com base na pecaId
	  * 
	  * @param pecaId
	  * @return
	  * @throws DaoException
	  */
	 private Item getItemByPecaId(Long pecaId)throws DaoException {

		 DetachedCriteria criteria = super.getDetachedCriteria(Item.class);
		 criteria.add( Expression.eq("entityId"     , pecaId  ) );
		 criteria.add( Expression.eq("organizationId", new Long(91)) );

		 List resultsPiece = super.getHibernateTemplate().findByCriteria(criteria);

		 // Se houver um registro, retornamos sendo o Revisao da pesquisa.
		 // Se houver mais de um registro temos um problema de banco de dados.
		 // Se não houver nenhum registro, retornamos nulo.
		 if ( resultsPiece.size() == 1 ) 
			return (Item) resultsPiece.get(0);

		 else if ( resultsPiece.size() > 1 )
			 throw new DaoException("Too many entities found. There should be only one.");

		 else
			 return null;

	 }
	 
	
	 public List listCampaignForSelect(String chassi ) throws DaoException {
		 
		 List campaigns = new ArrayList();
		 
		 StringBuffer sbSql 		 = new StringBuffer();

		 sbSql.append(" SELECT faturamento_id ");
		 sbSql.append(" FROM YM_SG_FATURAMENTO ");
		 sbSql.append(" WHERE CHASSI = :chassi ");
		 sbSql.append(" AND START_DATE <= sysdate AND (END_DATE is null or END_DATE > sysdate) ");
		 
		 Session session = super.getSession();
		 SQLQuery query = session.createSQLQuery(sbSql.toString());
		 
		 query.setParameter("chassi" ,  chassi != null && !"".equals(chassi) ? chassi.toString() : chassi);
		 
		 try {

			 List resultsFat  = query.list();
			 BigDecimal faturamentoId = null;
			 if( resultsFat != null && resultsFat.size() > 0) {
				 faturamentoId = (BigDecimal) resultsFat.get(0);
			 }
			 
			 if ( faturamentoId != null) {

				 sbSql 		 = new StringBuffer();
				 sbSql.append(" SELECT ct.CAMPAIGN_ID ");
				 sbSql.append(" 		, ct.CODE_CAMPAIGN ");
				 sbSql.append(" 		, ct.MODEL_PRODUCT ");
				 sbSql.append(" 		, ct.SUBJECT_CAMPAIGN ");
				 sbSql.append(" 		, ct.ST_RECALL ");
				 sbSql.append(" 		, ct.VALIDITY_START_DATE ");
				 sbSql.append(" 		, ct.VALIDITY_END_DATE ");
				 sbSql.append(" FROM YM_SG_CAMPAIGN_TEC_CHANGE ct");
				 sbSql.append(" WHERE ct.MODEL_PRODUCT = :modelo ");
				 sbSql.append(" AND ct.CHASSI_SEQ_START <= :chassiSeq ");
				 sbSql.append(" AND ct.CHASSI_SEQ_END   >= :chassiSeq ");
				 sbSql.append(" AND ct.START_DATE <= sysdate AND (ct.END_DATE is null or ct.END_DATE > sysdate) ");
				 sbSql.append(" AND ct.CAMPAIGN_ID NOT IN ( ");
				 sbSql.append("     SELECT cp.CAMPAIGN_ID  ");
				 sbSql.append("     FROM YM_SG_CAMPAIGN_BILLING cp, YM_SG_FATURAMENTO ft ");
				 sbSql.append("     WHERE cp.BILLING_ID = ft.FATURAMENTO_ID ");
				 sbSql.append("     AND ft.FATURAMENTO_ID = :faturamento ");
				 sbSql.append("     AND cp.START_DATE <= sysdate AND (cp.END_DATE is null or cp.END_DATE > sysdate) ");
				 sbSql.append("     AND ft.START_DATE <= sysdate AND (ft.END_DATE is null or ft.END_DATE > sysdate) ");
				 sbSql.append(" ) ");
				 sbSql.append(" ORDER BY ct.CODE_CAMPAIGN DESC ");

				 query = session.createSQLQuery(sbSql.toString());

				 String chassiSeq 	  = ControllerHelper.getSequencyByChassi(chassi);
				 String chassiModelo  = ControllerHelper.getModeloByChassi(chassi);

				 query.setParameter("modelo"      , chassiModelo);
				 query.setParameter("chassiSeq"   , chassiSeq);
				 query.setParameter("faturamento" , faturamentoId);

				 Campaign 		campaign = null;

				 Iterator it = query.list().iterator();
				 
				 // Se houver um registro, retornamos sendo o Revisao da pesquisa.
				 // Se houver mais de um registro temos um problema de banco de dados.
				 // Se não houver nenhum registro, retornamos nulo.
				 while ( it.hasNext() ) {

					 campaign = new Campaign();

					 Object[] row = (Object[]) it.next();

					 campaign.setEntityId(Long.valueOf(row[0].toString()));
					 campaign.setCodeCampaign((String)row[1]);
					 campaign.setModelo((String)row[2]);
					 campaign.setSubjectCampaign((String)row[3]);
					 campaign.setStRecall((String)row[4]);
					 campaign.setValidityStartDate(row[5] != null ? (Date)row[5] : null);
					 campaign.setValidityEndDate(row[6] != null ? (Date)row[6] : null);
					 campaigns.add(campaign);
				 }
			 }

		 } catch ( Exception exp ) {

			 throw new DaoException(exp);

		 }  finally {
			 session.close();
		 }
		 
		 return campaigns;
		 
	 }
	 
	 public Long getCampaignByGarantia(Integer garantiaId ) throws DaoException {
		 		 
		 StringBuffer sbSql 		 = new StringBuffer();

		 sbSql.append(" SELECT CAMPAIGN_ID FROM YM_SG_CAMPAIGN_BILLING WHERE GARANTIA_ID = :garantiaId ");
		 sbSql.append(" AND START_DATE <= sysdate AND (END_DATE is null or END_DATE > sysdate) ");
		 
		 Session session = super.getSession();
		 SQLQuery query = session.createSQLQuery(sbSql.toString());
		 
		 query.setParameter("garantiaId" ,  garantiaId);
		 
		 BigDecimal campaignId = null;
		 
		 try {

			 List results  = query.list();
			 
			 if( results != null && results.size() > 0) {
				 campaignId = (BigDecimal) results.get(0);
			 }

		 } catch ( Exception exp ) {

			 throw new DaoException(exp);

		 }  finally {
			 session.close();
		 }
		 
		 return campaignId != null ? new Long(campaignId.longValue()) : null;
	 }
	 
	 /** Lista as campanhas de um determinado produto pelo seu chassi, desde de que, 
	 *  o produto não tenha participado da campanha.
	 * 
	 * @param chassi
	 * 		<code>String</code> Chassi do produto.	
	 * 
	 * @return list
	 * 	<code>list</code> lista de campanhas.
	 * 
	 * @throws DaoException
	 */	
	 public List listCampaign( String chassi ) throws DaoException {
		 		 	
	        final String chassiFinal  = chassi;
	        
	        List results = super.getHibernateTemplate().executeFind(
	            new HibernateCallback() {
	                
	                public Object doInHibernate(Session session) throws HibernateException, SQLException {
	                    
		                Criteria criteria = session.createCriteria(Faturamento.class);
		                criteria.add( Expression.eq( "chassi" , chassiFinal.toUpperCase() ) );
		                criteria.add( Expression.isNull("dataTermino"));
		                criteria.addOrder(Order.desc("dataCriacao"));  //edson 01/07/2013
		                	                    
		                List resultsFat = criteria.list();
		                List results   = new ArrayList();
		                
		                Faturamento faturamento = null;
		       		 	Campaign    campaign    = null;
	           		 	
	           		 
						// Se houver um registro, pegamos o Faturamento.
						// Se houver mais de um registro temos um problema de banco de dados.
						// Se não houver nenhum registro, retornamos nulo.
						if ( resultsFat.size() > 0 ) //edson 01/07/2013
							faturamento = (Faturamento) resultsFat.get(0);
						
						if ( faturamento != null ) {
							
							String chassiSeq 	 = ControllerHelper.getSequencyByChassi(chassiFinal);
							String chassiModelo  = ControllerHelper.getModeloByChassi(chassiFinal);
							
							session.enableFilter("effectiveDate").setParameter("asOfDate", new Date());
							criteria = session.createCriteria( Campaign.class );
							criteria.add( Expression.eq( "modelo"  	     , chassiModelo ) );
							criteria.add( Expression.le( "chassiSeqStart", chassiSeq ) );
							criteria.add( Expression.ge( "chassiSeqFinal", chassiSeq ) );
							//criteria.add( Expression.eq( "stRecall", Campaign.RECALL_YES ) );
							criteria.addOrder( Order.asc("codeCampaign") );
							 
							List resultsCP = criteria.list();
							 			 
							Iterator it = resultsCP.iterator();
							 			 
							while ( it.hasNext() ) {
								 
								 campaign = (Campaign) it.next();
								 
								 Iterator itCP = campaign.getBilling().iterator();
								 boolean hasCampaign = false;
								 
								 while ( itCP.hasNext() ) {
									 
									 Faturamento billing = (Faturamento) itCP.next();
									 					 					 
									 if ( billing.getEntityId().equals(faturamento.getEntityId()) )
										 hasCampaign = true;
								 }
								 
								 if ( !hasCampaign )				 
									 results.add(campaign);					 
							 }
							 
						} 
						return results;
	                }
	                
	            }
	        );

	        return results;
	 }
 
}

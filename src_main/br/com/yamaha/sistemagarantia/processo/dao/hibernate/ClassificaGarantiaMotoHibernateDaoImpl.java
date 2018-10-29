/* Yamaha
 * Copyright (c) 2013 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......ClassificaGarantiaMotoHibernateDaoImpl.java
 *   .: Criação.....08 de Junho 2013.
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...DAO com tarefas específicas para aos serviços de Analise de Garantia
 */
package br.com.yamaha.sistemagarantia.processo.dao.hibernate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import br.com.resource.infra.dao.hibernate.HibernateDaoImpl;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.processo.dao.ClassificaGarantiaMotoDao;
import br.com.yamaha.sistemagarantia.processo.model.ClassificaGarantiaMotoVO;
import br.com.yamaha.sistemagarantia.processo.model.HistGarantiaVO;

/** Objeto de acesso aos dados com métodos específicos para
 *  aos serviços de Classificação Técnica de Garantia.
 *  
 *  @author Edson Luiz Sumensari
 */
public class ClassificaGarantiaMotoHibernateDaoImpl extends HibernateDaoImpl implements ClassificaGarantiaMotoDao {

	/** Lista as garantias para classificação por lote.
	 * @param loteId
	 * @param linhaId
	 * @return List 
	 * @throws DaoException
	 */
	public List listGarantiaByLote(Integer loteId, Long linhaId) throws DaoException {
		
		StringBuffer sbSql 		 = getSQLQuery();
		
		sbSql.append("  AND   lt.lote_id = :lote_id ");
		sbSql.append(" ORDER BY lt.LIBERACAO_DATE DESC ");
		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sbSql.toString());

		query.setParameter("linha_id" , linhaId);
		query.setParameter("lote_id"  , loteId);
					
		List values = null;
		
		try {
			
			List results = query.list();
				
			values = listClassificaGarantiaMoto(results);
			
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		
		return values;
	}
	
	/** Recupera a garantia para classificação.
	 * @param garantiaId
	 * @param linhaId
	 * @return List 
	 * @throws DaoException
	 */
	public List listGarantia(Integer garantiaId, Long linhaId) throws DaoException {
		
		StringBuffer sbSql 		 = getSQLQuery();
		
		sbSql.append("  AND   ga.garantia_id = :garantia_id ");
		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sbSql.toString());

		query.setParameter("linha_id"     , linhaId);
		query.setParameter("garantia_id"  , garantiaId);
					
		List values = null;
		
		try {
			
			List results = query.list();
				
			values = listClassificaGarantiaMoto(results);
			
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		
		return values;
	}
	
	/** Recupera a garantia para classificação.
	 * @param modelo
	 * @param linhaId
	 * @return List 
	 * @throws DaoException
	 */
	public List listGarantiaByChassi(String chassi, Long linhaId) throws DaoException {
		
		StringBuffer sbSql 		 = getSQLQuery();
		
		sbSql.append("  AND   ga.modelo = :chassi ");
		sbSql.append(" ORDER BY lt.LIBERACAO_DATE DESC ");
		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sbSql.toString());

		query.setParameter("linha_id" , linhaId);
		query.setParameter("chassi"   , chassi);
					
		List values = null;
		
		try {
			
			List results = query.list();
				
			values = listClassificaGarantiaMoto(results);
			
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		
		return values;
	}
	
	/** Recupera a garantia para classificação.
	 * @param modelo
	 * @param linhaId
	 * @return List 
	 * @throws DaoException
	 */
	public List listGarantiaByModelo(String modelo, Long linhaId) throws DaoException {
		
		StringBuffer sbSql 		 = getSQLQuery();
		
		sbSql.append("  AND substr(ga.modelo,1,5) = :modelo ");
		sbSql.append(" ORDER BY lt.LIBERACAO_DATE DESC ");
		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sbSql.toString());

		query.setParameter("linha_id" , linhaId);
		query.setParameter("modelo"   , modelo);
					
		List values = null;
		
		try {
			
			List results = query.list();
				
			values = listClassificaGarantiaMoto(results);
			
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		
		return values;
	}
	
	/** Recupera a garantia para classificação.
	 * @param linhaId
	 * @return List 
	 * @throws DaoException
	 */
	public List listGarantiaByLinha(Long linhaId) throws DaoException {
		
		StringBuffer sbSql 		 = getSQLQuery();
		sbSql.append(" ORDER BY lt.LIBERACAO_DATE DESC ");
		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sbSql.toString());

		query.setParameter("linha_id" 			, linhaId);
					
		List values = null;
		
		try {
			
			List results = query.list();
				
			values = listClassificaGarantiaMoto(results);
			
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		
		return values;
	}
	
	/** Recupera a garantia para classificação pelo código da peça causadora.
	 * @param String itemCode
	 * @param linhaId
	 * @return List 
	 * @throws DaoException
	 */
	public List listGarantiaByPecaCausadora(String itemCode, Long linhaId) throws DaoException {
		
		StringBuffer sbSql 		 = getSQLQuery();
		
		sbSql.append("  AND yi.item_code = :itemCode ");
		sbSql.append(" ORDER BY lt.LIBERACAO_DATE DESC ");
		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sbSql.toString());

		query.setParameter("linha_id" 	, linhaId);
		query.setParameter("itemCode"   , itemCode);
					
		List values = null;
		
		try {
			
			List results = query.list();
				
			values = listClassificaGarantiaMoto(results);
			
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		
		return values;
	}
	
	/** Recupera a garantia para classificação.
	 * @param String concessionariaDs
	 * @param linhaId
	 * @return List 
	 * @throws DaoException
	 */
	public List listGarantiaByConcessionaria(String concessionariaDs, Long linhaId) throws DaoException {
		
		StringBuffer sbSql 		 = getSQLQuery();
		
		sbSql.append("  AND UPPER(cc.razao_social) LIKE '%' || UPPER(:concessionariaDs) || '%' ");
		sbSql.append(" ORDER BY lt.LIBERACAO_DATE DESC ");
		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sbSql.toString());

		query.setParameter("linha_id" 			, linhaId);
		query.setParameter("concessionariaDs"   , concessionariaDs);
					
		List values = null;
		
		try {
			
			List results = query.list();
				
			values = listClassificaGarantiaMoto(results);
			
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		
		return values;
	}
	
	/**
	 * 
	 * @param results
	 * @return
	 */
	private List listClassificaGarantiaMoto(List results) {
		
		if(results != null) {
			
			Iterator it =  results.iterator();
			ClassificaGarantiaMotoVO classificaGarantiaMoto = null;
			List values = new ArrayList();
			
			while(it.hasNext()) {
				
				Object[] row = (Object[]) it.next();
				
				classificaGarantiaMoto = new ClassificaGarantiaMotoVO();
				classificaGarantiaMoto.setLoteId(Integer.valueOf(row[0].toString()));
				classificaGarantiaMoto.setRazaoSocialConcessionaria((String)row[1]);
				classificaGarantiaMoto.setGarantiaId(Integer.valueOf(row[2].toString()));
				classificaGarantiaMoto.setModelo((String)row[3]);				
				classificaGarantiaMoto.setDataAprovacao((Date)row[4]);
				classificaGarantiaMoto.setDsStatusMov((String)row[5]);
				classificaGarantiaMoto.setDataLiberacao((Date)row[6]);
				classificaGarantiaMoto.setQuilometragem(row[7] != null ? Long.valueOf(row[7].toString()) : null);
				classificaGarantiaMoto.setDataAberturaOS((Date)row[8]);
				classificaGarantiaMoto.setItemCode((String)row[9]);
				classificaGarantiaMoto.setDescricao((String)row[10]);
				values.add(classificaGarantiaMoto);
			}
			
			return values;			
		}			
		
		return null;
		
	}
	
	/**
	 * retorna uma string com a query padrão par lista de garantias para análise
	 * 
	 * @return StringBuffer
	 */
	private StringBuffer getSQLQuery() {
		
		StringBuffer sbSql 		 = new StringBuffer();
		
		sbSql.append(" SELECT lt.lote_id 	");
		sbSql.append("  , cc.razao_social 	");
		sbSql.append("  , ga.garantia_id 	");
		sbSql.append("  , ga.modelo 		");		
		sbSql.append("  , ga.created_date 	");
		sbSql.append("  , st.descricao      ");
		sbSql.append("  , lt.LIBERACAO_DATE	");
		sbSql.append("  , ga.quilometragem  ");
		sbSql.append("  , ga.data_abertura_os ");
		sbSql.append("  , yi.item_code ");
		sbSql.append("  , yi.descricao as descPeca"); 
		sbSql.append(" FROM YM_SG_LOTE lt ");
		sbSql.append("      , YM_SG_GARANTIA_HEADER ga ");
		sbSql.append("		, YM_SG_GARANTIA_LINE   gl ");
		sbSql.append(" 		, ym_sg_item            yi ");
		sbSql.append("      , YM_SG_CONCESSIONARIA cc ");
		sbSql.append("      , YM_SG_STATUS_MOV st ");
		sbSql.append(" WHERE lt.lote_id          = ga.lote_id ");
		sbSql.append(" AND ga.garantia_id = gl.garantia_id ");
		sbSql.append(" AND gl.peca_causadora = 'S' ");
		sbSql.append(" AND gl.item_id = yi.item_id ");
		sbSql.append(" AND yi.organization_id = 91 ");
		sbSql.append(" AND lt.concessionaria_id  = cc.concessionaria_id ");
		sbSql.append(" AND ga.status_mov_id      = st.status_mov_id ");
		sbSql.append(" AND lt.status_lote_id = 27 ");	// Lote com status "Aguardando Classificação Técnica"
		sbSql.append(" AND ga.status_mov_id  = 26 "); 	//Garantia com status "Aguardando Classificação Técnica"
		sbSql.append(" AND lt.linha_id = :linha_id "); 	// Linha do produto		
		sbSql.append(" AND ga.start_date <= sysdate ");
		sbSql.append(" AND ( (ga.end_date is null) OR (ga.end_date >= sysdate) )");
		sbSql.append(" AND lt.start_date <= sysdate " );
		sbSql.append(" AND ( (lt.end_date is null) OR (lt.end_date >= sysdate) )");
		
		return sbSql;
	}
	
	/**retorna uma lista de garantias do Chassi informado
	 * 
	 * @param String chassi
	 * @param Integer garantiaId 
	 * @return List
	 */
	public List listHistoricoGarantia(Integer garantiaId, String modelo) throws DaoException {
		
		StringBuffer sbSql 		 = new StringBuffer();
		
		sbSql.append(" SELECT lt.lote_id 	");
		sbSql.append("  , cc.razao_social 	");
		sbSql.append("  , cc.cnpj 			");
		sbSql.append("  , ga.garantia_id 	");
		sbSql.append("  , ga.created_date 	");
		sbSql.append("  , st.descricao      ");
		sbSql.append("  , lt.LIBERACAO_DATE	");
		sbSql.append(" FROM YM_SG_LOTE lt ");
		sbSql.append("      , YM_SG_GARANTIA_HEADER ga ");
		sbSql.append("      , YM_SG_CONCESSIONARIA cc ");
		sbSql.append("      , YM_SG_STATUS_MOV st ");
		sbSql.append(" WHERE lt.lote_id          = ga.lote_id ");
		sbSql.append(" AND lt.concessionaria_id  = cc.concessionaria_id ");
		sbSql.append(" AND ga.status_mov_id      = st.status_mov_id ");
		sbSql.append(" AND ga.start_date <= sysdate ");
		sbSql.append(" AND ( (ga.end_date is null) OR (ga.end_date >= sysdate) )");
		sbSql.append(" AND lt.start_date <= sysdate " );
		sbSql.append(" AND ( (lt.end_date is null) OR (lt.end_date >= sysdate) )");
		sbSql.append(" AND   ga.modelo = :modelo ");
		sbSql.append(" ORDER BY lt.LIBERACAO_DATE DESC ");
		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sbSql.toString());

		query.setParameter("modelo"   , modelo);
					
		List values = null;
		
		try {
			
			List results = query.list();
				
			if(results != null) {
				
				Iterator it =  results.iterator();
				HistGarantiaVO histGarantia = null;
				values = new ArrayList();
				while(it.hasNext()) {
					
					Object[] row = (Object[]) it.next();
					
					histGarantia = new HistGarantiaVO();
					
					histGarantia.setGarantiaId(Integer.valueOf(row[3].toString()));
					//se a garantia recuperada for diferente da analisada, colocamos na lista
					if(!garantiaId.equals(histGarantia.getGarantiaId())) {
						
						histGarantia.setLoteId(Integer.valueOf(row[0].toString()));
						histGarantia.setRazaoSocialConcessionaria((String)row[1]);
						histGarantia.setCnpj((BigDecimal)row[2]);
						histGarantia.setDataAbertura((Date)row[4]);
						histGarantia.setDsStatusMov((String)row[5]);
						histGarantia.setDataLiberacao((Date)row[6]);
						values.add(histGarantia);
					}
				}
				
				return values;			
			}			
			
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		
		return values;
	}


}


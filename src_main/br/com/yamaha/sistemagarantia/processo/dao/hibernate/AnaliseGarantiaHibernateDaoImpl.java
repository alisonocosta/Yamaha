/* Yamaha
 * Copyright (c) 2012 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......AnaliseGarantiaHibernateDaoImpl.java
 *   .: Criação.....17 de Dezembro, 21:52.
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
import br.com.yamaha.sistemagarantia.model.StatusLote;
import br.com.yamaha.sistemagarantia.processo.dao.AnaliseGarantiaDao;
import br.com.yamaha.sistemagarantia.processo.model.AnaliseGarantiaVO;
import br.com.yamaha.sistemagarantia.processo.model.HistGarantiaVO;

/** Objeto de acesso aos dados com métodos específicos para
 *  aos serviços de Analise de Garantia.
 *  
 *  @author Edson Luiz Sumensari
 */
public class AnaliseGarantiaHibernateDaoImpl extends HibernateDaoImpl implements AnaliseGarantiaDao {

	/** Lista as garantias para análise por lote.
	 * @param loteId
	 * @param linhaId
	 * @return List 
	 * @throws DaoException
	 */
	public List listGarantiaByLote(Integer loteId, Long linhaId) throws DaoException {
		
		StringBuffer sbSql 		 = getSQLQuery();
		
		sbSql.append("  AND   lt.lote_id = :lote_id ");
		sbSql.append(" ORDER BY lt.LIBERACAO_DATE ASC ");
		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sbSql.toString());

		query.setParameter("linha_id" , linhaId);
		query.setParameter("lote_id"  , loteId);
					
		List values = null;
		
		try {
			
			List results = query.list();
				
			values = listAnaliseGarantia(results);
			
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		
		return values;
	}
	
	/** Recupera a garantia par análise.
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
				
			values = listAnaliseGarantia(results);
			
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		
		return values;
	}
	
	/** Recupera a garantia para análise.
	 * @param modelo
	 * @param linhaId
	 * @return List 
	 * @throws DaoException
	 */
	public List listGarantiaByModelo(String modelo, Long linhaId) throws DaoException {
		
		StringBuffer sbSql 		 = getSQLQuery();
		
		sbSql.append("  AND   ga.modelo = :modelo ");
		sbSql.append(" ORDER BY lt.LIBERACAO_DATE ASC ");
		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sbSql.toString());

		query.setParameter("linha_id" , linhaId);
		query.setParameter("modelo"   , modelo);
					
		List values = null;
		
		try {
			
			List results = query.list();
				
			values = listAnaliseGarantia(results);
			
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		
		return values;
	}
	
	/** Recupera a garantia para análise.
	 * @param linhaId
	 * @return List 
	 * @throws DaoException
	 */
	public List listGarantiaByLinha(Long linhaId) throws DaoException {
		
		StringBuffer sbSql 		 = getSQLQuery();
		sbSql.append(" ORDER BY lt.LIBERACAO_DATE ASC ");
		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sbSql.toString());

		query.setParameter("linha_id" 			, linhaId);
					
		List values = null;
		
		try {
			
			List results = query.list();
				
			values = listAnaliseGarantia(results);
			
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		
		return values;
	}
	
	/** Recupera a garantia para análise.
	 * @param String concessionariaDs
	 * @param linhaId
	 * @return List 
	 * @throws DaoException
	 */
	public List listGarantiaByConcessionaria(String concessionariaDs, Long linhaId) throws DaoException {
		
		StringBuffer sbSql 		 = getSQLQuery();
		
		sbSql.append("  AND UPPER(cc.razao_social) LIKE '%' || UPPER(:concessionariaDs) || '%' ");
		sbSql.append(" ORDER BY lt.LIBERACAO_DATE ASC ");
		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sbSql.toString());

		query.setParameter("linha_id" 			, linhaId);
		query.setParameter("concessionariaDs"   , concessionariaDs);
					
		List values = null;
		
		try {
			
			List results = query.list();
				
			values = listAnaliseGarantia(results);
			
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		
		return values;
	}
	
	/** Recupera a garantia para análise por representante.
	 * @param String REPRESENTANTE
	 * @param linhaId
	 * @return List 
	 * @throws DaoException
	 */
	public List listGarantiaByRepresentante(String representanteDs, Long linhaId) throws DaoException {
		
		StringBuffer sbSql 		 = getSQLQuery();
		StringBuffer sbSqlIds    = new StringBuffer();
		
		List representanteList = this.listarNomeRepresentanteByName(representanteDs);
		List values = null;
		
		if(representanteList != null && !representanteList.isEmpty()) {
			
			Iterator it = representanteList.iterator();
			BigDecimal representanteId = null;
			int cont = 0;
			while(it.hasNext()){	
				
				representanteId = (BigDecimal)it.next();
				if(cont > 0) 
					sbSqlIds.append(',');
				
				cont++;
				sbSqlIds.append(representanteId.toString());				
			}		

			sbSql.append("  AND cc.REPRESENTANTE_NAUT IN(");
			sbSql.append(sbSqlIds.toString());
			sbSql.append(") ");
			sbSql.append(" ORDER BY lt.LIBERACAO_DATE ASC ");

			Session session = super.getSession();
			SQLQuery query = session.createSQLQuery(sbSql.toString());

			query.setParameter("linha_id" 			, linhaId);

			try {

				List results = query.list();

				values = listAnaliseGarantia(results);

			} catch ( Exception exp ) {

				throw new DaoException(exp);

			}  finally {
				session.close();
			}
		}
		return values;
	}
	
	/**
	 * 
	 * @param results
	 * @return
	 */
	private List listAnaliseGarantia(List results) {
		
		if(results != null) {
			
			Iterator it =  results.iterator();
			AnaliseGarantiaVO analiseGarantia = null;
			List values      = new ArrayList();
			List valuesManut = new ArrayList();
			
			while(it.hasNext()) {
								
				Object[] row = (Object[]) it.next();
				
				analiseGarantia = new AnaliseGarantiaVO();
				analiseGarantia.setLoteId(Integer.valueOf(row[0].toString()));
				analiseGarantia.setRazaoSocialConcessionaria((String)row[1]);
				analiseGarantia.setGarantiaId(Integer.valueOf(row[2].toString()));
				analiseGarantia.setModelo((String)row[3]);				
				analiseGarantia.setDataAprovacao((Date)row[4]);
				analiseGarantia.setDsStatusMov((String)row[5]);
				analiseGarantia.setDataLiberacao((Date)row[6]);
				analiseGarantia.setNomeRepresentanteNautica(row[7] != null ? this.buscarNomeRepresentante(Integer.valueOf(row[7].toString())) : null);
				analiseGarantia.setLinhaId(Integer.valueOf(row[8].toString()));
				
				if(this.verificaManutencao(analiseGarantia.getLoteId())) {
					valuesManut.add(analiseGarantia);
				} else {				
					values.add(analiseGarantia);
				}				
			}
				
			if(valuesManut.size() > 0){
				for(int i=0;i < valuesManut.size();i++){
					values.add(i, valuesManut.get(i));					
				}
			}		
			
			return values;			
		}			
		
		return null;
		
	}
	
	/**
	 *Recupera o nome do representante de Náutica
	 * 
	 * @return String  
	 */
	private String buscarNomeRepresentante(Integer representanteId) { 
		
		StringBuffer sbSql 		 = new StringBuffer();		
		sbSql.append(" SELECT NOME_REPRESENTANTE FROM ym_sg_representante  WHERE representante_id = :representanteId");
				
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sbSql.toString());

		query.setParameter("representanteId" , representanteId);
				
		String nmRepresentante = null;
		try {
			
			nmRepresentante = (String)query.uniqueResult();
						
		} catch ( Exception exp ) {
			
			exp.printStackTrace();		
			
		}  finally {
			session.close();
		}
		
		return nmRepresentante;
	}
	
	/**
	 *Recupera representante(s) por nome 
	 * 
	 * @return List  
	 */
	private List listarNomeRepresentanteByName(String representanteDs) { 
		
		StringBuffer sbSql 		 = new StringBuffer();		
		sbSql.append(" SELECT REPRESENTANTE_ID FROM ym_sg_representante  WHERE UPPER(nome_representante) LIKE '%' || UPPER(:representanteDs) || '%'");
				
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sbSql.toString());

		query.setParameter("representanteDs" , representanteDs);
				
		List representanteList = null;
		try {
			
			representanteList = query.list();
						
		} catch ( Exception exp ) {
			
			exp.printStackTrace();		
			
		}  finally {
			session.close();
		}
		
		return representanteList;
	}
	
	
	/**
	 * Verifica se um lote veio de manutenção
	 * 
	 * @return boolean  true quando veio de manutenção
	 */
	private boolean verificaManutencao(Integer LoteId) { 
		
		StringBuffer sbSql 		 = new StringBuffer();		
		sbSql.append(" SELECT status_lote_id FROM ym_sg_lote_log  WHERE lote_id = :loteId ORDER BY created_date DESC ");
		
		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sbSql.toString());

		query.setParameter("loteId" , LoteId);
					
		boolean isManut = false;
		
		try {
			
			List results = query.list();
			
			if(results != null && results.size() > 1) {
				
				BigDecimal statusLoteId = (BigDecimal)results.get(1);
				
				if(statusLoteId != null && StatusLote.STATUS_MANUTENCAO.equals(new Long(statusLoteId.longValue())))
					isManut = true;
			}
			
		} catch ( Exception exp ) {
			
			exp.printStackTrace();			
			isManut = false;			
			
		}  finally {
			session.close();
		}
		
		return isManut;
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
		sbSql.append("  , cc.REPRESENTANTE_NAUT	");
		sbSql.append("  , lt.linha_id	");		
		sbSql.append(" FROM YM_SG_LOTE lt ");
		sbSql.append("      , YM_SG_GARANTIA_HEADER ga ");
		sbSql.append("      , YM_SG_CONCESSIONARIA cc ");
		sbSql.append("      , YM_SG_STATUS_MOV st ");
		sbSql.append(" WHERE lt.lote_id          = ga.lote_id ");
		sbSql.append(" AND lt.concessionaria_id  = cc.concessionaria_id ");
		sbSql.append(" AND ga.status_mov_id      = st.status_mov_id ");
		sbSql.append(" AND lt.status_lote_id     in (2,5) ");// Lote com status "Em Análise" e "Aguardando Digitação"
		sbSql.append(" AND ga.status_mov_id      = 1 "); //Garantia com status "Em Análise"
		sbSql.append(" AND lt.linha_id = :linha_id "); // Linha do produto		
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


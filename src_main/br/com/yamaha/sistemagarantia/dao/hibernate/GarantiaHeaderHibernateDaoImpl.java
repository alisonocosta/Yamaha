/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......GarantiaHeaderHibernateDaoImpl.java
 *   .: Criação.....01 de junho, 12:46
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...DAO com tarefas específicas para a entidade GarantiaHeader.
 */
package br.com.yamaha.sistemagarantia.dao.hibernate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.HibernateCallback;

import br.com.resource.infra.dao.hibernate.HibernateDaoImpl;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.controller.helper.ControllerHelper;
import br.com.yamaha.sistemagarantia.dao.GarantiaHeaderDao;
import br.com.yamaha.sistemagarantia.model.AlertGarantia;
import br.com.yamaha.sistemagarantia.model.CampaignBilling;
import br.com.yamaha.sistemagarantia.model.Concessionaria;
import br.com.yamaha.sistemagarantia.model.Cupom;
import br.com.yamaha.sistemagarantia.model.DescricaoDefeito;
import br.com.yamaha.sistemagarantia.model.Faturamento;
import br.com.yamaha.sistemagarantia.model.GarantiaHeader;
import br.com.yamaha.sistemagarantia.model.GarantiaLine;
import br.com.yamaha.sistemagarantia.model.InfoMercGarantia;
import br.com.yamaha.sistemagarantia.model.Item;
import br.com.yamaha.sistemagarantia.model.Linha;
import br.com.yamaha.sistemagarantia.model.Lote;
import br.com.yamaha.sistemagarantia.model.Recall;
import br.com.yamaha.sistemagarantia.model.Servico;
import br.com.yamaha.sistemagarantia.model.ServicoGrupo;
import br.com.yamaha.sistemagarantia.model.StatusGarantia;
import br.com.yamaha.sistemagarantia.model.StatusLote;
import br.com.yamaha.sistemagarantia.model.TempoPadrao;
import br.com.yamaha.sistemagarantia.model.TipoLote;
import br.com.yamaha.sistemagarantia.model.Usuario;
import br.com.yamaha.sistemagarantia.model.ValorServico;
import br.com.yamaha.sistemagarantia.model.id.GarantiaLineId;
import br.com.yamaha.sistemagarantia.view.GarantiaVO;

/** Objeto DAO com tarefas específicas para a entidade <b>GarantiaHeader</b>. 
 * 
 *  @author Edson Luiz Sumensari
 */
public class GarantiaHeaderHibernateDaoImpl extends HibernateDaoImpl implements GarantiaHeaderDao {
  
   /** 
    * recupera um lote de garantias e suas dependências.
    *      
    *  @param entityId
    *  	<code>Integer</code> Id do lote.
    *  
    *  @return
    *      <code>Lote</code> Uma entidade de Lote.
    *  
    *  @throws DaoException
    *      Se houverem erros durante a execução estes serão lançados
    *      como uma Exception deste tipo.
    */
	public Lote getLote(Integer entityId) throws DaoException {
		
		StringBuffer sbSql 		 = new StringBuffer();
					
		sbSql.append(" SELECT lt.lote_id  		loteId 						 "); //0
		sbSql.append(" 		  ,li.descr_linha 	descricaoLinha 				 "); //1
		sbSql.append("		  ,li.linha_id    	linhaId						 "); //2
		sbSql.append("		  ,sl.status_lote_id statusId					 "); //3
		sbSql.append("		  ,sl.DESCR_REDUZ   descricaoReduz				 "); //4
		sbSql.append("		  ,lt.lote_date		dataLote					 "); //5
		sbSql.append("		  ,lt.DATA_ENVIO_ADT  dataEnvioAdiant			 "); //6
		sbSql.append("		  ,lt.TIPO_LOTE_ID    tipoLoteid			 	 "); //7
		sbSql.append("	FROM YM_SG_LOTE lt		");
		sbSql.append("      ,YM_SG_STATUS_LOTE sl ");
		sbSql.append("      ,YM_SG_STATUS_ADT sa ");
		sbSql.append("      ,YM_SG_LINHA li ");
		sbSql.append("  WHERE lt.linha_id = li.linha_id ");
		sbSql.append("  AND   sl.status_lote_id = lt.status_lote_id ");
		sbSql.append("  AND   sa.status_adt_id(+) = lt.status_adt ");
		sbSql.append("  AND   lt.START_DATE <= sysdate AND (lt.END_DATE is null or lt.END_DATE > sysdate) ");
		sbSql.append("  AND   lt.lote_id = :lote_id ");
		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sbSql.toString());

		query.setParameter("lote_id" , entityId);
			
		Lote lote = null;
		Linha linha = null;
		StatusLote statusLote = null;
		TipoLote tipoLote = null;
		try {
			List results = query.list();
			
			if(results != null && results.size() == 1) {
				
				Iterator it =  results.iterator();
				
				while(it.hasNext()) {
					
					Object[] row = (Object[]) it.next();
					
					lote = new Lote();
					lote.setEntityId(Integer.valueOf(row[0].toString()));
					linha = new Linha();
					linha.setDescricao((String)row[1]);
					linha.setEntityId(Long.valueOf(row[2].toString()));
					lote.setLinha(linha);
					
					statusLote = new StatusLote();
					statusLote.setEntityId(Long.valueOf(row[3].toString()));
					statusLote.setDescricaoReduzida((String)row[4]);
					lote.setStatusLote(statusLote);
					
					lote.setDataLote((Date)row[5]);
					lote.setDataEnvioAdiant((Date)row[6]);		
					
					tipoLote = new TipoLote();
					tipoLote.setEntityId(Long.valueOf(row[7].toString()));					
					lote.setTipoLote(tipoLote);
				}
				
			} else if(results != null && results.size() > 1)
				throw new DaoException("Mais de um Lote com o Número " + entityId + " Localizado!"); 
				
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		
		return lote;
	}
	
	/** Determina se já houveram garantias cobradas.
	 * 
	 *  @param itemId
	 *  @param modelo
	 *  @return
	 *  @throws DaoException
	 */
	public boolean hasGarantiasCobradas( long itemId, String modelo ) throws DaoException {
		
		String sql = " SELECT gh.garantia_id " + 
		             "   FROM ym_sg_garantia_header	gh " +
		             "      , ym_sg_garantia_line   gl " +
		             "  WHERE gh.garantia_id = gl.garantia_id " +
		             "    AND gh.modelo  = :modelo " +
		             "    AND gl.item_id = :itemId " +
		             "    AND gh.start_date < :date " +
					 "    AND (gh.end_date > :date or  gh.end_date is null)	";
				
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		query.setParameter("itemId", new Long(itemId));
		query.setParameter("modelo", modelo);
		query.setParameter("date"  , new Date());
		
		try {
			
			Iterator garantiasCobradasIt = query.list().iterator();
			if ( garantiasCobradasIt.hasNext() ) {
				return true;
				
			} else {
				return false;
				
			}
		} catch( Exception exp ) { 
			
			throw new DaoException(exp);
			
		} finally {
			session.close();
		}
	}
	
	/** Recupera a lista de serviços aproximada de uma determinada linha.
	 *
	 *  @param code
	 *      <code>String<code> Valor aproximado do código do serviço.
	 *      
	 *  @param linhaId
	 *      <code>Long<code> id da linha do produto.
	 *  
	 *  @return
	 *      Um <code>List</code> lista de peças.
	 *      
	 * @throws BusinessException
	 *      Se houverem erros de execução ou nas camadas 
	 *      abaixo dos serviços, serão encapsulados neste
	 *      tipo de <code>Exception</code>.
	 */	
	public List listApproachServices(String code, Long linhaId) throws DaoException { 
		
		DetachedCriteria criteria = super.getDetachedCriteria(Servico.class);
		criteria.add( Expression.like("servicoCode" , code + "%" ) );
		criteria.add( Expression.eq("linha.entityId", linhaId    ) );
		
		return super.getHibernateTemplate().findByCriteria(criteria);
	}
	
	/** Recupera a lista de serviços aproximada de um determinado chassi.
	 *
	 *  @param code
	 *      <code>String<code> Valor aproximado do código do serviço.
	 *      
	 *  @param chassi
	 *      <code>String<code> chassi.
	 *  
	 *  @return
	 *      Um <code>List</code> lista de peças.
	 *      
	 * @throws BusinessException
	 *      Se houverem erros de execução ou nas camadas 
	 *      abaixo dos serviços, serão encapsulados neste
	 *      tipo de <code>Exception</code>.
	 */	
	public List listApproachServicesByChassi(String partCode, String chassi) throws DaoException { 
		
		String sql = 	" select distinct	sv.* " +
						" from 		ym_sg_servico       sv" +
						"			, ym_sg_faturamento ft" +
						"			, ym_sg_item 		it" +
						" where 	ft.item_id = it.item_id" +
						" and   	sv.tipo_veiculo = it.tipo_veiculo" +
						" and   	ft.chassi = :chassi " +
						" and 		sv.servico_code like '" + partCode.toUpperCase() + "%'" +
						" and       ft.created_date = (SELECT MAX(CREATED_DATE) FROM YM_SG_FATURAMENTO WHERE chassi = ft.chassi) " + //edson 01/07/2013
						" and       sv.start_date < :date " +
						" and       (sv.end_date > :date or  sv.end_date is null)	" +
						" order by sv.servico_code ";
		
		Session session = null;
		
		try {
			
			session = super.getSession();
			SQLQuery query  = session.createSQLQuery(sql);
			
			query.addEntity(Servico.class);
			query.setParameter("chassi"    , chassi.toUpperCase() );
			query.setParameter("date"      , new Date());

			List servicos = query.list();
			
			return servicos;	  

		} catch( Exception exp ) { 
			
			throw new DaoException(exp);
			
		} finally {
			session.close();
		}
	}
	
	/** Obtém uma lista de garantias para relacionar com notas fiscais.
	 *
	 *  @param concessionaria
	 *  	<code>Concessionaria</code> entidade concessionária
	 *  
	 *  @param statusLote
	 *  	<code>StatusLote</code> entidade statusLote
	 *  
	 *  @throws DaoException
	 *      Se houverem erros durante a execução estes serão lançados
	 *      como uma Exception deste tipo.
	 */
	public List listGarantias( Concessionaria concessionaria, StatusLote statusLote) throws DaoException {    	

		String sql = " SELECT grh.garantia_id garantiaId," +
		             "        SUM(gsv.vl_servico) valorServico, " +
					 "	      SUM(grl.vl_peca_garantia * grl.quantidade) valorPeca, " +
					 "        SUM(grl.vl_preco_sugerido * grl.quantidade) valorRemessa" +
					 " FROM ym_sg_garantia_header grh, " +
					 "      ym_sg_garantia_line grl, " +
					 "	    ym_sg_servico_grupo gsv, " +
					 "	    ym_sg_lote lt " + 
					 " WHERE grh.garantia_id = grl.garantia_id " + 
					 " AND   grh.servico_grupo_id = gsv.servico_grupo_id " +
					 " AND   grh.status_mov_id = :statusMovId "  +
					 " AND   lt.lote_id = grh.lote_id " +
 					 " AND   lt.status_lote_id = :statusLoteId " +
 					 " AND	 lt.concessionaria_id = :concessionariaId " +
 					 " AND   grh.start_date < :date " +
					 " AND   (grh.end_date > :date OR  grh.end_date is null)	" +
					 " GROUP BY grh.garantia_id";
		
		Session session = null;
		GarantiaVO garantiaVO = null;
		List results = new ArrayList();
		
		try {
			
			session = super.getSession();			
			SQLQuery query = session.createSQLQuery(sql);
	
			query.setParameter("statusLoteId"    , statusLote.getEntityId());
			query.setParameter("concessionariaId", concessionaria.getEntityId());
			query.setParameter("statusMovId"     , new Long(2));
			query.setParameter("date"            , new Date());	
			
			Iterator garantias = query.list().iterator();
			
			while ( garantias.hasNext() ) {

				garantiaVO = new GarantiaVO();
				Object[] row = (Object[]) garantias.next();
				garantiaVO.setGarantiaId((Long)row[0]);
				garantiaVO.setValorServico(((Double)row[1]).doubleValue());
				garantiaVO.setValorPeca(((Double)row[2]).doubleValue());
				garantiaVO.setValorRemessa(((Double)row[1]).doubleValue());

				results.add(garantiaVO);
			}

			return results;	
		
		} catch( Exception exp ) { 
			
			throw new DaoException(exp);
			
		} finally {
			session.close();
		}  

	}

	/** Verifica se existem garantias com status de mov. que ainda não foram lançadas as notas fiscais
	 * 
	 * @param lote
	 * 	<code>Lote</code> Lote para verificação
	 * 
	 * @return boolean
	 * 	<code>boolean</code> true quando existir garantia
	 * 
	 * @throws DaoException
	 */
	public boolean getGarantiasByLote( Lote lote ) throws DaoException {

		boolean isValue = false;
		
		List statusMov = new ArrayList();
		statusMov.add(StatusGarantia.STATUS_AGUARDANDO_DIGITACAO_NOTA);
		statusMov.add(StatusGarantia.STATUS_NF_DIVERGENTE);
		
		DetachedCriteria criteria = super.getDetachedCriteria(GarantiaHeader.class);
		criteria.add( Expression.eq("lote"            			, lote ) );
		criteria.add( Expression.in("statusGarantia.entityId"	, statusMov) );

		List results = super.getHibernateTemplate().findByCriteria(criteria);

		// Se houver um registro, retornamos true.
		if ( results.size() > 0 )
			isValue = true;

		return isValue;

	}
	
	/** Recupera um entidade de CampaignBilling de um Garantia
	 * 
	 * @param garantia
	 * 	<code>GarantiaHeader</code> Entidade de GarantiaHeader
	 * 
	 * @return campaignBilling
	 * 	<code>CampaignBilling</code> Quando a garantia for de uma campanha
	 * 
	 * @throws DaoException
	 */
	public CampaignBilling getCampaignBillingByGarantia( GarantiaHeader garantia ) throws DaoException {

		Faturamento faturamento = (Faturamento)this.getByParameter(Faturamento.class, "chassi", garantia.getModelo().toUpperCase());
		
		DetachedCriteria criteria = super.getDetachedCriteria(CampaignBilling.class);
		criteria.add( Expression.eq("billing" , faturamento ) );
		criteria.add( Expression.eq("garantia"    , garantia ) );

		List results = super.getHibernateTemplate().findByCriteria(criteria);

		// Se houver um registro, retornamos sendo o Revisao da pesquisa.
		// Se houver mais de um registro temos um problema de banco de dados.
		// Se não houver nenhum registro, retornamos nulo.
		if ( results.size() == 1 )
			return (CampaignBilling) results.get(0);

		else if ( results.size() > 1 )
			throw new DaoException("Too many entities found. There should be only one.");

		else
			return null;

	}
	
	/** Recupera um entidade de InfoMercGarantia de um Garantia
	 * 
	 * @param garantia
	 * 	<code>InfoMercGarantia</code> populado com a Garantia e InfoMercado associada
	 * 
	 * @return InfoMercGarantia
	 * 	<code>InfoMercGarantia</code> Quando a garantia for de uma InfoMercGarantia
	 * 
	 * @throws DaoException
	 */
	public InfoMercGarantia getInfoMercGarantia( InfoMercGarantia infoMercGarantia ) throws DaoException {
		
		DetachedCriteria criteria = super.getDetachedCriteria(InfoMercGarantia.class);
		criteria.add( Expression.eq("infoMercado" , infoMercGarantia.getInfoMercado() ) );
		criteria.add( Expression.eq("garantia"    , infoMercGarantia.getGarantia() ) );

		List results = super.getHibernateTemplate().findByCriteria(criteria);

		// Se houver um registro, retornamos sendo o InfoMercGarantia da pesquisa.
		// Se houver mais de um registro temos um problema de banco de dados.
		// Se não houver nenhum registro, retornamos nulo.
		if ( results.size() == 1 )
			return (InfoMercGarantia) results.get(0);

		else if ( results.size() > 1 )
			throw new DaoException("Too many entities found. There should be only one.");

		else
			return null;

	}

	/** Obtém uma entidade de Servico a partir de seu ID.
	 * 
	 *  @param entityId
	 *      <code>Long<code>. Id da entidade.
	 *      	    
	 *  @return
	 *      Um <code>Servico</code> populado com os dados da 
	 *      entidade específica, solicitada, ou caso nenhuma entidade
	 *      seja encontrada, retornará <code>null</code>.
	 *      
	 *  @throws DaoException
	 *      Se houverem problemas nas camadas inferiores, estes serão 
	 *      convertidos para uma DaoException.
	 */     
	public Servico getServicoById(Long servicoId) throws DaoException {

		DetachedCriteria criteria = super.getDetachedCriteria(Servico.class);
		criteria.add( Expression.eq("entityId", servicoId ) );

		List results = super.getHibernateTemplate().findByCriteria(criteria);

		// Se houver um registro, retornamos sendo o Revisao da pesquisa.
		// Se houver mais de um registro temos um problema de banco de dados.
		// Se não houver nenhum registro, retornamos nulo.
		if ( results.size() == 1 )
			return (Servico) results.get(0);

		else if ( results.size() > 1 )
			throw new DaoException("Too many entities found. There should be only one.");

		else
			return null;
	}   
	
	/** Obtém uma entidade de Servico a partir de seu servicoCode.
	 * 
	 *  @param servicoCode
	 *      <code>String<code>. Código do Serviço.
	 *      
	 *  @param linha
	 *      <code>Linha<code> Linha de produto relacionadada.
	 *      	    
	 *  @return
	 *      Um <code>Servico</code> populado com os dados da 
	 *      entidade específica, solicitada, ou caso nenhuma entidade
	 *      seja encontrada, retornará <code>null</code>.
	 *      
	 *  @throws DaoException
	 *      Se houverem problemas nas camadas inferiores, estes serão 
	 *      convertidos para uma DaoException.
	 */     
	public Servico getServicoByCode(String servicoCode, Linha linha) throws DaoException {

		DetachedCriteria criteria = super.getDetachedCriteria(Servico.class);
		criteria.add( Expression.eq("servicoCode", servicoCode ) );
		criteria.add( Expression.eq("linha"      , linha       ) );

		List results = super.getHibernateTemplate().findByCriteria(criteria);

		// Se houver um registro, retornamos sendo o Revisao da pesquisa.
		// Se houver mais de um registro temos um problema de banco de dados.
		// Se não houver nenhum registro, retornamos nulo.
		if ( results.size() == 1 )
			return (Servico) results.get(0);

		else if ( results.size() > 1 )
			throw new DaoException("Too many entities found. There should be only one.");

		else
			return null;
	} 
	
	/** Recupera uma Entidade de serviço pelo código e um determinado chassi.
	 *
	 *  @param code
	 *      <code>String<code> Valor do código do serviço.
	 *      
	 *  @param chassi
	 *      <code>String<code> chassi.
	 *      
	 *  @param linha
	 *      <code>Linha<code> linha do produto.
	 *  
	 *  @return
	 *      Um <code>Servico</code> entidade de Servico.
	 *      
	 * @throws BusinessException
	 *      Se houverem erros de execução ou nas camadas 
	 *      abaixo dos serviços, serão encapsulados neste
	 *      tipo de <code>Exception</code>.
	 */	
	public Servico getServiceByChassi(String code, String chassi, Linha linha) throws DaoException { 
		
		String sql = new String();
		
		// Quando for produto da linha motocicleta, devemos buscar pelo id da linha
		if ( !Linha.TIPO_MOTOCICLETA.equals(linha.getEntityId())) {
		
				sql = 	" select distinct	sv.* " +
						" from 		ym_sg_servico       sv" +
						"			, ym_sg_faturamento ft" +
						"			, ym_sg_item 		it" +
						" where 	ft.item_id = it.item_id" +
						" and   	sv.tipo_veiculo = it.tipo_veiculo" +
						" and   	ft.chassi = :chassi " +
						" and       ft.created_date = (SELECT MAX(CREATED_DATE) FROM YM_SG_FATURAMENTO WHERE chassi = ft.chassi) " + //edson 01/07/2013
						" and       sv.start_date < :date " +
						" and       (sv.end_date > :date or  sv.end_date is null)	" +
						" and 		sv.servico_code = :code";
				
		} else {
			
			sql = 	" select distinct	sv.* " +
					" from 		ym_sg_servico sv" +
					" where 	sv.linha_id = 1 " +
					" and       sv.start_date < :date " +
					" and       (sv.end_date > :date or  sv.end_date is null)	" +
					" and 		sv.servico_code = :code";
			
			
		}
		
		Session session = null;
		
		try {

			session = super.getSession();
			SQLQuery query  = session.createSQLQuery(sql);
			
			query.addEntity(Servico.class);
			
			if ( !Linha.TIPO_MOTOCICLETA.equals(linha.getEntityId()))
				query.setParameter("chassi", chassi.toUpperCase() );
			
			query.setParameter("code"  , code.toUpperCase() );
			query.setParameter("date"  , new Date() );
	
			List servicos = query.list();
	
			// Se houver um registro, retornamos sendo o Item da pesquisa.
			// Se houver mais de um registro temos um problema de banco de dados.
			// Se não houver nenhum registro, retornamos nulo.
			if ( servicos.size() == 1 )
				return (Servico) servicos.get(0);
	
			else if ( servicos.size() > 1 )
				throw new DaoException("Too many entities found. There should be only one.");
	
			else
				return null;	
		
		} catch( Exception exp ) { 
			
			throw new DaoException(exp);
			
		} finally {
			session.close();
		}  
	}


	/** Obtém uma lista de  Recall a partir do chassi.
	 * 
	 *  @param chassi
	 *      <code>String<code>     
	 *
	 *  @return
	 *      Um <code>List</code> populado com os objetos da 
	 *      entidade específica, solicitada, ou caso nenhuma entidade
	 *      seja encontrada, retornará <code>null</code>.
	 *      
	 *  @throws DaoException
	 *      Se houverem problemas nas camadas inferiores, estes serão 
	 *      convertidos para uma DaoException.
	 */     
	public List listRecall(String chassi) throws DaoException {

		//System.out.println("Valores:" +  chassi.toUpperCase());
		DetachedCriteria criteria = super.getDetachedCriteria(Recall.class);
		criteria.add( Expression.le("chassiInicial" , chassi.toUpperCase()));
		criteria.add( Expression.ge("chassiFinal"   , chassi.toUpperCase()));
		criteria.addOrder(Order.asc("numeroIT"));

		return super.getHibernateTemplate().findByCriteria(criteria);

	}
	
	/** Obtém uma lista de  Recall não executados a partir do chassi.
	 * 
	 *  @param chassi
	 *      <code>String<code>     
	 *
	 *  @return
	 *      Um <code>List</code> populado com os objetos da 
	 *      entidade específica, solicitada, ou caso nenhuma entidade
	 *      seja encontrada, retornará <code>null</code>.
	 *      
	 *  @throws DaoException
	 *      Se houverem problemas nas camadas inferiores, estes serão 
	 *      convertidos para uma DaoException.
	 */     
	public List listRecallNotExByChassi(String chassi) throws DaoException {

		//System.out.println("Valores:" +  chassi.toUpperCase());
		DetachedCriteria criteria = super.getDetachedCriteria(Recall.class);
		criteria.add( Expression.le("chassiInicial" , chassi.toUpperCase()));
		criteria.add( Expression.ge("chassiFinal"   , chassi.toUpperCase()));
		criteria.addOrder(Order.asc("numeroIT"));
		
		DetachedCriteria criteriaG = super.getDetachedCriteria(GarantiaHeader.class);
		criteriaG.add( Expression.eq("modelo", chassi ).ignoreCase() );
		
		List recalls 	= super.getHibernateTemplate().findByCriteria(criteria);
		List garantias 	= super.getHibernateTemplate().findByCriteria(criteriaG);
		
		//System.out.println("Valores:" +  recalls.isEmpty() + " Campanhas:" + garantias.isEmpty());
		
		if ( recalls.isEmpty() || ( !recalls.isEmpty() && garantias.isEmpty()) )
			return recalls;
		else {
			List recallsVal 	= new ArrayList();
			Iterator itR 		= recalls.iterator();
			Iterator itG		= null;
			Recall rc 			= null;
			GarantiaHeader gh 	= null;
			
			while( itR.hasNext() ) {
				
				rc  = (Recall)itR.next();
				itG = garantias.iterator();
				while( itG.hasNext() ){
					
					gh = (GarantiaHeader)itG.next();
					
					if ( !gh.getRecall().getNumeroIT().equalsIgnoreCase(rc.getNumeroIT()) )
						recallsVal.add(rc);
					
				}				
			}			
			return recallsVal;
		}
	}
	
	/** Recupera a lista de peças da garantia relacionada.
	 *
	 *  @param garantiaId
	 *      <code>Integer<code> id da entidade garantia relacionada.
	 *  @return
	 *      Um <code>List</code> lista de peças.
	 *      
	 * @throws BusinessException
	 *      Se houverem erros de execução ou nas camadas 
	 *      abaixo dos serviços, serão encapsulados neste
	 *      tipo de <code>Exception</code>.
	 */	
	public List listPecas(GarantiaHeader garantia) throws DaoException {
		
		String sqlRes =" SELECT distinct yl.garantia_id	" +
					   "		, yl.line_id" +
					   "		, yl.item_id" +
					   "		, yl.quantidade" +
					   "		, yl.envia_peca" +
					   "		, yl.cobra_peca" +
					   "		, yl.peca_faltante" +
					   "		, yl.VL_CUSTO_PADRAO_SP " +
					   "		, yl.VL_PECA_GARANTIA" +
					   "		, yl.VL_PRECO_SUGERIDO " +
					   "		, yl.VL_PRECO_FOB" +
					   "		, yl.FATOR_GARANTIA_ID " +
					   "		, yi.item_code" +
					   "		, yi.descricao" +
					   "		, yl.peca_causadora " +
					   " FROM ym_sg_garantia_line    yl,         		" +
					   "      ym_sg_item             yi,         		" +
					   "      ym_sg_garantia_header  yh		     		" + 
					   " WHERE yl.garantia_id = yh.garantia_id   		" +		   
					   //" AND   yh.organization_id = yi.organization_id 	" +
					   " AND   yl.item_id = yi.item_id                  " +
					   " AND   yi.organization_id = 91                  " +
					   " AND   yl.garantia_id = :garantiaId            	" +
					   " AND   yl.start_date <= :date        			" +
					   " AND   (yl.end_date > :date 		 			" +
					   " OR    yl.end_date is null)		 				" +
					   " ORDER BY yl.line_id 							";

		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sqlRes);
		
		//query.addEntity(GarantiaLine.class);
		query.setParameter("garantiaId"		 , garantia.getEntityId());
		//query.setParameter("organizationId"  , garantia.getOrganizationId());
		query.setParameter("date"			 , new Date());
		
		List results = new ArrayList();
		Iterator rsIt = null;
		
		try {
			
			rsIt =  query.list().iterator();
			
		} catch( Exception exp ) { 
		
			throw new DaoException(exp);
			
		} finally {
			session.close();
		}
		
		
		GarantiaLine garantiaLine = null;
		String pecaCausadora = "";
		Item item = null;
		
		while ( rsIt.hasNext() ) {
			
			Object[] row = (Object[]) rsIt.next();
			garantiaLine = new GarantiaLine();	
			//System.out.println("Garantia:" + garantia.getEntityId() + " - LineID:" + ((BigDecimal)row[1]));
			
			//GarantiaLineId compositeEntityId  = new GarantiaLineId(garantia.getEntityId(), (Long)row[1]);
			garantiaLine.setCompositeEntityId(garantia.getEntityId(), new Long(((BigDecimal)row[1]).longValue()));
			
			item = new Item(); 
			item.setEntityId(new Long(((BigDecimal)row[2]).longValue()));
			item.setItemCode(row[12].toString());
			item.setDescricao(row[13].toString());
			//item.setOrganizationId(Long.valueOf(((BigDecimal)row[14]).longValue()));
			
			garantiaLine.setItem(item);
			//System.out.println("Quantidade:" + new Integer(((BigDecimal)row[3]).intValue()));
			garantiaLine.setQuantidade(new Integer(((BigDecimal)row[3]).intValue()));
			garantiaLine.setEnviaPeca(row[4].toString());
			garantiaLine.setCobraPeca(row[5].toString());
			garantiaLine.setPecaFaltante(row[6].toString());
			garantiaLine.setValorCustoPadraoSP(((BigDecimal)row[7]).doubleValue());
			garantiaLine.setValorPecaGarantia(((BigDecimal)row[8]).doubleValue());
			garantiaLine.setValorPrecoSugerido(((BigDecimal)row[9]).doubleValue());
			garantiaLine.setValorPrecoFob(((BigDecimal)row[10]).doubleValue());
			garantiaLine.setFatorGarantiaId((BigDecimal)row[11]);
			
			pecaCausadora = row[14] != null ? row[14].toString() : null;
			
			garantiaLine.setPecaCausadora(pecaCausadora);
			
			results.add(garantiaLine);
			
		}
		
		return results;
	}
	
	
	/** Recupera uma GarantiaLine pelo id.
	 *
	 *  @param compositeId
	 *      Referência da entidade na camada de persistência.
	 *  @return
	 *      Um objeto <code>GarantiaLine</code>.
	 *      
	 * @throws BusinessException
	 *      Se houverem erros de execução ou nas camadas 
	 *      abaixo dos serviços, serão encapsulados neste
	 *      tipo de <code>Exception</code>.
	 */	
	public GarantiaLine getGarantiaLine(GarantiaLineId compositeId)  throws DaoException {
		
		String sqlRes =" SELECT distinct yl.garantia_id	" +
					   "		, yl.line_id" +
					   "		, yl.item_id" +
					   "		, yl.quantidade" +
					   "		, yl.envia_peca" +
					   "		, yl.cobra_peca" +
					   "		, yl.peca_faltante" +
					   "		, yl.VL_CUSTO_PADRAO_SP " +
					   "		, yl.VL_PECA_GARANTIA" +
					   "		, yl.VL_PRECO_SUGERIDO " +
					   "		, yl.VL_PRECO_FOB" +
					   "		, yl.FATOR_GARANTIA_ID " +
					   "		, yi.item_code" +
					   "		, yi.descricao" +
					   "		, yl.peca_causadora" +
					   " FROM ym_sg_garantia_line    yl,         		" +
					   "      ym_sg_item             yi,         		" +
					   "      ym_sg_garantia_header  yh		     		" + 
					   " WHERE yl.garantia_id = yh.garantia_id   		" +		   
					   //" AND   yh.organization_id = yi.organization_id 	" +
					   " AND   yl.item_id = yi.item_id                  " +
					   " AND   yi.organization_id = 91                  " +
					   " AND   yl.garantia_id = :garantiaId            	" +
					   " AND   yl.line_id     = :lineId					" +		
					   " AND   yl.start_date <= :date        			" +
					   " AND   (yl.end_date >  :date 		 			" +
					   " OR    yl.end_date is null)		 				" +
					   " ORDER BY yl.line_id 							";

		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sqlRes);
		
		//query.addEntity(GarantiaLine.class);
		query.setParameter("garantiaId"		 , compositeId.getGarantiaId());
		query.setParameter("lineId"		 	 , compositeId.getLineId());
		//query.setParameter("organizationId"  , garantia.getOrganizationId());
		query.setParameter("date"			 , new Date());
		
		List results = new ArrayList();
		
		try {
			
			results = query.list();
			
		} catch( Exception exp ) { 
		
			throw new DaoException(exp);
			
		} finally {
			session.close();
		}
		
		// Se houver um registro, retornamos sendo o Revisao da pesquisa.
	    // Se houver mais de um registro temos um problema de banco de dados.
	    // Se não houver nenhum registro, retornamos nulo.
	    if ( results.size() == 1 ) {        
		
			Iterator rsIt =  results.iterator();
			GarantiaLine garantiaLine = new GarantiaLine();	
			String pecaCausadora = "";
			while ( rsIt.hasNext() ) {
				
				Object[] row = (Object[]) rsIt.next();
				
				//System.out.println("Garantia:" + garantia.getEntityId() + " - LineID:" + ((BigDecimal)row[1]));
				
				//GarantiaLineId compositeEntityId  = new GarantiaLineId(garantia.getEntityId(), (Long)row[1]);
				garantiaLine.setCompositeEntityId(compositeId);				
				Item item = new Item(); 
				item.setEntityId(new Long(((BigDecimal)row[2]).longValue()));
				item.setItemCode(row[12].toString());
				item.setDescricao(row[13].toString());
				//item.setOrganizationId(Long.valueOf(((BigDecimal)row[14]).longValue()));
				
				garantiaLine.setItem(item);
				//System.out.println("Quantidade:" + new Integer(((BigDecimal)row[3]).intValue()));
				garantiaLine.setQuantidade(new Integer(((BigDecimal)row[3]).intValue()));
				garantiaLine.setEnviaPeca(row[4].toString());
				garantiaLine.setCobraPeca(row[5].toString());
				garantiaLine.setPecaFaltante(row[6].toString());
				garantiaLine.setValorCustoPadraoSP(((BigDecimal)row[7]).doubleValue());
				garantiaLine.setValorPecaGarantia(((BigDecimal)row[8]).doubleValue());
				garantiaLine.setValorPrecoSugerido(((BigDecimal)row[9]).doubleValue());
				garantiaLine.setValorPrecoFob(((BigDecimal)row[10]).doubleValue());
				garantiaLine.setFatorGarantiaId((BigDecimal)row[11]);
				
				pecaCausadora = row[14] != null ? row[14].toString() : null;
				
				garantiaLine.setPecaCausadora(pecaCausadora);
				
			}
			
			return garantiaLine;
			
			   
	    } else if ( results.size() > 1 )
		    throw new DaoException("Too many entities found. There should be only one.");
		else
		    return null;
	}
	
	/** Lista as revisões anteriores a enviada existentes no banco de dados de acordo
	*  com o modelo.
	*
	* @param garantia
	*   <code>GarantiaHeader</code> Uma Entidade de Garantia.
	*   
	* @param linha
	*   <code>Linha</code> Uma Entidade de Linha.
	* 
	*  @return
	*      Um valor <code>AlertGarantia</code> null para informar que todas as revisões estão ok.
	*      
	* @throws BusinessException
	*      Se houverem erros de execução ou nas camadas 
	*      abaixo dos serviços, serão encapsulados neste
	*      tipo de <code>Exception</code>.
	*/
	public AlertGarantia isRevisaoAnterior(GarantiaHeader garantia, Linha linha) throws DaoException {
		//System.out.println("----> isRevisaoAnterior -Lista as revisões anteriores a enviada existentes no banco de dados de acordo com o modelo.");
		
		AlertGarantia alertGarantia = null; 
   
		String sqlRes = "";
		String sqlCp  = "";
		
		Session session   = null;
		
		try {
			
			session   = super.getSession();		   
			SQLQuery queryRes = null;
			SQLQuery queryCP  = null;
	   
			//System.out.println(" Linha: " + linha.getEntityId());
	   
			if ( linha.getEntityId().equals(new Long(1)) ) {
				
				sqlRes =   " SELECT  revisao_id qtRes 		 " +
						   " FROM ym_sg_revisao  		     " + 
						   " WHERE modelo = :modeloRes" +
						   " AND   tempo_km < :quilometragem " +
						   " AND   start_date < :date        " +
						   " AND   (end_date > :date 		 " +
						   " OR    end_date is null)		 ";
	   
				sqlCp =   " SELECT  cp.cupom_id qtCp          	  " +
						  " FROM ym_sg_cupom cp, ym_sg_revisao re " + 
						  " WHERE cp.modelo = :modeloCp    " +
						  " AND   re.revisao_id = cp.revisao_id   " +
						  " AND   re.start_date < :date			  " +
						  " AND   (re.end_date > :date 			  " +
						  " OR    re.end_date is null)            " +
						  " AND   cp.start_date < :date			  " +
						  " AND   (cp.end_date > :date 			  " +
						  " OR    cp.end_date is null)			  ";
	   
			   queryRes = session.createSQLQuery(sqlRes);
			   queryCP  = session.createSQLQuery(sqlCp);
			   
			   //System.out.println(" modeloRes: " + garantia.getModelo().substring(0, 5));
			   queryRes.setParameter("modeloRes", ControllerHelper.getModeloByChassi(garantia.getModelo()));
			   
			   //System.out.println(" quilometragem: " + garantia.getQuilometragem());
			   queryRes.setParameter("quilometragem", garantia.getQuilometragem().toString());
			   
			   queryRes.setParameter("date", new Date());
			   
			   //System.out.println(" modeloCp: " + garantia.getModelo().toUpperCase());			   
			   queryCP.setParameter("modeloCp", garantia.getModelo().toUpperCase());
			   
			   queryCP.setParameter("date", new Date());
			
			} else {
				sqlRes =" SELECT  revisao_id qtRes          " +
						" FROM ym_sg_revisao  			    " + 
						" WHERE modelo = :modeloRes 		" +
						" AND  ( tempo_dias < :tempoDias    " +
						" OR    tempo_horas < :tempoHoras ) " +
						" AND   tempo_horas is not null     " +
						" AND   tempo_dias is not null      " +
						" AND  ( start_date < :date		    " +
						" AND   (end_date > :date     	    " +
						" OR    end_date is null)	)       ";
	
			   sqlCp = " SELECT  cp.cupom_id qtCp 				" +
					   " FROM ym_sg_cupom cp, ym_sg_revisao re  " + 
					   " WHERE cp.modelo = :modeloCp 			" +
					   " AND   re.revisao_id = cp. revisao_id 	" +
					   " AND  ( re.start_date < :date			" +
					   " AND   (re.end_date > :date 			" +
					   " OR    re.end_date is null)	)			" +
					   " AND  ( cp.start_date < :date			" +
					   " AND   (cp.end_date > :date 			" +
					   " OR    cp.end_date is null)	)			";
	   
			   queryRes = session.createSQLQuery(sqlRes);
			   queryCP  = session.createSQLQuery(sqlCp);
			   queryRes.setParameter("modeloRes"  , ControllerHelper.getModeloByChassi(garantia.getModelo()));
			   queryRes.setParameter("tempoDias"  , garantia.getDiasUso().toString());
			   queryRes.setParameter("tempoHoras" , garantia.getHorasUso().toString());
			   queryRes.setParameter("date"  	  , new Date());
			   queryCP.setParameter("modeloCp"	  , garantia.getModelo().toUpperCase() );
			   queryCP.setParameter("date"  	  , new Date());
		   
			}
	   
		   List revisoes = queryRes.list();
		   List cupons   = queryCP.list();
		   
		   //System.out.println(" Revisões: " + (revisoes != null) + "  Cupons:" + (cupons != null));
			
		   if ( revisoes != null) {
			   
			   if ( cupons != null ) {
				   
				   //System.out.println(" Qtde Revisões: " + revisoes.size() + " Qtde Cupons:" + cupons.size());
				   
				   // Se o número de cupons for maior que a quantidade de revisões necessárias para a quilometragem informada,
				   // Pode existir um problema no valor da quilometragem ou dias e horas informados
				   if ( cupons.size() > revisoes.size() ) {
					   
					   alertGarantia = new AlertGarantia();
						
					   alertGarantia.setAlertGarantiaText("O número de revisões realizadas é maior que o número de revisões necessárias!");
					   alertGarantia.setAlertGarantiaSeveridade(AlertGarantia.SEVERIDADE_WARNING);
					   alertGarantia.setAlertGarantiaKey("garantia.msg.warning.preview.realXNec");				   
					   
				   } else if ( cupons.size() < revisoes.size() ) {
					   
					   alertGarantia = new AlertGarantia();
						
					   alertGarantia.setAlertGarantiaText("Uma ou mais revisão anterior não foi realizada para este chassi!");
					   alertGarantia.setAlertGarantiaSeveridade(AlertGarantia.SEVERIDADE_WARNING);
					   alertGarantia.setAlertGarantiaKey("garantia.msg.warning.preview.revision");
					   
				   }
				   
			   } else {
				   
					alertGarantia = new AlertGarantia();
						
					alertGarantia.setAlertGarantiaText("Revisão de entrega não foi cadastrada para este chassi!");
					alertGarantia.setAlertGarantiaSeveridade(AlertGarantia.SEVERIDADE_WARNING);
					alertGarantia.setAlertGarantiaKey("garantia.msg.warning.preview.entrega");			   
				   
			   }			   
			   
		   } else {
			   
			    alertGarantia = new AlertGarantia();
				
				alertGarantia.setAlertGarantiaText("Não foram localizadas revisões para o chassi informado!");
				alertGarantia.setAlertGarantiaSeveridade(AlertGarantia.SEVERIDADE_ERROR);
				alertGarantia.setAlertGarantiaKey("garantia.msg.error.preview.revisionEmpty");
			   
		   }
			
		   return alertGarantia;
		   
		} catch( Exception exp ) { 
			
			throw new DaoException(exp);
			
		} finally {
			
			session.close();
			
		}

   }  
	   
	   /** retorna o cupom de revisao para um modelo de chassi
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
	   
	   /** Recupera um entidade de Tempo Padrão para um serviço
	    *  Necessário para para definir o valor de serviço para garantia
	    *  
	    * @param garantiaId
	    * 	<code>Long</code>
	    * 
	    * @param chassi
	    * 	<code>String</code>
	    * 
	    * @return tempoPadrao
	    * 	<code>TempoPadrao</code> uma Entidade de TempoPadrao.
	    * 
	    * @throws DaoException
	    * 	Se houverem erros de execução ou nas camadas 
	    *   abaixo dos serviços, serão encapsulados neste
	    *   tipo de <code>Exception</code>.
	    */
	   public TempoPadrao getTempoPadrao(Servico servico, String chassi) throws DaoException {
		   
		   //System.out.println("Dados enviados para recuperar o o tempo padrão - Serviço :" + servico.getEntityId() + " Modelo:" + ControllerHelper.getModeloByChassi(chassi));
		   
		   DetachedCriteria criteria = super.getDetachedCriteria(TempoPadrao.class);
		   criteria.add( Expression.eq("modelo"  , ControllerHelper.getModeloByChassi(chassi)));	   
	       criteria.add( Expression.eq("servico" , servico));

	       List results = super.getHibernateTemplate().findByCriteria(criteria);

	       // Se houver um registro, retornamos sendo o Revisao da pesquisa.
	       // Se houver mais de um registro temos um problema de banco de dados.
	       // Se não houver nenhum registro, retornamos nulo.
	       if ( results.size() == 1 )
	           return (TempoPadrao) results.get(0);
	           
	       else if ( results.size() > 1 )
	           throw new DaoException("Too many entities found. There should be only one.");
	       
	       else
	           return null;
		   
	   }
	   
	   /** Recupera um entidade de ValorServico para um serviço
		 *  Necessário para para definir o valor de serviço para garantia
		 *  O valor de serviço para a Garantia é determinado pela revisão de entrega,
		 *  Porisso pegamos a revisão de entrega da lista para retornar o objeto 
		 *  
		 * @param codRegiao
		 * 	<code>Long</code> Código da região da concessionária
		 * 
		 * @param chassi
		 * 	<code>String</code>
		 * 
		 * @return valorServico
		 * 	<code>ValorServico</code> uma Entidade de ValorServico.
		 * 
		 * @throws DaoException
		 * 	Se houverem erros de execução ou nas camadas 
		 *   abaixo dos serviços, serão encapsulados neste
		 *   tipo de <code>BusinessException</code>.
		 */
	   public ValorServico getValorServico(Long codRegiao, String chassi) throws DaoException {
		   
		   //System.out.println("Dados enviados para recuperar o valor de serviço - Código da regisão :" + codRegiao + " Modelo:" + ControllerHelper.getModeloByChassi(chassi));
		   
		   DetachedCriteria criteria = super.getDetachedCriteria(ValorServico.class);
		   criteria.add( Expression.eq("modelo"       , ControllerHelper.getModeloByChassi(chassi)) );	   
	       criteria.add( Expression.eq("codigoRegiao" , codRegiao));

	       List results = super.getHibernateTemplate().findByCriteria(criteria);

	       //System.out.println("Valor de Serviços Results:" + results.size());
	       
	       
	       Iterator it = results.iterator();
	       ValorServico valorServico = null;
	       
	       while ( it.hasNext() ) {
	    	   
	    	   ValorServico valorServicoTmp = (ValorServico) it.next();
	    	   if ( valorServicoTmp.getRevisao().getNumeroRevisao().equals(new Long(1)) ) 
	    		   valorServico = valorServicoTmp;	    	   
	    	   
	       }
	       
	       return valorServico;
	       
	       // Se houver um registro, retornamos sendo o Revisao da pesquisa.
	       // Se houver mais de um registro temos um problema de banco de dados.
	       // Se não houver nenhum registro, retornamos nulo.
	       //if ( results.size() > 0 )
	           //return (ValorServico) results.get(0);
	       //else
	           //return null;
		   
		   
	   }
	   
	   /** Verifica se já existe uma garantia cadastrada com os parâmetros passados
		 *  
		 * @param garantia
		 * 	<code>GarantiaHeader</code> Entidade de Garantia com os parâmetros para validação.
		 * 
		 * @return boolean
		 * 	<code>TRUE</code> quando já existir uma garantia.
		 * 		
		 * @throws DaoException
		 * 	Se houverem erros de execução ou nas camadas 
		 *   abaixo dos serviços, serão encapsulados neste
		 *   tipo de <code>BusinessException</code>.
		 */
	   public boolean isEcxistsGarantia( GarantiaHeader garantia ) throws DaoException {
		   
		   DetachedCriteria criteria = super.getDetachedCriteria(GarantiaHeader.class);
		   criteria.add( Expression.eq("modelo"        , garantia.getModelo())        );	   
	       criteria.add( Expression.eq("numeroOS"      , garantia.getNumeroOS())      );
	       criteria.add( Expression.eq("dataAberturaOS", garantia.getDataAberturaOS()));
	       
	       if ( Linha.TIPO_MOTOCICLETA.equals(garantia.getLote().getLinha().getEntityId()) ) {
	    	   
	    	   criteria.add( Expression.eq("quilometragem", garantia.getQuilometragem()));	    	   
	    	   
	       } else {
	    	   
	    	   criteria.add( Expression.eq("diasUso"      , garantia.getDiasUso() ));
	    	   
	       }

	       List results = super.getHibernateTemplate().findByCriteria(criteria);

	       // Se houver um registro, retornamos sendo o Revisao da pesquisa.
	       // Se houver mais de um registro temos um problema de banco de dados.
	       // Se não houver nenhum registro, retornamos nulo.
	       if ( results.size() > 0 )
	           return true;	           
	       else 
	    	   return false;
		   
	   }
	   
	   /** Remoção lógica uma Solicitação de Garantia e seus dependentes.
		 * 
		 *  @param garatnia
		 *  @param usuario
		 *  @throws DaoException
		 */
		public void remove(GarantiaHeader garantia, Usuario usuario) throws DaoException {
			
			final GarantiaHeader	garantiaFinal = garantia;
			final Usuario 			usuarioFinal  = usuario;
	    	
			super.getHibernateTemplate().executeFind(
		    		
	    			new HibernateCallback() {
	    				
	    				public Object doInHibernate(Session session) throws HibernateException {
	    					
	    					try {
	    						//Removemos o relacionamento com a campanha, se existir
		    					if ( garantiaFinal.getCampaign() != null ) {      			
		    		    			
		    		    			CampaignBilling campaignBilling = getCampaignBillingByGarantia(garantiaFinal);
		    		    			ControllerHelper.prepare(campaignBilling, (Long)usuarioFinal.getEntityId());
		    		    			remove(campaignBilling);
		    		    			
		    		    		}
		    					//Removemos os servicos associados, se existirem
		    					if ( garantiaFinal.getGrupos() != null ) {
		    						
		    						Iterator itSg = garantiaFinal.getGrupos().iterator();
		    						while ( itSg.hasNext() ) {
		    						
		    							ServicoGrupo servicoGrupo = (ServicoGrupo)itSg.next();
		    							ControllerHelper.prepare(servicoGrupo, (Long)usuarioFinal.getEntityId());
			    		    			remove(servicoGrupo);
			    		    			
		    						}
		    					}		    					
		    					//Removemos as peças, se existirem		    					
		    					if ( garantiaFinal.getListGarantiaLine() != null ) {
		    						
		    						Iterator itPc = garantiaFinal.getListGarantiaLine().iterator();
		    						while ( itPc.hasNext() ) {
		    						
		    							GarantiaLine garantiaLine = (GarantiaLine)itPc.next();
		    							ControllerHelper.prepare(garantiaLine, (Long)usuarioFinal.getEntityId());
			    		    			remove(garantiaLine);
			    		    			
		    						}
		    					}
		    					
		    					// Removemos a descrição de defeito quando existir
		    					if ( garantiaFinal.getDefeitos() != null ) {
		    						
		    						Iterator itDf = garantiaFinal.getDefeitos().iterator();
		    						while ( itDf.hasNext() ) {
		    						
		    							DescricaoDefeito descricaoDefeito = (DescricaoDefeito)itDf.next();
		    							ControllerHelper.prepare(descricaoDefeito, (Long)usuarioFinal.getEntityId());
			    		    			remove(descricaoDefeito);			    		    			
		    						}
		    					}
		    					
		    					// Removemos a Informação de Mercado, quando existir
		    					if ( garantiaFinal.getHasInfoMercado() ) {
		    						
		    						InfoMercGarantia infoMercGarantia = new InfoMercGarantia();
		    						infoMercGarantia.setInfoMercado(garantiaFinal.getInfoMercado());
		    						infoMercGarantia.setGarantia(garantiaFinal);
		    						infoMercGarantia = getInfoMercGarantia(infoMercGarantia);
		    						ControllerHelper.prepare(infoMercGarantia,(Long)usuarioFinal.getEntityId());
		    						remove(infoMercGarantia);		    						
		    					}
		    		    		
		    		    		// Removemos e entidade
		    		    		ControllerHelper.prepare(garantiaFinal, (Long)usuarioFinal.getEntityId());
		    		    		
		    		    		garantiaFinal.setStatusGarantia(StatusGarantia.getInstance(StatusGarantia.STATUS_CANCELADA));
		    		    		
		    		    		remove(garantiaFinal);
		    		    		
		    		    		return null;
		    		    		
	    					} catch( DaoException dExp ) { 
	    						
	    						throw new HibernateException(dExp);
	    						
	    					}
	    				}   				
	    				
	    			}
	    			
	    	);
		}
		
		/** Obtém uma entidade de GarantiaLine a partir de seu id composto e da empresa do faturamento.
		 * 
		 *  @param garantiaLineId
		 *      <code>GarantiaLineId<code>. Entidade com o id composto.
		 *      
		 *  @param organizationId
		 *      <code>Long<code> id da empresa do faturamento.
		 *      	    
		 *  @return
		 *      Um <code>GarantiaLineId</code> populado com os dados da 
		 *      entidade específica, solicitada, ou caso nenhuma entidade
		 *      seja encontrada, retornará <code>null</code>.
		 *      
		 *  @throws DaoException
		 *      Se houverem problemas nas camadas inferiores, estes serão 
		 *      convertidos para uma DaoException.
		 */     
		public GarantiaLine getGarantiaLine(GarantiaLineId garantiaLineId, Long organizationId) throws DaoException { 
			
			DetachedCriteria criteria = super.getDetachedCriteria(GarantiaLine.class);
			criteria.add( Expression.eq("compositeEntityId"  , garantiaLineId ) );
			
			List results = super.getHibernateTemplate().findByCriteria(criteria);

			// Se houver um registro, retornamos sendo o Revisao da pesquisa.
			// Se houver mais de um registro temos um problema de banco de dados.
			// Se não houver nenhum registro, retornamos nulo.
			if ( results.size() == 1 )
	           return (GarantiaLine) results.get(0);
	           
			else if ( results.size() > 1 ) {
				//Se a peça for do segmento COMPL pegamos o primeiro, caso contrário lançamos a exception
				GarantiaLine garantiaLine = (GarantiaLine) results.get(0);
				
				if ( "COMPL".equalsIgnoreCase(garantiaLine.getItem().getSegmento()) )
					return garantiaLine;
				else
					throw new DaoException("Too many entities found. There should be only one.");
	       
			} else
	           return null;
	       
		}
}
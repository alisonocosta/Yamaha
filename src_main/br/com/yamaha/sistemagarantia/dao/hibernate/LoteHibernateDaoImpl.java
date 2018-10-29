/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......LoteHibernateDaoImpl.java
 *   .: Criação.....28 de junho, 06:51
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...DAO com tarefas específicas para a entidade Lote.
 */
package br.com.yamaha.sistemagarantia.dao.hibernate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import br.com.resource.infra.dao.hibernate.HibernateDaoImpl;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.dao.LoteDao;
import br.com.yamaha.sistemagarantia.model.Concessionaria;
import br.com.yamaha.sistemagarantia.model.GarantiaHeader;
import br.com.yamaha.sistemagarantia.model.GarantiaLine;
import br.com.yamaha.sistemagarantia.model.Lote;
import br.com.yamaha.sistemagarantia.model.StatusLote;
import br.com.yamaha.sistemagarantia.view.GarantiaVO;
import br.com.yamaha.sistemagarantia.view.LoteCompactVO;

/** Objeto DAO com tarefas específicas para a entidade <b>Lote</b>. 
 * 
 *  @author Edson Luiz Sumensari
 */
public class LoteHibernateDaoImpl extends HibernateDaoImpl implements LoteDao {

	/** Lista as linhas de peça de uma garantia de acordo com o lote.
     * 
     *  @param garantia
     *      <code>GarantiaHeader</code> Uma entidade que representa uma Garantia.
     *  
     *  @return
     *      <code>List</code> Uma lista de peças de garantia de uma determinada garantia.
     *  
     *  @throws DaoException
     *      Se houverem erros durante a execução estes serão lançados
     *      como uma Exception deste tipo.
     */
	public List listGarantiaLineByGarantia(GarantiaHeader garantia) throws DaoException{  
		
		DetachedCriteria criteria = super.getDetachedCriteria(GarantiaLine.class);
		criteria.createAlias("item", "it");
		criteria.add(Expression.eq("compositeEntityId.garantiaId", garantia.getEntityId()));
		criteria.add(Expression.eq("it.organizationId", new Long(91)));
		criteria.addOrder(Order.desc("compositeEntityId.lineId")); 	    
		
       return super.getHibernateTemplate().findByCriteria(criteria);
       
	}
	
	
	/** Lista de lotes.
     * 
     *  @param status
     *      <code>List</code> Uma entidade que representa o status  de um lote.
     *      
     *  @param concessionaria
     *  	<code>Concessionaria</code> Uma entidade que representa aconcessionária do lote.
     *  
     *  @return
     *      <code>List</code> Uma lista de lotes de uma concessionária de acordo com o status.
     *  
     *  @throws DaoException
     *      Se houverem erros durante a execução estes serão lançados
     *      como uma Exception deste tipo.
     */
    public List listByStatus(List status, Concessionaria concessionaria) throws DaoException{  
    	
    	DetachedCriteria criteria = super.getDetachedCriteria(Lote.class);
		criteria.add(Expression.in("statusLote", status));
		criteria.add(Expression.eq("concessionaria", concessionaria));
		criteria.add(Expression.gt("entityId", new Integer(0)));
		criteria.addOrder(Order.desc("entityId")); 	    
		
        List results = super.getHibernateTemplate().findByCriteria(criteria);
        
        /*/////////////////////////////////////////////////////////////////////
         * Após recuperarmos os lotes, vamos pegar as peças dos lotes de garantia
         * e atualizar a lista buscando sempre pela empresa 91.
         * Esta implementação foi necessária, pois existe duplicação na tabela de item 
         * 
         */
        Iterator itRes = results.iterator();
        while ( itRes.hasNext() ) { 
        	
        	Lote lote = (Lote) itRes.next();
        	
        	if ( lote.getTipoLote().getEntityId().equals(new Long(2)) ) {
	        	
        		Iterator itGs = lote.getGarantias().iterator();
	        	
        		while ( itGs.hasNext() ) {
	        		
	        		GarantiaHeader garantia = (GarantiaHeader) itGs.next(); 	        		
	        		garantia.setListGarantiaLine(this.listGarantiaLineByGarantia(garantia));	        		   		
	        	}    
        	}
        } 
 	   return results;	    	
    	
    }
    
   /** Recupera um lote pelo id.
    *
    *  @param entityId
    *      Referência da entidade na camada de persistência.
    *      
    *  @param concessionaria
    *      Concessionária do usuário.
    *      
    *  @return
    *      Um objeto <code>Lote</code>.
    *      
    * @throws BusinessException
    *      Se houverem erros de execução ou nas camadas 
    *      abaixo dos serviços, serão encapsulados neste
    *      tipo de <code>Exception</code>.
    */
   public Lote getById(Serializable entityId, Concessionaria concessionaria) throws DaoException { 
	   
	   DetachedCriteria criteria = super.getDetachedCriteria(Lote.class);
	    criteria.add( Expression.eq("entityId"       , entityId) );
	    criteria.add( Expression.eq("concessionaria" , concessionaria) );	    

       List results = super.getHibernateTemplate().findByCriteria(criteria);
       
       	// Se houver um registro, retornamos sendo o Revisao da pesquisa.
		// Se houver mais de um registro temos um problema de banco de dados.
		// Se não houver nenhum registro, retornamos nulo.
		if ( results.size() == 1 )
			return (Lote) results.get(0);

		else if ( results.size() > 1 )
			throw new DaoException("Too many entities found. There should be only one.");

		else
			return null;	   
	   
   }
   
   /** Recupera um lote pelo id.
    *
    *  @param entityId
    *      Referência da entidade na camada de persistência.
    *      
    *  @param concessionaria
    *      Concessionária do usuário.
    *      
    *  @return
    *      Um objeto <code>Lote</code>.
    *      
    * @throws BusinessException
    *      Se houverem erros de execução ou nas camadas 
    *      abaixo dos serviços, serão encapsulados neste
    *      tipo de <code>Exception</code>.
    */
   public LoteCompactVO getByIdList(Serializable entityId, Concessionaria concessionaria) throws DaoException { 

	   DetachedCriteria criteria = super.getDetachedCriteria(Lote.class);
	   criteria.add( Expression.eq("entityId"       , entityId) );
	   criteria.add( Expression.eq("concessionaria" , concessionaria) );	    

	   List results = super.getHibernateTemplate().findByCriteria(criteria);

	   // Se houver um registro, retornamos sendo o Revisao da pesquisa.
	   // Se houver mais de um registro temos um problema de banco de dados.
	   // Se não houver nenhum registro, retornamos nulo.
	   if ( results.size() == 1 ) {
		   Lote lote =  (Lote) results.get(0);

		   LoteCompactVO loteVO = new LoteCompactVO();
		   loteVO.setLoteId(lote.getEntityId());	
		   loteVO.setDescricaoLinha(lote.getLinha().getDescricao());
		   loteVO.setLinhaId(lote.getLinha().getEntityId());			
		   loteVO.setTipoId(lote.getTipoLote().getEntityId());
		   loteVO.setDescricaoTipoLote(lote.getTipoLote().getDescricao());
		   loteVO.setStatusId(lote.getStatusLote().getEntityId());
		   loteVO.setStatusCor(lote.getStatusLote().getCorStatus());
		   loteVO.setStatusMascara(lote.getStatusLote().getMascara());
		   loteVO.setDescricaoStatusAdiantamento(lote.getStatusAdiantamento() != null ? lote.getStatusAdiantamento().getDescricao() : null);
		   loteVO.setDataLote(lote.getDataLote());
		   loteVO.setDataFechamento(lote.getDataFechamento());
		   loteVO.setDataLiberacao(lote.getDataLiberacao());
		   loteVO.setDataEnvioAdiant(lote.getDataEnvioAdiant());
		   loteVO.setHasMovimento(lote.getIsMovimento() ? Boolean.TRUE : Boolean.FALSE);	
		   loteVO.setDescricaoReduzidaStatus(lote.getStatusLote().getDescricaoReduzida());
		   loteVO.setGarantias(lote.getGarantias());

		   return loteVO;

	   } else if ( results.size() > 1 )
		   throw new DaoException("Too many entities found. There should be only one.");

	   else
		   return null;	   

   }
   
   /** Lista de lotes Novo.
    * 
    *  @param status
    *      <code>List</code> Uma entidade que representa o status  de um lote.
    *      
    *  @param concessionaria
    *  	<code>Concessionaria</code> Uma entidade que representa aconcessionária do lote.
    *  
    *  @return
    *      <code>List</code> Uma lista de lotes de uma concessionária de acordo com o status.
    *  
    *  @throws DaoException
    *      Se houverem erros durante a execução estes serão lançados
    *      como uma Exception deste tipo.
    */
	public List listlotes(List status, Concessionaria concessionaria) throws DaoException {
		
		StringBuffer sbSql 		 = new StringBuffer();
		StringBuffer statusWhere = new StringBuffer();
		
		Iterator it = status.iterator();
		StatusLote statusLote = null;
		
		int count = 1;
		while(it.hasNext()) {
			statusLote = (StatusLote) it.next();
			if(count++ == 1)
				statusWhere.append(statusLote.getEntityId().toString());
			else
				statusWhere.append("," + statusLote.getEntityId().toString());
		}
		
		sbSql.append(" SELECT lt.lote_id  		loteId 						 "); //0
		sbSql.append(" 		  ,li.descr_linha 	descricaoLinha 				 "); //1
		sbSql.append("		  ,li.linha_id    	linhaId						 "); //2
		sbSql.append("		  ,tp.tipo_lote_id   tipoId						 "); //3
		sbSql.append("		  ,tp.descricao		descricaoTipoLote			 "); //4
		sbSql.append("		  ,sl.status_lote_id statusId					 "); //5
		sbSql.append("		  ,sl.mascara		statusMascara				 "); //6
		sbSql.append("		  ,sl.status_color   statusCor					 "); //7
		sbSql.append("		  ,sa.status_descricao descricaoStatusAdiantamento"); //8
		sbSql.append("		  ,lt.lote_date		dataLote					 "); //9
		sbSql.append("		  ,lt.fechamento_date dataFechamento			 "); //10
		sbSql.append("		  ,lt.liberacao_date  dataLiberacao				 "); //11
		sbSql.append("		  ,lt.DATA_ENVIO_ADT  dataEnvioAdiant			 "); //12
		sbSql.append("        ,( 											  ");
		sbSql.append("				Case									  ");
		sbSql.append("					When( SELECT COUNT(cp.cupom_id)       ");
		sbSql.append("						   FROM YM_SG_CUPOM cp            ");
		sbSql.append("        				   WHERE cp.lote_id =  lt.lote_id ");
		sbSql.append("        				) > 0 OR 						  ");
		sbSql.append("        				( 								  ");
		sbSql.append("        				   SELECT COUNT(gr.garantia_id)   ");
		sbSql.append("        				   FROM YM_SG_GARANTIA_HEADER gr  ");
		sbSql.append("        				   WHERE gr.lote_id = lt.lote_id  ");
		sbSql.append("        				) > 0							  ");
		sbSql.append("    				then  'true'						  ");
		sbSql.append("    				else   'false'						  ");
		sbSql.append("    			end) hasMovimento						  "); //13
		sbSql.append("		  ,sl.DESCR_REDUZ   descreduz					  "); //14
		sbSql.append("		  ,( 											  ");	  
	    sbSql.append("		  		Case									  ");	  
	    sbSql.append("		  			When(  	tp.tipo_lote_id = 2 ) AND	  ");
	    sbSql.append("		      (	   SELECT COUNT(gr.garantia_id)   	  	  ");
	    sbSql.append("		  				   FROM YM_SG_GARANTIA_HEADER gr  ");
	    sbSql.append("		  				   WHERE gr.lote_id = lt.lote_id  ");
	    sbSql.append("		  		)  > 0 AND						  		  ");
	    sbSql.append("		      ((	   SELECT COUNT(gr.garantia_id)   	  ");
	    sbSql.append("		  				   FROM YM_SG_GARANTIA_HEADER gr  ");
	    sbSql.append("		  				   WHERE gr.lote_id = lt.lote_id  ");
	    sbSql.append("		  		) = 							          ");
	    sbSql.append("		      ( 										  ");
	    sbSql.append("		        SELECT COUNT(gr.garantia_id)   			  ");
	    sbSql.append("		  				   FROM YM_SG_GARANTIA_HEADER gr  ");  
	    sbSql.append("		  				   WHERE gr.lote_id = lt.lote_id  ");
	    sbSql.append("		         AND gr.status_mov_id IN(5,12,18)		  "); 
	    sbSql.append("		        ))										  ");
	    sbSql.append("		  			then  'true'						  ");				  
	    sbSql.append("		  			else   'false'						  ");				  
	    sbSql.append("		  		  end) podeLiberar						  "); //15
		sbSql.append("	FROM YM_SG_LOTE lt									  ");
		sbSql.append("      ,YM_SG_STATUS_LOTE sl ");
		sbSql.append("      ,YM_SG_TIPO_LOTE tp ");
		sbSql.append("      ,YM_SG_STATUS_ADT sa ");
		sbSql.append("      ,YM_SG_LINHA li ");
		sbSql.append("  WHERE lt.linha_id = li.linha_id ");
		sbSql.append("  AND   sl.status_lote_id = lt.status_lote_id ");
		sbSql.append("  AND   tp.tipo_lote_id = lt.tipo_lote_id ");
		sbSql.append("  AND   sa.status_adt_id(+) = lt.status_adt ");
		sbSql.append("  AND   lt.lote_id > 0 ");
		sbSql.append("  AND   lt.START_DATE <= sysdate AND (lt.END_DATE is null or lt.END_DATE > sysdate) ");
		sbSql.append("  AND   lt.concessionaria_id = :concessionaria_id ");
		sbSql.append("  AND   lt.status_lote_id in (" + statusWhere.toString() + ")");
		sbSql.append(" ORDER BY  lt.lote_date desc, lt.lote_id desc");
		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sbSql.toString());

		query.setParameter("concessionaria_id" , concessionaria.getEntityId());
				
		List results    = new ArrayList();	
		List resultsMan = new ArrayList();	
		try {
			Iterator lotes = query.list().iterator();
	
			while ( lotes.hasNext() ) {
				
				LoteCompactVO loteVO = new LoteCompactVO();
				Object[] row = (Object[]) lotes.next();
				loteVO.setLoteId(Integer.valueOf(row[0].toString()));	
				loteVO.setDescricaoLinha((String)row[1]);
				loteVO.setLinhaId(Long.valueOf(row[2].toString()));			
				loteVO.setTipoId(Long.valueOf(row[3].toString()));
				loteVO.setDescricaoTipoLote((String)row[4]);
				loteVO.setStatusId(Long.valueOf(row[5].toString()));
				loteVO.setStatusMascara((String)row[6]);
				loteVO.setStatusCor((String)row[7]);
				loteVO.setDescricaoStatusAdiantamento((String)row[8]);
				loteVO.setDataLote((Date)row[9]);
				loteVO.setDataFechamento((Date)row[10]);
				loteVO.setDataLiberacao((Date)row[11]);
				loteVO.setDataEnvioAdiant((Date)row[12]);
				loteVO.setHasMovimento(Boolean.valueOf(row[13].toString()));	
				loteVO.setDescricaoReduzidaStatus((String)row[14]);
				loteVO.setPodeLiberarGarantia(Boolean.valueOf(row[15].toString()));	
				
				if(StatusLote.STATUS_MANUTENCAO.equals(loteVO.getStatusId())){					
					resultsMan.add(loteVO);
				} else {				
					results.add(loteVO);
				}
			}
				if(resultsMan.size() > 0){
					for(int i=0;i < resultsMan.size();i++){
						results.add(i, resultsMan.get(i));					
					}
				}
				
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		
		return results;
	}
	
	/** Lista de Garantias de um lote para lançamento de NF.
    * 
    *  @param entityId
    *  	<code>Serializable</code> Id do Lote.
    *  
    *  @return
    *      <code>List</code> Uma lista de Garantias.
    *  
    *  @throws DaoException
    *      Se houverem erros durante a execução estes serão lançados
    *      como uma Exception deste tipo.
    */
	public List listGaranatiasToNF(Serializable entityId) throws DaoException {
		
		StringBuffer sbSql 		 = new StringBuffer();
				
		sbSql.append(" SELECT gh.garantia_id  		 garantiaId 	  "); //0
		sbSql.append(" 		  ,gh.modelo 			 chassi	 		  "); //1
		sbSql.append("		  ,gh.STATUS_MOV_ID    	 statusGarantia   "); //2
		sbSql.append("		  ,gh.END_DATE			 dataTermino      "); //3
				
		sbSql.append("		  ,(SELECT SUM(nvl(sg.VL_SERVICO,0))  	"); 
		sbSql.append("			FROM YM_SG_SERVICO_GRUPO sg			"); 
		sbSql.append("          WHERE sg.GARANTIA_ID = gh.GARANTIA_ID");
		sbSql.append(" 			AND sg.START_DATE <= sysdate AND (sg.END_DATE is null or sg.END_DATE > sysdate)) valorTotalServicos "); //4			
		
		sbSql.append(" 		  ,(SELECT SUM(nvl((gl.VL_PECA_GARANTIA * gl.QUANTIDADE),0)) ");
		sbSql.append("  		FROM YM_SG_GARANTIA_LINE gl  ");
		sbSql.append("				 , YM_SG_ITEM it ");
		sbSql.append("			WHERE gl.GARANTIA_ID = gh.GARANTIA_ID ");
		sbSql.append("			AND gl.COBRA_PECA = 'S' ");
		sbSql.append("			AND it.ITEM_ID = gl.ITEM_ID ");
		sbSql.append("			AND gl.START_DATE <= sysdate AND (gl.END_DATE is null or gl.END_DATE > sysdate) ");
		sbSql.append("			AND it.ORGANIZATION_ID = 91 ");
		sbSql.append("			) 		valorPecasCobrar");	//5	
		
		sbSql.append("		  ,gh.VL_SERVICO_TERCEIRO valorServicoterc"); //6
		sbSql.append("		  ,gh.DATA_ABERTURA_OS   dataAberturaOS   "); //7
		sbSql.append("		  ,gh.DATA_FECHAMENTO_OS dataFechamentoOS "); //8
		
		sbSql.append("	FROM YM_SG_GARANTIA_HEADER gh ");	
		sbSql.append("  WHERE gh.lote_id = :loteId ");
		sbSql.append("  AND   gh.START_DATE <= sysdate AND (gh.END_DATE is null or gh.END_DATE > sysdate) ");
		sbSql.append("	AND   gh.STATUS_MOV_ID in (0,12) ");
		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sbSql.toString());

		query.setParameter("loteId" , entityId);
				
		List results = new ArrayList();	
		
		GarantiaVO garantiaVO = null;
		try {
			Iterator it = query.list().iterator();
	
			while ( it.hasNext() ) {
				
				garantiaVO = new GarantiaVO();
				Object[] row = (Object[]) it.next();
				
				garantiaVO.setGarantiaId(Long.valueOf(row[0].toString()));
				garantiaVO.setModelo((String)row[1]);
				garantiaVO.setValorServico(row[4] != null ? (new Double(row[4].toString())).doubleValue():0.0);
				garantiaVO.setValorPeca(row[5] != null ? (new Double(row[5].toString())).doubleValue():0.0);
				garantiaVO.setValorServicoTerceiro(row[6] != null ? (new Double(row[6].toString())).doubleValue():0.0);
				garantiaVO.setDataAberturaOS(row[7] != null ? (Date)row[7] : null);
				garantiaVO.setDataFechamentoOS(row[8] != null ? (Date)row[8] : null);
				results.add(garantiaVO);
	            
			}		
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		
		return results;
	}
	
	/** Lista de Garantias da Concessionaria em Manutenção por mais de 2 dias
    * 
    *  @param concessionariaId
    *  	<code>Serializable</code> Id da Concessionaria.
    *  
    *  @return
    *      <code>boolean</code> true quando existir garantia.
    *  
    *  @throws DaoException
    *      Se houverem erros durante a execução estes serão lançados
    *      como uma Exception deste tipo.
    */
	public boolean listGaranatiasInManut(Serializable concessionariaId) throws DaoException {
		
		StringBuffer sbSql 		 = new StringBuffer();
		boolean hasGar = false;	
		sbSql.append(" SELECT count(gh.garantia_id) as qtde	  "); //0
		sbSql.append(" FROM ym_sg_garantia_header gh, ym_sg_lote lt  ");
		sbSql.append(" WHERE gh.lote_id = lt.lote_id  ");
		sbSql.append(" AND lt.concessionaria_id = :concessionariaId  ");
		sbSql.append(" AND gh.status_mov_id = 5  "); //Em Manutenção
		sbSql.append(" AND gh.START_DATE <= sysdate AND (gh.END_DATE is null or gh.END_DATE > sysdate) ");
		sbSql.append(" AND lt.START_DATE <= sysdate AND (lt.END_DATE is null or lt.END_DATE > sysdate) ");
		sbSql.append(" AND (SYSDATE - gh.last_update_date) > 2 "); //Com mais de 2 dias em Manutenção
		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sbSql.toString());

		query.setParameter("concessionariaId" , concessionariaId);
						
		BigDecimal qtdeGar = null;
		try {
			qtdeGar = (BigDecimal)query.uniqueResult();
			
			hasGar = qtdeGar != null && qtdeGar.intValue() > 0 ? true : false;
	
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		
		return hasGar;
	}
}
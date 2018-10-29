/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......NotaFiscalHibernateDaoImpl.java
 *   .: Criação.....28 de julho, 17:16
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...DAO com tarefas específicas para a entidade NotaFiscal.
 */
package br.com.yamaha.sistemagarantia.dao.hibernate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import br.com.resource.infra.dao.hibernate.HibernateDaoImpl;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.dao.NotaFiscalDao;
import br.com.yamaha.sistemagarantia.model.Concessionaria;
import br.com.yamaha.sistemagarantia.model.Cupom;
import br.com.yamaha.sistemagarantia.model.Faturamento;
import br.com.yamaha.sistemagarantia.model.GarantiaHeader;
import br.com.yamaha.sistemagarantia.model.GarantiaLine;
import br.com.yamaha.sistemagarantia.model.Item;
import br.com.yamaha.sistemagarantia.model.NotaFiscal;
import br.com.yamaha.sistemagarantia.model.to.NotaFiscalLancamentoTO;
import br.com.yamaha.sistemagarantia.view.CupomNFVO;
import br.com.yamaha.sistemagarantia.view.NotaFiscalVO;

/** Objeto DAO com tarefas específicas para a entidade <b>NotaFiscal</b>. 
 * 
 *  @author Edson Luiz Sumensari
 */
public class NotaFiscalHibernateDaoImpl extends HibernateDaoImpl implements NotaFiscalDao {

	/**
	 * Verifica se um cupom já possui nota fiscal lançada
	 * 
	 * @param cupomId Long
	 * @return boolean true quando existir nota fiscal para o cupom
	 * @since  30/07/2013
	 * @author ist_edson
	 * @throws DaoException
	 */
	public boolean hasNotaByCupom(Long cupomId)throws DaoException {
		
		String sql = " SELECT cup.cupom_id " +
   				" 	FROM    ym_sg_cupom_nota cup " +
   				"	WHERE cup.cupom_id = :cupomId ";
   
	   Session session = null;
	   
	   try {
		   
			session = super.getSession();
			SQLQuery query  = session.createSQLQuery(sql);
			
			query.setParameter("cupomId",cupomId);
			
			List itens = query.list();
			
			if(itens != null && !itens.isEmpty() && itens.size() > 0) {
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
	
	/** Retorna uma lista de lotes disponíveis para lanaçamento de notas.
    *
    *  
    *  @return
    *      Retorna uma lista de lotes disponíveis para lanaçamento de notas.
    *  
    *  @throws DaoException
    *      Se houverem erros durante a execução estes serão lançados
    *      como uma Exception deste tipo.
    */
   public List listLotesDisp( Concessionaria concessionaria) throws DaoException {
        
	   String sql = " SELECT cup.cupom_id entityId " +
	   				" 		, get_vl_revisao_lote(lot.lote_id) valorMaoObra " +
	   				"		, 0 valorPeca " +
	   				"		, 0 valorRemessa " +
	   				"		, cup.lote_id loteId " +
	   				"		, lot.status_lote_id statusLoteId " +
	   				"		, tpl.tipo_lote_id tipoLoteId "	+	
	   				"		, tpl.descricao tipoLote " +
	   				"		, stl.descr_reduz descricaoStatus " +
	   				" 	FROM    ym_sg_cupom cup " +
	   				"	  	, ym_sg_lote lot " +
	   				"		, ym_sg_tipo_lote tpl " +
	   				"		, ym_sg_status_lote stl " +
	   				"	WHERE cup.lote_id = lot.lote_id " +
	   				"	AND   lot.tipo_lote_id = tpl.tipo_lote_id " +
	   				"	AND   cup.status_mov_id in (0, 12) " +
	   				"   AND   stl.status_lote_id = lot.status_lote_id " +
	   				"	AND   lot.status_lote_id in (1, 8, 9)" +
	   				"	AND	  lot.concessionaria_id = :concessionariaId " +
	   				"   AND cup.start_date <= :data " +
			        "   AND ( (cup.end_date is null) OR (cup.end_date >= :data) )" +
			        "	AND lot.start_date <= :data " +
			        "   AND ( (lot.end_date is null) OR (lot.end_date >= :data) )" +
			        "	AND tpl.start_date <= :data " +
			        "   AND ( (tpl.end_date is null) OR (tpl.end_date >= :data) )" +
	   				" UNION " +
	   				"	SELECT grh.garantia_id entityId " +
	   				"		, get_vl_mobra_lote(grh.lote_id) valorMaoObra " +
	   				"		, get_vl_peca_lote (grh.lote_id) valorPeca " +
	   				"		, get_vl_remessa_lote(grh.lote_id) valorRemessa " +
	   				"		, grh.lote_id loteId " +
	   				"		, lot.status_lote_id statusLoteId " +
	   				"		, tpl.tipo_lote_id tipoLoteId "	+	
	   				"		, tpl.descricao tipoLote " +
	   				"		, stl.descr_reduz descricaoStatus" +
	   				"	FROM  ym_sg_garantia_header grh " +
	   				"		, ym_sg_lote lot " +
	   				"		, ym_sg_tipo_lote tpl " +
	   				"		, ym_sg_status_lote stl " +
	   				"	WHERE grh.lote_id = lot.lote_id " +
	   				"	AND   lot.tipo_lote_id = tpl.tipo_lote_id " +
	   				"   AND   stl.status_lote_id = lot.status_lote_id " +
	   				"	AND   grh.status_mov_id = 2 " +
	   				"	AND   lot.status_lote_id in (5,12) " +
	   				" 	AND	  lot.concessionaria_id = :concessionariaId " +
	   				"   AND grh.start_date <= :data " +
			        "   AND ( (grh.end_date is null) OR (grh.end_date >= :data) )" +
			        "	AND lot.start_date <= :data " +
			        "   AND ( (lot.end_date is null) OR (lot.end_date >= :data) )" +
			        "	AND tpl.start_date <= :data " +
			        "   AND ( (tpl.end_date is null) OR (tpl.end_date >= :data) )" +
	   				"	ORDER BY 1 ";
	   
	   Session session = null;
	   
	   try {
		   
			session = super.getSession();
			SQLQuery query  = session.createSQLQuery(sql);
			
			query.setParameter("concessionariaId", concessionaria.getEntityId());
			query.setParameter("data"			 , new Date());
			
			
			NotaFiscalVO notaFiscalVO = null;
			List results = new ArrayList();
	
			Iterator itens = query.list().iterator();
	
			while ( itens.hasNext() ) {
	
				notaFiscalVO = new NotaFiscalVO();
				Object[] row = (Object[]) itens.next();
				
				notaFiscalVO.setEntityId(Long.valueOf(row[0].toString()));
				
				double valorMO = row[1] != null ? (Double.valueOf(row[1].toString())).doubleValue() : 0;
				notaFiscalVO.setValorMaoObra(valorMO);
				
				double valorPeca = row[2] != null ? (Double.valueOf(row[2].toString())).doubleValue() : 0 ;
				notaFiscalVO.setValorPeca(valorPeca);
				
				double valorRemessa = row[3] != null ? (Double.valueOf(row[3].toString())).doubleValue() : 0 ;
				notaFiscalVO.setValorRemessa(valorRemessa);
				
				notaFiscalVO.setLoteId(Long.valueOf(row[4].toString()));
				notaFiscalVO.setStatusLoteId(Long.valueOf(row[5].toString()));
				notaFiscalVO.setTipoLoteId(Long.valueOf(row[6].toString()));
				notaFiscalVO.setTipoLote(row[7].toString());
				notaFiscalVO.setDescricaoStatus(row[8].toString());
	
				results.add(notaFiscalVO);
			}
	
			return results;	 
			
	   } catch( Exception exp ) { 
			
			throw new DaoException(exp);
			
		} finally {
			session.close();
		}
        
   }
   
   /** Retorna uma lista dos cupons de um lote para lançamento de Nota Fiscal.
    * 
    * @param cupons  
    * 	<code>String</code> - Lista de número de cupons selecionados
    *  
    *  @return
    *      NotaFiscalLancamentoTO.
    *  
    *  @throws DaoException
    *      Se houverem erros durante a execução estes serão lançados
    *      como uma Exception deste tipo.
    */
   public NotaFiscalLancamentoTO listCuponsForNota( String cupons ) throws DaoException {
	   
	   String sql 		= 	" SELECT   cup.cupom_id 									" +
			   				"        , cup.cupom_code									" +
			   				"        , cup.modelo										" +
			   				"        , cup.vl_revisao									" +
			   				"        , cup.vl_bonificacao								" +
			   				"        , emp.org_code										" +
			   				"        , rev.descricao									" +
			   				" FROM  ym_sg_cupom 	      cup							" +
			   				"	  , ym_sg_lote            lot 							" +
			   				"	  , ym_sg_concessionaria  con  							" +
			   				"	  , ym_sg_faturamento     fat							" +
			   				"	  , ym_sg_empresa         emp							" +
			   				"	  , ym_sg_revisao         rev							" +
			   				" WHERE cup.lote_id 		  = lot.lote_id					" +
			   				" AND   cup.revisao_id        = rev.revisao_id				" +
			   				" AND   cup.modelo  		  = fat.chassi					" +
			   				" AND   fat.created_date = (SELECT MAX(CREATED_DATE) FROM YM_SG_FATURAMENTO WHERE chassi = fat.chassi) " + //Edson 02/07/2013
			   				" AND   lot.concessionaria_id = con.concessionaria_id		" +
			   				" AND   fat.organization_id   = emp.organization_id			" +
			   				" AND   cup.cupom_id 		  in (" + cupons + " )			" +
			   				" AND   cup.status_mov_id     in (0, 12)					" +
			   				" AND   nvl(trunc(cup.end_date), sysdate + 1) >= SYSDATE 	" +
			   				" AND   nvl(trunc(lot.end_date), sysdate + 1) >= SYSDATE 	" +
			   				" AND   nvl(trunc(fat.end_date), sysdate + 1) >= SYSDATE 	" +
			   				" AND   nvl(trunc(rev.end_date), sysdate + 1) >= SYSDATE 	" +
			   				" ORDER BY emp.org_code										";	
	   
	   String sqlSumar = 	" SELECT   round(sum(cup.vl_revisao + nvl(cup.vl_bonificacao,0) ),2)	" +
	   						"		   ,EMP.org_code											 	" +
	   						" FROM  	ym_sg_cupom 	        cup									" +
	   						"	    	, ym_sg_lote            lot									" +
	   						"       	, ym_sg_concessionaria  con									" +
	   						"       	, ym_sg_faturamento     fat									" +
	   						"  	    	, ym_sg_empresa         emp									" +
	   						" WHERE 	cup.lote_id 		    = lot.lote_id						" +
	   						" AND   	cup.modelo  		    = fat.chassi						" +
	   						" AND       fat.created_date = (SELECT MAX(CREATED_DATE) FROM YM_SG_FATURAMENTO WHERE chassi = fat.chassi) " + //Edson 02/07/2013
	   						" AND   	lot.concessionaria_id   = con.concessionaria_id				" +
	   						" AND   	fat.organization_id     = emp.organization_id				" +
	   						" AND   	cup.cupom_id 		    in (" + cupons + " )				" +
	   						" AND   	cup.status_mov_id       in (0, 12)							" +
	   						" AND   	nvl(trunc(cup.end_date), sysdate + 1) >= SYSDATE			" +
	   						" AND   	nvl(trunc(lot.end_date), sysdate + 1) >= SYSDATE 			" +
	   						" AND   	nvl(trunc(fat.end_date), sysdate + 1) >= SYSDATE			" +
	   						" GROUP BY emp.org_code													";	
	   
	   Session session = null;
	   
	   try {
		   
			session = super.getSession();			
			NotaFiscalLancamentoTO notaFiscalLancamentoTO = null;
			
			SQLQuery query  = session.createSQLQuery(sql);
			
			CupomNFVO cupomNFVO = null;
			
			List itens = query.list();
			
			if ( !itens.isEmpty() ) {
				
				notaFiscalLancamentoTO = new NotaFiscalLancamentoTO();
				
				Iterator it = itens.iterator();
				
				while ( it.hasNext() ) {
				
					cupomNFVO = new CupomNFVO();
					Object[] row = (Object[]) it.next();
					
					Long cupomId = row[0] != null ? Long.valueOf(row[0].toString()) : null;
					cupomNFVO.setCupomId(cupomId);
					
					Long cupomCode = row[1] != null ? Long.valueOf(row[1].toString()) : null;
					cupomNFVO.setCupomCode(cupomCode);
					
					cupomNFVO.setChassi(row[2].toString());
					
					double valorRevisao = row[3] != null ? (Double.valueOf(row[3].toString())).doubleValue() : 0 ;
					cupomNFVO.setValorRevisao(valorRevisao);
					
					double valorBonificacao = row[4] != null ? (Double.valueOf(row[4].toString())).doubleValue() : 0 ;
					cupomNFVO.setValorBonificacao(valorBonificacao);
					
					cupomNFVO.setCodigoEmpresa(row[5].toString());
					
					cupomNFVO.setDescricaoRevisao(row[6].toString());
					
					notaFiscalLancamentoTO.addListItens(cupomNFVO);
					
				}
				
				query  = session.createSQLQuery(sqlSumar);
				
				itens = query.list();
				
				it = itens.iterator();
				
				while ( it.hasNext() ) {
					
					NotaFiscalVO notaFiscalVO = new NotaFiscalVO();
					Object[] row = (Object[]) it.next();
					
					double valorMO = row[0] != null ? (Double.valueOf(row[0].toString())).doubleValue() : 0;
					notaFiscalVO.setValorMaoObra(valorMO);
					
					notaFiscalVO.setDestinatario(row[1].toString());
					
					notaFiscalLancamentoTO.addListSumario(notaFiscalVO);
				}
				
			}
			
			return notaFiscalLancamentoTO;
			
	   } catch( Exception exp ) { 
			
			throw new DaoException(exp);
			
	   } finally {
			session.close();
	   }
   }
   
   /** Retorna uma lista das SG's de um lote para lançamento de Nota Fiscal.
    * 
    * @param garantias 
    * 	<code>String</code> - Lista de número das SG's selecionadas
    *  
    *  @return
    *      NotaFiscalLancamentoTO.
    *  
    *  @throws DaoException
    *      Se houverem erros durante a execução estes serão lançados
    *      como uma Exception deste tipo.
    */
   public NotaFiscalLancamentoTO listGarantiasForNota( String garantias ) throws DaoException {
	   
	   //System.out.println("NotaFiscalLancamentoTO --> Garantias:" + garantias);
	   
	   String sqlSumar = 	" SELECT SUM(valorMaoObra) 									" +
	   						"		, SUM(valorPeca)									" +
	   						"       , SUM(valorRemessa)									" +
	   						"       , SUM(valorMaoObraTerc)								" +
	   						"       , destinatario										" +
	   						" FROM (													" +
	   						"		 SELECT sum(vl_servico) valorMaoObra				" +
	   						"				, 0  valorPeca								" +
	   						"				, 0  valorRemessa							" +
	   						"				, 0	 valorMaoObraTerc						" +
	   						"				, emp.org_code destinatario					" +
	   						"	     FROM    ym_sg_garantia_header grh					" +
	   						"		 	    ,ym_sg_lote           lot					" +
	   						"				,ym_sg_concessionaria con					" +
	   						"				,ym_sg_faturamento    fat					" +
	   						"				,ym_sg_servico_grupo  sgr					" +
	   						"				,ym_sg_empresa        emp					" +
	   						"		 WHERE grh.lote_id 		 	  = lot.lote_id			" +
	   						"		 AND   grh.modelo  		 	  = fat.chassi			" +
	   						"		 AND   lot.concessionaria_id  = con.concessionaria_id" +
	   						"		 AND   grh.garantia_id 	 	  = sgr.garantia_id		" +
	   						"		 AND   fat.organization_id 	  = emp.organization_id	" +
	   						"		 AND   grh.garantia_id 	 	  in (" + garantias + ")" +
	   						"		 AND   grh.status_mov_id      in (0, 12)			" +
	   						"        AND   nvl(trunc(sgr.end_date), sysdate + 1) >= SYSDATE" +
	   						"        AND   nvl(trunc(grh.end_date), sysdate + 1) >= SYSDATE" +
	   						"        AND   nvl(trunc(lot.end_date), sysdate + 1) >= SYSDATE" +
	   						"        AND   nvl(trunc(fat.end_date), sysdate + 1) >= SYSDATE" +
	   						"        AND   nvl(trunc(con.end_date), sysdate + 1) >= SYSDATE" +
	   						"        AND   nvl(trunc(emp.end_date), sysdate + 1) >= SYSDATE" +
	   						"		 GROUP BY emp.org_code								" +
	   						"		 UNION ALL											" +
	   						"		 SELECT 0											" +
	   						"	           , round(sum(grl.vl_peca_garantia * grl.quantidade),2) valorPeca" +
	   						"              , 0  valorRemessa							" +
	   						"              , 0	 valorMaoObraTerc						" +
	   						"	   		   , (											" +
	   						"		           	Case									" +
	   						"						When  grh.quilometragem  = 0 and lot.linha_id = 1 and mdl.velocimetro = 'S' then emp.org_code" +
	   						"					    When  grh.dias_uso       = 0 and lot.linha_id = 1 and mdl.velocimetro = 'N' then emp.org_code" +
	   						"						When  nvl(grh.horas_uso,0) = 0 and lot.linha_id = 2 then emp.org_code" +
	   						"						When  std.faturamento_ym = 'S' then  emp.org_code" +
	   						"  	 				Else									" +
	   						"						'Cliente'							" +
	   						"					End										" +
	   						"				)											" +
	   						"		 FROM   ym_sg_garantia_header   grh					" +
	   						"				, ym_sg_lote            lot					" +
	   						"				, ym_sg_concessionaria  con					" +
	   						"				, ym_sg_estado          std					" +
	   						"				, ym_sg_tipo_lote       tpl					" +
	   						"  				, ym_sg_faturamento     fat					" +
	   						"				, ym_sg_garantia_line   grl					" +
	   						"				, ym_sg_empresa         emp					" +
	   						"				, ym_sg_modelo          mdl					" +
	   						"		 WHERE grh.lote_id 		     = lot.lote_id			" +
	   						"		 AND grh.modelo  		     = fat.chassi			" +
	   						"		 AND fat.modelo  	         = mdl.modelo_id		" +
	   						"		 AND lot.concessionaria_id   = con.concessionaria_id" +
	   						"		 AND con.estado_id           = std.estado_id		" +
	   						"		 AND grh.garantia_id 	     = grl.garantia_id		" +
	   						"		 AND grl.cobra_peca	         = 'S'					" +
	   						"		 AND fat.organization_id     = emp.organization_id	" +
	   						"		 AND tpl.tipo_lote_id 	     = lot.tipo_lote_id		" +
	   						"		 AND grh.garantia_id 	     in (" + garantias + ") " +
	   						"        AND grh.status_mov_id       in (0, 12)				" +
	   						"		 AND nvl(trunc(std.end_date), sysdate + 1) >= SYSDATE" +
	   						"        AND nvl(trunc(grh.end_date), sysdate + 1) >= SYSDATE" +
	   						"        AND nvl(trunc(grl.end_date), sysdate + 1) >= SYSDATE" +
	   						"        AND nvl(trunc(lot.end_date), sysdate + 1) >= SYSDATE" +
	   						"        AND nvl(trunc(fat.end_date), sysdate + 1) >= SYSDATE" +
	   						"        AND nvl(trunc(con.end_date), sysdate + 1) >= SYSDATE" +
	   						"        AND nvl(trunc(emp.end_date), sysdate + 1) >= SYSDATE" +
	   						//"        AND nvl(trunc(mdl.end_date), sysdate + 1) >= SYSDATE" +
	   						"        AND nvl(trunc(tpl.end_date), sysdate + 1) >= SYSDATE" +
	   						"	 	 GROUP BY (											" +
	   						"           	    Case									" +
	   						"						When  grh.quilometragem  = 0 and lot.linha_id = 1 and mdl.velocimetro = 'S' then emp.org_code" +
	   						"						When  grh.dias_uso       = 0 and lot.linha_id = 1 and mdl.velocimetro = 'N' then emp.org_code" +
	   						"						When  nvl(grh.horas_uso,0)   = 0 and lot.linha_id = 2 then emp.org_code" +
	   						"						When  std.faturamento_ym = 'S' then  emp.org_code" +
	   						"	 				Else									" +
	   						"						'Cliente'							" +
	   						"					End										" +
	   						"				)											" +
	   						"		 UNION	ALL											" +
	   						"		 SELECT  0 valorMaoObra								" +
	   						"			   , 0 valorPeca								" +
	   						"			   , sum(round((grl.quantidade * grl.vl_preco_sugerido * .1),2)) valorRemessa" +
	   						"			   , 0 valorMaoObraTerc							" +
	   						"   		   , emp.org_code destinatario					" +
	   						"		 FROM  ym_sg_garantia_header	grh					" +
	   						"			   , ym_sg_lote             lot					" +
	   						"			   , ym_sg_concessionaria   con					" +
	   						"			   , ym_sg_faturamento      fat					" +
	   						"			   , ym_sg_garantia_line    grl					" +
	   						"			   , ym_sg_empresa          emp					" +
	   						"		 WHERE grh.lote_id 		        = lot.lote_id		" +
	   						"	     AND   grh.modelo  		        = fat.chassi		" +
	   						"		 AND   lot.concessionaria_id    = con.concessionaria_id" +
	   						"		 AND   grh.garantia_id 	        = grl.garantia_id	" +
	   						"		 AND   fat.organization_id      = emp.organization_id" +
	   						"		 AND   grh.garantia_id 	        in (" + garantias + ")" +
	   						"		 AND   grh.status_mov_id        in (0, 12)			" +
	   						"		 AND   grl.envia_peca           = 'S'				" +
	   						"        AND nvl(trunc(grh.end_date), sysdate + 1) >= SYSDATE" +
	   						"        AND nvl(trunc(grl.end_date), sysdate + 1) >= SYSDATE" +
	   						"        AND nvl(trunc(lot.end_date), sysdate + 1) >= SYSDATE" +
	   						"        AND nvl(trunc(fat.end_date), sysdate + 1) >= SYSDATE" +
	   						"        AND nvl(trunc(con.end_date), sysdate + 1) >= SYSDATE" +
	   						"        AND nvl(trunc(emp.end_date), sysdate + 1) >= SYSDATE" +
	   						"		 GROUP BY emp.org_code								" +
	   						"		 UNION	ALL											" +
	   						"		 SELECT 0   valorMaoObra							" +
	   						"				, 0 valorPeca								" +
	   						"				, 0 valorRemessa							" +
	   						"				, round(sum(grh.vl_servico_terceiro),2) valorMaoObraTerc" +
	   						"				, emp.org_code                           destinatario" +
	   						" 		 FROM 	ym_sg_garantia_header 	    grh				" +
	   						"			   	, ym_sg_lote    			lot				" +
	   						"  			    , ym_sg_concessionaria   	con				" +
	   						" 			    , ym_sg_faturamento 	 	fat				" +
	   						"			    , ym_sg_empresa      		emp				" +
	   						"		 WHERE  grh.lote_id 		        = lot.lote_id	" +
	   						"		 AND grh.modelo     		        = fat.chassi	" +
	   						"		 AND lot.concessionaria_id 			= con.concessionaria_id" +
	   						"		 AND fat.organization_id   			= emp.organization_id" +
	   						"		 AND grh.garantia_id 	     		in (" + garantias + ")" +
	   						"        AND grh.status_mov_id       		in (0, 12)		" +
	   						"		 AND nvl(trunc(grh.end_date), sysdate + 1) >= SYSDATE" +
	   						"        AND nvl(trunc(lot.end_date), sysdate + 1) >= SYSDATE" +
	   						"        AND nvl(trunc(fat.end_date), sysdate + 1) >= SYSDATE" +
	   						"        AND nvl(trunc(con.end_date), sysdate + 1) >= SYSDATE" +
	   						"        AND nvl(trunc(emp.end_date), sysdate + 1) >= SYSDATE" +
	   						"        GROUP BY emp.org_code, grh.garantia_id				" +
	   						" )															" +
	   						" GROUP BY DESTINATARIO										";	
	   
	   Session session = super.getSession();	
	   
	   NotaFiscalLancamentoTO notaFiscalLancamentoTO = null;
	   
	   // Montamos uma coleção dos ids para recuperar as Garantias
	   String gars[] = garantias.split(",");
	   List   entityIds = new ArrayList();
	   for ( int i = 0 ; i < gars.length ; i ++ )
		   entityIds.add(new Integer(gars[i].trim()));
	   
	   DetachedCriteria criteria = super.getDetachedCriteria(GarantiaHeader.class);
       criteria.add( Expression.in("entityId", entityIds) );
       criteria.addOrder(Order.asc("entityId"));

       List listItens = super.getHibernateTemplate().findByCriteria(criteria);
       
       // Rotina para restaurar as peças de cada Garantia
       List listGarantias = new ArrayList();
       Iterator itSg = listItens.iterator();
       while( itSg.hasNext() ) {
    	   
    	   GarantiaHeader garantia = (GarantiaHeader)itSg.next();
    	   garantia.setListGarantiaLine(this.listPecas(garantia));
    	   //System.out.println("Itens na Garantia:" + garantia.getEntityId() + " - Itens:" + garantia.getListGarantiaLine().size());
    	   listGarantias.add(garantia);
       }
       //System.out.println("Garantias:" + listGarantias.size());
	   try {
	       
	       if ( !listGarantias.isEmpty())  {
	       
	    	   notaFiscalLancamentoTO = new NotaFiscalLancamentoTO();
	    	   notaFiscalLancamentoTO.setListItens(listGarantias);
			
	    	   SQLQuery query  = session.createSQLQuery(sqlSumar);
				
	    	   List sumario = query.list();
				
	    	   Iterator it = sumario.iterator();
				
				while ( it.hasNext() ) {
					
					NotaFiscalVO notaFiscalVO = new NotaFiscalVO();
					Object[] row = (Object[]) it.next();
					
					double valorMO = row[0] != null ? (Double.valueOf(row[0].toString())).doubleValue() : 0;
					notaFiscalVO.setValorMaoObra(valorMO);
					
					double valorPeca = row[1] != null ? (Double.valueOf(row[1].toString())).doubleValue() : 0 ;
					notaFiscalVO.setValorPeca(valorPeca);
					
					double valorRemessa = row[2] != null ? (Double.valueOf(row[2].toString())).doubleValue() : 0 ;
					notaFiscalVO.setValorRemessa(valorRemessa);
					
					double valorMaoObraTerc = row[3] != null ? (Double.valueOf(row[3].toString())).doubleValue() : 0 ;
					notaFiscalVO.setValorMaoObraTerc(valorMaoObraTerc);
					
					notaFiscalVO.setDestinatario(row[4].toString());
					
					notaFiscalLancamentoTO.addListSumario(notaFiscalVO);
				}
				
			}
			
			return notaFiscalLancamentoTO;
			
	   } catch( Exception exp ) { 
			
			throw new DaoException(exp);
			
	   } finally {
			session.close();
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
		
		String sqlRes =" SELECT distinct yl.garantia_id							" +
					   "		, yl.line_id									" +
					   "		, yl.item_id									" +
					   "		, yl.quantidade									" +
					   "		, yl.envia_peca									" +
					   "		, yl.cobra_peca									" +
					   "		, yl.peca_faltante								" +
					   "		, yl.VL_CUSTO_PADRAO_SP 						" +
					   "		, yl.VL_PECA_GARANTIA							" +
					   "		, yl.VL_PRECO_SUGERIDO 							" +
					   "		, yl.VL_PRECO_FOB								" +
					   "		, yl.FATOR_GARANTIA_ID 							" +
					   "		, yi.item_code									" +
					   "		, yi.descricao									" +
					   " FROM ym_sg_garantia_line    yl,         				" +
					   "      ym_sg_item             yi,         				" +
					   "      ym_sg_garantia_header  yh		     				" + 
					   " WHERE yl.garantia_id = yh.garantia_id   				" +	
					   " AND   yl.item_id = yi.item_id                  		" +
					   " AND   yi.organization_id = 91                  		" +
					   " AND   yl.garantia_id = :garantiaId            			" +
					   " AND   yl.start_date <= sysdate        					" +
					   " AND nvl(trunc(yl.end_date), sysdate + 1) >= SYSDATE	" +
					   " ORDER BY yl.line_id 									";

		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sqlRes);
		
		query.setParameter("garantiaId"		 , garantia.getEntityId());
		
		List results = new ArrayList();
		Iterator rsIt = null;
		
		try {
			
			rsIt =  query.list().iterator();
			
		} catch( Exception exp ) { 
		
			throw new DaoException(exp);
			
		} finally {
			session.close();
		}
		
		while ( rsIt.hasNext() ) {
			
			Object[] row = (Object[]) rsIt.next();
			GarantiaLine garantiaLine = new GarantiaLine();	
			//System.out.println("Garantia:" + garantia.getEntityId() + " - LineID:" + ((BigDecimal)row[1]));
			
			//GarantiaLineId compositeEntityId  = new GarantiaLineId(garantia.getEntityId(), (Long)row[1]);
			garantiaLine.setCompositeEntityId(garantia.getEntityId(), new Long(((BigDecimal)row[1]).longValue()));
			
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
			
			results.add(garantiaLine);
			
		}
		
		return results;
	}
   
   
   /** Retorna uma lista dos itens de um lote.
    * 
    * @param cupons  
    * 	<code>String</code> - Lista de número de cupons selecionados
    * 
    * @param garantias  
    * 	<code>String</code> - Lista de número de garantias selecionados	
    *  
    *  @return
    *      Retorna uma lista de objetos NotaFiscalVO.
    *  
    *  @throws DaoException
    *      Se houverem erros durante a execução estes serão lançados
    *      como uma Exception deste tipo.
    */
   public List listItensLote( String cupons, String garantias ) throws DaoException {
	   
	   String sql = " select  SUM(valorMaoObra) 													" +
	   				"	    , SUM(valorPeca) 														" +
		   			"   	, SUM(valorRemessa) 													" +
		   			"	   	, SUM(valorMaoObraTerc) 												" +
		   			"		, destinatario from (													" +
		   			" SELECT																		" +
	   				"	   round(sum(vl_revisao + nvl(vl_bonificacao,0) ),2)	valorMaoObra 		" +
	   				"	   , 0 										valorPeca 						" +
	   				"	   , 0                                      valorRemessa 					" +
	   				"	   , 0                                      valorMaoObraTerc 				" +
	   				"	   , emp.org_code                           destinatario 					" +
	   				//"      , round(sum(vl_revisao + nvl(vl_bonificacao,0)       					" +
	   				//"		 * fgr.aliq_icms_yamaha / 100 ),2) 		    icmsYamaha					" +
	   				//"	   , round(sum(vl_revisao + nvl(vl_bonificacao,0)	    					" +
	   				//"		 * fgr.aliq_icms_cliente / 100 ),2)        icmsCliente					" +
	   				"	   FROM  ym_sg_cupom 	       cup  										" +
	   				"	   ,     ym_sg_lote            lot 											" +
	   				"	   ,     ym_sg_concessionaria  con  										" +
	   				//"      ,     ym_sg_fator_garantia  fgr											" +
	   				"	   ,     ym_sg_faturamento     fat											" +
	   				"	   ,     ym_sg_empresa         emp											" +
	   				"	   WHERE cup.lote_id 		  = lot.lote_id 								" +
	   				"	   AND   cup.modelo  		  = fat.chassi									" +
	   				"      AND  lot.concessionaria_id = con.concessionaria_id						" +
	   				//"      AND   fgr.estado 		  = con.estado									" +
	   				"	   AND   fat.organization_id  = emp.organization_id							" +
	   				"	   AND   cup.cupom_id 		  in (" + cupons + ")   						" +
	   				"	   AND   cup.end_date  is null                        						" +
	   				"	   AND   lot.end_date  is null												" +
	   				//"      AND   fgr.end_date  is null												" +
	   				"	   AND  fat.end_date is null												" + 
	   				"	   AND   cup.status_mov_id  in (0, 12)										" +
	   				"	   AND   lot.status_lote_id in (1, 5, 8, 9)									" +
	   				"	   group by emp.org_code 													" +
	   				" UNION ALL																		" +
	   				" SELECT 																		" +
	   				"	     sum(vl_servico) 								valorMaoObra			" +
	   				"	   , 0 	             								valorPeca 				" +
	   				"	   , 0               								valorRemessa 			" +
	   				"	   , 0	             								valorMaoObraTerc 		" +
	   				"	   , emp.org_code                           		destinatario    		" +
	   				//"	   , round(sum(vl_servico * fgr.aliq_icms_yamaha / 100 ),2) 	icmsYamaha  " +
	   			   // "      , round(sum(vl_servico * fgr.aliq_icms_cliente / 100 ),2)	icmsCliente	" +
	   				"	   FROM ym_sg_garantia_header grh 											" +
	   				"	   ,     ym_sg_lote           lot											" +
	   				"	   ,     ym_sg_concessionaria con											" +
	   				//"	   ,     ym_sg_fator_garantia fgr											" +
	   				"	   ,     ym_sg_faturamento    fat											" +
	   				"	   ,     ym_sg_servico_grupo  sgr											" +
	   				"	   ,     ym_sg_empresa        emp											" +
	   				"	   WHERE grh.lote_id 		 	= lot.lote_id								" +
	   				"	   AND   grh.modelo  		 	= fat.chassi								" +
	   				"	   AND   lot.concessionaria_id 	= con.concessionaria_id						" +
	   				//"	   AND   fgr.estado 			= con.estado								" +
	   				"	   AND   grh.garantia_id 	 	= sgr.garantia_id							" +
	   				"	   AND   fat.organization_id 	= emp.organization_id						" +
	   				"	   AND   grh.garantia_id 	 	in (" + garantias + ")                      " +
	   				"	   AND   grh.status_mov_id 	in (0, 12)										" +
	   				"	   AND   lot.status_lote_id in (1, 8, 9)									" +
	   				"	   AND   sgr.end_date is null 												" +
	   				"	   AND   grh.end_date is null												" +
	   				"	   AND   lot.end_date is null												" +
	   				"      AND   sgr.end_date is null												" +
	   				//"      AND   fgr.end_date is null												" +
	   				"	   AND  fat.end_date is null												" + 
	   				"	   group by emp.org_code				 									" +
	   				" UNION ALL																		" +
	   				" SELECT 																		" +
	   				"	     0 											valorMaoObra 				" +
	   				"	   , round(sum(grl.vl_peca_garantia * grl.quantidade),2) valorPeca 			" +
	   				"	   , 0                              	        valorRemessa 				" +
	   				"	   , 0	                                        valorMaoObraTerc 			" +
	   				"	   , (																		" +
	   				"           Case 																" +															
	   				"				When  grh.quilometragem  = 0 and lot.linha_id = 1 and mdl.velocimetro = 'S' then emp.org_code   " +
	   				"				When  grh.dias_uso       = 0 and lot.linha_id = 1 and mdl.velocimetro = 'N' then emp.org_code 	" +   
	   				"				When  grh.horas_uso      = 0 and lot.linha_id = 2 then emp.org_code  							" +
	   				"				When  std.faturamento_ym = 'S' then  emp.org_code 												" +  
	   				" 			Else 																								" +																
	   				"				'Cliente' 																						" + 															
	   				"			End 																								" +
	   				"		) 	 												destinatario    	" +
	   				//"	   , round(sum(grl.vl_peca_garantia * grl.quantidade						" +
	   				//"		 * fgr.aliq_icms_yamaha / 100 ),2)				icmsYamaha 				" +
	   				//"	   , round(sum(grl.vl_peca_garantia * grl.quantidade  						" +
	   				//"		 * fgr.aliq_icms_cliente / 100 ),2)			icmsCliente 				" +
	   				"	   FROM ym_sg_garantia_header			grh 								" +
	   				"	   ,     ym_sg_lote                     lot 								" +
	   				"	   ,     ym_sg_concessionaria  			con									" +
	   				"	   ,     ym_sg_estado                  std									" +
	   				//"	   ,     ym_sg_fator_garantia			fgr									" +
	   				"	   ,     ym_sg_tipo_lote                tpl									" +
	   				"	   ,     ym_sg_faturamento              fat									" +
	   				"	   ,     ym_sg_garantia_line            grl									" +
	   				"	   ,     ym_sg_empresa                  emp									" +
	   				"	   ,     ym_sg_modelo                   mdl									" +
	   				"	   WHERE grh.lote_id 		  = lot.lote_id									" +
	   				"	   AND   grh.modelo  		  = fat.chassi									" +
	   				"      AND   fat.modelo  	      = mdl.modelo_id								" +
	   				"	   AND  lot.concessionaria_id = con.concessionaria_id						" +
	   				"	   AND   con.estado_id        = std.estado_id   							" +
	   				//"	   AND   fgr.estado 		  = con.estado									" +
	   				"	   AND   grh.garantia_id 	  = grl.garantia_id								" +
	   				"	   AND   grl.cobra_peca	      = 'S'											" +
	   				"	   AND   fat.organization_id  = emp.organization_id							" +
	   				"	   AND   tpl.tipo_lote_id 	  = lot.tipo_lote_id							" +
	   				"	   AND   grh.garantia_id 	  in (" + garantias + ") 						" +
	   				"      AND   grh.end_date  is null						                       	" +
	   				//"      AND   fgr.end_date  is null												" +
	   				"      AND   grl.end_date  is null												" +
	   				"	   AND  fat.end_date is null												" + 
	   				"	   AND   grh.status_mov_id   in (0, 12)										" +
	   				"	   AND   lot.status_lote_id  in (1, 8, 9)									" +
	   				"	   group by 																" +
	   				"			(																	" +
	   				"           	Case 																" +															
	   				"					When  grh.quilometragem  = 0 and lot.linha_id = 1 and mdl.velocimetro = 'S' then emp.org_code   " +
	   				"					When  grh.dias_uso       = 0 and lot.linha_id = 1 and mdl.velocimetro = 'N' then emp.org_code 	" +   
	   				"					When  grh.horas_uso      = 0 and lot.linha_id = 2 then emp.org_code  							" +
	   				"					When  std.faturamento_ym = 'S' then  emp.org_code 												" +  
	   				" 				Else 																								" +																
	   				"					'Cliente' 																						" + 															
	   				"				End 																								" +
	   				"			) 																		" +
	   				" UNION	ALL																		" +
	   				" SELECT																		" +
	   				"	     0                                                 	valorMaoObra     	" +
	   				"	   , 0                                                 	valorPeca        	" +
	   				"	   , sum(round((grl.quantidade * grl.vl_preco_sugerido * .1),2)) valorRemessa	" +
	   				"	   , 0	                                                valorMaoObraTerc 	" +
	   				"	   , emp.org_code                           			destinatario		" +
	   				//"	   , round(sum(grl.quantidade * grl.vl_preco_sugerido 						" +
	   				//"		 * .1 * fgr.aliq_icms_yamaha / 100 ),2) 			icmsYamaha			" +
	   				//" 	   , round(sum(grl.quantidade * grl.vl_preco_sugerido						" +
	   				//"		 * .1 * fgr.aliq_icms_cliente / 100 ),2) 			icmsCliente			" +
	   				"	   FROM ym_sg_garantia_header	grh											" +
	   				"	   ,     ym_sg_lote             lot											" +
	   				"	   ,     ym_sg_concessionaria   con											" +
	   				//"	   ,     ym_sg_fator_garantia   fgr											" +
	   				"	   ,     ym_sg_faturamento      fat											" +
	   				"	   ,     ym_sg_garantia_line    grl											" +
	   				"	   ,     ym_sg_empresa          emp											" +
	   				"	   WHERE grh.lote_id 		   = lot.lote_id								" +
	   				"	   AND   grh.modelo  		   = fat.chassi									" +
	   				"	   AND   lot.concessionaria_id = con.concessionaria_id						" +
	   				//"	   AND   fgr.estado 		   = con.estado									" +
	   				"	   AND   grh.garantia_id 	   = grl.garantia_id 							" +
	   				"	   AND   fat.organization_id   = emp.organization_id						" +
	   				"	   AND   grh.garantia_id 	   in (" + garantias + ")   					" +
	   				"      AND   lot.end_date  is null												" +
	   				"	   AND   grl.end_date  is null							                    " +
	   				"	   AND  fat.end_date is null												" + 
	   				"	   AND   grh.status_mov_id   in (0, 12)										" +
	   				"	   AND   grl.envia_peca        = 'S'										" +
	   				"	   AND   lot.status_lote_id  in (1, 8, 9)									" +
	   				"	   group by emp.org_code													" +
	   				" UNION	ALL																		" +
	   				" SELECT																		" +
	   				"	     0 									valorMaoObra						" +
	   				"	   , 0          						valorPeca 							" +
	   				"	   , 0          						valorRemessa						" +
	   				"	   , round(sum(grh.vl_servico_terceiro),2) valorMaoObraTerc					" +
	   				"	   , emp.org_code                           destinatario 					" +
	   				//"	   , round(sum(grh.vl_servico_terceiro 										" +
	   				//"	     * .1 * fgr.aliq_icms_yamaha / 100 ),2) icmsYamaha						" +
	   				//"	   , round(sum(grh.vl_servico_terceiro 										" +
	   				//"	     * fgr.aliq_icms_cliente / 100 ),2)    icmsCliente						" +
	   				"	   FROM ym_sg_garantia_header 	grh											" +
	   				"	   ,     ym_sg_lote    			lot											" +
	   				"	   ,     ym_sg_concessionaria   con											" +
	   				//"	   ,     ym_sg_fator_garantia	fgr											" +
	   				"	   ,     ym_sg_faturamento 	 	fat											" +
	   				"	   ,     ym_sg_empresa      	emp											" +
	   				"	   WHERE grh.lote_id 		   = lot.lote_id								" +
	   				"	   AND   grh.modelo  		   = fat.chassi									" +
	   				"	   AND   lot.concessionaria_id = con.concessionaria_id						" +
	   				//"	   AND   fgr.estado            = con.estado									" +
	   				"	   AND   fat.organization_id   = emp.organization_id						" +
	   				"	   AND   grh.garantia_id 	   in (" + garantias + ")     				    " +
	   				"	   AND   grh.end_date is null 											    " +
	   				//"      AND   fgr.end_date is null												" +
	   				"	   AND  fat.end_date is null												" + 
	   				"	   AND   grh.status_mov_id   in (0, 12)										" +
	   				"	   AND   lot.status_lote_id  in (1, 8, 9)									" +
	   				"	   group by emp.org_code, grh.garantia_id									" +
	   				"	) group by DESTINATARIO														" ;
	   
	   Session session = null;
	   
	   try {
		   
			session = super.getSession();
			SQLQuery query  = session.createSQLQuery(sql);
			
			NotaFiscalVO notaFiscalVO = null;
			List results = new ArrayList();
			
			Iterator itens = query.list().iterator();
			
			while ( itens.hasNext() ) {
			
				notaFiscalVO = new NotaFiscalVO();
				Object[] row = (Object[]) itens.next();
				
				double valorMO = row[0] != null ? (Double.valueOf(row[0].toString())).doubleValue() : 0;
				notaFiscalVO.setValorMaoObra(valorMO);
				
				double valorPeca = row[1] != null ? (Double.valueOf(row[1].toString())).doubleValue() : 0 ;
				notaFiscalVO.setValorPeca(valorPeca);
				
				double valorRemessa = row[2] != null ? (Double.valueOf(row[2].toString())).doubleValue() : 0 ;
				notaFiscalVO.setValorRemessa(valorRemessa);
				//System.out.println("Remessa line: " + valorRemessa);
				
				double valorMaoObraTerc = row[3] != null ? (Double.valueOf(row[3].toString())).doubleValue() : 0 ;
				notaFiscalVO.setValorMaoObraTerc(valorMaoObraTerc);
				
				notaFiscalVO.setDestinatario(row[4].toString());
				
				results.add(notaFiscalVO);
			}
			
			return results;
			
	   } catch( Exception exp ) { 
			
			throw new DaoException(exp);
			
		} finally {
			session.close();
		}
   }
   
   
   /** Retorna um objeto NotaFiscalVO do cupom enviado.
    * 
    * @param cupom
    * 	<code>Cupom</code> Entidade de cupom 
    *  
    *  @return
    *      Retorna uma lista de objetos NotaFiscalVO ou nulo.
    *  
    *  @throws DaoException
    *      Se houverem erros durante a execução estes serão lançados
    *      como uma Exception deste tipo.
    */
   public List getItembyCupom( Cupom cupom ) throws DaoException {
	   
	   String sql = " SELECT	vl_revisao + nvl(vl_bonificacao,0)	valorMaoObra " +
	   				"   		, emp.org_code                      destinatario" +
	   				" 	    	, vl_revisao + nvl(vl_bonificacao,0)* fgr.aliq_icms_yamaha / 100    icmsYamaha" +
	   				"	    	, vl_revisao + nvl(vl_bonificacao,0) * fgr.aliq_icms_cliente / 100  icmsCliente" +
	   				" FROM  	ym_sg_cupom 	       cup" +
	   				"			,ym_sg_lote            lot" +
	   				"			,ym_sg_concessionaria  con" +
	   				"			,ym_sg_fator_garantia  fgr" +
	   				"			,ym_sg_faturamento     fat" +
	   				"			,ym_sg_empresa         emp" +
	   				" WHERE 	cup.lote_id 		    = lot.lote_id" +
	   				"	AND   cup.modelo  		    = fat.chassi" +
	   				"	AND   lot.concessionaria_id = con.concessionaria_id" +
	   				"	AND   fgr.estado 		    = con.estado" +
	   				"	AND   fat.organization_id   = emp.organization_id" +
	   				"	AND   cup.cupom_id 	        = :p_cupomId";  
	   
	   Session session = null;
	   
	   try {
		   
		   session = super.getSession();
		   SQLQuery query  = session.createSQLQuery(sql);
	   
		   query.setParameter("p_cupomId", cupom.getEntityId());
			
		   NotaFiscalVO notaFiscalVO = null;
		   List results = new ArrayList();
			
		   Iterator itens = query.list().iterator();
			
		   while ( itens.hasNext() ) {
			
			   notaFiscalVO = new NotaFiscalVO();
			   Object[] row = (Object[]) itens.next();
				
			   double valorMO = row[0] != null ? (Double.valueOf(row[0].toString())).doubleValue() : 0;
			   notaFiscalVO.setValorMaoObra(valorMO);
				
			   notaFiscalVO.setDestinatario(row[1].toString());
			   
			   Double icmsYamaha = row[2] != null ? (Double.valueOf(row[2].toString())) : new Double(0) ;
			   notaFiscalVO.setIcmsYamaha(icmsYamaha);
			   
			   Double icmsCliente = row[3] != null ? (Double.valueOf(row[3].toString())) : new Double(0) ;
			   notaFiscalVO.setIcmsCliente(icmsCliente);
				
			   results.add(notaFiscalVO);
			}
			
			return results;
			
	   	} catch( Exception exp ) { 
			
			throw new DaoException(exp);
			
		} finally {
			session.close();
		}
	   
   }
   
   /** Retorna um objeto NotaFiscalVO da garantia enviada.
    * 
    * @param garantia
    * 	<code>Garantia</code> Entidade de garantia 
    *  
    *  @return
    *      Retorna uma lista de objetos NotaFiscalVO ou nulo.
    *  
    *  @throws DaoException
    *      Se houverem erros durante a execução estes serão lançados
    *      como uma Exception deste tipo.
    */
   public List getItembyGarantia( GarantiaHeader garantia ) throws DaoException {
	   
	   String sql = " select  SUM(valorMaoObra) 													" +
					"	    , SUM(valorPeca) 														" +
					"   	, SUM(valorRemessa) 													" +
					"	   	, SUM(valorMaoObraTerc) 												" +
					"		, destinatario from (													" +					
					" SELECT 																		" +
					"	     sum(vl_servico) 								valorMaoObra			" +
					"	   , 0 	             								valorPeca 				" +
					"	   , 0               								valorRemessa 			" +
					"	   , 0	             								valorMaoObraTerc 		" +
					"	   , emp.org_code    								destinatario  			" +
					"	   , sum(vl_servico * fgr.aliq_icms_yamaha / 100 ) 	icmsYamaha				" +
					"      , sum(vl_servico * fgr.aliq_icms_cliente / 100 )	icmsCliente				" +
					"	   FROM ym_sg_garantia_header grh 											" +
					"	   ,     ym_sg_lote           lot											" +
					"	   ,     ym_sg_concessionaria con											" +
					"	   ,     ym_sg_fator_garantia fgr											" +
					"	   ,     ym_sg_faturamento    fat											" +
					"	   ,     ym_sg_servico_grupo  sgr											" +
					"	   ,     ym_sg_empresa        emp											" +
					"	   WHERE grh.lote_id 		 	= lot.lote_id								" +
					"	   AND   grh.modelo  		 	= fat.chassi								" +
					"	   AND   lot.concessionaria_id 	= con.concessionaria_id						" +
					"	   AND   fgr.estado 			= con.estado								" +
					"	   AND   grh.garantia_id 	 	= sgr.garantia_id							" +
					"	   AND   sgr.end_date is null												" +
					"	   AND   fat.organization_id 	= emp.organization_id						" +
					"	   AND   grh.garantia_id 	 	= :p_garantiaId                      		" +
					"	   group by emp.org_code 													" +
					" UNION 																		" +
					" SELECT 																		" +
					"	     0 											valorMaoObra 				" +
					"	   , sum(grl.vl_peca_garantia * grl.quantidade) valorPeca 					" +
					"	   , 0                              	        valorRemessa 				" +
					"	   , 0	                                        valorMaoObraTerc 			" +
					"	   , (																		" +
	   				"           Case 																" +															
	   				"				When  grh.quilometragem  = 0 and lot.linha_id = 1 and mdl.velocimetro = 'S' then emp.org_code   " +
	   				"				When  grh.dias_uso       = 0 and lot.linha_id = 1 and mdl.velocimetro = 'N' then emp.org_code 	" +   
	   				"				When  grh.horas_uso      = 0 and lot.linha_id = 2 then emp.org_code  							" +
	   				"				When  std.faturamento_ym = 'S' then  emp.org_code 												" +  
	   				" 			Else 																								" +																
	   				"				'Cliente' 																						" + 															
	   				"			End 																								" +
	   				"		)  												destinatario    	" +
					"	   , sum(grl.vl_peca_garantia * grl.quantidade  							" +
					"		 * fgr.aliq_icms_yamaha / 100 )				icmsYamaha 					" +
					"	   , sum(grl.vl_peca_garantia * grl.quantidade  							" +
					"		 * fgr.aliq_icms_cliente / 100 )			icmsCliente 				" +
					"	   FROM ym_sg_garantia_header			grh 								" +
					"	   ,     ym_sg_lote                     lot 								" +
					"	   ,     ym_sg_concessionaria  			con									" +
					"	   ,     ym_sg_fator_garantia			fgr									" +
					"	   ,     ym_sg_tipo_lote                tpl									" +
					"	   ,     ym_sg_faturamento              fat									" +
					"	   ,     ym_sg_garantia_line            grl									" +
					"	   ,     ym_sg_empresa                  emp									" +
					"	   WHERE grh.lote_id 		  = lot.lote_id									" +
					"	   AND   grh.modelo  		  = fat.chassi									" +
					"	   AND  lot.concessionaria_id = con.concessionaria_id						" +
					"	   AND   fgr.estado 		  = con.estado									" +
					"	   AND   grh.garantia_id 	  = grl.garantia_id								" +
					"	   AND   fat.organization_id  = emp.organization_id							" +
					"	   AND   tpl.tipo_lote_id 	  = lot.tipo_lote_id							" +
					"	   AND   grh.garantia_id 	  = :p_garantiaId		                        " +
					"	   group by 																" +
					"			(																	" +
	   				"           	Case 																" +															
	   				"					When  grh.quilometragem  = 0 and lot.linha_id = 1 and mdl.velocimetro = 'S' then emp.org_code   " +
	   				"					When  grh.dias_uso       = 0 and lot.linha_id = 1 and mdl.velocimetro = 'N' then emp.org_code 	" +   
	   				"					When  grh.horas_uso      = 0 and lot.linha_id = 2 then emp.org_code  							" +
	   				"					When  std.faturamento_ym = 'S' then  emp.org_code 												" +  
	   				" 				Else 																								" +																
	   				"					'Cliente' 																						" + 															
	   				"				End 																								" +
	   				"			) 																" +
					" UNION																			" +
					" SELECT																		" +
					"	     0                                                 	valorMaoObra     	" +
					"	   , 0                                                 	valorPeca        	" +
					"	   , sum(grl.quantidade * grl.vl_preco_sugerido * .1)  	valorRemessa     	" +
					"	   , 0	                                                valorMaoObraTerc 	" +
					"	   , emp.org_code                                      	destinatario     	" +
					"	   , sum(grl.quantidade * grl.vl_preco_sugerido 							" +
					"		 * .1 * fgr.aliq_icms_yamaha / 100 ) 				icmsYamaha			" +
					" 	   , sum(grl.quantidade * grl.vl_preco_sugerido 							" +
					"		 * .1 * fgr.aliq_icms_cliente / 100 ) 				icmsCliente			" +
					"	   FROM ym_sg_garantia_header	grh											" +
					"	   ,     ym_sg_lote             lot											" +
					"	   ,     ym_sg_concessionaria   con											" +
					"	   ,     ym_sg_fator_garantia   fgr											" +
					"	   ,     ym_sg_faturamento      fat											" +
					"	   ,     ym_sg_garantia_line    grl											" +
					"	   ,     ym_sg_empresa          emp											" +
					"	   WHERE grh.lote_id 		   = lot.lote_id								" +
					"	   AND   grh.modelo  		   = fat.chassi									" +
					"	   AND   lot.concessionaria_id = con.concessionaria_id						" +
					"	   AND   fgr.estado 		   = con.estado									" +
					"	   AND   grh.garantia_id 	   = grl.garantia_id 							" +
					"	   AND   fat.organization_id   = emp.organization_id						" +
					"	   AND   grh.garantia_id 	   = :p_garantiaId		                        " +
					"	   AND   grl.envia_peca        = 'S'										" +
					"	   group by emp.org_code 													" +
					" UNION																			" +
					" SELECT																		" +
					"	     0 									valorMaoObra						" +
					"	   , 0          						valorPeca 							" +
					"	   , 0          						valorRemessa						" +
					"	   , sum(grh.vl_servico_terceiro) 		valorMaoObraTerc					" +
					"	   , emp.org_code  				  		destinatario						" +
					"	   , sum(grh.vl_servico_terceiro 											" +
					"	     * .1 * fgr.aliq_icms_yamaha / 100 )icmsYamaha							" +
					"	   , sum(grh.vl_servico_terceiro 											" +
					"	     * fgr.aliq_icms_cliente / 100 )    icmsCliente							" +
					"	   FROM ym_sg_garantia_header 	grh											" +
					"	   ,     ym_sg_lote    			lot											" +
					"	   ,     ym_sg_concessionaria   con											" +
					"	   ,     ym_sg_fator_garantia	fgr											" +
					"	   ,     ym_sg_faturamento 	 	fat											" +
					"	   ,     ym_sg_empresa      	emp											" +
					"	   WHERE grh.lote_id 		   = lot.lote_id								" +
					"	   AND   grh.modelo  		   = fat.chassi									" +
					"	   AND   lot.concessionaria_id = con.concessionaria_id						" +
					"	   AND   fgr.estado            = con.estado									" +
					"	   AND   fat.organization_id   = emp.organization_id						" +
					"	   AND   grh.garantia_id 	   = :p_garantiaId		                        " +
					"	   group by emp.org_code													" +
					"	) group by DESTINATARIO														" ;
	   
	   Session session = null;
	   
	   try {
		   
		   session = super.getSession();
		   SQLQuery query  = session.createSQLQuery(sql);
		
			query.setParameter("p_garantiaId", garantia.getEntityId());
			
			NotaFiscalVO notaFiscalVO = null;
			List results = new ArrayList();
			
			Iterator itens = query.list().iterator();
			
			while ( itens.hasNext() ) {
			
				notaFiscalVO = new NotaFiscalVO();
				Object[] row = (Object[]) itens.next();
				
				double valorMO = row[0] != null ? (Double.valueOf(row[0].toString())).doubleValue() : 0;
				notaFiscalVO.setValorMaoObra(valorMO);
				
				double valorPeca = row[1] != null ? (Double.valueOf(row[1].toString())).doubleValue() : 0 ;
				notaFiscalVO.setValorPeca(valorPeca);
				
				double valorRemessa = row[2] != null ? (Double.valueOf(row[2].toString())).doubleValue() : 0 ;
				notaFiscalVO.setValorRemessa(valorRemessa);
				
				double valorMaoObraTerc = row[3] != null ? (Double.valueOf(row[3].toString())).doubleValue() : 0 ;
				notaFiscalVO.setValorMaoObraTerc(valorMaoObraTerc);
				
				notaFiscalVO.setDestinatario(row[4].toString());
				
				results.add(notaFiscalVO);
			}
			
			return results;
			
	   	} catch( Exception exp ) { 
			
			throw new DaoException(exp);
			
		} finally {
			session.close();
		}
  }
   
   /** Recupera uma NotaFiscal pelo número e concessionária
    * 
    * @param numeroNF
    * @param concessionaria
    * @return NotaFiscal
    * @throws DaoException
    */
   public NotaFiscal getNotaFiscalByNumberAndConcessionaria(Long numeroNF, Concessionaria concessionaria ) throws DaoException {
	   	   
	   DetachedCriteria criteria = super.getDetachedCriteria(NotaFiscal.class);
       criteria.add( Expression.eq("numeroNF", numeroNF) );
       criteria.add( Expression.eq("concessionaria", concessionaria) );

       List results = super.getHibernateTemplate().findByCriteria(criteria);

       // Se houver um registro, retornamos sendo o cliente da pesquisa.       
       // Se não houver nenhum registro, retornamos nulo.
       if ( results.size() > 0 )
           return (NotaFiscal) results.get(0);         
       else
           return null;
   }
   
   /** Recupera uma NotaFiscal pelo número, série e concessionária
    * 
    * @param numeroNF
    * @param serieNF
    * @param concessionaria
    * @return NotaFiscal
    * @throws DaoException
    */
   public NotaFiscal getNotaFiscalByNumberAndSerieAndConcessionaria(Long numeroNF, String serie, Concessionaria concessionaria ) throws DaoException {
	   	   
	   DetachedCriteria criteria = super.getDetachedCriteria(NotaFiscal.class);
       criteria.add( Expression.eq("numeroNF"      , numeroNF) );
       criteria.add( Expression.eq("serieNF"       , serie.toUpperCase()) );
       criteria.add( Expression.eq("concessionaria", concessionaria) );

       List results = super.getHibernateTemplate().findByCriteria(criteria);

       // Se houver um registro, retornamos sendo o cliente da pesquisa.       
       // Se não houver nenhum registro, retornamos nulo.
       if ( results.size() > 0 )
           return (NotaFiscal) results.get(0);         
       else
           return null;
   }
   
   /** Recupera um Faturamento pelo chassi
    * 
    * @param chassi
    * @return faturamento
    * @throws DaoException
    */
   public Faturamento getFaturamentoByChassi(String chassi ) throws DaoException {
	   	   
	   DetachedCriteria criteria = super.getDetachedCriteria(Faturamento.class);
       criteria.add( Expression.eq("chassi", chassi.toUpperCase()) );
       criteria.setFetchMode("empresa", FetchMode.JOIN);
       criteria.setFetchMode("linha", FetchMode.JOIN);
       criteria.setFetchMode("concessionaria", FetchMode.JOIN);
       criteria.addOrder(Order.desc("dataCriacao"));  //edson 01/07/2013

       List results = super.getHibernateTemplate().findByCriteria(criteria);

       // Se houver um registro, retornamos sendo o cliente da pesquisa.
       // Se houver mais de um registro temos um problema de banco de dados.
       // Se não houver nenhum registro, retornamos nulo.
       /* //edson 01/07/2013
       if ( results.size() == 1 )
           return (Faturamento) results.get(0);
           
       else if ( results.size() > 1 )
           throw new DaoException("Too many entities found. There should be only one.");
       */
       if ( results.size() > 0 ) //edson 01/07/2013
           return (Faturamento) results.get(0);
       else
           return null;
   }

}
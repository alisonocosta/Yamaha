/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioHibernateDaoImpl.java
 *   .: Criação.....12 de julho, 12:16
 *   .: Autor.......Gisele Panosso
 *   .: Descrição...Entidade "RelatorioHibernateDaoImpl".
 */
package br.com.yamaha.sistemagarantia.dao.hibernate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import br.com.resource.infra.dao.hibernate.HibernateDaoImpl;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.DaoException;
import br.com.resource.infra.utils.DateUtils;
import br.com.yamaha.sistemagarantia.controller.helper.ReportQueryGraphicHelper;
import br.com.yamaha.sistemagarantia.controller.helper.ReportQueryHelper;
import br.com.yamaha.sistemagarantia.dao.RelatorioDao;
import br.com.yamaha.sistemagarantia.model.NotaFiscal;
import br.com.yamaha.sistemagarantia.model.StatusLote;
import br.com.yamaha.sistemagarantia.view.EntityGraficoGarantiaMensalPercentualVO;
import br.com.yamaha.sistemagarantia.view.EntityGraficoGarantiaPagaVO;
import br.com.yamaha.sistemagarantia.view.EntityGraficosIndividuaisVO;
import br.com.yamaha.sistemagarantia.view.RelatorioAnalisePecasVO;
import br.com.yamaha.sistemagarantia.view.RelatorioConsultaLoteConcVO;
import br.com.yamaha.sistemagarantia.view.RelatorioConsultaLoteStatusVO;
import br.com.yamaha.sistemagarantia.view.RelatorioConsultaLoteVO;
import br.com.yamaha.sistemagarantia.view.RelatorioConsultaNfConcVO;
import br.com.yamaha.sistemagarantia.view.RelatorioConsultaNfEmpVO;
import br.com.yamaha.sistemagarantia.view.RelatorioConsultaNfLoteVO;
import br.com.yamaha.sistemagarantia.view.RelatorioConsultaNfNotaVO;
import br.com.yamaha.sistemagarantia.view.RelatorioDocRevisaoDetalheVO;
import br.com.yamaha.sistemagarantia.view.RelatorioDocRevisaoVO;
import br.com.yamaha.sistemagarantia.view.RelatorioGarPecasSubReportVO;
import br.com.yamaha.sistemagarantia.view.RelatorioGarantiaPecasModSubRepVO;
import br.com.yamaha.sistemagarantia.view.RelatorioGarantiaPecasModVO;
import br.com.yamaha.sistemagarantia.view.RelatorioGarantiaPecasVO;
import br.com.yamaha.sistemagarantia.view.RelatorioGerenciamentoMesVO;
import br.com.yamaha.sistemagarantia.view.RelatorioGraficosIndividuaisTableVO;
import br.com.yamaha.sistemagarantia.view.RelatorioHistChassiCabecVO;
import br.com.yamaha.sistemagarantia.view.RelatorioHistChassiGarantiaVO;
import br.com.yamaha.sistemagarantia.view.RelatorioHistChassiParecerVO;
import br.com.yamaha.sistemagarantia.view.RelatorioHistChassiPecaVO;
import br.com.yamaha.sistemagarantia.view.RelatorioHistChassiRevisaoVO;
import br.com.yamaha.sistemagarantia.view.RelatorioHistChassiTotalVO;
import br.com.yamaha.sistemagarantia.view.RelatorioImportedPartsInvoiceVO;
import br.com.yamaha.sistemagarantia.view.RelatorioImportedPartsVO;
import br.com.yamaha.sistemagarantia.view.RelatorioMalaDiretaVO;
import br.com.yamaha.sistemagarantia.view.RelatorioMensalModeloVO;
import br.com.yamaha.sistemagarantia.view.RelatorioPagamentosVO;
import br.com.yamaha.sistemagarantia.view.RelatorioProcPecaFase2PecaVO;
import br.com.yamaha.sistemagarantia.view.RelatorioProcPecaFase2ServicoVO;
import br.com.yamaha.sistemagarantia.view.RelatorioProcPecasFase1VO;
import br.com.yamaha.sistemagarantia.view.RelatorioProcPecasFase2VO;
import br.com.yamaha.sistemagarantia.view.RelatorioProcRevisoesFase1VO;
import br.com.yamaha.sistemagarantia.view.RelatorioProcRevisoesFase2VO;
import br.com.yamaha.sistemagarantia.view.RelatorioRecusaVO;
import br.com.yamaha.sistemagarantia.view.RelatorioServiceReportGraphVO;
import br.com.yamaha.sistemagarantia.view.RelatorioSolicitacaoGarantiaNotaVO;
import br.com.yamaha.sistemagarantia.view.RelatorioSolicitacaoGarantiaPecaVO;
import br.com.yamaha.sistemagarantia.view.RelatorioSolicitacaoGarantiaVO;
import br.com.yamaha.sistemagarantia.view.RelatorioVerificacaoVO;
import br.com.yamaha.sistemagarantia.view.RelatorioZeroKmVO;

public class RelatorioHibernateDaoImpl extends HibernateDaoImpl implements RelatorioDao {

	/** Lista os dados para o Relatório Zero Km.
	 * 
	 * @param dataIni
	 * 	<code>dataIni</code> data início
	 * 
	 * @param dataFim
	 * 	<code>dataFim</code> data fim
	 * 
	 * @param km
	 * 	<code>km</code> quilometragem
	 *  
	 * @return list
	 * 	<code>list</code> lista de dados do Relatório Zero Km.
	 * 
	 * @throws DaoException
	 */	
	public List listRelatorioZeroKm(Date dataIni, Date dataFim, Long km, Long linha) throws DaoException {
		
		String sql =" SELECT yl.descr_linha                                         linha          " + 
					"      , substr(gh.modelo, 1, 5)                                modelo         " + 
					"      , substr(gh.modelo, 7, 7)                                chassi         " + 
					"      , it.item_code                                           codPeca        " + 
					"      , it.descricao                                           descricao      " + 
					"      , gl.quantidade                                          quantidade     " + 
					"      , decode(gl.line_id, 1, 'S', null)                       pecaCausadora  " + 
					"      , decode(gl.cobra_peca,'S',get_vlpeca(gh.garantia_id),0) valorPeca      " + 
					"      , get_vlmobra(gh.garantia_id)                            valorMobra     " + 
					"      , gh.vl_servico_terceiro                                 valorServico3  " + 
					"      , si.sintoma_code                                        codSintoma     " + 
					"      , si.descricao                                           descSintoma    " + 
					"      , co.condicao_code                                       codCondicao    " + 
					"      , co.descricao                                           descCondicao   " + 				
					"   FROM ym_sg_garantia_header       gh                                        " + 
					"      , ym_sg_garantia_line         gl                                        " + 
					"      , ym_sg_item                  it                                        " + 
					"      , ym_sg_sintoma               si                                        " + 
					"      , ym_sg_condicao              co                                        " + 
					"      , ym_sg_lote                  lt                                        " + 
					"      , ym_sg_linha                 yl                                        " + 
					"  WHERE gh.garantia_id      =  gl.garantia_id	                               " + 
					"    AND gl.item_id          =  it.item_id                                     " + 
					"    AND gh.sintoma_id       =  si.sintoma_id                                  " + 
					"    AND gh.condicao_id      =  co.condicao_id                                 " + 
					"    AND gh.lote_id          =  lt.lote_id                                     " + 
					"    AND lt.linha_id         =  yl.linha_id                                    " + 
					"    AND lt.status_lote_id   in (5,6,7,9,10,11)                                " + 
					"    AND lt.linha_id         =  nvl(:p_linha,lt.linha_id)                      " + 
					"    AND trunc(lt.fechamento_date) >= to_date(:p_dt_ini,'DD/MM/YYYY')          " + 
					"    AND trunc(lt.fechamento_date) <  to_date(:p_dt_fim,'DD/MM/YYYY')+1        " + 
					"    AND ((lt.linha_id  = 1 AND gh.quilometragem < :p_km) OR                   " + 
					"         (lt.linha_id <> 1 AND nvl(gh.dias_uso,gh.horas_uso) < :p_km))        " +
					"    ORDER BY Modelo, Chassi                                                   " ;
		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);

		query.setParameter("p_dt_ini" , DateUtils.applyMask(dataIni));
		query.setParameter("p_dt_fim" , DateUtils.applyMask(dataFim));
		query.setParameter("p_km"     , km);
		query.setParameter("p_linha"  , linha);
				
		List results = new ArrayList();	
		try {
			Iterator zeroKm = query.list().iterator();
	
			while ( zeroKm.hasNext() ) {
				
				RelatorioZeroKmVO relatorioZeroKm = new RelatorioZeroKmVO();
				Object[] row = (Object[]) zeroKm.next();
				relatorioZeroKm.setLinha((String)row[0]);	
				relatorioZeroKm.setModelo((String)row[1]);			
				relatorioZeroKm.setChassi((String)row[2]);
				relatorioZeroKm.setCodPeca((String)row[3]);
				relatorioZeroKm.setDescricao((String)row[4]);
				relatorioZeroKm.setQuantidade((BigDecimal)row[5]);
				relatorioZeroKm.setPecaCausadora((String)row[6]);
				relatorioZeroKm.setValorPeca((BigDecimal)row[7]);
				relatorioZeroKm.setValorMobra((BigDecimal)row[8]);
				relatorioZeroKm.setValorServico3((BigDecimal)row[9]);
				relatorioZeroKm.setCodSintoma((String)row[10]);
				relatorioZeroKm.setDescSintoma((String)row[11]);
				relatorioZeroKm.setCodCondicao((String)row[12]);
				relatorioZeroKm.setDescCondicao((String)row[13]);	
				
				results.add(relatorioZeroKm);
	            
			}		
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		
		return results;
	}

	/** Lista os dados para o Relatório de Análise de Peças.
	 * 
	 * @param dataIni
	 * 	<code>dataIni</code> data início
	 * 
	 * @param dataFim
	 * 	<code>dataFim</code> data fim
	 *  
	 * @param tipoProduto
	 * 	<code>tipoProduto</code> tipo de produto
	 * 
	 * @param peca
	 * 	<code>peca</code> peça
	 * 
	 * @param modelo
	 * 	<code>modelo</code> modelo
	 *  
	 * @return list
	 * 	<code>list</code> lista de dados do Relatório de Pagamentos.
	 * 
	 * @throws DaoException
	 */			
	public List listRelatorioAnalisePecas( Date   dataIni
							             , Date   dataFim
							             , Long   tipoProduto
							             , Long   peca
							             , String modelo) throws DaoException {
		
		String sql = " SELECT  to_char(gh.data_fechamento_os, 'YYYY')  Ano "                                                    +    
					 "       , to_char(gh.data_fechamento_os, 'MM')    Mes "                                                    + 
					 "       , substr(gh.modelo, 1, 5)                 Modelo "                                                 + 
					 "       , substr(gh.modelo, 6, 7)                 Chassi "                                                 + 
					 "       , it.item_code                            Codigo_Peca "                                            + 
					 "       , initcap(it.descricao)                   Descricao_Peca "                                         + 
					 "       , gl.quantidade                           Quantidade "                                             + 
					 "       , gl.vl_peca_garantia                     Valor_Unitario "                                         + 
					 "       , si.sintoma_code                         Codigo_Sintoma "                                         + 
					 "       , co.condicao_code                        Codigo_condicao "                                        + 
					 "       , (gl.vl_peca_garantia*gl.quantidade)     Valor_Total "                                            + 
					 "   FROM  ym_sg_garantia_header       gh "                                                                 + 
					 " 	    , ym_sg_garantia_line         gl "                                                                  + 
					 " 	    , ym_sg_item                  it "                                                                  + 
					 " 	    , ym_sg_sintoma               si "                                                                  + 
					 " 	    , ym_sg_condicao              co "                                                                  + 
					 " 	    , ym_sg_lote                  lt "                                                                  + 
					 "   WHERE gh.garantia_id            = gl.garantia_id "                                                     + 
					 "     AND gl.item_id                = it.item_id "                                                         + 
					 "     AND gh.sintoma_id             = si.sintoma_id "                                                      + 
					 "     AND gh.condicao_id            = co.condicao_id "                                                     + 
					 "     AND gh.lote_id                = lt.lote_id "                                                         + 
					 "     AND TO_DATE(TO_CHAR(gh.data_fechamento_os, 'MM/RRRR'), 'MM/RRRR') >= TO_DATE(:p_dt_ini, 'MM/RRRR') " + 
					 "     AND TO_DATE(TO_CHAR(gh.data_fechamento_os, 'MM/RRRR'), 'MM/RRRR') <= TO_DATE(:p_dt_fim, 'MM/RRRR') " +
					 "     AND lt.tipo_lote_id           = :p_tipo_produto "                                                    +
					 "     AND it.item_code              = :p_peca "                                                            +
					 "     AND substr(gh.modelo, 1, 5)   = :p_modelo"                                                           +  					 
					 "     ORDER BY Quantidade desc ";         
			
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);

		query.setParameter("p_dt_ini" , dataIni);
		query.setParameter("p_dt_fim" , dataFim);
		query.setParameter("p_tipo_produto", tipoProduto);
		query.setParameter("p_peca", peca);
		query.setParameter("p_modelo", modelo);
				
		List results = new ArrayList();		
		
		try {
			
			Iterator analisePecas = query.list().iterator();
	
			while ( analisePecas.hasNext() ) {
				
				RelatorioAnalisePecasVO relatorioAnalisePecas = new RelatorioAnalisePecasVO();
				Object[] row = (Object[]) analisePecas.next();
				relatorioAnalisePecas.setConcessionaria((String)row[0]);			
				relatorioAnalisePecas.setChassi((String)row[1]);
				relatorioAnalisePecas.setCodPeca((String)row[2]);
				relatorioAnalisePecas.setDescricao((String)row[3]);
				relatorioAnalisePecas.setVlPeca((Long)row[4]);
				relatorioAnalisePecas.setPercTotal((Long)row[5]);
				relatorioAnalisePecas.setVlAcumulado((Long)row[6]);			
				relatorioAnalisePecas.setCodSintoma((String)row[7]);
				relatorioAnalisePecas.setCodCondicao((String)row[8]);	
				
				results.add(relatorioAnalisePecas);
			}	
			
        } catch ( Exception exp ) {
 			
 			throw new DaoException(exp);
 			
 		}  finally {
 			session.close();
 		}
		
		return results;

	}		

	/** Lista os dados para o Relatório de Pagamentos.
	 * 
	 * @param dataIni
	 * 	<code>dataIni</code> data início
	 * 
	 * @param dataFim
	 * 	<code>dataFim</code> data fim
	 *  
	 * @param concessionaria
	 * 	<code>concessionaria</code> concessionária
	 * 
	 * @param tipoServico
	 * 	<code>tipoServico</code> tipo de serviço
	 * 
	 * @return list
	 * 	<code>list</code> lista de dados do Relatório de Pagamentos.
	 * 
	 * @throws DaoException
	 */		
	public List listRelatorioPagamentos(Date dataIni, Date dataFim, Long concessionaria, String tipoServico ) throws DaoException{

		String sql = " 	SELECT cc.cnpj			    codigo "                  + 
				 	 " 	     , cc.razao_social  	nome_concessionaria "     + 
					 " 	     , sum(fg.vlpeca)		valor_peca  "             + 
					 " 	     , sum(fg.vlserv)		valor_servico "           + 
					 " 	     , sum(fg.vlserv3)		valor_servico3 "          + 
					 " 	     , sum(fg.vlrev)		valor_revisao "           + 
					 " 	 FROM  ym_sg_fato_garantia  fg "                      + 
					 " 	     , ym_sg_concessionaria cc "                      + 
					 " 	WHERE fg.concessionaria_id    = cc.concessionaria_id "+  
					 " 	  AND fg.tipo_fato            IN ( '1', '2') "        + 
					 " 	  AND cc.concessionaria_id    = :p_concessionaria"    + 
					 " 	  AND fg.tipo_servico         = :p_tipo_servico "     + 
					 "    AND TRUNC(fg.data_fech_os) >= :p_dt_ini "           + 
					 "    AND TRUNC(fg.data_fech_os) <= :p_dt_fim "           + 
					 " 	GROUP BY cc.cnpj "                                    + 
					 " 	       , cc.razao_social "                            + 
					 " 	ORDER BY cc.razao_social "; 
					
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		query.setParameter("p_concessionaria", concessionaria);
		query.setParameter("p_tipo_servico", tipoServico);
		query.setParameter("p_dt_ini" , dataIni);
		query.setParameter("p_dt_fim" , dataFim);
		
		List results = new ArrayList();	
		
		try {
			Iterator pagamentos = query.list().iterator();
			
			while ( pagamentos.hasNext() ) {
			
				RelatorioPagamentosVO relatorioPagamentos = new RelatorioPagamentosVO();
				Object[] row = (Object[]) pagamentos.next();
				relatorioPagamentos.setCnpj((Long)row[0]);			
				relatorioPagamentos.setConcessionaria((String)row[1]);
				relatorioPagamentos.setValorPeca((Long)row[2]);
				relatorioPagamentos.setValorServico((Long)row[3]);
				relatorioPagamentos.setValorServico3((Long)row[4]);
				relatorioPagamentos.setValorRevisao((Long)row[5]);
				
				results.add(relatorioPagamentos);
			}		
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		
		return results;		
		
	}	
	
	/** Lista os dados para o Relatório Recusa.
	 * 
	 * @param dataIni
	 * 	<code>dataIni</code> data início
	 * 
	 * @param dataFim
	 * 	<code>dataFim</code> data fim
	 * 
	 * @param peca
	 * 	<code>peca</code> peça
	 * 
	 * @param modelo
	 * 	<code>modelo</code> modelo
	 * 
	 * @param concessionaria
	 * 	<code>concessionaria</code> concessionária
	 * 
	 * @param representante
	 * 	<code>representante</code> representante
	 * 
	 * @param analista
	 * 	<code>analista</code> analista
	 * 
	 * @return list
	 * 	<code>list</code> lista de dados do Relatório de Recusa.
	 * 
	 * @throws DaoException
	 */	
	public List listRelatorioRecusa( Long   empresa
								   , Long   concessionaria
								   , Long   linha
								   , String chassi
								   , Date   dataIni
								   , Date   dataFim ) throws DaoException {
		
		String whereEmpresa = empresa.intValue() != 0 ? " AND ep.organization_id = nvl(:p_empresa,ep.organization_id) " : "";
		String whereConcessionaria = concessionaria.intValue() != 0 ? " AND lt.concessionaria_id = nvl(:p_concessionaria, lt.concessionaria_id)" : "";
		
		String sql = "SELECT 'garantia'                                            servico         " +
					"      , gh.modelo                                             chassi          " +
					"      , cp.data_venda                                         dtVenda         " +
					"      , gh.data_fechamento_os                                 dtServico       " +
					"      , gh.lote_id                                            lote            " +
					"      , NVL(gh.quilometragem ,NVL(gh.dias_uso, gh.horas_uso)) kmHr            " +
					"      , it.item_code                                          peca            " +
					"      , rc.descricao                                          recusa          " +
					"      , co.razao_social                                       concessionaria  " +
					"      , co.endereco                                           endereco        " +
					"      , co.cidade                                             cidade          " +
					"      , co.estado                                             estado          " +
					"      , co.cnpj                                               cnpj            " +
					"      , li.descr_linha                                        linhaProduto    " +
					"      , ep.org_code||' - '|| ep.nome_empresa                  empresa         " +
					"      , rv.numero_revisao                                     nrRevisao       " +
					"   FROM ym_sg_garantia_header gh                                              " +
					"      , ym_sg_garantia_line   gl                                              " +
					"      , ym_sg_cupom           cp                                              " +
					"      , ym_sg_lote            lt                                              " +
					"      , ym_sg_concessionaria  co                                              " +
					"      , ym_sg_recusa          rc                                              " +
					"      , ym_sg_revisao         rv                                              " +
					"      , ym_sg_item            it                                              " +
					"      , ym_sg_linha           li                                              " +
					"      , ym_sg_faturamento     ft                                              " +
					"      , ym_sg_empresa         ep                                              " +
					"  WHERE gh.garantia_id        =   gl.garantia_id                              " +
					"    AND gl.item_id            =   it.item_id                                  " +
					"    AND gh.modelo             =   cp.modelo                                   " +
					"    AND gh.lote_id            =   lt.lote_id                                  " +
					"    AND lt.concessionaria_id  =   co.concessionaria_id                        " +
					"    AND gh.recusa_id          =   rc.recusa_id                                " +
					"    AND cp.revisao_id         =   rv.revisao_id                               " +
					"    AND lt.linha_id           =   li.linha_id                                 " +
					"    AND cp.modelo             =   ft.chassi                                   " +
					"    AND ft.organization_id    =   ep.organization_id                          " +
					"    AND rv.numero_revisao     =   1                                           " +
					"    AND gl.line_id            =   1                                           " +
					"    AND rc.recusa_code        NOT IN ('99','00')                              " +
					whereEmpresa +
					whereConcessionaria +
					"    AND lt.linha_id           =   :p_linha                                    " +
					"    AND cp.modelo             =   nvl(:p_chassi, cp.modelo)                   " +
					"    AND trunc(lt.fechamento_date) BETWEEN TO_DATE(:p_data_ini, 'DD/MM/YYYY')  " +    
					" 					                   AND TO_DATE(:p_data_fim, 'DD/MM/YYYY')  " +
					" UNION ALL                                                                    " +
					" SELECT rv.descricao                                          servico         " +
					"      , cp.modelo                                             chassi          " +
					"      , cp.data_venda                                         dtVenda         " +
					"      , cp.data_revisao                                       dtServico       " +
					"      , cp.lote_id                                            lote            " +
					"      , NVL(cp.quilometragem ,NVL(cp.dias_uso, cp.horas_uso)) kmHr            " +
					"      , ' '                                                   peca            " +
					"      , rc.descricao                                          recusa          " +
					"      , co.razao_social                                       concessionaria  " +
					"      , co.endereco                                           endereco        " +
					"      , co.cidade                                             cidade          " +
					"      , co.estado                                             estado          " +
					"      , co.cnpj                                               cnpj            " +
					"      , li.descr_linha                                        linhaProduto    " +
					"      , ep.org_code||' - '|| ep.nome_empresa	               empresa         " +
					"      , rv.numero_revisao                                     nrRevisao       " +
					"   FROM ym_sg_cupom                                        cp                 " +
					"      , ym_sg_lote                                         lt                 " +
					"      , ym_sg_concessionaria                               co                 " +
					"      , ym_sg_recusa                                       rc                 " +
					"      , ym_sg_revisao                                      rv                 " +
					"      , ym_sg_linha                                        li                 " +
					"      , ym_sg_faturamento                                  ft                 " +
					"      , ym_sg_empresa                                      ep                 " +
					"  WHERE cp.lote_id            =   lt.lote_id                                  " +
					"    AND lt.concessionaria_id  =   co.concessionaria_id                        " +
					"    AND cp.recusa_id          =   rc.recusa_id                                " +
					"    AND cp.revisao_id         =   rv.revisao_id                               " +
					"    AND lt.linha_id           =   li.linha_id                                 " +
					"    AND cp.modelo             =   ft.chassi                                   " +
					"    AND ft.organization_id    =   ep.organization_id                          " +
					"    AND rv.numero_revisao     IN  (2,3)                                       " +
					"    AND rc.recusa_code        NOT IN ('99','00')                              " +
					whereEmpresa +
					whereConcessionaria +
					"    AND lt.linha_id           =   :p_linha                                    " +
					"    AND cp.modelo             =   nvl(:p_chassi, cp.modelo)                   " +
					"    AND trunc(lt.fechamento_date) BETWEEN TO_DATE(:p_data_ini, 'DD/MM/YYYY')  " +    
					" 					                   AND TO_DATE(:p_data_fim, 'DD/MM/YYYY')  " +
					"  ORDER BY concessionaria                                                     " +
					"      , servico                                                               " +
					"      , chassi                                                                " ;
			
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);

		if ( empresa.intValue() != 0  )
			query.setParameter("p_empresa" , empresa);
		
		if ( concessionaria.intValue() != 0  )
			query.setParameter("p_concessionaria" , concessionaria);
		
		query.setParameter("p_linha" , linha);
		query.setParameter("p_chassi" , chassi);
		query.setParameter("p_data_ini" , DateUtils.applyMask(dataIni));
		query.setParameter("p_data_fim" , DateUtils.applyMask(dataFim));
		
		List results = new ArrayList();		
		
		try {
			Iterator recusa = query.list().iterator();
			
			while ( recusa.hasNext() ) {
			
				RelatorioRecusaVO relatorioRecusa = new RelatorioRecusaVO();
				Object[] row = (Object[]) recusa.next();
				relatorioRecusa.setServico((String)row[0]);
				relatorioRecusa.setChassi((String)row[1]);
				relatorioRecusa.setDtVenda((Date)row[2]);
				relatorioRecusa.setDtServico((Date)row[3]);
				relatorioRecusa.setLote((Serializable)row[4]);
				relatorioRecusa.setKmHr((BigDecimal)row[5]);
				relatorioRecusa.setPeca((String)row[6]);
				relatorioRecusa.setRecusa((String)row[7]);
				relatorioRecusa.setConcessionaria((String)row[8]);
				relatorioRecusa.setEndereco((String)row[9]);
				relatorioRecusa.setCidade((String)row[10]);
				relatorioRecusa.setEstado((String)row[11]);
				relatorioRecusa.setCnpj((BigDecimal)row[12]);
				relatorioRecusa.setLinhaProduto((String)row[13]);
				relatorioRecusa.setEmpresa((String)row[14]);
				relatorioRecusa.setNrRevisao(Integer.valueOf(row[15].toString()));
				
				results.add(relatorioRecusa);
			}		
	   } catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
	   }  finally {
			session.close();
	   }
		
		return results;

	}

	/** Lista os dados para o Relatório de Verificação.
	 * 
	 * @param dataIni
	 * 	<code>dataIni</code> data início
	 * 
	 * @param dataFim
	 * 	<code>dataFim</code> data fim
	 *  
	 * @param concessionaria
	 * 	<code>concessionaria</code> concessionária
	 * 
	 * @param representante
	 * 	<code>representante</code> representante
	 * 
	 * @param regiao
	 * 	<code>regiao</code> região
	 * 
	 * @param tipoProduto
	 * 	<code>tipoProduto</code> tipo de produto
	 * 
	 * @param valorMinimo
	 * 	<code>valorMinimo</code> valor mínimo
	 *  
	 * @return list
	 * 	<code>list</code> lista de dados do Relatório de Verificação.
	 * 
	 * @throws DaoException
	 */	
	public List listRelatorioVerificacao( Date   dataIni
			                            , Date   dataFim
			                            , Long   linha
			                            , Long   concessionaria			                            
			                            , Long   valorMinimo) throws DaoException {
		
		String sql = "  SELECT distinct co.cnpj                      Cnpj                                                                   " + 
					 "       , co.razao_social                       Concessionaria                                                         " + 
					 "       , co.endereco                           Endereco                                                               " + 
					 "       , co.cep                                Cep                                                                    " + 
					 "       , co.cidade                             Cidade                                                                 " + 
					 "       , co.estado                             Estado                                                                 " + 
					 "       , gh.lote_id                            Lote                                                                   " + 
					 "       , gh.numero_os                          OS                                                                     " + 
					 "       , gh.garantia_id                        SG                                                                     " + 
					 "       , gh.data_fechamento_os                 DtServico                                                              " + 
					 "       , gh.modelo                             Chassi                                                                 " + 
					 "       , decode(lt.linha_id, 1, gh.quilometragem,nvl(gh.dias_uso,gh.horas_uso)) KmHr                                  " + 
					 "       , it.item_code                          Peca                                                                   " + 
					 "       , it.descricao                          Descricao                                                              " + 
					 "       , gl.quantidade                         Quantidade                                                             " + 
					 "       , round(gl.vl_peca_garantia,1)          VlUnitario                                                             " + 
					 "       , gl.quantidade * round(gl.vl_peca_garantia,1) VlTotal                                                         " + 
					 "    FROM ym_sg_lote             lt                                                                                    " + 
					 "       , ym_sg_garantia_header  gh                                                                                    " + 
					 "       , ym_sg_garantia_line    gl	                                                                                " + 
					 "       , ym_sg_item             it                                                                                    " + 
					 "       , ym_sg_concessionaria   co                                                                                    " + 
					 "       , ym_sg_linha            yl                                                                                    " + 
					 "   WHERE gh.garantia_id        = gl.garantia_id                                                                       " + 
					 " 	   AND gl.item_id            = it.item_id                                                                           " + 
					 " 	   AND gh.lote_id            = lt.lote_id                                                                           " + 
					 " 	   AND lt.concessionaria_id  = co.concessionaria_id                                                                 " + 
					 " 	   AND co.concessionaria_id  = nvl(:p_concessionaria, co.concessionaria_id)                                         " + 
					 " 	   AND lt.linha_id           = :p_linha                                                                             " + 
					 "     AND lt.fechamento_date   >= :p_dt_ini                                                     						" + 
					 "     AND lt.fechamento_date   <=  :p_dt_fim                                                    						" + 
					 "     AND gl.quantidade * round(gl.vl_peca_garantia,1)>= nvl(:p_valor_minimo, gl.quantidade * round(gl.vl_peca_garantia,1))" + 
					 "   ORDER BY Concessionaria, Lote, chassi, OS, Peca                                                                    " ;

		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		query.setParameter("p_dt_ini" , dataIni);
		query.setParameter("p_dt_fim" , dataFim);		
		query.setParameter("p_linha", linha);
		query.setParameter("p_concessionaria", concessionaria);				
		query.setParameter("p_valor_minimo", valorMinimo);
		
		List results = new ArrayList();	
		
		try {
		
			Iterator verificacao = query.list().iterator();
			
			while ( verificacao.hasNext() ) {
			
				RelatorioVerificacaoVO relatorioVerificacao = new RelatorioVerificacaoVO();
				Object[] row = (Object[]) verificacao.next();
				relatorioVerificacao.setCnpj(Long.valueOf(row[0].toString()));
				relatorioVerificacao.setConcessionaria((String)row[1]);
				relatorioVerificacao.setEndereco((String)row[2]);
				relatorioVerificacao.setCep((BigDecimal)row[3]);
				relatorioVerificacao.setCidade((String)row[4]);
				relatorioVerificacao.setEstado((String)row[5]);
				relatorioVerificacao.setLote(Long.valueOf(row[6].toString()));				
				relatorioVerificacao.setOrdemServico(Long.valueOf(row[7].toString()));
				relatorioVerificacao.setGarantiaId(Long.valueOf(row[8].toString()));
				relatorioVerificacao.setDtServico((Date)row[9]);
				relatorioVerificacao.setChassi((String)row[10]);
				relatorioVerificacao.setQuilometragem((BigDecimal)row[11]);
				relatorioVerificacao.setPeca((String)row[12]);
				relatorioVerificacao.setDescricao((String)row[13]);
				relatorioVerificacao.setQtdPecas(Long.valueOf(row[14].toString()));
				relatorioVerificacao.setValorPeca((BigDecimal)row[15]);
				relatorioVerificacao.setTotalPecas((BigDecimal)row[16]);			
				
				results.add(relatorioVerificacao);
			}	
			
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		
		return results;		
	}		

	/** Lista os dados para o Relatório de Mensal por Modelo.
	 * 
	 * @param dataIni
	 * 	<code>dataIni</code> data início
	 * 
	 * @param dataFim
	 * 	<code>dataFim</code> data fim
	 *  
	 * @return list
	 * 	<code>list</code> lista de dados do Relatório de Verificação.
	 * 
	 * @throws DaoException
	 */	
	public List listRelatorioMensalModelo(String mesReferencia, Long empresaId) throws DaoException{
		
		String sql = "SELECT li.descr_linha                              linha                                                     "+  
					"      , substr(gh.modelo,1,instr(gh.modelo,'0')-1)  modelo                                                    "+  
					"      , 0                                           qtMoto0Km                                                 "+  
					"      , sum(GET_CHASSI_DIFER_LOTE(gh.lote_id,substr(gh.modelo,1,instr(gh.modelo,'0')-1))) qtMotorPecaFaltante "+  
					"      , sum((select count(*)                                                                                  "+  
					"               from ym_sg_garantia_line glx                                                                   "+  
					"              where NVL(glx.peca_faltante,'N') = 'S'                                                          "+  
					"                and glx.garantia_id = gh.garantia_id)) qtSolicitacao                                          "+  
					"      , count(1)                                       totalMotoFaturada                                      "+             
					"      , 0                                              qtRevisao1                                             "+
					"      , 0                                              vlRevisao1                                             "+  
					"      , 0                                              qtRevisao2                                             "+  
					"      , 0                                              vlRevisao2                                             "+  
					"      , 0                                              qtRevisao3                                             "+  
					"      , 0                                              vlRevisao3                                             "+  
					"      , 0                                                                                                     "+  
					"   FROM ym_sg_lote               yl                                                                           "+  
					"      , ym_sg_garantia_header    gh                                                                           "+  
					"      , ym_sg_linha              li                                                                           "+  
					"  WHERE gh.organization_id = :p_empresa_id                                                                    "+
                    "    AND gh.lote_id         = yl.lote_id                                                                       "+  
					"    AND yl.linha_id        = li.linha_id                                                                      "+  
					"    AND yl.linha_id        = 1                                                                                "+  
					"    AND yl.status_lote_id  in (9,11)                                                                          "+  
					"    AND gh.status_mov_id   = 2                                                                                "+  
					"    AND yl.end_date        is null                                                                            "+  
					"    AND gh.end_date        is null                                                                            "+  
					"    AND trim(to_char(yl.fechamento_date,'MM/YYYY')) = to_char(to_date(:p_data_inicial, 'MM/YYYY'), 'MM/YYYY') "+  
					"  GROUP BY to_char(yl.fechamento_date,'MM/YYYY')                                                              "+  
					"         , li.descr_linha                                                                                     "+  
					"         , substr(gh.modelo,1,instr(gh.modelo,'0')-1)                                                         "+  
					" UNION ALL                                                                                                    "+  
					" SELECT yi.descr_linha                                                                   linha                "+  
					"      , substr(yc.modelo,1,instr(yc.modelo,'-')-1)                                       modelo               "+  
					"      , sum(decode(yr.numero_revisao,1,1,0))                                             qtMoto0Km            "+  
					"      , 0                                                                                qtMotorPecaFaltante  "+  
					"      , 0                                                                                qtPecaFaltante       "+  
					"      , 0                                                                                qtSolicitacao        "+  
					"      , 0                                                                                totalMotoFaturada    "+
					"      , sum(decode(yr.numero_revisao,1,1,0))                                             qtRevisao1           "+  
					"      , sum(decode(yr.numero_revisao,1,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0)) vlRevisao1           "+  
					"      , sum(decode(yr.numero_revisao,2,1,0))                                             qtRevisao2           "+  
					"      , sum(decode(yr.numero_revisao,2,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0)) vlRevisao2           "+  
					"      , sum(decode(yr.numero_revisao,3,1,0))                                             qtRevisao3           "+  
					"      , sum(decode(yr.numero_revisao,3,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0)) vlRevisao3           "+  
					"   FROM ym_sg_lote           yl                                                                               "+  
					"      , ym_sg_cupom          yc                                                                               "+  
					"      , ym_sg_revisao        yr                                                                               "+  
					"      , ym_sg_linha          yi                                                                               "+  
					"  WHERE yc.organization_id = :p_empresa_id                                                                    "+
                    "    AND yl.lote_id           = yc.lote_id                                                                     "+  
					"    AND yc.revisao_id        = yr.revisao_id                                                                  "+  
					"    AND yl.linha_id          = yi.linha_id                                                                    "+  
					"    AND yl.linha_id          = 1                                                                              "+  
					"    AND yl.status_lote_id    in (9,11)                                                                        "+  
					"    AND yc.status_mov_id     = 2                                                                              "+  
					"    AND yl.end_date          is null                                                                          "+  
					"    AND yc.end_date          is null                                                                          "+  
					"    AND to_char(yl.fechamento_date,'MM/YYYY') = to_char(to_date(:p_data_inicial, 'MM/YYYY'), 'MM/YYYY')       "+  
					"  GROUP BY to_char(yl.fechamento_date,'MM/YYYY')                                                              "+  
					"         , yi.descr_linha                                                                                     "+  
					"         , substr(yc.modelo,1,instr(yc.modelo,'-')-1)                                                         "+ 
					" UNION ALL                                                                                                    "+
					" SELECT li.descr_linha                             linha                                                      "+
					"      , substr(yf.chassi,1,instr(yf.chassi,'0')-1) modelo                                                     "+
					"      , 0                                          qtMoto0Km                                                  "+
					"      , 0                                          qtMotorPecaFaltante                                        "+
					"      , 0                                          qtPecaFaltante                                             "+
					"      , 0                                          qtSolicitacao                                              "+         
					"      , count(1)                                   totalMotoFaturada                                          "+
					"      , 0                                          qtRevisao1                                                 "+
					"      , 0                                          vlRevisao1                                                 "+
					"      , 0                                          qtRevisao2                                                 "+
					"      , 0                                          vlRevisao2                                                 "+
					"      , 0                                          qtRevisao3                                                 "+
					"      , 0                                          vlRevisao3                                                 "+
					"   FROM ym_sg_faturamento      yf                                                                             "+
					"      , ym_sg_linha            li                                                                             "+
					"  WHERE yf.organization_id = :p_empresa_id                                                                    "+
                    "    AND yf.linha_id        = li.linha_id                                                                      "+
					"    AND yf.linha_id        = 1                                                                                "+
					"    AND yf.end_date        is null                                                                            "+
					"    AND li.end_date        is null                                                                            "+
					"    AND trim(to_char(yf.data_nf,'MM/YYYY')) = to_char(to_date(:p_data_inicial, 'MM/YYYY'), 'MM/YYYY')         "+
					"  GROUP BY to_char(yf.data_nf,'MM/YYYY')                                                                      "+
					"         , li.descr_linha                                                                                     "+
					"         , substr(yf.chassi,1,instr(yf.chassi,'0')-1)                                                         "+
					"  ORDER BY modelo                                                                                             ";  
				                                                                                                           
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		query.setParameter("p_data_inicial" , mesReferencia);
        query.setParameter("p_empresa_id" , empresaId);
		
		List results = new ArrayList();		
		
		try {
			Iterator mensalModelo = query.list().iterator();
			
			while ( mensalModelo.hasNext() ) {
			
				RelatorioMensalModeloVO relatorioMensalModelo = new RelatorioMensalModeloVO();
				Object[] row = (Object[]) mensalModelo.next();
				relatorioMensalModelo.setLinha((String)row[0]);
				relatorioMensalModelo.setModelo((String)row[1]);
	
				Double qtMoto0Km = row[2] != null ? (Double.valueOf(row[2].toString())) : new Double(0);
				relatorioMensalModelo.setQtMoto0Km(qtMoto0Km);	
				
				Double qtMotorPF = row[3] != null ? (Double.valueOf(row[3].toString())) : new Double(0);
				relatorioMensalModelo.setQtMotorPecaFaltante(qtMotorPF);
				
				Double qtPecaFalt = row[4] != null ? (Double.valueOf(row[4].toString())) : new Double(0);
				relatorioMensalModelo.setQtPecaFaltante(qtPecaFalt);
				
				Double qtSolicitacao = row[5] != null ? (Double.valueOf(row[5].toString())) : new Double(0);
				relatorioMensalModelo.setQtSolicitacao(qtSolicitacao);
				
				Double totMotoFat = row[6] != null ? (Double.valueOf(row[6].toString())) : new Double(0);
				relatorioMensalModelo.setTotalMotoFaturada(totMotoFat);
				
				Double qtRev1 = row[7] != null ? (Double.valueOf(row[7].toString())) : new Double(0);
				relatorioMensalModelo.setQtRevisao1(qtRev1);
				
				Double vlRev1 = row[8] != null ? (Double.valueOf(row[8].toString())) : new Double(0);
				relatorioMensalModelo.setVlRevisao1(vlRev1);
				
				Double qtRev2 = row[9] != null ? (Double.valueOf(row[9].toString())) : new Double(0);
				relatorioMensalModelo.setQtRevisao2(qtRev2);
				
				Double vlRev2 = row[10] != null ? (Double.valueOf(row[10].toString())) : new Double(0);
				relatorioMensalModelo.setVlRevisao2(vlRev2);
				
				Double qtRev3 = row[11] != null ? (Double.valueOf(row[11].toString())) : new Double(0);
				relatorioMensalModelo.setQtRevisao3(qtRev3);
				
				Double vlRev3 = row[12] != null ? (Double.valueOf(row[12].toString())) : new Double(0);
				relatorioMensalModelo.setVlRevisao3(vlRev3);	
				
				results.add(relatorioMensalModelo);
			}		
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		
		return results;		

	}
	
	/** Lista os dados para o Relatório de Mensal por Modelo.
	 * 
	 * @param dataIni
	 * 	<code>dataIni</code> data início
	 * 
	 * @param dataFim
	 * 	<code>dataFim</code> data fim
	 * 
	 * @param linha
	 * 	<code>linha</code> linha
	 * 
	 * @param empresa
	 * 	<code>empresa</code> empresa
	 *  
	 * @param concessionaria
	 * 	<code>concessionaria</code> concessionaria
	 * 
	 * @param estado
	 * 	<code>estado</code> estado
	 * 
	 * @param modelo
	 * 	<code>modelo</code> modelo
	 * 
	 * @param chassiIni
	 * 	<code>chassiIni</code> chassiIni
	 * 
	 * @param chassiFim
	 * 	<code>chassiFim</code> chassiFim
	 *  
	 * @return list
	 * 	<code>list</code> lista de dados do Relatório de Verificação.
	 * 
	 * @throws DaoException
	 */	
	public List listRelatorioMalaDireta( Date   dataIni
							           , Date   dataFim
							           , Long   empresa
							           , Long   concessionaria
							           , Long   linha
							           , String estado
							           , String modelo
							           , String chassiIni
							           , String chassiFim) throws DaoException {
		
		String whereEmpresa = empresa.intValue() != 0 ? " AND ye.organization_id = nvl(:p_empresa,ye.organization_id) " : "";
		String whereConcessionaria = concessionaria.intValue() != 0 ? " AND lt.concessionaria_id = nvl(:p_concessionaria, lt.concessionaria_id) " : "";

		String sql = "SELECT cl.nome          cliente                                              " +                
					"      , cl.endereco      endereco                                             " +
					"      , cl.bairro        bairro                                               " +
					"      , cl.cidade        cidade                                               " +
					"      , cl.estado        estado                                               " +
					"      , substr(lpad(trim(TO_CHAR(cl.cep,'99999999')),8,'0'),1,5)||'-'||       " +  
					"        substr(lpad(trim(TO_CHAR(cl.cep,'99999999')),8,'0'),6,3) cep          " +
					"      , ft.chassi_completo  chassi                                            " +
					"      , co.razao_social     concessionaria                                    " +
					"   FROM ym_sg_cliente            cl                                           " +                                
					"      , ym_sg_cupom              cp                                           " +                                
					"      , ym_sg_faturamento        ft                                           " +                                
					"      , ym_sg_lote               lt                                           " +                                
					"      , ym_sg_concessionaria     co                                           " +                                
					"      , ym_sg_revisao            rv                                           " +                                  
					"      , ym_sg_empresa            ye                                           " +                           
					"  WHERE cl.cliente_id         = cp.cliente_id                                 " +                                
					"    AND cp.lote_id            = lt.lote_id                                    " +                                
					"    AND lt.concessionaria_id  = co.concessionaria_id                          " +                                
					"    AND cp.modelo             = ft.chassi                                     " +                                
					"    AND cp.revisao_id         = rv.revisao_id                                 " +
					"    AND ft.organization_id    = ye.organization_id                            " +                                   
					"    AND rv.numero_revisao     = 1                                             " +                                
					"    AND lt.status_lote_id     in (11,9)                                       " +                                
					"    AND cp.status_mov_id      = 2                                             " +                                
					"    AND lt.end_date           is null                                         " +                                
					"    AND rv.end_date           is null                                         " +                                
					"    AND ft.end_date           is null                                         " +                                
					"    AND lt.end_date           is null                                         " +                                
					"    AND rv.end_date           is null                                         " +                                
					"    AND ft.end_date           is null                                         " +
					whereEmpresa +
					whereConcessionaria +
					"    AND co.estado             =  nvl(:p_estado, co.estado)             	   " +
					"    AND lt.linha_id           =  :p_linha                                     " +
					"    AND substr(ft.chassi,1,instr(ft.chassi,'-')-1) =                          " +
					"        nvl(upper(:p_modelo),substr(ft.chassi,1,instr(ft.chassi,'-')-1))      " +
					"    AND ft.chassi     >= nvl(:p_chassi_ini, ft.chassi)          			   " +                                
					"    AND ft.chassi     <= nvl(:p_chassi_fim, ft.chassi)          			   " +					
					"    AND trunc(cp.data_venda) >= to_date(:p_data_inicial, 'dd/mm/yyyy')        " +                                
					"    AND trunc(cp.data_venda) <  to_date(:p_data_final, 'dd/mm/yyyy')          " +
					"    ORDER BY cliente                                                          " ;
	
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		if ( empresa.intValue() != 0  )
			query.setParameter("p_empresa" , empresa);
		
		if ( concessionaria.intValue() != 0  )
			query.setParameter("p_concessionaria" , concessionaria);
		
		query.setParameter("p_estado" , estado.toUpperCase());
		query.setParameter("p_linha" , linha);
		query.setParameter("p_modelo" , modelo);
		query.setParameter("p_chassi_ini" , chassiIni.toUpperCase());
		query.setParameter("p_chassi_fim" , chassiFim.toUpperCase());
		query.setParameter("p_data_inicial" , DateUtils.applyMask(dataIni));
		query.setParameter("p_data_final" , DateUtils.applyMask(dataFim));
		
		List results = new ArrayList();		
		try {
			
			Iterator malaDireta = query.list().iterator();
			
			while ( malaDireta.hasNext() ) {
			
				RelatorioMalaDiretaVO relatorioMalaDireta = new RelatorioMalaDiretaVO();
				Object[] row = (Object[]) malaDireta.next();
				relatorioMalaDireta.setCliente((String)row[0]);
				relatorioMalaDireta.setEndereco((String)row[1]);
				relatorioMalaDireta.setBairro((String)row[2]);
				relatorioMalaDireta.setCidade((String)row[3]);
				relatorioMalaDireta.setEstado((String)row[4]);
				relatorioMalaDireta.setCep((String)row[5]);
				relatorioMalaDireta.setChassi((String)row[6]);				
				relatorioMalaDireta.setConcessionaria((String)row[7]);
	
				results.add(relatorioMalaDireta);
			}		
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		
		return results;		
	}
	
	/** Lista os dados para o Relatório de Protocolo de Documentos da Garantia.
	 * 
	 * @param Integer lote
	 * 	<code>Integer</code> lote
	 * 
	 * @param concessionaria
	 * 	<code>Long</code> concessionaria
	 * 
	 * @return list
	 * 	<code>list</code> lista de dados do Relatório de Verificação.
	 * 
	 * @throws DaoException
	 */	
	public List listRelatorioSolicitacaoGarantiaNota( Integer lote, Long concessionaria) throws DaoException {

		String sql = "SELECT distinct lt.lote_id lote                                   		 " +
					"       , nf.num_nf          numeroNf                                        " +
					"       , nf.serie_nf        serieNf                                         " +
					"       , nf.data_nf         dataNf                                          " +
					"       , (nvl(nf.valor_nf_sv,0)+ nvl(nf.valor_nf_pc,0)) valorNf             " +
					"       , nf.emissor         emitente                                        " +
					"       , nf.destinatario    destinatario                                    " +
					"       , tn.descr_tipo_nf   tipo                                            " +
					"       , nf.nf_compl_num    numeroCp                                        " +
					"       , nf.nf_compl_serie  serieCp                                         " +
					"       , nf.nf_compl_data   dataCp                                          " +
					"       , nvl(nf.nf_compl_valor_sv,0) + nvl(nf.nf_compl_valor_pc,0) valorCp  " +
					"       , co.razao_social    nomeConc                                        " +
					"       , co.endereco        enderecoConc                                    " +
					"       , co.cidade          cidadeConc                                      " +
					"       , co.cep             cepConc                                         " +
					"       , co.estado          estadoConc                                      " +
					"       , sl.descr_reduz     statusLote                                      " +
					"       , co.cnpj            cnpjConc                                        " +
					"       , ye.org_code|| ' – '||ye.nome_empresa empresa                       " +
					"       , co.malote          malote											 " +
					"       , co.codigo_concessionaria  codigo                                   " +
					"       , nvl(nf.valor_icms_nf,0) valorIcmsNf             					 " +
					"    FROM ym_sg_nota             nf                                          " +
					"       , ym_sg_concessionaria   co                                          " +
					"       , ym_sg_garantia_nota    gn                                          " +
					"       , ym_sg_garantia_header  gh                                          " +
					"       , ym_sg_lote             lt                                          " +
					"       , ym_sg_status_lote      sl                                          " +
					"       , ym_sg_empresa          ye                                          " +
					"       , ym_sg_tipo_nf          tn                                          " +
					"   WHERE nf.nota_id            = gn.nota_id	                             " +
					"     AND nf.tipo_nf_id 		= tn.tipo_nf_id 							 " +
					"     AND gn.garantia_id        = gh.garantia_id                             " +
					"     AND gh.lote_id            = lt.lote_id                                 " +
					"     AND lt.status_lote_id     = sl.status_lote_id                          " +
					"     AND lt.concessionaria_id  = co.concessionaria_id                       " +
					"     AND gh.organization_id	= ye.organization_id                         " +
					"	  AND gh.end_date is null  												 " +
					"	  AND nf.end_date is null 												 " +
					"     AND lt.lote_id            = :p_lote                                    " +
					"     AND lt.concessionaria_id  = :p_concessionaria                          " +			
					"     AND lt.status_lote_id in (" + 
							StatusLote.STATUS_AGUARDANDO_RECEB_DOC +
					"," +
							StatusLote.STATUS_AGUARDANDO_ADIANTAMENTO_RECEB_DOC +
					"," +
							StatusLote.STATUS_ADIANTAMENTO_PAGO_AGUARDANDO_RECEB_DOC +
					")" +
					" ORDER BY nf.num_nf";
		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		query.setParameter("p_lote", lote);
		query.setParameter("p_concessionaria", concessionaria);
		//query.setParameter("p_status_lote", StatusLote.STATUS_AGUARDANDO_RECEB_DOC);
		
		List results = new ArrayList();	
		try {
			Iterator solicitacaoGarantia = query.list().iterator();
			
			while ( solicitacaoGarantia.hasNext() ) {
			
				RelatorioSolicitacaoGarantiaNotaVO relatorioSolicitacaoGarantia = new RelatorioSolicitacaoGarantiaNotaVO();
				Object[] row = (Object[]) solicitacaoGarantia.next();
				relatorioSolicitacaoGarantia.setLote((BigDecimal)row[0]);
				relatorioSolicitacaoGarantia.setNumeroNf((BigDecimal)row[1]);
				relatorioSolicitacaoGarantia.setSerieNf((String)row[2]);
				relatorioSolicitacaoGarantia.setDataNf((Date)row[3]);
				relatorioSolicitacaoGarantia.setValorNf((BigDecimal)row[4]);
				relatorioSolicitacaoGarantia.setEmitente((String)row[5]);
				relatorioSolicitacaoGarantia.setDestinatario((String)row[6]);
				relatorioSolicitacaoGarantia.setDescrTipo((String)row[7]);
				relatorioSolicitacaoGarantia.setNumeroCp((BigDecimal)row[8]);				
				relatorioSolicitacaoGarantia.setSerieCp((String)row[9]);
				relatorioSolicitacaoGarantia.setDataCp((Date)row[10]);
				relatorioSolicitacaoGarantia.setValorCp((BigDecimal)row[11]);
				relatorioSolicitacaoGarantia.setNomeConc((String)row[12]);
				relatorioSolicitacaoGarantia.setEnderecoConc((String)row[13]);
				relatorioSolicitacaoGarantia.setCidadeConc((String)row[14]);
				relatorioSolicitacaoGarantia.setCepConc((BigDecimal)row[15]);
				relatorioSolicitacaoGarantia.setEstadoConc((String)row[16]);
				relatorioSolicitacaoGarantia.setStatusLote((String)row[17]);
				relatorioSolicitacaoGarantia.setCnpjConc((BigDecimal)row[18]);
				relatorioSolicitacaoGarantia.setEmpresa((String)row[19]);
				relatorioSolicitacaoGarantia.setMalote((String)row[20]);
				relatorioSolicitacaoGarantia.setCodigoConcessionaria((String)row[21]);
				relatorioSolicitacaoGarantia.setValorIcmsNf((BigDecimal)row[22]);
				
				results.add(relatorioSolicitacaoGarantia);
			}		
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		
		return results;		
	}	
	
	/** Lista os dados para o Relatório de Mensal por Modelo.
	 * 
	 * @param lote
	 * 	<code>lote</code> lote
	 * 
	 * @param concessionaria
	 * 	<code>concessionaria</code> concessionaria
	 * 
	 * @return list
	 * 	<code>list</code> lista de dados do Relatório de Verificação.
	 * 
	 * @throws DaoException
	 */	
	public List listRelatorioSolicitacaoGarantia( Integer lote) throws DaoException {

		String sql = "SELECT gh.garantia_id   numeroSG  " + 
					"      , gh.modelo        chassi    " + 
					"   FROM ym_sg_garantia_header   gh " +
					"        , ym_sg_lote            lt " + 
					"  WHERE lt.lote_id = gh.lote_id    " +
					"  AND   gh.lote_id = :p_lote       " +
					"  AND   gh.end_date is null        " +
					"  AND   lt.end_date is null        " +
					"  AND   lt.status_lote_id in (" + 
							StatusLote.STATUS_AGUARDANDO_RECEB_DOC +
					"," +
							StatusLote.STATUS_AGUARDANDO_ADIANTAMENTO_RECEB_DOC +
					"," +
							StatusLote.STATUS_ADIANTAMENTO_PAGO_AGUARDANDO_RECEB_DOC +
					")" +
					" ORDER BY gh.modelo,gh.garantia_id ";
					//"  AND   lt.status_lote_id = :p_status_lote" ; 			
		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		query.setParameter("p_lote", lote);
		//query.setParameter("p_status_lote", StatusLote.STATUS_AGUARDANDO_RECEB_DOC);
		
		List results = new ArrayList();		
		try {
			Iterator solicitacaoGarantia = query.list().iterator();
			
			while ( solicitacaoGarantia.hasNext() ) {
			
				RelatorioSolicitacaoGarantiaVO relatorioSolicitacaoGarantia = new RelatorioSolicitacaoGarantiaVO();
				Object[] row = (Object[]) solicitacaoGarantia.next();
				relatorioSolicitacaoGarantia.setNumeroSG((BigDecimal)row[0]);
				relatorioSolicitacaoGarantia.setChassi((String)row[1]);			
				
				results.add(relatorioSolicitacaoGarantia);
			}		
		} catch ( Exception exp ) {
		
			throw new DaoException(exp);
		
		}  finally {
			session.close();
		}
		
		//System.out.println("Results:" + results.size());
		
		return results;		
	}		

	/** Lista os dados para o Relatório de Mensal por Modelo.
	 * 
	 * @param lote
	 * 	<code>lote</code> lote
	 * 
	 * @param concessionaria
	 * 	<code>concessionaria</code> concessionaria
	 * 
	 * @return list
	 * 	<code>list</code> lista de dados do Relatório de Verificação.
	 * 
	 * @throws DaoException
	 */	
	public List listRelatorioSolicitacaoGarantiaPeca( Integer lote, Long concessionaria) throws DaoException {

		String sql = "SELECT lt.lote_id                            lote         " +
                     "     , sl.descr_reduz                        statusLote   " +
                     "     , gl.quantidade                         quantidade   " +
                     "     , yi.item_code                          codItem      " + 
                     "     , yi.descricao                          descItem     " + 
                     "     , sum(round((gl.vl_preco_sugerido * gl.quantidade) * 0.1,2))  valorItem    " + 
                     "     , co.razao_social                       nomeConc     " + 
                     "     , co.endereco                           enderecoConc " + 
                     "     , co.cidade                             cidadeConc   " + 
                     "     , co.estado                             estadoConc   " + 
                     "     , co.cep                                cepConc      " + 
                     "     , co.cnpj                               cnpjConc     " + 
                     "     , gl.envia_peca                         enviaPeca    " + 
                     "     , gh.modelo                             chassi       " + 
                     "     , ye.org_code|| ' - '||ye.nome_empresa  empresa      " + 
                     "FROM  ym_sg_concessionaria    co           " + 
                     "   , ym_sg_garantia_header    gh           " + 
                     "   , ym_sg_garantia_line      gl           " + 
                     "   , ym_sg_lote               lt           " + 
                     "   , ym_sg_status_lote        sl           " + 
                     "   , ym_sg_empresa            ye           " + 
                     "   , ( select yix.item_id                  " + 
                     "            , max(yix.item_code) item_code " +
                     "            , max(yix.descricao) descricao " + 
                     "       from ym_sg_item yix                 " + 
                     "       group by yix.item_id ) yi           " + 
                     "WHERE lt.status_lote_id      = sl.status_lote_id    " +
                     "  AND lt.concessionaria_id   = co.concessionaria_id " + 
                     "  AND lt.lote_id             = gh.lote_id           " + 
                     "  AND gh.garantia_id         = gl.garantia_id       " + 
                     "  AND gl.item_id             = yi.item_id           " + 
                     "  AND gh.organization_id     = ye.organization_id   " + 
                     "  AND gl.envia_peca          = 'S'				  " +			
                     "  AND gh.end_date is null							  " +
                     "  AND lt.end_date is null							  " +
                     "	AND gl.end_date is null							  " +
                     "  AND lt.lote_id             = :p_lote              " + 
                     "  AND lt.concessionaria_id   = :p_concessionaria    " +
                     "  AND   lt.status_lote_id in (" + 
							StatusLote.STATUS_AGUARDANDO_RECEB_DOC +
					 "," +
							StatusLote.STATUS_AGUARDANDO_ADIANTAMENTO_RECEB_DOC +
					 "," +
							StatusLote.STATUS_ADIANTAMENTO_PAGO_AGUARDANDO_RECEB_DOC +
					 ") " +
                   	 "GROUP BY lt.lote_id      " +
                     "       , sl.descr_reduz  " + 
                     "       , gl.quantidade   " + 
                     "       , yi.item_code    " + 
                     "       , yi.descricao    " +  
                     "       , co.razao_social " + 
                     "       , co.endereco     " +
                     "       , co.cidade       " +
                     "       , co.estado       " + 
                     "       , co.cep          " + 
                     "       , co.cnpj         " + 
                     "       , gl.envia_peca   " + 
                     "       , gh.modelo       " + 
                     "       , ye.org_code || ' - ' || ye.nome_empresa   " +
                     "ORDER BY sl.descr_reduz, yi.item_code";  
				
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		query.setParameter("p_lote", lote);
		query.setParameter("p_concessionaria", concessionaria);
		//query.setParameter("p_status_lote", StatusLote.STATUS_AGUARDANDO_RECEB_DOC);
		
		List results = new ArrayList();	
		try {
			Iterator solicitacaoGarantia = query.list().iterator();
			
			while ( solicitacaoGarantia.hasNext() ) {
			
				RelatorioSolicitacaoGarantiaPecaVO relatorioSolicitacaoGarantia = new RelatorioSolicitacaoGarantiaPecaVO();
				Object[] row = (Object[]) solicitacaoGarantia.next();
				relatorioSolicitacaoGarantia.setLote((BigDecimal)row[0]);
				relatorioSolicitacaoGarantia.setStatusLote((String)row[1]);
				relatorioSolicitacaoGarantia.setQuantidade((BigDecimal)row[2]);
				relatorioSolicitacaoGarantia.setCodItem((String)row[3]);
				relatorioSolicitacaoGarantia.setDescItem((String)row[4]);
				relatorioSolicitacaoGarantia.setValorItem((BigDecimal)row[5]);
				relatorioSolicitacaoGarantia.setNomeConc((String)row[6]);
				relatorioSolicitacaoGarantia.setEnderecoConc((String)row[7]);
				relatorioSolicitacaoGarantia.setCidadeConc((String)row[8]);
				relatorioSolicitacaoGarantia.setEstadoConc((String)row[9]);
				relatorioSolicitacaoGarantia.setCepConc((BigDecimal)row[10]);
				relatorioSolicitacaoGarantia.setCnpjConc((BigDecimal)row[11]);
				relatorioSolicitacaoGarantia.setEnviaPeca((String)row[12]);
				relatorioSolicitacaoGarantia.setChassi((String)row[13]);
				relatorioSolicitacaoGarantia.setEmpresa((String)row[14]);
				
				results.add(relatorioSolicitacaoGarantia);
			}		
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		return results;		
	}		
	
	/** Lista os dados para o Relatório de Processamento de Revisoes - Fase1.
	 * 
	 * @param lote
	 * 	<code>lote</code> lote
	 * 
	 * @return list
	 * 	<code>list</code> lista de dados do Relatório de Processamento de Revisoes - Fase1.
	 * 
	 * @throws DaoException
	 */	
	public List listRelatorioProcRevisoesFase1(Long empresa, Integer lote) throws DaoException{
		
		String whereEmpresa = empresa.intValue() != 0 ? " AND ep.organization_id = nvl(:p_empresa,ep.organization_id) " : "";
		
		String sql = "SELECT li.descr_linha	      linha                                        " +                                           
					"      , lt.lote_id           lote                                         " + 
					"      , rv.numero_revisao    nrRevisao                                    " +   
					"      , rv.descricao         revisao                                      " + 
					"      , cp.modelo            chassi                                       " + 
					"      , cp.data_revisao      dtRevisao                                    " + 
					"      , nvl(cp.quilometragem,nvl(cp.dias_uso,cp.horas_uso)) quilometragem " + 
					"      , ep.org_code||' - '||ep.nome_empresa  nomeEmpresa                  " +                                             
					"      , substr(lpad(co.cnpj, 14, '0'),1,8)   cnpjConc                     " +  
					"      , co.razao_social      razaoSocialConc                              " + 
					"      , co.endereco          enderecoConc                                 " + 
					"      , co.cep               cepConc                                      " + 
					"      , co.cidade            cidadeConc                                   " + 
					"      , co.estado            estadoConc                                   " + 
					"   FROM ym_sg_lote             lt                                         " + 
					"      , ym_sg_cupom            cp                                         " +      
					"      , ym_sg_revisao          rv                                         " + 
					"      , ym_sg_faturamento      ft                                         " + 
					"      , ym_sg_empresa          ep                                         " + 
					"      , ym_sg_concessionaria   co                                         " + 
					"      , ym_sg_linha            li                                         " + 
					"  WHERE lt.lote_id              = cp.lote_id                              " + 
					"    AND cp.modelo               = ft.chassi                               " + 
					"    AND ft.organization_id      = ep.organization_id                      " + 
					"    AND lt.concessionaria_id    = co.concessionaria_id                    " + 
					"    AND li.linha_id             = lt.linha_id                             " + 
					"    AND cp.revisao_id           = rv.revisao_id                           " + 
					//"    AND lt.status_lote_id       IN (2, 4)                               " +  Alterado em 09/11/2007
					"    AND lt.status_lote_id       = 1                                       " +
					//"    AND cp.status_mov_id        = 2                                     " +  Alterado em 09/11/2007             
					"    AND cp.status_mov_id        = 0                                       " +
					whereEmpresa +
					"    AND lt.lote_id              = :p_lote	                               " + 
					"    AND lt.end_date is null                                               " + 
					"    AND cp.end_date is null                                               " +   
					"    AND ft.end_date          is null                                      " +
					"  ORDER BY rv.numero_revisao                                              " + 
					"         , rv.descricao                                                   " ;                                                                           
		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		if ( empresa.intValue() != 0  )
			query.setParameter("p_empresa", empresa);
		
		query.setParameter("p_lote", lote);
		
		List results = new ArrayList();	
		try {
			Iterator procRevisoesFase1 = query.list().iterator();
			
			while ( procRevisoesFase1.hasNext() ) {
			
				RelatorioProcRevisoesFase1VO relatorioProcRevisoesFase1 = new RelatorioProcRevisoesFase1VO();
				Object[] row = (Object[]) procRevisoesFase1.next();
				relatorioProcRevisoesFase1.setLinha((String)row[0]);
				relatorioProcRevisoesFase1.setLote(Integer.valueOf(row[1].toString()));
				relatorioProcRevisoesFase1.setNrRevisao(Integer.valueOf(row[2].toString()));
				relatorioProcRevisoesFase1.setRevisao((String)row[3]);
				relatorioProcRevisoesFase1.setChassi((String)row[4]);
				relatorioProcRevisoesFase1.setDtRevisao((Date)row[5]);
				relatorioProcRevisoesFase1.setQuilometragem((BigDecimal)row[6]);
				relatorioProcRevisoesFase1.setNomeEmpresa((String)row[7]);
				relatorioProcRevisoesFase1.setCnpjConc((String)row[8]);
				relatorioProcRevisoesFase1.setRazaoSocialConc((String)row[9]);
				relatorioProcRevisoesFase1.setEnderecoConc((String)row[10]);
				relatorioProcRevisoesFase1.setCepConc((BigDecimal)row[11]);
				relatorioProcRevisoesFase1.setCidadeConc((String)row[12]);
				relatorioProcRevisoesFase1.setEstadoConc((String)row[13]);
				
				results.add(relatorioProcRevisoesFase1);
			}		
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		
		return results;	
	}
	
	/** Lista os dados para o Relatório de Processamento de Revisoes - Fase2.
	 * 
	 * @param lote
	 * 	<code>lote</code> lote
	 * 
	 * @return list
	 * 	<code>list</code> lista de dados do Relatório de Processamento de Revisoes - Fase2.
	 * 
	 * @throws DaoException
	 */	
	public List listRelatorioProcRevisoesFase2(Long empresa, Integer lote) throws DaoException{
		
		String whereEmpresa = empresa.intValue() != 0 ? " AND ep.organization_id = nvl(:p_empresa, ep.organization_id) " : "";
		
		String sql = "SELECT li.descr_linha                                 linha              " + 					
					"      , lt.lote_id                                     lote	           " +
					"      , rv.numero_revisao                              nrRevisao          " +    
					"      , rv.descricao                                   revisao            " +
					"      , cp.modelo                                      chassi             " +
					"      , cp.data_revisao                                dtRevisao          " +
					"      , round(nvl(cp.vl_revisao, 0) + nvl(cp.vl_bonificacao,0),2)  vlRevisao " +
					"      , nvl(cp.quilometragem,nvl(cp.dias_uso,cp.horas_uso)) quilometragem " +
					"      , ep.nome_empresa                                nomeEmpresa        " +  
					"      , substr(lpad(co.cnpj, 14, '0'),1,8)             cnpjConc           " +
					"      , co.razao_social                                razaoSocialConc    " +
					"      , co.endereco                                    enderecoConc       " +
					"      , co.cep                                         cepConc            " +
					"      , co.cidade                                      cidadeConc         " +
					"      , co.estado                                      estadoConc         " +
					"      , li.linha_id                                    linhaId	           " +
					"   FROM ym_sg_lote            lt                                          " +
					"      , ym_sg_cupom           cp                                          " +
					"      , ym_sg_revisao         rv                                          " +
					"      , ym_sg_faturamento	   ft                                          " +
					"      , ym_sg_empresa         ep                                          " +
					"      , ym_sg_concessionaria  co                                          " +
					"      , ym_sg_linha           li                                          " +
					"  WHERE lt.lote_id           = cp.lote_id                                 " +
					"    AND cp.modelo            = ft.chassi                                  " +
					"    AND ft.organization_id   = ep.organization_id                         " +
					"    AND lt.concessionaria_id = co.concessionaria_id                       " +
					"    AND li.linha_id          = lt.linha_id                                " +
					"    AND cp.revisao_id        = rv.revisao_id                              " +
					//"    AND cp.status_mov_id     != 19 									   " + //Status periódico
					whereEmpresa +
					"    AND lt.lote_id           = trim(:p_lote)                              " +
					"    AND lt.end_date          is null                                      " +
					"    AND cp.end_date          is null                                      " +
					"	 AND ft.start_date <= :data 								   		   " +
			         "   AND ( (ft.end_date is null) OR (ft.end_date >= :data) )	   		   " +
					"    ORDER BY ep.nome_empresa, rv.numero_revisao,  cp.modelo               " ;
		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		if ( empresa.intValue() != 0  )
			query.setParameter("p_empresa", empresa);
		
		query.setParameter("p_lote", lote);
		query.setParameter("data"  , new Date());
		
		List results = new ArrayList();	
		try {
			Iterator procRevisoesFase2 = query.list().iterator();
			
			while ( procRevisoesFase2.hasNext() ) {
			
				RelatorioProcRevisoesFase2VO relatorioProcRevisoesFase2 = new RelatorioProcRevisoesFase2VO();
				Object[] row = (Object[]) procRevisoesFase2.next();
				relatorioProcRevisoesFase2.setLinha((String)row[0]);				
				relatorioProcRevisoesFase2.setLote((BigDecimal)row[1]);
				relatorioProcRevisoesFase2.setNrRevisao((BigDecimal)row[2]);
				relatorioProcRevisoesFase2.setRevisao((String)row[3]);
				relatorioProcRevisoesFase2.setChassi((String)row[4]);
				relatorioProcRevisoesFase2.setDtRevisao((Date)row[5]);
				relatorioProcRevisoesFase2.setVlRevisao(new Double(((BigDecimal)row[6]).doubleValue()));
				relatorioProcRevisoesFase2.setQuilometragem((BigDecimal)row[7]);
				relatorioProcRevisoesFase2.setNomeEmpresa(((String)row[8]).toUpperCase());
				relatorioProcRevisoesFase2.setCnpjConc((String)row[9]);
				relatorioProcRevisoesFase2.setRazaoSocialConc((String)row[10]);
				relatorioProcRevisoesFase2.setEnderecoConc((String)row[11]);
				relatorioProcRevisoesFase2.setCepConc((BigDecimal)row[12]);
				relatorioProcRevisoesFase2.setCidadeConc((String)row[13]);
				relatorioProcRevisoesFase2.setEstadoConc((String)row[14]);
				relatorioProcRevisoesFase2.setLinhaId((BigDecimal)row[15]);
				
				results.add(relatorioProcRevisoesFase2);
			}		
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		
		return results;	
	}	
	
	/** Lista os dados para o Relatório Consulta de Notas Fiscais.  
	 * 
	 * @param dataIni
	 * 	<code>dataIni</code> Data Início
	 * 
	 * @param dataFim
	 * 	<code>dataFim</code> Data Fim
	 * 
	 * @param lote
	 * 	<code>lote</code> lote	 
	 * 
	 * @param tipoLote
	 * 	<code>tipoLote</code> tipo do lote 
	 * 
	 * @param concessionaria
	 * 	<code>concessionaria</code> concessionaria
	 *  
	 * @return list
	 * 	<code>list</code> lista de dados do Relatório Consulta de Notas Fiscais. 
	 * 
	 * @throws DaoException
	 */			
	public List listRelatorioConsultaNfEmp( Date    dataIni
							              , Date    dataFim
							              , Long    linha
							              , Integer lote
							              , Long    tipoLote
							              , Long    concessionaria
							              , Long    empresa) throws DaoException{
		
		String whereEmpresa = empresa.intValue() != 0 ? " AND ye.organization_id = nvl(:p_empresa,ye.organization_id) " : "";
		String whereConcessionaria = concessionaria.intValue() != 0 ? " AND lt.concessionaria_id = nvl(:p_concessionaria,lt.concessionaria_id) " : "";
		String whereTipoLote = tipoLote.intValue() != 0 ? " AND lt.tipo_lote_id = nvl(:p_tipo_lote,lt.tipo_lote_id) ": "";
		
		String sql = "SELECT li.descr_linha                      linha                                " +
					"      , ye.org_code||' - '||ye.nome_empresa empresa                              " +
					"      , ye.organization_id                  empresaId                            " +
					"  FROM ym_sg_lote             lt                                                 " +
					"      , ym_sg_status_lote      sl                                                " +
					"      , ym_sg_garantia_header  gh                                                " +
					"      , ym_sg_garantia_nota    gn                                                " +
					"      , ym_sg_nota             nf                                                " +
					"      , ym_sg_concessionaria   co                                                " +
					"      , ym_sg_linha            li                                                " +
					"      , ym_sg_tipo_lote        tl                                                " +
					"      , ym_sg_tipo_nf          tn                                                " +
					"      , ym_sg_empresa          ye                                                " +
					"  WHERE lt.status_lote_id          = sl.status_lote_id                           " +
					"    AND lt.lote_id                 = gh.lote_id                                  " +
					"    AND nf.tipo_nf_id              = tn.tipo_nf_id                               " +
					"    AND lt.status_lote_id          in (9,11)                                     " +
					"    AND gh.status_mov_id           = 2                                           " +
					"    AND gh.garantia_id             = gn.garantia_id                              " +
					"    AND gn.nota_id                 = nf.nota_id                                  " +
					"    AND lt.concessionaria_id       = co.concessionaria_id                        " +
					"    AND lt.linha_id                = li.linha_id                                 " +
					"    AND lt.tipo_lote_id            = tl.tipo_lote_id                             " +
					"    AND nf.destinatario            = ye.org_code                                 " +
					"    AND lt.end_date                is null                                       " +
					"    AND gh.end_date                is null                                       " +
					"    AND gn.end_date                is null                                       " +
					"    AND nf.end_date                is NULL                                       " +
					whereEmpresa +
					whereConcessionaria +
					whereTipoLote +
					"    AND lt.linha_id                = nvl(:p_linha, lt.linha_id)                  " +
					"    AND lt.lote_id                 = nvl(trim(:p_lote),lt.lote_id)               " +
					"    AND trunc(lt.fechamento_date) >= to_date(:p_data_inicio, 'DD/MM/YYYY')       " +
					"    AND trunc(lt.fechamento_date) <  (to_date(:p_data_fim, 'DD/MM/YYYY')+1)      " +
					" UNION ALL                                                                       " +
					" SELECT li.descr_linha                      linha                                " +
					"      , ye.org_code||' - '||ye.nome_empresa empresa                              " +
					"      , ye.organization_id                  empresaId                            " +
					"   FROM ym_sg_lote             lt                                                " +
					"      , ym_sg_status_lote      sl                                                " +
					"      , ym_sg_cupom            yc                                                " +
					"      , ym_sg_cupom_nota       cn                                                " +
					"      , ym_sg_nota             nf                                                " +
					"      , ym_sg_concessionaria   co                                                " +
					"      , ym_sg_linha            li                                                " +
					"      , ym_sg_tipo_lote        tl                                                " +
					"      , ym_sg_tipo_nf          tn                                                " +
					"      , ym_sg_empresa          ye                                                " +
					"  WHERE lt.status_lote_id          = sl.status_lote_id                           " +
					"    AND lt.lote_id                 = yc.lote_id                                  " +
					"    AND nf.tipo_nf_id              = tn.tipo_nf_id                               " +
					"    AND lt.status_lote_id          in (9,11)                                     " +
					"    AND yc.status_mov_id           = 2                                           " +
					"    AND yc.cupom_id                = cn.cupom_id                                 " +
					"    AND cn.nota_id                 = nf.nota_id                                  " +
					"    AND lt.concessionaria_id       = co.concessionaria_id                        " +
					"    AND lt.linha_id                = li.linha_id                                 " +
					"    AND lt.tipo_lote_id            = tl.tipo_lote_id                             " +
					"    AND nf.destinatario            = ye.org_code                                 " +
					"    AND lt.end_date                is null                                       " +
					"    AND yc.end_date                is null                                       " +
					"    AND cn.end_date                is null                                       " +
					"    AND nf.end_date                is NULL                                       " +
					whereEmpresa +
					whereConcessionaria +
					whereTipoLote +
					"    AND lt.linha_id                = nvl(:p_linha, lt.linha_id)                  " +
					"    AND lt.lote_id                 = nvl(trim(:p_lote),lt.lote_id)               " +
					"    AND trunc(lt.fechamento_date) >= to_date(:p_data_inicio, 'DD/MM/YYYY')       " +
					"    AND trunc(lt.fechamento_date) <  (to_date(:p_data_fim, 'DD/MM/YYYY')+1)      " +
					"  ORDER BY empresa                                                               " ;					
		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		query.setParameter("p_data_inicio", DateUtils.applyMask(dataIni));
		query.setParameter("p_data_fim", DateUtils.applyMask(dataFim));
		query.setParameter("p_linha", linha);
		query.setParameter("p_lote", lote);
		
		if ( tipoLote.intValue() != 0  )
			query.setParameter("p_tipo_lote", tipoLote);
		
		if ( concessionaria.intValue() != 0  )
			query.setParameter("p_concessionaria", concessionaria);
		
		if ( empresa.intValue() != 0  )
			query.setParameter("p_empresa", empresa);
		
		List results = new ArrayList();
		try {
			Iterator consultaNF = query.list().iterator();
			
			while ( consultaNF.hasNext() ) {
			
				RelatorioConsultaNfEmpVO relatorioConsultaNF = new RelatorioConsultaNfEmpVO();
				Object[] row = (Object[]) consultaNF.next();
				relatorioConsultaNF.setLinha((String)row[0]);
				relatorioConsultaNF.setEmpresa((String)row[1]);
				relatorioConsultaNF.setEmpresaId(Long.valueOf(row[2].toString()));
	                   						
				results.add(relatorioConsultaNF);
			}		
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		
		return results;	
	}

	/** Lista os dados para o Relatório Consulta de Notas Fiscais.  
	 * 
	 * @param dataIni
	 * 	<code>dataIni</code> Data Início
	 * 
	 * @param dataFim
	 * 	<code>dataFim</code> Data Fim
	 * 
	 * @param lote
	 * 	<code>lote</code> lote	 
	 * 
	 * @param tipoLote
	 * 	<code>tipoLote</code> tipo do lote 
	 * 
	 * @param concessionaria
	 * 	<code>concessionaria</code> concessionaria
	 *  
	 * @return list
	 * 	<code>list</code> lista de dados do Relatório Consulta de Notas Fiscais. 
	 * 
	 * @throws DaoException
	 */			
	public List listRelatorioConsultaNfConc( Date    dataIni
							               , Date    dataFim
							               , Long    linha
							               , Integer lote
							               , Long    tipoLote
							               , Long    concessionaria
							               , Long    empresa) throws DaoException{
		
		String whereEmpresa = empresa.intValue() != 0 ? " AND ye.organization_id = nvl(:p_empresa,ye.organization_id) " : "";
		String whereConcessionaria = concessionaria.intValue() != 0 ? " AND lt.concessionaria_id = nvl(:p_concessionaria,lt.concessionaria_id) " : "";
		String whereTipoLote = tipoLote.intValue() != 0 ? " AND lt.tipo_lote_id = nvl(:p_tipo_lote,lt.tipo_lote_id) ": "";
		
		String sql = "SELECT co.razao_social        concessionaria                                    " +
					"      , co.endereco            endereco                                          " +
					"      , co.cep                 cep                                               " +
					"      , co.cidade              cidade                                            " +
					"      , co.estado              estado                                            " +
					"      , co.cnpj                cnpj                                              " +
					"      , co.concessionaria_id   concessionariaId                                  " +
					"   FROM ym_sg_lote             lt                                                " +
					"      , ym_sg_status_lote      sl                                                " +
					"      , ym_sg_garantia_header  gh                                                " +
					"      , ym_sg_garantia_nota    gn                                                " +
					"      , ym_sg_nota             nf                                                " +
					"      , ym_sg_concessionaria   co                                                " +
					"      , ym_sg_linha            li                                                " +
					"      , ym_sg_tipo_lote        tl                                                " +
					"      , ym_sg_tipo_nf          tn                                                " +
					"      , ym_sg_empresa          ye                                                " +
					"  WHERE lt.status_lote_id          = sl.status_lote_id                           " +
					"    AND lt.lote_id                 = gh.lote_id                                  " +
					"    AND nf.tipo_nf_id              = tn.tipo_nf_id                               " +
					"    AND lt.status_lote_id          in (9,11)                                     " +
					"    AND gh.status_mov_id           = 2                                           " +
					"    AND gh.garantia_id             = gn.garantia_id                              " +
					"    AND gn.nota_id                 = nf.nota_id                                  " +
					"    AND lt.concessionaria_id       = co.concessionaria_id                        " +
					"    AND lt.linha_id                = li.linha_id                                 " +
					"    AND lt.tipo_lote_id            = tl.tipo_lote_id                             " +
					"    AND nf.destinatario            = ye.org_code                                 " +
					"    AND lt.end_date                is null                                       " +
					"    AND gh.end_date                is null                                       " +
					"    AND gn.end_date                is null                                       " +
					"    AND nf.end_date                is NULL                                       " +
					whereEmpresa +
					whereConcessionaria +
					whereTipoLote +
					"    AND lt.linha_id                = nvl(:p_linha, lt.linha_id)                  " +
					"    AND lt.lote_id                 = nvl(trim(:p_lote),lt.lote_id)               " +
					"    AND trunc(lt.fechamento_date) >= to_date(:p_data_inicio, 'DD/MM/YYYY')       " +
					"    AND trunc(lt.fechamento_date) <  (to_date(:p_data_fim, 'DD/MM/YYYY')+1)      " +
					" UNION ALL                                                                       " +
					" SELECT co.razao_social        concessionaria                                    " +
					"      , co.endereco            endereco                                          " +
					"      , co.cep                 cep                                               " +
					"      , co.cidade              cidade                                            " +
					"      , co.estado              estado                                            " +
					"      , co.cnpj                cnpj                                              " +
					"      , co.concessionaria_id   concessionariaId                                  " +
					"   FROM ym_sg_lote             lt                                                " +
					"      , ym_sg_status_lote      sl                                                " +
					"      , ym_sg_cupom            yc                                                " +
					"      , ym_sg_cupom_nota       cn                                                " +
					"      , ym_sg_nota             nf                                                " +
					"      , ym_sg_concessionaria   co                                                " +
					"      , ym_sg_linha            li                                                " +
					"      , ym_sg_tipo_lote        tl                                                " +
					"      , ym_sg_tipo_nf          tn                                                " +
					"      , ym_sg_empresa          ye                                                " +
					"  WHERE lt.status_lote_id          = sl.status_lote_id                           " +
					"    AND lt.lote_id                 = yc.lote_id                                  " +
					"    AND nf.tipo_nf_id              = tn.tipo_nf_id                               " +
					"    AND lt.status_lote_id          in (9,11)                                     " +
					"    AND yc.status_mov_id           = 2                                           " +
					"    AND yc.cupom_id                = cn.cupom_id                                 " +
					"    AND cn.nota_id                 = nf.nota_id                                  " +
					"    AND lt.concessionaria_id       = co.concessionaria_id                        " +
					"    AND lt.linha_id                = li.linha_id                                 " +
					"    AND lt.tipo_lote_id            = tl.tipo_lote_id                             " +
					"    AND nf.destinatario            = ye.org_code                                 " +
					"    AND lt.end_date                is null                                       " +
					"    AND yc.end_date                is null                                       " +
					"    AND cn.end_date                is null                                       " +
					"    AND nf.end_date                is NULL                                       " +
					whereEmpresa +
					whereConcessionaria +
					whereTipoLote +
					"    AND lt.linha_id                = nvl(:p_linha, lt.linha_id)                  " +
					"    AND lt.lote_id                 = nvl(trim(:p_lote),lt.lote_id)               " +
					"    AND trunc(lt.fechamento_date) >= to_date(:p_data_inicio, 'DD/MM/YYYY')       " +
					"    AND trunc(lt.fechamento_date) <  (to_date(:p_data_fim, 'DD/MM/YYYY')+1)      " +
					"    ORDER BY concessionaria                                                      " ;
		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		query.setParameter("p_data_inicio", DateUtils.applyMask(dataIni));
		query.setParameter("p_data_fim", DateUtils.applyMask(dataFim));
		query.setParameter("p_linha", linha);
		query.setParameter("p_lote", lote);
		
		if ( tipoLote.intValue() != 0  )
			query.setParameter("p_tipo_lote", tipoLote);
		
		if ( concessionaria.intValue() != 0  )
			query.setParameter("p_concessionaria", concessionaria);
		
		if ( empresa.intValue() != 0  )
			query.setParameter("p_empresa", empresa);
		
		List results = new ArrayList();
		try {
			Iterator consultaNF = query.list().iterator();
			
			while ( consultaNF.hasNext() ) {
			
				RelatorioConsultaNfConcVO relatorioConsultaNF = new RelatorioConsultaNfConcVO();
				Object[] row = (Object[]) consultaNF.next();
				relatorioConsultaNF.setConcessionaria((String)row[0]);
				relatorioConsultaNF.setEndereco((String)row[1]);
				relatorioConsultaNF.setCep((String)row[2].toString());
				relatorioConsultaNF.setCidade((String)row[3]);
				relatorioConsultaNF.setEstado((String)row[4]);
				relatorioConsultaNF.setCnpj(Long.valueOf(row[5].toString()));
				relatorioConsultaNF.setConcessionariaId(Long.valueOf(row[6].toString()));
	                   						
				results.add(relatorioConsultaNF);
			}		
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		
		return results;	
	}

	/** Lista os dados para o Relatório Consulta de Notas Fiscais.  
	 * 
	 * @param dataIni
	 * 	<code>dataIni</code> Data Início
	 * 
	 * @param dataFim
	 * 	<code>dataFim</code> Data Fim
	 * 
	 * @param lote
	 * 	<code>lote</code> lote	 
	 * 
	 * @param tipoLote
	 * 	<code>tipoLote</code> tipo do lote 
	 * 
	 * @param concessionaria
	 * 	<code>concessionaria</code> concessionaria
	 *  
	 * @return list
	 * 	<code>list</code> lista de dados do Relatório Consulta de Notas Fiscais. 
	 * 
	 * @throws DaoException
	 */			
	public List listRelatorioConsultaNfLote( Date    dataIni
							               , Date    dataFim
							               , Long    linha
							               , Integer lote
							               , Long    tipoLote
							               , Long    concessionaria
							               , Long    empresa) throws DaoException{
		
		String whereEmpresa = empresa.intValue() != 0 ? " AND ye.organization_id = nvl(:p_empresa,ye.organization_id) " : "";
		String whereConcessionaria = concessionaria.intValue() != 0 ? " AND lt.concessionaria_id = nvl(:p_concessionaria,lt.concessionaria_id) " : "";
		String whereTipoLote = tipoLote.intValue() != 0 ? " AND lt.tipo_lote_id = nvl(:p_tipo_lote,lt.tipo_lote_id) ": "";

		String sql = "SELECT lt.lote_id             lote                                              " +
					"      , tl.descricao           tipoLote                                          " +
					"      , sl.descr_reduz         descStatus                                        " +
					"      , lt.lote_date           dtAbertura                                        " +
					"      , lt.liberacao_date      dtLiberacao                                       " +
					"      , lt.fechamento_date     dtFechamento                                      " +
					"   FROM ym_sg_lote             lt                                                " +
					"      , ym_sg_status_lote      sl                                                " +
					"      , ym_sg_garantia_header  gh                                                " +
					"      , ym_sg_garantia_nota    gn                                                " +
					"      , ym_sg_nota             nf                                                " +
					"      , ym_sg_concessionaria   co                                                " +
					"      , ym_sg_linha            li                                                " +
					"      , ym_sg_tipo_lote        tl                                                " +
					"      , ym_sg_tipo_nf          tn                                                " +
					"      , ym_sg_empresa          ye                                                " +
					"  WHERE lt.status_lote_id          = sl.status_lote_id                           " +
					"    AND lt.lote_id                 = gh.lote_id                                  " +
					"    AND nf.tipo_nf_id              = tn.tipo_nf_id                               " +
					"    AND lt.status_lote_id          in (9,11)                                     " +
					"    AND gh.status_mov_id           = 2                                           " +
					"    AND gh.garantia_id             = gn.garantia_id                              " +
					"    AND gn.nota_id                 = nf.nota_id                                  " +
					"    AND lt.concessionaria_id       = co.concessionaria_id                        " +
					"    AND lt.linha_id                = li.linha_id                                 " +
					"    AND lt.tipo_lote_id            = tl.tipo_lote_id                             " +
					"    AND nf.destinatario            = ye.org_code                                 " +
					"    AND lt.end_date                is null                                       " +
					"    AND gh.end_date                is null                                       " +
					"    AND gn.end_date                is null                                       " +
					"    AND nf.end_date                is NULL                                       " +
					whereEmpresa +
					whereConcessionaria +
					whereTipoLote +
					"    AND lt.linha_id                = nvl(:p_linha, lt.linha_id)                  " +
					"    AND lt.lote_id                 = nvl(trim(:p_lote),lt.lote_id)               " +
					"    AND trunc(lt.fechamento_date) >= to_date(:p_data_inicio, 'DD/MM/YYYY')       " +
					"    AND trunc(lt.fechamento_date) <  (to_date(:p_data_fim, 'DD/MM/YYYY')+1)      " +
					" UNION ALL                                                                       " +
					" SELECT lt.lote_id             lote                                              " +
					"      , tl.descricao           tipoLote                                          " +
					"      , sl.descr_reduz         descStatus                                        " +
					"      , lt.lote_date           dtAbertura                                        " +
					"      , lt.liberacao_date      dtLiberacao                                       " +
					"      , lt.fechamento_date     dtFechamento                                      " +
					"   FROM ym_sg_lote             lt                                                " +
					"      , ym_sg_status_lote      sl                                                " +
					"      , ym_sg_cupom            yc                                                " +
					"      , ym_sg_cupom_nota       cn                                                " +
					"      , ym_sg_nota             nf                                                " +
					"      , ym_sg_concessionaria   co                                                " +
					"      , ym_sg_linha            li                                                " +
					"      , ym_sg_tipo_lote        tl                                                " +
					"      , ym_sg_tipo_nf          tn                                                " +
					"      , ym_sg_empresa          ye                                                " +
					"  WHERE lt.status_lote_id          = sl.status_lote_id                           " +
					"    AND lt.lote_id                 = yc.lote_id                                  " +
					"    AND nf.tipo_nf_id              = tn.tipo_nf_id                               " +
					"    AND lt.status_lote_id          in (9,11)                                     " +
					"    AND yc.status_mov_id           = 2                                           " +
					"    AND yc.cupom_id                = cn.cupom_id                                 " +
					"    AND cn.nota_id                 = nf.nota_id                                  " +
					"    AND lt.concessionaria_id       = co.concessionaria_id                        " +
					"    AND lt.linha_id                = li.linha_id                                 " +
					"    AND lt.tipo_lote_id            = tl.tipo_lote_id                             " +
					"    AND nf.destinatario            = ye.org_code                                 " +
					"    AND lt.end_date                is null                                       " +
					"    AND yc.end_date                is null                                       " +
					"    AND cn.end_date                is null                                       " +
					"    AND nf.end_date                is NULL                                       " +
					whereEmpresa +
					whereConcessionaria +
					whereTipoLote +
					"    AND lt.linha_id                = nvl(:p_linha, lt.linha_id)                  " +
					"    AND lt.lote_id                 = nvl(trim(:p_lote),lt.lote_id)               " +
					"    AND trunc(lt.fechamento_date) >= to_date(:p_data_inicio, 'DD/MM/YYYY')       " +
					"    AND trunc(lt.fechamento_date) <  (to_date(:p_data_fim, 'DD/MM/YYYY')+1)      " +
					"  ORDER BY lote                                                                  " ;
		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		query.setParameter("p_data_inicio", DateUtils.applyMask(dataIni));
		query.setParameter("p_data_fim", DateUtils.applyMask(dataFim));
		query.setParameter("p_linha", linha);
		query.setParameter("p_lote", lote);
		
		if ( tipoLote.intValue() != 0  )
			query.setParameter("p_tipo_lote", tipoLote);
		
		if ( concessionaria.intValue() != 0  )
			query.setParameter("p_concessionaria", concessionaria);
		
		if ( empresa.intValue() != 0  )
			query.setParameter("p_empresa", empresa);
	
		List results = new ArrayList();
		try {
			Iterator consultaNF = query.list().iterator();
			
			while ( consultaNF.hasNext() ) {
			
				RelatorioConsultaNfLoteVO relatorioConsultaNF = new RelatorioConsultaNfLoteVO();
				Object[] row = (Object[]) consultaNF.next();
				relatorioConsultaNF.setLote(Integer.valueOf(row[0].toString()));
				relatorioConsultaNF.setTipoLote((String)row[1]);
				relatorioConsultaNF.setStatus((String)row[2]);
				relatorioConsultaNF.setDtAbertura((Date)row[3]);
				relatorioConsultaNF.setDtLiberacao((Date)row[4]);
				relatorioConsultaNF.setDtFechamento((Date)row[5]);
	                   						
				results.add(relatorioConsultaNF);
			}
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		
		return results;	
	}
	
	/** Lista os dados para o Relatório Consulta de Notas Fiscais.  
	 * 
	 * @param dataIni
	 * 	<code>dataIni</code> Data Início
	 * 
	 * @param dataFim
	 * 	<code>dataFim</code> Data Fim
	 * 
	 * @param lote
	 * 	<code>lote</code> lote	 
	 * 
	 * @param tipoLote
	 * 	<code>tipoLote</code> tipo do lote 
	 * 
	 * @param concessionaria
	 * 	<code>concessionaria</code> concessionaria
	 *  
	 * @return list
	 * 	<code>list</code> lista de dados do Relatório Consulta de Notas Fiscais. 
	 * 
	 * @throws DaoException
	 */			
	/*public List listRelatorioConsultaNfNota( Date    dataIni
							               , Date    dataFim
							               , Long    linha
							               , Integer lote
							               , Long    tipoLote
							               , Long    concessionaria
							               , Long    empresa) throws DaoException{
		String whereEmpresa = empresa.intValue() != 0 ? " AND ye.organization_id = nvl(:p_empresa,ye.organization_id) " : "";
		String whereConcessionaria = concessionaria.intValue() != 0 ? " AND lt.concessionaria_id = nvl(:p_concessionaria,lt.concessionaria_id) " : "";
		String whereTipoLote = tipoLote.intValue() != 0 ? " AND lt.tipo_lote_id = nvl(:p_tipo_lote,lt.tipo_lote_id) ": "";

		String sql = "SELECT nf.num_nf              notaFiscal                                        " +
					"      , nf.serie_nf            serie                                             " +
					"      , nf.data_nf             dtNotaFiscal                                      " +
					"      , nvl(nf.valor_nf_sv,0) + nvl(nf.valor_nf_pc,0)  valor                     " +
					"      , tn.descr_tipo_nf	    tipoNF                                            " +
					"   FROM ym_sg_lote             lt                                                " +
					"      , ym_sg_status_lote      sl                                                " +
					"      , ym_sg_garantia_header  gh                                                " +
					"      , ym_sg_garantia_nota    gn                                                " +
					"      , ym_sg_nota             nf                                                " +
					"      , ym_sg_concessionaria   co                                                " +
					"      , ym_sg_linha            li                                                " +
					"      , ym_sg_tipo_lote        tl                                                " +
					"      , ym_sg_tipo_nf          tn                                                " +
					"      , ym_sg_empresa          ye                                                " +
					"  WHERE lt.status_lote_id          = sl.status_lote_id                           " +
					"    AND lt.lote_id                 = gh.lote_id                                  " +
					"    AND nf.tipo_nf_id              = tn.tipo_nf_id                               " +
					"    AND lt.status_lote_id          in (9,11)                                     " +
					"    AND gh.status_mov_id           = 2                                           " +
					"    AND gh.garantia_id             = gn.garantia_id                              " +
					"    AND gn.nota_id                 = nf.nota_id                                  " +
					"    AND lt.concessionaria_id       = co.concessionaria_id                        " +
					"    AND lt.linha_id                = li.linha_id                                 " +
					"    AND lt.tipo_lote_id            = tl.tipo_lote_id                             " +
					"    AND nf.destinatario            = ye.org_code                                 " +
					"    AND lt.end_date                is null                                       " +
					"    AND gh.end_date                is null                                       " +
					"    AND gn.end_date                is null                                       " +
					"    AND nf.end_date                is NULL                                       " +
					whereEmpresa +
					whereConcessionaria +
					whereTipoLote +
					"    AND lt.linha_id                = nvl(:p_linha, lt.linha_id)                  " +
					"    AND lt.lote_id                 = nvl(trim(:p_lote),lt.lote_id)               " +
					"    AND trunc(lt.fechamento_date) >= to_date(:p_data_inicio, 'DD/MM/YYYY')       " +
					"    AND trunc(lt.fechamento_date) <  (to_date(:p_data_fim, 'DD/MM/YYYY')+1)      " +
					" UNION ALL                                                                       " +
					" SELECT nf.num_nf              notaFiscal                                        " +
					"      , nf.serie_nf            serie                                             " +
					"      , nf.data_nf             dtNotaFiscal                                      " +
					"      , nvl(nf.valor_nf_sv,0) + nvl(nf.valor_nf_pc,0)  valor                     " +
					"      , tn.descr_tipo_nf	    tipoNF                                            " +
					"   FROM ym_sg_lote             lt                                                " +
					"      , ym_sg_status_lote      sl                                                " +
					"      , ym_sg_cupom            yc                                                " +
					"      , ym_sg_cupom_nota       cn                                                " +
					"      , ym_sg_nota             nf                                                " +
					"      , ym_sg_concessionaria   co                                                " +
					"      , ym_sg_linha            li                                                " +
					"      , ym_sg_tipo_lote        tl                                                " +
					"      , ym_sg_tipo_nf          tn                                                " +
					"      , ym_sg_empresa          ye                                                " +
					"  WHERE lt.status_lote_id          = sl.status_lote_id                           " +
					"    AND lt.lote_id                 = yc.lote_id                                  " +
					"    AND nf.tipo_nf_id              = tn.tipo_nf_id                               " +
					"    AND lt.status_lote_id          in (9,11)                                     " +
					"    AND yc.status_mov_id           = 2                                           " +
					"    AND yc.cupom_id                = cn.cupom_id                                 " +
					"    AND cn.nota_id                 = nf.nota_id                                  " +
					"    AND lt.concessionaria_id       = co.concessionaria_id                        " +
					"    AND lt.linha_id                = li.linha_id                                 " +
					"    AND lt.tipo_lote_id            = tl.tipo_lote_id                             " +
					"    AND nf.destinatario            = ye.org_code                                 " +
					"    AND lt.end_date                is null                                       " +
					"    AND yc.end_date                is null                                       " +
					"    AND cn.end_date                is null                                       " +
					"    AND nf.end_date                is NULL                                       " +
					whereEmpresa +
					whereConcessionaria +
					whereTipoLote +
					"    AND lt.linha_id                = nvl(:p_linha, lt.linha_id)                  " +
					"    AND lt.lote_id                 = nvl(trim(:p_lote),lt.lote_id)               " +
					"    AND trunc(lt.fechamento_date) >= to_date(:p_data_inicio, 'DD/MM/YYYY')       " +
					"    AND trunc(lt.fechamento_date) <  (to_date(:p_data_fim, 'DD/MM/YYYY')+1)      " +
					"  ORDER BY notaFiscal                                                            " ;
		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		query.setParameter("p_data_inicio", DateUtils.applyMask(dataIni));
		query.setParameter("p_data_fim", DateUtils.applyMask(dataFim));
		query.setParameter("p_linha", linha);
		query.setParameter("p_lote", lote);
		
		if ( tipoLote.intValue() != 0  )
			query.setParameter("p_tipo_lote", tipoLote);
		
		if ( concessionaria.intValue() != 0  )
			query.setParameter("p_concessionaria", concessionaria);
		
		if ( empresa.intValue() != 0  )
			query.setParameter("p_empresa", empresa);
		
		List results = new ArrayList();		
		Iterator consultaNF = query.list().iterator();
		
		while ( consultaNF.hasNext() ) {
		
			RelatorioConsultaNfNotaVO relatorioConsultaNF = new RelatorioConsultaNfNotaVO();
			Object[] row = (Object[]) consultaNF.next();
			relatorioConsultaNF.setNotaFiscal(Long.valueOf(row[0].toString()));
			relatorioConsultaNF.setSerie((String)row[1]);
			relatorioConsultaNF.setDtNotaFiscal((Date)row[2]);
			relatorioConsultaNF.setValor((BigDecimal)row[3]);
			relatorioConsultaNF.setTipoNF((String)row[4]);
                   						
			results.add(relatorioConsultaNF);
		}		
		
		session.close();
		
		return results;	
	} */
	
	
	/** Lista os dados para o Relatório Consulta de Notas Fiscais.  
	 * 
	 * @param dataIni
	 * 	<code>dataIni</code> Data Início
	 * 
	 * @param dataFim
	 * 	<code>dataFim</code> Data Fim
	 * 
	 * @param lote
	 * 	<code>lote</code> lote	 
	 * 
	 * @param tipoLote
	 * 	<code>tipoLote</code> tipo do lote 
	 * 
	 * @param concessionaria
	 * 	<code>concessionaria</code> concessionaria
	 *  
	 * @return list
	 * 	<code>list</code> lista de dados do Relatório Consulta de Notas Fiscais. 
	 * 
	 * @throws DaoException
	 */			
	public List listRelatorioConsultaNfNota( Date    dataIni
							               , Date    dataFim
							               , Long    linha
							               , Integer lote
							               , Long    tipoLote
							               , Long    concessionaria
							               , Long    empresa) throws DaoException{
		
		//System.out.println("-->Valores - dataIni:" + dataIni + " - dataFim:" + dataFim + " - linha:" + linha + " - lote:" + lote + " - tipoLote:" + tipoLote + " - concessionaria:" + concessionaria + " - empresa:" + empresa);
		
		String whereEmpresa = empresa.intValue() != 0 ? " AND ye.organization_id = nvl(:p_empresa,ye.organization_id) " : "";
		String whereConcessionaria = concessionaria.intValue() != 0 ? " AND lt.concessionaria_id = nvl(:p_concessionaria,lt.concessionaria_id) " : "";
		//String whereTipoLote = tipoLote.intValue() != 0 ? " AND lt.tipo_lote_id = nvl(:p_tipo_lote,lt.tipo_lote_id) ": "";
		String whereLote = lote.intValue() != 0 ? " AND lt.lote_id = nvl(trim(:p_lote),lt.lote_id) ": "";

		String sql = 	"SELECT loteId																	  " +									   
						"       ,  notaFiscal															  " +                                         
						"		,  descLinha															  " +										   
						"		,  dt_abertura															  " +																									   
						"		,  dt_liberacao															  " +									   
						"		,  dt_fechamento														  " +									   
						"		,  tipo_lote															  " +										   
						"		,  serie																  " +                                              
						"		,  dtNotaFiscal															  " +                                       
						"		,  valor																  " +                      
						"		,  tipoNF																  " +            								   
						"		,  concessionaria														  " +									   
						"		,  endereco																  " +										   
						"		,  cidade																  " +
						"		,  cep																	  " +
						"		,  estado																  " +											   
						"		,  cnpj																	  " +											   
						"		,  empresa																  " +   
						"FROM (																			  " +
						"SELECT  DISTINCT lt.lote_id    loteId											  " +
						"	   , nf.num_nf              notaFiscal                                        " +
						"	   , li.descr_linha         descLinha										  " +
						"	   , lt.lote_date           dt_abertura										  " +
						"	   , lt.liberacao_date      dt_liberacao									  " +
						"	   , lt.fechamento_date     dt_fechamento									  " +
						"	   , tl.descricao           tipo_lote										  " +
						"      , nf.serie_nf            serie                                             " +
						"      , nf.data_nf             dtNotaFiscal                                      " +
						"      , nvl(nf.valor_nf_sv,0) + nvl(nf.valor_nf_pc,0)  valor                     " +
						"      , tn.descr_tipo_nf	    tipoNF            								  " +
						" 	   , co.razao_social        concessionaria									  " +
						"	   , co.endereco            endereco										  " +
						"	   , co.cidade              cidade											  " +
						"	   , co.cep 				cep												  " +
						"	   , co.estado				estado											  " +
						"	   , co.cnpj                cnpj											  " +
						"	   , ye.org_code ||' - '|| ye.nome_empresa   empresa                          " +
						"   FROM ym_sg_lote             lt                                                " +
						"      , ym_sg_garantia_header  gh                                                " +
						"      , ym_sg_garantia_nota    gn                                                " +
						"      , ym_sg_nota             nf                                                " +
						"      , ym_sg_concessionaria   co                                                " +
						"      , ym_sg_linha            li                                                " +
						"      , ym_sg_tipo_lote        tl                                                " +
						"      , ym_sg_tipo_nf          tn                                                " +
						"      , ym_sg_empresa          ye                                                " +
						"  WHERE lt.lote_id                 = gh.lote_id                                  " +
						"    AND nf.tipo_nf_id              = tn.tipo_nf_id                               " +
						"    AND lt.status_lote_id          in (1,6,7,9,10,11,13,15,20,21)                " +
						//"    AND gh.status_mov_id           in (13,14,15,16,20,21)                      " +
						"    AND gh.garantia_id             = gn.garantia_id                              " +
						"    AND gn.nota_id                 = nf.nota_id                                  " +
						"    AND lt.concessionaria_id       = co.concessionaria_id                        " +
						"    AND lt.linha_id                = li.linha_id                                 " +
						"    AND lt.tipo_lote_id            = tl.tipo_lote_id                             " +
						"    AND nf.destinatario            = ye.org_code                                 " +
						"    AND lt.end_date                is null                                       " +
						"    AND gh.end_date                is null                                       " +
						"    AND gn.end_date                is null                                       " +
						"    AND nf.end_date                is NULL                                       " +
						whereEmpresa +
						whereConcessionaria +
						//whereTipoLote +
						whereLote +
						"    AND lt.linha_id                 = nvl(:p_linha, lt.linha_id)                 " +
						"    AND (trunc(lt.lote_date) >= :p_data_inicio      						  	  " +
						"    AND trunc(lt.lote_date)  <= :p_data_fim )   							      " +
						" UNION ALL                                                                       " +
						" SELECT DISTINCT lt.lote_id    loteId											  " +
						"	   , nf.num_nf              notaFiscal                                        " +
						"	   , li.descr_linha         descLinha										  " +
						"	   , lt.lote_date           dt_abertura										  " +
						"	   , lt.liberacao_date      dt_liberacao									  " +
						"	   , lt.fechamento_date     dt_fechamento									  " +
						"	   , tl.descricao           tipo_lote										  " +
						"      , nf.serie_nf            serie                                             " +
						"      , nf.data_nf             dtNotaFiscal                                      " +
						"      , nvl(nf.valor_nf_sv,0) + nvl(nf.valor_nf_pc,0)  valor                     " +
						"      , tn.descr_tipo_nf	    tipoNF            								  " +
						" 	   , co.razao_social        concessionaria									  " +
						"	   , co.endereco            endereco										  " +
						"	   , co.cidade              cidade											  " +
						"	   , co.cep 				cep												  " +
						"	   , co.estado				estado											  " +
						"	   , co.cnpj                cnpj											  " +
						"	   , ye.org_code ||' - '|| ye.nome_empresa   empresa                          " +
						"   FROM ym_sg_lote             lt                                                " +
						"      , ym_sg_cupom            yc                                                " +
						"      , ym_sg_cupom_nota       cn                                                " +
						"      , ym_sg_nota             nf                                                " +
						"      , ym_sg_concessionaria   co                                                " +
						"      , ym_sg_linha            li                                                " +
						"      , ym_sg_tipo_lote        tl                                                " +
						"      , ym_sg_tipo_nf          tn                                                " +
						"      , ym_sg_empresa          ye                                                " +
						"  WHERE lt.lote_id                 = yc.lote_id                                  " +
						"    AND nf.tipo_nf_id              = tn.tipo_nf_id                               " +
						"    AND lt.status_lote_id          in (1,6,7,9,10,11,13,15,20,21)                " +
						//"    AND yc.status_mov_id           in (13,14,15,16,20,21)                        " +
						"    AND yc.cupom_id                = cn.cupom_id                                 " +
						"    AND cn.nota_id                 = nf.nota_id                                  " +
						"    AND lt.concessionaria_id       = co.concessionaria_id                        " +
						"    AND lt.linha_id                = li.linha_id                                 " +
						"    AND lt.tipo_lote_id            = tl.tipo_lote_id                             " +
						"    AND nf.destinatario            = ye.org_code                                 " +
						"    AND lt.end_date                is null                                       " +
						"    AND yc.end_date                is null                                       " +
						"    AND cn.end_date                is null                                       " +
						"    AND nf.end_date                is NULL                                       " +
						whereEmpresa +
						whereConcessionaria +
						//whereTipoLote +
						whereLote +
						"    AND lt.linha_id                = nvl(:p_linha, lt.linha_id)                  " +
						"    AND (trunc(lt.lote_date) >= :p_data_inicio						      		  " +
						"    AND trunc(lt.lote_date) <=  :p_data_fim  )    						  		  " +
						")																				  " +
						"  ORDER BY tipo_lote, loteId ,notaFiscal										  " ;
		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		//query.setParameter("p_data_inicio", DateUtils.applyMask(dataIni));
		//query.setParameter("p_data_fim", DateUtils.applyMask(dataFim));
		
		query.setParameter("p_data_inicio", dataIni);
		query.setParameter("p_data_fim", dataFim);
		query.setParameter("p_linha", linha);		
		
		//if ( tipoLote.intValue() != 0  )
			//query.setParameter("p_tipo_lote", tipoLote);
		
		if ( concessionaria.intValue() != 0  )
			query.setParameter("p_concessionaria", concessionaria);
		
		if ( empresa.intValue() != 0  )
			query.setParameter("p_empresa", empresa);
		
		if ( lote.intValue() != 0  )
			query.setParameter("p_lote", lote);
		
		List results = new ArrayList();	
		try {
			Iterator consultaNF = query.list().iterator();
			
			while ( consultaNF.hasNext() ) {
			
				RelatorioConsultaNfNotaVO relatorioConsultaNF = new RelatorioConsultaNfNotaVO();
				Object[] row = (Object[]) consultaNF.next();
				relatorioConsultaNF.setLoteId((BigDecimal)row[0]);
				relatorioConsultaNF.setNotaFiscal(Long.valueOf(row[1].toString()));
				relatorioConsultaNF.setDescLinha((String)row[2]);
				relatorioConsultaNF.setDataAberturaLote((Date)row[3]);
				relatorioConsultaNF.setDataLiberacaoLote((Date)row[4]);
				relatorioConsultaNF.setDataFechamentoLote((Date)row[5]);
				relatorioConsultaNF.setTipoLote((String)row[6]);
				relatorioConsultaNF.setSerie((String)row[7]);			
				relatorioConsultaNF.setDtNotaFiscal((Date)row[8]);
				relatorioConsultaNF.setValor((BigDecimal)row[9]);
				relatorioConsultaNF.setTipoNF((String)row[10]);
				relatorioConsultaNF.setConcessionaria((String)row[11]);
				relatorioConsultaNF.setEndereco((String)row[12]);
				relatorioConsultaNF.setCidade((String)row[13]);
				relatorioConsultaNF.setCep((BigDecimal)row[14]);
				relatorioConsultaNF.setEstado((String)row[15]);
				relatorioConsultaNF.setCnpj((BigDecimal)row[16]);
				relatorioConsultaNF.setEmpresa((String)row[17]);
	                   						
				results.add(relatorioConsultaNF);
			}		
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		return results;	
	}
	
	/** Lista os dados para o Relatório Consulta de Lotes. 
	 * 
	 * @param dataIni
	 * 	<code>dataIni</code> Data Início
	 * 
	 * @param dataFim
	 * 	<code>dataFim</code> Data Fim
	 * 
	 * @param lote
	 * 	<code>lote</code> lote	 
	 * 
	 * @param tipoLote
	 * 	<code>tipoLote</code> tipo do lote 
	 * 
	 * @param concessionaria
	 * 	<code>concessionaria</code> concessionaria
	 *  
	 * @return list
	 * 	<code>list</code> lista de dados do Relatório Consulta de Lotes. 
	 * 
	 * @throws DaoException
	 */			
	public List listRelatorioConsultaConc( Date    dataIni
						                 , Date    dataFim
						                 , Long    linha
						                 , Integer lote
						                 , Long    tipoLote
						                 , Long    concessionaria) throws DaoException{
		
		String whereLote = lote.intValue() != 0 ? " AND lt.lote_id = :p_lote " : "";
		
		String sql = "SELECT DISTINCT  co.concessionaria_id  concessionariaId                   " +
				    "      , co.razao_social                 concessionaria                     " + 
					"      , co.endereco                     endereco                           " + 
					"      , co.cep                          cep                                " + 
					"      , co.cidade                       cidade                             " + 
					"      , co.estado                       estado                             " + 
					"      , co.cnpj                         cnpj                               " + 
					"   FROM ym_sg_lote		        lt                                          " + 
					"      , ym_sg_concessionaria	co                                          " + 
					"      , ym_sg_linha            li                                          " + 
					"  WHERE lt.concessionaria_id  = co.concessionaria_id                       " + 
					"    AND lt.linha_id           = li.linha_id                                " + 
					"    AND lt.linha_id           = :p_linha                                   " + 
					"    AND lt.concessionaria_id  = nvl(:p_concessionaria,lt.concessionaria_id)" +
					whereLote + 
					"    AND lt.tipo_lote_id       = nvl(:p_tipo_lote, lt.tipo_lote_id)         " + 
					"    AND trunc(lt.created_date) >= to_date(:p_data_inicio, 'DD/MM/YYYY')    " + 
					"    AND trunc(lt.created_date) < (to_date(:p_data_fim, 'DD/MM/YYYY')+1)    " +
					"    ORDER BY co.razao_social                                               " ;

		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		query.setParameter("p_data_inicio", DateUtils.applyMask(dataIni));
		query.setParameter("p_data_fim", DateUtils.applyMask(dataFim));
		query.setParameter("p_linha", linha);
		
		if ( lote.intValue() != 0 )
			query.setParameter("p_lote", lote);	
		
		query.setParameter("p_tipo_lote", tipoLote); //(tipoLote == new Long (0)) ? null : tipoLote);
		query.setParameter("p_concessionaria", concessionaria); //(concessionaria == new Long (0)) ? null : concessionaria);
		
		List results = new ArrayList();
		try {
			Iterator consultaConc = query.list().iterator();
			
			while ( consultaConc.hasNext() ) {
			
				RelatorioConsultaLoteConcVO relatorioConsultaConc = new RelatorioConsultaLoteConcVO();
				Object[] row = (Object[]) consultaConc.next();
				relatorioConsultaConc.setConcessionariaId(Long.valueOf(((BigDecimal)row[0]).longValue()));
				relatorioConsultaConc.setConcessionaria((String)row[1]);
				relatorioConsultaConc.setEndereco((String)row[2]);
				relatorioConsultaConc.setCep((BigDecimal)row[3]);
				relatorioConsultaConc.setCidade((String)row[4]);
				relatorioConsultaConc.setEstado((String)row[5]);
				relatorioConsultaConc.setCnpj((BigDecimal)row[6]);
				
				results.add(relatorioConsultaConc);
			}		
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		return results;	
	}	

	/** Lista os dados para o Relatório Consulta de Lotes. 
	 * 
	 * @param dataIni
	 * 	<code>dataIni</code> Data Início
	 * 
	 * @param dataFim
	 * 	<code>dataFim</code> Data Fim
	 * 
	 * @param lote
	 * 	<code>lote</code> lote	 
	 * 
	 * @param tipoLote
	 * 	<code>tipoLote</code> tipo do lote 
	 * 
	 * @param concessionaria
	 * 	<code>concessionaria</code> concessionaria
	 *  
	 * @return list
	 * 	<code>list</code> lista de dados do Relatório Consulta de Lotes. 
	 * 
	 * @throws DaoException
	 */			
	public List listRelatorioConsultaLote( Date    dataIni
						                 , Date    dataFim
						                 , Long    linha
						                 , Integer lote
						                 , Long    tipoLote
						                 , Long    concessionaria) throws DaoException{
		
		String whereLote = lote.intValue() != 0 ? " AND lt.lote_id = :p_lote " : "";
		
		String sql = "SELECT lt.lote_id          lote                                           " + 
					"      , tl.descricao	     tipoLote                                       " + 
					"      , lt.lote_date	     dtAbertura                                     " + 
					"      , lt.liberacao_date   dtLiberacao                                    " + 
					"      , lt.fechamento_date  dtFechamento                                   " + 
					"      , sl.descricao        descStatus                                     " + 
					"   FROM ym_sg_lote	  	    lt                                              " + 
					"      , ym_sg_tipo_lote	tl                                              " + 
					"      , ym_sg_status_lote	sl                                              " + 
					"      , ym_sg_linha        li                                              " + 
					"  WHERE lt.tipo_lote_id       = tl.tipo_lote_id                            " + 
					"    AND lt.status_lote_id     = sl.status_lote_id                          " + 
					"    AND lt.linha_id           = li.linha_id                                " + 
					"    AND lt.linha_id           = :p_linha                                   " + 
					"    AND lt.concessionaria_id  = nvl(:p_concessionaria,lt.concessionaria_id)" + 
					whereLote +
					"    AND lt.tipo_lote_id       = nvl(:p_tipo_lote, lt.tipo_lote_id)         " + 
					"    AND trunc(lt.created_date) >= to_date(:p_data_inicio, 'DD/MM/YYYY')    " + 
					"    AND trunc(lt.created_date) < (to_date(:p_data_fim, 'DD/MM/YYYY')+1)    " + 
					"    ORDER BY lt.lote_id                                                    " ;  

		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		query.setParameter("p_data_inicio", DateUtils.applyMask(dataIni));
		query.setParameter("p_data_fim", DateUtils.applyMask(dataFim));
		query.setParameter("p_linha", linha);
		
		if ( lote.intValue() != 0 )
			query.setParameter("p_lote", lote);
		
		query.setParameter("p_tipo_lote", tipoLote);
		query.setParameter("p_concessionaria", concessionaria); 
		
		List results = new ArrayList();
		try {
			Iterator consultaLote = query.list().iterator();
			
			while ( consultaLote.hasNext() ) {
			
				RelatorioConsultaLoteVO relatorioConsultaLote = new RelatorioConsultaLoteVO();
				Object[] row = (Object[]) consultaLote.next();
				relatorioConsultaLote.setLote(Integer.valueOf(row[0].toString()));
				relatorioConsultaLote.setTipoLote((String)row[1]);
				relatorioConsultaLote.setDtAbertura((Date)row[2]);
				relatorioConsultaLote.setDtLiberacao((Date)row[3]);
				relatorioConsultaLote.setDtFechamento((Date)row[4]);
				relatorioConsultaLote.setDescStatus((String)row[5]);
		
				results.add(relatorioConsultaLote);
			}		
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		
		//System.out.println("listRelatorioConsultaLote:" + results.size());
		
		return results;	
	}	
	
	
	/** Lista os dados para o Relatório Consulta de Lotes. 
	 * 
	 * @param dataIni
	 * 	<code>dataIni</code> Data Início
	 * 
	 * @param dataFim
	 * 	<code>dataFim</code> Data Fim
	 * 
	 * @param lote
	 * 	<code>lote</code> lote	 
	 * 
	 * @param tipoLote
	 * 	<code>tipoLote</code> tipo do lote 
	 * 
	 * @param concessionaria
	 * 	<code>concessionaria</code> concessionaria
	 *  
	 * @return list
	 * 	<code>list</code> lista de dados do Relatório Consulta de Lotes. 
	 * 
	 * @throws DaoException
	 */			
	public List listRelatorioConsultaLoteStatus( Date    dataIni
								               , Date    dataFim
								               , Long    linha
								               , Integer lote
								               , Long    tipoLote
								               , Long    concessionaria) throws DaoException{
		
		String whereLote = lote.intValue() != 0 ? " AND lt.lote_id = :p_lote " : "";
		
		String sql = "SELECT ll.created_date    dtStatus                                      " +
					"      , sl.descricao       descStatusAnterior                            " +
					"   FROM ym_sg_lote		      lt                                          " +
					"      , ym_sg_lote_log       ll                                          " +
					"      , ym_sg_status_lote    sl                                          " +
					"  WHERE lt.lote_id            = ll.lote_id                               " +
					"    AND ll.status_lote_id     = sl.status_lote_id                        " +
					"    AND lt.linha_id           = :p_linha                                 " +
					"    AND lt.concessionaria_id  = :p_concessionaria                        " + 
					whereLote +
					"    AND lt.tipo_lote_id       = :p_tipo_lote                             " +
					"    AND trunc(lt.created_date) >= to_date(:p_data_inicio, 'DD/MM/YYYY')  " +
					"    AND trunc(lt.created_date) <  (to_date(:p_data_fim, 'DD/MM/YYYY')+1) " +					
					"    ORDER BY ll.created_date                                             " ; 

		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		query.setParameter("p_data_inicio", DateUtils.applyMask(dataIni));
		query.setParameter("p_data_fim", DateUtils.applyMask(dataFim));
		query.setParameter("p_linha", linha);
		
		if ( lote.intValue() != 0 )
			query.setParameter("p_lote", lote);
		
		query.setParameter("p_tipo_lote", tipoLote); 
		query.setParameter("p_concessionaria", concessionaria); 
		
		List results = new ArrayList();
		try {
			Iterator consultaStatus = query.list().iterator();
			
			while ( consultaStatus.hasNext() ) {
			
				RelatorioConsultaLoteStatusVO relatorioConsultaStatus = new RelatorioConsultaLoteStatusVO();
				Object[] row = (Object[]) consultaStatus.next();
	
				relatorioConsultaStatus.setDtStatus((Date)row[0]);
				relatorioConsultaStatus.setDescStatusAnterior((String)row[1]);
		
				results.add(relatorioConsultaStatus);
			}		
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		return results;	
	}	
	
	/** Lista os dados para o Relatório de Protocolo de Documentos de Revisão.
	 * 
	 * @param lote
	 * 	<code>lote</code> lote
	 *  
	 * @return list
	 * 	<code>list</code> lista de dados do Relatório de Protocolo de Documentos de Revisão.
	 * 
	 * @throws DaoException
	 */			
	public List listRelatorioDocRevisao( Integer lote) throws DaoException{
		
		String sql = "SELECT distinct cp.lote_id         lote                                    " +
					"      , sl.descr_reduz     statusLote                                       " +
					"      , nf.num_nf          numeroNf                                         " +
					"      , nf.serie_nf        serieNf                                          " +
					"      , nf.tipo_nf_id      tipoNf                                           " +
					"      , nf.data_nf         dataNf                                           " +
					"      , (nvl(nf.valor_nf_sv,0) + nvl(nf.valor_nf_pc,0)) valorNf             " +
					"      , nf.emissor         emitente                                         " +
					"      , nf.destinatario    destinatario                                     " +
					"      , nf.nf_compl_num    numeroCp                                         " +
					"      , nf.nf_compl_serie  serieCp                                          " +
					"      , nf.nf_compl_data   dataCp                                           " +
					"      , (nvl(nf.nf_compl_valor_sv,0) + nvl(nf.nf_compl_valor_pc,0)) valorCp " +
					"      , co.razao_social	nomeConc                                         " +
					"      , co.endereco        enderecoConc                                     " +
					"      , co.cidade          cidadeConc                                       " +
					"      , co.cep             cepConc                                          " +
					"      , co.estado          estadoConc                                       " +
					"      , co.cnpj            cnpjConc                                         " +
					"      , li.descr_linha     linha                                            " +
					"      , ye.org_code|| ' - '||ye.nome_empresa  empresa                       " +
					"	   , tn.descr_tipo_nf   descrTipo                                        " +
					"	   , sl.status_lote_id	starusLoteId									 " +
					"      , co.malote          malote                                           " +
					"      , co.codigo_concessionaria   codigo                                   " +
					"   FROM ym_sg_nota           nf                                             " +
					"      , ym_sg_concessionaria co                                             " +
					"      , ym_sg_cupom_nota     cn                                             " +
					"      , ym_sg_cupom          cp                                             " +
					"      , ym_sg_lote           lt                                             " +
					"      , ym_sg_status_lote    sl                                             " +
					"      , ym_sg_linha          li                                             " +
					"      , ym_sg_empresa        ye                                             " +
					"      , ym_sg_tipo_nf        tn											 " +
					"  WHERE nf.nota_id(+)        = cn.nota_id	                                 " +
					"    AND cn.cupom_id(+)       = cp.cupom_id                                  " +
					"    AND cp.lote_id           = lt.lote_id                                   " +
					"    AND lt.concessionaria_id = co.concessionaria_id                         " + 
					"    AND lt.status_lote_id    = sl.status_lote_id                            " +
					"    AND lt.linha_id          = li.linha_id                                  " +
					"    AND cp.organization_id   = ye.organization_id                           " +
					"    AND nf.tipo_nf_id        = tn.tipo_nf_id(+)                             " +
					"	 AND cp.end_date is null  												 " +
                    "    AND nf.end_date is null												 " +
					"    AND lt.lote_id           = :p_lote                                      " +
					"    AND lt.status_lote_id in (" + 
							StatusLote.STATUS_AGUARDANDO_RECEB_DOC +
							"," +
							StatusLote.STATUS_AGUARDANDO_ADIANTAMENTO_RECEB_DOC +
							"," +
							StatusLote.STATUS_ADIANTAMENTO_PAGO_AGUARDANDO_RECEB_DOC +
							"," +
							StatusLote.STATUS_PERIODICA +
							")" +
					" ORDER BY numeroNf                                                          " ;
		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		query.setParameter("p_lote", lote);
		
		List results = new ArrayList();	
		try {
			Iterator docRevisao = query.list().iterator();
			
			while ( docRevisao.hasNext() ) {
			
				RelatorioDocRevisaoVO relatorioDocRevisao = new RelatorioDocRevisaoVO();
				Object[] row = (Object[]) docRevisao.next();
				relatorioDocRevisao.setLote((BigDecimal)row[0]);
				relatorioDocRevisao.setStatusLote((String)row[1]);
				relatorioDocRevisao.setNumeroNf((BigDecimal)row[2]);
				relatorioDocRevisao.setSerieNf((String)row[3]);
				relatorioDocRevisao.setTipoNf((BigDecimal)row[4]);
				relatorioDocRevisao.setDataNf((Date)row[5]);
				relatorioDocRevisao.setValorNf((BigDecimal)row[6]);
				relatorioDocRevisao.setEmitente((String)row[7]);
				relatorioDocRevisao.setDestinatario((String)row[8]);
				relatorioDocRevisao.setNumeroCp((BigDecimal)row[9]);
				relatorioDocRevisao.setSerieCp((String)row[10]);
				relatorioDocRevisao.setDataCp((Date)row[11]);
				relatorioDocRevisao.setValorCp((BigDecimal)row[12]);
				relatorioDocRevisao.setNomeConc((String)row[13]);
				relatorioDocRevisao.setEnderecoConc((String)row[14]);
				relatorioDocRevisao.setCidadeConc((String)row[15]);
				relatorioDocRevisao.setCepConc((BigDecimal)row[16]);
				relatorioDocRevisao.setEstadoConc((String)row[17]);
				relatorioDocRevisao.setCnpjConc((BigDecimal)row[18]);
				relatorioDocRevisao.setLinha((String)row[19]);
				relatorioDocRevisao.setEmpresa((String)row[20]);
				relatorioDocRevisao.setDescrTipo((String)row[21]);
				relatorioDocRevisao.setMalote((String)row[23]);
				relatorioDocRevisao.setCodigoConcessionaria((String)row[24]);
				
				results.add(relatorioDocRevisao);
			}		
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		
		return results;	
	}	
	
	/** Lista os dados para o Relatório de Protocolo de Documentos de Revisão.
	 * 
	 * @param lote
	 * 	<code>lote</code> lote	 
	 * 
	 * @param concessionaria
	 * 	<code>concessionaria</code> concessionaria
	 *  
	 * @return list
	 * 	<code>list</code> lista de dados do Relatório de Protocolo de Documentos de Revisão.
	 * 
	 * @throws DaoException
	 */			
	public List listRelatorioDocRevisaoDetalhe( Integer lote) throws DaoException{
		
		String sql = "SELECT sum(decode(rv.numero_revisao,1,1,0))    qtdRev1   " +
					"      , sum(decode(rv.numero_revisao,2,1,0))    qtdRev2   " +
					"      , sum(decode(rv.numero_revisao,3,1,0))    qtdRev3   " +
					"      , sum(decode(rv.numero_revisao,4,1,0))    qtdRev4   " +
					"      , sum(decode(rv.numero_revisao,5,1,0))    qtdRev5   " +
					"      , sum(decode(rv.numero_revisao,6,1,0))    qtdRev6   " +
					"      , sum(decode(rv.numero_revisao,7,1,0))    qtdRev7   " +
					"      , sum(decode(rv.numero_revisao,8,1,0))    qtdRev8   " +
					"      , sum(decode(rv.numero_revisao,9,1,0))    qtdRev9   " +
					"      , sum(decode(rv.numero_revisao,10,1,0))   qtdRev10  " +
					"      , sum(decode(rv.numero_revisao,11,1,0))   qtdRev11  " +
					"      , sum(decode(rv.numero_revisao,12,1,0))   qtdRev12  " +
					"  FROM ym_sg_cupom       cp                               " +
					"     , ym_sg_revisao     rv  							   " +
					"	  , ym_sg_lote        lt                     		   " +
					" WHERE cp.revisao_id     =  rv.revisao_id                 " +
					"	AND cp.lote_id        =  lt.lote_id					   " +
					"	AND cp.end_date is null  							   " +
					"   AND lt.lote_id        =  :p_lote                       " +
					"	AND lt.status_lote_id in (" + 
							StatusLote.STATUS_AGUARDANDO_RECEB_DOC +
							"," +
							StatusLote.STATUS_AGUARDANDO_ADIANTAMENTO_RECEB_DOC +
							"," +
							StatusLote.STATUS_ADIANTAMENTO_PAGO_AGUARDANDO_RECEB_DOC +
							"," +
							StatusLote.STATUS_PERIODICA +
							")";
		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		query.setParameter("p_lote", lote);		
		
		List results = new ArrayList();
		try {
			Iterator docRevisao = query.list().iterator();
			
			while ( docRevisao.hasNext() ) {
			
				RelatorioDocRevisaoDetalheVO relatorioDocRevisao = new RelatorioDocRevisaoDetalheVO();
				Object[] row = (Object[]) docRevisao.next();
				relatorioDocRevisao.setQtdRev1((BigDecimal)row[0]);
				relatorioDocRevisao.setQtdRev2((BigDecimal)row[1]);
				relatorioDocRevisao.setQtdRev3((BigDecimal)row[2]);
				relatorioDocRevisao.setQtdRev4((BigDecimal)row[3]);
				relatorioDocRevisao.setQtdRev5((BigDecimal)row[4]);
				relatorioDocRevisao.setQtdRev6((BigDecimal)row[5]);
				relatorioDocRevisao.setQtdRev7((BigDecimal)row[6]);
				relatorioDocRevisao.setQtdRev8((BigDecimal)row[7]);
				relatorioDocRevisao.setQtdRev9((BigDecimal)row[8]);
				relatorioDocRevisao.setQtdRev10((BigDecimal)row[9]);
				relatorioDocRevisao.setQtdRev11((BigDecimal)row[10]);
				relatorioDocRevisao.setQtdRev12((BigDecimal)row[11]);
				
				results.add(relatorioDocRevisao);
			}		
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		
		return results;	
	}	
	
	/** Lista os dados para o Relatório de Processamento de Peças - Fase1.
	 * 
	 * @param empresa
	 * 	<code>empresa</code> empresa (Não Obrigatório)
	 * 	
	 * @param lote
	 * 	<code>lote</code> lote
	 * 
	 * @return list
	 * 	<code>list</code> lista de dados do Relatório de Processamento de Peças - Fase1.
	 * 
	 * @throws DaoException
	 */	
	public List listRelatorioProcPecasFase1(Long empresa, Integer lote) throws DaoException{
		
		String whereEmpresa = empresa.intValue() != 0 ? " AND ep.organization_id = nvl(:p_empresa,ep.organization_id) " : "";
		
		String sql = "SELECT li.descr_linha                     linhaProduto     " +  
					"      , lt.lote_id                         lote	         " + 
					"      , gh.garantia_id                     numeroSg         " + 
					"      , gh.modelo                          chassi           " + 
					"      , gh.numero_os                       numeroOs         " + 
					"      , gh.data_abertura_os                dtAbertura       " + 
					"      , gh.data_fechamento_os              dtFechto         " + 
					"      , gh.quilometragem                   km               " + 
					"      , ep.org_code ||' - '||ep.nome_empresa nomeEmp        " + 
					"      , ep.inscr_estadual                  inscrEstadualEmp " + 
					"      , substr(lpad(co.cnpj, 14, '0'),1,8) cnpjConc         " + 
					"      , co.razao_social                    razaoSocialConc  " + 
					"      , co.endereco                        enderecoConc     " + 
					"      , co.cep                             cepConc          " + 
					"      , co.cidade                          cidadeConc       " + 
					"      , co.estado                          estadoConc       " + 
					"      , nvl(pe.qtde, 0)                    qtde             " + 
					"      , pe.item                            item             " + 
					"      , pe.descricao                       descricao        " + 
					"      , se.servico                         servico          " + 
					"      , se.sintoma                         sintoma          " + 
					"      , se.condicao                        condicao         " + 
					"   FROM ym_sg_lote            lt                            " + 
					"      , ym_sg_garantia_header gh                            " + 
					"      , ym_sg_faturamento     ft                            " + 
					"      , ym_sg_empresa         ep                            " + 
					"      , ym_sg_concessionaria  co                            " + 
					"      , ym_sg_linha           li                            " + 
					"      ,(SELECT gl.quantidade   qtde                         " + 
					"             , it.item_code    item                         " + 
					"             , it.descricao    descricao                    " + 
					"             , gl.garantia_id  garantia_id                  " + 
					"          FROM ym_sg_garantia_line gl                       " + 
					"             , ym_sg_item          it                       " + 
					"         WHERE It.item_id     = gl.item_id                  " + 
					"           AND gl.end_date    IS NULL) pe                   " + 
					"      ,(SELECT sv.servico_code  servico                     " + 
					"             , si.sintoma_code  sintoma                     " + 
					"             , co.condicao_code condicao                    " + 
					"             , gh.garantia_id   garantia_id                 " + 
					"          FROM ym_sg_servico_grupo   sg                     " + 
					"             , ym_sg_servico         sv                     " + 
					"             , ym_sg_sintoma         si                     " + 
					"             , ym_sg_condicao        co                     " + 
					"             , ym_sg_garantia_header gh                     " + 
					"         WHERE gh.garantia_id = sg.garantia_id              " + 
					"           AND sg.servico_id  = sv.servico_id               " + 
					"           AND gh.sintoma_id  = si.sintoma_id               " + 
					"           AND gh.condicao_id = co.condicao_id              " + 
					"           AND gh.end_date   IS NULL                        " + 
					"           AND sg.end_date   IS NULL) se                    " + 
					"  WHERE lt.lote_id           = gh.lote_id                   " + 
					"    AND gh.modelo            = ft.chassi                    " + 
					"    AND ft.organization_id   = ep.organization_id           " + 
					"    AND lt.concessionaria_id = co.concessionaria_id         " + 
					"    AND li.linha_id          = lt.linha_id                  " + 
					"    AND gh.garantia_id       = pe.garantia_id(+)            " + 
					"    AND gh.garantia_id       = se.garantia_id(+)            " + 
					"    AND lt.status_lote_id    IN (2, 4)                      " + 
					"    AND lt.end_date          IS NULL                        " + 
					"    AND gh.end_date          IS NULL                        " + 
					"    AND ft.end_date          IS NULL                        " + 
					whereEmpresa +
					"    AND lt.lote_id           = trim(:p_lote)                " + 
					"  ORDER BY lote, numeroSg                                   " ;
		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		if ( empresa.intValue() != 0  )
			query.setParameter("p_empresa", empresa);
		
		query.setParameter("p_lote", lote);
		
		List results = new ArrayList();	
		try {
			Iterator procPecasFase1 = query.list().iterator();
			
			while ( procPecasFase1.hasNext() ) {
			
				RelatorioProcPecasFase1VO relatorioProcPecasFase1 = new RelatorioProcPecasFase1VO();
				Object[] row = (Object[]) procPecasFase1.next();
				relatorioProcPecasFase1.setLinhaProduto((String)row[0]);
				relatorioProcPecasFase1.setLote((BigDecimal)row[1]);
				relatorioProcPecasFase1.setNumeroSg((BigDecimal)row[2]);
				relatorioProcPecasFase1.setChassi((String)row[3]);
				relatorioProcPecasFase1.setNumeroOs((String)row[4]);
				relatorioProcPecasFase1.setDtAbertura((Date)row[5]);
				relatorioProcPecasFase1.setDtFechto((Date)row[6]);
				relatorioProcPecasFase1.setKm((BigDecimal)row[7]);
				relatorioProcPecasFase1.setNomeEmp((String)row[8]);
				relatorioProcPecasFase1.setInscrEstadualEmp((String)row[9]);
				relatorioProcPecasFase1.setCnpjConc((String)row[10]);
				relatorioProcPecasFase1.setRazaoSocialConc((String)row[11]);
				relatorioProcPecasFase1.setEnderecoConc((String)row[12]);
				relatorioProcPecasFase1.setCepConc((BigDecimal)row[13]);
				relatorioProcPecasFase1.setCidadeConc((String)row[14]);
				relatorioProcPecasFase1.setEstadoConc((String)row[15]);
				relatorioProcPecasFase1.setQtde((BigDecimal)row[16]);
				relatorioProcPecasFase1.setItem((String)row[17]);
				relatorioProcPecasFase1.setDescricao((String)row[18]);
				relatorioProcPecasFase1.setServico((String)row[19]);
				relatorioProcPecasFase1.setSintoma((String)row[20]);
				relatorioProcPecasFase1.setCondicao((String)row[21]);
	            
				results.add(relatorioProcPecasFase1);
			}		
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		
		return results;	
	}
	
	/** Lista os dados para o Relatório de Processamento de Peças - Fase2.
	 * 
	 * @param lote
	 * 	<code>lote</code> lote
	 * 
	 * @return list
	 * 	<code>list</code> lista de dados do Relatório de Processamento de Peças - Fase2.
	 * 
	 * @throws DaoException
	 */	
	public List listRelatorioProcPecasFase2( Long empresa, Integer lote) throws DaoException{
		
		String whereEmpresa = empresa.intValue() != 0 ? " AND ep.organization_id = nvl(:p_empresa,ep.organization_id) " : "";
		
		String sql = "SELECT li.descr_linha                         linhaProduto       " + //0
					 "      , lt.lote_id                            lote	           " + //1
					 "      , gh.garantia_id                        numeroSg           " + //2
					 "      , gh.modelo                             chassi             " + //3
					 "      , gh.numero_os                          numeroOs           " + //4
					 "      , gh.data_abertura_os                   dtAbertura         " + //5
					 "      , gh.data_fechamento_os                 dtFechto           " + //6
					 "      , gh.quilometragem                      km                 " + //7
					 "      , ep.org_code||' - '|| ep.nome_empresa  nomeEmp            " + //8
					 "      , ep.inscr_estadual                     inscrEstadualEmp   " + //9
					 "      , substr(lpad(co.cnpj, 14, '0'),1,8)    cnpjConc           " + //10
					 "      , co.razao_social                       razaoSocialConc    " + //11
					 "       , co.endereco                          enderecoConc       " + //12
					 "      , co.cep                                cepConc            " + //13
					 "      , co.cidade                             cidadeConc         " + //14
					 "      , co.estado                             estadoConc         " + //15
					 "      , se.sintoma                            sintoma            " + //16
					 "      , se.descricao_sintoma				    descricao_sintoma  " + //17					
					 "      , se.condicao                           condicao           " + //18
					 "      , se.condicao_descricao				    condicao_descricao " + //19	
					 "      , st.vlServicoTerc                      vlServicoTerc      " + //20
					 "   FROM Ym_sg_lote            lt                                 " + 
					 "      , ym_sg_garantia_header gh                                 " + 
					 "      , ym_sg_faturamento     ft                                 " + 
					 "      , ym_sg_empresa         ep                                 " + 
					 "      , ym_sg_concessionaria  co                                 " + 
					 "      , ym_sg_linha           li                                 " + 
					 "      , (	SELECT 	si.sintoma_code      sintoma				   " +
					 "					, si.descricao       descricao_sintoma		   " +
					 "					, co.condicao_code   condicao				   " +
					 "					, co.descricao       condicao_descricao		   " +
					 "					, gh.garantia_id     garantia_id			   " +
					 "			FROM    ym_sg_sintoma        	si					   " +
					 "					, ym_sg_condicao     	co					   " +
					 "					, ym_sg_garantia_header gh					   " +
					 "			WHERE   gh.sintoma_id  = si.sintoma_id				   " +
					 "			AND     gh.condicao_id = co.condicao_id 			   " +
					 "			AND     :data >= si.start_date 						   " +
					 "			AND     ((si.end_date is null) OR (si.end_date < :data))  " +
					 "			AND     :data >= co.start_date						   " +					 
					 "			AND ((co.end_date is null) OR (co.end_date < :data)) )se   " + 
					 "      , (	SELECT  gh.vl_servico_terceiro     vlServicoTerc       " + 
					 "              	, gh.garantia_id           garantia_id         " + 
					 "           FROM ym_sg_garantia_header gh						   " +
					 "			 WHERE  :data >= gh.start_date						   " +					 
					 "			 AND ((gh.end_date is null) OR (gh.end_date < :data))) st  " + 
					 "  WHERE lt.lote_id           = gh.lote_id                        " + 
					 "    AND gh.modelo            = ft.chassi                         " + 
					 "    AND ft.organization_id   = ep.organization_id                " + 
					 "    AND lt.concessionaria_id = co.concessionaria_id              " + 
					 "    AND li.linha_id          = lt.linha_id                       " + 
					 "    AND gh.garantia_id       = se.garantia_id (+)                " + 
					 "    AND gh.garantia_id       = st.garantia_id (+)                " + 			
					 whereEmpresa 														 +
					 "    AND lt.lote_id           = trim(:p_lote)                     " +
					 "    AND lt.start_date <= :data 								   " +
			         "    AND ( (lt.end_date is null) OR (lt.end_date >= :data) )	   " +
			         "	  AND gh.start_date <= :data 								   " +
			         "    AND ( (gh.end_date is null) OR (gh.end_date >= :data) )	   " +
			         "	  AND ft.start_date <= :data 								   " +
			         "    AND ( (ft.end_date is null) OR (ft.end_date >= :data) )	   " +
					 "    ORDER BY numeroSg                                            " ;
		
		String sqlSubPeca = "      SELECT gl.quantidade                        quantidade      " + 
							"              , it.item_code                         item         " + 
							"              , it.descricao                         descricao    " + 
							"              , CASE WHEN gl.cobra_peca = 'S' THEN				   " +
							"				 round(gl.vl_peca_garantia,2)          			   " +
							"				 ELSE 0 END							  vlUnitPeca   " + 
							"              , CASE WHEN gl.cobra_peca = 'S' THEN				   " +
							"				 round((gl.quantidade * gl.vl_peca_garantia ),2)   " +
							"				 ELSE 0 END							  vlTotalPeca  " + 
							"              , CASE WHEN gl.envia_peca = 'S' THEN                " + 
							"                round((gl.quantidade * gl.vl_preco_sugerido * 0.1),2) " + 
							"                ELSE 0 END				              vlRemesPeca  " + 
							"              , gl.garantia_id                       garantia_id  " +      
							"           FROM ym_sg_garantia_line gl                            " + 
							"              	 , (											   " +
							"						SELECT 	itx.item_id						    " +
							"								, itx.item_code						" +
							"								, itx.descricao 					" +
							"								, itx.start_date					" +
							"								, itx.end_date						" +
							"								, max(itx.organization_id)			" +
							"						FROM   ym_sg_item  itx						" +
							"						WHERE  itx.start_date <= :data				" +
							"						AND    ( (itx.end_date is null) OR (itx.end_date >= :data) ) " +
							"						GROUP BY 	itx.item_id,itx.item_code		" +
							"									, itx.start_date				" +
							"									, itx.descricao					" +
							"									, itx.end_date					" +
							"				)      it          								   " + 
							"          WHERE it.item_id   = gl.item_id                    	   " + 
							"		   AND gl.garantia_id = :p_garantiaId				   	   " +
							"		   AND gl.start_date <= :data 							   " +
							"   	   AND ( (gl.end_date is null) OR (gl.end_date >= :data) ) " +
							"		   AND it.start_date <= :data 							   " +
							"   	   AND ( (it.end_date is null) OR (it.end_date >= :data) ) ";
		
		String sqlSubServ = "      SELECT  	sv.servico_code    servico 							" +
							"				, sv.descricao     descricao						" +
							"				, sg.Vl_servico   vlTotalServico					" +
							"	   FROM 	ym_sg_servico_grupo  sg								" +
							"				, ym_sg_servico sv									" +
							"	   WHERE 	garantia_id = :p_garantiaId 						" +
							"	   AND sv.servico_id = sg.servico_id 							" +
							"	   AND :data >= sg.start_date 									" +
							"	   AND ((sg.end_date is null) OR (sg.end_date >= :data))		";

		
		Session session = super.getSession();
		SQLQuery query        = session.createSQLQuery(sql);
		SQLQuery querySubPeca = session.createSQLQuery(sqlSubPeca);
		SQLQuery querySubServ = session.createSQLQuery(sqlSubServ);
		
		if ( empresa.intValue() != 0  )
			query.setParameter("p_empresa", empresa);
		
		query.setParameter("p_lote", lote);
		query.setParameter("data"  , new Date());
		
		List results = new ArrayList();	
		try {
			Iterator procPecasFase2 = query.list().iterator();
			
			ArrayList resultsSubPecas   = null;
			ArrayList resultsSubServ    = null;
			Iterator  procPecasFase2Sub = null;
			Iterator  procServFase2Sub  = null;
			
			RelatorioProcPecaFase2PecaVO    pecaVO    = null;
			RelatorioProcPecaFase2ServicoVO servicoVO = null;
			
			while ( procPecasFase2.hasNext() ) {
			
				RelatorioProcPecasFase2VO relatorioProcPecasFase2 = new RelatorioProcPecasFase2VO();
				Object[] row = (Object[]) procPecasFase2.next();
				relatorioProcPecasFase2.setLinhaProduto((String)row[0]);
				relatorioProcPecasFase2.setLote((BigDecimal)row[1]);
				relatorioProcPecasFase2.setNumeroSg((BigDecimal)row[2]);
				relatorioProcPecasFase2.setChassi((String)row[3]);
				relatorioProcPecasFase2.setNumeroOs((String)row[4]);
				relatorioProcPecasFase2.setDtAbertura((Date)row[5]);
				relatorioProcPecasFase2.setDtFechto((Date)row[6]);
				relatorioProcPecasFase2.setKm((BigDecimal)row[7]);
				relatorioProcPecasFase2.setNomeEmp((String)row[8]);
				relatorioProcPecasFase2.setInscrEstadualEmp((String)row[9]);
				relatorioProcPecasFase2.setCnpjConc((String)row[10]);
				relatorioProcPecasFase2.setRazaoSocialConc((String)row[11]);
				relatorioProcPecasFase2.setEnderecoConc((String)row[12]);
				relatorioProcPecasFase2.setCepConc((BigDecimal)row[13]);
				relatorioProcPecasFase2.setCidadeConc((String)row[14]);
				relatorioProcPecasFase2.setEstadoConc((String)row[15]);
				relatorioProcPecasFase2.setSintoma((String)row[16]);
				relatorioProcPecasFase2.setSintomaDesc((String)row[17]);
				relatorioProcPecasFase2.setCondicao((String)row[18]);
				relatorioProcPecasFase2.setCondicaoDesc((String)row[19]);
				relatorioProcPecasFase2.setVlServicoTerc((BigDecimal)row[20]);
							
				//****************************************************************//
				// Neste bloco recuperamos as pecas da garantia para o relatório  //
				//****************************************************************//
				querySubPeca.setParameter("p_garantiaId", relatorioProcPecasFase2.getNumeroSg());
				querySubPeca.setParameter("data"  , new Date());
				
				resultsSubPecas = new ArrayList();		
				procPecasFase2Sub = querySubPeca.list().iterator();
				double totRemes = 0;
				
				// Aqui pegamos as peças utilizadas na Garantia
				while ( procPecasFase2Sub.hasNext() ) {
					
					pecaVO = new RelatorioProcPecaFase2PecaVO();
					Object[] rowSub = (Object[]) procPecasFase2Sub.next();
					pecaVO.setQuantidade(new Long(((BigDecimal)rowSub[0]).longValue()));
					pecaVO.setItem((String)rowSub[1]);
					pecaVO.setDescricao((String)rowSub[2]);
					pecaVO.setVlUnitPeca(new Double(((BigDecimal)rowSub[3]).doubleValue()));	
					pecaVO.setVlTotalPeca(new Double(((BigDecimal)rowSub[4]).doubleValue()));	
					pecaVO.setVlRemesPeca(new Double(((BigDecimal)rowSub[5]).doubleValue()));	
					totRemes += pecaVO.getVlRemesPeca().doubleValue();
					pecaVO.setGarantiaId(new Long(((BigDecimal)rowSub[6]).longValue()));
					
					resultsSubPecas.add(pecaVO);
					
				}
	            
				//System.out.println("Remessa:" + totRemes);
				
				relatorioProcPecasFase2.setListPecas(resultsSubPecas);
				
				//****************************************************************//
				// Neste bloco recuperamos os serviços da garantia para o relatório  //
				//****************************************************************//
				querySubServ.setParameter("p_garantiaId", relatorioProcPecasFase2.getNumeroSg());
				querySubServ.setParameter("data"  , new Date());
				
				resultsSubServ = new ArrayList();		
				procServFase2Sub = querySubServ.list().iterator();
				Double vlTotalServ = null;
				
				// Aqui pegamos as peças utilizadas na Garantia
				while ( procServFase2Sub.hasNext() ) {
					
					servicoVO = new RelatorioProcPecaFase2ServicoVO();
					Object[] rowSub = (Object[]) procServFase2Sub.next();
					servicoVO.setServico((String)rowSub[0]);
					servicoVO.setDescricao((String)rowSub[1]);
					vlTotalServ = (BigDecimal)rowSub[2] != null ? new Double(((BigDecimal)rowSub[2]).doubleValue()) : new Double(0);
					servicoVO.setVlTotalServico(vlTotalServ);	
					
					resultsSubServ.add(servicoVO);
					
				}
	            
				//System.out.println("subReportServicos Size:" + resultsSubServ.size());
				
				relatorioProcPecasFase2.setListServicos(resultsSubServ);
				
				relatorioProcPecasFase2.setListNotas(listRelatorioNotasToProcPecasFase2(lote));
				
				results.add(relatorioProcPecasFase2);
			}		
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		
		return results;	
	}
	
	/** Lista os dados das notas fiscais para o Relatório de Processamento de Peças - Fase2.
	 * 
	 * @param loteId
	 * 	<code>Integer</code> Id do lote
	 * 
	 * @return list
	 * 	<code>list</code> lista de notas do Relatório de Processamento de Peças - Fase2.
	 * 
	 * @throws DaoException
	 */
	public List listRelatorioNotasToProcPecasFase2( Integer loteId) throws DaoException {
		
		
		String sql        = "   SELECT  nt.*											" +
							" 	FROM 	ym_sg_nota nt									" +
							"        	, ym_sg_lote lt									" +
							"   WHERE 	lt.lote_id = nt.lote_id							" +
							"   AND     lt.lote_id = :p_loteId							" +
							"   AND     lt.start_date <= SYSDATE						" +
							"   AND     nvl(trunc(lt.end_date), SYSDATE + 1) >= SYSDATE	" +
							"   AND     nt.start_date <= SYSDATE						" +
							"   AND     nvl(trunc(nt.end_date), SYSDATE + 1) >= SYSDATE	";
		
		List    results = new ArrayList();
		Session session = super.getSession();
		
		try {
			
			SQLQuery query        = session.createSQLQuery(sql);
		
			query.addEntity(NotaFiscal.class);
			query.setParameter("p_loteId", loteId);
		
			results = query.list();
			
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}	
		
		return results;
	}
		
	/** Lista os dados para o Relatório Consulta Histórico Chassi, dados do Cabeçalho. 
	 * 
	 * @param chassi
	 * 	<code>chassi</code> chassi
	 * 
	 * @return list
	 * 	<code>list</code> lista de dados do Relatório Consulta Histórico Chassi, dados do Cabeçalho.  
	 * 
	 * @throws DaoException
	 */	
	public List listRelatorioHistChassiCabec(String chassi) throws DaoException{
		
		String sql = "SELECT yf.chassi   ||' - '||                             " +
					"        yi.item_code||' - '||                             " +
					"        yi.descricao              produto                 " +
					"      , yi.origem                 origem                  " +
					"      , co.razao_social||' - '||                          " +
					"        co.endereco    ||' - '||                          " +
					"        co.cidade      ||' - '||                          " +
					"        co.estado                 revenda                 " +
					"      , yf.ano_fabricacao||' - '||                        " +
					"        yf.ano_modelo             fabModelo               " +
					"      , (SELECT yc.data_venda                             " +
					"           FROM ym_sg_cupom   yc                          " +
					"              , ym_sg_revisao yr                          " +
					"          WHERE yc.revisao_id     = yr.revisao_id         " +
					"            AND yc.end_date       IS NULL                 " +
					//"            AND yc.status_mov_id  = 2                     " +
					"            AND yr.numero_revisao = 1                     " +
					"            AND yc.modelo         = yf.chassi) dtVenda    " +
					"      , yf.nota_fiscal            notaFiscal              " +
					"      , NULL                      empresa                 " +
					"      , yf.data_nf                dtRevenda               " +
					"      , yf.data_fabricacao        dtFabricacao            " +
					"      , yi.cor                    cor                     " +
					"      , (SELECT yc.cupom_code                             " +
					"           FROM ym_sg_cupom   yc                          " +
					"              , ym_sg_revisao yr                          " +
					"          WHERE yc.revisao_id     = yr.revisao_id         " +
					"            AND yc.end_date       IS NULL                 " +
					//"            AND yc.status_mov_id  = 2                     " +
					"            AND yr.numero_revisao = 1                     " +
					"            AND yc.modelo         = yf.chassi) nrGarantia " +         
					"      , yf.chassi_completo        chassi                  " +
					" FROM ym_sg_faturamento       yf                          " +
					"    , ym_sg_item              yi                          " +
					"    , ym_sg_concessionaria    co                          " +
					" WHERE yf.organization_id   = yi.organization_id          " +
					"   AND yf.item_id           = yi.item_id                  " +
					"   AND yf.concessionaria_id = co.concessionaria_id        " +
					"   AND yf.end_date          IS NULL                       " +
					"   AND   yf.chassi          = trim(:p_chassi)             " ;   
		
		
		StringBuffer sqltipoUso = new StringBuffer();
		sqltipoUso.append(" SELECT tu.descricao                           " );
		sqltipoUso.append(" FROM ym_sg_cupom   yc                         " );
		sqltipoUso.append("    , ym_sg_revisao yr                         " );
		sqltipoUso.append("    , ym_sg_tipo_uso_barco tu				  " );
		sqltipoUso.append(" WHERE yc.revisao_id     = yr.revisao_id       " );
		sqltipoUso.append(" AND yc.tipo_uso_id     = tu.tipo_uso_id       " );
		sqltipoUso.append(" AND yc.end_date       IS NULL                 " );
		sqltipoUso.append(" AND yr.numero_revisao = 1                     " );
		sqltipoUso.append(" AND yc.modelo         = trim(:p_chassi)		  " );     
		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		query.setParameter("p_chassi", chassi);
		
		List results = new ArrayList();	
		try {
			Iterator histChassiCabec = query.list().iterator();
			
			query = session.createSQLQuery(sqltipoUso.toString());
			
			while ( histChassiCabec.hasNext() ) {
			
				RelatorioHistChassiCabecVO relatorioHistChassiCabec = new RelatorioHistChassiCabecVO();
				Object[] row = (Object[]) histChassiCabec.next();
				
				relatorioHistChassiCabec.setProduto((String)row[0]);
				relatorioHistChassiCabec.setOrigem((String)row[1]);
				relatorioHistChassiCabec.setRevenda((String)row[2]);
				relatorioHistChassiCabec.setFabModelo((String)row[3]);
				relatorioHistChassiCabec.setDtVenda((Date)row[4]);
				relatorioHistChassiCabec.setNotaFiscal(Long.valueOf(row[5].toString()));
				relatorioHistChassiCabec.setEmpresa((String)row[6]);
				relatorioHistChassiCabec.setDtRevenda((Date)row[7]);
				relatorioHistChassiCabec.setDtFabricacao((Date)row[8]);
				relatorioHistChassiCabec.setCor((String)row[9]);
				relatorioHistChassiCabec.setNrGarantia((BigDecimal)row[10]);
				relatorioHistChassiCabec.setChassi((String)row[11]);
	            
				query.setParameter("p_chassi", chassi);
				
				String tipoUso = (String)query.uniqueResult();
				relatorioHistChassiCabec.setTipoUso(tipoUso);
				
				results.add(relatorioHistChassiCabec);
			}		
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		return results;	
	}
	
	/** Lista os dados para o Relatório Consulta Histórico Chassi, dados das Revisões. 
	 * 
	 * @param chassi
	 * 	<code>chassi</code> chassi
	 * 
	 * @return list
	 * 	<code>list</code> lista de dados do Relatório Consulta Histórico Chassi, dados das Revisões. 
	 * 
	 * @throws DaoException
	 */	
	public List listRelatorioHistChassiRevisao(String chassi) throws DaoException{
		
		String sql = "SELECT yr.numero_revisao      nrRevisao                            " + 
					"      , yc.data_revisao        dtRevisao                            " + 
					"      , co.razao_social        concessionaria                       " + 
					"      , sm.descricao           situacao                             " + 
					"      , lt.lote_id             lote                                 " + 
					"      , sl.descr_reduz         satusLote                            " + 
					"      , decode(yc.autorizacao_especial_id,null,' ','R') autorizacao " + 
					"   FROM ym_sg_lote                lt                                " + 
					"      , ym_sg_status_lote         sl                                " + 
					"      , ym_sg_cupom               yc                                " + 
					"      , ym_sg_revisao             yr                                " + 
					"      , ym_sg_concessionaria      co                                " + 
					"      , ym_sg_status_mov          sm                                " + 
					"  WHERE lt.status_lote_id    = sl.status_lote_id                    " + 
					"    AND   lt.lote_id           = yc.lote_id                         " + 
					//"    AND   lt.status_lote_id    in (9,11)                            " + 
					//"    AND   yc.status_mov_id     = 2                                  " + 
					"    AND   lt.concessionaria_id = co.concessionaria_id  			 " +
					"	 AND   yc.revisao_id        = yr.revisao_id						 " +
					"	 AND   yc.status_mov_id     = sm.status_mov_id		             " + 
					"    AND   lt.end_date          is null                              " + 
					"    AND   yc.end_date          is null                              " + 
					"    AND   lt.tipo_lote_id      = 1                                  " + 
					"    AND   yc.modelo            = trim(:p_chassi)                    " ;
					//"    AND   rownum <= 3                                               " ;
		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		query.setParameter("p_chassi", chassi);
		
		List results = new ArrayList();	
		try {
			Iterator histChassiRevisao = query.list().iterator();
			
			while ( histChassiRevisao.hasNext() ) {
			
				RelatorioHistChassiRevisaoVO relatorioHistChassiRevisao = new RelatorioHistChassiRevisaoVO();
				Object[] row = (Object[]) histChassiRevisao.next();
				
				relatorioHistChassiRevisao.setNrRevisao((BigDecimal)row[0]);
				relatorioHistChassiRevisao.setDtRevisao((Date)row[1]);
				relatorioHistChassiRevisao.setConcessionaria((String)row[2]);
				relatorioHistChassiRevisao.setSituacao((String)row[3]);
				relatorioHistChassiRevisao.setNrLote((BigDecimal)row[4]);
				relatorioHistChassiRevisao.setStatusLote((String)row[5]);
				relatorioHistChassiRevisao.setAutorizacao((String)row[6]);
				
				results.add(relatorioHistChassiRevisao);
			}		
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		return results;	
	}	
	
	/** Lista os dados para o Relatório Consulta Histórico Chassi, dados das Garantias. 
	 * 
	 * @param chassi
	 * 	<code>chassi</code> chassi
	 * 
	 * @return list
	 * 	<code>list</code> lista de dados do Relatório Consulta Histórico Chassi, dados das Garantias. 
	 * 
	 * @throws DaoException
	 */	
	public List listRelatorioHistChassiGarantia(String chassi) throws DaoException{
		
		String sql = "SELECT gh.garantia_code       garantia                          " +
					"      , gh.numero_os           numeroOs                          " +
					"      , gh.data_abertura_os    dtAberturaOs                      " +
					"      , gh.data_fechamento_os  dtFechamentoOs                    " +
					"      , co.razao_social        concessionaria                    " +
					"      , sm.descricao           situacao                          " +
					"      , lt.lote_id             lote                              " +
					"      , sl.descr_reduz         status                            " +
					"      , decode(gh.autorizacao_especial_id,null,' ','R') cortesia " +
					"      , gh.garantia_id         garantiaId                        " +
					"      , (SELECT descricao FROM ym_sg_tipo_uso_barco  WHERE tipo_uso_id = gh.tipo_uso_id) tipoUso " +
					"   FROM ym_sg_lote                lt                             " +
					"      , ym_sg_status_lote         sl                             " +
					"      , ym_sg_garantia_header     gh                             " +
					"      , ym_sg_concessionaria      co                             " +
					"      , ym_sg_status_mov          sm                             " +
					"  WHERE lt.status_lote_id    = sl.status_lote_id                 " +
					"    AND lt.lote_id           = gh.lote_id                        " +
					"    AND lt.status_lote_id    in (9,11)                           " +
					"    AND gh.status_mov_id     = 2                                 " +
					"    AND gh.status_mov_id     = sm.status_mov_id                  " +
					"    AND lt.concessionaria_id = co.concessionaria_id              " +
					"    AND lt.end_date          is null                             " +
					"    AND gh.end_date          is null                             " +
					"    AND lt.tipo_lote_id      = 2                                 " +
					"    AND gh.modelo            = trim(:p_chassi)                   " ;
		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		query.setParameter("p_chassi", chassi);
		
		List results = new ArrayList();	
		try {
			Iterator histChassiGarantia = query.list().iterator();
			
			while ( histChassiGarantia.hasNext() ) {
			
				RelatorioHistChassiGarantiaVO relatorioHistChassiGarantia = new RelatorioHistChassiGarantiaVO();
				Object[] row = (Object[]) histChassiGarantia.next();
				
				relatorioHistChassiGarantia.setGarantia((String)row[0]);
				relatorioHistChassiGarantia.setNumeroOs((String)row[1]);
				relatorioHistChassiGarantia.setDtAberturaOs((Date)row[2]);
				relatorioHistChassiGarantia.setDtFechamentoOs((Date)row[3]);
				relatorioHistChassiGarantia.setConcessionaria((String)row[4]);
				relatorioHistChassiGarantia.setSituacao((String)row[5]);
				relatorioHistChassiGarantia.setNrLote((BigDecimal)row[6]);
				relatorioHistChassiGarantia.setStatusLote((String)row[7]);
				relatorioHistChassiGarantia.setCortesia((String)row[8]);
				relatorioHistChassiGarantia.setGarantiaId(Long.valueOf(((BigDecimal)row[9]).longValue()));
				relatorioHistChassiGarantia.setTipoUso(row[10] != null ? (String)row[10] : "");
				
				results.add(relatorioHistChassiGarantia);
			}		
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		return results;	
	}	
	
	/** Lista os dados para o Relatório Consulta Histórico Chassi, dados das Peças. 
	 * 
	 * @param chassi
	 * 	<code>chassi</code> chassi
	 * 
	 * @return list
	 * 	<code>list</code> lista de dados do Relatório Consulta Histórico Chassi, dados das Peças. 
	 * 
	 * @throws DaoException
	 */	
	public List listRelatorioHistChassiPeca(Long garantia, String chassi) throws DaoException{
		
		String sql = "SELECT yi.item_code                      peca        " + 
					"      , gh.vl_servico_terceiro            vlServico3  " + 
					"      , get_servico_code(gh.garantia_id)  codServico  " + 
					"      , ys.sintoma_code                   codSintoma  " + 
					"      , yc.condicao_code                  codCondicao " + 
					"      , yc.condicao_code                  defeito " + 
				//	"      , yd.descricao_defeito_txt          defeito     " +
					"   FROM ym_sg_lote                lt                  " + 
					"      , ym_sg_status_lote         sl                  " + 
					"      , ym_sg_garantia_header     gh                  " + 
					"      , ym_sg_garantia_line       gl                  " + 
					"      , ym_sg_status_mov          sm                  " + 
					"      , ym_sg_item                yi                  " + 
					"      , ym_sg_sintoma             ys                  " + 
					"      , ym_sg_condicao            yc                  " + 
					"      , ym_sg_hist_descricao_defeito yd               " + 
					"  WHERE lt.status_lote_id    = sl.status_lote_id      " + 
					"    AND lt.lote_id           = gh.lote_id             " + 
					"    AND gh.garantia_id       = gl.garantia_id         " + 
					"    AND gh.organization_id   = yi.organization_id     " + 
					"    AND gh.sintoma_id        = ys.sintoma_id          " + 
					"    AND gl.item_id           = yi.item_id             " + 
					"    AND gh.garantia_id       = yd.garantia_id         " + 
					"    AND lt.status_lote_id    IN (9,11)                " + 
					"    AND gh.status_mov_id     = 2                      " + 
					"    AND gh.status_mov_id     = sm.status_mov_id       " + 
					"    AND lt.end_date          IS NULL                  " + 
					"    AND gh.end_date          IS NULL                  " + 
					"    AND lt.tipo_lote_id      = 2                      " + 
					"    AND gh.modelo            = trim(:p_chassi)        " + 
					"    AND gh.garantia_id       = :p_garantia            " ;
		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		query.setParameter("p_garantia", garantia);
		query.setParameter("p_chassi", chassi);
		
		List results = new ArrayList();	
		try {
			Iterator histChassiPeca = query.list().iterator();
			
			while ( histChassiPeca.hasNext() ) {
			
				RelatorioHistChassiPecaVO relatorioHistChassiPeca = new RelatorioHistChassiPecaVO();
				Object[] row = (Object[]) histChassiPeca.next();
				
				relatorioHistChassiPeca.setPeca((String)row[0]);
				relatorioHistChassiPeca.setVlServico3((BigDecimal)row[1]);
				relatorioHistChassiPeca.setCodServico((String)row[2]);
				relatorioHistChassiPeca.setCodSintoma((String)row[3]);
				relatorioHistChassiPeca.setCodCondicao((String)row[4]);
				relatorioHistChassiPeca.setDefeito((String)row[5].toString());
	            
				results.add(relatorioHistChassiPeca);
			}		
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		return results;	
	}	

	/** Lista os dados para o Relatório Consulta Histórico Chassi, parecer do Analista. 
	 * 
	 * @param chassi
	 * 	<code>chassi</code> chassi
	 * 
	 * @return list
	 * 	<code>list</code> lista de dados do Relatório Consulta Histórico Chassi, parecer do Analista. 
	 * 
	 * @throws DaoException
	 */	
	public List listRelatorioHistChassiParecer(Long garantia, String chassi) throws DaoException{
		
		String sql = "SELECT gh.modelo parecerAnalista "+ //yd.parecer_tecnico_txt  parecerAnalista   " +   
					"   FROM ym_sg_lote                 lt             " +   
					"      , ym_sg_status_lote          sl             " +   
					"      , ym_sg_garantia_header      gh             " +   
					"      , ym_sg_garantia_line        gl             " +   
					"      , ym_sg_item                 yi             " +   
					"      , ym_sg_condicao             yc             " +   
					"      , ym_sg_hist_parecer_tecnico yd             " +   
					"  WHERE lt.status_lote_id    = sl.status_lote_id  " +   
					"    AND lt.lote_id           = gh.lote_id         " +   
					"    AND gh.garantia_id       = yd.garantia_id     " +   
					"    AND gh.garantia_id       = gl.garantia_id     " +   
					"    AND gl.item_id           = yi.item_id         " +   
					"    AND lt.status_lote_id    in (9,11)            " +   
					"    AND gh.status_mov_id     = 2                  " +   
					"    AND lt.end_date          is null              " +   
					"    AND gh.end_date          is null              " +   
					"    AND lt.tipo_lote_id      = 2                  " +   
					"    AND gh.modelo            = trim(:p_chassi)    " +
					"    AND gh.garantia_id       = :p_garantia        " ; 
		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		query.setParameter("p_garantia", garantia);
		query.setParameter("p_chassi", chassi);
		
		List results = new ArrayList();	
		try {
			Iterator histChassiParecer = query.list().iterator();
			
			while ( histChassiParecer.hasNext() ) {
			
				RelatorioHistChassiParecerVO relatorioHistChassiParecer = new RelatorioHistChassiParecerVO();
				Object[] row = (Object[]) histChassiParecer.next();
				
				relatorioHistChassiParecer.setParecerAnalista((String)row[0]);
				            
				results.add(relatorioHistChassiParecer);
			}		
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		
		return results;	
	}		
	
	/** Lista os dados para o Relatório Consulta Histórico Chassi, totais da garantia, peças e terceiros. 
	 * 
	 * @param chassi
	 * 	<code>chassi</code> chassi
	 * 
	 * @return list
	 * 	<code>list</code> lista de dados do Relatório Consulta Histórico Chassi, totais da garantia, peças e terceiros.  
	 * 
	 * @throws DaoException
	 */	
	public List listRelatorioHistChassiTotais(String chassi) throws DaoException{
		
		String sql = "SELECT sum(nvl(get_vlmobra(gh.garantia_id),0))  vlGarantia                  " +
					"      , sum(nvl(get_vlpeca(gh.garantia_id),0))   vlPecas                     " +
					"      , sum(gh.vl_servico_terceiro)              vlTerceiros                 " +
					"      , re.vlRevisao                             vlRevisao                   " +
					"   FROM ym_sg_lote                lt                                         " +
					"      , ym_sg_status_lote         sl                                         " +
					"      , ym_sg_garantia_header     gh                                         " +
					"      , (SELECT yc.modelo modelo                                             " +
					"              , sum(nvl(nf.valor_nf_sv,0) + nvl(nf.valor_nf_pc,0)) vlRevisao " +
					"           FROM ym_sg_lote                lt                                 " +
					"              , ym_sg_status_lote         sl                                 " +
					"              , ym_sg_cupom               yc                                 " +
					"              , ym_sg_cupom_nota          cn                                 " +
					"              , ym_sg_nota                nf                                 " +
					"          WHERE lt.status_lote_id = sl.status_lote_id                        " +
					"            AND lt.lote_id        = yc.lote_id                               " +
					"            AND lt.status_lote_id in (9,11)                                  " +
					"            AND yc.status_mov_id  = 2                                        " +
					"            AND yc.cupom_id       = cn.cupom_id                              " +
					"            AND cn.nota_id        = nf.nota_id                               " +
					"            AND lt.end_date       is null                                    " +
					"            AND yc.end_date       is null                                    " +
					"            AND cn.end_date       is null                                    " +
					"            AND nf.end_date       is null                                    " +
					"            AND lt.tipo_lote_id   = 1                                        " +
					"            GROUP BY modelo) re                                              " +
					"  WHERE lt.status_lote_id  = sl.status_lote_id                               " +
					"    AND lt.lote_id         = gh.lote_id                                      " +
					"    AND gh.modelo          = re.modelo (+)                                   " +
					"    AND lt.status_lote_id  in (9,11)                                         " +
					"    AND gh.status_mov_id   = 2                                               " +
					"    AND lt.end_date        is null                                           " +
					"    AND gh.end_date        is NULL                                           " +
					"    AND gh.modelo          = trim(:p_chassi)                                 " +
					" GROUP BY vlRevisao                                                          " ;
		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		query.setParameter("p_chassi", chassi);
		
		List results = new ArrayList();	
		try {
			Iterator histChassiTotais = query.list().iterator();
			
			while ( histChassiTotais.hasNext() ) {
			
				RelatorioHistChassiTotalVO relatorioHistChassiTotais = new RelatorioHistChassiTotalVO();
				Object[] row = (Object[]) histChassiTotais.next();
				
				relatorioHistChassiTotais.setVlGarantia((BigDecimal)row[0]);
				relatorioHistChassiTotais.setVlPecas((BigDecimal)row[1]);
				relatorioHistChassiTotais.setVlTerceiros((BigDecimal)row[2]);
				relatorioHistChassiTotais.setVlRevisao((BigDecimal)row[3]);
	            
				results.add(relatorioHistChassiTotais);
			}		
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		return results;	
	}	

	/** Lista os dados para o Relatório de Garantia e Peças. 
	 * 
	 * @param empresa
	 * 	<code>empresa</code> empresa
	 * 
	 * @param ano
	 * 	<code>ano</code> ano
	 * 
	 * @param linha
	 * 	<code>linha</code> linha
	 * 
	 * @return list
	 * 	<code>list</code> lista de dados do Relatório de Garantia e Peças.  
	 * 
	 * @throws DaoException
	 */		
	public List listRelatorioGarantiaPecas(Long empresa, Long linha, String ano) throws DaoException{
		
		String sql =" SELECT to_char(ys.ano)                      ano                                                                                          " +
					"       , 'VL DOLAR'                          topico                                                                                       " +
					"       , max(decode(mes,1,ys.valor_real,0))  m01                                                                                          " +
					"       , max(decode(mes,2,ys.valor_real,0))  m02                                                                                          " +
					"       , max(decode(mes,3,ys.valor_real,0))  m03                                                                                          " +
					"       , max(decode(mes,4,ys.valor_real,0))  m04                                                                                          " +
					"       , max(decode(mes,5,ys.valor_real,0))  m05                                                                                          " +
					"       , max(decode(mes,6,ys.valor_real,0))  m06                                                                                          " +
					"       , max(decode(mes,7,ys.valor_real,0))  m07                                                                                          " +
					"       , max(decode(mes,8,ys.valor_real,0))  m08                                                                                          " +
					"       , max(decode(mes,9,ys.valor_real,0))  m09                                                                                          " +
					"       , max(decode(mes,10,ys.valor_real,0)) m10                                                                                          " +
					"       , max(decode(mes,11,ys.valor_real,0)) m11                                                                                          " +
					"       , max(decode(mes,12,ys.valor_real,0)) m12                                                                                          " +
					"    FROM ym_sg_moeda ys                                                                                                                   " +
					"   WHERE trunc(ys.start_date) <= sysdate                                                                                                  " +
					"     AND nvl(trunc(ys.end_date), sysdate + 1) >= sysdate                                                                                  " +
					"     AND to_char(ys.ano) = :p_ano                                                                                                         " +
					"   GROUP BY to_char(ys.ano)                                                                                                               " +
					"  UNION ALL                                                                                                                               " +
					"  SELECT to_char(yl.fechamento_date, 'YYYY')                  ano                                                                         " +
					"       , 'VL PCA GARAN'                                       topico                                                                      " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),1,(gl.vl_peca_garantia*gl.quantidade),0))   m01                           " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),2,(gl.vl_peca_garantia*gl.quantidade),0))   m02                           " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),3,(gl.vl_peca_garantia*gl.quantidade),0))   m03                           " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),4,(gl.vl_peca_garantia*gl.quantidade),0))   m04                           " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),5,(gl.vl_peca_garantia*gl.quantidade),0))   m05                           " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),6,(gl.vl_peca_garantia*gl.quantidade),0))   m06                           " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),7,(gl.vl_peca_garantia*gl.quantidade),0))   m07                           " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),8,(gl.vl_peca_garantia*gl.quantidade),0))   m08                           " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),9,(gl.vl_peca_garantia*gl.quantidade),0))   m09                           " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),10,(gl.vl_peca_garantia*gl.quantidade),0))  m10                           " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),11,(gl.vl_peca_garantia*gl.quantidade),0))  m11                           " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),12,(gl.vl_peca_garantia*gl.quantidade),0))  m12                           " +
					"    FROM ym_sg_lote               yl                                                                                                      " +
					"       , ym_sg_garantia_header    gh                                                                                                      " +
					"       , ym_sg_garantia_line	   gl                                                                                                      " +
					"       , ym_sg_faturamento        ft                                                                                                      " +
					"   WHERE yl.lote_id             = gh.lote_id                                                                                              " +
					"     AND gh.garantia_id         = gl.garantia_id                                                                                          " +
					"     AND ft.chassi              = gh.modelo                                                                                               " +
					"     AND yl.status_lote_id      in (9, 11)                                                                                                " +
					"     AND gh.status_mov_id       =  2                                                                                                      " +
					"     AND to_char(yl.fechamento_date, 'YYYY')  = :p_ano                                                                                    " +
					"     AND yl.linha_id	         = :p_linha                                                                                                " +
					"     AND ft.organization_id	 = :p_empresa                                                                                              " +
					"     GROUP BY to_char(yl.fechamento_date, 'YYYY')                                                                                         " +
					"  UNION ALL                                                                                                                               " +
					"  SELECT to_char(yl.fechamento_date, 'YYYY')                  ano                                                                         " +
					"       , 'VL MOBRA PECA'                                      topico                                                                      " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),1,ys.vl_servico,0))   m01                                                 " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),2,ys.vl_servico,0))   m02                                                 " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),3,ys.vl_servico,0))   m03                                                 " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),4,ys.vl_servico,0))   m04                                                 " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),5,ys.vl_servico,0))   m05                                                 " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),6,ys.vl_servico,0))   m06                                                 " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),7,ys.vl_servico,0))   m07                                                 " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),8,ys.vl_servico,0))   m08                                                 " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),9,ys.vl_servico,0))   m09                                                 " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),10,ys.vl_servico,0))  m10                                                 " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),11,ys.vl_servico,0))  m11                                                 " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),12,ys.vl_servico,0))  m12                                                 " +
					"    FROM ym_sg_lote               yl                                                                                                      " +
					"       , ym_sg_garantia_header    gh                                                                                                      " +
					"       , ym_sg_servico_grupo	   ys                                                                                                      " +
					"       , ym_sg_faturamento        ft                                                                                                      " +
					"   WHERE yl.lote_id              = gh.lote_id                                                                                             " +
					"     AND gh.servico_grupo_id     = ys.servico_grupo_id                                                                                    " +
					"     AND ft.chassi               = gh.modelo                                                                                              " +
					"     AND yl.status_lote_id      in (9, 11)                                                                                                " +
					"     AND to_char(yl.fechamento_date, 'YYYY')  = :p_ano                                                                                    " +
					"     AND yl.linha_id	          = :p_linha                                                                                               " +
					"     AND ft.organization_id	  = :p_empresa                                                                                             " +
					"     GROUP BY to_char(yl.fechamento_date, 'YYYY')                                                                                         " +
					"  UNION ALL                                                                                                                               " +
					"  SELECT to_char(yl.fechamento_date, 'YYYY')                  ano                                                                         " +
					"       , 'VL MOBRA TERC'                                      topico                                                                      " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),1,gh.vl_servico_terceiro,0))   m01                                        " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),2,gh.vl_servico_terceiro,0))   m02                                        " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),3,gh.vl_servico_terceiro,0))   m03                                        " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),4,gh.vl_servico_terceiro,0))   m04                                        " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),5,gh.vl_servico_terceiro,0))   m05                                        " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),6,gh.vl_servico_terceiro,0))   m06                                        " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),7,gh.vl_servico_terceiro,0))   m07                                        " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),8,gh.vl_servico_terceiro,0))   m08                                        " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),9,gh.vl_servico_terceiro,0))   m09                                        " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),10,gh.vl_servico_terceiro,0))  m10                                        " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),11,gh.vl_servico_terceiro,0))  m11                                        " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),12,gh.vl_servico_terceiro,0))  m12                                        " +
					"    FROM ym_sg_lote              yl                                                                                                       " +
					"       , ym_sg_garantia_header	gh                                                                                                         " +
					"       , ym_sg_faturamento       ft                                                                                                       " +
					"   WHERE yl.lote_id           = gh.lote_id                                                                                                " +
					"     AND ft.chassi            = gh.modelo                                                                                                 " +
					"     AND yl.status_lote_id    in (9, 11)                                                                                                  " +
					"     AND gh.status_mov_id     =  2                                                                                                        " +
					"     AND to_char(yl.fechamento_date, 'YYYY') = :p_ano                                                                                     " +
					"     AND yl.linha_id	       = :p_linha                                                                                                  " +
					"     AND ft.organization_id   = :p_empresa                                                                                                " +
					"   GROUP BY to_char(yl.fechamento_date, 'YYYY')                                                                                           " ;
		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		query.setParameter("p_empresa", empresa);
		query.setParameter("p_linha", linha);
		query.setParameter("p_ano", ano);
		
		List results = new ArrayList();	
		try {
			Iterator garantiaPecasIt = query.list().iterator();
			
			while ( garantiaPecasIt.hasNext() ) {
			
				RelatorioGarantiaPecasVO relatorioGarantiaPecas = new RelatorioGarantiaPecasVO();
				Object[] row = (Object[]) garantiaPecasIt.next();
				
				relatorioGarantiaPecas.setAno((String)row[0]);
				relatorioGarantiaPecas.setTopico((String)row[1]);
				relatorioGarantiaPecas.setM01(Double.valueOf(row[2].toString()));
				relatorioGarantiaPecas.setM02(Double.valueOf(row[3].toString()));
				relatorioGarantiaPecas.setM03(Double.valueOf(row[4].toString()));
				relatorioGarantiaPecas.setM04(Double.valueOf(row[5].toString()));
				relatorioGarantiaPecas.setM05(Double.valueOf(row[6].toString()));
				relatorioGarantiaPecas.setM06(Double.valueOf(row[7].toString()));
				relatorioGarantiaPecas.setM07(Double.valueOf(row[8].toString()));
				relatorioGarantiaPecas.setM08(Double.valueOf(row[9].toString()));
				relatorioGarantiaPecas.setM09(Double.valueOf(row[10].toString()));
				relatorioGarantiaPecas.setM10(Double.valueOf(row[11].toString()));
				relatorioGarantiaPecas.setM11(Double.valueOf(row[12].toString()));
				relatorioGarantiaPecas.setM12(Double.valueOf(row[13].toString()));
	
				results.add(relatorioGarantiaPecas);
			}		
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		return results;	
	}	

	/** Lista os dados para o Relatório de Garantia e Peças. 
	 * 
	 * @param empresa
	 * 	<code>empresa</code> empresa
	 * 
	 * @param ano
	 * 	<code>ano</code> ano
	 * 
	 * @param linha
	 * 	<code>linha</code> linha
	 * 
	 * @return list
	 * 	<code>list</code> lista de dados do Relatório de Garantia e Peças.  
	 * 
	 * @throws DaoException
	 */		
	public List listRelatorioGPOrcPcsMobra(Long empresa, Long linha, String ano) throws DaoException{
		
		String sql = "  SELECT to_char(yo.data,'YYYY')                 ano                                                            " +
					"       , 'ORC.PCS + MO'                           topico                                                         " +
					"       , sum(decode(to_number(to_char(yo.data,'MM')),1,nvl(yo.valor_garantia,0)+ nvl(yo.valor_peca,0), 0))  m01  " +
					"       , sum(decode(to_number(to_char(yo.data,'MM')),2,nvl(yo.valor_garantia,0)+ nvl(yo.valor_peca,0),0))   m02  " +
					"       , sum(decode(to_number(to_char(yo.data,'MM')),3,nvl(yo.valor_garantia,0)+ nvl(yo.valor_peca,0),0))   m03  " +
					"       , sum(decode(to_number(to_char(yo.data,'MM')),4,nvl(yo.valor_garantia,0)+ nvl(yo.valor_peca,0),0))   m04  " +
					"       , sum(decode(to_number(to_char(yo.data,'MM')),5,nvl(yo.valor_garantia,0)+ nvl(yo.valor_peca,0),0))   m05  " +
					"       , sum(decode(to_number(to_char(yo.data,'MM')),6,nvl(yo.valor_garantia,0)+ nvl(yo.valor_peca,0),0))   m06  " +
					"       , sum(decode(to_number(to_char(yo.data,'MM')),7,nvl(yo.valor_garantia,0)+ nvl(yo.valor_peca,0),0))   m07  " +
					"       , sum(decode(to_number(to_char(yo.data,'MM')),8,nvl(yo.valor_garantia,0)+ nvl(yo.valor_peca,0),0))   m08  " +
					"       , sum(decode(to_number(to_char(yo.data,'MM')),9,nvl(yo.valor_garantia,0)+ nvl(yo.valor_peca,0),0))   m09  " +
					"       , sum(decode(to_number(to_char(yo.data,'MM')),10,nvl(yo.valor_garantia,0)+ nvl(yo.valor_peca,0),0))  m10  " +
					"       , sum(decode(to_number(to_char(yo.data,'MM')),11,nvl(yo.valor_garantia,0)+ nvl(yo.valor_peca,0),0))  m11  " +
					"       , sum(decode(to_number(to_char(yo.data,'MM')),12,nvl(yo.valor_garantia,0)+ nvl(yo.valor_peca,0),0))  m12  " +
					"    FROM ym_sg_orcamento    yo                                                                                   " +
					"       , ym_sg_faturamento  ft                                                                                   " + 
					"   WHERE yo.end_date is null                                                                                     " + 
					"     AND ft.organization_id  = yo.organization_id                                                                " + 
					"     AND to_char(yo.data,'YYYY') = :p_ano                                                                        " + 
					"     AND yo.linha_id             = :p_linha                                                                      " + 
					"     AND ft.organization_id	  = :p_empresa                                                                    " + 
					"   GROUP BY to_char(yo.data,'YYYY')                                                                              " ;    

		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		query.setParameter("p_empresa", empresa);
		query.setParameter("p_linha", linha);
		query.setParameter("p_ano", ano);
		
		List results = new ArrayList();	
		try {
			Iterator garantiaPecasIt = query.list().iterator();
			
			while ( garantiaPecasIt.hasNext() ) {
			
				RelatorioGarPecasSubReportVO relatorioGarantiaPecas = new RelatorioGarPecasSubReportVO();
				Object[] row = (Object[]) garantiaPecasIt.next();
				
				relatorioGarantiaPecas.setAno((String)row[0]);
				relatorioGarantiaPecas.setTopico(String.valueOf(row[1].toString()));
				relatorioGarantiaPecas.setM01(Double.valueOf(row[2].toString()));
				relatorioGarantiaPecas.setM02(Double.valueOf(row[3].toString()));
				relatorioGarantiaPecas.setM03(Double.valueOf(row[4].toString()));
				relatorioGarantiaPecas.setM04(Double.valueOf(row[5].toString()));
				relatorioGarantiaPecas.setM05(Double.valueOf(row[6].toString()));
				relatorioGarantiaPecas.setM06(Double.valueOf(row[7].toString()));
				relatorioGarantiaPecas.setM07(Double.valueOf(row[8].toString()));
				relatorioGarantiaPecas.setM08(Double.valueOf(row[9].toString()));
				relatorioGarantiaPecas.setM09(Double.valueOf(row[10].toString()));
				relatorioGarantiaPecas.setM10(Double.valueOf(row[11].toString()));
				relatorioGarantiaPecas.setM11(Double.valueOf(row[12].toString()));
				relatorioGarantiaPecas.setM12(Double.valueOf(row[13].toString()));
	
				results.add(relatorioGarantiaPecas);
			}		
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		return results;	
	}	

	/** Lista os dados para o Relatório de Garantia e Peças. 
	 * 
	 * @param empresa
	 * 	<code>empresa</code> empresa
	 * 
	 * @param ano
	 * 	<code>ano</code> ano
	 * 
	 * @param linha
	 * 	<code>linha</code> linha
	 * 
	 * @return list
	 * 	<code>list</code> lista de dados do Relatório de Garantia e Peças.  
	 * 
	 * @throws DaoException
	 */		
	public List listRelatorioGPRevisao(Long empresa, Long linha, String ano) throws DaoException{
		
		String sql = " SELECT to_char(yl.fechamento_date,'YYYY')                         ano                                                    " +
					"       , 'QT REVISAO'||trim(to_char(yr.numero_revisao,'99'))        topico                                                 " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),1,1,0))   m01                                              " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),2,1,0))   m02                                              " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),3,1,0))   m03                                              " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),4,1,0))   m04                                              " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),5,1,0))   m05                                              " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),6,1,0))   m06                                              " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),7,1,0))   m07                                              " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),8,1,0))   m08                                              " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),9,1,0))   m09                                              " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),10,1,0))  m10                                              " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),11,1,0))  m11                                              " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),12,1,0))  m12                                              " +
					"       , '1'                                                              ordem                                            " +
					"    FROM ym_sg_lote         yl                                                                                             " +
					"       , ym_sg_cupom	     yc                                                                                             " +
					"       , ym_sg_revisao	     yr                                                                                             " +
					"       , ym_sg_faturamento  ft                                                                                             " +  
					"   WHERE yl.lote_id            = yc.lote_id                                                                                " +
					"     AND yc.revisao_id         = yr.revisao_id                                                                             " +
					"     AND ft.chassi             = yc.modelo                                                                                 " +
					"     AND yr.numero_revisao in (1,2,3)                                                                                      " +
					"     AND yl.status_lote_id      in (9, 11)                                                                                 " +
					"     AND yc.status_mov_id      = 2                                                                                         " +
					"     AND to_char(yl.fechamento_date,'YYYY')  = :p_ano                                                                      " +
					"     AND yl.linha_id	                      = :p_linha                                                                    " +
					"     AND ft.organization_id	              = :p_empresa                                                                  " +
					"     AND yr.numero_revisao < 4                                                                                             " +
					"   GROUP BY to_char(yl.fechamento_date,'YYYY')                                                                             " +
					"         ,  'QT REVISAO'||trim(to_char(yr.numero_revisao,'99'))                                                            " +
					"  UNION ALL                                                                                                                " +
					"  SELECT to_char(yl.fechamento_date,'YYYY')                        ano                                                     " +
					"       , 'VL REVISAO'||trim(to_char(yr.numero_revisao,'99'))      topico                                                   " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),1,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m01  " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),2,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m02  " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),3,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m03  " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),4,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m04  " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),5,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m05  " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),6,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m06  " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),7,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m07  " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),8,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m08  " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),9,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m09  " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),10,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))  m10  " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),11,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))  m11  " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),12,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))  m12  " +
					"       , '2'                                                              ordem                                            " +
					"    FROM ym_sg_lote         yl                                                                                             " +
					"       , ym_sg_cupom	     yc                                                                                             " +
					"       , ym_sg_revisao	     yr                                                                                             " +
					"       , ym_sg_faturamento  ft                                                                                             " +
					"   WHERE yl.lote_id            = yc.lote_id                                                                                " +
					"     AND yc.revisao_id         = yr.revisao_id                                                                             " +
					"     AND ft.chassi             = yc.modelo                                                                                 " +
					"     AND yr.numero_revisao     in (1,2,3)                                                                                  " +
					"     AND yl.status_lote_id     in (9, 11)                                                                                  " +
					"     AND yc.status_mov_id      =  2                                                                                        " +
					"     AND to_char(yl.fechamento_date,'YYYY') = :p_ano                                                                       " +
					"     AND yl.linha_id	                     = :p_linha                                                                     " +
					"     AND ft.organization_id	             = :p_empresa                                                                   " +
					"     AND yr.numero_revisao < 4                                                                                             " +
					"   GROUP BY to_char(yl.fechamento_date,'YYYY')                                                                             " +
					"          , 'VL REVISAO'||trim(to_char(yr.numero_revisao,'99'))                                                            " +
					"  UNION ALL                                                                                                                " +
					"  SELECT to_char(yl.fechamento_date,'YYYY')                   ano                                                          " +
					"       , 'TOT REVISOES'                                       topico                                                       " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),1,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m01  " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),2,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m02  " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),3,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m03  " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),4,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m04  " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),5,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m05  " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),6,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m06  " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),7,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m07  " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),8,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m08  " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),9,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m09  " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),10,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))  m10  " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),11,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))  m11  " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),12,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))  m12  " +
					"       , '3'                                                              ordem                                            " +
					"    FROM ym_sg_lote         yl                                                                                             " +
					"       , ym_sg_cupom        yc                                                                                             " +
					"       , ym_sg_revisao	   yr                                                                                               " +
					"       , ym_sg_faturamento  ft                                                                                             " +
					"   WHERE yl.lote_id            = yc.lote_id                                                                                " +
					"     AND yc.revisao_id         = yr.revisao_id                                                                             " +
					"     AND ft.chassi             = yc.modelo                                                                                 " +
					"     AND yl.status_lote_id      in (9, 11)                                                                                 " + 
					"     AND yr.numero_revisao  in (1,2,3)                                                                                     " + 
					"     AND to_char(yl.fechamento_date,'YYYY') = :p_ano                                                                       " + 
					"     AND yl.linha_id	                     = :p_linha                                                                     " + 
					"     AND ft.organization_id	             = :p_empresa                                                                   " + 
					"     AND yc.status_mov_id  = 2                                                                                             " + 
					"     AND yr.numero_revisao < 4                                                                                             " + 
					"   GROUP BY to_char(yl.fechamento_date,'YYYY')                                                                             " + 
					"          , 'TOT REVISOES'                                                                                                 " +                       
					"   ORDER BY ordem, topico                                                                                                  " ; 
		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		query.setParameter("p_empresa", empresa);
		query.setParameter("p_linha", linha);
		query.setParameter("p_ano", ano);
		
		List results = new ArrayList();	
		try {
			Iterator garantiaPecasIt = query.list().iterator();
			
			while ( garantiaPecasIt.hasNext() ) {
			
				RelatorioGarPecasSubReportVO relatorioGarantiaPecas = new RelatorioGarPecasSubReportVO();
				Object[] row = (Object[]) garantiaPecasIt.next();
				
				relatorioGarantiaPecas.setAno((String)row[0]);
				relatorioGarantiaPecas.setTopico((String)row[1]);
				relatorioGarantiaPecas.setM01(Double.valueOf(row[2].toString()));
				relatorioGarantiaPecas.setM02(Double.valueOf(row[3].toString()));
				relatorioGarantiaPecas.setM03(Double.valueOf(row[4].toString()));
				relatorioGarantiaPecas.setM04(Double.valueOf(row[5].toString()));
				relatorioGarantiaPecas.setM05(Double.valueOf(row[6].toString()));
				relatorioGarantiaPecas.setM06(Double.valueOf(row[7].toString()));
				relatorioGarantiaPecas.setM07(Double.valueOf(row[8].toString()));
				relatorioGarantiaPecas.setM08(Double.valueOf(row[9].toString()));
				relatorioGarantiaPecas.setM09(Double.valueOf(row[10].toString()));
				relatorioGarantiaPecas.setM10(Double.valueOf(row[11].toString()));
				relatorioGarantiaPecas.setM11(Double.valueOf(row[12].toString()));
				relatorioGarantiaPecas.setM12(Double.valueOf(row[13].toString()));
	
				results.add(relatorioGarantiaPecas);
			}		
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		return results;	
	}	

	/** Lista os dados para o Relatório de Garantia e Peças. 
	 * 
	 * @param empresa
	 * 	<code>empresa</code> empresa
	 * 
	 * @param ano
	 * 	<code>ano</code> ano
	 * 
	 * @param linha
	 * 	<code>linha</code> linha
	 * 
	 * @return list
	 * 	<code>list</code> lista de dados do Relatório de Garantia e Peças.  
	 * 
	 * @throws DaoException
	 */		
	public List listRelatorioGPOrcRev(Long empresa, Long linha, String ano) throws DaoException{
		
		String sql = " SELECT to_char(yo.data,'YYYY')                   ano                                   " +
					"       , 'ORC. REVISAO'                            topico                                " +
					"       , sum(decode(to_number(to_char(yo.data,'MM')),1,nvl(yo.valor_revisao,0),0))   m01 " +
					"       , sum(decode(to_number(to_char(yo.data,'MM')),2,nvl(yo.valor_revisao,0),0))   m02 " +
					"       , sum(decode(to_number(to_char(yo.data,'MM')),3,nvl(yo.valor_revisao,0),0))   m03 " +
					"       , sum(decode(to_number(to_char(yo.data,'MM')),4,nvl(yo.valor_revisao,0),0))   m04 " +
					"       , sum(decode(to_number(to_char(yo.data,'MM')),5,nvl(yo.valor_revisao,0),0))   m05 " +
					"       , sum(decode(to_number(to_char(yo.data,'MM')),6,nvl(yo.valor_revisao,0),0))   m06 " +
					"       , sum(decode(to_number(to_char(yo.data,'MM')),7,nvl(yo.valor_revisao,0),0))   m07 " +
					"       , sum(decode(to_number(to_char(yo.data,'MM')),8,nvl(yo.valor_revisao,0),0))   m08 " +
					"       , sum(decode(to_number(to_char(yo.data,'MM')),9,nvl(yo.valor_revisao,0),0))   m09 " +
					"       , sum(decode(to_number(to_char(yo.data,'MM')),10,nvl(yo.valor_revisao,0),0))  m10 " +
					"       , sum(decode(to_number(to_char(yo.data,'MM')),11,nvl(yo.valor_revisao,0),0))  m11 " +
					"       , sum(decode(to_number(to_char(yo.data,'MM')),12,nvl(yo.valor_revisao,0),0))  m12 " +
					"    FROM ym_sg_orcamento    yo                                                           " +
					"       , ym_sg_faturamento  ft                                                           " +
					"   WHERE yo.end_date is null                                                             " +
					"     AND ft.organization_id       = yo.organization_id                                   " +
					"     AND to_char(yo.data,'YYYY')  = :p_ano                                               " +
					"     AND yo.linha_id              = :p_linha                                             " +
					"     AND ft.organization_id	   = :p_empresa                                           " +
					"   GROUP BY to_char(yo.data,'YYYY')                                                      " +
					"          , 'ORC. REVISAO'                                                               " ;
		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		query.setParameter("p_empresa", empresa);
		query.setParameter("p_linha", linha);
		query.setParameter("p_ano", ano);
		
		List results = new ArrayList();	
		try {
			Iterator garantiaPecasIt = query.list().iterator();
			
			while ( garantiaPecasIt.hasNext() ) {
			
				RelatorioGarPecasSubReportVO relatorioGarantiaPecas = new RelatorioGarPecasSubReportVO();
				Object[] row = (Object[]) garantiaPecasIt.next();
				
				relatorioGarantiaPecas.setAno((String)row[0]);
				relatorioGarantiaPecas.setTopico((String)row[1].toString());
				relatorioGarantiaPecas.setM01(Double.valueOf(row[2].toString()));
				relatorioGarantiaPecas.setM02(Double.valueOf(row[3].toString()));
				relatorioGarantiaPecas.setM03(Double.valueOf(row[4].toString()));
				relatorioGarantiaPecas.setM04(Double.valueOf(row[5].toString()));
				relatorioGarantiaPecas.setM05(Double.valueOf(row[6].toString()));
				relatorioGarantiaPecas.setM06(Double.valueOf(row[7].toString()));
				relatorioGarantiaPecas.setM07(Double.valueOf(row[8].toString()));
				relatorioGarantiaPecas.setM08(Double.valueOf(row[9].toString()));
				relatorioGarantiaPecas.setM09(Double.valueOf(row[10].toString()));
				relatorioGarantiaPecas.setM10(Double.valueOf(row[11].toString()));
				relatorioGarantiaPecas.setM11(Double.valueOf(row[12].toString()));
				relatorioGarantiaPecas.setM12(Double.valueOf(row[13].toString()));
	
				results.add(relatorioGarantiaPecas);
			}		
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		return results;	
	}	

	/** Lista os dados para o Relatório de Garantia e Peças. 
	 * 
	 * @param empresa
	 * 	<code>empresa</code> empresa
	 * 
	 * @param ano
	 * 	<code>ano</code> ano
	 * 
	 * @param linha
	 * 	<code>linha</code> linha
	 * 
	 * @return list
	 * 	<code>list</code> lista de dados do Relatório de Garantia e Peças.  
	 * 
	 * @throws DaoException
	 */		
	public List listRelatorioGPOrcTotal(Long empresa, Long linha, String ano) throws DaoException{
		
		String sql = "  SELECT to_char(yo.data,'YYYY')                   ano                                                                                   " +
					"       , 'ORC. TOTAL'                               topico                                                                                " +
					"       , sum(decode(to_number(to_char(yo.data,'MM')),1,nvl(yo.valor_garantia,0)+ nvl(yo.valor_peca,0) + nvl(yo.valor_revisao,0),0)) m01   " +
					"       , sum(decode(to_number(to_char(yo.data,'MM')),2,nvl(yo.valor_garantia,0)+ nvl(yo.valor_peca,0) + nvl(yo.valor_revisao,0),0)) m02   " +
					"       , sum(decode(to_number(to_char(yo.data,'MM')),3,nvl(yo.valor_garantia,0)+ nvl(yo.valor_peca,0) + nvl(yo.valor_revisao,0),0)) m03   " +
					"       , sum(decode(to_number(to_char(yo.data,'MM')),4,nvl(yo.valor_garantia,0)+ nvl(yo.valor_peca,0) + nvl(yo.valor_revisao,0),0)) m04   " +
					"       , sum(decode(to_number(to_char(yo.data,'MM')),5,nvl(yo.valor_garantia,0)+ nvl(yo.valor_peca,0) + nvl(yo.valor_revisao,0),0)) m05   " +
					"       , sum(decode(to_number(to_char(yo.data,'MM')),6,nvl(yo.valor_garantia,0)+ nvl(yo.valor_peca,0) + nvl(yo.valor_revisao,0),0)) m06   " +
					"       , sum(decode(to_number(to_char(yo.data,'MM')),7,nvl(yo.valor_garantia,0)+ nvl(yo.valor_peca,0) + nvl(yo.valor_revisao,0),0)) m07   " +
					"       , sum(decode(to_number(to_char(yo.data,'MM')),8,nvl(yo.valor_garantia,0)+ nvl(yo.valor_peca,0) + nvl(yo.valor_revisao,0),0)) m08   " +
					"       , sum(decode(to_number(to_char(yo.data,'MM')),9,nvl(yo.valor_garantia,0)+ nvl(yo.valor_peca,0) + nvl(yo.valor_revisao,0),0)) m09   " +
					"       , sum(decode(to_number(to_char(yo.data,'MM')),10,nvl(yo.valor_garantia,0)+ nvl(yo.valor_peca,0)+ nvl(yo.valor_revisao,0),0)) m10   " +
					"       , sum(decode(to_number(to_char(yo.data,'MM')),11,nvl(yo.valor_garantia,0)+ nvl(yo.valor_peca,0)+ nvl(yo.valor_revisao,0),0)) m11   " +
					"       , sum(decode(to_number(to_char(yo.data,'MM')),12,nvl(yo.valor_garantia,0)+ nvl(yo.valor_peca,0)+ nvl(yo.valor_revisao,0),0)) m12   " +
					"    FROM ym_sg_orcamento    yo                                                                                                            " +
					"       , ym_sg_faturamento  ft                                                                                                            " +
					"   WHERE yo.end_date is null                                                                                                              " +
					"     AND yo.organization_id  = ft.organization_id                                                                                         " +
					"     AND to_char(yo.data,'YYYY')  = :p_ano                                                                                                " +
					"     AND yo.linha_id              = :p_linha                                                                                              " +
					"     AND ft.organization_id	   = :p_empresa                                                                                            " +
					"   GROUP BY to_char(yo.data,'YYYY')                                                                                                       " +
					"          , 'ORC. TOTAL'                                                                                                                  " ;

		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		query.setParameter("p_empresa", empresa);
		query.setParameter("p_linha", linha);
		query.setParameter("p_ano", ano);
		
		List results = new ArrayList();	
		try {
			Iterator garantiaPecasIt = query.list().iterator();
			
			while ( garantiaPecasIt.hasNext() ) {
			
				RelatorioGarPecasSubReportVO relatorioGarantiaPecas = new RelatorioGarPecasSubReportVO();
				Object[] row = (Object[]) garantiaPecasIt.next();
				
				relatorioGarantiaPecas.setAno((String)row[0]);
				relatorioGarantiaPecas.setTopico((String)row[1].toString());
				relatorioGarantiaPecas.setM01(Double.valueOf(row[2].toString()));
				relatorioGarantiaPecas.setM02(Double.valueOf(row[3].toString()));
				relatorioGarantiaPecas.setM03(Double.valueOf(row[4].toString()));
				relatorioGarantiaPecas.setM04(Double.valueOf(row[5].toString()));
				relatorioGarantiaPecas.setM05(Double.valueOf(row[6].toString()));
				relatorioGarantiaPecas.setM06(Double.valueOf(row[7].toString()));
				relatorioGarantiaPecas.setM07(Double.valueOf(row[8].toString()));
				relatorioGarantiaPecas.setM08(Double.valueOf(row[9].toString()));
				relatorioGarantiaPecas.setM09(Double.valueOf(row[10].toString()));
				relatorioGarantiaPecas.setM10(Double.valueOf(row[11].toString()));
				relatorioGarantiaPecas.setM11(Double.valueOf(row[12].toString()));
				relatorioGarantiaPecas.setM12(Double.valueOf(row[13].toString()));
	
				results.add(relatorioGarantiaPecas);
			}		
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		return results;	
	}	

	/** Lista os dados para o Relatório de Garantia e Peças. 
	 * 
	 * @param empresa
	 * 	<code>empresa</code> empresa
	 * 
	 * @param ano
	 * 	<code>ano</code> ano
	 * 
	 * @param linha
	 * 	<code>linha</code> linha
	 * 
	 * @return list
	 * 	<code>list</code> lista de dados do Relatório de Garantia e Peças.  
	 * 
	 * @throws DaoException
	 */		
	public List listRelatorioGPVlFatur(Long empresa, Long linha, String ano) throws DaoException{
		
		String sql = " SELECT to_char(yf.data_nf, 'YYYY')                  ano                           " +
					"       , 'VL FATUR'                                   topico                        " +
					"       , sum(decode(to_number(to_char(yf.data_nf,'MM')),1,yf.vl_produto,0))   m01   " +
					"       , sum(decode(to_number(to_char(yf.data_nf,'MM')),2,yf.vl_produto,0))   m02   " +
					"       , sum(decode(to_number(to_char(yf.data_nf,'MM')),3,yf.vl_produto,0))   m03   " +
					"       , sum(decode(to_number(to_char(yf.data_nf,'MM')),4,yf.vl_produto,0))   m04   " +
					"       , sum(decode(to_number(to_char(yf.data_nf,'MM')),5,yf.vl_produto,0))   m05   " +
					"       , sum(decode(to_number(to_char(yf.data_nf,'MM')),6,yf.vl_produto,0))   m06   " +
					"       , sum(decode(to_number(to_char(yf.data_nf,'MM')),7,yf.vl_produto,0))   m07   " +
					"       , sum(decode(to_number(to_char(yf.data_nf,'MM')),8,yf.vl_produto,0))   m08   " +
					"       , sum(decode(to_number(to_char(yf.data_nf,'MM')),9,yf.vl_produto,0))   m09   " +
					"       , sum(decode(to_number(to_char(yf.data_nf,'MM')),10,yf.vl_produto,0))  m10   " +
					"       , sum(decode(to_number(to_char(yf.data_nf,'MM')),11,yf.vl_produto,0))  m11   " +
					"       , sum(decode(to_number(to_char(yf.data_nf,'MM')),12,yf.vl_produto,0))  m12   " +
					"    FROM ym_sg_faturamento	   yf                                                    " +
					"   WHERE yf.end_date is NULL                                                        " +
					"     AND to_char(yf.data_nf, 'YYYY') = :p_ano                                       " +
					"     AND yf.linha_id	              = :p_linha                                     " +
					"     AND yf.organization_id          = :p_empresa                                   " +
					"   GROUP BY to_char(yf.data_nf, 'YYYY')                                             " +
					"          , 'VL FATUR'                                                              " ;

		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		query.setParameter("p_empresa", empresa);
		query.setParameter("p_linha", linha);
		query.setParameter("p_ano", ano);
		
		List results = new ArrayList();	
		try {
			Iterator garantiaPecasIt = query.list().iterator();
			
			while ( garantiaPecasIt.hasNext() ) {
			
				RelatorioGarPecasSubReportVO relatorioGarantiaPecas = new RelatorioGarPecasSubReportVO();
				Object[] row = (Object[]) garantiaPecasIt.next();
				
				relatorioGarantiaPecas.setAno((String)row[0]);
				relatorioGarantiaPecas.setTopico((String)row[1].toString());
				relatorioGarantiaPecas.setM01(Double.valueOf(row[2].toString()));
				relatorioGarantiaPecas.setM02(Double.valueOf(row[3].toString()));
				relatorioGarantiaPecas.setM03(Double.valueOf(row[4].toString()));
				relatorioGarantiaPecas.setM04(Double.valueOf(row[5].toString()));
				relatorioGarantiaPecas.setM05(Double.valueOf(row[6].toString()));
				relatorioGarantiaPecas.setM06(Double.valueOf(row[7].toString()));
				relatorioGarantiaPecas.setM07(Double.valueOf(row[8].toString()));
				relatorioGarantiaPecas.setM08(Double.valueOf(row[9].toString()));
				relatorioGarantiaPecas.setM09(Double.valueOf(row[10].toString()));
				relatorioGarantiaPecas.setM10(Double.valueOf(row[11].toString()));
				relatorioGarantiaPecas.setM11(Double.valueOf(row[12].toString()));
				relatorioGarantiaPecas.setM12(Double.valueOf(row[13].toString()));
	
				results.add(relatorioGarantiaPecas);
			}		
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		return results;	
	}	
	
	/** Lista os dados para o Relatório de Garantia e Peças. 
	 * 
	 * @param empresa
	 * 	<code>empresa</code> empresa
	 * 
	 * @param ano
	 * 	<code>ano</code> ano
	 * 
	 * @param linha
	 * 	<code>linha</code> linha
	 * 
	 * @return list
	 * 	<code>list</code> lista de dados do Relatório de Garantia e Peças.  
	 * 
	 * @throws DaoException
	 */		
	public List listRelatorioGPQtdes(Long empresa, Long linha, String ano) throws DaoException{
		
		String sql = " Select to_char(yl.fechamento_date, 'YYYY')                  ano                                 " +
					"       , 'QT SGs'                                             topico                   " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),1,1,0))   m01          " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),2,1,0))   m02          " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),3,1,0))   m03          " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),4,1,0))   m04          " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),5,1,0))   m05          " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),6,1,0))   m06          " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),7,1,0))   m07          " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),8,1,0))   m08          " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),9,1,0))   m09          " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),10,1,0))  m10          " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),11,1,0))  m11          " +
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),12,1,0))  m12          " +
					"    FROM ym_sg_lote               yl                                                   " +
					"       , ym_sg_garantia_header	 gh                                                     " +
					"       , ym_sg_faturamento        ft                                                   " +
					"   WHERE yl.lote_id             = gh.lote_id                                           " +
					"     AND ft.chassi              = gh.modelo                                            " +
					"     AND yl.status_lote_id      in (9,11)                                              " +
					"     AND to_char(yl.fechamento_date, 'YYYY') = :p_ano                                  " +
					"     AND yl.linha_id	                      = :p_linha                                " +
					"     AND ft.organization_id                  = :p_empresa                              " +
					"     AND gh.status_mov_id   = 2                                                        " +
					"   GROUP BY to_char(yl.fechamento_date, 'YYYY')                                        " +
					"          , 'QT SGs'                                                                   " +
					"  UNION ALL                                                                            " +
					"  SELECT ano                                                                           " +
					"       , 'QT MOTOS' topico                                                             " +
					"       , sum(m01)   m01                                                                " +
					"       , sum(m02)   m02                                                                " +
					"       , sum(m03)   m03                                                                " +
					"       , sum(m04)   m04                                                                " +
					"       , sum(m05)   m05                                                                " +
					"       , sum(m06)   m06                                                                " +
					"       , sum(m07)   m07                                                                " +
					"       , sum(m08)   m08                                                                " +
					"       , sum(m09)   m09                                                                " +
					"       , sum(m10)   m10                                                                " +
					"       , sum(m11)   m11                                                                " +
					"       , sum(m12)   m12                                                                " +
					"  FROM ( SELECT to_char(yl.fechamento_date, 'YYYY')  ano                               " +
					"              , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),1,1,0))   m01   " +
					"              , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),2,1,0))   m02   " +
					"              , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),3,1,0))   m03   " +
					"              , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),4,1,0))   m04   " +
					"              , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),5,1,0))   m05   " +
					"              , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),6,1,0))   m06   " +
					"              , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),7,1,0))   m07   " +
					"              , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),8,1,0))   m08   " +
					"              , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),9,1,0))   m09   " +
					"              , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),10,1,0))  m10   " +
					"              , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),11,1,0))  m11   " +
					"              , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),12,1,0))  m12   " +
					"           FROM ym_sg_lote               yl                                            " +
					"              , ym_sg_garantia_header	gh                                              " +
					"              , ym_sg_faturamento        ft                                            " +
					"          WHERE yl.lote_id             = gh.lote_id                                    " +
					"            AND ft.chassi              = gh.modelo                                     " +
					"            AND yl.status_lote_id      in (9, 11)                                      " +
					"            AND gh.status_mov_id       = 2                                             " +
					"            AND to_char(yl.fechamento_date, 'YYYY')  = :p_ano                          " +
					"            AND yl.linha_id	                      = :p_linha                        " +
					"            AND ft.organization_id                   = :p_empresa                      " +
					"          GROUP BY to_char(yl.fechamento_date, 'YYYY')                                 " +
					"      )                                                                                " +
					"   GROUP by ano                                                                        " +
					"          , 'QT MOTOS'                                                                 " +
					"  UNION ALL                                                                            " +
					"  SELECT to_char(yf.data_nf, 'YYYY')                   ano                             " +
					"       , 'QT FATURADA'                                 topico                          " +
					"       , sum(decode(to_number(to_char(yf.data_nf,'MM')),1,1,0))   m01                  " +
					"       , sum(decode(to_number(to_char(yf.data_nf,'MM')),2,1,0))   m02                  " +
					"       , sum(decode(to_number(to_char(yf.data_nf,'MM')),3,1,0))   m03                  " +
					"       , sum(decode(to_number(to_char(yf.data_nf,'MM')),4,1,0))   m04                  " +
					"       , sum(decode(to_number(to_char(yf.data_nf,'MM')),5,1,0))   m05                  " +
					"       , sum(decode(to_number(to_char(yf.data_nf,'MM')),6,1,0))   m06                  " +
					"       , sum(decode(to_number(to_char(yf.data_nf,'MM')),7,1,0))   m07                  " +
					"       , sum(decode(to_number(to_char(yf.data_nf,'MM')),8,1,0))   m08                  " +
					"       , sum(decode(to_number(to_char(yf.data_nf,'MM')),9,1,0))   m09                  " +
					"       , sum(decode(to_number(to_char(yf.data_nf,'MM')),10,1,0))  m10                  " +
					"       , sum(decode(to_number(to_char(yf.data_nf,'MM')),11,1,0))  m11                  " +
					"       , sum(decode(to_number(to_char(yf.data_nf,'MM')),12,1,0))  m12                  " +
					"    FROM ym_sg_faturamento	   yf                                                       " +
					"   WHERE yf.end_date is null                                                           " +
					"     AND to_char(yf.data_nf, 'YYYY') = :p_ano                                          " +
					"     AND yf.linha_id	              = :p_linha                                        " +
					"     AND yf.organization_id	      = :p_empresa                                      " +
					"   GROUP BY to_char(yf.data_nf, 'YYYY')                                                " +
					"          , 'QT FATURADA'                                                              " ;


		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		query.setParameter("p_empresa", empresa);
		query.setParameter("p_linha", linha);
		query.setParameter("p_ano", ano);
		
		List results = new ArrayList();	
		try {
			Iterator garantiaPecasIt = query.list().iterator();
			
			while ( garantiaPecasIt.hasNext() ) {
			
				RelatorioGarPecasSubReportVO relatorioGarantiaPecas = new RelatorioGarPecasSubReportVO();
				Object[] row = (Object[]) garantiaPecasIt.next();
				
				relatorioGarantiaPecas.setAno((String)row[0]);
				relatorioGarantiaPecas.setTopico((String)row[1].toString());
				relatorioGarantiaPecas.setM01(Double.valueOf(row[2].toString()));
				relatorioGarantiaPecas.setM02(Double.valueOf(row[3].toString()));
				relatorioGarantiaPecas.setM03(Double.valueOf(row[4].toString()));
				relatorioGarantiaPecas.setM04(Double.valueOf(row[5].toString()));
				relatorioGarantiaPecas.setM05(Double.valueOf(row[6].toString()));
				relatorioGarantiaPecas.setM06(Double.valueOf(row[7].toString()));
				relatorioGarantiaPecas.setM07(Double.valueOf(row[8].toString()));
				relatorioGarantiaPecas.setM08(Double.valueOf(row[9].toString()));
				relatorioGarantiaPecas.setM09(Double.valueOf(row[10].toString()));
				relatorioGarantiaPecas.setM10(Double.valueOf(row[11].toString()));
				relatorioGarantiaPecas.setM11(Double.valueOf(row[12].toString()));
				relatorioGarantiaPecas.setM12(Double.valueOf(row[13].toString()));
	
				results.add(relatorioGarantiaPecas);
			}		
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		return results;	
	}		

	/** Lista os dados para o Relatório de Garantia e Peças por Modelo. 
	 * 
	 * @param concessionaria
	 * 	<code>concessionaria</code> concessionaria
	 * 
	 * @param linha
	 * 	<code>linha</code> linha 
	 * 
	 * @param dataReferencia
	 * 	<code>ano</code> ano(mm/YYYY)
	 * 
	 * @param modelo
	 * 	<code>modelo</code> modelo
	 * 
	 * @return list
	 * 	<code>list</code> lista de dados do Relatório de Garantia e Peças por Modelo.  
	 * 
	 * @throws DaoException
	 */		
	public List listRelatorioGarantiaPecasModelo(Long concessionaria, Long linha, String dataReferencia, String modelo) throws DaoException {
		
		try {
            
            String whereConcessionaria = concessionaria.intValue() != 0 ? " AND yl.concessionaria_id = nvl(:p_concessionaria, yl.concessionaria_id) " : "";
            
            String sql = "SELECT to_char(yl.fechamento_date, 'YYYY')                   ano                                                   " +                     
            			"       , 'VL PCA GARAN'                                       topico                                                " + 
            			"       , substr(gh.modelo,1,instr(gh.modelo, '-' )-1)         modelo                                                " +                
            			"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),1,(gl.vl_peca_garantia*gl.quantidade),0))   m01     " +                     
            			"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),2,(gl.vl_peca_garantia*gl.quantidade),0))   m02     " +                     
            			"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),3,(gl.vl_peca_garantia*gl.quantidade),0))   m03     " +                     
            			"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),4,(gl.vl_peca_garantia*gl.quantidade),0))   m04     " +                     
            			"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),5,(gl.vl_peca_garantia*gl.quantidade),0))   m05     " +                     
            			"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),6,(gl.vl_peca_garantia*gl.quantidade),0))   m06     " +                     
            			"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),7,(gl.vl_peca_garantia*gl.quantidade),0))   m07     " +                     
            			"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),8,(gl.vl_peca_garantia*gl.quantidade),0))   m08     " +                     
            			"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),9,(gl.vl_peca_garantia*gl.quantidade),0))   m09     " +                     
            			"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),10,(gl.vl_peca_garantia*gl.quantidade),0))  m10     " +                     
            			"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),11,(gl.vl_peca_garantia*gl.quantidade),0))  m11     " +                     
            			"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),12,(gl.vl_peca_garantia*gl.quantidade),0))  m12     " +
            			"       , '1'                                                                                                ordem   " +
            			"    FROM ym_sg_lote               yl                                                                                " +                     
            			"       , ym_sg_garantia_header    gh                                                                                " +                     
            			"       , ym_sg_garantia_line	   gl                                                                                " + 
            			"   WHERE yl.lote_id             = gh.lote_id                                                                        " +                     
            			"     AND gh.garantia_id         = gl.garantia_id                                                                    " + 
            			"     AND yl.status_lote_id      in (9, 11)                                                                          " +                     
            			"     AND gh.status_mov_id       =  2                                                                                " +                     
                        "     AND to_date(to_char(yl.fechamento_date, 'MM/YYYY'), 'MM/YYYY') >= to_date(to_char(add_months(:p_date, -12), 'MM/YYYY'), 'MM/YYYY') " + 
                        "     AND to_date(to_char(yl.fechamento_date, 'MM/YYYY'), 'MM/YYYY') <= to_date(to_char(:p_date, 'MM/YYYY'), 'MM/YYYY')                  " + 
            			"     AND yl.linha_id	         = :p_linha                                                                          " + 
            			whereConcessionaria + 
            			"     AND substr(gh.modelo,1,instr(gh.modelo, '-' )-1) = nvl(:p_modelo, substr(gh.modelo,1,instr(gh.modelo, '-' )-1))" + 
            			"    GROUP BY to_char(yl.fechamento_date, 'YYYY')                                                                    " + 
            			"           , substr(gh.modelo,1,instr(gh.modelo, '-' )-1)                                                           " + 
            			"  UNION ALL                                                                                                         " +                     
            			"  SELECT to_char(yl.fechamento_date, 'YYYY')                  ano                                                   " +                     
            			"       , 'VL MOBRA PECA'                                      topico                                                " + 
            			"       , substr(gh.modelo,1,instr(gh.modelo, '-' )-1)         modelo                                                " + 
            			"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),1,ys.vl_servico,0))   m01                           " +                     
            			"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),2,ys.vl_servico,0))   m02                           " +                     
            			"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),3,ys.vl_servico,0))   m03                           " +                     
            			"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),4,ys.vl_servico,0))   m04                           " +                     
            			"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),5,ys.vl_servico,0))   m05                           " +                     
            			"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),6,ys.vl_servico,0))   m06                           " +                     
            			"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),7,ys.vl_servico,0))   m07                           " +                     
            			"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),8,ys.vl_servico,0))   m08                           " +                     
            			"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),9,ys.vl_servico,0))   m09                           " +                     
            			"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),10,ys.vl_servico,0))  m10                           " +                     
            			"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),11,ys.vl_servico,0))  m11                           " +                     
            			"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),12,ys.vl_servico,0))  m12                           " +
            			"       , '2'                                                                          ordem                         " +
            			"    FROM ym_sg_lote               yl                                                                                " +                     
            			"       , ym_sg_garantia_header    gh                                                                                " +                     
            			"       , ym_sg_servico_grupo	   ys                                                                                " +                     
            			"   WHERE yl.lote_id              = gh.lote_id                                                                       " +                     
            			"     AND gh.servico_grupo_id     = ys.servico_grupo_id                                                              " + 
            			"     AND yl.status_lote_id      in (9, 11)                                                                          " +                     
                        "     AND to_date(to_char(yl.fechamento_date, 'MM/YYYY'), 'MM/YYYY') >= to_date(to_char(add_months(:p_date, -12), 'MM/YYYY'), 'MM/YYYY') " + 
                        "     AND to_date(to_char(yl.fechamento_date, 'MM/YYYY'), 'MM/YYYY') <= to_date(to_char(:p_date, 'MM/YYYY'), 'MM/YYYY')                  " +                      
            			"     AND yl.linha_id	          = :p_linha                                                                         " + 
            			whereConcessionaria + 
            			"     AND substr(gh.modelo,1,instr(gh.modelo, '-' )-1) = nvl(:p_modelo, substr(gh.modelo,1,instr(gh.modelo, '-' )-1))" +   
            			"     GROUP BY to_char(yl.fechamento_date, 'YYYY')                                                                   " + 
            			"            , substr(gh.modelo,1,instr(gh.modelo, '-' )-1)                                                          " +                            
            			"  UNION ALL                                                                                                         " +                     
            			"  SELECT to_char(yl.fechamento_date, 'YYYY')                  ano                                                   " +                     
            			"       , 'VL MOBRA TERC'                                      topico                                                " + 
            			"       , substr(gh.modelo,1,instr(gh.modelo, '-' )-1)         modelo                                                " + 
            			"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),1,gh.vl_servico_terceiro,0))   m01                  " +                     
            			"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),2,gh.vl_servico_terceiro,0))   m02                  " +                     
            			"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),3,gh.vl_servico_terceiro,0))   m03                  " +                     
            			"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),4,gh.vl_servico_terceiro,0))   m04                  " +                     
            			"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),5,gh.vl_servico_terceiro,0))   m05                  " +                     
            			"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),6,gh.vl_servico_terceiro,0))   m06                  " +                     
            			"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),7,gh.vl_servico_terceiro,0))   m07                  " +                     
            			"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),8,gh.vl_servico_terceiro,0))   m08                  " +                     
            			"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),9,gh.vl_servico_terceiro,0))   m09                  " +                     
            			"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),10,gh.vl_servico_terceiro,0))  m10                  " +                     
            			"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),11,gh.vl_servico_terceiro,0))  m11                  " +                     
            			"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),12,gh.vl_servico_terceiro,0))  m12                  " +
            			"       , '3'                                                                                   ordem                " +
            			"    FROM ym_sg_lote              yl                                                                                 " +                      
            			"       , ym_sg_garantia_header   gh                                                                                 " + 
            			"   WHERE yl.lote_id           = gh.lote_id                                                                          " + 
            			"     AND yl.status_lote_id    in (9, 11)                                                                            " +                     
            			"     AND gh.status_mov_id     =  2                                                                                  " +                     
                        "     AND to_date(to_char(yl.fechamento_date, 'MM/YYYY'), 'MM/YYYY') >= to_date(to_char(add_months(:p_date, -12), 'MM/YYYY'), 'MM/YYYY') " + 
                        "     AND to_date(to_char(yl.fechamento_date, 'MM/YYYY'), 'MM/YYYY') <= to_date(to_char(:p_date, 'MM/YYYY'), 'MM/YYYY')                  " +                      
            			"     AND yl.linha_id	       = :p_linha                                                                            " + 
            			whereConcessionaria + 
            			"     AND substr(gh.modelo,1,instr(gh.modelo, '-' )-1) = nvl(:p_modelo, substr(gh.modelo,1,instr(gh.modelo, '-' )-1))" +                                                                                   
            			"   GROUP BY to_char(yl.fechamento_date, 'YYYY')                                                                     " + 
            			"          , substr(gh.modelo,1,instr(gh.modelo, '-' )-1)                                                            " +
            			"   ORDER BY modelo, ano, ordem";
            	
            Session session = super.getSession();
            SQLQuery query = session.createSQLQuery(sql);
            
            if (concessionaria.intValue() != 0)
            	query.setParameter("p_concessionaria", concessionaria);
            
            query.setParameter("p_linha", linha);
            query.setParameter("p_date", DateUtils.parseDate("01/"+dataReferencia));
            query.setParameter("p_modelo", modelo);

            
            List results = new ArrayList();		
            try {
	            Iterator garantiaPecasModIt = query.list().iterator();
	            
	            while ( garantiaPecasModIt.hasNext() ) {
	            
	            	RelatorioGarantiaPecasModVO relatorioGarantiaPecasMod = new RelatorioGarantiaPecasModVO();
	            	Object[] row = (Object[]) garantiaPecasModIt.next();
	            	
	            	relatorioGarantiaPecasMod.setAno((String)row[0]);
	            	relatorioGarantiaPecasMod.setTopico((String)row[1].toString());
	            	relatorioGarantiaPecasMod.setModelo((String)row[2].toString());
	            	relatorioGarantiaPecasMod.setM01(Double.valueOf(row[3].toString()));
	            	relatorioGarantiaPecasMod.setM02(Double.valueOf(row[4].toString()));
	            	relatorioGarantiaPecasMod.setM03(Double.valueOf(row[5].toString()));
	            	relatorioGarantiaPecasMod.setM04(Double.valueOf(row[6].toString()));
	            	relatorioGarantiaPecasMod.setM05(Double.valueOf(row[7].toString()));
	            	relatorioGarantiaPecasMod.setM06(Double.valueOf(row[8].toString()));
	            	relatorioGarantiaPecasMod.setM07(Double.valueOf(row[9].toString()));
	            	relatorioGarantiaPecasMod.setM08(Double.valueOf(row[10].toString()));
	            	relatorioGarantiaPecasMod.setM09(Double.valueOf(row[11].toString()));
	            	relatorioGarantiaPecasMod.setM10(Double.valueOf(row[12].toString()));
	            	relatorioGarantiaPecasMod.setM11(Double.valueOf(row[13].toString()));
	            	relatorioGarantiaPecasMod.setM12(Double.valueOf(row[14].toString()));
	
	            	results.add(relatorioGarantiaPecasMod);
	            }		
            } catch ( Exception exp ) {
    			
    			throw new DaoException(exp);
    			
    		}  finally {
    			session.close();
    		}
            return results;
        } catch (NumberFormatException e) {
            throw new DaoException(e);
            
        } catch (ParseException e) {
            throw new DaoException(e);
            
        }	
        
	}	

	/** Lista os dados para o Relatório de Garantia e Peças por Modelo. 
	 * 
	 * @param concessionaria
	 * 	<code>concessionaria</code> concessionaria
	 * 
	 * @param linha
	 * 	<code>linha</code> linha 
	 * 
	 * @param ano
	 * 	<code>ano</code> ano(yyyy)
	 * 
	 * @param modelo
	 * 	<code>modelo</code> modelo
	 * 
	 * @return list
	 * 	<code>list</code> lista de dados do Relatório de Garantia e Peças por Modelo.  
	 * 
	 * @throws DaoException
	 */			
	public List listRelatorioGPMRevisao(Long concessionaria, Long linha, String ano, String modelo) throws DaoException{
		
		String whereConcessionaria = concessionaria.intValue() != 0 ? " AND yl.concessionaria_id = nvl(:p_concessionaria, yl.concessionaria_id) " : "";
		
		String sql = "  SELECT to_char(yl.fechamento_date,'YYYY')                        ano                                                   " +
					"       , 'QT REVISAO'||trim(to_char(yr.numero_revisao,'99'))        topico                                                " +
					"       , substr(yc.modelo,1,instr(yc.modelo, '-' )-1)               modelo                                                " +         
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),1,1,0))   m01                                             " +    
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),2,1,0))   m02                                             " +    
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),3,1,0))   m03                                             " +    
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),4,1,0))   m04                                             " +        
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),5,1,0))   m05                                             " +    
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),6,1,0))   m06                                             " +    
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),7,1,0))   m07                                             " +    
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),8,1,0))   m08                                             " +    
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),9,1,0))   m09                                             " +    
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),10,1,0))  m10                                             " +    
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),11,1,0))  m11                                             " +    
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),12,1,0))  m12                                             " +
					"       , '1'                                                              ordem                                           " +
					"    FROM ym_sg_lote         yl                                                                                            " +       
					"       , ym_sg_cupom	     yc                                                                                            " +       
					"       , ym_sg_revisao	     yr                                                                                            " +
					"   WHERE yl.lote_id            = yc.lote_id                                                                               " +    
					"     AND yc.revisao_id         = yr.revisao_id                                                                            " +
					"     AND yr.numero_revisao in (1,2,3)                                                                                     " +    
					"     AND yl.status_lote_id      in (9, 11)                                                                                " +    
					"     AND yc.status_mov_id      = 2                                                                                        " +   
					"     AND to_char(yl.fechamento_date,'YYYY')  = :p_ano                                                                     " +    
					"     AND yl.linha_id	                      = :p_linha                                                                   " +
					whereConcessionaria +
					"     AND substr(yc.modelo,1,instr(yc.modelo, '-' )-1) = nvl(:p_modelo, substr(yc.modelo,1,instr(yc.modelo, '-' )-1))      " +
					"   GROUP BY to_char(yl.fechamento_date,'YYYY')                                                                            " +    
					"         ,  'QT REVISAO'||trim(to_char(yr.numero_revisao,'99'))                                                           " +
					"         ,  substr(yc.modelo,1,instr(yc.modelo, '-' )-1)                                                                  " +        
					"  UNION ALL                                                                                                               " +    
					"  SELECT to_char(yl.fechamento_date,'YYYY')                        ano                                                    " +    
					"       , 'VL REVISAO'||trim(to_char(yr.numero_revisao,'99'))       topico                                                 " +
					"       , substr(yc.modelo,1,instr(yc.modelo, '-' )-1)              modelo                                                 " +       
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),1,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m01 " +    
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),2,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m02 " +    
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),3,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m03 " +    
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),4,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m04 " +    
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),5,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m05 " +    
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),6,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m06 " +    
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),7,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m07 " +    
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),8,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m08 " +    
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),9,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m09 " +    
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),10,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))  m10 " +    
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),11,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))  m11 " +    
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),12,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))  m12 " +   
					"       , '2'   ordem                                                                                                      " +
					"    FROM ym_sg_lote         yl                                                                                            " +     
					"       , ym_sg_cupom	     yc                                                                                            " +      
					"       , ym_sg_revisao	     yr                                                                                            " +
					"   WHERE yl.lote_id            = yc.lote_id                                                                               " +    
					"     AND yc.revisao_id         = yr.revisao_id                                                                            " +
					"     AND yr.numero_revisao     in (1,2,3)                                                                                 " +    
					"     AND yl.status_lote_id     in (5,6,7,9,10,11)                                                                         " +    
					"     AND yc.status_mov_id      =  2                                                                                       " +    
					"     AND to_char(yl.fechamento_date,'YYYY') = :p_ano                                                                      " +    
					"     AND yl.linha_id	                     = :p_linha                                                                    " +
					whereConcessionaria +
					"     AND substr(yc.modelo,1,instr(yc.modelo, '-' )-1) = nvl(:p_modelo, substr(yc.modelo,1,instr(yc.modelo, '-' )-1))      " +                                                                  
					"   GROUP BY to_char(yl.fechamento_date,'YYYY')                                                                            " +    
					"          , 'VL REVISAO'||trim(to_char(yr.numero_revisao,'99'))                                                           " +
					"          , substr(yc.modelo,1,instr(yc.modelo, '-' )-1)                                                                  " +
					"  UNION ALL                                                                                                               " +
					"  SELECT to_char(yl.fechamento_date,'YYYY')                   ano                                                         " +    
					"       , 'TOT REVISOES'                                       topico                                                      " +
					"       , substr(yc.modelo,1,instr(yc.modelo, '-' )-1)         modelo                                                      " +               
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),1,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m01 " +    
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),2,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m02 " +    
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),3,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m03 " +    
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),4,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m04 " +    
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),5,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m05 " +    
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),6,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m06 " +    
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),7,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m07 " +    
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),8,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m08 " +    
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),9,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m09 " +    
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),10,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))  m10 " +    
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),11,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))  m11 " +             
					"       , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),12,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))  m12 " +
					"       , '3' ordem                                                                                                        " +
					"    FROM ym_sg_lote         yl                                                                                            " +     
					"       , ym_sg_cupom        yc                                                                                            " +     
					"       , ym_sg_revisao	     yr                                                                                            " +
					"   WHERE yl.lote_id            = yc.lote_id                                                                               " +    
					"     AND yc.revisao_id         = yr.revisao_id                                                                            " +
					"     AND yl.status_lote_id     in (9, 11)                                                                                 " +    
					"     AND yr.numero_revisao     in (1,2,3)                                                                                 " +    
					"     AND yc.status_mov_id      = 2                                                                                        " +
					"     AND to_char(yl.fechamento_date,'YYYY') = :p_ano                                                                      " +    
					"     AND yl.linha_id	                     = :p_linha                                                                    " +
					whereConcessionaria +
					"     AND substr(yc.modelo,1,instr(yc.modelo, '-' )-1) = nvl(:p_modelo, substr(yc.modelo,1,instr(yc.modelo, '-' )-1))      " +                                                                   
					"   GROUP BY to_char(yl.fechamento_date,'YYYY')                                                                            " +    
					"          , 'TOT REVISOES'                                                                                                " +
					"          , substr(yc.modelo,1,instr(yc.modelo, '-' )-1)                                                                  " +
					"   ORDER BY ordem, topico " ;
					
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		if (concessionaria.intValue() != 0)
			query.setParameter("p_concessionaria", concessionaria);
		
		query.setParameter("p_linha", linha);
		query.setParameter("p_ano", ano);
		query.setParameter("p_modelo", modelo);

		List results = new ArrayList();	
		try {
			Iterator garantiaPecasModIt = query.list().iterator();
			
			while ( garantiaPecasModIt.hasNext() ) {
			
				RelatorioGarantiaPecasModSubRepVO relatorioGarantiaPecasMod = new RelatorioGarantiaPecasModSubRepVO();
				Object[] row = (Object[]) garantiaPecasModIt.next();
				
				relatorioGarantiaPecasMod.setAno((String)row[0]);
				relatorioGarantiaPecasMod.setTopico((String)row[1].toString());
				relatorioGarantiaPecasMod.setModelo((String)row[2].toString());
				relatorioGarantiaPecasMod.setM01(Double.valueOf(row[3].toString()));
				relatorioGarantiaPecasMod.setM02(Double.valueOf(row[4].toString()));
				relatorioGarantiaPecasMod.setM03(Double.valueOf(row[5].toString()));
				relatorioGarantiaPecasMod.setM04(Double.valueOf(row[6].toString()));
				relatorioGarantiaPecasMod.setM05(Double.valueOf(row[7].toString()));
				relatorioGarantiaPecasMod.setM06(Double.valueOf(row[8].toString()));
				relatorioGarantiaPecasMod.setM07(Double.valueOf(row[9].toString()));
				relatorioGarantiaPecasMod.setM08(Double.valueOf(row[10].toString()));
				relatorioGarantiaPecasMod.setM09(Double.valueOf(row[11].toString()));
				relatorioGarantiaPecasMod.setM10(Double.valueOf(row[12].toString()));
				relatorioGarantiaPecasMod.setM11(Double.valueOf(row[13].toString()));
				relatorioGarantiaPecasMod.setM12(Double.valueOf(row[14].toString()));
	
				results.add(relatorioGarantiaPecasMod);
			}		
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		return results;	
	}	

	/** Lista os dados para o Relatório de Garantia e Peças por Modelo. 
	 * 
	 * @param concessionaria
	 * 	<code>concessionaria</code> concessionaria
	 * 
	 * @param linha
	 * 	<code>linha</code> linha 
	 * 
	 * @param ano
	 * 	<code>ano</code> ano(yyyy)
	 * 
	 * @param modelo
	 * 	<code>modelo</code> modelo
	 * 
	 * @return list
	 * 	<code>list</code> lista de dados do Relatório de Garantia e Peças por Modelo.  
	 * 
	 * @throws DaoException
	 */		
	public List listRelatorioGPMVlFatur(Long concessionaria, Long linha, String ano, String modelo) throws DaoException{
		
		String whereConcessionaria = concessionaria.intValue() != 0 ? " AND yf.concessionaria_id = nvl(:p_concessionaria, yf.concessionaria_id) " : "";
		
		String sql = " SELECT to_char(yf.data_nf, 'YYYY')                 ano                         " +                                                   
					"      , 'VL FATUR'                                   topico                      " +
					"      , substr(yf.chassi,1,instr(yf.chassi, '-' )-1) modelo                      " +                                               
					"      , sum(decode(to_number(to_char(yf.data_nf,'MM')),1,yf.vl_produto,0))   m01 " +                                                     
					"      , sum(decode(to_number(to_char(yf.data_nf,'MM')),2,yf.vl_produto,0))   m02 " +                                                     
					"      , sum(decode(to_number(to_char(yf.data_nf,'MM')),3,yf.vl_produto,0))   m03 " +                                                     
					"      , sum(decode(to_number(to_char(yf.data_nf,'MM')),4,yf.vl_produto,0))   m04 " +                                                     
					"      , sum(decode(to_number(to_char(yf.data_nf,'MM')),5,yf.vl_produto,0))   m05 " +                                                     
					"      , sum(decode(to_number(to_char(yf.data_nf,'MM')),6,yf.vl_produto,0))   m06 " +                                                     
					"      , sum(decode(to_number(to_char(yf.data_nf,'MM')),7,yf.vl_produto,0))   m07 " +                                                     
					"      , sum(decode(to_number(to_char(yf.data_nf,'MM')),8,yf.vl_produto,0))   m08 " +                                                     
					"      , sum(decode(to_number(to_char(yf.data_nf,'MM')),9,yf.vl_produto,0))   m09 " +                                                     
					"      , sum(decode(to_number(to_char(yf.data_nf,'MM')),10,yf.vl_produto,0))  m10 " +                                                     
					"      , sum(decode(to_number(to_char(yf.data_nf,'MM')),11,yf.vl_produto,0))  m11 " +                                                     
					"      , sum(decode(to_number(to_char(yf.data_nf,'MM')),12,yf.vl_produto,0))  m12 " +                                                     
					"   FROM ym_sg_faturamento	   yf                                                 " +                                                      
					"  WHERE yf.end_date is NULL                                                      " +                                                     
					"    AND to_char(yf.data_nf, 'YYYY') = :p_ano                                     " +                                                     
					"    AND yf.linha_id	             = :p_linha                                   " +
					whereConcessionaria +
					"    AND substr(yf.chassi,1,instr(yf.chassi, '-' )-1) =                           " +
					"        nvl(:p_modelo, substr(yf.chassi,1,instr(yf.chassi, '-' )-1))             " +                                                                       
					"  GROUP BY to_char(yf.data_nf, 'YYYY')                                           " +                                                     
					"         , 'VL FATUR'                                                            " +
					"         , substr(yf.chassi,1,instr(yf.chassi, '-' )-1)                          " ;

		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		if (concessionaria.intValue() != 0)
			query.setParameter("p_concessionaria", concessionaria);
		
		query.setParameter("p_linha", linha);
		query.setParameter("p_ano", ano);
		query.setParameter("p_modelo", modelo);
		
		List results = new ArrayList();	
		try {
			Iterator garantiaPecasModIt = query.list().iterator();
			
			while ( garantiaPecasModIt.hasNext() ) {
			
				RelatorioGarantiaPecasModSubRepVO relatorioGarantiaPecasMod = new RelatorioGarantiaPecasModSubRepVO();
				Object[] row = (Object[]) garantiaPecasModIt.next();
				
				relatorioGarantiaPecasMod.setAno((String)row[0]);
				relatorioGarantiaPecasMod.setTopico((String)row[1].toString());
				relatorioGarantiaPecasMod.setModelo((String)row[2].toString());
				relatorioGarantiaPecasMod.setM01(Double.valueOf(row[3].toString()));
				relatorioGarantiaPecasMod.setM02(Double.valueOf(row[4].toString()));
				relatorioGarantiaPecasMod.setM03(Double.valueOf(row[5].toString()));
				relatorioGarantiaPecasMod.setM04(Double.valueOf(row[6].toString()));
				relatorioGarantiaPecasMod.setM05(Double.valueOf(row[7].toString()));
				relatorioGarantiaPecasMod.setM06(Double.valueOf(row[8].toString()));
				relatorioGarantiaPecasMod.setM07(Double.valueOf(row[9].toString()));
				relatorioGarantiaPecasMod.setM08(Double.valueOf(row[10].toString()));
				relatorioGarantiaPecasMod.setM09(Double.valueOf(row[11].toString()));
				relatorioGarantiaPecasMod.setM10(Double.valueOf(row[12].toString()));
				relatorioGarantiaPecasMod.setM11(Double.valueOf(row[13].toString()));
				relatorioGarantiaPecasMod.setM12(Double.valueOf(row[14].toString()));
	
				results.add(relatorioGarantiaPecasMod);
			}		
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		return results;	
	}	
	
	/** Lista os dados para o Relatório de Garantia e Peças por Modelo. 
	 * 
	 * @param concessionaria
	 * 	<code>concessionaria</code> concessionaria
	 * 
	 * @param linha
	 * 	<code>linha</code> linha 
	 * 
	 * @param ano
	 * 	<code>ano</code> ano(yyyy)
	 * 
	 * @param modelo
	 * 	<code>modelo</code> modelo
	 * 
	 * @return list
	 * 	<code>list</code> lista de dados do Relatório de Garantia e Peças por Modelo.  
	 * 
	 * @throws DaoException
	 */			
	public List listRelatorioGPMQtdes(Long concessionaria, Long linha, String ano, String modelo) throws DaoException{

		String whereConcessionaria = concessionaria.intValue() != 0 ? " AND yl.concessionaria_id = nvl(:p_concessionaria, yl.concessionaria_id) " : "";
		
		String sql = " SELECT to_char(yl.fechamento_date, 'YYYY')                 ano                    " +                                                   
					"      , 'QT SGs'                                             topico                 " +
					"      , substr(gh.modelo,1,instr(gh.modelo, '-' )-1)         modelo                 " +                                              
					"      , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),1,1,0))   m01        " +                                                  
					"      , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),2,1,0))   m02        " +                                                  
					"      , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),3,1,0))   m03        " +                                                  
					"      , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),4,1,0))   m04        " +                                                  
					"      , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),5,1,0))   m05        " +                                                  
					"      , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),6,1,0))   m06        " +                                                  
					"      , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),7,1,0))   m07        " +                                                  
					"      , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),8,1,0))   m08        " +                                                  
					"      , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),9,1,0))   m09        " +                                                  
					"      , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),10,1,0))  m10        " +                                                  
					"      , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),11,1,0))  m11        " +                                                  
					"      , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),12,1,0))  m12        " +                                                  
					"   FROM ym_sg_lote               yl                                                 " +                                                  
					"      , ym_sg_garantia_header	  gh                                                 " +
					"  WHERE yl.lote_id             = gh.lote_id                                         " +
					"    AND yl.status_lote_id      in (9,11)                                            " +
					"    AND gh.status_mov_id       = 2                                                  " +
					"    AND to_char(yl.fechamento_date, 'YYYY') = :p_ano                                " +                                                  
					"    AND yl.linha_id	        = :p_linha                                           " +
					whereConcessionaria +
					"    AND substr(gh.modelo,1,instr(gh.modelo, '-' )-1) =                              " +
					"        nvl(:p_modelo, substr(gh.modelo,1,instr(gh.modelo, '-' )-1))                " +                                                                  
					"  GROUP BY to_char(yl.fechamento_date, 'YYYY')                                      " +                                                  
					"         , 'QT SGs'                                                                 " +
					"         , substr(gh.modelo,1,instr(gh.modelo, '-' )-1)                             " +                                                                                    
					" UNION ALL                                                                          " +                                                  
					" SELECT ano                                                                         " +                                                  
					"      , 'QT MOTOS' topico                                                           " +
					"      , modelo                                                                      " +                                      
					"      , sum(m01)   m01                                                              " +                                                  
					"      , sum(m02)   m02                                                              " +                                                  
					"      , sum(m03)   m03                                                              " +                                                  
					"      , sum(m04)   m04                                                              " +                                                  
					"      , sum(m05)   m05                                                              " +                                                  
					"      , sum(m06)   m06                                                              " +                                                  
					"      , sum(m07)   m07                                                              " +                                                  
					"      , sum(m08)   m08                                                              " +                                                  
					"      , sum(m09)   m09                                                              " +                                                  
					"      , sum(m10)   m10                                                              " +                                                  
					"      , sum(m11)   m11                                                              " +                                                  
					"      , sum(m12)   m12                                                              " +                                                  
					" FROM ( SELECT to_char(yl.fechamento_date, 'YYYY')	ano                              " +                                                 
					"			  , gh.modelo                           modelo                           " +                                                              
					"             , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),1,1,0))   m01 " +                                                  
					"             , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),2,1,0))   m02 " +                                                  
					"             , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),3,1,0))   m03 " +                                                  
					"             , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),4,1,0))   m04 " +                                                  
					"             , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),5,1,0))   m05 " +                                                  
					"             , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),6,1,0))   m06 " +                                                  
					"             , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),7,1,0))   m07 " +                                                 
					"             , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),8,1,0))   m08 " +                                                  
					"             , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),9,1,0))   m09 " +                                                  
					"             , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),10,1,0))  m10 " +                                                  
					"             , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),11,1,0))  m11 " +                                                  
					"             , sum(decode(to_number(to_char(yl.fechamento_date,'MM')),12,1,0))  m12 " +                                                  
					"          FROM ym_sg_lote               yl                                          " +                                                  
					"             , ym_sg_garantia_header	 gh                                          " +
					"         WHERE yl.lote_id             = gh.lote_id                                  " +
					"           AND yl.status_lote_id      in (9, 11)                                    " +                                                  
					"           AND gh.status_mov_id       = 2                                           " +                                                  
					"           AND to_char(yl.fechamento_date, 'YYYY')  = :p_ano                        " +                                                   
					"           AND yl.linha_id	           = :p_linha                                    " +
					whereConcessionaria +
					"           AND gh.modelo              = NVL(:p_modelo, gh.modelo)                   " +                                                             
					"         GROUP BY to_char(yl.fechamento_date, 'YYYY')                               " +                                                  
					"                , gh.modelo                                                         " +                                   
					"     )                                                                              " +                                                
					"  GROUP by ano                                                                      " +
					"         , 'QT MOTOS'                                                               " +                                                    
					"         , modelo                                                                   " +                                                                                                                                                  
					" UNION ALL                                                                          " +                                                
					" SELECT to_char(yl.data_nf, 'YYYY')                   ano                           " +                                                  
					"      , 'QT FATURADA'                                 topico                        " +
					"      , substr(yl.chassi,1,instr(yl.chassi, '-' )-1)  modelo                        " +                                               
					"      , sum(decode(to_number(to_char(yl.data_nf,'MM')),1,1,0))   m01                " +                                                  
					"      , sum(decode(to_number(to_char(yl.data_nf,'MM')),2,1,0))   m02                " +                                                  
					"      , sum(decode(to_number(to_char(yl.data_nf,'MM')),3,1,0))   m03                " +                                                  
					"      , sum(decode(to_number(to_char(yl.data_nf,'MM')),4,1,0))   m04                " +                                                  
					"      , sum(decode(to_number(to_char(yl.data_nf,'MM')),5,1,0))   m05                " +                                                  
					"      , sum(decode(to_number(to_char(yl.data_nf,'MM')),6,1,0))   m06                " +                                                  
					"      , sum(decode(to_number(to_char(yl.data_nf,'MM')),7,1,0))   m07                " +                                                  
					"      , sum(decode(to_number(to_char(yl.data_nf,'MM')),8,1,0))   m08                " +                                                  
					"      , sum(decode(to_number(to_char(yl.data_nf,'MM')),9,1,0))   m09                " +                                                  
					"      , sum(decode(to_number(to_char(yl.data_nf,'MM')),10,1,0))  m10                " +                                                  
					"      , sum(decode(to_number(to_char(yl.data_nf,'MM')),11,1,0))  m11                " +                                                  
					"      , sum(decode(to_number(to_char(yl.data_nf,'MM')),12,1,0))  m12                " +                                                  
					"   FROM ym_sg_faturamento	   yl                                                    " +                                                   
					"  WHERE yl.end_date is null                                                         " +                                                  
					"    AND to_char(yl.data_nf, 'YYYY') = :p_ano                                        " +                                                  
					"    AND yl.linha_id	             = :p_linha                                      " +
					whereConcessionaria +
					"    AND substr(yl.chassi,1,instr(yl.chassi, '-' )-1) =                              " +
					"        nvl(:p_modelo, substr(yl.chassi,1,instr(yl.chassi, '-' )-1))                " +                                                                  
					"  GROUP BY to_char(yl.data_nf, 'YYYY')                                              " +                                                  
					"         , 'QT FATURADA'                                                            " +
					"         , substr(yl.chassi,1,instr(yl.chassi, '-' )-1)                             " ;

		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		if (concessionaria.intValue() != 0)
			query.setParameter("p_concessionaria", concessionaria);
		
		query.setParameter("p_linha", linha);
		query.setParameter("p_ano", ano);
		query.setParameter("p_modelo", modelo);
		
		List results = new ArrayList();	
		try {
			Iterator garantiaPecasModIt = query.list().iterator();
			
			while ( garantiaPecasModIt.hasNext() ) {
			
				RelatorioGarantiaPecasModSubRepVO relatorioGarantiaPecasMod = new RelatorioGarantiaPecasModSubRepVO();
				Object[] row = (Object[]) garantiaPecasModIt.next();
				
				relatorioGarantiaPecasMod.setAno((String)row[0]);
				relatorioGarantiaPecasMod.setTopico((String)row[1].toString());
				relatorioGarantiaPecasMod.setModelo((String)row[2]);
				relatorioGarantiaPecasMod.setM01(Double.valueOf(row[3].toString()));
				relatorioGarantiaPecasMod.setM02(Double.valueOf(row[4].toString()));
				relatorioGarantiaPecasMod.setM03(Double.valueOf(row[5].toString()));
				relatorioGarantiaPecasMod.setM04(Double.valueOf(row[6].toString()));
				relatorioGarantiaPecasMod.setM05(Double.valueOf(row[7].toString()));
				relatorioGarantiaPecasMod.setM06(Double.valueOf(row[8].toString()));
				relatorioGarantiaPecasMod.setM07(Double.valueOf(row[9].toString()));
				relatorioGarantiaPecasMod.setM08(Double.valueOf(row[10].toString()));
				relatorioGarantiaPecasMod.setM09(Double.valueOf(row[11].toString()));
				relatorioGarantiaPecasMod.setM10(Double.valueOf(row[12].toString()));
				relatorioGarantiaPecasMod.setM11(Double.valueOf(row[13].toString()));
				relatorioGarantiaPecasMod.setM12(Double.valueOf(row[14].toString()));
	
				results.add(relatorioGarantiaPecasMod);
			}		
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		return results;	
	}	
	
	/** Lista os dados para o Relatório de Garantia e Peças por Modelo. 
	 * 
	 * @param ano
	 * 	<code>ano</code> ano(yyyy)
	 * 
	 * @return list
	 * 	<code>list</code> lista de dados do Relatório de Garantia e Peças por Modelo.  
	 * 
	 * @throws DaoException
	 */			
	public List listRelatorioGPMDolar(String ano) throws DaoException{
		
		String sql = " SELECT to_char(ys.ano)                     ano          " +                                                                                         
					"       , 'VL DOLAR'                          topico       " + 
					"       , max(decode(mes,1,ys.valor_real,0))  m01          " +                     
					"       , max(decode(mes,2,ys.valor_real,0))  m02          " +                     
					"       , max(decode(mes,3,ys.valor_real,0))  m03          " +                     
					"       , max(decode(mes,4,ys.valor_real,0))  m04          " +                     
					"       , max(decode(mes,5,ys.valor_real,0))  m05          " +                     
					"       , max(decode(mes,6,ys.valor_real,0))  m06          " +                     
					"       , max(decode(mes,7,ys.valor_real,0))  m07          " +                     
					"       , max(decode(mes,8,ys.valor_real,0))  m08          " +                     
					"       , max(decode(mes,9,ys.valor_real,0))  m09          " +                     
					"       , max(decode(mes,10,ys.valor_real,0)) m10          " +                     
					"       , max(decode(mes,11,ys.valor_real,0)) m11          " +                     
					"       , max(decode(mes,12,ys.valor_real,0)) m12          " +                     
					"    FROM ym_sg_moeda ys                                   " +                     
					"   WHERE trunc(ys.start_date) <= sysdate                  " +                     
					"     AND nvl(trunc(ys.end_date), sysdate + 1) >= sysdate  " +                     
					"     AND to_char(ys.ano) = :p_ano                         " +                     
					"   GROUP BY to_char(ys.ano)                               " ;                     

		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		query.setParameter("p_ano", ano);
		
		List results = new ArrayList();	
		try {
			Iterator garantiaPecasModIt = query.list().iterator();
			
			while ( garantiaPecasModIt.hasNext() ) {
			
				RelatorioGarantiaPecasModSubRepVO relatorioGarantiaPecasMod = new RelatorioGarantiaPecasModSubRepVO();
				Object[] row = (Object[]) garantiaPecasModIt.next();
				
				relatorioGarantiaPecasMod.setAno((String)row[0]);
				relatorioGarantiaPecasMod.setTopico((String)row[1].toString());
				relatorioGarantiaPecasMod.setM01(Double.valueOf(row[2].toString()));
				relatorioGarantiaPecasMod.setM02(Double.valueOf(row[3].toString()));
				relatorioGarantiaPecasMod.setM03(Double.valueOf(row[4].toString()));
				relatorioGarantiaPecasMod.setM04(Double.valueOf(row[5].toString()));
				relatorioGarantiaPecasMod.setM05(Double.valueOf(row[6].toString()));
				relatorioGarantiaPecasMod.setM06(Double.valueOf(row[7].toString()));
				relatorioGarantiaPecasMod.setM07(Double.valueOf(row[8].toString()));
				relatorioGarantiaPecasMod.setM08(Double.valueOf(row[9].toString()));
				relatorioGarantiaPecasMod.setM09(Double.valueOf(row[10].toString()));
				relatorioGarantiaPecasMod.setM10(Double.valueOf(row[11].toString()));
				relatorioGarantiaPecasMod.setM11(Double.valueOf(row[12].toString()));
				relatorioGarantiaPecasMod.setM12(Double.valueOf(row[13].toString()));
	
				results.add(relatorioGarantiaPecasMod);
			}		
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		return results;	
	}	
	
	/** Lista os dados para o Relatório Imported Parts Claim List. 
	 * 
	 * @param dataIni
	 * 	<code>dataIni</code> dataIni (dd/mm/yyyy)
	 * 
	 * @param dataFim
	 * 	<code>dataFim</code> dataFim (dd/mm/yyyy)
	 * 
	 * @param linha
	 * 	<code>linha</code> linha 
	 * 
	 * @param empresa
	 * 	<code>empresa</code> empresa 
	 * 
	 * @param fornecedor
	 * 	<code>fornecedor</code> fornecedor 
	 * 
	 * @return list
	 * 	<code>list</code> lista de dados do Relatório Imported Parts Claim List.  
	 * 
	 * @throws DaoException
	 */			
	public List listRelatorioImportedParts( Date   dataIni
								          , Date   dataFim
								          , Long   linha
								          , Long   empresa
								          , String fornecedor) throws DaoException{
		
		//System.out.println("Data Ini: " 	+ dataIni	);
		//System.out.println("Data Fim: " 	+ dataFim	);
		//System.out.println("Linha   : " 	+ linha		);
		//System.out.println("empresa : " 	+ empresa	);
		//System.out.println("Fornecedor: " 	+ fornecedor);
		
		String sql = "SELECT li.descr_linha                                     linha         " +                             
					"      , substr(gh.modelo, 1, instr(gh.modelo, '-') - 1)    modelo        " +
					"      , substr(gh.modelo, instr(gh.modelo, '-') +1, 7)     engine        " +
					"      , gh.modelo                                          chassi        " +
					"      , gh.garantia_id                                     claimForm     " +
					"      , gh.data_fechamento_os                              failureDate   " +
					"      , cp.data_venda                                      soldDate      " +
					"      , it.item_code                                       partsCode     " +
					"      , it.descricao_us                                    partsName     " +
					"      , decode(it.origem,'NAC', 'YMB', 'YAF')              orgCode       " +
					"      , gh.quilometragem                                   km            " +
					"      , si.sintoma_code                                    sintoma       " +
					"      , co.condicao_code                                   condicao      " +
					"      , decode(gl.line_id, 1, 'YES', ' ')                  failParts     " +
					"      , get_sum_tempo_padrao(gh.garantia_id,                             " +
					"        substr(gh.modelo, 1, instr(gh.modelo, '-') - 1))   laborTime     " +
					"      , nvl(get_sum_tempo_padrao(gh.garantia_id,                         " +
					"        substr(gh.modelo, 1, instr(gh.modelo, '-') - 1))                 " +
					"        * mo.valor_dolar_contratado, 0)                    laborUs       " +
					"      , gl.quantidade                                      qtde          " +
					"      , gl.quantidade * gl.vl_preco_fob                    partsFobPrice " +  
					"      , fi.fornecedor                                      fornecedor    " +
					"   FROM ym_sg_garantia_header         gh                                 " +
					"      , ym_sg_garantia_line           gl                                 " +
					"      , ym_sg_sintoma                 si                                 " +
					"      , ym_sg_condicao                co                                 " +
					"      , ym_sg_cupom                   cp                                 " +
					"      , ym_sg_revisao                 rv                                 " +
					"      , ym_sg_moeda                   mo                                 " +
					"      , ym_sg_lote                    lt                                 " +
					"      , ym_sg_item                    it                                 " +
					"      , ym_sg_empresa                 em                                 " +
					"      , ym_sg_item_fornecedor         fi                                 " +
					"      , ym_sg_linha                   li                                 " +
					"  WHERE gh.garantia_id               = gl.garantia_id                    " +
					"    AND gh.sintoma_id                = si.sintoma_id                     " +
					"    AND gh.condicao_id               = co.condicao_id                    " +
					"    AND gh.modelo                    = cp.modelo                         " +
					"    AND cp.revisao_id                = rv.revisao_id                     " +
					"    AND gh.lote_id                   = lt.lote_id                        " +
					"    AND gl.item_id                   = it.item_id                        " +
					"    AND it.organization_id           = em.organization_id                " +
					"    AND it.item_id                   = fi.item_id                        " +
					"    AND mo.ano                       = to_char(lt.liberacao_date,'YYYY')   " +
					"    AND mo.mes                       = to_char(lt.liberacao_date,'MM')     " +
					"    AND rv.numero_revisao            =  1                                " +
					//"    AND lt.status_lote_id            in (9,11)                           " +
					//"    AND gh.status_mov_id             = 2                                 " +
					//"    AND cp.status_mov_id             = 2                                 " +
					"    AND lt.end_date                  is null                             " +
					"    AND gh.end_date                  is null                             " +
					"    AND cp.end_date                  is null                             " +
					"    AND gl.cobra_peca_fornecedor     = 'S'                               " +
					"    AND lt.liberacao_date          >= to_date(:p_dtini, 'dd/mm/yyyy')   " +
					"    AND lt.liberacao_date          <  to_date(:p_dtfim, 'dd/mm/yyyy')+1 " +
					"    AND fi.fornecedor                = nvl(:p_fornecedor, fi.fornecedor) " +
					"    AND em.organization_id           = nvl(:p_empresa, em.organization_id) " +
					"    AND li.linha_id                  = :p_linha                          " +
					"  ORDER BY fi.fornecedor                                                 " +
					"         , substr(gh.modelo, 1, instr(gh.modelo, '-') - 1)               " +
					"         , substr(gh.modelo, instr(gh.modelo, '-') + 1, 7)               " +
					"         , it.item_code                                                  " ;                   

		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		query.setParameter("p_dtini", DateUtils.applyMask(dataIni));
		query.setParameter("p_dtfim", DateUtils.applyMask(dataFim));
		query.setParameter("p_fornecedor", fornecedor);
		
		if ( new Long(0).equals(empresa) )
			query.setString("p_empresa", null);
		else 
			query.setLong("p_empresa", empresa.longValue());
		
		query.setParameter("p_linha", linha);
		
		List results = new ArrayList();	
		try {
			Iterator importedIt = query.list().iterator();
			
			while ( importedIt.hasNext() ) {
			
	    		RelatorioImportedPartsVO relatorioImported = new RelatorioImportedPartsVO();
	    		Object[] row = (Object[]) importedIt.next();
	    		
	    		relatorioImported.setLinha((String)row[0]);
	    		relatorioImported.setModelo((String)row[1].toString());
	    		relatorioImported.setEngine((String)row[2].toString());
	    		relatorioImported.setChassi((String)row[3].toString());
	    		relatorioImported.setClaimForm(Double.valueOf(row[4].toString()));
	    		relatorioImported.setFailureDate((Date)row[5]);
	    		relatorioImported.setSoldDate((Date)row[6]);
	    		relatorioImported.setPartsCode((String)row[7].toString());
	    		relatorioImported.setPartsName((String)row[8].toString());
	    		relatorioImported.setOrgCode((String)row[9].toString());
	    		relatorioImported.setKm(Double.valueOf(row[10].toString()));
	    		relatorioImported.setSintoma((String)row[11].toString());
	    		relatorioImported.setCondicao((String)row[12].toString());
	    		relatorioImported.setFailParts((String)row[13].toString());
	    		relatorioImported.setLaborTime(Double.valueOf(row[14].toString()));
	    		relatorioImported.setLaborUs(Double.valueOf(row[15].toString()));
	    		relatorioImported.setQtde(Double.valueOf(row[16].toString()));
	    		relatorioImported.setPartsFobPrice(Double.valueOf(row[17].toString()));
	    		relatorioImported.setFornecedor((String)row[18]);
	    		
	    		results.add(relatorioImported);
	            
			}		
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		return results;			
	}
		
	/** Lista os dados para o Relatório Imported Parts Claim List. 
	 * 
	 * @return list
	 * 	<code>list</code> lista de dados para o Relatório Imported Parts Claim List - Invoice.  
	 * 
	 * @throws DaoException
	 */			
	public List listRelatorioImportedPartsInvoice1() throws DaoException{
		
		String sql = "SELECT ps.nome_parametro  parametro " +
					"     , ps.valor_parametro  valor " +
					"  FROM ym_sg_parametro_sistema ps " +
					" WHERE ps.nome_parametro IN ('INVOICE BANCO', 'INVOICE AGENCIA', 'INVOICE ACCOUNT')" ;                 

		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		List results = new ArrayList();	
		try {
			Iterator importedIt = query.list().iterator();
			
			while ( importedIt.hasNext() ) {
			
			RelatorioImportedPartsInvoiceVO relatorioImported = new RelatorioImportedPartsInvoiceVO();
			Object[] row = (Object[]) importedIt.next();
			
			relatorioImported.setParametro((String)row[0]);
			relatorioImported.setValor((String)row[1]);
			
			results.add(relatorioImported);
			}
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		return results;			
	}	
	
	/** Lista os dados para o Relatório Imported Parts Claim List. 
	 * 
	 * @return list
	 * 	<code>list</code> lista de dados para o Relatório Imported Parts Claim List - Invoice.  
	 * 
	 * @throws DaoException
	 */			
	public List listRelatorioImportedPartsInvoice2( String agencia
										          , String banco
										          , String conta) throws DaoException{
		
		String sql = "SELECT bc.nome_banco  banco            " +
					"      , bc.endereco||' - '||            " +
					"        bc.bairro  endereco             " +
					"      , bc.cidade  ||' - '||            " +
					"        bc.estado  ||' - '||            " +
					"        bc.pais    localidade           " +
					"      , 'ACCOUNT: '||bc.conta  conta    " +
					"      , 'AG. '     ||bc.agencia agencia " +
					"   FROM ym_sg_banco bc                  " +
					"  WHERE bc.banco   = to_number(:p_banco)" +
					"    AND bc.agencia = :p_agencia         " +
					"    AND bc.conta   = :p_conta           " ;               

		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		query.setParameter("p_banco", agencia);
		query.setParameter("p_agencia", banco);
		query.setParameter("p_conta", conta);
		
		List results = new ArrayList();
		try {
			Iterator importedIt = query.list().iterator();
			
			while ( importedIt.hasNext() ) {
			
				RelatorioImportedPartsInvoiceVO relatorioImported = new RelatorioImportedPartsInvoiceVO();
				Object[] row = (Object[]) importedIt.next();
				
				relatorioImported.setBanco((String)row[0]);
				relatorioImported.setEndereco((String)row[1]);
				relatorioImported.setLocalidade((String)row[2]);
				relatorioImported.setConta((String)row[3]);
				relatorioImported.setAgencia((String)row[4]);
				
				results.add(relatorioImported);
			}
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		
		return results;			
	}	
	
	/** Lista os dados para o Relatório Imported Parts Claim List. 
	 * 
	 * @return list
	 * 	<code>list</code> lista de dados para o Relatório Imported Parts Claim List - Invoice.  
	 * 
	 * @throws DaoException
	 */			
	public List listRelatorioImportedPartsInvoice3( String ano
                                                  , String mes) throws DaoException{
		
		String sql = "SELECT nvl(perc_comissao, 0)         comissao" +
		             "     , nvl(valor_dolar_contratado,0) vlDolar"  +
					 "  FROM ym_sg_moeda"                            +
					 " WHERE ano = to_number(:p_ano)"                +
					 "   AND mes = to_number(:p_mes)"                ;              

		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		query.setParameter("p_ano", ano);
		query.setParameter("p_mes", mes);
		
		List results = new ArrayList();
		try {
			Iterator importedIt = query.list().iterator();
			
			while ( importedIt.hasNext() ) {
			
	    		RelatorioImportedPartsInvoiceVO relatorioImported = new RelatorioImportedPartsInvoiceVO();
	    		Object[] row = (Object[]) importedIt.next();
	    		
	    		relatorioImported.setComissao(Double.valueOf(row[0].toString()));
	    		relatorioImported.setVlDolar(Double.valueOf(row[1].toString()));
	    		
	    		results.add(relatorioImported);
	            
			}		
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		return results;			
	}	
	
	/** Lista os dados para o Relatório Imported Parts Claim List. 
	 * 
	 * @return list
	 * 	<code>list</code> lista de dados para o Relatório Imported Parts Claim List - Invoice.  
	 * 
	 * @throws DaoException
	 */			
	public List listRelatorioImportedPartsInvoice4( Long empresa) throws DaoException{
		
		String sql = "SELECT nome_empresa  empresa"            + 
	                 "     , endereco||' - '||"                +
	                 "       bairro  ||' - '||"                +
	                 "       cidade         endEmp"            +
	                 "     , 'C.N.P.J'||' '||"                 +
	                 "       cnpj1          cnpjEmp"           +
	                 "     , 'INSCRIÇÃO ESTADUAL'||' '||"      +
	                 "       inscr_estadual incEstEmp"         +    
	                 "  FROM ym_sg_empresa emp"                +
	                 " WHERE emp.organization_id = :p_empresa ";  

		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		query.setParameter("p_empresa", empresa);
		
		List results = new ArrayList();
		try {
			Iterator importedIt = query.list().iterator();
			
			while ( importedIt.hasNext() ) {
			
				RelatorioImportedPartsInvoiceVO relatorioImported = new RelatorioImportedPartsInvoiceVO();
				Object[] row = (Object[]) importedIt.next();
				
				relatorioImported.setEmpresa((String)row[0]);
				relatorioImported.setEndEmp((String)row[1]);
				relatorioImported.setCnpjEmp((String)(row[2].toString()));
				relatorioImported.setIncEstEmp((String)row[3]);
				
				results.add(relatorioImported);
			}		
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		return results;			
	}
	
	/** Lista os dados para o Relatório Imported Parts Claim List. 
	 * 
	 * @return list
	 * 	<code>list</code> lista de dados para o Relatório Imported Parts Claim List - Invoice.  
	 * 
	 * @throws DaoException
	 */			
	public List listRelatorioImportedPartsInvoice5( String fornecedor) throws DaoException{
		
		String sql = "SELECT distinct fornecedor "      +
		             "     , endereco endFornec"        +
		             "     , cidade   cidFornec"        +
		             "     , pais     paisFornec"       +
					 "  FROM ym_sg_item_fornecedor"     +
					 " WHERE fornecedor = :p_fornecedor";

		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		query.setParameter("p_fornecedor", fornecedor);
		
		List results = new ArrayList();	
		try {
			Iterator importedIt = query.list().iterator();
			
			while ( importedIt.hasNext() ) {
			
				RelatorioImportedPartsInvoiceVO relatorioImported = new RelatorioImportedPartsInvoiceVO();
				Object[] row = (Object[]) importedIt.next();
				
				relatorioImported.setFornecedor((String)row[0]);
				relatorioImported.setEndFornec((String)row[1]);
				relatorioImported.setCidFornec((String)row[2]);
				relatorioImported.setPaisFornec((String)row[3]);
				
				results.add(relatorioImported);
			}		
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		return results;			
	}
	
	/** Lista os dados de Faturamento de um determinado período, de um empresa( ou geral) e linha de produto.
	 *  
	 *      
	 *  @param  orgId - Id da Organização
	 *  	<code>Long</code> Número que representa o id da Organização
	 *  
	 *  @param  dataInicioAp - Data de início para apuração
	 *  	<code>Date</code> Data para início da apuração dos dados para o relatório
	 *  
	 *  @param  dataFinalAp - Data final para apuração
	 *  	<code>Date</code> Data para fechamento da apuração dos dados para o relatório
	 *  
	 *  @param  linhaId - Id da Organização
	 *  	<code>Long</code> Linha do Produto
	 *  
	 *  @param  modelo - modelo definido para incluir no gráfico
	 *  	<code>String</code> Grupo Modelo do Produto
	 *  
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório Gráfico de Faturamento x Garantia.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      será lançada. Caso hajam problemas de regras de negócio, uma
	 *      BusinessRuleException (subclasse de BusinessException) será 
	 *      lançada.
	 */
	public List listFaturamento ( Long orgId, Date dataInicioAp, Date dataFinalAp, Long linhaId, String modelo ) throws DaoException {
		
		//System.out.println("Empresa :" + orgId + " Data inicio:" + dataInicioAp + " Data Final:" + dataFinalAp + " - Modelo:" + modelo);
		
		String sql = 	" SELECT  to_char(ft.data_nf, 'YYYY-MM')   		anomes			" +
						" 	 	  ,       'FATURAMENTO'            		serie			" +
						"         ,       round(sum(ft.vl_produto),2)   valor			" +
						" FROM    ym_sg_faturamento                	ft					" +
						"		  ,    ym_sg_modelo					md 					" +
						" WHERE ft.data_nf        >= :p_dataInicioAp 					" +
						" AND   ft.data_nf        <  :p_dataFinalAp						" +
						" AND   ft.organization_id = nvl(:p_orgId,ft.organization_id)   " +	
						" AND   ft.linha_id       = nvl(:p_linha,ft.linha_id)			" +
						" AND   ft.modelo         = md.modelo_id						" +
						" AND   md.inclui_grafico = nvl(:p_modelo, md.inclui_grafico)	" +
						" AND   nvl(ft.end_date, sysdate + 1) >= sysdate				" +
						" AND   nvl(md.end_date, sysdate + 1) >= sysdate				" +
						" group by to_char(ft.data_nf, 'YYYY-MM')						" +
						" order by to_char(ft.data_nf, 'YYYY-MM')						" ;
		
		//System.out.println("Query Fauramento Montada!" );
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		query.setDate("p_dataInicioAp"	, dataInicioAp);
		query.setDate("p_dataFinalAp" 	, dataFinalAp);
		
		if ( orgId != null )
			if ( orgId.longValue() == 0 )
				query.setString("p_orgId", null);
			else 
				query.setLong("p_orgId", orgId.longValue());
		else 
			query.setString("p_orgId", null);
			
		if ( linhaId != null )
			if ( linhaId.longValue() == 0 )
				query.setString("p_linha", null);
			else
				query.setLong("p_linha" 		, linhaId.longValue());
		else
			query.setString("p_linha", null);
		
		if ( "".equals(modelo) || ( modelo == null ) )
			query.setString("p_modelo", null);
		else
			query.setString("p_modelo", modelo);
		
		EntityGraficosIndividuaisVO entityGraficosIndividuaisFat = null;
		
		List results = new ArrayList();	
		try {
			
			//System.out.println("Executando a Query do Faturamento!" );
			List resultQuery =  query.list();
			//System.out.println("Tamanho:" +  resultQuery.size());
			
			Iterator it = resultQuery.iterator();
			while ( it.hasNext() ) {
			
				entityGraficosIndividuaisFat = new EntityGraficosIndividuaisVO();
				
				Object[] row = (Object[]) it.next();			
				
				entityGraficosIndividuaisFat.setCategory((String)row[0]);
				entityGraficosIndividuaisFat.setSerie("FATURAMENTO");
				entityGraficosIndividuaisFat.setValue((BigDecimal)row[2]);				
								
				results.add(entityGraficosIndividuaisFat);
			}	
			
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		
		//System.out.println("retorno:" + results.size());
		return results;	
		
	}
	
	/** Lista a quantidade de Faturamento de um determinado período, de um empresa( ou geral) por linha de produto.
	 *  
	 *      
	 *  @param  orgId - Id da Organização
	 *  	<code>Long</code> Número que representa o id da Organização
	 *  
	 *  @param  dataInicioAp - Data de início para apuração
	 *  	<code>Date</code> Data para início da apuração dos dados para o relatório
	 *  
	 *  @param  dataFinalAp - Data final para apuração
	 *  	<code>Date</code> Data para fechamento da apuração dos dados para o relatório
	 *  
	 *  @param  linhaId - Id da Organização
	 *  	<code>Long</code> Linha do Produto
	 *  
	 *  @param  modelo - modelo definido para incluir no gráfico
	 *  	<code>String</code> Grupo Modelo do Produto
	 *  
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório Gráfico de Faturamento x Garantia.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      será lançada. Caso hajam problemas de regras de negócio, uma
	 *      BusinessRuleException (subclasse de BusinessException) será 
	 *      lançada.
	 */
	public List listQtdeFaturamento ( Long orgId, Date dataInicioAp, Date dataFinalAp, Long linhaId, String modelo ) throws DaoException {
		
		//System.out.println("Empresa :" + orgId + " Data inicio:" + dataInicioAp + " Data Final:" + dataFinalAp);
		
		String sql = 	" SELECT  to_char(ft.data_nf, 'YYYY-MM')   		anomes    		" +
						" 	 	  ,       'FATURAMENTO'            		serie			" +
						"         ,       count(ft.faturamento_id)  qtde	     		" +
						" FROM    ym_sg_faturamento                ft					" +
						"		  ,    ym_sg_modelo					md 					" +
						" WHERE ft.data_nf        >= :p_dataInicioAp 					" +
						" AND   ft.data_nf        <  :p_dataFinalAp						" +
						" AND   ft.linha_id       = nvl(:p_linha,ft.linha_id)			" +
						" AND   ft.organization_id = nvl(:p_orgId,ft.organization_id)   " +	
						" AND   ft.modelo         = md.modelo_id						" +
						" AND   md.inclui_grafico = nvl(:p_modelo, md.inclui_grafico)	" +
						" AND   nvl(ft.end_date, sysdate + 1) >= sysdate				" +
						" AND   nvl(md.end_date, sysdate + 1) >= sysdate				" +
						" group by to_char(ft.data_nf, 'YYYY-MM')						" +
						" order by to_char(ft.data_nf, 'YYYY-MM')						" ;
		
		//System.out.println("Query Fauramento Montada!" );
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		query.setDate("p_dataInicioAp"	, dataInicioAp);
		query.setDate("p_dataFinalAp" 	, dataFinalAp);
		query.setLong("p_linha" 		, linhaId.longValue());
		
		if ( orgId != null )
			if ( orgId.longValue() == 0 )
				query.setString("p_orgId", null);
			else 
				query.setLong("p_orgId", orgId.longValue());
		else 
			query.setString("p_orgId", null);
			
		if ( linhaId != null )
			if ( linhaId.longValue() == 0 )
				query.setString("p_linha", null);
			else
				query.setLong("p_linha" 		, linhaId.longValue());
		else
			query.setString("p_linha", null);
		
		if ( "".equals(modelo) || ( modelo == null ) )
			query.setString("p_modelo", null);
		else
			query.setString("p_modelo", modelo);
		
		EntityGraficosIndividuaisVO entityGraficosIndividuaisFat = null;
		
		List results = new ArrayList();	
		try {
			
			//System.out.println("Executando a Query do Faturamento!" );
			List resultQuery =  query.list();
			//System.out.println("Tamanho:" +  resultQuery.size());
			
			Iterator it = resultQuery.iterator();
			while ( it.hasNext() ) {
			
				entityGraficosIndividuaisFat = new EntityGraficosIndividuaisVO();
				
				Object[] row = (Object[]) it.next();			
				
				entityGraficosIndividuaisFat.setCategory((String)row[0]);
				entityGraficosIndividuaisFat.setSerie("FATURAMENTO");
				entityGraficosIndividuaisFat.setValue((BigDecimal)row[2]);				
								
				results.add(entityGraficosIndividuaisFat);
			}	
			
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		
		//System.out.println("retorno:" + results.size());
		return results;	
		
	}
	
	/** Lista os dados de Faturamento e Garantia para o Relatório de Gráficos Individuais - Gráfico 1.
	 *  
	 *      
	 *  @param  orgId - Id da Organização
	 *  	<code>Long</code> Número que representa o id da Organização
	 *  
	 *  @param  dataInicioAp - Data de início para apuração
	 *  	<code>Date</code> Data para início da apuração dos dados para o relatório
	 *  
	 *  @param  dataFinalAp - Data final para apuração
	 *  	<code>Date</code> Data para fechamento da apuração dos dados para o relatório
	 *  
	 *  
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório Gráfico de Faturamento x Garantia.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      será lançada. Caso hajam problemas de regras de negócio, uma
	 *      BusinessRuleException (subclasse de BusinessException) será 
	 *      lançada.
	 */
	public List listFaturamentoByGarantia ( Long orgId, Date dataInicioAp, Date dataFinalAp ) throws DaoException {
		
		//System.out.println("Empresa :" + orgId + " Data inicio:" + dataInicioAp + " Data Final:" + dataFinalAp);
		String sql = 	" SELECT  to_char(ft.data_nf, 'YYYY-MM')   		anomes    						" +
						" 	 	  ,       'FATURAMENTO'            		serie							" +
						"         ,       round(sum(ft.vl_produto),2)   valor     						" +
						" FROM    ym_sg_faturamento                ft									" +
						" WHERE ft.data_nf between :p_dataInicioAp and :p_dataFinalAp					" +
						" AND   ft.linha_id       = 1													" +
						" group by to_char(ft.data_nf, 'YYYY-MM')										" +
						" UNION ALL																		" +
						" select  anomes																" +
						"         ,      'GARANTIA'  serie												" +
						"         ,      sum (valor_gar)	valor										" +
						" from (																		" +
						"        select  to_char(yl.fechamento_date , 'YYYY-MM')          anomes		" +
						"                ,      'GARANTIA'												" +
						"                ,      sum (gl.vl_peca_garantia*gl.quantidade)  valor_gar		" +
						"		From    ym_sg_lote         						yl						" +
						"		        ,    ym_sg_garantia_header		        gh						" +
						"				,    ym_sg_garantia_line                gl						" +
						"		        ,    ym_sg_faturamento                  yf						" +
						"		Where yl.lote_id             = gh.lote_id								" +
						"		and   gh.garantia_id         = gl.garantia_id							" +
						"		and (yf.chassi12            = gh.chassi12	OR yf.chassi = gh.modelo)	" +
						"		and   yf.organization_id     = nvl(:p_orgId, yf.organization_id)		" +
						"		and   yl.status_lote_id      in (9,11)									" +
						"		and   gh.status_mov_id       in (2,16)									" +
						"		and   yl.linha_id            =  1										" +
						"		and   yl.fechamento_date between :p_dataInicioAp and :p_dataFinalAp		" +
						"		group by to_char(yl.fechamento_date , 'YYYY-MM')						" +
						"		UNION ALL																" +
						"		select  to_char(yl.fechamento_date , 'YYYY-MM')   	anomes 				" +
						"		        ,      'GARANTIA'  												" +
						"		        ,      sum (ys.vl_servico)                	valor_gar			" +
						"		From    ym_sg_lote                             		yl					" +
						"		        ,    ym_sg_garantia_header		        	gh					" +
						"		        ,    ym_sg_servico_grupo                	ys					" +
						"		        ,    ym_sg_faturamento                  	yf					" +
						"		Where yl.lote_id              =  gh.lote_id								" +
						"		and   gh.servico_grupo_id     =  ys.servico_grupo_id					" +
						"		and (yf.chassi12            = gh.chassi12	OR yf.chassi = gh.modelo)	" +
						"		and   yf.organization_id      =  nvl(:p_orgId, yf.organization_id)		" +
						"		and   yl.status_lote_id       in (9, 11)								" +
						"		and   gh.status_mov_id        in (2,16)									" +
						"		and   yl.linha_id             =  1										" +
						"		and   yl.fechamento_date between :p_dataInicioAp and :p_dataFinalAp		" +
						"		group by to_char(yl.fechamento_date , 'YYYY-MM')						" +
						"		UNION ALL																" +
						"		select 	to_char(yl.fechamento_date , 'YYYY-MM')       anomes			" +
						"				,      'GARANTIA'   						  					" +
						"				,      sum (gh.vl_servico_terceiro)           valor_gar			" +
						"		From 	ym_sg_lote                                    yl				" +
						"				,    ym_sg_garantia_header		              gh				" +
						"				,    ym_sg_faturamento                        yf				" +
						"		Where yl.lote_id             = gh.lote_id								" +
						"		and (yf.chassi12             = gh.chassi12	OR yf.chassi = gh.modelo)	" +
						"		and   yf.organization_id     = nvl(:p_orgId, yf.organization_id)		" +
						"		and   yl.status_lote_id      in (9, 11)									" +
						"		and   gh.status_mov_id       in (2,16)									" +
						"		and   yl.linha_id            =  1										" +
						"		and   yl.fechamento_date between :p_dataInicioAp and :p_dataFinalAp		" +
						"		group by to_char(yl.fechamento_date , 'YYYY-MM')						" +
						" )																				" +
						" group by anomes																" +
						" order by anomes, serie													    ";
			
		//System.out.println("Query Montada!" );
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		query.setLong("p_orgId" 		, orgId.longValue());
		query.setDate("p_dataInicioAp"	, dataInicioAp);
		query.setDate("p_dataFinalAp" 	, dataFinalAp);
		
		List results = new ArrayList();	
		try {
			
			//System.out.println("Executando a Query!" );
			List resultQuery =  query.list();
			//System.out.println("Query Executada!" );
			//System.out.println("Tamanho:" +  resultQuery.size());
			Iterator it = resultQuery.iterator();
			while ( it.hasNext() ) {
			
				EntityGraficosIndividuaisVO entityGraficosIndividuais = new EntityGraficosIndividuaisVO();
				
				Object[] row = (Object[]) it.next();			
				
				entityGraficosIndividuais.setCategory((String)row[0]);
				entityGraficosIndividuais.setSerie((String)row[1]);
				entityGraficosIndividuais.setValue((BigDecimal)row[2]);
				
				results.add(entityGraficosIndividuais);
				
			}		
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		
		//System.out.println("retorno:" + results.size());
		return results;	
		
	}
	
	/** Lista os dados de Faturamento e Garantia para o Relatório de Gráficos Individuais  por Linha - Gráfico 1.
	 *  
	 *      
	 *  @param  orgId - Id da Organização
	 *  	<code>Long</code> Número que representa o id da Organização
	 *  
	 *  @param  dataInicioAp - Data de início para apuração
	 *  	<code>Date</code> Data para início da apuração dos dados para o relatório
	 *  
	 *  @param  dataFinalAp - Data final para apuração
	 *  	<code>Date</code> Data para fechamento da apuração dos dados para o relatório
	 *  
	 *  @param  linhaId - Id da Organização
	 *  	<code>Long</code> Linha do Produto
	 *  
	 *  @param  modelo - Modelo do produto
	 *  	<code>String</code> modelo do produto para o filtro 
	 *  
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório Gráfico de Faturamento x Garantia.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      será lançada. Caso hajam problemas de regras de negócio, uma
	 *      BusinessRuleException (subclasse de BusinessException) será 
	 *      lançada.
	 */
	public List listFaturamentoByGarantiaANDLinha ( Long orgId, Date dataInicioAp, Date dataFinalAp, Long linhaId, String modelo) throws DaoException {
		
		
		String sql = 	" select  anomes																" +
						"         ,      'GARANTIA'  				serie								" +
						"         ,      round(sum (valor_gar),2)	valor								" +
						" from (																		" +
						"        select  to_char(yl.liberacao_date , 'YYYY-MM')          	anomes		" +
						"                ,      'GARANTIA'		serie									" +
						"                ,      sum (gl.vl_peca_garantia*gl.quantidade)  valor_gar		" +
						"		From    ym_sg_lote         						yl						" +
						"		        ,    ym_sg_garantia_header		        gh						" +
						"				,    ym_sg_garantia_line                gl						" +
						"		        ,    ym_sg_faturamento                  yf						" +
						"			    ,    ym_sg_modelo						md						" +
						"		Where yl.lote_id             = gh.lote_id								" +
						"		and   yl.tipo_lote_id        = 2										" +
						"		and   gh.garantia_id         = gl.garantia_id							" +
						"		and   replace(yf.chassi,'-','') = replace(gh.modelo,'-','')				" +
						"		and   yf.organization_id     = nvl(:p_orgId, yf.organization_id)		" +
						"		and   yl.linha_id            = nvl(:p_linha,yl.linha_id)				" +
						"		and   yf.modelo         	 = md.modelo_id								" +
						"		and   md.inclui_grafico 	 = nvl(:p_modelo, md.inclui_grafico)		" +
						"		and nvl(yl.end_date, sysdate + 1) >= sysdate							" +
						"       and nvl(gh.end_date, sysdate + 1) >= sysdate							" +
						"       and nvl(gl.end_date, sysdate + 1) >= sysdate							" +
						"       and nvl(yf.end_date, sysdate + 1) >= sysdate							" +
						"		and   to_char(yl.liberacao_date, 'YYYY-MM') = :p_anomes					" +
						"		group by to_char(yl.liberacao_date , 'YYYY-MM')							" +
						"		UNION ALL																" +
						"		select  to_char(yl.liberacao_date , 'YYYY-MM')   	anomes 				" +
						"		        ,      'GARANTIA'  							serie				" +
						"		        ,      sum (ys.vl_servico)                	valor_gar			" +
						"		From    ym_sg_lote                             		yl					" +
						"		        ,    ym_sg_garantia_header		        	gh					" +
						"		        ,    ym_sg_servico_grupo                	ys					" +
						"		        ,    ym_sg_faturamento                  	yf					" +
						"			    ,    ym_sg_modelo						md						" +
						"		Where yl.lote_id              =  gh.lote_id								" +
						"		and   yl.tipo_lote_id         = 2										" +
						"		and   gh.servico_grupo_id     =  ys.servico_grupo_id					" +
						"		and   replace(yf.chassi,'-','') = replace(gh.modelo,'-','')				" +
						"		and   yf.organization_id      =  nvl(:p_orgId, yf.organization_id)		" +
						"		and   yl.linha_id             =  nvl(:p_linha,yl.linha_id)			    " +
						"		and   yf.modelo         	  = md.modelo_id							" +
						"		and   md.inclui_grafico 	  = nvl(:p_modelo, md.inclui_grafico)		" +
						"		and nvl(yl.end_date, sysdate + 1) >= sysdate							" +
						"       and nvl(gh.end_date, sysdate + 1) >= sysdate							" +
						"       and nvl(yf.end_date, sysdate + 1) >= sysdate							" +
						"       and nvl(ys.end_date, sysdate + 1) >= sysdate							" +
						"		and  to_char(yl.liberacao_date, 'YYYY-MM') = :p_anomes					" +
						"		group by to_char(liberacao_date , 'YYYY-MM')							" +
						"		UNION ALL																" + 
						"		select 	to_char(liberacao_date , 'YYYY-MM')       anomes				" +
						"				,      'GARANTIA'   						serie				" +
						"				,      sum (gh.vl_servico_terceiro)           valor_gar			" +
						"		From 	ym_sg_lote                                    yl				" +
						"				,    ym_sg_garantia_header		              gh				" +
						"				,    ym_sg_faturamento                        yf				" +
						"			    ,    ym_sg_modelo						md						" +
						"		Where yl.lote_id             = gh.lote_id								" +
						"		and   yl.tipo_lote_id        = 2										" +
						"		and   replace(yf.chassi,'-','') = replace(gh.modelo,'-','')				" +
						"		and   yf.organization_id     = nvl(:p_orgId, yf.organization_id)		" +
						"		and   yl.linha_id            = nvl(:p_linha,yl.linha_id)				" +
						"		and   yf.modelo         	 = md.modelo_id								" +
						"		and   md.inclui_grafico 	 = nvl(:p_modelo, md.inclui_grafico)		" +
						"		and nvl(yl.end_date, sysdate + 1) >= sysdate							" +
						"       and nvl(gh.end_date, sysdate + 1) >= sysdate							" +
						"       and nvl(yf.end_date, sysdate + 1) >= sysdate							" +
						"		and   to_char(yl.liberacao_date, 'YYYY-MM') = :p_anomes					" +
						"		group by to_char(liberacao_date , 'YYYY-MM')							" + 
						" )																				" +
						" group by anomes																" +
						" order by anomes, serie													    ";
		
		List results      = new ArrayList();
		List Faturamentos = this.listFaturamento(orgId, dataInicioAp, dataFinalAp, linhaId, modelo); 
		Session session   = super.getSession();
		
		try {
			
			//System.out.println("Query Montada!" );			
			SQLQuery query = session.createSQLQuery(sql);
			
			if ( orgId != null )
				if ( orgId.longValue() == 0 )
					query.setString("p_orgId", null);
				else 
					query.setLong("p_orgId", orgId.longValue());
			else 
				query.setString("p_orgId", null);
				
			if ( linhaId != null )
				if ( linhaId.longValue() == 0 )
					query.setString("p_linha", null);
				else
					query.setLong("p_linha" 		, linhaId.longValue());
			else
				query.setString("p_linha", null);
						
			if ( "".equals(modelo) || ( modelo == null ) )
				query.setString("p_modelo", null);
			else
				query.setString("p_modelo", modelo);
			
			Iterator it = Faturamentos.iterator();
			
			while ( it.hasNext() ) {
				
				EntityGraficosIndividuaisVO entityGraficosIndividuaisFat = (EntityGraficosIndividuaisVO) it.next();
				EntityGraficosIndividuaisVO entityGraficosIndividuaisGar = new EntityGraficosIndividuaisVO();
				
				query.setParameter("p_anomes", entityGraficosIndividuaisFat.getCategory());
			/*
				System.out.println("Parâmetros Sub -  p_orgId: " + orgId.longValue()); 
				System.out.println("------------------p_anomes:" + entityGraficosIndividuaisFat.getCategory());
				System.out.println("------------------p_linha:"  + linhaId.longValue());	
			*/	
				List resultQuerySub =  query.list();
				//System.out.println("------------------Tamanho Sub:" +  resultQuerySub.size());
				
				if ( !resultQuerySub.isEmpty() ) {
					Iterator itSub = resultQuerySub.iterator();
					if ( itSub.hasNext() ) {
						Object[] rowSub = (Object[]) itSub.next();
						
						entityGraficosIndividuaisGar.setCategory((String)rowSub[0]);
						entityGraficosIndividuaisGar.setSerie("GARANTIA");
						entityGraficosIndividuaisGar.setValue((BigDecimal)rowSub[2]);					
						
					} else {
						
						entityGraficosIndividuaisGar.setCategory( entityGraficosIndividuaisFat.getCategory() );
						entityGraficosIndividuaisFat.setSerie("GARANTIA");
						entityGraficosIndividuaisGar.setValue(BigDecimal.ZERO);
					
					}	
					
					results.add(entityGraficosIndividuaisFat);
					results.add(entityGraficosIndividuaisGar);
					
				} 
				
			}		
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		
		//System.out.println("retorno:" + results.size());
		return results;	
		
	}
	
	/** Lista os dados de Faturamento e Garantia para o Relatório de Gráficos Individuais Linha - Gráfico 1.
	 *  
	 *      
	 *  @param  orgId - Id da Organização
	 *  	<code>Long</code> Número que representa o id da Organização
	 *  
	 *  @param  dataInicioAp - Data de início para apuração
	 *  	<code>Date</code> Data para início da apuração dos dados para o relatório
	 *  
	 *  @param  dataFinalAp - Data final para apuração
	 *  	<code>Date</code> Data para fechamento da apuração dos dados para o relatório
	 *  
	 *  @param  modelo - Modelo do produto
	 *  	<code>String</code> modelo do produto para o filtro  
	 *  
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório Gráfico de Faturamento x Garantia.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      será lançada. Caso hajam problemas de regras de negócio, uma
	 *      BusinessRuleException (subclasse de BusinessException) será 
	 *      lançada.
	 */
	public List listGraphFaturamentoByGarantiaANDLinha ( Long orgId, Date dataInicioAp, Date dataFinalAp, String modelo ) throws DaoException {
		
		String sql = 	" SELECT  to_char(ft.data_nf, 'YYYY-MM')   anomes" +
						"        ,       md.inclui_grafico        inclui_grafico" +
						"        ,       'FATURAMENTO'" +
						"        ,       sum(ft.vl_produto)       valor_faturado" +
						" FROM    ym_sg_faturamento   ft" +
						"        ,    ym_sg_modelo	md" +
						" WHERE  ft.modelo             = md.modelo_id" +
						" AND   ft.data_nf BETWEEN :p_dataInicioAp AND :p_dataFinalAp" +
						" AND   md.inclui_grafico        = :p_modelo " +	
						//" AND   md.inclui_grafico is not null" +
						" AND   ft.linha_id       = 1" +
						" AND    ft.end_date is null  " +  
						" AND    md.end_date is null  " +
						" GROUP BY md.inclui_grafico, to_char(ft.data_nf, 'YYYY-MM')" +
						"						" +
						" UNION ALL				" +
						"						" +
						" SELECT  anomes" +
						"        ,      inclui_grafico" +
						"        ,      'GARANTIA'" +
						"        ,      sum (valor_gar)" +
						" FROM (" +
						"        SELECT  to_char(yl.fechamento_date , 'YYYY-MM')   anomes" +
						"                ,       md.inclui_grafico			 	  inclui_grafico" +
						"                ,      'GARANTIA'" +
						"                ,      sum (gl.vl_peca_garantia*gl.quantidade)  valor_gar" +
						"        FROM    ym_sg_lote                  yl" +
						"                ,    ym_sg_garantia_header	gh" +
						"                ,    ym_sg_garantia_line    gl" +
						"                ,    ym_sg_faturamento      yf" +
						"                ,    ym_sg_modelo			md" +
						"        WHERE yl.lote_id             = gh.lote_id" +
						"        AND   gh.garantia_id         = gl.garantia_id" +
						"        AND   yf.modelo              = md.modelo_id" +
						"		 AND   md.inclui_grafico        = :p_modelo " +	
						"        AND   yf.chassi12            = gh.chassi12 " +
						"        AND   yf.organization_id     = nvl(:p_orgId, yf.organization_id)" +
						"        AND   yl.status_lote_id      in (9,11)" +
						"        AND   gh.status_mov_id       in (2,16)  " +
						"        AND   yl.linha_id            =  1   " +
						"		 AND   gh.end_date is null " +
						"		 AND   gl.end_date is null " +
						"		 AND   yl.end_date is null " +
						"		 AND   yf.end_date is null " +
						"		 AND   md.end_date is null " +
						"        AND   yl.fechamento_date BETWEEN :p_dataInicioAp AND :p_dataFinalAp" +
						"        GROUP BY md.inclui_grafico, to_char(yl.fechamento_date , 'YYYY-MM')" +
						"											" +
						"        UNION ALL							" +
						"											" +
						"        SELECT  to_char(yl.fechamento_date , 'YYYY-MM')  anomes" +
						"                ,       md.inclui_grafico			    inclui_grafico" +
						"                ,      'GARANTIA'" +
						"                ,      sum (ys.vl_servico)              valor_gar" +
						"        FROM    ym_sg_lote                              yl" +
						"                ,    ym_sg_garantia_header		         gh" +
						"                ,    ym_sg_servico_grupo                ys" +
						"                ,    ym_sg_faturamento                  yf" +
						"                ,    ym_sg_modelo			             md" +
						"        WHERE   yl.lote_id           =  gh.lote_id" +
						"        AND   gh.servico_grupo_id    =  ys.servico_grupo_id" +
						"        AND   yf.chassi12            = gh.chassi12 " +
						"        AND   yf.modelo              =  md.modelo_id" +
						"		 AND   md.inclui_grafico      = :p_modelo " +	
						"        AND   yf.organization_id     =  nvl(:p_orgId, yf.organization_id) " +
						"        AND   yl.status_lote_id      in (9, 11)  " +
						"        AND   gh.status_mov_id       in (2,16)    " +
						"        AND   yl.linha_id             =  1" +
						"		 AND   gh.end_date is null " +
						"		 AND   yl.end_date is null " +
						"	     AND   yf.end_date is null " +
						"        AND   md.end_date is null " +
						"        AND   yl.fechamento_date BETWEEN :p_dataInicioAp AND :p_dataFinalAp" +
						"        GROUP BY md.inclui_grafico, to_char(yl.fechamento_date , 'YYYY-MM')" +
						"									" +
						"        UNION ALL					" +
						"									" +
						"        SELECT  to_char(yl.fechamento_date , 'YYYY-MM')   anomes" +
						"                ,       md.inclui_grafico			      inclui_grafico" +
						"                ,      'GARANTIA'" +
						"                ,      sum (gh.vl_servico_terceiro)      valor_gar" +
						"        FROM    ym_sg_lote                               yl" +
						"                ,    ym_sg_garantia_header		          gh" +
						"                ,    ym_sg_faturamento                   yf" +
						"                ,    ym_sg_modelo			              md" +
						"        WHERE   yl.lote_id             = gh.lote_id" +
						"        AND   yf.chassi12              = gh.chassi12 " +
						"        AND   yf.modelo                = md.modelo_id" +
						"		 AND   md.inclui_grafico        = :p_modelo " +	
						"        AND   yf.organization_id       = nvl(:p_orgId, yf.organization_id) " +
						"        AND   yl.status_lote_id        in (9, 11)" +
						"        AND   gh.status_mov_id         in (2,16)" +
						"        AND   yl.linha_id              =  1" +
						"		 AND   gh.end_date is null " +
						"		 AND   yf.end_date is null " +
						"		 AND   md.end_date is null " +
						"        AND   yl.fechamento_date BETWEEN :p_dataInicioAp AND :p_dataFinalAp" +
						"        GROUP BY md.inclui_grafico, to_char(yl.fechamento_date , 'YYYY-MM')" +
						" )" +
						" GROUP BY inclui_grafico, anomes" +
						" ORDER BY anomes";	

		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);

		if ( new Long(0).equals(orgId) ) 
			query.setParameter("p_orgId" , "");
		else
			query.setParameter("p_orgId" , orgId);
		
		query.setParameter("p_modelo"	    , modelo);
		query.setParameter("p_dataInicioAp"	, dataInicioAp);
		query.setParameter("p_dataFinalAp" 	, dataFinalAp);

		List results = new ArrayList();	
		try {
			Iterator it = query.list().iterator();
	
			while ( it.hasNext() ) {
	
				EntityGraficosIndividuaisVO entityGraficosIndividuais = new EntityGraficosIndividuaisVO();
	
				Object[] row = (Object[]) it.next();			
				entityGraficosIndividuais.setCategory((String)row[0]);
				entityGraficosIndividuais.setModelo((String)row[1]);
				entityGraficosIndividuais.setSerie((String)row[2]);
				entityGraficosIndividuais.setValue((BigDecimal)row[3]);
	
				results.add(entityGraficosIndividuais);
	
			}		
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		return results;	

	}
	
	/** Lista os dados de Faturamento e Quant. Garantia para o Relatório de Gráficos Individuais - Gráfico 3.
	 *  
	 *      
	 *  @param  orgId - Id da Organização
	 *  	<code>Long</code> Número que representa o id da Organização
	 *  
	 *  @param  dataInicioAp - Data de início para apuração
	 *  	<code>Date</code> Data para início da apuração dos dados para o relatório
	 *  
	 *  @param  dataFinalAp - Data final para apuração
	 *  	<code>Date</code> Data para fechamento da apuração dos dados para o relatório
	 *  
	 *  @param  linhaId - Id da linha do produto
	 *  	<code>Long</code> Número que representa o id da linha
	 *
	 *  @param  modelo - modelo definido para incluir no gráfico
	 *  	<code>String</code> Grupo Modelo do Produto
	 *
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório Gráfico de Faturamento x Garantia.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      será lançada. Caso hajam problemas de regras de negócio, uma
	 *      BusinessRuleException (subclasse de BusinessException) será 
	 *      lançada.
	 */
	public List listFaturamentoByQtdeGarantia ( Long orgId, Date dataInicioAp, Date dataFinalAp, Long linhaId, String modelo ) throws DaoException {
		
		String sql = 	" select  to_char(yl.liberacao_date, 'YYYY-MM')  anomes				" +
						"        ,      'GARANTIA'											" +
						"        ,      count(9)      					qtd_gar				" +
						" From    ym_sg_lote                          	yl					" +
						"        ,    ym_sg_garantia_header		    	gh					" +
						"        ,    ym_sg_faturamento              	yf					" +
						"		 , 	  ym_sg_modelo                      md					" +
						" Where yl.lote_id             = gh.lote_id							" +
						" and   yl.tipo_lote_id        = 2									" +
						" and   replace(yf.chassi,'-','') = replace(gh.modelo,'-','') 		" +
						" and   yf.organization_id    = nvl(:p_orgId, yf.organization_id)	" +
						" and   yf.chassi           = gh.modelo								" +
						" and   yf.modelo           = md.modelo_id							" +
						" and   md.inclui_grafico   = nvl(:p_modelo, md.inclui_grafico)		" +
						" and   yl.linha_id  		=  nvl(:p_linha,yl.linha_id)			" +
						" and   nvl(yl.end_date, sysdate + 1) >= sysdate 					" +
						" and   nvl(gh.end_date, sysdate + 1) >= sysdate					" +
						" and   nvl(yf.end_date, sysdate + 1) >= sysdate					" +
						" and   to_char(yl.liberacao_date, 'YYYY-MM') = :p_anomes			" +
						" group by to_char(yl.liberacao_date, 'YYYY-MM')					";	

		List results      = new ArrayList();
		List Faturamentos = this.listQtdeFaturamento(orgId, dataInicioAp, dataFinalAp, linhaId, null); 
		Session session   = super.getSession();
		
		try {
			
			//System.out.println("Query Montada!" );			
			SQLQuery query = session.createSQLQuery(sql);
			
			if ( orgId != null )
				if ( orgId.longValue() == 0 )
					query.setString("p_orgId", null);
				else 
					query.setLong("p_orgId", orgId.longValue());
			else 
				query.setString("p_orgId", null);
				
			if ( linhaId != null )
				if ( linhaId.longValue() == 0 )
					query.setString("p_linha", null);
				else
					query.setLong("p_linha" 		, linhaId.longValue());
			else
				query.setString("p_linha", null);
						
			if ( "".equals(modelo) || ( modelo == null ) )
				query.setString("p_modelo", null);
			else
				query.setString("p_modelo", modelo);
						
			Iterator it = Faturamentos.iterator();
			
			while ( it.hasNext() ) {
				
				EntityGraficosIndividuaisVO entityGraficosIndividuaisFat = (EntityGraficosIndividuaisVO) it.next();
				EntityGraficosIndividuaisVO entityGraficosIndividuais = new EntityGraficosIndividuaisVO();
				
				query.setParameter("p_anomes", entityGraficosIndividuaisFat.getCategory());
				
				//System.out.println("Parâmetros Sub -  p_orgId: " + orgId.longValue()); 
				//System.out.println("------------------p_anomes:" + entityGraficosIndividuaisFat.getCategory());
				//System.out.println("------------------p_linha:"  + linhaId.longValue());	
				
				List resultQuerySub =  query.list();
				//System.out.println("------------------Tamanho Sub:" +  resultQuerySub.size());
				
				if ( !resultQuerySub.isEmpty() ) {
					Iterator itSub = resultQuerySub.iterator();
					if ( itSub.hasNext() ) {
						Object[] rowSub = (Object[]) itSub.next();
						
						entityGraficosIndividuais.setCategory((String)rowSub[0]);
						entityGraficosIndividuais.setFirstValue((BigDecimal)rowSub[2]);
						entityGraficosIndividuais.setSecondValue(entityGraficosIndividuaisFat.getValue());
						
					} else {
						
						entityGraficosIndividuais.setCategory( entityGraficosIndividuaisFat.getCategory() );
						entityGraficosIndividuais.setFirstValue(BigDecimal.ZERO);
						entityGraficosIndividuais.setSecondValue(BigDecimal.ZERO);
					
					}	
					
					results.add(entityGraficosIndividuais);
					
				} 
				
			}		
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		
		//System.out.println("retorno:" + results.size());
		return results;	
		
	}
	
	/** Lista os dados de VALOR ACUMULADO GARANTIA 0 KM x INDICE GARANTIA 0 KM para o Relatório de Gráficos Individuais - Gráfico 5.
	 *  
	 *      
	 *  @param  orgId - Id da Organização
	 *  	<code>Long</code> Número que representa o id da Organização
	 *  
	 *  @param  dataInicioAp - Data de início para apuração
	 *  	<code>Date</code> Data para início da apuração dos dados para o relatório
	 *  
	 *  @param  dataFinalAp - Data final para apuração
	 *  	<code>Date</code> Data para fechamento da apuração dos dados para o relatório
	 *  
	 *  @param  linhaId - Id da linha do produto
	 *  	<code>Long</code> Id da linha do produto
	 *  
	 *  @param  modelo - modelo definido para incluir no gráfico
	 *  	<code>String</code> Grupo Modelo do Produto
	 *  
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório Gráfico de Faturamento x Garantia.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      será lançada. Caso hajam problemas de regras de negócio, uma
	 *      BusinessRuleException (subclasse de BusinessException) será 
	 *      lançada.
	 */
	public List listAcumuladoGarantiaByIndice ( Long orgId, Date dataInicioAp, Date dataFinalAp, Long linhaId, String modelo ) throws DaoException {
		
		String sqlGarantia = 	" select  anomes																" +
								"        , 'GARANTIAZERO'														" +
								"        , round(sum(valor_gar0km),2)  valor_gar								" +
								" from (       																	" +
								"		select  to_char(yl.liberacao_date , 'YYYY-MM')   anomes					" +
								"		        ,      'GARANTIAZERO'											" +
								"		        ,      sum (gl.vl_peca_garantia*gl.quantidade)  valor_gar0km	" +
								"		From    ym_sg_lote                     		yl							" +
								"		        ,    ym_sg_garantia_header		    gh							" +
								"		        ,    ym_sg_garantia_line            gl							" +
								"		        ,    ym_sg_faturamento              yf							" +
								"		 		, 	 ym_sg_modelo                  md							" +
								"		Where yl.lote_id             = gh.lote_id								" +
								"		and   gh.garantia_id         = gl.garantia_id							" +
								"		and   replace(yf.chassi,'-','') = replace(gh.modelo,'-','')				" +
								"		and   yf.organization_id     = nvl(:p_orgId, yf.organization_id)		" +
								"		and   gh.quilometragem       =  0										" +
								"		and   yl.linha_id            = nvl(:p_linha,yl.linha_id)				" +
								" 		and   yf.modelo           	 = md.modelo_id								" +
								" 		and   md.inclui_grafico   	 = nvl(:p_modelo, md.inclui_grafico)		" +
								"		and   yl.liberacao_date >= :p_dataInicioAp								" +
								"		and   yl.liberacao_date <= :p_dataFinalAp								" +
								" 		and   nvl(yl.end_date, sysdate + 1) >= sysdate 							" +
								" 		and   nvl(gh.end_date, sysdate + 1) >= sysdate							" +
								" 		and   nvl(gl.end_date, sysdate + 1) >= sysdate							" +
								" 		and   nvl(yf.end_date, sysdate + 1) >= sysdate							" +
								"		group by to_char(yl.liberacao_date , 'YYYY-MM')							" +
								"		UNION ALL																" +
								"		select to_char(yl.liberacao_date , 'YYYY-MM')   anomes					" +
								"		        ,      'GARANTIAZERO'											" +
								"		        ,      sum (ys.vl_servico)              valor_gar0km			" +
								"		From    ym_sg_lote               				yl						" +
								"		        ,    ym_sg_garantia_header				gh						" +
								"		        ,    ym_sg_servico_grupo	    		ys						" +
								"		        ,    ym_sg_faturamento            		yf						" +
								"		 		, 	 ym_sg_modelo                  	    md						" +
								"		Where yl.lote_id              = gh.lote_id								" +
								"		and   gh.servico_grupo_id     = ys.servico_grupo_id						" +
								"		and   replace(yf.chassi,'-','') = replace(gh.modelo,'-','')				" +
								"		and   yf.organization_id    = nvl(:p_orgId, yf.organization_id)			" +
								"		and   gh.quilometragem      =  0										" +
								"		and   yl.linha_id           =  nvl(:p_linha,yl.linha_id)				" +
								" 		and   yf.modelo           	 = md.modelo_id								" +
								" 		and   md.inclui_grafico   	 = nvl(:p_modelo, md.inclui_grafico)		" +
								"		and   yl.liberacao_date >= :p_dataInicioAp								" +
								"		and   yl.liberacao_date <= :p_dataFinalAp								" +
								" 		and   nvl(yl.end_date, sysdate + 1) >= sysdate 							" +
								" 		and   nvl(gh.end_date, sysdate + 1) >= sysdate							" +
								" 		and   nvl(ys.end_date, sysdate + 1) >= sysdate							" +
								" 		and   nvl(yf.end_date, sysdate + 1) >= sysdate							" +
								"		group by to_char(yl.liberacao_date , 'YYYY-MM')							" +
								"		UNION ALL																" +
								"		select 	to_char(yl.liberacao_date , 'YYYY-MM')   anomes					" +
								"				,      'GARANTIAZERO'											" +
								"				,      sum (gh.vl_servico_terceiro)      valor_gar0km			" +
								"		From 	ym_sg_lote               yl										" +
								"				,    ym_sg_garantia_header		gh								" +
								"				,    ym_sg_faturamento            yf							" +
								"		 		, 	 ym_sg_modelo                  	    md						" +
								"		Where yl.lote_id              = gh.lote_id								" +
								"		and   replace(yf.chassi,'-','') = replace(gh.modelo,'-','')				" +
								"		and   yf.organization_id    = nvl(:p_orgId, yf.organization_id)			" +
								"		and   gh.quilometragem      =  0										" +
								"		and   yl.linha_id           =  nvl(:p_linha,yl.linha_id)				" +
								" 		and   yf.modelo           	 = md.modelo_id								" +
								" 		and   md.inclui_grafico   	 = nvl(:p_modelo, md.inclui_grafico)		" +
								"		and   yl.liberacao_date >= :p_dataInicioAp								" +
								"		and   yl.liberacao_date <= :p_dataFinalAp								" +
								" 		and   nvl(yl.end_date, sysdate + 1) >= sysdate 							" +
								" 		and   nvl(gh.end_date, sysdate + 1) >= sysdate							" +
								" 		and   nvl(yf.end_date, sysdate + 1) >= sysdate							" +
								"		group by to_char(yl.liberacao_date , 'YYYY-MM')							" +
								" )																				" +
								" group by anomes																" +
								" order by anomes";	
		
		String sqlGarZero = 	" select  anomes																" +
								"        ,       'GARANTIA'														" +
								"        ,       sum (valor_gar)                         valor_gar				" +
								" from(																			" +
								"        select to_char(yl.liberacao_date , 'YYYY-MM')   anomes					" +
								"                ,      'GARANTIA'												" +
								"                ,      sum (gl.vl_peca_garantia*gl.quantidade) 	valor_gar	" +
								"        From    ym_sg_lote                     		yl						" +
								"                ,    ym_sg_garantia_header		    	gh						" +
								"                ,    ym_sg_garantia_line            		gl					" +
								"                ,    ym_sg_faturamento              		yf					" +
								"		 		 , 	  ym_sg_modelo                  	    md					" +
								"        Where yl.lote_id             = gh.lote_id								" +
								"        and   gh.garantia_id         = gl.garantia_id							" +
								"        and   replace(yf.chassi,'-','') = replace(gh.modelo,'-','')			" +
								"        and   yf.organization_id     = nvl(:p_orgId, yf.organization_id)		" +
								"        and   yl.linha_id            = nvl(:p_linha,yl.linha_id)				" +
								" 		 and   yf.modelo           	 = md.modelo_id								" +
								" 		 and   md.inclui_grafico   	 = nvl(:p_modelo, md.inclui_grafico)		" +
								"        and   to_char(yl.liberacao_date , 'YYYY-MM') = :p_anomes				" +
								" 		 and   nvl(yl.end_date, sysdate + 1) >= sysdate 						" +
								" 		 and   nvl(gh.end_date, sysdate + 1) >= sysdate							" +
								" 		 and   nvl(gl.end_date, sysdate + 1) >= sysdate							" +
								" 		 and   nvl(yf.end_date, sysdate + 1) >= sysdate							" +
								"        group by to_char(yl.liberacao_date , 'YYYY-MM')						" +
								"        UNION ALL																" +
								"        select to_char(yl.liberacao_date , 'YYYY-MM')	anomes					" +
								"                ,      'GARANTIA'												" +
								"                ,      sum (ys.vl_servico)                  	valor_gar		" +
								"        From    ym_sg_lote               			yl							" +
								"                ,    ym_sg_garantia_header			gh							" +
								"                ,    ym_sg_servico_grupo	    			ys					" +
								"                ,    ym_sg_faturamento            		yf						" +
								"		 		 , 	  ym_sg_modelo                  	    md					" +
								"        Where yl.lote_id              = gh.lote_id								" +
								"        and   gh.servico_grupo_id     = ys.servico_grupo_id					" +
								"        and   replace(yf.chassi,'-','') = replace(gh.modelo,'-','')			" +
								"        and   yf.organization_id    = nvl(:p_orgId, yf.organization_id)		" +
								"        and   yl.linha_id           =  nvl(:p_linha,yl.linha_id)				" +
								" 		 and   yf.modelo           	 = md.modelo_id								" +
								" 		 and   md.inclui_grafico   	 = nvl(:p_modelo, md.inclui_grafico)		" +
								"        and   to_char(yl.liberacao_date , 'YYYY-MM') = :p_anomes				" +
								" 		 and   nvl(yl.end_date, sysdate + 1) >= sysdate 						" +
								" 		 and   nvl(gh.end_date, sysdate + 1) >= sysdate							" +
								" 		 and   nvl(ys.end_date, sysdate + 1) >= sysdate							" +
								" 		 and   nvl(yf.end_date, sysdate + 1) >= sysdate							" +
								"        group by to_char(yl.liberacao_date , 'YYYY-MM')						" +
								"        UNION ALL																" +
								"        select  to_char(yl.liberacao_date , 'YYYY-MM')   anomes				" +
								"                ,      'GARANTIA'												" +
								"                ,      sum (gh.vl_servico_terceiro)             valor_gar		" +
								"        From    ym_sg_lote               			yl							" +
								"                ,    ym_sg_garantia_header			gh							" +
								"                ,    ym_sg_faturamento            		yf						" +
								"		 		 , 	 ym_sg_modelo                  	    md						" +
								"        Where yl.lote_id             = gh.lote_id								" +
								"        and   replace(yf.chassi,'-','') = replace(gh.modelo,'-','')			" +
								"        and   yf.organization_id     = nvl(:p_orgId, yf.organization_id)		" +
								"        and   yl.linha_id            =  nvl(:p_linha,yl.linha_id)			" +
								" 		 and   yf.modelo           	 = md.modelo_id								" +
								" 		 and   md.inclui_grafico   	 = nvl(:p_modelo, md.inclui_grafico)		" +
								"        and   to_char(yl.liberacao_date , 'YYYY-MM') = :p_anomes				" +
								" 		 and   nvl(yl.end_date, sysdate + 1) >= sysdate 						" +
								" 		 and   nvl(gh.end_date, sysdate + 1) >= sysdate							" +
								" 		 and   nvl(yf.end_date, sysdate + 1) >= sysdate							" +
								"        group by to_char(yl.liberacao_date , 'YYYY-MM')						" +
								" )																				" +
								" group by anomes																" +
								" order by anomes";

		Session session = super.getSession();
		SQLQuery queryGar 		= session.createSQLQuery(sqlGarantia);
		SQLQuery queryGarZero 	= null;
		
		if ( orgId != null )
			if ( orgId.longValue() == 0 )
				queryGar.setString("p_orgId", null);
			else 
				queryGar.setLong("p_orgId", orgId.longValue());
		else 
			queryGar.setString("p_orgId", null);
			
		if ( linhaId != null )
			if ( linhaId.longValue() == 0 )
				queryGar.setString("p_linha", null);
			else
				queryGar.setLong("p_linha" 		, linhaId.longValue());
		else
			queryGar.setString("p_linha", null);
		
		queryGar.setParameter("p_dataInicioAp"	, dataInicioAp);
		queryGar.setParameter("p_dataFinalAp" 	, dataFinalAp);
		
		if ( "".equals(modelo) || ( modelo == null ) )
			queryGar.setString("p_modelo", null);
		else
			queryGar.setString("p_modelo", modelo);
		
		List results = new ArrayList();	
		try {
			Iterator it = queryGar.list().iterator();
			
			while ( it.hasNext() ) {
				
				EntityGraficosIndividuaisVO entityGraficosIndividuaisGar     = new EntityGraficosIndividuaisVO();
				EntityGraficosIndividuaisVO entityGraficosIndividuaisGarZero = new EntityGraficosIndividuaisVO();
				
				//Recuperamos as Garantias
				Object[] rowGar = (Object[]) it.next();	
				
				entityGraficosIndividuaisGar.setCategory((String)rowGar[0]);
				entityGraficosIndividuaisGar.setSerie("GARANTIAZERO");
				entityGraficosIndividuaisGar.setValue(
										rowGar[2] != null ? (BigDecimal)rowGar[2] : BigDecimal.ZERO
				);
				
				queryGarZero = session.createSQLQuery(sqlGarZero);
				
				//Setamos os parâmetros para as Garantias de Zero KM
				if ( orgId != null )
					if ( orgId.longValue() == 0 )
						queryGarZero.setString("p_orgId", null);
					else 
						queryGarZero.setLong("p_orgId", orgId.longValue());
				else 
					queryGarZero.setString("p_orgId", null);
					
				if ( linhaId != null )
					if ( linhaId.longValue() == 0 )
						queryGarZero.setString("p_linha", null);
					else
						queryGarZero.setLong("p_linha" 		, linhaId.longValue());
				else
					queryGarZero.setString("p_linha", null);
				
				queryGarZero.setParameter("p_anomes"	, (String)rowGar[0]);
				
				if ( "".equals(modelo) || ( modelo == null ) )
					queryGarZero.setString("p_modelo", null);
				else
					queryGarZero.setString("p_modelo", modelo);
				
				// Recuperamos as Garantias de Zero KM
				Object[] rowGarZero = (Object[]) queryGarZero.uniqueResult();
				entityGraficosIndividuaisGarZero.setCategory((String)rowGarZero[0]);
				entityGraficosIndividuaisGarZero.setSerie("GARANTIA");
				entityGraficosIndividuaisGarZero.setValue(
										rowGarZero[2] != null ? (BigDecimal)rowGarZero[2] : BigDecimal.ZERO
				);
								
				results.add(entityGraficosIndividuaisGar);
				results.add(entityGraficosIndividuaisGarZero);
				
			}		
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		return results;	
	}
	
	/** Lista os dados das PRINCIPAIS PEÇAS para o Relatório de Gráficos Individuais - Gráfico 6 (Tabela). 
	 *  
	 *      
	 *  @param  orgId - Id da Organização
	 *  	<code>Long</code> Número que representa o id da Organização
	 *  
	 *  @param  dataFinalAp - Data final para apuração
	 *  	<code>Date</code> Data para fechamento da apuração dos dados para o relatório  
	 *  
	 *  @param  linhaId - Id da Linha do produto
	 *  	<code>Long</code> Id da Linha do produto  
	 *  
	 *  @param  modelo - Modelo do produto
	 *  	<code>String</code> modelo do produto para o filtro
	 *  
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório Gráfico de PRINCIPAIS PEÇAS.
	 *      
	 * @throws DaoException
	 */
	public List listValuesTablePeca ( Long orgId, Date dataFinalAp, Long linhaId, String modelo ) throws DaoException {
		
		String sql = 	" SELECT 	it.item_code			codigo  			" +
						"		 	, it.descricao			descricao   		" +
						"			, round(sum (gl.vl_peca_garantia*gl.quantidade ),2) valor_gar	" +
						"			, sum(gl.quantidade)    qtd_gar	" +
						" FROM 		ym_sg_lote              yl	" +
						"			, ym_sg_garantia_header gh	" +
						"			, ym_sg_garantia_line   gl	" +
						"			, ym_sg_item            it	" +
						"			, ym_sg_modelo          md	" +
						"	        , ym_sg_faturamento     yf	" +
						" WHERE 	yl.lote_id             = gh.lote_id	" +
						" AND   	gh.garantia_id         = gl.garantia_id	" +
						" AND       yf.chassi              = gh.modelo " +
						" AND   	gh.organization_id     = nvl(:p_orgId, gh.organization_id)" +
						" AND	   	gl.item_id             = it.item_id	" +
						" AND   	yl.linha_id            = nvl(:p_linha,yl.linha_id) " +
						" AND     yf.modelo                = md.modelo_id" +
						" AND     md.inclui_grafico = nvl(:p_modelo, md.inclui_grafico)" +
						" AND     yl.liberacao_date >= trunc(:p_dataFinalAp, 'mm') " +
						" AND     yl.liberacao_date <= LAST_DAY(:p_dataFinalAp) " +
						" AND     nvl(yl.end_date, sysdate + 1) >= sysdate	" +
						" AND     nvl(gh.end_date, sysdate + 1) >= sysdate	" +
						" AND     nvl(gl.end_date, sysdate + 1) >= sysdate	" +
						" AND     nvl(it.end_date, sysdate + 1) >= sysdate	" +
						//" AND       rownum < 6 " +
						" GROUP BY  it.item_code, it.descricao" +
						" ORDER BY 3 desc";	

		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		if ( orgId != null )
			if ( orgId.longValue() == 0 )
				query.setString("p_orgId", null);
			else 
				query.setLong("p_orgId", orgId.longValue());
		else 
			query.setString("p_orgId", null);
			
		if ( linhaId != null )
			if ( linhaId.longValue() == 0 )
				query.setString("p_linha", null);
			else
				query.setLong("p_linha" , linhaId.longValue());
		else
			query.setString("p_linha", null);
		
		query.setParameter("p_dataFinalAp" 	, dataFinalAp);
		
		if ( "".equals(modelo) || ( modelo == null ) )
			query.setString("p_modelo", null);
		else
			query.setString("p_modelo", modelo);
		
		List results = new ArrayList();	
		try {
			Iterator it = query.list().iterator();
			
			while ( it.hasNext() ) {
			
				RelatorioGraficosIndividuaisTableVO graficosIndividuaisTable = new RelatorioGraficosIndividuaisTableVO();
				
				Object[] row = (Object[]) it.next();			
				graficosIndividuaisTable.setItemCode((String)row[0]);
				graficosIndividuaisTable.setDescricao((String)row[1]);
				graficosIndividuaisTable.setValorGarantia(new Double(row[2].toString()));
				graficosIndividuaisTable.setQuantidadeItem(new Long(row[3].toString()));
				
				results.add(graficosIndividuaisTable);
			
			}		
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		
		//System.out.println("Qunatidade de itens retornados:" + results.size());
		return results;			
		
	}
	
	/** Lista os dados das PRINCIPAIS PEÇAS para o Relatório de Gráficos Individuais por Linha- Gráfico 6 (Tabela). 
	 *  
	 *      
	 *  @param  orgId - Id da Organização
	 *  	<code>Long</code> Número que representa o id da Organização
	 *  
	 *  @param  dataFinalAp - Data final para apuração
	 *  	<code>Date</code> Data para fechamento da apuração dos dados para o relatório  
	 *  
	 *  @param  modelo - Modelo
	 *  	<code>String</code> Modelo do produto  
	 *  
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório Gráfico de PRINCIPAIS PEÇAS.
	 *      
	 * @throws DaoException
	 */
	public List listValuesTablePecaByModelo ( Long orgId, Date dataFinalAp, String modelo ) throws DaoException {
		
		//System.out.println("listValuesTablePecaByModelo - Parâmetros - orgId:" + orgId + " - dataFinalAp:" + dataFinalAp + " - modelo:" + modelo);
		
		String sql = 	" SELECT codigo" +
						"		 ,      descricao" +
						"        ,      total" +
						"        ,      qtde_peca" +
						" FROM (" +
						"		SELECT  codigo  			codigo" +
						"		        ,      descricao	descricao" +
						"		        ,      sum(nvl(valor_gar,0)) + sum(nvl(valor_servico,0))	total " +
						"		        ,      qtde_peca" +
						"		FROM (" +
						"		        SELECT  it.item_code    		codigo" +
						"		                ,   it.descricao        descricao " +
						"		                ,   sum (gl.vl_peca_garantia*gl.quantidade +" +
						"			                decode( gl.line_id, 1, gh.vl_servico_terceiro, 0))	  valor_gar" +
						"		                ,   decode(gl.line_id, 1, get_vlmobra(gh.garantia_id), 0) valor_servico" +
						"		                ,   sum ( gl.quantidade )        qtde_peca" +
						"		        FROM    ym_sg_lote                 		yl" +
						"		                ,    ym_sg_garantia_header      gh" +
						"		                ,    ym_sg_garantia_line        gl" +
						"		                ,    ym_sg_item                 it" +
						"		                ,    ym_sg_faturamento      	yf" +
						"		                ,    ym_sg_modelo               md" +
						"		        WHERE   yl.lote_id           = gh.lote_id" +
						"		        AND   gh.garantia_id         = gl.garantia_id" +
						"		        AND   yf.chassi              = gh.modelo" +
						"		        AND   gl.item_id             = it.item_id" +
						"		        AND   yf.modelo              = md.modelo_id" +
						"		        AND   md.inclui_grafico	     = :p_modelo" +
						"		        AND   yf.organization_id     = nvl(:p_orgId, yf.organization_id) " +
						"		        AND   yl.status_lote_id      in (9,11)" +
						"		        AND   gh.status_mov_id       =  2" +
						"		        AND   yl.linha_id            =  1" +
						"		        AND   yl.fechamento_date BETWEEN to_date(concat('01/', to_char(:p_dataFinalAp,'MM/YYYY')), 'DD/MM/YYYY') " +
						"										 AND     last_day(:p_dataFinalAp)" +
						"		        GROUP BY it.item_code, it.descricao, decode(gl.line_id, 1, get_vlmobra(gh.garantia_id), 0)" +
						" 		)" +
						"		GROUP BY codigo, descricao, qtde_peca" +
						"		ORDER BY total desc" +
						" )" ;	

		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		if ( new Long(0).equals(orgId) )
			query.setParameter("p_orgId"	, "");
		else 
			query.setParameter("p_orgId"	, orgId);
		
		query.setParameter("p_modelo" 		, modelo);
		query.setParameter("p_dataFinalAp" 	, dataFinalAp);
		
		List results = new ArrayList();	
		try {
			Iterator it = query.list().iterator();
			
			while ( it.hasNext() ) {
			
				RelatorioGraficosIndividuaisTableVO graficosIndividuaisTable = new RelatorioGraficosIndividuaisTableVO();
				
				Object[] row = (Object[]) it.next();			
				graficosIndividuaisTable.setItemCode((String)row[0]);
				graficosIndividuaisTable.setDescricao((String)row[1]);
				graficosIndividuaisTable.setValorGarantia(new Double(row[2].toString()));
				graficosIndividuaisTable.setQuantidadeItem(new Long(row[3].toString()));
				
				results.add(graficosIndividuaisTable);
			
			}
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		//System.out.println("Resultados:" + results.size());
		
		return results;			
		
	}
	
	/** Lista os dados para os gráficos relatório Service Report. 
	 *  
	 *      
	 *  @param  orgId - Id da Organização
	 *  	<code>Long</code> Número que representa o id da Organização
	 *  
	 *  @param  dataAp - Datapara apuração
	 *  	<code>Date</code> Data para fechamento da apuração dos dados para o relatório  
	 *  
	 *  @param part - parte
	 *  	<code>int</code> parte do relatório solicitada  
	 *  
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório Gráfico Service Report.
	 *      
	 * @throws DaoException
	 */
	public List listValuesServiceReportParte ( Long orgId, Date dataAp, int part ) throws DaoException {
		
		String sql = "";
		switch (part) {
			case 1: sql = ReportQueryGraphicHelper.getQueryServiceReport1();
					break;
					
			case 2: sql = ReportQueryGraphicHelper.getQueryServiceReport2();
			  		break;
			  		
			case 3: sql = ReportQueryGraphicHelper.getQueryServiceReport3();
			  		break;
			  		
			default: throw new DaoException("A parte solicitada do relatório Service Report é inválida!");
		}

		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		if ( orgId != null )
			if ( orgId.longValue() == 0 )
				query.setString("p_orgId", null);
			else 
				query.setLong("p_orgId", orgId.longValue());
		else 
			query.setString("p_orgId", null);
				
		query.setDate("p_dataAp" 	, dataAp);
		
		List results = new ArrayList();	
		try {
			Iterator it = query.list().iterator();
			
			while ( it.hasNext() ) {
			
				RelatorioServiceReportGraphVO relatorioServiceReportGraphVO = new RelatorioServiceReportGraphVO();
				
				Object[] row = (Object[]) it.next();			
				
				relatorioServiceReportGraphVO.setAno((String)row[0]);
				relatorioServiceReportGraphVO.setSequencia(row[1]);
				relatorioServiceReportGraphVO.setItem((String)row[2]);
				relatorioServiceReportGraphVO.setMes01((BigDecimal)row[3]);
				relatorioServiceReportGraphVO.setMes02((BigDecimal)row[4]);
				relatorioServiceReportGraphVO.setMes03((BigDecimal)row[5]);
				relatorioServiceReportGraphVO.setMes04((BigDecimal)row[6]);
				relatorioServiceReportGraphVO.setMes05((BigDecimal)row[7]);
				relatorioServiceReportGraphVO.setMes06((BigDecimal)row[8]);
				relatorioServiceReportGraphVO.setMes07((BigDecimal)row[9]);
				relatorioServiceReportGraphVO.setMes08((BigDecimal)row[10]);
				relatorioServiceReportGraphVO.setMes09((BigDecimal)row[11]);
				relatorioServiceReportGraphVO.setMes10((BigDecimal)row[12]);
				relatorioServiceReportGraphVO.setMes11((BigDecimal)row[13]);
				relatorioServiceReportGraphVO.setMes12((BigDecimal)row[14]);
							
				results.add(relatorioServiceReportGraphVO);
			
			}	
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		return results;			
		
	}
	
	/** Lista os dados de Garantia Paga por Modelo para o Relatório Gráfico - Garantia Paga por Modelo de Produto.
	 *  
	 *      
	 *  @param  orgId - Id da Organização
	 *  	<code>Long</code> Número que representa o id da Organização
	 *  
	 *  @param  grupoModelo - Grupo do modelo
	 *  	<code>String</code> Quando for nulo, recupera todos os grupos
	 *  
	 *  @param  dataInicioAp - Data de início para apuração
	 *  	<code>Date</code> Data para início da apuração dos dados para o relatório
	 *  
	 *  @param  dataFinalAp - Data final para apuração
	 *  	<code>Date</code> Data para fechamento da apuração dos dados para o relatório
	 *  
	 *  @param  typeGraph - Tipo da recuperação dos dados
	 *  	<code>String</code> True para acumulado	
	 *  
	 *  @param linhaId 
	 *  	<code>Long</code> Id da linha do produto
	 *  
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório Gráfico de Faturamento x Garantia.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      será lançada. Caso hajam problemas de regras de negócio, uma
	 *      BusinessRuleException (subclasse de BusinessException) será 
	 *      lançada.
	 */
	public List listGarantiaPagaByModelo ( Long orgId, String grupoModelo, Date dataInicioAp, Date dataFinalAp, String typeGraph, Long linhaId ) throws DaoException {
		
		//System.out.println("listGarantiaPagaByModelo - Parâmetros - orgId:		 " + orgId);
		//System.out.println("listGarantiaPagaByModelo - Parâmetros - linhaId:	 " + linhaId);
		//System.out.println("listGarantiaPagaByModelo - Parâmetros - grupoModelo: " + grupoModelo);
		//System.out.println("listGarantiaPagaByModelo - Parâmetros - dataInicioAp:" + dataInicioAp);
		//System.out.println("listGarantiaPagaByModelo - Parâmetros - dataFinalAp: " + dataFinalAp);
		//System.out.println("listGarantiaPagaByModelo - Parâmetros - typeGraph:   " + typeGraph);
		
		String wherePeriodo = "";
		
		if ( EntityGraficoGarantiaPagaVO.PERIODO.equalsIgnoreCase(typeGraph) )
			wherePeriodo = " AND   yl.fechamento_date BETWEEN to_date('01' || to_char(add_months(:p_dataFinalAp, -11) ,'MM/YYYY'),'DD/MM/YYYY') AND  :p_dataFinalAp ";
		else if ( EntityGraficoGarantiaPagaVO.ACUMULADO.equalsIgnoreCase(typeGraph) )
			wherePeriodo = " AND   yl.fechamento_date BETWEEN :p_dataInicioAp AND :p_dataFinalAp " ;
		
		String sql = "";
		
		if ( EntityGraficoGarantiaPagaVO.QTDE.equalsIgnoreCase(typeGraph) ) {
			
			sql = 	" SELECT 	md. inclui_grafico   			inclui_grafico" +
								"           , count (gh.garantia_id)		garantia" +
								" FROM    	ym_sg_lote                      yl" +
								"         	,    ym_sg_garantia_header	    gh" +
								"           ,    ym_sg_garantia_line        gl" +
								"           ,    ym_sg_faturamento          yf" +
								"           ,    ym_sg_modelo			    md" +
								" WHERE   yl.lote_id           = gh.lote_id" +
								" AND   gh.garantia_id         = gl.garantia_id" +
								" AND   yf.modelo              = md.modelo_id" +
								" AND   md.grupo_modelo        = nvl(:p_grupoModelo, md.grupo_modelo) " +
								" AND   md.inclui_grafico	     is not null " +
								" AND   replace(yf.chassi,'-','') = replace(gh.modelo,'-','') " +
								" AND   yf.organization_id     = nvl(:p_orgId, yf.organization_id) " +
								" AND   yl.status_lote_id      in (9,11)" +
								" AND   gh.status_mov_id       =  2" +
								" AND   yl.linha_id            = nvl(:p_linha,yl.linha_id) " +
								" AND   yl.fechamento_date 	BETWEEN to_date('01' || to_char(add_months(:p_dataFinalAp, -11) ,'MM/YYYY'),'DD/MM/YYYY') AND  :p_dataFinalAp " +
								" GROUP BY md.inclui_grafico";

		} else {
			
			sql = 	" SELECT  	inclui_grafico" +
						"        		,   sum (valor_gar)" +
						" FROM (" +
						"        SELECT md. inclui_grafico        					inclui_grafico" +
						"               , sum (gl.vl_peca_garantia*gl.quantidade)  	valor_gar" +
						"        FROM    ym_sg_lote                                 yl" +
						"               ,    ym_sg_garantia_header		            gh" +
						"               ,    ym_sg_garantia_line                    gl" +
						"               ,    ym_sg_faturamento                      yf" +
						"               ,    ym_sg_modelo			                md" +
						"        WHERE   yl.lote_id           = gh.lote_id" +
						"        AND   gh.garantia_id         = gl.garantia_id" +
						"        AND   yf.modelo              = md.modelo_id" +
						"        AND   md.grupo_modelo        = nvl(:p_grupoModelo, md.grupo_modelo)" +
						"        AND   md.inclui_grafico	  is not null	" +
						"        AND   replace(yf.chassi,'-','') = replace(gh.modelo,'-','') " +
						"        AND   yf.organization_id     = nvl(:p_orgId, yf.organization_id) " +
						"        AND   yl.status_lote_id      in (9,11) " +
						"        AND   gh.status_mov_id       =  2 " +
						"        AND   yl.linha_id            =  nvl(:p_linha,yl.linha_id) " +
						wherePeriodo +        
						"        GROUP BY md.inclui_grafico" +
						"							" +
						"        UNION ALL			" +
						"							" +
						"        SELECT  md.inclui_grafico		        inclui_grafico" +
						"                , sum (ys.vl_servico)          valor_gar" +
						"        FROM    ym_sg_lote                     yl" +
						"                ,    ym_sg_garantia_header	    gh" +
						"                ,    ym_sg_servico_grupo       ys" +
						"                ,    ym_sg_faturamento         yf" +
						"                ,    ym_sg_modelo			    md" +
						"        WHERE   yl.lote_id              =  gh.lote_id " +
						"        AND     gh.servico_grupo_id     =  ys.servico_grupo_id " +
						"        AND     replace(yf.chassi,'-','') =  replace(gh.modelo,'-','') " +
						"        AND     yf.modelo               =  md.modelo_id " +
						"        AND     md.grupo_modelo         =  nvl(:p_grupoModelo, md.grupo_modelo) " +
						"        AND     md.inclui_grafico	    is not null		" +
						"        AND     yf.organization_id      =  nvl(:p_orgId, yf.organization_id) " +
						"        AND     yl.status_lote_id       in (9, 11) " +
						"        AND     gh.status_mov_id        =  2 " +
						"        AND     yl.linha_id             =  nvl(:p_linha,yl.linha_id) " +
						wherePeriodo + 
						"        GROUP BY md.inclui_grafico" +
						"						" +
						"        UNION ALL		" +
						"						" +
						"        SELECT  md.inclui_grafico				    inclui_grafico" +
						"                ,  sum (gh.vl_servico_terceiro)    valor_gar" +
						"        FROM    ym_sg_lote                         yl" +
						"                ,    ym_sg_garantia_header		    gh" +
						"                ,    ym_sg_faturamento             yf" +
						"                ,    ym_sg_modelo			        md" +
						"        WHERE   yl.lote_id             = gh.lote_id " +
						"        AND     replace(yf.chassi,'-','') = replace(gh.modelo,'-','') " +
						"        AND     yf.modelo              = md.modelo_id " +
						"        AND     md.grupo_modelo        = nvl(:p_grupoModelo, md.grupo_modelo) " +
						"        AND     md.inclui_grafico	   is not null		" +
						"        AND     yf.organization_id     = nvl(:p_orgId, yf.organization_id) " +
						"        AND     yl.status_lote_id      in (9, 11) " +
						"        AND     gh.status_mov_id       =  2 " +
						"        AND     yl.linha_id            =  nvl(:p_linha,yl.linha_id) " +
						wherePeriodo + 
						"        GROUP BY md.inclui_grafico" +
						" 									" +
						" )" +
						" GROUP BY inclui_grafico" +
						" order by inclui_grafico";	
		}
		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		if ( orgId != null )
			if ( orgId.longValue() == 0 )
				query.setString("p_orgId", null);
			else 
				query.setLong("p_orgId", orgId.longValue());
		else 
			query.setString("p_orgId", null);
		
		if ( linhaId != null )
			if ( linhaId.longValue() == 0 )
				query.setString("p_linha", null);
			else 
				query.setLong("p_linha", linhaId.longValue());
		else 
			query.setString("p_linha", null);
		
		if ( grupoModelo != null && !"".equals(grupoModelo) )
			query.setString("p_grupoModelo", grupoModelo);
		else
			query.setString("p_grupoModelo", null);
		
		if ( EntityGraficoGarantiaPagaVO.ACUMULADO.equalsIgnoreCase(typeGraph) )
			query.setDate("p_dataInicioAp", dataInicioAp);
		
		query.setDate("p_dataFinalAp", dataFinalAp);
		
		List results = new ArrayList();		
		try {
			Iterator it = query.list().iterator();
			
			while ( it.hasNext() ) {
			
				EntityGraficoGarantiaPagaVO entityGraficoGarantiaPaga = new EntityGraficoGarantiaPagaVO();
				
				Object[] row = (Object[]) it.next();			
				entityGraficoGarantiaPaga.setModelo((String)row[0]);
				entityGraficoGarantiaPaga.setValue((BigDecimal)row[1]);
				
				results.add(entityGraficoGarantiaPaga);
				
			}		
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		//System.out.println(" listGarantiaPagaByModelo -Tamanho:" + results.size());
		
		return results;	
	}
	
	/** Lista os dados para o Relatório Gráfico Garantia Mensal Percentual. 
	 *  
	 *      
	 *  @param  orgId - Id da Organização
	 *  	<code>Long</code> Número que representa o id da Organização
	 *  
	 *  @param  grupoModelo - Grupo do modelo
	 *  	<code>String</code> Quando for nulo, recupera todos os grupos
	 *  
	 *  @param  dataInicioAp - Data de início para apuração
	 *  	<code>Date</code> Data para início da apuração dos dados para o relatório
	 *  
	 *  @param  dataFinalAp - Data final para apuração
	 *  	<code>Date</code> Data para fechamento da apuração dos dados para o relatório
	 *  
	 *  @param linhaId 
	 *  	<code>Long</code> Id da linha do produto
	 *  
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório Gráfico de PRINCIPAIS PEÇAS.
	 *      
	 * @throws DaoException
	 */
	public List listValuesGraphGarantiaMensalPercentual ( Long orgId, String grupoModelo, Date dataInicioAp, Date dataFinalAp, Long linhaId ) throws DaoException {
		
		//System.out.println("listValuesGraphGarantiaMensalPercentual - Parâmetros - orgId:" + orgId + " - dataInicioAp:" + dataInicioAp + " - dataFinalAp:" + dataFinalAp + " - linhaId:" + linhaId);
		
		String sql = 	" SELECT anomes														" +
						"       , anomesVL													" +
						"       , grupo_modelo												" +
						"       , SUM(valor_gar)											" +
						" FROM (															" +
						"    SELECT to_char(yl.fechamento_date,'YYYYMM')     anomes			" +
						"           , to_char(yl.fechamento_date,'MM-YYYY')  anomesVL		" +
						"           , md.grupo_modelo	                     grupo_modelo	" +
						"           , SUM (gl.vl_peca_garantia*gl.quantidade) valor_gar		" +
						"    FROM ym_sg_lote                           yl					" +
						"         ,    ym_sg_garantia_header		   gh					" +
						"         ,    ym_sg_garantia_line             gl					" +
						"         ,    ym_sg_faturamento               yf					" +
						"         ,    ym_sg_modelo			           md					" +
						"    WHERE yl.lote_id             = gh.lote_id						" +
						"    AND   gh.garantia_id         = gl.garantia_id					" +
						"    AND   yf.modelo              = md.modelo_id					" +
						"    AND   md.grupo_modelo        = NVL(:p_grupoModelo, md.grupo_modelo)" +
						"    AND   md.inclui_grafico	     IS NOT NULL					" +
						"    AND   REPLACE(yf.chassi,'-','') = REPLACE(gh.modelo,'-','')	" +
						"    AND   yf.organization_id     = NVL(:p_orgId, yf.organization_id)" +
						"    AND   yl.status_lote_id      IN (9,11)							" +
						"    AND   gh.status_mov_id       = 2								" +
						"    AND   yl.linha_id            = NVL(:p_linha,yl.linha_id)		" +
						"    AND   yl.fechamento_date BETWEEN :p_dataInicioAp AND :p_dataFinalAp" +
						"    GROUP BY md.grupo_modelo, to_char(yl.fechamento_date,'YYYYMM'),to_char(yl.fechamento_date,'MM-YYYY')" +
						"    UNION ALL   													" +
						"    SELECT to_char(yl.fechamento_date,'YYYYMM')      anomes		" +
						"           , to_char(yl.fechamento_date,'MM-YYYY')   anomesVL		" +
						"           , md.grupo_modelo				          grupo_modelo	" +
						"           , SUM(ys.vl_servico)                      valor_gar		" +
						"    FROM ym_sg_lote                                  yl			" +
						"         , ym_sg_garantia_header		              gh			" +
						"         , ym_sg_servico_grupo                       ys			" +
						"         , ym_sg_faturamento                         yf			" +
						"         , ym_sg_modelo			                  md			" +
						"    WHERE yl.lote_id              =  gh.lote_id					" +
						"    AND   gh.servico_grupo_id     =  ys.servico_grupo_id			" +
						"    AND   REPLACE(yf.chassi,'-','') = REPLACE(gh.modelo,'-','')	" +
						"    AND   yf.modelo               = md.modelo_id					" +
						"    AND   md.grupo_modelo         = NVL(:p_grupoModelo, md.grupo_modelo)" +
						"    AND   md.inclui_grafico	   IS NOT NULL 						" +
						"    AND   yf.organization_id      = NVL(:p_orgId, yf.organization_id) " +
						"    AND   yl.status_lote_id       IN (9, 11)						" +
						"    AND   gh.status_mov_id        = 2								" +
						"    AND   yl.linha_id             = NVL(:p_linha,yl.linha_id)		" +
						"    AND   yl.fechamento_date BETWEEN :p_dataInicioAp AND :p_dataFinalAp " +
						"    GROUP BY md.grupo_modelo, to_char(yl.fechamento_date,'YYYYMM'),to_char(yl.fechamento_date,'MM-YYYY') " +
						"    UNION ALL   													" +
						"    SELECT to_char(yl.fechamento_date,'YYYYMM')     anomes 		" +
						"           , to_char(yl.fechamento_date,'MM-YYYY')  anomesVL		" +
						"           , md.grupo_modelo                        grupo_modelo 	" +
						"           , SUM(gh.vl_servico_terceiro)            valor_gar		" +
						"    FROM  ym_sg_lote                                yl				" +
						"          , ym_sg_garantia_header		             gh				" +
						"          , ym_sg_faturamento                       yf				" +
						"          , ym_sg_modelo			                 md				" +
						"    WHERE yl.lote_id                = gh.lote_id					" +
						"    AND   REPLACE(yf.chassi,'-','') = REPLACE(gh.modelo,'-','')	" +
						"    AND   yf.modelo              = md.modelo_id					" +
						"    AND   md.grupo_modelo        = NVL(:p_grupoModelo, md.grupo_modelo)" +
						"    AND   md.inclui_grafico	  IS NOT NULL 						" +
						"    AND   yf.organization_id     = NVL(:p_orgId, yf.organization_id) " +
						"    AND   yl.status_lote_id      IN(9, 11)							" +
						"    AND   gh.status_mov_id       =  2								" +
						"    AND   yl.linha_id            = NVL(:p_linha,yl.linha_id)		" +
						"    AND   yl.fechamento_date BETWEEN :p_dataInicioAp AND :p_dataFinalAp " +
						"    GROUP BY md.grupo_modelo, to_char(yl.fechamento_date,'YYYYMM'),to_char(yl.fechamento_date,'MM-YYYY') " +
						" )																	" +
						" GROUP BY  grupo_modelo,anomes,anomesVL							" +
						" ORDER BY  anomes 													" ;	

		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		if ( new Long(0).equals(orgId) )
			query.setString("p_orgId"	, null);
		else 
			query.setLong("p_orgId"	, orgId.longValue());
		
		if ( linhaId != null )
			if ( linhaId.longValue() == 0 )
				query.setString("p_linha", null);
			else 
				query.setLong("p_linha", linhaId.longValue());
		else 
			query.setString("p_linha", null);
		
		if ( grupoModelo != null && !"".equals(grupoModelo) )
			query.setString("p_grupoModelo", grupoModelo);
		else
			query.setString("p_grupoModelo", null);
		
		query.setDate("p_dataInicioAp" , dataInicioAp);
		query.setDate("p_dataFinalAp" 	, dataFinalAp);
		
		List results = new ArrayList();	
		try {
			Iterator it = query.list().iterator();
			
			while ( it.hasNext() ) {
			
				EntityGraficoGarantiaMensalPercentualVO entityGraficoGarantiaMensalPercentual = new EntityGraficoGarantiaMensalPercentualVO();
				
				Object[] row = (Object[]) it.next();
				entityGraficoGarantiaMensalPercentual.setCategory((String)row[1]);
				entityGraficoGarantiaMensalPercentual.setSerie((String)row[2]);
				entityGraficoGarantiaMensalPercentual.setValue((BigDecimal)row[3]);
				
				results.add(entityGraficoGarantiaMensalPercentual);
			
			}
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		//System.out.println("Resultados:" + results.size());
		
		return results;			
		
	}
	
	/** Lista os dados para o Relatório Gerenciamento Mês. 
	 *  
	 *  
	 *  @param  dataInicioAp - Data de início para apuração
	 *  	<code>Date</code> Data para início da apuração dos dados para o relatório
	 *  
	 *  @param  dataFinalAp - Data final para apuração
	 *  	<code>Date</code> Data para fechamento da apuração dos dados para o relatório
	 *  
	 *  @param linhaId 
	 *  	<code>Long</code> Id da linha do produto
	 *  
	 *  @param concessionariaId 
	 *  	<code>Long</code> Id da concessionária
	 *  
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório Gráfico de PRINCIPAIS PEÇAS.
	 *      
	 * @throws DaoException
	 */
	public List listValuesGerenciamentoMes ( Date dataInicioAp, Date dataFinalAp, Long linhaId, Long concessionariaId ) throws DaoException {
		
		System.out.println("listValuesGerencimanetoMes - Parâmetros - linhaId:" + linhaId + " - dataInicioAp:" + dataInicioAp + " - dataFinalAp:" + dataFinalAp + " - concessionariaId:" + concessionariaId);
		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(ReportQueryHelper.getQueryGerenciamentoMes());
		
		SQLQuery queryOrg = null; 
				
		if ( linhaId != null )
			if ( linhaId.longValue() == 0 )
				query.setString("p_linha", null);
			else 
				query.setLong("p_linha", linhaId.longValue());
		else 
			query.setString("p_linha", null);
		
		if ( concessionariaId != null )
			if ( concessionariaId.longValue() == 0 )
				query.setString("p_concessionaria", null);
			else 
				query.setLong("p_concessionaria", concessionariaId.longValue());
		else 
			query.setString("p_concessionaria", null);
		
		query.setDate("p_dataInicioAp" , dataInicioAp);
		query.setDate("p_dataFimAp" 	, dataFinalAp);
		
		List results = new ArrayList();	
		try {
			Iterator it = query.list().iterator();
			
			Long organizationId = null;
			
			while ( it.hasNext() ) {
			
				RelatorioGerenciamentoMesVO relatorioGerenciamentoMesVO = new RelatorioGerenciamentoMesVO();
				
				Object[] row = (Object[]) it.next();
				
				relatorioGerenciamentoMesVO.setModelo((String)row[0]);
				relatorioGerenciamentoMesVO.setQtModelo(new Long(row[1].toString()));
				
				organizationId = new Long(row[2].toString());
				
				System.out.println("Empresa:" + organizationId);
				
				if ( organizationId != null ) {
					
					queryOrg = session.createSQLQuery("SELECT emp.nome_empresa FROM ym_sg_empresa emp where emp.organization_id = :p_org_id");
					queryOrg.setLong("p_org_id", organizationId.longValue());
					
					Object rowOrg = queryOrg.uniqueResult();
					
					if ( rowOrg != null )
						relatorioGerenciamentoMesVO.setNomeEmpresa(rowOrg.toString());
						
				}
				
				relatorioGerenciamentoMesVO.setQtEstoque(new Long(row[3].toString()));
				relatorioGerenciamentoMesVO.setQtR1(new Long(row[4].toString()));
				relatorioGerenciamentoMesVO.setQtR2(new Long(row[5].toString()));
				relatorioGerenciamentoMesVO.setVlR2(new Double(row[6].toString()));
				relatorioGerenciamentoMesVO.setQtR3(new Long(row[7].toString()));
				relatorioGerenciamentoMesVO.setVlR3(new Double(row[8].toString()));
				relatorioGerenciamentoMesVO.setQtR4(new Long(row[9].toString()));
				relatorioGerenciamentoMesVO.setQtR5(new Long(row[10].toString()));
				relatorioGerenciamentoMesVO.setQtR6(new Long(row[11].toString()));
				relatorioGerenciamentoMesVO.setQtR7(new Long(row[12].toString()));
				relatorioGerenciamentoMesVO.setQtR8(new Long(row[13].toString()));
				relatorioGerenciamentoMesVO.setQtR9(new Long(row[14].toString()));
				relatorioGerenciamentoMesVO.setQtR10(new Long(row[15].toString()));
				relatorioGerenciamentoMesVO.setQtSG(new Long(row[16].toString()));
				relatorioGerenciamentoMesVO.setVlSG(new Double(row[17].toString()));
				relatorioGerenciamentoMesVO.setQtSGRec(new Long(row[18].toString()));
				relatorioGerenciamentoMesVO.setVlSGRec(new Double(row[19].toString()));
				relatorioGerenciamentoMesVO.setQtRevRec(new Long(row[20].toString()));
				relatorioGerenciamentoMesVO.setVlRevRec(new Double(row[21].toString()));				
				
				results.add(relatorioGerenciamentoMesVO);
			
			}
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		System.out.println("Resultados:" + results.size());
		
		return results;			
		
	}
	
	/** Retorna a quantidade de lotes liberados de um determinado período de uma concessionária e de uma determinada linha. 
	 *  
	 *  
	 *  @param  dataInicioAp - Data de início para apuração
	 *  	<code>Date</code> Data para início da apuração dos dados para o relatório
	 *  
	 *  @param  dataFinalAp - Data final para apuração
	 *  	<code>Date</code> Data para fechamento da apuração dos dados para o relatório
	 *  
	 *  @param linhaId 
	 *  	<code>Long</code> Id da linha do produto
	 *  
	 *  @param concessionariaId 
	 *  	<code>Long</code> Id da concessionária
	 *  
	 *  @return
	 *      Um valor <code>int</code>
	 *            
	 * @throws DaoException
	 */
	public int getQtLotesLiberadosGerenciamentoMes ( Date dataInicioAp, Date dataFinalAp, Long linhaId, Long concessionariaId ) throws DaoException {
		
		
		System.out.println("getQtLotesLiberadosGerenciamentoMes - Parâmetros - linhaId:" + linhaId + " - dataInicioAp:" + dataInicioAp + " - dataFinalAp:" + dataFinalAp + " - concessionariaId:" + concessionariaId);
		
		int qtde = 0;		
		
		String sql = " SELECT COUNT(LT.LOTE_ID) 										" +
					 " FROM YM_SG.YM_SG_LOTE LT 										" +
					 " WHERE LT.CONCESSIONARIA_ID = :p_concessionaria 					" +
					 " AND   LT.LINHA_ID          = :p_linha							" +
					 " AND   LT.LIBERACAO_DATE BETWEEN :p_dataInicioAp AND :p_dataFimAp " +
					 " AND   NVL(LT.END_DATE, SYSDATE + 1) >= SYSDATE 					";
		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
				
		if ( linhaId != null )
			if ( linhaId.longValue() == 0 )
				query.setString("p_linha", null);
			else 
				query.setLong("p_linha", linhaId.longValue());
		else 
			query.setString("p_linha", null);
		
		if ( concessionariaId != null )
			if ( concessionariaId.longValue() == 0 )
				query.setString("p_concessionaria", null);
			else 
				query.setLong("p_concessionaria", concessionariaId.longValue());
		else 
			query.setString("p_concessionaria", null);
		
		query.setDate("p_dataInicioAp" , dataInicioAp);
		query.setDate("p_dataFimAp" 	, dataFinalAp);
		try {
			Object row = query.uniqueResult();
			
			if ( row != null )
				qtde =   Integer.parseInt(row.toString());	
						
		} catch ( Exception exp ) {
				
			throw new DaoException(exp);
				
		}  finally {
			session.close();
		}
		
		//System.out.println("qtde:" + results.size());
		
		return qtde;
	}
	
	/** Retorna a quantidade de lotes devolvidos de um determinado período de uma concessionária e de uma determinada linha. 
	 *  
	 *  
	 *  @param  dataInicioAp - Data de início para apuração
	 *  	<code>Date</code> Data para início da apuração dos dados para o relatório
	 *  
	 *  @param  dataFinalAp - Data final para apuração
	 *  	<code>Date</code> Data para fechamento da apuração dos dados para o relatório
	 *  
	 *  @param linhaId 
	 *  	<code>Long</code> Id da linha do produto
	 *  
	 *  @param concessionariaId 
	 *  	<code>Long</code> Id da concessionária
	 *  
	 *  @return
	 *      Um valor <code>int</code>
	 *            
	 * @throws DaoException
	 */
	public int getQtLotesDevolvidosGerenciamentoMes ( Date dataInicioAp, Date dataFinalAp, Long linhaId, Long concessionariaId ) throws DaoException {
		
		
		System.out.println("getQtLotesDevolvidosGerenciamentoMes - Parâmetros - linhaId:" + linhaId + " - dataInicioAp:" + dataInicioAp + " - dataFinalAp:" + dataFinalAp + " - concessionariaId:" + concessionariaId);
		
		int qtde = 0;		
		
		String sql = " SELECT COUNT(LT.LOTE_ID) 										" +
					 " FROM YM_SG.YM_SG_LOTE LT 										" +
					 " WHERE LT.CONCESSIONARIA_ID = :p_concessionaria 					" +
					 " AND   LT.LINHA_ID          = :p_linha							" +
					 " AND   LT.STATUS_LOTE_ID	  = 16									" +
					 " AND   LT.LIBERACAO_DATE BETWEEN :p_dataInicioAp AND :p_dataFimAp " +
					 " AND   NVL(LT.END_DATE, SYSDATE + 1) >= SYSDATE 					";
		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(sql);
				
		if ( linhaId != null )
			if ( linhaId.longValue() == 0 )
				query.setString("p_linha", null);
			else 
				query.setLong("p_linha", linhaId.longValue());
		else 
			query.setString("p_linha", null);
		
		if ( concessionariaId != null )
			if ( concessionariaId.longValue() == 0 )
				query.setString("p_concessionaria", null);
			else 
				query.setLong("p_concessionaria", concessionariaId.longValue());
		else 
			query.setString("p_concessionaria", null);
		
		query.setDate("p_dataInicioAp" , dataInicioAp);
		query.setDate("p_dataFimAp" 	, dataFinalAp);
		
		try {
			Object row = query.uniqueResult();
			
			if ( row != null )
				qtde =   Integer.parseInt(row.toString());	
						
		} catch ( Exception exp ) {
				
			throw new DaoException(exp);
				
		}  finally {
			session.close();
		}
		
		//System.out.println("qtde:" + results.size());
		
		return qtde;
	}
	
	/** Retorna a quantidade média de dias entre a abertura  e o fechamento dos lotes
	 *  de um determinado período de uma concessionária.
	 *  
	 *  @param  dataInicioAp - Data de início para apuração
	 *  	<code>Date</code> Data para início da apuração dos dados para o relatório
	 *  
	 *  @param  dataFinalAp - Data final para apuração
	 *  	<code>Date</code> Data para fechamento da apuração dos dados para o relatório
	 *  
	 *  @param linhaId 
	 *  	<code>Long</code> Id da linha do produto
	 *  
	 *  @param concessionariaId 
	 *  	<code>Long</code> Id da concessionária
	 *  
	 *  @return
	 *      Um valor <code>int</code>
	 *            
	 * @throws DaoException
	 */
	public int getMediaAberturaFechamentoLote ( Date dataInicioAp, Date dataFinalAp, Long linhaId, Long concessionariaId ) throws DaoException {
		
		
		System.out.println("getMediaAberturaFechamentoLote - Parâmetros - linhaId:" + linhaId + " - dataInicioAp:" + dataInicioAp + " - dataFinalAp:" + dataFinalAp + " - concessionariaId:" + concessionariaId);
		
		int qtde = 0;
		
		Session session = super.getSession();
		SQLQuery query = session.createSQLQuery(ReportQueryHelper.getQueryMediaAberturaFechamentoLote());
				
		if ( linhaId != null )
			if ( linhaId.longValue() == 0 )
				query.setString("p_linha", null);
			else 
				query.setLong("p_linha", linhaId.longValue());
		else 
			query.setString("p_linha", null);
		
		if ( concessionariaId != null )
			if ( concessionariaId.longValue() == 0 )
				query.setString("p_concessionaria", null);
			else 
				query.setLong("p_concessionaria", concessionariaId.longValue());
		else 
			query.setString("p_concessionaria", null);
		
		query.setDate("p_dataInicioAp" , dataInicioAp);
		query.setDate("p_dataFimAp" 	, dataFinalAp);
		
		try {
			
			Object row = query.uniqueResult();
			
			if ( row != null )
				qtde =   Integer.parseInt(row.toString());			
						
		} catch ( Exception exp ) {
				
			throw new DaoException(exp);
				
		}  finally {
			session.close();
		}
		
		//System.out.println("qtde:" + results.size());
		
		return qtde;
	}
	
	
	/** Método temporário. 
	 *  
	 *  
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório Gráfico Service Report.
	 *      
	 * @throws DaoException
	 */
	public String processa ( String texto ) throws DaoException {
		
		String resp = new String();
		/*
		Session session = super.getSession();
		Query query = session.createQuery(texto);
		
		
		try {
			
			int res = query.executeUpdate();
			
			resp = String.valueOf(res);
			
		} catch ( Exception exp ) {
			
			throw new DaoException(exp);
			
		}  finally {
			session.close();
		}
		*/
		return resp;			
		
	}
	
}

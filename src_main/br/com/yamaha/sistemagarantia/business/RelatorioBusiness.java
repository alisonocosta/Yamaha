/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioBusiness.java
 *   .: Criação.....12 de julho, 12:16
 *   .: Autor.......Gisele Panosso
 *   .: Descrição...Entidade "RelatorioBusiness".
 */
package br.com.yamaha.sistemagarantia.business;

import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import br.com.resource.infra.business.BusinessObject;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.DaoException;
import br.com.resource.infra.exception.ServiceException;
import br.com.resource.infra.utils.DateUtils;
import br.com.resource.infra.utils.NumberUtils;
import br.com.yamaha.sistemagarantia.business.exception.CotacaoException;
import br.com.yamaha.sistemagarantia.controller.helper.GenerateChartReport;
import br.com.yamaha.sistemagarantia.dao.RelatorioDao;
import br.com.yamaha.sistemagarantia.view.EntityGraficoGarantiaPagaVO;
import br.com.yamaha.sistemagarantia.view.EntityImgReportGarantiaPagaVO;
import br.com.yamaha.sistemagarantia.view.EntityImgReportVO;
import br.com.yamaha.sistemagarantia.view.RelatorioConsultaLoteConcVO;
import br.com.yamaha.sistemagarantia.view.RelatorioConsultaLoteStatusVO;
import br.com.yamaha.sistemagarantia.view.RelatorioConsultaLoteVO;
import br.com.yamaha.sistemagarantia.view.RelatorioGarPecasSubReportVO;
import br.com.yamaha.sistemagarantia.view.RelatorioGarantiaPecasModSubRepVO;
import br.com.yamaha.sistemagarantia.view.RelatorioGarantiaPecasModVO;
import br.com.yamaha.sistemagarantia.view.RelatorioGarantiaPecasVO;
import br.com.yamaha.sistemagarantia.view.RelatorioGraficosIndividuaisTableVO;
import br.com.yamaha.sistemagarantia.view.RelatorioHistChassiGarantiaVO;
import br.com.yamaha.sistemagarantia.view.RelatorioHistChassiParecerVO;
import br.com.yamaha.sistemagarantia.view.RelatorioHistChassiPecaVO;
import br.com.yamaha.sistemagarantia.view.RelatorioImportedPartsInvoiceVO;
import br.com.yamaha.sistemagarantia.view.RelatorioImportedPartsVO;
import br.com.yamaha.sistemagarantia.view.RelatorioImportedPartsWarrantyVO;
import br.com.yamaha.sistemagarantia.view.RelatorioMensalModeloVO;
import br.com.yamaha.sistemagarantia.view.RelatorioProcRevisoesFase2VO;

/**
 * @author Gisele Panosso
 *
 */
public class RelatorioBusiness extends BusinessObject {

	/** Objeto relatorioDao para utilizado neste objeto de negócios. */
	private RelatorioDao relatorioDao;

	/** Lista todos os dados para o Relatório Zero Km existentes no banco de dados.
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório Zero Km.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      será lançada. Caso hajam problemas de regras de negócio, uma
	 *      BusinessRuleException (subclasse de BusinessException) será 
	 *      lançada.
	 */
	public List listZeroKm(Date dataIni, Date dataFim, Long km, Long linha) throws BusinessException {

		try {

			return relatorioDao.listRelatorioZeroKm(dataIni, dataFim, km, linha);

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);
		}		
	}  

	/** Lista todos os dados para o Relatório Análise de Peças existentes no banco de dados.
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório de Análise de Peças.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      será lançada. Caso hajam problemas de regras de negócio, uma
	 *      BusinessRuleException (subclasse de BusinessException) será 
	 *      lançada.
	 */
	public List listAnalisePecas( Date   dataIni
					            , Date   dataFim
					            , Long   tipoProduto
					            , Long   peca
					            , String modelo) throws BusinessException {

		try {

			return relatorioDao.listRelatorioAnalisePecas(dataIni, dataFim, tipoProduto, peca, modelo);

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);
		}		
	}	

	/** Lista todos os dados para o Relatório Pagamentos existentes no banco de dados.
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório de Pagamentos.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      será lançada. Caso hajam problemas de regras de negócio, uma
	 *      BusinessRuleException (subclasse de BusinessException) será 
	 *      lançada.
	 */
	public List listPagamentos(Date dataIni, Date dataFim, Long concessionaria, String tipoServico) throws BusinessException {

		try {

			return relatorioDao.listRelatorioPagamentos(dataIni, dataFim, concessionaria, tipoServico);

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);
		}
	}   

	/** Lista todos os dados para o Relatório de Recusa existentes no banco de dados.
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório de Recusa.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      será lançada. Caso hajam problemas de regras de negócio, uma
	 *      BusinessRuleException (subclasse de BusinessException) será 
	 *      lançada.
	 */
	public List listRecusa( Long   empresa
						  , Long   concessionaria
						  , Long   linha
						  , String chassi
						  , Date   dataIni
						  , Date   dataFim ) throws BusinessException {

		try {

			return relatorioDao.listRelatorioRecusa(empresa, concessionaria, linha, chassi, dataIni, dataFim);

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);
		}
	}

	/** Lista todos os dados para o Relatório de Verificação existentes no banco de dados.
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório de Verificação.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      será lançada. Caso hajam problemas de regras de negócio, uma
	 *      BusinessRuleException (subclasse de BusinessException) será 
	 *      lançada.
	 */
	public List listVerificacao( Date   dataIni
					           , Date   dataFim
					           , Long   linha
					           , Long   concessionaria					           
					           , Long   valorMinimo) throws BusinessException {

		try {
			
			return relatorioDao.listRelatorioVerificacao(dataIni, dataFim, linha, concessionaria, valorMinimo);
			
		} catch (DaoException dExp) {
			throw new BusinessException(dExp);
		}

	}

	/** Lista todos os dados para o Relatório Mensal por Modelo existentes no banco de dados.
     * 
     *  @param mesReferencia
     *  @param empresaId
     *  
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório Mensal por Modelo.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      será lançada. Caso hajam problemas de regras de negócio, uma
	 *      BusinessRuleException (subclasse de BusinessException) será 
	 *      lançada.
	 */
	public List listMensalModelo(String mesReferencia, Long empresaId) throws BusinessException {

		try {

			List results      = new ArrayList();
			List mensalModelo = relatorioDao.listRelatorioMensalModelo(mesReferencia, empresaId);
			
			RelatorioMensalModeloVO currentMensalMod = null;
			
			Iterator mensalModIt = mensalModelo.iterator();
			while ( mensalModIt.hasNext() ){
				
				currentMensalMod = (RelatorioMensalModeloVO)mensalModIt.next();
				
				double qtRevisao1 = currentMensalMod.getQtRevisao1().doubleValue();
				double qtFaturada = currentMensalMod.getTotalMotoFaturada().doubleValue();
				
				if (qtFaturada == 0 ){
					
					currentMensalMod.setPercCadatrado(new Double(0));
					
				}else{
					double percCadastrado = (qtRevisao1/qtFaturada)* 100;
					currentMensalMod.setPercCadatrado(Double.valueOf(percCadastrado));
				}
				results.add(currentMensalMod);
			}
			
			return results;
			
		} catch (DaoException dExp) {
			throw new BusinessException(dExp);
		}

	} 
	
	/** Lista todos os dados para o Relatório Mala Direta existentes no banco de dados.
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório Mala Direta.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      será lançada. Caso hajam problemas de regras de negócio, uma
	 *      BusinessRuleException (subclasse de BusinessException) será 
	 *      lançada.
	 */
	public List listMalaDireta ( Date   dataIni
					           , Date   dataFim
					           , Long   empresa
					           , Long   concessionaria
					           , Long   linha
					           , String estado
					           , String modelo
					           , String chassiIni
					           , String chassiFim) throws BusinessException {

		try {
			
			List malaDireta = relatorioDao.listRelatorioMalaDireta( dataIni
												                    , dataFim
												                    , empresa
												                    , concessionaria
												                    , linha
												                    , estado
												                    , modelo
												                    , chassiIni
												                    , chassiFim);
			
			return  malaDireta;
			
		} catch (DaoException dExp) {
			throw new BusinessException(dExp);
		}

	}	
	
	/** Lista os dados das notas para o Relatório de Solicitação Garantia existentes no banco de dados.
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório de Solicitação Garantia.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      será lançada. Caso hajam problemas de regras de negócio, uma
	 *      BusinessRuleException (subclasse de BusinessException) será 
	 *      lançada.
	 */
	public List listSolicitacaoGarantiaNotas( Integer lote, Long concessionaria) throws BusinessException {

		try {
			
			return relatorioDao.listRelatorioSolicitacaoGarantiaNota(lote, concessionaria);
			
			/*
			List garantias = relatorioDao.listRelatorioSolicitacaoGarantia(lote);                  

			List pecas     = relatorioDao.listRelatorioSolicitacaoGarantiaPeca(lote, concessionaria);
			
			RelatorioSolicitacaoGarantiaNotaVO  resultNota      = new RelatorioSolicitacaoGarantiaNotaVO();
			RelatorioSolicitacaoGarantiaVO      currentGarantia = null;
			RelatorioSolicitacaoGarantiaPecaVO  currentPeca     = null;
			
			if ( notas != null && garantias != null ) {
				// Adiciona as notas no relatorio
				Iterator notasIt     = notas.iterator();
				while (notasIt.hasNext()){
					
					resultNota = (RelatorioSolicitacaoGarantiaNotaVO) notasIt.next();
					results.add(resultNota);
				}
				
				// Adiciona as garantias no relatorio
				Iterator garantiasIt = garantias.iterator();
				while (garantiasIt.hasNext()){
					
					currentGarantia = (RelatorioSolicitacaoGarantiaVO) garantiasIt.next();
					resultNota.addListGarantias(currentGarantia);
				}
				results.add(resultNota);
				
				// Adiciona as peças no relatorio
				Iterator pecasIt     = pecas.iterator();
				while (pecasIt.hasNext()){
					
					currentPeca = (RelatorioSolicitacaoGarantiaPecaVO) pecasIt.next();
					resultNota.addListPecas(currentPeca);
				}
				results.add(resultNota);
			}
			
			return results;
			*/
		} catch (DaoException dExp) {
			throw new BusinessException(dExp);
		}
	}	
	
	/** Lista os dados das garantias para o Relatório de Solicitação Garantia existentes no banco de dados.
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório de Solicitação Garantia.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      será lançada. Caso hajam problemas de regras de negócio, uma
	 *      BusinessRuleException (subclasse de BusinessException) será 
	 *      lançada.
	 */
	public List listSolicitacaoGarantias( Integer lote, Long concessionaria) throws BusinessException {
		try {
			
			return relatorioDao.listRelatorioSolicitacaoGarantia(lote);   
		
		} catch (DaoException dExp) {
			throw new BusinessException(dExp);
		}
		
	}
	
	/** Lista os dados das peças para o Relatório de Solicitação Garantia existentes no banco de dados.
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório de Solicitação Garantia.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      será lançada. Caso hajam problemas de regras de negócio, uma
	 *      BusinessRuleException (subclasse de BusinessException) será 
	 *      lançada.
	 */
	public List listSolicitacaoGarantiasPecas( Integer lote, Long concessionaria) throws BusinessException {
		try {
			
			return relatorioDao.listRelatorioSolicitacaoGarantiaPeca(lote, concessionaria);
		
		} catch (DaoException dExp) {
			throw new BusinessException(dExp);
		}
		
	}
	
	/** Lista todos os dados para o Relatório de Processamento de Revisoes - Fase1 existentes no banco de dados.
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório de Processamento de Revisoes - Fase1.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      será lançada. Caso hajam problemas de regras de negócio, uma
	 *      BusinessRuleException (subclasse de BusinessException) será 
	 *      lançada.
	 */
	public List listProcRevisoesFase1 (Long empresa, Integer lote) throws BusinessException {

		try {
			
			return relatorioDao.listRelatorioProcRevisoesFase1(empresa, lote);
			
		} catch (DaoException dExp) {
			throw new BusinessException(dExp);
		}
	}	
	
	/** Lista todos os dados para o Relatório de Processamento de Revisoes - Fase2 existentes no banco de dados.
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório de Processamento de Revisoes - Fase2.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      será lançada. Caso hajam problemas de regras de negócio, uma
	 *      BusinessRuleException (subclasse de BusinessException) será 
	 *      lançada.
	 */
	public List listProcRevisoesFase2 (Long empresa, Integer lote) throws BusinessException {

		try {
			
			return relatorioDao.listRelatorioProcRevisoesFase2(empresa, lote);
			
		} catch (DaoException dExp) {
			throw new BusinessException(dExp);
		}
	}
	
	/** Método que calcula o valor total de uma Revisão do Relatório de Processamento de Revisoes - Fase2.
	 *
	 *  @return o valor da total de uma Revisão.
	 */
	public Double totalRev(List revisoes, int nrRevisao) {
		
		double acum  = 0;
		double value = 0;
		RelatorioProcRevisoesFase2VO currentRevisao = null;
		
		Iterator revIt = revisoes.iterator();
		
		while (revIt.hasNext()){
			
			currentRevisao = (RelatorioProcRevisoesFase2VO)revIt.next();
			
			if ( ((BigDecimal)currentRevisao.getNrRevisao()).intValue() == nrRevisao){
				
				value = ((Double)currentRevisao.getVlRevisao()).doubleValue();				
				
				acum += value;
			}
		}
		
		//System.out.println("Total:" +  acum);
		
		return new Double(acum);
	}
	
	/** Lista todos os dados para o Relatório Consulta de Notas Fiscais existentes no banco de dados.
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório Consulta de Notas Fiscais.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      será lançada. Caso hajam problemas de regras de negócio, uma
	 *      BusinessRuleException (subclasse de BusinessException) será 
	 *      lançada.
	 */	
	public List listConsultaNF( Date    dataIni
							  , Date    dataFim
							  , Long    linha
							  , Integer lote
							  , Long    tipoLote
							  , Long    concessionaria
							  , Long    empresa) throws BusinessException {
		
		try {
			List results  = relatorioDao.listRelatorioConsultaNfNota( dataIni
												                    , dataFim
												                    , linha
												                    , lote
												                    , tipoLote
												                    , concessionaria
												                    , empresa);
			/*
			List empresas = relatorioDao.listRelatorioConsultaNfEmp( dataIni
					                                               , dataFim
					                                               , linha
					                                               , lote
					                                               , tipoLote
					                                               , concessionaria
					                                               , empresa);
			
			System.out.println( "Empresas :" + empresas.size() );
			
			RelatorioConsultaNfEmpVO  currentEmp  = null;
			RelatorioConsultaNfConcVO currentConc = null;
			RelatorioConsultaNfLoteVO currentLote = null;
			RelatorioConsultaNfNotaVO currentNota = null;
			
			Iterator empIt = empresas.iterator();
			while ( empIt.hasNext() ){
				
				currentEmp = (RelatorioConsultaNfEmpVO)empIt.next();
				
				List concessionarias = relatorioDao.listRelatorioConsultaNfConc( dataIni
						                                                       , dataFim
						                                                       , linha
						                                                       , lote
						                                                       , tipoLote
						                                                       , concessionaria
						                                                       , currentEmp.getEmpresaId());
				
				System.out.println("Empresa:" + currentEmp.getEmpresa() + " - concessionarias:" + concessionarias.size());
				
				Iterator concIt = concessionarias.iterator();
				while ( concIt.hasNext()){
					
					currentConc = (RelatorioConsultaNfConcVO)concIt.next();
					
					List lotes = relatorioDao.listRelatorioConsultaNfLote( dataIni
							                                             , dataFim
							                                             , linha
							                                             , lote
							                                             , tipoLote
							                                             , currentConc.getConcessionariaId()
							                                             , currentEmp.getEmpresaId());
					
					System.out.println("Concessionaria:" + currentConc.getConcessionaria() + " - lotes:" + lotes.size());
					
					Iterator loteIt = lotes.iterator();
					while ( loteIt.hasNext() ){
						
						currentLote = (RelatorioConsultaNfLoteVO)loteIt.next();
						
						List notas = relatorioDao.listRelatorioConsultaNfNota( dataIni
								                                             , dataFim
								                                             , linha
								                                             , (Integer)currentLote.getLote()
								                                             , tipoLote
								                                             , currentConc.getConcessionariaId()
								                                             , currentEmp.getEmpresaId());
						
						System.out.println("Lote:" + currentLote.getLote() + " - notas:" + notas.size() );
						
						Iterator notaIt = notas.iterator();
						while ( notaIt.hasNext() ){
							
							currentNota = (RelatorioConsultaNfNotaVO)notaIt.next();
							
							System.out.println("Nota:" + currentNota.getNotaFiscal());

							// Adiciona a nota no lote
							currentLote.addListNotas(currentNota);
						}
						//Adiciona o lote na concessionaria
						currentConc.addListLotes(currentLote);
					}
					//Adiciona a concessionaria na empresa
					currentEmp.addListConc(currentConc);
				}
				//Adiciona a empresa na lista de resultados
				results.add(currentEmp);
			}
			*/
			return results;
			
		} catch (DaoException dExp) {
			throw new BusinessException(dExp);
		}
	}
	
	/** Lista todos os dados para o Relatório Consulta de Lotes existentes no banco de dados.
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório Consulta de Lotes. 
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      será lançada. Caso hajam problemas de regras de negócio, uma
	 *      BusinessRuleException (subclasse de BusinessException) será 
	 *      lançada.
	 */	
	public List listConsultaLote( Date    dataIni
							    , Date    dataFim
							    , Long    linha
							    , Integer lote
							    , Long    tipoLote
							    , Long    concessionaria) throws BusinessException {
		
		try {
			List results = new ArrayList();

			List concessionarias = relatorioDao.listRelatorioConsultaConc( dataIni
					                                                     , dataFim
					                                                     , linha
					                                                     , lote
					                                                     , tipoLote
					                                                     , concessionaria);
			
			RelatorioConsultaLoteConcVO   currentConc   = new RelatorioConsultaLoteConcVO();
			RelatorioConsultaLoteVO       currentLote   = new RelatorioConsultaLoteVO();
			RelatorioConsultaLoteStatusVO currentStatus = new RelatorioConsultaLoteStatusVO();

			Iterator concessionariaIt = concessionarias.iterator();
			
			while ( concessionariaIt.hasNext() ){
				
				currentConc = (RelatorioConsultaLoteConcVO)concessionariaIt.next();
				
				List lotes = relatorioDao.listRelatorioConsultaLote( dataIni
						                                           , dataFim
						                                           , linha
						                                           , lote
						                                           , tipoLote
						                                           , currentConc.getConcessionariaId());
				
				Iterator loteIt = lotes.iterator();
				
				while ( loteIt.hasNext() ){
					
					currentLote = (RelatorioConsultaLoteVO) loteIt.next();

					RelatorioConsultaLoteVO resultLote  = new RelatorioConsultaLoteVO();
					
					resultLote.setLote(currentLote.getLote());
					resultLote.setTipoLote(currentLote.getTipoLote());
					resultLote.setDescStatus(currentLote.getDescStatus());
					resultLote.setDtAbertura(currentLote.getDtAbertura());
					resultLote.setDtLiberacao(currentLote.getDtLiberacao());
					resultLote.setDtFechamento(currentLote.getDtFechamento());
					
					List status = relatorioDao.listRelatorioConsultaLoteStatus( dataIni
													                          , dataFim
													                          , linha
													                          , (Integer)currentLote.getLote()
													                          , tipoLote
													                          , currentConc.getConcessionariaId());
					
					Iterator statusIt = status.iterator();
					
					while ( statusIt.hasNext() ){
						
						currentStatus = (RelatorioConsultaLoteStatusVO)statusIt.next();
						
						RelatorioConsultaLoteStatusVO resultStatus = new RelatorioConsultaLoteStatusVO ();
						
						resultStatus.setDtStatus(currentStatus.getDtStatus());
						resultStatus.setDescStatusAnterior(currentStatus.getDescStatusAnterior());
						
						resultLote.addListStatus(resultStatus);
					}
					
					//Adiciona o Lote na Concessionaria
					currentConc.addListLotes(resultLote);
					
				}
				
				//Adiciona a concessionaria no result
				results.add(currentConc);
			}
			
			//System.out.println("Lista relatório:" + results.size());
			
			return results;
			
		} catch (DaoException dExp) {
			throw new BusinessException(dExp);
		}
	}	
	
	/** Lista todos os dados para o Relatório de Protocolo de Documentos de Revisão existentes no banco de dados.
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório de Protocolo de Documentos de Revisão.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      será lançada. Caso hajam problemas de regras de negócio, uma
	 *      BusinessRuleException (subclasse de BusinessException) será 
	 *      lançada.
	 */	
	public List listDocRevisao( Integer lote) throws BusinessException {
		
		try {
			/*
			List result = new ArrayList();
			
			List revisoes = relatorioDao.listRelatorioDocRevisao(lote, concessionaria);
			
			List revisoesDetalhe = relatorioDao.listRelatorioDocRevisaoDetalhe(lote);		
			
			RelatorioDocRevisaoDetalheVO currentDetalhe = null;
			RelatorioDocRevisaoVO        resultRevisao  = new RelatorioDocRevisaoVO();
			
			Iterator revisaoIt = revisoes.iterator();
			while(revisaoIt.hasNext()){
				
				resultRevisao = (RelatorioDocRevisaoVO)revisaoIt.next();
				
			}
			
			Iterator detalheIt = revisoesDetalhe.iterator();
			
			while (detalheIt.hasNext()){
				
				currentDetalhe = (RelatorioDocRevisaoDetalheVO)detalheIt.next();
				resultRevisao.addListRevisoes(currentDetalhe);
			}
			result.add(resultRevisao);
			
			return result;
			*/		
			
			return relatorioDao.listRelatorioDocRevisao(lote);
			
		} catch (DaoException dExp) {
			throw new BusinessException(dExp);
		}
	}	
	
	/** Lista os detalhes para o Relatório de Protocolo de Documentos de Revisão existentes no banco de dados.
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório de Protocolo de Documentos de Revisão.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      será lançada. Caso hajam problemas de regras de negócio, uma
	 *      BusinessRuleException (subclasse de BusinessException) será 
	 *      lançada.
	 */	
	public List listDocRevisaoDetalhes( Integer lote ) throws BusinessException {
		
		try {
	
				return relatorioDao.listRelatorioDocRevisaoDetalhe(lote);
			
		} catch (DaoException dExp) {
			throw new BusinessException(dExp);
		}
	}	
	
	/** Lista todos os dados para o Relatório de Processamento de Peças - Fase1 existentes no banco de dados.
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório de Processamento de Peças - Fase1.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      será lançada. Caso hajam problemas de regras de negócio, uma
	 *      BusinessRuleException (subclasse de BusinessException) será 
	 *      lançada.
	 */	
	public List listProcPecasFase1(Long empresa, Integer lote) throws BusinessException {
		
		try {
			
			return relatorioDao.listRelatorioProcPecasFase1(empresa, lote);
		} catch (DaoException dExp) {
			throw new BusinessException(dExp);
		}
	}
	
	/** Lista todos os dados para o Relatório de Processamento de Peças - Fase2 existentes no banco de dados.
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório de Processamento de Peças - Fase2.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      será lançada. Caso hajam problemas de regras de negócio, uma
	 *      BusinessRuleException (subclasse de BusinessException) será 
	 *      lançada.
	 */	
	public List listProcPecasFase2( Long empresa, Integer lote) throws BusinessException {
		
		try {
			
			return relatorioDao.listRelatorioProcPecasFase2(empresa, lote);			
		} catch (DaoException dExp) {
			throw new BusinessException(dExp);
		}
	}
	
	/** Lista os dados das notas fiscais de um Lote.
	 * 
	 * @param loteId
	 * 	<code>Integer</code> Id do lote
	 * 
	 * @return list
	 * 	<code>list</code> lista de notas do Relatório de Processamento de Peças - Fase2.
	 * 
	 * @throws BusinessException
	 */
	public List listNotasByLote( Integer loteId) throws BusinessException {
		
		try {
			
			return relatorioDao.listRelatorioNotasToProcPecasFase2(loteId);
			
		} catch (DaoException dExp) {
			throw new BusinessException(dExp);
		}
	}
	
	/** Lista todos os dados para o Relatório Consulta Histórico Chassi, dados do Cabeçalho existentes no banco de dados.
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório Consulta Histórico Chassi, dados do Cabeçalho.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      será lançada. Caso hajam problemas de regras de negócio, uma
	 *      BusinessRuleException (subclasse de BusinessException) será 
	 *      lançada.
	 */	
	public List listHistChassiCabec(String chassi) throws BusinessException {
		
		try {
			
			return relatorioDao.listRelatorioHistChassiCabec(chassi);
		} catch (DaoException dExp) {
			throw new BusinessException(dExp);
		}
	}	

	/** Lista todos os dados para o Relatório Consulta Histórico Chassi, dados das Revisões existentes no banco de dados.
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório Consulta Histórico Chassi, dados das Revisões.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      será lançada. Caso hajam problemas de regras de negócio, uma
	 *      BusinessRuleException (subclasse de BusinessException) será 
	 *      lançada.
	 */	
	public List listHistChassiRevisao(String chassi) throws BusinessException {
		
		try {
			
			return relatorioDao.listRelatorioHistChassiRevisao(chassi);			
		} catch (DaoException dExp) {
			throw new BusinessException(dExp);
		}
	}
	
	
	/** Lista todos os dados para o Relatório Consulta Histórico Chassi, dados das Garantias existentes no banco de dados.
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório Consulta Histórico Chassi, dados das Garantias.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      será lançada. Caso hajam problemas de regras de negócio, uma
	 *      BusinessRuleException (subclasse de BusinessException) será 
	 *      lançada.
	 */	
	public List listHistChassiGarantia(String chassi) throws BusinessException {
		
		try {
			
			List results = new ArrayList();
			
			List garantias = relatorioDao.listRelatorioHistChassiGarantia(chassi);	
			
			RelatorioHistChassiGarantiaVO currentGarantia = null;
			RelatorioHistChassiPecaVO     currentPeca     = null;
			RelatorioHistChassiParecerVO  currentParecer  = null;
			
			Iterator garantiaIt = garantias.iterator();
			while ( garantiaIt.hasNext() ){
				
				currentGarantia = (RelatorioHistChassiGarantiaVO) garantiaIt.next();
				
				// Adiciona a lista de peças na garantia corrente
				List     pecas   = relatorioDao.listRelatorioHistChassiPeca(currentGarantia.getGarantiaId(), chassi);
				Iterator pecasIt = pecas.iterator();
				while ( pecasIt.hasNext() ){
					
					currentPeca = (RelatorioHistChassiPecaVO) pecasIt.next();
					currentGarantia.addListPecas(currentPeca);
				}
			
				// Adiciona a lista de pareceres na garantia corrente
				List     parecer   = relatorioDao.listRelatorioHistChassiParecer(currentGarantia.getGarantiaId(), chassi);
				Iterator parecerIt = parecer.iterator();
				while ( parecerIt.hasNext() ){
					
					currentParecer = (RelatorioHistChassiParecerVO) parecerIt.next();
					currentGarantia.addListParecer(currentParecer);
				}

				// Adiciona a garantia corrente no result
				results.add(currentGarantia);
			}
	
			return results;		
		} catch (DaoException dExp) {
			throw new BusinessException(dExp);
		}
	}	
	
	/** Lista todos os dados para o Relatório Consulta Histórico Chassi, totais.
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório Consulta Histórico Chassi, totais da garantia, peças e terceiros.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      será lançada. Caso hajam problemas de regras de negócio, uma
	 *      BusinessRuleException (subclasse de BusinessException) será 
	 *      lançada.
	 */	
	public List listHistChassiTotais(String chassi) throws BusinessException {
		
		try {
			
			return relatorioDao.listRelatorioHistChassiTotais(chassi);			
		} catch (DaoException dExp) {
			throw new BusinessException(dExp);
		}
	}	

	
	/** Lista todos os dados para o Relatório de Garantia e Peças.
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório de Garantia e Peças.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      será lançada. Caso hajam problemas de regras de negócio, uma
	 *      BusinessRuleException (subclasse de BusinessException) será 
	 *      lançada.
	 */	
	public List listGarantiaPecas(Long empresa, Long linha, String ano, String moeda) throws BusinessException {
		
		try {
			List results       = new ArrayList();
			List garantiaPecas = relatorioDao.listRelatorioGarantiaPecas(empresa, linha, ano);
			List orcPcsMobra   = relatorioDao.listRelatorioGPOrcPcsMobra(empresa, linha, ano);
			List revisoes      = relatorioDao.listRelatorioGPRevisao(empresa, linha, ano);
			List orcRevisao    = relatorioDao.listRelatorioGPOrcRev(empresa, linha, ano);
			List orcTotal      = relatorioDao.listRelatorioGPOrcTotal(empresa, linha, ano);
			List vlFatur       = relatorioDao.listRelatorioGPVlFatur(empresa, linha, ano);
			List qtdes         = relatorioDao.listRelatorioGPQtdes(empresa, linha, ano);

		    RelatorioGarantiaPecasVO     resultGarPecas    = new RelatorioGarantiaPecasVO();
		    RelatorioGarPecasSubReportVO resultVlAcmMobra  = new RelatorioGarPecasSubReportVO();
		    RelatorioGarPecasSubReportVO resultOrcPcsMobra = new RelatorioGarPecasSubReportVO();
		    RelatorioGarPecasSubReportVO resultRevisoes    = new RelatorioGarPecasSubReportVO();
		    RelatorioGarPecasSubReportVO resultOrcRevisao  = new RelatorioGarPecasSubReportVO();
		    RelatorioGarPecasSubReportVO resultOrcTotal    = new RelatorioGarPecasSubReportVO();
		    RelatorioGarPecasSubReportVO resultSubtotal    = new RelatorioGarPecasSubReportVO();
		    RelatorioGarPecasSubReportVO resultTotal       = new RelatorioGarPecasSubReportVO();
		    RelatorioGarPecasSubReportVO resultVlFatur     = new RelatorioGarPecasSubReportVO();
		    RelatorioGarPecasSubReportVO resultQtdes       = new RelatorioGarPecasSubReportVO();
		    RelatorioGarPecasSubReportVO resultGarVlFatur  = new RelatorioGarPecasSubReportVO();
		    RelatorioGarPecasSubReportVO resultRevVlFatur  = new RelatorioGarPecasSubReportVO();
		    RelatorioGarPecasSubReportVO resultTotVlFatur  = new RelatorioGarPecasSubReportVO();
	    	
	    	// Variáveis para armazenar a cotação do dólar
	    	double dolarJan = 1;            double dolarFev = 1;            double dolarMar = 1;
    		double dolarAbr = 1;            double dolarMai = 1;            double dolarJun = 1;
    		double dolarJul = 1;            double dolarAgo = 1;            double dolarSet = 1;
    		double dolarOut = 1;            double dolarNov = 1;            double dolarDez = 1;
    		
    		//Variáveis do Valor Acumulado de Mão de Obra Peça
    		double vlMobraPcM01 = 0;     double vlMobraPcM02 = 0;     double vlMobraPcM03 = 0;
    		double vlMobraPcM04 = 0;     double vlMobraPcM05 = 0;     double vlMobraPcM06 = 0;
    		double vlMobraPcM07 = 0;     double vlMobraPcM08 = 0;     double vlMobraPcM09 = 0;
    		double vlMobraPcM10 = 0;     double vlMobraPcM11 = 0;     double vlMobraPcM12 = 0;

    		//Variáveis do Valor Mão de Obra de Terceiros
    		double vlMobraTerM01 = 0;    double vlMobraTerM02 = 0;    double vlMobraTerM03 = 0;
    		double vlMobraTerM04 = 0;    double vlMobraTerM05 = 0;    double vlMobraTerM06 = 0;
    		double vlMobraTerM07 = 0;    double vlMobraTerM08 = 0;    double vlMobraTerM09 = 0;
    		double vlMobraTerM10 = 0;    double vlMobraTerM11 = 0;    double vlMobraTerM12 = 0;
            
            //Variáveis do Valor de Garantia
            double vlGarM01 = 0;            double vlGarM02 = 0;            double vlGarM03 = 0;
            double vlGarM04 = 0;            double vlGarM05 = 0;            double vlGarM06 = 0;
            double vlGarM07 = 0;            double vlGarM08 = 0;            double vlGarM09 = 0;
            double vlGarM10 = 0;            double vlGarM11 = 0;            double vlGarM12 = 0;
            
            //Variáveis do Total de Revisões
            double totRevM01 = 0;           double totRevM02 = 0;           double totRevM03 = 0;
            double totRevM04 = 0;           double totRevM05 = 0;           double totRevM06 = 0;
            double totRevM07 = 0;           double totRevM08 = 0;           double totRevM09 = 0;
            double totRevM10 = 0;           double totRevM11 = 0;           double totRevM12 = 0;
            
            //Variáveis do Valor Faturado
            double vlFaturM01 = 0;          double vlFaturM02 = 0;          double vlFaturM03 = 0;
            double vlFaturM04 = 0;          double vlFaturM05 = 0;          double vlFaturM06 = 0;
            double vlFaturM07 = 0;          double vlFaturM08 = 0;          double vlFaturM09 = 0;
            double vlFaturM10 = 0;          double vlFaturM11 = 0;          double vlFaturM12 = 0;
            
    		
    		//Adiciona os dados de Garantia e Peças no relatório
		    Iterator garPecasIt = garantiaPecas.iterator();
		    while (garPecasIt.hasNext()){
		    	resultGarPecas = (RelatorioGarantiaPecasVO)garPecasIt.next();
		    	
		    	if (moeda.equalsIgnoreCase("dolar")){
		    		if (resultGarPecas.getTopico().equals("VL DOLAR")){
			    		dolarJan = resultGarPecas.getM01().doubleValue();
			    		dolarFev = resultGarPecas.getM02().doubleValue();
			    		dolarMar = resultGarPecas.getM03().doubleValue();
			    		dolarAbr = resultGarPecas.getM04().doubleValue();
			    		dolarMai = resultGarPecas.getM05().doubleValue();
			    		dolarJun = resultGarPecas.getM06().doubleValue();
			    		dolarJul = resultGarPecas.getM07().doubleValue();
			    		dolarAgo = resultGarPecas.getM08().doubleValue();
			    		dolarSet = resultGarPecas.getM09().doubleValue();
			    		dolarOut = resultGarPecas.getM10().doubleValue();
			    		dolarNov = resultGarPecas.getM11().doubleValue();
			    		dolarDez = resultGarPecas.getM12().doubleValue();
		    		}
		    	
			    	if (resultGarPecas.getTopico().equals("VL DOLAR")== false){
			    		resultGarPecas.setM01(Double.valueOf(resultGarPecas.getM01().doubleValue()/dolarJan));
			    		resultGarPecas.setM02(Double.valueOf(resultGarPecas.getM02().doubleValue()/dolarFev));
			    		resultGarPecas.setM03(Double.valueOf(resultGarPecas.getM03().doubleValue()/dolarMar));
			    		resultGarPecas.setM04(Double.valueOf(resultGarPecas.getM04().doubleValue()/dolarAbr));
			    		resultGarPecas.setM05(Double.valueOf(resultGarPecas.getM05().doubleValue()/dolarMai));
			    		resultGarPecas.setM06(Double.valueOf(resultGarPecas.getM06().doubleValue()/dolarJun));
			    		resultGarPecas.setM07(Double.valueOf(resultGarPecas.getM07().doubleValue()/dolarJul));
			    		resultGarPecas.setM08(Double.valueOf(resultGarPecas.getM08().doubleValue()/dolarAgo));
			    		resultGarPecas.setM09(Double.valueOf(resultGarPecas.getM09().doubleValue()/dolarSet));
			    		resultGarPecas.setM10(Double.valueOf(resultGarPecas.getM10().doubleValue()/dolarOut));
			    		resultGarPecas.setM11(Double.valueOf(resultGarPecas.getM11().doubleValue()/dolarNov));
			    		resultGarPecas.setM12(Double.valueOf(resultGarPecas.getM12().doubleValue()/dolarDez));
			    	}
		    	}
		    	
		    	//Armazena os valores da Mão de Obra de Peças
		    	if (resultGarPecas.getTopico().equals("VL MOBRA PECA")){
		    		vlMobraPcM01 =  resultGarPecas.getM01().doubleValue(); 
		    		vlMobraPcM02 =  resultGarPecas.getM02().doubleValue();   
		    		vlMobraPcM03 =  resultGarPecas.getM03().doubleValue(); 
		    		vlMobraPcM04 =  resultGarPecas.getM04().doubleValue();     
		    		vlMobraPcM05 =  resultGarPecas.getM05().doubleValue();     
		    		vlMobraPcM06 =  resultGarPecas.getM06().doubleValue(); 
		    		vlMobraPcM07 =  resultGarPecas.getM07().doubleValue();     
		    		vlMobraPcM08 =  resultGarPecas.getM08().doubleValue();     
		    		vlMobraPcM09 =  resultGarPecas.getM09().doubleValue(); 
		    		vlMobraPcM10 =  resultGarPecas.getM10().doubleValue();     
		    		vlMobraPcM11 =  resultGarPecas.getM11().doubleValue();     
		    		vlMobraPcM12 =  resultGarPecas.getM12().doubleValue(); 
		    	}
		    	
		    	//Armazena os valores da Mão de Obra de Peças
		    	if (resultGarPecas.getTopico().equals("VL MOBRA TERC")){
		    		vlMobraTerM01 =  resultGarPecas.getM01().doubleValue(); 
		    		vlMobraTerM02 =  resultGarPecas.getM02().doubleValue();   
		    		vlMobraTerM03 =  resultGarPecas.getM03().doubleValue(); 
		    		vlMobraTerM04 =  resultGarPecas.getM04().doubleValue();     
		    		vlMobraTerM05 =  resultGarPecas.getM05().doubleValue();     
		    		vlMobraTerM06 =  resultGarPecas.getM06().doubleValue(); 
		    		vlMobraTerM07 =  resultGarPecas.getM07().doubleValue();     
		    		vlMobraTerM08 =  resultGarPecas.getM08().doubleValue();     
		    		vlMobraTerM09 =  resultGarPecas.getM09().doubleValue(); 
		    		vlMobraTerM10 =  resultGarPecas.getM10().doubleValue();     
		    		vlMobraTerM11 =  resultGarPecas.getM11().doubleValue();     
		    		vlMobraTerM12 =  resultGarPecas.getM12().doubleValue(); 
		    	}
		    	
		    	//Armazena os valores da Peças Garantia
		    	if (resultGarPecas.getTopico().equals("VL PCA GARAN")){
		    		vlGarM01 =  resultGarPecas.getM01().doubleValue(); 
		    		vlGarM02 =  resultGarPecas.getM02().doubleValue();   
		    		vlGarM03 =  resultGarPecas.getM03().doubleValue(); 
		    		vlGarM04 =  resultGarPecas.getM04().doubleValue();     
		    		vlGarM05 =  resultGarPecas.getM05().doubleValue();     
		    		vlGarM06 =  resultGarPecas.getM06().doubleValue(); 
		    		vlGarM07 =  resultGarPecas.getM07().doubleValue();     
		    		vlGarM08 =  resultGarPecas.getM08().doubleValue();     
		    		vlGarM09 =  resultGarPecas.getM09().doubleValue(); 
		    		vlGarM10 =  resultGarPecas.getM10().doubleValue();     
		    		vlGarM11 =  resultGarPecas.getM11().doubleValue();     
		    		vlGarM12 =  resultGarPecas.getM12().doubleValue();
		    	}
		    	results.add(resultGarPecas);
		    }
		    
		    //Adiciona o Valor Acumulado da Mão de Obra no relatório
		    resultVlAcmMobra.setAno(ano);
		    resultVlAcmMobra.setTopico("VL ACM MOBRA");
		    resultVlAcmMobra.setM01(Double.valueOf((vlMobraTerM01 + vlMobraPcM01) / dolarJan));
		    resultVlAcmMobra.setM02(Double.valueOf((vlMobraTerM02 + vlMobraPcM02) / dolarFev));
		    resultVlAcmMobra.setM03(Double.valueOf((vlMobraTerM03 + vlMobraPcM03) / dolarMar));
		    resultVlAcmMobra.setM04(Double.valueOf((vlMobraTerM04 + vlMobraPcM04) / dolarAbr));
		    resultVlAcmMobra.setM05(Double.valueOf((vlMobraTerM05 + vlMobraPcM05) / dolarMai));
		    resultVlAcmMobra.setM06(Double.valueOf((vlMobraTerM06 + vlMobraPcM06) / dolarJun));
		    resultVlAcmMobra.setM07(Double.valueOf((vlMobraTerM07 + vlMobraPcM07) / dolarJul));
		    resultVlAcmMobra.setM08(Double.valueOf((vlMobraTerM08 + vlMobraPcM08) / dolarAgo));
		    resultVlAcmMobra.setM09(Double.valueOf((vlMobraTerM09 + vlMobraPcM09) / dolarSet));
		    resultVlAcmMobra.setM10(Double.valueOf((vlMobraTerM10 + vlMobraPcM10) / dolarOut));
		    resultVlAcmMobra.setM11(Double.valueOf((vlMobraTerM11 + vlMobraPcM11) / dolarNov));
		    resultVlAcmMobra.setM12(Double.valueOf((vlMobraTerM12 + vlMobraPcM12) / dolarDez));
		    resultGarPecas.addListVlAcmMobra(resultVlAcmMobra);
		    //results.add(resultGarPecas);
		    
		    //Adiciona o Valor do Subtotal
		    resultSubtotal.setAno(ano);
		    resultSubtotal.setTopico("SUBTOTAL");
		    resultSubtotal.setM01(Double.valueOf((vlGarM01 + vlMobraPcM01 + vlMobraTerM01) / dolarJan));
		    resultSubtotal.setM02(Double.valueOf((vlGarM02 + vlMobraPcM02 + vlMobraTerM02) / dolarFev));
		    resultSubtotal.setM03(Double.valueOf((vlGarM03 + vlMobraPcM03 + vlMobraTerM03) / dolarMar));
		    resultSubtotal.setM04(Double.valueOf((vlGarM04 + vlMobraPcM04 + vlMobraTerM04) / dolarAbr));
		    resultSubtotal.setM05(Double.valueOf((vlGarM05 + vlMobraPcM05 + vlMobraTerM05) / dolarMai));
		    resultSubtotal.setM06(Double.valueOf((vlGarM06 + vlMobraPcM06 + vlMobraTerM06) / dolarJun));
		    resultSubtotal.setM07(Double.valueOf((vlGarM07 + vlMobraPcM07 + vlMobraTerM07) / dolarJul));
		    resultSubtotal.setM08(Double.valueOf((vlGarM08 + vlMobraPcM08 + vlMobraTerM08) / dolarAgo));
		    resultSubtotal.setM09(Double.valueOf((vlGarM09 + vlMobraPcM09 + vlMobraTerM09) / dolarSet));
		    resultSubtotal.setM10(Double.valueOf((vlGarM10 + vlMobraPcM10 + vlMobraTerM10) / dolarOut));
		    resultSubtotal.setM11(Double.valueOf((vlGarM11 + vlMobraPcM11 + vlMobraTerM11) / dolarNov));
		    resultSubtotal.setM12(Double.valueOf((vlGarM12 + vlMobraPcM12 + vlMobraTerM12) / dolarDez));
		    resultGarPecas.addListSubtotal(resultSubtotal);
		    //results.add(resultGarPecas);
		    
		    //Adiciona o valor de Orçamento de Peças + Mão de Obra Revisão no relatorio
		    Iterator orcPcsMobraIt = orcPcsMobra.iterator();
	    	while(orcPcsMobraIt.hasNext()){
	    		resultOrcPcsMobra = (RelatorioGarPecasSubReportVO)orcPcsMobraIt.next();
	    		resultGarPecas.addListOrcPcsMobra(resultOrcPcsMobra);
	    	}
	    	//results.add(resultGarPecas);
	    	
		    //Adiciona o valor de Detalhes das Revisões no relatorio
		    Iterator revisoesIt = revisoes.iterator();
	    	while(revisoesIt.hasNext()){
	    		resultRevisoes = (RelatorioGarPecasSubReportVO)revisoesIt.next();
	    		resultRevisoes.setM01(Double.valueOf(resultRevisoes.getM01().doubleValue() / dolarJan));		    		
	    		resultRevisoes.setM02(Double.valueOf(resultRevisoes.getM02().doubleValue() / dolarFev));
	    		resultRevisoes.setM03(Double.valueOf(resultRevisoes.getM03().doubleValue() / dolarMar));
	    		resultRevisoes.setM04(Double.valueOf(resultRevisoes.getM04().doubleValue() / dolarAbr));
	    		resultRevisoes.setM05(Double.valueOf(resultRevisoes.getM05().doubleValue() / dolarMai));
	    		resultRevisoes.setM06(Double.valueOf(resultRevisoes.getM06().doubleValue() / dolarJun));
	    		resultRevisoes.setM07(Double.valueOf(resultRevisoes.getM07().doubleValue() / dolarJul)); 
	    		resultRevisoes.setM08(Double.valueOf(resultRevisoes.getM08().doubleValue() / dolarAgo)); 
	    		resultRevisoes.setM09(Double.valueOf(resultRevisoes.getM09().doubleValue() / dolarSet)); 
	    		resultRevisoes.setM10(Double.valueOf(resultRevisoes.getM10().doubleValue() / dolarOut)); 
	    		resultRevisoes.setM11(Double.valueOf(resultRevisoes.getM11().doubleValue() / dolarNov)); 
	    		resultRevisoes.setM12(Double.valueOf(resultRevisoes.getM12().doubleValue() / dolarDez)); 
	    		
	    		//Armazena os totais de Revisão
		    	if (resultRevisoes.getTopico().equals("TOT REVISOES")){
		    		totRevM01 =  resultRevisoes.getM01().doubleValue(); 
		    		totRevM02 =  resultRevisoes.getM02().doubleValue();   
		    		totRevM03 =  resultRevisoes.getM03().doubleValue(); 
		    		totRevM04 =  resultRevisoes.getM04().doubleValue();     
		    		totRevM05 =  resultRevisoes.getM05().doubleValue();     
		    		totRevM06 =  resultRevisoes.getM06().doubleValue(); 
		    		totRevM07 =  resultRevisoes.getM07().doubleValue();     
		    		totRevM08 =  resultRevisoes.getM08().doubleValue();     
		    		totRevM09 =  resultRevisoes.getM09().doubleValue(); 
		    		totRevM10 =  resultRevisoes.getM10().doubleValue();     
		    		totRevM11 =  resultRevisoes.getM11().doubleValue();     
		    		totRevM12 =  resultRevisoes.getM12().doubleValue(); 
		    	}
	    		resultGarPecas.addListRevisoes(resultRevisoes);
	    	}
	    	//results.add(resultGarPecas);
	    	
	    	//Adiciona o valor de Orçamento Revisão no relatorio
		    Iterator orcRevisaoIt = orcRevisao.iterator();
	    	while(orcRevisaoIt.hasNext()){
	    		resultOrcRevisao = (RelatorioGarPecasSubReportVO)orcRevisaoIt.next();
	    		resultGarPecas.addListOrcRevisao(resultOrcRevisao);
	    	}
	    	//results.add(resultGarPecas);
	    	
		    //Adiciona o Valor Total 
		    resultTotal.setAno(ano);
		    resultTotal.setTopico("TOTAL");
		    resultTotal.setM01(Double.valueOf((vlGarM01 + vlMobraPcM01 + vlMobraTerM01 + totRevM01) / dolarJan));
		    resultTotal.setM02(Double.valueOf((vlGarM02 + vlMobraPcM02 + vlMobraTerM02 + totRevM02) / dolarFev));
		    resultTotal.setM03(Double.valueOf((vlGarM03 + vlMobraPcM03 + vlMobraTerM03 + totRevM03) / dolarMar));
		    resultTotal.setM04(Double.valueOf((vlGarM04 + vlMobraPcM04 + vlMobraTerM04 + totRevM04) / dolarAbr));
		    resultTotal.setM05(Double.valueOf((vlGarM05 + vlMobraPcM05 + vlMobraTerM05 + totRevM05) / dolarMai));
		    resultTotal.setM06(Double.valueOf((vlGarM06 + vlMobraPcM06 + vlMobraTerM06 + totRevM06) / dolarJun));
		    resultTotal.setM07(Double.valueOf((vlGarM07 + vlMobraPcM07 + vlMobraTerM07 + totRevM07) / dolarJul));
		    resultTotal.setM08(Double.valueOf((vlGarM08 + vlMobraPcM08 + vlMobraTerM08 + totRevM08) / dolarAgo));
		    resultTotal.setM09(Double.valueOf((vlGarM09 + vlMobraPcM09 + vlMobraTerM09 + totRevM09) / dolarSet));
		    resultTotal.setM10(Double.valueOf((vlGarM10 + vlMobraPcM10 + vlMobraTerM10 + totRevM10) / dolarOut));
		    resultTotal.setM11(Double.valueOf((vlGarM11 + vlMobraPcM11 + vlMobraTerM11 + totRevM11) / dolarNov));
		    resultTotal.setM12(Double.valueOf((vlGarM12 + vlMobraPcM12 + vlMobraTerM12 + totRevM12) / dolarDez));
		    resultGarPecas.addListTotal(resultTotal);
		    //results.add(resultGarPecas);
		   
	    	//Adiciona o valor de Orçamento Total no relatorio
		    Iterator orcTotalIt = orcTotal.iterator();
	    	while(orcTotalIt.hasNext()){
	    		resultOrcTotal = (RelatorioGarPecasSubReportVO)orcTotalIt.next();
	    		resultGarPecas.addListOrcTotal(resultOrcTotal);
	    	}
	    	//results.add(resultGarPecas);		    
		    
	    	//Adiciona o valor de Valor do faturamento no relatorio
		    Iterator vlFaturIt = vlFatur.iterator();
	    	while(vlFaturIt.hasNext()){
	    		resultVlFatur = (RelatorioGarPecasSubReportVO)vlFaturIt.next();
	    		resultVlFatur.setM01(Double.valueOf(resultVlFatur.getM01().doubleValue() / dolarJan));	    		
	    		resultVlFatur.setM02(Double.valueOf(resultVlFatur.getM02().doubleValue() / dolarFev));		    		
	    		resultVlFatur.setM03(Double.valueOf(resultVlFatur.getM03().doubleValue() / dolarMar));                                                                                                        
	    		resultVlFatur.setM04(Double.valueOf(resultVlFatur.getM04().doubleValue() / dolarAbr));
	    		resultVlFatur.setM05(Double.valueOf(resultVlFatur.getM05().doubleValue() / dolarMai));
	    		resultVlFatur.setM06(Double.valueOf(resultVlFatur.getM06().doubleValue() / dolarJun));
	    		resultVlFatur.setM07(Double.valueOf(resultVlFatur.getM07().doubleValue() / dolarJul));
	    		resultVlFatur.setM08(Double.valueOf(resultVlFatur.getM08().doubleValue() / dolarAgo));
	    		resultVlFatur.setM09(Double.valueOf(resultVlFatur.getM09().doubleValue() / dolarSet));
	    		resultVlFatur.setM10(Double.valueOf(resultVlFatur.getM10().doubleValue() / dolarOut));
	    		resultVlFatur.setM11(Double.valueOf(resultVlFatur.getM11().doubleValue() / dolarNov));
	    		resultVlFatur.setM12(Double.valueOf(resultVlFatur.getM12().doubleValue() / dolarDez));
	    		
	    		//Armazena os valores do Faturamento
	    		vlFaturM01 =  resultVlFatur.getM01().doubleValue();  
	    		vlFaturM02 =  resultVlFatur.getM02().doubleValue();   
	    		vlFaturM03 =  resultVlFatur.getM03().doubleValue(); 
	    		vlFaturM04 =  resultVlFatur.getM04().doubleValue();     
	    		vlFaturM05 =  resultVlFatur.getM05().doubleValue();     
	    		vlFaturM06 =  resultVlFatur.getM06().doubleValue(); 
	    		vlFaturM07 =  resultVlFatur.getM07().doubleValue();     
	    		vlFaturM08 =  resultVlFatur.getM08().doubleValue();     
	    		vlFaturM09 =  resultVlFatur.getM09().doubleValue(); 
	    		vlFaturM10 =  resultVlFatur.getM10().doubleValue();     
	    		vlFaturM11 =  resultVlFatur.getM11().doubleValue();     
	    		vlFaturM12 =  resultVlFatur.getM12().doubleValue(); 

	    		resultGarPecas.addListVlFatur(resultVlFatur);
	    	}
	    	//results.add(resultGarPecas);	
	    	
	    	//Adiciona o valor das qtdes de sg's, moto e faturada no relatorio
		    Iterator qtdesIt = qtdes.iterator();
	    	while(qtdesIt.hasNext()){
	    		resultQtdes = (RelatorioGarPecasSubReportVO)qtdesIt.next();
	    		resultGarPecas.addListQtdes(resultQtdes);
	    	}
	    	//results.add(resultGarPecas);	    	
	    	
	    	
	    	//Adiciona o valor de Garantia/Faturamento no relatorio
	    	if (resultSubtotal.getM01().doubleValue() == 0 || vlFaturM01 == 0){
	    		resultGarVlFatur.setM01(new Double(0));
	    	}else{
	    		resultGarVlFatur.setM01(Double.valueOf((resultSubtotal.getM01().doubleValue() / vlFaturM01) * 100 ));
	    	}	    	
	    	
	    	if (resultSubtotal.getM02().doubleValue() == 0 || vlFaturM02 == 0){
	    		resultGarVlFatur.setM02(new Double(0));
	    	}else{
	    		resultGarVlFatur.setM02(Double.valueOf((resultSubtotal.getM02().doubleValue() / vlFaturM02) * 100 ));
	    	}
	    	
	    	if (resultSubtotal.getM03().doubleValue() == 0 || vlFaturM03 == 0){
	    		resultGarVlFatur.setM03(new Double(0));
	    	}else{
	    		resultGarVlFatur.setM03(Double.valueOf((resultSubtotal.getM03().doubleValue() / vlFaturM03) * 100 ));
	    	}
	    	
	    	if (resultSubtotal.getM04().doubleValue() == 0 || vlFaturM04 == 0){
	    		resultGarVlFatur.setM04(new Double(0));
	    	}else{
	    		resultGarVlFatur.setM04(Double.valueOf((resultSubtotal.getM04().doubleValue() / vlFaturM04) * 100 ));
	    	}
	    	
	    	if (resultSubtotal.getM05().doubleValue() == 0 || vlFaturM05 == 0){
	    		resultGarVlFatur.setM05(new Double(0));
	    	}else{
	    		resultGarVlFatur.setM05(Double.valueOf((resultSubtotal.getM05().doubleValue() / vlFaturM05) * 100 ));
	    	}	    	
	    	
	    	if (resultSubtotal.getM06().doubleValue() == 0 || vlFaturM06 == 0){
	    		resultGarVlFatur.setM06(new Double(0));
	    	}else{
	    		resultGarVlFatur.setM06(Double.valueOf((resultSubtotal.getM06().doubleValue() / vlFaturM06) * 100 ));
	    	}
	    	
	    	if (resultSubtotal.getM07().doubleValue() == 0 || vlFaturM07 == 0){
	    		resultGarVlFatur.setM07(new Double(0));
	    	}else{
	    		resultGarVlFatur.setM07(Double.valueOf((resultSubtotal.getM07().doubleValue() / vlFaturM07) * 100 ));
	    	}
	    	
	    	if (resultSubtotal.getM08().doubleValue() == 0 || vlFaturM08 == 0){
	    		resultGarVlFatur.setM08(new Double(0));
	    	}else{
	    		resultGarVlFatur.setM08(Double.valueOf((resultSubtotal.getM08().doubleValue() / vlFaturM08) * 100 ));
	    	}
	    	
	    	if (resultSubtotal.getM09().doubleValue() == 0 || vlFaturM09 == 0){
	    		resultGarVlFatur.setM09(new Double(0));
	    	}else{
	    		resultGarVlFatur.setM09(Double.valueOf((resultSubtotal.getM09().doubleValue() / vlFaturM09) * 100 ));
	    	}
	    	
	    	if (resultSubtotal.getM10().doubleValue() == 0 || vlFaturM10 == 0){
	    		resultGarVlFatur.setM10(new Double(0));
	    	}else{
	    		resultGarVlFatur.setM10(Double.valueOf((resultSubtotal.getM10().doubleValue() / vlFaturM10) * 100 ));
	    	}
	    	
	    	if (resultSubtotal.getM11().doubleValue() == 0 || vlFaturM11 == 0){
	    		resultGarVlFatur.setM11(new Double(0));
	    	}else{
	    		resultGarVlFatur.setM11(Double.valueOf((resultSubtotal.getM11().doubleValue() / vlFaturM11) * 100 ));
	    	}
	    	
	    	if (resultSubtotal.getM12().doubleValue() == 0 || vlFaturM12 == 0){
	    		resultGarVlFatur.setM12(new Double(0));
	    	}else{
	    		resultGarVlFatur.setM12(Double.valueOf((resultSubtotal.getM12().doubleValue() / vlFaturM12) * 100 ));
	    	}
	    	resultGarPecas.addListGarVlFatur(resultGarVlFatur);
	    	//results.add(resultGarPecas);	
	    	
	    	
	    	//Adiciona o valor do Total Revisões/Faturamento
	    	if (totRevM01 == 0 || vlFaturM01 == 0){
	    		resultRevVlFatur.setM01(new Double(0));
	    	}else{
	    		resultRevVlFatur.setM01(Double.valueOf((totRevM01 / vlFaturM01) * 100 ));
	    	}	    	
	    	
	    	if (vlFaturM02 == 0 || vlFaturM02 == 0){
	    		resultRevVlFatur.setM02(new Double(0));
	    	}else{
	    		resultRevVlFatur.setM02(Double.valueOf((totRevM02 / vlFaturM02) * 100 ));
	    	}
	    	
	    	if (totRevM03 == 0 || vlFaturM03 == 0){
	    		resultRevVlFatur.setM03(new Double(0));
	    	}else{
	    		resultRevVlFatur.setM03(Double.valueOf((totRevM03 / vlFaturM03) * 100 ));
	    	}
	    	
	    	if (totRevM04 == 0 || vlFaturM04 == 0){
	    		resultRevVlFatur.setM04(new Double(0));
	    	}else{
	    		resultRevVlFatur.setM04(Double.valueOf((totRevM04 / vlFaturM04) * 100 ));
	    	}
	    	
	    	if (totRevM05 == 0 || vlFaturM05 == 0){
	    		resultRevVlFatur.setM05(new Double(0));
	    	}else{
	    		resultRevVlFatur.setM05(Double.valueOf((totRevM05 / vlFaturM05) * 100 ));
	    	}	    	
	    	
	    	if (totRevM06 == 0 || vlFaturM06 == 0){
	    		resultRevVlFatur.setM06(new Double(0));
	    	}else{
	    		resultRevVlFatur.setM06(Double.valueOf((totRevM06 / vlFaturM06) * 100 ));
	    	}
	    	
	    	if (totRevM07 == 0 || vlFaturM07 == 0){
	    		resultRevVlFatur.setM07(new Double(0));
	    	}else{
	    		resultRevVlFatur.setM07(Double.valueOf((totRevM07 / vlFaturM07) * 100 ));
	    	}
	    	
	    	if (totRevM08 == 0 || vlFaturM08 == 0){
	    		resultRevVlFatur.setM08(new Double(0));
	    	}else{
	    		resultRevVlFatur.setM08(Double.valueOf((totRevM08 / vlFaturM08) * 100 ));
	    	}
	    	
	    	if (totRevM09 == 0 || vlFaturM09 == 0){
	    		resultRevVlFatur.setM09(new Double(0));
	    	}else{
	    		resultRevVlFatur.setM09(Double.valueOf((totRevM09 / vlFaturM09) * 100 ));
	    	}
	    	
	    	if (totRevM10 == 0 || vlFaturM10 == 0){
	    		resultRevVlFatur.setM10(new Double(0));
	    	}else{
	    		resultRevVlFatur.setM10(Double.valueOf((totRevM10 / vlFaturM10) * 100 ));
	    	}
	    	
	    	if (totRevM11 == 0 || vlFaturM11 == 0){
	    		resultRevVlFatur.setM11(new Double(0));
	    	}else{
	    		resultRevVlFatur.setM11(Double.valueOf((totRevM11 / vlFaturM11) * 100 ));
	    	}
	    	
	    	if (totRevM12 == 0 || vlFaturM12 == 0){
	    		resultRevVlFatur.setM12(new Double(0));
	    	}else{
	    		resultRevVlFatur.setM12(Double.valueOf((totRevM12 / vlFaturM12) * 100 ));
	    	}
	    	resultGarPecas.addListRevVlFatur(resultRevVlFatur);
	    	//results.add(resultGarPecas);		
	    	
	    	
		    //Adiciona o Valor Total 
		    resultTotVlFatur.setAno(ano);
		    resultTotVlFatur.setTopico("TOT/VL FATUR");
		    
		    if (resultTotal.getM01().doubleValue() == 0 || vlFaturM01 == 0){
		    	resultTotVlFatur.setM01(new Double(0));
		    }else{
		    	resultTotVlFatur.setM01(Double.valueOf((resultTotal.getM01().doubleValue() / vlFaturM01) * 100));
		    }
		    
		    if (resultTotal.getM02().doubleValue() == 0 || vlFaturM02 == 0){
		    	resultTotVlFatur.setM02(new Double(0));
		    }else{
		    	resultTotVlFatur.setM02(Double.valueOf((resultTotal.getM02().doubleValue() / vlFaturM02) * 100));
		    }
		    
		    if (resultTotal.getM03().doubleValue() == 0 || vlFaturM03 == 0){
		    	resultTotVlFatur.setM03(new Double(0));
		    }else{
		    	resultTotVlFatur.setM03(Double.valueOf((resultTotal.getM03().doubleValue() / vlFaturM03) * 100));
		    }

		    if (resultTotal.getM04().doubleValue() == 0 || vlFaturM04 == 0){
		    	resultTotVlFatur.setM04(new Double(0));
		    }else{
		    	resultTotVlFatur.setM04(Double.valueOf((resultTotal.getM04().doubleValue() / vlFaturM04) * 100));
		    }
		    
		    if (resultTotal.getM05().doubleValue() == 0 || vlFaturM05 == 0){
		    	resultTotVlFatur.setM05(new Double(0));
		    }else{
		    	resultTotVlFatur.setM05(Double.valueOf((resultTotal.getM05().doubleValue() / vlFaturM05) * 100));
		    }
		    
		    if (resultTotal.getM06().doubleValue() == 0 || vlFaturM06 == 0){
		    	resultTotVlFatur.setM06(new Double(0));
		    }else{
		    	resultTotVlFatur.setM06(Double.valueOf((resultTotal.getM06().doubleValue() / vlFaturM06) * 100));
		    }
		    
		    if (resultTotal.getM07().doubleValue() == 0 || vlFaturM07 == 0){
		    	resultTotVlFatur.setM07(new Double(0));
		    }else{
		    	resultTotVlFatur.setM07(Double.valueOf((resultTotal.getM07().doubleValue() / vlFaturM07) * 100));
		    }
		    
		    if (resultTotal.getM08().doubleValue() == 0 || vlFaturM08 == 0){
		    	resultTotVlFatur.setM08(new Double(0));
		    }else{
		    	resultTotVlFatur.setM08(Double.valueOf((resultTotal.getM08().doubleValue() / vlFaturM08) * 100));
		    }
		    
		    if (resultTotal.getM09().doubleValue() == 0 || vlFaturM09 == 0){
		    	resultTotVlFatur.setM09(new Double(0));
		    }else{
		    	resultTotVlFatur.setM09(Double.valueOf((resultTotal.getM09().doubleValue() / vlFaturM09) * 100));
		    }
		    
		    if (resultTotal.getM10().doubleValue() == 0 || vlFaturM10 == 0){
		    	resultTotVlFatur.setM10(new Double(0));
		    }else{
		    	resultTotVlFatur.setM10(Double.valueOf((resultTotal.getM10().doubleValue() / vlFaturM10) * 100));
		    }
		    
		    if (resultTotal.getM11().doubleValue() == 0 || vlFaturM11 == 0){
		    	resultTotVlFatur.setM11(new Double(0));
		    }else{
		    	resultTotVlFatur.setM11(Double.valueOf((resultTotal.getM11().doubleValue() / vlFaturM11) * 100));
		    }
		    
		    if (resultTotal.getM12().doubleValue() == 0 || vlFaturM12 == 0){
		    	resultTotVlFatur.setM12(new Double(0));
		    }else{
		    	resultTotVlFatur.setM12(Double.valueOf((resultTotal.getM12().doubleValue() / vlFaturM12) * 100));
		    }
		    resultGarPecas.addListTotVlFatur(resultTotVlFatur);
		    results.add(resultGarPecas);		    

		    return results;	
			
		} catch (DaoException dExp) {
			throw new BusinessException(dExp);
		}
	}	

	/** Lista todos os dados para o Relatório de Garantia e Peças.
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório de Garantia e Peças.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      será lançada. Caso hajam problemas de regras de negócio, uma
	 *      BusinessRuleException (subclasse de BusinessException) será 
	 *      lançada.
	 */	
	public List listGarantiaPecasModelo( Long   concessionaria
			                           , Long   linha
			                           , String dataBase
			                           , String modelo
			                           , String moeda) throws BusinessException {
		
        String ano = dataBase.substring(dataBase.indexOf("/")+1, dataBase.length());
        
		try {
			
			List results       = new ArrayList();
			List garantiaPecas = relatorioDao.listRelatorioGarantiaPecasModelo(concessionaria, linha, dataBase, modelo);
			
		    RelatorioGarantiaPecasModVO       resultGarPecasAnt   = new RelatorioGarantiaPecasModVO();
		    RelatorioGarantiaPecasModVO       resultGarPecasAtual = new RelatorioGarantiaPecasModVO();
		    RelatorioGarantiaPecasModSubRepVO resultVlDolar       = new RelatorioGarantiaPecasModSubRepVO();
		    RelatorioGarantiaPecasModSubRepVO resultVlAcmMobra    = null;
		    RelatorioGarantiaPecasModSubRepVO resultRevisoes      = null;
		    RelatorioGarantiaPecasModSubRepVO resultSubtotal      = null;
		    RelatorioGarantiaPecasModSubRepVO resultTotal         = null;
		    RelatorioGarantiaPecasModSubRepVO resultVlFatur       = null;
		    RelatorioGarantiaPecasModSubRepVO resultQtdes         = null;
		    RelatorioGarantiaPecasModSubRepVO resultGarVlFatur    = null;
		    RelatorioGarantiaPecasModSubRepVO resultRevVlFatur    = null;
		    RelatorioGarantiaPecasModSubRepVO resultTotVlFatur    = null;
	    	
	    	// Variáveis para armazenar a cotação do dólar
	    	double dolarJan = 1;            double dolarFev = 1;            double dolarMar = 1;
    		double dolarAbr = 1;            double dolarMai = 1;            double dolarJun = 1;
    		double dolarJul = 1;            double dolarAgo = 1;            double dolarSet = 1;
    		double dolarOut = 1;            double dolarNov = 1;            double dolarDez = 1;
    		
    		//Variáveis do Valor Acumulado de Mão de Obra Peça
    		double vlMobraPcM01 = 0;     double vlMobraPcM02 = 0;     double vlMobraPcM03 = 0;
    		double vlMobraPcM04 = 0;     double vlMobraPcM05 = 0;     double vlMobraPcM06 = 0;
    		double vlMobraPcM07 = 0;     double vlMobraPcM08 = 0;     double vlMobraPcM09 = 0;
    		double vlMobraPcM10 = 0;     double vlMobraPcM11 = 0;     double vlMobraPcM12 = 0;

    		//Variáveis do Valor Mão de Obra de Terceiros
    		double vlMobraTerM01 = 0;    double vlMobraTerM02 = 0;    double vlMobraTerM03 = 0;
    		double vlMobraTerM04 = 0;    double vlMobraTerM05 = 0;    double vlMobraTerM06 = 0;
    		double vlMobraTerM07 = 0;    double vlMobraTerM08 = 0;    double vlMobraTerM09 = 0;
    		double vlMobraTerM10 = 0;    double vlMobraTerM11 = 0;    double vlMobraTerM12 = 0;
            
            //Variáveis do Valor de Garantia
            double vlGarM01 = 0;            double vlGarM02 = 0;            double vlGarM03 = 0;
            double vlGarM04 = 0;            double vlGarM05 = 0;            double vlGarM06 = 0;
            double vlGarM07 = 0;            double vlGarM08 = 0;            double vlGarM09 = 0;
            double vlGarM10 = 0;            double vlGarM11 = 0;            double vlGarM12 = 0;
            
            //Variáveis do Total de Revisões
            double totRevM01 = 0;           double totRevM02 = 0;           double totRevM03 = 0;
            double totRevM04 = 0;           double totRevM05 = 0;           double totRevM06 = 0;
            double totRevM07 = 0;           double totRevM08 = 0;           double totRevM09 = 0;
            double totRevM10 = 0;           double totRevM11 = 0;           double totRevM12 = 0;
            
            //Variáveis do Valor Faturado
            double vlFaturM01 = 0;          double vlFaturM02 = 0;          double vlFaturM03 = 0;
            double vlFaturM04 = 0;          double vlFaturM05 = 0;          double vlFaturM06 = 0;
            double vlFaturM07 = 0;          double vlFaturM08 = 0;          double vlFaturM09 = 0;
            double vlFaturM10 = 0;          double vlFaturM11 = 0;          double vlFaturM12 = 0;
            
            //Variável pra controlar a primeira passagem pelo loop
            boolean primeiroLoop = true;
            
            //Se a moeda for dolar, recuperamos o valor do dólar para cálculo dos demais valores
        	List dolar = relatorioDao.listRelatorioGPMDolar(ano);
			Iterator dolarIt = dolar.iterator();
			while(dolarIt.hasNext()){
				resultVlDolar = (RelatorioGarantiaPecasModSubRepVO)dolarIt.next();
	    	}
			if (moeda.equals("dolar")){
				//Carrega os valores do dólar nas variáveis
	    		dolarJan = resultVlDolar.getM01().doubleValue();
	    		dolarFev = resultVlDolar.getM02().doubleValue();
	    		dolarMar = resultVlDolar.getM03().doubleValue();
	    		dolarAbr = resultVlDolar.getM04().doubleValue();
	    		dolarMai = resultVlDolar.getM05().doubleValue();
	    		dolarJun = resultVlDolar.getM06().doubleValue();
	    		dolarJul = resultVlDolar.getM07().doubleValue();
	    		dolarAgo = resultVlDolar.getM08().doubleValue();
	    		dolarSet = resultVlDolar.getM09().doubleValue();
	    		dolarOut = resultVlDolar.getM10().doubleValue();
	    		dolarNov = resultVlDolar.getM11().doubleValue();
	    		dolarDez = resultVlDolar.getM12().doubleValue();
			}
    		
    		//Adiciona os dados de Garantia e Peças no relatório
		    Iterator garPecasIt = garantiaPecas.iterator();
		    while (garPecasIt.hasNext()){
		    	
		    	//Carrega o resultGarPecasAnt  e o resultGarPecasAtual se for o primeiro loop
		    	if (primeiroLoop){
		    		resultGarPecasAnt   = (RelatorioGarantiaPecasModVO)garPecasIt.next();
		    		resultGarPecasAtual = (RelatorioGarantiaPecasModVO)garPecasIt.next();
		    		primeiroLoop        = false;
		    	}else{
		    		resultGarPecasAtual = (RelatorioGarantiaPecasModVO)garPecasIt.next();
		    	}
		    	
		    	//Se a moeda for dolar, divide os valores obtidos pelo valor do dólar
    			if (moeda.equals("dolar")){
		    		resultGarPecasAnt.setM01(Double.valueOf(resultGarPecasAnt.getM01().doubleValue()/dolarJan));
		    		resultGarPecasAnt.setM02(Double.valueOf(resultGarPecasAnt.getM02().doubleValue()/dolarFev));
		    		resultGarPecasAnt.setM03(Double.valueOf(resultGarPecasAnt.getM03().doubleValue()/dolarMar));
		    		resultGarPecasAnt.setM04(Double.valueOf(resultGarPecasAnt.getM04().doubleValue()/dolarAbr));
		    		resultGarPecasAnt.setM05(Double.valueOf(resultGarPecasAnt.getM05().doubleValue()/dolarMai));
		    		resultGarPecasAnt.setM06(Double.valueOf(resultGarPecasAnt.getM06().doubleValue()/dolarJun));
		    		resultGarPecasAnt.setM07(Double.valueOf(resultGarPecasAnt.getM07().doubleValue()/dolarJul));
		    		resultGarPecasAnt.setM08(Double.valueOf(resultGarPecasAnt.getM08().doubleValue()/dolarAgo));
		    		resultGarPecasAnt.setM09(Double.valueOf(resultGarPecasAnt.getM09().doubleValue()/dolarSet));
		    		resultGarPecasAnt.setM10(Double.valueOf(resultGarPecasAnt.getM10().doubleValue()/dolarOut));
		    		resultGarPecasAnt.setM11(Double.valueOf(resultGarPecasAnt.getM11().doubleValue()/dolarNov));
		    		resultGarPecasAnt.setM12(Double.valueOf(resultGarPecasAnt.getM12().doubleValue()/dolarDez));
		    	}
		    	
		    	//Armazena os valores da Mão de Obra de Peças
		    	if (resultGarPecasAnt.getTopico().equals("VL MOBRA PECA")){
		    		vlMobraPcM01 =  resultGarPecasAnt.getM01().doubleValue(); 
		    		vlMobraPcM02 =  resultGarPecasAnt.getM02().doubleValue();   
		    		vlMobraPcM03 =  resultGarPecasAnt.getM03().doubleValue(); 
		    		vlMobraPcM04 =  resultGarPecasAnt.getM04().doubleValue();     
		    		vlMobraPcM05 =  resultGarPecasAnt.getM05().doubleValue();     
		    		vlMobraPcM06 =  resultGarPecasAnt.getM06().doubleValue(); 
		    		vlMobraPcM07 =  resultGarPecasAnt.getM07().doubleValue();     
		    		vlMobraPcM08 =  resultGarPecasAnt.getM08().doubleValue();     
		    		vlMobraPcM09 =  resultGarPecasAnt.getM09().doubleValue(); 
		    		vlMobraPcM10 =  resultGarPecasAnt.getM10().doubleValue();     
		    		vlMobraPcM11 =  resultGarPecasAnt.getM11().doubleValue();     
		    		vlMobraPcM12 =  resultGarPecasAnt.getM12().doubleValue(); 
		    	}
		    	
		    	//Armazena os valores da Mão de Obra de Peças
		    	if (resultGarPecasAnt.getTopico().equals("VL MOBRA TERC")){
		    		vlMobraTerM01 =  resultGarPecasAnt.getM01().doubleValue(); 
		    		vlMobraTerM02 =  resultGarPecasAnt.getM02().doubleValue();   
		    		vlMobraTerM03 =  resultGarPecasAnt.getM03().doubleValue(); 
		    		vlMobraTerM04 =  resultGarPecasAnt.getM04().doubleValue();     
		    		vlMobraTerM05 =  resultGarPecasAnt.getM05().doubleValue();     
		    		vlMobraTerM06 =  resultGarPecasAnt.getM06().doubleValue(); 
		    		vlMobraTerM07 =  resultGarPecasAnt.getM07().doubleValue();     
		    		vlMobraTerM08 =  resultGarPecasAnt.getM08().doubleValue();     
		    		vlMobraTerM09 =  resultGarPecasAnt.getM09().doubleValue(); 
		    		vlMobraTerM10 =  resultGarPecasAnt.getM10().doubleValue();     
		    		vlMobraTerM11 =  resultGarPecasAnt.getM11().doubleValue();     
		    		vlMobraTerM12 =  resultGarPecasAnt.getM12().doubleValue(); 
		    	}
		    	
		    	//Armazena os valores da Peças Garantia
		    	if (resultGarPecasAnt.getTopico().equals("VL PCA GARAN")){
		    		vlGarM01 =  resultGarPecasAnt.getM01().doubleValue(); 
		    		vlGarM02 =  resultGarPecasAnt.getM02().doubleValue();   
		    		vlGarM03 =  resultGarPecasAnt.getM03().doubleValue(); 
		    		vlGarM04 =  resultGarPecasAnt.getM04().doubleValue();     
		    		vlGarM05 =  resultGarPecasAnt.getM05().doubleValue();     
		    		vlGarM06 =  resultGarPecasAnt.getM06().doubleValue(); 
		    		vlGarM07 =  resultGarPecasAnt.getM07().doubleValue();     
		    		vlGarM08 =  resultGarPecasAnt.getM08().doubleValue();     
		    		vlGarM09 =  resultGarPecasAnt.getM09().doubleValue(); 
		    		vlGarM10 =  resultGarPecasAnt.getM10().doubleValue();     
		    		vlGarM11 =  resultGarPecasAnt.getM11().doubleValue();     
		    		vlGarM12 =  resultGarPecasAnt.getM12().doubleValue();
		    	}	
		    	results.add(resultGarPecasAnt);
		    	
	    		//Verifica se o modelo atual é igual ao modelo da próxima linha
	    		if (resultGarPecasAnt.getModelo().equals(resultGarPecasAtual.getModelo()) &&
	    			garPecasIt.hasNext()){

	    			resultGarPecasAnt = resultGarPecasAtual;
	    			
			    //Próximo modelo é diferente do atual
	    		}else{
				    //Se for o último registro adiciona o atual e o anterior no result
	    			if (garPecasIt.hasNext() == false){
	    				
	    				//Devemos inserir o último registro (Atual)
	    				resultGarPecasAnt = resultGarPecasAtual;
	    				
	    				//Se a moeda for dolar, divide os valores obtidos pelo valor do dólar
    					if (moeda.equals("dolar")){
    			    		resultGarPecasAnt.setM01(Double.valueOf(resultGarPecasAnt.getM01().doubleValue()/dolarJan));
    			    		resultGarPecasAnt.setM02(Double.valueOf(resultGarPecasAnt.getM02().doubleValue()/dolarFev));
    			    		resultGarPecasAnt.setM03(Double.valueOf(resultGarPecasAnt.getM03().doubleValue()/dolarMar));
    			    		resultGarPecasAnt.setM04(Double.valueOf(resultGarPecasAnt.getM04().doubleValue()/dolarAbr));
    			    		resultGarPecasAnt.setM05(Double.valueOf(resultGarPecasAnt.getM05().doubleValue()/dolarMai));
    			    		resultGarPecasAnt.setM06(Double.valueOf(resultGarPecasAnt.getM06().doubleValue()/dolarJun));
    			    		resultGarPecasAnt.setM07(Double.valueOf(resultGarPecasAnt.getM07().doubleValue()/dolarJul));
    			    		resultGarPecasAnt.setM08(Double.valueOf(resultGarPecasAnt.getM08().doubleValue()/dolarAgo));
    			    		resultGarPecasAnt.setM09(Double.valueOf(resultGarPecasAnt.getM09().doubleValue()/dolarSet));
    			    		resultGarPecasAnt.setM10(Double.valueOf(resultGarPecasAnt.getM10().doubleValue()/dolarOut));
    			    		resultGarPecasAnt.setM11(Double.valueOf(resultGarPecasAnt.getM11().doubleValue()/dolarNov));
    			    		resultGarPecasAnt.setM12(Double.valueOf(resultGarPecasAnt.getM12().doubleValue()/dolarDez));
    			    	}
	    					
	    				//Armazena os valores da Mão de Obra de Peças
    					if (resultGarPecasAnt.getTopico().equals("VL MOBRA PECA")){
    						vlMobraPcM01 =  resultGarPecasAnt.getM01().doubleValue(); 
    						vlMobraPcM02 =  resultGarPecasAnt.getM02().doubleValue();   
    						vlMobraPcM03 =  resultGarPecasAnt.getM03().doubleValue(); 
    						vlMobraPcM04 =  resultGarPecasAnt.getM04().doubleValue();     
    						vlMobraPcM05 =  resultGarPecasAnt.getM05().doubleValue();     
    						vlMobraPcM06 =  resultGarPecasAnt.getM06().doubleValue(); 
    						vlMobraPcM07 =  resultGarPecasAnt.getM07().doubleValue();     
    						vlMobraPcM08 =  resultGarPecasAnt.getM08().doubleValue();     
    						vlMobraPcM09 =  resultGarPecasAnt.getM09().doubleValue(); 
    						vlMobraPcM10 =  resultGarPecasAnt.getM10().doubleValue();     
    						vlMobraPcM11 =  resultGarPecasAnt.getM11().doubleValue();     
    						vlMobraPcM12 =  resultGarPecasAnt.getM12().doubleValue(); 
    					}

    					//Armazena os valores da Mão de Obra de Peças
    					if (resultGarPecasAnt.getTopico().equals("VL MOBRA TERC")){
    						vlMobraTerM01 =  resultGarPecasAnt.getM01().doubleValue(); 
    						vlMobraTerM02 =  resultGarPecasAnt.getM02().doubleValue();   
    						vlMobraTerM03 =  resultGarPecasAnt.getM03().doubleValue(); 
    						vlMobraTerM04 =  resultGarPecasAnt.getM04().doubleValue();     
    						vlMobraTerM05 =  resultGarPecasAnt.getM05().doubleValue();     
    						vlMobraTerM06 =  resultGarPecasAnt.getM06().doubleValue(); 
    						vlMobraTerM07 =  resultGarPecasAnt.getM07().doubleValue();     
    						vlMobraTerM08 =  resultGarPecasAnt.getM08().doubleValue();     
    						vlMobraTerM09 =  resultGarPecasAnt.getM09().doubleValue(); 
    						vlMobraTerM10 =  resultGarPecasAnt.getM10().doubleValue();     
    						vlMobraTerM11 =  resultGarPecasAnt.getM11().doubleValue();     
    						vlMobraTerM12 =  resultGarPecasAnt.getM12().doubleValue(); 
    					}

    					//Armazena os valores da Peças Garantia
    					if (resultGarPecasAnt.getTopico().equals("VL PCA GARAN")){
    						vlGarM01 =  resultGarPecasAnt.getM01().doubleValue(); 
    						vlGarM02 =  resultGarPecasAnt.getM02().doubleValue();   
    						vlGarM03 =  resultGarPecasAnt.getM03().doubleValue(); 
    						vlGarM04 =  resultGarPecasAnt.getM04().doubleValue();     
    						vlGarM05 =  resultGarPecasAnt.getM05().doubleValue();     
    						vlGarM06 =  resultGarPecasAnt.getM06().doubleValue(); 
    						vlGarM07 =  resultGarPecasAnt.getM07().doubleValue();     
    						vlGarM08 =  resultGarPecasAnt.getM08().doubleValue();     
    						vlGarM09 =  resultGarPecasAnt.getM09().doubleValue(); 
    						vlGarM10 =  resultGarPecasAnt.getM10().doubleValue();     
    						vlGarM11 =  resultGarPecasAnt.getM11().doubleValue();     
    						vlGarM12 =  resultGarPecasAnt.getM12().doubleValue();
    					}	
    					results.add(resultGarPecasAnt);
	    			}
	    			
	    			//Adiciona os valores do dólar no relatorio
			    	resultGarPecasAnt.addListVlDolar(resultVlDolar);
	    			
		    		//Adiciona o Valor Acumulado da Mão de Obra no relatório
			    	resultVlAcmMobra = new RelatorioGarantiaPecasModSubRepVO();
				    resultVlAcmMobra.setAno(ano);
				    resultVlAcmMobra.setTopico("VL ACM MOBRA");
				    resultVlAcmMobra.setModelo(resultGarPecasAnt.getModelo());
				    resultVlAcmMobra.setM01(Double.valueOf((vlMobraTerM01 + vlMobraPcM01) / dolarJan));
				    resultVlAcmMobra.setM02(Double.valueOf((vlMobraTerM02 + vlMobraPcM02) / dolarFev));
				    resultVlAcmMobra.setM03(Double.valueOf((vlMobraTerM03 + vlMobraPcM03) / dolarMar));
				    resultVlAcmMobra.setM04(Double.valueOf((vlMobraTerM04 + vlMobraPcM04) / dolarAbr));
				    resultVlAcmMobra.setM05(Double.valueOf((vlMobraTerM05 + vlMobraPcM05) / dolarMai));
				    resultVlAcmMobra.setM06(Double.valueOf((vlMobraTerM06 + vlMobraPcM06) / dolarJun));
				    resultVlAcmMobra.setM07(Double.valueOf((vlMobraTerM07 + vlMobraPcM07) / dolarJul));
				    resultVlAcmMobra.setM08(Double.valueOf((vlMobraTerM08 + vlMobraPcM08) / dolarAgo));
				    resultVlAcmMobra.setM09(Double.valueOf((vlMobraTerM09 + vlMobraPcM09) / dolarSet));
				    resultVlAcmMobra.setM10(Double.valueOf((vlMobraTerM10 + vlMobraPcM10) / dolarOut));
				    resultVlAcmMobra.setM11(Double.valueOf((vlMobraTerM11 + vlMobraPcM11) / dolarNov));
				    resultVlAcmMobra.setM12(Double.valueOf((vlMobraTerM12 + vlMobraPcM12) / dolarDez));
				    resultGarPecasAnt.addListVlAcmMobra(resultVlAcmMobra);
				    
				    //Adiciona o Valor do Subtotal
				    resultSubtotal = new RelatorioGarantiaPecasModSubRepVO();
				    resultSubtotal.setAno(ano);
				    resultSubtotal.setTopico("SUBTOTAL");
				    resultSubtotal.setModelo(resultGarPecasAnt.getModelo());
				    resultSubtotal.setM01(Double.valueOf((vlGarM01 + vlMobraPcM01 + vlMobraTerM01) / dolarJan));
				    resultSubtotal.setM02(Double.valueOf((vlGarM02 + vlMobraPcM02 + vlMobraTerM02) / dolarFev));
				    resultSubtotal.setM03(Double.valueOf((vlGarM03 + vlMobraPcM03 + vlMobraTerM03) / dolarMar));
				    resultSubtotal.setM04(Double.valueOf((vlGarM04 + vlMobraPcM04 + vlMobraTerM04) / dolarAbr));
				    resultSubtotal.setM05(Double.valueOf((vlGarM05 + vlMobraPcM05 + vlMobraTerM05) / dolarMai));
				    resultSubtotal.setM06(Double.valueOf((vlGarM06 + vlMobraPcM06 + vlMobraTerM06) / dolarJun));
				    resultSubtotal.setM07(Double.valueOf((vlGarM07 + vlMobraPcM07 + vlMobraTerM07) / dolarJul));
				    resultSubtotal.setM08(Double.valueOf((vlGarM08 + vlMobraPcM08 + vlMobraTerM08) / dolarAgo));
				    resultSubtotal.setM09(Double.valueOf((vlGarM09 + vlMobraPcM09 + vlMobraTerM09) / dolarSet));
				    resultSubtotal.setM10(Double.valueOf((vlGarM10 + vlMobraPcM10 + vlMobraTerM10) / dolarOut));
				    resultSubtotal.setM11(Double.valueOf((vlGarM11 + vlMobraPcM11 + vlMobraTerM11) / dolarNov));
				    resultSubtotal.setM12(Double.valueOf((vlGarM12 + vlMobraPcM12 + vlMobraTerM12) / dolarDez));
				    resultGarPecasAnt.addListSubtotal(resultSubtotal);
				    
				    //Adiciona o valor de Detalhes das Revisões no relatorio
				    List revisoes = relatorioDao.listRelatorioGPMRevisao(concessionaria, linha, ano, resultGarPecasAnt.getModelo());
				    Iterator revisoesIt = revisoes.iterator();
			    	while(revisoesIt.hasNext()){
			    		resultRevisoes = new RelatorioGarantiaPecasModSubRepVO();
			    		resultRevisoes = (RelatorioGarantiaPecasModSubRepVO)revisoesIt.next();
			    		resultRevisoes.setM01(Double.valueOf(resultRevisoes.getM01().doubleValue() / dolarJan));		    		
			    		resultRevisoes.setM02(Double.valueOf(resultRevisoes.getM02().doubleValue() / dolarFev));
			    		resultRevisoes.setM03(Double.valueOf(resultRevisoes.getM03().doubleValue() / dolarMar));
			    		resultRevisoes.setM04(Double.valueOf(resultRevisoes.getM04().doubleValue() / dolarAbr));
			    		resultRevisoes.setM05(Double.valueOf(resultRevisoes.getM05().doubleValue() / dolarMai));
			    		resultRevisoes.setM06(Double.valueOf(resultRevisoes.getM06().doubleValue() / dolarJun));
			    		resultRevisoes.setM07(Double.valueOf(resultRevisoes.getM07().doubleValue() / dolarJul)); 
			    		resultRevisoes.setM08(Double.valueOf(resultRevisoes.getM08().doubleValue() / dolarAgo)); 
			    		resultRevisoes.setM09(Double.valueOf(resultRevisoes.getM09().doubleValue() / dolarSet)); 
			    		resultRevisoes.setM10(Double.valueOf(resultRevisoes.getM10().doubleValue() / dolarOut)); 
			    		resultRevisoes.setM11(Double.valueOf(resultRevisoes.getM11().doubleValue() / dolarNov)); 
			    		resultRevisoes.setM12(Double.valueOf(resultRevisoes.getM12().doubleValue() / dolarDez)); 
			    		
			    		//Armazena os totais de Revisão
				    	if (resultRevisoes.getTopico().equals("TOT REVISOES")){
				    		totRevM01 =  resultRevisoes.getM01().doubleValue(); 
				    		totRevM02 =  resultRevisoes.getM02().doubleValue();   
				    		totRevM03 =  resultRevisoes.getM03().doubleValue(); 
				    		totRevM04 =  resultRevisoes.getM04().doubleValue();     
				    		totRevM05 =  resultRevisoes.getM05().doubleValue();     
				    		totRevM06 =  resultRevisoes.getM06().doubleValue(); 
				    		totRevM07 =  resultRevisoes.getM07().doubleValue();     
				    		totRevM08 =  resultRevisoes.getM08().doubleValue();     
				    		totRevM09 =  resultRevisoes.getM09().doubleValue(); 
				    		totRevM10 =  resultRevisoes.getM10().doubleValue();     
				    		totRevM11 =  resultRevisoes.getM11().doubleValue();     
				    		totRevM12 =  resultRevisoes.getM12().doubleValue(); 
				    	}
			    		resultGarPecasAnt.addListRevisoes(resultRevisoes);
			    	}
			    	
				    //Adiciona o Valor Total 
			    	resultTotal = new RelatorioGarantiaPecasModSubRepVO();
				    resultTotal.setAno(ano);
				    resultTotal.setTopico("TOTAL");
				    resultTotal.setModelo(resultGarPecasAnt.getModelo());
				    resultTotal.setM01(Double.valueOf((vlGarM01 + vlMobraPcM01 + vlMobraTerM01 + totRevM01) / dolarJan));
				    resultTotal.setM02(Double.valueOf((vlGarM02 + vlMobraPcM02 + vlMobraTerM02 + totRevM02) / dolarFev));
				    resultTotal.setM03(Double.valueOf((vlGarM03 + vlMobraPcM03 + vlMobraTerM03 + totRevM03) / dolarMar));
				    resultTotal.setM04(Double.valueOf((vlGarM04 + vlMobraPcM04 + vlMobraTerM04 + totRevM04) / dolarAbr));
				    resultTotal.setM05(Double.valueOf((vlGarM05 + vlMobraPcM05 + vlMobraTerM05 + totRevM05) / dolarMai));
				    resultTotal.setM06(Double.valueOf((vlGarM06 + vlMobraPcM06 + vlMobraTerM06 + totRevM06) / dolarJun));
				    resultTotal.setM07(Double.valueOf((vlGarM07 + vlMobraPcM07 + vlMobraTerM07 + totRevM07) / dolarJul));
				    resultTotal.setM08(Double.valueOf((vlGarM08 + vlMobraPcM08 + vlMobraTerM08 + totRevM08) / dolarAgo));
				    resultTotal.setM09(Double.valueOf((vlGarM09 + vlMobraPcM09 + vlMobraTerM09 + totRevM09) / dolarSet));
				    resultTotal.setM10(Double.valueOf((vlGarM10 + vlMobraPcM10 + vlMobraTerM10 + totRevM10) / dolarOut));
				    resultTotal.setM11(Double.valueOf((vlGarM11 + vlMobraPcM11 + vlMobraTerM11 + totRevM11) / dolarNov));
				    resultTotal.setM12(Double.valueOf((vlGarM12 + vlMobraPcM12 + vlMobraTerM12 + totRevM12) / dolarDez));
				    resultGarPecasAnt.addListTotal(resultTotal);
				   
			    	//Adiciona o valor de Valor do faturamento no relatorio
				    List vlFatur = relatorioDao.listRelatorioGPMVlFatur(concessionaria, linha, ano, resultGarPecasAnt.getModelo());
				    Iterator vlFaturIt = vlFatur.iterator();
			    	while(vlFaturIt.hasNext()){
			    		resultVlFatur = new RelatorioGarantiaPecasModSubRepVO();
			    		resultVlFatur = (RelatorioGarantiaPecasModSubRepVO)vlFaturIt.next();
			    		resultVlFatur.setM01(Double.valueOf(resultVlFatur.getM01().doubleValue() / dolarJan));	    		
			    		resultVlFatur.setM02(Double.valueOf(resultVlFatur.getM02().doubleValue() / dolarFev));		    		
			    		resultVlFatur.setM03(Double.valueOf(resultVlFatur.getM03().doubleValue() / dolarMar));                                                                                                        
			    		resultVlFatur.setM04(Double.valueOf(resultVlFatur.getM04().doubleValue() / dolarAbr));
			    		resultVlFatur.setM05(Double.valueOf(resultVlFatur.getM05().doubleValue() / dolarMai));
			    		resultVlFatur.setM06(Double.valueOf(resultVlFatur.getM06().doubleValue() / dolarJun));
			    		resultVlFatur.setM07(Double.valueOf(resultVlFatur.getM07().doubleValue() / dolarJul));
			    		resultVlFatur.setM08(Double.valueOf(resultVlFatur.getM08().doubleValue() / dolarAgo));
			    		resultVlFatur.setM09(Double.valueOf(resultVlFatur.getM09().doubleValue() / dolarSet));
			    		resultVlFatur.setM10(Double.valueOf(resultVlFatur.getM10().doubleValue() / dolarOut));
			    		resultVlFatur.setM11(Double.valueOf(resultVlFatur.getM11().doubleValue() / dolarNov));
			    		resultVlFatur.setM12(Double.valueOf(resultVlFatur.getM12().doubleValue() / dolarDez));
			    		
			    		//Armazena os valores do Faturamento
			    		vlFaturM01 =  resultVlFatur.getM01().doubleValue();  
			    		vlFaturM02 =  resultVlFatur.getM02().doubleValue();   
			    		vlFaturM03 =  resultVlFatur.getM03().doubleValue(); 
			    		vlFaturM04 =  resultVlFatur.getM04().doubleValue();     
			    		vlFaturM05 =  resultVlFatur.getM05().doubleValue();     
			    		vlFaturM06 =  resultVlFatur.getM06().doubleValue(); 
			    		vlFaturM07 =  resultVlFatur.getM07().doubleValue();     
			    		vlFaturM08 =  resultVlFatur.getM08().doubleValue();     
			    		vlFaturM09 =  resultVlFatur.getM09().doubleValue(); 
			    		vlFaturM10 =  resultVlFatur.getM10().doubleValue();     
			    		vlFaturM11 =  resultVlFatur.getM11().doubleValue();     
			    		vlFaturM12 =  resultVlFatur.getM12().doubleValue(); 

			    		resultGarPecasAnt.addListVlFatur(resultVlFatur);
			    	}
			    	
			    	//Adiciona o valor das qtdes de sg's, moto e faturada no relatorio
			    	List qtdes = relatorioDao.listRelatorioGPMQtdes(concessionaria, linha, ano, resultGarPecasAnt.getModelo());
				    Iterator qtdesIt = qtdes.iterator();
			    	while(qtdesIt.hasNext()){
			    		resultQtdes = new RelatorioGarantiaPecasModSubRepVO();
			    		resultQtdes = (RelatorioGarantiaPecasModSubRepVO)qtdesIt.next();
			    		resultGarPecasAnt.addListQtdes(resultQtdes);
			    	}
			    	
			    	//Adiciona o valor de Garantia/Faturamento no relatorio
			    	resultGarVlFatur = new RelatorioGarantiaPecasModSubRepVO();
			    	resultGarVlFatur.setAno(ano);
			    	resultGarVlFatur.setTopico("GAR/VL FATUR");
			    	resultGarVlFatur.setModelo(resultGarPecasAnt.getModelo());
			    	if (resultSubtotal.getM01().doubleValue() == 0 || vlFaturM01 == 0){
			    		resultGarVlFatur.setM01(new Double(0));
			    	}else{
			    		resultGarVlFatur.setM01(Double.valueOf((resultSubtotal.getM01().doubleValue() / vlFaturM01) * 100 ));
			    	}	    	
			    	
			    	if (resultSubtotal.getM02().doubleValue() == 0 || vlFaturM02 == 0){
			    		resultGarVlFatur.setM02(new Double(0));
			    	}else{
			    		resultGarVlFatur.setM02(Double.valueOf((resultSubtotal.getM02().doubleValue() / vlFaturM02) * 100 ));
			    	}
			    	
			    	if (resultSubtotal.getM03().doubleValue() == 0 || vlFaturM03 == 0){
			    		resultGarVlFatur.setM03(new Double(0));
			    	}else{
			    		resultGarVlFatur.setM03(Double.valueOf((resultSubtotal.getM03().doubleValue() / vlFaturM03) * 100 ));
			    	}
			    	
			    	if (resultSubtotal.getM04().doubleValue() == 0 || vlFaturM04 == 0){
			    		resultGarVlFatur.setM04(new Double(0));
			    	}else{
			    		resultGarVlFatur.setM04(Double.valueOf((resultSubtotal.getM04().doubleValue() / vlFaturM04) * 100 ));
			    	}
			    	
			    	if (resultSubtotal.getM05().doubleValue() == 0 || vlFaturM05 == 0){
			    		resultGarVlFatur.setM05(new Double(0));
			    	}else{
			    		resultGarVlFatur.setM05(Double.valueOf((resultSubtotal.getM05().doubleValue() / vlFaturM05) * 100 ));
			    	}	    	
			    	
			    	if (resultSubtotal.getM06().doubleValue() == 0 || vlFaturM06 == 0){
			    		resultGarVlFatur.setM06(new Double(0));
			    	}else{
			    		resultGarVlFatur.setM06(Double.valueOf((resultSubtotal.getM06().doubleValue() / vlFaturM06) * 100 ));
			    	}
			    	
			    	if (resultSubtotal.getM07().doubleValue() == 0 || vlFaturM07 == 0){
			    		resultGarVlFatur.setM07(new Double(0));
			    	}else{
			    		resultGarVlFatur.setM07(Double.valueOf((resultSubtotal.getM07().doubleValue() / vlFaturM07) * 100 ));
			    	}
			    	
			    	if (resultSubtotal.getM08().doubleValue() == 0 || vlFaturM08 == 0){
			    		resultGarVlFatur.setM08(new Double(0));
			    	}else{
			    		resultGarVlFatur.setM08(Double.valueOf((resultSubtotal.getM08().doubleValue() / vlFaturM08) * 100 ));
			    	}
			    	
			    	if (resultSubtotal.getM09().doubleValue() == 0 || vlFaturM09 == 0){
			    		resultGarVlFatur.setM09(new Double(0));
			    	}else{
			    		resultGarVlFatur.setM09(Double.valueOf((resultSubtotal.getM09().doubleValue() / vlFaturM09) * 100 ));
			    	}
			    	
			    	if (resultSubtotal.getM10().doubleValue() == 0 || vlFaturM10 == 0){
			    		resultGarVlFatur.setM10(new Double(0));
			    	}else{
			    		resultGarVlFatur.setM10(Double.valueOf((resultSubtotal.getM10().doubleValue() / vlFaturM10) * 100 ));
			    	}
			    	
			    	if (resultSubtotal.getM11().doubleValue() == 0 || vlFaturM11 == 0){
			    		resultGarVlFatur.setM11(new Double(0));
			    	}else{
			    		resultGarVlFatur.setM11(Double.valueOf((resultSubtotal.getM11().doubleValue() / vlFaturM11) * 100 ));
			    	}
			    	
			    	if (resultSubtotal.getM12().doubleValue() == 0 || vlFaturM12 == 0){
			    		resultGarVlFatur.setM12(new Double(0));
			    	}else{
			    		resultGarVlFatur.setM12(Double.valueOf((resultSubtotal.getM12().doubleValue() / vlFaturM12) * 100 ));
			    	}
			    	resultGarPecasAnt.addListGarVlFatur(resultGarVlFatur);
			    	
			    	
			    	//Adiciona o valor do Total Revisões/Faturamento
			    	resultRevVlFatur = new RelatorioGarantiaPecasModSubRepVO();
			    	resultRevVlFatur.setAno(ano);
			    	resultRevVlFatur.setTopico("REV/VL FATUR");
			    	resultRevVlFatur.setModelo(resultGarPecasAnt.getModelo());
			    	if (totRevM01 == 0 || vlFaturM01 == 0){
			    		resultRevVlFatur.setM01(new Double(0));
			    	}else{
			    		resultRevVlFatur.setM01(Double.valueOf((totRevM01 / vlFaturM01) * 100 ));
			    	}	    	
			    	
			    	if (vlFaturM02 == 0 || vlFaturM02 == 0){
			    		resultRevVlFatur.setM02(new Double(0));
			    	}else{
			    		resultRevVlFatur.setM02(Double.valueOf((totRevM02 / vlFaturM02) * 100 ));
			    	}
			    	
			    	if (totRevM03 == 0 || vlFaturM03 == 0){
			    		resultRevVlFatur.setM03(new Double(0));
			    	}else{
			    		resultRevVlFatur.setM03(Double.valueOf((totRevM03 / vlFaturM03) * 100 ));
			    	}
			    	
			    	if (totRevM04 == 0 || vlFaturM04 == 0){
			    		resultRevVlFatur.setM04(new Double(0));
			    	}else{
			    		resultRevVlFatur.setM04(Double.valueOf((totRevM04 / vlFaturM04) * 100 ));
			    	}
			    	
			    	if (totRevM05 == 0 || vlFaturM05 == 0){
			    		resultRevVlFatur.setM05(new Double(0));
			    	}else{
			    		resultRevVlFatur.setM05(Double.valueOf((totRevM05 / vlFaturM05) * 100 ));
			    	}	    	
			    	
			    	if (totRevM06 == 0 || vlFaturM06 == 0){
			    		resultRevVlFatur.setM06(new Double(0));
			    	}else{
			    		resultRevVlFatur.setM06(Double.valueOf((totRevM06 / vlFaturM06) * 100 ));
			    	}
			    	
			    	if (totRevM07 == 0 || vlFaturM07 == 0){
			    		resultRevVlFatur.setM07(new Double(0));
			    	}else{
			    		resultRevVlFatur.setM07(Double.valueOf((totRevM07 / vlFaturM07) * 100 ));
			    	}
			    	
			    	if (totRevM08 == 0 || vlFaturM08 == 0){
			    		resultRevVlFatur.setM08(new Double(0));
			    	}else{
			    		resultRevVlFatur.setM08(Double.valueOf((totRevM08 / vlFaturM08) * 100 ));
			    	}
			    	
			    	if (totRevM09 == 0 || vlFaturM09 == 0){
			    		resultRevVlFatur.setM09(new Double(0));
			    	}else{
			    		resultRevVlFatur.setM09(Double.valueOf((totRevM09 / vlFaturM09) * 100 ));
			    	}
			    	
			    	if (totRevM10 == 0 || vlFaturM10 == 0){
			    		resultRevVlFatur.setM10(new Double(0));
			    	}else{
			    		resultRevVlFatur.setM10(Double.valueOf((totRevM10 / vlFaturM10) * 100 ));
			    	}
			    	
			    	if (totRevM11 == 0 || vlFaturM11 == 0){
			    		resultRevVlFatur.setM11(new Double(0));
			    	}else{
			    		resultRevVlFatur.setM11(Double.valueOf((totRevM11 / vlFaturM11) * 100 ));
			    	}
			    	
			    	if (totRevM12 == 0 || vlFaturM12 == 0){
			    		resultRevVlFatur.setM12(new Double(0));
			    	}else{
			    		resultRevVlFatur.setM12(Double.valueOf((totRevM12 / vlFaturM12) * 100 ));
			    	}
			    	resultGarPecasAnt.addListRevVlFatur(resultRevVlFatur);

			    	
				    //Adiciona o Valor Total 
			    	resultTotVlFatur = new RelatorioGarantiaPecasModSubRepVO();
				    resultTotVlFatur.setAno(ano);
				    resultTotVlFatur.setTopico("TOT/VL FATUR");
				    resultTotVlFatur.setModelo(resultGarPecasAnt.getModelo());
				    if (resultTotal.getM01().doubleValue() == 0 || vlFaturM01 == 0){
				    	resultTotVlFatur.setM01(new Double(0));
				    }else{
				    	resultTotVlFatur.setM01(Double.valueOf((resultTotal.getM01().doubleValue() / vlFaturM01) * 100));
				    }
				    
				    if (resultTotal.getM02().doubleValue() == 0 || vlFaturM02 == 0){
				    	resultTotVlFatur.setM02(new Double(0));
				    }else{
				    	resultTotVlFatur.setM02(Double.valueOf((resultTotal.getM02().doubleValue() / vlFaturM02) * 100));
				    }
				    
				    if (resultTotal.getM03().doubleValue() == 0 || vlFaturM03 == 0){
				    	resultTotVlFatur.setM03(new Double(0));
				    }else{
				    	resultTotVlFatur.setM03(Double.valueOf((resultTotal.getM03().doubleValue() / vlFaturM03) * 100));
				    }

				    if (resultTotal.getM04().doubleValue() == 0 || vlFaturM04 == 0){
				    	resultTotVlFatur.setM04(new Double(0));
				    }else{
				    	resultTotVlFatur.setM04(Double.valueOf((resultTotal.getM04().doubleValue() / vlFaturM04) * 100));
				    }
				    
				    if (resultTotal.getM05().doubleValue() == 0 || vlFaturM05 == 0){
				    	resultTotVlFatur.setM05(new Double(0));
				    }else{
				    	resultTotVlFatur.setM05(Double.valueOf((resultTotal.getM05().doubleValue() / vlFaturM05) * 100));
				    }
				    
				    if (resultTotal.getM06().doubleValue() == 0 || vlFaturM06 == 0){
				    	resultTotVlFatur.setM06(new Double(0));
				    }else{
				    	resultTotVlFatur.setM06(Double.valueOf((resultTotal.getM06().doubleValue() / vlFaturM06) * 100));
				    }
				    
				    if (resultTotal.getM07().doubleValue() == 0 || vlFaturM07 == 0){
				    	resultTotVlFatur.setM07(new Double(0));
				    }else{
				    	resultTotVlFatur.setM07(Double.valueOf((resultTotal.getM07().doubleValue() / vlFaturM07) * 100));
				    }
				    
				    if (resultTotal.getM08().doubleValue() == 0 || vlFaturM08 == 0){
				    	resultTotVlFatur.setM08(new Double(0));
				    }else{
				    	resultTotVlFatur.setM08(Double.valueOf((resultTotal.getM08().doubleValue() / vlFaturM08) * 100));
				    }
				    
				    if (resultTotal.getM09().doubleValue() == 0 || vlFaturM09 == 0){
				    	resultTotVlFatur.setM09(new Double(0));
				    }else{
				    	resultTotVlFatur.setM09(Double.valueOf((resultTotal.getM09().doubleValue() / vlFaturM09) * 100));
				    }
				    
				    if (resultTotal.getM10().doubleValue() == 0 || vlFaturM10 == 0){
				    	resultTotVlFatur.setM10(new Double(0));
				    }else{
				    	resultTotVlFatur.setM10(Double.valueOf((resultTotal.getM10().doubleValue() / vlFaturM10) * 100));
				    }
				    
				    if (resultTotal.getM11().doubleValue() == 0 || vlFaturM11 == 0){
				    	resultTotVlFatur.setM11(new Double(0));
				    }else{
				    	resultTotVlFatur.setM11(Double.valueOf((resultTotal.getM11().doubleValue() / vlFaturM11) * 100));
				    }
				    
				    if (resultTotal.getM12().doubleValue() == 0 || vlFaturM12 == 0){
				    	resultTotVlFatur.setM12(new Double(0));
				    }else{
				    	resultTotVlFatur.setM12(Double.valueOf((resultTotal.getM12().doubleValue() / vlFaturM12) * 100));
				    }
				    resultGarPecasAnt.addListTotVlFatur(resultTotVlFatur);
				    results.add(resultGarPecasAnt);	
				    resultGarPecasAnt = resultGarPecasAtual;
				    
				    //Limpa as variáveis para usá-las no próximo modelo 
		    		vlMobraPcM01 = 0;     vlMobraPcM02 = 0;     vlMobraPcM03 = 0;
		    		vlMobraPcM04 = 0;     vlMobraPcM05 = 0;     vlMobraPcM06 = 0;
		    		vlMobraPcM07 = 0;     vlMobraPcM08 = 0;     vlMobraPcM09 = 0;
		    		vlMobraPcM10 = 0;     vlMobraPcM11 = 0;     vlMobraPcM12 = 0;

		    		vlMobraTerM01 = 0;    vlMobraTerM02 = 0;    vlMobraTerM03 = 0;
		    		vlMobraTerM04 = 0;    vlMobraTerM05 = 0;    vlMobraTerM06 = 0;
		    		vlMobraTerM07 = 0;    vlMobraTerM08 = 0;    vlMobraTerM09 = 0;
		    		vlMobraTerM10 = 0;    vlMobraTerM11 = 0;    vlMobraTerM12 = 0;

		    		vlGarM01 = 0;         vlGarM02 = 0;         vlGarM03 = 0;
		    		vlGarM04 = 0;         vlGarM05 = 0;         vlGarM06 = 0;
		    		vlGarM07 = 0;         vlGarM08 = 0;         vlGarM09 = 0;
		    		vlGarM10 = 0;         vlGarM11 = 0;         vlGarM12 = 0;

		    		totRevM01 = 0;        totRevM02 = 0;        totRevM03 = 0;
		    		totRevM04 = 0;        totRevM05 = 0;        totRevM06 = 0;
		    		totRevM07 = 0;        totRevM08 = 0;        totRevM09 = 0;
		    		totRevM10 = 0;        totRevM11 = 0;        totRevM12 = 0;

		    		vlFaturM01 = 0;       vlFaturM02 = 0;       vlFaturM03 = 0;
		    		vlFaturM04 = 0;       vlFaturM05 = 0;       vlFaturM06 = 0;
		    		vlFaturM07 = 0;       vlFaturM08 = 0;       vlFaturM09 = 0;
		    		vlFaturM10 = 0;       vlFaturM11 = 0;       vlFaturM12 = 0;	  
		    	}
		    } //fim while

		    return results;	
			
		} catch (DaoException dExp) {
			throw new BusinessException(dExp);
		}
	}	

	/** Lista todos os dados para o Relatório Imported Parts Claim List.
	 *  @return
	 *      Um objeto <code>List</code> contendo todos os 
	 *      dados para o Relatório Imported Parts Claim List.
	 *      
	 * @throws BusinessException
	 *      Se houverem problemas na camada de dados, uma BusinessException
	 *      será lançada. Caso hajam problemas de regras de negócio, uma
	 *      BusinessRuleException (subclasse de BusinessException) será 
	 *      lançada.
	 */
	public List listImportedParts ( Date   dataIni
						          , Date   dataFim
						          , Long   linha
						          , Long   empresa
						          , String fornecedor) throws BusinessException {

		try {
			
			System.out.println(	"Empresa:" 	  + empresa		);
			System.out.println(	"Linha:" 	  + linha		);
			System.out.println(	"Fornecedor:" + fornecedor	);
			System.out.println(	"DataIni:" 	  + dataIni		);
			System.out.println(	"DataFim:"    + dataFim		);
			
			List results       = new ArrayList();
			List importedParts = relatorioDao.listRelatorioImportedParts(dataIni
					                                                    , dataFim
					                                                    , linha
					                                                    , empresa
					                                                    , fornecedor);
			List invoice1 = relatorioDao.listRelatorioImportedPartsInvoice1();
			
			RelatorioImportedPartsVO         currentImported = new RelatorioImportedPartsVO();
			RelatorioImportedPartsWarrantyVO currentWar      = null;
			RelatorioImportedPartsInvoiceVO  currentInv      = null;
			
			// Armazena informações do banco
			String banco      = null;
			String agencia    = null;
			String conta      = null;
			String nomeBanco  = null;
		    String endereco   = null;
		    String localidade = null;
		    String numConta   = null;
		    String numAg      = null;
		    
		    //Armazena informações da moeda
            Double comissao   = null;
            Double vlDolar    = null;
			
		    // Armazena informações da empresa
		    String nomeEmp    = null;
		    String endEmp     = null;
		    String cnpjEmp    = null; 
		    String incEstEmp  = null;
		    
		    //Armazena informações do fornecedor
		    String nomeFornec = null;
		    String endFornec  = null;
		    String cidFornec  = null;
		    String paisFornec = null;
		    
		    String antFornecedor = "";
		    
			//Carrega os parametros do banco para a lista de Invoices
			Iterator invoiceIt = invoice1.iterator();
			while (invoiceIt.hasNext()){
				currentInv = new RelatorioImportedPartsInvoiceVO();
				currentInv = (RelatorioImportedPartsInvoiceVO)invoiceIt.next();
				
				if (currentInv.getParametro().equals("INVOICE BANCO")){
					banco = currentInv.getValor();
				}
				
				if (currentInv.getParametro().equals("INVOICE AGENCIA")){
					agencia = currentInv.getValor();
				}
				
				if (currentInv.getParametro().equals("INVOICE ACCOUNT")){
					conta = currentInv.getValor();
				}
			}
			
			//Carrega os dados do banco na lista de dados de Invoices
			List invoices2 = relatorioDao.listRelatorioImportedPartsInvoice2(agencia, banco, conta);
			Iterator invoice2It = invoices2.iterator();
			while (invoice2It.hasNext()){
				currentInv = new RelatorioImportedPartsInvoiceVO();
				currentInv = (RelatorioImportedPartsInvoiceVO)invoice2It.next();

				nomeBanco  = currentInv.getBanco();
				endereco   = currentInv.getEndereco(); 
				localidade = currentInv.getLocalidade();
				numConta   = currentInv.getConta();
				numAg      = currentInv.getAgencia();				
			}
			
			//Carrega os dados da moeda na lista de dados de Invoices
			List invoices3 = relatorioDao.listRelatorioImportedPartsInvoice3(DateUtils.applyMask(dataFim, "yyyy"), DateUtils.applyMask(dataFim, "MM"));
			Iterator invoice3It = invoices3.iterator();
            
            if ( invoices3 == null || invoices3.size() == 0 ) {

                // Lançar exception dizendo que a cotação não foi encontrada.
                throw new CotacaoException("Não foi possível encontrar os dados da cotação.");
            }
            
			while (invoice3It.hasNext()){
				currentInv = new RelatorioImportedPartsInvoiceVO();
				currentInv = (RelatorioImportedPartsInvoiceVO)invoice3It.next();
				
				comissao = currentInv.getComissao();
				vlDolar  = currentInv.getVlDolar();
			}
			
			//Carrega os dados da empresa na lista de dados de Invoices
			List invoices4 = relatorioDao.listRelatorioImportedPartsInvoice4(empresa);
			Iterator invoice4It = invoices4.iterator();
			while (invoice4It.hasNext()){
				currentInv = new RelatorioImportedPartsInvoiceVO();
				currentInv = (RelatorioImportedPartsInvoiceVO)invoice4It.next();
				
			    nomeEmp   = currentInv.getEmpresa();
			    endEmp    = currentInv.getEndEmp();
			    cnpjEmp   = currentInv.getCnpjEmp(); 
			    incEstEmp = currentInv.getIncEstEmp();
			}	

			//Percorre a lista do relatório principal
			Iterator importedIt = importedParts.iterator();
			while (importedIt.hasNext()){
				currentImported = (RelatorioImportedPartsVO) importedIt.next();
				
				//Carrega a lista de dados das Warranties
				currentWar = new RelatorioImportedPartsWarrantyVO();
				currentWar.setModelo(currentImported.getModelo());
				currentWar.setEngine(currentImported.getEngine());
				currentWar.setLaborTime(currentImported.getLaborTime());
				currentWar.setPartsFobPrice(currentImported.getPartsFobPrice());
				currentWar.setFornecedor(currentImported.getFornecedor());
				currentWar.setComissao(comissao);
				currentWar.setVlDolar(vlDolar);
				currentImported.addListWarranty(currentWar);
				
				//Carregar os dados do fornecedor somente quando mudar o fornecedor
				if (currentImported.getFornecedor().equals(antFornecedor) == false || antFornecedor.equals("")){
					
					antFornecedor = currentImported.getFornecedor();
					
					//Carrega os dados da empresa na lista de dados de Invoices
					List invoices5 = relatorioDao.listRelatorioImportedPartsInvoice5(currentImported.getFornecedor());
					Iterator invoice5It = invoices5.iterator();
					while (invoice5It.hasNext()){
						currentInv = new RelatorioImportedPartsInvoiceVO();
						currentInv = (RelatorioImportedPartsInvoiceVO)invoice5It.next();
						
					    nomeFornec = currentInv.getFornecedor();
					    endFornec  = currentInv.getEndFornec();
					    cidFornec  = currentInv.getCidFornec();
					    paisFornec = currentInv.getPaisFornec();
					}	
					
					// Atribui valores para a lista de dados de invoice. 
					currentInv = new RelatorioImportedPartsInvoiceVO();
					currentInv.setBanco(nomeBanco);
					currentInv.setAgencia(numAg);
					currentInv.setConta(numConta);
					currentInv.setEndereco(endereco);
					currentInv.setLocalidade(localidade);
					currentInv.setComissao(comissao);
					currentInv.setVlDolar(vlDolar);
					currentInv.setEmpresa(nomeEmp);
					currentInv.setEndEmp(endEmp);
					currentInv.setCnpjEmp(cnpjEmp);
					currentInv.setIncEstEmp(incEstEmp);
					currentInv.setFornecedor(nomeFornec);
					currentInv.setEndFornec(endFornec);
					currentInv.setCidFornec(cidFornec);
					currentInv.setPaisFornec(paisFornec);	
					currentImported.addListInvoice(currentInv);
				}
				results.add(currentImported);
			}

			return results;
			
		} catch (DaoException dExp) {
			throw new BusinessException(dExp);
		}
	}
	
	/** Lista os dados de Faturamento para o Relatório Gráfico de Faturamento x Garantia - Gráficos Individuais - Gráfico 1.
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
	 *  @param  linhaId - Id da Linha do produto
	 *  	<code>Long</code> Id da Linha do produto  
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
	public List listValuesFaturamentoByGarantia( Long orgId, Date dataInicioAp, Date dataFinalAp, Long linhaId, String modelo ) throws BusinessException {
		
		try {
			
			//List results = this.getRelatorioDao().listFaturamentoByGarantia(orgId, dataInicioAp, dataFinalAp );
			List results = this.getRelatorioDao().listFaturamentoByGarantiaANDLinha(orgId, dataInicioAp, dataFinalAp, linhaId, modelo );
			
			return results;	
		
		} catch (DaoException dExp) {
			throw new BusinessException(dExp);
		}
	}
	
	/** Lista os dados de Faturamento para o Relatório Gráfico de Faturamento x Garantia - Gráficos Individuais por Linha- Gráfico 1.
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
	 *  @param  modelo - Modelo
	 *  	<code>String</code> Modelo do produto  
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
	public List listValuesGraphFaturamentoByGarantiaANDLinha( Long orgId, Date dataInicioAp, Date dataFinalAp, String modelo ) throws BusinessException {
		
		try {
			
			List results = this.getRelatorioDao().listGraphFaturamentoByGarantiaANDLinha(orgId, dataInicioAp, dataFinalAp, modelo );
			
			return results;	
		
		} catch (DaoException dExp) {
			throw new BusinessException(dExp);
		}
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
	 *  @param  linhaId - Id da Linha do produto
	 *  	<code>Long</code> Id da Linha do produto  
	 *  
	 *  @param  modelo - Modelo
	 *  	<code>String</code> Modelo do produto  
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
	public List listFaturamentoByQtdeGarantia ( Long orgId, Date dataInicioAp, Date dataFinalAp, Long linhaId, String modelo ) throws BusinessException {
		
		try {
			
			List results = this.getRelatorioDao().listFaturamentoByQtdeGarantia(orgId, dataInicioAp, dataFinalAp, linhaId, modelo );
			
			return results;	
		
		} catch (DaoException dExp) {
			throw new BusinessException(dExp);
		}
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
	public List listAcumuladoGarantiaByIndice ( Long orgId, Date dataInicioAp, Date dataFinalAp, Long linhaId, String modelo ) throws BusinessException {
		
		try {
			
			List results = this.getRelatorioDao().listAcumuladoGarantiaByIndice(orgId, dataInicioAp, dataFinalAp, linhaId, modelo );
			
			return results;	
		
		} catch (DaoException dExp) {
			throw new BusinessException(dExp);
		}
	}
	
	/** Retorna os parâmetros para o Relatório de Gráficos Individuais e por linha - Gráfico 6 (Tabela).
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
	 *  @param  linhaId - Id da Linha do produto
	 *  	<code>Long</code> Id da Linha do produto
	 *  
	 *  @param  modelo - Modelo
	 *  	<code>String</code> Modelo do produto. Quando nulo a lista é gerada por modelo
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
	public Map listValuesTablePecas ( Long orgId, Date dataFinalAp, Long linhaId, String modelo ) throws BusinessException {
		
		try {
			
			Map parameters = new HashMap();
			
			List results = null;
			
			results = this.getRelatorioDao().listValuesTablePeca( orgId, dataFinalAp, linhaId, modelo);
			
			System.out.println(" -listValuesTablePecas - Results: " + results.size());
			
			Iterator it = results.iterator();
			RelatorioGraficosIndividuaisTableVO graficosIndividuaisTable = null;
			double valorParcial = 0;
			double valorTotal   = 0;
			
			// Devemos apresentar apenas as 4 primeiras peças, mas
			// temos que percorrer toda a coleção para fazer a somatória dos valores
			for (int i = 1 ; it.hasNext() ; i++ ) {
				
				graficosIndividuaisTable = (RelatorioGraficosIndividuaisTableVO)it.next();
				
				//System.out.println("Peça:" + graficosIndividuaisTable.getDescricao() + " - i :" + i );
				
				// Setamos os parâmetros para os 4 primeiros registros
				if ( i < 5 ) {
					
					switch ( i ) {
					
					case 1 :
						parameters.put(RelatorioGraficosIndividuaisTableVO.DESCRICAO_PECA1 , ( graficosIndividuaisTable.getItemCode()+ " - " + graficosIndividuaisTable.getDescricao() ));
						parameters.put(RelatorioGraficosIndividuaisTableVO.QUANTIDADE_PECA1, graficosIndividuaisTable.getQuantidadeItem().toString());
						parameters.put(RelatorioGraficosIndividuaisTableVO.VALOR_PECA1     , NumberUtils.formatNumberCurrencyMil(graficosIndividuaisTable.getValorGarantia().doubleValue()));
						break;
					case 2 :
						parameters.put(RelatorioGraficosIndividuaisTableVO.DESCRICAO_PECA2 , ( graficosIndividuaisTable.getItemCode()+ " - " + graficosIndividuaisTable.getDescricao() ));
						parameters.put(RelatorioGraficosIndividuaisTableVO.QUANTIDADE_PECA2, graficosIndividuaisTable.getQuantidadeItem().toString());
						parameters.put(RelatorioGraficosIndividuaisTableVO.VALOR_PECA2     , NumberUtils.formatNumberCurrencyMil(graficosIndividuaisTable.getValorGarantia().doubleValue()));
						break;
					case 3 :
						parameters.put(RelatorioGraficosIndividuaisTableVO.DESCRICAO_PECA3 , ( graficosIndividuaisTable.getItemCode()+ " - " + graficosIndividuaisTable.getDescricao() ));
						parameters.put(RelatorioGraficosIndividuaisTableVO.QUANTIDADE_PECA3, graficosIndividuaisTable.getQuantidadeItem().toString());
						parameters.put(RelatorioGraficosIndividuaisTableVO.VALOR_PECA3     , NumberUtils.formatNumberCurrencyMil(graficosIndividuaisTable.getValorGarantia().doubleValue()));
						break;
					case 4 :
						parameters.put(RelatorioGraficosIndividuaisTableVO.DESCRICAO_PECA4 , ( graficosIndividuaisTable.getItemCode()+ " - " + graficosIndividuaisTable.getDescricao() ));
						parameters.put(RelatorioGraficosIndividuaisTableVO.QUANTIDADE_PECA4, graficosIndividuaisTable.getQuantidadeItem().toString());
						parameters.put(RelatorioGraficosIndividuaisTableVO.VALOR_PECA4     , NumberUtils.formatNumberCurrencyMil(graficosIndividuaisTable.getValorGarantia().doubleValue()));
											
					}
					
					valorParcial += graficosIndividuaisTable.getValorGarantia().doubleValue();
				}
				
				valorTotal += graficosIndividuaisTable.getValorGarantia().doubleValue();
				
			}
			
			//System.out.println("Valor Parcial:" + valorParcial);
			//System.out.println("Valor Total:" 	+ valorTotal);
			
			if ( valorParcial != 0 && valorTotal != 0 ) {
			
				// Calculamos o percentual 
				double percentual = (valorParcial * 100 ) / valorTotal; 
				String pecentualStr = String.valueOf(NumberUtils.formatNumberCurrencyMil(NumberUtils.round(percentual,2))) + " %";
				parameters.put(RelatorioGraficosIndividuaisTableVO.PERCENTUAL_CORRESP, pecentualStr);
				
				// Setamos o valor Parcial
				String parcialStr = NumberUtils.formatNumberCurrencyMil(valorParcial);
				parameters.put(RelatorioGraficosIndividuaisTableVO.VALOR_TOTAL_PARCIAL_PECA, parcialStr);
				
				// Setamos o valor Total
				String totalStr =  NumberUtils.formatNumberCurrencyMil(valorTotal);
				parameters.put(RelatorioGraficosIndividuaisTableVO.VALOR_TOTAL_PECA, totalStr);
			
			} else {
				
				parameters.put(RelatorioGraficosIndividuaisTableVO.PERCENTUAL_CORRESP, "0 %");
				parameters.put(RelatorioGraficosIndividuaisTableVO.VALOR_TOTAL_PARCIAL_PECA, "0,0");
				parameters.put(RelatorioGraficosIndividuaisTableVO.VALOR_TOTAL_PECA, "0,0");
				
			}
			
			parameters.put(RelatorioGraficosIndividuaisTableVO.MES_APURADO, DateUtils.applyMask(dataFinalAp, "MMMM/yyyy"));
			
			return parameters;	
		
		} catch (DaoException dExp) {
			throw new BusinessException(dExp);
		}
	}
	
	/** Lista os dados para os gráficos do relatório Service Report. 
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
	public List listValuesServiceReportParte ( Long orgId, Date dataAp, int part ) throws BusinessException {
		
		try {
			
			List results = this.getRelatorioDao().listValuesServiceReportParte(orgId, dataAp, part );
			
			return results;	
		
		} catch (DaoException dExp) {
			throw new BusinessException(dExp);
		}
	}
	
	/** Lista os dados de Garantia Paga por Modelo para o Relatório Gráfico - Garantia Paga por Modelo de Produto.
	 *  
	 *      
	 *  @param  orgId - Id da Organização
	 *  	<code>Long</code> Número que representa o id da Organização
	 *  
	 *   *  @param  grupoModelo - Grupo do modelo
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
	public List listGarantiaPagaByModelo ( Long orgId, String grupoModelo, Date dataInicioAp, Date dataFinalAp, Long linhaId ) throws BusinessException {
		
		EntityImgReportGarantiaPagaVO graphVO = null;
		List resultGraph = new ArrayList();
		
		try {
			
			// Gráfico de Pagamentos Acumulados 
			List results = this.getRelatorioDao().listGarantiaPagaByModelo(orgId, grupoModelo, dataInicioAp, dataFinalAp, EntityGraficoGarantiaPagaVO.ACUMULADO, linhaId);
			
			BufferedImage bImageGraph = GenerateChartReport.gerarGraficoGarantiaPaga(	results
																						, "Valores Pagos em Garantia no Período de " + DateUtils.applyMask(dataInicioAp) +
																						  " até " + DateUtils.applyMask(dataFinalAp)
																					   );
			graphVO = new EntityImgReportGarantiaPagaVO();
	    	
	    	graphVO.setTitulo("Valores pagos em garantia acumulado(R$)");
	    	graphVO.setImage(bImageGraph);
	    	resultGraph.add(graphVO);
	    	
	    	//	 Gráfico de Pagamentos do Período 
			results = this.getRelatorioDao().listGarantiaPagaByModelo(orgId, grupoModelo, dataInicioAp, dataFinalAp, EntityGraficoGarantiaPagaVO.PERIODO, linhaId );
			
			bImageGraph = GenerateChartReport.gerarGraficoGarantiaPaga(	results
																		, "Valores Pagos em Garantia nos 12 meses anterior a " + DateUtils.applyMask(dataFinalAp)
																	   );
			graphVO = new EntityImgReportGarantiaPagaVO();
	    	
	    	graphVO.setTitulo("Valores pagos em garantia acumulado(R$)");
	    	graphVO.setImage(bImageGraph);
	    	resultGraph.add(graphVO);
	    	
	    	//	 Gráfico de Pagamentos por Quantidade
			results = this.getRelatorioDao().listGarantiaPagaByModelo(orgId, grupoModelo, dataInicioAp, dataFinalAp, EntityGraficoGarantiaPagaVO.QTDE, linhaId );
			
			bImageGraph = GenerateChartReport.gerarGraficoGarantiaPaga(	results
																		, "Quantidade de Garantias Pagas nos 12 meses anterior a " + DateUtils.applyMask(dataFinalAp)
																	   );
			graphVO = new EntityImgReportGarantiaPagaVO();
	    	
	    	graphVO.setTitulo("Quantidade de Garantias Pagas (Qtde)");
	    	graphVO.setImage(bImageGraph);
	    	resultGraph.add(graphVO);
	    	
	    	
			return resultGraph;	
		
		} catch (DaoException dExp) {
			throw new BusinessException(dExp);
		} catch (ServiceException sExp) {
			throw new BusinessException( sExp );  
        
        }      
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
	public List listValuesGraphGarantiaMensalPercentual ( Long orgId, String grupoModelo, Date dataInicioAp, Date dataFinalAp, Long linhaId ) throws BusinessException {
			
		EntityImgReportVO entityImgReportVO = null;
		List resultGraph = new ArrayList();
		try {
			
			List results = this.getRelatorioDao().listValuesGraphGarantiaMensalPercentual(orgId, grupoModelo, dataInicioAp, dataFinalAp, linhaId);
			
			BufferedImage bImageGraph = GenerateChartReport.gerarGraficoGarantiaMensalStackedBar(
											""											
											, "MÊS"
											, "%"
											, results
											);
			entityImgReportVO = new EntityImgReportVO();

			entityImgReportVO.setTitulo("GARANTIA MENSAL (%)");
			entityImgReportVO.setImage(bImageGraph);
			resultGraph.add(entityImgReportVO);
						
			return resultGraph;	
		
		} catch (DaoException dExp) {
			throw new BusinessException(dExp);
		} catch (ServiceException sExp) {
			throw new BusinessException( sExp );  
        
        }    
	}
		
	/** Lista os dados para o Relatório Gerenciamento Mês. 
	 *  
	 *  @param  concessionariaId  
	 *  	<code>Long</code> Id ad Concessionária
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
	 *      Um objeto <code>List</code> 
	 *      
	 * @throws DaoException
	 */
	public List listValuesGerenciamentoMes ( Date dataInicioAp, Date dataFinalAp, Long linhaId, Long concessionariaId ) throws BusinessException {
			
		try {
			
			return this.getRelatorioDao().listValuesGerenciamentoMes(dataInicioAp, dataFinalAp, linhaId, concessionariaId);
					
		} catch (DaoException dExp) {
			throw new BusinessException(dExp);
		} 
		
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
	public int getQtLotesLiberadosGerenciamentoMes ( Date dataInicioAp, Date dataFinalAp, Long linhaId, Long concessionariaId ) throws BusinessException {
				
		try {
			
			return this.getRelatorioDao().getQtLotesLiberadosGerenciamentoMes(dataInicioAp, dataFinalAp, linhaId, concessionariaId);
					
		} catch (DaoException dExp) {
			throw new BusinessException(dExp);
		} 
		
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
	public int getQtLotesDevolvidosGerenciamentoMes ( Date dataInicioAp, Date dataFinalAp, Long linhaId, Long concessionariaId ) throws BusinessException {
				
		try {
			
			return this.getRelatorioDao().getQtLotesDevolvidosGerenciamentoMes(dataInicioAp, dataFinalAp, linhaId, concessionariaId);
					
		} catch (DaoException dExp) {
			throw new BusinessException(dExp);
		} 
		
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
	public int getMediaAberturaFechamentoLote ( Date dataInicioAp, Date dataFinalAp, Long linhaId, Long concessionariaId ) throws BusinessException {
		
		try {
			
			return this.getRelatorioDao().getMediaAberturaFechamentoLote(dataInicioAp, dataFinalAp, linhaId, concessionariaId);
					
		} catch (DaoException dExp) {
			throw new BusinessException(dExp);
		}
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
	public String processa ( String texto )throws BusinessException {
		try {
			
			return this.getRelatorioDao().processa(texto);
			
		} catch (DaoException dExp) {
			throw new BusinessException(dExp);
		}
	}
	/** Método getter para a propriedade relatorioDao.
	 *
	 *  @return o valor atual de relatorioDao.
	 */
	public RelatorioDao getRelatorioDao() {
		return relatorioDao;
	}

	/** Obtém o valor atual de relatorioDao.
	 * 
	 *  @param relatorioDao 
	 *    O novo valor para relatorioDao.
	 */
	public void setRelatorioDao(RelatorioDao relatorioDao) {
		this.relatorioDao = relatorioDao;
	}	

}
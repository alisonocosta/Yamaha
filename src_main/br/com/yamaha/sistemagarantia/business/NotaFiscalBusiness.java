/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......NotaFiscalBusiness.java
 *   .: Cria��o.....13 de junho, 22:51
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Objeto de neg�cios para a entidade "NotaFiscal".
 */
package br.com.yamaha.sistemagarantia.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import br.com.resource.infra.business.BusinessObject;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.DaoException;
import br.com.resource.infra.utils.DateUtils;
import br.com.yamaha.sistemagarantia.dao.NotaFiscalDao;
import br.com.yamaha.sistemagarantia.model.AlertCupom;
import br.com.yamaha.sistemagarantia.model.Concessionaria;
import br.com.yamaha.sistemagarantia.model.Cupom;
import br.com.yamaha.sistemagarantia.model.Faturamento;
import br.com.yamaha.sistemagarantia.model.GarantiaHeader;
import br.com.yamaha.sistemagarantia.model.Lote;
import br.com.yamaha.sistemagarantia.model.NotaFiscal;
import br.com.yamaha.sistemagarantia.model.ParametroSistema;
import br.com.yamaha.sistemagarantia.model.Serie;
import br.com.yamaha.sistemagarantia.model.StatusGarantia;
import br.com.yamaha.sistemagarantia.model.StatusLote;
import br.com.yamaha.sistemagarantia.model.TipoLote;
import br.com.yamaha.sistemagarantia.model.to.AlertEntityTO;
import br.com.yamaha.sistemagarantia.model.to.NotaFiscalLancamentoTO;
import br.com.yamaha.sistemagarantia.view.GarantiaVO;
import br.com.yamaha.sistemagarantia.view.LoteCompactVO;
import br.com.yamaha.sistemagarantia.view.NotaFiscalVO;

/** Classe de neg�cios relacionada � entidade <b>NotaFiscal</b>.
 * 
 *  @author Edson Luiz Sumensari
 */
public class NotaFiscalBusiness extends BusinessObject {

    /** Objeto DAO para utilizado para este objeto de neg�cios. 
     */
    private NotaFiscalDao notaFiscalDao;
    
    private LoteBusiness loteBo;
    
    private CupomBusiness cupomBo;
    
    /**
	 *  Objeto transactionTemplate para controle de transa��o
	 */
	private TransactionTemplate transactionTemplate;
    
    /** Lista de Lotes e itens de Cupom e Garantia para lan�amento de notas da empresa YMA
     * 
     * @param concessionaria
     * 	<code>Concessionaria</code> Entidade de concession�ria
     * 
     * @return
     *      Um objeto <code>List</code> contendo todos os itens.      
     * 
     * @throws BusinessException
     *      Se houverem erros de execu��o ou nas camadas 
     *      abaixo dos servi�os, ser�o encapsulados neste
     *      tipo de <code>Exception</code>.
     */
    public List listItens( Concessionaria concessionaria ) throws BusinessException {
    	
    	try {
    		
	    	List lotes    = null;
	    	List status   = null;
	    	List itens    = new ArrayList();
	    	
			// Vamos preparar os status dos lotes para recuperar
			// O primeiro � "EM DIGITA��O"
			status = new ArrayList();
			StatusLote statusLote = new StatusLote();
			statusLote.setEntityId(StatusLote.STATUS_EM_DIGITACAO);    		
			status.add(statusLote);
			
			// O segundo � "EM DIGITA��O"
			statusLote = new StatusLote();
			statusLote.setEntityId(StatusLote.STATUS_NF_DIVERGENTE);    		
			status.add(statusLote);
			
			// O terceiro � "PARCIAMENTE PAGO"
			statusLote = new StatusLote();
			statusLote.setEntityId(StatusLote.STATUS_PARCIALMENTE_PAGO);
			status.add(statusLote);
			
			// O terceiro � "AGUARDANDO DIG. DE NOTAS"
			statusLote = new StatusLote();
			statusLote.setEntityId(StatusLote.STATUS_AGUARDANDO_DIGITACAO_NOTA);
			status.add(statusLote);
			
			//lotes = loteBo.listByStatus(status, concessionaria);
			lotes = loteBo.listLotes(status, concessionaria);
			
			// Agora pegamos os itens e montamos os objetos VO�s
			Iterator     it           = lotes.iterator();
			Iterator     itItens      = null;
			NotaFiscalVO notaFiscalVO = null;
			Cupom 		 cupom 		  = null;
			while ( it.hasNext() ) {
				
				LoteCompactVO lote = (LoteCompactVO) it.next();	
				
				//System.out.println("--> listItens - Tipo Lote:" + lote.getTipoLote().getEntityId());
				
				// Verificamos se o lote � de revis�o ou garantia
				if ( lote.getTipoId().equals(TipoLote.TIPO_REVISAO) ) {
					
					itItens = cupomBo.listCuponsByLote(lote.getLoteId()).iterator();
					//itItens = lote.getCupons().iterator();
					
					//System.out.println("--> listItens - Revis�o - Itens do Lote: " + lote.getEntityId() + " - " + lote.getCupons().size());
					
					// Quando for revis�o, percorremos os cupons e pegamos os itens com
					// os itens com status "NF DIVERGENTE" e "AGUARDANDO DIGITA��O DE NOTA".
					while ( itItens.hasNext() ) {
						
						cupom = (Cupom) itItens.next();
						
						//System.out.println("Cupom:" + cupom.getEntityId() + " - StatusMovId:" + cupom.getStatusMovId() );
						
						if ( 	(cupom.getStatusMovId().equals(StatusGarantia.STATUS_AGUARDANDO_DIGITACAO_NOTA)
							 || cupom.getStatusMovId().equals(StatusGarantia.STATUS_NF_DIVERGENTE))
							 && cupom.getDataTermino()== null	// esse filtro est� aqui apenas porque o hibernate n�o est� filtrando							
							) {	
							
							// Quando j� existir nota lan�ada desconsideramos
							if( !notaFiscalDao.hasNotaByCupom((Long)cupom.getEntityId())){							
							
								//Faturamento faturamento =(Faturamento)notaFiscalDao.getByParameter(Faturamento.class, "chassi", cupom.getChassi());
								Faturamento faturamento =(Faturamento)notaFiscalDao.getFaturamentoByChassi(cupom.getChassi());								
								
								if ( faturamento != null ) {	
									
									notaFiscalVO = new NotaFiscalVO();
									notaFiscalVO.setEntityId(cupom.getEntityId());	
									notaFiscalVO.setNumeroCupom(cupom.getCupomCode());
									notaFiscalVO.setDestinatario(faturamento.getEmpresa().getOrgCode());
									notaFiscalVO.setDescricaoStatus(lote.getDescricaoReduzidaStatus());
									notaFiscalVO.setLoteId(lote.getLoteId());
									notaFiscalVO.setStatusLoteId((Long)lote.getStatusId());
									notaFiscalVO.setTipoLote(lote.getDescricaoTipoLote());
									notaFiscalVO.setTipoLoteId((Long)lote.getTipoId());
									// Valor de Servi�o = valor da revis�o  + valor da Bonifica��o
									notaFiscalVO.setValorMaoObra(cupom.getValorRevisao() + cupom.getValorBonificacao()); 
									// Para revis�o o valor de pe�as ser� sempre Zero
									notaFiscalVO.setValorPeca(0L);
									//Para revis�o sempre est� flag deve ser verdadeira
									notaFiscalVO.setHasDtFechamento(true);
									itens.add(notaFiscalVO);
									
									//System.out.println("Cupom Valores:" + cupom.getValorRevisao() + " - Bonif." + cupom.getValorBonificacao());
								} else {
									
									//System.out.println("Alerta para o chassi:" + cupom.getChassi());
									// Quando ocorrer um problema na carga do faturamento geramos apenas um alerta								
									AlertCupom alert = new AlertCupom();
									alert.setAlertCupomSeveridade(AlertCupom.SEVERIDADE_ERROR);
									alert.setAlertCupomText("N�o foi localizado o Faturamento para o Chassi:" + cupom.getChassi());
									alert.setAlertCupomKey("notaFiscal.form.msg.error.faturamento.notFound");
									notaFiscalVO.setAlert(alert);									
								}
							}
						}	
					}
					
				} else  if ( lote.getTipoId().equals(TipoLote.TIPO_GARANTIA) ) {
					
					//itItens = lote.getGarantias().iterator();
					
					itItens = loteBo.listGaranatiasToNF(lote.getLoteId()).iterator();
					
					//System.out.println("--> listItens - Garantia - Itens do Lote: " + lote.getEntityId() + " - " + lote.getGarantias().size());
					
					GarantiaVO garantiaVO = null;
					boolean hasDtFechamento = false;
					// Quando for garantia, percorremos  lista de garantias e pegamos os itens com
					// statusMovId "AGUARDANDO DIGITA��O DE NOTAS" e "NF DIVERGENTE".
					while ( itItens.hasNext() ) {
						
						//GarantiaHeader garantia = (GarantiaHeader) itItens.next();
						garantiaVO = (GarantiaVO) itItens.next();;
						//System.out.println("Garantia:" + garantia.getEntityId() + " - Chassi:" + garantia.getModelo() +" - StatusMovId:" + garantia.getStatusGarantia().getEntityId() );

						//if ( 	(garantia.getStatusGarantia().getEntityId().equals(StatusGarantia.STATUS_AGUARDANDO_DIGITACAO_NOTA)
							// || garantia.getStatusGarantia().getEntityId().equals(StatusGarantia.STATUS_NF_DIVERGENTE) )
							// && garantia.getDataTermino() == null	// esse filtro est� aqui apenas porque o hibernate n�o est� filtrando					
							//) {
							
							//Faturamento faturamento =(Faturamento)notaFiscalDao.getByParameter(Faturamento.class, "chassi", garantia.getModelo());
							Faturamento faturamento =(Faturamento)notaFiscalDao.getFaturamentoByChassi(garantiaVO.getModelo());
							notaFiscalVO = new NotaFiscalVO();
							
							if ( faturamento != null ) {								
								
								notaFiscalVO.setEntityId(garantiaVO.getGarantiaId());
								notaFiscalVO.setDestinatario(faturamento.getEmpresa().getOrgCode());								
								notaFiscalVO.setDescricaoStatus(lote.getDescricaoReduzidaStatus());
								notaFiscalVO.setLoteId(lote.getLoteId());
								notaFiscalVO.setStatusLoteId((Long)lote.getStatusId());
								notaFiscalVO.setTipoLote(lote.getDescricaoTipoLote());
								notaFiscalVO.setTipoLoteId((Long)lote.getTipoId());							
								//notaFiscalVO.setValorMaoObra(garantia.getValorTotalServicos());
								notaFiscalVO.setValorMaoObra(garantiaVO.getValorServico());
								//notaFiscalVO.setValorPeca(garantia.getValorTotalPecasCobrar());
								notaFiscalVO.setValorPeca(garantiaVO.getValorPeca());
								//notaFiscalVO.setValorMaoObraTerc(garantia.getValorServicoTerceiro());
								notaFiscalVO.setValorMaoObraTerc(garantiaVO.getValorServicoTerceiro());
								notaFiscalVO.setDtAberturaOS(garantiaVO.getDataAberturaOS());
								//System.out.println("Garantia Valores:" + garantia.getValorTotalServicos() + " - Pe�as." + garantia.getValorTotalPecasCobrar());								
								//System.out.println("Garantia Valor Serv. Terc.:" + garantia.getValorServicoTerceiro());
								
								//inclu�do em 08/2013 - ist_edson
								hasDtFechamento = garantiaVO.getDataFechamentoOS() != null ? true : false;
								notaFiscalVO.setHasDtFechamento(hasDtFechamento);
								
							} else {
								
								//System.out.println("Alerta para o chassi:" + garantia.getModelo());
								// Quando ocorrer um problema na carga do faturamento geramos apenas um alerta								
								AlertCupom alert = new AlertCupom();
								alert.setAlertCupomSeveridade(AlertCupom.SEVERIDADE_ERROR);
								alert.setAlertCupomText("N�o foi localizado o Faturamento para o Chassi:" + garantiaVO.getModelo());
								alert.setAlertCupomKey("notaFiscal.form.msg.error.faturamento.notFound");
								notaFiscalVO.setAlert(alert);
								
							}
							
							itens.add(notaFiscalVO);
						//}					
						
					}
					
				}
				
			}	
			
			return itens;
		
    	} catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }
    	
    }
    
    /** Extrai os cupons selecionados 
     * 
     * @param targetsItens[]
     * 	<code>String[]</code> Lista de itens selecionados
     * 
     * @return
     *      Um objeto <code>List</code> contendo todos os cupons.      
     * 
     * @throws BusinessException
     *      Se houverem erros de execu��o ou nas camadas 
     *      abaixo dos servi�os, ser�o encapsulados neste
     *      tipo de <code>Exception</code>.
     */
    public List listCuponsSelected( String targetsItens[] ) throws BusinessException {  
    	
    	List cupons = new ArrayList();
    		    		
		for ( int iy = 0 ; iy < targetsItens.length ; iy++ ) {
			
			String itemId   = targetsItens[iy].substring(targetsItens[iy].indexOf('@') + 1 , targetsItens[iy].lastIndexOf('@'));
		
			String tipoLote = targetsItens[iy].substring(targetsItens[iy].lastIndexOf('@') + 1 , targetsItens[iy].length());
		
			//System.out.println("EntityId:" + entityId + " -- Cupom: " + itemId + " -- Tipo:" + Long.valueOf(itemId));
		    		
			if ( TipoLote.TIPO_REVISAO.equals( Long.valueOf(tipoLote) ) ) {
		
				Cupom cupom = this.getCupomBo().get(Long.valueOf(itemId));
			
				if ( cupom != null ) {
					//System.out.println("O cupom ser� associado a nota apenas se existir valor de revis�o");
					//System.out.println("Valor da revis�o:" + cupom.getValorRevisao());
					if ( cupom.getValorRevisao() > 0 )
						cupons.add(cupom);
				}
			}
		} 
		
    	return cupons;
    	
    }
    
    /** Extrai os cupons selecionados e os cupons enviados do lan�amento de notas e
     *  revupera os devidos cupons
     * 
     * @param cupons
     * 	<code>List</code> Entidades de Cupons selecionados para associa��o
     * 
     * @param itens[]
     * 	<code>String[]</code> Lista de itens enviados
     * 
     * @return
     *      Um objeto <code>List</code> contendo os cupons para Associar com a Nota Fiscal.      
     * 
     * @throws BusinessException
     *      Se houverem erros de execu��o ou nas camadas 
     *      abaixo dos servi�os, ser�o encapsulados neste
     *      tipo de <code>Exception</code>.
     */
    public List listCuponsAssoc( List cupons, String itens[] ) throws BusinessException {  
    	
    	List cuponsAssoc = new ArrayList();
    	Iterator it = null;    	
    	
    	for ( int ix = 0 ; ix < itens.length ; ix++ ) {
    	
    		Long entityId = Long.valueOf(itens[ix]);
    		 
    		it = cupons.iterator();
    		while ( it.hasNext() ) {
    			
    			Cupom cupom = (Cupom)it.next();
    			
    			if ( cupom.getEntityId().equals(entityId) )
    				cuponsAssoc.add(cupom);
    			
    		}
    	}
	
    	return cuponsAssoc;
    	
    }
    
    /** Extrai os cupons de uma String[] 
     * 
     * @param targetsItens[]
     * 	<code>String[]</code> Lista de itens selecionados
     * 
     * @return
     *      Um objeto <code>List</code> contendo todos os cupons.      
     * 
     * @throws BusinessException
     *      Se houverem erros de execu��o ou nas camadas 
     *      abaixo dos servi�os, ser�o encapsulados neste
     *      tipo de <code>Exception</code>.
    
    public List listCuponsSelected( String targetsItens[] ) throws BusinessException {  
    	
    	List cupons = new ArrayList();
    	
    	for (int i = 0 ; i < targetsItens.length ; i++) {
    		
    		//System.out.println("targetsItens: " + targetsItens[i]);

    		//String lId      = targetsItens[i].substring(0, targetsItens[i].indexOf('@'));
    		
    		String itemId   = targetsItens[i].substring(targetsItens[i].indexOf('@') + 1 , targetsItens[i].lastIndexOf('@'));
    		
    		String tipoLote = targetsItens[i].substring(targetsItens[i].lastIndexOf('@') + 1 , targetsItens[i].length());
    		
    		//System.out.println("Cupons --> Lote:" + lId + " -- Item:" + itemId + " -- Tipo:" + Long.valueOf(itemId));
    		
    		//System.out.println("Tipo � cupom:" + TipoLote.TIPO_REVISAO.equals( Long.valueOf(tipoLote) ));
    		    		
    		if ( TipoLote.TIPO_REVISAO.equals( Long.valueOf(tipoLote) ) ) {
    		
    			Cupom cupom = this.getCupomBo().get(Long.valueOf(itemId));
    			
    			if ( cupom != null ) {
    				//System.out.println("O cupom ser� associado a nota apenas se existir valor de revis�o");
    				//System.out.println("Valor da revis�o:" + cupom.getValorRevisao());
    				if ( cupom.getValorRevisao() > 0 )
    					cupons.add(cupom);
    			}
    		}
    	}    	
	
    	return cupons;

    	
    }
     */
    
    /** Lista de Garantias para associa��o com a nota fiscal
     * 
     * @param garantias
     * 	<code>List</code> Lista de garantias selecionadas para associa��o com Nota Fiscal
     * 
     * @param itensServNF
     * 	<code>String</code> Lista de itens de Servi�os selecionados
     *  Forma��o da string: garantiaId + @
     * 
     * @param itensPecNF
     * 	<code>String</code> Lista de itensde pe�a selecionadas
     *  Forma��o da string: garantiaId + L + lineId + @
     * 
     * @param itensRemNF
     * 	<code>String</code> Lista de itens de remessa selecionados
     *  Forma��o da string: garantiaId + L + lineId + @
     *  
     * @param itensMO3NF
     * 	<code>String</code> Lista de itens de Servi�os de M�o de Obra de Terceiro selecionados
     *  Forma��o da string: garantiaId + @  
     * 
     * @param notaFiscal
     * 	<code>NotaFiscal</code> Entidade da Nota Fiscal que receber� a associa��o com os itens
     *
     * @return
     *      Um objeto <code>List</code> lista de entidades de Garantias para associa��o com nota fiscal.      
     * 
     * @throws BusinessException
     *      Se houverem erros de execu��o ou nas camadas 
     *      abaixo dos servi�os, ser�o encapsulados neste
     *      tipo de <code>Exception</code>.
     */
    public List listGarantiasAssoc( List garantias, String itensServNF, String itensPecNF, String itensRemNF, String itensMO3NF, NotaFiscal notaFiscal ) throws BusinessException {  
    	
    	List 			garantiasAssoc = new ArrayList();
    	
		// Para nota fiscal de Remessa pegamos as SG's e setamos a entidade notaFiscal e a linha da SG 
		if ( NotaFiscal.TIPO_NOTA_REMESSA.equals(notaFiscal.getTipoNFId()) ) {
			//System.out.println("NotaFiscalBusinnes - listGarantiasAssoc - String itensRemNF:" + itensRemNF);
			
			garantiasAssoc = listGarantiasByPecaNotaFiscal(garantias, itensRemNF, notaFiscal);
			
		} else if ( NotaFiscal.TIPO_NOTA_PECA.equals(notaFiscal.getTipoNFId())) {
			//System.out.println("NotaFiscalBusinnes - listGarantiasAssoc - String itensPecNF:" + itensPecNF);
			
			garantiasAssoc = listGarantiasByPecaNotaFiscal(garantias,itensPecNF, notaFiscal);
	
		} else if ( NotaFiscal.TIPO_NOTA_SERVICO_TERCEIRO.equals(notaFiscal.getTipoNFId()) ) {
			
			//System.out.println("NotaFiscalBusinnes - listGarantiasAssoc - String itensMO3NF:" + itensMO3NF);
			
			garantiasAssoc = listGarantiasByServicoNotaFiscal(garantias, "" ,itensMO3NF);    	
			
		} else if ( NotaFiscal.TIPO_NOTA_SERVICO.equals(notaFiscal.getTipoNFId()) ) {
			//System.out.println("NotaFiscalBusinnes - listGarantiasAssoc - String itensServNF:" + itensServNF);
			//System.out.println("NotaFiscalBusinnes - listGarantiasAssoc - String itensMO3NF:" + itensMO3NF);
			
			garantiasAssoc = listGarantiasByServicoNotaFiscal(garantias,itensMO3NF, itensServNF);  
			
		} else if ( NotaFiscal.TIPO_NOTA_CONJUGADA.equals(notaFiscal.getTipoNFId())  ) {
			//System.out.println("NotaFiscalBusinnes - listGarantiasAssoc - String itensServNF:" + itensServNF);
			//System.out.println("NotaFiscalBusinnes - listGarantiasAssoc - String itensMO3NF:" + itensMO3NF);
			//System.out.println("NotaFiscalBusinnes - listGarantiasAssoc - String itensPecNF:" + itensPecNF);
			
			garantiasAssoc = listGarantiasByConjugadaNotaFiscal(garantias, itensServNF, itensMO3NF, itensPecNF, notaFiscal);    			
			
		}
		
    	return garantiasAssoc;
    	
    }
    
    /** Associa a Nota Fiscal de Servico ou M�o de Obra de Terceiros ou Pe�as com as Garantias Correspondentes 
     * 
     * @param garantias
     * 	<code>List</code> Lista de garantias selecionadas para associa��o com Nota Fiscal
     * 
     * @param itensServNF
     * 	<code>String</code> Lista de itens de servi�o ou pe�a selecionados
     *  Forma��o da string: garantiaId + @
     *  
     * @param itensMO3NF
     * 	<code>String</code> Lista de itens de remessa ou pe�a selecionados
     *  Forma��o da string: garantiaId + @
     *  
     * @param itensPecNF
     * 	<code>String</code> Lista de itensde pe�a selecionadas
     *  Forma��o da string: garantiaId + L + lineId + @
     *  
     *  @param notaFiscal
     * 	<code>NotaFiscal</code> Entidade da Nota Fiscal que receber� a associa��o com os itens
     *  
     * @return
     *      Um objeto <code>AlertEntityTO</code> contendo o resultado da opera��o.      
     * 
     * @throws BusinessException
     *      Se houverem erros de execu��o ou nas camadas 
     *      abaixo dos servi�os, ser�o encapsulados neste
     *      tipo de <code>Exception</code>.
     */
    public List listGarantiasByConjugadaNotaFiscal(List garantias, String itensServNF, String itensMO3NF, String itensPecNF, NotaFiscal notaFiscal) throws BusinessException {
    	
    	List garantiasAssoc = new ArrayList();    	
    	
    	String sgSv[]  = "".equals(itensServNF) ? null : itensServNF.split("@");
    	String sgMO[]  = "".equals(itensMO3NF)  ? null : itensMO3NF.split("@");
    	String sgPc[]  = "".equals(itensPecNF)  ? null : itensPecNF.split("@");
    	
    	Iterator it  = garantias.iterator();
    	boolean  isAssoc = false;
    	
    	while ( it.hasNext() ) {
			
			GarantiaHeader garantia = (GarantiaHeader)it.next();
			isAssoc = false;
			
			if ( sgSv != null  ) {
				// Primeiro percorremos a lista de itens de servi�o
				for ( int ix = 0 ; ix < sgSv.length ; ix++ ) {
					
					if ( garantia.getEntityId().equals(Integer.valueOf(sgSv[ix])) ) {
						
						isAssoc = true;
						
					}
				}
			}			
			
			if ( sgMO != null ) {
				// Percorremos a lista de itens de servi�ode M�o de Obra de Terceiros
				for ( int ix = 0 ; ix < sgMO.length ; ix++ ) {
					
					if ( garantia.getEntityId().equals(Integer.valueOf(sgMO[ix])) ) {
						
						isAssoc = true;
						
					}
				}
			}
			
			if ( sgPc != null ) {			
				// Agora as pe�as ser�o associadas
				if ( ! garantia.getListGarantiaLine().isEmpty() ) {
					for ( int ix = 0 ; ix < sgPc.length ; ix++ ) {
					
						String garantiaIdStr = sgPc[ix].substring(0, sgPc[ix].lastIndexOf('L'));
						String lineIdStr     = sgPc[ix].substring(sgPc[ix].lastIndexOf('L') + 1, sgPc[ix].length());
						
						//System.out.println("NotaFiscalBusinnes - listGarantiasByPecaNotaFiscal - garantiaIdStr:" + garantiaIdStr + " -lineIdStr:" + lineIdStr);
				
						if ( garantia.getEntityId().equals(Integer.valueOf(garantiaIdStr)) ) {
						
							isAssoc = garantia.addNotaFiscalPeca(new Long(lineIdStr), notaFiscal);
							
						}				 
						 
					 }				
				}
			}
			
			if ( isAssoc ) {
				
				StatusGarantia statusGarantia = new StatusGarantia();
				//statusGarantia.setEntityId(StatusGarantia.STATUS_NF_DIGITADA);
				statusGarantia.setEntityId(StatusGarantia.STATUS_AGUARDANDO_ENVIO_DOCUMENTOS);
				garantia.setStatusGarantia(statusGarantia);
				garantiasAssoc.add(garantia);
			}
    	}
    	
    	return garantiasAssoc;
    }
    /** lista de garantias para Nota Fiscal de Remessa ou Pe�a 
     * 
     * @param garantias
     * 	<code>List</code> Lista de garantias selecionadas para associa��o com Nota Fiscal
     * 
     * @param itensNF
     * 	<code>String</code> Lista de itens de remessa ou pe�a selecionados
     *  Forma��o da string: garantiaId + L + lineId + @
     * 
     * @param notaFiscal
     * 	<code>NotaFiscal</code> Entidade da Nota Fiscal que receber� a associa��o com os itens
     *  
     * @return
     *      Um objeto <code>AlertEntityTO</code> contendo o resultado da opera��o.      
     * 
     * @throws BusinessException
     *      Se houverem erros de execu��o ou nas camadas 
     *      abaixo dos servi�os, ser�o encapsulados neste
     *      tipo de <code>Exception</code>.
     */
    public List listGarantiasByPecaNotaFiscal(List garantias, String itensNF, NotaFiscal notaFiscal ) throws BusinessException {
    	
    	List garantiasAssoc = new ArrayList();  
    	
    	String sg[]  = "".equals(itensNF) ? null : itensNF.split("@");
    	Iterator it  = garantias.iterator();
    	boolean  isAssoc = false;
    	if ( sg != null ) {	
	    	while ( it.hasNext() ) {
				
				GarantiaHeader garantia = (GarantiaHeader)it.next();
				isAssoc = false;
				
				if ( ! garantia.getListGarantiaLine().isEmpty() ) {
					for ( int ix = 0 ; ix < sg.length ; ix++ ) {
					
						String garantiaIdStr = sg[ix].substring(0, sg[ix].lastIndexOf('L'));
						String lineIdStr     = sg[ix].substring(sg[ix].lastIndexOf('L') + 1, sg[ix].length());
						
						//System.out.println("NotaFiscalBusinnes - listGarantiasByPecaNotaFiscal - garantiaIdStr:" + garantiaIdStr + " -lineIdStr:" + lineIdStr);
				
						if ( garantia.getEntityId().equals(Integer.valueOf(garantiaIdStr)) ) {
						
							isAssoc = garantia.addNotaFiscalPeca(new Long(lineIdStr), notaFiscal);
							
						}				 
						 
					 }				
				}
				if ( isAssoc ) {
					
					StatusGarantia statusGarantia = new StatusGarantia();
					//statusGarantia.setEntityId(StatusGarantia.STATUS_NF_DIGITADA);
					statusGarantia.setEntityId(StatusGarantia.STATUS_AGUARDANDO_ENVIO_DOCUMENTOS);
					garantia.setStatusGarantia(statusGarantia);
					garantiasAssoc.add(garantia);
				}
	    	}
    	}
    	
    	return garantiasAssoc;
    	
    }
    

    
    /** Associa a Nota Fiscal de Servico ou M�o de Obra de Terceiros com as Garantias Correspondentes 
     * 
     * @param garantias
     * 	<code>List</code> Lista de garantias selecionadas para associa��o com Nota Fiscal
     * 
     * @param itensServNF
     * 	<code>String</code> Lista de itens de servi�o ou pe�a selecionados
     *  Forma��o da string: garantiaId + @
     *  
     * @param itensMO3NF
     * 	<code>String</code> Lista de itens de remessa ou pe�a selecionados
     *  Forma��o da string: garantiaId + @
     *  
     * @return
     *      Um objeto <code>AlertEntityTO</code> contendo o resultado da opera��o.      
     * 
     * @throws BusinessException
     *      Se houverem erros de execu��o ou nas camadas 
     *      abaixo dos servi�os, ser�o encapsulados neste
     *      tipo de <code>Exception</code>.
     */
    public List listGarantiasByServicoNotaFiscal(List garantias, String itensServNF, String itensMO3NF) throws BusinessException {
    	
    	List garantiasAssoc = new ArrayList();    	
    	
    	String sgSv[]  = "".equals(itensServNF) ? null : itensServNF.split("@");
    	String sgMO[]  = "".equals(itensMO3NF)  ? null : itensMO3NF.split("@");
    	
    	//System.out.println("NotaFiscalBusinnes - listGarantiasByServicoNotaFiscal - sgSv:" + sgSv + " -lineIdStr:" + sgMO);
    	
    	Iterator it  = garantias.iterator();
    	boolean  isAssoc = false;
    	
    	while ( it.hasNext() ) {
			
			GarantiaHeader garantia = (GarantiaHeader)it.next();
			isAssoc = false;
			
			if ( sgSv != null ) {
				// Primeiro percorremos a lista de itens de servi�o
				for ( int ix = 0 ; ix < sgSv.length ; ix++ ) {
					
					if ( garantia.getEntityId().equals(Integer.valueOf(sgSv[ix])) ) {
						
						isAssoc = true;
						
					}
				}
			}
			
			if ( sgMO != null ) {
				// Percorremos a lista de itens de servi�ode M�o de Obra de Terceiros
				for ( int ix = 0 ; ix < sgMO.length ; ix++ ) {
					
					if ( garantia.getEntityId().equals(Integer.valueOf(sgMO[ix])) ) {
						
						isAssoc = true;
						
					}
				}
			}
			
			if ( isAssoc ) {
				
				StatusGarantia statusGarantia = new StatusGarantia();
				//statusGarantia.setEntityId(StatusGarantia.STATUS_NF_DIGITADA);
				statusGarantia.setEntityId(StatusGarantia.STATUS_AGUARDANDO_ENVIO_DOCUMENTOS);
				garantia.setStatusGarantia(statusGarantia);
				garantiasAssoc.add(garantia);
			}
    	}
    	
    	return garantiasAssoc;
    	
    }
    
    /** Extrai as garantias de uma String[] 
     * 
     * @param targetsItens[]
     * 	<code>String[]</code> Lista de itens selecionados
     *
     * @return
     *      Um objeto <code>List</code> contendo todos as garantias.      
     * 
     * @throws BusinessException
     *      Se houverem erros de execu��o ou nas camadas 
     *      abaixo dos servi�os, ser�o encapsulados neste
     *      tipo de <code>Exception</code>.
     */
    public List listGarantiasSelected( String targetsItens[] ) throws BusinessException {  
    	
    	List garantias = new ArrayList();
    	
    	try {
    		
	    	for (int i = 0 ; i < targetsItens.length ; i++) {
	
	    		//String lId      = targetsItens[i].substring(0, targetsItens[i].indexOf('@'));
	    		
	    		String itemId   = targetsItens[i].substring(targetsItens[i].indexOf('@') + 1 , targetsItens[i].lastIndexOf('@'));
	    		
	    		String tipoLote = targetsItens[i].substring(targetsItens[i].lastIndexOf('@') + 1 , targetsItens[i].length());
	    		
	    		//System.out.println("Garantias --> Lote:" + lId + " -- Item:" + itemId + " -- Tipo:" + tipoLote);
	    		    		
	    		if ( TipoLote.TIPO_GARANTIA.equals( Long.valueOf(tipoLote) ) ) {
	    		
	    			GarantiaHeader garantia = (GarantiaHeader)this.getNotaFiscalDao().get(GarantiaHeader.class, Integer.valueOf(itemId));
	    			
	    			if ( garantia != null ) {
	    				// A garantia ser� associada a nota apenas se existir valor de servi�o ou pe�a ou MO3
	    				if ( garantia.getValorTotalPecas() > 0 || garantia.getValorTotalServicos() > 0 || garantia.getValorServicoTerceiro() > 0 )
	    					garantias.add(garantia);
	    			}
	    		}
	    	}    	
    	
	    	return garantias;
    	
	    } catch (DaoException dExp) {
	
	        throw new BusinessException(dExp);
	
	    }
    	
    }
    
    /** Retorna o lote selecionado para lan�amento de notas
     * 
     * @param targetsLotes[]
     * 	<code>String[]</code> Lista de Lotes selecionados 
     * 
     * @return
     *      Um objeto <code>Lote</code>   
     * 
     * @throws BusinessException
     *      Se houverem erros de execu��o ou nas camadas 
     *      abaixo dos servi�os, ser�o encapsulados neste
     *      tipo de <code>Exception</code>.
     */
    public Lote getLoteByTargets( String targetsLotes[] ) throws BusinessException { 
    	
    	try {
    	
    		String lId = new String();
	    	for (int i = 0 ; i < targetsLotes.length ; i++) {
	
	    		lId      = targetsLotes[i];
	    	}
	    	
	    	Lote lote = (Lote)this.getNotaFiscalDao().get(Lote.class, Integer.valueOf(lId));

	    	return lote;
	    	
    	 } catch (DaoException dExp) {

             throw new BusinessException(dExp);

         }
    }
    
    /** Lista de itens de Cupom e Garantia para lan�amento de notas
     * 
     * @param targetsItens[]
     * 	<code>String[]</code> Lista de itens selecionados  9999@9999@9 -- loteId@itemId@tipoLote
     * 
     * @return
     *      Um objeto <code>NotaFiscalLancamentoTO</code> contendo todos os itens.      
     * 
     * @throws BusinessException
     *      Se houverem erros de execu��o ou nas camadas 
     *      abaixo dos servi�os, ser�o encapsulados neste
     *      tipo de <code>Exception</code>.
    */
    public NotaFiscalLancamentoTO listItensLote( String targetsItens[] ) throws BusinessException {    	
    	    	
    	String garantias = "";
    	String cupons    = "";
    	String tipoLote  = "";
    	NotaFiscalLancamentoTO notaFiscalLancamentoTO = null;
    	
    	/////////////////////////////////////////////////////////////////////////////////////
    	// Neste trecho ser�o extra�dos do array targetsItens[] os itens e o tipo do lote. //
    	// Ser� montado uma string de acordo dom o tipo do lote com a concatena��o do id   //
    	// do Cupom ou da SG com v�rgula para ser utilizada na query que recupera as       //
    	// entidades dos itens.															   //
    	/////////////////////////////////////////////////////////////////////////////////////
    	for (int i = 0 ; i < targetsItens.length ; i++) {

    		//String lId      = targetsItens[i].substring(0, targetsItens[i].indexOf('@'));
    		
    		String itemId   = targetsItens[i].substring(targetsItens[i].indexOf('@') + 1 , targetsItens[i].lastIndexOf('@'));
    		
    		tipoLote = targetsItens[i].substring(targetsItens[i].lastIndexOf('@') + 1 , targetsItens[i].length());
    		
    		//System.out.println("Lote:" + lId + " -- Item:" + itemId + " -- Tipo:" + tipoLote);
    		    		
    		if ( TipoLote.TIPO_REVISAO.equals( Long.valueOf(tipoLote) ) ) {
    		
    			if ( "".equals(cupons) )
    				cupons += 	itemId;
    			else
    				cupons += 	", " + itemId;
    			
    		} else if ( TipoLote.TIPO_GARANTIA.equals( Long.valueOf(tipoLote) ) ) { 
    			
    			if ( "".equals(garantias) )
    				garantias += 	itemId;
    			else
    				garantias += 	", " + itemId;
    			
    		}
    	}
    	
    	///////////////////////////////////////////////////////////////////////////////////////
    	// Neste trecho ser� chamado o m�todo correspondente ao tipo do lote para recuperar  //
    	// as entidades necess�rias para retornar ao solicitando							 //
    	//////////////////////////////////////////////////////////////////////////////////////    	
    	try {
    	
	    	if ( TipoLote.TIPO_REVISAO.equals( Long.valueOf(tipoLote) ) ) {
	    		
	    		if ( !("".equals(cupons)) && !(cupons.length() == 0) ) {
	    			
	    			notaFiscalLancamentoTO = notaFiscalDao.listCuponsForNota(cupons);    			
	    			
	    		}				
				
			} else if ( TipoLote.TIPO_GARANTIA.equals( Long.valueOf(tipoLote) ) ) { 
				
				if ( !("".equals(garantias)) && !(garantias.length() == 0) ) {
	    			
					notaFiscalLancamentoTO = notaFiscalDao.listGarantiasForNota(garantias);    				
	    			
	    		}	
			}
	    	
            return notaFiscalLancamentoTO;

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }
    	
    }
     
    // M�todo antigo 
    /** Lista de itens de Cupom e Garantia para lan�amento de notas
     * 
     * @param targetsItens[]
     * 	<code>String[]</code> Lista de itens selecionados  9999@9999@9 -- loteId@itemId@tipoLote
     * 
     * @return
     *      Um objeto <code>List</code> contendo todos os itens.      
     * 
     * @throws BusinessException
     *      Se houverem erros de execu��o ou nas camadas 
     *      abaixo dos servi�os, ser�o encapsulados neste
     *      tipo de <code>Exception</code>.
     
    public List listItensLote( String targetsItens[] ) throws BusinessException {    	
    	    	
    	String garantias = "";
    	String cupons    = "";
    	
    	for (int i = 0 ; i < targetsItens.length ; i++) {

    		//String lId      = targetsItens[i].substring(0, targetsItens[i].indexOf('@'));
    		
    		String itemId   = targetsItens[i].substring(targetsItens[i].indexOf('@') + 1 , targetsItens[i].lastIndexOf('@'));
    		
    		String tipoLote = targetsItens[i].substring(targetsItens[i].lastIndexOf('@') + 1 , targetsItens[i].length());
    		
    		//System.out.println("Lote:" + lId + " -- Item:" + itemId + " -- Tipo:" + tipoLote);
    		    		
    		if ( TipoLote.TIPO_REVISAO.equals( Long.valueOf(tipoLote) ) ) {
    		
    			if ( "".equals(cupons) )
    				cupons += 	itemId;
    			else
    				cupons += 	", " + itemId;
    			
    		} else if ( TipoLote.TIPO_GARANTIA.equals( Long.valueOf(tipoLote) ) ) { 
    			
    			if ( "".equals(garantias) )
    				garantias += 	itemId;
    			else
    				garantias += 	", " + itemId;
    			
    		}
    	}
    	
    	if ( "".equals(cupons) || cupons.length() == 0 )
    		cupons = "0";
    	if ( "".equals(garantias) || garantias.length() == 0 )
    		garantias = "0";
    	
    	//System.out.println(new Date() + " - Cupons:" + cupons  + " - garantias:" + garantias);
    	
    	try {

            return notaFiscalDao.listItensLote(cupons, garantias);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }
    	
    }
    */
    /** Lista de valor para o lan�amento de nota fiscal por Cupom ou Garantia
     * 
     * @param String item
     * 
     * @param String typeItem
     * 	<code>String</code> Tipo do Item "C" - Cupom ou "G" -  Garantia
     * 
     * @return
     *      Um objeto <code>List</code> contendo todos os itens ou vazio.      
     * 
     * @throws BusinessException
     *      Se houverem erros de execu��o ou nas camadas 
     *      abaixo dos servi�os, ser�o encapsulados neste
     *      tipo de <code>Exception</code>.
     */
    public List listItem( String item, String typeItem ) throws BusinessException {   
    	    	
    	try {
    		
    		if ( "C".equalsIgnoreCase(typeItem) )
    			return notaFiscalDao.listItensLote(item, "");
    		else if ( "G".equalsIgnoreCase(typeItem) )
    			// Para garantia deve ser enviado no segundo par�metro
    			return notaFiscalDao.listItensLote("", item);
    		else
    			return new ArrayList();

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }
    	
    }

    /** Recupera uma NotaFiscal pelo id.
     *
     *  @param entityId
     *      Refer�ncia da entidade na camada de persist�ncia.
     *  @return
     *      Um objeto <code>NotaFiscal</code>.
     *      
     * @throws BusinessException
     *      Se houverem erros de execu��o ou nas camadas 
     *      abaixo dos servi�os, ser�o encapsulados neste
     *      tipo de <code>Exception</code>.
     */
    public NotaFiscal get(Serializable entityId) throws BusinessException {

        try {

            return (NotaFiscal)notaFiscalDao.get(NotaFiscal.class, entityId);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    }
    
    /** Lista todos as notas fiscais existentes no banco de dados.
     * 
     *  @return
     *      Um objeto <code>List</code> contendo todos os 
     *      clientes persistentes na aplica��o.
     *      
     * @throws BusinessException
     *      Se houverem erros de execu��o ou nas camadas 
     *      abaixo dos servi�os, ser�o encapsulados neste
     *      tipo de <code>Exception</code>.
     */
    public List list() throws BusinessException {

        try {

            return notaFiscalDao.listAll(NotaFiscal.class);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    }
    
    /** Salva ou atualiza o estado de uma nota fiscal.
     *  <p>
     *  Se a entidade recebida n�o existir na camada de persist�ncia
     *  da aplica��o, ser� criada. Do contr�rio seus valores ser�o
     *  atualizados de acordo com as informa��es alteradas.
     *  
     *  @param notas
     *      <code>List</code> representando a cole��o de notas fiscais a serem
     *      criadas/atualizadas na camada de persist�ncia.
     *      
     *  @param cupons
     *      <code>List</code> representando a cole��o de cupons a serem
     *      inclu�dos na nota fiscal.  
     *      
     *  @param garantias
     *      <code>List</code> representando a cole��o de garantias a serem
     *      inclu�das na nota fiscal.    
     *      
     *  @param usuraioId
     *  	<code>Long</code> Id do usu�rio
     *      
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma BusinessException.
     */   
    public void save(List notas, List cupons, List garantias, Long usuarioId) throws BusinessException {
    	
    	Iterator itNotas     = notas.iterator();
    	Iterator itCupons    = null;
    	Iterator itGarantias = null;
    	
    	//System.out.println("Tamanhos - Notas:" + notas.size() + " - cupons:" + cupons.size() + " - garantias:" + garantias.size());
    	
    	while ( itNotas.hasNext() ) {
    		
    		NotaFiscal notaFiscal = (NotaFiscal)itNotas.next();
    		
    		// Inclu�mos os cupons para a nota fiscal
    		itCupons = cupons.iterator();
    		
    		while ( itCupons.hasNext() ) { 
    			
    			Cupom cupom = (Cupom)itCupons.next();    			
    			notaFiscal.addCupom(cupom);
    			
    		}
    		
    		// Inclu�mos as garantias para a nota fiscal
    		itGarantias = garantias.iterator();
    		
    		while ( itGarantias.hasNext() ) {
    			
    			GarantiaHeader garantiaHeader = (GarantiaHeader)itGarantias.next();    			
    			notaFiscal.setGarantia(garantiaHeader);
    			
    		}
    		
    		this.save(notaFiscal);
    		
    	}
    	
    } 
    
    /** Salva ou atualiza uma entidade de Nota Fiscal
     *  Possui controle de transa��o para salvar a cole��o de notas enviadas,
     *  caso ocorra algum problema no meio do processo, deve ser executado o rollback.
     *  
     *  @param notas
     *      <code>List</code> representando a cole��o de notas fiscais a serem
     *      criadas/atualizadas na camada de persist�ncia.
     *      
     *  @return alert
     *  	<code>AlertEntityTO</code> Objeto com as informa��es da transa��o. 
     *      
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma BusinessException.
     */    
    public AlertEntityTO save(List notas) throws BusinessException {
    	
    	// Recuperamos as Entidades
		final List notasFinal 	  = notas;
		
		AlertEntityTO alert = (AlertEntityTO)transactionTemplate.execute(
                
	            new TransactionCallback() {
	                
	                public Object doInTransaction(TransactionStatus ts) {
	                	
	                	AlertEntityTO alert = new AlertEntityTO();
	                	
						try {
							
							Iterator itNotas     = notasFinal.iterator();
							
							while ( itNotas.hasNext() ) {
    		
								NotaFiscal notaFiscal = (NotaFiscal)itNotas.next();
    		    		    		
								notaFiscalDao.makePersistent(notaFiscal);
								
								
								
    		
							}   
							
							alert.setAlertKey("notaFiscal.msg.success.save");
							alert.setAlertSeveridade(AlertEntityTO.SEVERIDADE_SUCCESS);
							
						} catch ( DaoException daoExp ) {

							ts.setRollbackOnly();
							
							System.out.println(" ----------> Rollback - Erro: " + daoExp.getMessage());
							
							alert.setAlertKey("notaFiscal.msg.error.launch");
							alert.setAlertSeveridade(AlertEntityTO.SEVERIDADE_ERROR);
							alert.setAlertText(daoExp.getMessage());

				        }
						
						return alert;
        
	                }
	                
	            }
	                
	        );
		
		return alert;
    	
    }
    
    /** Salva ou atualiza uma entidade de Nota Fiscal
     *  Possui controle de transa��o para salvar a cole��o de notas enviadas,
     *  caso ocorra algum problema no meio do processo, deve ser executado o rollback.
     *  
     *  @param Lote
     *      <code>Lote</code> representando um lote com a cole��o de notas fiscais a serem
     *      criadas/atualizadas na camada de persist�ncia.
     *      
     *  @return alert
     *  	<code>AlertEntityTO</code> Objeto com as informa��es da transa��o. 
     *      
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma BusinessException.
     */    
    public AlertEntityTO save(Lote lote) throws BusinessException {
    	
    	// Recuperamos as Entidades
		final Lote loteFinal  	  = lote;
		
		AlertEntityTO alert = (AlertEntityTO)transactionTemplate.execute(
                
	            new TransactionCallback() {
	                
	                public Object doInTransaction(TransactionStatus ts) {
	                	
	                	AlertEntityTO alert = new AlertEntityTO();
	                	
						try {
							
							Iterator itNotas     = loteFinal.getNotas().iterator();
							
							while ( itNotas.hasNext() ) {
    		
								NotaFiscal notaFiscal = (NotaFiscal)itNotas.next();
    		    		    		
								notaFiscalDao.makePersistent(notaFiscal);
    		
							}
							
							alert.setAlertKey("notaFiscal.msg.success.save");
							alert.setAlertSeveridade(AlertEntityTO.SEVERIDADE_SUCCESS);
							
						} catch ( DaoException daoExp ) {

							ts.setRollbackOnly();
							
							System.out.println(" ----------> Rollback - Erro: " + daoExp.getMessage());
							
							alert.setAlertKey("notaFiscal.msg.error.launch");
							alert.setAlertSeveridade(AlertEntityTO.SEVERIDADE_ERROR);
							alert.setAlertText(daoExp.getMessage());

				        } 
						
						return alert;
        
	                }
	                
	            }
	                
	        );
		
		return alert;
    	
    }

    /** Salva ou atualiza o estado de uma entidade.
     *  <p>
     *  Se a entidade recebida n�o existir na camada de persist�ncia
     *  da aplica��o, ser� criada. Do contr�rio seus valores ser�o
     *  atualizados de acordo com as informa��es alteradas.
     *  
     *  @param NotaFiscal
     *      <code>NotaFiscal</code> representando a entidade a ser
     *      criada/atualizada na camada de persist�ncia.
     *      
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma BusinessException.
     */    
    public void save(NotaFiscal notaFiscal) throws BusinessException {

        try {

        	notaFiscalDao.makePersistent(notaFiscal);

        } catch ( DaoException daoExp ) {

            throw new BusinessException( daoExp );

        }

    }
    
    /** Obt�m uma entidade de parametro do sistema a partir de um nome de par�metro passado.
	 * 
	 *  @param parameterName
	 *      <code>Serializable<code>. Nome do par�metro(Coluna) pelo qual o objeto
	 *      dever� ser pesquisado na camada de persist�ncia da aplica��o.

	 *  @return
	 *      Um <code>ParametroSistema</code> populado com os dados da 
	 *      entidade espec�fica, solicitada, ou caso nenhuma entidade
	 *      seja encontrada, retornar� <code>null</code>.
	 *      
	 *  @throws DaoException
	 *      Se houverem problemas nas camadas inferiores, estes ser�o 
	 *      convertidos para uma DaoException.
	 */     
	public ParametroSistema getByParameterSystem(String parameterName) throws BusinessException {

		try {

			return (ParametroSistema) notaFiscalDao.getByParameter(ParametroSistema.class, "nomeParametro", parameterName);

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}    	

	}
	
	/** Verifica se uma nota fiscal j� existe
	 * 
	 * @param numeroNF
	 * @param concessionaria
	 * @return boolean  true quando existir 
	 * @throws BusinessException
	 */
	public boolean isEcxistNota( Long numeroNF, Concessionaria concessionaria ) throws BusinessException {

		boolean isEcxist = false;
		
		try {

			NotaFiscal notaFiscal = (NotaFiscal)notaFiscalDao.getNotaFiscalByNumberAndConcessionaria(numeroNF, concessionaria);
			
			if ( notaFiscal != null )
				isEcxist = true;			
			
			return isEcxist;

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}    	

	}
	
	/** Verifica se uma nota fiscal e s�rie j� existe para uma determinada concession�ria
	 * 
	 * @param numeroNF
	 * @param serieNF
	 * @param concessionaria
	 * @return boolean  true quando existir 
	 * @throws BusinessException
	 */
	public boolean isEcxistNotaBySerie( Long numeroNF, String serie, Concessionaria concessionaria ) throws BusinessException {

		boolean isEcxist = false;
		
		try {
			
			ParametroSistema ps = (ParametroSistema)notaFiscalDao.getByParameter(ParametroSistema.class, "nomeParametro", ParametroSistema.DATA_BASE_NFE);
			//System.out.println("Data Param:" + ps.getValorParametro());
			NotaFiscal notaFiscal = (NotaFiscal)notaFiscalDao.getNotaFiscalByNumberAndSerieAndConcessionaria(numeroNF, serie, concessionaria);
			
			if ( notaFiscal != null ) {
				
				//System.out.println("Data:" + DateUtils.compareDate(notaFiscal.getDataNF(), DateUtils.format(ps.getValorParametro(), "dd/MM/yyyy")));
				if ( DateUtils.compareDate(notaFiscal.getDataNF(), DateUtils.format(ps.getValorParametro(), "dd/MM/yyyy")) > 0 )
					isEcxist = true;			
			}
			return isEcxist;

		} catch (DaoException dExp) {
			//System.out.println("Par�metro inv�lido!");
			throw new BusinessException(dExp);

		}    	

	}
	
	/**
	 * Retorna um objeto NotaFiscalVO do cupom enviado.
	 * 
	 * @param cupom
	 *            <code>Cupom</code> Entidade de cupom
	 * 
	 * @return Retorna uma lista de objetos NotaFiscalVO ou nulo.
	 * 
	 * @throws DaoException
	 *             Se houverem erros durante a execu��o estes ser�o lan�ados
	 *             como uma Exception deste tipo.
	 */
	public List getItembyCupom(Cupom cupom) throws BusinessException {

		try {

			return notaFiscalDao.getItembyCupom(cupom);

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}

	}
	
	/**
	 * Retorna um objeto NotaFiscalVO do cupom enviado.
	 * 
	 * @param garantia
	 *            <code>GarantiaHeader</code> Entidade de Garantia
	 * 
	 * @return Retorna uma lista de objetos NotaFiscalVO ou nulo.
	 * 
	 * @throws DaoException
	 *             Se houverem erros durante a execu��o estes ser�o lan�ados
	 *             como uma Exception deste tipo.
	 */
	public List getItembyGarantia(GarantiaHeader garantia) throws BusinessException {

		try {

			return notaFiscalDao.getItembyGarantia(garantia);

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}

	}
	
	/** Verifica se a s�rie informada � v�lida para o lan�amento de nota fiscal.
     * 
     *  @param serieStr
     *    <code>String</code> Valor para a proproiedade s�rie
     * 
     *  @return
     *      Um <code>true</code> para s�rie v�lida.
     *      
     * @throws BusinessException
     *      Se houverem erros de execu��o ou nas camadas 
     *      abaixo dos servi�os, ser�o encapsulados neste
     *      tipo de <code>Exception</code>.
     */
    public boolean isEcxistSerie(String serieStr) throws BusinessException {

    	boolean isValid = false;
        try {

            Serie serie = (Serie) notaFiscalDao.getByParameter(Serie.class, "serie", serieStr.toUpperCase());

            if ( serie !=  null ) {
            	
            	if ( serieStr.equalsIgnoreCase(serie.getSerie()) ) {
            		
            		isValid = true;
            	}
            	
            } 
            
        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }
        
        return isValid;

    }

	/**
	 * M�todo getter para a propriedade notaFiscalDao
	 * 
	 * @return NotaFiscalDao de notaFiscalDao
	 */
	public NotaFiscalDao getNotaFiscalDao() {
		return notaFiscalDao;
	}

	/** M�todo setter para a propriedade notaFiscalDao
	 *
	 * @param notaFiscalDao NotaFiscalDao
	 */
	public void setNotaFiscalDao(NotaFiscalDao notaFiscalDao) {
		this.notaFiscalDao = notaFiscalDao;
	}

	/** M�todo getter para a propriedade loteBo
	 * 
	 *  @return LoteBusiness
	 *
	 */
	public LoteBusiness getLoteBo() {
		return loteBo;
	}

	/** M�todo setter para a propriedade loteBo
	 *
	 * @param loteBo 
	 *           <code>LoteBusiness</code> a ser designado para loteBo.
	 * 
	 */
	public void setLoteBo(LoteBusiness loteBo) {
		this.loteBo = loteBo;
	}

	/** M�todo getter para a propriedade cupomBo
	 * 
	 *  @return CupomBusiness
	 *
	 */
	public CupomBusiness getCupomBo() {
		return cupomBo;
	}

	/** M�todo setter para a propriedade cupomBo
	 *
	 * @param cupomBo 
	 *           <code>CupomBusiness</code> a ser designado para cupomBo.
	 * 
	 */
	public void setCupomBo(CupomBusiness cupomBo) {
		this.cupomBo = cupomBo;
	}

	/**
	 * M�todo getter para a propriedade transactionTemplate
	 * @return  TransactionTemplate de transactionTemplate
	 */
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	/**
	 * M�todo setter para a propriedade transactionTemplate
	 * @param transactionTemplate TransactionTemplate
	 */
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
}
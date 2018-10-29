/* Resource Inform�tica
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......InfoMercadoBusiness.java
 *   .: Cria��o.....01 de julho, 11:20
 *   .: Autor.......Edson Luiz Sumensari
 *                  Thiago Uriel M. Garcia
 *   .: Descri��o...Objeto de neg�cios para a entidade "InfoMercado".
 */
package br.com.yamaha.sistemagarantia.business;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import br.com.resource.infra.business.BusinessObject;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.BusinessRuleException;
import br.com.resource.infra.exception.DaoException;
import br.com.resource.infra.utils.DateUtils;
import br.com.yamaha.sistemagarantia.controller.helper.ControllerHelper;
import br.com.yamaha.sistemagarantia.dao.InformacaoMercadoDao;
import br.com.yamaha.sistemagarantia.model.AlertGarantia;
import br.com.yamaha.sistemagarantia.model.Concessionaria;
import br.com.yamaha.sistemagarantia.model.Condicao;
import br.com.yamaha.sistemagarantia.model.FatorGarantia;
import br.com.yamaha.sistemagarantia.model.GarantiaHeader;
import br.com.yamaha.sistemagarantia.model.GarantiaLine;
import br.com.yamaha.sistemagarantia.model.HistAcaoTomada;
import br.com.yamaha.sistemagarantia.model.HistCausaProblema;
import br.com.yamaha.sistemagarantia.model.HistDiagnostico;
import br.com.yamaha.sistemagarantia.model.HistSintoma;
import br.com.yamaha.sistemagarantia.model.InfoMercFotos;
import br.com.yamaha.sistemagarantia.model.InfoMercGarantia;
import br.com.yamaha.sistemagarantia.model.InfoMercado;
import br.com.yamaha.sistemagarantia.model.Item;
import br.com.yamaha.sistemagarantia.model.Lote;
import br.com.yamaha.sistemagarantia.model.Sintoma;
import br.com.yamaha.sistemagarantia.model.StatusGarantia;
import br.com.yamaha.sistemagarantia.model.StatusInfoMercado;
import br.com.yamaha.sistemagarantia.model.TabelaPreco;
import br.com.yamaha.sistemagarantia.model.TipoGasolina;
import br.com.yamaha.sistemagarantia.model.TipoProdutoInfo;
import br.com.yamaha.sistemagarantia.model.TipoUsoBarco;
import br.com.yamaha.sistemagarantia.model.id.TabelaPrecoId;
import br.com.yamaha.sistemagarantia.view.InfoMercadoVO;

/** Classe de neg�cios relacionada � entidade <b>Cliente</b>.
 * 
 *  @author Edson Luiz Sumensari
 *  @author Thiago Uriel M. Garcia
 */
public class InfoMercadoBusiness extends BusinessObject {

    //----[ Atributos e Constantes ]------------------------------------------------	
	
	/** Constante. Representa o hist�rico de sintoma. */
	public static final Class HIST_SINTOMA = HistSintoma.class;

	/** Constante. Representa o hist�rico de causa. */
	public static final Class HIST_CAUSA = HistCausaProblema.class;

	/** Constante. Representa o hist�rico de diagn�stico. */
	public static final Class HIST_DIAGNOSTICO = HistDiagnostico.class;

	/** Constante. Representa o hist�rico de solucao. */
	public static final Class HIST_SOLUCAO = HistAcaoTomada.class;
	
	/** Constante. Quebra de linha para formata��o de hist�rico. */
	private static final String BREAKLINE = "\n";
	
    /** Objeto DAO espec�fico para este objeto de neg�cios. */
    private InformacaoMercadoDao infoMercadoDao;
    
    /** Objeto de neg�cios usado para este objeto de neg�cios. */
    private FaturamentoBusiness faturamentoBo;

    /** Objeto de neg�cios usado para este objeto de neg�cios. */    
    private LoteBusiness loteBo;
    
    /** Objeto de neg�cios usado para este objeto de neg�cios. */    
    private TipoLoteBusiness tipoLoteBo;
    
    /** Objeto de neg�cios usado para este objeto de neg�cios. */    
    private StatusLoteBusiness statusLoteBo;    
    
    /** Objeto de neg�cios usado para este objeto de neg�cios. */    
    private LinhaBusiness linhaBo;        
    
    /** Objeto de neg�cios usado para este objeto de neg�cios. */
    private GarantiaHeaderBusiness garantiaHeaderBo;
    
    /** Objeto de neg�cios usado para este objeto de neg�cios. */
    private GarantiaLineBusiness garantiaLineBo;
    
    /** Objeto de neg�cios usado para este objeto de neg�cios. */
    private ItemBusiness itemBo;    
    /**
	 *  Objeto transactionTemplate para controle de transa��o
	 */
	private TransactionTemplate transactionTemplate;
    
    //----[ M�todos de neg�cio ]----------------------------------------------------
    
    /** Recupera um cliente pelo id.
     *
     *  @param entityId
     *      Refer�ncia da entidade na camada de persist�ncia.
     *  @return
     *      Um objeto <code>InfoMercado</code>.
     *      
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
     *      BusinessRuleException (subclasse de BusinessException) ser� 
     *      lan�ada.
     */
    public InfoMercado get(Serializable entityId) throws BusinessException {

        try {

            return (InfoMercado)this.getInfoMercadoDao().get(InfoMercado.class, entityId);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    }
    
    /** Obt�m uma imagem de uma Informa��o de Mercado
     *  
     *  @param infoMercFotos
     *  	Id da imagem associada a informa��o de mercado. N�o pode ser nulo.
     *  
     *  @return InfoMerFotos
     *  	Uma entidade de InfoMercFotos.
     *  
     *  @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
     *      BusinessRuleException (subclasse de BusinessException) ser� 
     *      lan�ada.
     */
    public InfoMercFotos getInfoMercFotos(Serializable infoMercFotosId) throws BusinessException {
    	
    	 try {

             return (InfoMercFotos)this.getInfoMercadoDao().get(InfoMercFotos.class, infoMercFotosId);

         } catch (DaoException dExp) {

             throw new BusinessException(dExp);

         }
         
    }
    
    /** Obt�m o hist�rico de uma Informa��o de Mercado
     *  
     *  @param historyType
     *  	Tipo de hist�rico a ser obtido de acordo com as
     *  	constantes da classe. N�o pode ser nulo.
     *  @param infoMercadoId
     *  	Identificador de informa��es de mercado.
     *  
     *  @return
     *  	Uma String com o hist�rico atual.
     *  
     *  @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
     *      BusinessRuleException (subclasse de BusinessException) ser� 
     *      lan�ada.
     */
    public String getHistory(Class historyType, Serializable infoMercadoId) throws BusinessException {
    	
    	try {
    		
    		// N�o devemos proceder se a classe-alvo n�o foi definida corretamente.
    		if ( historyType != HIST_CAUSA && historyType != HIST_DIAGNOSTICO && historyType != HIST_SINTOMA && historyType != HIST_SOLUCAO )
    			throw new BusinessException("A classe-alvo de hist�rico n�o existe.");
    		
    		// Obter a listagem de acordo com a classe solicitada.
			List histList     = this.getInfoMercadoDao().listByParameter(historyType, "infoMercadoId", (Integer)infoMercadoId);
			int  listSize     = histList.size();
			int  listPosition = 0;
			
			StringBuffer results    = new StringBuffer();
			Iterator     histListIt = histList.iterator();
			
			// Criamos a string de resultados, utilizando as informa��es
			// da classe de hist�rico solicitada.
			while ( histListIt.hasNext() ) {

				listPosition++;
				Date   date = null;
				String text = null;
				
				if ( historyType == HIST_CAUSA ) {
					
					HistCausaProblema hist = (HistCausaProblema) histListIt.next();
					date = hist.getDataCriacao();
					text = hist.getCausaProblema();
					
				} else if ( historyType == HIST_DIAGNOSTICO ) {
					
					HistDiagnostico hist = (HistDiagnostico) histListIt.next();
					date = hist.getDataCriacao();
					text = hist.getDiagnostico();
					
				} else if ( historyType == HIST_SINTOMA ) {
					
					HistSintoma hist = (HistSintoma) histListIt.next();
					date = hist.getDataCriacao();
					text = hist.getSintoma();
					
				} else if ( historyType == HIST_SOLUCAO) {
					
					HistAcaoTomada hist = (HistAcaoTomada) histListIt.next();
					date = hist.getDataCriacao();
					text = hist.getAcaoTomada();
					
				}
				
				// Formato de uma linha de resultado:
				// [dd/MM/YYYY HH:mm] texto descritivo \n 
				results.append( "[" );
				results.append( DateUtils.applyMask(date, "dd/MM/yyyy HH:mm") );
				results.append( "] " );
				results.append( text );
				results.append( "\n" );
				
				// S� adicionamos a quebra de linha se n�o estivermos na
				// �ltima linha de hist�rico.
				if ( listPosition < listSize-1 )
					results.append( BREAKLINE );
				
			}
			
			return results.toString();
			
		} catch (DaoException dExp) {
			throw new BusinessException(dExp);
		}
    	
    }
    
    /** Obt�m um status de informa��o de mercado populado.
     * 
     *  @param statusId
     *  	Identificador do status.
     *  
     *  @return
     *  	Status populado.
     *  
     *  @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
     *      BusinessRuleException (subclasse de BusinessException) ser� 
     *      lan�ada.
     */
    public StatusInfoMercado getStatus(Serializable statusId) throws BusinessException {
    	
    	try {
    		
    		StatusInfoMercado status = (StatusInfoMercado) this.getInfoMercadoDao().get(StatusInfoMercado.class, statusId);
			return status;
			
		} catch (DaoException dExp) {
		
			throw new BusinessException(dExp);
		
		}
    	
    }    
    
    /** Inicia o processo de armazenamento de uma informa��o de mercado.
     * 
     *  @param infoMercado
     *  @param sintoma
     *  @param causaProblema
     *  @param diagnostico
     *  @param solucao
     *  
     *  @throws BusinessException
     *       Se houverem problemas na camada de dados, uma BusinessException
     *       ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
     *       BusinessRuleException (subclasse de BusinessException) ser� 
     *       lan�ada.
     */
    public InfoMercadoVO save(InfoMercado infoMercado, String sintoma, String causaProblema, String diagnostico, String solucao, Serializable userId, boolean isEdit) throws BusinessException, BusinessRuleException {

		// Valida��o do chassi fornecido.
		if ( !this.getFaturamentoBo().isValidChassi( infoMercado.getChassi() ) )
			throw new BusinessRuleException("cupom.msg.error.chassi");		
		
		final InfoMercado infoMercadoFinal 	= infoMercado;
		final String sintomaFinal 			= sintoma; 
		final String causaProblemaFinal 	= causaProblema; 
		final String diagnosticoFinal 		= diagnostico; 
		final String solucaoFinal 			= solucao; 
		final Serializable userIdFinal 		= userId; 
		final boolean isEditFinal 			= isEdit;
		
		return (InfoMercadoVO)transactionTemplate.execute(
                
	            new TransactionCallback() {
	                
	                public Object doInTransaction(TransactionStatus ts) {
	                	
	                	InfoMercadoVO imVO = new InfoMercadoVO();
						// Valida��es OK. Iniciamos o processo de armazenamento dos 
						// dados recebidos pelo usu�rio.
						try {	
							//System.out.println("Valida��es OK!" + infoMercado.getEntityId());
							
							//System.out.println("Info:" + (!isEdit && !infoMercado.isOnlyInfo()) + " Edit:" + isEdit);
							
							// Devemos primeiro salvar a entidade e obter o registro
							// salvo de forma a termos acesso ao entityId.
							//this.getInfoMercadoDao().makePersistent(infoMercado);
							if( !isEditFinal && !infoMercadoFinal.isOnlyInfo() ) {
								
								Lote lote = createLote(infoMercadoFinal, userIdFinal);
								GarantiaHeader garantia = createGarantia(infoMercadoFinal, lote, userIdFinal);
								GarantiaLine gLine = createGarantiaLine(infoMercadoFinal, garantia, userIdFinal);
								
								//Precisamos validar o Prazo da Garantia/////////////////////////////////////////////////////////////
								List alertasPrazo = garantiaHeaderBo.validatePrazo(true, garantia, faturamentoBo.getByChassi(garantia.getModelo()));
						    	
						    	Iterator itPrazo = alertasPrazo.iterator();
						    	while ( itPrazo.hasNext() ) {
						    		
						    		AlertGarantia alertGarantia_Prazo = (AlertGarantia) itPrazo.next();
						    	
							    	// Verificamos se existe alerta e  se � erro
							    	if ( alertGarantia_Prazo != null ) {
							    		
							    		if ( (alertGarantia_Prazo.getAlertGarantiaKey().indexOf("error") != -1)) {
								    		
							    			throw new BusinessRuleException("Chassi fora do prazo de Garantia!");
								    		
								    	} 
							    	}	
							    	
						    	}
						    	
								/////////////////////////////////////////////////////////////////////////////////////////////////////
								InfoMercado im = getInfoMercadoDao().saveWithGarantia(infoMercadoFinal
										  								  , lote
																		  , garantia
																		  , gLine
																		  , sintomaFinal
																		  , causaProblemaFinal
																		  , diagnosticoFinal
																		  , solucaoFinal
																		  , userIdFinal
																		  , isEditFinal);	
								imVO.setInfoMercado(im);
								imVO.setHasError(false);
								
							} else {
							
								getInfoMercadoDao().save(infoMercadoFinal
															  , sintomaFinal
															  , causaProblemaFinal
															  , diagnosticoFinal
															  , solucaoFinal
															  , userIdFinal
															  , isEditFinal);	
								imVO.setHasError(false);
							}			
			
						} catch ( DaoException dExp ) {
							
							ts.setRollbackOnly();
							System.out.println(" ----------> Rollback - Erro: " + dExp.getMessage());
							imVO.setHasError(true);
							imVO.setMensagem(dExp.getMessage());
							
						} catch ( BusinessRuleException brExp ) {
							
							System.out.println(" ----------> Erro: " + brExp.getMessage());
							imVO.setHasError(true);
							imVO.setMensagem(brExp.getMessage());
							
						} catch ( BusinessException bExp ) {
							
							ts.setRollbackOnly();
							System.out.println(" ----------> Rollback - Erro: " + bExp.getMessage());
							imVO.setHasError(true);
							imVO.setMensagem(bExp.getMessage());
							
						} 
				    	
				    	return imVO;
	                }	                
	            }	                
	        );
    	
    }
    
    public Lote createLote( InfoMercado infoMercado, Serializable userId ) throws BusinessException, BusinessRuleException {
    	
    	// Devemos criar um lote e cadastrar os dados bem como seus
		// relacionamentos pertinentes.
		
		// Este bloco foi comentado em 04/10/2007, conforme orienta��es do Pedro,
		// pois passou a ser tratado por um processo interno que ele desenvolveu
		
		Lote lote = new Lote();
		lote.setTipoLote( this.getTipoLoteBo().get( new Long(2) ) );
		lote.setStatusLote( this.getStatusLoteBo().get( new Long(1) ) );
		lote.setLinha( this.getLinhaBo().get( new Long(2) ) );
		lote.setConcessionaria( infoMercado.getConcessionaria() );
		lote.setDataLote( new Date() );		
		lote.setDataCriacao( new Date() );
		lote.setCriadoPor( (Long) userId );
		lote.setDataAtualizacao( new Date() );
		lote.setAtualizadoPor( (Long) userId );
		
		return lote;
    }
    
    /**
     * 
     * @param infoMercado
     * @param lote
     * @param userId
     * @return
     * @throws BusinessException
     * @throws BusinessRuleException
     */
    public GarantiaHeader createGarantia(InfoMercado infoMercado, Lote lote, Serializable userId )throws BusinessException, BusinessRuleException {
    	
    	try {
    		
	    	// Criamos o header de garantia adequando os dados e refer�ncias
			// necess�rias. Ao t�rmino do processo salvamos o header informando
			// nulo aos alertas do m�todo "save" uma vez que n�o existem alertas
			// a serem salvos.
			GarantiaHeader gHeader = new GarantiaHeader();
			gHeader.setModelo( infoMercado.getChassi() );
			//gHeader.setNumeroOS( infoMercado.getEntityId().toString() );
			
			//Geramos uma condi��o padr�o
			Condicao condicao = new Condicao();
			condicao.setEntityId(new Long(206));
			
			gHeader.setCondicao(condicao);
			gHeader.setSintoma((Sintoma) this.getInfoMercadoDao().getSintoma("00", new Long(2)));
			gHeader.setHorasUso( infoMercado.getHorasUso() );
			gHeader.setDataAberturaOS( infoMercado.getDataInfo() );
			gHeader.setDataFechamentoOS( infoMercado.getDataInfo() );
			gHeader.setLote( lote );
			gHeader.setStatusGarantia( (StatusGarantia) this.getGarantiaHeaderBo().get( StatusGarantia.class, new Long(18) ) );
			gHeader.setDataCriacao( new Date() );
			gHeader.setCriadoPor( (Long) userId );
			gHeader.setDataAtualizacao( new Date() );
			gHeader.setAtualizadoPor( (Long) userId );
		
			return gHeader;  
			
    	} catch (DaoException dExp) {
			
			throw new BusinessException(dExp);
			
		}	
    	
    }
    
    /**
     * 
     * @param infoMercado
     * @param gHeader
     * @param userId
     * @return
     * @throws BusinessException
     * @throws BusinessRuleException
     */
    public GarantiaLine createGarantiaLine( InfoMercado infoMercado, GarantiaHeader gHeader, Serializable userId ) throws BusinessException, BusinessRuleException {
    	
    	try {
    		
    		TabelaPrecoId 	compositeId 	= null;
    		TabelaPreco   	tabelaPreco 	= null;
    		boolean 		hasTabelaPreco 	= false;
    		
    		System.out.println("Item Id:" + infoMercado.getItemId());
    		
    		// Para o preenchimento da linha precisamos obter a tabela de pre�os
			// relacionada ao item da Informa��o de Mercado.
    		Item item = this.getItemBo().getItem(infoMercado.getItemId());
        	
    		if(item != null ) {
    			System.out.println("OrganizationId:" + item.getOrganizationId() + " ItemId: " + item.getEntityId());
    			compositeId = new TabelaPrecoId(item.getOrganizationId(), item.getEntityId() );
    			tabelaPreco = (TabelaPreco) this.getInfoMercadoDao().getByParameter(TabelaPreco.class, "compositeEntityId", compositeId);
    			System.out.println("Tabela de pre�o:" + ( tabelaPreco != null ));
    			if( tabelaPreco != null )
    				hasTabelaPreco = true;
    		} 
    		
    		System.out.println("hasTabelaPreco:" + hasTabelaPreco);
    		
    		if( !hasTabelaPreco ) {
				item = itemBo.getItemDao().getItem(infoMercado.getItemId());    
				if(item != null ) {
					item.setOrganizationId(new Long(91));
					System.out.println("OrganizationId:" + item.getOrganizationId() + " ItemId: " + item.getEntityId());
					compositeId = new TabelaPrecoId(item.getOrganizationId(), item.getEntityId() );
	    			tabelaPreco = (TabelaPreco) this.getInfoMercadoDao().getByParameter(TabelaPreco.class, "compositeEntityId", compositeId);
	    			System.out.println("Tabela de pre�o:" + ( tabelaPreco != null ));
	    			if( tabelaPreco != null )
	    				hasTabelaPreco = true;
	    		}
			}        	
			
			if (item == null )
				throw new BusinessRuleException("N�o foi localizada a Pe�a!");
			
			//TabelaPrecoId compositeId = new TabelaPrecoId(item.getOrganizationId(), infoMercado.getItemId() );
			//TabelaPreco   tabelaPreco = (TabelaPreco) this.getInfoMercadoDao().getByParameter(TabelaPreco.class, "compositeEntityId", compositeId);
			GarantiaLine gLine = new GarantiaLine();
			
			System.out.println("hasTabelaPreco:" + hasTabelaPreco);
			
			if ( hasTabelaPreco ) {
				
				gLine.setCompositeEntityId(gHeader.getEntityId(), new Long(1));
				gLine.setItem( item );
				gLine.setQuantidade(new Integer(1));
				gLine.setEnviaPeca(GarantiaLine.ENVIA_PECA);
				gLine.setCobraPeca(GarantiaLine.COBRAR_PECA);
				gLine.setPecaFaltante("N");
				
				//Recupeamos o fator de garantia
				FatorGarantia fatorG = (FatorGarantia) this.getInfoMercadoDao().getFatorGarantia(infoMercado.getConcessionaria().getUf());
				gLine.setFatorGarantiaId(fatorG.getEntityId());
				gLine.setValorCustoPadraoSP( tabelaPreco.getPrecoGarantia() );
				gLine.setValorPecaGarantia( gLine.getValorCustoPadraoSP() * tabelaPreco.getFatorGarantia() );
				gLine.setValorPrecoSugerido( tabelaPreco.getPrecoGarantia() );
				gLine.setValorPrecoFob( tabelaPreco.getPrecoFOB() );
				gLine.setDataCriacao( new Date() );
				gLine.setCriadoPor( (Long) userId );
				gLine.setDataAtualizacao( new Date() );
				gLine.setAtualizadoPor( (Long) userId );
			} else {
				
				throw new BusinessRuleException("N�o foi localizada a Tabela de Pre�o para a Pe�a:" + item.getItemCode());
				
			}
			
			return gLine;
			
    	} catch (DaoException dExp) {
			
			throw new BusinessException(dExp);
			
		}		
    }
    
    /** Libera uma informa��o de mercado.
     * 
     *  @param infoMercadoId
     *  
     *  @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
     *      BusinessRuleException (subclasse de BusinessException) ser� 
     *      lan�ada.
     */
    public void release(Serializable infoMercadoId, Serializable userId, Concessionaria concessionaria) throws BusinessException, BusinessRuleException {
    	
	    	final Serializable infoMercadoIdFinal = infoMercadoId;
	    	final Serializable userIdFinal		  = userId;
    		transactionTemplate.execute(
                
	            new TransactionCallback() {
	                
	                public Object doInTransaction(TransactionStatus ts) {
    	    	
				    	try {
				    		
				    		// Obter uma entidade de Informa��es de Mercado utilizando o 
				    		// identificador fornecido ao m�todo.
				    		// 
				    		// Feito isso existem duas valida��es a serem feitas:
				    		//
				    		//   1. Caso n�o seja encontrada, levantar uma businessException 
				    		//      informando o problema.
				    		//   2. Caso seja encontrada ela n�o deve estar com status "3".
							InfoMercado infoMercado = (InfoMercado) getInfoMercadoDao().get(InfoMercado.class, infoMercadoIdFinal);
							
							if ( infoMercado == null)
								throw new BusinessRuleException("form.msg.invalid");
							
							if ( infoMercado.getStatusInfoMercado().getEntityId().equals( new Long(2) ) )
								throw new BusinessRuleException("infoMercado.error.msg.released");
							
							// Atualizamos o status para "2" e atualizamos a entidade de
							// Informa��es de Mercado na camada de persist�ncia.
							// 
							// Uma vez que n�o desejamos atualizar hist�ricos, fornecemos
							// valores nulos para estes campos (o m�todo "save" trata estes
							// campos e quando nulos, n�o efetua o armazenamento de hist�rico).
							infoMercado.setStatusInfoMercado( getStatus( new Long(2) ) );
							infoMercado.setDataAtualizacao( new Date() );
							infoMercado.setAtualizadoPor( (Long) userIdFinal );
							save(infoMercado, null, null, null, null, userIdFinal, true);
							
							if(infoMercado.getHasGarantia()) {
								
								GarantiaHeader garantia = infoMercado.getGarantia();
								if ( garantia.getStatusGarantia().getEntityId().equals(StatusGarantia.STATUS_DIGITACAO)) {
									StatusGarantia statusGarantia = new StatusGarantia();
									statusGarantia.setEntityId(StatusGarantia.STATUS_AGUARDANDO_APROVACAO_INFO_MERCADO);
							    	garantia.setStatusGarantia(statusGarantia);	
							    	ControllerHelper.prepare(garantia,(Long)userIdFinal);
									garantiaHeaderBo.saveEntity(garantia);
								}
							}
											    		
				    	} catch ( DaoException dExp ) {
							
							ts.setRollbackOnly();
							System.out.println(" ----------> Rollback - Erro: " + dExp.getMessage());
							
						} catch ( BusinessException bExp ) {
							
							ts.setRollbackOnly();
							System.out.println(" ----------> Rollback - Erro: " + bExp.getMessage());
							
						} 
				    	
				    	return null;
	                }
	                
	            }
	                
	        );
    	
    } 
    
    /** Atualiza os hist�ricos de uma informa��o de mercado.
     * 
     *  @param infoMercado
     *  @param sintoma
     *  @param causaProblema
     *  @param diagnostico
     *  @param solucao
     *  
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
     *      BusinessRuleException (subclasse de BusinessException) ser� 
     *      lan�ada.
     */
    public void updateHist(InfoMercado infoMercado, String sintoma, String causaProblema, String diagnostico, String solucao, Serializable userId) throws BusinessException {
    	
		try {
		
			// Armazenamos itens criados para "Sintoma".
			if ( sintoma != null ) {
				
				StringTokenizer sintomaST = new StringTokenizer(sintoma, "\n");
				while ( sintomaST.hasMoreTokens() ) {
					
					String token = sintomaST.nextToken();
					String tokenText = token.substring( token.indexOf("]") + 2 );
					String tokenDate = token.substring( token.indexOf("[") + 1, token.indexOf("]") );
					
					Date date = DateUtils.format(tokenDate, "dd/MM/yyyy HH:mm");
					
					HistSintoma histSintoma = new HistSintoma();
					histSintoma.setInfoMercadoId( new Long( infoMercado.getEntityId().toString() ) );
					histSintoma.setSintoma( tokenText );
					histSintoma.setDataInicio( date );
					histSintoma.setCriadoPor( (Long) userId );
					histSintoma.setDataCriacao( date );
					
					this.getInfoMercadoDao().makePersistent(histSintoma);
	
				}
				
			}
			
			// Armazenamos itens criados para "CausaProblema".
			if ( causaProblema != null ) {
				
				StringTokenizer causaProblemaST = new StringTokenizer(causaProblema, "\n");
				while ( causaProblemaST.hasMoreTokens() ) {
					
					String token = causaProblemaST.nextToken();
					String tokenText = token.substring( token.indexOf("]") + 2 );
					String tokenDate = token.substring( token.indexOf("[") + 1, token.indexOf("]") );
					
					Date date = DateUtils.format(tokenDate, "dd/MM/yyyy HH:mm");
					
					HistCausaProblema histCausaProblema = new HistCausaProblema();
					histCausaProblema.setInfoMercadoId( new Long( infoMercado.getEntityId().toString() ) );
					histCausaProblema.setCausaProblema( tokenText );
					histCausaProblema.setDataInicio( date );
					histCausaProblema.setCriadoPor( (Long) userId );
					histCausaProblema.setDataCriacao( date );
					
					this.getInfoMercadoDao().makePersistent(histCausaProblema);
	
				}
				
			}
			
			// Armazenamos itens criados para "Diagnostico".
			if ( diagnostico != null ) {
				
				StringTokenizer diagnosticoST = new StringTokenizer(diagnostico, "\n");
				while ( diagnosticoST.hasMoreTokens() ) {
					
					String token = diagnosticoST.nextToken();
					String tokenText = token.substring( token.indexOf("]") + 2 );
					String tokenDate = token.substring( token.indexOf("[") + 1, token.indexOf("]") );
					
					Date date = DateUtils.format(tokenDate, "dd/MM/yyyy HH:mm");
					
					HistDiagnostico histDiagnostico = new HistDiagnostico();
					histDiagnostico.setInfoMercadoId( new Long( infoMercado.getEntityId().toString() ) );
					histDiagnostico.setDiagnostico( tokenText );
					histDiagnostico.setDataInicio( date );
					histDiagnostico.setCriadoPor( (Long) userId );
					histDiagnostico.setDataCriacao( date );
					
					this.getInfoMercadoDao().makePersistent(histDiagnostico);
	
				}
				
			}
			
			// Armazenamos itens criados para "Solucao".
			if ( solucao != null ) {
				
				StringTokenizer solucaoST = new StringTokenizer(solucao, "\n");
				while ( solucaoST.hasMoreTokens() ) {
					
					String token = solucaoST.nextToken();
					String tokenText = token.substring( token.indexOf("]") + 2 );
					String tokenDate = token.substring( token.indexOf("[") + 1, token.indexOf("]") );
					
					Date date = DateUtils.format(tokenDate, "dd/MM/yyyy HH:mm");
					
					HistAcaoTomada histAcaoTomada = new HistAcaoTomada();
					histAcaoTomada.setInfoMercadoId( new Long( infoMercado.getEntityId().toString() ) );
					histAcaoTomada.setAcaoTomada( tokenText );
					histAcaoTomada.setDataInicio( date );
					histAcaoTomada.setCriadoPor( (Long) userId );
					histAcaoTomada.setDataCriacao( date );
					
					this.getInfoMercadoDao().makePersistent(histAcaoTomada);
	
				}
			
			}
			
		} catch (DaoException dExp) {
			
			throw new BusinessException( dExp );

		}
		
    }

    /** Lista todos as InfoMercado existentes no banco de dados.
     *  @return
     *      Um objeto <code>List</code> contendo todos os 
     *      clientes persistentes na aplica��o.
     *      
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
     *      BusinessRuleException (subclasse de BusinessException) ser� 
     *      lan�ada.
     */
    public List list() throws BusinessException {

        try {

            return this.getInfoMercadoDao().listAll(InfoMercado.class);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    }    
    
    /** Lista as informa��es de mercado 
     * 
     * @param concessionaria
     * @return List
     * 
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
     *      BusinessRuleException (subclasse de BusinessException) ser� 
     *      lan�ada.
     */
    public List listByConcessionaria(Concessionaria concessionaria)throws BusinessException {
    	
    	try {

            return this.getInfoMercadoDao().listByConcessionaria(concessionaria);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }
    	
    }
    
    /** Lista as informa��es de mercado 
     * 
     *  @param concessionaria
     *  @param filterType
     *  @param filterValue
     *  @return List
     * 
     *  @throws BusinessException
     *       Se houverem problemas na camada de dados, uma BusinessException
     *       ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
     *       BusinessRuleException (subclasse de BusinessException) ser� 
     *       lan�ada.
     */
    public List listByConcessionaria(Concessionaria concessionaria, int filterType, String filterValue)throws BusinessException {
    	
    	try {

            return this.getInfoMercadoDao().listByConcessionaria(concessionaria, filterType, filterValue);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }
    	
    }    
    
    /** Remove uma informa��o de mercado armazenada.
     * 
     *  @param infoMercadoId
     *  	Id da entidade a ser removida.
     *  
     *  @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
     *      BusinessRuleException (subclasse de BusinessException) ser� 
     *      lan�ada.
     */
    public void delete(Serializable infoMercadoId, Serializable usuarioId) throws BusinessException {
    	
    	final Serializable infoMercadoIdFinal = infoMercadoId;
    	final Serializable usuarioIdFinal	  = usuarioId;
    	
    	transactionTemplate.execute(
    			
    			new TransactionCallback() {
            
    				public Object doInTransaction(TransactionStatus ts) {
    	
			    	try {
			    		
			    		InfoMercado infoMercado = (InfoMercado)getInfoMercadoDao().get(InfoMercado.class, infoMercadoIdFinal);
			    		
			    		System.out.println("Removendo Bus Info:" + infoMercado.getEntityId() );
			    		System.out.println("Tem Info:" + (infoMercado != null ? infoMercado.getHasGarantia(): false));
			    		// Removemos a Garantia, quando existir
						if ( infoMercado != null && infoMercado.getHasGarantia() ) {
							
							InfoMercGarantia infoMercGarantia = new InfoMercGarantia();
							infoMercGarantia.setInfoMercado(infoMercado);
							infoMercGarantia.setGarantia(infoMercado.getGarantia());
							infoMercGarantia = garantiaHeaderBo.getInfoMercGarantia(infoMercGarantia);
							ControllerHelper.prepare(infoMercGarantia,(Long)usuarioIdFinal);
							getInfoMercadoDao().makeTransient(infoMercGarantia);		    						
						}
						
						ControllerHelper.prepare(infoMercado,(Long)usuarioIdFinal);
			    		getInfoMercadoDao().remove( infoMercado );
				
			    	} catch ( DaoException dExp ) {
						
						ts.setRollbackOnly();
						System.out.println(" ----------> Rollback - Erro: " + dExp.getMessage());
						
					} catch ( BusinessException bExp ) {
						
						ts.setRollbackOnly();
						System.out.println(" ----------> Rollback - Erro: " + bExp.getMessage());
						
					} 
			    	
			    	return null;
    			}
            
    		}
    	);
    	
    }
    
    /** Remove uma imagem de uma informa��o de mercado armazenada.
     * 
     *  @param infoMercFotosId
     *  	Id da entidade a ser removida.
     *  
     *  @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
     *      BusinessRuleException (subclasse de BusinessException) ser� 
     *      lan�ada.
     */
    public void removeImage(Serializable infoMercFotosId) throws BusinessException {
    	
    	try {
    		
			this.getInfoMercadoDao().makeTransient( this.getInfoMercadoDao().get(InfoMercFotos.class, infoMercFotosId) );
			
		} catch (DaoException dExp) {
			throw new BusinessException(dExp);
		}
    	
    }
    
    /** Retorna a lista de tipos de produto no banco
     * 
     * @return List
     * 
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
     *      BusinessRuleException (subclasse de BusinessException) ser� 
     *      lan�ada.
     */
    public List listTipoProdutoInfo()throws BusinessException {
    	
    	try {

            return this.getInfoMercadoDao().listAll(TipoProdutoInfo.class);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }
    	
    }
    
    /** Retorna a lista de tipos de gasolinas no banco
     * 
     * @return List
     * 
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
     *      BusinessRuleException (subclasse de BusinessException) ser� 
     *      lan�ada.
     */
    public List listTipoGasolina()throws BusinessException {
    	
    	try {

            return this.getInfoMercadoDao().listAll(TipoGasolina.class);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }
    	
    }
    
    /** Retorna a lista de tipos de uso no banco
     * 
     *  @return List
     * 
     *  @throws BusinessException
     *       Se houverem problemas na camada de dados, uma BusinessException
     *       ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
     *       BusinessRuleException (subclasse de BusinessException) ser� 
     *       lan�ada.
     */
    public List listTipoUso()throws BusinessException {
    	
    	try {

            return this.getInfoMercadoDao().listAll(TipoUsoBarco.class);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }
    	
    }
    
    /** Retorna um item de acordo com o id
     * 
     *  @param itemId
     *  
     *  @return uma entidade de Item
     * 
     *  @throws BusinessException
     *       Se houverem problemas na camada de dados, uma BusinessException
     *       ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
     *       BusinessRuleException (subclasse de BusinessException) ser� 
     *       lan�ada.
     */
    public Item getItemById(Long itemId)throws BusinessException {
		
    	Item item = null;
    	
    	item = this.getItemBo().getByOrg(itemId, new Long(89));
    	
    	if ( item == null ) {
    		
    		item = this.getItemBo().getByOrg(itemId, new Long(91));    		
    		
    	}
    	
        return item;
        
    }
    
    /** Retorna o conte�do do campo content de uma entidade InforMercFotos
     * 
     * @param infoMercFotosId
     * @return Blob
     * @throws BusinessException
     */
    public Blob getContentFile( Long infoMercFotosId ) throws BusinessException {
    	
    	try {

            return this.getInfoMercadoDao().getContentFile( infoMercFotosId );

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }
    	
    	
    	
    	
    }

    //----[ M�todos getter e setter ]-----------------------------------------------    
    
	/** M�todo getter para o campo "faturamentoBo".
	 *  
	 *  @return O valor atual de "faturamentoBo".
	 */
	public FaturamentoBusiness getFaturamentoBo() {
		return faturamentoBo;
	}

	/** M�todo setter para o campo "faturamentoBo".
	 * 
	 *  @param faturamentoBo 
	 *    O novo valor para "faturamentoBo".
	 */
	public void setFaturamentoBo(FaturamentoBusiness faturamentoBo) {
		this.faturamentoBo = faturamentoBo;
	}

	/** M�todo getter para o campo "infoMercadoDao".
	 *  
	 *  @return O valor atual de "infoMercadoDao".
	 */
	public InformacaoMercadoDao getInfoMercadoDao() {
		return infoMercadoDao;
	}

	/** M�todo setter para o campo "infoMercadoDao".
	 * 
	 *  @param infoMercadoDao 
	 *    O novo valor para "infoMercadoDao".
	 */
	public void setInfoMercadoDao(InformacaoMercadoDao infoMercadoDao) {
		this.infoMercadoDao = infoMercadoDao;
	}

	/** M�todo getter para o campo "loteBo".
	 *  
	 *  @return O valor atual de "loteBo".
	 */
	public LoteBusiness getLoteBo() {
		return loteBo;
	}

	/** M�todo setter para o campo "loteBo".
	 * 
	 *  @param loteBo 
	 *    O novo valor para "loteBo".
	 */
	public void setLoteBo(LoteBusiness loteBo) {
		this.loteBo = loteBo;
	}

	/** M�todo getter para o campo "statusLoteBo".
	 *  
	 *  @return O valor atual de "statusLoteBo".
	 */
	public StatusLoteBusiness getStatusLoteBo() {
		return statusLoteBo;
	}

	/** M�todo setter para o campo "statusLoteBo".
	 * 
	 *  @param statusLoteBo 
	 *    O novo valor para "statusLoteBo".
	 */
	public void setStatusLoteBo(StatusLoteBusiness statusLoteBo) {
		this.statusLoteBo = statusLoteBo;
	}

	/** M�todo getter para o campo "tipoLoteBo".
	 *  
	 *  @return O valor atual de "tipoLoteBo".
	 */
	public TipoLoteBusiness getTipoLoteBo() {
		return tipoLoteBo;
	}

	/** M�todo setter para o campo "tipoLoteBo".
	 * 
	 *  @param tipoLoteBo 
	 *    O novo valor para "tipoLoteBo".
	 */
	public void setTipoLoteBo(TipoLoteBusiness tipoLoteBo) {
		this.tipoLoteBo = tipoLoteBo;
	}

	/** M�todo getter para o campo "linhaBo".
	 *  
	 *  @return O valor atual de "linhaBo".
	 */
	public LinhaBusiness getLinhaBo() {
		return linhaBo;
	}

	/** M�todo setter para o campo "linhaBo".
	 * 
	 *  @param linhaBo 
	 *    O novo valor para "linhaBo".
	 */
	public void setLinhaBo(LinhaBusiness linhaBo) {
		this.linhaBo = linhaBo;
	}

	/** M�todo getter para o campo "itemBo".
	 *  
	 *  @return O valor atual de "itemBo".
	 */
	public ItemBusiness getItemBo() {
		return itemBo;
	}

	/** M�todo setter para o campo "itemBo".
	 * 
	 *  @param itemBo 
	 *    O novo valor para "itemBo".
	 */
	public void setItemBo(ItemBusiness itemBo) {
		this.itemBo = itemBo;
	}

	/** M�todo getter para o campo "garantiaHeaderBo".
	 *  
	 *  @return O valor atual de "garantiaHeaderBo".
	 */
	public GarantiaHeaderBusiness getGarantiaHeaderBo() {
		return garantiaHeaderBo;
	}

	/** M�todo getter para o campo "garantiaLineBo".
	 *  
	 *  @return O valor atual de "garantiaLineBo".
	 */
	public GarantiaLineBusiness getGarantiaLineBo() {
		return garantiaLineBo;
	}

	/** M�todo setter para o campo "garantiaHeaderBo".
	 * 
	 *  @param garantiaHeaderBo 
	 *    O novo valor para "garantiaHeaderBo".
	 */
	public void setGarantiaHeaderBo(GarantiaHeaderBusiness garantiaHeaderBo) {
		this.garantiaHeaderBo = garantiaHeaderBo;
	}

	/** M�todo setter para o campo "garantiaLineBo".
	 * 
	 *  @param garantiaLineBo 
	 *    O novo valor para "garantiaLineBo".
	 */
	public void setGarantiaLineBo(GarantiaLineBusiness garantiaLineBo) {
		this.garantiaLineBo = garantiaLineBo;
	}

	/** M�todo getter para a propriedade transactionTemplate
	 *
	 *  @return TransactionTemplate de transactionTemplate
	 */
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	/** M�todo setter para a propriedade transactionTemplate
	 *
	 * @param transactionTemplate TransactionTemplate
	 */
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
}
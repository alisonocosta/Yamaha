/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......NotaFiscal.java
 *   .: Criação.....10 de maio, 07:40
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "NotaFiscal".
 */
package br.com.yamaha.sistemagarantia.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.model.EntityObject;
import br.com.resource.infra.utils.NumberUtils;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "NotaFiscal" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class NotaFiscal extends EntityObject {
	
	//----[ Atributos de classe e instância ]-----------------------------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;  
    
    /** Constante para o id do tipo de nota de peça*/
    public static final Long TIPO_NOTA_PECA 			= new Long(1);
    
    /** Constante para o id do tipo de nota de serviço*/
    public static final Long TIPO_NOTA_SERVICO 			= new Long(2);
    
    /** Constante para o id do tipo de nota de conjugada*/
    public static final Long TIPO_NOTA_CONJUGADA 		= new Long(3);
    
    /** Constante para o id do tipo de nota de remessa*/
    public static final Long TIPO_NOTA_REMESSA 			= new Long(4);
    
    /** Constante para o id do tipo de nota de serviço de terceiro*/
    public static final Long TIPO_NOTA_SERVICO_TERCEIRO	= new Long(8);
    
    /** Valor da série da nota fiscal */
    private String serieNF;
    
    /** Destinatário da nota fiscal */
    private String destinatario;
    
    /** Emissor da nota fiscal */
    private String emissor;
    
    /** Número da nota fiscal */
    private Long numeroNF;
    
    /** Data de emisão da nota fiscal */
    private Date dataNF;
    
    /** Valor de Serviço da Nota fiscal */
    private Double serviceValueNF;
    
    /** Valor de Peças da Nota fiscal */
    private Double pecaValueNF;
    
    /** Valor de icms da Nota fiscal */
    private Double icmsValueNF;
    
    /** Número de complemento da nota fiscal */
    private Long NumeroComplNF;
    
    /** Data do complemento da nota fiscal */
    private Date dataComplNF;
    
    /** Série do complemento da nota fiscal */
    private String serieComplNF;
    
    /** Valor de Serviço do complemento da nota fiscal */
    private Double serviceValueNF_COMP;
    
    /** Valor de Peças do complemento da nota fiscal */
    private Double pecaValueNF_COMP;
    
    /** Valor de icms do complemento da nota fiscal */
    private Double icmsValueNF_COMP;
    
    /** Data de liberação da nota fiscal */
    private Date dataLiberacao;
    
    /** Data de pagamento da nota fiscal */
    private Date dataPagamento;
    
    /** Data de adiantamento */
    private Date dataAdiantamento;
    
    /** Id do tipo da nota fiscal */
    private Long tipoNFId;
    
    /** Entidade Lote */
    private Lote lote;
    
    /** Entidade Concessionária da nota fiscal */
    private Concessionaria concessionaria;
    
    /** Cupons de uma nota fiscal */
    private Collection cupons;
    
    /** Garantias de uma nota fiscal */
    private Collection garantias;    
    
    /** Construtor Padrão */
    public NotaFiscal() {    	
    	
    	garantias = new ArrayList();
    	cupons    = new ArrayList();
    	
    }
    
    //	----[ Métodos Getter ]---------------------------------------------------

	/** Método getter para a propriedade concessionaria
	 * 
	 *  @return Concessionaria
	 *
	 */
	public Concessionaria getConcessionaria() {
		return concessionaria;
	}

	/** Método getter para a propriedade dataAdiantamento
	 * 
	 *  @return Date
	 *
	 */
	public Date getDataAdiantamento() {
		return dataAdiantamento;
	}

	/** Método getter para a propriedade dataComplNF
	 * 
	 *  @return Date
	 *
	 */
	public Date getDataComplNF() {
		return dataComplNF;
	}

	/** Método getter para a propriedade dataNF
	 * 
	 *  @return Date
	 *
	 */
	public Date getDataNF() {
		return dataNF;
	}

	/** Método getter para a propriedade garantias
	 * 
	 *  @return Collection
	 *
	 */
	public Collection getGarantias() {
		return garantias;
	}	

	/** Método getter para a propriedade dataPagamento
	 * 
	 *  @return Date
	 *
	 */
	public Date getDataPagamento() {
		return dataPagamento;
	}

	/** Método getter para a propriedade NumeroComplNF
	 * 
	 *  @return Long
	 *
	 */
	public Long getNumeroComplNF() {
		return NumeroComplNF;
	}

	/** Método getter para a propriedade numeroNF
	 * 
	 *  @return Long
	 *
	 */
	public Long getNumeroNF() {
		return numeroNF;
	}

	/** Método getter para a propriedade serieComplNF
	 * 
	 *  @return String
	 *
	 */
	public String getSerieComplNF() {
		return serieComplNF;
	}

	/** Método getter para a propriedade serieNF
	 * 
	 *  @return String
	 *
	 */
	public String getSerieNF() {
		return serieNF;
	}

	/** Método getter para a propriedade tipoNFId
	 * 
	 *  @return Long
	 *
	 */
	public Long getTipoNFId() {
		return tipoNFId;
	}
	
	/** Método getter para a propriedade cupons
	 * 
	 *  @return Collection
	 *
	 */
	public Collection getCupons() {
		return cupons;
	}
	
	/** Método getter para a propriedade lote
	 *
	 *  @return Lote de lote
	 */
	public Lote getLote() {
		return lote;
	}

	/** Método getter para a propriedade destinatario
	 * 
	 *  @return String
	 *
	 */
	public String getDestinatario() {
		return destinatario;
	}

	/** Método getter para a propriedade emissor
	 * 
	 *  @return String
	 *
	 */
	public String getEmissor() {
		return emissor;
	}
	
	//	----[ Métodos Setter ]---------------------------------------------------

	/** Método setter para a propriedade garantias
	 *
	 * @param garantias 
	 *           <code>Collection</code> a ser designado para garantias.
	 * 
	 */
	public void setGarantias(Collection garantias) {
		this.garantias = garantias;
	}
	
	/** Método setter para a coleção de garantias, insere um objeto na coleção
	 *
	 * @param garantiaHeader 
	 *           <code>GarantiaHeader</code> a ser designado para garantias.
	 * 
	 */
	public void setGarantia(GarantiaHeader garantiaHeader) {
		this.garantias.add(garantiaHeader);
	}
	
	/** Método setter para a propriedade concessionaria
	 *
	 * @param concessionaria 
	 *           <code>Concessionaria</code> a ser designado para concessionaria.
	 * 
	 */
	public void setConcessionaria(Concessionaria concessionaria) {
		this.concessionaria = concessionaria;
	}

	/** Método setter para a propriedade dataAdiantamento
	 *
	 * @param dataAdiantamento 
	 *           <code>Date</code> a ser designado para dataAdiantamento.
	 * 
	 */
	public void setDataAdiantamento(Date dataAdiantamento) {
		this.dataAdiantamento = dataAdiantamento;
	}

	/** Método setter para a propriedade dataComplNF
	 *
	 * @param dataComplNF 
	 *           <code>Date</code> a ser designado para dataComplNF.
	 * 
	 */
	public void setDataComplNF(Date dataComplNF) {
		this.dataComplNF = dataComplNF;
	}

	/** Método setter para a propriedade dataNF
	 *
	 * @param dataNF 
	 *           <code>Date</code> a ser designado para dataNF.
	 * 
	 */
	public void setDataNF(Date dataNF) {
		this.dataNF = dataNF;
	}

	/** Método setter para a propriedade dataPagamento
	 *
	 * @param dataPagamento 
	 *           <code>Date</code> a ser designado para dataPagamento.
	 * 
	 */
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	/** Método getter para a propriedade dataLiberacao
	 * 
	 *  @return Date
	 *
	 */
	public Date getDataLiberacao() {
		return dataLiberacao;
	}

	/** Método setter para a propriedade dataLiberacao
	 *
	 * @param dataLiberacao 
	 *           <code>Date</code> a ser designado para dataLiberacao.
	 * 
	 */
	public void setDataLiberacao(Date dataLiberacao) {
		this.dataLiberacao = dataLiberacao;
	}

	/** Método setter para a propriedade NumeroComplNF
	 *
	 * @param numeroComplNF 
	 *           <code>Long</code> a ser designado para numeroComplNF.
	 * 
	 */
	public void setNumeroComplNF(Long numeroComplNF) {
		NumeroComplNF = numeroComplNF;
	}

	/** Método setter para a propriedade numeroNF
	 *
	 * @param numeroNF 
	 *           <code>Long</code> a ser designado para numeroNF.
	 * 
	 */
	public void setNumeroNF(Long numeroNF) {
		this.numeroNF = numeroNF;
	}

	/** Método setter para a propriedade serieComplNF
	 *
	 * @param serieComplNF 
	 *           <code>String</code> a ser designado para serieComplNF.
	 * 
	 */
	public void setSerieComplNF(String serieComplNF) {
		this.serieComplNF = serieComplNF;
	}

	/** Método setter para a propriedade serieNF
	 *
	 * @param serieNF 
	 *           <code>String</code> a ser designado para serieNF.
	 * 
	 */
	public void setSerieNF(String serieNF) {
		this.serieNF = serieNF;
	}

	/** Método setter para a propriedade tipoNFId
	 *
	 * @param tipoNFId 
	 *           <code>Long</code> a ser designado para tipoNFId.
	 * 
	 */
	public void setTipoNFId(Long tipoNFId) {
		this.tipoNFId = tipoNFId;
	}

	/** Método setter para a propriedade cupons
	 *
	 * @param cupons 
	 *           <code>Collection</code> a ser designado para cupons.
	 * 
	 */
	public void setCupons(Collection cupons) {
		this.cupons = cupons;
	}
	
	/** Método getter para a propriedade icmsValueNF
	 * 
	 *  @return Double
	 *
	 */
	public Double getIcmsValueNF() {
		return icmsValueNF;
	}

	/** Método getter para a propriedade icmsValueNF_COMP
	 * 
	 *  @return Double
	 *
	 */
	public Double getIcmsValueNF_COMP() {
		return icmsValueNF_COMP;
	}

	/** Método getter para a propriedade pecaValueNF
	 * 
	 *  @return Double
	 *
	 */
	public Double getPecaValueNF() {
		return pecaValueNF;
	}

	/** Método getter para a propriedade pecaValueNF_COMP
	 * 
	 *  @return Double
	 *
	 */
	public Double getPecaValueNF_COMP() {
		return pecaValueNF_COMP;
	}

	/** Método getter para a propriedade serviceValueNF
	 * 
	 *  @return Double
	 *
	 */
	public Double getServiceValueNF() {
		return serviceValueNF;
	}

	/** Método getter para a propriedade serviceValueNF_COMP
	 * 
	 *  @return Double
	 *
	 */
	public Double getServiceValueNF_COMP() {
		return serviceValueNF_COMP;
	}

	/** Método setter para a propriedade icmsValueNF
	 *
	 * @param icmsValueNF 
	 *           <code>Double</code> a ser designado para icmsValueNF.
	 * 
	 */
	public void setIcmsValueNF(Double icmsValueNF) {
		this.icmsValueNF = icmsValueNF;
	}

	/** Método setter para a propriedade icmsValueNF_COMP
	 *
	 * @param icmsValueNF_COMP 
	 *           <code>Double</code> a ser designado para icmsValueNF_COMP.
	 * 
	 */
	public void setIcmsValueNF_COMP(Double icmsValueNF_COMP) {
		this.icmsValueNF_COMP = icmsValueNF_COMP;
	}

	/** Método setter para a propriedade pecaValueNF
	 *
	 * @param pecaValueNF 
	 *           <code>Double</code> a ser designado para pecaValueNF.
	 * 
	 */
	public void setPecaValueNF(Double pecaValueNF) {
		this.pecaValueNF = pecaValueNF;
	}

	/** Método setter para a propriedade pecaValueNF_COMP
	 *
	 * @param pecaValueNF_COMP 
	 *           <code>Double</code> a ser designado para pecaValueNF_COMP.
	 * 
	 */
	public void setPecaValueNF_COMP(Double pecaValueNF_COMP) {
		this.pecaValueNF_COMP = pecaValueNF_COMP;
	}

	/** Método setter para a propriedade serviceValueNF
	 *
	 * @param serviceValueNF 
	 *           <code>Double</code> a ser designado para serviceValueNF.
	 * 
	 */
	public void setServiceValueNF(Double serviceValueNF) {
		this.serviceValueNF = serviceValueNF;
	}

	/** Método setter para a propriedade serviceValueNF_COMP
	 *
	 * @param serviceValueNF_COMP 
	 *           <code>Double</code> a ser designado para serviceValueNF_COMP.
	 * 
	 */
	public void setServiceValueNF_COMP(Double serviceValueNF_COMP) {
		this.serviceValueNF_COMP = serviceValueNF_COMP;
	}

	/** Método setter para a propriedade lote
	 *
	 * @param lote Lote
	 */
	public void setLote(Lote lote) {
		this.lote = lote;
	}

	/** Método setter para a propriedade destinatario
	 *
	 * @param destinatario 
	 *           <code>String</code> a ser designado para destinatario.
	 * 
	 */
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	/** Método setter para a propriedade emissor
	 *
	 * @param emissor 
	 *           <code>String</code> a ser designado para emissor.
	 * 
	 */
	public void setEmissor(String emissor) {
		this.emissor = emissor;
	}

	/** Método para realcionar um cupom com a nota fiscal 
	 * 
	 * @param cupom
	 *  <code>Cupom</code> Entidade Cupom 
	 * 
	 * @throws BusinessException
	 * 
	 */
	public void addCupom(Cupom cupom) throws BusinessException{
		
		if ( cupom != null ) {
			
			this.cupons.add(cupom);
			
		} else
			
			throw new BusinessException("O Objeto enviado é Nulo!" );
		
	}    
	
	/** Método para realcionar uma SG com a nota fiscal 
	 * 
	 * @param garantia
	 *  <code>GarantiaHeader</code> Entidade Garantia 
	 * 
	 * @throws BusinessException
	 * 
	 */
	public void addGarantia(GarantiaHeader garantia) throws BusinessException{
		
		if ( garantia != null ) {
			
			this.garantias.add(garantia);
			
		} else
			
			throw new BusinessException("O Objeto enviado é Nulo!" );
		
	}    
	
	//	----[ Métodos Setter ]---------------------------------------------------
	
	/**
	 * Retorna o valor total da nota formatada para Moeda 
	 * 
	 * @return String  ########0,00
	 * 
	 */
	public String getVlTotalStr() {
		
		double total = 0;
		
		if ( this.pecaValueNF != null )
			total += this.pecaValueNF.doubleValue();
		if ( this.serviceValueNF != null )
			total += this.serviceValueNF.doubleValue();
		
		return NumberUtils.formatNumberCurrencyMil( total );
	}	
	
	/** Retorna o Tipo da Nota 
	 * 
	 * @return String tipo da Nota
	 */
	public String getTipoNF() {
		
		String tipoNota = new String();
		
		if ( this.tipoNFId != null ) {
		
			if (this.tipoNFId.equals(TIPO_NOTA_PECA))
				tipoNota = "PEÇAS";
			else if (this.tipoNFId.equals(TIPO_NOTA_SERVICO)) 
				tipoNota = "SERVIÇOS";
			else if (this.tipoNFId.equals(TIPO_NOTA_CONJUGADA)) 
				tipoNota = "CONJUGADA";
			else if (this.tipoNFId.equals(TIPO_NOTA_REMESSA)) 
				tipoNota = "REMESSA";
			else if (this.tipoNFId.equals(TIPO_NOTA_SERVICO_TERCEIRO)) 
				tipoNota = "TERCEIRO";	
			else
				tipoNota = "NÃO IDENTIFICADO";
			
		} else {			
			tipoNota = "NÃO IDENTIFICADO";
		}
		
		return tipoNota;
		
	}

}

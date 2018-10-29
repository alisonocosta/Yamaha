/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......GarantiaLine.java
 *   .: Criação.....01 de junho, 11:31
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "GarantiaLine".
 */
package br.com.yamaha.sistemagarantia.model;

import java.io.Serializable;

import br.com.resource.infra.model.EntityObject;
import br.com.resource.infra.utils.NumberUtils;
import br.com.yamaha.sistemagarantia.model.id.GarantiaLineId;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "GarantiaLine" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class GarantiaLine extends EntityObject {

    //----[ Atributos de classe e instância ]-----------------------------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;   
    
    public static final String COBRAR_PECA 	= "S";
    
    public static final String ENVIA_PECA 	= "S";
    
    public static final String COBRAR_FORNECEDOR_SIM = "S";
    
    public static final String COBRAR_FORNECEDOR_NAO = "N";
    
    private GarantiaLineId compositeEntityId;
    
    /** Identificador da linha dentro da solicitação de garantia. */
    private Long lineId;
    
    /** Quantidade da peça utilizada. */
    private Integer quantidade;
    
    /** Flag indicativo que a peça com defeito foi enviada para a Yamaha. */
    private String enviaPeca;
    
    /** Flag indicativo que a concessionária cobrara a peça. */
    private String cobraPeca;
    
    /** Flag indicativo que a Yamaha cobrara a peça. */
    private String cobraPecaFornecedor;
    
    /** Flag indicativo que esta faltando peça no produto. */
    private String pecaFaltante;
    
    /** Flag indicativo da peça causadora. */
    private String pecaCausadora;
    
    /** Valor do custo do item em São Paulo. */
    private double valorCustoPadraoSP;
    
    /** Valor da peça acrescido fator de garantia. */
    private double valorPecaGarantia;
    
    /** Valor da peça. */
    private double valorPrecoSugerido;
    
    /** valorPrecoFob. */
    private double valorPrecoFob;
    
    /** valorRemessa. */
    private Double valorRemessa;
    
    /** Entidade da nota fiscal de Peça. */
    private NotaFiscal notaFiscalPeca;
    
    /** Identificador único do fator de garantia. */
    private Serializable fatorGarantiaId;
    
    /** Entidade da nota fiscal de remessa. */
    private NotaFiscal notaFiscalRemessa;
    
    /** Objeto de Item relacionado */
    private Item item;
    
    /** Construtor */
    public GarantiaLine() {
    	
    	// Fornece "null" como valor para o campo ID. Isto indicará
        // para a aplicação que este bean é novo.
        this.setEntityId(null);
        this.setCompositeEntityId(null);
    	
    }
    
    public boolean isNew() {

        if ( this.compositeEntityId == null )
            return true;
        else 
            return false;

    }    
    
    //  ----[ Métodos Getter ]---------------------------------------------------

	/** Método getter para a propriedade cobraPeca
	 * 
	 *  @return String
	 *
	 */
	public String getCobraPeca() {
		return cobraPeca;
	}

	/** Método getter para a propriedade cobraPecaFornecedor
	 * 
	 *  @return String
	 *
	 */
	public String getCobraPecaFornecedor() {
		return cobraPecaFornecedor;
	}

	/** Método getter para a propriedade enviaPeca
	 * 
	 *  @return String
	 *
	 */
	public String getEnviaPeca() {
		return enviaPeca;
	}

	/** Método getter para a propriedade lineId
	 * 
	 *  @return Long
	 *
	 */
	public Long getLineId() {
		return lineId;
	}

	/** Método getter para a propriedade pecaFaltante
	 * 
	 *  @return String
	 *
	 */
	public String getPecaFaltante() {
		return pecaFaltante;
	}

	/** Método getter para a propriedade quantidade
	 * 
	 *  @return Integer
	 *
	 */
	public Integer getQuantidade() {
		return quantidade;
	}

	/** Método getter para a propriedade valorCustoPadraoSP
	 * 
	 *  @return double
	 *
	 */
	public double getValorCustoPadraoSP() {
		return NumberUtils.round(valorCustoPadraoSP,2);
	}

	/** Método getter para a propriedade valorPecaGarantia
	 * 
	 *  @return double
	 *
	 */
	public double getValorPecaGarantia() {
		return NumberUtils.round(valorPecaGarantia,2);
	}

	/** Método getter para a propriedade valorPrecoFob
	 * 
	 *  @return double
	 *
	 */
	public double getValorPrecoFob() {
		return NumberUtils.round(valorPrecoFob,2);
	}

	/** Método getter para a propriedade valorPrecoSugerido
	 * 
	 *  @return double
	 *
	 */
	public double getValorPrecoSugerido() {
		return NumberUtils.round(valorPrecoSugerido,2);
	}
	
	/** Método getter para a propriedade item
	 * 
	 *  @return Item
	 *
	 */
	public Item getItem() {
		return item;
	}
	
	/**
	 * Método getter para a propriedade valorRemessa
	 * @return  Double de valorRemessa
	 */
	public Double getValorRemessa() {
		return valorRemessa;
	}

	/** Método getter para a propriedade fatorGarantiaId
	 * 
	 *  @return double
	 *
	 */
	public Serializable getFatorGarantiaId() {
		return fatorGarantiaId;
	}
	
	/**
	 * @return the compositeEntityId
	 */
	public GarantiaLineId getCompositeEntityId() {
		return compositeEntityId;
	}
	
	/**
	 * Método getter para a propriedade notaFiscalPeca
	 * @return  NotaFiscal de notaFiscalPeca
	 */
	public NotaFiscal getNotaFiscalPeca() {
		return notaFiscalPeca;
	}

	/**
	 * Método getter para a propriedade notaFiscalRemessa
	 * @return  NotaFiscal de notaFiscalRemessa
	 */
	public NotaFiscal getNotaFiscalRemessa() {
		return notaFiscalRemessa;
	}


	/** Método getter para a propriedade pecaCausadora
	 *
	 * @return pecaCausadora do tipo String
	 *
	 */
	
	public String getPecaCausadora() {
		return pecaCausadora;
	}
	
	
	//	----[ Métodos Setter ]---------------------------------------------------

	/** Método setter para a propriedade cobraPeca
	 *
	 * @param cobraPeca 
	 *           <code>String</code> a ser designado para cobraPeca.
	 * 
	 */
	public void setCobraPeca(String cobraPeca) {
		this.cobraPeca = cobraPeca;
	}

	/** Método setter para a propriedade cobraPecaFornecedor
	 *
	 * @param cobraPecaFornecedor 
	 *           <code>String</code> a ser designado para cobraPecaFornecedor.
	 * 
	 */
	public void setCobraPecaFornecedor(String cobraPecaFornecedor) {
		this.cobraPecaFornecedor = cobraPecaFornecedor;
	}

	/** Método setter para a propriedade enviaPeca
	 *
	 * @param enviaPeca 
	 *           <code>String</code> a ser designado para enviaPeca.
	 * 
	 */
	public void setEnviaPeca(String enviaPeca) {
		this.enviaPeca = enviaPeca;
	}

	/** Método setter para a propriedade fatorGarantiaId
	 *
	 * @param fatorGarantiaId 
	 *           <code>double</code> a ser designado para fatorGarantiaId.
	 * 
	 */
	public void setFatorGarantiaId(Serializable fatorGarantiaId) {
		this.fatorGarantiaId = fatorGarantiaId;
	}

	/** Método setter para a propriedade lineId
	 *
	 * @param lineId 
	 *           <code>Long</code> a ser designado para lineId.
	 * 
	 */
	public void setLineId(Long lineId) {
		this.lineId = lineId;
	}

	/** Método setter para a propriedade pecaFaltante
	 *
	 * @param pecaFaltante 
	 *           <code>String</code> a ser designado para pecaFaltante.
	 * 
	 */
	public void setPecaFaltante(String pecaFaltante) {
		this.pecaFaltante = pecaFaltante;
	}

	/** Método setter para a propriedade quantidade
	 *
	 * @param quantidade 
	 *           <code>Integer</code> a ser designado para quantidade.
	 * 
	 */
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	/**
	 * @param compositeEntityId the compositeEntityId to set
	 */
	public void setCompositeEntityId(GarantiaLineId compositeEntityId) {
		this.compositeEntityId = compositeEntityId;
	}
	
	public void setCompositeEntityId(Serializable garantiaId, Serializable lineId) {
		
		this.compositeEntityId = new GarantiaLineId(garantiaId, lineId);
		
	}

	
	/** Método setter para a propriedade valorCustoPadraoSP
	 *
	 * @param valorCustoPadraoSP 
	 *           <code>double</code> a ser designado para valorCustoPadraoSP.
	 * 
	 */
	public void setValorCustoPadraoSP(double valorCustoPadraoSP) {
		this.valorCustoPadraoSP = valorCustoPadraoSP;
	}

	/** Método setter para a propriedade valorPecaGarantia
	 *
	 * @param valorPecaGarantia 
	 *           <code>double</code> a ser designado para valorPecaGarantia.
	 * 
	 */
	public void setValorPecaGarantia(double valorPecaGarantia) {
		this.valorPecaGarantia = valorPecaGarantia;
	}

	/** Método setter para a propriedade valorPrecoFob
	 *
	 * @param valorPrecoFob 
	 *           <code>double</code> a ser designado para valorPrecoFob.
	 * 
	 */
	public void setValorPrecoFob(double valorPrecoFob) {
		this.valorPrecoFob = valorPrecoFob;
	}

	/** Método setter para a propriedade valorPrecoSugerido
	 *
	 * @param valorPrecoSugerido 
	 *           <code>double</code> a ser designado para valorPrecoSugerido.
	 * 
	 */
	public void setValorPrecoSugerido(double valorPrecoSugerido) {
		this.valorPrecoSugerido = valorPrecoSugerido;
	}
	
	/**
	 * Método setter para a propriedade valorRemessa
	 * @param valorRemessa douDoubleble
	 */
	public void setValorRemessa(Double valorRemessa) {
		this.valorRemessa = valorRemessa;
	}

	/**
	 * Método setter para a propriedade notaFiscalPeca
	 * @param notaFiscalPeca NotaFiscal
	 */
	public void setNotaFiscalPeca(NotaFiscal notaFiscalPeca) {
		this.notaFiscalPeca = notaFiscalPeca;
	}

	/**
	 * Método setter para a propriedade notaFiscalRemessa
	 * @param notaFiscalRemessa NotaFiscal
	 */
	public void setNotaFiscalRemessa(NotaFiscal notaFiscalRemessa) {
		this.notaFiscalRemessa = notaFiscalRemessa;
	}	
	
	/** Método setter para a propriedade item
	 *
	 * @param item 
	 *           <code>Item</code> a ser designado para item.
	 * 
	 */
	public void setItem(Item item) {
		this.item = item;
	}

	/** Método setter para a propriedade pecaCausadora
	 *
	 * @param pecaCausadora 
	 *       <code>pecaCausadora<code> a ser designado para GarantiaLine.java
	 *
	 */
	public void setPecaCausadora(String pecaCausadora) {
		this.pecaCausadora = pecaCausadora;
	}
	
//	----[ Métodos de Serviço da Classe ]---------------------------------------------------
	
	
	/** Método getter para o preço total da peça
	 *  
	 * @return double  - valor total da peça
	 *  
	 */
	public double getValorTotalPeca() {
		return NumberUtils.round((this.getValorPecaGarantia() * this.getQuantidade().intValue()),2);
	}
	
	/** Método getter para o preço total da peça, formatado para pt_BR
	 *  
	 * @return String  - valor total da peça
	 *  
	 */
	public String getFormatedValorTotalPeca() {
		return NumberUtils.formatNumberCurrencyMil(this.getValorPecaGarantia() * this.getQuantidade().intValue());
	}
	
	/** Método getter para o valor de remessa da peça
	 *  
	 * @return double  - valor de remessa da peça
	 *  
	 */
	public double getValorRemessaPeca() {
		return NumberUtils.round((this.getValorPrecoSugerido() * this.getQuantidade().intValue() * .1),2);
	}
	
	/** Método getter para o valor de remessa da peça, formatado para pt_BR
	 *  
	 * @return String  - valor de remessa da peça
	 *  
	 */
	public String getFormatedValorRemessaPeca() {
		return NumberUtils.formatNumberCurrencyMil(this.getValorPrecoSugerido() * this.getQuantidade().intValue() * .1);
	}
	
}
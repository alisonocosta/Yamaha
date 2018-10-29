/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......GarantiaLine.java
 *   .: Cria��o.....01 de junho, 11:31
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "GarantiaLine".
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

    //----[ Atributos de classe e inst�ncia ]-----------------------------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;   
    
    public static final String COBRAR_PECA 	= "S";
    
    public static final String ENVIA_PECA 	= "S";
    
    public static final String COBRAR_FORNECEDOR_SIM = "S";
    
    public static final String COBRAR_FORNECEDOR_NAO = "N";
    
    private GarantiaLineId compositeEntityId;
    
    /** Identificador da linha dentro da solicita��o de garantia. */
    private Long lineId;
    
    /** Quantidade da pe�a utilizada. */
    private Integer quantidade;
    
    /** Flag indicativo que a pe�a com defeito foi enviada para a Yamaha. */
    private String enviaPeca;
    
    /** Flag indicativo que a concession�ria cobrara a pe�a. */
    private String cobraPeca;
    
    /** Flag indicativo que a Yamaha cobrara a pe�a. */
    private String cobraPecaFornecedor;
    
    /** Flag indicativo que esta faltando pe�a no produto. */
    private String pecaFaltante;
    
    /** Flag indicativo da pe�a causadora. */
    private String pecaCausadora;
    
    /** Valor do custo do item em S�o Paulo. */
    private double valorCustoPadraoSP;
    
    /** Valor da pe�a acrescido fator de garantia. */
    private double valorPecaGarantia;
    
    /** Valor da pe�a. */
    private double valorPrecoSugerido;
    
    /** valorPrecoFob. */
    private double valorPrecoFob;
    
    /** valorRemessa. */
    private Double valorRemessa;
    
    /** Entidade da nota fiscal de Pe�a. */
    private NotaFiscal notaFiscalPeca;
    
    /** Identificador �nico do fator de garantia. */
    private Serializable fatorGarantiaId;
    
    /** Entidade da nota fiscal de remessa. */
    private NotaFiscal notaFiscalRemessa;
    
    /** Objeto de Item relacionado */
    private Item item;
    
    /** Construtor */
    public GarantiaLine() {
    	
    	// Fornece "null" como valor para o campo ID. Isto indicar�
        // para a aplica��o que este bean � novo.
        this.setEntityId(null);
        this.setCompositeEntityId(null);
    	
    }
    
    public boolean isNew() {

        if ( this.compositeEntityId == null )
            return true;
        else 
            return false;

    }    
    
    //  ----[ M�todos Getter ]---------------------------------------------------

	/** M�todo getter para a propriedade cobraPeca
	 * 
	 *  @return String
	 *
	 */
	public String getCobraPeca() {
		return cobraPeca;
	}

	/** M�todo getter para a propriedade cobraPecaFornecedor
	 * 
	 *  @return String
	 *
	 */
	public String getCobraPecaFornecedor() {
		return cobraPecaFornecedor;
	}

	/** M�todo getter para a propriedade enviaPeca
	 * 
	 *  @return String
	 *
	 */
	public String getEnviaPeca() {
		return enviaPeca;
	}

	/** M�todo getter para a propriedade lineId
	 * 
	 *  @return Long
	 *
	 */
	public Long getLineId() {
		return lineId;
	}

	/** M�todo getter para a propriedade pecaFaltante
	 * 
	 *  @return String
	 *
	 */
	public String getPecaFaltante() {
		return pecaFaltante;
	}

	/** M�todo getter para a propriedade quantidade
	 * 
	 *  @return Integer
	 *
	 */
	public Integer getQuantidade() {
		return quantidade;
	}

	/** M�todo getter para a propriedade valorCustoPadraoSP
	 * 
	 *  @return double
	 *
	 */
	public double getValorCustoPadraoSP() {
		return NumberUtils.round(valorCustoPadraoSP,2);
	}

	/** M�todo getter para a propriedade valorPecaGarantia
	 * 
	 *  @return double
	 *
	 */
	public double getValorPecaGarantia() {
		return NumberUtils.round(valorPecaGarantia,2);
	}

	/** M�todo getter para a propriedade valorPrecoFob
	 * 
	 *  @return double
	 *
	 */
	public double getValorPrecoFob() {
		return NumberUtils.round(valorPrecoFob,2);
	}

	/** M�todo getter para a propriedade valorPrecoSugerido
	 * 
	 *  @return double
	 *
	 */
	public double getValorPrecoSugerido() {
		return NumberUtils.round(valorPrecoSugerido,2);
	}
	
	/** M�todo getter para a propriedade item
	 * 
	 *  @return Item
	 *
	 */
	public Item getItem() {
		return item;
	}
	
	/**
	 * M�todo getter para a propriedade valorRemessa
	 * @return  Double de valorRemessa
	 */
	public Double getValorRemessa() {
		return valorRemessa;
	}

	/** M�todo getter para a propriedade fatorGarantiaId
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
	 * M�todo getter para a propriedade notaFiscalPeca
	 * @return  NotaFiscal de notaFiscalPeca
	 */
	public NotaFiscal getNotaFiscalPeca() {
		return notaFiscalPeca;
	}

	/**
	 * M�todo getter para a propriedade notaFiscalRemessa
	 * @return  NotaFiscal de notaFiscalRemessa
	 */
	public NotaFiscal getNotaFiscalRemessa() {
		return notaFiscalRemessa;
	}


	/** M�todo getter para a propriedade pecaCausadora
	 *
	 * @return pecaCausadora do tipo String
	 *
	 */
	
	public String getPecaCausadora() {
		return pecaCausadora;
	}
	
	
	//	----[ M�todos Setter ]---------------------------------------------------

	/** M�todo setter para a propriedade cobraPeca
	 *
	 * @param cobraPeca 
	 *           <code>String</code> a ser designado para cobraPeca.
	 * 
	 */
	public void setCobraPeca(String cobraPeca) {
		this.cobraPeca = cobraPeca;
	}

	/** M�todo setter para a propriedade cobraPecaFornecedor
	 *
	 * @param cobraPecaFornecedor 
	 *           <code>String</code> a ser designado para cobraPecaFornecedor.
	 * 
	 */
	public void setCobraPecaFornecedor(String cobraPecaFornecedor) {
		this.cobraPecaFornecedor = cobraPecaFornecedor;
	}

	/** M�todo setter para a propriedade enviaPeca
	 *
	 * @param enviaPeca 
	 *           <code>String</code> a ser designado para enviaPeca.
	 * 
	 */
	public void setEnviaPeca(String enviaPeca) {
		this.enviaPeca = enviaPeca;
	}

	/** M�todo setter para a propriedade fatorGarantiaId
	 *
	 * @param fatorGarantiaId 
	 *           <code>double</code> a ser designado para fatorGarantiaId.
	 * 
	 */
	public void setFatorGarantiaId(Serializable fatorGarantiaId) {
		this.fatorGarantiaId = fatorGarantiaId;
	}

	/** M�todo setter para a propriedade lineId
	 *
	 * @param lineId 
	 *           <code>Long</code> a ser designado para lineId.
	 * 
	 */
	public void setLineId(Long lineId) {
		this.lineId = lineId;
	}

	/** M�todo setter para a propriedade pecaFaltante
	 *
	 * @param pecaFaltante 
	 *           <code>String</code> a ser designado para pecaFaltante.
	 * 
	 */
	public void setPecaFaltante(String pecaFaltante) {
		this.pecaFaltante = pecaFaltante;
	}

	/** M�todo setter para a propriedade quantidade
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

	
	/** M�todo setter para a propriedade valorCustoPadraoSP
	 *
	 * @param valorCustoPadraoSP 
	 *           <code>double</code> a ser designado para valorCustoPadraoSP.
	 * 
	 */
	public void setValorCustoPadraoSP(double valorCustoPadraoSP) {
		this.valorCustoPadraoSP = valorCustoPadraoSP;
	}

	/** M�todo setter para a propriedade valorPecaGarantia
	 *
	 * @param valorPecaGarantia 
	 *           <code>double</code> a ser designado para valorPecaGarantia.
	 * 
	 */
	public void setValorPecaGarantia(double valorPecaGarantia) {
		this.valorPecaGarantia = valorPecaGarantia;
	}

	/** M�todo setter para a propriedade valorPrecoFob
	 *
	 * @param valorPrecoFob 
	 *           <code>double</code> a ser designado para valorPrecoFob.
	 * 
	 */
	public void setValorPrecoFob(double valorPrecoFob) {
		this.valorPrecoFob = valorPrecoFob;
	}

	/** M�todo setter para a propriedade valorPrecoSugerido
	 *
	 * @param valorPrecoSugerido 
	 *           <code>double</code> a ser designado para valorPrecoSugerido.
	 * 
	 */
	public void setValorPrecoSugerido(double valorPrecoSugerido) {
		this.valorPrecoSugerido = valorPrecoSugerido;
	}
	
	/**
	 * M�todo setter para a propriedade valorRemessa
	 * @param valorRemessa douDoubleble
	 */
	public void setValorRemessa(Double valorRemessa) {
		this.valorRemessa = valorRemessa;
	}

	/**
	 * M�todo setter para a propriedade notaFiscalPeca
	 * @param notaFiscalPeca NotaFiscal
	 */
	public void setNotaFiscalPeca(NotaFiscal notaFiscalPeca) {
		this.notaFiscalPeca = notaFiscalPeca;
	}

	/**
	 * M�todo setter para a propriedade notaFiscalRemessa
	 * @param notaFiscalRemessa NotaFiscal
	 */
	public void setNotaFiscalRemessa(NotaFiscal notaFiscalRemessa) {
		this.notaFiscalRemessa = notaFiscalRemessa;
	}	
	
	/** M�todo setter para a propriedade item
	 *
	 * @param item 
	 *           <code>Item</code> a ser designado para item.
	 * 
	 */
	public void setItem(Item item) {
		this.item = item;
	}

	/** M�todo setter para a propriedade pecaCausadora
	 *
	 * @param pecaCausadora 
	 *       <code>pecaCausadora<code> a ser designado para GarantiaLine.java
	 *
	 */
	public void setPecaCausadora(String pecaCausadora) {
		this.pecaCausadora = pecaCausadora;
	}
	
//	----[ M�todos de Servi�o da Classe ]---------------------------------------------------
	
	
	/** M�todo getter para o pre�o total da pe�a
	 *  
	 * @return double  - valor total da pe�a
	 *  
	 */
	public double getValorTotalPeca() {
		return NumberUtils.round((this.getValorPecaGarantia() * this.getQuantidade().intValue()),2);
	}
	
	/** M�todo getter para o pre�o total da pe�a, formatado para pt_BR
	 *  
	 * @return String  - valor total da pe�a
	 *  
	 */
	public String getFormatedValorTotalPeca() {
		return NumberUtils.formatNumberCurrencyMil(this.getValorPecaGarantia() * this.getQuantidade().intValue());
	}
	
	/** M�todo getter para o valor de remessa da pe�a
	 *  
	 * @return double  - valor de remessa da pe�a
	 *  
	 */
	public double getValorRemessaPeca() {
		return NumberUtils.round((this.getValorPrecoSugerido() * this.getQuantidade().intValue() * .1),2);
	}
	
	/** M�todo getter para o valor de remessa da pe�a, formatado para pt_BR
	 *  
	 * @return String  - valor de remessa da pe�a
	 *  
	 */
	public String getFormatedValorRemessaPeca() {
		return NumberUtils.formatNumberCurrencyMil(this.getValorPrecoSugerido() * this.getQuantidade().intValue() * .1);
	}
	
}
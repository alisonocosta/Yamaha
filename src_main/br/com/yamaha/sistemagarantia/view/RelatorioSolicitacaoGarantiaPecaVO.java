/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioSolicitacaoGarantiaPecaVO.java
 *   .: Cria��o.....16 de agosto, 08:35
 *   .: Autor.......Gisele Panosso
 *   .: Descri��o...Entidade "RelatorioSolicitacaoGarantiaPecaVO".
 */

package br.com.yamaha.sistemagarantia.view;

import java.math.BigDecimal;

import br.com.resource.infra.model.EntityObject;
import br.com.resource.infra.utils.NumberUtils;

public class RelatorioSolicitacaoGarantiaPecaVO extends EntityObject {

	//----[ Atributos de classe e inst�ncia ]---------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;    
    
    /** Armazena o campo lote do Relat�rio Solicita��o Garantia */
    private BigDecimal lote; 
    
    /** Armazena o campo statusLote do Relat�rio Solicita��o Garantia */
    private String statusLote;    
	
    /** Armazena o campo quantidade do Relat�rio Solicita��o Garantia */
    private BigDecimal quantidade;
    
    /** Armazena o campo codItem do Relat�rio Solicita��o Garantia */
    private String codItem;
    
    /** Armazena o campo descItem do Relat�rio Solicita��o Garantia */
    private String descItem;
    
    /** Armazena o campo valorItem do Relat�rio Solicita��o Garantia */
    private BigDecimal valorItem;
    
    /** Armazena o campo chassi do Relat�rio Solicita��o Garantia */
    private String enviaPeca;
    
    /** Armazena o campo nomeConc do Relat�rio Solicita��o Garantia */
    private String nomeConc;
    
    /** Armazena o campo enderecoConc do Relat�rio Solicita��o Garantia */
    private String enderecoConc;
    
    /** Armazena o campo cidadeConc do Relat�rio Solicita��o Garantia */
    private String cidadeConc;
    
    /** Armazena o campo estadoConc do Relat�rio Solicita��o Garantia */
    private String estadoConc;
    
    /** Armazena o campo cepConc do Relat�rio Solicita��o Garantia */
    private BigDecimal cepConc;
    
    /** Armazena o campo cnpjConc do Relat�rio Solicita��o Garantia */
    private BigDecimal cnpjConc;
    
    /** Armazena o campo empresa do Relat�rio Solicita��o Garantia */
    private String empresa;
    
    /** Armazena o campo chassi do Relat�rio Solicita��o Garantia */
    private String chassi;

    
	//	----[ M�todos Getter ]---------------------------------------------------
    
	/** M�todo getter para a propriedade cepConc.
	 *
	 *  @return o valor atual de cepConc.
	 */
	public BigDecimal getCepConc() {
		return cepConc;
	}

	/** M�todo getter para a propriedade cidadeConc.
	 *
	 *  @return o valor atual de cidadeConc.
	 */
	public String getCidadeConc() {
		return cidadeConc;
	}

	/** M�todo getter para a propriedade cnpjConc.
	 *
	 *  @return o valor atual de cnpjConc.
	 */
	public BigDecimal getCnpjConc() {
		return cnpjConc;
	}

	/** M�todo getter para a propriedade codItem.
	 *
	 *  @return o valor atual de codItem.
	 */
	public String getCodItem() {
		return codItem;
	}

	/** M�todo getter para a propriedade descItem.
	 *
	 *  @return o valor atual de descItem.
	 */
	public String getDescItem() {
		return descItem;
	}

	/** M�todo getter para a propriedade enderecoConc.
	 *
	 *  @return o valor atual de enderecoConc.
	 */
	public String getEnderecoConc() {
		return enderecoConc;
	}

	/** M�todo getter para a propriedade enviaPeca.
	 *
	 *  @return o valor atual de enviaPeca.
	 */
	public String getEnviaPeca() {
		return enviaPeca;
	}

	/** M�todo getter para a propriedade estadoConc.
	 *
	 *  @return o valor atual de estadoConc.
	 */
	public String getEstadoConc() {
		return estadoConc;
	}

	/** M�todo getter para a propriedade lote.
	 *
	 *  @return o valor atual de lote.
	 */
	public BigDecimal getLote() {
		return lote;
	}

	/** M�todo getter para a propriedade nomeConc.
	 *
	 *  @return o valor atual de nomeConc.
	 */
	public String getNomeConc() {
		return nomeConc;
	}

	/** M�todo getter para a propriedade quantidade.
	 *
	 *  @return o valor atual de quantidade.
	 */
	public BigDecimal getQuantidade() {
		return quantidade;
	}

	/** M�todo getter para a propriedade statusLote.
	 *
	 *  @return o valor atual de statusLote.
	 */
	public String getStatusLote() {
		return statusLote;
	}

	/** M�todo getter para a propriedade valorItem.
	 *
	 *  @return o valor atual de valorItem.
	 */
	public BigDecimal getValorItem() {
		return valorItem;
	}
	
	/** M�todo getter para a propriedade valorItem formatdo para moeda.
	 *
	 *  @return o valor atual de valorItem.
	 */
	public String getValorItemStr() {
		return NumberUtils.formatNumberCurrency(this.valorItem.doubleValue());
	}

	/** M�todo getter para a propriedade empresa.
	 *
	 *  @return o valor atual de empresa.
	 */
	public String getEmpresa() {
		return empresa;
	}
	
	//	----[ M�todos Setter ]---------------------------------------------------
	
	/** Obt�m o valor atual de cepConc.
	 * 
	 *  @param cepConc 
	 *    O novo valor para cepConc.
	 */
	public void setCepConc(BigDecimal cepConc) {
		this.cepConc = cepConc;
	}

	/** Obt�m o valor atual de cidadeConc.
	 * 
	 *  @param cidadeConc 
	 *    O novo valor para cidadeConc.
	 */
	public void setCidadeConc(String cidadeConc) {
		this.cidadeConc = cidadeConc;
	}

	/** Obt�m o valor atual de cnpjConc.
	 * 
	 *  @param cnpjConc 
	 *    O novo valor para cnpjConc.
	 */
	public void setCnpjConc(BigDecimal cnpjConc) {
		this.cnpjConc = cnpjConc;
	}

	/** Obt�m o valor atual de codItem.
	 * 
	 *  @param codItem 
	 *    O novo valor para codItem.
	 */
	public void setCodItem(String codItem) {
		this.codItem = codItem;
	}

	/** Obt�m o valor atual de descItem.
	 * 
	 *  @param descItem 
	 *    O novo valor para descItem.
	 */
	public void setDescItem(String descItem) {
		this.descItem = descItem;
	}

	/** Obt�m o valor atual de enderecoConc.
	 * 
	 *  @param enderecoConc 
	 *    O novo valor para enderecoConc.
	 */
	public void setEnderecoConc(String enderecoConc) {
		this.enderecoConc = enderecoConc;
	}

	/** Obt�m o valor atual de enviaPeca.
	 * 
	 *  @param enviaPeca 
	 *    O novo valor para enviaPeca.
	 */
	public void setEnviaPeca(String enviaPeca) {
		this.enviaPeca = enviaPeca;
	}

	/** Obt�m o valor atual de estadoConc.
	 * 
	 *  @param estadoConc 
	 *    O novo valor para estadoConc.
	 */
	public void setEstadoConc(String estadoConc) {
		this.estadoConc = estadoConc;
	}

	/** Obt�m o valor atual de lote.
	 * 
	 *  @param lote 
	 *    O novo valor para lote.
	 */
	public void setLote(BigDecimal lote) {
		this.lote = lote;
	}

	/** Obt�m o valor atual de nomeConc.
	 * 
	 *  @param nomeConc 
	 *    O novo valor para nomeConc.
	 */
	public void setNomeConc(String nomeConc) {
		this.nomeConc = nomeConc;
	}

	/** Obt�m o valor atual de quantidade.
	 * 
	 *  @param quantidade 
	 *    O novo valor para quantidade.
	 */
	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	/** Obt�m o valor atual de statusLote.
	 * 
	 *  @param statusLote 
	 *    O novo valor para statusLote.
	 */
	public void setStatusLote(String statusLote) {
		this.statusLote = statusLote;
	}

	/** Obt�m o valor atual de valorItem.
	 * 
	 *  @param valorItem 
	 *    O novo valor para valorItem.
	 */
	public void setValorItem(BigDecimal valorItem) {
		this.valorItem = valorItem;
	}

	/** M�todo getter para a propriedade chassi.
	 *
	 *  @return o valor atual de chassi.
	 */
	public String getChassi() {
		return chassi;
	}

	/** Obt�m o valur atual de chassi.
	 * 
	 *  @param chassi 
	 *    O novo valor para chassi.
	 */
	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	/** Obt�m o valur atual de empresa.
	 * 
	 *  @param empresa 
	 *    O novo valor para empresa.
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
    
    

}

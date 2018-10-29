/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioSolicitacaoGarantiaPecaVO.java
 *   .: Criação.....16 de agosto, 08:35
 *   .: Autor.......Gisele Panosso
 *   .: Descrição...Entidade "RelatorioSolicitacaoGarantiaPecaVO".
 */

package br.com.yamaha.sistemagarantia.view;

import java.math.BigDecimal;

import br.com.resource.infra.model.EntityObject;
import br.com.resource.infra.utils.NumberUtils;

public class RelatorioSolicitacaoGarantiaPecaVO extends EntityObject {

	//----[ Atributos de classe e instância ]---------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;    
    
    /** Armazena o campo lote do Relatório Solicitação Garantia */
    private BigDecimal lote; 
    
    /** Armazena o campo statusLote do Relatório Solicitação Garantia */
    private String statusLote;    
	
    /** Armazena o campo quantidade do Relatório Solicitação Garantia */
    private BigDecimal quantidade;
    
    /** Armazena o campo codItem do Relatório Solicitação Garantia */
    private String codItem;
    
    /** Armazena o campo descItem do Relatório Solicitação Garantia */
    private String descItem;
    
    /** Armazena o campo valorItem do Relatório Solicitação Garantia */
    private BigDecimal valorItem;
    
    /** Armazena o campo chassi do Relatório Solicitação Garantia */
    private String enviaPeca;
    
    /** Armazena o campo nomeConc do Relatório Solicitação Garantia */
    private String nomeConc;
    
    /** Armazena o campo enderecoConc do Relatório Solicitação Garantia */
    private String enderecoConc;
    
    /** Armazena o campo cidadeConc do Relatório Solicitação Garantia */
    private String cidadeConc;
    
    /** Armazena o campo estadoConc do Relatório Solicitação Garantia */
    private String estadoConc;
    
    /** Armazena o campo cepConc do Relatório Solicitação Garantia */
    private BigDecimal cepConc;
    
    /** Armazena o campo cnpjConc do Relatório Solicitação Garantia */
    private BigDecimal cnpjConc;
    
    /** Armazena o campo empresa do Relatório Solicitação Garantia */
    private String empresa;
    
    /** Armazena o campo chassi do Relatório Solicitação Garantia */
    private String chassi;

    
	//	----[ Métodos Getter ]---------------------------------------------------
    
	/** Método getter para a propriedade cepConc.
	 *
	 *  @return o valor atual de cepConc.
	 */
	public BigDecimal getCepConc() {
		return cepConc;
	}

	/** Método getter para a propriedade cidadeConc.
	 *
	 *  @return o valor atual de cidadeConc.
	 */
	public String getCidadeConc() {
		return cidadeConc;
	}

	/** Método getter para a propriedade cnpjConc.
	 *
	 *  @return o valor atual de cnpjConc.
	 */
	public BigDecimal getCnpjConc() {
		return cnpjConc;
	}

	/** Método getter para a propriedade codItem.
	 *
	 *  @return o valor atual de codItem.
	 */
	public String getCodItem() {
		return codItem;
	}

	/** Método getter para a propriedade descItem.
	 *
	 *  @return o valor atual de descItem.
	 */
	public String getDescItem() {
		return descItem;
	}

	/** Método getter para a propriedade enderecoConc.
	 *
	 *  @return o valor atual de enderecoConc.
	 */
	public String getEnderecoConc() {
		return enderecoConc;
	}

	/** Método getter para a propriedade enviaPeca.
	 *
	 *  @return o valor atual de enviaPeca.
	 */
	public String getEnviaPeca() {
		return enviaPeca;
	}

	/** Método getter para a propriedade estadoConc.
	 *
	 *  @return o valor atual de estadoConc.
	 */
	public String getEstadoConc() {
		return estadoConc;
	}

	/** Método getter para a propriedade lote.
	 *
	 *  @return o valor atual de lote.
	 */
	public BigDecimal getLote() {
		return lote;
	}

	/** Método getter para a propriedade nomeConc.
	 *
	 *  @return o valor atual de nomeConc.
	 */
	public String getNomeConc() {
		return nomeConc;
	}

	/** Método getter para a propriedade quantidade.
	 *
	 *  @return o valor atual de quantidade.
	 */
	public BigDecimal getQuantidade() {
		return quantidade;
	}

	/** Método getter para a propriedade statusLote.
	 *
	 *  @return o valor atual de statusLote.
	 */
	public String getStatusLote() {
		return statusLote;
	}

	/** Método getter para a propriedade valorItem.
	 *
	 *  @return o valor atual de valorItem.
	 */
	public BigDecimal getValorItem() {
		return valorItem;
	}
	
	/** Método getter para a propriedade valorItem formatdo para moeda.
	 *
	 *  @return o valor atual de valorItem.
	 */
	public String getValorItemStr() {
		return NumberUtils.formatNumberCurrency(this.valorItem.doubleValue());
	}

	/** Método getter para a propriedade empresa.
	 *
	 *  @return o valor atual de empresa.
	 */
	public String getEmpresa() {
		return empresa;
	}
	
	//	----[ Métodos Setter ]---------------------------------------------------
	
	/** Obtém o valor atual de cepConc.
	 * 
	 *  @param cepConc 
	 *    O novo valor para cepConc.
	 */
	public void setCepConc(BigDecimal cepConc) {
		this.cepConc = cepConc;
	}

	/** Obtém o valor atual de cidadeConc.
	 * 
	 *  @param cidadeConc 
	 *    O novo valor para cidadeConc.
	 */
	public void setCidadeConc(String cidadeConc) {
		this.cidadeConc = cidadeConc;
	}

	/** Obtém o valor atual de cnpjConc.
	 * 
	 *  @param cnpjConc 
	 *    O novo valor para cnpjConc.
	 */
	public void setCnpjConc(BigDecimal cnpjConc) {
		this.cnpjConc = cnpjConc;
	}

	/** Obtém o valor atual de codItem.
	 * 
	 *  @param codItem 
	 *    O novo valor para codItem.
	 */
	public void setCodItem(String codItem) {
		this.codItem = codItem;
	}

	/** Obtém o valor atual de descItem.
	 * 
	 *  @param descItem 
	 *    O novo valor para descItem.
	 */
	public void setDescItem(String descItem) {
		this.descItem = descItem;
	}

	/** Obtém o valor atual de enderecoConc.
	 * 
	 *  @param enderecoConc 
	 *    O novo valor para enderecoConc.
	 */
	public void setEnderecoConc(String enderecoConc) {
		this.enderecoConc = enderecoConc;
	}

	/** Obtém o valor atual de enviaPeca.
	 * 
	 *  @param enviaPeca 
	 *    O novo valor para enviaPeca.
	 */
	public void setEnviaPeca(String enviaPeca) {
		this.enviaPeca = enviaPeca;
	}

	/** Obtém o valor atual de estadoConc.
	 * 
	 *  @param estadoConc 
	 *    O novo valor para estadoConc.
	 */
	public void setEstadoConc(String estadoConc) {
		this.estadoConc = estadoConc;
	}

	/** Obtém o valor atual de lote.
	 * 
	 *  @param lote 
	 *    O novo valor para lote.
	 */
	public void setLote(BigDecimal lote) {
		this.lote = lote;
	}

	/** Obtém o valor atual de nomeConc.
	 * 
	 *  @param nomeConc 
	 *    O novo valor para nomeConc.
	 */
	public void setNomeConc(String nomeConc) {
		this.nomeConc = nomeConc;
	}

	/** Obtém o valor atual de quantidade.
	 * 
	 *  @param quantidade 
	 *    O novo valor para quantidade.
	 */
	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	/** Obtém o valor atual de statusLote.
	 * 
	 *  @param statusLote 
	 *    O novo valor para statusLote.
	 */
	public void setStatusLote(String statusLote) {
		this.statusLote = statusLote;
	}

	/** Obtém o valor atual de valorItem.
	 * 
	 *  @param valorItem 
	 *    O novo valor para valorItem.
	 */
	public void setValorItem(BigDecimal valorItem) {
		this.valorItem = valorItem;
	}

	/** Método getter para a propriedade chassi.
	 *
	 *  @return o valor atual de chassi.
	 */
	public String getChassi() {
		return chassi;
	}

	/** Obtém o valur atual de chassi.
	 * 
	 *  @param chassi 
	 *    O novo valor para chassi.
	 */
	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	/** Obtém o valur atual de empresa.
	 * 
	 *  @param empresa 
	 *    O novo valor para empresa.
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
    
    

}

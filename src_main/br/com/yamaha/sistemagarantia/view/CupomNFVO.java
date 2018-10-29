/* Resource Tecnologia
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......CupomNFTO.java
 *   .: Cria��o.....17 de julho 2008, 10:52
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Transfer Object para cupom que ser� relacionado com uma nota fiscal.
 */

package br.com.yamaha.sistemagarantia.view;

import br.com.resource.infra.utils.NumberUtils;

/** Transfer Object para cupom que ser� relacionado com uma nota fiscal.
 * 
 *  @author Edson Luiz Sumensari
 */
public class CupomNFVO {
	
	/** ID do Cupom informado */
    private Long cupomId;
    	
    /** ID da linha do Cupom informado */
    private Long linhaId;
    
	/** N�mero do Cupom informado */
    private Long cupomCode;
    
    /** Armazena um chassi. */
    private String chassi;
        
    /** Descri��o da Revis�o */
    private String descricaoRevisao;    
    
    /** Valor da Revis�o */
    private double valorRevisao;
    
    /** Valor da Bonifica��o para a Revis�o */
    private double valorBonificacao;
    
    /** C�digo da Empresa org_code */
    private String codigoEmpresa;

	/**
	 * M�todo getter para a propriedade cupomId
	 * @return  Long de cupomId
	 */
	public Long getCupomId() {
		return cupomId;
	}

	/**
	 * M�todo setter para a propriedade cupomId
	 * @param cupomId Long
	 */
	public void setCupomId(Long cupomId) {
		this.cupomId = cupomId;
	}

	/**
	 * M�todo getter para a propriedade linhaId
	 * @return  Long de linhaId
	 */
	public Long getLinhaId() {
		return linhaId;
	}

	/**
	 * M�todo setter para a propriedade linhaId
	 * @param linhaId Long
	 */
	public void setLinhaId(Long linhaId) {
		this.linhaId = linhaId;
	}

	/**
	 * M�todo getter para a propriedade cupomCode
	 * @return  Long de cupomCode
	 */
	public Long getCupomCode() {
		return cupomCode;
	}

	/**
	 * M�todo setter para a propriedade cupomCode
	 * @param cupomCode Long
	 */
	public void setCupomCode(Long cupomCode) {
		this.cupomCode = cupomCode;
	}

	/**
	 * M�todo getter para a propriedade chassi
	 * @return  String de chassi
	 */
	public String getChassi() {
		return chassi;
	}

	/**
	 * M�todo setter para a propriedade chassi
	 * @param chassi String
	 */
	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	/**
	 * M�todo getter para a propriedade descricaoRevisao
	 * @return  String de descricaoRevisao
	 */
	public String getDescricaoRevisao() {
		return descricaoRevisao;
	}

	/**
	 * M�todo setter para a propriedade descricaoRevisao
	 * @param descricaoRevisao String
	 */
	public void setDescricaoRevisao(String descricaoRevisao) {
		this.descricaoRevisao = descricaoRevisao;
	}

	/**
	 * M�todo getter para a propriedade valorRevisao
	 * @return  double de valorRevisao
	 */
	public double getValorRevisao() {
		return valorRevisao;
	}

	/**
	 * M�todo setter para a propriedade valorRevisao
	 * @param valorRevisao double
	 */
	public void setValorRevisao(double valorRevisao) {
		this.valorRevisao = valorRevisao;
	}

	/**
	 * M�todo getter para a propriedade valorBonificacao
	 * @return  double de valorBonificacao
	 */
	public double getValorBonificacao() {
		return valorBonificacao;
	}

	/**
	 * M�todo setter para a propriedade valorBonificacao
	 * @param valorBonificacao double
	 */
	public void setValorBonificacao(double valorBonificacao) {
		this.valorBonificacao = valorBonificacao;
	}

	/**
	 * M�todo getter para a propriedade codigoEmpresa
	 * @return  String de codigoEmpresa
	 */
	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}

	/**
	 * M�todo setter para a propriedade codigoEmpresa
	 * @param codigoEmpresa String
	 */
	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}
	
	/** Retorna o valor total da revis�o 
	 * 
	 * @return String
	 */
	public double getValorTotalRevisao() {
		
		return NumberUtils.round(this.getValorRevisao() + this.getValorBonificacao(),2);
		
	}
	
	/** Retorna o valor de revis�o, no formato Moeda REAL 
	 * 
	 * @return String
	 */
	public String getFormatedValorRevisao() {
		 
		return NumberUtils.formatNumberCurrency(this.getValorRevisao());
		
	}
	
	/** Retorna o valor de Bonifica��o, no formato Moeda REAL 
	 * 
	 * @return String
	 */
	public String getFormatedValorBonificacaoo() {
		 
		return NumberUtils.formatNumberCurrency(this.getValorBonificacao());
		
	}
	
	/** Retorna o valor total da revis�o, no formato Moeda REAL 
	 * 
	 * @return String
	 */
	public String getFormatedValorTotalRevisao() {
		
		return NumberUtils.formatNumberCurrency(this.getValorRevisao() + this.getValorBonificacao());
		
	}
}

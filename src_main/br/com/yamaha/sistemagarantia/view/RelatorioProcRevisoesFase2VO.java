/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioProcRevisoesFase2VO.java
 *   .: Criação.....03 de agosto, 09:08
 *   .: Autor.......Gisele Panosso
 *   .: Descrição...Entidade "RelatorioProcRevisoesFase2VO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import br.com.resource.infra.model.EntityObject;
import br.com.resource.infra.utils.NumberUtils;

public class RelatorioProcRevisoesFase2VO extends EntityObject {

	//----[ Atributos de classe e instância ]---------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;    
    
    /** Armazena o campo linha do Relatório Processamento de Revisoes - Fase2. */	
    private String linha;
    
    /** Armazena o campo id da linha do Relatório Processamento de Revisoes - Fase2. */	
    private Serializable linhaId;
    
    /** Armazena o campo lote do Relatório Processamento de Revisoes - Fase2. */
    private Serializable lote;

    /** Armazena o campo nrRevisao do Relatório Processamento de Revisoes - Fase2. */
    private Serializable nrRevisao;
    
    /** Armazena o campo revisao do Relatório Processamento de Revisoes - Fase2. */
    private String revisao;
    
    /** Armazena o campo chassi do Relatório Processamento de Revisoes - Fase2. */
    private String chassi;
    
    /** Armazena o campo dtRevisao do Relatório Processamento de Revisoes - Fase2. */
    private Date dtRevisao;
    
    /** Armazena o campo vlRevisao do Relatório Processamento de Revisoes - Fase2. */
    private Serializable vlRevisao;
    
    /** Armazena o campo quilometragem do Relatório Processamento de Revisoes - Fase2. */
    private Serializable quilometragem;
    
    /** Armazena o campo nomeEmpresa do Relatório Processamento de Revisoes - Fase2. */
    private String nomeEmpresa;
    
    /** Armazena o campo cnpjConc do Relatório Processamento de Revisoes - Fase2. */
    private String cnpjConc;
    
    /** Armazena o campo razaoSocialConc do Relatório Processamento de Revisoes - Fase2. */
    private String razaoSocialConc;
    
    /** Armazena o campo enderecoConc do Relatório Processamento de Revisoes - Fase2. */
    private String enderecoConc;
    
    /** Armazena o campo cepConc do Relatório Processamento de Revisoes - Fase2. */
    private Serializable cepConc;
    
    /** Armazena o campo cidadeConc do Relatório Processamento de Revisoes - Fase2. */
    private String cidadeConc;
    
    /** Armazena o campo estadoConc do Relatório Processamento de Revisoes - Fase2. */
    private String estadoConc;

    
    //	----[ Métodos Setter ]---------------------------------------------------
    
	/** Método getter para a propriedade cepConc.
	 *
	 *  @return o valor atual de cepConc.
	 */
	public Serializable getCepConc() {
		return cepConc;
	}

	/** Método getter para a propriedade chassi.
	 *
	 *  @return o valor atual de chassi.
	 */
	public String getChassi() {
		return chassi;
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
	public String getCnpjConc() {
		return cnpjConc;
	}

	/** Método getter para a propriedade dtRevisao.
	 *
	 *  @return o valor atual de dtRevisao.
	 */
	public Date getDtRevisao() {
		return dtRevisao;
	}

	/** Método getter para a propriedade enderecoConc.
	 *
	 *  @return o valor atual de enderecoConc.
	 */
	public String getEnderecoConc() {
		return enderecoConc;
	}

	/** Método getter para a propriedade estadoConc.
	 *
	 *  @return o valor atual de estadoConc.
	 */
	public String getEstadoConc() {
		return estadoConc;
	}

	/** Método getter para a propriedade linha.
	 *
	 *  @return o valor atual de linha.
	 */
	public String getLinha() {
		return linha;
	}

	/** Método getter para a propriedade lote.
	 *
	 *  @return o valor atual de lote.
	 */
	public Serializable getLote() {
		return lote;
	}

	/** Método getter para a propriedade nomeEmpresa.
	 *
	 *  @return o valor atual de nomeEmpresa.
	 */
	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	/** Método getter para a propriedade nrRevisao.
	 *
	 *  @return o valor atual de nrRevisao.
	 */
	public Serializable getNrRevisao() {
		return nrRevisao;
	}

	/** Método getter para a propriedade quilometragem.
	 *
	 *  @return o valor atual de quilometragem.
	 */
	public Serializable getQuilometragem() {
		return quilometragem;
	}

	/** Método getter para a propriedade razaoSocialConc.
	 *
	 *  @return o valor atual de razaoSocialConc.
	 */
	public String getRazaoSocialConc() {
		return razaoSocialConc;
	}

	/** Método getter para a propriedade revisao.
	 *
	 *  @return o valor atual de revisao.
	 */
	public String getRevisao() {
		return revisao;
	}

	/** Método getter para a propriedade vlRevisao.
	 *
	 *  @return o valor atual de vlRevisao.
	 */
	public Serializable getVlRevisao() {
		return vlRevisao;
	}

    
    //	----[ Métodos Setter ]---------------------------------------------------

	/** Obtém o valor atual de cepConc.
	 * 
	 *  @param cepConc 
	 *    O novo valor para cepConc.
	 */
	public void setCepConc(Serializable cepConc) {
		this.cepConc = cepConc;
	}

	/** Obtém o valor atual de chassi.
	 * 
	 *  @param chassi 
	 *    O novo valor para chassi.
	 */
	public void setChassi(String chassi) {
		this.chassi = chassi;
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
	public void setCnpjConc(String cnpjConc) {
		this.cnpjConc = cnpjConc;
	}

	/** Obtém o valor atual de dtRevisao.
	 * 
	 *  @param dtRevisao 
	 *    O novo valor para dtRevisao.
	 */
	public void setDtRevisao(Date dtRevisao) {
		this.dtRevisao = dtRevisao;
	}

	/** Obtém o valor atual de enderecoConc.
	 * 
	 *  @param enderecoConc 
	 *    O novo valor para enderecoConc.
	 */
	public void setEnderecoConc(String enderecoConc) {
		this.enderecoConc = enderecoConc;
	}

	/** Obtém o valor atual de estadoConc.
	 * 
	 *  @param estadoConc 
	 *    O novo valor para estadoConc.
	 */
	public void setEstadoConc(String estadoConc) {
		this.estadoConc = estadoConc;
	}

	/** Obtém o valor atual de linha.
	 * 
	 *  @param linha 
	 *    O novo valor para linha.
	 */
	public void setLinha(String linha) {
		this.linha = linha;
	}

	/** Obtém o valor atual de lote.
	 * 
	 *  @param lote 
	 *    O novo valor para lote.
	 */
	public void setLote(Serializable lote) {
		this.lote = lote;
	}

	/** Obtém o valor atual de nomeEmpresa.
	 * 
	 *  @param nomeEmpresa 
	 *    O novo valor para nomeEmpresa.
	 */
	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	/** Obtém o valor atual de nrRevisao.
	 * 
	 *  @param nrRevisao 
	 *    O novo valor para nrRevisao.
	 */
	public void setNrRevisao(Serializable nrRevisao) {
		this.nrRevisao = nrRevisao;
	}

	/** Obtém o valor atual de quilometragem.
	 * 
	 *  @param quilometragem 
	 *    O novo valor para quilometragem.
	 */
	public void setQuilometragem(Serializable quilometragem) {
		this.quilometragem = quilometragem;
	}

	/** Obtém o valor atual de razaoSocialConc.
	 * 
	 *  @param razaoSocialConc 
	 *    O novo valor para razaoSocialConc.
	 */
	public void setRazaoSocialConc(String razaoSocialConc) {
		this.razaoSocialConc = razaoSocialConc;
	}

	/** Obtém o valor atual de revisao.
	 * 
	 *  @param revisao 
	 *    O novo valor para revisao.
	 */
	public void setRevisao(String revisao) {
		this.revisao = revisao;
	}

	/** Obtém o valor atual de vlRevisao.
	 * 
	 *  @param vlRevisao 
	 *    O novo valor para vlRevisao.
	 */
	public void setVlRevisao(Serializable vlRevisao) {
		this.vlRevisao = vlRevisao;
	}

	/** Método getter para a propriedade linhaId
	 *
	 *  @return Serializable de linhaId
	 */
	public Serializable getLinhaId() {
		return linhaId;
	}

	/** Método setter para a propriedade linhaId
	 *
	 * @param linhaId Serializable
	 */
	public void setLinhaId(Serializable linhaId) {
		this.linhaId = linhaId;
	}
    
	
	// ----[ Métodos de Serviços ]---------------------------------------------------
		

	/**
	 * Retorna o valor da revisão formatada para Moeda 
	 * 
	 * @return String  ########0,00
	 * 
	 */
	public String getVlRevisaoStr() {		
		//System.out.println("Valor Revisão:" + ((Double)this.vlRevisao).doubleValue());
		if ( this.vlRevisao != null )
			return NumberUtils.formatNumberCurrency(((Double)this.vlRevisao).doubleValue());
		else
			return "0,00";
	}
	
	/**
	 * Retorna o valor da revisão formatada para Moeda 
	 * 
	 * @return String  ###.###.##0,00
	 * 
	 */
	public String getQuilometragemStr() {
		
		if ( this.quilometragem != null )
			return NumberUtils.formatNumberMil( ((BigDecimal)this.quilometragem).doubleValue() );
		else
			return "0";
		
	}
}

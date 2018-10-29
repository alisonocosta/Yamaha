/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioProcRevisoesFase1VO.java
 *   .: Criação.....02 de agosto, 09:21
 *   .: Autor.......Gisele Panosso
 *   .: Descrição...Entidade "RelatorioProcRevisoesFase1VO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.io.Serializable;
import java.util.Date;

import br.com.resource.infra.model.EntityObject;

public class RelatorioProcRevisoesFase1VO extends EntityObject {

	//----[ Atributos de classe e instância ]---------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;    
    
    /** Armazena o campo linha do Relatório Processamento de Revisoes - Fase1. */	
    private String linha;
    
    /** Armazena o campo lote do Relatório Processamento de Revisoes - Fase1. */
    private Serializable lote;
    
    /** Armazena o campo nrRevisao do Relatório Processamento de Revisoes - Fase1. */
    private Serializable nrRevisao;
    
    /** Armazena o campo revisao do Relatório Processamento de Revisoes - Fase1. */
    private String revisao;
    
    /** Armazena o campo chassi do Relatório Processamento de Revisoes - Fase1. */
    private String chassi;
    
    /** Armazena o campo dtRevisao do Relatório Processamento de Revisoes - Fase1. */
    private Date dtRevisao;
    
    /** Armazena o campo quilometragem do Relatório Processamento de Revisoes - Fase1. */
    private Serializable quilometragem;
    
    /** Armazena o campo nomeEmpresa do Relatório Processamento de Revisoes - Fase1. */
    private String nomeEmpresa;
    
    /** Armazena o campo razaoSocialConc do Relatório Processamento de Revisoes - Fase1. */
    private String razaoSocialConc;
    
    /** Armazena o campo enderecoConc do Relatório Processamento de Revisoes - Fase1. */
    private String enderecoConc;
    
    /** Armazena o campo cepConc do Relatório Processamento de Revisoes - Fase1. */
    private Serializable cepConc;
    
    /** Armazena o campo cidadeConc do Relatório Processamento de Revisoes - Fase1. */
    private String cidadeConc;
    
    /** Armazena o campo estadoConc do Relatório Processamento de Revisoes - Fase1. */
    private String estadoConc;
    
    /** Armazena o campo cnpjConc do Relatório Processamento de Revisoes - Fase1. */
    private String cnpjConc;
    
    
    //	----[ Métodos Getter ]---------------------------------------------------
    
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

}

/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioProcPecasFase1VO.java
 *   .: Criação.....08 de agosto, 11:25
 *   .: Autor.......Gisele Panosso
 *   .: Descrição...Entidade "RelatorioProcPecasFase1VO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.math.BigDecimal;
import java.util.Date;

import br.com.resource.infra.model.EntityObject;

public class RelatorioProcPecasFase1VO extends EntityObject {

	//----[ Atributos de classe e instância ]---------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;    
    
    /** Armazena o campo linhaProduto do Relatório Processamento de Peças - Fase1. */	
    private String linhaProduto;
    
    /** Armazena o campo lote do Relatório Processamento de Peças - Fase1. */
    private BigDecimal lote;
    
    /** Armazena o campo numeroSg do Relatório Processamento de Peças - Fase1. */
    private BigDecimal numeroSg;
    
    /** Armazena o campo chassi do Relatório Processamento de Peças - Fase1. */
    private String chassi;
        
    /** Armazena o campo numeroOs do Relatório Processamento de Peças - Fase1. */
    private String numeroOs;
    
    /** Armazena o campo dtAbertura do Relatório Processamento de Peças - Fase1. */
    private Date dtAbertura;
    
    /** Armazena o campo dtFechto do Relatório Processamento de Peças - Fase1. */
    private Date dtFechto;
    
    /** Armazena o campo km do Relatório Processamento de Peças - Fase1. */
    private BigDecimal km;
    
    /** Armazena o campo nomeEmp do Relatório Processamento de Peças - Fase1. */
    private String nomeEmp;
    
    /** Armazena o campo inscrEstadualEmp do Relatório Processamento de Peças - Fase1. */
    private String inscrEstadualEmp;
    
    /** Armazena o campo cnpjConc do Relatório Processamento de Peças - Fase1. */
    private String cnpjConc;
    
    /** Armazena o campo razaoSocialConc do Relatório Processamento de Peças - Fase1. */
    private String razaoSocialConc;
    
    /** Armazena o campo enderecoConc do Relatório Processamento de Peças - Fase1. */
    private String enderecoConc;
    
    /** Armazena o campo cepConc do Relatório Processamento de Peças - Fase1. */
    private BigDecimal cepConc;
   
    /** Armazena o campo cidadeConc do Relatório Processamento de Peças - Fase1. */
    private String cidadeConc;
    
    /** Armazena o campo estadoConc do Relatório Processamento de Peças - Fase1. */
    private String estadoConc;
    
    /** Armazena o campo qtde do Relatório Processamento de Peças - Fase1. */
    private BigDecimal qtde;
    
    /** Armazena o campo item do Relatório Processamento de Peças - Fase1. */
    private String item;
    
    /** Armazena o campo descricao do Relatório Processamento de Peças - Fase1. */
    private String descricao;
    
    /** Armazena o campo servico do Relatório Processamento de Peças - Fase1. */
    private String servico;
    
    /** Armazena o campo sintoma do Relatório Processamento de Peças - Fase1. */
    private String sintoma;
    
    /** Armazena o campo condicao do Relatório Processamento de Peças - Fase1. */
    private String condicao;
 
    
	//	----[ Métodos Getter ]---------------------------------------------------
    
	/** Método getter para a propriedade cepConc.
	 *
	 *  @return o valor atual de cepConc.
	 */
	public BigDecimal getCepConc() {
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

	/** Método getter para a propriedade condicao.
	 *
	 *  @return o valor atual de condicao.
	 */
	public String getCondicao() {
		return condicao;
	}

	/** Método getter para a propriedade descricao.
	 *
	 *  @return o valor atual de descricao.
	 */
	public String getDescricao() {
		return descricao;
	}

	/** Método getter para a propriedade dtAbertura.
	 *
	 *  @return o valor atual de dtAbertura.
	 */
	public Date getDtAbertura() {
		return dtAbertura;
	}

	/** Método getter para a propriedade dtFechto.
	 *
	 *  @return o valor atual de dtFechto.
	 */
	public Date getDtFechto() {
		return dtFechto;
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

	/** Método getter para a propriedade inscrEstadualEmp.
	 *
	 *  @return o valor atual de inscrEstadualEmp.
	 */
	public String getInscrEstadualEmp() {
		return inscrEstadualEmp;
	}

	/** Método getter para a propriedade item.
	 *
	 *  @return o valor atual de item.
	 */
	public String getItem() {
		return item;
	}

	/** Método getter para a propriedade km.
	 *
	 *  @return o valor atual de km.
	 */
	public BigDecimal getKm() {
		return km;
	}

	/** Método getter para a propriedade linhaProduto.
	 *
	 *  @return o valor atual de linhaProduto.
	 */
	public String getLinhaProduto() {
		return linhaProduto;
	}

	/** Método getter para a propriedade lote.
	 *
	 *  @return o valor atual de lote.
	 */
	public BigDecimal getLote() {
		return lote;
	}

	/** Método getter para a propriedade nomeEmp.
	 *
	 *  @return o valor atual de nomeEmp.
	 */
	public String getNomeEmp() {
		return nomeEmp;
	}

	/** Método getter para a propriedade numeroOs.
	 *
	 *  @return o valor atual de numeroOs.
	 */
	public String getNumeroOs() {
		return numeroOs;
	}

	/** Método getter para a propriedade numeroSg.
	 *
	 *  @return o valor atual de numeroSg.
	 */
	public BigDecimal getNumeroSg() {
		return numeroSg;
	}

	/** Método getter para a propriedade qtde.
	 *
	 *  @return o valor atual de qtde.
	 */
	public BigDecimal getQtde() {
		return qtde;
	}

	/** Método getter para a propriedade razaoSocialConc.
	 *
	 *  @return o valor atual de razaoSocialConc.
	 */
	public String getRazaoSocialConc() {
		return razaoSocialConc;
	}

	/** Método getter para a propriedade servico.
	 *
	 *  @return o valor atual de servico.
	 */
	public String getServico() {
		return servico;
	}

	/** Método getter para a propriedade sintoma.
	 *
	 *  @return o valor atual de sintoma.
	 */
	public String getSintoma() {
		return sintoma;
	}
	
    
	//	----[ Métodos Setter ]---------------------------------------------------

	/** Obtém o valur atual de cepConc.
	 * 
	 *  @param cepConc 
	 *    O novo valor para cepConc.
	 */
	public void setCepConc(BigDecimal cepConc) {
		this.cepConc = cepConc;
	}

	/** Obtém o valur atual de chassi.
	 * 
	 *  @param chassi 
	 *    O novo valor para chassi.
	 */
	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	/** Obtém o valur atual de cidadeConc.
	 * 
	 *  @param cidadeConc 
	 *    O novo valor para cidadeConc.
	 */
	public void setCidadeConc(String cidadeConc) {
		this.cidadeConc = cidadeConc;
	}

	/** Obtém o valur atual de cnpjConc.
	 * 
	 *  @param cnpjConc 
	 *    O novo valor para cnpjConc.
	 */
	public void setCnpjConc(String cnpjConc) {
		this.cnpjConc = cnpjConc;
	}

	/** Obtém o valur atual de condicao.
	 * 
	 *  @param condicao 
	 *    O novo valor para condicao.
	 */
	public void setCondicao(String condicao) {
		this.condicao = condicao;
	}

	/** Obtém o valur atual de descricao.
	 * 
	 *  @param descricao 
	 *    O novo valor para descricao.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/** Obtém o valur atual de dtAbertura.
	 * 
	 *  @param dtAbertura 
	 *    O novo valor para dtAbertura.
	 */
	public void setDtAbertura(Date dtAbertura) {
		this.dtAbertura = dtAbertura;
	}

	/** Obtém o valur atual de dtFechto.
	 * 
	 *  @param dtFechto 
	 *    O novo valor para dtFechto.
	 */
	public void setDtFechto(Date dtFechto) {
		this.dtFechto = dtFechto;
	}

	/** Obtém o valur atual de enderecoConc.
	 * 
	 *  @param enderecoConc 
	 *    O novo valor para enderecoConc.
	 */
	public void setEnderecoConc(String enderecoConc) {
		this.enderecoConc = enderecoConc;
	}

	/** Obtém o valur atual de estadoConc.
	 * 
	 *  @param estadoConc 
	 *    O novo valor para estadoConc.
	 */
	public void setEstadoConc(String estadoConc) {
		this.estadoConc = estadoConc;
	}

	/** Obtém o valur atual de inscrEstadualEmp.
	 * 
	 *  @param inscrEstadualEmp 
	 *    O novo valor para inscrEstadualEmp.
	 */
	public void setInscrEstadualEmp(String inscrEstadualEmp) {
		this.inscrEstadualEmp = inscrEstadualEmp;
	}

	/** Obtém o valur atual de item.
	 * 
	 *  @param item 
	 *    O novo valor para item.
	 */
	public void setItem(String item) {
		this.item = item;
	}

	/** Obtém o valur atual de km.
	 * 
	 *  @param km 
	 *    O novo valor para km.
	 */
	public void setKm(BigDecimal km) {
		this.km = km;
	}

	/** Obtém o valur atual de linhaProduto.
	 * 
	 *  @param linhaProduto 
	 *    O novo valor para linhaProduto.
	 */
	public void setLinhaProduto(String linhaProduto) {
		this.linhaProduto = linhaProduto;
	}

	/** Obtém o valur atual de lote.
	 * 
	 *  @param lote 
	 *    O novo valor para lote.
	 */
	public void setLote(BigDecimal lote) {
		this.lote = lote;
	}

	/** Obtém o valur atual de nomeEmp.
	 * 
	 *  @param nomeEmp 
	 *    O novo valor para nomeEmp.
	 */
	public void setNomeEmp(String nomeEmp) {
		this.nomeEmp = nomeEmp;
	}

	/** Obtém o valur atual de numeroOs.
	 * 
	 *  @param numeroOs 
	 *    O novo valor para numeroOs.
	 */
	public void setNumeroOs(String numeroOs) {
		this.numeroOs = numeroOs;
	}

	/** Obtém o valur atual de numeroSg.
	 * 
	 *  @param numeroSg 
	 *    O novo valor para numeroSg.
	 */
	public void setNumeroSg(BigDecimal numeroSg) {
		this.numeroSg = numeroSg;
	}

	/** Obtém o valur atual de qtde.
	 * 
	 *  @param qtde 
	 *    O novo valor para qtde.
	 */
	public void setQtde(BigDecimal qtde) {
		this.qtde = qtde;
	}

	/** Obtém o valur atual de razaoSocialConc.
	 * 
	 *  @param razaoSocialConc 
	 *    O novo valor para razaoSocialConc.
	 */
	public void setRazaoSocialConc(String razaoSocialConc) {
		this.razaoSocialConc = razaoSocialConc;
	}

	/** Obtém o valur atual de servico.
	 * 
	 *  @param servico 
	 *    O novo valor para servico.
	 */
	public void setServico(String servico) {
		this.servico = servico;
	}

	/** Obtém o valur atual de sintoma.
	 * 
	 *  @param sintoma 
	 *    O novo valor para sintoma.
	 */
	public void setSintoma(String sintoma) {
		this.sintoma = sintoma;
	}
    
}

/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioProcPecasFase1VO.java
 *   .: Cria��o.....08 de agosto, 11:25
 *   .: Autor.......Gisele Panosso
 *   .: Descri��o...Entidade "RelatorioProcPecasFase1VO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.math.BigDecimal;
import java.util.Date;

import br.com.resource.infra.model.EntityObject;

public class RelatorioProcPecasFase1VO extends EntityObject {

	//----[ Atributos de classe e inst�ncia ]---------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;    
    
    /** Armazena o campo linhaProduto do Relat�rio Processamento de Pe�as - Fase1. */	
    private String linhaProduto;
    
    /** Armazena o campo lote do Relat�rio Processamento de Pe�as - Fase1. */
    private BigDecimal lote;
    
    /** Armazena o campo numeroSg do Relat�rio Processamento de Pe�as - Fase1. */
    private BigDecimal numeroSg;
    
    /** Armazena o campo chassi do Relat�rio Processamento de Pe�as - Fase1. */
    private String chassi;
        
    /** Armazena o campo numeroOs do Relat�rio Processamento de Pe�as - Fase1. */
    private String numeroOs;
    
    /** Armazena o campo dtAbertura do Relat�rio Processamento de Pe�as - Fase1. */
    private Date dtAbertura;
    
    /** Armazena o campo dtFechto do Relat�rio Processamento de Pe�as - Fase1. */
    private Date dtFechto;
    
    /** Armazena o campo km do Relat�rio Processamento de Pe�as - Fase1. */
    private BigDecimal km;
    
    /** Armazena o campo nomeEmp do Relat�rio Processamento de Pe�as - Fase1. */
    private String nomeEmp;
    
    /** Armazena o campo inscrEstadualEmp do Relat�rio Processamento de Pe�as - Fase1. */
    private String inscrEstadualEmp;
    
    /** Armazena o campo cnpjConc do Relat�rio Processamento de Pe�as - Fase1. */
    private String cnpjConc;
    
    /** Armazena o campo razaoSocialConc do Relat�rio Processamento de Pe�as - Fase1. */
    private String razaoSocialConc;
    
    /** Armazena o campo enderecoConc do Relat�rio Processamento de Pe�as - Fase1. */
    private String enderecoConc;
    
    /** Armazena o campo cepConc do Relat�rio Processamento de Pe�as - Fase1. */
    private BigDecimal cepConc;
   
    /** Armazena o campo cidadeConc do Relat�rio Processamento de Pe�as - Fase1. */
    private String cidadeConc;
    
    /** Armazena o campo estadoConc do Relat�rio Processamento de Pe�as - Fase1. */
    private String estadoConc;
    
    /** Armazena o campo qtde do Relat�rio Processamento de Pe�as - Fase1. */
    private BigDecimal qtde;
    
    /** Armazena o campo item do Relat�rio Processamento de Pe�as - Fase1. */
    private String item;
    
    /** Armazena o campo descricao do Relat�rio Processamento de Pe�as - Fase1. */
    private String descricao;
    
    /** Armazena o campo servico do Relat�rio Processamento de Pe�as - Fase1. */
    private String servico;
    
    /** Armazena o campo sintoma do Relat�rio Processamento de Pe�as - Fase1. */
    private String sintoma;
    
    /** Armazena o campo condicao do Relat�rio Processamento de Pe�as - Fase1. */
    private String condicao;
 
    
	//	----[ M�todos Getter ]---------------------------------------------------
    
	/** M�todo getter para a propriedade cepConc.
	 *
	 *  @return o valor atual de cepConc.
	 */
	public BigDecimal getCepConc() {
		return cepConc;
	}

	/** M�todo getter para a propriedade chassi.
	 *
	 *  @return o valor atual de chassi.
	 */
	public String getChassi() {
		return chassi;
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
	public String getCnpjConc() {
		return cnpjConc;
	}

	/** M�todo getter para a propriedade condicao.
	 *
	 *  @return o valor atual de condicao.
	 */
	public String getCondicao() {
		return condicao;
	}

	/** M�todo getter para a propriedade descricao.
	 *
	 *  @return o valor atual de descricao.
	 */
	public String getDescricao() {
		return descricao;
	}

	/** M�todo getter para a propriedade dtAbertura.
	 *
	 *  @return o valor atual de dtAbertura.
	 */
	public Date getDtAbertura() {
		return dtAbertura;
	}

	/** M�todo getter para a propriedade dtFechto.
	 *
	 *  @return o valor atual de dtFechto.
	 */
	public Date getDtFechto() {
		return dtFechto;
	}

	/** M�todo getter para a propriedade enderecoConc.
	 *
	 *  @return o valor atual de enderecoConc.
	 */
	public String getEnderecoConc() {
		return enderecoConc;
	}

	/** M�todo getter para a propriedade estadoConc.
	 *
	 *  @return o valor atual de estadoConc.
	 */
	public String getEstadoConc() {
		return estadoConc;
	}

	/** M�todo getter para a propriedade inscrEstadualEmp.
	 *
	 *  @return o valor atual de inscrEstadualEmp.
	 */
	public String getInscrEstadualEmp() {
		return inscrEstadualEmp;
	}

	/** M�todo getter para a propriedade item.
	 *
	 *  @return o valor atual de item.
	 */
	public String getItem() {
		return item;
	}

	/** M�todo getter para a propriedade km.
	 *
	 *  @return o valor atual de km.
	 */
	public BigDecimal getKm() {
		return km;
	}

	/** M�todo getter para a propriedade linhaProduto.
	 *
	 *  @return o valor atual de linhaProduto.
	 */
	public String getLinhaProduto() {
		return linhaProduto;
	}

	/** M�todo getter para a propriedade lote.
	 *
	 *  @return o valor atual de lote.
	 */
	public BigDecimal getLote() {
		return lote;
	}

	/** M�todo getter para a propriedade nomeEmp.
	 *
	 *  @return o valor atual de nomeEmp.
	 */
	public String getNomeEmp() {
		return nomeEmp;
	}

	/** M�todo getter para a propriedade numeroOs.
	 *
	 *  @return o valor atual de numeroOs.
	 */
	public String getNumeroOs() {
		return numeroOs;
	}

	/** M�todo getter para a propriedade numeroSg.
	 *
	 *  @return o valor atual de numeroSg.
	 */
	public BigDecimal getNumeroSg() {
		return numeroSg;
	}

	/** M�todo getter para a propriedade qtde.
	 *
	 *  @return o valor atual de qtde.
	 */
	public BigDecimal getQtde() {
		return qtde;
	}

	/** M�todo getter para a propriedade razaoSocialConc.
	 *
	 *  @return o valor atual de razaoSocialConc.
	 */
	public String getRazaoSocialConc() {
		return razaoSocialConc;
	}

	/** M�todo getter para a propriedade servico.
	 *
	 *  @return o valor atual de servico.
	 */
	public String getServico() {
		return servico;
	}

	/** M�todo getter para a propriedade sintoma.
	 *
	 *  @return o valor atual de sintoma.
	 */
	public String getSintoma() {
		return sintoma;
	}
	
    
	//	----[ M�todos Setter ]---------------------------------------------------

	/** Obt�m o valur atual de cepConc.
	 * 
	 *  @param cepConc 
	 *    O novo valor para cepConc.
	 */
	public void setCepConc(BigDecimal cepConc) {
		this.cepConc = cepConc;
	}

	/** Obt�m o valur atual de chassi.
	 * 
	 *  @param chassi 
	 *    O novo valor para chassi.
	 */
	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	/** Obt�m o valur atual de cidadeConc.
	 * 
	 *  @param cidadeConc 
	 *    O novo valor para cidadeConc.
	 */
	public void setCidadeConc(String cidadeConc) {
		this.cidadeConc = cidadeConc;
	}

	/** Obt�m o valur atual de cnpjConc.
	 * 
	 *  @param cnpjConc 
	 *    O novo valor para cnpjConc.
	 */
	public void setCnpjConc(String cnpjConc) {
		this.cnpjConc = cnpjConc;
	}

	/** Obt�m o valur atual de condicao.
	 * 
	 *  @param condicao 
	 *    O novo valor para condicao.
	 */
	public void setCondicao(String condicao) {
		this.condicao = condicao;
	}

	/** Obt�m o valur atual de descricao.
	 * 
	 *  @param descricao 
	 *    O novo valor para descricao.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/** Obt�m o valur atual de dtAbertura.
	 * 
	 *  @param dtAbertura 
	 *    O novo valor para dtAbertura.
	 */
	public void setDtAbertura(Date dtAbertura) {
		this.dtAbertura = dtAbertura;
	}

	/** Obt�m o valur atual de dtFechto.
	 * 
	 *  @param dtFechto 
	 *    O novo valor para dtFechto.
	 */
	public void setDtFechto(Date dtFechto) {
		this.dtFechto = dtFechto;
	}

	/** Obt�m o valur atual de enderecoConc.
	 * 
	 *  @param enderecoConc 
	 *    O novo valor para enderecoConc.
	 */
	public void setEnderecoConc(String enderecoConc) {
		this.enderecoConc = enderecoConc;
	}

	/** Obt�m o valur atual de estadoConc.
	 * 
	 *  @param estadoConc 
	 *    O novo valor para estadoConc.
	 */
	public void setEstadoConc(String estadoConc) {
		this.estadoConc = estadoConc;
	}

	/** Obt�m o valur atual de inscrEstadualEmp.
	 * 
	 *  @param inscrEstadualEmp 
	 *    O novo valor para inscrEstadualEmp.
	 */
	public void setInscrEstadualEmp(String inscrEstadualEmp) {
		this.inscrEstadualEmp = inscrEstadualEmp;
	}

	/** Obt�m o valur atual de item.
	 * 
	 *  @param item 
	 *    O novo valor para item.
	 */
	public void setItem(String item) {
		this.item = item;
	}

	/** Obt�m o valur atual de km.
	 * 
	 *  @param km 
	 *    O novo valor para km.
	 */
	public void setKm(BigDecimal km) {
		this.km = km;
	}

	/** Obt�m o valur atual de linhaProduto.
	 * 
	 *  @param linhaProduto 
	 *    O novo valor para linhaProduto.
	 */
	public void setLinhaProduto(String linhaProduto) {
		this.linhaProduto = linhaProduto;
	}

	/** Obt�m o valur atual de lote.
	 * 
	 *  @param lote 
	 *    O novo valor para lote.
	 */
	public void setLote(BigDecimal lote) {
		this.lote = lote;
	}

	/** Obt�m o valur atual de nomeEmp.
	 * 
	 *  @param nomeEmp 
	 *    O novo valor para nomeEmp.
	 */
	public void setNomeEmp(String nomeEmp) {
		this.nomeEmp = nomeEmp;
	}

	/** Obt�m o valur atual de numeroOs.
	 * 
	 *  @param numeroOs 
	 *    O novo valor para numeroOs.
	 */
	public void setNumeroOs(String numeroOs) {
		this.numeroOs = numeroOs;
	}

	/** Obt�m o valur atual de numeroSg.
	 * 
	 *  @param numeroSg 
	 *    O novo valor para numeroSg.
	 */
	public void setNumeroSg(BigDecimal numeroSg) {
		this.numeroSg = numeroSg;
	}

	/** Obt�m o valur atual de qtde.
	 * 
	 *  @param qtde 
	 *    O novo valor para qtde.
	 */
	public void setQtde(BigDecimal qtde) {
		this.qtde = qtde;
	}

	/** Obt�m o valur atual de razaoSocialConc.
	 * 
	 *  @param razaoSocialConc 
	 *    O novo valor para razaoSocialConc.
	 */
	public void setRazaoSocialConc(String razaoSocialConc) {
		this.razaoSocialConc = razaoSocialConc;
	}

	/** Obt�m o valur atual de servico.
	 * 
	 *  @param servico 
	 *    O novo valor para servico.
	 */
	public void setServico(String servico) {
		this.servico = servico;
	}

	/** Obt�m o valur atual de sintoma.
	 * 
	 *  @param sintoma 
	 *    O novo valor para sintoma.
	 */
	public void setSintoma(String sintoma) {
		this.sintoma = sintoma;
	}
    
}

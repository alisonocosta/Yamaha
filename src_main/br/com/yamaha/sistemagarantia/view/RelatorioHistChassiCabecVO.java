/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioHistChassiCabecVO.java
 *   .: Cria��o.....12 de agosto, 15:11
 *   .: Autor.......Gisele Panosso
 *   .: Descri��o...Entidade "RelatorioHistChassiCabecVO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.io.Serializable;
import java.util.Date;

import br.com.resource.infra.model.EntityObject;
import br.com.resource.infra.utils.DateUtils;

public class RelatorioHistChassiCabecVO extends EntityObject {
	
	//----[ Atributos de classe e inst�ncia ]---------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;    
    
    /** Armazena o campo produto do Relat�rio Consulta Hist�rico Chassi. */
    private String produto;
    
    /** Armazena o campo origem do Relat�rio Consulta Hist�rico Chassi. */
    private String origem;
    
    /** Armazena o campo revenda do Relat�rio Consulta Hist�rico Chassi. */
    private String revenda;
    
    /** Armazena o campo fabModelo do Relat�rio Consulta Hist�rico Chassi. */
    private String fabModelo;
    
    /** Armazena o campo dtVenda do Relat�rio Consulta Hist�rico Chassi. */
    private Date dtVenda;
    
    /** Armazena o campo notaFiscal do Relat�rio Consulta Hist�rico Chassi. */
    private Serializable notaFiscal;
    
    /** Armazena o campo empresa do Relat�rio Consulta Hist�rico Chassi. */
    private String empresa; 
    
    /** Armazena o campo dtRevenda do Relat�rio Consulta Hist�rico Chassi. */
    private Date dtRevenda;
    
    /** Armazena o campo dtFabricacao do Relat�rio Consulta Hist�rico Chassi. */
    private Date dtFabricacao;
    
    /** Armazena o campo cor do Relat�rio Consulta Hist�rico Chassi. */
    private String cor;
    
    /** Armazena o campo nrGarantia do Relat�rio Consulta Hist�rico Chassi. */
    private Serializable nrGarantia;
    
    /** Armazena o campo chassi do Relat�rio Consulta Hist�rico Chassi. */
    private String chassi;
    
    /** Armazena o campo tipo de uso do Relat�rio Consulta Hist�rico Chassi. */
    private String tipoUso;


    //	----[ M�todos Getter ]---------------------------------------------------
    
	/** M�todo getter para a propriedade chassi.
	 *
	 *  @return o valor atual de chassi.
	 */
	public String getChassi() {
		return chassi;
	}

	/** M�todo getter para a propriedade tipoUso
	 *
	 * @return tipoUso do tipo String
	 *
	 */
	
	public String getTipoUso() {
		return tipoUso;
	}

	/** M�todo setter para a propriedade tipoUso
	 *
	 * @param tipoUso 
	 *       <code>tipoUso<code> a ser designado para RelatorioHistChassiCabecVO.java
	 *
	 */
	public void setTipoUso(String tipoUso) {
		this.tipoUso = tipoUso;
	}

	/** M�todo getter para a propriedade cor.
	 *
	 *  @return o valor atual de cor.
	 */
	public String getCor() {
		return cor;
	}

	/** M�todo getter para a propriedade dtFabricacao.
	 *
	 *  @return o valor atual de dtFabricacao.
	 */
	public Date getDtFabricacao() {
		return dtFabricacao;
	}
	
	/** M�todo getter para a propriedade dtFabricacao.
	 *
	 *  @return o valor atual de dtFabricacao.
	 */
	public String getStrDtFabricacao() {
		return DateUtils.applyMask(dtFabricacao);
	}

	/** M�todo getter para a propriedade dtRevenda.
	 *
	 *  @return o valor atual de dtRevenda.
	 */
	public Date getDtRevenda() {
		return dtRevenda;
	}
	
	/** M�todo getter para a propriedade dtRevenda.
	 *
	 *  @return o valor atual de dtRevenda.
	 */
	public String getStrDtRevenda() {
		return DateUtils.applyMask(dtRevenda);
	}

	/** M�todo getter para a propriedade dtVenda.
	 *
	 *  @return o valor atual de dtVenda.
	 */
	public Date getDtVenda() {
		return dtVenda;
	}
	
	/** M�todo getter para a propriedade dtVenda.
	 *
	 *  @return o valor atual de dtVenda.
	 */
	public String getStrDtVenda() {
		return DateUtils.applyMask(dtVenda);
	}

	/** M�todo getter para a propriedade empresa.
	 *
	 *  @return o valor atual de empresa.
	 */
	public String getEmpresa() {
		return empresa;
	}

	/** M�todo getter para a propriedade fabModelo.
	 *
	 *  @return o valor atual de fabModelo.
	 */
	public String getFabModelo() {
		return fabModelo;
	}

	/** M�todo getter para a propriedade notaFiscal.
	 *
	 *  @return o valor atual de notaFiscal.
	 */
	public Serializable getNotaFiscal() {
		return notaFiscal;
	}

	/** M�todo getter para a propriedade nrGarantia.
	 *
	 *  @return o valor atual de nrGarantia.
	 */
	public Serializable getNrGarantia() {
		return nrGarantia;
	}

	/** M�todo getter para a propriedade origem.
	 *
	 *  @return o valor atual de origem.
	 */
	public String getOrigem() {
		return origem;
	}

	/** M�todo getter para a propriedade produto.
	 *
	 *  @return o valor atual de produto.
	 */
	public String getProduto() {
		return produto;
	}

	/** M�todo getter para a propriedade revenda.
	 *
	 *  @return o valor atual de revenda.
	 */
	public String getRevenda() {
		return revenda;
	}

    //	----[ M�todos Setter ]---------------------------------------------------

	/** Obt�m o valur atual de chassi.
	 * 
	 *  @param chassi 
	 *    O novo valor para chassi.
	 */
	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	/** Obt�m o valur atual de cor.
	 * 
	 *  @param cor 
	 *    O novo valor para cor.
	 */
	public void setCor(String cor) {
		this.cor = cor;
	}

	/** Obt�m o valur atual de dtFabricacao.
	 * 
	 *  @param dtFabricacao 
	 *    O novo valor para dtFabricacao.
	 */
	public void setDtFabricacao(Date dtFabricacao) {
		this.dtFabricacao = dtFabricacao;
	}

	/** Obt�m o valur atual de dtRevenda.
	 * 
	 *  @param dtRevenda 
	 *    O novo valor para dtRevenda.
	 */
	public void setDtRevenda(Date dtRevenda) {
		this.dtRevenda = dtRevenda;
	}

	/** Obt�m o valur atual de dtVenda.
	 * 
	 *  @param dtVenda 
	 *    O novo valor para dtVenda.
	 */
	public void setDtVenda(Date dtVenda) {
		this.dtVenda = dtVenda;
	}

	/** Obt�m o valur atual de empresa.
	 * 
	 *  @param empresa 
	 *    O novo valor para empresa.
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/** Obt�m o valur atual de fabModelo.
	 * 
	 *  @param fabModelo 
	 *    O novo valor para fabModelo.
	 */
	public void setFabModelo(String fabModelo) {
		this.fabModelo = fabModelo;
	}

	/** Obt�m o valur atual de notaFiscal.
	 * 
	 *  @param notaFiscal 
	 *    O novo valor para notaFiscal.
	 */
	public void setNotaFiscal(Serializable notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	/** Obt�m o valur atual de nrGarantia.
	 * 
	 *  @param nrGarantia 
	 *    O novo valor para nrGarantia.
	 */
	public void setNrGarantia(Serializable nrGarantia) {
		this.nrGarantia = nrGarantia;
	}

	/** Obt�m o valur atual de origem.
	 * 
	 *  @param origem 
	 *    O novo valor para origem.
	 */
	public void setOrigem(String origem) {
		this.origem = origem;
	}

	/** Obt�m o valur atual de produto.
	 * 
	 *  @param produto 
	 *    O novo valor para produto.
	 */
	public void setProduto(String produto) {
		this.produto = produto;
	}

	/** Obt�m o valur atual de revenda.
	 * 
	 *  @param revenda 
	 *    O novo valor para revenda.
	 */
	public void setRevenda(String revenda) {
		this.revenda = revenda;
	}

    
    
}

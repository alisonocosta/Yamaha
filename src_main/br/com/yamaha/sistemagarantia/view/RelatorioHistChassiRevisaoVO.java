/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioHistChassiRevisaoVO.java
 *   .: Cria��o.....12 de agosto, 15:54
 *   .: Autor.......Gisele Panosso
 *   .: Descri��o...Entidade "RelatorioHistChassiRevisaoVO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.io.Serializable;
import java.util.Date;

import br.com.resource.infra.model.EntityObject;

public class RelatorioHistChassiRevisaoVO extends EntityObject {
	
	//----[ Atributos de classe e inst�ncia ]---------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;    
    
    /** Armazena o campo nrRevisao do Relat�rio Consulta Hist�rico Chassi. */
    private Serializable nrRevisao;
    
    /** Armazena o campo dtRevisao do Relat�rio Consulta Hist�rico Chassi. */
    private Date dtRevisao;
    
    /** Armazena o campo concessionaria do Relat�rio Consulta Hist�rico Chassi. */
    private String concessionaria;
    
    /** Armazena o campo situacao do Relat�rio Consulta Hist�rico Chassi. */
    private String situacao;
    
    /** Armazena o campo lote do Relat�rio Consulta Hist�rico Chassi. */
    private Serializable nrLote;
    
    /** Armazena o campo statusLote do Relat�rio Consulta Hist�rico Chassi. */
    private String statusLote;
    
    /** Armazena o campo autorizacao do Relat�rio Consulta Hist�rico Chassi. */
    private String autorizacao;
    

    //	----[ M�todos Getter ]---------------------------------------------------

	/** M�todo getter para a propriedade autorizacao.
	 *
	 *  @return o valor atual de autorizacao.
	 */
	public String getAutorizacao() {
		return autorizacao;
	}

	/** M�todo getter para a propriedade concessionaria.
	 *
	 *  @return o valor atual de concessionaria.
	 */
	public String getConcessionaria() {
		return concessionaria;
	}

	/** M�todo getter para a propriedade dtRevisao.
	 *
	 *  @return o valor atual de dtRevisao.
	 */
	public Date getDtRevisao() {
		return dtRevisao;
	}

	/** M�todo getter para a propriedade lote.
	 *
	 *  @return o valor atual de lote.
	 */
	public Serializable getNrLote() {
		return nrLote;
	}

	/** M�todo getter para a propriedade nrRevisao.
	 *
	 *  @return o valor atual de nrRevisao.
	 */
	public Serializable getNrRevisao() {
		return nrRevisao;
	}

	/** M�todo getter para a propriedade satusLote.
	 *
	 *  @return o valor atual de satusLote.
	 */
	public String getStatusLote() {
		return statusLote;
	}

	/** M�todo getter para a propriedade situacao.
	 *
	 *  @return o valor atual de situacao.
	 */
	public String getSituacao() {
		return situacao;
	}


    //	----[ M�todos Setter ]---------------------------------------------------
	
	/** Obt�m o valur atual de autorizacao.
	 * 
	 *  @param autorizacao 
	 *    O novo valor para autorizacao.
	 */
	public void setAutorizacao(String autorizacao) {
		this.autorizacao = autorizacao;
	}

	/** Obt�m o valur atual de concessionaria.
	 * 
	 *  @param concessionaria 
	 *    O novo valor para concessionaria.
	 */
	public void setConcessionaria(String concessionaria) {
		this.concessionaria = concessionaria;
	}

	/** Obt�m o valur atual de dtRevisao.
	 * 
	 *  @param dtRevisao 
	 *    O novo valor para dtRevisao.
	 */
	public void setDtRevisao(Date dtRevisao) {
		this.dtRevisao = dtRevisao;
	}

	/** Obt�m o valur atual de lote.
	 * 
	 *  @param lote 
	 *    O novo valor para lote.
	 */
	public void setNrLote(Serializable nrLote) {
		this.nrLote = nrLote;
	}

	/** Obt�m o valur atual de nrRevisao.
	 * 
	 *  @param nrRevisao 
	 *    O novo valor para nrRevisao.
	 */
	public void setNrRevisao(Serializable nrRevisao) {
		this.nrRevisao = nrRevisao;
	}

	/** Obt�m o valur atual de satusLote.
	 * 
	 *  @param satusLote 
	 *    O novo valor para satusLote.
	 */
	public void setStatusLote(String statusLote) {
		this.statusLote = statusLote;
	}

	/** Obt�m o valur atual de situacao.
	 * 
	 *  @param situacao 
	 *    O novo valor para situacao.
	 */
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
    
}

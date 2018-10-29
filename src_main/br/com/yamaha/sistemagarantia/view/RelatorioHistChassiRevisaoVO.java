/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioHistChassiRevisaoVO.java
 *   .: Criação.....12 de agosto, 15:54
 *   .: Autor.......Gisele Panosso
 *   .: Descrição...Entidade "RelatorioHistChassiRevisaoVO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.io.Serializable;
import java.util.Date;

import br.com.resource.infra.model.EntityObject;

public class RelatorioHistChassiRevisaoVO extends EntityObject {
	
	//----[ Atributos de classe e instância ]---------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;    
    
    /** Armazena o campo nrRevisao do Relatório Consulta Histórico Chassi. */
    private Serializable nrRevisao;
    
    /** Armazena o campo dtRevisao do Relatório Consulta Histórico Chassi. */
    private Date dtRevisao;
    
    /** Armazena o campo concessionaria do Relatório Consulta Histórico Chassi. */
    private String concessionaria;
    
    /** Armazena o campo situacao do Relatório Consulta Histórico Chassi. */
    private String situacao;
    
    /** Armazena o campo lote do Relatório Consulta Histórico Chassi. */
    private Serializable nrLote;
    
    /** Armazena o campo statusLote do Relatório Consulta Histórico Chassi. */
    private String statusLote;
    
    /** Armazena o campo autorizacao do Relatório Consulta Histórico Chassi. */
    private String autorizacao;
    

    //	----[ Métodos Getter ]---------------------------------------------------

	/** Método getter para a propriedade autorizacao.
	 *
	 *  @return o valor atual de autorizacao.
	 */
	public String getAutorizacao() {
		return autorizacao;
	}

	/** Método getter para a propriedade concessionaria.
	 *
	 *  @return o valor atual de concessionaria.
	 */
	public String getConcessionaria() {
		return concessionaria;
	}

	/** Método getter para a propriedade dtRevisao.
	 *
	 *  @return o valor atual de dtRevisao.
	 */
	public Date getDtRevisao() {
		return dtRevisao;
	}

	/** Método getter para a propriedade lote.
	 *
	 *  @return o valor atual de lote.
	 */
	public Serializable getNrLote() {
		return nrLote;
	}

	/** Método getter para a propriedade nrRevisao.
	 *
	 *  @return o valor atual de nrRevisao.
	 */
	public Serializable getNrRevisao() {
		return nrRevisao;
	}

	/** Método getter para a propriedade satusLote.
	 *
	 *  @return o valor atual de satusLote.
	 */
	public String getStatusLote() {
		return statusLote;
	}

	/** Método getter para a propriedade situacao.
	 *
	 *  @return o valor atual de situacao.
	 */
	public String getSituacao() {
		return situacao;
	}


    //	----[ Métodos Setter ]---------------------------------------------------
	
	/** Obtém o valur atual de autorizacao.
	 * 
	 *  @param autorizacao 
	 *    O novo valor para autorizacao.
	 */
	public void setAutorizacao(String autorizacao) {
		this.autorizacao = autorizacao;
	}

	/** Obtém o valur atual de concessionaria.
	 * 
	 *  @param concessionaria 
	 *    O novo valor para concessionaria.
	 */
	public void setConcessionaria(String concessionaria) {
		this.concessionaria = concessionaria;
	}

	/** Obtém o valur atual de dtRevisao.
	 * 
	 *  @param dtRevisao 
	 *    O novo valor para dtRevisao.
	 */
	public void setDtRevisao(Date dtRevisao) {
		this.dtRevisao = dtRevisao;
	}

	/** Obtém o valur atual de lote.
	 * 
	 *  @param lote 
	 *    O novo valor para lote.
	 */
	public void setNrLote(Serializable nrLote) {
		this.nrLote = nrLote;
	}

	/** Obtém o valur atual de nrRevisao.
	 * 
	 *  @param nrRevisao 
	 *    O novo valor para nrRevisao.
	 */
	public void setNrRevisao(Serializable nrRevisao) {
		this.nrRevisao = nrRevisao;
	}

	/** Obtém o valur atual de satusLote.
	 * 
	 *  @param satusLote 
	 *    O novo valor para satusLote.
	 */
	public void setStatusLote(String statusLote) {
		this.statusLote = statusLote;
	}

	/** Obtém o valur atual de situacao.
	 * 
	 *  @param situacao 
	 *    O novo valor para situacao.
	 */
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
    
}

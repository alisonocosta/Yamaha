/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioHistChassiGarantiaVO.java
 *   .: Cria��o.....12 de agosto, 16:00
 *   .: Autor.......Gisele Panosso
 *   .: Descri��o...Entidade "RelatorioHistChassiGarantiaVO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.resource.infra.model.EntityObject;

public class RelatorioHistChassiGarantiaVO extends EntityObject {
	
	//----[ Atributos de classe e inst�ncia ]---------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;    

    /** Armazena o campo garantiaId do Relat�rio Consulta Hist�rico Chassi. */
    private Long garantiaId;
    
    /** Armazena o campo garantia do Relat�rio Consulta Hist�rico Chassi. */
    private String garantia;
    
    /** Armazena o campo numeroOs do Relat�rio Consulta Hist�rico Chassi. */
    private String numeroOs;
    
    /** Armazena o campo dtAberturaOs do Relat�rio Consulta Hist�rico Chassi. */
    private Date dtAberturaOs;
    
    /** Armazena o campo dtFechamentoOs do Relat�rio Consulta Hist�rico Chassi. */
    private Date dtFechamentoOs;
    
    /** Armazena o campo concessionaria do Relat�rio Consulta Hist�rico Chassi. */
    private String concessionaria;
    
    /** Armazena o campo situacao do Relat�rio Consulta Hist�rico Chassi. */
    private String situacao;
    
    /** Armazena o campo lote do Relat�rio Consulta Hist�rico Chassi. */
    private Serializable nrLote;
    
    /** Armazena o campo status do Relat�rio Consulta Hist�rico Chassi. */
    private String statusLote;
    
    /** Armazena o campo cortesia do Relat�rio Consulta Hist�rico Chassi. */
    private String cortesia;
    
    /** Armazena o campo tipo de uso do Relat�rio Consulta Hist�rico Chassi. */
    private String tipoUso;
    
    /** Armazena o campo listPecas (lista de Pe�as) do Relat�rio Consulta Hist�rico Chassi. */
    private List listPecas;  

    /** Armazena o campo listParecer (lista de listParecer do analista) do Relat�rio Consulta Hist�rico Chassi. */
    private List listParecer;  
    
    //	----[ M�todos Construtor e Add ]---------------------------------------------------  

    public RelatorioHistChassiGarantiaVO() {
		super();
		listPecas   = new ArrayList();
		listParecer = new ArrayList();
	}

	/** Adiciona uma lista de Pe�as � listagem de Garantias.
     * 
     *  @param listPecas
     *      listPecas a ser adicionado.
     *  
     */
    public void addListPecas(RelatorioHistChassiPecaVO listPecas) {
    	
        this.listPecas.add( listPecas );
        
    }  
    
	/** Adiciona uma lista de Pareceres do Analista � listagem de Garantias.
     * 
     *  @param listParecer
     *      listParecer a ser adicionado.
     *  
     */
    public void addListParecer(RelatorioHistChassiParecerVO listParecer) {
    	
        this.listParecer.add( listParecer );
        
    }    

    //	----[ M�todos Getter ]---------------------------------------------------
    
	/** M�todo getter para a propriedade concessionaria.
	 *
	 *  @return o valor atual de concessionaria.
	 */
	public String getConcessionaria() {
		return concessionaria;
	}

	/** M�todo getter para a propriedade tipoUso
	 *
	 * @return tipoUso do tipo String
	 *
	 */
	
	public String getTipoUso() {
		return tipoUso;
	}
	
	/** M�todo getter para a propriedade cortesia.
	 *
	 *  @return o valor atual de cortesia.
	 */
	public String getCortesia() {
		return cortesia;
	}

	/** M�todo getter para a propriedade dtAberturaOs.
	 *
	 *  @return o valor atual de dtAberturaOs.
	 */
	public Date getDtAberturaOs() {
		return dtAberturaOs;
	}

	/** M�todo getter para a propriedade dtFechamentoOs.
	 *
	 *  @return o valor atual de dtFechamentoOs.
	 */
	public Date getDtFechamentoOs() {
		return dtFechamentoOs;
	}

	/** M�todo getter para a propriedade lote.
	 *
	 *  @return o valor atual de lote.
	 */
	public Serializable getNrLote() {
		return nrLote;
	}

	/** M�todo getter para a propriedade numeroOs.
	 *
	 *  @return o valor atual de numeroOs.
	 */
	public String getNumeroOs() {
		return numeroOs;
	}

	/** M�todo getter para a propriedade situacao.
	 *
	 *  @return o valor atual de situacao.
	 */
	public String getSituacao() {
		return situacao;
	}

	/** M�todo getter para a propriedade status.
	 *
	 *  @return o valor atual de status.
	 */
	public String getStatusLote() {
		return statusLote;
	}

	/** M�todo getter para a propriedade garantia.
	 *
	 *  @return o valor atual de garantia.
	 */
	public String getGarantia() {
		return garantia;
	}

	/** M�todo getter para a propriedade listPecas.
	 *
	 *  @return o valor atual de listPecas.
	 */
	public List getListPecas() {
		return listPecas;
	}
	
	/** M�todo getter para a propriedade listParecer.
	 *
	 *  @return o valor atual de listParecer.
	 */
	public List getListParecer() {
		return listParecer;
	}

	/** M�todo getter para a propriedade garantiaId.
	 *
	 *  @return o valor atual de garantiaId.
	 */
	public Long getGarantiaId() {
		return garantiaId;
	}
	
	
    //	----[ M�todos Setter ]---------------------------------------------------


	/** M�todo setter para a propriedade tipoUso
	 *
	 * @param tipoUso 
	 *       <code>tipoUso<code> a ser designado para RelatorioHistChassiGarantiaVO.java
	 *
	 */
	public void setTipoUso(String tipoUso) {
		this.tipoUso = tipoUso;
	}

	/** Obt�m o valur atual de concessionaria.
	 * 
	 *  @param concessionaria 
	 *    O novo valor para concessionaria.
	 */
	public void setConcessionaria(String concessionaria) {
		this.concessionaria = concessionaria;
	}

	/** Obt�m o valur atual de cortesia.
	 * 
	 *  @param cortesia 
	 *    O novo valor para cortesia.
	 */
	public void setCortesia(String cortesia) {
		this.cortesia = cortesia;
	}

	/** Obt�m o valur atual de dtAberturaOs.
	 * 
	 *  @param dtAberturaOs 
	 *    O novo valor para dtAberturaOs.
	 */
	public void setDtAberturaOs(Date dtAberturaOs) {
		this.dtAberturaOs = dtAberturaOs;
	}

	/** Obt�m o valur atual de dtFechamentoOs.
	 * 
	 *  @param dtFechamentoOs 
	 *    O novo valor para dtFechamentoOs.
	 */
	public void setDtFechamentoOs(Date dtFechamentoOs) {
		this.dtFechamentoOs = dtFechamentoOs;
	}

	/** Obt�m o valur atual de lote.
	 * 
	 *  @param lote 
	 *    O novo valor para lote.
	 */
	public void setNrLote(Serializable lote) {
		this.nrLote = lote;
	}

	/** Obt�m o valur atual de numeroOs.
	 * 
	 *  @param numeroOs 
	 *    O novo valor para numeroOs.
	 */
	public void setNumeroOs(String numeroOs) {
		this.numeroOs = numeroOs;
	}

	/** Obt�m o valur atual de situacao.
	 * 
	 *  @param situacao 
	 *    O novo valor para situacao.
	 */
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	/** Obt�m o valur atual de status.
	 * 
	 *  @param status 
	 *    O novo valor para status.
	 */
	public void setStatusLote(String status) {
		this.statusLote = status;
	}

	/** Obt�m o valur atual de garantia.
	 * 
	 *  @param garantia 
	 *    O novo valor para garantia.
	 */
	public void setGarantia(String garantia) {
		this.garantia = garantia;
	}

	/** Obt�m o valur atual de listPecas.
	 * 
	 *  @param listPecas 
	 *    O novo valor para listPecas.
	 */
	public void setListPecas(List listPecas) {
		this.listPecas = listPecas;
	}

	/** Obt�m o valur atual de listParecer.
	 * 
	 *  @param listParecer 
	 *    O novo valor para listParecer.
	 */
	public void setListParecer(List listParecer) {
		this.listParecer = listParecer;
	}

	/** Obt�m o valur atual de garantiaId.
	 * 
	 *  @param garantiaId 
	 *    O novo valor para garantiaId.
	 */
	public void setGarantiaId(Long garantiaId) {
		this.garantiaId = garantiaId;
	}

    
    
}

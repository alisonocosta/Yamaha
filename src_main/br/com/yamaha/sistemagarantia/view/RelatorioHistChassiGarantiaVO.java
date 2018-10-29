/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioHistChassiGarantiaVO.java
 *   .: Criação.....12 de agosto, 16:00
 *   .: Autor.......Gisele Panosso
 *   .: Descrição...Entidade "RelatorioHistChassiGarantiaVO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.resource.infra.model.EntityObject;

public class RelatorioHistChassiGarantiaVO extends EntityObject {
	
	//----[ Atributos de classe e instância ]---------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;    

    /** Armazena o campo garantiaId do Relatório Consulta Histórico Chassi. */
    private Long garantiaId;
    
    /** Armazena o campo garantia do Relatório Consulta Histórico Chassi. */
    private String garantia;
    
    /** Armazena o campo numeroOs do Relatório Consulta Histórico Chassi. */
    private String numeroOs;
    
    /** Armazena o campo dtAberturaOs do Relatório Consulta Histórico Chassi. */
    private Date dtAberturaOs;
    
    /** Armazena o campo dtFechamentoOs do Relatório Consulta Histórico Chassi. */
    private Date dtFechamentoOs;
    
    /** Armazena o campo concessionaria do Relatório Consulta Histórico Chassi. */
    private String concessionaria;
    
    /** Armazena o campo situacao do Relatório Consulta Histórico Chassi. */
    private String situacao;
    
    /** Armazena o campo lote do Relatório Consulta Histórico Chassi. */
    private Serializable nrLote;
    
    /** Armazena o campo status do Relatório Consulta Histórico Chassi. */
    private String statusLote;
    
    /** Armazena o campo cortesia do Relatório Consulta Histórico Chassi. */
    private String cortesia;
    
    /** Armazena o campo tipo de uso do Relatório Consulta Histórico Chassi. */
    private String tipoUso;
    
    /** Armazena o campo listPecas (lista de Peças) do Relatório Consulta Histórico Chassi. */
    private List listPecas;  

    /** Armazena o campo listParecer (lista de listParecer do analista) do Relatório Consulta Histórico Chassi. */
    private List listParecer;  
    
    //	----[ Métodos Construtor e Add ]---------------------------------------------------  

    public RelatorioHistChassiGarantiaVO() {
		super();
		listPecas   = new ArrayList();
		listParecer = new ArrayList();
	}

	/** Adiciona uma lista de Peças à listagem de Garantias.
     * 
     *  @param listPecas
     *      listPecas a ser adicionado.
     *  
     */
    public void addListPecas(RelatorioHistChassiPecaVO listPecas) {
    	
        this.listPecas.add( listPecas );
        
    }  
    
	/** Adiciona uma lista de Pareceres do Analista à listagem de Garantias.
     * 
     *  @param listParecer
     *      listParecer a ser adicionado.
     *  
     */
    public void addListParecer(RelatorioHistChassiParecerVO listParecer) {
    	
        this.listParecer.add( listParecer );
        
    }    

    //	----[ Métodos Getter ]---------------------------------------------------
    
	/** Método getter para a propriedade concessionaria.
	 *
	 *  @return o valor atual de concessionaria.
	 */
	public String getConcessionaria() {
		return concessionaria;
	}

	/** Método getter para a propriedade tipoUso
	 *
	 * @return tipoUso do tipo String
	 *
	 */
	
	public String getTipoUso() {
		return tipoUso;
	}
	
	/** Método getter para a propriedade cortesia.
	 *
	 *  @return o valor atual de cortesia.
	 */
	public String getCortesia() {
		return cortesia;
	}

	/** Método getter para a propriedade dtAberturaOs.
	 *
	 *  @return o valor atual de dtAberturaOs.
	 */
	public Date getDtAberturaOs() {
		return dtAberturaOs;
	}

	/** Método getter para a propriedade dtFechamentoOs.
	 *
	 *  @return o valor atual de dtFechamentoOs.
	 */
	public Date getDtFechamentoOs() {
		return dtFechamentoOs;
	}

	/** Método getter para a propriedade lote.
	 *
	 *  @return o valor atual de lote.
	 */
	public Serializable getNrLote() {
		return nrLote;
	}

	/** Método getter para a propriedade numeroOs.
	 *
	 *  @return o valor atual de numeroOs.
	 */
	public String getNumeroOs() {
		return numeroOs;
	}

	/** Método getter para a propriedade situacao.
	 *
	 *  @return o valor atual de situacao.
	 */
	public String getSituacao() {
		return situacao;
	}

	/** Método getter para a propriedade status.
	 *
	 *  @return o valor atual de status.
	 */
	public String getStatusLote() {
		return statusLote;
	}

	/** Método getter para a propriedade garantia.
	 *
	 *  @return o valor atual de garantia.
	 */
	public String getGarantia() {
		return garantia;
	}

	/** Método getter para a propriedade listPecas.
	 *
	 *  @return o valor atual de listPecas.
	 */
	public List getListPecas() {
		return listPecas;
	}
	
	/** Método getter para a propriedade listParecer.
	 *
	 *  @return o valor atual de listParecer.
	 */
	public List getListParecer() {
		return listParecer;
	}

	/** Método getter para a propriedade garantiaId.
	 *
	 *  @return o valor atual de garantiaId.
	 */
	public Long getGarantiaId() {
		return garantiaId;
	}
	
	
    //	----[ Métodos Setter ]---------------------------------------------------


	/** Método setter para a propriedade tipoUso
	 *
	 * @param tipoUso 
	 *       <code>tipoUso<code> a ser designado para RelatorioHistChassiGarantiaVO.java
	 *
	 */
	public void setTipoUso(String tipoUso) {
		this.tipoUso = tipoUso;
	}

	/** Obtém o valur atual de concessionaria.
	 * 
	 *  @param concessionaria 
	 *    O novo valor para concessionaria.
	 */
	public void setConcessionaria(String concessionaria) {
		this.concessionaria = concessionaria;
	}

	/** Obtém o valur atual de cortesia.
	 * 
	 *  @param cortesia 
	 *    O novo valor para cortesia.
	 */
	public void setCortesia(String cortesia) {
		this.cortesia = cortesia;
	}

	/** Obtém o valur atual de dtAberturaOs.
	 * 
	 *  @param dtAberturaOs 
	 *    O novo valor para dtAberturaOs.
	 */
	public void setDtAberturaOs(Date dtAberturaOs) {
		this.dtAberturaOs = dtAberturaOs;
	}

	/** Obtém o valur atual de dtFechamentoOs.
	 * 
	 *  @param dtFechamentoOs 
	 *    O novo valor para dtFechamentoOs.
	 */
	public void setDtFechamentoOs(Date dtFechamentoOs) {
		this.dtFechamentoOs = dtFechamentoOs;
	}

	/** Obtém o valur atual de lote.
	 * 
	 *  @param lote 
	 *    O novo valor para lote.
	 */
	public void setNrLote(Serializable lote) {
		this.nrLote = lote;
	}

	/** Obtém o valur atual de numeroOs.
	 * 
	 *  @param numeroOs 
	 *    O novo valor para numeroOs.
	 */
	public void setNumeroOs(String numeroOs) {
		this.numeroOs = numeroOs;
	}

	/** Obtém o valur atual de situacao.
	 * 
	 *  @param situacao 
	 *    O novo valor para situacao.
	 */
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	/** Obtém o valur atual de status.
	 * 
	 *  @param status 
	 *    O novo valor para status.
	 */
	public void setStatusLote(String status) {
		this.statusLote = status;
	}

	/** Obtém o valur atual de garantia.
	 * 
	 *  @param garantia 
	 *    O novo valor para garantia.
	 */
	public void setGarantia(String garantia) {
		this.garantia = garantia;
	}

	/** Obtém o valur atual de listPecas.
	 * 
	 *  @param listPecas 
	 *    O novo valor para listPecas.
	 */
	public void setListPecas(List listPecas) {
		this.listPecas = listPecas;
	}

	/** Obtém o valur atual de listParecer.
	 * 
	 *  @param listParecer 
	 *    O novo valor para listParecer.
	 */
	public void setListParecer(List listParecer) {
		this.listParecer = listParecer;
	}

	/** Obtém o valur atual de garantiaId.
	 * 
	 *  @param garantiaId 
	 *    O novo valor para garantiaId.
	 */
	public void setGarantiaId(Long garantiaId) {
		this.garantiaId = garantiaId;
	}

    
    
}

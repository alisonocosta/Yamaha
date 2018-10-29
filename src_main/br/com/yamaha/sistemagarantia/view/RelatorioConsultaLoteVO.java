/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioConsultaLoteVO.java
 *   .: Criação.....03 de agosto, 13:24
 *   .: Autor.......Gisele Panosso
 *   .: Descrição...Entidade "RelatorioConsultaLoteVO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.resource.infra.model.EntityObject;
import br.com.resource.infra.utils.DateUtils;

public class RelatorioConsultaLoteVO extends EntityObject {

	//----[ Atributos de classe e instância ]---------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;    
    
    /** Armazena o campo lote do Relatório Consulta de Lotes. */
    private Serializable lote;
    
    /** Armazena o campo tipoLote do Relatório Consulta de Lotes. */
    private String tipoLote;
    
    /** Armazena o campo descricaoStatus do Relatório Consulta de Lotes. */
    private String descStatus;    
    
    /** Armazena o campo dtAbertura do Relatório Consulta de Lotes. */
    private Date dtAbertura;
        
    /** Armazena o campo dtLiberacao do Relatório Consulta de Lotes. */
    private Date dtLiberacao;
    
    /** Armazena o campo dtFechamento do Relatório Consulta de Lotes. */
    private Date dtFechamento;    
  
    /** Armazena o campo listStatus (lista dos status do lotes) do Relatório Consulta de Lotes. */
    private List listStatus;  
    
 
    //	----[ Método Add ]---------------------------------------------------  

    public RelatorioConsultaLoteVO() {
		super();
		listStatus = new ArrayList();
	}

	/** Adiciona uma lista de Status do Lote à listagem de lotes.
     * 
     *  @param listStatus
     *      listStatus a ser adicionado.
     *  
     */
    public void addListStatus(RelatorioConsultaLoteStatusVO listStatus) {
    	
        this.listStatus.add( listStatus );
        
    } 

    
    //	----[ Métodos Getter ]---------------------------------------------------  

	/** Método getter para a propriedade dtAbertura.
	 *
	 *  @return o valor atual de dtAbertura com máscara dd/MM/yyyy.
	 */    
    public String getMaskedDtAbertura() {
    	
    	return DateUtils.applyMask( this.getDtAbertura() );
    	
    }    

	/** Método getter para a propriedade dtLiberacao.
	 *
	 *  @return o valor atual de dtLiberacao com máscara dd/MM/yyyy.
	 */
    public String getMaskedDtLiberacao() {
    	
    	return DateUtils.applyMask( this.getDtLiberacao() );
    	
    }  
    
	/** Método getter para a propriedade dtFechamento.
	 *
	 *  @return o valor atual de dtFechamento com máscara dd/MM/yyyy.
	 */
    public String getMaskedDtFechamento() {
    	
    	return DateUtils.applyMask( this.getDtFechamento() );
    	
    }

	/** Método getter para a propriedade descStatus.
	 *
	 *  @return o valor atual de descStatus.
	 */
	public String getDescStatus() {
		return descStatus;
	}

	/** Método getter para a propriedade dtAbertura.
	 *
	 *  @return o valor atual de dtAbertura.
	 */
	public Date getDtAbertura() {
		return dtAbertura;
	}

	/** Método getter para a propriedade dtFechamento.
	 *
	 *  @return o valor atual de dtFechamento.
	 */
	public Date getDtFechamento() {
		return dtFechamento;
	}

	/** Método getter para a propriedade dtLiberacao.
	 *
	 *  @return o valor atual de dtLiberacao.
	 */
	public Date getDtLiberacao() {
		return dtLiberacao;
	}

	/** Método getter para a propriedade listStatus.
	 *
	 *  @return o valor atual de listStatus.
	 */
	public List getListStatus() {
		return listStatus;
	}

	/** Método getter para a propriedade loteId.
	 *
	 *  @return o valor atual de loteId.
	 */
	public Serializable getLote() {
		return lote;
	}

	/** Método getter para a propriedade tipoLote.
	 *
	 *  @return o valor atual de tipoLote.
	 */
	public String getTipoLote() {
		return tipoLote;
	}
    
	
    //	----[ Métodos Getter ]---------------------------------------------------  

	/** Obtém o valur atual de descStatus.
	 * 
	 *  @param descStatus 
	 *    O novo valor para descStatus.
	 */
	public void setDescStatus(String descStatus) {
		this.descStatus = descStatus;
	}

	/** Obtém o valur atual de dtAbertura.
	 * 
	 *  @param dtAbertura 
	 *    O novo valor para dtAbertura.
	 */
	public void setDtAbertura(Date dtAbertura) {
		this.dtAbertura = dtAbertura;
	}

	/** Obtém o valur atual de dtFechamento.
	 * 
	 *  @param dtFechamento 
	 *    O novo valor para dtFechamento.
	 */
	public void setDtFechamento(Date dtFechamento) {
		this.dtFechamento = dtFechamento;
	}

	/** Obtém o valur atual de dtLiberacao.
	 * 
	 *  @param dtLiberacao 
	 *    O novo valor para dtLiberacao.
	 */
	public void setDtLiberacao(Date dtLiberacao) {
		this.dtLiberacao = dtLiberacao;
	}

	/** Obtém o valur atual de listStatus.
	 * 
	 *  @param listStatus 
	 *    O novo valor para listStatus.
	 */
	public void setListStatus(List listStatus) {
		this.listStatus = listStatus;
	}

	/** Obtém o valur atual de loteId.
	 * 
	 *  @param loteId 
	 *    O novo valor para loteId.
	 */
	public void setLote(Serializable lote) {
		this.lote = lote;
	}

	/** Obtém o valur atual de tipoLote.
	 * 
	 *  @param tipoLote 
	 *    O novo valor para tipoLote.
	 */
	public void setTipoLote(String tipoLote) {
		this.tipoLote = tipoLote;
	}    
    
 
    
}
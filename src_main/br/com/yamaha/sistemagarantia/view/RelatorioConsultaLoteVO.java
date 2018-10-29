/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioConsultaLoteVO.java
 *   .: Cria��o.....03 de agosto, 13:24
 *   .: Autor.......Gisele Panosso
 *   .: Descri��o...Entidade "RelatorioConsultaLoteVO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.resource.infra.model.EntityObject;
import br.com.resource.infra.utils.DateUtils;

public class RelatorioConsultaLoteVO extends EntityObject {

	//----[ Atributos de classe e inst�ncia ]---------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;    
    
    /** Armazena o campo lote do Relat�rio Consulta de Lotes. */
    private Serializable lote;
    
    /** Armazena o campo tipoLote do Relat�rio Consulta de Lotes. */
    private String tipoLote;
    
    /** Armazena o campo descricaoStatus do Relat�rio Consulta de Lotes. */
    private String descStatus;    
    
    /** Armazena o campo dtAbertura do Relat�rio Consulta de Lotes. */
    private Date dtAbertura;
        
    /** Armazena o campo dtLiberacao do Relat�rio Consulta de Lotes. */
    private Date dtLiberacao;
    
    /** Armazena o campo dtFechamento do Relat�rio Consulta de Lotes. */
    private Date dtFechamento;    
  
    /** Armazena o campo listStatus (lista dos status do lotes) do Relat�rio Consulta de Lotes. */
    private List listStatus;  
    
 
    //	----[ M�todo Add ]---------------------------------------------------  

    public RelatorioConsultaLoteVO() {
		super();
		listStatus = new ArrayList();
	}

	/** Adiciona uma lista de Status do Lote � listagem de lotes.
     * 
     *  @param listStatus
     *      listStatus a ser adicionado.
     *  
     */
    public void addListStatus(RelatorioConsultaLoteStatusVO listStatus) {
    	
        this.listStatus.add( listStatus );
        
    } 

    
    //	----[ M�todos Getter ]---------------------------------------------------  

	/** M�todo getter para a propriedade dtAbertura.
	 *
	 *  @return o valor atual de dtAbertura com m�scara dd/MM/yyyy.
	 */    
    public String getMaskedDtAbertura() {
    	
    	return DateUtils.applyMask( this.getDtAbertura() );
    	
    }    

	/** M�todo getter para a propriedade dtLiberacao.
	 *
	 *  @return o valor atual de dtLiberacao com m�scara dd/MM/yyyy.
	 */
    public String getMaskedDtLiberacao() {
    	
    	return DateUtils.applyMask( this.getDtLiberacao() );
    	
    }  
    
	/** M�todo getter para a propriedade dtFechamento.
	 *
	 *  @return o valor atual de dtFechamento com m�scara dd/MM/yyyy.
	 */
    public String getMaskedDtFechamento() {
    	
    	return DateUtils.applyMask( this.getDtFechamento() );
    	
    }

	/** M�todo getter para a propriedade descStatus.
	 *
	 *  @return o valor atual de descStatus.
	 */
	public String getDescStatus() {
		return descStatus;
	}

	/** M�todo getter para a propriedade dtAbertura.
	 *
	 *  @return o valor atual de dtAbertura.
	 */
	public Date getDtAbertura() {
		return dtAbertura;
	}

	/** M�todo getter para a propriedade dtFechamento.
	 *
	 *  @return o valor atual de dtFechamento.
	 */
	public Date getDtFechamento() {
		return dtFechamento;
	}

	/** M�todo getter para a propriedade dtLiberacao.
	 *
	 *  @return o valor atual de dtLiberacao.
	 */
	public Date getDtLiberacao() {
		return dtLiberacao;
	}

	/** M�todo getter para a propriedade listStatus.
	 *
	 *  @return o valor atual de listStatus.
	 */
	public List getListStatus() {
		return listStatus;
	}

	/** M�todo getter para a propriedade loteId.
	 *
	 *  @return o valor atual de loteId.
	 */
	public Serializable getLote() {
		return lote;
	}

	/** M�todo getter para a propriedade tipoLote.
	 *
	 *  @return o valor atual de tipoLote.
	 */
	public String getTipoLote() {
		return tipoLote;
	}
    
	
    //	----[ M�todos Getter ]---------------------------------------------------  

	/** Obt�m o valur atual de descStatus.
	 * 
	 *  @param descStatus 
	 *    O novo valor para descStatus.
	 */
	public void setDescStatus(String descStatus) {
		this.descStatus = descStatus;
	}

	/** Obt�m o valur atual de dtAbertura.
	 * 
	 *  @param dtAbertura 
	 *    O novo valor para dtAbertura.
	 */
	public void setDtAbertura(Date dtAbertura) {
		this.dtAbertura = dtAbertura;
	}

	/** Obt�m o valur atual de dtFechamento.
	 * 
	 *  @param dtFechamento 
	 *    O novo valor para dtFechamento.
	 */
	public void setDtFechamento(Date dtFechamento) {
		this.dtFechamento = dtFechamento;
	}

	/** Obt�m o valur atual de dtLiberacao.
	 * 
	 *  @param dtLiberacao 
	 *    O novo valor para dtLiberacao.
	 */
	public void setDtLiberacao(Date dtLiberacao) {
		this.dtLiberacao = dtLiberacao;
	}

	/** Obt�m o valur atual de listStatus.
	 * 
	 *  @param listStatus 
	 *    O novo valor para listStatus.
	 */
	public void setListStatus(List listStatus) {
		this.listStatus = listStatus;
	}

	/** Obt�m o valur atual de loteId.
	 * 
	 *  @param loteId 
	 *    O novo valor para loteId.
	 */
	public void setLote(Serializable lote) {
		this.lote = lote;
	}

	/** Obt�m o valur atual de tipoLote.
	 * 
	 *  @param tipoLote 
	 *    O novo valor para tipoLote.
	 */
	public void setTipoLote(String tipoLote) {
		this.tipoLote = tipoLote;
	}    
    
 
    
}
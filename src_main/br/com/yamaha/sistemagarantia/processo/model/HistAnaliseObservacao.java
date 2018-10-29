/** Yamaha
 * Copyright (c) 2012 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......HistAnaliseObservacao.java
 *   .: Cria��o.....30 de Dezembro, 15:41
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "HistAnaliseObservacao".
 */
package br.com.yamaha.sistemagarantia.processo.model;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "HistAnaliseObservacao" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class HistAnaliseObservacao extends EntityObject {
	
	//----[ Atributos de classe e inst�ncia ]-----------------------------------
    
    /** Version ID gerado para serializa��o. */ 
	private static final long serialVersionUID = -785731153892132399L;

	/**
	 * Id da Garantia assoaciada
	 */
	private Integer garantiaId;
	
	/**
	 * Observa��o 
	 */
	private String observacaoTxt;

	    
    //	----[ M�todos Getter ]---------------------------------------------------
    
	/** M�todo getter para a propriedade garantiaId
	 *
	 * @return garantiaId do tipo Integer
	 *
	 */
	
	public Integer getGarantiaId() {
		return garantiaId;
	}

	/** M�todo getter para a propriedade observacaoTxt
	 *
	 * @return observacaoTxt do tipo String
	 *
	 */	
	public String getObservacaoTxt() {
		return observacaoTxt;
	}
	
	//	----[ M�todos Setter ]---------------------------------------------------

	/** M�todo setter para a propriedade garantiaId
	 *
	 * @param garantiaId 
	 *       <code>garantiaId<code> a ser designado para HistAnaliseParecerTecnico.java
	 *
	 */
	public void setGarantiaId(Integer garantiaId) {
		this.garantiaId = garantiaId;
	}
	
	/** M�todo setter para a propriedade observacaoTxt
	 *
	 * @param observacaoTxt 
	 *       <code>observacaoTxt<code> a ser designado para HistAnaliseObservacao.java
	 *
	 */
	public void setObservacaoTxt(String observacaoTxt) {
		this.observacaoTxt = observacaoTxt;
	}
	
}

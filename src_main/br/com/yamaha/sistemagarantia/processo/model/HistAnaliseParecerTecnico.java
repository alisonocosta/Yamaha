/** Yamaha
 * Copyright (c) 2012 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......HistAnaliseParecerTecnico.java
 *   .: Cria��o.....30 de Dezembro, 15:41
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "HistAnaliseParecerTecnico".
 */
package br.com.yamaha.sistemagarantia.processo.model;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "HistAnaliseParecerTecnico" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class HistAnaliseParecerTecnico extends EntityObject {
	
	//----[ Atributos de classe e inst�ncia ]-----------------------------------
    
    /** Version ID gerado para serializa��o. */ 
	private static final long serialVersionUID = -6392763728458766812L;
	
	/**
	 * Id da Garantia assoaciada
	 */
	private Integer garantiaId;
	
	/**
	 * Descri��o do parecer t�cnico
	 */
	private String parecerTecnicoTxt;

	    
    //	----[ M�todos Getter ]---------------------------------------------------
    
	/** M�todo getter para a propriedade garantiaId
	 *
	 * @return garantiaId do tipo Integer
	 *
	 */
	
	public Integer getGarantiaId() {
		return garantiaId;
	}

	/** M�todo getter para a propriedade parecerTecnicoTxt
	 *
	 * @return parecerTecnicoTxt do tipo String
	 *
	 */
	
	public String getParecerTecnicoTxt() {
		return parecerTecnicoTxt;
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
	
	/** M�todo setter para a propriedade parecerTecnicoTxt
	 *
	 * @param parecerTecnicoTxt 
	 *       <code>parecerTecnicoTxt<code> a ser designado para HistAnaliseParecerTecnico.java
	 *
	 */
	public void setParecerTecnicoTxt(String parecerTecnicoTxt) {
		this.parecerTecnicoTxt = parecerTecnicoTxt;
	}
}

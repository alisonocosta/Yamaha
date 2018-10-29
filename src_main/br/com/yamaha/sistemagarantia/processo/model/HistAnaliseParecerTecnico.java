/** Yamaha
 * Copyright (c) 2012 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......HistAnaliseParecerTecnico.java
 *   .: Criação.....30 de Dezembro, 15:41
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "HistAnaliseParecerTecnico".
 */
package br.com.yamaha.sistemagarantia.processo.model;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "HistAnaliseParecerTecnico" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class HistAnaliseParecerTecnico extends EntityObject {
	
	//----[ Atributos de classe e instância ]-----------------------------------
    
    /** Version ID gerado para serialização. */ 
	private static final long serialVersionUID = -6392763728458766812L;
	
	/**
	 * Id da Garantia assoaciada
	 */
	private Integer garantiaId;
	
	/**
	 * Descrição do parecer técnico
	 */
	private String parecerTecnicoTxt;

	    
    //	----[ Métodos Getter ]---------------------------------------------------
    
	/** Método getter para a propriedade garantiaId
	 *
	 * @return garantiaId do tipo Integer
	 *
	 */
	
	public Integer getGarantiaId() {
		return garantiaId;
	}

	/** Método getter para a propriedade parecerTecnicoTxt
	 *
	 * @return parecerTecnicoTxt do tipo String
	 *
	 */
	
	public String getParecerTecnicoTxt() {
		return parecerTecnicoTxt;
	}
	
	//	----[ Métodos Setter ]---------------------------------------------------

	/** Método setter para a propriedade garantiaId
	 *
	 * @param garantiaId 
	 *       <code>garantiaId<code> a ser designado para HistAnaliseParecerTecnico.java
	 *
	 */
	public void setGarantiaId(Integer garantiaId) {
		this.garantiaId = garantiaId;
	}
	
	/** Método setter para a propriedade parecerTecnicoTxt
	 *
	 * @param parecerTecnicoTxt 
	 *       <code>parecerTecnicoTxt<code> a ser designado para HistAnaliseParecerTecnico.java
	 *
	 */
	public void setParecerTecnicoTxt(String parecerTecnicoTxt) {
		this.parecerTecnicoTxt = parecerTecnicoTxt;
	}
}

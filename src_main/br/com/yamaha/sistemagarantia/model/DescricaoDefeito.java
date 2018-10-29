/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......DescricaoDefeito.java
 *   .: Cria��o.....24 de junho, 10:53
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descri��o...Entidade "DescricaoDefeito".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema - DescricaoDefeito. 
 * 
 *  @author Thiago Uriel M. Garcia
 *  		Edson Luiz Sumensari
 */
public class DescricaoDefeito extends EntityObject {

	private static final long serialVersionUID = 1L;
	private String descricaoDefeito;
	private GarantiaHeader garantia;

	/**
	 * @return the descricaoDefeito
	 */
	public String getDescricaoDefeito() {
		return descricaoDefeito;
	}

	/**
	 * @param descricaoDefeito the descricaoDefeito to set
	 */
	public void setDescricaoDefeito(String descricaoDefeito) {
		this.descricaoDefeito = descricaoDefeito;
	}

	/** M�todo getter para a propriedade garantia
	 * 
	 *  @return GarantiaHeader
	 *
	 */
	public GarantiaHeader getGarantia() {
		return garantia;
	}

	/** M�todo setter para a propriedade garantia
	 *
	 * @param garantia 
	 *           <code>GarantiaHeader</code> a ser designado para garantia.
	 * 
	 */
	public void setGarantia(GarantiaHeader garantia) {
		this.garantia = garantia;
	}
	
	
}
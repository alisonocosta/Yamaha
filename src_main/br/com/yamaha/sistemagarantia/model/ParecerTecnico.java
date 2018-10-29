/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......ParecerTecnico.java
 *   .: Criação.....17 de agosto, 16:21
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "DescricaoDefeito".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema - ParecerTecnico. 
 * 
 *  @author Edson Luiz Sumensari
 */
public class ParecerTecnico extends EntityObject {

	private static final long serialVersionUID = 1L;
	private String parecerTecnico;
	private GarantiaHeader garantia;
	
	/** Método getter para a propriedade garantia
	 * 
	 *  @return GarantiaHeader
	 *
	 */
	public GarantiaHeader getGarantia() {
		return garantia;
	}
	/** Método getter para a propriedade parecerTecnico
	 * 
	 *  @return String
	 *
	 */
	public String getParecerTecnico() {
		return parecerTecnico;
	}
	/** Método setter para a propriedade garantia
	 *
	 * @param garantia 
	 *           <code>GarantiaHeader</code> a ser designado para garantia.
	 * 
	 */
	public void setGarantia(GarantiaHeader garantia) {
		this.garantia = garantia;
	}
	/** Método setter para a propriedade parecerTecnico
	 *
	 * @param parecerTecnico 
	 *           <code>String</code> a ser designado para parecerTecnico.
	 * 
	 */
	public void setParecerTecnico(String parecerTecnico) {
		this.parecerTecnico = parecerTecnico;
	}
	
}
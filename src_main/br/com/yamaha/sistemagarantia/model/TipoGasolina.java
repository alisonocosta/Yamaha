/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......TipoGasolina.java
 *   .: Cria��o.....30 de junho, 23:00
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "TipoGasolina".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "TipoGasolina" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class TipoGasolina extends EntityObject {
	
	//----[ Atributos de classe e inst�ncia ]-----------------------------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L; 
    
    //  ----[ M�todos Getter ]---------------------------------------------------
    /** Descri�ao do tipo da Gasolina */
    private String descricao;

	/** M�todo getter para a propriedade descricao
	 *
	 *  @return String de descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/** M�todo setter para a propriedade descricao
	 *
	 * @param descricao String
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	
}

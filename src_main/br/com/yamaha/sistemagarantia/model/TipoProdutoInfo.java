/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......TipoProdutoInfo.java
 *   .: Cria��o.....30 de junho, 23:04
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "TipoProdutoInfo".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "TipoProdutoInfo" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class TipoProdutoInfo extends EntityObject {
	
	//----[ Atributos de classe e inst�ncia ]-----------------------------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L; 
    
    //  ----[ M�todos Getter ]---------------------------------------------------
    /** Descri�ao do tipo do produto */
    private String descTipoProd;

	/** M�todo getter para a propriedade descTipoProd
	 *
	 *  @return String de descTipoProd
	 */
	public String getDescTipoProd() {
		return descTipoProd;
	}

	/** M�todo setter para a propriedade descTipoProd
	 *
	 * @param descTipoProd String
	 */
	public void setDescTipoProd(String descTipoProd) {
		this.descTipoProd = descTipoProd;
	}
	
}

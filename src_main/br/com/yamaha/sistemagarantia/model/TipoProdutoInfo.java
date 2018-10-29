/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......TipoProdutoInfo.java
 *   .: Criação.....30 de junho, 23:04
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "TipoProdutoInfo".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "TipoProdutoInfo" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class TipoProdutoInfo extends EntityObject {
	
	//----[ Atributos de classe e instância ]-----------------------------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L; 
    
    //  ----[ Métodos Getter ]---------------------------------------------------
    /** Descriçao do tipo do produto */
    private String descTipoProd;

	/** Método getter para a propriedade descTipoProd
	 *
	 *  @return String de descTipoProd
	 */
	public String getDescTipoProd() {
		return descTipoProd;
	}

	/** Método setter para a propriedade descTipoProd
	 *
	 * @param descTipoProd String
	 */
	public void setDescTipoProd(String descTipoProd) {
		this.descTipoProd = descTipoProd;
	}
	
}

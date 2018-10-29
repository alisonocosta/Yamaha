/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......TipoUsoBarco.java
 *   .: Cria��o.....10 de maio, 07:35
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "TipoUsoBarco".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "TipoUsoBarco" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class TipoUsoBarco extends EntityObject {
	
	
	//----[ Atributos de classe e inst�ncia ]-----------------------------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;   
    
    /** Valor da descri��o do tipo de uso do barco */
    private String descricao;

    //	----[ M�todos Getter ]---------------------------------------------------
    
	/** M�todo getter para a propriedade descricao
	 * 
	 *  @return String
	 *
	 */
	public String getDescricao() {
		return descricao;
	}

	//	----[ M�todos Setter ]---------------------------------------------------
	
	/** M�todo setter para a propriedade descricao
	 *
	 * @param descricao 
	 *           <code>String</code> a ser designado para descricao.
	 * 
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	} 
	
}

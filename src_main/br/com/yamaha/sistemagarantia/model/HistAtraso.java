/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......HistAtraso.java
 *   .: Cria��o.....08 de Agosto, 15:41
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "HistAtraso".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "HistAtraso" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class HistAtraso extends EntityObject {
	
	//----[ Atributos de classe e inst�ncia ]-----------------------------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L; 
        
    /** Concessionaria */
    private Concessionaria concessionaria;
    
    //	----[ M�todos Getter ]---------------------------------------------------
    
	/** M�todo getter para a propriedade concessionaria
	 * 
	 *  @return Concessionaria
	 *
	 */
	public Concessionaria getConcessionaria() {
		return concessionaria;
	}
	
	//	----[ M�todos Setter ]---------------------------------------------------

	/** M�todo setter para a propriedade concessionaria
	 *
	 * @param concessionaria 
	 *           <code>Concessionaria</code> a ser designado para concessionaria.
	 * 
	 */
	public void setConcessionaria(Concessionaria concessionaria) {
		this.concessionaria = concessionaria;
	}
}

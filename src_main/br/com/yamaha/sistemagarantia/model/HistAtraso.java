/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......HistAtraso.java
 *   .: Criação.....08 de Agosto, 15:41
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "HistAtraso".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "HistAtraso" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class HistAtraso extends EntityObject {
	
	//----[ Atributos de classe e instância ]-----------------------------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L; 
        
    /** Concessionaria */
    private Concessionaria concessionaria;
    
    //	----[ Métodos Getter ]---------------------------------------------------
    
	/** Método getter para a propriedade concessionaria
	 * 
	 *  @return Concessionaria
	 *
	 */
	public Concessionaria getConcessionaria() {
		return concessionaria;
	}
	
	//	----[ Métodos Setter ]---------------------------------------------------

	/** Método setter para a propriedade concessionaria
	 *
	 * @param concessionaria 
	 *           <code>Concessionaria</code> a ser designado para concessionaria.
	 * 
	 */
	public void setConcessionaria(Concessionaria concessionaria) {
		this.concessionaria = concessionaria;
	}
}

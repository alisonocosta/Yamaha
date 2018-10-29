/* Resource Tecnologia
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......Estado.java
 *   .: Cria��o.....26 de Julho de 2009
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "EstadoDDD".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, representando
 *  um objeto "EstadoDDD" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class EstadoDdd extends EntityObject {
		
	//----[ Atributos de classe e inst�ncia ]-----------------------------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L; 
    
    /** Armazena o ddd. */
    private Long ddd;
            
    //	----[ M�todos Getter ]---------------------------------------------------
    
	/** M�todo getter para a propriedade estado.
	 *
	 *  @return o valor atual de ddd.
	 */
	public Long getDdd() {
		return ddd;
	}
    
    //	----[ M�todos Setter ]---------------------------------------------------
    
	/** Obt�m o valor atual de ddd.
	 * 
	 *  @param ddd 
	 *    O novo valor para ddd.
	 */
	public void setDdd(Long ddd) {
		this.ddd = ddd;
	}
}

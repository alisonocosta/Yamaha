/* Resource Tecnologia
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......Estado.java
 *   .: Criação.....26 de Julho de 2009
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "EstadoDDD".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, representando
 *  um objeto "EstadoDDD" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class EstadoDdd extends EntityObject {
		
	//----[ Atributos de classe e instância ]-----------------------------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L; 
    
    /** Armazena o ddd. */
    private Long ddd;
            
    //	----[ Métodos Getter ]---------------------------------------------------
    
	/** Método getter para a propriedade estado.
	 *
	 *  @return o valor atual de ddd.
	 */
	public Long getDdd() {
		return ddd;
	}
    
    //	----[ Métodos Setter ]---------------------------------------------------
    
	/** Obtém o valor atual de ddd.
	 * 
	 *  @param ddd 
	 *    O novo valor para ddd.
	 */
	public void setDdd(Long ddd) {
		this.ddd = ddd;
	}
}

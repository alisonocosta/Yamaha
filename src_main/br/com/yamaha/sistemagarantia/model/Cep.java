/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......Cep.java
 *   .: Cria��o.....17 de julho, 23:04
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "Cep".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "Cep" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class Cep extends EntityObject {
	
	//----[ Atributos de classe e inst�ncia ]-----------------------------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L; 
    
    /** estado */
    private String estado;
    
    /** cidade */
    private String cidade;
    
    //  ----[ M�todos Getter ]---------------------------------------------------

	/** M�todo getter para a propriedade cidade
	 *
	 *  @return String de cidade
	 */
	public String getCidade() {
		return cidade;
	}

	/** M�todo getter para a propriedade estado
	 *
	 *  @return String de estado
	 */
	public String getEstado() {
		return estado;
	}
	
	//	----[ M�todos Setter ]---------------------------------------------------

	/** M�todo setter para a propriedade estado
	 *
	 * @param estado String
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	/** M�todo setter para a propriedade cidade
	 *
	 * @param cidade String
	 */
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
    
}

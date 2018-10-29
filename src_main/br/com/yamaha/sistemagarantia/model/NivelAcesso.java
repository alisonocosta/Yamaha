/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......NivelAcesso.java
 *   .: Cria��o.....18 de dezembro, 18:01
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "NivelAcesso".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "NivelAcesso" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class NivelAcesso extends EntityObject{
	
//	----[ Atributos de classe e inst�ncia ]-----------------------------------
	
	 /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L; 
    
//	----[ M�todos Getter ]---------------------------------------------------
    /** C�digo de acesso do usu�rio. */	
	private String codigo;

	/** M�todo getter para a propriedade codigo
	 * 
	 *  @return String
	 *
	 */
	public String getCodigo() {
		return codigo;
	}

//	----[ M�todos Setter ]---------------------------------------------------
	/** M�todo setter para a propriedade codigo
	 *
	 * @param codigo 
	 *           <code>String</code> a ser designado para codigo.
	 * 
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
}

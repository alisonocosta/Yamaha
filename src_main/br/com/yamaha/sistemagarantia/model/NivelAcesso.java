/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......NivelAcesso.java
 *   .: Criação.....18 de dezembro, 18:01
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "NivelAcesso".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "NivelAcesso" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class NivelAcesso extends EntityObject{
	
//	----[ Atributos de classe e instância ]-----------------------------------
	
	 /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L; 
    
//	----[ Métodos Getter ]---------------------------------------------------
    /** Código de acesso do usuário. */	
	private String codigo;

	/** Método getter para a propriedade codigo
	 * 
	 *  @return String
	 *
	 */
	public String getCodigo() {
		return codigo;
	}

//	----[ Métodos Setter ]---------------------------------------------------
	/** Método setter para a propriedade codigo
	 *
	 * @param codigo 
	 *           <code>String</code> a ser designado para codigo.
	 * 
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
}

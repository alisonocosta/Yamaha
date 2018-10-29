/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......TipoGasolina.java
 *   .: Criação.....30 de junho, 23:00
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "TipoGasolina".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "TipoGasolina" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class TipoGasolina extends EntityObject {
	
	//----[ Atributos de classe e instância ]-----------------------------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L; 
    
    //  ----[ Métodos Getter ]---------------------------------------------------
    /** Descriçao do tipo da Gasolina */
    private String descricao;

	/** Método getter para a propriedade descricao
	 *
	 *  @return String de descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/** Método setter para a propriedade descricao
	 *
	 * @param descricao String
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	
}

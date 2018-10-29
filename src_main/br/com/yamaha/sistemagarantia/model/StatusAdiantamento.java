/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......StatusAdiantamento.java
 *   .: Cria��o.....06 de Dezembro, 15:17
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "StatusAdiantamento".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;

public class StatusAdiantamento extends EntityObject {

    //----[ Atributos de classe e inst�ncia ]-----------------------------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;   

    /** Descri��o do status. */
    private String descricao;

    //----[ M�todos Getter ]---------------------------------------------------    
    
	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

    //----[ M�todos Setter ]---------------------------------------------------	
	
	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
    
}
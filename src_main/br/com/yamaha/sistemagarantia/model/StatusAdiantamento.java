/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......StatusAdiantamento.java
 *   .: Criação.....06 de Dezembro, 15:17
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "StatusAdiantamento".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;

public class StatusAdiantamento extends EntityObject {

    //----[ Atributos de classe e instância ]-----------------------------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;   

    /** Descrição do status. */
    private String descricao;

    //----[ Métodos Getter ]---------------------------------------------------    
    
	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

    //----[ Métodos Setter ]---------------------------------------------------	
	
	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
    
}
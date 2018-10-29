/* Resource Informática
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......StatusInfoMercado.java
 *   .: Criação.....05 de junho, 19:34
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Entidade "StatusInfoMercado".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;

/** Bean para Status de informações de Mercado. 
 * 
 *  @author Thiago Uriel M. Garcia
 */
public class StatusInfoMercado extends EntityObject {

    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;  	
	
    /** Descrição do status. */
    private String descricao;

	/** Método getter para a propriedade "descricao".
	 *
	 *  @return O valor atual de descricao.
	 */
	public String getDescricao() {
		return this.descricao;
	}

	/** Método setter para a propriedade "descricao".
	 *
	 *  @param Novo valor para descricao.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
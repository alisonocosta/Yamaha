/* Resource Inform�tica
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......StatusInfoMercado.java
 *   .: Cria��o.....05 de junho, 19:34
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descri��o...Entidade "StatusInfoMercado".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;

/** Bean para Status de informa��es de Mercado. 
 * 
 *  @author Thiago Uriel M. Garcia
 */
public class StatusInfoMercado extends EntityObject {

    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;  	
	
    /** Descri��o do status. */
    private String descricao;

	/** M�todo getter para a propriedade "descricao".
	 *
	 *  @return O valor atual de descricao.
	 */
	public String getDescricao() {
		return this.descricao;
	}

	/** M�todo setter para a propriedade "descricao".
	 *
	 *  @param Novo valor para descricao.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......Analista.java
 *   .: Criação.....16 de julho, 09:40
 *   .: Autor.......Gisele Panosso	
 *   .: Descrição...Entidade "Analista".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;

public class Representante extends EntityObject {

	//----[ Atributos de classe e instância ]-----------------------------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;    
    
    /** Armazena o nome, não formatado, do Representante. */
    private String nome;

    //	----[ Métodos Getter ]---------------------------------------------------
    
	/** Método getter para o campo nome
	 * 
	 * @return String
	 *
	 */
	public String getNome() {
		return nome;
	}
    
	//	----[ Métodos Setter ]---------------------------------------------------

	/** Método setter para o campo nome
	 *
	 * @param nome String
	 * 
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}    
}

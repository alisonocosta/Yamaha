/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......Serie.java
 *   .: Criação.....24 de junho 2008, 8:45
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "Serie".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "Serie" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class Serie extends EntityObject {
	
	//----[ Atributos de classe e instância ]-----------------------------------
	    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L; 
    
    /** Código do série */
    private String serie;
       
    /** Descrição da série */
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

	/** Método getter para a propriedade serie
	 *
	 *  @return String de serie
	 */
	public String getSerie() {
		return serie;
	}

	/** Método setter para a propriedade serie
	 *
	 * @param serie String
	 */
	public void setSerie(String serie) {
		this.serie = serie;
	}
}

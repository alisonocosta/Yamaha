/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......Serie.java
 *   .: Cria��o.....24 de junho 2008, 8:45
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "Serie".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "Serie" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class Serie extends EntityObject {
	
	//----[ Atributos de classe e inst�ncia ]-----------------------------------
	    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L; 
    
    /** C�digo do s�rie */
    private String serie;
       
    /** Descri��o da s�rie */
    private String descricao;

	/** M�todo getter para a propriedade descricao
	 *
	 *  @return String de descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/** M�todo setter para a propriedade descricao
	 *
	 * @param descricao String
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/** M�todo getter para a propriedade serie
	 *
	 *  @return String de serie
	 */
	public String getSerie() {
		return serie;
	}

	/** M�todo setter para a propriedade serie
	 *
	 * @param serie String
	 */
	public void setSerie(String serie) {
		this.serie = serie;
	}
}

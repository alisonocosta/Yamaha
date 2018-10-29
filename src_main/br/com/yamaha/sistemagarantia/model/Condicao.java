/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......Condicao.java
 *   .: Cria��o.....21 de junho, 08:04
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "Condicao".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "Condicao" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class Condicao extends EntityObject {
	
	//----[ Atributos de classe e inst�ncia ]-----------------------------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L; 
    
    /** C�digo da Condicao */
    private String condicaoCode;
    
    /** Modelo do item */
    private String descricao;
    
    /** Linha do produto */
    private Linha linha;
    
    //  ----[ M�todos Getter ]---------------------------------------------------


	/** M�todo getter para a propriedade descricao
	 * 
	 *  @return String
	 *
	 */
	public String getDescricao() {
		return descricao.toUpperCase();
	}
	
	/** M�todo getter para a propriedade linha
	 * 
	 *  @return Linha
	 *
	 */
	public Linha getLinha() {
		return linha;
	}


	/** M�todo getter para a propriedade condicaoCode
	 * 
	 *  @return String
	 *
	 */
	public String getCondicaoCode() {
		return condicaoCode;
	}
	
	/** Retorna uma String com a concatena��o de C�digo e a Descri��o
	 * 
	 * @return String
	 */
	public String getDescricaoCode() {
		
		return this.condicaoCode  + " - " + this.descricao;
		
	}

	//	----[ M�todos Setter ]---------------------------------------------------
	
	/** M�todo setter para a propriedade descricao
	 *
	 * @param descricao 
	 *           <code>String</code> a ser designado para descricao.
	 * 
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	/** M�todo setter para a propriedade linha
	 *
	 * @param linha 
	 *           <code>Linha</code> a ser designado para linha.
	 * 
	 */
	public void setLinha(Linha linha) {
		this.linha = linha;
	}

	/** M�todo setter para a propriedade condicaoCode
	 *
	 * @param condicaoCode 
	 *           <code>String</code> a ser designado para condicaoCode.
	 * 
	 */
	public void setCondicaoCode(String condicaoCode) {
		this.condicaoCode = condicaoCode;
	}
    
}

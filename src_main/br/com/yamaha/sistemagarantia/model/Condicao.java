/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......Condicao.java
 *   .: Criação.....21 de junho, 08:04
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "Condicao".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "Condicao" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class Condicao extends EntityObject {
	
	//----[ Atributos de classe e instância ]-----------------------------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L; 
    
    /** Código da Condicao */
    private String condicaoCode;
    
    /** Modelo do item */
    private String descricao;
    
    /** Linha do produto */
    private Linha linha;
    
    //  ----[ Métodos Getter ]---------------------------------------------------


	/** Método getter para a propriedade descricao
	 * 
	 *  @return String
	 *
	 */
	public String getDescricao() {
		return descricao.toUpperCase();
	}
	
	/** Método getter para a propriedade linha
	 * 
	 *  @return Linha
	 *
	 */
	public Linha getLinha() {
		return linha;
	}


	/** Método getter para a propriedade condicaoCode
	 * 
	 *  @return String
	 *
	 */
	public String getCondicaoCode() {
		return condicaoCode;
	}
	
	/** Retorna uma String com a concatenação de Código e a Descrição
	 * 
	 * @return String
	 */
	public String getDescricaoCode() {
		
		return this.condicaoCode  + " - " + this.descricao;
		
	}

	//	----[ Métodos Setter ]---------------------------------------------------
	
	/** Método setter para a propriedade descricao
	 *
	 * @param descricao 
	 *           <code>String</code> a ser designado para descricao.
	 * 
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	/** Método setter para a propriedade linha
	 *
	 * @param linha 
	 *           <code>Linha</code> a ser designado para linha.
	 * 
	 */
	public void setLinha(Linha linha) {
		this.linha = linha;
	}

	/** Método setter para a propriedade condicaoCode
	 *
	 * @param condicaoCode 
	 *           <code>String</code> a ser designado para condicaoCode.
	 * 
	 */
	public void setCondicaoCode(String condicaoCode) {
		this.condicaoCode = condicaoCode;
	}
    
}

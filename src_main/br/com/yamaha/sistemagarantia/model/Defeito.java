/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......Defeito.java
 *   .: Cria��o.....21 de junho, 08:08
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "Defeito".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "Defeito" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class Defeito extends EntityObject {
	
	//----[ Atributos de classe e inst�ncia ]-----------------------------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L; 
    
    /** C�digo do Defeito */
    private String defeitoCode;
    
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
		return descricao;
	}
	
	/** M�todo getter para a propriedade linha
	 * 
	 *  @return Linha
	 *
	 */
	public Linha getLinha() {
		return linha;
	}

	/** M�todo getter para a propriedade defeitoCode
	 * 
	 *  @return String
	 *
	 */
	public String getDefeitoCode() {
		return defeitoCode;
	}
	
	/** Retorna uma String com a concatena��o de C�digo e a Descri��o
	 * 
	 * @return String
	 */
	public String getDescricaoCode() {
		
		return this.defeitoCode  + " - " + this.descricao;
		
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

	/** M�todo setter para a propriedade defeitoCode
	 *
	 * @param defeitoCode 
	 *           <code>String</code> a ser designado para defeitoCode.
	 * 
	 */
	public void setDefeitoCode(String defeitoCode) {
		this.defeitoCode = defeitoCode;
	}
	
    
}

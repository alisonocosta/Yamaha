/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......Sintoma.java
 *   .: Cria��o.....21 de junho, 07:59
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "Sintoma".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "Sintoma" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class Sintoma extends EntityObject {
	
	//----[ Atributos de classe e inst�ncia ]-----------------------------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L; 
    
    /** C�digo do sintoma */
    private String sintomaCode;
    
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
	
	/** M�todo getter para a propriedade sintomaCode
	 * 
	 *  @return String
	 *
	 */
	public String getSintomaCode() {
		return sintomaCode;
	}
	
	/** Retorna uma String com a concatena��o do C�digo e a Descri��o  
	 * 
	 * @return String
	 */
	public String getDescricaoCode(){
		
		return this.sintomaCode + " - " + this.descricao;
		
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

	/** M�todo setter para a propriedade sintomaCode
	 *
	 * @param sintomaCode 
	 *           <code>String</code> a ser designado para sintomaCode.
	 * 
	 */
	public void setSintomaCode(String sintomaCode) {
		this.sintomaCode = sintomaCode;
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
	
    
}

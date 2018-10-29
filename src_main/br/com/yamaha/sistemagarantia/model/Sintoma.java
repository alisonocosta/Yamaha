/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......Sintoma.java
 *   .: Criação.....21 de junho, 07:59
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "Sintoma".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "Sintoma" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class Sintoma extends EntityObject {
	
	//----[ Atributos de classe e instância ]-----------------------------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L; 
    
    /** Código do sintoma */
    private String sintomaCode;
    
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
	
	/** Método getter para a propriedade sintomaCode
	 * 
	 *  @return String
	 *
	 */
	public String getSintomaCode() {
		return sintomaCode;
	}
	
	/** Retorna uma String com a concatenação do Código e a Descrição  
	 * 
	 * @return String
	 */
	public String getDescricaoCode(){
		
		return this.sintomaCode + " - " + this.descricao;
		
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

	/** Método setter para a propriedade sintomaCode
	 *
	 * @param sintomaCode 
	 *           <code>String</code> a ser designado para sintomaCode.
	 * 
	 */
	public void setSintomaCode(String sintomaCode) {
		this.sintomaCode = sintomaCode;
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
	
    
}

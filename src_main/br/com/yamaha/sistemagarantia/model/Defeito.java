/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......Defeito.java
 *   .: Criação.....21 de junho, 08:08
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "Defeito".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "Defeito" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class Defeito extends EntityObject {
	
	//----[ Atributos de classe e instância ]-----------------------------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L; 
    
    /** Código do Defeito */
    private String defeitoCode;
    
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
		return descricao;
	}
	
	/** Método getter para a propriedade linha
	 * 
	 *  @return Linha
	 *
	 */
	public Linha getLinha() {
		return linha;
	}

	/** Método getter para a propriedade defeitoCode
	 * 
	 *  @return String
	 *
	 */
	public String getDefeitoCode() {
		return defeitoCode;
	}
	
	/** Retorna uma String com a concatenação de Código e a Descrição
	 * 
	 * @return String
	 */
	public String getDescricaoCode() {
		
		return this.defeitoCode  + " - " + this.descricao;
		
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

	/** Método setter para a propriedade defeitoCode
	 *
	 * @param defeitoCode 
	 *           <code>String</code> a ser designado para defeitoCode.
	 * 
	 */
	public void setDefeitoCode(String defeitoCode) {
		this.defeitoCode = defeitoCode;
	}
	
    
}

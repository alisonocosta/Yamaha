/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......Servico.java
 *   .: Criação.....18 de junho, 09:01
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "Servico".
 */
package br.com.yamaha.sistemagarantia.model;

import java.util.List;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "Servico" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class Servico extends EntityObject {
	
	//----[ Atributos de classe e instância ]-----------------------------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L; 
    
    /** Código do Serviço */
    private String servicoCode;
    
    /** Modelo do item */
    private String descricao;
    
    /** Linha do produto */
    private Linha linha;
    
    private List recallServicos;
    
    //  ----[ Métodos Getter ]---------------------------------------------------


	/** Método getter para a propriedade descricao
	 * 
	 *  @return String
	 *
	 */
	public String getDescricao() {
		return descricao.toUpperCase();
	}

	/** Método getter para a propriedade servicoCode
	 * 
	 *  @return String
	 *
	 */
	public String getServicoCode() {
		return servicoCode;
	}
	
	/** Método getter para a propriedade linha
	 * 
	 *  @return Linha
	 *
	 */
	public Linha getLinha() {
		return linha;
	}
	
	/** Método getter para a propriedade recallServicos
	 * 
	 *  @return List
	 *
	 */
	public List getRecallServicos() {
		return recallServicos;
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

	/** Método setter para a propriedade servicoCode
	 *
	 * @param servicoCode 
	 *           <code>String</code> a ser designado para servicoCode.
	 * 
	 */
	public void setServicoCode(String servicoCode) {
		this.servicoCode = servicoCode;
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

	/** Método setter para a propriedade recallServicos
	 *
	 * @param recallServicos 
	 *           <code>List</code> a ser designado para recallServicos.
	 * 
	 */
	public void setRecallServicos(List recallServicos) {
		this.recallServicos = recallServicos;
	} 
	
}

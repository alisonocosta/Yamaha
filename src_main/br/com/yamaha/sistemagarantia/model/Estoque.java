/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......Estoque.java
 *   .: Criação.....16 de abril, 13:28
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "Estoque".
 */
package br.com.yamaha.sistemagarantia.model;

import java.util.Date;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "Estoque" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class Estoque extends EntityObject {

    //----[ Atributos de classe e instância ]-----------------------------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L; 
    
    /** Data da apuração do estoque. */
    private Date dataEstoque;
       
    /** Descrição do Concessionaria. */
    private Concessionaria concessionaria;
    
    /** id do faturamento */
    private Long faturamentoId;
    
    //	----[ Métodos Getter ]---------------------------------------------------
	
    
    /** Método getter para a propriedade dataEstoque
	 *
	 *  @return Date de dataEstoque
	 */
	public Date getDataEstoque() {
		return dataEstoque;
	}

	/** Método getter para a propriedade faturamentoId
	 *
	 *  @return Long de faturamentoId
	 */
	public Long getFaturamentoId() {
		return faturamentoId;
	}
	
	/** Método getter para a propriedade concessionaria
	 *
	 *  @return Concessionaria de concessionaria
	 */
	public Concessionaria getConcessionaria() {
		return concessionaria;
	}
	
	//	----[ Métodos Setter ]---------------------------------------------------
    
	/** Método setter para a propriedade concessionaria
	 *
	 * @param concessionaria Concessionaria
	 */
	public void setConcessionaria(Concessionaria concessionaria) {
		this.concessionaria = concessionaria;
	}

	

	/** Método setter para a propriedade dataEstoque
	 *
	 * @param dataEstoque Date
	 */
	public void setDataEstoque(Date dataEstoque) {
		this.dataEstoque = dataEstoque;
	}


	/** Método setter para a propriedade faturamentoId
	 *
	 * @param faturamentoId Long
	 */
	public void setFaturamentoId(Long faturamentoId) {
		this.faturamentoId = faturamentoId;
	}
	
	
	
}
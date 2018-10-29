/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......Estoque.java
 *   .: Cria��o.....16 de abril, 13:28
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "Estoque".
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

    //----[ Atributos de classe e inst�ncia ]-----------------------------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L; 
    
    /** Data da apura��o do estoque. */
    private Date dataEstoque;
       
    /** Descri��o do Concessionaria. */
    private Concessionaria concessionaria;
    
    /** id do faturamento */
    private Long faturamentoId;
    
    //	----[ M�todos Getter ]---------------------------------------------------
	
    
    /** M�todo getter para a propriedade dataEstoque
	 *
	 *  @return Date de dataEstoque
	 */
	public Date getDataEstoque() {
		return dataEstoque;
	}

	/** M�todo getter para a propriedade faturamentoId
	 *
	 *  @return Long de faturamentoId
	 */
	public Long getFaturamentoId() {
		return faturamentoId;
	}
	
	/** M�todo getter para a propriedade concessionaria
	 *
	 *  @return Concessionaria de concessionaria
	 */
	public Concessionaria getConcessionaria() {
		return concessionaria;
	}
	
	//	----[ M�todos Setter ]---------------------------------------------------
    
	/** M�todo setter para a propriedade concessionaria
	 *
	 * @param concessionaria Concessionaria
	 */
	public void setConcessionaria(Concessionaria concessionaria) {
		this.concessionaria = concessionaria;
	}

	

	/** M�todo setter para a propriedade dataEstoque
	 *
	 * @param dataEstoque Date
	 */
	public void setDataEstoque(Date dataEstoque) {
		this.dataEstoque = dataEstoque;
	}


	/** M�todo setter para a propriedade faturamentoId
	 *
	 * @param faturamentoId Long
	 */
	public void setFaturamentoId(Long faturamentoId) {
		this.faturamentoId = faturamentoId;
	}
	
	
	
}
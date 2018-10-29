/* Resource Tecnologia
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......EstoqueVO.java
 *   .: Cria��o.....17 de abril de 2008
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "Estoque".
 */
package br.com.yamaha.sistemagarantia.view;

import java.util.Date;

import br.com.resource.infra.model.EntityObject;
import br.com.resource.infra.utils.DateUtils;

/** Entidade do sistema, representando
 *  um objeto "EstoqueVO" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class EstoqueVO extends EntityObject {
	
	//----[ Atributos de classe e inst�ncia ]-----------------------------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L; 
    
    /** Armazena o Chassi. */
    private String chassi;
    
    /** Armazena a data do apontamento no estoque. */
    private Date dataEstoque;
    
    /** Armazena a data da nota fiscal do faturamento. */
    private Date dataFaturamento;

	/** M�todo getter para a propriedade chassi
	 *
	 *  @return String de chassi
	 */
	public String getChassi() {
		return chassi;
	}

	/** M�todo setter para a propriedade chassi
	 *
	 * @param chassi String
	 */
	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	/** M�todo getter para a propriedade dataEstoque
	 *
	 *  @return Date de dataEstoque
	 */
	public Date getDataEstoque() {
		return dataEstoque;
	}
	
	/** M�todo getter para a propriedade dataEstoque formatada para pt_BR
	 *
	 *  @return String  da dataEstoque formatada para pt_BR
	 */
	public String getStrDataEstoque() {
		return DateUtils.applyMask(dataEstoque);
	}

	/** M�todo setter para a propriedade dataEstoque
	 *
	 * @param dataEstoque Date
	 */
	public void setDataEstoque(Date dataEstoque) {
		this.dataEstoque = dataEstoque;
	}

	/** M�todo getter para a propriedade dataFaturamento
	 *
	 *  @return Date de dataFaturamento
	 */
	public Date getDataFaturamento() {
		return dataFaturamento;
	}
	
	/** M�todo getter para a propriedade dataFaturamento formatada para pt_BR
	 *
	 *  @return Date de dataFaturamento formatada para pt_BR
	 */
	public String getStrDataFaturamento() {
		return DateUtils.applyMask(dataFaturamento);
	}

	/** M�todo setter para a propriedade dataFaturamento
	 *
	 * @param dataFaturamento Date
	 */
	public void setDataFaturamento(Date dataFaturamento) {
		this.dataFaturamento = dataFaturamento;
	}
    
}

/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioGraficosIndividuaisTableVO.java
 *   .: Cria��o.....30 de novembro, 17:55
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "RelatorioGraficosIndividuaisTableVO".
 */

package br.com.yamaha.sistemagarantia.view;

import br.com.resource.infra.model.EntityObject;

public class RelatorioGraficosIndividuaisTableVO extends EntityObject {
	
	//----[ Constantes paras os 4 registros de pe�as da tabela ] --------------
	
	/** Constante da descri��o da primeira pe�a da tabela */
	public static final String DESCRICAO_PECA1 = "p_descricao_peca1";
	
	/** Constante da descri��o da segunda pe�a da tabela */
	public static final String DESCRICAO_PECA2 = "p_descricao_peca2";
	
	/** Constante da descri��o da terceira pe�a da tabela */
	public static final String DESCRICAO_PECA3 = "p_descricao_peca3";
	
	/** Constante da descri��o da quarta pe�a da tabela */
	public static final String DESCRICAO_PECA4 = "p_descricao_peca4";
	
	/** Constante da quantidade da primeira pe�a da tabela */
	public static final String QUANTIDADE_PECA1 = "p_qtde_peca1";
	
	/** Constante da descri��o da segunda pe�a da tabela */
	public static final String QUANTIDADE_PECA2 = "p_qtde_peca2";
	
	/** Constante da descri��o da terceira pe�a da tabela */
	public static final String QUANTIDADE_PECA3 = "p_qtde_peca3";
	
	/** Constante da descri��o da quarta pe�a da tabela */
	public static final String QUANTIDADE_PECA4 = "p_qtde_peca4";
	
	/** Constante da quantidade da primeira pe�a da tabela */
	public static final String VALOR_PECA1 = "p_valor_peca1";
	
	/** Constante da descri��o da segunda pe�a da tabela */
	public static final String VALOR_PECA2 = "p_valor_peca2";
	
	/** Constante da descri��o da terceira pe�a da tabela */
	public static final String VALOR_PECA3 = "p_valor_peca3";
	
	/** Constante da descri��o da quarta pe�a da tabela */
	public static final String VALOR_PECA4 = "p_valor_peca4";
	
	/** Constante do valor total parcial de pe�as da tabela */
	public static final String VALOR_TOTAL_PARCIAL_PECA = "p_total_parcial_valor";
	
	/** Constante do valor total de pe�as da tabela */
	public static final String VALOR_TOTAL_PECA = "total_valor_peca";
	
	/** Constante do valor percentual correspondente ao total parcial do valor de pe�as da tabela */
	public static final String PERCENTUAL_CORRESP = "p_percentual_corresp";
	
	/** Constante do valor percentual correspondente ao total parcial do valor de pe�as da tabela */
	public static final String MES_APURADO = "p_mes_apurado";

	//----[ Atributos de classe e inst�ncia ]---------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;    
  
    /** Armazena o campo c�digo da pe�a do Relat�rio de Gr�ficos Individuais Gr�fico 6. */
    private String itemCode;
               
    /** Armazena o campo descri��o da pe�a do Relat�rio de Gr�ficos Individuais Gr�fico 6. */
    private String descricao;
    
    /** Armazena o campo cep do Relat�rio de Verifica��o. */
    private Double valorGarantia;
    
    /** Armazena o campo quantidade de itens do Relat�rio de Gr�ficos Individuais Gr�fico 6. */
    private Long quantidadeItem;

    //	----[ M�todos Getter ]---------------------------------------------------
    
	/** M�todo getter para a propriedade descricao
	 * 
	 *  @return String
	 *
	 */
	public String getDescricao() {
		return descricao;
	}

	/** M�todo getter para a propriedade itemCode
	 * 
	 *  @return String
	 *
	 */
	public String getItemCode() {
		return itemCode;
	}

	/** M�todo getter para a propriedade quantidadeItem
	 * 
	 *  @return Long
	 *
	 */
	public Long getQuantidadeItem() {
		return quantidadeItem;
	}

	/** M�todo getter para a propriedade valorGarantia
	 * 
	 *  @return Double
	 *
	 */
	public Double getValorGarantia() {
		return valorGarantia;
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

	/** M�todo setter para a propriedade itemCode
	 *
	 * @param itemCode 
	 *           <code>String</code> a ser designado para itemCode.
	 * 
	 */
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	/** M�todo setter para a propriedade quantidadeItem
	 *
	 * @param quantidadeItem 
	 *           <code>Long</code> a ser designado para quantidadeItem.
	 * 
	 */
	public void setQuantidadeItem(Long quantidadeItem) {
		this.quantidadeItem = quantidadeItem;
	}

	/** M�todo setter para a propriedade valorGarantia
	 *
	 * @param valorGarantia 
	 *           <code>Double</code> a ser designado para valorGarantia.
	 * 
	 */
	public void setValorGarantia(Double valorGarantia) {
		this.valorGarantia = valorGarantia;
	} 
}


/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioGraficosIndividuaisTableVO.java
 *   .: Criação.....30 de novembro, 17:55
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "RelatorioGraficosIndividuaisTableVO".
 */

package br.com.yamaha.sistemagarantia.view;

import br.com.resource.infra.model.EntityObject;

public class RelatorioGraficosIndividuaisTableVO extends EntityObject {
	
	//----[ Constantes paras os 4 registros de peças da tabela ] --------------
	
	/** Constante da descrição da primeira peça da tabela */
	public static final String DESCRICAO_PECA1 = "p_descricao_peca1";
	
	/** Constante da descrição da segunda peça da tabela */
	public static final String DESCRICAO_PECA2 = "p_descricao_peca2";
	
	/** Constante da descrição da terceira peça da tabela */
	public static final String DESCRICAO_PECA3 = "p_descricao_peca3";
	
	/** Constante da descrição da quarta peça da tabela */
	public static final String DESCRICAO_PECA4 = "p_descricao_peca4";
	
	/** Constante da quantidade da primeira peça da tabela */
	public static final String QUANTIDADE_PECA1 = "p_qtde_peca1";
	
	/** Constante da descrição da segunda peça da tabela */
	public static final String QUANTIDADE_PECA2 = "p_qtde_peca2";
	
	/** Constante da descrição da terceira peça da tabela */
	public static final String QUANTIDADE_PECA3 = "p_qtde_peca3";
	
	/** Constante da descrição da quarta peça da tabela */
	public static final String QUANTIDADE_PECA4 = "p_qtde_peca4";
	
	/** Constante da quantidade da primeira peça da tabela */
	public static final String VALOR_PECA1 = "p_valor_peca1";
	
	/** Constante da descrição da segunda peça da tabela */
	public static final String VALOR_PECA2 = "p_valor_peca2";
	
	/** Constante da descrição da terceira peça da tabela */
	public static final String VALOR_PECA3 = "p_valor_peca3";
	
	/** Constante da descrição da quarta peça da tabela */
	public static final String VALOR_PECA4 = "p_valor_peca4";
	
	/** Constante do valor total parcial de peças da tabela */
	public static final String VALOR_TOTAL_PARCIAL_PECA = "p_total_parcial_valor";
	
	/** Constante do valor total de peças da tabela */
	public static final String VALOR_TOTAL_PECA = "total_valor_peca";
	
	/** Constante do valor percentual correspondente ao total parcial do valor de peças da tabela */
	public static final String PERCENTUAL_CORRESP = "p_percentual_corresp";
	
	/** Constante do valor percentual correspondente ao total parcial do valor de peças da tabela */
	public static final String MES_APURADO = "p_mes_apurado";

	//----[ Atributos de classe e instância ]---------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;    
  
    /** Armazena o campo código da peça do Relatório de Gráficos Individuais Gráfico 6. */
    private String itemCode;
               
    /** Armazena o campo descrição da peça do Relatório de Gráficos Individuais Gráfico 6. */
    private String descricao;
    
    /** Armazena o campo cep do Relatório de Verificação. */
    private Double valorGarantia;
    
    /** Armazena o campo quantidade de itens do Relatório de Gráficos Individuais Gráfico 6. */
    private Long quantidadeItem;

    //	----[ Métodos Getter ]---------------------------------------------------
    
	/** Método getter para a propriedade descricao
	 * 
	 *  @return String
	 *
	 */
	public String getDescricao() {
		return descricao;
	}

	/** Método getter para a propriedade itemCode
	 * 
	 *  @return String
	 *
	 */
	public String getItemCode() {
		return itemCode;
	}

	/** Método getter para a propriedade quantidadeItem
	 * 
	 *  @return Long
	 *
	 */
	public Long getQuantidadeItem() {
		return quantidadeItem;
	}

	/** Método getter para a propriedade valorGarantia
	 * 
	 *  @return Double
	 *
	 */
	public Double getValorGarantia() {
		return valorGarantia;
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

	/** Método setter para a propriedade itemCode
	 *
	 * @param itemCode 
	 *           <code>String</code> a ser designado para itemCode.
	 * 
	 */
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	/** Método setter para a propriedade quantidadeItem
	 *
	 * @param quantidadeItem 
	 *           <code>Long</code> a ser designado para quantidadeItem.
	 * 
	 */
	public void setQuantidadeItem(Long quantidadeItem) {
		this.quantidadeItem = quantidadeItem;
	}

	/** Método setter para a propriedade valorGarantia
	 *
	 * @param valorGarantia 
	 *           <code>Double</code> a ser designado para valorGarantia.
	 * 
	 */
	public void setValorGarantia(Double valorGarantia) {
		this.valorGarantia = valorGarantia;
	} 
}


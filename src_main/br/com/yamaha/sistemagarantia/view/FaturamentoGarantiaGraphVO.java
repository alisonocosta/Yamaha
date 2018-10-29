/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioZeroKmVO.java
 *   .: Criação.....29 de Outubro de 2007, 12:01
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "FaturamentoGarantiaGraph".
 */
package br.com.yamaha.sistemagarantia.view;

import java.math.BigDecimal;

import br.com.resource.infra.model.EntityObject;

/** Classe para armazenar valores para o relatório gráfico - Faturamento x Garantia
 * 
 */
public class FaturamentoGarantiaGraphVO extends EntityObject {

	//----[ Atributos de classe e instância ]---------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;    
	
    /** Armazena o campo anomes do Relatório Gráfico Faturamento x Garantia. */
    private String anomes;
    
    /** Armazena o campo chassi do Relatório Gráfico Faturamento x Garantia. */
    private String faturamento;
    
    /** Armazena o campo valorFaturado do Relatório Gráfico Faturamento x Garantia. */
    private BigDecimal valorFaturado;

    //	----[ Métodos Getter ]---------------------------------------------------
    
	/** Método getter para a propriedade anomes
	 * 
	 *  @return String
	 *
	 */
	public String getAnomes() {
		return anomes;
	}

	/** Método getter para a propriedade faturamento
	 * 
	 *  @return String
	 *
	 */
	public String getFaturamento() {
		return faturamento;
	}

	/** Método getter para a propriedade valorFaturado
	 * 
	 *  @return BigDecimal
	 *
	 */
	public BigDecimal getValorFaturado() {
		return valorFaturado;
	}
	
	//	----[ Métodos Setter ]---------------------------------------------------

	/** Método setter para a propriedade anomes
	 *
	 * @param anomes 
	 *           <code>String</code> a ser designado para anomes.
	 * 
	 */
	public void setAnomes(String anomes) {
		this.anomes = anomes;
	}

	/** Método setter para a propriedade faturamento
	 *
	 * @param faturamento 
	 *           <code>String</code> a ser designado para faturamento.
	 * 
	 */
	public void setFaturamento(String faturamento) {
		this.faturamento = faturamento;
	}

	/** Método setter para a propriedade valorFaturado
	 *
	 * @param valorFaturado 
	 *           <code>BigDecimal</code> a ser designado para valorFaturado.
	 * 
	 */
	public void setValorFaturado(BigDecimal valorFaturado) {
		this.valorFaturado = valorFaturado;
	} 
}

/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioZeroKmVO.java
 *   .: Cria��o.....29 de Outubro de 2007, 12:01
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "FaturamentoGarantiaGraph".
 */
package br.com.yamaha.sistemagarantia.view;

import java.math.BigDecimal;

import br.com.resource.infra.model.EntityObject;

/** Classe para armazenar valores para o relat�rio gr�fico - Faturamento x Garantia
 * 
 */
public class FaturamentoGarantiaGraphVO extends EntityObject {

	//----[ Atributos de classe e inst�ncia ]---------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;    
	
    /** Armazena o campo anomes do Relat�rio Gr�fico Faturamento x Garantia. */
    private String anomes;
    
    /** Armazena o campo chassi do Relat�rio Gr�fico Faturamento x Garantia. */
    private String faturamento;
    
    /** Armazena o campo valorFaturado do Relat�rio Gr�fico Faturamento x Garantia. */
    private BigDecimal valorFaturado;

    //	----[ M�todos Getter ]---------------------------------------------------
    
	/** M�todo getter para a propriedade anomes
	 * 
	 *  @return String
	 *
	 */
	public String getAnomes() {
		return anomes;
	}

	/** M�todo getter para a propriedade faturamento
	 * 
	 *  @return String
	 *
	 */
	public String getFaturamento() {
		return faturamento;
	}

	/** M�todo getter para a propriedade valorFaturado
	 * 
	 *  @return BigDecimal
	 *
	 */
	public BigDecimal getValorFaturado() {
		return valorFaturado;
	}
	
	//	----[ M�todos Setter ]---------------------------------------------------

	/** M�todo setter para a propriedade anomes
	 *
	 * @param anomes 
	 *           <code>String</code> a ser designado para anomes.
	 * 
	 */
	public void setAnomes(String anomes) {
		this.anomes = anomes;
	}

	/** M�todo setter para a propriedade faturamento
	 *
	 * @param faturamento 
	 *           <code>String</code> a ser designado para faturamento.
	 * 
	 */
	public void setFaturamento(String faturamento) {
		this.faturamento = faturamento;
	}

	/** M�todo setter para a propriedade valorFaturado
	 *
	 * @param valorFaturado 
	 *           <code>BigDecimal</code> a ser designado para valorFaturado.
	 * 
	 */
	public void setValorFaturado(BigDecimal valorFaturado) {
		this.valorFaturado = valorFaturado;
	} 
}

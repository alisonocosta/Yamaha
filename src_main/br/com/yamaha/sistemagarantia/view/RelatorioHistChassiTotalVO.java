/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioHistChassiTotalVO.java
 *   .: Criação.....12 de agosto, 16:00
 *   .: Autor.......Gisele Panosso
 *   .: Descrição...Entidade "RelatorioHistChassiTotalVO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.math.BigDecimal;

import br.com.resource.infra.model.EntityObject;

public class RelatorioHistChassiTotalVO extends EntityObject {
	
	//----[ Atributos de classe e instância ]---------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;    
    
    /** Armazena o campo vlGarantia do Relatório Consulta Histórico Chassi. */
    private BigDecimal vlGarantia;
    
    /** Armazena o campo vlPecas do Relatório Consulta Histórico Chassi. */
    private BigDecimal vlPecas;
    
    /** Armazena o campo vlTerceiros do Relatório Consulta Histórico Chassi. */
    private BigDecimal vlTerceiros;
    
    /** Armazena o campo vlRevisao do Relatório Consulta Histórico Chassi. */
    private BigDecimal vlRevisao;
    
    
    //	----[ Métodos Getter ]---------------------------------------------------

	/** Método getter para a propriedade vlGarantia.
	 *
	 *  @return o valor atual de vlGarantia.
	 */
	public BigDecimal getVlGarantia() {
		return vlGarantia;
	}

	/** Método getter para a propriedade vlPecas.
	 *
	 *  @return o valor atual de vlPecas.
	 */
	public BigDecimal getVlPecas() {
		return vlPecas;
	}

	/** Método getter para a propriedade vlRevisao.
	 *
	 *  @return o valor atual de vlRevisao.
	 */
	public BigDecimal getVlRevisao() {
		return vlRevisao;
	}

	/** Método getter para a propriedade vlTerceiros.
	 *
	 *  @return o valor atual de vlTerceiros.
	 */
	public BigDecimal getVlTerceiros() {
		return vlTerceiros;
	}

	   
    //	----[ Métodos Setter ]---------------------------------------------------

	/** Obtém o valur atual de vlGarantia.
	 * 
	 *  @param vlGarantia 
	 *    O novo valor para vlGarantia.
	 */
	public void setVlGarantia(BigDecimal vlGarantia) {
		this.vlGarantia = vlGarantia;
	}

	/** Obtém o valur atual de vlPecas.
	 * 
	 *  @param vlPecas 
	 *    O novo valor para vlPecas.
	 */
	public void setVlPecas(BigDecimal vlPecas) {
		this.vlPecas = vlPecas;
	}

	/** Obtém o valur atual de vlRevisao.
	 * 
	 *  @param vlRevisao 
	 *    O novo valor para vlRevisao.
	 */
	public void setVlRevisao(BigDecimal vlRevisao) {
		this.vlRevisao = vlRevisao;
	}

	/** Obtém o valur atual de vlTerceiros.
	 * 
	 *  @param vlTerceiros 
	 *    O novo valor para vlTerceiros.
	 */
	public void setVlTerceiros(BigDecimal vlTerceiros) {
		this.vlTerceiros = vlTerceiros;
	}
    

}

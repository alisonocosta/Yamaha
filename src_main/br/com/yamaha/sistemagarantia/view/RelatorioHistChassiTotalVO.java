/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioHistChassiTotalVO.java
 *   .: Cria��o.....12 de agosto, 16:00
 *   .: Autor.......Gisele Panosso
 *   .: Descri��o...Entidade "RelatorioHistChassiTotalVO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.math.BigDecimal;

import br.com.resource.infra.model.EntityObject;

public class RelatorioHistChassiTotalVO extends EntityObject {
	
	//----[ Atributos de classe e inst�ncia ]---------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;    
    
    /** Armazena o campo vlGarantia do Relat�rio Consulta Hist�rico Chassi. */
    private BigDecimal vlGarantia;
    
    /** Armazena o campo vlPecas do Relat�rio Consulta Hist�rico Chassi. */
    private BigDecimal vlPecas;
    
    /** Armazena o campo vlTerceiros do Relat�rio Consulta Hist�rico Chassi. */
    private BigDecimal vlTerceiros;
    
    /** Armazena o campo vlRevisao do Relat�rio Consulta Hist�rico Chassi. */
    private BigDecimal vlRevisao;
    
    
    //	----[ M�todos Getter ]---------------------------------------------------

	/** M�todo getter para a propriedade vlGarantia.
	 *
	 *  @return o valor atual de vlGarantia.
	 */
	public BigDecimal getVlGarantia() {
		return vlGarantia;
	}

	/** M�todo getter para a propriedade vlPecas.
	 *
	 *  @return o valor atual de vlPecas.
	 */
	public BigDecimal getVlPecas() {
		return vlPecas;
	}

	/** M�todo getter para a propriedade vlRevisao.
	 *
	 *  @return o valor atual de vlRevisao.
	 */
	public BigDecimal getVlRevisao() {
		return vlRevisao;
	}

	/** M�todo getter para a propriedade vlTerceiros.
	 *
	 *  @return o valor atual de vlTerceiros.
	 */
	public BigDecimal getVlTerceiros() {
		return vlTerceiros;
	}

	   
    //	----[ M�todos Setter ]---------------------------------------------------

	/** Obt�m o valur atual de vlGarantia.
	 * 
	 *  @param vlGarantia 
	 *    O novo valor para vlGarantia.
	 */
	public void setVlGarantia(BigDecimal vlGarantia) {
		this.vlGarantia = vlGarantia;
	}

	/** Obt�m o valur atual de vlPecas.
	 * 
	 *  @param vlPecas 
	 *    O novo valor para vlPecas.
	 */
	public void setVlPecas(BigDecimal vlPecas) {
		this.vlPecas = vlPecas;
	}

	/** Obt�m o valur atual de vlRevisao.
	 * 
	 *  @param vlRevisao 
	 *    O novo valor para vlRevisao.
	 */
	public void setVlRevisao(BigDecimal vlRevisao) {
		this.vlRevisao = vlRevisao;
	}

	/** Obt�m o valur atual de vlTerceiros.
	 * 
	 *  @param vlTerceiros 
	 *    O novo valor para vlTerceiros.
	 */
	public void setVlTerceiros(BigDecimal vlTerceiros) {
		this.vlTerceiros = vlTerceiros;
	}
    

}

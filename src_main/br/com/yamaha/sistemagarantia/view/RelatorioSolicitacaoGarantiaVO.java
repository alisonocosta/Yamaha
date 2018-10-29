/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioSolicitacaoGarantiaVO.java
 *   .: Criação.....16 de agosto, 08:32
 *   .: Autor.......Gisele Panosso
 *   .: Descrição...Entidade "RelatorioSolicitacaoGarantiaVO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.math.BigDecimal;

import br.com.resource.infra.model.EntityObject;

public class RelatorioSolicitacaoGarantiaVO extends EntityObject {

	//----[ Atributos de classe e instância ]---------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;    
	
    /** Armazena o campo numeroSG do Relatório Solicitação Garantia */
    private BigDecimal numeroSG;
    
    /** Armazena o campo chassi do Relatório Solicitação Garantia */
    private String chassi;
    

	/** Método getter para a propriedade chassi.
	 *
	 *  @return o valor atual de chassi.
	 */
	public String getChassi() {
		return chassi;
	}

	/** Método getter para a propriedade numeroSG.
	 *
	 *  @return o valor atual de numeroSG.
	 */
	public BigDecimal getNumeroSG() {
		return numeroSG;
	}

	/** Obtém o valur atual de chassi.
	 * 
	 *  @param chassi 
	 *    O novo valor para chassi.
	 */
	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	/** Obtém o valur atual de numeroSG.
	 * 
	 *  @param numeroSG 
	 *    O novo valor para numeroSG.
	 */
	public void setNumeroSG(BigDecimal numeroSG) {
		this.numeroSG = numeroSG;
	}	

}

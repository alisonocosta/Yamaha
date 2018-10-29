/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioHistChassiParecerVO.java
 *   .: Cria��o.....23 de agosto, 15:00
 *   .: Autor.......Gisele Panosso
 *   .: Descri��o...Entidade "RelatorioHistChassiParecerVO".
 */
package br.com.yamaha.sistemagarantia.view;

import br.com.resource.infra.model.EntityObject;

public class RelatorioHistChassiParecerVO extends EntityObject {
	
	//----[ Atributos de classe e inst�ncia ]---------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;    
    
    /** Armazena o campo parecerAnalista do Relat�rio Consulta Hist�rico Chassi. */
    private String parecerAnalista;
	
    
	/** M�todo getter para a propriedade parecerAnalista.
	 *
	 *  @return o valor atual de parecerAnalista.
	 */
	public String getParecerAnalista() {
		return parecerAnalista;
	}
	

	/** Obt�m o valur atual de parecerAnalista.
	 * 
	 *  @param parecerAnalista 
	 *    O novo valor para parecerAnalista.
	 */
	public void setParecerAnalista(String parecerAnalista) {
		this.parecerAnalista = parecerAnalista;
	}	
}

/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioHistChassiParecerVO.java
 *   .: Criação.....23 de agosto, 15:00
 *   .: Autor.......Gisele Panosso
 *   .: Descrição...Entidade "RelatorioHistChassiParecerVO".
 */
package br.com.yamaha.sistemagarantia.view;

import br.com.resource.infra.model.EntityObject;

public class RelatorioHistChassiParecerVO extends EntityObject {
	
	//----[ Atributos de classe e instância ]---------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;    
    
    /** Armazena o campo parecerAnalista do Relatório Consulta Histórico Chassi. */
    private String parecerAnalista;
	
    
	/** Método getter para a propriedade parecerAnalista.
	 *
	 *  @return o valor atual de parecerAnalista.
	 */
	public String getParecerAnalista() {
		return parecerAnalista;
	}
	

	/** Obtém o valur atual de parecerAnalista.
	 * 
	 *  @param parecerAnalista 
	 *    O novo valor para parecerAnalista.
	 */
	public void setParecerAnalista(String parecerAnalista) {
		this.parecerAnalista = parecerAnalista;
	}	
}

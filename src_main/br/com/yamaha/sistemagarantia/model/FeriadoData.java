/* Resource Inform�tica
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......FeriadoData.java
 *   .: Cria��o.....25 de maio, 14:00
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "FeriadoData".
 */
package br.com.yamaha.sistemagarantia.model;

import java.util.Date;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "FeriadoData" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class FeriadoData extends EntityObject {
	
	
	//----[ Atributos de classe e inst�ncia ]-----------------------------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;   
    
    /** Data do Feriado */
    private Date dataFeriado;

    //	----[ M�todos Getter ]---------------------------------------------------
    
	/** M�todo getter para a propriedade dataFeriado
	 * 
	 *  @return Date
	 *
	 */
	public Date getDataFeriado() {
		return dataFeriado;
	}

	//	----[ M�todos Setter ]---------------------------------------------------
	
	/** M�todo setter para a propriedade dataFeriado
	 *
	 * @param dataFeriado 
	 *           <code>Date</code> a ser designado para dataFeriado.
	 * 
	 */
	public void setDataFeriado(Date dataFeriado) {
		this.dataFeriado = dataFeriado;
	} 
	
}

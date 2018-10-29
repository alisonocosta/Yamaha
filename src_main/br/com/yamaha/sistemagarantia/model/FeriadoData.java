/* Resource Informática
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......FeriadoData.java
 *   .: Criação.....25 de maio, 14:00
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "FeriadoData".
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
	
	
	//----[ Atributos de classe e instância ]-----------------------------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;   
    
    /** Data do Feriado */
    private Date dataFeriado;

    //	----[ Métodos Getter ]---------------------------------------------------
    
	/** Método getter para a propriedade dataFeriado
	 * 
	 *  @return Date
	 *
	 */
	public Date getDataFeriado() {
		return dataFeriado;
	}

	//	----[ Métodos Setter ]---------------------------------------------------
	
	/** Método setter para a propriedade dataFeriado
	 *
	 * @param dataFeriado 
	 *           <code>Date</code> a ser designado para dataFeriado.
	 * 
	 */
	public void setDataFeriado(Date dataFeriado) {
		this.dataFeriado = dataFeriado;
	} 
	
}

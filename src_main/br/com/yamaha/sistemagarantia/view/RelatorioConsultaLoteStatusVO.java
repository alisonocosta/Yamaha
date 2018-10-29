/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioConsultaStatusLoteVO.java
 *   .: Criação.....10 de agosto, 17:39
 *   .: Autor.......Gisele Panosso
 *   .: Descrição...Entidade "RelatorioConsultaStatusLoteVO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.util.Date;

import br.com.resource.infra.model.EntityObject;
import br.com.resource.infra.utils.DateUtils;

public class RelatorioConsultaLoteStatusVO extends EntityObject {

	//----[ Atributos de classe e instância ]---------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;    
    
    /** Armazena o campo dtStatus do Relatório Consulta de Lotes. */
    private Date dtStatus;  
    
    /** Armazena o campo descStatusAnterior do Relatório Consulta de Lotes. */
    private String descStatusAnterior;
    
    
    //	----[ Método Getter ]---------------------------------------------------  

    public String getMaskedDtStatus() {
    	
    	return DateUtils.applyMask( this.getDtStatus() );
    	
    }    

	/** Método getter para a propriedade descStatusAnterior.
	 *
	 *  @return o valor atual de descStatusAnterior.
	 */
	public String getDescStatusAnterior() {
		return descStatusAnterior;
	}

	/** Método getter para a propriedade dtStatus.
	 *
	 *  @return o valor atual de dtStatus.
	 */
	public Date getDtStatus() {
		return dtStatus;
	}
	
    //	----[ Método Setter ]---------------------------------------------------  
	
	/** Obtém o valur atual de descStatusAnterior.
	 * 
	 *  @param descStatusAnterior 
	 *    O novo valor para descStatusAnterior.
	 */
	public void setDescStatusAnterior(String descStatusAnterior) {
		this.descStatusAnterior = descStatusAnterior;
	}

	/** Obtém o valur atual de dtStatus.
	 * 
	 *  @param dtStatus 
	 *    O novo valor para dtStatus.
	 */
	public void setDtStatus(Date dtStatus) {
		this.dtStatus = dtStatus;
	}    
    
}
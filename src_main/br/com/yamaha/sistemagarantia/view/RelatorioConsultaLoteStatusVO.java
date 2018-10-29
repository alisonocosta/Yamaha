/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioConsultaStatusLoteVO.java
 *   .: Cria��o.....10 de agosto, 17:39
 *   .: Autor.......Gisele Panosso
 *   .: Descri��o...Entidade "RelatorioConsultaStatusLoteVO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.util.Date;

import br.com.resource.infra.model.EntityObject;
import br.com.resource.infra.utils.DateUtils;

public class RelatorioConsultaLoteStatusVO extends EntityObject {

	//----[ Atributos de classe e inst�ncia ]---------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;    
    
    /** Armazena o campo dtStatus do Relat�rio Consulta de Lotes. */
    private Date dtStatus;  
    
    /** Armazena o campo descStatusAnterior do Relat�rio Consulta de Lotes. */
    private String descStatusAnterior;
    
    
    //	----[ M�todo Getter ]---------------------------------------------------  

    public String getMaskedDtStatus() {
    	
    	return DateUtils.applyMask( this.getDtStatus() );
    	
    }    

	/** M�todo getter para a propriedade descStatusAnterior.
	 *
	 *  @return o valor atual de descStatusAnterior.
	 */
	public String getDescStatusAnterior() {
		return descStatusAnterior;
	}

	/** M�todo getter para a propriedade dtStatus.
	 *
	 *  @return o valor atual de dtStatus.
	 */
	public Date getDtStatus() {
		return dtStatus;
	}
	
    //	----[ M�todo Setter ]---------------------------------------------------  
	
	/** Obt�m o valur atual de descStatusAnterior.
	 * 
	 *  @param descStatusAnterior 
	 *    O novo valor para descStatusAnterior.
	 */
	public void setDescStatusAnterior(String descStatusAnterior) {
		this.descStatusAnterior = descStatusAnterior;
	}

	/** Obt�m o valur atual de dtStatus.
	 * 
	 *  @param dtStatus 
	 *    O novo valor para dtStatus.
	 */
	public void setDtStatus(Date dtStatus) {
		this.dtStatus = dtStatus;
	}    
    
}
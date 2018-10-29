/* Resource Inform�tica
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......HistAcaoTomada.java
 *   .: Cria��o.....25 de maio, 14:00
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descri��o...Entidade "HistCausaProblema".
 */
package br.com.yamaha.sistemagarantia.model;

import java.io.Serializable;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema.
 * 
 *  @author Thiago Uriel M. Garcia
 */
public class HistAcaoTomada extends EntityObject {

    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L; 
    
    private Serializable infoMercadoId;
    private String acaoTomada;
    
	/** M�todo getter para o campo "acaoTomada".
	 *  
	 *  @return O valor atual de "acaoTomada".
	 */
	public String getAcaoTomada() {
		return acaoTomada;
	}
	/** M�todo getter para o campo "infoMercadoId".
	 *  
	 *  @return O valor atual de "infoMercadoId".
	 */
	public Serializable getInfoMercadoId() {
		return infoMercadoId;
	}
	/** M�todo setter para o campo "acaoTomada".
	 * 
	 *  @param acaoTomada 
	 *    O novo valor para "acaoTomada".
	 */
	public void setAcaoTomada(String acaoTomada) {
		this.acaoTomada = acaoTomada;
	}
	/** M�todo setter para o campo "infoMercadoId".
	 * 
	 *  @param infoMercadoId 
	 *    O novo valor para "infoMercadoId".
	 */
	public void setInfoMercadoId(Serializable infoMercadoId) {
		this.infoMercadoId = infoMercadoId;
	}

    

}
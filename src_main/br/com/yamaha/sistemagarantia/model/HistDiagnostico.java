/* Resource Inform�tica
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......HistDiagnostico.java
 *   .: Cria��o.....25 de maio, 14:00
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descri��o...Entidade "HistDiagnostico".
 */
package br.com.yamaha.sistemagarantia.model;

import java.io.Serializable;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema.
 * 
 *  @author Thiago Uriel M. Garcia
 */
public class HistDiagnostico extends EntityObject {

    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L; 
    
    private Serializable infoMercadoId;
    private String diagnostico;
    
	/** M�todo getter para o campo "diagnostico".
	 *  
	 *  @return O valor atual de "diagnostico".
	 */
	public String getDiagnostico() {
		return diagnostico;
	}
	/** M�todo getter para o campo "infoMercadoId".
	 *  
	 *  @return O valor atual de "infoMercadoId".
	 */
	public Serializable getInfoMercadoId() {
		return infoMercadoId;
	}
	/** M�todo setter para o campo "diagnostico".
	 * 
	 *  @param diagnostico 
	 *    O novo valor para "diagnostico".
	 */
	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
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
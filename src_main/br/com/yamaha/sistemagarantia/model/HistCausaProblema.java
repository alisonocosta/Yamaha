/* Resource Informática
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......HistCausaProblema.java
 *   .: Criação.....25 de maio, 14:00
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Entidade "HistCausaProblema".
 */
package br.com.yamaha.sistemagarantia.model;

import java.io.Serializable;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema.
 * 
 *  @author Thiago Uriel M. Garcia
 */
public class HistCausaProblema extends EntityObject {

    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L; 
    
    private Serializable infoMercadoId;
    private String causaProblema;
    
	/** Método getter para o campo "causaProblema".
	 *  
	 *  @return O valor atual de "causaProblema".
	 */
	public String getCausaProblema() {
		return causaProblema;
	}
	/** Método getter para o campo "infoMercadoId".
	 *  
	 *  @return O valor atual de "infoMercadoId".
	 */
	public Serializable getInfoMercadoId() {
		return infoMercadoId;
	}
	/** Método setter para o campo "causaProblema".
	 * 
	 *  @param causaProblema 
	 *    O novo valor para "causaProblema".
	 */
	public void setCausaProblema(String causaProblema) {
		this.causaProblema = causaProblema;
	}
	/** Método setter para o campo "infoMercadoId".
	 * 
	 *  @param infoMercadoId 
	 *    O novo valor para "infoMercadoId".
	 */
	public void setInfoMercadoId(Serializable infoMercadoId) {
		this.infoMercadoId = infoMercadoId;
	}

}
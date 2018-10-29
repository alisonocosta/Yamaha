/* Resource Informática
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......HistAcaoTomada.java
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
public class HistAcaoTomada extends EntityObject {

    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L; 
    
    private Serializable infoMercadoId;
    private String acaoTomada;
    
	/** Método getter para o campo "acaoTomada".
	 *  
	 *  @return O valor atual de "acaoTomada".
	 */
	public String getAcaoTomada() {
		return acaoTomada;
	}
	/** Método getter para o campo "infoMercadoId".
	 *  
	 *  @return O valor atual de "infoMercadoId".
	 */
	public Serializable getInfoMercadoId() {
		return infoMercadoId;
	}
	/** Método setter para o campo "acaoTomada".
	 * 
	 *  @param acaoTomada 
	 *    O novo valor para "acaoTomada".
	 */
	public void setAcaoTomada(String acaoTomada) {
		this.acaoTomada = acaoTomada;
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
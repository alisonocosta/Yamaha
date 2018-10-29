/* Resource Informática
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......HistSintoma.java
 *   .: Criação.....25 de maio, 14:00
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Entidade "HistSintoma".
 */
package br.com.yamaha.sistemagarantia.model;

import java.io.Serializable;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema.
 * 
 *  @author Thiago Uriel M. Garcia
 */
public class HistSintoma extends EntityObject {

    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L; 
    
    private Serializable infoMercadoId;
    private String sintoma;
    
	/** Método getter para o campo "infoMercadoId".
	 *  
	 *  @return O valor atual de "infoMercadoId".
	 */
	public Serializable getInfoMercadoId() {
		return infoMercadoId;
	}
	/** Método getter para o campo "sintoma".
	 *  
	 *  @return O valor atual de "sintoma".
	 */
	public String getSintoma() {
		return sintoma;
	}
	/** Método setter para o campo "infoMercadoId".
	 * 
	 *  @param infoMercadoId 
	 *    O novo valor para "infoMercadoId".
	 */
	public void setInfoMercadoId(Serializable infoMercadoId) {
		this.infoMercadoId = infoMercadoId;
	}
	/** Método setter para o campo "sintoma".
	 * 
	 *  @param sintoma 
	 *    O novo valor para "sintoma".
	 */
	public void setSintoma(String sintoma) {
		this.sintoma = sintoma;
	}
	
}
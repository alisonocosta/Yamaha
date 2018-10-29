/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......GarantiaLineId.java
 *   .: Criação.....01 de junho, 10:48
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Classe para chave composta da Entidade "GarantiaLine".
 */
package br.com.yamaha.sistemagarantia.model.id;

import java.io.Serializable;

/** Classe para chave composta da Entidade "GarantiaLine".
 *  
 *  @author Thiago Uriel M. Garcia
 */
public class GarantiaLineId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Serializable garantiaId;
	private Serializable lineId;
	
	/** Construtor Padrão */
    public GarantiaLineId() {
    	
    	super();
    	
    }
	
	public GarantiaLineId( Serializable garantiaId, Serializable lineId ) {
		
		this.garantiaId = garantiaId;
		this.lineId = lineId;
		
	}
	
	/** Sobrecarga do método equals para chave composta
	 * 
	 *  @param Object
	 *  
	 *  @return boolean
	 *  
	 */
	public boolean equals(Object o) {
		
		final GarantiaLineId garantiaLineId = (GarantiaLineId) o;
		
		//if ( this.equals(o) ) 
			//return true;
		
		if ( o == null ) 
			return false;
		
		if ( !(o instanceof GarantiaLineId) ) 
			return false;		
		
		if ( !(this.garantiaId.equals(garantiaLineId.getGarantiaId())) )
			return false;
		
		if ( !(this.lineId.equals(garantiaLineId.getLineId())) )
			return false;
		
		return true;
		
	}

	/**
	 * @return the garantiaId
	 */
	public Serializable getGarantiaId() {
		return garantiaId;
	}

	/**
	 * @param garantiaId the garantiaId to set
	 */
	public void setGarantiaId(Serializable garantiaId) {
		this.garantiaId = garantiaId;
	}

	/**
	 * @return the lineId
	 */
	public Serializable getLineId() {
		return lineId;
	}

	/**
	 * @param lineId the lineId to set
	 */
	public void setLineId(Serializable lineId) {
		this.lineId = lineId;
	}	
	
}
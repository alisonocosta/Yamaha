/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......DescricaoDefeitoId.java
 *   .: Criação.....23 de maio, 16:43
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Classe para chave composta da Entidade "DescricaoDefeito".
 */
package br.com.yamaha.sistemagarantia.model.id;

import java.io.Serializable;

/** Classe para chave composta da Entidade "DescricaoDefeito".
 * 
 *  @author Thiago Uriel M. Garcia 
 */
public class DescricaoDefeitoId implements Serializable {

	private static final long serialVersionUID = 1L;

	private Serializable garantiaId;
	private Serializable descricaoDefeitoSeq;
	
	public DescricaoDefeitoId(Serializable garantiaId, Serializable descricaoDefeitoSeq) {
		
		super();
		
		this.garantiaId = garantiaId;
		this.descricaoDefeitoSeq = descricaoDefeitoSeq;
		
	}

	/** Sobrecarga do método equals para chave composta
	 * 
	 *  @param Object
	 *  
	 *  @return boolean
	 *  
	 */
	public boolean equals(Object o) {
		
		final DescricaoDefeitoId descricaoDefeitoId = (DescricaoDefeitoId) o;
		
		if ( this.equals(o) ) 
			return true;
		
		if ( o == null ) 
			return false;
		
		if ( !(o instanceof DescricaoDefeitoId) ) 
			return false;		
		
		if ( !(this.garantiaId == descricaoDefeitoId.getGarantiaId()) )
			return false;
		
		if ( !(this.descricaoDefeitoSeq == descricaoDefeitoId.getDescricaoDefeitoSeq()) )
			return false;
		
		return true;
		
	}	
	
	/**
	 * @return the descricaoDefeitoSeq
	 */
	public Serializable getDescricaoDefeitoSeq() {
		return descricaoDefeitoSeq;
	}

	/**
	 * @param descricaoDefeitoSeq the descricaoDefeitoSeq to set
	 */
	public void setDescricaoDefeitoSeq(Serializable descricaoDefeitoSeq) {
		this.descricaoDefeitoSeq = descricaoDefeitoSeq;
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
	
}
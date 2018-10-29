/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......TabelaPrecoId.java
 *   .: Criação.....01 de junho, 10:48
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Classe para chave composta da Entidade "TabelaPreco".
 */
package br.com.yamaha.sistemagarantia.model.id;

import java.io.Serializable;

/** Classe para chave composta da Entidade "TabelaPreco".
 *  
 *  @author Thiago Uriel M. Garcia
 */
public class TabelaPrecoId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Serializable organizationId;
	private Serializable itemId;
	
	public TabelaPrecoId() {
		super();
	}

	public TabelaPrecoId( Serializable organizationId, Serializable itemId ) {
		
		this.organizationId = organizationId;
		this.itemId = itemId;
		
	}
	
	/** Sobrecarga do método equals para chave composta
	 * 
	 *  @param Object
	 *  
	 *  @return boolean
	 *  
	 */
	public boolean equals(Object o) {
		
		final TabelaPrecoId tabelaPrecoId = (TabelaPrecoId) o;
		
		if ( this.equals(o) ) 
			return true;
		
		if ( o == null ) 
			return false;
		
		if ( !(o instanceof TabelaPrecoId) ) 
			return false;		
		
		if ( !(this.organizationId == tabelaPrecoId.getOrganizationId()) )
			return false;
		
		if ( !(this.itemId == tabelaPrecoId.getItemId()) )
			return false;
		
		return true;
		
	}	
	
	
	/**
	 * @return the itemId
	 */
	public Serializable getItemId() {
		return itemId;
	}
	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(Serializable itemId) {
		this.itemId = itemId;
	}
	/**
	 * @return the organizationId
	 */
	public Serializable getOrganizationId() {
		return organizationId;
	}
	/**
	 * @param organizationId the organizationId to set
	 */
	public void setOrganizationId(Serializable organizationId) {
		this.organizationId = organizationId;
	}
	
	
	
}
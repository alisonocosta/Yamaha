/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......AlertCupomId.java
 *   .: Criação.....21 de maio, 10:00
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Classe para chave composta da Entidade "AlertCupom".
 */
package br.com.yamaha.sistemagarantia.model.id;

import java.io.Serializable;

/** Classe para chave composta da Entidade "AlertCupom".
 *  
 *  @author Edson Luiz Sumensari
 */
public class AlertCupomId implements Serializable {

    //----[ Atributos de classe e instância ]-----------------------------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L; 
    
    /** Id do cupom */
    private Serializable cupomId;
    
    /** Número do alert */
    private Serializable alertCupomSeq;
    
    /** Construtor Padrão */
    public AlertCupomId() {
    	
    	super();
    	
    }
    
    /** Construtor que recebe os valores para definição do objeto
     * 
     * @param <code>Serializable</code> alertCupomSeq
     * @param <code>Serializable</code> cupomId
     */    
    public AlertCupomId( Serializable alertCupomSeq, Serializable cupomId ) {
    	
    	this.cupomId 		= cupomId;
    	this.alertCupomSeq  = alertCupomSeq;
    	
    }
    
    //	----[ Métodos Getter ]---------------------------------------------------

	/** Método getter para a propriedade alertCupomSeq
	 * 
	 *  @return Serializable
	 *
	 */
	public Serializable getAlertCupomSeq() {
		return alertCupomSeq;
	}

	/** Método getter para a propriedade cupomId
	 * 
	 *  @return Serializable
	 *
	 */
	public Serializable getCupomId() {
		return cupomId;
	}
	
	//	----[ Métodos Setter ]---------------------------------------------------

	/** Método setter para a propriedade alertCupomSeq
	 *
	 * @param alertCupomSeq 
	 *           <code>Serializable</code> a ser designado para alertCupomSeq.
	 * 
	 */
	public void setAlertCupomSeq(Serializable alertCupomSeq) {
		this.alertCupomSeq = alertCupomSeq;
	}

	/** Método setter para a propriedade cupomId
	 *
	 * @param cupomId 
	 *           <code>Serializable</code> a ser designado para cupomId.
	 * 
	 */
	public void setCupomId(Serializable cupomId) {
		this.cupomId = cupomId;
	}
	
	/** Sobrecarga do método equals para chave composta
	 * 
	 *  @param Object
	 *  
	 *  @return boolean
	 *  
	 */
	public boolean equals(Object o) {
		
		final AlertCupomId alertCupomId = (AlertCupomId) o;
		
		if ( this.equals(o) ) 
			return true;
		
		if ( o == null ) 
			return false;
		
		if ( !(o instanceof AlertCupomId) ) 
			return false;		
		
		if ( !(this.cupomId == alertCupomId.getCupomId()) )
			return false;
		
		if ( !(this.alertCupomSeq == alertCupomId.getAlertCupomSeq()) )
			return false;
		
		return true;
		
	}
		
	/** Sobre do método hasCode
	 * 
	 * @return int
	 * 
	 */
	public int hashCode() {
		
		return this.hashCode();
		
	}
    
}
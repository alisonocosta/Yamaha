/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......AlertCupomId.java
 *   .: Cria��o.....21 de maio, 10:00
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Classe para chave composta da Entidade "AlertCupom".
 */
package br.com.yamaha.sistemagarantia.model.id;

import java.io.Serializable;

/** Classe para chave composta da Entidade "AlertCupom".
 *  
 *  @author Edson Luiz Sumensari
 */
public class AlertCupomId implements Serializable {

    //----[ Atributos de classe e inst�ncia ]-----------------------------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L; 
    
    /** Id do cupom */
    private Serializable cupomId;
    
    /** N�mero do alert */
    private Serializable alertCupomSeq;
    
    /** Construtor Padr�o */
    public AlertCupomId() {
    	
    	super();
    	
    }
    
    /** Construtor que recebe os valores para defini��o do objeto
     * 
     * @param <code>Serializable</code> alertCupomSeq
     * @param <code>Serializable</code> cupomId
     */    
    public AlertCupomId( Serializable alertCupomSeq, Serializable cupomId ) {
    	
    	this.cupomId 		= cupomId;
    	this.alertCupomSeq  = alertCupomSeq;
    	
    }
    
    //	----[ M�todos Getter ]---------------------------------------------------

	/** M�todo getter para a propriedade alertCupomSeq
	 * 
	 *  @return Serializable
	 *
	 */
	public Serializable getAlertCupomSeq() {
		return alertCupomSeq;
	}

	/** M�todo getter para a propriedade cupomId
	 * 
	 *  @return Serializable
	 *
	 */
	public Serializable getCupomId() {
		return cupomId;
	}
	
	//	----[ M�todos Setter ]---------------------------------------------------

	/** M�todo setter para a propriedade alertCupomSeq
	 *
	 * @param alertCupomSeq 
	 *           <code>Serializable</code> a ser designado para alertCupomSeq.
	 * 
	 */
	public void setAlertCupomSeq(Serializable alertCupomSeq) {
		this.alertCupomSeq = alertCupomSeq;
	}

	/** M�todo setter para a propriedade cupomId
	 *
	 * @param cupomId 
	 *           <code>Serializable</code> a ser designado para cupomId.
	 * 
	 */
	public void setCupomId(Serializable cupomId) {
		this.cupomId = cupomId;
	}
	
	/** Sobrecarga do m�todo equals para chave composta
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
		
	/** Sobre do m�todo hasCode
	 * 
	 * @return int
	 * 
	 */
	public int hashCode() {
		
		return this.hashCode();
		
	}
    
}
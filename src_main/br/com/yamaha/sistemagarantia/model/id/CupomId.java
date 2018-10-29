/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......CupomId.java
 *   .: Cria��o.....23 de maio, 16:43
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Classe para chave composta da Entidade "Cupom".
 */
package br.com.yamaha.sistemagarantia.model.id;

import java.io.Serializable;

/** Classe para chave composta da Entidade "Cupom".
 *  
 *  @author Edson Luiz Sumensari
 */
public class CupomId implements Serializable {

    //----[ Atributos de classe e inst�ncia ]-----------------------------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L; 
    
    /** Classe Revisao */
    private Serializable revisao;
    
    /** N�mero  do Cupom */
    private Serializable cupomId;
    
    /** Construtor Padr�o */
    public CupomId() {
    	
    	super();
    	
    }
    
    /** Construtor que recebe os valores para defini��o do objeto
     * 
     * @param <code>Serializable</code> revisao
     * @param <code>Serializable</code> cupomId
     */    
    public CupomId( Serializable revisao, Serializable cupomId ) {
    	
    	this.cupomId = cupomId;
    	this.revisao = revisao;
    	
    }
    
    //	----[ M�todos Getter ]---------------------------------------------------

    /** M�todo getter para a propriedade revisao
	 * 
	 *  @return Serializable
	 *
	 */
	public Serializable getRevisao() {
		return revisao;
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


	/** M�todo setter para a propriedade cupomId
	 *
	 * @param cupomId 
	 *           <code>Serializable</code> a ser designado para cupomId.
	 * 
	 */
	public void setCupomId(Serializable cupomId) {
		this.cupomId = cupomId;
	}
	
	/** M�todo setter para a propriedade revisao
	 *
	 * @param revisao 
	 *           <code>Serializable</code> a ser designado para revisao.
	 * 
	 */
	public void setRevisao(Serializable revisao) {
		this.revisao = revisao;
	}
	
	//	----[ M�todos Servi�os ]---------------------------------------------------
	
	/** Sobrecarga do m�todo equals para chave composta
	 * 
	 *  @param Object
	 *  
	 *  @return boolean
	 *  
	 */
	public boolean equals(Object o) {
		
		final CupomId cupomId = (CupomId) o;
		
		if ( this.equals(o) ) 
			return true;
		
		if ( o == null ) 
			return false;
		
		if ( !( o instanceof CupomId ) ) 
			return false;		
		
		if ( !( this.cupomId == cupomId.getCupomId() ) )
			return false;
		
		if ( !( this.revisao.equals(cupomId.getRevisao()) ) )
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
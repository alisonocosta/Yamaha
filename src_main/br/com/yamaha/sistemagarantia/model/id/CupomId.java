/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......CupomId.java
 *   .: Criação.....23 de maio, 16:43
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Classe para chave composta da Entidade "Cupom".
 */
package br.com.yamaha.sistemagarantia.model.id;

import java.io.Serializable;

/** Classe para chave composta da Entidade "Cupom".
 *  
 *  @author Edson Luiz Sumensari
 */
public class CupomId implements Serializable {

    //----[ Atributos de classe e instância ]-----------------------------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L; 
    
    /** Classe Revisao */
    private Serializable revisao;
    
    /** Número  do Cupom */
    private Serializable cupomId;
    
    /** Construtor Padrão */
    public CupomId() {
    	
    	super();
    	
    }
    
    /** Construtor que recebe os valores para definição do objeto
     * 
     * @param <code>Serializable</code> revisao
     * @param <code>Serializable</code> cupomId
     */    
    public CupomId( Serializable revisao, Serializable cupomId ) {
    	
    	this.cupomId = cupomId;
    	this.revisao = revisao;
    	
    }
    
    //	----[ Métodos Getter ]---------------------------------------------------

    /** Método getter para a propriedade revisao
	 * 
	 *  @return Serializable
	 *
	 */
	public Serializable getRevisao() {
		return revisao;
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


	/** Método setter para a propriedade cupomId
	 *
	 * @param cupomId 
	 *           <code>Serializable</code> a ser designado para cupomId.
	 * 
	 */
	public void setCupomId(Serializable cupomId) {
		this.cupomId = cupomId;
	}
	
	/** Método setter para a propriedade revisao
	 *
	 * @param revisao 
	 *           <code>Serializable</code> a ser designado para revisao.
	 * 
	 */
	public void setRevisao(Serializable revisao) {
		this.revisao = revisao;
	}
	
	//	----[ Métodos Serviços ]---------------------------------------------------
	
	/** Sobrecarga do método equals para chave composta
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
		
	/** Sobre do método hasCode
	 * 
	 * @return int
	 * 
	 */
	public int hashCode() {
		
		return this.hashCode();
		
	}
    
}
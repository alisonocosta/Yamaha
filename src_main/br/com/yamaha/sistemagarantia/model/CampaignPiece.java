/* Resource Tecnologia
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......CampaignPiece.java
 *   .: Cria��o.....16 de julho, 14:10
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "CampaignPiece".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "CampaignPiece" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class CampaignPiece extends EntityObject {
	
	//----[ Atributos de classe e inst�ncia ]-----------------------------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;
    
    public static final String VALUE_TRUE  = "S"; 
    public static final String VALUE_FALSE = "N";
    
    /** Quantidade de pe�as*/
    private Integer quantityPiece;
    
    private Long pieceId;
    
    /** Enviar Pe�a "S" ou "N" */
    private String recoveredPiece;
    
    /** Cobrar Pe�a "S" ou "N" */
    private String sendPiece;
    
    /** Pe�a causadora "S" ou "N" */
    private String causingPiece;
    
    /** Entidade relacionada - Modelo do produto */
    private Campaign campaign;
    
    /** Pe�a associada a campanha */
    private Item piece;

    
//	----[ M�todos Getter ]---------------------------------------------------
	/**
	 * M�todo getter para a propriedade quantityPiece
	 * @return  Integer de quantityPiece
	 */
	public Integer getQuantityPiece() {
		return quantityPiece;
	}

	/**
	 * M�todo setter para a propriedade quantityPiece
	 * @param quantityPiece Integer
	 */
	public void setQuantityPiece(Integer quantityPiece) {
		this.quantityPiece = quantityPiece;
	}

	/**
	 * M�todo getter para a propriedade recoveredPiece
	 * @return  String de recoveredPiece
	 */
	public String getRecoveredPiece() {
		return recoveredPiece;
	}

	/**
	 * M�todo setter para a propriedade recoveredPiece
	 * @param recoveredPiece String
	 */
	public void setRecoveredPiece(String recoveredPiece) {
		this.recoveredPiece = recoveredPiece;
	}

	/**
	 * M�todo getter para a propriedade sendPiece
	 * @return  String de sendPiece
	 */
	public String getSendPiece() {
		return sendPiece;
	}
	
	/**
	 * M�todo getter para a propriedade causingPiece
	 * @return  String de causingPiece
	 */
	public String getCausingPiece() {
		return causingPiece;
	}
	
	/** Verifica se a pe�a e causadora
	 * 
	 * @return boolean TRUE para pe�a causadora
	 */
	public boolean getIsRecoveredPiece(){
		
		if ( "S".equalsIgnoreCase(this.recoveredPiece) )
			return true;
		else
			return false;
		
	}
	
	/** Verifica se � para enviar a pe�a 
	 * 
	 * @return boolean TRUE para enviar a pe�a
	 */
	public boolean getIsSendPiece(){
		
		if ( "S".equalsIgnoreCase(this.sendPiece) )
			return true;
		else
			return false;
		
	}
	
	/** Verifica se � para cobrar a pe�a
	 * 
	 * @return boolean TRUE para cobrar
	 */
	public boolean getIsCausingPiece(){
		
		if ( "S".equalsIgnoreCase(this.causingPiece) )
			return true;
		else
			return false;
		
	}
	
//	----[ M�todos Setter ]---------------------------------------------------

	/**
	 * M�todo setter para a propriedade sendPiece
	 * @param sendPiece String
	 */
	public void setSendPiece(String sendPiece) {
		this.sendPiece = sendPiece;
	}

	/**
	 * M�todo getter para a propriedade campaign
	 * @return  Campaign de campaign
	 */
	public Campaign getCampaign() {
		return campaign;
	}

	/**
	 * M�todo setter para a propriedade campaign
	 * @param campaign Campaign
	 */
	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}

	/**
	 * M�todo getter para a propriedade piece
	 * @return  Item de piece
	 */
	public Item getPiece() {
		
		return piece;
	}

	/**
	 * M�todo setter para a propriedade piece
	 * @param piece Item
	 */
	public void setPiece(Item piece) {
		this.piece = piece;
	}
	
	/**
	 * M�todo setter para a propriedade causingPiece
	 * @param causingPiece String
	 */
	public void setCausingPiece(String causingPiece) {
		this.causingPiece = causingPiece;
	}

	/**
	 * M�todo getter para a propriedade pieceId
	 * @return  Long de pieceId
	 */
	public Long getPieceId() {
		return pieceId;
	}

	/**
	 * M�todo setter para a propriedade pieceId
	 * @param pieceId Long
	 */
	public void setPieceId(Long pieceId) {
		this.pieceId = pieceId;
	}
}

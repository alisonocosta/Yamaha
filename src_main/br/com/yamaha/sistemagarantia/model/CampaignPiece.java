/* Resource Tecnologia
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......CampaignPiece.java
 *   .: Criação.....16 de julho, 14:10
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "CampaignPiece".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "CampaignPiece" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class CampaignPiece extends EntityObject {
	
	//----[ Atributos de classe e instância ]-----------------------------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;
    
    public static final String VALUE_TRUE  = "S"; 
    public static final String VALUE_FALSE = "N";
    
    /** Quantidade de peças*/
    private Integer quantityPiece;
    
    private Long pieceId;
    
    /** Enviar Peça "S" ou "N" */
    private String recoveredPiece;
    
    /** Cobrar Peça "S" ou "N" */
    private String sendPiece;
    
    /** Peça causadora "S" ou "N" */
    private String causingPiece;
    
    /** Entidade relacionada - Modelo do produto */
    private Campaign campaign;
    
    /** Peça associada a campanha */
    private Item piece;

    
//	----[ Métodos Getter ]---------------------------------------------------
	/**
	 * Método getter para a propriedade quantityPiece
	 * @return  Integer de quantityPiece
	 */
	public Integer getQuantityPiece() {
		return quantityPiece;
	}

	/**
	 * Método setter para a propriedade quantityPiece
	 * @param quantityPiece Integer
	 */
	public void setQuantityPiece(Integer quantityPiece) {
		this.quantityPiece = quantityPiece;
	}

	/**
	 * Método getter para a propriedade recoveredPiece
	 * @return  String de recoveredPiece
	 */
	public String getRecoveredPiece() {
		return recoveredPiece;
	}

	/**
	 * Método setter para a propriedade recoveredPiece
	 * @param recoveredPiece String
	 */
	public void setRecoveredPiece(String recoveredPiece) {
		this.recoveredPiece = recoveredPiece;
	}

	/**
	 * Método getter para a propriedade sendPiece
	 * @return  String de sendPiece
	 */
	public String getSendPiece() {
		return sendPiece;
	}
	
	/**
	 * Método getter para a propriedade causingPiece
	 * @return  String de causingPiece
	 */
	public String getCausingPiece() {
		return causingPiece;
	}
	
	/** Verifica se a peça e causadora
	 * 
	 * @return boolean TRUE para peça causadora
	 */
	public boolean getIsRecoveredPiece(){
		
		if ( "S".equalsIgnoreCase(this.recoveredPiece) )
			return true;
		else
			return false;
		
	}
	
	/** Verifica se é para enviar a peça 
	 * 
	 * @return boolean TRUE para enviar a peça
	 */
	public boolean getIsSendPiece(){
		
		if ( "S".equalsIgnoreCase(this.sendPiece) )
			return true;
		else
			return false;
		
	}
	
	/** Verifica se é para cobrar a peça
	 * 
	 * @return boolean TRUE para cobrar
	 */
	public boolean getIsCausingPiece(){
		
		if ( "S".equalsIgnoreCase(this.causingPiece) )
			return true;
		else
			return false;
		
	}
	
//	----[ Métodos Setter ]---------------------------------------------------

	/**
	 * Método setter para a propriedade sendPiece
	 * @param sendPiece String
	 */
	public void setSendPiece(String sendPiece) {
		this.sendPiece = sendPiece;
	}

	/**
	 * Método getter para a propriedade campaign
	 * @return  Campaign de campaign
	 */
	public Campaign getCampaign() {
		return campaign;
	}

	/**
	 * Método setter para a propriedade campaign
	 * @param campaign Campaign
	 */
	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}

	/**
	 * Método getter para a propriedade piece
	 * @return  Item de piece
	 */
	public Item getPiece() {
		
		return piece;
	}

	/**
	 * Método setter para a propriedade piece
	 * @param piece Item
	 */
	public void setPiece(Item piece) {
		this.piece = piece;
	}
	
	/**
	 * Método setter para a propriedade causingPiece
	 * @param causingPiece String
	 */
	public void setCausingPiece(String causingPiece) {
		this.causingPiece = causingPiece;
	}

	/**
	 * Método getter para a propriedade pieceId
	 * @return  Long de pieceId
	 */
	public Long getPieceId() {
		return pieceId;
	}

	/**
	 * Método setter para a propriedade pieceId
	 * @param pieceId Long
	 */
	public void setPieceId(Long pieceId) {
		this.pieceId = pieceId;
	}
}

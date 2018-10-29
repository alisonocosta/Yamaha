/* Resource Tecnologia
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......CampaignPiece.java
 *   .: Cria��o.....25 de julho, 15:21
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "CampaignBilling".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "CampaignBilling" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class CampaignBilling extends EntityObject {
	
	//----[ Atributos de classe e inst�ncia ]-----------------------------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;
    
    /** Entidade relacionada - Campanha */
    private Campaign campaign;
    
    /** Faturamento associado a campanha */
    private Faturamento billing;
    
    /** Garantia associado a campanha */
    private GarantiaHeader garantia;

    /** Construtor padr�o */
    public CampaignBilling(){
    	
    }
    
    /** Construtor
     * 
     * @param garantia
     * @param billing
     * @param campaign
     */
    public CampaignBilling(GarantiaHeader garantia, Faturamento billing, Campaign campaign) {
    	
    	this.campaign = campaign;
    	this.billing  = billing;
    	this.garantia = garantia;    	
    	
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
	 * M�todo getter para a propriedade billing
	 * @return  Faturamento de billing
	 */
	public Faturamento getBilling() {
		return billing;
	}

	/**
	 * M�todo setter para a propriedade billing
	 * @param billing Faturamento
	 */
	public void setBilling(Faturamento billing) {
		this.billing = billing;
	}

	/**
	 * M�todo getter para a propriedade garantia
	 * @return  GarantiaHeader de garantia
	 */
	public GarantiaHeader getGarantia() {
		return garantia;
	}

	/**
	 * M�todo setter para a propriedade garantia
	 * @param garantia GarantiaHeader
	 */
	public void setGarantia(GarantiaHeader garantia) {
		this.garantia = garantia;
	}
	
}

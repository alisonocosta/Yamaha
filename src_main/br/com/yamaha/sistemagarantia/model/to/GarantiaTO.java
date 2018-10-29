/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......GarantiaTO.java
 *   .: Criação.....12 de dembro, 17:01
 *   .: Autor.......Esdon Luiz Sumensari
 *   .: Descrição...Transfer Object para os dados de uma garantia que devem ser transferidos pela sessão do usuário .
 */
package br.com.yamaha.sistemagarantia.model.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.yamaha.sistemagarantia.model.AutorizacaoEspecialSG;
import br.com.yamaha.sistemagarantia.model.Campaign;
import br.com.yamaha.sistemagarantia.model.CampaignBilling;
import br.com.yamaha.sistemagarantia.model.GarantiaHeader;
import br.com.yamaha.sistemagarantia.model.GarantiaLine;
import br.com.yamaha.sistemagarantia.model.InfoMercado;

/** Transfer Object para os dados de uma garantia que devem ser transferidos pela sessão do usuário .
 * 
 *  @author Esdon Luiz Sumensari
 */
public class GarantiaTO {
	
	/** Entidade de GarantiaHeader */
	private GarantiaHeader garantia;
	
	/** Entidade de AutorizacaoEspecialSG */
	private AutorizacaoEspecialSG autorizacaoEspecialSG;
	
	/** Entidade de Campaign */
	private Campaign campaign;
	
	private CampaignBilling campaignBilling;
	
	/** Entidade de Informação de Mercado*/
	private InfoMercado infoMercado;
	
	/** Lista de alertas disparados na validação */
	private List listAlertas;
	
	/** Lista de peças da garantia */
	private List listGarantiaLine;
	
	public GarantiaTO() {
		
		garantia 				= null;
		autorizacaoEspecialSG 	= null;
		listAlertas      = new ArrayList();		
		listGarantiaLine = new ArrayList();
		
	}
	
	/** Método getter para a propriedade campaignBilling
	 *
	 * @return campaignBilling do tipo CampaignBilling
	 *
	 */
	
	public CampaignBilling getCampaignBilling() {
		return campaignBilling;
	}



	/** Método setter para a propriedade campaignBilling
	 *
	 * @param campaignBilling 
	 *       <code>campaignBilling<code> a ser designado para GarantiaTO.java
	 *
	 */
	public void setCampaignBilling(CampaignBilling campaignBilling) {
		this.campaignBilling = campaignBilling;
	}



	/** Método getter para a propriedade garantia
	 * 
	 *  @return GarantiaHeader
	 *
	 */
	public GarantiaHeader getGarantia() {
		return garantia;
	}

	/** Método getter para a propriedade listAlertas
	 * 
	 *  @return List
	 *
	 */
	public List getListAlertas() {
		return listAlertas;
	}

	/** Método setter para a propriedade garantia
	 *
	 * @param garantia 
	 *           <code>GarantiaHeader</code> a ser designado para garantia.
	 * 
	 */
	public void setGarantia(GarantiaHeader garantia) {
		this.garantia = garantia;
	}

	/** Método setter para a propriedade listAlertas
	 *
	 * @param listAlertas 
	 *           <code>List</code> a ser designado para listAlertas.
	 * 
	 */
	public void setListAlertas(List listAlertas) {
		this.listAlertas = listAlertas;
	}

	/** Método getter para a propriedade listGarantiaLine
	 * 
	 *  @return List
	 *
	 */
	public List getListGarantiaLine() {
		return listGarantiaLine;
	}

	/**
	 * Método getter para a propriedade campaign
	 * @return  Campaign de campaign
	 */
	public Campaign getCampaign() {
		return campaign;
	}

	/** Método setter para a propriedade listGarantiaLine
	 *
	 * @param listGarantiaLine 
	 *           <code>List</code> a ser designado para listGarantiaLine.
	 * 
	 */
	public void setListGarantiaLine(List listGarantiaLine) {
		this.listGarantiaLine = listGarantiaLine;
	}
	
	/**  Adiciona um objeto de GarantiaLine a lista
	 * 
	 * @param garantiaLine
	 */
	public void addGarantiaLine( GarantiaLine garantiaLine ) {
		
		if ( garantiaLine != null ) {
			this.listGarantiaLine.add(garantiaLine);
		}		
		
	}

	/** Método getter para a propriedade autorizacaoEspecialSG
	 * 
	 *  @return AutorizacaoEspecialSG
	 *
	 */
	public AutorizacaoEspecialSG getAutorizacaoEspecialSG() {
		return autorizacaoEspecialSG;
	}

	/** Método setter para a propriedade autorizacaoEspecialSG
	 *
	 * @param autorizacaoEspecialSG 
	 *           <code>AutorizacaoEspecialSG</code> a ser designado para autorizacaoEspecialSG.
	 * 
	 */
	public void setAutorizacaoEspecialSG(AutorizacaoEspecialSG autorizacaoEspecialSG) {
		this.autorizacaoEspecialSG = autorizacaoEspecialSG;
	}

	/**
	 * Método setter para a propriedade campaign
	 * @param campaign Campaign
	 */
	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}	
	
	/** Método getter para a propriedade infoMercado
	 *
	 *  @return InfoMercado de infoMercado
	 */
	public InfoMercado getInfoMercado() {
		return infoMercado;
	}

	/** Método setter para a propriedade infoMercado
	 *
	 * @param infoMercado InfoMercado
	 */
	public void setInfoMercado(InfoMercado infoMercado) {
		this.infoMercado = infoMercado;
	}

	/** Retorna uma entidade de garantiaLine da coleção
	 * 
	 * @param garantiaId
	 * 
	 * @param lineId
	 * 
	 * @return GarantiaLine  ou null
	 * 
	 */
	public GarantiaLine getGarantiaLine ( Serializable garantiaId, Long lineId ) {
		
		System.out.println("Recuperando o garantialine -  Garantia:" + garantiaId  + "  - Line:" + lineId);
		
		GarantiaLine garantiaLine =  null;
		Iterator it = this.listGarantiaLine.iterator();
		
		while ( it.hasNext() ) {
			
			GarantiaLine garantiaLineTmp = (GarantiaLine) it.next();
				
			System.out.println("Recuperou? " + (garantiaLineTmp != null) );
			
			if ( garantiaLineTmp.getLineId().equals(lineId) 
				 && garantiaLineTmp.getCompositeEntityId().getGarantiaId().equals(garantiaId) ) {
				
				garantiaLine = garantiaLineTmp;
				
			}
			
		}
		
		return garantiaLine;		
		
	}
	
	
}

/* Resource Tecnologia
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......Camapaign.java
 *   .: Cria��o.....15 de julho, 14:10
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "Campaign".
 */
package br.com.yamaha.sistemagarantia.model;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import br.com.resource.infra.model.EntityObject;
import br.com.yamaha.sistemagarantia.controller.helper.ControllerHelper;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "Campaign" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class Campaign extends EntityObject {
	
	//----[ Atributos de classe e inst�ncia ]-----------------------------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;
    
    public static final String RECALL_YES ="S";
    
    /** Valor inicial do chassi -  Apenas o Valor antes do "-" */
    private String modelo;
    
    /** Valor inicial do intervalo do chassi -  Apenas da seq��ncia - Valores ap�s o "-" */
    private String chassiSeqStart;
    
    /** Valor inicial do intervalo do chassi -  Apenas da seq��ncia - Valores ap�s o "-" */
    private String chassiSeqFinal;
    
    /** C�digo da Campanha */
    private String codeCampaign;
    
    /** Assunto - Descri��o da Campanha */
    private String subjectCampaign;
    
    /** Valor limite para lan�amento do valor de terceiros */
    private double serviceValue;
    
    /** Data de inicio da campanha */
    private Date validityStartDate;
    
    /** Data de t�rmino da campanha */
    private Date validityEndDate;
    
    /** Identifica��o de Recall */
    private String stRecall;
    
    /** Entidade de Condicao */
    private Condicao condition;
    
    /** Entidade de Sintoma */
    private Sintoma sintoma;
    
    /** Lista de servi�os */
    private List services;
    
    /** Lista de Pe�as para a Campanha */
    private List pieces;
    
    /** Lista de Faturamentos que j� participaram da Campanha */
    private List billing;

    //	----[ M�todos Getter ]---------------------------------------------------
	/**
	 * M�todo getter para a propriedade modelo
	 * @return  String de modelo
	 */
	public String getModelo() {
		return modelo;
	}

	/**
	 * M�todo setter para a propriedade modelo
	 * @param modelo String
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/**
	 * M�todo getter para a propriedade chassiSeqStart
	 * @return  String de chassiSeqStart
	 */
	public String getChassiSeqStart() {
		return chassiSeqStart;
	}

	/**
	 * M�todo setter para a propriedade chassiSeqStart
	 * @param chassiSeqStart String
	 */
	public void setChassiSeqStart(String chassiSeqStart) {
		this.chassiSeqStart = chassiSeqStart;
	}

	/**
	 * M�todo getter para a propriedade chassiSeqFinal
	 * @return  String de chassiSeqFinal
	 */
	public String getChassiSeqFinal() {
		return chassiSeqFinal;
	}

	/**
	 * M�todo setter para a propriedade chassiSeqFinal
	 * @param chassiSeqFinal String
	 */
	public void setChassiSeqFinal(String chassiSeqFinal) {
		this.chassiSeqFinal = chassiSeqFinal;
	}

	/**
	 * M�todo getter para a propriedade codeCampaign
	 * @return  String de codeCampaign
	 */
	public String getCodeCampaign() {
		return codeCampaign;
	}

	/**
	 * M�todo setter para a propriedade codeCampaign
	 * @param codeCampaign String
	 */
	public void setCodeCampaign(String codeCampaign) {
		this.codeCampaign = codeCampaign;
	}

	/**
	 * M�todo getter para a propriedade subjectCampaign
	 * @return  String de subjectCampaign
	 */
	public String getSubjectCampaign() {
		return subjectCampaign;
	}

	/**
	 * M�todo setter para a propriedade subjectCampaign
	 * @param subjectCampaign String
	 */
	public void setSubjectCampaign(String subjectCampaign) {
		this.subjectCampaign = subjectCampaign;
	}

	/**
	 * M�todo getter para a propriedade serviceValue
	 * @return  double de serviceValue
	 */
	public double getServiceValue() {
		return serviceValue;
	}

	/**
	 * M�todo setter para a propriedade serviceValue
	 * @param serviceValue double
	 */
	public void setServiceValue(double serviceValue) {
		this.serviceValue = serviceValue;
	}

	/**
	 * M�todo getter para a propriedade validityStartDate
	 * @return  Date de validityStartDate
	 */
	public Date getValidityStartDate() {
		return validityStartDate;
	}

	/**
	 * M�todo getter para a propriedade billing
	 * @return  List de billing
	 */
	public List getBilling() {
		return billing;
	}


	//	----[ M�todos Setter ]---------------------------------------------------
	/**
	 * M�todo setter para a propriedade validityStartDate
	 * @param validityStartDate Date
	 */
	public void setValidityStartDate(Date validityStartDate) {
		this.validityStartDate = validityStartDate;
	}

	/**
	 * M�todo getter para a propriedade validityEndDate
	 * @return  Date de validityEndDate
	 */
	public Date getValidityEndDate() {
		return validityEndDate;
	}

	/**
	 * M�todo setter para a propriedade validityEndDate
	 * @param validityEndDate Date
	 */
	public void setValidityEndDate(Date validityEndDate) {
		this.validityEndDate = validityEndDate;
	}

	/**
	 * M�todo getter para a propriedade condition
	 * @return  Condicao de condition
	 */
	public Condicao getCondition() {
		return condition;
	}

	/**
	 * M�todo setter para a propriedade condition
	 * @param condition Condicao
	 */
	public void setCondition(Condicao condition) {
		this.condition = condition;
	}

	/**
	 * M�todo getter para a propriedade sintoma
	 * @return  Sintoma de sintoma
	 */
	public Sintoma getSintoma() {
		return sintoma;
	}

	/**
	 * M�todo setter para a propriedade sintoma
	 * @param sintoma Sintoma
	 */
	public void setSintoma(Sintoma sintoma) {
		this.sintoma = sintoma;
	}

	/**
	 * M�todo getter para a propriedade services
	 * @return  List de services
	 */
	public List getServices() {
		return services;
	}

	/**
	 * M�todo setter para a propriedade services
	 * @param services List
	 */
	public void setServices(List services) {
		this.services = services;
	}

	/**
	 * M�todo getter para a propriedade pieces
	 * @return  List de pieces
	 */
	public List getPieces() {
		return this.pieces;
	}

	/**
	 * M�todo setter para a propriedade pieces
	 * @param pieces List
	 */
	public void setPieces(List pieces) {
		this.pieces = pieces;
	}
	/**
	 * M�todo setter para a propriedade billing
	 * @param billing List
	 */
	public void setBilling(List billing) {
		this.billing = billing;
	}	
	
	/** M�todo getter para a propriedade stRecall
	 *
	 *  @return String de stRecall
	 */
	public String getStRecall() {
		return stRecall;
	}

	/** M�todo setter para a propriedade stRecall
	 *
	 * @param stRecall String
	 */
	public void setStRecall(String stRecall) {
		this.stRecall = stRecall;
	}
	
	//---------------Servi�os ---------------------------
	
	/** Realiza a valida��o de um chassi, anlisando se ele faz parte da camapanha
	 *  representada por esta entidade
	 *  
	 * @param chassi
	 * 	<code>String</code> - Chassi do produto
	 * 
	 * @return TRUE - quando o produto pertencer a campanha
	 * 	<code>boolean</code> 
	 */
	public boolean isValidModel(String chassi) {
		
		boolean isValid = false;
		String modelo    = ControllerHelper.getModeloByChassi(chassi);
		String seqChassi = ControllerHelper.getSequencyByChassi(chassi);
		
		if ( this.modelo.equals(modelo) ) {
			
			if ( this.chassiSeqStart.compareTo(seqChassi) <= 0 && this.chassiSeqFinal.compareTo(seqChassi) >= 0 ) 
				isValid = true;
			
		}
		
		return isValid;
		
	}
	
	/** Percorre a lista de pe�as e marca enviar a pe�a como false
	 * 
	 */
	public void setAllSendPieceFalse() {
		
		Iterator it = this.getPieces().iterator();
		
		while ( it.hasNext() ) {
			
			CampaignPiece campaignPiece = (CampaignPiece) it.next();
			campaignPiece.setSendPiece(CampaignPiece.VALUE_FALSE);
			
		}
		
	}
	
	/** Percorre a lista de pe�as e marca para enviar a pe�a
	 * 
	 * @param targets
	 */
	public void setSendPiece(String targets[]) {
		
		if ( targets.length == 0 )
			this.setAllSendPieceFalse();
		else {	
    		// Marcamos as pe�as para envio		
			Iterator it = this.getPieces().iterator();
			boolean isSend = false;
			
			while ( it.hasNext() ) {
				
				CampaignPiece campaignPiece = (CampaignPiece) it.next();
				isSend = false;
				
				for ( int i=0; i<targets.length; i++ ) {  					
					
					if ( campaignPiece.getEntityId().equals(new Long(targets[i])) ) 
						isSend = true;					
				}
				
				if( isSend )
					campaignPiece.setSendPiece(CampaignPiece.VALUE_TRUE);
				else
					campaignPiece.setSendPiece(CampaignPiece.VALUE_FALSE);
			}
		}
		
	}
		
}

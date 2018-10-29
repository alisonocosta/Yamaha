/* Resource Tecnologia
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......Camapaign.java
 *   .: Criação.....15 de julho, 14:10
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "Campaign".
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
	
	//----[ Atributos de classe e instância ]-----------------------------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;
    
    public static final String RECALL_YES ="S";
    
    /** Valor inicial do chassi -  Apenas o Valor antes do "-" */
    private String modelo;
    
    /** Valor inicial do intervalo do chassi -  Apenas da seqüência - Valores após o "-" */
    private String chassiSeqStart;
    
    /** Valor inicial do intervalo do chassi -  Apenas da seqüência - Valores após o "-" */
    private String chassiSeqFinal;
    
    /** Código da Campanha */
    private String codeCampaign;
    
    /** Assunto - Descrição da Campanha */
    private String subjectCampaign;
    
    /** Valor limite para lançamento do valor de terceiros */
    private double serviceValue;
    
    /** Data de inicio da campanha */
    private Date validityStartDate;
    
    /** Data de término da campanha */
    private Date validityEndDate;
    
    /** Identificação de Recall */
    private String stRecall;
    
    /** Entidade de Condicao */
    private Condicao condition;
    
    /** Entidade de Sintoma */
    private Sintoma sintoma;
    
    /** Lista de serviços */
    private List services;
    
    /** Lista de Peças para a Campanha */
    private List pieces;
    
    /** Lista de Faturamentos que já participaram da Campanha */
    private List billing;

    //	----[ Métodos Getter ]---------------------------------------------------
	/**
	 * Método getter para a propriedade modelo
	 * @return  String de modelo
	 */
	public String getModelo() {
		return modelo;
	}

	/**
	 * Método setter para a propriedade modelo
	 * @param modelo String
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/**
	 * Método getter para a propriedade chassiSeqStart
	 * @return  String de chassiSeqStart
	 */
	public String getChassiSeqStart() {
		return chassiSeqStart;
	}

	/**
	 * Método setter para a propriedade chassiSeqStart
	 * @param chassiSeqStart String
	 */
	public void setChassiSeqStart(String chassiSeqStart) {
		this.chassiSeqStart = chassiSeqStart;
	}

	/**
	 * Método getter para a propriedade chassiSeqFinal
	 * @return  String de chassiSeqFinal
	 */
	public String getChassiSeqFinal() {
		return chassiSeqFinal;
	}

	/**
	 * Método setter para a propriedade chassiSeqFinal
	 * @param chassiSeqFinal String
	 */
	public void setChassiSeqFinal(String chassiSeqFinal) {
		this.chassiSeqFinal = chassiSeqFinal;
	}

	/**
	 * Método getter para a propriedade codeCampaign
	 * @return  String de codeCampaign
	 */
	public String getCodeCampaign() {
		return codeCampaign;
	}

	/**
	 * Método setter para a propriedade codeCampaign
	 * @param codeCampaign String
	 */
	public void setCodeCampaign(String codeCampaign) {
		this.codeCampaign = codeCampaign;
	}

	/**
	 * Método getter para a propriedade subjectCampaign
	 * @return  String de subjectCampaign
	 */
	public String getSubjectCampaign() {
		return subjectCampaign;
	}

	/**
	 * Método setter para a propriedade subjectCampaign
	 * @param subjectCampaign String
	 */
	public void setSubjectCampaign(String subjectCampaign) {
		this.subjectCampaign = subjectCampaign;
	}

	/**
	 * Método getter para a propriedade serviceValue
	 * @return  double de serviceValue
	 */
	public double getServiceValue() {
		return serviceValue;
	}

	/**
	 * Método setter para a propriedade serviceValue
	 * @param serviceValue double
	 */
	public void setServiceValue(double serviceValue) {
		this.serviceValue = serviceValue;
	}

	/**
	 * Método getter para a propriedade validityStartDate
	 * @return  Date de validityStartDate
	 */
	public Date getValidityStartDate() {
		return validityStartDate;
	}

	/**
	 * Método getter para a propriedade billing
	 * @return  List de billing
	 */
	public List getBilling() {
		return billing;
	}


	//	----[ Métodos Setter ]---------------------------------------------------
	/**
	 * Método setter para a propriedade validityStartDate
	 * @param validityStartDate Date
	 */
	public void setValidityStartDate(Date validityStartDate) {
		this.validityStartDate = validityStartDate;
	}

	/**
	 * Método getter para a propriedade validityEndDate
	 * @return  Date de validityEndDate
	 */
	public Date getValidityEndDate() {
		return validityEndDate;
	}

	/**
	 * Método setter para a propriedade validityEndDate
	 * @param validityEndDate Date
	 */
	public void setValidityEndDate(Date validityEndDate) {
		this.validityEndDate = validityEndDate;
	}

	/**
	 * Método getter para a propriedade condition
	 * @return  Condicao de condition
	 */
	public Condicao getCondition() {
		return condition;
	}

	/**
	 * Método setter para a propriedade condition
	 * @param condition Condicao
	 */
	public void setCondition(Condicao condition) {
		this.condition = condition;
	}

	/**
	 * Método getter para a propriedade sintoma
	 * @return  Sintoma de sintoma
	 */
	public Sintoma getSintoma() {
		return sintoma;
	}

	/**
	 * Método setter para a propriedade sintoma
	 * @param sintoma Sintoma
	 */
	public void setSintoma(Sintoma sintoma) {
		this.sintoma = sintoma;
	}

	/**
	 * Método getter para a propriedade services
	 * @return  List de services
	 */
	public List getServices() {
		return services;
	}

	/**
	 * Método setter para a propriedade services
	 * @param services List
	 */
	public void setServices(List services) {
		this.services = services;
	}

	/**
	 * Método getter para a propriedade pieces
	 * @return  List de pieces
	 */
	public List getPieces() {
		return this.pieces;
	}

	/**
	 * Método setter para a propriedade pieces
	 * @param pieces List
	 */
	public void setPieces(List pieces) {
		this.pieces = pieces;
	}
	/**
	 * Método setter para a propriedade billing
	 * @param billing List
	 */
	public void setBilling(List billing) {
		this.billing = billing;
	}	
	
	/** Método getter para a propriedade stRecall
	 *
	 *  @return String de stRecall
	 */
	public String getStRecall() {
		return stRecall;
	}

	/** Método setter para a propriedade stRecall
	 *
	 * @param stRecall String
	 */
	public void setStRecall(String stRecall) {
		this.stRecall = stRecall;
	}
	
	//---------------Serviços ---------------------------
	
	/** Realiza a validação de um chassi, anlisando se ele faz parte da camapanha
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
	
	/** Percorre a lista de peças e marca enviar a peça como false
	 * 
	 */
	public void setAllSendPieceFalse() {
		
		Iterator it = this.getPieces().iterator();
		
		while ( it.hasNext() ) {
			
			CampaignPiece campaignPiece = (CampaignPiece) it.next();
			campaignPiece.setSendPiece(CampaignPiece.VALUE_FALSE);
			
		}
		
	}
	
	/** Percorre a lista de peças e marca para enviar a peça
	 * 
	 * @param targets
	 */
	public void setSendPiece(String targets[]) {
		
		if ( targets.length == 0 )
			this.setAllSendPieceFalse();
		else {	
    		// Marcamos as peças para envio		
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

/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......GarantiaPecaAction.java
 *   .: Criação.....19 de julho, 18:44
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Transfer Object para um cupom.
 */

package br.com.yamaha.sistemagarantia.view;

import java.io.Serializable;
import java.util.Date;

import br.com.resource.infra.utils.DateUtils;
import br.com.resource.infra.utils.NumberUtils;
import br.com.yamaha.sistemagarantia.model.AlertCupom;

/** Transfer Object de uma peça.
 * 
 *  @author Edson Luiz Sumensari
 */
public class NotaFiscalVO {
	
	private Serializable entityId;
	
	private Serializable numeroCupom;
	
	private double valorMaoObra;
	
	private double valorRemessa;
	
	private double valorPeca;
	
	private double valorMaoObraTerc;
	
	private String destinatario;
	
	private Double icmsYamaha;
	
	private Double icmsCliente;
	
	private Serializable loteId;
	
	private Long statusLoteId;
	
	private Long tipoLoteId;
	
	private String tipoLote;
	
	private String descricaoStatus;
	
	private Date dtAberturaOS;
	
	private AlertCupom alert;
	/**
	 * Flag que identifica a existência da data de Fechamento da OS
	 */
	private boolean hasDtFechamento;
	
	public NotaFiscalVO() {
		
		this.valorMaoObra 		= 0;
		this.valorMaoObraTerc 	= 0;
		this.valorPeca 			= 0;
		this.valorRemessa 		= 0;
		
	}
	
	
	/** Método getter para a propriedade dtAberturaOS
	 *
	 * @return dtAberturaOS do tipo Date
	 *
	 */
	
	public Date getDtAberturaOS() {
		return dtAberturaOS;
	}

	/** Método getter para "dtAberturaOS".
     *  @return
     *      <code>String</code>. O valor atual de dtAberturaOS,
     *      com uma máscara aplicada.
     */
    public String getMaskedDtAberturaOS() {
        return DateUtils.applyMask(this.dtAberturaOS);
    }    

	/** Método setter para a propriedade dtAberturaOS
	 *
	 * @param dtAberturaOS 
	 *       <code>dtAberturaOS<code> a ser designado para NotaFiscalVO.java
	 *
	 */
	public void setDtAberturaOS(Date dtAberturaOS) {
		this.dtAberturaOS = dtAberturaOS;
	}



	/** Método getter para a propriedade hasDtFechamento
	 *
	 * @return hasDtFechamento do tipo boolean
	 *
	 */	
	public boolean getHasDtFechamento() {
		return hasDtFechamento;
	}



	/** Método setter para a propriedade hasDtFechamento
	 *
	 * @param hasDtFechamento 
	 *       <code>hasDtFechamento<code> a ser designado para NotaFiscalVO.java
	 *
	 */
	public void setHasDtFechamento(boolean hasDtFechamento) {
		this.hasDtFechamento = hasDtFechamento;
	}

	/** Retorna o valor de MaoObra, no formato Moeda REAL 
	 * 
	 * @return String
	 */
	public String getFormatedValorMaoObra() {
		 
		return NumberUtils.formatNumberCurrency(this.getValorMaoObra());
		
	}
	
	/** Retorna o valor de valorRemessa, no formato Moeda REAL 
	 * 
	 * @return String
	 */
	public String getFormatedValorRemessa() {
		 
		return NumberUtils.formatNumberCurrency(this.getValorRemessa());
		
	}
	
	/** Retorna o valor de valorPeca, no formato Moeda REAL 
	 * 
	 * @return String
	 */
	public String getFormatedValorPeca() {
		 
		return NumberUtils.formatNumberCurrency(this.getValorPeca());
		
	}
	
	/** Retorna o valor de valorMaoObraTerc, no formato Moeda REAL 
	 * 
	 * @return String
	 */
	public String getFormatedValorMaoObraTerc() {
		 
		return NumberUtils.formatNumberCurrency(this.getValorMaoObraTerc());
		
	}

	/** Método getter para a propriedade entityId
	 *
	 *  @return Serializable de entityId
	 */
	public Serializable getEntityId() {
		return entityId;
	}
	


	/** Método getter para a propriedade numeroCupom
	 *
	 *  @return Serializable de numeroCupom
	 */
	public Serializable getNumeroCupom() {
		return numeroCupom;
	}

	/** Método setter para a propriedade entityId
	 *
	 * @param entityId Serializable
	 */
	public void setEntityId(Serializable entityId) {
		this.entityId = entityId;
	}

	/** Método getter para a propriedade loteId
	 *
	 *  @return Serializable de loteId
	 */
	public Serializable getLoteId() {
		return loteId;
	}

	/** Método setter para a propriedade loteId
	 *
	 * @param loteId Long
	 */
	public void setLoteId(Serializable loteId) {
		this.loteId = loteId;
	}

	/** Método getter para a propriedade statusLoteId
	 *
	 *  @return Long de statusLoteId
	 */
	public Long getStatusLoteId() {
		return statusLoteId;
	}

	/** Método setter para a propriedade statusLoteId
	 *
	 * @param statusLoteId Long
	 */
	public void setStatusLoteId(Long statusLoteId) {
		this.statusLoteId = statusLoteId;
	}

	/** Método getter para a propriedade tipoLote
	 *
	 *  @return String de tipoLote
	 */
	public String getTipoLote() {
		return tipoLote;
	}

	/** Método setter para a propriedade tipoLote
	 *
	 * @param tipoLote String
	 */
	public void setTipoLote(String tipoLote) {
		this.tipoLote = tipoLote;
	}

	/** Método getter para a propriedade tipoLoteId
	 *
	 *  @return Long de tipoLoteId
	 */
	public Long getTipoLoteId() {
		return tipoLoteId;
	}

	/** Método setter para a propriedade tipoLoteId
	 *
	 * @param tipoLoteId Long
	 */
	public void setTipoLoteId(Long tipoLoteId) {
		this.tipoLoteId = tipoLoteId;
	}

	/** Método getter para a propriedade valorMaoObra
	 *
	 *  @return double de valorMaoObra
	 */
	public double getValorMaoObra() {
		return NumberUtils.round(valorMaoObra,2);
	}

	/** Método setter para a propriedade valorMaoObra
	 *
	 * @param valorMaoObra double
	 */
	public void setValorMaoObra(double valorMaoObra) {
		this.valorMaoObra = valorMaoObra;
	}

	/** Método getter para a propriedade valorPeca
	 *
	 *  @return double de valorPeca
	 */
	public double getValorPeca() {
		return NumberUtils.round(valorPeca,2);
	}

	/** Método setter para a propriedade valorPeca
	 *
	 * @param valorPeca double
	 */
	public void setValorPeca(double valorPeca) {
		this.valorPeca = valorPeca;
	}

	/** Método getter para a propriedade valorRemessa
	 *
	 *  @return double de valorRemessa
	 */
	public double getValorRemessa() {
		return NumberUtils.round(valorRemessa,2);
	}

	/** Método setter para a propriedade valorRemessa
	 *
	 * @param valorRemessa double
	 */
	public void setValorRemessa(double valorRemessa) {
		this.valorRemessa = valorRemessa;
	}

	/** Método getter para a propriedade descricaoStatus
	 *
	 *  @return String de descricaoStatus
	 */
	public String getDescricaoStatus() {
		return descricaoStatus;
	}

	/** Método setter para a propriedade descricaoStatus
	 *
	 * @param descricaoStatus String
	 */
	public void setDescricaoStatus(String descricaoStatus) {
		this.descricaoStatus = descricaoStatus;
	}

	/** Método getter para a propriedade valorMaoObraTerc
	 *
	 *  @return double de valorMaoObraTerc
	 */
	public double getValorMaoObraTerc() {
		return NumberUtils.round(valorMaoObraTerc,2);
	}
	
	/** Método getter para a propriedade valorSerivcos 
	 *  Retorna a soma do valor de Mão de obra e Mão de obra de terceiros
	 *
	 *  @return double de valorServicos
	 */
	public double getValorServicos() {
		return NumberUtils.round(this.getValorMaoObra() + this.getValorMaoObraTerc(),2);
	}

	/** Método setter para a propriedade valorMaoObraTerc
	 *
	 * @param valorMaoObraTerc double
	 */
	public void setValorMaoObraTerc(double valorMaoObraTerc) {
		this.valorMaoObraTerc = valorMaoObraTerc;
	}

	/** Método getter para a propriedade destinatario
	 *
	 *  @return String de destinatario
	 */
	public String getDestinatario() {
		return destinatario;
	}

	/** Método setter para a propriedade destinatario
	 *
	 * @param destinatario String
	 */
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	/** Método getter para a propriedade icmsCliente
	 * 
	 *  @return String
	 *
	 */
	public Double getIcmsCliente() {
		return icmsCliente;
	}

	/** Método getter para a propriedade icmsYamaha
	 * 
	 *  @return Double
	 *
	 */
	public Double getIcmsYamaha() {
		return icmsYamaha;
	}

	/** Método setter para a propriedade icmsCliente
	 *
	 * @param icmsCliente 
	 *           <code>Double</code> a ser designado para icmsCliente.
	 * 
	 */
	public void setIcmsCliente(Double icmsCliente) {
		this.icmsCliente = icmsCliente;
	}

	/** Método setter para a propriedade icmsYamaha
	 *
	 * @param icmsYamaha 
	 *           <code>Double</code> a ser designado para icmsYamaha.
	 * 
	 */
	public void setIcmsYamaha(Double icmsYamaha) {
		this.icmsYamaha = icmsYamaha;
	}

	/** Método getter para a propriedade alert
	 * 
	 *  @return AlertCupom
	 *
	 */
	public AlertCupom getAlert() {
		return alert;
	}

	/** Método setter para a propriedade alert
	 *
	 * @param alert 
	 *           <code>AlertCupom</code> a ser designado para alert.
	 * 
	 */
	public void setAlert(AlertCupom alert) {
		this.alert = alert;
	}

	/** Método setter para a propriedade numeroCupom
	 *
	 * @param numeroCupom Serializable
	 */
	public void setNumeroCupom(Serializable numeroCupom) {
		this.numeroCupom = numeroCupom;
	}
}

/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......GarantiaPecaAction.java
 *   .: Cria��o.....19 de julho, 18:44
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Transfer Object para um cupom.
 */

package br.com.yamaha.sistemagarantia.view;

import java.io.Serializable;
import java.util.Date;

import br.com.resource.infra.utils.DateUtils;
import br.com.resource.infra.utils.NumberUtils;
import br.com.yamaha.sistemagarantia.model.AlertCupom;

/** Transfer Object de uma pe�a.
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
	 * Flag que identifica a exist�ncia da data de Fechamento da OS
	 */
	private boolean hasDtFechamento;
	
	public NotaFiscalVO() {
		
		this.valorMaoObra 		= 0;
		this.valorMaoObraTerc 	= 0;
		this.valorPeca 			= 0;
		this.valorRemessa 		= 0;
		
	}
	
	
	/** M�todo getter para a propriedade dtAberturaOS
	 *
	 * @return dtAberturaOS do tipo Date
	 *
	 */
	
	public Date getDtAberturaOS() {
		return dtAberturaOS;
	}

	/** M�todo getter para "dtAberturaOS".
     *  @return
     *      <code>String</code>. O valor atual de dtAberturaOS,
     *      com uma m�scara aplicada.
     */
    public String getMaskedDtAberturaOS() {
        return DateUtils.applyMask(this.dtAberturaOS);
    }    

	/** M�todo setter para a propriedade dtAberturaOS
	 *
	 * @param dtAberturaOS 
	 *       <code>dtAberturaOS<code> a ser designado para NotaFiscalVO.java
	 *
	 */
	public void setDtAberturaOS(Date dtAberturaOS) {
		this.dtAberturaOS = dtAberturaOS;
	}



	/** M�todo getter para a propriedade hasDtFechamento
	 *
	 * @return hasDtFechamento do tipo boolean
	 *
	 */	
	public boolean getHasDtFechamento() {
		return hasDtFechamento;
	}



	/** M�todo setter para a propriedade hasDtFechamento
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

	/** M�todo getter para a propriedade entityId
	 *
	 *  @return Serializable de entityId
	 */
	public Serializable getEntityId() {
		return entityId;
	}
	


	/** M�todo getter para a propriedade numeroCupom
	 *
	 *  @return Serializable de numeroCupom
	 */
	public Serializable getNumeroCupom() {
		return numeroCupom;
	}

	/** M�todo setter para a propriedade entityId
	 *
	 * @param entityId Serializable
	 */
	public void setEntityId(Serializable entityId) {
		this.entityId = entityId;
	}

	/** M�todo getter para a propriedade loteId
	 *
	 *  @return Serializable de loteId
	 */
	public Serializable getLoteId() {
		return loteId;
	}

	/** M�todo setter para a propriedade loteId
	 *
	 * @param loteId Long
	 */
	public void setLoteId(Serializable loteId) {
		this.loteId = loteId;
	}

	/** M�todo getter para a propriedade statusLoteId
	 *
	 *  @return Long de statusLoteId
	 */
	public Long getStatusLoteId() {
		return statusLoteId;
	}

	/** M�todo setter para a propriedade statusLoteId
	 *
	 * @param statusLoteId Long
	 */
	public void setStatusLoteId(Long statusLoteId) {
		this.statusLoteId = statusLoteId;
	}

	/** M�todo getter para a propriedade tipoLote
	 *
	 *  @return String de tipoLote
	 */
	public String getTipoLote() {
		return tipoLote;
	}

	/** M�todo setter para a propriedade tipoLote
	 *
	 * @param tipoLote String
	 */
	public void setTipoLote(String tipoLote) {
		this.tipoLote = tipoLote;
	}

	/** M�todo getter para a propriedade tipoLoteId
	 *
	 *  @return Long de tipoLoteId
	 */
	public Long getTipoLoteId() {
		return tipoLoteId;
	}

	/** M�todo setter para a propriedade tipoLoteId
	 *
	 * @param tipoLoteId Long
	 */
	public void setTipoLoteId(Long tipoLoteId) {
		this.tipoLoteId = tipoLoteId;
	}

	/** M�todo getter para a propriedade valorMaoObra
	 *
	 *  @return double de valorMaoObra
	 */
	public double getValorMaoObra() {
		return NumberUtils.round(valorMaoObra,2);
	}

	/** M�todo setter para a propriedade valorMaoObra
	 *
	 * @param valorMaoObra double
	 */
	public void setValorMaoObra(double valorMaoObra) {
		this.valorMaoObra = valorMaoObra;
	}

	/** M�todo getter para a propriedade valorPeca
	 *
	 *  @return double de valorPeca
	 */
	public double getValorPeca() {
		return NumberUtils.round(valorPeca,2);
	}

	/** M�todo setter para a propriedade valorPeca
	 *
	 * @param valorPeca double
	 */
	public void setValorPeca(double valorPeca) {
		this.valorPeca = valorPeca;
	}

	/** M�todo getter para a propriedade valorRemessa
	 *
	 *  @return double de valorRemessa
	 */
	public double getValorRemessa() {
		return NumberUtils.round(valorRemessa,2);
	}

	/** M�todo setter para a propriedade valorRemessa
	 *
	 * @param valorRemessa double
	 */
	public void setValorRemessa(double valorRemessa) {
		this.valorRemessa = valorRemessa;
	}

	/** M�todo getter para a propriedade descricaoStatus
	 *
	 *  @return String de descricaoStatus
	 */
	public String getDescricaoStatus() {
		return descricaoStatus;
	}

	/** M�todo setter para a propriedade descricaoStatus
	 *
	 * @param descricaoStatus String
	 */
	public void setDescricaoStatus(String descricaoStatus) {
		this.descricaoStatus = descricaoStatus;
	}

	/** M�todo getter para a propriedade valorMaoObraTerc
	 *
	 *  @return double de valorMaoObraTerc
	 */
	public double getValorMaoObraTerc() {
		return NumberUtils.round(valorMaoObraTerc,2);
	}
	
	/** M�todo getter para a propriedade valorSerivcos 
	 *  Retorna a soma do valor de M�o de obra e M�o de obra de terceiros
	 *
	 *  @return double de valorServicos
	 */
	public double getValorServicos() {
		return NumberUtils.round(this.getValorMaoObra() + this.getValorMaoObraTerc(),2);
	}

	/** M�todo setter para a propriedade valorMaoObraTerc
	 *
	 * @param valorMaoObraTerc double
	 */
	public void setValorMaoObraTerc(double valorMaoObraTerc) {
		this.valorMaoObraTerc = valorMaoObraTerc;
	}

	/** M�todo getter para a propriedade destinatario
	 *
	 *  @return String de destinatario
	 */
	public String getDestinatario() {
		return destinatario;
	}

	/** M�todo setter para a propriedade destinatario
	 *
	 * @param destinatario String
	 */
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	/** M�todo getter para a propriedade icmsCliente
	 * 
	 *  @return String
	 *
	 */
	public Double getIcmsCliente() {
		return icmsCliente;
	}

	/** M�todo getter para a propriedade icmsYamaha
	 * 
	 *  @return Double
	 *
	 */
	public Double getIcmsYamaha() {
		return icmsYamaha;
	}

	/** M�todo setter para a propriedade icmsCliente
	 *
	 * @param icmsCliente 
	 *           <code>Double</code> a ser designado para icmsCliente.
	 * 
	 */
	public void setIcmsCliente(Double icmsCliente) {
		this.icmsCliente = icmsCliente;
	}

	/** M�todo setter para a propriedade icmsYamaha
	 *
	 * @param icmsYamaha 
	 *           <code>Double</code> a ser designado para icmsYamaha.
	 * 
	 */
	public void setIcmsYamaha(Double icmsYamaha) {
		this.icmsYamaha = icmsYamaha;
	}

	/** M�todo getter para a propriedade alert
	 * 
	 *  @return AlertCupom
	 *
	 */
	public AlertCupom getAlert() {
		return alert;
	}

	/** M�todo setter para a propriedade alert
	 *
	 * @param alert 
	 *           <code>AlertCupom</code> a ser designado para alert.
	 * 
	 */
	public void setAlert(AlertCupom alert) {
		this.alert = alert;
	}

	/** M�todo setter para a propriedade numeroCupom
	 *
	 * @param numeroCupom Serializable
	 */
	public void setNumeroCupom(Serializable numeroCupom) {
		this.numeroCupom = numeroCupom;
	}
}

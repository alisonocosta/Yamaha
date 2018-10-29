/** Resource Tecnologia
 * Copyright (c) 2009 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......LoteVO.java
 *   .: Criação.....23 de Dezembro, 21:32
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...VO de Lote, Linha e TipoLote.
 */
package br.com.yamaha.sistemagarantia.view;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import br.com.resource.infra.utils.DateUtils;
import br.com.resource.infra.view.ViewObject;
import br.com.yamaha.sistemagarantia.model.GarantiaHeader;
import br.com.yamaha.sistemagarantia.model.StatusGarantia;
import br.com.yamaha.sistemagarantia.model.StatusLote;
import br.com.yamaha.sistemagarantia.model.TipoLote;

/** View object juntando informações do Lote, relacionando 
 *  as entidades "Lote", "Linha" e "TipoLote".
 *  
 *  @author Edson Luiz Sumensari
 */
public class LoteCompactVO extends ViewObject {

    //----[ Atributos de classe e instância ]-----------------------------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;    

    /** Identificador da entidade <code>Lote</code> utilizada
     *  na formação deste <i>View Object</i>.
     */
    private Serializable loteId;

    /** Identificador da entidade <code>Linha</code> utilizada
     *  na formação deste <i>View Object</i>.
     */
    private Serializable linhaId;
    
    /**
     * Descrição da linha do Lote
     */
    private String descricaoLinha;

    /** Identificador da entidade <code>TipoLote</code> utilizada
     *  na formação deste <i>View Object</i>.
     */
    private Serializable tipoId;
    
    /**
     * Descrição dotipo do lote
     */
    private String descricaoTipoLote;
    
    /** Identificador da entidade <code>StatusLote</code> utilizada
     *  na formação deste <i>View Object</i>.
     */
    private Serializable statusId;
    
    /**
     * Descrição reduzida do status do lote
     */
    private String descricaoReduzidaStatus;
    
    /**
     * Máscara para o status do Lote
     */
    private String statusMascara;
    
    /**
     * Cor do status
     */
    private String statusCor;
    
    /**
     * Descrição do status adiantamento do lote
     */
    private String descricaoStatusAdiantamento;
    
    /** Data do lote. */
    private Date dataLote;
    
    /** Data de fechamento. */
    private Date dataFechamento;
    
    /** Data de liberação. */
    private Date dataLiberacao;
    
    /** Data de dataEnvioAdiant. */
    private Date dataEnvioAdiant;
    
    /**
     * Identifica se o lote possui movimento
     */
    private Boolean hasMovimento;
    
    /** 
     * Flag que indica se um lote de garantia pode ser liberado
     */
    private Boolean podeLiberarGarantia;
    
    /** Coleção de Garantias do lote */
    private List garantias;
    

	/** Método getter para a propriedade dataEnvioAdiant
	 *
	 *  @return Date de dataEnvioAdiant
	 */
	public Date getDataEnvioAdiant() {
		return dataEnvioAdiant;
	}

	/** Método setter para a propriedade dataEnvioAdiant
	 *
	 * @param dataEnvioAdiant Date
	 */
	public void setDataEnvioAdiant(Date dataEnvioAdiant) {
		this.dataEnvioAdiant = dataEnvioAdiant;
	}

	/** Método getter para a propriedade dataFechamento
	 *
	 *  @return Date de dataFechamento
	 */
	public Date getDataFechamento() {
		return dataFechamento;
	}
	
	/** Método getter para "dataFechamento".
     *  @return
     *      <code>String</code>. O valor atual de dataFechamento,
     *      com uma máscara aplicada.
     */
    public String getMaskedDataFechamento() {
        return DateUtils.applyMask(this.dataFechamento);
    }    

	/** Método setter para a propriedade dataFechamento
	 *
	 * @param dataFechamento Date
	 */
	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	/** Método getter para a propriedade dataLiberacao
	 *
	 *  @return Date de dataLiberacao
	 */
	public Date getDataLiberacao() {
		return dataLiberacao;
	}
	
	/** Método getter para "dataLiberacao".
     *  @return
     *      <code>String</code>. O valor atual de dataLiberacao.
     */
    public String getMaskedDataLiberacao() {
        return DateUtils.applyMask(this.dataLiberacao);
    }

	/** Método setter para a propriedade dataLiberacao
	 *
	 * @param dataLiberacao Date
	 */
	public void setDataLiberacao(Date dataLiberacao) {
		this.dataLiberacao = dataLiberacao;
	}

	/** Método getter para a propriedade dataLote
	 *
	 *  @return Date de dataLote
	 */
	public Date getDataLote() {
		return dataLote;
	}
	
	/** Método getter para "dataLote".
     *  @return
     *      <code>String</code>. O valor atual de dataLote.
     */
    public String getMaskedDataLote() {
        return DateUtils.applyMask(this.dataLote);
    }

	/** Método setter para a propriedade dataLote
	 *
	 * @param dataLote Date
	 */
	public void setDataLote(Date dataLote) {
		this.dataLote = dataLote;
	}

	/** Método getter para a propriedade descricaoLinha
	 *
	 *  @return String de descricaoLinha
	 */
	public String getDescricaoLinha() {
		return descricaoLinha;
	}

	/** Método setter para a propriedade descricaoLinha
	 *
	 * @param descricaoLinha String
	 */
	public void setDescricaoLinha(String descricaoLinha) {
		this.descricaoLinha = descricaoLinha;
	}

	/** Método getter para a propriedade descricaoStatusAdiantamento
	 *
	 *  @return String de descricaoStatusAdiantamento
	 */
	public String getDescricaoStatusAdiantamento() {
		return descricaoStatusAdiantamento;
	}

	/** Método setter para a propriedade descricaoStatusAdiantamento
	 *
	 * @param descricaoStatusAdiantamento String
	 */
	public void setDescricaoStatusAdiantamento(String descricaoStatusAdiantamento) {
		this.descricaoStatusAdiantamento = descricaoStatusAdiantamento;
	}

	/** Método getter para a propriedade descricaoTipoLote
	 *
	 *  @return String de descricaoTipoLote
	 */
	public String getDescricaoTipoLote() {
		return descricaoTipoLote;
	}

	/** Método setter para a propriedade descricaoTipoLote
	 *
	 * @param descricaoTipoLote String
	 */
	public void setDescricaoTipoLote(String descricaoTipoLote) {
		this.descricaoTipoLote = descricaoTipoLote;
	}

	/** Método getter para a propriedade linhaId
	 *
	 *  @return Serializable de linhaId
	 */
	public Serializable getLinhaId() {
		return linhaId;
	}

	/** Método setter para a propriedade linhaId
	 *
	 * @param linhaId Serializable
	 */
	public void setLinhaId(Serializable linhaId) {
		this.linhaId = linhaId;
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
	 * @param loteId Serializable
	 */
	public void setLoteId(Serializable loteId) {
		this.loteId = loteId;
	}

	/** Método getter para a propriedade statusId
	 *
	 *  @return Serializable de statusId
	 */
	public Serializable getStatusId() {
		return statusId;
	}

	/** Método setter para a propriedade statusId
	 *
	 * @param statusId Serializable
	 */
	public void setStatusId(Serializable statusId) {
		this.statusId = statusId;
	}

	/** Método getter para a propriedade statusMascara
	 *
	 *  @return String de statusMascara
	 */
	public String getStatusMascara() {
		return statusMascara;
	}

	/** Método setter para a propriedade statusMascara
	 *
	 * @param statusMascara String
	 */
	public void setStatusMascara(String statusMascara) {
		this.statusMascara = statusMascara;
	}

	/** Método getter para a propriedade tipoId
	 *
	 *  @return Serializable de tipoId
	 */
	public Serializable getTipoId() {
		return tipoId;
	}

	/** Método setter para a propriedade tipoId
	 *
	 * @param tipoId Serializable
	 */
	public void setTipoId(Serializable tipoId) {
		this.tipoId = tipoId;
	}

	/** Método getter para a propriedade hasMovimento
	 *
	 *  @return Boolean de hasMovimento
	 */
	public Boolean getHasMovimento() {
		return hasMovimento;
	}

	/** Método setter para a propriedade hasMovimento
	 *
	 * @param hasMovimento Boolean
	 */
	public void setHasMovimento(Boolean hasMovimento) {
		this.hasMovimento = hasMovimento;
	}
	
	
	/** Método getter para a propriedade statusCor
	 *
	 *  @return String de statusCor
	 */
	public String getStatusCor() {
		return statusCor;
	}

	/** Método setter para a propriedade statusCor
	 *
	 * @param statusCor String
	 */
	public void setStatusCor(String statusCor) {
		this.statusCor = statusCor;
	}

	/** Método getter para a propriedade garantias
	 *
	 * @return garantias do tipo List
	 *
	 */
	
	public List getGarantias() {
		return garantias;
	}

	/** Método setter para a propriedade garantias
	 *
	 * @param garantias 
	 *       <code>garantias<code> a ser designado para LoteCompactVO.java
	 *
	 */
	public void setGarantias(List garantias) {
		this.garantias = garantias;
	}

	/** Método getter para a propriedade podeLiberarGarantia
	 *
	 * @return podeLiberarGarantia do tipo Boolean
	 *
	 */
	
	public Boolean getPodeLiberarGarantia() {
		return podeLiberarGarantia;
	}

	/** Método setter para a propriedade podeLiberarGarantia
	 *
	 * @param podeLiberarGarantia 
	 *       <code>podeLiberarGarantia<code> a ser designado para LoteCompactVO.java
	 *
	 */
	public void setPodeLiberarGarantia(Boolean podeLiberarGarantia) {
		this.podeLiberarGarantia = podeLiberarGarantia;
	}

	/** Verifica se o Lote pode ser liberado de acordo com seu status e 
	 *  se existe itens lançados
	 * 
	 * @return true  para liberação permitida 
	 * 	<code>boolean</code> 
	 */
	public boolean getReleaseValid() {
		
		boolean releaseValid = false;
				
		if ( StatusLote.STATUS_EM_DIGITACAO.equals(this.statusId) ||  StatusLote.STATUS_MANUTENCAO.equals(this.statusId) || StatusLote.STATUS_NF_DIVERGENTE.equals(this.statusId)) {				
			
			if(this.tipoId.equals(TipoLote.TIPO_GARANTIA)) {
				if(this.garantias != null &&  this.garantias.size() > 0) {
					
					Iterator it = this.garantias.iterator();
					GarantiaHeader sg = null;
					int nlib = 0;
					while(it.hasNext()){
						sg = (GarantiaHeader) it.next();
						if(StatusGarantia.STATUS_DIGITACAO.equals(sg.getStatusGarantia().getEntityId())
								|| StatusGarantia.STATUS_MANUTENCAO.equals(sg.getStatusGarantia().getEntityId())
								|| StatusGarantia.STATUS_NF_DIVERGENTE.equals(sg.getStatusGarantia().getEntityId())								
							)
							nlib++;
					}
					
					if(nlib == this.garantias.size())
						releaseValid = true;
				} else
					releaseValid = this.podeLiberarGarantia != null ? this.podeLiberarGarantia.booleanValue() : false;
				
			} else if ( this.getHasMovimento().booleanValue()){
				releaseValid = true;			 
			}
		} 
		
		return releaseValid;		
		
	}
	
	/** Verifica se o Lote pode ser cancelado de acordo com seu status
	 *  O lote só poderá ser cancelado quando seu status estiver com EM DIGITAÇÃO
	 *  e não foi dado adiantamento
	 *  
	 * @return true  para cancelamento permitido
	 * 	<code>boolean</code> 
	 */
	public boolean getCancelValid() {
		
		boolean cancelValid = false;
		
		if ( StatusLote.STATUS_EM_DIGITACAO.equals( this.statusId) 
			 &&	this.getDataEnvioAdiant() == null  
			) {	
				
			cancelValid = true;
			
		}
		
		return cancelValid;		
		
	}

	/** Método getter para a propriedade descricaoReduzidaStatus
	 *
	 *  @return String de descricaoReduzidaStatus
	 */
	public String getDescricaoReduzidaStatus() {
		return descricaoReduzidaStatus;
	}

	/** Método setter para a propriedade descricaoReduzidaStatus
	 *
	 * @param descricaoReduzidaStatus String
	 */
	public void setDescricaoReduzidaStatus(String descricaoReduzidaStatus) {
		this.descricaoReduzidaStatus = descricaoReduzidaStatus;
	}
	
	
}
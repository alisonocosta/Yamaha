/* Yamaha
 * Copyright (c) 2012 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......AnaliseGarantiaVO.java
 *   .: Criação.....12 de Dezembro, 22:18
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "AnaliseGarantiaVO".
 */
package br.com.yamaha.sistemagarantia.processo.model;

import java.util.Date;

import br.com.resource.infra.utils.DateUtils;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "AnaliseGarantiaVO" no sistema.
 *  
 *  
 *  @author Edson Luiz Sumensari
 */
public class AnaliseGarantiaVO {
	
	public static final String PARECER_DEVOLVER_SG = "BCK_SG";
	public static final String PARECER_RECUSAR_SG  = "RCU_SG";
	public static final String PARECER_APROVAR_SG  = "APV_SG";
	public static final String PARECER_CANCELAR_SG = "CNL_SG";	
	
	//----[ Atributos de classe e instância ]-----------------------------------
    
	/** Número do lote */
    private Integer loteId;
    
    private String razaoSocialConcessionaria;
    
    private String nomeRepresentanteNautica;
    
    /** Id da garantia */
    private Integer garantiaId;
    
    /** Armazena um modelo. */
    private String modelo;  
    
    /** Data da Aprovação. */
	private Date dataAprovacao;
    
    /** Armazena a descrição do status de movimentação */
    private String dsStatusMov;
    
    /**Date da liberação do lote*/
    private Date dataLiberacao;
    
    private Integer linhaId;
    //	----[ Métodos Getter ]---------------------------------------------------

	/** Método getter para a propriedade loteId
	 *
	 * @return loteId do tipo Integer
	 *
	 */	
	public Integer getLoteId() {
		return loteId;
	}


	/** Método getter para a propriedade linhaId
	 *
	 * @return linhaId do tipo Integer
	 *
	 */
	
	public Integer getLinhaId() {
		return linhaId;
	}


	/** Método setter para a propriedade linhaId
	 *
	 * @param linhaId 
	 *       <code>linhaId<code> a ser designado para AnaliseGarantiaVO.java
	 *
	 */
	public void setLinhaId(Integer linhaId) {
		this.linhaId = linhaId;
	}


	/** Método getter para a propriedade nomeRepresentanteNautica
	 *
	 * @return nomeRepresentanteNautica do tipo String
	 *
	 */
	
	public String getNomeRepresentanteNautica() {
		return nomeRepresentanteNautica;
	}
	
	/** Método getter para a propriedade razaoSocialConcessionaria
	 *
	 * @return razaoSocialConcessionaria do tipo String
	 *
	 */
	
	public String getRazaoSocialConcessionaria() {
		return razaoSocialConcessionaria;
	}



	/** Método getter para a propriedade garantiaId
	 *
	 * @return garantiaId do tipo Integer
	 *
	 */	
	public Integer getGarantiaId() {
		return garantiaId;
	}



	/** Método getter para a propriedade modelo
	 *
	 * @return modelo do tipo String
	 *
	 */
	
	public String getModelo() {
		return modelo;
	}



	/** Método getter para a propriedade dataAprovacao
	 *
	 * @return dataAprovacao do tipo Date
	 *
	 */
	
	public Date getDataAprovacao() {
		return dataAprovacao;
	}

	/** Método getter para a propriedade dsStatusMov
	 *
	 * @return dsStatusMov do tipo String
	 *
	 */
	
	public String getDsStatusMov() {
		return dsStatusMov;
	}

	/** Método getter para a propriedade dataLiberacao
	 *
	 * @return dataLiberacao do tipo Date
	 *
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
    


	
//	----[ Métodos Setter ]---------------------------------------------------
    /** Método setter para a propriedade nomeRepresentanteNautica
	 *
	 * @param nomeRepresentanteNautica 
	 *       <code>nomeRepresentanteNautica<code> a ser designado para AnaliseGarantiaVO.java
	 *
	 */
	public void setNomeRepresentanteNautica(String nomeRepresentanteNautica) {
		this.nomeRepresentanteNautica = nomeRepresentanteNautica;
	}


	/** Método setter para a propriedade loteId
	 *
	 * @param loteId 
	 *       <code>loteId<code> a ser designado para AnaliseGarantiaVO.java
	 *
	 */
	public void setLoteId(Integer loteId) {
		this.loteId = loteId;
	}

	/** Método setter para a propriedade razaoSocialConcessionaria
	 *
	 * @param razaoSocialConcessionaria 
	 *       <code>razaoSocialConcessionaria<code> a ser designado para AnaliseGarantiaVO.java
	 *
	 */
	public void setRazaoSocialConcessionaria(String razaoSocialConcessionaria) {
		this.razaoSocialConcessionaria = razaoSocialConcessionaria;
	}

	/** Método setter para a propriedade garantiaId
	 *
	 * @param garantiaId 
	 *       <code>garantiaId<code> a ser designado para AnaliseGarantiaVO.java
	 *
	 */
	public void setGarantiaId(Integer garantiaId) {
		this.garantiaId = garantiaId;
	}

	/** Método setter para a propriedade modelo
	 *
	 * @param modelo 
	 *       <code>modelo<code> a ser designado para AnaliseGarantiaVO.java
	 *
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/** Método setter para a propriedade dataAprovacao
	 *
	 * @param dataAprovacao 
	 *       <code>dataAprovacao<code> a ser designado para AnaliseGarantiaVO.java
	 *
	 */
	public void setDataAprovacao(Date dataAprovacao) {
		this.dataAprovacao = dataAprovacao;
	}
	
	/** Método getter para "dataAprovacao" formatada.
     *  @return
     *      <code>String</code>. O valor atual de dataAprovacao,
     *      com uma máscara aplicada.
     */
    public String getMaskedDataAprovacao() {
        return DateUtils.applyMask(this.dataAprovacao);
    }

	/** Método setter para a propriedade dsStatusMov
	 *
	 * @param dsStatusMov 
	 *       <code>dsStatusMov<code> a ser designado para AnaliseGarantiaVO.java
	 *
	 */
	public void setDsStatusMov(String dsStatusMov) {
		this.dsStatusMov = dsStatusMov;
	}


	/** Método setter para "dataLiberacao".
     *  @param entityId
     *      <code>Object</code> a ser designado para dataLiberacao.
     */
    public void setDataLiberacao(Object dataLiberacao) {
        this.dataLiberacao = DateUtils.secureSet(dataLiberacao);
    }
	
}

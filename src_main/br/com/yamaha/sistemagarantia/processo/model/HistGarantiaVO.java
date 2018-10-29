/* Yamaha
 * Copyright (c) 2012 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......HistGarantiaVO.java
 *   .: Criação.....08 de Janeiro de 2013, 22:18
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "HistGarantiaVO".
 */
package br.com.yamaha.sistemagarantia.processo.model;

import java.math.BigDecimal;
import java.util.Date;

import br.com.resource.infra.utils.DateUtils;
import br.com.resource.infra.utils.StringUtils;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "HistGarantiaVO" no sistema.
 *  
 *  
 *  @author Edson Luiz Sumensari
 */
public class HistGarantiaVO {
	
	//----[ Atributos de classe e instância ]-----------------------------------
    
	/** Número do lote */
    private Integer loteId;
    
    /** Número da Garantia */
    private Integer garantiaId;
    
    private String razaoSocialConcessionaria;
    
    /** CNPJ da Concessionária */
    private BigDecimal cnpj;
    
    /** Armazena um modelo. */
    private String modelo;  
    
    /** Data da Abertura. */
	private Date dataAbertura;
    
    /** Armazena a descrição do status de movimentação */
    private String dsStatusMov;
    
    /**Date da liberação do lote*/
    private Date dataLiberacao;
    
    //	----[ Métodos Getter ]---------------------------------------------------

	/** Método getter para a propriedade loteId
	 *
	 * @return loteId do tipo Integer
	 *
	 */	
	public Integer getLoteId() {
		return loteId;
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


	/**
	 * Método getter para o campo cnpj com máscara
	 * 
	 * @return String
	 * 
	 */
	public String getMaskedCnpj() {
		if(cnpj != null)		
			return StringUtils.formatarCnpj(String.valueOf(cnpj.longValue()));
		else
			return "";
	}

	
//	----[ Métodos Setter ]---------------------------------------------------


    
    
    /** Método setter para a propriedade cnpj
	 *
	 * @param cnpj 
	 *       <code>cnpj<code> a ser designado para HistGarantiaVO.java
	 *
	 */
	public void setCnpj(BigDecimal cnpj) {
		this.cnpj = cnpj;
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
	
	/** Método getter para "dataAbertura" formatada.
     *  @return
     *      <code>String</code>. O valor atual de dataAbertura,
     *      com uma máscara aplicada.
     */
    public String getMaskedDataAbertura() {
        return DateUtils.applyMask(this.dataAbertura);
    }
    
    /** Método setter para "dataAbertura".
     *  @param entityId
     *      <code>Object</code> a ser designado para dataAbertura.
     */
    public void setDataAbertura(Object dataAbertura) {
        this.dataAbertura = DateUtils.secureSet(dataAbertura);
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


	/** Método getter para a propriedade cnpj
	 *
	 * @return cnpj do tipo BigDecimal
	 *
	 */
	
	public BigDecimal getCnpj() {
		return cnpj;
	}

	
}

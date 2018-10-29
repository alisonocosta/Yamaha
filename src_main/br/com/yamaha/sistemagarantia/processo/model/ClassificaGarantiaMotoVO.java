/* Yamaha
 * Copyright (c) 2012 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......ClassificaGarantiaMotoVO.java
 *   .: Criação.....08 de Junho
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "ClassificaGarantiaMotoVO".
 */
package br.com.yamaha.sistemagarantia.processo.model;

import java.util.Date;

import br.com.resource.infra.utils.DateUtils;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "ClassificaGarantiaMotoVO" no sistema.
 *  
 *  
 *  @author Edson Luiz Sumensari
 */
public class ClassificaGarantiaMotoVO {
	
	public static final String CLASSIFICA_DEVOLVER_COM_SOLICITACAO	= "BCK_SOL";
	public static final String CLASSIFICA_LIBERAR_SEM_CLASSIFICA  	= "LIB_SEM";
	public static final String CLASSIFICA_LIBERAR_COM_CLASSIFICA 	= "LIB_COM";	
	
	//----[ Atributos de classe e instância ]-----------------------------------
    
	/** Número do lote */
    private Integer loteId;
    
    private String razaoSocialConcessionaria;
    
    /** Quilometragem do produto na data da ordem de serviço. */
    private Long quilometragem; 
    
    /** Id da garantia */
    private Integer garantiaId;
    
    /** Armazena um modelo. */
    private String modelo;  
    
    /** Data da Aprovação. */
	private Date dataAprovacao;
	
	/** Data da abertura da ordem de serviço. */
    private Date dataAberturaOS;
    
    /** Armazena a descrição do status de movimentação */
    private String dsStatusMov;
    
    /**Date da liberação do lote*/
    private Date dataLiberacao;
    
    /** Código da peça causadora */
    private String itemCode;
    
    /** descrição da Peça */
    private String descricao;
    
    //	----[ Métodos Getter ]---------------------------------------------------

    
    
	/** Método getter para a propriedade loteId
	 *
	 * @return loteId do tipo Integer
	 *
	 */	
	public Integer getLoteId() {
		return loteId;
	}


	/** Método getter para a propriedade descricao
	 *
	 * @return descricao do tipo String
	 *
	 */
	
	public String getDescricao() {
		return descricao;
	}


	/** Método setter para a propriedade descricao
	 *
	 * @param descricao 
	 *       <code>descricao<code> a ser designado para ClassificaGarantiaMotoVO.java
	 *
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	/** Método getter para a propriedade dataAberturaOS
	 *
	 * @return dataAberturaOS do tipo Date
	 *
	 */
	
	public Date getDataAberturaOS() {
		return dataAberturaOS;
	}
	
	/** Método getter para "dataAberturaOS".
     *  @return
     *      <code>String</code>. O valor atual de dataAberturaOS.
     */
    public String getMaskedDataAberturaOS() {
        return DateUtils.applyMask(this.dataAberturaOS);
    }

	/** Método getter para a propriedade quilometragem
	 *
	 * @return quilometragem do tipo Long
	 *
	 */
	
	public Long getQuilometragem() {
		return quilometragem;
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
   
   /** Método getter para a propriedade itemCode
	 *
	 * @return itemCode do tipo String
	 *
	 */
	
	public String getItemCode() {
		return itemCode;
	}

	
//	----[ Métodos Setter ]---------------------------------------------------

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


	/** Método setter para "dataAberturaOS".
     *  @param entityId
     *      <code>Object</code> a ser designado para dataAberturaOS.
     */
    public void setDataAberturaOS(Object dataAberturaOS) {
        this.dataAberturaOS = DateUtils.secureSet(dataAberturaOS);
    }

	/** Método setter para a propriedade quilometragem
	 *
	 * @param quilometragem 
	 *       <code>quilometragem<code> a ser designado para ClassificaGarantiaMotoVO.java
	 *
	 */
	public void setQuilometragem(Long quilometragem) {
		this.quilometragem = quilometragem;
	}

	/** Método setter para a propriedade dataAberturaOS
	 *
	 * @param dataAberturaOS 
	 *       <code>dataAberturaOS<code> a ser designado para ClassificaGarantiaMotoVO.java
	 *
	 */
	public void setDataAberturaOS(Date dataAberturaOS) {
		this.dataAberturaOS = dataAberturaOS;
	}
	
	/** Método setter para a propriedade dataLiberacao
	 *
	 * @param dataLiberacao 
	 *       <code>dataLiberacao<code> a ser designado para ClassificaGarantiaMotoVO.java
	 *
	 */
	public void setDataLiberacao(Date dataLiberacao) {
		this.dataLiberacao = dataLiberacao;
	}


	/** Método setter para "dataLiberacao".
     *  @param entityId
     *      <code>Object</code> a ser designado para dataLiberacao.
     */
    public void setDataLiberacao(Object dataLiberacao) {
        this.dataLiberacao = DateUtils.secureSet(dataLiberacao);
    }

	/** Método setter para a propriedade itemCode
	 *
	 * @param itemCode 
	 *       <code>itemCode<code> a ser designado para ClassificaGarantiaMotoVO.java
	 *
	 */
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	
}

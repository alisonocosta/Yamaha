/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioImportedPartsVO.java
 *   .: Cria��o.....26 de setembro, 16:31
 *   .: Autor.......Gisele Panosso
 *   .: Descri��o...Entidade "RelatorioImportedPartsVO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.resource.infra.model.EntityObject;

public class RelatorioImportedPartsVO extends EntityObject {
	
	//----[ Atributos de classe e inst�ncia ]---------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;  
    
    /** Armazena o campo linha*/
    private String linha;
    
    /** Armazena o campo modelo*/
    private String modelo;

    /** Armazena o campo engine*/
    private String engine;
    
    /** Armazena o campo chassi*/
    private String chassi;
    
    /** Armazena o campo claimForm*/
    private Double claimForm;
    
    /** Armazena o campo failureDate*/
    private Date failureDate;
    
    /** Armazena o campo soldDate*/
    private Date soldDate;
    
    /** Armazena o campo partsCode*/
    private String partsCode;
    
    /** Armazena o campo partsName*/
    private String partsName;
    
    /** Armazena o campo orgCode*/
    private String orgCode;
    
    /** Armazena o campo km*/
    private Double km;
    
    /** Armazena o campo sintoma*/
    private String sintoma;  

    /** Armazena o campo condicao*/
    private String condicao;  
    
    /** Armazena o campo failParts*/
    private String failParts;
    
    /** Armazena o campo laborTime*/
    private Double laborTime;
    
    /** Armazena o campo laborUs*/
    private Double laborUs;
    
    /** Armazena o campo qtde*/
    private Double qtde;
    
    /** Armazena o campo partsFobPrice*/
    private Double partsFobPrice;
    
    /** Armazena o campo fornecedor*/
    private String fornecedor;

    /** Armazena a lista de Warranty*/
    private List listWarranty;
    
    /** Armazena a lista de Invoice*/
    private List listInvoice;    
    
    
    //	----[ M�todo Construtor]---------------------------------------------------  

	/** M�todo construtor.
     */
	public RelatorioImportedPartsVO() {
		super();
		listWarranty = new ArrayList();
		listInvoice  = new ArrayList();
	}
	
    //	----[ M�todos Add]---------------------------------------------------	
	
	/** Adiciona uma lista para gera��o da Warranty (Garantia).
     * 
     *  @param listWarranty
     *      listWarranty a ser adicionado.
     *  
     */
    public void addListWarranty(RelatorioImportedPartsWarrantyVO listWarranty) {
    	
        this.listWarranty.add( listWarranty );
        
    }	
    
	/** Adiciona uma lista para gera��o da Invoice (Fatura).
     * 
     *  @param listInvoice
     *      listInvoice a ser adicionado.
     *  
     */
    public void addListInvoice(RelatorioImportedPartsInvoiceVO listInvoice) {
    	
        this.listInvoice.add( listInvoice );
        
    }

    
    //	----[ M�todos Getter ]---------------------------------------------------
    
	/** M�todo getter para a propriedade chassi.
	 *
	 *  @return o valor atual de chassi.
	 */
	public String getChassi() {
		return chassi;
	}

	/** M�todo getter para a propriedade claimForm.
	 *
	 *  @return o valor atual de claimForm.
	 */
	public Double getClaimForm() {
		return claimForm;
	}

	/** M�todo getter para a propriedade condicao.
	 *
	 *  @return o valor atual de condicao.
	 */
	public String getCondicao() {
		return condicao;
	}

	/** M�todo getter para a propriedade engine.
	 *
	 *  @return o valor atual de engine.
	 */
	public String getEngine() {
		return engine;
	}

	/** M�todo getter para a propriedade failParts.
	 *
	 *  @return o valor atual de failParts.
	 */
	public String getFailParts() {
		return failParts;
	}

	/** M�todo getter para a propriedade failureDate.
	 *
	 *  @return o valor atual de failureDate.
	 */
	public Date getFailureDate() {
		return failureDate;
	}

	/** M�todo getter para a propriedade fornecedor.
	 *
	 *  @return o valor atual de fornecedor.
	 */
	public String getFornecedor() {
		return fornecedor;
	}

	/** M�todo getter para a propriedade km.
	 *
	 *  @return o valor atual de km.
	 */
	public Double getKm() {
		return km;
	}

	/** M�todo getter para a propriedade laborTime.
	 *
	 *  @return o valor atual de laborTime.
	 */
	public Double getLaborTime() {
		return laborTime;
	}

	/** M�todo getter para a propriedade laborUs.
	 *
	 *  @return o valor atual de laborUs.
	 */
	public Double getLaborUs() {
		return laborUs;
	}

	/** M�todo getter para a propriedade linha.
	 *
	 *  @return o valor atual de linha.
	 */
	public String getLinha() {
		return linha;
	}

	/** M�todo getter para a propriedade listInvoice.
	 *
	 *  @return o valor atual de listInvoice.
	 */
	public List getListInvoice() {
		return listInvoice;
	}

	/** M�todo getter para a propriedade listWarranty.
	 *
	 *  @return o valor atual de listWarranty.
	 */
	public List getListWarranty() {
		return listWarranty;
	}

	/** M�todo getter para a propriedade modelo.
	 *
	 *  @return o valor atual de modelo.
	 */
	public String getModelo() {
		return modelo;
	}

	/** M�todo getter para a propriedade orgCode.
	 *
	 *  @return o valor atual de orgCode.
	 */
	public String getOrgCode() {
		return orgCode;
	}

	/** M�todo getter para a propriedade partsCode.
	 *
	 *  @return o valor atual de partsCode.
	 */
	public String getPartsCode() {
		return partsCode;
	}

	/** M�todo getter para a propriedade partsFobPrice.
	 *
	 *  @return o valor atual de partsFobPrice.
	 */
	public Double getPartsFobPrice() {
		return partsFobPrice;
	}

	/** M�todo getter para a propriedade partsName.
	 *
	 *  @return o valor atual de partsName.
	 */
	public String getPartsName() {
		return partsName;
	}

	/** M�todo getter para a propriedade qtde.
	 *
	 *  @return o valor atual de qtde.
	 */
	public Double getQtde() {
		return qtde;
	}

	/** M�todo getter para a propriedade sintoma.
	 *
	 *  @return o valor atual de sintoma.
	 */
	public String getSintoma() {
		return sintoma;
	}

	/** M�todo getter para a propriedade soldDate.
	 *
	 *  @return o valor atual de soldDate.
	 */
	public Date getSoldDate() {
		return soldDate;
	}

	 //	----[ M�todos Setter ]---------------------------------------------------
	
	/** Obt�m o valor atual de chassi.
	 * 
	 *  @param chassi 
	 *    O novo valor para chassi.
	 */
	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	/** Obt�m o valor atual de claimForm.
	 * 
	 *  @param claimForm 
	 *    O novo valor para claimForm.
	 */
	public void setClaimForm(Double claimForm) {
		this.claimForm = claimForm;
	}

	/** Obt�m o valor atual de condicao.
	 * 
	 *  @param condicao 
	 *    O novo valor para condicao.
	 */
	public void setCondicao(String condicao) {
		this.condicao = condicao;
	}

	/** Obt�m o valor atual de engine.
	 * 
	 *  @param engine 
	 *    O novo valor para engine.
	 */
	public void setEngine(String engine) {
		this.engine = engine;
	}

	/** Obt�m o valor atual de failParts.
	 * 
	 *  @param failParts 
	 *    O novo valor para failParts.
	 */
	public void setFailParts(String failParts) {
		this.failParts = failParts;
	}

	/** Obt�m o valor atual de failureDate.
	 * 
	 *  @param failureDate 
	 *    O novo valor para failureDate.
	 */
	public void setFailureDate(Date failureDate) {
		this.failureDate = failureDate;
	}

	/** Obt�m o valor atual de fornecedor.
	 * 
	 *  @param fornecedor 
	 *    O novo valor para fornecedor.
	 */
	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	/** Obt�m o valor atual de km.
	 * 
	 *  @param km 
	 *    O novo valor para km.
	 */
	public void setKm(Double km) {
		this.km = km;
	}

	/** Obt�m o valor atual de laborTime.
	 * 
	 *  @param laborTime 
	 *    O novo valor para laborTime.
	 */
	public void setLaborTime(Double laborTime) {
		this.laborTime = laborTime;
	}

	/** Obt�m o valor atual de laborUs.
	 * 
	 *  @param laborUs 
	 *    O novo valor para laborUs.
	 */
	public void setLaborUs(Double laborUs) {
		this.laborUs = laborUs;
	}

	/** Obt�m o valor atual de linha.
	 * 
	 *  @param linha 
	 *    O novo valor para linha.
	 */
	public void setLinha(String linha) {
		this.linha = linha;
	}

	/** Obt�m o valor atual de listInvoice.
	 * 
	 *  @param listInvoice 
	 *    O novo valor para listInvoice.
	 */
	public void setListInvoice(List listInvoice) {
		this.listInvoice = listInvoice;
	}

	/** Obt�m o valor atual de listWarranty.
	 * 
	 *  @param listWarranty 
	 *    O novo valor para listWarranty.
	 */
	public void setListWarranty(List listWarranty) {
		this.listWarranty = listWarranty;
	}

	/** Obt�m o valor atual de modelo.
	 * 
	 *  @param modelo 
	 *    O novo valor para modelo.
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/** Obt�m o valor atual de orgCode.
	 * 
	 *  @param orgCode 
	 *    O novo valor para orgCode.
	 */
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	/** Obt�m o valor atual de partsCode.
	 * 
	 *  @param partsCode 
	 *    O novo valor para partsCode.
	 */
	public void setPartsCode(String partsCode) {
		this.partsCode = partsCode;
	}

	/** Obt�m o valor atual de partsFobPrice.
	 * 
	 *  @param partsFobPrice 
	 *    O novo valor para partsFobPrice.
	 */
	public void setPartsFobPrice(Double partsFobPrice) {
		this.partsFobPrice = partsFobPrice;
	}

	/** Obt�m o valor atual de partsName.
	 * 
	 *  @param partsName 
	 *    O novo valor para partsName.
	 */
	public void setPartsName(String partsName) {
		this.partsName = partsName;
	}

	/** Obt�m o valor atual de qtde.
	 * 
	 *  @param qtde 
	 *    O novo valor para qtde.
	 */
	public void setQtde(Double qtde) {
		this.qtde = qtde;
	}

	/** Obt�m o valor atual de sintoma.
	 * 
	 *  @param sintoma 
	 *    O novo valor para sintoma.
	 */
	public void setSintoma(String sintoma) {
		this.sintoma = sintoma;
	}

	/** Obt�m o valor atual de soldDate.
	 * 
	 *  @param soldDate 
	 *    O novo valor para soldDate.
	 */
	public void setSoldDate(Date soldDate) {
		this.soldDate = soldDate;
	}	
    

    
    
}

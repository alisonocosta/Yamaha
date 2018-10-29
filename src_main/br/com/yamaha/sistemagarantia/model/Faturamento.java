/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......TipoLote.java
 *   .: Cria��o.....23 de abril, 15:53
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "Faturamento".
 */
package br.com.yamaha.sistemagarantia.model;

import java.util.Date;

import br.com.resource.infra.model.EntityObject;
import br.com.resource.infra.utils.DateUtils;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "Faturamento" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class Faturamento extends EntityObject {

    //----[ Atributos de classe e inst�ncia ]-----------------------------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L; 
    
    /** Codigo de neg�cio para o chassi. */
    private String chassi;
    
    /** Armazena o n�mero */
    private Long customerTrxLineId;
    
    /** Armazena o n�mero da note fiscal. */
    private Long numeroNotaFiscal;
    
    /** Armazena a data da nota fiscal. */
    private Date dataNotaFiscal;
    
    /** Armazena a s�rie da nota fiscal. */
    private String serieNotaFiscal;
    
    /** Armazena o id do modelo do produto. */
    private String modelo;
    
    /** Descri��o do Concessionaria. */
    private Concessionaria concessionaria;
    
    /** Objeto linha relacionado ao lote */
    private Linha linha;
    
    /** Id item relacionado ao lote */
    private Long itemId;
    //private Item item;
    
    /** Entidade empresa realcionada */
    private Empresa empresa;

    //	----[ M�todos Getter ]---------------------------------------------------
    
	/** M�todo getter para a propriedade chassi
	 * 
	 *  @return String
	 *
	 */
	public String getChassi() {
		return chassi;
	}

	/** M�todo getter para a propriedade concessionaria
	 * 
	 *  @return Concessionaria
	 *
	 */
	public Concessionaria getConcessionaria() {
		return concessionaria;
	}
	
	/** M�todo getter para a propriedade dataNotaFiscal
	 * 
	 *  @return Date
	 *
	 */
	public Date getDataNotaFiscal() {
		return dataNotaFiscal;
	}
	
	/** M�todo getter para "dataNotaFiscal".
     *  @return
     *      <code>String</code>. O valor atual de dataNotaFiscal,
     *      com uma m�scara aplicada.
     */
    public String getMaskedDataNotaFiscal() {
        return DateUtils.applyMask(this.dataNotaFiscal);
    }    

	/** M�todo getter para a propriedade numeroNotaFiscal
	 * 
	 *  @return Long
	 *
	 */
	public Long getNumeroNotaFiscal() {
		return numeroNotaFiscal;
	}

	/** M�todo getter para a propriedade serieNotaFiscal
	 * 
	 *  @return String
	 *
	 */
	public String getSerieNotaFiscal() {
		return serieNotaFiscal;
	}
	
	/** M�todo getter para a propriedade customerTrxLineId
	 * 
	 *  @return Long
	 *
	 */
	public Long getCustomerTrxLineId() {
		return customerTrxLineId;
	}

	/** M�todo getter para a propriedade linha
	 * 
	 *  @return Linha
	 *
	 */
	public Linha getLinha() {
		return linha;
	}
	
	/** M�todo getter para a propriedade itemId
	 *
	 *  @return itemId de item
	 */
	public Long getItemId() {
		return itemId;
	}
	
	/** M�todo getter para a propriedade empresa
	 *
	 *  @return Empresa de empresa
	 */
	public Empresa getEmpresa() {
		return empresa;
	}
	
	/** M�todo getter para a propriedade modelo
	 *
	 *  @return String de modelo
	 */
	public String getModelo() {
		return modelo;
	}

	
	//	----[ M�todos Setter ]---------------------------------------------------
	
	/** M�todo setter para a propriedade chassi
	 *
	 * @param chassi 
	 *           <code>String</code> a ser designado para chassi.
	 * 
	 */
	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	/** M�todo setter para a propriedade concessionaria
	 *
	 * @param concessionaria 
	 *           <code>Concessionaria</code> a ser designado para concessionaria.
	 * 
	 */
	public void setConcessionaria(Concessionaria concessionaria) {
		this.concessionaria = concessionaria;
	}

	/** M�todo setter para a propriedade dataNotaFiscal
	 *
	 * @param dataNotaFiscal 
	 *           <code>Date</code> a ser designado para dataNotaFiscal.
	 * 
	 */
	public void setDataNotaFiscal(Date dataNotaFiscal) {
		this.dataNotaFiscal = dataNotaFiscal;
	}

	/** M�todo setter para a propriedade numeroNotaFiscal
	 *
	 * @param numeroNotaFiscal 
	 *           <code>Long</code> a ser designado para numeroNotaFiscal.
	 * 
	 */
	public void setNumeroNotaFiscal(Long numeroNotaFiscal) {
		this.numeroNotaFiscal = numeroNotaFiscal;
	}

	/** M�todo setter para a propriedade serieNotaFiscal
	 *
	 * @param serieNotaFiscal 
	 *           <code>String</code> a ser designado para serieNotaFiscal.
	 * 
	 */
	public void setSerieNotaFiscal(String serieNotaFiscal) {
		this.serieNotaFiscal = serieNotaFiscal;
	}

	/** M�todo setter para a propriedade customerTrxLineId
	 *
	 * @param customerTrxLineId 
	 *           <code>Long</code> a ser designado para customerTrxLineId.
	 * 
	 */
	public void setCustomerTrxLineId(Long customerTrxLineId) {
		this.customerTrxLineId = customerTrxLineId;
	}

	/** M�todo setter para a propriedade linha
	 *
	 * @param linha 
	 *           <code>Linha</code> a ser designado para linha.
	 * 
	 */
	public void setLinha(Linha linha) {
		this.linha = linha;
	}

	/** M�todo setter para a propriedade itemId
	 *
	 * @param itemId de  item
	 */
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	/** M�todo setter para a propriedade empresa
	 *
	 * @param empresa Empresa
	 */
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	/** M�todo setter para a propriedade modelo
	 *
	 * @param modelo String
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	
	
}
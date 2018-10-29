/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......TipoLote.java
 *   .: Criação.....23 de abril, 15:53
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "Faturamento".
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

    //----[ Atributos de classe e instância ]-----------------------------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L; 
    
    /** Codigo de negócio para o chassi. */
    private String chassi;
    
    /** Armazena o número */
    private Long customerTrxLineId;
    
    /** Armazena o número da note fiscal. */
    private Long numeroNotaFiscal;
    
    /** Armazena a data da nota fiscal. */
    private Date dataNotaFiscal;
    
    /** Armazena a série da nota fiscal. */
    private String serieNotaFiscal;
    
    /** Armazena o id do modelo do produto. */
    private String modelo;
    
    /** Descrição do Concessionaria. */
    private Concessionaria concessionaria;
    
    /** Objeto linha relacionado ao lote */
    private Linha linha;
    
    /** Id item relacionado ao lote */
    private Long itemId;
    //private Item item;
    
    /** Entidade empresa realcionada */
    private Empresa empresa;

    //	----[ Métodos Getter ]---------------------------------------------------
    
	/** Método getter para a propriedade chassi
	 * 
	 *  @return String
	 *
	 */
	public String getChassi() {
		return chassi;
	}

	/** Método getter para a propriedade concessionaria
	 * 
	 *  @return Concessionaria
	 *
	 */
	public Concessionaria getConcessionaria() {
		return concessionaria;
	}
	
	/** Método getter para a propriedade dataNotaFiscal
	 * 
	 *  @return Date
	 *
	 */
	public Date getDataNotaFiscal() {
		return dataNotaFiscal;
	}
	
	/** Método getter para "dataNotaFiscal".
     *  @return
     *      <code>String</code>. O valor atual de dataNotaFiscal,
     *      com uma máscara aplicada.
     */
    public String getMaskedDataNotaFiscal() {
        return DateUtils.applyMask(this.dataNotaFiscal);
    }    

	/** Método getter para a propriedade numeroNotaFiscal
	 * 
	 *  @return Long
	 *
	 */
	public Long getNumeroNotaFiscal() {
		return numeroNotaFiscal;
	}

	/** Método getter para a propriedade serieNotaFiscal
	 * 
	 *  @return String
	 *
	 */
	public String getSerieNotaFiscal() {
		return serieNotaFiscal;
	}
	
	/** Método getter para a propriedade customerTrxLineId
	 * 
	 *  @return Long
	 *
	 */
	public Long getCustomerTrxLineId() {
		return customerTrxLineId;
	}

	/** Método getter para a propriedade linha
	 * 
	 *  @return Linha
	 *
	 */
	public Linha getLinha() {
		return linha;
	}
	
	/** Método getter para a propriedade itemId
	 *
	 *  @return itemId de item
	 */
	public Long getItemId() {
		return itemId;
	}
	
	/** Método getter para a propriedade empresa
	 *
	 *  @return Empresa de empresa
	 */
	public Empresa getEmpresa() {
		return empresa;
	}
	
	/** Método getter para a propriedade modelo
	 *
	 *  @return String de modelo
	 */
	public String getModelo() {
		return modelo;
	}

	
	//	----[ Métodos Setter ]---------------------------------------------------
	
	/** Método setter para a propriedade chassi
	 *
	 * @param chassi 
	 *           <code>String</code> a ser designado para chassi.
	 * 
	 */
	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	/** Método setter para a propriedade concessionaria
	 *
	 * @param concessionaria 
	 *           <code>Concessionaria</code> a ser designado para concessionaria.
	 * 
	 */
	public void setConcessionaria(Concessionaria concessionaria) {
		this.concessionaria = concessionaria;
	}

	/** Método setter para a propriedade dataNotaFiscal
	 *
	 * @param dataNotaFiscal 
	 *           <code>Date</code> a ser designado para dataNotaFiscal.
	 * 
	 */
	public void setDataNotaFiscal(Date dataNotaFiscal) {
		this.dataNotaFiscal = dataNotaFiscal;
	}

	/** Método setter para a propriedade numeroNotaFiscal
	 *
	 * @param numeroNotaFiscal 
	 *           <code>Long</code> a ser designado para numeroNotaFiscal.
	 * 
	 */
	public void setNumeroNotaFiscal(Long numeroNotaFiscal) {
		this.numeroNotaFiscal = numeroNotaFiscal;
	}

	/** Método setter para a propriedade serieNotaFiscal
	 *
	 * @param serieNotaFiscal 
	 *           <code>String</code> a ser designado para serieNotaFiscal.
	 * 
	 */
	public void setSerieNotaFiscal(String serieNotaFiscal) {
		this.serieNotaFiscal = serieNotaFiscal;
	}

	/** Método setter para a propriedade customerTrxLineId
	 *
	 * @param customerTrxLineId 
	 *           <code>Long</code> a ser designado para customerTrxLineId.
	 * 
	 */
	public void setCustomerTrxLineId(Long customerTrxLineId) {
		this.customerTrxLineId = customerTrxLineId;
	}

	/** Método setter para a propriedade linha
	 *
	 * @param linha 
	 *           <code>Linha</code> a ser designado para linha.
	 * 
	 */
	public void setLinha(Linha linha) {
		this.linha = linha;
	}

	/** Método setter para a propriedade itemId
	 *
	 * @param itemId de  item
	 */
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	/** Método setter para a propriedade empresa
	 *
	 * @param empresa Empresa
	 */
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	/** Método setter para a propriedade modelo
	 *
	 * @param modelo String
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	
	
}
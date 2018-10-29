/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......Linha.java
 *   .: Criação.....23 de abril, 16:09
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Entidade "Linha".
 */
package br.com.yamaha.sistemagarantia.model;

import java.io.Serializable;
import java.util.Date;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "Item" no sistema.
 *  
 *  @author Thiago Uriel M. Garcia
 */
public class Item extends EntityObject {

    //----[ Atributos de classe e instância ]-----------------------------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;  	
    
    public static final String ORIGEM_NAC = "NAC";
    public static final String ORIGEM_IMP = "IMP";
    
    public static final String TIPO_VEICULO_VA = "VA";
    public static final String TIPO_VEICULO_MP = "MP";
    public static final String TIPO_VEICULO_GE = "GE";
    
    public static final String SEGMENTO_COMPLEMENTAR = "COMPL";
    
    /** Constante para Inf. de Mercado sem Peça. */
    public static final String SEM_PECA_IM = "XXX";

    private Integer anoModelo;
    private Integer peso; 
    private Serializable qtdePecas;
    
    private Long organizationId;
    
    private String itemCode;
    private String descricao;
    private String codigoProduto;
    private String modelo;
    private String cor;
    private String tipo;
    private String tipoVeiculo;
    private String segmento;
    private String origem;
    private String combustivel;
    private String tipoPartida;
    private String cilReal;
    private String cilUsual;
    private String cilindros;
    private String hp;
    private String tempos;
    private String bloqueiaGarantia;
    
    private Date dataInclusaoPeca;
    
    /** Retorna o itemCode e a descrição concatenados
     * 
     * @return String
     */
    public String getCodeDescricao(){
    	
    	return this.getItemCode() + " - " + this.getDescricao().replace('\"','\'');
    	
    }
    
	/**
	 * @return the anoModelo
	 */
	public Integer getAnoModelo() {
		return anoModelo;
	}
	/**
	 * @param anoModelo the anoModelo to set
	 */
	public void setAnoModelo(Integer anoModelo) {
		this.anoModelo = anoModelo;
	}
	/**
	 * @return the bloqueiaGarantia
	 */
	public String getBloqueiaGarantia() {
		return bloqueiaGarantia;
	}
	/**
	 * @param bloqueiaGarantia the bloqueiaGarantia to set
	 */
	public void setBloqueiaGarantia(String bloqueiaGarantia) {
		this.bloqueiaGarantia = bloqueiaGarantia;
	}
	/**
	 * @return the cilindros
	 */
	public String getCilindros() {
		return cilindros;
	}
	/**
	 * @param cilindros the cilindros to set
	 */
	public void setCilindros(String cilindros) {
		this.cilindros = cilindros;
	}
	/**
	 * @return the cilReal
	 */
	public String getCilReal() {
		return cilReal;
	}
	/**
	 * @param cilReal the cilReal to set
	 */
	public void setCilReal(String cilReal) {
		this.cilReal = cilReal;
	}
	/**
	 * @return the cilUsual
	 */
	public String getCilUsual() {
		return cilUsual;
	}
	/**
	 * @param cilUsual the cilUsual to set
	 */
	public void setCilUsual(String cilUsual) {
		this.cilUsual = cilUsual;
	}
	/**
	 * @return the codigoProduto
	 */
	public String getCodigoProduto() {
		return codigoProduto;
	}
	/**
	 * @param codigoProduto the codigoProduto to set
	 */
	public void setCodigoProduto(String codigoProduto) {
		this.codigoProduto = codigoProduto;
	}
	/**
	 * @return the combustivel
	 */
	public String getCombustivel() {
		return combustivel;
	}
	/**
	 * @param combustivel the combustivel to set
	 */
	public void setCombustivel(String combustivel) {
		this.combustivel = combustivel;
	}
	/**
	 * @return the cor
	 */
	public String getCor() {
		return cor;
	}
	/**
	 * @param cor the cor to set
	 */
	public void setCor(String cor) {
		this.cor = cor;
	}
	/**
	 * @return the dataInclusaoPeca
	 */
	public Date getDataInclusaoPeca() {
		return dataInclusaoPeca;
	}
	/**
	 * @param dataInclusaoPeca the dataInclusaoPeca to set
	 */
	public void setDataInclusaoPeca(Date dataInclusaoPeca) {
		this.dataInclusaoPeca = dataInclusaoPeca;
	}
	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	/**
	 * @return the hp
	 */
	public String getHp() {
		return hp;
	}
	/**
	 * @param hp the hp to set
	 */
	public void setHp(String hp) {
		this.hp = hp;
	}
	/**
	 * @return the itemCode
	 */
	public String getItemCode() {
		return itemCode;
	}
	/**
	 * @param itemCode the itemCode to set
	 */
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	/**
	 * @return the modelo
	 */
	public String getModelo() {
		return modelo;
	}
	/**
	 * @param modelo the modelo to set
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	/**
	 * @return the organizationId
	 */
	public Long getOrganizationId() {
		return organizationId;
	}
	/**
	 * @param organizationId the organizationId to set
	 */
	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
	/**
	 * @return the origem
	 */
	public String getOrigem() {
		return origem;
	}
	/**
	 * @param origem the origem to set
	 */
	public void setOrigem(String origem) {
		this.origem = origem;
	}
	/**
	 * @return the peso
	 */
	public Integer getPeso() {
		return peso;
	}
	/**
	 * @param peso the peso to set
	 */
	public void setPeso(Integer peso) {
		this.peso = peso;
	}
	/**
	 * @return the segmento
	 */
	public String getSegmento() {
		return segmento;
	}
	/**
	 * @param segmento the segmento to set
	 */
	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}
	/**
	 * @return the tempos
	 */
	public String getTempos() {
		return tempos;
	}
	/**
	 * @param tempos the tempos to set
	 */
	public void setTempos(String tempos) {
		this.tempos = tempos;
	}
	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	/**
	 * @return the tipoPartida
	 */
	public String getTipoPartida() {
		return tipoPartida;
	}
	/**
	 * @param tipoPartida the tipoPartida to set
	 */
	public void setTipoPartida(String tipoPartida) {
		this.tipoPartida = tipoPartida;
	}
	/**
	 * @return the tipoVeiculo
	 */
	public String getTipoVeiculo() {
		return tipoVeiculo;
	}
	/**
	 * @param tipoVeiculo the tipoVeiculo to set
	 */
	public void setTipoVeiculo(String tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}

	/** Método getter para a propriedade qtdePecas
	 *
	 *  @return Serializable de qtdePecas
	 */
	public Serializable getQtdePecas() {
		return qtdePecas;
	}

	/** Método setter para a propriedade qtdePecas
	 *
	 * @param qtdePecas Serializable
	 */
	public void setQtdePecas(Serializable qtdePecas) {
		this.qtdePecas = qtdePecas;
	}
    
    
}
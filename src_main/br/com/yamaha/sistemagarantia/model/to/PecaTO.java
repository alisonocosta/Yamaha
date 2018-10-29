/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......GarantiaPecaAction.java
 *   .: Criação.....15 de junho, 10:11
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Transfer Object para uma peça.
 */
package br.com.yamaha.sistemagarantia.model.to;

/** Transfer Object de uma peça.
 * 
 *  @author Thiago Uriel M. Garcia
 */
public class PecaTO {
	
	private long lineId;
	private boolean enviar;
	private boolean cobrar;
	private boolean faltante;
	private Boolean causadora;
	private int quantidade;
	private long pecaId;
	private String descricao;
	private String pecaCode;
	
	/**
	 * @return the cobrar
	 */
	public boolean isCobrar() {
		return cobrar;
	}
	/**
	 * @param cobrar the cobrar to set
	 */
	public void setCobrar(boolean cobrar) {
		this.cobrar = cobrar;
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
	 * @return the enviar
	 */
	public boolean isEnviar() {
		return enviar;
	}
	/**
	 * @param enviar the enviar to set
	 */
	public void setEnviar(boolean enviar) {
		this.enviar = enviar;
	}
	/**
	 * @return the faltante
	 */
	public boolean isFaltante() {
		return faltante;
	}
	/**
	 * @param faltante the faltante to set
	 */
	public void setFaltante(boolean faltante) {
		this.faltante = faltante;
	}
	/**
	 * @return the pecaId
	 */
	public long getPecaId() {
		return pecaId;
	}
	/**
	 * @param pecaId the pecaId to set
	 */
	public void setPecaId(long pecaId) {
		this.pecaId = pecaId;
	}
	/**
	 * @return the quantidade
	 */
	public int getQuantidade() {
		return quantidade;
	}
	/**
	 * @param quantidade the quantidade to set
	 */
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	/** Método getter para a propriedade lineId
	 * 
	 *  @return long
	 *
	 */
	public long getLineId() {
		return lineId;
	}
	
	/** Método setter para a propriedade lineId
	 *
	 * @param lineId 
	 *           <code>long</code> a ser designado para lineId.
	 * 
	 */
	public void setLineId(long lineId) {
		this.lineId = lineId;
	}
	/** Método getter para a propriedade pecaCode
	 * 
	 *  @return String
	 *
	 */
	public String getPecaCode() {
		return pecaCode;
	}
	/** Método setter para a propriedade pecaCode
	 *
	 * @param pecaCode 
	 *           <code>String</code> a ser designado para pecaCode.
	 * 
	 */
	public void setPecaCode(String pecaCode) {
		this.pecaCode = pecaCode;
	}
	/** Método getter para a propriedade causadora
	 *
	 * @return causadora do tipo Boolean
	 *
	 */
	
	public Boolean getCausadora() {
		return causadora;
	}
	/** Método setter para a propriedade causadora
	 *
	 * @param causadora 
	 *       <code>causadora<code> a ser designado para PecaTO.java
	 *
	 */
	public void setCausadora(Boolean causadora) {
		this.causadora = causadora;
	}
	
	/**
	 * @return the Causadora
	 */
	public boolean isCausadora() {
		return causadora.booleanValue();
	}
	
	
}

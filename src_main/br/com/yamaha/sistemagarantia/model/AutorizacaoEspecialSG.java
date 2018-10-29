/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......AutorizacaoEspecialSG.java
 *   .: Criação.....08 de abril, 15:09
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Entidade "AutorizacaoEspecialSG".
 */
package br.com.yamaha.sistemagarantia.model;

import java.io.Serializable;
import java.util.Date;

import br.com.resource.infra.model.EntityObject;

/** Bean para entidade "AutorizacaoEspecialSG".
 * 
 *  @author Thiago Uriel M. Garcia
 *  alterado o nome da classe em 15/06/2007 - Edson Luiz Sumensari 
 */
public class AutorizacaoEspecialSG extends EntityObject {

	private static final long serialVersionUID = 1L;
	
	public static final String STATUS_USO_NOT = "N";
	public static final String STATUS_USO_YES = "S";
	
	private String numAutorizacao;
	private String chassi;
	private String autPecas;
	private String autServico;
	private String autMo3;
	private Serializable representanteId;
	private Serializable concessionariaId;
	private String statusUso;
	
	private Date startDate;
	private Date endDate;
	
	/**
	 * @return the autMo3
	 */
	public String getAutMo3() {
		return autMo3;
	}
	/**
	 * @param autMo3 the autMo3 to set
	 */
	public void setAutMo3(String autMo3) {
		this.autMo3 = autMo3;
	}
	/**
	 * @return the autPecas
	 */
	public String getAutPecas() {
		return autPecas;
	}
	/**
	 * @param autPecas the autPecas to set
	 */
	public void setAutPecas(String autPecas) {
		this.autPecas = autPecas;
	}
	/**
	 * @return the autServico
	 */
	public String getAutServico() {
		return autServico;
	}
	/**
	 * @param autServico the autServico to set
	 */
	public void setAutServico(String autServico) {
		this.autServico = autServico;
	}
	/**
	 * @return the chassi
	 */
	public String getChassi() {
		return chassi;
	}
	/**
	 * @param chassi the chassi to set
	 */
	public void setChassi(String chassi) {
		this.chassi = chassi;
	}
	/**
	 * @return the concessionariaId
	 */
	public Serializable getConcessionariaId() {
		return concessionariaId;
	}
	/**
	 * @param concessionariaId the concessionariaId to set
	 */
	public void setConcessionariaId(Serializable concessionariaId) {
		this.concessionariaId = concessionariaId;
	}
	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the numAutorizacao
	 */
	public String getNumAutorizacao() {
		return numAutorizacao;
	}
	/**
	 * @param numAutorizacao the numAutorizacao to set
	 */
	public void setNumAutorizacao(String numAutorizacao) {
		this.numAutorizacao = numAutorizacao;
	}
	/**
	 * @return the representanteId
	 */
	public Serializable getRepresentanteId() {
		return representanteId;
	}
	/**
	 * @param representanteId the representanteId to set
	 */
	public void setRepresentanteId(Serializable representanteId) {
		this.representanteId = representanteId;
	}
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	/** Método getter para a propriedade statusUso
	 *
	 *  @return String de statusUso
	 */
	public String getStatusUso() {
		return statusUso;
	}
	
	/** Método setter para a propriedade statusUso
	 *
	 * @param statusUso String
	 */
	public void setStatusUso(String statusUso) {
		this.statusUso = statusUso;
	}
	
}
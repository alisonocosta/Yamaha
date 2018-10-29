/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......Revisao.java
 *   .: Criação.....09 de maio, 21:45
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "Revisao".
 */
package br.com.yamaha.sistemagarantia.model;

import java.util.Date;
import java.util.List;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "Revisao" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class Revisao extends EntityObject {
	
	//----[ Atributos de classe e instância ]-----------------------------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L; 
    
    /** Constante de Número de revisão zero Km  */
    public static final Long REVISAO_ZERO = new Long(1);
    
    /** Constante de Número de revisão 2  */
    public static final Long REVISAO_HUM = new Long(2); 
    
    /** Constante de Número de revisão 3  */
    public static final Long REVISAO_DOIS = new Long(3); 
    
    private String 	modelo;
    private String 	descricao;
    private Long 	numeroRevisao;
    private Long    tempoHoras;
    private Long	tempoDias;
    private Long	tempoKm;
    private Double	toleranciaMotoPerc;
    private Double	toleranciaMotoMes;
    private Double	toleranciaNauticaDias;
    private Double	toleranciaNauticaHoras;
    private Date	dataInicio;
    private Date	dataTermino;    
    private List	cupons;
    
    //	----[ Métodos Getter ]---------------------------------------------------
    
	/** Método getter para a propriedade dataInicio
	 *
	 *  @return the dataInicio
	 */
	public Date getDataInicio() {
		return dataInicio;
	}
	/** Método getter para a propriedade dataTermino
	 *
	 *  @return the dataTermino
	 */
	public Date getDataTermino() {
		return dataTermino;
	}
	/** Método getter para a propriedade descricao
	 *
	 *  @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	/** Método getter para a propriedade modelo
	 *
	 *  @return the modelo
	 */
	public String getModelo() {
		return modelo;
	}
	/** Método getter para a propriedade numeroRevisao
	 *
	 *  @return the numeroRevisao
	 */
	public Long getNumeroRevisao() {
		return numeroRevisao;
	}
	/** Método getter para a propriedade tempoDias
	 *
	 *  @return the tempoDias
	 */
	public Long getTempoDias() {
		return tempoDias;
	}
	/** Método getter para a propriedade tempoHoras
	 *
	 *  @return the tempoHoras
	 */
	public Long getTempoHoras() {
		return tempoHoras;
	}
	/** Método getter para a propriedade tempoKm
	 *
	 *  @return the tempoKm
	 */
	public Long getTempoKm() {
		return tempoKm;
	}
	/** Método getter para a propriedade toleranciaNauticaDias
	 *
	 *  @return the toleranciaNauticaDias
	 */
	public Double getToleranciaNauticaDias() {
		return toleranciaNauticaDias;
	}
	/** Método getter para a propriedade toleranciaMotoHoras
	 *
	 *  @return the toleranciaMotoHoras
	 */
	public Double getToleranciaNauticaHoras() {
		return toleranciaNauticaHoras;
	}
	/** Método getter para a propriedade toleranciaMotoMes
	 *
	 *  @return the toleranciaMotoMes
	 */
	public Double getToleranciaMotoMes() {
		return toleranciaMotoMes;
	}
	/** Método getter para a propriedade toleranciaMotoPerc
	 *
	 *  @return the toleranciaMotoPerc
	 */
	public Double getToleranciaMotoPerc() {
		return toleranciaMotoPerc;
	}
	
	/** Método getter para a propriedade cupons
	 * 
	 *  @return List
	 *
	 */
	public List getCupons() {
		return cupons;
	}
	
	//	----[ Métodos Setter ]---------------------------------------------------
	
	/** Método setter para a propriedade cupons
	 *
	 * @param cupons 
	 *           <code>List</code> a ser designado para cupons.
	 * 
	 */
	public void setCupons(List cupons) {
		this.cupons = cupons;
	}
	
	/** Método setter para a propriedade dataInicio
	 *
	 * @param dataInicio Date
	 */
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	/** Método setter para a propriedade dataTermino
	 *
	 * @param dataTermino Date
	 */
	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}
	/** Método setter para a propriedade descricao
	 *
	 * @param descricao String
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	/** Método setter para a propriedade modelo
	 *
	 * @param modelo String
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	/** Método setter para a propriedade numeroRevisao
	 *
	 * @param numeroRevisao Long
	 */
	public void setNumeroRevisao(Long numeroRevisao) {
		this.numeroRevisao = numeroRevisao;
	}
	/** Método setter para a propriedade tempoDias
	 *
	 * @param tempoDias Long
	 */
	public void setTempoDias(Long tempoDias) {
		this.tempoDias = tempoDias;
	}
	/** Método setter para a propriedade tempoHoras
	 *
	 * @param tempoHoras Long
	 */
	public void setTempoHoras(Long tempoHoras) {
		this.tempoHoras = tempoHoras;
	}
	/** Método setter para a propriedade tempoKm
	 *
	 * @param tempoKm Long
	 */
	public void setTempoKm(Long tempoKm) {
		this.tempoKm = tempoKm;
	}
	/** Método setter para a propriedade toleranciaMotoDias
	 *
	 * @param toleranciaMotoDias Double
	 */
	public void setToleranciaNauticaDias(Double toleranciaNauticaDias) {
		this.toleranciaNauticaDias = toleranciaNauticaDias;
	}
	/** Método setter para a propriedade toleranciaNauticaHoras
	 *
	 * @param toleranciaNauticaHoras Double
	 */
	public void setToleranciaNauticaHoras(Double toleranciaNauticaHoras) {
		this.toleranciaNauticaHoras = toleranciaNauticaHoras;
	}
	/** Método setter para a propriedade toleranciaMotoMes
	 *
	 * @param toleranciaMotoMes Double
	 */
	public void setToleranciaMotoMes(Double toleranciaMotoMes) {
		this.toleranciaMotoMes = toleranciaMotoMes;
	}
	/** Método setter para a propriedade toleranciaMotoPerc
	 *
	 * @param toleranciaMotoPerc Double
	 */
	public void setToleranciaMotoPerc(Double toleranciaMotoPerc) {
		this.toleranciaMotoPerc = toleranciaMotoPerc;
	}
	
}

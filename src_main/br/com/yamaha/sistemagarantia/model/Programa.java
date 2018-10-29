/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......Programa.java
 *   .: Criação.....13 de junho, 12:51
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Entidade "Programa".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema representando a tabela "Programa".
 *  
 *  @author Thiago Uriel M. Garcia
 */
public class Programa extends EntityObject {

	 /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L; 
    
    /** Constatnte.*/ 
    public static final String MENU_JAVA = "J"; 
    
    /** Constatnte.*/ 
    public static final String MENU_JAVA_INTERNO = "A"; 
	
	private String codigoPrograma;
	private String objectCode;
	private String descricao;
	private Integer linhasPagina;
	private Long segmento;
	private String tipoRelatorio;
	private String urlAcesso;
	private Integer sequenciaMenu;
	private String menu;
	
	/**
	 * @return the codigoPrograma
	 */
	public String getCodigoPrograma() {
		return codigoPrograma;
	}
	
	/**
	 * @param codigoPrograma the codigoPrograma to set
	 */
	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
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
	 * @return the linhasPagina
	 */
	public Integer getLinhasPagina() {
		return linhasPagina;
	}
	
	/**
	 * @param linhasPagina the linhasPagina to set
	 */
	public void setLinhasPagina(Integer linhasPagina) {
		this.linhasPagina = linhasPagina;
	}
	
	/**
	 * @return the objectCode
	 */
	public String getObjectCode() {
		return objectCode;
	}
	
	/**
	 * @param objectCode the objectCode to set
	 */
	public void setObjectCode(String objectCode) {
		this.objectCode = objectCode;
	}
	
	/**
	 * @return the segmento
	 */
	public Long getSegmento() {
		return segmento;
	}
	
	/**
	 * @param segmento the segmento to set
	 */
	public void setSegmento(Long segmento) {
		this.segmento = segmento;
	}
	
	/** Método getter para a propriedade sequenciaMenu
	 * 
	 *  @return Integer
	 *
	 */
	public Integer getSequenciaMenu() {
		return sequenciaMenu;
	}
	
	/** Método getter para a propriedade tipoRelatorio
	 * 
	 *  @return String
	 *
	 */
	public String getTipoRelatorio() {
		return tipoRelatorio;
	}
	
	/** Método getter para a propriedade urlAcesso
	 * 
	 *  @return String
	 *
	 */
	public String getUrlAcesso() {
		return urlAcesso;
	}
	
	/** Método setter para a propriedade sequenciaMenu
	 *
	 * @param sequenciaMenu 
	 *           <code>Integer</code> a ser designado para sequenciaMenu.
	 * 
	 */
	public void setSequenciaMenu(Integer sequenciaMenu) {
		this.sequenciaMenu = sequenciaMenu;
	}
	
	/** Método setter para a propriedade tipoRelatorio
	 *
	 * @param tipoRelatorio 
	 *           <code>String</code> a ser designado para tipoRelatorio.
	 * 
	 */
	public void setTipoRelatorio(String tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}
	
	/** Método setter para a propriedade urlAcesso
	 *
	 * @param urlAcesso 
	 *           <code>String</code> a ser designado para urlAcesso.
	 * 
	 */
	public void setUrlAcesso(String urlAcesso) {
		this.urlAcesso = urlAcesso;
	}

	/** Método getter para a propriedade menu
	 *
	 *  @return String de menu
	 */
	public String getMenu() {
		return menu;
	}

	/** Método setter para a propriedade menu
	 *
	 * @param menu String
	 */
	public void setMenu(String menu) {
		this.menu = menu;
	}		
}
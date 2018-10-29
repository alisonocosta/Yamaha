/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......Modelo.java
 *   .: Criação.....20 de março 2008, 16:00
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "Modelo".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "Modelo" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class Modelo extends EntityObject {
	
	//----[ Atributos de classe e instância ]-----------------------------------
	
	public static final String VELOCIMETRO_YES = "S";
	public static final String VELOCIMETRO_NOT = "N";
	
	public static final String QUADRICICLO = "QUADRICICLO";
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L; 
    
    /** Código do modelo */
    private String modelo;
    
    /** Novo número do ano */
    private Long ano;  
    
    /** Número antigo */
    private String velocimetro;
    
    /** Grupo Modelo */
    private String grupo;

    /** Garantia Diferenciada ? */
    private String garantiaDif;

	/** Método getter para a propriedade ano
	 *
	 *  @return Long de ano
	 */
	public Long getAno() {
		return ano;
	}

	/** Método setter para a propriedade ano
	 *
	 * @param ano Long
	 */
	public void setAno(Long ano) {
		this.ano = ano;
	}

	/** Método getter para a propriedade modelo
	 *
	 *  @return String de modelo
	 */
	public String getModelo() {
		return modelo;
	}

	/** Método setter para a propriedade modelo
	 *
	 * @param modelo String
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/** Método getter para a propriedade velocimetro
	 *
	 *  @return String de velocimetro
	 */
	public String getVelocimetro() {
		return velocimetro;
	}

	/** Método setter para a propriedade velocimetro
	 *
	 * @param velocimetro String
	 */
	public void setVelocimetro(String velocimetro) {
		this.velocimetro = velocimetro;
	}

	/**
	 * Método getter para a propriedade grupo
	 * @return  String de grupo
	 */
	public String getGrupo() {
		return grupo;
	}

	/**
	 * Método setter para a propriedade grupo
	 * @param grupo String
	 */
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	/**
	 * Método getter para a propriedade garantiaDif
	 * @return  String de garantiaDif
	 */
	public String getGarantiaDif() {
		return garantiaDif;
	}

	/**
	 * Método setter para a propriedade garantiaDif
	 * @param garantiaDif String
	 */
	public void setGarantiaDif(String garantiaDif) {
		this.garantiaDif = garantiaDif;
	}
    
}

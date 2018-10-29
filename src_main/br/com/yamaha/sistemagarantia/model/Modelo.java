/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......Modelo.java
 *   .: Cria��o.....20 de mar�o 2008, 16:00
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "Modelo".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "Modelo" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class Modelo extends EntityObject {
	
	//----[ Atributos de classe e inst�ncia ]-----------------------------------
	
	public static final String VELOCIMETRO_YES = "S";
	public static final String VELOCIMETRO_NOT = "N";
	
	public static final String QUADRICICLO = "QUADRICICLO";
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L; 
    
    /** C�digo do modelo */
    private String modelo;
    
    /** Novo n�mero do ano */
    private Long ano;  
    
    /** N�mero antigo */
    private String velocimetro;
    
    /** Grupo Modelo */
    private String grupo;

    /** Garantia Diferenciada ? */
    private String garantiaDif;

	/** M�todo getter para a propriedade ano
	 *
	 *  @return Long de ano
	 */
	public Long getAno() {
		return ano;
	}

	/** M�todo setter para a propriedade ano
	 *
	 * @param ano Long
	 */
	public void setAno(Long ano) {
		this.ano = ano;
	}

	/** M�todo getter para a propriedade modelo
	 *
	 *  @return String de modelo
	 */
	public String getModelo() {
		return modelo;
	}

	/** M�todo setter para a propriedade modelo
	 *
	 * @param modelo String
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/** M�todo getter para a propriedade velocimetro
	 *
	 *  @return String de velocimetro
	 */
	public String getVelocimetro() {
		return velocimetro;
	}

	/** M�todo setter para a propriedade velocimetro
	 *
	 * @param velocimetro String
	 */
	public void setVelocimetro(String velocimetro) {
		this.velocimetro = velocimetro;
	}

	/**
	 * M�todo getter para a propriedade grupo
	 * @return  String de grupo
	 */
	public String getGrupo() {
		return grupo;
	}

	/**
	 * M�todo setter para a propriedade grupo
	 * @param grupo String
	 */
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	/**
	 * M�todo getter para a propriedade garantiaDif
	 * @return  String de garantiaDif
	 */
	public String getGarantiaDif() {
		return garantiaDif;
	}

	/**
	 * M�todo setter para a propriedade garantiaDif
	 * @param garantiaDif String
	 */
	public void setGarantiaDif(String garantiaDif) {
		this.garantiaDif = garantiaDif;
	}
    
}

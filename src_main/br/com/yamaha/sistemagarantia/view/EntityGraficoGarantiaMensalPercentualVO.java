/* Resource Tecnologia
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......EntityGraficoGarantiaMensalPercentualVO.java
 *   .: Cria��o.....17 de outubro de 2008, 09:48
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "EntityGraficoGarantiaMensalPercentualVO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.math.BigDecimal;

import br.com.resource.infra.model.EntityObject;
import br.com.resource.infra.utils.NumberUtils;

/** Classe para armazenar valores para um relat�rio de Gr�fico de Grantias Mensal Percentual
 * 
 */
public class EntityGraficoGarantiaMensalPercentualVO extends EntityObject {

	//----[ Atributos de classe e inst�ncia ]---------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L; 
    
    /** Armazena o valor para uma serie. */
    private String serie;
    
    /** Armazena o valor para uma categoria. */
    private String category;
    
    /** Armazena o valor para uma modelo. */
    private String modelo;
    
    /** Armazena valor para uma s�rie do value. */
    private BigDecimal value;
    
    /**
     * Construtor padr�o
     */
    public EntityGraficoGarantiaMensalPercentualVO() {
    	
    	this.category = "";
    	this.serie    = "";
    	this.value    = BigDecimal.ZERO;
    	
    }
	
    //	----[ M�todos Getter ]---------------------------------------------------
	/** M�todo getter para a propriedade category
	 * 
	 *  @return String
	 *
	 */
	public String getCategory() {
		return category;
	}
	
	/** M�todo getter para a propriedade modelo
	 * 
	 *  @return String
	 *
	 */
	public String getModelo() {
		return modelo;
	}
	
	/** M�todo getter para a propriedade value
	 * 
	 *  @return BigDecimal
	 *
	 */
	public BigDecimal getValue() {
		return value;
	}
	
	/** M�todo getter para a propriedade value, retorna uma string formatada para moeda pt_BR
	 * 
	 *  @return String
	 *
	 */
	public String getStrValue() {
		return NumberUtils.formatNumberCurrency(value.doubleValue());
	}
	
	/** M�todo getter para a propriedade serie
	 * 
	 *  @return String
	 *
	 */
	public String getSerie() {
		return serie;
	}
	
	 //	----[ M�todos Setter ]---------------------------------------------------
	
	/** M�todo setter para a propriedade category
	 *
	 * @param category 
	 *           <code>String</code> a ser designado para category.
	 * 
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	
	/** M�todo setter para a propriedade modelo
	 *
	 * @param modelo 
	 *           <code>String</code> a ser designado para modelo.
	 * 
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}	

	/** M�todo setter para a propriedade value
	 *
	 * @param value 
	 *           <code>BigDecimal</code> a ser designado para value.
	 * 
	 */
	public void setValue(BigDecimal value) {
		this.value = value;
	}

	/** M�todo setter para a propriedade serie
	 *
	 * @param serie 
	 *           <code>String</code> a ser designado para serie.
	 * 
	 */
	public void setSerie(String serie) {
		this.serie = serie;
	}	
	
}

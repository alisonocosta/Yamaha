/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......EntityGraficosIndividuaisVO.java
 *   .: Cria��o.....21 de Novembro de 2007, 18:41
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "EntityGraficosIndividuaisVO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.math.BigDecimal;

import br.com.resource.infra.model.EntityObject;
import br.com.resource.infra.utils.NumberUtils;

/** Classe para armazenar valores para um relat�rio de Gr�ficos Individuais
 * 
 */
public class EntityGraficosIndividuaisVO extends EntityObject {

	//----[ Atributos de classe e inst�ncia ]---------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;    
    
    public static final String GRAPH_SERIE_GARANTIA = "GARANTIA";
    
    public static final String GRAPH_SERIE_GARANTIA_ZERO = "GARANTIAZERO";
	
    public static final String GRAPH_SERIE_FATURAMENTO = "FATURAMENTO";
    
    /** Armazena o valor para uma serie. */
    private String serie;
    
    /** Armazena o valor para uma categoria. */
    private String category;
    
    /** Armazena o valor para uma modelo. */
    private String modelo;
    
    /** Armazena valor para uma s�rie do value. */
    private BigDecimal value;
    
    /** Armazena valor para uma s�rie do gr�fico. */
    private BigDecimal firstValue;
    
    /** Armazena valor para uam segunda s�rie do gr�fico. */
    private BigDecimal secondValue;	
	
    //	----[ M�todos Getter ]---------------------------------------------------
	/** M�todo getter para a propriedade category
	 * 
	 *  @return String
	 *
	 */
	public String getCategory() {
		return category;
	}

	/** M�todo getter para a propriedade firstValue
	 * 
	 *  @return BigDecimal
	 *
	 */
	public BigDecimal getFirstValue() {
		return firstValue;
	}

	/** M�todo getter para a propriedade secondValue
	 * 
	 *  @return BigDecimal
	 *
	 */
	public BigDecimal getSecondValue() {
		return secondValue;
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

	/** M�todo setter para a propriedade firstValue
	 *
	 * @param firstValue 
	 *           <code>BigDecimal</code> a ser designado para firstValue.
	 * 
	 */
	public void setFirstValue(BigDecimal firstValue) {
		this.firstValue = firstValue;
	}

	/** M�todo setter para a propriedade secondValue
	 *
	 * @param secondValue 
	 *           <code>BigDecimal</code> a ser designado para secondValue.
	 * 
	 */
	public void setSecondValue(BigDecimal secondValue) {
		this.secondValue = secondValue;
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

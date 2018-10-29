/* Resource Tecnologia
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......EntityGraficoGarantiaMensalPercentualVO.java
 *   .: Criação.....17 de outubro de 2008, 09:48
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "EntityGraficoGarantiaMensalPercentualVO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.math.BigDecimal;

import br.com.resource.infra.model.EntityObject;
import br.com.resource.infra.utils.NumberUtils;

/** Classe para armazenar valores para um relatório de Gráfico de Grantias Mensal Percentual
 * 
 */
public class EntityGraficoGarantiaMensalPercentualVO extends EntityObject {

	//----[ Atributos de classe e instância ]---------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L; 
    
    /** Armazena o valor para uma serie. */
    private String serie;
    
    /** Armazena o valor para uma categoria. */
    private String category;
    
    /** Armazena o valor para uma modelo. */
    private String modelo;
    
    /** Armazena valor para uma série do value. */
    private BigDecimal value;
    
    /**
     * Construtor padrão
     */
    public EntityGraficoGarantiaMensalPercentualVO() {
    	
    	this.category = "";
    	this.serie    = "";
    	this.value    = BigDecimal.ZERO;
    	
    }
	
    //	----[ Métodos Getter ]---------------------------------------------------
	/** Método getter para a propriedade category
	 * 
	 *  @return String
	 *
	 */
	public String getCategory() {
		return category;
	}
	
	/** Método getter para a propriedade modelo
	 * 
	 *  @return String
	 *
	 */
	public String getModelo() {
		return modelo;
	}
	
	/** Método getter para a propriedade value
	 * 
	 *  @return BigDecimal
	 *
	 */
	public BigDecimal getValue() {
		return value;
	}
	
	/** Método getter para a propriedade value, retorna uma string formatada para moeda pt_BR
	 * 
	 *  @return String
	 *
	 */
	public String getStrValue() {
		return NumberUtils.formatNumberCurrency(value.doubleValue());
	}
	
	/** Método getter para a propriedade serie
	 * 
	 *  @return String
	 *
	 */
	public String getSerie() {
		return serie;
	}
	
	 //	----[ Métodos Setter ]---------------------------------------------------
	
	/** Método setter para a propriedade category
	 *
	 * @param category 
	 *           <code>String</code> a ser designado para category.
	 * 
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	
	/** Método setter para a propriedade modelo
	 *
	 * @param modelo 
	 *           <code>String</code> a ser designado para modelo.
	 * 
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}	

	/** Método setter para a propriedade value
	 *
	 * @param value 
	 *           <code>BigDecimal</code> a ser designado para value.
	 * 
	 */
	public void setValue(BigDecimal value) {
		this.value = value;
	}

	/** Método setter para a propriedade serie
	 *
	 * @param serie 
	 *           <code>String</code> a ser designado para serie.
	 * 
	 */
	public void setSerie(String serie) {
		this.serie = serie;
	}	
	
}

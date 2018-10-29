/* Resource Tecnologia
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......EntityGraficoGarantiaPagaVO.java
 *   .: Criação.....12 de março de 2008, 13:09
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "EntityGraficoGarantiaPagaVO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.math.BigDecimal;

import br.com.resource.infra.model.EntityObject;

/** Classe para armazenar valores para o relatório Gráfico de Garantia Paga por modelo de Produto
 * 
 */
public class EntityGraficoGarantiaPagaVO extends EntityObject {

	//----[ Atributos de classe e instância ]---------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;    
	
    /** Constante que representa a informação para gráfico de valores acumulado */
    public static final String ACUMULADO = "AC";
    
    /** Constante que representa a informação para gráfico de valores do período */
    public static final String PERIODO = "PE";
    
    /** Constante que representa a informação para gráfico de valores por quantidade */
    public static final String QTDE = "QT";
    
    /** Armazena o valor para o modelo. */
    private String modelo;
    
    /** Armazena valor para o valor. */
    private BigDecimal value;
	
    //	----[ Métodos Getter ]---------------------------------------------------
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
	
	 //	----[ Métodos Setter ]---------------------------------------------------
	
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
	
	
}

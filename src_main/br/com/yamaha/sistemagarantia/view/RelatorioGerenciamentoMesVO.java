/* Resource Tecnologia
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioGerenciamentoMesVO.java
 *   .: Cria��o.....21 de outubro, 16:07
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "RelatorioGerenciamentoMesVO".
 */
package br.com.yamaha.sistemagarantia.view;

import br.com.resource.infra.model.EntityObject;
import br.com.resource.infra.utils.NumberUtils;

public class RelatorioGerenciamentoMesVO extends EntityObject {

	//----[ Atributos de classe e inst�ncia ]---------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;  
    
    /** Armazena o campo modelo */
    private String modelo;
    
    /** Armazena o a quantidade de produtos faturados para o modelo */
    private Long qtModelo;
    
    /** Armazena o nome da empresa  para o modelo */
    private String nomeEmpresa;
    
    /** Armazena o a quantidade de produtos em estoque na concession�ria */
    private Long qtEstoque;
    
    /** Armazena a quantidade de revis�es de entrega para o modelo */
    private Long qtR1;
    
    /** Armazena a quantidade de revis�es 2 para o modelo */
    private Long qtR2;
    
    /** Armazena a somat�ria dos valores das revis�es 2 para o modelo */
    private Double vlR2;
    
    /** Armazena a quantidade de revis�es 3 para o modelo */
    private Long qtR3;
    
    /** Armazena a somat�ria dos valores das revis�es 3 para o modelo */
    private Double vlR3;
    
    /** Armazena a quantidade de revis�es 4 para o modelo */
    private Long qtR4;
    
    /** Armazena a quantidade de revis�es 5 para o modelo */
    private Long qtR5;
    
    /** Armazena a quantidade de revis�es 6 para o modelo */
    private Long qtR6;
    
    /** Armazena a quantidade de revis�es 7 para o modelo */
    private Long qtR7;
    
    /** Armazena a quantidade de revis�es 8 para o modelo */
    private Long qtR8;
    
    /** Armazena a quantidade de revis�es 9 para o modelo */
    private Long qtR9;
    
    /** Armazena a quantidade de revis�es 10 para o modelo */
    private Long qtR10;
    
    /** Armazena a quantidade de Solicita��es de Garantia para o modelo */
    private Long qtSG;
    
    /** Armazena a somat�ria dos valores das SG�s para o modelo */
    private Double vlSG;
    
    /** Armazena a quantidade de Solicita��es de Garantia Recusadas para o modelo */
    private Long qtSGRec;
    
    /** Armazena a somat�ria dos valores das SG�s Recusadas para o modelo */
    private Double vlSGRec;
    
    /** Armazena a quantidade de Revis�es Recusadas para o modelo */
    private Long qtRevRec;
    
    /** Armazena a somat�ria dos valores das Revis�es Recusadas para o modelo */
    private Double vlRevRec;

  //----[ M�todos getter e setter para as propriedades ]---------------
    
	/**
	 * M�todo getter para a propriedade modelo
	 * @return  String de modelo
	 */
	public String getModelo() {
		return modelo;
	}

	/**
	 * M�todo setter para a propriedade modelo
	 * @param modelo String
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/**
	 * M�todo getter para a propriedade qtModelo
	 * @return  Long de qtModelo
	 */
	public Long getQtModelo() {
		return qtModelo;
	}

	/**
	 * M�todo setter para a propriedade qtModelo
	 * @param qtModelo Long
	 */
	public void setQtModelo(Long qtModelo) {
		this.qtModelo = qtModelo;
	}

	/**
	 * M�todo getter para a propriedade qtEstoque
	 * @return  Long de qtEstoque
	 */
	public Long getQtEstoque() {
		return qtEstoque;
	}

	/**
	 * M�todo setter para a propriedade qtEstoque
	 * @param qtEstoque Long
	 */
	public void setQtEstoque(Long qtEstoque) {
		this.qtEstoque = qtEstoque;
	}

	/**
	 * M�todo getter para a propriedade qtR1
	 * @return  Long de qtR1
	 */
	public Long getQtR1() {
		return qtR1;
	}

	/**
	 * M�todo setter para a propriedade qtR1
	 * @param qtR1 Long
	 */
	public void setQtR1(Long qtR1) {
		this.qtR1 = qtR1;
	}

	/**
	 * M�todo getter para a propriedade qtR2
	 * @return  Long de qtR2
	 */
	public Long getQtR2() {
		return qtR2;
	}

	/**
	 * M�todo setter para a propriedade qtR2
	 * @param qtR2 Long
	 */
	public void setQtR2(Long qtR2) {
		this.qtR2 = qtR2;
	}

	/**
	 * M�todo getter para a propriedade vlR2
	 * @return  Double de vlR2
	 */
	public Double getVlR2() {
		return vlR2;
	}

	/**
	 * M�todo setter para a propriedade vlR2
	 * @param vlR2 Double
	 */
	public void setVlR2(Double vlR2) {
		this.vlR2 = vlR2;
	}
	
	/**
	 * M�todo para retornar o valor de revis�o formatado para moeda
	 * @return String
	 */
	public String getVlR2Str() {
		
		return NumberUtils.formatNumberCurrencyMil(this.vlR2.doubleValue());		
		
	}

	/**
	 * M�todo getter para a propriedade qtR3
	 * @return  Long de qtR3
	 */
	public Long getQtR3() {
		return qtR3;
	}

	/**
	 * M�todo setter para a propriedade qtR3
	 * @param qtR3 Long
	 */
	public void setQtR3(Long qtR3) {
		this.qtR3 = qtR3;
	}

	/**
	 * M�todo getter para a propriedade vlR3
	 * @return  Double de vlR3
	 */
	public Double getVlR3() {
		return vlR3;
	}
	
	/**
	 * M�todo para retornar o valor de revis�o 3 formatado para moeda
	 * @return String
	 */
	public String getVlR3Str() {
		
		return NumberUtils.formatNumberCurrencyMil(this.vlR3.doubleValue());		
		
	}

	/**
	 * M�todo setter para a propriedade vlR3
	 * @param vlR3 Double
	 */
	public void setVlR3(Double vlR3) {
		this.vlR3 = vlR3;
	}

	/**
	 * M�todo getter para a propriedade qtR4
	 * @return  Long de qtR4
	 */
	public Long getQtR4() {
		return qtR4;
	}

	/**
	 * M�todo setter para a propriedade qtR4
	 * @param qtR4 Long
	 */
	public void setQtR4(Long qtR4) {
		this.qtR4 = qtR4;
	}

	/**
	 * M�todo getter para a propriedade qtR5
	 * @return  Long de qtR5
	 */
	public Long getQtR5() {
		return qtR5;
	}

	/**
	 * M�todo setter para a propriedade qtR5
	 * @param qtR5 Long
	 */
	public void setQtR5(Long qtR5) {
		this.qtR5 = qtR5;
	}

	/**
	 * M�todo getter para a propriedade qtR6
	 * @return  Long de qtR6
	 */
	public Long getQtR6() {
		return qtR6;
	}

	/**
	 * M�todo setter para a propriedade qtR6
	 * @param qtR6 Long
	 */
	public void setQtR6(Long qtR6) {
		this.qtR6 = qtR6;
	}

	/**
	 * M�todo getter para a propriedade qtR7
	 * @return  Long de qtR7
	 */
	public Long getQtR7() {
		return qtR7;
	}

	/**
	 * M�todo setter para a propriedade qtR7
	 * @param qtR7 Long
	 */
	public void setQtR7(Long qtR7) {
		this.qtR7 = qtR7;
	}

	/**
	 * M�todo getter para a propriedade qtR8
	 * @return  Long de qtR8
	 */
	public Long getQtR8() {
		return qtR8;
	}

	/**
	 * M�todo setter para a propriedade qtR8
	 * @param qtR8 Long
	 */
	public void setQtR8(Long qtR8) {
		this.qtR8 = qtR8;
	}

	/**
	 * M�todo getter para a propriedade qtR9
	 * @return  Long de qtR9
	 */
	public Long getQtR9() {
		return qtR9;
	}

	/**
	 * M�todo setter para a propriedade qtR9
	 * @param qtR9 Long
	 */
	public void setQtR9(Long qtR9) {
		this.qtR9 = qtR9;
	}

	/**
	 * M�todo getter para a propriedade qtR10
	 * @return  Long de qtR10
	 */
	public Long getQtR10() {
		return qtR10;
	}

	/**
	 * M�todo setter para a propriedade qtR10
	 * @param qtR10 Long
	 */
	public void setQtR10(Long qtR10) {
		this.qtR10 = qtR10;
	}

	/**
	 * M�todo getter para a propriedade qtSG
	 * @return  Long de qtSG
	 */
	public Long getQtSG() {
		return qtSG;
	}

	/**
	 * M�todo setter para a propriedade qtSG
	 * @param qtSG Long
	 */
	public void setQtSG(Long qtSG) {
		this.qtSG = qtSG;
	}

	/**
	 * M�todo getter para a propriedade vlSG
	 * @return  Double de vlSG
	 */
	public Double getVlSG() {
		return vlSG;
	}

	/**
	 * M�todo setter para a propriedade vlSG
	 * @param vlSG Double
	 */
	public void setVlSG(Double vlSG) {
		this.vlSG = vlSG;
	}
	
	/**
	 * M�todo para retornar o valor de garantias formatado para moeda
	 * @return String
	 */
	public String getVlSGStr() {
		
		return NumberUtils.formatNumberCurrencyMil(this.vlSG.doubleValue());		
		
	}

	/**
	 * M�todo getter para a propriedade qtSGRec
	 * @return  Long de qtSGRec
	 */
	public Long getQtSGRec() {
		return qtSGRec;
	}

	/**
	 * M�todo setter para a propriedade qtSGRec
	 * @param qtSGRec Long
	 */
	public void setQtSGRec(Long qtSGRec) {
		this.qtSGRec = qtSGRec;
	}

	/**
	 * M�todo getter para a propriedade vlSGRec
	 * @return  Double de vlSGRec
	 */
	public Double getVlSGRec() {
		return vlSGRec;
	}
	
	/**
	 * M�todo para retornar o valor de garantias recusadas formatado para moeda
	 * @return String
	 */
	public String getVlSGRecStr() {
		
		return NumberUtils.formatNumberCurrencyMil(this.vlSGRec.doubleValue());		
		
	}

	/**
	 * M�todo setter para a propriedade vlSGRec
	 * @param vlSGRec Double
	 */
	public void setVlSGRec(Double vlSGRec) {
		this.vlSGRec = vlSGRec;
	}

	/**
	 * M�todo getter para a propriedade qtRevRec
	 * @return  Long de qtRevRec
	 */
	public Long getQtRevRec() {
		return qtRevRec;
	}

	/**
	 * M�todo setter para a propriedade qtRevRec
	 * @param qtRevRec Long
	 */
	public void setQtRevRec(Long qtRevRec) {
		this.qtRevRec = qtRevRec;
	}

	/**
	 * M�todo getter para a propriedade vlRevRec
	 * @return  Double de vlRevRec
	 */
	public Double getVlRevRec() {
		return vlRevRec;
	}
	
	/**
	 * M�todo para retornar o valor de revis�o recusadas formatado para moeda
	 * @return String
	 */
	public String getVlRevRecStr() {
		
		return NumberUtils.formatNumberCurrencyMil(this.vlRevRec.doubleValue());		
		
	}

	/**
	 * M�todo setter para a propriedade vlRevRec
	 * @param vlRevRec Double
	 */
	public void setVlRevRec(Double vlRevRec) {
		this.vlRevRec = vlRevRec;
	}

	/**
	 * M�todo getter para a propriedade nomeEmpresa
	 * @return  String de nomeEmpresa
	 */
	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	/**
	 * M�todo setter para a propriedade nomeEmpresa
	 * @param nomeEmpresa String
	 */
	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}
	
}

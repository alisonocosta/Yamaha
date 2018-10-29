/* Resource Tecnologia
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioGerenciamentoMesVO.java
 *   .: Criação.....21 de outubro, 16:07
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "RelatorioGerenciamentoMesVO".
 */
package br.com.yamaha.sistemagarantia.view;

import br.com.resource.infra.model.EntityObject;
import br.com.resource.infra.utils.NumberUtils;

public class RelatorioGerenciamentoMesVO extends EntityObject {

	//----[ Atributos de classe e instância ]---------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;  
    
    /** Armazena o campo modelo */
    private String modelo;
    
    /** Armazena o a quantidade de produtos faturados para o modelo */
    private Long qtModelo;
    
    /** Armazena o nome da empresa  para o modelo */
    private String nomeEmpresa;
    
    /** Armazena o a quantidade de produtos em estoque na concessionária */
    private Long qtEstoque;
    
    /** Armazena a quantidade de revisões de entrega para o modelo */
    private Long qtR1;
    
    /** Armazena a quantidade de revisões 2 para o modelo */
    private Long qtR2;
    
    /** Armazena a somatória dos valores das revisões 2 para o modelo */
    private Double vlR2;
    
    /** Armazena a quantidade de revisões 3 para o modelo */
    private Long qtR3;
    
    /** Armazena a somatória dos valores das revisões 3 para o modelo */
    private Double vlR3;
    
    /** Armazena a quantidade de revisões 4 para o modelo */
    private Long qtR4;
    
    /** Armazena a quantidade de revisões 5 para o modelo */
    private Long qtR5;
    
    /** Armazena a quantidade de revisões 6 para o modelo */
    private Long qtR6;
    
    /** Armazena a quantidade de revisões 7 para o modelo */
    private Long qtR7;
    
    /** Armazena a quantidade de revisões 8 para o modelo */
    private Long qtR8;
    
    /** Armazena a quantidade de revisões 9 para o modelo */
    private Long qtR9;
    
    /** Armazena a quantidade de revisões 10 para o modelo */
    private Long qtR10;
    
    /** Armazena a quantidade de Solicitações de Garantia para o modelo */
    private Long qtSG;
    
    /** Armazena a somatória dos valores das SG´s para o modelo */
    private Double vlSG;
    
    /** Armazena a quantidade de Solicitações de Garantia Recusadas para o modelo */
    private Long qtSGRec;
    
    /** Armazena a somatória dos valores das SG´s Recusadas para o modelo */
    private Double vlSGRec;
    
    /** Armazena a quantidade de Revisões Recusadas para o modelo */
    private Long qtRevRec;
    
    /** Armazena a somatória dos valores das Revisões Recusadas para o modelo */
    private Double vlRevRec;

  //----[ Métodos getter e setter para as propriedades ]---------------
    
	/**
	 * Método getter para a propriedade modelo
	 * @return  String de modelo
	 */
	public String getModelo() {
		return modelo;
	}

	/**
	 * Método setter para a propriedade modelo
	 * @param modelo String
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/**
	 * Método getter para a propriedade qtModelo
	 * @return  Long de qtModelo
	 */
	public Long getQtModelo() {
		return qtModelo;
	}

	/**
	 * Método setter para a propriedade qtModelo
	 * @param qtModelo Long
	 */
	public void setQtModelo(Long qtModelo) {
		this.qtModelo = qtModelo;
	}

	/**
	 * Método getter para a propriedade qtEstoque
	 * @return  Long de qtEstoque
	 */
	public Long getQtEstoque() {
		return qtEstoque;
	}

	/**
	 * Método setter para a propriedade qtEstoque
	 * @param qtEstoque Long
	 */
	public void setQtEstoque(Long qtEstoque) {
		this.qtEstoque = qtEstoque;
	}

	/**
	 * Método getter para a propriedade qtR1
	 * @return  Long de qtR1
	 */
	public Long getQtR1() {
		return qtR1;
	}

	/**
	 * Método setter para a propriedade qtR1
	 * @param qtR1 Long
	 */
	public void setQtR1(Long qtR1) {
		this.qtR1 = qtR1;
	}

	/**
	 * Método getter para a propriedade qtR2
	 * @return  Long de qtR2
	 */
	public Long getQtR2() {
		return qtR2;
	}

	/**
	 * Método setter para a propriedade qtR2
	 * @param qtR2 Long
	 */
	public void setQtR2(Long qtR2) {
		this.qtR2 = qtR2;
	}

	/**
	 * Método getter para a propriedade vlR2
	 * @return  Double de vlR2
	 */
	public Double getVlR2() {
		return vlR2;
	}

	/**
	 * Método setter para a propriedade vlR2
	 * @param vlR2 Double
	 */
	public void setVlR2(Double vlR2) {
		this.vlR2 = vlR2;
	}
	
	/**
	 * Método para retornar o valor de revisão formatado para moeda
	 * @return String
	 */
	public String getVlR2Str() {
		
		return NumberUtils.formatNumberCurrencyMil(this.vlR2.doubleValue());		
		
	}

	/**
	 * Método getter para a propriedade qtR3
	 * @return  Long de qtR3
	 */
	public Long getQtR3() {
		return qtR3;
	}

	/**
	 * Método setter para a propriedade qtR3
	 * @param qtR3 Long
	 */
	public void setQtR3(Long qtR3) {
		this.qtR3 = qtR3;
	}

	/**
	 * Método getter para a propriedade vlR3
	 * @return  Double de vlR3
	 */
	public Double getVlR3() {
		return vlR3;
	}
	
	/**
	 * Método para retornar o valor de revisão 3 formatado para moeda
	 * @return String
	 */
	public String getVlR3Str() {
		
		return NumberUtils.formatNumberCurrencyMil(this.vlR3.doubleValue());		
		
	}

	/**
	 * Método setter para a propriedade vlR3
	 * @param vlR3 Double
	 */
	public void setVlR3(Double vlR3) {
		this.vlR3 = vlR3;
	}

	/**
	 * Método getter para a propriedade qtR4
	 * @return  Long de qtR4
	 */
	public Long getQtR4() {
		return qtR4;
	}

	/**
	 * Método setter para a propriedade qtR4
	 * @param qtR4 Long
	 */
	public void setQtR4(Long qtR4) {
		this.qtR4 = qtR4;
	}

	/**
	 * Método getter para a propriedade qtR5
	 * @return  Long de qtR5
	 */
	public Long getQtR5() {
		return qtR5;
	}

	/**
	 * Método setter para a propriedade qtR5
	 * @param qtR5 Long
	 */
	public void setQtR5(Long qtR5) {
		this.qtR5 = qtR5;
	}

	/**
	 * Método getter para a propriedade qtR6
	 * @return  Long de qtR6
	 */
	public Long getQtR6() {
		return qtR6;
	}

	/**
	 * Método setter para a propriedade qtR6
	 * @param qtR6 Long
	 */
	public void setQtR6(Long qtR6) {
		this.qtR6 = qtR6;
	}

	/**
	 * Método getter para a propriedade qtR7
	 * @return  Long de qtR7
	 */
	public Long getQtR7() {
		return qtR7;
	}

	/**
	 * Método setter para a propriedade qtR7
	 * @param qtR7 Long
	 */
	public void setQtR7(Long qtR7) {
		this.qtR7 = qtR7;
	}

	/**
	 * Método getter para a propriedade qtR8
	 * @return  Long de qtR8
	 */
	public Long getQtR8() {
		return qtR8;
	}

	/**
	 * Método setter para a propriedade qtR8
	 * @param qtR8 Long
	 */
	public void setQtR8(Long qtR8) {
		this.qtR8 = qtR8;
	}

	/**
	 * Método getter para a propriedade qtR9
	 * @return  Long de qtR9
	 */
	public Long getQtR9() {
		return qtR9;
	}

	/**
	 * Método setter para a propriedade qtR9
	 * @param qtR9 Long
	 */
	public void setQtR9(Long qtR9) {
		this.qtR9 = qtR9;
	}

	/**
	 * Método getter para a propriedade qtR10
	 * @return  Long de qtR10
	 */
	public Long getQtR10() {
		return qtR10;
	}

	/**
	 * Método setter para a propriedade qtR10
	 * @param qtR10 Long
	 */
	public void setQtR10(Long qtR10) {
		this.qtR10 = qtR10;
	}

	/**
	 * Método getter para a propriedade qtSG
	 * @return  Long de qtSG
	 */
	public Long getQtSG() {
		return qtSG;
	}

	/**
	 * Método setter para a propriedade qtSG
	 * @param qtSG Long
	 */
	public void setQtSG(Long qtSG) {
		this.qtSG = qtSG;
	}

	/**
	 * Método getter para a propriedade vlSG
	 * @return  Double de vlSG
	 */
	public Double getVlSG() {
		return vlSG;
	}

	/**
	 * Método setter para a propriedade vlSG
	 * @param vlSG Double
	 */
	public void setVlSG(Double vlSG) {
		this.vlSG = vlSG;
	}
	
	/**
	 * Método para retornar o valor de garantias formatado para moeda
	 * @return String
	 */
	public String getVlSGStr() {
		
		return NumberUtils.formatNumberCurrencyMil(this.vlSG.doubleValue());		
		
	}

	/**
	 * Método getter para a propriedade qtSGRec
	 * @return  Long de qtSGRec
	 */
	public Long getQtSGRec() {
		return qtSGRec;
	}

	/**
	 * Método setter para a propriedade qtSGRec
	 * @param qtSGRec Long
	 */
	public void setQtSGRec(Long qtSGRec) {
		this.qtSGRec = qtSGRec;
	}

	/**
	 * Método getter para a propriedade vlSGRec
	 * @return  Double de vlSGRec
	 */
	public Double getVlSGRec() {
		return vlSGRec;
	}
	
	/**
	 * Método para retornar o valor de garantias recusadas formatado para moeda
	 * @return String
	 */
	public String getVlSGRecStr() {
		
		return NumberUtils.formatNumberCurrencyMil(this.vlSGRec.doubleValue());		
		
	}

	/**
	 * Método setter para a propriedade vlSGRec
	 * @param vlSGRec Double
	 */
	public void setVlSGRec(Double vlSGRec) {
		this.vlSGRec = vlSGRec;
	}

	/**
	 * Método getter para a propriedade qtRevRec
	 * @return  Long de qtRevRec
	 */
	public Long getQtRevRec() {
		return qtRevRec;
	}

	/**
	 * Método setter para a propriedade qtRevRec
	 * @param qtRevRec Long
	 */
	public void setQtRevRec(Long qtRevRec) {
		this.qtRevRec = qtRevRec;
	}

	/**
	 * Método getter para a propriedade vlRevRec
	 * @return  Double de vlRevRec
	 */
	public Double getVlRevRec() {
		return vlRevRec;
	}
	
	/**
	 * Método para retornar o valor de revisão recusadas formatado para moeda
	 * @return String
	 */
	public String getVlRevRecStr() {
		
		return NumberUtils.formatNumberCurrencyMil(this.vlRevRec.doubleValue());		
		
	}

	/**
	 * Método setter para a propriedade vlRevRec
	 * @param vlRevRec Double
	 */
	public void setVlRevRec(Double vlRevRec) {
		this.vlRevRec = vlRevRec;
	}

	/**
	 * Método getter para a propriedade nomeEmpresa
	 * @return  String de nomeEmpresa
	 */
	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	/**
	 * Método setter para a propriedade nomeEmpresa
	 * @param nomeEmpresa String
	 */
	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}
	
}

/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioMensalModeloVO.java
 *   .: Criação.....27 de julho, 11:06
 *   .: Autor.......Gisele Panosso
 *   .: Descrição...Entidade "RelatorioMensalModeloVO".
 */

package br.com.yamaha.sistemagarantia.view;

import br.com.resource.infra.model.EntityObject;

public class RelatorioMensalModeloVO extends EntityObject {
	
	//----[ Atributos de classe e instância ]---------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L; 	
    
    /** Armazena o campo linha do Relatório Mensal por Modelo. */
    private String linha; 
    
    /** Armazena o campo modelo do Relatório Mensal por Modelo. */
    private String modelo;    

    /** Armazena o campo qtdMoto0Km do Relatório Mensal por Modelo. */
    private Double qtMoto0Km;
    
    /** Armazena o campo qtMotoPecaFaltante do Relatório Mensal por Modelo. */
    private Double qtMotorPecaFaltante;
    
    /** Armazena o campo qtPecaFaltante do Relatório Mensal por Modelo. */
    private Double qtPecaFaltante;
    
    /** Armazena o campo qtSolicitacao (Solicitações) do Relatório Mensal por Modelo. */
    private Double qtSolicitacao;
    
    /** Armazena o campo totalMotoFaturada do Relatório Mensal por Modelo. */
    private Double totalMotoFaturada;
    
    /** Armazena o campo percCadatrado do Relatório Mensal por Modelo. */
    private Double percCadatrado;
    
    /** Armazena o campo qtRevisao1 do Relatório Mensal por Modelo. */
    private Double qtRevisao1;
    
    /** Armazena o campo vlRevisao1 do Relatório Mensal por Modelo. */
    private Double vlRevisao1;
    
    /** Armazena o campo qtRevisao2 do Relatório Mensal por Modelo. */
    private Double qtRevisao2;
    
    /** Armazena o campo vlRevisao2 do Relatório Mensal por Modelo. */
    private Double vlRevisao2;
    
    /** Armazena o campo qtRevisao3 do Relatório Mensal por Modelo. */
    private Double qtRevisao3;
    
    /** Armazena o campo vlRevisao3 do Relatório Mensal por Modelo. */
    private Double vlRevisao3;

    //	----[ Métodos Getter ]---------------------------------------------------
    
	/** Método getter para a propriedade modelo.
	 *
	 *  @return o valor atual de modelo.
	 */
	public String getModelo() {
		return modelo;
	}

	/** Método getter para a propriedade percCadatrado.
	 *
	 *  @return o valor atual de percCadatrado.
	 */
	public Double getPercCadatrado() {
		return percCadatrado;
	}

	/** Método getter para a propriedade qtMoto0Km.
	 *
	 *  @return o valor atual de qtMoto0Km.
	 */
	public Double getQtMoto0Km() {
		return qtMoto0Km;
	}

	/** Método getter para a propriedade qtMotoPecaFaltante.
	 *
	 *  @return o valor atual de qtMotoPecaFaltante.
	 */
	public Double getQtMotorPecaFaltante() {
		return qtMotorPecaFaltante;
	}

	/** Método getter para a propriedade qtSolicitacao.
	 *
	 *  @return o valor atual de qtSolicitacao.
	 */
	public Double getQtSolicitacao() {
		return qtSolicitacao;
	}

	/** Método getter para a propriedade qtPecaFaltante.
	 *
	 *  @return o valor atual de qtPecaFaltante.
	 */
	public Double getQtPecaFaltante() {
		return qtPecaFaltante;
	}

	/** Método getter para a propriedade qtRevisao1.
	 *
	 *  @return o valor atual de qtRevisao1.
	 */
	public Double getQtRevisao1() {
		return qtRevisao1;
	}

	/** Método getter para a propriedade qtRevisao2.
	 *
	 *  @return o valor atual de qtRevisao2.
	 */
	public Double getQtRevisao2() {
		return qtRevisao2;
	}

	/** Método getter para a propriedade qtRevisao3.
	 *
	 *  @return o valor atual de qtRevisao3.
	 */
	public Double getQtRevisao3() {
		return qtRevisao3;
	}

	/** Método getter para a propriedade totalMotoFaturada.
	 *
	 *  @return o valor atual de totalMotoFaturada.
	 */
	public Double getTotalMotoFaturada() {
		return totalMotoFaturada;
	}

	/** Método getter para a propriedade vlRevisao1.
	 *
	 *  @return o valor atual de vlRevisao1.
	 */
	public Double getVlRevisao1() {
		return vlRevisao1;
	}

	/** Método getter para a propriedade vlRevisao2.
	 *
	 *  @return o valor atual de vlRevisao2.
	 */
	public Double getVlRevisao2() {
		return vlRevisao2;
	}

	/** Método getter para a propriedade vlRevisao3.
	 *
	 *  @return o valor atual de vlRevisao3.
	 */
	public Double getVlRevisao3() {
		return vlRevisao3;
	}


	//	----[ Métodos Setter ]---------------------------------------------------
	
	
	/** Obtém o valur atual de modelo.
	 * 
	 *  @param modelo 
	 *    O novo valor para modelo.
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/** Obtém o valur atual de percCadatrado.
	 * 
	 *  @param percCadatrado 
	 *    O novo valor para percCadatrado.
	 */
	public void setPercCadatrado(Double percCadatrado) {
		this.percCadatrado = percCadatrado;
	}

	/** Obtém o valur atual de qtMoto0Km.
	 * 
	 *  @param qtMoto0Km 
	 *    O novo valor para qtMoto0Km.
	 */
	public void setQtMoto0Km(Double qtMoto0Km) {
		this.qtMoto0Km = qtMoto0Km;
	}

	/** Obtém o valur atual de qtMotoPecaFaltante.
	 * 
	 *  @param qtMotoPecaFaltante 
	 *    O novo valor para qtMotoPecaFaltante.
	 */
	public void setQtMotorPecaFaltante(Double qtMotorPecaFaltante) {
		this.qtMotorPecaFaltante = qtMotorPecaFaltante;
	}

	/** Obtém o valur atual de qtSolicitacao.
	 * 
	 *  @param qtSolicitacao 
	 *    O novo valor para qtSolicitacao.
	 */
	public void setQtSolicitacao(Double qtSolicitacao) {
		this.qtSolicitacao = qtSolicitacao;
	}

	/** Obtém o valur atual de qtPecaFaltante.
	 * 
	 *  @param qtPecaFaltante 
	 *    O novo valor para qtPecaFaltante.
	 */
	public void setQtPecaFaltante(Double qtPecaFaltante) {
		this.qtPecaFaltante = qtPecaFaltante;
	}

	/** Obtém o valur atual de qtRevisao1.
	 * 
	 *  @param qtRevisao1 
	 *    O novo valor para qtRevisao1.
	 */
	public void setQtRevisao1(Double qtRevisao1) {
		this.qtRevisao1 = qtRevisao1;
	}

	/** Obtém o valur atual de qtRevisao2.
	 * 
	 *  @param qtRevisao2 
	 *    O novo valor para qtRevisao2.
	 */
	public void setQtRevisao2(Double qtRevisao2) {
		this.qtRevisao2 = qtRevisao2;
	}

	/** Obtém o valur atual de qtRevisao3.
	 * 
	 *  @param qtRevisao3 
	 *    O novo valor para qtRevisao3.
	 */
	public void setQtRevisao3(Double qtRevisao3) {
		this.qtRevisao3 = qtRevisao3;
	}

	/** Obtém o valur atual de totalMotoFaturada.
	 * 
	 *  @param totalMotoFaturada 
	 *    O novo valor para totalMotoFaturada.
	 */
	public void setTotalMotoFaturada(Double totalMotoFaturada) {
		this.totalMotoFaturada = totalMotoFaturada;
	}

	/** Obtém o valur atual de vlRevisao1.
	 * 
	 *  @param vlRevisao1 
	 *    O novo valor para vlRevisao1.
	 */
	public void setVlRevisao1(Double vlRevisao1) {
		this.vlRevisao1 = vlRevisao1;
	}

	/** Obtém o valur atual de vlRevisao2.
	 * 
	 *  @param vlRevisao2 
	 *    O novo valor para vlRevisao2.
	 */
	public void setVlRevisao2(Double vlRevisao2) {
		this.vlRevisao2 = vlRevisao2;
	}

	/** Obtém o valur atual de vlRevisao3.
	 * 
	 *  @param vlRevisao3 
	 *    O novo valor para vlRevisao3.
	 */
	public void setVlRevisao3(Double vlRevisao3) {
		this.vlRevisao3 = vlRevisao3;
	}

	/** Método getter para a propriedade linha.
	 *
	 *  @return o valor atual de linha.
	 */
	public String getLinha() {
		return linha;
	}

	/** Obtém o valur atual de linha.
	 * 
	 *  @param linha 
	 *    O novo valor para linha.
	 */
	public void setLinha(String linha) {
		this.linha = linha;
	}
          
}

/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......ValorServico.java
 *   .: Criação.....09 de maio, 21:45
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "ValorServico".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;
import br.com.resource.infra.utils.NumberUtils;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "ValorServico" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class ValorServico extends EntityObject {
	
	//----[ Atributos de classe e instância ]-----------------------------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L; 
    
    /** Código da região */
    private Long codigoRegiao;
    
    /** Modelo do item */
    private String modelo;
    
    /** Valor da Mão de Obra */
    private Double valorMaoObra;
    
    /** Valor do perc de bonificação */    
    private Double percBonificacao;
    
    /** Valor da revisão */
    private Double valorRevisao;
    
    /** Entidade Revisao para relacionamento */
    private Revisao Revisao;
    
    //  ----[ Métodos Getter ]---------------------------------------------------


	/** Método getter para a propriedade modelo
	 *
	 *  @return String de modelo
	 */
	public String getModelo() {
		return modelo;
	}

	/** Método getter para a propriedade percBonificacao
	 *
	 *  @return Double de percBonificacao
	 */
	public Double getPercBonificacao() {
		return percBonificacao;
	}
	
	/** Método getter para a propriedade percBonificacao retornando um valor do tipo primitivo
	 *
	 *  @return double de percBonificacao
	 */
	public double getPriPercBonificacao() {
		return NumberUtils.round(percBonificacao.doubleValue(),2);
	}

	/** Método getter para a propriedade Revisao
	 *
	 *  @return Revisao de Revisao
	 */
	public Revisao getRevisao() {
		return Revisao;
	}

	/** Método getter para a propriedade valorMaoObra
	 *
	 *  @return Double de valorMaoObra
	 */
	public Double getValorMaoObra() {
		return valorMaoObra;
	}
	
	/** Método getter para a propriedade valorMaoObra retornando um valor do tipo primitivo
	 *
	 *  @return Double de valorMaoObra
	 */
	public double getPriValorMaoObra() {
		return NumberUtils.round(valorMaoObra.doubleValue(),2);
	}

	/** Método getter para a propriedade valorRevisao
	 *
	 *  @return Double de valorRevisao
	 */
	public Double getValorRevisao() {
		return valorRevisao;
	}
	
	/** Método getter para a propriedade valorRevisao retornando um valor do tipo primitivo
	 *
	 *  @return double de valorRevisao
	 */
	public double getPriValorRevisao() {
		return NumberUtils.round(valorRevisao.doubleValue(),2);
	}

	
	/** Método getter para a propriedade codigoRegiao
	 *
	 *  @return Long de codigoRegiao
	 */
	public Long getCodigoRegiao() {
		return codigoRegiao;
	}


	//	----[ Métodos Setter ]---------------------------------------------------
	
	/** Método setter para a propriedade modelo
	 *
	 * @param modelo String
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/** Método setter para a propriedade percBonificacao
	 *
	 * @param percBonificacao Double
	 */
	public void setPercBonificacao(Double percBonificacao) {
		this.percBonificacao = percBonificacao;
	}

	/** Método setter para a propriedade Revisao
	 *
	 * @param revisao Revisao
	 */
	public void setRevisao(Revisao revisao) {
		Revisao = revisao;
	}

	/** Método setter para a propriedade valorMaoObra
	 *
	 * @param valorMaoObra Double
	 */
	public void setValorMaoObra(Double valorMaoObra) {
		this.valorMaoObra = valorMaoObra;
	}

	/** Método setter para a propriedade valorRevisao
	 *
	 * @param valorRevisao Double
	 */
	public void setValorRevisao(Double valorRevisao) {
		this.valorRevisao = valorRevisao;
	}
	
	/** Método setter para a propriedade codigoRegiao
	 *
	 * @param codigoRegiao Long
	 */
	public void setCodigoRegiao(Long codigoRegiao) {
		this.codigoRegiao = codigoRegiao;
	}    
    
}

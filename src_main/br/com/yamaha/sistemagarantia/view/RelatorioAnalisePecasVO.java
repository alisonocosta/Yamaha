/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioAnalisePecasVO.java
 *   .: Criação.....19 de julho, 09:29
 *   .: Autor.......Gisele Panosso
 *   .: Descrição...Entidade "RelatorioAnalisePecasVO".
 */
package br.com.yamaha.sistemagarantia.view;

import br.com.resource.infra.model.EntityObject;

public class RelatorioAnalisePecasVO extends EntityObject{

	//----[ Atributos de classe e instância ]---------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;    
    
    /** Armazena o campo concessionaria do Relatório Análise de Peças. */
    private String concessionaria;
    
    /** Armazena o campo chassi do Relatório Análise de Peças. */
    private String chassi;

    /** Armazena o campo codPeca do Relatório Análise de Peças. */
    private String codPeca;

    /** Armazena o campo descricao do Relatório Análise de Peças. */
    private String descricao;

    /** Armazena o campo vlPeca do Relatório Análise de Peças. */
    private Long vlPeca;

    /** Armazena o campo percTotal do Relatório Análise de Peças. */
    private Long percTotal;

    /** Armazena o campo vlAcumulado do Relatório Análise de Peças. */
    private Long vlAcumulado;
    
    /** Armazena o campo vlAcumulado do Relatório Análise de Peças. */
    private String codSintoma;    

    /** Armazena o campo codCondicao do Relatório Análise de Peças. */
    private String codCondicao;

	/** Método getter para a propriedade chassi.
	 *
	 *  @return o valor atual de chassi.
	 */
	public String getChassi() {
		return chassi;
	}

    //	----[ Métodos Getter ]---------------------------------------------------
	
	/** Método getter para a propriedade codCondicao.
	 *
	 *  @return o valor atual de codCondicao.
	 */
	public String getCodCondicao() {
		return codCondicao;
	}

	/** Método getter para a propriedade codPeca.
	 *
	 *  @return o valor atual de codPeca.
	 */
	public String getCodPeca() {
		return codPeca;
	}

	/** Método getter para a propriedade codSintoma.
	 *
	 *  @return o valor atual de codSintoma.
	 */
	public String getCodSintoma() {
		return codSintoma;
	}

	/** Método getter para a propriedade concessionaria.
	 *
	 *  @return o valor atual de concessionaria.
	 */
	public String getConcessionaria() {
		return concessionaria;
	}

	/** Método getter para a propriedade descricao.
	 *
	 *  @return o valor atual de descricao.
	 */
	public String getDescricao() {
		return descricao;
	}

	/** Método getter para a propriedade percTotal.
	 *
	 *  @return o valor atual de percTotal.
	 */
	public Long getPercTotal() {
		return percTotal;
	}

	/** Método getter para a propriedade vlAcumulado.
	 *
	 *  @return o valor atual de vlAcumulado.
	 */
	public Long getVlAcumulado() {
		return vlAcumulado;
	}

	/** Método getter para a propriedade vlPeca.
	 *
	 *  @return o valor atual de vlPeca.
	 */
	public Long getVlPeca() {
		return vlPeca;
	}

	//	----[ Métodos Setter ]---------------------------------------------------
		
	/** Obtém o valur atual de chassi.
	 * 
	 *  @param chassi 
	 *    O novo valor para chassi.
	 */
	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	/** Obtém o valur atual de codCondicao.
	 * 
	 *  @param codCondicao 
	 *    O novo valor para codCondicao.
	 */
	public void setCodCondicao(String codCondicao) {
		this.codCondicao = codCondicao;
	}

	/** Obtém o valur atual de codPeca.
	 * 
	 *  @param codPeca 
	 *    O novo valor para codPeca.
	 */
	public void setCodPeca(String codPeca) {
		this.codPeca = codPeca;
	}

	/** Obtém o valur atual de codSintoma.
	 * 
	 *  @param codSintoma 
	 *    O novo valor para codSintoma.
	 */
	public void setCodSintoma(String codSintoma) {
		this.codSintoma = codSintoma;
	}

	/** Obtém o valur atual de concessionaria.
	 * 
	 *  @param concessionaria 
	 *    O novo valor para concessionaria.
	 */
	public void setConcessionaria(String concessionaria) {
		this.concessionaria = concessionaria;
	}

	/** Obtém o valur atual de descricao.
	 * 
	 *  @param descricao 
	 *    O novo valor para descricao.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/** Obtém o valur atual de percTotal.
	 * 
	 *  @param percTotal 
	 *    O novo valor para percTotal.
	 */
	public void setPercTotal(Long percTotal) {
		this.percTotal = percTotal;
	}

	/** Obtém o valur atual de vlAcumulado.
	 * 
	 *  @param vlAcumulado 
	 *    O novo valor para vlAcumulado.
	 */
	public void setVlAcumulado(Long vlAcumulado) {
		this.vlAcumulado = vlAcumulado;
	}

	/** Obtém o valur atual de vlPeca.
	 * 
	 *  @param vlPeca 
	 *    O novo valor para vlPeca.
	 */
	public void setVlPeca(Long vlPeca) {
		this.vlPeca = vlPeca;
	}        

    
    
}

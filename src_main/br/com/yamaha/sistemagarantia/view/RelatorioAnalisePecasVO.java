/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioAnalisePecasVO.java
 *   .: Cria��o.....19 de julho, 09:29
 *   .: Autor.......Gisele Panosso
 *   .: Descri��o...Entidade "RelatorioAnalisePecasVO".
 */
package br.com.yamaha.sistemagarantia.view;

import br.com.resource.infra.model.EntityObject;

public class RelatorioAnalisePecasVO extends EntityObject{

	//----[ Atributos de classe e inst�ncia ]---------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;    
    
    /** Armazena o campo concessionaria do Relat�rio An�lise de Pe�as. */
    private String concessionaria;
    
    /** Armazena o campo chassi do Relat�rio An�lise de Pe�as. */
    private String chassi;

    /** Armazena o campo codPeca do Relat�rio An�lise de Pe�as. */
    private String codPeca;

    /** Armazena o campo descricao do Relat�rio An�lise de Pe�as. */
    private String descricao;

    /** Armazena o campo vlPeca do Relat�rio An�lise de Pe�as. */
    private Long vlPeca;

    /** Armazena o campo percTotal do Relat�rio An�lise de Pe�as. */
    private Long percTotal;

    /** Armazena o campo vlAcumulado do Relat�rio An�lise de Pe�as. */
    private Long vlAcumulado;
    
    /** Armazena o campo vlAcumulado do Relat�rio An�lise de Pe�as. */
    private String codSintoma;    

    /** Armazena o campo codCondicao do Relat�rio An�lise de Pe�as. */
    private String codCondicao;

	/** M�todo getter para a propriedade chassi.
	 *
	 *  @return o valor atual de chassi.
	 */
	public String getChassi() {
		return chassi;
	}

    //	----[ M�todos Getter ]---------------------------------------------------
	
	/** M�todo getter para a propriedade codCondicao.
	 *
	 *  @return o valor atual de codCondicao.
	 */
	public String getCodCondicao() {
		return codCondicao;
	}

	/** M�todo getter para a propriedade codPeca.
	 *
	 *  @return o valor atual de codPeca.
	 */
	public String getCodPeca() {
		return codPeca;
	}

	/** M�todo getter para a propriedade codSintoma.
	 *
	 *  @return o valor atual de codSintoma.
	 */
	public String getCodSintoma() {
		return codSintoma;
	}

	/** M�todo getter para a propriedade concessionaria.
	 *
	 *  @return o valor atual de concessionaria.
	 */
	public String getConcessionaria() {
		return concessionaria;
	}

	/** M�todo getter para a propriedade descricao.
	 *
	 *  @return o valor atual de descricao.
	 */
	public String getDescricao() {
		return descricao;
	}

	/** M�todo getter para a propriedade percTotal.
	 *
	 *  @return o valor atual de percTotal.
	 */
	public Long getPercTotal() {
		return percTotal;
	}

	/** M�todo getter para a propriedade vlAcumulado.
	 *
	 *  @return o valor atual de vlAcumulado.
	 */
	public Long getVlAcumulado() {
		return vlAcumulado;
	}

	/** M�todo getter para a propriedade vlPeca.
	 *
	 *  @return o valor atual de vlPeca.
	 */
	public Long getVlPeca() {
		return vlPeca;
	}

	//	----[ M�todos Setter ]---------------------------------------------------
		
	/** Obt�m o valur atual de chassi.
	 * 
	 *  @param chassi 
	 *    O novo valor para chassi.
	 */
	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	/** Obt�m o valur atual de codCondicao.
	 * 
	 *  @param codCondicao 
	 *    O novo valor para codCondicao.
	 */
	public void setCodCondicao(String codCondicao) {
		this.codCondicao = codCondicao;
	}

	/** Obt�m o valur atual de codPeca.
	 * 
	 *  @param codPeca 
	 *    O novo valor para codPeca.
	 */
	public void setCodPeca(String codPeca) {
		this.codPeca = codPeca;
	}

	/** Obt�m o valur atual de codSintoma.
	 * 
	 *  @param codSintoma 
	 *    O novo valor para codSintoma.
	 */
	public void setCodSintoma(String codSintoma) {
		this.codSintoma = codSintoma;
	}

	/** Obt�m o valur atual de concessionaria.
	 * 
	 *  @param concessionaria 
	 *    O novo valor para concessionaria.
	 */
	public void setConcessionaria(String concessionaria) {
		this.concessionaria = concessionaria;
	}

	/** Obt�m o valur atual de descricao.
	 * 
	 *  @param descricao 
	 *    O novo valor para descricao.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/** Obt�m o valur atual de percTotal.
	 * 
	 *  @param percTotal 
	 *    O novo valor para percTotal.
	 */
	public void setPercTotal(Long percTotal) {
		this.percTotal = percTotal;
	}

	/** Obt�m o valur atual de vlAcumulado.
	 * 
	 *  @param vlAcumulado 
	 *    O novo valor para vlAcumulado.
	 */
	public void setVlAcumulado(Long vlAcumulado) {
		this.vlAcumulado = vlAcumulado;
	}

	/** Obt�m o valur atual de vlPeca.
	 * 
	 *  @param vlPeca 
	 *    O novo valor para vlPeca.
	 */
	public void setVlPeca(Long vlPeca) {
		this.vlPeca = vlPeca;
	}        

    
    
}

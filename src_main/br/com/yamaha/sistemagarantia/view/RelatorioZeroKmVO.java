/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioZeroKmVO.java
 *   .: Cria��o.....12 de julho, 09:44
 *   .: Autor.......Gisele Panosso
 *   .: Descri��o...Entidade "RelatorioZeroKmVO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.math.BigDecimal;

import br.com.resource.infra.model.EntityObject;

public class RelatorioZeroKmVO extends EntityObject {

	//----[ Atributos de classe e inst�ncia ]---------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;    
	
    /** Armazena o campo modelo do Relat�rio Zero Km. */
    private String modelo;
    
    /** Armazena o campo chassi do Relat�rio Zero Km. */
    private String chassi;
    
    /** Armazena o campo codPeca do Relat�rio Zero Km. */
    private String codPeca;    
    
    /** Armazena o campo descricao do Relat�rio Zero Km. */
    private String descricao;  
    
    /** Armazena o campo quantidade do Relat�rio Zero Km. */
    private BigDecimal quantidade;  
    
    /** Armazena o campo pecaCausadora do Relat�rio Zero Km. */
    private String pecaCausadora;      
    
    /** Armazena o campo valorPeca do Relat�rio Zero Km. */
    private BigDecimal valorPeca; 
    
    /** Armazena o campo valorMobra do Relat�rio Zero Km. */
    private BigDecimal valorMobra;
    
    /** Armazena o campo valorMobra do Relat�rio Zero Km. */
    private BigDecimal valorServico3;

    /** Armazena o campo codSintoma do Relat�rio Zero Km. */
    private String codSintoma;    
    
    /** Armazena o campo descSintoma do Relat�rio Zero Km. */
    private String descSintoma;
 
    /** Armazena o campo codCondicao do Relat�rio Zero Km. */
    private String codCondicao; 
    
    /** Armazena o campo descCondicao do Relat�rio Zero Km. */
    private String descCondicao;
    
    /** Armazena o campo linha do Relat�rio Zero Km. */
    private String linha;


    //	----[ M�todos Getter ]---------------------------------------------------


	/** M�todo getter para a propriedade chassi.
	 *
	 *  @return o valor atual de chassi.
	 */
	public String getChassi() {
		return chassi;
	}

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

	/** M�todo getter para a propriedade descCondicao.
	 *
	 *  @return o valor atual de descCondicao.
	 */
	public String getDescCondicao() {
		return descCondicao;
	}

	/** M�todo getter para a propriedade descricao.
	 *
	 *  @return o valor atual de descricao.
	 */
	public String getDescricao() {
		return descricao;
	}

	/** M�todo getter para a propriedade descSintoma.
	 *
	 *  @return o valor atual de descSintoma.
	 */
	public String getDescSintoma() {
		return descSintoma;
	}

	/** M�todo getter para a propriedade modelo.
	 *
	 *  @return o valor atual de modelo.
	 */
	public String getModelo() {
		return modelo;
	}

	/** M�todo getter para a propriedade pecaCausadora.
	 *
	 *  @return o valor atual de pecaCausadora.
	 */
	public String getPecaCausadora() {
		return pecaCausadora;
	}

	/** M�todo getter para a propriedade quantidade.
	 *
	 *  @return o valor atual de quantidade.
	 */
	public BigDecimal getQuantidade() {
		return quantidade;
	}

	/** M�todo getter para a propriedade valorMobra.
	 *
	 *  @return o valor atual de valorMobra.
	 */
	public BigDecimal getValorMobra() {
		return valorMobra;
	}

	/** M�todo getter para a propriedade valorPeca.
	 *
	 *  @return o valor atual de valorPeca.
	 */
	public BigDecimal getValorPeca() {
		return valorPeca;
	}

	/** M�todo getter para a propriedade valorServico3.
	 *
	 *  @return o valor atual de valorServico3.
	 */
	public BigDecimal getValorServico3() {
		return valorServico3;
	}

	/** M�todo getter para a propriedade linha.
	 *
	 *  @return o valor atual de linha.
	 */
	public String getLinha() {
		return linha;
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

	/** Obt�m o valur atual de descCondicao.
	 * 
	 *  @param descCondicao 
	 *    O novo valor para descCondicao.
	 */
	public void setDescCondicao(String descCondicao) {
		this.descCondicao = descCondicao;
	}

	/** Obt�m o valur atual de descricao.
	 * 
	 *  @param descricao 
	 *    O novo valor para descricao.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/** Obt�m o valur atual de descSintoma.
	 * 
	 *  @param descSintoma 
	 *    O novo valor para descSintoma.
	 */
	public void setDescSintoma(String descSintoma) {
		this.descSintoma = descSintoma;
	}

	/** Obt�m o valur atual de modelo.
	 * 
	 *  @param modelo 
	 *    O novo valor para modelo.
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/** Obt�m o valur atual de pecaCausadora.
	 * 
	 *  @param pecaCausadora 
	 *    O novo valor para pecaCausadora.
	 */
	public void setPecaCausadora(String pecaCausadora) {
		this.pecaCausadora = pecaCausadora;
	}

	/** Obt�m o valur atual de quantidade.
	 * 
	 *  @param quantidade 
	 *    O novo valor para quantidade.
	 */
	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	/** Obt�m o valur atual de valorMobra.
	 * 
	 *  @param valorMobra 
	 *    O novo valor para valorMobra.
	 */
	public void setValorMobra(BigDecimal valorMobra) {
		this.valorMobra = valorMobra;
	}

	/** Obt�m o valur atual de valorPeca.
	 * 
	 *  @param valorPeca 
	 *    O novo valor para valorPeca.
	 */
	public void setValorPeca(BigDecimal valorPeca) {
		this.valorPeca = valorPeca;
	}

	/** Obt�m o valur atual de valorServico3.
	 * 
	 *  @param valorServico3 
	 *    O novo valor para valorServico3.
	 */
	public void setValorServico3(BigDecimal valorServico3) {
		this.valorServico3 = valorServico3;
	}

	/** Obt�m o valur atual de linha.
	 * 
	 *  @param linha 
	 *    O novo valor para linha.
	 */
	public void setLinha(String linha) {
		this.linha = linha;
	}

	
}

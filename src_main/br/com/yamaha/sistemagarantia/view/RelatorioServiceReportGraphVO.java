/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioServiceReportGraphVO.java
 *   .: Cria��o.....11 de agosto, 09:42
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "RelatorioServiceReportGraphVO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.math.BigDecimal;

import br.com.resource.infra.model.EntityObject;
import br.com.resource.infra.utils.NumberUtils;

public class RelatorioServiceReportGraphVO extends EntityObject {

	//----[ Atributos de classe e inst�ncia ]---------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;  
    
    public static final String ITEM_BUDGET = "Budget";
    public static final String ITEM_RESULT = "Result";
    public static final String ITEM_PREV   = "Prev Yr";
    
    public static final int PRI_PART    = 1; // Gr�ficos 1,2 e 3
    public static final int SEC_PART    = 2; // Gr�ficos 4,5 e 6
    public static final int TER_PART    = 3; // Gr�ficos 7,8 e 9
    
    /** Armazena o campo ano do Relat�rio. */
    private String ano;
    
    /** Armazena o campo de sequ�ncia num�rica do Relat�rio. */
    private String sequencia;
    
    /** Armazena o campo item do Relat�rio. */
    private String item;
    
    /** Armazena o valor para o m�s 01 do per�odo pesquisado */
    private BigDecimal mes01;
    
    /** Armazena o valor para o m�s 02 do per�odo pesquisado */
    private BigDecimal mes02;
    
    /** Armazena o valor para o m�s 03 do per�odo pesquisado */
    private BigDecimal mes03;
    
    /** Armazena o valor para o m�s 04 do per�odo pesquisado */
    private BigDecimal mes04;
    
    /** Armazena o valor para o m�s 05 do per�odo pesquisado */
    private BigDecimal mes05;
    
    /** Armazena o valor para o m�s 06 do per�odo pesquisado */
    private BigDecimal mes06;
    
    /** Armazena o valor para o m�s 07 do per�odo pesquisado */
    private BigDecimal mes07;
    
    /** Armazena o valor para o m�s 08 do per�odo pesquisado */
    private BigDecimal mes08;
    
    /** Armazena o valor para o m�s 09 do per�odo pesquisado */
    private BigDecimal mes09;
    
    /** Armazena o valor para o m�s 10 do per�odo pesquisado */
    private BigDecimal mes10;
    
    /** Armazena o valor para o m�s 11 do per�odo pesquisado */
    private BigDecimal mes11;
    
    /** Armazena o valor para o m�s 12 do per�odo pesquisado */
    private BigDecimal mes12;

	/** M�todo getter para a propriedade ano
	 * 
	 *  @return String
	 *
	 */
	public String getAno() {
		return ano;
	}

	/** M�todo getter para a propriedade item
	 * 
	 *  @return String
	 *
	 */
	public String getItem() {
		return item;
	}

	/** M�todo getter para a propriedade mes01
	 * 
	 *  @return BigDecimal
	 *
	 */
	public BigDecimal getMes01() {
		return mes01;
	}
	
	/** M�todo getter para a propriedade mes02
	 * 
	 *  @return BigDecimal
	 *
	 */
	public BigDecimal getMes02() {
		return mes02;
	}

	/** M�todo getter para a propriedade mes03
	 * 
	 *  @return BigDecimal
	 *
	 */
	public BigDecimal getMes03() {
		return mes03;
	}

	/** M�todo getter para a propriedade mes04
	 * 
	 *  @return BigDecimal
	 *
	 */
	public BigDecimal getMes04() {
		return mes04;
	}

	/** M�todo getter para a propriedade mes05
	 * 
	 *  @return BigDecimal
	 *
	 */
	public BigDecimal getMes05() {
		return mes05;
	}

	/** M�todo getter para a propriedade mes06
	 * 
	 *  @return BigDecimal
	 *
	 */
	public BigDecimal getMes06() {
		return mes06;
	}

	/** M�todo getter para a propriedade mes07
	 * 
	 *  @return BigDecimal
	 *
	 */
	public BigDecimal getMes07() {
		return mes07;
	}

	/** M�todo getter para a propriedade mes08
	 * 
	 *  @return BigDecimal
	 *
	 */
	public BigDecimal getMes08() {
		return mes08;
	}

	/** M�todo getter para a propriedade mes09
	 * 
	 *  @return BigDecimal
	 *
	 */
	public BigDecimal getMes09() {
		return mes09;
	}

	/** M�todo getter para a propriedade mes10
	 * 
	 *  @return BigDecimal
	 *
	 */
	public BigDecimal getMes10() {
		return mes10;
	}

	/** M�todo getter para a propriedade mes11
	 * 
	 *  @return BigDecimal
	 *
	 */
	public BigDecimal getMes11() {
		return mes11;
	}

	/** M�todo getter para a propriedade mes12
	 * 
	 *  @return BigDecimal
	 *
	 */
	public BigDecimal getMes12() {
		return mes12;
	}

	/** M�todo getter para a propriedade sequencia
	 * 
	 *  @return String
	 *
	 */
	public String getSequencia() {
		return sequencia;
	}

	/** M�todo setter para a propriedade ano
	 *
	 * @param ano 
	 *           <code>String</code> a ser designado para ano.
	 * 
	 */
	public void setAno(String ano) {
		this.ano = ano;
	}

	/** M�todo setter para a propriedade item
	 *
	 * @param item 
	 *           <code>String</code> a ser designado para item.
	 * 
	 */
	public void setItem(String item) {
		this.item = item;
	}

	/** M�todo setter para a propriedade mes01
	 *
	 * @param mes01 
	 *           <code>BigDecimal</code> a ser designado para mes01.
	 * 
	 */
	public void setMes01(BigDecimal mes01) {
		
			this.mes01 = mes01;
	}

	/** M�todo setter para a propriedade mes02
	 *
	 * @param mes02 
	 *           <code>BigDecimal</code> a ser designado para mes02.
	 * 
	 */
	public void setMes02(BigDecimal mes02) {
		this.mes02 = mes02;
	}

	/** M�todo setter para a propriedade mes03
	 *
	 * @param mes03 
	 *           <code>BigDecimal</code> a ser designado para mes03.
	 * 
	 */
	public void setMes03(BigDecimal mes03) {
		this.mes03 = mes03;
	}

	/** M�todo setter para a propriedade mes04
	 *
	 * @param mes04 
	 *           <code>BigDecimal</code> a ser designado para mes04.
	 * 
	 */
	public void setMes04(BigDecimal mes04) {
		this.mes04 = mes04;
	}

	/** M�todo setter para a propriedade mes05
	 *
	 * @param mes05 
	 *           <code>BigDecimal</code> a ser designado para mes05.
	 * 
	 */
	public void setMes05(BigDecimal mes05) {
		this.mes05 = mes05;
	}

	/** M�todo setter para a propriedade mes06
	 *
	 * @param mes06 
	 *           <code>BigDecimal</code> a ser designado para mes06.
	 * 
	 */
	public void setMes06(BigDecimal mes06) {
		this.mes06 = mes06;
	}

	/** M�todo setter para a propriedade mes07
	 *
	 * @param mes07 
	 *           <code>BigDecimal</code> a ser designado para mes07.
	 * 
	 */
	public void setMes07(BigDecimal mes07) {
		this.mes07 = mes07;
	}

	/** M�todo setter para a propriedade mes08
	 *
	 * @param mes08 
	 *           <code>BigDecimal</code> a ser designado para mes08.
	 * 
	 */
	public void setMes08(BigDecimal mes08) {
		this.mes08 = mes08;
	}

	/** M�todo setter para a propriedade mes09
	 *
	 * @param mes09 
	 *           <code>BigDecimal</code> a ser designado para mes09.
	 * 
	 */
	public void setMes09(BigDecimal mes09) {
		this.mes09 = mes09;
	}

	/** M�todo setter para a propriedade mes10
	 *
	 * @param mes10 
	 *           <code>BigDecimal</code> a ser designado para mes10.
	 * 
	 */
	public void setMes10(BigDecimal mes10) {
		this.mes10 = mes10;
	}

	/** M�todo setter para a propriedade mes11
	 *
	 * @param mes11 
	 *           <code>BigDecimal</code> a ser designado para mes11.
	 * 
	 */
	public void setMes11(BigDecimal mes11) {
		this.mes11 = mes11;
	}

	/** M�todo setter para a propriedade mes12
	 *
	 * @param mes12 
	 *           <code>BigDecimal</code> a ser designado para mes12.
	 * 
	 */
	public void setMes12(BigDecimal mes12) {
		this.mes12 = mes12;
	}

	/** M�todo setter para a propriedade sequencia
	 *
	 * @param sequencia 
	 *           <code>String</code> a ser designado para sequencia.
	 * 
	 */
	public void setSequencia(Object sequencia) {
		
		if ( sequencia instanceof String )
			this.sequencia = (String)sequencia;
		else
			this.sequencia = String.valueOf(sequencia);
	}
	 
	/** Retorna o valor acumulado dos 12 meses
	 * 
	 * @return double
	 */
	public double getAcumulado() {
		
		double acumulado = 0;
		
		acumulado += this.mes01 != null ? this.mes01.doubleValue() : 0 ;
		acumulado += this.mes02 != null ? this.mes02.doubleValue() : 0 ;
		acumulado += this.mes03 != null ? this.mes03.doubleValue() : 0 ;
		acumulado += this.mes04 != null ? this.mes04.doubleValue() : 0 ;
		acumulado += this.mes05 != null ? this.mes05.doubleValue() : 0 ;
		acumulado += this.mes06 != null ? this.mes06.doubleValue() : 0 ;
		acumulado += this.mes07 != null ? this.mes07.doubleValue() : 0 ;
		acumulado += this.mes08 != null ? this.mes08.doubleValue() : 0 ;
		acumulado += this.mes09 != null ? this.mes09.doubleValue() : 0 ;
		acumulado += this.mes10 != null ? this.mes10.doubleValue() : 0 ;
		acumulado += this.mes11 != null ? this.mes11.doubleValue() : 0 ;
		acumulado += this.mes12 != null ? this.mes12.doubleValue() : 0 ;
				
		return acumulado;
	}
	
	/** Retorna o valor formatado para n�mero
	 * 
	 * @return String
	 */
	public String getFormattedMes01() {
		
		return NumberUtils.formatNumberCurrencyMil(this.mes01.doubleValue());
		
	}
	
	/** Retorna o valor formatado para n�mero
	 * 
	 * @return String
	 */
	public String getFormattedMes02() {
		
		return NumberUtils.formatNumberCurrencyMil(this.mes02.doubleValue());
		
	}
	
	/** Retorna o valor formatado para n�mero
	 * 
	 * @return String
	 */
	public String getFormattedMes03() {
		
		return NumberUtils.formatNumberCurrencyMil(this.mes03.doubleValue());
		
	}
	
	/** Retorna o valor formatado para n�mero
	 * 
	 * @return String
	 */
	public String getFormattedMes04() {
		
		return NumberUtils.formatNumberCurrencyMil(this.mes04.doubleValue());
		
	}
	
	/** Retorna o valor formatado para n�mero
	 * 
	 * @return String
	 */
	public String getFormattedMes05() {
		
		return NumberUtils.formatNumberCurrencyMil(this.mes05.doubleValue());
		
	}
	
	/** Retorna o valor formatado para n�mero
	 * 
	 * @return String
	 */
	public String getFormattedMes06() {
		
		return NumberUtils.formatNumberCurrencyMil(this.mes06.doubleValue());
		
	}
	
	/** Retorna o valor formatado para n�mero
	 * 
	 * @return String
	 */
	public String getFormattedMes07() {
		
		return NumberUtils.formatNumberCurrencyMil(this.mes07.doubleValue());
		
	}
	
	/** Retorna o valor formatado para n�mero
	 * 
	 * @return String
	 */
	public String getFormattedMes08() {
		
		return NumberUtils.formatNumberCurrencyMil(this.mes08.doubleValue());
		
	}
	
	/** Retorna o valor formatado para n�mero
	 * 
	 * @return String
	 */
	public String getFormattedMes09() {
				return NumberUtils.formatNumberCurrencyMil(this.mes09.doubleValue());
		
	}
	
	/** Retorna o valor formatado para n�mero
	 * 
	 * @return String
	 */
	public String getFormattedMes10() {
				return NumberUtils.formatNumberCurrencyMil(this.mes10.doubleValue());
		
	}
	
	/** Retorna o valor formatado para n�mero
	 * 
	 * @return String
	 */
	public String getFormattedMes11() {
		
		return NumberUtils.formatNumberCurrencyMil(this.mes11.doubleValue());
		
	}
	
	/** Retorna o valor formatado para n�mero
	 * 
	 * @return String
	 */
	public String getFormattedMes12() {
		
		return NumberUtils.formatNumberCurrencyMil(this.mes12.doubleValue());
		
	}
	
	/** Retorna o valor formatado para n�mero
	 * 
	 * @return String
	 */
	public String getFormattedAcumulado() {
		
		return NumberUtils.formatNumberCurrencyMil(getAcumulado());
		
	}
}    
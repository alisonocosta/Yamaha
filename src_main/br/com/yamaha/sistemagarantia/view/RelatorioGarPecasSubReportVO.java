/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioGarPecasSubReportVO.java
 *   .: Cria��o.....11 de setembro de 2007
 *   .: Autor.......Gisele Panosso
 *   .: Descri��o...Entidade "RelatorioGarPecasSubReportVO".
 */
package br.com.yamaha.sistemagarantia.view;

import br.com.resource.infra.model.EntityObject;

public class RelatorioGarPecasSubReportVO extends EntityObject {
	
	//----[ Atributos de classe e inst�ncia ]---------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;  
	
    /** Armazena o campo ano do relat�rio Garantia e Pe�as.*/
    private String ano;
    
    /** Armazena o campo t�pico (tipo de dado do relat�rio) do relat�rio Garantia e Pe�as.*/
    private String topico;
    
    /** Armazena o campo m01 (m�s 01-Jan) do relat�rio Garantia e Pe�as.*/
    private Double m01;
    
    /** Armazena o campo m02 (m�s 02-Fev) do relat�rio Garantia e Pe�as.*/
    private Double m02;
    
    /** Armazena o campo m03 (m�s 03-Mar) do relat�rio Garantia e Pe�as.*/
    private Double m03;
    
    /** Armazena o campo m04 (m�s 04-Abr) do relat�rio Garantia e Pe�as.*/
    private Double m04;
    
    /** Armazena o campo m05 (m�s 05-Mai) do relat�rio Garantia e Pe�as.*/
    private Double m05;
    
    /** Armazena o campo m06 (m�s 06-Jun) do relat�rio Garantia e Pe�as.*/
    private Double m06;
    
    /** Armazena o campo m07 (m�s 07-Jul) do relat�rio Garantia e Pe�as.*/
    private Double m07;
    
    /** Armazena o campo m08 (m�s 08-Ago) do relat�rio Garantia e Pe�as.*/
    private Double m08;
    
    /** Armazena o campo m09 (m�s 09-Set) do relat�rio Garantia e Pe�as.*/
    private Double m09;
    
    /** Armazena o campo m10 (m�s 10-Out) do relat�rio Garantia e Pe�as.*/ /** Armazena o campo m01 (m�s 01-Jan) do relat�rio Garantia e Pe�as.*/
    private Double m10;
    
    /** Armazena o campo m11 (m�s 11-Nov) do relat�rio Garantia e Pe�as.*/
    private Double m11;
    
    /** Armazena o campo m12 (m�s 12-Dec) do relat�rio Garantia e Pe�as.*/
    private Double m12;
    
    //	----[ M�todos Getter ]---------------------------------------------------

	/** M�todo getter para a propriedade ano.
	 *
	 *  @return o valor atual de ano.
	 */
	public String getAno() {
		return ano;
	}

	/** M�todo getter para a propriedade m01.
	 *
	 *  @return o valor atual de m01.
	 */
	public Double getM01() {
		return m01;
	}

	/** M�todo getter para a propriedade m02.
	 *
	 *  @return o valor atual de m02.
	 */
	public Double getM02() {
		return m02;
	}

	/** M�todo getter para a propriedade m03.
	 *
	 *  @return o valor atual de m03.
	 */
	public Double getM03() {
		return m03;
	}

	/** M�todo getter para a propriedade m04.
	 *
	 *  @return o valor atual de m04.
	 */
	public Double getM04() {
		return m04;
	}

	/** M�todo getter para a propriedade m05.
	 *
	 *  @return o valor atual de m05.
	 */
	public Double getM05() {
		return m05;
	}

	/** M�todo getter para a propriedade m06.
	 *
	 *  @return o valor atual de m06.
	 */
	public Double getM06() {
		return m06;
	}

	/** M�todo getter para a propriedade m07.
	 *
	 *  @return o valor atual de m07.
	 */
	public Double getM07() {
		return m07;
	}

	/** M�todo getter para a propriedade m08.
	 *
	 *  @return o valor atual de m08.
	 */
	public Double getM08() {
		return m08;
	}

	/** M�todo getter para a propriedade m09.
	 *
	 *  @return o valor atual de m09.
	 */
	public Double getM09() {
		return m09;
	}

	/** M�todo getter para a propriedade m10.
	 *
	 *  @return o valor atual de m10.
	 */
	public Double getM10() {
		return m10;
	}

	/** M�todo getter para a propriedade m11.
	 *
	 *  @return o valor atual de m11.
	 */
	public Double getM11() {
		return m11;
	}

	/** M�todo getter para a propriedade m12.
	 *
	 *  @return o valor atual de m12.
	 */
	public Double getM12() {
		return m12;
	}


	/** M�todo getter para a propriedade topico.
	 *
	 *  @return o valor atual de topico.
	 */
	public String getTopico() {
		return topico;
	}

	//	----[ M�todos Setter ]---------------------------------------------------
	
	/** Obt�m o valor atual de ano.
	 * 
	 *  @param ano 
	 *    O novo valor para ano.
	 */
	public void setAno(String ano) {
		this.ano = ano;
	}

	/** Obt�m o valor atual de m01.
	 * 
	 *  @param m01 
	 *    O novo valor para m01.
	 */
	public void setM01(Double m01) {
		this.m01 = m01;
	}

	/** Obt�m o valor atual de m02.
	 * 
	 *  @param m02 
	 *    O novo valor para m02.
	 */
	public void setM02(Double m02) {
		this.m02 = m02;
	}

	/** Obt�m o valor atual de m03.
	 * 
	 *  @param m03 
	 *    O novo valor para m03.
	 */
	public void setM03(Double m03) {
		this.m03 = m03;
	}

	/** Obt�m o valor atual de m04.
	 * 
	 *  @param m04 
	 *    O novo valor para m04.
	 */
	public void setM04(Double m04) {
		this.m04 = m04;
	}

	/** Obt�m o valor atual de m05.
	 * 
	 *  @param m05 
	 *    O novo valor para m05.
	 */
	public void setM05(Double m05) {
		this.m05 = m05;
	}

	/** Obt�m o valor atual de m06.
	 * 
	 *  @param m06 
	 *    O novo valor para m06.
	 */
	public void setM06(Double m06) {
		this.m06 = m06;
	}

	/** Obt�m o valor atual de m07.
	 * 
	 *  @param m07 
	 *    O novo valor para m07.
	 */
	public void setM07(Double m07) {
		this.m07 = m07;
	}

	/** Obt�m o valor atual de m08.
	 * 
	 *  @param m08 
	 *    O novo valor para m08.
	 */
	public void setM08(Double m08) {
		this.m08 = m08;
	}

	/** Obt�m o valor atual de m09.
	 * 
	 *  @param m09 
	 *    O novo valor para m09.
	 */
	public void setM09(Double m09) {
		this.m09 = m09;
	}

	/** Obt�m o valor atual de m10.
	 * 
	 *  @param m10 
	 *    O novo valor para m10.
	 */
	public void setM10(Double m10) {
		this.m10 = m10;
	}

	/** Obt�m o valor atual de m11.
	 * 
	 *  @param m11 
	 *    O novo valor para m11.
	 */
	public void setM11(Double m11) {
		this.m11 = m11;
	}

	/** Obt�m o valor atual de m12.
	 * 
	 *  @param m12 
	 *    O novo valor para m12.
	 */
	public void setM12(Double m12) {
		this.m12 = m12;
	}

	/** Obt�m o valor atual de topico.
	 * 
	 *  @param topico 
	 *    O novo valor para topico.
	 */
	public void setTopico(String topico) {
		this.topico = topico;
	}
}

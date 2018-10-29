/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioGarPecasSubReportVO.java
 *   .: Criação.....11 de setembro de 2007
 *   .: Autor.......Gisele Panosso
 *   .: Descrição...Entidade "RelatorioGarPecasSubReportVO".
 */
package br.com.yamaha.sistemagarantia.view;

import br.com.resource.infra.model.EntityObject;

public class RelatorioGarPecasSubReportVO extends EntityObject {
	
	//----[ Atributos de classe e instância ]---------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;  
	
    /** Armazena o campo ano do relatório Garantia e Peças.*/
    private String ano;
    
    /** Armazena o campo tópico (tipo de dado do relatório) do relatório Garantia e Peças.*/
    private String topico;
    
    /** Armazena o campo m01 (mês 01-Jan) do relatório Garantia e Peças.*/
    private Double m01;
    
    /** Armazena o campo m02 (mês 02-Fev) do relatório Garantia e Peças.*/
    private Double m02;
    
    /** Armazena o campo m03 (mês 03-Mar) do relatório Garantia e Peças.*/
    private Double m03;
    
    /** Armazena o campo m04 (mês 04-Abr) do relatório Garantia e Peças.*/
    private Double m04;
    
    /** Armazena o campo m05 (mês 05-Mai) do relatório Garantia e Peças.*/
    private Double m05;
    
    /** Armazena o campo m06 (mês 06-Jun) do relatório Garantia e Peças.*/
    private Double m06;
    
    /** Armazena o campo m07 (mês 07-Jul) do relatório Garantia e Peças.*/
    private Double m07;
    
    /** Armazena o campo m08 (mês 08-Ago) do relatório Garantia e Peças.*/
    private Double m08;
    
    /** Armazena o campo m09 (mês 09-Set) do relatório Garantia e Peças.*/
    private Double m09;
    
    /** Armazena o campo m10 (mês 10-Out) do relatório Garantia e Peças.*/ /** Armazena o campo m01 (mês 01-Jan) do relatório Garantia e Peças.*/
    private Double m10;
    
    /** Armazena o campo m11 (mês 11-Nov) do relatório Garantia e Peças.*/
    private Double m11;
    
    /** Armazena o campo m12 (mês 12-Dec) do relatório Garantia e Peças.*/
    private Double m12;
    
    //	----[ Métodos Getter ]---------------------------------------------------

	/** Método getter para a propriedade ano.
	 *
	 *  @return o valor atual de ano.
	 */
	public String getAno() {
		return ano;
	}

	/** Método getter para a propriedade m01.
	 *
	 *  @return o valor atual de m01.
	 */
	public Double getM01() {
		return m01;
	}

	/** Método getter para a propriedade m02.
	 *
	 *  @return o valor atual de m02.
	 */
	public Double getM02() {
		return m02;
	}

	/** Método getter para a propriedade m03.
	 *
	 *  @return o valor atual de m03.
	 */
	public Double getM03() {
		return m03;
	}

	/** Método getter para a propriedade m04.
	 *
	 *  @return o valor atual de m04.
	 */
	public Double getM04() {
		return m04;
	}

	/** Método getter para a propriedade m05.
	 *
	 *  @return o valor atual de m05.
	 */
	public Double getM05() {
		return m05;
	}

	/** Método getter para a propriedade m06.
	 *
	 *  @return o valor atual de m06.
	 */
	public Double getM06() {
		return m06;
	}

	/** Método getter para a propriedade m07.
	 *
	 *  @return o valor atual de m07.
	 */
	public Double getM07() {
		return m07;
	}

	/** Método getter para a propriedade m08.
	 *
	 *  @return o valor atual de m08.
	 */
	public Double getM08() {
		return m08;
	}

	/** Método getter para a propriedade m09.
	 *
	 *  @return o valor atual de m09.
	 */
	public Double getM09() {
		return m09;
	}

	/** Método getter para a propriedade m10.
	 *
	 *  @return o valor atual de m10.
	 */
	public Double getM10() {
		return m10;
	}

	/** Método getter para a propriedade m11.
	 *
	 *  @return o valor atual de m11.
	 */
	public Double getM11() {
		return m11;
	}

	/** Método getter para a propriedade m12.
	 *
	 *  @return o valor atual de m12.
	 */
	public Double getM12() {
		return m12;
	}


	/** Método getter para a propriedade topico.
	 *
	 *  @return o valor atual de topico.
	 */
	public String getTopico() {
		return topico;
	}

	//	----[ Métodos Setter ]---------------------------------------------------
	
	/** Obtém o valor atual de ano.
	 * 
	 *  @param ano 
	 *    O novo valor para ano.
	 */
	public void setAno(String ano) {
		this.ano = ano;
	}

	/** Obtém o valor atual de m01.
	 * 
	 *  @param m01 
	 *    O novo valor para m01.
	 */
	public void setM01(Double m01) {
		this.m01 = m01;
	}

	/** Obtém o valor atual de m02.
	 * 
	 *  @param m02 
	 *    O novo valor para m02.
	 */
	public void setM02(Double m02) {
		this.m02 = m02;
	}

	/** Obtém o valor atual de m03.
	 * 
	 *  @param m03 
	 *    O novo valor para m03.
	 */
	public void setM03(Double m03) {
		this.m03 = m03;
	}

	/** Obtém o valor atual de m04.
	 * 
	 *  @param m04 
	 *    O novo valor para m04.
	 */
	public void setM04(Double m04) {
		this.m04 = m04;
	}

	/** Obtém o valor atual de m05.
	 * 
	 *  @param m05 
	 *    O novo valor para m05.
	 */
	public void setM05(Double m05) {
		this.m05 = m05;
	}

	/** Obtém o valor atual de m06.
	 * 
	 *  @param m06 
	 *    O novo valor para m06.
	 */
	public void setM06(Double m06) {
		this.m06 = m06;
	}

	/** Obtém o valor atual de m07.
	 * 
	 *  @param m07 
	 *    O novo valor para m07.
	 */
	public void setM07(Double m07) {
		this.m07 = m07;
	}

	/** Obtém o valor atual de m08.
	 * 
	 *  @param m08 
	 *    O novo valor para m08.
	 */
	public void setM08(Double m08) {
		this.m08 = m08;
	}

	/** Obtém o valor atual de m09.
	 * 
	 *  @param m09 
	 *    O novo valor para m09.
	 */
	public void setM09(Double m09) {
		this.m09 = m09;
	}

	/** Obtém o valor atual de m10.
	 * 
	 *  @param m10 
	 *    O novo valor para m10.
	 */
	public void setM10(Double m10) {
		this.m10 = m10;
	}

	/** Obtém o valor atual de m11.
	 * 
	 *  @param m11 
	 *    O novo valor para m11.
	 */
	public void setM11(Double m11) {
		this.m11 = m11;
	}

	/** Obtém o valor atual de m12.
	 * 
	 *  @param m12 
	 *    O novo valor para m12.
	 */
	public void setM12(Double m12) {
		this.m12 = m12;
	}

	/** Obtém o valor atual de topico.
	 * 
	 *  @param topico 
	 *    O novo valor para topico.
	 */
	public void setTopico(String topico) {
		this.topico = topico;
	}
}

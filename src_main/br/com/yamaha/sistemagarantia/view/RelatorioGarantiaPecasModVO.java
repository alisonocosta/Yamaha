/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioGarantiaPecasModVO.java
 *   .: Cria��o.....17 de setembro de 2007, 09:05
 *   .: Autor.......Gisele Panosso
 *   .: Descri��o...Entidade "RelatorioGarantiaPecasModVO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.util.ArrayList;
import java.util.List;

import br.com.resource.infra.model.EntityObject;

public class RelatorioGarantiaPecasModVO extends EntityObject {
	
	//----[ Atributos de classe e inst�ncia ]---------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;  
    
    /** Armazena o campo ano do relat�rio Garantia e Pe�as por Modelo.*/
    private String ano;
    
    /** Armazena o campo t�pico (tipo de dado do relat�rio) do relat�rio Garantia e Pe�as por Modelo.*/
    private String topico;
    
    /** Armazena o campo modelo do relat�rio Garantia e Pe�as por Modelo.*/
    private String modelo;    
    
    /** Armazena o campo m01 (m�s 01-Jan) do relat�rio Garantia e Pe�as por Modelo.*/
    private Double m01;
    
    /** Armazena o campo m02 (m�s 02-Fev) do relat�rio Garantia e Pe�as por Modelo.*/
    private Double m02;
    
    /** Armazena o campo m03 (m�s 03-Mar) do relat�rio Garantia e Pe�as por Modelo.*/
    private Double m03;
    
    /** Armazena o campo m04 (m�s 04-Abr) do relat�rio Garantia e Pe�as por Modelo.*/
    private Double m04;
    
    /** Armazena o campo m05 (m�s 05-Mai) do relat�rio Garantia e Pe�as por Modelo.*/
    private Double m05;
    
    /** Armazena o campo m06 (m�s 06-Jun) do relat�rio Garantia e Pe�as por Modelo.*/
    private Double m06;
    
    /** Armazena o campo m07 (m�s 07-Jul) do relat�rio Garantia e Pe�as por Modelo.*/
    private Double m07;
    
    /** Armazena o campo m08 (m�s 08-Ago) do relat�rio Garantia e Pe�as por Modelo.*/
    private Double m08;
    
    /** Armazena o campo m09 (m�s 09-Set) do relat�rio Garantia e Pe�as por Modelo.*/
    private Double m09;
    
    /** Armazena o campo m10 (m�s 10-Out) do relat�rio Garantia e Pe�as por Modelo.*/ /** Armazena o campo m01 (m�s 01-Jan) do relat�rio Garantia e Pe�as por Modelo.*/
    private Double m10;
    
    /** Armazena o campo m11 (m�s 11-Nov) do relat�rio Garantia e Pe�as por Modelo.*/
    private Double m11;
    
    /** Armazena o campo m12 (m�s 12-Dec) do relat�rio Garantia e Pe�as por Modelo.*/
    private Double m12;
    
    /** Armazena o campo listVlDolar (lista de valores do dolar) do relat�rio Garantia e Pe�as por Modelo.*/
    private List listVlDolar;
    
    /** Armazena o campo listVlAcmMobra (lista de valor acumulado de m�o de obra) do relat�rio Garantia e Pe�as por Modelo.*/
    private List listVlAcmMobra;
    
    /** Armazena o campo listSubtotal (lista de valor do subtotal) do relat�rio Garantia e Pe�as por Modelo.*/
    private List listSubtotal;   
    
    /** Armazena o campo listRevisoes (lista de Detalhes da Revis�o) do relat�rio Garantia e Pe�as por Modelo.*/
    private List listRevisoes; 
    
    /** Armazena o campo listTotal (lista de valor Total = Subtotal + Tot Revis�es) do relat�rio Garantia e Pe�as por Modelo.*/
    private List listTotal;  
    
    /** Armazena o campo listVlFatur (lista de valor do Faturamento) do relat�rio Garantia e Pe�as por Modelo.*/
    private List listVlFatur;    
    
    /** Armazena o campo listQtdes (lista de qtde de moto, sg's e faturada) do relat�rio Garantia e Pe�as por Modelo.*/
    private List listQtdes;       
    
    /** Armazena o campo listGarVlFatur (lista de valor Garantia/Vl Faturado) do relat�rio Garantia e Pe�as por Modelo.*/
    private List listGarVlFatur;    
    
    /** Armazena o campo listRevVlFatur (lista de valor Total Revis�es/Vl Faturado) do relat�rio Garantia e Pe�as por Modelo.*/
    private List listRevVlFatur;  
    
    /** Armazena o campo listTotVlFatur (lista de valor Total/Vl Faturado) do relat�rio Garantia e Pe�as por Modelo.*/
    private List listTotVlFatur;
    
    //	----[ M�todo Add ]---------------------------------------------------  

	/** M�todo construtor.
     */
	public RelatorioGarantiaPecasModVO() {
		super();
		listVlDolar     = new ArrayList();
		listVlAcmMobra  = new ArrayList();
		listSubtotal    = new ArrayList();
		listRevisoes    = new ArrayList();
		listTotal       = new ArrayList();
		listVlFatur     = new ArrayList();
		listQtdes       = new ArrayList();
		listGarVlFatur  = new ArrayList();
		listRevVlFatur  = new ArrayList();
		listTotVlFatur  = new ArrayList();
	}
	
	/** Adiciona uma lista de valores do d�lar no relat�rio Garantia e Pe�as por Modelo.
     * 
     *  @param listVlDolar
     *      listVlDolar a ser adicionado.
     *  
     */
    public void addListVlDolar(RelatorioGarantiaPecasModSubRepVO listVlDolar) {
    	
        this.listVlDolar.add( listVlDolar );
        
    }
    
	/** Adiciona uma lista de valores acumulados da m�o de obra no relat�rio Garantia e Pe�as por Modelo.
     * 
     *  @param listVlAcmMobra
     *      listVlAcmMobra a ser adicionado.
     *  
     */
    public void addListVlAcmMobra(RelatorioGarantiaPecasModSubRepVO listVlAcmMobra) {
    	
        this.listVlAcmMobra.add( listVlAcmMobra );
        
    }
    
	/** Adiciona uma lista de valores do Subtotal no relat�rio Garantia e Pe�as por Modelo.
     * 
     *  @param listSubtotal
     *      listSubtotal a ser adicionado.
     *  
     */
    public void addListSubtotal(RelatorioGarantiaPecasModSubRepVO listSubtotal) {
    	
        this.listSubtotal.add( listSubtotal );
        
    }   
    
	/** Adiciona uma lista de valores das Revis�es no relat�rio Garantia e Pe�as por Modelo.
     * 
     *  @param listRevisoes
     *      listRevisoes a ser adicionado.
     *  
     */
    public void addListRevisoes(RelatorioGarantiaPecasModSubRepVO listRevisoes) {
    	
        this.listRevisoes.add( listRevisoes );
        
    }     
    
	/** Adiciona uma lista de valores do Total no relat�rio Garantia e Pe�as por Modelo.
     * 
     *  @param listTotal
     *      listTotal a ser adicionado.
     *  
     */
    public void addListTotal(RelatorioGarantiaPecasModSubRepVO listTotal) {
    	
        this.listTotal.add( listTotal );
        
    }    

	/** Adiciona uma lista de valores do faturamento no relat�rio Garantia e Pe�as por Modelo.
     * 
     *  @param listVlFatur
     *      listVlFatur a ser adicionado.
     *  
     */
    public void addListVlFatur(RelatorioGarantiaPecasModSubRepVO listVlFatur) {
    	
        this.listVlFatur.add( listVlFatur );
        
    } 
    
	/** Adiciona uma lista de valores da qtde de moto, sg's e faturada no relat�rio Garantia e Pe�as por Modelo.
     * 
     *  @param listQtdes
     *      listQtdes a ser adicionado.
     *  
     */
    public void addListQtdes(RelatorioGarantiaPecasModSubRepVO listQtdes) {
    	
        this.listQtdes.add( listQtdes );
        
    } 
    
	/** Adiciona uma lista de valor Garantia/Vl Faturado no relat�rio Garantia e Pe�as por Modelo.
     * 
     *  @param listRevisoes
     *      listRevisoes a ser adicionado.
     *  
     */
    public void addListGarVlFatur(RelatorioGarantiaPecasModSubRepVO listGarVlFatur) {
    	
        this.listGarVlFatur.add( listGarVlFatur );
        
    }  
    
	/** Adiciona uma lista de valor Total Revis�es/Vl Faturado no relat�rio Garantia e Pe�as por Modelo.
     * 
     *  @param listRevisoes
     *      listRevisoes a ser adicionado.
     *  
     */
    public void addListRevVlFatur(RelatorioGarantiaPecasModSubRepVO listRevVlFatur) {
    	
        this.listRevVlFatur.add( listRevVlFatur );
        
    }
    
	/** Adiciona uma lista de valor Total/Vl Faturado no relat�rio Garantia e Pe�as por Modelo.
     * 
     *  @param listTotVlFatur
     *      listTotVlFatur a ser adicionado.
     *  
     */
    public void addListTotVlFatur(RelatorioGarantiaPecasModSubRepVO listTotVlFatur) {
    	
        this.listTotVlFatur.add( listTotVlFatur );
        
    }


    //	----[ M�todos Getter ]---------------------------------------------------

	/** M�todo getter para a propriedade ano.
	 *
	 *  @return o valor atual de ano.
	 */
	public String getAno() {
		return ano;
	}

	/** M�todo getter para a propriedade listGarVlFatur.
	 *
	 *  @return o valor atual de listGarVlFatur.
	 */
	public List getListGarVlFatur() {
		return listGarVlFatur;
	}

	/** M�todo getter para a propriedade listQtdes.
	 *
	 *  @return o valor atual de listQtdes.
	 */
	public List getListQtdes() {
		return listQtdes;
	}

	/** M�todo getter para a propriedade listRevisoes.
	 *
	 *  @return o valor atual de listRevisoes.
	 */
	public List getListRevisoes() {
		return listRevisoes;
	}

	/** M�todo getter para a propriedade listRevVlFatur.
	 *
	 *  @return o valor atual de listRevVlFatur.
	 */
	public List getListRevVlFatur() {
		return listRevVlFatur;
	}

	/** M�todo getter para a propriedade listSubtotal.
	 *
	 *  @return o valor atual de listSubtotal.
	 */
	public List getListSubtotal() {
		return listSubtotal;
	}

	/** M�todo getter para a propriedade listTotal.
	 *
	 *  @return o valor atual de listTotal.
	 */
	public List getListTotal() {
		return listTotal;
	}

	/** M�todo getter para a propriedade listTotVlFatur.
	 *
	 *  @return o valor atual de listTotVlFatur.
	 */
	public List getListTotVlFatur() {
		return listTotVlFatur;
	}

	/** M�todo getter para a propriedade listVlAcmMobra.
	 *
	 *  @return o valor atual de listVlAcmMobra.
	 */
	public List getListVlAcmMobra() {
		return listVlAcmMobra;
	}

	/** M�todo getter para a propriedade listVlFatur.
	 *
	 *  @return o valor atual de listVlFatur.
	 */
	public List getListVlFatur() {
		return listVlFatur;
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

	/** M�todo getter para a propriedade modelo.
	 *
	 *  @return o valor atual de modelo.
	 */
	public String getModelo() {
		return modelo;
	}
	
	/** M�todo getter para a propriedade listVlDolar.
	 *
	 *  @return o valor atual de listVlDolar.
	 */
	public List getListVlDolar() {
		return listVlDolar;
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

	/** Obt�m o valor atual de listGarVlFatur.
	 * 
	 *  @param listGarVlFatur 
	 *    O novo valor para listGarVlFatur.
	 */
	public void setListGarVlFatur(List listGarVlFatur) {
		this.listGarVlFatur = listGarVlFatur;
	}

	/** Obt�m o valor atual de listQtdes.
	 * 
	 *  @param listQtdes 
	 *    O novo valor para listQtdes.
	 */
	public void setListQtdes(List listQtdes) {
		this.listQtdes = listQtdes;
	}

	/** Obt�m o valor atual de listRevisoes.
	 * 
	 *  @param listRevisoes 
	 *    O novo valor para listRevisoes.
	 */
	public void setListRevisoes(List listRevisoes) {
		this.listRevisoes = listRevisoes;
	}

	/** Obt�m o valor atual de listRevVlFatur.
	 * 
	 *  @param listRevVlFatur 
	 *    O novo valor para listRevVlFatur.
	 */
	public void setListRevVlFatur(List listRevVlFatur) {
		this.listRevVlFatur = listRevVlFatur;
	}

	/** Obt�m o valor atual de listSubtotal.
	 * 
	 *  @param listSubtotal 
	 *    O novo valor para listSubtotal.
	 */
	public void setListSubtotal(List listSubtotal) {
		this.listSubtotal = listSubtotal;
	}

	/** Obt�m o valor atual de listTotal.
	 * 
	 *  @param listTotal 
	 *    O novo valor para listTotal.
	 */
	public void setListTotal(List listTotal) {
		this.listTotal = listTotal;
	}

	/** Obt�m o valor atual de listTotVlFatur.
	 * 
	 *  @param listTotVlFatur 
	 *    O novo valor para listTotVlFatur.
	 */
	public void setListTotVlFatur(List listTotVlFatur) {
		this.listTotVlFatur = listTotVlFatur;
	}

	/** Obt�m o valor atual de listVlAcmMobra.
	 * 
	 *  @param listVlAcmMobra 
	 *    O novo valor para listVlAcmMobra.
	 */
	public void setListVlAcmMobra(List listVlAcmMobra) {
		this.listVlAcmMobra = listVlAcmMobra;
	}

	/** Obt�m o valor atual de listVlFatur.
	 * 
	 *  @param listVlFatur 
	 *    O novo valor para listVlFatur.
	 */
	public void setListVlFatur(List listVlFatur) {
		this.listVlFatur = listVlFatur;
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

	/** Obt�m o valor atual de modelo.
	 * 
	 *  @param modelo 
	 *    O novo valor para modelo.
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/** Obt�m o valor atual de listVlDolar.
	 * 
	 *  @param listVlDolar 
	 *    O novo valor para listVlDolar.
	 */
	public void setListVlDolar(List listVlDolar) {
		this.listVlDolar = listVlDolar;
	}
    
    
    

}
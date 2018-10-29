/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioGarantiaPecasModVO.java
 *   .: Criação.....17 de setembro de 2007, 09:05
 *   .: Autor.......Gisele Panosso
 *   .: Descrição...Entidade "RelatorioGarantiaPecasModVO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.util.ArrayList;
import java.util.List;

import br.com.resource.infra.model.EntityObject;

public class RelatorioGarantiaPecasModVO extends EntityObject {
	
	//----[ Atributos de classe e instância ]---------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;  
    
    /** Armazena o campo ano do relatório Garantia e Peças por Modelo.*/
    private String ano;
    
    /** Armazena o campo tópico (tipo de dado do relatório) do relatório Garantia e Peças por Modelo.*/
    private String topico;
    
    /** Armazena o campo modelo do relatório Garantia e Peças por Modelo.*/
    private String modelo;    
    
    /** Armazena o campo m01 (mês 01-Jan) do relatório Garantia e Peças por Modelo.*/
    private Double m01;
    
    /** Armazena o campo m02 (mês 02-Fev) do relatório Garantia e Peças por Modelo.*/
    private Double m02;
    
    /** Armazena o campo m03 (mês 03-Mar) do relatório Garantia e Peças por Modelo.*/
    private Double m03;
    
    /** Armazena o campo m04 (mês 04-Abr) do relatório Garantia e Peças por Modelo.*/
    private Double m04;
    
    /** Armazena o campo m05 (mês 05-Mai) do relatório Garantia e Peças por Modelo.*/
    private Double m05;
    
    /** Armazena o campo m06 (mês 06-Jun) do relatório Garantia e Peças por Modelo.*/
    private Double m06;
    
    /** Armazena o campo m07 (mês 07-Jul) do relatório Garantia e Peças por Modelo.*/
    private Double m07;
    
    /** Armazena o campo m08 (mês 08-Ago) do relatório Garantia e Peças por Modelo.*/
    private Double m08;
    
    /** Armazena o campo m09 (mês 09-Set) do relatório Garantia e Peças por Modelo.*/
    private Double m09;
    
    /** Armazena o campo m10 (mês 10-Out) do relatório Garantia e Peças por Modelo.*/ /** Armazena o campo m01 (mês 01-Jan) do relatório Garantia e Peças por Modelo.*/
    private Double m10;
    
    /** Armazena o campo m11 (mês 11-Nov) do relatório Garantia e Peças por Modelo.*/
    private Double m11;
    
    /** Armazena o campo m12 (mês 12-Dec) do relatório Garantia e Peças por Modelo.*/
    private Double m12;
    
    /** Armazena o campo listVlDolar (lista de valores do dolar) do relatório Garantia e Peças por Modelo.*/
    private List listVlDolar;
    
    /** Armazena o campo listVlAcmMobra (lista de valor acumulado de mão de obra) do relatório Garantia e Peças por Modelo.*/
    private List listVlAcmMobra;
    
    /** Armazena o campo listSubtotal (lista de valor do subtotal) do relatório Garantia e Peças por Modelo.*/
    private List listSubtotal;   
    
    /** Armazena o campo listRevisoes (lista de Detalhes da Revisão) do relatório Garantia e Peças por Modelo.*/
    private List listRevisoes; 
    
    /** Armazena o campo listTotal (lista de valor Total = Subtotal + Tot Revisões) do relatório Garantia e Peças por Modelo.*/
    private List listTotal;  
    
    /** Armazena o campo listVlFatur (lista de valor do Faturamento) do relatório Garantia e Peças por Modelo.*/
    private List listVlFatur;    
    
    /** Armazena o campo listQtdes (lista de qtde de moto, sg's e faturada) do relatório Garantia e Peças por Modelo.*/
    private List listQtdes;       
    
    /** Armazena o campo listGarVlFatur (lista de valor Garantia/Vl Faturado) do relatório Garantia e Peças por Modelo.*/
    private List listGarVlFatur;    
    
    /** Armazena o campo listRevVlFatur (lista de valor Total Revisões/Vl Faturado) do relatório Garantia e Peças por Modelo.*/
    private List listRevVlFatur;  
    
    /** Armazena o campo listTotVlFatur (lista de valor Total/Vl Faturado) do relatório Garantia e Peças por Modelo.*/
    private List listTotVlFatur;
    
    //	----[ Método Add ]---------------------------------------------------  

	/** Método construtor.
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
	
	/** Adiciona uma lista de valores do dólar no relatório Garantia e Peças por Modelo.
     * 
     *  @param listVlDolar
     *      listVlDolar a ser adicionado.
     *  
     */
    public void addListVlDolar(RelatorioGarantiaPecasModSubRepVO listVlDolar) {
    	
        this.listVlDolar.add( listVlDolar );
        
    }
    
	/** Adiciona uma lista de valores acumulados da mão de obra no relatório Garantia e Peças por Modelo.
     * 
     *  @param listVlAcmMobra
     *      listVlAcmMobra a ser adicionado.
     *  
     */
    public void addListVlAcmMobra(RelatorioGarantiaPecasModSubRepVO listVlAcmMobra) {
    	
        this.listVlAcmMobra.add( listVlAcmMobra );
        
    }
    
	/** Adiciona uma lista de valores do Subtotal no relatório Garantia e Peças por Modelo.
     * 
     *  @param listSubtotal
     *      listSubtotal a ser adicionado.
     *  
     */
    public void addListSubtotal(RelatorioGarantiaPecasModSubRepVO listSubtotal) {
    	
        this.listSubtotal.add( listSubtotal );
        
    }   
    
	/** Adiciona uma lista de valores das Revisões no relatório Garantia e Peças por Modelo.
     * 
     *  @param listRevisoes
     *      listRevisoes a ser adicionado.
     *  
     */
    public void addListRevisoes(RelatorioGarantiaPecasModSubRepVO listRevisoes) {
    	
        this.listRevisoes.add( listRevisoes );
        
    }     
    
	/** Adiciona uma lista de valores do Total no relatório Garantia e Peças por Modelo.
     * 
     *  @param listTotal
     *      listTotal a ser adicionado.
     *  
     */
    public void addListTotal(RelatorioGarantiaPecasModSubRepVO listTotal) {
    	
        this.listTotal.add( listTotal );
        
    }    

	/** Adiciona uma lista de valores do faturamento no relatório Garantia e Peças por Modelo.
     * 
     *  @param listVlFatur
     *      listVlFatur a ser adicionado.
     *  
     */
    public void addListVlFatur(RelatorioGarantiaPecasModSubRepVO listVlFatur) {
    	
        this.listVlFatur.add( listVlFatur );
        
    } 
    
	/** Adiciona uma lista de valores da qtde de moto, sg's e faturada no relatório Garantia e Peças por Modelo.
     * 
     *  @param listQtdes
     *      listQtdes a ser adicionado.
     *  
     */
    public void addListQtdes(RelatorioGarantiaPecasModSubRepVO listQtdes) {
    	
        this.listQtdes.add( listQtdes );
        
    } 
    
	/** Adiciona uma lista de valor Garantia/Vl Faturado no relatório Garantia e Peças por Modelo.
     * 
     *  @param listRevisoes
     *      listRevisoes a ser adicionado.
     *  
     */
    public void addListGarVlFatur(RelatorioGarantiaPecasModSubRepVO listGarVlFatur) {
    	
        this.listGarVlFatur.add( listGarVlFatur );
        
    }  
    
	/** Adiciona uma lista de valor Total Revisões/Vl Faturado no relatório Garantia e Peças por Modelo.
     * 
     *  @param listRevisoes
     *      listRevisoes a ser adicionado.
     *  
     */
    public void addListRevVlFatur(RelatorioGarantiaPecasModSubRepVO listRevVlFatur) {
    	
        this.listRevVlFatur.add( listRevVlFatur );
        
    }
    
	/** Adiciona uma lista de valor Total/Vl Faturado no relatório Garantia e Peças por Modelo.
     * 
     *  @param listTotVlFatur
     *      listTotVlFatur a ser adicionado.
     *  
     */
    public void addListTotVlFatur(RelatorioGarantiaPecasModSubRepVO listTotVlFatur) {
    	
        this.listTotVlFatur.add( listTotVlFatur );
        
    }


    //	----[ Métodos Getter ]---------------------------------------------------

	/** Método getter para a propriedade ano.
	 *
	 *  @return o valor atual de ano.
	 */
	public String getAno() {
		return ano;
	}

	/** Método getter para a propriedade listGarVlFatur.
	 *
	 *  @return o valor atual de listGarVlFatur.
	 */
	public List getListGarVlFatur() {
		return listGarVlFatur;
	}

	/** Método getter para a propriedade listQtdes.
	 *
	 *  @return o valor atual de listQtdes.
	 */
	public List getListQtdes() {
		return listQtdes;
	}

	/** Método getter para a propriedade listRevisoes.
	 *
	 *  @return o valor atual de listRevisoes.
	 */
	public List getListRevisoes() {
		return listRevisoes;
	}

	/** Método getter para a propriedade listRevVlFatur.
	 *
	 *  @return o valor atual de listRevVlFatur.
	 */
	public List getListRevVlFatur() {
		return listRevVlFatur;
	}

	/** Método getter para a propriedade listSubtotal.
	 *
	 *  @return o valor atual de listSubtotal.
	 */
	public List getListSubtotal() {
		return listSubtotal;
	}

	/** Método getter para a propriedade listTotal.
	 *
	 *  @return o valor atual de listTotal.
	 */
	public List getListTotal() {
		return listTotal;
	}

	/** Método getter para a propriedade listTotVlFatur.
	 *
	 *  @return o valor atual de listTotVlFatur.
	 */
	public List getListTotVlFatur() {
		return listTotVlFatur;
	}

	/** Método getter para a propriedade listVlAcmMobra.
	 *
	 *  @return o valor atual de listVlAcmMobra.
	 */
	public List getListVlAcmMobra() {
		return listVlAcmMobra;
	}

	/** Método getter para a propriedade listVlFatur.
	 *
	 *  @return o valor atual de listVlFatur.
	 */
	public List getListVlFatur() {
		return listVlFatur;
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

	/** Método getter para a propriedade modelo.
	 *
	 *  @return o valor atual de modelo.
	 */
	public String getModelo() {
		return modelo;
	}
	
	/** Método getter para a propriedade listVlDolar.
	 *
	 *  @return o valor atual de listVlDolar.
	 */
	public List getListVlDolar() {
		return listVlDolar;
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

	/** Obtém o valor atual de listGarVlFatur.
	 * 
	 *  @param listGarVlFatur 
	 *    O novo valor para listGarVlFatur.
	 */
	public void setListGarVlFatur(List listGarVlFatur) {
		this.listGarVlFatur = listGarVlFatur;
	}

	/** Obtém o valor atual de listQtdes.
	 * 
	 *  @param listQtdes 
	 *    O novo valor para listQtdes.
	 */
	public void setListQtdes(List listQtdes) {
		this.listQtdes = listQtdes;
	}

	/** Obtém o valor atual de listRevisoes.
	 * 
	 *  @param listRevisoes 
	 *    O novo valor para listRevisoes.
	 */
	public void setListRevisoes(List listRevisoes) {
		this.listRevisoes = listRevisoes;
	}

	/** Obtém o valor atual de listRevVlFatur.
	 * 
	 *  @param listRevVlFatur 
	 *    O novo valor para listRevVlFatur.
	 */
	public void setListRevVlFatur(List listRevVlFatur) {
		this.listRevVlFatur = listRevVlFatur;
	}

	/** Obtém o valor atual de listSubtotal.
	 * 
	 *  @param listSubtotal 
	 *    O novo valor para listSubtotal.
	 */
	public void setListSubtotal(List listSubtotal) {
		this.listSubtotal = listSubtotal;
	}

	/** Obtém o valor atual de listTotal.
	 * 
	 *  @param listTotal 
	 *    O novo valor para listTotal.
	 */
	public void setListTotal(List listTotal) {
		this.listTotal = listTotal;
	}

	/** Obtém o valor atual de listTotVlFatur.
	 * 
	 *  @param listTotVlFatur 
	 *    O novo valor para listTotVlFatur.
	 */
	public void setListTotVlFatur(List listTotVlFatur) {
		this.listTotVlFatur = listTotVlFatur;
	}

	/** Obtém o valor atual de listVlAcmMobra.
	 * 
	 *  @param listVlAcmMobra 
	 *    O novo valor para listVlAcmMobra.
	 */
	public void setListVlAcmMobra(List listVlAcmMobra) {
		this.listVlAcmMobra = listVlAcmMobra;
	}

	/** Obtém o valor atual de listVlFatur.
	 * 
	 *  @param listVlFatur 
	 *    O novo valor para listVlFatur.
	 */
	public void setListVlFatur(List listVlFatur) {
		this.listVlFatur = listVlFatur;
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

	/** Obtém o valor atual de modelo.
	 * 
	 *  @param modelo 
	 *    O novo valor para modelo.
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/** Obtém o valor atual de listVlDolar.
	 * 
	 *  @param listVlDolar 
	 *    O novo valor para listVlDolar.
	 */
	public void setListVlDolar(List listVlDolar) {
		this.listVlDolar = listVlDolar;
	}
    
    
    

}
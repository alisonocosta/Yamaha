/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......NumberUtils.java
 *   .: Cria��o.....03 de maio, 09:17
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "NumberUtils".
 */
package br.com.resource.infra.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/** Classe utilit�ria para formata��o de valores num�ricos
 *  
 *  @author Edson Luiz Sumensari
 */
public class NumberUtils {
	
	/** 
     * Locale Brasileiro 
     */ 
    private static final Locale BRAZIL = new Locale("pt","BR"); 
    
    /** 
     * S�mbolos especificos do Real Brasileiro 
     */ 
    private static final DecimalFormatSymbols REAL = new DecimalFormatSymbols(BRAZIL);
	
	/** 
	* Mascara de moeda para Real Brasileiro 
	*/ 
	public static final DecimalFormat DINHEIRO_REAL = new DecimalFormat("� ###,###,##0.00",REAL); 
	
	/** 
	* Mascara de decimal 
	*/ 
	public static final DecimalFormat DECIMAL = new DecimalFormat("########0.00",REAL); 
	
	/** 
	* Mascara de decimal com separa��o de milhar
	*/ 
	public static final DecimalFormat DECIMAL_MILHAR = new DecimalFormat("###,###,##0.00",REAL); 
	
	/** 
	* Mascara sem decimais com separa��o de milhar
	*/ 
	public static final DecimalFormat MILHAR = new DecimalFormat("###,###,##0",REAL); 
	
	
	/** Retorna o valor formatado para moeda REAL 
	 * 
	 * @param double valor
	 * @param  DecimalFormat moeda
	 * @return String
	 */
	public static String formatCurrency(double value){
		
		return DINHEIRO_REAL.format(value);
		
	} 
	
	/** Retorna o valor formatado para moeda REAL  sem o R$
	 * 
	 * @param double valor
	 * @param  DecimalFormat moeda
	 * @return String
	 */
	public static String formatNumberCurrency(double value){
		
		return DECIMAL.format(round(value,2));
		
	} 
	
	/** Retorna o valor formatado para moeda REAL  sem o R$ e com separa��o de milhar
	 * 
	 * @param double valor
	 * @return String
	 */
	public static String formatNumberCurrencyMil(double value){
		
		return DECIMAL_MILHAR.format(round(value,2));
		
	} 
	
	/** Retorna o valor formatado para N�mero com separa��o de milhar e sem decimais
	 * 
	 * @param double valor
	 * @return String
	 */
	public static String formatNumberMil(double value){
		
		return MILHAR.format(round(value,0));
		
	} 
	
	/** Retorna o valor truncado no numero de decimais especificadas
	 * 
	 * @param value
	 * @param decimais
	 * @return double
	 */
	public static double trunc(double value, int decimais) {
		double p = Math.pow(10, decimais);
		return Math.floor(value * p) / p; 
	}

	/** Arredonda um valor com o numero de decimais especificado
	 * 
	 * @param value
	 * @param decimais
	 * @return
	 */
	public static double round(double value, int decimais) {
		double p = Math.pow(10, decimais);
		return Math.round(value * p) / p; 
	}

	
	public static double converteStringParaDouble(String valor) throws NumberFormatException {		
		 return Double.parseDouble(valor); 
	}

}

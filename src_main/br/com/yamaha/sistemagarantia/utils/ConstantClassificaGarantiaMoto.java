/**
 * 
 */
package br.com.yamaha.sistemagarantia.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Edson Luiz Sumensari
 */
public class ConstantClassificaGarantiaMoto {
	
	public static String TIPO_LOTE 				= "LOTE";
	public static String TIPO_GARANTIA 			= "GARANTIA";
	public static String TIPO_CHASSI 			= "CHASSI";
	public static String TIPO_CONCESSIONARIA 	= "CONCESSIONARIA";
	public static String TIPO_MODELO 			= "MODELO";
	public static String TIPO_PECA_CAUSADORA	= "PEÇA CAUSADORA";
	public static String KEY_PECA_CAUSADORA		= "K_PECA_CAUS";
	public static String TIPO_POR_LINHA 		= "LINHA";
	
	public static Long LINHA_MOTO 		= new Long(1);
	public static Long LINHA_NAUTICA 	= new Long(2);
		
	
	public static Map listValuesSearch() {
		
		Map listOptionsSearch  = new HashMap();
		
		listOptionsSearch.put(TIPO_LOTE, TIPO_LOTE);
		listOptionsSearch.put(TIPO_GARANTIA, TIPO_GARANTIA);
		listOptionsSearch.put(TIPO_CHASSI, TIPO_CHASSI);
		listOptionsSearch.put(TIPO_CONCESSIONARIA, TIPO_CONCESSIONARIA);
		listOptionsSearch.put(TIPO_MODELO, TIPO_MODELO);
		listOptionsSearch.put(KEY_PECA_CAUSADORA, TIPO_PECA_CAUSADORA);
				
		return listOptionsSearch;
	}
}

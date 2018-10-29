/**
 * 
 */
package br.com.yamaha.sistemagarantia.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Edson Luiz Sumensari
 */
public class ConstantAnaliseGarantia {
	
	public static String TIPO_LOTE 				= "LOTE";
	public static String TIPO_GARANTIA 			= "GARANTIA";
	public static String TIPO_CHASSI 			= "CHASSI";
	public static String TIPO_CONCESSIONARIA 	= "CONCESSIONARIA";
	public static String TIPO_POR_LINHA 		= "LINHA";
	public static String TIPO_POR_REPRESENTANTE	= "REPRESENTANTE";
	
	
	public static Map listValuesSearch(Long idTipo) {
		
		Map listOptionsSearch  = new HashMap();
		
		listOptionsSearch.put(TIPO_LOTE, TIPO_LOTE);
		listOptionsSearch.put(TIPO_GARANTIA, TIPO_GARANTIA);
		listOptionsSearch.put(TIPO_CHASSI, TIPO_CHASSI);
		listOptionsSearch.put(TIPO_CONCESSIONARIA, TIPO_CONCESSIONARIA);
		if(!(new Long(1)).equals(idTipo))
			listOptionsSearch.put(TIPO_POR_REPRESENTANTE, TIPO_POR_REPRESENTANTE);
				
		return listOptionsSearch;
	}
}

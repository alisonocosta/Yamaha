/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Infraestrutura para aplicativos Java
 *   .: Objeto......StringUtils.java
 *   .: Cria��o.....22 de mar�o, 12:47
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descri��o...M�todos auxiliares para Strings.
 */
package br.com.resource.infra.utils;

import java.util.Map;

/** Fornece m�todos utilit�rios para tratamento de Strings.
 * 
 *  @author Thiago Uriel M. Garcia
 */
public class StringUtils {

    /** Utilizado para ambientes com JVM < 1.5
     *  <p>
     *  O m�todo <code>replace</code> para Strings foi adicionado na 
     *  JVM 1.5, portanto, para possibilitar esta facilidade, uma
     *  implementa��o trivial do m�todo foi criada para JVMs anteriores.
     *  
     *  @param str
     *      String que ser� pesquisada em busca do padr�o a ser alterado.
     *  @param pattern
     *      String contendo o padr�o a ser pesquisado.
     *  @param replace
     *      String contendo a substitui��o a ser feita quando o padr�o
     *      for encontrado dentro da String de origem.
     *  
     *  @return
     *      A String alterada, ou a original caso nenhuma ocorr�ncia
     *      do padr�o tenha sido encontrada.
     */
	public static String replace(String str, String pattern, String replace) {
        
        int s = 0;
        int e = 0;
        StringBuffer result = new StringBuffer();
    
        while ( (e = str.indexOf(pattern, s) ) >= 0) {
            
            result.append(str.substring(s, e));
            result.append(replace);
            s = e + pattern.length();
            
        }
        
        result.append(str.substring(s));
        return result.toString();
        
    }	

    /** M�todo de apoio que obt�m uma String do mapa, tratando-a
     *  em caso de valor nulo ou convertendo o objeto caso exista.
     *  
     *  @param key
     *      Chave a ser pesquisada no mapa de objetos.
     *  @param reportData
     *      Mapa de objetos.
     *  
     *  @return
     *      O valor do servi�o relacionado ao mapa, convertido para
     *      <code>String</code> ou nulo caso n�o exista.
     */
    public static String safeRetrieve(String key, Map reportData) {
        
        Object value = reportData.get( key );
        if ( value != null )
            return value.toString();
        
        else
            return null;
        
    } 
 
    /** Formata um valor de acordo com uma m�scara.
     *  
     *  @param valor
     *      <code>String</code> Valor  a ser formatado.
     *  @param mascara
     *      <code>String</code> M�scara a ser aplicada na formata��o.
     *  
     *  @return
     *      O valor formatado com a devida m�scara.
     */
    public static String formatar( String valor, String mascara ) {            
    	String dado = "";              // remove caracteres nao numericos        
    	for ( int i = 0; i < valor.length(); i++ )  {            
    		char c = valor.charAt(i);            
    		if ( Character.isDigit( c ) ) { 
    			dado += c;
    		}
    	}        
    	int indMascara = mascara.length();        
    	int indCampo = dado.length();        
    	for ( ; indCampo > 0 && indMascara > 0; ) {            
    		if ( mascara.charAt( --indMascara ) == '#' ) { 
    			indCampo--; 
    		}        
    	}        
    	String saida = "";        
    	for ( ; indMascara < mascara.length(); indMascara++ ) {                
    		saida += ( ( mascara.charAt( indMascara ) == '#' ) ? dado.charAt( indCampo++ ) : mascara.charAt( indMascara ) );        
    	}            
    	return saida;    
    }        
    
    /** Formata um valor para o formato de CPF.
     *  
     *  @param cpf
     *      <code>String</code> Valor  a ser formatado.
     *  
     *  @return
     *      O valor formatado com a m�scara de CPF
     */
    public static String formatarCpf( String cpf ) {        
    	while( cpf.length() < 11 ) {            
    		cpf = "0" + cpf;        
    	}        
    	return formatar( cpf, "###.###.###-##" );    
    }        
    
    /** Formata um valor para o formato de CNPJ.
     *  
     *  @param CNPJ
     *      <code>String</code> Valor  a ser formatado.
     *  
     *  @return
     *      O valor formatado com a m�scara de CNPJ
     */
    public static String formatarCnpj( String cnpj ) {        
    	while( cnpj.length() < 14 ) {            
    		cnpj = "0" + cnpj;        
    	}        
    	return formatar( cnpj,"##.###.###/####-##" );    
    }
    
}
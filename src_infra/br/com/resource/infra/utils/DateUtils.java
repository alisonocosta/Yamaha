/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Infraestrutura para aplicativos Java
 *   .: Objeto......DateUtils.java
 *   .: Criação.....22 de fevereiro, 22:27
 *   .: Autor.......Thiago Uriel M. Garcia
 *                  Edson Luiz Sumensari
 *   .: Descrição...Métodos utilitários para datas.
 */
package br.com.resource.infra.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/** Contém métodos utilitários para tratamento de datas.
 * 
 *  @author Thiago Uriel M. Garcia
 *  @author Edson Luiz Sumensari
 */
public final class DateUtils {

	/** Constante.
	 *  <p>
	 *  Determina o formato de data padrão a ser aplicadas em 
	 *  datas de padrão não informado.
	 */
	public static final String DEFAULT_MASK = "dd/MM/yyyy";

	/** Constante.
	 *  <p>
	 *  Idioma padrão para localização dos formatos de data.
	 */
	public static final String DEFAULT_LOCALE_LANGUAGE = "pt";

	/** Constante.
	 *  <p>
	 *  País padrão para localização dos formatos de data.
	 */
	public static final String DEFAULT_LOCALE_COUNTRY = "BR";
	
	/** Obtém um <code>Locale</code> padrão pt-BR.
	 * 
	 *  @return
	 *  	Um objeto <code>Locale</code> padrão.
	 */
	private static Locale getLocale() {
		
		return new Locale(
			DEFAULT_LOCALE_LANGUAGE,
			DEFAULT_LOCALE_COUNTRY
		);
		
	}
	
	/** Retorna a quantidade de dias de um intervalo de datas 
	 *  Locale "pt_BR"
	 *  Esta rotina conta apenas os dias de final de semana (Sábado e Domingo) 
	 *  como não úteis
	 *  
	 * @param startDate
	 * 		<code>Date</code> Data Inicial do intervalo
	 * 
	 * @param finalDate
	 * 		<code>Date</code> Data Final do intervalo
	 * 
	 * @return int
	 * 		<code>int</code> Quantidade de dias do intervalo
	 */
	public static int getDays( Date startDate, Date finalDate) {
		
		//System.out.println("DateUtils - getDays - startDate:" + startDate + " - finalDate:" + finalDate);
				
		GregorianCalendar gStartDate = new GregorianCalendar(getLocale());
		GregorianCalendar gFinalDate = new GregorianCalendar(getLocale());
		
		gStartDate.setTime(startDate);
		gFinalDate.setTime(finalDate);
		
		int diferenca = DateUtils.dataDiff( gStartDate.getTime(), gFinalDate.getTime());
		
		//System.out.println("DateUtils - getDays - Diferenca:" + diferenca);
		
		return diferenca;
	}
		
	/** Retorna a quantidade de dias úteis de um intervalo de datas 
	 *  Locale "pt_BR"
	 *  Esta rotina conta apenas os dias de final de semana (Sábado e Domingo) 
	 *  como não úteis
	 *  
	 * @param startDate
	 * 		<code>Date</code> Data Inicial do intervalo
	 * 
	 * @param finalDate
	 * 		<code>Date</code> Data Final do intervalo
	 * 
	 * @return daysUtils
	 * 		<code>int</code> Quantidade de dias Úteis
	 */
	public static int daysUtils( Date startDate, Date finalDate) {
		
		int daysUtils = 0;
		
		GregorianCalendar gStartDate = new GregorianCalendar(getLocale());
		GregorianCalendar gFinalDate = new GregorianCalendar(getLocale());
		
		gStartDate.setTime(startDate);
		gFinalDate.setTime(finalDate);
		
		int dif = DateUtils.dataDiff( gStartDate.getTime(), gFinalDate.getTime());
		
		if ( dif > 0 ) {
		
			for (int i = 1 ; i <= dif ; i++ ) {
			
				if ( Calendar.SATURDAY != gStartDate.get(Calendar.DAY_OF_WEEK) && Calendar.SUNDAY != gStartDate.get(Calendar.DAY_OF_WEEK) )
					daysUtils = daysUtils + 1;
				
				gStartDate.add(Calendar.DAY_OF_MONTH, 1);
				
			}
			
		}
		
		return daysUtils;
	}
	
	/** Retorna a quantidade de dias úteis de um intervalo de datas e list de feriados 
	 *  Locale "pt_BR"
	 *  
	 *  
	 * @param startDate
	 * 		<code>Date</code> Data Inicial do intervalo
	 * 
	 * @param finalDate
	 * 		<code>Date</code> Data Final do intervalo
	 * 
	 * @param holidays
	 * 		<code>List</code> Lista de feriados - objetos <code>Date</code>.
	 * 
	 * @return daysUtils
	 * 		<code>int</code> Quantidade de dias Úteis
	 */
	public static int daysUtils( Date startDate, Date finalDate, List holidays ) {
		
		int      daysUtils = 0;		
		Date     holiday;
		boolean  isWeekend = false;
		boolean  isHoliday = false;
		
		GregorianCalendar gStartDate = new GregorianCalendar(getLocale());
		GregorianCalendar gFinalDate = new GregorianCalendar(getLocale());
		GregorianCalendar gHoliday   = new GregorianCalendar(getLocale());
		
		gStartDate.setTime(startDate);
		gFinalDate.setTime(finalDate);
		
		int dif = DateUtils.dataDiff( gStartDate.getTime(), gFinalDate.getTime());
		
		//System.out.println("Diferença de dias entre duas datas:" + dif);
		
		if ( dif > 0 ) {
		
			for (int i = 1 ; i <= dif ; i++ ) {
				
				isHoliday = false;
				isWeekend = ( Calendar.SATURDAY == gStartDate.get(Calendar.DAY_OF_WEEK)) || ( Calendar.SUNDAY   == gStartDate.get(Calendar.DAY_OF_WEEK) );
				
				//System.out.println("StartDate:" + gStartDate.getTime() + " - é útil:" + isWeekend + " - Dia:" + gStartDate.get(Calendar.DAY_OF_WEEK));
				
				// Se a coleção de feriados estive preenchida, verificamos ela 
				if ( holidays != null ) {
					
					Iterator iter = holidays.iterator();
					while ( iter.hasNext() ) { 
						
						holiday = (Date) iter.next();
						gHoliday.setTime(holiday);
						
						if ( DateUtils.equalDate( gStartDate.getTime(), gHoliday.getTime()) ) {
							
							isHoliday = true;
							
						}
					}
					
				}
				
				//System.out.println("Final de Semana:" + isWeekend + " Feriado:" + isHoliday + " StartDate:" + gStartDate.getTime() +  " - Dia:" + gStartDate.get(Calendar.DAY_OF_WEEK));
				
				if ( !isWeekend && !isHoliday ) 
					daysUtils = daysUtils + 1;
				
				gStartDate.add(Calendar.DAY_OF_MONTH, 1);				
			}			
		}
		
		//System.out.println("Diferença total:" + daysUtils);
		
		return daysUtils;
	}
	
	/** Aplica uma máscara a uma data informada.
	 * 
	 *  @param date
	 *  	Data que receberá a máscara.
	 *  @param mask
	 *  	Máscara a ser aplicada.
	 *  
	 *  @return
	 *  	Uma String representando a data com a máscara
	 *  	aplicada.
	 */
	public static String applyMask(Date date, String mask) {
		
		if ( date != null ) {
			
			SimpleDateFormat formater = new SimpleDateFormat( mask, getLocale() );
			return formater.format( date );
			
		} else {
			
			return new String("");
			
		}
			
	}

	/** Aplica uma máscara padrão a uma data informada.
	 * 	<p>
	 *  A máscara padrão é <b>dd/MM/yyyy</b> e está armazenada
	 *  na constante <code>DEFAULT_MASK</code>. 
	 * 
	 *  @param date
	 *  	Data que receberá a máscara.
	 *  
	 *  @return
	 *  	Uma String representando a data com a máscara
	 *  	aplicada.
	 */
	public static String applyMask(Date date) {
		
		return applyMask( date, DEFAULT_MASK );
		
	}
	
    /** Fornece uma instância segura de uma data.
     *  <p>
     *  Se o objeto "date" for do tipo <code>String</code>
     *  aplica um parse e retorna seu formato em data.
     *  <p>
     *  Se o objeto "date" for do tipo <code>Date</code> (java.util)
     *  aplica um cast e retorna o objeto.
     *  <p>
     *  Se o objeto "date" for do tipo <code>Date</code> (java.sql)
     *  aplica converte para uma date (java.util) e retorna.
     *  
     *  @param date
     *      <code>Object</code> representando a data. Deve
     *      ser do tipo <code>Date</code> (java.util), <code>Date</code>
     *      (java.sql) ou <code>String</code>.
     *       
     *  @return
     *      Uma data de acordo com o objeto recebido.
     */
    public static Date secureSet(Object date) {
        
        if ( date instanceof String ) {
            
            try {
                
                return DateUtils.parseDate( date.toString() );
                
            } catch ( ParseException pExp) {
                return null;
            }
        
        } else if ( date instanceof java.sql.Date ) { 
            
            try {
                
                java.sql.Date sqlDate = (java.sql.Date) date;
                return DateUtils.parseDate( sqlDate.toString() );
                
            } catch ( ParseException pExp) {
                return null;
            }            
            
        } else if ( date instanceof java.util.Date ) {
            
            return (java.util.Date) date;
            
        } else {
            
            return null; 
            
        }
        
    }
    
	/** Gera uma data a partir de uma <code>String</code>.
	 * 
	 *  @param source
	 *  	String de origem a ser convertida.
	 *  
	 *  @return
	 *  	Data resultante da conversão, caso a String
	 *  	recebida seja válida.		
	 *  
	 *  @throws ParseException
	 *  	Se houverem problemas de conversão, esta
	 *  	exception será lançada.
	 */
	public static Date parseDate(String source) throws ParseException {
		
		if (source != null && !source.equals("") ) {
			
			SimpleDateFormat formater = new SimpleDateFormat( DEFAULT_MASK, getLocale() );
			formater.setLenient(false);	 
			
			return formater.parse( source );
			
		} else {
		
			return null;
			
		}
		
	}
	
	/** Converte uma data fornecida para uma data SQL.
	 * 
	 *  @param date
	 *  	Data a ser convertida.
	 *  
	 *  @return
	 *  	Data convertida para um formato SQL.
	 */
	public static java.sql.Date toSQLDate(java.util.Date date) {
		
		if (date != null)
			return new java.sql.Date( date.getTime() );
				
		else
			return null;
		
	}
	
	/** Converte uma String contendo uma data, para um formado SQL.
	 * 
	 *  @param date
	 *  	<code>String</code> contendo a data a ser convertida.
	 *  
	 *  @return
	 *  	Data convertida para um formato SQL.
	 */
	public static java.sql.Date toSQLDate(String date) {
		
		try {
			
			if (date != null) {
				java.util.Date myDate = parseDate( date );
				return new java.sql.Date( myDate.getTime() );
					
			} else
				return null;
			
		} catch (ParseException e) {
			
			return null;
			
		}
		
	}	

    /** Retorna uma data convertida para um padrão.
     *  Os padrões mais comuns são:
     *  <pre>
     *  +-------+---------------------+<br>
     *  | Letra | Componente          |<br>
     *  +-------+---------------------+<br>
     *  | y     | Ano                 |<br>
     *  | M     | Mês do ano          |<br>
     *  | d     | Dia do mês          |<br>
     *  | E     | Dia da semana       |<br>
     *  | a     | AM/PM               |<br>
     *  | H     | Hora (0-23)         |<br>
     *  | k     | Hora (1-24)         |<br>
     *  | K     | Hora (AM/PM, 0-11)  |<br>
     *  | h     | Hora (AM/PM, 1-12)  |<br>
     *  | m     | Minuto na hora      |<br>
     *  | s     | Segundo no minuto   |<br>
     *  | z     | Time zone           |<br>
     *  +-------+---------------------+<br>
     *  </pre>   
     * @param String  strDate
     * @param pattern String 
     * @return Date 
     */
    public static Date format( String strDate, String pattern ) {
        
    	try {
    		
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            
            return formatter.parse(strDate);
            
        } catch (Exception e) {
        	
            return null;   
            
        }
        
    }
	
    /** Este método retorna o resultado da comparação
     * 
     *     
     * @param date1 Date
     * 
     * @param date2 Date
     * 
     * @return int  0 para datas iguais 
     * 				Um valor Menor que 0 se a primeira data for anterior a segunda data
     * 				Uma maior que que 0 se a primeira data for posterior a segunda data 
     */
    public static int compareDate(Date date1, Date date2) {
                
        return date1.compareTo(date2);
    }
    
    /** Este método retorna o resultado da comparação entre duas datas
     * 
     *     
     * @param String strDate1
     * 
     * @param String StrDate2
     * 
     * @return int  0 para datas iguais 
     * 				Um valor Menor que 0 se a primeira data for anterior a segunda data
     * 				Uma maior que que 0 se a primeira data for posterior a segunda data 
     */
    public static int compareDate( String strDate1, String strDate2 ) {
        
    	Date date1 = format( strDate1, "dd-MMM-yyyy" );
    	Date date2 = format( strDate2, "dd-MMM-yyyy" );
    	
        return compareDate(date1, date2);
    }
	
    
    
    /** Este método é utilizado para comparar a igualdade entre duas datas
     * 
     * @param  Date date1
     * 
     * @param  Date date2
     * 
     * @return boolean  True para datas iguas
     */
    public static boolean equalDate(Date date1, Date date2) {
        return compareDate(date1, date2) == 0;
    }    
    
    
    /** Este Método é utilizado para comparar se uma data é posterior a outra
     * 
     * @param date1 Date
     * 
     * @param date2 Date
     * 
     * @return boolean True para primeira data posterior a segunda
     */
    public static boolean afterDate(Date date1, Date date2) {
        return compareDate(date1, date2) > 0;
    }    
    
    
    /** Este Método é utilizado para comparar se uma data é anterior a outra
     * 
     * @param date1 Date
     * 
     * @param date2 Date
     * 
     * @return boolean True para primeira data anterior a segunda data
     */
    public static boolean beforeDate(Date date1, Date date2) {
        return compareDate(date1, date2) < 0;
    }	
    
    /**
     * Método para comparar as das e retornar o numero de dias de diferença entre elas
     *
     * Compare two date and return the difference between them in days.
     *
     * @param dataLow The lowest date
     * @param dataHigh The highest date
     *
     * @return int
     */
    public static int dataDiff(Date dataLow, Date dataHigh) {

        GregorianCalendar startTime = new GregorianCalendar();
        GregorianCalendar endTime = new GregorianCalendar();
        
        GregorianCalendar curTime = new GregorianCalendar();
        GregorianCalendar baseTime = new GregorianCalendar();

        startTime.setTime(dataLow);
        endTime.setTime(dataHigh);
        
        int dif_multiplier = 1;
        
        // Verifica a ordem de inicio das datas
        if ( dataLow.compareTo( dataHigh ) < 0 ) {
        	
            baseTime.setTime(dataHigh);
            curTime.setTime(dataLow);
            dif_multiplier = 1;
            
        } else {
        	
            baseTime.setTime(dataLow);
            curTime.setTime(dataHigh);
            dif_multiplier = -1;
            
        }
        
        int result_years = 0;
        int result_months = 0;
        int result_days = 0;

        // Para cada mes e ano, vai de mes em mes pegar o ultimo dia para import acumulando
        // no total de dias. Ja leva em consideracao ano bissesto
        while( curTime.get(GregorianCalendar.YEAR) < baseTime.get(GregorianCalendar.YEAR) ||
               curTime.get(GregorianCalendar.MONTH) < baseTime.get(GregorianCalendar.MONTH)  ) {
            
            int max_day = curTime.getActualMaximum( GregorianCalendar.DAY_OF_MONTH );
            result_months += max_day;
            curTime.add(GregorianCalendar.MONTH, 1);
            
        }
        
        // Marca que é um saldo negativo ou positivo
        result_months = result_months * dif_multiplier;
        
        
        // Retorna a diferenca de dias do total dos meses
        result_days += (endTime.get(GregorianCalendar.DAY_OF_MONTH) - startTime.get(GregorianCalendar.DAY_OF_MONTH));
        
        return result_years + result_months + result_days;
    }
	
    /** Adiciona meses a uma data composta por mês e ano
     * 
     *  @param startYear
     *  @param startMonth Utilizar constantes de Calendar.
     *  @param monthsToAdd Para subtrair meses, informar um valor negativo.
     *  @return
     */
    public static Date addMonth(int startYear, int startMonth, int monthsToAdd) {
        
        Calendar calendar = new GregorianCalendar(startYear, startMonth, 1);
        calendar.add(Calendar.MONTH, monthsToAdd);
        
        return calendar.getTime();
        
    }    
    
    /** Cria uma data a partir de valores int para mês e ano.
     * 
     * @param startYear
     * @param startMonth
     * @return
     */
    public static Date getDate(int startYear, int startMonth) {
        
        Calendar calendar = new GregorianCalendar(startYear, startMonth, 1);
        return calendar.getTime();
        
    }
}
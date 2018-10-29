/* Resource Inform�tica
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Infraestrutura para aplicativos Java
 *   .: Objeto......ReportConstants.java
 *   .: Cria��o.....01 agosto, 12:03
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descri��o...Constantes para relat�rios.
 */
package br.com.resource.infra.core.constants;

/** Constantes para relat�rios.
 * 
 *  @author Thiago Uriel M. Garcia
 */
public class ReportConstants {

    /** Constante.
     *  <br>
     *  Indica o nome do par�metro que cont�m os dados do relat�rio.
     */
    public static final String REPORT_REQUEST_DATA = "report.data";
	
    /** Constante.
     *  <br>
     *  Indica o nome do par�metro que armazena o template Jasper utilizado.
     */
    public static final String REPORT_TEMPLATE = "report.template";

    /** Constante.
     *  <br>
     *  Indica o nome do par�metro que determina se existem subrelat�rios.
     */
    public static final String REPORT_MAIN = "report.main";
    
    /** Constante.
     *  <br>
     *  Indica o nome do par�metro que determina o tipo de resultado.
     */
    public static final String REPORT_DEFAULT_MEDIA = "report.media";

    /** Constante.
     *  <br>
     *  Indica o nome do par�metro que determina o tipo de resultado como PDF.
     */
    public static final String REPORT_DEFAULT_MEDIA_PDF = "PDF";
    
    /** Constante.
     *  <br>
     *  Indica o nome do par�metro que determina o tipo de resultado como HTML.
     */
    public static final String REPORT_DEFAULT_MEDIA_HTML = "HTML";
    
    /** Constante.
     *  <br>
     *  Indica o nome do par�metro que determina o tipo de resultado como CSV.
     */
    public static final String REPORT_DEFAULT_MEDIA_CSV = "CSV";    
    
    /** Constante.
     *  <br>
     *  Indica o nome do par�metro que determina o objeto nos resultados.
     */
    public static final String REPORT_RESULT_OBJECT_TYPE = "report.object";
    
    /** Constante.
     *  <br>
     *  Indica o nome do par�metro que armazena o conte�do.
     */
    public static final String REPORT_CONTENTS = "report.contents";    

    /** Constante.
     *  <br>
     *  Indica o nome do par�metro que armazena os par�metros.
     */
    public static final String REPORT_PARAMETERS = "report.parameters";    
    
}
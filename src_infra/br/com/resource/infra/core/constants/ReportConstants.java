/* Resource Informática
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Infraestrutura para aplicativos Java
 *   .: Objeto......ReportConstants.java
 *   .: Criação.....01 agosto, 12:03
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Constantes para relatórios.
 */
package br.com.resource.infra.core.constants;

/** Constantes para relatórios.
 * 
 *  @author Thiago Uriel M. Garcia
 */
public class ReportConstants {

    /** Constante.
     *  <br>
     *  Indica o nome do parâmetro que contém os dados do relatório.
     */
    public static final String REPORT_REQUEST_DATA = "report.data";
	
    /** Constante.
     *  <br>
     *  Indica o nome do parâmetro que armazena o template Jasper utilizado.
     */
    public static final String REPORT_TEMPLATE = "report.template";

    /** Constante.
     *  <br>
     *  Indica o nome do parâmetro que determina se existem subrelatórios.
     */
    public static final String REPORT_MAIN = "report.main";
    
    /** Constante.
     *  <br>
     *  Indica o nome do parâmetro que determina o tipo de resultado.
     */
    public static final String REPORT_DEFAULT_MEDIA = "report.media";

    /** Constante.
     *  <br>
     *  Indica o nome do parâmetro que determina o tipo de resultado como PDF.
     */
    public static final String REPORT_DEFAULT_MEDIA_PDF = "PDF";
    
    /** Constante.
     *  <br>
     *  Indica o nome do parâmetro que determina o tipo de resultado como HTML.
     */
    public static final String REPORT_DEFAULT_MEDIA_HTML = "HTML";
    
    /** Constante.
     *  <br>
     *  Indica o nome do parâmetro que determina o tipo de resultado como CSV.
     */
    public static final String REPORT_DEFAULT_MEDIA_CSV = "CSV";    
    
    /** Constante.
     *  <br>
     *  Indica o nome do parâmetro que determina o objeto nos resultados.
     */
    public static final String REPORT_RESULT_OBJECT_TYPE = "report.object";
    
    /** Constante.
     *  <br>
     *  Indica o nome do parâmetro que armazena o conteúdo.
     */
    public static final String REPORT_CONTENTS = "report.contents";    

    /** Constante.
     *  <br>
     *  Indica o nome do parâmetro que armazena os parâmetros.
     */
    public static final String REPORT_PARAMETERS = "report.parameters";    
    
}
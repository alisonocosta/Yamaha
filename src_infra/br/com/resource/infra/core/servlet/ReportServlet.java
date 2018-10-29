/* Resource Informática
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Infraestrutura para aplicativos Java
 *   .: Objeto......ReportServlet.java
 *   .: Criação.....01 de agosto, 11:34
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Servlet que recebe requisições de relatório.
 */
package br.com.resource.infra.core.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRCsvExporterParameter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import br.com.resource.infra.core.constants.ReportConstants;
import br.com.resource.infra.exception.ReportException;
import br.com.resource.infra.utils.StringUtils;
import br.com.resource.infra.view.report.ReportDataSource;

/** Servlet que trata requisições de relatório e junta
 *  os datasources com os templates, devolvendo ao usuário
 *  a visão final.
 *  
 *  @author Thiago Uriel M. Garcia
 */
public class ReportServlet extends HttpServlet {

    //----[ Constantes e atributos da classe ]--------------------------------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;    
    
    /** Contexto da aplicação. */
    protected ServletContext application = null;
    
    //----[ Métodos públicos ]------------------------------------------------------    
    
    /** Inicializa o Servlet.
     *  <p>
     *  Este método é chamado pelo servidor de aplicações quando está
     *  carregando os servlets definidos em <i>web.xml</i>.
     *  
     *  @param config
     *      Configuração do servlet.
     */
    public void init(ServletConfig config) throws ServletException {
        
        super.init(config);
        this.application = getServletContext();
        
    }    
    
    /** Método principal de serviço, chamado por requisições
     *  <code>POST</code> e <code>GET</code>.
     *  
     *  @param request
     *      Requisição recebida pelo <i>Servlet</i>.
     *  @param response
     *      Resposta sendo gerada até então.
     *      
     *  @throws ServletException
     *  @throws IOException
     */
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        try {
            
            Map reportData = retrieveReportData( request );
            
            if ( reportData != null ) {
                
                // Aqui obtemos os dados mais básicos do relatório, convertidos em String
                // para que possamos iniciar a conversão dos dados.
                String jasperTemplate = StringUtils.safeRetrieve( ReportConstants.REPORT_TEMPLATE, reportData ); 
                String defaultMedia   = StringUtils.safeRetrieve( ReportConstants.REPORT_DEFAULT_MEDIA, reportData );
                Locale locale         = reportData.get(JRParameter.REPORT_LOCALE) == null ? null : (Locale)reportData.get(JRParameter.REPORT_LOCALE);
                
                if ( jasperTemplate != null && defaultMedia != null ) {
                    
                    // Obtemos a lista de objetos e criamos o datasource.
                    // Se a lista de objetos estiver preenchida, atribuimos estes objetos
                    // ao datasource criado. Caso contrário, criamos uma lista vazia
                    // e o relatório será gerado sem erros, porém em branco.
                    ReportDataSource reportDS = new ReportDataSource();
                    Object contents = safeRetrieveContents( reportData );
                    
                    if ( contents != null ) {
                    	
                    	if ( contents instanceof List ) {
                    		
                    		reportDS.setObjectCollection( (List)contents );
                    		
                    	} else if ( contents instanceof Map ) {
                    		
                    		reportDS.setObjectCollection( (List)((Map)contents).get( ReportConstants.REPORT_MAIN ) );
                    		reportDS.setSubreportFields((Map)contents);
                    		
                    	}
                    	
                    } else {
                    	
                        reportDS.setObjectCollection( new ArrayList() );
                        
                    }
                        
                    // Obtemos o arquivo de template do relatório e carregamos.
                    File reportFile = new File( application.getRealPath( jasperTemplate ) );
                    
                    // Criamos uma resposta baseada no tipo de conteúdo solicitado.
                    if ( defaultMedia.equalsIgnoreCase( ReportConstants.REPORT_DEFAULT_MEDIA_PDF ) ) {
                        
                        Map params = safeRetrieveParameters( reportData );
                        
                        if ( locale != null )
                            params.put(JRParameter.REPORT_LOCALE, locale);
                        
                    	response.setContentType("application/pdf");
                        // Criamos um array de bytes contendo o relatório gerado
                        // pela API, com os parâmetros e resultados.
                        byte[] bytes = JasperRunManager.runReportToPdf( reportFile.getPath()
                                                                      , params
                                                                      , reportDS );
                        
                        // E escrevemos a resposta no formato correto.
                        ServletOutputStream ouputStream = response.getOutputStream();
                        ouputStream.write(bytes, 0, bytes.length);
                        ouputStream.flush();
                        ouputStream.close();
                        
                    } else if ( defaultMedia.equalsIgnoreCase( ReportConstants.REPORT_DEFAULT_MEDIA_HTML ) ) {
                        
                        Map params = safeRetrieveParameters( reportData );
                        if ( locale != null )
                            params.put(JRParameter.REPORT_LOCALE, locale);
                        
                    	PrintWriter out = response.getWriter();
                    	response.setContentType("text/html");
                    	
                        // Utilizamos a API para gerar o resultado populado do relatório.
                    	JasperPrint jasperPrint = JasperFillManager.fillReport( reportFile.getPath()
                                                                              , params
                                                                              , reportDS );
                    	
                    	// Criamos arquivo físico no sistema utilizando como base
                    	// o jasper populado.
                        String filename = jasperPrint.getName() + System.currentTimeMillis() + ".html";
                        File   destFile = new File( reportFile.getParent(), filename );
                        
                        // Criamos um exporter e cadastramos os dados como parâmetros.
                        // Em seguida solicitamos sua exportação. Isso exportará os dados
                        // do relatório.
                        JRHtmlExporter exporter = new JRHtmlExporter();
                    	exporter.setParameter( JRExporterParameter.JASPER_PRINT, jasperPrint );
                        exporter.setParameter( JRExporterParameter.OUTPUT_FILE_NAME, destFile.toString() );                    	
                        exporter.exportReport();

                        // Agora devemos ler o conteúdo do arquivo gerado
                        // e exbir na tela.
                        BufferedReader in = new BufferedReader( new FileReader( destFile.toString() ) );
                        String str;
                        while ( (str = in.readLine()) != null ) {
                            out.println(str);
                        }
                        in.close();

                        // E finalmente, removemos o arquivo gerado.
                        destFile.delete();
                        
                    } else if ( defaultMedia.equalsIgnoreCase( ReportConstants.REPORT_DEFAULT_MEDIA_CSV ) ) {

                    	PrintWriter out = response.getWriter();
                    	
                    	//response.setContentType("application/txt");
                    	response.setContentType("application/octet-stream"); 
                    
                    	Map customParams = safeRetrieveParameters( reportData );
                    	customParams.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);
                        
                        if ( locale != null )
                            customParams.put(JRParameter.REPORT_LOCALE, locale);
                        
                    	
                    	JasperPrint jasperPrint = JasperFillManager.fillReport( reportFile.getPath()
                    														  , customParams
                    			                                              , reportDS );
                    	
                    	// Criamos arquivo físico no sistema utilizando como base
                    	// o jasper populado.
                        String filename = jasperPrint.getName() + System.currentTimeMillis() + ".csv";
                        File   destFile = new File( reportFile.getParent(), filename );
                    	
                        JRCsvExporter exporter = new JRCsvExporter();
                    	exporter.setParameter( JRExporterParameter.JASPER_PRINT, jasperPrint );
                        exporter.setParameter( JRExporterParameter.OUTPUT_FILE_NAME, destFile.toString() );
                        exporter.setParameter( JRCsvExporterParameter.FIELD_DELIMITER, ";" );
                        exporter.setParameter( JRCsvExporterParameter.RECORD_DELIMITER, "\n" );
                        exporter.setParameter( JRCsvExporterParameter.RECORD_DELIMITER, System.getProperty("line.separator"));
                        exporter.exportReport();

                        // Agora devemos ler o conteúdo do arquivo gerado
                        // e exbir na tela.
                        BufferedReader in = new BufferedReader( new FileReader( destFile.toString() ) );
                        String str;
                        while ( (str = in.readLine()) != null ) {
                            out.println(str);
                        }
                        in.close();

                        // E finalmente, removemos o arquivo gerado.
                        destFile.delete();

                    	
                    }
                    
                } else {
               
                    // Algum dado do relatório é nulo. Devemos lançar uma exception.
                    // A Exception deverá ser empacotada por ServletException.
                    ReportException rExp = new ReportException("Data retrieved is broken. Some values are null.");
                    throw new ServletException( rExp );                
                    
                }
                
            } else {

                // Não achamos os dados do relatório então devemos lançar uma exception
                // informando este problema. A Exception deverá ser empacotada por ServletException.
                ReportException rExp = new ReportException("Unable to retrieve report data from request.");
                throw new ServletException( rExp );
                
            }
            
        } catch (JRException jrExp) {
            
            // Houve um erro na criação do relatório.
            throw new ServletException( new ReportException( jrExp ) );

        }
        
    }    
    
    //----[ Métodos protegidos ]----------------------------------------------------    
    
    /** Obtém os dados do relatório recebidos na requisição.
     * 
     *  @param request
     *      Requisição recebida pelo <i>Servlet</i>.
     */
    protected Map retrieveReportData(HttpServletRequest request) {
        
        return (Map)request.getAttribute( ReportConstants.REPORT_REQUEST_DATA );
        
    }
    
    /** Obtém o conteúdo do relatório no mapa fornecido.
     *  <p>
     *  Se o conteúdo for nulo, retornará nulo.
     * 
     *  @param reportData
     *      Mapa de objetos do relatório.
     *  
     *  @return
     *      Lista de conteúdo.
     */
    protected Object safeRetrieveContents(Map reportData) {
        
        Object contents = reportData.get( ReportConstants.REPORT_CONTENTS );
        
        if ( contents != null )
            return contents;
        
        else
            return null;
        
    }
    
    /** Obtém os parâmetros do relatório no mapa fornecido.
     *  <p>
     *  Se o conteúdo for nulo, retornará nulo.
     * 
     *  @param reportData
     *      Mapa de objetos do relatório.
     *  
     *  @return
     *      Mapa de parâmetros de conteúdo.
     */
    protected Map safeRetrieveParameters(Map reportData) {
        
        Object parameters = reportData.get( ReportConstants.REPORT_PARAMETERS );
        Map    parameterMap = null;
        
        if ( parameters != null ) {
        
        	// Antes de retornarmos os parâmetros, devemos buscar por valores
        	// que indiquem templates para subrelatórios. Nestes casos, alterar
        	// seus valores para que contenham o caminho correto.
        	
        	// Primeiro convertemos o mapa original para um objeto do tipo "Map"
        	// e obtemos em seguir suas "chaves".
        	parameterMap = (Map) parameters;
        	//Set parameterKeys = parameterMap.entrySet();
        	Set parameterKeys = parameterMap.keySet();

        	// Criamos um iterator para percorrer a coleção de chaves.
        	Iterator paramIt = parameterKeys.iterator();
        	while ( paramIt.hasNext() ) {
        
        		// Obtemos o nome atual e verificamos se possui a string 
        		// que indica um caminho de template de subreport.
        		String paramName = paramIt.next().toString();
        		
        		if ( paramName.toLowerCase().indexOf("subreportpath") != -1 ) {
        		
        			// Caso possua, substituimos o valor original pelo caminho
        			// completo do arquivo.
        			String value = parameterMap.get(paramName).toString();
        			parameterMap.put( paramName, application.getRealPath( value ) );
        		}
        	}
        }else{
        	parameterMap =  new HashMap();
    	}
        
        return parameterMap;

    }    
    
    /** Recebe requisições HTTP do tipo <code>GET</code>.
     *  
     *  @param request
     *      Requisição recebida pelo <i>Servlet</i>.
     *  @param response
     *      Resposta sendo gerada até então.
     *      
     *  @throws ServletException
     *  @throws IOException
     */    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        this.service(request, response);
        
    }

    /** Recebe requisições HTTP do tipo <code>POST</code>.
     *  
     *  @param request
     *      Requisição recebida pelo <i>Servlet</i>.
     *  @param response
     *      Resposta sendo gerada até então.
     *      
     *  @throws ServletException
     *  @throws IOException
     */  
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        this.service(request, response);
        
    }

}
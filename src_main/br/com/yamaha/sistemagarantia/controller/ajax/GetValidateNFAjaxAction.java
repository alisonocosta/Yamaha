/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......ConsultaClienteAjaxAction.java
 *   .: Criação.....26 de junho, 10:11
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Action de serviço para validar o número e a série de um nota fiscal lançada pelo usuário.
 */
package br.com.yamaha.sistemagarantia.controller.ajax;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ajaxtags.helpers.AjaxXmlBuilder;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import br.com.resource.infra.controller.InfraAjaxDefaultAction;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.ControllerException;
import br.com.resource.infra.security.exception.InvalidSessionException;
import br.com.resource.infra.security.helper.UserHelper;
import br.com.resource.infra.utils.DateUtils;
import br.com.yamaha.sistemagarantia.business.NotaFiscalBusiness;
import br.com.yamaha.sistemagarantia.model.ParametroSistema;
import br.com.yamaha.sistemagarantia.model.Usuario;

/** Action de serviço para validar o número e a série de um nota fiscal lançada pelo usuário
 * 
 *  @author Edson Luiz Sumensari
 */
public class GetValidateNFAjaxAction extends InfraAjaxDefaultAction {

    /** Action de serviço para validar o número e a série de um nota fiscal lançada pelo usuário
     *  
     *  @param mapping
     *      O objeto <code>ActionMapping</code> utilizado para
     *      selecionar esta instância.
     *  @param form
     *      Um objeto (opcional) do tipo <code>ActionForm</code>
     *      utilizado nesta requisição.
     *  @param request
     *      A requisição <code>HTTP</code> que estamos processando.
     *  @param response
     *      A resposta <code>HTTP</code> que estamos processando.
     * 
     *  @return 
     *      Uma String com o conteúdo XML (ou texto) a ser enviado
     *      de volta ao cliente.
     * 
     *  @throws ControllerException
     *      Se houverem erros, estes serão lançados como uma exception
     *      deste tipo.
     */ 
    public String getXmlContent( ActionMapping mapping
                               , ActionForm form
                               , HttpServletRequest request
                               , HttpServletResponse response) throws ControllerException {
    	
    	AjaxXmlBuilder builder = new AjaxXmlBuilder();	 
    	boolean isValid = false;
    	
    	try {
    		
    		//System.out.println("numeroNF: " + request.getParameter("numeroNF"));
    		//System.out.println("serieNF: " + request.getParameter("serieNF"));
    		//System.out.println("dateNF: " + request.getParameter("dateNF"));
			
    		if ( request.getParameter("numeroNF") != null  && request.getParameter("serieNF") != null && request.getParameter("dateNF") != null ) {
    		
	    		String numeroNF = URLDecoder.decode( request.getParameter("numeroNF"), "ISO-8859-1" );
	    		
	    		String serieNF  = URLDecoder.decode( request.getParameter("serieNF"), "ISO-8859-1" );
	    		
	    		String dateNF  = URLDecoder.decode( request.getParameter("dateNF"), "ISO-8859-1" );
	    		
	    		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    		sdf.setLenient(false);	    
	    		
	    		if ( !"".equals(numeroNF) && !"".equals(serieNF) && !"".equals(dateNF) ) {
	    		
	    			Long numeroNFL = Long.valueOf(numeroNF);
	    		
		    		Usuario 			usuario 		   = (Usuario) UserHelper.getBoundedUser(request.getSession());
		    		NotaFiscalBusiness 	notaFiscalBusiness = (NotaFiscalBusiness) springContext.getBean("notaFiscalBO");	    		
		    		
		    		if ( notaFiscalBusiness.isEcxistNotaBySerie(numeroNFL, serieNF, usuario.getConcessionaria()) ) {
		    			
		    			builder.addItem("targetMessageNF", "A nota fiscal número " + numeroNF + " de série " + serieNF + ", já foi lançada anteriormente!");
		    					    			
		    		} else {
		    			
		    			if (  notaFiscalBusiness.isEcxistSerie(serieNF) ) {
		    				
		    				// Tolerância de dias para o lançamento da nota fiscal
					    	ParametroSistema parametroDiasNF = notaFiscalBusiness.getByParameterSystem(ParametroSistema.TOLERANCIA_DIAS_NF);
					    	int toleranciaDiasNF  = Integer.parseInt(parametroDiasNF.getValorParametro());
					    	int diferenca = DateUtils.dataDiff(sdf.parse(dateNF), new Date());
		    				//System.out.println("Dif:" + diferenca + " - Tolerância: " + toleranciaDiasNF);
		    				
		    				//System.out.println("isDif:" + (diferenca <= toleranciaDiasNF));
		    				
		    				if ( diferenca <= toleranciaDiasNF ) {
		    						    			
		    					isValid = true;
		    					builder.addItem("targetMessageNF", "Nota Válida!");
		    					//System.out.println("Válida!"); 
		    					
		    				} else {
		    						
		    					builder.addItem("targetMessageNF", "A data informada para a nota fiscal é inválida!");
		    					//System.out.println("Inválida!"); 
		    					
		    				}
		    				
		    			} else {
		    				
		    				builder.addItem("targetMessageNF", "A série  " + serieNF + " informada para a nota fiscal não é válida!");
		    						    				
		    			}
		    			
		    		} 
	    		} else {
	    			builder.addItem("targetMessageNF", "Parâmetros inválidos!");
	    		}
	    		
    		} else    			
    			builder.addItem("targetMessageNF", "A requisição fornecida não possui os parâmetros necessários.");
    		    		
    	
    	} catch (BusinessException bExp) {
    		
    		builder.addItem("targetMessageNF", bExp.getMessage());
            
        } catch (UnsupportedEncodingException useExp) {
    		
        	builder.addItem("targetMessageNF", useExp.getMessage());
			
		} catch (NumberFormatException nfe) {
			
			builder.addItem("targetMessageNF", nfe.getMessage());
			
		} catch (InvalidSessionException ivSe) {
			
			builder.addItem("targetMessageNF", ivSe.getMessage());
			
		} catch (ParseException pex) {
			
			builder.addItem("targetMessageNF", "A Data da Nota Fiscal é Inválida!");
		}
		
		if ( isValid )
			builder.addItem("targetValidateNF", "true");
		else
			builder.addItem("targetValidateNF", "false");
		
		//System.out.println("GetValidateNFAjaxAction - " + builder.toString() );
		
		return builder.toString();
    }
    
}
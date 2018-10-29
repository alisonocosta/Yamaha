/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......GetEstadoDddAjaxAction.java
 *   .: Cria��o.....26 de Julho de 2009, 12:36
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Action de servi�o para validar o DDD.
 */
package br.com.yamaha.sistemagarantia.controller.ajax;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ajaxtags.helpers.AjaxXmlBuilder;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import br.com.resource.infra.controller.InfraAjaxDefaultAction;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.ControllerException;
import br.com.yamaha.sistemagarantia.business.EstadoDddBusiness;
import br.com.yamaha.sistemagarantia.model.EstadoDdd;

/** Action de servi�o para validar o DDD.
 * 
 *  @author Edson Luiz Sumensari
 */
public class GetEstadoDddAjaxAction extends InfraAjaxDefaultAction {

    /** Action de servi�o para validar o DDD.
     *  
     *  @param mapping
     *      O objeto <code>ActionMapping</code> utilizado para
     *      selecionar esta inst�ncia.
     *  @param form
     *      Um objeto (opcional) do tipo <code>ActionForm</code>
     *      utilizado nesta requisi��o.
     *  @param request
     *      A requisi��o <code>HTTP</code> que estamos processando.
     *  @param response
     *      A resposta <code>HTTP</code> que estamos processando.
     * 
     *  @return 
     *      Uma String com o conte�do XML (ou texto) a ser enviado
     *      de volta ao cliente.
     * 
     *  @throws ControllerException
     *      Se houverem erros, estes ser�o lan�ados como uma exception
     *      deste tipo.
     */ 
    public String getXmlContent( ActionMapping mapping
                               , ActionForm form
                               , HttpServletRequest request
                               , HttpServletResponse response) throws ControllerException {
        
    	try {
			
    		if ( request.getParameter("ddd") != null ) {
    		
	    		String strDdd = URLDecoder.decode( request.getParameter("ddd"), "ISO-8859-1" );
	    		EstadoDddBusiness estadoDddBusiness = (EstadoDddBusiness) springContext.getBean("estadoDddBO");	    		
	    		
	    		Long ddd = Long.valueOf(strDdd);
	    		
	    		AjaxXmlBuilder builder = new AjaxXmlBuilder();
	    		
	    		EstadoDdd estadoDdd = estadoDddBusiness.getByDdd(ddd);
	    		
	    		if ( estadoDdd != null ) {
	    				    			
	    			builder.addItem("estadoDdd"  , estadoDdd.getDdd().toString());
	    			
	    		} else {
	    			
	    			System.out.println("DDD n�o localizado!");
	    			
	    		}
	    		
	    		return builder.toString();
    		
    		}

    		throw new ControllerException("A requisi��o fornecida n�o possui os par�metros necess�rios.");
    	
    	} catch (BusinessException bExp) {
    		
            throw new ControllerException(bExp);
            
        } catch (UnsupportedEncodingException e) {
    		
    		throw new ControllerException(e);
			
		}
    }
    
}
/* Resource Informática
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......DeterminaChassiByLinhaAjaxAction.java
 *   .: Criação.....11 de maio, 12:57
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Action de serviço para alimentar o campo chassi autocomplete.
 */
package br.com.yamaha.sistemagarantia.controller.ajax;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ajaxtags.helpers.AjaxXmlBuilder;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import br.com.resource.infra.controller.InfraAjaxDefaultAction;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.ControllerException;
import br.com.resource.infra.utils.StringUtils;
import br.com.yamaha.sistemagarantia.business.CupomBusiness;
import br.com.yamaha.sistemagarantia.model.Faturamento;

/** Action de serviço para alimentar o campo chassi autocomplete.
 * 
 *  @author Edson Luiz Sumensari
 */
public class DeterminaChassiByLinhaAjaxAction extends InfraAjaxDefaultAction {

    /** Obtém a data e hora formatados no servidor e retorna.
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
        
    	try {
    		
    		String chassiPart = URLDecoder.decode( request.getParameter("chassi"), "ISO-8859-1" );
    		CupomBusiness cupomBusiness = (CupomBusiness)springContext.getBean("cupomBO");
    		
    		//System.out.println("Chassi:" + chassiPart);
    		
    		AjaxXmlBuilder builder = new AjaxXmlBuilder();
    		
    		List listChassi = null;
    		listChassi = cupomBusiness.listFaturamentoByLinhaAndChassi(chassiPart, new Long(2) );
    		
    		Faturamento faturamento = null; 
    		Iterator iter = listChassi.iterator();
    		
    		while ( iter.hasNext() ) {
    			
    			faturamento = new Faturamento();
    			faturamento = (Faturamento) iter.next();
    			
    			//System.out.println("Faturamento - Chassi:" + faturamento.getChassi());
    			
    			builder.addItem( StringUtils.replace( faturamento.getChassi()
         			   		                        , chassiPart
         			   		                        , "&lt;b&gt;" + chassiPart + "&lt;/b&gt;" )
         			   		                        , faturamento.getChassi() );    			
    			
    		}    		
    		
    		return builder.toString();
    	
    	} catch (BusinessException bExp) {
    		
            throw new ControllerException(bExp);
            
        }catch (UnsupportedEncodingException e) {
    		
    		throw new ControllerException(e);
			
		}
        
    }
    
}
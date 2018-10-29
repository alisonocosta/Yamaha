/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......LoadApproachServicesAjaxAction.java
 *   .: Criação.....13 de setembro, 20:17
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Action de serviço para alimentar o campo Serviço.
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
import br.com.yamaha.sistemagarantia.business.GarantiaHeaderBusiness;
import br.com.yamaha.sistemagarantia.model.Linha;
import br.com.yamaha.sistemagarantia.model.Servico;

/** Action de serviço para alimentar o campo Serviço.
 * 
 *  @author Edson Luiz Sumensari
 */
public class LoadApproachServiceAjaxAction extends InfraAjaxDefaultAction {

    /** Obtém os itens de um determinadao chassi.
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
    		
    		String code    = URLDecoder.decode( request.getParameter("code"),    "ISO-8859-1" );
    		String chassi  = URLDecoder.decode( request.getParameter("chassi"),  "ISO-8859-1" );
    		String linhaIdStr = URLDecoder.decode( request.getParameter("linhaId"), "ISO-8859-1" );
    		Long   linhaId  = Long.valueOf(linhaIdStr);
    		//System.out.println("LoadApproachServiceAjaxAction -  Chassi: " + chassi + "  Linha:" + linhaIdStr);
    		
    		GarantiaHeaderBusiness garantiaHeaderBusiness = (GarantiaHeaderBusiness)springContext.getBean("garantiaHeaderBO");
    		    		
    		AjaxXmlBuilder builder = new AjaxXmlBuilder();
    		
    		
    		List listServices = null;
    		
    		// Quando for motocicleta a busca é feita pela linha do produto
    		if ( Linha.TIPO_MOTOCICLETA.equals(linhaId) ) {
    			listServices = garantiaHeaderBusiness.listApproachServices(code, linhaId);
    		} else {
    			listServices = garantiaHeaderBusiness.listApproachServicesByChassi(code, chassi);    			
    		}
    			
    		if ( listServices.size() == 0 || listServices.isEmpty() ) {
	    			
	    			builder.addItem( code, "O Serviço não foi encontrado!" );
	    			
    		} else {
    			
    			Iterator iter = listServices.iterator();
	    		
	    		while ( iter.hasNext() ) {
	    			
	    			Servico servico = (Servico) iter.next();
	    			
	    			builder.addItem( StringUtils.replace( servico.getServicoCode()
	         			   		                        , code
	         			   		                        , "&lt;b&gt;" + code + "&lt;/b&gt;" )
	         			   		                        , servico.getDescricao() );    			
	    			
	    		}   
	    		
    		}
    		
    		return builder.toString();
    	
    	} catch (BusinessException bExp) {
    		
            throw new ControllerException(bExp);
            
        }catch (UnsupportedEncodingException e) {
    		
    		throw new ControllerException(e);
			
		} 
    }
    
}
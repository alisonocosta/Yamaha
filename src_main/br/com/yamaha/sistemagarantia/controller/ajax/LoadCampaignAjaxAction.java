/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......LoadCampaignAjaxAction.java
 *   .: Criação.....27 de julho, 09:54
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Action de serviço para alimentar o formulário de garantia com as Campanhas
 *                  de acordo com um chassi informado.
 */
package br.com.yamaha.sistemagarantia.controller.ajax;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.util.List;

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
import br.com.yamaha.sistemagarantia.business.CampaignBusiness;

/** Action de serviço para alimentar o formulário de garantia com as campanhas
 *  de acordo com um chassi informado.
 * 
 *  @author Edson Luiz Sumensari
 */
public class LoadCampaignAjaxAction extends InfraAjaxDefaultAction {

    /** Action de serviço para alimentar o formulário de garantia com as campanhas
     *  de acordo com um chassi informado.
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
    		
    		UserHelper.getBoundedUser(request.getSession());
    		
    		if ( request.getParameter("chassi") != null ) {
    			
	    		String chassi = URLDecoder.decode( request.getParameter("chassi"), "ISO-8859-1" );
	    		CampaignBusiness campaignBusiness = (CampaignBusiness) springContext.getBean("campaignBO");
	    		
	    		AjaxXmlBuilder builder = new AjaxXmlBuilder();
	    		
	    		/* Rotina para listar as campanhas para o chassi informado*/	    		
	    		//List listCampaign = campaignBusiness.listCampaign(chassi);
	    		List listCampaign = campaignBusiness.listCampaignForSelect(chassi);
	    		
	    		if ( !listCampaign.isEmpty() ) {
	    			
	    			builder.addItem("Selecione","null");
	    			builder.addItems(listCampaign, "codeCampaign", "entityId");  			
	    			
	    		}  		
	    		
	    		return builder.toString();
    		
    		}

    		throw new ControllerException("A requisição fornecida não possui os parâmetros necessários.");
    	
    	} catch (BusinessException bExp) {
    		
            throw new ControllerException(bExp);
            
        } catch (UnsupportedEncodingException ueExp) {
    		
    		throw new ControllerException(ueExp);
			
		} catch (IllegalAccessException ellaExp) {
			
			throw new ControllerException(ellaExp);
			
		} catch (InvocationTargetException itExp) {
			
			throw new ControllerException(itExp);
			
		} catch (NoSuchMethodException nsmExp) {
			
			throw new ControllerException(nsmExp);
			
		} catch ( InvalidSessionException isExp ) {
    		
    		throw new ControllerException(isExp);
    		
		}  
    }
    
}
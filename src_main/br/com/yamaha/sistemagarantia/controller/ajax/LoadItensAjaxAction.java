/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......LoadItensAjaxAction.java
 *   .: Criação.....01 de julho, 22:09
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Action de serviço para alimentar o campo Item.
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
import br.com.yamaha.sistemagarantia.business.ItemBusiness;

/** Action de serviço para alimentar o campo autorizacaoEspecialSG.
 * 
 *  @author Edson Luiz Sumensari
 */
public class LoadItensAjaxAction extends InfraAjaxDefaultAction {

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
			
    		String chassi = URLDecoder.decode( request.getParameter("chassi"), "ISO-8859-1" );
    		
    		//System.out.println("LoadItens -- > Chassi:" + chassi);
    		
    		AjaxXmlBuilder builder = new AjaxXmlBuilder();
    		
    		if ( chassi.length() < 12 ) {
    		
	    		ItemBusiness itemBusiness = (ItemBusiness) springContext.getBean("itemBO");
	    				
	    		List listItens = itemBusiness.listByChassi(chassi.toUpperCase(), new Long(2));
	    		
	    		if ( listItens.size() > 0 )
	    			builder.addItem("Selecione","null");
	    		
	    		builder.addItems(listItens, "codeDescricao", "entityId" );
    		}
    		
    		return builder.toString();
    	
    	} catch (BusinessException bExp) {
    		
            throw new ControllerException(bExp);
            
        } catch (UnsupportedEncodingException e) {
    		
    		throw new ControllerException(e);
			
		} catch (IllegalAccessException e) {
			
			throw new ControllerException(e);
			
		} catch (InvocationTargetException e) {
			
			throw new ControllerException(e);
			
		} catch (NoSuchMethodException e) {
			
			throw new ControllerException(e);
			
		} 
        
    }
    
}
/* Resource Tecnologia
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......LoadModelsAjaxAction.java
 *   .: Criação.....14 de Outubro, 09:43
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Action de serviço para alimentar o campo Modelo do form do relatório Gráficos Individuais por Linha.
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
import br.com.yamaha.sistemagarantia.business.ModeloBusiness;

/** Action de serviço para alimentar o campo autorizacaoEspecialSG.
 * 
 *  @author Edson Luiz Sumensari
 */
public class LoadModelsAjaxAction extends InfraAjaxDefaultAction {

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
			
    		ModeloBusiness  modeloBusiness  = (ModeloBusiness) springContext.getBean("modeloBO");
    		
    		String linhaStr = URLDecoder.decode( request.getParameter("linhaId"), "ISO-8859-1" );
    		
    		
    		AjaxXmlBuilder builder = new AjaxXmlBuilder();
    		
    		//System.out.println("LinhaId -- > linhaStr:" + linhaStr);
    		
    		if ( linhaStr != null ) {
    			
    			//System.out.println("not null");
    			
    			Long linhaId = Long.valueOf(linhaStr);
    		
    			//System.out.println("LinhaId -- > linhaStr:" + linhaId);
	    				
	    		List listModels = modeloBusiness.listGraphModelos(linhaId);
	    		
	    		if ( listModels.size() > 0 )
	    			builder.addItem("Selecione","null");
	    		
	    		//System.out.println("listModels -- >" + listModels.size());
	    		
	    		builder.addItems(listModels, "modelo", "modelo" );
	    		
    		} else
    			throw new BusinessException("Valor Inválido!");
    			
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
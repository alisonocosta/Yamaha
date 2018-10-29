/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......DeterminaItemAjaxAction.java
 *   .: Criação.....14 de setembro, 15:02
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Action de serviço para alimentar o campo item da garantia peça.
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
import br.com.yamaha.sistemagarantia.business.ItemBusiness;
import br.com.yamaha.sistemagarantia.model.Item;

/** Action de serviço para alimentar o campo item da garantia peça.
 * 
 *  @author Edson Luiz Sumensari
 */
public class DeterminaItemAjaxAction extends InfraAjaxDefaultAction {

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
        
    	
    	//System.out.println("DeterminaItemAjaxAction - Pesquisa de itens para o cadastro de peças em Garantia!");
    	
    	try {
			
    		if ( request.getParameter("itemCode") != null ) {
    		
	    		String itemCode = URLDecoder.decode( request.getParameter("itemCode"), "ISO-8859-1" );
	    		String chassi   = URLDecoder.decode( request.getParameter("chassi"),   "ISO-8859-1" );
	    		String counter  = URLDecoder.decode( request.getParameter("counter"),  "ISO-8859-1" );
	    		String linhaId  = URLDecoder.decode( request.getParameter("linhaId"),  "ISO-8859-1" );
	    		
	    		//System.out.println("Parâmetros recebidos do form --> itemCode:" + itemCode + " chassi: " + chassi + " Linha:" + linhaId + " counter:" + counter);
	    		
	    		ItemBusiness itemBusiness = (ItemBusiness) springContext.getBean("itemBO");	    		
	    		
	    		AjaxXmlBuilder builder = new AjaxXmlBuilder();
	    		
	    		List listItens = itemBusiness.listByChassi(chassi, itemCode, Long.valueOf(linhaId));
	    		
	    		//System.out.println("Itens Recuperados:" + listItens.size());
	    		
	    		if ( listItens.size() == 0 || listItens.isEmpty() ) {
	    			
	    			builder.addItem( itemCode, "NOT FOUND" );    			
	    			
	    		} else {
		    		
	    			for (Iterator iter = listItens.iterator(); iter.hasNext(); ) {
		    			
	    				String cvItem = new String();
		    			Item item = (Item) iter.next();
		    			
		    			cvItem = item.getEntityId().toString() + "@" + item.getDescricao() + "@" + counter;
		    			
		    			//System.out.println("Busca do item :" + cvItem);
		    			
		    			builder.addItem( StringUtils.replace( String.valueOf(item.getItemCode())
	    			   			                            , itemCode
	    			   			                            , "&lt;b&gt;" + itemCode + "&lt;/b&gt;" )
	    			   			                            , cvItem); 
		    		}  
	    			
	    		}
	    		
	    		//System.out.println("Retornando ao Form:" + builder.toString());
	    		
	    		return builder.toString();  			
    			
    		}

    		throw new ControllerException("A requisição fornecida não possui os parâmetros necessários.");
    	
    	} catch (BusinessException bExp) {
    		
            throw new ControllerException(bExp);
            
        } catch (UnsupportedEncodingException e) {
    		
    		throw new ControllerException(e);
			
		} 
    }
    
}
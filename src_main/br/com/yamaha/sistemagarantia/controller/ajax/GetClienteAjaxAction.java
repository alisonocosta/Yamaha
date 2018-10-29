/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......ConsultaClienteAjaxAction.java
 *   .: Criação.....18 de maio, 10:40
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Action de serviço para alimentar o formulário do cliente quando o cliente já existir.
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
import br.com.yamaha.sistemagarantia.business.ClienteBusiness;
import br.com.yamaha.sistemagarantia.model.Cliente;

/** Action de serviço para alimentar o formulário do cliente quando o cliente já existir.
 * 
 *  @author Edson Luiz Sumensari
 */
public class GetClienteAjaxAction extends InfraAjaxDefaultAction {

    /** Action de serviço para alimentar o formulário do cliente quando o cliente já existir.
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
			
    		if ( request.getParameter("cnpj") != null ) {
    		
	    		String cnpj = URLDecoder.decode( request.getParameter("cnpj"), "ISO-8859-1" );
	    		ClienteBusiness clienteBusiness = (ClienteBusiness) springContext.getBean("clienteBO");	    		
	    		
	    		// Removemos a máscara, caso exista
				String newCpfOrCnpj = cnpj.replaceAll("[^0-9]*","");
	    		
	    		AjaxXmlBuilder builder = new AjaxXmlBuilder();
	    		
	    		Cliente cliente = clienteBusiness.getByCpfCnpj(Long.valueOf(newCpfOrCnpj));
	    		
	    		if ( cliente != null ) {
	    			
	    			String clienteId = (cliente.getEntityId()).toString();		    			
	    			String cep       = (cliente.getCep()).toString();
	    			
	    			builder.addItem("entityId"   , clienteId);
	    			builder.addItem("nome"       , cliente.getNome());
	    			builder.addItem("endereco"   , cliente.getEndereco());
	    			builder.addItem("bairro"     , cliente.getBairro());	    			
	    			builder.addItem("estado"     , cliente.getEstado());
	    			builder.addItem("cidade"     , cliente.getCidade());
	    			builder.addItem("cep_tmp"    , cep);
	    			builder.addItem("telefoneRes", cliente.getTelefoneRes());
	    			builder.addItem("telefoneCom", cliente.getTelefoneCom());
	    			builder.addItem("telefoneCel", cliente.getTelefoneCel());
	    			builder.addItem("email"      , cliente.getEmail());
	    			
	    		} else {
	    			
	    			System.out.println("Cliente não localizado!");
	    			
	    		}
	    		
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
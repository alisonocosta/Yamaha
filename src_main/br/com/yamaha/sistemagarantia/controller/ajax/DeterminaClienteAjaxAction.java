/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......DeterminaClienteAjaxAction.java
 *   .: Criação.....18 de maio, 10:40
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Action de serviço para alimentar o campo cpfCnpj do cliente autocomplete.
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
import br.com.resource.infra.security.exception.InvalidSessionException;
import br.com.resource.infra.security.helper.UserHelper;
import br.com.resource.infra.utils.StringUtils;
import br.com.yamaha.sistemagarantia.business.ClienteBusiness;
import br.com.yamaha.sistemagarantia.model.Cliente;
import br.com.yamaha.sistemagarantia.model.Usuario;

/** Action de serviço para alimentar o campo cpfCnpj do cliente autocomplete.
 * 
 *  @author Edson Luiz Sumensari
 */
public class DeterminaClienteAjaxAction extends InfraAjaxDefaultAction {

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
			
    		if ( request.getParameter("cpfCnpjCliente") != null ) {
    		
	    		String cpfCnpjPart = URLDecoder.decode( request.getParameter("cpfCnpjCliente"), "ISO-8859-1" );
	    		ClienteBusiness clienteBusiness = (ClienteBusiness) springContext.getBean("clienteBO");	    		
	    			    		
	    		AjaxXmlBuilder builder = new AjaxXmlBuilder();
	    		
	    		List listCpfCnpj = clienteBusiness.listApproachedByCpfCnpj(cpfCnpjPart);
	    		
	    		//System.out.println("CNPJ:" + cpfCnpjPart);
	    		 
	    		if ( listCpfCnpj.size() == 0 || listCpfCnpj.isEmpty() ) {
	    			
	    			builder.addItem( cpfCnpjPart, "null@O CPF/CNPJ não foi encontrado!" );    			
	    			
	    		} else {
	    			
	    			Iterator iter = listCpfCnpj.iterator();
		    		
	    			while ( iter.hasNext() ) {
		    			
		    			Cliente cliente = (Cliente) iter.next();
		    			String nome = new String();
		    			
		    			if ( cliente.getNome().indexOf('&') > -1 )
		    				nome = cliente.getNome().replace('&', 'E');
		    			else
		    				nome = cliente.getNome();
		    			
		    			
		    			/*
		    			 * Validacao dos campos Email, DataNascimento e Sexo
		    			 * Demanda: 00003
		    			 * Data: 29/06/2012
		    			 * Resource IT
		    			 */
		    			if (cliente.getEmail() == null) {
		    				builder.addItem(cpfCnpjPart, "null-e@" + nome.toUpperCase() );
		    				break;
		    			}
		    			
		    			if (cliente.getDataNascimento() == null) {
		    				builder.addItem(cpfCnpjPart, "null-dt@" + nome.toUpperCase() );
		    				break;
		    			}
		    			
		    			if (cliente.getSexo() == null) {
		    				builder.addItem(cpfCnpjPart, "null-s@" + nome.toUpperCase() );
		    				break;
		    			}
		    			
		    			//System.out.println("Nome:" + nome);
		    			
		    			builder.addItem( cpfCnpjPart  
	    			   			         , cliente.getEntityId() + "@" + nome.toUpperCase()); 
		    		}  
	    			
	    		}
	    		return builder.toString();
    		
    		} else if ( request.getParameter("nomeClienteForId") != null && !request.getParameter("nomeClienteForId").equals("") ) {
    			
    			String nomePart = URLDecoder.decode( request.getParameter("nomeClienteForId"), "ISO-8859-1" ).toUpperCase();
	    		ClienteBusiness clienteBusiness = (ClienteBusiness) springContext.getBean("clienteBO");
	    		Usuario usuario = (Usuario) UserHelper.getBoundedUser(request.getSession());
	    		
	    		AjaxXmlBuilder builder = new AjaxXmlBuilder();
	    		
	    		System.out.println(" Nome:" + nomePart);
	    		
	    		List results = clienteBusiness.listByNamePart(nomePart, usuario.getConcessionaria());
	    		//List results = clienteBusiness.listByNamePart(nomePart);
	    		
//	    		System.out.println(" Registros:" + results.size());
	    		
	    		if ( !results.isEmpty() && results.size() > 0 ) {
	    			
	    			for (Iterator iter = results.iterator(); iter.hasNext(); ) {
		    			
		    			Cliente cliente = (Cliente) iter.next();  
		    			//System.out.println(" Cliente:" + cliente.getNome());
		    			
		    			builder.addItem( StringUtils.replace( cliente.getNome().toUpperCase()
	    			   			                            , nomePart
	    			   			                            , "&lt;b&gt;" + nomePart + "&lt;/b&gt;" )
	    			   			                            , cliente.getEntityId().toString()); 
		    			
		    		} 
	    			
	    		} else {
	    			
	    			builder.addItem( "O Cliente não foi localizado!", "undefined" );    
	    			
	    		}	    		
	    		
	    		
	    		return builder.toString();    			
    			
    		} else if ( request.getParameter("cpfCnpjClienteForId") != null && !request.getParameter("cpfCnpjClienteForId").equals("") ) {
    			
    			String cnpj = URLDecoder.decode( request.getParameter("cpfCnpjClienteForId"), "ISO-8859-1" );
	    		ClienteBusiness clienteBusiness = (ClienteBusiness) springContext.getBean("clienteBO");
	    		//Usuario usuario = (Usuario) UserHelper.getBoundedUser(request.getSession());
	    		
	    		AjaxXmlBuilder builder = new AjaxXmlBuilder();
	    		
	    		//List results = clienteBusiness.listApproachedByCpfCnpjByConc(cnpj, usuario.getConcessionaria());
	    		
	    		List results = clienteBusiness.listApproachedByCpfCnpj(cnpj);
	    		
	    		//System.out.println(" Registros:" + results.size());
	    		
	    		if ( !results.isEmpty() && results.size() > 0 ) {
		    		
	    			for (Iterator iter = results.iterator(); iter.hasNext(); ) {
		    			
		    			Cliente cliente = (Cliente) iter.next();  
		    			builder.addItem( cliente.getNome() 
		    					       , cliente.getEntityId().toString()); 
		    		}  
	    			
	    		} else {
	    			
	    			builder.addItem( "O Cliente não foi localizado!", "undefined" );
	    			
	    		}
	    		
	    		return builder.toString();    			
    			
    		} else if ( request.getParameter("chassiClienteForId") != null && !request.getParameter("chassiClienteForId").equals("") ) {
    			
    			String chassi = URLDecoder.decode( request.getParameter("chassiClienteForId"), "ISO-8859-1" );
	    		ClienteBusiness clienteBusiness = (ClienteBusiness) springContext.getBean("clienteBO");
	    		Usuario usuario = (Usuario) UserHelper.getBoundedUser(request.getSession());
	    		
	    		AjaxXmlBuilder builder = new AjaxXmlBuilder();
	    		
	    		List results = clienteBusiness.listByChassiByConc(chassi, usuario.getConcessionaria());
	    		
	    		//System.out.println(" Registros:" + results.size());
	    		
	    		if ( !results.isEmpty() && results.size() > 0 ) {
		    		
	    			for (Iterator iter = results.iterator(); iter.hasNext(); ) {
		    			
		    			Cliente cliente = (Cliente) iter.next();  
		    			builder.addItem( cliente.getNome() 
		    					       , cliente.getEntityId().toString()); 
		    		}  
	    			
	    		} else {
	    			
	    			builder.addItem( "O Cliente não foi localizado!", "undefined" );
	    			
	    		}
	    		
	    		return builder.toString();    			
    			
    		}

    		throw new ControllerException("A requisição fornecida não possui os parâmetros necessários.");
    	
    	} catch (BusinessException bExp) {
    		
            throw new ControllerException(bExp);
            
        } catch (UnsupportedEncodingException e) {
    		
    		throw new ControllerException(e);
			
		} catch ( InvalidSessionException isExp ) {
    		
    		throw new ControllerException(isExp);
    		
		} 
    }
    
}
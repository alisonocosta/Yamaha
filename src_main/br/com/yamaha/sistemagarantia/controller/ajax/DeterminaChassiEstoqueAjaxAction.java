/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......DeterminaChassiEstoqueAjaxAction.java
 *   .: Cria��o.....17 de abril, 11:00
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Action de servi�o para alimentar o campo chassi autocomplete.
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
import br.com.resource.infra.security.SystemUser;
import br.com.resource.infra.security.exception.InvalidSessionException;
import br.com.resource.infra.security.helper.UserHelper;
import br.com.resource.infra.utils.StringUtils;
import br.com.yamaha.sistemagarantia.business.FaturamentoBusiness;
import br.com.yamaha.sistemagarantia.model.Concessionaria;
import br.com.yamaha.sistemagarantia.model.Faturamento;
import br.com.yamaha.sistemagarantia.model.Usuario;

/** Action de servi�o para alimentar o campo chassi autocomplete.
 * 
 *  @author Edson Luiz Sumensari
 */
public class DeterminaChassiEstoqueAjaxAction extends InfraAjaxDefaultAction {

    /** Obt�m a data e hora formatados no servidor e retorna.
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
			
    		
    		String chassiPart = URLDecoder.decode( request.getParameter("chassi"), "ISO-8859-1" );
    		
    		FaturamentoBusiness faturamentoBusiness = (FaturamentoBusiness) springContext.getBean("faturamentoBO");
    		    		
    		SystemUser usuario = (Usuario) UserHelper.getBoundedUser(request.getSession());
    		
    		Concessionaria concessionaria = (Concessionaria)usuario.getAttribute("concessionaria"); 
    		
    		AjaxXmlBuilder builder = new AjaxXmlBuilder();
    		
    		List listChassi = faturamentoBusiness.listChassiByConcessionaria(chassiPart,concessionaria);
    		
    		Faturamento faturamento = null; 
    		Iterator iter = listChassi.iterator();
    		
    		while ( iter.hasNext() ) {
    			
    			faturamento = new Faturamento();
    			faturamento = (Faturamento) iter.next();
    			 
    			//System.out.println("Chassi:" + faturamento.getChassi());
    			builder.addItem( StringUtils.replace( faturamento.getChassi().toUpperCase()
         			   		                        , chassiPart
         			   		                        , "&lt;b&gt;" + chassiPart + "&lt;/b&gt;" )
         			   		                        , faturamento.getChassi().toUpperCase() );    			
    			
    		}    		
    		
    		return builder.toString();
    	
    	} catch (BusinessException bExp) {
    		
            throw new ControllerException(bExp);
            
        }catch (UnsupportedEncodingException e) {
    		
    		throw new ControllerException(e);
			
		} catch (InvalidSessionException e) {
			
			throw new ControllerException(e);
			
		}
        
    }
    
}
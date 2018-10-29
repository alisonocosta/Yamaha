/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......InsertCondicaoProblemaAjaxAction.java
 *   .: Cria��o.....01 de Outubro, 12:28
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Action de servi�o para inserir um item de Condi��o de problema para uma Inofrma��es de Mercado.
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
import br.com.resource.infra.exception.DaoException;
import br.com.resource.infra.security.SystemUser;
import br.com.resource.infra.security.exception.InvalidSessionException;
import br.com.resource.infra.security.helper.UserHelper;
import br.com.yamaha.sistemagarantia.business.InfoMercadoBusiness;
import br.com.yamaha.sistemagarantia.controller.helper.ControllerHelper;
import br.com.yamaha.sistemagarantia.model.HistSintoma;
import br.com.yamaha.sistemagarantia.model.InfoMercado;
import br.com.yamaha.sistemagarantia.model.Usuario;

/** Action de servi�o para inserir um item de Condi��o de problema para uma Inofrma��es de Mercado.
 * 
 *  @author Edson Luiz Sumensari
 */
public class InsertCondicaoProblemaAjaxAction extends InfraAjaxDefaultAction {

    /** Action de servi�o para inserir um item de Condi��o de problema para uma Inofrma��es de Mercado.
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
			
    		//System.out.println("Id enviado : " + request.getParameter("entityId") );
    		//System.out.println("Condi��o : " + request.getParameter("condicaoTxt") );
    		
    		if ( request.getParameter("entityId") != null ) {
    		
	    		String infoMercadoId = URLDecoder.decode( request.getParameter("entityId"), "ISO-8859-1" );
	    		String condicaoTxt   = URLDecoder.decode( request.getParameter("condicaoTxt"), "ISO-8859-1" );
	    		
	    		InfoMercadoBusiness infoMercadoBusiness = (InfoMercadoBusiness) springContext.getBean("infoMercadoBO");	    		
	    		
	    		SystemUser usuario = (Usuario) UserHelper.getBoundedUser(request.getSession());
	    		
	    		AjaxXmlBuilder builder = new AjaxXmlBuilder();
	    		
	    		//System.out.println("Condi��o enviada:" + condicaoTxt);
	    		
	    		if ( condicaoTxt != null && !"".equals(condicaoTxt) ) {
	    		
		    		InfoMercado infoMercado = infoMercadoBusiness.get(new Integer(infoMercadoId));
		    		
		    		//System.out.println("InfoMercado:" + (infoMercado != null));
		    		
		    		if ( infoMercado != null ) {
		    			
		    			HistSintoma histSintoma = new HistSintoma();
						histSintoma.setInfoMercadoId( new Integer( infoMercado.getEntityId().toString() ) );
						histSintoma.setSintoma( condicaoTxt );
						
						ControllerHelper.prepare( histSintoma, (Long)usuario.getIdentifier() );
							
						infoMercadoBusiness.getInfoMercadoDao().makePersistent(histSintoma);   			
		    			
						//System.out.println("Recuperando as informa��es gravadas! ID: " + infoMercado.getEntityId());
						
						String history = infoMercadoBusiness.getHistory( InfoMercadoBusiness.HIST_SINTOMA, infoMercado.getEntityId() );
						
						//System.out.println("Hist�rico recuperado: " + history);
						
		    			builder.addItem("condicaoProblema"   ,  history);
		    			
		    		} else {
		    			
		    			System.out.println("Informa��o de Mercado n�o localizada!");		    			
		    		}
		    		
	    		} else {
	    			
	    			System.out.println("N�o foi enviado valor para inclus�o!");
	    			
	    		}
	    		
	    		return builder.toString();
    		
    		}

    		throw new ControllerException("A requisi��o fornecida n�o possui os par�metros necess�rios.");
    	
    	} catch (BusinessException bExp) {
    		
            throw new ControllerException(bExp);
            
        } catch (UnsupportedEncodingException e) {
    		
    		throw new ControllerException(e);
			
		} catch (DaoException daoExp) {
			
			throw new ControllerException(daoExp);
			
		} catch (InvalidSessionException ivExp) {
			
			throw new ControllerException(ivExp);
			
		}
    }
    
}
/* Yamaha
 * Copyright (c) 2012 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......InsertParecerYmAjaxAction.java
 *   .: Cria��o.....30 de Dezembro, 12:28
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Action de servi�o para inserir um item de parecer da an�lise da garantia.
 */
package br.com.yamaha.sistemagarantia.processo.controller.ajax;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

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
import br.com.resource.infra.utils.DateUtils;
import br.com.yamaha.sistemagarantia.controller.helper.ControllerHelper;
import br.com.yamaha.sistemagarantia.model.Usuario;
import br.com.yamaha.sistemagarantia.processo.business.AnaliseGarantiaBusiness;
import br.com.yamaha.sistemagarantia.processo.model.HistAnaliseParecerTecnico;

/** Action de servi�o para inserir um item de parecer da an�lise da garantia.
 * 
 *  @author Edson Luiz Sumensari
 */
public class InsertAnaliseParecerYmAjaxAction extends InfraAjaxDefaultAction {

    /** Action de servi�o para inserir um item de parecer da an�lise da garantia.
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
    		//System.out.println("parecerYmTxt : " + request.getParameter("parecerYmTxt") );
    		
    		if ( request.getParameter("entityId") != null ) {

    			String garantiaIdStr = URLDecoder.decode( request.getParameter("entityId"), "ISO-8859-1" );
    			String parecerYmTxt  = URLDecoder.decode( request.getParameter("parecerYmTxt"), "ISO-8859-1" );

    			AnaliseGarantiaBusiness analiseGarantiaBusiness = (AnaliseGarantiaBusiness) springContext.getBean("analiseGarantiaBO");	    		

    			Usuario usuario = (Usuario) UserHelper.getBoundedUser(request.getSession());

    			AjaxXmlBuilder builder = new AjaxXmlBuilder();

    			//System.out.println("Parecer enviada:" + parecerYmTxt);

    			if ( parecerYmTxt != null && !"".equals(parecerYmTxt) && (garantiaIdStr != null && !"".equals(garantiaIdStr)) ) {

    				Integer garantiaId = Integer.valueOf(garantiaIdStr);
    				StringBuffer parecerYm = new StringBuffer();
    				parecerYm.append(usuario.getNome());
    				parecerYm.append(" - ");
    				parecerYm.append(DateUtils.applyMask(new Date(),"dd/MM/yyyy HH:mm:ss"));
    				parecerYm.append(": ");
    				parecerYm.append(parecerYmTxt);

    				HistAnaliseParecerTecnico parecerTecnico = new HistAnaliseParecerTecnico();
    				parecerTecnico.setGarantiaId(garantiaId);
    				parecerTecnico.setParecerTecnicoTxt(parecerYm.toString());

    				ControllerHelper.prepare( parecerTecnico, (Long)usuario.getIdentifier() );

    				analiseGarantiaBusiness.insertParecerTecnico(parecerTecnico);

    				String history = analiseGarantiaBusiness.listHistParecerTecnico(garantiaId);

    				builder.addItem("parecerYm"   ,  history);

    			} else {

    				System.out.println("Par�metros inv�lidos!");		    			
    			}

    			return builder.toString();

    		} else {

    			System.out.println("N�o foi enviado valor para inclus�o!");

    		}
    		
    		throw new ControllerException("A requisi��o fornecida n�o possui os par�metros necess�rios.");
    		
    	} catch (BusinessException bExp) {
    		
            throw new ControllerException(bExp);
            
        } catch (UnsupportedEncodingException e) {
    		
    		throw new ControllerException(e);
			
		} catch (InvalidSessionException ivExp) {
			
			throw new ControllerException(ivExp);
			
		}
    }
    
}
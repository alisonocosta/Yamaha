/* Yamaha
 * Copyright (c) 2012 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......InsertAnaliseObservacaoYmAjaxAction.java
 *   .: Criação.....30 de Dezembro, 12:28
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Action de serviço para inserir um item de parecer da análise da garantia.
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
import br.com.resource.infra.security.exception.InvalidSessionException;
import br.com.resource.infra.security.helper.UserHelper;
import br.com.resource.infra.utils.DateUtils;
import br.com.yamaha.sistemagarantia.controller.helper.ControllerHelper;
import br.com.yamaha.sistemagarantia.model.Usuario;
import br.com.yamaha.sistemagarantia.processo.business.AnaliseGarantiaBusiness;
import br.com.yamaha.sistemagarantia.processo.model.HistAnaliseObservacao;

/** Action de serviço para inserir um item de observação da análise da garantia.
 * 
 *  @author Edson Luiz Sumensari
 */
public class InsertAnaliseObservacaoYmAjaxAction extends InfraAjaxDefaultAction {

    /** Action de serviço para inserir um item de observação da análise da garantia.
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
			
    		//System.out.println("Id enviado : " + request.getParameter("entityId") );
    		//System.out.println("observacaoYmTxt : " + request.getParameter("observacaoYmTxt") );
    		
    		if ( request.getParameter("entityId") != null ) {

    			String garantiaIdStr    = URLDecoder.decode( request.getParameter("entityId"), "ISO-8859-1" );
    			String observacaoYmTxt  = URLDecoder.decode( request.getParameter("observacaoYmTxt"), "ISO-8859-1" );

    			AnaliseGarantiaBusiness analiseGarantiaBusiness = (AnaliseGarantiaBusiness) springContext.getBean("analiseGarantiaBO");	    		

    			Usuario usuario = (Usuario) UserHelper.getBoundedUser(request.getSession());

    			AjaxXmlBuilder builder = new AjaxXmlBuilder();

    			//System.out.println("Parecer enviada:" + observacaoYmTxt);

    			if ( observacaoYmTxt != null && !"".equals(observacaoYmTxt) && (garantiaIdStr != null && !"".equals(garantiaIdStr)) ) {

    				Integer garantiaId = Integer.valueOf(garantiaIdStr);
    				StringBuffer observacaoYm = new StringBuffer();
    				observacaoYm.append(usuario.getNome());
    				observacaoYm.append(" - ");
    				observacaoYm.append(DateUtils.applyMask(new Date(),"dd/MM/yyyy HH:mm:ss"));
    				observacaoYm.append(": ");
    				observacaoYm.append(observacaoYmTxt);

    				HistAnaliseObservacao observacaoTecnico = new HistAnaliseObservacao();
    				observacaoTecnico.setGarantiaId(garantiaId);
    				observacaoTecnico.setObservacaoTxt(observacaoYm.toString());

    				ControllerHelper.prepare( observacaoTecnico, (Long)usuario.getIdentifier() );

    				analiseGarantiaBusiness.insertAnaliseHistorico(observacaoTecnico);

    				String history = analiseGarantiaBusiness.listHistAnaliseObservacao(garantiaId);

    				builder.addItem("observacaoYm"   ,  history);

    			} else {

    				System.out.println("Parâmetros inválidos!");		    			
    			}

    			return builder.toString();

    		} else {

    			System.out.println("Não foi enviado valor para inclusão!");

    		}
    		
    		throw new ControllerException("A requisição fornecida não possui os parâmetros necessários.");
    		
    	} catch (BusinessException bExp) {
    		
            throw new ControllerException(bExp);
            
        } catch (UnsupportedEncodingException e) {
    		
    		throw new ControllerException(e);
			
		} catch (InvalidSessionException ivExp) {
			
			throw new ControllerException(ivExp);
			
		}
    }
    
}
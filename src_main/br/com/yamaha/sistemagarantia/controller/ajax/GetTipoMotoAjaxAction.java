/* Resource Tecnologia
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......GetTipoMotoAjaxAction.java
 *   .: Cria��o.....31 de Mar�o, 10:49
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Action de servi�o para alimentar o formul�rio de revis�o se possui veloc�metro
 *                  de acordo com um chassi informado.
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
import br.com.yamaha.sistemagarantia.business.FaturamentoBusiness;
import br.com.yamaha.sistemagarantia.model.Faturamento;
import br.com.yamaha.sistemagarantia.model.Modelo;

/** Action de servi�o para alimentar o formul�rio de de revis�o se possui veloc�metro
 *  de acordo com um chassi informado.
 * 
 *  @author Edson Luiz Sumensari
 */
public class GetTipoMotoAjaxAction extends InfraAjaxDefaultAction {

    /** Action de servi�o para alimentar o formul�rio de de revis�o se possui veloc�metro
     *  de acordo com um chassi informado.
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
    		
    		if ( request.getParameter("chassi") != null ) {
    			
	    		String chassi = URLDecoder.decode( request.getParameter("chassi"), "ISO-8859-1" );
	    		
	    		//System.out.println("Chassi:" + chassi);
	    		 
	    		FaturamentoBusiness    faturamentoBusiness    = (FaturamentoBusiness) springContext.getBean("faturamentoBO");
	    			    		
	    		AjaxXmlBuilder builder = new AjaxXmlBuilder();
	    		
	    		Faturamento faturamento = faturamentoBusiness.getByChassi(chassi.toUpperCase());
	    		Modelo      modelo		= (Modelo)faturamentoBusiness.getGenericDao().get(Modelo.class, Long.valueOf(faturamento.getModelo())); 
	    		
	    		//System.out.println("GetTipoMotoAjaxAction - Veloc�metro:" + modelo.getVelocimetro());
	    		
	    		if ( Modelo.VELOCIMETRO_NOT.equalsIgnoreCase(modelo.getVelocimetro()) ) {
	    			
	    			builder.addItem("hasKM", "false");
	    			
	    		} else {
	    			
	    			builder.addItem("hasKM", "true");
	    			
	    		}
	    		
	    		return builder.toString();
    		
    		}

    		throw new ControllerException("A requisi��o fornecida n�o possui os par�metros necess�rios.");
    	
    	} catch (BusinessException bExp) {
    		
            throw new ControllerException(bExp);
            
        } catch (UnsupportedEncodingException e) {
    		
    		throw new ControllerException(e);
			
		}  catch (DaoException daoExp) {
			
			throw new ControllerException(daoExp);
		}	
    }
    
}
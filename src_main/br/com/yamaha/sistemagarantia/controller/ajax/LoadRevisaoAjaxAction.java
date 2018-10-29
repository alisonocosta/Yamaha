/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......LoadRevisaoAjaxAction.java
 *   .: Cria��o.....14 de maio, 10:20
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Action de servi�o para alimentar o campo revis�o.
 */
package br.com.yamaha.sistemagarantia.controller.ajax;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.util.ArrayList;
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
import br.com.yamaha.sistemagarantia.business.CupomBusiness;
import br.com.yamaha.sistemagarantia.business.RevisaoBusiness;
import br.com.yamaha.sistemagarantia.controller.helper.ControllerHelper;
import br.com.yamaha.sistemagarantia.model.Cupom;
import br.com.yamaha.sistemagarantia.model.Revisao;

/** Action de servi�o para alimentar o campo revis�o.
 * 
 *  @author Edson Luiz Sumensari
 */
public class LoadRevisaoAjaxAction extends InfraAjaxDefaultAction {

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
    		
    		//System.out.println("Lista de Revis�es - Chassi: " + chassiPart);
    		
    		AjaxXmlBuilder builder = new AjaxXmlBuilder();
    		
    		if ( !(chassiPart.length() < 4) ) {
    		
	    		RevisaoBusiness revisaoBusiness = (RevisaoBusiness) springContext.getBean("revisaoBO");
	    		CupomBusiness   cupomBusiness   = (CupomBusiness) springContext.getBean("cupomBO"); 
	    				
	    		//List listRevisao = revisaoBusiness.listApproachedByChassi(ControllerHelper.getModeloByChassi(chassiPart));
	    		List listRevisao = revisaoBusiness.listByModel(ControllerHelper.getModeloByChassi(chassiPart));
	    		List listCupom   = cupomBusiness.listByChassi(chassiPart.toUpperCase());
	    		
	    		//System.out.println("Cupons:" + listCupom.size());
	    		
	    		List listRevisaoFiltered = new ArrayList();
	    		Revisao revisao =  null;
	    		
	    		Iterator it = listCupom.iterator();
	    		
	    		while ( it.hasNext() ) {
	    			
	    			Cupom cupom = (Cupom)it.next();
	    			revisao = cupom.getRevisao();
	    			
	    			Iterator itr = listRevisao.iterator();
	    			
	    			while ( itr.hasNext() ) {
	    				
	    				Revisao revisao_l = (Revisao)itr.next();
	    				
	    				//System.out.println("Cupom RevisaoId:"  + revisao.getEntityId() + " RevisaoId:" + revisao_l.getEntityId());
	    				
	    				if ( ((Long)revisao.getEntityId()).equals( (Long)revisao_l.getEntityId() ) ) {
	    						    	
	    					//System.out.println(" Revis�o realizada:" + revisao_l.getNumeroRevisao());
	    					listRevisaoFiltered.add(revisao_l);	    					
	    					
	    				}
	    				
	    			}
	    			
	    		}
	    		
	    		Iterator itRes = listRevisaoFiltered.iterator();
    			
    			while ( itRes.hasNext() ) {
    				
    				revisao = (Revisao)itRes.next();
    				
    				listRevisao.remove(revisao);
    				
    			}	    		
	    		
	    		if ( listRevisao.size() > 0 )
	    			builder.addItem("Selecione","null");
	    		
	    		builder.addItems(listRevisao, "descricao", "numeroRevisao" );
    		}
    		
    		return builder.toString();
    	
    	} catch (BusinessException bExp) {
    		//bExp.printStackTrace();
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
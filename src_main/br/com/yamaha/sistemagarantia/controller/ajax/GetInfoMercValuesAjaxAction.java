/* Resource Tecnologia
 * Copyright (c) 2009 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......GetInfoMercValuesAjaxAction.java
 *   .: Criação.....18 de Agosto, 23:18
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Action de serviço para alimentar o formulário de Informação de Mercado com a data venda
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
import br.com.resource.infra.security.exception.InvalidSessionException;
import br.com.resource.infra.security.helper.UserHelper;
import br.com.yamaha.sistemagarantia.business.CupomBusiness;
import br.com.yamaha.sistemagarantia.business.FaturamentoBusiness;
import br.com.yamaha.sistemagarantia.business.RevisaoBusiness;
import br.com.yamaha.sistemagarantia.controller.helper.ControllerHelper;
import br.com.yamaha.sistemagarantia.model.Cupom;
import br.com.yamaha.sistemagarantia.model.Faturamento;
import br.com.yamaha.sistemagarantia.model.Revisao;

/** Action de serviço para alimentar o formulário de Informação de Mercado com a data venda
 *  de acordo com um chassi informado.
 * 
 *  @author Edson Luiz Sumensari
 */
public class GetInfoMercValuesAjaxAction extends InfraAjaxDefaultAction {

    /** Action de serviço para alimentar o formulário de Informação de Mercado com a data venda
     *  de acordo com um chassi informado.
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
    		
    		if ( request.getParameter("chassi") != null ) {
    			
	    		String chassi   = URLDecoder.decode( request.getParameter("chassi"),   "ISO-8859-1" );
	    		
	    		UserHelper.getBoundedUser(request.getSession());
	    		
	    		//System.out.println("Chassi:" + chassi);
	    		CupomBusiness          cupomBusiness          = (CupomBusiness)springContext.getBean("cupomBO");  
	    		RevisaoBusiness        revisaoBusiness        = (RevisaoBusiness)springContext.getBean("revisaoBO"); 
	    		FaturamentoBusiness    faturamentoBusiness    = (FaturamentoBusiness) springContext.getBean("faturamentoBO");
	    		
	    		AjaxXmlBuilder builder = new AjaxXmlBuilder();
	    		
	    		/** 
	    		 * Rotina para encontrar a data de venda através de uma revisão ou de um faturamento
	    		 * 
	    		 */
	    		
	    		//System.out.println("GetGarantiaValuesAjaxAction - chassi:" + chassi);
	    		chassi = chassi.trim();
	    		// Recuparamos a revisão que irá servir de parâmetro para encontrar um cupom
	    		Revisao revisao = revisaoBusiness.getByModel(ControllerHelper.getModeloByChassi(chassi), new Long(1));
	    			    		
	    		Cupom   cupom   = cupomBusiness.getCupomByChassiAndRevisao( chassi, revisao);
	    		
	    		if ( cupom != null ) {
	    			
	    			//System.out.println("GetGarantiaValuesAjaxAction - Cupom data:" + cupom.getMaskedDataVenda());
	    				    			
	    			builder.addItem("dataVenda", cupom.getMaskedDataVenda());
	    			
	    		} else {
	    			
	    			//System.out.println("Cupom não localizado - Buscando no Faturamento!");
	    			
	    			Faturamento faturamento = faturamentoBusiness.getByChassi(chassi);
	    			
	    			builder.addItem("dataVenda", faturamento.getMaskedDataNotaFiscal());
	    			
	    		}
	    			    		
	    		return builder.toString();
    		
    		}

    		throw new ControllerException("A requisição fornecida não possui os parâmetros necessários.");
    	
    	} catch (BusinessException bExp) {
    		
            throw new ControllerException(bExp);
            
        } catch (UnsupportedEncodingException e) {
    		
    		throw new ControllerException(e);
			
		} catch (InvalidSessionException e) {
			
			throw new ControllerException(e);
			
		}
    }
    
}
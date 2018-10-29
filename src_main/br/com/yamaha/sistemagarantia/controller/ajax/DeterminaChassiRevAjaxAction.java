/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......DeterminaChassiAjaxAction.java
 *   .: Criação.....11 de maio, 12:57
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Action de serviço para alimentar o campo chassi autocomplete.
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
import br.com.yamaha.sistemagarantia.business.CupomBusiness;
import br.com.yamaha.sistemagarantia.business.ItemBusiness;
import br.com.yamaha.sistemagarantia.model.Concessionaria;
import br.com.yamaha.sistemagarantia.model.Faturamento;
import br.com.yamaha.sistemagarantia.model.Item;
import br.com.yamaha.sistemagarantia.model.Linha;
import br.com.yamaha.sistemagarantia.model.Usuario;

/** Action de serviço para alimentar o campo chassi autocomplete.
 * 
 *  @author Edson Luiz Sumensari
 */
public class DeterminaChassiRevAjaxAction extends InfraAjaxDefaultAction {

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
			
    		
    		String chassiPart = URLDecoder.decode( request.getParameter("chassi"), "ISO-8859-1" );
    		String loteId     = null;
    		String linhaId    = null;
    		
    		if ( request.getParameter("loteId") != null )
    			loteId     = URLDecoder.decode( request.getParameter("loteId"), "ISO-8859-1" );
    		
    		if ( request.getParameter("linhaId") != null )
    			linhaId     = URLDecoder.decode( request.getParameter("linhaId"), "ISO-8859-1" );
    		
    		//FaturamentoBusiness faturamentoBusiness = (FaturamentoBusiness) springContext.getBean("faturamentoBO");
    		CupomBusiness		cupomBusiness       = (CupomBusiness)springContext.getBean("cupomBO");
    		ItemBusiness		itemBusiness        = (ItemBusiness)springContext.getBean("itemBO");
    		
    		SystemUser usuario = (Usuario) UserHelper.getBoundedUser(request.getSession());
    		
    		Concessionaria concessionaria = (Concessionaria)usuario.getAttribute("concessionaria"); 
    		
    		AjaxXmlBuilder builder = new AjaxXmlBuilder();
    		
    		List listChassi = null;
    		
    		if ( loteId != null && (!"".equals(loteId)) )     		
    			listChassi = cupomBusiness.listApproachChassiByLote(chassiPart.toUpperCase(), concessionaria, Long.valueOf(loteId));
    		else {
    			//listChassi = faturamentoBusiness.listChassiByConcessionaria(chassiPart, concessionaria);
    			listChassi = cupomBusiness.listApproachFaturamentoByLinha(chassiPart, concessionaria, Long.valueOf(linhaId));
    		}
    		
    		Faturamento faturamento = null; 
    		Iterator iter = listChassi.iterator();
    		
    		while ( iter.hasNext() ) {
    			
    			faturamento = new Faturamento();
    			faturamento = (Faturamento) iter.next();
    			
    			String nacChassi = new String();
    			 
    			// Apenas quando for Motocicleta
    			if ( Long.valueOf(linhaId).equals(Linha.TIPO_MOTOCICLETA)) { 
    				
    				Item   item   = itemBusiness.getItemByOrg(faturamento.getItemId(), (Long)faturamento.getEmpresa().getEntityId());
	    				    			
	    			if ( item.getOrigem().equals(Item.ORIGEM_NAC) ) {
	    				
	    				nacChassi = "S@" + faturamento.getChassi();
	    				
	    			} else {
	    				
	    				nacChassi = "N@" + faturamento.getChassi();
	    			}
    			
    			} else {
    				
    				nacChassi = faturamento.getChassi();
    				
    			}
    			
    			//System.out.println("Chassi:" + nacChassi);
    			builder.addItem( StringUtils.replace( faturamento.getChassi()
         			   		                        , chassiPart
         			   		                        , "&lt;b&gt;" + chassiPart + "&lt;/b&gt;" )
         			   		                        , nacChassi );    			
    			
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
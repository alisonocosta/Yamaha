/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......LoadCamapaignAjaxAction.java
 *   .: Criação.....02 de julho 2009, 21:17
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Action de serviço para leitura de camapaign.
 */
package br.com.yamaha.sistemagarantia.controller.ajax;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import br.com.resource.infra.controller.InfraAjaxDefaultAction;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.ControllerException;
import br.com.resource.infra.utils.DateUtils;
import br.com.yamaha.sistemagarantia.business.CampaignBusiness;
import br.com.yamaha.sistemagarantia.business.GarantiaHeaderBusiness;
import br.com.yamaha.sistemagarantia.model.Campaign;
import br.com.yamaha.sistemagarantia.model.Recall;

/** Action de serviço para leitura de camapaign.
 * 
 *  @author Edson Luiz Sumensari
 */
public class LoadCampaignNewAjaxAction extends InfraAjaxDefaultAction {

    /** Obtém a lista de campanhas.
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
    	System.out.println("Lendo Campanhas!");
    	
    	try {
    		
    		//UserHelper.getBoundedUser(request.getSession());
    		
    		if ( request.getParameter("chassi") != null ) {
    			
	    		String chassi = URLDecoder.decode( request.getParameter("chassi"), "ISO-8859-1" );
	    		CampaignBusiness campaignBusiness = (CampaignBusiness) springContext.getBean("campaignBO");
	    		GarantiaHeaderBusiness garantiaHeaderBusiness = (GarantiaHeaderBusiness) springContext.getBean("garantiaHeaderBO");
	    			    		
	    		/* Rotina para listar as campanhas para o chassi informado*/	    		
	    		List listCampaign = campaignBusiness.listCampaign(chassi);	
	    		/* Rotina para listar Recall para o chassi informado*/	    		
	    		List listRecall = garantiaHeaderBusiness.listRecallNotExByChassi(chassi);
	    		
	    		//System.out.println("Campanhas:" + listCampaign.size() + " Recall:" + listRecall.size());
	    		
	    		if ( !listCampaign.isEmpty() || !listRecall.isEmpty() ) {

	    	    	StringBuffer sb = new StringBuffer(); 
	    			int i	  = 1;
	    			boolean hasItem = false;
	    			
	    			sb.append("<script language='javascript'>window.alert(");
	    			sb.append("'CAMPANHA/RECALL NÃO REALIZADO(s) PARA O CHASSI " + chassi + " \\n\\n' + ");	    		
		    		
	    			Iterator itC = listCampaign.iterator();
		    		Campaign cp = null;
		    		while(itC.hasNext()){
		    			cp = (Campaign) itC.next();
		    			if( cp.getValidityStartDate() != null && Campaign.RECALL_YES.equals(cp.getStRecall())) {
		    				if( DateUtils.compareDate(new Date(), cp.getValidityStartDate()) >= 0 ) {
		    					if( (cp.getValidityEndDate() != null ? (DateUtils.compareDate(new Date(), cp.getValidityEndDate()) < 0) : true) ) {
		    						
						    			if ( i > 1)
						    				sb.append(" + '");
						    			else
						    				sb.append("'");
						    			
						    			sb.append( i++ + " - CÓDIGO DA CAMPANHA: " + cp.getCodeCampaign() + "\\nDESCRIÇÃO:" + cp.getSubjectCampaign() + "\\n\\n'");
						    			hasItem = true;
		    					}
		    				}
		    			} 
		    		}
		    		
		    		Iterator itR = listRecall.iterator();
		    		Recall rc = null;
		    		while( itR.hasNext() ){
		    			rc = (Recall) itR.next();
		    			if ( i > 1)
		    				sb.append(" + '");
		    			else
		    				sb.append("'");
		    			
		    			sb.append( i++ + " - CÓDIGO DO RECALL: " + rc.getNumeroIT() + "\\nDESCRIÇÃO:" + rc.getAssunto() + "\\n\\n'");
		    			hasItem = true;    			
		    		}
		    		
		    		sb.append(");</script>");
		    		//System.out.println(sb.toString());
		    		
		    		if ( hasItem )
		    			return sb.toString();
		    		else
		    			return "";
		    		
	    		} 	  		
	    		
	    		return "";
	    		
    		}

    		throw new ControllerException("A requisição fornecida não possui os parâmetros necessários.");
    	
    	} catch (BusinessException bExp) {
    		//bExp.printStackTrace();
            throw new ControllerException(bExp);
            
        } catch (UnsupportedEncodingException ueExp) {
        	//ueExp.printStackTrace();
    		throw new ControllerException(ueExp);
			
		} 
    	
    }
    
}
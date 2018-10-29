/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......GetGarantiaValuesAjaxAction.java
 *   .: Criação.....25 de julho, 17:15
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Action de serviço para alimentar o formulário de garantia com a data venda
 *                  de acordo com um chassi informado.
 */
package br.com.yamaha.sistemagarantia.controller.ajax;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ajaxtags.helpers.AjaxXmlBuilder;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import br.com.resource.infra.controller.InfraAjaxDefaultAction;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.ControllerException;
import br.com.resource.infra.utils.NumberUtils;
import br.com.yamaha.sistemagarantia.business.CupomBusiness;
import br.com.yamaha.sistemagarantia.business.FaturamentoBusiness;
import br.com.yamaha.sistemagarantia.business.GarantiaHeaderBusiness;
import br.com.yamaha.sistemagarantia.business.RevisaoBusiness;
import br.com.yamaha.sistemagarantia.controller.helper.ControllerHelper;
import br.com.yamaha.sistemagarantia.dao.CupomDao;
import br.com.yamaha.sistemagarantia.model.AlertCupom;
import br.com.yamaha.sistemagarantia.model.Cupom;
import br.com.yamaha.sistemagarantia.model.Faturamento;
import br.com.yamaha.sistemagarantia.model.Linha;
import br.com.yamaha.sistemagarantia.model.Modelo;
import br.com.yamaha.sistemagarantia.model.ParametroSistema;
import br.com.yamaha.sistemagarantia.model.Revisao;

/** Action de serviço para alimentar o formulário de garantia com a data venda
 *  de acordo com um chassi informado.
 * 
 *  @author Edson Luiz Sumensari
 */
public class GetGarantiaValuesAjaxAction extends InfraAjaxDefaultAction {

    /** Action de serviço para alimentar o formulário de garantia com a data venda
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
    			
	    		String chassi      = URLDecoder.decode( request.getParameter("chassi"),   "ISO-8859-1" );
	    		String isEdit      = URLDecoder.decode( request.getParameter("isEdit"),   "ISO-8859-1" );
	    		String entityId    = URLDecoder.decode( request.getParameter("entityId"), "ISO-8859-1" );
	    		String linhaIdStr  = URLDecoder.decode( request.getParameter("linhaId"),  "ISO-8859-1" );
	    		
	    		Long linhaId = linhaIdStr != null && !"".equals(linhaIdStr) ? new Long(linhaIdStr) : null;  
	    		
	    		//System.out.println("Chassi:" + chassi);
	    		CupomBusiness          cupomBusiness          = (CupomBusiness)springContext.getBean("cupomBO");  
	    		RevisaoBusiness        revisaoBusiness        = (RevisaoBusiness)springContext.getBean("revisaoBO"); 
	    		FaturamentoBusiness    faturamentoBusiness    = (FaturamentoBusiness) springContext.getBean("faturamentoBO");
	    		GarantiaHeaderBusiness garantiaHeaderBusiness = (GarantiaHeaderBusiness) springContext.getBean("garantiaHeaderBO");
	    		
	    		AjaxXmlBuilder builder = new AjaxXmlBuilder();
	    		
	    		/** 
	    		 * Rotina para encontrar a data de venda através de uma revisão ou de um faturamento
	    		 * 
	    		 */
	    		
	    		//System.out.println("GetGarantiaValuesAjaxAction - chassi:" + chassi);
	    		chassi = chassi.trim();
	    		// Recuparamos a revisão que irá servir de parâmetro para encontrar um cupom
	    		Revisao revisao = revisaoBusiness.getByModel(ControllerHelper.getModeloByChassi(chassi), new Long(1));
	    			    		
	    		Cupom       cupom       = cupomBusiness.getCupomByChassiAndRevisao( chassi, revisao);
	    		Faturamento faturamento = faturamentoBusiness.getByChassi(chassi);
	    		
	    		if ( cupom != null ) {
	    			
	    			//System.out.println("GetGarantiaValuesAjaxAction - Cupom data:" + cupom.getMaskedDataVenda());
	    				    			
	    			builder.addItem("dtVenda", cupom.getMaskedDataVenda());
	    			builder.addItem("isRevEntrega", "true");
	    			
	    		} else {
	    			
	    			//System.out.println("Cupom não localizado - Buscando no Faturamento!");
	    			
	    			builder.addItem("dtVenda", faturamento.getMaskedDataNotaFiscal());
	    			builder.addItem("isRevEntrega", "false");
	    			
	    		}
	    		
	    		/** 
	    		 * Rotina para encontrar um recall para o chassi informado
	    		 * Se existir recall retornamos true para o objeto
	    		 * 
	    		 */	    		
	    		List listRecall = garantiaHeaderBusiness.listRecall(chassi);
	    		
	    		//System.out.println("GetGarantiaValuesAjaxAction - Recall:" + listRecall.size());
	    		
	    		if ( listRecall != null && !listRecall.isEmpty() ) {
	    			
	    			builder.addItem("isRecall", "true");	    			
	    			
	    		} else {
	    			
	    			builder.addItem("isRecall", "false");
	    			
	    		}	
	    		
	    		//System.out.println("isEdit:" + isEdit);
	    		//System.out.println("entityId:" + entityId);
	    		
	    		Revisao nextRev = revisaoBusiness.getNextByChassi(chassi);
	    		
	    		if(Linha.TIPO_MOTOCICLETA.equals(linhaId)) {
	    			/**
	    			 * Rotina para determinar a Kilometragem mínima para o lançamento da Garantia
	    			 * 
	    			 */
	    			Long km = null;
	    			if ( isEdit != null ) { 
	    				if ( (new Boolean(isEdit)).booleanValue() )
	    					km = cupomBusiness.getLastRevision(chassi, new Long(entityId));
	    				else
	    					km = cupomBusiness.getLastRevision(chassi);
	    			} else {

	    				km = cupomBusiness.getLastRevision(chassi);

	    			}

	    			builder.addItem("kmMin", km.toString());

	    			/**
	    			 * Rotina para recuperar o parâmetro da Kilometragem máxima para o lançamento da Garantia
	    			 * 
	    			 */
	    			ParametroSistema param = garantiaHeaderBusiness.getByParameterSystem(ParametroSistema.KM_MAX_WARRANTY);

	    			builder.addItem("kmMax", param.getValorParametro());
	    			
	    			//Definimos a kilometragem limite para lançamento da garantia em realçãoa última revisão
	    			if(nextRev != null){
	    				
	    				long tempoKM  = 0;
	    				Modelo modelo = null;
	    				// Linha motoclicleta e quadriciclos
	    				double toleranciaMotoPerc = 0;	

	    				try {

	    					if (faturamento != null && faturamento.getModelo() != null )
	    						modelo = (Modelo) garantiaHeaderBusiness.get(Modelo.class, new Long(faturamento.getModelo()));
	    					if ( Modelo.VELOCIMETRO_NOT.equals(modelo.getVelocimetro()) ) {
	    						toleranciaMotoPerc = nextRev.getToleranciaMotoMes().doubleValue();
	    					} else {
	    						toleranciaMotoPerc  = nextRev.getToleranciaMotoPerc().doubleValue();
	    					}
	    					tempoKM  = nextRev.getTempoKm().longValue();	
	    					double toleranciaFinal   = NumberUtils.round((( 1 + toleranciaMotoPerc / 100 ) *  tempoKM),0);
	    					builder.addItem("kmValida", String.valueOf(toleranciaFinal));
	    				} catch (NullPointerException npExp) {
	    					builder.addItem("kmValida", "NAN");	    					   
	    				}	    				
	    			}	 
	    		} 
	    		
	    		return builder.toString();
    		
    		}

    		throw new ControllerException("A requisição fornecida não possui os parâmetros necessários.");
    	
    	} catch (BusinessException bExp) {
    		
            throw new ControllerException(bExp);
            
        } catch (UnsupportedEncodingException e) {
    		
    		throw new ControllerException(e);
			
		}
    }
    
}
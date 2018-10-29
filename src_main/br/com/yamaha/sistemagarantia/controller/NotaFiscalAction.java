/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......NotaFiscalAction.java
 *   .: Criação.....31 de maio, 12:25
 *   .: Autor.......Edson Luiz Sumensari
 *                  Thiago Uriel M. Garcia
 *   .: Descrição...Controla requisições sobre Notas Fiscais.
 */
package br.com.yamaha.sistemagarantia.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import br.com.resource.infra.controller.InfraDispatchAction;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.ControllerException;
import br.com.resource.infra.model.EntityObject;
import br.com.resource.infra.security.exception.InvalidSessionException;
import br.com.resource.infra.security.helper.UserHelper;
import br.com.resource.infra.utils.DateUtils;
import br.com.resource.infra.utils.NumberUtils;
import br.com.yamaha.sistemagarantia.business.GarantiaHeaderBusiness;
import br.com.yamaha.sistemagarantia.business.LoteBusiness;
import br.com.yamaha.sistemagarantia.business.NotaFiscalBusiness;
import br.com.yamaha.sistemagarantia.controller.helper.ControllerHelper;
import br.com.yamaha.sistemagarantia.controller.helper.YmSessionHelper;
import br.com.yamaha.sistemagarantia.model.AlertCupom;
import br.com.yamaha.sistemagarantia.model.AlertGarantia;
import br.com.yamaha.sistemagarantia.model.Cupom;
import br.com.yamaha.sistemagarantia.model.GarantiaHeader;
import br.com.yamaha.sistemagarantia.model.Linha;
import br.com.yamaha.sistemagarantia.model.Lote;
import br.com.yamaha.sistemagarantia.model.NotaFiscal;
import br.com.yamaha.sistemagarantia.model.ParametroSistema;
import br.com.yamaha.sistemagarantia.model.StatusGarantia;
import br.com.yamaha.sistemagarantia.model.StatusLote;
import br.com.yamaha.sistemagarantia.model.TipoLote;
import br.com.yamaha.sistemagarantia.model.Usuario;
import br.com.yamaha.sistemagarantia.model.to.AlertEntityTO;
import br.com.yamaha.sistemagarantia.model.to.NotaFiscalLancamentoTO;
import br.com.yamaha.sistemagarantia.view.NotaFiscalVO;

/** ontrola requisições sobre <b>Notas Fiscais</b>.
 * 
 *  @author Edson Luiz Sumensari
 */
public class NotaFiscalAction extends InfraDispatchAction {
	
	
	private static final long serialVersionUID = 1L;
	
	/** Tarefa: Gravar a data de fechamento da OS para uma garantia.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */  
    public ActionForward gravarDtFechamento( ActionMapping mapping
                            , ActionForm form
                            , HttpServletRequest request
                            , HttpServletResponse response) throws ControllerException {
    	
    	DynaActionForm notaFiscalListForm = (DynaActionForm) form;
    	ActionMessages messagesWarning    = new ActionMessages();
    	ActionMessages messagesProblem    = new ActionMessages();
    	
    	notaFiscalListForm.initialize(mapping);
    	
    	try {
    		
	    	Usuario usuario = (Usuario) UserHelper.getBoundedUser(request.getSession());
	    	GarantiaHeaderBusiness garantiaHeaderBusiness = (GarantiaHeaderBusiness) springContext.getBean("garantiaHeaderBO");
	    	NotaFiscalBusiness 	   notaFiscalBusiness 	  = (NotaFiscalBusiness) springContext.getBean("notaFiscalBO");
	    		    	
	    	Integer entityId  = request.getParameter("garantiaId") == null ? null :  Integer.valueOf(request.getParameter("garantiaId"));
	    	String  strDtFech = request.getParameter("dtFech") == null ? null :  request.getParameter("dtFech");
	    	
	    	GarantiaHeader garantia = garantiaHeaderBusiness.get(entityId);
	    	Linha linha = garantia.getLote().getLinha();
	    	
	    	
	    	if(garantia != null) {
	    		
	    		if(garantia.getDataFechamentoOS() != null) { 
	    			messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("A Garantia " + entityId + " já possui data de Fechamento da OS!") );
	                super.addProblemMessages(request, messagesProblem);
	    		} else {
	    			try {
						garantia.setDataFechamentoOS(DateUtils.parseDate(strDtFech));
						
						//Para Campanha de Moto não será preciso validar prazos de fechamento e abertura de OS
						AlertGarantia alertGarantia_PrazoEnvioEnvio = null;						
						if(!linha.isMotocicleta() && garantia.getHasCampaign()) {						
							garantiaHeaderBusiness.validatePrazoEnvio(garantia, usuario);
						}
						// Verificamos se existe alerta e  se é warning ou error
				    	if ( alertGarantia_PrazoEnvioEnvio != null ) {				    		
				    		if ( alertGarantia_PrazoEnvioEnvio.getAlertGarantiaKey().indexOf("warning") != -1 ) {				    			
				    			messagesWarning.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage(alertGarantia_PrazoEnvioEnvio.getAlertGarantiaKey(),alertGarantia_PrazoEnvioEnvio.getAtributo()) );
				    			super.addWarningMessages(request, messagesWarning);	 
				    						    		
					    	} else if ( (alertGarantia_PrazoEnvioEnvio.getAlertGarantiaKey().indexOf("error") != -1)) {					    		
					    		// Retornamos para form com os dados e a mensagem 
				    			messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage(alertGarantia_PrazoEnvioEnvio.getAlertGarantiaKey(),alertGarantia_PrazoEnvioEnvio.getAtributo()) );
				    			super.addProblemMessages(request, messagesProblem);					    		
					    	} 		    						    		
				    	} else {
				    		garantiaHeaderBusiness.saveEntity(garantia);
				    	}
				    	
					} catch (ParseException e) {
						messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("A data de Fechamento da OS informada é inválida!") );
		                super.addProblemMessages(request, messagesProblem);
					}
	    		}    		
	    		
	    	} else {
	    		messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("A Garantia " + entityId + " não foi localizada!") );
                super.addProblemMessages(request, messagesProblem);
	    	}	    	
	    	
	    	List itens = notaFiscalBusiness.listItens(usuario.getConcessionaria());
	    	
	    	List itensYMA = new ArrayList();
	    	List itensYMB = new ArrayList();
	    	
	    	if ( itens != null ) {
	    		
		    	Iterator it = itens.iterator();
		    	NotaFiscalVO notaFiscalVO = null;
		    	
		    	while ( it.hasNext() ) {
		    		
		    		notaFiscalVO = (NotaFiscalVO) it.next();
		    		
		    		if ( notaFiscalVO.getAlert() == null ) {
		    		
			    		//log.info("Valor Serviço:" + notaFiscalVO.getValorMaoObra() + " -- Valor Peça:" + notaFiscalVO.getValorPeca() + " -- Valor Peça:" + notaFiscalVO.getValorMaoObraTerc());
			    		
			    		boolean isValues = (notaFiscalVO.getValorMaoObra() == 0) && (notaFiscalVO.getValorPeca() == 0) && (notaFiscalVO.getValorMaoObraTerc() == 0)? false : true;
			    		
			    		if ( isValues ) {
			    		
				    		if ( "YMA".equalsIgnoreCase(notaFiscalVO.getDestinatario()) ) {
				    			
				    			itensYMA.add(notaFiscalVO);  
				    			
				    		} else if ( "YMB".equalsIgnoreCase(notaFiscalVO.getDestinatario()) ) {
				    			
				    			itensYMB.add(notaFiscalVO);  
				    			
				    		}
				    		
			    		}
		    		} else {
		    			
		    			AlertCupom alert = notaFiscalVO.getAlert();
		    			
		        		messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage(alert.getAlertCupomKey(),alert.getAlertCupomText()) );
		                super.addProblemMessages(request, messagesProblem);
		    			
		    		}
		    		
		    	}
		    	
	    	}
	    	notaFiscalListForm.set("listItensYMA", itensYMA);
	    	notaFiscalListForm.set("listItensYMB", itensYMB);
	    	
	    	if ( (itensYMA.isEmpty() || itensYMA.size() < 1) && (itensYMB.isEmpty() || itensYMB.size() < 1) ) {
	    		
	    		messagesWarning.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("notaFiscal.msg.lotes.empty") );
	            super.addWarningMessages(request, messagesWarning);	
	            
	    	}
	    	
	    	notaFiscalListForm.set("sysDate"       		, DateUtils.applyMask(new Date()));
	    	
    	} catch (BusinessException bExp) {
	    		
	        throw new ControllerException(bExp);
	            
	    } catch ( InvalidSessionException isExp ) {
    		
    		throw new ControllerException(isExp);
    		
		}   

        return mapping.findForward("_form");
        
    } 
		
	/** Tarefa: Apresenta o formulário de Lotes para seleção de itens.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */  
    public ActionForward list( ActionMapping mapping
                            , ActionForm form
                            , HttpServletRequest request
                            , HttpServletResponse response) throws ControllerException {
    	
    	DynaActionForm notaFiscalListForm = (DynaActionForm) form;
    	ActionMessages messagesWarning    = new ActionMessages();
    	ActionMessages messagesProblem    = new ActionMessages();
    	
    	notaFiscalListForm.initialize(mapping);
    	
    	try {
    		
	    	Usuario usuario = (Usuario) UserHelper.getBoundedUser(request.getSession());
	    	NotaFiscalBusiness 	   notaFiscalBusiness 	  = (NotaFiscalBusiness) springContext.getBean("notaFiscalBO");
	    		    	
	    	List itens = notaFiscalBusiness.listItens(usuario.getConcessionaria());
	    	
	    	List itensYMA = new ArrayList();
	    	List itensYMB = new ArrayList();
	    	
	    	if ( itens != null ) {
	    		
		    	Iterator it = itens.iterator();
		    	NotaFiscalVO notaFiscalVO = null;
		    	
		    	while ( it.hasNext() ) {
		    		
		    		notaFiscalVO = (NotaFiscalVO) it.next();
		    		
		    		if ( notaFiscalVO.getAlert() == null ) {
		    		
			    		//log.info("Valor Serviço:" + notaFiscalVO.getValorMaoObra() + " -- Valor Peça:" + notaFiscalVO.getValorPeca() + " -- Valor Peça:" + notaFiscalVO.getValorMaoObraTerc());
			    		
			    		boolean isValues = (notaFiscalVO.getValorMaoObra() == 0) && (notaFiscalVO.getValorPeca() == 0) && (notaFiscalVO.getValorMaoObraTerc() == 0)? false : true;
			    		
			    		if ( isValues ) {
			    		
				    		if ( "YMA".equalsIgnoreCase(notaFiscalVO.getDestinatario()) ) {
				    			
				    			itensYMA.add(notaFiscalVO);  
				    			
				    		} else if ( "YMB".equalsIgnoreCase(notaFiscalVO.getDestinatario()) ) {
				    			
				    			itensYMB.add(notaFiscalVO);  
				    			
				    		}
				    		
			    		}
		    		} else {
		    			
		    			AlertCupom alert = notaFiscalVO.getAlert();
		    			
		        		messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage(alert.getAlertCupomKey(),alert.getAlertCupomText()) );
		                super.addProblemMessages(request, messagesProblem);
		    			
		    		}
		    		
		    	}
		    	
	    	}
	    	notaFiscalListForm.set("listItensYMA", itensYMA);
	    	notaFiscalListForm.set("listItensYMB", itensYMB);
	    	
	    	if ( (itensYMA.isEmpty() || itensYMA.size() < 1) && (itensYMB.isEmpty() || itensYMB.size() < 1) ) {
	    		
	    		messagesWarning.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("notaFiscal.msg.lotes.empty") );
	            super.addWarningMessages(request, messagesWarning);	
	            
	    	}
	    	
	    	notaFiscalListForm.set("sysDate"       		, DateUtils.applyMask(new Date()));
	    	
    	} catch (BusinessException bExp) {
	    		
	        throw new ControllerException(bExp);
	            
	    } catch ( InvalidSessionException isExp ) {
    		
    		throw new ControllerException(isExp);
    		
		}   

        return mapping.findForward("_form");
        
    }  
    
    /** Tarefa: Recebe os itens selecionados e monta o form para lancamento das notas fiscais.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward launchNote( ActionMapping mapping
                            , ActionForm form
                            , HttpServletRequest request
                            , HttpServletResponse response) throws ControllerException {
    	
    	DynaActionForm notaFiscalListForm = (DynaActionForm) form;
    	ActionMessages messagesProblem    = new ActionMessages();
    	
    	try {
    		
    		NotaFiscalBusiness 	   notaFiscalBusiness 	  = (NotaFiscalBusiness) springContext.getBean("notaFiscalBO");
	    	
    		String  targetEmpresa = notaFiscalListForm.getString("empresa");    		
    		Integer loteId		  = notaFiscalListForm.get("loteId") != null ? (Integer)notaFiscalListForm.get("loteId"):null;			
    		
    		//log.info("LoteId:" + loteId);
    		
    		String  targetsItens[] = null;
    		//String  targetsLotes[] = null;
    		//Lote    lote = null;
    		Lote    lote = notaFiscalBusiness.getLoteBo().get(loteId);
	    	
	    	if ( "YMA".equals(targetEmpresa) ) {
	    		
	    		targetsItens = notaFiscalListForm.getStrings("selectedItemYMA");
	    		//targetsLotes = notaFiscalListForm.getStrings("selectedLotesYMA");
	    		
	    		//System.out.println("Buscando o lote selecionado para YMA!");
	    		//lote = notaFiscalBusiness.getLoteByTargets(targetsLotes);	    		
	    		
	    	}  else if ( "YMB".equals(targetEmpresa) ) {
	    		
	    		//System.out.println("Buscando o lote selecionado para YMB!");
	    		targetsItens = notaFiscalListForm.getStrings("selectedItemYMB");
	    		//targetsLotes = notaFiscalListForm.getStrings("selectedLotesYMB");
	    		
	    		//lote = notaFiscalBusiness.getLoteByTargets(targetsLotes);	
	    		
	    	} else {
	    		
	    		messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("notaFiscal.msg.launch.empty","1") );
	            super.addProblemMessages(request, messagesProblem);	 
	            
	            YmSessionHelper.removeNotaItensToSession(request);
	            return this.list(mapping,form,request,response);	    		
	    		
	    	}
	    	
	    	//System.out.println("Lote encontrado?" + lote != null);
	    	
	    	// Verificamos se foram selecioandos itens
	    	if ( targetsItens.length < 1 ) {
	    		
	    		messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("notaFiscal.msg.launch.empty","2") );
	            super.addProblemMessages(request, messagesProblem);	 
	            
	            YmSessionHelper.removeNotaItensToSession(request);
	            return this.list(mapping,form,request,response); 
	            
	    	}
	    	
	    	// Tolerância para diferença no valor do lançamento de notas fiscais
	    	ParametroSistema parametro 		 = notaFiscalBusiness.getByParameterSystem(ParametroSistema.TOLERANCIA_NOTA);
	    	// Tolerância de dias para o lançamento da nota fiscal
	    	ParametroSistema parametroDiasNF = notaFiscalBusiness.getByParameterSystem(ParametroSistema.TOLERANCIA_DIAS_NF);
	    	
	    	Long toleranciaNota    = Long.valueOf(parametro.getValorParametro());
	    	
	    	Long toleranciaDiasNF  = Long.valueOf(parametroDiasNF.getValorParametro());
	    	
	    	NotaFiscalLancamentoTO notaFiscalLancamentoTO  = notaFiscalBusiness.listItensLote(targetsItens);
	    		    	
	    	// Colocamos os iten selecionados na sessão para capturar após o lançamento das notas	    	
	    	YmSessionHelper.setNotaItensToSession(request, targetsItens);
	    		    	
	    	// Verificamos se foram selecionados itens
	    	if ( notaFiscalLancamentoTO == null ) {
	    		
	    		messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("notaFiscal.msg.launch.empty","3") );
	            super.addProblemMessages(request, messagesProblem);	 
	            
	            YmSessionHelper.removeNotaItensToSession(request);
	            return this.list(mapping,form,request,response); 
	            
	    	}
	    	
	    	request.setAttribute("idTipoLote", lote.getTipoLote().getEntityId());
	    	
	    	notaFiscalListForm.set("sysDate"       		, DateUtils.applyMask(new Date()));
	    	notaFiscalListForm.set("loteId"        		, lote.getEntityId());
	    	notaFiscalListForm.set("listItens"     		, notaFiscalLancamentoTO.getListSumario() );
	    	notaFiscalListForm.set("listMov"     		, notaFiscalLancamentoTO.getListItens() );
	    	notaFiscalListForm.set("toleranciaNota"		, toleranciaNota.toString() );
	    	notaFiscalListForm.set("toleranciaDiasNF"	, toleranciaDiasNF.toString() );
	    		    	
    	} catch (BusinessException bExp) {
    		
           throw new ControllerException(bExp);
            
       }
    	
    	return mapping.findForward("_note");    	
    }
    
    
    /** Tarefa: Recebe os itens selecionados e monta o form para lancamento das notas fiscais.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     
    public ActionForward launchNote( ActionMapping mapping
                            , ActionForm form
                            , HttpServletRequest request
                            , HttpServletResponse response) throws ControllerException {
    	
    	DynaActionForm notaFiscalListForm = (DynaActionForm) form;
    	ActionMessages messagesProblem    = new ActionMessages();
    	
    	try {
    		
    		NotaFiscalBusiness 	   notaFiscalBusiness 	  = (NotaFiscalBusiness) springContext.getBean("notaFiscalBO");
	    	
    		String targetEmpresa     = notaFiscalListForm.getString("empresa"); 
    		
    		String  targetsItens[] = null;
    		String  targetsLotes[] = null;
    		Lote    lote = null;
	    	
	    	if ( "YMA".equals(targetEmpresa) ) {
	    		
	    		targetsItens = notaFiscalListForm.getStrings("selectedItemYMA");
	    		targetsLotes = notaFiscalListForm.getStrings("selectedLotesYMA");
	    		
	    		//System.out.println("Buscando o lote selecionado para YMA!");
	    		lote = notaFiscalBusiness.getLoteByTargets(targetsLotes);	    		
	    		
	    	}  else if ( "YMB".equals(targetEmpresa) ) {
	    		
	    		//System.out.println("Buscando o lote selecionado para YMB!");
	    		targetsItens = notaFiscalListForm.getStrings("selectedItemYMB");
	    		targetsLotes = notaFiscalListForm.getStrings("selectedLotesYMB");
	    		
	    		lote = notaFiscalBusiness.getLoteByTargets(targetsLotes);	
	    		
	    	} else {
	    		
	    		messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("notaFiscal.msg.launch.empty","1") );
	            super.addProblemMessages(request, messagesProblem);	 
	            
	            YmSessionHelper.removeNotaItensToSession(request);
	            return this.list(mapping,form,request,response);	    		
	    		
	    	}
	    	
	    	//System.out.println("Lote encontrado?" + lote != null);
	    	
	    	// Verificamos se foram selecioandos itens
	    	if ( targetsItens.length < 1 ) {
	    		
	    		messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("notaFiscal.msg.launch.empty","2") );
	            super.addProblemMessages(request, messagesProblem);	 
	            
	            YmSessionHelper.removeNotaItensToSession(request);
	            return this.list(mapping,form,request,response); 
	            
	    	}
	    	
	    	// Tolerância para diferença no valor do lançamento de notas fiscais
	    	ParametroSistema parametro 		 = notaFiscalBusiness.getByParameterSystem(ParametroSistema.TOLERANCIA_NOTA);
	    	// Tolerância de dias para o lançamento da nota fiscal
	    	ParametroSistema parametroDiasNF = notaFiscalBusiness.getByParameterSystem(ParametroSistema.TOLERANCIA_DIAS_NF);
	    	
	    	Long toleranciaNota    = Long.valueOf(parametro.getValorParametro());
	    	
	    	Long toleranciaDiasNF  = Long.valueOf(parametroDiasNF.getValorParametro());
	    	
	    	List itens = notaFiscalBusiness.listItensLote(targetsItens);
	    	
	    	// Colocamos os iten selecionados na sessão para capturar após o lançamento das notas	    	
	    	YmSessionHelper.setNotaItensToSession(request, targetsItens);
	    		    	
	    	// Verificamos se foram selecioandos itens
	    	if ( itens.isEmpty() ) {
	    		
	    		messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("notaFiscal.msg.launch.empty","3") );
	            super.addProblemMessages(request, messagesProblem);	 
	            
	            YmSessionHelper.removeNotaItensToSession(request);
	            return this.list(mapping,form,request,response); 
	            
	    	}
	    	
	    	request.setAttribute("idTipoLote", lote.getTipoLote().getEntityId());
	    	
	    	notaFiscalListForm.set("sysDate"       		, DateUtils.applyMask(new Date()));
	    	notaFiscalListForm.set("loteId"        		, lote.getEntityId());
	    	notaFiscalListForm.set("listItens"     		, itens );
	    	notaFiscalListForm.set("toleranciaNota"		, toleranciaNota.toString() );
	    	notaFiscalListForm.set("toleranciaDiasNF"	, toleranciaDiasNF.toString() );
	    		    	
    	} catch (BusinessException bExp) {
    		
           throw new ControllerException(bExp);
            
       }
    	
    	return mapping.findForward("_note");    	
    }
    */ 
    /** Tarefa: Recebe uma entidade de Cupom ou Garantia e monta o form para lancamento das notas fiscais.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward launchNoteByItem( ActionMapping mapping
                            , ActionForm form
                            , HttpServletRequest request
                            , HttpServletResponse response) throws ControllerException {
    	
    	DynaActionForm notaFiscalListForm = (DynaActionForm) form;
    	ActionMessages messagesProblem    = new ActionMessages();
    	
    	
    	try {
    		
    		Cupom 			cupom  	 = null;
    		GarantiaHeader 	garantia = null;
    		Lote			lote     = null;
    		List 			itens 	 = new ArrayList();
    		String  targetsItens[]   = new String[1];
    		boolean isRevisao		 = true; // Identifica se é revisão ou garantia - TRUE para Revisão(Cupom)
    		
    		EntityObject 		entity 				= YmSessionHelper.getEntityObjectFromSession(request);
    		
    		NotaFiscalBusiness 	notaFiscalBusiness 	= (NotaFiscalBusiness) springContext.getBean("notaFiscalBO");
    		
    		if ( entity instanceof Cupom ) {    			
    			
    			cupom = (Cupom) entity;  
    			lote = cupom.getLote();
    			targetsItens[0] = cupom.getLote().getEntityId() + "@" + cupom.getEntityId() + "@" + TipoLote.TIPO_REVISAO;
    			
    			itens = notaFiscalBusiness.getItembyCupom(cupom);
    			    			
    			notaFiscalListForm.set("pathBack"			, "_listCupom");
    			
    		} else if ( entity instanceof GarantiaHeader ) {    			
    			
    			isRevisao = false;
    			
    			garantia = (GarantiaHeader) entity;
    			lote = garantia.getLote();
    			targetsItens[0] = garantia.getLote().getEntityId() + "@" + garantia.getEntityId() + "@" + TipoLote.TIPO_GARANTIA;    			
    			
    			itens = notaFiscalBusiness.getItembyGarantia(garantia);
    			
    			notaFiscalListForm.set("pathBack"			, "_listGarantia");
    			
    		}    		
    		
    		//log.info("Garantia - Item:" + targetsItens[0]);
    		
    		// Removemos o EntityObject da Sessão
    		YmSessionHelper.removeNotaItensToSession(request);
    		
    		ParametroSistema parametro = notaFiscalBusiness.getByParameterSystem(ParametroSistema.TOLERANCIA_NOTA);
	    	
	    	Long toleranciaNota  = Long.valueOf(parametro.getValorParametro());
	    	
	    	//log.info("Tolerância:" +  toleranciaNota);
	    	
	    	//List itens = notaFiscalBusiness.listItensLote(targetsItens);
	    	
	    	// Colocamos os itens selecionados na sessão para capturar após o lançamento das notas	    	
	    	YmSessionHelper.setNotaItensToSession(request, targetsItens);
	    		    	
	    	//log.info("Itens encontrados:" + itens.size());
	    	
	    	//  Verificamos se foram selecioandos itens
	    	if ( itens.isEmpty() ) {
	    		
	    		messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("notaFiscal.msg.launch.empty") );
	            super.addProblemMessages(request, messagesProblem);	 
	            
	            //Verificamos se é Garantia ou Cupom e retornamos para lista de itens
	            if ( isRevisao ) {
	            	request.setAttribute("cupomId", cupom.getEntityId());
	            
	            	return mapping.findForward("_cupom");
	            } else {
	            	
	            	request.setAttribute("garantiaId", garantia.getEntityId());
	            	
	            	return mapping.findForward("_garantia");
	            }
	            
	    	} 
	    	notaFiscalListForm.set("sysDate"            , DateUtils.applyMask(new Date()));
	    	notaFiscalListForm.set("loteId"        		, lote.getEntityId());
	    	notaFiscalListForm.set("listItens"     		, itens );
	    	notaFiscalListForm.set("toleranciaNota"		, toleranciaNota.toString() );
	    	notaFiscalListForm.set("individualLaunch"	, "S");
	    	
	    	//Definimos o path para retorno ao item cadastrado 
	    	String pathBack = isRevisao ? "_listCupom" : "_listGarantia";
	    	notaFiscalListForm.set("pathBack"			, pathBack);
	    	
    	} catch (BusinessException bExp) {
    		
            throw new ControllerException(bExp);
             
        }
     	
     	return mapping.findForward("_note");  
    	
    }
    
    /** Tarefa: Recebe as notas lançadas e realiza a gravação no banco.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward save( ActionMapping mapping
                            , ActionForm form
                            , HttpServletRequest request
                            , HttpServletResponse response) throws ControllerException {
    	
    	DynaActionForm notaFiscalListForm = (DynaActionForm) form;
    	ActionMessages 			messagesWarning    = new ActionMessages();
    	ActionMessages 			messagesSuccess    = new ActionMessages();
    	ActionMessages 			messagesProblem    = new ActionMessages();
    	
    	try {
    		
    		int contField = Integer.parseInt(request.getParameter("contField"));
    		
    		if ( contField < 1 ) {
    			
    			messagesWarning.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("notaFiscal.msg.notas.empty") );
	            super.addWarningMessages(request, messagesWarning);
	            
	            return mapping.findForward("_note"); 
    		}
    		
			//CupomBusiness 	       cupomBusiness 	      = (CupomBusiness) springContext.getBean("cupomBO");
			//GarantiaHeaderBusiness garantiaHeaderBusiness = (GarantiaHeaderBusiness) springContext.getBean("garantiaHeaderBO");
			NotaFiscalBusiness     notaFiscalBusiness     = (NotaFiscalBusiness) springContext.getBean("notaFiscalBO");
			
			Usuario usuario = (Usuario) UserHelper.getBoundedUser(request.getSession());
			Integer loteId = (Integer)notaFiscalListForm.get("loteId");
			
			boolean individualLaunch = "S".equals(notaFiscalListForm.getString("individualLaunch")) ? true : false;
			boolean isDif 			 = "S".equals(notaFiscalListForm.getString("isDif")) ? true : false;
			String  pathBack		 = notaFiscalListForm.getString("pathBack");
						
			Lote lote = notaFiscalBusiness.getLoteBo().get(loteId);
			
			String targetsItens[]    = YmSessionHelper.getNotaItensFromSession(request);
			
			if ( targetsItens == null ) {
				
				messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("logon.form.session.expired") );
	            super.addProblemMessages(request, messagesProblem);
	            
	            return mapping.findForward("sessionExpired");  
			}
			
			List cupons 	= null;
			List garantias  = null;
			
			// Recuperamos a lista de itens selecionados
			if ( contField > 0 ) {
				
				if ( TipoLote.TIPO_REVISAO.equals(lote.getTipoLote().getEntityId()) ) {
					
					cupons = notaFiscalBusiness.listCuponsSelected(targetsItens);
					
				} else if ( TipoLote.TIPO_GARANTIA.equals(lote.getTipoLote().getEntityId()) ) {
				
					garantias = notaFiscalBusiness.listGarantiasSelected(targetsItens);
				}
			}			
			
			//List notas = new ArrayList();
			
			for ( int i = 1 ; i <= contField ; i++ ) {
				
				//log.info("-----> Nota envida:" + new Date() + "  -  " + request.getParameter("numeroNF_" + i));
				
				if ( request.getParameter("numeroNF_" + i) != null ) {
				
					// Coletamos os valores enviados pelo form de lançamento da notas
					Long   numeroNF       	= Long.valueOf(request.getParameter("numeroNF_" + i));
					String itensServNF     	= request.getParameter("itensServNF_" + i);
					String itensPecNF       = request.getParameter("itensPecNF_" + i);
					String itensRemNF      	= request.getParameter("itensRemNF_" + i);
					String itensMO3NF      	= request.getParameter("itensMO3NF_" + i);
					String serieNF        	= request.getParameter("serieNF_" + i);
					String emitenteNota   	= request.getParameter("emitenteNota_" + i);
					String destinatarioNota = request.getParameter("destinatarioNota_" + i);
					Date   dateNF         	= DateUtils.secureSet(request.getParameter("dateNF_" + i));
					double serviceValueNF 	= request.getParameter("serviceValueNF_" + i) != null ? Double.parseDouble(request.getParameter("serviceValueNF_" + i)): 0;
					double pecaValueNF    	= request.getParameter("pecaValueNF_" + i) != null ? Double.parseDouble(request.getParameter("pecaValueNF_" + i)) : 0;
					double remessaValueNF 	= request.getParameter("remessaValueNF_" + i) != null ? Double.parseDouble(request.getParameter("remessaValueNF_" + i)) : 0;
					double mo3ValueNF 	  	= request.getParameter("mo3ValueNF_" + i) != null ? Double.parseDouble(request.getParameter("mo3ValueNF_" + i)) : 0;
					
					boolean serviceCheck  = "true".equalsIgnoreCase(request.getParameter("serviceCheck_" + i)) ? true :false;
					boolean pecaCheck     = "true".equalsIgnoreCase(request.getParameter("pecaCheck_" + i)) ? true : false ;
					boolean remessaCheck  = "true".equalsIgnoreCase(request.getParameter("remessaCheck_" + i)) ? true : false;
					boolean mo3Check      = "true".equalsIgnoreCase(request.getParameter("mo3Check_" + i)) ? true : false;
					boolean conjCheck     = "true".equalsIgnoreCase(request.getParameter("conjCheck_" + i)) ? true : false;
					/*
					log.info("//********   Checks selecionados - Serviço:"   + serviceCheck + 
							 " - Peça:" + pecaCheck + 
							 " - Remessa:" + remessaCheck + 
							 " - MO3:" + mo3Check + 
							 " - Conj:" + conjCheck);
					*/					
					//***************************************************************************************//
					//*                      Log das informações obtidas do FORM						    *//
					//***************************************************************************************//
					
					//log.info("//******************************************************************* //");
					/*
					log.info(new Date() + "  -  " + "//********   Concessionária:" + usuario.getConcessionaria().getRazaoSocial());
					log.info("//********   Lote:" 								+ lote.getEntityId());
					log.info("//********   Número da Nota Fiscal:" 				+ numeroNF); 
					log.info("//********   Itens Seriços Associados a Nota:" 	+ itensServNF);
					log.info("//********   Itens Peças Associados a Nota:" 		+ itensPecNF);
					log.info("//********   Itens Remessa Associados a Nota:" 	+ itensRemNF);
					log.info("//********   Itens MO3 Associados a Nota:" 		+ itensMO3NF);
					log.info("//********   Série da Nota Fiscal:"  				+ serieNF);
					log.info("//********   Emitente da Nota Fiscal:"  			+ emitenteNota);
					log.info("//********   Destinatário da Nota Fiscal:"  		+ destinatarioNota);
					log.info("//********   Data da Nota Fiscal:"   				+ dateNF);
					log.info("//********   Valor de Serviço:"      				+ serviceValueNF);
					log.info("//********   Valor de peça:"         				+ pecaValueNF);
					log.info("//********   Valor de remessa:"      				+ remessaValueNF);
					log.info("//********   Valor de mo3:"          				+ mo3ValueNF);
					*/
					//**************************************************************************************//
					//*  Validação - Verificar se a nota fiscal já foi lançada anteriormente			   *//
					//**************************************************************************************//
					if ( notaFiscalBusiness.isEcxistNotaBySerie(numeroNF, serieNF, usuario.getConcessionaria()) ) {	
						
						//log.info(" A Nota Fiscal " + numeroNF + " já foi lançada!");
						
						messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("notaFiscal.msg.error.notaIsEcxist",numeroNF) );
			            super.addProblemMessages(request, messagesProblem);
			            
			            YmSessionHelper.removeNotaItensToSession(request);
			            
			            return this.list(mapping, form, request, response);
						
					}
										
					// Montamos o objeto Nota Fiscal com os valores enviados
					NotaFiscal notaFiscal = new NotaFiscal();
					
					// Dados da Nota Fiscal
					notaFiscal.setConcessionaria(usuario.getConcessionaria());
					notaFiscal.setDataNF(dateNF);
					notaFiscal.setEmissor("Conc".equals(emitenteNota)? "C" :"O" );										
					notaFiscal.setDestinatario(destinatarioNota);
					
					notaFiscal.setNumeroNF(numeroNF);
					notaFiscal.setSerieNF(serieNF.toUpperCase());
										
					if ( serviceCheck  && mo3Check ) 
						notaFiscal.setServiceValueNF(new Double(NumberUtils.round(serviceValueNF + mo3ValueNF,2)));
					else if (mo3Check)
						notaFiscal.setServiceValueNF(new Double(NumberUtils.round(mo3ValueNF,2)));
					else if (serviceCheck)
						notaFiscal.setServiceValueNF(new Double(NumberUtils.round(serviceValueNF,2)));
					
					if ( pecaCheck )
						notaFiscal.setPecaValueNF(new Double(NumberUtils.round(pecaValueNF,2)));
					
					if ( remessaCheck )
						notaFiscal.setPecaValueNF(new Double(NumberUtils.round(remessaValueNF,2)));
					
					if ( conjCheck ) {
						
						if ( pecaValueNF > 0 && ( (serviceValueNF > 0 || mo3ValueNF > 0) ) ) { 
							notaFiscal.setServiceValueNF(new Double(NumberUtils.round(serviceValueNF + mo3ValueNF,2)));
							notaFiscal.setPecaValueNF(new Double(NumberUtils.round(pecaValueNF,2)));
						} else {							
							messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("notaFiscal.msg.error.valorPeca ") );
				            super.addProblemMessages(request, messagesProblem);
				            
				            YmSessionHelper.removeNotaItensToSession(request);
				            
				            return this.list(mapping,form,request,response);
						}
							
					}
					/*
					log.info("----> Tipo de Nota - Nota:" 	+ notaFiscal.getNumeroNF() + 
							 " - pecaCheck:" 				+ pecaCheck + 
							 " - serviceCheck:" 			+ serviceCheck + 
							 " - mo3Check:" 				+ mo3Check + 
							 " - remessaCheck:" 			+ remessaCheck + 
							 " - conjCheck:" 				+ conjCheck);
					*/
					if ( pecaCheck && !serviceCheck && !mo3Check && !remessaCheck && !conjCheck ) 
						notaFiscal.setTipoNFId(NotaFiscal.TIPO_NOTA_PECA);
					else if ( !pecaCheck && serviceCheck && !mo3Check && !remessaCheck && !conjCheck )
						notaFiscal.setTipoNFId(NotaFiscal.TIPO_NOTA_SERVICO);
					else if ( !pecaCheck && (serviceCheck && mo3Check) && !remessaCheck && !conjCheck )
						notaFiscal.setTipoNFId(NotaFiscal.TIPO_NOTA_SERVICO);
					else if ( (pecaCheck && serviceCheck) || (pecaCheck && serviceCheck && mo3Check) || (pecaCheck && mo3Check) )
						notaFiscal.setTipoNFId(NotaFiscal.TIPO_NOTA_CONJUGADA);
					else if ( !pecaCheck && !serviceCheck && !mo3Check && conjCheck )
						notaFiscal.setTipoNFId(NotaFiscal.TIPO_NOTA_CONJUGADA);
					else if ( !pecaCheck && !serviceCheck && !mo3Check && remessaCheck )
						notaFiscal.setTipoNFId(NotaFiscal.TIPO_NOTA_REMESSA);
					else if ( !pecaCheck && !serviceCheck && mo3Check && !remessaCheck )
						notaFiscal.setTipoNFId(NotaFiscal.TIPO_NOTA_SERVICO_TERCEIRO);
					else {
						messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("notaFiscal.msg.error.tipoNota",numeroNF) );
			            super.addProblemMessages(request, messagesProblem);
			            
			            YmSessionHelper.removeNotaItensToSession(request);
			            
			            return this.list(mapping,form,request,response); 						
					}
					
					//log.info("-------------> Tipo de Nota Definido: " + notaFiscal.getTipoNFId());					
					
					//**************************************************************************************//
					//*  Recuperamos os itens selecionados	e associamos com a note						   *//
					//**************************************************************************************//	
					
					Iterator it 	 = null;
					// Se o lote for de revisão, recuperamos os cupons e associamos com a nota
					if ( TipoLote.TIPO_REVISAO.equals(lote.getTipoLote().getEntityId()) ) {		
						
						String 	 itensServ[] = itensServNF.split("@");
						// Recuperamos os cupons de acordo com array enviado
						List cuponsAssoc = notaFiscalBusiness.listCuponsAssoc(cupons,itensServ);
						
						if ( cuponsAssoc != null ) {
							
							it = cuponsAssoc.iterator();
							while ( it.hasNext() ) {
								
								Cupom cupom = (Cupom)it.next();
								cupom.setStatusMovId(StatusGarantia.STATUS_NF_DIGITADA);
								cupom.setStatusGarantia(StatusGarantia.getInstance(StatusGarantia.STATUS_NF_DIGITADA));
								notaFiscal.addCupom(cupom);
							}
							
						} else {
							
							// quando não for encontrado cupom para associar, lançamos uma mensgem de erro
							messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("cupom.msg.error.cupomNotFound",numeroNF) );
				            super.addProblemMessages(request, messagesProblem);
				            
				            YmSessionHelper.removeNotaItensToSession(request);
				            
				            return this.list(mapping,form,request,response); 
							
						}
						
					} else if ( TipoLote.TIPO_GARANTIA.equals(lote.getTipoLote().getEntityId()) ) { 		
					/*
						log.info("itensServNF:"   + itensServNF  + 
								 " -itensPecNF:"  + itensPecNF   + 
								 " -itensRemNF:"  + itensRemNF   + 
								 " -itensMO3NF:"  + itensMO3NF   );
						*/						
						// Recuperamos as garantias de acordo com os arrays enviados
						List garantiasAssoc  = notaFiscalBusiness.listGarantiasAssoc(
																				garantias
																				, itensServNF
																				, itensPecNF
																				, itensRemNF
																				, itensMO3NF		
																				, notaFiscal
																			  );
					
						if ( garantiasAssoc != null )
							notaFiscal.setGarantias(garantiasAssoc);
						else {
							
							messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("notaFiscal.msg.error.sgNotFound",notaFiscal.getNumeroNF()) );
				            super.addProblemMessages(request, messagesProblem);
				            
				            YmSessionHelper.removeNotaItensToSession(request);
				            
				            return this.list(mapping,form,request,response); 
							
						}
					}
					notaFiscal.setLote(lote);
					ControllerHelper.prepare(notaFiscal, (Long)usuario.getEntityId());
					lote.setNotas(notaFiscal);
					ControllerHelper.prepare(lote, (Long)usuario.getEntityId());
					//notas.add(notaFiscal);
					
				}
			}
		
			//log.info("------> Tem difirença no lançamento:" + isDif);
			if ( !isDif ) {
				
				AlertEntityTO alert = notaFiscalBusiness.save(lote);
				
				if ( alert.getAlertKey().indexOf("error") != -1   ) {
					
					messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage(alert.getAlertKey(),alert.getAlertText()) );
		            super.addProblemMessages(request, messagesProblem);
		            
		            YmSessionHelper.removeNotaItensToSession(request);
		            
		            return this.list(mapping,form,request,response); 
					
				}
				
			} else {
				
				messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("notaFiscal.msg.error.isDif") );
	            super.addProblemMessages(request, messagesProblem);
	            
	            YmSessionHelper.removeNotaItensToSession(request);
	            
	            return this.list(mapping,form,request,response); 			
				
			}
			
			//Quando for do tipo GARANTIA verificamos se podemos liberar o lote
			if(TipoLote.TIPO_GARANTIA.equals(lote.getTipoLote().getEntityId())){
				
				lote = notaFiscalBusiness.getLoteBo().get(lote.getEntityId());
				Iterator iSg = lote.getGarantias().iterator();
				GarantiaHeader sg = null;
				boolean isLiberate = true;
				while(iSg.hasNext()){										
					sg = (GarantiaHeader) iSg.next();
					if(!StatusGarantia.STATUS_AGUARDANDO_ENVIO_DOCUMENTOS.equals(sg.getStatusGarantia().getEntityId())){											
						isLiberate = false;
					}										
				}
				
				//Quando toda as garantias do lote estiverem com nota fiscal digitada, passamos o lote para Aguarda rec de documentos
				if(isLiberate) {
					
					StatusLote statusLote = new StatusLote();
					statusLote.setEntityId(StatusLote.STATUS_AGUARDANDO_RECEB_DOC);
					lote.setStatusLote(statusLote);
					if(lote.getDataLiberacao() == null) {						
						lote.setDataLiberacao(new Date());
					}
					notaFiscalBusiness.getLoteBo().save(lote);
					
				}
				
			}
			
			YmSessionHelper.removeNotaItensToSession(request);
			
			messagesSuccess.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("notaFiscal.msg.success.save") );
            super.addSuccessMessages(request, messagesSuccess);
            
            //log.info("------> IndividualLaunch:" + individualLaunch);
            
            if ( individualLaunch ) {
            	
            	request.setAttribute("loteId", lote.getEntityId());
            	return mapping.findForward(pathBack);
            	
            }
	    		
	    } catch (BusinessException bExp) {
    		
            throw new ControllerException(bExp);
            
        } catch ( InvalidSessionException isExp ) {
    		
    		throw new ControllerException(isExp);
    		
		}  
    	
    	return this.list(mapping,form,request,response);    	
    }
}

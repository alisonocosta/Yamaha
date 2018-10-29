/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......EstoqueFormAction.java
 *   .: Criação.....16 de abril, 14:11
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Controla requisições sobre Estoque.
 */
package br.com.yamaha.sistemagarantia.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.validator.DynaValidatorActionForm;

import br.com.resource.infra.controller.InfraDispatchAction;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.BusinessRuleException;
import br.com.resource.infra.exception.ControllerException;
import br.com.resource.infra.security.exception.InvalidSessionException;
import br.com.resource.infra.security.helper.UserHelper;
import br.com.resource.infra.utils.DateUtils;
import br.com.yamaha.sistemagarantia.business.EstoqueBusiness;
import br.com.yamaha.sistemagarantia.business.FaturamentoBusiness;
import br.com.yamaha.sistemagarantia.controller.helper.ControllerHelper;
import br.com.yamaha.sistemagarantia.model.AlertGarantia;
import br.com.yamaha.sistemagarantia.model.Concessionaria;
import br.com.yamaha.sistemagarantia.model.Estoque;
import br.com.yamaha.sistemagarantia.model.Faturamento;
import br.com.yamaha.sistemagarantia.model.ParametroSistema;
import br.com.yamaha.sistemagarantia.model.Usuario;

/** Classe controla as tarefas relacionadas ao <b>Estoque</b>.
 * 
 *  @author Edson Luiz Sumensari
 */
public class EstoqueAction extends InfraDispatchAction {

   
	private static final long serialVersionUID = 1L;
    
    /** Tarefa: Listagem.
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
     *  @throws ControllerException
     *      Se houverem erros, estes serão lançados como uma exception
     *      deste tipo.
     */ 
    public ActionForward list( ActionMapping mapping
                             , ActionForm form
                             , HttpServletRequest request
                             , HttpServletResponse response) throws ControllerException {

        try {
        	
        	EstoqueBusiness estoqueBusiness = (EstoqueBusiness) springContext.getBean("estoqueBO");
        	Usuario usuario = (Usuario) UserHelper.getBoundedUser(request.getSession());
        	
        	List list = estoqueBusiness.listByConcessionaria(usuario.getConcessionaria());
        	        	
            DynaActionForm actionForm = (DynaValidatorActionForm) form;
            actionForm.initialize( mapping );
            
            ParametroSistema parametro = estoqueBusiness.getByParameterSystem(ParametroSistema.DATA_INVENTARIO);
            
            if ( list != null )
            	actionForm.set( "listResults" , list );
            else
            	actionForm.set( "listResults" , new ArrayList() );
            
            if ( parametro != null )
            	request.setAttribute("dataEstoque" , parametro.getValorParametro() );
            else
            	throw new BusinessException("O Parâmetro DATA DO INVENTÁRIO não está cadastrado!");
            
            return mapping.findForward("_list");

        } catch (BusinessException bExp) {
            
            throw new ControllerException(bExp);
            
        } catch ( InvalidSessionException isExp ) {
    		
    		throw new ControllerException(isExp);
    		
		}  
        
    } 
    
    /** Tarefa: Remover entidade.
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
     *  @throws ControllerException
     *      Se houverem erros, estes serão lançados como uma exception
     *      deste tipo.
     */ 
    public ActionForward remove( ActionMapping mapping
                               , ActionForm form
                               , HttpServletRequest request
                               , HttpServletResponse response) throws ControllerException {
        
    	
    	DynaActionForm actionForm = (DynaValidatorActionForm) form;        
        ActionMessages 			messagesSuccess    = new ActionMessages();
    	ActionMessages 			messagesProblem    = new ActionMessages();
    	ActionMessages 			messagesWarning    = new ActionMessages();
        
        try {
	        // Primeiro, pegamos nossos alvos e quebramos em
	        // um array de Strings.
	        String targets[] = actionForm.getStrings("deleteTargets");
	        	        
	        Usuario 		usuario 	= (Usuario) UserHelper.getBoundedUser(request.getSession());
	        Concessionaria 	concessionaria = usuario.getConcessionaria();
	    	
	    	Estoque 		estoque 		= null;
	    	List 			listEstoque 	= new ArrayList();
	        EstoqueBusiness estoqueBusiness = (EstoqueBusiness) springContext.getBean("estoqueBO");
	        	        
	        //log.info("Itens selecionados:" + targets.length);
	        
	        for ( int i=0; i<targets.length; i++ ) {   
	        	
	        	//log.info("Valor recuperado do id:" + targets[i]);
	        	
        		estoque = estoqueBusiness.get(new Long(targets[i]));
        		
        		//log.info("Estoque null? " + (estoque == null) );
        		
        		if ( estoque != null ) {
        			
        			if ( estoque.getConcessionaria().getEntityId().equals(concessionaria.getEntityId()) )
        				listEstoque.add(estoque);
        			else {
        				
        				messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("estoque.form.msg.error.notExc") );
    					super.addProblemMessages(request, messagesProblem);	
        				
    					return this.list(mapping, form, request, response);
        			}
        			
        		}
        		
	        }
	        
	        if ( !listEstoque.isEmpty() ) {
	        	
	        	AlertGarantia alerta = estoqueBusiness.removeList(listEstoque);
	        	
	        	if ( alerta.getAlertGarantiaKey().indexOf("error") != -1 ) {
					
		        	messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage(alerta.getAlertGarantiaKey()) );
					super.addProblemMessages(request, messagesProblem);		
					
				} else if ( alerta.getAlertGarantiaKey().indexOf("success") != -1 ) {
					
					messagesSuccess.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage(alerta.getAlertGarantiaKey()) );
	            	super.addSuccessMessages(request, messagesSuccess);
				}
	        	
	        } else {
	        	
	        	messagesWarning.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("estoque.form.msg.warning.notFound") );
	            super.addWarningMessages(request, messagesWarning);
	        	
	        }
	        
        } catch (BusinessException bExp) {
            
            throw new ControllerException(bExp);
            
        } catch ( InvalidSessionException isExp ) {
    		
    		throw new ControllerException(isExp);
    		
		}  
        
        return this.list(mapping, form, request, response);
        
    }
    
    /** Tarefa: Grava as informações enviadas do formulário de Estoque.
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
    	
    	DynaValidatorActionForm clienteForm = (DynaValidatorActionForm) form;
    	ActionMessages 			messagesSuccess    = new ActionMessages();
    	ActionMessages 			messagesProblem    = new ActionMessages();
    	ActionMessages 			messagesWarning    = new ActionMessages();
    	
    	String chassi = clienteForm.getString("chassi");
    	
    	try {
    		
	    	// Usuario logado
	    	Usuario usuario = (Usuario) UserHelper.getBoundedUser(request.getSession());
	    	
	    	FaturamentoBusiness faturamentoBusiness = (FaturamentoBusiness) springContext.getBean("faturamentoBO");
    		EstoqueBusiness 	estoqueBusiness 	= (EstoqueBusiness) springContext.getBean("estoqueBO");
	    	
	    	Faturamento faturamento = faturamentoBusiness.getByChassi(chassi.toUpperCase());
	    	
	    	if ( faturamento !=  null ) {
    			
    			ParametroSistema parametro = estoqueBusiness.getByParameterSystem(ParametroSistema.DATA_INVENTARIO);
    			
    			if ( parametro == null )
    				throw new BusinessException("O parâmetro de sistema DATA DO INVENTÁRIO não está cadastrado!");
    			
    			Date dataEstoque = DateUtils.secureSet(parametro.getValorParametro());
    			
    			Estoque estoque = new Estoque();	    			
    			estoque.setFaturamentoId( (Long)faturamento.getEntityId() );
    			estoque.setConcessionaria(usuario.getConcessionaria());
    			estoque.setDataEstoque(dataEstoque);
    			ControllerHelper.prepare( estoque, (Long)usuario.getIdentifier() );
    			
    			AlertGarantia alert  = estoqueBusiness.save(estoque);
    			
    			if ( !faturamento.getConcessionaria().getEntityId().equals(usuario.getConcessionaria().getEntityId()) ) {
    				        			
    				messagesWarning.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("estoque.form.msg.warning.faturamentoOther", chassi.toUpperCase()) );
    	            super.addWarningMessages(request, messagesWarning);
        			
        		}
    			
    			if ( alert.getAlertGarantiaKey().indexOf("error") != -1 ) {
    				
    				messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( alert.getAlertGarantiaKey(), chassi.toUpperCase()) );
    	            super.addProblemMessages(request, messagesProblem);
    				
    			} else if ( alert.getAlertGarantiaKey().indexOf("success") != -1 ) {
    			
    				messagesSuccess.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage(alert.getAlertGarantiaKey(), chassi.toUpperCase()) );
    				super.addSuccessMessages(request, messagesSuccess);
    			}
    			
    		} else {
    			
	            messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("estoque.form.msg.error.faturamento.notFound", chassi.toUpperCase()) );
	            super.addProblemMessages(request, messagesProblem);
    			
    		}	 
            	
	    	return this.list(mapping, form, request, response);
         
    	} catch (BusinessRuleException brExp) {
                
    		messagesWarning.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("cupom.msg.error.chassi", chassi.toUpperCase()) );
            super.addWarningMessages(request, messagesWarning);
            
            return this.list(mapping, form, request, response);
                
    	} catch (BusinessException bExp) {
                
            throw new ControllerException(bExp);
                
    	} catch ( InvalidSessionException isExp ) {
        	
        	throw new ControllerException(isExp);
        	
        } 

    } 
}
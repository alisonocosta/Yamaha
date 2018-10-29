/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......CupomAlterAction.java
 *   .: Criação.....29 de maio, 15:04
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Action para tratar CupomAlter.
 */
package br.com.yamaha.sistemagarantia.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.validator.DynaValidatorActionForm;

import br.com.resource.infra.controller.InfraDispatchAction;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.BusinessRuleException;
import br.com.resource.infra.exception.ControllerException;
import br.com.resource.infra.security.exception.InvalidSessionException;
import br.com.resource.infra.security.helper.UserHelper;
import br.com.yamaha.sistemagarantia.business.AlertCupomBusiness;
import br.com.yamaha.sistemagarantia.business.CupomBusiness;
import br.com.yamaha.sistemagarantia.business.FaturamentoBusiness;
import br.com.yamaha.sistemagarantia.business.TipoUsoBarcoBusiness;
import br.com.yamaha.sistemagarantia.model.AlertCupom;
import br.com.yamaha.sistemagarantia.model.Cupom;
import br.com.yamaha.sistemagarantia.model.Faturamento;
import br.com.yamaha.sistemagarantia.model.TipoUsoBarco;
import br.com.yamaha.sistemagarantia.model.Usuario;

/** Classe que controla as tarefas relacionadas ao <b>CupomAlter</b>.
 * 
 *  @author Edson Luiz Sumensari
 */
public class CupomAlterAction extends InfraDispatchAction {

    
	private static final long serialVersionUID = 1L;
	
    
    /** Tarefa: Monta a tela de alteração.
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
    public ActionForward alter( ActionMapping mapping
                             , ActionForm form
                             , HttpServletRequest request
                             , HttpServletResponse response) throws ControllerException {
    	
    	try { 
    		
    		DynaValidatorActionForm cupomAlterForm = (DynaValidatorActionForm) form;   		
            
    		CupomBusiness 		   cupomBusiness 		 = (CupomBusiness)        springContext.getBean("cupomBO");	
    		TipoUsoBarcoBusiness   tipoUsobarcoBusiness  = (TipoUsoBarcoBusiness) springContext.getBean("tipoUsoBarcoBO");
    		//RevisaoBusiness		   revisaoBusiness	     = (RevisaoBusiness)	  springContext.getBean("revisaoBO"	 );
    		
    		//Long numberId  = null;
    		//Long revisaoId = null;
    		
    		Long cupomId  = null;
    		
    		// Recuperação dos valores para a criação da cheve composta do cupom - revisao e número de cupom    		
    		try { 
    			
    			cupomId = Long.valueOf(request.getParameter("cupomId")); 
    			//numberId   = Long.valueOf(request.getParameter("numberCupom"));    						  
    			//revisaoId  = Long.valueOf(request.getParameter("revisaoId"));
            				  
    		} catch ( NumberFormatException npExp ) { 
    			
    			cupomId = (Long)cupomAlterForm.get("entityId");
    			//numberId   = (Long)cupomAlterForm.get("entityId");
    			//revisaoId  = (Long)cupomAlterForm.get("revisaoId");
    			
    		}
    		
    		cupomAlterForm.initialize(mapping);
    		
    		//Revisao revisao = revisaoBusiness.get(revisaoId);
    		
    		//Montamos  a Chave composta
    		//CupomId cupomId = new CupomId(revisao, numberId);
    		
    		Cupom cupom = cupomBusiness.get(cupomId);
    		
    		request.setAttribute("cupom", cupom);
    		
    		List listUseType = tipoUsobarcoBusiness.list();
    		
    		cupomAlterForm.set("quilometragem" , cupom.getQuilometragem() != null ? cupom.getQuilometragem() : new Long(0));
    		cupomAlterForm.set("diasUso"       , cupom.getDiasUso() != null ? cupom.getDiasUso() : new Long(0));
    		cupomAlterForm.set("horasUso"      , cupom.getHorasUso() != null ? cupom.getHorasUso() : new Long(0));
    		cupomAlterForm.set("horasUso"      , cupom.getHorasUso() != null ? cupom.getHorasUso() : new Long(0));
    		cupomAlterForm.set("tipoUsoId"     , cupom.getTipoUsoBarco()!= null ? cupom.getTipoUsoBarco().getEntityId() : new Long(0));
    		cupomAlterForm.set("listUseType"   , listUseType);
    		cupomAlterForm.set("linhaId"	   , cupom.getLote().getLinha().getEntityId());
    		cupomAlterForm.set("loteId"	       , cupom.getLote().getEntityId());
    		    		
    		
    		this.setJavaScriptExecuter(request, "setTitle()" );
    		
    		return mapping.findForward("_alter");
    		
    	} catch (BusinessException bExp) {
    		
            throw new ControllerException(bExp);
            
        }
    } 
    
    /** Tarefa: Validar e alterar se necessário.
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
    public ActionForward update( ActionMapping mapping
                             , ActionForm form
                             , HttpServletRequest request
                             , HttpServletResponse response) throws ControllerException {
    	
    	DynaValidatorActionForm cupomAlterForm = (DynaValidatorActionForm) form;
		ActionMessages 			messagesWarning    = new ActionMessages();
    	ActionMessages 			messagesSuccess    = new ActionMessages();
    	ActionMessages 			messagesProblem    = new ActionMessages();
    	
    	
    	Cupom cupom = null;
    	
    	// Flag que verificação se o chassi informado foi faturado pela concessionária do usuário
    	// Se for True
    	boolean isConcessionaria = true;
    	
    	// Recuperação dos valores para a criação da cheve composta do cupom - revisao e número de cupom
    	Long cupomId   = (Long)cupomAlterForm.get("entityId");
		//Long numberId   = (Long)cupomAlterForm.get("entityId");
        //Long revisaoId  = (Long)cupomAlterForm.get("revisaoId");
    	
    	try { 
    		
    		TipoUsoBarco    tipoUsoBarco = null;
    		Usuario usuario = (Usuario) UserHelper.getBoundedUser(request.getSession());
    		
    		CupomBusiness 		   cupomBusiness 		 = (CupomBusiness)        springContext.getBean("cupomBO");    		
    		//RevisaoBusiness		   revisaoBusiness	     = (RevisaoBusiness)	  springContext.getBean("revisaoBO"	 );
    		FaturamentoBusiness    faturamentoBusiness   = (FaturamentoBusiness)  springContext.getBean("faturamentoBO" );
    		AlertCupomBusiness     alertCupomBusiness    = (AlertCupomBusiness)   springContext.getBean("alertCupomBO"	 );
    		TipoUsoBarcoBusiness   tipoUsoBarcoBusiness  = (TipoUsoBarcoBusiness) springContext.getBean("tipoUsoBarcoBO");
    		
    		
            Long tipoUsoId	=  cupomAlterForm.get("tipoUsoId") != null ? (Long)cupomAlterForm.get("tipoUsoId") : null;
            
            if ( tipoUsoId != null )
    			tipoUsoBarco = tipoUsoBarcoBusiness.get(tipoUsoId);
            
    		//Revisao revisao = revisaoBusiness.get(revisaoId);    		
    		
    		//Montamos  a Chave composta
    		//CupomId cupomId = new CupomId(revisao, numberId);
    		
    		// Recuperamos o Cupom 
    		cupom = cupomBusiness.get(cupomId);
    		
    		cupom.setDiasUso(cupomAlterForm.get("diasUso") != null ? (Long)cupomAlterForm.get("diasUso") : new Long(0));
	    	cupom.setHorasUso(cupomAlterForm.get("horasUso")!= null ? (Long)cupomAlterForm.get("horasUso"): new Long(0));
	    	cupom.setQuilometragem(cupomAlterForm.get("quilometragem") != null ? (Long)cupomAlterForm.get("quilometragem") : new Long(0) );
	    	cupom.setTipoUsoBarco(tipoUsoBarco);
	    	
    		// Recuperamos a Entidade faturamento para verificação da Concessionária
    		Faturamento 	faturamento	 = faturamentoBusiness.getByChassi(cupom.getChassi()); 
    		
    		//  Verificamos se o chassi faturado é da mesma concessionária do usuário
	    	if (!usuario.compareIdAtribute("concessionaria",faturamento.getConcessionaria().getEntityId())){
	    		
	    		isConcessionaria = false;
	    		messagesWarning.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("cupom.msg.warning.chassi",cupom.getChassi().toUpperCase()));
	    		super.addWarningMessages(request, messagesWarning);
	    		
	    	}	
	    	
	    	// **************************************************************** //
	    	// * Validações dos valores para retornar alertas ao usuário	  * //
	    	// * Salvar ou apenas Retornar mensagens de validação			  * //
	    	// **************************************************************** //
	    	
	    	// Validação da Quilometragem para linhas Motocicleta e Quadriciclo 
	    	// Validação de intervalo de tempo para linhas Náutica e Produtos de força
	    	AlertCupom alertCupom_KmCondiz = cupomBusiness.validateKmCondiz(cupom, cupom.getLote().getLinha(), isConcessionaria);
	    		    		
    		if ( alertCupom_KmCondiz != null ) {
    			
    			alertCupomBusiness.save(alertCupom_KmCondiz);
    		
	    		if ( alertCupom_KmCondiz.getAlertCupomKey().indexOf("error") != -1 ) {
	    			
	    			throw new BusinessRuleException(alertCupom_KmCondiz.getAlertCupomKey());
	    			
	    		} else if ( alertCupom_KmCondiz.getAlertCupomKey().indexOf("warning") != -1 ) {
	    			
	    			messagesWarning.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage(alertCupom_KmCondiz.getAlertCupomKey()) );
    	            super.addWarningMessages(request, messagesWarning);    			
	    			
	    		}
	    		
    		} 
    		
    		cupomBusiness.save(cupom);
    		
    		messagesSuccess.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("cupom.msg.alter.success.cupom", String.valueOf(cupom.getEntityId()))); // ist_edson 01/07/3013
            super.addSuccessMessages(request, messagesSuccess);
    		
    		
    	} catch ( BusinessRuleException rExp) { 
    		
    		// Quando tentamos recuperar um objeto e ele retornar null, apresentamos esta mensagem
    		messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage(rExp.getMessage()) );
            super.addProblemMessages(request, messagesProblem);
    		
    	} catch (BusinessException bExp) {
    		
            throw new ControllerException(bExp);
            
        }catch ( InvalidSessionException isExp ) {
    		
    		throw new ControllerException(isExp);
    		
		}    
        
        cupomAlterForm.set("entityId" , cupom.getEntityId());
        cupomAlterForm.set("revisaoId", cupom.getRevisao().getEntityId());
        
        return this.alter(mapping, form, request, response);
        
    }
    
}
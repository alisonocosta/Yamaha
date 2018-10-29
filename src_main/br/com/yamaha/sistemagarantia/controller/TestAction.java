/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......TesteAction.java
 *   .: Criação.....24 de maio, 07:22
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Action para testes.
 */
package br.com.yamaha.sistemagarantia.controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.validator.DynaValidatorActionForm;
import org.displaytag.properties.SortOrderEnum;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;

import br.com.resource.infra.controller.InfraDispatchAction;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.ControllerException;
import br.com.resource.infra.security.SystemUser;
import br.com.resource.infra.security.exception.InvalidSessionException;
import br.com.resource.infra.security.helper.UserHelper;
import br.com.resource.infra.view.list.DefaultPaginatedList;
import br.com.yamaha.sistemagarantia.business.ConcessionariaBusiness;
import br.com.yamaha.sistemagarantia.business.CupomBusiness;
import br.com.yamaha.sistemagarantia.business.LoteBusiness;
import br.com.yamaha.sistemagarantia.business.RelatorioBusiness;
import br.com.yamaha.sistemagarantia.business.RevisaoBusiness;
import br.com.yamaha.sistemagarantia.model.Concessionaria;
import br.com.yamaha.sistemagarantia.model.Cupom;
import br.com.yamaha.sistemagarantia.model.Revisao;
import br.com.yamaha.sistemagarantia.model.Usuario;
import br.com.yamaha.sistemagarantia.model.id.CupomId;

/** Action para testes.
 * 
 *  @author Edson Luiz Sumensari
 */
public class TestAction extends InfraDispatchAction {

	private static final long serialVersionUID = 1L;
	
	/** Tarefa: Apresenta o formulário de Manutenção.
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
    public ActionForward view( ActionMapping mapping
                             , ActionForm form
                             , HttpServletRequest request
                             , HttpServletResponse response) throws ControllerException {
    	
    	return mapping.findForward("_proc");
    }
    
    /** Tarefa: Apresenta o formulário de Manutenção.
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
    public ActionForward processamento( ActionMapping mapping
                             , ActionForm form
                             , HttpServletRequest request
                             , HttpServletResponse response) throws ControllerException {
    	
    	String texto = request.getParameter("linhaDescricao");
    	
    	try { 
    		
    		RelatorioBusiness relatorioBusiness = (RelatorioBusiness) springContext.getBean("relatorioBO");
    		String res = new String();
    		if ( texto != null ) {
    			res = relatorioBusiness.processa(texto);
    		} else {
    			res = "Não foi enviado o texto!";
    			
    		}
    		ActionMessages warningMessages = new ActionMessages();
            
            warningMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("infra.alert.texto",res) );
            super.addWarningMessages(request, warningMessages);
    		
            
    	} catch (BusinessException bExp) {
            throw new ControllerException( bExp );
            
        }
    	
    	return mapping.findForward("_proc");
    }
	
    /** Tarefa: Apresenta o formulário de inclusão de Lote.
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
    public ActionForward add( ActionMapping mapping
                             , ActionForm form
                             , HttpServletRequest request
                             , HttpServletResponse response) throws ControllerException {
    	
    	
    	// Verificar se o usuário identificado é concessionária e tem acesso ao programa "Sg_cd_lote"
    	// Apresentar mensagem de acesso negado caso o usuário não possua permissão para acessar o programa 
    	// Implementar   	
    	
    	
    	DynaValidatorActionForm cupomForm = (DynaValidatorActionForm) form;
        cupomForm.initialize(mapping);
        
        try {   
        	CupomBusiness cupomBusiness = (CupomBusiness) springContext.getBean("cupomBO");
        	RevisaoBusiness revisaoBusiness = (RevisaoBusiness) springContext.getBean("revisaoBO");
        	Revisao			revisao		 = revisaoBusiness.getByModel("4vw00".substring(0, 5), new Long(1));
        	
        	Cupom cupom = new Cupom();
        	
        	cupom = cupomBusiness.getByParameter("revisao", revisao);
        	
        	if ( cupom != null && !cupom.isNew()) {
        		
        		log.info("Cupom Chassi - 1:" + cupom.getChassi());
        		log.info("Número Revisão - 1:" + cupom.getRevisao().getNumeroRevisao());
        		
        	}
        	
        	cupom = null;
        	
        	cupom = cupomBusiness.get(new CupomId(revisao, new Long(1)));
        	
        	if ( cupom != null && !cupom.isNew()) {
        		
        		log.info("Cupom Chassi - 2:" + cupom.getChassi());
        		log.info("Número Revisão - 2:" + cupom.getRevisao().getNumeroRevisao());
        		
        	}
        	
        	cupom = null;
        	
        	List listCupom = cupomBusiness.listByChassi("4vw00itu565i".toUpperCase());
        	
        	log.info(" Tamanho: " + listCupom.size());
        	
        	Iterator iter = listCupom.iterator();
        	
        	while ( iter.hasNext() ) { 
        		
        		cupom = new Cupom();
        		
        		cupom = (Cupom)iter.next();
        		
        		log.info("Número Cupom - 3:" + cupom.getEntityId() + " - ou - " + cupom.getEntityId());
        		log.info("Número Revisão - 3:" + cupom.getRevisao().getNumeroRevisao());        		
        		
        	}
        	
        	return mapping.findForward("_form");
        	
        } catch ( BusinessException bExp ){
        	
        	throw new ControllerException();
        	
        }
        
    }       
    
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
    		
    		String pOrderName = new ParamEncoder("lote").encodeParameterName(TableTagParameters.PARAMETER_ORDER);
            String pFieldName = new ParamEncoder("lote").encodeParameterName(TableTagParameters.PARAMETER_SORT);
            
            //          Variáveis recebidas no request:
            int page  = Integer.parseInt( request.getParameter("page") );
            int start = ( ( page - 1 ) * 20 );
            int limit = Integer.parseInt( request.getParameter("limit") );
            SystemUser usuario = null;
            Concessionaria concessionaria = null;
            
            String order = request.getParameter( pOrderName ) == null ? "2" : request.getParameter( pOrderName );
            String field = request.getParameter( pFieldName ) == null ? "entityId" : request.getParameter( pFieldName );
            
            DynaValidatorActionForm loteForm = (DynaValidatorActionForm) form;
            loteForm.initialize(mapping);
    		
    		LoteBusiness loteBusiness = (LoteBusiness) springContext.getBean("loteBO");   
    		ConcessionariaBusiness concessionariaBusiness = (ConcessionariaBusiness) springContext.getBean("concessionariaBO");   
    		    		    		
    		usuario = (Usuario) UserHelper.getBoundedUser(request.getSession());
    		
    		concessionaria = concessionariaBusiness.get(((Concessionaria)usuario.getAttribute("concessionaria")).getEntityId());
    		   		
    		DefaultPaginatedList list = new DefaultPaginatedList();
            
            list.setFullListSize( loteBusiness.getTotals( concessionaria ) );
            list.setObjectsPerPage( DefaultPaginatedList.OBJECTS_PER_PAGE );
            list.setPageNumber( page );
            list.setSortCriterion( "descending" );
            list.setSearchId( null );
            list.setSortDirection( SortOrderEnum.DESCENDING );
            list.setList( loteBusiness.listByConcessionaria( concessionaria
            								  , start
                                              , limit
                                              , Integer.parseInt(order)
                                              , field ) );
            
            loteForm.set( "task"        , "list");
            loteForm.set( "listResults" , list );
            
            String url = "&page=" + String.valueOf(page) + "&dir=desc&sort=1&limit=" + limit;
            
            request.setAttribute("url", url );
    		
    		return mapping.findForward("_list");
    		
    	} catch (BusinessException bExp) {
    		
            throw new ControllerException(bExp);
            
        } catch (InvalidSessionException isExp) {
        	
        	throw new ControllerException(isExp);
        	
        }
        
    } 
    
}
/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Infraestrutura para aplicativos Java
 *   .: Objeto......InfraDispatchAction.java
 *   .: Criação.....16 de fevereiro, 12:47
 *   .: Autor.......Thiago Uriel M. Garcia
 *   				Edson Luiz Sumensari
 *   .: Descrição...Classe básica para dispatch actions do Struts.
 */
package br.com.resource.infra.controller;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.struts.DispatchActionSupport;

import br.com.resource.infra.controller.adapter.CommonActionFeatures;
import br.com.resource.infra.controller.adapter.CommonActionFeaturesAdapter;
import br.com.resource.infra.controller.adapter.ConfirmAction;
import br.com.resource.infra.exception.ControllerException;

/** Classe representando uma <code>DispatchAction</code> básica.
 *  <p/>
 *  Seu objetivo é implementar de forma transparente e unificada 
 *  todas as necessidades básicas do controle, como sistemas de 
 *  <i>log</i> e integrações.
 *  <p>
 *  Ainda atua com um proxy verificando se as requisições feitas para
 *  as outras <i>actions</i> do sistema possuem uma sessão válida de
 *  <b>Single Sign On</b>
 *  
 *  @author Thiago Uriel M. Garcia
 */
public class InfraDispatchAction extends DispatchActionSupport implements CommonActionFeaturesAdapter {
    
	private static final long serialVersionUID = 1L;

	/** Constante. Indica o ID de mensagens de sucesso. */
    protected static final String MESSAGE_ID_SUCCESS = "successMessages";
    
    /** Constante. Indica o ID de mensagens de alerta. */
    protected static final String MESSAGE_ID_WARNING = "warningMessages";
    
    /** Constante. Indica o ID de mensagens de problemas. */
    protected static final String MESSAGE_ID_PROBLEM = "problemMessages";    
    
    /** Armazena o contexto da aplicação para que as actions filhas
     *  possam utilizar beans instanciados pelo Spring.
     */
    protected WebApplicationContext springContext;
    
    /** Objeto para tratar mensagens javascript */
    private final CommonActionFeatures common;
    
	/** Construtor.
	 *  <p>
	 *  Recebe uma classe que será utilizada como base para as
	 *  saídas de log efetuadas ao servidor. 
	 */
	public InfraDispatchAction() {
	
	    super();
	    
	    this.common = new CommonActionFeatures();
		
	}
	
    /** Adiciona uma coleção de mensagens de sucesso ao request. 
     * 
     *  @param request
     *      Request no qual as mensagens serão adicionadas. 
     *  
     *  @param successMsgs
     *      <code>ActionMessages</code> contendo as mensagens.
     */
    protected void addSuccessMessages(HttpServletRequest request, ActionMessages successMsgs) {
        
        request.setAttribute(MESSAGE_ID_SUCCESS, successMsgs);
        
    }    
    
    /** Adiciona uma coleção de mensagens de alerta ao request. 
     * 
     *  @param request
     *      Request no qual as mensagens serão adicionadas. 
     *  
     *  @param warningMsgs
     *      <code>ActionMessages</code> contendo as mensagens.
     */
    protected void addWarningMessages(HttpServletRequest request, ActionMessages warningMsgs) {
        
        request.setAttribute(MESSAGE_ID_WARNING, warningMsgs);
        
    }    
    
    /** Adiciona uma coleção de mensagens de problemas ao request. 
     * 
     *  @param request
     *      Request no qual as mensagens serão adicionadas. 
     *  
     *  @param problemMsgs
     *      <code>ActionMessages</code> contendo as mensagens.
     */
    protected void addProblemMessages(HttpServletRequest request, ActionMessages problemMsgs) {
        
        request.setAttribute(MESSAGE_ID_PROBLEM, problemMsgs);
        
    } 
    
    /** Determina um método para execução 
     * 
     * @param request
     * 		Request no qual as mensagens serão adicionadas. 
     * 
     * @param method
     * 		<code>String</code> contendo a mensagem
     */
    public void setJavaScriptExecuter(HttpServletRequest request, String method) {
    	
        common.setJavaScriptExecuter(request, method);
        
    }
    
    /** Adiciona uma mensagem de alerta para ser apresentada no JSP
     *  
     *  @param    HttpServletRequest  request
     *  	Request no qual as mensagens serão adicionadas. 
     * 
     * @param alertMessage
     * 		<code>String</code> contendo a mensagem
     */
    public void setAlertMessage(HttpServletRequest request, String alertMessage) {
    	
        common.setAlertMessage(request, alertMessage);
        
    }
    
    /** Adiciona uma mensagem de alerta para ser apresentada no JSP e colocar o focu no campo
     *  
     *  @param    HttpServletRequest  request
     *  	Request no qual as mensagens serão adicionadas. 
     * 
     * @param alertMessage
     * 		<code>String</code> contendo a mensagem
     *  
     *  @param 	  String   fieldFocus
     *  	<code>String</code>  contendo o objeto que receberá o focu
     */
    public void setAlertMessage(HttpServletRequest request, String alertMessage, String fieldFocus) {
    	
        common.setAlertMessage(request, alertMessage, fieldFocus);
        
    }
    
    /** Adiciona uma mensagem de confirmação para ser apresentada no JSP e encaminhar para determinado método
     *  
     *  @param    HttpServletRequest  request
     *  	Request no qual as mensagens serão adicionadas. 
     * 
     * @param String   	confirmMessage
     * 		<code>String</code> contendo a mensagem
     *  
     * @param methodIfTrue  Método ou ações executadas caso o usuário confirme.
     *                      Um "ponto-e-vírgula" será adicionado ao final automaticamente.
     *                      
     * @param methodIfFalse Método ou ações executadas caso o usuário não confirme.
     *                      Um "ponto-e-vírgula" será adicionado ao final automaticamente.
     */
    public void setConfirmMessage(HttpServletRequest request, String confirmMessage, String methodIfTrue, String methodIfFalse) {
    	
        common.setConfirmMessage(request, confirmMessage, methodIfTrue, methodIfFalse);
        
    }
    
    /** Detecta a escolha do usuário YES/NO
     * 
     *  @param    HttpServletRequest  request
     *  	Request no qual a mensagem foi adicionada.
     */
    public ConfirmAction getConfirmAction(HttpServletRequest request) {
    	
        return common.getConfirmAction(request);
        
    }
    
	/** Adiciona mensagens de erro ao request para que sejam
	 *  apresentadas por uma tela de erro ao usuário.
	 *  
	 *  @param e
	 *  	Exception recebida, a ser incluida ao Request.
	 *  @param request
	 *		Requisição que irá receber o erro.  	
	 */
	protected void addException(Exception e, HttpServletRequest request) {
		
		request.setAttribute("error.exception", e);

		StringWriter strWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(strWriter);
		e.printStackTrace(writer);
		writer.flush();
		writer.close();
		
		request.setAttribute("error.stackTrace", strWriter.getBuffer().toString());
		request.setAttribute("error.message", e.getMessage());
		
	}	
	
	/** Salva informações do erro ocorrido e retorna o forward 
	 *  para a página de exibição do erro.
	 * 
	 *  @param msg Exceção
	 *  @param request Request
	 *  @return Forward para a página de erro
	 */
	protected void addException(String msg, HttpServletRequest request) {
		
		request.setAttribute("error.message", msg);
		
	}	
	
	/** Método de entrada da action. Despacha a requisição para os
	 *  métodos específicos e implementados das actions filhas.
	 * 
	 *  @param mapping
	 *  	O objeto <code>ActionMapping</code> utilizado para
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
	 *  	Um objeto <code>ActionForward</code> determinando para onde o
	 *      <i>framework</i> deverá despachar a requisição.
	 * 
	 *  @throws ControllerException
	 *      Se houverem erros, estes serão lançados como uma exception
	 *      deste tipo.
	 */	
	public ActionForward dispatchMethod(ActionMapping mapping
			                          , ActionForm form
			                          , HttpServletRequest request
			                          , HttpServletResponse response
			                          , String method) throws ControllerException {
		
		try {
			
            // Disponibilizamos o contexto da aplicação para action.
            this.springContext = super.getWebApplicationContext();
            
			// Bloqueio do cache da página.
			response.setHeader("Pragma","no-cache");
			response.setHeader("Cache-Control", "no-store,no-cache,must-revalidate");
			response.setDateHeader("Expires" , -1);
			
			ActionForward forward = super.dispatchMethod( mapping, form, request, response, method );
			return forward;
			
		} catch ( Exception exp ) {
			
			addException( exp, request );
			return mapping.findForward( "errorPage" );			
			
		}
		
	}
	
}
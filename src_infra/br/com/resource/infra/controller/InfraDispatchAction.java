/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Infraestrutura para aplicativos Java
 *   .: Objeto......InfraDispatchAction.java
 *   .: Cria��o.....16 de fevereiro, 12:47
 *   .: Autor.......Thiago Uriel M. Garcia
 *   				Edson Luiz Sumensari
 *   .: Descri��o...Classe b�sica para dispatch actions do Struts.
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

/** Classe representando uma <code>DispatchAction</code> b�sica.
 *  <p/>
 *  Seu objetivo � implementar de forma transparente e unificada 
 *  todas as necessidades b�sicas do controle, como sistemas de 
 *  <i>log</i> e integra��es.
 *  <p>
 *  Ainda atua com um proxy verificando se as requisi��es feitas para
 *  as outras <i>actions</i> do sistema possuem uma sess�o v�lida de
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
    
    /** Armazena o contexto da aplica��o para que as actions filhas
     *  possam utilizar beans instanciados pelo Spring.
     */
    protected WebApplicationContext springContext;
    
    /** Objeto para tratar mensagens javascript */
    private final CommonActionFeatures common;
    
	/** Construtor.
	 *  <p>
	 *  Recebe uma classe que ser� utilizada como base para as
	 *  sa�das de log efetuadas ao servidor. 
	 */
	public InfraDispatchAction() {
	
	    super();
	    
	    this.common = new CommonActionFeatures();
		
	}
	
    /** Adiciona uma cole��o de mensagens de sucesso ao request. 
     * 
     *  @param request
     *      Request no qual as mensagens ser�o adicionadas. 
     *  
     *  @param successMsgs
     *      <code>ActionMessages</code> contendo as mensagens.
     */
    protected void addSuccessMessages(HttpServletRequest request, ActionMessages successMsgs) {
        
        request.setAttribute(MESSAGE_ID_SUCCESS, successMsgs);
        
    }    
    
    /** Adiciona uma cole��o de mensagens de alerta ao request. 
     * 
     *  @param request
     *      Request no qual as mensagens ser�o adicionadas. 
     *  
     *  @param warningMsgs
     *      <code>ActionMessages</code> contendo as mensagens.
     */
    protected void addWarningMessages(HttpServletRequest request, ActionMessages warningMsgs) {
        
        request.setAttribute(MESSAGE_ID_WARNING, warningMsgs);
        
    }    
    
    /** Adiciona uma cole��o de mensagens de problemas ao request. 
     * 
     *  @param request
     *      Request no qual as mensagens ser�o adicionadas. 
     *  
     *  @param problemMsgs
     *      <code>ActionMessages</code> contendo as mensagens.
     */
    protected void addProblemMessages(HttpServletRequest request, ActionMessages problemMsgs) {
        
        request.setAttribute(MESSAGE_ID_PROBLEM, problemMsgs);
        
    } 
    
    /** Determina um m�todo para execu��o 
     * 
     * @param request
     * 		Request no qual as mensagens ser�o adicionadas. 
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
     *  	Request no qual as mensagens ser�o adicionadas. 
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
     *  	Request no qual as mensagens ser�o adicionadas. 
     * 
     * @param alertMessage
     * 		<code>String</code> contendo a mensagem
     *  
     *  @param 	  String   fieldFocus
     *  	<code>String</code>  contendo o objeto que receber� o focu
     */
    public void setAlertMessage(HttpServletRequest request, String alertMessage, String fieldFocus) {
    	
        common.setAlertMessage(request, alertMessage, fieldFocus);
        
    }
    
    /** Adiciona uma mensagem de confirma��o para ser apresentada no JSP e encaminhar para determinado m�todo
     *  
     *  @param    HttpServletRequest  request
     *  	Request no qual as mensagens ser�o adicionadas. 
     * 
     * @param String   	confirmMessage
     * 		<code>String</code> contendo a mensagem
     *  
     * @param methodIfTrue  M�todo ou a��es executadas caso o usu�rio confirme.
     *                      Um "ponto-e-v�rgula" ser� adicionado ao final automaticamente.
     *                      
     * @param methodIfFalse M�todo ou a��es executadas caso o usu�rio n�o confirme.
     *                      Um "ponto-e-v�rgula" ser� adicionado ao final automaticamente.
     */
    public void setConfirmMessage(HttpServletRequest request, String confirmMessage, String methodIfTrue, String methodIfFalse) {
    	
        common.setConfirmMessage(request, confirmMessage, methodIfTrue, methodIfFalse);
        
    }
    
    /** Detecta a escolha do usu�rio YES/NO
     * 
     *  @param    HttpServletRequest  request
     *  	Request no qual a mensagem foi adicionada.
     */
    public ConfirmAction getConfirmAction(HttpServletRequest request) {
    	
        return common.getConfirmAction(request);
        
    }
    
	/** Adiciona mensagens de erro ao request para que sejam
	 *  apresentadas por uma tela de erro ao usu�rio.
	 *  
	 *  @param e
	 *  	Exception recebida, a ser incluida ao Request.
	 *  @param request
	 *		Requisi��o que ir� receber o erro.  	
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
	
	/** Salva informa��es do erro ocorrido e retorna o forward 
	 *  para a p�gina de exibi��o do erro.
	 * 
	 *  @param msg Exce��o
	 *  @param request Request
	 *  @return Forward para a p�gina de erro
	 */
	protected void addException(String msg, HttpServletRequest request) {
		
		request.setAttribute("error.message", msg);
		
	}	
	
	/** M�todo de entrada da action. Despacha a requisi��o para os
	 *  m�todos espec�ficos e implementados das actions filhas.
	 * 
	 *  @param mapping
	 *  	O objeto <code>ActionMapping</code> utilizado para
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
	 *  	Um objeto <code>ActionForward</code> determinando para onde o
	 *      <i>framework</i> dever� despachar a requisi��o.
	 * 
	 *  @throws ControllerException
	 *      Se houverem erros, estes ser�o lan�ados como uma exception
	 *      deste tipo.
	 */	
	public ActionForward dispatchMethod(ActionMapping mapping
			                          , ActionForm form
			                          , HttpServletRequest request
			                          , HttpServletResponse response
			                          , String method) throws ControllerException {
		
		try {
			
            // Disponibilizamos o contexto da aplica��o para action.
            this.springContext = super.getWebApplicationContext();
            
			// Bloqueio do cache da p�gina.
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
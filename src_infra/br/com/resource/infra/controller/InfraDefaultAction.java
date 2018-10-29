/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Infraestrutura para aplicativos Java
 *   .: Objeto......InfraDefaultAction.java
 *   .: Criação.....16 de fevereiro, 12:47
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Classe básica para actions do Struts.
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
import org.springframework.web.struts.ActionSupport;

import br.com.resource.infra.exception.ControllerException;

/** Classe abstrata representando uma <code>action</code> básica
 *  do <b>Struts</b>. Seu objetivo é implementar de forma transparente
 *  e unificada todas as necessidades básicas do controle, como
 *  sistemas de <i>log</i> e integrações.
 *  <p>
 *  Ainda atua com um proxy verificando se as requisições feitas para
 *  as outras <i>actions</i> do sistema possuem uma sessão válida de
 *  <b>Single Sign On</b>
 * 
 *  @author Thiago Uriel M. Garcia
 */
public abstract class InfraDefaultAction extends ActionSupport {

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
    
	/** Construtor.
	 *  <p>
	 *  Recebe uma classe que será utilizada como base para as
	 *  saídas de log efetuadas ao servidor. 
	 */
	public InfraDefaultAction() {
		
		super();
		
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
	
	/** Método de entrada da action. Envia a requisição para o
	 *  método <code>doTask</code> implementado nas actions filhas.
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
	 *  	Um objeto <code>ActionForward</code> determinando para onde o
	 *      <i>framework</i> deverá despachar a requisição.
	 * 
	 *  @throws Exception
	 *		Se houverem erros, estes serão lançados como uma exception
	 *      deste tipo.
	 */
	public ActionForward execute(ActionMapping mapping
			                   , ActionForm form
			                   , HttpServletRequest request
			                   , HttpServletResponse response) throws Exception {

        // Disponibilizamos o contexto da aplicação para action.
        this.springContext = super.getWebApplicationContext();        
        
		// Bloqueio do cache da página.
		response.setHeader("Pragma","no-cache");
		response.setHeader("Cache-Control", "no-store,no-cache,must-revalidate");
		response.setDateHeader("Expires" , -1);
		
		// Chamamos o método abstrado doTask que deverá ser implementado
		// por todas as actions do sistema.
		try {

			return this.doTask( mapping, form, request, response );
			
		} catch ( ControllerException cExp ) {
			
            addException( cExp, request );
			return mapping.findForward( "errorPage" );
			
		}
		
	}

	/** Método abstrato a ser implementado por todas as actions. 
	 *  <p/>
	 *  Este método será chamado pela porta de entrada das actions do
	 *  <b>Struts</b> - O método <code>execute()</code>.
	 *  
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
	 *  	Um objeto <code>ActionForward</code> determinando para onde 
	 *  	o <i>framework</i> deverá despachar a requisição.
	 * 
	 *  @throws ControllerException
	 *  	Se houverem erros, estes serão lançados como uma exception
	 *      deste tipo.
	 */	
	public abstract ActionForward doTask(ActionMapping mapping
									   , ActionForm form
									   , HttpServletRequest request
									   , HttpServletResponse response) throws ControllerException;
	
}
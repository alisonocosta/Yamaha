/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Infraestrutura para aplicativos Java
 *   .: Objeto......InfraAjaxDefaultAction.java
 *   .: Cria��o.....20 de mar�o, 13:22
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descri��o...Classe b�sica para actions do Struts, com suporte ao Ajax.
 */
package br.com.resource.infra.controller;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.struts.ActionSupport;

import br.com.resource.infra.exception.ControllerException;

/** Action que retorna resultados de requisi��es que esperam
 *  respostas em <b>Ajax</b>.
 *  <p>
 *  Esta action n�o retorna <i>forwards</i>, estes ser�o sempre
 *  nulos. Sua fun��o � criar um XML de resposta atrav�s de suas 
 *  classes filhas, e escrev�-lo na sa�da.
 *  <p>
 *  Cada action filha dever� sobrescrever o m�todo abstrato
 *  <code>getXmlContent</code>, retornando uma String de resposta.
 *  
 *  @author Thiago Uriel M. Garcia
 */
public abstract class InfraAjaxDefaultAction extends ActionSupport {
	
	/** Armazena o contexto da aplica��o para que as actions filhas
     *  possam utilizar beans instanciados pelo Spring.
     */
    protected WebApplicationContext springContext;

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
	
	/** M�todo de entrada da action. Envia a requisi��o para o
	 *  m�todo <code>doTask</code> implementado nas actions filhas.
	 * 
	 *  @param mapping
	 *      O objeto <code>ActionMapping</code> utilizado para
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
	 *  @throws Exception
	 *		Se houverem erros, estes ser�o lan�ados como uma exception
	 *      deste tipo.
	 */
	public ActionForward execute(ActionMapping mapping
			                   , ActionForm form
			                   , HttpServletRequest request
			                   , HttpServletResponse response) throws Exception {

        // Disponibilizamos o contexto da aplica��o para action.
        this.springContext = super.getWebApplicationContext();        
        
		// Bloqueio do cache da p�gina.
		response.setHeader("Pragma","no-cache");
		response.setHeader("Cache-Control", "no-store,no-cache,must-revalidate");
		response.setDateHeader("Expires" , -1);
		
		// Chamamos o m�todo abstrado doTask que dever� ser implementado
		// por todas as actions do sistema.
		try {

			return this.doTask( mapping, form, request, response );
			
		} catch ( ControllerException cExp ) {
			
            addException( cExp, request );
			return mapping.findForward( "errorPage" );
			
		}
		
	}
	
	/** Chama o m�todo abstrado das classes filhas que cria o XML
	 *  de resposta e com esta resposta, envia os dados ao cliente.
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
	 *  	Um objeto <code>ActionForward</code> determinando para onde 
	 *  	o <i>framework</i> dever� despachar a requisi��o. Como este
	 *  	tipo de action dever� retornar a resposta ao client, contendo
	 *  	um XML e isso n�o ser� uma tela, a resposta sempre ser� nula
	 *  	e o conte�do do Ajax ser� retornado via <code>PrintWriter</code>.
	 * 
	 *  @throws ControllerException
	 *  	Se houverem erros, estes ser�o lan�ados como uma exception
	 *      deste tipo.
	 */	
	public ActionForward doTask( ActionMapping mapping
			  			       , ActionForm form
							   , HttpServletRequest request
							   , HttpServletResponse response) throws ControllerException {
		
		String xml = null;
	    
	    try {
	    	
	    	xml = getXmlContent( mapping, form, request, response );
	      
			// Determinamos que o conte�do ser� XML.
			response.setContentType("text/xml; charset=UTF-8");
			PrintWriter pw = response.getWriter();
			pw.write( xml );
			pw.close();
			
			return null;	      
	      
	    } catch ( Exception exp ) {

	    	throw new ControllerException( exp );
	      
	    }

	}
	
	/** Cada classe filha dever� sobrescrever este m�todo para gerar a resposta XML
	 *  esperada por cada requisi��o do Ajax.
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
     *      Uma String com o conte�do XML (ou texto) a ser enviado
     *      de volta ao cliente.

	 * 
	 *  @throws ControllerException
	 *  	Se houverem erros, estes ser�o lan�ados como uma exception
	 *      deste tipo.
	 */		  
	public abstract String getXmlContent( ActionMapping mapping
										, ActionForm form
										, HttpServletRequest request
										, HttpServletResponse response) throws ControllerException;	
	
}
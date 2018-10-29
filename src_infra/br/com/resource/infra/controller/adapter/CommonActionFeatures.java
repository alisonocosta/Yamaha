/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Infraestrutura para aplicativos Java
 *   .: Objeto......CommonActionFeaturesAdapter.java
 *   .: Cria��o.....22 de maio, 08:40
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Classe b�sica para implementa��o de tratamento de mensagens.
 */
package br.com.resource.infra.controller.adapter;

import javax.servlet.http.HttpServletRequest;

/**Classe b�sica para implementa��o de tratamento de mensagens.*/
public class CommonActionFeatures{

	/** Construtor */
    public CommonActionFeatures() {
        super();
        
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
    	
        request.setAttribute("javaScriptExecuter.method", method);
        
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
    	
        request.setAttribute("alertMessage", alertMessage);
        
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
        setAlertMessage(request, alertMessage);
        
        request.setAttribute("alertMessage.fieldFocus", fieldFocus);
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
        
        request.setAttribute("confirmMessage", confirmMessage);
        request.setAttribute("confirmMessage.methodIfTrue", methodIfTrue);
        request.setAttribute("confirmMessage.methodIfFalse", methodIfFalse);
    }
    
    /** Detecta a escolha do usu�rio YES/NO
     * 
     *  @param    HttpServletRequest  request
     *  	Request no qual a mensagem foi adicionada.
     */
    public ConfirmAction getConfirmAction(HttpServletRequest request) {
        String confirmAction = request.getParameter("confirmAction");
        
        if (confirmAction != null) {
            return confirmAction.equals("yes") ? ConfirmAction.YES : ConfirmAction.NO;
        }
        
        return ConfirmAction.NO;
    }
}

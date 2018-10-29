/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Infraestrutura para aplicativos Java
 *   .: Objeto......CommonActionFeaturesAdapter.java
 *   .: Criação.....22 de maio, 08:40
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Classe básica para implementação de tratamento de mensagens.
 */
package br.com.resource.infra.controller.adapter;

import javax.servlet.http.HttpServletRequest;

/**Classe básica para implementação de tratamento de mensagens.*/
public class CommonActionFeatures{

	/** Construtor */
    public CommonActionFeatures() {
        super();
        
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
    	
        request.setAttribute("javaScriptExecuter.method", method);
        
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
    	
        request.setAttribute("alertMessage", alertMessage);
        
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
        setAlertMessage(request, alertMessage);
        
        request.setAttribute("alertMessage.fieldFocus", fieldFocus);
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
        
        request.setAttribute("confirmMessage", confirmMessage);
        request.setAttribute("confirmMessage.methodIfTrue", methodIfTrue);
        request.setAttribute("confirmMessage.methodIfFalse", methodIfFalse);
    }
    
    /** Detecta a escolha do usuário YES/NO
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

/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Infraestrutura para aplicativos Java
 *   .: Objeto......CommonActionFeaturesAdapter.java
 *   .: Cria��o.....22 de maio, 08:30
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Interface b�sica para implementa��o de tratamento de mensagens.
 */
package br.com.resource.infra.controller.adapter;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

/** Interface b�sica para implementa��o de tratamento de mensagens.*/
public interface CommonActionFeaturesAdapter extends Serializable {
    
    /** Determina um m�todo para execu��o 
     * 
     * @param request
     * @param method
     */
    public void setJavaScriptExecuter(HttpServletRequest request, String method);
    
    /** Adiciona uma mensagem de alerta para ser apresentada no JSP
     *  
     *  @param    HttpServletRequest  request
     *  
     *  @param	  String   alertMessage
     */
    public void setAlertMessage(HttpServletRequest request, String alertMessage);
    
    /** Adiciona uma mensagem de alerta para ser apresentada no JSP e colocar o focu no campo
     *  
     *  @param    HttpServletRequest  request
     *  
     *  @param	  String   alertMessage
     *  
     *  @param 	  String   fieldFocus
     */
    public void setAlertMessage(HttpServletRequest request, String alertMessage, String fieldFocus);
    
    /** Adiciona uma mensagem de confirma��o para ser apresentada no JSP e encaminhar para determinado m�todo
     *  
     *  @param    HttpServletRequest  request
     *  
     *  @param	  String   	confirmMessage
     *  
     * @param methodIfTrue  M�todo ou a��es executadas caso o usu�rio confirme.
     *                      Um "ponto-e-v�rgula" ser� adicionado ao final automaticamente.
     *                      
     * @param methodIfFalse M�todo ou a��es executadas caso o usu�rio n�o confirme.
     *                      Um "ponto-e-v�rgula" ser� adicionado ao final automaticamente.
     */
    public void setConfirmMessage(HttpServletRequest request, String confirmMessage, String methodIfTrue, String methodIfFalse);
    
    /** Detecta a escolha do usu�rio YES/NO
     * 
     *  @param    HttpServletRequest  request
     */
    public ConfirmAction getConfirmAction(HttpServletRequest request);
}

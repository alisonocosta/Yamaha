/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Infraestrutura para aplicativos Java
 *   .: Objeto......CommonActionFeaturesAdapter.java
 *   .: Criação.....22 de maio, 08:30
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Interface básica para implementação de tratamento de mensagens.
 */
package br.com.resource.infra.controller.adapter;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

/** Interface básica para implementação de tratamento de mensagens.*/
public interface CommonActionFeaturesAdapter extends Serializable {
    
    /** Determina um método para execução 
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
    
    /** Adiciona uma mensagem de confirmação para ser apresentada no JSP e encaminhar para determinado método
     *  
     *  @param    HttpServletRequest  request
     *  
     *  @param	  String   	confirmMessage
     *  
     * @param methodIfTrue  Método ou ações executadas caso o usuário confirme.
     *                      Um "ponto-e-vírgula" será adicionado ao final automaticamente.
     *                      
     * @param methodIfFalse Método ou ações executadas caso o usuário não confirme.
     *                      Um "ponto-e-vírgula" será adicionado ao final automaticamente.
     */
    public void setConfirmMessage(HttpServletRequest request, String confirmMessage, String methodIfTrue, String methodIfFalse);
    
    /** Detecta a escolha do usuário YES/NO
     * 
     *  @param    HttpServletRequest  request
     */
    public ConfirmAction getConfirmAction(HttpServletRequest request);
}

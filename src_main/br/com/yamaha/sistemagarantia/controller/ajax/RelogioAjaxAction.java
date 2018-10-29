/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelogioAjaxAction.java
 *   .: Criação.....02 de maio, 12:08
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Action de serviço para mostrar data e hroa.
 */
package br.com.yamaha.sistemagarantia.controller.ajax;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import br.com.resource.infra.controller.InfraAjaxDefaultAction;
import br.com.resource.infra.exception.ControllerException;
import br.com.resource.infra.utils.DateUtils;
import br.com.resource.infra.utils.StringUtils;

/** Action de serviço responsável por obter a data e hora do servidor.
 * 
 *  @author Thiago Uriel M. Garcia
 */
public class RelogioAjaxAction extends InfraAjaxDefaultAction {

    /** Obtém a data e hora formatados no servidor e retorna.
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
     *      Uma String com o conteúdo XML (ou texto) a ser enviado
     *      de volta ao cliente.
     * 
     *  @throws ControllerException
     *      Se houverem erros, estes serão lançados como uma exception
     *      deste tipo.
     */ 
    public String getXmlContent( ActionMapping mapping
                               , ActionForm form
                               , HttpServletRequest request
                               , HttpServletResponse response) throws ControllerException {
        
        String formatedDate = DateUtils.applyMask(new Date(), "dd.MM.yyyy kk:mm");
        return StringUtils.replace(formatedDate, " ", "<br>");
        
    }
    
}
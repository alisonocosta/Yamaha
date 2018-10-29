/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelogioAjaxAction.java
 *   .: Cria��o.....02 de maio, 12:08
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descri��o...Action de servi�o para mostrar data e hroa.
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

/** Action de servi�o respons�vel por obter a data e hora do servidor.
 * 
 *  @author Thiago Uriel M. Garcia
 */
public class RelogioAjaxAction extends InfraAjaxDefaultAction {

    /** Obt�m a data e hora formatados no servidor e retorna.
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
     *      Uma String com o conte�do XML (ou texto) a ser enviado
     *      de volta ao cliente.
     * 
     *  @throws ControllerException
     *      Se houverem erros, estes ser�o lan�ados como uma exception
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
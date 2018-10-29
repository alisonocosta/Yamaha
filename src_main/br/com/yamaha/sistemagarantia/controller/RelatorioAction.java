/* Resource Informática
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioAction.java
 *   .: Criação.....19 de julho, 10:41
 *   .: Autor.......Gisele Panosso
 *   .: Descrição...Action central de relatórios.
 */
package br.com.yamaha.sistemagarantia.controller;

import java.awt.image.BufferedImage;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.springframework.beans.BeansException;

import br.com.resource.infra.controller.InfraDispatchAction;
import br.com.resource.infra.core.constants.ReportConstants;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.ControllerException;
import br.com.resource.infra.exception.ServiceException;
import br.com.resource.infra.security.exception.InvalidSessionException;
import br.com.resource.infra.security.helper.UserHelper;
import br.com.resource.infra.utils.DateUtils;
import br.com.resource.infra.utils.NumberUtils;
import br.com.yamaha.sistemagarantia.business.ConcessionariaBusiness;
import br.com.yamaha.sistemagarantia.business.EmpresaBusiness;
import br.com.yamaha.sistemagarantia.business.FornecedorBusiness;
import br.com.yamaha.sistemagarantia.business.LinhaBusiness;
import br.com.yamaha.sistemagarantia.business.LoteBusiness;
import br.com.yamaha.sistemagarantia.business.ModeloBusiness;
import br.com.yamaha.sistemagarantia.business.ProgramaBusiness;
import br.com.yamaha.sistemagarantia.business.RelatorioBusiness;
import br.com.yamaha.sistemagarantia.business.TipoLoteBusiness;
import br.com.yamaha.sistemagarantia.business.UsuarioBusiness;
import br.com.yamaha.sistemagarantia.business.exception.CotacaoException;
import br.com.yamaha.sistemagarantia.controller.helper.GenerateChartReport;
import br.com.yamaha.sistemagarantia.model.Concessionaria;
import br.com.yamaha.sistemagarantia.model.Empresa;
import br.com.yamaha.sistemagarantia.model.Linha;
import br.com.yamaha.sistemagarantia.model.Lote;
import br.com.yamaha.sistemagarantia.model.Programa;
import br.com.yamaha.sistemagarantia.model.StatusLote;
import br.com.yamaha.sistemagarantia.model.TipoLote;
import br.com.yamaha.sistemagarantia.model.Usuario;
import br.com.yamaha.sistemagarantia.view.EntityImgReportGraficosIndividuaisVO;
import br.com.yamaha.sistemagarantia.view.RelatorioDocRevisaoDetalheVO;
import br.com.yamaha.sistemagarantia.view.RelatorioGarPecasSubReportVO;
import br.com.yamaha.sistemagarantia.view.RelatorioGarantiaPecasModSubRepVO;
import br.com.yamaha.sistemagarantia.view.RelatorioGarantiaPecasModVO;
import br.com.yamaha.sistemagarantia.view.RelatorioGarantiaPecasVO;
import br.com.yamaha.sistemagarantia.view.RelatorioGerenciamentoMesVO;
import br.com.yamaha.sistemagarantia.view.RelatorioImportedPartsInvoiceVO;
import br.com.yamaha.sistemagarantia.view.RelatorioImportedPartsVO;
import br.com.yamaha.sistemagarantia.view.RelatorioImportedPartsWarrantyVO;
import br.com.yamaha.sistemagarantia.view.RelatorioProcPecaFase2PecaVO;
import br.com.yamaha.sistemagarantia.view.RelatorioProcPecaFase2ServicoVO;
import br.com.yamaha.sistemagarantia.view.RelatorioProcPecasFase2VO;
import br.com.yamaha.sistemagarantia.view.RelatorioServiceReportGraphVO;
import br.com.yamaha.sistemagarantia.view.RelatorioVerificacaoVO;

/** Processa relatórios. 
 * 
 *  @author Gisele Panosso
 */
public class RelatorioAction extends InfraDispatchAction {

    private static final long serialVersionUID = 1L;

    private static final String JASPER_FOLDER = "/report/";

    /** Tarefa: Loga o usuário que chegou pelo menu de relatórios da parte interna do sistema
     * 			Por não se tratar de um usuário de concessionária, devemos pegar o id do usuário
     * 			enviado via parâmetro e realizar o logon.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     *  @throws ControllerException
     *      Se houverem erros, estes serão lançados como uma exception
     *      deste tipo.
     */ 
    public ActionForward logon( ActionMapping mapping
                             , ActionForm form
                             , HttpServletRequest request
                             , HttpServletResponse response) throws ControllerException {
    	
    	Long    usuario_id = request.getParameter("usuario_id") != null ? (new Long(request.getParameter("usuario_id"))):null;
    	    	
    	try {
    		
    		UsuarioBusiness  usuarioBusiness  = (UsuarioBusiness) springContext.getBean("usuarioBO");
    		UserHelper.unboundUserToSession(request.getSession());
                        
            if ( usuario_id == null ) {
        		
        		throw new BusinessException("O usuário não foi informado!");
        		
        	} else {
        		
        		Usuario usuario = usuarioBusiness.get(usuario_id);
        		
        		if ( usuario == null )
        			throw new BusinessException("O usuário é inválido!");
        		else
        			if ( !usuario.getNivelAcesso().getEntityId().equals(new Long(3)) && !usuario.getNivelAcesso().getEntityId().equals(new Long(4)) )
        				throw new BusinessException("O usuário não tem permissão!");
        			else
        				UserHelper.boundUserToSession(request.getSession(), usuario);
        		
        	}
    		
    	} catch (BusinessException bExp) {
            
            throw new ControllerException("Erro na autenticação do usuário - Exception:" + bExp);
            
        }
    	
    	return this.list(mapping, form, request, response);
    }
    
    /** Tarefa: Monta a tela com a lista de relatórios disponíveis para usuário internos da Yamaha.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward list( ActionMapping mapping
                                 , ActionForm form
                                 , HttpServletRequest request
                                 , HttpServletResponse response) throws ControllerException {
        
        DynaActionForm relatorioForm = (DynaActionForm) form;
        relatorioForm.initialize(mapping);      
        
        ProgramaBusiness programaBusiness = (ProgramaBusiness) springContext.getBean("programaBO");
        
        try {
        	
            UserHelper.getBoundedUser(request.getSession());
            relatorioForm.set( "listProgramas", programaBusiness.listProgramByMenu(Programa.MENU_JAVA_INTERNO));
            
        } catch (BusinessException bExp) {
            throw new ControllerException(bExp);
            
        } catch (InvalidSessionException isExp) {
            throw new ControllerException(isExp);
        }
        
        return mapping.findForward("_listInner");
        
    } 
    
    
    /** Tarefa: Prepara tela de filtros para relatório Zero Km.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward showList( ActionMapping mapping
                                 , ActionForm form
                                 , HttpServletRequest request
                                 , HttpServletResponse response) throws ControllerException {
        
        DynaActionForm relatorioForm = (DynaActionForm) form;
        relatorioForm.initialize(mapping);      
        
        ProgramaBusiness programaBusiness = (ProgramaBusiness) springContext.getBean("programaBO");
        
        try {
            Usuario usuario = (Usuario) UserHelper.getBoundedUser(request.getSession());
            relatorioForm.set( "listProgramas", programaBusiness.listProgramByMenu());
            request.setAttribute("userId", usuario.getEntityId());
            
        } catch (BusinessException bExp) {
            throw new ControllerException(bExp);
            
        } catch (InvalidSessionException isExp) {
            throw new ControllerException(isExp);
        }
        
        return mapping.findForward("_list");
        
    }   
    
    
    
    //  ----[ Métodos Prepare ]---------------------------------------------------
    
    
    /** Tarefa: Prepara tela de filtros para relatório Zero Km.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward prepareRelatorioZeroKm( ActionMapping mapping
                                               , ActionForm form
                                               , HttpServletRequest request
                                               , HttpServletResponse response) throws ControllerException {
        
        try {
            
            DynaActionForm relatorioForm = (DynaActionForm) form;
            relatorioForm.initialize(mapping);
            
            LinhaBusiness linha = (LinhaBusiness) springContext.getBean("linhaBO");
            relatorioForm.set( "linhaList", linha.list());
            
            return mapping.findForward("_relatorioZeroKmFilter");
            
        } catch (BeansException bExp) {
            throw new ControllerException(bExp);
            
        } catch (BusinessException bExp) {
            throw new ControllerException(bExp);
            
        }
        
    }
    
    /** Tarefa: Prepara tela de filtros para relatório Análise de Peças.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward prepareRelatorioAnalisePecas( ActionMapping mapping
                                                     , ActionForm form
                                                     , HttpServletRequest request
                                                     , HttpServletResponse response) throws ControllerException {

        DynaActionForm relatorioForm = (DynaActionForm) form;
        relatorioForm.initialize(mapping);

        return mapping.findForward("_relatorioAnalisePecasFilter");

    }    

    /** Tarefa: Prepara tela de filtros para relatório Análise de Peças.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward prepareRelatorioPagamentos( ActionMapping mapping
                                                     , ActionForm form
                                                     , HttpServletRequest request
                                                     , HttpServletResponse response) throws ControllerException {

        try {
            
            DynaActionForm relatorioForm = (DynaActionForm) form;
            relatorioForm.initialize(mapping);
            
            ConcessionariaBusiness concessionaria = (ConcessionariaBusiness) springContext.getBean("concessionariaBO");
            relatorioForm.set("concessionariaList", concessionaria.list());
                        
            return mapping.findForward("_relatorioPagamentosFilter");
            
        } catch (BeansException bExp) {
            throw new ControllerException(bExp);
            
        } catch (BusinessException bExp) {
            throw new ControllerException(bExp);
            
        }       
    }     

    /** Tarefa: Prepara tela de filtros para relatório de Recusa.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward prepareRelatorioRecusa( ActionMapping mapping
                                                     , ActionForm form
                                                     , HttpServletRequest request
                                                     , HttpServletResponse response) throws ControllerException {

        try {
            
            DynaActionForm relatorioForm = (DynaActionForm) form;
            relatorioForm.initialize(mapping);
            EmpresaBusiness empresa = (EmpresaBusiness) springContext.getBean("empresaBO");
            relatorioForm.set("empresaList", empresa.list());
            
            ConcessionariaBusiness concessionaria = (ConcessionariaBusiness) springContext.getBean("concessionariaBO");
            relatorioForm.set("concessionariaList", concessionaria.list());
            
            LinhaBusiness linha = (LinhaBusiness) springContext.getBean("linhaBO");
            relatorioForm.set( "linhaList", linha.list());  
            
            return mapping.findForward("_relatorioRecusaFilter");
            
        } catch (BeansException bExp) {
            throw new ControllerException(bExp);
            
        } catch (BusinessException bExp) {
            throw new ControllerException(bExp);    
            
        }
    }     

    /** Tarefa: Prepara tela de filtros para relatório de Verificação.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward prepareRelatorioVerificacao( ActionMapping mapping
                                                     , ActionForm form
                                                     , HttpServletRequest request
                                                     , HttpServletResponse response) throws ControllerException {

        try {
            
            DynaActionForm relatorioForm = (DynaActionForm) form;
            relatorioForm.initialize(mapping);
            
            ConcessionariaBusiness concessionaria = (ConcessionariaBusiness) springContext.getBean("concessionariaBO");
            relatorioForm.set("concessionariaList", concessionaria.list());
            
            LinhaBusiness linha = (LinhaBusiness) springContext.getBean("linhaBO");
            relatorioForm.set( "linhaList", linha.list());
            
            return mapping.findForward("_relatorioVerificacaoFilter");
            
        } catch (BeansException bExp) {
            throw new ControllerException(bExp);
            
        } catch (BusinessException bExp) {
            throw new ControllerException(bExp);
            
        }
    }     
       
    /** Tarefa: Prepara tela de filtros para relatório de Mensal por Modelo.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward prepareRelatorioMensalModelo( ActionMapping mapping
                                                     , ActionForm form
                                                     , HttpServletRequest request
                                                     , HttpServletResponse response) throws ControllerException {

        try {
            
            DynaActionForm relatorioForm = (DynaActionForm) form;
            relatorioForm.initialize(mapping);
            
            EmpresaBusiness empresaBO = (EmpresaBusiness) springContext.getBean("empresaBO");
            relatorioForm.set("empresaList", empresaBO.list());
            
            return mapping.findForward("_relatorioMensalModeloFilter");
            
        } catch (BeansException bExp) {
            throw new ControllerException(bExp);        
            
        } catch (BusinessException bExp) {
            throw new ControllerException(bExp);
            
        }
        
    }  
    
    /** Tarefa: Prepara tela de filtros para relatório Mala Direta.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward prepareRelatorioMalaDireta( ActionMapping mapping
                                                   , ActionForm form
                                                   , HttpServletRequest request
                                                   , HttpServletResponse response) throws ControllerException {

        try {
            
            DynaActionForm relatorioForm = (DynaActionForm) form;
            relatorioForm.initialize(mapping);
            
            Usuario usuario = (Usuario) UserHelper.getBoundedUser(request.getSession());
            
            boolean isConcessionaria = true;
            
            EmpresaBusiness empresa = (EmpresaBusiness) springContext.getBean("empresaBO");
            relatorioForm.set("empresaList", empresa.list());
            
            LinhaBusiness linha = (LinhaBusiness) springContext.getBean("linhaBO");
            relatorioForm.set( "linhaList", linha.list());  
            
            if ( usuario != null ) {
	            if ( !Usuario.NIVEL_ACESSO_CONCESSIONARIA.equalsIgnoreCase(usuario.getNivelAcesso().getCodigo()) ) {
	                
	                ConcessionariaBusiness concessionariaBusiness = (ConcessionariaBusiness) springContext.getBean("concessionariaBO");
	                relatorioForm.set("concessionariaList", concessionariaBusiness.list()); 
	                
	            } else {
	                
	                isConcessionaria = false;
	                Concessionaria conc = usuario.getConcessionaria();
	                relatorioForm.set( "concessionariaId", conc.getEntityId() );
	                relatorioForm.set( "estado", conc.getEstado().getUf() );                
	                
	            }
            } else {
            	
            	ConcessionariaBusiness concessionariaBusiness = (ConcessionariaBusiness) springContext.getBean("concessionariaBO");
                relatorioForm.set("concessionariaList", concessionariaBusiness.list());             	
            	
            }
            
            request.setAttribute("isConc", new Boolean(isConcessionaria));
            
            return mapping.findForward("_relatorioMalaDiretaFilter");
            
        } catch (BeansException bExp) {
            throw new ControllerException(bExp);
            
        } catch (BusinessException bExp) {
            throw new ControllerException(bExp);    
            
        } catch (InvalidSessionException iSexp) {
            throw new ControllerException(iSexp);   
            
        }
    }      
    
    /** Tarefa: Prepara tela de filtros para relatório Solicitacao Garantia.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward prepareRelatorioSolicitacaoGarantia( ActionMapping mapping
                                                            , ActionForm form
                                                            , HttpServletRequest request
                                                            , HttpServletResponse response) throws ControllerException {

        try {
            
            DynaActionForm relatorioForm = (DynaActionForm) form;
            relatorioForm.initialize(mapping);          
            
            Usuario usuario = (Usuario) UserHelper.getBoundedUser(request.getSession());
            boolean isConcessionaria = true;
            
            if ( usuario != null ) {
	            if ( !Usuario.NIVEL_ACESSO_CONCESSIONARIA.equalsIgnoreCase(usuario.getNivelAcesso().getCodigo()) ) {
	                
	                ConcessionariaBusiness concessionariaBusiness = (ConcessionariaBusiness) springContext.getBean("concessionariaBO");
	                relatorioForm.set("concessionariaList", concessionariaBusiness.list()); 
	                
	            } else {
	                
	                isConcessionaria = false;
	                Concessionaria conc = usuario.getConcessionaria();
	                relatorioForm.set( "concessionariaId", conc.getEntityId() );
	                relatorioForm.set( "estado", conc.getEstado().getUf() );                
	                
	            }
            } else {
            	
            	ConcessionariaBusiness concessionariaBusiness = (ConcessionariaBusiness) springContext.getBean("concessionariaBO");
                relatorioForm.set("concessionariaList", concessionariaBusiness.list());
            	
            }
            
            request.setAttribute("isConc", new Boolean(isConcessionaria));            
            
            return mapping.findForward("_relatorioSolicitacaoGarantiaFilter");
            
        } catch (BeansException bExp) {
            throw new ControllerException(bExp);

        } catch (BusinessException bExp) {
            throw new ControllerException(bExp);        
            
        } catch (InvalidSessionException iSexp) {
            throw new ControllerException(iSexp);   
            
        }
        
    }  
    
    /** Tarefa: Prepara tela de filtros para Relatório de Processamento de Revisoes - Fase1.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward prepareRelatorioProcRevisoesFase1 ( ActionMapping mapping
                                                           , ActionForm form
                                                           , HttpServletRequest request
                                                           , HttpServletResponse response) throws ControllerException {
        
        try {
            
            DynaActionForm relatorioForm = (DynaActionForm) form;
            relatorioForm.initialize(mapping);

            EmpresaBusiness empresa = (EmpresaBusiness) springContext.getBean("empresaBO");
            relatorioForm.set("empresaList", empresa.list());

            return mapping.findForward("_relatorioProcRevisoesFase1Filter");
            
        } catch (BeansException bExp) {
            throw new ControllerException(bExp);

        } catch (BusinessException bExp) {
            throw new ControllerException(bExp);                
        }
        
    }
    
   
    /** Tarefa: Prepara tela de filtros para Relatório de Processamento de Revisoes - Fase2.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward prepareRelatorioProcRevisoesFase2 ( ActionMapping mapping
                                                           , ActionForm form
                                                           , HttpServletRequest request
                                                           , HttpServletResponse response) throws ControllerException {
        
        try {
            
            DynaActionForm relatorioForm = (DynaActionForm) form;
            relatorioForm.initialize(mapping);
            
            EmpresaBusiness empresa = (EmpresaBusiness) springContext.getBean("empresaBO");
            relatorioForm.set("empresaList", empresa.list());

            return mapping.findForward("_relatorioProcRevisoesFase2Filter");
            
        } catch (BeansException bExp) {
            throw new ControllerException(bExp);

        } catch (BusinessException bExp) {
            throw new ControllerException(bExp);                
        }
        
    }    
    
    /** Tarefa: Prepara tela de filtros para Relatório Consulta de Notas Fiscais.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward prepareRelatorioConsultaNF ( ActionMapping mapping
                                                    , ActionForm form
                                                    , HttpServletRequest request
                                                    , HttpServletResponse response) throws ControllerException {
        
        try {
            
            DynaActionForm relatorioForm = (DynaActionForm) form;
            relatorioForm.initialize(mapping);
            
            Usuario usuario = (Usuario) UserHelper.getBoundedUser(request.getSession());
            boolean isConcessionaria = true;
             
            if ( usuario != null ) {
             
               if ( !Usuario.NIVEL_ACESSO_CONCESSIONARIA.equalsIgnoreCase(usuario.getNivelAcesso().getCodigo()) ) {
                     
                    ConcessionariaBusiness concessionariaBusiness = (ConcessionariaBusiness) springContext.getBean("concessionariaBO");
                    relatorioForm.set("concessionariaList", concessionariaBusiness.list()); 
                    
                    TipoLoteBusiness tipoLote = (TipoLoteBusiness) springContext.getBean("tipoLoteBO");
                    relatorioForm.set("tipoLoteList", tipoLote.list());
                     
                } else {
                     
                    isConcessionaria = false;
                    Concessionaria conc = usuario.getConcessionaria();
                    relatorioForm.set( "concessionariaId", conc.getEntityId() );                    
                    relatorioForm.set( "tipoLoteId", TipoLote.TIPO_REVISAO );
                     
                }
            } else {
                 
                 ConcessionariaBusiness concessionariaBusiness = (ConcessionariaBusiness) springContext.getBean("concessionariaBO");
                 relatorioForm.set("concessionariaList", concessionariaBusiness.list()); 
                 TipoLoteBusiness tipoLote = (TipoLoteBusiness) springContext.getBean("tipoLoteBO");
                 relatorioForm.set("tipoLoteList", tipoLote.list());
                 
            }             
            request.setAttribute("isConc", new Boolean(isConcessionaria)); 

            EmpresaBusiness empresa = (EmpresaBusiness) springContext.getBean("empresaBO");
            relatorioForm.set("empresaList", empresa.list());   
            
            LinhaBusiness linha = (LinhaBusiness) springContext.getBean("linhaBO");
            relatorioForm.set( "linhaList", linha.list());  
            
            return mapping.findForward("_relatorioConsultaNFFilter");
            
        } catch (BeansException bExp) {
            throw new ControllerException(bExp);
            
        } catch (BusinessException bExp) {
            throw new ControllerException(bExp);
            
        } catch (InvalidSessionException isExp) {
            throw new ControllerException(isExp);
            
        }           
    }
    
    /** Tarefa: Prepara tela de filtros para Relatório Consulta de Lotes.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     */ 
    public ActionForward prepareRelatorioConsultaLote ( ActionMapping mapping
                                                      , ActionForm form
                                                      , HttpServletRequest request
                                                      , HttpServletResponse response) throws ControllerException {
        
        try {
            
            DynaActionForm relatorioForm = (DynaActionForm) form;
            relatorioForm.initialize(mapping);

            //ConcessionariaBusiness concessionaria = (ConcessionariaBusiness) springContext.getBean("concessionariaBO");
            //relatorioForm.set("concessionariaList", concessionaria.list());       
            
            TipoLoteBusiness tipoLote = (TipoLoteBusiness) springContext.getBean("tipoLoteBO");
            relatorioForm.set("tipoLoteList", tipoLote.list());
            
            LinhaBusiness linha = (LinhaBusiness) springContext.getBean("linhaBO");
            relatorioForm.set( "linhaList", linha.list());  
            
            return mapping.findForward("_relatorioConsultaLoteFilter");
            
        } catch (BeansException bExp) {
            throw new ControllerException(bExp);
            
        } catch (BusinessException bExp) {
            throw new ControllerException(bExp);    
        }           
    }     
    
    /** Tarefa: Prepara tela de filtros para Relatório de Protocolo de Documentos de Revisão.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward prepareRelatorioDocRevisao( ActionMapping mapping
                                                   , ActionForm form
                                                   , HttpServletRequest request
                                                   , HttpServletResponse response) throws ControllerException {

        try {
            
            DynaActionForm relatorioForm = (DynaActionForm) form;
            relatorioForm.initialize(mapping);
            
            Usuario usuario = (Usuario) UserHelper.getBoundedUser(request.getSession());
            boolean isConcessionaria = true;
            
            if ( usuario != null ) {
	            if ( !Usuario.NIVEL_ACESSO_CONCESSIONARIA.equalsIgnoreCase(usuario.getNivelAcesso().getCodigo()) ) {
	                
	                ConcessionariaBusiness concessionariaBusiness = (ConcessionariaBusiness) springContext.getBean("concessionariaBO");
	                relatorioForm.set("concessionariaList", concessionariaBusiness.list()); 
	                
	            } else {
	                
	                isConcessionaria = false;
	                Concessionaria conc = usuario.getConcessionaria();
	                relatorioForm.set( "concessionariaId", conc.getEntityId() );
	                relatorioForm.set( "estado", conc.getEstado().getUf() );                
	                
	            }
            } else {
            	
            	ConcessionariaBusiness concessionariaBusiness = (ConcessionariaBusiness) springContext.getBean("concessionariaBO");
                relatorioForm.set("concessionariaList", concessionariaBusiness.list()); 
                
            }
            
            request.setAttribute("isConc", new Boolean(isConcessionaria));             
            
            return mapping.findForward("_relatorioDocRevisaoFilter");
            
        } catch (BeansException bExp) {
            throw new ControllerException(bExp);
            
        } catch (BusinessException bExp) {
            throw new ControllerException(bExp);    
            
        } catch (InvalidSessionException iSexp) {
            throw new ControllerException(iSexp);   
            
        }
        
    }      
    
    /** Tarefa: Prepara tela de filtros para Relatório de Processamento de Peças - Fase1.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward prepareRelatorioProcPecasFase1( ActionMapping mapping
                                                       , ActionForm form
                                                       , HttpServletRequest request
                                                       , HttpServletResponse response) throws ControllerException {

        try{
            DynaActionForm relatorioForm = (DynaActionForm) form;
            relatorioForm.initialize(mapping);

            EmpresaBusiness empresa = (EmpresaBusiness) springContext.getBean("empresaBO");
            relatorioForm.set("empresaList", empresa.list());
            
            return mapping.findForward("_relatorioProcPecasFase1Filter");           
        
        } catch (BeansException bExp) {
            throw new ControllerException(bExp);
            
        } catch (BusinessException bExp) {
            throw new ControllerException(bExp);    
            
        }
    }      
    
    /** Tarefa: Prepara tela de filtros para Relatório de Processamento de Peças - Fase2.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward prepareRelatorioProcPecasFase2( ActionMapping mapping
                                                       , ActionForm form
                                                       , HttpServletRequest request
                                                       , HttpServletResponse response) throws ControllerException {

        try{
            DynaActionForm relatorioForm = (DynaActionForm) form;
            relatorioForm.initialize(mapping);

            EmpresaBusiness empresa = (EmpresaBusiness) springContext.getBean("empresaBO");
            relatorioForm.set("empresaList", empresa.list());
            
            return mapping.findForward("_relatorioProcPecasFase2Filter");           
        
        } catch (BeansException bExp) {
            throw new ControllerException(bExp);
            
        } catch (BusinessException bExp) {
            throw new ControllerException(bExp);    
            
        }
    }  
    
    /** Tarefa: Prepara tela de filtros para Relatório Consulta Histórico Chassi.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward prepareRelatorioHistChassi( ActionMapping mapping
                                                   , ActionForm form
                                                   , HttpServletRequest request
                                                   , HttpServletResponse response) throws ControllerException {

        DynaActionForm relatorioForm = (DynaActionForm) form;
        relatorioForm.initialize(mapping);

        return mapping.findForward("_relatorioHistChassiFilter");

    }   
    
    /** Tarefa: Prepara tela de filtros para Relatório de Garantia e Peças.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward prepareRelatorioGarantiaPecas( ActionMapping mapping
                                                      , ActionForm form
                                                      , HttpServletRequest request
                                                      , HttpServletResponse response) throws ControllerException {

        try{
            DynaActionForm relatorioForm = (DynaActionForm) form;
            relatorioForm.initialize(mapping);

            EmpresaBusiness empresa = (EmpresaBusiness) springContext.getBean("empresaBO");
            relatorioForm.set("empresaList", empresa.list());
            
            LinhaBusiness linha = (LinhaBusiness) springContext.getBean("linhaBO");
            relatorioForm.set( "linhaList", linha.list());  

            return mapping.findForward("_relatorioGarantPecasFilter");      
        
        } catch (BeansException bExp) {
            throw new ControllerException(bExp);
            
        } catch (BusinessException bExp) {
            throw new ControllerException(bExp);    
            
        }       

    }     

    /** Tarefa: Prepara tela de filtros para Relatório de Garantia e Peças.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward prepareRelatorioGarantiaPecasModelo( ActionMapping mapping
                                                            , ActionForm form
                                                            , HttpServletRequest request
                                                            , HttpServletResponse response) throws ControllerException {

        try{
            DynaActionForm relatorioForm = (DynaActionForm) form;
            relatorioForm.initialize(mapping);

            ConcessionariaBusiness concessionaria = (ConcessionariaBusiness) springContext.getBean("concessionariaBO");
            relatorioForm.set("concessionariaList", concessionaria.list());
            
            LinhaBusiness linha = (LinhaBusiness) springContext.getBean("linhaBO");
            relatorioForm.set( "linhaList", linha.list());  

            ModeloBusiness modelo = (ModeloBusiness) springContext.getBean("modeloBO");
            relatorioForm.set("modeloList", modelo.list());
            
            return mapping.findForward("_relatorioGarantPecasModeloFilter");        
        
        } catch (BeansException bExp) {
            throw new ControllerException(bExp);
            
        } catch (BusinessException bExp) {
            throw new ControllerException(bExp);    
            
        }       

    }     

    /** Tarefa: Prepara tela de filtros para Relatório de Garantia e Peças.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward prepareRelatorioImportedParts( ActionMapping mapping
                                                      , ActionForm form
                                                      , HttpServletRequest request
                                                      , HttpServletResponse response) throws ControllerException {

        try{
            DynaActionForm relatorioForm = (DynaActionForm) form;
            relatorioForm.initialize(mapping);

            EmpresaBusiness empresa = (EmpresaBusiness) springContext.getBean("empresaBO");
            relatorioForm.set("empresaList", empresa.list());
            
            LinhaBusiness linha = (LinhaBusiness) springContext.getBean("linhaBO");
            relatorioForm.set( "linhaList", linha.list());  

            FornecedorBusiness fornecedor = (FornecedorBusiness) springContext.getBean("fornecedorBO");
            relatorioForm.set( "fornecedorList", fornecedor.list());    
            
            return mapping.findForward("_relatorioImportedPartsFilter");        
        
        } catch (BeansException bExp) {
            throw new ControllerException(bExp);
            
        } catch (BusinessException bExp) {
            throw new ControllerException(bExp);    
        }       
    }     
    
    /** Tarefa: Prepara tela de filtros para Relatório de Gráficos Individuais.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward prepareRelatorioGraficosIndividuais( ActionMapping mapping
                                                      , ActionForm form
                                                      , HttpServletRequest request
                                                      , HttpServletResponse response) throws ControllerException {

        try{
            
            DynaActionForm relatorioForm = (DynaActionForm) form;
            relatorioForm.initialize(mapping);

            EmpresaBusiness empresaBusiness = (EmpresaBusiness) springContext.getBean("empresaBO");
            LinhaBusiness   linhaBusiness   = (LinhaBusiness) springContext.getBean("linhaBO");
            relatorioForm.set("empresaList", empresaBusiness.list());
            relatorioForm.set("linhaList"  , linhaBusiness.list());
            
            /*
            try {               
                
                relatorioForm.set("dataInicio", DateUtils.applyMask(DateUtils.parseDate("01/01/2003"), "dd/MM/yyyy"));
                
            } catch (ParseException pEx) {
                
                throw new BusinessException(pEx);
                
            }*/
            relatorioForm.set("dataFim", DateUtils.applyMask(new Date(), "dd/MM/yyyy"));
            
            return mapping.findForward("_relatorioGraficosIndividuais");        
            
        } catch (BeansException bExp) {
            throw new ControllerException(bExp);
            
        } catch (BusinessException bExp) {
            throw new ControllerException(bExp);    
        }       
    }   
    
    /** Tarefa: Prepara tela de filtros para Relatório de Gráficos Individuais por Linha.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward prepareRelatorioGraficosIndividuaisLinha( ActionMapping mapping
                                                      , ActionForm form
                                                      , HttpServletRequest request
                                                      , HttpServletResponse response) throws ControllerException {

        try{
            
            DynaActionForm relatorioForm = (DynaActionForm) form;
            relatorioForm.initialize(mapping);

            EmpresaBusiness empresa 		= (EmpresaBusiness) springContext.getBean("empresaBO");
            ModeloBusiness  modeloBusiness  = (ModeloBusiness) springContext.getBean("modeloBO");
            LinhaBusiness   linhaBusiness   = (LinhaBusiness) springContext.getBean("linhaBO");
            
            relatorioForm.set("empresaList", empresa.list());
            relatorioForm.set("linhaList"  , linhaBusiness.list());
            relatorioForm.set("modeloList" , modeloBusiness.listGraphModelos());
            /*
            try {               
                
                relatorioForm.set("dataInicio", DateUtils.applyMask(DateUtils.parseDate("01/01/2003"), "dd/MM/yyyy"));
                
            } catch (ParseException pEx) {
                
                throw new BusinessException(pEx);
                
            }*/
            relatorioForm.set("dataFim", DateUtils.applyMask(new Date(), "dd/MM/yyyy"));
            
            return mapping.findForward("_relatorioGraficosIndividuaisLinha");        
            
        } catch (BeansException bExp) {
            throw new ControllerException(bExp);
            
        } catch (BusinessException bExp) {
            throw new ControllerException(bExp);    
        }       
    }   
    
    /** Tarefa: Prepara tela de filtros para Relatório de Gráficos Service Report.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward prepareRelatorioServiceReport( ActionMapping mapping
                                                      , ActionForm form
                                                      , HttpServletRequest request
                                                      , HttpServletResponse response) throws ControllerException {

        try{
            
            DynaActionForm relatorioForm = (DynaActionForm) form;
            relatorioForm.initialize(mapping);

            EmpresaBusiness empresa = (EmpresaBusiness) springContext.getBean("empresaBO");
            relatorioForm.set("empresaList", empresa.list());
            
            relatorioForm.set("dataFim", DateUtils.applyMask(new Date(), "dd/MM/yyyy"));
            
            return mapping.findForward("_relatorioServiceReport");      
            
        } catch (BeansException bExp) {
            throw new ControllerException(bExp);
            
        } catch (BusinessException bExp) {
            throw new ControllerException(bExp);    
        }       
    }
    
    /** Tarefa: Prepara tela de filtros para Relatório Gráfico de Garantia paga por Modelo de Produto.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward prepareRelatorioGraficoGarantiaPaga( ActionMapping mapping
                                                      , ActionForm form
                                                      , HttpServletRequest request
                                                      , HttpServletResponse response) throws ControllerException {

        try{
            
            DynaActionForm relatorioForm = (DynaActionForm) form;
            relatorioForm.initialize(mapping);

            EmpresaBusiness empresa = (EmpresaBusiness) springContext.getBean("empresaBO");
            LinhaBusiness   linhaBusiness   = (LinhaBusiness) springContext.getBean("linhaBO");
            relatorioForm.set("empresaList", empresa.list());
            relatorioForm.set("linhaList"  , linhaBusiness.list());
            /*
            try {               
                
                relatorioForm.set("dataInicio", DateUtils.applyMask(DateUtils.parseDate("01/01/2003"), "dd/MM/yyyy"));
                
            } catch (ParseException pEx) {
                
                throw new BusinessException(pEx);
                
            }*/
            relatorioForm.set("dataFim", DateUtils.applyMask(new Date(), "dd/MM/yyyy"));
            
            return mapping.findForward("_relatorioGraficoGarantiaPaga");        
            
        } catch (BeansException bExp) {
            throw new ControllerException(bExp);
            
        } catch (BusinessException bExp) {
            throw new ControllerException(bExp);    
        }       
    }   
    
    /** Tarefa: Prepara tela de filtros para Relatório Gráfico de Garantia Mensal Percentual.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward prepareRelatorioGraficoGarantiaMensalPercentual( ActionMapping mapping
                                                      , ActionForm form
                                                      , HttpServletRequest request
                                                      , HttpServletResponse response) throws ControllerException {

        try{
            
            DynaActionForm relatorioForm = (DynaActionForm) form;
            relatorioForm.initialize(mapping);

            EmpresaBusiness empresa = (EmpresaBusiness) springContext.getBean("empresaBO");
            LinhaBusiness   linhaBusiness   = (LinhaBusiness) springContext.getBean("linhaBO");
            relatorioForm.set("empresaList", empresa.list());
            relatorioForm.set("linhaList"  , linhaBusiness.list());
           
            relatorioForm.set("dataFim", DateUtils.applyMask(new Date(), "dd/MM/yyyy"));
            
            return mapping.findForward("_relatorioGraficoGarantiaMensalPercentual");        
            
        } catch (BeansException bExp) {
            throw new ControllerException(bExp);
            
        } catch (BusinessException bExp) {
            throw new ControllerException(bExp);    
        }       
    } 
    
    /** Tarefa: Prepara tela de filtros para Relatório Gerenciamento do Mês.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward prepareRelatorioGerenciamentoMes( ActionMapping mapping
                                                      , ActionForm form
                                                      , HttpServletRequest request
                                                      , HttpServletResponse response) throws ControllerException {

        try{
            
            DynaActionForm relatorioForm = (DynaActionForm) form;
            relatorioForm.initialize(mapping);
            LinhaBusiness   linhaBusiness   = (LinhaBusiness) springContext.getBean("linhaBO");
            ConcessionariaBusiness concessionariaBusiness = (ConcessionariaBusiness) springContext.getBean("concessionariaBO");
            
            Usuario usuario  = (Usuario)UserHelper.getBoundedUser(request.getSession());
            
            log.info("Código do Usuário:" + usuario.getNivelAcesso().getCodigo());
            
            if ( Usuario.isInterno(usuario.getNivelAcesso().getCodigo()) ) {
                relatorioForm.set("concessionariaList", concessionariaBusiness.list());
                request.setAttribute("isInnerUser", new Boolean(true));                
            } else
            	request.setAttribute("isInnerUser", new Boolean(false));
            
            relatorioForm.set("linhaList"  , linhaBusiness.list());            
            relatorioForm.set("dataFim", DateUtils.applyMask(new Date(), "dd/MM/yyyy"));
            
            return mapping.findForward("_relatorioGerenciamentoMes");        
            
        } catch (BeansException bExp) {
            throw new ControllerException(bExp);
            
        } catch (BusinessException bExp) {
            throw new ControllerException(bExp);    
        } catch ( InvalidSessionException isExp ) {
    		
    		throw new ControllerException(isExp);
    		
		}      
    } 

    //  ----[ Métodos Proccess ]---------------------------------------------------
    
    
    /** Tarefa: Processa tela de filtros para relatório Zero Km.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward proccessRelatorioZeroKm( ActionMapping mapping
                                                , ActionForm form
                                                , HttpServletRequest request
                                                , HttpServletResponse response) throws ControllerException {

        try {
            
            // 1. Obter o formulário atrelado à action corrente.
            DynaActionForm relatorioForm = (DynaActionForm) form;
            
            LinhaBusiness linhaBusiness = (LinhaBusiness) springContext.getBean("linhaBO");
            
            // 2. Obter os dados do formulário, digitados pelo usuário e 
            //    recebidos pelo formBean desta action.
            String dataInicio   = relatorioForm.getString("dataInicio");
            String dataFim      = relatorioForm.getString("dataFim");
            Long   km           = (Long)relatorioForm.get("km");
            Long   linha        = (Long)relatorioForm.get("linhaId");
            List   linhaList    = linhaBusiness.list();
            Linha  currentLinha = new Linha();
            
            Iterator linhaIt = linhaList.iterator();
            while ( linhaIt.hasNext() ) {
                
                Linha tmp = (Linha) linhaIt.next();
                
                if ( tmp.getEntityId().equals(linha) ) {
                    
                    currentLinha = tmp;
                    break;
                    
                }
                
            }
            
            // 3. Obter o objeto de negócios responsável pela obtenção
            //    das informações do relatório e fornecer os parâmetros
            //    recebidos do formulário.
            RelatorioBusiness relatorioBusiness = (RelatorioBusiness) springContext.getBean("relatorioBO");
            
            List results = relatorioBusiness.listZeroKm( DateUtils.parseDate(dataInicio)
                                                       , DateUtils.parseDate(dataFim)
                                                       , km
                                                       , linha);

            // 4. Cadastrar todas as informações a serem enviadas para o
            //    relatório (parâmetros e lista de resultados).
            Map parameters = new HashMap();
            parameters.put("DataInicio", DateUtils.applyMask( DateUtils.parseDate(dataInicio) ) );
            parameters.put("DataFim", DateUtils.applyMask( DateUtils.parseDate(dataFim) ) );    
            parameters.put("Quilometragem", km);
            parameters.put("Linha", currentLinha.getDescricao() );

            Map data = new HashMap();
            data.put(ReportConstants.REPORT_TEMPLATE, JASPER_FOLDER + "Sg_rl_zerokm.jasper");
            data.put(ReportConstants.REPORT_PARAMETERS, parameters);
            data.put(ReportConstants.REPORT_CONTENTS, results);
            data.put(ReportConstants.REPORT_DEFAULT_MEDIA, ReportConstants.REPORT_DEFAULT_MEDIA_PDF);
            data.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
            
            request.setAttribute(ReportConstants.REPORT_REQUEST_DATA, data);
            
            // 5. Despachar para o forward de relatorio.
            return mapping.findForward("report");
            
        } catch (BeansException bExp) {
            throw new ControllerException( bExp );
            
        } catch (NumberFormatException nfExp) {
            throw new ControllerException( nfExp );
            
        } catch (BusinessException bExp) {
            throw new ControllerException( bExp );
            
        } catch (ParseException pExp) {
            throw new ControllerException( pExp );
            
        }
        
    }    

    /** Tarefa: Processa tela de filtros para relatório Zero Km.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward proccessRelatorioAnalisePecas( ActionMapping mapping
                                                      , ActionForm form
                                                      , HttpServletRequest request
                                                      , HttpServletResponse response) throws ControllerException {

        try {
            
            // 1. Obter o formulário atrelado à action corrente.
            DynaActionForm relatorioForm = (DynaActionForm) form;
            
            // 2. Obter os dados do formulário, digitados pelo usuário e 
            //    recebidos pelo formBean desta action.
            String dataInicio  = relatorioForm.getString("dataInicio");
            String dataFim     = relatorioForm.getString("dataFim");
            Long   tipoProduto = (Long)relatorioForm.get("tipoProdutoId");
            Long   peca        = (Long)relatorioForm.get("peca");
            String modelo      = relatorioForm.getString("modelo");
            
            
            // 3. Obter o objeto de negócios responsável pela obtenção
            //    das informações do relatório e fornecer os parâmetros
            //    recebidos do formulário.
            RelatorioBusiness relatorioBusiness = (RelatorioBusiness) springContext.getBean("relatorioBO");
            
            List results = relatorioBusiness.listAnalisePecas( DateUtils.parseDate(dataInicio)
                                                             , DateUtils.parseDate(dataFim)
                                                             , tipoProduto
                                                             , peca
                                                             , modelo);

            // 4. Cadastrar todas as informações a serem enviadas para o
            //    relatório (parâmetros e lista de resultados).
            Map parameters = new HashMap();
            parameters.put("DataInicio", dataInicio);
            parameters.put("DataFim", dataFim); 
            parameters.put("TipoProduto", tipoProduto);
            parameters.put("Peca", peca);
            parameters.put("Modelo", modelo);           
            
            Map data = new HashMap();
            data.put(ReportConstants.REPORT_TEMPLATE, "Relatorio_Analise_Pecas");
            data.put(ReportConstants.REPORT_PARAMETERS, parameters);
            data.put(ReportConstants.REPORT_CONTENTS, results);
            data.put(ReportConstants.REPORT_DEFAULT_MEDIA, ReportConstants.REPORT_DEFAULT_MEDIA_PDF);
            data.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
            
            request.setAttribute(ReportConstants.REPORT_REQUEST_DATA, data);
            
            // 5. Despachar para o forward de relatorio.
            return mapping.findForward("_relatorioServlet");
            
        } catch (BeansException bExp) {
            throw new ControllerException( bExp );
            
        } catch (NumberFormatException nfExp) {
            throw new ControllerException( nfExp );
            
        } catch (BusinessException bExp) {
            throw new ControllerException( bExp );
            
        } catch (ParseException pExp) {
            throw new ControllerException( pExp );
            
        }       
    }    
    
    /** Tarefa: Processa tela de filtros para relatório de Pagamentos.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward proccessRelatorioPagamentos( ActionMapping mapping
                                                      , ActionForm form
                                                      , HttpServletRequest request
                                                      , HttpServletResponse response) throws ControllerException {

        try {
            
            // 1. Obter o formulário atrelado à action corrente.
            DynaActionForm relatorioForm = (DynaActionForm) form;
            
            // 2. Obter os dados do formulário, digitados pelo usuário e 
            //    recebidos pelo formBean desta action.
            String dataInicio     = relatorioForm.getString("dataInicio");
            String dataFim        = relatorioForm.getString("dataFim");
            Long   concessionaria = (Long)relatorioForm.get("concessionariaId");
            String tipoServico    = relatorioForm.getString("tipoServico");
            
            // 3. Obter o objeto de negócios responsável pela obtenção
            //    das informações do relatório e fornecer os parâmetros
            //    recebidos do formulário.
            RelatorioBusiness relatorioBusiness = (RelatorioBusiness) springContext.getBean("relatorioBO");
            
            List results = relatorioBusiness.listPagamentos( DateUtils.parseDate(dataInicio)
                                                           , DateUtils.parseDate(dataFim)
                                                           , concessionaria
                                                           , tipoServico);

            // 4. Cadastrar todas as informações a serem enviadas para o
            //    relatório (parâmetros e lista de resultados).
            Map parameters = new HashMap();
            parameters.put("DataInicio", dataInicio);
            parameters.put("DataFim", dataFim); 
            parameters.put("Concessionaria", concessionaria);
            parameters.put("TipoServico", tipoServico);         
            
            Map data = new HashMap();
            data.put(ReportConstants.REPORT_TEMPLATE, "Relatorio_Pagamentos");
            data.put(ReportConstants.REPORT_PARAMETERS, parameters);
            data.put(ReportConstants.REPORT_CONTENTS, results);
            data.put(ReportConstants.REPORT_DEFAULT_MEDIA, ReportConstants.REPORT_DEFAULT_MEDIA_PDF);
            data.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
            
            request.setAttribute(ReportConstants.REPORT_REQUEST_DATA, data);
            
            // 5. Despachar para o forward de relatorio.
            return mapping.findForward("_relatorioServlet");
            
        } catch (BeansException bExp) {
            throw new ControllerException( bExp );
            
        } catch (NumberFormatException nfExp) {
            throw new ControllerException( nfExp );
            
        } catch (BusinessException bExp) {
            throw new ControllerException( bExp );
            
        } catch (ParseException pExp) {
            throw new ControllerException( pExp );
            
        }       
    }     
     
    /** Tarefa: Processa tela de filtros para relatório de Recusas.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward proccessRelatorioRecusa( ActionMapping mapping
                                                      , ActionForm form
                                                      , HttpServletRequest request
                                                      , HttpServletResponse response) throws ControllerException {

        try {
            
            // 1. Obter o formulário atrelado à action corrente.
            DynaActionForm relatorioForm = (DynaActionForm) form;
            
            // 2. Obter os dados do formulário, digitados pelo usuário e 
            //    recebidos pelo formBean desta action.
            Long   empresa        = (Long)relatorioForm.get("empresaId");
            Long   concessionaria = (Long)relatorioForm.get("concessionariaId");
            Long   linha          = (Long)relatorioForm.get("linhaId");     
            String chassi         = relatorioForm.getString("chassi");
            String dataInicio     = relatorioForm.getString("dataInicio");
            String dataFim        = relatorioForm.getString("dataFim");
                        
            // 3. Obter o objeto de negócios responsável pela obtenção
            //    das informações do relatório e fornecer os parâmetros
            //    recebidos do formulário.
            RelatorioBusiness relatorioBusiness = (RelatorioBusiness) springContext.getBean("relatorioBO");
            
            List results = relatorioBusiness.listRecusa( empresa
                                                       , concessionaria
                                                       , linha
                                                       , chassi
                                                       , DateUtils.parseDate(dataInicio)
                                                       , DateUtils.parseDate(dataFim));

            // 4. Cadastrar todas as informações a serem enviadas para o
            //    relatório (parâmetros e lista de resultados).
            Map parameters = new HashMap();
            parameters.put("DataInicio", DateUtils.applyMask( DateUtils.parseDate(dataInicio) ) );
            parameters.put("DataFim", DateUtils.applyMask( DateUtils.parseDate(dataFim) ) );    

            Map data = new HashMap();
            data.put(ReportConstants.REPORT_TEMPLATE, JASPER_FOLDER + "Sg_rl_recusa.jasper");
            data.put(ReportConstants.REPORT_PARAMETERS, parameters);
            data.put(ReportConstants.REPORT_CONTENTS, results);
            data.put(ReportConstants.REPORT_DEFAULT_MEDIA, ReportConstants.REPORT_DEFAULT_MEDIA_PDF);
            data.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
            
            request.setAttribute(ReportConstants.REPORT_REQUEST_DATA, data);
            
            // 5. Despachar para o forward de relatorio.
            return mapping.findForward("report");
            
        } catch (BeansException bExp) {
            throw new ControllerException( bExp );
            
        } catch (NumberFormatException nfExp) {
            throw new ControllerException( nfExp );
            
        } catch (BusinessException bExp) {
            throw new ControllerException( bExp );
            
        } catch (ParseException pExp) {
            throw new ControllerException( pExp );
            
        }       
    }     
    
    /** Tarefa: Processa tela de filtros para relatório de Verificação.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward proccessRelatorioVerificacao( ActionMapping mapping
                                                      , ActionForm form
                                                      , HttpServletRequest request
                                                      , HttpServletResponse response) throws ControllerException {

        try {
            
            // 1. Obter o formulário atrelado à action corrente.
            DynaActionForm relatorioForm = (DynaActionForm) form;
            
            // 2. Obter os dados do formulário, digitados pelo usuário e 
            //    recebidos pelo formBean desta action.
            String dataInicio     = relatorioForm.getString("dataInicio");
            String dataFim        = relatorioForm.getString("dataFim");
            Long   concessionaria = (Long)relatorioForm.get("concessionariaId");
            Long   linhaId          = (Long)relatorioForm.get("linhaId");
            Long   valorMinimo    = (Long)relatorioForm.get("valorMinimo");
                        
            // 3. Obter o objeto de negócios responsável pela obtenção
            //    das informações do relatório e fornecer os parâmetros
            //    recebidos do formulário.
            RelatorioBusiness relatorioBusiness = (RelatorioBusiness) springContext.getBean("relatorioBO");
            LinhaBusiness linhaBusiness = (LinhaBusiness) springContext.getBean("linhaBO");
            
            Linha linha = linhaBusiness.get(linhaId);
            
            List results = relatorioBusiness.listVerificacao( DateUtils.parseDate(dataInicio)
                                                            , DateUtils.parseDate(dataFim)
                                                            , (Long)linha.getEntityId()
                                                            , concessionaria
                                                            , valorMinimo);
            
            double totalQtde = 0;
            double totalUnitario = 0;
            double totalVlTotal = 0;
            Iterator itRes = results.iterator();
            while ( itRes.hasNext() ) {
                
                RelatorioVerificacaoVO relatorioVerificacao = (RelatorioVerificacaoVO)itRes.next();
                totalQtde     += relatorioVerificacao.getQtdPecas()  != null ? relatorioVerificacao.getQtdPecas().doubleValue() : 0 ;
                totalUnitario += relatorioVerificacao.getValorPeca() != null ? relatorioVerificacao.getValorPeca().doubleValue() : 0 ;
                totalVlTotal  += relatorioVerificacao.getValorPeca() != null ? 
                                 relatorioVerificacao.getValorPeca().doubleValue() * relatorioVerificacao.getQtdPecas().doubleValue():
                                 0 ;     
                                
            }
            
            // 4. Cadastrar todas as informações a serem enviadas para o
            //    relatório (parâmetros e lista de resultados).
            Map parameters = new HashMap();
            parameters.put("DataInicio", dataInicio);
            parameters.put("DataFim", dataFim);     
            parameters.put("Linha", linha.getDescricao());
            parameters.put("strTotalQtde", NumberUtils.formatNumberMil(totalQtde));
            parameters.put("strTotalUnitario", NumberUtils.formatNumberCurrencyMil(totalUnitario));
            parameters.put("strTotalVlTotal", NumberUtils.formatNumberCurrencyMil(totalVlTotal));
            
            Map data = new HashMap();
            data.put(ReportConstants.REPORT_TEMPLATE, JASPER_FOLDER + "Sg_rl_verifica.jasper");
            data.put(ReportConstants.REPORT_PARAMETERS, parameters);
            data.put(ReportConstants.REPORT_CONTENTS, results);
            data.put(ReportConstants.REPORT_DEFAULT_MEDIA, ReportConstants.REPORT_DEFAULT_MEDIA_PDF);
            data.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
            
            request.setAttribute(ReportConstants.REPORT_REQUEST_DATA, data);
            
            // 5. Despachar para o forward de relatorio.
            return mapping.findForward("report");
            
        } catch (BeansException bExp) {
            throw new ControllerException( bExp );
            
        } catch (NumberFormatException nfExp) {
            throw new ControllerException( nfExp );
            
        } catch (BusinessException bExp) {
            throw new ControllerException( bExp );
            
        } catch (ParseException pExp) {
            throw new ControllerException( pExp );
            
        }       
    }    

    /** Tarefa: Processa tela de filtros para relatório Mensal por Modelo.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward proccessRelatorioMensalModelo( ActionMapping mapping
                                                      , ActionForm form
                                                      , HttpServletRequest request
                                                      , HttpServletResponse response) throws ControllerException {

        try {
            
            // 1. Obter o formulário atrelado à action corrente.
            DynaActionForm relatorioForm = (DynaActionForm) form;
            
            // 2. Obter os dados do formulário, digitados pelo usuário e 
            //    recebidos pelo formBean desta action.
            String mesReferencia = relatorioForm.getString("dataInicio");
            Long   empresaId     = (Long)relatorioForm.get("empresaId");
                        
            // 3. Obter o objeto de negócios responsável pela obtenção
            //    das informações do relatório e fornecer os parâmetros
            //    recebidos do formulário.
            RelatorioBusiness relatorioBusiness = (RelatorioBusiness) springContext.getBean("relatorioBO");
            EmpresaBusiness   empresaBusiness   = (EmpresaBusiness) springContext.getBean("empresaBO");
            
            Empresa empresa = empresaBusiness.get(empresaId);
            List    results = relatorioBusiness.listMensalModelo(mesReferencia, empresaId);
            
            // Se não houverem resultados, levantar um alerta informando
            // e retornar ao formulário de filtro
            if ( results == null || results.size() == 0 ) {
                
                ActionMessages warningMessages = new ActionMessages();
                
                warningMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("report.error.notfound") );
                super.addWarningMessages(request, warningMessages);
                
                return this.prepareRelatorioMensalModelo(mapping, form, request, response);
                
            }
            
            // 4. Cadastrar todas as informações a serem enviadas para o
            //    relatório (parâmetros e lista de resultados).
            Map parameters = new HashMap();
            parameters.put("mesReferencia", mesReferencia);
            parameters.put("nomeEmpresa", empresa.getNomeEmpresa() );
            
            Map data = new HashMap();
            data.put(ReportConstants.REPORT_TEMPLATE, JASPER_FOLDER + "Sg_rl_menmod.jasper");
            data.put(ReportConstants.REPORT_PARAMETERS, parameters);
            data.put(ReportConstants.REPORT_CONTENTS, results);
            data.put(ReportConstants.REPORT_DEFAULT_MEDIA, ReportConstants.REPORT_DEFAULT_MEDIA_PDF);
            data.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
            
            request.setAttribute(ReportConstants.REPORT_REQUEST_DATA, data);
            
            // 5. Despachar para o forward de relatorio.
            return mapping.findForward("report");
            
        } catch (BeansException bExp) {
            throw new ControllerException( bExp );
            
        } catch (NumberFormatException nfExp) {
            throw new ControllerException( nfExp );
            
        } catch (BusinessException bExp) {
            throw new ControllerException( bExp );
            
        }       
    }
    
    /** Tarefa: Processa tela de filtros para relatório Mala Direta.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward proccessRelatorioMalaDireta( ActionMapping mapping
                                                      , ActionForm form
                                                      , HttpServletRequest request
                                                      , HttpServletResponse response) throws ControllerException {

        try {
            
            // 1. Obter o formulário atrelado à action corrente.
            DynaActionForm relatorioForm = (DynaActionForm) form;
            
            // 2. Obter os dados do formulário, digitados pelo usuário e 
            //    recebidos pelo formBean desta action.
            Long   empresa        = (Long)relatorioForm.get("empresaId");
            Long   concessionaria = (Long)relatorioForm.get("concessionariaId");
            String dataInicio     = relatorioForm.getString("dataInicio");
            String dataFim        = relatorioForm.getString("dataFim");
            Long   linha          = (Long)relatorioForm.get("linhaId");            
            String estado         = relatorioForm.getString("estado");
            String modelo         = relatorioForm.getString("modelo");
            String chassiIni      = relatorioForm.getString("chassiIni");
            String chassiFim      = relatorioForm.getString("chassiFim");
                        
            // 3. Obter o objeto de negócios responsável pela obtenção
            //    das informações do relatório e fornecer os parâmetros
            //    recebidos do formulário.
            RelatorioBusiness relatorioBusiness = (RelatorioBusiness) springContext.getBean("relatorioBO");
            
            List results = relatorioBusiness.listMalaDireta( DateUtils.parseDate(dataInicio)
                                                           , DateUtils.parseDate(dataFim)
                                                           , empresa
                                                           , concessionaria
                                                           , linha
                                                           , estado
                                                           , modelo
                                                           , chassiIni
                                                           , chassiFim);
            
            log.info("Resultados:" + results.size());
            
            // 4. Cadastrar todas as informações a serem enviadas para o
            //    relatório (parâmetros e lista de resultados).
            Map data = new HashMap();
            data.put(ReportConstants.REPORT_TEMPLATE, JASPER_FOLDER + "Sg_rl_arqdir.jasper");
            data.put(ReportConstants.REPORT_CONTENTS, results);
            data.put(ReportConstants.REPORT_DEFAULT_MEDIA, ReportConstants.REPORT_DEFAULT_MEDIA_CSV);
            data.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
            
            request.setAttribute(ReportConstants.REPORT_REQUEST_DATA, data);
            
            // 5. Despachar para o forward de relatorio.
            return mapping.findForward("report");
            
        } catch (BeansException bExp) {
            throw new ControllerException( bExp );
            
        } catch (NumberFormatException nfExp) {
            throw new ControllerException( nfExp );
            
        } catch (BusinessException bExp) {
            throw new ControllerException( bExp );
            
        } catch (ParseException pExp) {
            throw new ControllerException( pExp );
            
        }       
    }    
    
    /** Tarefa: Processa tela de filtros para relatório Solicitação de Garantia.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward proccessRelatorioSolicitacaoGarantia( ActionMapping mapping
                                                             , ActionForm form
                                                             , HttpServletRequest request
                                                             , HttpServletResponse response) throws ControllerException {

        ActionMessages warningMessages = new ActionMessages();
        ActionMessages problemMessages = new ActionMessages();
        try {
            
            // 1. Obter o formulário atrelado à action corrente.
            DynaActionForm relatorioForm = (DynaActionForm) form;
            
            // 2. Obter os dados do formulário, digitados pelo usuário e 
            //    recebidos pelo formBean desta action.
            Integer lote           = (Integer)relatorioForm.get("lote");
            Long    concessionaria = (Long)relatorioForm.get("concessionariaId");
            
            // 3. Validar se o lote é do tipo "Garantia". Caso seja 
            //    do tipo "Revisão", devemos lançar um alerta.
            LoteBusiness loteBusiness = (LoteBusiness) springContext.getBean("loteBO");
            Lote l = loteBusiness.get(lote);
            if ( l != null ) { 
	            if ( l.getTipoLote().getEntityId().equals(TipoLote.TIPO_REVISAO) ) {
	                
	                warningMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("lote.msg.revisao") );
	                super.addWarningMessages(request, warningMessages);
	                
	                return this.prepareRelatorioSolicitacaoGarantia(mapping, form, request, response);
	                
	            }    
            } else {
            	
            	problemMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("proccessRelatorioSolicitacaoGarantia.form.lote.notFound") );
                super.addProblemMessages(request, problemMessages);
                
                return this.prepareRelatorioSolicitacaoGarantia(mapping, form, request, response);
            	
            }
            
            if ( !l.getConcessionaria().getEntityId().equals(concessionaria) ) {
            	
            	warningMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("proccessRelatorioSolicitacaoGarantia.form.lote.invalid",l.getEntityId()) );
                super.addWarningMessages(request, warningMessages);
                
                return this.prepareRelatorioSolicitacaoGarantia(mapping, form, request, response);            	
            	
            }
            
            // 4. Obter o objeto de negócios responsável pela obtenção
            //    das informações do relatório e fornecer os parâmetros
            //    recebidos do formulário.
            RelatorioBusiness relatorioBusiness = (RelatorioBusiness) springContext.getBean("relatorioBO");
            
            List result          = relatorioBusiness.listSolicitacaoGarantiaNotas( lote, concessionaria );            
            List resultGarantias = relatorioBusiness.listSolicitacaoGarantias( lote, concessionaria );
            List resultPecas     = relatorioBusiness.listSolicitacaoGarantiasPecas( lote, concessionaria );
            
            /*
            RelatorioSolicitacaoGarantiaNotaVO  currentNota = null;
            RelatorioSolicitacaoGarantiaVO      garantias   = new RelatorioSolicitacaoGarantiaVO();
            RelatorioSolicitacaoGarantiaPecaVO  pecas       = new RelatorioSolicitacaoGarantiaPecaVO();
            
            Iterator resultIt = result.iterator();
            while (resultIt.hasNext()){
                
                //Recupera a lista de Notas
                currentNota = (RelatorioSolicitacaoGarantiaNotaVO)resultIt.next();
                
                //Recupera a lista de Garantias             
                if (!currentNota.getListGarantias().isEmpty()){
                    
                    Iterator garantiaIt = currentNota.getListGarantias().iterator();
                    while (garantiaIt.hasNext()){
                        garantias = (RelatorioSolicitacaoGarantiaVO)garantiaIt.next();
                        resultGarantias.add(garantias);
                    }
                }
                
                //Recupera a lista de Peças
                if (!currentNota.getListPeca().isEmpty()){

                    Iterator pecaIt = currentNota.getListPeca().iterator();
                    while (pecaIt.hasNext()){
                        pecas = (RelatorioSolicitacaoGarantiaPecaVO)pecaIt.next();
                        resultPecas.add(pecas);
                    }
                }
            }            
            */
            if ( result.size() > 0 ) {
            
                // 5. Cadastrar todas as informações a serem enviadas para o
                //    relatório (parâmetros e lista de resultados).
                Map parameters = new HashMap();
                parameters.put("subReportPath01", JASPER_FOLDER + "Sg_rl_prdocsg_subreport0.jasper");
                parameters.put("subReportPath02", JASPER_FOLDER + "Sg_rl_prdocsg_subreport1.jasper");
                parameters.put("hasPecas", new Boolean(!resultPecas.isEmpty()));
                
                Map subreportFields = new HashMap();
                subreportFields.put(ReportConstants.REPORT_MAIN, result);
                subreportFields.put("listModelo", resultGarantias);
                subreportFields.put("listPeca", resultPecas);
                
                Map data = new HashMap();
                data.put(ReportConstants.REPORT_TEMPLATE, JASPER_FOLDER + "Sg_rl_prdocsg.jasper");
                data.put(ReportConstants.REPORT_PARAMETERS, parameters);
                data.put(ReportConstants.REPORT_CONTENTS, subreportFields);
                data.put(ReportConstants.REPORT_DEFAULT_MEDIA, ReportConstants.REPORT_DEFAULT_MEDIA_PDF);
                data.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
                
                request.setAttribute(ReportConstants.REPORT_REQUEST_DATA, data);
                
                // 6. Despachar para o forward de relatorio.
                return mapping.findForward("report");   
            
            } else {
                
                warningMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("proccessRelatorioSolicitacaoGarantia.form.notFound") );
                super.addWarningMessages(request, warningMessages);
                
                return this.prepareRelatorioSolicitacaoGarantia(mapping, form, request, response);
                
            }
            
        } catch (BeansException bExp) {
            throw new ControllerException( bExp );
            
        } catch (NumberFormatException nfExp) {
            throw new ControllerException( nfExp );
            
        } catch (BusinessException bExp) {
            throw new ControllerException( bExp );          
            
        }       
    }      
    
    /** Tarefa: Processa tela de filtros para Relatório de Processamento de Revisoes - Fase1 .
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward proccessRelatorioProcRevisoesFase1 ( ActionMapping mapping
                                                             , ActionForm form
                                                             , HttpServletRequest request
                                                             , HttpServletResponse response) throws ControllerException {
        
        ActionMessages          warningMessages = new ActionMessages();
        
        try {
            
            // 1. Obter o formulário atrelado à action corrente.
            DynaActionForm relatorioForm = (DynaActionForm) form;
            
            // 2. Obter os dados do formulário, digitados pelo usuário e 
            //    recebidos pelo formBean desta action.
            Integer lote    = (Integer)relatorioForm.get("lote");
            Long    empresa = (Long)relatorioForm.get("empresaId");

            // 3. Obter o objeto de negócios responsável pela obtenção
            //    das informações do relatório e fornecer os parâmetros
            //    recebidos do formulário.
            RelatorioBusiness relatorioBusiness = (RelatorioBusiness) springContext.getBean("relatorioBO");
            
            List results = relatorioBusiness.listProcRevisoesFase1(empresa, lote);
            
            if ( results.size() > 0 ) {                     
                // 4. Cadastrar todas as informações a serem enviadas para o
                //    relatório (parâmetros e lista de resultados).
                Map data = new HashMap();
                data.put(ReportConstants.REPORT_TEMPLATE, JASPER_FOLDER + "Sg_rl_prrev1.jasper");
                data.put(ReportConstants.REPORT_CONTENTS, results);
                data.put(ReportConstants.REPORT_DEFAULT_MEDIA, ReportConstants.REPORT_DEFAULT_MEDIA_PDF);
                data.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
                
                request.setAttribute(ReportConstants.REPORT_REQUEST_DATA, data);
                
                // 5. Despachar para o forward de relatorio.
                return mapping.findForward("report");
                
            } else {
                
                warningMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("processRevFase1.form.notFound") );
                super.addWarningMessages(request, warningMessages);
                
                return this.prepareRelatorioProcRevisoesFase1(mapping, form, request, response);
                
            }
            
        } catch (BeansException bExp) {
            throw new ControllerException( bExp );
            
        } catch (NumberFormatException nfExp) {
            throw new ControllerException( nfExp );
            
        } catch (BusinessException bExp) {
            throw new ControllerException( bExp );          
            
        }       
    }     
    
    /** Tarefa: Processa tela de filtros para Relatório de Processamento de Revisoes - Fase2 .
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward proccessRelatorioProcRevisoesFase2 ( ActionMapping mapping
                                                             , ActionForm form
                                                             , HttpServletRequest request
                                                             , HttpServletResponse response) throws ControllerException {
        
        ActionMessages          warningMessages = new ActionMessages();
        
        try {
            
            // 1. Obter o formulário atrelado à action corrente.
            DynaActionForm relatorioForm = (DynaActionForm) form;
            
            // 2. Obter os dados do formulário, digitados pelo usuário e 
            //    recebidos pelo formBean desta action.
            Integer loteId  = (Integer)relatorioForm.get("lote");
            Long    empresa = (Long)relatorioForm.get("empresaId");
            
            Usuario usuario = (Usuario) UserHelper.getBoundedUser(request.getSession());
            
            // 3. Obter o objeto de negócios responsável pela obtenção
            //    das informações do relatório e fornecer os parâmetros
            //    recebidos do formulário.
            LoteBusiness  	  loteBusiness  	= (LoteBusiness)  	  springContext.getBean("loteBO");
            
            Lote lote = loteBusiness.get(loteId);
            
            if ( lote != null ) {
            
	            if ( !usuario.getConcessionaria().getEntityId().equals(lote.getConcessionaria().getEntityId())) {
	                    
	            	warningMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("processRelatorioProcPecasFase2.form.lotNotUser") );
	                super.addWarningMessages(request, warningMessages);
	                    
	                return this.prepareRelatorioProcPecasFase2(mapping, form, request, response);
	                    
	            }
            } else {
            	warningMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("processRelatorioProcPecasFase2.form.lotNotFound") );
                super.addWarningMessages(request, warningMessages);
                    
                return this.prepareRelatorioProcPecasFase2(mapping, form, request, response);
            }
            
            // 3. Obter o objeto de negócios responsável pela obtenção
            //    das informações do relatório e fornecer os parâmetros
            //    recebidos do formulário.
            RelatorioBusiness relatorioBusiness = (RelatorioBusiness) springContext.getBean("relatorioBO");
            
            List results = relatorioBusiness.listProcRevisoesFase2(empresa, (Integer)lote.getEntityId());
            
            //System.out.println(" Valores para o relatório ProcRevisoesFase2 - Qtde" + results.size());
            
            if ( results.size() > 0 ) {
                
            	Double totalRev1 = relatorioBusiness.totalRev(results, 1);
                Double totalRev2 = relatorioBusiness.totalRev(results, 2);
                Double totalRev3 = relatorioBusiness.totalRev(results, 3);
                double totalLote = totalRev1.doubleValue() + totalRev2.doubleValue() + totalRev3.doubleValue();
                
                List notas = relatorioBusiness.listNotasByLote(loteId);
                
                JRBeanCollectionDataSource listNotasDts = null;
                
                if ( !notas.isEmpty() )
                	listNotasDts = new JRBeanCollectionDataSource(notas);
                
                // 4. Cadastrar todas as informações a serem enviadas para o
                //    relatório (parâmetros e lista de resultados).
                Map parameters = new HashMap();
                //System.out.println("TotalRev2:" + NumberUtils.formatNumberCurrency(NumberUtils.round(totalRev2.doubleValue(),2)));
                parameters.put("totalRev1", NumberUtils.formatNumberCurrency(NumberUtils.round(totalRev1.doubleValue(),2)));
                parameters.put("totalRev2", NumberUtils.formatNumberCurrency(NumberUtils.round(totalRev2.doubleValue(),2)));
                parameters.put("totalRev3", NumberUtils.formatNumberCurrency(NumberUtils.round(totalRev3.doubleValue(),2)));
                parameters.put("totalLote", NumberUtils.formatNumberCurrency(NumberUtils.round(totalLote, 2)));
                parameters.put("subReportPath0"   , JASPER_FOLDER + "Sg_rl_prrev2_new_subreport0.jasper");
                parameters.put("listNotasDts", listNotasDts);
                
                Map subreportFields = new HashMap();
                
                subreportFields.put(ReportConstants.REPORT_MAIN, results);
                
                Map data = new HashMap();
                data.put(ReportConstants.REPORT_TEMPLATE, JASPER_FOLDER + "Sg_rl_prrev2.jasper");
                data.put(ReportConstants.REPORT_PARAMETERS, parameters);
                data.put(ReportConstants.REPORT_CONTENTS, subreportFields);
                data.put(ReportConstants.REPORT_DEFAULT_MEDIA, ReportConstants.REPORT_DEFAULT_MEDIA_PDF);
                data.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
                
                request.setAttribute(ReportConstants.REPORT_REQUEST_DATA, data);
                
                //System.out.println("Despachar para o forward de relatório");
                
                // 5. Despachar para o forward de relatorio.
                return mapping.findForward("report");
                
            } else {
                
                warningMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("processRevFase2.form.notFound") );
                super.addWarningMessages(request, warningMessages);
                
                return this.prepareRelatorioProcRevisoesFase2(mapping, form, request, response);
                
            }
            
        } catch (BeansException bExp) {
            throw new ControllerException( bExp );
            
        } catch (NumberFormatException nfExp) {
            throw new ControllerException( nfExp );
            
        } catch (BusinessException bExp) {
            throw new ControllerException( bExp );          
            
        } catch ( InvalidSessionException isExp ) {    		
    		throw new ControllerException(isExp);
    		
		}        
    }       
    
    /** Tarefa: Processa tela de filtros para Relatório Consulta de Notas Fiscais.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward proccessRelatorioConsultaNF ( ActionMapping mapping
                                                     , ActionForm form
                                                     , HttpServletRequest request
                                                     , HttpServletResponse response) throws ControllerException {
        
        ActionMessages          warningMessages = new ActionMessages();
        
        try {
            
            // 1. Obter o formulário atrelado à action corrente.
            DynaActionForm relatorioForm = (DynaActionForm) form;
            
            // 2. Obter os dados do formulário, digitados pelo usuário e 
            //    recebidos pelo formBean desta action.
            String  dataInicio     = relatorioForm.getString("dataInicio");
            String  dataFim        = relatorioForm.getString("dataFim");
            Long    linha          = (Long)relatorioForm.get("linhaId");
            Integer lote           = (Integer)relatorioForm.get("lote");            
            Long    tipoLote       = (Long)relatorioForm.get("tipoLoteId");  
            Long    concessionaria = (Long)relatorioForm.get("concessionariaId");
            Long    empresa        = (Long)relatorioForm.get("empresaId");
            
            // 3. Obter o objeto de negócios responsável pela obtenção
            //    das informações do relatório e fornecer os parâmetros
            //    recebidos do formulário.
            RelatorioBusiness relatorioBusiness = (RelatorioBusiness) springContext.getBean("relatorioBO");
            
            List results = relatorioBusiness.listConsultaNF ( DateUtils.parseDate(dataInicio)
                                                            , DateUtils.parseDate(dataFim)
                                                            , linha
                                                            , lote
                                                            , tipoLote
                                                            , concessionaria
                                                            , empresa);
            
            System.out.println(" Valores para o relatório proccessRelatorioConsultaNF : " + results.size());
            
            if ( results.size() > 0 ) { 
                
                //   4. Cadastrar todas as informações a serem enviadas para o
                //    relatório (parâmetros e lista de resultados).
                Map parameters = new HashMap();
                parameters.put("DataInicio", DateUtils.applyMask(DateUtils.parseDate(dataInicio)));
                parameters.put("DataFim", DateUtils.applyMask(DateUtils.parseDate(dataFim)));
                
                Map data = new HashMap();
                data.put(ReportConstants.REPORT_TEMPLATE, JASPER_FOLDER + "Sg_co_nf.jasper");
                data.put(ReportConstants.REPORT_PARAMETERS, parameters);
                data.put(ReportConstants.REPORT_CONTENTS, results);
                data.put(ReportConstants.REPORT_DEFAULT_MEDIA, ReportConstants.REPORT_DEFAULT_MEDIA_PDF);
                data.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
                
                request.setAttribute(ReportConstants.REPORT_REQUEST_DATA, data);
                
                // 5. Despachar para o forward de relatorio.
                return mapping.findForward("report");
                
                /*
            
                request.setAttribute("now", new Date());
                request.setAttribute("listEmpresas", results);
                
                
                // 4. Despachar para o forward de relatorio.
                return mapping.findForward("_relatorioConsultaNfResultForm");
                */
            } else {
                
                warningMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("processRelatorioConsultaNF.form.notFound") );
                super.addWarningMessages(request, warningMessages);
                
                return this.prepareRelatorioConsultaNF(mapping, form, request, response);
                
            }

            
        } catch (BeansException bExp) {
            throw new ControllerException( bExp );
            
        } catch (NumberFormatException nfExp) {
            throw new ControllerException( nfExp );
            
        } catch (BusinessException bExp) {
            throw new ControllerException( bExp );  
        
        } catch (ParseException pExp) {
            throw new ControllerException( pExp );          
            
        }       
    }    
    
    /** Tarefa: Processa tela de filtros para Relatório Consulta de Lotes.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward proccessRelatorioConsultaLote ( ActionMapping mapping
                                                       , ActionForm form
                                                       , HttpServletRequest request
                                                       , HttpServletResponse response) throws ControllerException {

        try {
            
            Concessionaria concessionaria = ((Usuario)UserHelper.getBoundedUser(request.getSession())).getConcessionaria();
            
            // 1. Obter o formulário atrelado à action corrente.
            DynaActionForm relatorioForm = (DynaActionForm) form;
            LinhaBusiness linhaBusiness = (LinhaBusiness) springContext.getBean("linhaBO");
            
            // 2. Obter os dados do formulário, digitados pelo usuário e 
            //    recebidos pelo formBean desta action.
            String  dataInicio     = relatorioForm.getString("dataInicio");
            String  dataFim        = relatorioForm.getString("dataFim");
            Long    linhaId          = (Long)relatorioForm.get("linhaId");
            Integer lote           = (Integer)relatorioForm.get("lote");            
            Long    tipoLote       = (Long)relatorioForm.get("tipoLoteId");  
            //Long    concessionaria = (Long)relatorioForm.get("concessionariaId");
                        
            Linha linha = linhaBusiness.get(linhaId);
            relatorioForm.set("linha", linha.getDescricao());
            
            // 3. Obter o objeto de negócios responsável pela obtenção
            //    das informações do relatório e fornecer os parâmetros
            //    recebidos do formulário.
            RelatorioBusiness relatorioBusiness = (RelatorioBusiness) springContext.getBean("relatorioBO");
            
            List result = relatorioBusiness.listConsultaLote( DateUtils.parseDate(dataInicio)
                                                            , DateUtils.parseDate(dataFim)
                                                            , (Long)linha.getEntityId()
                                                            , lote
                                                            , tipoLote
                                                            , (Long)concessionaria.getEntityId());
            
            request.setAttribute("now", new Date());
            request.setAttribute("listConcessionaria", result);
            
            // 4. Despachar para o forward de relatorio.
            return mapping.findForward("_relatorioConsultaLoteResultForm");
            
        } catch (BeansException bExp) {
            throw new ControllerException( bExp );
            
        } catch (NumberFormatException nfExp) {
            throw new ControllerException( nfExp );
            
        } catch (BusinessException bExp) {
            throw new ControllerException( bExp );  
        
        } catch (ParseException pExp) {
            throw new ControllerException( pExp );          
            
        } catch (InvalidSessionException iSExp) {
            throw new ControllerException( iSExp ); 
            
        }       
    }     

    /** Tarefa: Processa tela de filtros para Relatório de Protocolo de Documentos de Revisão.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward proccessRelatorioDocRevisao ( ActionMapping mapping
                                                     , ActionForm form
                                                     , HttpServletRequest request
                                                     , HttpServletResponse response) throws ControllerException {

        try {
            
            ActionMessages warningMessages = new ActionMessages();
            ActionMessages problemMessages = new ActionMessages();
            
            // 1. Obter o formulário atrelado à action corrente.
            DynaActionForm relatorioForm = (DynaActionForm) form;
            
            // 2. Obter os dados do formulário, digitados pelo usuário e 
            //    recebidos pelo formBean desta action.
            Integer loteId           = (Integer)relatorioForm.get("lote");
            
            // 3. Validar se o lote é do tipo "Garantia". Caso seja 
            //    do tipo "Revisão", devemos lançar um alerta.
            LoteBusiness loteBusiness = (LoteBusiness) springContext.getBean("loteBO");
            
            Lote 	 lote 	= loteBusiness.get(loteId);
            TipoLote tl 	= null;
            
            if ( lote != null ) {
            	
	            tl = lote.getTipoLote();
	            if ( tl.getEntityId().equals(TipoLote.TIPO_GARANTIA) ) {
	                
	                warningMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("lote.msg.garantia") );
	                super.addWarningMessages(request, warningMessages);
	                
	                return this.prepareRelatorioDocRevisao(mapping, form, request, response);
	                
	            }  
            } else {
            	
            	problemMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("proccessRelatorioDocRevisao.form.lote.notFound") );
                super.addProblemMessages(request, problemMessages);
                
                return this.prepareRelatorioDocRevisao(mapping, form, request, response);
            	
            }
            
            // 3. Obter o objeto de negócios responsável pela obtenção
            //    das informações do relatório e fornecer os parâmetros
            //    recebidos do formulário.
            RelatorioBusiness relatorioBusiness = (RelatorioBusiness) springContext.getBean("relatorioBO");
            
            //List resultDetalhe = new ArrayList();
            
            List result     = relatorioBusiness.listDocRevisao(loteId);
            List resultDet  = relatorioBusiness.listDocRevisaoDetalhes(loteId);
            /*
            RelatorioDocRevisaoVO        currentRevisao = null;
            RelatorioDocRevisaoDetalheVO detalhe        = new RelatorioDocRevisaoDetalheVO();
            
            Iterator resultIt = result.iterator();
            while (resultIt.hasNext()){
                
                currentRevisao = (RelatorioDocRevisaoVO)resultIt.next();
                
                if (currentRevisao.getListRevisoes().isEmpty() == false) {
                    
                    Iterator detalheIt = currentRevisao.getListRevisoes().iterator();
                    while (detalheIt.hasNext()){
                        
                        detalhe = (RelatorioDocRevisaoDetalheVO)detalheIt.next();
                        resultDetalhe.add(detalhe);
                    }
                }
            }
            */
            System.out.println("Results:"   + result.size());
            System.out.println("resultDet:" + resultDet.size());
            
            if ( result.size() > 0 ||(lote.getStatusLote().getEntityId().equals(StatusLote.STATUS_PERIODICA) && resultDet.size() > 0) ) { 
            	
	            // 4. Cadastrar todas as informações a serem enviadas para o
	            //    relatório (parâmetros e lista de resultados).         
	            Map parameters = new HashMap();
	            //parameters.put("subReportPath01", JASPER_FOLDER + "Sg_rl_prdocrev_subreport0.jasper");
	            Iterator itRes = resultDet.iterator();
	            while ( itRes.hasNext() ) {
	            	RelatorioDocRevisaoDetalheVO relatorioDocRevisao = (RelatorioDocRevisaoDetalheVO)itRes.next();
	            	parameters.put("qtdeRev1", relatorioDocRevisao.getQtdRev1());
	            	parameters.put("qtdeRev2", relatorioDocRevisao.getQtdRev2());
	            	parameters.put("qtdeRev3", relatorioDocRevisao.getQtdRev3());
	            	parameters.put("qtdeRev4", relatorioDocRevisao.getQtdRev4());
	            	parameters.put("qtdeRev5", relatorioDocRevisao.getQtdRev5());
	            	parameters.put("qtdeRev6", relatorioDocRevisao.getQtdRev6());
	            	parameters.put("qtdeRev7", relatorioDocRevisao.getQtdRev7());
	            	parameters.put("qtdeRev8", relatorioDocRevisao.getQtdRev8());
	            	parameters.put("qtdeRev9", relatorioDocRevisao.getQtdRev9());
	            	parameters.put("qtdeRev10", relatorioDocRevisao.getQtdRev10());
	            	parameters.put("qtdeRev11", relatorioDocRevisao.getQtdRev11());
	            	parameters.put("qtdeRev12", relatorioDocRevisao.getQtdRev12());
	            }
	            //Map subreportFields = new HashMap();
	            
	            //subreportFields.put(ReportConstants.REPORT_MAIN, result);
	            //subreportFields.put("listRevisoes", resultDet); 
	            
	            Map data = new HashMap();
	            data.put(ReportConstants.REPORT_TEMPLATE		, JASPER_FOLDER + "Sg_rl_prdocrev_periodico.jasper");
	            data.put(ReportConstants.REPORT_PARAMETERS		, parameters);            
	            data.put(ReportConstants.REPORT_CONTENTS		, result);
	            data.put(ReportConstants.REPORT_DEFAULT_MEDIA	, ReportConstants.REPORT_DEFAULT_MEDIA_PDF);
	            data.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
	            
	            request.setAttribute(ReportConstants.REPORT_REQUEST_DATA, data);
	            
	            // 5. Despachar para o forward de relatorio.
	            return mapping.findForward("report");
            
	        } else {
	            
	            warningMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("proccessRelatorioDocRevisao.form.notFound") );
	            super.addWarningMessages(request, warningMessages);
	            
	            return this.prepareRelatorioDocRevisao(mapping, form, request, response);
	            
	        }
            
        } catch (BeansException bExp) {
            throw new ControllerException( bExp );
            
        } catch (NumberFormatException nfExp) {
            throw new ControllerException( nfExp );
            
        } catch (BusinessException bExp) {
            throw new ControllerException( bExp );          
            
        }       
    }    

    /** Tarefa: Processa tela de filtros para Relatório de Processamento de Peças - Fase1 .
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward proccessRelatorioProcPecasFase1 ( ActionMapping mapping
                                                         , ActionForm form
                                                         , HttpServletRequest request
                                                         , HttpServletResponse response) throws ControllerException {
        
        ActionMessages          warningMessages = new ActionMessages();
        
        try {
            
            // 1. Obter o formulário atrelado à action corrente.
            DynaActionForm relatorioForm = (DynaActionForm) form;
            
            // 2. Obter os dados do formulário, digitados pelo usuário e 
            //    recebidos pelo formBean desta action.
            Long    empresa = (Long)relatorioForm.get("empresaId");  
            Integer lote    = (Integer)relatorioForm.get("lote");           

            // 3. Obter o objeto de negócios responsável pela obtenção
            //    das informações do relatório e fornecer os parâmetros
            //    recebidos do formulário.
            RelatorioBusiness relatorioBusiness = (RelatorioBusiness) springContext.getBean("relatorioBO");
            
            List result  = relatorioBusiness.listProcPecasFase1(empresa, lote);
            
            log.info("Valores recuperados para o relatório:" + result.size());
            
            if ( result.size() > 0 ) {
            
                // 4. Cadastrar todas as informações a serem enviadas para o
                //    relatório (parâmetros e lista de resultados).
                Map data = new HashMap();
                data.put(ReportConstants.REPORT_TEMPLATE, JASPER_FOLDER + "Sg_rl_prpc1.jasper");
                data.put(ReportConstants.REPORT_CONTENTS, result);
                data.put(ReportConstants.REPORT_DEFAULT_MEDIA, ReportConstants.REPORT_DEFAULT_MEDIA_PDF);
                data.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
                
                request.setAttribute(ReportConstants.REPORT_REQUEST_DATA, data);
                
                log.info("Dispachando para o relatório:" + result.size());
                
                // 5. Despachar para o forward de relatorio.
                return mapping.findForward("report");
            
            } else {
                
                warningMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("processRelatorioProcPecasFase1.form.notFound") );
                super.addWarningMessages(request, warningMessages);
                
                return this.prepareRelatorioProcPecasFase1(mapping, form, request, response);
                
            }
            
            
        } catch (BeansException bExp) {
            throw new ControllerException( bExp );
            
        } catch (NumberFormatException nfExp) {
            throw new ControllerException( nfExp );
            
        } catch (BusinessException bExp) {
            throw new ControllerException( bExp );          
            
        }       
    }      

    /** Tarefa: Processa tela de filtros para Relatório de Processamento de Peças - Fase2.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward proccessRelatorioProcPecasFase2 ( ActionMapping mapping
                                                         , ActionForm form
                                                         , HttpServletRequest request
                                                         , HttpServletResponse response) throws ControllerException {
        
        ActionMessages          warningMessages = new ActionMessages();
        
        try {
            
            // 1. Obter o formulário atrelado à action corrente.
            DynaActionForm relatorioForm = (DynaActionForm) form;
            
            // 2. Obter os dados do formulário, digitados pelo usuário e 
            //    recebidos pelo formBean desta action.
            Long    empresa = (Long)relatorioForm.get("empresaId");  
            Integer loteId    = (Integer)relatorioForm.get("lote");       
            
            Usuario usuario = (Usuario) UserHelper.getBoundedUser(request.getSession());
            
            // 3. Obter o objeto de negócios responsável pela obtenção
            //    das informações do relatório e fornecer os parâmetros
            //    recebidos do formulário.
            RelatorioBusiness relatorioBusiness = (RelatorioBusiness) springContext.getBean("relatorioBO");
            LoteBusiness  	  loteBusiness  	= (LoteBusiness)  	  springContext.getBean("loteBO");
            
            Lote lote = loteBusiness.get(loteId);
            
            if ( lote != null ) {
            
	            if ( !usuario.getConcessionaria().getEntityId().equals(lote.getConcessionaria().getEntityId())) {
	                    
	            	warningMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("processRelatorioProcPecasFase2.form.lotNotUser") );
	                super.addWarningMessages(request, warningMessages);
	                    
	                return this.prepareRelatorioProcPecasFase2(mapping, form, request, response);
	                    
	            }
            } else {
            	warningMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("processRelatorioProcPecasFase2.form.lotNotFound") );
                super.addWarningMessages(request, warningMessages);
                    
                return this.prepareRelatorioProcPecasFase2(mapping, form, request, response);
            }
            
            List result  		= relatorioBusiness.listProcPecasFase2(empresa, (Integer)lote.getEntityId());
            
            // Precisamos iterar pela coleção realizar as somatórias
            // Variáveis para totalizar         
            double totalPeca    = 0;
            double totalServico = 0;
            double totalRemessa = 0;
            double totalServ3   = 0;
            
            Iterator itRes = result.iterator();
            
            RelatorioProcPecasFase2VO relatorioProcPecasFase2 =  null;
            
            
            Iterator itResSubPeca = null;
            Iterator itResSubServ = null;
            RelatorioProcPecaFase2PecaVO pecaVO = new RelatorioProcPecaFase2PecaVO(); 
            RelatorioProcPecaFase2ServicoVO servicoVO = new RelatorioProcPecaFase2ServicoVO();  
            
            while( itRes.hasNext() ) {
                
                relatorioProcPecasFase2 = (RelatorioProcPecasFase2VO)itRes.next();
                
                if( relatorioProcPecasFase2.getListPecas() != null ) {
                
                    
                    itResSubPeca = relatorioProcPecasFase2.getListPecas().iterator();               
                    
                    while( itResSubPeca.hasNext() ) {
                    
                        pecaVO = (RelatorioProcPecaFase2PecaVO)itResSubPeca.next();
                            
                        if ( pecaVO.getVlTotalPeca() != null )
                            totalPeca    += NumberUtils.round(pecaVO.getVlTotalPeca().doubleValue(),2);
                        
                        if ( pecaVO.getVlRemesPeca() != null )
                            totalRemessa += NumberUtils.round(pecaVO.getVlRemesPeca().doubleValue(),2);
                    
                    }
                }
                
                if( relatorioProcPecasFase2.getListServicos() != null ) {
                    
                    
                    itResSubServ = relatorioProcPecasFase2.getListServicos().iterator();                
                    
                    while( itResSubServ.hasNext() ) {
                    
                        servicoVO = (RelatorioProcPecaFase2ServicoVO)itResSubServ.next();
                            
                        
                        if ( servicoVO.getVlTotalServico() != null )
                            totalServico    += NumberUtils.round(servicoVO.getVlTotalServico().doubleValue(),2);
                    
                    }
                }
                
                if ( relatorioProcPecasFase2.getVlServicoTerc() != null )
                    totalServ3   += NumberUtils.round(relatorioProcPecasFase2.getVlServicoTerc().doubleValue(),2);
                
            }
            
            //System.out.println("Tot Remessa:" + totalRemessa);
            
            Map parameters = new HashMap();
            parameters.put("totalPecaStr"    , NumberUtils.formatNumberCurrency(totalPeca));
            parameters.put("totalServicoStr" , NumberUtils.formatNumberCurrency(totalServico));
            parameters.put("totalRemessaStr" , NumberUtils.formatNumberCurrency(totalRemessa));
            parameters.put("totalServ3Str"   , NumberUtils.formatNumberCurrency(totalServ3));
            parameters.put("subReportPath0"  , JASPER_FOLDER + "Sg_rl_prpc2_new_subreport0.jasper");
            parameters.put("subReportPath1"  , JASPER_FOLDER + "Sg_rl_prpc2_new_subreport1.jasper");
            parameters.put("subReportPath2"  , JASPER_FOLDER + "Sg_rl_prpc2_new_subreport2.jasper");
            
            //log.info("Valores recuperados para o relatório:" + result.size());            
            
            if ( result.size() > 0 ) {
            
                // 4. Cadastrar todas as informações a serem enviadas para o
                //    relatório (parâmetros e lista de resultados).
                Map data = new HashMap();
                data.put(ReportConstants.REPORT_TEMPLATE, JASPER_FOLDER + "Sg_rl_prpc2_new.jasper");
                data.put(ReportConstants.REPORT_CONTENTS, result);
                data.put(ReportConstants.REPORT_PARAMETERS, parameters);
                data.put(ReportConstants.REPORT_DEFAULT_MEDIA, ReportConstants.REPORT_DEFAULT_MEDIA_PDF);
                data.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
                
                request.setAttribute(ReportConstants.REPORT_REQUEST_DATA, data);
                
                //log.info("Dispachando para o relatório:" + result.size());
                
                // 5. Despachar para o forward de relatorio.
                return mapping.findForward("report");
                
            } else {
                
                warningMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("processRelatorioProcPecasFase2.form.notFound") );
                super.addWarningMessages(request, warningMessages);
                
                return this.prepareRelatorioProcPecasFase2(mapping, form, request, response);
                
            }
            
        } catch (BeansException bExp) {
            throw new ControllerException( bExp );
            
        } catch (NumberFormatException nfExp) {
            throw new ControllerException( nfExp );
            
        } catch (BusinessException bExp) {
            throw new ControllerException( bExp );          
            
        } catch ( InvalidSessionException isExp ) {    		
    		throw new ControllerException(isExp);
    		
		}       
    }
    
    /** Tarefa: Processa tela de filtros para Relatório Consulta Histórico Chassi.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward proccessRelatorioHistChassi ( ActionMapping mapping
                                                     , ActionForm form
                                                     , HttpServletRequest request
                                                     , HttpServletResponse response) throws ControllerException {

        try {
            
            // 1. Obter o usuário atrelado a sessão e verificar se é de concessionária
            Usuario usuario = (Usuario) UserHelper.getBoundedUser(request.getSession());
            
            if ( usuario != null ) {
	            if ( !Usuario.NIVEL_ACESSO_CONCESSIONARIA.equalsIgnoreCase(usuario.getNivelAcesso().getCodigo()) ){
	                
	                request.setAttribute("isInterUser", new Boolean(true));
	                
	            } else {
	                
	                request.setAttribute("isInterUser", new Boolean(false));
	                
	            }
            } else {
            	
            	request.setAttribute("isInterUser", new Boolean(true));
            	
            }
            
            // 1. Obter o formulário atrelado à action corrente.
            DynaActionForm relatorioForm = (DynaActionForm) form;
            
            // 2. Obter os dados do formulário, digitados pelo usuário e 
            //    recebidos pelo formBean desta action.
            String chassi  = relatorioForm.getString("chassi");

            // 3. Obter o objeto de negócios responsável pela obtenção
            //    das informações do relatório e fornecer os parâmetros
            //    recebidos do formulário.
            RelatorioBusiness relatorioBusiness = (RelatorioBusiness) springContext.getBean("relatorioBO");
            
            
            // chamar todos os business e organizar em lista.
            List cabecalho  = relatorioBusiness.listHistChassiCabec(chassi.toUpperCase());
            List revisao    = relatorioBusiness.listHistChassiRevisao(chassi.toUpperCase());
            List garantia   = relatorioBusiness.listHistChassiGarantia(chassi.toUpperCase());
            List totais     = relatorioBusiness.listHistChassiTotais(chassi.toUpperCase());
            
            request.setAttribute("now", new Date());
            request.setAttribute("listCabecalho", cabecalho);
            request.setAttribute("listRevisao", revisao);
            request.setAttribute("listGarantia", garantia);
            request.setAttribute("listTotais", totais);
            
            // 4. Despachar para o forward de relatorio.
            return mapping.findForward("_relatorioHistChassiResultForm");
            
        } catch (BeansException bExp) {
            throw new ControllerException( bExp );
            
        } catch (NumberFormatException nfExp) {
            throw new ControllerException( nfExp );
            
        } catch (BusinessException bExp) {
            throw new ControllerException( bExp );  
            
        } catch (InvalidSessionException isExp) {
            throw new ControllerException( isExp ); 
        }       
    }     

    /** Tarefa: Processa tela de filtros para Relatório Consulta de Notas Fiscais.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward proccessRelatorioGarantiaPecas ( ActionMapping mapping
                                                        , ActionForm form
                                                        , HttpServletRequest request
                                                        , HttpServletResponse response) throws ControllerException {

        try {
            
            // 1. Obter o formulário atrelado à action corrente.
            DynaActionForm relatorioForm = (DynaActionForm) form;
            
            LinhaBusiness linhaBusiness = (LinhaBusiness) springContext.getBean("linhaBO");
            EmpresaBusiness empresaBusiness = (EmpresaBusiness) springContext.getBean("empresaBO");
            
            // 2. Obter os dados do formulário, digitados pelo usuário e 
            //    recebidos pelo formBean desta action.
            String  ano          = relatorioForm.getString("ano");
            String  moeda        = relatorioForm.getString("moeda");
            Long    empresa      = (Long)relatorioForm.get("empresaId"); 
            List    empresaList  = empresaBusiness.list();
            Empresa currentEmp   = new Empresa();
            Long    linha        = (Long)relatorioForm.get("linhaId");
            List    linhaList    = linhaBusiness.list();
            Linha   currentLinha = new Linha();
            
            Iterator linhaIt = linhaList.iterator();
            while ( linhaIt.hasNext() ) {
                
                Linha tmp = (Linha) linhaIt.next();
                
                if ( tmp.getEntityId().equals(linha) ) {
                    
                    currentLinha = tmp;
                    break;
                }
            }
            
            Iterator empresaIt = empresaList.iterator();
            while (empresaIt.hasNext()){
                
                Empresa tmp = (Empresa)empresaIt.next();
                
                if (tmp.getEntityId().equals(empresa)){
                    
                    currentEmp = tmp;
                    break;
                }
            }
            
            // 3. Obter o objeto de negócios responsável pela obtenção
            //    das informações do relatório e fornecer os parâmetros
            //    recebidos do formulário.
            RelatorioBusiness relatorioBusiness = (RelatorioBusiness) springContext.getBean("relatorioBO");
            
            List result        = relatorioBusiness.listGarantiaPecas(empresa, linha, ano, moeda);
            
            List garPecas      = new ArrayList();
            List vlAcmMobra    = new ArrayList();
            List subtotal      = new ArrayList();
            List total         = new ArrayList();
            List garVlFatur    = new ArrayList();
            List revVlFatur    = new ArrayList();
            List totVlFatur    = new ArrayList();
            List orcPcsMobra   = new ArrayList();
            List revisoes      = new ArrayList();
            List orcRevisao    = new ArrayList();
            List orcTotal      = new ArrayList();
            List vlFatur       = new ArrayList();
            List qtdes         = new ArrayList();

            //Flag utilizado para adicionar o último registro somente uma vez 
            //na lista de resultados do relatório principal.
            String topicoAnt = "";
            
            RelatorioGarantiaPecasVO     resultGarPecas    = new RelatorioGarantiaPecasVO();
            RelatorioGarPecasSubReportVO resultVlAcmMobra  = new RelatorioGarPecasSubReportVO();
            RelatorioGarPecasSubReportVO resultOrcPcsMobra = new RelatorioGarPecasSubReportVO();
            RelatorioGarPecasSubReportVO resultRevisoes    = new RelatorioGarPecasSubReportVO();
            RelatorioGarPecasSubReportVO resultOrcRevisao  = new RelatorioGarPecasSubReportVO();
            RelatorioGarPecasSubReportVO resultOrcTotal    = new RelatorioGarPecasSubReportVO();
            RelatorioGarPecasSubReportVO resultSubtotal    = new RelatorioGarPecasSubReportVO();
            RelatorioGarPecasSubReportVO resultTotal       = new RelatorioGarPecasSubReportVO();
            RelatorioGarPecasSubReportVO resultVlFatur     = new RelatorioGarPecasSubReportVO();
            RelatorioGarPecasSubReportVO resultQtdes       = new RelatorioGarPecasSubReportVO();
            RelatorioGarPecasSubReportVO resultGarVlFatur  = new RelatorioGarPecasSubReportVO();
            RelatorioGarPecasSubReportVO resultRevVlFatur  = new RelatorioGarPecasSubReportVO();
            RelatorioGarPecasSubReportVO resultTotVlFatur  = new RelatorioGarPecasSubReportVO();            
            
            Iterator resultIt = result.iterator();
            while (resultIt.hasNext()){
                
                //Recupera os dados de Garantia e Peças
                resultGarPecas = (RelatorioGarantiaPecasVO)resultIt.next();
                
                if (topicoAnt.equals("") || resultGarPecas.getTopico().equals(topicoAnt) == false) {
                    garPecas.add(resultGarPecas);
                    topicoAnt = resultGarPecas.getTopico();
                }
            }   
                //Recupera a lista de Valor Acumulado de Mão de Obra
                if (resultGarPecas.getListVlAcmMobra().isEmpty() == false){
                    Iterator vlAcmIt = resultGarPecas.getListVlAcmMobra().iterator();
                    while (vlAcmIt.hasNext()){
                        resultVlAcmMobra = (RelatorioGarPecasSubReportVO)vlAcmIt.next();
                        vlAcmMobra.add(resultVlAcmMobra);
                    }
                }
                
                
                //Recupera a lista de Subtotal
                if (resultGarPecas.getListSubtotal().isEmpty() == false){
                    Iterator subtotalIt = resultGarPecas.getListSubtotal().iterator();
                    while (subtotalIt.hasNext()){
                        resultSubtotal = (RelatorioGarPecasSubReportVO)subtotalIt.next();
                        subtotal.add(resultSubtotal);
                    }
                }
                //Recupera a lista de Totais
                if (resultGarPecas.getListTotal().isEmpty() == false){
                    Iterator totalIt = resultGarPecas.getListTotal().iterator();
                    while (totalIt.hasNext()){
                        resultTotal = (RelatorioGarPecasSubReportVO)totalIt.next();
                        total.add(resultTotal);
                    }
                }
                //Recupera a lista de Garantia/Faturamento
                if (resultGarPecas.getListGarVlFatur().isEmpty() == false){
                    Iterator garVlFaturIt = resultGarPecas.getListGarVlFatur().iterator();
                    while (garVlFaturIt.hasNext()){
                        resultGarVlFatur = (RelatorioGarPecasSubReportVO)garVlFaturIt.next();
                        garVlFatur.add(resultGarVlFatur);
                    }
                }
                //Recupera a lista de Revisões/Faturamento
                if (resultGarPecas.getListRevVlFatur().isEmpty() == false){
                    Iterator revVlFaturIt = resultGarPecas.getListRevVlFatur().iterator();
                    while (revVlFaturIt.hasNext()){
                        resultRevVlFatur = (RelatorioGarPecasSubReportVO)revVlFaturIt.next();
                        revVlFatur.add(resultRevVlFatur);
                    }
                }
                //Recupera a lista de Totais/Faturamento
                if (resultGarPecas.getListTotVlFatur().isEmpty() == false){
                    Iterator totVlFaturIt = resultGarPecas.getListTotVlFatur().iterator();
                    while (totVlFaturIt.hasNext()){
                        resultTotVlFatur = (RelatorioGarPecasSubReportVO)totVlFaturIt.next();
                        totVlFatur.add(resultTotVlFatur);
                    }
                }       
                //Recupera a lista de Orçamento de Peças e MObra
                if (resultGarPecas.getListOrcPcsMobra().isEmpty() == false){
                    Iterator orcPcsMobraIt = resultGarPecas.getListOrcPcsMobra().iterator();
                    while (orcPcsMobraIt.hasNext()){
                        resultOrcPcsMobra = (RelatorioGarPecasSubReportVO)orcPcsMobraIt.next();
                        orcPcsMobra.add(resultOrcPcsMobra);
                    }
                }   
                //Recupera a lista de Detathes da Revisão
                if (resultGarPecas.getListRevisoes().isEmpty() == false){
                    Iterator revisoesIt = resultGarPecas.getListRevisoes().iterator();
                    while (revisoesIt.hasNext()){
                        resultRevisoes = (RelatorioGarPecasSubReportVO)revisoesIt.next();
                        revisoes.add(resultRevisoes);
                    }
                }   
                //Recupera a lista de Orçamento de Revisão
                if (resultGarPecas.getListOrcRevisao().isEmpty() == false){
                    Iterator orcRevisaoIt = resultGarPecas.getListOrcRevisao().iterator();
                    while (orcRevisaoIt.hasNext()){
                        resultOrcRevisao = (RelatorioGarPecasSubReportVO)orcRevisaoIt.next();
                        orcRevisao.add(resultOrcRevisao);
                    }
                }
                //Recupera a lista de Orçamento Total
                if (resultGarPecas.getListOrcTotal().isEmpty() == false){
                    Iterator orcTotalIt = resultGarPecas.getListOrcTotal().iterator();
                    while (orcTotalIt.hasNext()){
                        resultOrcTotal = (RelatorioGarPecasSubReportVO)orcTotalIt.next();
                        orcTotal.add(resultOrcTotal);
                    }
                }       
                //Recupera a lista de Valor do Faturamento
                if (resultGarPecas.getListVlFatur().isEmpty() == false){
                    Iterator vlFaturIt = resultGarPecas.getListVlFatur().iterator();
                    while (vlFaturIt.hasNext()){
                        resultVlFatur = (RelatorioGarPecasSubReportVO)vlFaturIt.next();
                        vlFatur.add(resultVlFatur);
                    }
                }
                //Recupera a lista de Qtdes de SG's, Motos e Faturadas
                if (resultGarPecas.getListQtdes().isEmpty() == false){
                    Iterator qtdesIt = resultGarPecas.getListQtdes().iterator();
                    while (qtdesIt.hasNext()){
                        resultQtdes = (RelatorioGarPecasSubReportVO)qtdesIt.next();
                        qtdes.add(resultQtdes);
                    }
                }   

            // 4. Cadastrar todas as informações a serem enviadas para o
            //    relatório (parâmetros e lista de resultados).
            Map parameters = new HashMap();
            parameters.put("empresa", currentEmp.getNomeEmpresa());
            parameters.put("moeda", moeda);
            parameters.put("linha", currentLinha.getDescricao());
            parameters.put("subReportPath0", JASPER_FOLDER + "Sg_rl_garpec_subreport0.jasper");
            parameters.put("subReportPath1", JASPER_FOLDER + "Sg_rl_garpec_subreport1.jasper");
            parameters.put("subReportPath2", JASPER_FOLDER + "Sg_rl_garpec_subreport2.jasper");
            parameters.put("subReportPath3", JASPER_FOLDER + "Sg_rl_garpec_subreport3.jasper");
            parameters.put("subReportPath4", JASPER_FOLDER + "Sg_rl_garpec_subreport4.jasper");
            parameters.put("subReportPath5", JASPER_FOLDER + "Sg_rl_garpec_subreport5.jasper");
            parameters.put("subReportPath6", JASPER_FOLDER + "Sg_rl_garpec_subreport6.jasper");
            parameters.put("subReportPath7", JASPER_FOLDER + "Sg_rl_garpec_subreport7.jasper");
            parameters.put("subReportPath8", JASPER_FOLDER + "Sg_rl_garpec_subreport8.jasper");
            parameters.put("subReportPath9", JASPER_FOLDER + "Sg_rl_garpec_subreport9.jasper");
            parameters.put("subReportPath10", JASPER_FOLDER + "Sg_rl_garpec_subreport10.jasper");
            parameters.put("subReportPath11", JASPER_FOLDER + "Sg_rl_garpec_subreport11.jasper");
            
            Map subreportFields = new HashMap();
            subreportFields.put(ReportConstants.REPORT_MAIN, garPecas);
            subreportFields.put("listVlAcmMobra", vlAcmMobra); 
            subreportFields.put("listSubtotal", subtotal); 
            subreportFields.put("listGarVlFatur", garVlFatur); 
            subreportFields.put("listRevVlFatur", revVlFatur); 
            subreportFields.put("listTotal", total);
            subreportFields.put("listTotVlFatur", totVlFatur);
            subreportFields.put("listOrcPcsMobra", orcPcsMobra);
            subreportFields.put("listRevisoes", revisoes);
            subreportFields.put("listOrcRevisao", orcRevisao);
            subreportFields.put("listOrcTotal", orcTotal);
            subreportFields.put("listVlFatur", vlFatur);
            subreportFields.put("listQtdes", qtdes);
            
            Map data = new HashMap();
            data.put(ReportConstants.REPORT_TEMPLATE, JASPER_FOLDER + "Sg_rl_garpec.jasper");
            data.put(ReportConstants.REPORT_PARAMETERS, parameters);
            data.put(ReportConstants.REPORT_CONTENTS, subreportFields);
            data.put(ReportConstants.REPORT_DEFAULT_MEDIA, ReportConstants.REPORT_DEFAULT_MEDIA_PDF);
            data.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
            
            request.setAttribute(ReportConstants.REPORT_REQUEST_DATA, data);
            
            // 5. Despachar para o forward de relatorio.
            return mapping.findForward("report");

        } catch (BeansException bExp) {
            throw new ControllerException( bExp );
            
        } catch (NumberFormatException nfExp) {
            throw new ControllerException( nfExp );
            
        } catch (BusinessException bExp) {
            throw new ControllerException( bExp );  
        
        }       
    }    

    /** Tarefa: Processa tela de filtros para Relatório Consulta de Notas Fiscais.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward proccessRelatorioGarantiaPecasModelo ( ActionMapping mapping
                                                              , ActionForm form
                                                              , HttpServletRequest request
                                                              , HttpServletResponse response) throws ControllerException {

        try {
            
            // 1. Obter o formulário atrelado à action corrente.
            DynaActionForm relatorioForm = (DynaActionForm) form;
            
            LinhaBusiness linhaBusiness         = (LinhaBusiness) springContext.getBean("linhaBO");
            ConcessionariaBusiness concBusiness = (ConcessionariaBusiness) springContext.getBean("concessionariaBO");
            
            // 2. Obter os dados do formulário, digitados pelo usuário e 
            //    recebidos pelo formBean desta action.
            String  dataBase        = relatorioForm.getString("dataFim");
            String  moeda           = relatorioForm.getString("moeda");
            String  modelo          = relatorioForm.getString("modeloId");
            Long    concessionaria  = (Long)relatorioForm.get("concessionariaId"); 
            Long    linha           = (Long)relatorioForm.get("linhaId");
            List    concList        = concBusiness.list();
            List    linhaList       = linhaBusiness.list();
            Concessionaria conc     = new Concessionaria(); 
            Linha   currentLinha    = new Linha();
            
            Iterator linhaIt = linhaList.iterator();
            while ( linhaIt.hasNext() ) {
                
                Linha tmp = (Linha) linhaIt.next();
                
                if ( tmp.getEntityId().equals(linha) ) {
                    
                    currentLinha = tmp;
                    break;
                }
            }
            
            Iterator concIt = concList.iterator();
            while ( concIt.hasNext() ) {
                
                Concessionaria tmp = (Concessionaria) concIt.next();
                
                if ( tmp.getEntityId().equals(concessionaria) ) {
                    
                    conc = tmp;
                    break;
                }
            }
            
            // 3. Obter o objeto de negócios responsável pela obtenção
            //    das informações do relatório e fornecer os parâmetros
            //    recebidos do formulário.
            RelatorioBusiness relatorioBusiness = (RelatorioBusiness) springContext.getBean("relatorioBO");
            
            List result = relatorioBusiness.listGarantiaPecasModelo(concessionaria, linha, dataBase, modelo, moeda);
            
            if ( result == null || result.size() == 0 ) {
                
                ActionMessages warningMessages = new ActionMessages();
                
                warningMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("report.error.notfound") );
                super.addWarningMessages(request, warningMessages);
                
                return this.prepareRelatorioGarantiaPecasModelo(mapping, form, request, response);                
                
            }
            
            List garPecas   = new ArrayList();
            List dolar      = new ArrayList();
            List vlAcmMobra = new ArrayList();
            List subtotal   = new ArrayList();
            List revisoes   = new ArrayList();
            List total      = new ArrayList();
            List vlFatur    = new ArrayList();
            List qtdes      = new ArrayList();
            List garVlFatur = new ArrayList();
            List revVlFatur = new ArrayList();
            List totVlFatur = new ArrayList();
            
            RelatorioGarantiaPecasModVO       resultGarPecas     = new RelatorioGarantiaPecasModVO();
            RelatorioGarantiaPecasModSubRepVO resultVlDolar      = new RelatorioGarantiaPecasModSubRepVO();
            RelatorioGarantiaPecasModSubRepVO resultVlAcmMobra   = new RelatorioGarantiaPecasModSubRepVO();
            RelatorioGarantiaPecasModSubRepVO resultSubtotal     = new RelatorioGarantiaPecasModSubRepVO();
            RelatorioGarantiaPecasModSubRepVO resultRevisoes     = new RelatorioGarantiaPecasModSubRepVO();
            RelatorioGarantiaPecasModSubRepVO resultTotal        = new RelatorioGarantiaPecasModSubRepVO();
            RelatorioGarantiaPecasModSubRepVO resultVlFatur      = new RelatorioGarantiaPecasModSubRepVO();
            RelatorioGarantiaPecasModSubRepVO resultQtdes        = new RelatorioGarantiaPecasModSubRepVO();
            RelatorioGarantiaPecasModSubRepVO resultGarVlFatur   = new RelatorioGarantiaPecasModSubRepVO();
            RelatorioGarantiaPecasModSubRepVO resultRevVlFatur   = new RelatorioGarantiaPecasModSubRepVO();
            RelatorioGarantiaPecasModSubRepVO resultTotVlFatur   = new RelatorioGarantiaPecasModSubRepVO();         
            
            String topicoAnt = "";
            
            Iterator resultIt = result.iterator();
            while (resultIt.hasNext()){
                resultGarPecas = (RelatorioGarantiaPecasModVO)resultIt.next();

                //Recupera os dados de Garantia e Peças
                if (topicoAnt.equals("") || (resultGarPecas.getTopico().equals(topicoAnt) == false)){
                    garPecas.add(resultGarPecas);
                    
                    topicoAnt = resultGarPecas.getTopico();
                
                    //Recupera a lista de Valor Acumulado de Mão de Obra
                    if (resultGarPecas.getListVlAcmMobra().isEmpty() == false){
                        Iterator vlAcmIt = resultGarPecas.getListVlAcmMobra().iterator();
                        while (vlAcmIt.hasNext()){
                            resultVlAcmMobra = (RelatorioGarantiaPecasModSubRepVO)vlAcmIt.next();
                            vlAcmMobra.add(resultVlAcmMobra);
                        }
                    }
                
                    //Recupera a lista de Subtotal
                    if (resultGarPecas.getListSubtotal().isEmpty() == false){
                        Iterator subtotalIt = resultGarPecas.getListSubtotal().iterator();
                        while (subtotalIt.hasNext()){
                            resultSubtotal = (RelatorioGarantiaPecasModSubRepVO)subtotalIt.next();
                            subtotal.add(resultSubtotal);
                        }
                    }
                    
                    //Recupera a lista de Totais
                    if (resultGarPecas.getListTotal().isEmpty() == false){
                        Iterator totalIt = resultGarPecas.getListTotal().iterator();
                        while (totalIt.hasNext()){
                            resultTotal = (RelatorioGarantiaPecasModSubRepVO)totalIt.next();
                            total.add(resultTotal);
                        }
                    }
                    
                    //Recupera a lista de Garantia/Faturamento
                    if (resultGarPecas.getListGarVlFatur().isEmpty() == false){
                        Iterator garVlFaturIt = resultGarPecas.getListGarVlFatur().iterator();
                        while (garVlFaturIt.hasNext()){
                            resultGarVlFatur = (RelatorioGarantiaPecasModSubRepVO)garVlFaturIt.next();
                            garVlFatur.add(resultGarVlFatur);
                        }
                    }
                    
                    //Recupera a lista de Revisões/Faturamento
                    if (resultGarPecas.getListRevVlFatur().isEmpty() == false){
                        Iterator revVlFaturIt = resultGarPecas.getListRevVlFatur().iterator();
                        while (revVlFaturIt.hasNext()){
                            resultRevVlFatur = (RelatorioGarantiaPecasModSubRepVO)revVlFaturIt.next();
                            revVlFatur.add(resultRevVlFatur);
                        }
                    }
                    
                    //Recupera a lista de Totais/Faturamento
                    if (resultGarPecas.getListTotVlFatur().isEmpty() == false){
                        Iterator totVlFaturIt = resultGarPecas.getListTotVlFatur().iterator();
                        while (totVlFaturIt.hasNext()){
                            resultTotVlFatur = (RelatorioGarantiaPecasModSubRepVO)totVlFaturIt.next();
                            totVlFatur.add(resultTotVlFatur);
                        }
                    }       
        
                    //Recupera a lista de Detalhes da Revisão
                    if (resultGarPecas.getListRevisoes().isEmpty() == false){
                        Iterator revisoesIt = resultGarPecas.getListRevisoes().iterator();
                        while (revisoesIt.hasNext()){
                            resultRevisoes = (RelatorioGarantiaPecasModSubRepVO)revisoesIt.next();
                            revisoes.add(resultRevisoes);
                        }
                    }   
                    
                    //Recupera a lista de Valor do Faturamento
                    if (resultGarPecas.getListVlFatur().isEmpty() == false){
                        Iterator vlFaturIt = resultGarPecas.getListVlFatur().iterator();
                        while (vlFaturIt.hasNext()){
                            resultVlFatur = (RelatorioGarantiaPecasModSubRepVO)vlFaturIt.next();
                            vlFatur.add(resultVlFatur);
                        }
                    }
                    
                    //Recupera a lista de Qtdes de SG's, Motos e Faturadas
                    if (resultGarPecas.getListQtdes().isEmpty() == false){
                        Iterator qtdesIt = resultGarPecas.getListQtdes().iterator();
                        while (qtdesIt.hasNext()){
                            resultQtdes = (RelatorioGarantiaPecasModSubRepVO)qtdesIt.next();
                            qtdes.add(resultQtdes);
                        }
                    }   
                    
                    //Recupera a lista de valores do dólar
                    if (resultGarPecas.getListVlDolar().isEmpty() == false){
                        Iterator dolarIt = resultGarPecas.getListVlDolar().iterator();
                        while (dolarIt.hasNext()){
                            resultVlDolar = (RelatorioGarantiaPecasModSubRepVO)dolarIt.next();
                            dolar.add(resultVlDolar);
                        }
                    }
                }                   
            }   
            
            // 4. Cadastrar todas as informações a serem enviadas para o
            //    relatório (parâmetros e lista de resultados).
            Map parameters = new HashMap();
            parameters.put("concessionaria", conc.getRazaoSocial());
            parameters.put("moeda", moeda);
            parameters.put("linha", currentLinha.getDescricao());
            parameters.put("subReportPath0", JASPER_FOLDER + "Sg_rl_garpecmod_subreport0.jasper");
            parameters.put("subReportPath1", JASPER_FOLDER + "Sg_rl_garpecmod_subreport1.jasper");
            parameters.put("subReportPath2", JASPER_FOLDER + "Sg_rl_garpecmod_subreport2.jasper");
            parameters.put("subReportPath3", JASPER_FOLDER + "Sg_rl_garpecmod_subreport3.jasper");
            parameters.put("subReportPath4", JASPER_FOLDER + "Sg_rl_garpecmod_subreport4.jasper");
            parameters.put("subReportPath5", JASPER_FOLDER + "Sg_rl_garpecmod_subreport5.jasper");
            parameters.put("subReportPath6", JASPER_FOLDER + "Sg_rl_garpecmod_subreport6.jasper");
            parameters.put("subReportPath7", JASPER_FOLDER + "Sg_rl_garpecmod_subreport7.jasper");
            parameters.put("subReportPath8", JASPER_FOLDER + "Sg_rl_garpecmod_subreport8.jasper");
            parameters.put("subReportPath9", JASPER_FOLDER + "Sg_rl_garpecmod_subreport9.jasper");
            
            // Obter separadamente o mês e o ano. Lembrando que para utilização da classe
            // DateUtils (método addMonth e getDate), a contagem de meses deverá começar
            // do zero, portanto subtraímos um mês na variável "mesBase".
            int mesBase = Integer.parseInt(dataBase.substring(0, dataBase.indexOf("/"))) - 1 ;
            int anoBase = Integer.parseInt(dataBase.substring(dataBase.indexOf("/")+1, dataBase.length()));
            
            parameters.put("date01", DateUtils.addMonth(anoBase, mesBase, -11));
            parameters.put("date02", DateUtils.addMonth(anoBase, mesBase, -10));
            parameters.put("date03", DateUtils.addMonth(anoBase, mesBase, -9));
            parameters.put("date04", DateUtils.addMonth(anoBase, mesBase, -8));
            parameters.put("date05", DateUtils.addMonth(anoBase, mesBase, -7));
            parameters.put("date06", DateUtils.addMonth(anoBase, mesBase, -6));
            parameters.put("date07", DateUtils.addMonth(anoBase, mesBase, -5));
            parameters.put("date08", DateUtils.addMonth(anoBase, mesBase, -4));
            parameters.put("date09", DateUtils.addMonth(anoBase, mesBase, -3));
            parameters.put("date10", DateUtils.addMonth(anoBase, mesBase, -2));
            parameters.put("date11", DateUtils.addMonth(anoBase, mesBase, -1));
            parameters.put("date12", DateUtils.getDate(anoBase, mesBase));
            parameters.put("anoRef", new Integer(anoBase));
        
            Map subreportFields = new HashMap();
            subreportFields.put(ReportConstants.REPORT_MAIN, garPecas);
            subreportFields.put("listVlDolar", dolar);
            subreportFields.put("listVlAcmMobra", vlAcmMobra ); 
            subreportFields.put("listSubtotal", subtotal); 
            subreportFields.put("listRevisoes", revisoes);
            subreportFields.put("listTotal", total);            
            subreportFields.put("listVlFatur", vlFatur);
            subreportFields.put("listQtdes", qtdes);
            subreportFields.put("listGarVlFatur", garVlFatur); 
            subreportFields.put("listRevVlFatur", revVlFatur);          
            subreportFields.put("listTotVlFatur", totVlFatur);          
            
            Map data = new HashMap();
            data.put(ReportConstants.REPORT_TEMPLATE, JASPER_FOLDER + "Sg_rl_garpecmod.jasper");
            data.put(ReportConstants.REPORT_PARAMETERS, parameters);
            data.put(ReportConstants.REPORT_CONTENTS, subreportFields);
            data.put(ReportConstants.REPORT_DEFAULT_MEDIA, ReportConstants.REPORT_DEFAULT_MEDIA_PDF);
            data.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
            
            request.setAttribute(ReportConstants.REPORT_REQUEST_DATA, data);
            
            // 5. Despachar para o forward de relatorio.
            return mapping.findForward("report");

        } catch (BeansException bExp) {
            throw new ControllerException( bExp );
            
        } catch (NumberFormatException nfExp) {
            throw new ControllerException( nfExp );
            
        } catch (BusinessException bExp) {
            throw new ControllerException( bExp );  
        
        }
        
    }    
    
    /** Tarefa: Processa tela de filtros para Relatório Consulta de Notas Fiscais.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward proccessRelatorioImportedParts ( ActionMapping mapping
                                                        , ActionForm form
                                                        , HttpServletRequest request
                                                        , HttpServletResponse response) throws ControllerException {

        try {
            
            // 1. Obter o formulário atrelado à action corrente.
            DynaActionForm relatorioForm = (DynaActionForm) form;

            EmpresaBusiness empresaBusiness = (EmpresaBusiness) springContext.getBean("empresaBO");
            
            // 2. Obter os dados do formulário, digitados pelo usuário e 
            //    recebidos pelo formBean desta action.
            String  dataIni      = relatorioForm.getString("dataInicio");
            String  dataFim      = relatorioForm.getString("dataFim");
            String  fornecedor   = relatorioForm.getString("fornecedor");
            Long    empresa      = (Long)relatorioForm.get("empresaId"); 
            Long    linha        = (Long)relatorioForm.get("linhaId");
            List    empresaList  = empresaBusiness.list();
            Empresa currentEmp   = new Empresa();
            
            Iterator empresaIt = empresaList.iterator();
            while (empresaIt.hasNext()){
                
                Empresa tmp = (Empresa)empresaIt.next();
                
                if (tmp.getEntityId().equals(empresa)){
                    
                    currentEmp = tmp;
                    break;
                }
            }
            
            // 3. Obter o objeto de negócios responsável pela obtenção
            //    das informações do relatório e fornecer os parâmetros
            //    recebidos do formulário.
            RelatorioBusiness relatorioBusiness = (RelatorioBusiness) springContext.getBean("relatorioBO");
            log.info(	"proccessRelatorioImportedParts");
            log.info(	"Empresa:" 	  + empresa		);
            log.info(	"Linha:" 	  + linha		);
            log.info(	"Fornecedor:" + fornecedor	);
            log.info(	"DataIni:" 	  + dataIni		);
            log.info(	"DataFim:"    + dataFim		);
            
            List importedPart = new ArrayList();
            List warranty = new ArrayList();
            List invoice  = new ArrayList();
            List result   = relatorioBusiness.listImportedParts( DateUtils.parseDate(dataIni)
                                                               , DateUtils.parseDate(dataFim)
                                                               , linha
                                                               , empresa
                                                               , fornecedor);
            RelatorioImportedPartsVO         currentResult = null;
            RelatorioImportedPartsWarrantyVO currentWar    = new RelatorioImportedPartsWarrantyVO();
            RelatorioImportedPartsInvoiceVO  currentInv    = new RelatorioImportedPartsInvoiceVO();
            
            Iterator resultIt = result.iterator();
            while (resultIt.hasNext()){
                currentResult = new RelatorioImportedPartsVO();
                currentResult = (RelatorioImportedPartsVO)resultIt.next();
                
                importedPart.add(currentResult);
                
                // Adicionar a warranty na lista
                if (currentResult.getListWarranty().isEmpty() == false ){
                    Iterator warrantyIt = currentResult.getListWarranty().iterator();
                    while (warrantyIt.hasNext()){
                        currentWar = (RelatorioImportedPartsWarrantyVO)warrantyIt.next();
                        warranty.add(currentWar);
                    }
                }
                
                // Adicionar a invoice na lista
                if (currentResult.getListInvoice().isEmpty() == false ){
                    Iterator invoiceIt = currentResult.getListInvoice().iterator();
                    while (invoiceIt.hasNext()){
                        currentInv = (RelatorioImportedPartsInvoiceVO)invoiceIt.next();
                        invoice.add(currentInv);
                    }
                }
            }
            
            // 4. Cadastrar todas as informações a serem enviadas para o
            //    relatório (parâmetros e lista de resultados).
            Map parameters = new HashMap();
            parameters.put("empresa", currentEmp.getNomeEmpresa());
            parameters.put("dataIni", DateUtils.parseDate(dataIni));
            parameters.put("dataFim", DateUtils.parseDate(dataFim));
            parameters.put("subReportPath0", JASPER_FOLDER + "Sg_rl_imppart_subreport0.jasper");
            parameters.put("subReportPath1", JASPER_FOLDER + "Sg_rl_imppart_subreport1.jasper");
            
            Map subreportFields = new HashMap();
            subreportFields.put(ReportConstants.REPORT_MAIN, importedPart);
            subreportFields.put("listWarranty", warranty);
            subreportFields.put("listInvoice" , invoice ); 
            
            Map data = new HashMap();
            data.put(ReportConstants.REPORT_TEMPLATE, JASPER_FOLDER + "Sg_rl_imppart.jasper");
            data.put(ReportConstants.REPORT_PARAMETERS, parameters);
            data.put(ReportConstants.REPORT_CONTENTS, subreportFields);
            data.put(ReportConstants.REPORT_DEFAULT_MEDIA, ReportConstants.REPORT_DEFAULT_MEDIA_PDF);
            data.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
            
            request.setAttribute(ReportConstants.REPORT_REQUEST_DATA, data);
            
            // 5. Despachar para o forward de relatorio.
            return mapping.findForward("report");

        } catch (CotacaoException cExp) {
            
            ActionMessages errorMessages = new ActionMessages();

            errorMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("importedParts.error.cotacao") );
            super.addProblemMessages(request, errorMessages);
                           
            return this.prepareRelatorioImportedParts(mapping, form, request, response);            
            
        } catch (BeansException bExp) {
            throw new ControllerException( bExp );
            
        } catch (NumberFormatException nfExp) {
            throw new ControllerException( nfExp );
            
        } catch (BusinessException bExp) {
            throw new ControllerException( bExp );  
        
        } catch (ParseException pExp) {
            throw new ControllerException( pExp );
            
        }
    }      
    
    /** Tarefa: Processa o relatório de Gráficos Individuais .
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward proccessRelatorioGraficosIndividuais ( ActionMapping mapping
                                                                , ActionForm form
                                                                , HttpServletRequest request
                                                                , HttpServletResponse response) throws ControllerException {
            
        
        try {
            
            //    1. Obter o formulário atrelado à action corrente.
            DynaActionForm relatorioForm = (DynaActionForm) form;
            
            //    2. Obter os dados do formulário, digitados pelo usuário e 
            //    recebidos pelo formBean desta action.
            String  dataInicioAp = relatorioForm.getString("dataInicio");
            String  dataFinalAp  = relatorioForm.getString("dataFim");
            Long    empresa      = (Long)relatorioForm.get("empresaId"); 
            Long    linhaId      = (Long)relatorioForm.get("linhaId");
            
            //    3. Obter o objeto de negócios responsável pela obtenção
            //    das informações do relatório e fornecer os parâmetros
            //    recebidos do formulário.
            RelatorioBusiness relatorioBusiness = (RelatorioBusiness) springContext.getBean("relatorioBO");
            
            List resultsGraf = new ArrayList(); 
            
            EntityImgReportGraficosIndividuaisVO graphVO = new EntityImgReportGraficosIndividuaisVO();
            
            // Esta lista atende aos gráficos 1 e 2
            List valuesFaturamentoByGarantia = relatorioBusiness.listValuesFaturamentoByGarantia(empresa, DateUtils.secureSet(dataInicioAp), DateUtils.secureSet(dataFinalAp), linhaId, null);
            
            /** Gráfico 1
             *  Criação do gráfico Faturamento x Garantia
             *  Gráfico Composto - Faturamento (Barra) x Garantia (Linha)
             */         
            BufferedImage   bImageGraph1 = 
                GenerateChartReport.gerarGraficoFaturamentoGarantiaDualAxis(
                    "Gráfico Faturamento x Garantia (R$)"
                    , ""
                    , "Faturamento"
                    , "Garantia"
                    , valuesFaturamentoByGarantia 
                );
            
            graphVO.setImage(bImageGraph1);
            resultsGraf.add(graphVO);
                        
            //log.info("Imagem Gráfico Faturamento x Garantia Bufferizada:" + bImageGraph1.getWidth());
            
            /** Gráfico 2
             *  Criação do gráfico Garantia Mensal x Índice
             *  Gráfico Composto - Garantia Mensal (Barra) x Índice (Linha)
             */
            BufferedImage   bImageGraph2 = 
                GenerateChartReport.gerarGraficoGarantiaIndiceDualAxis(
                    "Gráfico Garantia Mensal (R$) x Índice (%)"
                    , ""
                    , "Garantia Mensal"
                    , "Índice %"
                    , valuesFaturamentoByGarantia // usamos os mesmos dados do gráfico anterior
                );
            
            graphVO = new EntityImgReportGraficosIndividuaisVO();
            graphVO.setImage(bImageGraph2);
            resultsGraf.add(graphVO);
             
            //log.info("Imagem Gráfico Garantia Mensal x Índice - Bufferizada:" + bImageGraph2.getWidth());
            
            // Esta lista atende aos gráficos 3 e 4
            List resultsGraph3_4 = relatorioBusiness.listFaturamentoByQtdeGarantia(empresa, DateUtils.secureSet(dataInicioAp), DateUtils.secureSet(dataFinalAp), linhaId, null);
            
            /** Gráfico 3
             *  Criação do gráfico Faturamento x Garantia (Qtde)
             *  Gráfico Composto - Faturamento (Barra) x Garantia (Linha)
             */                   
            BufferedImage   bImageGraph3 = 
                GenerateChartReport.gerarGraficoFaturamentoQtdeGarantiaDualAxis(
                    "Gráfico Faturamento x Garantia (Qtde)"
                    , ""
                    , "Faturamento"
                    , "Garantia (Qtde)"
                    , resultsGraph3_4
                );
            graphVO = new EntityImgReportGraficosIndividuaisVO();
            graphVO.setImage(bImageGraph3);
            resultsGraf.add(graphVO);
             
            //log.info("Imagem Gráfico Faturamento x Garantia (Qtde) - Bufferizada:" + bImageGraph3.getWidth());
            
            /** Gráfico 4
             *  Criação do gráfico GARANTIA MENSAL x ÍNDICE DE GARANTIA (QTD)
             *  Gráfico Composto - GARANTIA MENSAL (Barra) x ÍNDICE DE GARANTIA (Linha)
             */                   
            BufferedImage   bImageGraph4 = 
                GenerateChartReport.gerarGraficoGarantiaMensalIndiceQtdeDualAxis(
                    "Gráfico Garantia Mensal x Índice de Garantia (Qtde)"
                    , ""
                    , "Garantia Mensal"
                    , "Índice Garantia (Qtde)"
                    , resultsGraph3_4
                );
            graphVO = new EntityImgReportGraficosIndividuaisVO();
            graphVO.setImage(bImageGraph4);
            resultsGraf.add(graphVO);
              
            //log.info("Imagem GARANTIA MENSAL x ÍNDICE DE GARANTIA (QTD) - Bufferizada:" + bImageGraph4.getWidth());
            
            /** Gráfico 5
             *  Criação do gráfico VALOR ACUMULADO GARANTIA 0 KM x INDICE GARANTIA 0 KM
             *  Gráfico Composto - VALOR ACUMULADO GARANTIA 0 KM (Barra) x ÍNDICE GARANTIA 0 KM (Linha)
             */ 
            BufferedImage   bImageGraph5 = 
                GenerateChartReport.gerarGraficoAcumuladoGarantiaIndiceDualAxis(
                    "Gráfico Valor Acumulado Garantia 0 KM x Índice Garantia 0 KM (%)"
                    , ""
                    , "Acumulado Garantia"
                    , "Índice %"
                    , relatorioBusiness.listAcumuladoGarantiaByIndice(empresa, DateUtils.secureSet(dataInicioAp), DateUtils.secureSet(dataFinalAp), linhaId, null)
                );
            
            graphVO = new EntityImgReportGraficosIndividuaisVO();
            graphVO.setImage(bImageGraph5);
            resultsGraf.add(graphVO);
            
            log.info("Imagem Gráfico VALOR ACUMULADO GARANTIA 0 KM x INDICE GARANTIA 0 KM - Bufferizada:" + bImageGraph5.getWidth());
            
            /** Gráfico 6
             *  Criação da tabela PRINCIPAIS PEÇAS
             *  Método que monta os parâmetros para a tabela
             */
            Map parameters = relatorioBusiness.listValuesTablePecas(empresa, DateUtils.secureSet(dataFinalAp), linhaId, null);
            
            if ( Linha.TIPO_MOTOCICLETA.equals(linhaId) || Linha.TIPO_QUADRICICLO.equals(linhaId) )
            	parameters.put("linha", "DSC");
            else if ( Linha.TIPO_NAUTICA.equals(linhaId) || Linha.TIPO_FORCA.equals(linhaId) )
            	parameters.put("linha", "NSC");
            else
            	parameters.put("linha", "CONSOLIDADO");
            
            Map subreportFields = new HashMap();
            
            log.info("Gráficos:" + resultsGraf.size());
            
            subreportFields.put(ReportConstants.REPORT_MAIN, resultsGraf);
            
            //    1. Cadastrar todas as informações a serem enviadas para o
            //    relatório (parâmetros e lista de resultados).
            Map data = new HashMap();
            data.put(ReportConstants.REPORT_TEMPLATE, JASPER_FOLDER + "Sg_gr_individuais.jasper");
            data.put(ReportConstants.REPORT_PARAMETERS, parameters);
            data.put(ReportConstants.REPORT_CONTENTS, subreportFields);
            data.put(ReportConstants.REPORT_DEFAULT_MEDIA, ReportConstants.REPORT_DEFAULT_MEDIA_PDF);
            data.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
            
            request.setAttribute(ReportConstants.REPORT_REQUEST_DATA, data);
            
        } catch (BusinessException bExp) {
            throw new ControllerException( bExp );  
        
        } catch (ServiceException sExp) {
            throw new ControllerException( sExp );  
        
        }                   
            
        // 2. Despachar para o forward de relatorio.
        return mapping.findForward("report");
    }
    
    /** Tarefa: Processa o relatório de Gráficos Individuais por Linha.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward processRelatorioGraficosIndividuaisLinha ( ActionMapping mapping
                                                                , ActionForm form
                                                                , HttpServletRequest request
                                                                , HttpServletResponse response) throws ControllerException {
            
        
        try {
            
            //    1. Obter o formulário atrelado à action corrente.
            DynaActionForm relatorioForm = (DynaActionForm) form;
            
            //    2. Obter os dados do formulário, digitados pelo usuário e 
            //    recebidos pelo formBean desta action.
            String  dataInicioAp = relatorioForm.getString("dataInicio");
            String  dataFinalAp  = relatorioForm.getString("dataFim");
            Long    empresa      = (Long)relatorioForm.get("empresaId"); 
            Long    linhaId      = (Long)relatorioForm.get("linhaId");
            String  modelo       = relatorioForm.getString("modelo");
            
            //    3. Obter o objeto de negócios responsável pela obtenção
            //    das informações do relatório e fornecer os parâmetros
            //    recebidos do formulário.
            RelatorioBusiness relatorioBusiness = (RelatorioBusiness) springContext.getBean("relatorioBO");
            
            List resultsGraf = new ArrayList();                 
            EntityImgReportGraficosIndividuaisVO graphVO = new EntityImgReportGraficosIndividuaisVO();
            
            
            List valuesFaturamentoByGarantia = relatorioBusiness.listValuesFaturamentoByGarantia(empresa, DateUtils.secureSet(dataInicioAp), DateUtils.secureSet(dataFinalAp), linhaId, modelo);
            
            /** Gráfico 1
             *  Criação do gráfico Faturamento x Garantia
             *  Gráfico Composto - Faturamento (Barra) x Garantia (Linha)
             */         
            BufferedImage   bImageGraph1 = 
                GenerateChartReport.gerarGraficoFaturamentoGarantiaLinhaDualAxis(
                    "Gráfico Faturamento x Garantia (R$) Modelo:" + modelo
                    , ""
                    , "Faturamento"
                    , "Garantia"
                    , valuesFaturamentoByGarantia
                );
            
            graphVO.setImage(bImageGraph1);
            resultsGraf.add(graphVO);
             
            //log.info("Imagem Gráfico Faturamento x Garantia Bufferizada:" + bImageGraph1.getWidth());
            
            /** Gráfico 2
             *  Criação do gráfico Garantia Mensal x Índice
             *  Gráfico Composto - Garantia Mensal (Barra) x Índice (Linha)
             */
            BufferedImage   bImageGraph2 = 
                GenerateChartReport.gerarGraficoGarantiaIndiceDualAxis(
                    "Gráfico Garantia Mensal (R$) x Índice (%)"
                    , ""
                    , "Garantia Mensal"
                    , "Índice %"
                    , valuesFaturamentoByGarantia // usamos os mesmos dados do gráfico anterior
                );
            
            graphVO = new EntityImgReportGraficosIndividuaisVO();
            graphVO.setImage(bImageGraph2);
            resultsGraf.add(graphVO);
            
            //log.info("Imagem Gráfico Garantia Mensal x Índice - Bufferizada:" + bImageGraph2.getWidth());
            
            // Esta lista atende aos gráficos 3 e 4
            List resultsGraph3_4 = relatorioBusiness.listFaturamentoByQtdeGarantia(empresa, DateUtils.secureSet(dataInicioAp), DateUtils.secureSet(dataFinalAp), linhaId, modelo);
            
            /** Gráfico 3
             *  Criação do gráfico Faturamento x Garantia (Qtde)
             *  Gráfico Composto - Faturamento (Barra) x Garantia (Linha)
             */                   
            BufferedImage   bImageGraph3 = 
                GenerateChartReport.gerarGraficoFaturamentoQtdeGarantiaDualAxis(
                    "Gráfico Faturamento x Garantia (Qtde) Modelo:" + modelo
                    , ""
                    , "Faturamento"
                    , "Garantia (Qtde)"
                    , resultsGraph3_4
                );
            graphVO = new EntityImgReportGraficosIndividuaisVO();
            graphVO.setImage(bImageGraph3);
            resultsGraf.add(graphVO);
             
            //log.info("Imagem Gráfico Faturamento x Garantia (Qtde) - Bufferizada:" + bImageGraph3.getWidth());
            
            /** Gráfico 4
             *  Criação do gráfico GARANTIA MENSAL x ÍNDICE DE GARANTIA (QTD)
             *  Gráfico Composto - GARANTIA MENSAL (Barra) x ÍNDICE DE GARANTIA (Linha)
             */                   
            BufferedImage   bImageGraph4 = 
                GenerateChartReport.gerarGraficoGarantiaMensalIndiceQtdeDualAxis(
                    "Gráfico Garantia Mensal x Índice de Garantia (Qtde) Modelo:" + modelo
                    , ""
                    , "Garantia Mensal"
                    , "Índice Garantia (Qtde)"
                    , resultsGraph3_4
                );
            graphVO = new EntityImgReportGraficosIndividuaisVO();
            graphVO.setImage(bImageGraph4);
            resultsGraf.add(graphVO);
              
            //log.info("Imagem GARANTIA MENSAL x ÍNDICE DE GARANTIA (QTD) - Bufferizada:" + bImageGraph4.getWidth());
            
            /** Gráfico 5
             *  Criação do gráfico VALOR ACUMULADO GARANTIA 0 KM x INDICE GARANTIA 0 KM
             *  Gráfico Composto - VALOR ACUMULADO GARANTIA 0 KM (Barra) x ÍNDICE GARANTIA 0 KM (Linha)
             */ 
            BufferedImage   bImageGraph5 = 
                GenerateChartReport.gerarGraficoAcumuladoGarantiaIndiceDualAxis(
                    "Gráfico Valor Acumulado Garantia 0 KM x Índice Garantia 0 KM (%) Modelo:" + modelo
                    , ""
                    , "Acumulado Garantia"
                    , "Índice %"
                    , relatorioBusiness.listAcumuladoGarantiaByIndice(empresa, DateUtils.secureSet(dataInicioAp), DateUtils.secureSet(dataFinalAp), linhaId, modelo)
                );
            
            graphVO = new EntityImgReportGraficosIndividuaisVO();
            graphVO.setImage(bImageGraph5);
            resultsGraf.add(graphVO);
            
            /** Gráfico 6
             *  Criação da tabela PRINCIPAIS PEÇAS
             *  Método que monta os parâmetros para a tabela
             */
            Map parameters = relatorioBusiness.listValuesTablePecas(empresa, DateUtils.secureSet(dataFinalAp),new Long(1), modelo);
             
            parameters.put("p_modelo", modelo);
            
            if ( Linha.TIPO_MOTOCICLETA.equals(linhaId) || Linha.TIPO_QUADRICICLO.equals(linhaId) )
            	parameters.put("linha", "DSC");
            else if ( Linha.TIPO_NAUTICA.equals(linhaId) || Linha.TIPO_FORCA.equals(linhaId) )
            	parameters.put("linha", "NSC");
            else
            	parameters.put("linha", "CONSOLIDADO");
            
            Map subreportFields = new HashMap();
            
            subreportFields.put(ReportConstants.REPORT_MAIN, resultsGraf);
            
            //    1. Cadastrar todas as informações a serem enviadas para o
            //    relatório (parâmetros e lista de resultados).
            Map data = new HashMap();
            data.put(ReportConstants.REPORT_TEMPLATE, JASPER_FOLDER + "Sg_gr_individuais_linha.jasper");
            data.put(ReportConstants.REPORT_PARAMETERS, parameters);
            data.put(ReportConstants.REPORT_CONTENTS, subreportFields);
            data.put(ReportConstants.REPORT_DEFAULT_MEDIA, ReportConstants.REPORT_DEFAULT_MEDIA_PDF);
            data.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
            
            request.setAttribute(ReportConstants.REPORT_REQUEST_DATA, data);
            
        } catch (BusinessException bExp) {
            throw new ControllerException( bExp );  
        
        } catch (ServiceException sExp) {
            throw new ControllerException( sExp );  
        
        }                  
            
        // 2. Despachar para o forward de relatorio.
        return mapping.findForward("report");
    }
    
    /** Tarefa: Processa o relatório Gráfico Service Report.
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward processRelatorioServiceReport ( ActionMapping mapping
                                                                , ActionForm form
                                                                , HttpServletRequest request
                                                                , HttpServletResponse response) throws ControllerException {
        try {
            
            //    1. Obter o formulário atrelado à action corrente.
            DynaActionForm relatorioForm = (DynaActionForm) form;
            
            //    2. Obter os dados do formulário, digitados pelo usuário e 
            //    recebidos pelo formBean desta action.
            String  dataFinalAp  = relatorioForm.getString("dataFim");
            //Long    empresa    = (Long)relatorioForm.get("empresaId"); 
            
            //log.info("Data Apuração:" + DateUtils.applyMask(DateUtils.secureSet(dataFinalAp)));
            
            //    3. Obter o objeto de negócios responsável pela obtenção
            //    das informações do relatório e fornecer os parâmetros
            //    recebidos do formulário.
            RelatorioBusiness relatorioBusiness = (RelatorioBusiness) springContext.getBean("relatorioBO");
            
            // ********************************** GRUPO DOS RELATÓRIOS 1,2 E 3 ****************************************//
            // Gráfico 1
            List listValuesReportGraph1 = relatorioBusiness.listValuesServiceReportParte( null, DateUtils.secureSet(dataFinalAp), RelatorioServiceReportGraphVO.PRI_PART );
            
            List resultsGraph1 = GenerateChartReport.gerarGraficoServiceReport( listValuesReportGraph1
                                                                                , DateUtils.applyMask(DateUtils.secureSet(dataFinalAp), "MMM")
                                                                                , "1. CKD + CBU TOTAL WARRANTY PAYMENT (R$)");
            // Gráfico 2
            List listValuesReportGraph2 = relatorioBusiness.listValuesServiceReportParte( Empresa.EMPRESA_YMA, DateUtils.secureSet(dataFinalAp), RelatorioServiceReportGraphVO.PRI_PART );
            
            List resultsGraph2 = GenerateChartReport.gerarGraficoServiceReport( listValuesReportGraph2
                                                                                , DateUtils.applyMask(DateUtils.secureSet(dataFinalAp), "MMM")
                                                                                , "2. YMDA – CKD WARRANTY PAYMENTS  (R$)");
            // Gráfico 3
            List listValuesReportGraph3 = relatorioBusiness.listValuesServiceReportParte( Empresa.EMPRESA_YMB, DateUtils.secureSet(dataFinalAp), RelatorioServiceReportGraphVO.PRI_PART );
            
            List resultsGraph3 = GenerateChartReport.gerarGraficoServiceReport( listValuesReportGraph3
                                                                                , DateUtils.applyMask(DateUtils.secureSet(dataFinalAp), "MMM")
                                                                                , "3. YMDB -  CBU WARRANTY PAYMENT (R$)");
            
            
            // ********************************* GRUPO DOS RELATÓRIOS 4,5 E 6 *************************************** //
                       
            // Gráfico 4
            List listValuesReportGraph4 = relatorioBusiness.listValuesServiceReportParte( null, DateUtils.secureSet(dataFinalAp), RelatorioServiceReportGraphVO.SEC_PART );
            
            List resultsGraph4 = GenerateChartReport.gerarGraficoServiceReport( listValuesReportGraph4
                                                                                , DateUtils.applyMask(DateUtils.secureSet(dataFinalAp), "MMM")
                                                                                , "4. CKD + CBU TOTAL WARRANTY AND SERVICE MAINTENANCE PAYMENT (R$)");
            
            // Gráfico 5
            List listValuesReportGraph5 = relatorioBusiness.listValuesServiceReportParte( Empresa.EMPRESA_YMB, DateUtils.secureSet(dataFinalAp), RelatorioServiceReportGraphVO.SEC_PART );
            
            List resultsGraph5 = GenerateChartReport.gerarGraficoServiceReport( listValuesReportGraph5
                                                                                , DateUtils.applyMask(DateUtils.secureSet(dataFinalAp), "MMM")
                                                                                , "5. YMDA - CKD WARRANTY AND SERVICE MAINTENANCE PAYMENT (R$)");
            
            // Gráfico 6
            List listValuesReportGraph6 = relatorioBusiness.listValuesServiceReportParte( Empresa.EMPRESA_YMB, DateUtils.secureSet(dataFinalAp), RelatorioServiceReportGraphVO.SEC_PART );
            
            List resultsGraph6 = GenerateChartReport.gerarGraficoServiceReport( listValuesReportGraph6
                                                                                , DateUtils.applyMask(DateUtils.secureSet(dataFinalAp), "MMM")
                                                                                , "6. YMDB - CBU WARRANTY AND SERVICE MAINTENANCE PAYMENT (R$)");
            
            //  ********************************* GRUPO DOS RELATÓRIOS 7,8 E 9 *************************************** //
            
            // Gráfico 7
            List listValuesReportGraph7 = relatorioBusiness.listValuesServiceReportParte( null, DateUtils.secureSet(dataFinalAp), RelatorioServiceReportGraphVO.TER_PART );
            
            List resultsGraph7 = GenerateChartReport.gerarGraficoServiceReport( listValuesReportGraph7
                                                                                , DateUtils.applyMask(DateUtils.secureSet(dataFinalAp), "MMM")
                                                                                , "7. CKD + CBU TOTAL SERVICE MAINTENANCE PAYMENT (R$)");
            
            // Gráfico 8
            List listValuesReportGraph8 = relatorioBusiness.listValuesServiceReportParte( Empresa.EMPRESA_YMB, DateUtils.secureSet(dataFinalAp), RelatorioServiceReportGraphVO.TER_PART );
            
            List resultsGraph8 = GenerateChartReport.gerarGraficoServiceReport( listValuesReportGraph8
                                                                                , DateUtils.applyMask(DateUtils.secureSet(dataFinalAp), "MMM")
                                                                                , "8. CKD - SERVICE MAINTENANCE PAYMENT (R$)");
            
            // Gráfico 9
            List listValuesReportGraph9 = relatorioBusiness.listValuesServiceReportParte( Empresa.EMPRESA_YMB, DateUtils.secureSet(dataFinalAp), RelatorioServiceReportGraphVO.TER_PART );
            
            List resultsGraph9 = GenerateChartReport.gerarGraficoServiceReport( listValuesReportGraph9
                                                                                , DateUtils.applyMask(DateUtils.secureSet(dataFinalAp), "MMM")
                                                                                , "9. CBU - SERVICE MAINTENANCE PAYMENT (R$)");
            
            Map parameters = new HashMap();
            parameters.put("subReportPath1" , JASPER_FOLDER + "SG_gr_serviceReport_Sub01.jasper");
            parameters.put("subReportPath1b", JASPER_FOLDER + "Sg_gr_serviceReport_Sub01b.jasper");
            
            Map subreportFields = new HashMap();
            
            // Colocamos alguma informação no relatório principal - Teóricamente desnecessário
            subreportFields.put(ReportConstants.REPORT_MAIN, resultsGraph1);
            
            subreportFields.put("resultsGraph1"         , resultsGraph1);
            subreportFields.put("listValuesReportGraph1", listValuesReportGraph1);
            subreportFields.put("resultsGraph2"         , resultsGraph2);
            subreportFields.put("listValuesReportGraph2", listValuesReportGraph2);
            subreportFields.put("resultsGraph3"         , resultsGraph3);
            subreportFields.put("listValuesReportGraph3", listValuesReportGraph3);
            subreportFields.put("resultsGraph4"         , resultsGraph4);
            subreportFields.put("listValuesReportGraph4", listValuesReportGraph4);
            subreportFields.put("resultsGraph5"         , resultsGraph5);
            subreportFields.put("listValuesReportGraph5", listValuesReportGraph5);
            subreportFields.put("resultsGraph6"         , resultsGraph6);
            subreportFields.put("listValuesReportGraph6", listValuesReportGraph6);
            subreportFields.put("resultsGraph7"         , resultsGraph7);
            subreportFields.put("listValuesReportGraph7", listValuesReportGraph7);
            subreportFields.put("resultsGraph8"         , resultsGraph8);
            subreportFields.put("listValuesReportGraph8", listValuesReportGraph8);
            subreportFields.put("resultsGraph9"         , resultsGraph9);
            subreportFields.put("listValuesReportGraph9", listValuesReportGraph9);
            
            //    4. Cadastrar todas as informações a serem enviadas para o
            //    relatório (parâmetros e lista de resultados).
            Map data = new HashMap();
            data.put(ReportConstants.REPORT_TEMPLATE     , JASPER_FOLDER + "Sg_gr_serviceReport.jasper");
            data.put(ReportConstants.REPORT_PARAMETERS   , parameters);
            data.put(ReportConstants.REPORT_CONTENTS     , subreportFields);
            data.put(ReportConstants.REPORT_DEFAULT_MEDIA, ReportConstants.REPORT_DEFAULT_MEDIA_PDF);
            data.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
            
            request.setAttribute(ReportConstants.REPORT_REQUEST_DATA, data);
            
        } catch (BusinessException bExp) {
            throw new ControllerException( bExp );  
        
        } catch (ServiceException sExp) {
            throw new ControllerException( sExp );  
        
        }                   
            
        // 2. Despachar para o forward de relatorio.
        return mapping.findForward("report");
    }
    
    /** Tarefa: Processa o relatório Gráfico de Garantia Paga por Modelo de Produto .
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward processRelatorioGraficoGarantiaPaga ( ActionMapping mapping
                                                                , ActionForm form
                                                                , HttpServletRequest request
                                                                , HttpServletResponse response) throws ControllerException {
            
        
        try {
            
            //    1. Obter o formulário atrelado à action corrente.
            DynaActionForm relatorioForm = (DynaActionForm) form;
            
            //    2. Obter os dados do formulário, digitados pelo usuário e 
            //    recebidos pelo formBean desta action.
            String  dataInicioAp = relatorioForm.getString("dataInicio");
            String  dataFinalAp  = relatorioForm.getString("dataFim");
            Long    empresa      = relatorioForm.get("empresaId") != null ? (Long)relatorioForm.get("empresaId"): null; 
            Long    linhaId      = (Long)relatorioForm.get("linhaId");
            
            //    3. Obter o objeto de negócios responsável pela obtenção
            //    das informações do relatório e fornecer os parâmetros
            //    recebidos do formulário.
            RelatorioBusiness relatorioBusiness = (RelatorioBusiness) springContext.getBean("relatorioBO");
            
            List resultsGraf   = relatorioBusiness.listGarantiaPagaByModelo ( empresa, "", DateUtils.secureSet(dataInicioAp), DateUtils.secureSet(dataFinalAp), linhaId); 
                                              
            Map subreportFields = new HashMap();
            
            subreportFields.put(ReportConstants.REPORT_MAIN, resultsGraf);
            
            //    1. Cadastrar todas as informações a serem enviadas para o
            //    relatório (parâmetros e lista de resultados).
            Map data = new HashMap();
            data.put(ReportConstants.REPORT_TEMPLATE, JASPER_FOLDER + "Sg_gr_garantia_paga.jasper");
            data.put(ReportConstants.REPORT_CONTENTS, subreportFields);
            data.put(ReportConstants.REPORT_DEFAULT_MEDIA, ReportConstants.REPORT_DEFAULT_MEDIA_PDF);
            data.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
            
            request.setAttribute(ReportConstants.REPORT_REQUEST_DATA, data);
            
        } catch (BusinessException bExp) {
            throw new ControllerException( bExp );  
        
        }                
            
        // 2. Despachar para o forward de relatorio.
        return mapping.findForward("report");
    }
    
    /** Tarefa: Processa o relatório Gráfico de Garantia Mensal Percentual. 
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward processRelatorioGraficoGarantiaMensalPercentual ( ActionMapping mapping
                                                                , ActionForm form
                                                                , HttpServletRequest request
                                                                , HttpServletResponse response) throws ControllerException {
            
        
        try {
            
            //    1. Obter o formulário atrelado à action corrente.
            DynaActionForm relatorioForm = (DynaActionForm) form;
            
            //    2. Obter os dados do formulário, digitados pelo usuário e 
            //    recebidos pelo formBean desta action.
            String  dataInicioAp = relatorioForm.getString("dataInicio");
            String  dataFinalAp  = relatorioForm.getString("dataFim");
            Long    orgId        = relatorioForm.get("empresaId") != null ? (Long)relatorioForm.get("empresaId"): null; 
            Long    linhaId      = (Long)relatorioForm.get("linhaId");
            
            //    3. Obter o objeto de negócios responsável pela obtenção
            //    das informações do relatório e fornecer os parâmetros
            //    recebidos do formulário.
            RelatorioBusiness relatorioBusiness = (RelatorioBusiness) springContext.getBean("relatorioBO");
            
            List resultsGraf   = relatorioBusiness.listValuesGraphGarantiaMensalPercentual(orgId, "", DateUtils.secureSet(dataInicioAp), DateUtils.secureSet(dataFinalAp), linhaId); 
             
            Map parameters = new HashMap();
            parameters.put("title", "% GARANTIA MENSAL YMDA");
            
            Map subreportFields = new HashMap();
            
            subreportFields.put(ReportConstants.REPORT_MAIN, resultsGraf);
            
            //    1. Cadastrar todas as informações a serem enviadas para o
            //    relatório (parâmetros e lista de resultados).
            Map data = new HashMap();
            data.put(ReportConstants.REPORT_TEMPLATE, JASPER_FOLDER + "Sg_gr_garantia_mensal_percentual.jasper");
            data.put(ReportConstants.REPORT_PARAMETERS   , parameters);
            data.put(ReportConstants.REPORT_CONTENTS, subreportFields);
            data.put(ReportConstants.REPORT_DEFAULT_MEDIA, ReportConstants.REPORT_DEFAULT_MEDIA_PDF);
            data.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
            
            request.setAttribute(ReportConstants.REPORT_REQUEST_DATA, data);
            
        } catch (BusinessException bExp) {
            throw new ControllerException( bExp );  
        
        }                
            
        // 2. Despachar para o forward de relatorio.
        return mapping.findForward("report");
    }
    
    /** Tarefa: Processa o relatório Gerenciamento Mês. 
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
     *  @return Um objeto <code>ActionForward</code> determinando para onde o
     *      <i>framework</i> deverá despachar a requisição.
     * 
     */ 
    public ActionForward processRelatorioGerenciamentoMes ( ActionMapping mapping
                                                                , ActionForm form
                                                                , HttpServletRequest request
                                                                , HttpServletResponse response) throws ControllerException {
            
        
        try {
            
            //    1. Obter o formulário atrelado à action corrente.
            DynaActionForm relatorioForm = (DynaActionForm) form;
            
            //    2. Obter os dados do formulário, digitados pelo usuário e 
            //    recebidos pelo formBean desta action.
            String  dataInicioApStr = relatorioForm.getString("dataInicio");
            String  dataFinalApStr  = relatorioForm.getString("dataFim"); 
            Long    linhaId         = (Long)relatorioForm.get("linhaId");
            boolean isInnerUser     = "true".equalsIgnoreCase(request.getParameter("isInnerUser")) ? true : false ; 
            int     totalRevEntrega = 0;
            int     totalRev2       = 0;
            double  totalVlRev2     = 0;
            int     totalRev3       = 0;
            double  totalVlRev3     = 0;
            int     totalRevRec     = 0;
            double  totalVlRevRec   = 0;
            int     totalSGEnv      = 0;
            double  totalVlSGEnv    = 0;            
            int     totalSGRec      = 0;
            double  totalVlSGRec    = 0;
            double  aproveitamentoRev   = 0;
            double  aproveitamentoSG    = 0;
            double  mediaTempoAnalise   = 0;
            double  mediaTempoConc      = 0;
            double  mediaDiasPagto      = 0;           
            double  aproveitamentoLotes = 0;
            
            Date dataInicioAp = DateUtils.parseDate(dataInicioApStr);
            Date dataFinalAp  = DateUtils.parseDate(dataFinalApStr);
            
            //    3. Obter o objeto de negócios responsável pela obtenção
            //    das informações do relatório e fornecer os parâmetros
            //    recebidos do formulário.
            RelatorioBusiness relatorioBusiness = (RelatorioBusiness) springContext.getBean("relatorioBO");
            LinhaBusiness     linhaBusiness     = (LinhaBusiness) springContext.getBean("linhaBO");
            ConcessionariaBusiness concessionariaBusiness = (ConcessionariaBusiness) springContext.getBean("concessionariaBO");
            Usuario 		  usuario 			= (Usuario)UserHelper.getBoundedUser(request.getSession());
            Linha 			  linha   			= linhaBusiness.get(linhaId);
            Concessionaria    concessionaria    = null;
            Long    		  concessionariaId  = null;
            
            if ( isInnerUser ) {
            	
            	concessionariaId = (Long)relatorioForm.get("concessionariaId");
            	
            	log.info("Concesionaria ID:" + concessionariaId);
            	concessionaria   = concessionariaBusiness.get(concessionariaId);
            	
            	log.info("Razão:" + concessionaria.getRazaoSocial());
            } else {
            	
            	concessionaria = usuario.getConcessionaria();            	
            	
            }
            
            int qtdLotesEnviados    = relatorioBusiness.getQtLotesLiberadosGerenciamentoMes(dataInicioAp, dataFinalAp, linhaId, (Long)concessionaria.getEntityId());
            int qtdLotesDevolvidos  = relatorioBusiness.getQtLotesDevolvidosGerenciamentoMes(dataInicioAp, dataFinalAp, linhaId, (Long)concessionaria.getEntityId());
            int qtdDiasAbertuFech   = relatorioBusiness.getMediaAberturaFechamentoLote(dataInicioAp, dataFinalAp, linhaId, (Long)concessionaria.getEntityId());
            
            List results  = relatorioBusiness.listValuesGerenciamentoMes(dataInicioAp, dataFinalAp, linhaId, (Long)concessionaria.getEntityId()); 
             
            Iterator it = results.iterator();
            while ( it.hasNext() ) {
            	
            	RelatorioGerenciamentoMesVO relatorioGerenciamentoMesVO = (RelatorioGerenciamentoMesVO) it.next();
            	totalRevEntrega += relatorioGerenciamentoMesVO.getQtR1().intValue();
            	totalRev2       += relatorioGerenciamentoMesVO.getQtR2().intValue();
            	totalVlRev2     += relatorioGerenciamentoMesVO.getVlR2().doubleValue();
            	totalRev3		+= relatorioGerenciamentoMesVO.getQtR3().intValue();
            	totalVlRev3		+= relatorioGerenciamentoMesVO.getVlR3().doubleValue();
            	totalRevRec     += relatorioGerenciamentoMesVO.getQtRevRec().intValue();
            	totalVlRevRec	+= relatorioGerenciamentoMesVO.getVlRevRec().doubleValue();
            	totalSGEnv		+= relatorioGerenciamentoMesVO.getQtSG().intValue();
            	totalVlSGEnv	+= relatorioGerenciamentoMesVO.getVlSG().doubleValue();
            	totalSGRec		+= relatorioGerenciamentoMesVO.getQtSGRec().intValue();
            	totalVlSGRec	+= relatorioGerenciamentoMesVO.getVlSGRec().doubleValue();            	
            	
            }
            
            //log.info("totalRev2:" + totalRev2 + " - totalRev3:" + totalRev3 + " - totalVlRevRec" + totalVlRevRec);
            //log.info("totalSGRec:" + totalSGRec + " - totalSGEnv:" + totalSGEnv);
            
            if ( totalVlRevRec > 0  )
            	aproveitamentoRev = NumberUtils.round((totalRevRec / (totalRev2 + totalRev3 + totalRevRec)) * 100,2);
            else
            	aproveitamentoRev = 100;
            
            if ( totalSGRec > 0 )
            	aproveitamentoSG  = NumberUtils.round((totalSGRec /  (totalSGEnv + totalSGRec)) * 100, 2);
            else 
            	aproveitamentoSG = 100;
            
            if ( qtdLotesDevolvidos > 0 )
            	aproveitamentoLotes = NumberUtils.round((qtdLotesDevolvidos / qtdLotesEnviados),2);
            else
            	aproveitamentoLotes = 100;
            
            Map parameters = new HashMap();
            parameters.put("title"			, "GERENCIAMENTO DO MÊS");
            parameters.put("empresa"		, "YAMAHA - BRASIL");
            
            if ( linha != null )
            	parameters.put("linha"			, linha.getDescricao());
            else
            	parameters.put("linha"			, "CONSOLIDADO");
            parameters.put("razaoSocialConc", concessionaria.getRazaoSocial());
            parameters.put("enderecoConc"	, concessionaria.getEndereco());
            parameters.put("cepConc"		, concessionaria.getCep().toString());
            parameters.put("cidadeConc"		, concessionaria.getCidade());
            parameters.put("estadoConc"		, concessionaria.getEstado().getEstado());
            parameters.put("cnpjConc"		, concessionaria.getCnpj().toString());
            parameters.put("dataInicioAp"	, dataInicioApStr);
            parameters.put("dataFinalAp"	, dataFinalApStr);
            parameters.put("qtLoteEnv"		, NumberUtils.formatNumberMil(qtdLotesEnviados));           
            parameters.put("qtR1Total"		, NumberUtils.formatNumberMil(totalRevEntrega));
            parameters.put("qtR2Total"		, NumberUtils.formatNumberMil(totalRev2));
            parameters.put("vlR2Total"		, NumberUtils.formatNumberCurrencyMil(totalVlRev2));
            parameters.put("qtR3Total"		, NumberUtils.formatNumberMil(totalRev3));
            parameters.put("vlR3Total"		, NumberUtils.formatNumberCurrencyMil(totalVlRev3));
            parameters.put("qtSGEnvTotal"	, NumberUtils.formatNumberMil(totalSGEnv));
            parameters.put("vlSGEnvTotal"	, NumberUtils.formatNumberCurrencyMil(totalVlSGEnv));
            parameters.put("qtSGRecTotal"	, NumberUtils.formatNumberMil(totalSGRec));
            parameters.put("vlSGRecTotal"	, NumberUtils.formatNumberCurrencyMil(totalVlSGRec));
            parameters.put("qtRevRecTotal"	, NumberUtils.formatNumberMil(totalRevRec));
            parameters.put("vlRevRecTotal"	, NumberUtils.formatNumberCurrencyMil(totalVlRevRec));
            parameters.put("aprovRev"		, NumberUtils.formatNumberCurrencyMil(aproveitamentoRev) + " %");
            parameters.put("aprovSG"		, NumberUtils.formatNumberCurrencyMil(aproveitamentoSG) + " %");
            parameters.put("tempoAnalise"	, NumberUtils.formatNumberCurrencyMil(mediaTempoAnalise));
            parameters.put("tempoConcessionaria", NumberUtils.formatNumberCurrencyMil(mediaTempoConc));
            parameters.put("diasPagto"		, NumberUtils.formatNumberCurrencyMil(mediaDiasPagto));
            parameters.put("diasAberturaPagto", NumberUtils.formatNumberMil(qtdDiasAbertuFech));
            parameters.put("qtLotesDevolvidos", NumberUtils.formatNumberCurrencyMil(qtdLotesDevolvidos));
            parameters.put("aprovLotes"		, NumberUtils.formatNumberCurrencyMil(aproveitamentoLotes) + " %");
                        
            Map subreportFields = new HashMap();
            
            subreportFields.put(ReportConstants.REPORT_MAIN, results);
            
            //    1. Cadastrar todas as informações a serem enviadas para o
            //    relatório (parâmetros e lista de resultados).
            Map data = new HashMap();
            data.put(ReportConstants.REPORT_TEMPLATE	 , JASPER_FOLDER + "Sg_rl_gerenciamento_mes.jasper");
            data.put(ReportConstants.REPORT_PARAMETERS   , parameters);
            data.put(ReportConstants.REPORT_CONTENTS	 , subreportFields);
            data.put(ReportConstants.REPORT_DEFAULT_MEDIA, ReportConstants.REPORT_DEFAULT_MEDIA_PDF);
            data.put(JRParameter.REPORT_LOCALE			 , new Locale("pt", "BR"));
            
            request.setAttribute(ReportConstants.REPORT_REQUEST_DATA, data);
            
        } catch (BusinessException bExp) {
        	
            throw new ControllerException( bExp );  
        
        } catch ( InvalidSessionException isExp ) {
    		
    		throw new ControllerException(isExp);
    		
		} catch (ParseException pex) {
			
			throw new ControllerException("Data inválida!");
		}                
            
        // 2. Despachar para o forward de relatorio.
        return mapping.findForward("report");
    }
}
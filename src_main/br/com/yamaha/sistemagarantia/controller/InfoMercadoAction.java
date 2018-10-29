/* Resource Informática
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......InfoMercadoAction.java
 *   .: Criação.....01 de julho, 11:05
 *   .: Autor.......Edson Luiz Sumensari
 *                  Thiago Uriel M. Garcia
 *   .: Descrição...Controla requisições sobre InfoMercado.
 */
package br.com.yamaha.sistemagarantia.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.DynaValidatorActionForm;
import org.springframework.beans.BeansException;

import br.com.resource.infra.controller.InfraDispatchAction;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.BusinessRuleException;
import br.com.resource.infra.exception.ControllerException;
import br.com.resource.infra.security.exception.InvalidSessionException;
import br.com.resource.infra.security.helper.UserHelper;
import br.com.resource.infra.utils.DateUtils;
import br.com.yamaha.sistemagarantia.business.InfoMercadoBusiness;
import br.com.yamaha.sistemagarantia.business.ItemBusiness;
import br.com.yamaha.sistemagarantia.controller.helper.ControllerHelper;
import br.com.yamaha.sistemagarantia.controller.helper.YmSessionHelper;
import br.com.yamaha.sistemagarantia.model.InfoMercFotos;
import br.com.yamaha.sistemagarantia.model.InfoMercado;
import br.com.yamaha.sistemagarantia.model.Item;
import br.com.yamaha.sistemagarantia.model.Usuario;
import br.com.yamaha.sistemagarantia.view.InfoMercadoVO;

/** Classe controla as tarefas relacionadas ao <b>InfoMercado</b>.
 * 
 *  @author Edson Luiz Sumensari
 *  @author Thiago Uriel M. Garcia
 */
public class InfoMercadoAction extends InfraDispatchAction {

	private static final long serialVersionUID = 1L;

	/** Tarefa: Apresenta o formulário de InfoMercado em branco
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
	public ActionForward showBlankForm( ActionMapping mapping
			                          , ActionForm form
                                      , HttpServletRequest request
			                          , HttpServletResponse response) throws ControllerException {

		try {

			// Inicialização do formulário.
			InfoMercadoBusiness     infoMercadoBusiness = (InfoMercadoBusiness) springContext.getBean("infoMercadoBO");
			DynaValidatorActionForm infoMercadoForm     = (DynaValidatorActionForm) form;
			
			infoMercadoForm.initialize(mapping);

			// Cadastro de valores iniciais no formulário. 
			infoMercadoForm.set("data", DateUtils.applyMask( new Date() ) );
			infoMercadoForm.set("listTipoGasolina", infoMercadoBusiness.listTipoGasolina());
			infoMercadoForm.set("listTipoProduto" , infoMercadoBusiness.listTipoProdutoInfo() );
			infoMercadoForm.set("listTipoUso"     , infoMercadoBusiness.listTipoUso()); 
			infoMercadoForm.set( "listItems"      , new ArrayList() );
			infoMercadoForm.set( "listFotos"      , new ArrayList() );

			// Indica q o formulário está aberto para edição.
			request.setAttribute( "lockForm", new Boolean(false) );
			
			// Exibe a tela.
			return mapping.findForward("_form");

		} catch (BusinessException bExp) {

			throw new ControllerException(bExp);

		}

	}   

	/** Tarefa: Apresenta o formulário de InfoMercado preenchido.
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
	public ActionForward showFilledForm( ActionMapping mapping
			                           , ActionForm form
                                       , HttpServletRequest request
			                           , HttpServletResponse response) throws ControllerException {

		// Só iniciamos o processo se houver um identificador fornecido.		
		Integer entityId = request.getParameter("entityId") != null 
						   ?  new Integer( request.getParameter("entityId") ):
						   (Integer) request.getAttribute("entityId");
						   
		if ( entityId != null ) {
			
			try {

				// Declaração de variáveis utilizadas pelo controller.
				//Integer                 entityId            = new Integer( request.getParameter("entityId") );
				InfoMercadoBusiness     infoMercadoBusiness = (InfoMercadoBusiness) springContext.getBean("infoMercadoBO");
				ItemBusiness            itemBusiness        = (ItemBusiness) springContext.getBean("itemBO");
				DynaValidatorActionForm infoMercadoForm     = (DynaValidatorActionForm) form;
				InfoMercado             infoMercado         = infoMercadoBusiness.get(entityId);
				Item					item				= itemBusiness.getByOrg(infoMercado.getItemId(), new Long(91) );
				if ( item == null ) {
					
					item = itemBusiness.getByOrg(infoMercado.getItemId(), new Long(89) );
					
				}				
				
				//System.out.println("Tem Garantia:" + (infoMercado.getGarantia() != null));
				
				infoMercadoForm.initialize(mapping);

				// Popular formulário com entidade recebida.
				infoMercadoForm.set( "entityId"         , infoMercado.getEntityId() );
				infoMercadoForm.set( "locked"           , "true" );
				infoMercadoForm.set( "isEdit"           , "true" );
				infoMercadoForm.set( "hasGarantia"      , String.valueOf(infoMercado.getHasGarantia()) );
				infoMercadoForm.set( "garantiaId"      	, infoMercado.getHasGarantia() ? (Integer)infoMercado.getGarantia().getEntityId():null );
				infoMercadoForm.set( "preenchidoPor"    , infoMercado.getPreenchidoPor() );
				infoMercadoForm.set( "data"             , DateUtils.applyMask( infoMercado.getDataInfo() ) );
				infoMercadoForm.set( "chassi"           , infoMercado.getChassi() );
				infoMercadoForm.set( "totalHrs"         , infoMercado.getTotalHrs() );
				infoMercadoForm.set( "dataVenda"        , DateUtils.applyMask( infoMercado.getDataVenda() ) );
				infoMercadoForm.set( "dataProblema"     , DateUtils.applyMask( infoMercado.getDataProblema() ) );
				infoMercadoForm.set( "tipoGasolinaId"   , infoMercado.getTipoGasolinaId() );
				infoMercadoForm.set( "localUsoDoce"     , infoMercado.getLocalUsoDoce() );
				infoMercadoForm.set( "localUsoSalg"     , infoMercado.getLocalUsoSalg() );
				infoMercadoForm.set( "materHelice"      , infoMercado.getMaterHelice() );
				infoMercadoForm.set( "tipoUso"          , infoMercado.getTipoUso() );
				infoMercadoForm.set( "cidadeUso"        , infoMercado.getCidadeUso() );
				infoMercadoForm.set( "marcaCasco"       , infoMercado.getMarcaCasco() );
				infoMercadoForm.set( "modeloCasco"      , infoMercado.getModeloCasco() );
				infoMercadoForm.set( "marcaHelice"      , infoMercado.getMarcaHelice() );
				infoMercadoForm.set( "passoHelice"      , infoMercado.getPassoHelice() );
				infoMercadoForm.set( "rotacaoMaxima"    , infoMercado.getRotacaoMaxima() );
				infoMercadoForm.set( "itemId"           , infoMercado.getItemId() );
				infoMercadoForm.set( "itemCode"         , item.getItemCode() );
				infoMercadoForm.set( "descItem"         , item.getDescricao() );
				infoMercadoForm.set( "statusImId"       , infoMercado.getStatusInfoMercado().getEntityId() );
				infoMercadoForm.set( "concessionariaId" , infoMercado.getConcessionaria().getEntityId() );
				infoMercadoForm.set( "listTipoGasolina" , infoMercadoBusiness.listTipoGasolina() );
				infoMercadoForm.set( "listTipoProduto"  , infoMercadoBusiness.listTipoProdutoInfo() );
				infoMercadoForm.set( "listTipoUso"      , infoMercadoBusiness.listTipoUso() );
				infoMercadoForm.set( "listFotos"        , infoMercado.getListFotos() );
				//infoMercadoForm.set( "listItems"        , itemBusiness.listByChassi( infoMercado.getChassi().toUpperCase(), new Long(2) ) );
				infoMercadoForm.set( "condicaoProblema" , infoMercadoBusiness.getHistory( InfoMercadoBusiness.HIST_SINTOMA, infoMercado.getEntityId() ) );
				infoMercadoForm.set( "causaProblema"    , infoMercadoBusiness.getHistory( InfoMercadoBusiness.HIST_CAUSA, infoMercado.getEntityId() ) );
				infoMercadoForm.set( "diagnostico"      , infoMercadoBusiness.getHistory( InfoMercadoBusiness.HIST_DIAGNOSTICO, infoMercado.getEntityId() ) );
				infoMercadoForm.set( "solucao"          , infoMercadoBusiness.getHistory( InfoMercadoBusiness.HIST_SOLUCAO, infoMercado.getEntityId() ) );
				
				// Configurar o status do formulário (editável ou não). Só será
				// editável no primeiro status da informação de mercado.
				Long statusInfoMercado = (Long)infoMercado.getStatusInfoMercado().getEntityId();
				
				log.info("Status Informações de Mercado:" + statusInfoMercado);
				
				if ( statusInfoMercado.equals( new Long(1) ) || statusInfoMercado.equals( new Long(3) ) )
					request.setAttribute( "lockForm", new Boolean(false) );				
				else 
					request.setAttribute( "lockForm", new Boolean(true) );
				
				request.setAttribute( "statusImId", statusInfoMercado );
					
				// E por fim, exibí-lo ao usuário.
				return mapping.findForward("_form");

			} catch (BusinessException bExp) {

				throw new ControllerException(bExp);

			}
			
		} else {
			
			throw new ControllerException("Nenhum identificador de entidade encontrado.");
			
		}

	} 	
	
	/** Tarefa: Grava as informações recebidas do formulário de InfoMercado.
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
	public ActionForward save( ActionMapping mapping
			                 , ActionForm form
			                 , HttpServletRequest request
			                 , HttpServletResponse response) throws ControllerException {

		try {
			
			ActionMessages messagesWarning    = new ActionMessages();
			
			DynaValidatorActionForm infoMercadoForm     = (DynaValidatorActionForm) form;		
			InfoMercadoBusiness     infoMercadoBusiness = (InfoMercadoBusiness) springContext.getBean("infoMercadoBO");
			Usuario                 usuario             = (Usuario) UserHelper.getBoundedUser(request.getSession() );		
			
			InfoMercado infoMercado = null;
			
			boolean  isEdit = "true".equals(infoMercadoForm.getString("isEdit")) ? true : false;
			
			Integer entityId = infoMercadoForm.get("entityId") != null ? (Integer)infoMercadoForm.get("entityId") : new Integer(0);						
			
			infoMercadoForm.set("listResults", infoMercadoBusiness.listByConcessionaria( usuario.getConcessionaria()));
			
			if ( !entityId.equals(new Integer(0)) ) {					
				
				infoMercado = infoMercadoBusiness.get(entityId);
				infoMercado.setOnlyInfo(!infoMercado.getHasGarantia()); // Se tem Garantia associada,seta falso.
				
			} else { 
				
				infoMercado = new InfoMercado();
				infoMercado.setOnlyInfo(true);// Sempre gravar como apenas Info Merc
			}
			
			infoMercado.setDataInfo( new Date() );
			infoMercado.setPreenchidoPor( infoMercadoForm.getString("preenchidoPor") );
			infoMercado.setChassi( infoMercadoForm.getString("chassi").trim() );
			infoMercado.setTotalHrs( (Long) infoMercadoForm.get("totalHrs") );
			infoMercado.setDataVenda( DateUtils.parseDate( infoMercadoForm.getString("dataVenda") ) );
			infoMercado.setDataProblema( DateUtils.parseDate( infoMercadoForm.getString("dataProblema") ) );
			
			long tipoGasolinaId = new Long( infoMercadoForm.getString("tipoGasolinaId") ).longValue();
			if ( tipoGasolinaId > 0 )			
				infoMercado.setTipoGasolinaId( infoMercadoForm.getString("tipoGasolinaId") );
			
			if ( infoMercadoForm.getString("localUsoDoce") != null )
				infoMercado.setLocalUsoDoce( infoMercadoForm.getString("localUsoDoce") );
			
			if ( infoMercadoForm.getString("localUsoSalg") != null )
					infoMercado.setLocalUsoSalg( infoMercadoForm.getString("localUsoSalg") );
			
			infoMercado.setMaterHelice( infoMercadoForm.getString("materHelice") );
			
			long tipoUsoId =  new Long( infoMercadoForm.getString("tipoUso") ).longValue();
			if ( tipoUsoId > 0 ) { 
				infoMercado.setTipoUso( infoMercadoForm.getString("tipoUso") );
				infoMercado.setTipoUsoId( new Long( infoMercadoForm.getString("tipoUso") ) );
			}
			
			infoMercado.setCidadeUso( infoMercadoForm.getString("cidadeUso") );
			infoMercado.setMarcaCasco( infoMercadoForm.getString("marcaCasco") );
			infoMercado.setModeloCasco( infoMercadoForm.getString("modeloCasco") );
			infoMercado.setMarcaHelice( infoMercadoForm.getString("marcaHelice") );
			
			Long itemId = infoMercadoForm.get("itemId") != null  ? (Long)infoMercadoForm.get("itemId") : new Long(0); 
			
			if ( itemId == null || itemId.equals(new Long(0)) ) {
				
				ActionMessages problemMessages = new ActionMessages();
				problemMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( "infoMercado.msg.error.pecaObrigatorio" ) );
	            super.addProblemMessages(request, problemMessages);
	            
	            infoMercadoForm.set("data", DateUtils.applyMask( new Date() ) );
	            infoMercadoForm.set("listTipoGasolina", infoMercadoBusiness.listTipoGasolina());
				infoMercadoForm.set("listTipoProduto" , infoMercadoBusiness.listTipoProdutoInfo() );
				infoMercadoForm.set("listTipoUso"     , infoMercadoBusiness.listTipoUso()); 
				infoMercadoForm.set( "listItems"      , new ArrayList() );
				infoMercadoForm.set( "listFotos"      , new ArrayList() );
				infoMercadoForm.set( "chassi"         , "" );
	            
				this.setJavaScriptExecuter(request, "setFocusChassi()" );
				
	            return mapping.findForward("_form");
	            
			}
			
			Item item = infoMercadoBusiness.getItemById( itemId ); 						
			
			log.info("Item:" + item.getEntityId() + "  Agua Doce:" + infoMercado.getLocalUsoDoce() + "  Agua Salagada:" + infoMercado.getLocalUsoSalg());
			
			infoMercado.setItemId((Long)item.getEntityId());
			infoMercado.setStatusImId( new Long(1) );
			infoMercado.setStatusInfoMercado( infoMercadoBusiness.getStatus( new Long(1) ) );
			infoMercado.setConcessionaria( usuario.getConcessionaria() );
			
			if ( infoMercadoForm.get("passoHelice") != null )
				infoMercado.setPassoHelice( (Long) infoMercadoForm.get("passoHelice") );

			if ( infoMercadoForm.get("rotacaoMaxima") != null )
				infoMercado.setRotacaoMaxima( (Long) infoMercadoForm.get("rotacaoMaxima") );
			
			FormFile imageFile1 = (FormFile) infoMercadoForm.get("imageFile1");
			FormFile imageFile2 = (FormFile) infoMercadoForm.get("imageFile2");
			FormFile imageFile3 = (FormFile) infoMercadoForm.get("imageFile3");
			
			InfoMercFotos image = new InfoMercFotos();
			List listFotos = new ArrayList();
			
			if ( imageFile1 != null && imageFile1.getFileName() != null && imageFile1.getFileSize() > 0 ) {
				
				//log.info("Image1:" + imageFile1.getFileName());
				
				if ( InfoMercFotos.isValidFile(imageFile1) ) {
										
					image.setContent(imageFile1.getFileData());
					image.setFilename(imageFile1.getFileName());
					
					ControllerHelper.prepare( image, (Long)usuario.getIdentifier() );
					
					image.setInfoMercado(infoMercado);
					
					listFotos.add(image);
					
				} else {
					
					messagesWarning.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("infoMercado.warning.msg.invalidFile", imageFile1.getFileName()) );
		            super.addWarningMessages(request, messagesWarning);						
					
				}
			}
			
			if ( imageFile2 != null && imageFile2.getFileName() != null && imageFile2.getFileSize() > 0 ) {
				
				//log.info("Image2:" + imageFile2.getFileName());
				
				if ( InfoMercFotos.isValidFile(imageFile2) ) {
					
					image = new InfoMercFotos();
					image.setContent(imageFile2.getFileData());
					image.setFilename(imageFile2.getFileName());
					
					ControllerHelper.prepare( image, (Long)usuario.getIdentifier() );
					
					image.setInfoMercado(infoMercado);
					
					listFotos.add(image);
					
				} else {
					
					messagesWarning.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("infoMercado.warning.msg.invalidFile", imageFile2.getFileName()) );
		            super.addWarningMessages(request, messagesWarning);						
					
				}
			}
			
			if ( imageFile3 != null && imageFile3.getFileName() != null && imageFile3.getFileSize() > 0 ) {
				
				//log.info("Image3:" + imageFile3.getFileName());
				
				if ( InfoMercFotos.isValidFile(imageFile3) ) {
					
					image = new InfoMercFotos();
					image.setContent(imageFile3.getFileData());
					image.setFilename(imageFile3.getFileName());
					
					ControllerHelper.prepare( image, (Long)usuario.getIdentifier() );
					
					image.setInfoMercado(infoMercado);
					
					listFotos.add(image);
					
				} else {
					
					messagesWarning.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("infoMercado.warning.msg.invalidFile", imageFile3.getFileName()) );
		            super.addWarningMessages(request, messagesWarning);						
					
				}	
			}
			
			if ( !listFotos.isEmpty() )
				infoMercado.setListFotos(listFotos);
			
			ControllerHelper.prepare( infoMercado, (Long)usuario.getIdentifier() );
			
			InfoMercadoVO imVO = infoMercadoBusiness.save( infoMercado
									                , infoMercadoForm.getString("condicaoProblema")
									                , infoMercadoForm.getString("causaProblema")
									                , infoMercadoForm.getString("diagnostico")
									                , infoMercadoForm.getString("solucao")
									                , usuario.getEntityId() 
									                , isEdit);
						
			if(!imVO.isHasError()) {
				this.setAlertMessage(request, "A Informação de Mercado foi registrada com Sucesso!");
			}else {
				
				infoMercadoForm.set("data", DateUtils.applyMask( new Date() ) );
	            infoMercadoForm.set("listTipoGasolina", infoMercadoBusiness.listTipoGasolina());
				infoMercadoForm.set("listTipoProduto" , infoMercadoBusiness.listTipoProdutoInfo() );
				infoMercadoForm.set("listTipoUso"     , infoMercadoBusiness.listTipoUso()); 
				infoMercadoForm.set( "listItems"      , new ArrayList() );
				infoMercadoForm.set( "listFotos"      , new ArrayList() );
				throw new BusinessRuleException(imVO.getMensagem());
				
			}
			
			infoMercadoForm.set("listResults", infoMercadoBusiness.listByConcessionaria( usuario.getConcessionaria() ));
			
			ActionMessages successMessages = new ActionMessages();
			successMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("form.msg.saved") );
        	super.addSuccessMessages(request, successMessages);	
        	
			return mapping.findForward("_list");
			
		} catch (BusinessRuleException brExp) {
			
			this.setAlertMessage(request, brExp.getMessage());
             
            return mapping.findForward("_form");
            
		} catch (BeansException bExp) {
			throw new ControllerException(bExp);
			
		} catch (BusinessException bExp) {
			throw new ControllerException(bExp);
			
		} catch (InvalidSessionException isExp) {
			throw new ControllerException(isExp);
			
		} catch (ParseException pExp) {
			throw new ControllerException(pExp);
			
		} catch (FileNotFoundException e) {
			
			throw new ControllerException(e);
			
		} catch (IOException e) {
			
			throw new ControllerException(e);
			
		}
		
	}   

	/** Tarefa: Libera uma informação de mercado.
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
	public ActionForward release( ActionMapping mapping
			                    , ActionForm form
			                    , HttpServletRequest request
			                    , HttpServletResponse response) throws ControllerException {

		try {
			
			// Primeiro verificamos se o identificador foi recebido corretamente.
			String pEntityId = request.getParameter("entityId");
			if (pEntityId == null)
				throw new ControllerException("Nenhum ID informado");
			
			Integer    entityId = new Integer( request.getParameter("entityId") );
			Usuario usuario  = (Usuario) UserHelper.getBoundedUser(request.getSession() );	
			InfoMercadoBusiness infoMercadoBusiness = (InfoMercadoBusiness) springContext.getBean("infoMercadoBO");
			infoMercadoBusiness.release(entityId, (Long)usuario.getEntityId(), usuario.getConcessionaria());
			
			ActionMessages successMessages = new ActionMessages();
			successMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("infoMercado.success.msg.released", entityId) );
        	super.addSuccessMessages(request, successMessages);
			
			return this.list(mapping, form, request, response);
			
		} catch (NumberFormatException nfExp) {
			throw new ControllerException(nfExp);
			
		} catch (BeansException bExp) {
			throw new ControllerException(bExp);
			
		} catch (BusinessRuleException brExp) {
			
			ActionMessages problemMessages = new ActionMessages();
			problemMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( brExp.getMessage() ) );
            super.addProblemMessages(request, problemMessages);
             
            return mapping.findForward("_list");			
			
		} catch (BusinessException bExp) {
			throw new ControllerException(bExp);
			
		} catch (InvalidSessionException isExp) {
			throw new ControllerException(isExp);
			
		}

	}	
	
	/** Tarefa: Carrega as informações de um InfoMercado para formulário de InfoMercado.
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
	public ActionForward edit( ActionMapping mapping
			                 , ActionForm form
			                 , HttpServletRequest request
			                 , HttpServletResponse response) throws ControllerException {

		return mapping.findForward("_form");

	}

	/** Tarefa: Listagem.
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
	public ActionForward list( ActionMapping mapping
		                   	 , ActionForm form
			                 , HttpServletRequest request
			                 , HttpServletResponse response) throws ControllerException {

		DynaValidatorActionForm infoMercadoForm = (DynaValidatorActionForm) form;

		try {

			InfoMercadoBusiness infoMercadoBusiness = (InfoMercadoBusiness) springContext.getBean("infoMercadoBO");
			Usuario             usuario             = (Usuario) UserHelper.getBoundedUser(request.getSession());
			List                results             = new ArrayList();

			if ( infoMercadoForm.get("filterType") != null 
						&& !((Integer)infoMercadoForm.get("filterType")).equals( new Integer(0 ) )
						&& infoMercadoForm.getString("filterValue") != null ) {

				int    filterType  = ( (Integer)infoMercadoForm.get("filterType") ).intValue();
				String filterValue = infoMercadoForm.getString("filterValue");

				results = infoMercadoBusiness.listByConcessionaria(usuario.getConcessionaria(), filterType, filterValue);

			} else {
				//log.info("Carregando entidades!");
				
				results = infoMercadoBusiness.listByConcessionaria( usuario.getConcessionaria() );

			}
			
			//log.info("Retornando dados para o form:" + results.size());

			infoMercadoForm.set("listResults", results);

			return mapping.findForward("_list");

		} catch (BusinessException bExp) {
			throw new ControllerException(bExp);

		} catch ( InvalidSessionException isExp ) {
			throw new ControllerException(isExp);

		}  

	}
	
	/** Tarefa: Remove uma informação existente.
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
	public ActionForward delete( ActionMapping mapping
			                 , ActionForm form
			                 , HttpServletRequest request
			                 , HttpServletResponse response) throws ControllerException {

		// Só iniciamos o processo se houver um identificador fornecido.
		if ( request.getParameter("entityId") != null ) {

			try {
				
				log.info("removendo InfoMercado:" + request.getParameter("entityId"));
				
				InfoMercadoBusiness infoMercadoBusiness = (InfoMercadoBusiness) springContext.getBean("infoMercadoBO");
				Usuario             usuario             = (Usuario) UserHelper.getBoundedUser(request.getSession());
				infoMercadoBusiness.delete( new Integer( request.getParameter("entityId") ), usuario.getEntityId() );

				ActionMessages successMessages = new ActionMessages();
				successMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("form.msg.oneremoved") );
				super.addSuccessMessages(request, successMessages);
				
				return this.list(mapping, form, request, response);
				
			} catch (BeansException bExp) {
				throw new ControllerException( bExp );
				
			} catch (NumberFormatException nfExp) {
				throw new ControllerException( nfExp );
				
			} catch (BusinessException bExp) {
				throw new ControllerException( bExp );

			} catch ( InvalidSessionException isExp ) {
				throw new ControllerException(isExp);

			}  
			
		} else {
			
			throw new ControllerException("Nenhum identificador de entidade encontrado.");
			
		}

	}	
	
	/** Tarefa: Localiza uma imagem e dispara o download.
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
	public ActionForward dowloaderImage( ActionMapping mapping
			                           , ActionForm form
                                       , HttpServletRequest request
			                           , HttpServletResponse response) throws ControllerException {

		// Só iniciamos o processo se houver um identificador fornecido.
		if ( request.getParameter("infoMercFotosId") != null ) {
			
			try {

				// Declaração de variáveis utilizadas pelo controller.
				Long                 infoMercFotosId     = new Long( request.getParameter("infoMercFotosId") );
				InfoMercadoBusiness  infoMercadoBusiness = (InfoMercadoBusiness) springContext.getBean("infoMercadoBO");
				
				Blob content = infoMercadoBusiness.getContentFile(infoMercFotosId);				
				
				YmSessionHelper.setImageToSession(request, content);
				
				//request.setAttribute(InfoMercFotos.CONTENT_IMAGE, infoMercFotos.getContent());
				//request.setAttribute(InfoMercFotos.NAME_IMAGE   , infoMercFotos.getFilename());
				
				log.info("ACTION - Arquivo lido:" + (content != null));
				
				
			} catch (BusinessException bExp) {
				
				throw new ControllerException( bExp );
				
			}
			
			return mapping.findForward("_downloader");
			
		} else {
			
			throw new ControllerException("Nenhum identificador de entidade encontrado.");
			
		}
		
	}	
	
	/** Tarefa: Localiza uma imagem e remove do Banco de Dados.
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
	public ActionForward removeImage( ActionMapping mapping
			                           , ActionForm form
                                       , HttpServletRequest request
			                           , HttpServletResponse response) throws ControllerException {
		
		ActionMessages successMessages = new ActionMessages();
		ActionMessages problemMessages = new ActionMessages();

		// Só iniciamos o processo se houver um identificador fornecido.
		if ( request.getParameter("infoMercFotosId") != null ) {
			
			try {

				// Declaração de variáveis utilizadas pelo controller.
				Long                 infoMercFotosId     = new Long( request.getParameter("infoMercFotosId") );
				InfoMercadoBusiness  infoMercadoBusiness = (InfoMercadoBusiness) springContext.getBean("infoMercadoBO");
				
				InfoMercFotos infoMercFotos = (InfoMercFotos)infoMercadoBusiness.getInfoMercFotos(infoMercFotosId);				
								
				request.setAttribute("entityId", (Integer)infoMercFotos.getInfoMercado().getEntityId());
				
				infoMercadoBusiness.removeImage(infoMercFotos.getEntityId());
				
				successMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("infoMercado.success.msg.removedImage") );
				super.addSuccessMessages(request, successMessages);
				
				return showFilledForm(mapping, form, request, response);				
				
			} catch (BusinessException bExp) {
				
				problemMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( bExp.getMessage() ) );
	            super.addProblemMessages(request, problemMessages);
				
	            return showFilledForm(mapping, form, request, response);
				
			}
			
		} else {
			
			throw new ControllerException("Nenhum identificador de entidade encontrado.");
			
		}
		
	}	
	
}
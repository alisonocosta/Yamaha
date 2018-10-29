/* Yamaha
 * Copyright (c) 2013 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......ClassificaGarantiaMotoAction.java
 *   .: Criação.....10 de dezembro, 20:40
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Action para o processo de classificação técnica de garantias.
 */
package br.com.yamaha.sistemagarantia.processo.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import br.com.resource.infra.controller.InfraDispatchAction;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.BusinessRuleException;
import br.com.resource.infra.exception.ControllerException;
import br.com.resource.infra.security.exception.InvalidSessionException;
import br.com.resource.infra.security.helper.UserHelper;
import br.com.yamaha.sistemagarantia.business.CupomBusiness;
import br.com.yamaha.sistemagarantia.business.FaturamentoBusiness;
import br.com.yamaha.sistemagarantia.business.GarantiaArquivoBusiness;
import br.com.yamaha.sistemagarantia.business.GarantiaHeaderBusiness;
import br.com.yamaha.sistemagarantia.business.UsuarioBusiness;
import br.com.yamaha.sistemagarantia.business.exception.FileException;
import br.com.yamaha.sistemagarantia.controller.helper.FileHelper;
import br.com.yamaha.sistemagarantia.model.AlertGarantia;
import br.com.yamaha.sistemagarantia.model.Cupom;
import br.com.yamaha.sistemagarantia.model.Faturamento;
import br.com.yamaha.sistemagarantia.model.GarantiaArquivo;
import br.com.yamaha.sistemagarantia.model.GarantiaArquivoMoto;
import br.com.yamaha.sistemagarantia.model.GarantiaArquivoNtc;
import br.com.yamaha.sistemagarantia.model.GarantiaHeader;
import br.com.yamaha.sistemagarantia.model.Linha;
import br.com.yamaha.sistemagarantia.model.Usuario;
import br.com.yamaha.sistemagarantia.processo.business.AnaliseGarantiaBusiness;
import br.com.yamaha.sistemagarantia.processo.business.ClassificaGarantiaMotoBusiness;
import br.com.yamaha.sistemagarantia.processo.model.ClassificaGarantiaMotoVO;
import br.com.yamaha.sistemagarantia.utils.ConstantAnaliseGarantia;
import br.com.yamaha.sistemagarantia.utils.ConstantClassificaGarantiaMoto;
import br.com.yamaha.sistemagarantia.utils.FileUtils;

/** Action responsável por fornecer controle às funcionalidades
 *  do módulo de garantias do sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class ClassificaGarantiaMotoAction extends InfraDispatchAction {
	
	private static final long serialVersionUID = 1655995272785870005L;

	/** Tarefa: Apresenta form de pesquisa de garantias para classificação.
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
    public ActionForward showForm( ActionMapping mapping
                             , ActionForm form
                             , HttpServletRequest request
                             , HttpServletResponse response) throws ControllerException {
    	   
    	DynaActionForm classificaTecForm = (DynaActionForm) form;
    	try { 
    		Long usuarioId  = null;
    		Usuario user = null;
    		String innerCode = request.getParameter( "innerCode" );
    		
    		if(innerCode == null){    		
    			user = (Usuario) UserHelper.getBoundedUser(request.getSession());
    			usuarioId = (Long) user.getEntityId();
    		}
    		
    		if(!"".equals(innerCode)) {
	    		
    			if(user == null) {
    				usuarioId  = Long.valueOf(innerCode); 
		    		UserHelper.unboundUserToSession(request.getSession());		        	
		            UsuarioBusiness  usuarioBusiness  = (UsuarioBusiness) springContext.getBean("usuarioBO");
		            user = usuarioBusiness.get( usuarioId );  
		            user = usuarioBusiness.getByCode(user.getUsuarioCode());		            	    		
		            UserHelper.boundUserToSession(request.getSession(), user);
    			}
    			
    			if(ConstantClassificaGarantiaMoto.LINHA_MOTO.equals( user.getSegmento())){

    				ClassificaGarantiaMotoBusiness  classificaGarantiaMotoBusiness  = (ClassificaGarantiaMotoBusiness)  springContext.getBean("classificaGarantiaMotoBO");

    				List results = classificaGarantiaMotoBusiness.listByParametro(null, ConstantClassificaGarantiaMoto.TIPO_POR_LINHA, user.getSegmento());            

    				if(results != null && !results.isEmpty()) {         
    					classificaTecForm.set("listResults", results);    			
    				} else {
    					classificaTecForm.set( "listResults" , new ArrayList(0));
    				}	            
    				request.setAttribute("listOptionsSearch" , ConstantClassificaGarantiaMoto.listValuesSearch());	 	            	   

    				return mapping.findForward("_list");
    			} else
        			throw new ControllerException("Usuário não pertence a linha de Motocicletas!");
    		} else
    			throw new ControllerException("Usuário inválido!");
    		
        } catch (BusinessException bExp) {
            bExp.printStackTrace();
            throw new ControllerException("Um Erro ocorreu:" + bExp.getMessage());
            
        } catch ( InvalidSessionException isExp ) {
        	isExp.printStackTrace();
    		throw new ControllerException(isExp);

    	} 
    	
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
    	
    	DynaActionForm classificaTecForm = (DynaActionForm) form;
    	ActionMessages 			messagesProblem    = new ActionMessages();
    	ActionMessages 			messagesWarning    = new ActionMessages();

    	try {

    		String parametro = classificaTecForm.getString("parametro");            
    		String criterio  = classificaTecForm.getString("criterio");
    		if(parametro == null || criterio == null || "".equals(parametro) || "".equals("criterio")) {
    			messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("classificaGarantiaMoto.msg.problem.parametros.pesquisa") );
    			super.addProblemMessages(request, messagesProblem);	            
    		}  else {  

    			ClassificaGarantiaMotoBusiness  classificaGarantiaMotoBusiness  = (ClassificaGarantiaMotoBusiness)  springContext.getBean("classificaGarantiaMotoBO");
    			Usuario usuario = (Usuario) UserHelper.getBoundedUser(request.getSession());

    			List results = classificaGarantiaMotoBusiness.listByParametro(parametro.trim(), criterio.trim(), usuario.getSegmento());            

    			if(results != null && !results.isEmpty())            
    				classificaTecForm.set("listResults", results);
    			else {
    				messagesWarning.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("classificaGarantiaMoto.msg.warning.results") );
    				super.addWarningMessages(request, messagesWarning);
    			}

    		}     

    	} catch (BusinessRuleException bExp) {

    		messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("classificaGarantiaMoto.msg.problem.parametros.pesquisa") );
    		super.addProblemMessages(request, messagesProblem);

    	} catch (BusinessException bExp) {

    		throw new ControllerException(bExp);

    	} catch ( InvalidSessionException isExp ) {

    		throw new ControllerException(isExp);

    	} 

    	request.setAttribute("listOptionsSearch" , ConstantClassificaGarantiaMoto.listValuesSearch());	

    	return mapping.findForward("_list");
    	
    }
    
    /** Tarefa: Apresenta form de classificação Técnica de garantia.
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
    public ActionForward showFormClassica( ActionMapping mapping
                             , ActionForm form
                             , HttpServletRequest request
                             , HttpServletResponse response) throws ControllerException {
    	
    	DynaActionForm classifcaTecForm = (DynaActionForm) form;

    	String garantiaIdStr = request.getParameter( "garantiaId" );
    	if(garantiaIdStr != null && !"".equals(garantiaIdStr)) {
    		Integer garantiaId  = Integer.valueOf(garantiaIdStr.trim());    

    		preparaForm(classifcaTecForm, garantiaId);

    	} else
    		throw new ControllerException("Garantia inválida!");
    	
    	return mapping.findForward("_form");
    }
    
    /** Tarefa: Liberar uma Garantia sem a necessidade de classificação técnica.
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
    public ActionForward liberar( ActionMapping mapping
                             , ActionForm form
                             , HttpServletRequest request
                             , HttpServletResponse response) throws ControllerException {
    	
    	DynaActionForm classificaTecForm = (DynaActionForm) form;
    	ActionMessages messagesProblem    = new ActionMessages();
    	ActionMessages messagesSuccess    = new ActionMessages();
    	
    	try{
    		String garantiaIdStr = request.getParameter( "garantiaId" );
    		if(garantiaIdStr != null && !"".equals(garantiaIdStr)) {
    			Integer garantiaId  = Integer.valueOf(garantiaIdStr.trim());    

    			ClassificaGarantiaMotoBusiness  classificaGarantiaMotoBusiness  = (ClassificaGarantiaMotoBusiness)  springContext.getBean("classificaGarantiaMotoBO");
    			Usuario usuario = (Usuario) UserHelper.getBoundedUser(request.getSession());

    			GarantiaHeader garantia	= (GarantiaHeader) classificaGarantiaMotoBusiness.getGarantia(garantiaId);

    			if(garantia != null) {
    				
    				//Atualizamos o status
    				AlertGarantia alerta = classificaGarantiaMotoBusiness.classificarSG(usuario, garantia, ClassificaGarantiaMotoVO.CLASSIFICA_LIBERAR_SEM_CLASSIFICA);

    				if ( alerta != null ) {

    					if ( alerta.getAlertGarantiaKey().indexOf("error") != -1 ) {

    						messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage(alerta.getAlertGarantiaKey(),garantiaId) );
    						super.addProblemMessages(request, messagesProblem);

    					} else if ( alerta.getAlertGarantiaKey().indexOf("success") != -1 ) {

    						messagesSuccess.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage(alerta.getAlertGarantiaKey(), garantiaId) );
    						super.addSuccessMessages(request, messagesSuccess);

    					}
    				}
    			}
    		} else
    			throw new ControllerException("Garantia inválida!");
    		
    	} catch (BusinessException bExp) {
    		bExp.printStackTrace();
    		throw new ControllerException(bExp);
    	} catch ( InvalidSessionException isExp ) {
    		isExp.printStackTrace();
    		throw new ControllerException(isExp);
    	} 
    	return mapping.findForward("_list");
    }
    
    /** Tarefa: Classifica uma Garantia devolvendo para o usuário 
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
    public ActionForward classificar( ActionMapping mapping
                             , ActionForm form
                             , HttpServletRequest request
                             , HttpServletResponse response) throws ControllerException {
    	
    	DynaActionForm classificaTecForm = (DynaActionForm) form;
    	ActionMessages messagesProblem    = new ActionMessages();
    	ActionMessages messagesSuccess    = new ActionMessages();
    	
    	try{
    		Integer garantiaId = (Integer)classificaTecForm.get( "entityId" );
    		String prcCode 		 = request.getParameter("prcCode");
    		String tipoProblema  = classificaTecForm.getString("tipoProblema");
    		String classificaAcao = classificaTecForm.getString("classificaAcao");
    		
    		if(garantiaId != null && prcCode !=  null && !"".equals(prcCode)) {    			   

    			ClassificaGarantiaMotoBusiness  classificaGarantiaMotoBusiness  = (ClassificaGarantiaMotoBusiness)  springContext.getBean("classificaGarantiaMotoBO");
    			Usuario usuario = (Usuario) UserHelper.getBoundedUser(request.getSession());

    			GarantiaHeader garantia	= (GarantiaHeader) classificaGarantiaMotoBusiness.getGarantia(garantiaId);

    			if(garantia != null) { 
    				
    				if(tipoProblema != null && !"".equals(tipoProblema)) {
    					garantia.setTipoProblema(tipoProblema);
    				}
    				if(classificaAcao != null && !"".equals(classificaAcao)){
    					garantia.setClassificaAcao(classificaAcao);
    				}
    				
    				//Atualizamos o status
    				AlertGarantia alerta = classificaGarantiaMotoBusiness.classificarSG(usuario, garantia, prcCode);

    				if ( alerta != null ) {

    					if ( alerta.getAlertGarantiaKey().indexOf("error") != -1 ) {

    						messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage(alerta.getAlertGarantiaKey(),garantiaId) );
    						super.addProblemMessages(request, messagesProblem);

    					} else if ( alerta.getAlertGarantiaKey().indexOf("success") != -1 ) {

    						messagesSuccess.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage(alerta.getAlertGarantiaKey(), garantiaId) );
    						super.addSuccessMessages(request, messagesSuccess);

    					}
    				}
    			}
    		} else
    			throw new ControllerException("Garantia inválida!");
    		
    	} catch (BusinessException bExp) {
    		bExp.printStackTrace();
    		throw new ControllerException(bExp);
    	} catch ( InvalidSessionException isExp ) {
    		isExp.printStackTrace();
    		throw new ControllerException(isExp);
    	} 
    	return mapping.findForward("_list");
    }
    
    /** Prepara o formulário para retornar a apresentação
     * 
     * @param classificaTecForm
     * @param garantiaId
     * @throws ControllerException
     */
    private void preparaForm(DynaActionForm classificaTecForm, Integer garantiaId) throws ControllerException {
    	
    	try { 
    		
    		GarantiaHeaderBusiness garantiaHeaderBusiness = (GarantiaHeaderBusiness) springContext.getBean("garantiaHeaderBO");
    		GarantiaHeader garantia = garantiaHeaderBusiness.get(garantiaId);

    		FaturamentoBusiness faturamentoBusiness = (FaturamentoBusiness) springContext.getBean("faturamentoBO");
    		Faturamento 		faturamento 		= faturamentoBusiness.getByChassi(garantia.getModelo());
    		AnaliseGarantiaBusiness  analiseGarantiaBusiness  = (AnaliseGarantiaBusiness)  springContext.getBean("analiseGarantiaBO");

    		CupomBusiness 		cupomBusiness 		= (CupomBusiness) springContext.getBean("cupomBO");

    		List cupons = cupomBusiness.listByChassiAsc(garantia.getModelo());
    		
    		//Recuperando o cupom da revisão de entrega
    		Iterator itCp = cupons.iterator();
    		Cupom cp = null;
    		String dtVenda = "";
    		while(itCp.hasNext()){
    			
    			cp = (Cupom)itCp.next();
    			if(new Long(1).equals(cp.getRevisao().getNumeroRevisao())) {
    				dtVenda = cp.getMaskedDataVenda();
    				break;
    			}
    			
    		}    		
    		classificaTecForm.set( "entityId"		 , garantia.getEntityId());    		
    		classificaTecForm.set( "loteId"			 ,  garantia.getLote().getEntityId());
    		classificaTecForm.set( "chassi"			 ,  garantia.getModelo());
    		classificaTecForm.set( "dtVenda"			 , dtVenda);
    		classificaTecForm.set( "numOS"			 , garantia.getNumeroOS());
    		classificaTecForm.set( "dtAbert"			 , garantia.getMaskedDataAberturaOS());
    		classificaTecForm.set( "dtFech"			 , garantia.getMaskedDataFechamentoOS());
    		classificaTecForm.set( "condicaoProblema" , garantia.getCondicaoProblemaGarantia() != null ? garantia.getCondicaoProblemaGarantia().getCondicaoProblema():"");
    		classificaTecForm.set( "causaProblema"    , garantia.getCausaProblemaGarantia() != null ? garantia.getCausaProblemaGarantia().getCausaProblema():"");
    		classificaTecForm.set( "diagnostico"      , garantia.getDiagnosticoProblemaGarantia() != null ? garantia.getDiagnosticoProblemaGarantia().getDiagnosticoProblema():"");
    		classificaTecForm.set( "solucao"          , garantia.getSolucaoProblemaGarantia() != null ? garantia.getSolucaoProblemaGarantia().getSolucaoProblema():"");
    		classificaTecForm.set( "dtFaturamento"	 , faturamento.getMaskedDataNotaFiscal());
    		classificaTecForm.set( "nuNtFiscal"		 , faturamento.getNumeroNotaFiscal());
    		classificaTecForm.set( "empresa"			 , faturamento.getEmpresa().getOrgCode());
    		classificaTecForm.set( "dsConcessionaria" , faturamento.getConcessionaria().getRazaoSocial());
    		classificaTecForm.set( "cnpjConc" 		 , faturamento.getConcessionaria().getMaskedCnpj());
    		classificaTecForm.set( "dsCidade"		 , faturamento.getConcessionaria().getCidade());
    		classificaTecForm.set( "dsUf"			 , faturamento.getConcessionaria().getUf());
    		
    		classificaTecForm.set( "listRevisao"		 , cupons);
    		classificaTecForm.set( "listGarantias"	 , analiseGarantiaBusiness.listHistoricoGarantia((Integer)garantia.getEntityId(),garantia.getModelo()));
    				
    		classificaTecForm.set( "parecerYm"		 , analiseGarantiaBusiness.listHistParecerTecnico((Integer)garantia.getEntityId()));
    		classificaTecForm.set( "observacaoYm"	 , analiseGarantiaBusiness.listHistAnaliseObservacao((Integer)garantia.getEntityId()));
    		classificaTecForm.set( "listPecas"		 , garantia.getListGarantiaLineNotDouble());
    		
    		classificaTecForm.set( "tipoProblema"     , garantia.getTipoProblema());
    		classificaTecForm.set( "classificaAcao"   , garantia.getClassificaAcao());
			 
    		try {
				if(garantia.getEntityId() != null ) {
					GarantiaArquivoBusiness gArquivoBusiness = (GarantiaArquivoBusiness) springContext.getBean("garantiaArquivoBO");				
									
					classificaTecForm.set( "listFiles", gArquivoBusiness.listFile((Integer)garantia.getEntityId()));					
					
				} else {
					classificaTecForm.set( "listFiles", null);
				}
				
			}catch(FileException flExp){
				classificaTecForm.set( "listFiles", null);
			}
    		
    	} catch (BusinessException bExp) {

    		throw new ControllerException("Usuário inválido!");

    	}   

    }
    
    /** Tarefa: Retorna o fluxo de bytes para download do arquivo selecionado.
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
     * 
     */ 
    public ActionForward download(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception {
    	    
		ActionMessages          errorMessages   = new ActionMessages();
		
    	Integer fileId = request.getParameter("fileId") != null ? Integer.valueOf(request.getParameter("fileId")):null;
    	
    	try {
    		
    		GarantiaArquivoBusiness gArquivoBusiness = (GarantiaArquivoBusiness) springContext.getBean("garantiaArquivoBO");
    			
    		GarantiaArquivo gArq = gArquivoBusiness.get(fileId);
    		
    		if(gArq != null) {
    			
    			byte[] bytes;
    			
    			if(Linha.TIPO_MOTOCICLETA.equals(gArq.getLinhaId())){
    				GarantiaArquivoMoto  gAMoto = gArquivoBusiness.getArquivoMoto(gArq);
    				bytes = gAMoto.getContent().getBytes(1,gArq.getSize().intValue());
    				
    			} else {
    				GarantiaArquivoNtc gANtc = gArquivoBusiness.getArquivoNtc(gArq);
    				bytes = gANtc.getContent().getBytes(1, gArq.getSize().intValue());
    			}

    			if(bytes.length > 0 ) {
    				
    				ServletOutputStream ouputStream = response.getOutputStream(); 

    				response.setContentType(gArq.getContentType());
    				response.setHeader("Content-Disposition", "attachment; filename=" + gArq.getFilename());

    				response.setContentLength( bytes.length); 

    				ouputStream.write( bytes, 0,  bytes.length);

    				ouputStream.flush();
    				ouputStream.close();

    			}
    			
    			/*
		        File file = FileUtils.getFileByName(gArq.getFilePath());
		        		        	
		        if ( file.exists() ) { 
		        		
		        		ServletOutputStream ouputStream = response.getOutputStream(); 
		        		
			        	response.setContentType(gArq.getContentType());
			        	response.setHeader("Content-Disposition", "attachment; filename=" + gArq.getFilename());
		    	
			        	byte[] bytes = FileHelper.getBytesFromFile(file);
			        	response.setContentLength( bytes.length); 
		    	
			        	ouputStream.write( bytes, 0,  bytes.length);
			        	
			        	ouputStream.flush();
			    		ouputStream.close();
			        	
		        } else {
		        	errorMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("documento.msg.error.fileNotFount") );
		        	super.addProblemMessages(request, errorMessages);

		        }
				*/
    		} else {

    			errorMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("documento.msg.error.fileNotFount") );
    			super.addProblemMessages(request, errorMessages);


    		}
    	    		
    	} catch (FileNotFoundException fex){
    	
    		errorMessages.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("documento.msg.error.fileNotFount") );
            super.addProblemMessages(request, errorMessages);
                		
    	}
    
    	return null;
        
    }  
	
}
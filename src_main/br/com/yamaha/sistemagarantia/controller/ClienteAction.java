/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......ClienteFormAction.java
 *   .: Criação.....16 de abril, 9:50
 *   .: Autor.......Edson Luiz Sumensari
 *                  Thiago Uriel M. Garcia
 *   .: Descrição...Controla requisições sobre Clientes.
 */
package br.com.yamaha.sistemagarantia.controller;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.validator.DynaValidatorActionForm;

import br.com.resource.infra.controller.InfraDispatchAction;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.BusinessRuleException;
import br.com.resource.infra.exception.ControllerException;
import br.com.resource.infra.security.SystemUser;
import br.com.resource.infra.security.exception.InvalidSessionException;
import br.com.resource.infra.security.helper.UserHelper;
import br.com.resource.infra.utils.DateUtils;
import br.com.yamaha.sistemagarantia.business.ClienteBusiness;
import br.com.yamaha.sistemagarantia.business.EstadoBusiness;
import br.com.yamaha.sistemagarantia.business.EstadoDddBusiness;
import br.com.yamaha.sistemagarantia.controller.helper.ControllerHelper;
import br.com.yamaha.sistemagarantia.controller.helper.YmSessionHelper;
import br.com.yamaha.sistemagarantia.model.Cliente;
import br.com.yamaha.sistemagarantia.model.Estado;
import br.com.yamaha.sistemagarantia.model.EstadoDdd;
import br.com.yamaha.sistemagarantia.model.Usuario;
import br.com.yamaha.sistemagarantia.model.to.CupomTO;

/** Classe controla as tarefas relacionadas ao <b>Cliente</b>.
 * 
 *  @author Edson Luiz Sumensari
 *  @author Thiago Uriel M. Garcia
 */
public class ClienteAction extends InfraDispatchAction {

   
	private static final long serialVersionUID = 1L;

	/** Tarefa: Apresenta o formulário de Clientes.
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
    public ActionForward add( ActionMapping mapping
                            , ActionForm form
                            , HttpServletRequest request
                            , HttpServletResponse response) throws ControllerException {
    	
    	DynaValidatorActionForm clienteForm = (DynaValidatorActionForm) form;
    	
    	String cpfCnpjCliente = request.getParameter("cpfCnpjCliente") != null ? request.getParameter("cpfCnpjCliente") : "";
        clienteForm.initialize(mapping);
        
        if ( !"".equals(cpfCnpjCliente) )
        	clienteForm.set("cnpj", cpfCnpjCliente);
        
        clienteForm.set("pessoaFJ",  	Cliente.FISICA);
        
        clienteForm.set("listCliente",  	listEstados());
	    
        return mapping.findForward("_form");
        
    } 
    
    /** Tarefa: Apresenta o formulário de Clientes.
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
    public ActionForward include( ActionMapping mapping
                            , ActionForm form
                            , HttpServletRequest request
                            , HttpServletResponse response) throws ControllerException {
    	
    	DynaValidatorActionForm clienteForm = (DynaValidatorActionForm) form;
    	
    	clienteForm.initialize(mapping);
    	
    	CupomTO cupomTO = YmSessionHelper.getCupomTOFromSession(request);
    	
    	//log.info("CNPJ Cliente:" + cupomTO.getCnpj());
    	
    	String cnpj = cupomTO.getCnpj().toString();
    	
    	if ( cnpj.length() > 11 )
    		clienteForm.set("pessoaFJ", "J");
    	else
    		clienteForm.set("pessoaFJ", "F");
    	
    	clienteForm.set("cnpj", cupomTO.getCnpj().toString());
    	clienteForm.set("isFromCupom", "true");
    	clienteForm.set("listCliente",  	listEstados());
    	
    	this.setJavaScriptExecuter(request, "setTitle()" );

        return mapping.findForward("_form");
        
    }   
    
    /** Tarefa: Apresenta o formulário de Clientes.
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
     *  @author Resource IT - Demanda 0003
     */ 
    public ActionForward editByCnpjFromCupom( ActionMapping mapping
                            , ActionForm form
                            , HttpServletRequest request
                            , HttpServletResponse response) throws ControllerException {
    	
    	
    	try {
    		
    		DynaValidatorActionForm clienteForm = (DynaValidatorActionForm) form;
        	
        	clienteForm.initialize(mapping);
        	
        	CupomTO cupomTO = YmSessionHelper.getCupomTOFromSession(request);
        	
        	ClienteBusiness clienteBusiness = (ClienteBusiness) springContext.getBean("clienteBO");

        	// Usuario logado        
        	Usuario usuario = (Usuario) UserHelper.getBoundedUser(request.getSession());
        	
        	//log.info("CNPJ Cliente:" + cupomTO.getCnpj());
        	
        	String cnpj = cupomTO.getCnpj().toString();
        	
        	// recupera um cliente de acordo com um chassi e concessionária
        	Cliente         cliente         = (Cliente) clienteBusiness.getByCpfCnpj(Long.valueOf(cnpj));
        	
        	this.setJavaScriptExecuter(request, "setTitle()" );
        	
        	if ( cliente != null ) {
        	
        		//log.info(" Cliente localizado:" + cliente.getNome());
        		/* 
		    	 * Demanda 00003 - Resource IT
		    	 * Formatando a data de nascimento
		    	 */
	    		String dataNascimentoCorrigida = "";
		    	if (cliente.getDataNascimento() != null) {
			    	dataNascimentoCorrigida = new SimpleDateFormat("dd/MM/yyyy").format(cliente.getDataNascimento());
		    	}
		    	
        		BeanUtils.copyProperties(clienteForm, cliente);
            	clienteForm.set("cnpj", cupomTO.getCnpj().toString());
            	clienteForm.set("isFromCupom", "true");
        		clienteForm.set("pessoaFJ", 	cliente.getTipoCliente());	
        		clienteForm.set("listCliente",	listEstados());
        		clienteForm.set("dataNascimento", dataNascimentoCorrigida);
        		ControllerHelper.prepare( cliente, (Long)usuario.getIdentifier() );	    		    	
        		
        		return mapping.findForward("_form");
        		
        	} else {
        		
        		//log.info(" Cliente não localizado!");
        		clienteForm.set("listCliente",  	listEstados());	    
        		this.setAlertMessage(request, "Cliente não localizado!");
        		
        		return mapping.findForward("_search");
        		
        	}
        	
    	} catch (BusinessException bExp) {
    		
            throw new ControllerException(bExp);
            
        } catch (IllegalAccessException illegAccExp) {
        	
        	throw new ControllerException(illegAccExp);
        	
		} catch (InvocationTargetException invTarExp) {
			
			throw new ControllerException(invTarExp);
			
		} catch ( InvalidSessionException isExp ) {
        	
        	throw new ControllerException(isExp);
        	
        } 
    	        
    }
    
    /** Tarefa: Grava as informações enviadas do formulário de Cliente.
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
    	
    	DynaValidatorActionForm clienteForm = (DynaValidatorActionForm) form;
    	ActionMessages 			messagesSuccess    = new ActionMessages();
    	ActionMessages 			messagesWarning    = new ActionMessages();
    	ActionMessages 			messagesProblem    = new ActionMessages();
    	
    	boolean isFromCupom = clienteForm.getString("isFromCupom").equalsIgnoreCase("false") ? false : true;
    	
    	try {
    	
    		ClienteBusiness 	clienteBusiness 	= (ClienteBusiness) springContext.getBean("clienteBO");
    		EstadoDddBusiness 	estadoDddBusiness 	= (EstadoDddBusiness) springContext.getBean("estadoDddBO");
    		EstadoBusiness 		estadoBusiness 		= (EstadoBusiness) springContext.getBean("estadoBO");
	    	Cliente cliente = new Cliente();
	    	
	    	clienteForm.set("listCliente",  	listEstados());
	    	
	    	// Usuario logado
	    	SystemUser usuario = (Usuario) UserHelper.getBoundedUser(request.getSession());
	    	
	    	String tipoCliente = clienteForm.getString("pessoaFJ");
	    	
	    	String cpfOrCnpj = clienteForm.getString("cnpj");
	    	
	    	// Removemos a máscara, caso exista
			String newCpfOrCnpj = cpfOrCnpj.replaceAll("[^0-9]*","");
			
			// Verificamos se o CPF/CNPJ é válido e se é Pessoa Física ou Jurídica
				
			if ( "F".equalsIgnoreCase(tipoCliente) ) {
				
				if ( newCpfOrCnpj.length() != 11 )
					
					throw new BusinessRuleException("customer.error.invalidCPF");
				
				else
					
					cliente.setTipoCliente(Cliente.FISICA);
				
			} else {
				
				if ( newCpfOrCnpj.length() != 14 )
					
					throw new BusinessRuleException("customer.error.invalidCNPJ");
				
				else
					
					cliente.setTipoCliente(Cliente.JURIDICA);
			}
			
			// Validação do CEP
			Long cep = clienteBusiness.unmaskCepCpfCnpj( clienteForm.get("cep").toString() );
	    	
			if ( !clienteBusiness.isValidCep(cep) ) {
				
				throw new BusinessRuleException("customer.error.invalidCep");			
				
			}	
			
			// Validação do DDD		
			Long dddRes = null;
			Long dddCom = null;
			Long dddCel = null;
			boolean validDDD = true;
			String estadoStr 	= clienteForm.getString("estado");
			Estado estado 		= estadoBusiness.getByName(estadoStr);
			EstadoDdd dddTel    = null;
			
			if ( estado == null  ) {
				// Quando tentamos recuperar um objeto e ele retornar null, apresentamos esta mensagem
	    		messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("customer.error.state") );
	            super.addProblemMessages(request, messagesProblem);  
	            return mapping.findForward("_form");
	            
			}
			
			// DDD Residencial
			
			if ( clienteForm.getString("dddRes")!= null && !"".equals(clienteForm.getString("dddRes")) ) {
				
				dddRes = Long.valueOf(clienteForm.getString("dddRes"));
				dddTel = estadoDddBusiness.getByEstado(dddRes, (Long)estado.getEntityId());
				if ( dddTel == null || !dddTel.getDdd().equals(dddRes) ) {
					// Quando tentamos recuperar um objeto e ele retornar null, apresentamos esta mensagem
		    		messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("customer.error.invalidDDDRes") );
		            super.addProblemMessages(request, messagesProblem);  
		            validDDD = false;
		            
				} 
			}
			
			// DDD Comercial
			if ( clienteForm.getString("dddCom") != null && !"".equals(clienteForm.getString("dddCom")) ) {
				dddCom = Long.valueOf(clienteForm.getString("dddCom"));
				dddTel = estadoDddBusiness.getByEstado(dddCom, (Long)estado.getEntityId());
				if ( dddTel == null || !dddTel.getDdd().equals(dddCom) ) {
					// Quando tentamos recuperar um objeto e ele retornar null, apresentamos esta mensagem
		    		messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("customer.error.invalidDDDCom") );
		            super.addProblemMessages(request, messagesProblem);  
		            validDDD = false;
				}
			}
			
			// DDD Celular
			if ( clienteForm.getString("dddCel") != null && !"".equals(clienteForm.getString("dddCel")) ) {
				dddCel = Long.valueOf(clienteForm.getString("dddCel"));
				dddTel = estadoDddBusiness.getByEstado(dddCel, (Long)estado.getEntityId());
				if ( dddTel == null || !dddTel.getDdd().equals(dddCel) ) {
					// Quando tentamos recuperar um objeto e ele retornar null, apresentamos esta mensagem
		    		messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("customer.error.invalidDDDCel") );
		            super.addProblemMessages(request, messagesProblem);  
		            validDDD = false;
				}
			}
			
			if ( !validDDD )
				return mapping.findForward("_form");
			
			
			/*
			 * Validacao dos campos: Email, Data de Nascimento e Sexo 
			 * Demanda 00003
			 * Data: 29/07/2012
			 * Resource IT 
			 */
			
			//Tratamento do email
			if ("".equals(clienteForm.getString("email")))
				throw new BusinessRuleException("customer.error.requiredEmail");
			
			// Tratamento da Data de Nascimento
			if (cliente.getTipoCliente().equals(Cliente.FISICA)) {
				
				//Data de Nascimento
				if ("".equals(clienteForm.getString("dataNascimento")))
					throw new BusinessRuleException("customer.error.requiredDtNascimento");
				
				cliente.setDataNascimento("".equals(clienteForm.getString("dataNascimento")) ? 
 					   null :
 					   DateUtils.format(clienteForm.getString("dataNascimento"),"dd/MM/yyyy"));
				
				//Sexo
				
				if ("".equals(clienteForm.getString("sexo")))
					throw new BusinessRuleException("customer.error.requiredSexo");
				
				cliente.setSexo (clienteForm.getString("sexo"));
			}
			
	    	
			
	    	cliente.setEntityId        ( clienteForm.get("entityId")		  );
	    	//cliente.setTipoCliente	   ( clienteForm.getString("pessoaFJ")    );
	    	cliente.setNome            ( clienteForm.getString("nome")        );
	    	cliente.setEndereco        ( clienteForm.getString("endereco")    );
	    	cliente.setBairro          ( clienteForm.getString("bairro")      );
	    	cliente.setCidade          ( clienteForm.getString("cidade")      );
	    	cliente.setCep             ( cep 								  );
	    	cliente.setCnpj            ( clienteBusiness.unmaskCepCpfCnpj( clienteForm.get("cnpj").toString() ) );
	    	cliente.setEmail           ( clienteForm.getString("email")       );
	    	cliente.setEstado          ( estadoStr	  );
	    	cliente.setTelefoneRes     ( clienteForm.getString("telefoneRes") );
	    	cliente.setTelefoneCom     ( clienteForm.getString("telefoneCom") );
	    	cliente.setTelefoneCel     ( clienteForm.getString("telefoneCel") );
	    	cliente.setDddRes		   (dddRes);
	    	cliente.setDddCom		   (dddCom);
	    	cliente.setDddCel		   (dddCel);	    	

            ControllerHelper.prepare( cliente, (Long)usuario.getIdentifier() );
	    	
	    	clienteBusiness.save( cliente );

            // O cliente foi salvo sem problemas. Iremos direcionar o usuário para
            // a página de formulário porém devemos definir qual alerta será exibido.
            //
            // Se o cliente possui informações de contato, retornamos uma mensagem de
            // sucesso. Caso contrário um alerta indicando que o cliente foi salvo,
            // porém sem informações de contato.
            if ( clienteBusiness.hasContactInformation(cliente) ) {
                
            	messagesSuccess.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("form.msg.saved") );
                super.addSuccessMessages(request, messagesSuccess);
                
            } else {

            	messagesWarning.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("customer.msg.noContact") );
                super.addWarningMessages(request, messagesWarning);

            }
           
            // Verificamos se devemos retornar para o cadastro de cupom.
            if ( !isFromCupom ) {            	
            	
            	return mapping.findForward("_form");
            	
            } else {
            	
            	// Atualizamos o objteo cupomTO na sessão
            	CupomTO cupomTO = YmSessionHelper.getCupomTOFromSession(request);
            	cupomTO.setCnpj(cliente.getCnpj().toString());
            	cupomTO.setClienteNome(cliente.getNome());
            	
            	YmSessionHelper.setCupomTOToSession(request, cupomTO);
            	
            	return mapping.findForward("_cupom");
            }
         
    	} catch ( BusinessRuleException brExp ) {
    	    	
         	messagesProblem.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( brExp.getMessage() ) );
            super.addProblemMessages(request, messagesProblem);
             
            return mapping.findForward("_form");
             
    	} catch (BusinessException bExp) {
                
            throw new ControllerException(bExp);
                
    	} catch ( InvalidSessionException isExp ) {
        	
        	throw new ControllerException(isExp);
        	
        } 

    }   
    
    /** Tarefa: Carrega as informações de um cliente para formulário de Cliente.
     * 			Recebe o id do Cliente como parâmetro
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
    	try {
    	
	    	DynaValidatorActionForm clienteForm = (DynaValidatorActionForm) form;
	    	
	    	long entityId = Long.parseLong(request.getParameter("entityId"));
	    	
	    	ClienteBusiness clienteBusiness = (ClienteBusiness) springContext.getBean("clienteBO");
	    	Cliente         cliente         = (Cliente) clienteBusiness.get( new Long(entityId) );
	    	
	    	// Usuario logado
	    	SystemUser usuario = (Usuario) UserHelper.getBoundedUser(request.getSession());
	    	
	    	/* 
	    	 * Demanda 00003 - Resource IT
	    	 * Formatando a data de nascimento
	    	 */
    		String dataNascimentoCorrigida = "";
	    	if (cliente.getDataNascimento() != null) {
		    	dataNascimentoCorrigida = new SimpleDateFormat("dd/MM/yyyy").format(cliente.getDataNascimento());
	    	}
	    	
	    	BeanUtils.copyProperties(clienteForm, cliente);
	    	clienteForm.set("pessoaFJ", cliente.getTipoCliente());
	    	clienteForm.set("listCliente",  	listEstados());
	    	clienteForm.set("dataNascimento", dataNascimentoCorrigida);
	    	
	    	ControllerHelper.prepare( cliente, (Long)usuario.getIdentifier() );
	    	
            return mapping.findForward("_form");
	        
    	} catch (BusinessException bExp) {
    		
            throw new ControllerException(bExp);
            
        } catch (IllegalAccessException illegAccExp) {
        	
        	throw new ControllerException(illegAccExp);
        	
		} catch (InvocationTargetException invTarExp) {
			
			throw new ControllerException(invTarExp);
			
		} catch ( InvalidSessionException isExp ) {
        	
        	throw new ControllerException(isExp);
        	
        } 
    	
    }
    
    
    /** Tarefa: Carrega as informações de um cliente para formulário de Cliente.
     * 			Recebe chassi como parâmetro
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
    public ActionForward editByChassi( ActionMapping mapping
                             , ActionForm form
                             , HttpServletRequest request
                             , HttpServletResponse response) throws ControllerException {
    	try {
    	
	    	DynaValidatorActionForm clienteForm = (DynaValidatorActionForm) form;
	    	
	    	String chassi = request.getParameter("chassi");
	    	
	    	ClienteBusiness clienteBusiness = (ClienteBusiness) springContext.getBean("clienteBO");
	    	
	    	// Usuario logado
	    	Usuario usuario = (Usuario) UserHelper.getBoundedUser(request.getSession());

	    	// recupera um cliente de acordo com um chassi e concessionária
	    	Cliente         cliente         = (Cliente) clienteBusiness.getByChassi( chassi, usuario.getConcessionaria() );
	    	clienteForm.set("listCliente",  	listEstados());	    	
	    	if ( cliente != null ) {
	    	
	    		//log.info(" Cliente localizado:" + cliente.getNome());
	    		/* 
		    	 * Demanda 00003 - Resource IT
		    	 * Formatando a data de nascimento
		    	 */
	    		String dataNascimentoCorrigida = "";
		    	if (cliente.getDataNascimento() != null) {
			    	dataNascimentoCorrigida = new SimpleDateFormat("dd/MM/yyyy").format(cliente.getDataNascimento());
		    	}
	    		BeanUtils.copyProperties(clienteForm, cliente);
	    		clienteForm.set("pessoaFJ", cliente.getTipoCliente());	    	
	    		clienteForm.set("dataNascimento", dataNascimentoCorrigida);
	    		ControllerHelper.prepare( cliente, (Long)usuario.getIdentifier() );	    		    	
	    		
	    		return mapping.findForward("_form");
	    		
	    	} else {
	    		
	    		//log.info(" Cliente não localizado!");
	    		
	    		this.setAlertMessage(request, "Cliente não localizado!");
	    		
	    		return mapping.findForward("_search");
	    		
	    	}
	        
    	} catch (BusinessException bExp) {
    		
            throw new ControllerException(bExp);
            
        } catch (IllegalAccessException illegAccExp) {
        	
        	throw new ControllerException(illegAccExp);
        	
		} catch (InvocationTargetException invTarExp) {
			
			throw new ControllerException(invTarExp);
			
		} catch ( InvalidSessionException isExp ) {
        	
        	throw new ControllerException(isExp);
        	
        } 
    	
    }
    
    /** Tarefa: Carrega as informações de um cliente para formulário de Cliente.
     * 			Recebe o cpf ou cnpj como parâmetro
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
    public ActionForward editByCnpj( ActionMapping mapping
                             , ActionForm form
                             , HttpServletRequest request
                             , HttpServletResponse response) throws ControllerException {
    	try {
    	
	    	DynaValidatorActionForm clienteForm = (DynaValidatorActionForm) form;
	    	
	    	String cpfCnpj = request.getParameter("cnpj");
	    	
	    	ClienteBusiness clienteBusiness = (ClienteBusiness) springContext.getBean("clienteBO");
	    	
	    	// Usuario logado
	    	Usuario usuario = (Usuario) UserHelper.getBoundedUser(request.getSession());

	    	// recupera um cliente de acordo com um chassi e concessionária
	    	Cliente         cliente         = (Cliente) clienteBusiness.getByCpfCnpj(Long.valueOf(cpfCnpj));
	    		
	    	if ( cliente != null ) {
	    	
	    		//log.info(" Cliente localizado:" + cliente.getNome());
	    		
		    	/* 
		    	 * Demanda 00003 - Resource IT
		    	 * Formatando a data de nascimento
		    	 */
	    		String dataNascimentoCorrigida = "";
		    	if (cliente.getDataNascimento() != null) {
			    	dataNascimentoCorrigida = new SimpleDateFormat("dd/MM/yyyy").format(cliente.getDataNascimento());
		    	}
	    		
	    		BeanUtils.copyProperties(clienteForm, cliente);
	    		clienteForm.set("pessoaFJ", 	cliente.getTipoCliente());	
	    		clienteForm.set("listCliente",	listEstados());	    
	    		clienteForm.set("dataNascimento", dataNascimentoCorrigida);
	    		
	    		ControllerHelper.prepare( cliente, (Long)usuario.getIdentifier() );	    		    	
	    		
	    		return mapping.findForward("_form");
	    		
	    	} else {
	    		
	    		//log.info(" Cliente não localizado!");
	    		clienteForm.set("listCliente",  	listEstados());	    
	    		this.setAlertMessage(request, "Cliente não localizado!");
	    		
	    		return mapping.findForward("_search");
	    		
	    	}
	        
    	} catch (BusinessException bExp) {
    		
            throw new ControllerException(bExp);
            
        } catch (IllegalAccessException illegAccExp) {
        	
        	throw new ControllerException(illegAccExp);
        	
		} catch (InvocationTargetException invTarExp) {
			
			throw new ControllerException(invTarExp);
			
		} catch ( InvalidSessionException isExp ) {
        	
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

        try {
        	
        	ClienteBusiness clienteBusiness = (ClienteBusiness) springContext.getBean("clienteBO");
        	Usuario usuario = (Usuario) UserHelper.getBoundedUser(request.getSession());
        	
        	List list = clienteBusiness.listByConcessionaria(usuario.getConcessionaria());
        	
        	
            DynaActionForm actionForm = (DynaValidatorActionForm) form;
            actionForm.initialize( mapping );
            actionForm.set( "task"        , "list");
            actionForm.set( "listResults" , list );
            
            return mapping.findForward("_list");

        } catch (BusinessException bExp) {
            
            throw new ControllerException(bExp);
            
        } catch ( InvalidSessionException isExp ) {
    		
    		throw new ControllerException(isExp);
    		
		}  
        
    }    
    
    /** Tarefa: Pesquisa.
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
    public ActionForward search( ActionMapping mapping
                               , ActionForm form
                               , HttpServletRequest request
                               , HttpServletResponse response) throws ControllerException {
        
        return mapping.findForward("_search");
        
    }
    
    /**
     * Lista de estados
     * 
     * @return List estados
     * 
     * @throws ControllerException
     */
    public List listEstados() throws ControllerException {
    	
    	try {
    		EstadoBusiness 		estadoBusiness 		= (EstadoBusiness) springContext.getBean("estadoBO");
    		return estadoBusiness.listAll();
    	} catch (BusinessException bExp) {
            
            throw new ControllerException(bExp);
            
        }
    }
    
}
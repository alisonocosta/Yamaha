/* Resource Tecnologia
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......DocumentoBusiness.java
 *   .: Criação.....28 de Agosto, 10:38
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Objeto de negócios para a entidade "Documento".
 */
package br.com.yamaha.sistemagarantia.business;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import br.com.resource.infra.business.BusinessObject;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.controller.helper.ControllerHelper;
import br.com.yamaha.sistemagarantia.controller.helper.FileHelper;
import br.com.yamaha.sistemagarantia.dao.DocumentoDao;
import br.com.yamaha.sistemagarantia.model.AlertGarantia;
import br.com.yamaha.sistemagarantia.model.Documento;
import br.com.yamaha.sistemagarantia.model.DocumentoLog;
import br.com.yamaha.sistemagarantia.model.ParametroSistema;
import br.com.yamaha.sistemagarantia.model.Usuario;

/** Classe de negócios relacionada à entidade <b>Documento</b>.
 * 
 *  @author Edson Luiz Sumensari
 */
public class DocumentoBusiness extends BusinessObject {

	/** Objeto DAO utilizado para este objeto de negócios. */
	private DocumentoDao documentoDao;	 
	
	/**
	 *  Objeto transactionTemplate para controle de transação
	 */
	private TransactionTemplate transactionTemplate;


    /** Recupera um Documento pelo id.
     *
     *  @param entityId
     *      Referência da entidade na camada de persistência.
     *  @return
     *      Um objeto <code>Documento</code>.
     *      
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      será lançada. Caso hajam problemas de regras de negócio, uma
     *      BusinessRuleException (subclasse de BusinessException) será 
     *      lançada.
     */
    public Documento get(Serializable entityId) throws BusinessException {

        try {

            return (Documento)documentoDao.get(Documento.class, entityId);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    }
    
    /** Lista todo os arquivos existentes no banco de dados.
     *  @return
     *      Um objeto <code>List</code> contendo todos os 
     *      clientes persistentes na aplicação.
     *      
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      será lançada. Caso hajam problemas de regras de negócio, uma
     *      BusinessRuleException (subclasse de BusinessException) será 
     *      lançada.
     */
    public List list() throws BusinessException {

        try {

            return documentoDao.orderedList(Documento.class, 2, "dataCriacao");

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    }
    
    /** Lista os arquivos existentes no banco de dados expirados.
     *  @return
     *      Um objeto <code>List</code> contendo todos os 
     *      clientes persistentes na aplicação.
     *      
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      será lançada. Caso hajam problemas de regras de negócio, uma
     *      BusinessRuleException (subclasse de BusinessException) será 
     *      lançada.
     */
    public List listExpired() throws BusinessException {

        try {

            return documentoDao.listExpired();

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    }
    
    /** Obtém uma entidade de parametro do sistema a partir de um nome de parâmetro passado.
	 * 
	 *  @param parameterName
	 *      <code>Serializable<code>. Nome do parâmetro(Coluna) pelo qual o objeto
	 *      deverá ser pesquisado na camada de persistência da aplicação.

	 *  @return
	 *      Um <code>ParametroSistema</code> populado com os dados da 
	 *      entidade específica, solicitada, ou caso nenhuma entidade
	 *      seja encontrada, retornará <code>null</code>.
	 *      
	 *  @throws DaoException
	 *      Se houverem problemas nas camadas inferiores, estes serão 
	 *      convertidos para uma DaoException.
	 */     
	public ParametroSistema getByParameterSystem(String parameterName) throws BusinessException {

		try {

			return (ParametroSistema) documentoDao.getByParameter(ParametroSistema.class, "nomeParametro", parameterName);

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}    	

	}
    
    /** Salva ou atualiza o estado de uma entidade.
     *  <p>
     *  Se a entidade recebida não existir na camada de persistência
     *  da aplicação, será criada. Do contrário seus valores serão
     *  atualizados de acordo com as informações alteradas.
     *  <p>
     *  No entanto, antes de proceder com o processo de persistência
     *  algumas validações irão ocorrer. Se houverem problemas, serão
     *  lançados como <code>BusinessRuleExceptions</code>.
     *  
     *  @param documento
     *      <code>Documento</code> representando a entidade a ser
     *      criada/atualizada na camada de persistência.
     *      
     *  @param usuario
     *  	<code>Usuario</code> Entidade do Usuário.
     *      
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      será lançada. Caso hajam problemas de regras de negócio, uma
     *      BusinessRuleException (subclasse de BusinessException) será 
     *      lançada.
     */    
    public AlertGarantia save(Documento documento, Usuario usuario) throws BusinessException {
    	
    	AlertGarantia alert = new AlertGarantia();    	
    	
    	try {
    		
            if ( documento.isNew() ) {
            
            	Documento fileTaken = this.getByFilename( documento.getFilename() );
            	
                // Validação se já possui um arquivo cadastrado com o mesmo nome.
                if ( fileTaken != null ) {
                    alert.setAlertGarantiaKey("documento.msg.error.filename");
                    alert.setAlertGarantiaText("Já existe um arquivo cadastrado com este nome!");
                    alert.setParam(documento.getFilename());
                    return alert;
                }
            }
            
            this.writeFile(documento);
            
            // Se chegamos aqui, não houveram erros. Podemos
            // salvar o estoque na base de dados.
            documentoDao.makePersistent(documento);
            
            //Vamos Logar a Operação de remove
            DocumentoLog docLog = new DocumentoLog();
			docLog.setDocumento(documento);
			docLog.setUsuario(usuario);
			docLog.setOperacao(DocumentoLog.OPERACAO_UPLOAD);
			docLog.setObservacao(documento.getFilename());
			
			ControllerHelper.prepare( docLog, (Long)usuario.getEntityId() );
			
			documentoDao.makePersistent(docLog);
            
            alert.setAlertGarantiaKey("documento.msg.success.saved");
            alert.setAlertGarantiaText("O arquivo foi gravado com sucesso!");

        } catch ( DaoException daoExp ) {
        	
        	(FileHelper.createFullPatchedFile(documento.getFilename())).delete();
            throw new BusinessException( daoExp );

        } catch (FileNotFoundException fnfExp) {
            
        	alert.setAlertGarantiaKey("documento.msg.error.Permission");
            alert.setAlertGarantiaText("Não foi possível gravar o arquivo!");
            alert.setParam(fnfExp.getMessage());
            
        } catch (IOException ioExp) {
            
        	alert.setAlertGarantiaKey("documento.msg.error.Permission");
            alert.setAlertGarantiaText("Não foi possível gravar o arquivo!");
            alert.setParam(ioExp.getMessage());
            
        }
        
        return alert;

    }
    
    /** Cria um log para a operação de Download.
     *  <p>
     *  Se a entidade recebida não existir na camada de persistência
     *  da aplicação, será criada. Do contrário seus valores serão
     *  atualizados de acordo com as informações alteradas.
     *  <p>
     *  No entanto, antes de proceder com o processo de persistência
     *  algumas validações irão ocorrer. Se houverem problemas, serão
     *  lançados como <code>BusinessRuleExceptions</code>.
     *  
     *  @param documento
     *      <code>Documento</code> representando a entidade a ser
     *      criada/atualizada na camada de persistência.
     *      
     *  @param usuario
     *  	<code>Usuario</code> Entidade do Usuário.
     *      
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      será lançada. Caso hajam problemas de regras de negócio, uma
     *      BusinessRuleException (subclasse de BusinessException) será 
     *      lançada.
     */    
    public void saveLog(Documento documento, Usuario usuario) throws BusinessException {
    	
    	try{ 
    		
	    	//Vamos Logar a Operação de remove
	        DocumentoLog docLog = new DocumentoLog();
			docLog.setDocumento(documento);
			docLog.setUsuario(usuario);
			docLog.setOperacao(DocumentoLog.OPERACAO_DOWNLOAD);
			docLog.setObservacao(documento.getFilename());
			
			ControllerHelper.prepare( docLog, (Long)usuario.getEntityId() );
			
			documentoDao.makePersistent(docLog);
	    	
	    } catch ( DaoException daoExp ) {
	    	
	    	(FileHelper.createFullPatchedFile(documento.getFilename())).delete();
	        throw new BusinessException( daoExp );
	
	    }
    }
    
    /** Cria um arquivo físico na pasta parametrizada
     * 
     * @param documento
     * 	<code>Documento</code> - Entidade que contém o fluxo de bytes ( FormFile ) e os dados do arquivo
     * 
     * 
     * @throws BusinessException
     */
    public void writeFile(Documento documento) throws FileNotFoundException, IOException {
    	
		byte[] fileData    = documento.getFormFile().getFileData();
        int    fileSize    = documento.getFormFile().getFileSize();
        
        byte[] byteArr = new byte[fileSize];
        ByteArrayInputStream bytein = new ByteArrayInputStream(fileData);
        bytein.read(byteArr);

        ByteArrayOutputStream byteout = new ByteArrayOutputStream();
        byteout.write(byteArr);
        FileOutputStream fileOut = new FileOutputStream(FileHelper.createFullPatchedFile(documento.getFilename()));
       
        byteout.writeTo(fileOut);
        
        bytein.close();
        byteout.close();
        fileOut.close();
    }
    
    /** Obtém uma entidade de File a partir do filename do arquivo.
     * 
     *  @param filename
     *      <code>String</code> Parâmetro de pesquisa a ser utilizado na busca 
     *      do arquivo na camada de persistência.
     *  
     *  @return
     *      Uma entidade de Documento  ou <code>null</code>
     *      caso nenhum Documento seja encontrado.
     *  
     *  @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      será lançada. Caso hajam problemas de regras de negócio, uma
     *      BusinessRuleException (subclasse de BusinessException) será 
     *      lançada.
     */
    public Documento getByFilename( String filename )throws BusinessException {
    	
    	try {
            
            return (Documento)documentoDao.getByParameter(Documento.class, "filename", filename);
            
        } catch (DaoException dExp) {
            
            throw new BusinessException(dExp);
            
        }
    	
    	
    }
    
    /** Remove entidades de arquivo. 
     * 
     * @param lista de entidades
     *  <code>List</code> entidades de Arquivos.
     *  
     *  @parm entidade de Usuario
     *   <code>Usuario</code> Usuário que executou a operação.
     *  
     *  
     *  @return lista de alertas da operação 
     *     <code>List</code>.
     *  
     * @throws BusinessException
     *      Se houverem erros de execução ou nas camadas 
     *      abaixo dos serviços, serão encapsulados neste
     *      tipo de <code>Exception</code>.  
     */
    public List removeList(List listFile, Usuario usuario) throws BusinessException {
    
    	final List listFinal = listFile;
    	final Usuario usuarioFinal = usuario;
    	
    	List alertas = (List) transactionTemplate.execute(
                
	            new TransactionCallback() {
	                
	                public Object doInTransaction(TransactionStatus ts) {
	                	
	                	List 			alertas 	= new ArrayList();
	                	AlertGarantia 	alert 		= null;	                	
	                	boolean 		isRemoved 	= true;
	                	
	                	try {
	                		
	                		//System.out.println("Vamos iterar coma lista.");	                		
	                		Documento documento = null;
	                		DocumentoLog docLog = null;
	                		Iterator itF = listFinal.iterator();
	                		while( itF.hasNext() ) {
	                			
	                			documento = (Documento)itF.next();
	                			
	                			//Vamos Logar a Operação de remove
	                			docLog = new DocumentoLog();
	                			docLog.setDocumento(documento);
	                			docLog.setUsuario(usuarioFinal);
	                			docLog.setOperacao(DocumentoLog.OPERACAO_REMOVE);
	                			docLog.setObservacao(documento.getFilename());
	                			
	                			ControllerHelper.prepare( docLog, (Long)usuarioFinal.getEntityId() );
	                			
	                			documentoDao.makePersistent(docLog);
	                			
	                			// Agora Removemos a entidade
	                			documentoDao.makeTransient(documento);	                			
	                			
	                			alert = new AlertGarantia();
	                			alert.setAlertGarantiaKey("documento.msg.success.removed");
		                		alert.setParam(documento.getFilename());
		                		alertas.add(alert);
	                		}	                		
	                		
	                	} catch ( DaoException bExp ) {
							
							ts.setRollbackOnly();
							
							isRemoved = false;
							
							alert = new AlertGarantia();
							alert.setAlertGarantiaKey("documento.msg.error.notRem");
							alert.setAlertGarantiaText("A operação não pode ser realizada! Problema:" + bExp.getMessage());
							alert.setParam(bExp.getMessage());
							alertas = new ArrayList();
							alertas.add(alert);
							//System.out.println(" ----------> Rollback - Erro: " + bExp.getMessage());
							
						} 
	                	
	                	if ( isRemoved ) {
	                		          
	                		//System.out.println("Removendo físico!");
		                	//Remoção física do arquivo
		                	Iterator itF = listFinal.iterator();
		                	
	                		while( itF.hasNext() ) {
	                			Documento documento = (Documento)itF.next();
	                			(FileHelper.createFullPatchedFile(documento.getFilename())).delete();
	                		}
	                		
	                	}
	                	
	                	return alertas;
	                    
	                }
	                
	            }	                
        );
        
        return alertas;		
    	
    }
    
    /** Alterar apenas o registro da entidade do arquivo. 
     * 
     * @param Documento
     *  <code>Documento</code> entidades de documento.
     *  
     *  @param Usuario
     *   <code>Usuario</code> Entidade de Usuário.     * 
     *  
     * @throws BusinessException
     *      Se houverem erros de execução ou nas camadas 
     *      abaixo dos serviços, serão encapsulados neste
     *      tipo de <code>Exception</code>.  
     */
    public void update(Documento documento, Usuario usuario) throws BusinessException {
    	
    	try {
    		
    		if ( !documento.isNew() ) { 
    			
    			ControllerHelper.prepare( documento, (Long)usuario.getEntityId() );
    			
    			documentoDao.makePersistent(documento);
    			
    			//Vamos Logar a Operação de remove
    			DocumentoLog docLog = new DocumentoLog();
    			docLog.setDocumento(documento);
    			docLog.setUsuario(usuario);
    			docLog.setOperacao(DocumentoLog.OPERACAO_UPDATE);
    			docLog.setObservacao(documento.getFilename());
    			
    			ControllerHelper.prepare( docLog, (Long)usuario.getEntityId() );
    			
    			documentoDao.makePersistent(docLog);
    			
    		} else
    			throw new BusinessException("Não foi possivel atualizar a entidade. A entidade é nova!");
    	
    	} catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }
    	
    	
    }
    
    /** Remove entidade do arquivo. 
     * 
     * @param lista de entidades
     *  <code>List</code> entidades de documento.
     * 
     *  
     * @throws BusinessException
     *      Se houverem erros de execução ou nas camadas 
     *      abaixo dos serviços, serão encapsulados neste
     *      tipo de <code>Exception</code>.  
     */
    public void remove(Documento documento) throws BusinessException {
    	
    	try {
    		
    		documentoDao.makeTransient(documento);
    	
    	} catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }
    	
    	
    }
    
    /** Método getter para "documentoDao".
	 *  @return
	 *      <code>DocumentoDao</code>. O valor atual de documentoDao.
	 */
	public DocumentoDao getDocumentoDao() {
		return this.documentoDao;
	}

	/** Método setter para "documentoDao".
	 *  @param entityId
	 *      <code>DocumentoDao</code> a ser designado para documentoDao.
	 */
	public void setDocumentoDao(DocumentoDao documentoDao) {
		this.documentoDao = documentoDao;
	}  
	
	 /**
	 * Método getter para "transactionTemplate".
	 * 
	 * @return <code>TransactionTemplate</code>. O valor atual de
	 *         transactionTemplate.
	 */
    public TransactionTemplate getTransactionTemplate() {
        return this.transactionTemplate;
    }
    
    /**
	 * Método setter para "transactionTemplate".
	 * 
	 * @param transactionTemplate
	 *            <code>transactionTemplate</code> a ser designado para
	 *            transactionTemplate.
	 */
    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }
}
/* Resource Tecnologia
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......DocumentoBusiness.java
 *   .: Cria��o.....28 de Agosto, 10:38
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Objeto de neg�cios para a entidade "Documento".
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

/** Classe de neg�cios relacionada � entidade <b>Documento</b>.
 * 
 *  @author Edson Luiz Sumensari
 */
public class DocumentoBusiness extends BusinessObject {

	/** Objeto DAO utilizado para este objeto de neg�cios. */
	private DocumentoDao documentoDao;	 
	
	/**
	 *  Objeto transactionTemplate para controle de transa��o
	 */
	private TransactionTemplate transactionTemplate;


    /** Recupera um Documento pelo id.
     *
     *  @param entityId
     *      Refer�ncia da entidade na camada de persist�ncia.
     *  @return
     *      Um objeto <code>Documento</code>.
     *      
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
     *      BusinessRuleException (subclasse de BusinessException) ser� 
     *      lan�ada.
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
     *      clientes persistentes na aplica��o.
     *      
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
     *      BusinessRuleException (subclasse de BusinessException) ser� 
     *      lan�ada.
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
     *      clientes persistentes na aplica��o.
     *      
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
     *      BusinessRuleException (subclasse de BusinessException) ser� 
     *      lan�ada.
     */
    public List listExpired() throws BusinessException {

        try {

            return documentoDao.listExpired();

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    }
    
    /** Obt�m uma entidade de parametro do sistema a partir de um nome de par�metro passado.
	 * 
	 *  @param parameterName
	 *      <code>Serializable<code>. Nome do par�metro(Coluna) pelo qual o objeto
	 *      dever� ser pesquisado na camada de persist�ncia da aplica��o.

	 *  @return
	 *      Um <code>ParametroSistema</code> populado com os dados da 
	 *      entidade espec�fica, solicitada, ou caso nenhuma entidade
	 *      seja encontrada, retornar� <code>null</code>.
	 *      
	 *  @throws DaoException
	 *      Se houverem problemas nas camadas inferiores, estes ser�o 
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
     *  Se a entidade recebida n�o existir na camada de persist�ncia
     *  da aplica��o, ser� criada. Do contr�rio seus valores ser�o
     *  atualizados de acordo com as informa��es alteradas.
     *  <p>
     *  No entanto, antes de proceder com o processo de persist�ncia
     *  algumas valida��es ir�o ocorrer. Se houverem problemas, ser�o
     *  lan�ados como <code>BusinessRuleExceptions</code>.
     *  
     *  @param documento
     *      <code>Documento</code> representando a entidade a ser
     *      criada/atualizada na camada de persist�ncia.
     *      
     *  @param usuario
     *  	<code>Usuario</code> Entidade do Usu�rio.
     *      
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
     *      BusinessRuleException (subclasse de BusinessException) ser� 
     *      lan�ada.
     */    
    public AlertGarantia save(Documento documento, Usuario usuario) throws BusinessException {
    	
    	AlertGarantia alert = new AlertGarantia();    	
    	
    	try {
    		
            if ( documento.isNew() ) {
            
            	Documento fileTaken = this.getByFilename( documento.getFilename() );
            	
                // Valida��o se j� possui um arquivo cadastrado com o mesmo nome.
                if ( fileTaken != null ) {
                    alert.setAlertGarantiaKey("documento.msg.error.filename");
                    alert.setAlertGarantiaText("J� existe um arquivo cadastrado com este nome!");
                    alert.setParam(documento.getFilename());
                    return alert;
                }
            }
            
            this.writeFile(documento);
            
            // Se chegamos aqui, n�o houveram erros. Podemos
            // salvar o estoque na base de dados.
            documentoDao.makePersistent(documento);
            
            //Vamos Logar a Opera��o de remove
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
            alert.setAlertGarantiaText("N�o foi poss�vel gravar o arquivo!");
            alert.setParam(fnfExp.getMessage());
            
        } catch (IOException ioExp) {
            
        	alert.setAlertGarantiaKey("documento.msg.error.Permission");
            alert.setAlertGarantiaText("N�o foi poss�vel gravar o arquivo!");
            alert.setParam(ioExp.getMessage());
            
        }
        
        return alert;

    }
    
    /** Cria um log para a opera��o de Download.
     *  <p>
     *  Se a entidade recebida n�o existir na camada de persist�ncia
     *  da aplica��o, ser� criada. Do contr�rio seus valores ser�o
     *  atualizados de acordo com as informa��es alteradas.
     *  <p>
     *  No entanto, antes de proceder com o processo de persist�ncia
     *  algumas valida��es ir�o ocorrer. Se houverem problemas, ser�o
     *  lan�ados como <code>BusinessRuleExceptions</code>.
     *  
     *  @param documento
     *      <code>Documento</code> representando a entidade a ser
     *      criada/atualizada na camada de persist�ncia.
     *      
     *  @param usuario
     *  	<code>Usuario</code> Entidade do Usu�rio.
     *      
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
     *      BusinessRuleException (subclasse de BusinessException) ser� 
     *      lan�ada.
     */    
    public void saveLog(Documento documento, Usuario usuario) throws BusinessException {
    	
    	try{ 
    		
	    	//Vamos Logar a Opera��o de remove
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
    
    /** Cria um arquivo f�sico na pasta parametrizada
     * 
     * @param documento
     * 	<code>Documento</code> - Entidade que cont�m o fluxo de bytes ( FormFile ) e os dados do arquivo
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
    
    /** Obt�m uma entidade de File a partir do filename do arquivo.
     * 
     *  @param filename
     *      <code>String</code> Par�metro de pesquisa a ser utilizado na busca 
     *      do arquivo na camada de persist�ncia.
     *  
     *  @return
     *      Uma entidade de Documento  ou <code>null</code>
     *      caso nenhum Documento seja encontrado.
     *  
     *  @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
     *      BusinessRuleException (subclasse de BusinessException) ser� 
     *      lan�ada.
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
     *   <code>Usuario</code> Usu�rio que executou a opera��o.
     *  
     *  
     *  @return lista de alertas da opera��o 
     *     <code>List</code>.
     *  
     * @throws BusinessException
     *      Se houverem erros de execu��o ou nas camadas 
     *      abaixo dos servi�os, ser�o encapsulados neste
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
	                			
	                			//Vamos Logar a Opera��o de remove
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
							alert.setAlertGarantiaText("A opera��o n�o pode ser realizada! Problema:" + bExp.getMessage());
							alert.setParam(bExp.getMessage());
							alertas = new ArrayList();
							alertas.add(alert);
							//System.out.println(" ----------> Rollback - Erro: " + bExp.getMessage());
							
						} 
	                	
	                	if ( isRemoved ) {
	                		          
	                		//System.out.println("Removendo f�sico!");
		                	//Remo��o f�sica do arquivo
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
     *   <code>Usuario</code> Entidade de Usu�rio.     * 
     *  
     * @throws BusinessException
     *      Se houverem erros de execu��o ou nas camadas 
     *      abaixo dos servi�os, ser�o encapsulados neste
     *      tipo de <code>Exception</code>.  
     */
    public void update(Documento documento, Usuario usuario) throws BusinessException {
    	
    	try {
    		
    		if ( !documento.isNew() ) { 
    			
    			ControllerHelper.prepare( documento, (Long)usuario.getEntityId() );
    			
    			documentoDao.makePersistent(documento);
    			
    			//Vamos Logar a Opera��o de remove
    			DocumentoLog docLog = new DocumentoLog();
    			docLog.setDocumento(documento);
    			docLog.setUsuario(usuario);
    			docLog.setOperacao(DocumentoLog.OPERACAO_UPDATE);
    			docLog.setObservacao(documento.getFilename());
    			
    			ControllerHelper.prepare( docLog, (Long)usuario.getEntityId() );
    			
    			documentoDao.makePersistent(docLog);
    			
    		} else
    			throw new BusinessException("N�o foi possivel atualizar a entidade. A entidade � nova!");
    	
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
     *      Se houverem erros de execu��o ou nas camadas 
     *      abaixo dos servi�os, ser�o encapsulados neste
     *      tipo de <code>Exception</code>.  
     */
    public void remove(Documento documento) throws BusinessException {
    	
    	try {
    		
    		documentoDao.makeTransient(documento);
    	
    	} catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }
    	
    	
    }
    
    /** M�todo getter para "documentoDao".
	 *  @return
	 *      <code>DocumentoDao</code>. O valor atual de documentoDao.
	 */
	public DocumentoDao getDocumentoDao() {
		return this.documentoDao;
	}

	/** M�todo setter para "documentoDao".
	 *  @param entityId
	 *      <code>DocumentoDao</code> a ser designado para documentoDao.
	 */
	public void setDocumentoDao(DocumentoDao documentoDao) {
		this.documentoDao = documentoDao;
	}  
	
	 /**
	 * M�todo getter para "transactionTemplate".
	 * 
	 * @return <code>TransactionTemplate</code>. O valor atual de
	 *         transactionTemplate.
	 */
    public TransactionTemplate getTransactionTemplate() {
        return this.transactionTemplate;
    }
    
    /**
	 * M�todo setter para "transactionTemplate".
	 * 
	 * @param transactionTemplate
	 *            <code>transactionTemplate</code> a ser designado para
	 *            transactionTemplate.
	 */
    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }
}
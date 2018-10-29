/* Yamaha
 * Copyright (c) 2012 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......GarantiaArquivoBusiness.java
 *   .: Cria��o.....04 de Outubro, 10:38
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Objeto de neg�cios para a entidade "GarantiaArquivoBusiness".
 */
package br.com.yamaha.sistemagarantia.business;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import br.com.resource.infra.business.BusinessObject;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.DaoException;
import br.com.resource.infra.model.EntityObject;
import br.com.yamaha.sistemagarantia.business.exception.FileException;
import br.com.yamaha.sistemagarantia.controller.helper.ControllerHelper;
import br.com.yamaha.sistemagarantia.dao.GarantiaArquivoDao;
import br.com.yamaha.sistemagarantia.model.AlertGarantia;
import br.com.yamaha.sistemagarantia.model.GarantiaArquivo;
import br.com.yamaha.sistemagarantia.model.GarantiaArquivoMoto;
import br.com.yamaha.sistemagarantia.model.GarantiaArquivoNtc;
import br.com.yamaha.sistemagarantia.model.GarantiaHeader;
import br.com.yamaha.sistemagarantia.model.Linha;
import br.com.yamaha.sistemagarantia.model.ParametroSistema;
import br.com.yamaha.sistemagarantia.model.Usuario;
import br.com.yamaha.sistemagarantia.utils.FileUtils;

/** Classe de neg�cios relacionada � entidade <b>GarantiaArquivo</b>.
 * 
 *  @author Edson Luiz Sumensari
 */
public class GarantiaArquivoBusiness extends BusinessObject {

	/** Objeto DAO utilizado para este objeto de neg�cios. */
	private GarantiaArquivoDao garantiaArquivoDao;	 
	
	/**
	 *  Objeto transactionTemplate para controle de transa��o
	 */
	private TransactionTemplate transactionTemplate;


    /** Recupera um Documento pelo id.
     *
     *  @param entityId
     *      Refer�ncia da entidade na camada de persist�ncia.
     *  @return
     *      Um objeto <code>GarantiaArquivo</code>.
     *      
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
     *      BusinessRuleException (subclasse de BusinessException) ser� 
     *      lan�ada.
     */
    public GarantiaArquivo get(Serializable entityId) throws BusinessException {

        try {

            return (GarantiaArquivo)garantiaArquivoDao.get(GarantiaArquivo.class, entityId);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    }
    
    /** Recupera um Documento pelo id.
    *
    *  @param entityId
    *      Refer�ncia da entidade na camada de persist�ncia.
    *  @return
    *      Um objeto <code>byte[] </code>.
    *      
    * @throws BusinessException
    *      Se houverem problemas na camada de dados, uma BusinessException
    *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
    *      BusinessRuleException (subclasse de BusinessException) ser� 
    *      lan�ada.
    */
    public GarantiaArquivoMoto getArquivoMoto(GarantiaArquivo garantiaArquivo) throws BusinessException {

    	try {

    		List result = garantiaArquivoDao.listByParameter(GarantiaArquivoMoto.class, "garantiaArquivo", garantiaArquivo);

    		if(result != null && result.size() > 0)
    			return 	(GarantiaArquivoMoto)result.get(0);
    		else
    			return null;


    	} catch (DaoException dExp) {

    		throw new BusinessException(dExp);

    	}

    }
    
    /** Recupera um Documento pelo id.
     *
     *  @param entityId
     *      Refer�ncia da entidade na camada de persist�ncia.
     *  @return
     *      Um objeto <code>byte[] </code>.
     *      
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
     *      BusinessRuleException (subclasse de BusinessException) ser� 
     *      lan�ada.
     */
    public byte[]  getContentMoto(GarantiaArquivo garantiaArquivo) throws BusinessException {

    	try {
    		
    		return garantiaArquivoDao.getFileMoto(garantiaArquivo);
    		
    	} catch (DaoException dExp) {

    		throw new BusinessException(dExp);

    	}

    }
    
    /** Recupera um Documento pelo id.
    *
    *  @param entityId
    *      Refer�ncia da entidade na camada de persist�ncia.
    *  @return
    *      Um objeto <code>GarantiaArquivoNtc </code>.
    *      
    * @throws BusinessException
    *      Se houverem problemas na camada de dados, uma BusinessException
    *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
    *      BusinessRuleException (subclasse de BusinessException) ser� 
    *      lan�ada.
    */
   public GarantiaArquivoNtc  getArquivoNtc(GarantiaArquivo garantiaArquivo) throws BusinessException {

   	try {
   		List result = garantiaArquivoDao.listByParameter(GarantiaArquivoNtc.class, "garantiaArquivo", garantiaArquivo);

   		if(result != null && result.size() > 0)
   			return 	(GarantiaArquivoNtc)result.get(0);
   		else
   			return null;    		
   		
   	} catch (DaoException dExp) {

   		throw new BusinessException(dExp);

   	}

   }
    
    /** Recupera um Documento pelo id.
     *
     *  @param entityId
     *      Refer�ncia da entidade na camada de persist�ncia.
     *  @return
     *      Um objeto <code>byte[] </code>.
     *      
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
     *      BusinessRuleException (subclasse de BusinessException) ser� 
     *      lan�ada.
     */
    public byte[]  getContentNtc(GarantiaArquivo garantiaArquivo) throws BusinessException {

    	try {
    		
    		return garantiaArquivoDao.getFileNtc(garantiaArquivo); 
    		
    	} catch (DaoException dExp) {

    		throw new BusinessException(dExp);

    	}

    }
    
    /** Lista os arquivos existentes no banco de dados de acordo o id da garantia enviada.
     * 
     * @param Integer garatniaId
     * 
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
    public List listFile(Integer garantiaId) throws BusinessException {

        try {
 
            return garantiaArquivoDao.listFile(garantiaId);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    }
    
    /** Lista os arquivos existentes no banco de dados de acordo com a garantia enviada.
     * 
     * @param GarantiaHeader garantia
     * 
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
    public List listFileByGarantia(GarantiaHeader garantia) throws BusinessException {

        try {

            return garantiaArquivoDao.listByParameter(GarantiaArquivo.class, "garantia", garantia);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    }
    
    
    /** Obt�m uma entidade de parametro do sistema a partir de um nome de par�metro passado.
	 * 
	 *  @param parameterName
	 *      <code>String<code>. Nome do par�metro(Coluna) pelo qual o objeto
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

			return (ParametroSistema) garantiaArquivoDao.getByParameter(ParametroSistema.class, "nomeParametro", parameterName);

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
     *  @param garantiaArquivo
     *      <code>GarantiaArquivo</code> representando a entidade a ser
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
    public AlertGarantia save(GarantiaArquivo garantiaArquivo, Usuario usuario) throws BusinessException {
    	
    	AlertGarantia alert = new AlertGarantia();    	
    	
    	try {
            
    		//ParametroSistema param = this.getByParameterSystem(ParametroSistema.CAMINHO_ARQUIVOS_GARANTIA);
    		//garantiaArquivo.setFilePath(param.getValorParametro());
    		
    		//criando fisicamente o arquivo e recuperando o path real do sistema
    		//garantiaArquivo.setFilePath(this.writeFile(garantiaArquivo,usuario,param.getValorParametro()));
            
            // Se chegamos aqui, n�o houveram erros. Podemos
            // salvar os dados do arquivo na base de dados.
            garantiaArquivoDao.makePersistent(garantiaArquivo);
            	            
            alert.setAlertGarantiaKey("documento.msg.success.saved");
            alert.setAlertGarantiaText("O arquivo foi gravado com sucesso!");

        } catch ( DaoException daoExp ) {
        	
        	(FileUtils.getFileByName(garantiaArquivo.getFilePath())).delete();
        	
            throw new BusinessException( daoExp );

        }/* catch (FileNotFoundException fnfExp) {
            
        	alert.setAlertGarantiaKey("garantia.msg.error.Permission");
            alert.setAlertGarantiaText("N�o foi poss�vel gravar o arquivo!");
            alert.setParam(fnfExp.getMessage());
            
        } catch (IOException ioExp) {
            
        	alert.setAlertGarantiaKey("garantia.msg.error.Permission");
            alert.setAlertGarantiaText("N�o foi poss�vel gravar o arquivo!");
            alert.setParam(ioExp.getMessage());
            
        } catch (FileException flExp) {
            
        	alert.setAlertGarantiaKey("garantia.msg.error.Permission");
            alert.setAlertGarantiaText("N�o foi poss�vel gravar o arquivo!");
            alert.setParam(flExp.getMessage());
            
        }*/
        
        return alert;

    }   
    
    /** Salva ou atualiza um arquivo da Garantia
	 *  <p>
	 *  Este processo exige a unifica��o da transa��o, se cocorrer algum problema
	 *  o sistema realiza roolBack.
	 *  
	 *  @param garantiaArquivo
	 *      <code>garantiaArquivo</code> representando a entidade a ser
	 *      criada/atualizada na camada de persist�ncia.
	 *      
	 *  @param Usuario
	 *      <code>Usuario</code> representando a entidade a ser
	 *      criada/atualizada na camada de persist�ncia.
	 *      
	 * @throws BusinessException
	 *      Se houverem erros de execu��o ou nas camadas 
	 *      abaixo dos servi�os, ser�o encapsulados neste
	 *      tipo de <code>Exception</code>.
	 */    
	public AlertGarantia saveFile(GarantiaArquivo garantiaArquivo, Usuario usuario) throws BusinessException {
		
		// recuperamos as entidades
		final GarantiaArquivo garantiaArq 	= garantiaArquivo;
		final Usuario 		  user			= usuario;	
		
		AlertGarantia alerta = (AlertGarantia) transactionTemplate.execute(
                
	            new TransactionCallback() {
	                
	                public Object doInTransaction(TransactionStatus ts) {
	                    
	                	AlertGarantia alert = null;
		
						try {
							
							ControllerHelper.prepare( garantiaArq, (Long)user.getEntityId() );
							
							saveEntity(garantiaArq);
							
							if(Linha.TIPO_MOTOCICLETA.equals(garantiaArq.getLinhaId())) {								
								GarantiaArquivoMoto gAMoto = new GarantiaArquivoMoto();
								gAMoto.setGarantiaArquivo(garantiaArq);
								gAMoto.setContent(garantiaArq.getFormFile().getFileData());
								ControllerHelper.prepare( gAMoto, (Long)user.getEntityId() );
								saveEntity(gAMoto);								
							} else {								
								GarantiaArquivoNtc gANtc = new GarantiaArquivoNtc();
								gANtc.setGarantiaArquivo(garantiaArq);
								gANtc.setContent(garantiaArq.getFormFile().getFileData());
								ControllerHelper.prepare( gANtc, (Long)user.getEntityId() );
								saveEntity(gANtc);	
							}
			    			
			    			alert = new AlertGarantia();
			    			alert.setAlertGarantiaKey("documento.msg.success.saved");
			                alert.setAlertGarantiaText("O arquivo foi gravado com sucesso!");
							
							//System.out.println(" Pe�as salvas com sucesso!");
				
						} catch ( BusinessException bExp ) {
							
							ts.setRollbackOnly();
							
							alert = new AlertGarantia();
							alert.setAlertGarantiaKey("garantia.msg.error.Permission");
				            alert.setAlertGarantiaText("N�o foi poss�vel gravar o arquivo!");
				            alert.setParam(bExp.getMessage());
							
							System.out.println(" ----------> Rollback - Erro: " + bExp.getMessage());
							
						} catch (FileNotFoundException fne) {
							
							ts.setRollbackOnly();
							
							alert = new AlertGarantia();
							alert.setAlertGarantiaKey("garantia.msg.error.Permission");
				            alert.setAlertGarantiaText("N�o foi poss�vel gravar o arquivo!");
				            alert.setParam(fne.getMessage());	
				            
							System.out.println(" ----------> Rollback - Erro: " + fne.getMessage());
						} catch (IOException ioe) {
							
							ts.setRollbackOnly();
							
							alert = new AlertGarantia();
							alert.setAlertGarantiaKey("garantia.msg.error.Permission");
				            alert.setAlertGarantiaText("N�o foi poss�vel gravar o arquivo!");
				            alert.setParam(ioe.getMessage());	
				            
							System.out.println(" ----------> Rollback - Erro: " + ioe.getMessage());
						} 
						
						return alert;
        
	                }
	                
	            }
	                
	        );
	        
	        return alerta;		

	} 	
    
	/** Salva ou atualiza o estado de uma entidade relacionada a garantiaArquivo.
	 *  <p>
	 *  Se a entidade recebida n�o existir na camada de persist�ncia
	 *  da aplica��o, ser� criada. Do contr�rio seus valores ser�o
	 *  atualizados de acordo com as informa��es alteradas.
	 *  
	 *  @param entityObject
	 *      <code>EntityObject</code> representando a entidade a ser
	 *      criada/atualizada na camada de persist�ncia.
	 *      
	 *  @throws DaoException
	 *      Se houverem problemas nas camadas inferiores, estes ser�o 
	 *      convertidos para uma BusinessException.
	 */    
	public void saveEntity(EntityObject entityObject) throws BusinessException {
		
		try {

			garantiaArquivoDao.makePersistent(entityObject);
			
		} catch ( DaoException daoExp ) {

			throw new BusinessException( daoExp );

		}
	}
    
    /** Cria um arquivo f�sico na pasta parametrizada
     * 
     * @param GarantiaArquivo garantiaArquivo
     *  	<code>GarantiaArquivo</code> - Entidade que representa um arquivo da garantia
     * @param Usuario usuario
     * 	<code>Usuario</code> - Entidade que representa o usu�rio
     * 
     * 
     * @throws BusinessException
     */
    public String writeFile(GarantiaArquivo garantiaArquivo, Usuario usuario, String pathBase) throws FileNotFoundException, IOException, FileException {
    	
    	//Verificamos se o content type do arquivo � valido
    	if(!FileUtils.isValidFile(garantiaArquivo.getFormFile()))
    		throw new FileException("Arquivo inv�lido!");
    	
		byte[] fileData    = garantiaArquivo.getFormFile().getFileData();
        int    fileSize    = garantiaArquivo.getFormFile().getFileSize();
        
        File file = FileUtils.createFullPatchedByFileName(
        		(Long)usuario.getConcessionaria().getEntityId()
        		,(Integer)garantiaArquivo.getGarantia().getEntityId()
        		,pathBase
        		,garantiaArquivo.getFilename());
        
        byte[] byteArr = new byte[fileSize];
        ByteArrayInputStream bytein = new ByteArrayInputStream(fileData);
        bytein.read(byteArr);

        ByteArrayOutputStream byteout = new ByteArrayOutputStream();
        byteout.write(byteArr);
        FileOutputStream fileOut = new FileOutputStream(file);
       
        byteout.writeTo(fileOut);
        
        bytein.close();
        byteout.close();
        fileOut.close();
        
        return file.getPath();
    }
    
    /** Remove entidade do arquivo. 
     * 
     * @param Entidade que representa um arquivo de uma garantia
     *  <code>GarantiaArquivo</code> entidades de garantiaArquivo.
     * 
     *  
     * @throws BusinessException
     *      Se houverem erros de execu��o ou nas camadas 
     *      abaixo dos servi�os, ser�o encapsulados neste
     *      tipo de <code>Exception</code>.  
     */
    public void remove(GarantiaArquivo garantiaArquivo) throws BusinessException {
    	
    	try {
    		    		
    		if(Linha.TIPO_MOTOCICLETA.equals(garantiaArquivo.getLinhaId())){    			
    			GarantiaArquivoMoto gAMoto = this.getArquivoMoto(garantiaArquivo);
    			garantiaArquivoDao.makeTransient(gAMoto);
    		} else {    			
    			GarantiaArquivoNtc gANtc = this.getArquivoNtc(garantiaArquivo);
    			garantiaArquivoDao.makeTransient(gANtc);    			
    		}  		
    		
    		garantiaArquivoDao.makeTransient(garantiaArquivo);
    		
    	
    	} catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }
    	
    	
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
    
    /** M�todo getter para a propriedade garantiaArquivoDao
	 *
	 * @return garantiaArquivoDao do tipo GarantiaArquivoDao
	 *
	 */
	
	public GarantiaArquivoDao getGarantiaArquivoDao() {
		return garantiaArquivoDao;
	}

	/** M�todo setter para a propriedade garantiaArquivoDao
	 *
	 * @param garantiaArquivoDao 
	 *       <code>garantiaArquivoDao<code> a ser designado para GarantiaArquivoBusiness.java
	 *
	 */
	public void setGarantiaArquivoDao(GarantiaArquivoDao garantiaArquivoDao) {
		this.garantiaArquivoDao = garantiaArquivoDao;
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
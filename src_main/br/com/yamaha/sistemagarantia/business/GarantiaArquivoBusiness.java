/* Yamaha
 * Copyright (c) 2012 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......GarantiaArquivoBusiness.java
 *   .: Criação.....04 de Outubro, 10:38
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Objeto de negócios para a entidade "GarantiaArquivoBusiness".
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

/** Classe de negócios relacionada à entidade <b>GarantiaArquivo</b>.
 * 
 *  @author Edson Luiz Sumensari
 */
public class GarantiaArquivoBusiness extends BusinessObject {

	/** Objeto DAO utilizado para este objeto de negócios. */
	private GarantiaArquivoDao garantiaArquivoDao;	 
	
	/**
	 *  Objeto transactionTemplate para controle de transação
	 */
	private TransactionTemplate transactionTemplate;


    /** Recupera um Documento pelo id.
     *
     *  @param entityId
     *      Referência da entidade na camada de persistência.
     *  @return
     *      Um objeto <code>GarantiaArquivo</code>.
     *      
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      será lançada. Caso hajam problemas de regras de negócio, uma
     *      BusinessRuleException (subclasse de BusinessException) será 
     *      lançada.
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
    *      Referência da entidade na camada de persistência.
    *  @return
    *      Um objeto <code>byte[] </code>.
    *      
    * @throws BusinessException
    *      Se houverem problemas na camada de dados, uma BusinessException
    *      será lançada. Caso hajam problemas de regras de negócio, uma
    *      BusinessRuleException (subclasse de BusinessException) será 
    *      lançada.
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
     *      Referência da entidade na camada de persistência.
     *  @return
     *      Um objeto <code>byte[] </code>.
     *      
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      será lançada. Caso hajam problemas de regras de negócio, uma
     *      BusinessRuleException (subclasse de BusinessException) será 
     *      lançada.
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
    *      Referência da entidade na camada de persistência.
    *  @return
    *      Um objeto <code>GarantiaArquivoNtc </code>.
    *      
    * @throws BusinessException
    *      Se houverem problemas na camada de dados, uma BusinessException
    *      será lançada. Caso hajam problemas de regras de negócio, uma
    *      BusinessRuleException (subclasse de BusinessException) será 
    *      lançada.
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
     *      Referência da entidade na camada de persistência.
     *  @return
     *      Um objeto <code>byte[] </code>.
     *      
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      será lançada. Caso hajam problemas de regras de negócio, uma
     *      BusinessRuleException (subclasse de BusinessException) será 
     *      lançada.
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
     *      clientes persistentes na aplicação.
     *      
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      será lançada. Caso hajam problemas de regras de negócio, uma
     *      BusinessRuleException (subclasse de BusinessException) será 
     *      lançada.
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
     *      clientes persistentes na aplicação.
     *      
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      será lançada. Caso hajam problemas de regras de negócio, uma
     *      BusinessRuleException (subclasse de BusinessException) será 
     *      lançada.
     */
    public List listFileByGarantia(GarantiaHeader garantia) throws BusinessException {

        try {

            return garantiaArquivoDao.listByParameter(GarantiaArquivo.class, "garantia", garantia);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    }
    
    
    /** Obtém uma entidade de parametro do sistema a partir de um nome de parâmetro passado.
	 * 
	 *  @param parameterName
	 *      <code>String<code>. Nome do parâmetro(Coluna) pelo qual o objeto
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

			return (ParametroSistema) garantiaArquivoDao.getByParameter(ParametroSistema.class, "nomeParametro", parameterName);

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
     *  @param garantiaArquivo
     *      <code>GarantiaArquivo</code> representando a entidade a ser
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
    public AlertGarantia save(GarantiaArquivo garantiaArquivo, Usuario usuario) throws BusinessException {
    	
    	AlertGarantia alert = new AlertGarantia();    	
    	
    	try {
            
    		//ParametroSistema param = this.getByParameterSystem(ParametroSistema.CAMINHO_ARQUIVOS_GARANTIA);
    		//garantiaArquivo.setFilePath(param.getValorParametro());
    		
    		//criando fisicamente o arquivo e recuperando o path real do sistema
    		//garantiaArquivo.setFilePath(this.writeFile(garantiaArquivo,usuario,param.getValorParametro()));
            
            // Se chegamos aqui, não houveram erros. Podemos
            // salvar os dados do arquivo na base de dados.
            garantiaArquivoDao.makePersistent(garantiaArquivo);
            	            
            alert.setAlertGarantiaKey("documento.msg.success.saved");
            alert.setAlertGarantiaText("O arquivo foi gravado com sucesso!");

        } catch ( DaoException daoExp ) {
        	
        	(FileUtils.getFileByName(garantiaArquivo.getFilePath())).delete();
        	
            throw new BusinessException( daoExp );

        }/* catch (FileNotFoundException fnfExp) {
            
        	alert.setAlertGarantiaKey("garantia.msg.error.Permission");
            alert.setAlertGarantiaText("Não foi possível gravar o arquivo!");
            alert.setParam(fnfExp.getMessage());
            
        } catch (IOException ioExp) {
            
        	alert.setAlertGarantiaKey("garantia.msg.error.Permission");
            alert.setAlertGarantiaText("Não foi possível gravar o arquivo!");
            alert.setParam(ioExp.getMessage());
            
        } catch (FileException flExp) {
            
        	alert.setAlertGarantiaKey("garantia.msg.error.Permission");
            alert.setAlertGarantiaText("Não foi possível gravar o arquivo!");
            alert.setParam(flExp.getMessage());
            
        }*/
        
        return alert;

    }   
    
    /** Salva ou atualiza um arquivo da Garantia
	 *  <p>
	 *  Este processo exige a unificação da transação, se cocorrer algum problema
	 *  o sistema realiza roolBack.
	 *  
	 *  @param garantiaArquivo
	 *      <code>garantiaArquivo</code> representando a entidade a ser
	 *      criada/atualizada na camada de persistência.
	 *      
	 *  @param Usuario
	 *      <code>Usuario</code> representando a entidade a ser
	 *      criada/atualizada na camada de persistência.
	 *      
	 * @throws BusinessException
	 *      Se houverem erros de execução ou nas camadas 
	 *      abaixo dos serviços, serão encapsulados neste
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
							
							//System.out.println(" Peças salvas com sucesso!");
				
						} catch ( BusinessException bExp ) {
							
							ts.setRollbackOnly();
							
							alert = new AlertGarantia();
							alert.setAlertGarantiaKey("garantia.msg.error.Permission");
				            alert.setAlertGarantiaText("Não foi possível gravar o arquivo!");
				            alert.setParam(bExp.getMessage());
							
							System.out.println(" ----------> Rollback - Erro: " + bExp.getMessage());
							
						} catch (FileNotFoundException fne) {
							
							ts.setRollbackOnly();
							
							alert = new AlertGarantia();
							alert.setAlertGarantiaKey("garantia.msg.error.Permission");
				            alert.setAlertGarantiaText("Não foi possível gravar o arquivo!");
				            alert.setParam(fne.getMessage());	
				            
							System.out.println(" ----------> Rollback - Erro: " + fne.getMessage());
						} catch (IOException ioe) {
							
							ts.setRollbackOnly();
							
							alert = new AlertGarantia();
							alert.setAlertGarantiaKey("garantia.msg.error.Permission");
				            alert.setAlertGarantiaText("Não foi possível gravar o arquivo!");
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
	 *  Se a entidade recebida não existir na camada de persistência
	 *  da aplicação, será criada. Do contrário seus valores serão
	 *  atualizados de acordo com as informações alteradas.
	 *  
	 *  @param entityObject
	 *      <code>EntityObject</code> representando a entidade a ser
	 *      criada/atualizada na camada de persistência.
	 *      
	 *  @throws DaoException
	 *      Se houverem problemas nas camadas inferiores, estes serão 
	 *      convertidos para uma BusinessException.
	 */    
	public void saveEntity(EntityObject entityObject) throws BusinessException {
		
		try {

			garantiaArquivoDao.makePersistent(entityObject);
			
		} catch ( DaoException daoExp ) {

			throw new BusinessException( daoExp );

		}
	}
    
    /** Cria um arquivo físico na pasta parametrizada
     * 
     * @param GarantiaArquivo garantiaArquivo
     *  	<code>GarantiaArquivo</code> - Entidade que representa um arquivo da garantia
     * @param Usuario usuario
     * 	<code>Usuario</code> - Entidade que representa o usuário
     * 
     * 
     * @throws BusinessException
     */
    public String writeFile(GarantiaArquivo garantiaArquivo, Usuario usuario, String pathBase) throws FileNotFoundException, IOException, FileException {
    	
    	//Verificamos se o content type do arquivo é valido
    	if(!FileUtils.isValidFile(garantiaArquivo.getFormFile()))
    		throw new FileException("Arquivo inválido!");
    	
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
     *      Se houverem erros de execução ou nas camadas 
     *      abaixo dos serviços, serão encapsulados neste
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
	 * Método getter para "transactionTemplate".
	 * 
	 * @return <code>TransactionTemplate</code>. O valor atual de
	 *         transactionTemplate.
	 */
    public TransactionTemplate getTransactionTemplate() {
        return this.transactionTemplate;
    }
    
    /** Método getter para a propriedade garantiaArquivoDao
	 *
	 * @return garantiaArquivoDao do tipo GarantiaArquivoDao
	 *
	 */
	
	public GarantiaArquivoDao getGarantiaArquivoDao() {
		return garantiaArquivoDao;
	}

	/** Método setter para a propriedade garantiaArquivoDao
	 *
	 * @param garantiaArquivoDao 
	 *       <code>garantiaArquivoDao<code> a ser designado para GarantiaArquivoBusiness.java
	 *
	 */
	public void setGarantiaArquivoDao(GarantiaArquivoDao garantiaArquivoDao) {
		this.garantiaArquivoDao = garantiaArquivoDao;
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
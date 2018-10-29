/* Yamaha
 * Copyright (c) 2012 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......ClassificaGarantiaMotoBusiness.java
 *   .: Cria��o.....09 de Junho
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Objeto de neg�cios para a servi�os "Classifica��o t�cnica de Garantia".
 */
package br.com.yamaha.sistemagarantia.processo.business;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import br.com.resource.infra.business.BusinessObject;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.BusinessRuleException;
import br.com.resource.infra.exception.DaoException;
import br.com.resource.infra.model.EntityObject;
import br.com.yamaha.sistemagarantia.model.AlertGarantia;
import br.com.yamaha.sistemagarantia.model.GarantiaHeader;
import br.com.yamaha.sistemagarantia.model.Lote;
import br.com.yamaha.sistemagarantia.model.StatusGarantia;
import br.com.yamaha.sistemagarantia.model.StatusLote;
import br.com.yamaha.sistemagarantia.model.Usuario;
import br.com.yamaha.sistemagarantia.processo.dao.ClassificaGarantiaMotoDao;
import br.com.yamaha.sistemagarantia.processo.model.AnaliseGarantiaVO;
import br.com.yamaha.sistemagarantia.processo.model.ClassificaGarantiaMotoVO;
import br.com.yamaha.sistemagarantia.utils.ConstantAnaliseGarantia;
import br.com.yamaha.sistemagarantia.utils.ConstantClassificaGarantiaMoto;

/** Classe de neg�cios relacionada � servi�os <b>Classifica��o t�cnica de Garantia</b>.
 * 
 *  @author Edson Luiz Sumensari
 */
public class ClassificaGarantiaMotoBusiness extends BusinessObject {
	
	/** Constante. Quebra de linha para formata��o de hist�rico. */
	private static final String BREAKLINE = "\n";
	
	private static final String MSG_SUCCESS = "MSG_SUCCESS";
	
	private static final String MSG_PROBLEM = "MSG_PROBLEM";
	
	
    ClassificaGarantiaMotoDao classificaGarantiaMotoDao;
    
    /**
	 *  Objeto transactionTemplate para controle de transa��o
	 */
	private TransactionTemplate transactionTemplate;
	
    
    /** Lista de itens para classifica��o t�cnicade garantia
     * 
     * @param parametro  - valor de refer�ncia para pesquisa
     * @param criterio   - tipo de pesquisa
     * @param linhaId    - c�digo da linha de produtos do usu�rio
     * 
     * @return List AnaliseGarantiaVO
     * 
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
     *      BusinessRuleException (subclasse de BusinessException) ser� 
     *      lan�ada.
     */
    public List listByParametro(String parametro, String criterio, Long linhaId) throws BusinessException, BusinessRuleException  {

    	List results = null;
    	
        try {

        	if(criterio.equals(ConstantClassificaGarantiaMoto.TIPO_LOTE)) {
        		Integer loteId = null;        		
        		try {        		
        			loteId = Integer.valueOf(parametro);      
        		}catch(Exception exc) {
        			throw new BusinessRuleException("classificaGarantiaMoto.msg.problem.parametro");
        		}        		
        		results = classificaGarantiaMotoDao.listGarantiaByLote(loteId, linhaId);
        		
        	} else if(criterio.equals(ConstantClassificaGarantiaMoto.TIPO_GARANTIA)) {
        		Integer garantiaId = null;
        		try {
        			garantiaId = Integer.valueOf(parametro);        		
        			
        		}catch(Exception exc) {
        			throw new BusinessRuleException("classificaGarantiaMoto.msg.problem.parametro");
        		}        		
        		results = classificaGarantiaMotoDao.listGarantia(garantiaId, linhaId);
        		
        	} else if(criterio.equals(ConstantClassificaGarantiaMoto.TIPO_CHASSI)) { 
        		
        		results = classificaGarantiaMotoDao.listGarantiaByChassi(parametro, linhaId); 
        		
        	} else if(criterio.equals(ConstantClassificaGarantiaMoto.TIPO_CONCESSIONARIA)) { 
        		    		
	        	results = classificaGarantiaMotoDao.listGarantiaByConcessionaria(parametro, linhaId); 
	        		
        	} else if(criterio.equals(ConstantClassificaGarantiaMoto.TIPO_MODELO)) { 
        		
	        	results = classificaGarantiaMotoDao.listGarantiaByModelo(parametro, linhaId); 
	        		
        	} else if(criterio.equals(ConstantClassificaGarantiaMoto.KEY_PECA_CAUSADORA)) { 
        		
	        	results = classificaGarantiaMotoDao.listGarantiaByPecaCausadora(parametro, linhaId); 
	        		
        	} else if(criterio.equals(ConstantClassificaGarantiaMoto.TIPO_POR_LINHA)) { 
        		
	        	results = classificaGarantiaMotoDao.listGarantiaByLinha(linhaId); 
	        		
        	}  else {
        		throw new BusinessRuleException("classificaGarantiaMoto.msg.problem.criteria");
        	}
        	
            return results;

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    } 
    
    /** Recupera uma Garantia de acordo com ID
     * 
     * @param garantiaId
     * @return GarantiaHeader
     * @throws BusinessException
     */
	public GarantiaHeader getGarantia(Integer garantiaId)  throws BusinessException {
		
		try {
				return (GarantiaHeader) classificaGarantiaMotoDao.get(GarantiaHeader.class, garantiaId);
				
		} catch (DaoException dExp) {

	         throw new BusinessException(dExp);

	     }
		
	}
    
    /** Atualiza o status da SG e do lote de acordo com a Classifica��o T�cnica
	 * 
	 * @param Usuario usuario
	 * @param garantia
	 * @param String classificaCode
	 * @throws BusinessException
	 */
	public AlertGarantia classificarSG(Usuario usuario, GarantiaHeader garantia, String classificaCode)  throws BusinessException {
		
		AlertGarantia alerta = null;
		if(ClassificaGarantiaMotoVO.CLASSIFICA_DEVOLVER_COM_SOLICITACAO.equals(classificaCode)) { //Garantia Devolvida para Concession�ria ap�s classifica��o t�cnica
			garantia.setFlClassificacaoTecnica(GarantiaHeader.CLASSIFICACAO_TEC_SIM);
			alerta = this.alterarStatus(usuario, garantia, StatusGarantia.STATUS_DIGITACAO, StatusLote.STATUS_EM_DIGITACAO);
			if(alerta != null) {
				
				if(MSG_SUCCESS.equals(alerta.getAlertGarantiaKey())) {
					alerta.setAlertGarantiaKey("classificaGarantiaMoto.msg.success.devolver");
				} else 	if(MSG_PROBLEM.equals(alerta.getAlertGarantiaKey())) {
					alerta.setAlertGarantiaKey("classificaGarantiaMoto.msg.error.devolver");
				}			
			}
		} else if(ClassificaGarantiaMotoVO.CLASSIFICA_LIBERAR_COM_CLASSIFICA.equals(classificaCode)) { //Garantia liberada ap�s classifica��o t�cnica
			garantia.setFlClassificacaoTecnica(GarantiaHeader.CLASSIFICACAO_TEC_SIM);
			alerta =  this.alterarStatus(usuario, garantia, StatusGarantia.STATUS_AGUARDANDO_DIGITACAO_NOTA, StatusLote.STATUS_AGUARDANDO_DIGITACAO_NOTA);
			
			if(alerta != null) {
				
				if(MSG_SUCCESS.equals(alerta.getAlertGarantiaKey())) {
					alerta.setAlertGarantiaKey("classificaGarantiaMoto.msg.success.liberar.com.classica");
				} else 	if(MSG_PROBLEM.equals(alerta.getAlertGarantiaKey())) {
					alerta.setAlertGarantiaKey("classificaGarantiaMoto.msg.error.liberar.com.classica");
				}			
			}
		} else if(ClassificaGarantiaMotoVO.CLASSIFICA_LIBERAR_SEM_CLASSIFICA.equals(classificaCode)) { //Garantia liberada  sem classifica��o t�cnica
			garantia.setFlClassificacaoTecnica(GarantiaHeader.CLASSIFICACAO_TEC_NAO);
			alerta =  this.alterarStatus(usuario, garantia, StatusGarantia.STATUS_AGUARDANDO_DIGITACAO_NOTA, StatusLote.STATUS_AGUARDANDO_DIGITACAO_NOTA);
			if(alerta != null) {
				
				if(MSG_SUCCESS.equals(alerta.getAlertGarantiaKey())) {
					alerta.setAlertGarantiaKey("classificaGarantiaMoto.msg.success.liberar.sem.classica");
				} else 	if(MSG_PROBLEM.equals(alerta.getAlertGarantiaKey())) {
					alerta.setAlertGarantiaKey("classificaGarantiaMoto.msg.error.liberar.sem.classica");
				}			
			}
		}
		
		return alerta;
	}
	
    
    /** Altera o status da garantia e do lote  analisados
	 * 
	 * @param Usuario usuario
	 * @param GarantiaHeader garantia
	 * @param statusGarantiaId
	 * @param statusLoteId
	 * @throws BusinessException
	 */
	private AlertGarantia alterarStatus(Usuario usuario, GarantiaHeader garantia, Long statusGarantiaId, Long statusLoteId)  throws BusinessException {
			
		Lote 		   lote     = garantia.getLote();

		StatusGarantia statusSg = new StatusGarantia();
		statusSg.setEntityId(statusGarantiaId);

		StatusLote statusLote = new StatusLote();
		statusLote.setEntityId(statusLoteId);

		garantia.setStatusGarantia(statusSg);
		garantia.setAtualizadoPor((Long)usuario.getEntityId());
		garantia.setDataAtualizacao(new Date());

		lote.setStatusLote(statusLote);
		lote.setAtualizadoPor((Long)usuario.getEntityId());
		lote.setDataAtualizacao(new Date());

		final GarantiaHeader garantiaFinal 	= garantia;
		final Lote			 loteFinal		= lote;	

		AlertGarantia alerta = (AlertGarantia) transactionTemplate.execute(

				new TransactionCallback() {

					public Object doInTransaction(TransactionStatus ts) {

						AlertGarantia alert = null;

						try {
							saveEntity(garantiaFinal);
							saveEntity(loteFinal);

							alert = new AlertGarantia();
							alert.setAlertGarantiaKey(MSG_SUCCESS);
							
						} catch ( BusinessException bExp ) {

							ts.setRollbackOnly();
							alert = new AlertGarantia();
							alert.setAlertGarantiaKey(MSG_PROBLEM);

							System.out.println(" ----------> Rollback - Erro: " + bExp.getMessage());

						} 

						return alert;

					}

				}

				);
		return alerta;		

	}
	
	/** Salva ou atualiza o estado de uma entidade relacionada a garantia.
	 *  <p>
	 *  Se a entidade recebida n�o existir na camada de persist�ncia
	 *  da aplica��o, ser� criada. Do contr�rio seus valores ser�o
	 *  atualizados de acordo com as informa��es alteradas.
	 *  
	 *  @param entityObject
	 *      <code>EntityObject</code> representando a entidade a ser
	 *      criada/atualizada na camada de persist�ncia.
	 *      
	 *  @throws BusinessException
	 *      Se houverem problemas nas camadas inferiores, estes ser�o 
	 *      convertidos para uma BusinessException.
	 */    
	public void saveEntity(EntityObject entityObject) throws BusinessException {
		
		try {

			classificaGarantiaMotoDao.makePersistent(entityObject);
			
		} catch ( DaoException daoExp ) {

			throw new BusinessException( daoExp );

		}
	}

	

	/** M�todo getter para a propriedade classificaGarantiaMotoDao
	 *
	 * @return classificaGarantiaMotoDao do tipo ClassificaGarantiaMotoDao
	 *
	 */
	
	public ClassificaGarantiaMotoDao getClassificaGarantiaMotoDao() {
		return classificaGarantiaMotoDao;
	}

	/** M�todo setter para a propriedade classificaGarantiaMotoDao
	 *
	 * @param classificaGarantiaMotoDao 
	 *       <code>classificaGarantiaMotoDao<code> a ser designado para ClassificaGarantiaMotoBusiness.java
	 *
	 */
	public void setClassificaGarantiaMotoDao(
			ClassificaGarantiaMotoDao classificaGarantiaMotoDao) {
		this.classificaGarantiaMotoDao = classificaGarantiaMotoDao;
	}

	/** M�todo getter para a propriedade transactionTemplate
	 *
	 * @return transactionTemplate do tipo TransactionTemplate
	 *
	 */
	
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	/** M�todo setter para a propriedade transactionTemplate
	 *
	 * @param transactionTemplate 
	 *       <code>transactionTemplate<code> a ser designado para AnaliseGarantiaBusiness.java
	 *
	 */
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
    
}
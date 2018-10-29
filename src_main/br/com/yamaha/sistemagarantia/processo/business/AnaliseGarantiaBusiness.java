/* Yamaha
 * Copyright (c) 2012 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......AnaliseGarantiaBusiness.java
 *   .: Criação.....17 de dezembro, 21:49
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Objeto de negócios para a serviços "Analise de Garantia".
 */
package br.com.yamaha.sistemagarantia.processo.business;

import java.util.Date;
import java.util.Iterator;
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
import br.com.yamaha.sistemagarantia.model.Linha;
import br.com.yamaha.sistemagarantia.model.Lote;
import br.com.yamaha.sistemagarantia.model.StatusGarantia;
import br.com.yamaha.sistemagarantia.model.StatusLote;
import br.com.yamaha.sistemagarantia.model.Usuario;
import br.com.yamaha.sistemagarantia.processo.dao.AnaliseGarantiaDao;
import br.com.yamaha.sistemagarantia.processo.model.AnaliseGarantiaVO;
import br.com.yamaha.sistemagarantia.processo.model.HistAnaliseObservacao;
import br.com.yamaha.sistemagarantia.processo.model.HistAnaliseParecerTecnico;
import br.com.yamaha.sistemagarantia.utils.ConstantAnaliseGarantia;

/** Classe de negócios relacionada à serviços <b>Analise de Garantia</b>.
 * 
 *  @author Edson Luiz Sumensari
 */
public class AnaliseGarantiaBusiness extends BusinessObject {
	
	/** Constante. Quebra de linha para formatação de histórico. */
	private static final String BREAKLINE = "\n";
	
	private static final String MSG_SUCCESS = "MSG_SUCCESS";
	
	private static final String MSG_PROBLEM = "MSG_PROBLEM";
	
	
    AnaliseGarantiaDao analiseGarantiaDao;
    
    /**
	 *  Objeto transactionTemplate para controle de transação
	 */
	private TransactionTemplate transactionTemplate;
	
    
    /** Insere um novo parecer técnico da análise da garantia
     * 
     * @param HistAnaliseParecerTecnico parecerTecnico
     * 
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      será lançada.
     */
    public void insertParecerTecnico(HistAnaliseParecerTecnico parecerTecnico) throws BusinessException {
    	try {
    		analiseGarantiaDao.makePersistent(parecerTecnico);
    	} catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }
    }
    
    /** Insere um nova observação da análise da garantia
     * 
     * @param HistAnaliseObservacao analiseObservacao
     * 
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      será lançada.
     */
    public void insertAnaliseHistorico(HistAnaliseObservacao analiseObservacao) throws BusinessException {
    	try {
    		analiseGarantiaDao.makePersistent(analiseObservacao);
    	} catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }
    }
    
    /**retorna uma lista de garantias do Chassi informado
	 * 
	 * @param String chassi
	 * @return List
	 */
    public List listHistoricoGarantia(Integer garantiaId, String modelo) throws BusinessException {
		try {
			return this.analiseGarantiaDao.listHistoricoGarantia(garantiaId,modelo);
		} catch (DaoException dExp) {
			throw new BusinessException(dExp);
		}
	}
	
    
    /** Listar o histórico de parecer técnico de uma garantia
     * 
     * @param Integer garantiId
     * @return String histórico
     * 
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      será lançada.
     */
    public String listHistParecerTecnico(Integer garantiaId) throws BusinessException {
    	
    	try {
    		
    		// Obter a listagem de acordo com a classe solicitada.
			List histList     = analiseGarantiaDao.listByParameter(HistAnaliseParecerTecnico.class, "garantiaId", garantiaId, "entityId", "DESC");
			int  listSize     = histList.size();
			int  listPosition = 0;
			
			StringBuffer results    = new StringBuffer();
			Iterator     histListIt = histList.iterator();
			
			// Criamos a string de resultados, utilizando as informações
			// da classe de histórico solicitada.
			while ( histListIt.hasNext() ) {

				listPosition++;
				String text = null;				
				
				HistAnaliseParecerTecnico hist = (HistAnaliseParecerTecnico) histListIt.next();
				text = hist.getParecerTecnicoTxt();
				
				// Formato de uma linha de resultado:
				results.append( text );
				
				// Só adicionamos a quebra de linha se não estivermos na
				// última linha de histórico.
				if ( listPosition < listSize )
					results.append( BREAKLINE );
				
			}
			
			return results.toString();
			
		} catch (DaoException dExp) {
			throw new BusinessException(dExp);
		}
    }
    
    /** Listar o histórico de observação do parecer de uma garantia
     * 
     * @param Integer garantiId
     * @return String
     * 
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      será lançada.
     */
    public String listHistAnaliseObservacao(Integer garantiId) throws BusinessException {
    	
    	try {
    		
    		// Obter a listagem de acordo com a classe solicitada.
			List histList     = analiseGarantiaDao.listByParameter(HistAnaliseObservacao.class, "garantiaId", garantiId, "entityId", "DESC");
			int  listSize     = histList.size();
			int  listPosition = 0;
			
			StringBuffer results    = new StringBuffer();
			Iterator     histListIt = histList.iterator();
			
			// Criamos a string de resultados, utilizando as informações
			// da classe de histórico solicitada.
			while ( histListIt.hasNext() ) {

				listPosition++;
				String text = null;				
				
				HistAnaliseObservacao hist = (HistAnaliseObservacao) histListIt.next();
				text = hist.getObservacaoTxt();
				
				// Formato de uma linha de resultado:
				results.append( text );
				
				// Só adicionamos a quebra de linha se não estivermos na
				// última linha de histórico.
				if ( listPosition < listSize )
					results.append( BREAKLINE );
				
			}
			
			return results.toString();
			
		} catch (DaoException dExp) {
			throw new BusinessException(dExp);
		}
    }
    
    /** Lista de itens para analise de garantia
     * 
     * @param parametro  - valor de referência para pesquisa
     * @param criterio   - tipo de pesquisa
     * @param linhaId    - código da linha de produtos do usuário
     * 
     * @return List AnaliseGarantiaVO
     * 
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      será lançada. Caso hajam problemas de regras de negócio, uma
     *      BusinessRuleException (subclasse de BusinessException) será 
     *      lançada.
     */
    public List listByParametro(String parametro, String criterio, Long linhaId) throws BusinessException, BusinessRuleException  {

    	List results = null;
    	
        try {

        	if(criterio.equals(ConstantAnaliseGarantia.TIPO_LOTE)) {
        		Integer loteId = null;        		
        		try {        		
        			loteId = Integer.valueOf(parametro);      
        		}catch(Exception exc) {
        			throw new BusinessRuleException("analiseGarantia.msg.problem.parametro");
        		}        		
        		results = analiseGarantiaDao.listGarantiaByLote(loteId, linhaId);
        		
        	} else if(criterio.equals(ConstantAnaliseGarantia.TIPO_GARANTIA)) {
        		Integer garantiaId = null;
        		try {
        			garantiaId = Integer.valueOf(parametro);        		
        			
        		}catch(Exception exc) {
        			throw new BusinessRuleException("analiseGarantia.msg.problem.parametro");
        		}        		
        		results = analiseGarantiaDao.listGarantia(garantiaId, linhaId);
        		
        	} else if(criterio.equals(ConstantAnaliseGarantia.TIPO_CHASSI)) { 
        		
        		results = analiseGarantiaDao.listGarantiaByModelo(parametro, linhaId); 
        		
        	} else if(criterio.equals(ConstantAnaliseGarantia.TIPO_CONCESSIONARIA)) { 
        		    		
	        	results = analiseGarantiaDao.listGarantiaByConcessionaria(parametro, linhaId); 
	        		
        	} else if(criterio.equals(ConstantAnaliseGarantia.TIPO_POR_LINHA)) { 
        		
	        	results = analiseGarantiaDao.listGarantiaByLinha(linhaId); 
	        		
        	} else if(criterio.equals(ConstantAnaliseGarantia.TIPO_POR_REPRESENTANTE)) { 
        		
	        	results = analiseGarantiaDao.listGarantiaByRepresentante(parametro, linhaId); 
	        		
        	} else {
        		throw new BusinessRuleException("analiseGarantia.msg.problem.criteria");
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
				return (GarantiaHeader) analiseGarantiaDao.get(GarantiaHeader.class, garantiaId);
				
		} catch (DaoException dExp) {

	         throw new BusinessException(dExp);

	     }
		
	}
    
    /** Atualiza o status da G SG e do lote de acordo com o o parecer enviado
	 * 
	 * @param Usuario usuario
	 * @param garantia
	 * @param String parecerCode
	 * @throws BusinessException
	 */
	public AlertGarantia definirParecerSG(Usuario usuario, GarantiaHeader garantia, String parecerCode)  throws BusinessException {
		
		AlertGarantia alerta = null;
		if(AnaliseGarantiaVO.PARECER_DEVOLVER_SG.equals(parecerCode)) {
			alerta = this.alterarStatus(usuario, garantia, StatusGarantia.STATUS_MANUTENCAO, StatusLote.STATUS_MANUTENCAO);
			if(alerta != null) {
				
				if(MSG_SUCCESS.equals(alerta.getAlertGarantiaKey())) {
					alerta.setAlertGarantiaKey("analiseGarantia.msg.success.devolver");
				} else 	if(MSG_PROBLEM.equals(alerta.getAlertGarantiaKey())) {
					alerta.setAlertGarantiaKey("analiseGarantia.msg.error.devolver");
				}			
			}
		} else if(AnaliseGarantiaVO.PARECER_RECUSAR_SG.equals(parecerCode)) {
			alerta = this.alterarStatus(usuario, garantia, StatusGarantia.STATUS_RECUSADO, StatusLote.STATUS_RECUSADO);
			if(alerta != null) {
				
				if(MSG_SUCCESS.equals(alerta.getAlertGarantiaKey())) {
					alerta.setAlertGarantiaKey("analiseGarantia.msg.success.recusar");
				} else 	if(MSG_PROBLEM.equals(alerta.getAlertGarantiaKey())) {
					alerta.setAlertGarantiaKey("analiseGarantia.msg.error.recusar");
				}			
			}
		} else if(AnaliseGarantiaVO.PARECER_APROVAR_SG.equals(parecerCode)) {
			//Quando for da linha de moto - Deve ir para a Classificação técnica
			if(garantia.getLote().getLinha().isMotocicleta()) {
				//alerta =  this.alterarStatus(usuario, garantia, StatusGarantia.STATUS_AGUARDANDO_CLASSIFICACAO_TECNICA, StatusLote.STATUS_AGUARDANDO_CLASSIFICACAO_TECNICA);
				alerta =  this.alterarStatus(usuario, garantia, StatusGarantia.STATUS_AGUARDANDO_DIGITACAO_NOTA, StatusLote.STATUS_AGUARDANDO_DIGITACAO_NOTA);
			} else {  
				alerta =  this.alterarStatus(usuario, garantia, StatusGarantia.STATUS_AGUARDANDO_DIGITACAO_NOTA, StatusLote.STATUS_AGUARDANDO_DIGITACAO_NOTA);
			}
			if(alerta != null) {
				
				if(MSG_SUCCESS.equals(alerta.getAlertGarantiaKey())) {
					alerta.setAlertGarantiaKey("analiseGarantia.msg.success.aprovar");
				} else 	if(MSG_PROBLEM.equals(alerta.getAlertGarantiaKey())) {
					alerta.setAlertGarantiaKey("analiseGarantia.msg.error.aprovar");
				}			
			}
		} else if(AnaliseGarantiaVO.PARECER_CANCELAR_SG.equals(parecerCode)) {
			alerta = this.alterarStatus(usuario, garantia, StatusGarantia.STATUS_CANCELADA, StatusLote.STATUS_CANCELADO);
			if(alerta != null) {
				
				if(MSG_SUCCESS.equals(alerta.getAlertGarantiaKey())) {
					alerta.setAlertGarantiaKey("analiseGarantia.msg.success.cancelar");
				} else 	if(MSG_PROBLEM.equals(alerta.getAlertGarantiaKey())) {
					alerta.setAlertGarantiaKey("analiseGarantia.msg.error.cancelar");
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
	 *  Se a entidade recebida não existir na camada de persistência
	 *  da aplicação, será criada. Do contrário seus valores serão
	 *  atualizados de acordo com as informações alteradas.
	 *  
	 *  @param entityObject
	 *      <code>EntityObject</code> representando a entidade a ser
	 *      criada/atualizada na camada de persistência.
	 *      
	 *  @throws BusinessException
	 *      Se houverem problemas nas camadas inferiores, estes serão 
	 *      convertidos para uma BusinessException.
	 */    
	public void saveEntity(EntityObject entityObject) throws BusinessException {
		
		try {

			this.analiseGarantiaDao.makePersistent(entityObject);
			
		} catch ( DaoException daoExp ) {

			throw new BusinessException( daoExp );

		}
	}

	/** Método getter para a propriedade analiseGarantiaDao
	 *
	 * @return analiseGarantiaDao do tipo AnaliseGarantiaDao
	 *
	 */	
	public AnaliseGarantiaDao getAnaliseGarantiaDao() {
		return analiseGarantiaDao;
	}

	/** Método setter para a propriedade analiseGarantiaDao
	 *
	 * @param analiseGarantiaDao 
	 *       <code>analiseGarantiaDao<code> a ser designado para AnaliseGarantiaBusiness.java
	 *
	 */
	public void setAnaliseGarantiaDao(AnaliseGarantiaDao analiseGarantiaDao) {
		this.analiseGarantiaDao = analiseGarantiaDao;
	}

	/** Método getter para a propriedade transactionTemplate
	 *
	 * @return transactionTemplate do tipo TransactionTemplate
	 *
	 */
	
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	/** Método setter para a propriedade transactionTemplate
	 *
	 * @param transactionTemplate 
	 *       <code>transactionTemplate<code> a ser designado para AnaliseGarantiaBusiness.java
	 *
	 */
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
    
}
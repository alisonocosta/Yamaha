/* Resource Tecnologia
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......EstoqueBusiness.java
 *   .: Cria��o.....16 de abril, 14:22
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Objeto de neg�cios para a entidade "Estoque".
 */
package br.com.yamaha.sistemagarantia.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import br.com.resource.infra.business.BusinessObject;
import br.com.resource.infra.dao.Dao;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.model.AlertGarantia;
import br.com.yamaha.sistemagarantia.model.Concessionaria;
import br.com.yamaha.sistemagarantia.model.Estoque;
import br.com.yamaha.sistemagarantia.model.Faturamento;
import br.com.yamaha.sistemagarantia.model.ParametroSistema;
import br.com.yamaha.sistemagarantia.model.Programa;
import br.com.yamaha.sistemagarantia.view.EstoqueVO;

/** Classe de neg�cios relacionada � entidade <b>Estoque</b>.
 * 
 *  @author Edson Luiz Sumensari
 */
public class EstoqueBusiness extends BusinessObject {

	/** Objeto DAO gen�rico para utilizado para este objeto de neg�cios. 
	 *  <p>
	 *  A entidade "GarantiaLine" n�o possui tarefas espec�ficas, portanto, 
	 *  sua ponte com o banco de dados � feita utilizando um DAO padr�o. 
	 */
	private Dao genericDao;	 
	
	/**
	 *  Objeto transactionTemplate para controle de transa��o
	 */
	private TransactionTemplate transactionTemplate;


    /** Recupera um estoque pelo id.
     *
     *  @param entityId
     *      Refer�ncia da entidade na camada de persist�ncia.
     *  @return
     *      Um objeto <code>Estoque</code>.
     *      
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
     *      BusinessRuleException (subclasse de BusinessException) ser� 
     *      lan�ada.
     */
    public Estoque get(Serializable entityId) throws BusinessException {

        try {

            return (Estoque)genericDao.get(Estoque.class, entityId);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    }
    
    /** Lista todo o estoque existentes no banco de dados.
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

            return genericDao.listAll(Estoque.class);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    } 
    
    /** Lista todos o estoque de uma concession�ria.
     * 
     *  @param concessionaria
     *  	<code>Concessionaria</code> Entidade da Concession�ria.
     *    
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
    public List listByConcessionaria(Concessionaria concessionaria) throws BusinessException {
    	
    	try {
    		
    		List listResults = new ArrayList();

    		List listEstoque = genericDao.listByParameter(Estoque.class, "concessionaria", concessionaria);

    		Iterator itRes = listEstoque.iterator();
    		EstoqueVO estoqueVO = null;
    		
    		while ( itRes.hasNext() ) {
    			
    			Estoque estoque = (Estoque)itRes.next();
    			Faturamento faturamento = (Faturamento)genericDao.get(Faturamento.class, estoque.getFaturamentoId());
    			
    			if ( faturamento == null )
    				throw new DaoException("N�o foi localizado o faturamento de id : " + estoque.getFaturamentoId() );
    			
    			estoqueVO = new EstoqueVO();
    			estoqueVO.setEntityId(estoque.getEntityId());
    			estoqueVO.setChassi(faturamento.getChassi().toUpperCase());
    			estoqueVO.setDataEstoque(estoque.getDataEstoque());
    			estoqueVO.setDataFaturamento(faturamento.getDataNotaFiscal());
    			
    			listResults.add(estoqueVO);
    			
    		}
           
           return listResults;
           
        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }
    	
    }
    
    /** Obt�m uma entidade do Programa. 
     * 
     * @param nomePrograma
     *  <code>String</code> O nome do programa.
     *  
     *  @return quantidade de linhas
     *     <code>Integer</code>.
     *  
     * @throws BusinessException
     *      Se houverem erros de execu��o ou nas camadas 
     *      abaixo dos servi�os, ser�o encapsulados neste
     *      tipo de <code>Exception</code>.  
     */
    public Integer getLinhasPagina(String nomePrograma) throws BusinessException {
    	
    	try {
    		
    		Programa programa = (Programa)genericDao.getByParameter(Programa.class, "codigoPrograma", nomePrograma);
    		
    		return programa.getLinhasPagina();
    	
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

			return (ParametroSistema) genericDao.getByParameter(ParametroSistema.class, "nomeParametro", parameterName);

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
     *  @param estoque
     *      <code>Estoque</code> representando a entidade a ser
     *      criada/atualizada na camada de persist�ncia.
     *      
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
     *      BusinessRuleException (subclasse de BusinessException) ser� 
     *      lan�ada.
     */    
    public AlertGarantia save(Estoque estoque) throws BusinessException {
    	
    	AlertGarantia alert = new AlertGarantia();

        try {

            // � um estoque novo. Devemos proceder com valida��es
            // de integridade antes de salv�-lo na base.
            if ( estoque.isNew() ) {
            
            	Estoque estoqueTaken = this.getByFaturamento( estoque.getFaturamentoId() );
                // Valida��o se este faturamento j� foi cadastrado no estoque.
                if ( estoqueTaken != null ) {
                    alert.setAlertGarantiaKey("estoque.form.msg.error.faturamentoTaken");
                    alert.setAlertGarantiaText("Este produto j� foi inclu�do no invent�rio de estoque!");
                    return alert;
                }
            }
            
            // Se chegamos aqui, n�o houveram erros. Podemos
            // salvar o estoque na base de dados.
            genericDao.makePersistent(estoque);
            
            alert.setAlertGarantiaKey("estoque.msg.success.saved");
            alert.setAlertGarantiaText("O produto foi gravado no invent�rio com sucesso!");

        } catch ( DaoException daoExp ) {

            throw new BusinessException( daoExp );

        }
        
        return alert;

    }
    
    /** Obt�m uma entidade de Estoque a partir do faturamentoId do produto.
     * 
     *  @param faturamentoId
     *      <code>Long</code> Par�metro de pesquisa a ser utilizado na busca 
     *      do faturamento na camada de persist�ncia.
     *  
     *  @return
     *      Uma entidade de Estoque relacionado ao faturamentoId ou <code>null</code>
     *      caso nenhum estoque seja encontrado.
     *  
     *  @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
     *      BusinessRuleException (subclasse de BusinessException) ser� 
     *      lan�ada.
     */
    public Estoque getByFaturamento( Long faturamentoId )throws BusinessException {
    	
    	try {
            
            return (Estoque)genericDao.getByParameter(Estoque.class, "faturamentoId", faturamentoId);
            
        } catch (DaoException dExp) {
            
            throw new BusinessException(dExp);
            
        }
    	
    	
    }
    
    /** Remove entidades do estoque. 
     * 
     * @param lista de entidades
     *  <code>List</code> entidades de Estoque.
     *  
     *  @return alerta 
     *     <code>AlertGarantia</code>.
     *  
     * @throws BusinessException
     *      Se houverem erros de execu��o ou nas camadas 
     *      abaixo dos servi�os, ser�o encapsulados neste
     *      tipo de <code>Exception</code>.  
     */
    public AlertGarantia removeList(List listEstoque) throws BusinessException {
    
    	final List listFinalEst = listEstoque;
    	
    	System.out.println("Lista :" + listFinalEst.size());
    	
    	AlertGarantia alerta = (AlertGarantia) transactionTemplate.execute(
                
	            new TransactionCallback() {
	                
	                public Object doInTransaction(TransactionStatus ts) {
	                	
	                	AlertGarantia alert = new AlertGarantia();
	                	
	                	try {
	                		
	                		System.out.println("Vamos iterar coma lista.");	                		
	                		Estoque estoque = null;
	                		Iterator itEsq = listFinalEst.iterator();
	                		while( itEsq.hasNext() ) {
	                			
	                			estoque = (Estoque)itEsq.next();
	                			
	                			System.out.println("Removendo o item :" + estoque.getEntityId());
	                			
	                			remove(estoque);
	                			
	                		}
	                		
							alert.setAlertGarantiaKey("estoque.msg.success.removed");
	                		
	                	} catch ( BusinessException bExp ) {
							
							ts.setRollbackOnly();
							alert.setAlertGarantiaKey("estoque.form.msg.error.notRem");
							alert.setAlertGarantiaText("A opera��o n�o pode ser realizada! Problema:" + bExp.getMessage());
							
							System.out.println(" ----------> Rollback - Erro: " + bExp.getMessage());
							
						} 
	                	
	                	return alert;
	                    
	                }
	                
	            }	                
        );
        
        return alerta;		
    	
    }
    
    /** Remove entidade do estoque. 
     * 
     * @param lista de entidades
     *  <code>List</code> entidades de Estoque.
     * 
     *  
     * @throws BusinessException
     *      Se houverem erros de execu��o ou nas camadas 
     *      abaixo dos servi�os, ser�o encapsulados neste
     *      tipo de <code>Exception</code>.  
     */
    public void remove(Estoque estoque) throws BusinessException {
    	
    	try {
    		
    		genericDao.makeTransient(estoque);
    	
    	} catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }
    	
    	
    }
    
    /** M�todo getter para "genericDao".
	 *  @return
	 *      <code>Dao</code>. O valor atual de genericDao.
	 */
	public Dao getGenericDao() {
		return this.genericDao;
	}

	/** M�todo setter para "genericDao".
	 *  @param entityId
	 *      <code>Dao</code> a ser designado para genericDao.
	 */
	public void setGenericDao(Dao genericDao) {
		this.genericDao = genericDao;
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
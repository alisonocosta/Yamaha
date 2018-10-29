/* Resource Tecnologia
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......EstoqueBusiness.java
 *   .: Criação.....16 de abril, 14:22
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Objeto de negócios para a entidade "Estoque".
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

/** Classe de negócios relacionada à entidade <b>Estoque</b>.
 * 
 *  @author Edson Luiz Sumensari
 */
public class EstoqueBusiness extends BusinessObject {

	/** Objeto DAO genérico para utilizado para este objeto de negócios. 
	 *  <p>
	 *  A entidade "GarantiaLine" não possui tarefas específicas, portanto, 
	 *  sua ponte com o banco de dados é feita utilizando um DAO padrão. 
	 */
	private Dao genericDao;	 
	
	/**
	 *  Objeto transactionTemplate para controle de transação
	 */
	private TransactionTemplate transactionTemplate;


    /** Recupera um estoque pelo id.
     *
     *  @param entityId
     *      Referência da entidade na camada de persistência.
     *  @return
     *      Um objeto <code>Estoque</code>.
     *      
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      será lançada. Caso hajam problemas de regras de negócio, uma
     *      BusinessRuleException (subclasse de BusinessException) será 
     *      lançada.
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

            return genericDao.listAll(Estoque.class);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    } 
    
    /** Lista todos o estoque de uma concessionária.
     * 
     *  @param concessionaria
     *  	<code>Concessionaria</code> Entidade da Concessionária.
     *    
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
    				throw new DaoException("Não foi localizado o faturamento de id : " + estoque.getFaturamentoId() );
    			
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
    
    /** Obtém uma entidade do Programa. 
     * 
     * @param nomePrograma
     *  <code>String</code> O nome do programa.
     *  
     *  @return quantidade de linhas
     *     <code>Integer</code>.
     *  
     * @throws BusinessException
     *      Se houverem erros de execução ou nas camadas 
     *      abaixo dos serviços, serão encapsulados neste
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

			return (ParametroSistema) genericDao.getByParameter(ParametroSistema.class, "nomeParametro", parameterName);

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
     *  @param estoque
     *      <code>Estoque</code> representando a entidade a ser
     *      criada/atualizada na camada de persistência.
     *      
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      será lançada. Caso hajam problemas de regras de negócio, uma
     *      BusinessRuleException (subclasse de BusinessException) será 
     *      lançada.
     */    
    public AlertGarantia save(Estoque estoque) throws BusinessException {
    	
    	AlertGarantia alert = new AlertGarantia();

        try {

            // É um estoque novo. Devemos proceder com validações
            // de integridade antes de salvá-lo na base.
            if ( estoque.isNew() ) {
            
            	Estoque estoqueTaken = this.getByFaturamento( estoque.getFaturamentoId() );
                // Validação se este faturamento já foi cadastrado no estoque.
                if ( estoqueTaken != null ) {
                    alert.setAlertGarantiaKey("estoque.form.msg.error.faturamentoTaken");
                    alert.setAlertGarantiaText("Este produto já foi incluído no inventário de estoque!");
                    return alert;
                }
            }
            
            // Se chegamos aqui, não houveram erros. Podemos
            // salvar o estoque na base de dados.
            genericDao.makePersistent(estoque);
            
            alert.setAlertGarantiaKey("estoque.msg.success.saved");
            alert.setAlertGarantiaText("O produto foi gravado no inventário com sucesso!");

        } catch ( DaoException daoExp ) {

            throw new BusinessException( daoExp );

        }
        
        return alert;

    }
    
    /** Obtém uma entidade de Estoque a partir do faturamentoId do produto.
     * 
     *  @param faturamentoId
     *      <code>Long</code> Parâmetro de pesquisa a ser utilizado na busca 
     *      do faturamento na camada de persistência.
     *  
     *  @return
     *      Uma entidade de Estoque relacionado ao faturamentoId ou <code>null</code>
     *      caso nenhum estoque seja encontrado.
     *  
     *  @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      será lançada. Caso hajam problemas de regras de negócio, uma
     *      BusinessRuleException (subclasse de BusinessException) será 
     *      lançada.
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
     *      Se houverem erros de execução ou nas camadas 
     *      abaixo dos serviços, serão encapsulados neste
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
							alert.setAlertGarantiaText("A operação não pode ser realizada! Problema:" + bExp.getMessage());
							
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
     *      Se houverem erros de execução ou nas camadas 
     *      abaixo dos serviços, serão encapsulados neste
     *      tipo de <code>Exception</code>.  
     */
    public void remove(Estoque estoque) throws BusinessException {
    	
    	try {
    		
    		genericDao.makeTransient(estoque);
    	
    	} catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }
    	
    	
    }
    
    /** Método getter para "genericDao".
	 *  @return
	 *      <code>Dao</code>. O valor atual de genericDao.
	 */
	public Dao getGenericDao() {
		return this.genericDao;
	}

	/** Método setter para "genericDao".
	 *  @param entityId
	 *      <code>Dao</code> a ser designado para genericDao.
	 */
	public void setGenericDao(Dao genericDao) {
		this.genericDao = genericDao;
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
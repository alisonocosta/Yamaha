/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RevisaoBusiness.java
 *   .: Cria��o.....14 e maio, 10:23
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Objeto de neg�cios para a entidade "Revisao".
 */
package br.com.yamaha.sistemagarantia.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.resource.infra.business.BusinessObject;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.BusinessRuleException;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.controller.helper.ControllerHelper;
import br.com.yamaha.sistemagarantia.dao.RevisaoDao;
import br.com.yamaha.sistemagarantia.model.Cupom;
import br.com.yamaha.sistemagarantia.model.Revisao;

/** Classe de neg�cios relacionada � entidade <b>Revisao</b>.
 * 
 *  @author Edson Luiz Sumensari
 */
public class RevisaoBusiness extends BusinessObject {

    /** Objeto DAO gen�rico para utilizado para este objeto de neg�cios. 
     *  <p>
     *  A entidade "Revisao" n�o possui tarefas espec�ficas, portanto, 
     *  sua ponte com o banco de dados � feita utilizando um DAO padr�o. 
     */
    private RevisaoDao revisaoDao;

    /** Recupera uma Faturamento pelo id.
     *
     *  @param entityId
     *      Refer�ncia da entidade na camada de persist�ncia.
     *  @return
     *      Um objeto <code>Revisao</code>.
     *      
     * @throws BusinessException
     *      Se houverem erros de execu��o ou nas camadas 
     *      abaixo dos servi�os, ser�o encapsulados neste
     *      tipo de <code>Exception</code>.
     */
    public Revisao get(Serializable entityId) throws BusinessException {

        try {

            return (Revisao)revisaoDao.get(Revisao.class, entityId);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }

    }
    
    /** Obt�m uma entidade da pr�xima revis�o para o mode informado, revis�o ainda n�o realizada.
     *    
     * @param chassi
     *      Chassi do produto.
     * 
     * @return
     *      Uma entidade de Revisao
     * 
     *  @throws BusinessException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
     */
    public Revisao getNextByChassi( String chassi ) throws BusinessException {
    	
    	try {

            return (Revisao)revisaoDao.getNextByChassi(chassi);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }    	
    	
    }
    
    
    /** Obt�m uma listagem de registros aproxiamda limitada de acordo o par�metro passado.
     *    
     * @param paramName
     *      Nome do par�metro do filtro.
     *      
     * @param valuePart
     *      valor que servir� como par�metro de filtro.
     * 
     * @return
     *      Uma listagem de elementos, organizada e restrita aos 
     *      delimitadores fornecidos.
     * 
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
     */
    public List listApproachedByChassi( String valuePart) throws BusinessException {
    	
    	try {

            return revisaoDao.listApproachedByChassi(valuePart);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }
    	
    	
    }
    
    /** Obt�m uma listagem de revis�es ainda n�o realizadas para o chassi informado.
     *    
     * @param chassi
     *      Chassi do produto.
     * 
     * 
     * @return
     *      Uma listagem de elementos, organizada e restrita aos 
     *      delimitadores fornecidos.
     * 
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
     */
    public List listNewByModel( String chassi) throws BusinessException {
    	
    	try {

            return revisaoDao.listNewByModel(chassi);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }
    	
    	
    }
    
    /** Este m�todo retorna uma lista de revis�es realizadas mais a revis�o atual
     *  passada com o par�metro da Entidade Cupom
     * 
     * @param cupom
     * 	<code>Cupom</code> Entidade Cupom.
     * 
     * @return List
     * 
     * @throws BusinessException
     */
    public List loadAlterRevisao(Cupom cupom)throws BusinessException {
    	
    	try {
    		
    		String modelo = ControllerHelper.getModeloByChassi(cupom.getChassi());
    		
    		Long numeroRevisao = cupom.getRevisao().getNumeroRevisao();
    		
    		List listRevisao = this.revisaoDao.listByParameter(Revisao.class, "modelo", modelo, "numeroRevisao", "ASC");
    		List listCupom   = this.revisaoDao.listByParameter(Cupom.class  , "chassi", cupom.getChassi().toUpperCase(), "entityId", "ASC");
    		
    		//System.out.println("Cupons:" + listCupom.size());
    		
    		List listRevisaoFiltered = new ArrayList();
    		Revisao revisao =  null;
    		
    		Iterator it = listCupom.iterator();
    		
    		while ( it.hasNext() ) {
    			
    			Cupom cupomLoad = (Cupom)it.next();
    			revisao = cupomLoad.getRevisao();
    			
    			Long idRevisao = (Long)revisao.getEntityId();
    			
    			Iterator itr = listRevisao.iterator();
    			
    			while ( itr.hasNext() ) {
    				
    				Revisao revisao_l = (Revisao)itr.next();
    				
    				Long idRevisao_l = (Long)revisao_l.getEntityId();
    				
    				//System.out.println("Cupom RevisaoId:"  + revisao.getEntityId() + " RevisaoId:" + revisao_l.getEntityId());
    				
    				if ( (idRevisao).equals( idRevisao_l ) && ( !revisao.getNumeroRevisao().equals(numeroRevisao)) ) {
    						    	
    					//System.out.println(" Revis�o realizada:" + revisao_l.getNumeroRevisao());
    					listRevisaoFiltered.add(revisao_l);	    					
    					
    				}
    				
    			}
    			
    		}
    		
    		Iterator itRes = listRevisaoFiltered.iterator();
			
			while ( itRes.hasNext() ) {
				
				revisao = (Revisao)itRes.next();
				
				listRevisao.remove(revisao);
				
			}
			
			
			return listRevisao;

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }
    	
    	
    }    
    
    
    /** Obt�m uma listagem de registros limitada de acordo o par�metro passado.
     *    
     * @param paramName
     *      Nome do par�metro do filtro.
     *      
     * @param modelo
     *      valor que servir� como par�metro de filtro.
     * 
     * @return
     *      Uma listagem de elementos, organizada e restrita aos 
     *      delimitadores fornecidos.
     * 
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
     */
    public List listByModel( String modelo ) throws BusinessException {
    	
    	try {

            return revisaoDao.listByModel(modelo);

        } catch (DaoException dExp) {

            throw new BusinessException(dExp);

        }
    	
    	
    }
    
    
    /** Obt�m uma entidade de Revisao de acordo com o modelo - 5 primeiras posi��es do chassi e
     *  o n�mero da revis�o desejada
     *    
     * @param String model
     *      modelo.
     *      
     * @param Long numberRevision
     *      valor que servir� como par�metro de filtro da Revis�o.
     * 
     * @return Revisao
     *      Uma entitydade de Revisao.
     * 
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
     */
    public Revisao getByModel(String model, Long numberRevision) throws BusinessException, BusinessRuleException {
    	
    	try {

            return (Revisao)revisaoDao.getByModel(model, numberRevision);

        } catch (DaoException dExp) {

            throw new BusinessRuleException("cupom.msg.error.revision");

        }
    	
    }
    
    /** M�todo getter para "revisaoDao".
     *  @return
     *      <code>RevisaoDao</code>. O valor atual de revisaoDao.
     */
    public RevisaoDao getRevisaoDao() {
        return this.revisaoDao;
    }

    /** M�todo setter para "revisaoDao".
     *  @param entityId
     *      <code>RevisaoDao</code> a ser designado para revisaoDao.
     */
    public void setRevisaoDao(RevisaoDao revisaoDao) {
        this.revisaoDao = revisaoDao;
    }   

    
}
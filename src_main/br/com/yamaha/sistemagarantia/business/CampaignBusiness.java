/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......CampaignBusiness.java
 *   .: Criação.....21 de julho, 08:10
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Objeto de negócios para a entidade "Campaign".
 */
package br.com.yamaha.sistemagarantia.business;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import br.com.resource.infra.business.BusinessObject;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.DaoException;
import br.com.resource.infra.utils.DateUtils;
import br.com.yamaha.sistemagarantia.dao.CampaignDao;
import br.com.yamaha.sistemagarantia.model.Campaign;
import br.com.yamaha.sistemagarantia.model.GarantiaHeader;
import br.com.yamaha.sistemagarantia.model.Recall;

/** Objeto de negócios para a entidade "Campaign".
 * 
 *  @author Edson Luiz Sumensari
 */
public class CampaignBusiness extends BusinessObject {

	/** Objeto DAO utilizado para este objeto de negócios. */
	private CampaignDao campaignDao;
	
	/** Refererência para o o objeto de negócios da Garantia */
	private GarantiaHeaderBusiness garantiaHeaderBo;
	
	
	/** recupera uma campanha por garantia.
	 * 
	 * @param GarantiaHeader
	 * 		<code>garantia</code> 
	 * 
	 * @return Campaign
	 * 	<code>Campaign</code>
	 * 
	 * @throws DaoException
	 */	
	 public Campaign getCampaignByGarantia( GarantiaHeader garantia) throws BusinessException {
		 
		 try {
			 
			 Long campaignId = campaignDao.getCampaignByGarantia((Integer)garantia.getEntityId());
			 
			return campaignDao.getCampaignById(campaignId);
			
		 } catch (DaoException dExp) {

	            throw new BusinessException(dExp);

	        }
	 }
	
	/** Lista as campanhas de um determinado produto pelo seu chassi, desde de que, 
	 *  o produto não tenha participado da campanha.
	 * 
	 * @param chassi
	 * 		<code>String</code> Chassi
	 * 
	 * @return list
	 * 	<code>list</code> lista de campanhas.
	 * 
	 * @throws DaoException
	 */	
	 public List listCampaign( String chassi ) throws BusinessException {
		 
		 try {
			 
			return campaignDao.listCampaign(chassi);
			
		 } catch (DaoException dExp) {

	            throw new BusinessException(dExp);

	        }
	 }
	 
	 /** Lista as campanhas de um determinado produto pelo seu chassi, desde de que, 
	  *  o produto não tenha participado da campanha.
	  * 
	  * @param chassi
	  * 		<code>String</code> Chassi
	  * 
	  * @return list
	  * 	<code>list</code> lista de campanhas.
	  * 
	  * @throws DaoException
	  */	
	 public List listCampaignForSelect( String chassi ) throws BusinessException {

		 try {
			 return campaignDao.listCampaignForSelect(chassi);

		 } catch (DaoException dExp) {

			 throw new BusinessException(dExp);

		 }
	 }

	 /** Recupera uma campanha pelo seu id.
	 * 
	 * @param entityId
	 * 		<code>Long</code> entityId
	 * 
	 * @return Campaign
	 * 	<code>Campaign</code> uma entidade de Campaign.
	 * 
	 * @throws DaoException
	 */	
	 public Campaign getById( Long entityId ) throws BusinessException {
		 
		 try {
			 
			 return campaignDao.getById(entityId);
			 
		 } catch (DaoException dExp) {

	            throw new BusinessException(dExp);

	        }
	 }
	 
	 /** Recupera uma campanha pelo seu id.
		 * 
		 * @param campaignId
		 * 		<code>Long</code> campaignId
		 * 
		 * @return Campaign
		 * 	<code>Campaign</code> uma entidade de Campaign.
		 * 
		 * @throws DaoException
		 */	
	 public Campaign getCampaignById(Long campaignId) throws BusinessException {
			 
			 try {
				 
				 return campaignDao.getCampaignById(campaignId);
				 
			 } catch (DaoException dExp) {

		            throw new BusinessException(dExp);

		        }
		 }
	
    /** Salva ou atualiza o estado de uma entidade.
     *  <p>
     *  Se a entidade recebida não existir na camada de persistência
     *  da aplicação, será criada. Do contrário seus valores serão
     *  atualizados de acordo com as informações alteradas.
     *  
     *  @param campaign
     *      <code>Campaign</code> representando a entidade a ser
     *      criada/atualizada na camada de persistência.
     *      
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      será lançada. Caso hajam problemas de regras de negócio, uma
     *      BusinessRuleException (subclasse de BusinessException) será 
     *      lançada.
     */     
	public void save(Campaign campaign) throws BusinessException {
		
		try {
			
			campaignDao.makePersistent( campaign );
			
		} catch (DaoException dExp) {
			
			throw new BusinessException(dExp);
			
		}
		
	}
	
	/**
	 * Método para verifiacar um produto possui campanha de Recall para 
	 * realizar e retorna uma mensagem para se apresentada ao usuário
	 * 
	 * @param chassi String
	 * @param isGarantia boolean  true para Inclusão de garantia
	 * @return String Mensagem
	 * 
	 * @throws BusinessException
	 */
	public String checkCampaingByChassi(String chassi, boolean isGarantia) throws BusinessException {
		    		
		if ( chassi != null ) {
			
    		/* Rotina para listar as campanhas para o chassi informado*/	    		
    		List listCampaign = this.listCampaignForSelect(chassi);	
    		/* Rotina para listar Recall para o chassi informado*/	    		
    		List listRecall = garantiaHeaderBo.listRecallNotExByChassi(chassi);
    		
    		//System.out.println("Campanhas:" + listCampaign.size() + " Recall:" + listRecall.size());
    		
    		if ( !listCampaign.isEmpty() || !listRecall.isEmpty() ) {

    	    	StringBuffer sb = new StringBuffer(); 
    			int i	  = 1;
    			boolean hasItem = false;
    			
    			sb.append("OBSERVAÇÃO: CAMPANHA/RECALL NÃO REALIZADO(s) PARA O CHASSI " + chassi + " \\n\\n' + ");	    		
	    		
    			Iterator itC = listCampaign.iterator();
	    		Campaign cp = null;
	    		while(itC.hasNext()){
	    			cp = (Campaign) itC.next();
	    			if( cp.getValidityStartDate() != null && Campaign.RECALL_YES.equals(cp.getStRecall())) {
	    				if( DateUtils.compareDate(new Date(), cp.getValidityStartDate()) >= 0 ) {
	    					if( (cp.getValidityEndDate() != null ? (DateUtils.compareDate(new Date(), cp.getValidityEndDate()) < 0) : true) ) {
	    						
					    			if ( i > 1)
					    				sb.append(" + '");
					    			else
					    				sb.append("'");
					    			
					    			sb.append( i++ + " - CÓDIGO DA CAMPANHA: " + cp.getCodeCampaign() + "\\nDESCRIÇÃO:" + cp.getSubjectCampaign() + "\\n\\n'");
					    			hasItem = true;
	    					}
	    				}
	    			} 
	    		}
	    		
	    		Iterator itR = listRecall.iterator();
	    		Recall rc = null;
	    		while( itR.hasNext() ){
	    			rc = (Recall) itR.next();
	    			if ( i > 1)
	    				sb.append(" + '");
	    			else
	    				sb.append("'");
	    			
	    			sb.append( i++ + " - CÓDIGO DO RECALL: " + rc.getNumeroIT() + "\\nDESCRIÇÃO:" + rc.getAssunto() + "\\n\\n");
	    			if ( i > 1)
	    				sb.append(" + '");
	    			hasItem = true;    			
	    		}
	    		//System.out.println(sb.toString());
	    		
	    		if ( hasItem ) {
	    			if(isGarantia)
	    				sb.append(" + 'Confirma a inclusão da Solicitação de Garantia?");
	    			else
	    				sb.append(" + 'Deseja continuar a operação?");
	    			
	    			//System.out.println(sb.toString());
	    			return sb.toString();
	    		} else
	    			return "";
	    		
    		} 	  		
    		
    		return "";
    		
		}

		return "";
	}
	
	/** Método getter para "campaignDao".
	 *  @return
	 *      <code>Dao</code>. O valor atual de campaignDao.
	 */
	public CampaignDao getCampaignDao() {
		return this.campaignDao;
	}

	/** Método setter para "campaignDao".
	 *  @param entityId
	 *      <code>CampaignDao</code> a ser designado para campaignDao.
	 */
	public void setCampaignDao(CampaignDao campaignDao) {
		this.campaignDao = campaignDao;
	}

	/** Método setter para a propriedade garantiaHeaderBo
	 *
	 * @param garantiaHeaderBo GarantiaHeaderBusiness
	 */
	public void setGarantiaHeaderBo(GarantiaHeaderBusiness garantiaHeaderBo) {
		this.garantiaHeaderBo = garantiaHeaderBo;
	}
}
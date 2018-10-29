/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......CampaignBusiness.java
 *   .: Cria��o.....21 de julho, 08:10
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Objeto de neg�cios para a entidade "Campaign".
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

/** Objeto de neg�cios para a entidade "Campaign".
 * 
 *  @author Edson Luiz Sumensari
 */
public class CampaignBusiness extends BusinessObject {

	/** Objeto DAO utilizado para este objeto de neg�cios. */
	private CampaignDao campaignDao;
	
	/** Referer�ncia para o o objeto de neg�cios da Garantia */
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
	 *  o produto n�o tenha participado da campanha.
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
	  *  o produto n�o tenha participado da campanha.
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
     *  Se a entidade recebida n�o existir na camada de persist�ncia
     *  da aplica��o, ser� criada. Do contr�rio seus valores ser�o
     *  atualizados de acordo com as informa��es alteradas.
     *  
     *  @param campaign
     *      <code>Campaign</code> representando a entidade a ser
     *      criada/atualizada na camada de persist�ncia.
     *      
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
     *      BusinessRuleException (subclasse de BusinessException) ser� 
     *      lan�ada.
     */     
	public void save(Campaign campaign) throws BusinessException {
		
		try {
			
			campaignDao.makePersistent( campaign );
			
		} catch (DaoException dExp) {
			
			throw new BusinessException(dExp);
			
		}
		
	}
	
	/**
	 * M�todo para verifiacar um produto possui campanha de Recall para 
	 * realizar e retorna uma mensagem para se apresentada ao usu�rio
	 * 
	 * @param chassi String
	 * @param isGarantia boolean  true para Inclus�o de garantia
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
    			
    			sb.append("OBSERVA��O: CAMPANHA/RECALL N�O REALIZADO(s) PARA O CHASSI " + chassi + " \\n\\n' + ");	    		
	    		
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
					    			
					    			sb.append( i++ + " - C�DIGO DA CAMPANHA: " + cp.getCodeCampaign() + "\\nDESCRI��O:" + cp.getSubjectCampaign() + "\\n\\n'");
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
	    			
	    			sb.append( i++ + " - C�DIGO DO RECALL: " + rc.getNumeroIT() + "\\nDESCRI��O:" + rc.getAssunto() + "\\n\\n");
	    			if ( i > 1)
	    				sb.append(" + '");
	    			hasItem = true;    			
	    		}
	    		//System.out.println(sb.toString());
	    		
	    		if ( hasItem ) {
	    			if(isGarantia)
	    				sb.append(" + 'Confirma a inclus�o da Solicita��o de Garantia?");
	    			else
	    				sb.append(" + 'Deseja continuar a opera��o?");
	    			
	    			//System.out.println(sb.toString());
	    			return sb.toString();
	    		} else
	    			return "";
	    		
    		} 	  		
    		
    		return "";
    		
		}

		return "";
	}
	
	/** M�todo getter para "campaignDao".
	 *  @return
	 *      <code>Dao</code>. O valor atual de campaignDao.
	 */
	public CampaignDao getCampaignDao() {
		return this.campaignDao;
	}

	/** M�todo setter para "campaignDao".
	 *  @param entityId
	 *      <code>CampaignDao</code> a ser designado para campaignDao.
	 */
	public void setCampaignDao(CampaignDao campaignDao) {
		this.campaignDao = campaignDao;
	}

	/** M�todo setter para a propriedade garantiaHeaderBo
	 *
	 * @param garantiaHeaderBo GarantiaHeaderBusiness
	 */
	public void setGarantiaHeaderBo(GarantiaHeaderBusiness garantiaHeaderBo) {
		this.garantiaHeaderBo = garantiaHeaderBo;
	}
}
/* Resource Tecnologia
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......CampaignDao.java
 *   .: Criação.....21 de Julho de 2008
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Objeto de negócios para a entidade "CampaignDao".
 */

package br.com.yamaha.sistemagarantia.dao;

import java.io.Serializable;
import java.util.List;

import br.com.resource.infra.dao.Dao;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.model.Campaign;

public interface CampaignDao extends Dao {
	/** Recupera uma Campanha com base em seu ID
	 * 
	 *  @param entityId
	 *  	<code>Serializable</code> Id da Campanha.
	 *  
	 *  @return
	 *      <code>Campaign</code> Uma entidade de Campaign.
	 *  
	 *  @throws DaoException
	 *      Se houverem erros durante a execução estes serão lançados
	 *      como uma Exception deste tipo.
	 */
	public Campaign getCampaignById(Serializable entityId) throws DaoException;

	/** Lista as campanhas de um determinado produto pelo seu chassi, desde de que, 
	 *  o produto não tenha participado da campanha.
	 * 
	 * @param campaignId
	 * 		<code>Long</code> id da Campanha.	
	 * 
	 * @return campaign
	 * 	<code>Campaign</code> uma Entidade de Campaign devidamente preenchida com as peças relaciondas.
	 * 
	 * @throws DaoException
	 */	
	 public Campaign getById( Long campaignId ) throws DaoException;
	 
	 public Long getCampaignByGarantia(Integer garantiaId ) throws DaoException;
	
	 public List listCampaignForSelect(String chassi ) throws DaoException;
	 
	 /** Lista as campanhas de um determinado produto pelo seu chassi, desde de que, 
	 *  o produto não tenha participado da campanha.
	 * 
	 * @param chassi
	 * 		<code>String</code> Chassi do produto.	
	 * 
	 * @return list
	 * 	<code>list</code> lista de campanhas.
	 * 
	 * @throws DaoException
	 */	
	 public List listCampaign(String chassi ) throws DaoException;
	
}

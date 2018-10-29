/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......FaturamentoBusiness.java
 *   .: Criação.....11 e maio, 14:55
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Objeto de negócios para a entidade "Faturamento".
 */
package br.com.yamaha.sistemagarantia.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.resource.infra.business.BusinessObject;
import br.com.resource.infra.dao.Dao;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.BusinessRuleException;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.model.Campaign;
import br.com.yamaha.sistemagarantia.model.CampaignBilling;
import br.com.yamaha.sistemagarantia.model.Concessionaria;
import br.com.yamaha.sistemagarantia.model.Faturamento;

/** Classe de negócios relacionada à entidade <b>Faturamento</b>.
 * 
 *  @author Edson Luiz Sumensari
 */
public class FaturamentoBusiness extends BusinessObject {

	/** Objeto DAO genérico para utilizado para este objeto de negócios. 
	 *  <p>
	 *  A entidade "Faturamento" não possui tarefas específicas, portanto, 
	 *  sua ponte com o banco de dados é feita utilizando um DAO padrão. 
	 */
	private Dao genericDao;

	/** Recupera uma Faturamento pelo id.
	 *
	 *  @param entityId
	 *      Referência da entidade na camada de persistência.
	 *  @return
	 *      Um objeto <code>Faturamento</code>.
	 *      
	 * @throws BusinessException
	 *      Se houverem erros de execução ou nas camadas 
	 *      abaixo dos serviços, serão encapsulados neste
	 *      tipo de <code>Exception</code>.
	 */
	public Faturamento get(Serializable entityId) throws BusinessException {

		try {

			return (Faturamento)genericDao.get(Faturamento.class, entityId);

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}

	}

	/** Obtém uma entidade faturamento pela propriedade <code>chassi<code>.
	 *  
	 *
	 *      
	 *  @param chassi
	 *  	<code>String<code>. Valor do parâmetro
	 *  
	 *  @return
	 *      Um <code>Faturamento</code> populado com os dados da 
	 *      entidade específica, solicitada, ou caso nenhuma entidade
	 *      seja encontrada, retornará <code>null</code>.
	 *      
	 *  @throws BusinessException
	 *      Se houverem problemas nas camadas inferiores, estes serão 
	 *      convertidos para uma DaoException.
	 */     
	public Faturamento getByChassi(String chassi) throws BusinessException,BusinessRuleException {

		try {

			Faturamento faturamento = (Faturamento)genericDao.getByParameter(Faturamento.class, "chassi", chassi.toUpperCase());

			return faturamento;


		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}


	}
	
	/** Obtém uma entidade faturamento pela propriedade <code>chassi<code> e restringe se ela já 
	 *  participou de uma determinada campanha.
	 *      
	 *  @param chassi
	 *  	<code>String<code>. Valor do parâmetro
	 *  
	 *  @param campaign
	 *  	<code>Campaign<code> Campanha que se quer verificar
	 *  
	 *  @return
	 *      Um <code>Faturamento</code> populado com os dados da 
	 *      entidade específica, solicitada, ou caso nenhuma entidade
	 *      seja encontrada dentro da regra, retornará <code>null</code>.
	 *      
	 *  @throws BusinessException
	 *      Se houverem problemas nas camadas inferiores, estes serão 
	 *      convertidos para uma DaoException.
	 */     
	public Faturamento getByChassiAndCampaign(String chassi, Campaign campaign) throws BusinessException,BusinessRuleException {

		try {

			Faturamento faturamento = (Faturamento)genericDao.getByParameter(Faturamento.class, "chassi", chassi.toUpperCase());
			List campaigns = (ArrayList)genericDao.listByParameter(CampaignBilling.class, "billing", faturamento);
			
			boolean isCampaign = false;
			Iterator it = campaigns.iterator();
			
			while ( it.hasNext() ) {
				
				CampaignBilling campaignBilling = (CampaignBilling) it.next();
				
				if ( campaignBilling.getCampaign().getEntityId().equals(campaign.getEntityId()) )
					isCampaign = true;
				
			} 
			
			if ( isCampaign )
				return null;
			else 
				return faturamento;

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}

	}

	/** Obtém uma entidade faturamento pela propriedade <code>chassi<code>.
	 *      
	 *  @param chassi
	 *  	<code>String<code>. Valor do parâmetro
	 *  
	 *  @return
	 *      Um <code>Faturamento</code> populado com os dados da 
	 *      entidade específica, solicitada, ou caso nenhuma entidade
	 *      seja encontrada, retornará <code>null</code>.
	 *      
	 *  @throws BusinessException
	 *      Se houverem problemas nas camadas inferiores, estes serão 
	 *      convertidos para uma DaoException.
	 */     
	public boolean isValidChassi(String chassi) throws BusinessException {

		try {

			Faturamento faturamento = (Faturamento)genericDao.getByParameter(Faturamento.class, "chassi", chassi.toUpperCase());

			return ( faturamento != null );

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}

	}	
	
	/** Recupera uma  lista de Faturamento por parte do chassi.
	 *
	 *  @param chassiPart
	 *      Parte do valor do chassi para filtro.
	 *  @return
	 *      Um objeto <code>List</code>.
	 *      
	 * @throws BusinessException
	 *      Se houverem erros de execução ou nas camadas 
	 *      abaixo dos serviços, serão encapsulados neste
	 *      tipo de <code>Exception</code>.
	 */
	public List listChassiByConcessionaria(String chassiPart, Concessionaria concessionaria) throws BusinessException {

		try {

			return genericDao.listApproachedByParameter(Faturamento.class, "concessionaria", concessionaria, "chassi", chassiPart.toUpperCase());

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}

	}

	/** Recupera uma lista de chassis de uma concessionária.
	 *
	 *  @return
	 *      Um objeto <code>List</code>.
	 *      
	 * @throws BusinessException
	 *      Se houverem erros de execução ou nas camadas 
	 *      abaixo dos serviços, serão encapsulados neste
	 *      tipo de <code>Exception</code>.
	 */	
	public List listChassiByConcessionaria(Concessionaria concessionaria) throws BusinessException {
		
		try {
			
			return genericDao.listByParameter(Faturamento.class, "concessionaria", concessionaria);
			
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


}
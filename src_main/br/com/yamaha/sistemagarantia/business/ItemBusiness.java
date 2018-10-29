/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......ItemBusiness.java
 *   .: Criação.....15 de junho, 18:22
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Objeto de negócios para a entidade "Item".
 */
package br.com.yamaha.sistemagarantia.business;

import java.io.Serializable;
import java.util.List;

import br.com.resource.infra.business.BusinessObject;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.dao.ItemDao;
import br.com.yamaha.sistemagarantia.model.Item;
import br.com.yamaha.sistemagarantia.model.Modelo;

/** Objeto de negócios para tarefas com a entidade "Item".
 * 
 *  @author Thiago Uriel M. Garcia
 */
public class ItemBusiness extends BusinessObject {

	/** Objeto DAO  para utilizado para este objeto de negócios. 
	 *  
	 */
	private ItemDao itemDao;
	
	
	/** Recupera uma item pelo id.
	 *
	 *  @param entityId
	 *      Referência da entidade na camada de persistência.
	 *  @return
	 *      Um objeto <code>Item</code>.
	 *      
	 * @throws BusinessException
	 *      Se houverem erros de execução ou nas camadas 
	 *      abaixo dos serviços, serão encapsulados neste
	 *      tipo de <code>Exception</code>.
	 */
	public Item get(Serializable entityId) throws BusinessException {

		try {

			return (Item)this.getItemDao().get(Item.class, entityId);

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}

	}
	
	/** Retorna a peça pai de uma peça
	 * 
	 * @param itemCodeCausadora
	 * 	<code>String</code> itemCode da peça causadora. 
	 * 
	 * @param itemCode
	 * 	<code>String</code> itemCode da Peca solicitada
	 * 
	 * @return Long
	 * 	<code>Quantidade</code> Quantidade de Peças do conjunto.
	 * 
	 * @throws BusinessException
	 *      Se houverem erros de execução ou nas camadas 
	 *      abaixo dos serviços, serão encapsulados neste
	 *      tipo de <code>Exception</code>.
	 */
	public Long getPecaPai(String itemCodeCausadora, String itemCode) throws BusinessException {
		
		try {
			
			return  this.getItemDao().getPecaPai(itemCodeCausadora, itemCode);
			
		} catch (DaoException dExp) {
			
			throw new BusinessException(dExp);
			
		}
		
		
		
		
	}
	
	/** Retorna verdadeiro se o produto é nacional
	 * 
	 * @param chassi
	 * 	<code>String</code>.
	 * 
	 * @return boolean
	 * 	<code>true</code>  se o produto é nacional.
	 * 
	 * @throws BusinessException
	 *      Se houverem erros de execução ou nas camadas 
	 *      abaixo dos serviços, serão encapsulados neste
	 *      tipo de <code>Exception</code>.
	 */
	public boolean isNacional(String chassi) throws BusinessException {
		
		try {
			
			return  this.getItemDao().isNacional(chassi);
			
		} catch (DaoException dExp) {
			
			throw new BusinessException(dExp);
			
		}
	
	}
	
	/** Retorna quantidade de peças de um determinadao chassi
	 * 
	 * @param modelo
	 * 	<code>String</code> modelo.
	 * 
	 * @param itemCode
	 * 	<code>String</code> Código do item.
	 * 
	 * @return int
	 * 	<code>Quantidade</code> Quantidade de Peças no chassi.
	 * 
	 * @throws BusinessException
	 *      Se houverem erros de execução ou nas camadas 
	 *      abaixo dos serviços, serão encapsulados neste
	 *      tipo de <code>Exception</code>.
	 */
	public int getQtdePecaByModelo(String modelo, String itemCode) throws BusinessException {
		
		try {
			
			return  this.getItemDao().getQtdePecaByModelo(modelo, itemCode);
			
		} catch (DaoException dExp) {
			
			throw new BusinessException(dExp);
			
		}
	
	}
	
	/** Lista items a partir de um determinado chassi.
	 * 
	 *  @param chassi
	 *  	Chassi a ser utilizado como referência.
	 * @param linhaId
	 *  	Linha do Produto.
	 *  
	 *  @return
	 *  	Lista com itens encontrados.
	 *  
	 *  @throws BusinessException
	 *      Se houverem erros de execução ou nas camadas 
	 *      abaixo dos serviços, serão encapsulados neste
	 *      tipo de <code>Exception</code>.  	
	 */
	public List listByChassi(String chassi, Long linhaId) throws BusinessException {

		try {
			
			return  this.getItemDao().listByChassi(chassi, linhaId);
			
		} catch (DaoException dExp) {
			
			throw new BusinessException(dExp);
			
		}
		
	}
	
	/** Lista items a partir de um determinado chassi.
	 * 
	 *  @param chassi
	 *  	Chassi a ser utilizado como referência.
	 *  
	 *  @param itemCodePart
	 *  	Parte ou completo código da peça para pesquisa.
	 *  
	 *  *  @param linhaId
	 *  	Linha do Produto.
	 *  
	 *  @return
	 *  	Lista com itens encontrados.
	 *  
	 *  @throws BusinessException
	 *      Se houverem erros de execução ou nas camadas 
	 *      abaixo dos serviços, serão encapsulados neste
	 *      tipo de <code>Exception</code>.  	
	 */
	public List listByChassi(String chassi, String itemCodePart, Long linhaId) throws BusinessException {

		try {
			
			return  this.getItemDao().listByChassi(chassi, itemCodePart, linhaId);
			
		} catch (DaoException dExp) {
			
			throw new BusinessException(dExp);
			
		}
		
	}
	
	/** Recupera uma entidade de Modelo.
	 * 
	 *  @param modelo
	 *  	Modelo a ser utilizado como referência.
	 *  
	 *  
	 *  @return
	 *  	uma entidade de Modelo.
	 *  
	 *  @throws BusinessException
	 *      Se houverem erros de execução ou nas camadas 
	 *      abaixo dos serviços, serão encapsulados neste
	 *      tipo de <code>Exception</code>.  	
	 */
	public Modelo getModelo(String modelo) throws BusinessException {

		try {
			
			return  (Modelo)this.getItemDao().getByParameter(Modelo.class, "modelo", modelo);
			
		} catch (DaoException dExp) {
			
			throw new BusinessException(dExp);
			
		}
		
	}
	
	/** Retorna um objeto item a partir de um itemId e organizationId e do segemento MTC
	 * 
	 * @param itemId
	 * 	<code>Long</code> 
	 * 
	 * @param organizationId
	 * 	<code>Long</code> 
	 * 
	 * @return item
	 * 	<code>Item</code> 
	 * 
	 * @throws DaoException
	 */
	public Item getItemByOrg( Long itemId, Long organizationId ) throws BusinessException {
	
		try {
			
			return this.getItemDao().getItemByOrg(itemId, organizationId);
		
		} catch (DaoException dExp) {
			
			throw new BusinessException(dExp);
			
		}
		
	}
	
	/** Retorna um objeto item a partir de um itemId e organizationId
	 * 
	 * @param itemId
	 * 	<code>Long</code> 
	 * 
	 * @param organizationId
	 * 	<code>Long</code> 
	 * 
	 * @return item
	 * 	<code>Item</code> 
	 * 
	 * @throws DaoException
	 */
	public Item getByOrg( Long ItemId, Long organizationId ) throws BusinessException {
	
		try {
			
			return this.getItemDao().getByOrg(ItemId, organizationId);
		
		} catch (DaoException dExp) {
			
			throw new BusinessException(dExp);
			
		}
		
	}
	
	/** Retorna um objeto item a partir de um itemId 
	 * 
	 * @param itemId
	 * 	<code>Long</code> 
	 *  
	 * @return item
	 * 	<code>Item</code> 
	 * 
	 * @throws DaoException
	 */
	public Item getItem( Long ItemId ) throws BusinessException {
	
		try {
			
			Item item = this.getItemDao().getByOrg(ItemId, new Long(91));
			
			if ( item == null )
				item = this.getItemDao().getByOrg(ItemId, new Long(89));
				
			return item;
		
		} catch (DaoException dExp) {
			
			throw new BusinessException(dExp);
			
		}
		
	}

	/** Método getter para a propriedade itemDao
	 *
	 *  @return ItemDao de itemDao
	 */
	public ItemDao getItemDao() {
		return itemDao;
	}

	/** Método setter para a propriedade itemDao
	 *
	 * @param itemDao ItemDao
	 */
	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}
	
	
}
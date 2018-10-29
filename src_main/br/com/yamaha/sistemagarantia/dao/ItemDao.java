/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RevisaoDao.java
 *   .: Criação.....30 de junho, 13:49
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Interface DAO para a entidade "Item".
 */
package br.com.yamaha.sistemagarantia.dao;

import java.util.List;

import br.com.resource.infra.dao.Dao;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.model.Item;

/** Interface contendo os métodos especializados para
 *  as entidades <b>Item</b>.
 *  
 *  @author Edson Luiz Sumensari
 */
public interface ItemDao extends Dao {
	
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
	public Long getPecaPai(String itemCodeCausadora, String itemCode) throws DaoException;
		
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
	public int getQtdePecaByModelo(String modelo, String itemCode) throws DaoException;
	
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
	public boolean isNacional(String chassi) throws DaoException;
	
	/** Lista items a partir de um determinado chassi.
	 * 
	 *  @param chassi
	 *  	Chassi a ser utilizado como referência.
	 *  
	 *  @param linhaId
	 *  	Linha do Produto
	 *  
	 *  @return
	 *  	Lista com itens encontrados.
	 *  
	 *  @throws BusinessException
	 *      Se houverem erros de execução ou nas camadas 
	 *      abaixo dos serviços, serão encapsulados neste
	 *      tipo de <code>Exception</code>.  	
	 */
	public List listByChassi(String chassi, Long linhaId) throws DaoException;
	
	/** Lista items a partir de um determinado chassi.
	 * 
	 *  @param chassi
	 *  	Chassi a ser utilizado como referência.
	 *  
	 *  @param itemCodePart
	 *  	Parte ou completo código da peça para pesquisa.
	 *  
	 *  @param linhaId
	 *  	Linha do produto
	 *  
	 *  @return
	 *  	Lista com itens encontrados.
	 *  
	 *  @throws BusinessException
	 *      Se houverem erros de execução ou nas camadas 
	 *      abaixo dos serviços, serão encapsulados neste
	 *      tipo de <code>Exception</code>.  	
	 */
	public List listByChassi(String chassi, String itemCodePart, Long linhaId) throws DaoException;
	
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
	public Item getItemByOrg( Long ItemId, Long organizationId ) throws DaoException;
    
	
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
	public Item getByOrg( Long ItemId, Long organizationId ) throws DaoException;
	
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
	public Item getItem( Long ItemId ) throws DaoException;
}
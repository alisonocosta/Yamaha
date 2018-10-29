/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......LoteDao.java
 *   .: Cria��o.....28 de junho, 06:45
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Interface DAO para a entidade "Lote".
 */
package br.com.yamaha.sistemagarantia.dao;

import java.io.Serializable;
import java.util.List;

import br.com.resource.infra.dao.Dao;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.model.Concessionaria;
import br.com.yamaha.sistemagarantia.model.Lote;
import br.com.yamaha.sistemagarantia.view.LoteCompactVO;

/** Interface contendo os m�todos especializados para
 *  as entidades <b>Lote</b>.
 *  
 *  @author Edson Luiz Sumensari
 */
public interface LoteDao extends Dao {

    /** Obt�m um cliente a partir de seu CPF ou CNPJ.
     * 
     *  @param status
     *      <code>List</code> Uma entidade que representa o status  de um lote.
     *      
     *  @param concessionaria
     *  	<code>Concessionaria</code> Uma entidade que representa aconcession�ria do lote.
     *  
     *  @return
     *      <code>List</code> Uma lista de lotes de uma concession�ria de acordo com o status.
     *  
     *  @throws DaoException
     *      Se houverem erros durante a execu��o estes ser�o lan�ados
     *      como uma Exception deste tipo.
     */
    public List listByStatus(List status, Concessionaria concessionaria) throws DaoException;    
    
    /** Recupera um lote pelo id.
    *
    *  @param entityId
    *      Refer�ncia da entidade na camada de persist�ncia.
    *      
    *  @param concessionaria
    *      Concession�ria do usu�rio.
    *      
    *  @return
    *      Um objeto <code>Lote</code>.
    *      
    * @throws BusinessException
    *      Se houverem erros de execu��o ou nas camadas 
    *      abaixo dos servi�os, ser�o encapsulados neste
    *      tipo de <code>Exception</code>.
    */
   public Lote getById(Serializable entityId, Concessionaria concessionaria) throws DaoException;
   
   /** Recupera um lote pelo id.
   *
   *  @param entityId
   *      Refer�ncia da entidade na camada de persist�ncia.
   *      
   *  @param concessionaria
   *      Concession�ria do usu�rio.
   *      
   *  @return
   *      Um objeto <code>Lote</code>.
   *      
   * @throws BusinessException
   *      Se houverem erros de execu��o ou nas camadas 
   *      abaixo dos servi�os, ser�o encapsulados neste
   *      tipo de <code>Exception</code>.
   */
  public LoteCompactVO getByIdList(Serializable entityId, Concessionaria concessionaria) throws DaoException;
   
   /** Lista de lotes Novo.
    * 
    *  @param status
    *      <code>List</code> Uma entidade que representa o status  de um lote.
    *      
    *  @param concessionaria
    *  	<code>Concessionaria</code> Uma entidade que representa aconcession�ria do lote.
    *  
    *  @return
    *      <code>List</code> Uma lista de lotes de uma concession�ria de acordo com o status.
    *  
    *  @throws DaoException
    *      Se houverem erros durante a execu��o estes ser�o lan�ados
    *      como uma Exception deste tipo.
    */
	public List listlotes(List status, Concessionaria concessionaria) throws DaoException;
	
	/** Lista de Garantias de um lote para lan�amento de NF.
    * 
    *  @param entityId
    *  	<code>Serializable</code> Id do Lote.
    *  
    *  @return
    *      <code>List</code> Uma lista de Garantias.
    *  
    *  @throws DaoException
    *      Se houverem erros durante a execu��o estes ser�o lan�ados
    *      como uma Exception deste tipo.
    */
	public List listGaranatiasToNF(Serializable entityId) throws DaoException;
	
	/** Lista de Garantias da Concessionaria em Manuten��o por mais de 2 dias
    * 
    *  @param concessionariaId
    *  	<code>Serializable</code> Id da Concessionaria.
    *  
    *  @return
    *      <code>boolean</code> True quando existir garantia.
    *  
    *  @throws DaoException
    *      Se houverem erros durante a execu��o estes ser�o lan�ados
    *      como uma Exception deste tipo.
    */
	public boolean listGaranatiasInManut(Serializable concessionariaId) throws DaoException;
    
      
}
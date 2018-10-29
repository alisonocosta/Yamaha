/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......GarantiaHeaderDao.java
 *   .: Cria��o.....01 de maio, 12:40
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Interface DAO para a entidade "GarantiaHeader".
 */
package br.com.yamaha.sistemagarantia.dao;

import java.util.List;

import br.com.resource.infra.dao.Dao;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.model.AlertGarantia;
import br.com.yamaha.sistemagarantia.model.CampaignBilling;
import br.com.yamaha.sistemagarantia.model.Concessionaria;
import br.com.yamaha.sistemagarantia.model.Cupom;
import br.com.yamaha.sistemagarantia.model.GarantiaHeader;
import br.com.yamaha.sistemagarantia.model.GarantiaLine;
import br.com.yamaha.sistemagarantia.model.InfoMercGarantia;
import br.com.yamaha.sistemagarantia.model.Linha;
import br.com.yamaha.sistemagarantia.model.Lote;
import br.com.yamaha.sistemagarantia.model.Servico;
import br.com.yamaha.sistemagarantia.model.StatusLote;
import br.com.yamaha.sistemagarantia.model.TempoPadrao;
import br.com.yamaha.sistemagarantia.model.Usuario;
import br.com.yamaha.sistemagarantia.model.ValorServico;
import br.com.yamaha.sistemagarantia.model.id.GarantiaLineId;

/** Interface contendo os m�todos especializados para
 *  as entidades <b>GarantiaHeader</b>.
 *  
 *  @author Edson Luiz Sumensari
 */
public interface GarantiaHeaderDao extends Dao {
	
	/** 
    * recupera um lote de garantias e suas depend�ncias.
    *      
    *  @param entityId
    *  	<code>Integer</code> Id do lote.
    *  
    *  @return
    *      <code>Lote</code> Uma entidade de Lote.
    *  
    *  @throws DaoException
    *      Se houverem erros durante a execu��o estes ser�o lan�ados
    *      como uma Exception deste tipo.
    */
	public Lote getLote(Integer entityId) throws DaoException;

	/** Determina se j� houveram garantias cobradas.
	 * 
	 *  @param itemId
	 *  @param modelo
	 *  @return
	 *  @throws DaoException
	 */
	public boolean hasGarantiasCobradas( long itemId, String modelo ) throws DaoException;
	
	/** Obt�m uma lista de garantias para relacionar com notas fiscais.
    *
    *  @param concessionaria
    *  	<code>Concessionaria</code> entidade concession�ria
    *  
    *  @param statusLote
    *  	<code>StatusLote</code> entidade statusLote
    *  
    *  @throws DaoException
    *      Se houverem erros durante a execu��o estes ser�o lan�ados
    *      como uma Exception deste tipo.
    */
   public List listGarantias( Concessionaria concessionaria, StatusLote statusLote) throws DaoException;
   
   /** Recupera a lista de servi�os aproximada de uma determinada linha.
	 *
	 *  @param code
	 *      <code>String<code> Valor aproximado do c�digo do servi�o.
	 *      
	 *  @param linhaId
	 *      <code>Long<code> id da linha do produto.
	 *  
	 *  @return
	 *      Um <code>List</code> lista de pe�as.
	 *      
	 * @throws BusinessException
	 *      Se houverem erros de execu��o ou nas camadas 
	 *      abaixo dos servi�os, ser�o encapsulados neste
	 *      tipo de <code>Exception</code>.
	 */	
	public List listApproachServices(String code, Long linhaId) throws DaoException;
	
	/** Recupera a lista de servi�os aproximada de um determinado chassi.
	 *
	 *  @param code
	 *      <code>String<code> Valor aproximado do c�digo do servi�o.
	 *      
	 *  @param chassi
	 *      <code>String<code> chassi.
	 *  
	 *  @return
	 *      Um <code>List</code> lista de pe�as.
	 *      
	 * @throws BusinessException
	 *      Se houverem erros de execu��o ou nas camadas 
	 *      abaixo dos servi�os, ser�o encapsulados neste
	 *      tipo de <code>Exception</code>.
	 */	
	public List listApproachServicesByChassi(String partCode, String chassi) throws DaoException;
   
   /** Recupera a lista de pe�as da garantia relacionada.
	 *
	 *  @param garantiaId
	 *      <code>Integer<code> id da entidade garantia relacionada.
	 *  @return
	 *      Um <code>List</code> lista de pe�as.
	 *      
	 * @throws BusinessException
	 *      Se houverem erros de execu��o ou nas camadas 
	 *      abaixo dos servi�os, ser�o encapsulados neste
	 *      tipo de <code>Exception</code>.
	 */	
	public List listPecas(GarantiaHeader garantia) throws DaoException;
   
   /** Verifica se existem garantias com status de mov. com id 6
    * 
    * @param lote
    * 	<code>Lote</code> Lote para verifica��o
    * 
    * @return boolean
    * 	<code>boolean</code> true quando existir garantia
    * 
    * @throws DaoException
    */
   public boolean getGarantiasByLote( Lote lote ) throws DaoException;
   
   /** recupera um entidade de CampaignBilling de um Garantia
	 * 
	 * @param garantia
	 * 	<code>GarantiaHeader</code> Entidade de GarantiaHeader
	 * 
	 * @return campaignBilling
	 * 	<code>CampaignBilling</code> Quando a garantia for de uma campanha
	 * 
	 * @throws DaoException
	 */
	public CampaignBilling getCampaignBillingByGarantia( GarantiaHeader garantia ) throws DaoException;
   
   /** Obt�m uma entidade de Servico a partir de seu ID.
	 * 
	 *  @param entityId
	 *      <code>Long<code>. Id da entidade.
	 *      	    
	 *  @return
	 *      Um <code>Servico</code> populado com os dados da 
	 *      entidade espec�fica, solicitada, ou caso nenhuma entidade
	 *      seja encontrada, retornar� <code>null</code>.
	 *      
	 *  @throws DaoException
	 *      Se houverem problemas nas camadas inferiores, estes ser�o 
	 *      convertidos para uma DaoException.
	 */     
	public Servico getServicoById(Long servicoId) throws DaoException;
	
	/** Obt�m uma entidade de Servico a partir de seu servicoCode.
	 * 
	 *  @param servicoCode
	 *      <code>String<code>. C�digo do Servi�o.
	 *      	    
	 *  @return
	 *      Um <code>Servico</code> populado com os dados da 
	 *      entidade espec�fica, solicitada, ou caso nenhuma entidade
	 *      seja encontrada, retornar� <code>null</code>.
	 *      
	 *  @throws DaoException
	 *      Se houverem problemas nas camadas inferiores, estes ser�o 
	 *      convertidos para uma DaoException.
	 */     
	public Servico getServicoByCode(String servicoCode, Linha linha) throws DaoException;
	
	/** Recupera uma Entidade de servi�o pelo c�digo e um determinado chassi.
	 *
	 *  @param code
	 *      <code>String<code> Valor do c�digo do servi�o.
	 *      
	 *  @param chassi
	 *      <code>String<code> chassi.
	 *      	 
	 *  @param linha
	 *      <code>Linha<code> linha do produto.
	 *  
	 *  @return
	 *      Um <code>Servico</code> entidade de Servico.
	 *      
	 * @throws BusinessException
	 *      Se houverem erros de execu��o ou nas camadas 
	 *      abaixo dos servi�os, ser�o encapsulados neste
	 *      tipo de <code>Exception</code>.
	 */	
	public Servico getServiceByChassi(String code, String chassi, Linha Linha) throws DaoException;
   
   /** Obt�m uma lista de  Recall a partir do chassi.
	 * 
	 *  @param chassi
	 *      <code>String<code>     
	 *
	 *  @return
	 *      Um <code>List</code> populado com os objetos da 
	 *      entidade espec�fica, solicitada, ou caso nenhuma entidade
	 *      seja encontrada, retornar� <code>null</code>.
	 *      
	 *  @throws DaoException
	 *      Se houverem problemas nas camadas inferiores, estes ser�o 
	 *      convertidos para uma DaoException.
	 */     
	public List listRecall(String chassi) throws DaoException;
	
	/** Obt�m uma lista de  Recall n�o executados a partir do chassi.
	 * 
	 *  @param chassi
	 *      <code>String<code>     
	 *
	 *  @return
	 *      Um <code>List</code> populado com os objetos da 
	 *      entidade espec�fica, solicitada, ou caso nenhuma entidade
	 *      seja encontrada, retornar� <code>null</code>.
	 *      
	 *  @throws DaoException
	 *      Se houverem problemas nas camadas inferiores, estes ser�o 
	 *      convertidos para uma DaoException.
	 */     
	public List listRecallNotExByChassi(String chassi) throws DaoException;
   
   /** Lista as revis�es anteriores a enviada existentes no banco de dados de acordo
    *  com o modelo.
    *
    * @param garantia
    *   <code>GarantiaHeader</code> Uma Entidade de Garantia.
    *   
    *  @param linha
    *   <code>Linha</code> Uma Entidade de Linha.
    * 
    *  @return
    *      Um valor <code>AlertGarantia</code> null para informar que todas as revis�es est�o ok.
    *      
    * @throws DaoException
    *      Se houverem erros de execu��o ou nas camadas 
    *      abaixo dos servi�os, ser�o encapsulados neste
    *      tipo de <code>Exception</code>.
    */
   public AlertGarantia isRevisaoAnterior(GarantiaHeader garantia, Linha linha ) throws DaoException;
   
   /** retorna o cupom de revisao para um modelo de chassi
    * 
    * @param modelo
    * 	<code>String</code> Valor do chassi.
    * 
    * @param numeroRevisao
    * 	<code>Long</code>  N�mero da Revis�o.
    * 
    * @return cupom
    * 	<code>Cupom</code>  Uma entidade de Cupom.
    * 
    * @throws DaoException
    * 	Se houverem erros de execu��o ou nas camadas 
    *      abaixo dos servi�os, ser�o encapsulados neste
    *      tipo de <code>Exception</code>.
    */
   public Cupom getCupomByRevisao(String modelo, Long numeroRevisao)throws DaoException;
   
   /** Recupera um entidade de Tempo Padr�o para um servi�o
    *  Necess�rio para para definir o valor de servi�o para garantia
    *  
    * @param servico
    * 	<code>Servico</code>
    * 
    * @param chassi
    * 	<code>String</code>
    * 
    * @return tempoPadrao
    * 	<code>TempoPadrao</code> uma Entidade de TempoPadrao.
    * 
    * @throws DaoException
    * 	Se houverem erros de execu��o ou nas camadas 
    *   abaixo dos servi�os, ser�o encapsulados neste
    *   tipo de <code>Exception</code>.
    */
   public TempoPadrao getTempoPadrao(Servico servico, String chassi) throws DaoException;
   
   
   /** Recupera um entidade de ValorServico para um servi�o
	 *  Necess�rio para para definir o valor de servi�o para garantia
	 *  
	 * @param codRegiao
	 * 	<code>Long</code> C�digo da regi�o da concession�ria
	 * 
	 * @param chassi
	 * 	<code>String</code>
	 * 
	 * @return valorServico
	 * 	<code>ValorServico</code> uma Entidade de ValorServico.
	 * 
	 * @throws DaoException
	 * 	Se houverem erros de execu��o ou nas camadas 
	 *   abaixo dos servi�os, ser�o encapsulados neste
	 *   tipo de <code>BusinessException</code>.
	 */
   public ValorServico getValorServico(Long codRegiao, String chassi) throws DaoException;
   
   /** Verifica se j� existe uma garantia cadastrada com os par�metros passados
	 *  
	 * @param garantia
	 * 	<code>GarantiaHeader</code> Entidade de Garantia com os par�metros para valida��o.
	 * 
	 * @return boolean
	 * 	<code>TRUE</code> quando j� existir uma garantia.
	 * 		
	 * @throws DaoException
	 * 	Se houverem erros de execu��o ou nas camadas 
	 *   abaixo dos servi�os, ser�o encapsulados neste
	 *   tipo de <code>BusinessException</code>.
	 */
  public boolean isEcxistsGarantia( GarantiaHeader garantia ) throws DaoException;  
  
  /** Recupera uma GarantiaLine pelo id.
	 *
	 *  @param compositeId
	 *      Refer�ncia da entidade na camada de persist�ncia.
	 *  @return
	 *      Um objeto <code>GarantiaLine</code>.
	 *      
	 * @throws DaoException
	 *      Se houverem erros de execu��o ou nas camadas 
	 *      abaixo dos servi�os, ser�o encapsulados neste
	 *      tipo de <code>Exception</code>.
	 */	
	public GarantiaLine getGarantiaLine(GarantiaLineId compositeId) throws DaoException;  
	
	/** Remo��o l�gica uma Solicita��o de Garantia e seus dependentes.
	 * 
	 *  @param garatnia
	 *  @param usuario
	 *  @throws DaoException
	 */
	public void remove(GarantiaHeader garantia, Usuario usuario) throws DaoException;
	
	/** Recupera um entidade de InfoMercGarantia de um Garantia
	 * 
	 * @param garantia
	 * 	<code>InfoMercGarantia</code> populado com a Garantia e InfoMercado associada
	 * 
	 * @return InfoMercGarantia
	 * 	<code>InfoMercGarantia</code> Quando a garantia for de uma InfoMercGarantia
	 * 
	 * @throws DaoException
	 */
	public InfoMercGarantia getInfoMercGarantia( InfoMercGarantia infoMercGarantia ) throws DaoException;
	
	/** Obt�m uma entidade de GarantiaLine a partir de seu id composto e da empresa do faturamento.
	 * 
	 *  @param garantiaLineId
	 *      <code>GarantiaLineId<code>. Entidade com o id composto.
	 *      
	 *  @param organizationId
	 *      <code>Long<code> id da empresa do faturamento.
	 *      	    
	 *  @return
	 *      Um <code>GarantiaLineId</code> populado com os dados da 
	 *      entidade espec�fica, solicitada, ou caso nenhuma entidade
	 *      seja encontrada, retornar� <code>null</code>.
	 *      
	 *  @throws DaoException
	 *      Se houverem problemas nas camadas inferiores, estes ser�o 
	 *      convertidos para uma DaoException.
	 */     
	public GarantiaLine getGarantiaLine(GarantiaLineId garantiaLineId, Long organizationId) throws DaoException;
   
}
/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......CupomDao.java
 *   .: Criação.....31 de maio, 12:59
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Interface CupomDao para a entidade "Cupom".
 */
package br.com.yamaha.sistemagarantia.dao;

import java.io.Serializable;
import java.util.List;

import br.com.resource.infra.dao.Dao;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.model.Concessionaria;
import br.com.yamaha.sistemagarantia.model.Cupom;
import br.com.yamaha.sistemagarantia.model.Faturamento;
import br.com.yamaha.sistemagarantia.model.Linha;
import br.com.yamaha.sistemagarantia.model.Lote;
import br.com.yamaha.sistemagarantia.model.Revisao;
import br.com.yamaha.sistemagarantia.model.StatusLote;
import br.com.yamaha.sistemagarantia.model.ValorServico;

/** Interface contendo os métodos especializados para
 *  as entidades <b>Cupom</b>.
 *  
 *  @author Edson Luiz Sumensari
 */
public interface CupomDao extends Dao {
		
	/** Obtém uma lista de cupons de um determinado lote.
	*  
	*  @throws DaoException
	*      Se houverem erros durante a execução estes serão lançados
	*      como uma Exception deste tipo.
	*/
   public List listCuponsByLote( Serializable loteId ) throws DaoException;
	
    /** Obtém uma lista de cupons para relacionar com notas fiscais.
     *
     *  @param concessionaria
     *  	<code>Concessionaria</code> entidade concessionária
     *  
     *  @param statusLote
     *  	<code>StatusLote</code> entidade statusLote
     *  
     *  @throws DaoException
     *      Se houverem erros durante a execução estes serão lançados
     *      como uma Exception deste tipo.
     */
    public List listCupons( Concessionaria concessionaria, StatusLote statusLote) throws DaoException;    
    
    /** Verifica se existem cupons com status de mov. com id 6
     * 
     * @param lote
     * 	<code>Lote</code> Lote para verificação
     * 
     * @return boolean
     * 	<code>boolean</code> true quando existir cupom
     * 
     * @throws DaoException
     */
    public boolean getCuponsByLote( Lote lote ) throws DaoException;
    
    /** Retorna uma entidade de cupom de acordo com um chassi e revisao
     * 
     * @param chassi
     * 	<code>String</code> Chassi informado
     * 
     * @param linha
     * 	<code>Linha</code> Linha do produto
     * 
     * @return boolean
     * 	<code>Faturamento</code> uma entidade de Faturamento ou null se não encontrar
     * 
     * @throws DaoException
     */
    public Cupom getCupomByChassiAndRevisao( String chassi,Revisao revisao ) throws DaoException;
    
    /** Retorna uma entidade de faturamento de acordo com um chassi e linha do produto
     * 
     * @param chassi
     * 	<code>String</code> Chassi informado
     * 
     * @param linha
     * 	<code>Linha</code> Linha do produto
     * 
     * @return boolean
     * 	<code>Faturamento</code> uma entidade de Faturamento ou null se não encontrar
     * 
     * @throws DaoException
     */
    public Faturamento getFaturamentoByLinha( String chassi,Linha linha ) throws DaoException;
    
    /** Recupera um objeto ValorServico para o tipo revisao, modelo e região
     * 
     * @param revisao
     * 	<code>Revisao</code> ID da revisão
     * 
     * @param modelo
     * 	<code>String</code> Modelo do produto - 5 primeiras posições do chassi
     * 
     * @param regiao
     * 	<code>Long</code> regiao_moto ou regiao_nautica da concessionária
     * 
     * @return ValorServico
     * 	Retorna <code>null</code> quando existir valor conforme os parâmetros enviados
     * 
     * @throws DaoException
     *      Se houverem erros durante a execução estes serão lançados
     *      como uma Exception deste tipo.
     */
    public ValorServico getValorServico(Revisao revisao, String modelo, Long regiao) throws DaoException;
    
    /** Obtém uma lista de chassi de uma concessionária e lote.
     * 
     * @param chassiPart
     *  <code>String</code> Chassi ou parte.
     *
     *  @param concessionaria
     *  	<code>Concessionaria</code> entidade concessionária
     *  
     *  @param loteId
     *  	<code>Long</code> Id do lote.
     *    
     *  @throws DaoException
     *      Se houverem erros durante a execução estes serão lançados
     *      como uma Exception deste tipo.
     */
    public List listApproachChassiByLote(String chassiPart, Concessionaria concessionaria, Long loteId ) throws DaoException;  
    
    /** Lista faturamentos a partir de uma linha e parte de um chassi.
     *  
     *  @param chassiPart
     *  	Parte do chassi a ser buscado
     *  @param linhaId
     *  	Identiicador da linha
     *  
     *  @return
     *  	Lista de faturamentos
     *  
     *  @throws DaoException
     *      Se houverem erros durante a execução estes serão lançados
     *      como uma Exception deste tipo.
     */
    public List listFaturamentoByLinhaAndChassi(String chassiPart, Serializable linhaId) throws DaoException;    
    
    /** Retorna uma lista de enitdades de faturamento de acordo com um chassi e linha do produto
     * 
     * @param chassiPart
     * 	<code>String</code> Chassi informado ou parte do valor do chassi
     * 
     * @param concessionaria
     * 	<code>Concessionaria</code> Entidade de Concessionaria 
     * 
     * @param linhaId
     * 	<code>Long</code> entityId da Linha do produto
     * 
     * @return 
     * 	<code>List</code> uma lista de entidades de Faturamento
     * 
     * @throws DaoException
     */
    public List listApproachFaturamentoByLinha( String chassiPart, Concessionaria concessionaria, Long linhaId ) throws DaoException;
    
    /** Retorna a última kilometragem registrada 
     * 
     * @param chassi
     * @return Long
     * @throws DaoException
     */
    public Long getLastRevision(String chassi)throws DaoException;
    
    /** Retorna a última kilometragem registrada, com exceção da garantia enviada
     *  Para os casos de alteração da quilometragem da Garantia
     *  
     * @param chassi
     * @param garantiaId
     * @return Long
     * @throws DaoException
     */
    public Long getLastRevision(String chassi, Long garantiaId)throws DaoException;
    
    /** Retorna um cupom de revisao de acordo com o número da revisão e para modelo informados
     * 
     * @param chassi
     * @param numeroRevisao
     * @return
     * @throws DaoException
     */
    public Cupom getCupomByRevisao(String chassi, Long numeroRevisao)throws DaoException;
    
}
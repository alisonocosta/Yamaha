/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......notaFiscalDao.java
 *   .: Cria��o.....28 de julho, 17:41
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Interface DAO para a entidade "NotaFiscal".
 */
package br.com.yamaha.sistemagarantia.dao;

import java.util.List;

import br.com.resource.infra.dao.Dao;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.model.Concessionaria;
import br.com.yamaha.sistemagarantia.model.Cupom;
import br.com.yamaha.sistemagarantia.model.Faturamento;
import br.com.yamaha.sistemagarantia.model.GarantiaHeader;
import br.com.yamaha.sistemagarantia.model.NotaFiscal;
import br.com.yamaha.sistemagarantia.model.to.NotaFiscalLancamentoTO;

/** Interface contendo os m�todos especializados para
 *  as entidades <b>NotaFiscal</b>.
 *  
 *  @author Edson Luiz Sumensari
 */
public interface NotaFiscalDao extends Dao {
 
	/**
	 * Verifica se um cupom j� possui nota fiscal lan�ada
	 * 
	 * @param cupomId Long
	 * @return boolean true quando existir nota fiscal para o cupom
	 * @throws DaoException
	 */
	public boolean hasNotaByCupom(Long cupomId)throws DaoException;
	
    /** Retorna uma lista de lotes dispon�veis para lana�amento de notas.
     *
     *  
     *  @return
     *      Retorna uma lista de lotes dispon�veis para lana�amento de notas.
     *  
     *  @throws DaoException
     *      Se houverem erros durante a execu��o estes ser�o lan�ados
     *      como uma Exception deste tipo.
     */
    public List listLotesDisp( Concessionaria concessionaria) throws DaoException;    
    
    /** Retorna uma lista dos cupons de um lote para lan�amento de Nota Fiscal.
     * 
     * @param cupons  
     * 	<code>String</code> - Lista de n�mero de cupons selecionados
     *  
     *  @return
     *      Retorna uma lista de objetos CupomNFTO.
     *  
     *  @throws DaoException
     *      Se houverem erros durante a execu��o estes ser�o lan�ados
     *      como uma Exception deste tipo.
     */
    public NotaFiscalLancamentoTO listCuponsForNota( String cupons ) throws DaoException;   
    
    /** Retorna uma lista das SG's de um lote para lan�amento de Nota Fiscal.
     * 
     * @param garantias 
     * 	<code>String</code> - Lista de n�mero das SG's selecionadas
     *  
     *  @return
     *      NotaFiscalLancamentoTO.
     *  
     *  @throws DaoException
     *      Se houverem erros durante a execu��o estes ser�o lan�ados
     *      como uma Exception deste tipo.
     */
    public NotaFiscalLancamentoTO listGarantiasForNota( String garantias ) throws DaoException;
    
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
    
    /** Retorna uma lista dos itens de um lote.
     * 
     * @param cupons  
     * 	<code>String</code> - Lista de n�mero de cupons selecionados
     * 
     * @param garantias  
     * 	<code>String</code> - Lista de n�mero de garantias selecionados	
     *  
     *  @return
     *      Retorna uma lista de objetos NotaFiscalVO.
     *  
     *  @throws DaoException
     *      Se houverem erros durante a execu��o estes ser�o lan�ados
     *      como uma Exception deste tipo.
     */
    public List listItensLote( String cupons, String garantias ) throws DaoException;
    
    /** Retorna um objeto NotaFiscalVO do cupom enviado.
     * 
     * @param cupom
     * 	<code>Cupom</code> Entidade de cupom 
     *  
     *  @return
     *      Retorna uma lista de objetos NotaFiscalVO ou nulo.
     *  
     *  @throws DaoException
     *      Se houverem erros durante a execu��o estes ser�o lan�ados
     *      como uma Exception deste tipo.
     */
    public List getItembyCupom( Cupom cupom ) throws DaoException;
    
    /** Retorna um objeto NotaFiscalVO da garantia enviada.
     * 
     * @param garantia
     * 	<code>Garantia</code> Entidade de garantia 
     *  
     *  @return
     *      Retorna uma lista de objetos NotaFiscalVO ou nulo.
     *  
     *  @throws DaoException
     *      Se houverem erros durante a execu��o estes ser�o lan�ados
     *      como uma Exception deste tipo.
     */
    public List getItembyGarantia( GarantiaHeader garantia ) throws DaoException;
    
    /** Recupera uma NotaFiscal pelo n�mero e concession�ria
     * 
     * @param numeroNF
     * @param concessionaria
     * @return NotaFiscal
     * @throws DaoException
     */
    public NotaFiscal getNotaFiscalByNumberAndConcessionaria(Long numeroNF, Concessionaria concessionaria ) throws DaoException;
    
    /** Recupera uma NotaFiscal pelo n�mero, s�rie e concession�ria
     * 
     * @param numeroNF
     * @param serieNF
     * @param concessionaria
     * @return NotaFiscal
     * @throws DaoException
     */
    public NotaFiscal getNotaFiscalByNumberAndSerieAndConcessionaria(Long numeroNF, String serie, Concessionaria concessionaria ) throws DaoException; 
    
    /** Recupera um Faturamento pelo chassi
     * 
     * @param chassi
     * @return faturamento
     * @throws DaoException
     */
    public Faturamento getFaturamentoByChassi(String chassi ) throws DaoException;
}
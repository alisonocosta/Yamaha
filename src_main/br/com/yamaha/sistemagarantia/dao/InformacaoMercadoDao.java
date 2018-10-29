/* Resource Inform�tica
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......InformacaoMercadoDao.java
 *   .: Cria��o.....01 de maio, 12:40
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descri��o...Interface DAO para a entidade "InformacaoMercado".
 */
package br.com.yamaha.sistemagarantia.dao;

import java.io.Serializable;
import java.sql.Blob;
import java.util.List;

import br.com.resource.infra.dao.Dao;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.BusinessRuleException;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.model.Concessionaria;
import br.com.yamaha.sistemagarantia.model.FatorGarantia;
import br.com.yamaha.sistemagarantia.model.GarantiaHeader;
import br.com.yamaha.sistemagarantia.model.GarantiaLine;
import br.com.yamaha.sistemagarantia.model.InfoMercado;
import br.com.yamaha.sistemagarantia.model.Lote;
import br.com.yamaha.sistemagarantia.model.Sintoma;

/** Interface Dao para informa��es de mercado.
 * 
 *  @author Thiago Uriel M. Garcia
 */
public interface InformacaoMercadoDao extends Dao {

	/** Filtro indicando chassi. */
	public static final int FILTER_CHASSI = 1;
	
	/** Filtro indicando status. */
	public static final int FILTER_STATUS = 2;
	
	/**
	 * 
	 * @param infoMercado
	 * @param lote
	 * @param garantia
	 * @param garantiaLine
	 * @param sintoma
	 * @param causaProblema
	 * @param diagnostico
	 * @param solucao
	 * @param userId
	 * @param isEdit
	 * @return InfoMercado.
	 * @throws DaoException
	 */
	public InfoMercado saveWithGarantia(InfoMercado infoMercado
						, Lote lote
						, GarantiaHeader garantia
						, GarantiaLine garantiaLine
						, String sintoma
						, String causaProblema
						, String diagnostico
						, String solucao
						, Serializable userId
						, boolean isEdit) throws DaoException;
	
	/**
	 * 
	 * @param infoMercado
	 * @param sintoma
	 * @param causaProblema
	 * @param diagnostico
	 * @param solucao
	 * @param userId
	 * @param isEdit
	 * @return boolean TRUE se a entidade for salva com sucesso.
	 * @throws BusinessException
	 * @throws BusinessRuleException
	 */
	public boolean save(InfoMercado infoMercado
						, String sintoma
						, String causaProblema
						, String diagnostico
						, String solucao
						, Serializable userId 
						, boolean isEdit) throws DaoException;
	
	/** Implementa��o do m�todo: getSintoma. */
    public Sintoma getSintoma(String codigo, Long linhaId) throws DaoException;
    
    /** Implementa��o do m�todo: getFatorGarantia. */
    public FatorGarantia getFatorGarantia(String uf) throws DaoException;
	
    /** Lista informa��es de mercado para uma concession�ria. 
     * 
     *  @param concessionaria
     *  @return
     *  @throws DaoException
     */
    public List listByConcessionaria(Concessionaria concessionaria) throws DaoException;
    
    /** Lista informa��es de mercado para uma concession�ria, aplicando
     *  filtros fornecidos. 
     * 
     *  @param concessionaria
     *  	Concession�ria alvo.
     *  
     *  @param filterType
     *  	Tipo de filtro de acordo com constantes da classe.
     *  
     *  @param filterValue
     *  	Valor utilizado no filtro.
     *  
     *  @return
     *  	Lista filtrada de informa��es de Mercado.
     *  
     *  @throws DaoException
     */
    public List listByConcessionaria(Concessionaria concessionaria, int filterType, String filterValue) throws DaoException;
    
    /** Retorna o conte�do do campo content de uma entidade InforMercFotos
     * 
     * @param infoMercFotosId
     * @return Blob
     * @throws BusinessException
     */
    public Blob getContentFile( Long infoMercFotosId ) throws DaoException;
}
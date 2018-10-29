/* Yamaha
 * Copyright (c) 2012 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......AnaliseGarantiaDao.java
 *   .: Cria��o.....17 de Dezembro, 21:55
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Interface DAO para a entidade "An�lise Garantia".
 */
package br.com.yamaha.sistemagarantia.processo.dao;

import java.util.List;

import br.com.resource.infra.dao.Dao;
import br.com.resource.infra.exception.DaoException;

/** Interface que adiciona m�todos de servi�o pertinentes ao
 *  usu�rio em um objeto DAO padr�o.
 *  
 *  @author Edson Luiz Sumensari
 */
public interface AnaliseGarantiaDao extends Dao {
	
	/** Lista os lotes de garantias para An�lise de acordo com o perfil do usu�rio.
	 * 
	 * @param loteId
	 * @param linhaId
	 * @return
	 * @throws DaoException
	 */
	public List listGarantiaByLote(Integer loteId, Long linhaId) throws DaoException;
	
	/** Lista os lotes de garantias para An�lise de acordo com o perfil do usu�rio.
	 * 
	 * @param garantiaId
	 * @param linhaId
	 * @return
	 * @throws DaoException
	 */
	public List listGarantia(Integer garantiaId, Long linhaId) throws DaoException;
	
	/** Recupera a garantia para an�lise.
	 * @param modelo
	 * @param linhaId
	 * @return List 
	 * @throws DaoException
	 */
	public List listGarantiaByModelo(String modelo, Long linhaId) throws DaoException;
	
	/** Recupera a garantia para an�lise.
	 * @param linhaId
	 * @return List 
	 * @throws DaoException
	 */
	public List listGarantiaByLinha(Long linhaId) throws DaoException;
	
	/** Recupera a garantia para an�lise.
	 * @param String concessionariaDs
	 * @param linhaId
	 * @return List 
	 * @throws DaoException
	 */
	public List listGarantiaByConcessionaria(String concessionariaDs, Long linhaId) throws DaoException;
	
	/** Recupera a garantia para an�lise por representante.
	 * @param String REPRESENTANTE
	 * @param linhaId
	 * @return List 
	 * @throws DaoException
	 */
	public List listGarantiaByRepresentante(String representanteDs, Long linhaId) throws DaoException;
	
	/**retorna uma lista de garantias do Chassi informado
	 * 
	 * @param String chassi
	 * @return List
	 */
	public List listHistoricoGarantia(Integer garantiaId, String modelo) throws DaoException;
	
	
}
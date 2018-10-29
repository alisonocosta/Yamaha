/* Yamaha
 * Copyright (c) 2012 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......AnaliseGarantiaDao.java
 *   .: Criação.....17 de Dezembro, 21:55
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Interface DAO para a entidade "Análise Garantia".
 */
package br.com.yamaha.sistemagarantia.processo.dao;

import java.util.List;

import br.com.resource.infra.dao.Dao;
import br.com.resource.infra.exception.DaoException;

/** Interface que adiciona métodos de serviço pertinentes ao
 *  usuário em um objeto DAO padrão.
 *  
 *  @author Edson Luiz Sumensari
 */
public interface AnaliseGarantiaDao extends Dao {
	
	/** Lista os lotes de garantias para Análise de acordo com o perfil do usuário.
	 * 
	 * @param loteId
	 * @param linhaId
	 * @return
	 * @throws DaoException
	 */
	public List listGarantiaByLote(Integer loteId, Long linhaId) throws DaoException;
	
	/** Lista os lotes de garantias para Análise de acordo com o perfil do usuário.
	 * 
	 * @param garantiaId
	 * @param linhaId
	 * @return
	 * @throws DaoException
	 */
	public List listGarantia(Integer garantiaId, Long linhaId) throws DaoException;
	
	/** Recupera a garantia para análise.
	 * @param modelo
	 * @param linhaId
	 * @return List 
	 * @throws DaoException
	 */
	public List listGarantiaByModelo(String modelo, Long linhaId) throws DaoException;
	
	/** Recupera a garantia para análise.
	 * @param linhaId
	 * @return List 
	 * @throws DaoException
	 */
	public List listGarantiaByLinha(Long linhaId) throws DaoException;
	
	/** Recupera a garantia para análise.
	 * @param String concessionariaDs
	 * @param linhaId
	 * @return List 
	 * @throws DaoException
	 */
	public List listGarantiaByConcessionaria(String concessionariaDs, Long linhaId) throws DaoException;
	
	/** Recupera a garantia para análise por representante.
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
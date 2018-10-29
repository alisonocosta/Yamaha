/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......DescricaoDefeitoBusiness.java
 *   .: Criação.....10 de maio, 10:54
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Objeto de negócios para a entidade "DescricaoDefeito".
 */
package br.com.yamaha.sistemagarantia.business;

import br.com.resource.infra.business.BusinessObject;
import br.com.resource.infra.dao.Dao;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.model.DescricaoDefeito;

/** Objeto de negócios para a entidade "DescricaoDefeito".
 * 
 *  @author Thiago Uriel M. Garcia
 */
public class DescricaoDefeitoBusiness extends BusinessObject {

	/** Objeto DAO genérico para utilizado para este objeto de negócios. 
	 *  <p>
	 *  A entidade "GarantiaLine" não possui tarefas específicas, portanto, 
	 *  sua ponte com o banco de dados é feita utilizando um DAO padrão. 
	 */
	private Dao genericDao;	
	
    /** Salva ou atualiza o estado de uma entidade.
     *  <p>
     *  Se a entidade recebida não existir na camada de persistência
     *  da aplicação, será criada. Do contrário seus valores serão
     *  atualizados de acordo com as informações alteradas.
     *  
     *  @param descricaoDefeito
     *      <code>descricaoDefeito</code> representando a entidade a ser
     *      criada/atualizada na camada de persistência.
     *      
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      será lançada. Caso hajam problemas de regras de negócio, uma
     *      BusinessRuleException (subclasse de BusinessException) será 
     *      lançada.
     */     
	public void save(DescricaoDefeito descricaoDefeito) throws BusinessException {
		
		try {
			
			genericDao.makePersistent( descricaoDefeito );
			
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
/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......DescricaoDefeitoBusiness.java
 *   .: Cria��o.....10 de maio, 10:54
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descri��o...Objeto de neg�cios para a entidade "DescricaoDefeito".
 */
package br.com.yamaha.sistemagarantia.business;

import br.com.resource.infra.business.BusinessObject;
import br.com.resource.infra.dao.Dao;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.model.DescricaoDefeito;

/** Objeto de neg�cios para a entidade "DescricaoDefeito".
 * 
 *  @author Thiago Uriel M. Garcia
 */
public class DescricaoDefeitoBusiness extends BusinessObject {

	/** Objeto DAO gen�rico para utilizado para este objeto de neg�cios. 
	 *  <p>
	 *  A entidade "GarantiaLine" n�o possui tarefas espec�ficas, portanto, 
	 *  sua ponte com o banco de dados � feita utilizando um DAO padr�o. 
	 */
	private Dao genericDao;	
	
    /** Salva ou atualiza o estado de uma entidade.
     *  <p>
     *  Se a entidade recebida n�o existir na camada de persist�ncia
     *  da aplica��o, ser� criada. Do contr�rio seus valores ser�o
     *  atualizados de acordo com as informa��es alteradas.
     *  
     *  @param descricaoDefeito
     *      <code>descricaoDefeito</code> representando a entidade a ser
     *      criada/atualizada na camada de persist�ncia.
     *      
     * @throws BusinessException
     *      Se houverem problemas na camada de dados, uma BusinessException
     *      ser� lan�ada. Caso hajam problemas de regras de neg�cio, uma
     *      BusinessRuleException (subclasse de BusinessException) ser� 
     *      lan�ada.
     */     
	public void save(DescricaoDefeito descricaoDefeito) throws BusinessException {
		
		try {
			
			genericDao.makePersistent( descricaoDefeito );
			
		} catch (DaoException dExp) {
			
			throw new BusinessException(dExp);
			
		}
		
	}
	
	/** M�todo getter para "genericDao".
	 *  @return
	 *      <code>Dao</code>. O valor atual de genericDao.
	 */
	public Dao getGenericDao() {
		return this.genericDao;
	}

	/** M�todo setter para "genericDao".
	 *  @param entityId
	 *      <code>Dao</code> a ser designado para genericDao.
	 */
	public void setGenericDao(Dao genericDao) {
		this.genericDao = genericDao;
	}	
	
}
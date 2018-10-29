/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......ProgramaBusiness.java
 *   .: Cria��o.....17 de Dezembro 2007, 15:50
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Objeto de neg�cios para a entidade "Programa".
 */
package br.com.yamaha.sistemagarantia.business;

import java.util.List;

import br.com.resource.infra.business.BusinessObject;
import br.com.resource.infra.dao.Dao;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.model.Programa;

/** Objeto de neg�cios para a entidade "Programa".
 * 
 *  @author Edson Luiz Sumensari
 */
public class ProgramaBusiness extends BusinessObject {

	/** Objeto DAO gen�rico para utilizado para este objeto de neg�cios. 
	 *  <p>
	 *  A entidade "Programa" n�o possui tarefas espec�ficas, portanto, 
	 *  sua ponte com o banco de dados � feita utilizando um DAO padr�o. 
	 */
	private Dao genericDao;	
	
	/**
	 * Retorna uma lista de objetos programa de acordo o tipoRelatorio enviado
	 * 
	 * @param type
	 * 	<code>String</code>  Tipo de relat�rio
	 * 
	 * @return List   - Lista de entidades de Programa
	 * 
	 * @throws BusinessException
	 */
    public List listProgramByType(String type) throws BusinessException {
    	
    	try {
			
			return genericDao.listByParameter(Programa.class, "tipoRelatorio", type, "sequenciaMenu", "ASC");
			
		} catch (DaoException dExp) {
			
			throw new BusinessException(dExp);
			
		}
		
    }
    
    /**
	 * Retorna uma lista de objetos programa de acordo o menu
	 * 	 
	 * 
	 * @return List   - Lista de entidades de Programa
	 * 
	 * @throws BusinessException
	 */
    public List listProgramByMenu() throws BusinessException {
    	
    	try {
			
			return genericDao.listByParameter(Programa.class, "menu",Programa.MENU_JAVA, "sequenciaMenu", "ASC");
			
		} catch (DaoException dExp) {
			
			throw new BusinessException(dExp);
			
		}
		
    }
    
    /**
	 * Retorna uma lista de objetos programa de acordo com o menu
	 * 	 
	 * @param menu
	 * 	<code>String</code> Letra que o menu
	 * 
	 * @return List   - Lista de entidades de Programa
	 * 
	 * @throws BusinessException
	 */
    public List listProgramByMenu(String menu) throws BusinessException {
    	
    	try {
			
			return genericDao.listByParameter(Programa.class, "menu", menu, "sequenciaMenu", "ASC");
			
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
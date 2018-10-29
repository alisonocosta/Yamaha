/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......ProgramaBusiness.java
 *   .: Criação.....17 de Dezembro 2007, 15:50
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Objeto de negócios para a entidade "Programa".
 */
package br.com.yamaha.sistemagarantia.business;

import java.util.List;

import br.com.resource.infra.business.BusinessObject;
import br.com.resource.infra.dao.Dao;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.model.Programa;

/** Objeto de negócios para a entidade "Programa".
 * 
 *  @author Edson Luiz Sumensari
 */
public class ProgramaBusiness extends BusinessObject {

	/** Objeto DAO genérico para utilizado para este objeto de negócios. 
	 *  <p>
	 *  A entidade "Programa" não possui tarefas específicas, portanto, 
	 *  sua ponte com o banco de dados é feita utilizando um DAO padrão. 
	 */
	private Dao genericDao;	
	
	/**
	 * Retorna uma lista de objetos programa de acordo o tipoRelatorio enviado
	 * 
	 * @param type
	 * 	<code>String</code>  Tipo de relatório
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
/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......GarantiaLineBusiness.java
 *   .: Cria��o.....01 de junho, 12:32
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Objeto de neg�cios para a entidade "GarantiaLine".
 */
package br.com.yamaha.sistemagarantia.business;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import br.com.resource.infra.business.BusinessObject;
import br.com.resource.infra.dao.Dao;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.model.DescricaoDefeito;
import br.com.yamaha.sistemagarantia.model.FatorGarantia;
import br.com.yamaha.sistemagarantia.model.GarantiaHeader;
import br.com.yamaha.sistemagarantia.model.GarantiaLine;
import br.com.yamaha.sistemagarantia.model.ParecerTecnico;
import br.com.yamaha.sistemagarantia.model.TabelaPreco;
import br.com.yamaha.sistemagarantia.model.id.GarantiaLineId;
import br.com.yamaha.sistemagarantia.model.id.TabelaPrecoId;

/** Classe de neg�cios relacionada � entidade <b>GarantiaLine</b>.
 * 
 *  @author Edson Luiz Sumensari
 */
public class GarantiaLineBusiness extends BusinessObject {


	/** Objeto DAO gen�rico para utilizado para este objeto de neg�cios. 
	 *  <p>
	 *  A entidade "GarantiaLine" n�o possui tarefas espec�ficas, portanto, 
	 *  sua ponte com o banco de dados � feita utilizando um DAO padr�o. 
	 */
	private Dao genericDao;

	/** Recupera uma GarantiaLine pelo id.
	 *
	 *  @param entityId
	 *      Refer�ncia da entidade na camada de persist�ncia.
	 *  @return
	 *      Um objeto <code>GarantiaLine</code>.
	 *      
	 * @throws BusinessException
	 *      Se houverem erros de execu��o ou nas camadas 
	 *      abaixo dos servi�os, ser�o encapsulados neste
	 *      tipo de <code>Exception</code>.
	 */
	public GarantiaLine get(Serializable entityId) throws BusinessException {

		try {

			return (GarantiaLine)genericDao.get(GarantiaLine.class, entityId);

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}

	}
	
	/** Recupera uma GarantiaLine pelo id.
	 *
	 *  @param compositeId
	 *      Refer�ncia da entidade na camada de persist�ncia.
	 *  @return
	 *      Um objeto <code>GarantiaLine</code>.
	 *      
	 * @throws BusinessException
	 *      Se houverem erros de execu��o ou nas camadas 
	 *      abaixo dos servi�os, ser�o encapsulados neste
	 *      tipo de <code>Exception</code>.
	 */	
	public GarantiaLine getGarantiaLine(GarantiaLineId compositeId) throws BusinessException {

		try {
			
			return (GarantiaLine)genericDao.getByParameter(GarantiaLine.class, "compositeEntityId", compositeId);

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}

	}

	/** Recupera uma TabelaPreco pelo id.
	 *
	 *  @param compositeId
	 *      Refer�ncia da entidade na camada de persist�ncia.
	 *  @return
	 *      Um objeto <code>TabelaPreco</code>.
	 *      
	 * @throws BusinessException
	 *      Se houverem erros de execu��o ou nas camadas 
	 *      abaixo dos servi�os, ser�o encapsulados neste
	 *      tipo de <code>Exception</code>.
	 */	
	public TabelaPreco getTabelaPreco(TabelaPrecoId compositeId) throws BusinessException {

		try {
			//return (TabelaPreco)genericDao.get(TabelaPreco.class, compositeId);
			return (TabelaPreco)genericDao.getByParameter(TabelaPreco.class, "compositeEntityId", compositeId);

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}

	}
	
	/** Recupera um Fator de Garantia pelo Estado.
	 *
	 *  @param estado
	 *      Estado da Concession�ria.
	 *  @return
	 *      Um objeto <code>FatorGarantia</code>.
	 *      
	 * @throws BusinessException
	 *      Se houverem erros de execu��o ou nas camadas 
	 *      abaixo dos servi�os, ser�o encapsulados neste
	 *      tipo de <code>Exception</code>.
	 */	
	public FatorGarantia getFatorGarantia(String estado) throws BusinessException {

		try {
			
			return (FatorGarantia)genericDao.getByParameter(FatorGarantia.class, "estado", estado);

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}

	}
	
	
	/** Recupera o Hist�rico de descri��o de defeito.
	 *
	 *  @param garantiaId
	 *      <code>Integer<code> id da entidade garantia relacionada.
	 *  @return
	 *      Uma <code>String</code> com as descri��es de defeitos concatenados.
	 *      
	 * @throws BusinessException
	 *      Se houverem erros de execu��o ou nas camadas 
	 *      abaixo dos servi�os, ser�o encapsulados neste
	 *      tipo de <code>Exception</code>.
	 */	
	public String listDescricaoDefeito(GarantiaHeader garantia) throws BusinessException {
		
		List listDescricaoDefeito = null;
		
		String histDescDefeito = new String();
			
		listDescricaoDefeito = garantia.getDefeitos();
		
		Iterator it = listDescricaoDefeito.iterator();
		
		while ( it.hasNext() ) {
			
			DescricaoDefeito descDefeito = new DescricaoDefeito();
			descDefeito = (DescricaoDefeito) it.next();
			
			histDescDefeito = descDefeito.getDescricaoDefeito();
			
		}
		
		return histDescDefeito;
		
	}
	
	/** Recupera a primeira descri��o de defeito.
	 *
	 *  @param garantiaId
	 *      <code>Integer<code> id da entidade garantia relacionada.
	 *  @return
	 *      Uma <code>DescricaoDefeito</code> com a descri��o do defeito.
	 *      
	 * @throws BusinessException
	 *      Se houverem erros de execu��o ou nas camadas 
	 *      abaixo dos servi�os, ser�o encapsulados neste
	 *      tipo de <code>Exception</code>.
	 */	
	public DescricaoDefeito getDescricaoDefeito(GarantiaHeader garantia) throws BusinessException {
		
		DescricaoDefeito descricao = null;
		
		List listDescricaoDefeito = garantia.getDefeitos();
		
		if ( listDescricaoDefeito != null ) {
			Iterator it = listDescricaoDefeito.iterator();
			
			while ( it.hasNext() ) {
				
				descricao = new DescricaoDefeito();
				descricao = (DescricaoDefeito) it.next();
				
			}
		}
		
		return descricao;
		
	}
	
	
	/** Recupera o Hist�rico de parecer t�cnico.
	 *
	 *  @param garantiaId
	 *      <code>Integer<code> id da entidade garantia relacionada.
	 *  @return
	 *      Uma <code>String</code> com pareceres t�cnicos concatenados.
	 *      
	 * @throws BusinessException
	 *      Se houverem erros de execu��o ou nas camadas 
	 *      abaixo dos servi�os, ser�o encapsulados neste
	 *      tipo de <code>Exception</code>.
	 */	
	public String listParecerTecnico(GarantiaHeader garantia) throws BusinessException {
		
		List listParecerTecnico = null;
		
		String histparecerTecnico = new String();
		
		listParecerTecnico = garantia.getPareceres();
		
		Iterator it = listParecerTecnico.iterator();
		
		while ( it.hasNext() ) {
			
			ParecerTecnico parecerTecnico = new ParecerTecnico();
			parecerTecnico = (ParecerTecnico) it.next();
			
			histparecerTecnico += parecerTecnico.getMaskedDataCriacao() + " : " + parecerTecnico.getParecerTecnico() + "\n";
			
		}
		
		return histparecerTecnico;
		
	}
	
	/** Salva ou atualiza o estado de uma entidade.
	 *  <p>
	 *  Se a entidade recebida n�o existir na camada de persist�ncia
	 *  da aplica��o, ser� criada. Do contr�rio seus valores ser�o
	 *  atualizados de acordo com as informa��es alteradas.
	 *  
	 *  @param cupom
	 *      <code>GarantiaLine</code> representando a entidade a ser
	 *      criada/atualizada na camada de persist�ncia.
	 *      
	 * @throws BusinessException
	 *      Se houverem erros de execu��o ou nas camadas 
	 *      abaixo dos servi�os, ser�o encapsulados neste
	 *      tipo de <code>Exception</code>.
	 */    
	public void save(GarantiaLine garantiaLine) throws BusinessException {

		try {

			genericDao.makePersistent(garantiaLine);

		} catch ( DaoException daoExp ) {

			throw new BusinessException( daoExp );

		}

	} 
	
	/** Remo��o l�gica de um objeto da camada de persist�ncia da aplica��o.
     * 
     *  @param baseClass
     *      <code>Class</code>. Tipo de classe a ser removida pelo m�todo.
     *  
     *  @param entity
     *      <code>EntityObject<code>
     *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes ser�o 
     *      convertidos para uma DaoException.
     */    
	public void remove(GarantiaLine garantiaLine) throws BusinessException {
		
		try {		

			this.genericDao.remove(garantiaLine);


		} catch (DaoException dExp) {
			throw new BusinessException( dExp );
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

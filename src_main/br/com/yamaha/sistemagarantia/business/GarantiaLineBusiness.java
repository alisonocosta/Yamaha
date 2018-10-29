/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......GarantiaLineBusiness.java
 *   .: Criação.....01 de junho, 12:32
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Objeto de negócios para a entidade "GarantiaLine".
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

/** Classe de negócios relacionada à entidade <b>GarantiaLine</b>.
 * 
 *  @author Edson Luiz Sumensari
 */
public class GarantiaLineBusiness extends BusinessObject {


	/** Objeto DAO genérico para utilizado para este objeto de negócios. 
	 *  <p>
	 *  A entidade "GarantiaLine" não possui tarefas específicas, portanto, 
	 *  sua ponte com o banco de dados é feita utilizando um DAO padrão. 
	 */
	private Dao genericDao;

	/** Recupera uma GarantiaLine pelo id.
	 *
	 *  @param entityId
	 *      Referência da entidade na camada de persistência.
	 *  @return
	 *      Um objeto <code>GarantiaLine</code>.
	 *      
	 * @throws BusinessException
	 *      Se houverem erros de execução ou nas camadas 
	 *      abaixo dos serviços, serão encapsulados neste
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
	 *      Referência da entidade na camada de persistência.
	 *  @return
	 *      Um objeto <code>GarantiaLine</code>.
	 *      
	 * @throws BusinessException
	 *      Se houverem erros de execução ou nas camadas 
	 *      abaixo dos serviços, serão encapsulados neste
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
	 *      Referência da entidade na camada de persistência.
	 *  @return
	 *      Um objeto <code>TabelaPreco</code>.
	 *      
	 * @throws BusinessException
	 *      Se houverem erros de execução ou nas camadas 
	 *      abaixo dos serviços, serão encapsulados neste
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
	 *      Estado da Concessionária.
	 *  @return
	 *      Um objeto <code>FatorGarantia</code>.
	 *      
	 * @throws BusinessException
	 *      Se houverem erros de execução ou nas camadas 
	 *      abaixo dos serviços, serão encapsulados neste
	 *      tipo de <code>Exception</code>.
	 */	
	public FatorGarantia getFatorGarantia(String estado) throws BusinessException {

		try {
			
			return (FatorGarantia)genericDao.getByParameter(FatorGarantia.class, "estado", estado);

		} catch (DaoException dExp) {

			throw new BusinessException(dExp);

		}

	}
	
	
	/** Recupera o Histórico de descrição de defeito.
	 *
	 *  @param garantiaId
	 *      <code>Integer<code> id da entidade garantia relacionada.
	 *  @return
	 *      Uma <code>String</code> com as descrições de defeitos concatenados.
	 *      
	 * @throws BusinessException
	 *      Se houverem erros de execução ou nas camadas 
	 *      abaixo dos serviços, serão encapsulados neste
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
	
	/** Recupera a primeira descrição de defeito.
	 *
	 *  @param garantiaId
	 *      <code>Integer<code> id da entidade garantia relacionada.
	 *  @return
	 *      Uma <code>DescricaoDefeito</code> com a descrição do defeito.
	 *      
	 * @throws BusinessException
	 *      Se houverem erros de execução ou nas camadas 
	 *      abaixo dos serviços, serão encapsulados neste
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
	
	
	/** Recupera o Histórico de parecer técnico.
	 *
	 *  @param garantiaId
	 *      <code>Integer<code> id da entidade garantia relacionada.
	 *  @return
	 *      Uma <code>String</code> com pareceres técnicos concatenados.
	 *      
	 * @throws BusinessException
	 *      Se houverem erros de execução ou nas camadas 
	 *      abaixo dos serviços, serão encapsulados neste
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
	 *  Se a entidade recebida não existir na camada de persistência
	 *  da aplicação, será criada. Do contrário seus valores serão
	 *  atualizados de acordo com as informações alteradas.
	 *  
	 *  @param cupom
	 *      <code>GarantiaLine</code> representando a entidade a ser
	 *      criada/atualizada na camada de persistência.
	 *      
	 * @throws BusinessException
	 *      Se houverem erros de execução ou nas camadas 
	 *      abaixo dos serviços, serão encapsulados neste
	 *      tipo de <code>Exception</code>.
	 */    
	public void save(GarantiaLine garantiaLine) throws BusinessException {

		try {

			genericDao.makePersistent(garantiaLine);

		} catch ( DaoException daoExp ) {

			throw new BusinessException( daoExp );

		}

	} 
	
	/** Remoção lógica de um objeto da camada de persistência da aplicação.
     * 
     *  @param baseClass
     *      <code>Class</code>. Tipo de classe a ser removida pelo método.
     *  
     *  @param entity
     *      <code>EntityObject<code>
     *  
     *  @throws DaoException
     *      Se houverem problemas nas camadas inferiores, estes serão 
     *      convertidos para uma DaoException.
     */    
	public void remove(GarantiaLine garantiaLine) throws BusinessException {
		
		try {		

			this.genericDao.remove(garantiaLine);


		} catch (DaoException dExp) {
			throw new BusinessException( dExp );
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

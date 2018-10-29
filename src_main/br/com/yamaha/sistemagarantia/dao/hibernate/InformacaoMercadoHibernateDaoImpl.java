/* Resource Informática
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......InformacaoMercadoHibernateDaoImpl.java
 *   .: Criação.....01 de maio, 12:40
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Implementação DAO hibernate para a entidade "InformacaoMercado".
 */
package br.com.yamaha.sistemagarantia.dao.hibernate;

import java.io.Serializable;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import br.com.resource.infra.dao.hibernate.HibernateDaoImpl;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.BusinessRuleException;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.dao.InformacaoMercadoDao;
import br.com.yamaha.sistemagarantia.model.Concessionaria;
import br.com.yamaha.sistemagarantia.model.FatorGarantia;
import br.com.yamaha.sistemagarantia.model.GarantiaHeader;
import br.com.yamaha.sistemagarantia.model.GarantiaLine;
import br.com.yamaha.sistemagarantia.model.HistAcaoTomada;
import br.com.yamaha.sistemagarantia.model.HistCausaProblema;
import br.com.yamaha.sistemagarantia.model.HistDiagnostico;
import br.com.yamaha.sistemagarantia.model.HistSintoma;
import br.com.yamaha.sistemagarantia.model.InfoMercGarantia;
import br.com.yamaha.sistemagarantia.model.InfoMercado;
import br.com.yamaha.sistemagarantia.model.Lote;
import br.com.yamaha.sistemagarantia.model.Sintoma;
import br.com.yamaha.sistemagarantia.model.StatusInfoMercado;

/** Implementação DAO para Informações de Mercado. 
 * 
 *  @author Edson Luiz Sumensari
 */
public class InformacaoMercadoHibernateDaoImpl extends HibernateDaoImpl implements InformacaoMercadoDao {

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
										, boolean isEdit) throws DaoException {
		
		final InfoMercado 	infoMercadoFinal 	= infoMercado;
		final Lote			loteFinal			= lote;		
		final GarantiaHeader garantiaFinal 		= garantia;
		final GarantiaLine	garantiaLineFinal	= garantiaLine;
		final String 		sintomaFinal 		= sintoma;
		final String		causaProblemaFinal 	= causaProblema;
		final String 		diagnosticoFinal	= diagnostico;
		final String		solucaoFinal		= solucao;
		final Serializable 	userIdFinal			= userId;
		final boolean		isEditFinal			= isEdit;
		
		List results = super.getHibernateTemplate().executeFind(
	    		
    			new HibernateCallback() {
    				
    				public Object doInHibernate(Session session) throws HibernateException {
    					
    					try {
    						//----[ Processo de gravação da Informação de Mercado ]---------
    						
    						makePersistent(infoMercadoFinal);
    						
    						if ( !isEditFinal ) {
    							
    							// Armazenamos itens criados para "Sintoma".
    							if ( sintomaFinal != null && !"".equals(sintomaFinal)) {
    								
    								HistSintoma histSintoma = new HistSintoma();
    								histSintoma.setInfoMercadoId( new Integer( infoMercadoFinal.getEntityId().toString() ) );
    								histSintoma.setSintoma( sintomaFinal );
    								histSintoma.setDataInicio( new Date() );
    								histSintoma.setCriadoPor( (Long) userIdFinal );
    								histSintoma.setDataCriacao( new Date() );
    									
    								makePersistent(histSintoma);
    								
    							}
    				
    							// Armazenamos itens criados para "CausaProblema".
    							if ( causaProblemaFinal != null && !"".equals(causaProblemaFinal)) {
    								
    								HistCausaProblema histCausaProblema = new HistCausaProblema();
    								histCausaProblema.setInfoMercadoId( new Integer( infoMercadoFinal.getEntityId().toString() ) );
    								histCausaProblema.setCausaProblema( causaProblemaFinal );
    								histCausaProblema.setDataInicio( new Date() );
    								histCausaProblema.setCriadoPor( (Long) userIdFinal );
    								histCausaProblema.setDataCriacao( new Date() );
    								
    								makePersistent(histCausaProblema);
    								
    							}
    				
    							// Armazenamos itens criados para "Diagnostico".
    							if ( diagnosticoFinal != null && !"".equals(diagnosticoFinal)) {
    								
    								HistDiagnostico histDiagnostico = new HistDiagnostico();
    								histDiagnostico.setInfoMercadoId( new Integer( infoMercadoFinal.getEntityId().toString() ) );
    								histDiagnostico.setDiagnostico( diagnosticoFinal );
    								histDiagnostico.setDataInicio( new Date() );
    								histDiagnostico.setCriadoPor( (Long) userIdFinal );
    								histDiagnostico.setDataCriacao( new Date() );
    								
    								makePersistent(histDiagnostico);
    								
    							}
    				
    							// Armazenamos itens criados para "Solucao".
    							if ( solucaoFinal != null && !"".equals(solucaoFinal)) {
    								
    								HistAcaoTomada histAcaoTomada = new HistAcaoTomada();
    								histAcaoTomada.setInfoMercadoId( new Integer( infoMercadoFinal.getEntityId().toString() ) );
    								histAcaoTomada.setAcaoTomada( solucaoFinal );
    								histAcaoTomada.setDataInicio( new Date() );
    								histAcaoTomada.setCriadoPor( (Long) userIdFinal );
    								histAcaoTomada.setDataCriacao( new Date() );
    								
    								makePersistent(histAcaoTomada);
    								
    							}
    							
    							//Salvamos o lote;
    							makePersistent(loteFinal);
    							
    							//Salvamos a Garantia
    							garantiaFinal.setLote(loteFinal);
    							garantiaFinal.setNumeroOS( infoMercadoFinal.getEntityId().toString());
    							makePersistent(garantiaFinal);
    							
    							//Salvamos a Linha da Garantia
    							garantiaLineFinal.setCompositeEntityId(garantiaFinal.getEntityId(), new Long(1));
    							makePersistent(garantiaLineFinal);
    							
    							//Salvamos a relação entre garantia e Informação de Mercado
    							InfoMercGarantia infoMercGarantia = new InfoMercGarantia();
    							infoMercGarantia.setInfoMercado(infoMercadoFinal);
    							infoMercGarantia.setGarantia(garantiaFinal);
    							infoMercGarantia.setDataCriacao( new Date() );
    							infoMercGarantia.setCriadoPor( (Long) userIdFinal );
    							infoMercGarantia.setDataAtualizacao( new Date() );
    							infoMercGarantia.setAtualizadoPor( (Long) userIdFinal );
    							makePersistent(infoMercGarantia);
    							
    							infoMercadoFinal.setGarantia(garantiaFinal);
    							
    						}
    						
    						List results = new ArrayList();
    						results.add(infoMercadoFinal);
    						
    						return results;
    						
    					} catch( DaoException dExp ) { 
    						
    						throw new HibernateException(dExp);
    						
    					}
    				}   				
    				
    			}    			
    	);
		return (InfoMercado)results.get(0);
	}
	
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
						, boolean isEdit) throws DaoException {
		
		final InfoMercado 	infoMercadoFinal 	= infoMercado;
		final String 		sintomaFinal 		= sintoma;
		final String		causaProblemaFinal 	= causaProblema;
		final String 		diagnosticoFinal	= diagnostico;
		final String		solucaoFinal		= solucao;
		final Serializable 	userIdFinal			= userId;
		final boolean		isEditFinal			= isEdit;
		
		super.getHibernateTemplate().executeFind(
    		
			new HibernateCallback() {
				
				public Object doInHibernate(Session session) throws HibernateException {
					
					try {
						// ----[ 1. Processo de gravação da Informação de Mercado ]---------
						makePersistent(infoMercadoFinal);
						
						if ( !isEditFinal ) {
							
							// Armazenamos itens criados para "Sintoma".
							if ( sintomaFinal != null && !"".equals(sintomaFinal)) {
								
								HistSintoma histSintoma = new HistSintoma();
								histSintoma.setInfoMercadoId( new Integer( infoMercadoFinal.getEntityId().toString() ) );
								histSintoma.setSintoma( sintomaFinal );
								histSintoma.setDataInicio( new Date() );
								histSintoma.setCriadoPor( (Long) userIdFinal );
								histSintoma.setDataCriacao( new Date() );
									
								makePersistent(histSintoma);
								
							}
				
							// Armazenamos itens criados para "CausaProblema".
							if ( causaProblemaFinal != null && !"".equals(causaProblemaFinal)) {
								
								HistCausaProblema histCausaProblema = new HistCausaProblema();
								histCausaProblema.setInfoMercadoId( new Integer( infoMercadoFinal.getEntityId().toString() ) );
								histCausaProblema.setCausaProblema( causaProblemaFinal );
								histCausaProblema.setDataInicio( new Date() );
								histCausaProblema.setCriadoPor( (Long) userIdFinal );
								histCausaProblema.setDataCriacao( new Date() );
								
								makePersistent(histCausaProblema);
								
							}
				
							// Armazenamos itens criados para "Diagnostico".
							if ( diagnosticoFinal != null && !"".equals(diagnosticoFinal)) {
								
								HistDiagnostico histDiagnostico = new HistDiagnostico();
								histDiagnostico.setInfoMercadoId( new Integer( infoMercadoFinal.getEntityId().toString() ) );
								histDiagnostico.setDiagnostico( diagnosticoFinal );
								histDiagnostico.setDataInicio( new Date() );
								histDiagnostico.setCriadoPor( (Long) userIdFinal );
								histDiagnostico.setDataCriacao( new Date() );
								
								makePersistent(histDiagnostico);
								
							}
				
							// Armazenamos itens criados para "Solucao".
							if ( solucaoFinal != null && !"".equals(solucaoFinal)) {
								
								HistAcaoTomada histAcaoTomada = new HistAcaoTomada();
								histAcaoTomada.setInfoMercadoId( new Integer( infoMercadoFinal.getEntityId().toString() ) );
								histAcaoTomada.setAcaoTomada( solucaoFinal );
								histAcaoTomada.setDataInicio( new Date() );
								histAcaoTomada.setCriadoPor( (Long) userIdFinal );
								histAcaoTomada.setDataCriacao( new Date() );
								
								makePersistent(histAcaoTomada);
								
							}
							
						}
						
						return null;
						
					} catch( DaoException dExp ) { 
						
						throw new HibernateException(dExp);
						
					}
				}   				
				
			}
			
		);
			
		return true;
	}
	
	/** Implementação do método: getSintoma. */
    public Sintoma getSintoma(String codigo, Long linhaId) throws DaoException {
    	
    	DetachedCriteria criteria = super.getDetachedCriteria(Sintoma.class);
    	criteria.createAlias("linha", "linha");
    	criteria.add( Expression.eq("sintomaCode"   , codigo.toUpperCase()));	   
    	criteria.add( Expression.eq("linha.entityId" , linhaId));

       List results = super.getHibernateTemplate().findByCriteria(criteria);

       // Se houver um registro, retornamos sendo o Sintoma da pesquisa.
       // Se houver mais de um registro temos um problema de banco de dados.
       // Se não houver nenhum registro, retornamos nulo.
       if ( results.size() == 1 )
           return (Sintoma) results.get(0);
           
       else if ( results.size() > 1 )
           throw new DaoException("Too many entities found. There should be only one.");
       
       else
           return null;    	
    	
    }
    
    /** Implementação do método: getFatorGarantia. */
    public FatorGarantia getFatorGarantia(String uf) throws DaoException {
    	
    	DetachedCriteria criteria = super.getDetachedCriteria(FatorGarantia.class);
    	criteria.add( Expression.eq("estado"   , uf.toUpperCase()));

       List results = super.getHibernateTemplate().findByCriteria(criteria);

       // Se houver um registro, retornamos sendo o FatorGarantia da pesquisa.
       // Se houver mais de um registro temos um problema de banco de dados.
       // Se não houver nenhum registro, retornamos nulo.
       if ( results.size() == 1 )
           return (FatorGarantia) results.get(0);
           
       else if ( results.size() > 1 )
           throw new DaoException("Too many entities found. There should be only one.");
       
       else
           return null;
    }
	
	
    /** Implementação do método: listByConcessionaria. */
    public List listByConcessionaria(Concessionaria concessionaria) throws DaoException {
        
        StatusInfoMercado s1 = new StatusInfoMercado();
        StatusInfoMercado s2 = new StatusInfoMercado();
        s1.setEntityId( new Long(1) );
        s2.setEntityId( new Long(3) );
        
        List criteriaStatusGroup = new ArrayList();
        criteriaStatusGroup.add( s1 );
        criteriaStatusGroup.add( s2 );
        
        DetachedCriteria criteria = super.getDetachedCriteria(InfoMercado.class);
        criteria.add( Expression.eq("concessionaria", concessionaria) );
        criteria.add( Restrictions.in("statusInfoMercado", criteriaStatusGroup ) );
        criteria.addOrder( Order.desc("entityId") );
        
        return super.getHibernateTemplate().findByCriteria( criteria );
        
    }

    /** Implementação do método: listByConcessionaria. */
    public List listByConcessionaria(Concessionaria concessionaria, int filterType, String filterValue) throws DaoException {
    	
        DetachedCriteria criteria = super.getDetachedCriteria(InfoMercado.class);
        criteria.add( Expression.eq("concessionaria", concessionaria) );    	
    	
    	if ( filterType == FILTER_CHASSI ) {
    		
    		criteria.add( Expression.eq("chassi", filterValue).ignoreCase() );
    		
    	} else if ( filterType == FILTER_STATUS ) {
    		
    		DetachedCriteria criteriaStm = super.getDetachedCriteria(StatusInfoMercado.class);
    		criteriaStm.add(Expression.eq("descricao", filterValue).ignoreCase());
    		    		
    		List results = super.getHibernateTemplate().findByCriteria(criteriaStm);
    		
    		System.out.println("Lista de status IM:" + results.size());
    		
    		if ( !results.isEmpty() )
    			//criteria.add( Expression.eq("statusInfoMercado", new StatusInfoMercado()));
    		//else
    			criteria.add( Expression.in("statusInfoMercado", results ) );
    		
    	} else {
    		
    		throw new DaoException("Tipo de filtro desconhecido.");
    		
    	}
    	
        criteria.addOrder( Order.desc("entityId") );
        
        return super.getHibernateTemplate().findByCriteria( criteria );    	
    	
    }
    
    /** Retorna o conteúdo do campo content de uma entidade InforMercFotos
     * 
     * @param infoMercFotosId
     * @return Blob
     * @throws BusinessException
     */
    public Blob getContentFile( Long infoMercFotosId ) throws DaoException {
    	
    	String sqlPecas = 	" select info_merc_foto from ym_sg_infomerc_fotos where info_merc_fotos_id = :infoMercFotosId ";
    	
    	Blob content = null;
    	
		Session session = super.getSession();
		
		SQLQuery queryPecas  = session.createSQLQuery(sqlPecas);
		
		queryPecas.setParameter("infoMercFotosId"  , infoMercFotosId );
		
		List results = queryPecas.list();
	           
	    if ( results.size() > 0 )
	    	if ( results.get(0)!= null  )
	    		content = (Blob) results.get(0);
		
		System.out.println("Arquivo recuperado - Content:" + (content != null));
		
		session.close();
		
		return content;
    	
    	
    	
    }
    
}
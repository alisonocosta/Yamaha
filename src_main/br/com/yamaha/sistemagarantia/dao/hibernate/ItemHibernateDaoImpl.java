/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......ItemHibernateDaoImpl.java
 *   .: Criação.....30 de junho, 13:54
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...DAO com tarefas específicas para a entidade Item.
 */
package br.com.yamaha.sistemagarantia.dao.hibernate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;

import br.com.resource.infra.dao.hibernate.HibernateDaoImpl;
import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.dao.ItemDao;
import br.com.yamaha.sistemagarantia.model.Item;

/** Objeto DAO com tarefas específicas para a entidade <b>Item</b>. 
 * 
 *  @author Edson Luiz Sumensari
 */
public class ItemHibernateDaoImpl extends HibernateDaoImpl implements ItemDao {
	
	
	/** Retorna a peça pai de uma peça 
	 * 
	 * @param chassi
	 * 	<code>String</code> chassi do produto. 
	 * 
	 * @param itemCode
	 * 	<code>String</code> itemCode da Peca solicitada
	 * 
	 * @return Long
	 * 	<code>Long</code> id da peça pai.
	 * 
	 * @throws BusinessException
	 *      Se houverem erros de execução ou nas camadas 
	 *      abaixo dos serviços, serão encapsulados neste
	 *      tipo de <code>Exception</code>.
	 */
	public Long getPecaPai(String chassi, String itemCode) throws DaoException { 
		
		String sqlPecas = 	"SELECT ym_sg_veritem_pai( :chassi, :itemCode ) FROM dual ";
 
		Session session = super.getSession();
		
		SQLQuery queryPecas  = session.createSQLQuery(sqlPecas);
		
		queryPecas.setParameter("chassi"  , chassi.toUpperCase() );
		queryPecas.setParameter("itemCode", itemCode.toUpperCase() );
		
		String strPecas = "0";
		
		List results = queryPecas.list();
	           
	    if ( results.size() > 0 )
	    	if ( results.get(0)!= null  )
	    		strPecas = results.get(0).toString();
		
		//System.out.println("getPecaPai - Peça:" + strPecas);
		
		session.close();
		
		return Long.valueOf(strPecas);
		
	}
	
	
	/** Retorna quantidade de peças de um determinadao item
	 * 
	 * @param chassi
	 * 	<code>String</code> chassi do produto.
	 * 
	 * @param itemCode
	 * 	<code>String</code> Código do item.
	 * 
	 * @return Long
	 * 	<code>Quantidade</code> Quantidade de Peças no chassi.
	 * 
	 * @throws BusinessException
	 *      Se houverem erros de execução ou nas camadas 
	 *      abaixo dos serviços, serão encapsulados neste
	 *      tipo de <code>Exception</code>.
	 */
	public int getQtdePecaByModelo(String chassi, String itemCode) throws DaoException { 
		
		String  sqlPecas = 	" select distinct	quantidade" +
							" from (" +
							"			select  yx.item_code  item_code" +
							"					, yx.quantidade quantidade" +	
							" 			from 	ym_sg_faturamento   yf " +
							"					, ym_sg_modelo      ys " +
							"					, ym_sg_mpl         yx " +
							"					, ym_sg_item        yi " +
							"					, ym_sg_tabela_preco tp " +
							" 			where yf.modelo          = ys.modelo_id " +
							" 			and   ys.codigo_produto  = yx.codigo_produto " +
							" 			and   yx.item_code         = yi.item_code " +
							"			and   yi.item_id = tp.item_id " +
							"			and   yf.status_fat_id   = 1 " +							
							" 			and   yf.chassi          = :chassi " +
							"			and   yi.organization_id = 91 " +
							"			and   yi.segmento = 'PEÇAS' " +
							"			and   yi.end_date is null	" +
							"			and   yx.end_date is null   " +
							"			and   yf.end_date is null   " +
							"			UNION ALL " +
							" 			select  yx.item_code  item_code " +
							"					, yx.quantidade quantidade 	" +
							"			from 	ym_sg_faturamento   yf  " +
							"					, ym_sg_modelo      ys  " +
							"					, ym_sg_mpl         yx  " +
							"					, ym_sg_item        yi  " +
							"					, ym_sg_tabela_preco tp " +
							"			where yf.modelo          = ys.modelo_id  " +
							"			and   ys.codigo_produto  like yx.codigo_produto ||'%'  " +
							" 			and   yx.item_code         = yi.item_code  	" +
							"			and   yi.item_id = tp.item_id " +
							"			and   yf.status_fat_id   = 1 " +						
							" 			and    yf.chassi  =  :chassi " + 
							"			and   yi.organization_id = 91 " +
							"			and   yi.segmento = 'PEÇAS' " +
							"			and   yi.end_date is null	" +
							"			and   yx.end_date is null   " +
							"			and   yf.end_date is null   " +
							" )" +
							" where item_code = '" + itemCode.trim().toUpperCase() + "' ";
					
		//System.out.println("Recuperando uma Sessão do Hibernate!");		
		Session session = super.getSession();
		
		SQLQuery query = session.createSQLQuery(sqlPecas);
		
		//System.out.println("CHASSI:" + chassi.trim().toUpperCase());
		
		query.setParameter("chassi"  , chassi.trim().toUpperCase());
		String strPecas = "0";
		
		List results = query.list();
		
		//System.out.println("getQtdePecaByModelo - Results:" + results.size());
		
		// Se houver um registro, retornamos sendo o Revisao da pesquisa.
		// Se houver mais de um registro temos um problema de banco de dados.
		// Se não houver nenhum registro, retornamos nulo.
		if ( results.size() == 1 ) {
				
			strPecas = ((BigDecimal)results.get(0)).toString();

		} else if ( results.size() > 1 )
			throw new DaoException("Too many entities found. There should be only one.");

		else
			throw new DaoException("O Item " + itemCode.toUpperCase() + " não foi localizado!");
		
		//System.out.println("getQtdePecaByModelo - Quantidade de Peças:" + strPecas);
		
		session.close();
		
		return Integer.parseInt(strPecas);	
		
	}
	
	/** Retorna verdadeiro se o produto é nacional
	 * 
	 * @param chassi
	 * 	<code>String</code>.
	 * 
	 * @return boolean
	 * 	<code>true</code>  se o produto é nacional.
	 * 
	 * @throws BusinessException
	 *      Se houverem erros de execução ou nas camadas 
	 *      abaixo dos serviços, serão encapsulados neste
	 *      tipo de <code>Exception</code>.
	 */
	public boolean isNacional(String chassi) throws DaoException {
		
		boolean isNac = false;
	
		/* Capturamos a origem do chassi - 'NAC' ou 'IMP' */
		String sqlOrigem = 	" select distinct yi.origem			 	" +
							" from   ym_sg_faturamento yf 		 	" +
							" 	   , ym_sg_item        yi 		 	" +
							" where  yi.item_code = yf.item_code 	" +
							"		 and yf.chassi = :chassi " +
							"		 and yf.start_date < :date						 " +
							"		 and (yf.end_date is null or yf.end_date > :date)" +
							"		 and yi.start_date < :date						 " +
							"		 and (yi.end_date is null or yi.end_date > :date)";
		
		Session session = super.getSession();
		
		SQLQuery queryOrigem  = session.createSQLQuery(sqlOrigem);
		
		queryOrigem.setParameter("chassi", chassi.trim().toUpperCase() );
		queryOrigem.setParameter("date"  , new Date());			
		
		String origem = new String();
		
		List results = queryOrigem.list();
		
		if ( results.size() == 0 )
	    
			return false;
	           
	    else if ( results.size() > 0 )
	    	
	    	origem = results.get(0).toString();
		
		
		//System.out.println(" Origem:" + origem);
		
		if ( origem.equals("")) {			
			
			throw new DaoException("Origem não localizada para o chassi:" + chassi.toUpperCase());
			
		}
		
		if ( origem.equals(Item.ORIGEM_NAC) ) {
			
			isNac = true;
			
		}
		
		session.close();
		
		return isNac;
	}
	
	/** Lista items a partir de um determinado chassi.
	 * 
	 *  @param chassi
	 *  	Chassi a ser utilizado como referência.
	 *  
	 *  @return
	 *  	Lista com itens encontrados.
	 *  
	 *  @throws BusinessException
	 *      Se houverem erros de execução ou nas camadas 
	 *      abaixo dos serviços, serão encapsulados neste
	 *      tipo de <code>Exception</code>.  	
	 */
	public List listByChassi(String chassi, Long linhaId) throws DaoException {
		
		//System.out.println("Valores Recebidos   Chassi:" + chassi + " - Linha:" + linhaId); 
		
		String  sqlPecas = 	" select distinct	item_id" +
							"			,descricao" +
							"			,item_code" +
							"			,quantidade" +
							" from (" +
							"			select  yi.item_id      item_id" +							
							"		  			, yi.descricao  descricao" +							
							"					, yx.item_code  item_code" +
							"					, yx.quantidade quantidade" +	
							" 			from 	ym_sg_faturamento   yf " +
							"					, ym_sg_modelo      ys " +
							"					, ym_sg_mpl         yx " +
							"					, ym_sg_item        yi " +
							"					, ym_sg_tabela_preco tp " +
							" 			where yf.modelo          = ys.modelo_id " +
							" 			and   ys.codigo_produto  = yx.codigo_produto " +
							" 			and   yx.item_code         = yi.item_code " +
							"			and   yi.item_id = tp.item_id " +
							"			and   yf.status_fat_id   = 1 " +							
							" 			and   yf.chassi          = :chassi " +
							"			and   yi.organization_id = 91 " +
							"			and   yi.segmento = 'PEÇAS' " +
							"			and   yi.end_date is null	" +
							"			and   yx.end_date is null   " +
							"			and   yf.end_date is null   " +
							"			UNION ALL " +
							" 			select  yi.item_id      item_id " +							
							"		  			, yi.descricao  descricao"+ 							
							"					, yx.item_code  item_code "+
							"					, yx.quantidade quantidade 	"+
							"			from 	ym_sg_faturamento   yf  "+
							"					, ym_sg_modelo      ys  "+
							"					, ym_sg_mpl         yx  "+
							"					, ym_sg_item        yi  "+
							"					, ym_sg_tabela_preco tp " +
							"			where yf.modelo          = ys.modelo_id  "+
							"			and   ys.codigo_produto  like yx.codigo_produto ||'%'  "+
							" 			and   yx.item_code         = yi.item_code  	" +
							"			and   yi.item_id = tp.item_id " +
							"			and   yf.status_fat_id   = 1 "+						
							" 			and    yf.chassi  =  :chassi " + 
							"			and   yi.organization_id = 91 " +
							"			and   yi.segmento = 'PEÇAS' " +
							"			and   yi.end_date is null	" +
							"			and   yx.end_date is null   " +
							"			and   yf.end_date is null   " +
							" 			UNION ALL" +
							" 			select 	yz.item_id 			item_id" +
							"					, yz.descricao      descricao" +
							"					, yz.item_code      item_code" +
							"					, 99999             quantidade" +
							" 			from ym_sg_item yz, ym_sg_tabela_preco tp   " +
							" 			where   yz.segmento   		= 'COMPL'" +
							"			and   yz.item_id = tp.item_id " +
							"			and   yz.organization_id = 91 " +
							"			and   yz.end_date is null	" +
							"			and   tp.end_date is null   " +
							" )" +
							" order by  item_code";		
		
		//System.out.println("Recuperando uma Sessão do Hibernate!");		
		Session session = super.getSession();
		
		List    results = new ArrayList();
		
		SQLQuery query = session.createSQLQuery(sqlPecas);
		
		query.setParameter("chassi", chassi.trim().toUpperCase());
		
		List itens = query.list();
		
		Iterator it = itens.iterator();
		
		while ( it.hasNext() ) {
		
			Item item = new Item();
			Object[] row = (Object[]) it.next();
			item.setEntityId((BigDecimal)row[0]);
			item.setDescricao((String)row[1]);
			item.setItemCode((String)row[2]);
			item.setQtdePecas((BigDecimal)row[3]);
			
			results.add(item);
		}	
		
		
		session.close();
		return results;	
    	
    }
	
	/** Lista items a partir de um determinado chassi.
	 * 
	 *  @param chassi
	 *  	Chassi a ser utilizado como referência.
	 *  
	 *  @param itemCodePart
	 *  
	 *  @param linhaId
	 *  	Linha do Produto.
	 *  
	 *  @return
	 *  	Lista com itens encontrados.
	 *  
	 *  @throws BusinessException
	 *      Se houverem erros de execução ou nas camadas 
	 *      abaixo dos serviços, serão encapsulados neste
	 *      tipo de <code>Exception</code>.  	
	 */
	public List listByChassi(String chassi, String itemCodePart, Long linhaId) throws DaoException {
		
		//System.out.println("Valores Recebidos   Chassi:" + chassi + " - item code:" + itemCodePart + " - Linha:" + linhaId); 
		
		String  sqlPecas = 	" select distinct item_id" +
							"			,descricao" +
							"			,item_code" +
							"			,quantidade" +
							" from (" +
							"			select  yi.item_id      item_id" +							
							"		  			, yi.descricao  descricao" +							
							"					, yx.item_code  item_code" +
							"					, yx.quantidade quantidade" +	
							" 			from 	ym_sg_faturamento   yf " +
							"					, ym_sg_modelo      ys " +
							"					, ym_sg_mpl         yx " +
							"					, ym_sg_item        yi " +
							"					, ym_sg_tabela_preco tp " +
							" 			where yf.modelo          = ys.modelo_id " +
							" 			and   ys.codigo_produto  = yx.codigo_produto " +
							" 			and   yx.item_code         = yi.item_code " +
							"			and   yi.item_id = tp.item_id " +
							"			and   yf.status_fat_id   = 1 " +							
							" 			and   yf.chassi          = :chassi " +
							"			and   yi.organization_id = 91 " +
							"			and   yi.segmento = 'PEÇAS' " +
							"			and   yi.end_date is null	" +
							"			and   yx.end_date is null   " +
							"			and   yf.end_date is null   " +
							"			UNION ALL " +
							" 			select  yi.item_id      item_id " +							
							"		  			, yi.descricao  descricao" + 							
							"					, yx.item_code  item_code " +
							"					, yx.quantidade quantidade 	" +
							"			from 	ym_sg_faturamento   yf  " + 
							"					, ym_sg_modelo      ys  " +
							"					, ym_sg_mpl         yx  " +
							"					, ym_sg_item        yi  " +
							"					, ym_sg_tabela_preco tp " +
							"			where yf.modelo          = ys.modelo_id  " +
							"			and   ys.codigo_produto  like yx.codigo_produto ||'%'  " +
							" 			and   yx.item_code         = yi.item_code  	" +
							"			and   yi.item_id = tp.item_id " +
							"			and   yf.status_fat_id   = 1 " +						
							" 			and    yf.chassi  		 =  :chassi " + 	
							"			and   yi.organization_id = 91 " +
							"			and   yi.segmento = 'PEÇAS' " +
							"			and   yi.end_date is null	" +
							"			and   yx.end_date is null   " +
							"			and   yf.end_date is null   " +
							" 			UNION ALL" +
							" 			select 	yz.item_id 			item_id" +
							"					, yz.descricao      descricao" +
							"					, yz.item_code      item_code" +
							"					, 99999             quantidade" +
							" 			from ym_sg_item yz , ym_sg_tabela_preco tp " +
							"			where yz.segmento		 = 'COMPL'" +
							"			and   yz.item_id = tp.item_id " +
							"			and   yz.organization_id = 91 " +
							"			and   yz.end_date is null	" +
							"			and   tp.end_date is null   " +
							" )" +
							" where item_code like  '" + itemCodePart.toUpperCase() + "%' " +
							" order by  item_code";
					
		//System.out.println("Recuperando uma Sessão do Hibernate!");		
		Session session = super.getSession();
		
		List    results = new ArrayList();
		
		SQLQuery query = session.createSQLQuery(sqlPecas);
		
		query.setParameter("chassi", chassi.trim().toUpperCase());
				
		List itens = query.list();
		
		Iterator it = itens.iterator();
		
		while ( it.hasNext() ) {
		
			Item item = new Item();
			Object[] row = (Object[]) it.next();
			item.setEntityId((BigDecimal)row[0]);
			item.setDescricao((String)row[1]);
			item.setItemCode((String)row[2]);
			item.setQtdePecas((BigDecimal)row[3]);
		
			results.add(item);
		}
		
		session.close();
		return results;	
    	
    }
	
	/** Retorna um objeto item a partir de um itemId e organizationId e do segmento MTC
	 * 
	 * @param itemId
	 * 	<code>Long</code> 
	 * 
	 * @param organizationId
	 * 	<code>Long</code> 
	 * 
	 * @return item
	 * 	<code>Item</code> 
	 * 
	 * @throws DaoException
	 */
	public Item getItemByOrg( Long ItemId, Long organizationId ) throws DaoException {

		DetachedCriteria criteria = super.getDetachedCriteria(Item.class);
		criteria.add( Expression.eq("entityId"        , ItemId )        );
		criteria.add( Expression.eq("organizationId"  , organizationId) );
		criteria.add( Expression.eq("segmento"        , "MTC") );

		List results = super.getHibernateTemplate().findByCriteria(criteria);

		// Se houver um registro, retornamos sendo o Item da pesquisa.
		// Se houver mais de um registro temos um problema de banco de dados.
		// Se não houver nenhum registro, retornamos nulo.
		if ( results.size() == 1 )
			return (Item) results.get(0);

		else if ( results.size() > 1 )
			throw new DaoException("Too many entities found. There should be only one.");

		else
			return null;

	}
	
	
	/** Retorna um objeto item a partir de um itemId e organizationId
	 * 
	 * @param itemId
	 * 	<code>Long</code> 
	 * 
	 * @param organizationId
	 * 	<code>Long</code> 
	 * 
	 * @return item
	 * 	<code>Item</code> 
	 * 
	 * @throws DaoException
	 */
	public Item getByOrg( Long ItemId, Long organizationId ) throws DaoException {

		DetachedCriteria criteria = super.getDetachedCriteria(Item.class);
		criteria.add( Expression.eq("entityId"        , ItemId )        );
		criteria.add( Expression.eq("organizationId"  , organizationId) );

		List results = super.getHibernateTemplate().findByCriteria(criteria);

		// Se houver um registro, retornamos sendo o Item da pesquisa.
		// Se houver mais de um registro temos um problema de banco de dados.
		// Se não houver nenhum registro, retornamos nulo.
		if ( results.size() == 1 )
			return (Item) results.get(0);

		else if ( results.size() > 1 )
			throw new DaoException("Too many entities found. There should be only one.");

		else
			return null;

	}
	
	/** Retorna um objeto item a partir de um itemId 
	 * 
	 * @param itemId
	 * 	<code>Long</code> 
	 *  
	 * @return item
	 * 	<code>Item</code> 
	 * 
	 * @throws DaoException
	 */
	public Item getItem( Long ItemId ) throws DaoException {
				
		DetachedCriteria criteria = super.getDetachedCriteria(Item.class);
		criteria.add( Expression.eq("entityId"        , ItemId )        );
		criteria.add( Expression.eq("organizationId"  , new Long(91)) );

		List results = super.getHibernateTemplate().findByCriteria(criteria);
		
		// Se houver um registro, retornamos sendo o Item da pesquisa.
		// Se houver mais de um registro temos um problema de banco de dados.
		// Se não houver nenhum registro, retornamos nulo.
		if ( results.size() ==  0 ) {
			
			criteria.add( Expression.eq("organizationId"  , new Long(89)) );

			results = super.getHibernateTemplate().findByCriteria(criteria);
			
		}
		
		if ( results.size() == 1 )
			return (Item) results.get(0);

		else if ( results.size() > 1 )
			throw new DaoException("Too many entities found. There should be only one.");

		else
			return null;

	}
	

}
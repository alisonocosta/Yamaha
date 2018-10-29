/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......UsuarioHibernateDaoImpl.java
 *   .: Criação.....13 de junho, 12:39.
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...DAO com tarefas específicas para a entidade Usuario.
 */
package br.com.yamaha.sistemagarantia.dao.hibernate;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;

import br.com.resource.infra.dao.hibernate.HibernateDaoImpl;
import br.com.resource.infra.exception.DaoException;
import br.com.yamaha.sistemagarantia.dao.UsuarioDao;
import br.com.yamaha.sistemagarantia.model.Programa;
import br.com.yamaha.sistemagarantia.model.ProgramaNivel;
import br.com.yamaha.sistemagarantia.model.Usuario;

/** Objeto de acesso aos dados com métodos específicos para
 *  a entidade "Usuário".
 *  
 *  @author Thiago Uriel M. Garcia
 */
public class UsuarioHibernateDaoImpl extends HibernateDaoImpl implements UsuarioDao {

	/** Lista os perfis de um usuário. */
	public List listRoles(Serializable nivelAcessoId) throws DaoException {
		
		List nivelAcessoList = new ArrayList();
		List programaList    = new ArrayList();
		
		DetachedCriteria criteriaNivelAcesso = super.getDetachedCriteria( ProgramaNivel.class );
		criteriaNivelAcesso.add( Expression.eq( "nivelAcessoId", nivelAcessoId ) );
		nivelAcessoList = super.getHibernateTemplate().findByCriteria( criteriaNivelAcesso );

		
		Iterator nivelAcessoIt = nivelAcessoList.iterator();
        while ( nivelAcessoIt.hasNext() ) {        	
        	
        	ProgramaNivel pn = (ProgramaNivel) nivelAcessoIt.next();
        	DetachedCriteria criteriaPrograma = super.getDetachedCriteria( Programa.class );
        	criteriaPrograma.add( Expression.eq( "entityId", pn.getProgramaId() ) );

        	List programaListTmp =  super.getHibernateTemplate().findByCriteria( criteriaPrograma );
        	
        	Iterator programaIt = programaListTmp.iterator();
        	
        	while ( programaIt.hasNext() ) {
        		
        		
        		Programa prog = (Programa)programaIt.next();
        		programaList.add( prog.getCodigoPrograma().toLowerCase() );
        		
        		//System.out.println("Código do Programa:" + prog.getCodigoPrograma().toLowerCase());
        		
        	}
        	
        }

        return programaList;
        
	}
	
	/** Busca um usuário pelo nome e pelo hash da senha.
     * 
     *  @param username
     *      <code>String</code> nome do usuário.
     *      
     *  @param hashPassword
     *  	<code>String</code> Hash da senha do usuário.
     *  
     *  @return
     *      <code>Usuario</code> Uma entidade do usuário.
     *  
     *  @throws DaoException
     *      Se houverem erros durante a execução estes serão lançados
     *      como uma Exception deste tipo.
     */
	public Usuario getUser(String username, String hashPassword) throws DaoException {
		
		DetachedCriteria criteria = super.getDetachedCriteria( Usuario.class );
    	criteria.add( Expression.eq( "usuarioCode"    , username ) );
    	criteria.add( Expression.eq( "password"       , hashPassword ) );
    	List results = super.getHibernateTemplate().findByCriteria(criteria);
       	// Se houver um registro, retornamos sendo o Revisao da pesquisa.
		// Se houver mais de um registro temos um problema de banco de dados.
		// Se não houver nenhum registro, retornamos nulo.
		if ( results.size() == 1 )
			return (Usuario) results.get(0);
		else if ( results.size() > 1 )
			throw new DaoException( "Mais de uma entidade de usuário foi localizada pelo nome e senha informados. " +
									" É esperado apenas uma!");
		else
			return null;
		
	}
	
	/**
	 * Atualiza o valor da Session de um usuário logado
	 *  
	 * @param String sessionUserId
	 * @param String usuarioCode
	 * @throws DaoException
	 */
	public void setSessionUserId(String sessionUserId, String usuarioCode)throws DaoException {
		try {
			String sqlUp = 	"UPDATE YM_SG_USUARIO SET SESSION_ID= ? WHERE USUARIO_CODE = ? ";

			Session session = super.getSession();

			PreparedStatement querySqlUp  = session.connection().prepareStatement(sqlUp);
			
			querySqlUp.setString(1, sessionUserId);
			querySqlUp.setString(2, usuarioCode);
			
			querySqlUp.execute();  
			querySqlUp.close();  
			
			session.close();
			
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
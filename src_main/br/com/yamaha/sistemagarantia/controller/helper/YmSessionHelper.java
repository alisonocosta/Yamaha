/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Infraestrutura para aplicativos Java
 *   .: Objeto......YmSessionHelper.java
 *   .: Criação.....20 de Julho, 11:07
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .:				Edson Luiz Sumensari
 *   .: Descrição...Implementação para o tratamento de objetos da 
 *   .:				sessão da aplicação
 */
package br.com.yamaha.sistemagarantia.controller.helper;

import java.sql.Blob;

import javax.servlet.http.HttpServletRequest;

import br.com.resource.infra.controller.helper.SessionHelper;
import br.com.resource.infra.model.EntityObject;
import br.com.resource.infra.security.SystemUser;
import br.com.yamaha.sistemagarantia.model.ParametroSistema;
import br.com.yamaha.sistemagarantia.model.to.CupomTO;
import br.com.yamaha.sistemagarantia.model.to.GarantiaTO;

/** Implementação para o tratamento de objetos da 
 *  sessão da aplicação
 *
 *	@author Edson Luiz Sumensari
 */
public class YmSessionHelper extends SessionHelper {
	
	/** Constante.
	 *  <p>
	 *  Label utilizado para indicar um cupomTO armazenado
	 *  na sessão do usuário corrente. 
	 */
	public static final String SESSION_CUPOMTO = "YM_SESSION_CUPOMTO";
	
	/** Constante.
	 *  <p>
	 *  Label utilizado para indicar uma garantiaTO armazenado
	 *  na sessão do usuário corrente. 
	 */
	public static final String SESSION_GARANTIATO = "YM_SESSION_GARANTIATO";
	
	/** Constante.
	 *  <p>
	 *  Label utilizado para indicar um OBJETO DO SISTEMA armazenado
	 *  na sessão do usuário corrente. 
	 */
	public static final String SESSION_ENTITY = "YM_SESSION_ENTITY";
	
	/** Constante.
	 *  <p>
	 *  Label utilizado para indicar lista de itens de nota fiscal armazenada
	 *  na sessão do usuário corrente. 
	 */
	public static final String SESSION_NOTA_ITENS = "YM_SESSION_NOTA_ITENS";
	
	/** Constante.
	 *  <p>
	 *  Label utilizado para indicar uma imagem serializada armazenada
	 *  na sessão do usuário corrente. 
	 */
	public static final String SESSION_CONTENT_IMAGE = "YM_SESSION_CONTENT_IMAGE";
	
    /** Constante.
     *  <p>
     *  Label utilizado para indicar a url apontada pelo logo do sistema.
     */    
    public static final String SESSION_LOGO_URL = "YM_SESSION_LOGO_URL";
    
    /** Constante.
     *  <p>
     *  Label utilizado para indicar um objeto na sessão do usuário interno da Yamaha.
     */    
    public static final String SESSION_USER_INNER = "YM_SESSION_USER_INNER";
    
    /** Obtém um objeto da sessão de acordo com seu identificador.
     * 
     *  @param request
     *   <code>HttpServletRequest<code> do qual a sessão alvo
     *   deverá ser obtida.
     * 
     * @return ParametroSistema
     *   <code>ParametroSistema</code> entidade recuperada da sessão ou null se não encontrar 
     *   ou ocorrer erro.
     */    
    public ParametroSistema getHomePageInternalUrl(HttpServletRequest request) {
        
        try {
            
            return (ParametroSistema)getFromSession(request, SESSION_LOGO_URL);
            
        } catch ( ClassCastException cExp ) {
            
            return null;
        }
        
    }
    
    /** Atrela um objeto ParametroSistema à sessão do usuário.
     * 
     *  @param request
     *      <code>HttpServletRequest<code> do qual a sessão alvo
     *      deverá ser obtida.
     *      
     *  @param parametroSistema
     *      <code>ParametroSistema</code> que será inserido na sessão.    
     */
    public static void setParametroSistemaToSession(HttpServletRequest request, ParametroSistema parametroSistema) {
                
        setToSession(request, SESSION_LOGO_URL, parametroSistema, true);      
                
    }    
    
	/** Obtém um objeto da sessão de acordo com seu identificador.
	 * 
	 *  @param request
	 *   <code>HttpServletRequest<code> do qual a sessão alvo
	 *   deverá ser obtida.
	 * 
	 * @return cupomTO
	 * 	 <code>CupomTO</code> entidade recuperada da sessão ou null se não encontrar 
	 *   ou ocorrer erro.
	 */
	public static CupomTO getCupomTOFromSession(HttpServletRequest request) {
		
		try {
		
			return (CupomTO)getFromSession(request, SESSION_CUPOMTO);
		
		} catch ( ClassCastException cExp ) {
			
			return null;
		}
		
	}
	
	/** Atrela um objeto cupomTO à sessão do usuário.
	 * 
	 *  @param request
	 *  	<code>HttpServletRequest<code> do qual a sessão alvo
	 *  	deverá ser obtida.
	 *  	
	 *  @param cupomTO
	 *  	<code>CupomTO</code> que será inserido na sessão.	 
	 */
	public static void setCupomTOToSession(HttpServletRequest request, CupomTO cupomTO) {
				
		setToSession(request, SESSION_CUPOMTO, cupomTO, true);		
				
	}
	
	/** Obtém um objeto da sessão de acordo com seu identificador.
	 * 
	 *  @param request
	 *   <code>HttpServletRequest<code> do qual a sessão alvo
	 *   deverá ser obtida.
	 * 
	 * @return garantiaTO
	 * 	 <code>GarantiaTO</code> entidade recuperada da sessão ou null se não encontrar 
	 *   ou ocorrer erro.
	 */
	public static GarantiaTO getGarantiaTOFromSession(HttpServletRequest request) {
		
		try {
		
			return (GarantiaTO)getFromSession(request, SESSION_GARANTIATO);
		
		} catch ( ClassCastException cExp ) {
			
			return null;
		}
		
	}
	
	/** Atrela um objeto garantiaTO à sessão do usuário.
	 * 
	 *  @param request
	 *  	<code>HttpServletRequest<code> do qual a sessão alvo
	 *  	deverá ser obtida.
	 *  	
	 *  @param garantiaTO
	 *  	<code>GarantiaTO</code> que será inserido na sessão.	 
	 */
	public static void setGarantiaTOToSession(HttpServletRequest request, GarantiaTO garantiaTO) {
				
		setToSession(request, SESSION_GARANTIATO, garantiaTO, true);		
				
	}
	
	/** Obtém um objeto da sessão de acordo com seu identificador.
	 * 
	 *  @param request
	 *   <code>HttpServletRequest<code> do qual a sessão alvo
	 *   deverá ser obtida.
	 * 
	 * @return EntityObject
	 * 	 <code>EntityObject</code> entidade recuperada da sessão ou null se não encontrar 
	 *   ou ocorrer erro.
	 */
	public static EntityObject getEntityObjectFromSession(HttpServletRequest request) {
		
		try {
		
			return (EntityObject)getFromSession(request, SESSION_ENTITY);
		
		} catch ( ClassCastException cExp ) {
			
			return null;
		}
		
	}
	
	/** Atrela um objeto cupomTO à sessão do usuário.
	 * 
	 *  @param request
	 *  	<code>HttpServletRequest<code> do qual a sessão alvo
	 *  	deverá ser obtida.
	 *  	
	 *  @param entity
	 *  	<code>EntityObject</code> que será inserido na sessão.	 
	 */
	public static void setEntityObjectToSession(HttpServletRequest request, EntityObject entity) {
				
		setToSession(request, SESSION_ENTITY, entity, true);		
				
	}
	
	/** Atrela um objeto cupomTO à sessão do usuário.
	 * 
	 *  @param request
	 *  	<code>HttpServletRequest<code> do qual a sessão alvo
	 *  	deverá ser obtida.
	 *  	
	 *  @param targetsItens
	 *  	<code>String[]</code> que será inserido na sessão.	 
	 */
	public static void setNotaItensToSession(HttpServletRequest request, String targetsItens[]) {
				
		setToSession(request, SESSION_NOTA_ITENS, targetsItens, true);		
				
	}
	
	/** Obtém um objeto da sessão de acordo com seu identificador.
	 * 
	 *  @param request
	 *   <code>HttpServletRequest<code> do qual a sessão alvo
	 *   deverá ser obtida.
	 * 
	 * @return targetsItens
	 * 	 <code>String[]</code> lista de itens selecionados para lançamento de notas.
	 */
	public static String[] getNotaItensFromSession(HttpServletRequest request) {
		
		try {
		
			return (String[])getFromSession(request, SESSION_NOTA_ITENS);
		
		} catch ( ClassCastException cExp ) {
			
			return null;
		}
		
	}
	/** Atrela uma imagem à sessão do usuário.
	 * 
	 *  @param request
	 *  	<code>HttpServletRequest<code> do qual a sessão alvo
	 *  	deverá ser obtida.
	 *  	
	 *  @param content
	 *  	<code>Blob</code> arquivo de imagem.	 
	 */
	public static void setImageToSession(HttpServletRequest request, Blob content) {
				
		setToSession(request, SESSION_CONTENT_IMAGE, content, true);		
				
	}
	
	/** Obtém um objeto da sessão de acordo com seu identificador.
	 * 
	 *  @param request
	 *   	<code>HttpServletRequest<code> do qual a sessão alvo
	 *   	deverá ser obtida.
	 * 
	 * @return content
	 * 	 <code>Blob</code> Arquivo.
	 */
	public static Blob getImageFromSession(HttpServletRequest request) {
		
		try {
		
			return (Blob)getFromSession(request, SESSION_CONTENT_IMAGE);
		
		} catch ( ClassCastException cExp ) {
			
			return null;
		}
		
	}
	
	/** Atrela um usuário interno da Yamaha à sessão do usuário.
	 * 
	 *  @param request
	 *  	<code>HttpServletRequest<code> do qual a sessão alvo
	 *  	deverá ser obtida.
	 *  	
	 *  @param SystemUser user
	 *  	<code>SystemUser</code> usuário.	 
	 */
	public static void setInnerUserToSession(HttpServletRequest request, SystemUser user) {
				
		setToSession(request, SESSION_USER_INNER, user, true);		
				
	}
	
	/** Obtém um usuário interno da sessão de acordo com seu identificador.
	 * 
	 *  @param request
	 *   	<code>HttpServletRequest<code> do qual a sessão alvo
	 *   	deverá ser obtida.
	 * 
	 * @return content
	 * 	 <code>SystemUser</code> Usuário.
	 */
	public static SystemUser getInnerUserFromSession(HttpServletRequest request) {
		
		try {
		
			return (SystemUser)getFromSession(request, SESSION_USER_INNER);
		
		} catch ( ClassCastException cExp ) {
			
			return null;
		}
		
	}
	
	/** remove um usuário interno da Yamaha da sessão do usuário.
	 * 
	 *  @param request
	 *  	<code>HttpServletRequest<code> do qual a sessão alvo
	 *  	deverá ser obtida. 
	 */
	public static void removeInnerUserToSession(HttpServletRequest request) {
				
		removeFromSession(request, SESSION_USER_INNER);		
				
	}
	
	/** remove um CupomTO da sessão do usuário.
	 * 
	 *  @param request
	 *  	<code>HttpServletRequest<code> do qual a sessão alvo
	 *  	deverá ser obtida. 
	 */
	public static void removeCupomTOToSession(HttpServletRequest request) {
				
		removeFromSession(request, SESSION_CUPOMTO);		
				
	}
	
	/** remove um Garantia da sessão do usuário.
	 * 
	 *  @param request
	 *  	<code>HttpServletRequest<code> do qual a sessão alvo
	 *  	deverá ser obtida. 
	 */
	public static void removeGarantiaTOToSession(HttpServletRequest request) {
				
		removeFromSession(request, SESSION_GARANTIATO);		
				
	}
	
	/** remove Notas da sessão do usuário.
	 * 
	 *  @param request
	 *  	<code>HttpServletRequest<code> do qual a sessão alvo
	 *  	deverá ser obtida. 
	 */
	public static void removeNotaItensToSession(HttpServletRequest request) {
				
		removeFromSession(request, SESSION_NOTA_ITENS);		
				
	}
	/** remove imagem da sessão do usuário.
	 * 
	 *  @param request
	 *  	<code>HttpServletRequest<code> do qual a sessão alvo
	 *  	deverá ser obtida. 
	 */
	public static void removeImageToSession(HttpServletRequest request) {
				
		removeFromSession(request, SESSION_CONTENT_IMAGE);		
				
	}	
	
	/** remove todos os itens da sessão do usuário.
	 * 
	 *  @param request
	 *  	<code>HttpServletRequest<code> do qual a sessão alvo
	 *  	deverá ser obtida. 
	 */
	public static void removeAllToSession(HttpServletRequest request) {
				
		removeFromSession(request, SESSION_CONTENT_IMAGE);
		removeFromSession(request, SESSION_NOTA_ITENS);	
		removeFromSession(request, SESSION_GARANTIATO);	
		removeFromSession(request, SESSION_CUPOMTO);
		removeFromSession(request, SESSION_USER_INNER);	
				
	}
	
	/**
	 *  Remove um EntityObject da sessão do usuário
	 *  
	 *  @param request
	 *   	<code>HttpServletRequest<code> do qual a sessão alvo
	 *   	deverá acessada.
	 *   
	 */
	public static void removeEntity(HttpServletRequest request) {
		
		request.getSession().removeAttribute(SESSION_ENTITY);
		
		
	}


}

/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......Usuario.java
 *   .: Criação.....07 de maio, 14:51
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "Usuario".
 */
package br.com.yamaha.sistemagarantia.model;

import java.io.Serializable;
import java.util.List;

import br.com.resource.infra.model.EntityObject;
import br.com.resource.infra.security.SystemUser;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "Usuario" no sistema e de sessão da aplicação.
 *  
 *  @author Thiago Uriel M. Garcia
 *  		Edson Luiz Sumensari
 */
public class Usuario extends EntityObject  implements SystemUser {
	
//	----[ Atributos de classe e instância ]-----------------------------------
	
	 /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L; 
    
    public static final String NIVEL_ACESSO_CONCESSIONARIA = "C";
    
    public static final String NIVEL_ACESSO_OPERADOR       = "O";
    
    public static final String NIVEL_ACESSO_ADMINISTRADOR  = "A";
    
    public static final String NIVEL_ACESSO_SUPER_USUARIO  = "S";
    
    /** Código do usuário Usuario. */	
	private String usuarioCode;
	
	/** Nome do usuário */
	private String nome;
	
	/** Senha do usuário */
	private String password;
	
	/** Segmento / Linha do produto do usuário */
	private Long segmento;
	
	/** Email do usuário */
	private String email;
		
	/** instância de relacionamento com uma concessionária */
	private Concessionaria concessionaria;	
	
	/** instância de relacionamento com um NivelAcesso */
	private NivelAcesso nivelAcesso;
	
	/** Armazena perfis deste usuário. */
	private List roles;

	//	----[ Métodos Getter ]---------------------------------------------------
	
	
	
	/** Este método recupera o atributo concessionária
	 * 
	 * @param <code>String</code> nome do atributo.
	 * @return <code>Object</code> atributo.
	 * 
	 */
	public Object getAttribute(String attributeName) {
		
		if ("concessionaria".equals(attributeName))
			return this.concessionaria;		
		else
			return null;
		
	}
	
	/** Método setter para um atributo do usuário
	 * 
	 * @param <code>String</code> nome do atributo.
	 * @param <code>Object</code> atributo.
	 * 
	 */
	public void setAttribute(String attributeName, Object attribute) {
		
		if ("concessionaria".equals(attributeName))
			this.concessionaria = (Concessionaria) attribute;
		
	}
	
	/** Método que compara o id de uma entidade com um id de um atributo do usuário
	 * 
	 * @param attributeName  Nome do Atributo do usuário
	 * 
	 * @param entityId
	 * 
	 * @return <code>true<code> quando id enviado form igual ao id do atributo do usuário
	 */
	public boolean compareIdAtribute(String attributeName, Serializable entityId) {
		
		boolean isAtribute = false;
		
		if ("concessionaria".equals(attributeName)){
			if ( this.concessionaria.getEntityId().equals(entityId) )
				isAtribute = true;
		} 
		
		return isAtribute;
		
	}
	
	/** Método getter para "Identificador".
     *  @return
     *      <code>Object</code>. O valor atual de Identificador.
     */
	public Object getIdentifier() {
		return super.getEntityId();
	}
	
	/** Método getter para "nome".
     *  @return
     *      <code>String</code>. O valor atual de nome.
     */
	public String getUsername() {
		return this.nome;
	}

	/** Método getter para "concessionaria".
     *  @return
     *      <code>Object Concessionaria</code>. O valor atual de concessionaria.
     */
	public Concessionaria getConcessionaria() {
		return concessionaria;
	}

	/** Método getter para "email".
     *  @return
     *      <code>String</code>. O valor atual de email.
     */
	public String getEmail() {
		return email;
	}

	/** Método getter para "email".
     *  @return
     *      <code>String</code>. O valor atual de email.
     */
	public String getNome() {
		return nome;
	}

	/** Método getter para "usuarioCode".
     *  @return
     *      <code>String</code>. O valor atual de usuarioCode.
     */
	public String getUsuarioCode() {
		return usuarioCode;
	}
	
	/** Método getter para a propriedade segmento
	 * 
	 *  @return Long
	 *
	 */
	public Long getSegmento() {
		return segmento;
	}
	
	/** Método getter para a propriedade nivelAcesso
	 * 
	 *  @return NivelAcesso
	 *
	 */
	public NivelAcesso getNivelAcesso() {
		return nivelAcesso;
	}
	
	/** Método getter para a propriedade password
	 *
	 *  @return String de password
	 */
	public String getPassword() {
		return password;
	}

	
	//	----[ Métodos Setter ]---------------------------------------------------

	/** Método setter para a propriedade concessionaria
	 * @param concessionaria para setar concessionaria
	 */
	public void setConcessionaria(Concessionaria concessionaria) {
		this.concessionaria = concessionaria;
	}

	/** Método setter para a propriedade email
	 * @param email para setar email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/** Método setter para a propriedade nome
	 * @param nome para setar nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/** Método setter para a propriedade usuarioCode
	 * @param usuarioCode para setar usuarioCode
	 */
	public void setUsuarioCode(String usuarioCode) {
		this.usuarioCode = usuarioCode;
	}

	/** Método setter para a propriedade segmento
	 *
	 * @param segmento 
	 *           <code>Long</code> a ser designado para segmento.
	 * 
	 */
	public void setSegmento(Long segmento) {
		this.segmento = segmento;
	}

	/* (non-Javadoc)
	 * @see br.com.resource.infra.security.SystemUser#hasRole(java.lang.String)
	 */
	public boolean hasRole(Object roleName) {
		
		return this.roles.contains( roleName );
		
	}
	
	public void addRole(Object roleName) {
		
		this.roles.add( roleName );
		
	}

	/**
	 * @return the roles
	 */
	public List getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List roles) {
		this.roles = roles;
	}
	

	/** Método setter para a propriedade nivelAcesso
	 *
	 * @param nivelAcesso 
	 *           <code>NivelAcesso</code> a ser designado para nivelAcesso.
	 * 
	 */
	public void setNivelAcesso(NivelAcesso nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}
	
	/** Retorna TRUE se o nível de acesso enviado for de usuário interno da Yamaha
	 * 
	 * @param nivelAcesso
	 * @return boolean
	 * 	<CODE>TRUE</CODE> - Nível de acesso usuário interno da Yamaha
	 * 	<CODE>FALSE</CODE> - Nível de acesso concessionária ou representante
	 */
	public static boolean isInterno( String nivelAcesso ) {
		
		return 	   NIVEL_ACESSO_OPERADOR.equalsIgnoreCase(nivelAcesso)
				|| NIVEL_ACESSO_ADMINISTRADOR.equalsIgnoreCase(nivelAcesso)
				|| NIVEL_ACESSO_SUPER_USUARIO.equalsIgnoreCase(nivelAcesso)
				? true : false;
	}
	
	/** Método setter para a propriedade password
	 *
	 * @param password String
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}

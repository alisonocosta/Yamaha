/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Infraestrutura para aplicativos Java
 *   .: Objeto......EntityObject.java
 *   .: Criação.....16 de fevereiro, 15:56
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Implementação base para uma entidade do sistema.
 */
package br.com.resource.infra.model;

import java.io.Serializable;
import java.util.Date;

import br.com.resource.infra.core.SystemObject;
import br.com.resource.infra.utils.DateUtils;

/** Classe básica para objetos que representam alguma informação do banco de dados.
 *  <p/>
 *  Esta classe deverá ser extendida por qualquer objeto que reflita alguma
 *  informação persistente. Sua configuração e de forma serializada para que
 *  possa ser utilizado em um ambiente de cluster.
 *
 *  @author Thiago Uriel M. Garcia
 */
public class EntityObject implements SystemObject {
 
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;
    
    /** Chave-primária desta entidade.
     *  <p/>
     *  A partir deste identificador um registro de banco de dados poderá ser 
     *  encontrado e até comparado. 
     *  <p/>
     *  O id ("identificador") de uma entidade é obrigatório para qualquer uma 
     *  de suas subclasses. Sendo assim, foi implementado na classe padrão.
     */
    private Serializable entityId;

    /** Identificador do usuário que criou esta entidade. */
    private Long criadoPor;
    
    /** Identificador do usuário que alterou esta entidade. */
    private Long atualizadoPor;
    
    /** Data de criação desta entidade. */
    private Date dataCriacao;
    
    /** Data de atualização desta entidade. */
    private Date dataAtualizacao;
    
    /** Data de início da valida desta entidade. */
    private Date dataInicio;
    
    /** Data de término da validade desta entidade. */
    private Date dataTermino;
    
    /** Construtor padrão (default).
     *  <p/>
     *  Inicializa o valor da propriedade <code>entityId</code> em 0, para que
     *  esta entidade possa ser considerada como "nova".
     */
    public EntityObject() {
        
        super();
        
        // Fornece "null" como valor para o campo ID. Isto indicará
        // para a aplicação que este bean é novo.
        this.setEntityId(null);
        
        this.setDataInicio(new Date());
        this.setDataCriacao(new Date());
        
    }
    
    /** Indica se esta entidade é nova (um id de valor 0)  ou se já existia 
     *  (um id cujo valor já foi preenchido).
     *  <p/>
     *  Este método é necessário pois o atributo "id" não é visível para outros 
     *  objetos. E a utilização do método "getId" obrigaria o desenvolver a 
     *  utilizar uma cláusula <code>if</code> um pouco mais complexa.
     *  <p/>
     *  Sendo assim, a implementação deste método se fez necessária.
     * 
     *  @return boolean   
     *     Um valor booleano indicando se esta entidade é nova ou não.
     */
    public boolean isNew() {

        if ( this.getEntityId() == null )
            return true;
        else 
            return false;

    }

    /** Sobrescrita do método <code>equals</code>. 
     *  <p/>  
     *  Aqui, uma entidade será igual a outra <b>sempre</b> que ambas forem 
     *  entidades do mesmo tipo e possuirem o mesmo ID. Este é justamente o 
     *  conceito de comparação de registros de banco de dados: Um registro é 
     *  igual a outro se forem da mesma tabela e possuirem a mesma chave.
     * 
     *  @param  java.lang.Object
     *      Um objeto de referência com o qual esta entidade
     *      será comparada.
     * 
     *  @return boolean 
     *      O resultado da comparação entre os objetos.
     */
    public boolean equals(Object object) {

        // Averiguação real de igualdade:
        boolean isSameClass = ( this.getClass() == object.getClass() );
        boolean isSameId    = false;
        if ( isSameClass ) {
            isSameId = ( this.getEntityId() == ( (EntityObject) object ).getEntityId() );
        } else return false; 

        if ( isSameClass && isSameId ) return true;

        // Se não retornou até agora, então é falso! 
        return false;

    }

    /** Sobrescrita do método <code>toString</code>. 
     *  <p/>
     *  Sua função é fornecer uma String "identificável" que de alguma forma 
     *  represente de forma única o objeto corrente.
     *  <p/>
     *  Contudo, está conversão também não é adequada ao propósitode uma entidade. 
     *  Por padrão, este método retornaria o nome da classe utilizada e um 
     *  identificador utilizado pela Virtual Machine que executa a aplicação.
     *  <p/>
     *  Aqui, será utilizada uma implementação que retorna a classe usada (como 
     *  o original), porém retorna o identificador da entidade (sua propriedade ID).
     * 
     *  @return java.lang.String  
     *      Uma string identificadora desta entidade.
     */
    public String toString() {

        return ( this.getClass().toString() + ": " + this.getEntityId().toString() );

    }

    /** Método getter para "entityId".
     *  @return
     *      <code>Serializable</code>. O valor atual de entityId.
     */
    public Serializable getEntityId() {
        return this.entityId;
    }

    /** Método setter para "entityId".
     *  @param entityId
     *      <code>Serializable</code> a ser designado para entityId.
     */
    public void setEntityId(Serializable entityId) {
        this.entityId = entityId;
    }
    
    /** Método setter para "entityId".
     *  @param entityId
     *      <code>Object</code> a ser designado para entityId.
     */
    public void setEntityId(Object entityId) {
    	
    	Long 	entityIdL 	= null;
    	String 	entityIdStr = null;
    	Integer entityIdInt = null;
        
    	if ( entityId instanceof Long ) {    		
        	
    		entityIdL = (Long) entityId;
        	
    		if ( entityIdL.equals( new Long(0) ) || entityIdL == null )    		
    			this.entityId = null;
    		else
    			this.entityId = entityIdL;
    		
    	} else if ( entityId instanceof String ){
    		
    		entityIdStr = String.valueOf(entityId);
    		
    		if ( "0".equals(entityIdStr)  || entityId == null )
    			this.entityId = null;
    		else
    			this.entityId = Long.valueOf(entityIdStr);
    		
    	} else if ( entityId instanceof Integer ){
    		
    		entityIdInt = (Integer) entityId;
    		
    		if ( entityIdInt.equals( new Integer(0) ) || entityIdInt == null )    		
    			this.entityId = null;
    		else
    			this.entityId = entityIdInt;
    		
    	}
    	
    }
    
    /** Método setter para "entityId".
     *  @param entityId
     *      <code>long</code> a ser designado para entityId.
     */
    public void setEntityId(long entityId) {
    	
    	if ( entityId == 0 )
    		this.entityId = null;
    	else
    		this.entityId = new Long(entityId);
    	
    }

    /** Método getter para "atualizadoPor".
     *  @return
     *      <code>Long</code>. O valor atual de atualizadoPor.
     */
    public Long getAtualizadoPor() {
        return this.atualizadoPor;
    }

    /** Método getter para "criadoPor".
     *  @return
     *      <code>Long</code>. O valor atual de criadoPor.
     */
    public Long getCriadoPor() {
        return this.criadoPor;
    }

    /** Método getter para "dataAtualizacao".
     *  @return
     *      <code>Date</code>. O valor atual de dataAtualizacao.
     */
    public Date getDataAtualizacao() {
        return this.dataAtualizacao;
    }

    /** Método getter para "dataCriacao".
     *  @return
     *      <code>String</code>. O valor atual de dataCriacao.
     */
    public String getMaskedDataAtualizacao() {
        return DateUtils.applyMask(this.dataAtualizacao);
    }    
    
    /** Método getter para "dataCriacao".
     *  @return
     *      <code>Date</code>. O valor atual de dataCriacao.
     */
    public Date getDataCriacao() {
        return this.dataCriacao;
    }
    
    /** Método getter para "dataInicio".
     *  @return
     *      <code>Date</code>. O valor atual de dataInicio.
     */
    public Date getDataInicio() {
        return this.dataInicio;
    }

    /** Método getter para "dataInicio".
     *  @return
     *      <code>String</code>. O valor atual de dataInicio.
     */
    public String getMaskedDataInicio() {
        return DateUtils.applyMask(this.dataInicio);
    }    
    
    /** Método getter para "dataTermino".
     *  @return
     *      <code>Date</code>. O valor atual de dataTermino.
     */
    public Date getDataTermino() {
        return this.dataTermino;
    }

    /** Método getter para "dataTermino".
     *  @return
     *      <code>String</code>. O valor atual de dataTermino.
     */
    public String getMaskedDataTermino() {
        return DateUtils.applyMask(this.dataTermino);
    }    

    /** Método getter para "dataCriacao".
     *  @return
     *      <code>String</code>. O valor atual de dataCriacao.
     */
    public String getMaskedDataCriacao() {
        return DateUtils.applyMask(this.dataCriacao);
    }    
    
    /** Método setter para "atualizadoPor".
     *  @param entityId
     *      <code>Long</code> a ser designado para atualizadoPor.
     */
    public void setAtualizadoPor(Long atualizadoPor) {
        this.atualizadoPor = atualizadoPor;
    }

    /** Método setter para "criadoPor".
     *  @param entityId
     *      <code>Long</code> a ser designado para criadoPor.
     */
    public void setCriadoPor(Long criadoPor) {
        this.criadoPor = criadoPor;
    }

    /** Método setter para "dataAtualizacao".
     *  @param entityId
     *      <code>Object</code> a ser designado para dataAtualizacao.
     */
    public void setDataAtualizacao(Object dataAtualizacao) {
        this.dataAtualizacao = DateUtils.secureSet(dataAtualizacao);
    }

    /** Método setter para "dataCriacao".
     *  @param entityId
     *      <code>Object</code> a ser designado para dataCriacao.
     */
    public void setDataCriacao(Object dataCriacao) {
        this.dataCriacao = DateUtils.secureSet(dataCriacao);
    }
    
    /** Método setter para "dataInicio".
     *  @param entityId
     *      <code>Date</code> a ser designado para dataInicio.
     */
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = DateUtils.secureSet(dataInicio);
    }

    /** Método setter para "dataTermino".
     *  @param entityId
     *      <code>Date</code> a ser designado para dataTermino.
     */
    public void setDataTermino(Date dataTermino) {
        this.dataTermino = DateUtils.secureSet(dataTermino);
    }
   
}
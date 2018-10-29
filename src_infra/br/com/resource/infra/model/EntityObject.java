/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Infraestrutura para aplicativos Java
 *   .: Objeto......EntityObject.java
 *   .: Cria��o.....16 de fevereiro, 15:56
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descri��o...Implementa��o base para uma entidade do sistema.
 */
package br.com.resource.infra.model;

import java.io.Serializable;
import java.util.Date;

import br.com.resource.infra.core.SystemObject;
import br.com.resource.infra.utils.DateUtils;

/** Classe b�sica para objetos que representam alguma informa��o do banco de dados.
 *  <p/>
 *  Esta classe dever� ser extendida por qualquer objeto que reflita alguma
 *  informa��o persistente. Sua configura��o e de forma serializada para que
 *  possa ser utilizado em um ambiente de cluster.
 *
 *  @author Thiago Uriel M. Garcia
 */
public class EntityObject implements SystemObject {
 
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;
    
    /** Chave-prim�ria desta entidade.
     *  <p/>
     *  A partir deste identificador um registro de banco de dados poder� ser 
     *  encontrado e at� comparado. 
     *  <p/>
     *  O id ("identificador") de uma entidade � obrigat�rio para qualquer uma 
     *  de suas subclasses. Sendo assim, foi implementado na classe padr�o.
     */
    private Serializable entityId;

    /** Identificador do usu�rio que criou esta entidade. */
    private Long criadoPor;
    
    /** Identificador do usu�rio que alterou esta entidade. */
    private Long atualizadoPor;
    
    /** Data de cria��o desta entidade. */
    private Date dataCriacao;
    
    /** Data de atualiza��o desta entidade. */
    private Date dataAtualizacao;
    
    /** Data de in�cio da valida desta entidade. */
    private Date dataInicio;
    
    /** Data de t�rmino da validade desta entidade. */
    private Date dataTermino;
    
    /** Construtor padr�o (default).
     *  <p/>
     *  Inicializa o valor da propriedade <code>entityId</code> em 0, para que
     *  esta entidade possa ser considerada como "nova".
     */
    public EntityObject() {
        
        super();
        
        // Fornece "null" como valor para o campo ID. Isto indicar�
        // para a aplica��o que este bean � novo.
        this.setEntityId(null);
        
        this.setDataInicio(new Date());
        this.setDataCriacao(new Date());
        
    }
    
    /** Indica se esta entidade � nova (um id de valor 0)  ou se j� existia 
     *  (um id cujo valor j� foi preenchido).
     *  <p/>
     *  Este m�todo � necess�rio pois o atributo "id" n�o � vis�vel para outros 
     *  objetos. E a utiliza��o do m�todo "getId" obrigaria o desenvolver a 
     *  utilizar uma cl�usula <code>if</code> um pouco mais complexa.
     *  <p/>
     *  Sendo assim, a implementa��o deste m�todo se fez necess�ria.
     * 
     *  @return boolean   
     *     Um valor booleano indicando se esta entidade � nova ou n�o.
     */
    public boolean isNew() {

        if ( this.getEntityId() == null )
            return true;
        else 
            return false;

    }

    /** Sobrescrita do m�todo <code>equals</code>. 
     *  <p/>  
     *  Aqui, uma entidade ser� igual a outra <b>sempre</b> que ambas forem 
     *  entidades do mesmo tipo e possuirem o mesmo ID. Este � justamente o 
     *  conceito de compara��o de registros de banco de dados: Um registro � 
     *  igual a outro se forem da mesma tabela e possuirem a mesma chave.
     * 
     *  @param  java.lang.Object
     *      Um objeto de refer�ncia com o qual esta entidade
     *      ser� comparada.
     * 
     *  @return boolean 
     *      O resultado da compara��o entre os objetos.
     */
    public boolean equals(Object object) {

        // Averigua��o real de igualdade:
        boolean isSameClass = ( this.getClass() == object.getClass() );
        boolean isSameId    = false;
        if ( isSameClass ) {
            isSameId = ( this.getEntityId() == ( (EntityObject) object ).getEntityId() );
        } else return false; 

        if ( isSameClass && isSameId ) return true;

        // Se n�o retornou at� agora, ent�o � falso! 
        return false;

    }

    /** Sobrescrita do m�todo <code>toString</code>. 
     *  <p/>
     *  Sua fun��o � fornecer uma String "identific�vel" que de alguma forma 
     *  represente de forma �nica o objeto corrente.
     *  <p/>
     *  Contudo, est� convers�o tamb�m n�o � adequada ao prop�sitode uma entidade. 
     *  Por padr�o, este m�todo retornaria o nome da classe utilizada e um 
     *  identificador utilizado pela Virtual Machine que executa a aplica��o.
     *  <p/>
     *  Aqui, ser� utilizada uma implementa��o que retorna a classe usada (como 
     *  o original), por�m retorna o identificador da entidade (sua propriedade ID).
     * 
     *  @return java.lang.String  
     *      Uma string identificadora desta entidade.
     */
    public String toString() {

        return ( this.getClass().toString() + ": " + this.getEntityId().toString() );

    }

    /** M�todo getter para "entityId".
     *  @return
     *      <code>Serializable</code>. O valor atual de entityId.
     */
    public Serializable getEntityId() {
        return this.entityId;
    }

    /** M�todo setter para "entityId".
     *  @param entityId
     *      <code>Serializable</code> a ser designado para entityId.
     */
    public void setEntityId(Serializable entityId) {
        this.entityId = entityId;
    }
    
    /** M�todo setter para "entityId".
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
    
    /** M�todo setter para "entityId".
     *  @param entityId
     *      <code>long</code> a ser designado para entityId.
     */
    public void setEntityId(long entityId) {
    	
    	if ( entityId == 0 )
    		this.entityId = null;
    	else
    		this.entityId = new Long(entityId);
    	
    }

    /** M�todo getter para "atualizadoPor".
     *  @return
     *      <code>Long</code>. O valor atual de atualizadoPor.
     */
    public Long getAtualizadoPor() {
        return this.atualizadoPor;
    }

    /** M�todo getter para "criadoPor".
     *  @return
     *      <code>Long</code>. O valor atual de criadoPor.
     */
    public Long getCriadoPor() {
        return this.criadoPor;
    }

    /** M�todo getter para "dataAtualizacao".
     *  @return
     *      <code>Date</code>. O valor atual de dataAtualizacao.
     */
    public Date getDataAtualizacao() {
        return this.dataAtualizacao;
    }

    /** M�todo getter para "dataCriacao".
     *  @return
     *      <code>String</code>. O valor atual de dataCriacao.
     */
    public String getMaskedDataAtualizacao() {
        return DateUtils.applyMask(this.dataAtualizacao);
    }    
    
    /** M�todo getter para "dataCriacao".
     *  @return
     *      <code>Date</code>. O valor atual de dataCriacao.
     */
    public Date getDataCriacao() {
        return this.dataCriacao;
    }
    
    /** M�todo getter para "dataInicio".
     *  @return
     *      <code>Date</code>. O valor atual de dataInicio.
     */
    public Date getDataInicio() {
        return this.dataInicio;
    }

    /** M�todo getter para "dataInicio".
     *  @return
     *      <code>String</code>. O valor atual de dataInicio.
     */
    public String getMaskedDataInicio() {
        return DateUtils.applyMask(this.dataInicio);
    }    
    
    /** M�todo getter para "dataTermino".
     *  @return
     *      <code>Date</code>. O valor atual de dataTermino.
     */
    public Date getDataTermino() {
        return this.dataTermino;
    }

    /** M�todo getter para "dataTermino".
     *  @return
     *      <code>String</code>. O valor atual de dataTermino.
     */
    public String getMaskedDataTermino() {
        return DateUtils.applyMask(this.dataTermino);
    }    

    /** M�todo getter para "dataCriacao".
     *  @return
     *      <code>String</code>. O valor atual de dataCriacao.
     */
    public String getMaskedDataCriacao() {
        return DateUtils.applyMask(this.dataCriacao);
    }    
    
    /** M�todo setter para "atualizadoPor".
     *  @param entityId
     *      <code>Long</code> a ser designado para atualizadoPor.
     */
    public void setAtualizadoPor(Long atualizadoPor) {
        this.atualizadoPor = atualizadoPor;
    }

    /** M�todo setter para "criadoPor".
     *  @param entityId
     *      <code>Long</code> a ser designado para criadoPor.
     */
    public void setCriadoPor(Long criadoPor) {
        this.criadoPor = criadoPor;
    }

    /** M�todo setter para "dataAtualizacao".
     *  @param entityId
     *      <code>Object</code> a ser designado para dataAtualizacao.
     */
    public void setDataAtualizacao(Object dataAtualizacao) {
        this.dataAtualizacao = DateUtils.secureSet(dataAtualizacao);
    }

    /** M�todo setter para "dataCriacao".
     *  @param entityId
     *      <code>Object</code> a ser designado para dataCriacao.
     */
    public void setDataCriacao(Object dataCriacao) {
        this.dataCriacao = DateUtils.secureSet(dataCriacao);
    }
    
    /** M�todo setter para "dataInicio".
     *  @param entityId
     *      <code>Date</code> a ser designado para dataInicio.
     */
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = DateUtils.secureSet(dataInicio);
    }

    /** M�todo setter para "dataTermino".
     *  @param entityId
     *      <code>Date</code> a ser designado para dataTermino.
     */
    public void setDataTermino(Date dataTermino) {
        this.dataTermino = DateUtils.secureSet(dataTermino);
    }
   
}
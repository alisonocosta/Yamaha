/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......AlertCupom.java
 *   .: Criação.....21 de maio, 10:53
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "AlertCupom".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;
import br.com.yamaha.sistemagarantia.model.id.AlertCupomId;


/** Entidade do sistema, representando
 *  um objeto "AlertCupom" no sistema.
 *  
 *  OBS: Está classe possui id composto,formado pela classe AlertCupomId - propriedades:
 *       	<code>alertCupomSeq</code> e 
 *       	<code>cupomId</code>.
 *  
 *  @author Edson Luiz Sumensari
 */
public class AlertCupom extends EntityObject {
	
	//----[ Atributos de classe e instância ]-----------------------------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;   
    
    /** Contante para severidade de Erro */
    public static final String SEVERIDADE_ERROR = "E";
    
    /** Constante para severida de Warning */
    public static final String SEVERIDADE_WARNING = "W";    
    
    /** Id composto do Alerta, tratado na classe AlertCupomId */
    private AlertCupomId compositeEntityId;
    
    /** Código de Severidade do alerta */
    private String alertCupomSeveridade;
    
    /** Mensagem do Alerta */
    private String alertCupomText;
    
    /** Chave da mensagem de properties */
    private String alertCupomKey;
    
    /** Construtor */
    public AlertCupom() {
    	
    	// Fornece "null" como valor para o campo ID. Isto indicará
        // para a aplicação que este bean é novo.
        this.setEntityId(null);
        this.setCompositeEntityId(null);
    	
    }
    
    //	----[ Métodos Getter ]---------------------------------------------------

    
	/** Método getter para a propriedade alertCupomSeveridade
	 * 
	 *  @return String
	 *
	 */
	public String getAlertCupomSeveridade() {
		return alertCupomSeveridade;
	}

	/** Método getter para a propriedade alertCupomText
	 * 
	 *  @return String
	 *
	 */
	public String getAlertCupomText() {
		return alertCupomText;
	}
	
	/** Método getter para a propriedade compositeEntityId
	 * 
	 *  @return AlertCupomId
	 *
	 */
	public AlertCupomId getCompositeEntityId() {
		return compositeEntityId;
	}
	
	/** Método getter para a propriedade alertCupomSeq
	 * 
	 * @return Long alertCupomSeq
	 */
	public Long getAlertCupomSeq() {
		
		return (Long)this.compositeEntityId.getAlertCupomSeq();
		
	}
	
	/** Método getter para a propridade cupomId 
	 * 
	 *  @return Long cupomId
	 */
	public Long getCupomId() {
		
		return (Long)this.compositeEntityId.getCupomId();
		
	}
	
	/** Método getter para a propriedade alertCupomKey
	 * 
	 *  @return String
	 *
	 */
	public String getAlertCupomKey() {
		return alertCupomKey;
	}
	
	//	----[ Métodos Setter ]---------------------------------------------------

	/** Método setter para a propriedade alertCupomSeveridade
	 *
	 * @param alertCupomSeveridade 
	 *           <code>String</code> a ser designado para alertCupomSeveridade.
	 * 
	 */
	public void setAlertCupomSeveridade(String alertCupomSeveridade) {
		this.alertCupomSeveridade = alertCupomSeveridade;
	}

	/** Método setter para a propriedade alertCupomText
	 *
	 * @param alertCupomText 
	 *           <code>String</code> a ser designado para alertCupomText.
	 * 
	 */
	public void setAlertCupomText(String alertCupomText) {
		this.alertCupomText = alertCupomText;
	}	

	/** Método setter para a propriedade compositeEntityId
	 *
	 * @param compositeEntityId 
	 *           <code>AlertCupomId</code> a ser designado para compositeEntityId.
	 * 
	 */
	public void setCompositeEntityId(AlertCupomId compositeEntityId) {
		this.compositeEntityId = compositeEntityId;
	}
	
	/** Método setter para a propriedade compositeEntityId
	 *
	 * @param alertCupomSeq 
	 *           <code>Long</code> a ser designado para alertCupomSeq.
	 *           
	 * @param cupomId
	 * 			<code>Long</code> a ser designado para cupomId.
	 * 
	 */
	public void setCompositeEntityId(Long alertCupomSeq, Long cupomId) {
		
		this.compositeEntityId = new AlertCupomId(alertCupomSeq, cupomId);
		
	}
	
	/** Método setter para a propriedade alertCupomKey
	 *
	 * @param alertCupomKey 
	 *           <code>String</code> a ser designado para alertCupomKey.
	 * 
	 */
	public void setAlertCupomKey(String alertCupomKey) {
		
		this.alertCupomKey = alertCupomKey;
		
	}
	
	//	----[ Métodos de Serviço ]---------------------------------------------------
	
	/** Indica se esta entidade é nova (um id de valor null)  ou se já existia 
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

        if ( this.getCompositeEntityId() == null )
            return true;
        else 
            return false;

    }
    
}

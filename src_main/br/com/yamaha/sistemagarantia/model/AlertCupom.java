/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......AlertCupom.java
 *   .: Cria��o.....21 de maio, 10:53
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "AlertCupom".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;
import br.com.yamaha.sistemagarantia.model.id.AlertCupomId;


/** Entidade do sistema, representando
 *  um objeto "AlertCupom" no sistema.
 *  
 *  OBS: Est� classe possui id composto,formado pela classe AlertCupomId - propriedades:
 *       	<code>alertCupomSeq</code> e 
 *       	<code>cupomId</code>.
 *  
 *  @author Edson Luiz Sumensari
 */
public class AlertCupom extends EntityObject {
	
	//----[ Atributos de classe e inst�ncia ]-----------------------------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;   
    
    /** Contante para severidade de Erro */
    public static final String SEVERIDADE_ERROR = "E";
    
    /** Constante para severida de Warning */
    public static final String SEVERIDADE_WARNING = "W";    
    
    /** Id composto do Alerta, tratado na classe AlertCupomId */
    private AlertCupomId compositeEntityId;
    
    /** C�digo de Severidade do alerta */
    private String alertCupomSeveridade;
    
    /** Mensagem do Alerta */
    private String alertCupomText;
    
    /** Chave da mensagem de properties */
    private String alertCupomKey;
    
    /** Construtor */
    public AlertCupom() {
    	
    	// Fornece "null" como valor para o campo ID. Isto indicar�
        // para a aplica��o que este bean � novo.
        this.setEntityId(null);
        this.setCompositeEntityId(null);
    	
    }
    
    //	----[ M�todos Getter ]---------------------------------------------------

    
	/** M�todo getter para a propriedade alertCupomSeveridade
	 * 
	 *  @return String
	 *
	 */
	public String getAlertCupomSeveridade() {
		return alertCupomSeveridade;
	}

	/** M�todo getter para a propriedade alertCupomText
	 * 
	 *  @return String
	 *
	 */
	public String getAlertCupomText() {
		return alertCupomText;
	}
	
	/** M�todo getter para a propriedade compositeEntityId
	 * 
	 *  @return AlertCupomId
	 *
	 */
	public AlertCupomId getCompositeEntityId() {
		return compositeEntityId;
	}
	
	/** M�todo getter para a propriedade alertCupomSeq
	 * 
	 * @return Long alertCupomSeq
	 */
	public Long getAlertCupomSeq() {
		
		return (Long)this.compositeEntityId.getAlertCupomSeq();
		
	}
	
	/** M�todo getter para a propridade cupomId 
	 * 
	 *  @return Long cupomId
	 */
	public Long getCupomId() {
		
		return (Long)this.compositeEntityId.getCupomId();
		
	}
	
	/** M�todo getter para a propriedade alertCupomKey
	 * 
	 *  @return String
	 *
	 */
	public String getAlertCupomKey() {
		return alertCupomKey;
	}
	
	//	----[ M�todos Setter ]---------------------------------------------------

	/** M�todo setter para a propriedade alertCupomSeveridade
	 *
	 * @param alertCupomSeveridade 
	 *           <code>String</code> a ser designado para alertCupomSeveridade.
	 * 
	 */
	public void setAlertCupomSeveridade(String alertCupomSeveridade) {
		this.alertCupomSeveridade = alertCupomSeveridade;
	}

	/** M�todo setter para a propriedade alertCupomText
	 *
	 * @param alertCupomText 
	 *           <code>String</code> a ser designado para alertCupomText.
	 * 
	 */
	public void setAlertCupomText(String alertCupomText) {
		this.alertCupomText = alertCupomText;
	}	

	/** M�todo setter para a propriedade compositeEntityId
	 *
	 * @param compositeEntityId 
	 *           <code>AlertCupomId</code> a ser designado para compositeEntityId.
	 * 
	 */
	public void setCompositeEntityId(AlertCupomId compositeEntityId) {
		this.compositeEntityId = compositeEntityId;
	}
	
	/** M�todo setter para a propriedade compositeEntityId
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
	
	/** M�todo setter para a propriedade alertCupomKey
	 *
	 * @param alertCupomKey 
	 *           <code>String</code> a ser designado para alertCupomKey.
	 * 
	 */
	public void setAlertCupomKey(String alertCupomKey) {
		
		this.alertCupomKey = alertCupomKey;
		
	}
	
	//	----[ M�todos de Servi�o ]---------------------------------------------------
	
	/** Indica se esta entidade � nova (um id de valor null)  ou se j� existia 
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

        if ( this.getCompositeEntityId() == null )
            return true;
        else 
            return false;

    }
    
}

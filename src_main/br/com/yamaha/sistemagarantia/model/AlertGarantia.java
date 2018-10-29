/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......AlertGarantia.java
 *   .: Cria��o.....21 de junho, 09:57
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "AlertGarantia".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;


/** Entidade do sistema, representando
 *  um objeto "AlertGarantia" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class AlertGarantia extends EntityObject {
	
	//----[ Atributos de classe e inst�ncia ]-----------------------------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;   
    
    /** Contante para severidade de Erro */
    public static final String SEVERIDADE_ERROR = "E";
    
    /** Constante para severida de Warning */
    public static final String SEVERIDADE_WARNING = "W";  
    
    /** C�digo de Severidade do alerta */
    private String alertGarantiaSeveridade;
    
    /** Mensagem do Alerta */
    private String alertGarantiaText;
    
    /** Chave da mensagem de properties */
    private String alertGarantiaKey;
    
    /** Par�metro da mensagem de properties */
    private String param;
    
    /** Atributo da mensagem de properties */
    private String atributo;
    
    /** Entidade de garantia */
    private GarantiaHeader garantia;
    
    /** Construtor padr�o*/
    public AlertGarantia() {
    	
    	// Fornece "null" como valor para o campo ID. Isto indicar�
        // para a aplica��o que este bean � novo.
        this.setEntityId(null);
        this.setParam("");
    	
    }
    
    /** F�brica de alertas 
     * 
     * @param text 
     * 	<code>String</code> - Texto com a mensagem de alerta
     * 
     * @param
     * 	<code>String</code> - Severidade do alerta - Warning / Error 
     * 
     * @param
     * 	<code>String</code> - Chave do arquivo de properties para a mensagem
     * 
     * @return AlertGarantia
     * 
     * */
    public static AlertGarantia getAlertGarantia( String text, String severidade, String key) {
    	
    	// Fornece "null" como valor para o campo ID. Isto indicar�
        // para a aplica��o que este bean � novo.
    	AlertGarantia alert = new AlertGarantia();
    	alert.setEntityId(null);
    	alert.setAlertGarantiaText(text);
    	alert.setAlertGarantiaSeveridade(severidade);
    	alert.setAlertGarantiaKey(key);  
    	
    	return alert;
    	
    }
    
    /** F�brica de alertas 
     * 
     * @param text 
     * 	<code>String</code> - Texto com a mensagem de alerta
     * 
     * @param
     * 	<code>String</code> - Severidade do alerta - Warning / Error 
     * 
     * @param
     * 	<code>String</code> - Chave do arquivo de properties para a mensagem
     * 
     * @param
     * 	<code>String</code> -Par�metro para a mensagem
     * 
     * @return AlertGarantia
     * 
     * */
    public static AlertGarantia getAlertGarantia( String text, String severidade, String key, String param) {
    	
    	// Fornece "null" como valor para o campo ID. Isto indicar�
        // para a aplica��o que este bean � novo.
    	AlertGarantia alert = new AlertGarantia();
    	alert.setEntityId(null);
    	alert.setAlertGarantiaText(text);
    	alert.setAlertGarantiaSeveridade(severidade);
    	alert.setAlertGarantiaKey(key);  
    	alert.setParam(param);
    	
    	return alert;
    	
    }
    
    //	----[ M�todos Getter ]---------------------------------------------------

    /** M�todo getter para a propriedade alertGarantiaKey
	 * 
	 *  @return String
	 *
	 */
	public String getAlertGarantiaKey() {
		return alertGarantiaKey;
	}

	/** M�todo getter para a propriedade alertGarantiaSeveridade
	 * 
	 *  @return String
	 *
	 */
	public String getAlertGarantiaSeveridade() {
		return alertGarantiaSeveridade;
	}

	/** M�todo getter para a propriedade alertGarantiaText
	 * 
	 *  @return String
	 *
	 */
	public String getAlertGarantiaText() {
		return alertGarantiaText;
	}	


	/** M�todo getter para a propriedade garantia
	 * 
	 *  @return GarantiaHeader
	 *
	 */
	public GarantiaHeader getGarantia() {
		return garantia;
	}

	/**
	 * M�todo getter para a propriedade param
	 * @return  String de param
	 */
	public String getParam() {
		return param;
	}

	
	//	----[ M�todos Setter ]---------------------------------------------------

	/** M�todo setter para a propriedade alertGarantiaKey
	 *
	 * @param alertGarantiaKey 
	 *           <code>String</code> a ser designado para alertGarantiaKey.
	 * 
	 */
	public void setAlertGarantiaKey(String alertGarantiaKey) {
		this.alertGarantiaKey = alertGarantiaKey;
	}

	/** M�todo setter para a propriedade alertGarantiaSeveridade
	 *
	 * @param alertGarantiaSeveridade 
	 *           <code>String</code> a ser designado para alertGarantiaSeveridade.
	 * 
	 */
	public void setAlertGarantiaSeveridade(String alertGarantiaSeveridade) {
		this.alertGarantiaSeveridade = alertGarantiaSeveridade;
	}

	/** M�todo setter para a propriedade alertGaratniaText
	 *
	 * @param alertGarantiaText 
	 *           <code>String</code> a ser designado para alertGarantiaText.
	 * 
	 */
	public void setAlertGarantiaText(String alertGarantiaText) {
		this.alertGarantiaText = alertGarantiaText;
	}
	
	/** M�todo setter para a propriedade garantia
	 *
	 * @param garantia 
	 *           <code>GarantiaHeader</code> a ser designado para garantia.
	 * 
	 */
	public void setGarantia(GarantiaHeader garantia) {
		this.garantia = garantia;
	}

	/**
	 * M�todo setter para a propriedade param
	 * @param param String
	 */
	public void setParam(String param) {
		this.param = param;
	}

	/** M�todo getter para a propriedade atributo
	 *
	 * @return atributo do tipo String
	 *
	 */
	
	public String getAtributo() {
		return atributo;
	}

	/** M�todo setter para a propriedade atributo
	 *
	 * @param atributo 
	 *       <code>atributo<code> a ser designado para AlertGarantia.java
	 *
	 */
	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}
	
	
	
}

/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......AlertGarantia.java
 *   .: Criação.....21 de junho, 09:57
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "AlertGarantia".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;


/** Entidade do sistema, representando
 *  um objeto "AlertGarantia" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class AlertGarantia extends EntityObject {
	
	//----[ Atributos de classe e instância ]-----------------------------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;   
    
    /** Contante para severidade de Erro */
    public static final String SEVERIDADE_ERROR = "E";
    
    /** Constante para severida de Warning */
    public static final String SEVERIDADE_WARNING = "W";  
    
    /** Código de Severidade do alerta */
    private String alertGarantiaSeveridade;
    
    /** Mensagem do Alerta */
    private String alertGarantiaText;
    
    /** Chave da mensagem de properties */
    private String alertGarantiaKey;
    
    /** Parâmetro da mensagem de properties */
    private String param;
    
    /** Atributo da mensagem de properties */
    private String atributo;
    
    /** Entidade de garantia */
    private GarantiaHeader garantia;
    
    /** Construtor padrão*/
    public AlertGarantia() {
    	
    	// Fornece "null" como valor para o campo ID. Isto indicará
        // para a aplicação que este bean é novo.
        this.setEntityId(null);
        this.setParam("");
    	
    }
    
    /** Fábrica de alertas 
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
    	
    	// Fornece "null" como valor para o campo ID. Isto indicará
        // para a aplicação que este bean é novo.
    	AlertGarantia alert = new AlertGarantia();
    	alert.setEntityId(null);
    	alert.setAlertGarantiaText(text);
    	alert.setAlertGarantiaSeveridade(severidade);
    	alert.setAlertGarantiaKey(key);  
    	
    	return alert;
    	
    }
    
    /** Fábrica de alertas 
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
     * 	<code>String</code> -Parâmetro para a mensagem
     * 
     * @return AlertGarantia
     * 
     * */
    public static AlertGarantia getAlertGarantia( String text, String severidade, String key, String param) {
    	
    	// Fornece "null" como valor para o campo ID. Isto indicará
        // para a aplicação que este bean é novo.
    	AlertGarantia alert = new AlertGarantia();
    	alert.setEntityId(null);
    	alert.setAlertGarantiaText(text);
    	alert.setAlertGarantiaSeveridade(severidade);
    	alert.setAlertGarantiaKey(key);  
    	alert.setParam(param);
    	
    	return alert;
    	
    }
    
    //	----[ Métodos Getter ]---------------------------------------------------

    /** Método getter para a propriedade alertGarantiaKey
	 * 
	 *  @return String
	 *
	 */
	public String getAlertGarantiaKey() {
		return alertGarantiaKey;
	}

	/** Método getter para a propriedade alertGarantiaSeveridade
	 * 
	 *  @return String
	 *
	 */
	public String getAlertGarantiaSeveridade() {
		return alertGarantiaSeveridade;
	}

	/** Método getter para a propriedade alertGarantiaText
	 * 
	 *  @return String
	 *
	 */
	public String getAlertGarantiaText() {
		return alertGarantiaText;
	}	


	/** Método getter para a propriedade garantia
	 * 
	 *  @return GarantiaHeader
	 *
	 */
	public GarantiaHeader getGarantia() {
		return garantia;
	}

	/**
	 * Método getter para a propriedade param
	 * @return  String de param
	 */
	public String getParam() {
		return param;
	}

	
	//	----[ Métodos Setter ]---------------------------------------------------

	/** Método setter para a propriedade alertGarantiaKey
	 *
	 * @param alertGarantiaKey 
	 *           <code>String</code> a ser designado para alertGarantiaKey.
	 * 
	 */
	public void setAlertGarantiaKey(String alertGarantiaKey) {
		this.alertGarantiaKey = alertGarantiaKey;
	}

	/** Método setter para a propriedade alertGarantiaSeveridade
	 *
	 * @param alertGarantiaSeveridade 
	 *           <code>String</code> a ser designado para alertGarantiaSeveridade.
	 * 
	 */
	public void setAlertGarantiaSeveridade(String alertGarantiaSeveridade) {
		this.alertGarantiaSeveridade = alertGarantiaSeveridade;
	}

	/** Método setter para a propriedade alertGaratniaText
	 *
	 * @param alertGarantiaText 
	 *           <code>String</code> a ser designado para alertGarantiaText.
	 * 
	 */
	public void setAlertGarantiaText(String alertGarantiaText) {
		this.alertGarantiaText = alertGarantiaText;
	}
	
	/** Método setter para a propriedade garantia
	 *
	 * @param garantia 
	 *           <code>GarantiaHeader</code> a ser designado para garantia.
	 * 
	 */
	public void setGarantia(GarantiaHeader garantia) {
		this.garantia = garantia;
	}

	/**
	 * Método setter para a propriedade param
	 * @param param String
	 */
	public void setParam(String param) {
		this.param = param;
	}

	/** Método getter para a propriedade atributo
	 *
	 * @return atributo do tipo String
	 *
	 */
	
	public String getAtributo() {
		return atributo;
	}

	/** Método setter para a propriedade atributo
	 *
	 * @param atributo 
	 *       <code>atributo<code> a ser designado para AlertGarantia.java
	 *
	 */
	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}
	
	
	
}

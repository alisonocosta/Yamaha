/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......AlertEntityTO.java
 *   .: Criação.....18 de Agosto, 10:21
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "AlertEntityTO" - Tratamento para problemas no processamento das entidades e das mensagens.
 */
package br.com.yamaha.sistemagarantia.model.to;

/** Entidade do sistema, representando
 *  um objeto "AlertEntityTO" no sistema.
 *  
 *  OBS: Tratamento para problemas no processamento das entidades e das mensagens para a view
 *  
 *  @author Edson Luiz Sumensari
 */
public class AlertEntityTO {
	
	//----[ Atributos de classe e instância ]-----------------------------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;   
    
    /** Contante para severidade de Erro */
    public static final String SEVERIDADE_ERROR = "E";
    
    /** Constante para severida de Warning */
    public static final String SEVERIDADE_WARNING = "W";
    
    /** Constante para severida de Warning */
    public static final String SEVERIDADE_SUCCESS = "S";
    
    /** Código de Severidade do alerta */
    private String alertSeveridade;
    
    /** Mensagem do Alerta */
    private String alertText;
    
    /** Chave da mensagem de properties */
    private String alertKey;
    
	/**
	 * Método getter para a propriedade alertSeveridade
	 * @return  String de alertSeveridade
	 */
	public String getAlertSeveridade() {
		return alertSeveridade;
	}

	/**
	 * Método setter para a propriedade alertSeveridade
	 * @param alertSeveridade String
	 */
	public void setAlertSeveridade(String alertSeveridade) {
		this.alertSeveridade = alertSeveridade;
	}

	/**
	 * Método getter para a propriedade alertText
	 * @return  String de alertText
	 */
	public String getAlertText() {
		return alertText;
	}

	/**
	 * Método setter para a propriedade alertText
	 * @param alertText String
	 */
	public void setAlertText(String alertText) {
		this.alertText = alertText;
	}

	/**
	 * Método getter para a propriedade alertKey
	 * @return  String de alertKey
	 */
	public String getAlertKey() {
		return alertKey;
	}

	/**
	 * Método setter para a propriedade alertKey
	 * @param alertKey String
	 */
	public void setAlertKey(String alertKey) {
		this.alertKey = alertKey;
	}
}

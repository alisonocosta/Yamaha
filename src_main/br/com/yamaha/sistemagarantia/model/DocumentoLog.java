/* Resource Tecnologia
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......DocumentoLog.java
 *   .: Cria��o.....10 de Setembro de 2008
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "DocumentoLog".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, representando
 *  um objeto "DocumentoLog" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class DocumentoLog extends EntityObject {
		
	//----[ Atributos de classe e inst�ncia ]-----------------------------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L; 
    
    /** Constante para a opera��o de UPLOAD */
    public static final String OPERACAO_UPLOAD   = "UPLOAD";
    /** Constante para a opera��o de DOWNLOAD */
    public static final String OPERACAO_DOWNLOAD = "DOWNLOAD";
    /** Constante para a opera��o de UPDATE */
    public static final String OPERACAO_UPDATE   = "UPDATE";
    /** Constante para a opera��o de REMOVE */
    public static final String OPERACAO_REMOVE   = "REMOVE";
    
    /** Armazena a Opera��o. */
    private String operacao;
    
    /** Armazena a Observa��o da Opera��o. */
    private String observacao;
    
    /** Entidade do usu�rio. */
    private Usuario usuario;
    
    /** Entidade do Documento */
    private Documento  documento;

//	----[ M�todos Getter ]---------------------------------------------------
    /**
	 * M�todo getter para a propriedade usuario
	 * @return  Usuario de usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * M�todo getter para a propriedade operacao
	 * @return  String de operacao
	 */
	public String getOperacao() {
		return operacao;
	}
	
	/**
	 * M�todo getter para a propriedade documento
	 * @return  Documento de documento
	 */
	public Documento getDocumento() {
		return documento;
	}

	/**
	 * M�todo getter para a propriedade observacao
	 * @return  String de observacao
	 */
	public String getObservacao() {
		return observacao;
	}

	
//	----[ M�todos Setter ]---------------------------------------------------

	/**
	 * M�todo setter para a propriedade operacao
	 * @param operacao String
	 */
	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
	
	/**
	 * M�todo setter para a propriedade usuario
	 * @param usuario Usuario
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}	

	/**
	 * M�todo setter para a propriedade documento
	 * @param documento Documento
	 */
	public void setDocumento(Documento documento) {
		this.documento = documento;
	}
	
	/**
	 * M�todo setter para a propriedade observacao
	 * @param observacao String
	 */
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
		
}

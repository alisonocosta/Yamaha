/* Resource Tecnologia
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......DocumentoLog.java
 *   .: Criação.....10 de Setembro de 2008
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "DocumentoLog".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, representando
 *  um objeto "DocumentoLog" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class DocumentoLog extends EntityObject {
		
	//----[ Atributos de classe e instância ]-----------------------------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L; 
    
    /** Constante para a operação de UPLOAD */
    public static final String OPERACAO_UPLOAD   = "UPLOAD";
    /** Constante para a operação de DOWNLOAD */
    public static final String OPERACAO_DOWNLOAD = "DOWNLOAD";
    /** Constante para a operação de UPDATE */
    public static final String OPERACAO_UPDATE   = "UPDATE";
    /** Constante para a operação de REMOVE */
    public static final String OPERACAO_REMOVE   = "REMOVE";
    
    /** Armazena a Operação. */
    private String operacao;
    
    /** Armazena a Observação da Operação. */
    private String observacao;
    
    /** Entidade do usuário. */
    private Usuario usuario;
    
    /** Entidade do Documento */
    private Documento  documento;

//	----[ Métodos Getter ]---------------------------------------------------
    /**
	 * Método getter para a propriedade usuario
	 * @return  Usuario de usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * Método getter para a propriedade operacao
	 * @return  String de operacao
	 */
	public String getOperacao() {
		return operacao;
	}
	
	/**
	 * Método getter para a propriedade documento
	 * @return  Documento de documento
	 */
	public Documento getDocumento() {
		return documento;
	}

	/**
	 * Método getter para a propriedade observacao
	 * @return  String de observacao
	 */
	public String getObservacao() {
		return observacao;
	}

	
//	----[ Métodos Setter ]---------------------------------------------------

	/**
	 * Método setter para a propriedade operacao
	 * @param operacao String
	 */
	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
	
	/**
	 * Método setter para a propriedade usuario
	 * @param usuario Usuario
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}	

	/**
	 * Método setter para a propriedade documento
	 * @param documento Documento
	 */
	public void setDocumento(Documento documento) {
		this.documento = documento;
	}
	
	/**
	 * Método setter para a propriedade observacao
	 * @param observacao String
	 */
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
		
}

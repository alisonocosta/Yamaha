/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......ProgramaNivel.java
 *   .: Criação.....13 de junho, 12:07
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Entidade "ProgramaNivel".
 */
package br.com.yamaha.sistemagarantia.model;

import java.io.Serializable;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema representando a tabela "ProgramaNivel".
 *  
 *  @author Thiago Uriel M. Garcia
 */
public class ProgramaNivel extends EntityObject {

	 /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L; 	
	
	private Serializable nivelAcessoId;
	private Serializable programaId;
	
	
	/**
	 * @return the nivelAcessoId
	 */
	public Serializable getNivelAcessoId() {
		return nivelAcessoId;
	}
	/**
	 * @param nivelAcessoId the nivelAcessoId to set
	 */
	public void setNivelAcessoId(Serializable nivelAcessoId) {
		this.nivelAcessoId = nivelAcessoId;
	}
	/**
	 * @return the programaId
	 */
	public Serializable getProgramaId() {
		return programaId;
	}
	/**
	 * @param programaId the programaId to set
	 */
	public void setProgramaId(Serializable programaId) {
		this.programaId = programaId;
	}
	
}
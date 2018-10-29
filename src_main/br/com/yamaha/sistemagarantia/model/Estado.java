/* Resource Tecnologia
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......Estado.java
 *   .: Cria��o.....05 de Setembro de 2008
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "Estado".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, representando
 *  um objeto "Estado" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class Estado extends EntityObject {
		
	//----[ Atributos de classe e inst�ncia ]-----------------------------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L; 
    
    /** Armazena o UF. */
    private String uf;
    
    /** Armazena o estado. */
    private String estado;
    
    /** Armazena a flag de faturamento para a Yamaha ou Cliente */
    private String  faturamentoYM;
    
    //	----[ M�todos Getter ]---------------------------------------------------
    
	/** M�todo getter para a propriedade estado.
	 *
	 *  @return o valor atual de estado.
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * M�todo getter para a propriedade uf
	 * @return  String de uf
	 */
	public String getUf() {
		return uf;
	}
	
	/**
	 * M�todo getter para a propriedade faturamentoYM
	 * @return  String de faturamentoYM
	 */
	public String getFaturamentoYM() {
		return faturamentoYM;
	}
    
    //	----[ M�todos Setter ]---------------------------------------------------
    
	/** Obt�m o valor atual de estado.
	 * 
	 *  @param estado 
	 *    O novo valor para estado.
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * M�todo setter para a propriedade uf
	 * @param uf String
	 */
	public void setUf(String uf) {
		this.uf = uf;
	}

	/**
	 * M�todo setter para a propriedade faturamentoYM
	 * @param faturamentoYM String
	 */
	public void setFaturamentoYM(String faturamentoYM) {
		this.faturamentoYM = faturamentoYM;
	}		
}

/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......FatorGarantia.java
 *   .: Cria��o.....02 de Outubro de 2007
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "FatorGarantia".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, representando
 *  um objeto "FatorGarantia" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class FatorGarantia extends EntityObject {
	
	//----[ Atributos de classe e inst�ncia ]-----------------------------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L; 
    
    /** Estado no qual a Concession�ria se localiza. */
    private String estado;
    
    /** Fator de Garantia. */
    private double fatorGarantia;
    
    //	----[ M�todos Getter ]---------------------------------------------------
    
	/** M�todo getter para a propriedade estado.
	 *
	 *  @return o valor atual de estado.
	 */
	public String getEstado() {
		return estado;
	}
	
	/** M�todo getter para a propriedade fatorGarantia
	 * 
	 *  @return double
	 *
	 */
	public double getFatorGarantia() {
		return fatorGarantia;
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

	/** M�todo setter para a propriedade fatorGarantia
	 *
	 * @param fatorGarantia 
	 *           <code>double</code> a ser designado para fatorGarantia.
	 * 
	 */
	public void setFatorGarantia(double fatorGarantia) {
		this.fatorGarantia = fatorGarantia;
	}
	
}

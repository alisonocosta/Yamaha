/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......FatorGarantia.java
 *   .: Criação.....02 de Outubro de 2007
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "FatorGarantia".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, representando
 *  um objeto "FatorGarantia" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class FatorGarantia extends EntityObject {
	
	//----[ Atributos de classe e instância ]-----------------------------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L; 
    
    /** Estado no qual a Concessionária se localiza. */
    private String estado;
    
    /** Fator de Garantia. */
    private double fatorGarantia;
    
    //	----[ Métodos Getter ]---------------------------------------------------
    
	/** Método getter para a propriedade estado.
	 *
	 *  @return o valor atual de estado.
	 */
	public String getEstado() {
		return estado;
	}
	
	/** Método getter para a propriedade fatorGarantia
	 * 
	 *  @return double
	 *
	 */
	public double getFatorGarantia() {
		return fatorGarantia;
	}

    //	----[ Métodos Setter ]---------------------------------------------------
 
	/** Obtém o valor atual de estado.
	 * 
	 *  @param estado 
	 *    O novo valor para estado.
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/** Método setter para a propriedade fatorGarantia
	 *
	 * @param fatorGarantia 
	 *           <code>double</code> a ser designado para fatorGarantia.
	 * 
	 */
	public void setFatorGarantia(double fatorGarantia) {
		this.fatorGarantia = fatorGarantia;
	}
	
}

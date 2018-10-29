/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......ModeloVO.java
 *   .: Criação.....04 de outubro de 2007
 *   .: Autor.......Gisele Panosso
 *   .: Descrição...Entidade "ModeloVO".
 */
package br.com.yamaha.sistemagarantia.view;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "ModeloVO" no sistema.
 *  
 *  @author Gisele Panosso
 */
public class ModeloVO extends EntityObject {
	
	//----[ Atributos de classe e instância ]-----------------------------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L; 
    
    /** Armazena o modelo. */
    private String modelo;
    
    /** Armazena o nome do modelo. */
    private String nomeModelo;
    
    
    //	----[ Métodos Getter ]---------------------------------------------------

	/** Método getter para a propriedade modelo.
	 *
	 *  @return o valor atual de modelo.
	 */
	public String getModelo() {
		return modelo;
	}

	/** Método getter para a propriedade nomeModelo.
	 *
	 *  @return o valor atual de nomeModelo.
	 */
	public String getNomeModelo() {
		return nomeModelo;
	}
	   
    //	----[ Métodos Getter ]---------------------------------------------------
	

	/** Obtém o valor atual de modelo.
	 * 
	 *  @param modelo 
	 *    O novo valor para modelo.
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/** Obtém o valor atual de nomeModelo.
	 * 
	 *  @param nomeModelo 
	 *    O novo valor para nomeModelo.
	 */
	public void setNomeModelo(String nomeModelo) {
		this.nomeModelo = nomeModelo;
	}

     
}

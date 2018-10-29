/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......Analista.java
 *   .: Cria��o.....16 de julho, 11:28
 *   .: Autor.......Gisele Panosso	
 *   .: Descri��o...Entidade "Analista".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;

public class Analista extends EntityObject {

	//----[ Atributos de classe e inst�ncia ]-----------------------------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;    
    
    /** Armazena o registro do Analista. */
    private String registro;
    
    /** Armazena o nome do Analista. */
    private String nome;    

    //	----[ M�todos Getter ]---------------------------------------------------
    
	/** M�todo getter para o campo registro
	 * 
	 * @return String
	 *
	 */
	public String getRegistro() {
		return registro;
	}

	/** M�todo getter para o campo nome
	 * 
	 * @return String
	 *
	 */
	public String getNome() {
		return nome;
	}	
    
	//	----[ M�todos Setter ]---------------------------------------------------

	/** M�todo setter para o campo nome
	 *
	 * @param nome String
	 * 
	 */
	public void setRegistro(String registro) {
		this.registro = registro;
	}    

	/** M�todo setter para o campo nome
	 *
	 * @param nome String
	 * 
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}	
}

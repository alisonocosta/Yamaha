/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......Fornecedor.java
 *   .: Criação.....04 de outubro de 2007
 *   .: Autor.......Gisele Panosso
 *   .: Descrição...Entidade "Fornecedor".
 */
package br.com.yamaha.sistemagarantia.view;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, representando
 *  um objeto "FornecedorVO" no sistema.
 *  
 *  @author Gisele Panosso
 */
public class FornecedorVO extends EntityObject {
	
	//----[ Atributos de classe e instância ]-----------------------------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L; 
    
    /** Armazena o Código da Fornecedor. */
    private String codigo;
    
    /** Armazena a Razão Social do Fornecedor. */
    private String razaoSocial;
    

	/** Método getter para a propriedade codigo.
	 *
	 *  @return o valor atual de codigo.
	 */
	public String getCodigo() {
		return codigo;
	}

	/** Método getter para a propriedade razaoSocial.
	 *
	 *  @return o valor atual de razaoSocial.
	 */
	public String getRazaoSocial() {
		return razaoSocial;
	}

	/** Obtém o valur atual de codigo.
	 * 
	 *  @param codigo 
	 *    O novo valor para codigo.
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/** Obtém o valur atual de razaoSocial.
	 * 
	 *  @param razaoSocial 
	 *    O novo valor para razaoSocial.
	 */
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

}

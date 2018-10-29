/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioMalaDiretaVO.java
 *   .: Cria��o.....12 de julho, 09:44
 *   .: Autor.......Gisele Panosso
 *   .: Descri��o...Entidade "RelatorioMalaDiretaVO".
 */
package br.com.yamaha.sistemagarantia.view;

import br.com.resource.infra.model.EntityObject;

public class RelatorioMalaDiretaVO extends EntityObject {
	
	//----[ Atributos de classe e inst�ncia ]---------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;    
    
    /** Armazena o campo cliente do Relat�rio Mala Direta. */
    private String cliente;
    
    /** Armazena o campo endereco do Relat�rio Mala Direta. */
    private String endereco;
    
    /** Armazena o campo bairro do Relat�rio Mala Direta. */
    private String bairro;
    
    /** Armazena o campo cidade do Relat�rio Mala Direta. */
    private String cidade;
    
    /** Armazena o campo estado do Relat�rio Mala Direta. */
    private String estado;
    
    /** Armazena o campo cep do Relat�rio Mala Direta. */
    private String cep;
    
    /** Armazena o campo chassi do Relat�rio Mala Direta. */
    private String chassi;
    
    /** Armazena o campo concessionaria do Relat�rio Mala Direta. */
    private String concessionaria;
    

    //	----[ M�todos Getter ]---------------------------------------------------
    
	/** M�todo getter para a propriedade bairro.
	 *
	 *  @return o valor atual de bairro.
	 */
	public String getBairro() {
		return bairro;
	}

	/** M�todo getter para a propriedade cep.
	 *
	 *  @return o valor atual de cep.
	 */
	public String getCep() {
		return cep;
	}

	/** M�todo getter para a propriedade chassi.
	 *
	 *  @return o valor atual de chassi.
	 */
	public String getChassi() {
		return chassi;
	}

	/** M�todo getter para a propriedade cidade.
	 *
	 *  @return o valor atual de cidade.
	 */
	public String getCidade() {
		return cidade;
	}

	/** M�todo getter para a propriedade cliente.
	 *
	 *  @return o valor atual de cliente.
	 */
	public String getCliente() {
		return cliente;
	}

	/** M�todo getter para a propriedade concessionaria.
	 *
	 *  @return o valor atual de concessionaria.
	 */
	public String getConcessionaria() {
		return concessionaria;
	}

	/** M�todo getter para a propriedade endereco.
	 *
	 *  @return o valor atual de endereco.
	 */
	public String getEndereco() {
		return endereco;
	}

	/** M�todo getter para a propriedade estado.
	 *
	 *  @return o valor atual de estado.
	 */
	public String getEstado() {
		return estado;
	}
	
	//	----[ M�todos Setter ]---------------------------------------------------
		
	/** Obt�m o valur atual de bairro.
	 * 
	 *  @param bairro 
	 *    O novo valor para bairro.
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	/** Obt�m o valur atual de cep.
	 * 
	 *  @param cep 
	 *    O novo valor para cep.
	 */
	public void setCep(String cep) {
		this.cep = cep;
	}

	/** Obt�m o valur atual de chassi.
	 * 
	 *  @param chassi 
	 *    O novo valor para chassi.
	 */
	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	/** Obt�m o valur atual de cidade.
	 * 
	 *  @param cidade 
	 *    O novo valor para cidade.
	 */
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	/** Obt�m o valur atual de cliente.
	 * 
	 *  @param cliente 
	 *    O novo valor para cliente.
	 */
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	/** Obt�m o valur atual de concessionaria.
	 * 
	 *  @param concessionaria 
	 *    O novo valor para concessionaria.
	 */
	public void setConcessionaria(String concessionaria) {
		this.concessionaria = concessionaria;
	}

	/** Obt�m o valur atual de endereco.
	 * 
	 *  @param endereco 
	 *    O novo valor para endereco.
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/** Obt�m o valur atual de estado.
	 * 
	 *  @param estado 
	 *    O novo valor para estado.
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
    
}

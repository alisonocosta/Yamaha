/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......Empresa.java
 *   .: Cria��o.....20 de agosto de 2007
 *   .: Autor.......Gisele Panosso
 *   .: Descri��o...Entidade "Empresa".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, representando
 *  um objeto "Empresa" no sistema.
 *  
 *  @author Gisele Panosso
 */
public class Empresa extends EntityObject {
	
	/** Constante para o id da empresa YMA */
	public static final Long EMPRESA_YMA = new Long(89);
	
	/** Constante para o id da empresa YMB */
	public static final Long EMPRESA_YMB = new Long(91);
	
	//----[ Atributos de classe e inst�ncia ]-----------------------------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L; 
    
    /** Armazena o C�digo da Empresa. */
    private String orgCode;
    
    /** Armazena o C�digo da Montadora. */
    private String codMontadora;
    
    /** Armazena o organizationId, da Empresa. */
    private Long organizationId;

    /** Armazena o CNPJ, n�o formatado, da Empresa. */
    private Long cnpj;
    
    /** Armazena o Nome da Empresa. */
    private String nomeEmpresa;
    
    /** Armazena a Inscri��o Estadual da Empresa. */
    private String inscrEstadual;

	/** Endere�o no qual a Concession�ria se localiza. */
    private String endereco;
    
    /** Bairro no qual a Empresa se localiza. */
    private String bairro;
    
    /** Cidade no qual a Empresa se localiza. */
    private String cidade;
    
    /** Estado no qual a Empresa se localiza. */
    private String estado;
    
    /** CEP n�o formatado da Empresa. */
    private Long cep;

    
    
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
	public Long getCep() {
		return cep;
	}

	/** M�todo getter para a propriedade cidade.
	 *
	 *  @return o valor atual de cidade.
	 */
	public String getCidade() {
		return cidade;
	}

	/** M�todo getter para a propriedade cnpj.
	 *
	 *  @return o valor atual de cnpj.
	 */
	public Long getCnpj() {
		return cnpj;
	}

	/** M�todo getter para a propriedade codMontadora.
	 *
	 *  @return o valor atual de codMontadora.
	 */
	public String getCodMontadora() {
		return codMontadora;
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

	/** M�todo getter para a propriedade inscrEstadual.
	 *
	 *  @return o valor atual de inscrEstadual.
	 */
	public String getInscrEstadual() {
		return inscrEstadual;
	}

	/** M�todo getter para a propriedade nomeEmpresa.
	 *
	 *  @return o valor atual de nomeEmpresa.
	 */
	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	/** M�todo getter para a propriedade orgCode.
	 *
	 *  @return o valor atual de orgCode.
	 */
	public String getOrgCode() {
		return orgCode;
	}
	
	/** M�todo getter para a propriedade organizationId
	 * 
	 *  @return Long
	 *
	 */
	public Long getOrganizationId() {
		return organizationId;
	}

    
    //	----[ M�todos Setter ]---------------------------------------------------
    
	/** Obt�m o valor atual de bairro.
	 * 
	 *  @param bairro 
	 *    O novo valor para bairro.
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	/** Obt�m o valor atual de cep.
	 * 
	 *  @param cep 
	 *    O novo valor para cep.
	 */
	public void setCep(Long cep) {
		this.cep = cep;
	}

	/** Obt�m o valor atual de cidade.
	 * 
	 *  @param cidade 
	 *    O novo valor para cidade.
	 */
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	/** Obt�m o valor atual de cnpj.
	 * 
	 *  @param cnpj 
	 *    O novo valor para cnpj.
	 */
	public void setCnpj(Long cnpj) {
		this.cnpj = cnpj;
	}

	/** Obt�m o valor atual de codMontadora.
	 * 
	 *  @param codMontadora 
	 *    O novo valor para codMontadora.
	 */
	public void setCodMontadora(String codMontadora) {
		this.codMontadora = codMontadora;
	}

	/** Obt�m o valor atual de endereco.
	 * 
	 *  @param endereco 
	 *    O novo valor para endereco.
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/** Obt�m o valor atual de estado.
	 * 
	 *  @param estado 
	 *    O novo valor para estado.
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/** Obt�m o valor atual de inscrEstadual.
	 * 
	 *  @param inscrEstadual 
	 *    O novo valor para inscrEstadual.
	 */
	public void setInscrEstadual(String inscrEstadual) {
		this.inscrEstadual = inscrEstadual;
	}

	/** Obt�m o valor atual de nomeEmpresa.
	 * 
	 *  @param nomeEmpresa 
	 *    O novo valor para nomeEmpresa.
	 */
	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	/** Obt�m o valor atual de orgCode.
	 * 
	 *  @param orgCode 
	 *    O novo valor para orgCode.
	 */
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	/** M�todo setter para a propriedade organizationId
	 *
	 * @param organizationId 
	 *           <code>Long</code> a ser designado para organizationId.
	 * 
	 */
	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
    
    

	
}

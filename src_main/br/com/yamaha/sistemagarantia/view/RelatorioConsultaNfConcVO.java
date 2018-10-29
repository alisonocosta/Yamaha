/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioConsultaNFConcVO.java
 *   .: Cria��o.....31 de agosto de 2007
 *   .: Autor.......Gisele Panosso
 *   .: Descri��o...Entidade "RelatorioConsultaNFConcVO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.util.ArrayList;
import java.util.List;

import br.com.resource.infra.model.EntityObject;

public class RelatorioConsultaNfConcVO extends EntityObject {

	//----[ Atributos de classe e inst�ncia ]---------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;    
    
    /** Armazena o campo concessionaria do Relat�rio Consulta de Notas Fiscais. */
    private Long concessionariaId;
    
    /** Armazena o campo concessionaria do Relat�rio Consulta de Notas Fiscais. */
    private String concessionaria;
    
    /** Armazena o campo endereco do Relat�rio Consulta de Notas Fiscais. */
    private String endereco;
    
    /** Armazena o campo cep do Relat�rio Consulta de Notas Fiscais. */
    private String cep;
    
    /** Armazena o campo cidade do Relat�rio Consulta de Notas Fiscais. */
    private String cidade;
    
    /** Armazena o campo estado do Relat�rio Consulta de Notas Fiscais. */
    private String estado;

    /** Armazena o campo cnpj do Relat�rio Consulta de Notas Fiscais. */
    private Long cnpj;

    /** Armazena o campo listLotes do Relat�rio Consulta de Notas Fiscais. */
    private List listLotes;
    
    //	----[ M�todos Construtor ]---------------------------------------------------  

	public RelatorioConsultaNfConcVO() {
		super();
		listLotes = new ArrayList();
	}

    //	----[ M�todos Add ]---------------------------------------------------  
	
	/** Adiciona uma lista de lotes � listagem de Concessionarias.
     * 
     *  @param listLotes
     *      listLotes a ser adicionado.
     *  
     */
    public void addListLotes(RelatorioConsultaNfLoteVO listLotes) {
    	
        this.listLotes.add( listLotes );
        
    }  	
    
    //	----[ M�todos Getter ]---------------------------------------------------  

	/** M�todo getter para a propriedade cep.
	 *
	 *  @return o valor atual de cep.
	 */
	public String getCep() {
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

	/** M�todo getter para a propriedade listLotes.
	 *
	 *  @return o valor atual de listLotes.
	 */
	public List getListLotes() {
		return listLotes;
	}	

	/** M�todo getter para a propriedade concessionariaId.
	 *
	 *  @return o valor atual de concessionariaId.
	 */
	public Long getConcessionariaId() {
		return concessionariaId;
	}

    //	----[ M�todos Setter ]---------------------------------------------------  
	
	/** Obt�m o valor atual de cep.
	 * 
	 *  @param cep 
	 *    O novo valor para cep.
	 */
	public void setCep(String cep) {
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

	/** Obt�m o valor atual de concessionaria.
	 * 
	 *  @param concessionaria 
	 *    O novo valor para concessionaria.
	 */
	public void setConcessionaria(String concessionaria) {
		this.concessionaria = concessionaria;
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

	/** Obt�m o valor atual de listLotes.
	 * 
	 *  @param listLotes 
	 *    O novo valor para listLotes.
	 */
	public void setListLotes(List listLotes) {
		this.listLotes = listLotes;
	}

	/** Obt�m o valor atual de concessionariaId.
	 * 
	 *  @param concessionariaId 
	 *    O novo valor para concessionariaId.
	 */
	public void setConcessionariaId(Long concessionariaId) {
		this.concessionariaId = concessionariaId;
	}
    
}

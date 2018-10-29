/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioConsultaNFConcVO.java
 *   .: Criação.....31 de agosto de 2007
 *   .: Autor.......Gisele Panosso
 *   .: Descrição...Entidade "RelatorioConsultaNFConcVO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.util.ArrayList;
import java.util.List;

import br.com.resource.infra.model.EntityObject;

public class RelatorioConsultaNfConcVO extends EntityObject {

	//----[ Atributos de classe e instância ]---------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;    
    
    /** Armazena o campo concessionaria do Relatório Consulta de Notas Fiscais. */
    private Long concessionariaId;
    
    /** Armazena o campo concessionaria do Relatório Consulta de Notas Fiscais. */
    private String concessionaria;
    
    /** Armazena o campo endereco do Relatório Consulta de Notas Fiscais. */
    private String endereco;
    
    /** Armazena o campo cep do Relatório Consulta de Notas Fiscais. */
    private String cep;
    
    /** Armazena o campo cidade do Relatório Consulta de Notas Fiscais. */
    private String cidade;
    
    /** Armazena o campo estado do Relatório Consulta de Notas Fiscais. */
    private String estado;

    /** Armazena o campo cnpj do Relatório Consulta de Notas Fiscais. */
    private Long cnpj;

    /** Armazena o campo listLotes do Relatório Consulta de Notas Fiscais. */
    private List listLotes;
    
    //	----[ Métodos Construtor ]---------------------------------------------------  

	public RelatorioConsultaNfConcVO() {
		super();
		listLotes = new ArrayList();
	}

    //	----[ Métodos Add ]---------------------------------------------------  
	
	/** Adiciona uma lista de lotes à listagem de Concessionarias.
     * 
     *  @param listLotes
     *      listLotes a ser adicionado.
     *  
     */
    public void addListLotes(RelatorioConsultaNfLoteVO listLotes) {
    	
        this.listLotes.add( listLotes );
        
    }  	
    
    //	----[ Métodos Getter ]---------------------------------------------------  

	/** Método getter para a propriedade cep.
	 *
	 *  @return o valor atual de cep.
	 */
	public String getCep() {
		return cep;
	}

	/** Método getter para a propriedade cidade.
	 *
	 *  @return o valor atual de cidade.
	 */
	public String getCidade() {
		return cidade;
	}

	/** Método getter para a propriedade cnpj.
	 *
	 *  @return o valor atual de cnpj.
	 */
	public Long getCnpj() {
		return cnpj;
	}

	/** Método getter para a propriedade concessionaria.
	 *
	 *  @return o valor atual de concessionaria.
	 */
	public String getConcessionaria() {
		return concessionaria;
	}

	/** Método getter para a propriedade endereco.
	 *
	 *  @return o valor atual de endereco.
	 */
	public String getEndereco() {
		return endereco;
	}

	/** Método getter para a propriedade estado.
	 *
	 *  @return o valor atual de estado.
	 */
	public String getEstado() {
		return estado;
	}

	/** Método getter para a propriedade listLotes.
	 *
	 *  @return o valor atual de listLotes.
	 */
	public List getListLotes() {
		return listLotes;
	}	

	/** Método getter para a propriedade concessionariaId.
	 *
	 *  @return o valor atual de concessionariaId.
	 */
	public Long getConcessionariaId() {
		return concessionariaId;
	}

    //	----[ Métodos Setter ]---------------------------------------------------  
	
	/** Obtém o valor atual de cep.
	 * 
	 *  @param cep 
	 *    O novo valor para cep.
	 */
	public void setCep(String cep) {
		this.cep = cep;
	}

	/** Obtém o valor atual de cidade.
	 * 
	 *  @param cidade 
	 *    O novo valor para cidade.
	 */
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	/** Obtém o valor atual de cnpj.
	 * 
	 *  @param cnpj 
	 *    O novo valor para cnpj.
	 */
	public void setCnpj(Long cnpj) {
		this.cnpj = cnpj;
	}

	/** Obtém o valor atual de concessionaria.
	 * 
	 *  @param concessionaria 
	 *    O novo valor para concessionaria.
	 */
	public void setConcessionaria(String concessionaria) {
		this.concessionaria = concessionaria;
	}

	/** Obtém o valor atual de endereco.
	 * 
	 *  @param endereco 
	 *    O novo valor para endereco.
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/** Obtém o valor atual de estado.
	 * 
	 *  @param estado 
	 *    O novo valor para estado.
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/** Obtém o valor atual de listLotes.
	 * 
	 *  @param listLotes 
	 *    O novo valor para listLotes.
	 */
	public void setListLotes(List listLotes) {
		this.listLotes = listLotes;
	}

	/** Obtém o valor atual de concessionariaId.
	 * 
	 *  @param concessionariaId 
	 *    O novo valor para concessionariaId.
	 */
	public void setConcessionariaId(Long concessionariaId) {
		this.concessionariaId = concessionariaId;
	}
    
}

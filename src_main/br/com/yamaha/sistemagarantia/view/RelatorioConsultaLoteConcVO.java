/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioConsultaLoteConcVO.java
 *   .: Cria��o.....11 de agosto, 09:42
 *   .: Autor.......Gisele Panosso
 *   .: Descri��o...Entidade "RelatorioConsultaLoteConcVO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.resource.infra.model.EntityObject;

public class RelatorioConsultaLoteConcVO extends EntityObject {

	//----[ Atributos de classe e inst�ncia ]---------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;    
    
    /** Armazena o campo linha do Relat�rio Consulta de Lotes. */
    private String linha;
    
    /** Armazena o campo lote do Relat�rio Consulta de Lotes. */
    private Serializable loteId;
    
    /** Armazena o campo tipoLote do Relat�rio Consulta de Lotes. */
    private String tipoLote;
    
    /** Armazena o campo concessionariaId do Relat�rio Consulta de Lotes. */
    private Long concessionariaId;
    
    /** Armazena o campo concessionaria do Relat�rio Consulta de Lotes. */
    private String concessionaria;
    
    /** Armazena o campo endereco do Relat�rio Consulta de Lotes. */
    private String endereco;
    
    /** Armazena o campo cep do Relat�rio Consulta de Lotes. */
    private Serializable cep;
    
    /** Armazena o campo cidade do Relat�rio Consulta de Lotes. */
    private String cidade;
    
    /** Armazena o campo estado do Relat�rio Consulta de Lotes. */
    private String estado;

    /** Armazena o campo cnpj do Relat�rio Consulta de Lotes. */
    private Serializable cnpj;    
    
    /** Armazena o campo dtAbertura do Relat�rio Consulta de Lotes. */
    private Date dtAbertura;
        
    /** Armazena o campo dtLiberacao do Relat�rio Consulta de Lotes. */
    private Date dtLiberacao;
    
    /** Armazena o campo dtFechamento do Relat�rio Consulta de Lotes. */
    private Date dtFechamento;    
  
    /** Armazena o campo dtStatus do Relat�rio Consulta de Lotes. */
    private Date dtStatus;  
    
    /** Armazena o campo descricaoStatus do Relat�rio Consulta de Lotes. */
    private String descStatus;
    
    /** Armazena o campo descStatusAnterior do Relat�rio Consulta de Lotes. */
    private String descStatusAnterior;    

    /** Armazena o campo listLote (lista dos lotes de uma concession�ria) do Relat�rio Consulta de Lotes. */
    private List listLote;  
    
 
    //	----[ M�todo Add ]---------------------------------------------------  

    public RelatorioConsultaLoteConcVO() {
		super();
		listLote = new ArrayList();
	}

	/** Adiciona uma lista de Lote � listagem da Concession�ria.
     * 
     *  @param listLotes
     *      listLotes a ser adicionado.
     *  
     */
    public void addListLotes(RelatorioConsultaLoteVO listLote) {
    	
        this.listLote.add( listLote );
        
    }
 
    
    //	----[ M�todos Getter ]---------------------------------------------------  

	/** M�todo getter para a propriedade cep.
	 *
	 *  @return o valor atual de cep.
	 */
	public Serializable getCep() {
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
	public Serializable getCnpj() {
		return cnpj;
	}

	/** M�todo getter para a propriedade concessionaria.
	 *
	 *  @return o valor atual de concessionaria.
	 */
	public String getConcessionaria() {
		return concessionaria;
	}

	/** M�todo getter para a propriedade descStatus.
	 *
	 *  @return o valor atual de descStatus.
	 */
	public String getDescStatus() {
		return descStatus;
	}

	/** M�todo getter para a propriedade descStatusAnterior.
	 *
	 *  @return o valor atual de descStatusAnterior.
	 */
	public String getDescStatusAnterior() {
		return descStatusAnterior;
	}

	/** M�todo getter para a propriedade dtAbertura.
	 *
	 *  @return o valor atual de dtAbertura.
	 */
	public Date getDtAbertura() {
		return dtAbertura;
	}

	/** M�todo getter para a propriedade dtFechamento.
	 *
	 *  @return o valor atual de dtFechamento.
	 */
	public Date getDtFechamento() {
		return dtFechamento;
	}

	/** M�todo getter para a propriedade dtLiberacao.
	 *
	 *  @return o valor atual de dtLiberacao.
	 */
	public Date getDtLiberacao() {
		return dtLiberacao;
	}

	/** M�todo getter para a propriedade dtStatus.
	 *
	 *  @return o valor atual de dtStatus.
	 */
	public Date getDtStatus() {
		return dtStatus;
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

	/** M�todo getter para a propriedade linha.
	 *
	 *  @return o valor atual de linha.
	 */
	public String getLinha() {
		return linha;
	}

	/** M�todo getter para a propriedade listStatus.
	 *
	 *  @return o valor atual de listStatus.
	 */
	public List getListLote() {
		return listLote;
	}

	/** M�todo getter para a propriedade loteId.
	 *
	 *  @return o valor atual de loteId.
	 */
	public Serializable getLoteId() {
		return loteId;
	}

	/** M�todo getter para a propriedade tipoLote.
	 *
	 *  @return o valor atual de tipoLote.
	 */
	public String getTipoLote() {
		return tipoLote;
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
	public void setCep(Serializable cep) {
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
	public void setCnpj(Serializable cnpj) {
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

	/** Obt�m o valor atual de descStatus.
	 * 
	 *  @param descStatus 
	 *    O novo valor para descStatus.
	 */
	public void setDescStatus(String descStatus) {
		this.descStatus = descStatus;
	}

	/** Obt�m o valor atual de descStatusAnterior.
	 * 
	 *  @param descStatusAnterior 
	 *    O novo valor para descStatusAnterior.
	 */
	public void setDescStatusAnterior(String descStatusAnterior) {
		this.descStatusAnterior = descStatusAnterior;
	}

	/** Obt�m o valor atual de dtAbertura.
	 * 
	 *  @param dtAbertura 
	 *    O novo valor para dtAbertura.
	 */
	public void setDtAbertura(Date dtAbertura) {
		this.dtAbertura = dtAbertura;
	}

	/** Obt�m o valor atual de dtFechamento.
	 * 
	 *  @param dtFechamento 
	 *    O novo valor para dtFechamento.
	 */
	public void setDtFechamento(Date dtFechamento) {
		this.dtFechamento = dtFechamento;
	}

	/** Obt�m o valor atual de dtLiberacao.
	 * 
	 *  @param dtLiberacao 
	 *    O novo valor para dtLiberacao.
	 */
	public void setDtLiberacao(Date dtLiberacao) {
		this.dtLiberacao = dtLiberacao;
	}

	/** Obt�m o valor atual de dtStatus.
	 * 
	 *  @param dtStatus 
	 *    O novo valor para dtStatus.
	 */
	public void setDtStatus(Date dtStatus) {
		this.dtStatus = dtStatus;
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

	/** Obt�m o valor atual de linha.
	 * 
	 *  @param linha 
	 *    O novo valor para linha.
	 */
	public void setLinha(String linha) {
		this.linha = linha;
	}

	/** Obt�m o valor atual de listStatus.
	 * 
	 *  @param listStatus 
	 *    O novo valor para listStatus.
	 */
	public void setListLote(List listLote) {
		this.listLote = listLote;
	}

	/** Obt�m o valor atual de loteId.
	 * 
	 *  @param loteId 
	 *    O novo valor para loteId.
	 */
	public void setLoteId(Long loteId) {
		this.loteId = loteId;
	}

	/** Obt�m o valor atual de tipoLote.
	 * 
	 *  @param tipoLote 
	 *    O novo valor para tipoLote.
	 */
	public void setTipoLote(String tipoLote) {
		this.tipoLote = tipoLote;
	}

	/** Obt�m o valur atual de concessionariaId.
	 * 
	 *  @param concessionariaId 
	 *    O novo valor para concessionariaId.
	 */
	public void setConcessionariaId(Long concessionariaId) {
		this.concessionariaId = concessionariaId;
	} 
    
    
    
}    
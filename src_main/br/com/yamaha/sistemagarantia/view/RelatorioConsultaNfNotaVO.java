/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioConsultaNfNotaVO.java
 *   .: Cria��o.....31 de agosto de 2007
 *   .: Autor.......Gisele Panosso
 *   .: Descri��o...Entidade "RelatorioConsultaNfNotaVO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import br.com.resource.infra.model.EntityObject;
import br.com.resource.infra.utils.DateUtils;
import br.com.resource.infra.utils.NumberUtils;

public class RelatorioConsultaNfNotaVO extends EntityObject {

	//----[ Atributos de classe e inst�ncia ]---------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;    
    
    /** Armazena o campo loteId do Relat�rio Consulta de Notas Fiscais. */
    private Serializable loteId;
    
    /** Armazena o campo descri��o da linha do produto do Relat�rio Consulta de Notas Fiscais. */
    private String descLinha;
    
    /** Armazena o campo data de abertura do lote do Relat�rio Consulta de Notas Fiscais. */
    private Date dataAberturaLote;
    
    /** Armazena o campo data de libera��o do lote do Relat�rio Consulta de Notas Fiscais. */
    private Date dataLiberacaoLote;
    
    /** Armazena o campo data de fechamento do lote do Relat�rio Consulta de Notas Fiscais. */
    private Date dataFechamentoLote;
    
    /** Armazena o campo tipo do lote do Relat�rio Consulta de Notas Fiscais. */
    private String tipoLote;
    
    /** Armazena o campo notaFiscal do Relat�rio Consulta de Notas Fiscais. */
    private Serializable notaFiscal;
    
    /** Armazena o campo serie do Relat�rio Consulta de Notas Fiscais. */
    private String serie;
    
    /** Armazena o campo dtNotaFiscal do Relat�rio Consulta de Notas Fiscais. */
    private Date dtNotaFiscal;
    
    /** Armazena o campo valor do Relat�rio Consulta de Notas Fiscais. */
    private BigDecimal valor;
    
    /** Armazena o campo tipoNF do Relat�rio Consulta de Notas Fiscais. */
    private String tipoNF;
    
    /** Armazena o campo Razao Social da Concessionaria do Relat�rio Consulta de Notas Fiscais. */
    private String concessionaria;
    
    /** Armazena o campo endere�o da Concessionaria do Relat�rio Consulta de Notas Fiscais. */
    private String endereco;
    
    /** Armazena o campo cidade da Concessionaria do Relat�rio Consulta de Notas Fiscais. */
    private String cidade;

    /** Armazena o campo cep da Concessionaria do Relat�rio Consulta de Notas Fiscais. */
    private Serializable cep;
    
    /** Armazena o campo estado da Concessionaria do Relat�rio Consulta de Notas Fiscais. */
    private String estado;

    /** Armazena o campo Cnpj da Concessionaria do Relat�rio Consulta de Notas Fiscais. */
    private Serializable cnpj;
    
    /** Armazena o campo Empresa da Concessionaria do Relat�rio Consulta de Notas Fiscais. */
    private String empresa;

    //	----[ M�todos Getter ]---------------------------------------------------  
    
	/** M�todo getter para a propriedade dtNotaFiscal.
	 *
	 *  @return o valor atual de dtNotaFiscal.
	 */
	public Date getDtNotaFiscal() {
		return dtNotaFiscal;
	}
	
	/** M�todo getter para a propriedade dtNotaFiscal.
	 *
	 *  @return o valor atual de dtNotaFiscal.
	 */
	public String getStrDtNotaFiscal() {
		return DateUtils.applyMask(dtNotaFiscal);
	}


	/** M�todo getter para a propriedade notaFiscal.
	 *
	 *  @return o valor atual de notaFiscal.
	 */
	public Serializable getNotaFiscal() {
		return notaFiscal;
	}

	/** M�todo getter para a propriedade serie.
	 *
	 *  @return o valor atual de serie.
	 */
	public String getSerie() {
		return serie;
	}

	/** M�todo getter para a propriedade tipoNF.
	 *
	 *  @return o valor atual de tipoNF.
	 */
	public String getTipoNF() {
		return tipoNF;
	}

	/** M�todo getter para a propriedade valor.
	 *
	 *  @return o valor atual de valor.
	 */
	public BigDecimal getValor() {
		return valor;
	}
	
	/** M�todo getter para a propriedade valor.
	 *
	 *  @return o valor atual de valor.
	 */
	public String getStrValor() {
		return NumberUtils.formatNumberCurrencyMil(valor.doubleValue());
	}
	
	/** M�todo getter para a propriedade cidade
	 *
	 *  @return String de cidade
	 */
	public String getCidade() {
		return cidade;
	}

	/** M�todo getter para a propriedade cnpj
	 *
	 *  @return Serializable de cnpj
	 */
	public Serializable getCnpj() {
		return cnpj;
	}
	
	/** M�todo getter para a propriedade cnpj
	 *
	 *  @return String de cnpj
	 */
	public String getStrCnpj() {
		return cnpj.toString();
	}

	/** M�todo getter para a propriedade concessionaria
	 *
	 *  @return String de concessionaria
	 */
	public String getConcessionaria() {
		return concessionaria;
	}

	/** M�todo getter para a propriedade dataAberturaLote
	 *
	 *  @return Date de dataAberturaLote
	 */
	public Date getDataAberturaLote() {
		return dataAberturaLote;
	}
	
	/** M�todo getter para a propriedade dataAberturaLote
	 *
	 *  @return String de dataAberturaLote
	 */
	public String getStrDataAberturaLote() {
		return DateUtils.applyMask(dataAberturaLote);
	}

	/** M�todo getter para a propriedade dataFechamentoLote
	 *
	 *  @return Date de dataFechamentoLote
	 */
	public Date getDataFechamentoLote() {
		return dataFechamentoLote;
	}
	
	/** M�todo getter para a propriedade dataFechamentoLote
	 *
	 *  @return String de dataFechamentoLote
	 */
	public String getStrDataFechamentoLote() {
		return DateUtils.applyMask(dataFechamentoLote);
	}

	/** M�todo getter para a propriedade dataLiberacaoLote
	 *
	 *  @return Date de dataLiberacaoLote
	 */
	public Date getDataLiberacaoLote() {
		return dataLiberacaoLote;
	}
	
	/** M�todo getter para a propriedade dataLiberacaoLote
	 *
	 *  @return String de dataLiberacaoLote
	 */
	public String getStrDataLiberacaoLote() {
		return DateUtils.applyMask(dataLiberacaoLote);
	}


	/** M�todo getter para a propriedade empresa
	 *
	 *  @return String de empresa
	 */
	public String getEmpresa() {
		return empresa;
	}

	/** M�todo getter para a propriedade descLinha
	 *
	 *  @return String de descLinha
	 */
	public String getDescLinha() {
		return descLinha;
	}

	/** M�todo getter para a propriedade endereco
	 *
	 *  @return String de endereco
	 */
	public String getEndereco() {
		return endereco;
	}

	/** M�todo getter para a propriedade loteId
	 *
	 *  @return Serializable de loteId
	 */
	public Serializable getLoteId() {
		return loteId;
	}

	/** M�todo getter para a propriedade tipoLote
	 *
	 *  @return String de tipoLote
	 */
	public String getTipoLote() {
		return tipoLote;
	}

    //	----[ M�todos Setter ]---------------------------------------------------  

	/** Obt�m o valor atual de dtNotaFiscal.
	 * 
	 *  @param dtNotaFiscal 
	 *    O novo valor para dtNotaFiscal.
	 */
	public void setDtNotaFiscal(Date dtNotaFiscal) {
		this.dtNotaFiscal = dtNotaFiscal;
	}

	/** Obt�m o valor atual de notaFiscal.
	 * 
	 *  @param notaFiscal 
	 *    O novo valor para notaFiscal.
	 */
	public void setNotaFiscal(Serializable notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	/** Obt�m o valor atual de serie.
	 * 
	 *  @param serie 
	 *    O novo valor para serie.
	 */
	public void setSerie(String serie) {
		this.serie = serie;
	}

	/** Obt�m o valor atual de tipoNF.
	 * 
	 *  @param tipoNF 
	 *    O novo valor para tipoNF.
	 */
	public void setTipoNF(String tipoNF) {
		this.tipoNF = tipoNF;
	}

	/** Obt�m o valor atual de valor.
	 * 
	 *  @param valor 
	 *    O novo valor para valor.
	 */
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	

	/** M�todo setter para a propriedade cidade
	 *
	 * @param cidade String
	 */
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	/** M�todo setter para a propriedade cnpj
	 *
	 * @param cnpj Serializable
	 */
	public void setCnpj(Serializable cnpj) {
		this.cnpj = cnpj;
	}

	/** M�todo setter para a propriedade concessionaria
	 *
	 * @param concessionaria String
	 */
	public void setConcessionaria(String concessionaria) {
		this.concessionaria = concessionaria;
	}

	/** M�todo setter para a propriedade dataAberturaLote
	 *
	 * @param dataAberturaLote Date
	 */
	public void setDataAberturaLote(Date dataAberturaLote) {
		this.dataAberturaLote = dataAberturaLote;
	}

	/** M�todo setter para a propriedade dataFechamentoLote
	 *
	 * @param dataFechamentoLote Date
	 */
	public void setDataFechamentoLote(Date dataFechamentoLote) {
		this.dataFechamentoLote = dataFechamentoLote;
	}

	/** M�todo setter para a propriedade dataLiberacaoLote
	 *
	 * @param dataLiberacaoLote Date
	 */
	public void setDataLiberacaoLote(Date dataLiberacaoLote) {
		this.dataLiberacaoLote = dataLiberacaoLote;
	}

	/** M�todo setter para a propriedade descLinha
	 *
	 * @param descLinha String
	 */
	public void setDescLinha(String descLinha) {
		this.descLinha = descLinha;
	}

	/** M�todo setter para a propriedade empresa
	 *
	 * @param empresa String
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/** M�todo setter para a propriedade endereco
	 *
	 * @param endereco String
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	/** M�todo setter para a propriedade loteId
	 *
	 * @param loteId Serializable
	 */
	public void setLoteId(Serializable loteId) {
		this.loteId = loteId;
	}

	/** M�todo setter para a propriedade tipoLote
	 *
	 * @param tipoLote String
	 */
	public void setTipoLote(String tipoLote) {
		this.tipoLote = tipoLote;
	}

	/** M�todo getter para a propriedade cep
	 *
	 *  @return String de Serializable
	 */
	public Serializable getCep() {
		return cep;
	}
	
	/** M�todo getter para a propriedade cep
	 *
	 *  @return String de cep
	 */
	public String getStrCep() {
		return cep.toString();
	}

	/** M�todo setter para a propriedade cep
	 *
	 * @param cep Serializable
	 */
	public void setCep(Serializable cep) {
		this.cep = cep;
	}

	/** M�todo getter para a propriedade estado
	 *
	 *  @return String de estado
	 */
	public String getEstado() {
		return estado;
	}

	/** M�todo setter para a propriedade estado
	 *
	 * @param estado String
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
}

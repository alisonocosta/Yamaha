/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioConsultaNfNotaVO.java
 *   .: Criação.....31 de agosto de 2007
 *   .: Autor.......Gisele Panosso
 *   .: Descrição...Entidade "RelatorioConsultaNfNotaVO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import br.com.resource.infra.model.EntityObject;
import br.com.resource.infra.utils.DateUtils;
import br.com.resource.infra.utils.NumberUtils;

public class RelatorioConsultaNfNotaVO extends EntityObject {

	//----[ Atributos de classe e instância ]---------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;    
    
    /** Armazena o campo loteId do Relatório Consulta de Notas Fiscais. */
    private Serializable loteId;
    
    /** Armazena o campo descrição da linha do produto do Relatório Consulta de Notas Fiscais. */
    private String descLinha;
    
    /** Armazena o campo data de abertura do lote do Relatório Consulta de Notas Fiscais. */
    private Date dataAberturaLote;
    
    /** Armazena o campo data de liberação do lote do Relatório Consulta de Notas Fiscais. */
    private Date dataLiberacaoLote;
    
    /** Armazena o campo data de fechamento do lote do Relatório Consulta de Notas Fiscais. */
    private Date dataFechamentoLote;
    
    /** Armazena o campo tipo do lote do Relatório Consulta de Notas Fiscais. */
    private String tipoLote;
    
    /** Armazena o campo notaFiscal do Relatório Consulta de Notas Fiscais. */
    private Serializable notaFiscal;
    
    /** Armazena o campo serie do Relatório Consulta de Notas Fiscais. */
    private String serie;
    
    /** Armazena o campo dtNotaFiscal do Relatório Consulta de Notas Fiscais. */
    private Date dtNotaFiscal;
    
    /** Armazena o campo valor do Relatório Consulta de Notas Fiscais. */
    private BigDecimal valor;
    
    /** Armazena o campo tipoNF do Relatório Consulta de Notas Fiscais. */
    private String tipoNF;
    
    /** Armazena o campo Razao Social da Concessionaria do Relatório Consulta de Notas Fiscais. */
    private String concessionaria;
    
    /** Armazena o campo endereço da Concessionaria do Relatório Consulta de Notas Fiscais. */
    private String endereco;
    
    /** Armazena o campo cidade da Concessionaria do Relatório Consulta de Notas Fiscais. */
    private String cidade;

    /** Armazena o campo cep da Concessionaria do Relatório Consulta de Notas Fiscais. */
    private Serializable cep;
    
    /** Armazena o campo estado da Concessionaria do Relatório Consulta de Notas Fiscais. */
    private String estado;

    /** Armazena o campo Cnpj da Concessionaria do Relatório Consulta de Notas Fiscais. */
    private Serializable cnpj;
    
    /** Armazena o campo Empresa da Concessionaria do Relatório Consulta de Notas Fiscais. */
    private String empresa;

    //	----[ Métodos Getter ]---------------------------------------------------  
    
	/** Método getter para a propriedade dtNotaFiscal.
	 *
	 *  @return o valor atual de dtNotaFiscal.
	 */
	public Date getDtNotaFiscal() {
		return dtNotaFiscal;
	}
	
	/** Método getter para a propriedade dtNotaFiscal.
	 *
	 *  @return o valor atual de dtNotaFiscal.
	 */
	public String getStrDtNotaFiscal() {
		return DateUtils.applyMask(dtNotaFiscal);
	}


	/** Método getter para a propriedade notaFiscal.
	 *
	 *  @return o valor atual de notaFiscal.
	 */
	public Serializable getNotaFiscal() {
		return notaFiscal;
	}

	/** Método getter para a propriedade serie.
	 *
	 *  @return o valor atual de serie.
	 */
	public String getSerie() {
		return serie;
	}

	/** Método getter para a propriedade tipoNF.
	 *
	 *  @return o valor atual de tipoNF.
	 */
	public String getTipoNF() {
		return tipoNF;
	}

	/** Método getter para a propriedade valor.
	 *
	 *  @return o valor atual de valor.
	 */
	public BigDecimal getValor() {
		return valor;
	}
	
	/** Método getter para a propriedade valor.
	 *
	 *  @return o valor atual de valor.
	 */
	public String getStrValor() {
		return NumberUtils.formatNumberCurrencyMil(valor.doubleValue());
	}
	
	/** Método getter para a propriedade cidade
	 *
	 *  @return String de cidade
	 */
	public String getCidade() {
		return cidade;
	}

	/** Método getter para a propriedade cnpj
	 *
	 *  @return Serializable de cnpj
	 */
	public Serializable getCnpj() {
		return cnpj;
	}
	
	/** Método getter para a propriedade cnpj
	 *
	 *  @return String de cnpj
	 */
	public String getStrCnpj() {
		return cnpj.toString();
	}

	/** Método getter para a propriedade concessionaria
	 *
	 *  @return String de concessionaria
	 */
	public String getConcessionaria() {
		return concessionaria;
	}

	/** Método getter para a propriedade dataAberturaLote
	 *
	 *  @return Date de dataAberturaLote
	 */
	public Date getDataAberturaLote() {
		return dataAberturaLote;
	}
	
	/** Método getter para a propriedade dataAberturaLote
	 *
	 *  @return String de dataAberturaLote
	 */
	public String getStrDataAberturaLote() {
		return DateUtils.applyMask(dataAberturaLote);
	}

	/** Método getter para a propriedade dataFechamentoLote
	 *
	 *  @return Date de dataFechamentoLote
	 */
	public Date getDataFechamentoLote() {
		return dataFechamentoLote;
	}
	
	/** Método getter para a propriedade dataFechamentoLote
	 *
	 *  @return String de dataFechamentoLote
	 */
	public String getStrDataFechamentoLote() {
		return DateUtils.applyMask(dataFechamentoLote);
	}

	/** Método getter para a propriedade dataLiberacaoLote
	 *
	 *  @return Date de dataLiberacaoLote
	 */
	public Date getDataLiberacaoLote() {
		return dataLiberacaoLote;
	}
	
	/** Método getter para a propriedade dataLiberacaoLote
	 *
	 *  @return String de dataLiberacaoLote
	 */
	public String getStrDataLiberacaoLote() {
		return DateUtils.applyMask(dataLiberacaoLote);
	}


	/** Método getter para a propriedade empresa
	 *
	 *  @return String de empresa
	 */
	public String getEmpresa() {
		return empresa;
	}

	/** Método getter para a propriedade descLinha
	 *
	 *  @return String de descLinha
	 */
	public String getDescLinha() {
		return descLinha;
	}

	/** Método getter para a propriedade endereco
	 *
	 *  @return String de endereco
	 */
	public String getEndereco() {
		return endereco;
	}

	/** Método getter para a propriedade loteId
	 *
	 *  @return Serializable de loteId
	 */
	public Serializable getLoteId() {
		return loteId;
	}

	/** Método getter para a propriedade tipoLote
	 *
	 *  @return String de tipoLote
	 */
	public String getTipoLote() {
		return tipoLote;
	}

    //	----[ Métodos Setter ]---------------------------------------------------  

	/** Obtém o valor atual de dtNotaFiscal.
	 * 
	 *  @param dtNotaFiscal 
	 *    O novo valor para dtNotaFiscal.
	 */
	public void setDtNotaFiscal(Date dtNotaFiscal) {
		this.dtNotaFiscal = dtNotaFiscal;
	}

	/** Obtém o valor atual de notaFiscal.
	 * 
	 *  @param notaFiscal 
	 *    O novo valor para notaFiscal.
	 */
	public void setNotaFiscal(Serializable notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	/** Obtém o valor atual de serie.
	 * 
	 *  @param serie 
	 *    O novo valor para serie.
	 */
	public void setSerie(String serie) {
		this.serie = serie;
	}

	/** Obtém o valor atual de tipoNF.
	 * 
	 *  @param tipoNF 
	 *    O novo valor para tipoNF.
	 */
	public void setTipoNF(String tipoNF) {
		this.tipoNF = tipoNF;
	}

	/** Obtém o valor atual de valor.
	 * 
	 *  @param valor 
	 *    O novo valor para valor.
	 */
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	

	/** Método setter para a propriedade cidade
	 *
	 * @param cidade String
	 */
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	/** Método setter para a propriedade cnpj
	 *
	 * @param cnpj Serializable
	 */
	public void setCnpj(Serializable cnpj) {
		this.cnpj = cnpj;
	}

	/** Método setter para a propriedade concessionaria
	 *
	 * @param concessionaria String
	 */
	public void setConcessionaria(String concessionaria) {
		this.concessionaria = concessionaria;
	}

	/** Método setter para a propriedade dataAberturaLote
	 *
	 * @param dataAberturaLote Date
	 */
	public void setDataAberturaLote(Date dataAberturaLote) {
		this.dataAberturaLote = dataAberturaLote;
	}

	/** Método setter para a propriedade dataFechamentoLote
	 *
	 * @param dataFechamentoLote Date
	 */
	public void setDataFechamentoLote(Date dataFechamentoLote) {
		this.dataFechamentoLote = dataFechamentoLote;
	}

	/** Método setter para a propriedade dataLiberacaoLote
	 *
	 * @param dataLiberacaoLote Date
	 */
	public void setDataLiberacaoLote(Date dataLiberacaoLote) {
		this.dataLiberacaoLote = dataLiberacaoLote;
	}

	/** Método setter para a propriedade descLinha
	 *
	 * @param descLinha String
	 */
	public void setDescLinha(String descLinha) {
		this.descLinha = descLinha;
	}

	/** Método setter para a propriedade empresa
	 *
	 * @param empresa String
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/** Método setter para a propriedade endereco
	 *
	 * @param endereco String
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	/** Método setter para a propriedade loteId
	 *
	 * @param loteId Serializable
	 */
	public void setLoteId(Serializable loteId) {
		this.loteId = loteId;
	}

	/** Método setter para a propriedade tipoLote
	 *
	 * @param tipoLote String
	 */
	public void setTipoLote(String tipoLote) {
		this.tipoLote = tipoLote;
	}

	/** Método getter para a propriedade cep
	 *
	 *  @return String de Serializable
	 */
	public Serializable getCep() {
		return cep;
	}
	
	/** Método getter para a propriedade cep
	 *
	 *  @return String de cep
	 */
	public String getStrCep() {
		return cep.toString();
	}

	/** Método setter para a propriedade cep
	 *
	 * @param cep Serializable
	 */
	public void setCep(Serializable cep) {
		this.cep = cep;
	}

	/** Método getter para a propriedade estado
	 *
	 *  @return String de estado
	 */
	public String getEstado() {
		return estado;
	}

	/** Método setter para a propriedade estado
	 *
	 * @param estado String
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
}

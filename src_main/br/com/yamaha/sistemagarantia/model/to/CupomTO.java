/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......GarantiaPecaAction.java
 *   .: Criação.....19 de julho, 18:44
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Transfer Object para um cupom.
 */

package br.com.yamaha.sistemagarantia.model.to;

import java.util.Date;

import br.com.resource.infra.utils.DateUtils;

/** Transfer Object de uma peça.
 * 
 *  @author Edson Luiz Sumensari
 */
public class CupomTO {
	
	/** ID do Cupom informado */
    private Long cupomId;
    
    /** ID do lote do Cupom informado */
    private Integer loteId;
	
    /** ID da linha do Cupom informado */
    private Long linhaId;
    
	/** Número do Cupom informado */
    private Long cupomCode;
    
    /** Armazena um chassi. */
    private String chassi;
    
    /** Armazena a data da revisão. */
	private Date dataRevisao;
	
	/** Armazena a data da venda. */
    private Date dataVenda;
    
    /** Armazena a Data de Entrega. */
    private Date dataEntrega;
    
    /** Armazena a Quilometragem . */
    private Long quilometragem;
    
    /** Armazena o número de dias de uso. */
    private Long diasUso;
    
    /** Armazena o número de horas de uso. */
    private Long horasUso;
    
    /** Número da Revisão */
    private Long numeroRevisao;
    
    /** Armazena o modelo do barco */
    private String modeloBarco;
    
    /** Armazena a marca do barco */
    private String marcaBarco;
    
    /** ID do tipo de uso do Cupom informado */
    private Long tipoUsoId;
    
    /** Número do CPF/CNPJ do Cliente */
    private String cnpj;
    
    /** Armazena o nome do cliente */
    private String clienteNome;
    

	/** Método getter para a propriedade chassi
	 * 
	 *  @return String
	 *
	 */
	public String getChassi() {
		return chassi;
	}

	/** Método getter para a propriedade cupomCode
	 * 
	 *  @return Long
	 *
	 */
	public Long getCupomCode() {
		return cupomCode;
	}

	/** Método getter para a propriedade dataEntrega
	 * 
	 *  @return Date
	 *
	 */
	public Date getDataEntrega() {
		return dataEntrega;
	}
	
	/** Método getter para "dataEntrega".
     *  @return
     *      <code>String</code>. O valor atual de dataEntrega,
     *      com uma máscara aplicada.
     */
    public String getMaskedDataEntrega() {
        return DateUtils.applyMask(this.dataEntrega);
    }    

	/** Método getter para a propriedade dataRevisao
	 * 
	 *  @return Date
	 *
	 */
	public Date getDataRevisao() {
		return dataRevisao;
	}
	
	/** Método getter para "dataRevisao".
     *  @return
     *      <code>String</code>. O valor atual de dataRevisao,
     *      com uma máscara aplicada.
     */
    public String getMaskedDataRevisao() {
        return DateUtils.applyMask(this.dataRevisao);
    }    

	/** Método getter para a propriedade dataVenda
	 * 
	 *  @return Date
	 *
	 */
	public Date getDataVenda() {
		return dataVenda;
	}

	/** Método getter para a propriedade diasUso
	 * 
	 *  @return Long
	 *
	 */
	public Long getDiasUso() {
		return diasUso;
	}

	/** Método getter para a propriedade horasUso
	 * 
	 *  @return Long
	 *
	 */
	public Long getHorasUso() {
		return horasUso;
	}

	/** Método getter para a propriedade marcaBarco
	 * 
	 *  @return String
	 *
	 */
	public String getMarcaBarco() {
		return marcaBarco;
	}

	/** Método getter para a propriedade modeloBarco
	 * 
	 *  @return String
	 *
	 */
	public String getModeloBarco() {
		return modeloBarco;
	}

	/** Método getter para a propriedade numeroRevisao
	 * 
	 *  @return Long
	 *
	 */
	public Long getNumeroRevisao() {
		return numeroRevisao;
	}

	/** Método getter para a propriedade quilometragem
	 * 
	 *  @return Long
	 *
	 */
	public Long getQuilometragem() {
		return quilometragem;
	}

	/** Método setter para a propriedade chassi
	 *
	 * @param chassi 
	 *           <code>String</code> a ser designado para chassi.
	 * 
	 */
	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	/** Método setter para a propriedade cupomCode
	 *
	 * @param cupomCode 
	 *           <code>Long</code> a ser designado para cupomCode.
	 * 
	 */
	public void setCupomCode(Long cupomCode) {
		this.cupomCode = cupomCode;
	}

	/** Método setter para a propriedade dataEntrega
	 *
	 * @param dataEntrega 
	 *           <code>Date</code> a ser designado para dataEntrega.
	 * 
	 */
	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	/** Método setter para a propriedade dataRevisao
	 *
	 * @param dataRevisao 
	 *           <code>Date</code> a ser designado para dataRevisao.
	 * 
	 */
	public void setDataRevisao(Date dataRevisao) {
		this.dataRevisao = dataRevisao;
	}

	/** Método setter para a propriedade dataVenda
	 *
	 * @param dataVenda 
	 *           <code>Date</code> a ser designado para dataVenda.
	 * 
	 */
	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	/** Método setter para a propriedade diasUso
	 *
	 * @param diasUso 
	 *           <code>Long</code> a ser designado para diasUso.
	 * 
	 */
	public void setDiasUso(Long diasUso) {
		this.diasUso = diasUso;
	}

	/** Método setter para a propriedade horasUso
	 *
	 * @param horasUso 
	 *           <code>Long</code> a ser designado para horasUso.
	 * 
	 */
	public void setHorasUso(Long horasUso) {
		this.horasUso = horasUso;
	}

	/** Método setter para a propriedade marcaBarco
	 *
	 * @param marcaBarco 
	 *           <code>String</code> a ser designado para marcaBarco.
	 * 
	 */
	public void setMarcaBarco(String marcaBarco) {
		this.marcaBarco = marcaBarco;
	}

	/** Método setter para a propriedade modeloBarco
	 *
	 * @param modeloBarco 
	 *           <code>String</code> a ser designado para modeloBarco.
	 * 
	 */
	public void setModeloBarco(String modeloBarco) {
		this.modeloBarco = modeloBarco;
	}

	/** Método setter para a propriedade numeroRevisao
	 *
	 * @param numeroRevisao 
	 *           <code>Long</code> a ser designado para numeroRevisao.
	 * 
	 */
	public void setNumeroRevisao(Long numeroRevisao) {
		this.numeroRevisao = numeroRevisao;
	}

	/** Método setter para a propriedade quilometragem
	 *
	 * @param quilometragem 
	 *           <code>Long</code> a ser designado para quilometragem.
	 * 
	 */
	public void setQuilometragem(Long quilometragem) {
		this.quilometragem = quilometragem;
	}

	/** Método getter para a propriedade clienteNome
	 * 
	 *  @return String
	 *
	 */
	public String getClienteNome() {
		return clienteNome;
	}

	/** Método getter para a propriedade cnpj
	 * 
	 *  @return Long
	 *
	 */
	public String getCnpj() {
		return cnpj;
	}

	/** Método getter para a propriedade cupomId
	 * 
	 *  @return Long
	 *
	 */
	public Long getCupomId() {
		return cupomId;
	}

	/** Método getter para a propriedade linhaId
	 * 
	 *  @return Long
	 *
	 */
	public Long getLinhaId() {
		return linhaId;
	}

	/** Método getter para a propriedade loteId
	 * 
	 *  @return Integer
	 *
	 */
	public Integer getLoteId() {
		return loteId;
	}

	/** Método getter para a propriedade tipoUsoId
	 * 
	 *  @return Long
	 *
	 */
	public Long getTipoUsoId() {
		return tipoUsoId;
	}

	/** Método setter para a propriedade clienteNome
	 *
	 * @param clienteNome 
	 *           <code>String</code> a ser designado para clienteNome.
	 * 
	 */
	public void setClienteNome(String clienteNome) {
		this.clienteNome = clienteNome;
	}

	/** Método setter para a propriedade cnpj
	 *
	 * @param cnpj 
	 *           <code>String</code> a ser designado para cnpj.
	 * 
	 */
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	/** Método setter para a propriedade cupomId
	 *
	 * @param cupomId 
	 *           <code>Long</code> a ser designado para cupomId.
	 * 
	 */
	public void setCupomId(Long cupomId) {
		this.cupomId = cupomId;
	}

	/** Método setter para a propriedade linhaId
	 *
	 * @param linhaId 
	 *           <code>Long</code> a ser designado para linhaId.
	 * 
	 */
	public void setLinhaId(Long linhaId) {
		this.linhaId = linhaId;
	}

	/** Método setter para a propriedade loteId
	 *
	 * @param loteId 
	 *           <code>Integer</code> a ser designado para loteId.
	 * 
	 */
	public void setLoteId(Integer loteId) {
		this.loteId = loteId;
	}

	/** Método setter para a propriedade tipoUsoId
	 *
	 * @param tipoUsoId 
	 *           <code>Long</code> a ser designado para tipoUsoId.
	 * 
	 */
	public void setTipoUsoId(Long tipoUsoId) {
		this.tipoUsoId = tipoUsoId;
	}	
	
}

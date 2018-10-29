/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......GarantiaPecaAction.java
 *   .: Cria��o.....19 de julho, 18:44
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Transfer Object para um cupom.
 */

package br.com.yamaha.sistemagarantia.model.to;

import java.util.Date;

import br.com.resource.infra.utils.DateUtils;

/** Transfer Object de uma pe�a.
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
    
	/** N�mero do Cupom informado */
    private Long cupomCode;
    
    /** Armazena um chassi. */
    private String chassi;
    
    /** Armazena a data da revis�o. */
	private Date dataRevisao;
	
	/** Armazena a data da venda. */
    private Date dataVenda;
    
    /** Armazena a Data de Entrega. */
    private Date dataEntrega;
    
    /** Armazena a Quilometragem . */
    private Long quilometragem;
    
    /** Armazena o n�mero de dias de uso. */
    private Long diasUso;
    
    /** Armazena o n�mero de horas de uso. */
    private Long horasUso;
    
    /** N�mero da Revis�o */
    private Long numeroRevisao;
    
    /** Armazena o modelo do barco */
    private String modeloBarco;
    
    /** Armazena a marca do barco */
    private String marcaBarco;
    
    /** ID do tipo de uso do Cupom informado */
    private Long tipoUsoId;
    
    /** N�mero do CPF/CNPJ do Cliente */
    private String cnpj;
    
    /** Armazena o nome do cliente */
    private String clienteNome;
    

	/** M�todo getter para a propriedade chassi
	 * 
	 *  @return String
	 *
	 */
	public String getChassi() {
		return chassi;
	}

	/** M�todo getter para a propriedade cupomCode
	 * 
	 *  @return Long
	 *
	 */
	public Long getCupomCode() {
		return cupomCode;
	}

	/** M�todo getter para a propriedade dataEntrega
	 * 
	 *  @return Date
	 *
	 */
	public Date getDataEntrega() {
		return dataEntrega;
	}
	
	/** M�todo getter para "dataEntrega".
     *  @return
     *      <code>String</code>. O valor atual de dataEntrega,
     *      com uma m�scara aplicada.
     */
    public String getMaskedDataEntrega() {
        return DateUtils.applyMask(this.dataEntrega);
    }    

	/** M�todo getter para a propriedade dataRevisao
	 * 
	 *  @return Date
	 *
	 */
	public Date getDataRevisao() {
		return dataRevisao;
	}
	
	/** M�todo getter para "dataRevisao".
     *  @return
     *      <code>String</code>. O valor atual de dataRevisao,
     *      com uma m�scara aplicada.
     */
    public String getMaskedDataRevisao() {
        return DateUtils.applyMask(this.dataRevisao);
    }    

	/** M�todo getter para a propriedade dataVenda
	 * 
	 *  @return Date
	 *
	 */
	public Date getDataVenda() {
		return dataVenda;
	}

	/** M�todo getter para a propriedade diasUso
	 * 
	 *  @return Long
	 *
	 */
	public Long getDiasUso() {
		return diasUso;
	}

	/** M�todo getter para a propriedade horasUso
	 * 
	 *  @return Long
	 *
	 */
	public Long getHorasUso() {
		return horasUso;
	}

	/** M�todo getter para a propriedade marcaBarco
	 * 
	 *  @return String
	 *
	 */
	public String getMarcaBarco() {
		return marcaBarco;
	}

	/** M�todo getter para a propriedade modeloBarco
	 * 
	 *  @return String
	 *
	 */
	public String getModeloBarco() {
		return modeloBarco;
	}

	/** M�todo getter para a propriedade numeroRevisao
	 * 
	 *  @return Long
	 *
	 */
	public Long getNumeroRevisao() {
		return numeroRevisao;
	}

	/** M�todo getter para a propriedade quilometragem
	 * 
	 *  @return Long
	 *
	 */
	public Long getQuilometragem() {
		return quilometragem;
	}

	/** M�todo setter para a propriedade chassi
	 *
	 * @param chassi 
	 *           <code>String</code> a ser designado para chassi.
	 * 
	 */
	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	/** M�todo setter para a propriedade cupomCode
	 *
	 * @param cupomCode 
	 *           <code>Long</code> a ser designado para cupomCode.
	 * 
	 */
	public void setCupomCode(Long cupomCode) {
		this.cupomCode = cupomCode;
	}

	/** M�todo setter para a propriedade dataEntrega
	 *
	 * @param dataEntrega 
	 *           <code>Date</code> a ser designado para dataEntrega.
	 * 
	 */
	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	/** M�todo setter para a propriedade dataRevisao
	 *
	 * @param dataRevisao 
	 *           <code>Date</code> a ser designado para dataRevisao.
	 * 
	 */
	public void setDataRevisao(Date dataRevisao) {
		this.dataRevisao = dataRevisao;
	}

	/** M�todo setter para a propriedade dataVenda
	 *
	 * @param dataVenda 
	 *           <code>Date</code> a ser designado para dataVenda.
	 * 
	 */
	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	/** M�todo setter para a propriedade diasUso
	 *
	 * @param diasUso 
	 *           <code>Long</code> a ser designado para diasUso.
	 * 
	 */
	public void setDiasUso(Long diasUso) {
		this.diasUso = diasUso;
	}

	/** M�todo setter para a propriedade horasUso
	 *
	 * @param horasUso 
	 *           <code>Long</code> a ser designado para horasUso.
	 * 
	 */
	public void setHorasUso(Long horasUso) {
		this.horasUso = horasUso;
	}

	/** M�todo setter para a propriedade marcaBarco
	 *
	 * @param marcaBarco 
	 *           <code>String</code> a ser designado para marcaBarco.
	 * 
	 */
	public void setMarcaBarco(String marcaBarco) {
		this.marcaBarco = marcaBarco;
	}

	/** M�todo setter para a propriedade modeloBarco
	 *
	 * @param modeloBarco 
	 *           <code>String</code> a ser designado para modeloBarco.
	 * 
	 */
	public void setModeloBarco(String modeloBarco) {
		this.modeloBarco = modeloBarco;
	}

	/** M�todo setter para a propriedade numeroRevisao
	 *
	 * @param numeroRevisao 
	 *           <code>Long</code> a ser designado para numeroRevisao.
	 * 
	 */
	public void setNumeroRevisao(Long numeroRevisao) {
		this.numeroRevisao = numeroRevisao;
	}

	/** M�todo setter para a propriedade quilometragem
	 *
	 * @param quilometragem 
	 *           <code>Long</code> a ser designado para quilometragem.
	 * 
	 */
	public void setQuilometragem(Long quilometragem) {
		this.quilometragem = quilometragem;
	}

	/** M�todo getter para a propriedade clienteNome
	 * 
	 *  @return String
	 *
	 */
	public String getClienteNome() {
		return clienteNome;
	}

	/** M�todo getter para a propriedade cnpj
	 * 
	 *  @return Long
	 *
	 */
	public String getCnpj() {
		return cnpj;
	}

	/** M�todo getter para a propriedade cupomId
	 * 
	 *  @return Long
	 *
	 */
	public Long getCupomId() {
		return cupomId;
	}

	/** M�todo getter para a propriedade linhaId
	 * 
	 *  @return Long
	 *
	 */
	public Long getLinhaId() {
		return linhaId;
	}

	/** M�todo getter para a propriedade loteId
	 * 
	 *  @return Integer
	 *
	 */
	public Integer getLoteId() {
		return loteId;
	}

	/** M�todo getter para a propriedade tipoUsoId
	 * 
	 *  @return Long
	 *
	 */
	public Long getTipoUsoId() {
		return tipoUsoId;
	}

	/** M�todo setter para a propriedade clienteNome
	 *
	 * @param clienteNome 
	 *           <code>String</code> a ser designado para clienteNome.
	 * 
	 */
	public void setClienteNome(String clienteNome) {
		this.clienteNome = clienteNome;
	}

	/** M�todo setter para a propriedade cnpj
	 *
	 * @param cnpj 
	 *           <code>String</code> a ser designado para cnpj.
	 * 
	 */
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	/** M�todo setter para a propriedade cupomId
	 *
	 * @param cupomId 
	 *           <code>Long</code> a ser designado para cupomId.
	 * 
	 */
	public void setCupomId(Long cupomId) {
		this.cupomId = cupomId;
	}

	/** M�todo setter para a propriedade linhaId
	 *
	 * @param linhaId 
	 *           <code>Long</code> a ser designado para linhaId.
	 * 
	 */
	public void setLinhaId(Long linhaId) {
		this.linhaId = linhaId;
	}

	/** M�todo setter para a propriedade loteId
	 *
	 * @param loteId 
	 *           <code>Integer</code> a ser designado para loteId.
	 * 
	 */
	public void setLoteId(Integer loteId) {
		this.loteId = loteId;
	}

	/** M�todo setter para a propriedade tipoUsoId
	 *
	 * @param tipoUsoId 
	 *           <code>Long</code> a ser designado para tipoUsoId.
	 * 
	 */
	public void setTipoUsoId(Long tipoUsoId) {
		this.tipoUsoId = tipoUsoId;
	}	
	
}

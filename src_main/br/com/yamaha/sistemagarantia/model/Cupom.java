/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......Cupom.java
 *   .: Criação.....03 de maio, 09:17
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "Cupom".
 */
package br.com.yamaha.sistemagarantia.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.model.EntityObject;
import br.com.resource.infra.utils.DateUtils;
import br.com.resource.infra.utils.NumberUtils;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "Cupom" no sistema.
 *  
 *  O Id de desta classe é formado pelo <code>número de um cupom (cupomId)</code>
 *  E por um objeto <code>Revisao</code>.
 *  
 *  Para obter o número do cupom:
 *  <code> getCompositeId().getCupomId(); </code>
 *  
 *  Para determinar um Id composto:
 *  <code> setCompositeId( new CupomId( Revisao revisao, Long cupomId ) ); </code> 
 *  
 *  @author Edson Luiz Sumensari
 */
public class Cupom extends EntityObject {
	
	//----[ Atributos de classe e instância ]-----------------------------------
    
    /** Version ID padrão para serialização */
    private static final long serialVersionUID = 1L;    
    
    /** Id composto do Alerta, tratado na classe CupomId 
     *  Esta classe susbstitui a propriedade entityId.
     *  
     
    private CupomId compositeEntityId;*/
    
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
    
    /** Armazena o número da note fiscal. */
    private Long numeroNotaFiscal;
    
    /** Armazena a data da nota fiscal. */
    private Date dataNotaFiscal;
    
    /** Armazena a série da nota fiscal. */
    private String serieNotaFiscal;
    
    /** Armazena o valor da revisão. */
    private double valorRevisao;
    
    /** Armazena o valor da bonificação */
    private double valorBonificacao;
    
    /** Armazena o modelo do barco */
    private String modeloBarco;
    
    /** Armazena a marca do barco */
    private String marcaBarco;
    
    /** Armazena o id do status de movimentação */
    private Long statusMovId;
    
    /** Armazena o id da recusa */
    private Long recusaId;
    
    /** Armazena o id do analista */
    private Long analistaId;
    
    /** Armazena a autorização especial */
    private Long autorizacaoEsp;
    
    /** Armazena o número do manual substituido */
    private Long numeroManualSubst;
    
    /** Armazena o id do alertCupomId */
    private Long alertCupomId;
    
    /** Armazena o status do cupom */
    private StatusGarantia statusGarantia;
    
    /** Entidade AlertCupom para o cupom */
    private AlertCupom alertCupom;
    
    /** Entidade Cliente para o cupom */
    private Cliente cliente;
    
    /** Entidade Lote para o cupom */
    private Lote lote;
    
    /** Entidade ValorServico para o cupom */
    private ValorServico valorServico;  
    
    /** Entidade Revisao para o cupom */
    private Revisao revisao;
    
    /** Entidade TipoUsoBarco para o cupom */
    private TipoUsoBarco tipoUsoBarco; 
    
    /** Notas fiscais de um cupom */
    private Collection notas;
    
    private Date endDate;
    
	/** Construtor */
    public Cupom(){
    	
    	super();
    	
    	// Fornece "null" como valor para o campo ID. Isto indicará
        // para a aplicação que este bean é novo.
        this.setEntityId(null);
        this.notas = new ArrayList();
        //this.setCompositeEntityId(null);
    }
            
    //	----[ Métodos Getter ]---------------------------------------------------
    
    /** Método getter para a propriedade notas
	 * 
	 *  @return Collection
	 *
	 */
	public Collection getNotas() {
		return notas;
	}
    
	/** Método getter para a propriedade analistaId
	 * 
	 *  @return Long
	 *
	 */
	public Long getAnalistaId() {
		return analistaId;
	}

	/** Método getter para a propriedade autorizacaoEsp
	 * 
	 *  @return Long
	 *
	 */
	public Long getAutorizacaoEsp() {
		return autorizacaoEsp;
	}

	/** Método getter para a propriedade chassi
	 * 
	 *  @return String
	 *
	 */
	public String getChassi() {
		return chassi.toUpperCase();
	}

	/** Método getter para a propriedade cliente
	 * 
	 *  @return Cliente
	 *
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/** Método getter para a propriedade dataEntrega
	 * 
	 *  @return Date
	 *
	 */
	public Date getDataEntrega() {
		return dataEntrega;
	}

	/** Método getter para a propriedade dataNotaFiscal
	 * 
	 *  @return Date
	 *
	 */
	public Date getDataNotaFiscal() {
		return dataNotaFiscal;
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
	
	/** Método getter para "dataVenda".
     *  @return
     *      <code>String</code>. O valor atual de dataVenda,
     *      com uma máscara aplicada.
     */
    public String getMaskedDataVenda() {
        return DateUtils.applyMask(this.dataVenda);
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

	/** Método getter para a propriedade lote
	 * 
	 *  @return Lote
	 *
	 */
	public Lote getLote() {
		return lote;
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

	/** Método getter para a propriedade numeroManualSubst
	 * 
	 *  @return Long
	 *
	 */
	public Long getNumeroManualSubst() {
		return numeroManualSubst;
	}

	/** Método getter para a propriedade numeroNotaFiscal
	 * 
	 *  @return Long
	 *
	 */
	public Long getNumeroNotaFiscal() {
		return numeroNotaFiscal;
	}

	/** Método getter para a propriedade quilometragem
	 * 
	 *  @return Long
	 *
	 */
	public Long getQuilometragem() {
		return quilometragem;
	}

	/** Método getter para a propriedade recusaId
	 * 
	 *  @return Long
	 *
	 */
	public Long getRecusaId() {
		return recusaId;
	}

	/** Método getter para a propriedade revisao
	 * 
	 *  @return Revisao
	 *
	 */
	public Revisao getRevisao() {
		return revisao;
	}

	/** Método getter para a propriedade serieNotaFiscal
	 * 
	 *  @return String
	 *
	 */
	public String getSerieNotaFiscal() {
		return serieNotaFiscal;
	}

	/** Método getter para a propriedade statusMovId
	 * 
	 *  @return Long
	 *
	 */
	public Long getStatusMovId() {
		return (Long)statusGarantia.getEntityId();
	}

	/** Método getter para a propriedade tipoUsoBarco
	 * 
	 *  @return TipoUsoBarco
	 *
	 */
	public TipoUsoBarco getTipoUsoBarco() {
		return tipoUsoBarco;
	}

	/** Método getter para a propriedade valorBonificacao
	 * 
	 *  @return Long
	 *
	 */
	public double getValorBonificacao() {
		return NumberUtils.round(valorBonificacao,2);
	}

	/** Método getter para a propriedade valorRevisao
	 * 
	 *  @return Long
	 *
	 */
	public double getValorRevisao() {
		return NumberUtils.round( valorRevisao,2);
	}

	/** Método getter para a propriedade valorServico
	 * 
	 *  @return ValorServico
	 *
	 */
	public ValorServico getValorServico() {
		return valorServico;
	}
	
	/** Retorna o valor total da Revisão (revisão + Binificação), no formato Moeda REAL 
	 * 
	 * @return String
	 */
	public String getFormatedValueTotalRevisao() {
		 
		return NumberUtils.formatCurrency(
										  NumberUtils.round( (this.getValorRevisao() + this.getValorBonificacao()),2 )
										  );
		
	}
	
	/** Método getter para a propriedade compositeEntityId
	 * 
	 *  @return CupomId
	 *
	 
	public CupomId getCompositeEntityId() {
		return compositeEntityId;
	}*/
	
	/** Método getter para a propriedade AlertCupom
	 * 
	 *  @return AlertCupom
	 *
	 */
	public AlertCupom getAlertCupom() {
		return alertCupom;
	}
	
	/** Método getter para a propriedade alertCupomId
	 * 
	 *  @return Long
	 *
	 */
	public Long getAlertCupomId() {
		return alertCupomId;
	}
	
	/** Método getter para a propriedade cupomCode
	 * 
	 *  @return Long
	 *
	 */
	public Long getCupomCode() {
		return cupomCode;
	}
	
	/** Método getter para a propriedade statusGarantia
	 *
	 * @return statusGarantia do tipo StatusGarantia
	 *
	 */
	
	public StatusGarantia getStatusGarantia() {
		return statusGarantia;
	}

	
	//	----[ Métodos Setter ]---------------------------------------------------
	
	
	/** Método setter para a propriedade statusGarantia
	 *
	 * @param statusGarantia 
	 *       <code>statusGarantia<code> a ser designado para Cupom.java
	 *
	 */
	public void setStatusGarantia(StatusGarantia statusGarantia) {
		this.statusGarantia = statusGarantia;
	}

	/** Método setter para a propriedade notas
	 *
	 * @param notas 
	 *           <code>Collection</code> a ser designado para notas.
	 * 
	 */
	public void setNotas(Collection notas) {
		this.notas = notas;
	}
	
	/** Método setter para a propriedade analistaId
	 *
	 * @param analistaId 
	 *           <code>Long</code> a ser designado para analistaId.
	 * 
	 */
	public void setAnalistaId(Long analistaId) {
		this.analistaId = analistaId;
	}

	/** Método setter para a propriedade autorizacaoEsp
	 *
	 * @param autorizacaoEsp 
	 *           <code>Long</code> a ser designado para autorizacaoEsp.
	 * 
	 */
	public void setAutorizacaoEsp(Long autorizacaoEsp) {
		this.autorizacaoEsp = autorizacaoEsp;
	}

	/** Método setter para a propriedade chassi
	 *
	 * @param chassi 
	 *           <code>String</code> a ser designado para chassi.
	 * 
	 */
	public void setChassi(String chassi) {
		this.chassi = chassi.toUpperCase();
	}

	/** Método setter para a propriedade cliente
	 *
	 * @param cliente 
	 *           <code>Cliente</code> a ser designado para cliente.
	 * 
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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

	/** Método setter para a propriedade dataNotaFiscal
	 *
	 * @param dataNotaFiscal 
	 *           <code>Date</code> a ser designado para dataNotaFiscal.
	 * 
	 */
	public void setDataNotaFiscal(Date dataNotaFiscal) {
		this.dataNotaFiscal = dataNotaFiscal;
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

	/** Método setter para a propriedade lote
	 *
	 * @param lote 
	 *           <code>Lote</code> a ser designado para lote.
	 * 
	 */
	public void setLote(Lote lote) {
		this.lote = lote;
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

	/** Método setter para a propriedade numeroManualSubst
	 *
	 * @param numeroManualSubst 
	 *           <code>Long</code> a ser designado para numeroManualSubst.
	 * 
	 */
	public void setNumeroManualSubst(Long numeroManualSubst) {
		this.numeroManualSubst = numeroManualSubst;
	}

	/** Método setter para a propriedade numeroNotaFiscal
	 *
	 * @param numeroNotaFiscal 
	 *           <code>Long</code> a ser designado para numeroNotaFiscal.
	 * 
	 */
	public void setNumeroNotaFiscal(Long numeroNotaFiscal) {
		this.numeroNotaFiscal = numeroNotaFiscal;
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

	/** Método setter para a propriedade recusaId
	 *
	 * @param recusaId 
	 *           <code>Long</code> a ser designado para recusaId.
	 * 
	 */
	public void setRecusaId(Long recusaId) {
		this.recusaId = recusaId;
	}

	/** Método setter para a propriedade revisao
	 *
	 * @param revisao 
	 *           <code>Revisao</code> a ser designado para revisao.
	 * 
	 */
	public void setRevisao(Revisao revisao) {
		this.revisao = revisao;
	}

	/** Método setter para a propriedade serieNotaFiscal
	 *
	 * @param serieNotaFiscal 
	 *           <code>String</code> a ser designado para serieNotaFiscal.
	 * 
	 */
	public void setSerieNotaFiscal(String serieNotaFiscal) {
		this.serieNotaFiscal = serieNotaFiscal;
	}

	/** Método setter para a propriedade statusMovId
	 *
	 * @param statusMovId 
	 *           <code>Long</code> a ser designado para statusMovId.
	 * 
	 */
	public void setStatusMovId(Long statusMovId) {
		this.statusMovId = statusMovId;
	}

	/** Método setter para a propriedade tipoUsoBarco
	 *
	 * @param tipoUsoBarco 
	 *           <code>TipoUsoBarco</code> a ser designado para tipoUsoBarco.
	 * 
	 */
	public void setTipoUsoBarco(TipoUsoBarco tipoUsoBarco) {
		this.tipoUsoBarco = tipoUsoBarco;
	}

	/** Método setter para a propriedade valorBonificacao
	 *
	 * @param valorBonificacao 
	 *           <code>Long</code> a ser designado para valorBonificacao.
	 * 
	 */
	public void setValorBonificacao(double valorBonificacao) {
		this.valorBonificacao = valorBonificacao;
	}

	/** Método setter para a propriedade valorRevisao
	 *
	 * @param valorRevisao 
	 *           <code>Long</code> a ser designado para valorRevisao.
	 * 
	 */
	public void setValorRevisao(double valorRevisao) {
		this.valorRevisao = valorRevisao;
	}

	/** Método setter para a propriedade valorServico
	 *
	 * @param valorServico 
	 *           <code>ValorServico</code> a ser designado para valorServico.
	 * 
	 */
	public void setValorServico(ValorServico valorServico) {
		this.valorServico = valorServico;
	}

	/** Método setter para a propriedade compositeEntityId
	 *
	 * @param compositeEntityId 
	 *           <code>CupomId</code> a ser designado para compositeEntityId.
	 * 
	 
	public void setCompositeEntityId(CupomId compositeEntityId) {
		this.compositeEntityId = compositeEntityId;
	}*/
	
	/** Método setter para a propriedade listAlertCupom
	 *
	 * @param alertCupom 
	 *           <code>AlertCupom</code> a ser designado para alertCupom.
	 * 
	 */
	public void setAlertCupom(AlertCupom alertCupom) {
		this.alertCupom = alertCupom;
	}
	
	/** Método setter para a propriedade alertCupomId
	 *
	 * @param alertCupomId 
	 *           <code>Long</code> a ser designado para alertCupomId.
	 * 
	 */
	public void setAlertCupomId(Long alertCupomId) {
		this.alertCupomId = alertCupomId;
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

	
	//	----[ Métodos de Serviço ]---------------------------------------------------
	
	/** Indica se esta entidade é nova (um id de valor null)  ou se já existia 
     *  (um id cujo valor já foi preenchido).
     *  <p/>
     *  Este método é necessário pois o atributo "id" não é visível para outros 
     *  objetos. E a utilização do método "getId" obrigaria o desenvolver a 
     *  utilizar uma cláusula <code>if</code> um pouco mais complexa.
     *  <p/>
     *  Sendo assim, a implementação deste método se fez necessária.
     * 
     *  @return boolean   
     *     Um valor booleano indicando se esta entidade é nova ou não.
     
    public boolean isNew() {

        if ( this.getCompositeEntityId() == null )
            return true;
        else 
            return false;

    }*/
    
    /** Retorna o Valor total da revisão (revisão + bonificação) formato para moeda
     * 
     * @return String
     *  	Soma da propriedade valorRevisão com a prop. valorBonificação
     */
    public String getValorTotalRevisao() {
    	
    	return  NumberUtils.formatCurrency( NumberUtils.round( (this.valorRevisao + this.valorBonificacao),2 ) );
    	
    }
    
    /** Adiciona uma nota fiscal ao Cupom
     * 
     * @param notaFiscal]
     * 		<code>NotaFiscal<code> Uma entidade de Nota Fiscal.
     * 
     * @throws BusinessException
     * 
     */
    public void addNotaFiscal(NotaFiscal notaFiscal) throws BusinessException{
		
		if ( notaFiscal == null ) {
			
			throw new BusinessException("O Objeto enviado é Nulo!" );
			
		} else {
			
			this.notas.add(notaFiscal);
			
		}
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	} 
	
	/** Valida se o cupom pode ser removido ou não
	 * 
	 * @return boolean TRUE para exclusão válida
	 */
	public boolean getValidateRemove() {
		
		boolean isValid = false;
		
		boolean statusMov  = ( (this.getStatusMovId().equals(StatusGarantia.STATUS_AGUARDANDO_DIGITACAO_NOTA)
							   || this.getStatusMovId().equals(StatusGarantia.STATUS_NF_DIVERGENTE)) 
							   && this.getLote().getDataEnvioAdiant() == null
							  );
		
		boolean statusLote = ( (this.getStatusMovId().equals(StatusGarantia.STATUS_PERIODICA) 
							   &&	StatusLote.STATUS_EM_DIGITACAO.equals(this.getLote().getStatusLote().getEntityId())
							   &&   this.getLote().getDataEnvioAdiant() == null)
							   ||
							   (this.getStatusMovId().equals(StatusGarantia.STATUS_PERIODICA) 
								&& StatusLote.STATUS_EM_DIGITACAO.equals(this.getLote().getStatusLote().getEntityId()))							   
							  ) ;
		
		if ( statusMov || statusLote )
			isValid = true;
				
		return isValid;
		
	}
	
	/** Valida se o cupom pode ser alterado ou não
	 * 
	 * @return boolean TRUE para alteração válida
	 */
	public boolean getValidateAlter() {
		
		boolean isValid = false;
		
		boolean statusMov  = ( (this.getStatusMovId().equals(StatusGarantia.STATUS_AGUARDANDO_DIGITACAO_NOTA)
							   || this.getStatusMovId().equals(StatusGarantia.STATUS_NF_DIVERGENTE)) 
							   && this.getLote().getDataEnvioAdiant() == null
							  );
		
		boolean statusLote = ( this.getStatusMovId().equals(StatusGarantia.STATUS_PERIODICA) 
							   &&	StatusLote.STATUS_EM_DIGITACAO.equals(this.getLote().getStatusLote().getEntityId())
							   &&   this.getLote().getDataEnvioAdiant() == null
							  ) ;
		
		if ( statusMov || statusLote )
			isValid = true;
				
		return isValid;
		
	}

}

/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......Cupom.java
 *   .: Cria��o.....03 de maio, 09:17
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "Cupom".
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
 *  O Id de desta classe � formado pelo <code>n�mero de um cupom (cupomId)</code>
 *  E por um objeto <code>Revisao</code>.
 *  
 *  Para obter o n�mero do cupom:
 *  <code> getCompositeId().getCupomId(); </code>
 *  
 *  Para determinar um Id composto:
 *  <code> setCompositeId( new CupomId( Revisao revisao, Long cupomId ) ); </code> 
 *  
 *  @author Edson Luiz Sumensari
 */
public class Cupom extends EntityObject {
	
	//----[ Atributos de classe e inst�ncia ]-----------------------------------
    
    /** Version ID padr�o para serializa��o */
    private static final long serialVersionUID = 1L;    
    
    /** Id composto do Alerta, tratado na classe CupomId 
     *  Esta classe susbstitui a propriedade entityId.
     *  
     
    private CupomId compositeEntityId;*/
    
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
    
    /** Armazena o n�mero da note fiscal. */
    private Long numeroNotaFiscal;
    
    /** Armazena a data da nota fiscal. */
    private Date dataNotaFiscal;
    
    /** Armazena a s�rie da nota fiscal. */
    private String serieNotaFiscal;
    
    /** Armazena o valor da revis�o. */
    private double valorRevisao;
    
    /** Armazena o valor da bonifica��o */
    private double valorBonificacao;
    
    /** Armazena o modelo do barco */
    private String modeloBarco;
    
    /** Armazena a marca do barco */
    private String marcaBarco;
    
    /** Armazena o id do status de movimenta��o */
    private Long statusMovId;
    
    /** Armazena o id da recusa */
    private Long recusaId;
    
    /** Armazena o id do analista */
    private Long analistaId;
    
    /** Armazena a autoriza��o especial */
    private Long autorizacaoEsp;
    
    /** Armazena o n�mero do manual substituido */
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
    	
    	// Fornece "null" como valor para o campo ID. Isto indicar�
        // para a aplica��o que este bean � novo.
        this.setEntityId(null);
        this.notas = new ArrayList();
        //this.setCompositeEntityId(null);
    }
            
    //	----[ M�todos Getter ]---------------------------------------------------
    
    /** M�todo getter para a propriedade notas
	 * 
	 *  @return Collection
	 *
	 */
	public Collection getNotas() {
		return notas;
	}
    
	/** M�todo getter para a propriedade analistaId
	 * 
	 *  @return Long
	 *
	 */
	public Long getAnalistaId() {
		return analistaId;
	}

	/** M�todo getter para a propriedade autorizacaoEsp
	 * 
	 *  @return Long
	 *
	 */
	public Long getAutorizacaoEsp() {
		return autorizacaoEsp;
	}

	/** M�todo getter para a propriedade chassi
	 * 
	 *  @return String
	 *
	 */
	public String getChassi() {
		return chassi.toUpperCase();
	}

	/** M�todo getter para a propriedade cliente
	 * 
	 *  @return Cliente
	 *
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/** M�todo getter para a propriedade dataEntrega
	 * 
	 *  @return Date
	 *
	 */
	public Date getDataEntrega() {
		return dataEntrega;
	}

	/** M�todo getter para a propriedade dataNotaFiscal
	 * 
	 *  @return Date
	 *
	 */
	public Date getDataNotaFiscal() {
		return dataNotaFiscal;
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
	
	/** M�todo getter para "dataVenda".
     *  @return
     *      <code>String</code>. O valor atual de dataVenda,
     *      com uma m�scara aplicada.
     */
    public String getMaskedDataVenda() {
        return DateUtils.applyMask(this.dataVenda);
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

	/** M�todo getter para a propriedade lote
	 * 
	 *  @return Lote
	 *
	 */
	public Lote getLote() {
		return lote;
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

	/** M�todo getter para a propriedade numeroManualSubst
	 * 
	 *  @return Long
	 *
	 */
	public Long getNumeroManualSubst() {
		return numeroManualSubst;
	}

	/** M�todo getter para a propriedade numeroNotaFiscal
	 * 
	 *  @return Long
	 *
	 */
	public Long getNumeroNotaFiscal() {
		return numeroNotaFiscal;
	}

	/** M�todo getter para a propriedade quilometragem
	 * 
	 *  @return Long
	 *
	 */
	public Long getQuilometragem() {
		return quilometragem;
	}

	/** M�todo getter para a propriedade recusaId
	 * 
	 *  @return Long
	 *
	 */
	public Long getRecusaId() {
		return recusaId;
	}

	/** M�todo getter para a propriedade revisao
	 * 
	 *  @return Revisao
	 *
	 */
	public Revisao getRevisao() {
		return revisao;
	}

	/** M�todo getter para a propriedade serieNotaFiscal
	 * 
	 *  @return String
	 *
	 */
	public String getSerieNotaFiscal() {
		return serieNotaFiscal;
	}

	/** M�todo getter para a propriedade statusMovId
	 * 
	 *  @return Long
	 *
	 */
	public Long getStatusMovId() {
		return (Long)statusGarantia.getEntityId();
	}

	/** M�todo getter para a propriedade tipoUsoBarco
	 * 
	 *  @return TipoUsoBarco
	 *
	 */
	public TipoUsoBarco getTipoUsoBarco() {
		return tipoUsoBarco;
	}

	/** M�todo getter para a propriedade valorBonificacao
	 * 
	 *  @return Long
	 *
	 */
	public double getValorBonificacao() {
		return NumberUtils.round(valorBonificacao,2);
	}

	/** M�todo getter para a propriedade valorRevisao
	 * 
	 *  @return Long
	 *
	 */
	public double getValorRevisao() {
		return NumberUtils.round( valorRevisao,2);
	}

	/** M�todo getter para a propriedade valorServico
	 * 
	 *  @return ValorServico
	 *
	 */
	public ValorServico getValorServico() {
		return valorServico;
	}
	
	/** Retorna o valor total da Revis�o (revis�o + Binifica��o), no formato Moeda REAL 
	 * 
	 * @return String
	 */
	public String getFormatedValueTotalRevisao() {
		 
		return NumberUtils.formatCurrency(
										  NumberUtils.round( (this.getValorRevisao() + this.getValorBonificacao()),2 )
										  );
		
	}
	
	/** M�todo getter para a propriedade compositeEntityId
	 * 
	 *  @return CupomId
	 *
	 
	public CupomId getCompositeEntityId() {
		return compositeEntityId;
	}*/
	
	/** M�todo getter para a propriedade AlertCupom
	 * 
	 *  @return AlertCupom
	 *
	 */
	public AlertCupom getAlertCupom() {
		return alertCupom;
	}
	
	/** M�todo getter para a propriedade alertCupomId
	 * 
	 *  @return Long
	 *
	 */
	public Long getAlertCupomId() {
		return alertCupomId;
	}
	
	/** M�todo getter para a propriedade cupomCode
	 * 
	 *  @return Long
	 *
	 */
	public Long getCupomCode() {
		return cupomCode;
	}
	
	/** M�todo getter para a propriedade statusGarantia
	 *
	 * @return statusGarantia do tipo StatusGarantia
	 *
	 */
	
	public StatusGarantia getStatusGarantia() {
		return statusGarantia;
	}

	
	//	----[ M�todos Setter ]---------------------------------------------------
	
	
	/** M�todo setter para a propriedade statusGarantia
	 *
	 * @param statusGarantia 
	 *       <code>statusGarantia<code> a ser designado para Cupom.java
	 *
	 */
	public void setStatusGarantia(StatusGarantia statusGarantia) {
		this.statusGarantia = statusGarantia;
	}

	/** M�todo setter para a propriedade notas
	 *
	 * @param notas 
	 *           <code>Collection</code> a ser designado para notas.
	 * 
	 */
	public void setNotas(Collection notas) {
		this.notas = notas;
	}
	
	/** M�todo setter para a propriedade analistaId
	 *
	 * @param analistaId 
	 *           <code>Long</code> a ser designado para analistaId.
	 * 
	 */
	public void setAnalistaId(Long analistaId) {
		this.analistaId = analistaId;
	}

	/** M�todo setter para a propriedade autorizacaoEsp
	 *
	 * @param autorizacaoEsp 
	 *           <code>Long</code> a ser designado para autorizacaoEsp.
	 * 
	 */
	public void setAutorizacaoEsp(Long autorizacaoEsp) {
		this.autorizacaoEsp = autorizacaoEsp;
	}

	/** M�todo setter para a propriedade chassi
	 *
	 * @param chassi 
	 *           <code>String</code> a ser designado para chassi.
	 * 
	 */
	public void setChassi(String chassi) {
		this.chassi = chassi.toUpperCase();
	}

	/** M�todo setter para a propriedade cliente
	 *
	 * @param cliente 
	 *           <code>Cliente</code> a ser designado para cliente.
	 * 
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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

	/** M�todo setter para a propriedade dataNotaFiscal
	 *
	 * @param dataNotaFiscal 
	 *           <code>Date</code> a ser designado para dataNotaFiscal.
	 * 
	 */
	public void setDataNotaFiscal(Date dataNotaFiscal) {
		this.dataNotaFiscal = dataNotaFiscal;
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

	/** M�todo setter para a propriedade lote
	 *
	 * @param lote 
	 *           <code>Lote</code> a ser designado para lote.
	 * 
	 */
	public void setLote(Lote lote) {
		this.lote = lote;
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

	/** M�todo setter para a propriedade numeroManualSubst
	 *
	 * @param numeroManualSubst 
	 *           <code>Long</code> a ser designado para numeroManualSubst.
	 * 
	 */
	public void setNumeroManualSubst(Long numeroManualSubst) {
		this.numeroManualSubst = numeroManualSubst;
	}

	/** M�todo setter para a propriedade numeroNotaFiscal
	 *
	 * @param numeroNotaFiscal 
	 *           <code>Long</code> a ser designado para numeroNotaFiscal.
	 * 
	 */
	public void setNumeroNotaFiscal(Long numeroNotaFiscal) {
		this.numeroNotaFiscal = numeroNotaFiscal;
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

	/** M�todo setter para a propriedade recusaId
	 *
	 * @param recusaId 
	 *           <code>Long</code> a ser designado para recusaId.
	 * 
	 */
	public void setRecusaId(Long recusaId) {
		this.recusaId = recusaId;
	}

	/** M�todo setter para a propriedade revisao
	 *
	 * @param revisao 
	 *           <code>Revisao</code> a ser designado para revisao.
	 * 
	 */
	public void setRevisao(Revisao revisao) {
		this.revisao = revisao;
	}

	/** M�todo setter para a propriedade serieNotaFiscal
	 *
	 * @param serieNotaFiscal 
	 *           <code>String</code> a ser designado para serieNotaFiscal.
	 * 
	 */
	public void setSerieNotaFiscal(String serieNotaFiscal) {
		this.serieNotaFiscal = serieNotaFiscal;
	}

	/** M�todo setter para a propriedade statusMovId
	 *
	 * @param statusMovId 
	 *           <code>Long</code> a ser designado para statusMovId.
	 * 
	 */
	public void setStatusMovId(Long statusMovId) {
		this.statusMovId = statusMovId;
	}

	/** M�todo setter para a propriedade tipoUsoBarco
	 *
	 * @param tipoUsoBarco 
	 *           <code>TipoUsoBarco</code> a ser designado para tipoUsoBarco.
	 * 
	 */
	public void setTipoUsoBarco(TipoUsoBarco tipoUsoBarco) {
		this.tipoUsoBarco = tipoUsoBarco;
	}

	/** M�todo setter para a propriedade valorBonificacao
	 *
	 * @param valorBonificacao 
	 *           <code>Long</code> a ser designado para valorBonificacao.
	 * 
	 */
	public void setValorBonificacao(double valorBonificacao) {
		this.valorBonificacao = valorBonificacao;
	}

	/** M�todo setter para a propriedade valorRevisao
	 *
	 * @param valorRevisao 
	 *           <code>Long</code> a ser designado para valorRevisao.
	 * 
	 */
	public void setValorRevisao(double valorRevisao) {
		this.valorRevisao = valorRevisao;
	}

	/** M�todo setter para a propriedade valorServico
	 *
	 * @param valorServico 
	 *           <code>ValorServico</code> a ser designado para valorServico.
	 * 
	 */
	public void setValorServico(ValorServico valorServico) {
		this.valorServico = valorServico;
	}

	/** M�todo setter para a propriedade compositeEntityId
	 *
	 * @param compositeEntityId 
	 *           <code>CupomId</code> a ser designado para compositeEntityId.
	 * 
	 
	public void setCompositeEntityId(CupomId compositeEntityId) {
		this.compositeEntityId = compositeEntityId;
	}*/
	
	/** M�todo setter para a propriedade listAlertCupom
	 *
	 * @param alertCupom 
	 *           <code>AlertCupom</code> a ser designado para alertCupom.
	 * 
	 */
	public void setAlertCupom(AlertCupom alertCupom) {
		this.alertCupom = alertCupom;
	}
	
	/** M�todo setter para a propriedade alertCupomId
	 *
	 * @param alertCupomId 
	 *           <code>Long</code> a ser designado para alertCupomId.
	 * 
	 */
	public void setAlertCupomId(Long alertCupomId) {
		this.alertCupomId = alertCupomId;
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

	
	//	----[ M�todos de Servi�o ]---------------------------------------------------
	
	/** Indica se esta entidade � nova (um id de valor null)  ou se j� existia 
     *  (um id cujo valor j� foi preenchido).
     *  <p/>
     *  Este m�todo � necess�rio pois o atributo "id" n�o � vis�vel para outros 
     *  objetos. E a utiliza��o do m�todo "getId" obrigaria o desenvolver a 
     *  utilizar uma cl�usula <code>if</code> um pouco mais complexa.
     *  <p/>
     *  Sendo assim, a implementa��o deste m�todo se fez necess�ria.
     * 
     *  @return boolean   
     *     Um valor booleano indicando se esta entidade � nova ou n�o.
     
    public boolean isNew() {

        if ( this.getCompositeEntityId() == null )
            return true;
        else 
            return false;

    }*/
    
    /** Retorna o Valor total da revis�o (revis�o + bonifica��o) formato para moeda
     * 
     * @return String
     *  	Soma da propriedade valorRevis�o com a prop. valorBonifica��o
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
			
			throw new BusinessException("O Objeto enviado � Nulo!" );
			
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
	
	/** Valida se o cupom pode ser removido ou n�o
	 * 
	 * @return boolean TRUE para exclus�o v�lida
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
	
	/** Valida se o cupom pode ser alterado ou n�o
	 * 
	 * @return boolean TRUE para altera��o v�lida
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

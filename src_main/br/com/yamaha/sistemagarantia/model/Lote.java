/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......Lote.java
 *   .: Criação.....23 de abril, 15:41
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Entidade "Lote".
 */
package br.com.yamaha.sistemagarantia.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import br.com.resource.infra.model.EntityObject;
import br.com.resource.infra.utils.DateUtils;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "Lote" no sistema.
 *  
 *  @author Thiago Uriel M. Garcia
 */
public class Lote extends EntityObject {

    //----[ Atributos de classe e instância ]-----------------------------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L; 
    
    /** Data do lote. */
    private Date dataLote;
    
    /** Data de fechamento. */
    private Date dataFechamento;
    
    /** Data de liberação. */
    private Date dataLiberacao;
    
    /** Data de dataEnvioAdiant. */
    private Date dataEnvioAdiant;
    
    /** Status Adiantamento. */
    private StatusAdiantamento statusAdiantamento;
    
    /** Coleção de Cupons do lote */
    private List cupons;
    
    /** Coleção de Garantias do lote */
    private List garantias;
    
    /** Coleção de notas do lote */
    private List notas;
    
    /** Objeto linha relacionado ao lote */
    private Linha linha;
    
    /** Objeto StatusLote relacionado ao Lote */
    private StatusLote statusLote;
    
    /** Objeto TipoLote relacionado ao Lote */
    private TipoLote tipoLote;
    
    /** Objeto Concessionaria relacionado ao Lote */
    private Concessionaria concessionaria;
    
    
    //----[ Métodos Getter ]---------------------------------------------------
    
    /** Método getter para "dataFechamento".
     *  @return
     *      <code>Date</code>. O valor atual de dataFechamento.
     */
    public Date getDataFechamento() {
        return this.dataFechamento;
    }    
    
    /** Método getter para "dataFechamento".
     *  @return
     *      <code>String</code>. O valor atual de dataFechamento,
     *      com uma máscara aplicada.
     */
    public String getMaskedDataFechamento() {
        return DateUtils.applyMask(this.dataFechamento);
    }    
   
    /** Método getter para "dataLiberacao".
     *  @return
     *      <code>Date</code>. O valor atual de dataLiberacao.
     */
    public Date getDataLiberacao() {
        return this.dataLiberacao;
    }    
    
    /** Método getter para "dataLiberacao".
     *  @return
     *      <code>String</code>. O valor atual de dataLiberacao.
     */
    public String getMaskedDataLiberacao() {
        return DateUtils.applyMask(this.dataLiberacao);
    }

    /** Método getter para "dataLote".
     *  @return
     *      <code>Date</code>. O valor atual de dataLote.
     */
    public Date getDataLote() {
        return this.dataLote;
    }    
    
    /** Método getter para "dataLote".
     *  @return
     *      <code>String</code>. O valor atual de dataLote.
     */
    public String getMaskedDataLote() {
        return DateUtils.applyMask(this.dataLote);
    }

    /** Método getter para o campo linha
	 * 
	 * @return Linha
	 *
	 */
	public Linha getLinha() {
		return linha;
	}

	/** Método getter para o campo statusLote
	 * 
	 * @return StatusLote
	 *
	 */
	public StatusLote getStatusLote() {
		return statusLote;
	}

	/** Método getter para o campo tipoLote
	 * 
	 * @return TipoLote
	 *
	 */
	public TipoLote getTipoLote() {
		return tipoLote;
	}


	/** Método getter para o campo concessionaria
	 * 
	 * @return Concessionaria
	 *
	 */
	public Concessionaria getConcessionaria() {
		return concessionaria;
	}	
	
	/** Método getter para a propriedade cupons
	 * 
	 *  @return List
	 *
	 */
	public List getCupons() {
		if ( this.tipoLote.getEntityId().equals(TipoLote.TIPO_REVISAO))
			return cupons;
		else
			return new ArrayList();
	}
	
	/** Método getter para a propriedade garantias
	 * 
	 *  @return List
	 *
	 */
	public List getGarantias() {
		if ( this.tipoLote.getEntityId().equals(TipoLote.TIPO_GARANTIA))
			return garantias;
		else
			return new ArrayList();
	}	
	
	
	/** Método getter para a propriedade statusAdiantamento
	 * 
	 *  @return StatusAdiantamento
	 *
	 */
	public StatusAdiantamento getStatusAdiantamento() {
		return statusAdiantamento;
	}
	
	/** Método getter para a propriedade notas
	 * 
	 *  @return List
	 *
	 */
	public List getNotas() {
		return notas;
	}
	
	/** Método getter para a propriedade dataEnvioAdiant
	 *
	 *  @return Date de dataEnvioAdiant
	 */
	public Date getDataEnvioAdiant() {
		return dataEnvioAdiant;
	}
	
    //----[ Métodos Setter ]---------------------------------------------------
    
   
	/** Método setter para "dataFechamento".
     *  @param entityId
     *      <code>dataFechamento</code> a ser designado para dataFechamento.
     */
    public void setDataFechamento(Object dataFechamento) {
        this.dataFechamento = DateUtils.secureSet(dataFechamento);        
    }

    /** Método setter para "dataLiberacao".
     *  @param entityId
     *      <code>Object</code> a ser designado para dataLiberacao.
     */
    public void setDataLiberacao(Object dataLiberacao) {
        this.dataLiberacao = DateUtils.secureSet(dataLiberacao);
    }

    /** Método setter para "dataLote".
     *  @param entityId
     *      <code>Object</code> a ser designado para dataLote.
     */
    public void setDataLote(Object dataLote) {
        this.dataLote = DateUtils.secureSet(dataLote);
    }

 	/** Método setter para o campo linha
	 *
	 * @param linha  Linha
	 * 
	 */
	public void setLinha(Linha linha) {
		this.linha = linha;
	}

	/** Método setter para o campo statusLote
	 *
	 * @param statusLote  StatusLote
	 * 
	 */
	public void setStatusLote(StatusLote statusLote) {
		this.statusLote = statusLote;
	}

	/** Método setter para o campo tipoLote
	 *
	 * @param tipoLote  TipoLote
	 * 
	 */
	public void setTipoLote(TipoLote tipoLote) {
		this.tipoLote = tipoLote;
	}

	/** Método setter para o campo concessionaria
	 *
	 * @param concessionaria  Concessionaria
	 * 
	 */
	public void setConcessionaria(Concessionaria concessionaria) {
		this.concessionaria = concessionaria;
	}
	
	/** Método setter para a propriedade cupons
	 *
	 * @param cupons 
	 *           <code>List</code> a ser designado para cupons.
	 * 
	 */
	public void setCupons(List cupons) {
		this.cupons = cupons;
	}

	/** Método setter para a propriedade garantias
	 *
	 * @param garantias 
	 *           <code>List</code> a ser designado para garantias.
	 * 
	 */
	public void setGarantias(List garantias) {
		this.garantias = garantias;
	}
	
	/** Método setter para a propriedade statusAdiantamento
	 *
	 * @param statusAdiantamento 
	 *           <code>StatusAdiantamento</code> a ser designado para statusAdiantamento.
	 * 
	 */
	public void setStatusAdiantamento(StatusAdiantamento statusAdiantamento) {
		this.statusAdiantamento = statusAdiantamento;
	}

	/** Método setter para a propriedade notas
	 *
	 * @param notas 
	 *           <code>List</code> a ser designado para notas.
	 * 
	 */
	public void setNotas(List notas) {
		this.notas = notas;
	}
	
	/** Adiciona uma nota fiscal a lista de notas do lote
	 * 
	 * @param notaFiscal
	 */
	public void setNotas(NotaFiscal notaFiscal) {
		
		this.notas.add(notaFiscal);
		
	}

	/** Método setter para a propriedade dataEnvioAdiant
	 *
	 * @param dataEnvioAdiant Date
	 */
	public void setDataEnvioAdiant(Date dataEnvioAdiant) {
		this.dataEnvioAdiant = dataEnvioAdiant;
	}
	
	//	----[ Métodos Utils ]---------------------------------------------------
	
	
	/** Verifica se existe movimento de cupom ou garantia
	 * 
	 * @return boolean
	 *  <code>true</code> quando existe movimento
	 */
	public boolean getIsMovimento() {
		
		if ( this.cupons.size() > 0 || this.garantias.size() > 0 )
			return true;
		else
			return false;		
		
	}
	
	
	/**
	 *  Verifica se o lote permite inclusão de item (Revisão e Garantia)
	 *  
	 *  
	 *  @return boolean   TRUE se for permitido incluir
	 */
	public boolean getValidInclude() {
		
		boolean isInclude      = false;
		// A inclusão será permitida apenas quando o lote estiver com status de "EM DIGITAÇÃO" e estiver vazio
		if ( this.getStatusLote().getEntityId().equals( StatusLote.STATUS_EM_DIGITACAO ) && this.getDataEnvioAdiant() == null ) {
			isInclude = true;			
		}		
		return isInclude;
	}
	
	/**
	 *  Verifica se o lote permite alteração nos itens (Revisão e Garantia)
	 *   
	 *  O lote só poderá ser cancelado quando seu status estiver com EM DIGITAÇÃO
	 *  e não foi dado adiantamento ou com status EM MANUTENÇÃO  
	 *  
	 *  @return boolean   TRUE se for permitido alterar
	 */
	public boolean getValidAlter() {
		
		boolean isAlter = false;
		
		// É Permita a alteração quando o lote estiver com status de "Em Digitação"
		// OU "Manutenção"
		if ( (  this.getStatusLote().getEntityId().equals( StatusLote.STATUS_EM_DIGITACAO ) 
			 && this.getDataEnvioAdiant() == null ) 
			 || this.getStatusLote().getEntityId().equals( StatusLote.STATUS_MANUTENCAO   )
			) {
			
			isAlter = true;
			
		}
		
		return isAlter;
		
	}
	
	/**
	 *  Verifica se o lote permite alteração nos itens (Revisão e Garantia/recall/campanha)
	 *   
	 *  O lote só poderá ser cancelado quando seu status estiver com EM DIGITAÇÃO
	 *  e não foi dado adiantamento ou com status EM MANUTENÇÃO  
	 *  
	 *  @return boolean   TRUE se for permitido alterar
	 */
	public boolean getValidAlterNew() {
		
		boolean isAlter = false;
		
		// É Permita a alteração quando o lote estiver com status de "Em Digitação"
		// OU "Manutenção"
		if ( (  this.getStatusLote().getEntityId().equals( StatusLote.STATUS_EM_DIGITACAO ) 
			 && this.getDataEnvioAdiant() == null ) 
			 || this.getStatusLote().getEntityId().equals( StatusLote.STATUS_MANUTENCAO   )
			) {
			
			isAlter = true;
			
		} else {
			
			if ( StatusLote.STATUS_AGUARDANDO_DIGITACAO_NOTA.equals(this.getStatusLote().getEntityId())) {				
				
				if(this.getTipoLote().equals(TipoLote.TIPO_GARANTIA)) {
					if(this.garantias.size() > 0) {
						
						Iterator it = this.garantias.iterator();
						GarantiaHeader sg = null;
						while(it.hasNext()){
							sg = (GarantiaHeader) it.next();
							if(StatusGarantia.STATUS_AGUARDANDO_DIGITACAO_NOTA.equals(sg.getStatusGarantia().getEntityId()) && sg.getHasCampaign())
								isAlter = true;
						}
					}
				} 
			} 			
		}
		
		return isAlter;
		
	}
	
	/** Retorna um valor true se o lote está liberado para alteração e cancelamento
	 * 
	 * @return boolean
	 */
	public boolean getHasAprovAut() {
		
		boolean hasAprovAut = false;
		
		if ( Concessionaria.FLAG_APROVA_YES.equals(concessionaria.getFlagAprovacaoAutom()) ) {	
				
			hasAprovAut = true;
			
		}
		
		return hasAprovAut;		
		
	}
	
	/** Verifica se o Lote pode ser liberado de acordo com seu status e 
	 *  se existe itens lançados
	 * 
	 * @return true  para liberação permitida 
	 * 	<code>boolean</code> 
	 */
	public boolean getReleaseValid() {
		
		boolean releaseValid = false;
		
		if ( StatusLote.STATUS_EM_DIGITACAO.equals(this.getStatusLote().getEntityId()) ||  StatusLote.STATUS_MANUTENCAO.equals(this.getStatusLote().getEntityId()) || StatusLote.STATUS_NF_DIVERGENTE.equals(this.getStatusLote().getEntityId())) {				
					
			if(this.getTipoLote().equals(TipoLote.TIPO_GARANTIA)) {
				if(this.garantias.size() > 0) {
					
					Iterator it = this.garantias.iterator();
					GarantiaHeader sg = null;
					int nlib = 0;
					while(it.hasNext()){
						sg = (GarantiaHeader) it.next();
						if(StatusGarantia.STATUS_DIGITACAO.equals(sg.getStatusGarantia().getEntityId())
								|| StatusGarantia.STATUS_MANUTENCAO.equals(sg.getStatusGarantia().getEntityId())
								|| StatusGarantia.STATUS_NF_DIVERGENTE.equals(sg.getStatusGarantia().getEntityId())								
							)
							nlib++;
					}
					
					if(nlib == this.garantias.size())
						releaseValid = true;
				}
				
			} else if ( this.cupons.size() > 0 ){
				releaseValid = true;			 
			}
		} 
		
		return releaseValid;		
		
	}
	
	/** Verifica se o Lote pode ser cancelado de acordo com seu status
	 *  O lote só poderá ser cancelado quando seu status estiver com EM DIGITAÇÃO
	 *  e não foi dado adiantamento
	 *  
	 * @return true  para cancelamento permitido
	 * 	<code>boolean</code> 
	 */
	public boolean getCancelValid() {
		
		boolean cancelValid = false;
		
		if ( StatusLote.STATUS_EM_DIGITACAO.equals( this.getStatusLote().getEntityId()) 
			 &&	this.getDataEnvioAdiant() == null  
			) {	
				
			cancelValid = true;
			
		}
		
		return cancelValid;		
		
	}
	
}
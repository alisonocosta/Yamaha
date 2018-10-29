/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioDocRevisaoVO.java
 *   .: Cria��o.....07 de agosto, 09:25
 *   .: Autor.......Gisele Panosso
 *   .: Descri��o...Entidade "RelatorioDocRevisaoVO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.resource.infra.model.EntityObject;
import br.com.resource.infra.utils.NumberUtils;

public class RelatorioDocRevisaoVO extends EntityObject {
	
	//----[ Atributos de classe e inst�ncia ]---------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;    
    
    /** Armazena o campo linha do Relat�rio de Protocolo de Documentos de Revis�o. */
    private String linha;    
    
    /** Armazena o campo lote do Relat�rio de Protocolo de Documentos de Revis�o. */
    private BigDecimal lote;
    
    /** Armazena o campo statusLote do Relat�rio de Protocolo de Documentos de Revis�o. */
    private String statusLote;
    
    /** Armazena o campo statusLoteId do Relat�rio de Protocolo de Documentos de Revis�o. */
    private BigDecimal statusLoteId;
    
    /** Armazena o campo numeroNf do Relat�rio de Protocolo de Documentos de Revis�o. */
    private BigDecimal numeroNf;
    
    /** Armazena o campo serieNf do Relat�rio de Protocolo de Documentos de Revis�o. */
    private String serieNf;
    
    /** Armazena o campo tipoNf do Relat�rio de Protocolo de Documentos de Revis�o. */
    private BigDecimal tipoNf;
    
    /** Armazena o campo descri��o do tipo de NF do Relat�rio Solicita��o Garantia */
    private String descrTipo;
    
    /** Armazena o campo dataNf do Relat�rio de Protocolo de Documentos de Revis�o. */
    private Date dataNf;
    
    /** Armazena o campo valorNf do Relat�rio de Protocolo de Documentos de Revis�o. */
    private BigDecimal valorNf;
    
    /** Armazena o campo emitente do Relat�rio de Protocolo de Documentos de Revis�o. */
    private String emitente;
    
    /** Armazena o campo destinatario do Relat�rio de Protocolo de Documentos de Revis�o. */
    private String destinatario;
    
    /** Armazena o campo numeroCp do Relat�rio de Protocolo de Documentos de Revis�o. */
    private BigDecimal numeroCp;
    
    /** Armazena o campo serieCp do Relat�rio de Protocolo de Documentos de Revis�o. */
    private String serieCp;
    
    /** Armazena o campo dataCp do Relat�rio de Protocolo de Documentos de Revis�o. */
    private Date dataCp;
   
    /** Armazena o campo valorCp do Relat�rio de Protocolo de Documentos de Revis�o. */
    private BigDecimal valorCp;
    
    /** Armazena o campo nomeConc do Relat�rio de Protocolo de Documentos de Revis�o. */
    private String nomeConc;
    
    /** Armazena o campo enderecoConc do Relat�rio de Protocolo de Documentos de Revis�o. */
    private String enderecoConc;
    
    /** Armazena o campo cidadeConc do Relat�rio de Protocolo de Documentos de Revis�o. */
    private String cidadeConc;
    
    /** Armazena o campo cepConc do Relat�rio de Protocolo de Documentos de Revis�o. */
    private BigDecimal cepConc;
    
    /** Armazena o campo estadoConc do Relat�rio de Protocolo de Documentos de Revis�o. */
    private String estadoConc;
 
    /** Armazena o campo cnpjConc do Relat�rio de Protocolo de Documentos de Revis�o. */
    private BigDecimal cnpjConc;
    
    /** Armazena o campo empresa do Relat�rio de Protocolo de Documentos de Revis�o. */
    private String empresa; 
    
    /** Armazena o campo malote do Relat�rio de Protocolo de Documentos de Revis�o. */
    private String malote;
    
    /** Armazena o campo codigoConcesssionaria do Relat�rio de Protocolo de Documentos de Revis�o. */
    private String codigoConcessionaria;
    
    /** Armazena o campo listRevisoes (lista de Revis�es) do Relat�rio de Protocolo de Documentos de Revis�o. */
    private List listRevisoes;
    

    //	----[ M�todo Add ]---------------------------------------------------  

	/** M�todo construtor.
     */
	public RelatorioDocRevisaoVO() {
		super();
		listRevisoes = new ArrayList();
	}
	
	/** Adiciona uma lista de Revis�es no relat�rio Protocolo de Documentos de Revis�.
     * 
     *  @param listRevisoes
     *      listRevisoes a ser adicionado.
     *  
     */
    public void addListRevisoes(RelatorioDocRevisaoDetalheVO listRevisoes) {
    	
        this.listRevisoes.add( listRevisoes );
        
    }
    

    //	----[ M�todos Getter ]---------------------------------------------------

	/** M�todo getter para a propriedade cepConc.
	 *
	 *  @return o valor atual de cepConc.
	 */
	public BigDecimal getCepConc() {
		return cepConc;
	}

	/** M�todo getter para a propriedade cidadeConc.
	 *
	 *  @return o valor atual de cidadeConc.
	 */
	public String getCidadeConc() {
		return cidadeConc;
	}

	/** M�todo getter para a propriedade cnpjConc.
	 *
	 *  @return o valor atual de cnpjConc.
	 */
	public BigDecimal getCnpjConc() {
		return cnpjConc;
	}

	/** M�todo getter para a propriedade dataCp.
	 *
	 *  @return o valor atual de dataCp.
	 */
	public Date getDataCp() {
		return dataCp;
	}

	/** M�todo getter para a propriedade dataNf.
	 *
	 *  @return o valor atual de dataNf.
	 */
	public Date getDataNf() {
		return dataNf;
	}

	/** M�todo getter para a propriedade destinatario.
	 *
	 *  @return o valor atual de destinatario.
	 */
	public String getDestinatario() {
		return destinatario;
	}

	/** M�todo getter para a propriedade emitente.
	 *
	 *  @return o valor atual de emitente.
	 */
	public String getEmitente() {
		return emitente;
	}

	/** M�todo getter para a propriedade enderecoConc.
	 *
	 *  @return o valor atual de enderecoConc.
	 */
	public String getEnderecoConc() {
		return enderecoConc;
	}

	/** M�todo getter para a propriedade estadoConc.
	 *
	 *  @return o valor atual de estadoConc.
	 */
	public String getEstadoConc() {
		return estadoConc;
	}

	/** M�todo getter para a propriedade linha.
	 *
	 *  @return o valor atual de linha.
	 */
	public String getLinha() {
		return linha;
	}

	/** M�todo getter para a propriedade listRevisoes.
	 *
	 *  @return o valor atual de listRevisoes.
	 */
	public List getListRevisoes() {
		return listRevisoes;
	}

	/** M�todo getter para a propriedade lote.
	 *
	 *  @return o valor atual de lote.
	 */
	public BigDecimal getLote() {
		return lote;
	}

	/** M�todo getter para a propriedade nomeConc.
	 *
	 *  @return o valor atual de nomeConc.
	 */
	public String getNomeConc() {
		return nomeConc;
	}

	/** M�todo getter para a propriedade numeroCp.
	 *
	 *  @return o valor atual de numeroCp.
	 */
	public BigDecimal getNumeroCp() {
		return numeroCp;
	}

	/** M�todo getter para a propriedade numeroNf.
	 *
	 *  @return o valor atual de numeroNf.
	 */
	public BigDecimal getNumeroNf() {
		return numeroNf;
	}

	/** M�todo getter para a propriedade serieCp.
	 *
	 *  @return o valor atual de serieCp.
	 */
	public String getSerieCp() {
		return serieCp;
	}

	/** M�todo getter para a propriedade serieNf.
	 *
	 *  @return o valor atual de serieNf.
	 */
	public String getSerieNf() {
		return serieNf;
	}

	/** M�todo getter para a propriedade statusLote.
	 *
	 *  @return o valor atual de statusLote.
	 */
	public String getStatusLote() {
		return statusLote;
	}

	/** M�todo getter para a propriedade tipoNf.
	 *
	 *  @return o valor atual de tipoNf.
	 */
	public BigDecimal getTipoNf() {
		return tipoNf;
	}

	/** M�todo getter para a propriedade valorCp.
	 *
	 *  @return o valor atual de valorCp.
	 */
	public BigDecimal getValorCp() {
		return valorCp;
	}

	/** M�todo getter para a propriedade valorNf.
	 *
	 *  @return o valor atual de valorNf.
	 */
	public BigDecimal getValorNf() {
		return valorNf;
	}
	   
	/** M�todo getter para a propriedade empresa.
	 *
	 *  @return o valor atual de empresa.
	 */
	public String getEmpresa() {
		return empresa;
	}

	/** M�todo getter para a propriedade descrTipo
	 * 
	 *  @return String
	 *
	 */
	public String getDescrTipo() {
		return descrTipo;
	}

	/** M�todo getter para a propriedade statusLoteId
	 *
	 *  @return BigDecimal de statusLoteId
	 */
	public BigDecimal getStatusLoteId() {
		return statusLoteId;
	}
	
	/** M�todo getter para a propriedade malote
	 *
	 *  @return String de malote
	 */
	public String getMalote() {
		return malote;
	}
	
	/** M�todo getter para a propriedade codigoConcesssionaria
	 *
	 *  @return String de codigoConcesssionaria
	 */
	public String getCodigoConcessionaria() {
		return codigoConcessionaria;
	}

    //	----[ M�todos Setter ]---------------------------------------------------

	/** Obt�m o valor atual de cepConc.
	 * 
	 *  @param cepConc 
	 *    O novo valor para cepConc.
	 */
	public void setCepConc(BigDecimal cepConc) {
		this.cepConc = cepConc;
	}

	/** Obt�m o valor atual de cidadeConc.
	 * 
	 *  @param cidadeConc 
	 *    O novo valor para cidadeConc.
	 */
	public void setCidadeConc(String cidadeConc) {
		this.cidadeConc = cidadeConc;
	}

	/** Obt�m o valor atual de cnpjConc.
	 * 
	 *  @param cnpjConc 
	 *    O novo valor para cnpjConc.
	 */
	public void setCnpjConc(BigDecimal cnpjConc) {
		this.cnpjConc = cnpjConc;
	}

	/** Obt�m o valor atual de dataCp.
	 * 
	 *  @param dataCp 
	 *    O novo valor para dataCp.
	 */
	public void setDataCp(Date dataCp) {
		this.dataCp = dataCp;
	}

	/** Obt�m o valor atual de dataNf.
	 * 
	 *  @param dataNf 
	 *    O novo valor para dataNf.
	 */
	public void setDataNf(Date dataNf) {
		this.dataNf = dataNf;
	}

	/** Obt�m o valor atual de destinatario.
	 * 
	 *  @param destinatario 
	 *    O novo valor para destinatario.
	 */
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	/** Obt�m o valor atual de emitente.
	 * 
	 *  @param emitente 
	 *    O novo valor para emitente.
	 */
	public void setEmitente(String emitente) {
		this.emitente = emitente;
	}

	/** Obt�m o valor atual de enderecoConc.
	 * 
	 *  @param enderecoConc 
	 *    O novo valor para enderecoConc.
	 */
	public void setEnderecoConc(String enderecoConc) {
		this.enderecoConc = enderecoConc;
	}

	/** Obt�m o valor atual de estadoConc.
	 * 
	 *  @param estadoConc 
	 *    O novo valor para estadoConc.
	 */
	public void setEstadoConc(String estadoConc) {
		this.estadoConc = estadoConc;
	}

	/** Obt�m o valor atual de linha.
	 * 
	 *  @param linha 
	 *    O novo valor para linha.
	 */
	public void setLinha(String linha) {
		this.linha = linha;
	}

	/** Obt�m o valor atual de listRevisoes.
	 * 
	 *  @param listRevisoes 
	 *    O novo valor para listRevisoes.
	 */
	public void setListRevisoes(List listRevisoes) {
		this.listRevisoes = listRevisoes;
	}

	/** Obt�m o valor atual de lote.
	 * 
	 *  @param lote 
	 *    O novo valor para lote.
	 */
	public void setLote(BigDecimal lote) {
		this.lote = lote;
	}

	/** Obt�m o valor atual de nomeConc.
	 * 
	 *  @param nomeConc 
	 *    O novo valor para nomeConc.
	 */
	public void setNomeConc(String nomeConc) {
		this.nomeConc = nomeConc;
	}

	/** Obt�m o valor atual de numeroCp.
	 * 
	 *  @param numeroCp 
	 *    O novo valor para numeroCp.
	 */
	public void setNumeroCp(BigDecimal numeroCp) {
		this.numeroCp = numeroCp;
	}

	/** Obt�m o valor atual de numeroNf.
	 * 
	 *  @param numeroNf 
	 *    O novo valor para numeroNf.
	 */
	public void setNumeroNf(BigDecimal numeroNf) {
		this.numeroNf = numeroNf;
	}

	/** Obt�m o valor atual de serieCp.
	 * 
	 *  @param serieCp 
	 *    O novo valor para serieCp.
	 */
	public void setSerieCp(String serieCp) {
		this.serieCp = serieCp;
	}

	/** Obt�m o valor atual de serieNf.
	 * 
	 *  @param serieNf 
	 *    O novo valor para serieNf.
	 */
	public void setSerieNf(String serieNf) {
		this.serieNf = serieNf;
	}

	/** Obt�m o valor atual de statusLote.
	 * 
	 *  @param statusLote 
	 *    O novo valor para statusLote.
	 */
	public void setStatusLote(String statusLote) {
		this.statusLote = statusLote;
	}

	/** Obt�m o valor atual de tipoNf.
	 * 
	 *  @param tipoNf 
	 *    O novo valor para tipoNf.
	 */
	public void setTipoNf(BigDecimal tipoNf) {
		this.tipoNf = tipoNf;
	}

	/** Obt�m o valor atual de valorCp.
	 * 
	 *  @param valorCp 
	 *    O novo valor para valorCp.
	 */
	public void setValorCp(BigDecimal valorCp) {
		this.valorCp = valorCp;
	}

	/** Obt�m o valor atual de valorNf.
	 * 
	 *  @param valorNf 
	 *    O novo valor para valorNf.
	 */
	public void setValorNf(BigDecimal valorNf) {
		this.valorNf = valorNf;
	}

	/** Obt�m o valur atual de empresa.
	 * 
	 *  @param empresa 
	 *    O novo valor para empresa.
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/** M�todo setter para a propriedade descrTipo
	 *
	 * @param descrTipo 
	 *           <code>String</code> a ser designado para descrTipo.
	 * 
	 */
	public void setDescrTipo(String descrTipo) {
		this.descrTipo = descrTipo;
	}
    
	
	/** M�todo getter retornar o valorNf como string e formatado para moeda pt_BR.
	 *
	 *  @return o valor atual de valorNf.
	 */
	public String getStrValorNf() {
		if ( valorNf != null )
			return NumberUtils.formatNumberCurrencyMil(valorNf.doubleValue());
		else
			return "";
	}
	
	/** M�todo getter para a propriedade valorCp formatado para moeda pt_BR.
	 *
	 *  @return o valor atual de valorCp.
	 */
	public String getStrValorCp() {
		
		if ( valorCp != null )
			return NumberUtils.formatNumberCurrencyMil(valorCp.doubleValue());
		else
			return "";
	}

	/** M�todo setter para a propriedade statusLoteId
	 *
	 * @param statusLoteId BigDecimal
	 */
	public void setStatusLoteId(BigDecimal statusLoteId) {
		this.statusLoteId = statusLoteId;
	}

	/** M�todo setter para a propriedade malote
	 *
	 * @param malote String
	 */
	public void setMalote(String malote) {
		this.malote = malote;
	}

	

	/** M�todo setter para a propriedade codigoConcessionaria
	 *
	 * @param codigoConcessionaria String
	 */
	public void setCodigoConcessionaria(String codigoConcessionaria) {
		this.codigoConcessionaria = codigoConcessionaria;
	}
	
	
 
}

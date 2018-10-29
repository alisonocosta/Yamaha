/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioDocRevisaoVO.java
 *   .: Criação.....07 de agosto, 09:25
 *   .: Autor.......Gisele Panosso
 *   .: Descrição...Entidade "RelatorioDocRevisaoVO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.resource.infra.model.EntityObject;
import br.com.resource.infra.utils.NumberUtils;

public class RelatorioDocRevisaoVO extends EntityObject {
	
	//----[ Atributos de classe e instância ]---------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;    
    
    /** Armazena o campo linha do Relatório de Protocolo de Documentos de Revisão. */
    private String linha;    
    
    /** Armazena o campo lote do Relatório de Protocolo de Documentos de Revisão. */
    private BigDecimal lote;
    
    /** Armazena o campo statusLote do Relatório de Protocolo de Documentos de Revisão. */
    private String statusLote;
    
    /** Armazena o campo statusLoteId do Relatório de Protocolo de Documentos de Revisão. */
    private BigDecimal statusLoteId;
    
    /** Armazena o campo numeroNf do Relatório de Protocolo de Documentos de Revisão. */
    private BigDecimal numeroNf;
    
    /** Armazena o campo serieNf do Relatório de Protocolo de Documentos de Revisão. */
    private String serieNf;
    
    /** Armazena o campo tipoNf do Relatório de Protocolo de Documentos de Revisão. */
    private BigDecimal tipoNf;
    
    /** Armazena o campo descrição do tipo de NF do Relatório Solicitação Garantia */
    private String descrTipo;
    
    /** Armazena o campo dataNf do Relatório de Protocolo de Documentos de Revisão. */
    private Date dataNf;
    
    /** Armazena o campo valorNf do Relatório de Protocolo de Documentos de Revisão. */
    private BigDecimal valorNf;
    
    /** Armazena o campo emitente do Relatório de Protocolo de Documentos de Revisão. */
    private String emitente;
    
    /** Armazena o campo destinatario do Relatório de Protocolo de Documentos de Revisão. */
    private String destinatario;
    
    /** Armazena o campo numeroCp do Relatório de Protocolo de Documentos de Revisão. */
    private BigDecimal numeroCp;
    
    /** Armazena o campo serieCp do Relatório de Protocolo de Documentos de Revisão. */
    private String serieCp;
    
    /** Armazena o campo dataCp do Relatório de Protocolo de Documentos de Revisão. */
    private Date dataCp;
   
    /** Armazena o campo valorCp do Relatório de Protocolo de Documentos de Revisão. */
    private BigDecimal valorCp;
    
    /** Armazena o campo nomeConc do Relatório de Protocolo de Documentos de Revisão. */
    private String nomeConc;
    
    /** Armazena o campo enderecoConc do Relatório de Protocolo de Documentos de Revisão. */
    private String enderecoConc;
    
    /** Armazena o campo cidadeConc do Relatório de Protocolo de Documentos de Revisão. */
    private String cidadeConc;
    
    /** Armazena o campo cepConc do Relatório de Protocolo de Documentos de Revisão. */
    private BigDecimal cepConc;
    
    /** Armazena o campo estadoConc do Relatório de Protocolo de Documentos de Revisão. */
    private String estadoConc;
 
    /** Armazena o campo cnpjConc do Relatório de Protocolo de Documentos de Revisão. */
    private BigDecimal cnpjConc;
    
    /** Armazena o campo empresa do Relatório de Protocolo de Documentos de Revisão. */
    private String empresa; 
    
    /** Armazena o campo malote do Relatório de Protocolo de Documentos de Revisão. */
    private String malote;
    
    /** Armazena o campo codigoConcesssionaria do Relatório de Protocolo de Documentos de Revisão. */
    private String codigoConcessionaria;
    
    /** Armazena o campo listRevisoes (lista de Revisões) do Relatório de Protocolo de Documentos de Revisão. */
    private List listRevisoes;
    

    //	----[ Método Add ]---------------------------------------------------  

	/** Método construtor.
     */
	public RelatorioDocRevisaoVO() {
		super();
		listRevisoes = new ArrayList();
	}
	
	/** Adiciona uma lista de Revisões no relatório Protocolo de Documentos de Revisã.
     * 
     *  @param listRevisoes
     *      listRevisoes a ser adicionado.
     *  
     */
    public void addListRevisoes(RelatorioDocRevisaoDetalheVO listRevisoes) {
    	
        this.listRevisoes.add( listRevisoes );
        
    }
    

    //	----[ Métodos Getter ]---------------------------------------------------

	/** Método getter para a propriedade cepConc.
	 *
	 *  @return o valor atual de cepConc.
	 */
	public BigDecimal getCepConc() {
		return cepConc;
	}

	/** Método getter para a propriedade cidadeConc.
	 *
	 *  @return o valor atual de cidadeConc.
	 */
	public String getCidadeConc() {
		return cidadeConc;
	}

	/** Método getter para a propriedade cnpjConc.
	 *
	 *  @return o valor atual de cnpjConc.
	 */
	public BigDecimal getCnpjConc() {
		return cnpjConc;
	}

	/** Método getter para a propriedade dataCp.
	 *
	 *  @return o valor atual de dataCp.
	 */
	public Date getDataCp() {
		return dataCp;
	}

	/** Método getter para a propriedade dataNf.
	 *
	 *  @return o valor atual de dataNf.
	 */
	public Date getDataNf() {
		return dataNf;
	}

	/** Método getter para a propriedade destinatario.
	 *
	 *  @return o valor atual de destinatario.
	 */
	public String getDestinatario() {
		return destinatario;
	}

	/** Método getter para a propriedade emitente.
	 *
	 *  @return o valor atual de emitente.
	 */
	public String getEmitente() {
		return emitente;
	}

	/** Método getter para a propriedade enderecoConc.
	 *
	 *  @return o valor atual de enderecoConc.
	 */
	public String getEnderecoConc() {
		return enderecoConc;
	}

	/** Método getter para a propriedade estadoConc.
	 *
	 *  @return o valor atual de estadoConc.
	 */
	public String getEstadoConc() {
		return estadoConc;
	}

	/** Método getter para a propriedade linha.
	 *
	 *  @return o valor atual de linha.
	 */
	public String getLinha() {
		return linha;
	}

	/** Método getter para a propriedade listRevisoes.
	 *
	 *  @return o valor atual de listRevisoes.
	 */
	public List getListRevisoes() {
		return listRevisoes;
	}

	/** Método getter para a propriedade lote.
	 *
	 *  @return o valor atual de lote.
	 */
	public BigDecimal getLote() {
		return lote;
	}

	/** Método getter para a propriedade nomeConc.
	 *
	 *  @return o valor atual de nomeConc.
	 */
	public String getNomeConc() {
		return nomeConc;
	}

	/** Método getter para a propriedade numeroCp.
	 *
	 *  @return o valor atual de numeroCp.
	 */
	public BigDecimal getNumeroCp() {
		return numeroCp;
	}

	/** Método getter para a propriedade numeroNf.
	 *
	 *  @return o valor atual de numeroNf.
	 */
	public BigDecimal getNumeroNf() {
		return numeroNf;
	}

	/** Método getter para a propriedade serieCp.
	 *
	 *  @return o valor atual de serieCp.
	 */
	public String getSerieCp() {
		return serieCp;
	}

	/** Método getter para a propriedade serieNf.
	 *
	 *  @return o valor atual de serieNf.
	 */
	public String getSerieNf() {
		return serieNf;
	}

	/** Método getter para a propriedade statusLote.
	 *
	 *  @return o valor atual de statusLote.
	 */
	public String getStatusLote() {
		return statusLote;
	}

	/** Método getter para a propriedade tipoNf.
	 *
	 *  @return o valor atual de tipoNf.
	 */
	public BigDecimal getTipoNf() {
		return tipoNf;
	}

	/** Método getter para a propriedade valorCp.
	 *
	 *  @return o valor atual de valorCp.
	 */
	public BigDecimal getValorCp() {
		return valorCp;
	}

	/** Método getter para a propriedade valorNf.
	 *
	 *  @return o valor atual de valorNf.
	 */
	public BigDecimal getValorNf() {
		return valorNf;
	}
	   
	/** Método getter para a propriedade empresa.
	 *
	 *  @return o valor atual de empresa.
	 */
	public String getEmpresa() {
		return empresa;
	}

	/** Método getter para a propriedade descrTipo
	 * 
	 *  @return String
	 *
	 */
	public String getDescrTipo() {
		return descrTipo;
	}

	/** Método getter para a propriedade statusLoteId
	 *
	 *  @return BigDecimal de statusLoteId
	 */
	public BigDecimal getStatusLoteId() {
		return statusLoteId;
	}
	
	/** Método getter para a propriedade malote
	 *
	 *  @return String de malote
	 */
	public String getMalote() {
		return malote;
	}
	
	/** Método getter para a propriedade codigoConcesssionaria
	 *
	 *  @return String de codigoConcesssionaria
	 */
	public String getCodigoConcessionaria() {
		return codigoConcessionaria;
	}

    //	----[ Métodos Setter ]---------------------------------------------------

	/** Obtém o valor atual de cepConc.
	 * 
	 *  @param cepConc 
	 *    O novo valor para cepConc.
	 */
	public void setCepConc(BigDecimal cepConc) {
		this.cepConc = cepConc;
	}

	/** Obtém o valor atual de cidadeConc.
	 * 
	 *  @param cidadeConc 
	 *    O novo valor para cidadeConc.
	 */
	public void setCidadeConc(String cidadeConc) {
		this.cidadeConc = cidadeConc;
	}

	/** Obtém o valor atual de cnpjConc.
	 * 
	 *  @param cnpjConc 
	 *    O novo valor para cnpjConc.
	 */
	public void setCnpjConc(BigDecimal cnpjConc) {
		this.cnpjConc = cnpjConc;
	}

	/** Obtém o valor atual de dataCp.
	 * 
	 *  @param dataCp 
	 *    O novo valor para dataCp.
	 */
	public void setDataCp(Date dataCp) {
		this.dataCp = dataCp;
	}

	/** Obtém o valor atual de dataNf.
	 * 
	 *  @param dataNf 
	 *    O novo valor para dataNf.
	 */
	public void setDataNf(Date dataNf) {
		this.dataNf = dataNf;
	}

	/** Obtém o valor atual de destinatario.
	 * 
	 *  @param destinatario 
	 *    O novo valor para destinatario.
	 */
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	/** Obtém o valor atual de emitente.
	 * 
	 *  @param emitente 
	 *    O novo valor para emitente.
	 */
	public void setEmitente(String emitente) {
		this.emitente = emitente;
	}

	/** Obtém o valor atual de enderecoConc.
	 * 
	 *  @param enderecoConc 
	 *    O novo valor para enderecoConc.
	 */
	public void setEnderecoConc(String enderecoConc) {
		this.enderecoConc = enderecoConc;
	}

	/** Obtém o valor atual de estadoConc.
	 * 
	 *  @param estadoConc 
	 *    O novo valor para estadoConc.
	 */
	public void setEstadoConc(String estadoConc) {
		this.estadoConc = estadoConc;
	}

	/** Obtém o valor atual de linha.
	 * 
	 *  @param linha 
	 *    O novo valor para linha.
	 */
	public void setLinha(String linha) {
		this.linha = linha;
	}

	/** Obtém o valor atual de listRevisoes.
	 * 
	 *  @param listRevisoes 
	 *    O novo valor para listRevisoes.
	 */
	public void setListRevisoes(List listRevisoes) {
		this.listRevisoes = listRevisoes;
	}

	/** Obtém o valor atual de lote.
	 * 
	 *  @param lote 
	 *    O novo valor para lote.
	 */
	public void setLote(BigDecimal lote) {
		this.lote = lote;
	}

	/** Obtém o valor atual de nomeConc.
	 * 
	 *  @param nomeConc 
	 *    O novo valor para nomeConc.
	 */
	public void setNomeConc(String nomeConc) {
		this.nomeConc = nomeConc;
	}

	/** Obtém o valor atual de numeroCp.
	 * 
	 *  @param numeroCp 
	 *    O novo valor para numeroCp.
	 */
	public void setNumeroCp(BigDecimal numeroCp) {
		this.numeroCp = numeroCp;
	}

	/** Obtém o valor atual de numeroNf.
	 * 
	 *  @param numeroNf 
	 *    O novo valor para numeroNf.
	 */
	public void setNumeroNf(BigDecimal numeroNf) {
		this.numeroNf = numeroNf;
	}

	/** Obtém o valor atual de serieCp.
	 * 
	 *  @param serieCp 
	 *    O novo valor para serieCp.
	 */
	public void setSerieCp(String serieCp) {
		this.serieCp = serieCp;
	}

	/** Obtém o valor atual de serieNf.
	 * 
	 *  @param serieNf 
	 *    O novo valor para serieNf.
	 */
	public void setSerieNf(String serieNf) {
		this.serieNf = serieNf;
	}

	/** Obtém o valor atual de statusLote.
	 * 
	 *  @param statusLote 
	 *    O novo valor para statusLote.
	 */
	public void setStatusLote(String statusLote) {
		this.statusLote = statusLote;
	}

	/** Obtém o valor atual de tipoNf.
	 * 
	 *  @param tipoNf 
	 *    O novo valor para tipoNf.
	 */
	public void setTipoNf(BigDecimal tipoNf) {
		this.tipoNf = tipoNf;
	}

	/** Obtém o valor atual de valorCp.
	 * 
	 *  @param valorCp 
	 *    O novo valor para valorCp.
	 */
	public void setValorCp(BigDecimal valorCp) {
		this.valorCp = valorCp;
	}

	/** Obtém o valor atual de valorNf.
	 * 
	 *  @param valorNf 
	 *    O novo valor para valorNf.
	 */
	public void setValorNf(BigDecimal valorNf) {
		this.valorNf = valorNf;
	}

	/** Obtém o valur atual de empresa.
	 * 
	 *  @param empresa 
	 *    O novo valor para empresa.
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/** Método setter para a propriedade descrTipo
	 *
	 * @param descrTipo 
	 *           <code>String</code> a ser designado para descrTipo.
	 * 
	 */
	public void setDescrTipo(String descrTipo) {
		this.descrTipo = descrTipo;
	}
    
	
	/** Método getter retornar o valorNf como string e formatado para moeda pt_BR.
	 *
	 *  @return o valor atual de valorNf.
	 */
	public String getStrValorNf() {
		if ( valorNf != null )
			return NumberUtils.formatNumberCurrencyMil(valorNf.doubleValue());
		else
			return "";
	}
	
	/** Método getter para a propriedade valorCp formatado para moeda pt_BR.
	 *
	 *  @return o valor atual de valorCp.
	 */
	public String getStrValorCp() {
		
		if ( valorCp != null )
			return NumberUtils.formatNumberCurrencyMil(valorCp.doubleValue());
		else
			return "";
	}

	/** Método setter para a propriedade statusLoteId
	 *
	 * @param statusLoteId BigDecimal
	 */
	public void setStatusLoteId(BigDecimal statusLoteId) {
		this.statusLoteId = statusLoteId;
	}

	/** Método setter para a propriedade malote
	 *
	 * @param malote String
	 */
	public void setMalote(String malote) {
		this.malote = malote;
	}

	

	/** Método setter para a propriedade codigoConcessionaria
	 *
	 * @param codigoConcessionaria String
	 */
	public void setCodigoConcessionaria(String codigoConcessionaria) {
		this.codigoConcessionaria = codigoConcessionaria;
	}
	
	
 
}

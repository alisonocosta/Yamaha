/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioSolicitacaoGarantiaNotaVO.java
 *   .: Criação.....16 de agosto, 09:43
 *   .: Autor.......Gisele Panosso
 *   .: Descrição...Entidade "RelatorioSolicitacaoGarantiaNotaVO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.resource.infra.model.EntityObject;
import br.com.resource.infra.utils.StringUtils;

public class RelatorioSolicitacaoGarantiaNotaVO extends EntityObject {

	//----[ Atributos de classe e instância ]---------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;    
	
    /** Armazena o campo lote do Relatório Solicitação Garantia */
    private BigDecimal lote; 
    
    /** Armazena o campo statusLote do Relatório Solicitação Garantia */
    private String statusLote;
    
    /** Armazena o campo numeroNf do Relatório Solicitação Garantia */
    private BigDecimal numeroNf;
    
    /** Armazena o campo serieNf do Relatório Solicitação Garantia */
    private String serieNf;
    
    /** Armazena o campo dataNf do Relatório Solicitação Garantia */
    private Date dataNf;
    
    /** Armazena o campo valorNf do Relatório Solicitação Garantia */
    private BigDecimal valorNf;
    
    /** Armazena o campo valorIcmsNf do Relatório Solicitação Garantia */
    private BigDecimal valorIcmsNf;
    
    /** Armazena o campo tipo do Relatório Solicitação Garantia */
    private BigDecimal tipo;
    
    /** Armazena o campo descrição do tipo de NF do Relatório Solicitação Garantia */
    private String descrTipo;
    
    /** Armazena o campo numeroCp do Relatório Solicitação Garantia */
    private BigDecimal numeroCp;
    
    /** Armazena o campo serieCp do Relatório Solicitação Garantia */
    private String serieCp;
    
    /** Armazena o campo dataCp do Relatório Solicitação Garantia */
    private Date dataCp;
    
    /** Armazena o campo valorCp do Relatório Solicitação Garantia */
    private BigDecimal valorCp;
    
    /** Armazena o campo emitente do Relatório Solicitação Garantia */
    private String emitente;
    
    /** Armazena o campo destinatario do Relatório Solicitação Garantia */
    private String destinatario;
    
    /** Armazena o campo nomeConc do Relatório Solicitação Garantia */
    private String nomeConc;
    
    /** Armazena o campo enderecoConc do Relatório Solicitação Garantia */
    private String enderecoConc;
    
    /** Armazena o campo cidadeConc do Relatório Solicitação Garantia */
    private String cidadeConc;
    
    /** Armazena o campo estadoConc do Relatório Solicitação Garantia */
    private String estadoConc;
    
    /** Armazena o campo cepConc do Relatório Solicitação Garantia */
    private BigDecimal cepConc;
    
    /** Armazena o campo cnpjConc do Relatório Solicitação Garantia */
    private BigDecimal cnpjConc;
    
    /** Armazena o campo empresa do Relatório Solicitação Garantia */
    private String empresa;
    
    /** Armazena o campo malote do Relatório Solicitação Garantia */
    private String malote;
    
    /** Armazena o campo codigoConcessionaria do Relatório Solicitação Garantia */
    private String codigoConcessionaria;
    
    /** Armazena o campo listModelo (lista de Garantias) do Relatório Solicitação Garantia */
    private List listGarantias;
    
    /** Armazena o campo listPecas (lista de Peças) do Relatório Solicitação Garantia */
    private List listPeca;
    

    //	----[ Método Construtor]---------------------------------------------------  

	/** Método construtor.
     */
	public RelatorioSolicitacaoGarantiaNotaVO() {
		super();
		listGarantias = new ArrayList();
		listPeca      = new ArrayList();
	}
	
    //	----[ Métodos Add]---------------------------------------------------	
	
	/** Adiciona uma lista de Garantias no relatório Solicitação de Garantia.
     * 
     *  @param listGarantias
     *      listGarantias a ser adicionado.
     *  
     */
    public void addListGarantias(RelatorioSolicitacaoGarantiaVO listGarantias) {
    	
        this.listGarantias.add( listGarantias );
        
    }	
    
	/** Adiciona uma lista de Peças no relatório Solicitação de Garantia.
     * 
     *  @param listaPecas
     *      listaPecas a ser adicionado.
     *  
     */
    public void addListPecas(RelatorioSolicitacaoGarantiaPecaVO listPeca) {
    	
        this.listPeca.add( listPeca );
        
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
	
	/** Método getter para a propriedade cnpjConc no formato String.
	 *
	 *  @return o valor atual de cnpjConc.
	 */
	public String getStrCnpjConc() {
		return StringUtils.formatarCnpj(String.valueOf(cnpjConc));
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


	/** Método getter para a propriedade tipo.
	 *
	 *  @return o valor atual de tipo.
	 */
	public BigDecimal getTipo() {
		return tipo;
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
	
	/** Método getter para a propriedade listGarantias.
	 *
	 *  @return o valor atual de listGarantias.
	 */
	public List getListGarantias() {
		return listGarantias;
	}

	/** Método getter para a propriedade listPeca.
	 *
	 *  @return o valor atual de listPeca.
	 */
	public List getListPeca() {
		return listPeca;
	}	

	/** Método getter para a propriedade empresa.
	 *
	 *  @return o valor atual de empresa.
	 */
	public String getEmpresa() {
		return empresa;
	}	

	/** Método getter para a propriedade malote
	 *
	 *  @return String de malote
	 */
	public String getMalote() {
		return malote;
	}

	/** Método getter para a propriedade codigoConcessionaria
	 *
	 *  @return String de codigoConcessionaria
	 */
	public String getCodigoConcessionaria() {
		return codigoConcessionaria;
	}
	
	/**
	 * Método getter para a propriedade valorIcmsNf
	 * @return  BigDecimal de valorIcmsNf
	 */
	public BigDecimal getValorIcmsNf() {
		return valorIcmsNf;
	}
    
	//	----[ Métodos Setter ]---------------------------------------------------
    
	/** Obtém o valur atual de cepConc.
	 * 
	 *  @param cepConc 
	 *    O novo valor para cepConc.
	 */
	public void setCepConc(BigDecimal cepConc) {
		this.cepConc = cepConc;
	}


	/** Obtém o valur atual de cidadeConc.
	 * 
	 *  @param cidadeConc 
	 *    O novo valor para cidadeConc.
	 */
	public void setCidadeConc(String cidadeConc) {
		this.cidadeConc = cidadeConc;
	}


	/** Obtém o valur atual de cnpjConc.
	 * 
	 *  @param cnpjConc 
	 *    O novo valor para cnpjConc.
	 */
	public void setCnpjConc(BigDecimal cnpjConc) {
		this.cnpjConc = cnpjConc;
	}


	/** Obtém o valur atual de dataCp.
	 * 
	 *  @param dataCp 
	 *    O novo valor para dataCp.
	 */
	public void setDataCp(Date dataCp) {
		this.dataCp = dataCp;
	}


	/** Obtém o valur atual de dataNf.
	 * 
	 *  @param dataNf 
	 *    O novo valor para dataNf.
	 */
	public void setDataNf(Date dataNf) {
		this.dataNf = dataNf;
	}


	/** Obtém o valur atual de destinatario.
	 * 
	 *  @param destinatario 
	 *    O novo valor para destinatario.
	 */
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}


	/** Obtém o valur atual de emitente.
	 * 
	 *  @param emitente 
	 *    O novo valor para emitente.
	 */
	public void setEmitente(String emitente) {
		this.emitente = emitente;
	}


	/** Obtém o valur atual de enderecoConc.
	 * 
	 *  @param enderecoConc 
	 *    O novo valor para enderecoConc.
	 */
	public void setEnderecoConc(String enderecoConc) {
		this.enderecoConc = enderecoConc;
	}


	/** Obtém o valur atual de estadoConc.
	 * 
	 *  @param estadoConc 
	 *    O novo valor para estadoConc.
	 */
	public void setEstadoConc(String estadoConc) {
		this.estadoConc = estadoConc;
	}

	/** Obtém o valur atual de lote.
	 * 
	 *  @param lote 
	 *    O novo valor para lote.
	 */
	public void setLote(BigDecimal lote) {
		this.lote = lote;
	}


	/** Obtém o valur atual de nomeConc.
	 * 
	 *  @param nomeConc 
	 *    O novo valor para nomeConc.
	 */
	public void setNomeConc(String nomeConc) {
		this.nomeConc = nomeConc;
	}


	/** Obtém o valur atual de numeroCp.
	 * 
	 *  @param numeroCp 
	 *    O novo valor para numeroCp.
	 */
	public void setNumeroCp(BigDecimal numeroCp) {
		this.numeroCp = numeroCp;
	}


	/** Obtém o valur atual de numeroNf.
	 * 
	 *  @param numeroNf 
	 *    O novo valor para numeroNf.
	 */
	public void setNumeroNf(BigDecimal numeroNf) {
		this.numeroNf = numeroNf;
	}


	/** Obtém o valur atual de serieCp.
	 * 
	 *  @param serieCp 
	 *    O novo valor para serieCp.
	 */
	public void setSerieCp(String serieCp) {
		this.serieCp = serieCp;
	}


	/** Obtém o valur atual de serieNf.
	 * 
	 *  @param serieNf 
	 *    O novo valor para serieNf.
	 */
	public void setSerieNf(String serieNf) {
		this.serieNf = serieNf;
	}


	/** Obtém o valur atual de statusLote.
	 * 
	 *  @param statusLote 
	 *    O novo valor para statusLote.
	 */
	public void setStatusLote(String statusLote) {
		this.statusLote = statusLote;
	}


	/** Obtém o valur atual de tipo.
	 * 
	 *  @param tipo 
	 *    O novo valor para tipo.
	 */
	public void setTipo(BigDecimal tipo) {
		this.tipo = tipo;
	}


	/** Obtém o valur atual de valorCp.
	 * 
	 *  @param valorCp 
	 *    O novo valor para valorCp.
	 */
	public void setValorCp(BigDecimal valorCp) {
		this.valorCp = valorCp;
	}


	/** Obtém o valur atual de valorNf.
	 * 
	 *  @param valorNf 
	 *    O novo valor para valorNf.
	 */
	public void setValorNf(BigDecimal valorNf) {
		this.valorNf = valorNf;
	}

	/** Obtém o valur atual de listGarantias.
	 * 
	 *  @param listGarantias 
	 *    O novo valor para listGarantias.
	 */
	public void setListGarantias(List listGarantias) {
		this.listGarantias = listGarantias;
	}

	/** Obtém o valur atual de listPeca.
	 * 
	 *  @param listPeca 
	 *    O novo valor para listPeca.
	 */
	public void setListPeca(List listPeca) {
		this.listPeca = listPeca;
	}

	/** Obtém o valur atual de empresa.
	 * 
	 *  @param empresa 
	 *    O novo valor para empresa.
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/** Método getter para a propriedade descrTipo
	 *
	 *  @return String de descrTipo
	 */
	public String getDescrTipo() {
		return descrTipo;
	}

	/** Método setter para a propriedade descrTipo
	 *
	 * @param descrTipo String
	 */
	public void setDescrTipo(String descrTipo) {
		this.descrTipo = descrTipo;
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

	/**
	 * Método setter para a propriedade valorIcmsNf
	 * @param valorIcmsNf BigDecimal
	 */
	public void setValorIcmsNf(BigDecimal valorIcmsNf) {
		this.valorIcmsNf = valorIcmsNf;
	}    
    
	
    
}

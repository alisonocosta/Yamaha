/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioSolicitacaoGarantiaNotaVO.java
 *   .: Cria��o.....16 de agosto, 09:43
 *   .: Autor.......Gisele Panosso
 *   .: Descri��o...Entidade "RelatorioSolicitacaoGarantiaNotaVO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.resource.infra.model.EntityObject;
import br.com.resource.infra.utils.StringUtils;

public class RelatorioSolicitacaoGarantiaNotaVO extends EntityObject {

	//----[ Atributos de classe e inst�ncia ]---------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;    
	
    /** Armazena o campo lote do Relat�rio Solicita��o Garantia */
    private BigDecimal lote; 
    
    /** Armazena o campo statusLote do Relat�rio Solicita��o Garantia */
    private String statusLote;
    
    /** Armazena o campo numeroNf do Relat�rio Solicita��o Garantia */
    private BigDecimal numeroNf;
    
    /** Armazena o campo serieNf do Relat�rio Solicita��o Garantia */
    private String serieNf;
    
    /** Armazena o campo dataNf do Relat�rio Solicita��o Garantia */
    private Date dataNf;
    
    /** Armazena o campo valorNf do Relat�rio Solicita��o Garantia */
    private BigDecimal valorNf;
    
    /** Armazena o campo valorIcmsNf do Relat�rio Solicita��o Garantia */
    private BigDecimal valorIcmsNf;
    
    /** Armazena o campo tipo do Relat�rio Solicita��o Garantia */
    private BigDecimal tipo;
    
    /** Armazena o campo descri��o do tipo de NF do Relat�rio Solicita��o Garantia */
    private String descrTipo;
    
    /** Armazena o campo numeroCp do Relat�rio Solicita��o Garantia */
    private BigDecimal numeroCp;
    
    /** Armazena o campo serieCp do Relat�rio Solicita��o Garantia */
    private String serieCp;
    
    /** Armazena o campo dataCp do Relat�rio Solicita��o Garantia */
    private Date dataCp;
    
    /** Armazena o campo valorCp do Relat�rio Solicita��o Garantia */
    private BigDecimal valorCp;
    
    /** Armazena o campo emitente do Relat�rio Solicita��o Garantia */
    private String emitente;
    
    /** Armazena o campo destinatario do Relat�rio Solicita��o Garantia */
    private String destinatario;
    
    /** Armazena o campo nomeConc do Relat�rio Solicita��o Garantia */
    private String nomeConc;
    
    /** Armazena o campo enderecoConc do Relat�rio Solicita��o Garantia */
    private String enderecoConc;
    
    /** Armazena o campo cidadeConc do Relat�rio Solicita��o Garantia */
    private String cidadeConc;
    
    /** Armazena o campo estadoConc do Relat�rio Solicita��o Garantia */
    private String estadoConc;
    
    /** Armazena o campo cepConc do Relat�rio Solicita��o Garantia */
    private BigDecimal cepConc;
    
    /** Armazena o campo cnpjConc do Relat�rio Solicita��o Garantia */
    private BigDecimal cnpjConc;
    
    /** Armazena o campo empresa do Relat�rio Solicita��o Garantia */
    private String empresa;
    
    /** Armazena o campo malote do Relat�rio Solicita��o Garantia */
    private String malote;
    
    /** Armazena o campo codigoConcessionaria do Relat�rio Solicita��o Garantia */
    private String codigoConcessionaria;
    
    /** Armazena o campo listModelo (lista de Garantias) do Relat�rio Solicita��o Garantia */
    private List listGarantias;
    
    /** Armazena o campo listPecas (lista de Pe�as) do Relat�rio Solicita��o Garantia */
    private List listPeca;
    

    //	----[ M�todo Construtor]---------------------------------------------------  

	/** M�todo construtor.
     */
	public RelatorioSolicitacaoGarantiaNotaVO() {
		super();
		listGarantias = new ArrayList();
		listPeca      = new ArrayList();
	}
	
    //	----[ M�todos Add]---------------------------------------------------	
	
	/** Adiciona uma lista de Garantias no relat�rio Solicita��o de Garantia.
     * 
     *  @param listGarantias
     *      listGarantias a ser adicionado.
     *  
     */
    public void addListGarantias(RelatorioSolicitacaoGarantiaVO listGarantias) {
    	
        this.listGarantias.add( listGarantias );
        
    }	
    
	/** Adiciona uma lista de Pe�as no relat�rio Solicita��o de Garantia.
     * 
     *  @param listaPecas
     *      listaPecas a ser adicionado.
     *  
     */
    public void addListPecas(RelatorioSolicitacaoGarantiaPecaVO listPeca) {
    	
        this.listPeca.add( listPeca );
        
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
	
	/** M�todo getter para a propriedade cnpjConc no formato String.
	 *
	 *  @return o valor atual de cnpjConc.
	 */
	public String getStrCnpjConc() {
		return StringUtils.formatarCnpj(String.valueOf(cnpjConc));
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


	/** M�todo getter para a propriedade tipo.
	 *
	 *  @return o valor atual de tipo.
	 */
	public BigDecimal getTipo() {
		return tipo;
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
	
	/** M�todo getter para a propriedade listGarantias.
	 *
	 *  @return o valor atual de listGarantias.
	 */
	public List getListGarantias() {
		return listGarantias;
	}

	/** M�todo getter para a propriedade listPeca.
	 *
	 *  @return o valor atual de listPeca.
	 */
	public List getListPeca() {
		return listPeca;
	}	

	/** M�todo getter para a propriedade empresa.
	 *
	 *  @return o valor atual de empresa.
	 */
	public String getEmpresa() {
		return empresa;
	}	

	/** M�todo getter para a propriedade malote
	 *
	 *  @return String de malote
	 */
	public String getMalote() {
		return malote;
	}

	/** M�todo getter para a propriedade codigoConcessionaria
	 *
	 *  @return String de codigoConcessionaria
	 */
	public String getCodigoConcessionaria() {
		return codigoConcessionaria;
	}
	
	/**
	 * M�todo getter para a propriedade valorIcmsNf
	 * @return  BigDecimal de valorIcmsNf
	 */
	public BigDecimal getValorIcmsNf() {
		return valorIcmsNf;
	}
    
	//	----[ M�todos Setter ]---------------------------------------------------
    
	/** Obt�m o valur atual de cepConc.
	 * 
	 *  @param cepConc 
	 *    O novo valor para cepConc.
	 */
	public void setCepConc(BigDecimal cepConc) {
		this.cepConc = cepConc;
	}


	/** Obt�m o valur atual de cidadeConc.
	 * 
	 *  @param cidadeConc 
	 *    O novo valor para cidadeConc.
	 */
	public void setCidadeConc(String cidadeConc) {
		this.cidadeConc = cidadeConc;
	}


	/** Obt�m o valur atual de cnpjConc.
	 * 
	 *  @param cnpjConc 
	 *    O novo valor para cnpjConc.
	 */
	public void setCnpjConc(BigDecimal cnpjConc) {
		this.cnpjConc = cnpjConc;
	}


	/** Obt�m o valur atual de dataCp.
	 * 
	 *  @param dataCp 
	 *    O novo valor para dataCp.
	 */
	public void setDataCp(Date dataCp) {
		this.dataCp = dataCp;
	}


	/** Obt�m o valur atual de dataNf.
	 * 
	 *  @param dataNf 
	 *    O novo valor para dataNf.
	 */
	public void setDataNf(Date dataNf) {
		this.dataNf = dataNf;
	}


	/** Obt�m o valur atual de destinatario.
	 * 
	 *  @param destinatario 
	 *    O novo valor para destinatario.
	 */
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}


	/** Obt�m o valur atual de emitente.
	 * 
	 *  @param emitente 
	 *    O novo valor para emitente.
	 */
	public void setEmitente(String emitente) {
		this.emitente = emitente;
	}


	/** Obt�m o valur atual de enderecoConc.
	 * 
	 *  @param enderecoConc 
	 *    O novo valor para enderecoConc.
	 */
	public void setEnderecoConc(String enderecoConc) {
		this.enderecoConc = enderecoConc;
	}


	/** Obt�m o valur atual de estadoConc.
	 * 
	 *  @param estadoConc 
	 *    O novo valor para estadoConc.
	 */
	public void setEstadoConc(String estadoConc) {
		this.estadoConc = estadoConc;
	}

	/** Obt�m o valur atual de lote.
	 * 
	 *  @param lote 
	 *    O novo valor para lote.
	 */
	public void setLote(BigDecimal lote) {
		this.lote = lote;
	}


	/** Obt�m o valur atual de nomeConc.
	 * 
	 *  @param nomeConc 
	 *    O novo valor para nomeConc.
	 */
	public void setNomeConc(String nomeConc) {
		this.nomeConc = nomeConc;
	}


	/** Obt�m o valur atual de numeroCp.
	 * 
	 *  @param numeroCp 
	 *    O novo valor para numeroCp.
	 */
	public void setNumeroCp(BigDecimal numeroCp) {
		this.numeroCp = numeroCp;
	}


	/** Obt�m o valur atual de numeroNf.
	 * 
	 *  @param numeroNf 
	 *    O novo valor para numeroNf.
	 */
	public void setNumeroNf(BigDecimal numeroNf) {
		this.numeroNf = numeroNf;
	}


	/** Obt�m o valur atual de serieCp.
	 * 
	 *  @param serieCp 
	 *    O novo valor para serieCp.
	 */
	public void setSerieCp(String serieCp) {
		this.serieCp = serieCp;
	}


	/** Obt�m o valur atual de serieNf.
	 * 
	 *  @param serieNf 
	 *    O novo valor para serieNf.
	 */
	public void setSerieNf(String serieNf) {
		this.serieNf = serieNf;
	}


	/** Obt�m o valur atual de statusLote.
	 * 
	 *  @param statusLote 
	 *    O novo valor para statusLote.
	 */
	public void setStatusLote(String statusLote) {
		this.statusLote = statusLote;
	}


	/** Obt�m o valur atual de tipo.
	 * 
	 *  @param tipo 
	 *    O novo valor para tipo.
	 */
	public void setTipo(BigDecimal tipo) {
		this.tipo = tipo;
	}


	/** Obt�m o valur atual de valorCp.
	 * 
	 *  @param valorCp 
	 *    O novo valor para valorCp.
	 */
	public void setValorCp(BigDecimal valorCp) {
		this.valorCp = valorCp;
	}


	/** Obt�m o valur atual de valorNf.
	 * 
	 *  @param valorNf 
	 *    O novo valor para valorNf.
	 */
	public void setValorNf(BigDecimal valorNf) {
		this.valorNf = valorNf;
	}

	/** Obt�m o valur atual de listGarantias.
	 * 
	 *  @param listGarantias 
	 *    O novo valor para listGarantias.
	 */
	public void setListGarantias(List listGarantias) {
		this.listGarantias = listGarantias;
	}

	/** Obt�m o valur atual de listPeca.
	 * 
	 *  @param listPeca 
	 *    O novo valor para listPeca.
	 */
	public void setListPeca(List listPeca) {
		this.listPeca = listPeca;
	}

	/** Obt�m o valur atual de empresa.
	 * 
	 *  @param empresa 
	 *    O novo valor para empresa.
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/** M�todo getter para a propriedade descrTipo
	 *
	 *  @return String de descrTipo
	 */
	public String getDescrTipo() {
		return descrTipo;
	}

	/** M�todo setter para a propriedade descrTipo
	 *
	 * @param descrTipo String
	 */
	public void setDescrTipo(String descrTipo) {
		this.descrTipo = descrTipo;
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

	/**
	 * M�todo setter para a propriedade valorIcmsNf
	 * @param valorIcmsNf BigDecimal
	 */
	public void setValorIcmsNf(BigDecimal valorIcmsNf) {
		this.valorIcmsNf = valorIcmsNf;
	}    
    
	
    
}

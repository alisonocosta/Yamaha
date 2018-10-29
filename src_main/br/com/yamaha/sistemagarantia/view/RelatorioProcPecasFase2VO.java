/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioProcPecasFase2VO.java
 *   .: Cria��o.....08 de agosto, 15:37
 *   .: Autor.......Gisele Panosso
 *   .: Descri��o...Entidade "RelatorioProcPecasFase2VO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.com.resource.infra.model.EntityObject;
import br.com.resource.infra.utils.NumberUtils;

public class RelatorioProcPecasFase2VO extends EntityObject {
	
	
	public RelatorioProcPecasFase2VO () {
		
		super();
		this.listPecas = new ArrayList();
		
	}

	//----[ Atributos de classe e inst�ncia ]---------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;    
    
    /** Armazena o campo linhaProduto do Relat�rio Processamento de Pe�as - Fase2. */	
    private String linhaProduto;
    
    /** Armazena o campo lote do Relat�rio Processamento de Pe�as - Fase2. */
    private BigDecimal lote;
    
    /** Armazena o campo numeroSg do Relat�rio Processamento de Pe�as - Fase2. */
    private BigDecimal numeroSg;
    
    /** Armazena o campo chassi do Relat�rio Processamento de Pe�as - Fase2. */
    private String chassi;
        
    /** Armazena o campo numeroOs do Relat�rio Processamento de Pe�as - Fase2. */
    private String numeroOs;
    
    /** Armazena o campo dtAbertura do Relat�rio Processamento de Pe�as - Fase2. */
    private Date dtAbertura;
    
    /** Armazena o campo dtFechto do Relat�rio Processamento de Pe�as - Fase2. */
    private Date dtFechto;
    
    /** Armazena o campo km do Relat�rio Processamento de Pe�as - Fase2. */
    private BigDecimal km;
    
    /** Armazena o campo nomeEmp do Relat�rio Processamento de Pe�as - Fase2. */
    private String nomeEmp;
    
    /** Armazena o campo inscrEstadualEmp do Relat�rio Processamento de Pe�as - Fase2. */
    private String inscrEstadualEmp;
    
    /** Armazena o campo cnpjConc do Relat�rio Processamento de Pe�as - Fase2. */
    private String cnpjConc;
    
    /** Armazena o campo razaoSocialConc do Relat�rio Processamento de Pe�as - Fase2. */
    private String razaoSocialConc;
    
    /** Armazena o campo enderecoConc do Relat�rio Processamento de Pe�as - Fase2. */
    private String enderecoConc;
    
    /** Armazena o campo cepConc do Relat�rio Processamento de Pe�as - Fase2. */
    private BigDecimal cepConc;
   
    /** Armazena o campo cidadeConc do Relat�rio Processamento de Pe�as - Fase2. */
    private String cidadeConc;
    
    /** Armazena o campo estadoConc do Relat�rio Processamento de Pe�as - Fase2. */
    private String estadoConc;
    
    /** Armazena o campo quantidade do Relat�rio Processamento de Pe�as - Fase2. */
    private BigDecimal quantidade;
    
    /** Armazena o campo item do Relat�rio Processamento de Pe�as - Fase2. */
    private String item;
    
    /** Armazena o campo descricao do Relat�rio Processamento de Pe�as - Fase2. */
    private String descricao;   
    
    /** Armazena o campo vlUnitPeca do Relat�rio Processamento de Pe�as - Fase2. */    
    private BigDecimal vlUnitPeca;
    
    /** Armazena o campo vlTotalPeca do Relat�rio Processamento de Pe�as - Fase2. */
    private BigDecimal vlTotalPeca;
    
    /** Armazena o campo vlRemesPeca (valor remessa) do Relat�rio Processamento de Pe�as - Fase2. */
    private BigDecimal vlRemesPeca;
    
    /** Armazena o campo servico do Relat�rio Processamento de Pe�as - Fase2. */
    private String servico;
    
    /** Armazena o campo sintoma do Relat�rio Processamento de Pe�as - Fase2. */
    private String sintoma;
    
    /** Armazena o campo descri��o do sintoma do Relat�rio Processamento de Pe�as - Fase2. */
    private String sintomaDesc;
    
    /** Armazena o campo condicao do Relat�rio Processamento de Pe�as - Fase2. */
    private String condicao;
    
    /** Armazena o campo descri��o da condi��o do Relat�rio Processamento de Pe�as - Fase2. */
    private String condicaoDesc;
    
    /** Armazena o campo vlTotalServico do Relat�rio Processamento de Pe�as - Fase2. */
    private BigDecimal vlTotalServico;
    
    /** Armazena o campo vlServicoTerc do Relat�rio Processamento de Pe�as - Fase2. */
    private BigDecimal vlServicoTerc;
    
    private ArrayList listPecas;
    
    private ArrayList listServicos;
    
    private List listNotas;
    
	
	//	----[ M�todos Getter ]---------------------------------------------------

	/** M�todo getter para a propriedade cepConc.
	 *
	 *  @return o valor atual de cepConc.
	 */
	public BigDecimal getCepConc() {
		return cepConc;
	}

	/** M�todo getter para a propriedade chassi.
	 *
	 *  @return o valor atual de chassi.
	 */
	public String getChassi() {
		return chassi;
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
	public String getCnpjConc() {
		return cnpjConc;
	}

	/** M�todo getter para a propriedade condicao.
	 *
	 *  @return o valor atual de condicao.
	 */
	public String getCondicao() {
		return condicao;
	}

	/** M�todo getter para a propriedade descricao.
	 *
	 *  @return o valor atual de descricao.
	 */
	public String getDescricao() {
		return descricao;
	}

	/** M�todo getter para a propriedade dtAbertura.
	 *
	 *  @return o valor atual de dtAbertura.
	 */
	public Date getDtAbertura() {
		return dtAbertura;
	}

	/** M�todo getter para a propriedade dtFechto.
	 *
	 *  @return o valor atual de dtFechto.
	 */
	public Date getDtFechto() {
		return dtFechto;
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

	/** M�todo getter para a propriedade inscrEstadualEmp.
	 *
	 *  @return o valor atual de inscrEstadualEmp.
	 */
	public String getInscrEstadualEmp() {
		return inscrEstadualEmp;
	}

	/** M�todo getter para a propriedade item.
	 *
	 *  @return o valor atual de item.
	 */
	public String getItem() {
		return item;
	}

	/** M�todo getter para a propriedade km.
	 *
	 *  @return o valor atual de km.
	 */
	public BigDecimal getKm() {
		return km;
	}

	/** M�todo getter para a propriedade linhaProduto.
	 *
	 *  @return o valor atual de linhaProduto.
	 */
	public String getLinhaProduto() {
		return linhaProduto;
	}

	/** M�todo getter para a propriedade lote.
	 *
	 *  @return o valor atual de lote.
	 */
	public BigDecimal getLote() {
		return lote;
	}

	/** M�todo getter para a propriedade nomeEmp.
	 *
	 *  @return o valor atual de nomeEmp.
	 */
	public String getNomeEmp() {
		return nomeEmp;
	}

	/** M�todo getter para a propriedade numeroOs.
	 *
	 *  @return o valor atual de numeroOs.
	 */
	public String getNumeroOs() {
		return numeroOs;
	}

	/** M�todo getter para a propriedade numeroSg.
	 *
	 *  @return o valor atual de numeroSg.
	 */
	public BigDecimal getNumeroSg() {
		return numeroSg;
	}

	/** M�todo getter para a propriedade quantidade.
	 *
	 *  @return o valor atual de quantidade.
	 */
	public BigDecimal getQuantidade() {
		return quantidade;
	}

	/** M�todo getter para a propriedade razaoSocialConc.
	 *
	 *  @return o valor atual de razaoSocialConc.
	 */
	public String getRazaoSocialConc() {
		return razaoSocialConc;
	}

	/** M�todo getter para a propriedade servico.
	 *
	 *  @return o valor atual de servico.
	 */
	public String getServico() {
		return servico;
	}

	/** M�todo getter para a propriedade sintoma.
	 *
	 *  @return o valor atual de sintoma.
	 */
	public String getSintoma() {
		return sintoma;
	}

	/** M�todo getter para a propriedade vlRemesPeca.
	 *
	 *  @return o valor atual de vlRemesPeca.
	 */
	public BigDecimal getVlRemesPeca() {
		return vlRemesPeca;
	}

	/** M�todo getter para a propriedade vlServicoTerc.
	 *
	 *  @return o valor atual de vlServicoTerc.
	 */
	public BigDecimal getVlServicoTerc() {
		return vlServicoTerc;
	}

	/** M�todo getter para a propriedade vlTotalPeca.
	 *
	 *  @return o valor atual de vlTotalPeca.
	 */
	public BigDecimal getVlTotalPeca() {
		return vlTotalPeca;
	}

	/** M�todo getter para a propriedade vlTotalServico.
	 *
	 *  @return o valor atual de vlTotalServico.
	 */
	public BigDecimal getVlTotalServico() {
		return vlTotalServico;
	}

	/** M�todo getter para a propriedade vlUnitPeca.
	 *
	 *  @return o valor atual de vlUnitPeca.
	 */
	public BigDecimal getVlUnitPeca() {
		return vlUnitPeca;
	}

	/** M�todo getter para a propriedade condicaoDesc
	 * 
	 *  @return String
	 *
	 */
	public String getCondicaoDesc() {
		return condicaoDesc;
	}

	/** M�todo getter para a propriedade sintomaDesc
	 * 
	 *  @return String
	 *
	 */
	public String getSintomaDesc() {
		return sintomaDesc;
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

	/** Obt�m o valor atual de chassi.
	 * 
	 *  @param chassi 
	 *    O novo valor para chassi.
	 */
	public void setChassi(String chassi) {
		this.chassi = chassi;
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
	public void setCnpjConc(String cnpjConc) {
		this.cnpjConc = cnpjConc;
	}

	/** Obt�m o valor atual de condicao.
	 * 
	 *  @param condicao 
	 *    O novo valor para condicao.
	 */
	public void setCondicao(String condicao) {
		this.condicao = condicao;
	}

	/** Obt�m o valor atual de descricao.
	 * 
	 *  @param descricao 
	 *    O novo valor para descricao.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/** Obt�m o valor atual de dtAbertura.
	 * 
	 *  @param dtAbertura 
	 *    O novo valor para dtAbertura.
	 */
	public void setDtAbertura(Date dtAbertura) {
		this.dtAbertura = dtAbertura;
	}

	/** Obt�m o valor atual de dtFechto.
	 * 
	 *  @param dtFechto 
	 *    O novo valor para dtFechto.
	 */
	public void setDtFechto(Date dtFechto) {
		this.dtFechto = dtFechto;
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

	/** Obt�m o valor atual de inscrEstadualEmp.
	 * 
	 *  @param inscrEstadualEmp 
	 *    O novo valor para inscrEstadualEmp.
	 */
	public void setInscrEstadualEmp(String inscrEstadualEmp) {
		this.inscrEstadualEmp = inscrEstadualEmp;
	}

	/** Obt�m o valor atual de item.
	 * 
	 *  @param item 
	 *    O novo valor para item.
	 */
	public void setItem(String item) {
		this.item = item;
	}

	/** Obt�m o valor atual de km.
	 * 
	 *  @param km 
	 *    O novo valor para km.
	 */
	public void setKm(BigDecimal km) {
		this.km = km;
	}

	/** Obt�m o valor atual de linhaProduto.
	 * 
	 *  @param linhaProduto 
	 *    O novo valor para linhaProduto.
	 */
	public void setLinhaProduto(String linhaProduto) {
		this.linhaProduto = linhaProduto;
	}

	/** Obt�m o valor atual de lote.
	 * 
	 *  @param lote 
	 *    O novo valor para lote.
	 */
	public void setLote(BigDecimal lote) {
		this.lote = lote;
	}

	/** Obt�m o valor atual de nomeEmp.
	 * 
	 *  @param nomeEmp 
	 *    O novo valor para nomeEmp.
	 */
	public void setNomeEmp(String nomeEmp) {
		this.nomeEmp = nomeEmp;
	}

	/** Obt�m o valor atual de numeroOs.
	 * 
	 *  @param numeroOs 
	 *    O novo valor para numeroOs.
	 */
	public void setNumeroOs(String numeroOs) {
		this.numeroOs = numeroOs;
	}

	/** Obt�m o valor atual de numeroSg.
	 * 
	 *  @param numeroSg 
	 *    O novo valor para numeroSg.
	 */
	public void setNumeroSg(BigDecimal numeroSg) {
		this.numeroSg = numeroSg;
	}

	/** Obt�m o valor atual de quantidade.
	 * 
	 *  @param quantidade 
	 *    O novo valor para quantidade.
	 */
	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	/** Obt�m o valor atual de razaoSocialConc.
	 * 
	 *  @param razaoSocialConc 
	 *    O novo valor para razaoSocialConc.
	 */
	public void setRazaoSocialConc(String razaoSocialConc) {
		this.razaoSocialConc = razaoSocialConc;
	}

	/** Obt�m o valor atual de servico.
	 * 
	 *  @param servico 
	 *    O novo valor para servico.
	 */
	public void setServico(String servico) {
		this.servico = servico;
	}

	/** Obt�m o valor atual de sintoma.
	 * 
	 *  @param sintoma 
	 *    O novo valor para sintoma.
	 */
	public void setSintoma(String sintoma) {
		this.sintoma = sintoma;
	}

	/** Obt�m o valor atual de vlRemesPeca.
	 * 
	 *  @param vlRemesPeca 
	 *    O novo valor para vlRemesPeca.
	 */
	public void setVlRemesPeca(BigDecimal vlRemesPeca) {
		this.vlRemesPeca = vlRemesPeca;
	}

	/** Obt�m o valor atual de vlServicoTerc.
	 * 
	 *  @param vlServicoTerc 
	 *    O novo valor para vlServicoTerc.
	 */
	public void setVlServicoTerc(BigDecimal vlServicoTerc) {
		this.vlServicoTerc = vlServicoTerc;
	}

	/** Obt�m o valor atual de vlTotalPeca.
	 * 
	 *  @param vlTotalPeca 
	 *    O novo valor para vlTotalPeca.
	 */
	public void setVlTotalPeca(BigDecimal vlTotalPeca) {
		this.vlTotalPeca = vlTotalPeca;
	}

	/** Obt�m o valor atual de vlTotalServico.
	 * 
	 *  @param vlTotalServico 
	 *    O novo valor para vlTotalServico.
	 */
	public void setVlTotalServico(BigDecimal vlTotalServico) {
		this.vlTotalServico = vlTotalServico;
	}

	/** Obt�m o valor atual de vlUnitPeca.
	 * 
	 *  @param vlUnitPeca 
	 *    O novo valor para vlUnitPeca.
	 */
	public void setVlUnitPeca(BigDecimal vlUnitPeca) {
		this.vlUnitPeca = vlUnitPeca;
	} 
	
	/** M�todo setter para a propriedade condicaoDesc
	 *
	 * @param condicaoDesc 
	 *           <code>String</code> a ser designado para condicaoDesc.
	 * 
	 */
	public void setCondicaoDesc(String condicaoDesc) {
		this.condicaoDesc = condicaoDesc;
	}

	/** M�todo setter para a propriedade sintomaDesc
	 *
	 * @param sintomaDesc 
	 *           <code>String</code> a ser designado para sintomaDesc.
	 * 
	 */
	public void setSintomaDesc(String sintomaDesc) {
		this.sintomaDesc = sintomaDesc;
	}	
	
	
	//	----[ M�todos de Servi�os da Classe ]---------------------------------------------------
	

	/**
	 * Retorna o valor Unit�rio da pe�a formatado para Moeda 
	 * 
	 * @return String  ########0,00
	 * 
	 */
	public String getVlUnitPecaStr() {
		
		if ( this.vlUnitPeca != null )
			return NumberUtils.formatNumberCurrency(this.vlUnitPeca.doubleValue());
		else
			return "0,00";
		
	}

	/**
	 * Retorna o valor total de servi�o formatado para Moeda 
	 * 
	 * @return String  ########0,00
	 * 
	 */
	public String getVlTotalServicoStr() {
		
		if ( this.vlTotalServico != null )
			return NumberUtils.formatNumberCurrency(this.vlTotalServico.doubleValue());
		else
			return "0,00";
		
	}

	/**
	 * Retorna o valor total de pe�a formatado para Moeda 
	 * 
	 * @return String  ########0,00
	 * 
	 */
	public String getVlTotalPecaStr() {
		
		if ( this.vlTotalPeca != null )
			return NumberUtils.formatNumberCurrency(this.vlTotalPeca.doubleValue());
		else
			return "0,00";
		
	}
	
	/**
	 * Retorna o valor do servi�o de terceiro formatado para Moeda 
	 * 
	 * @return String  ########0,00
	 * 
	 */
	public String getVlServicoTercStr() {
		
		if ( this.vlServicoTerc != null )
			return NumberUtils.formatNumberCurrency(this.vlServicoTerc.doubleValue());
		else
			return "0,00";
		
	}
	
	/**
	 * Retorna o valor de remessa formatado para Moeda 
	 * 
	 * @return String  ########0,00
	 * 
	 */
	public String getVlRemesPecaStr() {
		
		if ( this.vlRemesPeca != null )
			return NumberUtils.formatNumberCurrency(this.vlRemesPeca.doubleValue());
		else
			return "0,00";
		
	}

	/** M�todo getter para a propriedade listPecas
	 * 
	 *  @return JRBeanCollectionDataSource
	 *
	 */
	public JRBeanCollectionDataSource getListPecasDts() {
		return new JRBeanCollectionDataSource(this.listPecas);
	}
	
	/** M�todo getter para a propriedade listPecas
	 * 
	 *  @return ArrayList
	 *
	 */
	public ArrayList getListPecas() {
		return this.listPecas;
	}

	/** M�todo setter para a propriedade listPecas
	 *
	 * @param listPecas 
	 *           <code>ArrayList</code> a ser designado para listPecas.
	 * 
	 */
	public void setListPecas(ArrayList listPecas) {
		this.listPecas = listPecas;
	}
	
	/** M�todo getter para a propriedade listServicos
	 * 
	 *  @return JRBeanCollectionDataSource
	 *
	 */
	public JRBeanCollectionDataSource getListServicosDts() {
		return new JRBeanCollectionDataSource(this.listServicos);
	}
	
	/** M�todo getter para a propriedade listNotas
	 * 
	 *  @return JRBeanCollectionDataSource
	 *
	 */
	public JRBeanCollectionDataSource getListNotasDts() {
		if ( !this.listNotas.isEmpty() )
			return new JRBeanCollectionDataSource(this.listNotas);
		else 
			return null;
	}
	
	/** M�todo getter para a propriedade listServicos
	 * 
	 *  @return ArrayList
	 *
	 */
	public ArrayList getListServicos() {
		return this.listServicos;
	}

	/** M�todo setter para a propriedade listServicos
	 *
	 * @param listServicos 
	 *           <code>ArrayList</code> a ser designado para listServicos.
	 * 
	 */
	public void setListServicos(ArrayList listServicos) {
		this.listServicos = listServicos;
	}

	/**
	 * M�todo getter para a propriedade listNotas
	 * @return  List de listNotas
	 */
	public List getListNotas() {
		return listNotas;
	}

	/**
	 * M�todo setter para a propriedade listNotas
	 * @param listNotas List
	 */
	public void setListNotas(List listNotas) {
		this.listNotas = listNotas;
	}
	
}

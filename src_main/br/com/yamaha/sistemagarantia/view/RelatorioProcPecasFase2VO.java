/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioProcPecasFase2VO.java
 *   .: Criação.....08 de agosto, 15:37
 *   .: Autor.......Gisele Panosso
 *   .: Descrição...Entidade "RelatorioProcPecasFase2VO".
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

	//----[ Atributos de classe e instância ]---------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;    
    
    /** Armazena o campo linhaProduto do Relatório Processamento de Peças - Fase2. */	
    private String linhaProduto;
    
    /** Armazena o campo lote do Relatório Processamento de Peças - Fase2. */
    private BigDecimal lote;
    
    /** Armazena o campo numeroSg do Relatório Processamento de Peças - Fase2. */
    private BigDecimal numeroSg;
    
    /** Armazena o campo chassi do Relatório Processamento de Peças - Fase2. */
    private String chassi;
        
    /** Armazena o campo numeroOs do Relatório Processamento de Peças - Fase2. */
    private String numeroOs;
    
    /** Armazena o campo dtAbertura do Relatório Processamento de Peças - Fase2. */
    private Date dtAbertura;
    
    /** Armazena o campo dtFechto do Relatório Processamento de Peças - Fase2. */
    private Date dtFechto;
    
    /** Armazena o campo km do Relatório Processamento de Peças - Fase2. */
    private BigDecimal km;
    
    /** Armazena o campo nomeEmp do Relatório Processamento de Peças - Fase2. */
    private String nomeEmp;
    
    /** Armazena o campo inscrEstadualEmp do Relatório Processamento de Peças - Fase2. */
    private String inscrEstadualEmp;
    
    /** Armazena o campo cnpjConc do Relatório Processamento de Peças - Fase2. */
    private String cnpjConc;
    
    /** Armazena o campo razaoSocialConc do Relatório Processamento de Peças - Fase2. */
    private String razaoSocialConc;
    
    /** Armazena o campo enderecoConc do Relatório Processamento de Peças - Fase2. */
    private String enderecoConc;
    
    /** Armazena o campo cepConc do Relatório Processamento de Peças - Fase2. */
    private BigDecimal cepConc;
   
    /** Armazena o campo cidadeConc do Relatório Processamento de Peças - Fase2. */
    private String cidadeConc;
    
    /** Armazena o campo estadoConc do Relatório Processamento de Peças - Fase2. */
    private String estadoConc;
    
    /** Armazena o campo quantidade do Relatório Processamento de Peças - Fase2. */
    private BigDecimal quantidade;
    
    /** Armazena o campo item do Relatório Processamento de Peças - Fase2. */
    private String item;
    
    /** Armazena o campo descricao do Relatório Processamento de Peças - Fase2. */
    private String descricao;   
    
    /** Armazena o campo vlUnitPeca do Relatório Processamento de Peças - Fase2. */    
    private BigDecimal vlUnitPeca;
    
    /** Armazena o campo vlTotalPeca do Relatório Processamento de Peças - Fase2. */
    private BigDecimal vlTotalPeca;
    
    /** Armazena o campo vlRemesPeca (valor remessa) do Relatório Processamento de Peças - Fase2. */
    private BigDecimal vlRemesPeca;
    
    /** Armazena o campo servico do Relatório Processamento de Peças - Fase2. */
    private String servico;
    
    /** Armazena o campo sintoma do Relatório Processamento de Peças - Fase2. */
    private String sintoma;
    
    /** Armazena o campo descrição do sintoma do Relatório Processamento de Peças - Fase2. */
    private String sintomaDesc;
    
    /** Armazena o campo condicao do Relatório Processamento de Peças - Fase2. */
    private String condicao;
    
    /** Armazena o campo descrição da condição do Relatório Processamento de Peças - Fase2. */
    private String condicaoDesc;
    
    /** Armazena o campo vlTotalServico do Relatório Processamento de Peças - Fase2. */
    private BigDecimal vlTotalServico;
    
    /** Armazena o campo vlServicoTerc do Relatório Processamento de Peças - Fase2. */
    private BigDecimal vlServicoTerc;
    
    private ArrayList listPecas;
    
    private ArrayList listServicos;
    
    private List listNotas;
    
	
	//	----[ Métodos Getter ]---------------------------------------------------

	/** Método getter para a propriedade cepConc.
	 *
	 *  @return o valor atual de cepConc.
	 */
	public BigDecimal getCepConc() {
		return cepConc;
	}

	/** Método getter para a propriedade chassi.
	 *
	 *  @return o valor atual de chassi.
	 */
	public String getChassi() {
		return chassi;
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
	public String getCnpjConc() {
		return cnpjConc;
	}

	/** Método getter para a propriedade condicao.
	 *
	 *  @return o valor atual de condicao.
	 */
	public String getCondicao() {
		return condicao;
	}

	/** Método getter para a propriedade descricao.
	 *
	 *  @return o valor atual de descricao.
	 */
	public String getDescricao() {
		return descricao;
	}

	/** Método getter para a propriedade dtAbertura.
	 *
	 *  @return o valor atual de dtAbertura.
	 */
	public Date getDtAbertura() {
		return dtAbertura;
	}

	/** Método getter para a propriedade dtFechto.
	 *
	 *  @return o valor atual de dtFechto.
	 */
	public Date getDtFechto() {
		return dtFechto;
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

	/** Método getter para a propriedade inscrEstadualEmp.
	 *
	 *  @return o valor atual de inscrEstadualEmp.
	 */
	public String getInscrEstadualEmp() {
		return inscrEstadualEmp;
	}

	/** Método getter para a propriedade item.
	 *
	 *  @return o valor atual de item.
	 */
	public String getItem() {
		return item;
	}

	/** Método getter para a propriedade km.
	 *
	 *  @return o valor atual de km.
	 */
	public BigDecimal getKm() {
		return km;
	}

	/** Método getter para a propriedade linhaProduto.
	 *
	 *  @return o valor atual de linhaProduto.
	 */
	public String getLinhaProduto() {
		return linhaProduto;
	}

	/** Método getter para a propriedade lote.
	 *
	 *  @return o valor atual de lote.
	 */
	public BigDecimal getLote() {
		return lote;
	}

	/** Método getter para a propriedade nomeEmp.
	 *
	 *  @return o valor atual de nomeEmp.
	 */
	public String getNomeEmp() {
		return nomeEmp;
	}

	/** Método getter para a propriedade numeroOs.
	 *
	 *  @return o valor atual de numeroOs.
	 */
	public String getNumeroOs() {
		return numeroOs;
	}

	/** Método getter para a propriedade numeroSg.
	 *
	 *  @return o valor atual de numeroSg.
	 */
	public BigDecimal getNumeroSg() {
		return numeroSg;
	}

	/** Método getter para a propriedade quantidade.
	 *
	 *  @return o valor atual de quantidade.
	 */
	public BigDecimal getQuantidade() {
		return quantidade;
	}

	/** Método getter para a propriedade razaoSocialConc.
	 *
	 *  @return o valor atual de razaoSocialConc.
	 */
	public String getRazaoSocialConc() {
		return razaoSocialConc;
	}

	/** Método getter para a propriedade servico.
	 *
	 *  @return o valor atual de servico.
	 */
	public String getServico() {
		return servico;
	}

	/** Método getter para a propriedade sintoma.
	 *
	 *  @return o valor atual de sintoma.
	 */
	public String getSintoma() {
		return sintoma;
	}

	/** Método getter para a propriedade vlRemesPeca.
	 *
	 *  @return o valor atual de vlRemesPeca.
	 */
	public BigDecimal getVlRemesPeca() {
		return vlRemesPeca;
	}

	/** Método getter para a propriedade vlServicoTerc.
	 *
	 *  @return o valor atual de vlServicoTerc.
	 */
	public BigDecimal getVlServicoTerc() {
		return vlServicoTerc;
	}

	/** Método getter para a propriedade vlTotalPeca.
	 *
	 *  @return o valor atual de vlTotalPeca.
	 */
	public BigDecimal getVlTotalPeca() {
		return vlTotalPeca;
	}

	/** Método getter para a propriedade vlTotalServico.
	 *
	 *  @return o valor atual de vlTotalServico.
	 */
	public BigDecimal getVlTotalServico() {
		return vlTotalServico;
	}

	/** Método getter para a propriedade vlUnitPeca.
	 *
	 *  @return o valor atual de vlUnitPeca.
	 */
	public BigDecimal getVlUnitPeca() {
		return vlUnitPeca;
	}

	/** Método getter para a propriedade condicaoDesc
	 * 
	 *  @return String
	 *
	 */
	public String getCondicaoDesc() {
		return condicaoDesc;
	}

	/** Método getter para a propriedade sintomaDesc
	 * 
	 *  @return String
	 *
	 */
	public String getSintomaDesc() {
		return sintomaDesc;
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

	/** Obtém o valor atual de chassi.
	 * 
	 *  @param chassi 
	 *    O novo valor para chassi.
	 */
	public void setChassi(String chassi) {
		this.chassi = chassi;
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
	public void setCnpjConc(String cnpjConc) {
		this.cnpjConc = cnpjConc;
	}

	/** Obtém o valor atual de condicao.
	 * 
	 *  @param condicao 
	 *    O novo valor para condicao.
	 */
	public void setCondicao(String condicao) {
		this.condicao = condicao;
	}

	/** Obtém o valor atual de descricao.
	 * 
	 *  @param descricao 
	 *    O novo valor para descricao.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/** Obtém o valor atual de dtAbertura.
	 * 
	 *  @param dtAbertura 
	 *    O novo valor para dtAbertura.
	 */
	public void setDtAbertura(Date dtAbertura) {
		this.dtAbertura = dtAbertura;
	}

	/** Obtém o valor atual de dtFechto.
	 * 
	 *  @param dtFechto 
	 *    O novo valor para dtFechto.
	 */
	public void setDtFechto(Date dtFechto) {
		this.dtFechto = dtFechto;
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

	/** Obtém o valor atual de inscrEstadualEmp.
	 * 
	 *  @param inscrEstadualEmp 
	 *    O novo valor para inscrEstadualEmp.
	 */
	public void setInscrEstadualEmp(String inscrEstadualEmp) {
		this.inscrEstadualEmp = inscrEstadualEmp;
	}

	/** Obtém o valor atual de item.
	 * 
	 *  @param item 
	 *    O novo valor para item.
	 */
	public void setItem(String item) {
		this.item = item;
	}

	/** Obtém o valor atual de km.
	 * 
	 *  @param km 
	 *    O novo valor para km.
	 */
	public void setKm(BigDecimal km) {
		this.km = km;
	}

	/** Obtém o valor atual de linhaProduto.
	 * 
	 *  @param linhaProduto 
	 *    O novo valor para linhaProduto.
	 */
	public void setLinhaProduto(String linhaProduto) {
		this.linhaProduto = linhaProduto;
	}

	/** Obtém o valor atual de lote.
	 * 
	 *  @param lote 
	 *    O novo valor para lote.
	 */
	public void setLote(BigDecimal lote) {
		this.lote = lote;
	}

	/** Obtém o valor atual de nomeEmp.
	 * 
	 *  @param nomeEmp 
	 *    O novo valor para nomeEmp.
	 */
	public void setNomeEmp(String nomeEmp) {
		this.nomeEmp = nomeEmp;
	}

	/** Obtém o valor atual de numeroOs.
	 * 
	 *  @param numeroOs 
	 *    O novo valor para numeroOs.
	 */
	public void setNumeroOs(String numeroOs) {
		this.numeroOs = numeroOs;
	}

	/** Obtém o valor atual de numeroSg.
	 * 
	 *  @param numeroSg 
	 *    O novo valor para numeroSg.
	 */
	public void setNumeroSg(BigDecimal numeroSg) {
		this.numeroSg = numeroSg;
	}

	/** Obtém o valor atual de quantidade.
	 * 
	 *  @param quantidade 
	 *    O novo valor para quantidade.
	 */
	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	/** Obtém o valor atual de razaoSocialConc.
	 * 
	 *  @param razaoSocialConc 
	 *    O novo valor para razaoSocialConc.
	 */
	public void setRazaoSocialConc(String razaoSocialConc) {
		this.razaoSocialConc = razaoSocialConc;
	}

	/** Obtém o valor atual de servico.
	 * 
	 *  @param servico 
	 *    O novo valor para servico.
	 */
	public void setServico(String servico) {
		this.servico = servico;
	}

	/** Obtém o valor atual de sintoma.
	 * 
	 *  @param sintoma 
	 *    O novo valor para sintoma.
	 */
	public void setSintoma(String sintoma) {
		this.sintoma = sintoma;
	}

	/** Obtém o valor atual de vlRemesPeca.
	 * 
	 *  @param vlRemesPeca 
	 *    O novo valor para vlRemesPeca.
	 */
	public void setVlRemesPeca(BigDecimal vlRemesPeca) {
		this.vlRemesPeca = vlRemesPeca;
	}

	/** Obtém o valor atual de vlServicoTerc.
	 * 
	 *  @param vlServicoTerc 
	 *    O novo valor para vlServicoTerc.
	 */
	public void setVlServicoTerc(BigDecimal vlServicoTerc) {
		this.vlServicoTerc = vlServicoTerc;
	}

	/** Obtém o valor atual de vlTotalPeca.
	 * 
	 *  @param vlTotalPeca 
	 *    O novo valor para vlTotalPeca.
	 */
	public void setVlTotalPeca(BigDecimal vlTotalPeca) {
		this.vlTotalPeca = vlTotalPeca;
	}

	/** Obtém o valor atual de vlTotalServico.
	 * 
	 *  @param vlTotalServico 
	 *    O novo valor para vlTotalServico.
	 */
	public void setVlTotalServico(BigDecimal vlTotalServico) {
		this.vlTotalServico = vlTotalServico;
	}

	/** Obtém o valor atual de vlUnitPeca.
	 * 
	 *  @param vlUnitPeca 
	 *    O novo valor para vlUnitPeca.
	 */
	public void setVlUnitPeca(BigDecimal vlUnitPeca) {
		this.vlUnitPeca = vlUnitPeca;
	} 
	
	/** Método setter para a propriedade condicaoDesc
	 *
	 * @param condicaoDesc 
	 *           <code>String</code> a ser designado para condicaoDesc.
	 * 
	 */
	public void setCondicaoDesc(String condicaoDesc) {
		this.condicaoDesc = condicaoDesc;
	}

	/** Método setter para a propriedade sintomaDesc
	 *
	 * @param sintomaDesc 
	 *           <code>String</code> a ser designado para sintomaDesc.
	 * 
	 */
	public void setSintomaDesc(String sintomaDesc) {
		this.sintomaDesc = sintomaDesc;
	}	
	
	
	//	----[ Métodos de Serviços da Classe ]---------------------------------------------------
	

	/**
	 * Retorna o valor Unitário da peça formatado para Moeda 
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
	 * Retorna o valor total de serviço formatado para Moeda 
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
	 * Retorna o valor total de peça formatado para Moeda 
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
	 * Retorna o valor do serviço de terceiro formatado para Moeda 
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

	/** Método getter para a propriedade listPecas
	 * 
	 *  @return JRBeanCollectionDataSource
	 *
	 */
	public JRBeanCollectionDataSource getListPecasDts() {
		return new JRBeanCollectionDataSource(this.listPecas);
	}
	
	/** Método getter para a propriedade listPecas
	 * 
	 *  @return ArrayList
	 *
	 */
	public ArrayList getListPecas() {
		return this.listPecas;
	}

	/** Método setter para a propriedade listPecas
	 *
	 * @param listPecas 
	 *           <code>ArrayList</code> a ser designado para listPecas.
	 * 
	 */
	public void setListPecas(ArrayList listPecas) {
		this.listPecas = listPecas;
	}
	
	/** Método getter para a propriedade listServicos
	 * 
	 *  @return JRBeanCollectionDataSource
	 *
	 */
	public JRBeanCollectionDataSource getListServicosDts() {
		return new JRBeanCollectionDataSource(this.listServicos);
	}
	
	/** Método getter para a propriedade listNotas
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
	
	/** Método getter para a propriedade listServicos
	 * 
	 *  @return ArrayList
	 *
	 */
	public ArrayList getListServicos() {
		return this.listServicos;
	}

	/** Método setter para a propriedade listServicos
	 *
	 * @param listServicos 
	 *           <code>ArrayList</code> a ser designado para listServicos.
	 * 
	 */
	public void setListServicos(ArrayList listServicos) {
		this.listServicos = listServicos;
	}

	/**
	 * Método getter para a propriedade listNotas
	 * @return  List de listNotas
	 */
	public List getListNotas() {
		return listNotas;
	}

	/**
	 * Método setter para a propriedade listNotas
	 * @param listNotas List
	 */
	public void setListNotas(List listNotas) {
		this.listNotas = listNotas;
	}
	
}

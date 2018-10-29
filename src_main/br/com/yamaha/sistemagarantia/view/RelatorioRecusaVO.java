/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioRecusaVO.java
 *   .: Cria��o.....16 de julho, 15:20
 *   .: Autor.......Gisele Panosso
 *   .: Descri��o...Entidade "RelatorioRecusaVO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import br.com.resource.infra.model.EntityObject;

public class RelatorioRecusaVO extends EntityObject {

	//----[ Atributos de classe e inst�ncia ]---------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;    
    

    /** Armazena o campo servico do Relat�rio de Recusa. */
    private String servico; 
    
    /** Armazena o campo chassi do Relat�rio de Recusa. */
    private String chassi;  
    
    /** Armazena o campo dtVenda do Relat�rio de Recusa. */
    private Date dtVenda; 
    
    /** Armazena o campo dtServico do Relat�rio de Recusa. */
    private Date dtServico;   
    
    /** Armazena o campo lote do Relat�rio de Recusa. */
    private Serializable lote;
    
    /** Armazena o campo kmHr do Relat�rio de Recusa. */
    private BigDecimal kmHr;  
    
    /** Armazena o campo peca do Relat�rio de Recusa. */
    private String peca;     

    /** Armazena o campo recusa do Relat�rio de Recusa. */
    private String recusa;      
    
    /** Armazena o campo concessionaria do Relat�rio de Recusa. */
    private String concessionaria;  
    
    /** Armazena o campo endereco do Relat�rio de Recusa. */
    private String endereco;  
    
    /** Armazena o campo cidade do Relat�rio de Recusa. */
    private String cidade;  
    
    /** Armazena o campo estado do Relat�rio de Recusa. */
    private String estado;  
    
    /** Armazena o campo cnpj do Relat�rio de Recusa. */
    private BigDecimal cnpj;  
    
    /** Armazena o campo linhaProduto do Relat�rio de Recusa. */
    private String linhaProduto;
    
    /** Armazena o campo empresa do Relat�rio de Recusa. */
    private String empresa;    
    
    /** Armazena o campo nrRevisao do Relat�rio de Recusa. */
    private Serializable nrRevisao;    

    
    //	----[ M�todos Getter ]---------------------------------------------------
    
	/** M�todo getter para a propriedade chassi.
	 *
	 *  @return o valor atual de chassi.
	 */
	public String getChassi() {
		return chassi;
	}

	/** M�todo getter para a propriedade cidade.
	 *
	 *  @return o valor atual de cidade.
	 */
	public String getCidade() {
		return cidade;
	}

	/** M�todo getter para a propriedade cnpj.
	 *
	 *  @return o valor atual de cnpj.
	 */
	public BigDecimal getCnpj() {
		return cnpj;
	}

	/** M�todo getter para a propriedade concessionaria.
	 *
	 *  @return o valor atual de concessionaria.
	 */
	public String getConcessionaria() {
		return concessionaria;
	}

	/** M�todo getter para a propriedade dtServico.
	 *
	 *  @return o valor atual de dtServico.
	 */
	public Date getDtServico() {
		return dtServico;
	}

	/** M�todo getter para a propriedade dtVenda.
	 *
	 *  @return o valor atual de dtVenda.
	 */
	public Date getDtVenda() {
		return dtVenda;
	}

	/** M�todo getter para a propriedade endereco.
	 *
	 *  @return o valor atual de endereco.
	 */
	public String getEndereco() {
		return endereco;
	}

	/** M�todo getter para a propriedade estado.
	 *
	 *  @return o valor atual de estado.
	 */
	public String getEstado() {
		return estado;
	}

	/** M�todo getter para a propriedade kmHr.
	 *
	 *  @return o valor atual de kmHr.
	 */
	public BigDecimal getKmHr() {
		return kmHr;
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
	public Serializable getLote() {
		return lote;
	}

	/** M�todo getter para a propriedade peca.
	 *
	 *  @return o valor atual de peca.
	 */
	public String getPeca() {
		return peca;
	}

	/** M�todo getter para a propriedade recusa.
	 *
	 *  @return o valor atual de recusa.
	 */
	public String getRecusa() {
		return recusa;
	}

	/** M�todo getter para a propriedade servico.
	 *
	 *  @return o valor atual de servico.
	 */
	public String getServico() {
		return servico;
	}

	/** M�todo getter para a propriedade empresa.
	 *
	 *  @return o valor atual de empresa.
	 */
	public String getEmpresa() {
		return empresa;
	}
	   
	/** M�todo getter para a propriedade nrRevisao.
	 *
	 *  @return o valor atual de nrRevisao.
	 */
	public Serializable getNrRevisao() {
		return nrRevisao;
	}
	
    //	----[ M�todos Setter ]---------------------------------------------------
 
	/** Obt�m o valur atual de chassi.
	 * 
	 *  @param chassi 
	 *    O novo valor para chassi.
	 */
	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	/** Obt�m o valur atual de cidade.
	 * 
	 *  @param cidade 
	 *    O novo valor para cidade.
	 */
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	/** Obt�m o valur atual de cnpj.
	 * 
	 *  @param cnpj 
	 *    O novo valor para cnpj.
	 */
	public void setCnpj(BigDecimal cnpj) {
		this.cnpj = cnpj;
	}

	/** Obt�m o valur atual de concessionaria.
	 * 
	 *  @param concessionaria 
	 *    O novo valor para concessionaria.
	 */
	public void setConcessionaria(String concessionaria) {
		this.concessionaria = concessionaria;
	}

	/** Obt�m o valur atual de dtServico.
	 * 
	 *  @param dtServico 
	 *    O novo valor para dtServico.
	 */
	public void setDtServico(Date dtServico) {
		this.dtServico = dtServico;
	}

	/** Obt�m o valur atual de dtVenda.
	 * 
	 *  @param dtVenda 
	 *    O novo valor para dtVenda.
	 */
	public void setDtVenda(Date dtVenda) {
		this.dtVenda = dtVenda;
	}

	/** Obt�m o valur atual de endereco.
	 * 
	 *  @param endereco 
	 *    O novo valor para endereco.
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/** Obt�m o valur atual de estado.
	 * 
	 *  @param estado 
	 *    O novo valor para estado.
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/** Obt�m o valur atual de kmHr.
	 * 
	 *  @param kmHr 
	 *    O novo valor para kmHr.
	 */
	public void setKmHr(BigDecimal kmHr) {
		this.kmHr = kmHr;
	}

	/** Obt�m o valur atual de linhaProduto.
	 * 
	 *  @param linhaProduto 
	 *    O novo valor para linhaProduto.
	 */
	public void setLinhaProduto(String linhaProduto) {
		this.linhaProduto = linhaProduto;
	}

	/** Obt�m o valur atual de lote.
	 * 
	 *  @param lote 
	 *    O novo valor para lote.
	 */
	public void setLote(Serializable lote) {
		this.lote = lote;
	}

	/** Obt�m o valur atual de peca.
	 * 
	 *  @param peca 
	 *    O novo valor para peca.
	 */
	public void setPeca(String peca) {
		this.peca = peca;
	}

	/** Obt�m o valur atual de recusa.
	 * 
	 *  @param recusa 
	 *    O novo valor para recusa.
	 */
	public void setRecusa(String recusa) {
		this.recusa = recusa;
	}

	/** Obt�m o valur atual de servico.
	 * 
	 *  @param servico 
	 *    O novo valor para servico.
	 */
	public void setServico(String servico) {
		this.servico = servico;
	}

	/** Obt�m o valor atual de empresa.
	 * 
	 *  @param empresa 
	 *    O novo valor para empresa.
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/** Obt�m o valur atual de nrRevisao.
	 * 
	 *  @param nrRevisao 
	 *    O novo valor para nrRevisao.
	 */
	public void setNrRevisao(Serializable nrRevisao) {
		this.nrRevisao = nrRevisao;
	}

}

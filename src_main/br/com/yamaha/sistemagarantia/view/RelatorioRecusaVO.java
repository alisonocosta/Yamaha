/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioRecusaVO.java
 *   .: Criação.....16 de julho, 15:20
 *   .: Autor.......Gisele Panosso
 *   .: Descrição...Entidade "RelatorioRecusaVO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import br.com.resource.infra.model.EntityObject;

public class RelatorioRecusaVO extends EntityObject {

	//----[ Atributos de classe e instância ]---------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;    
    

    /** Armazena o campo servico do Relatório de Recusa. */
    private String servico; 
    
    /** Armazena o campo chassi do Relatório de Recusa. */
    private String chassi;  
    
    /** Armazena o campo dtVenda do Relatório de Recusa. */
    private Date dtVenda; 
    
    /** Armazena o campo dtServico do Relatório de Recusa. */
    private Date dtServico;   
    
    /** Armazena o campo lote do Relatório de Recusa. */
    private Serializable lote;
    
    /** Armazena o campo kmHr do Relatório de Recusa. */
    private BigDecimal kmHr;  
    
    /** Armazena o campo peca do Relatório de Recusa. */
    private String peca;     

    /** Armazena o campo recusa do Relatório de Recusa. */
    private String recusa;      
    
    /** Armazena o campo concessionaria do Relatório de Recusa. */
    private String concessionaria;  
    
    /** Armazena o campo endereco do Relatório de Recusa. */
    private String endereco;  
    
    /** Armazena o campo cidade do Relatório de Recusa. */
    private String cidade;  
    
    /** Armazena o campo estado do Relatório de Recusa. */
    private String estado;  
    
    /** Armazena o campo cnpj do Relatório de Recusa. */
    private BigDecimal cnpj;  
    
    /** Armazena o campo linhaProduto do Relatório de Recusa. */
    private String linhaProduto;
    
    /** Armazena o campo empresa do Relatório de Recusa. */
    private String empresa;    
    
    /** Armazena o campo nrRevisao do Relatório de Recusa. */
    private Serializable nrRevisao;    

    
    //	----[ Métodos Getter ]---------------------------------------------------
    
	/** Método getter para a propriedade chassi.
	 *
	 *  @return o valor atual de chassi.
	 */
	public String getChassi() {
		return chassi;
	}

	/** Método getter para a propriedade cidade.
	 *
	 *  @return o valor atual de cidade.
	 */
	public String getCidade() {
		return cidade;
	}

	/** Método getter para a propriedade cnpj.
	 *
	 *  @return o valor atual de cnpj.
	 */
	public BigDecimal getCnpj() {
		return cnpj;
	}

	/** Método getter para a propriedade concessionaria.
	 *
	 *  @return o valor atual de concessionaria.
	 */
	public String getConcessionaria() {
		return concessionaria;
	}

	/** Método getter para a propriedade dtServico.
	 *
	 *  @return o valor atual de dtServico.
	 */
	public Date getDtServico() {
		return dtServico;
	}

	/** Método getter para a propriedade dtVenda.
	 *
	 *  @return o valor atual de dtVenda.
	 */
	public Date getDtVenda() {
		return dtVenda;
	}

	/** Método getter para a propriedade endereco.
	 *
	 *  @return o valor atual de endereco.
	 */
	public String getEndereco() {
		return endereco;
	}

	/** Método getter para a propriedade estado.
	 *
	 *  @return o valor atual de estado.
	 */
	public String getEstado() {
		return estado;
	}

	/** Método getter para a propriedade kmHr.
	 *
	 *  @return o valor atual de kmHr.
	 */
	public BigDecimal getKmHr() {
		return kmHr;
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
	public Serializable getLote() {
		return lote;
	}

	/** Método getter para a propriedade peca.
	 *
	 *  @return o valor atual de peca.
	 */
	public String getPeca() {
		return peca;
	}

	/** Método getter para a propriedade recusa.
	 *
	 *  @return o valor atual de recusa.
	 */
	public String getRecusa() {
		return recusa;
	}

	/** Método getter para a propriedade servico.
	 *
	 *  @return o valor atual de servico.
	 */
	public String getServico() {
		return servico;
	}

	/** Método getter para a propriedade empresa.
	 *
	 *  @return o valor atual de empresa.
	 */
	public String getEmpresa() {
		return empresa;
	}
	   
	/** Método getter para a propriedade nrRevisao.
	 *
	 *  @return o valor atual de nrRevisao.
	 */
	public Serializable getNrRevisao() {
		return nrRevisao;
	}
	
    //	----[ Métodos Setter ]---------------------------------------------------
 
	/** Obtém o valur atual de chassi.
	 * 
	 *  @param chassi 
	 *    O novo valor para chassi.
	 */
	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	/** Obtém o valur atual de cidade.
	 * 
	 *  @param cidade 
	 *    O novo valor para cidade.
	 */
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	/** Obtém o valur atual de cnpj.
	 * 
	 *  @param cnpj 
	 *    O novo valor para cnpj.
	 */
	public void setCnpj(BigDecimal cnpj) {
		this.cnpj = cnpj;
	}

	/** Obtém o valur atual de concessionaria.
	 * 
	 *  @param concessionaria 
	 *    O novo valor para concessionaria.
	 */
	public void setConcessionaria(String concessionaria) {
		this.concessionaria = concessionaria;
	}

	/** Obtém o valur atual de dtServico.
	 * 
	 *  @param dtServico 
	 *    O novo valor para dtServico.
	 */
	public void setDtServico(Date dtServico) {
		this.dtServico = dtServico;
	}

	/** Obtém o valur atual de dtVenda.
	 * 
	 *  @param dtVenda 
	 *    O novo valor para dtVenda.
	 */
	public void setDtVenda(Date dtVenda) {
		this.dtVenda = dtVenda;
	}

	/** Obtém o valur atual de endereco.
	 * 
	 *  @param endereco 
	 *    O novo valor para endereco.
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/** Obtém o valur atual de estado.
	 * 
	 *  @param estado 
	 *    O novo valor para estado.
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/** Obtém o valur atual de kmHr.
	 * 
	 *  @param kmHr 
	 *    O novo valor para kmHr.
	 */
	public void setKmHr(BigDecimal kmHr) {
		this.kmHr = kmHr;
	}

	/** Obtém o valur atual de linhaProduto.
	 * 
	 *  @param linhaProduto 
	 *    O novo valor para linhaProduto.
	 */
	public void setLinhaProduto(String linhaProduto) {
		this.linhaProduto = linhaProduto;
	}

	/** Obtém o valur atual de lote.
	 * 
	 *  @param lote 
	 *    O novo valor para lote.
	 */
	public void setLote(Serializable lote) {
		this.lote = lote;
	}

	/** Obtém o valur atual de peca.
	 * 
	 *  @param peca 
	 *    O novo valor para peca.
	 */
	public void setPeca(String peca) {
		this.peca = peca;
	}

	/** Obtém o valur atual de recusa.
	 * 
	 *  @param recusa 
	 *    O novo valor para recusa.
	 */
	public void setRecusa(String recusa) {
		this.recusa = recusa;
	}

	/** Obtém o valur atual de servico.
	 * 
	 *  @param servico 
	 *    O novo valor para servico.
	 */
	public void setServico(String servico) {
		this.servico = servico;
	}

	/** Obtém o valor atual de empresa.
	 * 
	 *  @param empresa 
	 *    O novo valor para empresa.
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/** Obtém o valur atual de nrRevisao.
	 * 
	 *  @param nrRevisao 
	 *    O novo valor para nrRevisao.
	 */
	public void setNrRevisao(Serializable nrRevisao) {
		this.nrRevisao = nrRevisao;
	}

}

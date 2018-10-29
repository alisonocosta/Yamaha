/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioVerificacaoVO.java
 *   .: Cria��o.....16 de julho, 09:00
 *   .: Autor.......Gisele Panosso
 *   .: Descri��o...Entidade "RelatorioVerificacaoVO".
 */

package br.com.yamaha.sistemagarantia.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import br.com.resource.infra.model.EntityObject;
import br.com.resource.infra.utils.DateUtils;
import br.com.resource.infra.utils.NumberUtils;

public class RelatorioVerificacaoVO extends EntityObject {

	//----[ Atributos de classe e inst�ncia ]---------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;    
  
    /** Armazena o campo cnpj do Relat�rio de Verifica��o. */
    private Long cnpj;    

    /** Armazena o campo concessionaria do Relat�rio de Verifica��o. */
    private String concessionaria;
               
    /** Armazena o campo endereco do Relat�rio de Verifica��o. */
    private String endereco;
    
    /** Armazena o campo cep do Relat�rio de Verifica��o. */
    private Serializable cep;
    
    /** Armazena o campo cidade do Relat�rio de Verifica��o. */
    private String cidade;    
     
    /** Armazena o campo estado do Relat�rio de Verifica��o. */
    private String estado; 
           
    /** Armazena o campo lote do Relat�rio de Verifica��o. */
    private Long lote;  
    
    /** Armazena o campo ordemServico do Relat�rio de Verifica��o. */
    private Long ordemServico;  
    
    /** Armazena o campo garantiaId do Relat�rio de Verifica��o. */
    private Long garantiaId;
    
    /** Armazena o campo dtServico do Relat�rio de Verifica��o. */
    private Date dtServico;     
    
    /** Armazena o campo chassi do Relat�rio de Verifica��o. */
    private String chassi;   
    
    /** Armazena o campo quilometragem do Relat�rio de Verifica��o. */
    private BigDecimal quilometragem;  
    
    /** Armazena o campo pe�a do Relat�rio de Verifica��o. */
    private String peca;    
    
    /** Armazena o campo descricao do Relat�rio de Verifica��o. */
    private String descricao;   

    /** Armazena o campo qtdPecas do Relat�rio de Verifica��o. */
    private Long qtdPecas;    

    /** Armazena o campo valorPeca do Relat�rio de Verifica��o. */
    private BigDecimal valorPeca; 
    
    /** Armazena o campo (valor) totalPecas do Relat�rio de Verifica��o. */
    private BigDecimal totalPecas;

    
    //	----[ M�todos Getter ]---------------------------------------------------

    /** M�todo getter para a propriedade cep.
	 *
	 *  @return o valor atual de cep.
	 */
	public Serializable getCep() {
		return cep;
	}
	
	/** M�todo getter para a propriedade cep.
	 *
	 *  @return o valor atual de cep.
	 */
	public String getStrCep() {
		return cep.toString();
	}

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
	public Long getCnpj() {
		return cnpj;
	}
	
	/** M�todo getter para a propriedade cnpj.
	 *
	 *  @return o valor atual de cnpj.
	 */
	public String getStrCnpj() {
		return cnpj.toString();
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
	
	/** M�todo getter para a propriedade dtServico.
	 *
	 *  @return o valor atual de dtServico.
	 */
	public String getStrDtServico() {
		return DateUtils.applyMask(dtServico);
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

	/** M�todo getter para a propriedade lote.
	 *
	 *  @return o valor atual de lote.
	 */
	public Long getLote() {
		return lote;
	}

	/** M�todo getter para a propriedade ordemServico.
	 *
	 *  @return o valor atual de ordemServico.
	 */
	public Long getOrdemServico() {
		return ordemServico;
	}

	/** M�todo getter para a propriedade qtdPecas.
	 *
	 *  @return o valor atual de qtdPecas.
	 */
	public Long getQtdPecas() {
		return qtdPecas;
	}

	/** M�todo getter para a propriedade quilometragem.
	 *
	 *  @return o valor atual de quilometragem.
	 */
	public BigDecimal getQuilometragem() {
		return quilometragem;
	}
	
	/** M�todo getter para a propriedade quilometragem.
	 *
	 *  @return o valor atual de quilometragem.
	 */
	public String getStrQuilometragem() {
		return NumberUtils.formatNumberMil(quilometragem.doubleValue());
	}

	/** M�todo getter para a propriedade totalPecas.
	 *
	 *  @return o valor atual de totalPecas.
	 */
	public BigDecimal getTotalPecas() {
		return totalPecas;
	}
	
	/** M�todo getter para a propriedade totalPecas.
	 *
	 *  @return o valor atual de totalPecas.
	 */
	public String getStrTotalPecas() {
		return NumberUtils.formatNumberCurrencyMil(totalPecas.doubleValue());
	}

	/** M�todo getter para a propriedade valorPeca.
	 *
	 *  @return o valor atual de valorPeca.
	 */
	public BigDecimal getValorPeca() {
		return valorPeca;
	}
	
	/** M�todo getter para a propriedade valorPeca.
	 *
	 *  @return o valor atual de valorPeca.
	 */
	public String getStrValorPeca() {
		return NumberUtils.formatNumberCurrencyMil(valorPeca.doubleValue());
	}
	
	/** M�todo getter para a propriedade garantiaId
	 *
	 *  @return Long de garantiaId
	 */
	public Long getGarantiaId() {
		return garantiaId;
	}

	/** M�todo getter para a propriedade peca
	 *
	 *  @return String de peca
	 */
	public String getPeca() {
		return peca;
	}

	/** M�todo getter para a propriedade descricao
	 *
	 *  @return String de descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	

	//	----[ M�todos Setter ]---------------------------------------------------
    	
	/** Obt�m o valur atual de cep.
	 * 
	 *  @param cep 
	 *    O novo valor para cep.
	 */
	public void setCep(Serializable cep) {
		this.cep = cep;
	}

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
	public void setCnpj(Long cnpj) {
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

	/** Obt�m o valur atual de lote.
	 * 
	 *  @param lote 
	 *    O novo valor para lote.
	 */
	public void setLote(Long lote) {
		this.lote = lote;
	}

	/** Obt�m o valur atual de ordemServico.
	 * 
	 *  @param ordemServico 
	 *    O novo valor para ordemServico.
	 */
	public void setOrdemServico(Long ordemServico) {
		this.ordemServico = ordemServico;
	}

	/** Obt�m o valur atual de qtdPecas.
	 * 
	 *  @param qtdPecas 
	 *    O novo valor para qtdPecas.
	 */
	public void setQtdPecas(Long qtdPecas) {
		this.qtdPecas = qtdPecas;
	}

	/** Obt�m o valur atual de quilometragem.
	 * 
	 *  @param quilometragem 
	 *    O novo valor para quilometragem.
	 */
	public void setQuilometragem(BigDecimal quilometragem) {
		this.quilometragem = quilometragem;
	}

	/** Obt�m o valur atual de totalPecas.
	 * 
	 *  @param totalPecas 
	 *    O novo valor para totalPecas.
	 */
	public void setTotalPecas(BigDecimal totalPecas) {
		this.totalPecas = totalPecas;
	}

	/** Obt�m o valur atual de valorPeca.
	 * 
	 *  @param valorPeca 
	 *    O novo valor para valorPeca.
	 */
	public void setValorPeca(BigDecimal valorPeca) {
		this.valorPeca = valorPeca;
	}
	

	/** M�todo setter para a propriedade garantiaId
	 *
	 * @param garantiaId Long
	 */
	public void setGarantiaId(Long garantiaId) {
		this.garantiaId = garantiaId;
	}

	/** M�todo setter para a propriedade peca
	 *
	 * @param peca String
	 */
	public void setPeca(String peca) {
		this.peca = peca;
	}

	/** M�todo setter para a propriedade descricao
	 *
	 * @param descricao String
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}    
	
	
}


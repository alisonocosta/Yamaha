/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioImportedPartsInvoiceVO.java
 *   .: Cria��o.....04 de outubro de 2007, 16:21
 *   .: Autor.......Gisele Panosso
 *   .: Descri��o...Entidade "RelatorioImportedPartsInvoiceVO".
 */
package br.com.yamaha.sistemagarantia.view;

import br.com.resource.infra.model.EntityObject;

public class RelatorioImportedPartsInvoiceVO extends EntityObject {
	
	//----[ Atributos de classe e inst�ncia ]---------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;  
    
    /** Armazena o parametro (banco, agencia e conta) para recuperar os dados do banco*/
    private String parametro;

    /** Armazena o campo valor do parametro (banco, agencia e conta) para recuperar os dados do banco*/
    private String valor;
    
    /** Armazena o campo banco (nome do banco)*/
    private String banco;
    
    /** Armazena o campo endere�o do banco*/
    private String endereco;
    
    /** Armazena o campo localiza��o do banco (cidade/estado/pais)*/
    private String localidade;
    
    /** Armazena o numero da conta do banco*/
    private String conta;
    
    /** Armazena o numero da agencia do banco*/
    private String agencia;

    /** Armazena percentual de comiss�o*/
    private Double comissao;
    
    /** Armazena valor do d�lar contratado (na data final)*/
    private Double vlDolar;
    
    /** Armazena a razaoSocial da empresa*/
    private String empresa;
    
    /** Armazena o endere�o da empresa*/
    private String endEmp;
    
    /** Armazena o cnpj da empresa*/
    private String cnpjEmp;
    
    /** Armazena a inscri��o estadual da empresa*/
    private String incEstEmp;
    
    /** Armazena a razaoSocial do fornecedor*/
    private String fornecedor;
    
    /** Armazena endere�o do fornecedor*/
    private String endFornec;
    
    /** Armazena a cidade do fornecedor*/
    private String cidFornec;
    
    /** Armazena pa�s do fornecedor*/
    private String paisFornec;
    
    
    //	----[ M�todos Getter ]---------------------------------------------------
        
	/** M�todo getter para a propriedade agencia.
	 *
	 *  @return o valor atual de agencia.
	 */
	public String getAgencia() {
		return agencia;
	}

	/** M�todo getter para a propriedade banco.
	 *
	 *  @return o valor atual de banco.
	 */
	public String getBanco() {
		return banco;
	}

	/** M�todo getter para a propriedade comissao.
	 *
	 *  @return o valor atual de comissao.
	 */
	public Double getComissao() {
		return comissao;
	}

	/** M�todo getter para a propriedade conta.
	 *
	 *  @return o valor atual de conta.
	 */
	public String getConta() {
		return conta;
	}

	/** M�todo getter para a propriedade endereco.
	 *
	 *  @return o valor atual de endereco.
	 */
	public String getEndereco() {
		return endereco;
	}

	/** M�todo getter para a propriedade localidade.
	 *
	 *  @return o valor atual de localidade.
	 */
	public String getLocalidade() {
		return localidade;
	}

	/** M�todo getter para a propriedade parametro.
	 *
	 *  @return o valor atual de parametro.
	 */
	public String getParametro() {
		return parametro;
	}

	/** M�todo getter para a propriedade valor.
	 *
	 *  @return o valor atual de valor.
	 */
	public String getValor() {
		return valor;
	}

	/** M�todo getter para a propriedade vlDolar.
	 *
	 *  @return o valor atual de vlDolar.
	 */
	public Double getVlDolar() {
		return vlDolar;
	}
	
	/** M�todo getter para a propriedade cidFornec.
	 *
	 *  @return o valor atual de cidFornec.
	 */
	public String getCidFornec() {
		return cidFornec;
	}

	/** M�todo getter para a propriedade cnpjEmp.
	 *
	 *  @return o valor atual de cnpjEmp.
	 */
	public String getCnpjEmp() {
		return cnpjEmp;
	}

	/** M�todo getter para a propriedade empresa.
	 *
	 *  @return o valor atual de empresa.
	 */
	public String getEmpresa() {
		return empresa;
	}

	/** M�todo getter para a propriedade endEmp.
	 *
	 *  @return o valor atual de endEmp.
	 */
	public String getEndEmp() {
		return endEmp;
	}

	/** M�todo getter para a propriedade endFornec.
	 *
	 *  @return o valor atual de endFornec.
	 */
	public String getEndFornec() {
		return endFornec;
	}

	/** M�todo getter para a propriedade fornecedor.
	 *
	 *  @return o valor atual de fornecedor.
	 */
	public String getFornecedor() {
		return fornecedor;
	}

	/** M�todo getter para a propriedade incEstEmp.
	 *
	 *  @return o valor atual de incEstEmp.
	 */
	public String getIncEstEmp() {
		return incEstEmp;
	}

	/** M�todo getter para a propriedade paisFornec.
	 *
	 *  @return o valor atual de paisFornec.
	 */
	public String getPaisFornec() {
		return paisFornec;
	}
	
    //	----[ M�todos Setter ]---------------------------------------------------
	   	
	/** Obt�m o valur atual de agencia.
	 * 
	 *  @param agencia 
	 *    O novo valor para agencia.
	 */
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	/** Obt�m o valor atual de banco.
	 * 
	 *  @param banco 
	 *    O novo valor para banco.
	 */
	public void setBanco(String banco) {
		this.banco = banco;
	}

	/** Obt�m o valor atual de comissao.
	 * 
	 *  @param comissao 
	 *    O novo valor para comissao.
	 */
	public void setComissao(Double comissao) {
		this.comissao = comissao;
	}

	/** Obt�m o valor atual de conta.
	 * 
	 *  @param conta 
	 *    O novo valor para conta.
	 */
	public void setConta(String conta) {
		this.conta = conta;
	}

	/** Obt�m o valor atual de endereco.
	 * 
	 *  @param endereco 
	 *    O novo valor para endereco.
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/** Obt�m o valor atual de localidade.
	 * 
	 *  @param localidade 
	 *    O novo valor para localidade.
	 */
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	/** Obt�m o valor atual de parametro.
	 * 
	 *  @param parametro 
	 *    O novo valor para parametro.
	 */
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	/** Obt�m o valor atual de valor.
	 * 
	 *  @param valor 
	 *    O novo valor para valor.
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

	/** Obt�m o valor atual de vlDolar.
	 * 
	 *  @param vlDolar 
	 *    O novo valor para vlDolar.
	 */
	public void setVlDolar(Double vlDolar) {
		this.vlDolar = vlDolar;
	}

	/** Obt�m o valor atual de cidFornec.
	 * 
	 *  @param cidFornec 
	 *    O novo valor para cidFornec.
	 */
	public void setCidFornec(String cidFornec) {
		this.cidFornec = cidFornec;
	}

	/** Obt�m o valor atual de cnpjEmp.
	 * 
	 *  @param cnpjEmp 
	 *    O novo valor para cnpjEmp.
	 */
	public void setCnpjEmp(String cnpjEmp) {
		this.cnpjEmp = cnpjEmp;
	}

	/** Obt�m o valor atual de empresa.
	 * 
	 *  @param empresa 
	 *    O novo valor para empresa.
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/** Obt�m o valor atual de endEmp.
	 * 
	 *  @param endEmp 
	 *    O novo valor para endEmp.
	 */
	public void setEndEmp(String endEmp) {
		this.endEmp = endEmp;
	}

	/** Obt�m o valor atual de endFornec.
	 * 
	 *  @param endFornec 
	 *    O novo valor para endFornec.
	 */
	public void setEndFornec(String endFornec) {
		this.endFornec = endFornec;
	}

	/** Obt�m o valor atual de fornecedor.
	 * 
	 *  @param fornecedor 
	 *    O novo valor para fornecedor.
	 */
	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	/** Obt�m o valor atual de incEstEmp.
	 * 
	 *  @param incEstEmp 
	 *    O novo valor para incEstEmp.
	 */
	public void setIncEstEmp(String incEstEmp) {
		this.incEstEmp = incEstEmp;
	}

	/** Obt�m o valor atual de paisFornec.
	 * 
	 *  @param paisFornec 
	 *    O novo valor para paisFornec.
	 */
	public void setPaisFornec(String paisFornec) {
		this.paisFornec = paisFornec;
	}

}
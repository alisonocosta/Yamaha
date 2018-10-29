/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioPagamentosVO.java
 *   .: Cria��o.....16 de julho, 16:45
 *   .: Autor.......Gisele Panosso
 *   .: Descri��o...Entidade "RelatorioPagamentosVO".
 */

package br.com.yamaha.sistemagarantia.view;

import br.com.resource.infra.model.EntityObject;

public class RelatorioPagamentosVO extends EntityObject {

	//----[ Atributos de classe e inst�ncia ]---------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;    
    
    /** Armazena o campo cnpj do Relat�rio de Pagamentos. */
    private Long cnpj;  
    
    /** Armazena o campo concessionaria do Relat�rio de Pagamentos. */
    private String concessionaria;  
    
    /** Armazena o campo vlPeca do Relat�rio de Pagamentos. */
    private Long valorPeca;     
    
    /** Armazena o campo vlPeca do Relat�rio de Pagamentos. */
    private Long valorServico;   
    
    /** Armazena o campo vlPeca do Relat�rio de Pagamentos. */
    private Long valorServico3;       

    /** Armazena o campo vlPeca do Relat�rio de Pagamentos. */
    private Long valorRevisao;      
    
    //	----[ M�todos Getter ]---------------------------------------------------

	/** M�todo getter para o campo cnpj
	 * 
	 * @return Long
	 *
	 */
	public Long getCnpj() {
		return cnpj;
	}

	/** M�todo getter para o campo concessionaria
	 * 
	 * @return String
	 *
	 */
	public String getConcessionaria() {
		return concessionaria;
	}	

	/** M�todo getter para o campo valorPeca
	 * 
	 * @return Long
	 *
	 */
	public Long getValorPeca() {
		return valorPeca;
	}

	/** M�todo getter para o campo valorServico
	 * 
	 * @return Long
	 *
	 */
	public Long getValorServico() {
		return valorServico;
	}	

	/** M�todo getter para o campo valorServico3
	 * 
	 * @return Long
	 *
	 */
	public Long getValorServico3() {
		return valorServico3;
	}

	/** M�todo getter para o campo valorRevisao
	 * 
	 * @return Long
	 *
	 */
	public Long getValorRevisao() {
		return valorRevisao;
	}	
	
    //	----[ M�todos Setter ]---------------------------------------------------
    
	/** M�todo setter para o campo cnpj
	 *
	 * @param cnpj Long
	 * 
	 */
	public void setCnpj(Long cnpj) {
		this.cnpj = cnpj;
	}

	/** M�todo setter para o campo concessionaria
	 *
	 * @param concessionaria String
	 * 
	 */
	public void setConcessionaria(String concessionaria) {
		this.concessionaria = concessionaria;
	}	

	/** M�todo setter para o campo valorPeca
	 *
	 * @param valorPeca Long
	 * 
	 */
	public void setValorPeca(Long valorPeca) {
		this.valorPeca = valorPeca;
	}	

	/** M�todo setter para o campo valorServico
	 *
	 * @param valorServico Long
	 * 
	 */
	public void setValorServico(Long valorServico) {
		this.valorServico = valorServico;
	}	

	/** M�todo setter para o campo valorServico3
	 *
	 * @param valorServico3 Long
	 * 
	 */
	public void setValorServico3(Long valorServico3) {
		this.valorServico3 = valorServico3;
	}	

	/** M�todo setter para o campo valorRevisao
	 *
	 * @param valorRevisao Long
	 * 
	 */
	public void setValorRevisao(Long valorRevisao) {
		this.valorRevisao = valorRevisao;
	}		
}

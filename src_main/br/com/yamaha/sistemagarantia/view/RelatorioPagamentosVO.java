/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioPagamentosVO.java
 *   .: Criação.....16 de julho, 16:45
 *   .: Autor.......Gisele Panosso
 *   .: Descrição...Entidade "RelatorioPagamentosVO".
 */

package br.com.yamaha.sistemagarantia.view;

import br.com.resource.infra.model.EntityObject;

public class RelatorioPagamentosVO extends EntityObject {

	//----[ Atributos de classe e instância ]---------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;    
    
    /** Armazena o campo cnpj do Relatório de Pagamentos. */
    private Long cnpj;  
    
    /** Armazena o campo concessionaria do Relatório de Pagamentos. */
    private String concessionaria;  
    
    /** Armazena o campo vlPeca do Relatório de Pagamentos. */
    private Long valorPeca;     
    
    /** Armazena o campo vlPeca do Relatório de Pagamentos. */
    private Long valorServico;   
    
    /** Armazena o campo vlPeca do Relatório de Pagamentos. */
    private Long valorServico3;       

    /** Armazena o campo vlPeca do Relatório de Pagamentos. */
    private Long valorRevisao;      
    
    //	----[ Métodos Getter ]---------------------------------------------------

	/** Método getter para o campo cnpj
	 * 
	 * @return Long
	 *
	 */
	public Long getCnpj() {
		return cnpj;
	}

	/** Método getter para o campo concessionaria
	 * 
	 * @return String
	 *
	 */
	public String getConcessionaria() {
		return concessionaria;
	}	

	/** Método getter para o campo valorPeca
	 * 
	 * @return Long
	 *
	 */
	public Long getValorPeca() {
		return valorPeca;
	}

	/** Método getter para o campo valorServico
	 * 
	 * @return Long
	 *
	 */
	public Long getValorServico() {
		return valorServico;
	}	

	/** Método getter para o campo valorServico3
	 * 
	 * @return Long
	 *
	 */
	public Long getValorServico3() {
		return valorServico3;
	}

	/** Método getter para o campo valorRevisao
	 * 
	 * @return Long
	 *
	 */
	public Long getValorRevisao() {
		return valorRevisao;
	}	
	
    //	----[ Métodos Setter ]---------------------------------------------------
    
	/** Método setter para o campo cnpj
	 *
	 * @param cnpj Long
	 * 
	 */
	public void setCnpj(Long cnpj) {
		this.cnpj = cnpj;
	}

	/** Método setter para o campo concessionaria
	 *
	 * @param concessionaria String
	 * 
	 */
	public void setConcessionaria(String concessionaria) {
		this.concessionaria = concessionaria;
	}	

	/** Método setter para o campo valorPeca
	 *
	 * @param valorPeca Long
	 * 
	 */
	public void setValorPeca(Long valorPeca) {
		this.valorPeca = valorPeca;
	}	

	/** Método setter para o campo valorServico
	 *
	 * @param valorServico Long
	 * 
	 */
	public void setValorServico(Long valorServico) {
		this.valorServico = valorServico;
	}	

	/** Método setter para o campo valorServico3
	 *
	 * @param valorServico3 Long
	 * 
	 */
	public void setValorServico3(Long valorServico3) {
		this.valorServico3 = valorServico3;
	}	

	/** Método setter para o campo valorRevisao
	 *
	 * @param valorRevisao Long
	 * 
	 */
	public void setValorRevisao(Long valorRevisao) {
		this.valorRevisao = valorRevisao;
	}		
}

/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioProcPecaFase2ServicoVO.java
 *   .: Cria��o.....11 de mar�o, 11:15
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...VO de Servico.
 */
package br.com.yamaha.sistemagarantia.view;

import br.com.resource.infra.utils.NumberUtils;
import br.com.resource.infra.view.ViewObject;

/** View object juntando informa��es de Garantia, relacionando 
 *  as entidades "Servico".
 *  
 *  @author Edson Luiz Sumensari
 */
public class RelatorioProcPecaFase2ServicoVO extends ViewObject {

    //----[ Atributos de classe e inst�ncia ]-----------------------------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;    
        
    private String servico;
    
    private String descricao;
    
    private Double vlTotalServico;
   
    
    //  ----[ M�todos Getter ]---------------------------------------------------
    /** M�todo getter para a propriedade descricao
	 * 
	 *  @return String
	 *
	 */
	public String getDescricao() {
		return descricao;
	}
	
	/** M�todo getter para a propriedade servico
	 * 
	 *  @return String
	 *
	 */
	public String getServico() {
		return servico;
	}



	/** M�todo getter para a propriedade vlTotalServico
	 * 
	 *  @return Double
	 *
	 */
	public Double getVlTotalServico() {
		return vlTotalServico;
	}	
	
	//	----[ M�todos Setter ]---------------------------------------------------

	/** M�todo setter para a propriedade descricao
	 *
	 * @param descricao 
	 *           <code>String</code> a ser designado para descricao.
	 * 
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	/** M�todo setter para a propriedade servico
	 *
	 * @param servico 
	 *           <code>String</code> a ser designado para servico.
	 * 
	 */
	public void setServico(String servico) {
		this.servico = servico;
	}

	/** M�todo setter para a propriedade vlTotalServico
	 *
	 * @param vlTotalServico 
	 *           <code>Double</code> a ser designado para vlTotalServico.
	 * 
	 */
	public void setVlTotalServico(Double vlTotalServico) {
		this.vlTotalServico = vlTotalServico;
	}
	
	
	//	----[ M�todos de Servi�o ]---------------------------------------------------
		

	/** Retorna o vlTotalServico no formato para moeda
     * 
     * @return String
     *  	vlTotalServicoStr
     */
    public String getVlTotalServicoStr() {
    	
    	return  NumberUtils.formatNumberCurrency( this.vlTotalServico.doubleValue() );
    	
    }
	
}
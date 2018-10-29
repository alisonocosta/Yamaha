/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioProcPecaFase2ServicoVO.java
 *   .: Criação.....11 de março, 11:15
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...VO de Servico.
 */
package br.com.yamaha.sistemagarantia.view;

import br.com.resource.infra.utils.NumberUtils;
import br.com.resource.infra.view.ViewObject;

/** View object juntando informações de Garantia, relacionando 
 *  as entidades "Servico".
 *  
 *  @author Edson Luiz Sumensari
 */
public class RelatorioProcPecaFase2ServicoVO extends ViewObject {

    //----[ Atributos de classe e instância ]-----------------------------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;    
        
    private String servico;
    
    private String descricao;
    
    private Double vlTotalServico;
   
    
    //  ----[ Métodos Getter ]---------------------------------------------------
    /** Método getter para a propriedade descricao
	 * 
	 *  @return String
	 *
	 */
	public String getDescricao() {
		return descricao;
	}
	
	/** Método getter para a propriedade servico
	 * 
	 *  @return String
	 *
	 */
	public String getServico() {
		return servico;
	}



	/** Método getter para a propriedade vlTotalServico
	 * 
	 *  @return Double
	 *
	 */
	public Double getVlTotalServico() {
		return vlTotalServico;
	}	
	
	//	----[ Métodos Setter ]---------------------------------------------------

	/** Método setter para a propriedade descricao
	 *
	 * @param descricao 
	 *           <code>String</code> a ser designado para descricao.
	 * 
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	/** Método setter para a propriedade servico
	 *
	 * @param servico 
	 *           <code>String</code> a ser designado para servico.
	 * 
	 */
	public void setServico(String servico) {
		this.servico = servico;
	}

	/** Método setter para a propriedade vlTotalServico
	 *
	 * @param vlTotalServico 
	 *           <code>Double</code> a ser designado para vlTotalServico.
	 * 
	 */
	public void setVlTotalServico(Double vlTotalServico) {
		this.vlTotalServico = vlTotalServico;
	}
	
	
	//	----[ Métodos de Serviço ]---------------------------------------------------
		

	/** Retorna o vlTotalServico no formato para moeda
     * 
     * @return String
     *  	vlTotalServicoStr
     */
    public String getVlTotalServicoStr() {
    	
    	return  NumberUtils.formatNumberCurrency( this.vlTotalServico.doubleValue() );
    	
    }
	
}
/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioProcPecaFase2PecaVO.java
 *   .: Cria��o.....06 de mar�o, 19:08
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...VO de Pe�a.
 */
package br.com.yamaha.sistemagarantia.view;

import br.com.resource.infra.utils.NumberUtils;
import br.com.resource.infra.view.ViewObject;

/** View object juntando informa��es de Garantia, relacionando 
 *  as entidades "Peca".
 *  
 *  @author Edson Luiz Sumensari
 */
public class RelatorioProcPecaFase2PecaVO extends ViewObject {

    //----[ Atributos de classe e inst�ncia ]-----------------------------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;    
    
    /** Identificador da entidade <code>Loter</code> utilizada
     *  na forma��o deste <i>View Object</i>.
     */
    private Long garantiaId;
    
    private Long quantidade;
    
    private String item;
    
    private String descricao;
    
    private Double vlUnitPeca;
    
    private Double vlTotalPeca;
    
    private Double vlRemesPeca;
   
    
    //  ----[ M�todos Getter ]---------------------------------------------------
    /** M�todo getter para a propriedade descricao
	 * 
	 *  @return String
	 *
	 */
	public String getDescricao() {
		return descricao;
	}

	/** M�todo getter para a propriedade garantiaId
	 * 
	 *  @return Long
	 *
	 */
	public Long getGarantiaId() {
		return garantiaId;
	}

	/** M�todo getter para a propriedade item
	 * 
	 *  @return String
	 *
	 */
	public String getItem() {
		return item;
	}

	/** M�todo getter para a propriedade quantidade
	 * 
	 *  @return Long
	 *
	 */
	public Long getQuantidade() {
		return quantidade;
	}

	/** M�todo getter para a propriedade vlRemesPeca
	 * 
	 *  @return Double
	 *
	 */
	public Double getVlRemesPeca() {
		return vlRemesPeca;
	}

	/** M�todo getter para a propriedade vlTotalPeca
	 * 
	 *  @return Double
	 *
	 */
	public Double getVlTotalPeca() {
		return vlTotalPeca;
	}

	/** M�todo getter para a propriedade vlUnitPeca
	 * 
	 *  @return Double
	 *
	 */
	public Double getVlUnitPeca() {
		return vlUnitPeca;
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

	/** M�todo setter para a propriedade garantiaId
	 *
	 * @param garantiaId 
	 *           <code>Long</code> a ser designado para garantiaId.
	 * 
	 */
	public void setGarantiaId(Long garantiaId) {
		this.garantiaId = garantiaId;
	}

	/** M�todo setter para a propriedade item
	 *
	 * @param item 
	 *           <code>String</code> a ser designado para item.
	 * 
	 */
	public void setItem(String item) {
		this.item = item;
	}

	/** M�todo setter para a propriedade quantidade
	 *
	 * @param quantidade 
	 *           <code>Long</code> a ser designado para quantidade.
	 * 
	 */
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	/** M�todo setter para a propriedade vlRemesPeca
	 *
	 * @param vlRemesPeca 
	 *           <code>Double</code> a ser designado para vlRemesPeca.
	 * 
	 */
	public void setVlRemesPeca(Double vlRemesPeca) {
		this.vlRemesPeca = vlRemesPeca;
	}

	/** M�todo setter para a propriedade vlTotalPeca
	 *
	 * @param vlTotalPeca 
	 *           <code>Double</code> a ser designado para vlTotalPeca.
	 * 
	 */
	public void setVlTotalPeca(Double vlTotalPeca) {
		this.vlTotalPeca = vlTotalPeca;
	}

	/** M�todo setter para a propriedade vlUnitPeca
	 *
	 * @param vlUnitPeca 
	 *           <code>Double</code> a ser designado para vlUnitPeca.
	 * 
	 */
	public void setVlUnitPeca(Double vlUnitPeca) {
		this.vlUnitPeca = vlUnitPeca;
	}
	
	//	----[ M�todos de Servi�o ]---------------------------------------------------
	
	/** Retorna o vlRemesPeca no formato para moeda
     * 
     * @return String
     *  	vlRemesPecaStr
     */
    public String getVlRemesPecaStr() {
    	
    	return  NumberUtils.formatNumberCurrency( this.vlRemesPeca.doubleValue() );
    	
    }
    
    /** Retorna vlUnitPeca formato para moeda
     * 
     * @return String
     *  	vlUnitPecaStr
     */
    public String getVlUnitPecaStr() {
    	
    	return  NumberUtils.formatNumberCurrency( this.vlUnitPeca.doubleValue() );
    	
    }
    
    /** Retorna vlTotalPeca formato para moeda
     * 
     * @return String
     *  	vlTotalPecaStr
     */
    public String getVlTotalPecaStr() {
    	
    	return  NumberUtils.formatNumberCurrency( this.vlTotalPeca.doubleValue() );
    	
    }

	

	
	
}
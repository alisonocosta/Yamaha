/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioProcPecaFase2PecaVO.java
 *   .: Criação.....06 de março, 19:08
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...VO de Peça.
 */
package br.com.yamaha.sistemagarantia.view;

import br.com.resource.infra.utils.NumberUtils;
import br.com.resource.infra.view.ViewObject;

/** View object juntando informações de Garantia, relacionando 
 *  as entidades "Peca".
 *  
 *  @author Edson Luiz Sumensari
 */
public class RelatorioProcPecaFase2PecaVO extends ViewObject {

    //----[ Atributos de classe e instância ]-----------------------------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;    
    
    /** Identificador da entidade <code>Loter</code> utilizada
     *  na formação deste <i>View Object</i>.
     */
    private Long garantiaId;
    
    private Long quantidade;
    
    private String item;
    
    private String descricao;
    
    private Double vlUnitPeca;
    
    private Double vlTotalPeca;
    
    private Double vlRemesPeca;
   
    
    //  ----[ Métodos Getter ]---------------------------------------------------
    /** Método getter para a propriedade descricao
	 * 
	 *  @return String
	 *
	 */
	public String getDescricao() {
		return descricao;
	}

	/** Método getter para a propriedade garantiaId
	 * 
	 *  @return Long
	 *
	 */
	public Long getGarantiaId() {
		return garantiaId;
	}

	/** Método getter para a propriedade item
	 * 
	 *  @return String
	 *
	 */
	public String getItem() {
		return item;
	}

	/** Método getter para a propriedade quantidade
	 * 
	 *  @return Long
	 *
	 */
	public Long getQuantidade() {
		return quantidade;
	}

	/** Método getter para a propriedade vlRemesPeca
	 * 
	 *  @return Double
	 *
	 */
	public Double getVlRemesPeca() {
		return vlRemesPeca;
	}

	/** Método getter para a propriedade vlTotalPeca
	 * 
	 *  @return Double
	 *
	 */
	public Double getVlTotalPeca() {
		return vlTotalPeca;
	}

	/** Método getter para a propriedade vlUnitPeca
	 * 
	 *  @return Double
	 *
	 */
	public Double getVlUnitPeca() {
		return vlUnitPeca;
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

	/** Método setter para a propriedade garantiaId
	 *
	 * @param garantiaId 
	 *           <code>Long</code> a ser designado para garantiaId.
	 * 
	 */
	public void setGarantiaId(Long garantiaId) {
		this.garantiaId = garantiaId;
	}

	/** Método setter para a propriedade item
	 *
	 * @param item 
	 *           <code>String</code> a ser designado para item.
	 * 
	 */
	public void setItem(String item) {
		this.item = item;
	}

	/** Método setter para a propriedade quantidade
	 *
	 * @param quantidade 
	 *           <code>Long</code> a ser designado para quantidade.
	 * 
	 */
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	/** Método setter para a propriedade vlRemesPeca
	 *
	 * @param vlRemesPeca 
	 *           <code>Double</code> a ser designado para vlRemesPeca.
	 * 
	 */
	public void setVlRemesPeca(Double vlRemesPeca) {
		this.vlRemesPeca = vlRemesPeca;
	}

	/** Método setter para a propriedade vlTotalPeca
	 *
	 * @param vlTotalPeca 
	 *           <code>Double</code> a ser designado para vlTotalPeca.
	 * 
	 */
	public void setVlTotalPeca(Double vlTotalPeca) {
		this.vlTotalPeca = vlTotalPeca;
	}

	/** Método setter para a propriedade vlUnitPeca
	 *
	 * @param vlUnitPeca 
	 *           <code>Double</code> a ser designado para vlUnitPeca.
	 * 
	 */
	public void setVlUnitPeca(Double vlUnitPeca) {
		this.vlUnitPeca = vlUnitPeca;
	}
	
	//	----[ Métodos de Serviço ]---------------------------------------------------
	
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
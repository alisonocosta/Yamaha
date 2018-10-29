/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioImportedPartsWarrantyVO.java
 *   .: Criação.....28 de setembro, 16:54
 *   .: Autor.......Gisele Panosso
 *   .: Descrição...Entidade "RelatorioImportedPartsWarrantyVO".
 */
package br.com.yamaha.sistemagarantia.view;

import br.com.resource.infra.model.EntityObject;

public class RelatorioImportedPartsWarrantyVO extends EntityObject {
	
	//----[ Atributos de classe e instância ]---------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;  
    
    /** Armazena o campo modelo*/
    private String modelo;

    /** Armazena o campo engine*/
    private String engine;
    
    /** Armazena o campo laborTime*/
    private Double laborTime;
    
    /** Armazena o campo partsFobPrice*/
    private Double partsFobPrice;
    
    /** Armazena o campo fornecedor*/
    private String fornecedor;
    
    /** Armazena percentual de comissão*/
    private Double comissao;    

    /** Armazena valor do dólar contratado (na data final)*/
    private Double vlDolar;    
 
    
    //	----[ Métodos Getter ]---------------------------------------------------
    
	/** Método getter para a propriedade engine.
	 *
	 *  @return o valor atual de engine.
	 */
	public String getEngine() {
		return engine;
	}

	/** Método getter para a propriedade fornecedor.
	 *
	 *  @return o valor atual de fornecedor.
	 */
	public String getFornecedor() {
		return fornecedor;
	}

	/** Método getter para a propriedade laborTime.
	 *
	 *  @return o valor atual de laborTime.
	 */
	public Double getLaborTime() {
		return laborTime;
	}

	/** Método getter para a propriedade modelo.
	 *
	 *  @return o valor atual de modelo.
	 */
	public String getModelo() {
		return modelo;
	}

	/** Método getter para a propriedade partsFobPrice.
	 *
	 *  @return o valor atual de partsFobPrice.
	 */
	public Double getPartsFobPrice() {
		return partsFobPrice;
	}

	/** Método getter para a propriedade comissao.
	 *
	 *  @return o valor atual de comissao.
	 */
	public Double getComissao() {
		return comissao;
	}
	
	/** Método getter para a propriedade vlDolar.
	 *
	 *  @return o valor atual de vlDolar.
	 */
	public Double getVlDolar() {
		return vlDolar;
	}	

    //	----[ Métodos setter ]---------------------------------------------------	
	
	/** Obtém o valor atual de engine.
	 * 
	 *  @param engine 
	 *    O novo valor para engine.
	 */
	public void setEngine(String engine) {
		this.engine = engine;
	}

	/** Obtém o valor atual de fornecedor.
	 * 
	 *  @param fornecedor 
	 *    O novo valor para fornecedor.
	 */
	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	/** Obtém o valor atual de laborTime.
	 * 
	 *  @param laborTime 
	 *    O novo valor para laborTime.
	 */
	public void setLaborTime(Double laborTime) {
		this.laborTime = laborTime;
	}

	/** Obtém o valor atual de modelo.
	 * 
	 *  @param modelo 
	 *    O novo valor para modelo.
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/** Obtém o valor atual de partsFobPrice.
	 * 
	 *  @param partsFobPrice 
	 *    O novo valor para partsFobPrice.
	 */
	public void setPartsFobPrice(Double partsFobPrice) {
		this.partsFobPrice = partsFobPrice;
	}

	/** Obtém o valOr atual de comissao.
	 * 
	 *  @param comissao 
	 *    O novo valor para comissao.
	 */
	public void setComissao(Double comissao) {
		this.comissao = comissao;
	}

	/** Obtém o valor atual de vlDolar.
	 * 
	 *  @param vlDolar 
	 *    O novo valor para vlDolar.
	 */
	public void setVlDolar(Double vlDolar) {
		this.vlDolar = vlDolar;
	}

}
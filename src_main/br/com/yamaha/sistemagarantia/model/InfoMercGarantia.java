/* Resource Tecnologia
 * Copyright (c) 2009 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......InfoMercGarantia.java
 *   .: Cria��o.....02 de Agosto
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "InfoMercGarantia".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "InfoMercGarantia" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class InfoMercGarantia extends EntityObject {

	/** Version ID padr�o para serializa��o. */
	private static final long serialVersionUID = 0L;

	//----[ Atributos de classe e inst�ncia ]-----------------------------------
    private GarantiaHeader garantia;
    
    private InfoMercado infoMercado;

	/** M�todo getter para a propriedade garantia
	 *
	 *  @return GarantiaHeader de garantia
	 */
	public GarantiaHeader getGarantia() {
		return garantia;
	}

	/** M�todo setter para a propriedade garantia
	 *
	 * @param garantia GarantiaHeader
	 */
	public void setGarantia(GarantiaHeader garantia) {
		this.garantia = garantia;
	}

	/** M�todo getter para a propriedade infoMercado
	 *
	 *  @return InfoMercado de infoMercado
	 */
	public InfoMercado getInfoMercado() {
		return infoMercado;
	}

	/** M�todo setter para a propriedade infoMercado
	 *
	 * @param infoMercado InfoMercado
	 */
	public void setInfoMercado(InfoMercado infoMercado) {
		this.infoMercado = infoMercado;
	}    
}
/* Resource Tecnologia
 * Copyright (c) 2009 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......InfoMercadoVO.java
 *   .: Cria��o.....10 de Agosto de 2009
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "InfoMercado".
 */
package br.com.yamaha.sistemagarantia.view;

import br.com.yamaha.sistemagarantia.model.InfoMercado;

/** Entidade do sistema, representando
 *  um objeto "InfoMercado" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class InfoMercadoVO {
	
	//----[ Atributos de classe e inst�ncia ]-----------------------------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L; 
    
    /** Armazena a mensagem de erro. */
    private String mensagem;
    
    private boolean hasError;
    
    /** Armazena a Informa��o de Mercado. */
    private InfoMercado infoMercado;
	
	/** M�todo getter para a propriedade mensagem
	 *
	 *  @return String de mensagem
	 */
	public String getMensagem() {
		return mensagem;
	}

	/** M�todo setter para a propriedade mensagem
	 *
	 * @param mensagem String
	 */
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	/** M�todo getter para a propriedade hasError
	 *
	 *  @return boolean de hasError
	 */
	public boolean isHasError() {
		return hasError;
	}

	/** M�todo setter para a propriedade hasError
	 *
	 * @param hasError boolean
	 */
	public void setHasError(boolean hasError) {
		this.hasError = hasError;
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

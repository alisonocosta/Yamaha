/* Resource Tecnologia
 * Copyright (c) 2009 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......InfoMercadoVO.java
 *   .: Criação.....10 de Agosto de 2009
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "InfoMercado".
 */
package br.com.yamaha.sistemagarantia.view;

import br.com.yamaha.sistemagarantia.model.InfoMercado;

/** Entidade do sistema, representando
 *  um objeto "InfoMercado" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class InfoMercadoVO {
	
	//----[ Atributos de classe e instância ]-----------------------------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L; 
    
    /** Armazena a mensagem de erro. */
    private String mensagem;
    
    private boolean hasError;
    
    /** Armazena a Informação de Mercado. */
    private InfoMercado infoMercado;
	
	/** Método getter para a propriedade mensagem
	 *
	 *  @return String de mensagem
	 */
	public String getMensagem() {
		return mensagem;
	}

	/** Método setter para a propriedade mensagem
	 *
	 * @param mensagem String
	 */
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	/** Método getter para a propriedade hasError
	 *
	 *  @return boolean de hasError
	 */
	public boolean isHasError() {
		return hasError;
	}

	/** Método setter para a propriedade hasError
	 *
	 * @param hasError boolean
	 */
	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}

	/** Método getter para a propriedade infoMercado
	 *
	 *  @return InfoMercado de infoMercado
	 */
	public InfoMercado getInfoMercado() {
		return infoMercado;
	}

	/** Método setter para a propriedade infoMercado
	 *
	 * @param infoMercado InfoMercado
	 */
	public void setInfoMercado(InfoMercado infoMercado) {
		this.infoMercado = infoMercado;
	}    
}

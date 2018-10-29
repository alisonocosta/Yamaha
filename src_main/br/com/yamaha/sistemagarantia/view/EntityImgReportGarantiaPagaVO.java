/* Resource Tecnologia
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......EntityImgReportGarantiaPagaVO.java
 *   .: Cria��o.....12 de Abril de 2008, 15:40
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "EntityImgReportGarantiaPagaVO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.awt.image.BufferedImage;

import br.com.resource.infra.model.EntityObject;

/** Classe para armazenar as imagens para o relat�rio gr�fico Garantia Paga
 * 
 */
public class EntityImgReportGarantiaPagaVO extends EntityObject {

	//----[ Atributos de classe e inst�ncia ]---------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;    
	
    /** Armazena o valor para o gr�fico. */
    private BufferedImage image;
    
    /** Armazena o valor do t�tulo do gr�fico. */
    private String titulo;

    //	----[ M�todos Getter ]---------------------------------------------------
    
    /** M�todo getter para a propriedade image
	 * 
	 *  @return BufferedImage
	 *
	 */
	public BufferedImage getImage() {
		return image;
	}
	
	/** M�todo getter para a propriedade titulo
	 *
	 *  @return String de titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	
	 //	----[ M�todos Setter ]---------------------------------------------------
	
	
	/** M�todo setter para a propriedade image
	 *
	 * @param image 
	 *           <code>BufferedImage</code> a ser designado para image.
	 * 
	 */
	public void setImage(BufferedImage image) {
		this.image = image;
	}

	/** M�todo setter para a propriedade titulo
	 *
	 * @param titulo 
	 *           <code>String</code> a ser designado para titulo.
	 * 
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
}

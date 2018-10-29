/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......EntityReportGraphVO.java
 *   .: Cria��o.....26 de Novembro de 2007, 16:55
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "EntityReportGraphVO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.awt.image.BufferedImage;

import br.com.resource.infra.model.EntityObject;

/** Classe para armazenar valores para um relat�rio gr�fico Gen�rico
 * 
 */
public class EntityImgReportGraficosIndividuaisVO extends EntityObject {

	//----[ Atributos de classe e inst�ncia ]---------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;    
	
    /** Armazena o valor para uma categoria. */
    private BufferedImage image;

    //	----[ M�todos Getter ]---------------------------------------------------
    
	/** M�todo getter para a propriedade image
	 * 
	 *  @return BufferedImage
	 *
	 */
	public BufferedImage getImage() {
		return image;
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
	
}

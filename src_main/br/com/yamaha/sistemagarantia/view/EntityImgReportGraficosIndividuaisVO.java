/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......EntityReportGraphVO.java
 *   .: Criação.....26 de Novembro de 2007, 16:55
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "EntityReportGraphVO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.awt.image.BufferedImage;

import br.com.resource.infra.model.EntityObject;

/** Classe para armazenar valores para um relatório gráfico Genérico
 * 
 */
public class EntityImgReportGraficosIndividuaisVO extends EntityObject {

	//----[ Atributos de classe e instância ]---------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;    
	
    /** Armazena o valor para uma categoria. */
    private BufferedImage image;

    //	----[ Métodos Getter ]---------------------------------------------------
    
	/** Método getter para a propriedade image
	 * 
	 *  @return BufferedImage
	 *
	 */
	public BufferedImage getImage() {
		return image;
	}
	
	//	----[ Métodos Setter ]---------------------------------------------------

	/** Método setter para a propriedade image
	 *
	 * @param image 
	 *           <code>BufferedImage</code> a ser designado para image.
	 * 
	 */
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
}

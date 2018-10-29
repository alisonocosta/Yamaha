/* Resource Tecnologia
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......EntityImgReportVO.java
 *   .: Criação.....17 de Outubro de 2008, 10:20
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "EntityImgReportVO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.awt.image.BufferedImage;

import br.com.resource.infra.model.EntityObject;

/** Classe para armazenar as imagens para o relatório gráfico Garantia Mensal percentual
 * 
 */
public class EntityImgReportVO extends EntityObject {

	//----[ Atributos de classe e instância ]---------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;    
	
    /** Armazena o valor para o gráfico. */
    private BufferedImage image;
    
    /** Armazena o valor do título do gráfico. */
    private String titulo;

    //	----[ Métodos Getter ]---------------------------------------------------
    
    /** Método getter para a propriedade image
	 * 
	 *  @return BufferedImage
	 *
	 */
	public BufferedImage getImage() {
		return image;
	}
	
	/** Método getter para a propriedade titulo
	 *
	 *  @return String de titulo
	 */
	public String getTitulo() {
		return titulo;
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

	/** Método setter para a propriedade titulo
	 *
	 * @param titulo 
	 *           <code>String</code> a ser designado para titulo.
	 * 
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
}

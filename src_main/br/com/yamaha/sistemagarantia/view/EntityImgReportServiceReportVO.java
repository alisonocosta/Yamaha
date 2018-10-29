/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......EntityImgReportServiceReportVO.java
 *   .: Criação.....05 de Dezembro de 2007, 11:55
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "EntityImgReportServiceReportVO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.awt.image.BufferedImage;

import br.com.resource.infra.model.EntityObject;

/** Classe para armazenar as imagens para o relatório gráfico Service Report
 * 
 */
public class EntityImgReportServiceReportVO extends EntityObject {

	//----[ Atributos de classe e instância ]---------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;    
	
    /** Armazena o valor para o primeiro gráfico. */
    private BufferedImage imgAccum;
    
    /** Armazena o valor o segundo gráfico. */
    private BufferedImage imgBYMon;
    
    /** Armazena o valor do título do gráfico. */
    private String titulo;

    //	----[ Métodos Getter ]---------------------------------------------------
    
    /** Método getter para a propriedade imgAccum
	 * 
	 *  @return BufferedImage
	 *
	 */
	public BufferedImage getImgAccum() {
		return imgAccum;
	}

	/** Método getter para a propriedade imgBYMon
	 * 
	 *  @return BufferedImage
	 *
	 */
	public BufferedImage getImgBYMon() {
		return imgBYMon;
	}
	
	/** Método getter para a propriedade titulo
	 * 
	 *  @return String
	 *
	 */
	public String getTitulo() {
		return titulo;
	}
	
	 //	----[ Métodos Setter ]---------------------------------------------------
	
	/** Método setter para a propriedade imgAccum
	 *
	 * @param imgAccum 
	 *           <code>BufferedImage</code> a ser designado para imgAccum.
	 * 
	 */
	public void setImgAccum(BufferedImage imgAccum) {
		this.imgAccum = imgAccum;
	}

	/** Método setter para a propriedade imgBYMon
	 *
	 * @param imgBYMon 
	 *           <code>BufferedImage</code> a ser designado para imgBYMon.
	 * 
	 */
	public void setImgBYMon(BufferedImage imgBYMon) {
		this.imgBYMon = imgBYMon;
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

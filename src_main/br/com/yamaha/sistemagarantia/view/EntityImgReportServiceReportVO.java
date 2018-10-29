/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......EntityImgReportServiceReportVO.java
 *   .: Cria��o.....05 de Dezembro de 2007, 11:55
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "EntityImgReportServiceReportVO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.awt.image.BufferedImage;

import br.com.resource.infra.model.EntityObject;

/** Classe para armazenar as imagens para o relat�rio gr�fico Service Report
 * 
 */
public class EntityImgReportServiceReportVO extends EntityObject {

	//----[ Atributos de classe e inst�ncia ]---------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;    
	
    /** Armazena o valor para o primeiro gr�fico. */
    private BufferedImage imgAccum;
    
    /** Armazena o valor o segundo gr�fico. */
    private BufferedImage imgBYMon;
    
    /** Armazena o valor do t�tulo do gr�fico. */
    private String titulo;

    //	----[ M�todos Getter ]---------------------------------------------------
    
    /** M�todo getter para a propriedade imgAccum
	 * 
	 *  @return BufferedImage
	 *
	 */
	public BufferedImage getImgAccum() {
		return imgAccum;
	}

	/** M�todo getter para a propriedade imgBYMon
	 * 
	 *  @return BufferedImage
	 *
	 */
	public BufferedImage getImgBYMon() {
		return imgBYMon;
	}
	
	/** M�todo getter para a propriedade titulo
	 * 
	 *  @return String
	 *
	 */
	public String getTitulo() {
		return titulo;
	}
	
	 //	----[ M�todos Setter ]---------------------------------------------------
	
	/** M�todo setter para a propriedade imgAccum
	 *
	 * @param imgAccum 
	 *           <code>BufferedImage</code> a ser designado para imgAccum.
	 * 
	 */
	public void setImgAccum(BufferedImage imgAccum) {
		this.imgAccum = imgAccum;
	}

	/** M�todo setter para a propriedade imgBYMon
	 *
	 * @param imgBYMon 
	 *           <code>BufferedImage</code> a ser designado para imgBYMon.
	 * 
	 */
	public void setImgBYMon(BufferedImage imgBYMon) {
		this.imgBYMon = imgBYMon;
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

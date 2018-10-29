/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......InfoMercFotos.java
 *   .: Criação.....04 de outubro de 2007
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "InfoMercFotos".
 */
package br.com.yamaha.sistemagarantia.model;

import java.sql.Blob;

import org.apache.struts.upload.FormFile;
import org.hibernate.Hibernate;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, representando
 *  um objeto "InfoMercFotos" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class InfoMercFotos extends EntityObject {
	
	//----[ Atributos de classe e instância ]-----------------------------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L; 
    
    /** Constante para tratamento do conteúdo da imagem na requisição */
    public static final String CONTENT_IMAGE = "CONTENT_IMAGE";
    
    /** Constante para transferência do nome da imagem na requisição */
    public static final String NAME_IMAGE = "NAME_IMAGE";
    
    //  Constantes para as chaves que relacionam com o tipo da imagem
    public static final String CONTENT_TYPE_PDF_FILE 		= "application/pdf";
    public static final String CONTENT_TYPE_IMG_GIF_FILE 	= "image/gif";
    public static final String CONTENT_TYPE_IMG_JPG_FILE 	= "image/pjpeg";
    public static final String CONTENT_TYPE_DOC_FILE		= "application/msword";
    public static final String CONTENT_TYPE_XLS_FILE		= "application/vnd.ms-excel";
    public static final String CONTENT_TYPE_TXT_FILE 		= "text/plain";
    
    /** Armazena o nome do arquivo. */
    private String filename;
    
    /** Armazena o Conteúdo do arquivo. */
    private Blob content;
    
    /** Representa uma entidade de InfoMercado. */
    private InfoMercado infoMercado;

	/** Método getter para a propriedade content
	 * 
	 *  @return Blob
	 *
	 */
	public Blob getContent() {
		
		System.out.println("Solicitado o conteúdo!" );
		return content;
	}

	/** Método getter para a propriedade filename
	 * 
	 *  @return String
	 *
	 */
	public String getFilename() {
		return filename;
	}

	/** Método setter para a propriedade content
	 *
	 * @param content 
	 *           <code>byte[]</code> a ser designado para content.
	 * 
	 */
	public void setContent(byte[] contentByte) {
		
		this.content = Hibernate.createBlob(contentByte);
	}
	
	/** Método setter para a propriedade content
	 *
	 * @param content 
	 *           <code>byte[]</code> a ser designado para content.
	 * 
	 */
	public void setContent(Blob contentBlob) {
		this.content = contentBlob;
	}
	
	/** Método setter para a propriedade filename
	 *
	 * @param filename 
	 *           <code>String</code> a ser designado para filename.
	 * 
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/** Método getter para a propriedade infoMercado
	 * 
	 *  @return InfoMercado
	 *
	 */
	public InfoMercado getInfoMercado() {
		return infoMercado;
	}

	/** Método setter para a propriedade infoMercado
	 *
	 * @param infoMercado 
	 *           <code>InfoMercado</code> a ser designado para infoMercado.
	 * 
	 */
	public void setInfoMercado(InfoMercado infoMercado) {
		this.infoMercado = infoMercado;
	}
	
	/** Verifica se a image é válida para salvar no banco
	 * 
	 * @param myFile
	 * 
	 * @return boolean  True se a imagem for válida 
 	 */
	public static boolean isValidImage(FormFile myFile){
		
		boolean isType = myFile.getContentType().equalsIgnoreCase("image/pjpeg");    	
		
		return isType;
		
	}
    
    /** Verifica se o contentType do arquivo é válido para o upload
     * 
     * @param String contentType
     * @return boolean isValid
     */
    public static boolean isValidFile(FormFile myFile){
    	
    	boolean isValid = false;
    	
    	if(myFile.getContentType().equalsIgnoreCase(CONTENT_TYPE_IMG_JPG_FILE))
    		isValid = true;
    	else if(myFile.getContentType().equalsIgnoreCase(CONTENT_TYPE_DOC_FILE))
    		isValid = true;
    	else if(myFile.getContentType().equalsIgnoreCase(CONTENT_TYPE_XLS_FILE))
    		isValid = true;
    	
        return isValid;  
    	
    }
}

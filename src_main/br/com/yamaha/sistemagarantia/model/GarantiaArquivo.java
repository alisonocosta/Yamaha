/* Yamaha
 * Copyright (c) 2012 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......GarantiaArquivo.java
 *   .: Criação.....04 de Outubro de 2012
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "GarantiaArquivo".
 */
package br.com.yamaha.sistemagarantia.model;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.struts.upload.FormFile;

import br.com.resource.infra.model.EntityObject;
import br.com.resource.infra.utils.DateUtils;
import br.com.yamaha.sistemagarantia.business.exception.FileException;
import br.com.yamaha.sistemagarantia.controller.helper.FileHelper;
import br.com.yamaha.sistemagarantia.utils.FileUtils;

/** Representa um arquivo enviado ao sistema através de um garantia.
 *
 */
public class GarantiaArquivo extends EntityObject {
    

	private static final long serialVersionUID = -3494093298125601942L;
	
	//  Constantes para as chaves que relacionam com o tipo da imagem
    public static final String CONTENT_TYPE_PDF_FILE 		= "application/pdf";
    public static final String CONTENT_TYPE_IMG_GIF_FILE 	= "image/gif";
    public static final String CONTENT_TYPE_IMG_JPG_FILE 	= "image/pjpeg";
    public static final String CONTENT_TYPE_DOC_FILE		= "application/msword";
    public static final String CONTENT_TYPE_XLS_FILE		= "application/vnd.ms-excel";
    public static final String CONTENT_TYPE_TXT_FILE 		= "text/plain";
    public static final String CONTENT_TYPE_XLSX_FILE 		= "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static final String CONTENT_TYPE_DOCX_FILE 		= "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
    public static final String CONTENT_TYPE_OTHER 			= "";
        
    private String 	 filename;
    private String 	 contentType;
    private String   filePath;
    private Integer  size;
    private FormFile formFile;
    private Long linhaId;
    
    private GarantiaHeader garantia;
        
    /** Construtor da Classe */
    public GarantiaArquivo() {
        
        super();
        
    }
    
    public static GarantiaArquivo getInstance(FormFile formFile, Long concessionariaId, GarantiaHeader garantia) throws FileException, FileNotFoundException, IOException {
    	
    	GarantiaArquivo garantiaArquivo = null; 
    	
    	if(FileUtils.isValidFile(formFile)){
    		garantiaArquivo = new GarantiaArquivo();  	    	
    		garantiaArquivo.setFilename(formFile.getFileName());
    		
    		garantiaArquivo.setContentType(formFile.getContentType());
    		garantiaArquivo.setSize(new Integer(formFile.getFileSize()));
    		garantiaArquivo.setGarantia(garantia);
    		garantiaArquivo.setFormFile(formFile);
    		garantiaArquivo.setLinhaId((Long)garantia.getLote().getLinha().getEntityId());
    	}
    	
    	return garantiaArquivo;
    }
	

	/** Método getter para a propriedade linhaId
	 *
	 * @return linhaId do tipo Long
	 *
	 */
	
	public Long getLinhaId() {
		return linhaId;
	}

	/** Método setter para a propriedade linhaId
	 *
	 * @param linhaId 
	 *       <code>linhaId<code> a ser designado para GarantiaArquivo.java
	 *
	 */
	public void setLinhaId(Long linhaId) {
		this.linhaId = linhaId;
	}

	/** Retorna o caminho completo do arquivo dentro no servidor.
     * 
     *  @return   Retorna o caminho completo do arquivo.
     */
    public String getRealFilename() {
        
        return FileHelper.createAbsolutePatch(this.filename);
        
    }
    
    /** Retona o caminho completo do arquivo dentro da web
     * 
     * @return  Caminho web do arquivo.
     */
    public String getWebFilename() {
        
        return FileHelper.createAbsoluteWebPatch(this.filename);
        
    }     
    
    /** Método getter para a propriedade "contentType".
     *  @return O valor da propriedade "contentType".
     */
    public String getContentType() {
        return contentType;
    }
    
    /** Método setter para a propriedade "contentType".
     *  @param description Novo valor da propriedade "contentType".
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
    
    /** Método getter para a propriedade "filename".
     *  @return O valor da propriedade "filename".
     */
    public String getFilename() {
        return filename;
    }
    
    /** Método setter para a propriedade "filename".
     *  @param description Novo valor da propriedade "filename".
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }
    
    /** Método getter para a propriedade "size".
     *  @return O valor da propriedade "size".
     */
    public Integer getSize() {
        return size;
    }
    
    /** Método getter para a propriedade "size" formatado.
     *  @return O valor da propriedade "size" em KBytes.
     */
    public String getSizeStr() {
    	
    	if ( this.size != null )
    		return String.valueOf((size.intValue()/1024)) + " KB";
    	else
    		return "";
    }
    
    /** Método getter para "data de criação".
     *  @return
     *      <code>String</code>. O valor atual da data de criação,
     *      com uma máscara aplicada.
     */
    public String getMaskedCreatedDate() {
    	
    	return DateUtils.applyMask(this.getDataCriacao());
    	
    	
    }
            
    /** Método setter para a propriedade "size".
     *  @param description Novo valor da propriedade "size".
     */
    public void setSize(Integer size) {
        this.size = size;
    }
        
	
	/**
	 * Método getter para a propriedade formFile
	 * @return  FormFile de formFile
	 */
	public FormFile getFormFile() {
		return formFile;
	}

	/**
	 * Método setter para a propriedade formFile
	 * @param formFile FormFile
	 */
	public void setFormFile(FormFile formFile) {
		this.formFile = formFile;
	}	

	/** Método getter para a propriedade filePath
	 *
	 * @return filePath do tipo String
	 *
	 */
	
	public String getFilePath() {
		return filePath;
	}

	/** Método setter para a propriedade filePath
	 *
	 * @param filePath 
	 *       <code>filePath<code> a ser designado para GarantiaArquivo.java
	 *
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/** Método getter para a propriedade garantia
	 *
	 * @return garantia do tipo GarantiaHeader
	 *
	 */
	
	public GarantiaHeader getGarantia() {
		return garantia;
	}

	/** Método setter para a propriedade garantia
	 *
	 * @param garantia 
	 *       <code>garantia<code> a ser designado para GarantiaArquivo.java
	 *
	 */
	public void setGarantia(GarantiaHeader garantia) {
		this.garantia = garantia;
	}

	/** Método getter para a retornar a chave para o arquivo.
     *  @return O valor da chave.
     */
    public String getFileType() {
    	
    	String key = "";
    	
    	if ( this.contentType != null ) {
    		
    		if(this.contentType.equalsIgnoreCase(CONTENT_TYPE_IMG_JPG_FILE))
    			key = CONTENT_TYPE_IMG_JPG_FILE;
        	else if(this.contentType.equalsIgnoreCase(CONTENT_TYPE_DOC_FILE))
        		key = CONTENT_TYPE_DOC_FILE;
        	else if(this.contentType.equalsIgnoreCase(CONTENT_TYPE_XLS_FILE))
        		key = CONTENT_TYPE_XLS_FILE;
        	else if(this.contentType.equalsIgnoreCase(CONTENT_TYPE_PDF_FILE))
        		key = CONTENT_TYPE_PDF_FILE;
        	else if(this.contentType.equalsIgnoreCase(CONTENT_TYPE_IMG_GIF_FILE))
        		key = CONTENT_TYPE_IMG_GIF_FILE;
        	else if(this.contentType.equalsIgnoreCase(CONTENT_TYPE_TXT_FILE))
        		key = CONTENT_TYPE_TXT_FILE;
        	else if(this.contentType.equalsIgnoreCase(CONTENT_TYPE_XLSX_FILE))
        		key = CONTENT_TYPE_XLSX_FILE;
        	else if(this.contentType.equalsIgnoreCase(CONTENT_TYPE_DOCX_FILE))
        		key = CONTENT_TYPE_DOCX_FILE;
	    	else 
	    		key = CONTENT_TYPE_OTHER;
	    	
    	} else 
    		key = CONTENT_TYPE_OTHER;
    	
        return key;
    }        
    
}

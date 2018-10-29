/* Resource Tecnologia
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......Documento.java
 *   .: Criação.....28 de Agosto de 2008
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "Documento".
 */
package br.com.yamaha.sistemagarantia.model;

import org.apache.struts.upload.FormFile;

import br.com.resource.infra.model.EntityObject;
import br.com.resource.infra.utils.DateUtils;
import br.com.yamaha.sistemagarantia.controller.helper.FileHelper;

/** Representa um arquivo "bruto" enviado ao sistema.
 *
 */
public class Documento extends EntityObject {

   
	private static final long serialVersionUID = -6334412927528617185L;
    
    // Constantes para as chaves que relacionam com o tipo da imagem
    public static final String CONTENT_TYPE_PDF 	= "documento.fileupload.img.pdf";
    public static final String CONTENT_TYPE_DOC 	= "documento.fileupload.img.doc";
    public static final String CONTENT_TYPE_XLS 	= "documento.fileupload.img.xls";
    public static final String CONTENT_TYPE_TXT 	= "documento.fileupload.img.txt";
    public static final String CONTENT_TYPE_OTHER 	= "documento.fileupload.img.other";
    
    // Constantes para as chaves que realcionam com a mensagem do tipo da imagem
    public static final String CONTENT_MSG_PDF 	 = "documento.fileupload.msg.pdf";
    public static final String CONTENT_MSG_DOC 	 = "documento.fileupload.msg.doc";
    public static final String CONTENT_MSG_XLS   = "documento.fileupload.msg.xls";
    public static final String CONTENT_MSG_TXT 	 = "documento.fileupload.msg.txt";
    public static final String CONTENT_MSG_OTHER = "documento.fileupload.msg.other";
    
    //  Constantes para as chaves que relacionam com o tipo da imagem
    public static final String CONTENT_TYPE_PDF_FILE = "application/pdf";
    public static final String CONTENT_TYPE_DOC_FILE = "application/msword";
    public static final String CONTENT_TYPE_XLS_FILE = "application/vnd.ms-excel";
    public static final String CONTENT_TYPE_TXT_FILE = "text/plain";
    
    private String 	 filename;
    private String   description;
    private String 	 contentType;
    private String   url;
    private Integer  size;
    private FormFile formFile;
    
    /** Construtor da Classe */
    public Documento() {
        
        super();
        
        this.filename    = new String();
        this.description = new String();
        this.contentType = new String();
        this.url         = new String();
        this.size        = new Integer(0);
        
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
    
    /** Método getter para "data inicial de validade do registro".
     *  @return
     *      <code>String</code>. O valor atual da data inicial da vailidade do registro,
     *      com uma máscara aplicada.
     */
    public String getMaskedStartDate() {
    	
    	return DateUtils.applyMask(this.getDataInicio());
    	
    	
    }
    
    /** Método getter para "data final de validade do registro".
     *  @return
     *      <code>String</code>. O valor atual da data final da vailidade do registro,
     *      com uma máscara aplicada.
     */
    public String getMaskedEndDate() {
    	
    	return DateUtils.applyMask(this.getDataTermino());
    	
    	
    }
    
    /** Método setter para a propriedade "size".
     *  @param description Novo valor da propriedade "size".
     */
    public void setSize(Integer size) {
        this.size = size;
    }
    
    /**
	 * Método getter para a propriedade description
	 * @return  String de description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Método setter para a propriedade description
	 * @param description String
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Método getter para a propriedade url
	 * @return  String de url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Método setter para a propriedade url
	 * @param url String
	 */
	public void setUrl(String url) {
		this.url = url;
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

	/** Método getter para a retornar a chave para o arquivo.
     *  @return O valor da chave.
     */
    public String getFileType() {
    	
    	String key = "";
    	
    	if ( this.contentType != null ) {
    		
	    	if(this.contentType.equalsIgnoreCase(CONTENT_TYPE_PDF_FILE))
	    		key = CONTENT_TYPE_PDF;
	    	else if(this.contentType.equalsIgnoreCase(CONTENT_TYPE_DOC_FILE))
	    		key = CONTENT_TYPE_DOC;
	    	else if(this.contentType.equalsIgnoreCase(CONTENT_TYPE_XLS_FILE))
	    		key = CONTENT_TYPE_XLS;
	    	else if(this.contentType.equalsIgnoreCase(CONTENT_TYPE_TXT_FILE))
	    		key = CONTENT_TYPE_TXT;
	    	else 
	    		key = CONTENT_TYPE_OTHER;
	    	
    	} else 
    		key = CONTENT_TYPE_OTHER;
    	
        return key;
    }
    
    /** Método getter para a retornar a chave para a mensagem do arquivo.
     *  @return O valor da chave.
     */
    public String getFileTypeMsg() {
    	
    	String key = "";
    	
    	if ( this.contentType != null ) {
    		
	    	if(this.contentType.equalsIgnoreCase(CONTENT_TYPE_PDF_FILE))
	    		key = CONTENT_MSG_PDF;
	    	else if(this.contentType.equalsIgnoreCase(CONTENT_TYPE_DOC_FILE))
	    		key = CONTENT_MSG_DOC;
	    	else if(this.contentType.equalsIgnoreCase(CONTENT_TYPE_XLS_FILE))
	    		key = CONTENT_MSG_XLS;
	    	else if(this.contentType.equalsIgnoreCase(CONTENT_TYPE_TXT_FILE))
	    		key = CONTENT_MSG_TXT;
	    	else 
	    		key = CONTENT_MSG_OTHER;
	    	
    	} else 
    		key = CONTENT_MSG_OTHER;
    	
        return key;
    }
    
    /** Verifica se o contentType e o tamanho do arquivo são válidos  para o upload
     * 
     * @param FormFile formFile
     * @param int fileMaxSize
     * @return boolean isValid
     */
    public static boolean isValidFile(FormFile formFile, int fileMaxSize){
    	
    	boolean isValid = false;
    	
    	if(formFile.getContentType().equalsIgnoreCase(CONTENT_TYPE_PDF_FILE) && formFile.getFileSize() <= fileMaxSize )
    		isValid = true;
    	else if(formFile.getContentType().equalsIgnoreCase(CONTENT_TYPE_DOC_FILE)&& formFile.getFileSize() <= fileMaxSize )
    		isValid = true;
    	else if(formFile.getContentType().equalsIgnoreCase(CONTENT_TYPE_XLS_FILE) && formFile.getFileSize() <= fileMaxSize )
    		isValid = true;
    	else if(formFile.getContentType().equalsIgnoreCase(CONTENT_TYPE_TXT_FILE) && formFile.getFileSize() <= fileMaxSize )
    		isValid = true;
    	
        return isValid;  
    	
    }
        
    
}

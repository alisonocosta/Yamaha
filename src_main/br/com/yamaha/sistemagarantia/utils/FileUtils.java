/**
 * 
 */
package br.com.yamaha.sistemagarantia.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.struts.upload.FormFile;

import br.com.yamaha.sistemagarantia.business.exception.FileException;

/**
 * @author Edson Luiz Sumensari
 *
 */
public class FileUtils {
	
	public static String SIGLA_FOLDER_CONCESSIONARIA = "YM_CONC_";
	
	public static String SIGLA_FILENAME = "YM_GAR_";
	
	//  Constantes para as chaves que relacionam com o tipo da imagem
	public static final String CONTENT_TYPE_XML_FILE 		= "application/xml";
	public static final String CONTENT_TYPE_XML_TXT_FILE 	= "text/xml";
    public static final String CONTENT_TYPE_PDF_FILE 		= "application/pdf";
    public static final String CONTENT_TYPE_IMG_GIF_FILE 	= "image/gif";
    public static final String CONTENT_TYPE_IMG_JPG_FILE 	= "image/pjpeg";
    public static final String CONTENT_TYPE_DOC_FILE		= "application/msword";
    public static final String CONTENT_TYPE_XLS_FILE		= "application/vnd.ms-excel";
    public static final String CONTENT_TYPE_TXT_FILE 		= "text/plain";
    public static final String CONTENT_TYPE_XLSX_FILE 		= "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static final String CONTENT_TYPE_DOCX_FILE 		= "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
    
    /**Cria um arquivo a partir de um patch  do sistema e um filename enviado 
     *  
     * @param concessionariaId
     * @param garantiaId
     * @param pathSistem
     * @param fileName
     * @return
     * @throws FileException
     */
    public static File createFullPatchedByFileName(Long concessionariaId, Integer garantiaId, String pathSistem, String fileName) throws FileException {
    	
    	java.io.File file = null;
    	
    	try {
	    	String folder = getPathDirByConcessionaria(concessionariaId, pathSistem);
	        //char separator = java.io.File.separatorChar;
	        
	        //file = new java.io.File((folder.replace('/', separator)) + separator + fileName);
	    	file = new java.io.File(folder + "/" + fileName);
    	}catch(Exception exp) {
    		throw new FileException(exp);
    	}
        
       return file;
        
    }   
    
    /** Cria um arquivo a partir de um patch criado pelo sistema
     * 
     *  @param fileName
     *  @return
     */
    public static File createFullPatchedFile(Long concessionariaId, Integer garantiaId, String pathSistem) throws FileException {
    	
    	java.io.File file = null;
    	
    	try {
	    	String folder = getPathDirByConcessionaria(concessionariaId, pathSistem);
	        //char separator = java.io.File.separatorChar;
	        
	        //file = new java.io.File((folder.replace('/', separator)) + separator + getFileNameByGarantia(garantiaId));
	    	file = new java.io.File(folder + "/" + getFileNameByGarantia(garantiaId));
    	}catch(Exception exp) {
    		throw new FileException(exp);
    	}
        
       return file;
        
    }   
    
    public static String getPathDirByConcessionaria(Long concessionariaId, String pathSistem)throws FileException {
    	
    	String pathFolder = null;
    	
    	try {
    		
    		//char separator = java.io.File.separatorChar;
    		StringBuffer nFolder = new StringBuffer();
    		nFolder.append(pathSistem);
    		nFolder.append("/");
    		nFolder.append(SIGLA_FOLDER_CONCESSIONARIA);
    		nFolder.append(concessionariaId.intValue());
	    	//File newFolder = new File(nFolder.replace('/', separator));
    		File newFolder = new File(nFolder.toString());
		   
	    	if(!newFolder.exists())
		    	newFolder.mkdir();
    	
		    pathFolder = newFolder.getPath();
		    
    	}catch(Exception exc) {
    		
    		throw new FileException(exc);
    	}
    	
    	return pathFolder;
    }
    
    public static String getFileNameByGarantia(Integer garantiaId){
    	
    	StringBuffer fileName = new StringBuffer();
    	
    	fileName.append(SIGLA_FILENAME); 
    	fileName.append(garantiaId);
    	fileName.append("_");
    	fileName.append(System.currentTimeMillis());
    	
    	return fileName.toString();
    }
    
    public static File[] listPathFileNameByGarantia(Long concessionariaId, Integer garantiaId, String pathSistem) throws FileException {
    	
    	//char separator = java.io.File.separatorChar;
    	StringBuffer sFolder = new StringBuffer();
    	sFolder.append(pathSistem);
    	sFolder.append("/");
    	sFolder.append(SIGLA_FOLDER_CONCESSIONARIA);
    	sFolder.append(concessionariaId.intValue());
    	//File folder = new File(sFolder.replace('/', separator));
    	File folder = new File(sFolder.toString());
    	
    	if(!folder.exists())
    		throw new FileException("Pasta Não localizada!" + folder.getPath());	
    	
    	File[] listValidFiles = new File[folder.listFiles().length];
    	File[] listFiles =  folder.listFiles();
    	File arq = null;	 
    	int  iArq = 0;	 
    	for( int i = 0 ; i < listFiles.length ; i++){
    		
    		arq = listFiles[i];
    		
    		if(arq.getName().indexOf(SIGLA_FILENAME + garantiaId) != -1) {
    			
    			listValidFiles[iArq++] = arq;
    			
    		}    		
    		
    	}
    	 
    	return listValidFiles;
    	 
    }
    
    public static File getFileByName(String pathSistem)  throws FileException {
    	
    	//File file = new File(pathSistem.replace('/', separator));
    	File file = new File(pathSistem);
    	if(!file.isFile())
    		throw new FileException("Pasta Não localizada!" + file.getPath());
    	
    	return file;
    	
    }
    
    public static Boolean createPhysicalFile(FormFile formFile, Long concessionariaId, Integer garantiaId, String pathSistem) throws FileException {
    	
    	Boolean isCreated = Boolean.FALSE;  
    	
    	//Verificamos se o content type do arquivo é valido
    	if(!isValidFile(formFile))
    		throw new FileException("Arquivo inválido!");
    	
    	//Criamos o Folder caso ele não exista e o arquivo lógico
    	File file = createFullPatchedFile(concessionariaId, garantiaId, pathSistem);
    	  	
    	byte dataFile[];
		try {
			dataFile = formFile.getFileData();    	
	    	OutputStream out = new FileOutputStream(file);
	        out.write(dataFile);
	        out.flush();
	        out.close();
	        isCreated = Boolean.TRUE;
		} catch (FileNotFoundException fnfExc) {
			fnfExc.printStackTrace();
			throw new FileException(fnfExc);
		} catch (IOException ioExc) {
			ioExc.printStackTrace();
			throw new FileException(ioExc);
		}
		
		return isCreated;

    }
    
    /** Verifica se o contentType do arquivo é válido para o upload
     * 
     * @param String contentType
     * @return boolean isValid
     */
    public static boolean isValidFile(FormFile myFile){
    	
    	Boolean isValid = Boolean.FALSE;
    	
    	if(myFile.getContentType().equalsIgnoreCase(CONTENT_TYPE_IMG_JPG_FILE))
    		isValid = Boolean.TRUE;
    	else if(myFile.getContentType().equalsIgnoreCase(CONTENT_TYPE_DOC_FILE))
    		isValid = Boolean.TRUE;
    	else if(myFile.getContentType().equalsIgnoreCase(CONTENT_TYPE_XLS_FILE))
    		isValid = Boolean.TRUE;
    	else if(myFile.getContentType().equalsIgnoreCase(CONTENT_TYPE_PDF_FILE))
    		isValid = Boolean.TRUE;
    	else if(myFile.getContentType().equalsIgnoreCase(CONTENT_TYPE_IMG_GIF_FILE))
    		isValid = Boolean.TRUE;
    	else if(myFile.getContentType().equalsIgnoreCase(CONTENT_TYPE_TXT_FILE))
    		isValid = Boolean.TRUE;
    	else if(myFile.getContentType().equalsIgnoreCase(CONTENT_TYPE_XLSX_FILE))
    		isValid = Boolean.TRUE;
    	else if(myFile.getContentType().equalsIgnoreCase(CONTENT_TYPE_DOCX_FILE))
    		isValid = Boolean.TRUE;
    	else if(myFile.getContentType().equalsIgnoreCase(CONTENT_TYPE_XML_FILE))
    		isValid = Boolean.TRUE;
    	else if(myFile.getContentType().equalsIgnoreCase(CONTENT_TYPE_XML_TXT_FILE))
    		isValid = Boolean.TRUE;
    	
        return isValid.booleanValue();  
    	
    }

}

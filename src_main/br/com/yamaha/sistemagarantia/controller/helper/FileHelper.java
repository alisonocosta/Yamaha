/* Resource Tecnologia
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......FileHelper.java
 *   .: Criação.....28 de Agosto de 2008
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "FileHelper".
 */
package br.com.yamaha.sistemagarantia.controller.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


/** Auxilia em tarefas básicas referentes
 *  aos arquivos.
 */
public final class FileHelper {

    //private static final String UPLOAD_FOLDER = "d:#files#"; // Local
	//private static final String WEB_SERVER    = "d://files//"; // Local
    //private static final String UPLOAD_FOLDER   = "#teste_sg#proddata#work#files#"; // Desenvolvimento
    //private static final String WEB_SERVER      = "\\teste_sg\\proddata\\work\\files\\"; // Dessenvolvimento 
	private static final String UPLOAD_FOLDER = "#prod_sg#proddata#work#files#"; // Produção
    private static final String WEB_SERVER    = "\\prod_sg\\proddata\\work\\files\\"; // Produção
    private static final String WEB_LOCATION  = "#files#";   // Desenvolvimento, Produção e Local
    
    /** Cria um arquivo a partir de um patch criado pelo sistema
     * 
     *  @param fileName
     *  @return File
     */
    public static File createFullPatchedFile(String fileName) {
        
        char separator = java.io.File.separatorChar;
        
        return new java.io.File((UPLOAD_FOLDER.replace('#', separator)) + fileName);
        
    }
    
    
    /** Retorna o caminho completo de um arquivo no sistema. 
     * 
     *  @param fileName
     *  @return String
     */
    public static String createAbsolutePatch(String fileName) {
        
        return (createFullPatchedFile(fileName)).getAbsolutePath();
        
    }
    
    /** Retorna o caminho completo do arquivo, na internet.
     * 
     *  @param fileName
     * 
     *  @return   Retorna o caminho do arquivo, em forma de URL, ou
     *            a String "undefined" caso não seja possível converter.
     */    
    public static String createWebPatch(String path, String fileName) {
        
        char separator = java.io.File.separatorChar;
        
        return ("http://" + path + WEB_LOCATION.replace('#', separator) + fileName);
        
    }

    /** Retorna o caminho completo do arquivo, na internet.
     * 
     *  @param fileName
     * 
     *  @return   Retorna o caminho do arquivo, em forma de URL, ou
     *            a String "undefined" caso não seja possível converter.
     */    
    public static String createAbsoluteWebPatch(String fileName) {
        
        return (WEB_SERVER + fileName);
        
    }

    
    /** Retorna o caminho do arquivo utilizando o separador
     *  adequado ao sistema.
     * 
     *  @return  Caminho de imagens no sistema.
     */
    public static String getUploadFolder() {
        
        char separator = java.io.File.separatorChar;
        return UPLOAD_FOLDER.replace('#', separator);
        
    }
 
    /** Retorna o caminho web (relativo) do arquivo utilizando o separador
     *  adequado ao sistema.
     * 
     *  @return  Caminho de imagens (web, relativo) no sistema.
     */
    public static String getWebLocation() {
        
        char separator = java.io.File.separatorChar;
        return WEB_LOCATION.replace('#', separator);
        
    }    
    
    // Returns the contents of the file in a byte array.
    public static byte[] getBytesFromFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);
    
        // Get the size of the file
        long length = file.length();
    
        // You cannot create an array using a long type.
        // It needs to be an int type.
        // Before converting to an int type, check
        // to ensure that file is not larger than Integer.MAX_VALUE.
        if (length > Integer.MAX_VALUE) {
        	throw new IOException("Tamanho inválido "+file.getName());
        }
    
        // Create the byte array to hold the data
        byte[] bytes = new byte[(int)length];
    
        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
               && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }
    
        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+file.getName());
        }
    
        // Close the input stream and return bytes
        is.close();
        return bytes;
    }
       
}

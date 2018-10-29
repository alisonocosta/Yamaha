/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......DownloaderServlet.java
 *   .: Criação.....16 de outubro de 2007
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Servlet "DownloaderServlet" para controlar o download de fotos para Inform. de Mercado.
 */
package br.com.yamaha.sistemagarantia.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.yamaha.sistemagarantia.controller.helper.YmSessionHelper;
import br.com.yamaha.sistemagarantia.model.InfoMercFotos;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * Servlet "DownloaderServlet" para controlar o download de fotos para Inform. de Mercado.
 *
 */
public class DownloaderServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   
	private static final long serialVersionUID = -6497882854162235616L;

	//----[ Métodos públicos ]------------------------------------------------------    
    
    /** Inicializa o Servlet.
     *  <p>
     *  Este método é chamado pelo servidor de aplicações quando está
     *  carregando os servlets definidos em <i>web.xml</i>.
     *  
     *  @param config
     *      Configuração do servlet.
     */
	public DownloaderServlet() throws ServletException {
		super();
		
	}   
	
	/** Método principal de serviço, chamado por requisições
     *  <code>POST</code> e <code>GET</code>.
     *  
     *  @param request
     *      Requisição recebida pelo <i>Servlet</i>.
     *  @param response
     *      Resposta sendo gerada até então.
     *      
     *  @throws ServletException
     *  @throws IOException
     */
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	
		ServletOutputStream ouputStream = response.getOutputStream(); 
		
		try {	
			
			byte[] bytes = retrieveDownloader(request);
			
			//System.out.println("BYTES:" + (bytes != null));
			
			//this.encodeJPG(ouputStream, bytes);
			
			response.setContentType("image/pjpeg");
			
			//response.setHeader("Content-Disposition", "attachment; filename=" + retrieveNameImage(request) );
		
			response.setContentLength(bytes.length); 
			
			//System.out.println("Tamanho do arquivo lido:" + bytes.length);
		
			ouputStream.write( bytes );
			
		} catch (FileNotFoundException fex){
		
			System.out.println("Não foi possível localizar o arquivo!");
		
		} finally {
			
			ouputStream.flush();
	
			ouputStream.close();
		}
		 
	}
	 
	
	//----[ Métodos protegidos ]----------------------------------------------------    
    
    /** Obtém o conteúdo da imagem recebido na requisição.
     * 
     *  @param request
     *      Requisição recebida pelo <i>Servlet</i>.
     */
    protected byte[] retrieveDownloader(HttpServletRequest request) {
    	   
    	Blob content = YmSessionHelper.getImageFromSession(request);
        
    	//Blob content = (Blob)request.getAttribute( InfoMercFotos.CONTENT_IMAGE );
    	
    	byte[] contentByte = null;
    	
    	try {
			contentByte = getBytesFromFile(content) ;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	//contentByte = InfoMercFotos.toByteArray(infoMercFotos.getContent()) ;
		
		return contentByte;
        
    }
    
    /** Obtém o nome da imagem recebido na requisição.
     * 
     *  @param request
     *      Requisição recebida pelo <i>Servlet</i>.
     */
    protected String retrieveNameImage(HttpServletRequest request) {
    	   	
        String fileName = (String)request.getAttribute( InfoMercFotos.NAME_IMAGE );
        
        System.out.println("Nome do arquivio:" + fileName);
    	
        return fileName;
        
    }
    
	/** Recebe requisições HTTP do tipo <code>GET</code>.
     *  
     *  @param request
     *      Requisição recebida pelo <i>Servlet</i>.
     *  @param response
     *      Resposta sendo gerada até então.
     *      
     *  @throws ServletException
     *  @throws IOException
     */    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}  	
	
	/** Recebe requisições HTTP do tipo <code>POST</code>.
     *  
     *  @param request
     *      Requisição recebida pelo <i>Servlet</i>.
     *  @param response
     *      Resposta sendo gerada até então.
     *      
     *  @throws ServletException
     *  @throws IOException
     */  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}  
	
	/**
     * Método para converter a serqüência de bytes em JPEG
     * 
     * @param ServletOutputStream sos
     * 
     * @param  byte[] ecoder
     * 
     * @throws IOException
     */
    public void encodeJPG(ServletOutputStream sos, byte[] image )throws IOException{   
    	
        InputStream fs = new ByteArrayInputStream(image);
        
        JPEGImageDecoder 	decoder = JPEGCodec.createJPEGDecoder(fs);
        
        BufferedImage bImage = decoder.decodeAsBufferedImage();
        
        //System.out.println("Width: " + bImage.getWidth() + " Height: " + bImage.getHeight());
        
        JpgImage jpgImage = new JpgImage(bImage);
        
        bImage = jpgImage.sendToBufferedImage();
        
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(sos);
		
		JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bImage);
		
		//qualidade maxima aqui (1.0f)
		param.setQuality(1.0f, false);
		
		encoder.setJPEGEncodeParam(param); 
		
		encoder.encode(bImage);
		
		encoder = null;
		bImage  = null;
		fs.close();
        fs = null;
        
    }
   
   
    /** Returns the contents of the file in a byte array.
     *  @param Blob
     *  
     *  @return byte[]
     *  
     */
    public static byte[] getBytesFromFile(Blob content) throws IOException, SQLException {
    	
    	
        InputStream is;
        byte[] bytes = null;        
		
		is = content.getBinaryStream();
    
        // Get the size of the file
        long length = content.length();
    
        // You cannot create an array using a long type.
        // It needs to be an int type.
        // Before converting to an int type, check
        // to ensure that file is not larger than Integer.MAX_VALUE.
        if (length > Integer.MAX_VALUE) {
        	throw new IOException("Tamanho do arquivo inválido! ");
        }
    
        // Create the byte array to hold the data
        bytes = new byte[(int)length];
    
        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
               && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }
    
        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Não possível completar a carga do arquivo!");
        }
        
        // Close the input stream and return bytes
        is.close();        
        
        return bytes;
    }
	
}
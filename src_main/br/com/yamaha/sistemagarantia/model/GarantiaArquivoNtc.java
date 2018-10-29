/* Yamaha
 * Copyright (c) 2013 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......GarantiaArquivoNtc.java
 *   .: Cria��o.....05 de Setembro de 2013
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "GarantiaArquivoNtc".
 */
package br.com.yamaha.sistemagarantia.model;

import java.sql.Blob;

import org.hibernate.Hibernate;

import br.com.resource.infra.model.EntityObject;
import br.com.resource.infra.utils.DateUtils;

/** Representa um arquivo enviado ao sistema atrav�s de um garantia.
 *
 */
public class GarantiaArquivoNtc extends EntityObject {
    	
	private static final long serialVersionUID = 5085928609051164661L;
	
    /** Armazena o Conte�do do arquivo. */
    private Blob content;
    
    private GarantiaArquivo garantiaArquivo;
        
    /** Construtor da Classe */
    public GarantiaArquivoNtc() {
        
        super();
        
    }
    
    /** M�todo getter para a propriedade content
	 * 
	 *  @return Blob
	 *
	 */
	public Blob getContent() {
		
		System.out.println("Solicitado o conte�do!" );
		return content;
	}
	
	/** M�todo setter para a propriedade content
	 *
	 * @param content 
	 *           <code>byte[]</code> a ser designado para content.
	 * 
	 */
	public void setContent(byte[] contentByte) {
		
		this.content = Hibernate.createBlob(contentByte);
	}
	
	/** M�todo setter para a propriedade content
	 *
	 * @param content 
	 *           <code>byte[]</code> a ser designado para content.
	 * 
	 */
	public void setContent(Blob contentBlob) {
		this.content = contentBlob;
	}
    
    /** M�todo getter para "data de cria��o".
     *  @return
     *      <code>String</code>. O valor atual da data de cria��o,
     *      com uma m�scara aplicada.
     */
    public String getMaskedCreatedDate() {
    	
    	return DateUtils.applyMask(this.getDataCriacao());
    	
    	
    }

	/** M�todo getter para a propriedade garantiaArquivo
	 *
	 * @return garantiaArquivo do tipo GarantiaArquivo
	 *
	 */
	
	public GarantiaArquivo getGarantiaArquivo() {
		return garantiaArquivo;
	}

	/** M�todo setter para a propriedade garantiaArquivo
	 *
	 * @param garantiaArquivo 
	 *       <code>garantiaArquivo<code> a ser designado para GarantiaArquivoNtc.java
	 *
	 */
	public void setGarantiaArquivo(GarantiaArquivo garantiaArquivo) {
		this.garantiaArquivo = garantiaArquivo;
	}     
    
}

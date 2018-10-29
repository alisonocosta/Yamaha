/* Yamaha
 * Copyright (c) 2013 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......GarantiaArquivoNtc.java
 *   .: Criação.....05 de Setembro de 2013
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "GarantiaArquivoNtc".
 */
package br.com.yamaha.sistemagarantia.model;

import java.sql.Blob;

import org.hibernate.Hibernate;

import br.com.resource.infra.model.EntityObject;
import br.com.resource.infra.utils.DateUtils;

/** Representa um arquivo enviado ao sistema através de um garantia.
 *
 */
public class GarantiaArquivoNtc extends EntityObject {
    	
	private static final long serialVersionUID = 5085928609051164661L;
	
    /** Armazena o Conteúdo do arquivo. */
    private Blob content;
    
    private GarantiaArquivo garantiaArquivo;
        
    /** Construtor da Classe */
    public GarantiaArquivoNtc() {
        
        super();
        
    }
    
    /** Método getter para a propriedade content
	 * 
	 *  @return Blob
	 *
	 */
	public Blob getContent() {
		
		System.out.println("Solicitado o conteúdo!" );
		return content;
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
    
    /** Método getter para "data de criação".
     *  @return
     *      <code>String</code>. O valor atual da data de criação,
     *      com uma máscara aplicada.
     */
    public String getMaskedCreatedDate() {
    	
    	return DateUtils.applyMask(this.getDataCriacao());
    	
    	
    }

	/** Método getter para a propriedade garantiaArquivo
	 *
	 * @return garantiaArquivo do tipo GarantiaArquivo
	 *
	 */
	
	public GarantiaArquivo getGarantiaArquivo() {
		return garantiaArquivo;
	}

	/** Método setter para a propriedade garantiaArquivo
	 *
	 * @param garantiaArquivo 
	 *       <code>garantiaArquivo<code> a ser designado para GarantiaArquivoNtc.java
	 *
	 */
	public void setGarantiaArquivo(GarantiaArquivo garantiaArquivo) {
		this.garantiaArquivo = garantiaArquivo;
	}     
    
}

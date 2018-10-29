/* Yamaha
 * Copyright (c) 2012 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......CondicaoProblema.java
 *   .: Criação.....06 de Outubro, 10:00
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "CondicaoProblemaGarantia".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema.
 * 
 *  @author Edson Luiz Sumensari
 */
public class CondicaoProblemaGarantia extends EntityObject {

    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L; 
       
    /**
     * Descrição da Condição do problema
     */
    private String condicaoProblema;   
    
    /**
     * Entidade de Garantia Associada
     */
    private GarantiaHeader garantia;
       
    /** Cria um entidade com base na descrição enviada
     *     
     * @param String condicaoProblemaGarantia
     * 
     * @return CondicaoProblemaGarantia
     * 
     */
    public static CondicaoProblemaGarantia getInstance(String condicaoProblemaGarantia) {
    	
    	if(condicaoProblemaGarantia != null && !"".equals(condicaoProblemaGarantia)){
    		
    		CondicaoProblemaGarantia entity = new CondicaoProblemaGarantia();
    		entity.setCondicaoProblema(condicaoProblemaGarantia);
    		return entity;
    	} else
    		return null;
    	
    }
    
	/** Método getter para a propriedade condicaoProblema
	 *
	 *  @return String de condicaoProblema
	 */
	public String getCondicaoProblema() {
		return condicaoProblema;
	}
	/** Método setter para a propriedade condicaoProblema
	 *
	 * @param condicaoProblema String
	 */
	public void setCondicaoProblema(String condicaoProblema) {
		this.condicaoProblema = condicaoProblema;
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
	 *       <code>garantia<code> a ser designado para CondicaoProblemaGarantia.java
	 *
	 */
	public void setGarantia(GarantiaHeader garantia) {
		this.garantia = garantia;
	}
	
}
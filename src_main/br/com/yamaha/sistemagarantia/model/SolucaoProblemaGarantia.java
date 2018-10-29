/* Yamaha
 * Copyright (c) 2012 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......SolucaoProblemaGarantia.java
 *   .: Cria��o.....06 de Outubro, 10:10
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "SolucaoProblemaGarantia".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema.
 * 
 *  @author Edson Luiz Sumensari
 */
public class SolucaoProblemaGarantia extends EntityObject {

    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L; 
      
    /**
     * Descri��o do diagn�stico do problema
     */
    private String solucaoProblema;
    
    /**
     * Entidade de garantia associada
     */
    private GarantiaHeader garantia;
    
    /** Cria um entidade com base na descri��o enviada
     *     
     * @param String solucaoProblemaGarantia
     * 
     * @return SolucaoProblemaGarantia
     * 
     */
    public static SolucaoProblemaGarantia getInstance(String solucaoProblemaGarantia) {
    	
    	if(solucaoProblemaGarantia != null && !"".equals(solucaoProblemaGarantia)){
    		
    		SolucaoProblemaGarantia entity = new SolucaoProblemaGarantia();
    		entity.setSolucaoProblema(solucaoProblemaGarantia);
    		return entity;
    	} else
    		return null;
    	
    } 
    
	/** M�todo getter para a propriedade solucaoProblema
	 *
	 *  @return String de solucaoProblema
	 */
	public String getSolucaoProblema() {
		return solucaoProblema;
	}
	/** M�todo setter para a propriedade solucaoProblema
	 *
	 * @param solucaoProblema String
	 */
	public void setSolucaoProblema(String solucaoProblema) {
		this.solucaoProblema = solucaoProblema;
	}

	/** M�todo getter para a propriedade garantia
	 *
	 * @return garantia do tipo GarantiaHeader
	 *
	 */
	
	public GarantiaHeader getGarantia() {
		return garantia;
	}

	/** M�todo setter para a propriedade garantia
	 *
	 * @param garantia 
	 *       <code>garantia<code> a ser designado para SolucaoProblemaGarantia.java
	 *
	 */
	public void setGarantia(GarantiaHeader garantia) {
		this.garantia = garantia;
	}
	
}
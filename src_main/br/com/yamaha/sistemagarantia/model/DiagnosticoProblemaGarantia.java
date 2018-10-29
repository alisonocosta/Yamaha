/* Yamaha
 * Copyright (c) 2012 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......DiagnosticoProblemaGarantia.java
 *   .: Criação.....06 de Outubro, 10:05
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "DiagnosticoProblemaGarantia".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema.
 * 
 *  @author Edson Luiz Sumensari
 */
public class DiagnosticoProblemaGarantia extends EntityObject {

    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L; 
        
    /**
     * Descrição do diagnóstico do problema
     */
    private String diagnosticoProblema;
    
    /**
     * Entidade de Garantia associada
     */
    private GarantiaHeader garantia;
    
    /** Cria um entidade com base na descrição enviada
     *     
     * @param String diagnosticoProblemaGarantia
     * 
     * @return DiagnosticoProblemaGarantia
     * 
     */
    public static DiagnosticoProblemaGarantia getInstance(String diagnosticoProblemaGarantia) {
    	
    	if(diagnosticoProblemaGarantia != null && !"".equals(diagnosticoProblemaGarantia)){
    		
    		DiagnosticoProblemaGarantia entity = new DiagnosticoProblemaGarantia();
    		entity.setDiagnosticoProblema(diagnosticoProblemaGarantia);
    		return entity;
    	} else
    		return null;
    	
    } 
	/** Método getter para a propriedade diagnosticoProblema
	 *
	 *  @return String de diagnosticoProblema
	 */
	public String getDiagnosticoProblema() {
		return diagnosticoProblema;
	}
	/** Método setter para a propriedade diagnosticoProblema
	 *
	 * @param diagnosticoProblema String
	 */
	public void setDiagnosticoProblema(String diagnosticoProblema) {
		this.diagnosticoProblema = diagnosticoProblema;
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
	 *       <code>garantia<code> a ser designado para DiagnosticoProblemaGarantia.java
	 *
	 */
	public void setGarantia(GarantiaHeader garantia) {
		this.garantia = garantia;
	}		
}
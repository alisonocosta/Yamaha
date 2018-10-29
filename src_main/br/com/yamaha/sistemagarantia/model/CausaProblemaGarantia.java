/* Yamaha
 * Copyright (c) 2012 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......CausaProblemaGarantia.java
 *   .: Criação.....06 de Outubro, 10:05
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "CausaProblemaGarantia".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema.
 * 
 *  @author Edson Luiz Sumensari
 */
public class CausaProblemaGarantia extends EntityObject {

    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L; 
    
    /**
     * Descrição da causa do problema
     */
    private String causaProblema;
    
    /**
     * Entidad de Garantia Associada
     */
    private GarantiaHeader garantia;
    
    /** Cria um entidade com base na descrição enviada
     *     
     * @param String causaProblemaGarantia
     * 
     * @return CausaProblemaGarantia
     * 
     */
    public static CausaProblemaGarantia getInstance(String causaProblemaGarantia) {
    	
    	if(causaProblemaGarantia != null && !"".equals(causaProblemaGarantia)){
    		
    		CausaProblemaGarantia entity = new CausaProblemaGarantia();
    		entity.setCausaProblema(causaProblemaGarantia);
    		return entity;
    	} else
    		return null;
    	
    }
    	
	/** Método getter para a propriedade causaProblema
	 *
	 *  @return String de causaProblema
	 */
	public String getCausaProblema() {
		return causaProblema;
	}
	/** Método setter para a propriedade causaProblema
	 *
	 * @param causaProblema String
	 */
	public void setCausaProblema(String causaProblema) {
		this.causaProblema = causaProblema;
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
	 *       <code>garantia<code> a ser designado para CausaProblemaGarantia.java
	 *
	 */
	public void setGarantia(GarantiaHeader garantia) {
		this.garantia = garantia;
	}	
	
}
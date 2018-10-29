/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......TabelaPreco.java
 *   .: Criação.....10 de maio, 10:54
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Entidade "TabelaPreco".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;
import br.com.yamaha.sistemagarantia.model.id.TabelaPrecoId;

/** Entidade "TabelaPreco".
 * 
 *  @author Thiago Uriel M. Garcia
 */
public class TabelaPreco extends EntityObject {

	private static final long serialVersionUID = 1L;
	
	private TabelaPrecoId compositeEntityId;
	
	private Long organizationId;
	private Long itemId;
	private double precoUnitario;
	private double precoGarantia;
	private double precoPublico;
	private double precoFOB;
	private double aliquotaIPI;
	private double fatorGarantia;
	
	public TabelaPreco() {
		
		this.setEntityId(null);
		this.setCompositeEntityId(null);
		
	}
	
    public boolean isNew() {

        if ( this.compositeEntityId == null )
            return true;
        else 
            return false;

    }	
	

	/** Método getter para a propriedade aliquotaIPI
	 * 
	 *  @return double
	 *
	 */
	public double getAliquotaIPI() {
		return aliquotaIPI;
	}

	/** Método getter para a propriedade fatorGarantia
	 * 
	 *  @return double
	 *
	 */
	public double getFatorGarantia() {
		return fatorGarantia;
	}

	/** Método getter para a propriedade itemId
	 * 
	 *  @return Long
	 *
	 */
	public Long getItemId() {
		return itemId;
	}

	/** Método getter para a propriedade organizationId
	 * 
	 *  @return Long
	 *
	 */
	public Long getOrganizationId() {
		return organizationId;
	}

	/** Método getter para a propriedade precoFOB
	 * 
	 *  @return double
	 *
	 */
	public double getPrecoFOB() {
		return precoFOB;
	}

	/** Método getter para a propriedade precoGarantia
	 * 
	 *  @return double
	 *
	 */
	public double getPrecoGarantia() {
		return precoGarantia;
	}

	/** Método getter para a propriedade precoPublico
	 * 
	 *  @return double
	 *
	 */
	public double getPrecoPublico() {
		return precoPublico;
	}

	/** Método getter para a propriedade precoUnitario
	 * 
	 *  @return double
	 *
	 */
	public double getPrecoUnitario() {
		return precoUnitario;
	}

	/** Método setter para a propriedade aliquotaIPI
	 *
	 * @param aliquotaIPI 
	 *           <code>double</code> a ser designado para aliquotaIPI.
	 * 
	 */
	public void setAliquotaIPI(double aliquotaIPI) {
		this.aliquotaIPI = aliquotaIPI;
	}

	/** Método setter para a propriedade fatorGarantia
	 *
	 * @param fatorGarantia 
	 *           <code>double</code> a ser designado para fatorGarantia.
	 * 
	 */
	public void setFatorGarantia(double fatorGarantia) {
		this.fatorGarantia = fatorGarantia;
	}

	/** Método setter para a propriedade itemId
	 *
	 * @param itemId 
	 *           <code>Long</code> a ser designado para itemId.
	 * 
	 */
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	/** Método setter para a propriedade organizationId
	 *
	 * @param organizationId 
	 *           <code>Long</code> a ser designado para organizationId.
	 * 
	 */
	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	/** Método setter para a propriedade precoFOB
	 *
	 * @param precoFOB 
	 *           <code>double</code> a ser designado para precoFOB.
	 * 
	 */
	public void setPrecoFOB(double precoFOB) {
		this.precoFOB = precoFOB;
	}

	/** Método setter para a propriedade precoGarantia
	 *
	 * @param precoGarantia 
	 *           <code>double</code> a ser designado para precoGarantia.
	 * 
	 */
	public void setPrecoGarantia(double precoGarantia) {
		this.precoGarantia = precoGarantia;
	}

	/** Método setter para a propriedade precoPublico
	 *
	 * @param precoPublico 
	 *           <code>double</code> a ser designado para precoPublico.
	 * 
	 */
	public void setPrecoPublico(double precoPublico) {
		this.precoPublico = precoPublico;
	}

	/** Método setter para a propriedade precoUnitario
	 *
	 * @param precoUnitario 
	 *           <code>double</code> a ser designado para precoUnitario.
	 * 
	 */
	public void setPrecoUnitario(double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	/**
	 * @return the compositeEntityId
	 */
	public TabelaPrecoId getCompositeEntityId() {
		return compositeEntityId;
	}

	/**
	 * @param compositeEntityId the compositeEntityId to set
	 */
	public void setCompositeEntityId(TabelaPrecoId compositeEntityId) {
		this.compositeEntityId = compositeEntityId;
	}

}

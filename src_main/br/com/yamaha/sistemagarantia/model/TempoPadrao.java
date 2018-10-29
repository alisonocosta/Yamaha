/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......TempoPadrao.java
 *   .: Criação.....18 de junho, 09:25
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "TempoPadrao".
 */
package br.com.yamaha.sistemagarantia.model;

import java.util.Date;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "TempoPadrao" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class TempoPadrao extends EntityObject {
	
	//----[ Atributos de classe e instância ]-----------------------------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L; 
    
    /** Estado da federação aplicado o valor */
    private String modelo;
    
    /** Tempo padrão */
    private double tempo;
    
    /** Data de início */
    private Date startDate;
    
    /** Data de Término */
    private Date endDate;
    
    /** Serviço relacionado */
    private Servico servico;
    
    
    //  ----[ Métodos Getter ]---------------------------------------------------

	/** Método getter para a propriedade endDate
	 * 
	 *  @return Date
	 *
	 */
	public Date getEndDate() {
		return endDate;
	}

	/** Método getter para a propriedade startDate
	 * 
	 *  @return Date
	 *
	 */
	public Date getStartDate() {
		return startDate;
	}


	/** Método getter para a propriedade modelo
	 * 
	 *  @return String
	 *
	 */
	public String getModelo() {
		return modelo;
	}

	/** Método getter para a propriedade tempo
	 * 
	 *  @return double
	 *
	 */
	public double getTempo() {
		return tempo;
	}
	
	/** Método getter para a propriedade servico
	 * 
	 *  @return Servico
	 *
	 */
	public Servico getServico() {
		return servico;
	}

	//	----[ Métodos Setter ]---------------------------------------------------
	
	
	/** Método setter para a propriedade endDate
	 *
	 * @param endDate 
	 *           <code>Date</code> a ser designado para endDate.
	 * 
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/** Método setter para a propriedade startDate
	 *
	 * @param startDate 
	 *           <code>Date</code> a ser designado para startDate.
	 * 
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	/** Método setter para a propriedade modelo
	 *
	 * @param modelo 
	 *           <code>String</code> a ser designado para modelo.
	 * 
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/** Método setter para a propriedade tempo
	 *
	 * @param tempo 
	 *           <code>double</code> a ser designado para tempo.
	 * 
	 */
	public void setTempo(double tempo) {
		this.tempo = tempo;
	}

	/** Método setter para a propriedade servico
	 *
	 * @param servico 
	 *           <code>Servico</code> a ser designado para servico.
	 * 
	 */
	public void setServico(Servico servico) {
		this.servico = servico;
	}
	
	
}

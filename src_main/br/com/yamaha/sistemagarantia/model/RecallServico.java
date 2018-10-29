/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RecallServico.java
 *   .: Cria��o.....09 de Agosto, 09:44
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "RecallServico".
 */
package br.com.yamaha.sistemagarantia.model;

import java.io.Serializable;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "RecallServico" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class RecallServico extends EntityObject {
	
	//----[ Atributos de classe e inst�ncia ]-----------------------------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;  
    
    /** Valor do Servico de Recall */
    private double valorServicoRecall;
        
    private Recall recall;
    
    private Servico servico;
    
    //	----[ M�todos Getter ]---------------------------------------------------

	/** M�todo getter para a propriedade recall
	 * 
	 *  @return Recall
	 *
	 */
	public Recall getRecall() {
		return recall;
	}

	/** M�todo getter para a propriedade servico
	 * 
	 *  @return Servico
	 *
	 */
	public Servico getServico() {
		return servico;
	}

	/** M�todo getter para a propriedade valorServicoRecall
	 * 
	 *  @return double
	 *
	 */
	public double getValorServicoRecall() {
		return valorServicoRecall;
	}
	
	//	----[ M�todos Setter ]---------------------------------------------------

	/** M�todo setter para a propriedade recall
	 *
	 * @param recall 
	 *           <code>Recall</code> a ser designado para recall.
	 * 
	 */
	public void setRecall(Recall recall) {
		this.recall = recall;
	}

	/** M�todo setter para a propriedade servico
	 *
	 * @param servico 
	 *           <code>Servico</code> a ser designado para servico.
	 * 
	 */
	public void setServico(Servico servico) {
		this.servico = servico;
	}

	/** M�todo setter para a propriedade valorServicoRecall
	 *
	 * @param valorServicoRecall 
	 *           <code>double</code> a ser designado para valorServicoRecall.
	 * 
	 */
	public void setValorServicoRecall(Serializable valorServicoRecall) {
		
		if ( valorServicoRecall != null ) { 	
		
			this.valorServicoRecall = ((Double)valorServicoRecall).doubleValue();
		} else {
			
			this.valorServicoRecall = 0D;
			
		}
	}

}

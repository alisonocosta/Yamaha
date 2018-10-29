/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......Recall.java
 *   .: Cria��o.....22 de junho, 16:44
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "Recall".
 */
package br.com.yamaha.sistemagarantia.model;

import java.util.List;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "Recall" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class Recall extends EntityObject {
	
	//----[ Atributos de classe e inst�ncia ]-----------------------------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;  
    
    /** Valor inicial do intervalo de chassi */
    private String chassiInicial;
    
    /** Valor final do intervalo de chassi */
    private String chassiFinal;
    
    private String numeroIT;
    
    private String assunto;
    
    private double valorServico;
    
    private Long faseRecall;
    
   // private String cobraPeca;
    
    private Condicao condicao;
    
    private Sintoma sintoma;
    
    private List servicos;
    
    private List recallServicos;
    
   // private Item item;
    
   // private Item newItem;
    
    //	----[ M�todos Getter ]---------------------------------------------------

	/** M�todo getter para a propriedade assunto
	 * 
	 *  @return String
	 *
	 */
	public String getAssunto() {
		return assunto;
	}

	/** M�todo getter para a propriedade chassiFinal
	 * 
	 *  @return String
	 *
	 */
	public String getChassiFinal() {
		return chassiFinal;
	}

	/** M�todo getter para a propriedade chassiInicial
	 * 
	 *  @return String
	 *
	 */
	public String getChassiInicial() {
		return chassiInicial;
	}

	/** M�todo getter para a propriedade cobraPeca
	 * 
	 *  @return String
	 *
	 
	public String getCobraPeca() {
		return cobraPeca;
	}*/

	/** M�todo getter para a propriedade condicao
	 * 
	 *  @return Condicao
	 *
	 */
	public Condicao getCondicao() {
		return condicao;
	}

	/** M�todo getter para a propriedade faseRecall
	 * 
	 *  @return Long
	 *
	 */
	public Long getFaseRecall() {
		return faseRecall;
	}

	/** M�todo getter para a propriedade item
	 * 
	 *  @return Item
	 *
	 
	public Item getItem() {
		return item;
	}*/

	/** M�todo getter para a propriedade newItem
	 * 
	 *  @return Item
	 *
	
	public Item getNewItem() {
		return newItem;
	} */

	/** M�todo getter para a propriedade numeroIT
	 * 
	 *  @return String
	 *
	 */
	public String getNumeroIT() {
		return numeroIT;
	}

	/** M�todo getter para a propriedade sintoma
	 * 
	 *  @return Sintoma
	 *
	 */
	public Sintoma getSintoma() {
		return sintoma;
	}

	/** M�todo getter para a propriedade valorServico
	 * 
	 *  @return double
	 *
	 */
	public double getValorServico() {
		return valorServico;
	}
	
	/** M�todo getter para a propriedade servicos
	 * 
	 *  @return List
	 *
	 */
	public List getServicos() {
		return servicos;
	}

	/** M�todo getter para a propriedade recallServicos
	 * 
	 *  @return List
	 *
	 */
	public List getRecallServicos() {
		return recallServicos;
	}
	
	//	----[ M�todos Setter ]---------------------------------------------------

	/** M�todo setter para a propriedade assunto
	 *
	 * @param assunto 
	 *           <code>String</code> a ser designado para assunto.
	 * 
	 */
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	/** M�todo setter para a propriedade chassiFinal
	 *
	 * @param chassiFinal 
	 *           <code>String</code> a ser designado para chassiFinal.
	 * 
	 */
	public void setChassiFinal(String chassiFinal) {
		this.chassiFinal = chassiFinal;
	}

	/** M�todo setter para a propriedade chassiInicial
	 *
	 * @param chassiInicial 
	 *           <code>String</code> a ser designado para chassiInicial.
	 * 
	 */
	public void setChassiInicial(String chassiInicial) {
		this.chassiInicial = chassiInicial;
	}

	/** M�todo setter para a propriedade cobraPeca
	 *
	 * @param cobraPeca 
	 *           <code>String</code> a ser designado para cobraPeca.
	 * 
	 
	public void setCobraPeca(String cobraPeca) {
		this.cobraPeca = cobraPeca;
	}*/

	/** M�todo setter para a propriedade condicao
	 *
	 * @param condicao 
	 *           <code>Condicao</code> a ser designado para condicao.
	 * 
	 */
	public void setCondicao(Condicao condicao) {
		this.condicao = condicao;
	}

	/** M�todo setter para a propriedade faseRecall
	 *
	 * @param faseRecall 
	 *           <code>Long</code> a ser designado para faseRecall.
	 * 
	 */
	public void setFaseRecall(Long faseRecall) {
		this.faseRecall = faseRecall;
	}

	/** M�todo setter para a propriedade item
	 *
	 * @param item 
	 *           <code>Item</code> a ser designado para item.
	 * 
	 
	public void setItem(Item item) {
		this.item = item;
	}*/

	/** M�todo setter para a propriedade newItem
	 *
	 * @param newItem 
	 *           <code>Item</code> a ser designado para newItem.
	 * 
	
	public void setNewItem(Item newItem) {
		this.newItem = newItem;
	} */

	/** M�todo setter para a propriedade numeroIT
	 *
	 * @param numeroIT 
	 *           <code>String</code> a ser designado para numeroIT.
	 * 
	 */
	public void setNumeroIT(String numeroIT) {
		this.numeroIT = numeroIT;
	}

	/** M�todo setter para a propriedade sintoma
	 *
	 * @param sintoma 
	 *           <code>Sintoma</code> a ser designado para sintoma.
	 * 
	 */
	public void setSintoma(Sintoma sintoma) {
		this.sintoma = sintoma;
	}

	/** M�todo setter para a propriedade valorServico
	 *
	 * @param valorServico 
	 *           <code>double</code> a ser designado para valorServico.
	 * 
	 */
	public void setValorServico(double valorServico) {
		this.valorServico = valorServico;
	}

	/** M�todo setter para a propriedade servicos
	 *
	 * @param servicos 
	 *           <code>List</code> a ser designado para servicos.
	 * 
	 */
	public void setServicos(List servicos) {
		this.servicos = servicos;
	}

	/** M�todo setter para a propriedade recallServicos
	 *
	 * @param recallServicos 
	 *           <code>List</code> a ser designado para recallServicos.
	 * 
	 */
	public void setRecallServicos(List recallServicos) {
		this.recallServicos = recallServicos;
	}
}

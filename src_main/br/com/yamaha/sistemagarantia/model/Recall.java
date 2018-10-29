/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......Recall.java
 *   .: Criação.....22 de junho, 16:44
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "Recall".
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
	
	//----[ Atributos de classe e instância ]-----------------------------------
    
    /** Version ID padrão para serialização. */
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
    
    //	----[ Métodos Getter ]---------------------------------------------------

	/** Método getter para a propriedade assunto
	 * 
	 *  @return String
	 *
	 */
	public String getAssunto() {
		return assunto;
	}

	/** Método getter para a propriedade chassiFinal
	 * 
	 *  @return String
	 *
	 */
	public String getChassiFinal() {
		return chassiFinal;
	}

	/** Método getter para a propriedade chassiInicial
	 * 
	 *  @return String
	 *
	 */
	public String getChassiInicial() {
		return chassiInicial;
	}

	/** Método getter para a propriedade cobraPeca
	 * 
	 *  @return String
	 *
	 
	public String getCobraPeca() {
		return cobraPeca;
	}*/

	/** Método getter para a propriedade condicao
	 * 
	 *  @return Condicao
	 *
	 */
	public Condicao getCondicao() {
		return condicao;
	}

	/** Método getter para a propriedade faseRecall
	 * 
	 *  @return Long
	 *
	 */
	public Long getFaseRecall() {
		return faseRecall;
	}

	/** Método getter para a propriedade item
	 * 
	 *  @return Item
	 *
	 
	public Item getItem() {
		return item;
	}*/

	/** Método getter para a propriedade newItem
	 * 
	 *  @return Item
	 *
	
	public Item getNewItem() {
		return newItem;
	} */

	/** Método getter para a propriedade numeroIT
	 * 
	 *  @return String
	 *
	 */
	public String getNumeroIT() {
		return numeroIT;
	}

	/** Método getter para a propriedade sintoma
	 * 
	 *  @return Sintoma
	 *
	 */
	public Sintoma getSintoma() {
		return sintoma;
	}

	/** Método getter para a propriedade valorServico
	 * 
	 *  @return double
	 *
	 */
	public double getValorServico() {
		return valorServico;
	}
	
	/** Método getter para a propriedade servicos
	 * 
	 *  @return List
	 *
	 */
	public List getServicos() {
		return servicos;
	}

	/** Método getter para a propriedade recallServicos
	 * 
	 *  @return List
	 *
	 */
	public List getRecallServicos() {
		return recallServicos;
	}
	
	//	----[ Métodos Setter ]---------------------------------------------------

	/** Método setter para a propriedade assunto
	 *
	 * @param assunto 
	 *           <code>String</code> a ser designado para assunto.
	 * 
	 */
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	/** Método setter para a propriedade chassiFinal
	 *
	 * @param chassiFinal 
	 *           <code>String</code> a ser designado para chassiFinal.
	 * 
	 */
	public void setChassiFinal(String chassiFinal) {
		this.chassiFinal = chassiFinal;
	}

	/** Método setter para a propriedade chassiInicial
	 *
	 * @param chassiInicial 
	 *           <code>String</code> a ser designado para chassiInicial.
	 * 
	 */
	public void setChassiInicial(String chassiInicial) {
		this.chassiInicial = chassiInicial;
	}

	/** Método setter para a propriedade cobraPeca
	 *
	 * @param cobraPeca 
	 *           <code>String</code> a ser designado para cobraPeca.
	 * 
	 
	public void setCobraPeca(String cobraPeca) {
		this.cobraPeca = cobraPeca;
	}*/

	/** Método setter para a propriedade condicao
	 *
	 * @param condicao 
	 *           <code>Condicao</code> a ser designado para condicao.
	 * 
	 */
	public void setCondicao(Condicao condicao) {
		this.condicao = condicao;
	}

	/** Método setter para a propriedade faseRecall
	 *
	 * @param faseRecall 
	 *           <code>Long</code> a ser designado para faseRecall.
	 * 
	 */
	public void setFaseRecall(Long faseRecall) {
		this.faseRecall = faseRecall;
	}

	/** Método setter para a propriedade item
	 *
	 * @param item 
	 *           <code>Item</code> a ser designado para item.
	 * 
	 
	public void setItem(Item item) {
		this.item = item;
	}*/

	/** Método setter para a propriedade newItem
	 *
	 * @param newItem 
	 *           <code>Item</code> a ser designado para newItem.
	 * 
	
	public void setNewItem(Item newItem) {
		this.newItem = newItem;
	} */

	/** Método setter para a propriedade numeroIT
	 *
	 * @param numeroIT 
	 *           <code>String</code> a ser designado para numeroIT.
	 * 
	 */
	public void setNumeroIT(String numeroIT) {
		this.numeroIT = numeroIT;
	}

	/** Método setter para a propriedade sintoma
	 *
	 * @param sintoma 
	 *           <code>Sintoma</code> a ser designado para sintoma.
	 * 
	 */
	public void setSintoma(Sintoma sintoma) {
		this.sintoma = sintoma;
	}

	/** Método setter para a propriedade valorServico
	 *
	 * @param valorServico 
	 *           <code>double</code> a ser designado para valorServico.
	 * 
	 */
	public void setValorServico(double valorServico) {
		this.valorServico = valorServico;
	}

	/** Método setter para a propriedade servicos
	 *
	 * @param servicos 
	 *           <code>List</code> a ser designado para servicos.
	 * 
	 */
	public void setServicos(List servicos) {
		this.servicos = servicos;
	}

	/** Método setter para a propriedade recallServicos
	 *
	 * @param recallServicos 
	 *           <code>List</code> a ser designado para recallServicos.
	 * 
	 */
	public void setRecallServicos(List recallServicos) {
		this.recallServicos = recallServicos;
	}
}

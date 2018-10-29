/* Resource Tecnologia
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......NotaFiscalLancamentoTO.java
 *   .: Cria��o.....18 de Setembro 2008, 09:20
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Transfer Object dos itens de a sumariza��o dos itens para lan�amento da Nota Fiscal .
 */

package br.com.yamaha.sistemagarantia.model.to;

import java.util.ArrayList;
import java.util.List;

import br.com.yamaha.sistemagarantia.view.CupomNFVO;
import br.com.yamaha.sistemagarantia.view.NotaFiscalVO;

/** Transfer Object dos itens de a sumariza��o dos itens para lan�amento da Nota Fiscal .
 * 
 *  @author Edson Luiz Sumensari
 */
public class NotaFiscalLancamentoTO {
	
	/** Lista de itens de Cupons ou Sg's para relacionar com as Notas Fiscais */
	private List listItens;
	
	/** Lista de itens de Sumariza��o por empresa para o lan�amento de Nota Fiscal */
	private List listSumario;
	
	/**
	 * Construtor padr�o
	 */
	public NotaFiscalLancamentoTO() {
		
		this.listItens   = new ArrayList();
		this.listSumario = new ArrayList();		
		
	}

	/**
	 * M�todo getter para a propriedade listItens
	 * @return  List de listItens
	 */
	public List getListItens() {
		return listItens;
	}

	/**
	 * M�todo setter para a propriedade listItens
	 * @param listItens List
	 */
	public void setListItens(List listItens) {
		this.listItens = listItens;
	}

	/**
	 * M�todo getter para a propriedade listSumario
	 * @return  List de listSumario
	 */
	public List getListSumario() {
		return listSumario;
	}

	/**
	 * M�todo setter para a propriedade listSumario
	 * @param listSumario List
	 */
	public void setListSumario(List listSumario) {
		this.listSumario = listSumario;
	}
	
	/** 
	 * Adiciona uma entidade de CupomNFTO na lista de itens
	 * @param cupomNFTO
	 */
	public void addListItens( CupomNFVO cupomNFTO ) {
		
		if ( cupomNFTO != null ) {		
			this.listItens.add(cupomNFTO);			
		}
	}
	
	/** 
	 * Adiciona uma Entidade de NotaFiscalVO na lista de Sumariza��o
	 * @param notaFiscalVO
	 */
	public void addListSumario( NotaFiscalVO notaFiscalVO ) {
		
		if ( notaFiscalVO != null ) {			
			this.listSumario.add(notaFiscalVO);			
		}				
	}
	
}

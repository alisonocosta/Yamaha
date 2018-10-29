/**
 * 
 */
package br.com.yamaha.sistemagarantia.view;

import br.com.resource.infra.model.EntityObject;

/**
 * @author user
 *
 */
public class TesteGraf extends EntityObject {
	
	private static final long serialVersionUID = 1L;
	
    private String mes;
    private String produto;
    private int quantidade;

    public TesteGraf() {
    }

    public TesteGraf(String mes, String produto, int quantidade) {
        this.mes = mes;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}

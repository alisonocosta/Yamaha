/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioConsultaNfLoteVO.java
 *   .: Criação.....31 de agosto de 2007
 *   .: Autor.......Gisele Panosso
 *   .: Descrição...Entidade "RelatorioConsultaNfLoteVO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.resource.infra.model.EntityObject;

public class RelatorioConsultaNfLoteVO extends EntityObject {

	//----[ Atributos de classe e instância ]---------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;    
    
    /** Armazena o campo lote do Relatório Consulta de Notas Fiscais. */
    private Serializable lote;

    /** Armazena o campo tipoLote do Relatório Consulta de Notas Fiscais. */
    private String tipoLote;
    
    /** Armazena o campo statusLote do Relatório Consulta de Notas Fiscais. */
    private String statusLote;
    
    /** Armazena o campo dtAbertura do Relatório Consulta de Notas Fiscais. */
    private Date dtAbertura;
        
    /** Armazena o campo dtLiberacao do Relatório Consulta de Notas Fiscais. */
    private Date dtLiberacao;
    
    /** Armazena o campo dtFechamento do Relatório Consulta de Notas Fiscais. */
    private Date dtFechamento;

    /** Armazena o campo listNotas do Relatório Consulta de Notas Fiscais. */
    private List listNotas;
    
    //	----[ Métodos Construtor ]---------------------------------------------------  

	public RelatorioConsultaNfLoteVO() {
		super();
		listNotas = new ArrayList();
	}

    //	----[ Métodos Add ]---------------------------------------------------  
	
	/** Adiciona uma lista de notas à listagem de Lotes.
     * 
     *  @param listNotas
     *      listNotas a ser adicionado.
     *  
     */
    public void addListNotas(RelatorioConsultaNfNotaVO listNotas) {
    	
        this.listNotas.add( listNotas );
        
    }  	
    
    //	----[ Métodos Getter ]---------------------------------------------------  
    
	/** Método getter para a propriedade dtAbertura.
	 *
	 *  @return o valor atual de dtAbertura.
	 */
	public Date getDtAbertura() {
		return dtAbertura;
	}

	/** Método getter para a propriedade dtFechamento.
	 *
	 *  @return o valor atual de dtFechamento.
	 */
	public Date getDtFechamento() {
		return dtFechamento;
	}

	/** Método getter para a propriedade dtLiberacao.
	 *
	 *  @return o valor atual de dtLiberacao.
	 */
	public Date getDtLiberacao() {
		return dtLiberacao;
	}

	/** Método getter para a propriedade lote.
	 *
	 *  @return o valor atual de lote.
	 */
	public Serializable getLote() {
		return lote;
	}

	/** Método getter para a propriedade status.
	 *
	 *  @return o valor atual de status.
	 */
	public String getStatus() {
		return statusLote;
	}

	/** Método getter para a propriedade tipoLote.
	 *
	 *  @return o valor atual de tipoLote.
	 */
	public String getTipoLote() {
		return tipoLote;
	}

	/** Método getter para a propriedade listNotas.
	 *
	 *  @return o valor atual de listNotas.
	 */
	public List getListNotas() {
		return listNotas;
	}

	/** Método getter para a propriedade statusLote.
	 *
	 *  @return o valor atual de statusLote.
	 */
	public String getStatusLote() {
		return statusLote;
	}	

   //	----[ Métodos Setter ]---------------------------------------------------  

	/** Obtém o valor atual de dtAbertura.
	 * 
	 *  @param dtAbertura 
	 *    O novo valor para dtAbertura.
	 */
	public void setDtAbertura(Date dtAbertura) {
		this.dtAbertura = dtAbertura;
	}

	/** Obtém o valor atual de dtFechamento.
	 * 
	 *  @param dtFechamento 
	 *    O novo valor para dtFechamento.
	 */
	public void setDtFechamento(Date dtFechamento) {
		this.dtFechamento = dtFechamento;
	}

	/** Obtém o valor atual de dtLiberacao.
	 * 
	 *  @param dtLiberacao 
	 *    O novo valor para dtLiberacao.
	 */
	public void setDtLiberacao(Date dtLiberacao) {
		this.dtLiberacao = dtLiberacao;
	}

	/** Obtém o valor atual de lote.
	 * 
	 *  @param lote 
	 *    O novo valor para lote.
	 */
	public void setLote(Serializable lote) {
		this.lote = lote;
	}

	/** Obtém o valor atual de status.
	 * 
	 *  @param status 
	 *    O novo valor para status.
	 */
	public void setStatus(String statusLote) {
		this.statusLote = statusLote;
	}

	/** Obtém o valor atual de tipoLote.
	 * 
	 *  @param tipoLote 
	 *    O novo valor para tipoLote.
	 */
	public void setTipoLote(String tipoLote) {
		this.tipoLote = tipoLote;
	}

	/** Obtém o valor atual de listNotas.
	 * 
	 *  @param listNotas 
	 *    O novo valor para listNotas.
	 */
	public void setListNotas(List listNotas) {
		this.listNotas = listNotas;
	}

	/** Obtém o valor atual de statusLote.
	 * 
	 *  @param statusLote 
	 *    O novo valor para statusLote.
	 */
	public void setStatusLote(String statusLote) {
		this.statusLote = statusLote;
	}
    
}

/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioConsultaNfLoteVO.java
 *   .: Cria��o.....31 de agosto de 2007
 *   .: Autor.......Gisele Panosso
 *   .: Descri��o...Entidade "RelatorioConsultaNfLoteVO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.resource.infra.model.EntityObject;

public class RelatorioConsultaNfLoteVO extends EntityObject {

	//----[ Atributos de classe e inst�ncia ]---------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;    
    
    /** Armazena o campo lote do Relat�rio Consulta de Notas Fiscais. */
    private Serializable lote;

    /** Armazena o campo tipoLote do Relat�rio Consulta de Notas Fiscais. */
    private String tipoLote;
    
    /** Armazena o campo statusLote do Relat�rio Consulta de Notas Fiscais. */
    private String statusLote;
    
    /** Armazena o campo dtAbertura do Relat�rio Consulta de Notas Fiscais. */
    private Date dtAbertura;
        
    /** Armazena o campo dtLiberacao do Relat�rio Consulta de Notas Fiscais. */
    private Date dtLiberacao;
    
    /** Armazena o campo dtFechamento do Relat�rio Consulta de Notas Fiscais. */
    private Date dtFechamento;

    /** Armazena o campo listNotas do Relat�rio Consulta de Notas Fiscais. */
    private List listNotas;
    
    //	----[ M�todos Construtor ]---------------------------------------------------  

	public RelatorioConsultaNfLoteVO() {
		super();
		listNotas = new ArrayList();
	}

    //	----[ M�todos Add ]---------------------------------------------------  
	
	/** Adiciona uma lista de notas � listagem de Lotes.
     * 
     *  @param listNotas
     *      listNotas a ser adicionado.
     *  
     */
    public void addListNotas(RelatorioConsultaNfNotaVO listNotas) {
    	
        this.listNotas.add( listNotas );
        
    }  	
    
    //	----[ M�todos Getter ]---------------------------------------------------  
    
	/** M�todo getter para a propriedade dtAbertura.
	 *
	 *  @return o valor atual de dtAbertura.
	 */
	public Date getDtAbertura() {
		return dtAbertura;
	}

	/** M�todo getter para a propriedade dtFechamento.
	 *
	 *  @return o valor atual de dtFechamento.
	 */
	public Date getDtFechamento() {
		return dtFechamento;
	}

	/** M�todo getter para a propriedade dtLiberacao.
	 *
	 *  @return o valor atual de dtLiberacao.
	 */
	public Date getDtLiberacao() {
		return dtLiberacao;
	}

	/** M�todo getter para a propriedade lote.
	 *
	 *  @return o valor atual de lote.
	 */
	public Serializable getLote() {
		return lote;
	}

	/** M�todo getter para a propriedade status.
	 *
	 *  @return o valor atual de status.
	 */
	public String getStatus() {
		return statusLote;
	}

	/** M�todo getter para a propriedade tipoLote.
	 *
	 *  @return o valor atual de tipoLote.
	 */
	public String getTipoLote() {
		return tipoLote;
	}

	/** M�todo getter para a propriedade listNotas.
	 *
	 *  @return o valor atual de listNotas.
	 */
	public List getListNotas() {
		return listNotas;
	}

	/** M�todo getter para a propriedade statusLote.
	 *
	 *  @return o valor atual de statusLote.
	 */
	public String getStatusLote() {
		return statusLote;
	}	

   //	----[ M�todos Setter ]---------------------------------------------------  

	/** Obt�m o valor atual de dtAbertura.
	 * 
	 *  @param dtAbertura 
	 *    O novo valor para dtAbertura.
	 */
	public void setDtAbertura(Date dtAbertura) {
		this.dtAbertura = dtAbertura;
	}

	/** Obt�m o valor atual de dtFechamento.
	 * 
	 *  @param dtFechamento 
	 *    O novo valor para dtFechamento.
	 */
	public void setDtFechamento(Date dtFechamento) {
		this.dtFechamento = dtFechamento;
	}

	/** Obt�m o valor atual de dtLiberacao.
	 * 
	 *  @param dtLiberacao 
	 *    O novo valor para dtLiberacao.
	 */
	public void setDtLiberacao(Date dtLiberacao) {
		this.dtLiberacao = dtLiberacao;
	}

	/** Obt�m o valor atual de lote.
	 * 
	 *  @param lote 
	 *    O novo valor para lote.
	 */
	public void setLote(Serializable lote) {
		this.lote = lote;
	}

	/** Obt�m o valor atual de status.
	 * 
	 *  @param status 
	 *    O novo valor para status.
	 */
	public void setStatus(String statusLote) {
		this.statusLote = statusLote;
	}

	/** Obt�m o valor atual de tipoLote.
	 * 
	 *  @param tipoLote 
	 *    O novo valor para tipoLote.
	 */
	public void setTipoLote(String tipoLote) {
		this.tipoLote = tipoLote;
	}

	/** Obt�m o valor atual de listNotas.
	 * 
	 *  @param listNotas 
	 *    O novo valor para listNotas.
	 */
	public void setListNotas(List listNotas) {
		this.listNotas = listNotas;
	}

	/** Obt�m o valor atual de statusLote.
	 * 
	 *  @param statusLote 
	 *    O novo valor para statusLote.
	 */
	public void setStatusLote(String statusLote) {
		this.statusLote = statusLote;
	}
    
}

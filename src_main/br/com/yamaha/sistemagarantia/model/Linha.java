/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......Linha.java
 *   .: Cria��o.....23 de abril, 16:09
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descri��o...Entidade "Linha".
 */
package br.com.yamaha.sistemagarantia.model;

import java.util.Date;
import java.util.List;

import br.com.resource.infra.model.EntityObject;
import br.com.resource.infra.utils.DateUtils;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "Linha" no sistema.
 *  
 *  @author Thiago Uriel M. Garcia
 */
public class Linha extends EntityObject {

    //----[ Atributos de classe e inst�ncia ]-----------------------------------
	
	/** Constante para identificar o tipo de linha do produto como MOTOCICLETA */
    public static final Long TIPO_MOTOCICLETA = new Long(1);
    
    /** Constante para identificar o tipo de linha do produto como NAUTICA */
    public static final Long TIPO_NAUTICA     = new Long(2);
    
    /** Constante para identificar o tipo de linha do produto como PRODUTOS DE FOR�A */
    public static final Long TIPO_FORCA       = new Long(3);
    
    /** Constante para identificar o tipo de linha do produto como QUADRICICLO */
    public static final Long TIPO_QUADRICICLO = new Long(4);
	
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;     
    
    /** C�digo de neg�cio para a linha. */
    private String codigoLinha;
    
    /** Descri��o da linha. */
    private String descricao;
    
    /** Data de in�cio da linha. */
    private Date dataInicio;
    
    /** Data de t�rmino da linha. */
    private Date dataTermino;
    
    /** Lista de lotes desta linha */
    private List lotes;

    //----[ M�todos Auxiliares ]-----------------------------------------------    

    /** Determina se esta linha � do tipo <b>motocicleta</b>. */
    public boolean isMotocicleta() {
    	
    	return getEntityId() == TIPO_MOTOCICLETA;
    	
    }
    
    /** Determina se esta linha � do tipo <b>n�utica</b>. */
    public boolean isNautica() {
    	
    	return getEntityId() == TIPO_NAUTICA;
    	
    }

    /** Determina se esta linha � do tipo <b>for�a el�trica</b>. */
    public boolean isForca() {
    	
    	return getEntityId() == TIPO_FORCA;
    	
    }
    
    /** Determina se esta linha � do tipo <b>Quadriciclo</b>. */
    public boolean isQuadriciclo() {
    	
    	return getEntityId() == TIPO_QUADRICICLO;
    	
    }
    
    //----[ M�todos Getter ]---------------------------------------------------
    
    /** M�todo getter para "codigoLinha".
     *  @return
     *      <code>String</code>. O valor atual de codigoLinha.
     */
    public String getCodigoLinha() {
        return this.codigoLinha;
    }

    /** M�todo getter para "dataInicio".
     *  @return
     *      <code>Date</code>. O valor atual de dataInicio.
     */
    public Date getDataInicio() {
        return this.dataInicio;
    }

    /** M�todo getter para "dataInicio".
     *  @return
     *      <code>String</code>. O valor atual de dataInicio.
     */
    public String getMaskedDataInicio() {
        return DateUtils.applyMask(this.dataInicio);
    }    
    
    /** M�todo getter para "dataTermino".
     *  @return
     *      <code>Date</code>. O valor atual de dataTermino.
     */
    public Date getDataTermino() {
        return this.dataTermino;
    }

    /** M�todo getter para "dataTermino".
     *  @return
     *      <code>String</code>. O valor atual de dataTermino.
     */
    public String getMaskedDataTermino() {
        return DateUtils.applyMask(this.dataTermino);
    }    
    
    /** M�todo getter para "descricao".
     *  @return
     *      <code>String</code>. O valor atual de descricao.
     */
    public String getDescricao() {
        return this.descricao;
    }
    
    /** M�todo getter para o campo lotes
	 * 
	 * @return List
	 *
	 */
	public List getLotes() {
		return lotes;
	}


    //----[ M�todos Setter ]---------------------------------------------------
    
    /** M�todo setter para "codigoLinha".
     *  @param entityId
     *      <code>String</code> a ser designado para codigoLinha.
     */
    public void setCodigoLinha(String codigoLinha) {
        this.codigoLinha = codigoLinha;
    }

    /** M�todo setter para "dataInicio".
     *  @param entityId
     *      <code>Date</code> a ser designado para dataInicio.
     */
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = DateUtils.secureSet(dataInicio);
    }

    /** M�todo setter para "dataTermino".
     *  @param entityId
     *      <code>Date</code> a ser designado para dataTermino.
     */
    public void setDataTermino(Date dataTermino) {
        this.dataTermino = DateUtils.secureSet(dataTermino);
    }

    /** M�todo setter para "descricao".
     *  @param entityId
     *      <code>String</code> a ser designado para descricao.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
	
	/** M�todo setter para o campo lotes
	 *
	 * @param lotes  List
	 * 
	 */
	public void setLotes(List lotes) {
		this.lotes = lotes;
	}
    
    
    
}
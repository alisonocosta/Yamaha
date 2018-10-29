/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......Linha.java
 *   .: Criação.....23 de abril, 16:09
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Entidade "Linha".
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

    //----[ Atributos de classe e instância ]-----------------------------------
	
	/** Constante para identificar o tipo de linha do produto como MOTOCICLETA */
    public static final Long TIPO_MOTOCICLETA = new Long(1);
    
    /** Constante para identificar o tipo de linha do produto como NAUTICA */
    public static final Long TIPO_NAUTICA     = new Long(2);
    
    /** Constante para identificar o tipo de linha do produto como PRODUTOS DE FORÇA */
    public static final Long TIPO_FORCA       = new Long(3);
    
    /** Constante para identificar o tipo de linha do produto como QUADRICICLO */
    public static final Long TIPO_QUADRICICLO = new Long(4);
	
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;     
    
    /** Código de negócio para a linha. */
    private String codigoLinha;
    
    /** Descrição da linha. */
    private String descricao;
    
    /** Data de início da linha. */
    private Date dataInicio;
    
    /** Data de término da linha. */
    private Date dataTermino;
    
    /** Lista de lotes desta linha */
    private List lotes;

    //----[ Métodos Auxiliares ]-----------------------------------------------    

    /** Determina se esta linha é do tipo <b>motocicleta</b>. */
    public boolean isMotocicleta() {
    	
    	return getEntityId() == TIPO_MOTOCICLETA;
    	
    }
    
    /** Determina se esta linha é do tipo <b>náutica</b>. */
    public boolean isNautica() {
    	
    	return getEntityId() == TIPO_NAUTICA;
    	
    }

    /** Determina se esta linha é do tipo <b>força elétrica</b>. */
    public boolean isForca() {
    	
    	return getEntityId() == TIPO_FORCA;
    	
    }
    
    /** Determina se esta linha é do tipo <b>Quadriciclo</b>. */
    public boolean isQuadriciclo() {
    	
    	return getEntityId() == TIPO_QUADRICICLO;
    	
    }
    
    //----[ Métodos Getter ]---------------------------------------------------
    
    /** Método getter para "codigoLinha".
     *  @return
     *      <code>String</code>. O valor atual de codigoLinha.
     */
    public String getCodigoLinha() {
        return this.codigoLinha;
    }

    /** Método getter para "dataInicio".
     *  @return
     *      <code>Date</code>. O valor atual de dataInicio.
     */
    public Date getDataInicio() {
        return this.dataInicio;
    }

    /** Método getter para "dataInicio".
     *  @return
     *      <code>String</code>. O valor atual de dataInicio.
     */
    public String getMaskedDataInicio() {
        return DateUtils.applyMask(this.dataInicio);
    }    
    
    /** Método getter para "dataTermino".
     *  @return
     *      <code>Date</code>. O valor atual de dataTermino.
     */
    public Date getDataTermino() {
        return this.dataTermino;
    }

    /** Método getter para "dataTermino".
     *  @return
     *      <code>String</code>. O valor atual de dataTermino.
     */
    public String getMaskedDataTermino() {
        return DateUtils.applyMask(this.dataTermino);
    }    
    
    /** Método getter para "descricao".
     *  @return
     *      <code>String</code>. O valor atual de descricao.
     */
    public String getDescricao() {
        return this.descricao;
    }
    
    /** Método getter para o campo lotes
	 * 
	 * @return List
	 *
	 */
	public List getLotes() {
		return lotes;
	}


    //----[ Métodos Setter ]---------------------------------------------------
    
    /** Método setter para "codigoLinha".
     *  @param entityId
     *      <code>String</code> a ser designado para codigoLinha.
     */
    public void setCodigoLinha(String codigoLinha) {
        this.codigoLinha = codigoLinha;
    }

    /** Método setter para "dataInicio".
     *  @param entityId
     *      <code>Date</code> a ser designado para dataInicio.
     */
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = DateUtils.secureSet(dataInicio);
    }

    /** Método setter para "dataTermino".
     *  @param entityId
     *      <code>Date</code> a ser designado para dataTermino.
     */
    public void setDataTermino(Date dataTermino) {
        this.dataTermino = DateUtils.secureSet(dataTermino);
    }

    /** Método setter para "descricao".
     *  @param entityId
     *      <code>String</code> a ser designado para descricao.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
	
	/** Método setter para o campo lotes
	 *
	 * @param lotes  List
	 * 
	 */
	public void setLotes(List lotes) {
		this.lotes = lotes;
	}
    
    
    
}
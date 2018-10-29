/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......TipoLote.java
 *   .: Criação.....23 de abril, 15:53
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Entidade "TipoLote".
 */
package br.com.yamaha.sistemagarantia.model;

import java.util.List;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "TipoLote" no sistema.
 *  
 *  @author Thiago Uriel M. Garcia
 */
public class TipoLote extends EntityObject {

    //----[ Atributos de classe e instância ]-----------------------------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L; 
    
    /** Identifica um lote de revisão */
    public static final Long TIPO_REVISAO = new Long(1);
    
    /** Identifica um lote de garantia */
    public static final Long TIPO_GARANTIA = new Long(2);
    
    /** Codigo de negócio para o TipoLote. */
    private String codigoTipoLote;
    
    /** Descrição do TipoLote. */
    private String descricao;
    
    /** Lista de lotes deste TipoLote */
    private List lotes;
    
    //----[ Métodos Getter ]---------------------------------------------------
    
    
    /** Método getter para "codigoTipoLote".
     *  @return
     *      <code>String</code>. O valor atual de codigoTipoLote.
     */
    public String getCodigoTipoLote() {
        return this.codigoTipoLote;
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
    
    /** Método setter para "codigoTipoLote".
     *  @param entityId
     *      <code>String</code> a ser designado para codigoTipoLote.
     */
    public void setCodigoTipoLote(String codigoTipoLote) {
        this.codigoTipoLote = codigoTipoLote;
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
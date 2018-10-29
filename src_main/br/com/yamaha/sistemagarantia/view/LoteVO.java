/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......LoteVO.java
 *   .: Cria��o.....24 de fevereiro, 14:58
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descri��o...VO de Lote, Linha e TipoLote.
 */
package br.com.yamaha.sistemagarantia.view;

import java.io.Serializable;
import java.util.Date;

import br.com.resource.infra.utils.DateUtils;
import br.com.resource.infra.view.ViewObject;

/** View object juntando informa��es do Lote, relacionando 
 *  as entidades "Lote", "Linha" e "TipoLote".
 *  
 *  @author Thiago Uriel M. Garcia
 */
public class LoteVO extends ViewObject {

    //----[ Atributos de classe e inst�ncia ]-----------------------------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;    

    /** Identificador da entidade <code>Lote</code> utilizada
     *  na forma��o deste <i>View Object</i>.
     */
    private Serializable loteId;

    /** Identificador da entidade <code>Linha</code> utilizada
     *  na forma��o deste <i>View Object</i>.
     */
    private Serializable linhaId;

    /** Identificador da entidade <code>TipoLote</code> utilizada
     *  na forma��o deste <i>View Object</i>.
     */
    private Serializable tipoId;
    
    /** Identificador da entidade <code>StatusLote</code> utilizada
     *  na forma��o deste <i>View Object</i>.
     */
    private Serializable statusId;
    
    /** Nome do tipo do lote. */
    private String tipoLote;
    
    /** Nome da linha. */
    private String linha;
    
    /** Status atual do lote. */
    private String status;
    
    /** Data de abertura do lote. */
    private Date abertura;

    //----[ M�todos Getter ]---------------------------------------------------
    
    /** M�todo getter para "abertura".
     *  @return
     *      <code>Date</code>. O valor atual de abertura.
     */
    public Date getAbertura() {
        return this.abertura;
    }

    /** M�todo getter para "abertura".
     *  @return
     *      <code>String</code>. O valor atual de abertura.
     */
    public String getMaskedAbertura() {
        return DateUtils.applyMask(this.abertura);
    }    
    
    /** M�todo getter para "linha".
     *  @return
     *      <code>String</code>. O valor atual de linha.
     */
    public String getLinha() {
        return this.linha;
    }

    /** M�todo getter para "status".
     *  @return
     *      <code>String</code>. O valor atual de status.
     */
    public String getStatus() {
        return this.status;
    }

    /** M�todo getter para "tipoLote".
     *  @return
     *      <code>String</code>. O valor atual de tipoLote.
     */
    public String getTipoLote() {
        return this.tipoLote;
    }

    /** M�todo getter para "linhaId".
     *  @return
     *      <code>Serializable</code>. O valor atual de linhaId.
     */
    public Serializable getLinhaId() {
        return this.linhaId;
    }

    /** M�todo getter para "loteId".
     *  @return
     *      <code>Serializable</code>. O valor atual de loteId.
     */
    public Serializable getLoteId() {
        return this.loteId;
    }

    /** M�todo getter para "statusId".
     *  @return
     *      <code>Serializable</code>. O valor atual de statusId.
     */
    public Serializable getStatusId() {
        return this.statusId;
    }

    /** M�todo getter para "tipoId".
     *  @return
     *      <code>Serializable</code>. O valor atual de tipoId.
     */
    public Serializable getTipoId() {
        return this.tipoId;
    }    
    
    //----[ M�todos Setter ]---------------------------------------------------
    
    /** M�todo setter para "abertura".
     *  @param entityId
     *      <code>Object</code> a ser designado para abertura.
     */
    public void setAbertura(Object abertura) {
        this.abertura = DateUtils.secureSet(abertura);
    }

    /** M�todo setter para "linha".
     *  @param entityId
     *      <code>String</code> a ser designado para linha.
     */
    public void setLinha(String linha) {
        this.linha = linha;
    }

    /** M�todo setter para "status".
     *  @param entityId
     *      <code>String</code> a ser designado para status.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /** M�todo setter para "tipoLote".
     *  @param entityId
     *      <code>String</code> a ser designado para tipoLote.
     */
    public void setTipoLote(String tipoLote) {
        this.tipoLote = tipoLote;
    }

    /** M�todo setter para "linhaId".
     *  @param entityId
     *      <code>Serializable</code> a ser designado para linhaId.
     */
    public void setLinhaId(Serializable linhaId) {
        this.linhaId = linhaId;
    }

    /** M�todo setter para "loteId".
     *  @param entityId
     *      <code>Serializable</code> a ser designado para loteId.
     */
    public void setLoteId(Serializable loteId) {
        this.loteId = loteId;
    }

    /** M�todo setter para "statusId".
     *  @param entityId
     *      <code>Serializable</code> a ser designado para statusId.
     */
    public void setStatusId(Serializable statusId) {
        this.statusId = statusId;
    }

    /** M�todo setter para "tipoId".
     *  @param entityId
     *      <code>Serializable</code> a ser designado para tipoId.
     */
    public void setTipoId(Serializable tipoId) {
        this.tipoId = tipoId;
    }
    
}
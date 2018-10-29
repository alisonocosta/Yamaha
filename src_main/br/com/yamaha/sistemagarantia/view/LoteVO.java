/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......LoteVO.java
 *   .: Criação.....24 de fevereiro, 14:58
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...VO de Lote, Linha e TipoLote.
 */
package br.com.yamaha.sistemagarantia.view;

import java.io.Serializable;
import java.util.Date;

import br.com.resource.infra.utils.DateUtils;
import br.com.resource.infra.view.ViewObject;

/** View object juntando informações do Lote, relacionando 
 *  as entidades "Lote", "Linha" e "TipoLote".
 *  
 *  @author Thiago Uriel M. Garcia
 */
public class LoteVO extends ViewObject {

    //----[ Atributos de classe e instância ]-----------------------------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;    

    /** Identificador da entidade <code>Lote</code> utilizada
     *  na formação deste <i>View Object</i>.
     */
    private Serializable loteId;

    /** Identificador da entidade <code>Linha</code> utilizada
     *  na formação deste <i>View Object</i>.
     */
    private Serializable linhaId;

    /** Identificador da entidade <code>TipoLote</code> utilizada
     *  na formação deste <i>View Object</i>.
     */
    private Serializable tipoId;
    
    /** Identificador da entidade <code>StatusLote</code> utilizada
     *  na formação deste <i>View Object</i>.
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

    //----[ Métodos Getter ]---------------------------------------------------
    
    /** Método getter para "abertura".
     *  @return
     *      <code>Date</code>. O valor atual de abertura.
     */
    public Date getAbertura() {
        return this.abertura;
    }

    /** Método getter para "abertura".
     *  @return
     *      <code>String</code>. O valor atual de abertura.
     */
    public String getMaskedAbertura() {
        return DateUtils.applyMask(this.abertura);
    }    
    
    /** Método getter para "linha".
     *  @return
     *      <code>String</code>. O valor atual de linha.
     */
    public String getLinha() {
        return this.linha;
    }

    /** Método getter para "status".
     *  @return
     *      <code>String</code>. O valor atual de status.
     */
    public String getStatus() {
        return this.status;
    }

    /** Método getter para "tipoLote".
     *  @return
     *      <code>String</code>. O valor atual de tipoLote.
     */
    public String getTipoLote() {
        return this.tipoLote;
    }

    /** Método getter para "linhaId".
     *  @return
     *      <code>Serializable</code>. O valor atual de linhaId.
     */
    public Serializable getLinhaId() {
        return this.linhaId;
    }

    /** Método getter para "loteId".
     *  @return
     *      <code>Serializable</code>. O valor atual de loteId.
     */
    public Serializable getLoteId() {
        return this.loteId;
    }

    /** Método getter para "statusId".
     *  @return
     *      <code>Serializable</code>. O valor atual de statusId.
     */
    public Serializable getStatusId() {
        return this.statusId;
    }

    /** Método getter para "tipoId".
     *  @return
     *      <code>Serializable</code>. O valor atual de tipoId.
     */
    public Serializable getTipoId() {
        return this.tipoId;
    }    
    
    //----[ Métodos Setter ]---------------------------------------------------
    
    /** Método setter para "abertura".
     *  @param entityId
     *      <code>Object</code> a ser designado para abertura.
     */
    public void setAbertura(Object abertura) {
        this.abertura = DateUtils.secureSet(abertura);
    }

    /** Método setter para "linha".
     *  @param entityId
     *      <code>String</code> a ser designado para linha.
     */
    public void setLinha(String linha) {
        this.linha = linha;
    }

    /** Método setter para "status".
     *  @param entityId
     *      <code>String</code> a ser designado para status.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /** Método setter para "tipoLote".
     *  @param entityId
     *      <code>String</code> a ser designado para tipoLote.
     */
    public void setTipoLote(String tipoLote) {
        this.tipoLote = tipoLote;
    }

    /** Método setter para "linhaId".
     *  @param entityId
     *      <code>Serializable</code> a ser designado para linhaId.
     */
    public void setLinhaId(Serializable linhaId) {
        this.linhaId = linhaId;
    }

    /** Método setter para "loteId".
     *  @param entityId
     *      <code>Serializable</code> a ser designado para loteId.
     */
    public void setLoteId(Serializable loteId) {
        this.loteId = loteId;
    }

    /** Método setter para "statusId".
     *  @param entityId
     *      <code>Serializable</code> a ser designado para statusId.
     */
    public void setStatusId(Serializable statusId) {
        this.statusId = statusId;
    }

    /** Método setter para "tipoId".
     *  @param entityId
     *      <code>Serializable</code> a ser designado para tipoId.
     */
    public void setTipoId(Serializable tipoId) {
        this.tipoId = tipoId;
    }
    
}
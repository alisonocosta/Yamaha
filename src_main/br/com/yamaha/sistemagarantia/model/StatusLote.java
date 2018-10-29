/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......StatusLote.java
 *   .: Cria��o.....24 de abril, 16:09
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descri��o...Entidade "StatusLote".
 */
package br.com.yamaha.sistemagarantia.model;

import java.util.List;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "StatusLote" no sistema.
 *  
 *  @author Thiago Uriel M. Garcia
 */
public class StatusLote extends EntityObject {

    //----[ Atributos de classe e inst�ncia ]-----------------------------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;    
    
    /** Constatnte. Status EM DIGITA��O. */ 
    public static final Long STATUS_EM_DIGITACAO = new Long(1); 
    
    /** Constatnte. Status Em An�lise. */ 
    public static final Long STATUS_ANALISE = new Long(2);
    
    /** Constatnte. Status Recusado. */ 
    public static final Long STATUS_RECUSADO = new Long(3);

    /** Constatnte. Status Manuten�ao. */ 
    public static final Long STATUS_MANUTENCAO = new Long(4);
    
    /** Constatnte. Status Aguar.Dig das NF�s. */ 
    public static final Long STATUS_AGUARDANDO_DIGITACAO_NOTA = new Long(5);
    
    /** Constatnte. Status Aguar.Receb. Doctos. */ 
    public static final Long STATUS_AGUARDANDO_RECEB_DOC = new Long(6); 
    
    /** Constatnte. Status LIBERADO PARA PAGAMENTO. */ 
    public static final Long STATUS_LIBERADO_PAGAMENTO = new Long(7); 
    
    /** Constatnte. Status NF DIVERGENTE. */ 
    public static final Long STATUS_NF_DIVERGENTE = new Long(8);
    
    /** Constatnte. Status PARCIALMENTE PAGO. */ 
    public static final Long STATUS_PARCIALMENTE_PAGO = new Long(9); 
        
    /** Constatnte. Status DIGITA�AO INCOMPLETA. */ 
    public static final Long STATUS_DIGITACAO_INCOMPLETA = new Long(12); 
    
    /** Constatnte. Status DEVOLVIDO. */ 
    public static final Long STATUS_DEVOLVIDO = new Long(16);
    
    /** Constatnte. Status CANCELADO. */ 
    public static final Long STATUS_CANCELADO = new Long(17);
    
    /** Constatnte. Status CONTROLE. */ 
    public static final Long STATUS_PERIODICA = new Long(19);
    
    /** Constatnte. Status Aguardando Adiantamento e Documentos. */ 
    public static final Long STATUS_AGUARDANDO_ADIANTAMENTO_RECEB_DOC = new Long(20);
    
    /** Constatnte. Status Adiant. Pago / Aguar. Doctos. */ 
    public static final Long STATUS_ADIANTAMENTO_PAGO_AGUARDANDO_RECEB_DOC = new Long(21);
    
    /** Constatnte. Status Aguardando Classific��o T�cnica. */ 
    public static final Long STATUS_AGUARDANDO_CLASSIFICACAO_TECNICA = new Long(27);
    
    /** Descri��o completa do status. */
    private String descricao;
    
    /** Descri��o reduzida do status. */
    private String descricaoReduzida;
    
    /** Descri��o rda m�scara do status do lote. */
    private String mascara;
    
    /** Cor utilizada na apresenta��o do status (ex. #FFFFFF). */
    private String corStatus;
    
    /** Lista de lotes deste status */
    private List lotes;

    
    //----[ M�todos Getter ]---------------------------------------------------
    
    /** M�todo getter para o campo lotes
	 * 
	 * @return List
	 *
	 */
	public List getLotes() {
		return lotes;
	}

	/** M�todo getter para "descricao".
     *  @return
     *      <code>String</code>. O valor atual de descricao.
     */
    public String getDescricao() {
        return this.descricao;
    }

    /** M�todo getter para "descricaoReduzida".
     *  @return
     *      <code>String</code>. O valor atual de descricaoReduzida.
     */
    public String getDescricaoReduzida() {
        return this.descricaoReduzida;
    }

    /** M�todo getter para "corStatus".
     *  @return
     *      <code>String</code>. O valor atual de corStatus.
     */
    public String getCorStatus() {
        return this.corStatus;
    }   


	/** M�todo getter para a propriedade mascara
	 *
	 *  @return String de mascara
	 */
	public String getMascara() {
		return mascara;
	}

    //----[ M�todos Setter ]---------------------------------------------------
        
    /** M�todo setter para "descricao".
     *  @param entityId
     *      <code>String</code> a ser designado para descricao.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /** M�todo setter para "descricaoReduzida".
     *  @param entityId
     *      <code>String</code> a ser designado para descricaoReduzida.
     */
    public void setDescricaoReduzida(String descricaoReduzida) {
        this.descricaoReduzida = descricaoReduzida;
    }

    /** M�todo setter para "corStatus".
     *  @param entityId
     *      <code>String</code> a ser designado para corStatus.
     */
    public void setCorStatus(String corStatus) {
        this.corStatus = corStatus;
    }

	/** M�todo setter para o campo lotes
	 *
	 * @param lotes  List
	 * 
	 */
	public void setLotes(List lotes) {
		this.lotes = lotes;
	}

	/** M�todo setter para a propriedade mascara
	 *
	 * @param mascara String
	 */
	public void setMascara(String mascara) {
		this.mascara = mascara;
	}

	
}
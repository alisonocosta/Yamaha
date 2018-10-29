/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......StatusLote.java
 *   .: Criação.....24 de abril, 16:09
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Entidade "StatusLote".
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

    //----[ Atributos de classe e instância ]-----------------------------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;    
    
    /** Constatnte. Status EM DIGITAÇÃO. */ 
    public static final Long STATUS_EM_DIGITACAO = new Long(1); 
    
    /** Constatnte. Status Em Análise. */ 
    public static final Long STATUS_ANALISE = new Long(2);
    
    /** Constatnte. Status Recusado. */ 
    public static final Long STATUS_RECUSADO = new Long(3);

    /** Constatnte. Status Manutençao. */ 
    public static final Long STATUS_MANUTENCAO = new Long(4);
    
    /** Constatnte. Status Aguar.Dig das NF´s. */ 
    public static final Long STATUS_AGUARDANDO_DIGITACAO_NOTA = new Long(5);
    
    /** Constatnte. Status Aguar.Receb. Doctos. */ 
    public static final Long STATUS_AGUARDANDO_RECEB_DOC = new Long(6); 
    
    /** Constatnte. Status LIBERADO PARA PAGAMENTO. */ 
    public static final Long STATUS_LIBERADO_PAGAMENTO = new Long(7); 
    
    /** Constatnte. Status NF DIVERGENTE. */ 
    public static final Long STATUS_NF_DIVERGENTE = new Long(8);
    
    /** Constatnte. Status PARCIALMENTE PAGO. */ 
    public static final Long STATUS_PARCIALMENTE_PAGO = new Long(9); 
        
    /** Constatnte. Status DIGITAÇAO INCOMPLETA. */ 
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
    
    /** Constatnte. Status Aguardando Classificção Técnica. */ 
    public static final Long STATUS_AGUARDANDO_CLASSIFICACAO_TECNICA = new Long(27);
    
    /** Descrição completa do status. */
    private String descricao;
    
    /** Descrição reduzida do status. */
    private String descricaoReduzida;
    
    /** Descrição rda máscara do status do lote. */
    private String mascara;
    
    /** Cor utilizada na apresentação do status (ex. #FFFFFF). */
    private String corStatus;
    
    /** Lista de lotes deste status */
    private List lotes;

    
    //----[ Métodos Getter ]---------------------------------------------------
    
    /** Método getter para o campo lotes
	 * 
	 * @return List
	 *
	 */
	public List getLotes() {
		return lotes;
	}

	/** Método getter para "descricao".
     *  @return
     *      <code>String</code>. O valor atual de descricao.
     */
    public String getDescricao() {
        return this.descricao;
    }

    /** Método getter para "descricaoReduzida".
     *  @return
     *      <code>String</code>. O valor atual de descricaoReduzida.
     */
    public String getDescricaoReduzida() {
        return this.descricaoReduzida;
    }

    /** Método getter para "corStatus".
     *  @return
     *      <code>String</code>. O valor atual de corStatus.
     */
    public String getCorStatus() {
        return this.corStatus;
    }   


	/** Método getter para a propriedade mascara
	 *
	 *  @return String de mascara
	 */
	public String getMascara() {
		return mascara;
	}

    //----[ Métodos Setter ]---------------------------------------------------
        
    /** Método setter para "descricao".
     *  @param entityId
     *      <code>String</code> a ser designado para descricao.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /** Método setter para "descricaoReduzida".
     *  @param entityId
     *      <code>String</code> a ser designado para descricaoReduzida.
     */
    public void setDescricaoReduzida(String descricaoReduzida) {
        this.descricaoReduzida = descricaoReduzida;
    }

    /** Método setter para "corStatus".
     *  @param entityId
     *      <code>String</code> a ser designado para corStatus.
     */
    public void setCorStatus(String corStatus) {
        this.corStatus = corStatus;
    }

	/** Método setter para o campo lotes
	 *
	 * @param lotes  List
	 * 
	 */
	public void setLotes(List lotes) {
		this.lotes = lotes;
	}

	/** Método setter para a propriedade mascara
	 *
	 * @param mascara String
	 */
	public void setMascara(String mascara) {
		this.mascara = mascara;
	}

	
}
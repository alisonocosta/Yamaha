/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......StatusGarantia.java
 *   .: Criação.....05 de junho, 19:04
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Entidade "StatusGarantia".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;

public class StatusGarantia extends EntityObject {

    //----[ Atributos de classe e instância ]-----------------------------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;  
    
    /** Constatnte. Status Aguar.Dig das NF´s. */ 
    public static final Long STATUS_AGUARDANDO_DIGITACAO_NOTA = new Long(0); 
    
    /** Constatnte. Status EM ANÁLISE. */ 
    public static final Long STATUS_ANALISE = new Long(1); 
    
    /** Constatnte. Status AUTORIZADO PAGAMENTO. */ 
    public static final Long STATUS_AUTORIZADO_PAGAMENTO = new Long(2);
    
    /** Constatnte. Status EM MANUTENÇÃO. */ 
    public static final Long STATUS_MANUTENCAO = new Long(5);
    
    /** Constatnte. Status RECUSADO. */ 
    public static final Long STATUS_RECUSADO = new Long(6); 
    
    /** Constatnte. Status PARCIALMENTE PAGO. */ 
    public static final Long STATUS_PARCIALMENTE_PAGO = new Long(9); 
    
    /** Constatnte. Status NF DIVERGENTE. */ 
    public static final Long STATUS_NF_DIVERGENTE = new Long(12);
    
    /** Constatnte. Status NF DIGITADA. */ 
    public static final Long STATUS_NF_DIGITADA = new Long(13);
    
    /** Constatnte. Status Aguar. Envio de Doc. */ 
    public static final Long STATUS_AGUARDANDO_ENVIO_DOCUMENTOS = new Long(14); 
    
    /** Constatnte. Status LIBERADO PARA PAGAMENTO. */ 
    public static final Long STATUS_LIBERADO_PAGAMENTO = new Long(15); 
    
    /** Constatnte. Status PAGO. */ 
    public static final Long STATUS_PAGO = new Long(16); 
    
    /** Constatnte. Status EM DIGITAÇÃO. */ 
    public static final Long STATUS_DIGITACAO = new Long(18); 
    
    /** Constatnte. Status EM CONTROLE. */ 
    public static final Long STATUS_PERIODICA = new Long(19); 
    
    /** Constatnte. Status Aguar. Adiant. e Envio de Doc. */ 
    public static final Long STATUS_AGUARDANDO_ADIANTAMENTO_DOCUMENTOS = new Long(20); 
    
    /** Constatnte. Status Aguar. Adiant. e Envio de Doc. */ 
    public static final Long STATUS_ADIANTAMENTO_PAGO_AGUARD_DOCUMENTOS = new Long(21); 
    
    /** Constatnte. Status AGUARDANDO APROVAÇÃO DA INFORMAÇÃO DE MERCADO. */ 
    public static final Long STATUS_AGUARDANDO_APROVACAO_INFO_MERCADO = new Long(24);
    
    /** Constatnte. Status Aguardando Classificção Técnica. */ 
    public static final Long STATUS_AGUARDANDO_CLASSIFICACAO_TECNICA = new Long(26);
    
    /** Constatnte. Status CANCELADA. */ 
    public static final Long STATUS_CANCELADA = new Long(25); 
    
    /** Descrição do status. */
    private String descricao;
    
    /**
     * Retorna uma instancia de StatusGarantia de acordo com o ID
     * @param statusGarantia_id
     * @return
     */
    public static StatusGarantia getInstance(Long statusGarantia_id){
    	
    	StatusGarantia statusGar = new StatusGarantia();
    	statusGar.setEntityId(statusGarantia_id);
    	
    	return statusGar;
    }

    //----[ Métodos Getter ]---------------------------------------------------    
    
	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

    //----[ Métodos Setter ]---------------------------------------------------	
	
	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
    
}
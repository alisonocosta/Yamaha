/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......ManualRevisao.java
 *   .: Criação.....21 de feverreiro 2008, 14:42
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "ManualRevisao".
 */
package br.com.yamaha.sistemagarantia.model;

import java.util.Date;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "ManualRevisao" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class ManualRevisao extends EntityObject {
	
	//----[ Atributos de classe e instância ]-----------------------------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L; 
    
    /** Código do chassi */
    private String chassi;
    
    /** Novo número do Manual */
    private String numeroManual;
    
    /** Número antigo */
    private String manualSubst;
    
    /** Data da Substituição */
    private Date dataSubst;    
    
    /** Entidade concessinariaId */
    private Long concessionariaId;

	/** Método getter para a propriedade chassi
	 *
	 *  @return String de chassi
	 */
	public String getChassi() {
		return chassi;
	}

	/** Método setter para a propriedade chassi
	 *
	 * @param chassi String
	 */
	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	/** Método getter para a propriedade concessinaria
	 *
	 *  @return Concessionaria de concessinariaId
	 */
	public Long getConcessionariaId() {
		return concessionariaId;
	}

	/** Método setter para a propriedade concessinaria
	 *
	 * @param concessinaria Concessionaria
	 */
	public void setConcessionariaId(Long concessionariaId) {
		this.concessionariaId = concessionariaId;
	}

	/** Método getter para a propriedade dataSubst
	 *
	 *  @return Date de dataSubst
	 */
	public Date getDataSubst() {
		return dataSubst;
	}

	/** Método setter para a propriedade dataSubst
	 *
	 * @param dataSubst Date
	 */
	public void setDataSubst(Date dataSubst) {
		this.dataSubst = dataSubst;
	}

	/** Método getter para a propriedade manualSubst
	 *
	 *  @return String de manualSubst
	 */
	public String getManualSubst() {
		return manualSubst;
	}

	/** Método setter para a propriedade manualSubst
	 *
	 * @param manualSubst String
	 */
	public void setManualSubst(String manualSubst) {
		this.manualSubst = manualSubst;
	}

	/** Método getter para a propriedade numeroManual
	 *
	 *  @return String de numeroManual
	 */
	public String getNumeroManual() {
		return numeroManual;
	}

	/** Método setter para a propriedade numeroManual
	 *
	 * @param numeroManual String
	 */
	public void setNumeroManual(String numeroManual) {
		this.numeroManual = numeroManual;
	}
    
}

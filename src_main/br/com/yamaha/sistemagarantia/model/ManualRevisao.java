/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......ManualRevisao.java
 *   .: Cria��o.....21 de feverreiro 2008, 14:42
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "ManualRevisao".
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
	
	//----[ Atributos de classe e inst�ncia ]-----------------------------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L; 
    
    /** C�digo do chassi */
    private String chassi;
    
    /** Novo n�mero do Manual */
    private String numeroManual;
    
    /** N�mero antigo */
    private String manualSubst;
    
    /** Data da Substitui��o */
    private Date dataSubst;    
    
    /** Entidade concessinariaId */
    private Long concessionariaId;

	/** M�todo getter para a propriedade chassi
	 *
	 *  @return String de chassi
	 */
	public String getChassi() {
		return chassi;
	}

	/** M�todo setter para a propriedade chassi
	 *
	 * @param chassi String
	 */
	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	/** M�todo getter para a propriedade concessinaria
	 *
	 *  @return Concessionaria de concessinariaId
	 */
	public Long getConcessionariaId() {
		return concessionariaId;
	}

	/** M�todo setter para a propriedade concessinaria
	 *
	 * @param concessinaria Concessionaria
	 */
	public void setConcessionariaId(Long concessionariaId) {
		this.concessionariaId = concessionariaId;
	}

	/** M�todo getter para a propriedade dataSubst
	 *
	 *  @return Date de dataSubst
	 */
	public Date getDataSubst() {
		return dataSubst;
	}

	/** M�todo setter para a propriedade dataSubst
	 *
	 * @param dataSubst Date
	 */
	public void setDataSubst(Date dataSubst) {
		this.dataSubst = dataSubst;
	}

	/** M�todo getter para a propriedade manualSubst
	 *
	 *  @return String de manualSubst
	 */
	public String getManualSubst() {
		return manualSubst;
	}

	/** M�todo setter para a propriedade manualSubst
	 *
	 * @param manualSubst String
	 */
	public void setManualSubst(String manualSubst) {
		this.manualSubst = manualSubst;
	}

	/** M�todo getter para a propriedade numeroManual
	 *
	 *  @return String de numeroManual
	 */
	public String getNumeroManual() {
		return numeroManual;
	}

	/** M�todo setter para a propriedade numeroManual
	 *
	 * @param numeroManual String
	 */
	public void setNumeroManual(String numeroManual) {
		this.numeroManual = numeroManual;
	}
    
}

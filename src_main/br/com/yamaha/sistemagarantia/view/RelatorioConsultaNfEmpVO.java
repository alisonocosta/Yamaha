/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioConsultaNfEmpVO.java
 *   .: Cria��o.....03 de agosto de 2007
 *   .: Autor.......Gisele Panosso
 *   .: Descri��o...Entidade "RelatorioConsultaNfEmpVO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.util.ArrayList;
import java.util.List;

import br.com.resource.infra.model.EntityObject;

public class RelatorioConsultaNfEmpVO extends EntityObject {

	//----[ Atributos de classe e inst�ncia ]---------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;    
    
    /** Armazena o campo linha do Relat�rio Consulta de Notas Fiscais. */
    private String linha;
 
    /** Armazena o campo empresaId do Relat�rio Consulta de Notas Fiscais. */
    private Long empresaId;
    
    /** Armazena o campo empresa do Relat�rio Consulta de Notas Fiscais. */
    private String empresa;

    /** Armazena o campo listConc (lista de concessionarias) do Relat�rio Consulta de Notas Fiscais. */
    private List listConc;

    
    //	----[ M�todos Construtor ]---------------------------------------------------  

	public RelatorioConsultaNfEmpVO() {
		super();
		listConc = new ArrayList();
	}

    //	----[ M�todos Add ]---------------------------------------------------  
	
	/** Adiciona uma lista de concession�rias � listagem de Empresas.
     * 
     *  @param listConc
     *      listConc a ser adicionado.
     *  
     */
    public void addListConc(RelatorioConsultaNfConcVO listConc) {
    	
        this.listConc.add( listConc );
        
    }  	

    //	----[ M�todos Getter ]---------------------------------------------------  

	/** M�todo getter para a propriedade empresaId.
	 *
	 *  @return o valor atual de empresaId.
	 */
	public Long getEmpresaId() {
		return empresaId;
	} 
	
	/** M�todo getter para a propriedade empresa.
	 *
	 *  @return o valor atual de empresa.
	 */
	public String getEmpresa() {
		return empresa;
	}

	/** M�todo getter para a propriedade linha.
	 *
	 *  @return o valor atual de linha.
	 */
	public String getLinha() {
		return linha;
	}

	/** M�todo getter para a propriedade listConc.
	 *
	 *  @return o valor atual de listConc.
	 */
	public List getListConc() {
		return listConc;
	}	

    //	----[ M�todos Setter ]---------------------------------------------------  

	/** Obt�m o valor atual de empresaId.
	 * 
	 *  @param empresaId 
	 *    O novo valor para empresaId.
	 */
	public void setEmpresaId(Long empresaId) {
		this.empresaId = empresaId;
	}
	
	/** Obt�m o valor atual de empresa.
	 * 
	 *  @param empresa 
	 *    O novo valor para empresa.
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/** Obt�m o valor atual de linha.
	 * 
	 *  @param linha 
	 *    O novo valor para linha.
	 */
	public void setLinha(String linha) {
		this.linha = linha;
	}

	/** Obt�m o valur atual de listConc.
	 * 
	 *  @param listConc 
	 *    O novo valor para listConc.
	 */
	public void setListConc(List listConc) {
		this.listConc = listConc;
	}
    
}

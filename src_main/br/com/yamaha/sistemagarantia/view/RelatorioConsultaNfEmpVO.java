/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioConsultaNfEmpVO.java
 *   .: Criação.....03 de agosto de 2007
 *   .: Autor.......Gisele Panosso
 *   .: Descrição...Entidade "RelatorioConsultaNfEmpVO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.util.ArrayList;
import java.util.List;

import br.com.resource.infra.model.EntityObject;

public class RelatorioConsultaNfEmpVO extends EntityObject {

	//----[ Atributos de classe e instância ]---------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;    
    
    /** Armazena o campo linha do Relatório Consulta de Notas Fiscais. */
    private String linha;
 
    /** Armazena o campo empresaId do Relatório Consulta de Notas Fiscais. */
    private Long empresaId;
    
    /** Armazena o campo empresa do Relatório Consulta de Notas Fiscais. */
    private String empresa;

    /** Armazena o campo listConc (lista de concessionarias) do Relatório Consulta de Notas Fiscais. */
    private List listConc;

    
    //	----[ Métodos Construtor ]---------------------------------------------------  

	public RelatorioConsultaNfEmpVO() {
		super();
		listConc = new ArrayList();
	}

    //	----[ Métodos Add ]---------------------------------------------------  
	
	/** Adiciona uma lista de concessionárias à listagem de Empresas.
     * 
     *  @param listConc
     *      listConc a ser adicionado.
     *  
     */
    public void addListConc(RelatorioConsultaNfConcVO listConc) {
    	
        this.listConc.add( listConc );
        
    }  	

    //	----[ Métodos Getter ]---------------------------------------------------  

	/** Método getter para a propriedade empresaId.
	 *
	 *  @return o valor atual de empresaId.
	 */
	public Long getEmpresaId() {
		return empresaId;
	} 
	
	/** Método getter para a propriedade empresa.
	 *
	 *  @return o valor atual de empresa.
	 */
	public String getEmpresa() {
		return empresa;
	}

	/** Método getter para a propriedade linha.
	 *
	 *  @return o valor atual de linha.
	 */
	public String getLinha() {
		return linha;
	}

	/** Método getter para a propriedade listConc.
	 *
	 *  @return o valor atual de listConc.
	 */
	public List getListConc() {
		return listConc;
	}	

    //	----[ Métodos Setter ]---------------------------------------------------  

	/** Obtém o valor atual de empresaId.
	 * 
	 *  @param empresaId 
	 *    O novo valor para empresaId.
	 */
	public void setEmpresaId(Long empresaId) {
		this.empresaId = empresaId;
	}
	
	/** Obtém o valor atual de empresa.
	 * 
	 *  @param empresa 
	 *    O novo valor para empresa.
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/** Obtém o valor atual de linha.
	 * 
	 *  @param linha 
	 *    O novo valor para linha.
	 */
	public void setLinha(String linha) {
		this.linha = linha;
	}

	/** Obtém o valur atual de listConc.
	 * 
	 *  @param listConc 
	 *    O novo valor para listConc.
	 */
	public void setListConc(List listConc) {
		this.listConc = listConc;
	}
    
}

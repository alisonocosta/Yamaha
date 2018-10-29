/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioHistChassiPecaVO.java
 *   .: Criação.....12 de agosto, 16:00
 *   .: Autor.......Gisele Panosso
 *   .: Descrição...Entidade "RelatorioHistChassiPecaVO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.math.BigDecimal;

import br.com.resource.infra.model.EntityObject;

public class RelatorioHistChassiPecaVO extends EntityObject {
	
	//----[ Atributos de classe e instância ]---------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;    
    
    /** Armazena o campo peca do Relatório Consulta Histórico Chassi. */
    private String peca;
  
    /** Armazena o campo vlServico3 do Relatório Consulta Histórico Chassi. */
    private BigDecimal vlServico3;
    
    /** Armazena o campo codServico do Relatório Consulta Histórico Chassi. */
    private String codServico;
    
    /** Armazena o campo codSintoma do Relatório Consulta Histórico Chassi. */
    private String codSintoma;
    
    /** Armazena o campo codCondicao do Relatório Consulta Histórico Chassi. */
    private String codCondicao;
    
    /** Armazena o campo defeito do Relatório Consulta Histórico Chassi. */
    private String defeito;
    
    
    //	----[ Métodos Getter ]---------------------------------------------------

	/** Método getter para a propriedade codCondicao.
	 *
	 *  @return o valor atual de codCondicao.
	 */
	public String getCodCondicao() {
		return codCondicao;
	}

	/** Método getter para a propriedade codServico.
	 *
	 *  @return o valor atual de codServico.
	 */
	public String getCodServico() {
		return codServico;
	}

	/** Método getter para a propriedade codSintoma.
	 *
	 *  @return o valor atual de codSintoma.
	 */
	public String getCodSintoma() {
		return codSintoma;
	}

	/** Método getter para a propriedade defeito.
	 *
	 *  @return o valor atual de defeito.
	 */
	public String getDefeito() {
		return defeito;
	}

	/** Método getter para a propriedade peca.
	 *
	 *  @return o valor atual de peca.
	 */
	public String getPeca() {
		return peca;
	}

	/** Método getter para a propriedade vlServico3.
	 *
	 *  @return o valor atual de vlServico3.
	 */
	public BigDecimal getVlServico3() {
		return vlServico3;
	}

    //	----[ Métodos Setter ]---------------------------------------------------
	
	/** Obtém o valur atual de codCondicao.
	 * 
	 *  @param codCondicao 
	 *    O novo valor para codCondicao.
	 */
	public void setCodCondicao(String codCondicao) {
		this.codCondicao = codCondicao;
	}

	/** Obtém o valur atual de codServico.
	 * 
	 *  @param codServico 
	 *    O novo valor para codServico.
	 */
	public void setCodServico(String codServico) {
		this.codServico = codServico;
	}

	/** Obtém o valur atual de codSintoma.
	 * 
	 *  @param codSintoma 
	 *    O novo valor para codSintoma.
	 */
	public void setCodSintoma(String codSintoma) {
		this.codSintoma = codSintoma;
	}

	/** Obtém o valur atual de defeito.
	 * 
	 *  @param defeito 
	 *    O novo valor para defeito.
	 */
	public void setDefeito(String defeito) {
		this.defeito = defeito;
	}

	/** Obtém o valur atual de peca.
	 * 
	 *  @param peca 
	 *    O novo valor para peca.
	 */
	public void setPeca(String peca) {
		this.peca = peca;
	}

	/** Obtém o valur atual de vlServico3.
	 * 
	 *  @param vlServico3 
	 *    O novo valor para vlServico3.
	 */
	public void setVlServico3(BigDecimal vlServico3) {
		this.vlServico3 = vlServico3;
	}


}

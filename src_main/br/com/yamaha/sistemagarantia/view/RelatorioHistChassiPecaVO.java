/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......RelatorioHistChassiPecaVO.java
 *   .: Cria��o.....12 de agosto, 16:00
 *   .: Autor.......Gisele Panosso
 *   .: Descri��o...Entidade "RelatorioHistChassiPecaVO".
 */
package br.com.yamaha.sistemagarantia.view;

import java.math.BigDecimal;

import br.com.resource.infra.model.EntityObject;

public class RelatorioHistChassiPecaVO extends EntityObject {
	
	//----[ Atributos de classe e inst�ncia ]---------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;    
    
    /** Armazena o campo peca do Relat�rio Consulta Hist�rico Chassi. */
    private String peca;
  
    /** Armazena o campo vlServico3 do Relat�rio Consulta Hist�rico Chassi. */
    private BigDecimal vlServico3;
    
    /** Armazena o campo codServico do Relat�rio Consulta Hist�rico Chassi. */
    private String codServico;
    
    /** Armazena o campo codSintoma do Relat�rio Consulta Hist�rico Chassi. */
    private String codSintoma;
    
    /** Armazena o campo codCondicao do Relat�rio Consulta Hist�rico Chassi. */
    private String codCondicao;
    
    /** Armazena o campo defeito do Relat�rio Consulta Hist�rico Chassi. */
    private String defeito;
    
    
    //	----[ M�todos Getter ]---------------------------------------------------

	/** M�todo getter para a propriedade codCondicao.
	 *
	 *  @return o valor atual de codCondicao.
	 */
	public String getCodCondicao() {
		return codCondicao;
	}

	/** M�todo getter para a propriedade codServico.
	 *
	 *  @return o valor atual de codServico.
	 */
	public String getCodServico() {
		return codServico;
	}

	/** M�todo getter para a propriedade codSintoma.
	 *
	 *  @return o valor atual de codSintoma.
	 */
	public String getCodSintoma() {
		return codSintoma;
	}

	/** M�todo getter para a propriedade defeito.
	 *
	 *  @return o valor atual de defeito.
	 */
	public String getDefeito() {
		return defeito;
	}

	/** M�todo getter para a propriedade peca.
	 *
	 *  @return o valor atual de peca.
	 */
	public String getPeca() {
		return peca;
	}

	/** M�todo getter para a propriedade vlServico3.
	 *
	 *  @return o valor atual de vlServico3.
	 */
	public BigDecimal getVlServico3() {
		return vlServico3;
	}

    //	----[ M�todos Setter ]---------------------------------------------------
	
	/** Obt�m o valur atual de codCondicao.
	 * 
	 *  @param codCondicao 
	 *    O novo valor para codCondicao.
	 */
	public void setCodCondicao(String codCondicao) {
		this.codCondicao = codCondicao;
	}

	/** Obt�m o valur atual de codServico.
	 * 
	 *  @param codServico 
	 *    O novo valor para codServico.
	 */
	public void setCodServico(String codServico) {
		this.codServico = codServico;
	}

	/** Obt�m o valur atual de codSintoma.
	 * 
	 *  @param codSintoma 
	 *    O novo valor para codSintoma.
	 */
	public void setCodSintoma(String codSintoma) {
		this.codSintoma = codSintoma;
	}

	/** Obt�m o valur atual de defeito.
	 * 
	 *  @param defeito 
	 *    O novo valor para defeito.
	 */
	public void setDefeito(String defeito) {
		this.defeito = defeito;
	}

	/** Obt�m o valur atual de peca.
	 * 
	 *  @param peca 
	 *    O novo valor para peca.
	 */
	public void setPeca(String peca) {
		this.peca = peca;
	}

	/** Obt�m o valur atual de vlServico3.
	 * 
	 *  @param vlServico3 
	 *    O novo valor para vlServico3.
	 */
	public void setVlServico3(BigDecimal vlServico3) {
		this.vlServico3 = vlServico3;
	}


}

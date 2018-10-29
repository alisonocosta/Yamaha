/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......GarantiaVO.java
 *   .: Cria��o.....01 de junho, 13:42
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...VO de Lote, GarantiaHeader, GarantiaLine e GrupoServico.
 */
package br.com.yamaha.sistemagarantia.view;

import java.util.Date;

import br.com.resource.infra.utils.NumberUtils;
import br.com.resource.infra.view.ViewObject;

/** View object juntando informa��es de Garantia, relacionando 
 *  as entidades "Lote", "GarantiaHeader", "GarantiaLine" e "GrupoServico".
 *  
 *  @author Edson Luiz Sumensari
 */
public class GarantiaVO extends ViewObject {

    //----[ Atributos de classe e inst�ncia ]-----------------------------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;    
    
    /** Identificador da entidade <code>Loter</code> utilizada
     *  na forma��o deste <i>View Object</i>.
     */
    private Long loteId;
    
    /** Identificador da entidade <code>GarantiaHeader</code> utilizada
     *  na forma��o deste <i>View Object</i>.
     */
    private Long garantiaId;
    
    /** N�mero do modelo(chassi com 12)  que deu origem a SG. */
    private String modelo;
    
    /** Valor do Servico prestado por terceiros. */
    private double valorServicoTerceiro;

    /** Identificador da entidade <code>GrupoServico</code> utilizada
     *  na forma��o deste <i>View Object</i>.
     */
    private double valorServico;
    
    /** Identificador da entidade <code>GarantiaLine</code> utilizada
     *  na forma��o deste <i>View Object</i>.
     */
    private double valorPeca;
    
    /** Identificador da entidade <code>GarantiaLine</code> utilizada
     *  na forma��o deste <i>View Object</i>.
     */
    private double valorRemessa;
    
    /** Data da abertura da ordem de servi�o. */
    private Date dataAberturaOS;
    
    /** Data de encerramento da ordem de servi�o. */
    private Date dataFechamentoOS;
    
    
    //  ----[ M�todos Getter ]---------------------------------------------------
    
	/** M�todo getter para a propriedade garantiaId
	 * 
	 *  @return Long
	 *
	 */
	public Long getGarantiaId() {
		return garantiaId;
	}

	/** M�todo getter para a propriedade valorPeca
	 * 
	 *  @return double
	 *
	 */
	public double getValorPeca() {
		return valorPeca;
	}

	/** M�todo getter para a propriedade valorRemessa
	 * 
	 *  @return double
	 *
	 */
	public double getValorRemessa() {
		return valorRemessa;
	}

	/** M�todo getter para a propriedade valorServico
	 * 
	 *  @return double
	 *
	 */
	public double getValorServico() {
		return valorServico;
	}
	
		
	/** M�todo getter para a propriedade loteId
	 *
	 *  @return Long de loteId
	 */
	public Long getLoteId() {
		return loteId;
	}
	
	/** M�todo getter para a propriedade dataAberturaOS
	 *
	 * @return dataAberturaOS do tipo Date
	 *
	 */	
	public Date getDataAberturaOS() {
		return dataAberturaOS;
	}
	
	/** M�todo getter para a propriedade dataFechamentoOS
	 *
	 * @return dataFechamentoOS do tipo Date
	 *
	 */	
	public Date getDataFechamentoOS() {
		return dataFechamentoOS;
	}
	
	//	----[ M�todos Setter ]---------------------------------------------------

	
	/** M�todo setter para a propriedade dataAberturaOS
	 *
	 * @param dataAberturaOS 
	 *       <code>dataAberturaOS<code> a ser designado para GarantiaVO.java
	 *
	 */
	public void setDataAberturaOS(Date dataAberturaOS) {
		this.dataAberturaOS = dataAberturaOS;
	}	

	/** M�todo setter para a propriedade dataFechamentoOS
	 *
	 * @param dataFechamentoOS 
	 *       <code>dataFechamentoOS<code> a ser designado para GarantiaVO.java
	 *
	 */
	public void setDataFechamentoOS(Date dataFechamentoOS) {
		this.dataFechamentoOS = dataFechamentoOS;
	}

	/** M�todo setter para a propriedade garantiaId
	 *
	 * @param garantiaId 
	 *           <code>Long</code> a ser designado para garantiaId.
	 * 
	 */
	public void setGarantiaId(Long garantiaId) {
		this.garantiaId = garantiaId;
	}

	/** M�todo setter para a propriedade valorPeca
	 *
	 * @param valorPeca 
	 *           <code>double</code> a ser designado para valorPeca.
	 * 
	 */
	public void setValorPeca(double valorPeca) {
		this.valorPeca = valorPeca;
	}

	/** M�todo setter para a propriedade valorRemessa
	 *
	 * @param valorRemessa 
	 *           <code>double</code> a ser designado para valorRemessa.
	 * 
	 */
	public void setValorRemessa(double valorRemessa) {
		this.valorRemessa = valorRemessa;
	}

	/** M�todo setter para a propriedade valorServico
	 *
	 * @param valorServico 
	 *           <code>double</code> a ser designado para valorServico.
	 * 
	 */
	public void setValorServico(double valorServico) {
		this.valorServico = valorServico;
	}
	
	/** M�todo setter para a propriedade loteId
	 *
	 * @param loteId Long
	 */
	public void setLoteId(Long loteId) {
		this.loteId = loteId;
	}
	
	//	----[ M�todos de Servi�o ]---------------------------------------------------
	
	/** Retorna o valorServico no formato para moeda
     * 
     * @return String
     *  	valorServico
     */
    public String getFormattedValorServico() {
    	
    	return  NumberUtils.formatNumberCurrency( this.valorServico );
    	
    }
    
    /** Retorna valorPeca formato para moeda
     * 
     * @return String
     *  	valorPeca
     */
    public String getFormattedValorPeca() {
    	
    	return  NumberUtils.formatNumberCurrency( this.valorPeca );
    	
    }
    
    /** Retorna valorPecano formato para moeda
     * 
     * @return String
     *  	valorRemessa
     */
    public String getFormattedValorRemessa() {
    	
    	return  NumberUtils.formatNumberCurrency( this.valorRemessa );
    	
    }

	/** M�todo getter para a propriedade modelo
	 *
	 *  @return String de modelo
	 */
	public String getModelo() {
		return modelo;
	}

	/** M�todo setter para a propriedade modelo
	 *
	 * @param modelo String
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/** M�todo getter para a propriedade valorServicoTerceiro
	 *
	 *  @return double de valorServicoTerceiro
	 */
	public double getValorServicoTerceiro() {
		return valorServicoTerceiro;
	}

	/** M�todo setter para a propriedade valorServicoTerceiro
	 *
	 * @param valorServicoTerceiro double
	 */
	public void setValorServicoTerceiro(double valorServicoTerceiro) {
		this.valorServicoTerceiro = valorServicoTerceiro;
	}
	
}
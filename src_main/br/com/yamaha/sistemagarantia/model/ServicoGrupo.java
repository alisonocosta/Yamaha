/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......ServicoGrupo.java
 *   .: Cria��o.....03 de maio, 09:17
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "ServicoGrupo".
 */
package br.com.yamaha.sistemagarantia.model;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

import br.com.resource.infra.model.EntityObject;
import br.com.resource.infra.utils.NumberUtils;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "ServicoGrupo" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class ServicoGrupo extends EntityObject {
	
	//----[ Atributos de classe e inst�ncia ]-----------------------------------
    
    /** Version ID padr�o para serializa��o */
    private static final long serialVersionUID = 1L; 
        
    /** Armazena o servicoId . */
    private Long servicoId;
    
    /** Armazena o valorServico. */
    private double valorServico;
    
    /** Armazena o valorServicoBasico. */
    private Double valorServicoBasico;
    
    /** Armazena o valorBonificacao. */
    private Double valorBonificacao;
    
    /** Servi�o relacionado */
    private Servico servico;
    
    /** Garantia relacionada */
    private GarantiaHeader garantia;
    
    /** Construtor */
    public ServicoGrupo(){
    	
    	super();
    }
      
    
    
    //	----[ M�todos Getter ]---------------------------------------------------
       



	/** M�todo getter para a propriedade valorServicoBasico
	 *
	 * @return valorServicoBasico do tipo Double
	 *
	 */
	
	public Double getValorServicoBasico() {
		return valorServicoBasico;
	}



	/** M�todo getter para a propriedade valorBonificacao
	 *
	 * @return valorBonificacao do tipo Double
	 *
	 */
	
	public Double getValorBonificacao() {
		return valorBonificacao;
	}



	/** M�todo setter para a propriedade valorServicoBasico
	 *
	 * @param valorServicoBasico 
	 *       <code>valorServicoBasico<code> a ser designado para ServicoGrupo.java
	 *
	 */
	public void setValorServicoBasico(Double valorServicoBasico) {
		if(valorServicoBasico != null) {
			Locale en = new Locale ("en", "US");   
	        NumberFormat nf  = new DecimalFormat ("######.00",new DecimalFormatSymbols (en)); 
	       
			this.valorServicoBasico = Double.valueOf(nf.format(valorServicoBasico.doubleValue()));
		} else
			this.valorServicoBasico = valorServicoBasico;
	}


	
	/** M�todo setter para a propriedade valorBonificacao
	 *
	 * @param valorBonificacao 
	 *       <code>valorBonificacao<code> a ser designado para ServicoGrupo.java
	 *
	 */
	public void setValorBonificacao(Double valorBonificacao) {
		if(valorBonificacao != null){
			Locale en = new Locale ("en", "US");   
			NumberFormat nf  = new DecimalFormat ("######.00",new DecimalFormatSymbols (en)); 

			this.valorBonificacao = Double.valueOf(nf.format(valorBonificacao.doubleValue()));
		} else
			this.valorBonificacao = valorBonificacao;
	}



	/** M�todo getter para a propriedade servicoId
	 * 
	 *  @return Long
	 *
	 */
	public Long getServicoId() {
		return servicoId;
	}

	/** M�todo getter para a propriedade valorServico
	 * 
	 *  @return double
	 *
	 */
	public double getValorServico() {
		return valorServico;
	}
	
	/** M�todo getter para a propriedade servico
	 * 
	 *  @return Servico
	 *
	 */
	public Servico getServico() {
		return servico;
	}
	
	/** M�todo getter para a propriedade garantia
	 * 
	 *  @return GarantiaHeader
	 *
	 */
	public GarantiaHeader getGarantia() {
		return garantia;
	}
	
	//	----[ M�todos Setter ]---------------------------------------------------

	/** M�todo setter para a propriedade servicoId
	 *
	 * @param servicoId 
	 *           <code>Long</code> a ser designado para servicoId.
	 * 
	 */
	public void setServicoId(Long servicoId) {
		this.servicoId = servicoId;
	}

	/** M�todo setter para a propriedade valorServico
	 *
	 * @param valorServico 
	 *           <code>double</code> a ser designado para valorServico.
	 * 
	 */
	public void setValorServico(double valorServico) {	
		
		Locale en = new Locale ("en", "US");   
        NumberFormat nf  = new DecimalFormat ("######.00",new DecimalFormatSymbols (en)); 
        
		this.valorServico = Double.parseDouble(nf.format(valorServico));
	}
	
	/** M�todo setter para a propriedade servico
	 *
	 * @param servico 
	 *           <code>Servico</code> a ser designado para servico.
	 * 
	 */
	public void setServico(Servico servico) {
		this.servico = servico;
	}
	
	/** M�todo setter para a propriedade garantia
	 *
	 * @param garantia 
	 *           <code>GarantiaHeader</code> a ser designado para garantia.
	 * 
	 */
	public void setGarantia(GarantiaHeader garantia) {
		this.garantia = garantia;
	}
	
	
	//	----[ M�todos de Servi�o ]---------------------------------------------------
	    
    /** Retorna o Valor valorServico formato para moeda
     * 
     * @return String
     *  	 valorServico
     */
    public String geFormattedtValorServico() {
    	
    	return  NumberUtils.formatCurrency( this.valorServico );
    	
    }
    
}

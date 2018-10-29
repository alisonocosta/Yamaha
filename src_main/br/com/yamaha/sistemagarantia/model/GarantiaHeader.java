/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......GarantiaHeader.java
 *   .: Cria��o.....01 de junho, 12:04
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "GarantiaHeader".
 */
package br.com.yamaha.sistemagarantia.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import br.com.resource.infra.model.EntityObject;
import br.com.resource.infra.utils.DateUtils;
import br.com.resource.infra.utils.NumberUtils;
import br.com.yamaha.sistemagarantia.model.id.GarantiaLineId;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "GarantiaHeader" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class GarantiaHeader extends EntityObject {

    //----[ Atributos de classe e inst�ncia ]-----------------------------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L; 
    
    public static String CLASSIFICACAO_TEC_SIM = "S";
    
    public static String CLASSIFICACAO_TEC_NAO = "N";
    
    /** Identificador do codigo de garantia do legado*/
    private String garantiaCode;
    
    /** Identificador �nico da organiza��o. */
    private Long organizationId;
    
    /** N�mero do modelo(chassi com 12)  que deu origem a SG. */
    private String modelo;
    
    /** N�mero da ordem de servi�o da concession�ria. */
    private String numeroOS;
    
    /** Quilometragem do produto na data da ordem de servi�o. */
    private Long quilometragem;
    
    /** Dias de uso do produto. */
    private Long diasUso;
    
    /** Horas de uso do produto. */
    private Long horasUso;
    
    /** Data da abertura da ordem de servi�o. */
    private Date dataAberturaOS;
    
    /** Data de encerramento da ordem de servi�o. */
    private Date dataFechamentoOS;
    
    /** alertGarantiaId. */
    private Long alertGarantiaId;
    
    private String tipoGasolinaId;
    
    private Long tipoUsoId;
    
    /** Local Uso - �gua Doce. */
    private String localUsoDoce;
    
    /** Local uso - �gua Salgada. */
    private String localUsoSalg;
    
    /** Cidade de Uso. */
    private String cidadeUso;
    
    /** Tipo de Uso. */
    private String tipoUso;
    
    /** marca do casco. */
    private String marcaCasco;
    
    /** Modelo do casco. */
    private String modeloCasco;
    
    /** Marca h�lice. */
    private String marcaHelice;
    
    /** Material H�lice. */
    private String materHelice;
    
    /** Passo h�lice. */
    private Long passoHelice;
    
    /** Rota��o M�xima. */
    private Long rotacaoMaxima;
    
    /** Entidade de defeito. */
    private Defeito defeito;
    
    /** Identificador �nico da recusa. */
    private Long recusaId;
    
    /** Nome do respons�vel pelo preenchimento da Garantia. */
    private String preenchidoPor;
    
    /** Entidade Condi��o. */
    private Condicao condicao;
    
    /** Identificador �nico do analista que aprova ou reprova a SG ou Cupom. */
    private Long analistaId;
    
    /** lote de revis�es ou solicita��es de garantia. */
    private Lote lote;
    
    /** status da SG. */
    private StatusGarantia statusGarantia;
    
    /** Entidade Sintoma. */
    private Sintoma sintoma;
    
    /** Entidade Recall. */
    private Recall recall;
    
    /** Entidade campaign. */
    private Campaign campaign;
    
    /** Entidade infoMercado. */
    private InfoMercado infoMercado;
    
    /** Entidade da autoriza��o especial para SG. */
    private AutorizacaoEspecialSG autorizacaoEspecialSG;
    
    /** Entidade que representa a causa do problema*/
    private CausaProblemaGarantia causaProblemaGarantia;
    
    /** Entidade que representa a condi��o do problema*/
    private CondicaoProblemaGarantia condicaoProblemaGarantia;
    
    /** Entidade que representa o diagn�stico do problema*/
    private DiagnosticoProblemaGarantia diagnosticoProblemaGarantia;
    
    /**Entidade que representa a solu��o do problema*/
    private SolucaoProblemaGarantia solucaoProblemaGarantia;
    
    /** Identificador �nico do grupo notas. */
    private Long notaGrupoId;
    
    /** Valor do Servico prestado por terceiros. */
    private double valorServicoTerceiro;
    
    /** Justificativa por usar servicos de terceiros. */
    private String justServicoTerceiro;
    
    /** Lista de grupos de servi�os relacionados */
    private List grupos;
    
    /** Lista de defeitos relacionados */
    private List defeitos;
    
    /** Lista de parareceres t�cnicos relacionados */
    private List pareceres;
    
    /** Lista de pe�as */
    private List listGarantiaLine;   
    
    /** Identifica se existiu classifica��o t�cnica para a SG. */
    private String flClassificacaoTecnica;
    
    /** Tipo Problema da  classifica��o t�cnica para a SG. */
    private String tipoProblema;
    
    /** Tipo a��o da  classifica��o t�cnica para a SG. */
    private String classificaAcao;
    
    public GarantiaHeader() {
    	
    	super();
    	
    	this.campaign = null;
    	this.infoMercado = null;
    	this.grupos   = new ArrayList();
    	this.defeitos = new ArrayList();    	
    	
    }
    
    //  ----[ M�todos Getter ]---------------------------------------------------
    
	/** M�todo getter para a propriedade alertGarantiaId
	 * 
	 *  @return Long
	 *
	 */
	public Long getAlertGarantiaId() {
		return alertGarantiaId;
	}

	/** M�todo getter para a propriedade analistaId
	 * 
	 *  @return Long
	 *
	 */
	public Long getAnalistaId() {
		return analistaId;
	}

	/** M�todo getter para a propriedade autorizacaoEspecialSG
	 * 
	 *  @return AutorizacaoEspecialSG
	 *
	 */
	public AutorizacaoEspecialSG getAutorizacaoEspecialSG() {
		return autorizacaoEspecialSG;
	}

	/** M�todo getter para a propriedade condicao
	 * 
	 *  @return Long
	 *
	 */
	public Condicao getCondicao() {
		return condicao;
	}

	/** M�todo getter para a propriedade dataAberturaOS
	 * 
	 *  @return Date
	 *
	 */
	public Date getDataAberturaOS() {
		return dataAberturaOS;
	}
	
	/** M�todo getter para "dataAberturaOS" formatada.
     *  @return
     *      <code>String</code>. O valor atual de dataAberturaOS,
     *      com uma m�scara aplicada.
     */
    public String getMaskedDataAberturaOS() {
        return DateUtils.applyMask(this.dataAberturaOS);
    }    

	/** M�todo getter para a propriedade dataFechamentoOS
	 * 
	 *  @return Date
	 *
	 */
	public Date getDataFechamentoOS() {
		return dataFechamentoOS;
	}
	
	/** M�todo getter para "dataFechamentoOS" formatada.
     *  @return
     *      <code>String</code>. O valor atual de dataFechamentoOS,
     *      com uma m�scara aplicada.
     */
    public String getMaskedDataFechamentoOS() {
        return DateUtils.applyMask(this.dataFechamentoOS);
    }    

	/** M�todo getter para a propriedade Defeito
	 * 
	 *  @return Defeito
	 *
	 */
	public Defeito getDefeito() {
		return defeito;
	}

	/** M�todo getter para a propriedade diasUso
	 * 
	 *  @return Long
	 *
	 */
	public Long getDiasUso() {
		return diasUso;
	}

	/** M�todo getter para a propriedade garantiaCode
	 * 
	 *  @return String
	 *
	 */
	public String getGarantiaCode() {
		return garantiaCode;
	}

	/** M�todo getter para a propriedade horasUso
	 * 
	 *  @return Long
	 *
	 */
	public Long getHorasUso() {
		return horasUso;
	}

	/** M�todo getter para a propriedade justServicoTerceiro
	 * 
	 *  @return String
	 *
	 */
	public String getJustServicoTerceiro() {
		return justServicoTerceiro;
	}

	/** M�todo getter para a propriedade modelo
	 * 
	 *  @return String
	 *
	 */
	public String getModelo() {
		return modelo;
	}

	/** M�todo getter para a propriedade notaGrupoId
	 * 
	 *  @return Long
	 *
	 */
	public Long getNotaGrupoId() {
		return notaGrupoId;
	}

	/** M�todo getter para a propriedade numeroOS
	 * 
	 *  @return String
	 *
	 */
	public String getNumeroOS() {
		return numeroOS;
	}

	/** M�todo getter para a propriedade organizationId
	 * 
	 *  @return Long
	 *
	 */
	public Long getOrganizationId() {
		return organizationId;
	}

	/** M�todo getter para a propriedade quilometragem
	 * 
	 *  @return Long
	 *
	 */
	public Long getQuilometragem() {
		return quilometragem;
	}

	/** M�todo getter para a propriedade recall
	 * 
	 *  @return Long
	 *
	 */
	public Recall getRecall() {
		return recall;
	}

	/** M�todo getter para a propriedade recusaId
	 * 
	 *  @return Long
	 *
	 */
	public Long getRecusaId() {
		return recusaId;
	}

	/** M�todo getter para a propriedade sintomaId
	 * 
	 *  @return Long
	 *
	 */
	public Sintoma getSintoma() {
		return sintoma;
	}

	/** M�todo getter para a propriedade valorServicoTerceiro
	 * 
	 *  @return double
	 *
	 */
	public double getValorServicoTerceiro() {
		return valorServicoTerceiro;
	}
	
	/** M�todo getter para a propriedade valorServicoTerceiro, formatado para pt_BR
	 * 
	 *  @return String
	 *
	 */
	public String getFormatedValorServicoTerceiro() {
		return NumberUtils.formatNumberCurrency(valorServicoTerceiro);
	}

	/** M�todo getter para a propriedade statusGarantia
	 * 
	 *  @return StatusGarantia
	 *
	 */
	public StatusGarantia getStatusGarantia() {
		return statusGarantia;
	}	

	/** M�todo getter para a propriedade tipoProblema
	 *
	 * @return tipoProblema do tipo String
	 *
	 */
	
	public String getTipoProblema() {
		return tipoProblema;
	}

	/** M�todo setter para a propriedade tipoProblema
	 *
	 * @param tipoProblema 
	 *       <code>tipoProblema<code> a ser designado para GarantiaHeader.java
	 *
	 */
	public void setTipoProblema(String tipoProblema) {
		this.tipoProblema = tipoProblema;
	}

	/** M�todo getter para a propriedade classificaAcao
	 *
	 * @return classificaAcao do tipo String
	 *
	 */
	
	public String getClassificaAcao() {
		return classificaAcao;
	}

	/** M�todo setter para a propriedade classifcaAcao
	 *
	 * @param classifcaAcao 
	 *       <code>classificaAcao<code> a ser designado para GarantiaHeader.java
	 *
	 */
	public void setClassificaAcao(String classificaAcao) {
		this.classificaAcao = classificaAcao;
	}

	/** M�todo getter para a propriedade lote
	 * 
	 *  @return Lote
	 *
	 */
	public Lote getLote() {
		return lote;
	}
	
	/** M�todo getter para a propriedade grupos
	 * 
	 *  @return List
	 *
	 */
	public List getGrupos() {
		return grupos;
	}
	
	/** M�todo getter para a propriedade listGarantiaLine
	 * 	Para eliminar duplicidade de pe�as para a Garantia
	 *  Isto acontece nos itens complemetares
	 *  
	 *  @return List
	 *
	 */
	public List getListGarantiaLineNotDouble() {
		List sgLine = new ArrayList();
		
		Iterator it = listGarantiaLine.iterator();
		Iterator pc = null;
		GarantiaLine garantiaLine = null;
		GarantiaLine glPc = null;
		boolean hasPc = false; 
		while ( it.hasNext() ) {
			
			garantiaLine = (GarantiaLine) it.next();
			
			if ( garantiaLine.getDataTermino() == null ) {	
					
				if(!sgLine.isEmpty()){
					hasPc = false; 
					pc= sgLine.iterator();
					while(pc.hasNext()){
						glPc = (GarantiaLine) pc.next();						
						if(garantiaLine.getCompositeEntityId().equals(glPc.getCompositeEntityId())){
							hasPc = true; 
						}						
					}
					if(!hasPc)
						sgLine.add(garantiaLine);
				} else
					sgLine.add(garantiaLine);
			}
				
		}
		
		return sgLine;
	}
	
	/** M�todo getter para retorna uma pe�a de acordo compositeId
	 *  
	 *  @return GarantiaLine
	 *
	 */
	public GarantiaLine getGarantiaLine(GarantiaLineId garantiaLineId) {
		
		GarantiaLine sgLine = null;
		
		if ( garantiaLineId != null ) {
			
			Iterator it = listGarantiaLine.iterator();
			while ( it.hasNext() ) {
				
				GarantiaLine garantiaLine = (GarantiaLine) it.next();
				//System.out.println("-->garantiaLine:" + garantiaLine.getCompositeEntityId().getGarantiaId() + " -- lineId:" + garantiaLine.getCompositeEntityId().getLineId());
				//System.out.println("-->GarantiaLineId:" + garantiaLineId.getGarantiaId() + " -- lineId:" + garantiaLineId.getLineId());
				if ( garantiaLine.getCompositeEntityId().equals(garantiaLineId) ) {	
					sgLine = garantiaLine;
				}					
			}
		}
		
		//System.out.println("getGarantiaLine retornando:" + (sgLine != null));
		return sgLine;
	}
	
	/** M�todo getter para a propriedade listGarantiaLine
	 *  
	 *  @return List
	 *
	 */
	public List getListGarantiaLine() {
		
		return listGarantiaLine;
		
	}
	
	/** Retorna um ServicoGrupo de acordo com o c�digo informado
	 * 
	 * @param String servicoCode
	 * 
	 * @return ServicoGrupo ou null
	 * 
	 */
	public ServicoGrupo getServico(String servicoCode) {
		
		//Servico      servico      = null;
		ServicoGrupo servicoGrupo = null;		
		
		Iterator it = this.getGrupos().iterator();
		
		while ( it.hasNext() ) {
			
			servicoGrupo = new ServicoGrupo();
			servicoGrupo = (ServicoGrupo) it.next();
			
			//System.out.println("Servi�o: " + servicoGrupo.getServico().getServicoCode() + " Param:" + servicoCode );
			
			if ( servicoCode.equals(servicoGrupo.getServico().getServicoCode()) && servicoGrupo.getDataTermino() == null ) {
												
				break;
				
			}
			
		}		
		
		return servicoGrupo;
		
	}
	
	/** Retorna o valor total dos sevi�os
	 *  Percorre a lista de Grupos de Servi�os e soma o valor do servi�o
	 * 
	 * @return double
	 * 
	 */
	public double getValorTotalServicos() {
		
		double valorTotalServicos = 0L;
		
		Iterator it = this.grupos.iterator();
		//int i = 0;
		
		while ( it.hasNext() ) {
			
			ServicoGrupo servicoGrupo = (ServicoGrupo) it.next();
			
			//System.out.println("Garantia:" + this.getEntityId() + " - Valor de Servi�o "  + i++ +":" + servicoGrupo.getValorServico());
			
			//System.out.println("Final date:" + servicoGrupo.getDataTermino());
			
			if (servicoGrupo.getDataTermino() == null )			
				valorTotalServicos += servicoGrupo.getValorServico();			
			
		}
		
		return valorTotalServicos;
		
	}
	
	/** Retorna o valor total dos sevi�os, formatado pt_BR
	 * 
	 * @return String
	 * 
	 */
	public String getFormatedValorTotalServicos() {
		
		return NumberUtils.formatNumberCurrency(this.getValorTotalServicos());
		
	}
		
	
	
	/** Retorna o valor total de pe�as da garantia
	 * 
	 * @return double 
	 */
	public double getValorTotalPecas() {
		
		double valorTotalPecas = 0L;
		
		Iterator it = this.listGarantiaLine.iterator();
		
		while ( it.hasNext() ) {
			
			GarantiaLine garantiaLine = (GarantiaLine) it.next();
			
			if ( garantiaLine.getDataTermino() == null )
				valorTotalPecas += (garantiaLine.getValorPecaGarantia() * garantiaLine.getQuantidade().doubleValue());
			
		}
		
		return valorTotalPecas;
		
	}
	
	/** M�todo getter para a propriedade infoMercado
	 *
	 *  @return InfoMercado de infoMercado
	 */
	public InfoMercado getInfoMercado() {
		return infoMercado;
	}
	
	/**
	 * M�todo getter para a propriedade campaign
	 * @return  Campaign de campaign
	 */
	public Campaign getCampaign() {
		return campaign;
	}

	/** M�todo getter para a propriedade causaProblemaGarantia
	 *
	 * @return causaProblemaGarantia do tipo CausaProblemaGarantia
	 *
	 */
	
	public CausaProblemaGarantia getCausaProblemaGarantia() {
		return causaProblemaGarantia;
	}

	/** M�todo getter para a propriedade condicaoProblemaGarantia
	 *
	 * @return condicaoProblemaGarantia do tipo CondicaoProblemaGarantia
	 *
	 */
	
	public CondicaoProblemaGarantia getCondicaoProblemaGarantia() {
		return condicaoProblemaGarantia;
	}

	/** M�todo getter para a propriedade diagnosticoProblemaGarantia
	 *
	 * @return diagnosticoProblemaGarantia do tipo DiagnosticoProblemaGarantia
	 *
	 */
	
	public DiagnosticoProblemaGarantia getDiagnosticoProblemaGarantia() {
		return diagnosticoProblemaGarantia;
	}

	/** M�todo getter para a propriedade solucaoProblemaGarantia
	 *
	 * @return solucaoProblemaGarantia do tipo SolucaoProblemaGarantia
	 *
	 */
	
	public SolucaoProblemaGarantia getSolucaoProblemaGarantia() {
		return solucaoProblemaGarantia;
	}

	/** Retorna o valor total de pe�as da garantia
	 * 
	 * @return double 
	 */
	public double getValorTotalPecasCobrar() {
		
		double valorTotalPecas = 0;
		
		//System.out.println("Garantialines:" + this.listGarantiaLine.size());
		
		Iterator it = this.listGarantiaLine.iterator();
		
		while ( it.hasNext() ) {
			
			GarantiaLine garantiaLine = (GarantiaLine) it.next();
			
			if ( new Long(91).equals(garantiaLine.getItem().getOrganizationId()) ) {
				//System.out.println(" Garantia:" + garantiaLine.getCompositeEntityId().getLineId() +  " - Garantialine Valores:" + garantiaLine.getCobraPeca() + " - " + garantiaLine.getValorPecaGarantia() + " - " + garantiaLine.getQuantidade().doubleValue());
				if ( GarantiaLine.COBRAR_PECA.equalsIgnoreCase(garantiaLine.getCobraPeca()) && garantiaLine.getDataTermino() == null ) {
				
					valorTotalPecas += (garantiaLine.getValorPecaGarantia() * garantiaLine.getQuantidade().doubleValue());
					//System.out.println("Garantialine Valor somado:" + (garantiaLine.getValorPecaGarantia() * garantiaLine.getQuantidade().doubleValue()));
				}
			}			
		}
		//System.out.println("Garantialine Valor total:" + valorTotalPecas);
		return valorTotalPecas;
		
	}
	
	
	//	----[ M�todos Setter ]---------------------------------------------------
	
	/** M�todo setter para a propriedade pareceres
	 *
	 * @param pareceres 
	 *           <code>List</code> a ser designado para pareceres.
	 * 
	 */
	public void setPareceres(List pareceres) {
		this.pareceres = pareceres;
	}

	/** M�todo setter para a propriedade listGarantiaLine
	 *
	 * @param listGarantiaLine 
	 *           <code>List</code> a ser designado para listGarantiaLine.
	 * 
	 */
	public void setListGarantiaLine(List listGarantiaLine) {
		this.listGarantiaLine = listGarantiaLine;
	}
	
	/**
	 * M�todo setter para a propriedade campaign
	 * @param Campaign campaign
	 */
	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}

	/** M�todo setter para a propriedade infoMercado
	 *
	 * @param infoMercado InfoMercado
	 */
	public void setInfoMercado(InfoMercado infoMercado) {
		this.infoMercado = infoMercado;
	}
	
	
	/** M�todo setter para a propriedade causaProblemaGarantia
	 *
	 * @param causaProblemaGarantia 
	 *       <code>causaProblemaGarantia<code> a ser designado para GarantiaHeader.java
	 *
	 */
	public void setCausaProblemaGarantia(CausaProblemaGarantia causaProblemaGarantia) {
		this.causaProblemaGarantia = causaProblemaGarantia;
	}

	/** M�todo setter para a propriedade condicaoProblemaGarantia
	 *
	 * @param condicaoProblemaGarantia 
	 *       <code>condicaoProblemaGarantia<code> a ser designado para condicaoProblemaGarantia
	 *
	 */
	public void setCondicaoProblemaGarantia(
			CondicaoProblemaGarantia condicaoProblemaGarantia) {
		this.condicaoProblemaGarantia = condicaoProblemaGarantia;
	}

	/** M�todo setter para a propriedade diagnosticoProblemaGarantia
	 *
	 * @param diagnosticoProblemaGarantia 
	 *       <code>diagnosticoProblemaGarantia<code> a ser designado para GarantiaHeader.java
	 *
	 */
	public void setDiagnosticoProblemaGarantia(
			DiagnosticoProblemaGarantia diagnosticoProblemaGarantia) {
		this.diagnosticoProblemaGarantia = diagnosticoProblemaGarantia;
	}

	/** M�todo setter para a propriedade solucaoProblemaGarantia
	 *
	 * @param solucaoProblemaGarantia 
	 *       <code>solucaoProblemaGarantia<code> a ser designado para GarantiaHeader.java
	 *
	 */
	public void setSolucaoProblemaGarantia(
			SolucaoProblemaGarantia solucaoProblemaGarantia) {
		this.solucaoProblemaGarantia = solucaoProblemaGarantia;
	}
	
	/** M�todo setter para a propriedade alertGarantiaId
	 *
	 * @param alertGarantiaId 
	 *           <code>Long</code> a ser designado para alertGarantiaId.
	 * 
	 */
	public void setAlertGarantiaId(Long alertGarantiaId) {
		this.alertGarantiaId = alertGarantiaId;
	}

	/** M�todo setter para a propriedade analistaId
	 *
	 * @param analistaId 
	 *           <code>Long</code> a ser designado para analistaId.
	 * 
	 */
	public void setAnalistaId(Long analistaId) {
		this.analistaId = analistaId;
	}

	/** M�todo setter para a propriedade autorizacaoEspecialSG
	 *
	 * @param AutorizacaoEspecialSG 
	 *           <code>AutorizacaoEspecialSG</code> a ser designado para autorizacaoEspecialSG.
	 * 
	 */
	public void setAutorizacaoEspecialSG(AutorizacaoEspecialSG autorizacaoEspecialSG) {
		this.autorizacaoEspecialSG = autorizacaoEspecialSG;
	}

	/** M�todo setter para a propriedade condicaoId
	 *
	 * @param condicao 
	 *           <code>Condicao</code> a ser designado para condicao.
	 * 
	 */
	public void setCondicao(Condicao condicao) {
		this.condicao = condicao;
	}

	/** M�todo setter para a propriedade dataAberturaOS
	 *
	 * @param dataAberturaOS 
	 *           <code>Date</code> a ser designado para dataAberturaOS.
	 * 
	 */
	public void setDataAberturaOS(Date dataAberturaOS) {
		this.dataAberturaOS = dataAberturaOS;
	}

	/** M�todo setter para a propriedade dataFechamentoOS
	 *
	 * @param dataFechamentoOS 
	 *           <code>Date</code> a ser designado para dataFechamentoOS.
	 * 
	 */
	public void setDataFechamentoOS(Date dataFechamentoOS) {
		this.dataFechamentoOS = dataFechamentoOS;
	}

	/** M�todo setter para a propriedade Defeito
	 *
	 * @param defeitoId 
	 *           <code>Defeito</code> a ser designado para Defeito.
	 * 
	 */
	public void setDefeito(Defeito defeito) {
		this.defeito = defeito;
	}

	/** M�todo setter para a propriedade diasUso
	 *
	 * @param diasUso 
	 *           <code>Long</code> a ser designado para diasUso.
	 * 
	 */
	public void setDiasUso(Long diasUso) {
		this.diasUso = diasUso;
	}

	/** M�todo setter para a propriedade garantiaCode
	 *
	 * @param garantiaCode 
	 *           <code>String</code> a ser designado para garantiaCode.
	 * 
	 */
	public void setGarantiaCode(String garantiaCode) {
		this.garantiaCode = garantiaCode;
	}

	/** M�todo setter para a propriedade horasUso
	 *
	 * @param horasUso 
	 *           <code>Long</code> a ser designado para horasUso.
	 * 
	 */
	public void setHorasUso(Long horasUso) {
		this.horasUso = horasUso;
	}

	/** M�todo setter para a propriedade justServicoTerceiro
	 *
	 * @param justServicoTerceiro 
	 *           <code>String</code> a ser designado para justServicoTerceiro.
	 * 
	 */
	public void setJustServicoTerceiro(String justServicoTerceiro) {
		this.justServicoTerceiro = justServicoTerceiro;
	}

	/** M�todo setter para a propriedade modelo
	 *
	 * @param modelo 
	 *           <code>String</code> a ser designado para modelo.
	 * 
	 */
	public void setModelo(String modelo) {
		if ( modelo != null )
			this.modelo = modelo.toUpperCase();
	}

	/** M�todo setter para a propriedade notaGrupoId
	 *
	 * @param notaGrupoId 
	 *           <code>Long</code> a ser designado para notaGrupoId.
	 * 
	 */
	public void setNotaGrupoId(Long notaGrupoId) {
		this.notaGrupoId = notaGrupoId;
	}

	/** M�todo setter para a propriedade numeroOS
	 *
	 * @param numeroOS 
	 *           <code>String</code> a ser designado para numeroOS.
	 * 
	 */
	public void setNumeroOS(String numeroOS) {
		this.numeroOS = numeroOS;
	}

	/** M�todo setter para a propriedade organizationId
	 *
	 * @param organizationId 
	 *           <code>Long</code> a ser designado para organizationId.
	 * 
	 */
	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	/** M�todo setter para a propriedade quilometragem
	 *
	 * @param quilometragem 
	 *           <code>Long</code> a ser designado para quilometragem.
	 * 
	 */
	public void setQuilometragem(Long quilometragem) {
		this.quilometragem = quilometragem;
	}

	/** M�todo setter para a propriedade recall
	 *
	 * @param recall 
	 *           <code>Long</code> a ser designado para recall.
	 * 
	 */
	public void setRecall(Recall recall) {
		this.recall = recall;
	}

	/** M�todo setter para a propriedade recusaId
	 *
	 * @param recusaId 
	 *           <code>Long</code> a ser designado para recusaId.
	 * 
	 */
	public void setRecusaId(Long recusaId) {
		this.recusaId = recusaId;
	}

	/** M�todo setter para a propriedade sintomaId
	 *
	 * @param sintomaId 
	 *           <code>Long</code> a ser designado para sintomaId.
	 * 
	 */
	public void setSintoma(Sintoma sintoma) {
		this.sintoma = sintoma;
	}
	
	/** M�todo setter para a propriedade preenchidoPor
	 *
	 * @param preenchidoPor 
	 *           <code>String</code> a ser designado para preenchidoPor.
	 * 
	 */
	public void setPreenchidoPor(String preenchidoPor) {
		this.preenchidoPor = preenchidoPor;
	}

	/** M�todo setter para a propriedade valorServicoTerceiro
	 *
	 * @param valorServicoTerceiro 
	 *           <code>double</code> a ser designado para valorServicoTerceiro.
	 * 
	 */
	public void setValorServicoTerceiro(double valorServicoTerceiro) {
		this.valorServicoTerceiro = valorServicoTerceiro;
	}

	/** M�todo setter para a propriedade statusGarantia
	 *
	 * @param statusGarantia 
	 *           <code>StatusGarantia</code> a ser designado para statusGarantia.
	 * 
	 */
	public void setStatusGarantia(StatusGarantia statusGarantia) {
		this.statusGarantia = statusGarantia;
	}
	

	/**M�todo setter para a propriedade Lote
	 * 
	 * @param lote
	 */
	public void setLote(Lote lote) {
		this.lote = lote;
	}
	
	/** M�todo setter para a propriedade grupos
	 *
	 * @param grupos 
	 *           <code>List</code> a ser designado para grupos.
	 * 
	 */
	public void setGrupos(List grupos) {
		this.grupos = grupos;
	}
	
	/** M�todo setter para a propriedade defeitos
	 *
	 * @param defeitos 
	 *           <code>List</code> a ser designado para defeitos.
	 * 
	 */
	public void setDefeitos(List defeitos) {
		this.defeitos = defeitos;
	}
	
	/** Adiciona um ServicoGrupo relacionado a garantia
	 * 
	 * @param servicoGrupo
	 */
	public void addServicoGrupo(ServicoGrupo servicoGrupo) {
		
		if ( servicoGrupo != null )	{	
			this.grupos.add(servicoGrupo);
			servicoGrupo.setGarantia(this);
		}
		
	}

	/** M�todo getter para a propriedade preenchidoPor
	 *
	 * @return preenchidoPor do tipo String
	 *
	 */
	
	public String getPreenchidoPor() {
		return preenchidoPor;
	}
	
	/** M�todo getter para a propriedade defeitos
	 * 
	 *  @return List
	 *
	 */
	public List getDefeitos() {
		return defeitos;
	}	
	
	/** Adiciona uma Descri��o de Servi�o a Garantia
	 * 
	 * @param descricaoDefeito
	 */
	public void addDescricaoDefeito( DescricaoDefeito descricaoDefeito ) {
		
		if ( descricaoDefeito != null ) {
			this.defeitos.add(descricaoDefeito);
			descricaoDefeito.setGarantia(this);
		}
		
	}

	/** M�todo getter para a propriedade pareceres
	 * 
	 *  @return List
	 *
	 */
	public List getPareceres() {
		return pareceres;
	}
	
	
	/** M�todo getter para a propriedade tipoGasolinaId
	 *
	 * @return tipoGasolinaId do tipo String
	 *
	 */
	
	public String getTipoGasolinaId() {
		return tipoGasolinaId;
	}

	/** M�todo setter para a propriedade tipoGasolinaId
	 *
	 * @param tipoGasolinaId 
	 *       <code>tipoGasolinaId<code> a ser designado para GarantiaHeader.java
	 *
	 */
	public void setTipoGasolinaId(String tipoGasolinaId) {
		this.tipoGasolinaId = tipoGasolinaId;
	}

	/** M�todo getter para a propriedade tipoUsoId
	 *
	 * @return tipoUsoId do tipo Long
	 *
	 */
	
	public Long getTipoUsoId() {
		return tipoUsoId;
	}

	/** M�todo setter para a propriedade tipoUsoId
	 *
	 * @param tipoUsoId 
	 *       <code>tipoUsoId<code> a ser designado para GarantiaHeader.java
	 *
	 */
	public void setTipoUsoId(Long tipoUsoId) {
		this.tipoUsoId = tipoUsoId;
	}

	/** M�todo getter para a propriedade localUsoDoce
	 *
	 * @return localUsoDoce do tipo String
	 *
	 */
	
	public String getLocalUsoDoce() {
		return localUsoDoce;
	}

	/** M�todo setter para a propriedade localUsoDoce
	 *
	 * @param localUsoDoce 
	 *       <code>localUsoDoce<code> a ser designado para GarantiaHeader.java
	 *
	 */
	public void setLocalUsoDoce(String localUsoDoce) {
		this.localUsoDoce = localUsoDoce;
	}

	/** M�todo getter para a propriedade localUsoSalg
	 *
	 * @return localUsoSalg do tipo String
	 *
	 */
	
	public String getLocalUsoSalg() {
		return localUsoSalg;
	}

	/** M�todo setter para a propriedade localUsoSalg
	 *
	 * @param localUsoSalg 
	 *       <code>localUsoSalg<code> a ser designado para GarantiaHeader.java
	 *
	 */
	public void setLocalUsoSalg(String localUsoSalg) {
		this.localUsoSalg = localUsoSalg;
	}

	/** M�todo getter para a propriedade cidadeUso
	 *
	 * @return cidadeUso do tipo String
	 *
	 */
	
	public String getCidadeUso() {
		return cidadeUso;
	}

	/** M�todo setter para a propriedade cidadeUso
	 *
	 * @param cidadeUso 
	 *       <code>cidadeUso<code> a ser designado para GarantiaHeader.java
	 *
	 */
	public void setCidadeUso(String cidadeUso) {
		this.cidadeUso = cidadeUso;
	}

	/** M�todo getter para a propriedade tipoUso
	 *
	 * @return tipoUso do tipo String
	 *
	 */
	
	public String getTipoUso() {
		return tipoUso;
	}

	/** M�todo setter para a propriedade tipoUso
	 *
	 * @param tipoUso 
	 *       <code>tipoUso<code> a ser designado para GarantiaHeader.java
	 *
	 */
	public void setTipoUso(String tipoUso) {
		this.tipoUso = tipoUso;
	}

	/** M�todo getter para a propriedade marcaCasco
	 *
	 * @return marcaCasco do tipo String
	 *
	 */
	
	public String getMarcaCasco() {
		return marcaCasco;
	}

	/** M�todo setter para a propriedade marcaCasco
	 *
	 * @param marcaCasco 
	 *       <code>marcaCasco<code> a ser designado para GarantiaHeader.java
	 *
	 */
	public void setMarcaCasco(String marcaCasco) {
		this.marcaCasco = marcaCasco;
	}

	/** M�todo getter para a propriedade modeloCasco
	 *
	 * @return modeloCasco do tipo String
	 *
	 */
	
	public String getModeloCasco() {
		return modeloCasco;
	}

	/** M�todo setter para a propriedade modeloCasco
	 *
	 * @param modeloCasco 
	 *       <code>modeloCasco<code> a ser designado para GarantiaHeader.java
	 *
	 */
	public void setModeloCasco(String modeloCasco) {
		this.modeloCasco = modeloCasco;
	}

	/** M�todo getter para a propriedade marcaHelice
	 *
	 * @return marcaHelice do tipo String
	 *
	 */
	
	public String getMarcaHelice() {
		return marcaHelice;
	}

	/** M�todo setter para a propriedade marcaHelice
	 *
	 * @param marcaHelice 
	 *       <code>marcaHelice<code> a ser designado para GarantiaHeader.java
	 *
	 */
	public void setMarcaHelice(String marcaHelice) {
		this.marcaHelice = marcaHelice;
	}

	/** M�todo getter para a propriedade materHelice
	 *
	 * @return materHelice do tipo String
	 *
	 */
	
	public String getMaterHelice() {
		return materHelice;
	}

	/** M�todo setter para a propriedade materHelice
	 *
	 * @param materHelice 
	 *       <code>materHelice<code> a ser designado para GarantiaHeader.java
	 *
	 */
	public void setMaterHelice(String materHelice) {
		this.materHelice = materHelice;
	}

	/** M�todo getter para a propriedade passoHelice
	 *
	 * @return passoHelice do tipo Long
	 *
	 */
	
	public Long getPassoHelice() {
		return passoHelice;
	}

	/** M�todo setter para a propriedade passoHelice
	 *
	 * @param passoHelice 
	 *       <code>passoHelice<code> a ser designado para GarantiaHeader.java
	 *
	 */
	public void setPassoHelice(Long passoHelice) {
		this.passoHelice = passoHelice;
	}

	/** M�todo getter para a propriedade rotacaoMaxima
	 *
	 * @return rotacaoMaxima do tipo Long
	 *
	 */
	
	public Long getRotacaoMaxima() {
		return rotacaoMaxima;
	}

	/** M�todo setter para a propriedade rotacaoMaxima
	 *
	 * @param rotacaoMaxima 
	 *       <code>rotacaoMaxima<code> a ser designado para GarantiaHeader.java
	 *
	 */
	public void setRotacaoMaxima(Long rotacaoMaxima) {
		this.rotacaoMaxima = rotacaoMaxima;
	}
	
	/** M�todo setter para a propriedade flClassificacaoTecnica
	 *
	 * @param flClassificacaoTecnica 
	 *       <code>flClassificacaoTecnica<code> a ser designado para GarantiaHeader.java
	 *
	 */
	public void setFlClassificacaoTecnica(String flClassificacaoTecnica) {
		this.flClassificacaoTecnica = flClassificacaoTecnica;
	}
	
	/** M�todo getter para a propriedade flClassificacaoTecnica
	 *
	 * @return flClassificacaoTecnica do tipo String
	 *
	 */
	
	public String getFlClassificacaoTecnica() {
		return flClassificacaoTecnica;
	}

	
//	----[ M�todos Utils ]---------------------------------------------------

	/**
	 * Verifica se a garantia pertence a uma informa��o de Mercado
	 * 
	 * 
	 * @return  boolean - TRUE pertence a uma IM
	 */
	public boolean getHasInfoMercado() {
		
		if ( this.infoMercado != null ) 
			return true;
		else
			return false;
		
	}
	
	/** 
	 *  Verifica se a Garantia pode ser alterada ou n�o
	 *  Pode ser usado na l�gica do form na camada de visualiza��o
	 *  
	 *  @return boolean  True se for permitida a altera��o
	 */
	public boolean getValidAlterGarantia() {
		
		boolean isValid = false;
		
		if ( (	this.getStatusGarantia().getEntityId().equals( StatusGarantia.STATUS_DIGITACAO )
			 || this.getStatusGarantia().getEntityId().equals( StatusGarantia.STATUS_MANUTENCAO ) 
			 || this.getStatusGarantia().getEntityId().equals( StatusGarantia.STATUS_AGUARDANDO_APROVACAO_INFO_MERCADO ) 
			 || ( this.getStatusGarantia().getEntityId().equals( StatusGarantia.STATUS_NF_DIVERGENTE ) && this.getLote().getDataEnvioAdiant() == null )
			 )
			) {
			
			isValid = true;
			
		} else if(StatusGarantia.STATUS_AGUARDANDO_DIGITACAO_NOTA.equals(this.getStatusGarantia().getEntityId()) && this.getHasCampaign()){			
			isValid = true;			
		}
		
		return isValid;		
		
	}
	
	/**
	 * Verifica se a garantia pertence a uma campanha de modifica��o t�cnica
	 * 
	 * 
	 * @return  boolean - TRUE pertence a uma campanha
	 */
	public boolean getHasCampaign() {
		
		if ( this.campaign != null ) 
			return true;
		else
			return false;
		
	}
	
	/** Associa uma nota Fiscal de pe�a ou remessa a uma linha da garantia
	 * 
	 * @param lineId
	 * @param notaFiscal
	 * @return boolean
	 *  TRUE - para item associado
	 *  
	 */
	public boolean addNotaFiscalPeca(Long lineId, NotaFiscal notaFiscal) {
		
		boolean isAssoc = false;
		
		Iterator it = this.getListGarantiaLine().iterator();
		while ( it.hasNext() ) {
			
			GarantiaLine garantiaLine = (GarantiaLine)it.next();
			
			if ( garantiaLine.getCompositeEntityId().getLineId().equals(lineId) ) {			
				if ( NotaFiscal.TIPO_NOTA_REMESSA.equals(notaFiscal.getTipoNFId()) )
					garantiaLine.setNotaFiscalRemessa(notaFiscal);
				else if ( NotaFiscal.TIPO_NOTA_PECA.equals(notaFiscal.getTipoNFId()) || NotaFiscal.TIPO_NOTA_CONJUGADA.equals(notaFiscal.getTipoNFId()) )
					garantiaLine.setNotaFiscalPeca(notaFiscal);
				
				isAssoc = true;
			}		
		}
		
		return isAssoc;
	}
}
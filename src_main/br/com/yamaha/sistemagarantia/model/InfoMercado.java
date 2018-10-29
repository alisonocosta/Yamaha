/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......InfoMercado.java
 *   .: Cria��o.....30 de junho, 23:06
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "InfoMercado".
 */
package br.com.yamaha.sistemagarantia.model;

import java.util.Date;
import java.util.List;

import br.com.resource.infra.model.EntityObject;
import br.com.resource.infra.utils.DateUtils;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "InfoMercado" no sistema.
 *  
 *  @author Edson Luiz Sumensari
 */
public class InfoMercado extends EntityObject {

    //----[ Atributos de classe e inst�ncia ]-----------------------------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;    
    
    private Long gravidadeId;
    
    private String tipoGasolinaId;
    
    private Long tipoProblemaId;
    
    private Long itemId;
    
    private Long tipoUsoId;
    
    private Long statusImId;
    
    /** Usu�rio que preencheu */
    private String preenchidoPor;
    
    /** Data da Informa��o. */
    private Date dataInfo;
    
    /** N�mero do modelo(chassi com 12)  que deu origem a SG. */
    private String chassi;
    
    /** Total de horas. */
    private Long totalHrs;
    
    /** Data da venda. */
    private Date dataVenda;
    
    /** Data do problema. */
    private Date dataProblema;
    
    /** Horas de uso do produto. */
    private Long horasUso;
    
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
    
    /** Status da informa��o de mercado */
    private Long status;
    
    /** Identifica se a IM � apenas informativa ou deve possuir Garantia*/
    private boolean isOnlyInfo;
    
    /** Entidade de Garantia */
    private GarantiaHeader garantia;
    
    /** Entidade Concesin�ria relacionada */
    private Concessionaria concessionaria;
    
    /** Entidade do status relacionado */
    private StatusInfoMercado statusInfoMercado;
    
    /** Cole��o de fotos relacionadas a informa��o de mercado */
    private List listFotos;
	

	/** M�todo getter para a propriedade chassi
	 *
	 *  @return String de chassi
	 */
	public String getChassi() {
		return chassi.toUpperCase();
	}

	/** M�todo setter para a propriedade chassi
	 *
	 * @param chassi String
	 */
	public void setChassi(String chassi) {
		this.chassi = chassi.toUpperCase();
	}

	/** M�todo getter para a propriedade cidadeUso
	 *
	 *  @return String de cidadeUso
	 */
	public String getCidadeUso() {
		return cidadeUso;
	}

	/** M�todo setter para a propriedade cidadeUso
	 *
	 * @param cidadeUso String
	 */
	public void setCidadeUso(String cidadeUso) {
		this.cidadeUso = cidadeUso;
	}

	/** M�todo getter para a propriedade dataInfo
	 *
	 *  @return Date de dataInfo
	 */
	public Date getDataInfo() {
		return dataInfo;
	}
	
	/** M�todo getter para "dataInfo" formatada.
     *  @return
     *      <code>String</code>. O valor atual de dataInfo,
     *      com uma m�scara aplicada.
     */
    public String getMaskedDataInfo() {
        return DateUtils.applyMask(this.dataInfo);
    }    

	/** M�todo setter para a propriedade dataInfo
	 *
	 * @param dataInfo Date
	 */
	public void setDataInfo(Date dataInfo) {
		this.dataInfo = dataInfo;
	}

	/** M�todo getter para a propriedade dataProblema
	 *
	 *  @return Date de dataProblema
	 */
	public Date getDataProblema() {
		return dataProblema;
	}
	
	/** M�todo getter para "dataProblema" formatada.
     *  @return
     *      <code>String</code>. O valor atual de dataProblema,
     *      com uma m�scara aplicada.
     */
    public String getMaskedDataProblema() {
        return DateUtils.applyMask(this.dataProblema);
    }    


	/** M�todo setter para a propriedade dataProblema
	 *
	 * @param dataProblema Date
	 */
	public void setDataProblema(Date dataProblema) {
		this.dataProblema = dataProblema;
	}

	/** M�todo getter para a propriedade dataVenda
	 *
	 *  @return Date de dataVenda
	 */
	public Date getDataVenda() {
		return dataVenda;
	}

	/** M�todo setter para a propriedade dataVenda
	 *
	 * @param dataVenda Date
	 */
	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	/** M�todo getter para a propriedade horasUso
	 *
	 *  @return Long de horasUso
	 */
	public Long getHorasUso() {
		return horasUso;
	}

	/** M�todo setter para a propriedade horasUso
	 *
	 * @param horasUso Long
	 */
	public void setHorasUso(Long horasUso) {
		this.horasUso = horasUso;
	}

	/** M�todo getter para a propriedade localUsoDoce
	 *
	 *  @return String de localUsoDoce
	 */
	public String getLocalUsoDoce() {
		return localUsoDoce;
	}

	/** M�todo setter para a propriedade localUsoDoce
	 *
	 * @param localUsoDoce String
	 */
	public void setLocalUsoDoce(String localUsoDoce) {
		this.localUsoDoce = localUsoDoce;
	}

	/** M�todo getter para a propriedade localUsoSalg
	 *
	 *  @return String de localUsoSalg
	 */
	public String getLocalUsoSalg() {
		return localUsoSalg;
	}

	/** M�todo setter para a propriedade localUsoSalg
	 *
	 * @param localUsoSalg String
	 */
	public void setLocalUsoSalg(String localUsoSalg) {
		this.localUsoSalg = localUsoSalg;
	}

	/** M�todo getter para a propriedade marcaCasco
	 *
	 *  @return String de marcaCasco
	 */
	public String getMarcaCasco() {
		return marcaCasco;
	}

	/** M�todo setter para a propriedade marcaCasco
	 *
	 * @param marcaCasco String
	 */
	public void setMarcaCasco(String marcaCasco) {
		this.marcaCasco = marcaCasco;
	}

	/** M�todo getter para a propriedade marcaHelice
	 *
	 *  @return String de marcaHelica
	 */
	public String getMarcaHelice() {
		return marcaHelice;
	}

	/** M�todo setter para a propriedade marcaHelice
	 *
	 * @param marcaHelica String
	 */
	public void setMarcaHelice(String marcaHelice) {
		this.marcaHelice = marcaHelice;
	}

	/** M�todo getter para a propriedade materHelice
	 *
	 *  @return String de materHelice
	 */
	public String getMaterHelice() {
		return materHelice;
	}

	/** M�todo setter para a propriedade materHelice
	 *
	 * @param materHelice String
	 */
	public void setMaterHelice(String materHelice) {
		this.materHelice = materHelice;
	}

	/** M�todo getter para a propriedade modeloCasco
	 *
	 *  @return String de modeloCasco
	 */
	public String getModeloCasco() {
		return modeloCasco;
	}

	/** M�todo setter para a propriedade modeloCasco
	 *
	 * @param modeloCasco String
	 */
	public void setModeloCasco(String modeloCasco) {
		this.modeloCasco = modeloCasco;
	}

	/** M�todo getter para a propriedade passoHelice
	 *
	 *  @return Long de passoHelice
	 */
	public Long getPassoHelice() {
		return passoHelice;
	}

	/** M�todo setter para a propriedade passoHelice
	 *
	 * @param passoHelice Long
	 */
	public void setPassoHelice(Long passoHelice) {
		this.passoHelice = passoHelice;
	}

	/** M�todo getter para a propriedade preenchidoPor
	 *
	 *  @return String de preenchidoPor
	 */
	public String getPreenchidoPor() {
		return preenchidoPor;
	}

	/** M�todo setter para a propriedade preenchidoPor
	 *
	 * @param preenchidoPor String
	 */
	public void setPreenchidoPor(String preenchidoPor) {
		this.preenchidoPor = preenchidoPor;
	}

	/** M�todo getter para a propriedade rotacaoMaxima
	 *
	 *  @return Long de rotacaoMaxima
	 */
	public Long getRotacaoMaxima() {
		return rotacaoMaxima;
	}

	/** M�todo setter para a propriedade rotacaoMaxima
	 *
	 * @param rotacaoMaxima Long
	 */
	public void setRotacaoMaxima(Long rotacaoMaxima) {
		this.rotacaoMaxima = rotacaoMaxima;
	}

	/** M�todo getter para a propriedade tipoUso
	 *
	 *  @return String de tipoUso
	 */
	public String getTipoUso() {
		return tipoUso;
	}

	/** M�todo setter para a propriedade tipoUso
	 *
	 * @param tipoUso String
	 */
	public void setTipoUso(String tipoUso) {
		this.tipoUso = tipoUso;
	}

	/** M�todo getter para a propriedade totalHrs
	 *
	 *  @return Long de totalHrs
	 */
	public Long getTotalHrs() {
		return totalHrs;
	}

	/** M�todo setter para a propriedade totalHrs
	 *
	 * @param totalHrs Long
	 */
	public void setTotalHrs(Long totalHrs) {
		this.totalHrs = totalHrs;
	}

	/** M�todo getter para a propriedade concessionaria
	 *
	 *  @return Concessionaria de concessionaria
	 */
	public Concessionaria getConcessionaria() {
		return concessionaria;
	}

	/** M�todo setter para a propriedade concessionaria
	 *
	 * @param concessionaria Concessionaria
	 */
	public void setConcessionaria(Concessionaria concessionaria) {
		this.concessionaria = concessionaria;
	}

	/** M�todo getter para a propriedade status
	 *
	 *  @return Long de status
	 */
	public Long getStatus() {
		return status;
	}

	/** M�todo setter para a propriedade status
	 *
	 * @param status Long
	 */
	public void setStatus(Long status) {
		this.status = status;
	}

    /** M�todo getter para "statusInfoMercado".
     *  @return
     *      <code>StatusInfoMercado</code>. O valor atual de statusInfoMercado.
     */
    public StatusInfoMercado getStatusInfoMercado() {
        return this.statusInfoMercado;
    }

    /** M�todo setter para "statusInfoMercado".
     *  @param statusInfoMercado
     *      <code>statusInfoMercado</code> a ser designado para statusInfoMercado.
     */
    public void setStatusInfoMercado(StatusInfoMercado statusInfoMercado) {
        this.statusInfoMercado = statusInfoMercado;
    }

	/** M�todo getter para o campo "gravidadeId".
	 *  
	 *  @return O valor atual de "gravidadeId".
	 */
	public Long getGravidadeId() {
		return gravidadeId;
	}

	/** M�todo setter para o campo "gravidadeId".
	 * 
	 *  @param gravidadeId 
	 *    O novo valor para "gravidadeId".
	 */
	public void setGravidadeId(Long gravidadeId) {
		this.gravidadeId = gravidadeId;
	}

	/** M�todo getter para o campo "tipoGasolinaId".
	 *  
	 *  @return O valor atual de "tipoGasolinaId".
	 */
	public String getTipoGasolinaId() {
		return tipoGasolinaId;
	}

	/** M�todo setter para o campo "tipoGasolinaId".
	 * 
	 *  @param tipoGasolinaId 
	 *    O novo valor para "tipoGasolinaId".
	 */
	public void setTipoGasolinaId(String tipoGasolinaId) {
		this.tipoGasolinaId = tipoGasolinaId;
	}

	/** M�todo getter para o campo "tipoProblemaId".
	 *  
	 *  @return O valor atual de "tipoProblemaId".
	 */
	public Long getTipoProblemaId() {
		return tipoProblemaId;
	}

	/** M�todo setter para o campo "tipoProblemaId".
	 * 
	 *  @param tipoProblemaId 
	 *    O novo valor para "tipoProblemaId".
	 */
	public void setTipoProblemaId(Long tipoProblemaId) {
		this.tipoProblemaId = tipoProblemaId;
	}

	/** M�todo getter para o campo "itemId".
	 *  
	 *  @return O valor atual de "itemId".
	 */
	public Long getItemId() {
		return itemId;
	}

	/** M�todo setter para o campo "itemId".
	 * 
	 *  @param itemId 
	 *    O novo valor para "itemId".
	 */
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	/** M�todo getter para o campo "tipoUsoId".
	 *  
	 *  @return O valor atual de "tipoUsoId".
	 */
	public Long getTipoUsoId() {
		return tipoUsoId;
	}

	/** M�todo setter para o campo "tipoUsoId".
	 * 
	 *  @param tipoUsoId 
	 *    O novo valor para "tipoUsoId".
	 */
	public void setTipoUsoId(Long tipoUsoId) {
		this.tipoUsoId = tipoUsoId;
	}

	/** M�todo getter para o campo "statusImId".
	 *  
	 *  @return O valor atual de "statusImId".
	 */
	public Long getStatusImId() {
		return statusImId;
	}

	/** M�todo setter para o campo "statusImId".
	 * 
	 *  @param statusImId 
	 *    O novo valor para "statusImId".
	 */
	public void setStatusImId(Long statusImId) {
		this.statusImId = statusImId;
	}

	/** M�todo getter para a propriedade listFotos
	 * 
	 *  @return List
	 *
	 */
	public List getListFotos() {
		return listFotos;
	}

	/** M�todo setter para a propriedade listFotos
	 *
	 * @param listFotos 
	 *           <code>List</code> a ser designado para listFotos.
	 * 
	 */
	public void setListFotos(List listFotos) {
		this.listFotos = listFotos;
	}

	/** M�todo getter para a propriedade isOnlyInfo
	 *
	 *  @return boolean de isOnlyInfo
	 */
	public boolean isOnlyInfo() {
		return isOnlyInfo;
	}

	/** M�todo setter para a propriedade isOnlyInfo
	 *
	 * @param isOnlyInfo boolean
	 */
	public void setOnlyInfo(boolean isOnlyInfo) {
		this.isOnlyInfo = isOnlyInfo;
	}

	/** M�todo getter para a propriedade garantia
	 *
	 *  @return GarantiaHeader de garantia
	 */
	public GarantiaHeader getGarantia() {
		return garantia;
	}

	/** M�todo setter para a propriedade garantia
	 *
	 * @param garantia GarantiaHeader
	 */
	public void setGarantia(GarantiaHeader garantia) {
		this.garantia = garantia;
	}
	
	/**
	 * Verifica se a informa��o de Mercado pertence a uma garantia
	 * 
	 * 
	 * @return  boolean - TRUE pertence a uma garantia
	 */
	public boolean getHasGarantia() {
		
		if ( this.garantia != null ) 
			return true;
		else
			return false;
		
	}
}
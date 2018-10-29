/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......InfoMercado.java
 *   .: Criação.....30 de junho, 23:06
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "InfoMercado".
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

    //----[ Atributos de classe e instância ]-----------------------------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;    
    
    private Long gravidadeId;
    
    private String tipoGasolinaId;
    
    private Long tipoProblemaId;
    
    private Long itemId;
    
    private Long tipoUsoId;
    
    private Long statusImId;
    
    /** Usuário que preencheu */
    private String preenchidoPor;
    
    /** Data da Informação. */
    private Date dataInfo;
    
    /** Número do modelo(chassi com 12)  que deu origem a SG. */
    private String chassi;
    
    /** Total de horas. */
    private Long totalHrs;
    
    /** Data da venda. */
    private Date dataVenda;
    
    /** Data do problema. */
    private Date dataProblema;
    
    /** Horas de uso do produto. */
    private Long horasUso;
    
    /** Local Uso - Água Doce. */
    private String localUsoDoce;
    
    /** Local uso - Água Salgada. */
    private String localUsoSalg;
    
    /** Cidade de Uso. */
    private String cidadeUso;
    
    /** Tipo de Uso. */
    private String tipoUso;
    
    /** marca do casco. */
    private String marcaCasco;
    
    /** Modelo do casco. */
    private String modeloCasco;
    
    /** Marca hélice. */
    private String marcaHelice;
    
    /** Material Hélice. */
    private String materHelice;
    
    /** Passo hélice. */
    private Long passoHelice;
    
    /** Rotação Máxima. */
    private Long rotacaoMaxima;
    
    /** Status da informação de mercado */
    private Long status;
    
    /** Identifica se a IM é apenas informativa ou deve possuir Garantia*/
    private boolean isOnlyInfo;
    
    /** Entidade de Garantia */
    private GarantiaHeader garantia;
    
    /** Entidade Concesinária relacionada */
    private Concessionaria concessionaria;
    
    /** Entidade do status relacionado */
    private StatusInfoMercado statusInfoMercado;
    
    /** Coleção de fotos relacionadas a informação de mercado */
    private List listFotos;
	

	/** Método getter para a propriedade chassi
	 *
	 *  @return String de chassi
	 */
	public String getChassi() {
		return chassi.toUpperCase();
	}

	/** Método setter para a propriedade chassi
	 *
	 * @param chassi String
	 */
	public void setChassi(String chassi) {
		this.chassi = chassi.toUpperCase();
	}

	/** Método getter para a propriedade cidadeUso
	 *
	 *  @return String de cidadeUso
	 */
	public String getCidadeUso() {
		return cidadeUso;
	}

	/** Método setter para a propriedade cidadeUso
	 *
	 * @param cidadeUso String
	 */
	public void setCidadeUso(String cidadeUso) {
		this.cidadeUso = cidadeUso;
	}

	/** Método getter para a propriedade dataInfo
	 *
	 *  @return Date de dataInfo
	 */
	public Date getDataInfo() {
		return dataInfo;
	}
	
	/** Método getter para "dataInfo" formatada.
     *  @return
     *      <code>String</code>. O valor atual de dataInfo,
     *      com uma máscara aplicada.
     */
    public String getMaskedDataInfo() {
        return DateUtils.applyMask(this.dataInfo);
    }    

	/** Método setter para a propriedade dataInfo
	 *
	 * @param dataInfo Date
	 */
	public void setDataInfo(Date dataInfo) {
		this.dataInfo = dataInfo;
	}

	/** Método getter para a propriedade dataProblema
	 *
	 *  @return Date de dataProblema
	 */
	public Date getDataProblema() {
		return dataProblema;
	}
	
	/** Método getter para "dataProblema" formatada.
     *  @return
     *      <code>String</code>. O valor atual de dataProblema,
     *      com uma máscara aplicada.
     */
    public String getMaskedDataProblema() {
        return DateUtils.applyMask(this.dataProblema);
    }    


	/** Método setter para a propriedade dataProblema
	 *
	 * @param dataProblema Date
	 */
	public void setDataProblema(Date dataProblema) {
		this.dataProblema = dataProblema;
	}

	/** Método getter para a propriedade dataVenda
	 *
	 *  @return Date de dataVenda
	 */
	public Date getDataVenda() {
		return dataVenda;
	}

	/** Método setter para a propriedade dataVenda
	 *
	 * @param dataVenda Date
	 */
	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	/** Método getter para a propriedade horasUso
	 *
	 *  @return Long de horasUso
	 */
	public Long getHorasUso() {
		return horasUso;
	}

	/** Método setter para a propriedade horasUso
	 *
	 * @param horasUso Long
	 */
	public void setHorasUso(Long horasUso) {
		this.horasUso = horasUso;
	}

	/** Método getter para a propriedade localUsoDoce
	 *
	 *  @return String de localUsoDoce
	 */
	public String getLocalUsoDoce() {
		return localUsoDoce;
	}

	/** Método setter para a propriedade localUsoDoce
	 *
	 * @param localUsoDoce String
	 */
	public void setLocalUsoDoce(String localUsoDoce) {
		this.localUsoDoce = localUsoDoce;
	}

	/** Método getter para a propriedade localUsoSalg
	 *
	 *  @return String de localUsoSalg
	 */
	public String getLocalUsoSalg() {
		return localUsoSalg;
	}

	/** Método setter para a propriedade localUsoSalg
	 *
	 * @param localUsoSalg String
	 */
	public void setLocalUsoSalg(String localUsoSalg) {
		this.localUsoSalg = localUsoSalg;
	}

	/** Método getter para a propriedade marcaCasco
	 *
	 *  @return String de marcaCasco
	 */
	public String getMarcaCasco() {
		return marcaCasco;
	}

	/** Método setter para a propriedade marcaCasco
	 *
	 * @param marcaCasco String
	 */
	public void setMarcaCasco(String marcaCasco) {
		this.marcaCasco = marcaCasco;
	}

	/** Método getter para a propriedade marcaHelice
	 *
	 *  @return String de marcaHelica
	 */
	public String getMarcaHelice() {
		return marcaHelice;
	}

	/** Método setter para a propriedade marcaHelice
	 *
	 * @param marcaHelica String
	 */
	public void setMarcaHelice(String marcaHelice) {
		this.marcaHelice = marcaHelice;
	}

	/** Método getter para a propriedade materHelice
	 *
	 *  @return String de materHelice
	 */
	public String getMaterHelice() {
		return materHelice;
	}

	/** Método setter para a propriedade materHelice
	 *
	 * @param materHelice String
	 */
	public void setMaterHelice(String materHelice) {
		this.materHelice = materHelice;
	}

	/** Método getter para a propriedade modeloCasco
	 *
	 *  @return String de modeloCasco
	 */
	public String getModeloCasco() {
		return modeloCasco;
	}

	/** Método setter para a propriedade modeloCasco
	 *
	 * @param modeloCasco String
	 */
	public void setModeloCasco(String modeloCasco) {
		this.modeloCasco = modeloCasco;
	}

	/** Método getter para a propriedade passoHelice
	 *
	 *  @return Long de passoHelice
	 */
	public Long getPassoHelice() {
		return passoHelice;
	}

	/** Método setter para a propriedade passoHelice
	 *
	 * @param passoHelice Long
	 */
	public void setPassoHelice(Long passoHelice) {
		this.passoHelice = passoHelice;
	}

	/** Método getter para a propriedade preenchidoPor
	 *
	 *  @return String de preenchidoPor
	 */
	public String getPreenchidoPor() {
		return preenchidoPor;
	}

	/** Método setter para a propriedade preenchidoPor
	 *
	 * @param preenchidoPor String
	 */
	public void setPreenchidoPor(String preenchidoPor) {
		this.preenchidoPor = preenchidoPor;
	}

	/** Método getter para a propriedade rotacaoMaxima
	 *
	 *  @return Long de rotacaoMaxima
	 */
	public Long getRotacaoMaxima() {
		return rotacaoMaxima;
	}

	/** Método setter para a propriedade rotacaoMaxima
	 *
	 * @param rotacaoMaxima Long
	 */
	public void setRotacaoMaxima(Long rotacaoMaxima) {
		this.rotacaoMaxima = rotacaoMaxima;
	}

	/** Método getter para a propriedade tipoUso
	 *
	 *  @return String de tipoUso
	 */
	public String getTipoUso() {
		return tipoUso;
	}

	/** Método setter para a propriedade tipoUso
	 *
	 * @param tipoUso String
	 */
	public void setTipoUso(String tipoUso) {
		this.tipoUso = tipoUso;
	}

	/** Método getter para a propriedade totalHrs
	 *
	 *  @return Long de totalHrs
	 */
	public Long getTotalHrs() {
		return totalHrs;
	}

	/** Método setter para a propriedade totalHrs
	 *
	 * @param totalHrs Long
	 */
	public void setTotalHrs(Long totalHrs) {
		this.totalHrs = totalHrs;
	}

	/** Método getter para a propriedade concessionaria
	 *
	 *  @return Concessionaria de concessionaria
	 */
	public Concessionaria getConcessionaria() {
		return concessionaria;
	}

	/** Método setter para a propriedade concessionaria
	 *
	 * @param concessionaria Concessionaria
	 */
	public void setConcessionaria(Concessionaria concessionaria) {
		this.concessionaria = concessionaria;
	}

	/** Método getter para a propriedade status
	 *
	 *  @return Long de status
	 */
	public Long getStatus() {
		return status;
	}

	/** Método setter para a propriedade status
	 *
	 * @param status Long
	 */
	public void setStatus(Long status) {
		this.status = status;
	}

    /** Método getter para "statusInfoMercado".
     *  @return
     *      <code>StatusInfoMercado</code>. O valor atual de statusInfoMercado.
     */
    public StatusInfoMercado getStatusInfoMercado() {
        return this.statusInfoMercado;
    }

    /** Método setter para "statusInfoMercado".
     *  @param statusInfoMercado
     *      <code>statusInfoMercado</code> a ser designado para statusInfoMercado.
     */
    public void setStatusInfoMercado(StatusInfoMercado statusInfoMercado) {
        this.statusInfoMercado = statusInfoMercado;
    }

	/** Método getter para o campo "gravidadeId".
	 *  
	 *  @return O valor atual de "gravidadeId".
	 */
	public Long getGravidadeId() {
		return gravidadeId;
	}

	/** Método setter para o campo "gravidadeId".
	 * 
	 *  @param gravidadeId 
	 *    O novo valor para "gravidadeId".
	 */
	public void setGravidadeId(Long gravidadeId) {
		this.gravidadeId = gravidadeId;
	}

	/** Método getter para o campo "tipoGasolinaId".
	 *  
	 *  @return O valor atual de "tipoGasolinaId".
	 */
	public String getTipoGasolinaId() {
		return tipoGasolinaId;
	}

	/** Método setter para o campo "tipoGasolinaId".
	 * 
	 *  @param tipoGasolinaId 
	 *    O novo valor para "tipoGasolinaId".
	 */
	public void setTipoGasolinaId(String tipoGasolinaId) {
		this.tipoGasolinaId = tipoGasolinaId;
	}

	/** Método getter para o campo "tipoProblemaId".
	 *  
	 *  @return O valor atual de "tipoProblemaId".
	 */
	public Long getTipoProblemaId() {
		return tipoProblemaId;
	}

	/** Método setter para o campo "tipoProblemaId".
	 * 
	 *  @param tipoProblemaId 
	 *    O novo valor para "tipoProblemaId".
	 */
	public void setTipoProblemaId(Long tipoProblemaId) {
		this.tipoProblemaId = tipoProblemaId;
	}

	/** Método getter para o campo "itemId".
	 *  
	 *  @return O valor atual de "itemId".
	 */
	public Long getItemId() {
		return itemId;
	}

	/** Método setter para o campo "itemId".
	 * 
	 *  @param itemId 
	 *    O novo valor para "itemId".
	 */
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	/** Método getter para o campo "tipoUsoId".
	 *  
	 *  @return O valor atual de "tipoUsoId".
	 */
	public Long getTipoUsoId() {
		return tipoUsoId;
	}

	/** Método setter para o campo "tipoUsoId".
	 * 
	 *  @param tipoUsoId 
	 *    O novo valor para "tipoUsoId".
	 */
	public void setTipoUsoId(Long tipoUsoId) {
		this.tipoUsoId = tipoUsoId;
	}

	/** Método getter para o campo "statusImId".
	 *  
	 *  @return O valor atual de "statusImId".
	 */
	public Long getStatusImId() {
		return statusImId;
	}

	/** Método setter para o campo "statusImId".
	 * 
	 *  @param statusImId 
	 *    O novo valor para "statusImId".
	 */
	public void setStatusImId(Long statusImId) {
		this.statusImId = statusImId;
	}

	/** Método getter para a propriedade listFotos
	 * 
	 *  @return List
	 *
	 */
	public List getListFotos() {
		return listFotos;
	}

	/** Método setter para a propriedade listFotos
	 *
	 * @param listFotos 
	 *           <code>List</code> a ser designado para listFotos.
	 * 
	 */
	public void setListFotos(List listFotos) {
		this.listFotos = listFotos;
	}

	/** Método getter para a propriedade isOnlyInfo
	 *
	 *  @return boolean de isOnlyInfo
	 */
	public boolean isOnlyInfo() {
		return isOnlyInfo;
	}

	/** Método setter para a propriedade isOnlyInfo
	 *
	 * @param isOnlyInfo boolean
	 */
	public void setOnlyInfo(boolean isOnlyInfo) {
		this.isOnlyInfo = isOnlyInfo;
	}

	/** Método getter para a propriedade garantia
	 *
	 *  @return GarantiaHeader de garantia
	 */
	public GarantiaHeader getGarantia() {
		return garantia;
	}

	/** Método setter para a propriedade garantia
	 *
	 * @param garantia GarantiaHeader
	 */
	public void setGarantia(GarantiaHeader garantia) {
		this.garantia = garantia;
	}
	
	/**
	 * Verifica se a informação de Mercado pertence a uma garantia
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
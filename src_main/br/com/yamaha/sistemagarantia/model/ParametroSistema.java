/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......ParametroSistema.java
 *   .: Cria��o.....20 de junho, 08:36
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "ParametroSistema".
 */
package br.com.yamaha.sistemagarantia.model;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema representando a tabela "ParametroSistema".
 *  
 *  @author Edson Luiz Sumensari
 */
public class ParametroSistema extends EntityObject {

	 /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L; 
    
    public static final String QTDE_SERVICO = "QTD LIMITE CODIGO SERVICO";    
    public static final String VALOR_SERVICOS_TERCEIROS = "VALOR SERVICOS TERCEIROS";	
    /** Valor da toler�ncia para diferen�a no lan�amento de notas fiscais */
    public static final String TOLERANCIA_NOTA = "TOLERANCIA NOTA"; 
    /** Valor da toler�ncia para o n�mero de dias para o lan�amento de notas fiscais */
    public static final String TOLERANCIA_DIAS_NF = "TOLERANCIA DIAS EMISSAO NF";  
    /** Valor que determina o prazo de garantia para linha de MOTOCICLETAS */
    public static final String GARANTIA_MOTO_DIAS = "GARANTIA MOTO DIAS";
    /** Valor que determina o prazo de garantia para linha de MOTOCICLETAS DE COMPETI��O */
    public static final String GARANTIA_MOTO_COMPETICAO_DIAS = "GARANTIA MOTO COMPETICAO DIAS";
    /** Valor que determina o prazo de garantia para linha de MOTOCICLETAS QUADRICICLO */
    public static final String GARANTIA_MOTO_QUADRICICLO_DIAS = "GARANTIA MOTO QUADRICICLO DIAS";
    /** Valor que determina o prazo de garantia para linha de NAUTICA COMERCIAL JET */
    public static final String GARANTIA_NAUTICA_COMERCIAL_JET_DIAS	= "GARANTIA NAUTICA COMERCIAL JET DIAS";
    /** Valor que determina o prazo de garantia para linha de NAUTICA COMERCIAL MP */
    public static final String GARANTIA_NAUTICA_COMERCIAL_MP_DIAS	= "GARANTIA NAUTICA COMERCIAL MP DIAS";
    /** Valor que determina o prazo de garantia para linha de NAUTICA LASER CKD */
    public static final String GARANTIA_NAUTICA_LASER_CKD_DIAS	= "GARANTIA NAUTICA LASER CKD DIAS";
    /** Valor que determina o prazo de garantia para linha de NAUTICA LASER CBU */
    public static final String GARANTIA_NAUTICA_LASER_CBU_DIAS	= "GARANTIA NAUTICA LASER CBU DIAS";
    /** Valor que determina a data do invent�rio do estoque */
    public static final String DATA_INVENTARIO = "DATA DO INVENTARIO";    
    /** Valor que determina se o sistema est� liberado para acesso */     
    public static final String DISPONIBILIDADE = "SISTEMA DISPONIVEL";
    /** Valor que determina se o sistema est� liberado para acesso */     
    public static final String BONIFICACAO = "PERC BONIFICACAO MOTO";    
    /** Valor que determina o tamanho m�ximo de um arquivo para upload */     
    public static final String FILE_MAX_SIZE_UPLOAD = "FILE_MAX_SIZE_UPLOAD";
    /** Valor que determina o quilometragem m�xima para uma Garantia */     
    public static final String KM_MAX_WARRANTY = "QUILOMETRAGEM MAXIMA GARANTIA";
    /** Valor que determina o valor m�ximo para servi�os de terceiro para a linha N�utica */     
    public static final String GARANTIA_NAUTICA_VALOR_SERVICOS_TERCEIROS = "GARANTIA_NAUTICA_VALOR_SERVICOS_TERCEIROS";
    
    /* sjoviano 16-FEV-2016 INI #01 */
    /** Valor que determina o prazo de garantia diferenciada linha de GARANTIA DIFERENCIADA */
    public static final String GARANTIA_DIFERENCIADA = "GARANTIA DIFERENCIADA";
    /* sjoviano 16-FEV-2016 FIM #01 */
    
    /** Valor que determina a a valida��o da revis�o estar� ativa ou n�o
     * 	 S - Ativada
     * 	 N - Desativada 
     * 
     */     
    public static final String VALIDAR_REVISAO_ANTERIOR = "VALIDAR REVISAO ANTERIOR";
    
    /**
    *  S - Liberado para acesso 
    *  N - N�o est� liberado para acesso
    *  I - Dispon�vel apenas para os usu�rios internos
    */
    public static final String DISPONIBILIDADE_YES = "S";    
    public static final String DISPONIBILIDADE_NO  = "N";    
    public static final String DISPONIBILIDADE_IN  = "I";
    
    /** 
     * Constante para o par�metro correspondente a quantidade de dias 
     * corridos do lan�amento da garantia/cupom ap�s o fechamento da OS --Provis�rio
     */
    public static final String PRAZO_ENVIO_GARANTIA_DIAS = "PRAZO ENVIO GARANTIA DIAS";
    
    /** 
     * Constante para o par�metro correspondente a quantidade de dias 
     * corridos do lan�amento da garantia/cupom ap�s o fechamento da OS linha Moto
     */
    public static final String PRAZO_ENVIO_MOTO_DIAS = "PRAZO ENVIO MOTO DIAS";  
    
    /** 
     * Constante para o par�metro correspondente a quantidade de dias 
     * corridos do lan�amento da garantia/cupom ap�s o fechamento da OS linha N�utica
     */
    public static final String PRAZO_ENVIO_NAUTICA_DIAS = "PRAZO ENVIO NAUTICA DIAS";
    
    /** 
     * Constante para o par�metro correspondente a quantidade de dias 
     * corridos da venda do produto ao cliente para data da entrega
     */
    public static final String PRAZO_ENTREGA_NAUTICA_DIAS = "PRAZO ENTREGA NAUTICA DIAS";
    
    public static final String HOME_PAGE_INTERNO = "HOME_PAGE_INTERNO";
    
    public static final String DATA_BASE_NFE = "DATA_BASE_NFE";
    
    /**
     * constante para recupra��o de um Caminho base para os arquivos de uma garantia
     */
    public static final String CAMINHO_ARQUIVOS_GARANTIA = "CAMINHO_ARQUIVOS_GARANTIA";
    
    /** Valor que determina o tamanho m�ximo dos arquivos de garantia da linha de motocicletas para upload  */     
    public static final String FILE_MAX_SIZE_UPLOAD_GARANTIA_MOTO = "FILE_MAX_SIZE_UPLOAD_GARANTIA_MOTO";
    
    /** Valor que determina a quantidade de dias limite para a data da abertura da OS de garantia da linha de moto*/     
    public static final String QTDE_DIAS_ABERTURA_OS_GARANTIA_MOTO = "QTDE_DIAS_ABERTURA_OS_GARANTIA_MOTO";
    
    /** Valor que determina a quantidade de dias limite para a data da fechamento da OS de garantia da linha de Moto*/ 
    public static final String QTDE_DIAS_FECHAMENTO_OS_GARANTIA_MOTO = "QTDE_DIAS_FECHAMENTO_OS_GARANTIA_MOTO";
    
    /** Valor que determina o tamanho m�ximo dos arquivos de garantia da linha de N�utica para upload  */     
    public static final String FILE_MAX_SIZE_UPLOAD_GARANTIA_NAUTICA = "FILE_MAX_SIZE_UPLOAD_GARANTIA_NAUTICA";
    
    /** Valor que determina a quantidade de dias limite para a data da abertura da OS de garantia da linha de N�utica*/     
    public static final String QTDE_DIAS_ABERTURA_OS_GARANTIA_NAUTICA = "QTDE_DIAS_ABERTURA_OS_GARANTIA_NAUTICA";
    
    /** Valor que determina a quantidade de dias limite para a data da fechamento da OS de garantia da linha de N�utica*/ 
    public static final String QTDE_DIAS_FECHAMENTO_OS_GARANTIA_NAUTICA = "QTDE_DIAS_FECHAMENTO_OS_GARANTIA_NAUTICA";
    
	private String nomeParametro;
	private String valorParametro;
	private String tipoDadoParametro;
	
	
	/** M�todo getter para a propriedade nomeParametro
	 * 
	 *  @return String
	 *
	 */
	public String getNomeParametro() {
		return nomeParametro;
	}
	/** M�todo getter para a propriedade tipoDadoParametro
	 * 
	 *  @return String
	 *
	 */
	public String getTipoDadoParametro() {
		return tipoDadoParametro;
	}
	/** M�todo getter para a propriedade valorParametro
	 * 
	 *  @return String
	 *
	 */
	public String getValorParametro() {
		return valorParametro;
	}
	/** M�todo setter para a propriedade nomeParametro
	 *
	 * @param nomeParametro 
	 *           <code>String</code> a ser designado para nomeParametro.
	 * 
	 */
	public void setNomeParametro(String nomeParametro) {
		this.nomeParametro = nomeParametro;
	}
	/** M�todo setter para a propriedade tipoDadoParametro
	 *
	 * @param tipoDadoParametro 
	 *           <code>String</code> a ser designado para tipoDadoParametro.
	 * 
	 */
	public void setTipoDadoParametro(String tipoDadoParametro) {
		this.tipoDadoParametro = tipoDadoParametro;
	}
	/** M�todo setter para a propriedade valorParametro
	 *
	 * @param valorParametro 
	 *           <code>String</code> a ser designado para valorParametro.
	 * 
	 */
	public void setValorParametro(String valorParametro) {
		this.valorParametro = valorParametro;
	}
	
}
/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......Lote.java
 *   .: Cria��o.....03 de maio, 09:17
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Entidade "Concessionaria".
 */
package br.com.yamaha.sistemagarantia.model;

import java.util.List;

import br.com.resource.infra.model.EntityObject;
import br.com.resource.infra.utils.StringUtils;

/**
 * Entidade do sistema, em formato POJO, representando um objeto
 * "Concessionaria" no sistema.
 * 
 * @author Edson Luiz Sumensari
 */
public class Concessionaria extends EntityObject {
	
	//----[ Atributos de classe e inst�ncia ]-----------------------------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L; 
    
    /** Constante que indica que a concession�ria possui bonifica��o */
    public static final String FLAG_BONIFICACAO_YES = "S";
    
    /** Constante que indica que a concession�ria n�o possui bonifica��o */
    public static final String FLAG_BONIFICACAO_NOT = "N";
    
    /** Constante que indica que a concession�ria possui aprova��o autom�tica */
    public static final String FLAG_APROVA_YES = "S";
    
    /** Constante que indica que a concession�ria n�o possui aprova��o autom�tica */
    public static final String FLAG_APROVA_NOT = "N";
    
    /** Constante que indica que o sistema n�o est� dispon�vel para a concession�ria */
    public static final String FLAG_SISTEMA_NOT = "N";
    
    /** Constante que indica que o sistema est� dispon�vel para a concession�ria */
    public static final String FLAG_SISTEMA_YES = "S";
    
    /** Armazena um CNPJ, n�o formatado, da Concession�ria. */
    private Long cnpj;
    
    /** Armazena a Raz�o Social da Concession�ria. */
	private String razaoSocial;
	
	/** Endere�o no qual a Concession�ria se localiza. */
    private String endereco;
    
    /** Bairro no qual a Concession�ria se localiza. */
    private String bairro;
    
    /** Cidade no qual a Concession�ria se localiza. */
    private String cidade;
    
    /** Estado no qual a Concession�ria se localiza. */
    private String uf;
    
    /** Primeiro Telefone da Concession�ria. */
    private String telefone_1;
    
    /** Segundo Telefone da Concession�ria. */
    private String telefone_2;
    
    /** CEP n�o formatado da Concession�ria. */
    private Long cep;
    
    /** Ragi�o para linha Motocicleta. */
    private Long regiaMoto;
    
    /** Ragi�o para linha N�utica. */
    private Long regiaNautica;
    
    /** Flag de bonifica��o. */
    private String flagBonificacao;
    
    /** Flag de aprova��o autom�tica. */
    private String flagAprovacaoAutom;
    
    /** Flag de libera��o do sistema. */
    private String flagSistema;
    
    /** Flag para controlar a comunica��o de garantias em manuten��o */
    private boolean flagComunicado = false;
    
    /**Representante*/
    private Representante representante;
    
    /** Estado da Concession�ria */
    private Estado estado;
    
    /** Lista de lotes desta Concession�ria */
    private List lotes;
    
    //	----[ M�todos Getter ]---------------------------------------------------
    
	/**
	 * M�todo getter para o campo lotes
	 * 
	 * @return List
	 * 
	 */
	public List getLotes() {
		return lotes;
	}

	/** M�todo getter para a propriedade flagComunicado
	 *
	 * @return flagComunicado do tipo Boolean
	 *
	 */
	
	public boolean getFlagComunicado() {
		return flagComunicado;
	}

	/**
	 * M�todo getter para o campo bairro
	 * 
	 * @return String
	 * 
	 */
	public String getBairro() {
		return bairro;
	}

	/**
	 * M�todo getter para o campo cep
	 * 
	 * @return Long
	 * 
	 */
	public Long getCep() {
		return cep;
	}

	/**
	 * M�todo getter para o campo cidade
	 * 
	 * @return String
	 * 
	 */
	public String getCidade() {
		return cidade;
	}

	/**
	 * M�todo getter para o campo cnpj
	 * 
	 * @return Long
	 * 
	 */
	public Long getCnpj() {
		return cnpj;
	}
	
	/**
	 * M�todo getter para o campo cnpj com m�scara
	 * 
	 * @return String
	 * 
	 */
	public String getMaskedCnpj() {
		if(cnpj != null)		
			return StringUtils.formatarCnpj(String.valueOf(cnpj));
		else
			return "";
	}

	/**
	 * M�todo getter para o campo endereco
	 * 
	 * @return String
	 * 
	 */
	public String getEndereco() {
		return endereco;
	}
	/**
	 * M�todo getter para o campo razaoSocial
	 * 
	 * @return String
	 * 
	 */
	public String getRazaoSocial() {
		return razaoSocial;
	}

	/**
	 * M�todo getter para o campo telefone_1
	 * 
	 * @return String
	 * 
	 */
	public String getTelefone_1() {
		return telefone_1;
	}

	/**
	 * M�todo getter para o campo telefone_2
	 * 
	 * @return String
	 * 
	 */
	public String getTelefone_2() {
		return telefone_2;
	}

	/**
	 * M�todo getter para a propriedade regiaMoto
	 * 
	 * @return Long
	 * 
	 */
	public Long getRegiaMoto() {
		return regiaMoto;
	}

	/**
	 * M�todo getter para a propriedade regiaNautica
	 * 
	 * @return Long
	 * 
	 */
	public Long getRegiaNautica() {
		return regiaNautica;
	}
	
	/**
	 * M�todo getter para a propriedade flagBonificacao
	 * 
	 * @return String
	 * 
	 */
	public String getFlagBonificacao() {
		return flagBonificacao;
	}

	/**
	 * M�todo getter para a propriedade flagAprovacaoAutom
	 * 
	 * @return String
	 * 
	 */
	public String getFlagAprovacaoAutom() {
				
		return this.flagAprovacaoAutom;
			
	}

	/** M�todo getter para a propriedade flagSistema
	 *
	 *  @return String de flagSistema
	 */
	public String getFlagSistema() {
		return flagSistema;
	}

	/** M�todo getter para a propriedade representante
	 *
	 * @return representante do tipo Representante
	 *
	 */
	
	public Representante getRepresentante() {
		return representante;
	}
	
	//	----[ M�todos Setter ]---------------------------------------------------
	/** M�todo setter para a propriedade flagComunicado
	 *
	 * @param flagComunicado 
	 *       <code>flagComunicado<code> a ser designado para Concessionaria.java
	 *
	 */
	public void setFlagComunicado(boolean flagComunicado) {
		this.flagComunicado = flagComunicado;
	}

	/** M�todo setter para a propriedade representante
	 *
	 * @param representante 
	 *       <code>representante<code> a ser designado para Concessionaria.java
	 *
	 */
	public void setRepresentante(Representante representante) {
		this.representante = representante;
	}

	/**
	 * M�todo setter para o campo bairro
	 * 
	 * @param bairro
	 *            String
	 * 
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	/**
	 * M�todo setter para o campo cep
	 * 
	 * @param cep
	 *            Long
	 * 
	 */
	public void setCep(Long cep) {
		this.cep = cep;
	}

	/**
	 * M�todo setter para o campo cidade
	 * 
	 * @param cidade
	 *            String
	 * 
	 */
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	/**
	 * M�todo setter para o campo cnpj
	 * 
	 * @param cnpj
	 *            Long
	 * 
	 */
	public void setCnpj(Long cnpj) {
		this.cnpj = cnpj;
	}

	/**
	 * M�todo setter para o campo endereco
	 * 
	 * @param endereco
	 *            String
	 * 
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	/**
	 * M�todo setter para o campo razaoSocial
	 * 
	 * @param razaoSocial
	 *            String
	 * 
	 */
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	/**
	 * M�todo setter para o campo telefone_1
	 * 
	 * @param telefone_1
	 *            String
	 * 
	 */
	public void setTelefone_1(String telefone_1) {
		this.telefone_1 = telefone_1;
	}

	/**
	 * M�todo setter para o campo telefone_2
	 * 
	 * @param telefone_2
	 *            String
	 * 
	 */
	public void setTelefone_2(String telefone_2) {
		this.telefone_2 = telefone_2;
	}

	/**
	 * M�todo setter para o campo lotes
	 * 
	 * @param lotes
	 *            List
	 * 
	 */
	public void setLotes(List lotes) {
		this.lotes = lotes;
	}

	/**
	 * M�todo setter para a propriedade regiaMoto
	 * 
	 * @param regiaMoto
	 *            <code>Long</code> a ser designado para regiaMoto.
	 * 
	 */
	public void setRegiaMoto(Long regiaMoto) {
		this.regiaMoto = regiaMoto;
	}

	/**
	 * M�todo setter para a propriedade regiaNautica
	 * 
	 * @param regiaNautica
	 *            <code>Long</code> a ser designado para regiaNautica.
	 * 
	 */
	public void setRegiaNautica(Long regiaNautica) {
		this.regiaNautica = regiaNautica;
	}

	/**
	 * M�todo setter para a propriedade flagBonificacao
	 * 
	 * @param flagBonificacao
	 *            <code>String</code> a ser designado para flagBonificacao.
	 * 
	 */
	public void setFlagBonificacao(String flagBonificacao) {
		this.flagBonificacao = flagBonificacao;
	}
	
	/**
	 * M�todo setter para a propriedade flagAprovacaoAutom
	 * 
	 * Quando o valor enviado for nulo ou vazio, setamos "N"
	 * 
	 * @param flagAprovacaoAutom
	 *            <code>String</code> a ser designado para flagAprovacaoAutom.
	 * 
	 */
	public void setFlagAprovacaoAutom(String flagAprovacaoAutom) {
		
		String flag = new String();
		if ( "".equals(flagAprovacaoAutom) || flagAprovacaoAutom == null )
			flag = FLAG_APROVA_NOT;
		else
			flag = flagAprovacaoAutom;
		
		this.flagAprovacaoAutom =  flag;
		
	}
	
	/** M�todo setter para a propriedade flagSistema
	 *
	 * @param flagSistema String
	 */
	public void setFlagSistema(String flagSistema) {
		this.flagSistema = flagSistema;
	}
	
	/** Retorna a regi�o da concession�ria de acordo com a linha do produto
	 *  
	 * @param linha
	 * 	<code>Linha</code> - Entidade que representa alinha do produto
	 * 
	 * @return Long
	 * 	C�digo da regi�o do produto
	 */
	public Long getRegiao(Linha linha) {
		
		Long regiao = null;
		
		if ( new Long(1).equals(linha.getEntityId()) || new Long(4).equals(linha.getEntityId())) 
			regiao = getRegiaMoto();
		else if ( new Long(2).equals(linha.getEntityId()) || new Long(3).equals(linha.getEntityId())) 
			regiao = getRegiaNautica();
		
		return regiao;
	}

	/**
	 * M�todo getter para a propriedade uf
	 * @return  String de uf
	 */
	public String getUf() {
		return uf;
	}

	/**
	 * M�todo setter para a propriedade uf
	 * @param uf String
	 */
	public void setUf(String uf) {
		this.uf = uf;
	}

	/**
	 * M�todo getter para a propriedade estado
	 * @return  Estado de estado
	 */
	public Estado getEstado() {
		return estado;
	}

	/**
	 * M�todo setter para a propriedade estado
	 * @param estado Estado
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
}

/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......Lote.java
 *   .: Criação.....03 de maio, 09:17
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "Concessionaria".
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
	
	//----[ Atributos de classe e instância ]-----------------------------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L; 
    
    /** Constante que indica que a concessionária possui bonificação */
    public static final String FLAG_BONIFICACAO_YES = "S";
    
    /** Constante que indica que a concessionária não possui bonificação */
    public static final String FLAG_BONIFICACAO_NOT = "N";
    
    /** Constante que indica que a concessionária possui aprovação automática */
    public static final String FLAG_APROVA_YES = "S";
    
    /** Constante que indica que a concessionária não possui aprovação automática */
    public static final String FLAG_APROVA_NOT = "N";
    
    /** Constante que indica que o sistema não está disponível para a concessionária */
    public static final String FLAG_SISTEMA_NOT = "N";
    
    /** Constante que indica que o sistema está disponível para a concessionária */
    public static final String FLAG_SISTEMA_YES = "S";
    
    /** Armazena um CNPJ, não formatado, da Concessionária. */
    private Long cnpj;
    
    /** Armazena a Razão Social da Concessionária. */
	private String razaoSocial;
	
	/** Endereço no qual a Concessionária se localiza. */
    private String endereco;
    
    /** Bairro no qual a Concessionária se localiza. */
    private String bairro;
    
    /** Cidade no qual a Concessionária se localiza. */
    private String cidade;
    
    /** Estado no qual a Concessionária se localiza. */
    private String uf;
    
    /** Primeiro Telefone da Concessionária. */
    private String telefone_1;
    
    /** Segundo Telefone da Concessionária. */
    private String telefone_2;
    
    /** CEP não formatado da Concessionária. */
    private Long cep;
    
    /** Ragião para linha Motocicleta. */
    private Long regiaMoto;
    
    /** Ragião para linha Náutica. */
    private Long regiaNautica;
    
    /** Flag de bonificação. */
    private String flagBonificacao;
    
    /** Flag de aprovação automática. */
    private String flagAprovacaoAutom;
    
    /** Flag de liberação do sistema. */
    private String flagSistema;
    
    /** Flag para controlar a comunicação de garantias em manutenção */
    private boolean flagComunicado = false;
    
    /**Representante*/
    private Representante representante;
    
    /** Estado da Concessionária */
    private Estado estado;
    
    /** Lista de lotes desta Concessionária */
    private List lotes;
    
    //	----[ Métodos Getter ]---------------------------------------------------
    
	/**
	 * Método getter para o campo lotes
	 * 
	 * @return List
	 * 
	 */
	public List getLotes() {
		return lotes;
	}

	/** Método getter para a propriedade flagComunicado
	 *
	 * @return flagComunicado do tipo Boolean
	 *
	 */
	
	public boolean getFlagComunicado() {
		return flagComunicado;
	}

	/**
	 * Método getter para o campo bairro
	 * 
	 * @return String
	 * 
	 */
	public String getBairro() {
		return bairro;
	}

	/**
	 * Método getter para o campo cep
	 * 
	 * @return Long
	 * 
	 */
	public Long getCep() {
		return cep;
	}

	/**
	 * Método getter para o campo cidade
	 * 
	 * @return String
	 * 
	 */
	public String getCidade() {
		return cidade;
	}

	/**
	 * Método getter para o campo cnpj
	 * 
	 * @return Long
	 * 
	 */
	public Long getCnpj() {
		return cnpj;
	}
	
	/**
	 * Método getter para o campo cnpj com máscara
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
	 * Método getter para o campo endereco
	 * 
	 * @return String
	 * 
	 */
	public String getEndereco() {
		return endereco;
	}
	/**
	 * Método getter para o campo razaoSocial
	 * 
	 * @return String
	 * 
	 */
	public String getRazaoSocial() {
		return razaoSocial;
	}

	/**
	 * Método getter para o campo telefone_1
	 * 
	 * @return String
	 * 
	 */
	public String getTelefone_1() {
		return telefone_1;
	}

	/**
	 * Método getter para o campo telefone_2
	 * 
	 * @return String
	 * 
	 */
	public String getTelefone_2() {
		return telefone_2;
	}

	/**
	 * Método getter para a propriedade regiaMoto
	 * 
	 * @return Long
	 * 
	 */
	public Long getRegiaMoto() {
		return regiaMoto;
	}

	/**
	 * Método getter para a propriedade regiaNautica
	 * 
	 * @return Long
	 * 
	 */
	public Long getRegiaNautica() {
		return regiaNautica;
	}
	
	/**
	 * Método getter para a propriedade flagBonificacao
	 * 
	 * @return String
	 * 
	 */
	public String getFlagBonificacao() {
		return flagBonificacao;
	}

	/**
	 * Método getter para a propriedade flagAprovacaoAutom
	 * 
	 * @return String
	 * 
	 */
	public String getFlagAprovacaoAutom() {
				
		return this.flagAprovacaoAutom;
			
	}

	/** Método getter para a propriedade flagSistema
	 *
	 *  @return String de flagSistema
	 */
	public String getFlagSistema() {
		return flagSistema;
	}

	/** Método getter para a propriedade representante
	 *
	 * @return representante do tipo Representante
	 *
	 */
	
	public Representante getRepresentante() {
		return representante;
	}
	
	//	----[ Métodos Setter ]---------------------------------------------------
	/** Método setter para a propriedade flagComunicado
	 *
	 * @param flagComunicado 
	 *       <code>flagComunicado<code> a ser designado para Concessionaria.java
	 *
	 */
	public void setFlagComunicado(boolean flagComunicado) {
		this.flagComunicado = flagComunicado;
	}

	/** Método setter para a propriedade representante
	 *
	 * @param representante 
	 *       <code>representante<code> a ser designado para Concessionaria.java
	 *
	 */
	public void setRepresentante(Representante representante) {
		this.representante = representante;
	}

	/**
	 * Método setter para o campo bairro
	 * 
	 * @param bairro
	 *            String
	 * 
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	/**
	 * Método setter para o campo cep
	 * 
	 * @param cep
	 *            Long
	 * 
	 */
	public void setCep(Long cep) {
		this.cep = cep;
	}

	/**
	 * Método setter para o campo cidade
	 * 
	 * @param cidade
	 *            String
	 * 
	 */
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	/**
	 * Método setter para o campo cnpj
	 * 
	 * @param cnpj
	 *            Long
	 * 
	 */
	public void setCnpj(Long cnpj) {
		this.cnpj = cnpj;
	}

	/**
	 * Método setter para o campo endereco
	 * 
	 * @param endereco
	 *            String
	 * 
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	/**
	 * Método setter para o campo razaoSocial
	 * 
	 * @param razaoSocial
	 *            String
	 * 
	 */
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	/**
	 * Método setter para o campo telefone_1
	 * 
	 * @param telefone_1
	 *            String
	 * 
	 */
	public void setTelefone_1(String telefone_1) {
		this.telefone_1 = telefone_1;
	}

	/**
	 * Método setter para o campo telefone_2
	 * 
	 * @param telefone_2
	 *            String
	 * 
	 */
	public void setTelefone_2(String telefone_2) {
		this.telefone_2 = telefone_2;
	}

	/**
	 * Método setter para o campo lotes
	 * 
	 * @param lotes
	 *            List
	 * 
	 */
	public void setLotes(List lotes) {
		this.lotes = lotes;
	}

	/**
	 * Método setter para a propriedade regiaMoto
	 * 
	 * @param regiaMoto
	 *            <code>Long</code> a ser designado para regiaMoto.
	 * 
	 */
	public void setRegiaMoto(Long regiaMoto) {
		this.regiaMoto = regiaMoto;
	}

	/**
	 * Método setter para a propriedade regiaNautica
	 * 
	 * @param regiaNautica
	 *            <code>Long</code> a ser designado para regiaNautica.
	 * 
	 */
	public void setRegiaNautica(Long regiaNautica) {
		this.regiaNautica = regiaNautica;
	}

	/**
	 * Método setter para a propriedade flagBonificacao
	 * 
	 * @param flagBonificacao
	 *            <code>String</code> a ser designado para flagBonificacao.
	 * 
	 */
	public void setFlagBonificacao(String flagBonificacao) {
		this.flagBonificacao = flagBonificacao;
	}
	
	/**
	 * Método setter para a propriedade flagAprovacaoAutom
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
	
	/** Método setter para a propriedade flagSistema
	 *
	 * @param flagSistema String
	 */
	public void setFlagSistema(String flagSistema) {
		this.flagSistema = flagSistema;
	}
	
	/** Retorna a região da concessionária de acordo com a linha do produto
	 *  
	 * @param linha
	 * 	<code>Linha</code> - Entidade que representa alinha do produto
	 * 
	 * @return Long
	 * 	Código da região do produto
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
	 * Método getter para a propriedade uf
	 * @return  String de uf
	 */
	public String getUf() {
		return uf;
	}

	/**
	 * Método setter para a propriedade uf
	 * @param uf String
	 */
	public void setUf(String uf) {
		this.uf = uf;
	}

	/**
	 * Método getter para a propriedade estado
	 * @return  Estado de estado
	 */
	public Estado getEstado() {
		return estado;
	}

	/**
	 * Método setter para a propriedade estado
	 * @param estado Estado
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
}

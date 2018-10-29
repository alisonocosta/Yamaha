/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......Cliente.java
 *   .: Criação.....03 de abril, 12:41
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Entidade "Cliente".
 */
package br.com.yamaha.sistemagarantia.model;

import java.util.Date;

import br.com.resource.infra.model.EntityObject;

/** Entidade do sistema, em formato POJO, representando
 *  um objeto "Cliente" no sistema.
 *  
 *  @author Thiago Uriel M. Garcia
 */
public class Cliente extends EntityObject {

    //----[ Atributos de classe e instância ]-----------------------------------
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;  
    
    /** Constante para pessoa física */ 
    public static final String FISICA = "F";
    
    /** Constante para pessoa jurídica */
    public static final String JURIDICA = "J";
    
    /** Armazena um CNPJ, não formatado, do cliente. */
    private Long cnpj;
    
    /** Armazena um tipo de cliente, Jurídica- J ou Física - F. */
    private String tipoCliente;
    
    /** Código do cliente, utilizado pelo sistema. */
    private Long codigoCliente;
    
    /** CEP não formatado da residência do cliente. */
    private Long cep;
    
    /** Nome completo do cliente. */
    private String nome;
    
    /** Endereço no qual o cliente se localiza. */
    private String endereco;
    
    /** Bairro no qual o cliente se localiza. */
    private String bairro;
    
    /** Cidade no qual o cliente se localiza. */
    private String cidade;
    
    /** Estado no qual o cliente se localiza. */
    private String estado;
    
    /** Telefone residencial do cliente. */
    private String telefoneRes;
    
    /** Telefone comercial do cliente. */
    private String telefoneCom;
    
    /** Telefone celular do cliente. */
    private String telefoneCel;
    
    /** DDD do Telefone residencial do cliente. */
    private Long dddRes;
    
    /** DDD do Telefone comercial do cliente. */
    private Long dddCom;
    
    /** DDD do Telefone celular do cliente. */
    private Long dddCel;
    
    /** Email do cliente. */
    private String email;
    
    //Demanda 00003 - Resource IT
    /** Sexo do cliente. */
    private String sexo;
    
  //Demanda 00003 - Resource IT
    /** Data de nascimento do cliente. */
    private Date dataNascimento;
    
    //----[ Métodos Getter ]---------------------------------------------------
    
    /** Método getter para "bairro".
     *  @return
     *      <code>String</code>. O valor atual de bairro.
     */
    public String getBairro() {
    	
    	if ( !"".equals(this.bairro) && this.bairro != null )
    		return this.bairro.toUpperCase();
    	else
    		return "";
    }
    /** Método getter para "cep".
     *  @return
     *      <code>Long</code>. O valor atual de cep.
     */
    public Long getCep() {
        return this.cep;
    }
    /** Método getter para "cidade".
     *  @return
     *      <code>String</code>. O valor atual de cidade.
     */
    public String getCidade() {
        return this.cidade.toUpperCase();
    }
    /** Método getter para "cnpj".
     *  @return
     *      <code>Long</code>. O valor atual de cnpj.
     */
    public Long getCnpj() {
        return this.cnpj;
    }
    /** Método getter para "codigoCliente".
     *  @return
     *      <code>Long</code>. O valor atual de codigoCliente.
     */
    public Long getCodigoCliente() {
        return this.codigoCliente;
    }
    
    /** Método getter para "email".
     *  @return
     *      <code>String</code>. O valor atual de email.
     */
    public String getEmail() {
        return this.email;
    }
    
    /** Método getter para "endereco".
     *  @return
     *      <code>String</code>. O valor atual de endereco.
     */
    public String getEndereco() {
        return this.endereco.toUpperCase();
    }
    
    /** Método getter para "estado".
     *  @return
     *      <code>String</code>. O valor atual de estado.
     */
    public String getEstado() {
        return this.estado.toUpperCase();
    }
    
    /** Método getter para "nome".
     *  @return
     *      <code>String</code>. O valor atual de nome.
     */
    public String getNome() {
        return this.nome.toUpperCase();
    }
   
    /** Método getter para "telefoneCel".
     *  @return
     *      <code>String</code>. O valor atual de telefoneCel.
     */
    public String getTelefoneCel() {
        return this.telefoneCel;
    }
    
    /** Método getter para "telefoneCom".
     *  @return
     *      <code>String</code>. O valor atual de telefoneCom.
     */
    public String getTelefoneCom() {
        return this.telefoneCom;
    }
    
    /** Método getter para "telefoneRes".
     *  @return
     *      <code>String</code>. O valor atual de telefoneRes.
     */
    public String getTelefoneRes() {
        return this.telefoneRes;
    }
    
    /** Retorna o primeiro telefone disponível ou "não informado", caso
     *  nenhum telefone esteja cadastrado.
     *  <p>
     *  A ordem de prioridade é: Comercial > Celular > Residencial.
     *  
     *  @return
     *      O número de telefone, seguido com parênteses indicando sua 
     *      origem ("com.", "cel." ou "res."). 
     *      Caso nenhum telefone esteja disponível, o texto "não disponível"
     *      será retornado.
     */
    public String getTelefoneDisponivel() {
        
        if (this.getTelefoneCom() != null && !this.getTelefoneCom().equals("") ) {
            
            return this.getTelefoneCom() + (" (com.)");
            
        } else {
            
            if (this.getTelefoneCel() != null && !this.getTelefoneCel().equals("") ) {
                
                return this.getTelefoneCom() + (" (cel.)");
                
            } else {
                
                if (this.getTelefoneRes() != null && !this.getTelefoneRes().equals("") ) {
                    
                    return this.getTelefoneRes() + (" (res.)");
                    
                } else {
                    
                    return "Não informado.";
                    
                }
                
            }
            
        }
        
    }
    
    /** Método getter para a propriedade tipoCliente
	 *
	 *  @return String de tipoCliente
	 */
	public String getTipoCliente() {
		return tipoCliente.toUpperCase();
	}
    
	/**Método getter para a propriedade sexo. 
	 * 
	 * @return String de sexo
	 */
	public String getSexo() {
		return sexo;
	}
	
	/**Método getter para a propriedade dataNascimento.
	 * 
	 * @return Data de dataNascimento
	 */
	public Date getDataNascimento() {
		return dataNascimento;
	}
	
	
    //----[ Métodos Setter ]---------------------------------------------------
    
    /** Método setter para "bairro".
     *  @param entityId
     *      <code>String</code> a ser designado para bairro.
     */
    public void setBairro(String bairro) {
    	if ( !"".equals(bairro) && bairro != null)
    		this.bairro = bairro.toUpperCase();
    	else
    		this.bairro = bairro;
    }
    
    /** Método setter para "cep".
     *  @param entityId
     *      <code>Long</code> a ser designado para cep.
     */
    public void setCep(Long cep) {
        this.cep = cep;
    }
    
    /** Método setter para "cidade".
     *  @param entityId
     *      <code>String</code> a ser designado para cidade.
     */
    public void setCidade(String cidade) {
        this.cidade = cidade.toUpperCase();
    }
    
    /** Método setter para "cnpj".
     *  @param cnpj
     *      <code>Object</code> a ser designado para cnpj.
     */
    public void setCnpj(Object cnpj) {
    	
    	if ( cnpj instanceof Long )
    		this.cnpj = (Long)cnpj;
    	else
    		this.cnpj = new Long(0);
    }
    
    /** Método setter para "cnpj".
     *  @param entityId
     *      <code>Long</code> a ser designado para cnpj.
     */
    public void setCnpj(Long cnpj) {
    	this.cnpj = cnpj;
    }
    
    /** Método setter para "codigoCliente".
     *  @param entityId
     *      <code>Long</code> a ser designado para codigoCliente.
     */
    public void setCodigoCliente(Long codigoCliente) {
        this.codigoCliente = codigoCliente;
    }
    
    /** Método setter para "email".
     *  @param entityId
     *      <code>String</code> a ser designado para email.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /** Método setter para "endereco".
     *  @param entityId
     *      <code>String</code> a ser designado para endereco.
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco.toUpperCase();
    }
    
    /** Método setter para "estado".
     *  @param entityId
     *      <code>String</code> a ser designado para estado.
     */
    public void setEstado(String estado) {
        this.estado = estado.toUpperCase();
    }
    
    /** Método setter para "nome".
     *  @param entityId
     *      <code>String</code> a ser designado para nome.
     */
    public void setNome(String nome) {
        this.nome = nome.toUpperCase();
    }
    
    /** Método setter para "telefoneCel".
     *  @param entityId
     *      <code>String</code> a ser designado para telefoneCel.
     */
    public void setTelefoneCel(String telefoneCel) {
        this.telefoneCel = telefoneCel;
    }
    
    /** Método setter para "telefoneCom".
     *  @param entityId
     *      <code>String</code> a ser designado para telefoneCom.
     */
    public void setTelefoneCom(String telefoneCom) {
        this.telefoneCom = telefoneCom;
    }
    
    /** Método setter para "telefoneRes".
     *  @param entityId
     *      <code>String</code> a ser designado para telefoneRes.
     */
    public void setTelefoneRes(String telefoneRes) {
        this.telefoneRes = telefoneRes;
    }
	
	/** Método setter para a propriedade tipoCliente
	 *
	 * @param tipoCliente String
	 */
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente.toUpperCase();
	}
	/** Método getter para a propriedade dddCel
	 *
	 *  @return Long de dddCel
	 */
	public Long getDddCel() {
		return dddCel;
	}
	/** Método setter para a propriedade dddCel
	 *
	 * @param dddCel Long
	 */
	public void setDddCel(Long dddCel) {
		this.dddCel = dddCel;
	}
	/** Método getter para a propriedade dddCom
	 *
	 *  @return Long de dddCom
	 */
	public Long getDddCom() {
		return dddCom;
	}
	/** Método setter para a propriedade dddCom
	 *
	 * @param dddCom Long
	 */
	public void setDddCom(Long dddCom) {
		this.dddCom = dddCom;
	}
	/** Método getter para a propriedade dddRes
	 *
	 *  @return Long de dddRes
	 */
	public Long getDddRes() {
		return dddRes;
	}
	/** Método setter para a propriedade dddRes
	 *
	 * @param dddRes Long
	 */
	public void setDddRes(Long dddRes) {
		this.dddRes = dddRes;
	}
	
	/**Método setter para a propriedade sexo. 
	 * 
	 * @param sexo String
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	/**Método setter para a propriedade dataNascimento.
	 * 
	 * @param dataNascimento Date
	 */
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
}
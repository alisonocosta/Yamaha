/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......Cliente.java
 *   .: Cria��o.....03 de abril, 12:41
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descri��o...Entidade "Cliente".
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

    //----[ Atributos de classe e inst�ncia ]-----------------------------------
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;  
    
    /** Constante para pessoa f�sica */ 
    public static final String FISICA = "F";
    
    /** Constante para pessoa jur�dica */
    public static final String JURIDICA = "J";
    
    /** Armazena um CNPJ, n�o formatado, do cliente. */
    private Long cnpj;
    
    /** Armazena um tipo de cliente, Jur�dica- J ou F�sica - F. */
    private String tipoCliente;
    
    /** C�digo do cliente, utilizado pelo sistema. */
    private Long codigoCliente;
    
    /** CEP n�o formatado da resid�ncia do cliente. */
    private Long cep;
    
    /** Nome completo do cliente. */
    private String nome;
    
    /** Endere�o no qual o cliente se localiza. */
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
    
    //----[ M�todos Getter ]---------------------------------------------------
    
    /** M�todo getter para "bairro".
     *  @return
     *      <code>String</code>. O valor atual de bairro.
     */
    public String getBairro() {
    	
    	if ( !"".equals(this.bairro) && this.bairro != null )
    		return this.bairro.toUpperCase();
    	else
    		return "";
    }
    /** M�todo getter para "cep".
     *  @return
     *      <code>Long</code>. O valor atual de cep.
     */
    public Long getCep() {
        return this.cep;
    }
    /** M�todo getter para "cidade".
     *  @return
     *      <code>String</code>. O valor atual de cidade.
     */
    public String getCidade() {
        return this.cidade.toUpperCase();
    }
    /** M�todo getter para "cnpj".
     *  @return
     *      <code>Long</code>. O valor atual de cnpj.
     */
    public Long getCnpj() {
        return this.cnpj;
    }
    /** M�todo getter para "codigoCliente".
     *  @return
     *      <code>Long</code>. O valor atual de codigoCliente.
     */
    public Long getCodigoCliente() {
        return this.codigoCliente;
    }
    
    /** M�todo getter para "email".
     *  @return
     *      <code>String</code>. O valor atual de email.
     */
    public String getEmail() {
        return this.email;
    }
    
    /** M�todo getter para "endereco".
     *  @return
     *      <code>String</code>. O valor atual de endereco.
     */
    public String getEndereco() {
        return this.endereco.toUpperCase();
    }
    
    /** M�todo getter para "estado".
     *  @return
     *      <code>String</code>. O valor atual de estado.
     */
    public String getEstado() {
        return this.estado.toUpperCase();
    }
    
    /** M�todo getter para "nome".
     *  @return
     *      <code>String</code>. O valor atual de nome.
     */
    public String getNome() {
        return this.nome.toUpperCase();
    }
   
    /** M�todo getter para "telefoneCel".
     *  @return
     *      <code>String</code>. O valor atual de telefoneCel.
     */
    public String getTelefoneCel() {
        return this.telefoneCel;
    }
    
    /** M�todo getter para "telefoneCom".
     *  @return
     *      <code>String</code>. O valor atual de telefoneCom.
     */
    public String getTelefoneCom() {
        return this.telefoneCom;
    }
    
    /** M�todo getter para "telefoneRes".
     *  @return
     *      <code>String</code>. O valor atual de telefoneRes.
     */
    public String getTelefoneRes() {
        return this.telefoneRes;
    }
    
    /** Retorna o primeiro telefone dispon�vel ou "n�o informado", caso
     *  nenhum telefone esteja cadastrado.
     *  <p>
     *  A ordem de prioridade �: Comercial > Celular > Residencial.
     *  
     *  @return
     *      O n�mero de telefone, seguido com par�nteses indicando sua 
     *      origem ("com.", "cel." ou "res."). 
     *      Caso nenhum telefone esteja dispon�vel, o texto "n�o dispon�vel"
     *      ser� retornado.
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
                    
                    return "N�o informado.";
                    
                }
                
            }
            
        }
        
    }
    
    /** M�todo getter para a propriedade tipoCliente
	 *
	 *  @return String de tipoCliente
	 */
	public String getTipoCliente() {
		return tipoCliente.toUpperCase();
	}
    
	/**M�todo getter para a propriedade sexo. 
	 * 
	 * @return String de sexo
	 */
	public String getSexo() {
		return sexo;
	}
	
	/**M�todo getter para a propriedade dataNascimento.
	 * 
	 * @return Data de dataNascimento
	 */
	public Date getDataNascimento() {
		return dataNascimento;
	}
	
	
    //----[ M�todos Setter ]---------------------------------------------------
    
    /** M�todo setter para "bairro".
     *  @param entityId
     *      <code>String</code> a ser designado para bairro.
     */
    public void setBairro(String bairro) {
    	if ( !"".equals(bairro) && bairro != null)
    		this.bairro = bairro.toUpperCase();
    	else
    		this.bairro = bairro;
    }
    
    /** M�todo setter para "cep".
     *  @param entityId
     *      <code>Long</code> a ser designado para cep.
     */
    public void setCep(Long cep) {
        this.cep = cep;
    }
    
    /** M�todo setter para "cidade".
     *  @param entityId
     *      <code>String</code> a ser designado para cidade.
     */
    public void setCidade(String cidade) {
        this.cidade = cidade.toUpperCase();
    }
    
    /** M�todo setter para "cnpj".
     *  @param cnpj
     *      <code>Object</code> a ser designado para cnpj.
     */
    public void setCnpj(Object cnpj) {
    	
    	if ( cnpj instanceof Long )
    		this.cnpj = (Long)cnpj;
    	else
    		this.cnpj = new Long(0);
    }
    
    /** M�todo setter para "cnpj".
     *  @param entityId
     *      <code>Long</code> a ser designado para cnpj.
     */
    public void setCnpj(Long cnpj) {
    	this.cnpj = cnpj;
    }
    
    /** M�todo setter para "codigoCliente".
     *  @param entityId
     *      <code>Long</code> a ser designado para codigoCliente.
     */
    public void setCodigoCliente(Long codigoCliente) {
        this.codigoCliente = codigoCliente;
    }
    
    /** M�todo setter para "email".
     *  @param entityId
     *      <code>String</code> a ser designado para email.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /** M�todo setter para "endereco".
     *  @param entityId
     *      <code>String</code> a ser designado para endereco.
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco.toUpperCase();
    }
    
    /** M�todo setter para "estado".
     *  @param entityId
     *      <code>String</code> a ser designado para estado.
     */
    public void setEstado(String estado) {
        this.estado = estado.toUpperCase();
    }
    
    /** M�todo setter para "nome".
     *  @param entityId
     *      <code>String</code> a ser designado para nome.
     */
    public void setNome(String nome) {
        this.nome = nome.toUpperCase();
    }
    
    /** M�todo setter para "telefoneCel".
     *  @param entityId
     *      <code>String</code> a ser designado para telefoneCel.
     */
    public void setTelefoneCel(String telefoneCel) {
        this.telefoneCel = telefoneCel;
    }
    
    /** M�todo setter para "telefoneCom".
     *  @param entityId
     *      <code>String</code> a ser designado para telefoneCom.
     */
    public void setTelefoneCom(String telefoneCom) {
        this.telefoneCom = telefoneCom;
    }
    
    /** M�todo setter para "telefoneRes".
     *  @param entityId
     *      <code>String</code> a ser designado para telefoneRes.
     */
    public void setTelefoneRes(String telefoneRes) {
        this.telefoneRes = telefoneRes;
    }
	
	/** M�todo setter para a propriedade tipoCliente
	 *
	 * @param tipoCliente String
	 */
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente.toUpperCase();
	}
	/** M�todo getter para a propriedade dddCel
	 *
	 *  @return Long de dddCel
	 */
	public Long getDddCel() {
		return dddCel;
	}
	/** M�todo setter para a propriedade dddCel
	 *
	 * @param dddCel Long
	 */
	public void setDddCel(Long dddCel) {
		this.dddCel = dddCel;
	}
	/** M�todo getter para a propriedade dddCom
	 *
	 *  @return Long de dddCom
	 */
	public Long getDddCom() {
		return dddCom;
	}
	/** M�todo setter para a propriedade dddCom
	 *
	 * @param dddCom Long
	 */
	public void setDddCom(Long dddCom) {
		this.dddCom = dddCom;
	}
	/** M�todo getter para a propriedade dddRes
	 *
	 *  @return Long de dddRes
	 */
	public Long getDddRes() {
		return dddRes;
	}
	/** M�todo setter para a propriedade dddRes
	 *
	 * @param dddRes Long
	 */
	public void setDddRes(Long dddRes) {
		this.dddRes = dddRes;
	}
	
	/**M�todo setter para a propriedade sexo. 
	 * 
	 * @param sexo String
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	/**M�todo setter para a propriedade dataNascimento.
	 * 
	 * @param dataNascimento Date
	 */
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
}
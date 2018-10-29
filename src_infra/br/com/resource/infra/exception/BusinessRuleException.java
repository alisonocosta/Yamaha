/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Infraestrutura para aplicativos Java
 *   .: Objeto......BusinessRuleException.java
 *   .: Cria��o.....15 de fevereiro, 14:35
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descri��o...Exception lan�ada por erros de neg�cio.
 */
package br.com.resource.infra.exception;

/** Exception lan�ada por erros de neg�cio.
 * 
 *  @author Thiago Uriel M. Garcia
 */
public class BusinessRuleException extends BusinessException {

    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;    
    
    /** Construtor.
     *  <p>
     *  Construtor padr�o da classe.
     */
    public BusinessRuleException() {
        super();
    }

    /** Construtor.
     *  <p>
     *  Contrutor que recebe uma mensagem de erro e um objeto
     *  <code>Throwable</code> contendo o hist�rico de erros.
     *  
     *  @param message
     *    String de mensagem a ser lan�ado para verifica��o do erro.
     *  
     *  @param cause
     *    <code>Throwable</code> contendo o hist�rico do erro.
     */
    public BusinessRuleException(String message, Throwable cause) {
        super(message, cause);
    }

    /** Construtor.
     *  <p>
     *  Construtor que recebe uma mensagem de erro.
     * 
     *  @param message
     *    String de mensagem a ser lan�ado para verifica��o do erro.
     */
    public BusinessRuleException(String message) {
        super(message);
    }

    /** Construtor.
     *  <p>
     *  Construtor que recebe um <code>Throwable</code>, contendo
     *  o hist�rico de erros.
     *  
     *  @param cause
     *    <code>Throwable</code> contendo o hist�rico do erro.
     */
    public BusinessRuleException(Throwable cause) {
        super(cause);
    }    
    
}
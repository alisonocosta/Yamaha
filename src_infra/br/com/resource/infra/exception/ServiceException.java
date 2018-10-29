/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Infraestrutura para aplicativos Java
 *   .: Objeto......ServiceException.java
 *   .: Cria��o.....15 de fevereiro, 14:30
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descri��o...Exception lan�ada por erros na camada de servi�o.
 */
package br.com.resource.infra.exception;

/** Exception lan�ada por erros na camada de servi�o.
 *  
 *  @author Thiago Uriel M. Garcia
 */
public class ServiceException extends InfraException {
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;    
    
    /** Construtor.
     *  <p>
     *  Construtor padr�o da classe.
     */
    public ServiceException() {
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
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    /** Construtor.
     *  <p>
     *  Construtor que recebe uma mensagem de erro.
     * 
     *  @param message
     *    String de mensagem a ser lan�ado para verifica��o do erro.
     */
    public ServiceException(String message) {
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
    public ServiceException(Throwable cause) {
        super(cause);
    }
    
}
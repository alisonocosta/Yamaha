/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Infraestrutura para aplicativos Java
 *   .: Objeto......InfraException.java
 *   .: Cria��o.....15 de fevereiro, 14:30
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descri��o...Exception lan�ada por erros na camada de infraestrutura.
 */
package br.com.resource.infra.exception;

/** Exception lan�ada por erros na camada de infraestrutura,
 *  n�o relacionados a nenhuma das outras camadas.
 *  
 *  @author Thiago Uriel M. Garcia
 */
public class InfraException extends Exception {
    
    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;    
    
    /** Construtor.
     *  <p>
     *  Construtor padr�o da classe.
     */
    public InfraException() {
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
    public InfraException(String message, Throwable cause) {
        super(message, cause);
    }

    /** Construtor.
     *  <p>
     *  Construtor que recebe uma mensagem de erro.
     * 
     *  @param message
     *    String de mensagem a ser lan�ado para verifica��o do erro.
     */
    public InfraException(String message) {
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
    public InfraException(Throwable cause) {
        super(cause);
    }
    
}
/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Infraestrutura para aplicativos Java
 *   .: Objeto......ControllerException.java
 *   .: Criação.....14 de fevereiro, 02:03
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Exception lançada por erros na camada de controle.
 */
package br.com.resource.infra.exception;

/** Exception lançada por erros na camada de controle.
 *  
 *  @author Thiago Uriel M. Garcia
 */
public class ControllerException extends InfraException {

    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;    

    /** Construtor.
     *  <p>
     *  Construtor padrão da classe.
     */
    public ControllerException() {
        super();
    }

    /** Construtor.
     *  <p>
     *  Contrutor que recebe uma mensagem de erro e um objeto
     *  <code>Throwable</code> contendo o histórico de erros.
     *  
     *  @param message
     *    String de mensagem a ser lançado para verificação do erro.
     *  
     *  @param cause
     *    <code>Throwable</code> contendo o histórico do erro.
     */
    public ControllerException(String message, Throwable cause) {
        super(message, cause);
    }

    /** Construtor.
     *  <p>
     *  Construtor que recebe uma mensagem de erro.
     * 
     *  @param message
     *    String de mensagem a ser lançado para verificação do erro.
     */
    public ControllerException(String message) {
        super(message);
    }

    /** Construtor.
     *  <p>
     *  Construtor que recebe um <code>Throwable</code>, contendo
     *  o histórico de erros.
     *  
     *  @param cause
     *    <code>Throwable</code> contendo o histórico do erro.
     */
    public ControllerException(Throwable cause) {
        super(cause);
    }
    
}
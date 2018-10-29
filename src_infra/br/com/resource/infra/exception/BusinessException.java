/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Infraestrutura para aplicativos Java
 *   .: Objeto......BusinessException.java
 *   .: Criação.....15 de fevereiro, 14:30
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Exception lançada por erros na camada de negócios.
 */
package br.com.resource.infra.exception;

/** Exception lançada por erros na camada de negócios.
 *  
 *  @author Thiago Uriel M. Garcia
 */
public class BusinessException extends InfraException {
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;    
    
    /** Construtor.
     *  <p>
     *  Construtor padrão da classe.
     */
    public BusinessException() {
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
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    /** Construtor.
     *  <p>
     *  Construtor que recebe uma mensagem de erro.
     * 
     *  @param message
     *    String de mensagem a ser lançado para verificação do erro.
     */
    public BusinessException(String message) {
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
    public BusinessException(Throwable cause) {
        super(cause);
    }
    
}
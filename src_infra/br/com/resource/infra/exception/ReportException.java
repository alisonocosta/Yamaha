/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Infraestrutura para aplicativos Java
 *   .: Objeto......ReportException.java
 *   .: Criação.....15 de fevereiro, 14:30
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Exception lançada por erros na camada DAO.
 */
package br.com.resource.infra.exception;

/** Exception lançada por erros na camada DAO.
 *  
 *  @author Thiago Uriel M. Garcia
 */
public class ReportException extends InfraException {
    
    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;    
    
    /** Construtor.
     *  <p>
     *  Construtor padrão da classe.
     */
    public ReportException() {
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
    public ReportException(String message, Throwable cause) {
        super(message, cause);
    }

    /** Construtor.
     *  <p>
     *  Construtor que recebe uma mensagem de erro.
     * 
     *  @param message
     *    String de mensagem a ser lançado para verificação do erro.
     */
    public ReportException(String message) {
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
    public ReportException(Throwable cause) {
        super(cause);
    }
    
}
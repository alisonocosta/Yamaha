/*
 * Created on 23/06/2006
 *
 * ExpireSessionException.java
 */
package br.com.resource.infra.security.exception;

import java.lang.Exception;

/** Exception básica a ser lançada quando expira a sessão do usuário.
 *  
 *   
 *  Para a criação de uma exception simples, basta implementar
 *  os construtores de uma Exception mais genérica.
 * 
 *  @author edson.luiz
 */
public class InvalidSessionException extends Exception {
    
   
	private static final long serialVersionUID = 5758523735304477344L;

	/** Construtor padrão */
    public InvalidSessionException() {
        super();
    }
    
    /** Construtor.
     *  @param message   Mensagem a ser enviada, indicando
     *                   o erro ocorrido.
     */
    public InvalidSessionException(String message) {
        super(message);
    }

    /** Construtor.
     *  @param message  Mensagem a ser enviada, indicando
     *                  o erro ocorrido.
     * 
     *  @param cause  Throwable (base de toda Exception) 
     *                utilizada para exibir todo o stacktrace
     *                (histórico) de erros ocorridos e que geraram
     *                a presente exception.
     */
    public InvalidSessionException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /** Construtor.
     *  @param cause  Throwable (base de toda Exception) 
     *                utilizada para exibir todo o stacktrace
     *                (histórico) de erros ocorridos e que geraram
     *                a presente exception.
     */
    public InvalidSessionException(Throwable cause) {
        super(cause);
    }
}

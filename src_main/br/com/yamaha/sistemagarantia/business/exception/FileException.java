/**
 * 
 */
package br.com.yamaha.sistemagarantia.business.exception;

import br.com.resource.infra.exception.BusinessRuleException;

/**
 * @author Edson Luiz Sumensari
 *
 */
public class FileException extends BusinessRuleException {

    
	private static final long serialVersionUID = -4613022622867981045L;

	/**
     * 
     */
    public FileException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     */
    public FileException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     */
    public FileException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param cause
     */
    public FileException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

}

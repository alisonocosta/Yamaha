/**
 * 
 */
package br.com.yamaha.sistemagarantia.business.exception;

import br.com.resource.infra.exception.BusinessRuleException;

/**
 * @author Thiago U. M. Garcia
 *
 */
public class CotacaoException extends BusinessRuleException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 
     */
    public CotacaoException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     */
    public CotacaoException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     */
    public CotacaoException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param cause
     */
    public CotacaoException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

}

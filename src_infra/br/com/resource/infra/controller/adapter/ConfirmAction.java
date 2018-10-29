/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Infraestrutura para aplicativos Java
 *   .: Objeto......ConfirmAction.java
 *   .: Criação.....22 de maio, 08:25
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Classe básica para mensagens de confirmação.
 */
package br.com.resource.infra.controller.adapter;

import java.io.Serializable;

/** Classe básica para mensagens dwe confirmação */
public class ConfirmAction implements Serializable {

	/** Version ID padrão para serialização. */
	private static final long serialVersionUID = 1L;
	
	/** Constante de resposta positiva */
	public static final ConfirmAction YES = new ConfirmAction("yes");
	
	/** Constante de resposta negativa */
    public static final ConfirmAction NO  = new ConfirmAction("no");
    
    /** string enviado ao contrutor com a escolha do usuário */
    private String confirm;
    
    /** Construtor da classe */
    private ConfirmAction(String confirm) {
    	
        super();
        
        this.confirm = confirm;
        
    }
    
    /** Implementação de Equals 
     * 
     * @param <code>Object</code> 
     */
    public boolean equals(Object obj) {
       
    	if (!(obj instanceof ConfirmAction)) {
            return false;
        }
        
        return ((ConfirmAction)obj).confirm == confirm;
    }
    
    /** Implementação de toString */
    public String toString() {
    	
        return confirm;
        
    }
}

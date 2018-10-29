/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Infraestrutura para aplicativos Java
 *   .: Objeto......View Object.java
 *   .: Criação.....24 de fevereiro, 15:07
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Implementação básica de um View Object.
 */
package br.com.resource.infra.view;

import br.com.resource.infra.core.SystemObject;

/** Implementação básica de um <b>View Object</b>
 * 
 *  @author Thiago Uriel M. Garcia
 */
public class ViewObject implements SystemObject {

    /** Version ID padrão para serialização. */
    private static final long serialVersionUID = 1L;    
    
    /** Sobrescrita do método <code>equals</code>. 
     *  <p/>  
     *  Aqui, uma entidade será igual a outra <b>sempre</b> que ambas forem 
     *  entidades do mesmo tipo e possuirem o mesmo ID. Este é justamente o 
     *  conceito de comparação de registros de banco de dados: Um registro é 
     *  igual a outro se forem da mesma tabela e possuirem a mesma chave.
     * 
     *  @param  java.lang.Object
     *      Um objeto de referência com o qual esta entidade
     *      será comparada.
     * 
     *  @return boolean 
     *      O resultado da comparação entre os objetos.
     */
    public boolean equals(Object object) {

        // Averiguação real de igualdade:
        boolean isSameClass = ( this.getClass() == object.getClass() );
        boolean isSameId    = false;
        if ( isSameClass ) {
            isSameId = ( this.hashCode() == ( (ViewObject) object ).hashCode() );
        } else return false; 

        if ( isSameClass && isSameId ) return true;

        // Se não retornou até agora, então é falso! 
        return false;

    }

    /** Sobrescrita do método <code>toString</code>. 
     *  <p/>
     *  Sua função é fornecer uma String "identificável" que de alguma forma 
     *  represente de forma única o objeto corrente.
     *  <p/>
     *  Contudo, está conversão também não é adequada ao propósitode uma entidade. 
     *  Por padrão, este método retornaria o nome da classe utilizada e um 
     *  identificador utilizado pela Virtual Machine que executa a aplicação.
     *  <p/>
     *  Aqui, será utilizada uma implementação que retorna a classe usada (como 
     *  o original), porém retorna o identificador da entidade (hashmap).
     * 
     *  @return java.lang.String  
     *      Uma string identificadora desta entidade.
     */
    public String toString() {

        return ( this.getClass().toString() + ": " + hashCode() );

    }    
    
}
/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Infraestrutura para aplicativos Java
 *   .: Objeto......View Object.java
 *   .: Cria��o.....24 de fevereiro, 15:07
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descri��o...Implementa��o b�sica de um View Object.
 */
package br.com.resource.infra.view;

import br.com.resource.infra.core.SystemObject;

/** Implementa��o b�sica de um <b>View Object</b>
 * 
 *  @author Thiago Uriel M. Garcia
 */
public class ViewObject implements SystemObject {

    /** Version ID padr�o para serializa��o. */
    private static final long serialVersionUID = 1L;    
    
    /** Sobrescrita do m�todo <code>equals</code>. 
     *  <p/>  
     *  Aqui, uma entidade ser� igual a outra <b>sempre</b> que ambas forem 
     *  entidades do mesmo tipo e possuirem o mesmo ID. Este � justamente o 
     *  conceito de compara��o de registros de banco de dados: Um registro � 
     *  igual a outro se forem da mesma tabela e possuirem a mesma chave.
     * 
     *  @param  java.lang.Object
     *      Um objeto de refer�ncia com o qual esta entidade
     *      ser� comparada.
     * 
     *  @return boolean 
     *      O resultado da compara��o entre os objetos.
     */
    public boolean equals(Object object) {

        // Averigua��o real de igualdade:
        boolean isSameClass = ( this.getClass() == object.getClass() );
        boolean isSameId    = false;
        if ( isSameClass ) {
            isSameId = ( this.hashCode() == ( (ViewObject) object ).hashCode() );
        } else return false; 

        if ( isSameClass && isSameId ) return true;

        // Se n�o retornou at� agora, ent�o � falso! 
        return false;

    }

    /** Sobrescrita do m�todo <code>toString</code>. 
     *  <p/>
     *  Sua fun��o � fornecer uma String "identific�vel" que de alguma forma 
     *  represente de forma �nica o objeto corrente.
     *  <p/>
     *  Contudo, est� convers�o tamb�m n�o � adequada ao prop�sitode uma entidade. 
     *  Por padr�o, este m�todo retornaria o nome da classe utilizada e um 
     *  identificador utilizado pela Virtual Machine que executa a aplica��o.
     *  <p/>
     *  Aqui, ser� utilizada uma implementa��o que retorna a classe usada (como 
     *  o original), por�m retorna o identificador da entidade (hashmap).
     * 
     *  @return java.lang.String  
     *      Uma string identificadora desta entidade.
     */
    public String toString() {

        return ( this.getClass().toString() + ": " + hashCode() );

    }    
    
}
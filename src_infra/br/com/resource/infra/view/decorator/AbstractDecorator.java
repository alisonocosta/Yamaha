/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Infraestrutura para aplicativos Java
 *   .: Objeto......AbstractDecorator.java
 *   .: Cria��o.....02 de maio, 15:12
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descri��o...Objeto decorador padr�o.
 */
package br.com.resource.infra.view.decorator;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.ColumnDecorator;
import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

/** Implementa��o b�sica do <i>pattern</i> Decorator. 
 *  <p>
 *  Este objeto � padronizado para funcionar junto ao sistema
 *  de forma independente por�m tamb�m � preparado para atuar
 *  como um decorador do componente <b>DisplayTag</b>.
 *  
 *  @author Thiago Uriel M. Garcia
 */
public abstract class AbstractDecorator implements ColumnDecorator, DisplaytagColumnDecorator {

    /** M�todo padr�o para decora��o. Recebe um objeto a ser
     *  decorado de acordo com cada classe especializada.
     *  <p>
     *  Este m�todo � padr�o para a maioria dos cen�rios, por�m
     *  � tamb�m uma implementa��o marcada como <b>deprecated</b>
     *  pela biblioteca DisplayTag.
     *  
     *  @param target
     *      <code>Object</code> a ser decorado.
     *  
     *  @returns
     *      <code>String</code> formatada de acordo com o tipo de
     *      especializa��o da classe implementada.
     *      
     *  @throws
     *      Se houverem erros no processo, ser�o lan�ados como uma
     *      exception. Por hora, enquanto suportada, a Exception 
     *      lan�ada � da biblioteca <code>DisplayTag</code>.
     */
    public abstract String decorate(Object target) throws DecoratorException;

    /** M�todo utilizado em substitui��o ao <code>decorate(Object): String</code>. 
     *  
     *  @param target
     *      <code>Object</code> a ser decorado.
     *  @param context
     *      <code>PageContext</code> no qual a DisplayTag se encontra.
     *  @param media
     *      Tipo de m�dia utilizado para visualizar a tabela.
     *  
     *  @returns
     *      <code>Object</code> formatado de acordo com o tipo de
     *      especializa��o da classe implementada.
     *      
     *  @throws
     *      Se houverem erros no processo, ser�o lan�ados como uma
     *      exception. Por hora, enquanto suportada, a Exception 
     *      lan�ada � da biblioteca <code>DisplayTag</code>.
     */
    public abstract Object decorate(Object target, PageContext context, MediaTypeEnum media) throws DecoratorException;

}
/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Infraestrutura para aplicativos Java
 *   .: Objeto......AbstractDecorator.java
 *   .: Criação.....02 de maio, 15:12
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Objeto decorador padrão.
 */
package br.com.resource.infra.view.decorator;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.ColumnDecorator;
import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

/** Implementação básica do <i>pattern</i> Decorator. 
 *  <p>
 *  Este objeto é padronizado para funcionar junto ao sistema
 *  de forma independente porém também é preparado para atuar
 *  como um decorador do componente <b>DisplayTag</b>.
 *  
 *  @author Thiago Uriel M. Garcia
 */
public abstract class AbstractDecorator implements ColumnDecorator, DisplaytagColumnDecorator {

    /** Método padrão para decoração. Recebe um objeto a ser
     *  decorado de acordo com cada classe especializada.
     *  <p>
     *  Este método é padrão para a maioria dos cenários, porém
     *  é também uma implementação marcada como <b>deprecated</b>
     *  pela biblioteca DisplayTag.
     *  
     *  @param target
     *      <code>Object</code> a ser decorado.
     *  
     *  @returns
     *      <code>String</code> formatada de acordo com o tipo de
     *      especialização da classe implementada.
     *      
     *  @throws
     *      Se houverem erros no processo, serão lançados como uma
     *      exception. Por hora, enquanto suportada, a Exception 
     *      lançada é da biblioteca <code>DisplayTag</code>.
     */
    public abstract String decorate(Object target) throws DecoratorException;

    /** Método utilizado em substituição ao <code>decorate(Object): String</code>. 
     *  
     *  @param target
     *      <code>Object</code> a ser decorado.
     *  @param context
     *      <code>PageContext</code> no qual a DisplayTag se encontra.
     *  @param media
     *      Tipo de mídia utilizado para visualizar a tabela.
     *  
     *  @returns
     *      <code>Object</code> formatado de acordo com o tipo de
     *      especialização da classe implementada.
     *      
     *  @throws
     *      Se houverem erros no processo, serão lançados como uma
     *      exception. Por hora, enquanto suportada, a Exception 
     *      lançada é da biblioteca <code>DisplayTag</code>.
     */
    public abstract Object decorate(Object target, PageContext context, MediaTypeEnum media) throws DecoratorException;

}
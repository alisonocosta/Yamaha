/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......CpfCnpjDecorator.java
 *   .: Criação.....02 de maio, 15;09
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Objeto decorador para o formato de CPF e CNPJ.
 */
package br.com.yamaha.sistemagarantia.view.decorator;

import javax.servlet.jsp.PageContext;

import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

import br.com.resource.infra.view.decorator.AbstractDecorator;

/** Implementação do <i>pattern</i> Decorator para CPF e CNPJ. 
 * 
 *  @author Thiago Uriel M. Garcia
 */
public class CpfCnpjDecorator extends AbstractDecorator {

    /** Formata um CPF ou CNPJ.
     *  <p>
     *  Formatos:
     *  CPF: ###.###.###-## (11 dígitos)
     *  CNPJ: ##.###.###/####-## (14 dígitos)
     */
    public String decorate(Object target) throws DecoratorException {

        String value = target.toString();
        
        if ( value.length() < 11 ) {
            // Temos um CPF com menos de 9 dígitos. 
            // Adicionamos zeros no começo e aplicamos a máscara: ###.###.###-##
            
            StringBuffer sb = new StringBuffer();
            for (int i=0; i<11-value.length(); i++) {
            
                sb.append("0");
                
            }
            sb.append(value);
            
            return sb.toString().replaceAll("([0-9]{3})([0-9]{3})([0-9]{3})([0-9]{2})", "$1\\.$2\\.$3-$4");
            
        } else if ( value.length() == 11 ) {
            // Temos um CPF de 11 dígitos. 
            // Aplicamos a máscara: ###.###.###-##
            return value.replaceAll("([0-9]{3})([0-9]{3})([0-9]{3})([0-9]{2})", "$1\\.$2\\.$3-$4");

        } else if ( value.length() > 11 && value.length() < 14 ) {
            // Temos um CNPJ com menos 14 dígitos.
            // Adicionamos zeros no começo e aplicamos a máscara: ##.###.###/####-##
            
            StringBuffer sb = new StringBuffer();
            for (int i=0; i<14-value.length(); i++) {
            
                sb.append("0");
                
            }
            sb.append(value);
            
            return sb.toString().replaceAll("([0-9]{2})([0-9]{3})([0-9]{3})([0-9]{4})([0-9]{2})", "$1\\.$2\\.$3/$4-$5");            
            
        } else if ( value.length() == 14 ) {
            // Temos um CNPJ de 14 dígitos. 
            // Aplicamos a máscara: ##.###.###/####-##
            return value.replaceAll("([0-9]{2})([0-9]{3})([0-9]{3})([0-9]{4})([0-9]{2})", "$1\\.$2\\.$3/$4-$5");            

        } else if ( value.length() == 15 ) {
            // Temos um CNPJ de 15 dígitos. 
            // Aplicamos a máscara: ###.###.###/####-##
            return value.replaceAll("([0-9]{3})([0-9]{3})([0-9]{3})([0-9]{4})([0-9]{2})", "$1\\.$2\\.$3/$4-$5");            
            
        } else {
            return "??";
        }

    }    

    public Object decorate(Object target, PageContext context, MediaTypeEnum media) throws DecoratorException {
        
        return decorate(target);

    }

}
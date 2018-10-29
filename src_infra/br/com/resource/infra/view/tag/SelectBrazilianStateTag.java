/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......ClienteBusiness.java
 *   .: Cria��o.....17 de abril, 14:41
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Tag para alimentar um objeto select com os estados do Brasil.
 */
package br.com.resource.infra.view.tag;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;

import br.com.resource.infra.view.tag.OptionListTag;

/** Tag respons�vel por listar todos os estados
 *  brasileiros em elementos "option" (html).
 *
 *  @author edson.luiz
 */
public class SelectBrazilianStateTag extends OptionListTag {

	private static final long serialVersionUID = 1L;

	public Map getOptionList() throws JspException {
        
        LinkedHashMap map = new LinkedHashMap();
        
        map.put("AC", "Acre");
        map.put("AL", "Alagoas");
        map.put("AP", "Amap�");
        map.put("AM", "Amazonas");
        map.put("BA", "Bahia");
        map.put("CE", "Cear�");
        map.put("DF", "Distrito Federal");
        map.put("ES", "Esp�rito Santo");
        map.put("GO", "Goias");
        map.put("MA", "Maranh�o");
        map.put("MT", "Mato Grosso");
        map.put("MS", "Mato Grosso do Sul");
        map.put("MG", "Minas Gerais");   
        map.put("PA", "Par�");
        map.put("PB", "Para�ba");
        map.put("PR", "Paran�");
        map.put("PE", "Pernambuco");
        map.put("PI", "Piau�");
        map.put("RJ", "Rio de Janeiro");
        map.put("RN", "Rio Grande do Norte");
        map.put("RS", "Rio Grande do Sul");
        map.put("RO", "Rondonia");
        map.put("RR", "Roraima");
        map.put("SC", "Santa Catarina");
        map.put("SP", "S�o Paulo");
        map.put("SE", "Sergipe");
        map.put("TO", "Tocantins");
        
        return map;
        
    }
}

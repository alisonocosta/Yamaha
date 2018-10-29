/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......SelectRevisaoTag.java
 *   .: Cria��o.....22 de maio, 10:34
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Tag para alimentar um objeto select com as revis�o poss�veis para um chassi.
 */
package br.com.yamaha.sistemagarantia.view.tag;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;

import br.com.resource.infra.view.tag.OptionListTag;
import br.com.yamaha.sistemagarantia.model.Revisao;

/** Tag para alimentar um objeto select com as revis�o poss�veis para um chassi.
 *
 *  @author edson.luiz
 */
public class SelectRevisaoTag extends OptionListTag {
	
	private static final long serialVersionUID = 1L;
	
	/** Lista de revis�es */
	private List listRevisao;

	/** Retorna um Map com N�mero da revis�o  e Descri��o 
	 *  A partir de uma Cole��o de objetos enviados
	 */
    public Map getOptionList() throws JspException {
        
        LinkedHashMap map = new LinkedHashMap();
        
        Iterator iter = listRevisao.iterator();
        
        while ( iter.hasNext() ) {
        	
        	Revisao revisao = new Revisao();
        	
        	revisao = (Revisao) iter.next();
        	
        	map.put(revisao.getNumeroRevisao(), revisao.getDescricao());        	
        	
        }
        
        return map;
        
    }

	/** M�todo setter para a propriedade listRevisao
	 *
	 * @param listRevisao 
	 *           <code>Object</code> a ser designado para listRevisao.
	 * 
	 */
	public void setListRevisao(Object listRevisao) {
		if (listRevisao != null )
			this.listRevisao = (List)listRevisao;
		else
			this.listRevisao = new ArrayList();
	}
    
    
}

/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......ClienteBusiness.java
 *   .: Cria��o.....17 de abril, 14:42
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Tag b�sica para criar listas de valores.
 */
package br.com.resource.infra.view.tag;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/** Tag b�sica para criar listas de valores.
 *  Cuida de funcionalidades como Id selecionado
 *  e sa�da de dados para a tela.
 *
 * @author edson.luiz
 */
public abstract class OptionListTag extends TagSupport { 

    private static Log log;
    protected String selectedId;
    protected String showBlankLine;
    
    
    public OptionListTag(){
        selectedId = new String();
        showBlankLine = "yes";
    }
    
    
    /** Constr�i a tag "option" checando se o
     *  item inserido deve ou n�o ser marcado
     *  como selecionado.
     *  
     *  @param currentId    String. Id sendo listado atualmente.   
     *  @param currentLabel String. Label a ser inserida na op��o corrente.
     *  
     *  @return String   A tag completa, a ser constru�da.    
     */
    protected String buildOption(String currentId, String currentLabel)
    {
        StringBuffer option = new StringBuffer();
        String selectedOption = !selectedId.equals("null") && selectedId.length() != 0 ? selectedId : null;
        option.append("<option value='" + currentId + "'" + (currentId.equals(selectedOption) ? " selected" : "") + ">");
        option.append(currentLabel);
        option.append("</option>");
        return option.toString();
    }
    
    /** M�todo que executa quando a tag apresenta
     *  seu final. No caso, aqui colocaremos nossa
     *  l�gica, pois � aqui que ela dever� ser
     *  executada, uma vez que pode esperar por
     *  outros par�metros de entrada.
     * 
     *  @return  Indica��o dizendo que o processamento
     *           da p�gina dever� ser ignorado ou uma dizendo
     *           que dever� contionuar normalmente.
     * 
     *  @throws  Caso haja algum erro na execu��o do JSP, esta
     *           exception ser� lan�ada.
     */
    public int doStartTag()throws JspException{
        try {
            JspWriter out = pageContext.getOut();
            Map optionList = getOptionList();
            Iterator optionIdIt = optionList.keySet().iterator();
            Iterator optionLabelIt = optionList.values().iterator();
            if(showBlankLine.equalsIgnoreCase("yes"))
                out.println("<option></option>");
            
            int i = 0;
            while (optionIdIt.hasNext()) {
                i++;
                out.println(
                        buildOption(
                                optionIdIt.next().toString(),
                                optionLabelIt.next().toString()
                        )
                );                
            }
            
        }catch(Exception e){
            throw new JspException(e.getMessage());
        }
        return 0;
    }   
    
    /** M�todo a ser implementado por outras tags
     * 
     *  @return Map   Map utilizado para formar as op��es da tag.
     * 
     *  @throws Exception  Caso haja algum problema, uma Exception ser�
     *                     lan�ada.
     */
    public abstract Map getOptionList() throws Exception;    
    
    /** Determina qual o ID selecionado para
     *  que este seja marcado na sa�da da tag.
     * 
     *  @param selectedId   O valor do ID a ser marcado.
     */
    public void setSelectedId(Object selectedId) {
        
        this.selectedId = String.valueOf(selectedId);
        
    }    
    
    public void setShowBlankLine(Object showBlankLine)
    {
        this.showBlankLine = String.valueOf(showBlankLine);
    }
    
    static 
    {
        log = LogFactory.getLog(br.com.resource.infra.view.tag.OptionListTag.class);
    }
    
}

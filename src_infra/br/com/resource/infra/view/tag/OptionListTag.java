/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......ClienteBusiness.java
 *   .: Criação.....17 de abril, 14:42
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Tag básica para criar listas de valores.
 */
package br.com.resource.infra.view.tag;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/** Tag básica para criar listas de valores.
 *  Cuida de funcionalidades como Id selecionado
 *  e saída de dados para a tela.
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
    
    
    /** Constrói a tag "option" checando se o
     *  item inserido deve ou não ser marcado
     *  como selecionado.
     *  
     *  @param currentId    String. Id sendo listado atualmente.   
     *  @param currentLabel String. Label a ser inserida na opção corrente.
     *  
     *  @return String   A tag completa, a ser construída.    
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
    
    /** Método que executa quando a tag apresenta
     *  seu final. No caso, aqui colocaremos nossa
     *  lógica, pois é aqui que ela deverá ser
     *  executada, uma vez que pode esperar por
     *  outros parâmetros de entrada.
     * 
     *  @return  Indicação dizendo que o processamento
     *           da página deverá ser ignorado ou uma dizendo
     *           que deverá contionuar normalmente.
     * 
     *  @throws  Caso haja algum erro na execução do JSP, esta
     *           exception será lançada.
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
    
    /** Método a ser implementado por outras tags
     * 
     *  @return Map   Map utilizado para formar as opções da tag.
     * 
     *  @throws Exception  Caso haja algum problema, uma Exception será
     *                     lançada.
     */
    public abstract Map getOptionList() throws Exception;    
    
    /** Determina qual o ID selecionado para
     *  que este seja marcado na saída da tag.
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

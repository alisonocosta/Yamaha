/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Infraestrutura para aplicativos Java
 *   .: Objeto......DateUtils.java
 *   .: Cria��o.....26 de Abril, 16:01
 *   .: Autor.......Thiago Uriel M. Garcia
 *                  Edson Luiz Sumensari
 *   .: Descri��o...Tag para Montagem do Calend�rio.
 */
package br.com.resource.infra.view.tag;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

/** Tag respons�vel por exibir um campo de data
 *  ao lado de um bot�o que disponibiliza um 
 *  calend�rio.
 *
 *  @author Thiago Uriel M. Garcia
 *  @author Edson Luiz Sumensari
 */
public class InputDateTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

	/** Log utilizado para sa�da de informa��es desta classe. */
	//private static Log log = LogFactory.getLog(InputDateTag.class);    
    
    /** Formato de data a ser utilizado. Este campo
     *  n�o � obrigat�rio e seu formato padr�o �
     *  MM/dd/yyyy.
     */
    private String datePattern = new String("dd/MM/yyyy");
    
    /** �cone a ser exibido ao lado do campo, no 
     *  qual o usu�rio poder� clicar para exibir
     *  o calend�rio.
     */
    private String icon;
    
    /** Representa o atributo "alt" da tag de
     *  imagens. Este valor n�o � obrigat�rio
     *  e caso n�o seja enviado, nada ser�
     *  exibido.
     */
    private String iconDesc = new String("");
    
    /** Labels utilizados para os nomes dos meses.
     *  Devem ser separados por "|".
     */
    private String monthLabels;
    
    /** Headers utilizados para a nomenclatura dos
     *  dias da semana. Devem ser separados por "|".
     */
    private String dayHeaders;
    
    /** Dia do m�s no qual a data dever� ser 
     *  marcada inicialmente. Este campo n�o � obrigat�rio
     *  e seu valor inicial � <code>1</code>.
     */
    private String startDay = new String("1");
    
    /** Nome a ser utilizado para a inscri��o "hoje"
     *  no calend�rio.
     */
    private String todayLabel;
    
    /** Tamanho do campo a ser utilizado na renderiza��o
     *  do elemento HTML. Este campo n�o � obrigat�rio.
     */
    private String fieldLength = new String("12");
    
    /** Nome do campo de entrada. */
    private String fieldName;
    
    /** Nome do estilo a ser adotado. Este campo
     *  n�o � obrigat�rio.
     */
    private String fieldClass = new String("");
    
    /** Indica se este campo poder� ser manualmente 
     *  modificado pelo usu�rio. Este campo n�o � obrigat�rio
     *  e seu valor padr�o � <code>false</code>.
     */
    private String readOnly = "false";
    
    /** Indica se este campo poder� ser manualmente 
     *  modificado pelo usu�rio. Este campo n�o � obrigat�rio
     *  e seu valor padr�o � <code>enabled</code>.
     */
    private String disabled = "";
    
    /** M�todo javascript que deve ser chamado  no evento onBlur
     */
    private String onBlurMethod = "";
    
    /** Par�metro do campo para ordem de tabula��o
     */
    private String tabIndex = "";
    
    /**
     * Evento acionado ao solta uma tecla
     */
    private String onKeyUp = "";
    
    /**
     * Par�metro que indica o valor de maxlength do campo data
     */
    private String maxLength = new String("10");
    
    /** Quantidade de anos listados. Este campo
     *  n�o � obrigat�rio.
     */
    private String fieldYears = new String("4");
    
    public int doEndTag() throws JspException {
        
        try {
            
	        JspWriter   out  = pageContext.getOut();
	        BodyContent body = this.getBodyContent();
	        
	        String content = body.getString().trim();
	        
	        String calendarName = fieldName + "_cal";
	        String anchorName   = fieldName + "_anch";
	        String jsName       = fieldName + "_js";
	        String divName      = fieldName + "_div";
	        String isReadOnly   = this.readOnly.equalsIgnoreCase("true") ? "readonly='readonly'" : "";
	        String disabled     = this.disabled.equalsIgnoreCase("true") ? "disabled='disable'" : "";
	        String hasTabIndex  = "".equalsIgnoreCase(this.tabIndex) ? "" : "tabindex = " + this.tabIndex;
	        
	        StringBuffer sb = new StringBuffer();
	        
	        sb.append("<script language=\"JavaScript\" src=\"/ym_sistema_garantia/scripts/Calendar/CalendarPopup.js\"></script> \n");
	        sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"/ym_sistema_garantia/css/calendar/calendar.css\"></link> \n");
	        //sb.append("<script language=\"JavaScript\" src=\"/ym_sistema_garantia_ccs/scripts/Calendar/CalendarPopup.js\"></script> \n");
	        //sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"/ym_garantia_ccs/css/calendar/calendar.css\"></link> \n");
	        sb.append("<script language=\"JavaScript\" id=\"" + jsName + "\"> \n");
	        sb.append("var " + calendarName + " = new CalendarPopup(\"" + divName + "\"); \n");
	        sb.append(calendarName + ".setMonthNames(" + parseStringValues(this.monthLabels) + "); \n");
	        sb.append(calendarName + ".setDayHeaders(" + parseStringValues(this.dayHeaders) + "); \n");
	        sb.append(calendarName + ".setWeekStartDay(" + this.startDay + "); \n");
	        sb.append(calendarName + ".setTodayText(\"" + this.todayLabel + "\"); \n");
	        sb.append(calendarName + ".showNavigationDropdowns(); \n");
	        sb.append(calendarName + ".setCssPrefix(\"WM\"); \n");
	        sb.append(calendarName + ".setYearSelectStartOffset(" + this.fieldYears + "); \n");
	        
	        sb.append("</script> \n");
	        
	        sb.append("<input type=\"text\" name=\""+ this.fieldName + "\" maxlength=\"" + maxLength + "\" size=\"" + this.fieldLength + "\" class=\"" + fieldClass +  "\" " + isReadOnly + " value=\"" + content + "\"   " + disabled + " onblur=\"" + onBlurMethod + "\" OnKeyUp=\"" + onKeyUp + "\"/> ");
	        sb.append("<a href=\"#\" onClick=\"" + calendarName + ".select(document.forms[0]." + this.fieldName + ",'" + anchorName + "','" + this.datePattern + "');document.forms[0]." + this.fieldName + ".focus(); return false;\" name=\"" + anchorName + "\" " + hasTabIndex + "  id=\"" + anchorName + "\">");
	        sb.append("<img src=\"" + this.icon + "\" alt=\"" + this.iconDesc + "\" align=\"absmiddle\" border=\"0\"/>");
	        sb.append("</a>");
	        
	        sb.append("<div id=\"" + divName + "\" style=\"position:absolute;visibility:hidden;background-color:white;layer-background-color:white;\"></div>");

            out.println(sb.toString());
            
        } catch (IOException ioExp) {
            
            throw new JspException("Erro criando tag de datas: " + ioExp);
        }
        
        return EVAL_PAGE;
        
    }
    
    /** M�todo utilit�rio, respons�vel por quebrar uma
     *  String e format�-la da forma esperada
     *  pela API JavaScript.
     *  
     *  @param rawData   Valor enviado pela p�gina JSP
     * 
     *  @return   Valor adequado a ser enviado para a API
     *            Javascript.
     */
    private String parseStringValues(String rawData) {
        
        // Primeiro criamos um StringBuffer que armazenar�
        // toda a String e tamb�m criamos um tokenizer a partir
        // da String de meses recebida, indicando que esta
        // dever� ser separada por "|".
        StringBuffer    result = new StringBuffer();
        StringTokenizer st     = new StringTokenizer(rawData, "|");
        
        // Agora iteramos por todos os tokens e organizamos
        // a string a ser devolvida.
        int count = 0;
        int limit = st.countTokens();
        while (st.hasMoreTokens()) {
            
            count++;
            
            result.append("'" + st.nextToken() + "'");
            
            // Se n�o for o �ltimo elemento, devemos
            // inserir uma v�rgula.
            if (count != limit)
                result.append(",");
            
        }
        
        return result.toString();
        
    }
    
    /** M�todo setter para a propriedade "icon".
     *  @param description Novo valor da propriedade "icon".
     */
    public void setIcon(Object icon) {
        this.icon = icon.toString();
    }    
    
    /** M�todo setter para a propriedade "dayHeaders".
     *  @param description Novo valor da propriedade "dayHeaders".
     */
    public void setDayHeaders(Object dayHeaders) {
        this.dayHeaders = dayHeaders.toString();
    }    
    
    /** M�todo setter para a propriedade "monthLabels".
     *  @param description Novo valor da propriedade "monthLabels".
     */
    public void setMonthLabels(Object monthLabels) {
        this.monthLabels = monthLabels.toString();
    }

    /** M�todo setter para a propriedade "readOnly".
     *  @param description Novo valor da propriedade "readOnly".
     */
    public void setReadOnly(Object readOnly) {
        this.readOnly = readOnly.toString();
    }
    
    /** M�todo setter para a propriedade "startDay".
     *  @param description Novo valor da propriedade "startDay".
     */
    public void setStartDay(Object startDay) {
        this.startDay = startDay.toString();
    }
    
    /** M�todo setter para a propriedade "datePattern".
     *  @param description Novo valor da propriedade "datePattern".
     */
    public void setDatePattern(Object datePattern) {
        this.datePattern = datePattern.toString();
    }    

    /** M�todo setter para a propriedade "todayLabel".
     *  @param description Novo valor da propriedade "todayLabel".
     */
    public void setTodayLabel(Object todayLabel) {
        this.todayLabel = todayLabel.toString();
    }    

    /** M�todo setter para a propriedade "iconDesc".
     *  @param description Novo valor da propriedade "iconDesc".
     */
    public void setIconDesc(Object iconDesc) {
        this.iconDesc = iconDesc.toString();
    }
    
    /** M�todo setter para a propriedade "fieldClass".
     *  @param description Novo valor da propriedade "fieldClass".
     */
    public void setFieldClass(Object fieldClass) {
        this.fieldClass = fieldClass.toString();
    } 
    
    /** M�todo setter para a propriedade "datePattern".
     *  @param description Novo valor da propriedade "datePattern".
     */
    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }
    
    /** M�todo setter para a propriedade "dayHeaders".
     *  @param description Novo valor da propriedade "dayHeaders".
     */
    public void setDayHeaders(String dayHeaders) {
        this.dayHeaders = dayHeaders;
    }
    
    /** M�todo setter para a propriedade "fieldClass".
     *  @param description Novo valor da propriedade "fieldClass".
     */
    public void setFieldClass(String fieldClass) {
        this.fieldClass = fieldClass;
    }
    
    /** M�todo setter para a propriedade "fieldLength".
     *  @param description Novo valor da propriedade "fieldLength".
     */
    public void setFieldLength(String fieldLength) {
        this.fieldLength = fieldLength;
    }
    
    /** M�todo setter para a propriedade "fieldName".
     *  @param description Novo valor da propriedade "fieldName".
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
    
    /** M�todo setter para a propriedade "icon".
     *  @param description Novo valor da propriedade "icon".
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    /** M�todo setter para a propriedade "iconDesc".
     *  @param description Novo valor da propriedade "iconDesc".
     */
    public void setIconDesc(String iconDesc) {
        this.iconDesc = iconDesc;
    }
    
    /** M�todo setter para a propriedade "monthLabels".
     *  @param description Novo valor da propriedade "monthLabels".
     */
    public void setMonthLabels(String monthLabels) {
        this.monthLabels = monthLabels;
    }
    
    /** M�todo setter para a propriedade "readOnly".
     *  @param description Novo valor da propriedade "readOnly".
     */
    public void setReadOnly(String readOnly) {
        this.readOnly = readOnly;
    }
    
    /** M�todo setter para a propriedade "startDay".
     *  @param description Novo valor da propriedade "startDay".
     */
    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }
    
    /** M�todo setter para a propriedade "todayLabel".
     *  @param description Novo valor da propriedade "todayLabel".
     */
    public void setTodayLabel(String todayLabel) {
        this.todayLabel = todayLabel;
    }

	/** M�todo setter para a propriedade fieldYears.
	 * @param fieldYears Novo valor para fieldYears.
	 */
	public void setFieldYears(String fieldYears) {
		this.fieldYears = fieldYears;
	}

	/** M�todo setter para a propriedade disabled
	 *
	 * @param disabled String
	 */
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	/** M�todo getter para a propriedade onBlurMethod
	 * 
	 *  @return String
	 *
	 */
	public String getOnBlurMethod() {
		return onBlurMethod;
	}

	/** M�todo setter para a propriedade onBlurMethod
	 *
	 * @param onBlurMethod 
	 *           <code>String</code> a ser designado para onBlurMethod.
	 * 
	 */
	public void setOnBlurMethod(String onBlurMethod) {
		this.onBlurMethod = onBlurMethod;
	}

	/** M�todo getter para a propriedade tabIndex
	 * 
	 *  @return String
	 *
	 */
	public String getTabIndex() {
		return tabIndex;
	}

	/** M�todo setter para a propriedade tabIndex
	 *
	 * @param tabIndex 
	 *           <code>String</code> a ser designado para tabIndex.
	 * 
	 */
	public void setTabIndex(String tabIndex) {
		
		if ( tabIndex != null )		
			this.tabIndex = tabIndex;
		
	}

	/** M�todo setter para a propriedade onKeyUp
	 *
	 * @param onKeyUp 
	 *           <code>String</code> a ser designado para onKeyUp.
	 * 
	 */
	public void setOnKeyUp(String onKeyUp) {
		this.onKeyUp = onKeyUp;
	}

	/** M�todo setter para a propriedade maxLength
	 *
	 * @param maxLength String
	 */
	public void setMaxLength(String maxLength) {
		this.maxLength = maxLength;
	}
	
	
}

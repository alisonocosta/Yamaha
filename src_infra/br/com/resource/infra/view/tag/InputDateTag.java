/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Infraestrutura para aplicativos Java
 *   .: Objeto......DateUtils.java
 *   .: Criação.....26 de Abril, 16:01
 *   .: Autor.......Thiago Uriel M. Garcia
 *                  Edson Luiz Sumensari
 *   .: Descrição...Tag para Montagem do Calendário.
 */
package br.com.resource.infra.view.tag;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

/** Tag responsável por exibir um campo de data
 *  ao lado de um botão que disponibiliza um 
 *  calendário.
 *
 *  @author Thiago Uriel M. Garcia
 *  @author Edson Luiz Sumensari
 */
public class InputDateTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

	/** Log utilizado para saída de informações desta classe. */
	//private static Log log = LogFactory.getLog(InputDateTag.class);    
    
    /** Formato de data a ser utilizado. Este campo
     *  não é obrigatório e seu formato padrão é
     *  MM/dd/yyyy.
     */
    private String datePattern = new String("dd/MM/yyyy");
    
    /** Ícone a ser exibido ao lado do campo, no 
     *  qual o usuário poderá clicar para exibir
     *  o calendário.
     */
    private String icon;
    
    /** Representa o atributo "alt" da tag de
     *  imagens. Este valor não é obrigatório
     *  e caso não seja enviado, nada será
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
    
    /** Dia do mês no qual a data deverá ser 
     *  marcada inicialmente. Este campo não é obrigatório
     *  e seu valor inicial é <code>1</code>.
     */
    private String startDay = new String("1");
    
    /** Nome a ser utilizado para a inscrição "hoje"
     *  no calendário.
     */
    private String todayLabel;
    
    /** Tamanho do campo a ser utilizado na renderização
     *  do elemento HTML. Este campo não é obrigatório.
     */
    private String fieldLength = new String("12");
    
    /** Nome do campo de entrada. */
    private String fieldName;
    
    /** Nome do estilo a ser adotado. Este campo
     *  não é obrigatório.
     */
    private String fieldClass = new String("");
    
    /** Indica se este campo poderá ser manualmente 
     *  modificado pelo usuário. Este campo não é obrigatório
     *  e seu valor padrão é <code>false</code>.
     */
    private String readOnly = "false";
    
    /** Indica se este campo poderá ser manualmente 
     *  modificado pelo usuário. Este campo não é obrigatório
     *  e seu valor padrão é <code>enabled</code>.
     */
    private String disabled = "";
    
    /** Método javascript que deve ser chamado  no evento onBlur
     */
    private String onBlurMethod = "";
    
    /** Parâmetro do campo para ordem de tabulação
     */
    private String tabIndex = "";
    
    /**
     * Evento acionado ao solta uma tecla
     */
    private String onKeyUp = "";
    
    /**
     * Parâmetro que indica o valor de maxlength do campo data
     */
    private String maxLength = new String("10");
    
    /** Quantidade de anos listados. Este campo
     *  não é obrigatório.
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
    
    /** Método utilitário, responsável por quebrar uma
     *  String e formatá-la da forma esperada
     *  pela API JavaScript.
     *  
     *  @param rawData   Valor enviado pela página JSP
     * 
     *  @return   Valor adequado a ser enviado para a API
     *            Javascript.
     */
    private String parseStringValues(String rawData) {
        
        // Primeiro criamos um StringBuffer que armazenará
        // toda a String e também criamos um tokenizer a partir
        // da String de meses recebida, indicando que esta
        // deverá ser separada por "|".
        StringBuffer    result = new StringBuffer();
        StringTokenizer st     = new StringTokenizer(rawData, "|");
        
        // Agora iteramos por todos os tokens e organizamos
        // a string a ser devolvida.
        int count = 0;
        int limit = st.countTokens();
        while (st.hasMoreTokens()) {
            
            count++;
            
            result.append("'" + st.nextToken() + "'");
            
            // Se não for o último elemento, devemos
            // inserir uma vírgula.
            if (count != limit)
                result.append(",");
            
        }
        
        return result.toString();
        
    }
    
    /** Método setter para a propriedade "icon".
     *  @param description Novo valor da propriedade "icon".
     */
    public void setIcon(Object icon) {
        this.icon = icon.toString();
    }    
    
    /** Método setter para a propriedade "dayHeaders".
     *  @param description Novo valor da propriedade "dayHeaders".
     */
    public void setDayHeaders(Object dayHeaders) {
        this.dayHeaders = dayHeaders.toString();
    }    
    
    /** Método setter para a propriedade "monthLabels".
     *  @param description Novo valor da propriedade "monthLabels".
     */
    public void setMonthLabels(Object monthLabels) {
        this.monthLabels = monthLabels.toString();
    }

    /** Método setter para a propriedade "readOnly".
     *  @param description Novo valor da propriedade "readOnly".
     */
    public void setReadOnly(Object readOnly) {
        this.readOnly = readOnly.toString();
    }
    
    /** Método setter para a propriedade "startDay".
     *  @param description Novo valor da propriedade "startDay".
     */
    public void setStartDay(Object startDay) {
        this.startDay = startDay.toString();
    }
    
    /** Método setter para a propriedade "datePattern".
     *  @param description Novo valor da propriedade "datePattern".
     */
    public void setDatePattern(Object datePattern) {
        this.datePattern = datePattern.toString();
    }    

    /** Método setter para a propriedade "todayLabel".
     *  @param description Novo valor da propriedade "todayLabel".
     */
    public void setTodayLabel(Object todayLabel) {
        this.todayLabel = todayLabel.toString();
    }    

    /** Método setter para a propriedade "iconDesc".
     *  @param description Novo valor da propriedade "iconDesc".
     */
    public void setIconDesc(Object iconDesc) {
        this.iconDesc = iconDesc.toString();
    }
    
    /** Método setter para a propriedade "fieldClass".
     *  @param description Novo valor da propriedade "fieldClass".
     */
    public void setFieldClass(Object fieldClass) {
        this.fieldClass = fieldClass.toString();
    } 
    
    /** Método setter para a propriedade "datePattern".
     *  @param description Novo valor da propriedade "datePattern".
     */
    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }
    
    /** Método setter para a propriedade "dayHeaders".
     *  @param description Novo valor da propriedade "dayHeaders".
     */
    public void setDayHeaders(String dayHeaders) {
        this.dayHeaders = dayHeaders;
    }
    
    /** Método setter para a propriedade "fieldClass".
     *  @param description Novo valor da propriedade "fieldClass".
     */
    public void setFieldClass(String fieldClass) {
        this.fieldClass = fieldClass;
    }
    
    /** Método setter para a propriedade "fieldLength".
     *  @param description Novo valor da propriedade "fieldLength".
     */
    public void setFieldLength(String fieldLength) {
        this.fieldLength = fieldLength;
    }
    
    /** Método setter para a propriedade "fieldName".
     *  @param description Novo valor da propriedade "fieldName".
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
    
    /** Método setter para a propriedade "icon".
     *  @param description Novo valor da propriedade "icon".
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    /** Método setter para a propriedade "iconDesc".
     *  @param description Novo valor da propriedade "iconDesc".
     */
    public void setIconDesc(String iconDesc) {
        this.iconDesc = iconDesc;
    }
    
    /** Método setter para a propriedade "monthLabels".
     *  @param description Novo valor da propriedade "monthLabels".
     */
    public void setMonthLabels(String monthLabels) {
        this.monthLabels = monthLabels;
    }
    
    /** Método setter para a propriedade "readOnly".
     *  @param description Novo valor da propriedade "readOnly".
     */
    public void setReadOnly(String readOnly) {
        this.readOnly = readOnly;
    }
    
    /** Método setter para a propriedade "startDay".
     *  @param description Novo valor da propriedade "startDay".
     */
    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }
    
    /** Método setter para a propriedade "todayLabel".
     *  @param description Novo valor da propriedade "todayLabel".
     */
    public void setTodayLabel(String todayLabel) {
        this.todayLabel = todayLabel;
    }

	/** Método setter para a propriedade fieldYears.
	 * @param fieldYears Novo valor para fieldYears.
	 */
	public void setFieldYears(String fieldYears) {
		this.fieldYears = fieldYears;
	}

	/** Método setter para a propriedade disabled
	 *
	 * @param disabled String
	 */
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	/** Método getter para a propriedade onBlurMethod
	 * 
	 *  @return String
	 *
	 */
	public String getOnBlurMethod() {
		return onBlurMethod;
	}

	/** Método setter para a propriedade onBlurMethod
	 *
	 * @param onBlurMethod 
	 *           <code>String</code> a ser designado para onBlurMethod.
	 * 
	 */
	public void setOnBlurMethod(String onBlurMethod) {
		this.onBlurMethod = onBlurMethod;
	}

	/** Método getter para a propriedade tabIndex
	 * 
	 *  @return String
	 *
	 */
	public String getTabIndex() {
		return tabIndex;
	}

	/** Método setter para a propriedade tabIndex
	 *
	 * @param tabIndex 
	 *           <code>String</code> a ser designado para tabIndex.
	 * 
	 */
	public void setTabIndex(String tabIndex) {
		
		if ( tabIndex != null )		
			this.tabIndex = tabIndex;
		
	}

	/** Método setter para a propriedade onKeyUp
	 *
	 * @param onKeyUp 
	 *           <code>String</code> a ser designado para onKeyUp.
	 * 
	 */
	public void setOnKeyUp(String onKeyUp) {
		this.onKeyUp = onKeyUp;
	}

	/** Método setter para a propriedade maxLength
	 *
	 * @param maxLength String
	 */
	public void setMaxLength(String maxLength) {
		this.maxLength = maxLength;
	}
	
	
}

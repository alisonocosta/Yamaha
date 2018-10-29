<%@taglib uri="http://struts.apache.org/tags-bean"    prefix="bean"    %>
<%@taglib uri="http://struts.apache.org/tags-html-el" prefix="html"    %>
<%@taglib uri="http://struts.apache.org/tags-logic"   prefix="logic"   %>
<%@taglib uri="http://displaytag.sf.net/el" 		  prefix="display" %>
<%@taglib uri="http://java.sun.com/jstl/core" 		  prefix="c" 	   %>
<%@taglib uri="/tld/ym"  	 						  prefix="ym"      %>
<%@taglib uri="http://ajaxtags.org/tags/ajax" 		  prefix="ajax"    %>

<%--<ym:checkLogon roleName="sg_cd_doc" />--%>

<%-- 
/* Resource Informática LTDA.
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......documento_edit.jsp
 *   .: Criação.....01 de Setembro de 2008, 11:17
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Tela: Form para editar os dados de um documento.
 */
--%>

<html:html>
	<head>
		<title><bean:message key="home.title"/></title>
		<html:base/>
		<html:javascript formName="documentoForm"/>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>		
		<link rel="stylesheet" type="text/css" href="../css/progressbar/carregador.css"></link>	
		<link rel="stylesheet" type="text/css" href="../css/yamaha.css"></link>	
		<script language="JavaScript" src="../scripts/progressbar/carregador.js"></script>
		<script language="JavaScript"  src="../scripts/General/form.js"></script>
		<script type="text/javascript">
		
			var sysDate   ="<c:out value='${sysDate}'/>";
			
			function salvar() {
			
				if ( validateDocumentoForm(documentoForm) ) {
				
					if ( checkDate('endDate') ) {
			
						if ( window.confirm("Confirma a alteração do arquivo!") ) {			
							__showAnima();
							submitFormTsk(documentoForm,'saveEdit',true);
						} else 
							return false;
					} else 
						return false;
				} 
				
			}
			
			function fechar() {			
				//parent.window.location.reload();
				parent.hidePopWin(false,false);				
			}
			
			/*//////////////////////////////////////////////////////*/
			/*  Checa se data de Início e término é menor que a data atual */
			/*//////////////////////////////////////////////////////*/
			function checkDate( id ) {
			
	 			var startDate = window.document.getElementById("startDate").value;
	 			var endDate   = window.document.getElementById("endDate").value;
	 			
	 			if ( startDate != "" && sysDate != "" ) {
	 			
	 				// Inicio do Período
	 				if ( id == "endDate" && endDate != "" ) {	 				
	 					if ( comparaData( startDate, endDate ) > 0 ) {
			 				window.alert("A Data de Início não pode ser maior que a Data de Término!");
			 				window.document.getElementById(id).value = "";
			 				window.document.getElementById(id).focus();
			 				return false;
			 			}	
	 				}
	 				
	 				if ( (comparaData( sysDate, startDate ) > 0) ) {
			 				window.alert("A Data de Início não pode ser menor que a Data de Criação do registro!");
			 				window.document.getElementById("startDate").value = "";
			 				window.document.getElementById("startDate").focus();
			 				return false;
			 		}	
		 			
		 		}	 		
		 		
		 		return true;
	 		}

		</script>
	</head>
	<body leftmargin="0" topmargin="0" onLoad="__loadEsconde();" onunload="parent.reload();">
		<div id="carregador_pai">
		    <div id="carregador">
		        <div align="center">
		            <img src="../images/run.gif"/>
		            <br /><br />Carregando Aguarde...
		        </div>
		        <div id="carregador_fundo"><div id="barra_progresso"> </div></div>
		    </div>
		</div>
		<html:form action="/Documento" method="post" >
			<jsp:include flush="true" page="include_alerts.jsp"/>
			<html:hidden property="entityId"/>
			<input type="hidden" name="isEdit" value="<c:out value='${isEdit}'/>"/>
			<br>
			<table width="98%" border="0" cellpadding="2" cellspacing="2" align="center" style="border: 1 1 1 1 solid #788C9B;">	
			
               <tr>
				<td width="25%" class="td_dark">
						<bean:message key="documento.fileupload.description"/>:                                                   
               	</td>
               	<td width="75%">
						<html:text property="description" styleClass="text_field_maior" maxlength="500"></html:text>
						<html:img srcKey="form.img.required" altKey="form.msg.required"/>                                                 
               	</td>
               </tr>
               <tr>
               	<td width="25%" class="td_dark">
	 				<bean:message key="documento.fileupload.dateUpload"/>:                                                   
	            </td>
	                <td width="75%">
 						     <input type="text" name="createdDate" class="text_field_date" readonly="readonly" value="<c:out value='${sysDate}'/>"/>                                  
                	</td>
                </tr>
               <tr>
               	<td width="25%" class="td_dark">
	 				<bean:message key="documento.fileupload.startDate"/>:                                                   
	            </td>
	                <td width="75%">
 						<ym:inputDate icon="../images/icon_calendar.gif" 
				                 monthLabels	="Janeiro|Fevereiro|Março|Abril|Maio|Junho|Julho|Agosto|Setembro|Outubro|Novembro|Dezembro" 
				                 dayHeaders		="D|S|T|Q|Q|S|S"
								 todayLabel		="Hoje"
								 fieldName		="startDate"
								 fieldClass		="text_field_date"
								 datePattern	="dd/MM/yyyy"
								 fieldLength	="11"
								 maxLength      ="10"
								 fieldYears		="4"
								 onBlurMethod   ="checkDate('startDate')"
								 onKeyUp		="mascara_data('startDate',event);"
								 tabIndex       ="1000"> <%-- Valor aleatório para ordem de tablulação --%>
								 <bean:write name="documentoForm" property="startDate"/>						 
						</ym:inputDate> &nbsp;
						<html:img srcKey="form.img.required" altKey="form.msg.required"/>                                       
                	</td>
                </tr>
                <tr>
                	<td width="25%" class="td_dark">
 						<bean:message key="documento.fileupload.endDate"/>:                                                   
                	</td>
                	<td width="75%" valign="middle">
 						<ym:inputDate icon="../images/icon_calendar.gif" 
				                 monthLabels	="Janeiro|Fevereiro|Março|Abril|Maio|Junho|Julho|Agosto|Setembro|Outubro|Novembro|Dezembro" 
				                 dayHeaders		="D|S|T|Q|Q|S|S"
								 todayLabel		="Hoje"
								 fieldName		="endDate"
								 fieldClass		="text_field_date"
								 datePattern	="dd/MM/yyyy"
								 fieldLength	="11"
								 maxLength      ="10"
								 fieldYears		="4"
								 onBlurMethod   ="checkDate('endDate')"
								 onKeyUp		="mascara_data('endDate',event);"
								 tabIndex       ="1001"> <%-- Valor aleatório para ordem de tablulação --%>
								 <bean:write name="documentoForm" property="endDate"/>						 
						</ym:inputDate>                                             
                	</td>
                </tr>
               <tr>
				<td colspan="2" align="center" valign="middle">
						<html:button property="sv" styleClass="button_medium"  onclick="javascript:salvar();"><bean:message key="form.btn.save"/></html:button> &nbsp;    
						<html:button property="fr" styleClass="button_medium"  onclick="javascript:fechar();"><bean:message key="form.btn.close"/></html:button>                                            
               	</td>
               </tr>
			</table>		
		</html:form>
		<ym:alertMessage />		
	</body>

</html:html>
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
 *   .: Objeto......documento_list.jsp
 *   .: Criação.....28 de Agosto de 2008, 09:18
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Tela: Listagem dos documentos disponíveis para upload/download.
 */
--%>

<html:html>
	<head>
		<title><bean:message key="home.title"/></title>
		<html:base/>
		<html:javascript formName="documentoForm"/>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>		
		<link rel="stylesheet" type="text/css" href="../css/progressbar/carregador.css"></link>		
		<link rel="stylesheet" type="text/css" href="../css/displaytag/displaytag.css"></link>
		<link rel="stylesheet" type="text/css" href="../css/displaytag/site.css"></link>
		<link rel="stylesheet" type="text/css" href="../css/yamaha.css"></link>	
		<link rel="stylesheet" type="text/css" href="../css/modal/subModal.css" />		
		<script type="text/javascript" src="../scripts/General/common.js"></script>
		<script type="text/javascript" src="../scripts/General/subModal.js"></script>	
		<script language="JavaScript" src="../scripts/progressbar/carregador.js"></script>
		<script language="JavaScript"  src="../scripts/General/form.js"></script>
		<script type="text/javascript">
		
			var sysDate   ="<c:out value='${sysDate}'/>";
			var exp       =<c:out value="${exp}"/>;
		
			function editar( entityId ) {
		
				showPopWin("Documento.do?task=edit&entityId=" + entityId, 600, 200,null, true, 'Editar Documento');
		
			}
						
			function incluir() {
			
				if ( validateDocumentoForm(documentoForm) ) {
				
					if ( checkDate('endDate') ) {
			
						if ( window.confirm("Confirma o upload do arquivo!") ) {			
							__showAnima();
							submitFormTsk(documentoForm,'save',true);
						} else 
							return false;
					} else 
						return false;
				} 
				
			}
			
			function confExclusao() {
				if ( window.confirm("Confirma a exclusão do item selecionado?") ) {
					__showAnima();
					submitFormTsk(documentoForm,'remove',true);
				}else {
					return false;				
				}			
			}
			
			function reload(){
			
				if ( exp ) 				
					submitFormTsk(documentoForm,"list&exp=true",true);
				else 
					submitFormTsk(documentoForm,"list",true);
							
			}
			
			function listar(type) {
				
				var msg  = "";
				var task = "list";
				
				if ( type == null )
					type = !exp;
				
				if ( type )				
					msg = "Confirma a listagem dos arquivos válidos?";
				else {
					msg = "Confirma a listagem dos arquivos expirados?";
					task += "&exp=true";
				}				
				
				if ( window.confirm(msg) ) {
					__showAnima();
					submitFormTsk(documentoForm,task,true);
				}else {
					return false;				
				}
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
			 				window.alert("A Data de Início não pode ser menor que a Data de atual!");
			 				window.document.getElementById("startDate").value = "";
			 				window.document.getElementById("startDate").focus();
			 				return false;
			 		}	
		 			
		 		}	 		
		 		
		 		return true;
	 		}
		</script>
	</head>
	<body leftmargin="0" topmargin="0" onLoad="__loadEsconde();">
		<div id="carregador_pai">
		    <div id="carregador">
		        <div align="center">
		            <img src="../images/run.gif"/>
		            <br /><br />Carregando Aguarde...
		        </div>
		        <div id="carregador_fundo"><div id="barra_progresso"> </div></div>
		    </div>
		</div>
		<html:form action="/Documento" enctype="multipart/form-data" method="post" >
			<jsp:include flush="true" page="include_alerts.jsp"/>
			<html:hidden property="entityId"/> 
			<input type="hidden" name="isEdit" value="<c:out value='${isEdit}'/>"/>
			<center>
			<fieldset>
			<legend  class="labelList"><bean:message key="documento.fileupload.formUpload"/></legend>	
			
			<table width="98%" border="0" cellpadding="2" cellspacing="2">
			<tr>
				<td class="labelList" align="center" colspan="2">
					
				</td>
			</tr>
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
               	<td width="75%">
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
				<td width="25%" class="td_dark" align="right" valign="middle">
						<bean:message key="documento.fileupload.selectFile"/>:                                                   
               	</td>
               	<td width="75%" align="right" valign="middle">
						<html:file property="fileToUpload" styleClass="text_field_maior"/>   
						<html:img srcKey="form.img.required" altKey="form.msg.required"/>                                              
               	</td>
            </tr>
            <tr>
				<td colspan="2">
						<center>
							<html:button property="inc" styleClass="button_medium"  onclick="javascript:incluir();"><bean:message key="form.btn.include"/></html:button>
						</center>                                                 
               	</td>
            </tr>
			</table>
			</fieldset>
			<br>
			<fieldset>
			<legend  class="labelList"><bean:message key="documento.fileupload.list"/></legend>	
									
			<table class="form" width="98%" border="0" cellpadding="2" cellspacing="2">	
				<tr>
					<td align="right">
						<p align="right">
						
							<c:if test="${exp}">
								<html:button property="lis" styleClass="button_large"  onclick="javascript:listar(true);"><bean:message key="documento.fileupload.btn.list"/></html:button>
							</c:if>
							<c:if test="${!exp}">
								<html:button property="lis" styleClass="button_large"  onclick="javascript:listar(false);"><bean:message key="documento.fileupload.btn.listExp"/></html:button>
							</c:if>
							&nbsp;
							<html:button property="inc" styleClass="button_large"  onclick="javascript:confExclusao();"><bean:message key="documento.fileupload.btn.deleteSel"/></html:button>
						</p>
					</td>
				</tr>
			</table>
			<display:table name="documentoForm.listResults" 
			               requestURI="${pageContext.request.contextPath}/Documento.do"
			               export="false" 
			               sort="list" 
			               id="documento"
			               class="grid">
								
				<display:column titleKey="documento.fileupload.typeFile" style="text-align: center;" sortable="true"  headerClass="headerAlign">						
					 <html:img  srcKey="${documento.fileType}" altKey="${documento.fileTypeMsg}"/>
				</display:column>	
									
				<display:column titleKey="documento.fileupload.filename" maxLength="30" sortable="true" headerClass="headerAlign">						
						<c:out value="${documento.filename}"/>	
				</display:column>
				
				<display:column titleKey="documento.fileupload.description" maxLength="30"  sortable="true" headerClass="headerAlign">						
					 <html:link action="/Documento.do?task=download&entityId=${documento.entityId}&target=view"
					 			onmouseover="window.status='${documento.description}';return true;"
								onmouseout="window.status='';return true;"
								style="text-decoration: underline;">
					 	<c:out value="${documento.description}"/>
					 </html:link>
				</display:column>
				
				<display:column titleKey="documento.fileupload.sizeFile" sortable="true" style="text-align: right;"  headerClass="headerAlign">						
					 <c:out value="${documento.sizeStr}"/>
				</display:column>
				
				<display:column titleKey="documento.fileupload.dateUpload" sortable="true" sortProperty="dataCriacao" style="text-align: center;"  headerClass="headerAlign">						
					 <c:out value="${documento.maskedCreatedDate}"/>
				</display:column>
				
				<display:column titleKey="documento.fileupload.startDate" sortable="true" sortProperty="dataInicio" style="text-align: center;"  headerClass="headerAlign">						
					 <c:out value="${documento.maskedStartDate}"/>
				</display:column>
				
				<display:column  titleKey="documento.fileupload.endDate" sortable="true" sortProperty="dataTermino" style="text-align: center;"  headerClass="headerAlign">						
					 <c:out value="${documento.maskedEndDate}"/>
				</display:column>	
				
				<display:column  titleKey="documento.fileupload.edit" style="text-align: center;"  headerClass="headerAlign">
					 <html:img srcKey="documento.fileupload.img.edit" altKey="documento.fileupload.edit" onclick="editar(${documento.entityId})"/>
				</display:column>				
				
				<%-- Coluna com para seleção de itens para exclusão --%>
				<display:column media="html" style="width: 15px;" headerClass="td_lixeira">
					<html:multibox property="deleteTargets" value="${documento.entityId}" onclick="${pageScope.onClickFunctionName}"/>
				</display:column>
				
			</display:table>
			</fieldset>
			</center>
		</html:form>
		<ym:alertMessage />		
	</body>

</html:html>
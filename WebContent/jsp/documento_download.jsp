<%@taglib uri="http://struts.apache.org/tags-bean"    prefix="bean"    %>
<%@taglib uri="http://struts.apache.org/tags-html-el" prefix="html"    %>
<%@taglib uri="http://struts.apache.org/tags-logic"   prefix="logic"   %>
<%@taglib uri="http://displaytag.sf.net/el" 		  prefix="display" %>
<%@taglib uri="http://java.sun.com/jstl/core" 		  prefix="c" 	   %>
<%@taglib uri="/tld/ym"  	 						  prefix="ym"      %>

<%--<ym:checkLogon roleName="sg_cd_doc" />--%>

<%-- 
/* Resource Informática LTDA.
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......documento_download.jsp
 *   .: Criação.....02 de Setembro de 2008, 08:41
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Tela: Listagem dos documentos disponíveis para download.
 */
--%>

<html:html>
	<head>
		<title><bean:message key="home.title"/></title>
		<html:base/>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>		
		<link rel="stylesheet" type="text/css" href="../css/progressbar/carregador.css"></link>		
		<link rel="stylesheet" type="text/css" href="../css/displaytag/displaytag.css"></link>
		<link rel="stylesheet" type="text/css" href="../css/displaytag/site.css"></link>
		<link rel="stylesheet" type="text/css" href="../css/yamaha.css"></link>		
		<script language="JavaScript" src="../scripts/progressbar/carregador.js"></script>
		<script language="JavaScript"  src="../scripts/General/form.js"></script>
		<script type="text/javascript">
			
			function atualizar() {
			
				submitFormTsk(documentoForm,'listDownload',true);
			
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
			&nbsp;&nbsp;<html:button property="at" styleClass="button_medium"  onclick="javascript:atualizar();"><bean:message key="documento.fileupload.btn.update"/></html:button>
			<center>			
			<display:table name="documentoForm.listResults" 
			               requestURI="${pageContext.request.contextPath}/Documento.do"
			               export="false" 
			               sort="list"
			               pagesize="15"
			               id="documento"
			               class="grid">
					
				<display:column titleKey="documento.fileupload.typeFile" style="text-align: center;" sortable="true"  headerClass="headerAlign">						
					 <html:img  srcKey="${documento.fileType}" altKey="${documento.fileTypeMsg}"/>
				</display:column>
				
				<display:column titleKey="documento.fileupload.description" maxLength="80"  sortable="true" headerClass="headerAlign">						
					 <html:link action="/Documento.do?task=download&entityId=${documento.entityId}"
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
				
			</display:table>
			</center>
		</html:form>
		<ym:alertMessage />		
	</body>

</html:html>
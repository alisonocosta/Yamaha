<%@taglib uri="http://struts.apache.org/tags-bean"     prefix="bean"    %>
<%@taglib uri="http://struts.apache.org/tags-html-el"  prefix="html"    %>
<%@taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic"   %>
<%@taglib uri="http://displaytag.sf.net/el" 		   prefix="display" %>
<%@taglib uri="http://java.sun.com/jstl/core" 		   prefix="c" 	    %>
<%@taglib uri="/tld/ym"  	 						   prefix="ym"      %>

<%-- 
/* Resource Informática LTDA.
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......notaFiscal_lote_disponivel.jsp
 *   .: Criação.....31 de maio de 2007, 08:40
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Tela: Lista os lotes disponíveis e permite selecionar
 *						  para inclusão de notas fiscais.
 */
--%>

<html:html>
<head>
		<title><bean:message key="home.title"/></title>
		<html:base/>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>		
		<link rel="stylesheet" type="text/css" href="../css/displaytag/displaytag.css"></link>
		<link rel="stylesheet" type="text/css" href="../css/displaytag/site.css"></link>
		<link rel="stylesheet" type="text/css" href="../css/yamaha.css"></link>
		<script type="text/javascript" src="../scripts/WindowUI/window_utils.js"></script> 
		<script language="JavaScript" src="../scripts/General/form.js"></script>
		<script type="text/javascript">
		<!--
			function CheckAll(obj) { 
				loteId = obj.value;
				var isChecked = obj.checked;
				for (var i=0 ; i < window.document.notaFiscalListForm.elements.length ; i++) {
			    	var x = window.document.notaFiscalListForm.elements[i];
			    	if (x.name == 'selectedCupons' || x.name == 'selectedGuarantees' ) { 
			    		var value = x.value;
			    		if ( value.substring(0,value.indexOf('@')) == loteId )	
							x.checked = isChecked;
					} 
				}
				enableLaunch();	
			} 
			
			function enableLaunch() {
			
				var isChecked = false;
				for (var i=0 ; i < window.document.notaFiscalListForm.elements.length ; i++) {
					
					var x = window.document.notaFiscalListForm.elements[i];
					if ( x.type == "checkbox" ) {
						if ( x.checked == true )
							isChecked = true;				
					}				
				}
				if ( isChecked  )
					window.document.getElementById("lancar").disabled = false;		
				else
					window.document.getElementById("lancar").disabled = true;	
								
			}
		//-->			
		</script>
	</head>
	<body leftmargin="0" topmargin="0">
	
		<html:form action="/NotaFiscal" method="post">
			
			<jsp:include flush="true" page="include_alerts.jsp"/>
			<br>
			<center>
			<table width="100%" border="0" cellpadding="2" cellspacing="2" align="center">			
			<tr>
				<td width="40%" align="center">
					<center><span class="labelList"><bean:message key="notaFiscal.revision"/></span><br>
					<display:table name="notaFiscalListForm.listItensYMA" 
					               requestURI="${pageContext.request.contextPath}/NotaFiscal.do"
					               defaultsort="1" 
					               defaultorder="descending" 
					               id="nataFiscalVO"
					               class="grid">
					               
					    <display:column titleKey="notaFiscal.title.YMA"/>
					    
						<display:column group="1" titleKey="lote.title">
							<html:multibox property="selectedLotesYMA" value="${nataFiscalVO.loteId}" onclick="CheckAll(this);${pageScope.onClickFunctionName}"/>  
							<b><c:out value="${nataFiscalVO.loteId}"/></b>
						</display:column>
						
						<display:column titleKey="cupom.title" media="html">	
								<html:multibox property="selectedItemYma" value="${cupom.lote.entityId}@${cupom.entityId}" onclick="enableLaunch();${pageScope.onClickFunctionName}"/>
								<c:out value="${cupom.entityId}"/>												
						</display:column>	
						
						<display:column titleKey="notaFiscal.ServiceValue" style="text-align: right;">
							<c:out value="${cupom.valorTotalRevisao}"/>					
						</display:column>
						
					</display:table></center>
				</td>
				<td width="60%" align="center">  
					<center><span class="labelList"><bean:message key="notaFiscal.guarantee"/></span><br>
					<display:table name="notaFiscalListForm.listGarantias" 
					               requestURI="${pageContext.request.contextPath}/NotaFiscal.do"
					               defaultsort="1" 
					               defaultorder="descending" 
					               id="garantia"
					               class="grid">
					    
						<display:column group="1" titleKey="lote.title">
							<html:multibox property="selectedLotesGarantias"  value="${garantia.loteId}" onclick="CheckAll(this);${pageScope.onClickFunctionName}"/>  
							<b><c:out value="${garantia.loteId}"/></b>
						</display:column>
						
						<display:column titleKey="notaFiscal.guarantee" media="html">	
								<html:multibox property="selectedGuarantees" value="${garantia.loteId}@${garantia.garantiaId}" onclick="enableLaunch();${pageScope.onClickFunctionName}"/>
								<c:out value="${garantia.garantiaId}"/>												
						</display:column>	
						
						<display:column titleKey="notaFiscal.ServiceValue" style="text-align: right;">
							<c:out value="${garantia.formattedValorServico}"/>					
						</display:column>
						
						<display:column titleKey="notaFiscal.pecaValue" style="text-align: right;">
							<c:out value="${garantia.formattedValorPeca}"/>					
						</display:column>
						
						<display:column titleKey="notaFiscal.remessaValue" style="text-align: right;">
							<c:out value="${garantia.formattedValorRemessa}"/>					
						</display:column>
						
					</display:table></center>
					
				</td>
			</tr>
			</table>
			</center>
			<center>
				<table border="0" cellpadding="2" cellspacing="2" align="center">								
				<tr height="25">
					<td align="center">
						<html:button property="lancar" styleClass="button_medium" onclick="submitFormTsk(notaFiscalListForm,'launchNote',true)" disabled="true"><bean:message key="form.btn.launch"/></html:button>	
						&nbsp;
						<html:reset property="limpar" styleClass="button_medium"><bean:message key="form.btn.clear"/></html:reset>	
						&nbsp;
						<html:button property="cancelar" styleClass="button_medium" onclick="javascript:top.Windows.close(top.Windows.focusedWindow.getId());"><bean:message key="form.btn.cancel"/></html:button>	
					</td>
				</tr>
				</table>
			</center>
			
		</html:form>

	</body>
</html:html>
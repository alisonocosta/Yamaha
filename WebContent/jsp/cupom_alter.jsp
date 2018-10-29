<%@taglib uri="http://struts.apache.org/tags-bean"  	prefix="bean"  %>
<%@taglib uri="http://struts.apache.org/tags-html-el"   prefix="html"  %>
<%@taglib uri="http://struts.apache.org/tags-logic" 	prefix="logic" %>
<%@taglib uri="/tld/ym"  	 							prefix="ym"    %>
<%@taglib uri="http://java.sun.com/jstl/core" 		    prefix="c" 	   %>
<%@taglib uri="http://ajaxtags.org/tags/ajax" 			prefix="ajax"  %>

<ym:checkLogon roleName="sg_al_rev" />

<%-- 
/* Resource Informática LTDA.
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......cupom_alter.jsp
 *   .: Criação.....29 de maio de 2007, 09:10
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Tela: Formulário para alteração de cupom.
 */
--%>

<html>
	<head>
		<title><bean:message key="home.title"/></title>
		<html:base/>
		<html:javascript formName="cupomAlterForm"/>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
		<link rel="stylesheet" type="text/css" href="../css/yamaha.css"></link>
		<script language="JavaScript" src="../scripts/General/form.js"></script>
		<script language="JavaScript">
		
			function voltar() {
				window.top.location.href ="javascript:newTitle('Relação de Revisões');";
				window.location.href = "Cupom.do?task=list&loteId=" + window.document.cupomAlterForm.loteId.value;
			}
			
			function setTitle() {
				<c:if test="${cupom.revisao.numeroRevisao > 1}">	
					window.top.location.href ="javascript:newTitle('Alterar Revisão');";
				</c:if>
				<c:if test="${cupom.revisao.numeroRevisao == 1}">	
					window.top.location.href ="javascript:newTitle('Consultar Revisão');";
				</c:if>
			}
			
			/** Apresenta os campos do div revisão */
			function visibleFields() {
			
				var linhaId	  = window.document.getElementById('linhaId').value;
				   				
   				if ( linhaId == 2  || linhaId == 3 || linhaId == 4 )  {
   				
   					showFields('nautica_geral');
   					hiddenFields('motocicleta');
   					
   					window.document.getElementById("diasUso").disabled       = false;
   					window.document.getElementById("horasUso").disabled      = false; 
   					window.document.getElementById("tipoUsoId").disabled     = false; 
   					window.document.getElementById("quilometragem").disabled = true; 
   					
   				} else if ( linhaId == 1 ) {
   				
   					showFields('motocicleta');
   					hiddenFields('nautica_geral');
   					
   					window.document.getElementById("diasUso").disabled       = true;
   					window.document.getElementById("horasUso").disabled      = true; 
   					window.document.getElementById("tipoUsoId").disabled     = true; 
   					window.document.getElementById("quilometragem").disabled = false;
   				}
   				
   			}
			
			function showFields(id) {
				
				var layer 			= window.document.getElementById(id); 
   				layer.style.display = "";   				
			
			}
						
			/** Oculta os campos do div revisão */
			function hiddenFields(id) {			
			
				var layer 			= window.document.getElementById(id); 
   				layer.style.display = "none"; 
   				
			}
			
			if (document.layers)
				window.document.captureEvents(Event.KEYDOWN);
				
			window.document.onkeydown = function (evt) { 
				var keyCode = evt ? (evt.which ? evt.which : evt.keyCode) : event.keyCode;
				if (keyCode == 13) {
					validateCupomAlterForm(cupomAlterForm);
				}
	 		}
			
		</script>
	</head>
<body leftmargin="0" topmargin="3">
	<html:form action="/CupomAlter" method="post">
	
			<html:hidden property="linhaId"/>
			<html:hidden property="loteId"/>
			<html:hidden property="cupomCode" value="${cupom.cupomCode}"/>
			<html:hidden property="entityId"  value="${cupom.entityId}"/>
			<html:hidden property="revisaoId" value="${cupom.revisao.entityId}"/>
			
			<%-- Aletrtas enviados pela action --%>
			<jsp:include flush="true" page="include_alerts.jsp"/>
			<br>
			<%-- Título apresentando a Linha do produto - Náutica ou Motocicleta  --%>
			<fieldset>
			<legend  class="labelList"><b>Linha</b> <c:out value="${cupom.lote.linha.descricao}"/> </legend>			
			
			<%-- Campos que devem ser apresentados para todas as linhas de produtos --%>
			<table width="98%" border="0" cellpadding="2" cellspacing="2" align="center">
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="cupom.lote"/>:
				</td>
				<td width="75%" class="text" style="border: 1px solid #989898;">	
					<c:out value="${cupom.lote.entityId}"/>
				</td>
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="cupom.title"/>:
				</td>
				<td width="75%" class="text" style="border: 1px solid #989898;">	
					<c:out value="${cupom.cupomCode}"/>
				</td>
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="cupom.chassi"/>:
				</td>
				<td width="75%" class="text" style="border: 1px solid #989898;">	
					<c:out value="${cupom.chassi}"/>
				</td>
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark">	
					<bean:message key="cupom.typeRev"/>:
				</td>
				<td width="75%" class="text" style="border: 1px solid #989898;">	
					<c:out value="${cupom.revisao.descricao}"/>
				</td>
			</tr>
			</table>
			
			<%-- 
			  *  Campo que deve ser apresentado apenas quando a linha do produto for Motocicleta 
			  *  Para qualquer tipo de Revisão
			 --%>			
			<div id="motocicleta" style="display:none;">
			
			<table width="98%" border="0" cellpadding="2" cellspacing="2" align="center">		
			<tr height="25"> 
				<td width="25%" class="td_dark" >	
					<bean:message key="cupom.kilometrage"/>:
				</td>
				<td width="75%" class="text">
					<html:text property="quilometragem" styleClass="text_field_menor" maxlength="10" onkeypress="return blockChar(event);"></html:text>			   
					<html:img  srcKey="form.img.required" altKey="form.msg.required"/>  
				</td>
			</tr>
			</table>
			</div>
			
			<%-- 
			  *  Campo que deve ser apresentado apenas quando a linha do produto for Náutica 
			  *  Para qualquer tipo de Revisão
			 --%>
			<div id="nautica_geral" style="display:none;">
			
			<table width="98%" border="0" cellpadding="2" cellspacing="2" align="center">
			<tr height="25"> 
				<td width="25%" class="td_dark" >	
					<bean:message key="cupom.useDays"/>: 
				</td>
				<td width="75%" class="text">
					<html:text property="diasUso" styleClass="text_field_menor" maxlength="10" onkeypress="return blockChar(event);"></html:text>			   
					<html:img  srcKey="form.img.required" altKey="form.msg.required"/>  
				</td>
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark" >	
					<bean:message key="cupom.useHours"/>:
				</td>
				<td width="75%" class="text">
					<html:text property="horasUso" styleClass="text_field_menor" maxlength="10" onkeypress="return blockChar(event);"></html:text>			   
					<html:img  srcKey="form.img.required" altKey="form.msg.required"/>  
				</td>
			</tr>
			<tr height="25"> 
				<td width="25%" class="td_dark" >	
					<bean:message key="cupom.useType"/>:
				</td>
				<td width="75%" class="text">
					<table width="45%" border="0" cellpadding="1" cellspacing="1" align="left" style="border: 1px solid #989898;">
					<logic:iterate name="cupomAlterForm" property="listUseType" id="tipoUsoBarco" >
						<tr>
							<td class="text" valign="middle">							
									<html:radio property="tipoUsoId" value="${tipoUsoBarco.entityId}"></html:radio> 
									<bean:write name="tipoUsoBarco" property="descricao"/>							
							</td>						
						</tr>
					</logic:iterate>
					</table>
					<html:img  srcKey="form.img.required" altKey="form.msg.required"/>  
				</td>
			</tr>
			</table>	
			</div>
			
			<table width="98%" border="0" cellpadding="2" cellspacing="2" align="center">						
			<tr height="25">
				<td colspan="2" align="center">
					<c:if test="${cupom.revisao.numeroRevisao > 1}">	
						<html:button property="gravar" styleClass="button_medium" onclick="submitFormTsk(cupomAlterForm,'update',validateCupomAlterForm(cupomAlterForm))"><bean:message key="form.btn.save"/></html:button>	
						&nbsp;
						<html:reset property="limpar" styleClass="button_medium"><bean:message key="form.btn.clear"/></html:reset>	
						&nbsp;
					</c:if>
					<html:button property="back" styleClass="button_medium" onclick="javascript:voltar();"><bean:message key="form.btn.preview"/></html:button>		
				</td>
			</tr>
			<tr> 
				<td height="15" colspan="2">&nbsp;</td>
			</tr>
			<tr> 
				<td height="25" class="text" colspan="2"><html:image srcKey="form.img.required" altKey="form.msg.required"/>&nbsp;-&nbsp;<bean:message key="form.msg.required"/></td>
			</tr>
			</table>
			<script language="javascript">
				<c:if test="${cupom.revisao.numeroRevisao > 1}">					
					visibleFields();
				</c:if>
			</script>
	</html:form>
	<ym:javaScriptExecuter/>
	
</body>
</html>
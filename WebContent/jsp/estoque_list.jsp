<%@taglib uri="http://struts.apache.org/tags-bean"    prefix="bean"    %>
<%@taglib uri="http://struts.apache.org/tags-html-el" prefix="html"    %>
<%@taglib uri="http://struts.apache.org/tags-logic"   prefix="logic"   %>
<%@taglib uri="http://displaytag.sf.net/el" 		  prefix="display" %>
<%@taglib uri="http://java.sun.com/jstl/core" 		  prefix="c" 	   %>
<%@taglib uri="/tld/ym"  	 						  prefix="ym"      %>
<%@taglib uri="http://ajaxtags.org/tags/ajax" 		  prefix="ajax"    %>

<%-- 
/* Resource Informática LTDA.
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......estoqeu_list.jsp
 *   .: Criação.....16 de abril de 2008, 13:43
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Tela: Listagem de estoque da concessionária.
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
		<link rel="stylesheet" type="text/css" href="../css/autocomplete/autocomplete.css"></link>			
		<script language="JavaScript" src="../scripts/progressbar/carregador.js"></script>
		<script language="JavaScript"  src="../scripts/General/form.js"></script>
		<script type="text/javascript" src="../scripts/WindowUI/window_utils.js"></script> 				
		<script language="JavaScript"  src="../scripts/Ajax/Base/prototype-1.4.0.js"></script>
		<script language="JavaScript"  src="../scripts/Ajax/Base/overlibmws.js"></script>
		<script language="JavaScript"  src="../scripts/Ajax/Base/builder.js"></script>
		<script language="JavaScript"  src="../scripts/Ajax/Base/scriptaculous.js"></script>
		<script language="JavaScript"  src="../scripts/Ajax/Base/ajaxtags-1.2-beta2.js"></script>
		<script language="JavaScript"  src="../scripts/Ajax/Base/dragdrop.js"></script>	
		<script type="text/javascript">
		
			function incluir() {
			
				var objChassi = window.document.estoqueForm.chassi;				
				
				if ( objChassi.value != "" ) {
					if ( window.confirm("Confirma a inclusão do produto " + objChassi.value + " no inventário!") ) {			
						__showAnima();
						submitFormTsk(estoqueForm,'save',true);
					} else 
						return false;
				} else {
					window.alert("O campo Chassi é de preenchimento obrigatório para inclusão!");
					objChassi.focus();
					return false;
				}
				
			}
			
			function confExclusao() {
				if ( window.confirm("Confirma a exclusão do item selecionado?") ) {
					__showAnima();
					submitFormTsk(estoqueForm,'remove',true);
				}else {
					return false;				
				}			
			}			
			
			function storeChassi() {
				
				var chassiFieldSource   = window.document.estoqueForm.chassi;
				var chassiFieldTarget   = window.document.estoqueForm.escapedChassi;			
				chassiFieldTarget.value = escape(chassiFieldSource.value);
				
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
		<html:form action="/Estoque" method="post" >
			<jsp:include flush="true" page="include_alerts.jsp"/>
			
			<center>
				<table width="98%" border="0" cellpadding="2" cellspacing="2" align="center">	
				<tr>
					<td width="30%" class="td_dark" align="right" valign="middle">
 						<bean:message key="estoque.list.data.estoque"/>:                                                   
                	</td>
                	<td width="10%" align="center" valign="middle">
 						<b><c:out value="${dataEstoque}"/></b>	                                                 
                	</td>
					<td width="10%" class="td_dark" align="right" valign="middle">
 						<bean:message key="estoque.form.chassi"/>:                                                   
                	</td>
					<td>
						<html:text property="chassi" styleClass="text_field_menor" maxlength="13" onkeyup="javaScript:storeChassi()"></html:text>					
						<html:img  srcKey="form.img.required" altKey="form.msg.required"/>  
						<span id="indicator" style="display:none;"><html:img srcKey="form.img.indicador" altKey="form.msg.indicador"/></span>
						<input type="hidden" name="escapedChassi" value="null"/>
						<input type="hidden" name="targetChassi"  value="null"/>
							<script type="text/javascript">
								new AjaxJspTag.Autocomplete(
									"<%= request.getContextPath() %>/DeterminaChassiEstoque.do", {
										minimumCharacters: "8",
										parameters: "chassi={escapedChassi}", 
										target: "targetChassi",
										className: "autocomplete",
										source: "chassi",
										indicator: "indicator"
									});
							</script>
						<html:button property="inc" styleClass="button_medium"  onclick="javascript:incluir();"><bean:message key="form.btn.include"/></html:button>	</td>
				</tr>
				</table>
				<display:table name="estoqueForm.listResults" 
				               requestURI="${pageContext.request.contextPath}/Estoque.do"
				               defaultsort="1" 
				               defaultorder="ascending" 
				               export="false" 
				               sort="list"
				               id="estoqueVO"
				               class="grid">
										
					<display:column titleKey="estoque.form.chassi" headerClass="headerAlign">						
							<c:out value="${estoqueVO.chassi}"/>	
					</display:column>
					
					<display:column titleKey="estoque.list.data.estoque" style="width: 30%;text-align: center;"  headerClass="headerAlign">						
						 <c:out value="${estoqueVO.strDataEstoque}"/>
					</display:column>
					
					<display:column titleKey="estoque.list.data.faturamento" style="width: 30%;text-align: center;"  headerClass="headerAlign">						
						 <c:out value="${estoqueVO.strDataFaturamento}"/>
					</display:column>
					
					<%-- Coluna com para seleção de itens para exclusão --%>
					<display:column media="html" style="width: 15px;" headerClass="td_lixeira">
						<html:multibox property="deleteTargets" value="${estoqueVO.entityId}" onclick="${pageScope.onClickFunctionName}"/>
					</display:column>
					
				</display:table>
			</center>
			<table width="98%" border="0" cellpadding="2" cellspacing="2" align="center">	
				<tr>
					<td align="right"><html:button property="inc" styleClass="button_large"  onclick="javascript:confExclusao();"><bean:message key="estoque.list.btn.deleteSel"/></html:button></td>
				</tr>
			</table>
		
		</html:form>
		<ym:alertMessage />		
	</body>

</html:html>
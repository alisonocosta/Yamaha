<%@taglib uri="http://struts.apache.org/tags-bean"     prefix="bean"   %>
<%@taglib uri="http://struts.apache.org/tags-html-el"  prefix="html"   %>
<%@taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic"  %>
<%@taglib uri="http://struts.apache.org/tags-logic"    prefix="logreg" %>
<%@ taglib uri="/tld/ym"  	 						   prefix="ym"	   %>

<ym:checkLogon roleName="sg_cd_lote" />

<%-- 
/* Resource Informática LTDA.
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......lote_form.jsp
 *   .: Criação.....24 de abril de 2007, 11:14
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Tela: Formulário de inclusão de lote.
 */
--%>

<html:html>
	<head>
		<title><bean:message key="home.title"/></title>
		<html:base/>
		<html:javascript formName="loteForm"/>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
		<script language="JavaScript" src="../scripts/General/form.js"></script>		
		<script language="JavaScript" src="../scripts/progressbar/carregador.js"></script>
		<link rel="stylesheet" type="text/css" href="../css/yamaha.css"></link>
		<link rel="stylesheet" type="text/css" href="../css/progressbar/carregador.css"></link>
		
		<script language="javascript">
			function abrirRevisao( loteId ) {
				
				window.top.location.href ="javascript:newTitle('Incluir Revisão');";
				window.location.href     = "Cupom.do?task=add&loteId=" + loteId;				
			
			}
			
			function abrirGarantia( loteId ) { 
				
				window.top.location.href ="javascript:newTitle('Incluir Garantia');";
				window.location.href = "Garantia.do?task=add&loteId=" + loteId;
				
			}   
			
			function listar() { 
				
				__showAnima();
				window.top.location.href ="javascript:newTitle('Relação de Lotes');";
				window.location.href = "Lote.do?task=list&page=1&dir=desc&sort=1&limit=20";
								
			}   		
		</script>
	
	</head>
	<body leftmargin="0" topmargin="0" onLoad="__loadEsconde();">
		<div id="carregador_pai">
		    <div id="carregador">
		        <div align="center">
		            <img src="../images/run.gif" />
		            <br /><br />Carregando Aguarde...
		        </div>
		        <div id="carregador_fundo"><div id="barra_progresso"> </div></div>
		    </div>
		</div>
	
		<html:form action="/Lote" method="post">
		
			<html:hidden property="entityId"/>	
			<html:hidden property="idConcessionaria"/>
			<html:hidden property="criadoPor"/>
			<html:hidden property="atualizadoPor"/>
			<html:hidden property="dataAtualizacao"/>
			<html:hidden property="locked"/>
			<br>
			<jsp:include flush="true" page="include_alerts.jsp"/>
			
			<table width="98%" border="0" cellpadding="2" cellspacing="2" align="center">			
			<tr> 
				<td height="5" colspan="2">&nbsp;</td>
			</tr>
			<tr height="25"> 
				<td width="30%" class="td_dark">	
					<bean:message key="lote.type"/>:
				</td>
				<td width="60%" class="text">	
					<table width="99%" border="0" cellpadding="1" cellspacing="1" align="center" style="border: 1px solid #989898;">
					<logic:iterate name="loteForm" property="descriptionLoteTypeList" id="tipoLote" >
						<tr>
							<td class="text">
							
								<logreg:equal name="loteForm" property="locked" value="s">
									<html:radio property="idTipoLote" value="${tipoLote.entityId}" disabled="true"/>&nbsp;
									<bean:write name="tipoLote" property="descricao"/>
								</logreg:equal>
								
								<logreg:equal name="loteForm" property="locked" value="n">
									<html:radio property="idTipoLote" value="${tipoLote.entityId}"/>&nbsp;
									<bean:write name="tipoLote" property="descricao"/>
								</logreg:equal>
								
							</td>						
						</tr>
					</logic:iterate>
					</table>
				</td>
				<td  width="10%"  rowspan="" valign="middle">
							&nbsp;<html:img  srcKey="form.img.required" altKey="form.msg.required"/>  
				</td>
			</tr>
			<tr height="25"> 
				<td width="30%" class="td_dark">	
					<bean:message key="lote.line"/>:
				</td>
				<td width="60%" class="text">	
					<table width="99%" border="0" cellpadding="1" cellspacing="1" align="center" style="border: 1px solid #989898;">
					<logic:iterate name="loteForm" property="descriptionLineList" id="linha" >
						<tr>
							<td class="text">
							
								<logreg:equal name="loteForm" property="locked" value="s">
									<html:radio property="idLinha" value="${linha.entityId}" disabled="true"/>&nbsp;
									<bean:write name="linha" property="descricao"/>
								</logreg:equal>							

								<logreg:equal name="loteForm" property="locked" value="n">
									<html:radio property="idLinha" value="${linha.entityId}"/>&nbsp;
									<bean:write name="linha" property="descricao"/>
								</logreg:equal>
																
							</td>						
						</tr>
					</logic:iterate>
					</table>
				</td>
				<td  width="10%"  rowspan="" valign="middle">
							&nbsp;<html:img  srcKey="form.img.required" altKey="form.msg.required"/>  
				</td>
			</tr>
			<tr> 
				<td height="15">&nbsp;</td>
			</tr>
			
			<%--<logreg:equal name="loteForm" property="locked" value="s"> --%>
				<tr>
					<td colspan="2" align="center">
						<html:button property="gravar" styleClass="button_medium" onclick="submitFormTsk(loteForm,'save',validateLoteForm(loteForm))"><bean:message key="form.btn.save"/></html:button>							
						&nbsp;
						<html:button property="list" styleClass="button_medium" onclick="listar();"><bean:message key="form.btn.preview"/></html:button>	
					</td>
				</tr>
			<%--</logreg:equal>--%>
			
			<tr> 
				<td height="15" colspan="2">&nbsp;</td>
			</tr>
			<tr> 
				<td height="15" class="text" colspan="2"><html:image srcKey="form.img.required" altKey="form.msg.required"/>&nbsp;-&nbsp;<bean:message key="form.msg.required"/></td>
			</tr>
			</table>
		</html:form>
		<ym:confirmMessage />
		<ym:javaScriptExecuter/>
	</body>
</html:html>
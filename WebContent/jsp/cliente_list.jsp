<%@taglib uri="http://struts.apache.org/tags-bean"  	prefix="bean"    %>
<%@taglib uri="http://struts.apache.org/tags-html"  	prefix="html"    %>
<%@taglib uri="http://struts.apache.org/tags-html-el" 	prefix="html-el" %>
<%@taglib uri="http://struts.apache.org/tags-logic" 	prefix="logic"   %>
<%@taglib uri="http://displaytag.sf.net/el" 			prefix="display" %>
<%@taglib uri="/tld/ym"  	 							prefix="ym"      %>

<%-- 
/* Resource Informática LTDA.
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......clientes_list.jsp
 *   .: Criação.....02 de abril de 2007, 17:20
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Tela: Listagem de clientes.
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
		<script type="text/javascript">
		
			function editar(entityId) { 
				window.top.location.href ="javascript:newTitle('Editar Cliente');";
				window.location.href = "Cliente.do?task=edit&entityId=" + entityId;
				<%--window.parent.location.href = "javascript:openWindow(\'win_sg_cliente_edit\', \'Editar Cliente\', \'/ym_sistema_garantia/Cliente.do?task=edit&entityId=" + entityId + "\');"--%>
			} 
			
			function pesquisar(){
			
				window.top.location.href = "javascript:newTitle('Pesquisar Clientes');";
				window.location.href = "Cliente.do?task=search";
			
			}
			
			/*function incluir() {
				
				window.top.location.href ="javascript:newTitle('Incluir Cliente');";
				window.location.href = "/ym_sistema_garantia/Cliente.do?task=add";
			
			}*/
		</script>
	</head>
	<body leftmargin="0" topmargin="0">
	
		<html:form action="/Cliente" method="get">

			<html:hidden property="task"/>	
	
			<center>
				<%--<html:button property="cadastrar" styleClass="button_medium" onclick="incluir()"><bean:message key="form.btn.include"/></html:button> &nbsp;--%>
				<html:button property="pesq" styleClass="button_medium" onclick="pesquisar()"><bean:message key="form.btn.search"/></html:button> 	<br>
				<display:table name="requestScope.clienteForm.listResults" 
				               requestURI="${pageContext.request.contextPath}/Cliente.do"
				               defaultsort="2" 
				               defaultorder="ascending" 
				               export="false"
				               sort="external"
				               pagesize="20"
				               id="cliente"
				               class="grid">
				               
					<display:column titleKey="customer.cnpj" property="cnpj" decorator="br.com.yamaha.sistemagarantia.view.decorator.CpfCnpjDecorator"/>
					
					<display:column titleKey="customer.name" media="html" sortName="nome">
						<html-el:link href="javascript:editar(${cliente.entityId});">
							<bean:write name="cliente" property="nome"/>
						</html-el:link>						
					</display:column>
					
					<display:column titleKey="customer.name" media="csv excel xml pdf rtf">
							<bean:write name="cliente" property="nome"/>
					</display:column>
					
					<display:column titleKey="customer.phone" property="telefoneDisponivel" />
				</display:table>
				
			</center>
		
		</html:form>
				
	</body>

</html:html>
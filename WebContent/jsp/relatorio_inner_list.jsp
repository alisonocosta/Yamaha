<%@taglib uri="http://struts.apache.org/tags-bean"    	prefix="bean"    %>
<%@taglib uri="http://struts.apache.org/tags-html"    	prefix="html"    %>
<%@taglib uri="http://struts.apache.org/tags-html-el" 	prefix="html-el" %>
<%@taglib uri="http://struts.apache.org/tags-logic-el"  prefix="logic"   %>
<%@taglib uri="/tld/ym"  	 							prefix="ym"      %>
<%@taglib uri="http://java.sun.com/jstl/core" 			prefix="core"  %>

<%-- 
/* Resource Informática LTDA.
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......relatorio_inner_list.jsp
 *   .: Criação.....7 de outubro de 2008, 12:19
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Tela: Lista de Relatórios JAVA disponível para usuário
 *						  interno da Yamaha.
 */
--%>


<html>
	<head>
		<title><bean:message key="home.title"/></title>
		<html:base/>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
		<link rel="stylesheet" type="text/css" href="../css/yamaha.css"></link>	
		<link rel="stylesheet" type="text/css" href="../css/progressbar/carregador.css"></link>	
		<script language="JavaScript" src="../scripts/Formatter/cpfCnpj.js"></script>
		<script language="JavaScript" src="../scripts/Formatter/cep.js"></script>
		<script language="JavaScript" src="../scripts/progressbar/carregador.js"></script>
		<script type="text/javascript">
			if (document.layers)
				window.document.captureEvents(Event.KEYDOWN);
				
			window.document.onkeydown = function (evt) { 
				var keyCode = evt ? (evt.which ? evt.which : evt.keyCode) : event.keyCode;
								
				if (keyCode == 13) {	
				
					return false;
				}
	 		}
	 		
			function alterarTitulo(title) { 
			
				window.top.location.href ="javascript:newTitle('" + title + "');";
			}   
					
		</script>		
	</head>
	<body leftmargin="0" topmargin="3" onLoad="__loadEsconde();">
	<div id="carregador_pai">
	    <div id="carregador">
	        <div align="center">
	            <img src="../images/run.gif"/>
	            <br /><br />Carregando Aguarde...
	        </div>
	        <div id="carregador_fundo"><div id="barra_progresso"> </div></div>
	    </div>
	</div>
		<html:form action="/Relatorio" method="post">
			<table border="0" cellpadding="2" cellspacing="1">
			
			<logic:iterate name="reportForm" property="listProgramas" id="programa">
			<tr>
				<td class="labelList">
					<html:img srcKey="report.list.img.pdf" altKey="report.list.msg.pdf"/>&nbsp;
					<html-el:link action="${programa.urlAcesso}" 
								  onclick="javascript:__showAnima();return alterarTitulo('${programa.descricao}')" 
								  styleClass="labelList"
								  onmouseover="window.status='${programa.descricao}';return true;"
								  onmouseout="window.status='';return true;">
						<bean:write name="programa" property="descricao"/>
					</html-el:link>						
				</td>				
			</tr>
			</logic:iterate>
			
			<tr>
					<td class="labelList">
						&nbsp;
					</td>				
			</tr>
			</table>
		</html:form>
		</body>
</html>
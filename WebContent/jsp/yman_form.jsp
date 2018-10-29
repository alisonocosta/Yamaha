<%@taglib uri="http://struts.apache.org/tags-html"     prefix="html"    %>
<%@taglib uri="http://java.sun.com/jstl/core" 		   prefix="core" 	%>
<html:html>
	<head>
		<html:base/>
		<meta name="referrer" content="origin">
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="../css/progressbar/carregador.css"></link>
		<script language="JavaScript" src="../scripts/progressbar/carregador.js"></script>
		<script language="JavaScript"  src="../scripts/Pages/yman.js"></script>
		<script type="text/javascript" language="Javascript"> 
			<!-- Begin 
			document.oncontextmenu = function(){return false} 
			// End --> 
		</script> 
	</head>
	<body onLoad="__showAnima();">
		<div id="carregador_pai">
		    <div id="carregador">
		        <div align="center">
		            <br /><br />Direcionado para o YMAN! Selecione um item de menu para continuar no Sistema de  Garantia... 
		        </div>
		        <div id="carregador_fundo"><div id="barra_progresso"> </div></div>
		    </div>
		</div>	
		
		<form name="ymanForm" method="post" target="_blank">
			<input type="hidden" id="flag1" name="flag1" value="<core:out value='${flag1}'/>"/>
			<input type="hidden" id="flag2" name="flag2" value="<core:out value='${flag2}'/>"/>
			<input type="hidden" id="flag3" name="flag3" value="<core:out value='${flag3}'/>"/>
			<input type="hidden" id="flag4" name="flag4" value="<core:out value='${flag4}'/>"/>
		</form>
	</body>
	<script type="text/javascript">
		yman( window.document.getElementById("ymanForm"));		
	</script>
</html:html>
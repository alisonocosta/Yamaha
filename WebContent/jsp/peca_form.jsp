<%@taglib uri="http://struts.apache.org/tags-bean" 	prefix="bean"  %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://struts.apache.org/tags-html"  prefix="html"  %>
<%@taglib uri="http://struts.apache.org/tags-html-el"  prefix="html-el"  %>
<%@taglib uri="/tld/ym"  	 					    prefix="ym"	   %>
<%@taglib uri="http://java.sun.com/jstl/core" 		prefix="core"  %>
<%@page import="java.util.List" %>
<%@page import="java.util.Iterator" %>
<%@page import="br.com.yamaha.sistemagarantia.model.to.PecaTO" %>
<jsp:useBean id="garantiaPecaForm" 
             type="org.apache.struts.action.DynaActionForm" 
			 scope="request"/>
			 
<ym:checkLogon roleName="sg_cd_itsg" />

<%-- 
/* Yamaha
 * Copyright (c) 2012 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......peca_moto_form.jsp
 *   .: Criação.....14 de outubro de 2012, 17:55
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Tela: Formulário de cadastro de peça de garantia para linha de náutica.
 */
--%>

<html:html>
	<head>
		<title><bean:message key="home.title"/></title>
		<html:base/>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
		<link rel="stylesheet" type="text/css" href="../css/yamaha.css"></link>		
		<link rel="stylesheet" type="text/css" href="../css/autocomplete/autocomplete.css"></link>
		<link rel="stylesheet" type="text/css" href="../css/progressbar/carregador.css"></link>
		<script language="JavaScript" src="../scripts/General/form.js"></script>
		<script language="JavaScript" src="../scripts/Formatter/maskDate.js"></script>	
		<script language="JavaScript" src="../scripts/Formatter/cpfCnpj.js"></script>		
		<script language="JavaScript" src="../scripts/Ajax/Base/prototype-1.4.0.js"></script>
		<script language="JavaScript" src="../scripts/Ajax/Base/overlibmws.js"></script>
		<script language="JavaScript" src="../scripts/Ajax/Base/builder.js"></script>
		<script language="JavaScript" src="../scripts/Ajax/Base/scriptaculous.js"></script>
		<script language="JavaScript" src="../scripts/Ajax/Base/ajaxtags-1.2-beta2.js"></script>
		<script language="JavaScript" src="../scripts/progressbar/carregador.js"></script>
		
		<script language="JavaScript">
		
			if (document.layers)
				window.document.captureEvents(Event.KEYDOWN);
				
			window.document.onkeydown = function (evt) { 
				var keyCode = evt ? (evt.which ? evt.which : evt.keyCode) : event.keyCode;
								
				if (keyCode == 13) {	
				
					return false;
				}
	 		}
			
			function removerArquivo(fileId) {
				
				if(window.confirm("Confirma e remoção do arquivo?")) {
					__showAnima();
					submitFormTsk(garantiaPecaForm,'removeFile&fileId=' + fileId,true);
				}
				
				
			}
			
			function voltarSG() {
			
				__showAnima();// Apresenta o layer com a mensagem de processamento da requisição
				
				if ( window.document.getElementById("isEdit").value == "false" ) {
					window.top.location.href ="javascript:newTitle('Incluir Solicitação deGarantia');";
				} else {
					window.top.location.href ="javascript:newTitle('Alteração de Solicitação de  Garantia');";
				}
				window.location.href = "Garantia.do?task=alter&readonly=" + <core:out value="${consulta}"/> + "&garantiaId=" + window.document.garantiaPecaForm.entityId.value;
			
			
			}
			
			function viewPeca() {				
				window.top.location.href ="javascript:newTitle('Consultar Peças');";			
			}
			
			function validateLine(lineId) {
			
				var item  = eval( "document.garantiaPecaForm.itemCode_" + lineId );
				var qtd   = eval( "document.garantiaPecaForm.qtdPeca_" + lineId );
				
				var itemValue = item.value;
				var qtdValue  = qtd.value;
				
				if ( itemValue != "" && itemValue != null ) {
				
					if ( qtdValue == "" || qtdValue == null ) {
					
						window.alert("Por favor, digite uma combinação válida de valores. \n A quantidade é obrigatória para um item.");					    
					}
					
				}
			
			}
			
			// *********************************************************************** //
			// *    Função auxiliar da busca de Item - AJAX					     * //
			// *********************************************************************** //
			function storeItem(field) {
			
					var itemFieldSource   = eval("window.document.garantiaPecaForm.itemCode_" + field);
					var itemFieldTarget   = eval("window.document.garantiaPecaForm.escapedItem_" + field);			
					itemFieldTarget.value = escape(itemFieldSource.value);
					
			}
			
			function posGetItemCode(){
				
				var tgItemCode = window.document.garantiaPecaForm.targetItemCode.value;
				var table      = document.getElementById("values");
				var index      = table.rows.length;
				
				if ( tgItemCode != "NOT FOUND" ) {				
								
					var vItemId    = tgItemCode.substring( 0, tgItemCode.indexOf("@") );
					var isValid    = true;
					
					//alert(vItemId);
					
					var vItemDesc  = tgItemCode.substring( (tgItemCode.indexOf("@") + 1), tgItemCode.lastIndexOf("@") );
					
					var vItemCounter  = tgItemCode.substring( (tgItemCode.lastIndexOf("@") + 1), tgItemCode.length );
					
					//window.alert("Index:" + index + " - vItemId:" +vItemId + " - vItemCounter:" + vItemCounter);
					//alert("vItemId:" + vItemId + " - vItemDesc:" + vItemDesc + " vItemCounter:" + vItemCounter);
					
					// As linhas de peça começam a partir da linha 3 da tabela
					// Se estivermos depois da linha 3, devemos fazer a comparação com as linhas anteriores para validar
					if ( vItemCounter > 1 ) {
						
						for ( i = 1 ; i < vItemCounter ; i++ ){
						
							var objItemIdAnt = eval("window.document.garantiaPecaForm.itemId_" + i);
							//window.alert("objItemIdAnt:" + objItemIdAnt.value + " - vItemId:" + vItemId);
							if ( objItemIdAnt.value == vItemId )
								isValid = false;
						
						} 
					
					}
						
					var objDescItem   = eval("window.document.garantiaPecaForm.descItem_" + vItemCounter);
					var objItemId     = eval("window.document.garantiaPecaForm.itemId_" + vItemCounter);
					var itemCode      = eval( "document.garantiaPecaForm.itemCode_"  + vItemCounter);
					
					if ( isValid ) {
					
						objDescItem.value = vItemDesc;					
						objItemId.value   = vItemId;
						
					} else {					
											
						window.alert("Esta peça já foi informada!");
						itemCode.focus();
						
					}
					
				} else {
				
					window.alert("A peça não foi localizada!");
				
				}
				
				window.document.garantiaPecaForm.targetItemCode.value = "";			
			}
			
			function addRow() {
				
				var counter  = document.forms[0].counter.value;
				var table    = document.getElementById("values");
				var index = table.rows.length;
				
				// Quando não for a primeira linha devemos validar a linha anterior
				if ( counter > 1 ) {
				
					var idLineAnt = table.rows(index - 1).id;
					var ant = Number(idLineAnt);
					
					//window.alert("Linha Anterior:"  + ant + " - Counter:" + counter + " - Rows:" + table.rows.length);
					
					var qtdePecaAnt   = eval( "document.garantiaPecaForm.qtdPeca_"  + ant);
					var itemCodeAnt   = eval( "document.garantiaPecaForm.itemCode_" + ant);
					var vItemCodeAnt  = itemCodeAnt.value;
					var faltanteAnt   = eval( "document.garantiaPecaForm.faltante_" + ant);
					var enviarAnt     = eval( "document.garantiaPecaForm.enviar_"   + ant);
					var cobrarAnt     = eval( "document.garantiaPecaForm.cobrar_"   + ant);
					var isEnviar      = true;
										
					// Verificamos se o item enviar não está checado por padrão
					// Quando for um objeto text ele é true por padrão
					if ( enviarAnt.type == "checkbox" )
						isEnviar = enviarAnt.checked; 
					
					if ( qtdePecaAnt.value == "" || vItemCodeAnt == "" || (faltanteAnt.checked != true && isEnviar != true && cobrarAnt.checked != true) ) {
					
						window.alert("A linha anterior não foi preenchida corretamente!");
						qtdePecaAnt.focus();
						return;					
					
					}	
					
				} 
				
				var lineNumber = document.forms[0].lineNumber.value;
				
				var line = table.insertRow(index);
				line.id = counter;
				line.className = "tr_line";
				var a = line.insertCell(0);
				var b = line.insertCell(1);
				var c = line.insertCell(2);
				var d = line.insertCell(3);
				var e = line.insertCell(4);
								
				a.innerHTML = "<span class='text'>" + lineNumber + "</span>";				
				a.align = "center";
					
				b.innerHTML = "<input type='text' name='qtdPeca_" + counter + "' id='qtdPeca_" + counter + "' size='4' maxlength='4' style='font-family:Verdana; font-size:10px;' onKeyPress='return blockChar(event);' onBlur='validateLine(" + counter + ");' />";
				b.align = "center";
				
				c.innerHTML =   "<input type='text' name='itemCode_" + counter + "' id='itemCode_" + counter + "' class='text_field' size='20' maxlength='30' onkeyup='javaScript:storeItem(" + counter + ")'/>&nbsp;" +					 
								"<span id='indicatorItem_" + counter + "' style='display:none;'><img src='../images/indicator.gif' alt='Carregando...'/></span>" +
								"<input type='hidden' name='escapedItem_" + counter + "' id='escapedItem_" + counter + "' value='null' />" +									
								"<input type='hidden' name='itemId_" + counter + "' id='itemId_" + counter + "' value='null'/>" +
								"&nbsp; - &nbsp;<input type='text' name='descItem_" + counter + "' id='descItem_" + counter + "' class='text_field_menor' readOnly='true'/>";				
								
								new AjaxJspTag.Autocomplete( '<%= request.getContextPath() %>/DeterminaItem.do', {
									minimumCharacters: '6',
									parameters: 'itemCode={escapedItem_' + counter + '},chassi={chassi},counter=' + counter + ',linhaId={linhaId}',
									target: 'targetItemCode', 
									className: 'autocomplete', 
									source: 'itemCode_' + counter ,
									indicator: 'indicatorItem_' + counter,
									postFunction: posGetItemCode
								});	
								
				c.align = "center";										
										
				d.innerHTML = "<table border='0' cellpadding='0' cellspacing='0'>" +
				              "<tr>" +
				              "<td width='25%' class='text'>" +			              	
				              "<input type='checkbox' id='causadora_" + counter + "' name='causadora_" + counter + "' value='true' onclick='checkCausadora(" + counter + ")'/>Causadora" +
					          "</td>" +	 
				              "<td width='25%' class='text'>" +
				              <core:if test="${isCheckEnviar == true}">
				              	"<input type='checkbox' name='enviar_" + counter + "' value='true'/>Enviar" +
					            "</td>" +				              			              
				              </core:if>
				              <core:if test="${isCheckEnviar == false}">	
					              "<input type='checkbox' name='enviar_" + counter + "' value='false'/>Enviar" +
					              "</td>" +
				              </core:if>
				              "<td width='33%' class='text'>" +
				              "<input type='checkbox' name='cobrar_" + counter + "' value='true'/>Cobrar" +
				              "</td>" +
				              "<td width='33%' class='text'>" +				              
				              "<input type='checkbox' name='faltante_" + counter + "' value='true'/>Informação/Faltante" +
				              "</td>" + 
				              "</tr>" +
				              "</table>";
				d.align = "center";

				// A peça causadora não pode ser removida
				<core:if test="${consulta == false}">
				
					e.innerHTML = "<a href='javaScript:deleteRow(" + counter + ")'>" +
								  "<img src='../images/cancelar.jpg' border='0' alt='Remover Peça'/>" +
								  "</a>";
								  
				</core:if>
				<core:if test="${consulta == true}">
				
					e.innerHTML = " - ";
					
				</core:if>
				e.align = "center";

				document.forms[0].counter.value    = ++counter;
				document.forms[0].lineNumber.value = ++lineNumber;
			
			}
			
			/* Função para determinar se o campo enviar pode ser selecionado ou não
			 * @param counter  -  número para recuperação dos objetos
			 */
			function checkEnviar( counter ) {
			
				var objEnviar   = eval(document.getElementById("enviar_" + counter));
				var objFaltante = eval(document.getElementById("faltante_" + counter));
				
				if ( objEnviar != undefined ) {
					if ( objEnviar.type == "checkbox" ) {
						if ( objFaltante.checked ) {
						
							objEnviar.checked = false;
							objEnviar.disabled = true;
						
						} else {
						
							objEnviar.disabled = false;				
							
						}
					}
				}
			}
		
			function addFilledRow(pecaId, lineId, pecaCode, pecaDesc, qtd, enviar, cobrar, faltante, causadora) {
							
				var table = document.getElementById("values");
				var index = table.rows.length;
				var counter    = document.forms[0].counter.value;
				var lineNumber = document.forms[0].lineNumber.value;
				
				//window.alert("Adicionando linha " + lineNumber + " - Line Id: " + counter + " - Index: " + index);
				
				var causadoraCheck 	= "";
				var isCausadora 	= "";
				if (causadora) { 
					causadoraCheck 	= "checked";
					isCausadora 	= "true";
				}
				
				var enviarCheck = "";
				if (enviar) { enviarCheck = "checked"; }

				var cobrarCheck = "";
				if (cobrar) { cobrarCheck = "checked"; }

				var faltanteCheck = "";
				if (faltante) { faltanteCheck = "checked"; }

				//window.alert("enviarCheck: " + enviarCheck + " - cobrarCheck: " + cobrarCheck + " - faltanteCheck: " + faltanteCheck);

				var line = table.insertRow(index);
				line.id = counter;
				line.className = "tr_line";
				var a = line.insertCell(0);
				var b = line.insertCell(1);
				var c = line.insertCell(2);
				var d = line.insertCell(3);
				var e = line.insertCell(4);
				
				a.width = "50px";
				b.width = "60px";
				c.width = "50%";
				e.width = "50px";
				
				a.innerHTML = "<span class='text'>" + lineNumber + "</span>";				
				a.align = "center";
				
				b.innerHTML = "<input type='text' name='qtdPeca_" + counter + "' id='qtdPeca_" + counter + "' size='4' maxlength='4' " +
									" style='font-family:Verdana; font-size:10px;' onKeyPress='return blockChar(event);' value='" + qtd + "' onBlur='validateLine(" + counter + ");' />" +
								    " <input type='hidden' name='linha_" + counter + "' id='linha_" + counter + "' value='" + lineId + "'/>";
				b.align = "center";
				
				c.innerHTML =   "<input type='text' name='itemCode_" + counter + "' id='itemCode_" + counter + " class='text_field' size='20' value='" + pecaCode +"' maxlength='30' onkeyup='javaScript:storeItem(" + counter + ")'/>&nbsp;" +					 
								"<span id='indicatorItem_" + counter + "' style='display:none;'><img src='../images/indicator.gif' alt='Carregando...'/></span>" +
								"<input type='hidden' name='escapedItem_" + counter + "' id='escapedItem_" + counter + "' value='null' />" +									
								"<input type='hidden' name='itemId_" + counter + "' id='itemId_" + counter + "' value='" + pecaId + "'/>" +
								"&nbsp; - &nbsp;<input type='text' name='descItem_" + counter + "' id='descItem_" + counter + "' value='" + pecaDesc + "' class='text_field_menor' readOnly='true'/>";				
								
								new AjaxJspTag.Autocomplete( '<%= request.getContextPath() %>/DeterminaItem.do', {
									minimumCharacters: '6',
									parameters: 'itemCode={escapedItem_' + counter + '},chassi={chassi},counter=' + counter + ',linhaId={linhaId}',
									target: 'targetItemCode', 
									className: 'autocomplete', 
									source: 'itemCode_' + counter ,
									indicator: 'indicatorItem_' + counter,
									postFunction: posGetItemCode
								});	
								
				c.align = "center";

				d.innerHTML = "<table border='0' cellpadding='0' cellspacing='0'>" +
				              "<tr>" +
				                "<td width='25%' class='text'>" +
				              	"<input type='checkbox' id='causadora_" + counter + "' name='causadora_" + counter + "' " + causadoraCheck + " value='"+ isCausadora + "' onclick='checkCausadora(" + counter + ")'/>Causadora" +
					            "</td>" +
				                "<td width='25%' class='text'>" +
				              <core:if test="${isCheckEnviar == true}">
				              	"<input type='checkbox' id='enviar_" + counter + "' name='enviar_" + counter + "' " + enviarCheck + " value='true'/>Enviar" +
					            "</td>" +	              
				              </core:if>
				              <core:if test="${isCheckEnviar == false}">
					              "<input type='checkbox'  id='enviar_" + counter + "'  name='enviar_" + counter + "' " + enviarCheck + " value='false'/>Enviar" +
					              "</td>" +
				              </core:if>
				              "<td width='25%' class='text'>" +
				              "<input type='checkbox'  id='cobrar_" + counter + "'  name='cobrar_" + counter + "' " + cobrarCheck + " value='true'/>Cobrar" +
				              "</td>" +
				              "<td width='25%' class='text'>" +
				              "<input type='checkbox' id='faltante_" + counter + "' name='faltante_" + counter + "' " + faltanteCheck + " value='true'/>Informação/Faltante" +
				              "</td>" + 
				              "</tr>" +
				              "</table>";
				              
				d.align = "center";
				
				// Checkar o campo enviar
				//checkEnviar( counter );
				
				// A peça causadora não pode ser removida
				<core:if test="${consulta == false}">

					e.innerHTML = "<a href='javaScript:deleteAlterRow(" + counter + ")'>" +
								  "<img src='../images/cancelar.jpg' border='0' alt='Remover Peça'/>" +
								  "</a>";
					
				</core:if>
				<core:if test="${consulta == true}">
				
					e.innerHTML = " - ";
				
				</core:if>
				
				e.align = "center";
				
				document.forms[0].counter.value    = ++counter;
				document.forms[0].lineNumber.value = ++lineNumber;
			
			}

			function checkCausadora(counter) {				
				for (var i=0 ; i < window.document.garantiaPecaForm.elements.length ; i++) {
					var x = window.document.garantiaPecaForm.elements[i];
					var fieldname = x.name.substring(0,9);					
			    	if (fieldname == 'causadora') { 			    		
			    		//alert(x.name.substring(10,x.name.length) + " - " + counter);
			    		if(x.name.substring(10,x.name.length)  != counter)
			    			x.checked = "";
					} 
				}
				
			}
		
			function deleteRow(index) {
				
				//var causadora = document.getElementById("causadora_" + index);
				
				//alert(causadora !=  undefined ? causadora.checked : causadora);
			
				//if ( causadora.checked == false ) {
			
					if ( window.confirm("Confirma exclusão da peça?") ) {
												
						var counter = document.forms[0].counter.value;
						var table = document.getElementById("values");
						// Aqui excluímos a linha indicada
						table.deleteRow( document.getElementById(index).rowIndex );
						
						// devemos agora atualizar a numeração das linhas
						var index      = table.rows.length;
						var counter    = document.forms[0].counter.value;
						var lineNumber = 1;
						
						for ( li = 2 ; li < index ; li++ ){						
							
							var line = table.rows(li);
						
							var a = line.cells(0);
							a.innerHTML = "<span class='text'>" +  lineNumber + "</span>";
							a.align = "center";
							++lineNumber;
						
						}
						
						document.forms[0].lineNumber.value = lineNumber;
						document.forms[0].counter.value    = --counter;
	
					}
					
				//} else {
				
					//window.alert("A peça causadora não pode ser removida!");
				
				//}
			
			}
			
			function deleteAlterRow(index) {
			
				//var causadora = document.getElementById("causadora_" + index);
				
				//alert(causadora !=  undefined ? causadora.checked : causadora);
				
				//if ( causadora.checked == "checked" ) {
			
					if ( window.confirm("Confirma exclusão da peça?") ) {
					
						var linha = eval( "document.garantiaPecaForm.linha_" + index );
						var garantiaId = document.garantiaPecaForm.entityId.value;
					
						window.location.href = "GarantiaPeca.do?task=removeLine&garantiaId=" + garantiaId + "&lineId=" + linha.value + "&isZeroKm=" + <core:out value='${isZeroKm}'/>;
	
					}
					
				//} else {
				
					//window.alert("A peça causadora não pode ser removida!");
				
				//}
			
			}
		
			function inicializar() {
			
				<%
					
					if ( garantiaPecaForm.get("filledLines") != null ) {
	
						List filledLines = (List) garantiaPecaForm.get("filledLines");
						if ( filledLines.size() > 0 ) {
							
							//System.out.println("Tamanho:" + filledLines.size());
							
							Iterator filledLinesIt = filledLines.iterator();
							while ( filledLinesIt.hasNext() ) {
								
								PecaTO peca = (PecaTO) filledLinesIt.next();
								
								//System.out.println("PecaId:" + peca.getPecaId() + " -LineId:" + peca.getLineId() + " - PecaCode" + peca.getPecaCode() + " - Descricao:" + peca.getDescricao() + " -Quantidade:" + peca.getQuantidade() );
								//System.out.println("Enviar:" + peca.isEnviar()  + " - Cobrar:" + peca.isCobrar() + " - Faltante:" + peca.isFaltante());
										
								String enviar    = peca.isEnviar()     ? "true" : "false";
								String cobrar    = peca.isCobrar()     ? "true" : "false";
								String faltante  = peca.isFaltante()   ? "true" : "false";
								String causadora = peca.isCausadora()  ? "true" : "false";
								
								//System.out.println("Enviar:" + enviar + " - Cobrar:" + cobrar + " - Faltante:" + faltante);
								
								out.println("addFilledRow('" + peca.getPecaId() + "', '" + peca.getLineId() + "', '" + peca.getPecaCode() + "', '" + peca.getDescricao() + "', " + peca.getQuantidade() + ", " + enviar + ", " + cobrar + ", " + faltante + ", " + causadora + ");");
								
							}
							
						} else {
							
							out.println("addRow();");
							
						}
						
					} else {
						
						out.println("addRow();");
						
					}
				
				%>
				
			}			
			
			/** 
			*	Função para solicitar uma confirmação ao usuário e direcionar para outra Janela
			*   Ela é disparada pelo controle (Action)
			*/
			function redirectGar( loteId ) {
			
				var mensagem = "Pressione OK para incluir uma nova Garantia ou \n" +
							   "Pressione Cancelar para retornar para a Relação de Garantias";
			
				if ( window.confirm(mensagem) ) { 
					__showAnima();
					window.top.location.href ="javascript:newTitle('Incluir Solicitação de Garantia');";
					window.location.href = "Garantia.do?task=addWithNewLote&loteId=" + loteId;
					
				} else {
					__showAnima();
					window.top.location.href ="javascript:newTitle('Relação de Garantias');";
					window.location.href = "Garantia.do?task=list&loteId=" + loteId;	
				
				}
			
			}
			
			function validateForm() {
				
				var counter = document.forms[0].counter.value;
				var onlyServ = window.document.garantiaPecaForm.onlyService;
				/* Se existir apenas uma linha de peça,
				 * devemos verificar se foi informada a peça.
				 */
				if ( (counter - 1) == 1 ) {
				
					var itemCode = eval("window.document.forms[0].itemCode_" + (counter -1) + ".value");
					
					if ( itemCode == "" ) {
					
						if ( !confirm("Confirma a inclusão da Garantia sem peça?") ) 
							return false;
						else {
							onlyServ.value = "true";
						}
					}
					
				}
				
				if(onlyServ.value != undefined  && onlyServ.value != "true") {
					/* Verificamos se a peça causadora foi informada*/
					var hasCausadora = false;
					for (var i=0 ; i < window.document.garantiaPecaForm.elements.length ; i++) {
						var x = window.document.garantiaPecaForm.elements[i];
						var fieldname = x.name.substring(0,9);					
				    	if (fieldname == 'causadora') { 			    		
				    		//alert(x.checked);
				    		if(x.checked)
				    			hasCausadora = true;
						} 
					}
					
					if(!hasCausadora){					
						window.alert("A peça causadora não foi informada!");
						return false;
					}
				}
				__showAnima();
				window.document.garantiaPecaForm.salvar.disabled = true;
				submitFormTsk(garantiaPecaForm,'save',true);
			
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

		<center>

			<html:form action="/GarantiaPeca" method="post" enctype="multipart/form-data">
				<input type="hidden" name="targetItemCode" id="targetItemCode" value="null"/>
				<html:hidden property="counter" styleId="counter"/>				
				<html:hidden property="entityId" styleId="entityId"/>
				<html:hidden property="chassi" styleId="chassi"/>
				<html:hidden property="loteId" styleId="loteId"/>
				<html:hidden property="linhaId" styleId="linhaId"/>
				<html:hidden property="isEdit" styleId="isEdit"/>
				<html:hidden property="onlyService" styleId="onlyService"/>
				<input type="hidden" name="lineNumber" id="lineNumber" value="1" />
		
				<jsp:include flush="true" page="include_alerts.jsp"/>
		
				<table width="98%" border="0" cellpadding="0" cellspacing="2" align="center">
					<tr> 
						<td height="15" colspan="6" align="center" class="tilteList">
							<bean:message key="garantia.form.peca.title.add"/>
						</td>
					</tr>			
					<tr height="25"> 
						<td  width="13%" class="td_dark">	
							<bean:message key="garantia.form.peca.lote"/>:
						</td>
						<td width="11%" class="text">	
							<bean:write name="garantiaPecaForm" property="loteId"/>
						</td>
						<td width="13%" class="td_dark">	
							<bean:message key="garantia.form.peca.numSG"/>:
						</td>
						<td width="23%" class="text">	
							<bean:write name="garantiaPecaForm" property="entityId"/>
						</td>
						<td width="18%" class="td_dark">	
							<bean:message key="garantia.form.peca.chassi"/>:
						</td>
						<td width="22%" class="text">	
							<bean:write name="garantiaPecaForm" property="chassi"/>
						</td>
					</tr>
					<tr height="25"> 
						<td class="td_dark">	
							<bean:message key="garantia.form.peca.numOS"/>:
						</td>
						<td class="text">	
							<bean:write name="garantiaPecaForm" property="numeroOS"/>
						</td>
						<td class="td_dark">	
							<bean:message key="garantia.form.peca.dataAbert"/>:
						</td>
						<td class="text">	
							<bean:write name="garantiaPecaForm" property="dataAberturaOS"/>
						</td>
						<td class="td_dark">	
							<bean:message key="garantia.form.peca.dataFech"/>:
						</td>
						<td class="text">	
							<bean:write name="garantiaPecaForm" property="dataFechamentoOS"/>
						</td>
					</tr>
				</table>
				<p/>
				<table id="values" width="98%" border="0" cellpadding="0" cellspacing="2" align="center"/>
					<tr> 
						<td height="15" colspan="5" align="center" class="tilteList">
							<bean:message key="garantia.form.peca.pecas"/>
						</td>
					</tr>
					<tr> 
						<td width="5%" class="td_dark" align="center">	
							&nbsp;
						</td>
						<td width="11%" class="td_dark" align="center">	
							<bean:message key="garantia.form.peca.qtd"/>
						</td>
						<td  width="50%" class="td_dark" align="center">
							<bean:message key="garantia.form.peca.peca"/>
						</td>						
						<td  width="30%" class="td_dark" align="center">	
							&nbsp;
						</td>
						<td width="4%" class="td_lixeira" align="center">&nbsp;</td>
					</tr>
				</table>					
				<p/>
				<core:if test="${consulta == false}">
				<table border="0" cellpadding="0" cellspacing="0" width="700">
					<tr>
						<td  width="70%" height="10" class="text" align="left">
							<html:image srcKey="form.img.required" altKey="form.msg.required"/>&nbsp;-&nbsp;<bean:message key="form.msg.required"/>
						</td>
						<td height="10" class="text" align="right">
							<input type="button" class="button_medium" name="lancar"  value="Mais Peças" onclick="javascript:addRow();"/>					
						</td>			
					</tr>
				</table>
				<p/>
				<table border="0" cellpadding="0" cellspacing="0" width="700">
					<tr>
						<td class="td_dark"><bean:message key="garantia.form.peca.attachfield"/></td>	
					</tr>					
					<tr>
						<td>
							<html:file property="uploadFile1" styleClass="text_field_maior" accept="application/xml,image/jpeg,image/gif,application/msword,text/plain,application/vnd.ms-excel,application/pdf,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.openxmlformats-officedocument.wordprocessingml.document"/>	
						</td>
					</tr>
					<tr>
						<td>
							<html:file property="uploadFile2" styleClass="text_field_maior" accept="application/xml,image/jpeg,image/gif,application/msword,text/plain,application/vnd.ms-excel,application/pdf,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.openxmlformats-officedocument.wordprocessingml.document"/>	
						</td>
					</tr>
					<tr>
						<td>
							<html:file property="uploadFile3" styleClass="text_field_maior" accept="application/xml,image/jpeg,image/gif,application/msword,text/plain,application/vnd.ms-excel,application/pdf,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.openxmlformats-officedocument.wordprocessingml.document"/>	
						</td>
					</tr>
					<tr>
						<td>
							<html:file property="uploadFile4" styleClass="text_field_maior" accept="application/xml,image/jpeg,image/gif,application/msword,text/plain,application/vnd.ms-excel,application/pdf,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.openxmlformats-officedocument.wordprocessingml.document"/>	
						</td>
					</tr>
					<tr>
						<td>
							<html:file property="uploadFile5" styleClass="text_field_maior" accept="application/xml,image/jpeg,image/gif,application/msword,text/plain,application/vnd.ms-excel,application/pdf,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.openxmlformats-officedocument.wordprocessingml.document"/>	
						</td>
					</tr>
					<tr>
						<td>
							<html:file property="uploadFile6" styleClass="text_field_maior" accept="application/xml,image/jpeg,image/gif,application/msword,text/plain,application/vnd.ms-excel,application/pdf,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.openxmlformats-officedocument.wordprocessingml.document"/>	
						</td>
					</tr>
					<tr>
						<td>
							<html:file property="uploadFile7" styleClass="text_field_maior" accept="application/xml,image/jpeg,image/gif,application/msword,text/plain,application/vnd.ms-excel,application/pdf,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.openxmlformats-officedocument.wordprocessingml.document"/>	
						</td>
					</tr>
					<tr>
						<td>
							<html:file property="uploadFile8" styleClass="text_field_maior" accept="application/xml,image/jpeg,image/gif,application/msword,text/plain,application/vnd.ms-excel,application/pdf,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.openxmlformats-officedocument.wordprocessingml.document"/>	
						</td>
					</tr>					
				</table>
				
				<logic:notEmpty name="garantiaPecaForm" property="listFiles">
					<table border="0" cellpadding="0" cellspacing="2" width="70%">		
					<tr>
						<td class="td_dark"><bean:message key="garantia.file.peca.title"/></td>	
						<td class="td_dark" align="center"><bean:message key="garantia.file.peca.remove"/></td>
					</tr>											
					<logic:iterate name="garantiaPecaForm" property="listFiles" id="file">
						
						<tr>
							<td class="text" width="80%">
								<html-el:link action="/GarantiaPeca.do?task=download&fileId=${file.entityId}"
							 			onmouseover="window.status='${file.filename}';return true;"
										onmouseout="window.status='';return true;">
							 		<bean:write name="file" property="filename"/>
							 	</html-el:link>
							</td>
							<td class='text' align="center">
								<html-el:link href="#"  onclick="javascript:removerArquivo(${file.entityId})"
							 			onmouseover="window.status='${file.filename}';return true;"
										onmouseout="window.status='';return true;">
							 			<html:image src="../images/tb_bg_topo_lixeira.jpg"/>
							 	</html-el:link>
							</td>
						</tr>
					</logic:iterate>
					</table>
				</logic:notEmpty>
				</core:if>	
				<core:if test="${consulta != false}">
				<table border="0" cellpadding="0" cellspacing="0" width="700">
					<tr>
						<td height="10" class="text" align="left">
							<html:image srcKey="form.img.required" altKey="form.msg.required"/>&nbsp;-&nbsp;<bean:message key="form.msg.required"/>
							<html:image srcKey="form.img.required" altKey="garantia.form.peca.msg.causadora"/>
							<html:image srcKey="form.img.required" altKey="garantia.form.peca.msg.causadora"/>&nbsp;-&nbsp;<bean:message key="garantia.form.peca.msg.causadora"/>							
						</td>		
					</tr>
				</table>
				<logic:notEmpty name="garantiaPecaForm" property="listFiles">
					<table border="0" cellpadding="0" cellspacing="0" width="70%">	
					<tr>
						<td class="td_dark"><bean:message key="garantia.file.peca.title"/></td>	
						<td class="td_dark" align="center"><bean:message key="garantia.file.peca.remove"/></td>
					</tr>												
					<logic:iterate name="garantiaPecaForm" property="listFiles" id="file">
						<tr>
							<td class="text">
							 		<html-el:link action="/GarantiaPeca.do?task=download&fileId=${file.entityId}"
								 			onmouseover="window.status='${file.filename}';return true;"
											onmouseout="window.status='';return true;"
											style="text-decoration: underline;">
								 		<bean:write name="file" property="filename"/>
								 	</html-el:link>
							</td>							
						</tr>
					</logic:iterate>
					</table>
				</logic:notEmpty>
				</core:if>	
				<p/>				
				<table border="0" cellpadding="0" cellspacing="0" width="700">
					<tr>
						<td height="10" class="text" align="center" colspan="5">
							<core:if test="${consulta == false}">
								<input type="button" class="button_medium" name="salvar" value="Salvar" onclick="javascript:validateForm();"/>
								&nbsp;
							</core:if>
							<html:button property="backSG" styleClass="button_medium" onclick="javascript:voltarSG();"><bean:message key="form.btn.preview"/></html:button>
						</td>
					</tr>
				</table>	
			</html:form>
			<script type="text/javascript">
				__loadEsconde();
				inicializar();
			</script>

		</center>
		<ym:confirmMessage />
		<ym:javaScriptExecuter/>
		
	</body>
	
	
</html:html>
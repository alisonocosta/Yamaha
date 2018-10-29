 <%@taglib uri="http://struts.apache.org/tags-bean"     prefix="bean"    %>
<%@taglib uri="http://struts.apache.org/tags-html-el"  prefix="html"    %>
<%@taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic"   %>
<%@taglib uri="http://displaytag.sf.net/el" 		   prefix="display" %>
<%@taglib uri="http://java.sun.com/jstl/core" 		   prefix="c" 	    %>
<%@taglib uri="/tld/ym"  	 						   prefix="ym"      %>

<ym:checkLogon roleName="sg_cd_nfconc" />

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
		<link rel="stylesheet" type="text/css" href="../css/yamaha.css"></link>
		<link rel="stylesheet" type="text/css" href="../css/progressbar/carregador.css"></link>
		<script type="text/javascript" src="../scripts/WindowUI/window_utils.js"></script> 
		<script language="JavaScript" src="../scripts/General/form.js"></script>
		<script language="JavaScript" src="../scripts/progressbar/carregador.js"></script>	
		<script language="JavaScript" src="../scripts/Formatter/maskDate.js"></script>	
		<script type="text/javascript">
		<!--
			var totalPeca = deci(0);
			var totalServico = deci(0);
			
			/** Torna uma visível ou oculta as linhas de um lote  com a seleção de +/- 
			 *  @param id   número do lote
			 *  @param emp  empresa YMA ou YMB
			 */
			function viewHide(id, emp) {
			
				var linha = "linha" + emp + "_" + id;
				var sinal = "sinal" + emp + "_" + id;
				
				var targetElement = document.getElementsByTagName("tr");

				for ( i = 0 ; i < targetElement.length ; i++ ) {
				
					var elementId = targetElement[i].id;
					
					var linhaId   = elementId.substring(0,(elementId.indexOf("@")));
					
					if ( linhaId == linha ) {
					
						if ( targetElement[i].style.display == "none" ) {
							targetElement[i].style.display = "";
							document.getElementById(sinal).innerHTML = "-";
							
						} else {
							targetElement[i].style.display = "none";
							document.getElementById(sinal).innerHTML = "+";
						}
						
					}
				}		
			}
			
			/** Verifica se existem loets selcionados */
			function checkLote() {
			
				var  checks = document.getElementsByTagName("input");
				var isChecked = false;
				
				for ( i = 0 ; i < checks.length ; i++ ) {
				
					if ( checks[i].type == "radio" && checks[i].checked ) {
										
						isChecked = true;
					}
				
				}
				
				if ( !isChecked || window.document.notaFiscalListForm.loteId.value == "") {
				
					window.alert("Nenhum lote foi selecionado!");
					return false;
				
				} else 
					return true;
			
			}
			
			/* Troca o decimal de ponto para virgula */
			function convMoeda( valor ) {
								
				texto = valor.toString();
      			texto2 = "";
       			if (texto.indexOf('.') != -1) {
				  for (var i = 0; i < texto.length; i++){
					  if(texto.charAt(i) == ".")
						texto2 += ",";
					  else
						texto2 += texto.charAt(i);
				  }
				  texto = texto2;
			   } 	 
			
				return texto;			
			
			}
			
			/* funcao de formatacao de casas decimais */ 
			function deci(N) {
				N = Math.round(N * 100) / 100;
       			texto = N.toString();
      			texto2 = "";
       			if (texto.indexOf(',') != -1) {
				  for (var i = 0; i < texto.length; i++){
					  if(texto.charAt(i) == ",")
						texto2 += ".";
					  else
						texto2 += texto.charAt(i);
				  }
				  texto = texto2;
			   } 	                    
          	   texto[texto.indexOf(',')] = ".";
       		   ponto = texto.indexOf('.');
    		   if (ponto == -1){
 			   	texto += ".00";
 				Term = texto
    		   } else {
 				texto += "0";
 				decimal = ponto + 3;
 				Term = texto.substring(0,decimal);
    		   }
    		   if (Term == ".0") { 
			  	Term = "0.00";
			   }
       		   if (Term == ".00") { 
			  	Term = "0.00";
			   }
				//if (Term == "NaN.00"){ Term = "Erro";}
				return Term;
			}
			
			/** Seleciona ou retira a seleção dos subitens de um lote 
			 *  @param obj lote cliclado
			 */
			function CheckAll(obj) { 
				loteId = obj.value;
				var isChecked = obj.checked;
				for (var i=0 ; i < window.document.notaFiscalListForm.elements.length ; i++) {
			    	var x = window.document.notaFiscalListForm.elements[i];
			    	if (x.name == 'selectedItemYMA'|| x.name == 'selectedItemYMB') { 
			    		var value = x.value;
			    		if ( value.substring(0,value.indexOf('@')) == loteId )	
							x.checked = isChecked;
						else 
							x.checked = false;
					} else if ( x.name == 'selectedLotesYMA' || x.name == 'selectedLotesYMB' ) {
						if (x.value != loteId )
							x.checked = false;
					}
				}
				window.document.notaFiscalListForm.loteId.value = loteId;
				enableLaunch();	
			} 
			
			/** Retira a seleção de todos os checkbox do form */
			function unCheckedAll() { 
				for (var i=0 ; i < window.document.notaFiscalListForm.elements.length ; i++) {
			    	var x = window.document.notaFiscalListForm.elements[i];
			    	if (x.type == 'checkbox') { 			    		
						x.checked = false;
					} 
				}
				window.document.notaFiscalListForm.loteId.value = "";
				enableLaunch();	
			} 
			
			/** Desabilita ou habilita um checkbox de acordo uma seleção 
			 *  @param emp Empresa YMA/YMB			
			 */
			function disabledCheckbox(emp) { 
					
				var objLote  = 'selectedLotes' + emp;
				var objLinha = 'selectedItem' + emp;
				
				for (var i=0 ; i < window.document.notaFiscalListForm.elements.length ; i++) {
			    	
					var x = window.document.notaFiscalListForm.elements[i];
					
			    	if ( x.type == 'checkbox' ) { 			    		
			    		
			    		var isDisabled = false;
						
			    		if ( x.name == objLote || x.name == objLinha  ) {
						 
			    				isDisabled = true;
			    				
			    		} 
						
						x.disabled = isDisabled;
					} 
				}
			}
			
			/** Habilita os itens de uma empresa para seleção */
			function enableOrg( emp ) {
			
				var linhas = document.getElementById('disp').rows;
				var orgRow;
				var org;
				var id;  
				
				window.document.notaFiscalListForm.loteId.value = "";
								
				for( i = 0 ; i < linhas.length ; i++ ) {
				
					if ( linhas[i].id != undefined ) {
						
						orgRow = linhas[i].id;
						org = orgRow.substring(5,8);
						
						id =  orgRow.substring(orgRow.indexOf('_') + 1,orgRow.indexOf('@'));
						
						if( org == emp ) {
							document.getElementById("disp").rows[i].style.display = "none";
							document.getElementById("sinal" + emp + "_" + id).innerHTML = "+";
						
						} 
						
					}
				}
				
				disabledCheckbox(emp);
				unCheckedAll();				
			
			}
			
			/* Habilita ou desabilita o botão de processar */
			function enableLaunch() {
			
				var isChecked = false;
				var isSelected = false;
				var emp;
				for (var i=0 ; i < window.document.notaFiscalListForm.elements.length ; i++) {
					
					var x = window.document.notaFiscalListForm.elements[i];
					emp = x.name.substring( x.name.length,x.name.length - 3 ); // pegamos a empresa YMA ou YMB
					
					if ( x.type == "checkbox" ) {
						if ( x.checked == true )
							isChecked = true;				
					} else if ( x.type == "radio" ) {
						if ( x.checked == true )
							isSelected = true;						
					} 	
								
				}
				if ( isChecked  && isSelected )
					window.document.getElementById("lancar").disabled = false;		
				else
					window.document.getElementById("lancar").disabled = true;	
								

			}
			
			/** Desabilita os checkbox de outros lotes */
			function disableOthers(obj) {
			
				var objValue = obj.value;
			
				var loteId = objValue.substring(0,objValue.indexOf('@'));
				
				window.document.notaFiscalListForm.loteId.value = loteId;
				
				for (var i=0 ; i < window.document.notaFiscalListForm.elements.length ; i++) {
					
					var x = window.document.notaFiscalListForm.elements[i];
					
					if (x.name == 'selectedItemYMA'|| x.name == 'selectedItemYMB') { 
			    		var value = x.value;
			    		if ( value.substring(0,value.indexOf('@')) != loteId )	
							x.checked = false;
					} else if ( x.name == 'selectedLotesYMA' || x.name == 'selectedLotesYMB' ) {
						if (x.value != loteId )
							x.checked = false;
						else
							x.checked = true;
					}
					
				}
							
			
			}
			
			function informarDtFech(garantiaId, dtAbert) {
				var dtFech = window.prompt("Informe a data de Fechamento da OS para a Garantia " + garantiaId + " no formato " + " dd/mm/aaaa","");
				
				if(!validaData(dtFech)){
					window.alert("Data Inválida!");
					return false;					
				}
				
				if(dtFech == "") {					
					window.alert("Informe a Data de Fechamento!");
					return false;
				}
				
				if(!checkDate(dtAbert,dtFech)){
					return false;
				}
				
				if(!checkDateToday(dtFech)) {
					return false;
				}
				__showAnima();
				window.location.href = "NotaFiscal.do?task=gravarDtFechamento&garantiaId=" + garantiaId + "&dtFech=" + dtFech;
			}
			
			
			/*//////////////////////////////////////////////////////*/
			/*  Checa se data de Abertura da SG é menor que a fechamento */
			/*//////////////////////////////////////////////////////*/
			function checkDate(dtAbert, dtFech) {
	 			 			
	 			if ( dtFech != "" && dtAbert != "" ) {
		 			if ( comparaData(dtAbert,dtFech) > 0 ) {
		 				window.alert("A Data de Fechamento não pode ser menor que a data de Abertura da OS!");
		 				return false;
		 			}
		 		}	 		
		 		
		 		return true;
	 		}
	 		
	 		/*//////////////////////////////////////////////////////*/
			/*  Checa se data de um campo é menor ou igual a data atual */
			/*//////////////////////////////////////////////////////*/
	 		function checkDateToday(data2) {
	 		
	 			var data1 = window.document.getElementById("sysDate").value;
	 			if ( data2 != "" && data1 != "" ) {
		 			if ( comparaData(data2, data1) > 0 ) {
		 				window.alert("Data informada não pode ser maior que a data atual!");
		 				return false;
		 			}
		 		}	 
		 		return true;
	 		}
			
		//-->			
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
		<html:form action="/NotaFiscal" method="post">
			<html:hidden property="loteId"/>
			<html:hidden property="sysDate"/>
			<jsp:include flush="true" page="include_alerts.jsp"/>
			<br>
			<center>
			<table id="disp" width="700" cellpadding="2" cellspacing="2" align="center" style="border: 1 1 1 1 solid #788C9B;">			
			<tr>
				<td align="center" class="tilteList" colspan="6"><center><bean:message key="notaFiscal.msg.title"/></center></td>
			</tr>
			<tr>
				<td align="center" class="td_dark" width="15%"><bean:message key="notaFiscal.list.lote"/></td>
				<td align="center" class="td_dark" width="20%"><bean:message key="notaFiscal.list.tipo"/></td>
				<td align="center" class="td_dark" width="15%"><bean:message key="notaFiscal.list.SG.cupom"/></td>
				<td align="center" class="td_dark" width="15%"><bean:message key="notaFiscal.list.valServico"/></td>
				<td align="center" class="td_dark" width="15%"><bean:message key="notaFiscal.list.valPecas"/></td>
				<td align="center" class="td_dark"><bean:message key="notaFiscal.list.status"/></td>
			</tr>
			<tr>
				<td id="empYMA" class="td_dark" colspan="6"><html:radio property="empresa" value="YMA" onclick="enableOrg('YMB')"/> - <bean:message key="notaFiscal.title.YMA"/></td>	
			</tr>
			<c:set var="lote" value="0"/>		
			<logic:iterate name="notaFiscalListForm" property="listItensYMA" id="notaFiscalVO">				
				
				<c:if test="${lote != notaFiscalVO.loteId}">
					<tr class="tr_line" >
						
						<td class="text">
							<a href="javascript: viewHide(<c:out value='${notaFiscalVO.loteId}'/>, 'YMA');" class="text">
								<span id="sinalYMA_<c:out value='${notaFiscalVO.loteId}'/>">+</span>	
							</a>
							<c:if test="${notaFiscalVO.hasDtFechamento}">
								<html:multibox property="selectedLotesYMA" value="${notaFiscalVO.loteId}" onclick="CheckAll(this);${pageScope.onClickFunctionName}"/>
							</c:if> 
							<c:out value='${notaFiscalVO.loteId}'/>						
						</td>
						<td class="text">
							<c:out value='${notaFiscalVO.tipoLote}'/>				
						</td>
						<td align="right" class="text">
							<b><bean:message key="notaFiscal.list.total"/></b>		
						</td>
						<td align="right" class="text">
							<b><span id="servYMA_<c:out value='${notaFiscalVO.loteId}'/>"></span></b>						</td>
						<td  align="right" class="text">
							<b><span id="pecaYMA_<c:out value='${notaFiscalVO.loteId}'/>"></span></b>
						</td>
						<td class="text">
							<c:out value='${notaFiscalVO.descricaoStatus}'/>
							<script type="text/javascript">
							<!--	
								totalPeca    = 0;
								totalServico = 0;
							//-->			
							</script>	
							<c:set var="lote" value="${notaFiscalVO.loteId}"/>	
							<c:set var="num" value="0"/>					
						</td>
					</tr>
				</c:if>
				
				<tr id="linhaYMA_<c:out value='${notaFiscalVO.loteId}'/>@<c:out value='${num}'/>" style="display:none">		
						<c:if test="${notaFiscalVO.hasDtFechamento}">
							<td>
								&nbsp;				
							</td>
							<td>
								&nbsp;					
							</td>	
						</c:if>	
						<c:if test="${!notaFiscalVO.hasDtFechamento}">		
							<td class="text" colspan="2">
								<html:button property="Confirmar" styleClass="button_medium" onclick="informarDtFech(${notaFiscalVO.entityId},'${notaFiscalVO.maskedDtAberturaOS}')">Incluir Data</html:button>
							</td>
						</c:if>		
						<td class="td_subLine">
							<c:if test="${notaFiscalVO.hasDtFechamento}">
								<html:multibox property="selectedItemYMA" value="${notaFiscalVO.loteId}@${notaFiscalVO.entityId}@${notaFiscalVO.tipoLoteId}" onclick="enableLaunch();disableOthers(this);${pageScope.onClickFunctionName}"/>
							</c:if>							
							<c:out value="${notaFiscalVO.entityId}"/>							
						</td>
						<td align="right" class="td_subLine">
							<span id="valorMOYMA_<c:out value='${notaFiscalVO.entityId}'/>"></span>								
						</td>
						<td align="right" class="td_subLine">
							<span id="valorPecaYMA_<c:out value='${notaFiscalVO.entityId}'/>"></span>
						</td>
						<td class="warningDt">
							<c:if test="${!notaFiscalVO.hasDtFechamento}">
								Falta Data de Fechamento da OS!
							</c:if>
							&nbsp;
							<script type="text/javascript">
							<!--
								document.getElementById("valorPecaYMA_<c:out value='${notaFiscalVO.entityId}'/>").innerHTML = convMoeda( deci( <c:out value='${notaFiscalVO.valorPeca}'/>));
								document.getElementById("valorMOYMA_<c:out value='${notaFiscalVO.entityId}'/>").innerHTML   = convMoeda( deci( <c:out value='${notaFiscalVO.valorServicos}'/> ));									
								
								totalPeca    += <c:out value='${notaFiscalVO.valorPeca}'/>;
								totalServico += <c:out value='${notaFiscalVO.valorServicos}'/>;	
															
								<c:if test="${lote != 0}">
									document.getElementById("servYMA_<c:out value='${notaFiscalVO.loteId}'/>").innerHTML = convMoeda(deci(totalServico));
									document.getElementById("pecaYMA_<c:out value='${notaFiscalVO.loteId}'/>").innerHTML = convMoeda(deci(totalPeca));									
								</c:if>
								
							//-->			
							</script>	
							<c:set var="num" value="${num + 1}"/>						
						</td>					
				</tr>
			</logic:iterate>			
			<!-- </table>-->
			<script type="text/javascript">
				<!--
					<c:if test="${lote != 0}">
						document.getElementById("servYMA_<c:out value='${lote}'/>").innerHTML = convMoeda( deci(totalServico) );
						document.getElementById("pecaYMA_<c:out value='${lote}'/>").innerHTML = convMoeda( deci(totalPeca) );									
					</c:if>
					
					totalPeca    = 0;
					totalServico = 0; 
				//-->			
			</script>	
			<!-- </center> -->
			
			<tr>
				<td id="empYMB" class="td_dark" colspan="6"><html:radio property="empresa" value="YMB" onclick="enableOrg('YMA')"/> - <bean:message key="notaFiscal.title.YMB"/></td>	
			</tr>
			<c:set var="lote" value="0"/>		
			<logic:iterate name="notaFiscalListForm" property="listItensYMB" id="notaFiscalVO">				
				
				<c:if test="${lote != notaFiscalVO.loteId}">
					<tr class="tr_line">
						
						<td class="text">
							<a href="javascript: viewHide(<c:out value='${notaFiscalVO.loteId}'/>, 'YMB');" class="text">
								<span id="sinalYMB_<c:out value='${notaFiscalVO.loteId}'/>">+</span>	
							</a>	
							<c:if test="${notaFiscalVO.hasDtFechamento}">						
								<html:multibox property="selectedLotesYMB" value="${notaFiscalVO.loteId}" onclick="CheckAll(this);${pageScope.onClickFunctionName}"/>
							</c:if> 
							<c:out value='${notaFiscalVO.loteId}'/>						
						</td>
						<td class="text">
							<c:out value='${notaFiscalVO.tipoLote}'/>				
						</td>
						<td align="right" class="text">
							<b><bean:message key="notaFiscal.list.total"/></b>		
						</td>
						<td align="right" class="text">
							<b><span id="servYMB_<c:out value='${notaFiscalVO.loteId}'/>"></span></b>
						</td>
						<td  align="right" class="text">
							<b><span id="pecaYMB_<c:out value='${notaFiscalVO.loteId}'/>"></span></b>
						</td>
						<td class="text">
							<c:out value='${notaFiscalVO.descricaoStatus}'/>
							<script type="text/javascript">
							<!--							
								<c:if test="${notaFiscalVO.loteId != lote}">
									<c:if test="${lote != 0}">
										document.getElementById("servYMB_<c:out value='${lote}'/>").innerHTML = totalServico;
										document.getElementById("pecaYMB_<c:out value='${lote}'/>").innerHTML = totalPeca;									
									</c:if>
								</c:if>
								totalPeca    = 0;
								totalServico = 0;
							//-->			
							</script>	
							<c:set var="lote" value="${notaFiscalVO.loteId}"/>	
							<c:set var="num" value="0"/>					
						</td>
					</tr>
				</c:if>
				
				<tr id="linhaYMB_<c:out value='${notaFiscalVO.loteId}'/>@<c:out value='${num}'/>" style="display:none">						
						<c:if test="${notaFiscalVO.hasDtFechamento}">
							<td>
								&nbsp;				
							</td>
							<td>
								&nbsp;					
							</td>	
						</c:if>	
						<c:if test="${!notaFiscalVO.hasDtFechamento}">		
							<td class="text" colspan="2">
								<html:button property="Confirmar" styleClass="button_medium" onclick="informarDtFech(${notaFiscalVO.entityId},'${notaFiscalVO.maskedDtAberturaOS}')">Incluir Data</html:button>
							</td>
						</c:if>		
						<td class="td_subLine">
							<c:if test="${notaFiscalVO.hasDtFechamento}">
								<html:multibox property="selectedItemYMB" value="${notaFiscalVO.loteId}@${notaFiscalVO.entityId}@${notaFiscalVO.tipoLoteId}" onclick="enableLaunch();disableOthers(this);${pageScope.onClickFunctionName}"/>
							</c:if>							
							<c:out value="${notaFiscalVO.entityId}"/>		
						</td>
						<td align="right" class="td_subLine">
							<span id="valorMOYMB_<c:out value='${notaFiscalVO.entityId}'/>"></span>	
						</td>
						<td align="right" class="td_subLine">
							<span id="valorPecaYMB_<c:out value='${notaFiscalVO.entityId}'/>"></span>
						</td>
						<td class="text_tip">
							<c:if test="${!notaFiscalVO.hasDtFechamento}">
								Falta Data de Fechamento da OS!
							</c:if>
							&nbsp;
							<script type="text/javascript">
							<!--
								document.getElementById("valorPecaYMB_<c:out value='${notaFiscalVO.entityId}'/>").innerHTML = convMoeda( deci( <c:out value='${notaFiscalVO.valorPeca}'/>));
								document.getElementById("valorMOYMB_<c:out value='${notaFiscalVO.entityId}'/>").innerHTML   = convMoeda( deci( <c:out value='${notaFiscalVO.valorServicos}'/> ));									
								
								totalPeca    += <c:out value='${notaFiscalVO.valorPeca}'/>;
								totalServico += <c:out value='${notaFiscalVO.valorServicos}'/>;	
															
								<c:if test="${lote != 0}">
									document.getElementById("servYMB_<c:out value='${notaFiscalVO.loteId}'/>").innerHTML = convMoeda(deci(totalServico));
									document.getElementById("pecaYMB_<c:out value='${notaFiscalVO.loteId}'/>").innerHTML = convMoeda(deci(totalPeca));									
								</c:if>
								
							//-->			
							</script>	
							<c:set var="num" value="${num + 1}"/>						
						</td>					
				</tr>
			</logic:iterate>			
			</table>
			<script type="text/javascript">
				<!--
					<c:if test="${lote != 0}">
						document.getElementById("servYMB_<c:out value='${lote}'/>").innerHTML = convMoeda( deci(totalServico) );
						document.getElementById("pecaYMB_<c:out value='${lote}'/>").innerHTML = convMoeda( deci(totalPeca) );									
					</c:if>
					
					totalPeca    = 0;
					totalServico = 0;
				//-->			
			</script>	
			</center>
			
			
			
			<center>
				<table border="0" cellpadding="2" cellspacing="2" align="center">								
				<tr height="25">
					<td align="center">
 						<html:button property="lancar" styleClass="button_medium" onclick="submitFormTsk(notaFiscalListForm,'launchNote',checkLote())"><bean:message key="form.btn.processar"/></html:button>
						<%--<html:button property="lancar" styleClass="button_medium" onclick="javascript:checkLote();alert('Em Construção!')"><bean:message key="form.btn.processar"/></html:button>--%>		
						&nbsp;
						<html:reset property="limpar" styleClass="button_medium"><bean:message key="form.btn.clear"/></html:reset>	
					</td>
				</tr>
				</table>
			</center>
			
		</html:form>

	</body>
</html:html>
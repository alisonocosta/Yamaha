<%@taglib uri="http://java.sun.com/jstl/core" 		  prefix="c" 	   %>
<%@taglib uri="http://struts.apache.org/tags-bean"    prefix="bean"    %>
<%@taglib uri="http://struts.apache.org/tags-html"    prefix="html"    %>
<%@taglib uri="http://struts.apache.org/tags-html-el" prefix="html-el" %>
<%@taglib uri="http://struts.apache.org/tags-logic"   prefix="logic"   %>
<%@taglib uri="/tld/ym"  	 						  prefix="ym"	   %>
<%@taglib uri="http://ajaxtags.org/tags/ajax" 		  prefix="ajax"    %>

<%-- 
/* Resource Informática LTDA.
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......info_mercado_form.jsp
 *   .: Criação.....19 de agosto de 2007, 23:11
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Tela: Formulário de Informações de Mercado.
 */
--%>

<html:html>
	<head>
		<title><bean:message key="home.title"/></title>
		<html:base/>
		<html:javascript formName="infoMercadoForm"/>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
		<link rel="stylesheet" type="text/css" href="../css/yamaha.css"></link>
		<link rel="stylesheet" type="text/css" href="../css/autocomplete/autocomplete.css"></link>
		<script language="JavaScript" src="../scripts/General/form.js"></script>		
		<script language="JavaScript" src="../scripts/WindowUI/window_utils.js"></script> 
		<script language="JavaScript" src="../scripts/Formatter/cpfCnpj.js"></script>
		<script language="JavaScript" src="../scripts/Formatter/maskDate.js"></script>	
		<script language="JavaScript" src="../scripts/Ajax/Base/prototype-1.4.0.js"></script>
		<script language="JavaScript" src="../scripts/Ajax/Base/overlibmws.js"></script>
		<script language="JavaScript" src="../scripts/Ajax/Base/builder.js"></script>
		<script language="JavaScript" src="../scripts/Ajax/Base/scriptaculous.js"></script>
		<script language="JavaScript" src="../scripts/Ajax/Base/ajaxtags-1.2-beta2.js"></script>
		<script language="JavaScript" src="../scripts/Pages/info_mercado_form.js"></script>
		
		<script type="text/javascript">
		
			function removeImage(fileName, infoMercFotosId) {
			
				if ( confirm("Confirma a remoção da imagem  " + fileName + "?") ) {
				
					window.location.href = "InfoMercado.do?task=removeImage&infoMercFotosId=" + infoMercFotosId;
				
				}
			
			}
			
			function viewImage(idImage,fotoName) {
				
				var windowName = "Foto - " + fotoName;
				var url = "InfoMercado.do?task=dowloaderImage&infoMercFotosId=" + idImage;
				
				mywin = window.open(url,"Yamaha","menubar=0,status=0,location=0,toolbar=0,width=400,height=400");
				
				mywin.moveTo(0,0);
				
			}
		
			function setFocusChassi() {
			
				window.document.infoMercadoForm.chassi.focus();
			
			}
			
			function initProgressCondicao() {
				window.document.getElementById("condicaoProblema").value = escape(window.document.getElementById("condicaoProblema").value);
				
				Element.addClassName('condicaoProblema', 'progressMeterLoading');
			}

			function resetProgressCondicao() {
			
			  Element.removeClassName('condicaoProblema', 'progressMeterLoading');
			  
			  if ($('condicaoProblema').value == "null" )
			  	$('condicaoProblema').value ="";
			
			    /* do cool effect */
			    new Effect.Highlight('condicaoProblema');
			  
			}
			
			function initProgressCausa() {
				window.document.getElementById("causaProblema").value = escape(window.document.getElementById("causaProblema").value);
				
				Element.addClassName('causaProblema', 'progressMeterLoading');
			}

			function resetProgressCausa() {
			
			  Element.removeClassName('causaProblema', 'progressMeterLoading');
			  
			  if ($('causaProblema').value == "null" )
			  	$('causaProblema').value ="";
			
			    /* do cool effect */
			    new Effect.Highlight('causaProblema');
			  
			}
			
			function initProgressDiagnostico() {
				window.document.getElementById("diagnostico").value = escape(window.document.getElementById("diagnostico").value);
				
				Element.addClassName('diagnostico', 'progressMeterLoading');
			}

			function resetProgressDiagnostico() {
			
			  Element.removeClassName('diagnostico', 'progressMeterLoading');
			  
			  if ($('diagnostico').value == "null" )
			  	$('diagnostico').value ="";
			
			    /* do cool effect */
			    new Effect.Highlight('diagnostico');
			  
			}
			
			function initProgressSolucao() {
				window.document.getElementById("solucao").value = escape(window.document.getElementById("solucao").value);
				
				Element.addClassName('solucao', 'progressMeterLoading');
			}

			function resetProgressSolucao() {
			
			  Element.removeClassName('solucao', 'progressMeterLoading');
			  
			  if ($('solucao').value == "null" )
			  	$('solucao').value ="";
			
			    /* do cool effect */
			    new Effect.Highlight('solucao');
			  
			}			
			
			
			// *********************************************************************** //
			// *    Função auxiliar da busca de Item - AJAX					     	 * //
			// *********************************************************************** //
			function storeItem() {
			
					var itemFieldSource   = window.document.infoMercadoForm.itemCode;
					var itemFieldTarget   = window.document.infoMercadoForm.escapedItem;			
					itemFieldTarget.value = escape(itemFieldSource.value);
					
			}
			
			function posGetItemCode(){
				
				var tgItemCode = window.document.infoMercadoForm.targetItemCode.value;
								
				var vItemId    = tgItemCode.substring( 0, tgItemCode.indexOf("@") );
				
				var vItemDesc  = tgItemCode.substring( (tgItemCode.indexOf("@") + 1), tgItemCode.length );
				
				//alert("vItemId:" + vItemId + " - vItemDesc:" + vItemDesc );
					
				var objDescItem   = window.document.infoMercadoForm.descItem;
				var objItemId     = window.document.infoMercadoForm.itemId;
				objDescItem.value = vItemDesc;					
				objItemId.value   = vItemId;
				
				window.document.infoMercadoForm.targetItemCode.value = "";
			
			}
			
			//----[ Função: checkLen() ]-----------------------------------------------------
			/** Move o foco para o próximo objeto, aplicável a caixa de texto
			 *  quando maxLength foi atingido o foco é direcionado para o próximo objeto
			 *
			 *  obj  - Objeto 
			 *  tam  - tamanho do objeto
			 */
			function nextCampo(x,y) {
					
				if (y.length==x.maxLength) {
			
					var isThis =x.name;
						
					if ( isThis == "dataVenda" ) {
				
						window.document.getElementById("infoMercadoForm").dataProblema.focus();
					
					} else if ( isThis == "dataProblema" ) {
				
						window.document.getElementById("infoMercadoForm").cidadeUso.focus();
					
					} 
				}
				
			}	
			
			function getValuesInfo(){
				window.document.getElementById("selldate").click();		
			}
		</script>
		
	</head>
	<body leftmargin="0" topmargin="1" onLoad="javaScript:backup();">
	
		<html:form action="/InfoMercado" enctype="multipart/form-data" method="post" focus="preenchidoPor">
	
			<html:hidden property="entityId"/>
			<html:hidden property="statusImId"/>
			<html:hidden property="concessionariaId"/>
			<html:hidden property="locked"/>
			<html:hidden property="isEdit"/>
			
			<html:hidden property="condicaoProblema_newItems"/>
			<html:hidden property="causaProblema_newItems"/>
			<html:hidden property="diagnostico_newItems"/>
			<html:hidden property="solucao_newItems"/>
	
			<input type="hidden" name="condicaoProblema_backup" value=""/>
			<input type="hidden" name="causaProblema_backup" value=""/>
			<input type="hidden" name="diagnostico_backup" value=""/>
			<input type="hidden" name="solucao_backup" value=""/>	
			<input type="hidden" name="targetItemCode" value=""/>
	
			<center>
				<jsp:include flush="true" page="include_alerts.jsp"/><br>
				
				<logic:equal name="infoMercadoForm" property="hasGarantia" value="true">
					<table width="98%" border="0" cellpadding="2" cellspacing="2">
						<tr>
							<td class="td_dark" width="35%"><bean:message key="infoMercado.form.garantia"/></td>
							<td class="text">
								<html-el:link action="/Garantia.do?task=alter" 
											  style="text-decoration: underline;"
											  paramName="infoMercadoForm"
											  paramProperty="garantiaId"
											  paramId="garantiaId"
											  onmouseout="window.status='';return true;"
								   			  onmouseover="window.status='Garantia';return true;">
									   	
										<bean:write name="infoMercadoForm" property="garantiaId"/>
										
								</html-el:link>								
							</td>
						</tr>
					</table>
				</logic:equal>
				<table width="98%" border="0" cellpadding="2" cellspacing="2">
					<tr>
						<td class="td_dark"><bean:message key="infoMercado.form.data"/></td>
						<td class="td_dark"><bean:message key="infoMercado.form.preenchido"/></td>
						<td class="td_dark"><bean:message key="infoMercado.form.chassi"/></td>
						<td class="td_dark"><bean:message key="infoMercado.form.total.horas"/></td>
						<td class="td_dark"><bean:message key="infoMercado.form.data.venda"/></td>
						<td class="td_dark"><bean:message key="infoMercado.form.data.problema"/></td>
					</tr>
					<tr>
						<td class="text" width="10%"><bean:write name="infoMercadoForm" property="data"/></td>
						<td class="text">
							<html:text property="preenchidoPor" styleClass="text_field_maior" maxlength="50" disabled="${ lockForm }"/>
						</td>
						<td class="text">
							
							<html:text property="chassi" styleClass="text_field" size="18" maxlength="13" onblur="javascript:getValuesInfo();" onkeyup="JavaScript:storeChassi();" disabled="${ lockForm }"/>
							<span id="indicator" style="display:none;">
								<html:img srcKey="form.img.indicador" altKey="form.msg.indicador"/>
							</span>
							<input type="hidden" name="escapedChassi" value="null"/>							
							<input type="hidden" name="targetChassi"  value="null"/>
							
							<ajax:autocomplete baseUrl="${pageContext.request.contextPath}/ListChassiForIM.do"
			                                   source="chassi"
			                                   target="targetChassi"
			                                   minimumCharacters="8"
			                                   className="autocomplete"
			                                   parameters="chassi={escapedChassi}"
			                                   indicator="indicator"/>
							
						</td>
						<td class="text" width="10%"><html:text property="totalHrs" styleClass="text_field_menor" maxlength="10" onkeypress="return blockChar(event);" disabled="${ lockForm }"/></td>
						<td class="text" width="20%">
							<%--	<ym:inputDate icon="../images/icon_calendar.gif" 
								              monthLabels = "Janeiro|Fevereiro|Março|Abril|Maio|Junho|Julho|Agosto|Setembro|Outubro|Novembro|Dezembro" 
								              dayHeaders  = "D|S|T|Q|Q|S|S"
									          todayLabel  = "Hoje"
	   										  fieldName	  = "dataVenda"
									          fieldClass  = "text_field_date"
									          datePattern = "dd/MM/yyyy"
									          fieldLength = "11"
									          readOnly	    ="${lockForm}"
									          onKeyUp	  = "mascara_data('dataVenda',event);nextCampo(this,this.value);"
									          fieldYears  ="4">
									 <bean:write name="infoMercadoForm" property="dataVenda"/>						 
								</ym:inputDate>
								&nbsp;<html:img  srcKey="form.img.required" altKey="form.msg.required"/>
								--%>
								<html:text property="dataVenda" styleClass="text_field_date" readonly="true"></html:text>
								<img src="../images/icon_calendar.gif" border="0" alt="Clique aqui para buscar a data da venda" id="selldate">
							
								<%-- 
								/** Esta TAG busca valores no banco para os campos Data da Venda e Recall
								 *  Ela é disparada na saída do foco do campo chassi que chama a função getGarantiaValues(),
								 *  esta função dispara o evento click() da figura, como especificado no parâmetro action da TAG.
								 --%>
								<ajax:updateField
									  baseUrl="${pageContext.request.contextPath}/GetInfoMercValues.do"
									  source="chassi"
									  target="dataVenda"
									  action="selldate" 
									  parser="new ResponseXmlParser()" 	 				  
									  parameters="chassi={chassi}"/>
						</td>
						<td class="text" width="20%">
								<ym:inputDate icon="../images/icon_calendar.gif" 
			                                  monthLabels =" Janeiro|Fevereiro|Março|Abril|Maio|Junho|Julho|Agosto|Setembro|Outubro|Novembro|Dezembro" 
			                                  dayHeaders  = "D|S|T|Q|Q|S|S"
							                  todayLabel  = "Hoje"
							                  fieldName	  = "dataProblema"
							                  fieldClass  = "text_field_date"
							                  datePattern = "dd/MM/yyyy"
							                  fieldLength = "11"
							                  readOnly	    ="${lockForm}"
							                  onKeyUp	  = "mascara_data('dataProblema',event);nextCampo(this,this.value);"
							                  fieldYears  = "4">
									<bean:write name="infoMercadoForm" property="dataProblema"/>						 
								</ym:inputDate>
								&nbsp;<html:img  srcKey="form.img.required" altKey="form.msg.required"/>
						</td>
					</tr>
				</table>
		
				<p/>
		
				<table width="98%" border="0" cellpadding="2" cellspacing="2">
					<tr>
						<td class="td_dark" width="20%"><bean:message key="infoMercado.form.tipo.combust"/></td>
						<td class="td_dark" width="20%"><bean:message key="infoMercado.form.local.uso"/></td>
						<td class="td_dark" width="20%"><bean:message key="infoMercado.form.tipo.uso"/></td>
						<td class="td_dark" width="20%"><bean:message key="infoMercado.form.casco.util"/></td>
						<td class="td_dark" width="20%"><bean:message key="infoMercado.form.helice"/></td>
					</tr>
					<tr>
						<td class="text" align="left" valign="top">
						
							<table border="0" cellpadding="2" cellspacing="1" width="100%">
								<logic:iterate name="infoMercadoForm" property="listTipoGasolina" id="tipoGasolina">
									<tr>
										<td class="text"><html-el:radio property="tipoGasolinaId" value="${tipoGasolina.entityId}" disabled="${ lockForm }"/> <c:out value="${tipoGasolina.descricao}"/></td>
									</tr>
								</logic:iterate>					
							</table>
						
						</td>
						<td class="text" align="left" valign="top">
						
							<table border="0" cellpadding="2" cellspacing="1" width="100%">
								<tr>
									<td class="text"><html-el:checkbox property="localUsoSalg" value="S" disabled="${ lockForm }"/> <bean:message key="infoMercado.form.agua.salgada"/></td>
								</tr>
								<tr>
									<td class="text"><html-el:checkbox property="localUsoDoce" value="S" disabled="${ lockForm }"/> <bean:message key="infoMercado.form.agua.doce"/></td>
								</tr>
								<tr>
									<td class="text"><bean:message key="infoMercado.form.cidade.uso"/>:</td>
								</tr>
								<tr>
									<td class="text"><html:text property="cidadeUso" styleClass="text_field_maior" maxlength="30" disabled="${ lockForm }"/></td>
								</tr>
							</table>
						
						</td>
						<td class="text" align="left" valign="top">
						
							<table border="0" cellpadding="2" cellspacing="1" width="100%">
								<logic:iterate name="infoMercadoForm" property="listTipoUso" id="tipoUso">
									<tr>
										<td class="text"><html-el:radio property="tipoUso" value="${tipoUso.entityId}" disabled="${ lockForm }"/> <c:out value="${tipoUso.descricao}"/></td>
									</tr>
								</logic:iterate>							
							</table>
						
						</td>
						<td class="text" valign="top">
						
							<table border="0" cellpadding="2" cellspacing="1" width="100%">
								<tr>
									<td class="text"><bean:message key="infoMercado.form.casco.marca"/>:</td>
								</tr>
								<tr>
									<td class="text"><html:text property="marcaCasco" styleClass="text_field_maior" maxlength="20" disabled="${ lockForm }"/></td>
								</tr>
								<tr>
									<td class="text"><bean:message key="infoMercado.form.casco.modelo"/>:</td>
								</tr>
								<tr>
									<td class="text"><html:text property="modeloCasco" styleClass="text_field_maior" maxlength="20" disabled="${ lockForm }"/></td>
								</tr>
							</table>
						
						</td>
						<td class="text" valign="top">
		
							<table border="0" cellpadding="2" cellspacing="1" width="100%">
								<tr>
									<td align="left" valign="top">
		
										<table border="0" cellpadding="2" cellspacing="1" width="100%">
											<tr>
												<td class="text"><bean:message key="infoMercado.form.helice.marca"/>:</td>
											</tr>
											<tr>
												<td class="text"><html:text property="marcaHelice" styleClass="text_field_maior" maxlength="20" disabled="${ lockForm }"/></td>
											</tr>
											<tr>
												<td class="text"><bean:message key="infoMercado.form.helice.passo"/>:</td>
											</tr>
											<tr>
												<td class="text"><html:text property="passoHelice" styleClass="text_field_maior" maxlength="15" onkeypress="return blockChar(event);" disabled="${ lockForm }"/></td>
											</tr>
										</table>
		
									</td>
									<td align="left" valign="top">
										<table border="0" cellpadding="2" cellspacing="1" width="100%">
											<tr>
												<td class="text"><html:radio property="materHelice" value="I" disabled="${ lockForm }"/><bean:message key="infoMercado.form.helice.material.inox"/></td>
											</tr>
											<tr>
												<td class="text"><html:radio property="materHelice" value="A" disabled="${ lockForm }"/><bean:message key="infoMercado.form.helice.material.alum"/></td>
											</tr>
											<tr>
												<td class="text"><html:radio property="materHelice" value="P" disabled="${ lockForm }"/><bean:message key="infoMercado.form.helice.material.plas"/></td>
											</tr>
										</table>
									
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				
				<p/>
				
				<table width="98%" border="0" cellpadding="2" cellspacing="2">
					<tr>
						<td class="td_dark" width="60%"><bean:message key="infoMercado.form.peca.causadora"/></td>
						<td class="td_dark" width="40%"><bean:message key="infoMercado.form.rotacao.maxima"/></td>
					</tr>
					<tr>
						<td class="text">
							<div id="msg" style="display:none;border:1px solid #e00;background-color:#fee;padding:2px;margin-top:8px;width:300px;font:normal 11px Arial;color:#900"></div>
							<%--<html:select property="itemId" styleClass="listSelect">
								<html:optionsCollection property="listItems" label="codeDescricao" value="entityId"/>
							</html:select>	
							<ajax:select
									  baseUrl="${pageContext.request.contextPath}/LoadItens.do"
									  source="chassi"
									  target="itemId"	
									  eventType="blur"						  
									  parameters="chassi={chassi}"
									  preFunction="initProgress"
									  postFunction="finalProgress"
									  errorFunction="reportError"/>	--%>
							
							<html:text property="itemCode" styleClass="text_field" size="20" maxlength="30" onkeyup="javaScript:storeItem()"/>&nbsp;					 
							<span id="indicatorItem" style="display:none;"><img src="../images/indicator.gif" alt="Carregando..."/></span>
							<html:img  srcKey="form.img.required" altKey="form.msg.required"/>
							<input type="hidden" name="escapedItem" value="null"/>
							<html:hidden property="itemId"/>
							&nbsp;<html:text property="descItem" styleClass="text_field_menor" readonly="true"/>				
							
							<ajax:autocomplete
						  		baseUrl   		  ="${pageContext.request.contextPath}/DeterminaItemInfoMerc.do"
								minimumCharacters = "8"
								parameters 		  = "itemCode={escapedItem},chassi={chassi},linhaId=2"
								target 		      = "targetItemCode" 
								className 		  = "autocomplete" 
								source 			  = "itemCode"
								indicator 		  = "indicatorItem" 
								postFunction 	  = "posGetItemCode"/>
									
						</td>
						<td class="text"><html:text property="rotacaoMaxima" styleClass="text_field_maior" maxlength="15" onkeypress="return blockChar(event);"/></td>
					</tr>
				</table>
		
				<p/>
				
				<table width="98%" border="0" cellpadding="2" cellspacing="2">
					<tr>
						<td>
							<table border="0" cellpadding="2" cellspacing="0" width="100%">
								<tr>
									<td class="td_dark"><bean:message key="infoMercado.form.msg.sintoma"/>&nbsp;<html:img  srcKey="form.img.required" altKey="form.msg.required"/></td>
								</tr>
								<tr>
									<td class="text"><bean:message key="infoMercado.form.condicao"/></td>
								</tr>

								<c:if test="${statusImId == 1 || statusImId == 3}" >
									<tr>
										<td>
											<html:textarea property="condicaoProblema" styleClass="text_field_maior" style="width:100%" rows="6" disabled="disabled"/>	
										</td>
									</tr>
									<tr id="condicaoProblema_inputLine" style="display:none">
										<td align="right" width="100%">
											<table border="0" cellpadding="0" cellspacing="0" width="100%">
												<tr>
													<td class="td_dark" width="10%" align="right"><bean:message key="form.lbl.add"/>:&nbsp;</td>
													<td>
														<input type="text" name="condicaoProblemaNewText" id="condicaoProblemaTxt" class="text_field_maior"/>															
													</td>
													<td width="20%" align="right">														
														<html:button property="saveCondicao" styleClass="button_medium"><bean:message key="form.btn.save"/></html:button>
														&nbsp;
														<button name="cancelCondicao"  class="button_medium" onClick="JavaScript:showHideInput('condicaoProblema');"><bean:message key="form.btn.cancel"/></button>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr id="condicaoProblema_addLine" style="display:show">
										<td align="right">
											<button name="insertCondicao" class="button_medium" onClick="JavaScript:showHideInput('condicaoProblema');"><bean:message key="form.lbl.add"/></button>
										</td>
									</tr>
								</c:if>
								<c:if test="${statusImId != 1 && statusImId != 3}" >
									<tr>
										<td>
											<html:textarea property="condicaoProblema" styleClass="text_field_maior" style="width:100%" rows="6"/>
										</td>
									</tr>
								</c:if>
								
							</table>
						</td>
					</tr>
				</table>
		
				<p/>
				
				<table width="98%" border="0" cellpadding="2" cellspacing="2">
					<tr>
						<td>
							<table border="0" cellpadding="2" cellspacing="0" width="100%">
								<tr>
									<td class="td_dark"><bean:message key="infoMercado.form.msg.causa"/>&nbsp;<html:img  srcKey="form.img.required" altKey="form.msg.required"/></td>
								</tr>
								<tr>
									<td class="text"><bean:message key="infoMercado.form.causa"/></td>
								</tr>
								
								<c:if test="${statusImId == 1 || statusImId == 3}" >
									<tr>
										<td>
											<html:textarea property="causaProblema" styleClass="text_field_maior" style="width:100%" rows="6" disabled="disabled"/>
										</td>
									</tr>
									<tr id="causaProblema_inputLine" style="display:none">
										<td align="right" width="100%">
											<table border="0" cellpadding="0" cellspacing="0" width="100%">
												<tr>
													<td class="td_dark" width="10%" align="right"><bean:message key="form.lbl.add"/>:&nbsp;</td>
													<td><input type="text" name="causaProblemaNewText" id="causaProblemaTxt" class="text_field_maior"/></td>
													<td width="20%" align="right">
														<html:button property="saveCausa" styleClass="button_medium"><bean:message key="form.btn.save"/></html:button>
														<%--<button class="button_medium" onClick="JavaScript:addText('causaProblema');"><bean:message key="form.btn.save"/></button>--%>
														&nbsp;
														<button class="button_medium" onClick="JavaScript:showHideInput('causaProblema');"><bean:message key="form.btn.cancel"/></button>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr id="causaProblema_addLine" style="display:show">
										<td align="right">
											<button class="button_medium" onClick="JavaScript:showHideInput('causaProblema');"><bean:message key="form.lbl.add"/></button>
										</td>
									</tr>
								</c:if>
								<c:if test="${statusImId != 1 && statusImId != 3}" >
									<tr>
										<td>
											<html:textarea property="causaProblema" styleClass="text_field_maior" style="width:100%" rows="6"/>
										</td>
									</tr>
								</c:if>
								
							</table>
						</td>
					</tr>
				</table>
		
				<p/>
				
				<table width="98%" border="0" cellpadding="2" cellspacing="2">
					<tr>
						<td>
							<table border="0" cellpadding="2" cellspacing="0" width="100%">
								<tr>
									<td class="td_dark"><bean:message key="infoMercado.form.msg.diagnostico"/>&nbsp;<html:img  srcKey="form.img.required" altKey="form.msg.required"/></td>
								</tr>
								<tr>
									<td class="text"><bean:message key="infoMercado.form.diagnostico"/></td>
								</tr>
								
								<c:if test="${statusImId == 1 || statusImId == 3}" >
									<tr>
										<td>
											<html:textarea property="diagnostico" styleClass="text_field_maior" style="width:100%" rows="6" disabled="disabled"/>
										</td>
									</tr>
									<tr id="diagnostico_inputLine" style="display:none">
										<td align="right" width="100%">
											<table border="0" cellpadding="0" cellspacing="0" width="100%">
												<tr>
													<td class="td_dark" width="10%" align="right"><bean:message key="form.lbl.add"/>:&nbsp;</td>
													<td><input type="text" name="diagnosticoNewText" id="diagnosticoProblemaTxt" class="text_field_maior"/></td>
													<td width="20%" align="right">
														<html:button property="saveDiagnostico" styleClass="button_medium"><bean:message key="form.btn.save"/></html:button>
														&nbsp;
														<button class="button_medium" onClick="JavaScript:showHideInput('diagnostico');"><bean:message key="form.btn.cancel"/></button>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr id="diagnostico_addLine" style="display:show">
										<td align="right">
											<button class="button_medium" onClick="JavaScript:showHideInput('diagnostico');"><bean:message key="form.lbl.add"/></button>
										</td>
									</tr>
								</c:if>
								<c:if test="${statusImId != 1 && statusImId != 3}" >
									<tr>
										<td>
											<html:textarea property="diagnostico" styleClass="text_field_maior" style="width:100%" rows="6"/>
										</td>
									</tr>
								</c:if>								
								
							</table>
						</td>
					</tr>
				</table>
		
				<p/>
				
				<table width="98%" border="0" cellpadding="2" cellspacing="2">
					<tr>
						<td>
							<table border="0" cellpadding="2" cellspacing="0" width="100%">
								<tr>
									<td class="td_dark"><bean:message key="infoMercado.form.acao.solucao"/>&nbsp;<html:img  srcKey="form.img.required" altKey="form.msg.required"/></td>
								</tr>
								<tr>
									<td class="text"><bean:message key="infoMercado.form.acao.tomada"/></td>
								</tr>
								
								<c:if test="${statusImId == 1 || statusImId == 3}" >
									<tr>
										<td>
											<html:textarea property="solucao" styleClass="text_field_maior" style="width:100%" rows="6" disabled="disabled"/>
										</td>
									</tr>
									<tr id="solucao_inputLine" style="display:none">
										<td align="right" width="100%">
											<table border="0" cellpadding="0" cellspacing="0" width="100%">
												<tr>
													<td class="td_dark" width="10%" align="right"><bean:message key="form.lbl.add"/>:&nbsp;</td>
													<td><input type="text" name="solucaoNewText" id="solucaoTxt" class="text_field_maior"/></td>
													<td width="20%" align="right">
														<html:button property="saveSolucao" styleClass="button_medium"><bean:message key="form.btn.save"/></html:button>
														&nbsp;
														<button class="button_medium" onClick="JavaScript:showHideInput('solucao');"><bean:message key="form.btn.cancel"/></button>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr id="solucao_addLine" style="display:show">
										<td align="right">
											<button class="button_medium" onClick="JavaScript:showHideInput('solucao');"><bean:message key="form.lbl.add"/></button>
										</td>
									</tr>
								</c:if>
								<c:if test="${statusImId != 1 && statusImId != 3}" >
									<tr>
										<td>
											<html:textarea property="solucao" styleClass="text_field_maior" style="width:100%" rows="6"/>											
										</td>
									</tr>
								</c:if>									
								
								
							</table>
						</td>
					</tr>					
					<c:if test="${statusImId != 1 && statusImId != 3}" >
						<tr>
							<td class="td_dark"><bean:message key="infoMercado.form.attachfield"/></td>	
						</tr>					
						<tr>
							<td>
								<html:file property="imageFile1" styleClass="text_field_maior" accept="image/jpeg"/>	
							</td>
						</tr>
						<tr>
							<td>
								<html:file property="imageFile2" styleClass="text_field_maior" accept="image/jpeg"/>	
							</td>
						</tr>
						<tr>
							<td>
								<html:file property="imageFile3" styleClass="text_field_maior" accept="image/jpeg"/>	
							</td>
						</tr>
					</c:if>
					<c:if test="${statusImId == 1 || statusImId == 3}" >
					
						<tr>
							<td class="td_dark"><bean:message key="infoMercado.form.attachedfield"/></td>	
						</tr>	
						<tr>
							<td>
								<logic:notEmpty name="infoMercadoForm" property="listFotos">
									<table border="0" cellpadding="0" cellspacing="0" width="70%">												
									<logic:iterate name="infoMercadoForm" property="listFotos" id="foto">
										<tr id="<c:out value='${foto.entityId}'/>">
											<td class="text" width="50%">
												<%--<html-el:link href="javascript:viewImage('${foto.entityId}','${foto.filename}')" style="text-decoration: underline;">--%>
													<bean:write name="foto" property="filename"/>
												<%--</html-el:link>--%>
											</td>
											<td>
												<a href="javascript:removeImage('<c:out value='${foto.filename}'/>',<c:out value='${foto.entityId}'/>)"><img src='../images/cancelar.jpg' border='0' alt='Remover Imagem'/></a>
												<input type="hidden" name="<c:out value = '${foto.filename}' />" value="<c:out value='${foto.entityId}'/>" />
											</td>
										</tr>
									</logic:iterate>
									</table>
								</logic:notEmpty>
							</td>
						</tr>
									
						<tr>
							<td class="td_dark"><bean:message key="infoMercado.form.attachfield"/></td>	
						</tr>					
						<tr>
							<td>
								<html:file property="imageFile1" styleClass="text_field_maior" accept="image/jpeg"/>	
							</td>
						</tr>
						<tr>
							<td>
								<html:file property="imageFile2" styleClass="text_field_maior" accept="image/jpeg"/>	
							</td>
						</tr>
						<tr>
							<td>
								<html:file property="imageFile3" styleClass="text_field_maior" accept="image/jpeg"/>	
							</td>
						</tr>				
					
					</c:if>
						
					<tr height="25">
						<td colspan="2" align="center">
							<html:button property="gravar" styleClass="button_medium" onclick="JavaScript:submitFormTsk(infoMercadoForm,'save',validateInfoMercadoForm(infoMercadoForm));"><bean:message key="form.btn.save"/></html:button>
							&nbsp;
							<html:button property="limpar" styleClass="button_medium" onclick="JavaScript:resetInfoMercadoForm();"><bean:message key="form.btn.clear"/></html:button>
							&nbsp;
							<html:button property="cancelar" styleClass="button_medium" onclick="JavaScript:window.top.Windows.close(window.top.Windows.focusedWindow.getId());"><bean:message key="form.btn.cancel"/></html:button>	
						</td>
					</tr>
					<tr> 
						<td height="15" colspan="2">&nbsp;</td>
					</tr>
					<tr> 
						<td height="25" class="text" colspan="2"><img src="../images/icon_required.gif" alt="form.msg.required"/>&nbsp;-&nbsp;Campo Obrigatório</td>
					</tr>								
				</table>
				<%-- Função AJAX para incluir a Condição do Problema --%>
				<ajax:updateField
					  baseUrl="${pageContext.request.contextPath}/InsertCondicaoProblema.do"
					  source="condicaoProblemaTxt"
					  target="condicaoProblema"
					  action="saveCondicao"
					  parser="new ResponseXmlParser()"						  
					  parameters="condicaoTxt={condicaoProblemaTxt},entityId={entityId}" 
					  preFunction="initProgressCondicao"
					  postFunction="resetProgressCondicao"/>
					  
				<%-- Função AJAX para incluir a Causa do Problema --%>
				<ajax:updateField
					  baseUrl="${pageContext.request.contextPath}/InsertCausaProblema.do"
					  source="causaProblemaTxt"
					  target="causaProblema"
					  action="saveCausa"
					  parser="new ResponseXmlParser()"						  
					  parameters="causaTxt={causaProblemaTxt},entityId={entityId}" 
					  preFunction="initProgressCausa"
					  postFunction="resetProgressCausa"/>
					  
				<%-- Função AJAX para incluir a Diagnóstico do Problema --%>
				<ajax:updateField
					  baseUrl="${pageContext.request.contextPath}/InsertDiagnosticoProblema.do"
					  source="diagnosticoProblemaTxt"
					  target="diagnostico"
					  action="saveDiagnostico"
					  parser="new ResponseXmlParser()"						  
					  parameters="diagnosticoTxt={diagnosticoProblemaTxt},entityId={entityId}" 
					  preFunction="initProgressDiagnostico"
					  postFunction="resetProgressDiagnostico"/>
					  
				<%-- Função AJAX para incluir a Solução do Problema --%>
				<ajax:updateField
					  baseUrl="${pageContext.request.contextPath}/InsertSolucaoProblema.do"
					  source="solucaoTxt"
					  target="solucao"
					  action="saveSolucao"
					  parser="new ResponseXmlParser()"						  
					  parameters="solucaoTxt={solucaoTxt},entityId={entityId}" 
					  preFunction="initProgressSolucao"
					  postFunction="resetProgressSolucao"/>
			</center>
		
		</html:form>
		<ym:alertMessage />
		<ym:javaScriptExecuter/>
	</body>
</html:html>
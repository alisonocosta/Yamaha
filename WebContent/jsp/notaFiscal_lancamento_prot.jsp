<%@taglib uri="http://struts.apache.org/tags-bean"     prefix="bean"    %>
<%@taglib uri="http://struts.apache.org/tags-html-el"  prefix="html"    %>
<%@taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic"   %>
<%@taglib uri="http://displaytag.sf.net/el" 		   prefix="display" %>
<%@taglib uri="http://java.sun.com/jstl/core" 		   prefix="core" 	%>
<%@taglib uri="/tld/ym"  	 						   prefix="ym"      %>
<%@taglib uri="http://ajaxtags.org/tags/ajax" 		   prefix="ajax"    %>

<ym:checkLogon roleName="sg_cd_nfconc" />

<%-- 
/* Resource Inform�tica LTDA.
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......notaFiscal_lancamento_prot.jsp
 *   .: Cria��o.....15 de setembro de 2008, 09:23
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descri��o...Tela: Tela para lancamento de notas fiscais.
 */
--%>

<html:html>
	<head>
		<title><bean:message key="home.title"/></title>
		<html:base/>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>	
		<link rel="stylesheet" type="text/css" href="../css/yamaha.css"></link>
		<link rel="stylesheet" type="text/css" href="../css/progressbar/carregador.css"></link>
		<link rel="stylesheet" type="text/css" href="../css/autocomplete/autocomplete.css"></link>
		<link rel="stylesheet" type="text/css" href="../css/modal/subModal.css" />
		<script language="JavaScript" src="../scripts/General/form.js"></script>
		<script language="JavaScript" src="../scripts/Formatter/currency_format.js"></script>
		<script language="JavaScript" src="../scripts/Formatter/maskDate.js"></script>
		<script language="JavaScript" src="../scripts/progressbar/carregador.js"></script>	
		<script language="JavaScript" src="../scripts/Ajax/Base/prototype-1.4.0.js"></script>
		<script language="JavaScript" src="../scripts/Ajax/Base/overlibmws.js"></script>
		<script language="JavaScript" src="../scripts/Ajax/Base/builder.js"></script>
		<script language="JavaScript" src="../scripts/Ajax/Base/scriptaculous.js"></script>
		<script language="JavaScript" src="../scripts/Ajax/Base/ajaxtags-1.2-beta2.js"></script>
		<script type="text/javascript" src="../scripts/General/common.js"></script>
		<script type="text/javascript" src="../scripts/General/subModal.js"></script>
		<script language="JavaScript" src="../scripts/Objetos/objPeca.js"></script>
		<script language="JavaScript" src="../scripts/Objetos/objServico.js"></script>
		<script type="text/javascript">			
		
			if (document.layers)
				window.document.captureEvents(Event.KEYDOWN);
				
			window.document.onkeydown = function (evt) { 
				var keyCode = evt ? (evt.which ? evt.which : evt.keyCode) : event.keyCode;
								
				if (keyCode == 13) {	
					window.document.getElementById("lancar").click();
				} 
	 		} 
	 		
			// Vari�vel para determinar o id dos campos inseridos din�micamente o form
			var fields = 0;	
			
			// Defini��o dos objetos para relacionamento de item Servi�o ou Pe�a com nota fiscal
			var pecas       = new Array();
			var remessas    = new Array();
			var servicos    = new Array();
			var servicos3os = new Array();			
			<core:if test="${idTipoLote == 1}">
				var i = new Number(0);
				<logic:iterate name="notaFiscalListForm" property="listMov" id="cupomNFVO">
					servicos[i++] = new Servico(  
													<core:out value="${cupomNFVO.cupomId}"/>
													,  ""
													,"<bean:message key='notaFiscal.revision'/>"
													,'<core:out value="${cupomNFVO.valorTotalRevisao}"/>'
													,'<core:out value="${cupomNFVO.formatedValorTotalRevisao}"/>'
													,'<core:out value="${cupomNFVO.chassi}"/>'
													,'<core:out value="${cupomNFVO.descricaoRevisao}"/>'
												);											
				</logic:iterate>
			</core:if>	
			
			<core:if test="${idTipoLote == 2}">
				var iSgMO    = new Number(0); // �ndice para os objetos de Servi�os de Garantia
				var iSgMO3s  = new Number(0); // �ndice para os Objetos de M�o de Obrea de Terceiros
				var iSgPeca  = new Number(0); // �ndice para os Objetos de Pe�as
				var iSgRemes = new Number(0); // �ndice para os Objetos de Remessa
				var iSgConj  = new Number(0); // �ndice para os Objetos de Conjugada
				<logic:iterate name="notaFiscalListForm" property="listMov" id="garantia">
					<core:if test="${garantia.valorTotalServicos > 0}">
						
						// Servi�os de M�o de Obra de Garantia
						servicos[iSgMO++]     = new Servico(  
																<core:out value="${garantia.entityId}"/>
																, ""
																,"<bean:message key='notaFiscal.guarantee'/>"
																,'<core:out value="${garantia.valorTotalServicos}"/>'
																,'<core:out value="${garantia.formatedValorTotalServicos}"/>'
																,'<core:out value="${garantia.modelo}"/>'
																,""
															);
				
					</core:if>
					<core:if test="${garantia.valorServicoTerceiro > 0}">
						// Servi�os de M�o de Obra de Terceiros para a Garantia
						servicos3os[iSgMO3s++] = new Servico(
																<core:out value="${garantia.entityId}"/>
																,""
																,"<bean:message key='notaFiscal.guarantee'/>"
																,'<core:out value="${garantia.valorServicoTerceiro}"/>'
																,'<core:out value="${garantia.formatedValorServicoTerceiro}"/>'
																,'<core:out value="${garantia.modelo}"/>'
																,""
															);
					</core:if>
					
					// Pe�as a cobrar da Garantia
					<logic:iterate name="garantia" property="listGarantiaLine" id="garantiaLine">
						<core:if test="${garantiaLine.cobraPeca == 'S'}">
							pecas[iSgPeca++] = new CobraPeca( 
															  <core:out value="${garantia.entityId}"/>
															, <core:out value="${garantiaLine.compositeEntityId.lineId}"/>
															, <core:out value="${garantiaLine.quantidade}"/>
															, '<core:out value="${garantiaLine.item.descricao}"/>'
															, '<core:out value="${garantiaLine.item.itemCode}"/>'
															, <core:out value="${garantiaLine.valorTotalPeca}"/>
															, '<core:out value="${garantiaLine.formatedValorTotalPeca}"/>'
														);
						</core:if>						
						<core:if test="${garantiaLine.enviaPeca == 'S'}">
							// Pe�as a serem enviadas para a Yamaha
							remessas[iSgRemes++] = new CobraPeca( 
															  <core:out value="${garantia.entityId}"/>
															, <core:out value="${garantiaLine.compositeEntityId.lineId}"/>
															, <core:out value="${garantiaLine.quantidade}"/>
															, '<core:out value="${garantiaLine.item.descricao}"/>'
															, '<core:out value="${garantiaLine.item.itemCode}"/>'
															, <core:out value="${garantiaLine.valorRemessaPeca}"/>
															, '<core:out value="${garantiaLine.formatedValorRemessaPeca}"/>'
														);
						
						</core:if>			
					</logic:iterate>
				</logic:iterate>
			</core:if>	
			
			/**
			 * Fun��o para relacionar uma nota fiscal com os itens selecionados
			 * @param numeroNF
			 *    N�mero da nota fiscal
			 * @param tipo
			 *   Tipo do item para Associar 
			 *   - 'P' para Pe�a
			 *   - 'R' para Remessa
			 *   - 'S' para Servi�os
			 *   - 'M' para M�o de Obra de Terceiros
			 *
			 * @return itens
			 *    Array contendo os itens assoaciados a nota
			 **/
			function relacionaItemNota(numeroNF, tipo) {
				// Vari�vel para determinar se existe item selecionado para associar com as pe�as
				var hasItemSelected = false;
				var itens			= "";
				var itensSel  		= new Array();
				var msg 			= "";
				
				switch(tipo) {
				
					case 'P': itensSel = pecas;
							  msg = "N�o existem itens de pe�as associados a nota " + numeroNF + "!";
							  break;					
					case 'R': itensSel = remessas;
							  msg = "N�o existem remessa de pe�as associados a nota " + numeroNF + "!";
							  break;
					case 'S': itensSel = servicos;	
							  msg = "N�o existem servi�os associados a nota " + numeroNF + "!";
							  break;
					case 'M': itensSel = servicos3os;
							  msg = "N�o existem itens de m�o de obra de terceiros associados a nota " + numeroNF + "!";
							  break;
							  
					default: return itens;
				}
						
				/* Vamos setar os objetos dos itens selecionados com os dados da Nota Fiscal */
				for ( ix = 0; ix < itensSel.length ; ix++ ) {
				
					if ( itensSel[ix].getIsSelected() && !itensSel[ix].getIsNota() ) {
					
						itensSel[ix].setIsNota(true);
						itensSel[ix].setNumNota(numeroNF);
						hasItemSelected = true;
						
						// Montamos a concatena��o dos itens para enviar para o servidor
						// Quando for pe�a ou remessa enviamos tamb�m o lineId
						// O caracater 'L' separa o id do item do lineId
						// O caracter '@' separa os itens
						if ( itens == "" )
							if ( tipo == 'P' || tipo == 'R' )
								itens = itensSel[ix].id  + "L" + itensSel[ix].lineId;
							else
								itens = itensSel[ix].id;
						else
							if ( tipo == 'P' || tipo == 'R' )
								itens += "@" + itensSel[ix].id  + "L" + itensSel[ix].lineId;								
							else
								itens += "@" + itensSel[ix].id; 
					}
				
				}	
				
				if ( !hasItemSelected ) {
				
					window.alert(msg);
				
				}
				
				return itens;
			}
			
			/**
			 * Fun��o para retornar o valor total dos itens selecionados
			 * 
			 * @param tipo
			 *   Tipo do item para Associar 
			 *   - 'P' para Pe�a
			 *   - 'R' para Remessa
			 *   - 'S' para Servi�os
			 *   - 'M' para M�o de Obra de Terceiros
			 *
			 * @return valor
			 **/
			function getValorItemNota(tipo) {
				// Vari�vel para determinar se existe item selecionado para associar com as pe�as
				var valor 			= new Number(0);
				var itens			= "";
				var itensSel  		= new Array();
				
				switch(tipo) {
				
					case 'P': itensSel = pecas;
							  break;					
					case 'R': itensSel = remessas;
							  break;
					case 'S': itensSel = servicos;
							  break;
					case 'M': itensSel = servicos3os;
							  break;
							  
					default: return itens;
				}
						
				/* Vamos setar os objetos dos itens selecionados com os dados da Nota Fiscal */
				for ( ix = 0; ix < itensSel.length ; ix++ ) {
				
					if ( itensSel[ix].getIsSelected() && !itensSel[ix].getIsNota() ) {
						
						valor += itensSel[ix].valor;						
					}
				}
				
				return valor;
			}
					
			/** Insere uma linha com uma nota na tabela de lan�amentos */
			function insRow() {	
			
				/* Pegamos os valores dos objetos para atualizar os campos de totais e incluir uma nota */
				var emitenteNota     = window.document.forms(0).emitenteNota.value;
				var destinatarioNota = window.document.forms(0).destinatarioNota.value;
				var numeroNF         = window.document.forms(0).numeroNF.value;
				var serieNF          = window.document.forms(0).serieNF.value;
				var dateNF           = window.document.forms(0).dateNF.value;
				var serviceValueNF   = Number(moedaTofloat(window.document.forms(0).serviceValueNF.value));
				var pecaValueNF      = Number(moedaTofloat(window.document.forms(0).pecaValueNF.value));
				var remessaValueNF   = Number(moedaTofloat(window.document.forms(0).remessaValueNF.value));
				var mo3ValueNF	     = Number(moedaTofloat(window.document.forms(0).mo3ValueNF.value));
				//var icmsValueNF	     = Number(deci(window.document.forms(0).icmsValueNF.value));
				var linhaHidden      = window.document.forms(0).linhaHidden.value;	
			
				// vari�veis que identificam o tipo de nota 
				var isServico = window.document.getElementById("servico").checked;
				var isPeca	  = window.document.getElementById("peca").checked ;
				var isRemessa = window.document.getElementById("remessa").checked;
				var isMO3	  = window.document.getElementById("maoObra3").checked;
				var isConj	  = window.document.getElementById("conjugada").checked;
				
				// Quando o destinat�rio for Cliente a nota fiscal n�o pode ser de MO3 ou Remessa
				if ( destinatarioNota == "Cliente" && (isMO3 || isRemessa) ) {
					window.alert("Destinat�rio inv�lido para o Tipo da Nota Fiscal!");
					window.document.forms(0).serviceValueNF.focus();
					return false;
				}
				
				//*******************************************************************//
				//* Rotina para:													*//
				//* Validar os valores informados de acordo com o tipo da nota; 	*//
				//* Associar os itens selecionados com a Nota Fiscal informada		*//
				//*******************************************************************//
				var itensServ = "";  // Id dos itens de Servi�os
				var itensMO3  = "";  // Id dos itens de Servi�os de M�o de Obra de Terceiros
				var itensRem  = "";  // Id dos itens de Remessa
				var itensPec  = "";  // Id dos itens de Pe�a
				// Para nota conjugada
				if ( isConj ) {
					
					var valorServ = getValorItemNota('S');
					var valorMo   = getValorItemNota('M');
					var valorPc   = getValorItemNota('P');
					//window.alert("Soma PC:" + valorPc + " - Valor digitado:" + pecaValueNF);					
					//window.alert("Soma SV:" + valorServ + " - Valor digitado:" + serviceValueNF);					
					//window.alert("Soma MO:" + valorMo + " - Valor digitado:" + mo3ValueNF);
										
					if ( valorServ > 0 ) {
						if ( ((valorServ - serviceValueNF) > 1) || ((serviceValueNF - valorServ) > 1) ) {
							window.alert("O valor de servi�o � inv�lido!");
							return false;
						}
					}
					
				
				}
				//Para nota de Servi�o
				if ( isServico  ) {
				
					if ( serviceValueNF <= 0 || serviceValueNF == "") {
						window.alert("O valor de servi�o n�o pode ser menor ou igual a 0(Zero)!");
						window.document.forms(0).serviceValueNF.focus();
						return false;
					} 				
				} 
				// Para nota de Pe�a
				if ( isPeca  ) {
				
					if ( pecaValueNF <= 0 || pecaValueNF == "") {
						window.alert("O valor de pe�a n�o pode ser menor ou igual a 0(Zero)!");
						window.document.forms(0).pecaValueNF.focus();
						return false;
					}
				} 
				// Para nota de Remessa
				if ( isRemessa  ) {
				
					if ( remessaValueNF <= 0 || remessaValueNF == "") {
						window.alert("O valor de remessa n�o pode ser menor ou igual a 0(Zero)!");
						window.document.forms(0).remessaValueNF.focus();
						return false;
					}
				} 
				// Para nota de MO3
				if ( isMO3  ) {
				
					if ( mo3ValueNF <= 0 || mo3ValueNF == "") {
						window.alert("O valor de M.O.3 n�o pode ser menor ou igual a 0(Zero)!");
						window.document.forms(0).mo3ValueNF.focus();
						return false;
					}
				} 
				
				// Caso n�o tenha tipo da nota selecionado
				if ( !isServico && !isPeca  && !isRemessa && !isMO3 && !isConj) {
					window.alert("O Tipo da Nota n�o foi selecionado!");
					return false;
				}
				
				// Agora podemos relacionar
				itensServ = isServico ? relacionaItemNota(numeroNF, 'S') : "";
				itensMO3  = isMO3     ? relacionaItemNota(numeroNF, 'M') : "";
				itensRem  = isRemessa ? relacionaItemNota(numeroNF, 'R') : "";
				itensPec  = isPeca    ? relacionaItemNota(numeroNF, 'P') : "";
				if ( isConj ) {
					itensServ = relacionaItemNota(numeroNF, 'S');
					itensMO3  = relacionaItemNota(numeroNF, 'M');					
					itensPec  = relacionaItemNota(numeroNF, 'P');
				}
				
				// Incrementamos a vari�vel que determina o id dos campos
				fields++;
				
				if ( fields > 1 ) {
					for( i = 1 ; i <= fields ; i++ ) {
					
						var numNf     = eval("window.document.getElementById('numeroNF_" + i + "')");
						var numNfComp = eval("window.document.getElementById('numeroNF_COMP_" + i + "')");
						var serNF     = eval("window.document.getElementById('serieNF_" + i + "')");
						var serNFComp = eval("window.document.getElementById('serieNF_COMP_" + i + "')");
						//alert( "Objeto existe:" + (numNf != undefined) );
						
						if ( numNf != undefined ) {
							//alert( numNf.value + " - " + numeroNF);
							if ( (numNf.value == numeroNF) && (serNF.value == serieNF) ) {	
						
								window.alert("A nota fiscal n�mero " + numeroNF + " de s�rie " + serieNF + ", j� foi lan�ada!");
								--fields;
								return false;					
								
							}
						} else if ( numNfComp != undefined ) {
						
							if ( (numNfComp.value == numeroNF) && (serNFComp.value == serNFComp) ) {	
						
								window.alert("A nota fiscal n�mero " + numeroNF + " de s�rie " + serieNF + ", j� foi lan�ada!");
								--fields;
								return false;					
								
							}
							
						}
					}  
				}	
				
				var o = window.document.getElementById("values");	
				var cont = o.rows.length;			
				
				var id = "l" + cont;
				var x = o.insertRow(cont);
				x.id  = id;
				x.className = "tr_line";
				var a = x.insertCell(0);
				var b = x.insertCell(1);
				var c = x.insertCell(2);
				var d = x.insertCell(3);
				var e = x.insertCell(4);
				var f = x.insertCell(5);
				var g = x.insertCell(6);
				var h = x.insertCell(7);
				var i = x.insertCell(8);
				var j = x.insertCell(9);
				//var l = x.insertCell(10);	
				
				a.innerHTML = "<span class='text' >" + emitenteNota + "</span>" + 
							  "<input type='hidden' name='emitenteNota_" + fields + "' value='" + emitenteNota + "'>" +
							  "<input type='hidden' name='linhaNota_" + fields + "' value='" + linhaHidden + "'>" +
							  "<input type='hidden' name='serviceCheck_" + fields + "' value='" + isServico + "'>" +
							  "<input type='hidden' name='pecaCheck_" + fields + "' value='" + isPeca + "'>" +
							  "<input type='hidden' name='remessaCheck_" + fields + "' value='" + isRemessa + "'>" +
							  "<input type='hidden' name='mo3Check_" + fields + "' value='" + isMO3 + "'>" +
							  "<input type='hidden' name='conjCheck_" + fields + "' value='" + isConj + "'>";
							  
				b.innerHTML = "<span class='text' >" + destinatarioNota + "</span>" + 
							  "<input type='hidden' name='destinatarioNota_" + fields + "' value='" + destinatarioNota + "'>";
								
				c.innerHTML="<span class='text' >" + numeroNF + "</span>" + 
							"<input type='hidden' name='numeroNF_" + fields + "' value='" + numeroNF + "'>" +
							"<input type='hidden' name='itensServNF_" + fields + "'  value='" + itensServ + "'>" +
							"<input type='hidden' name='itensPecNF_"  + fields + "'  value='" + itensPec + "'>" +
							"<input type='hidden' name='itensRemNF_"  + fields + "'  value='" + itensRem + "'>" +
							"<input type='hidden' name='itensMO3NF_"  + fields + "'  value='" + itensMO3 + "'>" ;
						
				c.align="center";
				
				d.innerHTML="<span class='text'>" + serieNF + "</span>" + 
							"<input type='hidden' name='serieNF_" + fields + "' value='" + serieNF + "'>";
							
				d.align="center";
							
				e.innerHTML="<span class='text'>" + dateNF + "</span>" + 
							"<input type='hidden' name='dateNF_" + fields + "' value='" + dateNF + "'>";
							
				e.align="center";
				
				var valorMask = "0,00";
				if ( window.document.forms(0).serviceValueNF.value != "" ) 
					valorMask = window.document.forms(0).serviceValueNF.value;
				
				f.innerHTML="<span class='text' align='right'>" + valorMask + "</span>" + 
							"<input type='hidden' name='serviceValueNF_" + fields + "' value='" + serviceValueNF + "' >";
							
				f.align="right";
				
				valorMask = "0,00";
				if ( window.document.forms(0).pecaValueNF.value != "" ) 
					valorMask = window.document.forms(0).pecaValueNF.value;
							
				g.innerHTML="<span class='text' align='right'>" + valorMask + "</span>"+ 
							"<input type='hidden' name='pecaValueNF_" + fields + "' value='" + pecaValueNF + "' >";
				
				g.align="right";
				
				valorMask = "0,00";
				if ( window.document.forms(0).remessaValueNF.value != "" ) 
					valorMask = window.document.forms(0).remessaValueNF.value;
										
				h.innerHTML="<span class='text' align='right'>" + valorMask + "</span>"+ 
							"<input type='hidden' name='remessaValueNF_" + fields + "' value='" + remessaValueNF + "' >";	
							
				h.align="right";
				
				valorMask = "0,00";
				if ( window.document.forms(0).mo3ValueNF.value != "" ) 
					valorMask = window.document.forms(0).mo3ValueNF.value;
				
				i.innerHTML="<span class='text' align='right'>" + valorMask + "</span>"+ 
							"<input type='hidden' name='mo3ValueNF_" + fields + "' value='" + mo3ValueNF + "' >";	
							
				i.align="right";
				
				//j.innerHTML="<span class='text' align='right'>" + convMoeda(deci(icmsValueNF)) + "</span>"+ 
				//		"<input type='hidden' name='icmsValueNF_" + fields + "' value='" + icmsValueNF + "' >";	
							
				//j.align="right";
				
				j.innerHTML = "<span class='text' align='center'><a href=javascript:removeRow('" + cont + "','" + fields + "'); > " +	
							  " <img src='../images/cancelar.jpg' border='0' alt='Remover Nota'/></a>&nbsp; ";
							<%--  " <a href=javascript:incluirNota('" + cont + "','" + id + "');> " +
							  "<img src='../images/icon_c.gif' border='0' alt='Nota Complementar'/></a></span>";	--%>
				
				j.align = "center";	
				
				if ( isServico && !isPeca )
					updateValues( serviceValueNF, linhaHidden, "vm", "ADD");
					
				if ( !isServico && isPeca )
					updateValues( pecaValueNF, linhaHidden, "vp", "ADD");
					
				if ( isServico && isPeca ) {
					updateValues( serviceValueNF, linhaHidden, "vm", "ADD");
					updateValues( pecaValueNF, linhaHidden, "vp", "ADD");					
				}
				
				if ( isRemessa )
					updateValues( remessaValueNF, linhaHidden, "vr", "ADD");
					
				if ( isMO3 )
					updateValues( mo3ValueNF, linhaHidden, "vmt", "ADD");
					
				if ( isConj ) {
					updateValues( mo3ValueNF, linhaHidden, "vmt", "ADD");
					updateValues( serviceValueNF, linhaHidden, "vm", "ADD");
					updateValues( pecaValueNF, linhaHidden, "vp", "ADD");
				}
				
				
				window.document.getElementById("contField").value = fields;
				
				/* vamos retornar ao estado default os campos de valores */
				resetValues();
				
				/* Quando os valores estiverem zerados habilitamos o bot�o gravar */
				window.document.getElementById("gravar").disabled = !validateValues();
				
			}
			
			/**
			 * Fun��o para verificar se existem itens sem relacioamento com Nota Fiscal
			 *
			 * @param tipo
			 *   Tipo do item para Associar 
			 *   - 'P' para Pe�a
			 *   - 'R' para Remessa
			 *   - 'S' para Servi�os
			 *   - 'M' para M�o de Obra de Terceiros
			 *
			 * @return boolean
			 *    TRUE  - Quando existir item sem relacionamento
			 *    FALSE - Quando todos os itens estiverem relacionados
			 **/
			function hasNotSelectedItem(tipo) {
			
				var itensSel = new Array();
				var hasItem  = false;
				
				switch(tipo) {
				
					case 'P': itensSel = pecas;
							  break;					
					case 'R': itensSel = remessas;
							  break;
					case 'S': itensSel = servicos;
							  break;
					case 'M': itensSel = servicos3os;
							  break;
							  
					default: return itens;
				}
						
				// Vamos percorrer a cole��o para verificar os itens
				for ( var ix = 0; ix < itensSel.length ; ix++ ) {
				
					if ( !itensSel[ix].getIsSelected() && !itensSel[ix].getIsNota() )
						hasItem = true;	
										
				}				
				
				return hasItem;
			
			}
			
			//********************************************************************************************//
			//* Realiza a verifica��o dos valores de saldos retornando TRUE se os saldo estiverem  todos *//
			//* zerados ou dentro da toler�ncia parametrizada 											 *//
			//********************************************************************************************//  
			function validateValues() {
			
				var vmSaldo  = Number(deci(window.document.getElementById("vmSaldoTotalHidden").value));
				var vpSaldo  = Number(deci(window.document.getElementById("vpSaldoTotalHidden").value));
				var vrSaldo  = Number(deci(window.document.getElementById("vrSaldoTotalHidden").value));
				var vmtSaldo = Number(deci(window.document.getElementById("vmtSaldoTotalHidden").value));
				
				var individualLaunch = window.document.getElementById("individualLaunch").value;
				
				//window.alert(window.document.getElementById("toleranciaNota").value);
								
				var tolerancia = Number(window.document.getElementById("toleranciaNota").value);
				
				
				//window.alert( "VM:" + vmSaldo + " - VP:" + vpSaldo + " - VR:" + vrSaldo + " - VMT:" + vmtSaldo + " - Tol:" + tolerancia );
				
				var isVmSaldoOk;
				var isVpSaldoOk;
				var isVrSaldoOk;
				var isVmtSaldoOk;
				
				// Verificamos se todos os itens de pe�as est�o associados a nota
				var isItemPecaNota       = hasNotSelectedItem('P'); 
				var isItemServicoNota    = hasNotSelectedItem('S');
				var isItemRemessaNota    = hasNotSelectedItem('R');
				var isItemServico3osNota = hasNotSelectedItem('M');
				
				//window.alert("isItemPecaNota:" + isItemPecaNota + " -isItemServicoNota:" + isItemServicoNota + " -isItemRemessaNota:" + isItemRemessaNota + " -isItemServico3osNota:" + isItemServico3osNota);
				
				//* Se a toler�ncia enviada como par�metro dor diferente de zero, devemos conferir a diferen�a */
				//*  para mais ou para menos. */
				if ( tolerancia != 0 ) {
				
					isVmSaldoOk  = (vmSaldo  <= tolerancia) && (vmSaldo  >=  (tolerancia * (-1)));
					isVpSaldoOk  = (vpSaldo  <= tolerancia) && (vpSaldo  >=  (tolerancia * (-1)));
					isVrSaldoOk  = (vrSaldo  <= tolerancia) && (vrSaldo  >=  (tolerancia * (-1)));
					isVmtSaldoOk = (vmtSaldo <= tolerancia) && (vmtSaldo >=  (tolerancia * (-1)));
					
				} else {
				
					isVmSaldoOk  = (vmSaldo  == 0); 
					isVpSaldoOk  = (vpSaldo  == 0); 
					isVrSaldoOk  = (vrSaldo  == 0); 
					isVmtSaldoOk = (vmtSaldo == 0); 
				
				}
				
				//window.alert( "VM:" + isVmSaldoOk + " - VP:" + isVpSaldoOk + " - VR:" + isVrSaldoOk + " - VMT:" + isVmtSaldoOk );
			
				// Verificamos se o lan�amento foi individual
				if ( individualLaunch != "S" ) {
				
					if ( isVmSaldoOk )
						window.document.getElementById("vsControle").className = "td_title_ok";
					else if ( vmSaldo < 0 )
						window.document.getElementById("vsControle").className = "td_title_neg";
					else
						window.document.getElementById("vsControle").className = "tr_line";
						
					if ( isVpSaldoOk )
						window.document.getElementById("vpControle").className = "td_title_ok";
					else if ( vpSaldo < 0 )
						window.document.getElementById("vpControle").className = "td_title_neg";
					else
						window.document.getElementById("vpControle").className = "tr_line";
						
					if ( isVrSaldoOk )
						window.document.getElementById("vrControle").className = "td_title_ok";
					else if ( vrSaldo < 0 )
						window.document.getElementById("vrControle").className = "td_title_neg";
					else
						window.document.getElementById("vrControle").className = "tr_line";
						
					if ( isVmtSaldoOk )
						window.document.getElementById("vmControle").className = "td_title_ok";
					else if ( vmtSaldo < 0 )
						window.document.getElementById("vmControle").className = "td_title_neg";
					else
						window.document.getElementById("vmControle").className = "tr_line";
				
					if ( isVmSaldoOk && isVpSaldoOk && isVrSaldoOk && isVmtSaldoOk ) {
						if ( isItemPecaNota || isItemServicoNota || isItemServico3osNota || isItemRemessaNota ) {
							window.document.getElementById("messageNota").innerHTML = "<bean:message key='notaFiscal.launch.msg.error.pieceNotAssoc'/>";
							window.document.getElementById("messageNota").style.display = "";
							setTimeout("window.document.getElementById('messageNota').style.display = 'none'", 3500);
							
							return false;
						} else 
							return true;
					} else
						return false;	
						
				} else {
					// Quando for individual, ser� pemitido lan�amento com diferen�a no valor da remessa
					
					// Setamos a flag isDif para indicar se existe diferen�a ou n�o para o controle (Action)
					// "S" - Com diferen�a  e "N" - Sem diferen�a
					if ( !isVrSaldoOk ){
						//window.alert("Alterando o status de isDif para S");
						window.document.getElementById("isDif").value = "S";						
					} else { 
						//window.alert("Alterando o status de isDif para N");
						window.document.getElementById("isDif").value = "N";
					}
					
					if ( isVmSaldoOk && isVpSaldoOk && isVmtSaldoOk ) {
						
						if ( isItemPecaNota || isItemServicoNota || isItemServico3osNota || isItemRemessaNota ) {
							window.document.getElementById("messageNota").innerHTML = "Existem valores sem associa��o com Nota Fiscal!";							
							return false;
						} else 
							return true;
					} else
						return false;	
										
				}					
			
			}
			
			/* Atualiza a coluna de valores lan�ados da �rea de Controle de Saldos
			 *
			 * @param valueNF
 			 *   Valor
			 *
			 * @param linha
			 *   linha escolhida para atualiza��o - YMB, YMA ou cliente
			 
			 * @param coluna
			 *   prefixo dos objetos que devem sr atualizados
			 *
			 * @param operacao
			 *   "ADD"  para adicionar linha e "DEL" para remover linha
			*/			
			function updateValues(valueNF, linha, coluna, operacao) {
				
				/* Pegamos os valores da �rea de saldo da linha correspondente para realizar a atualiza��o */
				// Valor do objeto Hidden  da coluna Lan�ado								
				var lancHidden = Number(deci(eval("window.document.forms(0)." + coluna + "LancHidden_" + linha + ".value")));
								
				// Valor do objeto Hidden com o total da coluna  Lan�ado				
				var lancTotalHidden = Number(deci(eval("window.document.forms(0)." + coluna + "LancTotalHidden.value")));	
								
				// Valor do objeto Hidden da coluna Saldo
				var saldoHidden= Number(deci(eval("window.document.forms(0)." + coluna + "SaldoHidden_" + linha + ".value")));
				
				// Valor do objeto Hidden com o total da coluna Saldo				
				var saldoTotalHidden = Number( deci(eval( "window.document.forms(0)." + coluna + "SaldoTotalHidden.value")));	
											
				// Objeto Hidden da coluna Lan�ado
				var objLancHidden   = eval("window.document.forms(0)." + coluna + "LancHidden_" + linha);
				// Objeto Span para apresentar o valor lan�ado na tela
				var objLanc         = eval("window.document.getElementById('" + coluna + "Lanc_" + linha + "')");
				
				// Objeto Hidden da coluna Saldo
				var objSaldoHidden   = eval("window.document.forms(0)." + coluna + "SaldoHidden_" + linha);
				// Objeto Span para apresentar o valor Saldo na tela
				var objSaldo         = eval("window.document.getElementById('" + coluna + "Saldo_" + linha + "')");
				
				// Vari�veis 
				var newLanc 	  = new Number(); // novo valor lan�ado
				var newLancTotal  = new Number(); // novo total de valor lan�ado
				var newSaldo 	  = new Number(); // Novo saldo
				var newSaldoTotal = new Number(); // Novo valor total do saldo
				var newValueNF    = new Number(valueNF);
				
				/* Quando for uma opera��o de adicionar uma linha, somamos os valores*/
				if ( operacao == "ADD" ) {	
							
					newLanc = newValueNF + lancHidden;
					
					newLancTotal = lancTotalHidden + newValueNF;
					
					newSaldo = saldoHidden - newValueNF;					
					
					newSaldoTotal = saldoTotalHidden - newValueNF;

				
				} else if ( operacao == "DEL" ) {
				
					/* Quando for uma opera��o de remover uma linha, subtraimos os valores*/
					newLanc   = lancHidden - valueNF;
					newLancTotal = lancTotalHidden - valueNF;
					
					newSaldo = saldoHidden + valueNF;
					newSaldoTotal = saldoTotalHidden + valueNF;
				
				}
				
				objLancHidden.value = newLanc.toFixed(2);
				objLanc.innerHTML   = convMoeda( newLanc.toFixed(2) );
								
				objSaldoHidden.value = newSaldo.toFixed(2);
				objSaldo.innerHTML   = convMoeda( newSaldo.toFixed(2) );
				
				var newFormatSaldo = newSaldo.toFixed(2);
				
				var objLancTotalHidden   = eval("window.document.forms(0)." + coluna + "LancTotalHidden");				
				objLancTotalHidden.value = newLancTotal.toFixed(2);				
				document.getElementById(coluna + "LancTotal").innerHTML = convMoeda( newLancTotal.toFixed(2) );
				
				var objSaldoTotalHidden   = eval("window.document.forms(0)." + coluna + "SaldoTotalHidden");				
				objSaldoTotalHidden.value = newSaldoTotal.toFixed(2);				
				document.getElementById(coluna + "SaldoTotal").innerHTML = convMoeda( newSaldoTotal.toFixed(2) );
				
				//window.alert("Lan�ado Hidden:" + objLancHidden.value + " Saldo Hidden:" + objSaldoHidden.value);
				//window.alert("Total Lan�ado Hidden:" + objLancTotalHidden.value + " Total Saldo Hidden:" + objSaldoTotalHidden.value);	
																		
			
			}	
			
			/**
			 * Fun��o para retirar a sele��o de itens que n�o foram relacionados a Nota Fiscal
			 * Se existir item selecionado mas sem nota fiscal relacionada, a propriedade
			 * isSelected vai para FALSE
			 *
			 * @param tipo
			 *   Tipo do item para Associar 
			 *   - 'P' para Pe�a
			 *   - 'R' para Remessa
			 *   - 'S' para Servi�os
			 *   - 'M' para M�o de Obra de Terceiros
			 *			 
			 **/
			function resetItens(tipo) {
			
				var itensSel = new Array();
				var hasItem  = false;
				
				switch(tipo) {
				
					case 'P': // Pe�as 
							for ( ix = 0; ix < pecas.length ; ix++ ) {				
								if ( pecas[ix].getIsSelected() && !pecas[ix].getIsNota() ) {					
									pecas[ix].setIsSelected(false);
								}				
							}
							break;					
					case 'R': //Remessa 
							for ( ix = 0; ix < remessas.length ; ix++ ) {				
								if ( remessas[ix].getIsSelected() && !remessas[ix].getIsNota() ) {					
									remessas[ix].setIsSelected(false);
								}				
							}					
							break;
					case 'S': // Servi�os
							for ( ix = 0; ix < servicos.length ; ix++ ) {				
								if ( servicos[ix].getIsSelected() && !servicos[ix].getIsNota() ) {					
									servicos[ix].setIsSelected(false);					
								}				
							}				
							break;
					case 'M': // M�o de Obra de Terceiros
							for ( ix = 0; ix < servicos3os.length ; ix++ ) {				
								if ( servicos3os[ix].getIsSelected() && !servicos3os[ix].getIsNota() ) {					
									servicos3os[ix].setIsSelected(false);
								}				
							}							
				}			
			}
			
			function resetValues() {
				
				window.document.getElementById("numeroNF").value         = "";
				window.document.getElementById("serieNF").value          = "";
				window.document.getElementById("dateNF").value           = "";
				window.document.getElementById("serviceValueNF").value   = "";
				window.document.getElementById("pecaValueNF").value      = "";
				window.document.getElementById("remessaValueNF").value   = "";
				window.document.getElementById("mo3ValueNF").value       = "";
				//window.document.getElementById("icmsValueNF").value      = "";
				window.document.getElementById('destNota').innerHTML     = "";
				window.document.getElementById('destinatarioNota').value = "";
				
				//window.document.getElementById('emitNota').innerHTML   = "Conc";
				//window.document.getElementById('emitenteNota').value   = "Conc";				
				//window.document.getElementById('emitente').checked;
			
				window.document.getElementById('numeroNF').disabled     = true;
				window.document.forms(0).serviceValueNF.readOnly        = false;
				window.document.forms(0).pecaValueNF.readOnly 		    = true;
				window.document.forms(0).remessaValueNF.readOnly 		= true;
				window.document.forms(0).mo3ValueNF.readOnly 			= true;
				//window.document.forms(0).icmsValueNF.readOnly 			= true;
				
				<core:if test="${idTipoLote == 2}">
					
					window.document.forms(0).serviceValueNF.readOnly        = true;
					
					if ( window.document.forms(0).emitenteNota.value == "Conc" ) {
				
						window.document.forms(0).servico.checked 				= false;
						window.document.forms(0).peca.checked 				    = false;
						window.document.forms(0).remessa.checked 				= false;
						window.document.forms(0).maoObra3.checked 			    = false;
						window.document.forms(0).conjugada.checked 			    = false;
					
						window.document.forms(0).servico.disabled				= false;
						window.document.forms(0).peca.disabled 				    = false;
						window.document.forms(0).remessa.disabled 				= false;
						window.document.forms(0).maoObra3.disabled 			    = false;
						window.document.forms(0).conjugada.disabled 		    = false;
						
					} else if ( window.document.forms(0).emitenteNota.value == "Outros" ) {
					
						defEmitente( "Outros" );
					
					}
				
				</core:if>
				
				// Vamos setar a propriedade isSelected para false 
				resetItens('S'); // Servi�os
				resetItens('P'); // Pe�as
				resetItens('M'); // M�o de Obra de Terceiros
				resetItens('R'); // Remessa
				
			}			
			
			function removeRow(lineTable, lineObj) {
			
				if ( window.confirm("Confirma a exclus�o da nota?") ) {
				
					var service   = Number(deci(eval("window.document.getElementById( 'serviceValueNF_" + lineObj + "').value")));
					var peca      = Number(deci(eval("window.document.getElementById( 'pecaValueNF_"    + lineObj + "').value")));
					var remessa   = Number(deci(eval("window.document.getElementById( 'remessaValueNF_" + lineObj + "').value")));
					var mo3       = Number(deci(eval("window.document.getElementById( 'mo3ValueNF_"     + lineObj + "').value")));
					var linha     = Number(deci(eval("window.document.getElementById( 'linhaNota_"      + lineObj + "').value")));
					var numeroNF  = Number(deci(eval("window.document.getElementById( 'numeroNF_"       + lineObj + "').value")));
								
					//window.alert("Valor Servi�o:" + service);	
						
					var o = window.document.getElementById("values");
					
					var n = window.document.getElementById("nl" + lineTable) != null ? window.document.getElementById("nl" + lineTable).rowIndex : "";
					
					if (window.document.getElementById("nl" + lineTable) != null ) {	
						removeSub(lineTable,"nl" + lineTable );
						//var t = window.document.getElementById("tnl" + lineTable).rowIndex;
						//o.deleteRow(n);
						//o.deleteRow(t);
					}
					
					//window.alert("Excluindo linha:" + "l" + lineTable);	
					
					o.deleteRow(window.document.getElementById("l" + lineTable).rowIndex);
					
					if ( service != 0 )
						updateValues( service, linha, "vm", "DEL");
						
					if ( peca != 0 )
						updateValues( peca, linha, "vp", "DEL");
						
					if ( remessa != 0 )
						updateValues( remessa, linha, "vr", "DEL");	
					
					if ( mo3 != 0 )
						updateValues( mo3, linha, "vmt", "DEL");	
					
					if ( window.document.forms(0).tipoLoteId.value == 2 ) {	
										
						window.document.forms(0).servico.disabled	= false;
						window.document.forms(0).peca.disabled 		= false;
						window.document.forms(0).remessa.disabled 	= false;
						window.document.forms(0).maoObra3.disabled 	= false;
						window.document.forms(0).conjugada.disabled = false;
						
						window.document.forms(0).servico.checked	= false;
						window.document.forms(0).peca.checked 		= false;
						window.document.forms(0).remessa.checked 	= false;
						window.document.forms(0).maoObra3.checked 	= false;
						window.document.forms(0).conjugada.checked  = false;
						
					} else {
					
						window.document.forms(0).servico.checked	= false;
					}		
					
					/* Vamos retirar os dados da nota fiscal dos itens relacionados */
					for ( ix = 0; ix < pecas.length ; ix++ ) {
						
						if ( pecas[ix].getNumNota() == numeroNF ) {
						
							pecas[ix].setIsNota(false);
							pecas[ix].setNumNota(null);
							pecas[ix].setIsSelected(false);
							
						}
					
					}
					
					/* Vamos retirar os dados da nota fiscal dos itens relacionados */
					for ( ix = 0; ix < servicos.length ; ix++ ) {
						
						if ( servicos[ix].getNumNota() == numeroNF ) {
						
							servicos[ix].setIsNota(false);
							servicos[ix].setNumNota(null);
							servicos[ix].setIsSelected(false);
							
						}
					
					}
					
					/* Vamos retirar os dados da nota fiscal dos itens relacionados */
					for ( ix = 0; ix < servicos3os.length ; ix++ ) {
						
						if ( servicos3os[ix].getNumNota() == numeroNF ) {
						
							servicos3os[ix].setIsNota(false);
							servicos3os[ix].setNumNota(null);
							servicos3os[ix].setIsSelected(false);
							
						}
					
					}
					
					/* Vamos retirar os dados da nota fiscal dos itens relacionados */
					for ( ix = 0; ix < remessas.length ; ix++ ) {
						
						if ( remessas[ix].getNumNota() == numeroNF ) {
						
							remessas[ix].setIsNota(false);
							remessas[ix].setNumNota(null);
							remessas[ix].setIsSelected(false);
							
						}
					
					}
					
					/* Quando os valores estiverem zerados habilitamos o bot�o gravar */
					window.document.getElementById("gravar").disabled = !validateValues();					
						
				}
				
			}
			
					
			/*  Valida��o digita��o de valor n�merico com casas decimais */
			/*  As casas decimais podem ser definidas com uso de v�rgula ou ponto */ 
			function isNumber(field) {
				
				var n = window.document.getElementById(field).value;

				var isMoeda = false;
				var indexP = n.indexOf('.');
				var indexV = n.indexOf(',');
				
				if ( indexP != -1 && indexV != -1 ) {
					
					if ( indexP < indexV ) 
						isMoeda = true;					
					
				} else if (!(indexP == -1 && indexV != -1) || !( indexP == -1 && indexV == -1) ) {
				
					isMoeda = true;	
				
				}
				
				if (isNaN(deci(n)) || !isMoeda) {
					window.alert("Valor inv�lido!");
					window.document.getElementById(field).value = "";
					window.document.getElementById(field).focus();
				}
			}
			
			/* Habilita ou desabilita os campos de valores conforme o checkbox selecionado */
			function enableValues(idCheck, idField) {
			
				var check      = window.document.getElementById(idCheck);
				var fieldValue = window.document.getElementById(idField);
									
				if ( !check.checked ) {
					
					//if ( check.name != "peca"  && check.name != "remessa") {						
						fieldValue.value 	= "";	
						fieldValue.readOnly = true;
					//} 
					
					if ( check.name == "peca" ) 
						resetItens('P'); // Retiramos as pe�as selecionadas
					else if ( check.name == "servico" ) 
						resetItens('S'); // Retiramos os servi�os selecionadas
					else if ( check.name == "remessa" ) 
						resetItens('R'); // Retiramos as remessas de pe�as selecionadas	
					else if ( check.name == "maoObra3" ) 
						resetItens('M'); // Retiramos os servi�os de MO3 selecionados
					else if ( check.name == "conjugada" ) {
						resetItens('M'); // Retiramos os servi�os de MO3 selecionados
						resetItens('S'); // Retiramos os servi�os selecionadas
						resetItens('P'); // Retiramos as pe�as selecionadas
					}
						
				}
				
				if (  check.name == "conjugada"  ) {
					
					if ( check.checked ) {
						if ( hasNotSelectedItem('P') && (hasNotSelectedItem('S') || hasNotSelectedItem('M')) ) {
							window.document.getElementById("remessa").disabled  = true;
							window.document.getElementById("servico").disabled  = true;
							window.document.getElementById("peca").disabled     = true;
							window.document.getElementById("maoObra3").disabled = true;						
							showPopWin('protNF/notaFiscal_prot_conj.htm', 700, 350,null, false, 'Nota Fiscal Conjugada');
						} else {
							window.alert("N�o existe composi��o de valores para emiss�o de Nota Fiscal Conjugada!");	
							check.checked = false;
							fieldValue.readOnly = true;
						}				
					} else {
						window.document.getElementById("remessa").disabled  = false;
						window.document.getElementById("servico").disabled  = false;
						window.document.getElementById("peca").disabled     = false;
						window.document.getElementById("maoObra3").disabled = false;						
					}
				
				} else if ( check.name == "servico" ) {
					if ( check.checked ) {
											
						if ( hasNotSelectedItem('S') ) {
							window.document.getElementById("remessa").disabled    = true;
							window.document.getElementById("conjugada").disabled  = true;
							window.document.getElementById("peca").disabled       = true;
							//window.document.getElementById("maoObra3").disabled   = true;	
							<core:if test="${idTipoLote == 1}">						
								showPopWin('protNF/notaFiscal_prot_servico.htm', 600, 350,null, false, 'Nota Fiscal de Servi�os de Revis�o');
							</core:if>
							<core:if test="${idTipoLote == 2}">		
								showPopWin('protNF/notaFiscal_prot_servico.htm', 600, 350,null, false, 'Nota Fiscal de Servi�os de Garantia');						
							</core:if>
						} else {
							window.alert("N�o existe valor de servi�o de M�o de Obra para emiss�o de Nota Fiscal!");	
							check.checked = false;
							fieldValue.readOnly = true;
						}	
						
					} else {
				
							window.document.getElementById("remessa").disabled  = false;
							window.document.getElementById("conjugada").disabled     = false;
							window.document.getElementById("peca").disabled     = false;
							//window.document.getElementById("maoObra3").disabled = false;	
					}
				
				} else if ( check.name == "peca" ) {
				
					if ( check.checked ) {
						if ( hasNotSelectedItem('P') ) {
							window.document.getElementById("remessa").disabled  = true;
							window.document.getElementById("servico").disabled 	= true;
							window.document.getElementById("conjugada").disabled     = true;
							window.document.getElementById("maoObra3").disabled = true;				
							showPopWin('protNF/notaFiscal_prot_peca.htm', 700, 350,null, false, 'Pe�as');
						} else {
							window.alert("N�o existe pe�a para emiss�o de Nota Fiscal!");	
							check.checked = false;
							fieldValue.readOnly = true;
							<core:if test="${idTipoLote == 2}">		
								if ( !window.document.getElementById("servico").checked && !window.document.getElementById("peca").checked && !window.document.getElementById("maoObra3").checked){
									window.document.getElementById("remessa").disabled    = false;
									window.document.getElementById("conjugada").disabled  = false;											
								}							
							</core:if>
						}	
					} else { 
						window.document.getElementById("remessa").disabled  = false;
						window.document.getElementById("servico").disabled 	= false;
						window.document.getElementById("conjugada").disabled     = false;
						window.document.getElementById("maoObra3").disabled = false;
					} 
				
				} else if ( check.name == "remessa" ) {
				
					if ( check.checked ) {
						if ( hasNotSelectedItem('R') ) {
							window.document.getElementById("servico").disabled    = true;
							window.document.getElementById("peca").disabled       = true;
							window.document.getElementById("maoObra3").disabled   = true;
							window.document.getElementById("conjugada").disabled  = true;
							showPopWin('protNF/notaFiscal_remessa.htm', 700, 350,null, false, 'Nota Fiscal de Pe�as para Remessa');							
						} else {
							window.alert("N�o existe pe�a de remessa para emiss�o de Nota Fiscal!");
							check.checked = false;
							fieldValue.readOnly = true;
						}	
						
					} else {					
						window.document.getElementById("servico").disabled    = false;
						window.document.getElementById("peca").disabled       = false;
						window.document.getElementById("maoObra3").disabled   = false;
						window.document.getElementById("conjugada").disabled  = false;							
					}						
				
				} else if ( check.name == "maoObra3" ) {
					
					if ( window.document.getElementById("emitenteNota").value == "Conc" ) {
						if ( check.checked ) {
							if ( hasNotSelectedItem('M') ) {
								//window.document.getElementById("servico").disabled    = true;
								window.document.getElementById("peca").disabled       = true;
								window.document.getElementById("remessa").disabled    = true;
								window.document.getElementById("conjugada").disabled  = true;
								showPopWin('protNF/notaFiscal_servico3os.htm', 600, 350,null, false, 'Nota Fiscal de Servi�os M�o de Obra de Terceiros');
							} else {
								window.alert("N�o existe valor de M�o de Obra de Terceiros para emiss�o de Nota Fiscal!");
								check.checked = false;
								fieldValue.readOnly = true;
							}	
						} else {
							//window.document.getElementById("servico").disabled    = false;
							window.document.getElementById("peca").disabled       = false;
							window.document.getElementById("remessa").disabled    = false;
							window.document.getElementById("conjugada").disabled  = false;
						}	
					} else 	if ( window.document.getElementById("emitenteNota").value == "Outros" )	{
					
						if ( check.checked )
							showPopWin('protNF/notaFiscal_servico3os.htm', 600, 350,null, false, 'Nota Fiscal de Servi�os M�o de Obra de Terceiros');
										
					}	
				
				}
			}
			
			/* Inserir o destinat�rio da nota */
			function defDestinatario( destinatario, nLinha){
				
				window.document.getElementById('destNota').innerHTML     = destinatario;
				window.document.getElementById('destinatarioNota').value = destinatario;
				window.document.getElementById('linhaHidden').value      = nLinha;
				window.document.getElementById('numeroNF').disabled      = false;
				window.document.getElementById('numeroNF').focus();
								
			}
			
			/* Inserir oemitente da nota */
			function defEmitente( emitente ){
				
				var emitenteConc = "Conc";
				var emitenteOut  = "Outros";
				
				window.document.getElementById('emitNota').innerHTML = emitente;
				window.document.getElementById('emitenteNota').value = emitente;
				
				if ( emitente == emitenteOut ) {
				
					window.document.getElementById("servico").disabled  = true;
					window.document.getElementById("servico").checked   = false;
					window.document.getElementById("peca").disabled     = true;
					window.document.getElementById("peca").checked      = false;
					window.document.getElementById("remessa").disabled  = true;
					window.document.getElementById("remessa").checked   = false;
					window.document.getElementById("maoObra3").disabled = false;
					window.document.getElementById("maoObra3").checked  = false;
					window.document.getElementById("conjugada").disabled = true;
					window.document.getElementById("conjugada").checked  = false;
					
					window.document.getElementById("serviceValueNF").value    = "";
					window.document.getElementById("serviceValueNF").readOnly = true;
					
					window.document.getElementById("pecaValueNF").value    = "";
					window.document.getElementById("pecaValueNF").readOnly = true;
					
					window.document.getElementById("remessaValueNF").value    = "";
					window.document.getElementById("remessaValueNF").readOnly = true;
					
					window.document.getElementById("mo3ValueNF").value    = "";
					window.document.getElementById("mo3ValueNF").readOnly = false;
					
				} else if ( emitente == emitenteConc ) {
				
					window.document.getElementById("servico").disabled = false;
					window.document.getElementById("peca").disabled    = false;
					window.document.getElementById("remessa").disabled = false;
					window.document.getElementById("maoObra3").checked = false;
					window.document.getElementById("conjugada").disabled = false;
					
					window.document.getElementById("mo3ValueNF").value    = "";
					window.document.getElementById("mo3ValueNF").readOnly = true;
					
				}
				
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
			
			//Pega um valor formatado com virgula e separador de milhar e o transforma em float
			function moedaTofloat(moeda){
				
				if ( moeda != "" ) {
				
	   				moeda = moeda.replace(".","");

   					moeda = moeda.replace(",",".");

   					return parseFloat(moeda);
				} else
					return moeda;

			}
			
			/* funcao de formatacao de casas decimais */ 
			function deci(N) {
				//window.alert("Valor pr� - Conver��o:" + N);
				//N = Math.round(N * 100) / 100;
					
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
				//window.alert("Valor p�s - Convers�o:" + Term);
				return Term;
			}
			
			/** 
			*   Fun��o que pega o retorno da valida��o do n�mero da nota e da s�rie
			*   para permitir a inclus�o da nota ou n�o
			*/
			function posGetValidateNF() {
				
				var tgValidateNF = window.document.getElementById('targetValidateNF').value;
				var tgMessageNF  = window.document.getElementById('targetMessageNF').value;
				
				__loadEsconde();
				window.document.getElementById("lancar").disabled = false;
				
				if ( validateDataNF() ) {
				
					//window.alert("Valida��o:" + tgValidateNF + " - Mensagem:" + tgMessageNF);
				
					if ( tgValidateNF == "false" ) {
					
						window.alert("Nota Inv�lida! Erro:" + tgMessageNF );
					
					} else if ( tgValidateNF == "true"  ) {
					
						insRow();
						
					}	
				}
							
			}
			
			function preGetValidateNF() {
				__showAnima();
				window.document.getElementById("lancar").disabled = true;
										
			}
			
			function expFunction() {
			
				window.alert("ERRO na recupera��o das informa��es da nota!");
			
			}
			
			/** Realiza valida��es nos valores da nota fiscal */  
			function validateDataNF() {
			
				var data1 = window.document.getElementById("sysDate").value;
				var data2 = window.document.forms(0).dateNF.value;
				
				if ( window.document.forms(0).numeroNF.value == "" ) {
					window.alert("O n�mero da nota � obrigat�rio!");
					if ( window.document.forms(0).numeroNF.disabled == false ) {
						window.document.forms(0).numeroNF.focus();
					}
					return false;
				}
				
				if ( window.document.forms(0).serieNF.value == "" ) {
					window.alert("A s�rie da nota � obrigat�ria!");
					if ( window.document.forms(0).serieNF.disabled == false ) {
						window.document.forms(0).serieNF.focus();
					}
					return false;
				}
				
				if ( window.document.forms(0).dateNF.value == "" ) {
					window.alert("A data da nota � obrigat�rio!");
					if ( window.document.forms(0).serieNF.disabled == false ) {
						window.document.forms(0).dateNF.focus();
					}
					return false;
				} else if ( comparaData( data1, data2 ) < 0 ) {
					window.alert("A data da nota fiscal n�o pode ser maior que a data atual!");
		 			window.document.forms(0).dateNF.value = "";
		 			if ( window.document.forms(0).serieNF.disabled  == false ) {
		 				window.document.forms(0).dateNF.focus();
		 			}
		 			return false;
				
				}
				
				return true;
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
	
		<html:form action="/NotaFiscal" method="post">
		
			<html:hidden property="loteId"/>
			<input type="hidden" name="contField"/>
			<input type="hidden" name="tipoLoteId" id="tipoLoteId" value="<core:out value='${idTipoLote}'/>" />
			<html:hidden property="toleranciaNota"/>
			<html:hidden property="pathBack"/>
			<html:hidden property="individualLaunch"/>
			<html:hidden property="isDif"/>
			<html:hidden property="sysDate"/>
			<html:hidden property="toleranciaDiasNF"/>	
			<jsp:include flush="true" page="include_alerts.jsp"/>
			<br>
			<center>
			<table width="98%" border="0" cellpadding="2" cellspacing="2" align="center">			
			<tr>
				<td align="center" class="tilteList" colspan="9"><bean:message key="notaFiscal.launch.controle"/></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td id="vsControle" class="tr_line" align="center" colspan="2"><bean:message key="notaFiscal.launch.service"/></td>
				<td id="vpControle" class="tr_line" align="center" colspan="2"><bean:message key="notaFiscal.launch.peca"/></td>
				<td id="vrControle" class="tr_line" align="center" colspan="2"><bean:message key="notaFiscal.launch.remessa"/></td>
				<td id="vmControle" class="tr_line" align="center" colspan="2"><bean:message key="notaFiscal.launch.mo3"/></td>
			</tr>
			<tr>
				<td class="td_dark" align="center"><bean:message key="notaFiscal.launch.destinatario"/></td>
				<td class="td_dark" align="center"><bean:message key="notaFiscal.launch.lancado"/></td>
				<td class="td_dark" align="center"><bean:message key="notaFiscal.launch.saldo"/></td>
				<td class="td_dark" align="center"><bean:message key="notaFiscal.launch.lancado"/></td>
				<td class="td_dark" align="center"><bean:message key="notaFiscal.launch.saldo"/></td>
				<td class="td_dark" align="center"><bean:message key="notaFiscal.launch.lancado"/></td>
				<td class="td_dark" align="center"><bean:message key="notaFiscal.launch.saldo"/></td>
				<td class="td_dark" align="center"><bean:message key="notaFiscal.launch.lancado"/></td>
				<td class="td_dark" align="center"><bean:message key="notaFiscal.launch.saldo"/></td>
			</tr>
			<core:set var="linha" 				value= "0"/>
			<core:set var="valorMaoObra" 		value= "0"/>
			<core:set var="totalVlMOLanc" 		value= "0"/>
			<core:set var="totalVlMOSaldo" 	    value= "0"/>
			<core:set var="valorRemessa" 		value= "0"/>
			<core:set var="totalvalorRemLanc" 	value= "0"/>
			<core:set var="totalValorRemSaldo"  value= "0"/>
			<core:set var="valorPeca" 			value= "0"/>
			<core:set var="totalVlPeLanc" 		value= "0"/>
			<core:set var="totalVlPeSaldo" 	    value= "0"/>
			<core:set var="valorMaoObraTerc" 	value= "0"/>
			<core:set var="totalVlMOTercLanc" 	value= "0"/>
			<core:set var="totalVlMOTercSaldo"  value= "0"/>
			<logic:iterate name="notaFiscalListForm" property="listItens" id="notaFiscalVO">				
				<core:set var="linha" value= "${linha + 1 }"/>
				<tr onmouseover="mOvr(this,'#E7E8E6')" onmouseout="mOut(this,'#FFFFFF')">
					<td class="text"  onclick="mClk(this);">
						<a href="javascript:defDestinatario('<core:out value='${notaFiscalVO.destinatario}'/>', '<core:out value='${linha}'/>' )" class="text_tip">
							<core:out value="${notaFiscalVO.destinatario}"/>
						</a>
						<input type="hidden" name="destHidden_<core:out value='${linha}'/>" value="<core:out value='${notaFiscalVO.destinatario}'/>"/>
					</td>
					<td class="text" align="right">
						<span id="vmLanc_<core:out value='${linha}'/>">
							<core:out value="${valorMaoObra}"/> 
						</span>
						<input type="hidden" name="vmLancHidden_<core:out value='${linha}'/>" value="<core:out value='${valorMaoObra}'/>"/>
					</td>
					<td class="text" align="right">
						<span id="vmSaldo_<core:out value='${linha}'/>">
							<core:out value="${notaFiscalVO.formatedValorMaoObra}"/>
						</span>
						<input type="hidden" name="vmSaldoHidden_<core:out value='${linha}'/>" value="<core:out value='${notaFiscalVO.formatedValorMaoObra}'/>"/>
					</td>					
					<td class="text" align="right">
						<span id="vpLanc_<core:out value='${linha}'/>">
							<core:out value="${valorPeca}"/>
						</span>
						<input type="hidden" name="vpLancHidden_<core:out value='${linha}'/>" value="<core:out value='${valorPeca}'/>"/>
					</td>
					<td class="text" align="right">
						<span id="vpSaldo_<core:out value='${linha}'/>">
							<core:out value="${notaFiscalVO.formatedValorPeca}"/>
						</span>
						<input type="hidden" name="vpSaldoHidden_<core:out value='${linha}'/>" value="<core:out value='${notaFiscalVO.formatedValorPeca}'/>"/>
					</td>
					<td class="text" align="right">
						<span id="vrLanc_<core:out value='${linha}'/>">
							<core:out value="${valorRemessa}"/>
						</span>
						<input type="hidden" name="vrLancHidden_<core:out value='${linha}'/>" value="<core:out value='${valorRemessa}'/>"/>
					</td>
					<td class="text" align="right">
						<span id="vrSaldo_<core:out value='${linha}'/>">
							<core:out value="${notaFiscalVO.formatedValorRemessa}"/>
						</span>
						<input type="hidden" name="vrSaldoHidden_<core:out value='${linha}'/>" value="<core:out value='${notaFiscalVO.formatedValorRemessa}'/>"/>
					</td>
					<td class="text" align="right">
						<span id="vmtLanc_<core:out value='${linha}'/>">
							<core:out value="${valorMaoObraTerc}"/>
						</span>
						<input type="hidden" name="vmtLancHidden_<core:out value='${linha}'/>" value="<core:out value='${valorMaoObraTerc}'/>"/>
					</td>
					<td class="text" align="right">
						<span id="vmtSaldo_<core:out value='${linha}'/>">
							<core:out value="${notaFiscalVO.formatedValorMaoObraTerc}"/>
						</span>
						<input type="hidden" name="vmtSaldoHidden_<core:out value='${linha}'/>" value="<core:out value='${notaFiscalVO.formatedValorMaoObraTerc}'/>"/>
					</td>
				</tr>
				<core:set var="totalVlMOLanc" 	   value= "${totalVlMOLanc  	+ valorMaoObra}"/>
				<core:set var="totalVlMOSaldo" 	   value= "${totalVlMOSaldo   	+ notaFiscalVO.valorMaoObra}"/>
				<core:set var="totalvalorRemLanc"  value= "${totalvalorRemLanc 	+ valorRemessa}"/>
				<core:set var="totalValorRemSaldo" value= "${totalValorRemSaldo + notaFiscalVO.valorRemessa}"/>
				<core:set var="totalVlPeLanc" 	   value= "${totalVlPeLanc 		+ valorPeca}"/>
				<core:set var="totalVlPeSaldo" 	   value= "${totalVlPeSaldo 	+ notaFiscalVO.valorPeca}"/>
				<core:set var="totalVlMOTercLanc"  value= "${totalVlMOTercLanc 	+ valorMaoObraTerc}"/>
				<core:set var="totalVlMOTercSaldo" value= "${totalVlMOTercSaldo + notaFiscalVO.valorMaoObraTerc}"/>
			</logic:iterate>	
			<tr>
				<td class="td_dark" align="center">Total</td>
				<td class="td_dark" align="right">
					<span id="vmLancTotal">&nbsp;</span>
					<input type="hidden" name="vmLancTotalHidden"/>
				</td>
				<td class="td_dark" align="right">
					<span id="vmSaldoTotal">&nbsp;</span>
					<input type="hidden" name="vmSaldoTotalHidden"/>
				</td>
				<td class="td_dark" align="right">
					<span id="vpLancTotal">&nbsp;</span>
					<input type="hidden" name="vpLancTotalHidden"/>
				</td>
				<td class="td_dark" align="right">
					<span id="vpSaldoTotal">&nbsp;</span>
					<input type="hidden" name="vpSaldoTotalHidden"/>
				</td>
				<td class="td_dark" align="right">
					<span id="vrLancTotal">&nbsp;</span>
					<input type="hidden" name="vrLancTotalHidden"/>
				</td>
				<td class="td_dark" align="right">
					<span id="vrSaldoTotal">&nbsp;</span>
					<input type="hidden" name="vrSaldoTotalHidden"/>
				</td>				
				<td class="td_dark" align="right">
					<span id="vmtLancTotal">&nbsp;</span>
					<input type="hidden" name="vmtLancTotalHidden"/>
				</td>
				<td class="td_dark" align="right">
					<span id="vmtSaldoTotal">&nbsp;</span>
					<input type="hidden" name="vmtSaldoTotalHidden"/>
				</td>
			</tr>	
			</table>
			<script type="text/javascript">							
				window.document.getElementById("vmLancTotal").innerHTML     = convMoeda(deci("<core:out value='${totalVlMOLanc}'/>"));				
				window.document.getElementById("vmSaldoTotal").innerHTML    = convMoeda(deci("<core:out value='${totalVlMOSaldo}'/>"));
				window.document.getElementById("vmSaldoTotalHidden").value	= deci(<core:out value='${totalVlMOSaldo}'/>);	
						
				window.document.getElementById("vpLancTotal").innerHTML     = convMoeda(deci("<core:out value='${totalVlPeLanc}'/>"));				
				window.document.getElementById("vpSaldoTotal").innerHTML    = convMoeda(deci("<core:out value='${totalVlPeSaldo}'/>"));	
				window.document.getElementById("vpSaldoTotalHidden").value	= deci(<core:out value='${totalVlPeSaldo}'/>);
								
				window.document.getElementById("vrLancTotal").innerHTML     = convMoeda(deci("<core:out value='${totalvalorRemLanc}'/>"));				
				window.document.getElementById("vrSaldoTotal").innerHTML    = convMoeda(deci("<core:out value='${totalValorRemSaldo}'/>"));				
				window.document.getElementById("vrSaldoTotalHidden").value	= deci(<core:out value='${totalValorRemSaldo}'/>);
				
				window.document.getElementById("vmtLancTotal").innerHTML    = convMoeda(deci("<core:out value='${totalVlMOTercLanc}'/>") );				
				window.document.getElementById("vmtSaldoTotal").innerHTML   = convMoeda(deci("<core:out value='${totalVlMOTercSaldo}'/>"));				
				window.document.getElementById("vmtSaldoTotalHidden").value	= deci(<core:out value='${totalVlMOTercSaldo}'/>);
			</script>
			<br>	
			<table width="98%" border="0" cellpadding="2" cellspacing="2" align="center">			
			<tr>
				<td align="center" class="tilteList" colspan="2"><bean:message key="notaFiscal.launch.title"/></td>
			</tr>
			<tr>
				<td class="text">
					<bean:message key="notaFiscal.launch.emitente"/>:
					<!-- Para lote de revis�o o emitente ser� sempre a Concession�ria  -->
					<core:if test="${idTipoLote == 1}">
						<input type="radio" name="emitente" value="C" checked="checked" disabled="disabled"><bean:message key="notaFiscal.launch.concessionaria"/>&nbsp;
						<input type="radio" name="emitente" value="O" disabled="disabled"><bean:message key="notaFiscal.launch.outros"/>
					</core:if>
					<core:if test="${idTipoLote == 2}">
						<input type="radio" name="emitente" value="C" checked="checked" onclick="defEmitente('<bean:message key="notaFiscal.launch.conc"/>');"><bean:message key="notaFiscal.launch.concessionaria"/>&nbsp;
						<input type="radio" name="emitente" value="O" onclick="defEmitente('<bean:message key="notaFiscal.launch.outros"/>');"><bean:message key="notaFiscal.launch.outros"/>
					</core:if>				
				</td>
				<td class="text" align="right">
					<bean:message key="notaFiscal.launch.tipoNota"/>:
					<core:if test="${idTipoLote == 1}">
						<input type="checkbox" name="conjugada"  value="conj" disabled="disabled"/><bean:message key="notaFiscal.conjugada"/>&nbsp;
						<input type="checkbox" name="servico"    value="serv" onclick="enableServico('servico' , 'serviceValueNF');"/><bean:message key="notaFiscal.ServiceValue"/>&nbsp;
						<input type="checkbox" name="peca"       value="pec"  disabled="disabled"/><bean:message key="notaFiscal.pecaValue"/>&nbsp;
						<input type="checkbox" name="remessa"    value="rem"  disabled="disabled"/><bean:message key="notaFiscal.remessaValue"/>&nbsp;
						<input type="checkbox" name="maoObra3"   value="mo3"  disabled="disabled"/><bean:message key="notaFiscal.mo3Value"/>&nbsp;
						<script language="javascript">
							
							function enableServico(idCheck, idField) {
			
								var check      = window.document.getElementById(idCheck);
								var fieldValue = window.document.getElementById(idField);
								if ( check.checked ) {
						
									fieldValue.readOnly = false;
									showPopWin('protNF/notaFiscal_prot_servico.htm', 700, 360,null, false, 'Nota Fiscal de Servi�os de Revis�o');
						
								} else {
						
									fieldValue.value = "";	
									fieldValue.readOnly = true;
						
								}
							}
							
						</script>						
					</core:if>
					<core:if test="${idTipoLote == 2}">
						<input type="checkbox" name="conjugada"  value="conj" onclick="enableValues('conjugada' , 'pecaValueNF');"/><bean:message key="notaFiscal.conjugada"/>&nbsp;
						<input type="checkbox" name="servico"    value="serv" onclick="enableValues('servico'   , 'serviceValueNF');"/><bean:message key="notaFiscal.ServiceValue"/>&nbsp;
						<input type="checkbox" name="peca"       value="pec"  onclick="enableValues('peca'      , 'pecaValueNF');"/><bean:message key="notaFiscal.pecaValue"/>&nbsp;
						<input type="checkbox" name="remessa"    value="rem"  onclick="enableValues('remessa'   , 'remessaValueNF');"/><bean:message key="notaFiscal.remessaValue"/>&nbsp;
						<input type="checkbox" name="maoObra3"   value="mo3"  onclick="enableValues('maoObra3'  , 'mo3ValueNF');"/><bean:message key="notaFiscal.mo3Value"/>&nbsp;
					</core:if>
				</td>				
			</tr>
			</table>
			<table width="98%" border="0" cellpadding="2" cellspacing="2" align="center">	
			<tr>
				<td class="td_dark" align="center"><bean:message key="notaFiscal.launch.emitente"/></td>
				<td class="td_dark" align="center"><bean:message key="notaFiscal.launch.destinatario"/></td>
				<td class="td_dark" align="center"><bean:message key="notaFiscal.launch.numberNF"/></td>
				<td class="td_dark" align="center"><bean:message key="notaFiscal.launch.serieNF"/></td>
				<td class="td_dark" align="center"><bean:message key="notaFiscal.launch.dateNF"/></td>
				<td class="td_dark" align="center"><bean:message key="notaFiscal.launch.serviceValueNF"/></td>
				<td class="td_dark" align="center"><bean:message key="notaFiscal.launch.pecaValueNF"/></td>
				<td class="td_dark" align="center"><bean:message key="notaFiscal.launch.remessaValueNF"/></td>
				<td class="td_dark" align="center"><bean:message key="notaFiscal.launch.vmo3ValueNF"/></td>
			<%--	<td class="td_dark" align="center"><bean:message key="notaFiscal.launch.vicmsValueNF"/></td>--%>				
			</tr>
			<tr>
				<td class="text" align="center"><input type="hidden" name="emitenteNota" id="emitenteNota" value="Conc"/><span id="emitNota"><bean:message key="notaFiscal.launch.conc"/></span></td>
				<td class="text" align="center"><input type="hidden" name="destinatarioNota"/><input type="hidden" name="linhaHidden"/><span id="destNota"></span></td>
				<td class="text" align="center">
					<html:text property="numeroNF" styleClass="text_field_maior" maxlength="10" disabled="true" onkeypress="return blockChar(event);"></html:text>
				</td>
				<td class="text" align="center" width="60">
					<html:text property="serieNF" styleClass="text_field_maior" maxlength="10"></html:text>				
				</td>
				<td class="text" align="center">
					<ym:inputDate icon="../images/icon_calendar.gif" 
		                 monthLabels	="Janeiro|Fevereiro|Mar�o|Abril|Maio|Junho|Julho|Agosto|Setembro|Outubro|Novembro|Dezembro" 
		                 dayHeaders		="D|S|T|Q|Q|S|S"
						 todayLabel		="Hoje"
						 fieldName		="dateNF"
						 fieldClass		="text_field_date"
						 datePattern	="dd/MM/yyyy"
						 fieldLength	="11"
						 onKeyUp		="mascara_data('dateNF',event)"
						 fieldYears		="4">
						 <bean:write name="notaFiscalListForm" property="dateNF"/>
					</ym:inputDate>
				</td>
				<td class="text" align="center">
					<core:if test="${idTipoLote == 1}">
						<html:text property="serviceValueNF" styleId="serviceValueNF" styleClass="text_field_maior" maxlength="10" onkeypress="return formatar_moeda(this,'.',',',event);"></html:text>
					</core:if>
					<core:if test="${idTipoLote == 2}">
						<html:text property="serviceValueNF" styleId="serviceValueNF" styleClass="text_field_maior" maxlength="10" readonly="true" onkeypress="return formatar_moeda(this,'.',',',event);"></html:text>
					</core:if>
				</td>
				<td class="text" align="center">
					<html:text property="pecaValueNF" styleId="pecaValueNF" styleClass="text_field_maior" maxlength="10" readonly="true" onkeypress="return formatar_moeda(this,'.',',',event);"></html:text>
				</td>
				<td class="text" align="center">
					<html:text property="remessaValueNF" styleId="remessaValueNF" styleClass="text_field_maior" maxlength="10" readonly="true" onkeypress="return formatar_moeda(this,'.',',',event);"></html:text>
				</td>
				<td class="text" align="center">
					<html:text property="mo3ValueNF" styleId="mo3ValueNF" styleClass="text_field_maior" maxlength="10" readonly="true" onkeypress="return formatar_moeda(this,'.',',',event);"></html:text>
				</td>
			</tr>
			</table>
			<table width="98%" border="0" cellpadding="2" cellspacing="2" align="center">			
			<tr>
				<td align="center">
					<html:button property="lancar" styleClass="button_medium"><bean:message key="form.btn.launch"/></html:button>
					&nbsp;
					<html:button property="limpar" styleClass="button_medium" onclick="javascript:resetValues();"><bean:message key="form.btn.clear"/></html:button>
					<input type="hidden" value="false" name="targetValidateNF"> 
					<input type="hidden" value="" name="targetMessageNF">
					<ajax:updateField
									  baseUrl="${pageContext.request.contextPath}/GetValidateNF.do"
									  source="serieNF" 
									  action="lancar"
									  target="targetMessageNF,targetValidateNF" 
									  parser="new ResponseXmlParser()" 	 				  
									  parameters="serieNF={serieNF},numeroNF={numeroNF},dateNF={dateNF}"
									  preFunction="preGetValidateNF"
									  postFunction="posGetValidateNF"
									  errorFunction="expFunction"/> 
				</td>
			</tr>
			</table>
			<center><div id="messageNota" style="display:none;border:1px solid #e00;background-color:#fee;padding:2px;margin-top:8px;width:300px;font:normal 11px Arial;color:#900" class="text"></div></center>
			<br>
			<table id="values" width="98%" border="0" cellpadding="2" cellspacing="2" align="center">			
			<tr>
				<td align="center" class="tilteList" colspan="11"><bean:message key="notaFiscal.launch.lancadas"/></td>
			</tr>
			<tr>
				<td class="td_dark" align="center"><bean:message key="notaFiscal.launch.emitente"/></td>
				<td class="td_dark" align="center"><bean:message key="notaFiscal.launch.destinatario"/></td>
				<td class="td_dark" align="center"><bean:message key="notaFiscal.launch.numberNF"/></td>
				<td class="td_dark" align="center"><bean:message key="notaFiscal.launch.serieNF"/></td>
				<td class="td_dark" align="center"><bean:message key="notaFiscal.launch.dateNF"/></td>
				<td class="td_dark" align="center"><bean:message key="notaFiscal.launch.serviceValueNF"/></td>
				<td class="td_dark" align="center"><bean:message key="notaFiscal.launch.pecaValueNF"/></td>
				<td class="td_dark" align="center"><bean:message key="notaFiscal.launch.remessaValueNF"/></td>
				<td class="td_dark" align="center"><bean:message key="notaFiscal.launch.vmo3ValueNF"/></td>
				<%--<td class="td_dark" align="center"><bean:message key="notaFiscal.launch.vicmsValueNF"/></td>--%>
				<td class="td_dark" align="center">&nbsp;</td>
			</tr>
			</table>
			<table width="98%" border="0" cellpadding="2" cellspacing="2" align="center">			
			<tr>
				<td align="center">
 						<html:button  property="gravar" styleClass="button_medium" disabled="true" onclick="this.disabled = true;__showAnima();submitFormTsk(notaFiscalListForm,'save',true)"><bean:message key="form.btn.save"/></html:button>	
 						<%--<html:button  property="gravar" styleClass="button_medium" disabled="true" onclick="javascript:alert('Em Constru��o!');"><bean:message key="form.btn.save"/></html:button>--%>	
						&nbsp;
						<html:reset property="limpar" styleClass="button_medium"><bean:message key="form.btn.clear"/></html:reset>	
				</td>
			</tr>	
			</table>
			</center>
		</html:form>
	</body>
</html:html>
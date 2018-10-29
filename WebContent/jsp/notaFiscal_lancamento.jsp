<%@taglib uri="http://struts.apache.org/tags-bean"     prefix="bean"    %>
<%@taglib uri="http://struts.apache.org/tags-html-el"  prefix="html"    %>
<%@taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic"   %>
<%@taglib uri="http://displaytag.sf.net/el" 		   prefix="display" %>
<%@taglib uri="http://java.sun.com/jstl/core" 		   prefix="core" 	%>
<%@taglib uri="/tld/ym"  	 						   prefix="ym"      %>
<%@taglib uri="http://ajaxtags.org/tags/ajax" 		   prefix="ajax"    %>

<ym:checkLogon roleName="sg_cd_nfconc" />

<%-- 
/* Resource Informática LTDA.
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......notaFiscal_lancamento.jsp
 *   .: Criação.....03 de junho de 2007, 14:47
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Tela: Tela para lancamento de notas fiscais.
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
		<script language="JavaScript" src="../scripts/General/form.js"></script>
		<script language="JavaScript" src="../scripts/Formatter/currency_format.js"></script>
		<script language="JavaScript" src="../scripts/Formatter/maskDate.js"></script>
		<script language="JavaScript" src="../scripts/progressbar/carregador.js"></script>	
		<script language="JavaScript" src="../scripts/Ajax/Base/prototype-1.4.0.js"></script>
		<script language="JavaScript" src="../scripts/Ajax/Base/overlibmws.js"></script>
		<script language="JavaScript" src="../scripts/Ajax/Base/builder.js"></script>
		<script language="JavaScript" src="../scripts/Ajax/Base/scriptaculous.js"></script>
		<script language="JavaScript" src="../scripts/Ajax/Base/ajaxtags-1.2-beta2.js"></script>
		<script type="text/javascript">			
		
			if (document.layers)
				window.document.captureEvents(Event.KEYDOWN);
				
			window.document.onkeydown = function (evt) { 
				var keyCode = evt ? (evt.which ? evt.which : evt.keyCode) : event.keyCode;
								
				if (keyCode == 13) {	
					window.document.getElementById("lancar").click();
				} 
	 		} 
	 		
			// Variável para determinar o id dos campos inseridos dinâmicamente o form
			var fields = 0;	
					
			/** Insere uma linha com uma nota na tabela de lançamentos */
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
			
				/* variáveis que identificam o tipo de nota */
				var isServico = window.document.getElementById("servico").checked;
				var isPeca	  = window.document.getElementById("peca").checked ;
				var isRemessa = window.document.getElementById("remessa").checked;
				var isMO3	  = window.document.getElementById("maoObra3").checked;
				
				/* Rotina para validar os valores informados de acordo com o tipo da nota */
				//Para nota de Serviço
				if ( isServico  ) {
				
					if ( serviceValueNF <= 0 ) {
						window.alert("O valor de serviço não pode ser menor ou igual a 0(Zero)!");
						window.document.forms(0).serviceValueNF.focus();
						return false;
					}				
				// Para nota de Peça
				} else if ( isPeca  ) {
				
					if ( pecaValueNF <= 0 ) {
						window.alert("O valor de peça não pode ser menor ou igual a 0(Zero)!");
						window.document.forms(0).pecaValueNF.focus();
						return false;
					}				
				// Para nota de Remessa
				} else if ( isRemessa  ) {
				
					if ( remessaValueNF <= 0 ) {
						window.alert("O valor de remessa não pode ser menor ou igual a 0(Zero)!");
						window.document.forms(0).remessaValueNF.focus();
						return false;
					}				
				// Para nota de MO3
				} else if ( isMO3  ) {
				
					if ( mo3ValueNF <= 0 ) {
						window.alert("O valor de M.O.3 não pode ser menor ou igual a 0(Zero)!");
						window.document.forms(0).mo3ValueNF.focus();
						return false;
					}				
				
				} else {
					window.alert("O Tipo da Nota não foi selecionado!");
					return false;
				}
				
				// Incrementamos a variável que determina o id dos campos
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
						
								window.alert("A nota fiscal número " + numeroNF + " de série " + serieNF + ", já foi lançada!");
								--fields;
								return false;					
								
							}
						} else if ( numNfComp != undefined ) {
						
							if ( (numNfComp.value == numeroNF) && (serNFComp.value == serNFComp) ) {	
						
								window.alert("A nota fiscal número " + numeroNF + " de série " + serieNF + ", já foi lançada!");
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
							  "<input type='hidden' name='mo3Check_" + fields + "' value='" + isMO3 + "'>";
							  
				b.innerHTML = "<span class='text' >" + destinatarioNota + "</span>" + 
							  "<input type='hidden' name='destinatarioNota_" + fields + "' value='" + destinatarioNota + "'>";
								
				c.innerHTML="<span class='text' >" + numeroNF + "</span>" + 
							"<input type='hidden' name='numeroNF_" + fields + "' value='" + numeroNF + "'>";
							
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
				
				window.document.getElementById("contField").value = fields;
				
				/* vamos retornar ao estado default os campos de valores */
				resetValues();
				
				/* Quando os valores estiverem zerados habilitamos o botão gravar */
				window.document.getElementById("gravar").disabled = !validateValues();
				
			}
			
			
			//********************************************************************************************//
			//* Realiza a verificação dos valores de saldos retornando TRUE se os saldo estiverem  todos *//
			//* zerados ou dentro da tolerância parametrizada 											 *//
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
				
				//* Se a tolerância enviada como parâmetro dor diferente de zero, devemos conferir a diferença */
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
			
				// Verificamos se o lançamento foi individual
				if ( individualLaunch != "S" ) {
				
					if ( isVmSaldoOk && isVpSaldoOk && isVrSaldoOk && isVmtSaldoOk ) 
						return true;
					else
						return false;	
						
				} else {
					// Quando for individual, será pemitido lançamento com diferença no valor da remessa
					
					// Setamos a flag isDif para indicar se existe diferença ou não para o controle (Action)
					// "S" - Com diferença  e "N" - Sem diferença
					if ( !isVrSaldoOk ){
						//window.alert("Alterando o status de isDif para S");
						window.document.getElementById("isDif").value = "S";						
					} else { 
						//window.alert("Alterando o status de isDif para N");
						window.document.getElementById("isDif").value = "N";
					}
					
					if ( isVmSaldoOk && isVpSaldoOk && isVmtSaldoOk ) 
						return true;
					else
						return false;	
										
				}				
			
			}
			
			/* Atualiza a coluna de valores lançados da área de Controle de Saldos
			 *
			 * @param valueNF
 			 *   Valor
			 *
			 * @param linha
			 *   linha escolhida para atualização - YMB, YMA ou cliente
			 
			 * @param coluna
			 *   prefixo dos objetos que devem sr atualizados
			 *
			 * @param operacao
			 *   "ADD"  para adicionar linha e "DEL" para remover linha
			*/			
			function updateValues(valueNF, linha, coluna, operacao) {
				
				/* Pegamos os valores da área de saldo da linha correspondente para realizar a atualização */
				// Valor do objeto Hidden  da coluna Lançado								
				var lancHidden = Number(deci(eval("window.document.forms(0)." + coluna + "LancHidden_" + linha + ".value")));
								
				// Valor do objeto Hidden com o total da coluna  Lançado				
				var lancTotalHidden = Number(deci(eval("window.document.forms(0)." + coluna + "LancTotalHidden.value")));	
								
				// Valor do objeto Hidden da coluna Saldo
				var saldoHidden= Number(deci(eval("window.document.forms(0)." + coluna + "SaldoHidden_" + linha + ".value")));
				
				// Valor do objeto Hidden com o total da coluna Saldo				
				var saldoTotalHidden = Number( deci(eval( "window.document.forms(0)." + coluna + "SaldoTotalHidden.value")));	
											
				// Objeto Hidden da coluna Lançado
				var objLancHidden   = eval("window.document.forms(0)." + coluna + "LancHidden_" + linha);
				// Objeto Span para apresentar o valor lançado na tela
				var objLanc         = eval("window.document.getElementById('" + coluna + "Lanc_" + linha + "')");
				
				// Objeto Hidden da coluna Saldo
				var objSaldoHidden   = eval("window.document.forms(0)." + coluna + "SaldoHidden_" + linha);
				// Objeto Span para apresentar o valor Saldo na tela
				var objSaldo         = eval("window.document.getElementById('" + coluna + "Saldo_" + linha + "')");
				
				// Variáveis 
				var newLanc 	  = new Number(); // novo valor lançado
				var newLancTotal  = new Number(); // novo total de valor lançado
				var newSaldo 	  = new Number(); // Novo saldo
				var newSaldoTotal = new Number(); // Novo valor total do saldo
				var newValueNF    = new Number(valueNF);
				
				/* Quando for uma operação de adicionar uma linha, somamos os valores*/
				if ( operacao == "ADD" ) {	
							
					newLanc = newValueNF + lancHidden;
					
					newLancTotal = lancTotalHidden + newValueNF;
					
					newSaldo = saldoHidden - newValueNF;					
					
					newSaldoTotal = saldoTotalHidden - newValueNF;

				
				} else if ( operacao == "DEL" ) {
				
					/* Quando for uma operação de remover uma linha, subtraimos os valores*/
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
				
				//window.alert("Lançado Hidden:" + objLancHidden.value + " Saldo Hidden:" + objSaldoHidden.value);
				//window.alert("Total Lançado Hidden:" + objLancTotalHidden.value + " Total Saldo Hidden:" + objSaldoTotalHidden.value);	
																		
			
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
					
						window.document.forms(0).servico.disabled				= false;
						window.document.forms(0).peca.disabled 				    = false;
						window.document.forms(0).remessa.disabled 				= false;
						window.document.forms(0).maoObra3.disabled 			    = false;
						
					} else if ( window.document.forms(0).emitenteNota.value == "Outros" ) {
					
						defEmitente( "Outros" );
					
					}
				
				</core:if>
			}
			
			// Inclui uma sub-linha para cadastro de uma nota complementar 
			function incluirNota(cont,line) {
				
				var index = window.document.getElementById(line).rowIndex + 1; 
				
				if ( (window.document.getElementById("n" + line) == null) && (window.document.getElementById("c" + line) == null)) {
					
					var o = window.document.getElementById("values");
					
					// Vamos  inserir uma linha título
					var idTitle = "tn" + line;
					
					var y = o.insertRow(index++);
					y.id = idTitle
								
					//var t0 = y.insertCell(0);
					var t1 = y.insertCell(0);
					t1.colSpan = "11";
					t1.align = "center";
					t1.className = "td_subLine"
					t1.innerHTML = "Nota Fiscal Complementar";
					//var t2 = y.insertCell(2);	
					//t2.align = "center";
					//t2.rowSpan = "2";
					//t2.vAlign = "middle";
										
					/* Agora inserimos a linha com os campos */
					var id = "n" + line;
					var x = o.insertRow(index);
					x.id = id;
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
					
					a.align = "center";
					b.align = "center";
					c.align = "center";
					d.align = "center";
					e.align = "center";
					f.align = "center";
					g.align = "center";
					h.align = "center";
					i.align = "center";
					j.align = "center";
					//l.align = "center";
										
					//t2.innerHTML="<span class='text' align='center'><a href=javascript:removeSub('" + cont + "','" + id + "');>" 
								// + "<img src='../images/cancelar.jpg' border='0' alt='Remover Item'/></a></span>";
					
					var objEmitente = eval("window.document.getElementById('emitenteNota_" + cont + "')");
										
					a.innerHTML="<span class='text' align='center'>" + objEmitente.value + "</span>";
					
					var objDestinatario = eval("window.document.getElementById('destinatarioNota_" + cont + "')");
												
					b.innerHTML="<span class='text' align='center'>" + objDestinatario.value + "</span>";
								
					c.innerHTML="<input type='text' class='text_field_maior' name='numeroNF_COMP_"
								 + cont + "' size='10' onblur=isNumber('numeroNF_COMP_" + cont + "') onkeypress='return blockChar(event);' maxlength='10' >";			
								
					d.innerHTML="<input type='text' class='text_field_maior' name='serieNF_COMP_"
								 + cont + "' size='10' maxlength='10'>";
					
					var fieldDate = "dateNF_COMP_" + cont;			
					e.innerHTML = "<input type='text' name='" + fieldDate + "' maxlength='10' size='11' class='text_field_date'				 					    OnKeyUp=mascara_data('" + fieldDate + "',event) />";
													
					f.innerHTML="<input type='text' class='text_field_maior' name='serviceValueNF_COMP_"
								 + cont + "' size='10' maxlength='10' >";
								 
								 
					g.innerHTML="<input type='text' class='text_field_maior' name='pecaValueNF_COMP_"
								 + cont + "' size='10'  maxlength='10' >";
								 
					h.innerHTML="<input type='text' class='text_field_maior' name='remessaValueNF_COMP_"
								 + cont + "' size='10'  maxlength='10' >";
								 
					i.innerHTML="<input type='text' class='text_field_maior' name='mo3ValueNF_COMP_"
								 + cont + "' size='10'  maxlength='10' >";
					
					//j.innerHTML="<input type='text' class='text_field_maior' name='icmsValueNF_COMP_"
					//			 + cont + "' size='10'  maxlength='10' >";
								
					j.innerHTML="<input type='button' class='button_medium' name='item' value='Confirmar' onclick=updateItem('" 
								 + cont + "','" + id +"') >";
								 
					j.width = "50";
					
				} else {
				
					window.alert("Operação inválida, já existe um item incluído!");
					
				}
			
			}
			
			function updateItem( cont, id ) {
			
				var objServicoComp = Number(deci(eval("window.document.getElementById('serviceValueNF_COMP_" + cont  + "').value")));
				var objPecaComp    = Number(deci(eval("window.document.getElementById('pecaValueNF_COMP_" + cont + "').value")));
				var objRemessaComp = Number(deci(eval("window.document.getElementById('remessaValueNF_COMP_" + cont +"').value")));
				var objMo3Comp     = Number(deci(eval("window.document.getElementById('mo3ValueNF_COMP_" + cont + "').value")));
				//var objIcmsComp    = Number(deci(eval("window.document.getElementById('icmsValueNF_COMP_" + cont + "').value")));
				var numeroNF       = Number(window.document.getElementById("numeroNF_COMP_" + cont ).value);
				var serieNF        = window.document.getElementById("serieNF_COMP_" + cont ).value;
				var dateNF         = window.document.getElementById("dateNF_COMP_" + cont ).value;
				var linhaHidden    = window.document.forms(0).linhaHidden.value;
				
				// Data da Nota fiscal principal
				var dateNF_SUP = window.document.getElementById("dateNF_" + cont ).value;
				
				if ( comparaData( dateNF, dateNF_SUP) == -1 ) {
				 	window.alert("A data da Nota Fiscal complementar não pode ser menor que a data da Nota Fiscal principal!");
				 	window.document.getElementById("dateNF_COMP_" + cont ).focus();
					return false;
				}
				
				if ( cont > 1 ) {
					for( i = 2 ; i <= cont ; i++ ) {
					
						var numNf     = eval("window.document.getElementById('numeroNF_" + i + "')");
						var numNfComp = eval("window.document.getElementById('numeroNF_COMP_" + i + "')");
						if ( numNf != undefined ) {
							if ( numNf.value == numeroNF ) {	
						
								window.alert("A nota fiscal número " + numeroNF + " já foi lançada!");
								return false;					
								
							}
						} else if ( numNfComp != undefined ) {
						
							if ( numNfComp.value == numeroNF ) {	
						
								window.alert("A nota fiscal número " + numeroNF + " já foi lançada!");
								return false;			
								
							}
							
						}
					}  
				}
				
				//window.alert("Serviço:" + objServicoComp + " - Peça:" + objPecaComp + " - Remessa:" + objRemessaComp + " - Mo3:" + objMo3Comp);
				if ( objServicoComp != 0 )
					updateValues( objServicoComp, linhaHidden, "vm", "ADD" );
				if ( objPecaComp != 0 )					
					updateValues( objPecaComp, linhaHidden, "vp", "ADD" );
				if ( objRemessaComp != 0 )
					updateValues( objRemessaComp, linhaHidden, "vr", "ADD" );
				if ( objMo3Comp != 0 )
					updateValues( objMo3Comp, linhaHidden, "vmt", "ADD" );	
				
				// Número da Nota Fiscal
				window.document.getElementById(id).cells[2].innerHTML = 
					"<span class='text' >" + numeroNF + "</span>" + 
					"<input type='hidden' name='numeroNF_COMP_" + cont + "' value='" + numeroNF + "'>";
						
				// Série da Nota Fiscal	
				window.document.getElementById(id).cells[3].innerHTML =	
					"<span class='text'>" + serieNF + "</span>" + 
					"<input type='hidden' name='serieNF_COMP_" + cont + "' value='" + serieNF + "'>";
					
				//Data
				window.document.getElementById(id).cells[4].innerHTML =	
					"<span class='text'>" + dateNF + "</span>" + 
					"<input type='hidden' name='dateNF_COMP_" + cont + "' value='" + dateNF + "'>";
					
				window.document.getElementById(id).cells[5].innerHTML =	
					"<span class='text' align='right'>" + convMoeda(deci(objServicoComp)) + "</span>" + 
					"<input type='hidden' name='serviceValueNF_COMP_" + cont + "' value='" + objServicoComp + "' >";
					
				window.document.getElementById(id).cells[6].innerHTML =	
					"<span class='text' align='right'>" + convMoeda(deci(objPecaComp)) + "</span>"+ 
					"<input type='hidden' name='pecaValueNF_COMP_" + cont + "' value='" + objPecaComp + "' >";
				
				window.document.getElementById(id).cells[7].innerHTML =						
					"<span class='text' align='right'>" + convMoeda(deci(objRemessaComp)) + "</span>"+ 
					"<input type='hidden' name='remessaValueNF_COMP_" + cont + "' value='" + objRemessaComp + "' >";
				
				window.document.getElementById(id).cells[8].innerHTML =		
					"<span class='text' align='right'>" + convMoeda(deci(objMo3Comp)) + "</span>"+ 
					"<input type='hidden' name='mo3ValueNF_COMP_" + cont + "' value='" + objMo3Comp + "' >";
				
				//window.document.getElementById(id).cells[9].innerHTML =	
				//	"<span class='text' align='right'>" + convMoeda(deci(objIcmsComp)) + "</span>"+ 
				//	"<input type='hidden' name='icmsValueNF_COMP_" + cont + "' value='" + objIcmsComp + "' >";	
							
				window.document.getElementById(id).cells[9].innerHTML ="<a href=javascript:removeSub('" + cont + "','" + id + "');><img src='../images/cancelar.jpg' border='0' alt='Remover Item'/></a>";
	
				/* Quando os valores estiverem zerados habilitamos o botão gravar */
				window.document.getElementById("gravar").disabled = !validateValues();
			}
			
			function removeRow(lineTable, lineObj) {
			
				if ( window.confirm("Confirma a exclusão da nota?") ) {
				
					var service   = Number(deci(eval("window.document.getElementById( 'serviceValueNF_" + lineObj + "').value")));
					var peca      = Number(deci(eval("window.document.getElementById( 'pecaValueNF_"    + lineObj + "').value")));
					var remessa   = Number(deci(eval("window.document.getElementById( 'remessaValueNF_" + lineObj + "').value")));
					var mo3       = Number(deci(eval("window.document.getElementById( 'mo3ValueNF_"     + lineObj + "').value")));
					var linha     = Number(deci(eval("window.document.getElementById( 'linhaNota_"      + lineObj + "').value")));
								
					//window.alert("Valor Serviço:" + service);	
						
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
					
					<core:if test="${idTipoLote == 2}">		
						window.document.forms(0).servico.disabled	= false;
						window.document.forms(0).peca.disabled 		= false;
						window.document.forms(0).remessa.disabled 	= false;
						window.document.forms(0).maoObra3.disabled 	= false;	
					</core:if>
					
					/* Quando os valores estiverem zerados habilitamos o botão gravar */
					window.document.getElementById("gravar").disabled = !validateValues();					
						
				}
				
			}
			
			/* Remove apenas uma sub-linha de uma nota */
			function removeSub(lineTable,id) {
				
					var service   = Number(deci(eval("window.document.getElementById( 'serviceValueNF_COMP_" + lineTable + "' ).value")));
					var peca      = Number(deci(eval("window.document.getElementById( 'pecaValueNF_COMP_" + lineTable + "').value")));
					var remessa   = Number(deci(eval("window.document.getElementById( 'remessaValueNF_COMP_" + lineTable + "').value")));
					var mo3       = Number(deci(eval("window.document.getElementById( 'mo3ValueNF_COMP_" + lineTable + "').value")));
					var linha     = Number(deci(eval("window.document.getElementById( 'linhaNota_" + lineTable + "').value")));
					
									 
					var o = window.document.getElementById("values");
					var t = window.document.getElementById("t" + id).rowIndex; 			
					var r = window.document.getElementById(id).rowIndex; 			
					o.deleteRow(r);
					o.deleteRow(t);	
					
					//window.alert("Valor Serviço:" + service);	
					
					if ( service != 0 )
						updateValues( service, linha, "vm", "DEL");
						
					if ( peca != 0 )
						updateValues( peca, linha, "vp", "DEL");
						
					if ( remessa != 0 )
						updateValues( remessa, linha, "vr", "DEL");	
					
					if ( mo3 != 0 )
						updateValues( mo3, linha, "vmt", "DEL");
						
			}
					
			/*  Validação digitação de valor númerico com casas decimais */
			/*  As casas decimais podem ser definidas com uso de vírgula ou ponto */ 
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
					window.alert("Valor inválido!");
					window.document.getElementById(field).value = "";
					window.document.getElementById(field).focus();
				}
			}
			
			/* Habilita ou desabilita os campos de valores conforme o checkbox selecionado */
			function enableValues(idCheck, idField) {
			
				var check      = window.document.getElementById(idCheck);
				var fieldValue = window.document.getElementById(idField);
									
				if ( check.checked ) {
						
					fieldValue.readOnly = false;
					fieldValue.focus();
						
				} else {
						
					fieldValue.value = "";	
					fieldValue.readOnly = true;
						
				}
				/*
				if ( check.name == "peca" ) {
				
					if ( check.checked ) {
						window.document.getElementById("icmsValueNF").readOnly = false;
					} else {
						window.document.getElementById("icmsValueNF").value = "";
						window.document.getElementById("icmsValueNF").readOnly = true;						
					}
				}
				*/
				if ( check.name == "servico" || check.name == "peca" ) {
				
					if ( check.checked ) {
						window.document.getElementById("remessa").disabled  = true;
						
					} else if ( !window.document.getElementById("servico").checked && !window.document.getElementById("peca").checked && !window.document.getElementById("maoObra3").checked){
						window.document.getElementById("remessa").disabled  = false;											
					}				
				
				} else if ( check.name == "remessa" ) {
				
					if ( check.checked ) {
						//window.document.getElementById("icmsValueNF").value = "";
						//window.document.getElementById("icmsValueNF").readOnly = true;	
						window.document.getElementById("servico").disabled  = true;
						window.document.getElementById("peca").disabled     = true;
						window.document.getElementById("maoObra3").disabled = true;
						
					} else {					
						window.document.getElementById("servico").disabled  = false;
						window.document.getElementById("peca").disabled     = false;
						window.document.getElementById("maoObra3").disabled = false;					
					}				
				
				} else if ( check.name == "maoObra3" ) {
					
					if ( window.document.getElementById("emitenteNota").value == "Conc" ) {
						if ( check.checked ) {
							//if ( !window.document.getElementById("peca").checked ) {
							//	window.document.getElementById("icmsValueNF").value 	= "";
							//	window.document.getElementById("icmsValueNF").readOnly 	= true;	
							//}
							window.document.getElementById("servico").disabled = false;
							window.document.getElementById("peca").disabled    = false;
							window.document.getElementById("remessa").disabled = true;
						} else {
							window.document.getElementById("servico").disabled = false;
							window.document.getElementById("peca").disabled    = false;
							if ( !window.document.getElementById("servico").checked && !window.document.getElementById("peca").checked ){
								window.document.getElementById("remessa").disabled  = false;											
							}			
						}	
					}			
				
				}
			}
			
			/* Inserir o destinatário da nota */
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
					window.document.getElementById("maoObra3").checked  = true;
					
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
				//window.alert("Valor pré - Converção:" + N);
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
				//window.alert("Valor pós - Conversão:" + Term);
				return Term;
			}
			
			/** 
			*   Função que pega o retorno da validação do número da nota e da série
			*   para permitir a inclusão da nota ou não
			*/
			function posGetValidateNF() {
				
				var tgValidateNF = window.document.getElementById('targetValidateNF').value;
				var tgMessageNF  = window.document.getElementById('targetMessageNF').value;
				
				if ( validateDataNF() ) {
				
					//window.alert("Validação:" + tgValidateNF + " - Mensagem:" + tgMessageNF);
				
					if ( tgValidateNF == "false" ) {
					
						window.alert("Nota Inválida! Erro:" + tgMessageNF );
					
					} else if ( tgValidateNF == "true"  ) {
					
						insRow();
						
					}	
				}
							
			}
			
			function expFunction() {
			
				window.alert("ERRO na recuperação das informações da nota!");
			
			}
			
			/** Realiza validações nos valores da nota fiscal */  
			function validateDataNF() {
			
				var data1 = window.document.getElementById("sysDate").value;
				var data2 = window.document.forms(0).dateNF.value;
				
				if ( window.document.forms(0).numeroNF.value == "" ) {
					window.alert("O número da nota é obrigatório!");
					if ( window.document.forms(0).numeroNF.disabled == false ) {
						window.document.forms(0).numeroNF.focus();
					}
					return false;
				}
				
				if ( window.document.forms(0).serieNF.value == "" ) {
					window.alert("A série da nota é obrigatória!");
					if ( window.document.forms(0).serieNF.disabled == false ) {
						window.document.forms(0).serieNF.focus();
					}
					return false;
				}
				
				if ( window.document.forms(0).dateNF.value == "" ) {
					window.alert("A data da nota é obrigatório!");
					if ( window.document.forms(0).serieNF.disabled == false ) {
						window.document.forms(0).dateNF.focus();
					}
					return false;
				} else if ( comparaData( data1, data2 ) < 0 ) {
					window.alert("A data da nota fiscal não pode ser maior que a data atual!");
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
				<td class="tr_line" align="center" colspan="2"><bean:message key="notaFiscal.launch.service"/></td>
				<td class="tr_line" align="center" colspan="2"><bean:message key="notaFiscal.launch.peca"/></td>
				<td class="tr_line" align="center" colspan="2"><bean:message key="notaFiscal.launch.remessa"/></td>
				<td class="tr_line" align="center" colspan="2"><bean:message key="notaFiscal.launch.mo3"/></td>
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
					<!-- Para lote de revisão o emitente será sempre a Concessionária  -->
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
						<input type="checkbox" name="servico"  value="serv" checked="checked" onclick="enableServico('servico' , 'serviceValueNF');"/><bean:message key="notaFiscal.ServiceValue"/>&nbsp;
						<input type="checkbox" name="peca"     value="pec"  disabled="disabled"/><bean:message key="notaFiscal.pecaValue"/>&nbsp;
						<input type="checkbox" name="remessa"  value="rem"  disabled="disabled"/><bean:message key="notaFiscal.remessaValue"/>&nbsp;
						<input type="checkbox" name="maoObra3" value="mo3"  disabled="disabled"/><bean:message key="notaFiscal.mo3Value"/>&nbsp;
						<script language="javascript">
							
							function enableServico(idCheck, idField) {
			
								var check      = window.document.getElementById(idCheck);
								var fieldValue = window.document.getElementById(idField);
								if ( check.checked ) {
						
									fieldValue.readOnly = false;
						
								} else {
						
									fieldValue.value = "";	
									fieldValue.readOnly = true;
						
								}
							}
							
						</script>						
					</core:if>
					<core:if test="${idTipoLote == 2}">
						<input type="checkbox" name="servico"  value="serv" onclick="enableValues('servico' , 'serviceValueNF');"/><bean:message key="notaFiscal.ServiceValue"/>&nbsp;
						<input type="checkbox" name="peca"     value="pec"  onclick="enableValues('peca'    , 'pecaValueNF');"/><bean:message key="notaFiscal.pecaValue"/>&nbsp;
						<input type="checkbox" name="remessa"  value="rem"  onclick="enableValues('remessa' , 'remessaValueNF');"/><bean:message key="notaFiscal.remessaValue"/>&nbsp;
						<input type="checkbox" name="maoObra3" value="mo3"  onclick="enableValues('maoObra3', 'mo3ValueNF');"/><bean:message key="notaFiscal.mo3Value"/>&nbsp;
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
		                 monthLabels	="Janeiro|Fevereiro|Março|Abril|Maio|Junho|Julho|Agosto|Setembro|Outubro|Novembro|Dezembro" 
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
						<html:text property="serviceValueNF" styleClass="text_field_maior" maxlength="10" onkeypress="return formatar_moeda(this,'.',',',event);"></html:text>
					</core:if>
					<core:if test="${idTipoLote == 2}">
						<html:text property="serviceValueNF" styleClass="text_field_maior" maxlength="10" readonly="true" onkeypress="return formatar_moeda(this,'.',',',event);"></html:text>
					</core:if>
				</td>
				<td class="text" align="center">
					<html:text property="pecaValueNF" styleClass="text_field_maior" maxlength="10" readonly="true" onkeypress="return formatar_moeda(this,'.',',',event);"></html:text>
				</td>
				<td class="text" align="center">
					<html:text property="remessaValueNF" styleClass="text_field_maior" maxlength="10" readonly="true" onkeypress="return formatar_moeda(this,'.',',',event);"></html:text>
				</td>
				<td class="text" align="center">
					<html:text property="mo3ValueNF" styleClass="text_field_maior" maxlength="10" readonly="true" onkeypress="return formatar_moeda(this,'.',',',event);"></html:text>
				</td>
				<%--
				<td class="text" align="center">
					<core:if test="${idTipoLote == 1}">
						<html:text property="serviceValueNF" styleClass="text_field_maior" maxlength="10" onblur="isNumber('serviceValueNF')"></html:text>
					</core:if>
					<core:if test="${idTipoLote == 2}">
						<html:text property="serviceValueNF" styleClass="text_field_maior" maxlength="10" readonly="true" onblur="isNumber('serviceValueNF')"></html:text>
					</core:if>
				</td>
				<td class="text" align="center">
					<html:text property="pecaValueNF" styleClass="text_field_maior" maxlength="10" readonly="true" onblur="isNumber('pecaValueNF')"></html:text>
				</td>
				<td class="text" align="center">
					<html:text property="remessaValueNF" styleClass="text_field_maior" maxlength="10" readonly="true" onblur="isNumber('remessaValueNF')"></html:text>
				</td>
				<td class="text" align="center">
					<html:text property="mo3ValueNF" styleClass="text_field_maior" maxlength="10" readonly="true" onblur="isNumber('mo3ValueNF')"></html:text>
				</td>
				--%>
				<%-- %><td class="text" align="center">
					<html:text property="icmsValueNF" styleClass="text_field_maior" readonly="true" maxlength="10" onblur="isNumber('icmsValueNF')"></html:text>
				</td>--%>
				
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
									  postFunction="posGetValidateNF"
									  errorFunction="expFunction"/> 
				</td>
			</tr>
			</table>
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
 						<html:button  property="gravar" styleClass="button_medium" disabled="true" onclick="submitFormTsk(notaFiscalListForm,'save',true)"><bean:message key="form.btn.save"/></html:button>	
 						<%--<html:button  property="gravar" styleClass="button_medium" disabled="true" onclick="javascript:alert('Em Construção!');"><bean:message key="form.btn.save"/></html:button>--%>	
						&nbsp;
						<html:reset property="limpar" styleClass="button_medium"><bean:message key="form.btn.clear"/></html:reset>	
				</td>
			</tr>	
			</table>
			</center>
		</html:form>
	</body>
</html:html>
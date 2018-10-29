<%@taglib uri="http://struts.apache.org/tags-bean"     prefix="bean"    %>
<%@taglib uri="http://struts.apache.org/tags-html"     prefix="html"    %>
<%@taglib uri="http://struts.apache.org/tags-html-el"  prefix="html-el" %>
<%@taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic"   %>
<%@taglib uri="http://java.sun.com/jstl/core" 		   prefix="c" 	   %>
<%@ taglib uri="/tld/ym"  	 prefix="ym"    %>

<%-- 
/* Resource Informática LTDA.
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......home.jsp
 *   .: Criação.....30 de março de 2007, 15:31
 *   .: Autor.......Thiago Uriel M. Garcia
 *   .: Descrição...Tela principal do sistema.
 */
--%>

<html:html>
	<head>
		<title><bean:message key="home.title"/></title>
		<html:base />			
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"></meta>
		<link rel="stylesheet" type="text/css" href="../css/yamaha.css"></link>
		<link rel="stylesheet" type="text/css" href="../css/menu/menu.css"></link>
		<link rel="stylesheet" type="text/css" href="../css/themes/default.css"></link>
		<link rel="stylesheet" type="text/css" href="../css/themes/mac_os_x.css"></link>
		<link rel="stylesheet" type="text/css" href="../css/themes/alphacube.css"></link>
		<link rel="stylesheet" type="text/css" href="../css/themes/pwc-os.css"></link>
		<script type="text/javascript" src="../scripts/Menu/menu.js"> </script>
		<script type="text/javascript" src="../scripts/Ajax/Clock/ajaxClock.js"> </script>
		<script type="text/javascript" src="../scripts/WindowUI/window_utils.js"> </script>
		<script type="text/javascript" src="../scripts/WindowUI/prototype.js"> </script>
		<script type="text/javascript" src="../scripts/WindowUI/effects.js"> </script>
		<script type="text/javascript" src="../scripts/WindowUI/window.js"> </script>
		<script type="text/javascript" src="../scripts/WindowUI/window_effects.js"> </script>
		<script type="text/javascript" src="../scripts/WindowUI/debug.js"> </script>
		<%--<script type="text/javascript" src="../scripts/WindowUI/pwc-os.js"> </script>--%>
	</head>
	<body topmargin="0" leftmargin="0" onload="getClock();">
		
		<%--<div id="dock"></div>--%>
		
		<!-- Início: Tabela de alinhamento -->
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
		
			<!-- Início: Header -->
			<tr>
				<td align="left" valign="middle">
				
					<!-- Início: Logo -->
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td width="160">							
								<c:if test="${sessionScope.YM_SESSION_USER_INNER.nivelAcesso.codigo == 'C' || sessionScope.YM_SESSION_USER_INNER.nivelAcesso.codigo == 'R'}" >									
									<img src="../images/home_logo_red.gif" border="0" alt="Yamaha"/>									
								</c:if>
								<c:if test="${sessionScope.YM_SESSION_USER_INNER.nivelAcesso.codigo != 'C' && sessionScope.YM_SESSION_USER_INNER.nivelAcesso.codigo != 'R' }" >
									<html-el:link action="/Home.do?task=backInner" onmouseout="window.status='';return true;" onmouseover="window.status='Retornar para o Sistema Interno';return true;">
										<img src="../images/home_logo_red.gif" border="0" alt="Yamaha"/>
									</html-el:link>
								</c:if>
							</td>
							<td align="center" valign="middle"><img src="../images/title_home.gif" border="0" alt="Sistema de Garantia"/></td>
						</tr>
					</table>
					<!-- Fim: Logo -->
				
				</td>
				<td align="right" valign="middle">
				
					<!-- Início: Relógio -->
					<table border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td><img src="../images/home_clock_01.gif" border="0"/></td>
							<td background="../images/home_clock_02.gif" align="right" valign="middle" class="clock">
								<div id="clockDiv"></div>
							</td>
							<td><img src="../images/home_clock_03.gif" border="0"/></td>
						</tr>
					</table>
					<!-- Fim: Relógio -->
				
				</td>
			</tr>
			<tr>
				<td align="left" valign="top" colspan="2" width="100%">
				
					<!-- Início: Barra de divisão -->
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td width="144"><img src="../images/header_separator_01_01.gif" border="0"/></td>
							<td background="../images/header_separator_01_02.gif"></td>
							<td width="6"><img src="../images/header_separator_01_03.gif" border="0"/></td>
						</tr>
						<tr>
							<td><img src="../images/header_separator_02_01.gif" border="0"/></td>
							<td background="../images/header_separator_02_02.gif" align="right" valign="middle" class="bold">
								<ym:showConcessionaria/>
							</td>
							<td><img src="../images/header_separator_02_03.gif" border="0"/></td>
						</tr>
						<tr>
							<td><img src="../images/header_separator_03_01.gif" border="0"/></td>
							<td></td>
							<td></td>
						</tr>
					</table>
					<!-- Fim: Barra de divisão -->
				
				</td>
			</tr>
			<!-- Fim: Header -->
			
			<!-- Início: Body -->
			<tr>
				<td align="left" valign="top">
				
					<table border="0" cellpadding="0" cellspacing="0">
						
						<!-- Início: Item de menu (Clientes) -->
						<tr>
							<td align="left" valign="top">
							
								<!-- Início: Botão de menu (Clientes) -->
								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td id="clientesItemA" width="12"  height="56" style="background-image: none;"></td>
										<td id="clientesItemB" width="127" height="56" style="background-image: none;" valign="middle">
										
											<table border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td id="clientesItemC" width="46" height="46" style="background-image: url('../images/menu_button_left_normal.gif');" align="center" valign="middle">
														<img src="../images/menu_icon_clientes.gif" border="0"/>
													</td>
													<td id="clientesItemD" width="81" height="46" style="background-image: url('../images/menu_button_right_normal.gif');" align="left" valign="middle">
														<table border="0" cellpadding="0" cellspacing="0">
															<tr>
																<td width="5"></td>
																<td><a id="clientesOption" href="JavaScript:openWindow('win_sg_clientes_search', 'Pesquisa de Clientes', 'pageDispatch.jsp?param=/ym_sistema_garantia/Cliente.do?task=search');" class="menu_text" onmouseout="window.status='';return true;" onmouseover="window.status='Pesquisa de Clientes';return true;">Clientes</a></td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td></td>
										<td id="clientesItemE" width="123" height="3" style="background-image: none;"></td>
									</tr>
								</table>
								<!-- Fim: Botão de menu (Clientes) -->
							
							</td>
						</tr>
						<!-- Fim: Item de menu (Clientes) -->

						<!-- Início: Item de menu (Lotes) -->
						<tr>
							<td align="left" valign="top">
							
								<!-- Início: Botão de menu (Lotes) -->
								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td id="lotesItemA" width="12"  height="56" style="background-image: none;"></td>
										<td id="lotesItemB" width="127" height="56" style="background-image: none;" valign="middle">
										
											<table border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td id="lotesItemC" width="46" height="46" style="background-image: url('../images/menu_button_left_normal.gif');" align="center" valign="middle">
														<img src="../images/menu_icon_revisoes.gif" border="0"/>
													</td>
													<td id="lotesItemD" width="81" height="46" style="background-image: url('../images/menu_button_right_normal.gif');" align="left" valign="middle">
														<table border="0" cellpadding="0" cellspacing="0">
															<tr>
																<td width="5"></td>
																<td><a id="lotesOption" href="JavaScript:openWindow('win_sg_lote_list', 'Relação de Lotes', 'pageDispatch.jsp?param=/ym_sistema_garantia/Lote.do?task=list');" class="menu_text" onmouseout="window.status='';return true;" onmouseover="window.status='Relação de Lotes';return true;">Lote</a></td>																
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td></td>
										<td id="lotesItemE" width="123" height="3" style="background-image: none;"></td>
									</tr>
								</table>
								<!-- Fim: Botão de menu (lotes) -->
							
							</td>
						</tr>
						<!-- Fim: Item de menu (Lotes) -->
					
						<!-- Início: Item de menu ((Nota Fiscal) -->
						<tr>
							<td align="left" valign="top">
							
								<!-- Início: Botão de menu ((Nota Fiscal) -->
								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td id="notasItemA" width="12"  height="56" style="background-image: none;"></td>
										<td id="notasItemB" width="127" height="56" style="background-image: none;" valign="middle">
										
											<table border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td id="notasItemC" width="46" height="46" style="background-image: url('../images/menu_button_left_normal.gif');" align="center" valign="middle">
														<img src="../images/notaFiscal.gif" border="0"/>
													</td>
													<td id="notasItemD" width="81" height="46" style="background-image: url('../images/menu_button_right_normal.gif');" align="left" valign="middle">
														<table border="0" cellpadding="0" cellspacing="0">
															<tr>
																<td width="5"></td>
																<td><a id="notasOption" href="JavaScript:openWindow('win_sg_notaFiscal_list', 'Nota Fiscal', 'pageDispatch.jsp?param=/ym_sistema_garantia/NotaFiscal.do?task=list');" class="menu_text" onmouseout="window.status='';return true;"  onmouseover="window.status='Nota Fiscal';return true;">Nota Fiscal</a></td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td></td>
										<td id="notasItemE" width="123" height="3" style="background-image: none;"></td>
									</tr>
								</table>
								<!-- Fim: Botão de menu (Nota Fiscal) -->
							
							</td>
							<td align="left" valign="top">
							
								&nbsp;
								
							</td>
						</tr>
						<!-- Fim: Item de menu (Nota Fiscal) -->
						
						<!-- Início: Item de menu (Info. Mercado) -->
						<tr>
							<td align="left" valign="top">
							
								<!-- Início: Botão de menu (Relatórios) -->
								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td id="infosItemA" width="12"  height="56" style="background-image: none;"></td>
										<td id="infosItemB" width="127" height="56" style="background-image: none;" valign="middle">
										
											<table border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td id="infosItemC" width="46" height="46" style="background-image: url('../images/menu_button_left_normal.gif');" align="center" valign="middle">
														<img src="../images/menu_icon_garantias.gif" border="0"/>
													</td>
													<td id="infosItemD" width="81" height="46" style="background-image: url('../images/menu_button_right_normal.gif');" align="left" valign="middle">
														<table border="0" cellpadding="0" cellspacing="0">
															<tr>
																<td width="5"></td>
																<td><a id="infosOption" href="JavaScript:openWindow('win_sg_info_mercado_list', 'Informativo', 'pageDispatch.jsp?param=/ym_sistema_garantia/InfoMercado.do?task=list');" class="menu_text"  onmouseout="window.status='';return true;" onmouseover="window.status='Informativo';return true;">Informativo</a></td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td></td>
										<td id="infosItemE" width="123" height="3" style="background-image: none;"></td>
									</tr>
								</table>
								<!-- Fim: Botão de menu (Info. Mercado) -->
							
							</td>
						</tr>
						<!-- Fim: Item de menu (Info. Mercado) -->
					
						<!-- Início: Item de menu (Relatórios) -->
						<tr>
							<td align="left" valign="top">
							
								<!-- Início: Botão de menu (Relatórios) -->
								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td id="relatoriosItemA" width="12"  height="56" style="background-image: none;"></td>
										<td id="relatoriosItemB" width="127" height="56" style="background-image: none;" valign="middle">
										
											<table border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td id="relatoriosItemC" width="46" height="46" style="background-image: url('../images/menu_button_left_normal.gif');" align="center" valign="middle">
														<img src="../images/relatorios.gif" border="0"/>
													</td>
													<td id="relatoriosItemD" width="81" height="46" style="background-image: url('../images/menu_button_right_normal.gif');" align="left" valign="middle">
														<table border="0" cellpadding="0" cellspacing="0">
															<tr>
																<td width="5"></td>
																<td><a id="relatoriosOption" href="JavaScript:openWindow('win_sg_relatorio_list', 'Relatórios', 'pageDispatch.jsp?param=/ym_sistema_garantia/Relatorio.do?task=showList');" class="menu_text" onmouseout="window.status='';return true;" onmouseover="window.status='Relatórios';return true;">Diversos</a></td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td></td>
										<td id="relatoriosItemE" width="123" height="3" style="background-image: none;"></td>
									</tr>
								</table>
								<!-- Fim: Botão de menu (Relatórios) -->
							
							</td>
						</tr>
						<!-- Fim: Item de menu (Relatórios) -->
						
						<!-- Início: Item de menu (Relatório RMPS) -->
						<tr>
							<td align="left" valign="top">
							
								<!-- Início: Botão de menu ((Relatório RMPS) -->
								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td id="estItemA" width="12"  height="56" style="background-image: none;"></td>
										<td id="estItemB" width="127" height="56" style="background-image: none;" valign="middle">
										
											<table border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td id="estItemC" width="46" height="46" style="background-image: url('../images/menu_button_left_normal.gif');" align="center" valign="middle">
														<img src="../images/icon_c.gif" border="0" alt="R.M.P.S"/>
													</td>
													<td id="estItemD" width="81" height="46" style="background-image: url('../images/menu_button_right_normal.gif');" align="left" valign="middle">
														<table border="0" cellpadding="0" cellspacing="0">
															<tr>
																<td width="5"></td>
																<td><a id="estOption" href="JavaScript:openWindow('win_sg_rmps_rel', 'R.M.P.S', '/ym_sistema_garantia/RelatorioRmps.do');" class="menu_text">R.M.P.S Motocicleta</a></td> 
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td></td>
										<td id="estItemE" width="123" height="3" style="background-image: none;"></td>
									</tr>
								</table>
								</td>
						</tr>
						<!-- Fim: Item de menu (RMPS) -->
						
						<!-- Início: Item de menu (Relatório YMAN) -->
						
						<tr>
							<td align="left" valign="top">
							
								<!-- Início: Botão de menu ((Relatório YMAN) -->
								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td id="estItemA" width="12"  height="56" style="background-image: none;"></td>
										<td id="estItemB" width="127" height="56" style="background-image: none;" valign="middle">
										
											<table border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td id="estItemC" width="46" height="46" style="background-image: url('../images/menu_button_left_normal.gif');" align="center" valign="middle">
														<img src="../images/icon_c.gif" border="0" alt="YMAN"/>
													</td>
													<td id="estItemD" width="81" height="46" style="background-image: url('../images/menu_button_right_normal.gif');" align="left" valign="middle">
														<table border="0" cellpadding="0" cellspacing="0">
															<tr>
																<td width="5"></td>
																<td><a id="estOption" href="JavaScript:openWindow('win_sg_yman_rel', 'YMAN', '/ym_sistema_garantia/RelatorioYman.do');" class="menu_text">YMAN Náutica</a></td> 
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td></td>
										<td id="estItemE" width="123" height="3" style="background-image: none;"></td>
									</tr>
								</table>
								</td>
						</tr>
						 
						<!-- Fim: Item de menu (YMAN) -->
						<%--
						<!-- Início: Item de menu (Download) -->
						<tr>
							<td align="left" valign="top">
							
								<!-- Início: Botão de menu ((Nota Fiscal) -->
								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td id="estItemA" width="12"  height="56" style="background-image: none;"></td>
										<td id="estItemB" width="127" height="56" style="background-image: none;" valign="middle">
										
											<table border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td id="estItemC" width="46" height="46" style="background-image: url('../images/menu_button_left_normal.gif');" align="center" valign="middle">
														<img src="../images/icon_c.gif" border="0" alt="Estoque"/>
													</td>
													<td id="estItemD" width="81" height="46" style="background-image: url('../images/menu_button_right_normal.gif');" align="left" valign="middle">
														<table border="0" cellpadding="0" cellspacing="0">
															<tr>
																<td width="5"></td>
																<td><a id="estOption" href="JavaScript:openWindow('win_sg_upload_list', 'Upload de Arquivos', 'pageDispatch.jsp?param=/ym_sistema_garantia/Documento.do?task=list');" class="menu_text">Documento</a></td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td></td>
										<td id="estItemE" width="123" height="3" style="background-image: none;"></td>
									</tr>
								</table>
								</td>
						</tr>
						<!-- Fim: Item de menu (Download) -->
						--%>
						<!-- Início: Item de menu (Fechar janelas) -->
						<tr>
							<td align="left" valign="top">
							
								<!-- Início: Botão de menu ((Nota Fiscal) -->
								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td id="fecharItemA" width="12"  height="56" style="background-image: none;"></td>
										<td id="fecharItemB" width="127" height="56" style="background-image: none;" valign="middle">
										
											<table border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td id="fecharItemC" width="46" height="46" style="background-image: url('../images/menu_button_left_normal.gif');" align="center" valign="middle">
														<img src="../images/menu_icon_sair.gif" border="0" alt="Fechar Janela"/>
													</td>
													<td id="fecharItemD" width="81" height="46" style="background-image: url('../images/menu_button_right_normal.gif');" align="left" valign="middle">
														<table border="0" cellpadding="0" cellspacing="0">
															<tr>
																<td width="5"></td>
																<td><a id="fecharOption" href="JavaScript:close();" class="menu_text">Fechar</a></td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td></td>
										<td id="fecharItemE" width="123" height="3" style="background-image: none;"></td>
									</tr>
								</table>
								<!-- Fim: Botão de menu (Fechar janelas) -->
							
							</td>
							<td align="left" valign="top">&nbsp;
							<td>
						
					</table>
				
				</td>
			</tr>
			<!-- Fim: Body -->
			
		</table>
		<!-- Fim: Tabela de alinhamento -->

	</body>
</html:html>
/* Resource Tecnologia
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......ReportQueryGraphicHelper.java
 *   .: Criação.....22 de Outubro, 17:07
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "ReportQueryHelper".
 */

package br.com.yamaha.sistemagarantia.controller.helper;

/** Classe que contém as querys de relatórios
 * 
 * @author Edson Luiz Sumensari
 *
 */
public class ReportQueryHelper {
	
	/** Constante com a query do relatório de gerenciamento domês */
	private static final String QUERY_GERENCIAMENTO_MES =
		" SELECT modelo															" + // 0
		"       , SUM(qtdFat) qtdFat											" + // 1
		"       , org															" + // 2
		"       , SUM(estoque) estoque											" +	// 3	
		"       , SUM(qtdR1)  qtdR1												" + // 4
		"       , SUM(qtdR2)  qtdR2												" + // 5
		"       , SUM(vlR2)   vlR2												" + // 6
		"       , SUM(qtdR3)  qtdR3												" + // 7
		"       , SUM(vlR3)   vlR3												" + // 8
		"       , SUM(qtdR4)  qtdR4												" + // 9
		"       , SUM(qtdR5)  qtdR5												" + // 10 
		"       , SUM(qtdR6)  qtdR6												" + // 11 
		"       , SUM(qtdR7)  qtdR7												" + // 12 
		"       , SUM(qtdR8)  qtdR8												" + // 13 
		"       , SUM(qtdR9)  qtdR9												" + // 14 
		"       , SUM(qtdR10) qtdR10											" + // 15 
		"       , SUM(qtdSG)  qtdSG												" + // 16 
		"       , SUM(vlSG)   vlSG												" + // 17
		"       , SUM(qtdSGRec)  qtdSGRec										" + // 18 
		"       , SUM(vlSGRec)   vlSGRec										" + // 19 
		"       , SUM(qtdRevRec) qtdRevRec										" + // 20 
		"       , SUM(vlRevRec) vlRevRec										" + // 21
		" FROM ( 																" + 
     // Quantidade de Faturamento para a Concessionária no período */
		"		SELECT substr(ft.chassi,1,instr(ft.chassi,'-')-1) modelo				" +
		"             , COUNT(ft.faturamento_id) qtdFat, ft.organization_id org			" +
		" 			  , 0 estoque  														" +
		"             , 0 qtdR1, 0 qtdR2, 0 vlR2, 0 qtdR3, 0 vlR3, 0 qtdR4				" +
		"			  , 0 qtdR5, 0 qtdR6, 0 qtdR7, 0 qtdR8, 0 qtdR9, 0 qtdR10			" +
		"             , 0 qtdSG, 0 vlSG, 0 qtdSGRec, 0 vlSGRec, 0 qtdRevRec, 0 vlRevRec " +
		"		FROM ym_sg_faturamento ft												" +
		"      	WHERE ft.concessionaria_id = :p_concessionaria							" +
		"       AND   ft.linha_id          = nvl(:p_linha, ft.linha_id)					" +
		"		AND   nvl(ft.end_date, sysdate + 1) >= sysdate 							" +
		"		AND   ft.data_nf BETWEEN :p_dataInicioAp AND :p_dataFimAp				" +
		"		GROUP BY substr(ft.chassi,1,instr(ft.chassi,'-')-1), ft.organization_id " +
		"		UNION ALL																" +
      // Estoque da Concessionária 
        "		SELECT substr(ft.chassi,1,instr(ft.chassi,'-')-1) modelo				" +
        "             , 0 qtdFat														" +
        "			  , ft.organization_id org											" +
        "             , COUNT(*) estoque												" +
        "             , 0 qtdR1, 0 qtdR2, 0 vlR2, 0 qtdR3, 0 vlR3, 0 qtdR4, 0 qtdR5		" +
        "             , 0 qtdR6, 0 qtdR7, 0 qtdR8, 0 qtdR9, 0 qtdR10					" +
        "             , 0 qtdSG, 0 vlSG, 0 qtdSGRec, 0 vlSGRec, 0 qtdRevRec, 0 vlRevRec	" +
        "       FROM   ym_sg_estoque_chassi ec											" +
        "             , ym_sg_concessionaria cn											" +
        "             , ym_sg_faturamento ft											" +
        "       WHERE ec.concessionaria_id = cn.concessionaria_id						" +
        "       AND   ec.faturamento_id  = ft.faturamento_id							" +
        "       AND cn.concessionaria_id = :p_concessionaria							" +
        "		AND ft.linha_id 		 = nvl(:p_linha, ft.linha_id)	 				" +
        "       AND TRUNC(ec.dt_estoque) = TRUNC(SYSDATE - 1)							" +
        "       AND NVL(ec.end_date, SYSDATE + 1) >= SYSDATE							" +
        "       AND NVL(ft.end_date, SYSDATE + 1) >= SYSDATE							" +
        "       AND NVL(cn.end_date, SYSDATE + 1) >= SYSDATE							" +
        "       GROUP BY substr(ft.chassi,1,instr(ft.chassi,'-')-1), ft.organization_id	" +
		"       UNION ALL 																" +
      // Quantidade de revisões de Entrega - Revisão 1 */
		"		SELECT substr(cp.modelo,1,instr(cp.modelo,'-')-1) modelo				" +
		"             , 0 qtdFat, ft.organization_id org , 0 estoque					" +
		"             , COUNT(cp.cupom_id) qtdeR1										" +
		"             , 0 qtdR2, 0 vlR2, 0 qtdR3, 0 vlR3, 0 qtdR4, 0 qtdR5				" +
		"             , 0 qtdR6, 0 qtdR7, 0 qtdR8, 0 qtdR9, 0 qtdR10					" +
		"             , 0 qtdSG, 0 vlSG, 0 qtdSGRec, 0 vlSGRec, 0 qtdRevRec, 0 vlRevRec	" +
		"      FROM ym_sg_cupom cp, ym_sg_lote lt, ym_sg_revisao rv, ym_sg_faturamento ft " +
		"      WHERE lt.concessionaria_id = :p_concessionaria							" +
		"      AND   lt.lote_id           = cp.lote_id									" +
		"	   AND   replace(cp.modelo,'-','') = replace(ft.chassi,'-','')				" +
		"      AND   lt.linha_id          = nvl(:p_linha,lt.linha_id)					" +
		"      AND   cp.status_mov_id     in (2,19)										" +
		"      AND   cp.revisao_id        = rv.revisao_id								" +
		"      AND   rv.numero_revisao    = 1											" +
		"      AND   lt.liberacao_date BETWEEN :p_dataInicioAp AND :p_dataFimAp			" +
		"	   AND   NVL(cp.end_date, SYSDATE + 1) >= SYSDATE							" +
		"      AND   NVL(lt.end_date, SYSDATE + 1) >= SYSDATE						    " +
		"      AND   NVL(rv.end_date, SYSDATE + 1) >= SYSDATE				 			" +
		"      GROUP BY substr(cp.modelo,1,instr(cp.modelo,'-')-1), ft.organization_id	" +
		"      UNION ALL																" +
	  // Quantidade e valor das revisões 2 
		"	   SELECT substr(cp.modelo,1,instr(cp.modelo,'-')-1) modelo					" +
		"             , 0 qtdFat, ft.organization_id org , 0 estoque, 0 qtdR1			" +
		"             , COUNT(cp.cupom_id) qtdR2										" +
		"             , SUM(cp.vl_revisao + cp.vl_bonificacao) vlR2						" +
		"             , 0 qtdR3, 0 vlR3, 0 qtdR4, 0 qtdR5, 0 qtdR6						" +
		"             , 0 qtdR7, 0 qtdR8, 0 qtdR9, 0 qtdR10								" +
		"             , 0 qtdSG, 0 vlSG, 0 qtdSGRec, 0 vlSGRec, 0 qtdRevRec, 0 vlRevRec	" +
		"      FROM ym_sg_cupom cp, ym_sg_lote lt, ym_sg_revisao rv, ym_sg_faturamento ft " +
		"      WHERE lt.concessionaria_id = :p_concessionaria							" +
		"      AND   lt.lote_id           = cp.lote_id									" +
		"	   AND   replace(cp.modelo,'-','') = replace(ft.chassi,'-','')				" +
		"      AND   lt.linha_id          = nvl(:p_linha,lt.linha_id)					" +
		"      AND   cp.status_mov_id     in (2,19)										" +
		"      AND   cp.revisao_id  	  = rv.revisao_id								" +
		"      AND   rv.numero_revisao 	  = 2											" +
		"      AND   lt.liberacao_date between :p_dataInicioAp AND :p_dataFimAp			" +
		"	   AND   NVL(cp.end_date, SYSDATE + 1) >= SYSDATE							" +
		"      AND   NVL(lt.end_date, SYSDATE + 1) >= SYSDATE						    " +
		"      AND   NVL(rv.end_date, SYSDATE + 1) >= SYSDATE				 			" +
		"      GROUP BY substr(cp.modelo,1,instr(cp.modelo,'-')-1), ft.organization_id  " +
		"      UNION ALL 																" +
	 // Quantidade e valor das revisões 3 
		"	   SELECT substr(cp.modelo,1,instr(cp.modelo,'-')-1) modelo					" +
		"             , 0 qtdFat, ft.organization_id org , 0 estoque, 0 qtdR1, 0 qtdR2, 0 vlR2 " +
		"             , COUNT(cp.cupom_id) qtdR3										" +
		"             , SUM(cp.vl_revisao + cp.vl_bonificacao) vlR3						" +
		"             , 0 qtdR4, 0 qtdR5, 0 qtdR6, 0 qtdR7, 0 qtdR8, 0 qtdR9, 0 qtdR10	" +
		"             , 0 qtdSG, 0 vlSG, 0 qtdSGRec, 0 vlSGRec, 0 qtdRevRec, 0 vlRevRec	" +
		"      FROM ym_sg_cupom cp, ym_sg_lote lt, ym_sg_revisao rv, ym_sg_faturamento ft " +
		"      WHERE lt.concessionaria_id = :p_concessionaria							" +
		"      AND   lt.lote_id           = cp.lote_id									" +
		"	   AND   replace(cp.modelo,'-','') = replace(ft.chassi,'-','')				" +
		"      AND   cp.revisao_id        = rv.revisao_id								" +
		"      AND   lt.linha_id          = nvl(:p_linha,lt.linha_id)					" +
		"      AND   cp.status_mov_id     in (2,19)										" +
		"      AND   rv.numero_revisao    = 3											" +
		"      AND   lt.liberacao_date between :p_dataInicioAp AND :p_dataFimAp			" +
		"	   AND   NVL(cp.end_date, SYSDATE + 1) >= SYSDATE							" +
		"      AND   NVL(lt.end_date, SYSDATE + 1) >= SYSDATE						    " +
		"      AND   NVL(rv.end_date, SYSDATE + 1) >= SYSDATE				 			" +
		"      GROUP BY substr(cp.modelo,1,instr(cp.modelo,'-')-1),ft.organization_id	" +
		"      UNION ALL																" +
      // Quantidade de revisões 4                    
		"	   SELECT substr(cp.modelo,1,instr(cp.modelo,'-')-1) modelo					" +
		"             , 0 qtdFat, ft.organization_id org, 0 estoque, 0 qtdR1, 0 qtdR2	" +
		"			  , 0 vlR2, 0 qtdR3, 0 vlR3											" +
		"             , COUNT(cp.cupom_id) qtdR4										" +
		"             , 0 qtdR5, 0 qtdR6, 0 qtdR7, 0 qtdR8, 0 qtdR9, 0 qtdR10			" +
		"             , 0 qtdSG, 0 vlSG, 0 qtdSGRec, 0 vlSGRec, 0 qtdRevRec, 0 vlRevRec	" +
		"      FROM ym_sg_cupom cp, ym_sg_lote lt, ym_sg_revisao rv, ym_sg_faturamento ft " +
		"      WHERE lt.concessionaria_id = :p_concessionaria							" +
		"      AND   lt.lote_id           = cp.lote_id									" +
		"	   AND   replace(cp.modelo,'-','') = replace(ft.chassi,'-','')				" +
		"      AND   cp.revisao_id        = rv.revisao_id								" +
		"      AND   lt.linha_id          = nvl(:p_linha,lt.linha_id)					" +
		"      AND   cp.status_mov_id   in (2,19)										" +
		"      AND   rv.numero_revisao    = 4											" +
		"      AND   lt.liberacao_date between :p_dataInicioAp AND :p_dataFimAp			" +
		"	   AND   NVL(cp.end_date, SYSDATE + 1) >= SYSDATE							" +
		"      AND   NVL(lt.end_date, SYSDATE + 1) >= SYSDATE						    " +
		"      AND   NVL(rv.end_date, SYSDATE + 1) >= SYSDATE				 			" +
		"      GROUP BY substr(cp.modelo,1,instr(cp.modelo,'-')-1),ft.organization_id   " +
		"      UNION ALL																" +
      // Quantidade de revisões 5 
		"	   SELECT substr(cp.modelo,1,instr(cp.modelo,'-')-1) modelo					" +
		"             , 0 qtdFat, ft.organization_id org, 0 estoque, 0 qtdR1, 0 qtdR2	" +
		"			  , 0 vlR2, 0 qtdR3, 0 vlR3, 0 qtdR4								" +
		"             , COUNT(cp.cupom_id) qtdR5										" +
		"             , 0 qtdR6, 0 qtdR7, 0 qtdR8, 0 qtdR9, 0 qtdR10					" +
		"             , 0 qtdSG, 0 vlSG, 0 qtdSGRec, 0 vlSGRec, 0 qtdRevRec, 0 vlRevRec	" +
		"      FROM ym_sg_cupom cp, ym_sg_lote lt, ym_sg_revisao rv, ym_sg_faturamento ft " +
		"      WHERE lt.concessionaria_id = :p_concessionaria							" +
		"      AND   lt.lote_id           = cp.lote_id									" +
		"      AND   cp.revisao_id        = rv.revisao_id								" +
		"	   AND   replace(cp.modelo,'-','') = replace(ft.chassi,'-','')				" +
		"      AND   lt.linha_id          = nvl(:p_linha,lt.linha_id)					" +
		"      AND   cp.status_mov_id   in (2,19)										" +
		"      AND   rv.numero_revisao    = 5											" +
		"      AND   lt.liberacao_date between :p_dataInicioAp AND :p_dataFimAp			" +
		"	   AND   NVL(cp.end_date, SYSDATE + 1) >= SYSDATE							" +
		"      AND   NVL(lt.end_date, SYSDATE + 1) >= SYSDATE						    " +
		"      AND   NVL(rv.end_date, SYSDATE + 1) >= SYSDATE				 			" +
		"      GROUP BY substr(cp.modelo,1,instr(cp.modelo,'-')-1),ft.organization_id   " +
		"	   UNION ALL																" +
	// Quantidade de revisões 6 
		"		SELECT substr(cp.modelo,1,instr(cp.modelo,'-')-1) modelo				" +
		"             , 0 qtdFat, ft.organization_id org, 0 estoque, 0 qtdR1, 0 qtdR2	" +
		"			  , 0 vlR2, 0 qtdR3, 0 vlR3											" +
		"			  , 0 qtdR4, 0 qtdR5												" +
		"             , COUNT(cp.cupom_id) qtdR6										" +
		"             , 0 qtdR7, 0 qtdR8, 0 qtdR9, 0 qtdR10								" +
		"             , 0 qtdSG, 0 vlSG, 0 qtdSGRec, 0 vlSGRec							" +
		"             , 0 qtdRevRec, 0 vlRevRec											" +
		"      FROM ym_sg_cupom cp, ym_sg_lote lt, ym_sg_revisao rv, ym_sg_faturamento ft " +
		"      WHERE lt.concessionaria_id 	= :p_concessionaria							" +
		"      AND lt.lote_id 				= cp.lote_id								" +
		"      AND cp.revisao_id  			= rv.revisao_id								" +
		"	   AND   replace(cp.modelo,'-','') = replace(ft.chassi,'-','')				" +
		"      AND lt.linha_id 				= nvl(:p_linha,lt.linha_id)					" +
		"      AND cp.status_mov_id 		in (2,19)									" +
		"      AND rv.numero_revisao 		= 6											" +
		"      AND lt.liberacao_date between :p_dataInicioAp AND :p_dataFimAp			" +
		"	   AND   NVL(cp.end_date, SYSDATE + 1) >= SYSDATE							" +
		"      AND   NVL(lt.end_date, SYSDATE + 1) >= SYSDATE						    " +
		"      AND   NVL(rv.end_date, SYSDATE + 1) >= SYSDATE				 			" +
		"      GROUP BY substr(cp.modelo,1,instr(cp.modelo,'-')-1),ft.organization_id   " +
		"      UNION ALL																" +
	// Quantidade de revisões 7 
		"	   SELECT substr(cp.modelo,1,instr(cp.modelo,'-')-1) modelo					" +
		"             , 0 qtdFat, ft.organization_id org, 0 estoque, 0 qtdR1, 0 qtdR2	" +
		"			  , 0 vlR2, 0 qtdR3													" +
		"             , 0 vlR3, 0 qtdR4, 0 qtdR5, 0 qtdR6								" +
		"             , COUNT(cp.cupom_id) qtdR7										" +
		"             , 0 qtdR8, 0 qtdR9, 0 qtdR10										" +
		"             , 0 qtdSG, 0 vlSG, 0 qtdSGRec, 0 vlSGRec, 0 qtdRevRec, 0 vlRevRec	" +
		"      FROM ym_sg_cupom cp, ym_sg_lote lt, ym_sg_revisao rv, ym_sg_faturamento ft " +
		"      WHERE lt.concessionaria_id 	= :p_concessionaria							" +
		"      AND lt.lote_id 				= cp.lote_id								" +
		"      AND cp.revisao_id  			= rv.revisao_id								" +
		"	   AND   replace(cp.modelo,'-','') = replace(ft.chassi,'-','')				" +
		"      AND lt.linha_id 				= nvl(:p_linha,lt.linha_id)					" +
		"      AND cp.status_mov_id 		in (2,19)									" +
		"      AND rv.numero_revisao 		= 7											" +
		"      AND lt.liberacao_date between :p_dataInicioAp AND :p_dataFimAp			" +
		"	   AND   NVL(cp.end_date, SYSDATE + 1) >= SYSDATE							" +
		"      AND   NVL(lt.end_date, SYSDATE + 1) >= SYSDATE						    " +
		"      AND   NVL(rv.end_date, SYSDATE + 1) >= SYSDATE				 			" +
		"      GROUP BY substr(cp.modelo,1,instr(cp.modelo,'-')-1),ft.organization_id   " +
		"      UNION ALL																" +
    // Quantidade de revisões 8 
		"	   SELECT substr(cp.modelo,1,instr(cp.modelo,'-')-1) modelo					" +
		"             , 0 qtdFat,ft.organization_id org, 0 estoque, 0 qtdR1, 0 qtdR2	" +
		"			  , 0 vlR2, 0 qtdR3, 0 vlR3											" +
		"             , 0 qtdR4, 0 qtdR5, 0 qtdR6, 0 qtdR7								" +
		"             , COUNT(cp.cupom_id) qtdR8										" +
		"             , 0 qtdR9, 0 qtdR10												" +
		"             , 0 qtdSG, 0 vlSG, 0 qtdSGRec, 0 vlSGRec, 0 qtdRevRec, 0 vlRevRec	" +
		"      FROM ym_sg_cupom cp, ym_sg_lote lt, ym_sg_revisao rv, ym_sg_faturamento ft " +	
		"      WHERE lt.concessionaria_id 	= :p_concessionaria							" +
		"      AND lt.lote_id 				= cp.lote_id								" +
		"      AND cp.revisao_id  			= rv.revisao_id								" +
		"	   AND   replace(cp.modelo,'-','') = replace(ft.chassi,'-','')				" +
		"      AND lt.linha_id 				= nvl(:p_linha,lt.linha_id)					" +
		"      AND cp.status_mov_id 		in (2,19)									" +
		"      AND rv.numero_revisao 		= 8											" +
		"      AND lt.liberacao_date between :p_dataInicioAp AND :p_dataFimAp			" +
		"	   AND   NVL(cp.end_date, SYSDATE + 1) >= SYSDATE							" +
		"      AND   NVL(lt.end_date, SYSDATE + 1) >= SYSDATE						    " +
		"      AND   NVL(rv.end_date, SYSDATE + 1) >= SYSDATE				 			" +
		"      GROUP BY substr(cp.modelo,1,instr(cp.modelo,'-')-1),ft.organization_id   " +
		"      UNION ALL																" +
    //Quantidade de revisões 9 
		"	   SELECT substr(cp.modelo,1,instr(cp.modelo,'-')-1) modelo					" +
		"             , 0 qtdFat,ft.organization_id org, 0 estoque, 0 qtdR1, 0 qtdR2	" +
		"			  , 0 vlR2, 0 qtdR3, 0 vlR3											" +
		"             , 0 qtdR4, 0 qtdR5, 0 qtdR6, 0 qtdR7, 0 qtdR8						" +
		"             , COUNT(cp.cupom_id) qtdR9, 0 qtdR10, 0 qtdSG						" +
		"			  , 0 vlSG, 0 qtdSGRec, 0 vlSGRec, 0 qtdRevRec, 0 vlRevRec			" +
		"      FROM ym_sg_cupom cp, ym_sg_lote lt, ym_sg_revisao rv	, ym_sg_faturamento ft " +	
		"      WHERE lt.concessionaria_id 	= :p_concessionaria							" +
		"      AND lt.lote_id 				= cp.lote_id								" +
		"	   AND   replace(cp.modelo,'-','') = replace(ft.chassi,'-','')				" +
		"      AND lt.linha_id 				= nvl(:p_linha,lt.linha_id)					" +
		"      AND cp.status_mov_id 		in (2,19)									" +
		"      AND cp.revisao_id  			= rv.revisao_id								" +
		"      AND rv.numero_revisao 		= 9											" +
		"      AND lt.liberacao_date between :p_dataInicioAp AND :p_dataFimAp			" +
		"	   AND   NVL(cp.end_date, SYSDATE + 1) >= SYSDATE							" +
		"      AND   NVL(lt.end_date, SYSDATE + 1) >= SYSDATE						    " +
		"      AND   NVL(rv.end_date, SYSDATE + 1) >= SYSDATE				 			" +
		"      GROUP BY substr(cp.modelo,1,instr(cp.modelo,'-')-1),ft.organization_id   " +
		"      UNION ALL" +
    //Quantidade de revisões 10 
		"	   SELECT substr(cp.modelo,1,instr(cp.modelo,'-')-1) modelo					" +
		"             , 0 qtdFat,ft.organization_id org, 0 estoque, 0 qtdR1, 0 qtdR2	" +
		"			  , 0 vlR2, 0 qtdR3, 0 vlR3, 0 qtdR4								" +
		"             , 0 qtdR5, 0 qtdR6, 0 qtdR7, 0 qtdR8, 0 qtdR9						" +
		"             , COUNT(cp.cupom_id) qtdR10										" +
		"             , 0 qtdSG, 0 vlSG, 0 qtdSGRec, 0 vlSGRec, 0 qtdRevRec, 0 vlRevRec	" +
		"      FROM ym_sg_cupom cp, ym_sg_lote lt, ym_sg_revisao rv, ym_sg_faturamento ft " +	
		"      WHERE lt.concessionaria_id 		= :p_concessionaria						" +
		"      AND lt.lote_id 					= cp.lote_id							" +
		"	   AND   replace(cp.modelo,'-','') = replace(ft.chassi,'-','')				" +
		"      AND lt.linha_id 					= nvl(:p_linha,lt.linha_id)				" +
		"      AND cp.status_mov_id 			in (2,19)								" +
		"      AND cp.revisao_id  				= rv.revisao_id							" +
		"      AND rv.numero_revisao 			= 10									" +
		"      AND lt.liberacao_date between :p_dataInicioAp AND :p_dataFimAp			" +
		"	   AND   NVL(cp.end_date, SYSDATE + 1) >= SYSDATE							" +
		"      AND   NVL(lt.end_date, SYSDATE + 1) >= SYSDATE						    " +
		"      AND   NVL(rv.end_date, SYSDATE + 1) >= SYSDATE				 			" +
		"      GROUP BY substr(cp.modelo,1,instr(cp.modelo,'-')-1),ft.organization_id   " +
		"      UNION ALL																" +
	//Quantidade de Solicitação de Garantia no Período 
		"	   SELECT substr(gh.modelo,1,instr(gh.modelo,'-')-1) modelo					" +
		"             , 0 qtdFat,ft.organization_id org, 0 estoque, 0 qtdR1, 0 qtdR2	" +
		"			  , 0 vlR2, 0 qtdR3, 0 vlR3, 0 qtdR4								" +
		"             , 0 qtdR5, 0 qtdR6, 0 qtdR7, 0 qtdR8, 0 qtdR9,  0 qtdR10			" +
		"             , count(gh.garantia_id) qtdeSG									" +
		"             , 0 vlSG, 0 qtdSGRec, 0 vlSGRec, 0 qtdRevRec, 0 vlRevRec			" +
		"      FROM   ym_sg_garantia_header gh											" +
		"             , ym_sg_lote lt													" +
		"			  , ym_sg_faturamento ft											" +
		"      WHERE lt.concessionaria_id 	= :p_concessionaria							" +
		"      AND   lt.lote_id 			= gh.lote_id								" +
		"	   AND   replace(gh.modelo,'-','') = replace(ft.chassi,'-','')				" +
		"      AND   lt.linha_id 			= nvl(:p_linha,lt.linha_id)					" +
		"      AND   gh.status_mov_id 		in (2,19)									" +
		"      AND   lt.liberacao_date between :p_dataInicioAp AND :p_dataFimAp			" +
		"	   AND   NVL(gh.end_date, SYSDATE + 1) >= SYSDATE							" +
		"      AND   NVL(lt.end_date, SYSDATE + 1) >= SYSDATE						    " +
		"      GROUP BY substr(gh.modelo,1,instr(gh.modelo,'-')-1),ft.organization_id	" +
		"      UNION ALL 																" +
    // Valor das Solicitações de Garantia no período 
		"	   SELECT modelo" +
		"             , 0 qtdFat, org, 0 estoque, 0 qtdR1, 0 qtdR2, 0 vlR2, 0 qtdR3		" +
		"			  , 0 vlR3, 0 qtdR4													" +
		"             , 0 qtdR5, 0 qtdR6, 0 qtdR7, 0 qtdR8, 0 qtdR9,  0 qtdR10			" +
		"             , 0 qtdSG, sum(vlTerc + vlServico + vlPeca)  vlSG					" +
		"             , 0 qtdSGRec, 0 vlSGRec, 0 qtdRevRec, 0 vlRevRec					" +
		"      FROM (																	" +
		       // Valor de Serviço de Terceiro 
        "   		SELECT substr(gh.modelo,1,instr(gh.modelo,'-')-1) modelo			" +
        "				   , ft.organization_id org										" +
        "                  , 0  vlServico												" +
        "                  , sum(gh.vl_servico_terceiro) vlTerc							" +
        "                  , 0 vlPeca													" +
        "           FROM																" +
        "                  ym_sg_garantia_header gh										" +
        "                  , ym_sg_lote lt												" +
        "				   , ym_sg_faturamento ft										" +
        "           WHERE lt.lote_id 			= gh.lote_id							" +
        "           AND   lt.linha_id 			= nvl(:p_linha,lt.linha_id)				" +
        "			AND   replace(gh.modelo,'-','') = replace(ft.chassi,'-','')			" +
        "           AND   lt.concessionaria_id 	= :p_concessionaria						" +
        "           AND   gh.status_mov_id 		in (2,19)								" +
        "           AND   lt.liberacao_date between :p_dataInicioAp AND :p_dataFimAp    " +
        "	   		AND   NVL(gh.end_date, SYSDATE + 1) >= SYSDATE						" +
		"      		AND   NVL(lt.end_date, SYSDATE + 1) >= SYSDATE					    " +
        "           GROUP BY substr(gh.modelo,1,instr(gh.modelo,'-')-1),ft.organization_id " +
        "           UNION ALL															" +
        			// Valor de Serviço 
        "   		SELECT substr(gh.modelo,1,instr(gh.modelo,'-')-1) modelo			" +
        "				   , ft.organization_id org 								    " +
        "                  , sum(sg.vl_servico) vlServico    							" +
        "                  , 0 vlTerc, 0 vlPeca         								" +
        "           FROM																" +
        "                  ym_sg_garantia_header gh										" +
        "                  , ym_sg_lote lt												" +
        "                  , ym_sg_servico_grupo sg										" +
        "				   , ym_sg_faturamento ft										" +
        "           WHERE gh.garantia_id 		= sg.garantia_id						" +
        "           AND   lt.linha_id 			= nvl(:p_linha,lt.linha_id)				" +
        "			AND   replace(gh.modelo,'-','') = replace(ft.chassi,'-','')         " +
        "           AND   lt.lote_id 			= gh.lote_id							" +
        "           AND   lt.concessionaria_id 	= :p_concessionaria						" +
        "           AND   gh.status_mov_id 		in (2,19)								" +
        "           AND   lt.liberacao_date between :p_dataInicioAp AND :p_dataFimAp	" +
        "	   		AND   NVL(gh.end_date, SYSDATE + 1) >= SYSDATE						" +
		"      		AND   NVL(lt.end_date, SYSDATE + 1) >= SYSDATE					    " +
		"	   		AND   NVL(sg.end_date, SYSDATE + 1) >= SYSDATE						" +
        "           GROUP BY substr(gh.modelo,1,instr(gh.modelo,'-')-1),ft.organization_id " +
        "           UNION ALL															" +
               		//Valor de Peça 
        "   		SELECT substr(gh.modelo,1,instr(gh.modelo,'-')-1) modelo			" +
        "				   , ft.organization_id org 									" +		
        "                  , 0 vlServico, 0 vlTerc										" +
        "                  , sum(gl.quantidade * gl.vl_peca_garantia) vlPeca            " +
        "           FROM																" +
        "                  ym_sg_garantia_header gh										" +
        "                  , ym_sg_garantia_line gl                						" +
        "                  , ym_sg_lote lt												" +
        "				   , ym_sg_faturamento ft 										" +
        "           WHERE lt.lote_id 			= gh.lote_id							" +
        "           AND lt.linha_id 			= nvl(:p_linha,lt.linha_id)				" +
        "			AND   replace(gh.modelo,'-','') = replace(ft.chassi,'-','')			" +
        "           AND lt.concessionaria_id 	= :p_concessionaria						" +
        "           AND gh.garantia_id 			= gl.garantia_id						" +
        "           AND gh.status_mov_id 		in (2,19)								" +
        "           AND lt.liberacao_date between :p_dataInicioAp AND :p_dataFimAp		" +
        "	   		AND   NVL(gh.end_date, SYSDATE + 1) >= SYSDATE						" +
		"      		AND   NVL(lt.end_date, SYSDATE + 1) >= SYSDATE					    " +
		"	   		AND   NVL(gl.end_date, SYSDATE + 1) >= SYSDATE						" +
        "           GROUP BY  substr(gh.modelo,1,instr(gh.modelo,'-')-1),ft.organization_id " +
        "      )																		" +
        "      GROUP BY modelo, org   														" +
        "      UNION ALL																" +
            	// Quantidade de Solicitação de Garantia no Período recusadas 
        "	   SELECT substr(gh.modelo,1,instr(gh.modelo,'-')-1) modelo					" +
        "             , 0 qtdFat, ft.organization_id org, 0 estoque, 0 qtdR1, 0 qtdR2	" +
        "			  , 0 vlR2, 0 qtdR3, 0 vlR3, 0 qtdR4								" +
        "             , 0 qtdR5, 0 qtdR6, 0 qtdR7, 0 qtdR8, 0 qtdR9,  0 qtdR10			" +
        "             , 0 qtdeSG, 0 vlSG, count(gh.garantia_id) qtdSGRec				" +
        "             , 0 vlSGRec, 0 qtdRevRec, 0 vlRevRec								" +
        "      FROM   ym_sg_garantia_header gh											" +
        "             , ym_sg_lote lt													" +
        "			  , ym_sg_faturamento ft											" +
        "      WHERE lt.concessionaria_id 	= :p_concessionaria							" +
        "      AND   lt.lote_id 			= gh.lote_id								" +
        "	   AND   replace(gh.modelo,'-','') = replace(ft.chassi,'-','') 				" +
        "      AND   lt.linha_id 			= nvl(:p_linha,lt.linha_id)					" +
        "      AND   gh.status_mov_id 		= 6											" +
        "      AND   lt.liberacao_date between :p_dataInicioAp AND :p_dataFimAp			" +
        "	   AND   NVL(gh.end_date, SYSDATE + 1) >= SYSDATE							" +
		"      AND   NVL(lt.end_date, SYSDATE + 1) >= SYSDATE						    " +
        "      GROUP BY substr(gh.modelo,1,instr(gh.modelo,'-')-1),ft.organization_id	" +
        "      UNION ALL																" +
     // Valor das Solicitações de Garantia no período 
        "	   SELECT modelo															" +
        "             , 0 qtdFat, org, 0 estoque, 0 qtdR1, 0 qtdR2, 0 vlR2, 0 qtdR3		" +
        "			  , 0 vlR3, 0 qtdR4													" +
        "             , 0 qtdR5, 0 qtdR6, 0 qtdR7, 0 qtdR8, 0 qtdR9,  0 qtdR10			" +
        "             , 0 qtdSG, 0 vlSG, 0 qtSGRec										" +
        "             , sum(vlTercRec + vlServicoRec + vlPecaRec)  vlSGRec				" +
        "             , 0 qtdRevRec, 0 vlRevRec											" +
        "      FROM (																	" +
               // Valor de Serviço de Terceiro de SG recusada 
        "	   		SELECT 	substr(gh.modelo,1,instr(gh.modelo,'-')-1) modelo			" +
        "					, ft.organization_id org									" +
        "           	  	, 0  vlServicoRec											" +
        "             		, sum(gh.vl_servico_terceiro) vlTercRec						" +
        "             		, 0 vlPecaRec												" +
        "      		FROM																" +
        "             		ym_sg_garantia_header gh									" +
        "             		, ym_sg_lote lt												" +
        "					, ym_sg_faturamento ft										" +
        "           WHERE lt.lote_id 			= gh.lote_id							" +
        "			AND   replace(gh.modelo,'-','') = replace(ft.chassi,'-','')			" +
        "           AND   lt.linha_id 			= nvl(:p_linha,lt.linha_id)				" +
        "           AND   lt.concessionaria_id 	= :p_concessionaria						" +
        "           AND   gh.status_mov_id 		= 6										" +
        "           AND   lt.liberacao_date between :p_dataInicioAp AND :p_dataFimAp	" +
        "	   		AND   NVL(gh.end_date, SYSDATE + 1) >= SYSDATE						" +
		"      		AND   NVL(lt.end_date, SYSDATE + 1) >= SYSDATE					    " +
        "           GROUP BY substr(gh.modelo,1,instr(gh.modelo,'-')-1), ft.organization_id	" +
        "           UNION ALL															" +
              // Valor de Serviço de SG recusada 
        "   		SELECT substr(gh.modelo,1,instr(gh.modelo,'-')-1) modelo			" +
        "				   , ft.organization_id org										" +
        "                  , sum(sg.vl_servico) vlServicoRec    						" +
        "                  , 0 vlTercRec												" +
        "                  , 0 vlPecaRec         										" +
        "           FROM" +
        "                  ym_sg_garantia_header gh										" +
        "                  , ym_sg_lote lt												" +
        "                  , ym_sg_servico_grupo sg										" +
        "				   , ym_sg_faturamento ft										" +
        "           WHERE gh.garantia_id 		= sg.garantia_id						" +
        "           AND   lt.linha_id 			= nvl(:p_linha,lt.linha_id)				" +
        "			AND   replace(gh.modelo,'-','') = replace(ft.chassi,'-','')			" +
        "           AND   lt.lote_id 			= gh.lote_id							" +
        "           AND   lt.concessionaria_id 	= :p_concessionaria						" +
        "           AND   gh.status_mov_id 		= 6										" +
        "           AND   lt.liberacao_date between :p_dataInicioAp AND :p_dataFimAp	" +
        "	   		AND   NVL(gh.end_date, SYSDATE + 1) >= SYSDATE						" +
		"     		AND   NVL(lt.end_date, SYSDATE + 1) >= SYSDATE					    " +
        "           GROUP BY substr(gh.modelo,1,instr(gh.modelo,'-')-1), ft.organization_id	" +
        "           UNION ALL															" +
              // Valor de Peça da SG recusada
        "   		SELECT substr(gh.modelo,1,instr(gh.modelo,'-')-1) modelo			" +
        "				   , ft.organization_id org										" +
        "                  , 0 vlServicoRec, 0 vlTercRec								" +
        "                  , sum(gl.quantidade * gl.vl_peca_garantia) vlPecaRec         " +
        "           FROM																" +
        "                  ym_sg_garantia_header gh										" +
        "                  , ym_sg_garantia_line gl                						" +
        "                  , ym_sg_lote lt												" +
        "				   , ym_sg_faturamento ft										" +
        "           WHERE lt.lote_id 			= gh.lote_id							" +
        "           AND lt.linha_id 			= nvl(:p_linha,lt.linha_id)				" +
        "			AND replace(gh.modelo,'-','') = replace(ft.chassi,'-','')			" +
        "           AND lt.concessionaria_id 	= :p_concessionaria						" +
        "           AND gh.garantia_id 			= gl.garantia_id						" +
        "           AND gh.status_mov_id 		= 6										" +
        "           AND lt.liberacao_date between :p_dataInicioAp AND :p_dataFimAp		" +
        "	   		AND   NVL(gh.end_date, SYSDATE + 1) >= SYSDATE						" +
		"      		AND   NVL(lt.end_date, SYSDATE + 1) >= SYSDATE					    " +
		"      		AND   NVL(gl.end_date, SYSDATE + 1) >= SYSDATE					    " +
        "           GROUP BY  substr(gh.modelo,1,instr(gh.modelo,'-')-1), ft.organization_id " +
        "      )																		" +
        "      GROUP BY modelo, org														" +
        "      UNION ALL																" +
        //Quantidade e valor das revisões 2 
        "		SELECT substr(cp.modelo,1,instr(cp.modelo,'-')-1) modelo				" +
        "             , 0 qtdFat,ft.organization_id org, 0 estoque, 0 qtdR1, 0 qtdR2	" +
        "			  , 0 vlR2															" +
        "             , 0 qtdR3, 0 vlR3, 0 qtdR4, 0 qtdR5, 0 qtdR6						" +
        "             , 0 qtdR7, 0 qtdR8, 0 qtdR9, 0 qtdR10								" +
        "             , 0 qtdSG, 0 vlSG, 0 qtdSGRec, 0 vlSGRec							" +
        "             , COUNT(cp.cupom_id) qtdRevRec									" +
        "             , SUM(cp.vl_revisao + cp.vl_bonificacao) vlRevRec					" +
        "      FROM ym_sg_cupom cp, ym_sg_lote lt, ym_sg_faturamento ft					" +
        "      WHERE lt.concessionaria_id 		= :p_concessionaria						" +
        "      AND lt.lote_id 					= cp.lote_id							" +
        "	   AND replace(cp.modelo,'-','')    = replace(ft.chassi,'-','')				" +
        "      AND lt.linha_id 					= nvl(:p_linha,lt.linha_id)				" +
        "      AND cp.status_mov_id 			= 6										" +
        "      AND lt.liberacao_date between :p_dataInicioAp AND :p_dataFimAp			" +
        "      AND   NVL(lt.end_date, SYSDATE + 1) >= SYSDATE						    " +
        "      GROUP BY substr(cp.modelo,1,instr(cp.modelo,'-')-1), ft.organization_id  " +
        " )																				" +
        " GROUP BY modelo, org															" +
        " ORDER BY org, modelo															";
	
	/** Constante com a query do relatório de gerenciamento do mês 
	 * 
	 *  Parâmetros utilizados nesta query
	 *    p_dataInicioAp - data de início da apuração 
	 * 	  p_dataFimAp    - data final da apuração
	 * 	  p_linha		 - id da linha do produto
	 *    p_concessionaria - id da Concessionária
	 */
	private static final String QUERY_MEDIA_ABERTURA_FECHAMENTO_LOTE =
		" SELECT AVG(maximo-minimo) 												" +
		" FROM (																	" +
		"		SELECT lt.lote_id													" +
		"			   , (															" +
		"					SELECT MIN(TRUNC(lg.created_date)) minimo 				" +
		"					FROM   ym_sg_lote_log lg 								" +
		"					WHERE lg.lote_id         = lt.lote_id					" +
		"    				AND   lg.status_lote_id  = 1							" +
		"					AND   NVL(lg.end_date, SYSDATE + 1) >= SYSDATE			" +
		"				 ) minimo													" +
		"			   ,(															" +
		"					SELECT MAX(TRUNC(lg.created_date)) maximo				" +
		"				    FROM   ym_sg_lote_log lg								" +
		"				    WHERE  lg.lote_id          = lt.lote_id					" +
		"				    AND   lg.status_lote_id    IN (9,11) 					" +
		"					AND   NVL(lg.end_date, SYSDATE + 1) >= SYSDATE			" +
		"				) maximo													" +
		"		FROM  ym_Sg_lote lt													" +
		"		WHERE  lt.liberacao_date BETWEEN :p_dataInicioAp AND :p_dataFimAp	" +
		"		AND    lt.linha_id          = NVL(:p_linha,lt.linha_id)  			" +
		"		AND    lt.concessionaria_id = :p_concessionaria						" +
		"		AND   NVL(lt.end_date, SYSDATE + 1) >= SYSDATE						" +
		" ) arq																		" +
		" WHERE arq.minimo is not null 												" +
		" AND   arq.maximo is not null 												";
		
	/** Retorna query do relatório de Gerenciamento do Mês para recuperar a média de dias
	 *  entre a abertura e o fechamento dos lotes de um determinado período de uma concessionária
	 *  Parâmetros utlizados no relatório 
	 *  :p_linha -- Id da linha do produto
	 *  :p_concessionaria -- Id da concessionária
	 *  :p_dataInicioAp -- Data de Início para apuração dos dados
	 *  :p_dataFimAp    -- Data de Final para apuração dos dados
	 * 
	 * @return String 
	 */
	public static String getQueryMediaAberturaFechamentoLote() {		
		return QUERY_MEDIA_ABERTURA_FECHAMENTO_LOTE;
	}
	
	/** Retorna query do relatório de Gerenciamento do Mês
	 *  Parâmetros utlizados no relatório 
	 *  :p_orgId -- Id da empresa
	 *  :p_linha -- Id da linha do produto
	 *  :p_concessionaria -- Id da concessionária
	 *  :p_dataInicioAp -- Data de Início para apuração dos dados
	 *  :p_dataFimAp    -- Data de Final para apuração dos dados
	 * 
	 * @return String 
	 */
	public static String getQueryGerenciamentoMes() {		
		return QUERY_GERENCIAMENTO_MES;
	}
}

/* Resource Tecnologia
 * Copyright (c) 2008 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......ReportQueryGraphicHelper.java
 *   .: Criação.....09 de Março, 11:29
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Entidade "ReportQueryGraphicHelper".
 */

package br.com.yamaha.sistemagarantia.controller.helper;

/** Classe que contém as querys de relatórios gráficos
 * 
 * @author Edson Luiz Sumensari
 *
 */
public class ReportQueryGraphicHelper {
	
	/** Constante com a query do relatório service report parte 1, gráficos 1,2 e 3 */
	private static final String QUERY_SERVICE_REPORT_PART1 =
		" SELECT 	to_char(yl.fechamento_date, 'YYYY')	Ano " +
		" 			, '1'" +
		"			, 'Parts'" +
		"			, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),1,(gl.vl_peca_garantia*gl.quantidade),0))   m01" +
		"			, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),2,(gl.vl_peca_garantia*gl.quantidade),0))   m02" +
		"			, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),3,(gl.vl_peca_garantia*gl.quantidade),0))   m03" +
		"			, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),4,(gl.vl_peca_garantia*gl.quantidade),0))   m04" +
		"			, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),5,(gl.vl_peca_garantia*gl.quantidade),0))   m05" +
		"			, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),6,(gl.vl_peca_garantia*gl.quantidade),0))   m06" +
		"			, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),7,(gl.vl_peca_garantia*gl.quantidade),0))   m07" +
		"			, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),8,(gl.vl_peca_garantia*gl.quantidade),0))   m08" +
		"			, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),9,(gl.vl_peca_garantia*gl.quantidade),0))   m09" +
		"			, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),10,(gl.vl_peca_garantia*gl.quantidade),0))  m10" +
		"			, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),11,(gl.vl_peca_garantia*gl.quantidade),0))  m11" +
		"			, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),12,(gl.vl_peca_garantia*gl.quantidade),0))  m12" +
		" FROM 	ym_sg_lote       			yl" +
		"		, ym_sg_garantia_header		gh" +
		"		, ym_sg_garantia_line	  	gl" +
		"		, ym_sg_faturamento      	yf" +
		" WHERE yl.lote_id             = gh.lote_id" +
		" AND   gh.garantia_id         = gl.garantia_id" +
		" AND   replace(yf.chassi,'-','') = replace(gh.modelo,'-','')" +
		" AND   yf.organization_id     = nvl(:p_orgId, yf.organization_id)" +
		" AND   yl.status_lote_id      in (9,11)" +
		" AND   yl.linha_id            = 1" +
		" AND   gh.status_mov_id       =  2" +
		" AND   yl.fechamento_date between to_date(concat('01/01/', to_char(:p_dataAp,'YYYY')), 'DD/MM/YYYY') and :p_dataAp" +
		" AND   nvl(yl.end_date, sysdate + 1) >= sysdate " +
		" AND   nvl(gh.end_date, sysdate + 1) >= sysdate " +
		" AND   nvl(yf.end_date, sysdate + 1) >= sysdate " +
		" AND   nvl(gl.end_date, sysdate + 1) >= sysdate " +
		" GROUP BY   to_char(yl.fechamento_date, 'YYYY')" +
		" UNION ALL" +
		" SELECT 	ano" +
		"			, '2'" +
		"			, 'Labor'" +
		"			, sum(m01)" +
		"			, sum(m02)" +
		"			, sum(m03)" +
		"			, sum(m04)" +
		"			, sum(m05)" +
		"			, sum(m06)" +
		"			, sum(m07)" +
		"			, sum(m08)" +
		"			, sum(m09)" +
		"			, sum(m10)" +
		"			, sum(m11)" +
		"			, sum(m12)" +
		" FROM(" +
		"		SELECT 	to_char(yl.fechamento_date, 'YYYY')	Ano" +
		"				, '2'" +
		"				, 'Labor'" +
		"				, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),1,ys.vl_servico,0))   m01" +
		"				, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),2,ys.vl_servico,0))   m02" +
		"				, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),3,ys.vl_servico,0))   m03" +
		"				, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),4,ys.vl_servico,0))   m04" +
		"				, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),5,ys.vl_servico,0))   m05" +
		"				, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),6,ys.vl_servico,0))   m06" +
		"				, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),7,ys.vl_servico,0))   m07" +
		"				, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),8,ys.vl_servico,0))   m08" +
		"				, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),9,ys.vl_servico,0))   m09" +
		"				, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),10,ys.vl_servico,0))  m10" +
		"				, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),11,ys.vl_servico,0))  m11" +
		"				, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),12,ys.vl_servico,0))  m12" +
		"		FROM 	ym_sg_lote               	yl" +
		"				, ym_sg_garantia_header		gh" +
		"				, ym_sg_servico_grupo	    ys" +
		"				, ym_sg_faturamento         yf" +
		"		WHERE 	yl.lote_id              = gh.lote_id" +
		"		AND    	gh.servico_grupo_id     = ys.servico_grupo_id" +
		"		AND    	replace(yf.chassi,'-','') = replace(gh.modelo,'-','')" +
		"		AND     yf.organization_id      = nvl(:p_orgId, yf.organization_id)" +
		"		AND   	yl.status_lote_id      	in (9, 11)" +
		"		AND   	gh.status_mov_id        = 2" +
		"		AND   	yl.linha_id             = 1" +
		"		AND   	yl.fechamento_date between to_date(concat('01/01/', to_char(:p_dataAp,'YYYY')), 'DD/MM/YYYY') and :p_dataAp " +
		" 		AND   nvl(yl.end_date, sysdate + 1) >= sysdate " +
		" 		AND   nvl(gh.end_date, sysdate + 1) >= sysdate " +
		" 		AND   nvl(yf.end_date, sysdate + 1) >= sysdate " +
		"		GROUP BY to_char(yl.fechamento_date, 'YYYY')" +
		"		UNION" +
		"		SELECT 	to_char(yl.fechamento_date, 'YYYY')	Ano" +
		"				, '2'" +
		"				, 'Labor'" +
		"				, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),1,gh.vl_servico_terceiro,0))   m01" +
		"				, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),2,gh.vl_servico_terceiro,0))   m02" +
		"				, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),3,gh.vl_servico_terceiro,0))   m03" +
		"				, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),4,gh.vl_servico_terceiro,0))   m04" +
		"				, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),5,gh.vl_servico_terceiro,0))   m05" +
		"				, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),6,gh.vl_servico_terceiro,0))   m06" +
		"				, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),7,gh.vl_servico_terceiro,0))   m07" +
		"				, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),8,gh.vl_servico_terceiro,0))   m08" +
		"				, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),9,gh.vl_servico_terceiro,0))   m09" +
		"				, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),10,gh.vl_servico_terceiro,0))  m10" +
		"				, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),11,gh.vl_servico_terceiro,0))  m11" +
		"				, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),12,gh.vl_servico_terceiro,0))  m12" +
		"		FROM 	ym_sg_lote           		yl" +
		"				, ym_sg_garantia_header		gh" +
		"				, ym_sg_faturamento         yf" +
		"		WHERE 	yl.lote_id              = gh.lote_id" +
		"		AND     replace(yf.chassi,'-','') = replace(gh.modelo,'-','')" +
		"		AND     yf.organization_id      = nvl(:p_orgId, yf.organization_id)" +
		"		AND     yl.status_lote_id       in (9, 11)" +
		"		AND     gh.status_mov_id        = 2" +
		"		AND     yl.linha_id             = 1" +
		"		AND     yl.fechamento_date between to_date(concat('01/01/', to_char(:p_dataAp,'YYYY')), 'DD/MM/YYYY') and :p_dataAp" +
		" 		AND   	nvl(yl.end_date, sysdate + 1) >= sysdate " +
		" 		AND   	nvl(gh.end_date, sysdate + 1) >= sysdate " +
		" 		AND   	nvl(yf.end_date, sysdate + 1) >= sysdate " +
		"		GROUP BY to_char(yl.fechamento_date, 'YYYY')" +
		" )" +
		" GROUP BY ano" +
		" UNION ALL" +
		" SELECT 	ano" +
		"			, '3'" +
		"			, 'Result'" +
		"			, sum(m01) m01" +
		"			, sum(m02) m02" +
		"			, sum(m03) m03" +
		"			, sum(m04) m04" +
		"			, sum(m05) m05" +
		"			, sum(m06) m06" +
		"			, sum(m07) m07" +
		"			, sum(m08) m08" +
		"			, sum(m09) m09" +
		"			, sum(m10) m10" +
		"			, sum(m11) m11" +
		"			, sum(m12) m12" +
		" FROM(" +
		"		SELECT 	to_char(yl.fechamento_date, 'YYYY')	Ano" +
		"				, '3' " +
		"				, 'Result'" +
		"				, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),1,(gl.vl_peca_garantia*gl.quantidade),0))   m01" +
		"				, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),2,(gl.vl_peca_garantia*gl.quantidade),0))   m02" +
		"				, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),3,(gl.vl_peca_garantia*gl.quantidade),0))   m03" +
		"				, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),4,(gl.vl_peca_garantia*gl.quantidade),0))   m04" +
		"				, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),5,(gl.vl_peca_garantia*gl.quantidade),0))   m05" +
		"				, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),6,(gl.vl_peca_garantia*gl.quantidade),0))   m06" +
		"				, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),7,(gl.vl_peca_garantia*gl.quantidade),0))   m07" +
		"				, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),8,(gl.vl_peca_garantia*gl.quantidade),0))   m08" +
		"				, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),9,(gl.vl_peca_garantia*gl.quantidade),0))   m09" +
		"				, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),10,(gl.vl_peca_garantia*gl.quantidade),0))  m10" +
		"				, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),11,(gl.vl_peca_garantia*gl.quantidade),0))  m11" +
		"				, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),12,(gl.vl_peca_garantia*gl.quantidade),0))  m12" +
		"		FROM 	ym_sg_lote	               	yl" +
		"				, ym_sg_garantia_header		gh" +
		"				, ym_sg_garantia_line		gl" +
		"				, ym_sg_faturamento         yf" +
		"		WHERE 	yl.lote_id             = gh.lote_id" +
		"		AND     gh.garantia_id         = gl.garantia_id" +
		"		AND     replace(yf.chassi,'-','') = replace(gh.modelo,'-','')" +
		"		AND     yf.organization_id     = nvl(:p_orgId, yf.organization_id)" +
		"		AND     yl.status_lote_id      in (9,11)" +
		"		AND     gh.status_mov_id       =  2" +
		"		AND    	yl.linha_id            = 1" +
		"		AND     yl.fechamento_date between to_date(concat('01/01/', to_char(:p_dataAp,'YYYY')), 'DD/MM/YYYY') and :p_dataAp" +
		" 		AND   	nvl(yl.end_date, sysdate + 1) >= sysdate " +
		" 		AND   	nvl(gh.end_date, sysdate + 1) >= sysdate " +
		" 		AND   	nvl(yf.end_date, sysdate + 1) >= sysdate " +
		" 		AND   	nvl(gl.end_date, sysdate + 1) >= sysdate " +
		"		GROUP BY   to_char(yl.fechamento_date, 'YYYY')" +
		"		UNION ALL" +
		"		SELECT 	ano" +
		"				, '3'" +
		"				, 'Result'" +
		"				, sum(m01) m01" +
		"				, sum(m02) m02" +
		"				, sum(m03) m03" +
		"				, sum(m04) m04" +
		"				, sum(m05) m05" +
		"				, sum(m06) m06" +
		"				, sum(m07) m07" +
		"				, sum(m08) m08" +
		"				, sum(m09) m09" +
		"				, sum(m10) m10" +
		"				, sum(m11) m11" +
		"				, sum(m12) m12" +
		"		FROM (" +
		"				SELECT 	to_char(yl.fechamento_date, 'YYYY')	Ano" +
		"						, '3'" +
		"						, 'Result'" +
		"						, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),1,ys.vl_servico,0))   m01" +
		"						, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),2,ys.vl_servico,0))   m02" +
		"						, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),3,ys.vl_servico,0))   m03" +
		"						, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),4,ys.vl_servico,0))   m04" +
		"						, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),5,ys.vl_servico,0))   m05" +
		"						, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),6,ys.vl_servico,0))   m06" +
		"						, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),7,ys.vl_servico,0))   m07" +
		"						, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),8,ys.vl_servico,0))   m08" +
		"						, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),9,ys.vl_servico,0))   m09" +
		"						, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),10,ys.vl_servico,0))  m10" +
		"						, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),11,ys.vl_servico,0))  m11" +
		"						, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),12,ys.vl_servico,0))  m12" +
		"				FROM 	ym_sg_lote               	yl" +
		"						, ym_sg_garantia_header		gh" +
		"						, ym_sg_servico_grupo	    ys" +
		"						, ym_sg_faturamento         yf" +
		"				WHERE 	yl.lote_id              = gh.lote_id" +
		"				AND   	gh.servico_grupo_id     = ys.servico_grupo_id" +
		"				AND     replace(yf.chassi,'-','') = replace(gh.modelo,'-','')" +
		"				AND     yf.organization_id      = nvl(:p_orgId, yf.organization_id)" +
		"				AND     yl.status_lote_id       in (9, 11)" +
		"				AND   	gh.status_mov_id        = 2" +
		"				AND  	yl.linha_id             = 1" +
		"				AND   	yl.fechamento_date between to_date(concat('01/01/', to_char(:p_dataAp,'YYYY')), 'DD/MM/YYYY') and :p_dataAp" +
		" 				AND   	nvl(yl.end_date, sysdate + 1) >= sysdate " +
		" 				AND   	nvl(gh.end_date, sysdate + 1) >= sysdate " +
		" 				AND   	nvl(yf.end_date, sysdate + 1) >= sysdate " +
		"				GROUP BY to_char(yl.fechamento_date, 'YYYY')" +
		"				UNION ALL" +
		"				SELECT 	to_char(yl.fechamento_date, 'YYYY')	Ano" +
		"						, '3'" +
		"						, 'Result'" +
		"						, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),1,gh.vl_servico_terceiro,0))   m01" +
		"						, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),2,gh.vl_servico_terceiro,0))   m02" +
		"						, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),3,gh.vl_servico_terceiro,0))   m03" +
		"						, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),4,gh.vl_servico_terceiro,0))   m04" +
		"						, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),5,gh.vl_servico_terceiro,0))   m05" +
		"						, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),6,gh.vl_servico_terceiro,0))   m06" +
		"						, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),7,gh.vl_servico_terceiro,0))   m07" +
		"						, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),8,gh.vl_servico_terceiro,0))   m08" +
		"						, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),9,gh.vl_servico_terceiro,0))   m09" +
		"						, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),10,gh.vl_servico_terceiro,0))  m10" +
		"						, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),11,gh.vl_servico_terceiro,0))  m11" +
		"						, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),12,gh.vl_servico_terceiro,0))  m12" +
		"				FROM 	ym_sg_lote               	yl" +
		"						, ym_sg_garantia_header		gh" +
		"						, ym_sg_faturamento         yf" +
		"				WHERE 	yl.lote_id              = gh.lote_id" +
		"				AND    	replace(yf.chassi,'-','') = replace(gh.modelo,'-','')" +
		"				AND    	yf.organization_id    	= nvl(:p_orgId, yf.organization_id)" +
		"				AND   	yl.status_lote_id      	in (9, 11)" +
		"				AND   	gh.status_mov_id        = 2" +
		"				AND   	yl.linha_id             = 1" +
		"				AND   	yl.fechamento_date between to_date(concat('01/01/', to_char(:p_dataAp,'YYYY')), 'DD/MM/YYYY') and :p_dataAp" +
		" 				AND   	nvl(yl.end_date, sysdate + 1) >= sysdate " +
		" 				AND   	nvl(gh.end_date, sysdate + 1) >= sysdate " +
		" 				AND   	nvl(yf.end_date, sysdate + 1) >= sysdate " +
		"				GROUP BY to_char(yl.fechamento_date, 'YYYY')" +
		"		)" +
		"		GROUP BY ano" +
		" ) group by ano" +
		" UNION ALL" +
		" SELECT 	to_char(yo.data,'YYYY')    Ano" +
		"			, '4'" +
		"			, 'Budget'" +
		"			, sum(decode(to_number(to_char(yo.data,'MM')),1,nvl(yo.valor_garantia,0)+ nvl(yo.valor_peca,0), 0))  m01" +
		"			, sum(decode(to_number(to_char(yo.data,'MM')),2,nvl(yo.valor_garantia,0)+ nvl(yo.valor_peca,0),0))   m02" +
		"			, sum(decode(to_number(to_char(yo.data,'MM')),3,nvl(yo.valor_garantia,0)+ nvl(yo.valor_peca,0),0))   m03" +
		"			, sum(decode(to_number(to_char(yo.data,'MM')),4,nvl(yo.valor_garantia,0)+ nvl(yo.valor_peca,0),0))   m04" +
		"			, sum(decode(to_number(to_char(yo.data,'MM')),5,nvl(yo.valor_garantia,0)+ nvl(yo.valor_peca,0),0))   m05" +
		"			, sum(decode(to_number(to_char(yo.data,'MM')),6,nvl(yo.valor_garantia,0)+ nvl(yo.valor_peca,0),0))   m06" +
		"			, sum(decode(to_number(to_char(yo.data,'MM')),7,nvl(yo.valor_garantia,0)+ nvl(yo.valor_peca,0),0))   m07" +
		"			, sum(decode(to_number(to_char(yo.data,'MM')),8,nvl(yo.valor_garantia,0)+ nvl(yo.valor_peca,0),0))   m08" +
		"			, sum(decode(to_number(to_char(yo.data,'MM')),9,nvl(yo.valor_garantia,0)+ nvl(yo.valor_peca,0),0))   m09" +
		"			, sum(decode(to_number(to_char(yo.data,'MM')),10,nvl(yo.valor_garantia,0)+ nvl(yo.valor_peca,0),0))  m10" +
		"			, sum(decode(to_number(to_char(yo.data,'MM')),11,nvl(yo.valor_garantia,0)+ nvl(yo.valor_peca,0),0))  m11" +
		"			, sum(decode(to_number(to_char(yo.data,'MM')),12,nvl(yo.valor_garantia,0)+ nvl(yo.valor_peca,0),0))  m12" +
		" FROM 	ym_sg_orcamento 		yo" +
		" WHERE yo.linha_id   = 1" +
		"AND   	nvl(yo.end_date, sysdate + 1) >= sysdate " +
		" AND   yo.data between to_date(concat('01/01/', to_char(:p_dataAp,'YYYY')), 'DD/MM/YYYY') and :p_dataAp" +
		" GROUP BY to_char(yo.data,'YYYY')" +
		" UNION ALL" +
		" SELECT 	ano" +
		"			, '5'" +
		"			, 'Prev Yr'" +
		"			, sum(m01) m01" +
		"			, sum(m02) m02" +
		"			, sum(m03) m03" +
		"			, sum(m04) m04" +
		"			, sum(m05) m05" +
		"			, sum(m06) m06" +
		"			, sum(m07) m07" +
		"			, sum(m08) m08" +
		"			, sum(m09) m09" +
		"			, sum(m10) m10" +
		"			, sum(m11) m11" +
		"			, sum(m12) m12" +
		" FROM (" +
		"		SELECT 	to_char(yl.fechamento_date, 'YYYY')	Ano" +
		"				, '5'" +
		"				, 'Prev Yr'" +
		"				, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),1,(gl.vl_peca_garantia*gl.quantidade),0))   m01" +
		"				, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),2,(gl.vl_peca_garantia*gl.quantidade),0))   m02" +
		"				, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),3,(gl.vl_peca_garantia*gl.quantidade),0))   m03" +
		"				, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),4,(gl.vl_peca_garantia*gl.quantidade),0))   m04" +
		"				, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),5,(gl.vl_peca_garantia*gl.quantidade),0))   m05" +
		"				, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),6,(gl.vl_peca_garantia*gl.quantidade),0))   m06" +
		"				, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),7,(gl.vl_peca_garantia*gl.quantidade),0))   m07" +
		"				, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),8,(gl.vl_peca_garantia*gl.quantidade),0))   m08" +
		"				, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),9,(gl.vl_peca_garantia*gl.quantidade),0))   m09" +
		"				, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),10,(gl.vl_peca_garantia*gl.quantidade),0))  m10" +
		"				, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),11,(gl.vl_peca_garantia*gl.quantidade),0))  m11" +
		"				, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),12,(gl.vl_peca_garantia*gl.quantidade),0))  m12" +
		"		FROM 	ym_sg_lote               	yl" +
		"				, ym_sg_garantia_header		gh" +
		"				, ym_sg_garantia_line	  	gl" +
		"				, ym_sg_faturamento         yf" +
		"		WHERE 	yl.lote_id             = gh.lote_id" +
		"		AND   	gh.garantia_id         = gl.garantia_id" +
		"		AND    	replace(yf.chassi,'-','') = replace(gh.modelo,'-','')" +
		"		AND    	yf.organization_id     = nvl(:p_orgId, yf.organization_id)" +
		"		AND   	yl.status_lote_id      in (9,11)" +
		"		AND     gh.status_mov_id       =  2" +
		" 		AND     yl.linha_id            = 1" +
		"		AND     yl.fechamento_date between to_date(concat('01/01/', to_number(to_char(:p_dataAp,'YYYY')) - 1), 'DD/MM/YYYY') and add_months(:p_dataAp, -12)" +
		" 		AND   	nvl(yl.end_date, sysdate + 1) >= sysdate " +
		" 		AND   	nvl(gh.end_date, sysdate + 1) >= sysdate " +
		" 		AND   	nvl(yf.end_date, sysdate + 1) >= sysdate " +
		" 		AND   	nvl(gl.end_date, sysdate + 1) >= sysdate " +
		"		GROUP BY   to_char(yl.fechamento_date, 'YYYY')" +
		"		UNION ALL" +
		"		SELECT 	ano" +
		"				, '5'" +
		"				, 'Prev Yr'" +
		"				, sum(m01) m01" +
		"				, sum(m02) m02" +
		"				, sum(m03) m03" +
		"				, sum(m04) m04" +
		"				, sum(m05) m05" +
		"				, sum(m06) m06" +
		"				, sum(m07) m07" +
		"				, sum(m08) m08" +
		"				, sum(m09) m09" +
		"				, sum(m10) m10" +
		"				, sum(m11) m11" +
		"				, sum(m12) m12" +
		"		FROM (" +
		"				SELECT 	to_char(yl.fechamento_date, 'YYYY')	Ano" +
		"						, '5'" +
		"						, 'Prev Yr'" +
		"						, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),1,ys.vl_servico,0))   m01" +
		"						, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),2,ys.vl_servico,0))   m02" +
		"						, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),3,ys.vl_servico,0))   m03" +
		"						, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),4,ys.vl_servico,0))   m04" +
		"						, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),5,ys.vl_servico,0))   m05" +
		"						, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),6,ys.vl_servico,0))   m06" +
		"						, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),7,ys.vl_servico,0))   m07" +
		"						, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),8,ys.vl_servico,0))   m08" +
		"						, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),9,ys.vl_servico,0))   m09" +
		"						, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),10,ys.vl_servico,0))  m10" +
		"						, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),11,ys.vl_servico,0))  m11" +
		"						, sum(decode(to_number(to_char(yl.fechamento_date,'MM')),12,ys.vl_servico,0))  m12" +
		"				FROM 	ym_sg_lote               	yl" +
		"						, ym_sg_garantia_header		gh" +
		"						, ym_sg_servico_grupo	    ys" +
		"						, ym_sg_faturamento         yf" +
		"				WHERE 	yl.lote_id              = gh.lote_id" +
		"				AND   	gh.servico_grupo_id     = ys.servico_grupo_id" +
		"				AND     replace(yf.chassi,'-','') = replace(gh.modelo,'-','')" +
		"				AND    	yf.organization_id    	= nvl(:p_orgId, yf.organization_id)" +
		"				AND   	yl.status_lote_id       in (9,11)" +
		"				AND   	gh.status_mov_id        = 2" +
		"				AND   	yl.linha_id             = 1" +
		"				AND     yl.fechamento_date between to_date(concat('01/01/', to_number(to_char(:p_dataAp,'YYYY')) - 1), 'DD/MM/YYYY') and add_months(:p_dataAp, -12)" +
		" 				AND   	nvl(yl.end_date, sysdate + 1) >= sysdate " +
		" 				AND   	nvl(gh.end_date, sysdate + 1) >= sysdate " +
		" 				AND   	nvl(yf.end_date, sysdate + 1) >= sysdate " +
		" 				AND   	nvl(ys.end_date, sysdate + 1) >= sysdate " +
		"				GROUP BY to_char(yl.fechamento_date, 'YYYY')" +
		"				UNION ALL" +
		"				SELECT 	to_char(yl.fechamento_date, 'YYYY')	Ano" +
		"						, '5'" +
		"						, 'Prev Yr'" +
		"						,  sum(decode(to_number(to_char(yl.fechamento_date,'MM')),1,gh.vl_servico_terceiro,0))   m01" +
		"						,  sum(decode(to_number(to_char(yl.fechamento_date,'MM')),2,gh.vl_servico_terceiro,0))   m02" +
		"						,  sum(decode(to_number(to_char(yl.fechamento_date,'MM')),3,gh.vl_servico_terceiro,0))   m03" +
		"						,  sum(decode(to_number(to_char(yl.fechamento_date,'MM')),4,gh.vl_servico_terceiro,0))   m04" +
		"						,  sum(decode(to_number(to_char(yl.fechamento_date,'MM')),5,gh.vl_servico_terceiro,0))   m05" +
		"						,  sum(decode(to_number(to_char(yl.fechamento_date,'MM')),6,gh.vl_servico_terceiro,0))   m06" +
		"						,  sum(decode(to_number(to_char(yl.fechamento_date,'MM')),7,gh.vl_servico_terceiro,0))   m07" +
		"						,  sum(decode(to_number(to_char(yl.fechamento_date,'MM')),8,gh.vl_servico_terceiro,0))   m08" +
		"						,  sum(decode(to_number(to_char(yl.fechamento_date,'MM')),9,gh.vl_servico_terceiro,0))   m09" +
		"						,  sum(decode(to_number(to_char(yl.fechamento_date,'MM')),10,gh.vl_servico_terceiro,0))  m10" +
		"						,  sum(decode(to_number(to_char(yl.fechamento_date,'MM')),11,gh.vl_servico_terceiro,0))  m11" +
		"						,  sum(decode(to_number(to_char(yl.fechamento_date,'MM')),12,gh.vl_servico_terceiro,0))  m12" +
		"				FROM 	ym_sg_lote               	yl" +
		"						, ym_sg_garantia_header		gh" +
		"						, ym_sg_faturamento         yf" +
		"				WHERE 	yl.lote_id              = gh.lote_id" +
		"				AND    	replace(yf.chassi,'-','') = replace(gh.modelo,'-','')" +
		"				AND    	yf.organization_id    	= nvl(:p_orgId, yf.organization_id) " +
		"				AND   	yl.status_lote_id      in (9, 11)" +
		"				AND   	gh.status_mov_id       = 2" +
		"				AND   	yl.linha_id            = 1" +
		"				AND   	yl.fechamento_date between to_date(concat('01/01/', to_number(to_char(:p_dataAp,'YYYY')) - 1), 'DD/MM/YYYY') and add_months(:p_dataAp, -12)" +
		" 				AND   	nvl(yl.end_date, sysdate + 1) >= sysdate " +
		" 				AND   	nvl(gh.end_date, sysdate + 1) >= sysdate " +
		" 				AND   	nvl(yf.end_date, sysdate + 1) >= sysdate " +
		"				GROUP BY to_char(yl.fechamento_date, 'YYYY')" +
		"		)" +
		"		GROUP BY ano" +
		" )" +
		" GROUP BY ano " +
		" ORDER BY 2 ";
	
	/** Constante com a query do relatório service report parte 2, gráficos 4, 5 e 6 */
	private static final String QUERY_SERVICE_REPORT_PART2 =
		"SELECT  Ano  " +
		"        ,     '3' " +
		"        ,      'Result' " +
		"        ,      sum(m01) m01 " +
		"        ,      sum(m02) m02 " +
		"        ,      sum(m03) m03 " +
		"        ,      sum(m04) m04 " +
		"        ,      sum(m05) m05 " +
		"        ,      sum(m06) m06 " +
		"        ,      sum(m07) m07 " +
		"        ,      sum(m08) m08 " +
		"        ,      sum(m09) m09 " +
		"        ,      sum(m10) m10 " +
		"        ,      sum(m11) m11 " +
		"        ,      sum(m12) m12 " +
		"FROM(" +
		"        SELECT  to_char(yl.fechamento_date, 'YYYY')	Ano" +
		"                ,     '3'" +
		"                ,     'Result'" +
		"                ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),1,(gl.vl_peca_garantia*gl.quantidade),0))   m01" +
		"                ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),2,(gl.vl_peca_garantia*gl.quantidade),0))   m02" +
		"                ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),3,(gl.vl_peca_garantia*gl.quantidade),0))   m03" +
		"                ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),4,(gl.vl_peca_garantia*gl.quantidade),0))   m04" +
		"                ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),5,(gl.vl_peca_garantia*gl.quantidade),0))   m05" +
		"                ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),6,(gl.vl_peca_garantia*gl.quantidade),0))   m06" +
		"                ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),7,(gl.vl_peca_garantia*gl.quantidade),0))   m07" +
		"                ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),8,(gl.vl_peca_garantia*gl.quantidade),0))   m08" +
		"                ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),9,(gl.vl_peca_garantia*gl.quantidade),0))   m09" +
		"                ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),10,(gl.vl_peca_garantia*gl.quantidade),0))  m10" +
		"                ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),11,(gl.vl_peca_garantia*gl.quantidade),0))  m11" +
		"                ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),12,(gl.vl_peca_garantia*gl.quantidade),0))  m12" +
		"        FROM    ym_sg_lote                  yl" +
		"                ,    ym_sg_garantia_header  gh" +
		"                ,    ym_sg_garantia_line	gl" +
		"                ,    ym_sg_faturamento      yf" +
		"        WHERE   yl.lote_id              = gh.lote_id" +
		"        AND     gh.garantia_id          = gl.garantia_id" +
		"        AND     replace(yf.chassi,'-','') = replace(gh.modelo,'-','')" +
		"        AND     yf.organization_id      = nvl(:p_orgId, yf.organization_id)" +
		"        AND     yl.status_lote_id       in (9,11)" +
		"        AND     gh.status_mov_id        = 2" +
		"        AND     yl.linha_id             = 1" +
		"        AND     yl.fechamento_date between to_date(concat('01/01/', to_char(:p_dataAp,'YYYY')), 'DD/MM/YYYY') AND :p_dataAp" +
		" 		 AND   	nvl(yl.end_date, sysdate + 1) >= sysdate " +
		" 		 AND   	nvl(gh.end_date, sysdate + 1) >= sysdate " +
		" 		 AND   	nvl(yf.end_date, sysdate + 1) >= sysdate " +
		" 		 AND   	nvl(gl.end_date, sysdate + 1) >= sysdate " +
		"        GROUP BY  to_char(yl.fechamento_date, 'YYYY')" +
		"        				" +
		"        UNION ALL		" +
		"        				" +
		"        SELECT  Ano	" +
		"                ,      '3'" +
		"                ,      'Result'" +
		"                ,      sum(m01) m01" +
		"                ,      sum(m02) m02" +
		"                ,      sum(m03) m03" +
		"                ,      sum(m04) m04" +
		"                ,      sum(m05) m05" +
		"                ,      sum(m06) m06" +
		"                ,      sum(m07) m07" +
		"                ,      sum(m08) m08" +
		"                ,      sum(m09) m09" +
		"                ,      sum(m10) m10" +
		"                ,      sum(m11) m11" +
		"                ,      sum(m12) m12" +
		"        FROM(" +
		"                SELECT  to_char(yl.fechamento_date, 'YYYY')	Ano" +
		"                        ,     '3'" +
		"                        ,      'Result'" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),1,ys.vl_servico,0))   m01" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),2,ys.vl_servico,0))   m02" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),3,ys.vl_servico,0))   m03" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),4,ys.vl_servico,0))   m04" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),5,ys.vl_servico,0))   m05" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),6,ys.vl_servico,0))   m06" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),7,ys.vl_servico,0))   m07" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),8,ys.vl_servico,0))   m08" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),9,ys.vl_servico,0))   m09" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),10,ys.vl_servico,0))  m10" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),11,ys.vl_servico,0))  m11" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),12,ys.vl_servico,0))  m12" +
		"                FROM    ym_sg_lote                      yl" +
		"                        ,    ym_sg_garantia_header		gh" +
		"                        ,    ym_sg_servico_grupo	    ys" +
		"                        ,    ym_sg_faturamento          yf" +
		"                WHERE   yl.lote_id              = gh.lote_id" +
		"                AND     gh.servico_grupo_id     = ys.servico_grupo_id" +
		"                AND     replace(yf.chassi,'-','') = replace(gh.modelo,'-','')" +
		"                AND     yf.organization_id      = nvl(:p_orgId, yf.organization_id)" +
		"                AND     yl.status_lote_id       in (9, 11)" +
		"                AND     gh.status_mov_id        = 2" +
		"                AND     yl.linha_id             = 1" +
		"                AND     yl.fechamento_date between to_date(concat('01/01/', to_char(:p_dataAp,'YYYY')), 'DD/MM/YYYY') AND :p_dataAp" +
		" 		 		 AND   	nvl(yl.end_date, sysdate + 1) >= sysdate " +
		" 		 		 AND   	nvl(gh.end_date, sysdate + 1) >= sysdate " +
		" 		 		 AND   	nvl(yf.end_date, sysdate + 1) >= sysdate " +
		" 		 		 AND   	nvl(ys.end_date, sysdate + 1) >= sysdate " +
		"                GROUP BY to_char(yl.fechamento_date, 'YYYY')" +
		"                				" +
		"                UNION ALL		" +
		"								" +
		"                SELECT  to_char(yl.fechamento_date, 'YYYY')	Ano" +
		"                        ,     '3'" +
		"                        ,      'Result'" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),1,gh.vl_servico_terceiro,0))   m01" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),2,gh.vl_servico_terceiro,0))   m02" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),3,gh.vl_servico_terceiro,0))   m03" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),4,gh.vl_servico_terceiro,0))   m04" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),5,gh.vl_servico_terceiro,0))   m05" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),6,gh.vl_servico_terceiro,0))   m06" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),7,gh.vl_servico_terceiro,0))   m07" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),8,gh.vl_servico_terceiro,0))   m08" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),9,gh.vl_servico_terceiro,0))   m09" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),10,gh.vl_servico_terceiro,0))  m10" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),11,gh.vl_servico_terceiro,0))  m11" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),12,gh.vl_servico_terceiro,0))  m12" +
		"                FROM    ym_sg_lote                      yl" +
		"                        ,    ym_sg_garantia_header		gh" +
		"                        ,    ym_sg_faturamento          yf" +
		"                WHERE   yl.lote_id              = gh.lote_id" +
		"                AND     replace(yf.chassi,'-','') = replace(gh.modelo,'-','')" +
		"                AND     yf.organization_id      = nvl(:p_orgId, yf.organization_id)" +
		"                AND     yl.status_lote_id       in (9, 11)" +
		"                AND     gh.status_mov_id        = 2" +
		"                AND     yl.linha_id             = 1" +
		"                AND     yl.fechamento_date between to_date(concat('01/01/', to_char(:p_dataAp,'YYYY')), 'DD/MM/YYYY') AND :p_dataAp" +
		" 		 		 AND   	nvl(yl.end_date, sysdate + 1) >= sysdate " +
		" 		 		 AND   	nvl(gh.end_date, sysdate + 1) >= sysdate " +
		" 		 		 AND   	nvl(yf.end_date, sysdate + 1) >= sysdate " +
		"                GROUP BY to_char(yl.fechamento_date, 'YYYY')" +
		"                				" +
		"                UNION ALL		" +
		"                				" +
		"                SELECT  to_char(yl.fechamento_date,'YYYY')  Ano" +
		"                        ,     '3'" +
		"                        ,      'Result'" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),1,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m01" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),2,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m02" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),3,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m03" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),4,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m04" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),5,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m05" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),6,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m06" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),7,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m07" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),8,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m08" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),9,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m09" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),10,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))  m10" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),11,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))  m11" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),12,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))  m12      " +
		"                FROM    ym_sg_lote                  yl" +
		"                        ,    ym_sg_cupom			yc" +
		"                        ,    ym_sg_revisao		    yr" +
		"                        ,    ym_sg_faturamento      yf" +
		"                WHERE   yl.lote_id              = yc.lote_id" +
		"                AND     yc.revisao_id           = yr.revisao_id" +
		"                AND     replace(yf.chassi,'-','') = replace(yc.modelo,'-','')" +
		"                AND     yf.organization_id      = nvl(:p_orgId, yf.organization_id)" +
		"                AND     yr.numero_revisao       in (2, 3)" +
		"                AND     yl.status_lote_id       in (9,11)" +
		"                AND     yc.status_mov_id        =  2" +
		"                AND   yl.fechamento_date between to_date(concat('01/01/', to_char(:p_dataAp,'YYYY')), 'DD/MM/YYYY') AND :p_dataAp" +
		"                AND   yl.linha_id               = 1" +
		" 		 		 AND   	nvl(yl.end_date, sysdate + 1) >= sysdate " +
		" 		 		 AND   	nvl(yc.end_date, sysdate + 1) >= sysdate " +
		" 		 		 AND   	nvl(yf.end_date, sysdate + 1) >= sysdate " +
		" 		 		 AND   	nvl(yr.end_date, sysdate + 1) >= sysdate " +
		"                GROUP BY  to_char(yl.fechamento_date,'YYYY')     " +
		"        )					" +
		"        GROUP BY Ano		" +
		")							" +
		"GROUP BY Ano				" +
		"    						" +
		"UNION ALL					" +
		"SELECT  to_char(yo.data,'YYYY')    Ano    " +
		"        ,     '4'" +
		"        ,     'Budget'" +
		"        ,      sum(decode(to_number(to_char(yo.data,'MM')),1,nvl(yo.valor_garantia,0)+ nvl(yo.valor_peca,0),0))   m01	" +
		"        ,      sum(decode(to_number(to_char(yo.data,'MM')),2,nvl(yo.valor_garantia,0)+ nvl(yo.valor_peca,0),0))   m02	" +
		"        ,      sum(decode(to_number(to_char(yo.data,'MM')),3,nvl(yo.valor_garantia,0)+ nvl(yo.valor_peca,0),0))   m03	" +
		"        ,      sum(decode(to_number(to_char(yo.data,'MM')),4,nvl(yo.valor_garantia,0)+ nvl(yo.valor_peca,0),0))   m04	" +
		"        ,      sum(decode(to_number(to_char(yo.data,'MM')),5,nvl(yo.valor_garantia,0)+ nvl(yo.valor_peca,0),0))   m05	" +
		"        ,      sum(decode(to_number(to_char(yo.data,'MM')),6,nvl(yo.valor_garantia,0)+ nvl(yo.valor_peca,0),0))   m06	" +
		"        ,      sum(decode(to_number(to_char(yo.data,'MM')),7,nvl(yo.valor_garantia,0)+ nvl(yo.valor_peca,0),0))   m07	" +
		"        ,      sum(decode(to_number(to_char(yo.data,'MM')),8,nvl(yo.valor_garantia,0)+ nvl(yo.valor_peca,0),0))   m08	" +
		"        ,      sum(decode(to_number(to_char(yo.data,'MM')),9,nvl(yo.valor_garantia,0)+ nvl(yo.valor_peca,0),0))   m09	" +
		"        ,      sum(decode(to_number(to_char(yo.data,'MM')),10,nvl(yo.valor_garantia,0)+ nvl(yo.valor_peca,0),0))  m10	" +
		"        ,      sum(decode(to_number(to_char(yo.data,'MM')),11,nvl(yo.valor_garantia,0)+ nvl(yo.valor_peca,0),0))  m11	" +
		"        ,      sum(decode(to_number(to_char(yo.data,'MM')),12,nvl(yo.valor_garantia,0)+ nvl(yo.valor_peca,0),0))  m12	" +
		"FROM    ym_sg_orcamento yo				" +
		"WHERE   yo.linha_id       = 1 			" +
		"AND   	nvl(yo.end_date, sysdate + 1) >= sysdate " +
		"AND     yo.data between to_date(concat('01/01/', to_char(:p_dataAp,'YYYY')), 'DD/MM/YYYY') AND :p_dataAp	" +
		"GROUP BY to_char(yo.data,'YYYY')        " +
		"							" +
		"UNION ALL					" +
		"							" +
		"SELECT  Ano					" +
		"        ,      '5'				" +
		"        ,      'Prev Yr'		" +
		"        ,      sum(m01) m01	" +
		"        ,      sum(m02) m02	" +
		"        ,      sum(m03) m03	" +
		"        ,      sum(m04) m04	" +
		"        ,      sum(m05) m05	" +
		"        ,      sum(m06) m06	" +
		"        ,      sum(m07) m07	" +
		"        ,      sum(m08) m08	" +
		"        ,      sum(m09) m09	" +
		"        ,      sum(m10) m10	" +
		"        ,      sum(m11) m11	" +
		"        ,      sum(m12) m12	" +
		"FROM(							" +
		"        SELECT  to_char(yl.fechamento_date, 'YYYY')	Ano" +
		"                ,      '5'" +
		"                ,      'Prev Yr'" +
		"                ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),1,(gl.vl_peca_garantia*gl.quantidade),0))   m01" +
		"                ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),2,(gl.vl_peca_garantia*gl.quantidade),0))   m02" +
		"                ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),3,(gl.vl_peca_garantia*gl.quantidade),0))   m03" +
		"                ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),4,(gl.vl_peca_garantia*gl.quantidade),0))   m04" +
		"                ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),5,(gl.vl_peca_garantia*gl.quantidade),0))   m05" +
		"                ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),6,(gl.vl_peca_garantia*gl.quantidade),0))   m06" +
		"                ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),7,(gl.vl_peca_garantia*gl.quantidade),0))   m07" +
		"                ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),8,(gl.vl_peca_garantia*gl.quantidade),0))   m08" +
		"                ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),9,(gl.vl_peca_garantia*gl.quantidade),0))   m09" +
		"                ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),10,(gl.vl_peca_garantia*gl.quantidade),0))  m10" +
		"                ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),11,(gl.vl_peca_garantia*gl.quantidade),0))  m11" +
		"                ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),12,(gl.vl_peca_garantia*gl.quantidade),0))  m12" +
		"        FROM    ym_sg_lote                  yl" +
		"                ,    ym_sg_garantia_header	gh" +
		"                ,    ym_sg_garantia_line    gl" +
		"                ,    ym_sg_faturamento      yf" +
		"        WHERE   yl.lote_id                  = gh.lote_id" +
		"        AND     gh.garantia_id              = gl.garantia_id" +
		"        AND     replace(yf.chassi,'-','') = replace(gh.modelo,'-','')" +
		"        AND     yf.organization_id          = nvl(:p_orgId, yf.organization_id)" +
		"        AND     yl.status_lote_id           in (9,11)" +
		"        AND     gh.status_mov_id            =  2" +
		"        AND     yl.linha_id                 = 1" +
		"        AND     yl.fechamento_date between to_date(concat('01/01/', to_number(to_char(:p_dataAp,'YYYY')) - 1), 'DD/MM/YYYY') AND add_months(:p_dataAp, -12)" +
		" 		 AND   	nvl(yl.end_date, sysdate + 1) >= sysdate " +
		" 		 AND   	nvl(gh.end_date, sysdate + 1) >= sysdate " +
		" 		 AND   	nvl(gl.end_date, sysdate + 1) >= sysdate " +
		" 		 AND   	nvl(yf.end_date, sysdate + 1) >= sysdate " +
		"        GROUP BY   to_char(yl.fechamento_date, 'YYYY')" +
		"        									" +
		"        UNION ALL							" +
		"        									" +
		"        SELECT  Ano						" +
		"                ,     '5'" +
		"                ,      'Prev Yr'" +
		"                ,      sum(m01) m01" +
		"                ,      sum(m02) m02" +
		"                ,      sum(m03) m03" +
		"                ,      sum(m04) m04" +
		"                ,      sum(m05) m05" +
		"                ,      sum(m06) m06" +
		"                ,      sum(m07) m07" +
		"                ,      sum(m08) m08" +
		"                ,      sum(m09) m09" +
		"                ,      sum(m10) m10" +
		"                ,      sum(m11) m11" +
		"                ,      sum(m12) m12" +
		"        FROM(" +
		"                SELECT  to_char(yl.fechamento_date, 'YYYY')	Ano" +
		"                        ,     '5'" +
		"                        ,      'Prev Yr'" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),1,ys.vl_servico,0))   m01" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),2,ys.vl_servico,0))   m02" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),3,ys.vl_servico,0))   m03" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),4,ys.vl_servico,0))   m04" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),5,ys.vl_servico,0))   m05" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),6,ys.vl_servico,0))   m06" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),7,ys.vl_servico,0))   m07" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),8,ys.vl_servico,0))   m08" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),9,ys.vl_servico,0))   m09" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),10,ys.vl_servico,0))  m10" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),11,ys.vl_servico,0))  m11" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),12,ys.vl_servico,0))  m12" +
		"                FROM    ym_sg_lote                      yl" +
		"                        ,    ym_sg_garantia_header		gh" +
		"                        ,    ym_sg_servico_grupo	    ys" +
		"                        ,    ym_sg_faturamento          yf" +
		"                WHERE   yl.lote_id              = gh.lote_id" +
		"                AND     gh.servico_grupo_id     = ys.servico_grupo_id" +
		"                AND     replace(yf.chassi,'-','') = replace(gh.modelo,'-','')" +
		"                AND     yf.organization_id      = nvl(:p_orgId, yf.organization_id)" +
		"                AND     yl.status_lote_id       in (9,11)" +
		"                AND     gh.status_mov_id        =  2" +
		"                AND     yl.linha_id             = 1" +
		"                AND     yl.fechamento_date between to_date(concat('01/01/', to_number(to_char(:p_dataAp,'YYYY')) - 1), 'DD/MM/YYYY') AND add_months(:p_dataAp, -12)" +
		" 		 		 AND   	nvl(yl.end_date, sysdate + 1) >= sysdate " +
		" 		 		 AND   	nvl(gh.end_date, sysdate + 1) >= sysdate " +
		" 		 		 AND   	nvl(ys.end_date, sysdate + 1) >= sysdate " +
		" 		 		 AND   	nvl(yf.end_date, sysdate + 1) >= sysdate " +
		"                GROUP BY to_char(yl.fechamento_date, 'YYYY')" +
		"                									" +
		"                UNION ALL							" +
		"													" +
		"                SELECT  to_char(yl.fechamento_date, 'YYYY')	Ano" +
		"                        ,      '5'" +
		"                        ,      'Prev Yr'" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),1,gh.vl_servico_terceiro,0))   m01" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),2,gh.vl_servico_terceiro,0))   m02" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),3,gh.vl_servico_terceiro,0))   m03" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),4,gh.vl_servico_terceiro,0))   m04" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),5,gh.vl_servico_terceiro,0))   m05" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),6,gh.vl_servico_terceiro,0))   m06" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),7,gh.vl_servico_terceiro,0))   m07" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),8,gh.vl_servico_terceiro,0))   m08" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),9,gh.vl_servico_terceiro,0))   m09" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),10,gh.vl_servico_terceiro,0))  m10" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),11,gh.vl_servico_terceiro,0))  m11" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),12,gh.vl_servico_terceiro,0))  m12" +
		"                FROM    ym_sg_lote                  yl" +
		"                        ,    ym_sg_garantia_header	gh" +
		"                        ,    ym_sg_faturamento      yf" +
		"                WHERE   yl.lote_id                  = gh.lote_id" +
		"                AND     replace(yf.chassi,'-','') = replace(gh.modelo,'-','')" +
		"                AND     yf.organization_id          = nvl(:p_orgId, yf.organization_id) " +
		"                AND     yl.status_lote_id           in (9, 11)" +
		"                AND     gh.status_mov_id            =  2" +
		"                AND     yl.linha_id                 = 1" +
		"                AND     yl.fechamento_date between to_date(concat('01/01/', to_number(to_char(:p_dataAp,'YYYY')) - 1), 'DD/MM/YYYY') AND add_months(:p_dataAp, -12)" +
		" 		 		 AND   	nvl(yl.end_date, sysdate + 1) >= sysdate " +
		" 		 		 AND   	nvl(gh.end_date, sysdate + 1) >= sysdate " +
		" 		 		 AND   	nvl(yf.end_date, sysdate + 1) >= sysdate " +
		"                GROUP BY to_char(yl.fechamento_date, 'YYYY')" +
		"                								" +
		"                UNION ALL 						" +
		"               								" +
		"                SELECT  to_char(yl.fechamento_date,'YYYY')  Ano     " +
		"                        ,      '5'" +
		"                        ,      'Prev Yr'" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),1,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m01" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),2,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m02" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),3,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m03" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),4,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m04" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),5,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m05" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),6,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m06" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),7,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m07" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),8,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m08" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),9,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m09" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),10,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))  m10" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),11,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))  m11" +
		"                        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),12,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))  m12       " +
		"                FROM    ym_sg_lote                  yl" +
		"                        ,    ym_sg_cupom			yc" +
		"                        ,    ym_sg_revisao		    yr" +
		"                        ,    ym_sg_faturamento		yf" +
		"                WHERE   yl.lote_id                   = yc.lote_id" +
		"                AND     yc.revisao_id                = yr.revisao_id" +
		"                AND     replace(yf.chassi,'-','') = replace(yc.modelo,'-','')" +
		"                AND     yf.organization_id           = nvl(:p_orgId, yf.organization_id) " +
		"                AND     yr.numero_revisao            in (2,3)" +
		"                AND     yl.status_lote_id            in (9,11)" +
		"                AND     yc.status_mov_id             =  2" +
		"                AND     yl.fechamento_date between to_date(concat('0101', to_number(to_char(:p_dataAp,'YYYY')) - 1), 'DD/MM/YYYY') AND add_months(:p_dataAp, -12)" +
		"                AND     yl.linha_id                  = 1" +
		"                AND     yc.status_mov_id             =  2" +
		" 		 		 AND   	nvl(yl.end_date, sysdate + 1) >= sysdate " +
		" 		 		 AND   	nvl(yc.end_date, sysdate + 1) >= sysdate " +
		" 		 		 AND   	nvl(yr.end_date, sysdate + 1) >= sysdate " +
		" 		 		 AND   	nvl(yf.end_date, sysdate + 1) >= sysdate " +
		"                GROUP BY  to_char(yl.fechamento_date,'YYYY')  " +
		"        )" +
		"        GROUP BY Ano   " +
		")   " +
		"GROUP BY Ano    " +
		"ORDER BY 2 ";
	
	/** Constante com a query do relatório service report parte 3, gráficos 7, 8 e 9 */
	private static final String QUERY_SERVICE_REPORT_PART3 = 
		"SELECT  to_char(yl.fechamento_date,'YYYY')  Ano     " +
		"        ,      '1'" +
		"        ,      '1.000 km '" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),1,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m01	" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),2,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m02	" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),3,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m03	" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),4,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m04	" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),5,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m05	" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),6,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m06	" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),7,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m07	" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),8,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m08	" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),9,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m09	" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),10,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))  m10	" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),11,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))  m11	" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),12,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))  m12	" +
		"FROM    ym_sg_lote                  yl " +
		"        ,    ym_sg_cupom			 yc " +
		"        ,    ym_sg_revisao		     yr " +
		"        ,    ym_sg_faturamento      yf " +
		"WHERE   yl.lote_id            = yc.lote_id	" +
		"AND     yc.revisao_id         = yr.revisao_id	" +
		"AND     replace(yf.chassi,'-','') = replace(yc.modelo,'-','')	" +
		"AND     yf.organization_id    = nvl(:p_orgId, yf.organization_id)	" +
		"AND     yr.numero_revisao     = 2	" +
		"AND     yl.status_lote_id     IN (9,11)	" +
		"AND     yc.status_mov_id      = 2	" +
		"AND     yl.fechamento_date BETWEEN to_date(concat('01/01/', to_char(:p_dataAp,'YYYY')), 'DD/MM/YYYY') AND :p_dataAp	" +
		"AND     yl.linha_id           = 1	" +
		"AND   	nvl(yl.end_date, sysdate + 1) >= sysdate " +
		"AND   	nvl(yc.end_date, sysdate + 1) >= sysdate " +
		"AND   	nvl(yr.end_date, sysdate + 1) >= sysdate " +
		"AND   	nvl(yf.end_date, sysdate + 1) >= sysdate " +
		"GROUP BY  to_char(yl.fechamento_date,'YYYY')     " +
		"				" +
		"UNION ALL		" +
		"				" +
		"SELECT  to_char(yl.fechamento_date,'YYYY')  Ano     " +
		"        ,      '2'" +
		"        ,      '5.000 km '" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),1,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m01 " +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),2,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m02 " +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),3,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m03 " +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),4,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m04 " +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),5,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m05 " +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),6,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m06 " +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),7,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m07 " +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),8,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m08 " +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),9,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m09 " +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),10,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))  m10 " +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),11,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))  m11 " +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),12,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))  m12 " +
		"FROM    ym_sg_lote              yl 	" +
		"        ,    ym_sg_cupom        yc 	" +
		"        ,    ym_sg_revisao		 yr 	" +
		"        ,    ym_sg_faturamento  yf 	" +
		"WHERE   yl.lote_id            = yc.lote_id " +
		"AND     yc.revisao_id         = yr.revisao_id " +
		"AND     replace(yf.chassi,'-','') = replace(yc.modelo,'-','') " +
		"AND     yf.organization_id    = nvl(:p_orgId, yf.organization_id) 	" +
		"AND     yr.numero_revisao     = 3	" +
		"AND     yl.status_lote_id     IN (9,11)	" +
		"AND     yc.status_mov_id      =  2	" +
		"AND     yl.fechamento_date BETWEEN to_date(concat('01/01/', to_char(:p_dataAp,'YYYY')), 'DD/MM/YYYY') AND :p_dataAp	" +
		"AND     yl.linha_id           = 1	" +
		"AND   	nvl(yl.end_date, sysdate + 1) >= sysdate " +
		"AND   	nvl(yc.end_date, sysdate + 1) >= sysdate " +
		"AND   	nvl(yr.end_date, sysdate + 1) >= sysdate " +
		"AND   	nvl(yf.end_date, sysdate + 1) >= sysdate " +
		"GROUP BY  to_char(yl.fechamento_date,'YYYY')	" +
		"							" +
		"UNION ALL					" +
		"							" +
		"SELECT  to_char(yl.fechamento_date,'YYYY')  Ano    " +
		"        ,      '3'" +
		"        ,      'Result'" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),1,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m01" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),2,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m02" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),3,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m03" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),4,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m04" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),5,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m05" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),6,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m06" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),7,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m07" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),8,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m08" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),9,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m09" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),10,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))  m10" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),11,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))  m11" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),12,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))  m12      " +
		"FROM    ym_sg_lote              yl	" +
		"        ,    ym_sg_cupom		 yc	" +
		"        ,    ym_sg_revisao		 yr	" +
		"        ,    ym_sg_faturamento  yf	" +
		"WHERE   yl.lote_id            = yc.lote_id " +
		"AND     yc.revisao_id         = yr.revisao_id 	" +
		"AND     replace(yf.chassi,'-','') = replace(yc.modelo,'-','') 	" +
		"AND     yf.organization_id    = nvl(:p_orgId, yf.organization_id) " +
		"AND     yr.numero_revisao     IN (2, 3) " +
		"AND     yl.status_lote_id     IN (9,11) " +
		"AND     yc.status_mov_id      =  2 " +
		"AND     yl.fechamento_date BETWEEN to_date(concat('01/01/', to_char(:p_dataAp,'YYYY')), 'DD/MM/YYYY') AND :p_dataAp 	" +
		"AND     yl.lINha_id           = 1 " +
		"AND   	nvl(yl.end_date, sysdate + 1) >= sysdate " +
		"AND   	nvl(yc.end_date, sysdate + 1) >= sysdate " +
		"AND   	nvl(yr.end_date, sysdate + 1) >= sysdate " +
		"AND   	nvl(yf.end_date, sysdate + 1) >= sysdate " +
		"GROUP BY  to_char(yl.fechamento_date,'YYYY')      " +
		"					" +
		"UNION ALL			" +
		"					" +
		"SELECT  to_char(yo.data,'YYYY')    Ano    " +
		"        ,      '4'" +
		"        ,      'Budget'" +
		"        ,      sum(decode(to_number(to_char(yo.data,'MM')),1,nvl(yo.valor_revisao,0), 0))  m01" +
		"        ,      sum(decode(to_number(to_char(yo.data,'MM')),2,nvl(yo.valor_revisao,0),0))   m02" +
		"        ,      sum(decode(to_number(to_char(yo.data,'MM')),3,nvl(yo.valor_revisao,0),0))   m03" +
		"        ,      sum(decode(to_number(to_char(yo.data,'MM')),4,nvl(yo.valor_revisao,0),0))   m04" +
		"        ,      sum(decode(to_number(to_char(yo.data,'MM')),5,nvl(yo.valor_revisao,0),0))   m05" +
		"        ,      sum(decode(to_number(to_char(yo.data,'MM')),6,nvl(yo.valor_revisao,0),0))   m06" +
		"        ,      sum(decode(to_number(to_char(yo.data,'MM')),7,nvl(yo.valor_revisao,0),0))   m07" +
		"        ,      sum(decode(to_number(to_char(yo.data,'MM')),8,nvl(yo.valor_revisao,0),0))   m08" +
		"        ,      sum(decode(to_number(to_char(yo.data,'MM')),9,nvl(yo.valor_revisao,0),0))   m09" +
		"        ,      sum(decode(to_number(to_char(yo.data,'MM')),10,nvl(yo.valor_revisao,0),0))  m10" +
		"        ,      sum(decode(to_number(to_char(yo.data,'MM')),11,nvl(yo.valor_revisao,0),0))  m11" +
		"        ,      sum(decode(to_number(to_char(yo.data,'MM')),12,nvl(yo.valor_revisao,0),0))  m12      " +
		"FROM    ym_sg_orcamento yo			" +
		"WHERE   yo.linha_id     = 1		" +
		"AND   	 nvl(yo.end_date, sysdate + 1) >= sysdate " +
		"AND     yo.data BETWEEN to_date(concat('01/01/', to_char(:p_dataAp,'YYYY')), 'DD/MM/YYYY') AND :p_dataAp 	" +
		"GROUP BY to_char(yo.data,'YYYY')        " +
		"							" +
		"UNION ALL					" +
		"							" +
		"SELECT  to_char(yl.fechamento_date,'YYYY')  Ano     " +
		"        ,      '5'" +
		"        ,      'Prev Yr'" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),1,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m01" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),2,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m02" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),3,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m03" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),4,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m04" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),5,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m05" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),6,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m06" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),7,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m07" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),8,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m08" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),9,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))   m09" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),10,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))  m10" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),11,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))  m11" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),12,nvl(yc.vl_revisao,0)+nvl(yc.vl_bonificacao,0),0))  m12       " +
		"FROM    ym_sg_lote                  yl		" +
		"        ,    ym_sg_cupom			yc		" +
		"        ,    ym_sg_revisao		    yr		" +
		"        ,    ym_sg_faturamento		yf		" +
		"WHERE   yl.lote_id            = yc.lote_id		" +
		"AND     yc.revisao_id         = yr.revisao_id	" +
		"AND     replace(yf.chassi,'-','') = replace(yc.modelo,'-','')	" +
		"AND     yf.organization_id    = nvl(:p_orgId, yf.organization_id) " +
		"AND     yr.numero_revisao     IN (2,3)		" +
		"AND     yl.status_lote_id     IN (9,11)	" +
		"AND     yc.status_mov_id      =  2		" +
		"AND     yl.fechamento_date BETWEEN to_date(concat('01/01/', to_number(to_char(:p_dataAp,'YYYY')) - 1), 'DD/MM/YYYY') AND add_months(:p_dataAp, -12)	" +
		"AND   yl.lINha_id             = 1		" +
		"AND   	nvl(yl.end_date, sysdate + 1) >= sysdate " +
		"AND   	nvl(yc.end_date, sysdate + 1) >= sysdate " +
		"AND   	nvl(yr.end_date, sysdate + 1) >= sysdate " +
		"AND   	nvl(yf.end_date, sysdate + 1) >= sysdate " +
		"GROUP BY  to_char(yl.fechamento_date,'YYYY')" +
		"						" +
		"UNION ALL				" +
		"						" +
		"SELECT  to_char(yl.fechamento_date,'YYYY')    Ano    " +
		"        ,      '6'" +
		"        ,      '1.000 km'" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),1,1,0))   m01" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),2,1,0))   m02" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),3,1,0))   m03" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),4,1,0))   m04" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),5,1,0))   m05" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),6,1,0))   m06" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),7,1,0))   m07" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),8,1,0))   m08" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),9,1,0))   m09" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),10,1,0))  m10" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),11,1,0))  m11" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),12,1,0))  m12	" +
		"FROM    ym_sg_lote              yl	" +
		"        ,    ym_sg_cupom		yc	" +
		"        ,    ym_sg_revisao		yr	" +
		"        ,    ym_sg_faturamento  yf	" +
		"WHERE   yl.lote_id            = yc.lote_id	" +
		"AND     yc.revisao_id         = yr.revisao_id	" +
		"AND     replace(yf.chassi,'-','') = replace(yc.modelo,'-','')	" +
		"AND     yr.numero_revisao     = 2	" +
		"AND     yf.organization_id    = nvl(:p_orgId,yf.organization_id) " +
		"AND     yl.status_lote_id     IN (9,11)	" +
		"AND     yc.status_mov_id      = 2	" +
		"AND     yl.fechamento_date BETWEEN to_date(concat('01/01/', to_char(:p_dataAp,'YYYY')), 'DD/MM/YYYY') AND :p_dataAp	" +
		"AND     yl.lINha_id           = 1	" +
		"AND   	nvl(yl.end_date, sysdate + 1) >= sysdate " +
		"AND   	nvl(yc.end_date, sysdate + 1) >= sysdate " +
		"AND   	nvl(yr.end_date, sysdate + 1) >= sysdate " +
		"AND   	nvl(yf.end_date, sysdate + 1) >= sysdate " +
		"GROUP BY  to_char(yl.fechamento_date,'YYYY')      " +
		"					" +
		"UNION ALL			" +
		"					" +
		"SELECT  to_char(yl.fechamento_date,'YYYY')    Ano    " +
		"        ,      '7'" +
		"        ,      '5.000 km'" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),1,1,0))   m01" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),2,1,0))   m02" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),3,1,0))   m03" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),4,1,0))   m04" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),5,1,0))   m05" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),6,1,0))   m06" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),7,1,0))   m07" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),8,1,0))   m08" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),9,1,0))   m09" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),10,1,0))  m10" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),11,1,0))  m11" +
		"        ,      sum(decode(to_number(to_char(yl.fechamento_date,'MM')),12,1,0))  m12      " +
		"FROM    ym_sg_lote                  yl	" +
		"        ,    ym_sg_cupom			yc	" +
		"        ,    ym_sg_revisao		    yr	" +
		"        ,    ym_sg_faturamento      yf	" +
		"WHERE   yl.lote_id            = yc.lote_id	" +
		"AND     yc.revisao_id         = yr.revisao_id	" +
		"AND     replace(yf.chassi,'-','') = replace(yc.modelo,'-','')	" +
		"AND     yr.numero_revisao     = 3	" +
		"AND     yf.organization_id    = nvl(:p_orgId,yf.organization_id) " +
		"AND     yl.status_lote_id     IN (9,11)	" +
		"AND     yc.status_mov_id      =  2	" +
		"AND     yl.fechamento_date BETWEEN to_date(concat('01/01/', to_char(:p_dataAp,'YYYY')), 'DD/MM/YYYY') AND :p_dataAp	" +
		"AND     yl.linha_id           = 1	" +
		"AND   	nvl(yl.end_date, sysdate + 1) >= sysdate " +
		"AND   	nvl(yc.end_date, sysdate + 1) >= sysdate " +
		"AND   	nvl(yr.end_date, sysdate + 1) >= sysdate " +
		"AND   	nvl(yf.end_date, sysdate + 1) >= sysdate " +
		"GROUP BY  to_char(yl.fechamento_date,'YYYY')";
	 
		
	/** Retorna query do relatório service report - gráficos 1, 2 e 3
	 *  Parâmetros utlizados no relatório 
	 *  :p_orgId -- Id da empresa
	 *  :p_dataAp -- Data para apuração dos dados
	 * 
	 * @return String 
	 */
	public static String getQueryServiceReport1() {		
		return QUERY_SERVICE_REPORT_PART1;
	}
	
	/** Retorna query do relatório service report - gráficos 4, 5 e 6
	 *  Parâmetros utlizados no relatório 
	 *  :p_orgId -- Id da empresa
	 *  :p_dataAp -- Data para apuração dos dados
	 * 
	 * @return String 
	 */
	public static String getQueryServiceReport2() {
		return QUERY_SERVICE_REPORT_PART2;
	}
	
	/** Retorna query do relatório service report - gráficos 7, 8 e 9
	 *  Parâmetros utlizados no relatório 
	 *  :p_orgId -- Id da empresa
	 *  :p_dataAp -- Data para apuração dos dados
	 * 
	 * @return String 
	 */
	public static String getQueryServiceReport3() {		
		return QUERY_SERVICE_REPORT_PART3;
	}

}

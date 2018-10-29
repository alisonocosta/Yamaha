/* Resource Tecnologia
 * Copyright (c) 2007 - Todos os direitos reservados
 *
 *   .: Projeto.....Yamaha - Sistema de Garantia
 *   .: Objeto......GenerateChartReport.java
 *   .: Criação.....22 de Novembro, 16:00
 *   .: Autor.......Edson Luiz Sumensari
 *   .: Descrição...Classe "GenerateChartReport" - responsável por criar as imagens gráficas.
 */
package br.com.yamaha.sistemagarantia.controller.helper;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

import br.com.resource.infra.exception.BusinessException;
import br.com.resource.infra.exception.ServiceException;
import br.com.resource.infra.utils.NumberUtils;
import br.com.yamaha.sistemagarantia.view.EntityGraficoGarantiaMensalPercentualVO;
import br.com.yamaha.sistemagarantia.view.EntityGraficoGarantiaPagaVO;
import br.com.yamaha.sistemagarantia.view.EntityGraficosIndividuaisVO;
import br.com.yamaha.sistemagarantia.view.EntityImgReportServiceReportVO;
import br.com.yamaha.sistemagarantia.view.RelatorioServiceReportGraphVO;

/** Classe responsável pela geração de imagens para os relatórios gráficos
 * 
 *  Objetos - JfreeChart 
 * 
 * */
public class GenerateChartReport {
	
	/** Constante para definição da orientação Horizontal do gráfico */
	public static final String ORIENTATION_HORIZONTAL = "HORIZONTAL";
	
	/** Constante para definição da orientação Vertical do gráfico */
	public static final String ORIENTATION_VERTICAL   = "VERTICAL";
	
	/** Constante para definição da cor da Barra do gráfico */
	public static final Color PAINT_BAR   = Color.RED;
	
	/** Constante para definição da cor da linha do gráfico */
	public static final Color PAINT_LINE   = Color.BLUE;
	
	/** Este método cria uma imagem de um gráfico de barras combinado comum gráfico de linha
	 * 
	 * @param titleGraphic
	 * 	<code>String</code> Título do Gráfico
	 * 
	 * @param titleAxisX
	 * 	<code>String</code> Título para o eixo X do gráfico de barras
	 * 
	 * @param titleAxisY
	 * 	<code>String</code> Título para o eixo Y do gráfico de barras
	 * 
	 * @param titleAxisLine
	 * 	<code>String</code> Título para o eixo do gráfico de linha
	 * 
	 * @param dataSetAxisBar
	 * 	<code>DefaultCategoryDataset</code> DataSet para o gráfico de barras
	 * 
	 * @param dataSetAxisLine]
	 * 	<code>DefaultCategoryDataset</code> DataSet para o gráfico de linha
	 * 
	 * @param orientationGraph
	 * 	<code>String</code> Valor para a orientação do gráfico - HORINZONTAL ou VERTICAL
	 * 
	 * @return
	 * 	<code>BufferedImage</code>  Imagem com gráfico montado
	 * 
	 * @throws BusinessException
	 * 	Quando ocorrer algum problema com a geração da imagem gráfica
	 * 
	 */
	public static BufferedImage createGraphicBarLineDualAxis(
															String titleGraphic
															, String titleAxisX
															, String titleAxisY
															, String titleAxisLine
															, CategoryDataset dataSetAxisBar
															, CategoryDataset dataSetAxisLine
															, String orientationGraph
															, int width
															, int height
															, Color paintBar
															, Color paintLine
															) throws ServiceException {
				
		BufferedImage buf = null;
        
        try {
        	
        	// Definimos a orientação do gráfico - Horizontal ou vertical
        	PlotOrientation orientation =  ORIENTATION_HORIZONTAL.equalsIgnoreCase(orientationGraph) ? 
        								   PlotOrientation.HORIZONTAL :
        								   PlotOrientation.VERTICAL;
        	
        	// Criação do gráfico de barras padrão
        	JFreeChart chart = ChartFactory.createBarChart(  titleGraphic
            												 , titleAxisX
            												 , titleAxisY
            												 , dataSetAxisBar
            												 , orientation
            												 , true
            												 , true
            												 , true
            												 );
        	
        	// get a reference to the plot for further customisation...
            final CategoryPlot plot = chart.getCategoryPlot();
            
            // set the range axis to display integers only...
            final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
            rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
            rangeAxis.setTickLabelFont(new Font("arial",Font.TRUETYPE_FONT, 9));
            
            // disable bar outlines...
            final BarRenderer renderer = (BarRenderer) plot.getRenderer();
            renderer.setDrawBarOutline(false);
            renderer.setMaximumBarWidth(0.10);
            renderer.setMinimumBarLength(0.05);
            renderer.setBaseItemLabelFont(new Font("arial",Font.TRUETYPE_FONT, 9));
            //Cores das 2 primeiras barras
            renderer.setSeriesPaint(0, paintBar);
	        renderer.setSeriesPaint(1, new Color(102, 153, 255));
            
            // Deixa o label da categoria inclinado em 45º
            final CategoryAxis domainAxis = plot.getDomainAxis();
            domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
            domainAxis.setTickLabelFont(new Font("arial",Font.ITALIC, 8));
            
            // Cria um novo eixo de valores 
            final ValueAxis axis2 = new NumberAxis(titleAxisLine);
            axis2.setStandardTickUnits(NumberAxis.createStandardTickUnits());
            axis2.setTickLabelFont(new Font("arial",Font.TRUETYPE_FONT, 9));
            
            plot.setRangeAxis(1, axis2);
            plot.setDataset(1, dataSetAxisLine);
            plot.mapDatasetToRangeAxis(1, 1);
            
            final CategoryItemRenderer renderer2 = new LineAndShapeRenderer();
            // Cor da linha 
            //renderer2.setSeriesPaint(0, new Color(0, 128, 128));
            renderer2.setSeriesPaint(0, paintLine);
            plot.setRenderer(1, renderer2);

            plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
            
            // Configuramos o título do relatório
            TextTitle title = chart.getTitle();
            title.setFont(new Font("arial",Font.TRUETYPE_FONT, 10));
            
            chart.setBorderVisible(true);
            chart.setBorderPaint(Color.black);
            chart.setBackgroundPaint(new Color(255, 255, 255));
            
            LegendTitle legend = chart.getLegend();
            
            if (legend != null)            
            	legend.setItemFont(new Font("arial",Font.TRUETYPE_FONT, 9));
            
            buf = chart.createBufferedImage(width, height);
		
        } catch (Exception e) {
            throw new ServiceException(e);
        }
        
        //System.out.println("Retornando a imagem!");
        
        return buf;
		
	}
	
	/**
	 * Este método cria uma imagem de um gráfico de barras
	 * 
	 * @param titleGraphic
	 *            <code>String</code> Título do Gráfico
	 * 
	 * @param titleAxisX
	 *            <code>String</code> Título para o eixo X do gráfico de
	 *            barras
	 * 
	 * @param titleAxisY
	 *            <code>String</code> Título para o eixo Y do gráfico de
	 *            barras
	 * 
	 * @param dataSetAxisBar
	 *            <code>DefaultCategoryDataset</code> DataSet para o gráfico
	 *            de barras
	 * 
	 * @param orientationGraph
	 *            <code>String</code> Valor para a orientação do gráfico -
	 *            HORINZONTAL ou VERTICAL
	 * 
	 * @param isLegend
	 *            <code>boolean</code> Valor para a apresentação ou não da
	 *            legenda do gráfico - TRUE para apresentar
	 * 
	 * @return <code>BufferedImage</code> Imagem com gráfico montado
	 * 
	 * @throws ServiceException
	 *             Quando ocorrer algum problema com a geração da imagem gráfica
	 * 
	 */
	public static BufferedImage createGraphicBar(
												String titleGraphic
												, String titleAxisX
												, String titleAxisY
												, CategoryDataset categoryDataset
												, String orientationGraph
												, boolean isLegend
												, int width
												, int height
												) throws ServiceException {
				
		BufferedImage buf = null;
		
		 try {
			 
			/** montagem do gráfico de barras */
	        // Definimos a orientação do gráfico - Horizontal ou vertical
	    	PlotOrientation orientation =  ORIENTATION_HORIZONTAL.equalsIgnoreCase(orientationGraph) ? 
	    								   PlotOrientation.HORIZONTAL :
	    								   PlotOrientation.VERTICAL;
	    	
	    	// Criação do gráfico de barras padrão
	    	JFreeChart chart = ChartFactory.createBarChart(  titleGraphic
	        												 , titleAxisX
	        												 , titleAxisY
	        												 , categoryDataset
	        												 , orientation
	        												 , isLegend
	        												 , true
	        												 , true
	        												 );
	    	
	    	// get a reference to the plot for further customisation...
	        final CategoryPlot plot = chart.getCategoryPlot();
	        
	        // set the range axis to display integers only...
	        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
	        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	        rangeAxis.setTickLabelFont(new Font("arial",Font.TRUETYPE_FONT, 9));
	        
	        // disable bar outlines...
	        final BarRenderer renderer = (BarRenderer) plot.getRenderer();
	        renderer.setDrawBarOutline(false);
	        renderer.setMaximumBarWidth(0.10);
	        renderer.setMinimumBarLength(0.05);
	        renderer.setBaseItemLabelFont(new Font("arial",Font.TRUETYPE_FONT, 9));
	        // Cores par as 3 primeiras barras
	        renderer.setSeriesPaint(0, Color.RED);	              //Azul   
	        renderer.setSeriesPaint(1, new Color(102, 153, 255)); //Azul Claro
	        renderer.setSeriesPaint(2, Color.BLUE); //Verde 
	                    
	        // Fonte da categoria
	        final CategoryAxis domainAxis = plot.getDomainAxis();
	        domainAxis.setTickLabelFont(new Font("arial",Font.TRUETYPE_FONT, 8));
	        
	        // Configuramos o título do relatório
	        TextTitle title = chart.getTitle();
	        title.setFont(new Font("arial",Font.TRUETYPE_FONT, 10));
	        
	        chart.setBorderVisible(true);
	        chart.setBorderPaint(Color.black);
	        chart.setBackgroundPaint(new Color(255, 255, 255));
	        
	        LegendTitle legend = chart.getLegend();
	        
	        if (legend != null)            
	        	legend.setItemFont(new Font("arial",Font.TRUETYPE_FONT, 9));
	        
	        buf = chart.createBufferedImage(width, height);
	        
			
	    } catch (Exception e) {
	        throw new ServiceException(e);
	    }
    
	    return buf;
	}
	
	/**
	 * Este método cria uma imagem de um gráfico de barras (Stacked BAR)
	 * 
	 * @param titleGraphic
	 *            <code>String</code> Título do Gráfico
	 * 
	 * @param titleAxisX
	 *            <code>String</code> Título para o eixo X do gráfico de
	 *            barras
	 * 
	 * @param titleAxisY
	 *            <code>String</code> Título para o eixo Y do gráfico de
	 *            barras
	 * 
	 * @param orientationGraph
	 *            <code>String</code> Valor para a orientação do gráfico -
	 *            HORINZONTAL ou VERTICAL
	 * 
	 * @param isLegend
	 *            <code>boolean</code> Valor para a apresentação ou não da
	 *            legenda do gráfico - TRUE para apresentar
	 *            
	 * 
	 * @return <code>BufferedImage</code> Imagem com gráfico montado
	 * 
	 * @throws ServiceException
	 *             Quando ocorrer algum problema com a geração da imagem gráfica
	 * 
	 */
	public static BufferedImage createGraphicStackedBar(
												String titleGraphic
												, String titleAxisX
												, String titleAxisY
												, CategoryDataset categoryDataset
												, String orientationGraph
												, boolean isLegend
												, int width
												, int height
											) throws ServiceException {
				
		BufferedImage buf = null;
		
		 try {
			 
			/** montagem do gráfico de barras */
	        // Definimos a orientação do gráfico - Horizontal ou vertical
	    	PlotOrientation orientation =  ORIENTATION_HORIZONTAL.equalsIgnoreCase(orientationGraph) ? 
	    								   PlotOrientation.HORIZONTAL :
	    								   PlotOrientation.VERTICAL;
	    	
	    	// Criação do gráfico de barras padrão
	    	JFreeChart chart = ChartFactory.createStackedBarChart(
	    													titleGraphic
	        												 , titleAxisX
	        												 , titleAxisY
	        												 , categoryDataset
	        												 , orientation
	        												 , isLegend
	        												 , true
	        												 , false
	        												 );
	    	
	    	// get a reference to the plot for further customisation...
	        final CategoryPlot plot = chart.getCategoryPlot();
	        
	        // set the range axis to display integers only...
	        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
	        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	        rangeAxis.setTickLabelFont(new Font("arial",Font.TRUETYPE_FONT, 10));	       
	        rangeAxis.setRange(0,100);
	        
	        // disable bar outlines...
	        final BarRenderer renderer = (BarRenderer) plot.getRenderer();
	        renderer.setDrawBarOutline(false);
	        renderer.setMaximumBarWidth(0.10);
	        renderer.setMinimumBarLength(0.05);
	        renderer.setBaseItemLabelFont(new Font("arial",Font.TRUETYPE_FONT, 10));
	        	        
	        // Cores par as 3 primeiras barras
	       // renderer.setSeriesPaint(0, Color.RED);	              //Azul   
	        //renderer.setSeriesPaint(1, new Color(102, 153, 255)); //Azul Claro
	       // renderer.setSeriesPaint(2, Color.BLUE); //Verde 
	                    
	        // Fonte da categoria
	        final CategoryAxis domainAxis = plot.getDomainAxis();
	        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
	        domainAxis.setTickLabelFont(new Font("arial",Font.TRUETYPE_FONT, 10));
	        	        
	        chart.setBorderVisible(true);
	        chart.setBorderPaint(Color.black);
	        chart.setBackgroundPaint(new Color(255, 255, 255));
	        
	        LegendTitle legend = chart.getLegend();
	        
	        if (legend != null)            
	        	legend.setItemFont(new Font("arial",Font.TRUETYPE_FONT, 9));
	        
	        buf = chart.createBufferedImage(width, height);
	        
			
	    } catch (Exception e) {
	        throw new ServiceException(e);
	    }
    
	    return buf;
	}
	
	
	 /**
     * Este método cria uma imagem de um gráfico de pizza
     * 
     * @param titleGraphic
	 *            <code>String</code> Título do Gráfico
	 *
	 * @param pieDataset
	 *            <code>PieDataset</code> DataSet para o gráfico
	 *            
	 * @param isLegend
	 *            <code>boolean</code> Valor para a apresentação ou não da
	 *            legenda do gráfico - TRUE para apresentar
	 *            
	 * @param width
	 * 			  <code>int</code> comprimento da área do gráfico
	 * 
	 * @param heigth
	 * 			  <code>int</code> altura da área do gráfico
	 * 
	 * @return <code>BufferedImage</code> Imagem com gráfico montado
	 * 
	 * @throws ServiceException
	 *             Quando ocorrer algum problema com a geração da imagem gráfica
     */
	public static BufferedImage createGraphicPie3D(
														String titleGraphic
														, PieDataset pieDataset
														, boolean isLegend
														, int width
														, int height
													) throws ServiceException {
    	
		BufferedImage buf = null;
		
		try {
			
			// Criação do gráfico de pizza 3D
			JFreeChart chart = ChartFactory.createPieChart3D(
																titleGraphic,  // título do gráfico
													            pieDataset,    // dados
													            isLegend,      // incluir legenda?
													            true,
													            false
													        );

			PiePlot3D plot = (PiePlot3D) chart.getPlot();
			plot.setDirection(Rotation.CLOCKWISE);
			//plot.setForegroundAlpha(0.2f);
			plot.setNoDataMessage("Não foram localizados dados para apresentação do gráfico!");
			
			// Configuramos o título do relatório
            TextTitle title = chart.getTitle();
            title.setFont(new Font("arial",Font.TRUETYPE_FONT, 10));
            
            chart.setBorderVisible(true);
            chart.setBorderPaint(Color.LIGHT_GRAY);
            chart.setBackgroundPaint(new Color(255, 255, 255));
            
            LegendTitle legend = chart.getLegend();
	        
	        if (legend != null)            
	        	legend.setItemFont(new Font("arial",Font.TRUETYPE_FONT, 9));
			
			buf = chart.createBufferedImage(width, height);
			
		 } catch (Exception e) {
		        throw new ServiceException(e);
		 }
	    
		 return buf;
	}
	
	/* *******************************************************************************************
	 *
	 * Início dos métodos do Relatório de Gráficos Individuais - Totalização	
	 * 				 
	 *********************************************************************************************/
	
	/** Gráfico 1 - Relatórios de Gráficos Individuais
	 * 	Criação do gráfico Faturamento x Garantia
	 *  Gráfico Composto - Faturamento (Barra) x Garantia (Linha)
	 *  	
     *  Os Objetos DefaultCategoryDataset recebe os valores que irão gerar o gráfico 
     * 
     * @param tituloGrafico 
     * 	<code>String</code> Título do gráfico
     * 
     * @param tituloEixoX 
     * 	<code>String</code>  Título do eixo X
     * 
     * @param tituloEixoY 
     * 	<code>String</code> Título do eixo do gráfico de linha
     * 
     * @param listValuesGraph1 
     * 	<code>List</code> Lista com os valores para o gráfico
     * 
     * @return 
     * 	<code>BufferedImage</code> com a imagem do Gráfico gerada
     *     
     */
    public static BufferedImage gerarGraficoFaturamentoGarantiaDualAxis(
    												String titleGrafico
    												, String titleAxisX
    												, String titleAxisY
    												, String titleAxisLine
    												, List listValuesGraph1
    												) throws ServiceException {
    	        
        try {
            
        	DefaultCategoryDataset categoryDatasetFat = new DefaultCategoryDataset();
        	DefaultCategoryDataset categoryDatasetGar = new DefaultCategoryDataset();
        	
        	Iterator itFat = listValuesGraph1.iterator();
            while (itFat.hasNext()) {
            	
            	EntityGraficosIndividuaisVO entityGraficosIndividuais = (EntityGraficosIndividuaisVO) itFat.next();
            	            	
            	if ( EntityGraficosIndividuaisVO.GRAPH_SERIE_GARANTIA.equalsIgnoreCase(entityGraficosIndividuais.getSerie()) ) {
            	            	
            		categoryDatasetGar.addValue( entityGraficosIndividuais.getValue()
						 					 	, EntityGraficosIndividuaisVO.GRAPH_SERIE_GARANTIA
						 					 	, entityGraficosIndividuais.getCategory().toUpperCase()            									
					   							);
            		
            	} else if ( EntityGraficosIndividuaisVO.GRAPH_SERIE_FATURAMENTO.equalsIgnoreCase(entityGraficosIndividuais.getSerie()) ) {
            	
            		categoryDatasetFat.addValue( entityGraficosIndividuais.getValue()
            									, EntityGraficosIndividuaisVO.GRAPH_SERIE_FATURAMENTO
            									, entityGraficosIndividuais.getCategory().toUpperCase()            									
					   							);
            		
            	}
            	
            }
            
            return createGraphicBarLineDualAxis(
            									titleGrafico
            									, titleAxisX
            									, titleAxisY
            									, titleAxisLine
            									, categoryDatasetFat
            									, categoryDatasetGar
            									, ORIENTATION_VERTICAL
            									, 796
            									, 248
            									, PAINT_BAR
            									, PAINT_LINE
            		);
			
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    
    
    
    /** Gráfico 2 - Relatórios de Gráficos Individuais
	 * 	Criação do gráfico Garantia x Índice
	 *  Gráfico Composto - Garantia Mensal (Barra) x Índice (Linha)
	 *  	
     *  Os Objetos DefaultCategoryDataset recebe os valores que irão gerar o gráfico 
     * 
     * @param tituloGrafico 
     * 	<code>String</code> Título do gráfico
     * 
     * @param tituloEixoX 
     * 	<code>String</code>  Título do eixo X
     * 
     * @param tituloEixoY 
     * 	<code>String</code> Título do eixo do gráfico de linha
     * 
     * @param listValuesGraph 
     * 	<code>List</code> Lista com os valores para o gráfico
     * 
     * @return 
     * 	<code>BufferedImage</code> com a imagem do Gráfico gerada
     *     
     */
    public static BufferedImage gerarGraficoGarantiaIndiceDualAxis(
    												String titleGrafico
    												, String titleAxisX
    												, String titleAxisY
    												, String titleAxisLine
    												, List listValuesGraph
    												) throws ServiceException {
    	        
        try {
            
        	DefaultCategoryDataset categoryDatasetGar = new DefaultCategoryDataset();
        	DefaultCategoryDataset categoryDatasetInd = new DefaultCategoryDataset();
        	
        	String fatAnomes = null;
        	double fatValue  = 0;
        	
        	Iterator itFat = listValuesGraph.iterator();
            while (itFat.hasNext()) {
            	
            	EntityGraficosIndividuaisVO entityGraficosIndividuais = (EntityGraficosIndividuaisVO) itFat.next();
            	            	
            	//System.out.println("Categoria:" + entityGraficosIndividuais.getCategory());
            	//System.out.println("Série:" + entityGraficosIndividuais.getSerie());
            	
            	if ( EntityGraficosIndividuaisVO.GRAPH_SERIE_GARANTIA.equals(entityGraficosIndividuais.getSerie()) ) {
            		
            		if ( entityGraficosIndividuais.getCategory().equals(fatAnomes) ) {
            			
	            		categoryDatasetGar.addValue( 	entityGraficosIndividuais.getValue()
								 						, "Garantia"
								 						, entityGraficosIndividuais.getCategory().toUpperCase()            									
							   						);
	            		
	            		double vlGar = NumberUtils.round(entityGraficosIndividuais.getValue().doubleValue(),2);
	                	double vlFat = NumberUtils.round(fatValue,2);
	            		
	            		// Cálculo do índice de Garantia pelo faturamento - (Vl Garantia / Vl faturamento) * 100 (%) 
	                	double indice = (vlGar / vlFat) * 100;
	                	
	                	categoryDatasetInd.addValue( indice
	    						 					 , "Índice"
	    						 					 , entityGraficosIndividuais.getCategory().toUpperCase()            									
	    					   						);
	                	
            		}
            	
            	} else if ( EntityGraficosIndividuaisVO.GRAPH_SERIE_FATURAMENTO.equals(entityGraficosIndividuais.getSerie()) ) {
            		
            		fatAnomes = entityGraficosIndividuais.getCategory();
                	fatValue  = entityGraficosIndividuais.getValue().doubleValue();
            		
            	}
            	
            }
            
            return createGraphicBarLineDualAxis(
            									titleGrafico
            									, titleAxisX
            									, titleAxisY
            									, titleAxisLine
            									, categoryDatasetGar
            									, categoryDatasetInd
            									, ORIENTATION_VERTICAL
            									, 796
            									, 248    
            									, PAINT_BAR
            									, PAINT_LINE
            									);
			
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    
    /** Gráfico 3 - Relatórios de Gráficos Individuais
	 * 	Criação do gráfico Faturamento x Garantia (Qtde)
	 *  Gráfico Composto - Faturamento (Barra) x Garantia (Linha)
	 *  	
     *  Os Objetos DefaultCategoryDataset recebe os valores que irão gerar o gráfico 
     * 
     * @param tituloGrafico 
     * 	<code>String</code> Título do gráfico
     * 
     * @param tituloEixoX 
     * 	<code>String</code>  Título do eixo X
     * 
     * @param tituloEixoY 
     * 	<code>String</code> Título do eixo do gráfico de linha
     * 
     * @param listValuesGraph 
     * 	<code>List</code> Lista com os valores para o gráfico
     * 
     * @return 
     * 	<code>BufferedImage</code> com a imagem do Gráfico gerada
     *     
     */
    public static BufferedImage gerarGraficoFaturamentoQtdeGarantiaDualAxis(
    												String titleGrafico
    												, String titleAxisX
    												, String titleAxisY
    												, String titleAxisLine
    												, List listValuesGraph
    												) throws ServiceException {
    	        
        try {
            
        	DefaultCategoryDataset categoryDatasetFat = new DefaultCategoryDataset();
        	DefaultCategoryDataset categoryDatasetGar = new DefaultCategoryDataset();
        	
        	Iterator itFat = listValuesGraph.iterator();
            while (itFat.hasNext()) {
            	
            	EntityGraficosIndividuaisVO entityGraficosIndividuais = (EntityGraficosIndividuaisVO) itFat.next();
            	            	
            	//System.out.println("Categoria:" + entityGraficosIndividuais.getCategory());
            	
            	categoryDatasetFat.addValue( entityGraficosIndividuais.getSecondValue()
            								 , "Faturamento"
            								 , entityGraficosIndividuais.getCategory().toUpperCase()            									
            							   );
            	
            	categoryDatasetGar.addValue( entityGraficosIndividuais.getFirstValue()
											 , "Garantia (Qtde)"
											 , entityGraficosIndividuais.getCategory().toUpperCase()            									
										   );
            	
            }
            
            return createGraphicBarLineDualAxis(
            									titleGrafico
            									, titleAxisX
            									, titleAxisY
            									, titleAxisLine
            									, categoryDatasetFat
            									, categoryDatasetGar
            									, ORIENTATION_VERTICAL
            									, 796
            									, 248   
            									, PAINT_BAR
            									, PAINT_LINE
            		);
			
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    
    /** Gráfico 4 - Relatórios de Gráficos Individuais
	 * 	Criação do gráfico GARANTIA MENSAL x ÍNDICE DE GARANTIA (QTD)
	 *  Gráfico Composto - GARANTIA MENSAL (Barra) x ÍNDICE DE GARANTIA (Linha)
	 *  	
     *  Os Objetos DefaultCategoryDataset recebe os valores que irão gerar o gráfico 
     * 
     * @param tituloGrafico 
     * 	<code>String</code> Título do gráfico
     * 
     * @param tituloEixoX 
     * 	<code>String</code>  Título do eixo X
     * 
     * @param tituloEixoY 
     * 	<code>String</code> Título do eixo do gráfico de linha
     * 
     * @param listValuesGraph 
     * 	<code>List</code> Lista com os valores para o gráfico
     * 
     * @return 
     * 	<code>BufferedImage</code> com a imagem do Gráfico gerada
     *     
     */
    public static BufferedImage gerarGraficoGarantiaMensalIndiceQtdeDualAxis(
    												String titleGrafico
    												, String titleAxisX
    												, String titleAxisY
    												, String titleAxisLine
    												, List listValuesGraph
    												) throws ServiceException {
    	        
        try {
            
        	DefaultCategoryDataset categoryDatasetGarM = new DefaultCategoryDataset();
        	DefaultCategoryDataset categoryDatasetIndG = new DefaultCategoryDataset();
        	
        	Iterator itFat = listValuesGraph.iterator();
            while (itFat.hasNext()) {
            	
            	EntityGraficosIndividuaisVO entityGraficosIndividuais = (EntityGraficosIndividuaisVO) itFat.next();
            	
            	
            	//System.out.println("Categoria:" + entityGraficosIndividuais.getCategory());
            	
            	categoryDatasetGarM.addValue( entityGraficosIndividuais.getSecondValue()
            								 , "Garantia Mensal"
            								 , entityGraficosIndividuais.getCategory().toUpperCase()            									
            							   );
            	
            	double vlGar = NumberUtils.round(entityGraficosIndividuais.getFirstValue().doubleValue(),2);
            	double vlFat = NumberUtils.round(entityGraficosIndividuais.getSecondValue().doubleValue(),2);
            	 
            	double indice = (vlGar / vlFat) * 100;
            	
            	categoryDatasetIndG.addValue( indice
						 					 , "Índice Garantia"
						 					 , entityGraficosIndividuais.getCategory().toUpperCase()            									
					   						);
            	
            }
            
            return createGraphicBarLineDualAxis(
            									titleGrafico
            									, titleAxisX
            									, titleAxisY
            									, titleAxisLine
            									, categoryDatasetGarM
            									, categoryDatasetIndG
            									, ORIENTATION_VERTICAL
            									, 796
            									, 248
            									, PAINT_BAR
            									, PAINT_LINE
            		);
			
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    
    /** Gráfico 5 - Relatórios de Gráficos Individuais
	 * 	Criação do gráfico VALOR ACUMULADO GARANTIA 0 KM x INDICE GARANTIA 0 KM
	 *  Gráfico Composto - VALOR ACUMULADO GARANTIA 0 KM (Barra) x ÍNDICE GARANTIA 0 KM (Linha)
	 *  	
     *  Os Objetos DefaultCategoryDataset recebe os valores que irão gerar o gráfico 
     * 
     * @param tituloGrafico 
     * 	<code>String</code> Título do gráfico
     * 
     * @param tituloEixoX 
     * 	<code>String</code>  Título do eixo X
     * 
     * @param tituloEixoY 
     * 	<code>String</code> Título do eixo do gráfico de linha
     * 
     * @param listValuesGraph 
     * 	<code>List</code> Lista com os valores para o gráfico
     * 
     * @return 
     * 	<code>BufferedImage</code> com a imagem do Gráfico gerada
     *     
     */
    public static BufferedImage gerarGraficoAcumuladoGarantiaIndiceDualAxis(
    												String titleGrafico
    												, String titleAxisX
    												, String titleAxisY
    												, String titleAxisLine
    												, List listValuesGraph
    												) throws ServiceException {
    	        
        try {
        	
        	DefaultCategoryDataset categoryDatasetGar = new DefaultCategoryDataset();
        	DefaultCategoryDataset categoryDatasetInd = new DefaultCategoryDataset();
        	
        	String garZeroAnomes = null;
        	double vlGarZero  	 = 0;
        	double vlGar		 = 0;
        	double indice		 = 0;
        	
        	Iterator itFat = listValuesGraph.iterator();
            while (itFat.hasNext()) {
            	
            	EntityGraficosIndividuaisVO entityGraficosIndividuais = (EntityGraficosIndividuaisVO) itFat.next();
            	            	
            	//System.out.println("Categoria:" + entityGraficosIndividuais.getCategory());
            	//System.out.println("Série:" + entityGraficosIndividuais.getSerie());
            	
            	if ( EntityGraficosIndividuaisVO.GRAPH_SERIE_GARANTIA.equals(entityGraficosIndividuais.getSerie()) ) {
            		
            		if ( entityGraficosIndividuais.getCategory().equals(garZeroAnomes) ) {
            			
            			//System.out.println(" --------------> Anomes:" + entityGraficosIndividuais.getCategory());
            			//System.out.println(" Valor Gar:" + entityGraficosIndividuais.getValue());
            			//System.out.println(" Valor GarZero:" + vlGarZero);
            			
            			vlGar = NumberUtils.round(entityGraficosIndividuais.getValue().doubleValue(),2);
            			
            			categoryDatasetGar.addValue( 	vlGar
								 						, "Acumulado Garantia"
								 						, entityGraficosIndividuais.getCategory().toUpperCase()            									
							   						);
            			
            			if ( vlGar != 0 ) {     
		            		
		            		// Cálculo do índice de Garantia pelo faturamento - (Vl Garantia / Vl faturamento) * 100 (%) 
		                	indice = (vlGarZero / vlGar) * 100;
		                	
            			}
            			
            			//System.out.println(" Valor Indice:" + indice);
            			categoryDatasetInd.addValue( indice
								 					 , "Índice Garantia"
								 					 , entityGraficosIndividuais.getCategory().toUpperCase()            									
							   						);
	                	
            		}
            	
            	} else if ( EntityGraficosIndividuaisVO.GRAPH_SERIE_GARANTIA_ZERO.equals(entityGraficosIndividuais.getSerie()) ) {
            		
            		garZeroAnomes = entityGraficosIndividuais.getCategory();
            		vlGarZero     = entityGraficosIndividuais.getValue().doubleValue();
            		
            	}
            	
            }
            
            return createGraphicBarLineDualAxis(
            									titleGrafico
            									, titleAxisX
            									, titleAxisY
            									, titleAxisLine
            									, categoryDatasetGar
            									, categoryDatasetInd
            									, ORIENTATION_VERTICAL
            									, 796
            									, 248
            									, PAINT_BAR
            									, PAINT_LINE
            		);
			
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    
    /* *******************************************************************************************
	 *
	 * Fim dos métodos do Relatório de Gráficos Individuais - Totalização	
	 * 				 
	 *********************************************************************************************/
    
    /* *******************************************************************************************
	 *
	 * Início dos métodos do Relatório de Gráficos Individuais - Por Modelo	
	 * 				 
	 *********************************************************************************************/
    
    /** Gráfico 1 - Relatórios de Gráficos Individuais por linha
	 * 	Criação do gráfico Faturamento x Garantia
	 *  Gráfico Composto - Faturamento (Barra) x Garantia (Linha)
	 *  	
     *  Os Objetos DefaultCategoryDataset recebe os valores que irão gerar o gráfico 
     * 
     * @param tituloGrafico 
     * 	<code>String</code> Título do gráfico
     * 
     * @param tituloEixoX 
     * 	<code>String</code>  Título do eixo X
     * 
     * @param tituloEixoY 
     * 	<code>String</code> Título do eixo do gráfico de linha
     * 
     * @param listValuesGraph1 
     * 	<code>List</code> Lista com os valores para o gráfico
     * 
     * @return 
     * 	<code>BufferedImage</code> com a imagem do Gráfico gerada
     *     
     */
    public static BufferedImage gerarGraficoFaturamentoGarantiaLinhaDualAxis(
    												String titleGrafico
    												, String titleAxisX
    												, String titleAxisY
    												, String titleAxisLine
    												, List listValuesGraph1
    												) throws ServiceException {
    	        
        try {
            
        	DefaultCategoryDataset categoryDatasetFat = new DefaultCategoryDataset();
        	DefaultCategoryDataset categoryDatasetGar = new DefaultCategoryDataset();
        	
        	String category = "";
        	boolean hasGarantia    = false;
        	boolean hasFaturamento = false;
        	int contSerie = 0;
        	
        	Iterator itFat = listValuesGraph1.iterator();
            for( int i = 1 ; itFat.hasNext() ; i++ ) {
            	
            	//System.out.println("Entrada no Loop " + i );
            	//System.out.println("Valores - contSerie:" + contSerie + " - hasGarantia:" + hasGarantia + " - hasFaturamento:" + hasFaturamento);
            	EntityGraficosIndividuaisVO entityGraficosIndividuais = (EntityGraficosIndividuaisVO) itFat.next();
            	             	
            	if (!category.equalsIgnoreCase(entityGraficosIndividuais.getCategory().toUpperCase()) ) {
            		
            		if ( contSerie == 1 ) {
	            		if ( !hasFaturamento ) {
	            			
	            			//System.out.println("Categoria Faturamento com Zero:" + category);
	            			categoryDatasetFat.addValue( 0 , "FATURAMENTO" , category );
	            			
	            		} else if (!hasGarantia ) {
	            			
	            			//System.out.println("Categoria Garantia com Zero:" + category);
	            			categoryDatasetGar.addValue( 0 , "GARANTIA" , category );           			
	            			
	            		}
            		}
            		
            		category       = entityGraficosIndividuais.getCategory().toUpperCase();
            		hasGarantia    = false;
                	hasFaturamento = false;
                	contSerie = 1;
                	
            	} else 
            		contSerie++;
            	
            	
            	if ( EntityGraficosIndividuaisVO.GRAPH_SERIE_FATURAMENTO.equalsIgnoreCase(entityGraficosIndividuais.getSerie()) ) {
	            	
            		hasFaturamento = true;
            		
            		//System.out.println("Categoria Faturamento:" + entityGraficosIndividuais.getCategory().toUpperCase());
            		categoryDatasetFat.addValue( entityGraficosIndividuais.getValue()
	            								 , entityGraficosIndividuais.getSerie().toUpperCase()
	            								 , entityGraficosIndividuais.getCategory().toUpperCase()            									
	            							   );
            	} else if ( EntityGraficosIndividuaisVO.GRAPH_SERIE_GARANTIA.equalsIgnoreCase(entityGraficosIndividuais.getSerie()) ) {
	            	
            		hasGarantia = true;
            		
            		//System.out.println("Categoria Garantia:" + entityGraficosIndividuais.getCategory().toUpperCase());
            		categoryDatasetGar.addValue( entityGraficosIndividuais.getValue()
	            								 , entityGraficosIndividuais.getSerie().toUpperCase()
	            								 , entityGraficosIndividuais.getCategory().toUpperCase()            									
	            								);
            	}
            	
            	
            }
            
            if ( contSerie == 1 ) {
        		if ( !hasFaturamento ) {
        			
        			//System.out.println("Categoria Faturamento com Zero:" + category);
        			categoryDatasetFat.addValue( 0 , "FATURAMENTO" , category );
        			
        		} else if (!hasGarantia ) {
        			
        			//System.out.println("Categoria Garantia com Zero:" + category);
        			categoryDatasetGar.addValue( 0 , "GARANTIA" , category );           			
        			
        		}
    		}
            
            return createGraphicBarLineDualAxis(
	            									titleGrafico
	            									, titleAxisX
	            									, titleAxisY
	            									, titleAxisLine
	            									, categoryDatasetFat
	            									, categoryDatasetGar
	            									, ORIENTATION_VERTICAL
	            									, 796
	            									, 248
	            									, PAINT_BAR
	            									, PAINT_LINE
            									);
			
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    
    /** Gráfico 2 - Relatórios de Gráficos Individuais Linha
	 * 	Criação do gráfico Garantia x Índice por modelo
	 *  Gráfico Composto - Garantia Mensal (Barra) x Índice (Linha)
	 *  	
     *  Os Objetos DefaultCategoryDataset recebe os valores que irão gerar o gráfico 
     * 
     * @param tituloGrafico 
     * 	<code>String</code> Título do gráfico
     * 
     * @param tituloEixoX 
     * 	<code>String</code>  Título do eixo X
     * 
     * @param tituloEixoY 
     * 	<code>String</code> Título do eixo do gráfico de linha
     * 
     * @param listValuesGraph 
     * 	<code>List</code> Lista com os valores para o gráfico
     * 
     * @return 
     * 	<code>BufferedImage</code> com a imagem do Gráfico gerada
     *     
     */
    public static BufferedImage gerarGraficoGarantiaIndiceLinhaDualAxis(
    												String titleGrafico
    												, String titleAxisX
    												, String titleAxisY
    												, String titleAxisLine
    												, List listValuesGraph
    												) throws ServiceException {
    	        
        try {
            
        	DefaultCategoryDataset categoryDatasetGar = new DefaultCategoryDataset();
        	DefaultCategoryDataset categoryDatasetInd = new DefaultCategoryDataset();
        	
        	String  category       = "";
        	int contSerie 		   = 1;
        	double valueFat        = 0;
        	double valueGar        = 0;
        	
        	Iterator itValues = listValuesGraph.iterator();
            for ( int i = 1 ; itValues.hasNext() ; i++ ) {
            	
            	//System.out.println("/******************* LOOP " + i + " *************************************/");
            	
            	//System.out.println("Valores - contSerie:" + contSerie );
            	
            	EntityGraficosIndividuaisVO entityGraficosIndividuais = (EntityGraficosIndividuaisVO) itValues.next();
            	            	
            	//System.out.println("Categoria anterior:" + category + " - Categoria atual:" + entityGraficosIndividuais.getCategory());
            	
            	//System.out.println("Valor - Faturamento:" + entityGraficosIndividuais.getFirstValue());
            	//System.out.println("Valor - Garantia:" + entityGraficosIndividuais.getSecondValue());
            	            	            	            	             	
            	if ( category.equalsIgnoreCase(entityGraficosIndividuais.getCategory().toUpperCase()) && contSerie == 2 ) {
            		
            		if ( EntityGraficosIndividuaisVO.GRAPH_SERIE_FATURAMENTO.equalsIgnoreCase(entityGraficosIndividuais.getSerie()) ) {
            			
            			double fat = NumberUtils.round(entityGraficosIndividuais.getFirstValue().doubleValue(),2);
            			
            			if ( valueGar == 0 ) {
            				
            				//System.out.println("Categoria Garantia com Zero:" + category);
	            			categoryDatasetInd.addValue( 0 , "ÍNDICE" , category ); 
            				
	            			categoryDatasetGar.addValue( valueGar , "GARANTIA" , category );
            				
            			} else {
            				
            				//System.out.println("Indice da categoria:" + category + " - GAR:" + valueGar + " - Fat:" + fat);
            				categoryDatasetInd.addValue( (valueGar/fat) * 100 , "ÍNDICE" , category );       
            				
            				categoryDatasetGar.addValue( valueGar , "GARANTIA" , category ); 
            				
            			}            			
            			
            		} else if ( EntityGraficosIndividuaisVO.GRAPH_SERIE_GARANTIA.equalsIgnoreCase(entityGraficosIndividuais.getSerie()) ) {
        				            			
            			double gar = NumberUtils.round(entityGraficosIndividuais.getSecondValue().doubleValue(),2);
            			
            			if ( valueFat == 0 ) {
            				
            				//System.out.println("Categoria Faturamento com Zero:" + category);
	            			categoryDatasetInd.addValue( 0 , "ÍNDICE" , category ); 
            				
	            			categoryDatasetGar.addValue( gar , "GARANTIA" , category );
            				
            			} else {
            				
            				//System.out.println("Indice da categoria:" + category + " - GAR:" + gar + " - Fat:" + valueFat);
            				categoryDatasetInd.addValue( (gar/valueFat) * 100 , "ÍNDICE" , category );       
            				
            				categoryDatasetGar.addValue( gar , "GARANTIA" , category );               				
            				
            			} 
            			
            			category  = entityGraficosIndividuais.getCategory().toUpperCase();
                    	contSerie = 1;
                    	valueFat  = 0;
                    	valueGar  = 0;
        				
        			}             		
            		
            	} else if ( contSerie == 1 ) {
            		
            		if ( EntityGraficosIndividuaisVO.GRAPH_SERIE_FATURAMENTO.equalsIgnoreCase(entityGraficosIndividuais.getSerie()) ) {
            			
            			valueFat = NumberUtils.round(entityGraficosIndividuais.getFirstValue().doubleValue(),2);
            			
            		} else if ( EntityGraficosIndividuaisVO.GRAPH_SERIE_GARANTIA.equalsIgnoreCase(entityGraficosIndividuais.getSerie()) ) {
			            
            			valueGar = NumberUtils.round(entityGraficosIndividuais.getSecondValue().doubleValue(),2);
            		}
            		contSerie++;
            	}	
            	
            	category = entityGraficosIndividuais.getCategory();
            	//System.out.println("/********************************************************/");
            }
            
            return createGraphicBarLineDualAxis(
            									titleGrafico
            									, titleAxisX
            									, titleAxisY
            									, titleAxisLine
            									, categoryDatasetGar
            									, categoryDatasetInd
            									, ORIENTATION_VERTICAL
            									, 796
            									, 248    
            									, PAINT_BAR
            									, PAINT_LINE
            );
			
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    
    
    /* *******************************************************************************************
	 *
	 * Fim dos métodos do Relatório de Gráficos Individuais - Por Modelo	
	 * 				 
	 *********************************************************************************************/
    
    /** Gráfico - Relatórios de Gráficos Garantia Mensal Percentual
	 *  Gráfico Stacked Bar
	 *  	
     *  Os Objetos DefaultCategoryDataset recebe os valores que irão gerar o gráfico 
     * 
     * @param tituloGrafico 
     * 	<code>String</code> Título do gráfico
     * 
     * @param tituloEixoX 
     * 	<code>String</code>  Título do eixo X
     * 
     * @param tituloEixoY 
     * 	<code>String</code> Título do eixo do gráfico de linha
     * 
     * @param listValuesGraph 
     * 	<code>List</code> Lista com os valores para o gráfico
     * 
     * @return 
     * 	<code>BufferedImage</code> com a imagem do Gráfico gerada
     *     
     */
    public static BufferedImage gerarGraficoGarantiaMensalStackedBar(
    												String titleGrafico
    												, String titleAxisX
    												, String titleAxisY
    												, List listValuesGraph
    												) throws ServiceException {
    	        
        try {
            
        	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        	
        	Iterator it = listValuesGraph.iterator();
        	EntityGraficoGarantiaMensalPercentualVO entityGraficoGarantiaMensalPercentual = null;
        	EntityGraficoGarantiaMensalPercentualVO entityGraficoGarantiaMensalGrupo = new EntityGraficoGarantiaMensalPercentualVO();
        	
        	List valuesGraphGrupo = new ArrayList();
        	double total       = 0;
        	String categ       = "";
        	
        	// Vamos calcular o valor total
        	while ( it.hasNext() ) {
        		
        		entityGraficoGarantiaMensalPercentual = (EntityGraficoGarantiaMensalPercentualVO) it.next();
        		categ = entityGraficoGarantiaMensalPercentual.getCategory();
        		
        		//System.out.println("Categoria:" + categ + " - Total:" + total);
        		
        		if ( categ.equalsIgnoreCase( entityGraficoGarantiaMensalGrupo.getCategory() ) ||
        				"".equals(entityGraficoGarantiaMensalGrupo.getCategory())	
        			){
       				
        			total += entityGraficoGarantiaMensalPercentual.getValue().doubleValue();
        			entityGraficoGarantiaMensalGrupo.setCategory(categ);
        			
        		} else { 
        			
        			entityGraficoGarantiaMensalGrupo.setValue(BigDecimal.valueOf(total));
        			valuesGraphGrupo.add(entityGraficoGarantiaMensalGrupo);
        			
        			entityGraficoGarantiaMensalGrupo = new EntityGraficoGarantiaMensalPercentualVO();
        			total = 0;        			
        			entityGraficoGarantiaMensalGrupo.setCategory(categ);
        			total += entityGraficoGarantiaMensalPercentual.getValue().doubleValue();
        		}
        	}
        	
        	//System.out.println("Tamanho Grupo:" + valuesGraphGrupo.size() );
        	
        	Iterator itG = valuesGraphGrupo.iterator();
        	double percent = 0;
        	while ( itG.hasNext() ) {
        	
        		entityGraficoGarantiaMensalGrupo = (EntityGraficoGarantiaMensalPercentualVO) itG.next();
	        	// Agora montamos o dataset
	        	it = listValuesGraph.iterator();
	        	
	        	while ( it.hasNext() ) {
	        		
	        		entityGraficoGarantiaMensalPercentual = (EntityGraficoGarantiaMensalPercentualVO) it.next();
	        		
	        		//System.out.println("Serie:" 		 + entityGraficoGarantiaMensalPercentual.getSerie()  + " - Valor:" + entityGraficoGarantiaMensalPercentual.getValue());
	        		//System.out.println("Category line:"  + entityGraficoGarantiaMensalPercentual.getCategory() );
	        		//System.out.println("Category Grupo:" + entityGraficoGarantiaMensalGrupo.getCategory() );
	        				
	        		if ( entityGraficoGarantiaMensalPercentual.getCategory().equalsIgnoreCase(entityGraficoGarantiaMensalGrupo.getCategory())) {
	        			
	        			percent = entityGraficoGarantiaMensalPercentual.getValue().doubleValue() * 100 / entityGraficoGarantiaMensalGrupo.getValue().doubleValue();
	        			
	        			dataset.addValue(
	        					percent
		        				, entityGraficoGarantiaMensalPercentual.getSerie()
		        				, entityGraficoGarantiaMensalPercentual.getCategory()
		        		);
	        		}	        		
	        	}
        	}
        	
        	return createGraphicStackedBar(
        									titleGrafico
        									, titleAxisX
        									, titleAxisY
        									, dataset
        									, ORIENTATION_VERTICAL
        									, true
        									, 796
        									, 520  
        								);	
        	
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }    
    
    /**
	 * Gráfico - Relatório de Gráficos Service Report
	 * 
	 * @param listValuesReport
	 *            <code>List</code> Lista de valores criação dos DataSet das
	 *            imagens
	 * 
	 * @param mes
	 *            <code>String</code> Mês para o gráfico accumulate
	 * 
	 * @return <code>List</code> de entidades para montagem do relatório
	 * 
	 * @exception <code>ServiceException</code> Caso ocorre algum problema na
	 *                renderização das imagens dos gráficos
	 * 
	 */
    public static List gerarGraficoServiceReport( List listValuesReport, String mes, String title ) throws ServiceException {
    	
    	EntityImgReportServiceReportVO graphVO = null;
    	List resultsGraf = new ArrayList();
    	
    	// Gráfico 1a
		// Criação do gráfico CKD + CBU TOTAL WARRANTY PAYMENT (R$)
		// Accumulate
    	BufferedImage   bImageGraph1a = 
    		GenerateChartReport.gerarGraficoServiceReport1a(
										    				"Accumulated (Fm " + mes + ")"
															, ""
															, ""
															, listValuesReport
															); 
    	
    	// Gráfico 1b
		// Criação do gráfico CKD + CBU TOTAL WARRANTY PAYMENT (R$)
		// by Month
    	BufferedImage   bImageGraph1b = 
    		GenerateChartReport.gerarGraficoServiceReport1b(
										    				"by Month"
															, ""
															, ""
															, ""
															, listValuesReport
															); 
    	graphVO = new EntityImgReportServiceReportVO();
    	graphVO.setTitulo(title);
    	graphVO.setImgAccum(bImageGraph1a);
    	graphVO.setImgBYMon(bImageGraph1b);
    	resultsGraf.add(graphVO);
    	
    	return resultsGraf;
    	
    }

	
	/**
	 * Gráfico 1 - Relatórios de Gráficos Service Report 1a
	 * 
	 * O Objeto DefaultCategoryDataset recebe os valores que irão gerar o
	 * gráfico
	 * 
	 * @param tituloGrafico
	 *            <code>String</code> Título do gráfico
	 * 
	 * @param tituloEixoX
	 *            <code>String</code> Título do eixo X
	 * 
	 * @param listValuesGraph
	 *            <code>List</code> Lista com os valores para o gráfico
	 * 
	 * @return <code>BufferedImage</code> com a imagem do Gráfico gerada
	 * 
	 */
    public static BufferedImage gerarGraficoServiceReport1a(
    												String titleGrafico
    												, String titleAxisX
    												, String titleAxisY
    												, List listValuesGraph
    												) throws ServiceException {
    	    
    	/** Montagem do DataSet do gráfico */
    	DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
    	
    	Iterator it = listValuesGraph.iterator();
        while (it.hasNext()) {
        	
        	RelatorioServiceReportGraphVO relatorioServiceReport = (RelatorioServiceReportGraphVO) it.next();
        	
        	
        	//System.out.println("Categoria:" + entityGraficosIndividuais.getCategory());
        	
        	String serie = relatorioServiceReport.getItem().equalsIgnoreCase(RelatorioServiceReportGraphVO.ITEM_PREV) 
        					? relatorioServiceReport.getAno()
        					: relatorioServiceReport.getItem();
        	
        	System.out.println("Acumulado:" + relatorioServiceReport.getAcumulado());
        	System.out.println("Item:"      + relatorioServiceReport.getItem());
        	System.out.println("Serie:"     + serie);
        	
        	String item = relatorioServiceReport.getItem();
        	
        	if ( item.equalsIgnoreCase(RelatorioServiceReportGraphVO.ITEM_BUDGET) 
        		 || item.equalsIgnoreCase(RelatorioServiceReportGraphVO.ITEM_PREV) 
        		 || item.equalsIgnoreCase(RelatorioServiceReportGraphVO.ITEM_RESULT) ) {
        		
            	categoryDataset.addValue( NumberUtils.trunc(relatorioServiceReport.getAcumulado(),2)
            								 , relatorioServiceReport.getItem()
            								 , serie          									
            							 );
        	}
        	
        }
        
        return  GenerateChartReport.createGraphicBar(
										    			titleGrafico
										    			, titleAxisX
										    			, titleAxisY
										    			, categoryDataset 
										    			, ORIENTATION_VERTICAL
										    			, false
		            									, 200
		            									, 248
				);
            
    }
    
    /**
	 * Gráfico 1 - Relatórios de Gráficos Service Report 1b
	 * 
	 * O Objeto DefaultCategoryDataset recebe os valores que irão gerar o
	 * gráfico
	 * 
	 * @param tituloGrafico
	 *            <code>String</code> Título do gráfico
	 * 
	 * @param tituloEixoX
	 *            <code>String</code> Título do eixo X
	 *            
	 * @param titleAxisLine
	 *            <code>String</code> Título do eixo do gráfico de linha           
	 * 
	 * @param listValuesGraph
	 *            <code>List</code> Lista com os valores para o gráfico
	 * 
	 * @return <code>BufferedImage</code> com a imagem do Gráfico gerada
	 * 
	 */
    public static BufferedImage gerarGraficoServiceReport1b(
    												String titleGrafico
    												, String titleAxisX
    												, String titleAxisY
    												, String titleAxisLine
    												, List listValuesGraph
    												) throws ServiceException {
    	            
    	// DataSet do gráfico de linha 
    	DefaultCategoryDataset categoryDatasetLine = new DefaultCategoryDataset();
    	// DataSet do gráfico de Barra
    	DefaultCategoryDataset categoryDatasetBarr = new DefaultCategoryDataset();
    	
    	Iterator it = listValuesGraph.iterator();
        while (it.hasNext()) {
        	
        	RelatorioServiceReportGraphVO relatorioServiceReport = (RelatorioServiceReportGraphVO) it.next();
        	
        	String serie = relatorioServiceReport.getItem().equalsIgnoreCase(RelatorioServiceReportGraphVO.ITEM_PREV) 
        					? relatorioServiceReport.getAno()
        					: relatorioServiceReport.getItem();
        	
        	// Aqui comparamos o valor da coluna item
        	// A montagem do Dataset para o gráfico de linha será feita quando o item for Prev Yr
        	if ( relatorioServiceReport.getItem().equalsIgnoreCase(RelatorioServiceReportGraphVO.ITEM_PREV) ) {
        		
        		System.out.println("Valor 01:"  + relatorioServiceReport.getMes01());
            	System.out.println("Item:"      + relatorioServiceReport.getItem());
            	System.out.println("Serie:"     + serie);
        		
            	categoryDatasetLine.addValue( relatorioServiceReport.getMes01(), serie, "01" );
            	categoryDatasetLine.addValue( relatorioServiceReport.getMes02(), serie, "02" );
            	categoryDatasetLine.addValue( relatorioServiceReport.getMes03(), serie, "03" );
            	categoryDatasetLine.addValue( relatorioServiceReport.getMes04(), serie, "04" );
            	categoryDatasetLine.addValue( relatorioServiceReport.getMes05(), serie, "05" );
            	categoryDatasetLine.addValue( relatorioServiceReport.getMes06(), serie, "06" );
            	categoryDatasetLine.addValue( relatorioServiceReport.getMes07(), serie, "07" );
            	categoryDatasetLine.addValue( relatorioServiceReport.getMes08(), serie, "08" );
            	categoryDatasetLine.addValue( relatorioServiceReport.getMes09(), serie, "09" );
            	categoryDatasetLine.addValue( relatorioServiceReport.getMes10(), serie, "10" );
            	categoryDatasetLine.addValue( relatorioServiceReport.getMes11(), serie, "11" );
            	categoryDatasetLine.addValue( relatorioServiceReport.getMes12(), serie, "12" );  
        		
        	// A montagem do Dataset para gráfico de Barras será feita quando o item for Result ou Budget
        	} else if ( relatorioServiceReport.getItem().equalsIgnoreCase(RelatorioServiceReportGraphVO.ITEM_BUDGET)
        				|| relatorioServiceReport.getItem().equalsIgnoreCase(RelatorioServiceReportGraphVO.ITEM_RESULT) ){
        		
        		System.out.println("Valor 01:"  + relatorioServiceReport.getMes01());
            	System.out.println("Item:"      + relatorioServiceReport.getItem());
            	System.out.println("Serie:"     + serie);
            	
            	categoryDatasetBarr.addValue( relatorioServiceReport.getMes01(), serie, "01" );
            	categoryDatasetBarr.addValue( relatorioServiceReport.getMes02(), serie, "02" );
            	categoryDatasetBarr.addValue( relatorioServiceReport.getMes03(), serie, "03" );
            	categoryDatasetBarr.addValue( relatorioServiceReport.getMes04(), serie, "04" );
            	categoryDatasetBarr.addValue( relatorioServiceReport.getMes05(), serie, "05" );
            	categoryDatasetBarr.addValue( relatorioServiceReport.getMes06(), serie, "06" );
            	categoryDatasetBarr.addValue( relatorioServiceReport.getMes07(), serie, "07" );
            	categoryDatasetBarr.addValue( relatorioServiceReport.getMes08(), serie, "08" );
            	categoryDatasetBarr.addValue( relatorioServiceReport.getMes09(), serie, "09" );
            	categoryDatasetBarr.addValue( relatorioServiceReport.getMes10(), serie, "10" );
            	categoryDatasetBarr.addValue( relatorioServiceReport.getMes11(), serie, "11" );
            	categoryDatasetBarr.addValue( relatorioServiceReport.getMes12(), serie, "12" );
        		
        	}
        	
        }
        
        return  GenerateChartReport.createGraphicBarLineDualAxis(
										    			titleGrafico
										    			, titleAxisX
										    			, titleAxisY
										    			, titleAxisLine
										    			, categoryDatasetBarr
										    			, categoryDatasetLine
										    			, ORIENTATION_VERTICAL
		            									, 562
		            									, 248
		            									, PAINT_BAR
		            									, PAINT_LINE
				);
            
    }
    
    
    /**
	 * Gráfico - Relatório Gráfico Pagamentos de Garantia
	 * 
	 * O Objeto DefaultPieDataset recebe os valores que irão gerar o
	 * gráfico
	 * 
	 * @param tituloGrafico
	 *            <code>String</code> Título do gráfico
	 * 
	 * @param tituloEixoX
	 *            <code>String</code> Título do eixo X
	 * 
	 * @param listValuesGraph
	 *            <code>List</code> Lista com os valores para o gráfico
	 * 
	 * @return <code>BufferedImage</code> com a imagem do Gráfico gerada
	 * 
	 */
    public static BufferedImage gerarGraficoGarantiaPaga( List listValuesGraph, String titulo ) throws ServiceException {
    	
    	/** Montagem do DataSet do gráfico */
    	DefaultPieDataset pieDataset = new DefaultPieDataset();   	
    	
    	System.out.println("gerarGraficoGarantiaPaga - Tamanho da lista:" + listValuesGraph.size());
    	
    	// Vamos iterar a primeira vez para totalizar
    	double total = 0;
    	Iterator itRes = listValuesGraph.iterator();
    	while ( itRes.hasNext() ) { 
    		
    		EntityGraficoGarantiaPagaVO entityGraficoGarantiaPaga = (EntityGraficoGarantiaPagaVO) itRes.next();
    		total += entityGraficoGarantiaPaga.getValue().doubleValue();    		
    		
    	}
    	
    	System.out.println("gerarGraficoGarantiaPaga - Total:" + total);
    	
    	// Agora montamos o datset com o valor percentual
    	itRes = listValuesGraph.iterator();
    	double perc = 0;
    	while ( itRes.hasNext() ) { 
    		
    		EntityGraficoGarantiaPagaVO entityGraficoGarantiaPaga = (EntityGraficoGarantiaPagaVO) itRes.next();
    		perc = (entityGraficoGarantiaPaga.getValue().doubleValue() * 100 ) / total;
    		
    		pieDataset.setValue(entityGraficoGarantiaPaga.getModelo() + " - " + NumberUtils.formatNumberCurrencyMil(perc) + "%"
    							, entityGraficoGarantiaPaga.getValue()
    							);
    		
    	}
    	
    	System.out.println("gerarGraficoGarantiaPaga - Dataset:" + pieDataset.getItemCount());
    	
    	BufferedImage   bImageGraph =   GenerateChartReport.createGraphicPie3D(titulo, pieDataset, false, 796, 248);
    	
    	return bImageGraph;
    	
    }

}

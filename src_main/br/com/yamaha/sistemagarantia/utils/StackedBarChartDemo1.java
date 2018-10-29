
/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2004, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it under the terms
 * of the GNU Lesser General Public License as published by the Free Software Foundation;
 * either version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * library; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA 02111-1307, USA.
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc. 
 * in the United States and other countries.]
 *
 * -------------------------
 * StackedBarChartDemo1.java
 * -------------------------
 * (C) Copyright 2002-2004, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * $Id: StackedBarChartDemo1.java,v 1.2 2008/10/24 18:56:30 cvs Exp $
 *
 * Changes
 * -------
 * 06-Nov-2002 : Version 1 (DG);
 * 13-May-2003 : Renamed StackedVerticalBarChartDemo --> StackedBarChartDemo1 (DG);
 *
 */

/**
 * 
 */
package br.com.yamaha.sistemagarantia.utils;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to create a stacked bar chart
 * using data from a {@link CategoryDataset}.
 *
 */
public class StackedBarChartDemo1 extends ApplicationFrame {

	private static final long serialVersionUID = -6497882854162235616L;
	
    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public StackedBarChartDemo1(final String title) {

        super(title);
        final CategoryDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);

    }
    
    /**
     * Creates a sample dataset.
     * 
     * @return a sample dataset.
     */
    private CategoryDataset createDataset() {
    	
    	DefaultCategoryDataset categoryDatasetFat = new DefaultCategoryDataset();
    	
    	categoryDatasetFat.addValue(25.4,"NEO", "01-2008" );
    	categoryDatasetFat.addValue(5.0,"NEO", "02-2008" );
    	categoryDatasetFat.addValue(35.4,"NEO", "03-2008" );
    	categoryDatasetFat.addValue(55.9,"NEO", "04-2008" );
    	categoryDatasetFat.addValue(15.0,"NEO", "05-2008" );
    	categoryDatasetFat.addValue(16.5,"NEO", "06-2008" );
    	categoryDatasetFat.addValue(3.8,"NEO", "07-2008" );
    	categoryDatasetFat.addValue(21.0,"NEO", "08-2008" );
    	categoryDatasetFat.addValue(12.0,"NEO", "09-2008" );
    	
    	categoryDatasetFat.addValue(35.4,"XTZ", "01-2008" );
    	categoryDatasetFat.addValue(65.0,"XTZ", "02-2008" );
    	categoryDatasetFat.addValue(5.4,"XTZ", "03-2008" );
    	categoryDatasetFat.addValue(11.9,"XTZ", "04-2008" );
    	categoryDatasetFat.addValue(12.0,"XTZ", "05-2008" );
    	categoryDatasetFat.addValue(46.5,"XTZ", "06-2008" );
    	categoryDatasetFat.addValue(7.8,"XTZ", "07-2008" );
    	categoryDatasetFat.addValue(32.0,"XTZ", "08-2008" );
    	categoryDatasetFat.addValue(22.0,"XTZ", "09-2008" );    	
    	
        return categoryDatasetFat;
    }
    
    /**
     * Creates a sample chart.
     * 
     * @param dataset  the dataset for the chart.
     * 
     * @return a sample chart.
     */
    private JFreeChart createChart(final CategoryDataset dataset) {

        final JFreeChart chart = ChartFactory.createStackedBarChart(
            "Stacked Bar Chart Demo 1",  // chart title
            "Category",                  // domain axis label
            "Value",                     // range axis label
            dataset,                     // data
            PlotOrientation.VERTICAL,    // the plot orientation
            true,                        // legend
            true,                        // tooltips
            false                        // urls
        );
        
        final CategoryPlot plot = chart.getCategoryPlot();
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        
        rangeAxis.setRange(0,100);
        
        return chart;
        
    }

    // ****************************************************************************
    // * JFREECHART DEVELOPER GUIDE                                               *
    // * The JFreeChart Developer Guide, written by David Gilbert, is available   *
    // * to purchase from Object Refinery Limited:                                *
    // *                                                                          *
    // * http://www.object-refinery.com/jfreechart/guide.html                     *
    // *                                                                          *
    // * Sales are used to provide funding for the JFreeChart project - please    * 
    // * support us so that we can continue developing free software.             *
    // ****************************************************************************
    
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(final String[] args) {

        final StackedBarChartDemo1 demo = new StackedBarChartDemo1("Stacked Bar Chart Demo 1");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}

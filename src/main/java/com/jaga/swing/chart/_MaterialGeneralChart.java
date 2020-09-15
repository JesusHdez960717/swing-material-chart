package com.jaga.swing.chart;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Paint;
import java.io.File;
import java.io.IOException;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import com.jhw.swing.util.interfaces.MaterialComponent;

/**
 *
 * @author Jessica Aidyl Garcia Albalah (jgarciaalbalah@gmail.com)
 */
public abstract class _MaterialGeneralChart extends ChartPanel implements MaterialComponent {

    public double DEFAULT_CHART_WIDTH = 500;
    public double DEFAULT_CHART_HEIGHT = 480;
    public double DEFAULT_CHART_PROPORTION = 1.5;
    public double scale = 1.0;
    public double proportion = 1.5;

    /**
     * The chart
     */
    public JFreeChart chart;

    protected _MaterialGeneralChart() {
        super(null);
    }

    public double getProportion() {
        return proportion;
    }

    public void setProportion(double val) {
        proportion = val;
        resizeChart();
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double val) {
        scale = val;
        resizeChart();
    }

    public void resizeChart() {
        double w = DEFAULT_CHART_HEIGHT * scale * proportion;
        double h = DEFAULT_CHART_HEIGHT * scale;
        Dimension d = new java.awt.Dimension((int) (w), (int) (h));

        this.setSize(new java.awt.Dimension(d));
        this.setPreferredSize(this.getSize());

        // JFreeChart has a hillariously broken way of handling font scaling.
        // It allows fonts to scale independently in X and Y.  We hack a workaround
        // here.
        this.setMinimumDrawHeight((int) DEFAULT_CHART_HEIGHT);
        this.setMaximumDrawHeight((int) DEFAULT_CHART_HEIGHT);
        this.setMinimumDrawWidth((int) (DEFAULT_CHART_HEIGHT * proportion));
        this.setMaximumDrawWidth((int) (DEFAULT_CHART_HEIGHT * proportion));

        this.repaint();
    }

    /**
     * Override this to change the principal color that format the chart.
     * Default: Color.WHITE
     *
     * @param color
     */
    public void personaliceChart(Paint color) {

        chart.setBackgroundPaint(color);

        //Format legend border
        chart.getLegend().setFrame(BlockBorder.NONE);

        // JFreeChart has a hillariously broken way of handling font scaling.
        // It allows fonts to scale independently in X and Y.  We hack a workaround here.
        //      this.setMinimumDrawHeight((int) DEFAULT_CHART_HEIGHT);
        //       this.setMaximumDrawHeight((int) DEFAULT_CHART_HEIGHT);
        //       this.setMinimumDrawWidth((int) (DEFAULT_CHART_HEIGHT * proportion));
        //       this.setMaximumDrawWidth((int) (DEFAULT_CHART_HEIGHT * proportion));
        //      this.setPreferredSize(new java.awt.Dimension((int) (DEFAULT_CHART_HEIGHT * DEFAULT_CHART_PROPORTION), (int) (DEFAULT_CHART_HEIGHT)));
    }

    public void setChartTitle(String title) {
        chart.setTitle(title);
    }

    public void setChartTitleFont(Font font) {
        chart.getTitle().setFont(font);
    }

    public void saveChartAsPNG() {
        File imageFile = new File(chart.getTitle().getText() + ".png");
        int width = 640;
        int height = 480;

        try {
            ChartUtilities.saveChartAsPNG(imageFile, chart, width, height);
        } catch (IOException ex) {
            System.err.println(ex);
        }

    }
}

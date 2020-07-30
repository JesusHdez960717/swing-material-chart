package com.jaga.swing.chart;

import com.jaga.swing.chart.pieces.LineChartPiece;
import com.jaga.swing.chart.pieces.LineChartSerie;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Paint;
import java.awt.Stroke;
import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import com.jhw.swing.util.interfaces.MaterialComponent;
import com.jhw.swing.material.standards.MaterialColors;
import com.jhw.swing.material.standards.MaterialFontRoboto;

/**
 *
 * @author Jessica Aidyl Garcia Albalah (jgarciaalbalah@gmail.com)
 */
public class _MaterialLineChart extends _MaterialGeneralChart implements MaterialComponent {

    private XYSeriesCollection dataset = new XYSeriesCollection();
    private ArrayList<LineChartSerie> series = new ArrayList<>();
    private ArrayList<LineChartPiece> pieces = new ArrayList<>();

    public _MaterialLineChart() {
        buildChart(pieces);
    }

    public void buildChart(ArrayList<LineChartPiece> pieces) {
        createLineChartDataset(pieces);

        chart = ChartFactory.createXYLineChart(
                "", //title
                "", //xAxisTitle
                "", //yAxisTitle
                this.dataset, //data
                PlotOrientation.VERTICAL, // orientation
                true, // include legend
                true, // tooltips?
                false // URLs?
        );
        personaliceChart(MaterialColors.WHITE);
        changeDefaultColors();
        setChart(chart);
    }

    public void buildChart() {

        chart = ChartFactory.createXYLineChart(
                "", //title
                "", //xAxisTitle
                "", //yAxisTitle
                this.dataset, //data
                PlotOrientation.VERTICAL, // orientation
                true, // include legend
                true, // tooltips?
                false // URLs?
        );
        personaliceChart(MaterialColors.WHITE);
        changeDefaultColors();
        setChart(chart);
    }

    @Override
    public void personaliceChart(Paint color) {
        super.personaliceChart(color);

        //personalizacion del grafico
        XYPlot plot = (XYPlot) chart.getPlot();

        plot.setBackgroundPaint(color);

        //color de las lineas verticales
        plot.setDomainGridlinePaint(MaterialColors.GREY_600);

        //color de las lineas horizontales
        plot.setRangeGridlinePaint(MaterialColors.GREY_600);

        //color del recuadro que contiene el chart
        plot.setOutlinePaint(color);

        // -> Pinta Shapes en los puntos dados por el XYDataset
        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();

        renderer.setBaseShapesVisible(true);
        renderer.setBaseShapesFilled(true);

        // customise the range axis...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setAutoRangeIncludesZero(false);
        rangeAxis.setUpperMargin(0.12);

        //--> muestra los valores de cada punto XY
        XYItemLabelGenerator xy = new StandardXYItemLabelGenerator();

        //pone los valores
        renderer.setBaseItemLabelGenerator(xy);
        //muestra los valores
        renderer.setBaseItemLabelsVisible(true);
        //muestra las lineas
        renderer.setBaseLinesVisible(true);
        //cambiar la fuente de los valores
        renderer.setBaseItemLabelFont(MaterialFontRoboto.LIGHT);

        //format Legend
        //quitar bordes a la leyenda
        chart.getLegend().setFrame(BlockBorder.NONE);
        //letra de material
        chart.getLegend().setItemFont(MaterialFontRoboto.REGULAR);
        // posicion de la leyenda
    }

    public void addSerie(String serieName, Color color) {
        series.add(new LineChartSerie(serieName, color));
        this.dataset.addSeries(new XYSeries(serieName));
    }

    public void removeAllSeries() {
        series.clear();
        this.dataset.removeAllSeries();
    }

    public void addSpot(int serieId, Number x, Number y) {
        pieces.add(new LineChartPiece(series.get(serieId), x, y, serieId));
        buildChart(pieces);
    }

    public void removeAllSpots() {
        pieces.clear();
        buildChart(pieces);
    }

    private void createLineChartDataset(ArrayList<LineChartPiece> pieces) {

        for (LineChartPiece piece : pieces) {
            // Searching in the dataset 
            for (int j = 0; j < dataset.getSeriesCount(); j++) {
                if (dataset.getSeries(j).getKey().compareTo(piece.getSerie().getSerieName()) == 0) {
                    dataset.getSeries(j).add(piece.getX(), piece.getY());
                    break;
                }
            }
        }
    }

    private void changeDefaultColors() {
        XYPlot plot = (XYPlot) chart.getPlot();
        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
        for (int i = 0; i < series.size(); i++) {
            renderer.setSeriesPaint(i, series.get(i).getColor());
            renderer.setSeriesStroke(i, new BasicStroke(2.0f));
        }
    }

    public void changeStrokeInSpecificPoint(int rowToChange, int specificPoint) {
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer() {
            Stroke soild = new BasicStroke(2.0f);
            Stroke dashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[]{10.0f}, 0.0f);

            @Override
            public Stroke getItemStroke(int row, int column) {
                if (row == rowToChange) {
                    double x = dataset.getXValue(row, column);
                    if (x == specificPoint) {
                        return dashed;
                    } else {
                        return soild;
                    }
                } else {
                    return soild;
                }
            }
        };
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setRenderer(renderer);
        personaliceChart(MaterialColors.WHITE);
    }

    public void changeStrokeSinceSpecificPoint(int rowToChange, int specificPoint) {
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer() {
            Stroke soild = new BasicStroke(2.0f);
            Stroke dashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[]{10.0f}, 0.0f);

            @Override
            public Stroke getItemStroke(int row, int column) {
                if (row == rowToChange) {
                    double x = dataset.getXValue(row, column);
                    if (x > specificPoint) {
                        return dashed;
                    } else {
                        return soild;
                    }
                } else {
                    return soild;
                }
            }
        };
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setRenderer(renderer);
        personaliceChart(MaterialColors.WHITE);
    }

    public String getYAxisTitle() {
        XYPlot plot = (XYPlot) chart.getPlot();
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        return rangeAxis.getLabel();
    }

    public void setYAxisTitle(String title) {
        XYPlot plot = (XYPlot) chart.getPlot();
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setLabel(title);
        rangeAxis.setLabelFont(MaterialFontRoboto.REGULAR);
    }

    public String getXAxisTitle() {
        XYPlot plot = (XYPlot) chart.getPlot();
        final NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        return domainAxis.getLabel();
    }

    public void setXAxisTitle(String title) {
        XYPlot plot = (XYPlot) chart.getPlot();
        final NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setLabel(title);
        domainAxis.setLabelFont(MaterialFontRoboto.REGULAR);
    }

    public _MaterialBarChart convertLineToBar() {
        _MaterialBarChart barChart = new _MaterialBarChart();
        for (LineChartSerie serie : series) {
            barChart.addCategory(serie.getSerieName().toString(), serie.getColor());
        }
        for (LineChartPiece piece : pieces) {
            barChart.addBar(piece.getY(), piece.getSerieId(), piece.getX().toString());
        }

        if (!chart.getTitle().getText().isEmpty()) {
            barChart.setChartTitle(chart.getTitle().getText());
        }
        if (!getXAxisTitle().isEmpty()) {
            barChart.setXAxisTitle(getXAxisTitle());
        }
        if (!getYAxisTitle().isEmpty()) {
            barChart.setYAxisTitle(getYAxisTitle());
        }
        return barChart;
    }

    public XYSeriesCollection getDataset() {
        return dataset;
    }

    public void setDataset(XYSeriesCollection dataset) {
        this.dataset = dataset;
    }
}

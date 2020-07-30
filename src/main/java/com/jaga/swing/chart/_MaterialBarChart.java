package com.jaga.swing.chart;

import com.jaga.swing.chart.painters.CustomBarPainter;
import com.jaga.swing.chart.pieces.BarChartCategory;
import com.jaga.swing.chart.pieces.BarChartPiece;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Paint;
import java.text.DecimalFormat;
import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleInsets;
import com.jhw.swing.util.interfaces.MaterialComponent;
import com.jhw.swing.material.standards.MaterialColors;
import com.jhw.swing.material.standards.MaterialFontRoboto;

/**
 *
 * @author Jessica Aidyl Garcia Albalah (jgarciaalbalah@gmail.com)
 */
public class _MaterialBarChart extends _MaterialGeneralChart implements MaterialComponent {

    private CategoryDataset dataset;
    private ArrayList<BarChartCategory> category = new ArrayList<>();
    private ArrayList<BarChartPiece> pieces = new ArrayList<>();

    public _MaterialBarChart() {
        buildChart(pieces);
    }

    public void buildChart(ArrayList<BarChartPiece> pieces) {

        createBarChartDataset(pieces);

        chart = ChartFactory.createBarChart(
                "",// chart title
                "",// domain axis label
                "",// range axis label
                this.dataset,// data
                PlotOrientation.VERTICAL,// orientation
                true,// include legend
                true,// tooltips?
                false// URLs?
        );
        personaliceChart(MaterialColors.WHITE);
        setChart(chart);
        changeDefaultColorWithCategory();
    }

    @Override
    public void personaliceChart(Paint color) {
        super.personaliceChart(color);

        CategoryPlot categoryPlot = chart.getCategoryPlot();

        // color del fondo del histograma
        categoryPlot.setBackgroundPaint(color);
        categoryPlot.setDomainGridlinePaint(color);

        // se oculta el recuadro del histograma
        categoryPlot.setOutlineVisible(false);

        // color de las lineas horizontales a partir del eje Y
        categoryPlot.setRangeGridlinePaint(MaterialColors.GREY_200);
        categoryPlot.setRangeGridlineStroke(new BasicStroke(1));

        // se crea un bar renderer especial para mostrar cada "category" en una
        // sola barra con capas superpuestas
        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();

        renderer.setGradientPaintTransformer(null);
        renderer.setBarPainter(new CustomBarPainter());//StandardBarPainter
        renderer.setShadowVisible(false);

        // se ajusta el ancho de las barras de última factura para que coincidan
        // en tamaño con la de última factura ya que al ser el renderer por
        // capas según se va incrementando la "category" la anchura de la barra
        // aumenta
        // renderer.setItemMargin(-0.6D);
        categoryPlot.setRenderer(renderer);

        // format the x axis
        CategoryAxis xAxis = categoryPlot.getDomainAxis();
        xAxis.setCategoryLabelPositionOffset(4);
        xAxis.setCategoryMargin(0.2);

        // se reducen los margenes laterales entre el eje Y y las barras
        xAxis.setLowerMargin(0.01D);
        xAxis.setUpperMargin(0.01D);

        // se eliminan las marcas por categoria en el eje X
        xAxis.setTickMarksVisible(false);

        // color de la linea del eje X
        xAxis.setAxisLinePaint(MaterialColors.BLACK);

        // se rotan 45grados las etiquetas del eje X para que no haya problema de
        // espacio
        xAxis.setTickLabelFont(MaterialFontRoboto.REGULAR);
        //  cAxis.setTickLabelPaint(new Color(160, 163, 165));
        xAxis.setLabelFont(MaterialFontRoboto.REGULAR);

        // format the y axis
        ValueAxis yAxis = categoryPlot.getRangeAxis();

        // se reducen los margenes laterales entre el eje X y las barras
        // yAxis.setLowerMargin(0.01D);
        // yAxis.setUpperMargin(0.01D);
        // se eliminan las marcas por categoria en el eje Y
        // yAxis.setTickMarksVisible(false);
        // color de la linea del eje Y
        yAxis.setAxisLinePaint(MaterialColors.BLACK);

        // se rotan 45grados las etiquetas del eje Y para que no haya problema de espacio
        //   yAxis.setTickLabelFont(new Font("Arial", Font.BOLD, 9));
        yAxis.setLabelFont(MaterialFontRoboto.REGULAR);
    }

    public void hideAxisY() {
        // hide y axis
        NumberAxis rangeAxis = (NumberAxis) chart.getCategoryPlot().getRangeAxis();
        rangeAxis.setVisible(false);
    }

    public String getYAxisTitle() {
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        return rangeAxis.getLabel();
    }

    public void setYAxisTitle(String title) {
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setLabel(title);
        rangeAxis.setLabelFont(MaterialFontRoboto.REGULAR);
    }

    public String getXAxisTitle() {
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        final CategoryAxis domainAxis = (CategoryAxis) plot.getDomainAxis();
        return domainAxis.getLabel();
    }

    public void setXAxisTitle(String title) {
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        final CategoryAxis domainAxis = (CategoryAxis) plot.getDomainAxis();
        domainAxis.setLabel(title);
        domainAxis.setLabelFont(MaterialFontRoboto.REGULAR);
    }

    public void addValuesToBars() {
        BarRenderer renderer = (BarRenderer) chart.getCategoryPlot().getRenderer();

        // add values to bars
        renderer.setBaseItemLabelsVisible(true);
        renderer.setBaseItemLabelGenerator((CategoryItemLabelGenerator) new StandardCategoryItemLabelGenerator("{2}", new DecimalFormat("#0.00#")));
        renderer.setBaseItemLabelFont(MaterialFontRoboto.REGULAR);
        chart.getCategoryPlot().setRenderer(renderer);
    }

    public void changeDefaultColors(Paint[] colors) {
        BarRenderer renderer = (BarRenderer) chart.getCategoryPlot().getRenderer();
        renderer.setBarPainter(new StandardBarPainter());

        // change the default colors
        for (int i = 0; i < 4; i++) {
            renderer.setSeriesPaint(i, colors[i % colors.length]);
        }
        chart.getCategoryPlot().setRenderer(renderer);
    }

    public void verticalLegend() {
        //Format legend (queda en forma de lista)
        chart.getLegend().setItemLabelPadding(new RectangleInsets(5.0, 2.0, 10.0, DEFAULT_CHART_WIDTH));
        chart.getLegend().setPadding(new RectangleInsets(20.0, 20.0, 0.0, 0.0));
    }

    public void createBarChartDataset(ArrayList<BarChartPiece> pieces) {

        DefaultCategoryDataset datasets = new DefaultCategoryDataset();
        ArrayList<BarChartCategory> categorys = new ArrayList<>();

        for (int i = 0; i < pieces.size(); i++) {
            // Defining the dataset 
            datasets.addValue(pieces.get(i).getValue(), pieces.get(i).getCategory().getCategoryName(), pieces.get(i).getColumnName());

            if (!categorys.contains(pieces.get(i).getCategory())) {
                categorys.add(pieces.get(i).getCategory());
            }
        }
        setDataset(datasets);
    }

    public void changeDefaultColorWithCategory() {
        for (int j = 0; j < category.size(); j++) {
            chart.getCategoryPlot().getRenderer().setSeriesPaint(j, category.get(j).getColor());
        }
    }

    public CategoryDataset getDataset() {
        return dataset;
    }

    public void setDataset(CategoryDataset dataset) {
        this.dataset = dataset;
    }

    public ArrayList<BarChartCategory> getCategory() {
        return category;
    }

    public void setCategory(ArrayList<BarChartCategory> category) {
        this.category = category;
    }

    public void removeAllCategories() {
        category.clear();
    }

    public void addCategory(String categoryName, Color color) {
        category.add(new BarChartCategory(categoryName, color));
    }

    public void addBar(Number value, int categoryId, String columnName) {
        pieces.add(new BarChartPiece(value, category.get(categoryId), columnName, categoryId));
        buildChart(pieces);
    }

    public void removeAllBars() {
        pieces.clear();
        buildChart(pieces);
    }

    /**
     * Este metodo necesita que las columnas del bar chart sean de tipo Integer
     *
     * @return _MaterialLineChart
     */
    public _MaterialLineChart convertBarToLine() {
        _MaterialLineChart lineChart = new _MaterialLineChart();

        for (BarChartCategory category1 : category) {
            lineChart.addSerie(category1.getCategoryName(), category1.getColor());
        }
        for (BarChartPiece piece : pieces) {
            lineChart.addSpot(piece.getCategoryId(), Integer.parseInt(piece.getColumnName()), piece.getValue());
        }

        if (!chart.getTitle().getText().isEmpty()) {
            lineChart.setChartTitle(chart.getTitle().getText());
        }
        if (!getXAxisTitle().isEmpty()) {
            lineChart.setXAxisTitle(getXAxisTitle());
        }
        if (!getYAxisTitle().isEmpty()) {
            lineChart.setYAxisTitle(getYAxisTitle());
        }
        return lineChart;
    }
}

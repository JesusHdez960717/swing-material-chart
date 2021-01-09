package com.jaga.swing.chart;

import com.jaga.swing.chart.pieces.PieChartPiece;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.util.ArrayList;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.RingPlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.util.Rotation;
import com.jaga.swing.chart.PieChartEnum;
import com.root101.swing.util.interfaces.MaterialComponent;
import com.root101.swing.material.standards.MaterialColors;
import com.root101.swing.material.standards.MaterialFontRoboto;

/**
 *
 * @author Jessica Aidyl Garcia Albalah (jgarciaalbalah@gmail.com)
 */
public class _MaterialPieChart extends _MaterialGeneralChart implements MaterialComponent {

    private PieDataset dataset;
    private Color[] colors;
    private ArrayList<PieChartPiece> pieces = new ArrayList<PieChartPiece>();
    private PieChartEnum chartEnum;

    protected _MaterialPieChart(PieChartEnum chartEnum) {
        this.chartEnum = chartEnum;
        buildChart();
    }

    public void buildChart() {
        if (chartEnum.equals(PieChartEnum.HOLLOW)) {
            buildHollowPieChart();
        } else if (chartEnum.equals(PieChartEnum.EXPLODED)) {
            buildExplodedPieChart();
        } else {
            buildNormalChart(pieces);
        }
    }

    public void buildNormalChart(ArrayList<PieChartPiece> pieces) {

        createPieDataset(pieces);

        chart = ChartFactory.createPieChart(
                "", // chart title
                this.dataset, //PieDateset
                true,// include legend
                true,// tooltips
                false// URLs?
        );
        personaliceChart(MaterialColors.WHITE);
        setChart(chart);
        changeDefaultColors(this.colors);
    }

    public void buildHollowPieChart() {
        buildHollowPieChart(pieces);
    }

    public void buildHollowPieChart(ArrayList<PieChartPiece> pieces) {
        createPieDataset(pieces);
        RingPlot plot = new RingPlot(this.dataset);

        chart = new JFreeChart(
                "", // chart title
                JFreeChart.DEFAULT_TITLE_FONT, // chart title font
                plot, //para hecerlo en forma de anillo
                true // include legend
        );

        personaliceChart(MaterialColors.WHITE);

        //pinta la parte de afuera del grafico
        chart.setBackgroundPaint(java.awt.Color.white);

        // Ancho del anillo
        plot.setSectionDepth(0.35);

        //para separar las partes del anillo
        plot.setSeparatorPaint(MaterialColors.WHITE);
        plot.setSeparatorStroke(new BasicStroke(7));

        //Corrige las lineas que separan
        plot.setOuterSeparatorExtension(0);
        plot.setInnerSeparatorExtension(0);

        //dar color al borde de la etiqueta
        plot.setLabelOutlinePaint(MaterialColors.BLACK);

        //background del label
        plot.setLabelBackgroundPaint(MaterialColors.WHITE);

        //color de la letra
        plot.setLabelPaint(MaterialColors.BLACK);

        //  chart.setPadding(new RectangleInsets(4, 8, 2, 2));
        setChart(chart);
        changeDefaultColors(this.colors);
    }

    public void buildExplodedPieChart() {
        buildExplodedPieChart(pieces);
    }

    public void buildExplodedPieChart(ArrayList<PieChartPiece> pieces) {

        buildNormalChart(pieces);

        PiePlot plot = (PiePlot) chart.getPlot();

        List<Comparable> keys = dataset.getKeys();
        int aInt;
        for (int i = 0; i < keys.size(); i++) {
            plot.setExplodePercent(keys.get(i), 0.1);
        }
    }

    @Override
    public void personaliceChart(Paint color) {

        PiePlot plot = (PiePlot) chart.getPlot();

        // set background color
        plot.setBackgroundPaint(color);

        // remove background border
        plot.setOutlineVisible(false);

        // no mostrar bordes del grafico
        plot.setSectionOutlinesVisible(false);

        //dar color al borde de la etiqueta
        plot.setLabelOutlinePaint(null);

        //quitar la sombra a la etiqueta
        plot.setLabelShadowPaint(null);

        //color de la letra
        plot.setLabelPaint(color);

        //background del label
        plot.setLabelBackgroundPaint(MaterialColors.TRANSPARENT);

        //pone las etiquetas dentro del grafico 
        // en falso las pone fuera guiada por una linea 
        plot.setSimpleLabels(true);

        //Aumenta el espacio que ocupa el grafico
        //plot.setInteriorGap(0.0D);
        //sin sombra
        plot.setShadowPaint(color);

        //para que los labels no se sobrepongan
        plot.setStartAngle(180);
        plot.setDirection(Rotation.ANTICLOCKWISE);

        //Lo que va a mostrar el label
        // 0 el nombre 
        // 1 el valor entero
        // 2 el porciento del valor
        PieSectionLabelGenerator generator = new StandardPieSectionLabelGenerator("{0}: ({2})");
        plot.setLabelGenerator(generator);

        //format Legend
        //quitar bordes a la leyenda
        chart.getLegend().setFrame(BlockBorder.NONE);
        //letra de material
        chart.getLegend().setItemFont(MaterialFontRoboto.REGULAR);
        // posicion de la leyenda
        chart.getLegend().setPosition(RectangleEdge.RIGHT);
        //poner los datos en la leyenda
        plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1} ({2})"));

        plot.setLabelFont(MaterialFontRoboto.BOLD);
    }

    /**
     * La cantidad de colores debe ser mayor o igual que la cantidad de datos ya
     * que de lo contrario se repetirian los colores.
     */
    public void changeDefaultColors(Color[] colors) {
        PiePlot plot = (PiePlot) chart.getPlot();
        List<Comparable> keys = getDataset().getKeys();
        int aInt = 0;

        for (int i = 0; i < keys.size(); i++) {
            aInt = i % colors.length;
            plot.setSectionPaint(keys.get(i), colors[i]);
        }
    }

    public void setLabelFont(Font font) {
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelFont(font);
    }

    //Metodo por corregir
    public void personalizeTitle() {
        TextTitle t = chart.getTitle();
        //Cambia la posicion del titulo
        t.setHorizontalAlignment(org.jfree.ui.HorizontalAlignment.LEFT);
        //Cambia el color de la letra del titulo
        t.setPaint(new Color(240, 240, 240));
        //Cambia la letra del titulo
        t.setFont(MaterialFontRoboto.REGULAR);
    }

    public void createPieDataset(ArrayList<PieChartPiece> pieces) {

        DefaultPieDataset dataset = new DefaultPieDataset();
        Color[] colors = new Color[pieces.size()];

        for (int i = 0; i < pieces.size(); i++) {
            // Defining the dataset 
            dataset.setValue(pieces.get(i).getName(), pieces.get(i).getValue());
            colors[i] = pieces.get(i).getColor();
        }
        setDataset(dataset);
        setColors(colors);
    }

    public PieDataset getDataset() {
        return dataset;
    }

    public void setDataset(PieDataset dataset) {
        this.dataset = dataset;
    }

    public Color[] getColors() {
        return colors;
    }

    public void setColors(Color[] colors) {
        this.colors = colors;
    }

    public void setLegendPosition(RectangleEdge position) {
        // posicion de la leyenda
        chart.getLegend().setPosition(position);
    }

    public void addPiece(String name, Number value, Color color) {
        pieces.add(new PieChartPiece(name, value, color));
        buildChart();
    }

    public void removeAllPieces() {
        pieces.clear();
        buildChart();
    }

    public ArrayList<PieChartPiece> getPieces() {
        return pieces;
    }

    public void setPieces(ArrayList<PieChartPiece> pieces) {
        this.pieces = pieces;
    }

    public PieChartEnum getChartEnum() {
        return chartEnum;
    }

    public void setChartEnum(PieChartEnum chartEnum) {
        this.chartEnum = chartEnum;
    }
}

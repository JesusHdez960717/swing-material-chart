package com.jaga.swing.chart.pieces;

import java.awt.Color;

/**
 *
 * @author Jessica Aidyl Garcia Albalah (jgarciaalbalah@gmail.com)
 */
public class LineChartSerie {

    private Comparable serieName;
    private Color color;

    public LineChartSerie(String serieName, Color color) {
        this.serieName = serieName;
        this.color = color;
    }

    public Comparable getSerieName() {
        return serieName;
    }

    public void setSerieName(String serieName) {
        this.serieName = serieName;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}

package com.jaga.swing.chart.pieces;

import java.awt.Color;

/**
 *
 * @author Jessica Aidyl Garcia Albalah (jgarciaalbalah@gmail.com)
 */
public class PieChartPiece {

    private String name;
    private Number value;
    private Color color;

    public PieChartPiece(String name, Number value, Color color) {
        this.name = name;
        this.value = value;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Number getValue() {
        return value;
    }

    public void setValue(Number value) {
        this.value = value;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}

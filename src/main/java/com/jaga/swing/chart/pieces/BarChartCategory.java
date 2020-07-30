package com.jaga.swing.chart.pieces;

import java.awt.Color;

/**
 *
 * @author Jessica Aidyl Garcia Albalah (jgarciaalbalah@gmail.com)
 */
public class BarChartCategory {

    private String categoryName;
    private Color color;

    public BarChartCategory(String categoryName, Color color) {
        this.categoryName = categoryName;
        this.color = color;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}

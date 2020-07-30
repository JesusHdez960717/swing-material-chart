package com.jaga.swing.chart.pieces;

/**
 *
 * @author Jessica Aidyl Garcia Albalah (jgarciaalbalah@gmail.com)
 */
public class BarChartPiece {

    private Number value;
    private BarChartCategory category;
    private String columnName;
    private int categoryId;

    public BarChartPiece(Number value, BarChartCategory category, String columnName, int categoryId) {
        this.value = value;
        this.category = category;
        this.columnName = columnName;
        this.categoryId = categoryId;
    }

    public Number getValue() {
        return value;
    }

    public void setValue(Number value) {
        this.value = value;
    }

    public BarChartCategory getCategory() {
        return category;
    }

    public void setCategory(BarChartCategory category) {
        this.category = category;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}

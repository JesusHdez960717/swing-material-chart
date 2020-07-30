package com.jaga.swing.chart.pieces;

/**
 *
 * @author Jessica Aidyl Garcia Albalah (jgarciaalbalah@gmail.com)
 */
public class LineChartPiece {

    private LineChartSerie serie;
    private Number x;
    private Number y;
    private int serieId;

    public LineChartPiece(LineChartSerie serie, Number x, Number y, int serieId) {
        this.serie = serie;
        this.x = x;
        this.y = y;
        this.serieId = serieId;
    }

    public LineChartSerie getSerie() {
        return serie;
    }

    public void setSerie(LineChartSerie serie) {
        this.serie = serie;
    }

    public Number getX() {
        return x;
    }

    public void setX(Number x) {
        this.x = x;
    }

    public Number getY() {
        return y;
    }

    public void setY(Number y) {
        this.y = y;
    }

    public int getSerieId() {
        return serieId;
    }

    public void setSerieId(int serieId) {
        this.serieId = serieId;
    }
}

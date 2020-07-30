package com.jaga.swing.chart.example;

import com.jaga.swing.chart._MaterialPieChart;
import java.util.Random;
import com.jaga.swing.chart.PieChartEnum;
import java.awt.Color;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class PIE_HOLLOW_EXAMPLE extends _MaterialPieChart {

    public PIE_HOLLOW_EXAMPLE() {
        super(PieChartEnum.HOLLOW);
        int maxBars = 10;
        Random r = new Random();
        for (int i = 0; i < maxBars; i++) {
            addPiece("name " + r.nextInt(1000), r.nextInt(100), new Color(r.nextInt()));
        }
        //--------------------------------------------------------

        this.getChart().setTitle("Nombre del grafico de pie");
    }

}

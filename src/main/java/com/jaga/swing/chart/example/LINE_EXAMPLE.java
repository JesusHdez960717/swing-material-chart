package com.jaga.swing.chart.example;

import com.jaga.swing.chart._MaterialLineChart;
import java.awt.Color;
import java.util.Random;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class LINE_EXAMPLE extends _MaterialLineChart {

    public LINE_EXAMPLE() {
        Random r = new Random();
        addSerie("Categoria A", new Color(r.nextInt()));
        addSerie("Categoria B", new Color(r.nextInt()));

        int maxBars = 10;
        for (int i = 0; i < maxBars; i++) {
            addSpot(r.nextInt(2), r.nextInt(100), r.nextInt(1000));
        }
        //--------------------------------------------------------

        this.getChart().setTitle("Nombre del grafico de linea");
    }

}

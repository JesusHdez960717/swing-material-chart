package com.jaga.swing.chart.example;

import com.jaga.swing.chart._MaterialBarChart;
import java.awt.Color;
import java.util.Random;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class BAR_EXAMPLE extends _MaterialBarChart {

    public BAR_EXAMPLE() {
        Random r = new Random();
        addCategory("Categoria A", new Color(r.nextInt()));
        addCategory("Categoria B", new Color(r.nextInt()));

        int maxBars = 10;
        for (int i = 0; i < maxBars; i++) {
            addBar(r.nextInt(100), r.nextInt(2), "name " + r.nextInt(1000));
        }
        //--------------------------------------------------------

        this.getChart().setTitle("Nombre del grafico de barras");
    }

}

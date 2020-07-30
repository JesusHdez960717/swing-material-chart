package com.jaga.swing.chart.example;

import com.jaga.swing.chart._MaterialConvertChar;
import java.awt.Color;
import java.util.Random;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class CONVERT_EXAMPLE extends _MaterialConvertChar {

    public CONVERT_EXAMPLE() {
        Random r = new Random();
        addSerie("Categoria A", new Color(r.nextInt()));
        addSerie("Categoria B", new Color(r.nextInt()));

        int maxBars = 10;
        for (int i = 0; i < maxBars; i++) {
            addSpot(r.nextInt(2), r.nextInt(100), r.nextInt(1000));
        }
        //--------------------------------------------------------

        getChart().setChartTitle("Helloo");
        getChart().setXAxisTitle("Las Xs");
        setTitle("Nombre del grafico double");

        //the start position
        this.changeToBars();
    }

}

package com.jaga.swing.chart.example;

import com.jaga.swing.chart._MaterialConvertChar;
import com.root101.swing.material.components.container.MaterialContainersFactory;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.root101.swing.ui.MaterialLookAndFeel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Random;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class EXAMPLE_CONVERT extends javax.swing.JFrame {

    public EXAMPLE_CONVERT() {
        initComponents();
    }

    private void initComponents() {
        panelBack = MaterialContainersFactory.buildPanelComponentTransparent();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setContentPane(panelBack);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelBack.add(new CONVERT_CHART());

        pack();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new MaterialLookAndFeel());

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EXAMPLE_CONVERT().setVisible(true);
            }
        });
    }

    private JPanel panelBack;

    public class CONVERT_CHART extends _MaterialConvertChar {

        public CONVERT_CHART() {
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

}

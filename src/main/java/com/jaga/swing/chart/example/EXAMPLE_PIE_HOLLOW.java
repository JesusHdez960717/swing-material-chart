package com.jaga.swing.chart.example;

import com.jaga.swing.chart.PieChartEnum;
import com.jaga.swing.chart._MaterialConvertChar;
import com.jaga.swing.chart._MaterialLineChart;
import com.jaga.swing.chart._MaterialPieChart;
import com.jhw.swing.material.components.container.MaterialContainersFactory;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.jhw.swing.ui.MaterialLookAndFeel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Random;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class EXAMPLE_PIE_HOLLOW extends javax.swing.JFrame {

    public EXAMPLE_PIE_HOLLOW() {
        initComponents();
    }

    private void initComponents() {
        panelBack = MaterialContainersFactory.buildPanelComponentTransparent();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setContentPane(panelBack);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelBack.add(new PIE_HOLLOW_CHAR());

        pack();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new MaterialLookAndFeel());

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EXAMPLE_PIE_HOLLOW().setVisible(true);
            }
        });
    }

    private JPanel panelBack;

    public class PIE_HOLLOW_CHAR extends _MaterialPieChart {

        public PIE_HOLLOW_CHAR() {
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

}

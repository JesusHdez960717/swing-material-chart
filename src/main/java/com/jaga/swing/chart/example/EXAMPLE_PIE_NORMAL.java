package com.jaga.swing.chart.example;

import com.jaga.swing.chart.PieChartEnum;
import com.jaga.swing.chart._MaterialConvertChar;
import com.jaga.swing.chart._MaterialLineChart;
import com.jaga.swing.chart._MaterialPieChart;
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
public class EXAMPLE_PIE_NORMAL extends javax.swing.JFrame {

    public EXAMPLE_PIE_NORMAL() {
        initComponents();
    }

    private void initComponents() {
        panelBack = MaterialContainersFactory.buildPanelComponentTransparent();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setContentPane(panelBack);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelBack.add(new PIE_NORMAL_CHART());

        pack();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new MaterialLookAndFeel());

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EXAMPLE_PIE_NORMAL().setVisible(true);
            }
        });
    }

    private JPanel panelBack;

    public class PIE_NORMAL_CHART extends _MaterialPieChart {

        public PIE_NORMAL_CHART() {
            super(PieChartEnum.NORMAL);
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

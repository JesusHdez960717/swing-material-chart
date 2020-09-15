package com.jaga.swing.chart.example;

import com.jaga.swing.chart._MaterialBarChart;
import com.jhw.swing.material.components.container.MaterialContainersFactory;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.jhw.swing.ui.MaterialLookAndFeel;
import java.awt.Color;
import java.util.Random;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class EXAMPLE_BAR extends javax.swing.JFrame {

    public EXAMPLE_BAR() {
        initComponents();
    }

    private void initComponents() {
        panelBack = MaterialContainersFactory.buildPanelComponentTransparent();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setContentPane(panelBack);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelBack.add(new BAR_CHART());

        pack();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new MaterialLookAndFeel());

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EXAMPLE_BAR().setVisible(true);
            }
        });
    }

    private JPanel panelBack;
    
  public class BAR_CHART extends _MaterialBarChart {

    public BAR_CHART() {
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

}

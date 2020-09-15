package com.jaga.swing.chart;

import com.jhw.swing.material.components.button.MaterialButtonsFactory;
import com.jhw.swing.material.components.button._MaterialButtonIconTransparent;
import com.jhw.swing.material.components.container.panel._PanelComponent;
import com.jhw.swing.material.components.container.panel._PanelTransparent;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.jhw.swing.util.interfaces.MaterialComponent;
import com.jhw.swing.material.standards.MaterialIcons;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Jessica Aidyl Garcia Albalah (jgarciaalbalah@gmail.com)
 */
public class _MaterialConvertChar extends _PanelTransparent implements MaterialComponent {

    private _MaterialLineChart lineChart;
    private _MaterialBarChart barChart;

    public _MaterialConvertChar() {
        initComponents();
        lineChart = new _MaterialLineChart();
        barChart = new _MaterialBarChart();
        addListeners();
        personalize();
        changeToBars();
    }

    private void initComponents() {
        panelChart = MaterialContainersFactory.buildPanelComponent();
        buttonBarChart = MaterialButtonsFactory.buildIconTransparent();
        buttonLineChart = MaterialButtonsFactory.buildIconTransparent();

        this.setLayout(new BorderLayout());
        this.add(panelChart);

        JPanel up = MaterialContainersFactory.buildPanelTransparent();
        up.setLayout(new BorderLayout());

        JPanel panelButtons = MaterialContainersFactory.buildPanelComponentTransparent();
        up.add(panelButtons, BorderLayout.EAST);
        this.add(up, BorderLayout.NORTH);

        VerticalLayoutContainer.builder vlc = VerticalLayoutContainer.builder();
        vlc.add(buttonBarChart);
        vlc.add(buttonLineChart);
        panelButtons.add(vlc.build());
    }

    // Variables declaration - do not modify//:variables
    private JButton buttonBarChart;
    private JButton buttonLineChart;
    private JPanel panelChart;
    // End of variables declaration                   

    private void addListeners() {
        buttonBarChart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeToBars();
            }
        });
        buttonLineChart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeToLine();
            }
        });
        buttonBarChart.setToolTipText("Convertir a grafico de barras.");
        buttonLineChart.setToolTipText("Convertir a grafico de lineas.");
    }

    private void personalize() {
        buttonLineChart.setIcon(MaterialIcons.MULTILINE_CHART);
        buttonBarChart.setIcon(MaterialIcons.EQUALIZER);
    }

    public void changeToLine() {
        actualiceLines();
        panelChart.setComponent(lineChart);
    }

    public void changeToBars() {
        actualiceBars();
        panelChart.setComponent(barChart);
    }

    public void addSerie(String serieName, Color color) {
        lineChart.addSerie(serieName, color);
    }

    public void addSpot(int serieId, Number x, Number y) {
        lineChart.addSpot(serieId, x, y);
    }

    public _MaterialLineChart getChart() {
        return lineChart;
    }

    public void setChart(_MaterialLineChart lineChart) {
        this.lineChart = lineChart;
    }

    public JButton getButtonBarChart() {
        return buttonBarChart;
    }

    public JButton getButtonLineChart() {
        return buttonLineChart;
    }

    private void actualiceBars() {
        barChart = lineChart.convertLineToBar();
    }

    private void actualiceLines() {
        this.lineChart = lineChart.convertLineToBar().convertBarToLine();
    }

    public _PanelComponent getPanelChart() {
        return panelChart;
    }

    public void setTitle(String title) {
        this.getChart().getChart().setTitle(title);
    }
}

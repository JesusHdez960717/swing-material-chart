package com.jaga.swing.chart;

import com.root101.swing.material.components.button.MaterialButtonIcon;
import com.root101.swing.material.components.button.MaterialButtonsFactory;
import com.root101.swing.material.components.button._MaterialButtonIconTransparent;
import com.root101.swing.material.components.container.MaterialContainersFactory;
import com.root101.swing.material.components.container.layout.HorizontalLayoutContainer;
import com.root101.swing.material.components.container.layout.VerticalLayoutContainer;
import com.root101.swing.material.components.container.panel._PanelComponent;
import com.root101.swing.material.components.container.panel._PanelGradient;
import com.root101.swing.material.components.container.panel._PanelTransparent;
import com.root101.swing.material.standards.MaterialColors;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.root101.swing.util.interfaces.MaterialComponent;
import com.root101.swing.material.standards.MaterialIcons;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Jessica Aidyl Garcia Albalah (jgarciaalbalah@gmail.com)
 */
public class _MaterialConvertChar extends _PanelGradient implements MaterialComponent {

    private _MaterialLineChart lineChart;
    private _MaterialBarChart barChart;

    protected _MaterialConvertChar() {
        initComponents();
        lineChart = new _MaterialLineChart();
        barChart = new _MaterialBarChart();
        addListeners();
        personalize();
        changeToBars();
    }

    private void initComponents() {
        this.setBackground(MaterialColors.WHITE);

        panelChart = MaterialContainersFactory.buildPanelGradient();
        panelChart.setLayout(new BorderLayout());

        buttonBarChart = MaterialButtonsFactory.buildIconTransparent();
        buttonBarChart.setRippleColor(MaterialColors.GREY_400);
        buttonLineChart = MaterialButtonsFactory.buildIconTransparent();
        buttonLineChart.setRippleColor(MaterialColors.GREY_400);

        this.setLayout(new BorderLayout());
        this.add(panelChart);
        JPanel up = MaterialContainersFactory.buildPanelTransparent();
        up.setLayout(new BorderLayout());

        JPanel panelButtons = MaterialContainersFactory.buildPanelComponentTransparent();
        up.add(panelButtons, BorderLayout.EAST);
        this.add(up, BorderLayout.NORTH);

        HorizontalLayoutContainer.builder vlc = HorizontalLayoutContainer.builder();
        vlc.add(buttonBarChart);
        vlc.add(buttonLineChart);
        panelButtons.add(vlc.build());
    }

    // Variables declaration - do not modify//:variables
    private MaterialButtonIcon buttonBarChart;
    private MaterialButtonIcon buttonLineChart;
    private JPanel panelChart;
    // End of variables declaration                   

    private void addListeners() {
        buttonBarChart.addActionListener((ActionEvent e) -> {
            changeToBars();
        });
        buttonLineChart.addActionListener((ActionEvent e) -> {
            changeToLine();
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
        panelChart.add(lineChart);
        revalidate();
    }

    public void changeToBars() {
        actualiceBars();
        panelChart.add(barChart);
        revalidate();
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

    public void setTitle(String title) {
        this.getChart().getChart().setTitle(title);
    }
}

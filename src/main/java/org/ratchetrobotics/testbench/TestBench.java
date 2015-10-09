package org.ratchetrobotics.testbench;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.ratchetrobotics.algorithms.utilities.JoystickScaler;

import javax.swing.*;
import java.awt.*;

public class TestBench extends JFrame {
    public TestBench() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("testbench");
        setLayout(new GridLayout());
        setSize(800, 800);
        add(createChartPanel());
    }

    private ChartPanel createChartPanel() {
        ChartPanel chartPanel = new ChartPanel(
                createChart());
        return chartPanel;
    }

    private XYDataset createDataset() {
        XYSeries data = new XYSeries("interpolation");
        JoystickScaler joystickScaler = new JoystickScaler();

        for (int i = -1000; i < 1000; i++) {
            double x = 0.001*i;
            data.add(x, joystickScaler.in(x));
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(data);
        return dataset;
    }

    private JFreeChart createChart() {
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Interpolation plot",
                "input",
                "output",
                createDataset(),
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        return chart;
    }

	public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TestBench().setVisible(true);
            }
        });
	}
}
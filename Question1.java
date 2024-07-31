// NOTE ->  Took refrence from here - https://www.javaguides.net/2021/01/jfreechart-tutorial-create-charts-in-java.html
// But its also not working, couldn't do so 

import javax.swing.*;
import org.jfree.chart.ChartFactory;
        import org.jfree.chart.ChartPanel;
        import org.jfree.chart.JFreeChart;
        import org.jfree.chart.plot.PlotOrientation;
        import org.jfree.chart.plot.XYPlot;
        import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
        import org.jfree.data.xy.XYSeries;
        import org.jfree.data.xy.XYSeriesCollection;
        import javax.swing.*;
        import java.awt.*;

public class Question1 extends JFrame {

    public FlightPathPlotter() {
        // Create dataset
        XYSeriesCollection dataset = createDataset();

        // Create chart
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Flight Paths",
                "X-Axis",
                "Y-Axis",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        // Customize the chart
        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        // Set series colors
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesPaint(1, Color.BLUE);
        renderer.setSeriesPaint(2, Color.GREEN);

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.WHITE);

        // Create and display the panel
        ChartPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(new Dimension(800, 600));
        setContentPane(panel);
    }

    private XYSeriesCollection createDataset() {
        XYSeries flight1 = new XYSeries("Flight 1");
        flight1.add(1, 1);
        flight1.add(2, 2);
        flight1.add(3, 3);

        XYSeries flight2 = new XYSeries("Flight 2");
        flight2.add(1, 1);
        flight2.add(2, 4);
        flight2.add(3, 2);

        XYSeries flight3 = new XYSeries("Flight 3");
        flight3.add(1, 1);
        flight3.add(4, 2);
        flight3.add(3, 4);

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(flight1);
        dataset.addSeries(flight2);
        dataset.addSeries(flight3);

        return dataset;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FlightPathPlotter example = new FlightPathPlotter();
            example.setSize(800, 600);
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });
    }
}

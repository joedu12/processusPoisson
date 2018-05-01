/*
 * Decompiled with CFR 0_123.
 */
import java.awt.Container;
import java.awt.Dimension;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class TimePeriodValuesDemo3
extends ApplicationFrame {
    public TimePeriodValuesDemo3(String title) {
        super(title);
        XYDataset data1 = this.createDataset();
        XYBarRenderer renderer1 = new XYBarRenderer();
        DateAxis domainAxis = new DateAxis("Temps");
        NumberAxis rangeAxis = new NumberAxis("");
        XYPlot plot = new XYPlot(data1, domainAxis, rangeAxis, renderer1);
        JFreeChart chart = new JFreeChart("Processus de poisson", plot);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        chartPanel.setMouseZoomable(true, false);
        this.setContentPane(chartPanel);
    }

    public XYDataset createDataset() {
        throw new Error("Unresolved compilation problem: \n\tseries cannot be resolved\n");
    }

    public static void main(String[] args) {
        TimePeriodValuesDemo3 demo = new TimePeriodValuesDemo3("Processus de Poisson");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}


/*
 * Decompiled with CFR 0_123.
 */
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.DrawingSupplier;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.statistics.HistogramDataset;

public class Histogram2 {
    private static final int BINS = 256;
    private final BufferedImage image;
    private HistogramDataset dataset;
    private XYBarRenderer renderer;

    public Histogram2() {
        this.image = this.getImage();
    }

    private BufferedImage getImage() {
        try {
            return ImageIO.read(new URL("http://i.imgur.com/kxXhIH1.jpg"));
        }
        catch (IOException e) {
            e.printStackTrace(System.err);
            return null;
        }
    }

    private ChartPanel createChartPanel() {
        this.dataset = new HistogramDataset();
        WritableRaster raster = this.image.getRaster();
        int w = this.image.getWidth();
        int h = this.image.getHeight();
        double[] r = new double[w * h];
        r = raster.getSamples(0, 0, w, h, 0, r);
        this.dataset.addSeries((Comparable)((Object)"Etapes"), r, 256);
        JFreeChart chart = ChartFactory.createHistogram("Processus de poisson", "Temps", "", this.dataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = (XYPlot)chart.getPlot();
        this.renderer = (XYBarRenderer)plot.getRenderer();
        this.renderer.setBarPainter(new StandardXYBarPainter());
        Paint[] paintArray = new Paint[]{new Color(-2130771968, true), new Color(-2147418368, true), new Color(-2147483393, true)};
        plot.setDrawingSupplier(new DefaultDrawingSupplier(paintArray, DefaultDrawingSupplier.DEFAULT_FILL_PAINT_SEQUENCE, DefaultDrawingSupplier.DEFAULT_OUTLINE_PAINT_SEQUENCE, DefaultDrawingSupplier.DEFAULT_STROKE_SEQUENCE, DefaultDrawingSupplier.DEFAULT_OUTLINE_STROKE_SEQUENCE, DefaultDrawingSupplier.DEFAULT_SHAPE_SEQUENCE));
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    private void display() {
        JFrame f = new JFrame("Histogram");
        f.setDefaultCloseOperation(3);
        f.add(this.createChartPanel());
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new Histogram2().display();
        }
        );
    }
}


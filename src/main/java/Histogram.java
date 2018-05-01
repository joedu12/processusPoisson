/*
 * Decompiled with CFR 0_123.
 */
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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

public class Histogram {
    private static final int BINS = 256;
    private final BufferedImage image;
    private HistogramDataset dataset;
    private XYBarRenderer renderer;

    public Histogram() {
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
        this.dataset.addSeries((Comparable)((Object)"Red"), r, 256);
        r = raster.getSamples(0, 0, w, h, 1, r);
        this.dataset.addSeries((Comparable)((Object)"Green"), r, 256);
        r = raster.getSamples(0, 0, w, h, 2, r);
        this.dataset.addSeries((Comparable)((Object)"Blue"), r, 256);
        JFreeChart chart = ChartFactory.createHistogram("Histogram", "Value", "Count", this.dataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = (XYPlot)chart.getPlot();
        this.renderer = (XYBarRenderer)plot.getRenderer();
        this.renderer.setBarPainter(new StandardXYBarPainter());
        Paint[] paintArray = new Paint[]{new Color(-2130771968, true), new Color(-2147418368, true), new Color(-2147483393, true)};
        plot.setDrawingSupplier(new DefaultDrawingSupplier(paintArray, DefaultDrawingSupplier.DEFAULT_FILL_PAINT_SEQUENCE, DefaultDrawingSupplier.DEFAULT_OUTLINE_PAINT_SEQUENCE, DefaultDrawingSupplier.DEFAULT_STROKE_SEQUENCE, DefaultDrawingSupplier.DEFAULT_OUTLINE_STROKE_SEQUENCE, DefaultDrawingSupplier.DEFAULT_SHAPE_SEQUENCE));
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel();
        panel.add(new JCheckBox(new VisibleAction(0)));
        panel.add(new JCheckBox(new VisibleAction(1)));
        panel.add(new JCheckBox(new VisibleAction(2)));
        return panel;
    }

    private void display() {
        JFrame f = new JFrame("Histogram");
        f.setDefaultCloseOperation(3);
        f.add(this.createChartPanel());
        f.add((Component)this.createControlPanel(), "South");
        f.add((Component)new JLabel(new ImageIcon(this.image)), "West");
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new Histogram().display();
        }
        );
    }

    private class VisibleAction
    extends AbstractAction {
        private final int i;

        public VisibleAction(int i) {
            this.i = i;
            this.putValue("Name", Histogram.this.dataset.getSeriesKey(i));
            this.putValue("SwingSelectedKey", true);
            Histogram.this.renderer.setSeriesVisible(i, (Boolean)true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Histogram.this.renderer.setSeriesVisible(this.i, (Boolean)(Histogram.this.renderer.getSeriesVisible(this.i) == false));
        }
    }

}


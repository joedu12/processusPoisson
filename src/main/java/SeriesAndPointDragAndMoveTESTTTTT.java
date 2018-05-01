/*
 * Soulenq Joévin, Bastien Enjalbert, Peries Mickael
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import metier.ProcessusPoisson;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.XYItemEntity;
import org.jfree.chart.labels.StandardXYSeriesLabelGenerator;
import org.jfree.chart.labels.XYSeriesLabelGenerator;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.Range;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RefineryUtilities;

public class SeriesAndPointDragAndMoveTESTTTTT
extends ApplicationFrame {
    private int ECHELLE = 100;
    private boolean canMove = false;
    private double finalMovePointY = 0.0;
    private double initialMovePointY = 0.0;
    private JFreeChart jFreeChart = null;
    private ChartPanel chartPanel = null;
    private XYSeries series = new XYSeries((Comparable)((Object)"Etape"));
    private XYSeriesCollection collection = new XYSeriesCollection();
    private XYItemEntity xyItemEntity = null;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){

            @Override
            public void run() {
                SeriesAndPointDragAndMoveTESTTTTT app = new SeriesAndPointDragAndMoveTESTTTTT("Plot");
                app.pack();
                RefineryUtilities.centerFrameOnScreen(app);
                app.setVisible(true);
            }
        });
    }

    public SeriesAndPointDragAndMoveTESTTTTT(String paramString) {
        super(paramString);
        ProcessusPoisson pp = new ProcessusPoisson(100);
        pp.simulerTirages();
        this.jFreeChart = ChartFactory.createXYLineChart("Drag Point", "Points (Index)", "Velocity (m/s)", this.createDataset(pp.getListeNombresGeneres()), PlotOrientation.VERTICAL, true, true, false);
        this.chartPanel = new ChartPanel(this.jFreeChart){

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(640, 480);
            }
        };
        this.chartPanel.setAutoscrolls(false);
        this.chartPanel.setMouseZoomable(false);
        XYPlot localXYPlot = (XYPlot)this.jFreeChart.getPlot();
        XYItemRenderer localXYItemRenderer = localXYPlot.getRenderer();
        localXYItemRenderer.setSeriesStroke(0, new BasicStroke(2.0f));
        XYLineAndShapeRenderer localXYLineAndShapeRenderer = (XYLineAndShapeRenderer)localXYPlot.getRenderer();
        localXYLineAndShapeRenderer.setBaseShapesVisible(true);
        localXYLineAndShapeRenderer.setSeriesFillPaint(0, Color.white);
        localXYLineAndShapeRenderer.setUseFillPaint(true);
        localXYLineAndShapeRenderer.setLegendItemToolTipGenerator(new StandardXYSeriesLabelGenerator("Tooltip {0}"));
        ValueAxis range = localXYPlot.getRangeAxis();
        range.setLowerBound(0.0);
        range.setUpperBound(20.0);
        this.setContentPane(this.chartPanel);
    }

    public XYDataset createDataset(List<Double> listeDuree) {
        double cumulDuree = 0.0;
        Iterator<Double> iterator = listeDuree.iterator();
        while (iterator.hasNext()) {
            double duree = iterator.next();
            this.series.add(cumulDuree += (duree *= (double)this.ECHELLE), 1.0);
        }
        this.collection.addSeries(this.series);
        return this.collection;
    }

    public void movePoint(MouseEvent me) {
        if (this.canMove) {
            double targetPoint;
            int itemIndex = this.xyItemEntity.getItem();
            Point pt = me.getPoint();
            XYPlot xy = this.jFreeChart.getXYPlot();
            Rectangle2D dataArea = this.chartPanel.getChartRenderingInfo().getPlotInfo().getDataArea();
            Point2D p = this.chartPanel.translateScreenToJava2D(pt);
            this.finalMovePointY = xy.getRangeAxis().java2DToValue(p.getY(), dataArea, xy.getRangeAxisEdge());
            double difference = this.finalMovePointY - this.initialMovePointY;
            if (this.series.getY(itemIndex).doubleValue() + difference > xy.getRangeAxis().getRange().getLength() || this.series.getY(itemIndex).doubleValue() + difference < 0.0) {
                this.initialMovePointY = this.finalMovePointY;
            }
            if ((targetPoint = this.series.getY(itemIndex).doubleValue() + difference) > 15.0 || targetPoint < 0.0) {
                return;
            }
            this.series.update((Number)itemIndex, (Number)targetPoint);
            this.jFreeChart.fireChartChanged();
            this.chartPanel.updateUI();
            this.initialMovePointY = this.finalMovePointY;
        }
    }

    public void moveSeries(MouseEvent me) {
        if (this.canMove) {
            double targetPoint;
            Point pt = me.getPoint();
            XYPlot xy = this.jFreeChart.getXYPlot();
            Rectangle2D dataArea = this.chartPanel.getChartRenderingInfo().getPlotInfo().getDataArea();
            Point2D p = this.chartPanel.translateScreenToJava2D(pt);
            this.finalMovePointY = xy.getRangeAxis().java2DToValue(p.getY(), dataArea, xy.getRangeAxisEdge());
            double difference = this.finalMovePointY - this.initialMovePointY;
            int i = 0;
            while (i < this.series.getItemCount()) {
                System.out.println(this.series.getItemCount());
                if (this.series.getY(i).doubleValue() + difference > xy.getRangeAxis().getRange().getLength() || this.series.getY(i).doubleValue() + difference < 0.0) {
                    this.initialMovePointY = this.finalMovePointY;
                }
                ++i;
            }
            i = 0;
            while (i < this.series.getItemCount()) {
                targetPoint = this.series.getY(i).doubleValue() + difference;
                if (targetPoint > 50.0 || targetPoint < 0.0) {
                    return;
                }
                ++i;
            }
            i = 0;
            while (i < this.series.getItemCount()) {
                targetPoint = this.series.getY(i).doubleValue() + difference;
                this.series.update((Number)i, (Number)targetPoint);
                ++i;
            }
            this.jFreeChart.fireChartChanged();
            this.chartPanel.updateUI();
            this.initialMovePointY = this.finalMovePointY;
        }
    }

}


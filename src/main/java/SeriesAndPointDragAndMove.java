/*
 * Soulenq Joévin, Bastien Enjalbert, Peries Mickael
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.PrintStream;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.EntityCollection;
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

public class SeriesAndPointDragAndMove
extends ApplicationFrame
implements ChartMouseListener,
MouseListener,
MouseMotionListener {
    private boolean canMove = false;
    private double finalMovePointY = 0.0;
    private ChartRenderingInfo info = null;
    private double initialMovePointY = 0.0;
    private JFreeChart jFreeChart = null;
    private ChartPanel chartPanel = null;
    private XYSeries series = new XYSeries((Comparable)((Object)"Series"));
    private XYSeriesCollection collection = new XYSeriesCollection();
    private XYItemEntity xyItemEntity = null;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){

            @Override
            public void run() {
                SeriesAndPointDragAndMove app = new SeriesAndPointDragAndMove("Plot");
                app.pack();
                RefineryUtilities.centerFrameOnScreen(app);
                app.setVisible(true);
            }
        });
    }

    public SeriesAndPointDragAndMove(String paramString) {
        super(paramString);
        this.jFreeChart = ChartFactory.createXYLineChart("Drag Point", "Points (Index)", "Velocity (m/s)", this.createDataset(), PlotOrientation.VERTICAL, true, true, false);
        this.chartPanel = new ChartPanel(this.jFreeChart){

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(640, 480);
            }
        };
        this.chartPanel.addChartMouseListener(this);
        this.chartPanel.addMouseMotionListener(this);
        this.chartPanel.addMouseListener(this);
        this.chartPanel.setAutoscrolls(false);
        this.chartPanel.setMouseZoomable(false);
        this.info = this.chartPanel.getChartRenderingInfo();
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

    @Override
    public void chartMouseClicked(ChartMouseEvent paramChartMouseEvent) {
    }

    @Override
    public void chartMouseMoved(ChartMouseEvent paramChartMouseEvent) {
    }

    public XYDataset createDataset() {
        int i = 0;
        while (i < 8) {
            this.series.add((double)i, 4.0);
            ++i;
        }
        this.collection.addSeries(this.series);
        return this.collection;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.movePoint(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.canMove = false;
        this.initialMovePointY = 0.0;
        this.chartPanel.setCursor(new Cursor(0));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        EntityCollection entities = this.info.getEntityCollection();
        ChartMouseEvent cme = new ChartMouseEvent(this.jFreeChart, e, entities.getEntity(x, y));
        ChartEntity entity = cme.getEntity();
        if (entity != null && entity instanceof XYItemEntity) {
            this.xyItemEntity = (XYItemEntity)entity;
        } else if (!(entity instanceof XYItemEntity)) {
            this.xyItemEntity = null;
            return;
        }
        if (this.xyItemEntity == null) {
            return;
        }
        Point pt = e.getPoint();
        XYPlot xy = this.jFreeChart.getXYPlot();
        Rectangle2D dataArea = this.chartPanel.getChartRenderingInfo().getPlotInfo().getDataArea();
        Point2D p = this.chartPanel.translateScreenToJava2D(pt);
        this.initialMovePointY = xy.getRangeAxis().java2DToValue(p.getY(), dataArea, xy.getRangeAxisEdge());
        this.canMove = true;
        this.chartPanel.setCursor(new Cursor(12));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.canMove = false;
        this.initialMovePointY = 0.0;
        this.chartPanel.setCursor(new Cursor(0));
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


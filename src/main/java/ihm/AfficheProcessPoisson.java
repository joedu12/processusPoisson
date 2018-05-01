/*
 * Decompiled with CFR 0_123.
 */
package ihm;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import metier.ProcessusPoisson;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class AfficheProcessPoisson
extends ChartPanel {
    private static final long serialVersionUID = 1;
    private int ECHELLE = 1;
    private ProcessusPoisson pp = null;

    public AfficheProcessPoisson() {
        super(null);
        this.setDisplayToolTips(true);
        this.pp = new ProcessusPoisson(100);
    }

    public void lancerProcessus(int nbSimulation, double lambda, double T) {
        this.pp = new ProcessusPoisson(nbSimulation);
        this.pp.setLambda(lambda);
        this.pp.setT(T);
        this.pp.simulerTirages();
    }

    public void afficherProcessus() {
        IntervalXYDataset dataset = this.createDataset(this.pp.getListeNombresGeneres());
        this.setChart(ChartFactory.createXYBarChart("Processus de poisson", "Temps", false, null, dataset));
        XYPlot plot = (XYPlot)this.getChart().getPlot();
        XYBarRenderer renderer = (XYBarRenderer)plot.getRenderer();
        renderer.setMargin(0.99);
        this.revalidate();
        this.repaint();
    }

    private IntervalXYDataset createDataset(List<Double> listeDuree) {
        XYSeries series = new XYSeries((Comparable)((Object)"Etape"));
        System.out.println(Arrays.toString(listeDuree.toArray()));
        double cumulDuree = 0.0;
        Iterator<Double> iterator = listeDuree.iterator();
        while (iterator.hasNext()) {
            double duree = iterator.next();
            series.add(cumulDuree += (duree *= (double)this.ECHELLE), 1.0);
        }
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        return dataset;
    }

    public String getNbSimulation() {
        return "" + this.pp.getNbTirage();
    }

    public String getLambda() {
        return "" + this.pp.getLambda();
    }

    public String getT() {
        return "" + this.pp.getT();
    }

    public String getIndicLoiExpoTheor() {
        return this.deuxChiffreApresVirgule(this.pp.getUnSurLambdaTheo());
    }

    public String getIndicLoiExpoPrat() {
        return this.deuxChiffreApresVirgule(this.pp.getUnSurLambdaPrat());
    }

    public String getIndicProcessPoissTheor() {
        return this.deuxChiffreApresVirgule(this.pp.getLambdaTTheo());
    }

    public String getIndicProcessPoissPrat() {
        return this.deuxChiffreApresVirgule(this.pp.getLambdaTPrat());
    }

    private String deuxChiffreApresVirgule(Double d) {
        return String.format("%.4f", d);
    }

    public ProcessusPoisson getProcessusPoisson() {
        return this.pp;
    }
}


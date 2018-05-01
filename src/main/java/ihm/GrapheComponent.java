/*
 * Soulenq Joévin, Bastien Enjalbert, Peries Mickael
 */
package ihm;

import java.awt.Component;
import java.io.PrintStream;
import java.util.List;
import javax.swing.JFrame;
import metier.Main;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import outils.Classe;
import outils.ListeClasse;

public class GrapheComponent
extends ChartPanel {
    private static final long serialVersionUID = 1;

    public GrapheComponent(String titreGraphe, ListeClasse aAfficher) {
        super(ChartFactory.createBarChart3D(titreGraphe, "Intervalles", "Nombre de variables aléatoires", GrapheComponent.createDataset(aAfficher), PlotOrientation.VERTICAL, false, false, false));
    }

    private static CategoryDataset createDataset(ListeClasse listeClasse) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Classe<Double> classe : listeClasse.getClasses()) {
            String enteteClasse = "[" + String.format("%.2f", classe.getBorneInf()) + "; " + String.format("%.2f", classe.getBorneSup()) + "]";
            dataset.addValue(classe.nbElement(), enteteClasse, enteteClasse);
        }
        return dataset;
    }

    public static void main(String[] args) throws Exception {
        List<Double> listeAleatoireExponentielle = Main.tirerNBAleatoireWithLoiExponentielle(12.0);
        ListeClasse listeClasseAleatoireExponentielle = Main.trierListe(listeAleatoireExponentielle, 0.0, 1.0);
        GrapheComponent chart = new GrapheComponent("Repartition des VA dans les classes", listeClasseAleatoireExponentielle);
        JFrame frame = new JFrame("TOTO");
        frame.setSize(600, 800);
        frame.add(chart);
        frame.setVisible(true);
        System.out.println("DONE");
    }
}


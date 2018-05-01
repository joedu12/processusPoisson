/*
 * Bastien Enjalbert, Peries Mickael, Soulenq Joévin
 */
package ihm;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import outils.Classe;
import outils.ListeClasse;

public class GrapheComponent extends ChartPanel {
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
 
}


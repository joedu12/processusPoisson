/*
 * Bastien Enjalbert, Peries Mickael, Soulenq Joévin
 */
package metier;

import outils.GenerateurAleatoire;
import outils.ListeClasse;
import outils.lois.LoisProbabilite;

import java.util.ArrayList;
import java.util.List;

public class Main {
    
    public static final int NB_SIMULATION = 1000;
    
    public static final int NB_INTERVALLE = 10;
    
    public static final int LAMBDA = 12; // inutilisé, voir createGraphiqueExponentielleConstatee


    /*public static void main(String[] args) throws Exception {
        List<Double> listeAleatoire = Main.tirerNbAleatoireWithLoiUniforme();
        List<Double> listeAleatoireUniforme = Main.tirerNBAleatoireWithLoiUniformeTheorique();
        List<Double> listeAleatoireExponentielle = Main.tirerNBAleatoireWithLoiExponentielle(12.0);
        List<Double> listeAleatoireNormale = Main.tirerNBAleatoireWithLoiNormale();
        List<Double> listeAleatoireWeibull = Main.tirerNBAleatoireWithLoiWeibull(4.0, 4.0);
        ListeClasse listeClasseAleatoire = Main.trierListe(listeAleatoire, 0.0, 1.0);
        ListeClasse listeClasseAleatoireUniforme = Main.trierListe(listeAleatoireUniforme, 0.0, 1.0);
        ListeClasse listeClasseAleatoireExponentielle = Main.trierListe(listeAleatoireExponentielle, 0.0, 1.0);
        ListeClasse listeClasseAleatoireNormale = Main.trierListe(listeAleatoireNormale, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        ListeClasse listeClasseAleatoireWeibull = Main.trierListe(listeAleatoireWeibull, 0.0, 1.0);
        System.out.println("khi2 (uniforme) = " + Main.khi2(listeClasseAleatoire, listeClasseAleatoireUniforme));
    }*/


    public static List<Double> tirerNBAleatoireWithLoiExponentielle(double lambda) {
        ArrayList<Double> listeNBAleat = new ArrayList<Double>();
        int i = 0;
        while (i < NB_SIMULATION) {
            listeNBAleat.add(LoisProbabilite.loiExponentielle(GenerateurAleatoire.generateurAleatoire(), lambda));
            ++i;
        }
        return listeNBAleat;
    }

    public static ListeClasse listeClassesWithLoiExponentielleTheorique() {
        Double[] populationParClasse = new Double[]{669.0, 210.0, 64.0, 19.0, 6.0, 2.0, 1.0, 0.0, 0.0, 0.0};
        ListeClasse listeClasse = new ListeClasse(10, 0.0, 1.0);
        int i = 0;
        while (i < populationParClasse.length) {
            int j = 0;
            while ((double)j < populationParClasse[i]) {
                listeClasse.getClasses().get(i).ajouter(listeClasse.getClasses().get(i).getBorneInf() + 1.0E-6);
                ++j;
            }
            ++i;
        }
        return listeClasse;
    }

    public static ListeClasse trierListe(List<Double> listeATrier, double borneInf, double borneSup) throws Exception {
        ListeClasse listeClasse = new ListeClasse(NB_INTERVALLE, borneInf, borneSup);
        int i = 0;
        while (i < listeATrier.size()) {
            listeClasse.ajouterElement(listeATrier.get(i));
            ++i;
        }
        return listeClasse;
    }

    public static double khi2(ListeClasse listeTheorique, ListeClasse listeConstate) {
        double khi2 = 0.0;
        int i = 0;
        while (i < listeTheorique.getNbClasse()) {
            int nbConstate = listeConstate.getClasses().get(i).nbElement();
            int nbTheorique = listeTheorique.getClasses().get(i).nbElement();
            if (nbConstate != 0 && nbTheorique != 0) {
                khi2 += Math.pow(nbConstate - nbTheorique, 2.0) / (double)nbTheorique;
            }
            ++i;
        }
        return khi2;
    }
}


/*
 * Bastien Enjalbert, Peries Mickael, Soulenq Joévin
 */
package outils;

import java.util.ArrayList;
import java.util.List;

public class ListeClasse {
    private int nbClasse;
    private double borneInf;
    private double borneSup;
    private List<Classe<Double>> listeClasse = new ArrayList<Classe<Double>>();

    public ListeClasse(int nbClasse, double borneInf, double borneSup) {
        this.nbClasse = nbClasse;
        this.borneInf = borneInf;
        this.borneSup = borneSup;
        double intervalleClasse = (borneSup - borneInf) / (double)nbClasse;
        int i = 0;
        while (i < nbClasse) {
            this.listeClasse.add(new Classe<Double>(borneInf + intervalleClasse * (double)i, borneInf + intervalleClasse * (double)(i + 1)));
            ++i;
        }
    }

    public void ajouterElement(double element) throws Exception {
        if (element < this.borneInf || this.borneSup < element) {
            throw new Exception();
        }
        int i = 0;
        while (i < this.nbClasse) {
            if (element > this.listeClasse.get(i).getBorneInf() && element < this.listeClasse.get(i).getBorneSup()) {
                this.listeClasse.get(i).ajouter(element);
            }
            ++i;
        }
    }

    public String toString() {
        return "ListeClasse [nbClasse=" + this.nbClasse + ", bornes(" + this.borneInf + ", " + this.borneSup + "), nbElements=" + this.getNbElements() + "\nlisteClasse=\n" + this.listeClasse + "]";
    }

    public int getNbClasse() {
        return this.nbClasse;
    }

    public double getBorneInf() {
        return this.borneInf;
    }

    public double getBorneSup() {
        return this.borneSup;
    }

    public List<Classe<Double>> getClasses() {
        return this.listeClasse;
    }

    public int getNbElements() {
        int nbElements = 0;
        for (Classe<Double> classe : this.listeClasse) {
            nbElements += classe.nbElement();
        }
        return nbElements;
    }
}


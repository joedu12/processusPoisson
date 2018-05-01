/*
 * Decompiled with CFR 0_123.
 */
package metier;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import outils.GenerateurAleatoire;
import outils.lois.LoisProbabilite;

public class ProcessusPoisson {
    private List<Double> listeNombresGeneres = new ArrayList<Double>();
    private int nbTirage = 1;
    private double lambda = 12.0;
    private double T = 1.0;

    public ProcessusPoisson(int nbTirage) {
        this();
        this.nbTirage = nbTirage;
    }

    public ProcessusPoisson() {
    }

    private void tirage() {
        double aleatoire = GenerateurAleatoire.generateurAleatoire();
        double aleatLoiExponentielle = LoisProbabilite.loiExponentielle(aleatoire, this.lambda);
        this.listeNombresGeneres.add(aleatLoiExponentielle);
    }

    public void simulerTirages() {
        this.viderListeNombresGeneres();
        int i = 0;
        while (i < this.nbTirage) {
            this.tirage();
            ++i;
        }
    }

    private void viderListeNombresGeneres() {
        this.listeNombresGeneres.removeAll(this.listeNombresGeneres);
    }

    public String toString() {
        return "ProcessusPoisson [nbTirage=" + this.nbTirage + ", lambda=" + this.lambda + ", listeNombresGeneres=" + this.listeNombresGeneres + "]";
    }

    public int getNbTirage() {
        return this.nbTirage;
    }

    public void setNbTirage(int nbTirage) {
        this.nbTirage = nbTirage;
    }

    public double getLambda() {
        return this.lambda;
    }

    public void setLambda(double lambda) {
        this.lambda = lambda;
    }

    public List<Double> getListeNombresGeneres() {
        return this.listeNombresGeneres;
    }

    public double getT() {
        return this.T;
    }

    public void setT(double t) {
        this.T = t;
    }

    public Double getUnSurLambdaTheo() {
        return 1.0 / this.lambda;
    }

    public Double getUnSurLambdaPrat() {
        double cumulDiff = 0.0;
        int nbNombreGeneres = 0;
        int i = 0;
        while (i < this.listeNombresGeneres.size() - 1) {
            cumulDiff += this.listeNombresGeneres.get(i + 1).doubleValue();
            ++nbNombreGeneres;
            ++i;
        }
        return (cumulDiff -= this.listeNombresGeneres.get(0).doubleValue()) / (double)nbNombreGeneres;
    }

    public Double getLambdaTTheo() {
        return this.lambda * this.T;
    }

    public Double getLambdaTPrat() {
        double cumul = 0.0;
        double lambdaT = this.lambda * this.T;
        int nbElementPopulation = 0;
        ArrayList<Integer> populations = new ArrayList<Integer>();
        Double ancienCumul = new Double(this.T);
        int i = 0;
        while (i < this.listeNombresGeneres.size() - 1) {
            if ((cumul += this.listeNombresGeneres.get(i + 1).doubleValue()) > ancienCumul) {
                ancienCumul = ancienCumul + this.T;
                populations.add(nbElementPopulation);
                nbElementPopulation = 1;
            } else {
                ++nbElementPopulation;
            }
            ++i;
        }
        return (double)this.listeNombresGeneres.size() / (double)populations.size();
    }

    public static void main(String[] args) {
        ProcessusPoisson p = new ProcessusPoisson();
        p.simulerTirages();
        p.simulerTirages();
        System.out.println(p);
        p = new ProcessusPoisson(100);
        p.setLambda(6.3);
        p.simulerTirages();
        System.out.println(p);
    }
}


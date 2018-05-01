/*
 * Bastien Enjalbert, Peries Mickael, Soulenq Joévin
 */
package outils;

import java.util.ArrayList;
import java.util.List;

public class Classe<E> {
    private List<E> elements = new ArrayList();
    private E borneInf;
    private E borneSup;

    public Classe(E borneInf, E borneSup) {
        this.borneInf = borneInf;
        this.borneSup = borneSup;
    }

    public void ajouter(E aAjouter) {
        this.elements.add(aAjouter);
    }

    public void supprimer(E aSupprimer) {
        this.elements.remove(aSupprimer);
    }

    public int nbElement() {
        return this.elements.size();
    }

    public boolean estVide() {
        return this.elements.size() == 0;
    }

    public E getBorneInf() {
        return this.borneInf;
    }

    public E getBorneSup() {
        return this.borneSup;
    }

    public String toString() {
        return "Classe [intervalle(" + this.borneInf + ", " + this.borneSup + "), nbElements=" + this.elements.size() + "]\n";
    }
}


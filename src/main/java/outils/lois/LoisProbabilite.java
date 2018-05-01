/*
 * Bastien Enjalbert, Peries Mickael, Soulenq Joévin
 */
package outils.lois;

public class LoisProbabilite {

    public static double loiExponentielle(double nombreAleatoire, double lambda) {
        return -1.0 / lambda * Math.log(nombreAleatoire);
    }

}


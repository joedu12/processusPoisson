/*
 * Soulenq Joévin, Bastien Enjalbert, Peries Mickael
 */
package outils.lois;

public class LoisProbabilite {
    public static double loiUniforme(double nombreAleatoire) {
        return nombreAleatoire;
    }

    public static double loiExponentielle(double nombreAleatoire, double lambda) {
        return -1.0 / lambda * Math.log(nombreAleatoire);
    }

    public static double loiWeibull(double nombreAleatoire, double alpha, double beta) {
        return Math.pow(1.0 / beta, (- Math.log(1.0 - nombreAleatoire)) / Math.pow(beta, alpha));
    }

    public static double loiNormale(double nombreAleatoire1, double nombreAleatoire2) {
        return Math.sqrt(-2.0 * Math.log(nombreAleatoire1)) * Math.cos(6.283185307179586 * nombreAleatoire2);
    }
}


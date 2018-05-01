/*
 * Bastien Enjalbert, Peries Mickael, Soulenq Joévin
 */
package outils;

public class GenerateurAleatoire {
    public static double generateurAleatoire() {
        return Math.random();
    }

    public static int generateurAleatoireInt(int min, int max) {
        return (int)Math.round(Math.random() * (double)(max - min + 1)) + min;
    }

    public static double generateurAleatoireDouble(double min, double max) {
        return min + Math.random() * (max - min);
    }
}


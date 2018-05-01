/*
 * Decompiled with CFR 0_123.
 */
package test;

import outils.GenerateurAleatoire;

public class TestGenerateurLois {
    public static void main(String[] args) {
        int i = 0;
        while (i < 10) {
            System.out.println("G\u00e9n\u00e9rateur al\u00e9atoire basique : " + GenerateurAleatoire.generateurAleatoire());
            ++i;
        }
        i = 0;
        while (i < 10) {
            System.out.println("G\u00e9n\u00e9rateur al\u00e9atoire entier : " + GenerateurAleatoire.generateurAleatoireInt(0, 1));
            ++i;
        }
        i = 0;
        while (i < 10) {
            System.out.println("G\u00e9n\u00e9rateur al\u00e9atoire double : " + GenerateurAleatoire.generateurAleatoireDouble(0.0, 1.0));
            ++i;
        }
        int ok = 0;
        int nok = 0;
        int nbAleatoire = 0;
        int i2 = 0;
        while (i2 < 30) {
            nbAleatoire = GenerateurAleatoire.generateurAleatoireInt(7, 15);
            System.out.println("G\u00e9n\u00e9rateur entier entre 7 et 15 : " + nbAleatoire);
            if (nbAleatoire >= 7 && nbAleatoire <= 15) {
                ++ok;
            } else {
                ++nok;
            }
            ++i2;
        }
        System.out.println("R\u00e9sultat test : OK = " + ok + " NOK = " + nok);
        int ok2 = 0;
        int nok2 = 0;
        double nbAleatoire2 = 0.0;
        int i3 = 0;
        while (i3 < 30) {
            nbAleatoire2 = GenerateurAleatoire.generateurAleatoireDouble(7.5, 15.5);
            System.out.println("G\u00e9n\u00e9rateur entier entre 7.5 et 15.5 : " + nbAleatoire2);
            if (nbAleatoire2 >= 7.5 && nbAleatoire2 <= 15.5) {
                ++ok2;
            } else {
                ++nok2;
            }
            ++i3;
        }
        System.out.println("R\u00e9sultat test 2 : OK = " + ok2 + " NOK = " + nok2);
    }
}


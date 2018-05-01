/*
 * Soulenq Joévin, Bastien Enjalbert, Peries Mickael
 */
package ihm;

import ihm.GrapheComponent;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import metier.Main;
import outils.ListeClasse;

public class Generateur_ihm {
    private JFrame frame;
    private JTextField nbValeur;
    private JTextField expLambda;
    private JTextField weibullLambda;
    private JTextField weibullBeta;
    JPanel zoneGraphe = new JPanel();

    private void creationGrapheLoiExponentielleConstatee() throws Exception {
        List<Double> listeAleatoireExponentielle = Main.tirerNBAleatoireWithLoiExponentielle(12.0);
        ListeClasse listeClasseAleatoireExponentielle = Main.trierListe(listeAleatoireExponentielle, 0.0, 1.0);
        GrapheComponent chart = new GrapheComponent("Repartition des VA dans les classes", listeClasseAleatoireExponentielle);
        this.zoneGraphe.add(chart);
    }

    private void initialize() {
        this.frame = new JFrame();
        this.frame.setBounds(100, 100, 900, 785);
        this.frame.setDefaultCloseOperation(3);
        this.frame.getContentPane().setLayout(null);
        JButton btnLoiExponentielle = new JButton("Loi Exponentielle");
        JButton btnLoiDeWeibull = new JButton("Loi de Weibull");
        JButton btnLoiNormale = new JButton("Loi Normale");
        JLabel lblGnrateurDeVariables = new JLabel("Générateur de variables aléatoires pour les lois suivantes : ");
        JLabel lblNombreDeValeurs = new JLabel("Nombre de valeurs a générer :");
        this.nbValeur = new JTextField();
        this.expLambda = new JTextField();
        this.weibullLambda = new JTextField();
        this.weibullBeta = new JTextField();
        lblGnrateurDeVariables.setFont(new Font("Calibri", 0, 24));
        lblGnrateurDeVariables.setBounds(12, 13, 633, 34);
        this.frame.getContentPane().add(lblGnrateurDeVariables);
        btnLoiNormale.setBounds(12, 98, 130, 25);
        this.frame.getContentPane().add(btnLoiNormale);
        btnLoiNormale.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent arg0) {
                try {
                    Generateur_ihm.this.creationGrapheLoiExponentielleConstatee();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        btnLoiDeWeibull.setBounds(12, 136, 130, 25);
        this.frame.getContentPane().add(btnLoiDeWeibull);
        btnLoiDeWeibull.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent arg0) {
            }
        });
        btnLoiExponentielle.setBounds(12, 174, 130, 25);
        this.frame.getContentPane().add(btnLoiExponentielle);
        btnLoiExponentielle.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent arg0) {
            }
        });
        lblNombreDeValeurs.setFont(new Font("Calibri", 0, 16));
        lblNombreDeValeurs.setBounds(22, 60, 226, 25);
        this.frame.getContentPane().add(lblNombreDeValeurs);
        this.nbValeur.setBounds(253, 61, 72, 22);
        this.frame.getContentPane().add(this.nbValeur);
        this.nbValeur.setColumns(10);
        this.expLambda.setBounds(154, 175, 116, 22);
        this.frame.getContentPane().add(this.expLambda);
        this.expLambda.setColumns(10);
        this.expLambda.setText("Lambda");
        this.weibullLambda.setBounds(154, 137, 116, 22);
        this.frame.getContentPane().add(this.weibullLambda);
        this.weibullLambda.setColumns(10);
        this.weibullLambda.setText("Lambda");
        this.weibullBeta.setBounds(282, 137, 116, 22);
        this.frame.getContentPane().add(this.weibullBeta);
        this.weibullBeta.setColumns(10);
        this.weibullBeta.setText("Beta");
        this.zoneGraphe.setBounds(12, 226, 858, 499);
        this.frame.getContentPane().add(this.zoneGraphe);
    }

    public Generateur_ihm() {
        this.initialize();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){

            @Override
            public void run() {
                try {
                    Generateur_ihm window = new Generateur_ihm();
                    window.frame.setVisible(true);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}


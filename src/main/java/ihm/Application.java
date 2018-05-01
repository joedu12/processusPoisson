/*
 * Bastien Enjalbert, Peries Mickael, Soulenq Joévin
 */
package ihm;

import metier.Main;
import outils.ListeClasse;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Application
extends JFrame
implements ActionListener {
    private JPanel contentPane;
    private JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    private JButton btnLoiExponentielle;
    private JPanel panel_bouton;
    private JPanel panel_khi2;
    private JLabel lblKhi;
    private JLabel lblError;
    private JTextField lblKhi2;
    private JPanel panel_information;
    private JPanel panel_titre;
    private JLabel lblTitre;

    public static void main(String[] args) throws Exception{
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        EventQueue.invokeLater(new Runnable(){

            @Override
            public void run() {
                try {
                    Application frame = new Application();
                    frame.setVisible(true);
                    frame.getContentPane().setBackground(Color.BLACK);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Application() {
        this.setDefaultCloseOperation(3);
        this.setBounds(100, 100, 1200, 800);
        this.setTitle("Test de lois stochastiques par vérification avec le Khi2");

        this.contentPane = new JPanel();
        this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(this.contentPane);
        this.contentPane.setLayout(new BorderLayout(0, 0));
        this.panel_information = new JPanel();
        this.contentPane.add(this.panel_information, "North");

        this.panel_information.setLayout(new BorderLayout(0, 0));
        this.panel_titre = new JPanel();
        this.panel_information.add(this.panel_titre, "North");
        this.lblTitre = new JLabel("Test de lois stochastiques par vérification avec le Khi2");
        this.lblTitre.setFont(new Font("Calibri", 0, 24));

        this.panel_titre.add(this.lblTitre);
        this.panel_bouton = new JPanel();
        this.panel_information.add(this.panel_bouton, "Center");
        this.btnLoiExponentielle = new JButton("Loi exponentielle");
        this.panel_bouton.add(this.btnLoiExponentielle);
        this.btnLoiExponentielle.addActionListener(this);
        this.panel_khi2 = new JPanel();
        this.panel_information.add(this.panel_khi2, "South");
        this.lblKhi = new JLabel("Khi² = ");
        this.panel_bouton.add(this.lblKhi);
        this.lblKhi2 = new JTextField();
        this.panel_bouton.add(this.lblKhi2);
        this.lblKhi2.setEditable(false);
        this.lblKhi2.setColumns(10);
        this.lblError= new JLabel();
        this.panel_bouton.add(this.lblError);
        this.splitPane.setResizeWeight(0.5);
        this.splitPane.setBottomComponent(new JPanel());
        this.splitPane.setTopComponent(new JPanel());
        this.contentPane.add(this.splitPane, "Center");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.btnLoiExponentielle)) {
            ListeClasse constatee = this.createGraphiqueExponentielleConstatee();
            ListeClasse theorique = this.createGraphiqueExponentielleTheorique();
            if (constatee != null && theorique != null) {
                this.calculKhi2(theorique, constatee); 
                this.lblTitre.setText("Loi exponentielle");
            }
        }
    }

    private void calculKhi2(ListeClasse theorique, ListeClasse constatee) {
        double khi2 = Main.khi2(theorique, constatee);
        this.lblKhi2.setText(String.format("%.2f", khi2));
        if(khi2 > 16.92){
            this.lblError.setText("Rejet de l'hypothèse: khi² > 16.92");
            this.lblKhi2.setForeground(Color.red);
        } else {
            this.lblError.setText("");
            this.lblKhi2.setForeground(Color.black);
        }
    }

    private ListeClasse createGraphiqueExponentielleTheorique() {
        try {
            ListeClasse listeClasseAleatoireExponentielle = Main.listeClassesWithLoiExponentielleTheorique();
            this.splitPane.setTopComponent(new ihm.GrapheComponent("Valeurs théoriques loi exponentielle", listeClasseAleatoireExponentielle));
            return listeClasseAleatoireExponentielle;
        }
        catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
    }

    private ListeClasse createGraphiqueExponentielleConstatee() {
        List<Double> listeAleatoireExponentielle = Main.tirerNBAleatoireWithLoiExponentielle(12.0);
        try {
            ListeClasse listeClasseAleatoireExponentielle = Main.trierListe(listeAleatoireExponentielle, 0.0, 1.0);
            this.splitPane.setBottomComponent(new ihm.GrapheComponent("Valeurs constatées loi exponentielle", listeClasseAleatoireExponentielle));
            return listeClasseAleatoireExponentielle;
        }
        catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
    }

}


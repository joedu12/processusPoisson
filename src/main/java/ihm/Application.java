/*
 * Soulenq Joévin, Bastien Enjalbert, Peries Mickael
 */
package ihm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import metier.Main;
import outils.ListeClasse;

public class Application
extends JFrame
implements ActionListener {
    private JPanel contentPane;
    private JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    private JButton btnLoiExponentielle;
    private JButton btnLoiUniforme;
    private JButton btnLoiNormale;
    //private JButton btnLoiWeibull;
    private JPanel panel_bouton;
    private JPanel panel_khi2;
    private JLabel lblKhi;
    private JLabel lblError;
    private JTextField lblKhi2;
    private JPanel panel_information;
    private JPanel panel_titre;
    private JLabel lblTitre;

    public static void main(String[] args) {
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
        this.setBounds(100, 100, 779, 522);
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
        this.btnLoiUniforme = new JButton("Loi uniforme");
        this.panel_bouton.add(this.btnLoiUniforme);
        this.btnLoiUniforme.addActionListener(this);
        this.btnLoiNormale = new JButton("Loi normale");
        this.panel_bouton.add(this.btnLoiNormale);
        this.btnLoiNormale.addActionListener(this);
        //this.btnLoiWeibull = new JButton("Loi weibull");
        //this.panel_bouton.add(this.btnLoiWeibull);
        this.panel_khi2 = new JPanel();
        this.panel_information.add(this.panel_khi2, "South");
        this.lblKhi = new JLabel("Khi² = ");
        this.panel_khi2.add(this.lblKhi);
        this.lblKhi2 = new JTextField();
        this.panel_khi2.add(this.lblKhi2);
        this.lblKhi2.setEditable(false);
        this.lblKhi2.setColumns(10);
        //this.btnLoiWeibull.addActionListener(this);
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
        } else if (e.getSource().equals(this.btnLoiUniforme)) {
            ListeClasse constatee = this.createGraphiqueUniformeConstatee();
            ListeClasse theorique = this.createGraphiqueUniformeTheorique();
            if (constatee != null && theorique != null) {
                this.calculKhi2(theorique, constatee);
                this.lblTitre.setText("Loi uniforme");
            }
        } else if (e.getSource().equals(this.btnLoiNormale)) {
            ListeClasse constatee = this.createGraphiqueNormaleConstatee();
            ListeClasse theorique = this.createGraphiqueNormaleTheorique();
            if (constatee != null && theorique != null) {
                this.calculKhi2(theorique, constatee);
                this.lblTitre.setText("Loi normale");
            }
        }
        /*
        else if (e.getSource().equals(this.btnLoiWeibull)) {
            ListeClasse constatee = this.createGraphiqueWeibullConstatee();
            ListeClasse theorique = this.createGraphiqueWeibullTheorique();
            if (constatee != null && theorique != null) {
                this.calculKhi2(theorique, constatee);
                this.lblTitre.setText("Loi weibull");
            }
        } */
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
            this.splitPane.setTopComponent(new GrapheComponent("Valeur théorique loi exponentielle", listeClasseAleatoireExponentielle));
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
            this.splitPane.setBottomComponent(new GrapheComponent("Valeur constatée loi exponentielle", listeClasseAleatoireExponentielle));
            return listeClasseAleatoireExponentielle;
        }
        catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
    }

    private ListeClasse createGraphiqueUniformeTheorique() {
        List<Double> listeAleatoireUniforme = Main.tirerNBAleatoireWithLoiUniformeTheorique();
        try {
            ListeClasse listeClasseAleatoireUniforme = Main.trierListe(listeAleatoireUniforme, 0.0, 1.0);
            this.splitPane.setTopComponent(new GrapheComponent("Valeur théorique loi uniforme", listeClasseAleatoireUniforme));
            return listeClasseAleatoireUniforme;
        }
        catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
    }

    private ListeClasse createGraphiqueUniformeConstatee() {
        List<Double> listeAleatoire = Main.tirerNbAleatoireWithLoiUniforme();
        try {
            ListeClasse listeClasseAleatoire = Main.trierListe(listeAleatoire, 0.0, 1.0);
            this.splitPane.setBottomComponent(new GrapheComponent("Valeur constatée loi uniforme", listeClasseAleatoire));
            return listeClasseAleatoire;
        }
        catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
    }

    private ListeClasse createGraphiqueNormaleConstatee() {
        List<Double> listeAleatoireLoiNormale = Main.tirerNBAleatoireWithLoiNormale();
        try {
            ListeClasse listeClasseAleatoireLoiNormale = Main.trierListe(listeAleatoireLoiNormale, -5.0, 5.0);
            this.splitPane.setBottomComponent(new GrapheComponent("Valeur constatée loi normale", listeClasseAleatoireLoiNormale));
            return listeClasseAleatoireLoiNormale;
        }
        catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
    }

    private ListeClasse createGraphiqueNormaleTheorique() {
        try {
            ListeClasse listeClasseAleatoireNoramle = Main.listeClassesWithLoiNormaleTheorique();
            this.splitPane.setTopComponent(new GrapheComponent("Valeur théorique loi exponentielle", listeClasseAleatoireNoramle));
            return listeClasseAleatoireNoramle;
        }
        catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
    }
/*
    private ListeClasse createGraphiqueWeibullConstatee() {
        List<Double> listeAleatoireLoiWeibull = Main.tirerNBAleatoireWithLoiWeibull(4.0, 4.0);
        try {
            ListeClasse listeClasseAleatoireLoiWeibull = Main.trierListe(listeAleatoireLoiWeibull, 0.0, 10.0);
            this.splitPane.setBottomComponent(new GrapheComponent("Valeur constatée loi weibull", listeClasseAleatoireLoiWeibull));
            return listeClasseAleatoireLoiWeibull;
        }
        catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
    }

    private ListeClasse createGraphiqueWeibullTheorique() {
        try {
            ListeClasse listeClasseAleatoireWeibull = Main.listeClassesWithLoiWeibullTheorique();
            this.splitPane.setTopComponent(new GrapheComponent("Valeur théorique loi exponentielle", listeClasseAleatoireWeibull));
            return listeClasseAleatoireWeibull;
        }
        catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
    }
*/
}


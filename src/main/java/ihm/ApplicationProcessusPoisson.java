/*
 * Soulenq Joévin, Bastien Enjalbert, Peries Mickael
 */
package ihm;

import org.jfree.ui.ApplicationFrame;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplicationProcessusPoisson
        extends ApplicationFrame
        implements ActionListener,
        DocumentListener {
    private static final long serialVersionUID = 1;
    private JButton btnLancerProcessus;
    private JButton btnAfficherProcessus;
    private AfficheProcessPoisson processPoisson;
    private JPanel panel_action;
    private JLabel lblNombreSimulation;
    private JTextField textFieldNBSimulation;
    private JPanel panel_2;
    private JLabel lblLambda;
    private JTextField textFieldLambda;
    private JButton btnMAJDonnees;
    private JPanel panel_NbSimu;
    private JPanel panel_Lambda;
    private JLabel lblPriodeDeTemps;
    private JPanel panel_saisieT;
    private JTextField textFieldT;
    private JPanel panel_verification;
    private JPanel panel_verifLoiExpo;
    private JPanel panel_verifProcessPoisson;
    private JPanel panel_LoiExpoTheor;
    private JLabel lblLoiExpoLambda;
    private JLabel lblLoiExpoTheor;
    private JLabel lblLoiExpoTheoriq;
    private JPanel panel_LoiExpoPrat;
    private JLabel lblLoiExpoPrat;
    private JLabel lblLoiExpoLambda2;
    private JLabel lblLoiExpoPratiq;
    private JPanel panel_ProcessPoissonTheor;
    private JLabel lblProcessPoissonTheor;
    private JLabel lblProcessPoissonLambdaT;
    private JLabel lblProcessPoissonTheoriq;
    private JPanel panel_ProcessPoissonPrat;
    private JLabel lblProcessPoissonPrat;
    private JLabel lblProcessPoissonLambdaTPrat;
    private JLabel lblProcessPoissonPratiq;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    ApplicationProcessusPoisson window = new ApplicationProcessusPoisson();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ApplicationProcessusPoisson() {
        super("Simulation Processus de poisson");
        this.initialize();
    }

    private void initialize() {
        this.setBounds(100, 100, 1012, 504);
        this.setDefaultCloseOperation(3);
        this.getContentPane().setLayout(new BorderLayout(0, 0));
        JPanel panel = new JPanel();
        this.getContentPane().add(panel, "North");
        panel.setLayout(new BorderLayout(0, 0));
        this.processPoisson = new AfficheProcessPoisson();
        processPoisson.setBackground(Color.WHITE);
        this.processPoisson.setBorder(new TitledBorder(null, "Affichage", 4, 2, null, null));
        this.panel_2 = new JPanel();
        this.panel_2.setBorder(new TitledBorder(null, "Paramètres", 4, 2, null, null));
        panel_2.setBackground(Color.WHITE);
        panel.add(this.panel_2, "North");
        this.panel_2.setLayout(new FlowLayout(1, 5, 5));

        this.panel_Lambda = new JPanel();
        this.panel_2.add(this.panel_Lambda);
        this.lblLambda = new JLabel("Lambda");
        this.panel_Lambda.add(this.lblLambda);
        panel_Lambda.setBackground(Color.WHITE);
        this.textFieldLambda = new JTextField();
        this.panel_Lambda.add(this.textFieldLambda);
        this.textFieldLambda.setColumns(10);
        this.textFieldLambda.getDocument().addDocumentListener(this);
        
        this.btnMAJDonnees = new JButton("Valider les paramètres");
        this.btnMAJDonnees.setEnabled(false);
        this.btnMAJDonnees.addActionListener(this);
        this.panel_saisieT = new JPanel();
        this.panel_2.add(this.panel_saisieT);
        this.lblPriodeDeTemps = new JLabel("Durée");
        panel_saisieT.setBackground(Color.WHITE);
        this.panel_saisieT.add(this.lblPriodeDeTemps);
        this.textFieldT = new JTextField();
        this.panel_saisieT.add(this.textFieldT);
        this.textFieldT.setColumns(10);
        this.textFieldT.getDocument().addDocumentListener(this);

        this.panel_NbSimu = new JPanel();
        this.panel_2.add(this.panel_NbSimu);
        this.lblNombreSimulation = new JLabel("Nombre de tirages");
        this.panel_NbSimu.add(this.lblNombreSimulation);
        panel_NbSimu.setBackground(Color.WHITE);
        this.textFieldNBSimulation = new JTextField();
        this.panel_NbSimu.add(this.textFieldNBSimulation);
        this.textFieldNBSimulation.setColumns(10);
        this.textFieldNBSimulation.getDocument().addDocumentListener(this);

        this.panel_action = new JPanel();
        panel_action.setBackground(Color.WHITE);
        this.panel_action.setBorder(new TitledBorder(null, "Actions", 4, 2, null, null));
        panel.add(this.panel_action, "Center");
        this.panel_action.setLayout(new FlowLayout(1, 5, 5));
        this.panel_action.add(this.btnMAJDonnees);
        this.btnLancerProcessus = new JButton("Tirer les nombres");
        this.panel_action.add(this.btnLancerProcessus);
        this.btnLancerProcessus.setEnabled(false);
        this.btnLancerProcessus.addActionListener(this);
        this.btnAfficherProcessus = new JButton("Lancer la simulation");
        this.panel_action.add(this.btnAfficherProcessus);
        this.btnAfficherProcessus.setEnabled(false);
        this.panel_verification = new JPanel();
        this.panel_verification.setBorder(new TitledBorder(null, "Vérification", 4, 2, null, null));
        panel_verification.setBackground(Color.WHITE);
        panel.add(this.panel_verification, "South");
        this.panel_verification.setLayout(new GridLayout(1, 2));
        this.panel_verifLoiExpo = new JPanel();
        panel_verifLoiExpo.setBackground(Color.WHITE);
        this.panel_verifLoiExpo.setBorder(new TitledBorder(null, "Loi exponentielle", 4, 2, null, null));
        this.panel_verification.add(this.panel_verifLoiExpo);
        this.panel_verifLoiExpo.setLayout(new BorderLayout(0, 0));
        this.panel_LoiExpoTheor = new JPanel();
        panel_LoiExpoTheor.setBackground(Color.WHITE);
        this.panel_verifLoiExpo.add(this.panel_LoiExpoTheor, "North");
        this.lblLoiExpoTheor = new JLabel("(THEORIQUE)   ");
        this.lblLoiExpoTheor.setFont(new Font("Calibri", 1, 11));
        this.panel_LoiExpoTheor.add(this.lblLoiExpoTheor);
        this.lblLoiExpoLambda = new JLabel("1/ lambda = ");
        this.panel_LoiExpoTheor.add(this.lblLoiExpoLambda);
        this.lblLoiExpoTheoriq = new JLabel("");
        this.panel_LoiExpoTheor.add(this.lblLoiExpoTheoriq);
        this.panel_LoiExpoPrat = new JPanel();
        panel_LoiExpoPrat.setBackground(Color.WHITE);
        this.panel_verifLoiExpo.add(this.panel_LoiExpoPrat, "South");
        this.lblLoiExpoPrat = new JLabel("(PRATIQUE)   ");
        this.lblLoiExpoPrat.setFont(new Font("Calibri", 1, 11));
        this.panel_LoiExpoPrat.add(this.lblLoiExpoPrat);
        this.lblLoiExpoLambda2 = new JLabel("1/ lambda = ");
        this.panel_LoiExpoPrat.add(this.lblLoiExpoLambda2);
        this.lblLoiExpoPratiq = new JLabel("");
        this.panel_LoiExpoPrat.add(this.lblLoiExpoPratiq);
        this.panel_verifProcessPoisson = new JPanel();
        this.panel_verifProcessPoisson.setBorder(new TitledBorder(null, "Processus de Poisson", 4, 2, null, null));
        this.panel_verification.add(this.panel_verifProcessPoisson);
        this.panel_verifProcessPoisson.setLayout(new BorderLayout(0, 0));
        this.panel_ProcessPoissonTheor = new JPanel();
        panel_ProcessPoissonTheor.setBackground(Color.WHITE);
        this.panel_verifProcessPoisson.add(this.panel_ProcessPoissonTheor, "North");
        this.lblProcessPoissonTheor = new JLabel("(THEORIQUE)   ");
        this.lblProcessPoissonTheor.setFont(new Font("Calibri", 1, 11));
        this.panel_ProcessPoissonTheor.add(this.lblProcessPoissonTheor);
        this.lblProcessPoissonLambdaT = new JLabel("lambda * T = ");
        this.panel_ProcessPoissonTheor.add(this.lblProcessPoissonLambdaT);
        this.lblProcessPoissonTheoriq = new JLabel("");
        this.panel_ProcessPoissonTheor.add(this.lblProcessPoissonTheoriq);
        this.panel_ProcessPoissonPrat = new JPanel();
        panel_verifProcessPoisson.setBackground(Color.WHITE);
        this.panel_verifProcessPoisson.add(this.panel_ProcessPoissonPrat, "South");
        this.lblProcessPoissonPrat = new JLabel("(PRATIQUE)   ");
        this.lblProcessPoissonPrat.setFont(new Font("Calibri", 1, 11));
        this.panel_ProcessPoissonPrat.add(this.lblProcessPoissonPrat);
        this.lblProcessPoissonLambdaTPrat = new JLabel("lambda * T = ");
        this.panel_ProcessPoissonPrat.add(this.lblProcessPoissonLambdaTPrat);
        this.lblProcessPoissonPratiq = new JLabel("");
        panel_ProcessPoissonPrat.setBackground(Color.WHITE);
        this.panel_ProcessPoissonPrat.add(this.lblProcessPoissonPratiq);
        this.btnAfficherProcessus.addActionListener(this);
        this.getContentPane().add(this.processPoisson, "Center");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.btnAfficherProcessus)) {
            if (this.processPoisson != null) {
                this.processPoisson.afficherProcessus();
                this.btnAfficherProcessus.setEnabled(false);
                this.btnLancerProcessus.setEnabled(true);
                this.lblLoiExpoTheoriq.setText(this.processPoisson.getIndicLoiExpoTheor());
                this.lblLoiExpoPratiq.setText(this.processPoisson.getIndicLoiExpoPrat());
                this.lblProcessPoissonPratiq.setText(this.processPoisson.getIndicProcessPoissPrat());
                this.lblProcessPoissonTheoriq.setText(this.processPoisson.getIndicProcessPoissTheor());
            }
        } else if (e.getSource().equals(this.btnLancerProcessus)) {
            this.processPoisson.lancerProcessus(this.processPoisson.getProcessusPoisson().getNbTirage(), this.processPoisson.getProcessusPoisson().getLambda(), this.processPoisson.getProcessusPoisson().getT());
            this.btnLancerProcessus.setEnabled(false);
            this.btnAfficherProcessus.setEnabled(true);
            this.btnMAJDonnees.setEnabled(true);
        } else if (e.getSource().equals(this.btnMAJDonnees)) {
            String nbSimulation = this.textFieldNBSimulation.getText();
            String lambda = this.textFieldLambda.getText();
            String t = this.textFieldT.getText();
            boolean nbSimuOk = nbSimulation.matches("\\d+");
            boolean lambdaOk = lambda.matches("\\d+\\.\\d+");
            boolean tOk = t.matches("\\d+");
            if (nbSimuOk && lambdaOk && tOk) {
                this.processPoisson.getProcessusPoisson().setLambda(Double.parseDouble(lambda));
                this.processPoisson.getProcessusPoisson().setNbTirage(Integer.parseInt(nbSimulation));
                this.processPoisson.getProcessusPoisson().setT(Integer.parseInt(t));
                this.textFieldLambda.setText(this.processPoisson.getLambda());
                this.textFieldNBSimulation.setText(this.processPoisson.getNbSimulation());
                this.textFieldT.setText(this.processPoisson.getT());
                this.btnLancerProcessus.setEnabled(true);
                this.btnAfficherProcessus.setEnabled(false);
            }
        }
        this.revalidate();
        this.repaint();
    }

    private void validerMajDonnees() {
        String nbSimulation = this.textFieldNBSimulation.getText();
        String lambda = this.textFieldLambda.getText();
        String t = this.textFieldT.getText();
        boolean nbSimuOk = nbSimulation.matches("\\d+");
        boolean lambdaOk = lambda.matches("\\d+\\.\\d+");
        boolean tOk = t.matches("\\d+");
        this.btnMAJDonnees.setEnabled(nbSimuOk && lambdaOk && tOk);
    }

    @Override
    public void changedUpdate(DocumentEvent arg0) {
        this.validerMajDonnees();
    }

    @Override
    public void insertUpdate(DocumentEvent arg0) {
        this.validerMajDonnees();
    }

    @Override
    public void removeUpdate(DocumentEvent arg0) {
        this.validerMajDonnees();
    }

}

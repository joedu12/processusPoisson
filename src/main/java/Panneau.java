/*
 * Soulenq Joévin, Bastien Enjalbert, Peries Mickael
 */
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

public class Panneau
extends JViewport {
    private static final long serialVersionUID = 1;

    public Panneau() {
        this.setBackground(Color.PINK);
        this.setSize(3000, 580);
    }

    @Override
    public void paintComponent(Graphics g) {
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){

            @Override
            public void run() {
                JFrame fenetre = new JFrame("test panneau");
                JScrollPane scrollPane = new JScrollPane();
                scrollPane.setViewport(new Panneau());
                fenetre.add(scrollPane);
                fenetre.setSize(800, 600);
                scrollPane.setSize(800, 580);
                fenetre.setVisible(true);
            }
        });
    }

}


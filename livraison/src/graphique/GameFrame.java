package graphique;

import javax.swing.*;
import java.awt.*;
import battle.*;

/**
 * 
 * La classe GameFrame représente la fenêtre principale de l'application de la
 * bataille navale.
 */
public class GameFrame extends JFrame {
    /**
     * 
     * Crée une instance de GameFrame.
     * 
     * @param b l'instance de la classe Battle à utiliser dans la fenêtre
     */
    public GameFrame(Battle b) {
        this.setTitle("Bataille navale");
        this.setSize(1080, 720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        setLayout(new GridLayout(1, 2));
        HumainPlayerPanel h = new HumainPlayerPanel(b, true);

        add(h);
        add(new HumainPlayerPanel(b, false));

        Controler c = new Controler(h, b);

    }
}

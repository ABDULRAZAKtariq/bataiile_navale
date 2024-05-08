package graphique;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import graphique.mvc.*;
import players.Player;
import sea.*;
import battle.*;

/**
 * 
 * Panel représentant la grille de jeu. Il affiche les cases
 * de la grille sous forme de boutons interactifs.
 * 
 * C'est aussi un écouteur, quand il y a un changement dans le modéle on appelle
 * modeleMisAJour
 * et cette fonction met la grille à jour
 */
public class HumainPlayerPanel extends JPanel implements EcouteurModele {

    /* La grille composé des cellules de mer */
    private SeaCell[][] grille;
    /* Une grille de boutons interactifs */
    private GameButton[][] buttons;
    /*
     * Une instance de JPanel qui va représenter la grille (la classe représente la
     * grille + axe x + axe y)
     */
    private JPanel gridPanel;
    /* Une instance du modéle */
    private Battle b;
    /*
     * Un boolean qui nous dis c'est pour quel affichage, c'est grace à ce boolean
     * qu'on sait s'il faut afficher les bateaux dès le début ou pas
     */
    private boolean isHumain;

    /**
     * Constructeur de la classe HumainPlayerPanel.
     * 
     * @param b        la bataille à laquelle appartient le joueur
     * @param isHumain true si le joueur est humain, false sinon
     */
    public HumainPlayerPanel(Battle b, boolean isHumain) {
        super();
        this.b = b;
        (this.b).ajoutEcouteur(this);
        /*
         * Un boolean qui nous dis c'est pour quel affichage, c'est grace à ce boolean
         * qu'on sait s'il faut afficher les bateaux dès le début ou pas
         */
        this.isHumain = isHumain;
        setLayout(new BorderLayout());
        if (this.isHumain) {
            this.grille = this.b.getGrilleJoueur2();
        } else {
            this.grille = this.b.getGrilleJoueur1();
        }
        /* Une grille de boutons interactifs */
        this.buttons = new GameButton[Sea.SIZE_X][Sea.SIZE_Y];

        GridLayout g = new GridLayout(Sea.SIZE_X, Sea.SIZE_Y);
        g.setVgap(10);
        g.setHgap(10);
        gridPanel = new JPanel(g);

        if (isHumain) {
            for (int i = 0; i < grille.length; i++) {
                for (int j = 0; j < grille[0].length; j++) {
                    buttons[i][j] = new GameButtonH(grille[i][j]);
                    gridPanel.add(buttons[i][j]);
                }
            }
        } else {
            for (int i = 0; i < grille.length; i++) {
                for (int j = 0; j < grille[0].length; j++) {
                    buttons[i][j] = new GameButtonB(grille[i][j]);
                    gridPanel.add(buttons[i][j]);
                }
            }
        }

        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(Color.blue);
        gridPanel.setBackground(Color.blue);
        container.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        gridPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        container.add(gridPanel, BorderLayout.CENTER);

        JPanel xAxisPanel = new JPanel(new GridLayout(1, Sea.SIZE_X + 1));
        xAxisPanel.add(new JLabel(""));
        for (int x = 1; x <= Sea.SIZE_X; x++) {
            xAxisPanel.add(new JLabel("" + x));
        }

        JPanel yAxisPanel = new JPanel(new GridLayout(Sea.SIZE_Y, 1));
        for (int y = 1; y <= Sea.SIZE_Y; y++) {
            yAxisPanel.add(new JLabel("" + y));
        }

        setBorder(new EmptyBorder(10, 10, 10, 10));

        add(xAxisPanel, BorderLayout.NORTH);

        add(yAxisPanel, BorderLayout.WEST);

        add(container, BorderLayout.CENTER);
    }

    /**
     * Met à jour le panel lorsque le modèle est mis à jour.
     * 
     * @param source le modèle qui a été mis à jour
     */
    @Override
    public void modeleMisAJour(Object source) {

        for (int i = 0; i < Sea.SIZE_Y; i++) {
            for (int j = 0; j < Sea.SIZE_X; j++) {
                this.buttons[i][j].update();
            }
        }

        if ((this.b).getWinner() != null && (this.b).isOver()) {
            Player winner = (this.b).getWinner();
            Player joueur1 = (this.b).getPlayer1();
            if (isHumain) {
                JOptionPane.showMessageDialog(null, "Vous avez " + (winner == joueur1 ? "Gagné" : "perdu"));
            }
        }
    }

    /**
     * 
     * Renvoie la grille de boutons de jeu associée à ce panneau joueur.
     * 
     * @return la grille de boutons de jeu associée à ce panneau joueur.
     */
    public GameButton[][] getGrille() {
        return this.buttons;
    }

}

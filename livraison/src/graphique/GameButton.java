package graphique;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

import sea.SeaCell;

/**
 * 
 * Classe abstraite représentant un bouton dans la grille de jeu graphique.
 */
public abstract class GameButton extends JButton {
    /**
     * 
     * La cellule correspondante au bouton.
     */
    protected SeaCell cellule;

    /**
     * 
     * Constructeur de la classe GameButton.
     * 
     * @param cellule la cellule correspondante au bouton.
     */
    public GameButton(SeaCell cellule) {
        /**
         * 
         * La cellule correspondante au bouton.
         */
        this.cellule = cellule;
        setBackground(Color.blue);
        setBorder(null);
        Font biggerFont = new Font("Arial", Font.PLAIN, 100);
        setFont(biggerFont);
    }

    /**
     * 
     * Méthode abstraite appelée pour mettre à jour l'apparence du bouton.
     */
    public abstract void personalUpdate();

    /**
     * 
     * Met à jour l'apparence du bouton en fonction de l'état de la cellule
     * correspondante.
     */
    public void update() {
        if (cellule.isDestroyed() && cellule.contientBoat()) {
            setBackground(Color.RED);

        } else if (cellule.isDestroyed() && !(cellule.contientBoat())) {
            setBackground(Color.green);
        }

        personalUpdate();
    }

    /**
     * 
     * Retourne la cellule correspondante au bouton.
     * 
     * @return la cellule correspondante au bouton.
     */
    public SeaCell getCell() {
        return this.cellule;
    }

}

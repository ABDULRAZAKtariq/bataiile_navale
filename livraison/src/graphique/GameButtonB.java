package graphique;

import java.awt.Color;

import javax.swing.BorderFactory;

import sea.SeaCell;
import boat.*;

/**
 * 
 * La classe GameButtonB étend GameButton et représente un bouton pour
 * l'affichage de la grille de l'adversaire (ma grille sur la quelle
 * l'adversaire joue)
 * qui contient un bateau. Elle gère également la bordure du bouton pour
 * représenter le bateau de manière plus
 * visuelle.
 * Les bateux sont affiché dès le début.
 */
public class GameButtonB extends GameButton {
    /**
     * Constructeur de la classe GameButtonB.
     * 
     * @param cellule La cellule associée au bouton.
     */
    public GameButtonB(SeaCell cellule) {
        super(cellule);

        if (cellule.contientBoat()) {
            Boat b = cellule.getBoat();
            if (cellule == b.getCells()[0]) {
                if (!b.isHorizontale()) {
                    setBorder(BorderFactory.createMatteBorder(8, 8, 8, 0, Color.BLACK));
                } else {
                    setBorder(BorderFactory.createMatteBorder(8, 8, 0, 8, Color.BLACK));
                }
            } else if (cellule == b.getCells()[b.getSize() - 1]) {
                if (!b.isHorizontale()) {
                    setBorder(BorderFactory.createMatteBorder(8, 0, 8, 8, Color.BLACK));
                } else {
                    setBorder(BorderFactory.createMatteBorder(0, 8, 8, 8, Color.BLACK));
                }
            } else {
                if (!b.isHorizontale()) {
                    setBorder(BorderFactory.createMatteBorder(8, 0, 8, 0, Color.BLACK));
                } else {
                    setBorder(BorderFactory.createMatteBorder(0, 8, 0, 8, Color.BLACK));
                }
            }
        }
    }

    /*
     * Cette méthode ne fait rien car l'affichage est géré dans le constructeur de
     * cette classe.
     */
    @Override
    public void personalUpdate() {
    }
}

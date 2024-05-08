package graphique;

import java.awt.Color;

import javax.swing.BorderFactory;

import sea.SeaCell;
import boat.*;

/**
 * 
 * La classe GameButtonH hérite de la classe GameButton et représente un bouton
 * de la grille de jeu pour les bateaux détruits.
 * 
 * Elle permet de personnaliser l'apparence du bouton en fonction de l'état du
 * bateau qui occupe la cellule associée.
 * 
 * Les bateaux sont affiché qu'une fois qu'ils sont détruit, autrement dit c'est
 * les boutons de la grille du bot(c'est la grille sur
 * laquelle l'utilisateur joue)
 */
public class GameButtonH extends GameButton {
    /**
     * 
     * Constructeur de la classe GameButtonH qui prend en paramètre une cellule de
     * mer associée au bouton.
     * 
     * @param cellule La cellule de mer associée au bouton.
     */
    public GameButtonH(SeaCell cellule) {
        super(cellule);
    }

    /**
     * 
     * Cette méthode est appelée lorsqu'un changement est apporté à la cellule
     * associée au bouton.
     * 
     * Elle permet de personnaliser l'apparence du bouton en fonction de l'état du
     * bateau qui occupe la cellule associée.
     * 
     * Si le bateau est détruit, la méthode définit une bordure épaisse noire pour
     * les cellules extrémités du bateau
     * 
     */
    public void personalUpdate() {
        if (cellule.contientBoat()) {
            Boat b = cellule.getBoat();
            if (!b.isAlive()) {
                if (cellule == b.getCells()[0]) {
                    if (!b.isHorizontale()) {
                        setBorder(BorderFactory.createMatteBorder(10, 10, 10, 0, Color.BLACK));
                    } else {
                        setBorder(BorderFactory.createMatteBorder(10, 10, 0, 10, Color.BLACK));
                    }
                } else if (cellule == b.getCells()[b.getSize() - 1]) {
                    if (!b.isHorizontale()) {
                        setBorder(BorderFactory.createMatteBorder(10, 0, 10, 10, Color.BLACK));
                    } else {
                        setBorder(BorderFactory.createMatteBorder(0, 10, 10, 10, Color.BLACK));
                    }
                } else {
                    if (!b.isHorizontale()) {
                        setBorder(BorderFactory.createMatteBorder(10, 0, 10, 0, Color.BLACK));
                    } else {
                        setBorder(BorderFactory.createMatteBorder(0, 10, 0, 10, Color.BLACK));
                    }
                }
            }

        }
    }
}

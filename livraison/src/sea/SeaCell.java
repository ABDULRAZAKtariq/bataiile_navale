package sea;

import boat.*;

/**
 * Classe représentant une cellule de la grille de la mer.
 */
public class SeaCell {
    /* entier qui indique la coordonnée X de la cellule sur la grille de la mer */
    private int positionX;
    /* entier qui indique la coordonnée Y de la cellule sur la grille de la mer */
    private int positionY;
    /* Boolean qui indique si la cellule a été touchée par un tir ennemi */
    private boolean isDestroyed;

    /* Bateau présent sur la cellule, si présent sinon null */
    private Boat boat;

    /**
     * Constructeur de la classe SeaCell.
     *
     * @param positionX Coordonnée X de la cellule sur la grille de la mer.
     * @param positionY Coordonnée Y de la cellule sur la grille de la mer.
     */
    public SeaCell(int positionX, int positionY) {

        this.positionX = positionX;
        this.positionY = positionY;
        this.isDestroyed = false;
        this.boat = null;
    }

    /**
     * Renvoie un tableau contenant les coordonnées X et Y de la cellule sur la
     * grille de la mer.
     *
     * @return Un tableau contenant les coordonnées X et Y de la cellule.
     */
    public int[] getPosition() {
        return new int[] { this.positionX, this.positionY };
    }

    /**
     * Renvoie la coordonnée X de la cellule sur la grille de la mer.
     *
     * @return La coordonnée X de la cellule.
     */
    public int getX() {
        return this.positionX;
    }

    /**
     * Renvoie la coordonnée Y de la cellule sur la grille de la mer.
     *
     * @return La coordonnée Y de la cellule.
     */
    public int getY() {
        return this.positionY;
    }

    /**
     * Indique si la cellule a été touchée par un tir ennemi.
     *
     * @return True si la cellule a été touchée, false sinon.
     */
    public boolean isDestroyed() {
        return this.isDestroyed;
    }

    /**
     * Indique si la cellule contient un bateau.
     *
     * @return True si la cellule contient un bateau, false sinon.
     */
    public boolean contientBoat() {
        return (this.boat != null);
    }

    /**
     * Définit le bateau présent sur la cellule.
     *
     * @param b Le bateau présent sur la cellule (peut être null).
     */
    public void setBoat(Boat b) {
        this.boat = b;
    }

    /**
     * Renvoie le bateau présent sur la cellule.
     *
     * @return Le bateau présent sur la cellule, ou null si la cellule ne contient
     *         pas de bateau.
     */
    public Boat getBoat() {
        return this.boat;
    }

    /**
     * Définit si la cellule a été touchée par un tir ennemi.
     *
     * @param bool True si la cellule a été touchée, false sinon.
     */
    public void setIsDestroyed(boolean bool) {
        this.isDestroyed = bool;
    }

    /**
     * Renvoie une chaîne de caractères représentant la cellule.
     *
     * @return Une chaîne de caractères représentant la cellule.
     */
    @Override
    public String toString() {
        return "(" + this.positionX + ", " + this.positionY + ")" + "\n isDestroyed : " + this.isDestroyed
                + "\n contientBoat : " + this.contientBoat();
    }

}
package boat;

import sea.*;
import boat.exceptions.*;
import players.*;

/**
 * 
 * La classe Boat représente un bateau dans le jeu de bataille navale.
 * 
 * Chaque bateau est constitué de plusieurs SeaCell (cellules de la mer) et
 * appartient à un joueur.
 * 
 * Chaque bateau a une taille et une direction (vers le haut ou vert le bas).
 * 
 */
public class Boat {
    /*
     * Taille maximale du bateau (exemple : 5 pour une grille de 10 * 10 ).
     */
    public static final int MAX_SIZE = Sea.SIZE_X / 2;

    /*
     * Taille minimale du bateau (exemple : 2 pour une grille de 10 * 10 ).
     */
    public static final int MIN_SIZE = (Sea.SIZE_X) / 5;

    /*
     * Tableau des cellules de la mer qui constitent le bateau
     */
    private SeaCell[] cells;

    /*
     * Le joueur à qui le bateau appartient.
     */
    private Player joueur;

    /*
     * La taille du bateau.
     */
    private int size;

    /*
     * Le sens du bateau(horizontale ou pas).
     */
    private boolean isHorizontale;

    /**
     * 
     * Constructeur de la classe Boat.
     * 
     * @param cells  tableau de SeaCell constituant le bateau.
     * 
     * @param joueur le joueur auquel le bateau appartient.
     * 
     * @throws CellPositionException     si la position d'une cellule est
     *                                   incorrecte.
     * 
     * @throws BoatSizeExceededException si la taille du bateau ne respecte pas
     *                                   l'intervale [MIN_SIZE, MAX_SIZE].
     * 
     * @throws Exception                 si une autre erreur dans la liste suivante
     *                                   survient :
     *                                   -Le bateau n'est pas droit
     *                                   -L'une des cellules du bateau sort de la
     *                                   mer
     *                                   -Le bateau est droit mais ses cellules ne
     *                                   se suivent pas
     *                                   -L'une des cellules du bateau est deja
     *                                   prise
     */
    public Boat(SeaCell[] cells, Player joueur)
            throws CellPositionException, BoatSizeExceededException, Exception {

        this.cells = cells;
        this.joueur = joueur;
        this.size = (this.cells).length;
        this.isHorizontale = false;
        if (this.size > MAX_SIZE || this.size < MIN_SIZE) {
            throw new BoatSizeExceededException(this.size);
        }
        if (!checkCells()) {
            throw new CellPositionException(this);
        } else {
            for (SeaCell cell : this.cells) {
                cell.setBoat(this);
            }
        }
    }

    /**
     * 
     * Getter pour les cellules du bateau.
     * 
     * @return un tableau de SeaCell.
     */
    public SeaCell[] getCells() {
        return this.cells;
    }

    /**
     * 
     * Getter pour la taille du bateau.
     * 
     * @return la taille du bateau.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * 
     * Getter pour le joueur auquel le bateau appartient.
     * 
     * @return le joueur auquel le bateau appartient.
     */
    public Player getPlayer() {
        return this.joueur;
    }

    /**
     * 
     * Vérifie si les cellules du bateau sont correctement positionnées.
     * 
     * @return true si les cellules sont correctement positionnées, false sinon.
     * 
     * @throws Exception si une erreur survient.
     */
    public boolean checkCells() throws Exception {
        boolean sameRow = true;
        boolean sameColumn = true;
        boolean consecutiveCells = true;

        int row = this.cells[0].getY();

        int column = this.cells[0].getX();

        for (int i = 1; i < size; i++) {
            if (cells[i].getY() != row) {
                sameRow = false;
            }
            if (cells[i].getX() != column) {
                sameColumn = false;
            }

            int x1 = cells[i].getX();
            int x2 = cells[i - 1].getX();
            int y1 = cells[i].getY();
            int y2 = cells[i - 1].getY();

            boolean same = (x1 == x2 && y1 == y2);
            if ((Math.abs(x1 - x2) > 1) ||
                    (Math.abs(y1 - y2) > 1) || same) {
                consecutiveCells = false;
            }
        }

        boolean inBounds = true;
        for (int i = 0; i < size; i++) {
            if (cells[i].getX() < 0 || cells[i].getX() >= Sea.SIZE_X ||
                    cells[i].getY() < 0 || cells[i].getY() >= Sea.SIZE_Y) {
                inBounds = false;
                break;
            }
        }

        boolean contientBoat = false;

        for (int i = 0; i < size; i++) {
            if (cells[i].contientBoat()) {
                contientBoat = true;
                break;
            }
        }

        if (!(sameRow || sameColumn)) {
            throw new Exception("Le bateau n'est pas droit (il doit être sur une ligne ou une colonne)");
        } else if (!inBounds) {
            throw new Exception("L'une des cellules du bateau sort de la mer (hors de la grille)");
        } else if (!consecutiveCells) {
            throw new Exception("Les cellule du bateau sont sur la même ligne/colonne mais ils ne se suivent pas");
        } else if (contientBoat) {
            throw new Exception("L'une des cellules du bateau est deja prise");

        }

        if (sameColumn) {
            isHorizontale = true;
        }
        return (sameRow || sameColumn) && inBounds && consecutiveCells && (!contientBoat);
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de ce bateau.
     *
     * @return une chaîne de caractères représentant ce bateau
     */
    @Override
    public String toString() {
        String display = "";
        for (int i = 0; i < this.size; i++) {
            display += this.cells[i] + "\n\n";
        }
        return display;
    }

    /**
     * Vérifie si le bateau est en vie en vérifiant si toutes ses cellules ne sont
     * pas détruites.
     *
     * @return true si le bateau est en vie, false sinon
     */
    public boolean isAlive() {
        for (SeaCell sc : this.cells) {
            if (!(sc.isDestroyed())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Vérifie si le bateau est horizontal en fonction de la position de ses
     * cellules.
     *
     * @return true si le bateau est horizontal, false sinon
     */
    public boolean isHorizontale() {
        return this.isHorizontale;
    }
}

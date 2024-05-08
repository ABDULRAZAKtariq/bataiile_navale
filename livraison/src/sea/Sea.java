package sea;

import java.util.*;

import boat.*;
import sea.exceptions.*;
import players.*;

/**
 * 
 * La classe Sea représente la grille de jeu, elle est composée d'un tableau de
 * SeaCell pour représenter chaque case
 * de la grille et d'une liste de bateaux (allBoats) pour stocker tous les
 * bateaux placés sur la grille.
 * Elle permet l'ajout de bateaux sur la grille et l'affichage de la grille.
 * Elle permet également de récupérer le tableau de SeaCell, le joueur associé
 * et la liste de tous les bateaux.
 * Elle contient une méthode pour vérifier si tous les bateaux sont détruits.
 */
public class Sea {
    /**
     * La taille de la grille de jeu ( axe x + axe y).
     */
    public static int SIZE = 20;
    /**
     * La taille de la grille en X.
     */
    public static int SIZE_X = SIZE / 2;
    /**
     * La taille de la grille en Y.
     */
    public static int SIZE_Y = SIZE / 2;

    /**
     * Le nombre maximum de bateaux pouvant être placés sur la grille (exemple : 5
     * pour une grille de 10 * 10).
     */
    public static int NOMBRE_DE_BATEAU = SIZE_X / 2;
    /**
     * Le tableau de SeaCell pour représenter chaque case de la grille.
     */
    private SeaCell[][] sea = new SeaCell[SIZE_Y][SIZE_X];
    /**
     * La liste de tous les bateaux placés sur la grille.
     */
    private ArrayList<Boat> allBoats;
    /**
     * Le joueur associé à la grille.
     */
    private Player joueur;

    /**
     * Constructeur de la classe Sea.
     * Initialise le tableau de SeaCell avec une instance de SeaCell pour chaque
     * case de la grille.
     * Initialise la liste de tous les bateaux placés sur la grille avec une
     * ArrayList vide.
     * Initialise le joueur associé à la grille avec le joueur passé en paramètre.
     * 
     * @param joueur Le joueur associé à la grille.
     */
    public Sea(Player joueur) {
        for (int y = 0; y < SIZE_Y; y++) {
            for (int x = 0; x < SIZE_X; x++) {
                sea[y][x] = new SeaCell(x, y);
            }
        }
        this.allBoats = new ArrayList<>();
        this.joueur = joueur;
    }

    /**
     * Ajoute un bateau sur la grille.
     * Lance une exception de type InvalideBoatsNumberException si le nombre maximum
     * de bateaux est déjà atteint.
     * Ajoute le bateau à la liste de tous les bateaux placés sur la grille sinon.
     * si le bateau est crée alors il a passé toutes les vérifications
     * (vérifications dans la class Boat).
     * 
     * @param b Le bateau à ajouter sur la grille.
     * @throws InvalideBoatsNumberException Si le nombre maximum de bateaux est déjà
     *                                      atteint.
     */
    public void addBoat(Boat b) throws InvalideBoatsNumberException {
        if (((this.allBoats).size() + 1) > NOMBRE_DE_BATEAU) {
            throw new InvalideBoatsNumberException((this.allBoats).size() + 1);
        } else {
            this.allBoats.add(b);
        }
    }

    /**
     * Affiche la grille de jeu avec les bateaux et les cases touchées
     */
    public void displayGrid() {
        System.out.print(" ");
        for (int i = 0; i < Sea.SIZE_X; i++) {
            System.out.print(" " + i);
        }
        System.out.println();

        for (int i = 0; i < Sea.SIZE_Y; i++) {
            System.out.print(i + " ");

            for (int j = 0; j < Sea.SIZE_X; j++) {
                SeaCell cell = this.sea[i][j];

                if (!cell.isDestroyed() && !cell.contientBoat()) {
                    System.out.print("O ");
                } else if (cell.isDestroyed()) {
                    System.out.print("x ");
                } else {
                    System.out.print("- ");
                }
            }

            System.out.println();
        }
    }

    /**
     * Renvoie la grille de jeu
     * 
     * @return la grille de jeu
     */
    public SeaCell[][] getSea() {
        return this.sea;
    }

    /**
     * Renvoie le joueur auquel appartient la mer
     * 
     * @return le joueur auquel appartient la mer
     */
    public Player getPlayer() {
        return this.joueur;
    }

    /**
     * Renvoie tous les bateaux présents dans la mer
     * 
     * @return une liste de tous les bateaux dans la mer
     */
    public ArrayList<Boat> getAllBoats() {
        return this.allBoats;
    }

    /**
     * Vérifie si tous les bateaux dans la mer sont détruits
     * 
     * @return true si tous les bateaux sont détruits, false sinon
     */
    public boolean allBoatDestroyed() {
        for (Boat b : this.allBoats) {
            if (b.isAlive()) {
                return false;
            }
        }
        return true;
    }

}
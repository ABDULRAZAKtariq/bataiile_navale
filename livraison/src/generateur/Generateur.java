package generateur;

import java.util.Random;

import boat.Boat;
import players.*;
import sea.*;

/**
 * 
 * La classe Generateur permet de générer aléatoirement les bateaux pour les
 * deux joueurs elle respecte beacoup de chose.
 * -Tout d'abord les deux joueurs vont avoir le même nombre de bateau(définit à
 * l'avance avec Sea.NOMBRE_DE_BATEAU)
 * ce nombre est calculé par rapport à la taille de la grille du jeu.
 * 
 * -Les deux joueurs vont avoir les mêmes tailles de bateaux, ces tailles sont
 * généré aléatoirement au début
 * et ils respectent l'intervalle [Boat.MIN_SIZE, Boat.MAX_SIZE].
 * 
 * 
 */
public class Generateur {
    /* Instance de la classe Random pour la génération de nombre aléatoire */
    private Random random;
    /* Le joueur 1 */
    private Player joueur1;
    /* Le joueur 2 */
    private Player joueur2;
    /*
     * Le tableau des tailles des bateaux (généré aléatoirement au début
     * et il respect l'intervalle [Boat.MIN_SIZE, Boat.MAX_SIZE]) la taille de ce
     * bateau est Sea.NOMBRE_DE_BATEAU
     */
    private int[] sizes;
    /* La mer du joueur 1 */
    private Sea seaJoueur1;
    /* La mer du joueur 2 */
    private Sea seaJoueur2;

    /**
     * 
     * Constructeur de la classe Generateur.
     * 
     * @param joueur1 le joueur 1
     * @param joueur2 le joueur 2
     */
    public Generateur(Player joueur1, Player joueur2) {
        /*
         * Le tableau des tailles des bateaux (généré aléatoirement au début
         * et il respect l'intervalle [Boat.MIN_SIZE, Boat.MAX_SIZE]) la taille de ce
         * bateau est Sea.NOMBRE_DE_BATEAU
         */
        this.sizes = new int[Sea.NOMBRE_DE_BATEAU];
        /* Instance de la classe Random pour la génération de nombre aléatoire */
        this.random = new Random(System.currentTimeMillis());
        /* Le joueur 1 */
        this.joueur1 = joueur1;
        /* Le joueur 2 */
        this.joueur2 = joueur2;

        /* La mer du joueur 1 */
        this.seaJoueur1 = new Sea(joueur1);
        /* La mer du joueur 2 */
        this.seaJoueur2 = new Sea(joueur2);

        for (int i = 0; i < Sea.NOMBRE_DE_BATEAU; i++) {
            sizes[i] = random.nextInt(Boat.MAX_SIZE - Boat.MIN_SIZE + 1) + Boat.MIN_SIZE;
        }

        createBoat(joueur1);
        createBoat(joueur2);

    }

    /**
     * 
     * Méthode qui crée les bateaux pour un joueur donné.
     * 
     * @param p le joueur pour lequel les bateaux doivent être créés
     */
    public void createBoat(Player p) {

        Sea playerSea = null;
        if (p == joueur1) {
            playerSea = this.seaJoueur1;

        } else {
            playerSea = this.seaJoueur2;
        }

        SeaCell[][] grille = playerSea.getSea();

        for (int nBoat = 0; nBoat < sizes.length; nBoat++) {

            int size = sizes[nBoat];
            Boat b = null;

            do {
                int x = random.nextInt(Sea.SIZE_X);
                int y = random.nextInt(Sea.SIZE_Y);

                // 1 droite, 0 haut
                int direction = random.nextInt(2);

                SeaCell[] boatCells = new SeaCell[size];
                int vec1 = (direction == 0) ? 1 : 0;
                int vec2 = (direction == 1) ? 1 : 0;

                boolean correct = true;
                for (int i = 0; i < size; i++) {
                    if (0 <= (y + (i * vec1)) && (y + (i * vec1)) < Sea.SIZE_Y && (x + (i * vec2)) >= 0
                            && (x + (i * vec2)) < Sea.SIZE_X) {
                        boatCells[i] = grille[y + (i * vec1)][x + (i * vec2)];
                    } else {
                        correct = false;
                    }

                    if (correct) {
                        try {
                            b = new Boat(boatCells, p);
                        } catch (Exception e) {
                            b = null;
                        }
                    }

                }
            } while (b == null);
            try {
                playerSea.addBoat(b);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }

    }

    /**
     * 
     * Renvoie la grille de mer du joueur 1.
     * 
     * @return la grille de mer du joueur 1
     */
    public Sea getPlayer1Sea() {
        return this.seaJoueur1;
    }

    /**
     * 
     * Renvoie la grille de mer du joueur 2.
     * 
     * @return la grille de mer du joueur 2
     */
    public Sea getPlayer2Sea() {
        return this.seaJoueur2;
    }

    /**
     * 
     * Renvoie le joueur 1.
     * 
     * @return le joueur 1
     */
    public Player getPlayer1() {
        return this.joueur1;
    }

    /**
     * 
     * Renvoie le joueur 2.
     * 
     * @return le joueur 2
     */
    public Player getPlayer2() {
        return this.joueur2;
    }
}

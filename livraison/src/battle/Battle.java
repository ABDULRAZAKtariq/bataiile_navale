package battle;

import generateur.Generateur;

import boat.*;
import sea.*;
import players.*;
import graphique.mvc.*;

/**
 * 
 * La classe Battle représente un mode de jeu pour la bataille navale. Elle
 * permet de créer un objet Battle en donnant un joueur humain et un joueur bot.
 * Le mode de jeu est ensuite lancé avec la méthode startBattle(), qui retourne
 * le gagnant de la partie.
 */
public class Battle extends AbstractModeleEcoutable {
    /* Le joueur 1 */
    private Player joueur1;
    /* Le joueur 2 */
    private Player joueur2;
    /* La mer du joueur 1 */
    private Sea merJoueur1;
    /* La mer du joueur 2 */
    private Sea merJoueur2;

    /* La grille du joueur 1 */
    private SeaCell[][] grilleJoueur1;
    /* La grille du joueur 2 */
    private SeaCell[][] grilleJoueur2;

    /* Boolean qui indique si la partie est fini */
    private boolean end;

    /* Le joueur au moment t */
    private Player curentPlayer;

    /* Le joueur qui a gagné (null si la partie n'est pas encore finit) */
    private Player winner;

    /**
     * 
     * Constructeur de la classe Battle.
     * 
     * @param Joueur1 le joueur humain (clavier ou GUI)
     */
    public Battle(Player Joueur1) {
        /* Le joueur 1 (Humain) */
        this.joueur1 = Joueur1;
        /* Le joueur 2 (Bot) */
        this.joueur2 = new Bot();

        Generateur g = new Generateur(joueur1, joueur2);

        /* La mer du joueur 1 */
        this.merJoueur1 = g.getPlayer1Sea();
        /* La mer du joueur 2 */
        this.merJoueur2 = g.getPlayer2Sea();

        /* La grille du joueur 1 */
        this.grilleJoueur1 = merJoueur1.getSea();
        /* La grille du joueur 2 */
        this.grilleJoueur2 = merJoueur2.getSea();

        /* Boolean qui indique si la partie est fini (false au début) */
        this.end = false;
        /* Le joueur qui a gagné (null si la partie n'est pas encore finit) */
        this.winner = null;
        /* Le joueur au moment t (le joueur 1 au début) */
        this.curentPlayer = joueur1;
    }

    /**
     * 
     * Lance le mode de jeu Battle.
     * 
     * @return le gagnant de la partie
     */
    public Player startBattle() {
        while (!end) {
            joueur1.displayForPlayer(this);
            fireChangement();
            if (curentPlayer == joueur1) {
                joueur1.play(merJoueur2);
                curentPlayer = joueur2;
                if (merJoueur2.allBoatDestroyed()) {
                    end = true;
                    winner = joueur1;
                }
            } else {
                joueur2.play(merJoueur1);
                curentPlayer = joueur1;
                if (merJoueur1.allBoatDestroyed()) {
                    end = true;
                    winner = joueur2;
                }
            }

        }
        joueur1.displayForPlayer(this);
        fireChangement();
        return winner;
    }

    /**
     * 
     * Affiche les deux grilles de jeu côte à côte pour le joueur humain.
     */
    public void displayGrids() {
        // Afficher les indices horizontaux
        SeaCell[][] grilleBot = this.grilleJoueur2;
        SeaCell[][] grilleJoueur = this.grilleJoueur1;

        System.out.println("\n\n");

        System.out.print("   ");
        for (int x = 0; x < Sea.SIZE_X; x++) {
            System.out.print(" " + x);
        }
        System.out.println("   " + "  ");
        System.out
                .println("  " + "+-" + "-".repeat(Sea.SIZE_X * 2) + "-+   " + "+-" + "-".repeat(Sea.SIZE_X * 2) + "-+");

        // Afficher les deux grilles côte à côte
        for (int y = 0; y < Sea.SIZE_Y; y++) {
            // Afficher l'indice vertical
            System.out.print(y + " |");
            for (int x = 0; x < Sea.SIZE_X; x++) {
                // Afficher la représentation de la case sur la première grille
                if (grilleBot[y][x].isDestroyed() && grilleBot[y][x].contientBoat()) {
                    System.out.print(" x");
                } else if (grilleBot[y][x].isDestroyed() && !(grilleBot[y][x].contientBoat())) {
                    System.out.print(" !");
                } else {
                    System.out.print("  ");
                }
            }
            // Ajouter un espace entre les deux grilles
            System.out.print(" |   |");
            // Afficher la représentation de la case sur la deuxième grille
            for (int x = 0; x < Sea.SIZE_X; x++) {
                if (grilleJoueur[y][x].isDestroyed() && grilleJoueur[y][x].contientBoat()) {
                    System.out.print(" x");
                } else if (grilleJoueur[y][x].isDestroyed() && !(grilleJoueur[y][x].contientBoat())) {
                    System.out.print(" !");
                } else if (grilleJoueur[y][x].contientBoat()) {
                    Boat b = grilleJoueur[y][x].getBoat();
                    if (grilleJoueur[y][x] == b.getCells()[0]) {
                        if (!b.isHorizontale()) {
                            System.out.print(" <");

                        } else {
                            System.out.print(" ^");
                        }
                    } else if (grilleJoueur[y][x] == b.getCells()[b.getSize() - 1]) {
                        if (!b.isHorizontale()) {
                            System.out.print(" >");

                        } else {
                            System.out.print(" v");
                        }
                    } else {
                        if (!b.isHorizontale()) {
                            System.out.print(" -");

                        } else {
                            System.out.print(" |");
                        }
                    }
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println(" |");
        }

        // Afficher la dernière ligne d'indices horizontaux
        System.out
                .println("  " + "+-" + "-".repeat(Sea.SIZE_X * 2) + "-+   " + "+-" + "-".repeat(Sea.SIZE_X * 2) + "-+");
        System.out.print("  ");
        for (int x = 0; x < Sea.SIZE_X; x++) {
            System.out.print(" " + x);
        }
        System.out.println("   " + "  ");
    }

    /**
     * 
     * Cette méthode retourne la grille de jeu du joueur 1
     * 
     * @return la grille de jeu du joueur 1
     */
    public SeaCell[][] getGrilleJoueur1() {
        return this.grilleJoueur1;
    }

    /**
     * 
     * Cette méthode retourne la grille de jeu du joueur 2
     * 
     * @return la grille de jeu du joueur 2
     */
    public SeaCell[][] getGrilleJoueur2() {
        return this.grilleJoueur2;
    }

    /**
     * 
     * Cette méthode retourne la mer du joueur 1
     * 
     * @return la mer du joueur 1
     */
    public Sea getPlayer1Sea() {
        return this.merJoueur1;
    }

    /**
     * 
     * Cette méthode retourne la mer du joueur 2
     * 
     * @return la mer du joueur 2
     */
    public Sea getPlayer2Sea() {
        return this.merJoueur2;
    }

    /**
     * 
     * Cette méthode retourne le joueur gagnant
     * 
     * @return le joueur gagnant
     */
    public Player getWinner() {
        return this.winner;
    }

    /**
     * 
     * Cette méthode vérifie si la partie est terminée
     * 
     * @return true si la partie est terminée, false sinon
     */
    public boolean isOver() {
        return this.end;
    }

    /**
     * 
     * Cette méthode retourne le joueur 1
     * 
     * @return le joueur 1
     */
    public Player getPlayer1() {
        return this.joueur1;
    }

    /**
     * 
     * Cette méthode retourne le joueur 2
     * 
     * @return le joueur 2
     */
    public Player getPlayer2() {
        return this.joueur2;
    }
}

package players;

import java.util.Scanner;

import battle.Battle;
import sea.*;

/**
 * 
 * La classe HumainClavier implémente l'interface Player et permet à un joueur
 * humain d'interagir avec le jeu via la console.
 * Elle utilise un scanner pour récupérer les entrées de l'utilisateur et permet
 * à ce dernier de jouer un coup en entrant des coordonnées x et y.
 * Elle affiche également les grilles de jeu pour permettre au joueur de
 * visualiser l'état de la partie.
 */
public class HumainClavier implements Player {
    /* Une instance de scanner pour récupérer les entrées de l'utilisateur */
    private Scanner sc;
    /* Un boolean qui dis si le joueur a joué son tour ou pas */
    private boolean played;

    /**
     * Constructeur de la classe HumainClavier.
     * Initialise un scanner pour récupérer les entrées de l'utilisateur et
     * initialise played à false.
     */
    public HumainClavier() {
        /* Une instance de scanner pour récupérer les entrées de l'utilisateur */
        this.sc = new Scanner(System.in);
        /* Un boolean qui dis si le joueur a joué son tour ou pas */
        this.played = false;
    }

    /**
     * Permet au joueur humain de jouer un coup en entrant les coordonnées x et y
     * via la console.
     * Vérifie que les valeurs entrées pour x et y sont bien des nombres entiers.
     * Si les coordonnées entrées sont valides, le coup est joué.
     * Si une erreur survient lors du coup, le joueur est invité à réessayer.
     * 
     * @param otherPlayerSea La grille de l'autre joueur.
     */
    @Override
    public void play(Sea otherPlayerSea) {
        String erreur = "";
        do {
            erreur = "";
            System.out.print("Entrez un nombre pour x : ");
            String xStr = sc.nextLine();
            System.out.print("Entrez un nombre pour y : ");
            String yStr = sc.nextLine();

            if (xStr.matches("[0-9]+") && yStr.matches("[0-9]+")) {
                int x = Integer.parseInt(xStr);
                int y = Integer.parseInt(yStr);
                try {
                    play(otherPlayerSea, x, y);
                } catch (Exception e) {
                    erreur = e.getMessage();
                }
                if (erreur.equals("")) {
                    this.played = true;
                }
            } else {
                erreur = "Les valeurs entrées pour x et y doivent être des nombres entiers.";
            }
            System.out.println(erreur);
        } while (!erreur.equals(""));
    }

    /**
     * Joue un coup aux coordonnées x et y sur la grille de l'autre joueur.
     * Si le coup est valide, la cellule correspondante est marquée comme détruite.
     * Sinon, une exception est levée.
     * 
     * @param otherPlayerSea La grille de l'autre joueur.
     * @param x              La coordonnée x du coup.
     * @param y              La coordonnée y du coup.
     * @throws Exception si le coup n'est pas valide.
     */
    public void play(Sea otherPlayerSea, int x, int y) throws Exception {

        if (controlePosition(otherPlayerSea, x, y)) {
            SeaCell[][] grille = otherPlayerSea.getSea();
            grille[y][x].setIsDestroyed(true);
        } else {
            throw new Exception("le coup x = " + x + " , y = " + y + " n'est pas valide");
        }
    }

    /**
     * 
     * Cette méthode vérifie si la position du tir est valide et si elle ne
     * correspond pas à une case déjà détruite.
     * 
     * @param otherPlayerSea La grille de l'autre joueur.
     * @param x              La coordonnée en x du tir.
     * @param y              La coordonnée en y du tir.
     * @return true si la position est valide et la case n'est pas déjà détruite,
     *         false sinon.
     */
    public boolean controlePosition(Sea otherPlayerSea, int x, int y) {

        if (x < 0 || x >= Sea.SIZE_X || y < 0 || y >= Sea.SIZE_Y) {
            return false;
        }
        SeaCell[][] grille = otherPlayerSea.getSea();
        if (grille[y][x].isDestroyed()) {
            return false;
        }

        return true;
    }

    /**
     * 
     * Cette méthode renvoie si le joueur a joué son tour ou non.
     * 
     * @return true si le joueur a joué, false sinon.
     */
    @Override
    public boolean getPlayed() {
        return this.played;
    }

    /**
     * 
     * Cette méthode permet de définir si le joueur a joué son tour ou non.
     * 
     * @param b true si le joueur a joué, false sinon.
     */
    @Override
    public void setPlayed(boolean b) {
        this.played = b;
    }

    /**
     * 
     * Cette méthode affiche la grille de jeu et indique si le joueur a gagné ou
     * perdu la partie (une fois qu'elle est finit biensur !).
     * 
     * @param b La bataille en cours.
     */
    @Override
    public void displayForPlayer(Battle b) {
        b.displayGrids();
        if (b.isOver()) {
            System.out.println("Vous avez " + (b.getWinner() == b.getPlayer1() ? "Gagné" : "perdu"));
        }
    }
}

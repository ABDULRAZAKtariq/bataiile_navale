package players;

import sea.*;
import java.util.*;

import battle.Battle;

/**
 * 
 * Classe représentant un joueur de type "bot" qui joue de manière aléatoire.
 */
public class Bot implements Player {
    /* Une instance de la class Random pour générer les coup aléatoire */
    private Random random;
    /* Un boolean qui dit si le joueur a joué ou pas encore */
    private boolean played;

    /**
     * Constructeur de la classe Bot.
     */
    public Bot() {
        /* Une instance de la class Random pour générer les coup aléatoire */
        this.random = new Random(System.currentTimeMillis());
        /* Un boolean qui dit si le joueur a joué ou pas encore */
        this.played = false;
    }

    /**
     * 
     * Méthode qui permet au bot de jouer de manière aléatoire en choisissant une
     * case non détruite de la grille de l'autre joueur.
     * 
     * @param otherPlayerSea la grille de l'autre joueur
     */
    @Override
    public void play(Sea otherPlayerSea) {
        ArrayList<SeaCell> notDestroyed = getNotDistroyedCell(otherPlayerSea);
        int randomIndex = random.nextInt(notDestroyed.size());
        SeaCell randomValue = notDestroyed.get(randomIndex);
        randomValue.setIsDestroyed(true);
    }

    /**
     * 
     * Méthode qui retourne une liste des cases non détruites de la grille.
     * 
     * @param s la grille de la mer
     * 
     * @return une liste des cases non détruites
     */
    public ArrayList<SeaCell> getNotDistroyedCell(Sea s) {
        SeaCell[][] grille = s.getSea();
        ArrayList<SeaCell> notDestroyed = new ArrayList<>();

        for (int i = 0; i < Sea.SIZE_Y; i++) {
            for (int j = 0; j < Sea.SIZE_X; j++) {
                if (!(grille[i][j].isDestroyed())) {
                    notDestroyed.add(grille[i][j]);
                }
            }
        }
        return notDestroyed;
    }

    /**
     * 
     * Méthode qui retourne si le bot a joué pendant le tour en cours.
     * 
     * @return vrai si le bot a joué pendant le tour en cours, faux sinon
     */
    @Override
    public boolean getPlayed() {
        return this.played;
    }

    /**
     * 
     * Méthode qui permet de définir si le bot a joué pendant le tour en cours.
     * 
     * @param b vrai si le bot a joué pendant le tour en cours, faux sinon
     */
    @Override
    public void setPlayed(boolean b) {
        this.played = b;
    }

    /**
     * 
     * Méthode qui affiche la grille pour le joueur (vu que c'est un bot on ne fais
     * rien).
     * 
     * @param b la bataille en cours
     */
    @Override
    public void displayForPlayer(Battle b) {
    }
}

package players;

import battle.Battle;
import sea.*;

/**
 * Interface représentant un joueur de bataille navale.
 */
public interface Player {
    /**
     * Joue un tour en attaquant la grille de la mer de l'adversaire.
     *
     * @param otherPlayerSea La grille de la mer de l'adversaire.
     */
    public void play(Sea otherPlayerSea);

    /**
     * Indique si le joueur a joué son tour.
     *
     * @return True si le joueur a joué, false sinon.
     */
    public boolean getPlayed();

    /**
     * Définit si le joueur a joué son tour.
     *
     * @param b True si le joueur a joué, false sinon.
     */
    public void setPlayed(boolean b);

    /**
     * Affiche des informations pour le joueur.
     *
     * @param b La bataille en cours.
     */
    public void displayForPlayer(Battle b);
}

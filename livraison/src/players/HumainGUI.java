package players;

import battle.Battle;
import sea.Sea;

/**
 * 
 * Cette classe représente un joueur humain jouant avec une interface graphique.
 */
public class HumainGUI implements Player {
    /* Boolean qui dis si le joueur a joué son tour */
    private boolean played;

    /**
     * Constructeur de la classe HumainGUI.
     */
    public HumainGUI() {
        /* Boolean qui dis si le joueur a joué son tour */
        this.played = false;
    }

    /**
     * Cette méthode permet au joueur humain de jouer une fois que l'utilisateur a
     * cliqué sur une case
     * dans l'interface graphique. Cette méthode utilise une boucle pour attendre
     * que l'utilisateur clique sur une case
     * avant de continuer l'exécution.
     * 
     * @param otherPlayerSea La grille de l'adversaire.
     */
    @Override
    public void play(Sea otherPlayerSea) {
        while (!getPlayed()) {
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }
        setPlayed(false);
    }

    /**
     * Cette méthode retourne l'état de la variable played, qui indique si le joueur
     * a joué ou non.
     * 
     * @return L'état de la variable played.
     */
    @Override
    public boolean getPlayed() {
        return this.played;
    }

    /**
     * Cette méthode permet de modifier l'état de la variable played, qui indique si
     * le joueur a joué ou non.
     * 
     * @param b La nouvelle valeur de la variable played.
     */
    @Override
    public void setPlayed(boolean b) {
        this.played = b;
    }

    /**
     * Cette méthode ne fait rien pour le joueur humain jouant avec une interface
     * graphique car l'affichage se fait directement dans l'interface graphique
     * grâce à la méthode fireChangement() qui
     * met à joue l'affichage.
     * 
     * @param b La bataille en cours.
     */
    @Override
    public void displayForPlayer(Battle b) {
    }

}

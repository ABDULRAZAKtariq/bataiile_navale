package battle;

import players.HumainClavier;

/**
 * 
 * La classe MainClavier est la classe principale pour le jeu de bataille navale
 * avec l'entrée utilisateur à partir du clavier.
 */
public class MainClavier {
    /**
     * La méthode main est la méthode principale qui crée une instance de la classe
     * Battle avec un joueur HumainClavier
     * et lance la bataille.
     *
     * @param args les arguments de ligne de commande (non utilisés ici)
     */
    public static void main(String[] args) {

        Battle b = new Battle(new HumainClavier());
        b.startBattle();
    }

}
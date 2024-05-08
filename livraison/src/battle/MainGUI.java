package battle;

import graphique.*;
import players.HumainGUI;

/**
 * 
 * La classe MainGUI contient la méthode main qui permet de lancer une partie de
 * bataille navale en utilisant
 * l'interface graphique. Elle crée une instance de Battle avec un joueur humain
 * utilisant l'interface graphique,
 * puis crée une instance de GameFrame pour afficher le jeu. Enfin, elle lance
 * une nouvelle thread pour exécuter
 * la méthode startBattle() de l'instance de Battle et afficher le jeu.
 */
public class MainGUI {
    /**
     * La méthode main est la méthode principale qui crée une instance de la classe
     * Battle avec un joueur HumainGUI
     * et lance la bataille et l'interface graphique.
     *
     * @param args les arguments de ligne de commande (non utilisés ici)
     */
    public static void main(String[] args) {

        Battle b = new Battle(new HumainGUI());
        GameFrame f = new GameFrame(b);
        f.setVisible(true);
        new Thread(() -> {
            b.startBattle();
        }).start();
    }

}
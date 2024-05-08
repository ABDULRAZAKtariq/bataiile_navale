package graphique;

import battle.*;
import graphique.mvc.EcouteurModele;
import sea.Sea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * La classe Controler représente le contrôleur pour le joueur humain.
 * 
 * Il implémente l'interface EcouteurModele pour être notifié lorsque le modèle
 * change.
 */
public class Controler implements EcouteurModele {
    /* Le JPanel ou il y a la grille de boutons */
    private HumainPlayerPanel vue;
    /* Le modéle (le jeu) */
    private Battle model;
    /* Une grille de bouton intéractif */
    private GameButton[][] grille;

    /**
     * 
     * Constructeur de la classe Controler.
     * 
     * @param vue   La vue associée au contrôleur.
     * @param model Le modèle associé au contrôleur.
     */
    public Controler(HumainPlayerPanel vue, Battle model) {
        /* Le modéle (le jeu) */
        this.model = model;
        /* Le JPanel ou il y a la grille de boutons */
        this.vue = vue;
        /* Une grille de bouton intéractif */
        this.grille = (this.vue).getGrille();

        model.ajoutEcouteur(this);
    }

    /**
     * 
     * Méthode appelée lorsqu'un événement est déclenché dans le modèle.
     * 
     * @param source La source de l'événement.
     */
    @Override
    public void modeleMisAJour(Object source) {
        if (!model.getPlayer1().getPlayed()) {
            for (int i = 0; i < Sea.SIZE_Y; i++) {
                for (int j = 0; j < Sea.SIZE_X; j++) {
                    grille[i][j].addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            GameButton source = (GameButton) e.getSource();
                            if (!source.getCell().isDestroyed() && !model.getPlayer1().getPlayed()) {
                                source.getCell().setIsDestroyed(true);
                                model.getPlayer1().setPlayed(true);
                            }
                        }
                    });
                }
            }
        }
    }
}

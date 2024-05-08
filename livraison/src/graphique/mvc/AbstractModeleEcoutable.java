package graphique.mvc;

import java.util.*;

/**
 * 
 * Classe abstraite qui implémente l'interface ModeleEcoutable pour
 * faciliter la gestion d'écouteurs de modèle.
 * 
 * Cette classe fournit une liste d'écouteurs et des méthodes pour ajouter ou
 * supprimer des écouteurs.
 */
public abstract class AbstractModeleEcoutable implements ModeleEcoutable {
    /**
     * 
     * La liste des écouteurs de ce modèle.
     */
    protected List<EcouteurModele> listeEcouteur;

    /**
     * Constructeur de la classe AbstractModeleEcoutable
     * Crée une nouvelle liste d'écouteurs vide.
     */
    public AbstractModeleEcoutable() {
        this.listeEcouteur = new ArrayList<EcouteurModele>();
    }

    /**
     * 
     * Notifie tous les écouteurs de ce modèle qu'il y a eu un changement.
     * Pour chaque écouteur, appelle sa méthode modeleMisAJour avec ce
     * modèle comme argument.
     */
    protected void fireChangement() {
        for (EcouteurModele ecouteur : this.listeEcouteur) {
            ecouteur.modeleMisAJour(this);
        }
    }

    /**
     * 
     * Ajoute un écouteur à la liste des écouteurs de ce modèle.
     * 
     * @param e l'écouteur à ajouter
     */
    @Override
    public void ajoutEcouteur(EcouteurModele e) {
        (this.listeEcouteur).add(e);
    }

    /**
     * 
     * Retire un écouteur de la liste des écouteurs de ce modèle.
     * 
     * @param e l'écouteur à retirer
     */
    @Override
    public void retraitEcouteur(EcouteurModele e) {
        (this.listeEcouteur).remove(e);
    }
}
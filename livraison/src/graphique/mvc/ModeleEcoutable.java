package graphique.mvc;

/**
 * 
 * Interface représentant un modèle écoutable, c'est-à-dire un modèle qui peut
 * avoir des écouteurs qui sont notifiés
 * 
 * lorsqu'il y a des changements dans le modèle.
 */
public interface ModeleEcoutable {
    /**
     * 
     * Ajoute un écouteur au modèle écoutable.
     * 
     * @param e l'écouteur à ajouter
     */
    public void ajoutEcouteur(EcouteurModele e);

    /**
     * 
     * Retire un écouteur du modèle écoutable.
     * 
     * @param e l'écouteur à retirer
     */
    public void retraitEcouteur(EcouteurModele e);
}
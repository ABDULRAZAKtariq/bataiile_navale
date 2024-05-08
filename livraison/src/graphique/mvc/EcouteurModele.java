package graphique.mvc;

/**
 * 
 * Interface représentant un écouteur de modèle, capable de recevoir une
 * notification de mise à jour de celui-ci.
 */
public interface EcouteurModele {
    /**
     * 
     * Méthode appelée lorsqu'un modèle a été mis à jour.
     * 
     * @param source le modèle qui a été mis à jour
     */
    void modeleMisAJour(Object source);
}
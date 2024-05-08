package boat.exceptions;

import sea.*;

/**
 * 
 * Cette exception est lancée quand la taille du bateau n'est pas dans
 * l'intervalle décidé à l'avance
 */
public class BoatSizeExceededException extends Exception {
    /**
     * 
     * Constructeur de l'exception avec la longueur du bateau qui a causé
     * l'exception.
     * 
     * @param length la longueur du bateau qui a causé l'exception.
     */
    public BoatSizeExceededException(int length) {
        super("La taille " + length + " n'est pas valide, La taille du bateau doit être dans l'intervalle => ["
                + (Sea.SIZE_X) / 5 + ", " + Sea.SIZE_X / 2 + "]");
    }
}
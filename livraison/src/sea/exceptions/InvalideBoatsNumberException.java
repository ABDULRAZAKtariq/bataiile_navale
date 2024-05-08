package sea.exceptions;

import sea.*;

/**
 * 
 * Exception levée lorsque le nombre de bateaux placés dans la mer est invalide.
 */
public class InvalideBoatsNumberException extends Exception {
    /**
     * Constructeur de l'exception.
     * 
     * @param n le nombre de bateaux qui a été placé
     */
    public InvalideBoatsNumberException(int n) {
        super("Vous avez mis " + n + " bateaux au lieu de " + Sea.NOMBRE_DE_BATEAU);
    }
}

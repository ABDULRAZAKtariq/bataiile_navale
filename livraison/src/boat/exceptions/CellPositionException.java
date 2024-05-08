package boat.exceptions;

import boat.*;

/**
 * 
 * Cette exception est levée lorsque les positions des cellules d'un bateau ne
 * sont pas correctes.
 */
public class CellPositionException extends Exception {
    public CellPositionException(Boat b) {
        super("Les cellules du bateau" + b + "\n ne sont pas correct !");
    }
}
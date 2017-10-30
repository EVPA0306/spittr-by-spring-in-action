package spittr.exceptions;

/**
 * Created by evgenypavlenko on 10/17/17.
 */
public class DuplicateSpittleException extends RuntimeException {
    public DuplicateSpittleException(String s) {
        super(s);
    }
}

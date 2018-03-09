package dao;

/**
 * Created by Anna on 03.03.2018.
 */
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
    }
}

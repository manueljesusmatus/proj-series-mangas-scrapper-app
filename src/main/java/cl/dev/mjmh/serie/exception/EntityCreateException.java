package cl.dev.mjmh.serie.exception;

public class EntityCreateException extends RuntimeException {

    private static final String MESSAGE_FORMAT = "No se ha podido crear la entidad %s con id %d";

    public EntityCreateException() {
        super();
    }

    public EntityCreateException(String message) {
        super(message);
    }

    public <T> EntityCreateException(Class<T> entityClass, Long id, Throwable cause) {
        super(String.format(MESSAGE_FORMAT, entityClass.getName(), id), cause);
    }

    public EntityCreateException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityCreateException(Throwable cause) {
        super(cause);
    }

}

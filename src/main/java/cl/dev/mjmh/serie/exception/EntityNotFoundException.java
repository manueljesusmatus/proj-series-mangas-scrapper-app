package cl.dev.mjmh.serie.exception;

public class EntityNotFoundException extends RuntimeException{

    private static final String MESSAGE_FORMAT = "La entidad %s buscada no existe en los registros";

    public EntityNotFoundException() {
        super();
    }

    public EntityNotFoundException(String message) {
        super(message);
    }

    public <T> EntityNotFoundException(Class<T> entityClass) {
        super(String.format(MESSAGE_FORMAT, entityClass.getName()));
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityNotFoundException(Throwable cause) {
        super(cause);
    }

}

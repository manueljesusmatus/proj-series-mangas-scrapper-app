package cl.dev.mjmh.serie.exception;

public class LoadResourceException extends RuntimeException{

    public LoadResourceException() {
        super();
    }

    public LoadResourceException(String message) {
        super(message);
    }

    public LoadResourceException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoadResourceException(Throwable cause) {
        super(cause);
    }
}

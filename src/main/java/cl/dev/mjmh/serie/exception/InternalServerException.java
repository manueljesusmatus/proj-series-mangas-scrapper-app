package cl.dev.mjmh.serie.exception;

public class InternalServerException extends RuntimeException{

    public InternalServerException() {
        super();
    }

    public InternalServerException(String message) {
        super(message);
    }

    public InternalServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalServerException(Throwable cause) {
        super(cause);
    }

    public InternalServerException(String message, String ...params) {
        super(String.format(message, (Object) params));
    }


}

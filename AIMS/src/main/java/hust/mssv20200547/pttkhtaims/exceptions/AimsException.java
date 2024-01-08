package hust.mssv20200547.pttkhtaims.exceptions;

/**
 * Represent application exceptions
 */
public abstract class AimsException extends Exception {
    public AimsException() {
    }

    public AimsException(String message) {
        super(message);
    }

    public AimsException(String message, Throwable cause) {
        super(message, cause);
    }

    public AimsException(Throwable cause) {
        super(cause);
    }

    public AimsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

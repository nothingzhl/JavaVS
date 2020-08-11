package zhl.study.exception;

/**
 * 实例化错误
 */
public class InstantiateException extends RuntimeException {
    public InstantiateException() {
        super();
    }

    public InstantiateException(String message) {
        super(message);
    }

    public InstantiateException(String message, Throwable cause) {
        super(message, cause);
    }

    public InstantiateException(Throwable cause) {
        super(cause);
    }

    protected InstantiateException(String message, Throwable cause, boolean enableSuppression,
                                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

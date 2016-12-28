package updater.execption;

/**
 * Created by b010cli on 12/10/2016.
 */
public class UpdaterException extends RuntimeException {

    public UpdaterException() {
        super();
    }

    public UpdaterException(String message) {
        super(message);
    }

    public UpdaterException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdaterException(int message, Throwable cause) {
        super(message+"", cause);
    }

    public UpdaterException(Throwable cause) {
        super(cause);
    }


}

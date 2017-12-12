package pl.itgolo.libs.chromium.Exceptions;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 27.07.2017
 * Time: 09:27
 * Project name: crawler
 *
 * @author Karol Golec <karol.rebigo@gmail.com>
 */
public class ChromiumException extends Exception {

    /**
     * Constructor
     */
    public ChromiumException() {

    }

    /**
     * Constructor
     * @param message message exception
     */
    public ChromiumException(String message) {
        super (message);
    }


    /**
     * Constructor
     * @param cause cause exception
     */
    public ChromiumException(Throwable cause) {
        super (cause);
    }

    /**
     * Constructor
     * @param message message exception
     * @param cause cause exception
     */
    public ChromiumException(String message, Throwable cause) {
        super (message, cause);
    }
}

package pl.itgolo.libs.chromium;

import pl.itgolo.libs.chromium.Exceptions.ChromiumException;

/**
 * The type Main service.
 */
public class Main {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws ChromiumException the chromium exception
     */
    public static void main(String[] args) throws ChromiumException, InterruptedException {
        Browser browser = new Browser();
        browser.actions.navigateTo("https://www.google.pl/search?q=komputer&num=10");
    }


}



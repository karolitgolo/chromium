package pl.itgolo.libs.chromium.Actions;

import org.jsoup.nodes.Element;
import pl.itgolo.libs.chromium.Browser;
import pl.itgolo.libs.chromium.Exceptions.ChromiumException;

/**
 * The type Browser actions.
 */
public class BrowserActions {


    private final NavigateToAction navigateToAction;

    private final GetSourceAction getSourceAction;

    private final GetElementAction getElementAction;

    private final WaitElementAction waitElementAction;

    private Browser browser;

    /**
     * Instantiates a new Browser actions.
     *
     * @param browser the browser
     */
    public BrowserActions(Browser browser) {
        this.browser = browser;
        this.navigateToAction = new NavigateToAction(browser);
        this.getSourceAction = new GetSourceAction(browser);
        this.getElementAction = new GetElementAction(browser);
        this.waitElementAction = new WaitElementAction(browser);

    }

    /**
     * Navigate to.
     *
     * @param url                      the url
     * @param timeout                  the timeout
     * @param attempts                 the attempts
     * @param sleepAttemptMilliseconds the sleep attempt milliseconds
     * @throws ChromiumException the chromium exception
     */
    public void navigateTo(String url, Integer timeout, Integer attempts, Integer sleepAttemptMilliseconds) throws ChromiumException {
        this.navigateToAction.launch(url, timeout, attempts, sleepAttemptMilliseconds);
    }

    /**
     * Navigate to.
     *
     * @param url     the url
     * @param timeout the timeout
     * @throws ChromiumException the chromium exception
     */
    public void navigateTo(String url, Integer timeout) throws ChromiumException {
        this.navigateToAction.launch(url, timeout, 1, 1000);
    }

    /**
     * Navigate to.
     *
     * @param url the url
     * @throws ChromiumException the chromium exception
     */
    public void navigateTo(String url) throws ChromiumException {
        this.navigateToAction.launch(url, 60, 1, 1000);
    }

    /**
     * Gets source.
     *
     * @return the source
     * @throws ChromiumException the chromium exception
     */
    public String getSource() throws ChromiumException {
        return this.getSourceAction.launch(1, 1, 1);
    }

    /**
     * Gets source.
     *
     * @param timeout                  the timeout
     * @param attempts                 the attempts
     * @param sleepAttemptMilliseconds the sleep attempt milliseconds
     * @return the source
     * @throws ChromiumException the chromium exception
     */
    public String getSource(Integer timeout, Integer attempts, Integer sleepAttemptMilliseconds) throws ChromiumException {
       return this.getSourceAction.launch(timeout, attempts, sleepAttemptMilliseconds);
    }

    /**
     * Gets element.
     *
     * @param cssSelector the css selector
     * @return the element
     * @throws ChromiumException the chromium exception
     */
    public Element getElement(String cssSelector) throws ChromiumException {
        return this.getElementAction.launch(cssSelector, 1, 50);
    }

    /**
     * Gets element.
     *
     * @param cssSelector                   the css selector
     * @param timeout                       the timeout
     * @param sleepBetweenCheckMilliseconds the sleep between check milliseconds
     * @return the element
     * @throws ChromiumException the chromium exception
     */
    public Element getElement( String cssSelector, Integer timeout, Integer sleepBetweenCheckMilliseconds) throws ChromiumException {
        return this.getElementAction.launch( cssSelector, timeout, sleepBetweenCheckMilliseconds);
    }

    /**
     * Wait element.
     *
     * @param cssSelector the css selector
     * @param timeout     the timeout
     * @throws ChromiumException the chromium exception
     */
    public void waitElement( String cssSelector, Integer timeout) throws ChromiumException {
        this.waitElementAction.launch( cssSelector, timeout, 75);
    }

    /**
     * Wait element.
     *
     * @param cssSelector                   the css selector
     * @param timeout                       the timeout
     * @param sleepBetweenCheckMilliseconds the sleep between check milliseconds
     * @throws ChromiumException the chromium exception
     */
    public void waitElement( String cssSelector, Integer timeout, Integer sleepBetweenCheckMilliseconds) throws ChromiumException {
        this.waitElementAction.launch( cssSelector, timeout, sleepBetweenCheckMilliseconds);
    }
}

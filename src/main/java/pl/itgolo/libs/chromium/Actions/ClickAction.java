package pl.itgolo.libs.chromium.Actions;

import org.jsoup.nodes.Element;
import pl.itgolo.libs.chromium.Browser;
import pl.itgolo.libs.chromium.Exceptions.ChromiumException;
import pl.itgolo.libs.chromium.Services.LogService;

/**
 * The type Click action.
 */
public class ClickAction {

    private Browser browser;


    /**
     * Instantiates a new Click action.
     *
     * @param browser the browser
     */
    public ClickAction(Browser browser) {
        this.browser = browser;
    }

    /**
     * Launch.
     *
     * @param cssSelector the css selector
     * @throws ChromiumException the chromium exception
     */
    public void launch(String cssSelector) throws ChromiumException {
        LogService.debug("Action click in css selector: " + cssSelector);
        Element element = browser.actions.getElement(cssSelector, 0, 50);
        if (element == null){
            throw new ChromiumException("Can not find element with css selector: " + cssSelector);
        }
        executeScript(cssSelector);
    }

    private void executeScript(String cssSelector) {
        String script = String.format("document.querySelector('%1$s').click();", cssSelector.replaceAll("'", "\\\\'"), "value");
        browser.getJFrame().cefBrowser.executeJavaScript(script, "", 1);
    }
}

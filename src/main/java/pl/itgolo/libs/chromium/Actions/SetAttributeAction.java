package pl.itgolo.libs.chromium.Actions;

import org.jsoup.nodes.Element;
import pl.itgolo.libs.chromium.Browser;
import pl.itgolo.libs.chromium.Exceptions.ChromiumException;
import pl.itgolo.libs.chromium.Services.LogService;

/**
 * The type Set attribute action.
 */
public class SetAttributeAction {

    private Browser browser;


    /**
     * Instantiates a new Set attribute action.
     *
     * @param browser the browser
     */
    public SetAttributeAction(Browser browser) {
        this.browser = browser;
    }

    /**
     * Launch.
     *
     * @param cssSelector   the css selector
     * @param attributeName the attribute name
     * @param value         the value
     * @param timeout       the timeout
     * @throws ChromiumException the chromium exception
     */
    public void launch(String cssSelector, String attributeName, String value, Integer timeout) throws ChromiumException {
        LogService.debug("Action set attribute in css selector: " + cssSelector);
        executeScript(cssSelector, attributeName, value);
        for (Integer i = 0; i <= timeout; i++) {
            Element element = browser.actions.getElement(cssSelector, 1, 50);
            NavigateToAction.sleep(100);
            if (element != null){
                String attribute = element.attr(attributeName);
                if (attribute != null){
                    if (attribute.equals(value)){
                        return;
                    }
                }
            }
        }
        throw new ChromiumException(String.format("Not change attribute %1$s for css selector: %2$s", attributeName, cssSelector));
    }

    private void executeScript(String cssSelector, String attributeName, String value) {
        String script = String.format("document.querySelector('%1$s').setAttribute('%2$s', '%3$s');", cssSelector.replaceAll("'", "\\\\'"), attributeName, value);
        browser.getJFrame().cefBrowser.executeJavaScript(script, "", 1);
    }
}

package pl.itgolo.libs.chromium.Actions;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;
import pl.itgolo.libs.chromium.Browser;
import pl.itgolo.libs.chromium.Exceptions.ChromiumException;

/**
 * The type Select option action.
 */
public class SelectOptionAction {

    private Browser browser;


    /**
     * Instantiates a new Select option action.
     *
     * @param browser the browser
     */
    public SelectOptionAction(Browser browser) {
        this.browser = browser;
    }

    /**
     * Launch.
     *
     * @param cssSelector the css selector
     * @param value       the value
     * @throws ChromiumException the chromium exception
     */
    public void launch(String cssSelector, String value) throws ChromiumException {
        cssSelector = cssSelector + " option[value='" + value + "']";
        executeScript(cssSelector);
        Element element = browser.actions.getElement(cssSelector, 0, 50);
        Boolean validateSelected = false;
        for (Attribute attribute : element.attributes()) {
            if (attribute.getKey().equals("selected")) {
                if (attribute.getValue().equals("selected")) {
                    validateSelected = true;
                    break;
                }
            }
        }
        if (validateSelected) {
            return;
        }
        throw new ChromiumException(String.format("Not selected radio for css selector: %1$s", cssSelector));
    }

    private void executeScript(String cssSelector) {
        String script = String.format("document.querySelector('%1$s').setAttribute('selected', 'selected');", cssSelector.replaceAll("'", "\\\\'"));
        browser.getJFrame().cefBrowser.executeJavaScript(script, "", 1);
    }
}

package pl.itgolo.libs.chromium.Actions;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;
import pl.itgolo.libs.chromium.Browser;
import pl.itgolo.libs.chromium.Exceptions.ChromiumException;

/**
 * The type Fill input action.
 */
public class SelectRadioAction {

    private Browser browser;


    /**
     * Instantiates a new Fill input action.
     *
     * @param browser the browser
     */
    public SelectRadioAction(Browser browser) {
        this.browser = browser;
    }

    /**
     * Launch.
     *
     * @param cssSelector the css selector
     * @param value       the value
     * @param timeout     the timeout
     * @throws ChromiumException the chromium exception
     */
    public void launch(String cssSelector, String value) throws ChromiumException {
        cssSelector = cssSelector + "[value='"+value+"']";
        executeScript(cssSelector);
        Element element = browser.actions.getElement(cssSelector, 0, 50);
        Boolean checked = false;
        for (Attribute attribute : element.attributes()) {
            if (attribute.getKey().equals("checked")){
                checked = true;
                break;
            }
        }
        if (checked) {
            return;
        }
        throw new ChromiumException(String.format("Not selected radio for css selector: %1$s", cssSelector));
    }

    private void executeScript(String cssSelector) {
        String script = String.format("document.querySelector('%1$s').setAttribute('checked', 'true');", cssSelector.replaceAll("'", "\\\\'"));
        browser.getJFrame().cefBrowser.executeJavaScript(script, "", 1);
    }
}

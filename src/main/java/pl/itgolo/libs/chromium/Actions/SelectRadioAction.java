package pl.itgolo.libs.chromium.Actions;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;
import pl.itgolo.libs.chromium.Browser;
import pl.itgolo.libs.chromium.Exceptions.ChromiumException;
import pl.itgolo.libs.chromium.Services.LogService;

/**
 * The type Select radio action.
 */
public class SelectRadioAction {

    private Browser browser;


    /**
     * Instantiates a new Select radio action.
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
     * @throws ChromiumException the chromium exception
     */
    public void launch(String cssSelector, String value) throws ChromiumException {
        LogService.debug("Action select radio in css selector: " + cssSelector);
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

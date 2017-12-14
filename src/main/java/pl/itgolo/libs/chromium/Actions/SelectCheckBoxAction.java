package pl.itgolo.libs.chromium.Actions;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;
import pl.itgolo.libs.chromium.Browser;
import pl.itgolo.libs.chromium.Exceptions.ChromiumException;
import pl.itgolo.libs.chromium.Services.LogService;

/**
 * The type Select check box action.
 */
public class SelectCheckBoxAction {

    private Browser browser;


    /**
     * Instantiates a new Select check box action.
     *
     * @param browser the browser
     */
    public SelectCheckBoxAction(Browser browser) {
        this.browser = browser;
    }

    /**
     * Launch.
     *
     * @param cssSelector the css selector
     * @param checked     the checked
     * @throws ChromiumException the chromium exception
     */
    public void launch(String cssSelector, Boolean checked) throws ChromiumException {
        LogService.debug("Action select checkbox in css selector: " + cssSelector);
        executeScript(cssSelector, checked);
        Element element = browser.actions.getElement(cssSelector, 0, 50);
        Boolean validateChecked = false;
        for (Attribute attribute : element.attributes()) {
            if (attribute.getKey().equals("checked")){
                if (attribute.getValue().equals(checked.toString())){
                    validateChecked = true;
                    break;
                }
            }
        }
        if (validateChecked) {
            return;
        }
        throw new ChromiumException(String.format("Not selected radio for css selector: %1$s", cssSelector));
    }

    private void executeScript(String cssSelector, Boolean checked) {
        String script = String.format("document.querySelector('%1$s').setAttribute('checked', '%2$s');", cssSelector.replaceAll("'", "\\\\'"), checked.toString());
        browser.getJFrame().cefBrowser.executeJavaScript(script, "", 1);
    }
}

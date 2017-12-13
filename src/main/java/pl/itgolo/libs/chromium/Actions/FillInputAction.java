package pl.itgolo.libs.chromium.Actions;

import org.jsoup.nodes.Element;
import pl.itgolo.libs.chromium.Browser;
import pl.itgolo.libs.chromium.Exceptions.ChromiumException;

/**
 * The type Fill input action.
 */
public class FillInputAction {

    private Browser browser;


    /**
     * Instantiates a new Fill input action.
     *
     * @param browser the browser
     */
    public FillInputAction(Browser browser) {
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
    public void launch(String cssSelector, String value, Integer timeout) throws ChromiumException {
        executeScript(cssSelector, value);
        for (Integer i = 0; i <= timeout * 5; i++) {
            Element element = browser.actions.getElement(cssSelector, 0, 50);
            if (element != null){
                String attribute = element.val();
                if (attribute != null){
                    if (attribute.equals(value)){
                        return;
                    }
                }
            }
            NavigateToAction.sleep(200);
        }
        throw new ChromiumException(String.format("Not fill input for css selector: %1$s", cssSelector));
    }

    private void executeScript(String cssSelector, String value) {
        String script = String.format("document.querySelector('%1$s').setAttribute('%2$s', '%3$s');", cssSelector.replaceAll("'", "\\\\'"), "value", value.replaceAll("'", "\\\\'"));
        browser.getJFrame().cefBrowser.executeJavaScript(script, "", 1);
    }
}

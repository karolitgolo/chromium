package pl.itgolo.libs.chromium.Actions;

import org.jsoup.nodes.Element;
import pl.itgolo.libs.chromium.Browser;
import pl.itgolo.libs.chromium.Exceptions.ChromiumException;
import pl.itgolo.libs.chromium.Services.LogService;

/**
 * The type Fill text area action.
 */
public class FillTextAreaAction {

    private Browser browser;


    /**
     * Instantiates a new Fill text area action.
     *
     * @param browser the browser
     */
    public FillTextAreaAction(Browser browser) {
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
        LogService.debug("Action fill textarea in css selector: " + cssSelector);
        executeScript(cssSelector, value);
        for (Integer i = 0; i <= timeout * 5; i++) {
            Element element = browser.actions.getElement(cssSelector, 0, 50);
            if (element != null){
                String attribute = element.html();
                if (attribute != null){
                    if (attribute.equals(value)){
                        return;
                    }
                }
            }
            NavigateToAction.sleep(200);
        }
        throw new ChromiumException(String.format("Not fill text area for css selector: %1$s", cssSelector));
    }

    private void executeScript(String cssSelector, String value) {
        String script = String.format("document.querySelector('%1$s').innerHTML='%2$s';", cssSelector.replaceAll("'", "\\\\'"),  value.replaceAll("'", "\\\\'"));
        browser.getJFrame().cefBrowser.executeJavaScript(script, "", 1);
    }
}

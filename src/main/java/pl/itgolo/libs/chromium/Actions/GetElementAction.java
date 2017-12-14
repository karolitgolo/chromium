package pl.itgolo.libs.chromium.Actions;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pl.itgolo.libs.chromium.Browser;
import pl.itgolo.libs.chromium.Exceptions.ChromiumException;
import pl.itgolo.libs.chromium.Services.LogService;

/**
 * The type Get element action.
 */
public class GetElementAction {

    private Browser browser;


    /**
     * Instantiates a new Get element action.
     *
     * @param browser the browser
     */
    public GetElementAction(Browser browser) {
        this.browser = browser;
    }

    /**
     * Launch element.
     *
     * @param cssSelector                   the css selector
     * @param timeout                       the timeout
     * @param sleepBetweenCheckMilliseconds the sleep between check milliseconds
     * @return the element
     * @throws ChromiumException the chromium exception
     */
    public Element launch(String cssSelector, Integer timeout, Integer sleepBetweenCheckMilliseconds) throws ChromiumException {
        LogService.debug("Action get element in css selector: " + cssSelector);
        IntegerProperty countTimeoutProperty = new SimpleIntegerProperty(0);
        if (timeout.equals(0)){
            return getElement(cssSelector, 0, countTimeoutProperty);
        } else {
            while (countTimeoutProperty.get() < timeout * 1000) {
                Element element = getElement(cssSelector, sleepBetweenCheckMilliseconds, countTimeoutProperty);
                if (element != null){
                    return element;
                }
            }
        }
        return null;
    }

    private Element getElement(String cssSelector, Integer sleepBetweenCheckMilliseconds, IntegerProperty countTimeoutProperty) throws ChromiumException {
        String dom = browser.actions.getSource(2, 2, 0);
        Document domDocument = Jsoup.parse(dom);
        Elements elements = domDocument.select(cssSelector);
        if (elements.size()>0){
            return elements.get(0);
        }
        countTimeoutProperty.set(countTimeoutProperty.get() + sleepBetweenCheckMilliseconds);
        NavigateToAction.sleep(sleepBetweenCheckMilliseconds);
        return null;
    }
}

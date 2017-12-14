package pl.itgolo.libs.chromium.Actions;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import pl.itgolo.libs.chromium.Browser;
import pl.itgolo.libs.chromium.Exceptions.ChromiumException;
import pl.itgolo.libs.chromium.Services.LogService;

/**
 * The type Get elements action.
 */
public class GetElementsAction {

    private Browser browser;


    /**
     * Instantiates a new Get elements action.
     *
     * @param browser the browser
     */
    public GetElementsAction(Browser browser) {
        this.browser = browser;
    }

    /**
     * Launch elements.
     *
     * @param cssSelector                   the css selector
     * @param timeout                       the timeout
     * @param sleepBetweenCheckMilliseconds the sleep between check milliseconds
     * @return the elements
     * @throws ChromiumException the chromium exception
     */
    public Elements launch(String cssSelector, Integer timeout, Integer sleepBetweenCheckMilliseconds) throws ChromiumException {
        LogService.debug("Action get elements in css selector: " + cssSelector);
        Integer countTimeout = 0;
        if (timeout.equals(0)){
            String dom = browser.actions.getSource();
            Document domDocument = Jsoup.parse(dom);
            return domDocument.select(cssSelector);
        } else {
            while (countTimeout < timeout * 1000) {
                String dom = browser.actions.getSource(2, 2, 0);
                Document domDocument = Jsoup.parse(dom);
                Elements elements = domDocument.select(cssSelector);
                if (elements.size()>0){
                    return elements;
                }
                countTimeout += sleepBetweenCheckMilliseconds;
                NavigateToAction.sleep(sleepBetweenCheckMilliseconds);
            }
            return new Elements();
        }
    }
}

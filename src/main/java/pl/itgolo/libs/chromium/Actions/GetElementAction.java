package pl.itgolo.libs.chromium.Actions;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.cef.callback.CefStringVisitor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pl.itgolo.libs.chromium.Browser;
import pl.itgolo.libs.chromium.Exceptions.ChromiumException;

/**
 * The type Get attribute action.
 */
public class GetElementAction {

    private Browser browser;


    /**
     * Instantiates a new Get attribute action.
     *
     * @param browser the browser
     */
    public GetElementAction(Browser browser) {
        this.browser = browser;
    }

    /**
     * Launch string.
     *
     * @param cssSelector                   the css selector
     * @param attributeName                 the attribute name
     * @param timeout                       the timeout
     * @param sleepBetweenCheckMilliseconds the sleep between check milliseconds
     * @return the string
     * @throws ChromiumException the chromium exception
     */
    public Element launch(String cssSelector, Integer timeout, Integer sleepBetweenCheckMilliseconds) throws ChromiumException {
        System.out.println("Get element");
        StringProperty source = new SimpleStringProperty();
        Integer countTimeout = 0;
        while(countTimeout < timeout * 1000){
            readSource(source);
            Element element = getElement(cssSelector, source);
            if (element!= null){
                return element;
            }
            countTimeout += sleepBetweenCheckMilliseconds;
            NavigateToAction.sleep(sleepBetweenCheckMilliseconds);
        }
        return null;
    }

    private Element getElement(String cssSelector, StringProperty source) {
        if (source.get() != null){
            Document document = Jsoup.parse(source.get());
            Elements elements = document.select(cssSelector);
            if (elements.size() > 0){
                return elements.get(0);
            }
        }
        return null;
    }

    private void readSource(StringProperty source) throws ChromiumException {
        CefStringVisitor cefStringVisitor = new CefStringVisitor() {
            @Override
            public void visit(String string) {
                if (!browser.getJFrame().cefBrowser.isLoading()){
                    source.setValue(string);
                }
            }
        };
        browser.getJFrame().cefBrowser.getSource(cefStringVisitor);
    }
}

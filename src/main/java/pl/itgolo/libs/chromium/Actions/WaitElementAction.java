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
 * The type Wait element action.
 */
public class WaitElementAction {

    private Browser browser;


    /**
     * Instantiates a new Wait element action.
     *
     * @param browser the browser
     */
    public WaitElementAction(Browser browser) {
        this.browser = browser;
    }

    /**
     * Launch.
     *
     * @param cssSelector                   the css selector
     * @param timeout                       the timeout
     * @param sleepBetweenCheckMilliseconds the sleep between check milliseconds
     * @throws ChromiumException the chromium exception
     */
    public void launch(String cssSelector, Integer timeout, Integer sleepBetweenCheckMilliseconds) throws ChromiumException {
        System.out.println("Wait element");
        StringProperty source = new SimpleStringProperty();
        Integer countTimeout = 0;
        while(countTimeout < timeout * 1000){
            readSource(source);
            Element element = getElement(cssSelector, source);
            if (element!= null){
                return;
            }
            countTimeout += sleepBetweenCheckMilliseconds;
            NavigateToAction.sleep(sleepBetweenCheckMilliseconds);
        }
        throw new ChromiumException("Not found element for css selector: " + cssSelector);
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

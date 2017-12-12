package pl.itgolo.libs.chromium.Actions;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.cef.callback.CefStringVisitor;
import pl.itgolo.libs.chromium.Browser;
import pl.itgolo.libs.chromium.Exceptions.ChromiumException;

/**
 * The type Navigate to action.
 */
public class NavigateToAction {

    private Browser browser;


    /**
     * Instantiates a new Navigate to action.
     *
     * @param browser the browser
     */
    public NavigateToAction(Browser browser) {
        this.browser = browser;
    }

    /**
     * Launch.
     *
     * @param url                      the url
     * @param timeout                  the timeout
     * @param attempts                 the attempts
     * @param sleepAttemptMilliseconds the sleep attempt milliseconds
     * @throws ChromiumException the chromium exception
     */
    public void launch(String url, Integer timeout, Integer attempts, Integer sleepAttemptMilliseconds) throws ChromiumException {
        System.out.println("Load url: " + url);
        StringProperty source = new SimpleStringProperty();
        for (Integer i = 0 ; i <attempts ; i++){
            try {
                browser.getJFrame().cefBrowser.stopLoad();
                browser.getJFrame().cefBrowser.loadURL(url);
                waitLoad(url, source, timeout);
                break;
            } catch (Exception e) {
                if (i.equals(attempts - 1)){
                    throw new ChromiumException(e);
                }
                System.out.println("Attempt navigate to url: " + i + 1);
                NavigateToAction.sleep(sleepAttemptMilliseconds);
            }
        }
        System.out.println("Loaded");
    }

    private void waitLoad(String url, StringProperty source, Integer timeout) throws ChromiumException {
        CefStringVisitor cefStringVisitor = new CefStringVisitor() {
            @Override
            public void visit(String string) {
                if (!browser.getJFrame().cefBrowser.isLoading()){
                    source.setValue(string);
                }
            }
        };
        for (int i =0 ; i < timeout * 10 ; i++){
            browser.getJFrame().cefBrowser.getSource(cefStringVisitor);
            if (source.get() != null){
                break;
            }
           NavigateToAction.sleep(100);
        }
        if (source.get() == null){
            throw new ChromiumException("Not loaded url: " + url);
        }
    }

    /**
     * Sleep.
     *
     * @param milliseconds the milliseconds
     * @throws ChromiumException the chromium exception
     */
    public static void sleep(Integer milliseconds) throws ChromiumException {
        try {
            Thread.sleep(milliseconds);
        } catch (Exception e) {
            throw new ChromiumException(e);
        }
    }
}

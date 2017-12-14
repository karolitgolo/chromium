package pl.itgolo.libs.chromium.Actions;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.cef.callback.CefStringVisitor;
import pl.itgolo.libs.chromium.Browser;
import pl.itgolo.libs.chromium.Exceptions.ChromiumException;
import pl.itgolo.libs.chromium.Services.LogService;

/**
 * The type Get source action.
 */
public class GetSourceAction {

    private Browser browser;


    /**
     * Instantiates a new Get source action.
     *
     * @param browser the browser
     */
    public GetSourceAction(Browser browser) {
        this.browser = browser;
    }

    /**
     * Launch string.
     *
     * @param timeout                  the timeout
     * @param attempts                 the attempts
     * @param sleepAttemptMilliseconds the sleep attempt milliseconds
     * @return the string
     * @throws ChromiumException the chromium exception
     */
    public String launch(Integer timeout, Integer attempts, Integer sleepAttemptMilliseconds) throws ChromiumException {
        LogService.debug("Action get source");
        StringProperty source = new SimpleStringProperty();
        for (Integer i = 0 ; i <attempts ; i++){
            try {
                readSource(source, timeout);
                break;
            } catch (Exception e) {
                if (i.equals(attempts - 1)){
                    throw new ChromiumException(e);
                }
                NavigateToAction.sleep(sleepAttemptMilliseconds);
            }
        }
        return source.get();
    }

    private void readSource(StringProperty source, Integer timeout) throws ChromiumException {
        CefStringVisitor cefStringVisitor = new CefStringVisitor() {
            @Override
            public void visit(String string) {
                if (!browser.getJFrame().cefBrowser.isLoading()){
                    source.setValue(string);
                }
            }
        };
        final Integer waitTimeout = (timeout.equals(0)) ? 2 : timeout;
        for (int i =0 ; i < waitTimeout * 10 ; i++){
            browser.getJFrame().cefBrowser.getSource(cefStringVisitor);
            if (source.get() != null){
                break;
            }
           NavigateToAction.sleep(100);
        }
        if (source.get() == null){
            throw new ChromiumException("Not get source");
        }
    }
}

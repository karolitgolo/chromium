package pl.itgolo.libs.chromium.Actions;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.cef.callback.CefStringVisitor;
import pl.itgolo.libs.chromium.Browser;
import pl.itgolo.libs.chromium.Exceptions.ChromiumException;

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
        System.out.println("Get source");
        StringProperty source = new SimpleStringProperty();
        for (Integer i = 0 ; i <attempts ; i++){
            try {
                readSource(source, timeout);
                break;
            } catch (Exception e) {
                if (i.equals(attempts - 1)){
                    throw new ChromiumException(e);
                }
                System.out.println("Attempt get source: " + i + 1);
                NavigateToAction.sleep(sleepAttemptMilliseconds);
            }
        }
        System.out.println("Finished");
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
        for (int i =0 ; i < timeout * 10 ; i++){
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

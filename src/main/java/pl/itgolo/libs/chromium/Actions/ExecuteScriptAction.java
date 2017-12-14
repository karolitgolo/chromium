package pl.itgolo.libs.chromium.Actions;

import pl.itgolo.libs.chromium.Browser;
import pl.itgolo.libs.chromium.Exceptions.ChromiumException;
import pl.itgolo.libs.chromium.Scenes.Detailed.handler.MessageRouterReturnJSHandler;
import pl.itgolo.libs.chromium.Services.LogService;

/**
 * The type Execute script action.
 */
public class ExecuteScriptAction {

    private Browser browser;


    /**
     * Instantiates a new Execute script action.
     *
     * @param browser the browser
     */
    public ExecuteScriptAction(Browser browser) {
        this.browser = browser;
    }

    /**
     * Launch string.
     *
     * @param script         the script
     * @param objectJSReturn the object js return
     * @param timeout        the timeout
     * @return the string
     * @throws ChromiumException the chromium exception
     */
    public String launch(String script, String objectJSReturn, Integer timeout) throws ChromiumException {
        LogService.debug("Action execute script");
        MessageRouterReturnJSHandler.returnsJS.remove(browser.getJFrame().cefBrowser.getIdentifier());
        script = (script.trim().endsWith(";")) ? script : script+ ";";
        objectJSReturn = (objectJSReturn.trim().endsWith(";")) ? objectJSReturn : objectJSReturn+ ";";
        String cefQuery = script +
                "var resultCefQuery = " + objectJSReturn +
                "window.cefQuery({request: 'jsReturn:' + btoa(encodeURIComponent(resultCefQuery).replace(/%([0-9A-F]{2})/g, function(match, p1) {\n" +
                "        return String.fromCharCode(parseInt(p1, 16))\n" +
                "    })),\n" +
                "persistent: false,\n" +
                "onSuccess: function(response) { print(response); },\n" +
                "onFailure: function(error_code, error_message) {} });";
        browser.getJFrame().cefBrowser.executeJavaScript(cefQuery, "", 1);
        timeout = (timeout < 2) ? 2 : timeout;
            for (Integer i  = 0 ; i < timeout * 10 ; i++){
                if (MessageRouterReturnJSHandler.returnsJS.containsKey(browser.getJFrame().cefBrowser.getIdentifier())){
                    break;
                }
                NavigateToAction.sleep(100);
            }

        if (!MessageRouterReturnJSHandler.returnsJS.containsKey(browser.getJFrame().cefBrowser.getIdentifier())){
            return null;
        }
        return MessageRouterReturnJSHandler.returnsJS.get(browser.getJFrame().cefBrowser.getIdentifier());
    }
}

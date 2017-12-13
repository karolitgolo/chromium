package pl.itgolo.libs.chromium.Actions;

import pl.itgolo.libs.chromium.Browser;
import pl.itgolo.libs.chromium.Exceptions.ChromiumException;

/**
 * The type Click reload action.
 */
public class ClickReloadAction {

    private Browser browser;


    /**
     * Instantiates a new Click reload action.
     *
     * @param browser the browser
     */
    public ClickReloadAction(Browser browser) {
        this.browser = browser;
    }

    /**
     * Launch.
     *
     * @param cssSelector the css selector
     * @param timeout     the timeout
     * @throws ChromiumException the chromium exception
     */
    public void launch(String cssSelector, Integer timeout) throws ChromiumException {
        browser.actions.executeScript("var cefClickReloaded = false;");
        browser.actions.click(cssSelector);
        Boolean beginReload = false;
        for (Integer i =0 ; i <timeout * 4 ; i++){
            beginReload = beginReload(beginReload);
            if (beginReload){
                String isReady = browser.actions.executeScript("", "(document.readyState === 'complete')");
                if (isReady != null) {
                    if (isReady.equals("true")) {
                        return;
                    }
                }
            }
            NavigateToAction.sleep(250);
        }
        throw new ChromiumException("Failed reload after click");
    }

    private Boolean beginReload(Boolean beginReload) throws ChromiumException {
        if (beginReload == false){
            String noExistCefClickReloaded = browser.actions.executeScript("", "(typeof cefClickReloaded === 'undefined')");
            if (noExistCefClickReloaded != null) {
                if (noExistCefClickReloaded.equals("true")) {
                    beginReload = true;
                }
            }
        }
        return beginReload;
    }
}

package pl.itgolo.libs.chromium.Actions;

import pl.itgolo.libs.chromium.Browser;
import pl.itgolo.libs.chromium.Exceptions.ChromiumException;
import pl.itgolo.libs.chromium.Services.LogService;

/**
 * The type Resize action.
 */
public class ResizeAction {

    private Browser browser;


    /**
     * Instantiates a new Resize action.
     *
     * @param browser the browser
     */
    public ResizeAction(Browser browser) {
        this.browser = browser;
    }

    /**
     * Launch.
     *
     * @param width  the width
     * @param height the height
     * @throws ChromiumException the chromium exception
     */
    public void launch(Integer width, Integer height) throws ChromiumException {
        LogService.debug("Action resize");
        browser.getJFrame().setSize(width, height);
        for (Integer i = 0 ; i < 15 * 10; i++){
            Boolean validateWidth = width.equals(browser.getJFrame().getSize().width);
            Boolean validateHeight = height.equals(browser.getJFrame().getSize().height);
            if (validateWidth && validateHeight){
                return;
            }
            NavigateToAction.sleep(100);
        }
        throw new ChromiumException("Can not resize browser to size: " + width+ "x"+ height);
    }
}

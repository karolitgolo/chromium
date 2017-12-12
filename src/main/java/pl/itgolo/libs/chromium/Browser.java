package pl.itgolo.libs.chromium;

import lombok.Getter;
import org.cef.network.CefCookieManager;
import pl.itgolo.libs.chromium.Actions.BrowserActions;
import pl.itgolo.libs.chromium.Exceptions.ChromiumException;
import pl.itgolo.libs.chromium.Factories.BrowserFactory;
import pl.itgolo.libs.chromium.Scenes.BrowserJFrame;

import java.io.File;

/**
 * The type Browser.
 */
public class Browser {

    private BrowserFactory factory;

    @Getter
   private BrowserJFrame jFrame;

    @Getter
    private CefCookieManager cookieManager;

    /**
     * The Actions.
     */
    public BrowserActions actions;


    /**
     * Instantiates a new Browser.
     *
     * @throws ChromiumException the chromium exception
     */
    public Browser() throws ChromiumException {
        initialize("default");
    }

    /**
     * Instantiates a new Browser.
     *
     * @param profileName the profile name
     * @throws ChromiumException the chromium exception
     */
    public Browser(String profileName) throws ChromiumException {
        initialize(profileName);
    }

    private void initialize(String profileName) throws ChromiumException {
        factory = new BrowserFactory(profileName, new File("app/chromium"));
        jFrame = this.factory.browserJFrame();
        actions = new BrowserActions(this);
        cookieManager = jFrame.cefCookieManager;
    }
}
